package crm.login.cases;

import cn.kxy.homepage.business.LoginBusiness;
import com.alibaba.fastjson.JSONPath;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestCrmLogin {

	public static String user_name = "allenlor";
	
	public static String password = "123456";
	
	String token = "";
	@Test(description = "登录至crm系统",priority = 1)
	public void testLoginCrm() {
		String res = LoginBusiness.loginCrm(user_name, password);
		token = (String)JSONPath.read(res, "$.data.action_token");
		String msg = (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "登录成功");
	}
	@Test(description = "退出crm系统",priority = 2)
	public void testLogoutCrm() {
		String res = LoginBusiness.logoutCrm(token);
		String msg = (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "ok");
	}
}
