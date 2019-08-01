package com.mobiquityinc.model;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Bucket {
    private float maxWeightAllowed;
    private List<Item> things;

    public Bucket(float maxWeight) {
        this.maxWeightAllowed = maxWeight;
        things = new ArrayList<>();
    }

    public void addItemTo(Item element) {
        //if(hasSpaceFor(element)) {
        things.add(element);
        // }
        //else {
        //    throw new PackageOverWeightException("The item has more weight than this package can support.");
        //}
    }

    //private boolean hasSpaceFor(Item item) {
    //        return item.getWeight() + currentPackageWeight() <= 100;
    //    }

//    private float currentPackageWeight() {
//        float result = 0F;
//        for (Item it : things) {
//            result += it.getWeight();
//        }
//        return result;
//    }
}
