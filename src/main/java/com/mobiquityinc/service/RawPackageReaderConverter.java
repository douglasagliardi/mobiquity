package com.mobiquityinc.service;

import com.mobiquityinc.dto.RawPackage;
import com.mobiquityinc.exception.APIException;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RawPackageReaderConverter {

    public List<RawPackage> producePackage(List<String> fileContent) {
        List<RawPackage> rawPackages = new ArrayList<>();
        RawPackage rPackage;
        for (String line : fileContent) {
            Matcher mPackageWeight = Pattern.compile("(^\\d{1,3})").matcher(line);

            rPackage = new RawPackage();
            while (mPackageWeight.find()) {
                rPackage.setMaxWeight(mPackageWeight.group(0));
            }

            Matcher mEntries = Pattern.compile("\\((.*?)\\)").matcher(line);
            while (mEntries.find()) {

                StringTokenizer entriesTokenizer = new StringTokenizer(mEntries.group(1), ",");
                if (entriesTokenizer.countTokens() < 3) {
                    throw new APIException("Invalid input for this entry...");
                }

                rPackage.getItems().add(mEntries.group(1));
            }
            rawPackages.add(rPackage);
        }
        return rawPackages;
    }
}
