package com.lazy.assured.utils;

import cn.kxy.base.business.TokenData;
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
        response.then().statusCode(COMMON_SUCCESS_CODE);
        return response.asString();
    }

    public String sendGetRequest(String url, Map<String, Object> queryParameterMap) {
        RequestSpecification request = RestAssured.given();
        request.config((RestAssured.config().sslConfig(new SSLConfig().relaxedHTTPSValidation()))).headers(requestHeader).queryParams(queryParameterMap);
        if(isRequireToken) {
            request.queryParam("access_token", token);
        }

        Response response = request.get(url);
        response.then().statusCode(COMMON_SUCCESS_CODE);
        return response.asString();
    }

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
        response.then().statusCode(COMMON_SUCCESS_CODE);
        return response.asString();
    }

    public String sendPutRequest(String url, Map<Object, Object> requestBody, Map<String, Object> formParameterMap) {
        RequestSpecification request = RestAssured.given();
        request.config((RestAssured.config().sslConfig(new SSLConfig().relaxedHTTPSValidation()))).headers(requestHeader).formParams(formParameterMap).body(requestBody);
        if(isRequireToken) {
            request.queryParam("access_token", token);
        }

        Response response = (requestBody == null)? request.post(url) : request.body(requestBody).post(url);
        response.then().statusCode(COMMON_SUCCESS_CODE);
        return response.asString();
    }

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
        response.then().statusCode(COMMON_SUCCESS_CODE);
        return response.asString();
    }

    public String sendPostRequest(String url, Map<Object, Object> requestBody, Map<String, Object> formParameterMap) {
        RequestSpecification request = RestAssured.given();
        request.config((RestAssured.config().sslConfig(new SSLConfig().relaxedHTTPSValidation()))).headers(requestHeader).formParams(formParameterMap).body(requestBody);
        if(isRequireToken) {
            request.queryParam("access_token", token);
        }

        Response response = (requestBody == null)? request.post(url) : request.body(requestBody).post(url);
        response.then().statusCode(COMMON_SUCCESS_CODE);
        return response.asString();
    }

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
        response.then().statusCode(DELETE_SUCCESS_CODE);
        return response.asString();
    }

    public String sendDeleteRequest(String url, Map<String, Object> queryParameterMap) {
        RequestSpecification request = RestAssured.given();
        request.config((RestAssured.config().sslConfig(new SSLConfig().relaxedHTTPSValidation()))).headers(requestHeader).queryParams(queryParameterMap);
        if(isRequireToken) {
            request.queryParam("access_token", token);
        }

        Response response = request.delete(url);
        response.then().statusCode(DELETE_SUCCESS_CODE);
        return response.asString();
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
}

