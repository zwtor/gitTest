package crm.homepage.cases;

import cn.kxy.homepage.business.HomePageBusiness;
import com.alibaba.fastjson.JSONPath;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestMonthList {

	@Test(description = "每月课程数",priority =1)
	public void testqueryBizCourseWeekList() {
		String res = HomePageBusiness.queryBizCourseMonthList();
		Assert.assertTrue(res.contains("classMonthSeries"), "每周课程数实际返回结果："+res);
	}
	@Test(description = "每月订单数",priority = 2)
	public void testqueryBizOrderWeekList() {
		String res = HomePageBusiness.queryBizOrderMonthList();
		String name = (String)JSONPath.read(res, "$.orderMonthSeries[0].name");
		Assert.assertEquals(name, "新增订单","每月订单数实际返回结果"+res);
	}
	@Test(description = "每月企业用户数",priority = 3)
	public void testqueryEnterpriseUserWeekList() {
		String res = HomePageBusiness.queryEnterpriseUserMonthList();
		String name = (String)JSONPath.read(res, "$.userMonthSeries[2].name");
		Assert.assertEquals(name, "累计","每月企业用户数,实际返回结果："+res);
	}
	@Test(description = "每月企业数",priority = 4)
	public void testqueryEnterpriseWeekList() {
		String res = HomePageBusiness.queryEnterpriseMonthList();
		String name = (String)JSONPath.read(res, "$.enterpriseMonthSeries[0].name");
		Assert.assertEquals(name, "新增","每月企业数,实际返回结果："+res);
	}
}
