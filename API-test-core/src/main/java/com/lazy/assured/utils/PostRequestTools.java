package com.lazy.assured.utils;

import io.restassured.RestAssured;
import io.restassured.config.SSLConfig;

import static io.restassured.RestAssured.given;

public class PostRequestTools {

	public static String RequestFormDataByPost(String token, String A, Object O_one, String B, Object O_two,
			String url) {
		return given().config((RestAssured.config().sslConfig(new SSLConfig().relaxedHTTPSValidation())))
				.queryParam("access_token", token).formParams(A, O_one, B, O_two).post(url).asString();
	}

	public static String RequestFormDataByPost(String A, Object O_one, String B, Object O_two,
			String url) {
		return given().config((RestAssured.config().sslConfig(new SSLConfig().relaxedHTTPSValidation())))
				.formParams(A, O_one, B, O_two).post(url).asString();
	}

	
	public static String RequestFormDataByPost(String token, String A, Object O_one, String url) {
		return given().config((RestAssured.config().sslConfig(new SSLConfig().relaxedHTTPSValidation())))
				.queryParam("access_token", token).formParams(A, O_one).post(url).asString();
	}

	public static String RequestFormDataByPost(String token,String url) {
		return given().config((RestAssured.config().sslConfig(new SSLConfig().relaxedHTTPSValidation())))
				.queryParam("access_token", token ).post(url).asString();
	}
	
	public static String RequestFormDataByPost(String A, Object O_one, String B, Object O_two, String C,
			Object O_Tress, String url) {
		return given().config((RestAssured.config().sslConfig(new SSLConfig().relaxedHTTPSValidation())))
				.formParams(A, O_one, B, O_two, C, O_Tress).post(url).asString();
	}
	
	
	public static String RequestFormDataByPost(String token, String A, Object O_one, String B, Object O_two, String C,
			Object O_Tress, String url) {
		return given().config((RestAssured.config().sslConfig(new SSLConfig().relaxedHTTPSValidation())))
				.queryParam("access_token", token).formParams(A, O_one, B, O_two, C, O_Tress).post(url).asString();
	}

	public static String RequestFormDataByPost(String token, String A, Object O_one, String B, Object O_two, String C,
			Object O_Tress, String D, Object O_Four, String url) {
		return given().config((RestAssured.config().sslConfig(new SSLConfig().relaxedHTTPSValidation())))
				.queryParam("access_token", token).formParams(A, O_one, B, O_two, C, O_Tress, D, O_Four).post(url)
				.asString();
	}

	public static String RequestFormDataByPost(String token, String A, Object O_one, String B, Object O_two, String C,
			Object O_Tress, String D, Object O_Four, String E, Object O_Five, String url) {
		return given().config((RestAssured.config().sslConfig(new SSLConfig().relaxedHTTPSValidation())))
				.queryParam("access_token", token).formParams(A, O_one, B, O_two, C, O_Tress, D, O_Four, E, O_Five)
				.post(url).asString();
	}

	public static String RequestFormDataByPost(String token, String A, Object O_one, String B, Object O_two, String C,
			Object O_Tress, String D, Object O_Four, String E, Object O_Five, String F, Object O_Six, String url) {
		return given().config((RestAssured.config().sslConfig(new SSLConfig().relaxedHTTPSValidation())))
				.queryParam("access_token", token).formParams(A, O_one, B, O_two, C, O_Tress, D, O_Four, E, O_Five)
				.post(url).asString();
	}

	public static String RequestFormDataByPost(String token, String A, Object O_one, String B, Object O_two, String C,
			Object O_Tress, String D, Object O_Four, String E, Object O_Five, String F, Object O_Six, String G,
			Object O_Seven, String url) {
		return given().config((RestAssured.config().sslConfig(new SSLConfig().relaxedHTTPSValidation())))
				.queryParam("access_token", token)
				.formParams(A, O_one, B, O_two, C, O_Tress, D, O_Four, E, O_Five, G, O_Seven).post(url).asString();
	}

	public static String RequestFormDataByPost(String token, String A, Object O_one, String B, Object O_two, String C,
			Object O_Tress, String D, Object O_Four, String E, Object O_Five, String F, Object O_Six, String G,
			Object O_Seven, String H, Object O_eight, String url) {
		return given().config((RestAssured.config().sslConfig(new SSLConfig().relaxedHTTPSValidation())))
				.queryParam("access_token", token)
				.formParams(A, O_one, B, O_two, C, O_Tress, D, O_Four, E, O_Five, G, O_Seven, H, O_eight).post(url)
				.asString();
	}
	
	public static String RequestFormDataByPost(String token, String A, Object O_one, String B, Object O_two, String C,
			Object O_Tress, String D, Object O_Four, String E, Object O_Five, String F, Object O_Six, String G,
			Object O_Seven, String H, Object O_eight,String I,Object O_nine, String url) {
		return given().config((RestAssured.config().sslConfig(new SSLConfig().relaxedHTTPSValidation())))
				.queryParam("access_token", token)
				.formParams(A, O_one, B, O_two, C, O_Tress, D, O_Four, E, O_Five, G, O_Seven, H, O_eight,I,O_nine).post(url)
				.asString();
	}
	
	public static String RequestFormDataByPost(String token, String A, Object O_one, String B, Object O_two, String C,
			Object O_Tress, String D, Object O_Four, String E, Object O_Five, String F, Object O_Six, String G,
			Object O_Seven, String H, Object O_eight,String I,Object O_nine, String J,String O_ten,String url) {
		return given().config((RestAssured.config().sslConfig(new SSLConfig().relaxedHTTPSValidation())))
				.queryParam("access_token", token)
				.formParams(A, O_one, B, O_two, C, O_Tress, D, O_Four, E, O_Five, G, O_Seven, H, O_eight,I,O_nine,J,O_ten).post(url)
				.asString();
	}
	
	public static String RequestFormDataByPost(String token, String A, Object O_one, String B, Object O_two, String C,
			Object O_Tress, String D, Object O_Four, String E, Object O_Five, String F, Object O_Six, String G,
			Object O_Seven, String H, Object O_eight,String I,Object O_nine, String J,String O_ten,String k,String O_eleven,
			String L,String O_tevel,String M,String O_threteen,String N,String O_fourteen,String O,String O_fifteen,String url) {
		return given().config((RestAssured.config().sslConfig(new SSLConfig().relaxedHTTPSValidation())))
				.queryParam("access_token", token)
				.formParams(A, O_one, B, O_two, C, O_Tress, D, O_Four, E, O_Five, G, O_Seven, H, O_eight,I,O_nine,J,O_ten,
						k,O_eleven,L,O_tevel,M,O_threteen,N,O_fourteen,O,O_fifteen).post(url)
				.asString();
	}
	
	public static String RequestFormDataByPost(String token, String A, Object O_one, String B, Object O_two, String C,
			Object O_Tress, String D, Object O_Four, String E, Object O_Five, String F, Object O_Six, String G,
			Object O_Seven, String H, Object O_eight,String I,Object O_nine, String J,String O_ten,String k,String O_eleven,
			String L,String O_tevel,String M,String O_threteen,String N,String O_fourteen,String O,String O_fifteen,
			String p,String O_sixteen,String url) {
		return given().config((RestAssured.config().sslConfig(new SSLConfig().relaxedHTTPSValidation())))
				.queryParam("access_token", token)
				.formParams(A, O_one, B, O_two, C, O_Tress, D, O_Four, E, O_Five, G, O_Seven, H, O_eight,I,O_nine,J,O_ten,
						k,O_eleven,L,O_tevel,M,O_threteen,N,O_fourteen,O,O_fifteen,p,O_sixteen).post(url)
				.asString();
	}
	
	public static String RequestFormDataByPost(String token, String A, Object O_one, String B, Object O_two, String C,
			Object O_Tress, String D, Object O_Four, String E, Object O_Five, String F, Object O_Six, String G,
			Object O_Seven, String H, Object O_eight,String I,Object O_nine, String J,String O_ten,String k,String O_eleven,
			String L,String O_tevel,String M,String O_threteen,String N,String O_fourteen,String O,String O_fifteen,
			String p,String O_sixteen,String Q,String O_Seventeen,String url) {
		return given().config((RestAssured.config().sslConfig(new SSLConfig().relaxedHTTPSValidation())))
				.queryParam("access_token", token)
				.formParams(A, O_one, B, O_two, C, O_Tress, D, O_Four, E, O_Five, G, O_Seven, H, O_eight,I,O_nine,J,O_ten,
						k,O_eleven,L,O_tevel,M,O_threteen,N,O_fourteen,O,O_fifteen,p,O_sixteen,Q,O_Seventeen).post(url)
				.asString();
	}
	
	public static String RequestFormDataByPost(String token, String A, Object O_one, String B, Object O_two, String C,
			Object O_Tress, String D, Object O_Four, String E, Object O_Five, String F, Object O_Six, String G,
			Object O_Seven, String H, Object O_eight,String I,Object O_nine, String J,String O_ten,String k,String O_eleven,
			String L,String O_tevel,String M,String O_threteen,String N,String O_fourteen,String url) {
		return given().config((RestAssured.config().sslConfig(new SSLConfig().relaxedHTTPSValidation())))
				.queryParam("access_token", token)
				.formParams(A, O_one, B, O_two, C, O_Tress, D, O_Four, E, O_Five, G, O_Seven, H, O_eight,I,O_nine,J,O_ten,
						k,O_eleven,L,O_tevel,M,O_threteen,N,O_fourteen).post(url)
				.asString();
	}
	
	
	public static String RequestFormDataByPost(String token, String A, Object O_one, String B, Object O_two, String C,
			Object O_Tress, String D, Object O_Four, String E, Object O_Five, String F, Object O_Six, String G,
			Object O_Seven, String H, Object O_eight,String I,Object O_nine, String J,String O_ten,String k,String O_eleven,
			String L,String O_tevel,String url) {
		return given().config((RestAssured.config().sslConfig(new SSLConfig().relaxedHTTPSValidation())))
				.queryParam("access_token", token)
				.formParams(A, O_one, B, O_two, C, O_Tress, D, O_Four, E, O_Five, G, O_Seven, H, O_eight,I,O_nine,J,O_ten,
						k,O_eleven,L,O_tevel).post(url)
				.asString();
	}
	public static String RequestFormDataByPost(String token, String A, Object O_one, String B, Object O_two, String C,
			Object O_Tress, String D, Object O_Four, String E, Object O_Five, String F, Object O_Six, String G,
			Object O_Seven, String H, Object O_eight,String I,Object O_nine, String J,String O_ten,String k,String O_eleven,
			String url) {
		return given().config((RestAssured.config().sslConfig(new SSLConfig().relaxedHTTPSValidation())))
				.queryParam("access_token", token)
				.formParams(A, O_one, B, O_two, C, O_Tress, D, O_Four, E, O_Five, G, O_Seven, H, O_eight,I,O_nine,J,O_ten,
						k,O_eleven).post(url)
				.asString();
	}
	
	
	public static String RequestBodyByPost(String json ,String token,String url) {
		return given().config((RestAssured.config().sslConfig(new SSLConfig().relaxedHTTPSValidation())))
				.queryParam("access_token", token).body(json).post(url).asString();
		
	}
	
	public static String RequestBodyByPost(String json  ,String url) {
		return given().config((RestAssured.config().sslConfig(new SSLConfig().relaxedHTTPSValidation())))
				.body(json).post(url).asString();
		
	}
	
	public static String RequestBodyByDelete(String json,String token,String url) {
		return given().config((RestAssured.config().sslConfig(new SSLConfig().relaxedHTTPSValidation())))
				.queryParam("access_token", token).body(json).delete(url).asString();
	}
	
	
	public static String RequestBodyByPost(String json ,String token,String A,Object O_one,String url) {
		return given().config((RestAssured.config().sslConfig(new SSLConfig().relaxedHTTPSValidation())))
				.queryParam("access_token", token).queryParam(A, O_one).body(json).post(url).asString();
		
	}
	
	public static String RequestBodyByPost(String json ,String token,String A,Object O_one,String B,String O_two,String url) {
		return given().config((RestAssured.config().sslConfig(new SSLConfig().relaxedHTTPSValidation())))
				.queryParam("access_token", token).queryParam(A, O_one).queryParam(B, O_two).body(json).post(url).asString();
		
	}
	
	public static String RequestBodyByPost(String json ,String token,String A,Object O_one,String B,String O_two
			,String C,String C_Three,String url) {
		return given().config((RestAssured.config().sslConfig(new SSLConfig().relaxedHTTPSValidation())))
				.queryParam("access_token", token).queryParam(A, O_one).queryParam(B, O_two).queryParam(C,C_Three).body(json).post(url).asString();
		
	}
	
	public static String RequestQueryParamByPost(String A,Object O_one,String url) {
		return given().config((RestAssured.config().sslConfig(new SSLConfig().relaxedHTTPSValidation())))
				.queryParam(A, O_one).post(url).asString();
		
	}
}
