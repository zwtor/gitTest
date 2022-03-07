package setting.cases;

import cn.kxy.setting.bussiness.BannerSettingBusiness;
import com.jayway.jsonpath.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestBannerSetting {

	String id = "";
	@Test(description = "查看banner图信息",priority = 1)
	public void queryBanner() {
		String queryBanner = BannerSettingBusiness.queryBanner();
		id = (String)JsonPath.read(queryBanner, "$.menus[0].id");
		Assert.assertTrue(queryBanner.contains("jump"));
	}
	
	@Test(description = "设置banner图",priority = 2)
	public void updateBanner() {
		String updateBanner = BannerSettingBusiness.updateBanner(id);
		Assert.assertTrue(updateBanner.contains("operation"));
	}
}
