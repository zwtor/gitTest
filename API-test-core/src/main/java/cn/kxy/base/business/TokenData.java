package cn.kxy.base.business;

import cn.kxy.homepage.business.LoginBusiness;
import com.alibaba.fastjson.JSONPath;

public class TokenData {
	private static String token;

	private TokenData() {
	}

	public static String getMangerToken() {
		if(token == null || token.isEmpty()) {
			if(System.getProperty("token") == null || System.getProperty("token").isEmpty()) {
				String response = LoginBusiness.loginCoolColleague(System.getProperty("loginMobile"), System.getProperty("password"));
				token = String.valueOf(JSONPath.read(response, "$.action_token"));
			} else {
				token = System.getProperty("token");
			}
		}
		return token;
	}
}
