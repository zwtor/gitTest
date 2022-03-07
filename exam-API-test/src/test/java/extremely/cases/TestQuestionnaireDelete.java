package extremely.cases;

import cn.kxy.authentication.business.CertificateBusiness;
import cn.kxy.base.business.BaseBusiness;
import cn.kxy.course.resources.bussiness.ArticleBusiness;
import cn.kxy.course.resources.bussiness.CourseBusiness;
import cn.kxy.examination.business.PaperBusiness;
import cn.kxy.investigationresearch.business.InvestigatesBusiness;
import cn.kxy.investigationresearch.business.QuestionnaireBusiness;
import cn.kxy.setting.bussiness.ClassificationBusines;
import cn.kxy.study.business.StudyProjectBusiness;
import cn.kxy.study.business.StudyProjectNewBusinesss;
import init.cases.InitStudyAuthCourse;
import com.alibaba.fastjson.JSONPath;
import com.lazy.common.utils.CommonData;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestQuestionnaireDelete extends InitStudyAuthCourse {
	String ques_name ="DeleteQues"+CommonData.getStringRandom(3);
	String study_name = "Study name"+CommonData.getStringRandom(3);
	String investigates_name = "DeleteSelenium_use"+CommonData.getStringRandom(3);
	String project_name = "DeleteStudyProjectName"+CommonData.getStringRandom(3);
	String study_project_id = "";
	String investigates_id = "";
	String study_id= "";
	String ques_id  = "";
	
	String classification_id = ClassificationBusines.getPrimaryId();
	
	@Test(description = "添加问卷调研",priority = 1)
	public void testAddNormalQuestionnaire() {
		QuestionnaireBusiness.addNormalQuestionnaire(ques_name, "this is a description", "release");
	}
	
	@Test(description = "获取问卷调研的id",priority = 2)
	public void testGetqueId() {
		ques_id = QuestionnaireBusiness.getIdByKeyword(ques_name,"release");
		
	}
	
	@Test(description = "添加调研任务",priority = 3)
	public void testaddNomalInvestigates() {
		//添加调研任务
		InvestigatesBusiness.addNomalInvestigates(ques_name, investigates_name, "release");
	}
	
	@Test (description = "获取调研任务的id",priority = 4)
	public void testgetIdByKeyword(){
		
		investigates_id = InvestigatesBusiness.getIdByKeyword(investigates_name);
	}
	
	@Test(description = "添加学习任务",priority =5)
	public void testaddOnlineTypeStudyTask() {
//		String addOnlineTypeStudyTask = StudyTaskBusiness.addOnlineTypeStudyTask(study_name, DateUtil.getTimeStamp(0, ""), DateUtil.getTimeStamp(1, ""), 
//				CertificateBusiness.getIdByKeyword(cert_name), ArticleBusiness.getIdByKeyword(articlename), 
//				CourseBusiness.getIdByPage(course_name),ques_id);
//		System.out.println(addOnlineTypeStudyTask);
	}
	
	@Test(description = "添加学习项目",priority = 6)
	public void testaddStudyProject() {
//		StudyProjectNewBusinesss.addStudyProject(project_name, classification_id,
//				PaperBusiness.getIdByKeyword(BaseBusiness.pass_paper_name),
//				CertificateBusiness.getIdByKeyword(cert_name), ArticleBusiness.getIdByKeyword(articlename),
//				CourseBusiness.getIdByPage(course_name),
//				ques_id);
	}
	
	@Test(description = "获取学习项目的id",priority =7)
	public void testGetstudy_project_id() {
		String project_res = StudyProjectBusiness.queryLearningProjectList(project_name, classification_id);
		study_project_id = (String) JSONPath.read(project_res, "$.list[0].id");
	}
	
	@Test(description="问卷被问卷调查和学习任务引用时，验证是否能被删除",priority=8)
	public void testDeleteQuoteData() {
		
		//添加学习项目
		String res = BaseBusiness.deleteCheckData(ques_id, "questionnaire");
		
		String investigate_n= (String)JSONPath.read(res, "$.list[0].title");
		String investigate_type_name= (String)JSONPath.read(res, "$.list[0].type_name");
		Assert.assertEquals(investigate_type_name, "调研任务","问卷被问卷调查和学习任务引用时，验证是否能被删除:"+res);
		Assert.assertEquals(investigate_n, investigates_name,"问卷被问卷调查和学习任务引用时，验证是否能被删除:"+res);
	}
	
	@Test(description = "取消问卷",priority = 9)
	public void testCancelInvestigates() {
		
		InvestigatesBusiness.cancelInvestigates(investigates_name, "release");
		InvestigatesBusiness.deleteInvestigates(investigates_name, "draft");
	}
	
	
	@Test(description="删除调查问卷后，再次删除问卷",priority=10)
	public void testdeleteQuoteData() {
		
//		String res = BaseBusiness.deleteCheckData(ques_id, "questionnaire");
//		String study_n= (String)JSONPath.read(res, "$.list[0].title");
//		String study_type_name= (String)JSONPath.read(res, "$.list[0].type_name");
//		int total = (int)JSONPath.read(res, "$.total");
//		Assert.assertEquals(total, 2,"问卷被问卷调查和学习任务引用时，验证是否能被删除，个数进行校验"+res);
//		Assert.assertEquals(study_type_name, "学习任务","问卷被问卷调查和学习任务引用时，验证是否能被删除，学习任务名称进行校验："+res);
//		Assert.assertEquals(study_n, study_name,"问卷被问卷调查和学习任务引用时，验证是否能被删除，学习类型名称进行校验："+res);
//		
	}
	
	@Test(description = "获取学习的id",priority = 11)
	public void testGetSrudyId() {
//		String study_res = StudyTaskBusiness.getStudyTaskList(study_name, "true", "0", "");
//		study_id= (String)JSONPath.read(study_res, "$.list[0].id");
	}
	
	@Test(description = "删除学习任务",priority =12)
	public void testdeleteStudyTask() {
//		StudyTaskBusiness.deleteStudyTask(study_id);
		
	}
	
	@Test(description="删除学习任务后，再次删除问卷",priority= 13)
	public void testDeleteAgainQuoteData() {
//		String res = BaseBusiness.deleteCheckData(ques_id, "questionnaire");
//		int total = (int)JSONPath.read(res, "$.total");
//		Assert.assertEquals(total, 1,"问卷被问卷调查和学习任务引用时，验证是否能被删除，个数进行校验"+res);
	}
	
	@Test(description = "删除学习项目",priority =14)
	public void testdeleteStudyProject() {
		StudyProjectBusiness.deleteStudyProject(study_project_id);
		
	}
	
	@Test(description="删除学习项目后，再次删除问卷",priority=15)
	public void testdeleteStudyProject1 () {
		String res = BaseBusiness.deleteCheckData(ques_id, "questionnaire");
		int total = (int)JSONPath.read(res, "$.total");
		Assert.assertEquals(total, 0,"删除学习项目后，再次删除问卷，验证是否能被删除，个数进行校验"+res);
	}
	
	@Test(description="被引用的数据全部删除后 ，再次取消、删除问卷，应可删除",priority=16)
	public void testDeleteQuestionnaire() {
		QuestionnaireBusiness.cancelQuestionnaire(ques_name, "release");
		String res = QuestionnaireBusiness.deleteQuestionnaire(ques_name, "draft");
		String msg = (String)JSONPath.read(res, "$.msg") ;
		Assert.assertEquals(msg, "问卷删除成功","直接发布问卷，然后取消，删除实际返回结果："+res);
	}
	String name ="name01" +CommonData.getStringRandom(3);
	@Test(description = "新建问卷",priority =17)
	public void testaddNormalQuestionnaire() {
		
		QuestionnaireBusiness.addNormalQuestionnaire(name, "description ", "release");
	}
	
	@Test(description = "发布问卷",priority = 18)
	public void testAddNomalInvestigates() {
		InvestigatesBusiness.addNomalInvestigates(name, name, "draft");
	}
	
	@Test(description="问卷被引用时，不允许删除",priority=19)
	public void testQuestionnaireByAppoint() {
		
		String res = QuestionnaireBusiness.deleteQuestionnaire(name, "release");
		String msg = (String)JSONPath.read(res, "$.message");
	
		Assert.assertEquals(msg, "occupied by task","问卷被引用时，不允许删除实际返回结果："+res);
	}
	
	@Test(description = "删除调研任务",priority =20)
	public void testdeleteInvestigates() {
		InvestigatesBusiness.deleteInvestigates(name, "draft");
		
	}
	@Test(description = "取消调研任务",priority =21)
	public void testcancelQuestionnaire() {
		
		QuestionnaireBusiness.cancelQuestionnaire(name, "release");
	}
	
	@Test(description = "删除问卷",priority =22)
	public void testdeleteQuestionnaire() {
		QuestionnaireBusiness.deleteQuestionnaire(name, "draft");
	}
}
