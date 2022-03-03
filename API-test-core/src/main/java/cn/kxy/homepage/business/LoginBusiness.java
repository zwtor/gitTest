package cn.kxy.homepage.business;

import cn.kxy.base.business.EnterpriseDataUrl;
import com.lazy.assured.utils.RestAssuredRequestHandler;
import com.lazy.httpclient.utils.HttpRequest;
import io.restassured.RestAssured;
import io.restassured.config.SSLConfig;
import io.restassured.response.Response;

import java.util.LinkedHashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class LoginBusiness {
	public static String platform_url = EnterpriseDataUrl.getPlatformUrl();
	public static String platform_login_url = platform_url + "/v2/login";
	public static String platform_logout_url = platform_url + "/v2/logout";
	
	/**   
	 * @Title: logoutCrm   
	 * @Description: TODO  退出crm系统
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */  
	public static String logoutCrm(String access_token) {
		return HttpRequest.get(platform_logout_url).query("access_token", access_token).send().body();
	}
	
	/**   
	 * @Title: loginCrm   
	 * @Description: TODO  登录到crm系统
	 * @param: @param username
	 * @param: @param password
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */  
	public static String loginCrm(String access_token) {
		return HttpRequest.get(platform_login_url).query("access_token", access_token).send().body();
	}

	public static String loginCoolColleague(String loginMobile, String password) {
		Map requestBody = new LinkedHashMap<String, String>() {{
			put("login_mobile", loginMobile);
			put("password", password);
			put("login_type", "password");
		}};
//		Response response = RestAssured.given().config((RestAssured.config().sslConfig(new SSLConfig().relaxedHTTPSValidation()))).body(requestBody).post(platform_login_url);
		RestAssuredRequestHandler requestHandler = new RestAssuredRequestHandler(false);
		return requestHandler.sendPostRequest(platform_login_url, requestBody);
	}
	
}
