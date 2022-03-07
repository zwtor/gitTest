package authentication.cases;

import cn.kxy.authentication.business.CertificateBusiness;
import com.alibaba.fastjson.JSONPath;
import com.lazy.common.utils.CommonData;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestCertificateDelete {
	String id = "";
	String name = "Cert_Delete"+CommonData.getStringRandom(5);
	@Test(description="新增证书",priority=1)
	public void testCreatCertificate() {
		String res = CertificateBusiness.creatCertificate(name,"this is des", "kxyTest0", "Sinpoes", "false", "english", "2", "false", "false", "false", "false", "false", "false", "false");
		String msg = (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "新增证书成功！",""+res);
	}
	int status ;
	@Test(description="获取证书id",priority= 2)
	public void testGetId() {
		String res = CertificateBusiness.queryCertificateList(name, "");
		id= (String)JSONPath.read(res, "$.list[0].id");
		status = (int)JSONPath.read(res, "$.list[0].status");
	}
	String mapping_id = "";
	@Test(description="给个人颁发证书实际返回结果",priority= 3)
	public void testAwardCertificate() {
		String res = CertificateBusiness.awardCertificate(id, "very good");
	    mapping_id = (String)JSONPath.read(res, "$.biz_certificate_user_mapping_ids[0]");
		Assert.assertTrue(mapping_id!=null, "给个人颁发证书实际返回结果："+res);
	}
	@Test(description = "停用证书",priority = 4)
	public void testmodifyCertificateStatus() {
		if (status ==1) {
			String res = CertificateBusiness.modifyCertificateStatus(id);
			Assert.assertTrue(res.contains("修改证书状态成功！"),""+res);
		}
	}
	@Test(description = "删除证书",priority = 5)
	public void testDeleteSecondCret() {
		String res = CertificateBusiness.deleteCret(id);
		String msg = (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "删除证书成功！","删除证书"+res);
	}
	@Test(description="查看我的证书列表,证书不应删除",priority = 6)
	public void testQueryMyCertificateByHandType() {
//		String res = MyBusiness.queryMyCertificate("3", "", "", name);
//		int total = (int)JSONPath.read(res, "$.total");
//		Assert.assertTrue(total==1, "证书删除后，查看我的证书列表,获得的证书不应删除："+res);
	}
	
}
