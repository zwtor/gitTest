package homepage.cases;

import cn.kxy.base.business.EnterpriseData;
import cn.kxy.base.business.EnterpriseDataUrl;
import cn.kxy.base.business.TokenData;
import cn.kxy.homepage.business.HomePageBusiness;
import cn.kxy.setting.bussiness.UserBusiness;
import com.lazy.httpclient.utils.HttpRequest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestEnterpriseHomepage {

	@Test(description = "查看企业端首页",priority = 1)
	public void queryDataPic() {
		String res = HomePageBusiness.queryDataPic();
		Assert.assertTrue(res.contains("duration"),"查看企业端首页,个人数据统计"+res);
	}
	
	@Test(description = "查看首页签到",priority = 2)
	public void queryContinuitySign() {
		String res = HomePageBusiness.queryContinuitySign();
		Assert.assertTrue(res.contains("msg"),"查看首页签到，实际返回结果："+res);
	}
	@Test(description = "查看钉钉直播名单",priority = 3)
	public void queryBlackwhite() {
		String res = HomePageBusiness.queryBlackwhite();
		Assert.assertTrue(res.contains("message"),"查看钉钉直播名单，实际返回结果："+res);
	}
	@Test(description = "查看岗位测评版本",priority = 4)
	public void judgeEvaluationIsVip() {
		String res = HomePageBusiness.judgeEvaluationIsVip();
		Assert.assertTrue(res.contains("enterprise_version"),"查看岗位测评版本，实际返回结果："+res);
	}
	
	@Test(description = "判断菜单是否存在",priority = 5)
	public void queryMenusExist() {
		String res = HomePageBusiness.queryMenusExist("");
		Assert.assertTrue(res.contains("message"),"判断菜单是否存在，实际返回结果："+res);
	}
	
	@Test(description = "关闭签到弹窗",priority = 6)
	public void closeSign() {
		String res = HomePageBusiness.closeSign();
		Assert.assertTrue(res.contains("msg"),"关闭签到弹窗，实际返回结果："+res);
	}
	
	@Test(description = "查看一周签到",priority = 7)
	public void querySignWeek() {
		String res = HomePageBusiness.querySignWeek();
		Assert.assertTrue(res.contains("data"),"查看一周签到，实际返回结果："+res);
	}
	@Test(description = "获取用户卡片信息",priority =8)
	public void getUserCard() {
		String res = HttpRequest.get(EnterpriseDataUrl.getEnterpriseUrl() + "v1/user/card/getUserCard").
				query("access_token",TokenData.getMangerToken()).query("app_id","cool").query("enterprise_id",EnterpriseData.getEnterpriseId())
				.query("user_id",UserBusiness.getUserId()).send().body();
		Assert.assertTrue(res.contains("data"),"获取用户卡片信息，实际返回结果："+res);
	}
	
	@Test(description = "获取用户token信息",priority = 9)
	public void getUserToken() {
		String res = HttpRequest.get(EnterpriseDataUrl.getEnterpriseUrl() + "v2/get_user_info_by_token").
				query("access_token",TokenData.getMangerToken())
				.send().body();
		Assert.assertTrue(res.contains("name"),"获取用户token信息，实际返回结果："+res);
	}
	
	@Test(description = "查看健康等级",priority = 10)
	public void queryHealthLevel() {
		String res = HttpRequest.get(EnterpriseDataUrl.getEnterpriseUrl() + "dashboard/enterprise/"+EnterpriseData.getEnterpriseId()+"/health/level").
				query("access_token",TokenData.getMangerToken())
				.send().body();
		Assert.assertTrue(res.contains("success"),"查看健康等级，实际返回结果："+res);
		
	}
	
	@Test(description = "useridcardcolumn接口验证",priority =11)
	public void useridcardcolumn() {
		String res = HttpRequest.get(EnterpriseDataUrl.getEnterpriseUrl() + "v2/"+EnterpriseData.getEnterpriseId()+"/users/useridcardcolumn").
				query("access_token",TokenData.getMangerToken())
				.send().body();
		Assert.assertTrue(res.contains("success"),"查看健康等级，实际返回结果："+res);
	}
	
	@Test(description = "enterprise/user_info接口验证",priority =12)
	public void queryUserInfo() {
//		String res = HttpRequest.get(EnterpriseDataUrl.getEnterpriseUrl() + "enterprise/user_info").
//				query("access_token",TokenData.getMangerToken())
//				.send().body();
//		Assert.assertTrue(res.contains("name"),"查看enterprise/user_info，实际返回结果："+res);
	}
	
}
