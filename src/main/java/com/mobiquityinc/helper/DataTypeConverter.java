package com.mobiquityinc.helper;

public class DataTypeConverter {

    public Integer convertToInteger(String value) {
        return Integer.parseInt(value);
    }

    public Float convertToFloat(String value) {
        return Float.parseFloat(value);
    }
}
