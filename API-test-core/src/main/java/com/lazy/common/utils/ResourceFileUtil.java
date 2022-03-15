package com.lazy.common.utils;

import com.alibaba.fastjson.JSONObject;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
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
        JSONObject jsonObject = new JSONObject();

        List<File> fileList = new ArrayList<>();
        try {
            // walk the root path, we will walk all the classes
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
            System.out.println("file : " + file);
            List<String> lines;
            try {
                lines = Files.readAllLines(file.toPath(), StandardCharsets.UTF_8);
                lines.forEach(stringBuilder::append);
                jsonObject.putAll(JSONObject.parseObject(stringBuilder.toString()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        System.out.println(jsonObject.toJSONString());
        return jsonObject;
    }
}
