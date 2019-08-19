package com.mobiquityinc.validate;

import com.mobiquityinc.dto.PackageInputRequest;
import com.mobiquityinc.model.Item;
import com.mobiquityinc.model.Package;
import com.mobiquityinc.model.PackageDecorator;
import com.mobiquityinc.model.PackagePrintDecorator;
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
        p1.addItemTo(new Item(1, 20.0, 20));
        request.setBundle(p1);

        assertThat(packageWeightValidator.isValid(request), equalTo(true));
    }

    @Test
    public void isValidatorBlockingItemWithPriceHigherThanMaxAllowed() {
        PackageDecorator p1 = new PackagePrintDecorator(new Package(90));
        p1.addItemTo(new Item(1, 20.0, 101));
        request.setBundle(p1);

        assertThat(packageWeightValidator.isValid(request), equalTo(false));
    }

    @Test
    public void isValidatorInvalidatingItemsWithPriceHigherThanMaxAllowed() {
        PackageDecorator p1 = new PackagePrintDecorator(new Package(90));
        p1.addItemTo(new Item(1, 20.0, 20));
        p1.addItemTo(new Item(2, 20.0, 105)); // invalid
        p1.addItemTo(new Item(3, 20.0, 101)); // invalid
        p1.addItemTo(new Item(4, 20.0, 30));
        request.setBundle(p1);

        assertThat(packageWeightValidator.isValid(request), equalTo(false));
    }

    @Test
    public void isValidatorBlockingItemWithWeightHigherThanMaxAllowed() {
        PackageDecorator p1 = new PackagePrintDecorator(new Package(90));
        p1.addItemTo(new Item(1, 100.01, 10));
        request.setBundle(p1);

        assertThat(packageWeightValidator.isValid(request), equalTo(false));
    }

    @Test
    public void isValidatorInvalidatingItemsWithWeightHigherThanMaxAllowed() {
        PackageDecorator p1 = new PackagePrintDecorator(new Package(90));

        p1.addItemTo(new Item(1, 20.0, 20));
        p1.addItemTo(new Item(2, 101.0, 15)); // invalid
        p1.addItemTo(new Item(3, 100.3, 10)); // invalid
        p1.addItemTo(new Item(4, 20.0, 30));
        request.setBundle(p1);

        assertThat(packageWeightValidator.isValid(request), equalTo(false));
    }

    @Test
    public void isAllowedCustomPackageMaxWeightAndCost() {
        PackageValidator customValidator = new PackageItemsWeightAndCostValidator(110, 105);
        PackageDecorator p1 = new PackagePrintDecorator(new Package(101));

        p1.addItemTo(new Item(1, 20.0, 20));
        p1.addItemTo(new Item(2, 101.0, 15));
        p1.addItemTo(new Item(3, 100.3, 10));
        p1.addItemTo(new Item(4, 20.0, 30));
        request.setBundle(p1);

        assertThat(customValidator.isValid(request), equalTo(true));
    }

    @Test
    public void isCustomPackageMaxWeightAndCostNotAllowingPackageGreaterThanIt() {
        PackageValidator customValidator = new PackageItemsWeightAndCostValidator(110, 105);
        PackageDecorator p1 = new PackagePrintDecorator(new Package(110));

        p1.addItemTo(new Item(1, 20.0, 20));
        p1.addItemTo(new Item(2, 111.0, 15)); // invalid
        p1.addItemTo(new Item(3, 100.3, 10));
        p1.addItemTo(new Item(4, 20.0, 30));
        request.setBundle(p1);

        assertThat(customValidator.isValid(request), equalTo(false));
    }
}
