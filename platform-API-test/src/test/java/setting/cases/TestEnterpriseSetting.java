package setting.cases;

import cn.kxy.setting.bussiness.EnterpriseSettingBusiness;
import com.alibaba.fastjson.JSONPath;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestEnterpriseSetting {

	String id="";
	String logo=""; 
	String name=""; 
	String mobile=""; 
	String urlMobile="";
	String dingCorpId="";
	String urlQRCode="";
	String desc_qrcode="";
	@Test(description="获取企业设置",priority=1)
	public void testGetEnterpriseSetting() {
		String res = EnterpriseSettingBusiness.getEnterpriseSetting();
		id=(String)JSONPath.read(res, "$.data.id");
		urlMobile =(String)JSONPath.read(res, "$.data.urlMobile");
		logo=(String)JSONPath.read(res, "$.data.logo");
		name=(String)JSONPath.read(res, "$.data.name");
		mobile=(String)JSONPath.read(res, "$.data.mobile");
		dingCorpId=(String)JSONPath.read(res, "$.data.dingCorpId");
		urlQRCode=(String)JSONPath.read(res, "$.data.urlQRCode");
		desc_qrcode=(String)JSONPath.read(res, "$.data.desc_qrcode");
		Assert.assertNotNull(name,"获取企业设置,实际返回结果："+res);
		Assert.assertNotNull(desc_qrcode,"获取企业设置,实际返回结果："+res);
		Assert.assertNotNull(logo,"获取企业设置,实际返回结果："+res);
		Assert.assertNotNull(mobile,"获取企业设置,实际返回结果："+res);
		Assert.assertNotNull(urlQRCode,"获取企业设置,实际返回结果："+res);
	}
	@Test(description="保存企业设置",dependsOnMethods="testGetEnterpriseSetting",priority=2)
	public void testSaveEnterpriseSetting () {
//		String saveEnterpriseSetting = EnterpriseSettingBusiness.saveEnterpriseSetting(id, logo, name, mobile, urlMobile, dingCorpId, urlQRCode, desc_qrcode);
//		String msg = (String)JSONPath.read(saveEnterpriseSetting, "$.msg");
//		Assert.assertEquals(msg, "配置成功",""+saveEnterpriseSetting);
	}
	@Test(description="查看企业是否设置成功",priority=3)
	public void testQueryEnterpriseSetting() {
		String res = EnterpriseSettingBusiness.getEnterpriseSetting();
		logo=(String)JSONPath.read(res, "$.data.logo");
		name=(String)JSONPath.read(res, "$.data.name");
		mobile=(String)JSONPath.read(res, "$.data.mobile");
		urlQRCode=(String)JSONPath.read(res, "$.data.urlQRCode");
		desc_qrcode=(String)JSONPath.read(res, "$.data.desc_qrcode");
		Assert.assertNotNull(name,"获取企业设置,实际返回结果："+res);
		Assert.assertNotNull(desc_qrcode,"获取企业设置,实际返回结果："+res);
		Assert.assertNotNull(logo,"获取企业设置,实际返回结果："+res);
		Assert.assertNotNull(mobile,"获取企业设置,实际返回结果："+res);
		Assert.assertNotNull(urlQRCode,"获取企业设置,实际返回结果："+res);
	}
}
