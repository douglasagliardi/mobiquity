package com.mobiquityinc.service;

import com.mobiquityinc.dto.BundleStats;
import com.mobiquityinc.dto.PackageInputRequest;
import com.mobiquityinc.model.Item;
import com.mobiquityinc.model.Package;

import java.util.List;

public class EfficientPackageStrategy implements PackageStrategy {

    @Override
    public Package process(PackageInputRequest data) {
        for (Item item : data.getInput()) {
            if (item.getWeight() <= data.getBundle().getMaxWeightAllowed()) {
                if (hasSpaceFor(item, data.getStats().getCurrentWeight(), data.getBundle().getMaxWeightAllowed())) {
                    data.getBundle().addItemTo(item);
                } else if (hasPriceGreaterThanAllItemsInTheBucket(item, data.getStats().getCurrentCost())
                        && hasLessWeightThanAllItemsInTheBucket(item, data.getStats().getCurrentWeight())) {
                    replaceEntireList(data.getBundle().getThings(), item);
                }
                updatePackageStatus(data.getBundle(), data.getStats());
            }
        }
        return data.getBundle();
    }

    private void updatePackageStatus(Package aPackage, BundleStats currentStatus) {
        currentStatus.setCurrentWeight(getTotalPackageWeightFor(aPackage));
        currentStatus.setCurrentCost(getTotalCostFor(aPackage));
    }

    private boolean hasSpaceFor(Item item, float currentWeight, float maxWeight) {
        return (item.getWeight() + currentWeight) <= maxWeight;
    }

    private float getTotalPackageWeightFor(Package bundle) {
        float result = 0;
        for (Item it : bundle.getThings()) {
            result += it.getWeight();
        }
        return result;
    }

    private float getTotalCostFor(Package bundle) {
        float result = 0;
        //BigDecimal bd = new BigDecimal(0);
        for (Item it : bundle.getThings()) {
            result += it.getPrice();
            //bd.add(it.getPrice());
        }
        return result;
    }

    private void replaceEntireList(List<Item> items, Item element) {
        items.clear();
        items.add(element);
    }

    private boolean hasPriceGreaterThanAllItemsInTheBucket(Item element, float currentCost) {
        return hasValueEqualsOrGreaterThan(element.getPrice(), currentCost);
    }

    private boolean hasLessWeightThanAllItemsInTheBucket(Item element, float currentWeight) {
        return hasValueEqualsOrGreaterThan(element.getWeight(), currentWeight);
    }

    private boolean hasValueEqualsOrGreaterThan(float itemValue, float comparingTo) {
        return itemValue >= comparingTo;
    }
}