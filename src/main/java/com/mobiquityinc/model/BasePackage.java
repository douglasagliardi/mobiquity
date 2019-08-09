package com.mobiquityinc.model;

import lombok.Data;

import java.util.List;

@Data
public abstract class BasePackage implements IPackage {
    private float maxWeightAllowed;
    private List<Item> things;

    public abstract void addItemTo(Item item);
}
