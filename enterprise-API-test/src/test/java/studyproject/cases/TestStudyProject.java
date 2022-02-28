package studyproject.cases;

import cn.kxy.authentication.business.CertificateBusiness;
import cn.kxy.base.business.BaseBusiness;
import cn.kxy.course.resources.bussiness.CourseBusiness;
import cn.kxy.examination.business.PaperBusiness;
import cn.kxy.investigationresearch.business.QuestionnaireBusiness;
import cn.kxy.setting.bussiness.ClassificationBusines;
import cn.kxy.setting.bussiness.UserBusiness;
import cn.kxy.study.business.StudyProjectBusiness;
import cn.kxy.study.business.StudyProjectNewBusinesss;
import init.cases.InitStudyAuthCourse;
import com.alibaba.fastjson.JSONPath;
import com.lazy.common.utils.CommonData;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestStudyProject extends InitStudyAuthCourse {

	String study_name = "Study_Project" + CommonData.getStringRandom(5);

	String user_name = UserBusiness.getUsername();
	String user_id = UserBusiness.getUserId();
	String classification_id = ClassificationBusines.getPrimaryId();
	String id = "";
	String mon_id = "";
	@Test(description = "新增学习项目", priority = 1)
	public void testAddStudyProject() {
		String res = StudyProjectNewBusinesss.addStudyProject(study_name, classification_id,
				PaperBusiness.getIdByKeyword(BaseBusiness.pass_paper_name),
				CertificateBusiness.getIdByKeyword(cert_name),
				CourseBusiness.getIdByPage(course_name),
				QuestionnaireBusiness.getIdByKeyword(BaseBusiness.questionnaireName, "release"));
		id= (String)JSONPath.read(res, "$.id");
		mon_id = (String)JSONPath.read(res, "$.course_id");
	}

	@Test(description = "查看学习项目列表", priority = 2)
	public void testQueryLearningProjectList() {
		String res = StudyProjectBusiness.queryLearningProjectList(study_name, "");
		String classify_name = (String) JSONPath.read(res, "$.list[0].classify_name");
		Assert.assertNotNull(classify_name, "查看学习项目列表，分类进行校验：" + res);
	}

	@Test(description = "将学习项目的可见范围设置为全部可见", priority = 3)
	public void testSetAllVisible() {
		String res = StudyProjectBusiness.setVisible(id, "1", "");
		String update = (String) JSONPath.read(res, "$.update");
		String list_res = StudyProjectBusiness.queryLearningProjectList(study_name, classification_id);
		int is_all = (int) JSONPath.read(list_res, "$.list[0].is_all");
		Assert.assertEquals(update, "true", "将学习项目的可见范围设置为全部可见,实际返回结果：：" + res);
		Assert.assertEquals(is_all, 1, "将学习项目的可见范围设置为全部可见,查看学习项目列表，可见范围进行校验：" + res);
	}

	@Test(description = "将学习项目的可见范围设置为仅自己可见", priority = 4)
	public void testSetOnlySeeMeVisible() {
		String res = StudyProjectBusiness.setVisible(id, "3", "");
		String update = (String) JSONPath.read(res, "$.update");
		String list_res = StudyProjectBusiness.queryLearningProjectList(study_name, classification_id);
		int is_all = (int) JSONPath.read(list_res, "$.list[0].is_all");
		Assert.assertEquals(update, "true", "将学习项目的可见范围设置为仅自己可见,实际返回结果：：" + res);
		Assert.assertEquals(is_all, 3, "将学习项目的可见范围设置为全部可见,查看学习项目列表，可见范围进行校验：" + res);
	}

	@Test(description = "将学习项目的可见范围设置为部门学员可见", priority = 5)
	public void testSetUserVisible() {
		String res = StudyProjectBusiness.setVisible(id, "2", user_id);
		String update = (String) JSONPath.read(res, "$.update");
		String list_res = StudyProjectBusiness.queryLearningProjectList(study_name, classification_id);
		int is_all = (int) JSONPath.read(list_res, "$.list[0].is_all");
		Assert.assertEquals(update, "true", "将学习项目的可见范围设置为部门学员可见,实际返回结果：：" + res);
		Assert.assertEquals(is_all, 2, "将学习项目的可见范围设置为部门学员可见,查看学习项目列表，可见范围进行校验：" + list_res);
	}

	@Test(description = "批量设置学习项目的可见范围为全部可见", priority = 6)
	public void testBatchSetStudyProjectAllVisible() {
		String res = StudyProjectBusiness.batchSetStudyProjectVisible("1", "", id);
		String update = (String) JSONPath.read(res, "$.update");
		String list_res = StudyProjectBusiness.queryLearningProjectList(study_name, classification_id);
		int is_all = (int) JSONPath.read(list_res, "$.list[0].is_all");
		Assert.assertEquals(update, "true", "批量设置学习项目的可见范围为全部可见,实际返回结果：：" + res);
		Assert.assertEquals(is_all, 1, "批量设置学习项目的可见范围为全部可见,查看学习项目列表，可见范围进行校验：" + list_res);
	}

	@Test(description = "批量设置学习项目的可见范围为部分可见", priority = 7)
	public void testBatchSetStudyProjectUserVisible() {
		String res = StudyProjectBusiness.batchSetStudyProjectVisible("2", user_id, id);
		String update = (String) JSONPath.read(res, "$.update");
		String list_res = StudyProjectBusiness.queryLearningProjectList(study_name, classification_id);
		int is_all = (int) JSONPath.read(list_res, "$.list[0].is_all");
		Assert.assertEquals(update, "true", "批量设置学习项目的可见范围为部分可见,实际返回结果：：" + res);
		Assert.assertEquals(is_all, 2, "批量设置学习项目的可见范围为部分可见,查看学习项目列表，可见范围进行校验：" + list_res);
	}

	@Test(description = "批量设置学习项目的可见范围为仅自己可见", priority = 8)
	public void testBatchSetStudyProjectByMyseltVisible() {
		String res = StudyProjectBusiness.batchSetStudyProjectVisible("3", "", id);
		String update = (String) JSONPath.read(res, "$.update");
		String list_res = StudyProjectBusiness.queryLearningProjectList(study_name, classification_id);
		int is_all = (int) JSONPath.read(list_res, "$.list[0].is_all");
		Assert.assertEquals(update, "true", "批量设置学习项目的可见范围为仅自己可见,实际返回结果：：" + res);
		Assert.assertEquals(is_all, 3, "批量设置学习项目的可见范围为仅自己可见,查看学习项目列表，可见范围进行校验：" + list_res);
	}
	@Test(description = "查看学习项目日志", priority = 9)
	public void testQueryStudyAuditsLog() {
//		String res = StudyProjectBusiness.queryStudyAuditsLog(id);
//		String message = (String) JSONPath.read(res, "$.audits[0].message");
//		String user_name = (String) JSONPath.read(res, "$.audits[0].user_name");
//		String event_time = (String) JSONPath.read(res, "$.audits[0].event_time");
//		Assert.assertNotNull(message, "查看学习项目日志，message校验，实际返回结果：" + res);
//		Assert.assertNotNull(user_name, "查看学习项目日志，操作人校验，实际返回结果：" + res);
//		Assert.assertNotNull(event_time, "查看学习项目日志，事件时间校验，实际返回结果：" + res);
	}

	@Test(description = "查看学习项目详情", priority = 9)
	public void testQueryResource() {
		String res = StudyProjectBusiness.queryInfo(id);
		String title = (String) JSONPath.read(res, "$.title");
		Assert.assertNotNull(title,  "查看学习项目详情，标题进行校验：" + res);
	}

	@Test(description = "检查资源check_resource接口", priority = 10)
	public void testCheckResource() {
		String res = StudyProjectBusiness.checkResource(id);
		String title = (String) JSONPath.read(res, "$.title");
		Assert.assertNotNull(title,  "检查资源check_resource接口，标题进行校验：" + res);
	}

	@Test(description = "启用学习项目", priority = 11)
	public void testUpdateOpenStatus() {
		String res = StudyProjectBusiness.updateStatus(id, "normal");
		String update = (String) JSONPath.read(res, "$.update");
		Assert.assertEquals(update, "true", "启用学习项目后,实际返回结果：" + res);
	}

	@Test(description = "启用学习项目后，查看学习项目列表", priority = 12)
	public void testQueryStudyOpenProjectList() {
		String list_res = StudyProjectBusiness.queryLearningProjectList(study_name, classification_id);
		String status = (String) JSONPath.read(list_res, "$.list[0].status");
		Assert.assertEquals(status, "normal", "启用学习项目后，查看学习项目列表，状态进行校验：" + list_res);
	}

	@Test(description = "停用学习项目", priority = 13)
	public void testUpdateCloeStatus() {
		String res = StudyProjectBusiness.updateStatus(id, "disabled");
		String update = (String) JSONPath.read(res, "$.update");
		Assert.assertEquals(update, "true", "停用学习项目后,实际返回结果：" + res);

	}

	@Test(description = "停用学习项目后，查看学习项目列表", priority = 14)
	public void testQueryStudyCloseProjectList() {
		String list_res = StudyProjectBusiness.queryLearningProjectList(study_name, classification_id);
		String status = (String) JSONPath.read(list_res, "$.list[0].status");
		Assert.assertEquals(status, "disabled", "停用学习项目后，查看学习项目列表，状态进行校验：" + list_res);
	}

	@Test(description = "推送学习项目", priority = 15)
	public void testPushMessage() {
		String res = StudyProjectBusiness.pushMessage(id);
		String result = (String) JSONPath.read(res, "$.result");
		Assert.assertEquals(result, "true", "推送学习项目后,实际返回结果：" + res);
	}

	@Test(description = "删除学习项目", priority = 16)
	public void testDeleteStudyProject() {
		String res = StudyProjectBusiness.deleteStudyProject(id);
		String deleted = (String) JSONPath.read(res, "$.deleted");
		Assert.assertEquals(deleted, "true", "删除学习项目,实际返回结果：" + res);
	}
	
	@Test(description = "查询不同状态下的学习项目",priority = 17,dataProvider = "statusKey")
	public void queryLearningProjectList(String type,String status,String exp) {
		String res = StudyProjectBusiness.queryLearningProjectList(status);
		Assert.assertTrue(res.contains(exp),""+res);
	}
	
	@DataProvider(name = "statusKey")
	public Object[][] statusKey() {
		Object[][] obj = new Object[][] { 
			{"查看全部","","total"},{"查看已发布","normal","total"},
			{"查看未发布","disabled","total"},{"草稿","draft","total"}

			};
		return obj;
	}
}
