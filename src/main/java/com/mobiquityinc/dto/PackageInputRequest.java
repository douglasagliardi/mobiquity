package com.mobiquityinc.dto;

import com.mobiquityinc.model.Item;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PackageInputRequest {
    private int maxWeight;
    private int currentWeight;
    private List<Item> input = new ArrayList<>();
    private List<Item> items = new ArrayList<>();
}
