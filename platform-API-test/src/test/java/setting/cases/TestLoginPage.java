package setting.cases;

import cn.kxy.setting.bussiness.LoginPageSettingBusiness;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestLoginPage {

	@Test(description = "查看游客页设置",priority = 1)
	public void queryCmsPage() {
		String queryCmsPage = LoginPageSettingBusiness.queryCmsPage();
		Assert.assertTrue(queryCmsPage.contains("操作成功"),"查看游客页设置"+queryCmsPage);
	}
	
	@Test(description = "queryDomain接口",priority = 2)
	public void queryDomain () {
		String queryDomain = LoginPageSettingBusiness.queryDomain();
		Assert.assertTrue(queryDomain.contains("success"),"queryDomain："+queryDomain);
	}
	
	@Test(description = "查看登录页风格",priority = 3)
	public void queryLoginImage() {
		String queryLoginImage = LoginPageSettingBusiness.queryLoginImage();
		Assert.assertTrue(queryLoginImage.contains("success"),"查看登录页风格"+queryLoginImage);
	}
	
	@Test(description = "查看登录方式",priority = 4)
	public void queryLoginMethod() {
		String queryLoginMethod = LoginPageSettingBusiness.queryLoginMethod();
		Assert.assertTrue(queryLoginMethod.contains("success"),"查看登录方式"+queryLoginMethod);
	}
	
	@Test(description = "查看域名信息",priority = 5)
	public void queryPcandmobileDomain() {
		String queryPcandmobileDomain = LoginPageSettingBusiness.queryPcandmobileDomain();
		Assert.assertTrue(queryPcandmobileDomain.contains("success"),"查看域名信息"+queryPcandmobileDomain);
	}
}
