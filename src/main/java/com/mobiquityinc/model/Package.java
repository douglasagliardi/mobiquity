package com.mobiquityinc.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.ArrayList;

@Getter
@AllArgsConstructor
@ToString
public class Package extends BasePackage {

    public Package(float maxWeight) {
        setMaxWeightAllowed(maxWeight);
        setThings(new ArrayList<>());
    }

    @Override
    public void addItemTo(Item element) {
        getThings().add(element);
    }
}
