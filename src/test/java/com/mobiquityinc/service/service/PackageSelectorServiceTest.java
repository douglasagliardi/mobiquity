package com.mobiquityinc.service.service;

import com.mobiquityinc.model.Item;
import com.mobiquityinc.model.Package;
import com.mobiquityinc.service.PackageSelectorService;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class PackageSelectorServiceTest {

    @Test
    public void isSelectorPickingUpThePackageWithTheHigherPrice() {
        PackageSelectorService selectorService = new PackageSelectorService();
        Package p1 = new Package(57);
        p1.addItemTo(new Item(1, 20.00F, 30));
        Package p2 = new Package(80);
        p2.addItemTo(new Item(1, 25.00F, 50));
        Package p3 = new Package(56);
        p3.addItemTo(new Item(1, 20.00F, 31));

        Package result = selectorService.select(Arrays.asList(p1, p2, p3));
        assertThat(result, is(notNullValue()));
        assertThat(sumItemsPriceInThePackage(result.getThings()), equalTo(31));
    }

    @Test
    public void isSelectorPickingUpTheLighterPackageEvenIfBothHaveSameCost() {
        PackageSelectorService selectorService = new PackageSelectorService();
        Package p1 = new Package(57);
        p1.addItemTo(new Item(1, 20.00F, 30));
        Package p3 = new Package(56);
        p3.addItemTo(new Item(1, 20.01F, 30));

        Package result = selectorService.select(Arrays.asList(p1, p3));
        assertThat(result, is(notNullValue()));
        assertThat(round(sumItemsWeightInThePackage(result.getThings())), equalTo(20.01));
    }

    @Test
    public void isSelectorPickingUpTheRightPackageWithTheSamePriceButLessHeavy() {
        PackageSelectorService selectorService = new PackageSelectorService();
        Package p1 = new Package(57);
        p1.addItemTo(new Item(1, 20.00F, 30));
        Package p2 = new Package(80);
        p2.addItemTo(new Item(1, 25.00F, 50));
        Package p3 = new Package(57);
        p3.addItemTo(new Item(1, 11.00F, 15));
        p3.addItemTo(new Item(2, 10.00F, 15));

        Package result = selectorService.select(Arrays.asList(p1, p2, p3));
        assertThat(result, is(notNullValue()));
        assertThat(result.getThings().size(), equalTo(1));
        assertThat(sumItemsPriceInThePackage(result.getThings()), equalTo(30));
    }

    private int sumItemsPriceInThePackage(List<Item> items) {
        return items.stream().mapToInt(Item::getPrice).sum();
    }

    private double sumItemsWeightInThePackage(List<Item> items) {
        return items.stream().mapToDouble(Item::getWeight).sum();
    }

    private double round(double value) {
        BigDecimal tmp = BigDecimal.valueOf(value);
        tmp = tmp.setScale(2, RoundingMode.HALF_UP);
        return tmp.doubleValue();
    }
}
