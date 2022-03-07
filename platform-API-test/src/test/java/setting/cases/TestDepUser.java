package setting.cases;

import cn.kxy.base.business.EnterpriseData;
import cn.kxy.base.business.EnterpriseDataUrl;
import cn.kxy.base.business.TokenData;
import cn.kxy.setting.bussiness.UserBusiness;
import com.alibaba.fastjson.JSONPath;
import com.jayway.jsonpath.JsonPath;
import com.lazy.httpclient.utils.HttpRequest;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class TestDepUser {

	
	List<String> list = null;
	String user_id = "";
	String mobile = "18866365241";
	String ic_card = "610521198601036321";
	@SuppressWarnings("unchecked")
	@Test(description = "根据关键字查看部门下的用户",priority = 1)
	public void queryDepUser() {
		String res = UserBusiness.queryDepUser("1", UserBusiness.getUsername(), "ding");
		String name = (String)JsonPath.read(res, "$.list[0].user_name");
		list = (List<String>)JsonPath.read(res, "$.list[0].extend_list");
		user_id = (String)JsonPath.read(res, "$.list[0].user_id");
		Long recent_login_time = (Long)JSONPath.read(res, "$.list[0].recent_login_time");
		Assert.assertNotNull(recent_login_time,"根据关键字查看部门下的用户"+res);
		Assert.assertNotNull(name,"根据关键字查看部门下的用户"+res);
	}
	
	@Test(description = "设置用户信息",priority = 2)
	public void testSetUserInfoByTwo() {
		if (list.size() == 2) {
			String res = UserBusiness.setUserInfoByTwo(user_id, ic_card, mobile);
			String msg = (String)JSONPath.read(res, "$.msg");
			Assert.assertEquals(msg,"ok","设置用户信息，实际返回结果："+res);
		}else if (list.size() == 3) {
			String res = UserBusiness.setUserInfoByThree(user_id,ic_card, mobile,"15123623632");
			String msg = (String)JSONPath.read(res, "$.msg");
			Assert.assertEquals(msg,"ok","设置用户信息，实际返回结果："+res);
		}else  {
			System.out.println("员工信息自定义字段未设置");
		}
	}
	@Test(description = "切换部门",priority = 3)
	public void choiceDep() {
		String res = HttpRequest.post(EnterpriseDataUrl.getEnterpriseUrl() + "v2/"+EnterpriseData.getEnterpriseId()+"/home_customize/choice").query("access_token",TokenData.getMangerToken()).
		data("{\"department_id\":\"1\",\"type\":\"pc\",\"access_token\":\""+TokenData.getMangerToken()+"\"}").send().body();
		Assert.assertTrue(res.contains("choice"));
	}
	
}
