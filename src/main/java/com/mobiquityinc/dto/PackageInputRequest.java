package com.mobiquityinc.dto;

import com.mobiquityinc.model.Item;
import com.mobiquityinc.model.Package;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PackageInputRequest {
    private Package bundle;
    private BundleStats stats = new BundleStats();
    private List<Item> input = new ArrayList<>();
}
