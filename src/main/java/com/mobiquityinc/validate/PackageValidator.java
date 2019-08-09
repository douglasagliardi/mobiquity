package com.mobiquityinc.validate;

import com.mobiquityinc.dto.PackageInputRequest;

public abstract class PackageValidator {

    protected PackageValidator nextValidator;

    public PackageValidator linkToNext(PackageValidator packageValidator) {
        return this.nextValidator = packageValidator;
    }

    public abstract boolean isValid(PackageInputRequest request);
}
