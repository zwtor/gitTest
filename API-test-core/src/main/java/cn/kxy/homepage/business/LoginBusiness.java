package cn.kxy.homepage.business;

import cn.kxy.base.business.EnterpriseDataUrl;
import com.lazy.httpclient.utils.HttpRequest;

public class LoginBusiness {
	
	public static String platform_url = EnterpriseDataUrl.getPlatformUrl();
	
	public static String platform_login_url = platform_url + "login";
	
	public static String platform_logout_url = platform_url + "logout";
	
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
	public static String loginCrm(String username,String password) {
		return HttpRequest.post(platform_login_url).form("username",username).form("password", password).send().body();
	}
	
	
}
