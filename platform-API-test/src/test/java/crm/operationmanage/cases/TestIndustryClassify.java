package crm.operationmanage.cases;

import cn.kxy.operationmanage.business.IndustryBusiness;
import com.alibaba.fastjson.JSONPath;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestIndustryClassify {

	@Test(description = "查看课程行业管理列表",priority = 1)
	public void testQueryCourseClassify() {
		String res = IndustryBusiness.queryIndustryClassify();
		String name = (String)JSONPath.read(res, "$[0].children[0].name");
		Assert.assertNotNull(name,"查看课程行业分类管理列表，实际返回结果："+res);
	}
}
