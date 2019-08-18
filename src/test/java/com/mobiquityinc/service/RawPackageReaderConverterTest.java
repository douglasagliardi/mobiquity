package com.mobiquityinc.service;


import com.mobiquityinc.dto.RawPackage;
import com.mobiquityinc.exception.APIException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;

public class RawPackageReaderConverterTest {

    private RawPackageReaderConverter converter = new RawPackageReaderConverter();

    @Test
    public void simpleConversion() {
        List<RawPackage> result = converter.producePackage(Collections.singletonList("8 : (1,15.3,€34)"));
        assertThat(result, hasSize(1));
        assertThat(result.get(0).getMaxWeight(), equalTo("8"));
    }


    @Test
    public void convertingTwoEntries() {
        List<RawPackage> result = converter.producePackage(Arrays.asList("8 : (1,15.3,€34)", "75 : (1,85.31,€29) (2,14.55,€74)"));
        assertThat(result, hasSize(2));
        assertThat(result.get(0).getMaxWeight(), equalTo("8"));
        assertThat(result.get(0).getItems().size(), equalTo(1));

        assertThat(result.get(1).getMaxWeight(), equalTo("75"));
        assertThat(result.get(1).getItems().size(), equalTo(2));
    }

    @Test
    public void throwingExceptionWhenItemContainsInvalidFormat() {
        Assertions.assertThrows(APIException.class, () ->
                converter.producePackage(Collections.singletonList("8 : (1,15.3-€34)")));
    }
}