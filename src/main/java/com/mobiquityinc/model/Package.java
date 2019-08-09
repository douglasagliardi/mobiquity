package com.mobiquityinc.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
public class Package extends BasePackage {
    private float maxWeightAllowed;
    private List<Item> things;

    public Package(float maxWeight) {
        this.maxWeightAllowed = maxWeight;
        things = new ArrayList<>();
    }

    @Override
    public void addItemTo(Item element) {
        things.add(element);
    }
}
