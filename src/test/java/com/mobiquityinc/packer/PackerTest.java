package com.mobiquityinc.packer;

import org.junit.jupiter.api.Test;

public class PackerTest {

    @Test
    public void runFullIntegrationTestBasedOnUnitTests() {
        Packer.pack("src/main/resources/input.txt");
    }
}
