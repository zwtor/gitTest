package studyproject.cases;

import cn.kxy.course.resources.bussiness.AppCourseBusiness;
import cn.kxy.course.resources.bussiness.ArticleBusiness;
import cn.kxy.study.business.AppStudyProjectBusiness;
import cn.kxy.study.business.MySelfStudyBusiness;
import cn.kxy.study.business.StudyProjectBusiness;
import init.cases.InitStudyAuthCourse;
import com.alibaba.fastjson.JSONPath;
import com.lazy.common.utils.CommonData;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test(groups = {"studyProject"})
public class TestAppAddStudyProject {

	String title = "AppStudyProject"+CommonData.getStringRandom(5);
	String course_id = "";
	String id = "";
	@Test(description= "移动端新增学习项目",priority = 1)
	public void testAddAppStudyProject() {
		AppStudyProjectBusiness.addAppStudyProject(title, ArticleBusiness.getIdByKeyword(InitStudyAuthCourse.articlename));
		String queryAppCoursesList = AppCourseBusiness.queryAppCoursesList("",title);
		course_id = (String)JSONPath.read(queryAppCoursesList, "$.list[0].id");
		id = (String)JSONPath.read(queryAppCoursesList, "$.list[0].project_id");
	}
	@Test(description = "在App列表页查看学习项目",priority = 2)
	public void queryAppCoursesList() {
		String res = AppCourseBusiness.queryAppCoursesList("", title);
		String title_1 = (String)JSONPath.read(res, "$.list[0].title");
		int type = (int)JSONPath.read(res, "$.list[0].biz_type");
		Assert.assertEquals(type, 3,"查看app端课程列表实际返回结果："+res);
		Assert.assertEquals(title_1, title,"查看app端课程列表实际返回结果："+res);
	}
	String stage_id = "";
	@Test(description = "查看移动端新建的学习项目详情",priority = 3)
	public void queryStudyCourseInfo() {
		String res = MySelfStudyBusiness.queryStudyCourseInfo(course_id);
		stage_id = (String)JSONPath.read(res, "$.stages[0].resources[0].course_id");
		String title_1 = (String)JSONPath.read(res, "$.title");
		Assert.assertEquals(title_1, title,"查看移动端新建的学习项目详情,实际返回结果："+res);
	}
	
	@Test(description = "开始学习", priority = 4)
	public void testStartStudy() {
		String res = AppStudyProjectBusiness.saveProess(course_id,stage_id,"study");
		Assert.assertTrue(res.contains("true"),"开始学习"+res);
	}
	
	@Test(description = "保存学习项目的进度",priority = 5)
	public void testSaveProess() {
		String res = AppStudyProjectBusiness.saveProess(course_id,stage_id,"stop");
		Assert.assertTrue(res.contains("true"),"保存学习项目的进度"+res);
	}
	@Test(description = "学习完成后查看进度",priority = 6)
	public void testqueryStudyCourseInfo() {
		String res = AppStudyProjectBusiness.queryCourseInfo(course_id);
		int progress  =  (int)JSONPath.read(res, "$.progress");
		Assert.assertEquals(progress, 100, "学习完成后查看进度,实际返回结果：" + res);
	}
	
	@Test(description = "删除学习项目", priority = 10)
	public void testDeleteStudyProject() {
		 StudyProjectBusiness.deleteStudyProject(id);
//		String deleted = (String) JSONPath.read(res, "$.deleted");
//		Assert.assertEquals(deleted, "true", "删除学习项目,实际返回结果：" + res);
	}
	
}
