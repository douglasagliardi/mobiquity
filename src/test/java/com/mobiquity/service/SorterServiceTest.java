package com.mobiquity.service;

import com.mobiquityinc.dto.PackageInputRequest;
import com.mobiquityinc.model.Item;
import com.mobiquityinc.model.Package;
import com.mobiquityinc.service.SorterService;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;

public class SorterServiceTest {

    private SorterService service;


    SorterServiceTest() {
        service = new SorterService();
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
        Item it1 = new Item(1, 53.38F, 45);
        Item it2 = new Item(2, 88.62F, 98);
        Item it3 = new Item(1, 78.48F, 3);
        Item it4 = new Item(1, 72.30F, 76);
        Item it5 = new Item(1, 30.18F, 9);
        Item it6 = new Item(1, 46.34F, 48);


        PackageInputRequest request = new PackageInputRequest();
        request.setInput(Arrays.asList(it1, it2, it3, it4, it5, it6));
        Package bundle = new Package(81);
        request.setBundle(bundle);
        Package result = service.process(request);

        assertThat(result.getThings(), hasSize(1));
        assertThat(result.getThings().get(0).getWeight(), equalTo(72.30F));
        assertThat(result.getThings().get(0).getPrice(), equalTo(76));
    }

    @Test
    public void testCaseTwo() {
        Item it1 = new Item(1, 15.3F, 34);

        PackageInputRequest request = new PackageInputRequest();
        request.setInput(Collections.singletonList(it1));
        Package bundle = new Package(8);
        request.setBundle(bundle);
        Package result = service.process(request);

        assertThat(result.getThings(), hasSize(0));
    }

    @Test
    public void testCaseThree() {
        Item it1 = new Item(1, 85.31F, 29);
        Item it2 = new Item(2, 14.55F, 74); // yes
        Item it3 = new Item(3, 3.98F, 16);  // yes
        Item it4 = new Item(4, 26.24F, 55); // yes
        Item it5 = new Item(5, 63.69F, 52);
        Item it6 = new Item(6, 76.25F, 75);
        Item it7 = new Item(7, 60.02F, 74);
        Item it8 = new Item(8, 93.18F, 35);
        Item it9 = new Item(9, 89.95F, 78);


        PackageInputRequest request = new PackageInputRequest();
        request.setInput(Arrays.asList(it1, it2, it3, it4, it5, it6, it7, it8, it9));
        Package bundle = new Package(75);
        request.setBundle(bundle);
        Package result = service.process(request);

        assertThat(result.getThings(), hasSize(3));
        assertThat(result.getThings().stream().mapToInt(it -> (int) it.getPrice()).sum(), equalTo(145));
    }

    @Test
    public void testCaseFour() {
        Item it1 = new Item(1, 90.72F, 13);
        Item it2 = new Item(2, 33.80F, 40);
        Item it3 = new Item(3, 43.15F, 10);
        Item it4 = new Item(4, 37.97F, 16);
        Item it5 = new Item(5, 46.81F, 36);
        Item it6 = new Item(6, 48.77F, 79);
        Item it7 = new Item(7, 81.80F, 45);
        Item it8 = new Item(8, 19.36F, 79); // yes
        Item it9 = new Item(9, 6.76F, 64); // yes


        PackageInputRequest request = new PackageInputRequest();
        request.setInput(Arrays.asList(it1, it2, it3, it4, it5, it6, it7, it8, it9));
        Package bundle = new Package(56);
        request.setBundle(bundle);
        Package result = service.process(request);

        assertThat(result.getThings(), hasSize(2));
        assertThat(result.getThings().stream().mapToInt(Item::getPrice).sum(), equalTo(143));
    }


    /*
    Edge case: need to replace the list wi
     */
    @Test
    public void testCaseFive() {
        Item it1 = new Item(1, 30.00F, 41);
        Item it2 = new Item(2, 15.01F, 20);
        Item it3 = new Item(3, 15.00F, 20);
        /*Item it4 = new Item(4, 37.97F, 16);
        Item it5 = new Item(5, 46.81F, 36);
        Item it6 = new Item(6, 48.77F, 79);
        Item it7 = new Item(7, 81.80F, 45);
        Item it8 = new Item(8, 19.36F, 79); // yes
        Item it9 = new Item(9, 6.76F, 64); // yes
        */

        PackageInputRequest request = new PackageInputRequest();
        request.setInput(Arrays.asList(it1, it2, it3));
        Package bundle = new Package(56);
        request.setBundle(bundle);
        Package result = service.process(request);

        assertThat(result.getThings(), hasSize(1));
        assertThat(result.getThings().stream().mapToInt(Item::getPrice).sum(), equalTo(41));
    }
}
