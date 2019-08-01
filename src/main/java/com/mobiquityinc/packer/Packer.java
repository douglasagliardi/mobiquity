package com.mobiquityinc.packer;

import com.mobiquityinc.exception.APIException;

public class Packer {

    public static void pack(String absoluteFilePath) {
        if(absoluteFilePath.isEmpty()) {
            throw new APIException("The path " + absoluteFilePath + " is empty or invalid");
        }
    }
}
