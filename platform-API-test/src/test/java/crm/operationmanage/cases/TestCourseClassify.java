package crm.operationmanage.cases;

import cn.kxy.operationmanage.business.CourseClassifyBusiness;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestCourseClassify {

	@Test(description = "查看课程分类管理列表",priority = 1)
	public void testQueryCourseClassify() {
		String res = CourseClassifyBusiness.queryCourseClassify();
		Assert.assertTrue(res.contains("name"),"查看课程分类管理列表,实际返回结果："+res);
	}
}
