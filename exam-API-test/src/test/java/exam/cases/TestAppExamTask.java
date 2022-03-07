package exam.cases;

import cn.kxy.authentication.business.CertificateBusiness;
import cn.kxy.base.business.BaseBusiness;
import cn.kxy.examination.business.AppExamBusiness;
import cn.kxy.examination.business.ExaminationTaskBusiness;
import cn.kxy.examination.business.MyExamTaskBusiness;
import cn.kxy.examination.business.PaperBusiness;
import cn.kxy.setting.bussiness.UserBusiness;
import cn.lazy.init.cases.InitExam;
import com.alibaba.fastjson.JSONPath;
import com.lazy.common.utils.CommonData;
import com.lazy.common.utils.DateUtil;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.math.BigDecimal;

public class TestAppExamTask extends InitExam {


	String un_id = "";
	@Test(description = "新增考试后，在app端未完成列表进行查看", priority = 1)
	public void testUntestList() {
		String exam_task_name = "AppUnExam" + CommonData.getStringRandom(5);
		ExaminationTaskBusiness.creatRewardExamTask("1", "show", "60", "100", "4", exam_task_name,
				DateUtil.getRegularDate(0), DateUtil.getRegularDate(2), "false", UserBusiness.getUserId(),
				PaperBusiness.getIdByKeyword(BaseBusiness.pass_paper_name), "60", "0", "true", "1", "0", "60", "0", "0",
				"0", "0", "true", "false", UserBusiness.getUserId(),
				"{\"missScore\":4,\"passScore\":6,\"unPassScore\":2}");
		String res = AppExamBusiness.queryMyExamTaskList("unfinished");
		String title = (String) JSONPath.read(res, "$.list[0].title");
		Long begin_time = (Long) JSONPath.read(res, "$.list[0].begin_time");
		int score = (int) JSONPath.read(res, "$.list[0].award_score");
		un_id = ExaminationTaskBusiness.getIdByKeyword(exam_task_name);
		Assert.assertTrue(begin_time != null, "新增考试后，在app未完成列表进行查看实际返回结果:" + res);
		Assert.assertEquals(score, 6, "新增考试后，在app未完成列表进行查看实际返回结果:" + res);
		Assert.assertEquals(title, exam_task_name, "新增考试后，在app未完成列表进行查看实际返回结果:" + res);
	}

	@Test(description = "在app端查看未完成的考试详情", priority = 2)
	public void testQueryInfo() {
		
		String res = AppExamBusiness.queryListInfoById(un_id);
		String title = (String) JSONPath.read(res, "$.title");
		Long begin_time = (Long) JSONPath.read(res, "$.begin_time");
		Long end_time = (Long) JSONPath.read(res, "$.end_time");
		String total_score = (String) JSONPath.read(res, "$.total_score");
		String passing_score = (String) JSONPath.read(res, "$.passing_score");
		BigDecimal score = (BigDecimal) JSONPath.read(res, "$.score");
		BigDecimal expscore = new BigDecimal("6.0");
		ExaminationTaskBusiness.deleteExamTask(un_id);
		Assert.assertEquals(passing_score, "60.0", "在app端查看考试详情实际返回结果:" + res);
		Assert.assertEquals(total_score, "100.0", "在app端查看考试详情实际返回结果:" + res);
		Assert.assertEquals(score, expscore, "在app端查看考试详情实际返回结果:" + res);
		Assert.assertTrue(begin_time != null, "在app端查看考试详情查看实际返回结果:" + res);
		Assert.assertTrue(end_time != null, "在app端查看考试详情查看实际返回结果:" + res);
		Assert.assertNotNull(title,  "在app端查看考试详情实际返回结果:" + res);
	}

	@Test(description = "在app端进行交卷", priority = 3)
	public void testSubmit() {
		String exam_task_name = "AppSubmitExam" + CommonData.getStringRandom(5);
		ExaminationTaskBusiness.creatRewardExamTask("1", "show", "60", "100", "4", exam_task_name,
				DateUtil.getRegularDate(0), DateUtil.getRegularDate(2), "false", UserBusiness.getUserId(),
				PaperBusiness.getIdByKeyword(BaseBusiness.pass_paper_name), "60", "0", "true", "1", "0", "60", "0", "0",
				"0", "0", "true", "false", UserBusiness.getUserId(),
				"{\"missScore\":4,\"passScore\":6,\"unPassScore\":2}");
		String res = MyExamTaskBusiness.submitBlankExam(exam_task_name);
		String msg = (String) JSONPath.read(res, "$.msg!");
		ExaminationTaskBusiness.deleteExamTask(ExaminationTaskBusiness.getIdByKeyword(exam_task_name));
		Assert.assertEquals(msg, "提交试卷成功!", "在app端进行交卷" + res);

	}

	@Test(description = "在app端不及格列表查看", priority = 4)
	public void testFailList() {
//		String name = "AppFailExam" + CommonData.getStringRandom(5);
//		ExaminationTaskBusiness.failToDoExam(name);
//		String res = AppExamBusiness.queryMyExamTaskList("failed");
//		String status = (String) JSONPath.read(res, "$.list[0].status");
//		ExaminationTaskBusiness.deleteExamTask(ExaminationTaskBusiness.getIdByKeyword(name));
//		Assert.assertEquals(status, "failed", "在app端不及格列表查看" + res);

	}

	@Test(description = "在app端进行重考,并查看考试结果", priority = 5)
	public void testQueryResult() {
		String name = "AppRepeatExam" + CommonData.getStringRandom(5);
		ExaminationTaskBusiness.failToDoExam(name);
		ExaminationTaskBusiness.failToDoExam(name);
		String id = ExaminationTaskBusiness.getIdByKeyword(name);
		String res = AppExamBusiness.queryResultById(id);
		String correct_rate = (String) JSONPath.read(res, "$.correct_rate");
		ExaminationTaskBusiness.deleteExamTask(id);
		Assert.assertEquals(correct_rate, "0%", "在app端进行重考,并通过考试实际返回结果" + res);
	}

	
	String score_id = "";
	@Test(description = "app端阅卷中的列表页显示", priority = 6)
	public void testScoringResultList() {
		String name = "AppScoreExamName" + CommonData.getStringRandom(5);
		ExaminationTaskBusiness.creatRewardExamTask("1", "hide", "10", "100", "1", name, DateUtil.getRegularDate(0),
				DateUtil.getRegularDate(2), "false", UserBusiness.getUserId(),
				PaperBusiness.getIdByKeyword(BaseBusiness.pass_paper_name), "60", "0", "false", "2",
				CertificateBusiness.getIdByKeyword(BaseBusiness.pass_paper_name), "12", "0", "0", "0", "0", "true",
				"false", UserBusiness.getUserId(), "{\"missScore\":4,\"passScore\":6,\"unPassScore\":2}");
		// 交卷
		ExaminationTaskBusiness.submitPassExam(name);
		// 查看列表
		String res = AppExamBusiness.queryMyExamTaskList("scoring");
		score_id = ExaminationTaskBusiness.getIdByKeyword(name);
		Assert.assertTrue(res.contains("size"), "app端阅卷中的列表页显示，实际返回结果" + res);
	}



	@Test(description = "查看app端阅卷中的详情", priority = 7)
	public void testQueryScoringInfo() {
		// 查看详情
		String res = AppExamBusiness.queryResultById(score_id);
		String title01 = (String) JSONPath.read(res, "$.title");
		String exam_time = (String) JSONPath.read(res, "$.exam_time");
		String status01 = (String) JSONPath.read(res, "$.status");
		BigDecimal total_score = (BigDecimal) JSONPath.read(res, "$.total_score");
		BigDecimal expscore = new BigDecimal("100.0");
		ExaminationTaskBusiness.deleteExamTask(score_id);
		Assert.assertNotNull(title01, "app端阅卷中的详情页显示，实际返回结果" + res);
		Assert.assertTrue(!exam_time.isEmpty(), "app端阅卷中的详情页显示，实际返回结果" + res);
		Assert.assertEquals(status01, "scoring", "app端阅卷中的详情页显示，实际返回结果" + res);
		Assert.assertEquals(total_score, expscore, "app端阅卷中的详情页显示，实际返回结果" + res);
	}

	String absence_id = "";
	@Test(description = "缺考后，在app列表页查看信息", priority = 8)
	public void testMissExam() {
		String name = "AppAbsenceExam"+CommonData.getStringRandom(5);
		ExaminationTaskBusiness.creatRewardExamTask("1", "show", "60", "100", "4", name, DateUtil.getRegularDate(-1),
				DateUtil.getRegularDate(-2), "false", UserBusiness.getUserId(),
				PaperBusiness.getIdByKeyword(BaseBusiness.pass_paper_name), "60", "0", "true", "1", "0", "60", "0", "0",
				"0", "0", "true", "false", UserBusiness.getUserId(),
				"{\"missScore\":4,\"passScore\":6,\"unPassScore\":2}");
		String res = AppExamBusiness.queryMyExamTaskList("absence");
		String title = (String) JSONPath.read(res, "$.list[0].title");
		String status = (String) JSONPath.read(res, "$.list[0].status");
		absence_id = ExaminationTaskBusiness.getIdByKeyword(name);
		Assert.assertEquals(title, name, "缺考后，在列表页查看信息实际返回结果:" + res);
		Assert.assertEquals(status, "absence", "缺考后，在列表页查看信息实际返回结果:" + res);
	}

	@Test(description = "缺考后，在app查看考试信息,不可以继续考试", priority = 9)
	public void testMissExamInfo() {
		String res = AppExamBusiness.checkFunctionById(absence_id);
		String can_exam = (String) JSONPath.read(res, "$.can_exam");
		String has_submited = (String) JSONPath.read(res, "$.has_submited");
		ExaminationTaskBusiness.deleteExamTask(absence_id);
		Assert.assertEquals(can_exam, "false", "缺考后，在app查看考试信息,不可以继续考试实际返回结果:" + res);
		Assert.assertEquals(has_submited, "false", "缺考后，在app查看考试信息,不可以继续考试实际返回结果:" + res);
	}

	String passed_id = "";
	@Test(description = "在app查看及格的考试列表", priority = 10)
	public void testAppPassExam() {
		String exam_pass_name = "AppPassExam" + CommonData.getStringRandom(3);
		ExaminationTaskBusiness.passToDoExam(exam_pass_name);
		String res0 = AppExamBusiness.queryMyExamTaskList("passed");
		String title = (String) JSONPath.read(res0, "$.list[0].title");
		passed_id = ExaminationTaskBusiness.getIdByKeyword(exam_pass_name);
		Assert.assertNotNull(title, "在app查看及格的考试列表实际返回结果：" + res0);
	}

	@Test(description = "在app查看及格的考试详情", priority = 11)
	public void testAppPassExamInfo() {
		String res = AppExamBusiness.queryResultById(passed_id);
		String title01 = (String) JSONPath.read(res, "$.title");
//		String exam_time = (String) JSONPath.read(res, "$.exam_time");
//		String status01 = (String) JSONPath.read(res, "$.status");
//		BigDecimal total_score = (BigDecimal) JSONPath.read(res, "$.total_score");
//		BigDecimal score = (BigDecimal) JSONPath.read(res, "$.score");
//		BigDecimal passing_score = (BigDecimal) JSONPath.read(res, "$.passing_score");
//		BigDecimal expscore = new BigDecimal("100.0");
//		BigDecimal exppassing_score = new BigDecimal("10.0");
//		int correct_count = (int) JSONPath.read(res, "$.correct_count");
//		Assert.assertEquals(passing_score, exppassing_score, "在app查看及格的考试详情，实际返回结果" + res);
		Assert.assertNotNull(title01, "在app查看及格的考试详情，实际返回结果" + res);
//		Assert.assertTrue(!exam_time.isEmpty(), "在app查看及格的考试详情，实际返回结果" + res);
//		Assert.assertEquals(status01, "perfect", "在app查看及格的考试详情，实际返回结果" + res);
//		Assert.assertEquals(total_score, expscore, "在app查看及格的考试详情，实际返回结果" + res);
//		Assert.assertEquals(score, expscore, "在app查看及格的考试详情，实际返回结果" + res);
//		Assert.assertEquals(correct_count, 2, "在app查看及格的考试详情，实际返回结果" + res);

	}
	@Test(description = "app端查询等级排名", priority = 12)
	public void testQueryRanking() {
		String res = AppExamBusiness.queryRanking(passed_id);
		Assert.assertTrue(res.contains("total"),"查询等级排名实际返回结果：" + res);
	}
	
	@Test(description="app端查询我的等级排名",priority=13)
	public void testQueryMyranking() {
		String res = AppExamBusiness.queryMyranking(passed_id);
		ExaminationTaskBusiness.deleteExamTask(passed_id);
		String name = (String)JSONPath.read(res, "$.name");
		Assert.assertEquals(name, UserBusiness.getUsername(), "查询等级排名实际返回结果：" + res);
	}

	String fail_id = "";
	@Test(description = "在app查看答案解析详情", priority = 14)
	public void testAppCheckAnalysis() {
		String exam_fail_name = "AppFailExam" + CommonData.getStringRandom(3);
		
		ExaminationTaskBusiness.failToDoExam(exam_fail_name);
		fail_id = ExaminationTaskBusiness.getIdByKeyword(exam_fail_name);
		String res = AppExamBusiness.queryAnalysisById(fail_id);
		String analysis = (String) JSONPath.read(res, "$.analysis[0].analysis");
		String title = (String) JSONPath.read(res, "$.analysis[0].title");
		Assert.assertTrue(!title.isEmpty(), "在app查看答案解析详情实际返回结果" + res);
		Assert.assertTrue(!analysis.isEmpty(), "在app查看答案解析详情实际返回结果" + res);
	}

	@Test(description = "在app查看不及格的考试详情", priority = 15)
	public void testAppFailExamInfo() {
		String res = AppExamBusiness.queryResultById(fail_id);
		String exam_time = (String) JSONPath.read(res, "$.exam_time");
		String status01 = (String) JSONPath.read(res, "$.status");
		BigDecimal total_score = (BigDecimal) JSONPath.read(res, "$.total_score");
		BigDecimal score = (BigDecimal) JSONPath.read(res, "$.score");
		BigDecimal passing_score = (BigDecimal) JSONPath.read(res, "$.passing_score");
		String correct_rate = (String) JSONPath.read(res, "$.correct_rate");
		BigDecimal expscore = new BigDecimal("100.0");
		BigDecimal exppassing_score = new BigDecimal("60.0");
		BigDecimal actscore = new BigDecimal("0.0");
		int correct_count = (int) JSONPath.read(res, "$.correct_count");
		ExaminationTaskBusiness.deleteExamTask(fail_id);
		Assert.assertEquals(correct_rate, "0%", "在app查看及格的考试详情，实际返回结果" + res);
		Assert.assertEquals(passing_score, exppassing_score, "在app查看及格的考试详情，实际返回结果" + res);
		Assert.assertTrue(!exam_time.isEmpty(), "在app查看及格的考试详情，实际返回结果" + res);
		Assert.assertEquals(status01, "failed", "在app查看及格的考试详情，实际返回结果" + res);
		Assert.assertEquals(total_score, expscore, "在app查看及格的考试详情，实际返回结果" + res);
		Assert.assertEquals(score, actscore, "在app查看及格的考试详情，实际返回结果" + res);
		Assert.assertEquals(correct_count, 0, "在app查看及格的考试详情，实际返回结果" + res);
	}

	@Test(description = "查询App端考试列表时，不传status", priority = 16)
	public void testAppExamListNoStatus() {
		String list = AppExamBusiness.queryMyExamTaskList("");
		int total = (int) JSONPath.read(list, "$.total");
		Assert.assertTrue(total>=0, "查询App端考试列表时，不传status实际返回结果：" + list);
	}
}
