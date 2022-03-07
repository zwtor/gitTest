package extremely.cases;

import cn.kxy.authentication.business.CertificateBusiness;
import cn.kxy.authentication.business.PostAuthenticationBusiness;
import cn.kxy.base.business.BaseBusiness;
import cn.kxy.course.resources.bussiness.ArticleBusiness;
import cn.kxy.course.resources.bussiness.CourseBusiness;
import cn.kxy.examination.business.*;
import cn.kxy.investigationresearch.business.QuestionnaireBusiness;
import cn.kxy.setting.bussiness.ClassificationBusines;
import cn.kxy.setting.bussiness.UserBusiness;
import cn.kxy.study.business.StudyProjectBusiness;
import cn.kxy.study.business.StudyProjectNewBusinesss;
import cn.kxy.study.business.StudyTaskBusiness;
import cn.kxy.study.business.TimerStudyTaskTemplate;
import init.cases.InitStudyAuthCourse;

import com.alibaba.fastjson.JSONPath;
import com.lazy.common.utils.CommonData;
import com.lazy.common.utils.DateUtil;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestQuestionBankByStudyProjectDelete extends InitStudyAuthCourse {

	String question_bank_name = "DeleteQuestionBank"+CommonData.getStringRandom(3);
	String question_bank_id = "";
	String sinagleChoice_title_name = "DeleteSinagleChoice"+CommonData.getStringRandom(5);
	String question_id = "";
	String study_name  ="DeleteStudyProjet"+CommonData.getStringRandom(5);
	String study_project_id = "";
	@Test(description="新建待删除的题库",priority=1)
	public void testAddQuestionBank () {
		ExaminationBusines.createQuestionBank(question_bank_name, "1", "", "");
		String res = ExaminationBusines.queryQuestionBankList(question_bank_name, "false", "");
		question_bank_id = (String)JSONPath.read(res, "$.data.list[0].id");
		ExamTestQuestionsBusiness.addQuestions(sinagleChoice_title_name, question_bank_id,"1", "1", "analysis01", "knowledgepoint-jmeter", "", "",
				"[{\"title\":\"one\",\"id\":\"\"},{\"title\":\"two\",\"isAnswer\":true,\"id\":\"\"}]");
	}
	@Test(description="获取试题id",priority=2)
	public void testGetId () {
		String res_list = ExamTestQuestionsBusiness.getQuestionsList(sinagleChoice_title_name, question_bank_id, "false", "");
		question_id = (String)JSONPath.read(res_list, "$.list[0].id");
	}
	
	String timer_exam_id = "";
	@Test(description="新增随机考试的定时考试任务",priority=3)
	public void testAddRandomTimerExam() {
		String title = "RandomExam"+CommonData.getStringRandom(5);
		String res  = TimerExamTaskBusiness.addRandomExam(title, question_bank_id,"false");
		System.out.println("添加随机考试的定时考试任务"+res);
		timer_exam_id = TimerExamTaskBusiness.getIdByKeyword(title);
	}
	
	String qualificationsId = "";
	@Test(description="新增随机考试的岗位认证",priority=4)
	public void testAddPostAuthRandomExam () {
		String title = "DeletePost"+CommonData.getStringRandom(5);
		PostAuthenticationBusiness.addPostAuthRandomExam(title,  question_bank_id);
		String res = PostAuthenticationBusiness.queryList(title, "0");
		qualificationsId = (String)JSONPath.read(res, "$.list[0].id");
	}
	
	String random_exam_id = "";
	@Test(description="新增随机考试的考试任务",priority=5)
	public void testCreatRandomExamTask() {
		String random_exam_task_name = "DeleteRandomExam"+CommonData.getStringRandom(5);
		String res = ExaminationTaskBusiness.creatRandomExamTask("2", "show", "20", "1", "40", "0", "0", "0", "0", "0", "0", 
				"0", "0", question_bank_id, "2", random_exam_task_name, DateUtil.getRegularDate(0), DateUtil.getRegularDate(1), 
				"false", UserBusiness.getUserId(), "45", "0", "0", "1", CertificateBusiness.getIdByKeyword(BaseBusiness.certificate_name), "20", "0", "0", "0", "0",
				"{\"missScore\":0,\"passScore\":5,\"unPassScore\":0}", "true", "false", UserBusiness.getUserId(),"false");
		String msg = (String)JSONPath.read(res, "$.msg");
		random_exam_id = ExaminationTaskBusiness.getIdByName(random_exam_task_name);
		Assert.assertEquals(msg, "新增考试成功！","新增随机考试任务实际返回结果："+res);
	}
	
	String timer_study_id = "";
	@Test(description="添加随机考试的定时学习任务",priority=6)
	public void testAddRandomExam () {
		String title = "TimerRandomExam"+CommonData.getStringRandom(5);
		String addRandomExam = TimerStudyTaskTemplate.addRandomExam(title, question_bank_id);
		System.out.println("添加随机考试的定时学习任务"+addRandomExam);
		String res = TimerStudyTaskTemplate.queryList(title, "all");
		timer_study_id = (String)JSONPath.read(res,"$.list[0].id");
	}
	
	@Test(description="添加随机考试的学习项目",priority=7)
	public void testAddRondomExamStudyProject() {
//		String res = StudyProjectNewBusinesss.addStudyProject(study_name, ClassificationBusines.getPrimaryId(),
//				PaperBusiness.getIdByKeyword(BaseBusiness.pass_paper_name),
//				CertificateBusiness.getIdByKeyword(cert_name), ArticleBusiness.getIdByKeyword(articlename),
//				CourseBusiness.getIdByPage(course_name),
//				QuestionnaireBusiness.getIdByKeyword(BaseBusiness.questionnaireName, "release"));
//		study_project_id = (String) JSONPath.read(res, "$.id");
//		Assert.assertNotNull(study_project_id, "添加随机考试的学习项目" + res);
	}
	
	String study_id = "";
	@Test(description="添加随机考试的学习任务",priority=8)
	public void testAddRandomExamToStudyTask() {
//		String title = "RandomStudy"+CommonData.getStringRandom(5);
//		StudyTaskBusiness.addRandomExam(title, question_bank_id);
//		String res = StudyTaskBusiness.getStudyTaskList(title, "true", "0", "");
//		study_id= (String)JSONPath.read(res, "$.list[0].id");
	}
	
	
	@Test(description="删除引用题库的学习项目",priority=9)
	public void testDeleteStudyProject() {
		String res = StudyProjectBusiness.deleteStudyProject(study_project_id);
		String deleted = (String) JSONPath.read(res, "$.deleted");
		Assert.assertEquals(deleted, "true", "删除学习项目,实际返回结果：" + res);
	}
	
	@Test(description="删除题库下的试题",priority=10)
	public void testDeleteQuestions () {
		String res = ExamTestQuestionsBusiness.deleteQuestions(question_id);
		String msg = (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "试题删除成功","删除题库下的试题，提示语校验"+res);
	}

	@Test(description="删除引用题库的学习任务，再次删除题库，提示语校验",priority=11)
	public void testDeleteStudyTask() {
		StudyTaskBusiness.deleteStudyTask(study_id);
		String res= ExaminationBusines.deleteQuestionBank(question_bank_id);
		String msg = (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "题库被考试引用，请先删除考试再删除题库","删除引用题库的学习任务，再次删除题库，提示语校验"+res);
	}
	
	@Test(description="删除引用题库的定时学习任务，再次删除题库，提示语校验",priority=12)
	public void testDeleteTimerStudy() {
		TimerStudyTaskTemplate.deleteTimerStudy(timer_study_id);
		String res = ExaminationBusines.deleteQuestionBank(question_bank_id);
		String msg = (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "题库被考试引用，请先删除考试再删除题库","删除引用题库的定时学习任务，再次删除题库，提示语校验"+res);
	}
	
	@Test(priority=13,description="删除引用题库的考试任务，再次删除题库，提示语校验")
	public void testDeleteExamTask() {
		ExaminationTaskBusiness.deleteExamTask(random_exam_id);
		String res = ExaminationBusines.deleteQuestionBank(question_bank_id);
		String msg = (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "题库被任务、认证或学习项目引用，请先删除考试再删除题库","删除引用题库的考试任务，再次删除题库，提示语校验"+res);
	}

	@Test(description="删除引用题库的岗位认证，再次删除题库，提示语校验",priority=14)
	public void testDeleteAuthentication() {
		PostAuthenticationBusiness.deleteAuthentication(qualificationsId);
		String res = ExaminationBusines.deleteQuestionBank(question_bank_id);
		String msg = (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "题库被定时考试模板引用，请先删除考试再删除题库","删除引用题库的岗位认证，再次删除题库，提示语校验"+res);
	}
	
	@Test(description="试题被引用的项目删除后，删除题库",priority=15)
	public void testDeleteQuestionBank () {
		TimerExamTaskBusiness.deleteTimerExamTask(timer_exam_id);
		String res = ExaminationBusines.deleteQuestionBank(question_bank_id);
		String msg = (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "删除成功","试题被引用的项目删除后，删除题库"+res);
	}
}
