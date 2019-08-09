package com.mobiquityinc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BundleStats {
    private float currentWeight;
    private float currentCost;
}
