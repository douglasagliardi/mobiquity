package com.mobiquityinc.dto;

import com.mobiquityinc.model.Item;
import com.mobiquityinc.model.Package;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PackageInputRequest {
    private Package bundle;
    private BundleStats stats;
    private List<Item> input = new ArrayList<>();
}
