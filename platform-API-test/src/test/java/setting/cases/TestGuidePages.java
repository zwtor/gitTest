package setting.cases;

import cn.kxy.base.business.EnterpriseDataUrl;
import cn.kxy.base.business.TokenData;
import com.lazy.httpclient.utils.HttpRequest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestGuidePages {

	@Test(description = "引导页测试",priority = 1)
	public void test() {
		String res = HttpRequest.get(EnterpriseDataUrl.getPlatformUrl()+"v2/advertising_space/list").query("access_token",TokenData.getMangerToken())
		.send().body();
		Assert.assertTrue(res.contains("total"));
	}
}
