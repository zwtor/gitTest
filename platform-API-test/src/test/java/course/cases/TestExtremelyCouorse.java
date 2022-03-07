package course.cases;

import cn.kxy.course.resources.bussiness.ArticleBusiness;
import cn.kxy.course.resources.bussiness.CourseBusiness;
import cn.kxy.course.resources.bussiness.CourseFrontListInfoBusiness;
import cn.kxy.lecturer.business.LecturerListBusiness;
import cn.kxy.setting.bussiness.ClassificationBusines;
import init.cases.InitStudyAuthCourse;
import com.alibaba.fastjson.JSONPath;
import com.lazy.common.utils.CommonData;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestExtremelyCouorse extends InitStudyAuthCourse {
	String course_name = "MyJAVA" + CommonData.getStringRandom(5);

	@Test(description = "添加课程讲师为null时，校验提示", priority = 1)
	public void testLecturerIsNull() {
		String res = CourseBusiness.addCourse("ag", "1", "this is desription",
				LecturerListBusiness.getIdByKeyword(outer_name), "", "1", "1", "", "1", "3", "draft");
		String msg = (String) JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "新增课程失败", "添加课程讲师为null时，校验提示实际返回结果：" + res);
	}

	@Test(description = "添加课程时，课件资源为null时，校验提示", priority = 2)
	public void testOriginalIsNull() {
//		String res = CourseBusiness.addCourse("ag", "1", "this is desription",
//				LecturerListBusiness.getIdByKeyword(outer_name),
//				ArticleBusiness.getIdByKeyword(articlename), "", "1", "", "1",
//				"3", "draft");
//		String msg = (String) JSONPath.read(res, "$.msg");
//		Assert.assertEquals(msg, "新增课程失败", "添加课程时，课件资源为null时，校验提示实际返回结果：" + res);
	}

	@Test(description = "添加课程可见范围为null时，校验提示", priority = 3)
	public void testIsAllIsNull() {
//		String res = CourseBusiness.addCourse("ag", "1", "this is desription",
//				LecturerListBusiness.getIdByKeyword(outer_name),
//				ArticleBusiness.getIdByKeyword(articlename), "1", "", "", "1",
//				"3", "draft");
//		String msg = (String) JSONPath.read(res, "$.msg");
//		Assert.assertEquals(msg, "新增课程失败", "添加课程可见范围为null时，校验提示实际返回结果：" + res);
	}

	@Test(description = "添加课程是否可下载为null时，校验提示", priority = 4)
	public void testDownloadIsNull() {
//		String res = CourseBusiness.addCourse("ag", "1", "this is desription",
//				LecturerListBusiness.getIdByKeyword(outer_name),
//				ArticleBusiness.getIdByKeyword(articlename), "1", "3", "", "",
//				"3", "draft");
//		String msg = (String) JSONPath.read(res, "$.msg");
//		Assert.assertEquals(msg, "新增课程失败", "添加课程是否可下载为null时，校验提示实际返回结果：" + res);
	}

	@Test(description = "添加课程时，自学学分为null时，校验提示", priority = 5)
	public void testCreditIsNull() {
//		String res = CourseBusiness.addCourse("ag", "1", "this is desription",
//				LecturerListBusiness.getIdByKeyword(outer_name),
//				ArticleBusiness.getIdByKeyword(articlename), "1", "3", "", "1",
//				"", "draft");
//		String msg = (String) JSONPath.read(res, "$.msg");
//		Assert.assertEquals(msg, "新增课程失败", "添加课程时，自学学分为null时，校验提示实际返回结果：" + res);
	}

	@Test(description = "添加课程时，发布状态为null时，校验提示", priority = 6)
	public void testPublicIsNull() {
		String res = CourseBusiness.addCourse("ag", "1", "this is desription",
				LecturerListBusiness.getIdByKeyword(outer_name),
				ArticleBusiness.getIdByKeyword(articlename), "1", "3", "", "1",
				"3", "");
		String msg = (String) JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "新增课程失败", "添加课程时，发布状态为null时，校验提示实际返回结果：" + res);
	}

	@Test(description = "设置分类时，课程id为null", priority = 7)
	public void testSetCourseClassify() {
		String res = CourseBusiness.setCourseClassify("", "");
		String msg = (String) JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "params null", "设置分类时，课程id为null，校验提示实际返回结果：" + res);
	}

	@Test(description = "设置学分时，课程id为null", priority = 8)
	public void testsetCourseScoreIsNull() {
		String res = CourseBusiness.setCourseScore("", "");
		String msg = (String) JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "params null", "设置学分时，课程id为null，校验提示实际返回结果：" + res);
	}

	@Test(description = "设置可见范围时，课程id为null", priority = 9)
	public void testsetCourseVisibleByBatch() {
		String res = CourseBusiness.setCourseVisibleByBatch("", "1", "");
		String msg = (String) JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "params null", "设置可见范围时，课程id为null，校验提示实际返回结果：" + res);
	}

	@Test(description = "编辑课程时，指派范围为null时，校验提示", priority = 10)
	public void testEditOriginalIsNull() {
		
		CourseBusiness.addCourse(course_name, "1", "this is desription", LecturerListBusiness.getIdByKeyword(outer_name), 
				ArticleBusiness.getIdByKeyword(articlename), "1", "1", "", "0", "3", "draft");
		String id = CourseBusiness.getIdByKeyword(course_name, "unreleased");

		String info_res = CourseFrontListInfoBusiness.queryInfo(id);
		String baseCover = (String) JSONPath.read(info_res, "$.baseCover");
		String cover = (String) JSONPath.read(info_res, "$.cover");
		String res = CourseBusiness.editCourse(id, course_name, "this is a introduction", cover, baseCover,
				LecturerListBusiness.getIdByKeyword(outer_name), ClassificationBusines.getPrimaryId(), "", "0", "", "",
				"1", "3", "draft");

		String msg = (String) JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "编辑课程失败", "编辑课程时，指派范围为null时，校验提示实际返回结果：" + res);
	}

	@Test(description = "编辑课程发布状态为null时，校验提示", priority = 11)
	public void testEditIsAllIsNull() {
		String id = CourseBusiness.getIdByKeyword(course_name, "unreleased");

		String info_res = CourseFrontListInfoBusiness.queryInfo(id);
		String baseCover = (String) JSONPath.read(info_res, "$.baseCover");
		String cover = (String) JSONPath.read(info_res, "$.cover");
		String res = CourseBusiness.editCourse(id, course_name, "this is a introduction", cover, baseCover,
				LecturerListBusiness.getIdByKeyword(outer_name), ClassificationBusines.getPrimaryId(), "", "0", "1", "",
				"", "", "");
		String msg = (String) JSONPath.read(res, "$.msg");
		CourseBusiness.deleteCourse(id);
		Assert.assertEquals(msg, "编辑课程失败", "编辑课程发布状态为null时，校验提示实际返回结果：" + res);
	}

}
