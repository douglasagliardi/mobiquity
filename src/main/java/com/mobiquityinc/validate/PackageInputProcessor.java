package com.mobiquityinc.validate;

import com.mobiquityinc.dto.PackageInputRequest;

public class PackageInputProcessor extends InputProcessor {

    private int PACKAGE_MAX_WEIGHT_ALLOWED = 100;

    public PackageInputProcessor(int packageWeight) {
        if(packageWeight > 0) {
            this.PACKAGE_MAX_WEIGHT_ALLOWED = packageWeight;
        }
    }

    @Override
    public boolean check(PackageInputRequest request) {
        /*if(request.getMaxWeight() <= PACKAGE_MAX_WEIGHT_ALLOWED) {
            return checkNext(request);
        }*/
        return false;
    }
}
