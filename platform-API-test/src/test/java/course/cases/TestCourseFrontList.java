package course.cases;

import cn.kxy.course.resources.bussiness.CourseFrontListInfoBusiness;
import cn.lazy.init.cases.InitStudyAuthCourse;
import com.alibaba.fastjson.JSONPath;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestCourseFrontList extends InitStudyAuthCourse{

	@Test(description="查询课程分布图",priority=1)
	public void testQueryCoursePieChart() {
		String res = CourseFrontListInfoBusiness.queryCoursePieChart();
		String msg = (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "查询成功","查询课程分布图实际返回结果："+res);
	}
	int total = 0 ;
	@Test(description="course/getCourseCount接口测试",priority=2)
	public void testGetCourseCount() {
		String res = CourseFrontListInfoBusiness.getCourseCount();
		total = (int)JSONPath.read(res, "$.data.totalCount");
		Assert.assertTrue(total>=0, "course/getCourseCount接口测试实际返回结果："+res);
	}
	@Test(description="admin/classify/getListAndCount接口测试",priority=3)
	public void testQueryClassifyCount() {
		String res = CourseFrontListInfoBusiness.queryClassifyCount();
		Assert.assertTrue(res.contains("success"));
	}
}
