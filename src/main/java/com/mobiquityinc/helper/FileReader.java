package com.mobiquityinc.helper;

import com.mobiquityinc.exception.APIException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.apache.commons.io.FileUtils.readLines;
import static org.apache.commons.lang3.StringUtils.isBlank;

public class FileReader {

    public List<String> readFile(String path) {
        List<String> result = new ArrayList<>();
        if (isBlank(path)) {
           throw new APIException("Invalid path : " + path);
        }
        try {
            File f = new File(path);
            result = readLines(f, "UTF-8");

        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
