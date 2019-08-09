package com.mobiquityinc.exception;

public class NoPackageEligibleForSelectionException extends RuntimeException {

    private static final String DEFAULT_MESSAGE = "There is no package eligible for select in the end of the process";

    public NoPackageEligibleForSelectionException() {
        super(DEFAULT_MESSAGE);
    }
}
