package com.lazy.common.utils;

import com.alibaba.fastjson.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;

/**
 * 
 */
public class HTTPUtils {
    /** post 请求 */
    public static final String HTTP_POST = "POST";
    /** get 请求 */
    public static final String HTTP_GET = "GET";
    /** 字符编码 */
    public static final String CHARSET = "utf-8";
    /** http内容类型,如果未指定ContentType，默认为 application/json */
    public static final String CONTENT_TYPE = "application/json";
    /** 请求超时时间 */
    public static final int REQUEST_TIME_OUT = 50000;
    /** 将读超时时间 */
    public static final int READ_TIME_OUT = 50000;

    /**
     * 使用GET 方式获取数据
     * TODO url后面的参数作为单独的 key & value传参
     * @param urlString
     * @param charset
     * @return
     * @throws IOException
     */
    public static String sendGet(String urlString,String charset) {
        String result = "";
        BufferedReader in = null;
        try {
            URL url = new URL(urlString);
            /** 打开和URL之间的连接 */
            URLConnection connection = url.openConnection();
            /** 设置通用请求的属性 */
            connection.setRequestProperty("accept","*/*");
            connection.setRequestProperty("connection","Keep-Alive");
            connection.setRequestProperty("user-agent","Mozilla/5.0 (compatible; MSIE 6.0;Windows NT 5.1;SV1");

            /** 建立实际的连接 */
            connection.connect();
            /** 定义 BufferReader 输入流来读取URL的响应 */
            in =  new BufferedReader(new InputStreamReader(connection.getInputStream(),charset));
            String line;
            while ((line = in.readLine()) != null){
                result += line;
            }
        }catch (Exception e){
            System.out.println("发送GET请求出现异常!" + e);
            e.printStackTrace();
        }
        finally {
            try {
                if (in != null){
                    in.close();
                }
            }catch (Exception e2){
                e2.printStackTrace();
            }
        }
        return result;
    }

    /**
     * POST请求，字符形式数据
     * @param urlString
     * @return
     * @throws IOException
     */
    @SuppressWarnings("unchecked")
	public static JSONObject sendPOST(String urlString, @SuppressWarnings("rawtypes") Map map){
        String request = new JSONObject(map).toJSONString();
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL url = new URL(urlString);
            URLConnection connection = url.openConnection();

            connection.setRequestProperty("accept","*/*");
            connection.setRequestProperty("connection","Keep-Alive");
            connection.setRequestProperty("user-agent","Mozilla/5.0 (compatible; MSIE 6.0;Windows NT 5.1;SV1");

            connection.setDoOutput(true);
            connection.setDoInput(true);

            out = new PrintWriter(connection.getOutputStream());

            /** 发送请求参数 */
            out.print(request);
            /** flush 输入流的缓冲 */
            out.flush();

            in = new BufferedReader(new InputStreamReader(connection.getInputStream(),"utf-8"));
            String line;
            while ((line = in.readLine()) != null){
                result += line;
            }
        }catch (Exception e){
            System.out.println("发送POST请求出现异常!" + e);
            e.printStackTrace();
        }
        /** 使用finally块来关闭输出流、输入流 */
        finally {
            try {
                if (out != null){
                    out.close();
                }
                if (in != null){
                    in.close();
                }
            }catch (IOException ex){
                ex.printStackTrace();
            }
        }
        return JSONObject.parseObject(result);
    }

    /**
     * POST请求，Map形式数据
     * @param urlString
     * @param parameters
     * @param propertys
     * @return
     * @throws IOException
     */
    public static String sendPostUrl(String urlString,Map<String ,String > parameters,Map<String ,String > propertys) {
        PrintWriter out = null;
        BufferedReader in = null;
        StringBuffer buffer = new StringBuffer();
        String result = "";
        try {
            URL url = new URL(urlString);
            URLConnection connection = url.openConnection();
            if (propertys != null && !propertys.isEmpty()){
                for (String key : propertys.keySet()){
                    connection.addRequestProperty(key,propertys.get(key));
                }
            } else {
                connection.setRequestProperty("accept","*/*");
                connection.setRequestProperty("connection","Keep-Alive");
                connection.setRequestProperty("user-agent","Mozilla/5.0 (compatible; MSIE 6.0;Windows NT 5.1;SV1");
            }
            connection.setDoInput(true);
            connection.setDoOutput(true);
            if (parameters != null && !parameters.isEmpty()){
                StringBuffer parameter = new StringBuffer();
                for (String key : parameters.keySet()){
                    parameter.append("&");
                    parameter.append(key).append("=").append(parameters.get(key));
                }
            }
            out = new PrintWriter(connection.getOutputStream());
            out.print(buffer);
            out.flush();
            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null){
                result += line;
            }
        }catch (Exception e){
            System.out.println("发送POST请求出现异常" + e);
            e.printStackTrace();
        }
        finally {
            try {
                if (out != null){
                    out.close();
                }
                if (in != null){
                    in.close();
                }
            }catch (IOException ex){
                ex.printStackTrace();
            }
        }
        return result;
    }
}
