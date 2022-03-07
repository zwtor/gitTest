package setting.cases;

import cn.kxy.base.business.EnterpriseData;
import cn.kxy.base.business.EnterpriseDataUrl;
import cn.kxy.base.business.TokenData;
import cn.kxy.setting.bussiness.UserBusiness;
import com.lazy.httpclient.utils.HttpRequest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestIdentity {

	@Test(description = "查看实名认证详情",priority =1)
	public void test() {
		String body = HttpRequest.get(EnterpriseDataUrl.getEnterpriseUrl()+ "v2/"+EnterpriseData.getEnterpriseId()+"/users/identityquery")
		.query("access_token",TokenData.getMangerToken()).query("user_id",UserBusiness.getUserId()).send().body();
		Assert.assertTrue(body.contains("name"),"查看实名认证详情，实际返回结果："+body);
	}
}
