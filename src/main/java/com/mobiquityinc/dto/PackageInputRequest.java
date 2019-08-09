package com.mobiquityinc.dto;

import com.mobiquityinc.model.BasePackage;
import com.mobiquityinc.model.Item;
import com.mobiquityinc.model.PackageDecorator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PackageInputRequest {
    private PackageDecorator bundle;
    private BundleStats stats = new BundleStats();
    private List<Item> input = new ArrayList<>();

    public BasePackage getBundle() {
        return bundle.getDecoratedPackage();
    }
}
