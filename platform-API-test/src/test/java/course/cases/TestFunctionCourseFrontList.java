package course.cases;

import cn.kxy.course.resources.bussiness.ArticleBusiness;
import cn.kxy.course.resources.bussiness.CourseBusiness;
import cn.kxy.course.resources.bussiness.CourseFrontListInfoBusiness;
import cn.kxy.setting.bussiness.ClassificationBusines;
import cn.kxy.setting.bussiness.UserBusiness;
import init.cases.InitStudyAuthCourse;
import com.alibaba.fastjson.JSONPath;
import com.lazy.common.utils.CommonData;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestFunctionCourseFrontList extends InitStudyAuthCourse {
	String course_name= "Linux"+CommonData.getStringRandom(5);
	String courseId = "";
	@Test(description="修改企业课程分类",priority=1)
	public void testChangeCourseClassify() {
		System.out.println(course_name);
		String addCourse = CourseBusiness.addCourse(course_name, "1", "this is desription", "", 
				ArticleBusiness.getIdByKeyword(""), "0", "2", UserBusiness.getUserId(), "0", "3", "release");
		courseId = (String)JSONPath.read(addCourse, "$.data[0]");
		String res = CourseFrontListInfoBusiness.changeCourseClassify(courseId, ClassificationBusines.getSecondaryId());
		String msg = (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "修改企业课程分类成功","修改企业课程分类实际返回结果："+res);
	}
	@Test(description="修改课程学分",priority = 2)
	public void testCreditCourse() {
		String res = CourseFrontListInfoBusiness.creditCourse(courseId, "6");
		String msg = (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "更新学分成功","修改企业课程学分实际返回结果："+res);
	}
	@Test(description="顶置课程",priority = 3)
	public void testRecommendSetCourse() {
		String res = CourseFrontListInfoBusiness.recommendSetCourse(courseId);
		String msg = (String)JSONPath.read(res, "$.msg");
		Assert.assertTrue(msg.contains("re"),"顶置课程实际返回结果："+res);
	}
	@Test(description="对课程进行取消顶置操作",priority = 4)
	public void testRecommendRemoveCourse() {
		String res = CourseFrontListInfoBusiness.recommendRemoveCourse(courseId);
		String msg = (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "recommend sucess","对课程进行取消顶置操作实际返回结果："+res);
	}
	
	@Test(description = "删除课件",priority = 5)
	public void end() {
		CourseBusiness.deleteCourse(courseId);
	}
	
}
