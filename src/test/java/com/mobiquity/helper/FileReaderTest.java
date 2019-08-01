package com.mobiquity.helper;

import com.mobiquityinc.helper.FileReader;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class FileReaderTest {

    private FileReader fileReader = new FileReader();

    @Test
    public void canReadFile() throws IOException {
        fileReader.read("src/test/resources/input.txt");
    }
}
