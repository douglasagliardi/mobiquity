package com.mobiquityinc.service;

import com.mobiquityinc.dto.PackageInputRequest;
import com.mobiquityinc.model.Bucket;
import com.mobiquityinc.model.Item;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class SorterService {

    /*
    MAX-WEIGHT      INDEX   WEIGHT      PRICE
    81 :            (1 ,    53.38,      €45)
                    (2,     88.62,      €98)
                    (3,     78.48,      €3)
                    (4,     72.30,      €76)
                    (5,     30.18,      €9)
                    (6,     46.34,      €48)
     */
    public Bucket process(PackageInputRequest data) {
        for (Item it : data.getInput()) {
            if (hasSpaceFor(it,currentPackageWeight(data.getBucket()), data.getBucket().getMaxWeightAllowed())) {
                data.getBucket().addItemTo(it);
                currentPackageWeight(data.getBucket());
                data.setCurrentWeight(sumWeight(data.getBucket(), it.getWeight()));
            }
        }
        data.getBucket().getThings().forEach(System.out::println);
        return data.getBucket();
    }

    private float sumWeight(Bucket bucket, float weight) {
        return currentPackageWeight(bucket) + weight;
    }

    private boolean hasSpaceFor(Item item, float currentWeight, float maxWeight) {
        return item.getWeight() + currentWeight <= maxWeight;
    }

    private float currentPackageWeight(Bucket bucket) {
        float result = 0;
        for (Item it : bucket.getThings()) {
            result += it.getWeight();
        }
        return result;
    }

    public void canReplaceElementFor(Map<Float, List<Item>> items, Item element) {
        for (Map.Entry<Float, List<Item>> it : items.entrySet()) {
            if(items.containsKey(element.getPrice())) {
            }
        }
//            if(items.containsKey(element.getPrice())) {
//
//            }
//            if(element.getPrice() > it.getPrice()) {
//                canReplace = true;
//                itemToReplace = it;
//            }
//            break;
//        }
    }

    public List<Item> tryToReplace(List<Item> items, Item element) {
        int index = items.size() -1;
        for(; index >= 0; index--) {
            if(element.getPrice() >= items.get(index).getPrice() && element.getWeight() <= items.get(index).getWeight()) {
                System.out.println("Item at index " + index  + " is " + items.get(index).toString());
                System.out.println("Found one to replace -> " + element.toString());
            }
            break;
        }
        items.set(index, element);
        return items;
    }
}
