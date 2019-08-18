package com.mobiquityinc.validate;

import com.mobiquityinc.dto.PackageInputRequest;
import com.mobiquityinc.exception.PackageOverWeightException;
import com.mobiquityinc.model.Item;
import com.mobiquityinc.model.Package;
import com.mobiquityinc.model.PackageDecorator;
import com.mobiquityinc.model.PackagePrintDecorator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class PackageItemsValidatorTest {

    private PackageInputRequest request;
    private PackageValidator packageWeightValidator;

    @BeforeEach
    void setup() {
        packageWeightValidator = new PackageWeightValidator();
        PackageValidator packageItemsWeightAndCostValidator = new PackageItemsWeightAndCostValidator();
        packageWeightValidator.linkToNext(packageItemsWeightAndCostValidator);
        request = new PackageInputRequest();
    }

    @Test
    public void isValidationChainWorkingProperly() {
        PackageDecorator p1 = new PackagePrintDecorator(new Package(90));
        p1.addItemTo(new Item(1, 20.00F, 20));
        p1.addItemTo(new Item(2, 40.00F, 35));
        p1.addItemTo(new Item(3, 25.00F, 80));
        request.setBundle(p1);

        assertThat(packageWeightValidator.isValid(request), equalTo(true));
    }


    @Test
    public void isChainOfValidationWorkingWithPackageWithMoreWeightThanAllowed() {
        PackageInputRequest request = new PackageInputRequest();
        PackageDecorator p1 = new PackagePrintDecorator(new Package(101));
        p1.addItemTo(new Item(1, 20.00F, 30));
        p1.addItemTo(new Item(2, 80.00F, 40));
        request.setBundle(p1);

        Assertions.assertThrows(PackageOverWeightException.class, () ->
                packageWeightValidator.isValid(request));
    }

    @Test
    public void isChainOfValidationWorkingWithItemsWithMoreWeightThanAllowed() {
        PackageInputRequest request = new PackageInputRequest();
        PackageDecorator p1 = new PackagePrintDecorator(new Package(100));
        p1.addItemTo(new Item(1, 20.00F, 30));
        p1.addItemTo(new Item(2, 110.00F, 40)); // validation fails because there is an item with more weight than it should
        request.setBundle(p1);

        assertFalse(packageWeightValidator.isValid(request));
    }
}
