package com.mobiquityinc.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
public class Package {
    private float maxWeightAllowed;
    private List<Item> things;

    public Package(float maxWeight) {
        this.maxWeightAllowed = maxWeight;
        things = new ArrayList<>();
    }

    public void addItemTo(Item element) {
        things.add(element);
    }
}
