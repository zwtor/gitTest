package com.lazy.assured.utils;

import cn.kxy.base.business.EnvironmentData;
import cn.kxy.base.business.TokenData;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import io.restassured.RestAssured;
import io.restassured.config.SSLConfig;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class RestAssuredRequestHandler {
    private Map<String, String> requestHeader;
    private boolean isRequireToken;
    private String token;

    private final int COMMON_SUCCESS_CODE = 200;
    private final int DELETE_SUCCESS_CODE = 204;

    //By default token is required
    public RestAssuredRequestHandler() {
        token = TokenData.getMangerToken();
        isRequireToken = true;
        requestHeader = new HashMap<String, String>() {{
            put("Content-Type", "application/json");
            put("access_token", token);
        }};
    }

    //if not get token firstly, use this constructor to login and get token
    public RestAssuredRequestHandler(boolean isRequireToken) {
        requestHeader = new HashMap<String, String>() {{
            put("Content-Type", "application/json");
        }};
        this.isRequireToken = isRequireToken;
        // only get token when token is required
        if(isRequireToken) {
            token = TokenData.getMangerToken();
            requestHeader.put("access_token", token);
        }
    }

    // customize header
    public Map<String, String> buildHeader(Map<String, String> newHeader) {
        if (newHeader != null) {
            requestHeader.clear();
            requestHeader.put("access_token", token);
            requestHeader.putAll(newHeader);
        }
        return requestHeader;
    }

    // parameter format: parameter1, value1, parameter2, value2...
    public String sendGetRequest(String url, String... queryParameters) {
        RequestSpecification request = RestAssured.given();
        request.config((RestAssured.config().sslConfig(new SSLConfig().relaxedHTTPSValidation()))).headers(requestHeader);
        if(isRequireToken) {
            request.queryParam("access_token", token);
        }
        if(queryParameters != null && queryParameters.length > 0) {
            Map<String, Object> parameterMap = formatParameters(queryParameters);
            request.queryParams(parameterMap);
        }

        Response response = request.get(url);
        verifyStatusCode(response, COMMON_SUCCESS_CODE);
        return response.body().asString();
    }

    public String sendGetRequest(String url, Map<String, Object> queryParameterMap) {
        RequestSpecification request = RestAssured.given();
        request.config((RestAssured.config().sslConfig(new SSLConfig().relaxedHTTPSValidation()))).headers(requestHeader).queryParams(queryParameterMap);
        if(isRequireToken) {
            request.queryParam("access_token", token);
        }

        Response response = request.get(url);
        verifyStatusCode(response, COMMON_SUCCESS_CODE);
        return response.body().asString();
    }

    // parameter format: parameter1, value1, parameter2, value2...
    public String sendPutRequest(String url, Map<String, Object> requestBody, String... formParameters) {
        RequestSpecification request = RestAssured.given();
        request.config((RestAssured.config().sslConfig(new SSLConfig().relaxedHTTPSValidation()))).headers(requestHeader);
        if(isRequireToken) {
            request.queryParam("access_token", token);
        }
        if(formParameters != null && formParameters.length > 0) {
            Map<String, Object> parameterMap = formatParameters(formParameters);
            request.formParams(parameterMap);
        }

        Response response = (requestBody == null)? request.post(url) : request.body(requestBody).post(url);
        verifyStatusCode(response, COMMON_SUCCESS_CODE);
        return response.body().asString();
    }

    public String sendPutRequest(String url, Map<Object, Object> requestBody, Map<String, String> queryParameterMap, Map<String, Object> formParameterMap) {
        RequestSpecification request = RestAssured.given();
        request.config((RestAssured.config().sslConfig(new SSLConfig().relaxedHTTPSValidation()))).headers(requestHeader);
        if(isRequireToken) {
            request.queryParam("access_token", token);
        }
        if(queryParameterMap != null && queryParameterMap.size() > 0) {
            request.queryParams(queryParameterMap);
        }
        if(formParameterMap != null && formParameterMap.size() > 0) {
            request.formParams(formParameterMap);
        }

        Response response = (requestBody == null)? request.post(url) : request.body(requestBody).post(url);
        verifyStatusCode(response, COMMON_SUCCESS_CODE);
        return response.body().asString();
    }

    // parameter format: parameter1, value1, parameter2, value2...
    public String sendPostRequest(String url, Map<String, Object> requestBody, String... formParameters) {
        RequestSpecification request = RestAssured.given();
        request.config((RestAssured.config().sslConfig(new SSLConfig().relaxedHTTPSValidation()))).headers(requestHeader);
        if(isRequireToken) {
            request.queryParam("access_token", token);
        }
        if(formParameters != null && formParameters.length > 0) {
            Map<String, Object> parameterMap = formatParameters(formParameters);
            request.formParams(parameterMap);
        }

        Response response = (requestBody == null)? request.post(url) : request.body(requestBody).post(url);
        verifyStatusCode(response, COMMON_SUCCESS_CODE);
        return response.body().asString();
    }

    public String sendPostRequest(String url, Map<Object, Object> requestBody, Map<String, String> queryParameterMap, Map<String, Object> formParameterMap) {
        RequestSpecification request = RestAssured.given();
        request.config((RestAssured.config().sslConfig(new SSLConfig().relaxedHTTPSValidation()))).headers(requestHeader);

        if(isRequireToken) {
            request.queryParam("access_token", token);
        }
        if(queryParameterMap != null && queryParameterMap.size() > 0) {
            request.queryParams(queryParameterMap);
        }
        if(formParameterMap != null && formParameterMap.size() > 0) {
            request.formParams(formParameterMap);
        }

        Response response = (requestBody == null)? request.post(url) : request.body(requestBody).post(url);
        verifyStatusCode(response, COMMON_SUCCESS_CODE);
        return response.body().asString();
    }

    // parameter format: parameter1, value1, parameter2, value2...
    public String sendDeleteRequest(String url, String... queryParameters) {
        RequestSpecification request = RestAssured.given();
        request.config((RestAssured.config().sslConfig(new SSLConfig().relaxedHTTPSValidation()))).headers(requestHeader);
        if(isRequireToken) {
            request.queryParam("access_token", token);
        }
        if(queryParameters != null && queryParameters.length > 0) {
            Map<String, Object> parameterMap = formatParameters(queryParameters);
            request.queryParams(parameterMap);
        }

        Response response = request.delete(url);
        verifyStatusCode(response, DELETE_SUCCESS_CODE);
        return response.body().asString();
    }

    public String sendDeleteRequest(String url, Map<String, Object> queryParameterMap) {
        RequestSpecification request = RestAssured.given();
        request.config((RestAssured.config().sslConfig(new SSLConfig().relaxedHTTPSValidation()))).headers(requestHeader).queryParams(queryParameterMap);
        if(isRequireToken) {
            request.queryParam("access_token", token);
        }

        Response response = request.delete(url);
        verifyStatusCode(response, DELETE_SUCCESS_CODE);
        return response.body().asString();
    }

    private Map<String, Object> formatParameters(String... parameters) {
        if (parameters == null || (parameters.length % 2 != 0)) {
            System.err.println("The parameter format is wrong!It should be like: parameter1, value1, parameter2, value2...");
            return null;
        }

        Map<String, Object> parameterMap = new LinkedHashMap<>();
        for (int i = 0; i < parameters.length; i++) {
            parameterMap.put(parameters[i], parameters[++i]);
        }
        return parameterMap;
    }

    private void verifyStatusCode(Response response, Integer successCode) {
        try {
            response.then().statusCode(successCode);
        } catch (java.lang.AssertionError error) {
            response.body().prettyPrint();
            throw error;
        }
    }

    // provide a new empty request to business
    public RequestSpecification getNewRequest() {
        RequestSpecification request = RestAssured.given();
        request.config((RestAssured.config().sslConfig(new SSLConfig().relaxedHTTPSValidation()))).headers(requestHeader);
        return request;
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

    // format URL like: /test/{section}/{id} with arguments: post, 2
    // output will be: /test/post/2
    public static String buildURL(String baseURL, String... args) {
        baseURL = EnvironmentData.getHost() + baseURL;

        if (args == null) {
            System.err.println("The arguments format is wrong!It should be like: parameter1, value1, parameter2, value2...");
            return null;
        }

        String result = baseURL;
        if(args.length > 0) {
            String regex = "(\\{+(\\w|-|_)+}+)";
            for(String arg : args) {
                result = result.replaceFirst(regex, arg);
            }
        }
        return result;
    }

    public int getCOMMON_SUCCESS_CODE() {
        return COMMON_SUCCESS_CODE;
    }
}

