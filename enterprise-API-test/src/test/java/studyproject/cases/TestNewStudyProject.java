package studyproject.cases;

import cn.kxy.base.business.BaseBusiness;
import cn.kxy.course.resources.bussiness.CourseBusiness;
import cn.kxy.examination.business.PaperBusiness;
import cn.kxy.investigationresearch.business.QuestionnaireBusiness;
import cn.kxy.setting.bussiness.ClassificationBusines;
import cn.kxy.study.business.StudyProjectBusiness;
import cn.kxy.study.business.StudyProjectNewBusinesss;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import init.cases.InitStudyAuthCourse;
import com.alibaba.fastjson.JSONPath;
import com.lazy.common.utils.CommonData;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestNewStudyProject extends InitStudyAuthCourse {
	String title = "NewStudyProject"+CommonData.getStringRandom(5);
	String class_id = ClassificationBusines.getPrimaryId();
	String id = "";
	String course_id = "";
	@Test(description = "新增学习项目2.0的基础信息",priority = 1)
	public void testSaveBaseInfo() {
		String res = StudyProjectNewBusinesss.saveBaseInfo(title, class_id);
		id = (String)JSONPath.read(res, "$.id");
		course_id = (String)JSONPath.read(res, "$.course_id");
		Assert.assertTrue(res.contains("true"),"新增学习项目2.0的基础信息"+res);
	}
	
	@Test(description = "保存学习项目阶段的草稿信息",priority = 2)
	public void testSaveStageContentDraft() {
		String res = StudyProjectNewBusinesss.saveStageContentDraft(id);
		Assert.assertTrue(res.contains("true"),"保存学习项目阶段的草稿信息"+res);
	}
	
	@Test(description = "查看学习项目列表",priority = 3)
	public void testQueryLearningProjectList(){
		String response = StudyProjectBusiness.queryLearningProjectList(title, "");
		Assert.assertTrue((Integer) JSONPath.read(response, "$.extend.total_count") > 0, response);
		String status = String.valueOf(JSONPath.read(response, "$.list[0].status"));
		Assert.assertEquals(status, "draft","查看学习项目列表: " + response);
	}
	
	@Test(description= "保存阶段的内容",priority = 4)
	public void testSaveStageContent() {
		
		String res = StudyProjectNewBusinesss.saveStageContent(id,
				CourseBusiness.getIdByPage(course_name), PaperBusiness.getIdByKeyword(BaseBusiness.pass_paper_name), 
				QuestionnaireBusiness.getIdByKeyword(BaseBusiness.questionnaireName, "release"));
		Assert.assertTrue(res.contains("true"),"保存阶段的内容"+res);
	}
	
	@Test(description = "保存学习项目设置的草稿",priority= 5)
	public void testSaveSettingsDraft() {
		String res = StudyProjectNewBusinesss.saveSettingsDraft(id);
		Assert.assertTrue(res.contains("true"),"保存学习项目设置的草稿"+res);
	}
	
	@Test(description = "保存学习项目的设置",priority= 6)
	public void testSaveSetting() {
		String res = StudyProjectNewBusinesss.saveSetting(id);
		Assert.assertTrue(res.contains("true"),"保存学习项目的设置"+res);
	}
	String course_id_01 = "";
	String course_id_02 = "";
	String course_id_03 = "";
	String course_id_04 = "";
	String stage_id = "";
	@Test(description = "查看学习项目详情",priority = 7)
	public void testQueryInfo() {
		String res = StudyProjectBusiness.queryInfo(id);
		course_id_01 = (String)JSONPath.read(res, "$.stages[0].resources[0].course_id");
		course_id_02 = (String)JSONPath.read(res, "$.stages[0].resources[1].course_id");
		course_id_03 = (String)JSONPath.read(res, "$.stages[0].resources[2].course_id");
		course_id_04 = (String)JSONPath.read(res, "$.stages[0].resources[3].course_id");
		stage_id = (String)JSONPath.read(res, "$.stages[0].id");
	}
	String name= "MarketingAS"+CommonData.getStringRandom(5);
	@Test(description = "新增营期管理",priority = 8)
	public void testSaveCampPeriods() {
//		String res = StudyProjectNewBusinesss.saveCampPeriods(id, stage_id,name, DateUtil.getTimeStamp(0, ""),  DateUtil.getTimeStamp(1, ""),  DateUtil.getTimeStamp(2, ""),
//				 DateUtil.getTimeStamp(10, ""),  DateUtil.getTimeStamp(3, ""),  DateUtil.getTimeStamp(9, ""), course_id_01, course_id_02, course_id_03, course_id_04, 
//				 DateUtil.getTimeStamp(5, ""), "false","30", "19");
//		Assert.assertTrue(res.contains("true"));
	}
	
	String cap_id = "";
	@Test(description = "根据开启状态查看营期管理",priority = 9)
	public void testQueryCampPeriodsByStatus() {
//		String res = StudyProjectNewBusinesss.queryCampPeriods(id, "normal", "");
//		cap_id  = (String)JSONPath.read(res, "$.list[0].id");
//		String status = (String)JSONPath.read(res, "$.list[0].status");
//		Assert.assertNotNull(status,"根据名称查看营期管理,实际返回结果："+res);
	}
	
	@Test(description = "删除营期管理",priority = 10)
	public void testDeleteCampPeriods() {
//		String res = StudyProjectNewBusinesss.deleteCampPeriods(cap_id);
//		Assert.assertTrue(res.contains("true"));
	}
	
	@Test(description = "删除学习项目", priority = 11)
	public void testDeleteStudyProject() {
		String res = StudyProjectBusiness.deleteStudyProject(id);
		String deleted = (String) JSONPath.read(res, "$.data.deleted");
		Assert.assertEquals(deleted, "true", "删除学习项目,实际返回结果：" + res);
	}
}
