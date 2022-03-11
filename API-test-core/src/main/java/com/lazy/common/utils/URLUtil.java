package com.lazy.common.utils;

public class URLUtil {
    // format URL like: /test/{section}/{id} with arguments: post, 2
    // output will be /test/post/2
    public static String buildURL(String originalURL, String... args) {
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
}
