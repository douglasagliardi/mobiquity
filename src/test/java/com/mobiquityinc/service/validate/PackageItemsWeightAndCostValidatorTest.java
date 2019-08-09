package com.mobiquityinc.service.validate;

import com.mobiquityinc.dto.PackageInputRequest;
import com.mobiquityinc.model.Item;
import com.mobiquityinc.model.Package;
import com.mobiquityinc.model.PackageDecorator;
import com.mobiquityinc.model.PackagePrintDecorator;
import com.mobiquityinc.validate.PackageItemsWeightAndCostValidator;
import com.mobiquityinc.validate.PackageValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class PackageItemsWeightAndCostValidatorTest {

    private PackageInputRequest request;
    private PackageValidator packageWeightValidator = new PackageItemsWeightAndCostValidator();

    @BeforeEach
    void setup() {
        request = new PackageInputRequest();
    }

    @Test
    public void isValidatorAllowingCorrectItems() {
        PackageDecorator p1 = new PackagePrintDecorator(new Package(90));
        p1.addItemTo(new Item(1, 20.00F, 20));
        request.setBundle(p1);

        assertThat(packageWeightValidator.isValid(request), equalTo(true));
    }

    @Test
    public void isValidatorBlockingItemWithPriceHigherThanMaxAllowed() {
        PackageDecorator p1 = new PackagePrintDecorator(new Package(90));
        p1.addItemTo(new Item(1, 20.00F, 101));
        request.setBundle(p1);

        assertThat(packageWeightValidator.isValid(request), equalTo(false));
    }

    @Test
    public void isValidatorInvalidatingItemsWithPriceHigherThanMaxAllowed() {
        PackageDecorator p1 = new PackagePrintDecorator(new Package(90));
        p1.addItemTo(new Item(1, 20.00F, 20));
        p1.addItemTo(new Item(2, 20.00F, 105)); // invalid
        p1.addItemTo(new Item(3, 20.00F, 101)); // invalid
        p1.addItemTo(new Item(4, 20.00F, 30));
        request.setBundle(p1);

        assertThat(packageWeightValidator.isValid(request), equalTo(false));
    }

    @Test
    public void isValidatorBlockingItemWithWeightHigherThanMaxAllowed() {
        PackageDecorator p1 = new PackagePrintDecorator(new Package(90));
        p1.addItemTo(new Item(1, 100.01F, 10));
        request.setBundle(p1);

        assertThat(packageWeightValidator.isValid(request), equalTo(false));
    }

    @Test
    public void isValidatorInvalidatingItemsWithWeightHigherThanMaxAllowed() {
        PackageDecorator p1 = new PackagePrintDecorator(new Package(90));

        p1.addItemTo(new Item(1, 20.00F, 20));
        p1.addItemTo(new Item(2, 101.00F, 15)); // invalid
        p1.addItemTo(new Item(3, 100.30F, 10)); // invalid
        p1.addItemTo(new Item(4, 20.00F, 30));
        request.setBundle(p1);

        assertThat(packageWeightValidator.isValid(request), equalTo(false));
    }
}
