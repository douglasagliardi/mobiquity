package com.mobiquityinc.service.helper;

import com.mobiquityinc.exception.APIException;
import com.mobiquityinc.helper.FileReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.not;

public class FileReaderTest {

    private FileReader fileReader = new FileReader();

    @Test
    public void canReadSampleFile() throws APIException {
        List<String> sPackages = fileReader.readFile("src/main/resources/input.txt");
        assertThat(sPackages, not(empty()));
    }

    @Test
    public void isAcceptingEmptyPath() throws APIException {
        Assertions.assertThrows(APIException.class, () ->
                fileReader.readFile("")
        );
    }

    @Test
    public void isAcceptingNullPath() throws APIException {
        Assertions.assertThrows(APIException.class, () ->
                fileReader.readFile(null)
        );
    }
}
