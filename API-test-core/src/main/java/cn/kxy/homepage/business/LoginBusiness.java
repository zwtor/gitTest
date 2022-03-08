package cn.kxy.homepage.business;

import cn.kxy.base.business.EnterpriseData;
import cn.kxy.base.business.EnterpriseDataUrl;
import com.lazy.assured.utils.RestAssuredRequestHandler;
import com.lazy.httpclient.utils.HttpRequest;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class LoginBusiness {
	public static String platform_url = EnterpriseDataUrl.getPlatformUrl();
	public static String platform_login_url = platform_url + "/v2/login";
	public static String platform_logout_url = platform_url + "/v2/logout";
	private final static String chooseEnterpriseURL = platform_url +"/chooseEnterprise";

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

	public static String loginCrm(String username,String password) {
		return HttpRequest.post(platform_login_url).form("username",username).form("password", password).send().body();
	}

	public static String loginCoolColleague(String loginMobile, String password) {
		Map requestBody = new LinkedHashMap<String, String>() {{
			put("login_mobile", loginMobile);
			put("password", password);
			put("login_type", "password");
		}};

		RestAssuredRequestHandler requestHandler = new RestAssuredRequestHandler(false);
		return requestHandler.sendPostRequest(platform_login_url, requestBody);
	}

	public static String chooseEnterprise() {
		RestAssuredRequestHandler requestHandler = new RestAssuredRequestHandler(true);
		Map<String, String> headerMap = new HashMap<String, String>() {{
			put("Content-Type", "application/x-www-form-urlencoded");
		}};
		requestHandler.buildHeader(headerMap);
		return requestHandler.sendPostRequest(chooseEnterpriseURL, null, "enterpriseId", EnterpriseData.getEnterpriseId(), "isMultiterminal", "true");
	}
	
}
