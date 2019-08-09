package com.mobiquityinc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RawPackage {
    private String maxWeight;
    private List<String> items = new ArrayList<>();
}
