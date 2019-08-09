package com.mobiquityinc.helper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.apache.commons.io.FileUtils.readLines;

public class FileReader {

    public List<String> readFile(String path) {
        List<String> result = new ArrayList<>();
        try {
            File f = new File(path);
            result = readLines(f, "UTF-8");

        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
