package extremely.cases;

import cn.kxy.authentication.business.CertificateBusiness;
import cn.kxy.authentication.business.PostAuthenticationBusiness;
import cn.kxy.base.business.BaseBusiness;
import cn.kxy.examination.business.*;
import cn.kxy.setting.bussiness.UserBusiness;
import init.cases.InitStudyAuthCourse;

import com.alibaba.fastjson.JSONPath;
import com.lazy.common.utils.CommonData;
import com.lazy.common.utils.DateUtil;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestQuestionBankByExamDelete extends InitStudyAuthCourse {

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

	String qualificationsId = "";
	@Test(description="新增随机考试的岗位认证",priority=2)
	public void testAddPostAuthRandomExam () {
		String title = "DeletePost"+CommonData.getStringRandom(5);
		PostAuthenticationBusiness.addPostAuthRandomExam(title,  question_bank_id);
		String res = PostAuthenticationBusiness.queryList(title, "0");
		qualificationsId = (String)JSONPath.read(res, "$.list[0].id");
	}
	
	String random_exam_id = "";
	@Test(description="新增随机考试的考试任务",priority=3)
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
	
	@Test(description="删除考试任务，试题和题库",priority=5)
	public void testDeleteQuestionBank () {
		ExaminationTaskBusiness.deleteExamTask(random_exam_id);
		String res = ExamTestQuestionsBusiness.deleteQuestions(question_id);
		res = ExaminationBusines.deleteQuestionBank(question_bank_id);
		String msg = (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "删除成功","试题被引用的项目删除后，删除题库"+res);
	}
}
