package exam.cases;

import cn.kxy.authentication.business.CertificateBusiness;
import cn.kxy.base.business.BaseBusiness;
import cn.kxy.examination.business.*;
import cn.kxy.my.business.MyBusiness;
import cn.kxy.setting.bussiness.UserBusiness;
import cn.lazy.init.cases.InitExam;
import com.alibaba.fastjson.JSONPath;
import com.lazy.common.utils.CommonData;
import com.lazy.common.utils.DateUtil;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.math.BigDecimal;

public class TestExamTaskToPass extends InitExam {
	
	static String exam_pass_name = "PassExamAA" + CommonData.getStringRandom(5);
	int startscore = 0;
	
	String id = "";
	String user_name = UserBusiness.getUsername();
	@Test(description = "新增考试",priority = 1)
	public void init() {
		// 新增考试
		ExaminationTaskBusiness.creatRewardExamTask("1", "hide", "10", "100", "1", exam_pass_name,
				DateUtil.getRegularDate(0), DateUtil.getRegularDate(2), "false", UserBusiness.getUserId(),
				PaperBusiness.getIdByKeyword(BaseBusiness.pass_paper_name), "60", "0", "false", "2",
				CertificateBusiness.getIdByKeyword(BaseBusiness.certificate_name), "12", "0", "0", "0", "0", "true", "false",
				UserBusiness.getUserId(), "{\"missScore\":4,\"passScore\":6,\"unPassScore\":2}");
		id = ExaminationTaskBusiness.getIdByKeyword(exam_pass_name);
		
	}

	@Test(description = "交卷",priority = 2)
	public void testSubmitPassExam() {
		ExaminationTaskBusiness.submitPassExam(exam_pass_name);
	}
	
	@Test(description = "考试后,对我的考试任务待阅卷的信息校验",priority=3)
	public void testCheckMyExamTask() {
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		String res = MyExamTaskBusiness.queryMyExamTask("all", exam_pass_name);
		int makeup_count = (int) JSONPath.read(res, "$.list[0].makeup_count");
		BigDecimal total_score = (BigDecimal) JSONPath.read(res, "$.list[0].total_score");

		BigDecimal exptotal_score = new BigDecimal("100.0");
		String status = (String) JSONPath.read(res, "$.list[0].status");
		BigDecimal score = (BigDecimal) JSONPath.read(res, "$.list[0].score");

		BigDecimal expscore = new BigDecimal("40.0");
		Assert.assertEquals(makeup_count, 1, "考试后,对我的考试任务待阅卷的信息校验实际返回结果：" + res);
		Assert.assertEquals(total_score, exptotal_score, "考试后,对我的考试任务待阅卷的信息校验实际返回结果：" + res);
		Assert.assertEquals(status, "scoring", "考试后,对我的考试任务待阅卷的信息校验实际返回结果：" + res);
		Assert.assertEquals(score, expscore, "考试后,对我的考试任务待阅卷的信息校验实际返回结果：" + res);
	}

	@Test(description = "交卷后不允许看出答案解析",priority=4)
	public void testSubmitNotCheckExamAnalysis() {
		String res = MyExamTaskBusiness.queryMyExamTask("scoring", exam_pass_name);
		String show_answer_analysis = (String) JSONPath.read(res, "$.list[0].show_answer_analysis");
		Assert.assertEquals(show_answer_analysis, "false", "交卷后不允许看出答案解析实际返回结果：" + res);
	}

	@Test(description = "查看待阅卷详情",priority=5)
	public void testQueryExamPlanPending() {
		String res = ExaminationTaskBusiness.getExamPlanPendingList(exam_pass_name);
		String examname = (String) JSONPath.read(res, "$.data.userList[0].examName");
		String analysis = (String) JSONPath.read(res, "$.data.paperVo.showQuestionInfo[0].analysis");

		Assert.assertEquals(analysis, "JudgementQuestion Answer analysis", "查看待阅卷详情实际返回结果：" + res);
		Assert.assertEquals(examname, BaseBusiness.pass_paper_name, "查看待阅卷详情实际返回结果：" + res);
	}

	@Test(description = "考试任务的隐藏知识点校验",priority=6)
	public void testShow_knowledgeIsHide() {
		String res = ExaminationTaskBusiness.queryInfo(exam_pass_name);
		String show_knowledge = (String) JSONPath.read(res, "$.show_knowledge");
		Assert.assertEquals(show_knowledge, "hide","考试任务的隐藏知识点校验实际返回结果："+res);
	}

	@Test(description = "获取批阅前的学分",priority = 7)
	public void testGetStartscore() {
		startscore = MyBusiness.getMyTotalScore();
	}
	
	@Test(description = "对试题进行批阅", priority=8)
	public void testMarkingExam() {
		String res = ExaminationTaskBusiness.getExamPlanPendingList(exam_pass_name);
		String id1 =(String)JSONPath.read(res, "$.data.paperVo.showQuestionInfo[0].answer.bizQuestionOptionList[0].questionId");
		String res01 = ExaminationTaskBusiness.submitMarkingPaper(id, id1, "60");
		String msg=(String)JSONPath.read(res01,"$.msg");
		Assert.assertEquals(msg, "批阅成功","对试题进行批阅实际返回结果："+res01);
	
	}
	
	@Test(description = "批阅后查看移动端考试结果",priority= 9)
	public void testQueryExamResultById() {
		String res = MyExamTaskBusiness.queryExamResultById(id);
		Assert.assertTrue(!res.contains("scoring"),"批阅后查看移动端考试结果"+res);
	}
	
	@Test(description = "清除所有导出数据",priority = 10)
	public void deleteAllRecord() {
		String res = PaperExportBusiness.deleteAllRecord();
		Assert.assertTrue(res.contains("OK"),"清除所有导出数据,实际返回结果："+res);
	}
	
	@Test(description="导出考试结果--通过用户名查看",priority=11)
	public void testGetExportExamResultCodeByName() {
		int code = ExaminationTaskBusiness.getExportExamResultCode(id, user_name);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Assert.assertEquals(code, 200,"导出考试结果数据，实际状态码为："+code);
	}
	@Test(description="导出考试结果--无搜索条件的情况",priority=12)
	public void testGetExportExamResultCode() {
		int code = ExaminationTaskBusiness.getExportExamResultCode(id, "");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Assert.assertEquals(code, 200,"导出考试结果--无搜索条件的情况，实际状态码为："+code);
	}
	@Test(description="导出学员作答明细--通过用户名查看",priority=13)
	public void testGetExportExamDetailCodeByName() {
		int code = ExaminationTaskBusiness.getExportExamDetailCode(id, user_name);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Assert.assertEquals(code, 200,"导出学员作答明细--通过用户名查看，实际状态码为："+code);
	}
	@Test(description="导出学员作答明细--无搜索条件的情况",priority=14)
	public void testGetExportExamDetailCode() {
		int code = ExaminationTaskBusiness.getExportExamDetailCode(id, "");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Assert.assertEquals(code, 200,"导出学员作答明细--无搜索条件的情况，实际状态码为："+code);
	}
	@Test(description="导出试题作答明细--通过用户名查看",priority=15)
	public void testGetExportAnswerDetailCodeByName() {
		int code = ExaminationTaskBusiness.getExportAnswerDetailCode(id, user_name);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Assert.assertEquals(code, 200,"导出学员作答明细--通过用户名查看，实际状态码为："+code);
	}
	@Test(description="导出试题作答明细--无搜索条件的情况",priority=16)
	public void testGetExportAnswerDetailCode() {
		int code = ExaminationTaskBusiness.getExportAnswerDetailCode(id, "");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Assert.assertEquals(code, 200,"导出试题作答明细--无搜索条件的情况，实际状态码为："+code);
	}
	@Test(description="导出试题分析--全部试题类型的情况",priority=17)
	public void testGetExportQuestionAnalysisCodeAll() {
		int code = ExaminationTaskBusiness.getExportQuestionAnalysisCode(id, "");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Assert.assertEquals(code, 200,"导出试题分析--全部试题类型的情况，实际状态码为："+code);
	}
	@Test(description="导出试题分析--单选题类型的情况",priority=18)
	public void testGetExportQuestionAnalysisCodeSingle() {
		int code = ExaminationTaskBusiness.getExportQuestionAnalysisCode(id, "1");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Assert.assertEquals(code, 200,"导出试题分析--单选题类型的情况，实际状态码为："+code);
	}
	@Test(description="导出试题分析--多选试题类型的情况",priority=19)
	public void testGetExportQuestionAnalysisCodeMult() {
		int code = ExaminationTaskBusiness.getExportQuestionAnalysisCode(id, "2");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Assert.assertEquals(code, 200,"导出试题分析--多选试题类型的情况，实际状态码为："+code);
	}
	@Test(description="导出试题分析--判断试题类型的情况",priority=20)
	public void testGetExportQuestionAnalysisCodeJudge() {
		int code = ExaminationTaskBusiness.getExportQuestionAnalysisCode(id, "3");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Assert.assertEquals(code, 200,"导出试题分析--判断试题类型的情况，实际状态码为："+code);
	}
	@Test(description="导出补考明细",priority=21)
	public void testGetExportMakeupExamCode() {
		int code = ExaminationTaskBusiness.getExportMakeupExamCode(id, user_name);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Assert.assertEquals(code, 200,"导出补考明细，实际状态码为："+code);
	}
	
	@Test(description = "查看导出的结果",priority = 22)
	public void exportRecordList() {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		String res = PaperExportBusiness.exportRecordList();
		String status_1 = (String)JSONPath.read(res, "$.list[0].status");
		String status_2 = (String)JSONPath.read(res, "$.list[1].status");
		String status_3 = (String)JSONPath.read(res, "$.list[2].status");
		String status_4 = (String)JSONPath.read(res, "$.list[3].status");
		String status_5 = (String)JSONPath.read(res, "$.list[4].status");
		String status_6 = (String)JSONPath.read(res, "$.list[5].status");
		String status_7 = (String)JSONPath.read(res, "$.list[6].status");
		String status_8 = (String)JSONPath.read(res, "$.list[7].status");
		String status_9 = (String)JSONPath.read(res, "$.list[8].status");
		String status_10 = (String)JSONPath.read(res, "$.list[9].status");
		String status_11 = (String)JSONPath.read(res, "$.list[10].status");
		
		String type1 = (String)JSONPath.read(res, "$.list[0].type");
		String type2 = (String)JSONPath.read(res, "$.list[1].type");
		String type3 = (String)JSONPath.read(res, "$.list[5].type");
		String type4 = (String)JSONPath.read(res, "$.list[8].type");
		String type5 = (String)JSONPath.read(res, "$.list[10].type");
		Assert.assertFalse(status_1=="FAILED", "查看导出的结果:"+res);
		Assert.assertFalse(status_3=="FAILED", "查看导出的结果:"+res);
		Assert.assertFalse(status_2=="FAILED", "查看导出的结果:"+res);
		Assert.assertFalse(status_4=="FAILED", "查看导出的结果:"+res);
		Assert.assertFalse(status_5=="FAILED", "查看导出的结果:"+res);
		Assert.assertFalse(status_6=="FAILED", "查看导出的结果:"+res);
		Assert.assertFalse(status_7=="FAILED", "查看导出的结果:"+res);
		Assert.assertFalse(status_8=="FAILED", "查看导出的结果:"+res);
		Assert.assertFalse(status_9=="FAILED", "查看导出的结果:"+res);
	}
	
	@Test(description = "考试及格后获得学分",dependsOnMethods="testMarkingExam",priority=23)
	public void testPassExamGetScore() {
		int endscore = MyBusiness.getMyTotalScore();
		Assert.assertTrue(endscore>startscore,"考试前的学分："+startscore+", 及格后的学分："+endscore);
	}
	@Test(description="考试及格后，对人员监控的及格人员信息进行校验",priority=24)
	public void testQueryPersonnelmonitorPassInfo() {
		String res = ExaminationTaskBusiness.queryPersonnelmonitorResult(id, "", "", "", "");
		int total = (int) JSONPath.read(res, "$.data.planUser.total");
//		String statusName = (String) JSONPath.read(res, "$.data.planUser.list[0].statusName");
//		BigDecimal score = (BigDecimal)JSONPath.read(res, "$.data.planUser.list[0].score");
//		BigDecimal total_score = (BigDecimal)JSONPath.read(res, "$.data.planUser.list[0].totalScore");
//		BigDecimal expscore = new BigDecimal("100.0");
//		Assert.assertEquals(total_score, expscore,"对人员监控的及格人员信息的总分进行校验实际返回结果"+res);
//		Assert.assertEquals(score, expscore,"对人员监控的及格人员信息的得分进行校验实际返回结果"+res);
		Assert.assertEquals(total, 1,"考试前，对人员监控的及格人员信息进行校验实际返回结果："+res);
//		Assert.assertEquals(statusName, "合格","考试前，对人员监控的应考人员信息进行校验实际返回结果："+res);
	}

	@Test(description = "不允许重考时，重复提交试卷的msg校验",priority=25)
	public void testRepeatSubmitExam() {
		String res = ExaminationTaskBusiness.submitPassExam(exam_pass_name);
		String msg = (String) JSONPath.read(res, "$.msg");
		Assert.assertTrue(msg.equals("试卷已经提交,不允许重复提交!"), "考试及格后获得学分实际结果：" + res);

	}
	
	@Test(description = "考试及格后获得证书", dependsOnMethods = "testPassExamGetScore",priority=26)
	public void testPassExamGetCerticate() {
		String name = MyBusiness.getMyCertificateName(BaseBusiness.certificate_name);
		Assert.assertEquals(name, BaseBusiness.certificate_name,"获得的证书实际name为："+name);
	}
	String rate_id = "";
	@Test(description="考试及格后，对试题分析的信息进行校验",priority=27)
	public void testQueryQuestionAnalysis() {
		String res = ExaminationTaskBusiness.queryQuestionAnalysis(id, "sinagleChoice", "1");
		rate_id = (String )JSONPath.read(res, "$.data.list[0].id");
//		String accuracy = (String)JSONPath.read(res, "$.data.list[0].accuracy");
//		String errorRate = (String)JSONPath.read(res, "$.data.list[0].errorRate");
//		String questionBankName = (String)JSONPath.read(res, "$.data.list[0].questionBankName");
//		String title = (String)JSONPath.read(res, "$.data.list[0].title");
		
//		String typeName = (String)JSONPath.read(res, "$.data.list[0].typeName");
//		String answer = (String)JSONPath.read(res, "$.data.list[0].answer");
//		Assert.assertEquals(answer, "C","考试及格后，对试题分析的信息进行校验"+res);
//		Assert.assertEquals(typeName, "单选题","考试及格后，对试题分析的信息进行校验"+res);
//		Assert.assertEquals(title, "sinagleChoice","考试及格后，对试题分析的信息进行校验"+res);
//		Assert.assertNotNull(questionBankName,"考试及格后，对试题分析的信息进行校验"+res);
//		Assert.assertEquals(errorRate, "0.00%","考试及格后，对试题分析的信息进行校验"+res);
//		Assert.assertEquals(accuracy, "100.00%","考试及格后，对试题分析的信息进行校验"+res);
		Assert.assertTrue(res.contains("查询成功"));
	}
	

	@Test(description = "正确率导出",priority = 28)
	public void testRateUserExportTrue() {
		if (rate_id!=null) {
			String rateUserExport = ExaminationBusines.rateUserExport(id,rate_id,"true");
			
			Assert.assertTrue(rateUserExport.contains("正确率数据导入请求提交成功"));

		}
	}
	
	@Test(description = "错误率导出",priority = 29)
	public void testRateUserExportFalse() {
		if (rate_id!=null) {
			String rateUserExport = ExaminationBusines.rateUserExport(id,rate_id,"false");
			Assert.assertTrue(rateUserExport.contains("错误率数据导入请求提交成功"));
		}
	}
	
	@Test(description = "删除考试",priority=30)
	public void endTest() {
		ExaminationTaskBusiness.deleteExamTask(id); 
	}
}
