package com.mobiquityinc.service;

import com.mobiquityinc.dto.PackageInputRequest;
import com.mobiquityinc.dto.RawPackage;
import com.mobiquityinc.exception.APIException;
import com.mobiquityinc.helper.DataTypeConverter;
import com.mobiquityinc.model.Item;
import com.mobiquityinc.model.Package;
import com.mobiquityinc.model.PackageDecorator;
import com.mobiquityinc.model.PackagePrintDecorator;
import com.mobiquityinc.validate.PackageItemsWeightAndCostValidator;
import com.mobiquityinc.validate.PackageValidator;
import com.mobiquityinc.validate.PackageWeightValidator;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class PackageConverterServiceImpl implements PackageConverterService {

    private DataTypeConverter dataConverter;
    private PackageValidator validator;

    public PackageConverterServiceImpl(DataTypeConverter converter) {
        this.dataConverter = converter;
        validator = new PackageWeightValidator();
        validator.linkToNext(new PackageItemsWeightAndCostValidator());
    }

    @Override
    public List<PackageInputRequest> convert(List<RawPackage> rawPackages) {
        return rawPackages.stream()
                .map(this::convert)
                .filter(it -> validator.isValid(it))
                .collect(toList());
    }

    @Override
    public PackageInputRequest convert(RawPackage rawPackage) {
        PackageInputRequest request = new PackageInputRequest();
        PackageDecorator pkg = new PackagePrintDecorator(new Package(dataConverter.convertToDouble(rawPackage.getMaxWeight())));
        for (String item : rawPackage.getItems()) {
            request.getInput().add(produceItem(item));
        }
        request.setBundle(pkg);
        return request;
    }


    private Item produceItem(String entry) {
        List<String> fields = Arrays.asList(entry.split(","));
        Integer index;
        Double weight;
        Integer price;
        try {
            index = dataConverter.convertToInteger(fields.get(0));
            weight = dataConverter.convertToDouble(fields.get(1));
            price = dataConverter.convertToInteger(fields.get(2).replace("€", ""));
        } catch (NumberFormatException exception) {
            throw new APIException("Some value is invalid");
        }
        return new Item(index, weight, price);
    }
}
