package com.mobiquityinc.service.service;

import com.mobiquityinc.dto.PackageInputRequest;
import com.mobiquityinc.dto.RawPackage;
import com.mobiquityinc.helper.DataTypeConverter;
import com.mobiquityinc.service.PackageConverterServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PackageConverterServiceImplTest {

    private DataTypeConverter converter;
    private PackageConverterServiceImpl packageConverterService;

    @BeforeEach
    void setup() {
        converter = mock(DataTypeConverter.class, Mockito.RETURNS_DEEP_STUBS);
        when(converter.convertToInteger("1")).thenReturn(1);
        when(converter.convertToInteger("50")).thenReturn(50);
        when(converter.convertToFloat("50.00F")).thenReturn(50.00F);

        when(converter.convertToInteger("2")).thenReturn(2);
        when(converter.convertToInteger("10")).thenReturn(10);
        when(converter.convertToFloat("10.00F")).thenReturn(10.00F);

        packageConverterService = new PackageConverterServiceImpl(converter);
    }

    @Test
    public void isConversionReturningTheExpectedObjectWithAllPreFilledFields() {
        RawPackage rawPackage = new RawPackage();
        rawPackage.setMaxWeight("50.00F");
        rawPackage.setItems(Collections.singletonList("1, 50.00F, 50"));
        PackageInputRequest result = packageConverterService.convert(rawPackage);
        assertThat(result, notNullValue());
        assertThat(result.getBundle().getMaxWeightAllowed(), equalTo(50.0F));
        assertThat(result.getInput(), hasSize(1));
    }

    @Test
    public void isConversionReturningListOfPackageCandidates() {
        RawPackage rawPackage = new RawPackage();
        rawPackage.setMaxWeight("50");
        rawPackage.setItems(Arrays.asList("1, 50.00F, 50", "2, 10.00F, 10"));
        List<PackageInputRequest> result = packageConverterService.convert(Collections.singletonList(rawPackage));
        assertThat(result, notNullValue());
        assertThat(result.get(0).getInput(), hasSize(2));
    }
}