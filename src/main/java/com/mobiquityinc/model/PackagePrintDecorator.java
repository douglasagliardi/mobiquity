package com.mobiquityinc.model;

public class PackagePrintDecorator extends PackageDecorator {

    public PackagePrintDecorator(BasePackage bPackage) {
        super(bPackage);
    }

    @Override
    public void addItemTo(Item item) {
        decoratedPackage.addItemTo(item);
        System.out.println(item.getIndex());
        System.out.println("-");
        System.out.println(item.getWeight());
        System.out.println(item.getPrice());
        System.out.println();
    }
}
