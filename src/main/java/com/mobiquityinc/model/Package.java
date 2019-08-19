package com.mobiquityinc.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.ArrayList;

@Getter
@ToString
@AllArgsConstructor
public class Package extends BasePackage {

    public Package(double maxWeight) {
        setMaxWeightAllowed(maxWeight);
        setThings(new ArrayList<>());
    }

    @Override
    public void addItemTo(Item element) {
        getThings().add(element);
    }
}
