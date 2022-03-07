package setting.cases;

import cn.kxy.setting.bussiness.OpenPlatformBusiness;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestOpenPlatform {

	@Test(description = "查看公众号授权",priority = 1)
	public void queryAuthorizeList() {
		String res = OpenPlatformBusiness.queryAuthorizeList();
		Assert.assertTrue(res.contains("success"),"查看公众号授权，实际返回结果："+res);
	}
	@Test(description = "查看事件回调详情",priority = 2)
	public void queryEventBackDetail() {
		String res = OpenPlatformBusiness.queryEventBackDetail();
		Assert.assertTrue(res.contains("msg"),"查看事件回调详情，实际返回结果："+res);
	}
	@Test(description = "查看事件回调类型",priority = 3)
	public void queryEventBackType() {
		String res = OpenPlatformBusiness.queryEventBackType();
		Assert.assertTrue(res.contains("msg"),"查看事件回调类型，实际返回结果："+res);
	}
	@Test(description = "查看ApiKey",priority = 4)
	public void querySyncStandard() {
//		String res = OpenPlatformBusiness.queryApiKey();
//		Assert.assertTrue(res.contains("success"),"查看ApiKey，实际返回结果："+res);
	}
}
