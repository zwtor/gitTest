package crm.operationmanage.cases;

import cn.kxy.operationmanage.business.CompanyOperationBusiness;
import com.alibaba.fastjson.JSONPath;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestEnterpriseManage {

	@Test(description = "获取企业列表",priority = 1)
	public void testGetEnterpriseList() {
//		String res = CompanyOperationBusiness.getEnterpriseList();
//		String name = (String)JSONPath.read(res,"$.list[0].name");
//		Assert.assertNotNull(name,"获取企业列表,实际返回结果:"+res);
	}
	
	@Test(description = "获取行业筛选的列表",priority = 2)
	public void testGetIndustryList() {
		String res = CompanyOperationBusiness.getIndustryList();
		String name = (String)JSONPath.read(res, "$[0]");
		Assert.assertNotNull(name,"获取行业筛选的列表,实际返回结果："+res);
	}
	
	@Test(description = "在企业列表获取个人信息",priority =3)
	public void testGetPerson() {
		String res = CompanyOperationBusiness.getPerson();
		String name = (String)JSONPath.read(res, "$.personList[0].value");
		Assert.assertNotNull(name,"在企业列表获取个人信息,实际返回结果："+res);
	}
	
	@Test(description = "查看项目阶段下拉框的内容",priority = 4)
	public void testGetSelectListWithAll() {
		String res = CompanyOperationBusiness.getSelectListWithAll();
		String name = (String)JSONPath.read(res, "$.list[0].name");
		Assert.assertNotNull(name,"查看项目阶段下拉框的内容,实际返回结果："+res);
	}
}
