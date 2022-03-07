package crm.homepage.cases;

import cn.kxy.homepage.business.HomePageBusiness;
import com.alibaba.fastjson.JSONPath;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestWeekList {

	@Test(description = "每周课程数",priority =1)
	public void testqueryBizCourseWeekList() {
		String res = HomePageBusiness.queryBizCourseWeekList();
		Assert.assertTrue(res.contains("classWeekSeries"), "每周课程数实际返回结果："+res);
	}
	@Test(description = "每周订单数",priority = 2)
	public void testqueryBizOrderWeekList() {
		String res = HomePageBusiness.queryBizOrderWeekList();
		System.out.println("crm每周订单数:实际返回结果："+res);
	}
	/*@Test(description = "每周企业用户数",priority = 3)
	public void testqueryEnterpriseUserWeekList() {
		String res = HomePageBusiness.queryEnterpriseUserWeekList();
		String name = (String)JSONPath.read(res, "$.userWeekSeries[2].name");
		Assert.assertEquals(name, "累计","每周企业用户数,实际返回结果："+res);
	}*/
	@Test(description = "每周企业数",priority = 4)
	public void testqueryEnterpriseWeekList() {
		String res = HomePageBusiness.queryEnterpriseWeekList();
		String name = (String)JSONPath.read(res, "$.enterpriseWeekSeries[0].name");
		Assert.assertEquals(name, "新增","每周企业数,实际返回结果："+res);
	}
}
