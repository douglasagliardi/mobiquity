package com.mobiquityinc.service;

import com.mobiquityinc.dto.PackageInputRequest;
import com.mobiquityinc.dto.RawPackage;
import com.mobiquityinc.exception.APIException;
import com.mobiquityinc.helper.DataTypeConverter;
import com.mobiquityinc.model.Item;
import com.mobiquityinc.model.Package;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class PackageConverterServiceImpl implements PackageConverterService {

    private DataTypeConverter dataConverter;

    public PackageConverterServiceImpl(DataTypeConverter converter) {
        this.dataConverter = converter;
    }

    @Override
    public List<PackageInputRequest> convert(List<RawPackage> rawPackages) {
        return rawPackages.stream().map(this::convert).collect(toList());
    }

    @Override
    public PackageInputRequest convert(RawPackage rawPackage) {
        PackageInputRequest request = new PackageInputRequest();
        Package pkg = new Package(dataConverter.convertToInteger(rawPackage.getMaxWeight()));
        for (String item : rawPackage.getItems()) {
            pkg.addItemTo(produceItem(item));
        }
        request.setBundle(pkg);
        return request;
    }


    private Item produceItem(String entry) {
        List<String> fields = Arrays.asList(entry.split(","));
        Integer index;
        Float weight;
        Integer price;
        try {
            index = dataConverter.convertToInteger(fields.get(0));
            weight = dataConverter.convertToFloat(fields.get(1));
            price = dataConverter.convertToInteger(fields.get(2).replace("€", ""));
        } catch (NumberFormatException exception) {
            throw new APIException("Some value is invalid");
        }
        return new Item(index, weight, price);
    }
}
