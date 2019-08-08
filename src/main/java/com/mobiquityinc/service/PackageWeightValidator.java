package com.mobiquityinc.service;

import com.mobiquityinc.dto.PackageInputRequest;
import com.mobiquityinc.exception.PackageOverWeightException;
import lombok.NoArgsConstructor;

import java.util.Objects;

@NoArgsConstructor
public class PackageWeightValidator extends PackageValidator {

    private int PACKAGE_MAX_WEIGHT_ALLOWED = 100;

    public PackageWeightValidator(int maxWeightAllowed) {
        PACKAGE_MAX_WEIGHT_ALLOWED = maxWeightAllowed;
    }

    @Override
    public boolean isValid(PackageInputRequest request) {
        if (request.getBundle().getMaxWeightAllowed() > PACKAGE_MAX_WEIGHT_ALLOWED) {
            throw new PackageOverWeightException("The package has more weight than it should be. Max Weight allowed : " + PACKAGE_MAX_WEIGHT_ALLOWED + " . Package Weight : " + request.getBundle().getMaxWeightAllowed());
        }
        if (Objects.isNull(nextValidator)) {
            return true;
        }
        return nextValidator.isValid(request);
    }
}
