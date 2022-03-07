package exam.cases;

import cn.kxy.authentication.business.CertificateBusiness;
import cn.kxy.authentication.business.PostAuthenticationBusiness;
import cn.kxy.base.business.BaseBusiness;
import cn.kxy.examination.business.*;
import cn.kxy.setting.bussiness.UserBusiness;
import cn.kxy.study.business.StudyProjectBusiness;
import cn.lazy.init.cases.InitStudyAuthCourse;
import com.alibaba.fastjson.JSONPath;
import com.lazy.common.utils.CommonData;
import com.lazy.common.utils.DateUtil;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestExtremelyQuestionBank extends InitStudyAuthCourse{
	String question_bank_name = "QuoteQuestionBank"+CommonData.getStringRandom(5);
	String question_bank_id = "";
	String sinagleChoice_title_name = "Selenium"+CommonData.getStringRandom(5);
	String sinagleChoice_title = "Jmeter"+CommonData.getStringRandom(5);
	String question_id01 = "";
	String question_id02 = "";
	String option_title01 = "";
	String option_title02 = "";
	@Test(description="新建待删除的题库",priority=1)
	public void testAddQuestionBank () {
		ExaminationBusines.createQuestionBank(question_bank_name, "1", "", "");
		String res = ExaminationBusines.queryQuestionBankList(question_bank_name, "false", "");
		question_bank_id = (String)JSONPath.read(res, "$.data.list[0].id");
		ExamTestQuestionsBusiness.addQuestions(sinagleChoice_title_name, question_bank_id,"1", "1", "selenium", "knowledgepoint-jmeter", "", "",
				"[{\"title\":\"driver\",\"id\":\"\"},{\"title\":\"close\",\"isAnswer\":true,\"id\":\"\"}]");
		
		ExamTestQuestionsBusiness.addQuestions(sinagleChoice_title, question_bank_id,"1", "1", "jmeter", "knowledgepoint-jmeter", "", "",
				"[{\"title\":\"Thread\",\"id\":\"\"},{\"title\":\"http sample\",\"isAnswer\":true,\"id\":\"\"}]");
	}
	
	@Test(description="获取试题id",priority=2)
	public void testGetId () {
		String res_list = ExamTestQuestionsBusiness.getQuestionsList(sinagleChoice_title_name, question_bank_id, "false", "");
		question_id01 = (String)JSONPath.read(res_list, "$.list[0].id");
		
		String list = ExamTestQuestionsBusiness.getQuestionsList(sinagleChoice_title, question_bank_id, "false", "");
		question_id02 = (String)JSONPath.read(list, "$.list[0].id");
	}
	
	String timer_exam_id = "";
	@Test(description="新增随机考试的定时考试任务",priority=3)
	public void testAddRandomTimerExam() {
		String title = "QuoteRandomExam"+CommonData.getStringRandom(5);
		TimerExamTaskBusiness.addRandomExam(title, question_bank_id,"false");
		timer_exam_id = TimerExamTaskBusiness.getIdByKeyword(title);
	}
	
	@Test(description="获取试题的选项id",priority=4)
	public void testgetQuestionsInfo() {
		String res = ExamTestQuestionsBusiness.getQuestionsInfo(question_id02);
		option_title01= (String)JSONPath.read(res, "$.data.answer[0].title");
		option_title02= (String)JSONPath.read(res, "$.data.answer[1].title");
	}
	
	String qualificationsId = "";
	@Test(description="新增随机考试的岗位认证",priority=5)
	public void testAddPostAuthRandomExam () {
		String title = "QuotePost"+CommonData.getStringRandom(5);
		PostAuthenticationBusiness.addPostAuthRandomExam(title,  question_bank_id);
		String res = PostAuthenticationBusiness.queryList(title, "0");
		qualificationsId = (String)JSONPath.read(res, "$.list[0].id");
	}
	
	String timer_study_id = "";
	@Test(description="添加随机考试的定时学习任务",priority=6)
	public void testAddRandomExam () {
//		String title = "QuoteRandomExam"+CommonData.getStringRandom(5);
//		TimerStudyTaskTemplate.addRandomExam(title, question_bank_id);
//		String res = TimerStudyTaskTemplate.queryList(title, "all");
//		timer_study_id = (String)JSONPath.read(res,"$.list[0].id");
	}
	String study_name  ="QuoteStudyProjet"+CommonData.getStringRandom(5);
	String study_project_id = "";
	@Test(description="添加随机考试的学习项目",priority=7)
	public void testAddRondomExamStudyProject() {
//		String res = StudyProjectNewBusinesss.addStudyProject(study_name, ClassificationBusines.getPrimaryId(),
//				PaperBusiness.getIdByKeyword(BaseBusiness.pass_paper_name),
//				CertificateBusiness.getIdByKeyword(cert_name), ArticleBusiness.getIdByKeyword(articlename),
//				CourseBusiness.getIdByPage(course_name),
//				question_bank_id);
//		study_project_id = (String) JSONPath.read(res, "$.id");
//		Assert.assertNotNull(study_project_id, "添加随机考试的学习项目" + res);
//		study_project_id = (String) JSONPath.read(res, "$.id");
//		Assert.assertNotNull(study_project_id, "添加随机考试的学习项目" + res);
	}
	
	String study_id = "";
	@Test(description="添加随机考试的学习任务",priority=8)
	public void testAddRandomExamToStudyTask() {
//		String title = "QuoteRandomStudy"+CommonData.getStringRandom(5);
//		StudyTaskBusiness.addRandomExam(title, question_bank_id);
//		String res = StudyTaskBusiness.getStudyTaskList(title, "true", "0", "");
//		study_id= (String)JSONPath.read(res, "$.list[0].id");
	}
	
	String exam_id = "";
	@Test(description="新增随机考试的考试任务",priority=9)
	public void testCreatRandomExamTask() {
		String random_exam_task_name = "QuoteRandomExam"+CommonData.getStringRandom(5);
		String res = ExaminationTaskBusiness.creatRandomExamTask("2", "show", "20", "1", "40", "0", "0", "0", "0", "1", "30", 
				"0", "0", question_bank_id, "2", random_exam_task_name, DateUtil.getRegularDate(0), DateUtil.getRegularDate(1), 
				"false", UserBusiness.getUserId(), "45", "0", "0", "1", CertificateBusiness.getIdByKeyword(BaseBusiness.certificate_name), "20", "0", "0", "0", "0",
				"{\"missScore\":0,\"passScore\":5,\"unPassScore\":0}", "true", "false", UserBusiness.getUserId(),"false");
		String msg = (String)JSONPath.read(res, "$.msg");
		exam_id = ExaminationTaskBusiness.getIdByName(random_exam_task_name);
		Assert.assertEquals(msg, "新增考试成功！","新增随机考试任务实际返回结果："+res);
	}
	
	String practice_id = "";
	@Test(description="添加题库练习",priority=10)
	public void testAddPractice() {
		String name = "QuoteExercise"+CommonData.getStringRandom(5);
		String res = ItemBankExerciseBusiness.addPractice(name, question_bank_id);
		String queryPracticeList = ItemBankExerciseBusiness.queryPracticeList(name, "all");
		String status = (String) JSONPath.read(res, "$.status");
		practice_id = (String)JSONPath.read(queryPracticeList, "$.list[0].id");
		Assert.assertEquals("true",status,"新增题库练习实际返回结果："+res);
	}
	
	@Test(description="题库下的试题被引用时，判断题库下的试题是否能被删除",priority=11)
	public void testDeleteQuestions() {
		String res = ExamTestQuestionsBusiness.isCanDelete(question_id01);
		String msg = (String)JSONPath.read(res, "$.data.is_can_delete");
		Assert.assertEquals(msg,"false","判断题库下的试题是否能被删除,实际返回结果："+res);
	}
	
	@Test(description = "题库下的试题被引用时,检查哪些任务引用了题库",priority=12)
	public void testCheckDeleteAll() {
		String res = ExamTestQuestionsBusiness.checkDelete(question_id01);
		String type_exam_name =  (String)JSONPath.read(res, "$.list[0].type_name");
		String title = (String)JSONPath.read(res, "$.list[0].title");
		Assert.assertEquals(type_exam_name,"考试任务","题库下的试题被引用时,检查哪些任务引用了题库,实际返回结果："+res);
		Assert.assertNotNull(title, "题库下的试题被引用时,检查哪些任务引用了题库,引用的title实际返回结果："+res);
		
	}
	@Test(description="删除考试任务",priority=13)
	public void testDeleteExamTask() {
		String res =ExaminationTaskBusiness.deleteExamTask(exam_id);
		String msg = (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "删除任务成功","删除考试任务,实际返回结果："+res);
	}
	
	@Test(description="题库下的试题被引用时，判断题库下的试题是否能被删除",priority=14)
	public void testDeleteQuestionsNotExam() {
		String res = ExamTestQuestionsBusiness.isCanDelete(question_id01);
		String msg = (String)JSONPath.read(res, "$.data.is_can_delete");
		Assert.assertEquals(msg,"false","判断题库下的试题是否能被删除,实际返回结果："+res);
	}
	
	@Test(description = "题库下的试题被引用时,检查哪些任务引用了题库",priority=15)
	public void testCheckDeleteNotExam() {
		String res = ExamTestQuestionsBusiness.checkDelete(question_id01);
		String type_exam_name =  (String)JSONPath.read(res, "$.list[0].type_name");
		String title = (String)JSONPath.read(res, "$.list[0].title");
		Assert.assertEquals(type_exam_name,"定时考试模板","题库下的试题被引用时,检查哪些任务引用了题库,实际返回结果："+res);
		Assert.assertNotNull(title, "题库下的试题被引用时,检查哪些任务引用了题库,引用的title实际返回结果："+res);
		
	}
	@Test(description="删除定时考试任务",priority=16)
	public void testDeleteTimerExamTask() {
		String res = TimerExamTaskBusiness.deleteTimerExamTask(timer_exam_id);
		String status = (String)JSONPath.read(res, "$.status");
		Assert.assertEquals(status, "true","删除定时考试任务,实际返回结果："+res);
	}
	
	@Test(description="题库下的试题被引用时，删除定时考试任务,判断题库下的试题是否能被删除",priority=17)
	public void testDeleteQuestionsNotTimeExam() {
		String res = ExamTestQuestionsBusiness.isCanDelete(question_id01);
		String msg = (String)JSONPath.read(res, "$.data.is_can_delete");
		Assert.assertEquals(msg,"false","判断题库下的试题是否能被删除,实际返回结果："+res);
	}
	
	@Test(description = "题库下的试题被引用时,删除定时考试任务,检查哪些任务引用了题库",priority=18)
	public void testCheckDeleteNotTimeExam() {
//		String res = ExamTestQuestionsBusiness.checkDelete(question_id01);
//		String type_exam_name =  (String)JSONPath.read(res, "$.list[0].type_name");
//		String title = (String)JSONPath.read(res, "$.list[0].title");
//		Assert.assertEquals(type_exam_name,"学习任务","题库下的试题被引用时,检查哪些任务引用了题库,实际返回结果："+res);
//		Assert.assertNotNull(title, "题库下的试题被引用时,检查哪些任务引用了题库,引用的title实际返回结果："+res);
		
	}
	
	@Test(description="删除学习任务",priority=19)
	public void testDeleteStudyTask() {
//		StudyTaskBusiness.deleteStudyTask(study_id);
	}
	
	@Test(description="题库下的试题被引用时，删除学习任务,判断题库下的试题是否能被删除",priority=20)
	public void testDeleteQuestionsNotStudyTask() {
		String res = ExamTestQuestionsBusiness.isCanDelete(question_id01);
		String msg = (String)JSONPath.read(res, "$.data.is_can_delete");
		Assert.assertEquals(msg,"false","判断题库下的试题是否能被删除,实际返回结果："+res);
	}
	
	@Test(description = "题库下的试题被引用时,删除学习任务,检查哪些任务引用了题库",priority=21)
	public void testCheckDeleteNotStudyTask() {
		String res = ExamTestQuestionsBusiness.checkDelete(question_id01);
		String type_exam_name =  (String)JSONPath.read(res, "$.list[0].type_name");
		String title = (String)JSONPath.read(res, "$.list[0].title");
		Assert.assertEquals(type_exam_name,"题库练习","题库下的试题被引用时,检查哪些任务引用了题库,实际返回结果："+res);
		Assert.assertNotNull(title, "题库下的试题被引用时,检查哪些任务引用了题库,引用的title实际返回结果："+res);
		
	}
	
	@Test(description="删除定时学习模板",priority=22)
	public void testDeleteTimerStudy() {
//		String res = TimerStudyTaskTemplate.deleteTimerStudy(timer_study_id);
//		String status = (String)JSONPath.read(res, "$.status");
//		Assert.assertEquals(status, "true","删除定时学习模板，实际返回结果："+res);
	}
	
	@Test(description="题库下的试题被引用时，删除定时学习任务,判断题库下的试题是否能被删除",priority=23)
	public void testDeleteQuestionsNotTimeStudyTask() {
		String res = ExamTestQuestionsBusiness.isCanDelete(question_id01);
		String msg = (String)JSONPath.read(res, "$.data.is_can_delete");
		Assert.assertEquals(msg,"false","判断题库下的试题是否能被删除,实际返回结果："+res);
	}
	
	@Test(description = "题库下的试题被引用时,删除定时学习任务,检查哪些任务引用了题库",priority=24)
	public void testCheckDeleteNotTimeStudyTask() {
//		String res = ExamTestQuestionsBusiness.checkDelete(question_id01);
//		String type_exam_name =  (String)JSONPath.read(res, "$.list[0].type_name");
//		String title = (String)JSONPath.read(res, "$.list[0].title");
//		Assert.assertEquals(type_exam_name,"题库练习","题库下的试题被引用时,检查哪些任务引用了题库,实际返回结果："+res);
//		Assert.assertNotNull(title, "题库下的试题被引用时,检查哪些任务引用了题库,引用的title实际返回结果："+res);
		
	}
	@Test(description = "删除题库练习",priority=25)
	public void testDeleteExce() {
		ItemBankExerciseBusiness.deleteExce(practice_id);
	}
	
	@Test(description="题库下的试题被引用时，删除题库练习,判断题库下的试题是否能被删除",priority=26)
	public void testDeleteQuestionsNotItemBankExercise() {
		String res = ExamTestQuestionsBusiness.isCanDelete(question_id01);
		String msg = (String)JSONPath.read(res, "$.data.is_can_delete");
		Assert.assertEquals(msg,"false","判断题库下的试题是否能被删除,实际返回结果："+res);
	}
	
	@Test(description = "题库下的试题被引用时,删除题库练习,检查哪些任务引用了题库",priority=27)
	public void testCheckDeleteNotItemBankExercise() {
		String res = ExamTestQuestionsBusiness.checkDelete(question_id01);
		String title = (String)JSONPath.read(res, "$.list[0].title");
		Assert.assertNotNull(title, "题库下的试题被引用时,检查哪些任务引用了题库,引用的title实际返回结果："+res);
		
	}
	
	@Test(description= "删除学习项目",priority=28)
	public void testDeleteStudyProject() {
		StudyProjectBusiness.deleteStudyProject(study_project_id);
	}
	@Test(description="题库下的试题被引用时，删除题库练习,判断题库下的试题是否能被删除",priority=29)
	public void testDeleteQuestionsNotStudyProject() {
		String res = ExamTestQuestionsBusiness.isCanDelete(question_id01);
		String msg = (String)JSONPath.read(res, "$.data.is_can_delete");
		Assert.assertEquals(msg,"false","判断题库下的试题是否能被删除,实际返回结果："+res);
	}
	
	@Test(description = "题库下的试题被引用时,删除题库练习,检查哪些任务引用了题库",priority=30)
	public void testCheckDeleteNotStudyProject() {
		String res = ExamTestQuestionsBusiness.checkDelete(question_id01);
		String type_exam_name =  (String)JSONPath.read(res, "$.list[0].type_name");
		String title = (String)JSONPath.read(res, "$.list[0].title");
		Assert.assertEquals(type_exam_name,"岗位认证","题库下的试题被引用时,检查哪些任务引用了题库,实际返回结果："+res);
		Assert.assertNotNull(title, "题库下的试题被引用时,检查哪些任务引用了题库,引用的title实际返回结果："+res);
		
	}
	
	@Test(description="删除岗位认证",priority=31)
	public void testDeleteAuthentication() {
		String res = PostAuthenticationBusiness.deleteAuthentication(qualificationsId);
		String msg = (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "能力认证删除成功","删除岗位认证，实际返回结果："+res);
	}
	
	@Test(description = "删除试题，再删除题库",priority=32)
	public void testDelete() {
		ExamTestQuestionsBusiness.deleteQuestions(question_id01);
		ExamTestQuestionsBusiness.deleteQuestions(question_id02);
		String res = ExaminationBusines.deleteQuestionBank(question_bank_id);
		String msg = (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "删除成功","试题被引用的项目删除后，删除题库"+res);
	}
}
