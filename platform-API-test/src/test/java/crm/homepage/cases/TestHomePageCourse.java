package crm.homepage.cases;

import cn.kxy.homepage.business.HomePageBusiness;
import com.alibaba.fastjson.JSONPath;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestHomePageCourse {
	
	@Test(description = "查看课程播放排行榜",priority = 1)
	public void testQueryPlayCountOrderList() {
		String res = HomePageBusiness.queryPlayCountOrderList();
		String title = (String)JSONPath.read(res, "$.coursePlayOrder[0].title");
		Assert.assertNotNull(title,""+res);
	}
	
	@Test(description = "查看课程浏览排行榜",priority = 2)
	public void testQueryscanCountOrderList() {
		String res = HomePageBusiness.queryscanCountOrderList();
		String title = (String)JSONPath.read(res, "$.courseScanOrder[0].title");
		Assert.assertNotNull(title,""+res);
	}
	@Test(description = "查看企业分布",priority = 3)
	public void testQueryAuthTypeList() {
//		String res = HomePageBusiness.queryAuthTypeList();
//		String name = (String)JSONPath.read(res, "$.distributeData[0]");
//		Assert.assertEquals(name, "50人以下","查看企业分布实际返回结果:"+res);
	}
	@Test(description = "查看企业行业分布",priority = 4)
	public void testQueryIndustryList() {
//		String res = HomePageBusiness.queryIndustryList();
//		String name = (String)JSONPath.read(res, "$.industryOrder[0].industry");
//		Assert.assertNotNull(name, "查看企业行业分布，实际返回结果:"+res);
	}
	
	@Test(description = "查看开通职位分布",priority = 5)
	public void testQueryPositionListList() {
//		String res = HomePageBusiness.queryPositionList();
//		String name = (String)JSONPath.read(res, "$.positionOrder[0].position");
//		Assert.assertNotNull(name, "查看开通职位分布，实际返回结果:"+res);
	}
	
}
