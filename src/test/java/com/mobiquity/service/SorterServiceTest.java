package com.mobiquity.service;

import com.mobiquityinc.dto.PackageInputRequest;
import com.mobiquityinc.model.Bucket;
import com.mobiquityinc.model.Item;
import com.mobiquityinc.service.SorterService;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;

public class SorterServiceTest {

    private SorterService service = new SorterService();

    @Test
    public void acceptItems() {
        PackageInputRequest request = new PackageInputRequest();
        request.setInput(Arrays.asList(
                new Item(1, 40, 40),
                new Item(2, 40, 40),
                new Item(3, 40, 50)));
        Bucket bucket = new Bucket(91);
        request.setBucket(bucket);
        Bucket result = service.process(request);

        assertThat(result.getThings(), not((empty())));
        assertThat(result.getMaxWeightAllowed(), equalTo(91.0F));
        assertThat(result.getThings().size(), equalTo(2));
        //assertThat(result.getThings().get(1).getPrice(), equalTo(50.0F));
    }

    @Test
    public void t1() {
        //Map<Float, List<Item>> items = new TreeMap<>();
        //items.put(20F, Arrays.asList(new Item(1, 20, 30), new Item(2, 30, 40)));
        //service.canReplaceElementFor(items, new Item());
        List<Item> result = service.tryToReplace(Arrays.asList(new Item(1, 20, 30), new Item(2, 30, 40)), new Item(1, 20, 50));
        assertThat(result.size(), equalTo(2));
        assertThat(result.get(1).getPrice(), equalTo(50.0));
    }
}
