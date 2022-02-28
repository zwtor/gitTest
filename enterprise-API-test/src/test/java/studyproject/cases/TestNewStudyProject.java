package studyproject.cases;

import cn.kxy.setting.bussiness.ClassificationBusines;
import init.cases.InitStudyAuthCourse;
import com.lazy.common.utils.CommonData;
import org.testng.annotations.Test;

public class TestNewStudyProject  extends InitStudyAuthCourse{

	String title = "NewStudyProject"+CommonData.getStringRandom(5);
	String class_id = ClassificationBusines.getPrimaryId();
	String id = "";
	String course_id = "";
	@Test(description = "新增学习项目2.0的基础信息",priority = 1)
	public void testSaveBaseInfo() {
//		String res = StudyProjectNewBusinesss.saveBaseInfo(title, class_id);
//		id = (String)JSONPath.read(res, "$.id");
//		course_id = (String)JSONPath.read(res, "$.course_id");
//		Assert.assertTrue(res.contains("true"),"新增学习项目2.0的基础信息"+res);
	}
	
	@Test(description = "保存学习项目阶段的草稿信息",priority = 2)
	public void testSaveStageContentDraft() {
//		String res = StudyProjectNewBusinesss.saveStageContentDraft(id);
//		Assert.assertTrue(res.contains("true"),"保存学习项目阶段的草稿信息"+res);
	}
	
	@Test(description = "查看学习项目列表",priority = 3)
	public void testQueryLearningProjectList(){
//		String list_res = StudyProjectBusiness.queryLearningProjectList(title, "");
//		String status = (String)JSONPath.read(list_res, "$.list[0].status");
//		Assert.assertEquals(status, "draft","查看学习项目列表"+list_res);
	}
	
	@Test(description= "保存阶段的内容",priority = 4)
	public void testSaveStageContent() {
		
//		String res = StudyProjectNewBusinesss.saveStageContent(id,
//				CourseBusiness.getIdByPage(course_name), PaperBusiness.getIdByKeyword(BaseBusiness.pass_paper_name), 
//				QuestionnaireBusiness.getIdByKeyword(BaseBusiness.questionnaireName, "release"));
//		Assert.assertTrue(res.contains("true"),"保存阶段的内容"+res);
	}
	
	@Test(description = "保存学习项目设置的草稿",priority= 5)
	public void testSaveSettingsDraft() {
//		String res = StudyProjectNewBusinesss.saveSettingsDraft(id);
//		Assert.assertTrue(res.contains("true"),"保存学习项目设置的草稿"+res);
	}
	
	@Test(description = "保存学习项目的设置",priority= 6)
	public void testSaveSetting() {
//		String res = StudyProjectNewBusinesss.saveSetting(id);
//		Assert.assertTrue(res.contains("true"),"保存学习项目的设置"+res);
	}
	String course_id_01 = "";
	String course_id_02 = "";
	String course_id_03 = "";
	String course_id_04 = "";
	String stage_id = "";
	@Test(description = "查看学习项目详情",priority = 7)
	public void testQueryInfo() {
//		String res = StudyProjectBusiness.queryInfo(id);
//		course_id_01 = (String)JSONPath.read(res, "$.stages[0].resources[0].course_id");
//		course_id_02 = (String)JSONPath.read(res, "$.stages[0].resources[1].course_id");
//		course_id_03 = (String)JSONPath.read(res, "$.stages[0].resources[2].course_id");
//		course_id_04 = (String)JSONPath.read(res, "$.stages[0].resources[3].course_id");
//		stage_id = (String)JSONPath.read(res, "$.stages[0].id");
	}
	String name= "Marketing"+CommonData.getStringRandom(5);
	@Test(description = "新增营期管理",priority = 8)
	public void testSaveCampPeriods() {
//		String res = StudyProjectNewBusinesss.saveCampPeriods(id, stage_id,name, DateUtil.getTimeStamp(0, ""),  DateUtil.getTimeStamp(1, ""),  DateUtil.getTimeStamp(2, ""),
//				 DateUtil.getTimeStamp(10, ""),  DateUtil.getTimeStamp(3, ""),  DateUtil.getTimeStamp(9, ""), course_id_01, course_id_02, course_id_03, course_id_04, 
//				 DateUtil.getTimeStamp(5, ""), "true","", "");
//		Assert.assertTrue(res.contains("true"));
	}
	
	@Test(description = "根据名称查看营期管理",priority = 9)
	public void testQueryCampPeriodsByName() {
//		String res = StudyProjectNewBusinesss.queryCampPeriods(id, "", name);
//		String title = (String)JSONPath.read(res, "$.list[0].title");
//		String price = (String)JSONPath.read(res, "$.list[0].preferential_price_str");
//		Long registration_start_time = (Long)JSONPath.read(res, "$.list[0].registration_start_time");
//		Long registration_end_time = (Long)JSONPath.read(res, "$.list[0].registration_end_time");
//		String status = (String)JSONPath.read(res, "$.list[0].status");
//		String create_user_name = (String)JSONPath.read(res, "$.list[0].create_user_name");
//		Assert.assertNotNull(create_user_name,"根据名称查看营期管理,实际返回结果："+res);
//		Assert.assertNotNull(status,"根据名称查看营期管理,实际返回结果："+res);
//		Assert.assertNotNull(registration_start_time,"根据名称查看营期管理,实际返回结果："+res);
//		Assert.assertNotNull(registration_end_time,"根据名称查看营期管理,实际返回结果："+res);
//		Assert.assertNotNull(price,"根据名称查看营期管理,实际返回结果："+res);
//		Assert.assertNotNull(title,"根据名称查看营期管理,实际返回结果："+res);
	}
	
	String cap_id = "";
	@Test(description = "根据开启状态查看营期管理",priority = 10)
	public void testQueryCampPeriodsByStatus() {
//		String res = StudyProjectNewBusinesss.queryCampPeriods(id, "normal", "");
//		cap_id  = (String)JSONPath.read(res, "$.list[0].id");
//		String status = (String)JSONPath.read(res, "$.list[0].status");
//		Assert.assertNotNull(status,"根据名称查看营期管理,实际返回结果："+res);
	}
	
	@Test(description = "关闭营期",priority = 11)
	public void testChangeStatus() {
//		String res = StudyProjectNewBusinesss.changeStatus(cap_id, "disabled");
//		Assert.assertTrue(res.contains("true"));
	}
	
	@Test(description = "根据关闭状态查看营期管理",priority = 12)
	public void testQueryCampPeriodsByDisabledStatus() {
//		String res = StudyProjectNewBusinesss.queryCampPeriods(id, "disabled", "");
//		Integer total = (Integer)JSONPath.read(res, "$.total");
//		Assert.assertNotNull(total,"根据名称查看营期管理,实际返回结果："+res);
	}
	
	@Test(description = "关闭营期",priority = 13)
	public void testChangeStatusnormal() {
//		String res = StudyProjectNewBusinesss.changeStatus(cap_id, "normal");
//		Assert.assertTrue(res.contains("true"));
	}
	
	@Test(description = "存在营期的时候，删除学习项目", priority = 14)
	public void testDeleteStudyProjectExist() {
//		String res = StudyProjectBusiness.deleteStudyProject(id);
//		String message = (String) JSONPath.read(res, "$.message");
//		Assert.assertEquals(message, "Please delete the camp period before trying to delete it", "存在营期的时候删除学习项目,实际返回结果：" + res);
	}
	
	@Test(description = "删除营期管理",priority = 15)
	public void testDeleteCampPeriods() {
//		String res = StudyProjectNewBusinesss.deleteCampPeriods(cap_id);
//		Assert.assertTrue(res.contains("true"));
	}
	
	@Test(description = "删除学习项目", priority = 17)
	public void testDeleteStudyProject() {
//		String res = StudyProjectBusiness.deleteStudyProject(id);
//		String deleted = (String) JSONPath.read(res, "$.deleted");
//		Assert.assertEquals(deleted, "true", "删除学习项目,实际返回结果：" + res);
	}
	
}
