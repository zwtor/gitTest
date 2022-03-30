package com.lazy.common.utils;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ResourceFileUtil {
    public String readSingleFile(String folder, String fileName) {
        String wholeFilePath = folder + File.separator + fileName;
        ClassLoader classLoader = getClass().getClassLoader();

        StringBuilder stringBuilder = new StringBuilder();
        try (InputStream inputStream = classLoader.getResourceAsStream(wholeFilePath);
             InputStreamReader streamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
             BufferedReader reader = new BufferedReader(streamReader)) {

            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

    private List<File> readAllFiles(String folder) {
        ClassLoader classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource(folder);

        List<File> fileList = new ArrayList<>();
        try {
            // walk through all the classes
            fileList = Files.walk(Paths.get(resource.toURI()))
                    .filter(Files::isRegularFile)
                    .map(x -> x.toFile())
                    .collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fileList;
    }

    public JSONObject parseJsonFile(String folder, String fileName) {
        String fileContent = readSingleFile(folder, fileName);
        JSONObject jsonObject = JSONObject.parseObject(fileContent);
        return jsonObject;
    }

    public JSONObject parseAllJsonFiles(String folder) {
        List<File> fileList = readAllFiles(folder);
        JSONObject jsonObject = new JSONObject();
        for (File file : fileList) {
            StringBuilder stringBuilder = new StringBuilder();
            List<String> lines;
            try {
                lines = Files.readAllLines(file.toPath(), StandardCharsets.UTF_8);
                lines.forEach(stringBuilder::append);
                jsonObject.putAll(JSONObject.parseObject(stringBuilder.toString()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return jsonObject;
    }

    // parameter format: path1, value1, path2, value2...
    public static JSONObject setJsonBodyValue(JSONObject body, String... parameters) {
        if (parameters == null || (parameters.length % 2 != 0)) {
            System.err.println("The parameter format is wrong!It should be like: path1, value1, path2, value2...");
            return null;
        }

        for (int i = 0; i < parameters.length; i++) {
            JSONPath.set(body, parameters[i], parameters[++i]);
        }
        return body;
    }

    // key is path and value is the value to be set
    public static JSONObject setJsonBodyValue(JSONObject body, Map<String, String> valueMap) {
        if (valueMap == null) {
            System.err.println("The value map is null!");
            return null;
        }

        for (String key : valueMap.keySet()) {
            JSONPath.set(body, key, valueMap.get(key));
        }
        return body;
    }
}
