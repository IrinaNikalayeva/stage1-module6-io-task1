package com.epam.mjc.io;

import java.io.*;
import java.util.HashMap;
import java.util.Map;


public class FileReader {

    public Profile getDataFromFile(File file) {
        Map<String, String> finalMap = readData(file);
        return new Profile(finalMap.get("Name"), Integer.parseInt(finalMap.get("Age")), finalMap.get("Email"),
                           Long.parseLong(finalMap.get("Phone")));
    }

    public Map<String, String> readData(File file) {
        Map<String, String> resultMap = null;
        try (java.io.FileReader fileReader = new java.io.FileReader(file)) {
            resultMap = new HashMap<>();
            int ch;
            StringBuilder result = new StringBuilder();
            while ((ch = fileReader.read()) != -1) {
                result.append((char) ch);
            }
            String[] keyValuePairs = result.toString().split("\n");
            for (String pair : keyValuePairs) {
                String[] keyValue = pair.split(":");
                if (keyValue.length == 2) {
                    String key = keyValue[0];
                    String value = keyValue[1].trim();
                    resultMap.put(key, value);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resultMap;
    }
}
