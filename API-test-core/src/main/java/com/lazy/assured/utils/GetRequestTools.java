package com.lazy.assured.utils;

import io.restassured.RestAssured;
import io.restassured.config.SSLConfig;

import static io.restassured.RestAssured.given;

public class GetRequestTools {

	public static String RequestParamsByGet(String A, Object O_one, String B, Object O_two, String url) {
		return given().config((RestAssured.config().sslConfig(new SSLConfig().relaxedHTTPSValidation())))
				.params(A, O_one, B, O_two).get(url).asString();
	}

	public static String RequestParamsByGet(String A, Object O_one, String url) {
		return given().config((RestAssured.config().sslConfig(new SSLConfig().relaxedHTTPSValidation())))
				.params(A, O_one).get(url).asString();
	}

	public static String RequestParamsByGet(String A, Object O_one, String B, Object O_two, String C, Object O_Tress,
			String url) {
		return given().config((RestAssured.config().sslConfig(new SSLConfig().relaxedHTTPSValidation())))
				.params(A, O_one, B, O_two, C, O_Tress).get(url).asString();
	}

	public static String RequestParamsByGet(String A, Object O_one, String B, Object O_two, String C, Object O_Tress,
			String D, Object O_Four, String url) {
		return given().config((RestAssured.config().sslConfig(new SSLConfig().relaxedHTTPSValidation())))
				.params(A, O_one, B, O_two, C, O_Tress, D, O_Four).get(url).asString();
	}

	public static String RequestParamsByGet(String A, Object O_one, String B, Object O_two, String C, Object O_Tress,
			String D, Object O_Four, String E, Object O_Five, String url) {
		return given().config((RestAssured.config().sslConfig(new SSLConfig().relaxedHTTPSValidation())))
				.params(A, O_one, B, O_two, C, O_Tress, D, O_Four, E, O_Five).get(url).asString();
	}
	
	public static String RequestParamsByGet(String A, Object O_one, String B, Object O_two, String C, Object O_Tress,
			String D, Object O_Four, String E, Object O_Five,String F, Object O_Six, String url) {
		return given().config((RestAssured.config().sslConfig(new SSLConfig().relaxedHTTPSValidation())))
				.params(A, O_one, B, O_two, C, O_Tress, D, O_Four, E, O_Five).get(url).asString();
	}

	public static String RequestParamsByGet(String A, Object O_one, String B, Object O_two, String C, Object O_Tress,
			String D, Object O_Four, String E, Object O_Five,String F, Object O_Six, String G, Object O_Seven,String url) {
		return given().config((RestAssured.config().sslConfig(new SSLConfig().relaxedHTTPSValidation())))
				.params(A, O_one, B, O_two, C, O_Tress, D, O_Four, E, O_Five,G,O_Seven).get(url).asString();
	}
	
	public static String RequestParamsByGet(String A, Object O_one, String B, Object O_two, String C, Object O_Tress,
			String D, Object O_Four, String E, Object O_Five,String F, Object O_Six, String G, Object O_Seven,
			String H ,Object O_eight ,String url) {
		return given().config((RestAssured.config().sslConfig(new SSLConfig().relaxedHTTPSValidation())))
				.params(A, O_one, B, O_two, C, O_Tress, D, O_Four, E, O_Five,G,O_Seven,H,O_eight).get(url).asString();
	}
	
	public static String RequestParamsByGet(String A, Object O_one, String B, Object O_two, String C, Object O_Tress,
			String D, Object O_Four, String E, Object O_Five,String F, Object O_Six, String G, Object O_Seven,
			String H ,Object O_eight ,String I,String O_Nine,String url) {
		return given().config((RestAssured.config().sslConfig(new SSLConfig().relaxedHTTPSValidation())))
				.params(A, O_one, B, O_two, C, O_Tress, D, O_Four, E, O_Five,G,O_Seven,H,O_eight,I,O_Nine).get(url).asString();
	}
	
	//------------------------------------------------------------------------------------------------------------
	
	public static String RequestQueryParamsByGet(String A, Object O_one, String B, Object O_two, String url) {
		return given().config((RestAssured.config().sslConfig(new SSLConfig().relaxedHTTPSValidation())))
				.queryParams(A, O_one, B, O_two).get(url).asString();
	}
	
	public static String RequestQueryParamsByGet(String url) {
		return given().config((RestAssured.config().sslConfig(new SSLConfig().relaxedHTTPSValidation())))
				.get(url).asString();
	}

	public static String RequestQueryParamsByGet(String A, Object O_one, String url) {
		return given().config((RestAssured.config().sslConfig(new SSLConfig().relaxedHTTPSValidation())))
				.queryParams(A, O_one).get(url).asString();
	}

	public static String RequestQueryParamsByGet(String A, Object O_one, String B, Object O_two, String C, Object O_Tress,
			String url) {
		return given().config((RestAssured.config().sslConfig(new SSLConfig().relaxedHTTPSValidation())))
				.queryParams(A, O_one, B, O_two, C, O_Tress).get(url).asString();
	}

	public static String RequestQueryParamsByGet(String A, Object O_one, String B, Object O_two, String C, Object O_Tress,
			String D, Object O_Four, String url) {
		return given().config((RestAssured.config().sslConfig(new SSLConfig().relaxedHTTPSValidation())))
				.queryParams(A, O_one, B, O_two, C, O_Tress, D, O_Four).get(url).asString();
	}

	public static String RequestQueryParamsByGet(String A, Object O_one, String B, Object O_two, String C, Object O_Tress,
			String D, Object O_Four, String E, Object O_Five, String url) {
		return given().config((RestAssured.config().sslConfig(new SSLConfig().relaxedHTTPSValidation())))
				.queryParams(A, O_one, B, O_two, C, O_Tress, D, O_Four, E, O_Five).get(url).asString();
	}
	
	public static String RequestQueryParamsByGet(String A, Object O_one, String B, Object O_two, String C, Object O_Tress,
			String D, Object O_Four, String E, Object O_Five,String F, Object O_Six, String url) {
		return given().config((RestAssured.config().sslConfig(new SSLConfig().relaxedHTTPSValidation())))
				.queryParams(A, O_one, B, O_two, C, O_Tress, D, O_Four, E, O_Five).get(url).asString();
	}

	public static String RequestQueryParamsByGet(String A, Object O_one, String B, Object O_two, String C, Object O_Tress,
			String D, Object O_Four, String E, Object O_Five,String F, Object O_Six, String G, Object O_Seven,String url) {
		return given().config((RestAssured.config().sslConfig(new SSLConfig().relaxedHTTPSValidation())))
				.queryParams(A, O_one, B, O_two, C, O_Tress, D, O_Four, E, O_Five,G,O_Seven).get(url).asString();
	}
	
	public static String RequestQueryParamsByGet(String A, Object O_one, String B, Object O_two, String C, Object O_Tress,
			String D, Object O_Four, String E, Object O_Five,String F, Object O_Six, String G, Object O_Seven,
			String H ,Object O_eight ,String url) {
		return given().config((RestAssured.config().sslConfig(new SSLConfig().relaxedHTTPSValidation())))
				.queryParams(A, O_one, B, O_two, C, O_Tress, D, O_Four, E, O_Five,G,O_Seven,H,O_eight).get(url).asString();
	}
	
	public static String RequestQueryParamsByGet(String A, Object O_one, String B, Object O_two, String C, Object O_Tress,
			String D, Object O_Four, String E, Object O_Five,String F, Object O_Six, String G, Object O_Seven,
			String H ,Object O_eight ,String I,String O_nine,String url) {
		return given().config((RestAssured.config().sslConfig(new SSLConfig().relaxedHTTPSValidation())))
				.queryParams(A, O_one, B, O_two, C, O_Tress, D, O_Four, E, O_Five,G,O_Seven,H,O_eight,I,O_nine).get(url).asString();
	}
	public static String RequestQueryParamsByGet(String A, Object O_one, String B, Object O_two, String C, Object O_Tress,
			String D, Object O_Four, String E, Object O_Five,String F, Object O_Six, String G, Object O_Seven,
			String H ,Object O_eight ,String I,String O_nine,String J,String O_ten,String url) {
		return given().config((RestAssured.config().sslConfig(new SSLConfig().relaxedHTTPSValidation())))
				.queryParams(A, O_one, B, O_two, C, O_Tress, D, O_Four, E, O_Five,G,O_Seven,H,O_eight,I,O_nine,J,O_ten).get(url).asString();
	}
	public static String RequestQueryParamsByGet(String A, Object O_one, String B, Object O_two, String C, Object O_Tress,
			String D, Object O_Four, String E, Object O_Five,String F, Object O_Six, String G, Object O_Seven,
			String H ,Object O_eight ,String I,String O_nine,String J,String O_ten,String K,String O_eleven,String url) {
		return given().config((RestAssured.config().sslConfig(new SSLConfig().relaxedHTTPSValidation())))
				.queryParams(A, O_one, B, O_two, C, O_Tress, D, O_Four, E, O_Five,G,O_Seven,H,O_eight,I,O_nine,J,O_ten,K,O_eleven).get(url).asString();
	}
	
}
