package com.mobiquityinc.service;

import com.mobiquityinc.dto.PackageInputRequest;
import com.mobiquityinc.dto.RawPackage;

import java.util.List;

public interface PackageConverterService {

    List<PackageInputRequest> convert(List<RawPackage> rawPackages);

    PackageInputRequest convert(RawPackage rawPackage);
}
