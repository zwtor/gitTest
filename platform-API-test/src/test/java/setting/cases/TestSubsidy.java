package setting.cases;

import cn.kxy.base.business.EnterpriseData;
import cn.kxy.base.business.EnterpriseDataUrl;
import cn.kxy.base.business.TokenData;
import com.lazy.httpclient.utils.HttpRequest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestSubsidy {

	@Test(description= "查看补贴状态",priority = 1)
	public void testSubsidy() {
		String res = HttpRequest.get(EnterpriseDataUrl.getGzsubsidyUrl()+ "v1/subsidy/"+EnterpriseData.getEnterpriseId()+"/switch_status").
		query("access_token",TokenData.getMangerToken()).send().body();
		Assert.assertTrue(res.contains("status"),"查看补贴状态"+res);
	}
	
	@Test(description = "查看企业名称",priority =2)
	public void test() {
//		String res = HttpRequest.get(EnterpriseDataUrl.getGzsubsidyUrl()+ "v1/subsidy/"+EnterpriseData.getEnterpriseId()+"/get_enterprise_name").
//				query("access_token",TokenData.getMangerToken()).send().body();
//		Assert.assertTrue(res.contains("result"),"查看补贴状态"+res);
	}
}
