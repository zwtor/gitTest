package studyproject.cases;

import cn.kxy.setting.bussiness.ClassificationBusines;
import init.cases.InitStudyAuthCourse;
import com.lazy.common.utils.CommonData;
import org.testng.annotations.Test;

public class TestStudyAuthRangeProject extends InitStudyAuthCourse{

	String classification_id = ClassificationBusines.getPrimaryId();
	String title_0 = "AuthRangeProject"+CommonData.getStringRandom(5);
	String id_0 = "";
	String mon_id = "";
	@Test(description = "新增不勾选权限管辖范围的学习项目",priority = 1)
	public void testAddAuthorityRangeProject() {
//		StudyProjectBusiness.addAuthorityRangeProject(title_0, classification_id, 
//				ArticleBusiness.getIdByKeyword(articlename), "false");
	}
	@Test(description = "获取id",priority = 2)
	public void testGetId() {
//		String res = StudyProjectBusiness.queryLearningProjectList(title_0, classification_id);
//		id_0 = (String) JSONPath.read(res, "$.list[0].id");
//		mon_id = (String) JSONPath.read(res, "$.list[0].course_id");
	}
	@Test(description = "学习项目数据监控",priority = 3)
	public void testQueryStudyProjectUserMonitors() {
//		String res = StudyProjectBusiness.queryStudyProjectUserMonitors(mon_id, "", "", "", "", "");
//		String authority_range = (String)JSONPath.read(res, "$.authority_range");
//		Assert.assertEquals(authority_range, "false",""+res);
	}
	
	@Test(description = "查看学习项目详情--不勾选管辖范围",priority = 4)
	public void testQueryFalseInfo() {
//		String res = StudyProjectBusiness.queryInfo(id_0);
//		String authority_range = (String)JSONPath.read(res, "$.authority_range");
//		Assert.assertEquals(authority_range, "false","查看学习项目详情--不勾选管辖范围"+res);
	}
	
	@Test(description = "删除不勾选管辖范围的学习项目", priority = 5)
	public void testDeleteStudyFalseProject() {
//		String res = StudyProjectBusiness.deleteStudyProject(id_0);
//		String deleted = (String) JSONPath.read(res, "$.deleted");
//		Assert.assertEquals(deleted, "true", "删除不勾选管辖范围的学习项目,实际返回结果：" + res);
	}
	
	String title_1 = "AuthRangeProject"+CommonData.getStringRandom(5);
	String id_1 = "";
	String mon_id_1 = "";
	@Test(description = "新增不勾选权限管辖范围的学习项目",priority = 6)
	public void testAddAuthorityRangeProjectTrue() {
//		 StudyProjectBusiness.addAuthorityRangeProject(title_1, classification_id, 
//				ArticleBusiness.getIdByKeyword(articlename), "true");
	}
	@Test(description = "获取id",priority = 7)
	public void testGetIdTrue() {
//		String res = StudyProjectBusiness.queryLearningProjectList(title_1, classification_id);
//		id_1 = (String) JSONPath.read(res, "$.list[0].id");
//		mon_id_1 = (String) JSONPath.read(res, "$.list[0].course_id");
	}
	@Test(description = "学习项目数据监控",priority = 8)
	public void testQueryStudyProjectUserMonitorsTrue() {
//		String res = StudyProjectBusiness.queryStudyProjectUserMonitors(mon_id_1, "", "", "", "", "");
//		String authority_range = (String)JSONPath.read(res, "$.authority_range");
//		Assert.assertEquals(authority_range, "true",""+res);
	}
	
	@Test(description = "查看学习项目详情--不勾选管辖范围",priority = 9)
	public void testQueryTrueInfo() {
//		String res = StudyProjectBusiness.queryInfo(id_1);
//		String authority_range = (String)JSONPath.read(res, "$.authority_range");
//		Assert.assertEquals(authority_range, "true","查看学习项目详情--不勾选管辖范围"+res);
	}
	@Test(description = "删除学习项目", priority = 10)
	public void testDeleteStudyTrueProject() {
//		String res = StudyProjectBusiness.deleteStudyProject(id_1);
//		String deleted = (String) JSONPath.read(res, "$.deleted");
//		Assert.assertEquals(deleted, "true", "删除学习项目,实际返回结果：" + res);
	}
}
