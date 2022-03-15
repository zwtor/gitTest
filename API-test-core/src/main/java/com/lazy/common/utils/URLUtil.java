package com.lazy.common.utils;

import com.alibaba.fastjson.JSONObject;

public class URLUtil {
    private final static ResourceFileUtil resourceFileUtil = new ResourceFileUtil();
    private final static JSONObject examURLObject = resourceFileUtil.parseAllJsonFiles("url");

    // format URL like: /test/{section}/{id} with arguments: post, 2
    // output will be /test/post/2
    private static String formatURLParameter(String originalURL, String... args) {
        if (args == null) {
            System.err.println("The arguments format is wrong!It should be like: parameter1, value1, parameter2, value2...");
            return null;
        }

        String result = originalURL;
        if(args.length > 0) {
            String regex = "(\\{\\w+})";
            for(String arg : args) {
                result = result.replaceFirst(regex, arg);
            }
        }
        return result;
    }

    public static String buildURL(String key, String... args) {
        return formatURLParameter(examURLObject.getString(key), args);
    }
}
