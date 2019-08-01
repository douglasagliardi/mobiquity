package com.mobiquityinc.helper;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class FileReader {

    public void read(String path) throws IOException {
        InputStream inputStream = null;
        try {
            File file = new File(ClassLoader.getResource("fileTest.txt").getFile());
            inputStream = new FileInputStream(file);

            //...
        }
        finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
