package authentication.cases;

import cn.kxy.authentication.business.CertificateBusiness;
import com.alibaba.fastjson.JSONPath;
import com.lazy.common.utils.CommonData;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestExtremelyCertificate {

	String id = "";
	String name = "CERT"+CommonData.getStringRandom(7);
	@Test(description="新增证书",priority=1)
	public void testCreatCertificate() {
		String res = CertificateBusiness.creatCertificate(name,"this is des", "kxyTest0", "Sinpoes", "false", "english", "2", "false", "false", "false", "false", "false", "false", "false");
		String msg = (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "新增证书成功！",""+res);
	}
	int status ;
	@Test(description="获取证书id",priority=2)
	public void testGetId() {
		String res = CertificateBusiness.queryCertificateList(name, "");
		id= (String)JSONPath.read(res, "$.list[0].id");
		status = (int)JSONPath.read(res, "$.list[0].status");
	}
	
	
	@Test(description="查看证书详情，全部不显示",priority=3)
	public void testqueryInfoCheckAll() {
		String res = CertificateBusiness.queryInfo(id);
		String name = (String)JSONPath.read(res, "$.display.name");
		String summary = (String)JSONPath.read(res, "$.display.summary");
		String commonSeal = (String)JSONPath.read(res, "$.display.commonSeal");
		String certificateCode = (String)JSONPath.read(res, "$.display.certificateCode");
		String issueTime = (String)JSONPath.read(res, "$.display.issueTime");
		String department = (String)JSONPath.read(res, "$.display.department");
		String logoUrl = (String)JSONPath.read(res, "$.display.logoUrl");
		Assert.assertEquals(name, "false","查看证书详情,name字段展示，实际返回结果："+res);
		Assert.assertEquals(summary, "false","查看证书详情,summary字段不展示，实际返回结果："+res);
		Assert.assertEquals(commonSeal, "false","查看证书详情,commonSeal字段不展示，实际返回结果："+res);
		Assert.assertEquals(certificateCode, "false","查看证书详情,certificateCode字段不展示，实际返回结果："+res);
		Assert.assertEquals(issueTime, "false","查看证书详情,issueTime字段不展示，实际返回结果："+res);
		Assert.assertEquals(department, "false","查看证书详情,department字段不展示，实际返回结果："+res);
		Assert.assertEquals(logoUrl, "false","查看证书详情,logoUrl字段不展示，实际返回结果："+res);
	}
	
	@Test(description="编辑证书，修改为只显示描述",priority=4)
	public void testEditCertificateBydes() {
		String res = CertificateBusiness.editCertificate(id,name,"this is des", "kxyTest0", "Sinpoes", "false", "english", "2", "true", "false", "false", "false", "false", "false", "false");
		String msg = (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "编辑证书成功！",""+res);
	}
	@Test(description="查看证书详情，只显示描述+",priority=5)
	public void testQueryInfoCheckSummary() {
		String res = CertificateBusiness.queryInfo(id);
		String name = (String)JSONPath.read(res, "$.display.name");
		String summary = (String)JSONPath.read(res, "$.display.summary");
		String commonSeal = (String)JSONPath.read(res, "$.display.commonSeal");
		String certificateCode = (String)JSONPath.read(res, "$.display.certificateCode");
		String issueTime = (String)JSONPath.read(res, "$.display.issueTime");
		String department = (String)JSONPath.read(res, "$.display.department");
		String logoUrl = (String)JSONPath.read(res, "$.display.logoUrl");
		Assert.assertEquals(name, "false","查看证书详情,name字段不展示，实际返回结果："+res);
		Assert.assertEquals(summary, "true","查看证书详情,summary字段展示，实际返回结果："+res);
		Assert.assertEquals(commonSeal, "false","查看证书详情,commonSeal字段不展示，实际返回结果："+res);
		Assert.assertEquals(certificateCode, "false","查看证书详情,certificateCode字段不展示，实际返回结果："+res);
		Assert.assertEquals(issueTime, "false","查看证书详情,issueTime字段不展示，实际返回结果："+res);
		Assert.assertEquals(department, "false","查看证书详情,department字段不展示，实际返回结果："+res);
		Assert.assertEquals(logoUrl, "false","查看证书详情,logoUrl字段不展示，实际返回结果："+res);
	}
	
	@Test(description="编辑证书，修改为显示描述、公章",priority=6)
	public void testEditCertificateByCommonseal() {
		String res = CertificateBusiness.editCertificate(id,name,"this is des", "kxyTest0", "Sinpoes", "false", "english", "2", "true", "true", "false", "false", "false", "false", "false");
		String msg = (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "编辑证书成功！",""+res);
	}
	@Test(description="查看证书详情，显示描述、公章",priority=7)
	public void testQueryInfoCheckCommonSeal() {
		String res = CertificateBusiness.queryInfo(id);
		String name = (String)JSONPath.read(res, "$.display.name");
		String summary = (String)JSONPath.read(res, "$.display.summary");
		String commonSeal = (String)JSONPath.read(res, "$.display.commonSeal");
		String certificateCode = (String)JSONPath.read(res, "$.display.certificateCode");
		String issueTime = (String)JSONPath.read(res, "$.display.issueTime");
		String department = (String)JSONPath.read(res, "$.display.department");
		String logoUrl = (String)JSONPath.read(res, "$.display.logoUrl");
		Assert.assertEquals(name, "false","查看证书详情,name字段不展示，实际返回结果："+res);
		Assert.assertEquals(summary, "true","查看证书详情,summary字段展示，实际返回结果："+res);
		Assert.assertEquals(commonSeal, "true","查看证书详情,commonSeal字段展示，实际返回结果："+res);
		Assert.assertEquals(certificateCode, "false","查看证书详情,certificateCode字段不展示，实际返回结果："+res);
		Assert.assertEquals(issueTime, "false","查看证书详情,issueTime字段不展示，实际返回结果："+res);
		Assert.assertEquals(department, "false","查看证书详情,department字段不展示，实际返回结果："+res);
		Assert.assertEquals(logoUrl, "false","查看证书详情,logoUrl字段不展示，实际返回结果："+res);
	}
	
	@Test(description="编辑证书，修改为显示描述、公章，证书编码",priority=8)
	public void testEditCertificateByCertificateCode() {
		String res = CertificateBusiness.editCertificate(id,name,"this is des", "kxyTest0", "Sinpoes", "false", "english", "2", "true", "true", "true", "false", "false", "false", "false");
		String msg = (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "编辑证书成功！",""+res);
	}
	@Test(description="查看证书详情，显示描述、公章、证书编码",priority=9)
	public void testQueryInfoCheckCertificateCode() {
		String res = CertificateBusiness.queryInfo(id);
		String name = (String)JSONPath.read(res, "$.display.name");
		String summary = (String)JSONPath.read(res, "$.display.summary");
		String commonSeal = (String)JSONPath.read(res, "$.display.commonSeal");
		String certificateCode = (String)JSONPath.read(res, "$.display.certificateCode");
		String issueTime = (String)JSONPath.read(res, "$.display.issueTime");
		String department = (String)JSONPath.read(res, "$.display.department");
		String logoUrl = (String)JSONPath.read(res, "$.display.logoUrl");
		Assert.assertEquals(name, "false","查看证书详情,name字段不展示，实际返回结果："+res);
		Assert.assertEquals(summary, "true","查看证书详情,summary字段展示，实际返回结果："+res);
		Assert.assertEquals(commonSeal, "true","查看证书详情,commonSeal字段展示，实际返回结果："+res);
		Assert.assertEquals(certificateCode, "true","查看证书详情,certificateCode字段展示，实际返回结果："+res);
		Assert.assertEquals(issueTime, "false","查看证书详情,issueTime字段不展示，实际返回结果："+res);
		Assert.assertEquals(department, "false","查看证书详情,department字段不展示，实际返回结果："+res);
		Assert.assertEquals(logoUrl, "false","查看证书详情,logoUrl字段不展示，实际返回结果："+res);
	}
	
	@Test(description="编辑证书，修改为显示描述、公章，证书编码、颁发日期",priority=10)
	public void testEditCertificateByIssueTime() {
		String res = CertificateBusiness.editCertificate(id,name,"this is des", "kxyTest0", "Sinpoes", "false", "english", "2", "true", "true", "true", "true", "false", "false", "false");
		String msg = (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "编辑证书成功！",""+res);
	}
	@Test(description="查看证书详情，显示描述、公章、证书编码、颁发日期",priority=11)
	public void testQueryInfoCheckIssueTime() {
		String res = CertificateBusiness.queryInfo(id);
		String name = (String)JSONPath.read(res, "$.display.name");
		String summary = (String)JSONPath.read(res, "$.display.summary");
		String commonSeal = (String)JSONPath.read(res, "$.display.commonSeal");
		String certificateCode = (String)JSONPath.read(res, "$.display.certificateCode");
		String issueTime = (String)JSONPath.read(res, "$.display.issueTime");
		String department = (String)JSONPath.read(res, "$.display.department");
		String logoUrl = (String)JSONPath.read(res, "$.display.logoUrl");
		Assert.assertEquals(name, "false","查看证书详情,name字段不展示，实际返回结果："+res);
		Assert.assertEquals(summary, "true","查看证书详情,summary字段不展示，实际返回结果："+res);
		Assert.assertEquals(commonSeal, "true","查看证书详情,commonSeal字段不展示，实际返回结果："+res);
		Assert.assertEquals(certificateCode, "true","查看证书详情,certificateCode字段不展示，实际返回结果："+res);
		Assert.assertEquals(issueTime, "true","查看证书详情,issueTime字段不展示，实际返回结果："+res);
		Assert.assertEquals(department, "false","查看证书详情,department字展示，实际返回结果："+res);
		Assert.assertEquals(logoUrl, "false","查看证书详情,logoUrl字段展示，实际返回结果："+res);
	}
	
	@Test(description="编辑证书，修改为显示描述、公章，证书编码、颁发日期、名称",priority=12)
	public void testEditCertificateByName() {
		String res = CertificateBusiness.editCertificate(id,name,"this is des", "kxyTest0", "Sinpoes", "false", "english", "2", "true", "true", "true", "true", "true", "false", "false");
		String msg = (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "编辑证书成功！",""+res);
	}
	@Test(description="查看证书详情，显示描述、公章、证书编码、颁发日期、名称",priority=13)
	public void testQueryInfoCheckName() {
		String res = CertificateBusiness.queryInfo(id);
		String name = (String)JSONPath.read(res, "$.display.name");
		String summary = (String)JSONPath.read(res, "$.display.summary");
		String commonSeal = (String)JSONPath.read(res, "$.display.commonSeal");
		String certificateCode = (String)JSONPath.read(res, "$.display.certificateCode");
		String issueTime = (String)JSONPath.read(res, "$.display.issueTime");
		String department = (String)JSONPath.read(res, "$.display.department");
		String logoUrl = (String)JSONPath.read(res, "$.display.logoUrl");
		Assert.assertEquals(name, "true","查看证书详情,name字段展示，实际返回结果："+res);
		Assert.assertEquals(summary, "true","查看证书详情,summary字段展示，实际返回结果："+res);
		Assert.assertEquals(commonSeal, "true","查看证书详情,commonSeal字段展示，实际返回结果："+res);
		Assert.assertEquals(certificateCode, "true","查看证书详情,certificateCode字段展示，实际返回结果："+res);
		Assert.assertEquals(issueTime, "true","查看证书详情,issueTime字段展示，实际返回结果："+res);
		Assert.assertEquals(department, "false","查看证书详情,department字段不展示，实际返回结果："+res);
		Assert.assertEquals(logoUrl, "false","查看证书详情,logoUrl字段不展示，实际返回结果："+res);
	}
	
	@Test(description="编辑证书，修改为显示描述、公章，证书编码、颁发日期、名称",priority=14)
	public void testEditCertificateByDepartment() {
		String res = CertificateBusiness.editCertificate(id,name,"this is des", "kxyTest0", "Sinpoes", "false", "english", "2", "true", "true", "true", "true", "true", "true", "false");
		String msg = (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "编辑证书成功！",""+res);
	}
	@Test(description="查看证书详情，显示描述、公章、证书编码、颁发日期、名称",priority=15)
	public void testQueryInfoCheckDepartment() {
		String res = CertificateBusiness.queryInfo(id);
		String name = (String)JSONPath.read(res, "$.display.name");
		String summary = (String)JSONPath.read(res, "$.display.summary");
		String commonSeal = (String)JSONPath.read(res, "$.display.commonSeal");
		String certificateCode = (String)JSONPath.read(res, "$.display.certificateCode");
		String issueTime = (String)JSONPath.read(res, "$.display.issueTime");
		String department = (String)JSONPath.read(res, "$.display.department");
		String logoUrl = (String)JSONPath.read(res, "$.display.logoUrl");
		Assert.assertEquals(name, "true","查看证书详情,name字段展示，实际返回结果："+res);
		Assert.assertEquals(summary, "true","查看证书详情,summary字段展示，实际返回结果："+res);
		Assert.assertEquals(commonSeal, "true","查看证书详情,commonSeal字段展示，实际返回结果："+res);
		Assert.assertEquals(certificateCode, "true","查看证书详情,certificateCode字段展示，实际返回结果："+res);
		Assert.assertEquals(issueTime, "true","查看证书详情,issueTime字段展示，实际返回结果："+res);
		Assert.assertEquals(department, "true","查看证书详情,department字段展示，实际返回结果："+res);
		Assert.assertEquals(logoUrl, "false","查看证书详情,logoUrl字段不展示，实际返回结果："+res);
	}
	
	@Test(description="编辑证书，修改为显示描述、公章，证书编码、颁发日期、名称",priority=16)
	public void testEditCertificateByLogoUrl() {
		String res = CertificateBusiness.editCertificate(id,name,"this is des", "kxyTest0", "Sinpoes", "false", "english", "2", "true", "true", "true", "true", "true", "true", "true");
		String msg = (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "编辑证书成功！",""+res);
	}
	@Test(description="查看证书详情，显示描述、公章、证书编码、颁发日期、名称",priority=17)
	public void testQueryInfoCheckLogoUrl() {
		String res = CertificateBusiness.queryInfo(id);
		String name = (String)JSONPath.read(res, "$.display.name");
		String summary = (String)JSONPath.read(res, "$.display.summary");
		String commonSeal = (String)JSONPath.read(res, "$.display.commonSeal");
		String certificateCode = (String)JSONPath.read(res, "$.display.certificateCode");
		String issueTime = (String)JSONPath.read(res, "$.display.issueTime");
		String department = (String)JSONPath.read(res, "$.display.department");
		String logoUrl = (String)JSONPath.read(res, "$.display.logoUrl");
		Assert.assertEquals(name, "true","查看证书详情,name字段展示，实际返回结果："+res);
		Assert.assertEquals(summary, "true","查看证书详情,summary字段展示，实际返回结果："+res);
		Assert.assertEquals(commonSeal, "true","查看证书详情,commonSeal字段展示，实际返回结果："+res);
		Assert.assertEquals(certificateCode, "true","查看证书详情,certificateCode字段展示，实际返回结果："+res);
		Assert.assertEquals(issueTime, "true","查看证书详情,issueTime字段展示，实际返回结果："+res);
		Assert.assertEquals(department, "true","查看证书详情,department字段展示，实际返回结果："+res);
		Assert.assertEquals(logoUrl, "true","查看证书详情,logoUrl字段展示，实际返回结果："+res);
	}
	
	@Test(description = "停用证书",priority = 18)
	public void testmodifyCertificateStatus() {
		if (status ==1) {
			String res = CertificateBusiness.modifyCertificateStatus(id);
			Assert.assertTrue(res.contains("修改证书状态成功！"),""+res);
		}
	}
	@Test(description = "删除证书",priority = 19)
	public void testDeleteSecondCret() {
		String res = CertificateBusiness.deleteCret(id);
		String msg = (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "删除证书成功！","删除证书"+res);
	}
	
}
