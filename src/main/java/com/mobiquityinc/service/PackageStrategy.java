package com.mobiquityinc.service;

import com.mobiquityinc.dto.PackageInputRequest;
import com.mobiquityinc.model.Package;

public interface PackageStrategy {

    Package process(PackageInputRequest data);
}
