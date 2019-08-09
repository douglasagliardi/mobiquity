package com.mobiquityinc.service.helper;

import com.mobiquityinc.exception.APIException;
import com.mobiquityinc.helper.FileReader;
import org.junit.jupiter.api.Test;

import java.util.List;

public class FileReaderTest {

    private FileReader fileReader = new FileReader();

    @Test
    public void canReadFile() throws APIException {
        List<String> sPackages = fileReader.readFile("src/main/resources/input.txt");
    }
}
