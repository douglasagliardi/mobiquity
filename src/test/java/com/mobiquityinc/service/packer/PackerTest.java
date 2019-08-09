package com.mobiquityinc.service.packer;

import com.mobiquityinc.packer.Packer;
import org.junit.jupiter.api.Test;

public class PackerTest {

    @Test
    public void runFullIntegrationTestBasedOnUnitTests() {
        Packer.pack("src/main/resources/input.txt");
    }
}
