package com.mobiquityinc.service;

import com.mobiquityinc.exception.NoPackageEligibleForSelectionException;
import com.mobiquityinc.model.BasePackage;
import com.mobiquityinc.model.Item;

import java.util.Comparator;
import java.util.List;

public class PackageSelectorService {

    public BasePackage select(List<BasePackage> packageList) {
        return packageList.stream()
                .filter(it -> !it.getThings().isEmpty())
                .sorted(Comparator.comparing(BasePackage::getMaxWeightAllowed)
                        .thenComparing(it -> it.getThings().stream().mapToInt(Item::getPrice).sum()))
                .findAny().orElseThrow(NoPackageEligibleForSelectionException::new);
    }
}
