package com.mobiquityinc.service;

import com.mobiquityinc.dto.PackageInputRequest;
import com.mobiquityinc.model.BasePackage;
import com.mobiquityinc.model.Item;
import com.mobiquityinc.model.Package;
import com.mobiquityinc.model.PackageDecorator;
import com.mobiquityinc.model.PackagePrintDecorator;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;

public class EfficientPackageStrategyTest {

    private PackageStrategy service;


    EfficientPackageStrategyTest() {
        service = new EfficientPackageStrategy();
    }

    /*
    MAX-WEIGHT      INDEX   WEIGHT      PRICE
    81 :            (1 ,    53.38,      €45)
                    (2,     88.62,      €98)
                    (3,     78.48,      €3)
                    (4,     72.30,      €76)
                    (5,     30.18,      €9)
                    (6,     46.34,      €48)
     */
    @Test
    public void testCaseOne() {
        Item it1 = new Item(1, 53.3, 45);
        Item it2 = new Item(2, 88.6, 98);
        Item it3 = new Item(1, 78.4, 3);
        Item it4 = new Item(1, 72.3, 76);
        Item it5 = new Item(1, 30.18, 9);
        Item it6 = new Item(1, 46.34, 48);


        PackageInputRequest request = new PackageInputRequest();
        request.setInput(Arrays.asList(it1, it2, it3, it4, it5, it6));
        PackageDecorator bundle = new PackagePrintDecorator(new Package(81));
        request.setBundle(bundle);
        BasePackage result = service.process(request);

        assertThat(result.getThings(), hasSize(1));
        assertThat(result.getThings().get(0).getWeight(), equalTo(72.3));
        assertThat(result.getThings().get(0).getPrice(), equalTo(76));
    }

    @Test
    public void testCaseTwo() {
        Item it1 = new Item(1, 15.3, 34);

        PackageInputRequest request = new PackageInputRequest();
        request.setInput(Collections.singletonList(it1));
        PackageDecorator bundle = new PackagePrintDecorator(new Package(8));
        request.setBundle(bundle);
        BasePackage result = service.process(request);

        assertThat(result.getThings(), hasSize(0));
    }

    @Test
    public void testCaseThree() {
        Item it1 = new Item(1, 85.31, 29);
        Item it2 = new Item(2, 14.55, 74); // yes
        Item it3 = new Item(3, 3.98, 16);  // yes
        Item it4 = new Item(4, 26.24, 55); // yes
        Item it5 = new Item(5, 63.69, 52);
        Item it6 = new Item(6, 76.25, 75);
        Item it7 = new Item(7, 60.02, 74);
        Item it8 = new Item(8, 93.18, 35);
        Item it9 = new Item(9, 89.95, 78);


        PackageInputRequest request = new PackageInputRequest();
        request.setInput(Arrays.asList(it1, it2, it3, it4, it5, it6, it7, it8, it9));
        PackageDecorator bundle = new PackagePrintDecorator(new Package(75));
        request.setBundle(bundle);
        BasePackage result = service.process(request);

        assertThat(result.getThings(), hasSize(3));
        assertThat(result.getThings().stream().mapToInt(Item::getPrice).sum(), equalTo(145));
    }

    @Test
    public void testCaseFour() {
        Item it1 = new Item(1, 90.72, 13);
        Item it2 = new Item(2, 33.80, 40);
        Item it3 = new Item(3, 43.15, 10);
        Item it4 = new Item(4, 37.97, 16);
        Item it5 = new Item(5, 46.81, 36);
        Item it6 = new Item(6, 48.77, 79);
        Item it7 = new Item(7, 81.80, 45);
        Item it8 = new Item(8, 19.36, 79); // yes
        Item it9 = new Item(9, 6.76, 64); // yes


        PackageInputRequest request = new PackageInputRequest();
        request.setInput(Arrays.asList(it1, it2, it3, it4, it5, it6, it7, it8, it9));
        PackageDecorator bundle = new PackagePrintDecorator(new Package(56));
        request.setBundle(bundle);
        BasePackage result = service.process(request);

        assertThat(result.getThings(), hasSize(2));
        assertThat(result.getThings().stream().mapToInt(Item::getPrice).sum(), equalTo(143));
    }


    /*
        Edge case: need to replace the list wi
     */
    @Test
    public void testCaseFive() {
        Item it1 = new Item(1, 30.0, 41);
        Item it2 = new Item(2, 15.01, 20);
        Item it3 = new Item(3, 15.0, 20);

        PackageInputRequest request = new PackageInputRequest();
        request.setInput(Arrays.asList(it1, it2, it3));
        PackageDecorator bundle = new PackagePrintDecorator(new Package(56));
        request.setBundle(bundle);
        BasePackage result = service.process(request);

        assertThat(result.getThings(), hasSize(2));
        assertThat(round(sumItemsWeightInThePackage(result.getThings())), equalTo(45.01));
        assertThat(sumItemsPriceInThePackage(result.getThings()), equalTo(61));
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
