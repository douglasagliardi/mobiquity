package com.mobiquityinc.model;

import lombok.Getter;

public class PackageDecorator extends BasePackage {

    @Getter
    protected BasePackage decoratedPackage;

    public PackageDecorator(BasePackage basePackage) {
        decoratedPackage = basePackage;
    }

    @Override
    public void addItemTo(Item item) {
        decoratedPackage.addItemTo(item);
    }
}
