package com.mobiquity.validate;

import com.mobiquityinc.dto.PackageInputRequest;
import com.mobiquityinc.exception.PackageOverWeightException;
import com.mobiquityinc.model.Item;
import com.mobiquityinc.model.Package;
import com.mobiquityinc.service.PackageValidator;
import com.mobiquityinc.service.PackageWeightValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class PackageWeightValidatorTest {

    @Test
    public void isValidatorNotAllowingPackageWithInvalidWeight() {
        PackageValidator packageWeightValidator = new PackageWeightValidator();
        PackageInputRequest request = new PackageInputRequest();
        Package p1 = new Package(101);
        p1.addItemTo(new Item(1, 20.00F, 30));
        request.setBundle(p1);

        Assertions.assertThrows(PackageOverWeightException.class, () ->
                packageWeightValidator.isValid(request));
    }

    @Test
    public void isValidatorAcceptingRegularRequest() {
        PackageValidator packageWeightValidator = new PackageWeightValidator();
        PackageInputRequest request = new PackageInputRequest();
        Package p1 = new Package(80);
        p1.addItemTo(new Item(1, 20.00F, 30));
        request.setBundle(p1);

        assertThat(packageWeightValidator.isValid(request), equalTo(true));
    }
}
