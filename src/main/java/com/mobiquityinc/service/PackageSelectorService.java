package com.mobiquityinc.service;

import com.mobiquityinc.exception.NoPackageEligibleForSelectionException;
import com.mobiquityinc.model.Item;
import com.mobiquityinc.model.Package;

import java.util.Comparator;
import java.util.List;

public class PackageSelectorService {

    public Package select(List<Package> packageList) {
        return packageList.stream()
                .filter(it -> !it.getThings().isEmpty())
                .sorted(Comparator.comparing(Package::getMaxWeightAllowed)
                        .thenComparing(it -> it.getThings().stream().mapToInt(Item::getPrice).sum()))
                .findAny().orElseThrow(NoPackageEligibleForSelectionException::new);
    }
}
