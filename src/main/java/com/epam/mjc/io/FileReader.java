package com.epam.mjc.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;


public class FileReader {

    public Profile getDataFromFile(File file) {
        Map<String, String> finalMap = readData(file);
        return new Profile(finalMap.get("Name"), Integer.parseInt(finalMap.get("Age")), finalMap.get("Email"),
                           Long.parseLong(finalMap.get("Phone")));
    }

    public Map<String, String> readData(File file) {
        try  {
            Map<String, String> resultMap = new HashMap<>();
            String result = Files.readString(file.toPath());
            String[] keyValuePairs = result.split("\n");
            for (String pair : keyValuePairs) {
                String[] keyValue = pair.split(":");
                if (keyValue.length == 2) {
                    String key = keyValue[0];
                    String value = keyValue[1].trim();
                    resultMap.put(key, value);
                }
            }
            System.out.println(resultMap);
            return resultMap;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
