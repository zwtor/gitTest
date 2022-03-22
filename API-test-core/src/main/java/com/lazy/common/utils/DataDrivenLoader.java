package com.lazy.common.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

// This class is used to load test from json file, and provide result as data provider for test case
public class DataDrivenLoader {
    public static Object[][] loadTestData(String fileName, String methodName) {
        ResourceFileUtil resourceFileUtil = new ResourceFileUtil();
        JSONArray dataList = resourceFileUtil.parseJsonFile("testdata", fileName).getJSONArray(methodName);

        if(dataList.size() > 0) {
            int dataCount = dataList.size();
            int parameterCount = dataList.getJSONObject(0).size();

            Object[][] result = new Object[dataCount][parameterCount];
            for(int i = 0;i <dataList.size();i++) {
                int j = 0;
                JSONObject singleData = (JSONObject) dataList.get(i);
                for(String key : singleData.keySet()) {
                    result[i][j] = singleData.get(key);
                    j++;
                }
            }
            return result;
        } else {
            return null;
        }
    }
}
