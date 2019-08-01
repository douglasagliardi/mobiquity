package com.mobiquity;

import com.mobiquityinc.exception.PackageOverWeightException;
import com.mobiquityinc.model.Bucket;
import com.mobiquityinc.model.Item;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.collection.IsEmptyCollection.empty;

public class PackageThingsTest {

    private Bucket bucket;

    @BeforeEach
    public void setup() {
        bucket = new Bucket(100);
    }

    @Test
    public void simpleAddItemsIntoTheBucket() {
        Item it = new Item(1, 2.89F, 4.0F);
        bucket.addItemTo(it);
        assertThat(bucket.getThings(), not(empty()));
    }

    @Test
    public void addItemsTillTheWeightLimit() {
        Item it1 = new Item(1, 50.00F, 4.50F);
        Item it2 = new Item(2, 51.00F, 9.50F);
        bucket.addItemTo(it1);
        Assertions.assertThrows(PackageOverWeightException.class, () -> bucket.addItemTo(it2));
    }
}
