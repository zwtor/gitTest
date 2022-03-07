package authentication.cases;

import com.lazy.common.utils.CommonData;
import org.testng.annotations.Test;

public class TestCertificate2 {

	String cert_name = "PLSeniorHunter"+CommonData.getStringRandom(4);
	String cert_name0 = "Hunter"+CommonData.getStringRandom(4);
	
	
	String id01 = "";
	String mapping_id = "";
	@Test(description="查看证书模板",priority=1)
	public void testQueryTemplate() {
//		String res = CertificateBusiness.queryTemplate();
//		String png_url = (String)JSONPath.read(res, "$.list[0].url");
//		Assert.assertTrue(!png_url.isEmpty(), "查看证书模板实际返回结果："+res);
	}
	
	@Test(description="创建英文，不适用公章的证书",priority=2)
	public void testCreatEnglishCertificate () {
//		String res = CertificateBusiness.creatCertificate(cert_name, "kxyTest0", "Sinpoes", "false", "english", "1");
//		String msg = (String)JSONPath.read(res, "$.msg");
//		id01=(String)JSONPath.read(res, "$.data");
//		Assert.assertEquals(msg, "新增证书成功！","创建英文，不适用公章的证书，实际返回结果："+res);
 	}
	@Test(description="查看证书详情",priority=3)
	public void testQueryInfo() {
//		String res = CertificateBusiness.queryInfo(id01);
//		String code = (String)JSONPath.read(res, "$.certificateCode");
//		String name = (String)JSONPath.read(res, "$.name");
//		
//		String companyName = (String)JSONPath.read(res, "$.companyName");
//		String languageType = (String)JSONPath.read(res, "$.languageType");
//		Assert.assertEquals(companyName, "Sinpoes","查看证书详情实际返回结果："+res);
//		Assert.assertEquals(languageType, "english","查看证书详情实际返回结果："+res);
//		Assert.assertEquals(name, cert_name,"查看证书详情实际返回结果："+res);
//		Assert.assertEquals(code, "kxyTest0","查看证书详情实际返回结果："+res);
	}
	
	@Test(description="创建中文，适用公章的证书",priority=4)
	public void testCreatChinaCertificate () {
//		String res = CertificateBusiness.creatCertificate(cert_name0, "kxyTest0", "Sinpoes", "true", "chinese", "2");
//		String msg = (String)JSONPath.read(res, "$.msg");
//		Assert.assertEquals(msg, "新增证书成功！","创建中文，适用公章的证书，实际返回结果："+res);
 	}
	
	@Test(description="查询证书列表--全部",priority=5)
	public void testQueryCertificateAllList() {
//		String res = CertificateBusiness.queryCertificateList(cert_name, "");
//		String name = (String)JSONPath.read(res, "$.list[0].name");
//		int status = (int)JSONPath.read(res, "$.list[0].status");
//		Long createTime = (Long)JSONPath.read(res, "$.list[0].createTime");
//		int userCount = (int)JSONPath.read(res, "$.list[0].userCount");
//		
//		Assert.assertEquals(userCount, 0,"查询证书列表--全部(证书获取人数校验)："+res);
//		Assert.assertTrue(createTime!=null,"查询证书列表--全部(证书创建时间校验)："+res );
//		Assert.assertEquals(status, 1,"查询证书列表--全部(证书状态)："+res);
//		Assert.assertEquals(name, cert_name,"查询证书列表--全部(证书名称)："+res);
	}
	
	@Test(description="编辑证书",priority=6)
	public void testEditCertificate() {
//		String res = CertificateBusiness.editCertificate(id01, cert_name, "kxyCeoCO", "SpInfo", "true", "chinese", "2");
//		String msg = (String)JSONPath.read(res, "$.msg");
//		Assert.assertEquals(msg, "编辑证书成功！","编辑证书，实际返回结果："+res);
	}
	
	@Test(description="给个人颁发证书实际返回结果",priority=7)
	public void testAwardCertificate() {
//		String res = CertificateBusiness.awardCertificate(id01, "very good");
//		String  mapping_id = (String)JSONPath.read(res, "$.biz_certificate_user_mapping_ids[0]");
//		Assert.assertTrue(mapping_id!=null, "给个人颁发证书实际返回结果："+res);
	}
	
	@Test(description="通过单个证书Id，查询获取证书的人数列表",priority=8)
	public void testGetCertificateForPeopleList () {
//		try {
//			Thread.sleep(2000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		String res = CertificateBusiness.getCertificateForPeopleList(id01);
//		mapping_id  = (String)JSONPath.read(res, "$.list[0].id");
//		String userName = (String)JSONPath.read(res, "$.list[0].userName");
//		Long createTime = (Long)JSONPath.read(res, "$.list[0].createTime");
//		String sourceTypeName = (String)JSONPath.read(res, "$.list[0].sourceTypeName");
//		String sourceName = (String)JSONPath.read(res, "$.list[0].sourceName");
//		Assert.assertEquals(sourceName, "very good","通过单个证书Id，查询获取证书的人数列表--证书来源，实际返回结果："+res);
//		Assert.assertEquals(userName, UserBusiness.getUsername(),"通过单个证书Id，查询获取证书的人数列表--姓名，实际返回结果："+res);
//		Assert.assertEquals(sourceTypeName,"手动颁发","通过单个证书Id，查询获取证书的人数列表--颁发方式，实际返回结果："+res);
//		Assert.assertTrue(createTime!=null, "通过单个证书Id，查询获取证书的人数列表--创建时间，实际返回结果："+res);
	}
	@Test(description="获得证书后 ，查询证书列表中，证书获得的人数",priority=9)
	public void testQueryCertificatPeopleList() {
//		String res = CertificateBusiness.queryCertificateList(cert_name, "");
//		int userCount = (int)JSONPath.read(res, "$.list[0].userCount");
//		Assert.assertEquals(userCount, 1,"获得证书后 ，查询证书列表中，证书获得的人数："+res);
	}
	@Test(description="停用证书",priority=10)
	public void testModifyCertificateStopStatus() {
//		String res = CertificateBusiness.modifyCertificateStatus(id01);
//		String msg = (String)JSONPath.read(res, "$.msg");
//		Assert.assertEquals(msg, "修改证书状态成功！","停用证书，实际返回结果："+res);
	}
	
	@Test(description="启用证书",priority=11)
	public void testModifyCertificateOpenStatus() {
//		String res = CertificateBusiness.modifyCertificateStatus(id01);
//		String msg = (String)JSONPath.read(res, "$.msg");
//		Assert.assertEquals(msg, "修改证书状态成功！","启用证书，实际返回结果："+res);
	}
	@Test(description="撤销证书",priority=12)
	public void testRevokeCertificate() {
//		String res = CertificateBusiness.revokeCertificate(mapping_id,id01);
//		String delete = (String)JSONPath.read(res, "$.deleted");
//		Assert.assertEquals(delete, "true","撤销证书实际返回结果："+res);
	}
	
	@Test(description="撤销证书后 ，查询证书列表中，证书获得的人数",priority=13)
	public void testQueryRevokeCertificatList() {
//		try {
//			Thread.sleep(2000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		String res = CertificateBusiness.queryCertificateList(cert_name, "");
//		int userCount = (int)JSONPath.read(res, "$.list[0].userCount");
//		Assert.assertEquals(userCount, 0,"撤销证书后，查询证书列表中，证书获得的人数："+res);
	}
	@Test(description="启用证书时，id为null",priority=14)
	public void testmodifyCertificateStatusIdIsNull() {
//		String res = CertificateBusiness.modifyCertificateStatus("");
//		String msg = (String)JSONPath.read(res, "$.msg");
//		Assert.assertEquals(msg, "找不到该证书","启用证书时，id为null实际返回结果："+res);
	}
	int status ;
	@Test(description = "获取第一个证书的id",priority = 15)
	public void testGetFirstStatus() {
//		String res = CertificateBusiness.queryCertificateList(cert_name, "");
//		status = (int)JSONPath.read(res, "$.list[0].status");
		
	}
	@Test(description = "停用证书",priority = 16)
	public void testmodifyCertificateStatus1() {
//		if (status ==1) {
//			String res = CertificateBusiness.modifyCertificateStatus(id01);
//			Assert.assertTrue(res.contains("修改证书状态成功！"),""+res);
//		}
	}
	@Test(description = "删除第一个证书",priority = 17)
	public void testDeleteFirstCret() {
//		String res = CertificateBusiness.deleteCret(id01);
//		String msg = (String)JSONPath.read(res, "$.msg");
//		Assert.assertEquals(msg, "删除证书成功！","删除第一个证书"+res);
	}
	String id02 = "";
	int status2 ;
	@Test(description = "获取第二个证书的id",priority = 18)
	public void testGetSecondId() {
//		String res = CertificateBusiness.queryCertificateList(cert_name0, "");
//		id02 = (String)JSONPath.read(res, "$.list[0].id");
//		status2 = (int)JSONPath.read(res, "$.list[0].status");
	}
	
	@Test(description = "停用证书",priority = 19)
	public void testmodifyCertificateStatus() {
//		if (status2 ==1) {
//			String res = CertificateBusiness.modifyCertificateStatus(id02);
//			Assert.assertTrue(res.contains("修改证书状态成功！"),""+res);
//		}
	}
	@Test(description = "删除第二个证书",priority = 20)
	public void testDeleteSecondCret() {
//		String res = CertificateBusiness.deleteCret(id02);
//		String msg = (String)JSONPath.read(res, "$.msg");
//		Assert.assertEquals(msg, "删除证书成功！","删除第二个证书"+res);
	}
}
