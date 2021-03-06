package com.mobiquityinc.packer;

import com.mobiquityinc.dto.PackageInputRequest;
import com.mobiquityinc.dto.RawPackage;
import com.mobiquityinc.helper.DataTypeConverter;
import com.mobiquityinc.helper.FileReader;
import com.mobiquityinc.model.BasePackage;
import com.mobiquityinc.service.EfficientPackageStrategy;
import com.mobiquityinc.service.PackageConverterService;
import com.mobiquityinc.service.PackageConverterServiceImpl;
import com.mobiquityinc.service.PackageSelectorService;
import com.mobiquityinc.service.PackageStrategy;
import com.mobiquityinc.service.RawPackageReaderConverter;

import java.util.ArrayList;
import java.util.List;

public class Packer {

    public static void pack(String absoluteFilePath) {
        FileReader reader = new FileReader();
        List<String> entries = reader.readFile(absoluteFilePath);

        DataTypeConverter dataTypeConverter = new DataTypeConverter();
        PackageConverterService packageConverterService = new PackageConverterServiceImpl(dataTypeConverter);


        RawPackageReaderConverter rawPackageReaderConverter = new RawPackageReaderConverter();
        List<RawPackage> rawPackages = rawPackageReaderConverter.producePackage(entries);

        List<PackageInputRequest> packageInputRequests = packageConverterService.convert(rawPackages);

        PackageStrategy strategy = new EfficientPackageStrategy();
        List<BasePackage> finalPackageList = new ArrayList<>();
        for (PackageInputRequest request : packageInputRequests) {
            finalPackageList.add(strategy.process(request));
        }

        PackageSelectorService pkgSelector = new PackageSelectorService();
        BasePackage mostEfficientPackageToPickup = pkgSelector.select(finalPackageList);
        System.out.println(mostEfficientPackageToPickup.toString());
    }
}
