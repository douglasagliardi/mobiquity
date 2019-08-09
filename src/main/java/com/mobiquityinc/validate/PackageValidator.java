package com.mobiquityinc.validate;

import com.mobiquityinc.dto.PackageInputRequest;

import java.util.Objects;

public abstract class PackageValidator {

    protected PackageValidator nextValidator;

    public PackageValidator linkToNext(PackageValidator packageValidator) {
        return this.nextValidator = packageValidator;
    }

    public abstract boolean isValid(PackageInputRequest request);


    protected boolean ifValidGoNextStep(PackageInputRequest request) {
        return !Objects.nonNull(nextValidator) || nextValidator.ifValidGoNextStep(request);
    }

}
