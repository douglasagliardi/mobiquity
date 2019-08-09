package com.mobiquityinc.service;

import com.mobiquityinc.dto.PackageInputRequest;
import com.mobiquityinc.model.BasePackage;

public interface PackageStrategy {

    BasePackage process(PackageInputRequest data);
}
