package cn.kxy.base.business;

import cn.kxy.homepage.business.LoginBusiness;
import com.alibaba.fastjson.JSONPath;

public class TokenData {
	private static String token;

	private TokenData() {
	}

	public static String getToken() {
		return token;
	}

	public static String getMangerToken() {
		if(token == null || token.equals("")) {
			if(System.getProperty("token") == null) {
				String response = LoginBusiness.loginCoolColleague(System.getProperty("loginMobile"), System.getProperty("password"));
				token = (String) JSONPath.read(response, "$.action_token");
			} else {
				token = System.getProperty("token");
			}
		}
		return token;
	}
}
