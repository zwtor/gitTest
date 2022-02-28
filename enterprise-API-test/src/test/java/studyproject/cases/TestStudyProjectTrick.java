package studyproject.cases;

import cn.kxy.course.resources.bussiness.ArticleBusiness;
import cn.kxy.study.business.StudyProjectBusiness;
import init.cases.InitStudyAuthCourse;
import com.alibaba.fastjson.JSONPath;
import com.lazy.common.utils.CommonData;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestStudyProjectTrick extends InitStudyAuthCourse{

	String name = "StudyProjectTrick"+CommonData.getStringRandom(5);
	String art_name_01 = "ProjectHistory"+CommonData.getStringRandom(5);
	String art_name_02 = "ProjectEnglish"+CommonData.getStringRandom(5);
	String id = "";
	String art_id_01 = "";
	String art_id_02 = "";
	String art_id_03 = "";
	@Test(description = "添加供学习任务使用的课件",priority = 1)
	public void testAddArticle_01() {
		ArticleBusiness.addArticle(art_name_01,"This is a description","0");
	}
	@Test(description = "添加供学习任务使用的课件",priority = 2)
	public void testAddArticle_02() {
		ArticleBusiness.addArticle(art_name_02,"This is a description","0");
	}
	@Test(description = "获取第一个课件Id",priority = 3)
	public void testGetFirstId() {
		 art_id_01 = ArticleBusiness.getIdByKeyword(art_name_01);
	}
	@Test(description = "获取第二个课件Id",priority = 4)
	public void testGetSecondId() {
		 art_id_02 = ArticleBusiness.getIdByKeyword(art_name_02);
	}
	@Test(description = "获取第三个课件Id",priority = 5)
	public void testGetThirdId() {
		 art_id_03 = ArticleBusiness.getIdByKeyword(articlename);
	}
	@Test(description = "添加阶段间闯关的学习项目",priority = 6)
	public void testAddStageProject () {
		StudyProjectBusiness.addStageProject(name, art_id_01, art_id_02, art_id_03);
	}
	@Test(description = "获取学习项目的id，并启用学习项目",priority = 7)
	public void updateStudyProjectStatus() {
		String res = StudyProjectBusiness.queryLearningProjectList(name, "");
		id = (String) JSONPath.read(res, "$.list[0].id");
		StudyProjectBusiness.updateStatus(id, "normal");
	}
	String course_id = "";
	@Test(description = "查询课程详情页，检查每个任务是否可以学习",priority = 8)
	public void testQueryProjectCourse() {
//		String list_res = CourseBusiness.queryCourseByPage(name);
//		course_id= (String)JSONPath.read(list_res, "$.list[0].id");
//		String res = StudyProjectBusiness.queryProjectCourse(course_id);
//		String first_can = (String)JSONPath.read(res, "$.stages[0].resources[0].can_start_study");
//		String second_can = (String)JSONPath.read(res, "$.stages[0].resources[1].can_start_study");
//		String third_can = (String)JSONPath.read(res, "$.stages[1].resources[0].can_start_study");
//		Assert.assertEquals(first_can, "true","查询课程详情页,验证第一阶段的第一个任务可以学习，实际返回结果："+res);
//		Assert.assertEquals(second_can, "false","查询课程详情页,验证第一阶段的第二个任务不可以学习，实际返回结果："+res);
//		Assert.assertEquals(third_can, "false","查询课程详情页,验证第二阶段的第一个任务不可以学习，实际返回结果："+res);
	}
	@Test(description = "保存第一阶段的第一个任务的进度",priority = 9)
	public void testSaveProjectCouseFirstStageProcess() {
//		StudyProjectBusiness.loadProject(course_id);
//		StudyProjectBusiness.startStudy(course_id);
//		String res = StudyProjectBusiness.saveProjectCouseProcess(course_id, art_id_01);
//		int progress = (int)JSONPath.read(res, "$.progress");
//		Assert.assertEquals(progress, 100,"保存第一阶段的第一个任务的进度，实际返回结果："+res);
	}
	@Test(description = "保存第一阶段的第一个任务的进度,查看课程详情",priority = 10)
	public void testQueryFirstProjectCourse() {
//		String res = StudyProjectBusiness.queryProjectCourse(course_id);
//		String first_can = (String)JSONPath.read(res, "$.stages[0].resources[0].can_start_study");
//		String second_can = (String)JSONPath.read(res, "$.stages[0].resources[1].can_start_study");
//		String third_can = (String)JSONPath.read(res, "$.stages[1].resources[0].can_start_study");
//		Assert.assertEquals(first_can, "true","查询课程详情页,验证第一阶段的第一个任务可以学习，实际返回结果："+res);
//		Assert.assertEquals(second_can, "true","查询课程详情页,验证第一阶段的第二个任务不可以学习，实际返回结果："+res);
//		Assert.assertEquals(third_can, "false","查询课程详情页,验证第二阶段的第一个任务不可以学习，实际返回结果："+res);
	}
	@Test(description = "保存第一阶段的第二个任务的进度",priority = 11)
	public void testSaveProjectCouseProcess() {
//		String res = StudyProjectBusiness.saveProjectCouseProcess(course_id, art_id_02);
//		int progress = (int)JSONPath.read(res, "$.progress");
//		Assert.assertEquals(progress, 100,"保存第一阶段的第二个任务的进度，实际返回结果："+res);
	}
	@Test(description = "保存第一阶段的第二个任务的进度,查看课程详情",priority = 12)
	public void testQueryProjectSecondCourse() {
//		String res = StudyProjectBusiness.queryProjectCourse(course_id);
//	
//		String first_can = (String)JSONPath.read(res, "$.stages[0].resources[0].can_start_study");
//		String second_can = (String)JSONPath.read(res, "$.stages[0].resources[1].can_start_study");
//		String third_can = (String)JSONPath.read(res, "$.stages[1].resources[0].can_start_study");
//		Assert.assertEquals(first_can, "true","查询课程详情页,验证第一阶段的第一个任务可以学习，实际返回结果："+res);
//		Assert.assertEquals(second_can, "true","查询课程详情页,验证第一阶段的第二个任务可以学习，实际返回结果："+res);
//		Assert.assertEquals(third_can, "true","查询课程详情页,验证第二阶段的第一个任务不可以学习，实际返回结果："+res);

	}
	
	@Test(description = "保存第二阶段的第一个任务的进度",priority = 13)
	public void testSaveProjectCouseSecondStageProcess() {
//		String res = StudyProjectBusiness.saveProjectCouseProcess(course_id, art_id_03);
//		int progress = (int)JSONPath.read(res, "$.progress");
//		Assert.assertEquals(progress, 100,"保存第二阶段的第一个任务的进度，实际返回结果："+res);
	}
	@Test(description = "保存第二阶段的第一个任务的进度,查看课程详情",priority = 14)
	public void testQueryProjectSecondStageCourse() {
//		String res = StudyProjectBusiness.queryProjectCourse(course_id);
//		String first_can = (String)JSONPath.read(res, "$.stages[0].resources[0].can_start_study");
//		String second_can = (String)JSONPath.read(res, "$.stages[0].resources[1].can_start_study");
//		String third_can = (String)JSONPath.read(res, "$.stages[1].resources[0].can_start_study");
//		Assert.assertEquals(first_can, "true","查询课程详情页,验证第一阶段的第一个任务可以学习，实际返回结果："+res);
//		Assert.assertEquals(second_can, "true","查询课程详情页,验证第一阶段的第二个任务可以学习，实际返回结果："+res);
//		Assert.assertEquals(third_can, "true","查询课程详情页,验证第二阶段的第一个任务可以学习，实际返回结果："+res);

	}
	String study_id = "";
	String assign_name = "AssignTrickStudy" + CommonData.getStringRandom(3);
	@Test(description = "阶段间闯关指派到学习任务",priority = 15)
	public void testAssignStudyProject () {
//		String res = StudyProjectBusiness.assignStudyProject(id, assign_name, DateUtil.getTimeStamp(0, ""),
//				DateUtil.getTimeStamp(5, ""));
//		String id = (String) JSONPath.read(res, "$.id");
//		Assert.assertNotNull(id, "指派学习项目，实际返回结果：" + res);
	}
	
	@Test(description = "获取学习项目指派学习任务后的id",priority = 16)
	public void testGetStudyId() {
//		String res = StudyTaskBusiness.getStudyTaskList(assign_name, "true", "0", "");
//		study_id = (String)JSONPath.read(res, "$.list[0].id");
	}
	
	@Test(description = "查看学习项目指派的学习任务的详情",priority = 17)
	public void testQueryInfo() {
//		String res = MyStudyTask.queryInfo(study_id);
//		String first_can = (String)JSONPath.read(res, "$.data.stageList[0].courseMappingList[0].canStartStudy");
//		String second_can = (String)JSONPath.read(res, "$.data.stageList[0].courseMappingList[1].canStartStudy");
//		String third_can = (String)JSONPath.read(res, "$.data.stageList[1].canStartStudy");
//		Assert.assertEquals(first_can, "true","查看学习项目指派的学习任务的详情,第一阶段的第一个任务可学习，实际返回结果："+res);
//		Assert.assertEquals(second_can, "false","查看学习项目指派的学习任务的详情,第一阶段的第二个任务可学习，实际返回结果："+res);
//		Assert.assertEquals(third_can, "false","查看学习项目指派的学习任务的详情,第二阶段的第一个任务可学习，实际返回结果："+res);
	}
	
	@Test(description = "删除学习任务",priority = 18)
	public void testDelete() {
//		String res = StudyTaskBusiness.deleteStudyTask(study_id);
//		String msg= (String)JSONPath.read(res, "$.msg");
//		Assert.assertEquals(msg, "删除学习计划成功","删除考试任务，实际返回结果:"+res);
	}
	
	@Test(description = "删除学习项目",priority = 19)
	public void testDeleteStudyProject() {
		StudyProjectBusiness.deleteStudyProject(id);
	}
	@Test(description =  "删除引用的第一个课件",priority = 20)
	public void testDeleteFirstArticle() {
		String del_res = ArticleBusiness.deleteArticle(art_id_01);
		String msg = (String) JSONPath.read(del_res, "$.msg"); 
		Assert.assertEquals(msg,"删除课件成功", "删除文章实际返回结果：" + del_res); 
	}
	@Test(description = "删除引用的第二个课件",priority = 21)
	public void testDeleteSecondArticle() {
		String del_res = ArticleBusiness.deleteArticle(art_id_02);
		String msg = (String) JSONPath.read(del_res, "$.msg"); 
		Assert.assertEquals(msg,"删除课件成功", "删除文章实际返回结果：" + del_res); 
	}
}
