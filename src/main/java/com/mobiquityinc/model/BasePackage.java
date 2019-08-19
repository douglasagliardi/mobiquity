package com.mobiquityinc.model;

import lombok.Data;

import java.util.List;

@Data
public abstract class BasePackage implements IPackage {
    private double maxWeightAllowed;
    private List<Item> things;

    public abstract void addItemTo(Item item);
}
