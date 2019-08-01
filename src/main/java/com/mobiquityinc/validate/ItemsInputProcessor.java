package com.mobiquityinc.validate;

import com.mobiquityinc.dto.PackageInputRequest;

public class ItemsInputProcessor extends InputProcessor {

    private int ITEM_MAX_COST_ALLOWED = 100, ITEM_MAX_WEIGHT_ALLOWED = 100;

    public ItemsInputProcessor(int itemMaxCostAllowed, int itemMaxWeightAllowed) {
        if(itemMaxCostAllowed >= 0) {
            this.ITEM_MAX_COST_ALLOWED = itemMaxCostAllowed;
        }
        if(itemMaxWeightAllowed >= 0) {
            this.ITEM_MAX_WEIGHT_ALLOWED = itemMaxWeightAllowed;
        }
    }

    @Override
    public boolean check(PackageInputRequest request) {
        //request.getInput().stream().filter();
        return checkNext(request);
    }
}
