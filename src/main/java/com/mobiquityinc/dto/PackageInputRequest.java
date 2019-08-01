package com.mobiquityinc.dto;

import com.mobiquityinc.model.Bucket;
import com.mobiquityinc.model.Item;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PackageInputRequest {
    private float maxWeight;
    private float currentWeight;
    private List<Item> input = new ArrayList<>();
    private Bucket bucket;
}
