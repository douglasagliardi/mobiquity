package com.mobiquityinc.service;

import com.mobiquityinc.dto.PackageInputRequest;
import com.mobiquityinc.model.Item;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class PackageItemsWeightAndCostValidator extends PackageValidator {

    private int MAX_ITEM_WEIGHT_ALLOWED = 100;
    private int MAX_ITEM_COST_ALLOWED = 100;

    public PackageItemsWeightAndCostValidator(int maxItemWeightAllowed, int maxItemCostAllowed) {
        MAX_ITEM_WEIGHT_ALLOWED = maxItemWeightAllowed;
        MAX_ITEM_COST_ALLOWED = maxItemCostAllowed;
    }

    @Override
    public boolean isValid(PackageInputRequest request) {
        return request.getBundle()
                .getThings()
                .stream()
                .noneMatch(item -> hasItemWeightNotPermitted(item) || hasItemCostNotPermitted(item));
    }

    private boolean hasItemWeightNotPermitted(Item item) {
        return item.getWeight() >= MAX_ITEM_WEIGHT_ALLOWED;
    }

    private boolean hasItemCostNotPermitted(Item item) {
        return item.getPrice() >= MAX_ITEM_COST_ALLOWED;
    }
}
