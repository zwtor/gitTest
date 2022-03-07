package exam.cases;

import cn.kxy.authentication.business.CertificateBusiness;
import cn.kxy.base.business.BaseBusiness;
import cn.kxy.examination.business.ExaminationTaskBusiness;
import cn.kxy.examination.business.PaperBusiness;
import cn.kxy.examination.business.SoaExamBusiness;
import cn.kxy.setting.bussiness.UserBusiness;
import cn.lazy.init.cases.InitExam;
import com.alibaba.fastjson.JSONPath;
import com.lazy.common.utils.CommonData;
import com.lazy.common.utils.DateUtil;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TestSoaExam extends InitExam {
	String username = UserBusiness.getUsername();
	String exam_pass_name = "SoaPassExam" + CommonData.getStringRandom(3);
	String exam_pass_name1 = "SoaExamPass" + CommonData.getStringRandom(3);
	String examId1 = "";
	String examId2 = "";

	@BeforeClass
	public void init() {
		ExaminationTaskBusiness.creatRewardExamTask("1", "hide", "10", "100", "1", exam_pass_name,
				DateUtil.getRegularDate(0), DateUtil.getRegularDate(2), "false", UserBusiness.getUserId(),
				PaperBusiness.getIdByKeyword(BaseBusiness.pass_paper_name), "60", "0", "false", "2",
				CertificateBusiness.getIdByKeyword(BaseBusiness.certificate_name), "12", "0", "0", "0", "0", "true",
				"false", UserBusiness.getUserId(), "{\"missScore\":4,\"passScore\":6,\"unPassScore\":2}");
		ExaminationTaskBusiness.creatRewardExamTask("1", "hide", "10", "100", "1", exam_pass_name1,
				DateUtil.getRegularDate(0), DateUtil.getRegularDate(2), "false", UserBusiness.getUserId(),
				PaperBusiness.getIdByKeyword(BaseBusiness.pass_paper_name), "60", "0", "false", "2",
				CertificateBusiness.getIdByKeyword(BaseBusiness.certificate_name), "12", "0", "0", "0", "0", "true",
				"false", UserBusiness.getUserId(), "{\"missScore\":4,\"passScore\":6,\"unPassScore\":2}");
		// 交卷
		examId1 = ExaminationTaskBusiness.getIdByKeyword(exam_pass_name);
		examId2 = ExaminationTaskBusiness.getIdByKeyword(exam_pass_name1);
		String exam_res = ExaminationTaskBusiness.submitPassExam(exam_pass_name);
		System.out.println(exam_res);
		String res = ExaminationTaskBusiness.submitPassExam(exam_pass_name1);
		System.out.println(res);
	}

	@Test(description = "微服务---查询试卷待阅卷人员", priority = 1)
	public void testQueryMarkingUsers() {
		String res = SoaExamBusiness.queryMarkingUsers(examId1);
		String name = (String) JSONPath.read(res, "$.list[0].user_name");
		String paper_status = (String) JSONPath.read(res, "$.list[0].paper_status");
		String paper_name = (String) JSONPath.read(res, "$.list[0].paper_name");
		Assert.assertEquals(paper_name, BaseBusiness.pass_paper_name, "微服务---查询试卷待阅卷人员实际返回结果：" + res);
		Assert.assertEquals(name, username, "微服务---查询试卷待阅卷人员实际返回结果：" + res);
		Assert.assertEquals(paper_status, "待阅卷", "微服务---查询试卷待阅卷人员实际返回结果：" + res);
	}

	@Test(description = "微服务---批量查询考试任务详情", priority = 2)
	public void testBatchQueryExamInfo() {
		String res = SoaExamBusiness.batchQueryExamInfo(examId1, examId2);
		String title = (String) JSONPath.read(res, "$.exams[0].base_exam_info.title");
		String title0 = (String) JSONPath.read(res, "$.exams[1].base_exam_info.title");
		Assert.assertTrue(title.contains("Soa"), "微服务---批量查询考试任务详情:" + res);
		Assert.assertTrue(title0.contains("Soa"), "微服务---批量查询考试任务详情:" + res);

	}

	@Test(description = "微服务---批量查询考试监控信息", priority = 3)
	public void testBatchQueryExamMonitors() {
		String res = SoaExamBusiness.batchQueryExamMonitors(examId1, examId2);
		int status = (int) JSONPath.read(res, "$.monitors[0].status");
		Assert.assertEquals(status, 5, "微服务---批量查询考试监控信息实际返回结果：" + res);
	}

	@Test(description = "微服务---批量查询人员的考试任务信息", priority = 4)
	public void testBatchQueryExamPeopleInfo() {
		String res = SoaExamBusiness.batchQueryExamPeopleInfo(examId1, examId2);
		String title = (String) JSONPath.read(res, "$.exams[0].base_exam_info.title");
		Assert.assertTrue(title.contains("Soa"), "微服务---批量查询考试任务详情:" + res);
	}

	@Test(description = "微服务---新增考试监控", priority = 5)
	public void testAddExamMonitors() {
		String res = SoaExamBusiness.addExamMonitors(examId1, DateUtil.getRegularDate(1), DateUtil.getRegularDate(2));
		String status = (String) JSONPath.read(res, "$.added");
		Assert.assertEquals(status, "true", "微服务---新增考试监控，实际返回结果：" + res);
	}

	@Test(description = "微服务---删除考试试卷关系", priority = 6)
	public void testDeleteExamPaperMappings() {
		String res = SoaExamBusiness.deleteExamPaperMappings(examId1, examId2);
		String deleted = (String) JSONPath.read(res, "$.deleted");
		Assert.assertEquals(deleted, "true", "微服务---删除考试试卷关系" + res);
	}

	@Test(description = "微服务---删除考试监控", priority = 7)
	public void testDeleteExamMonitors() {
		String res = SoaExamBusiness.deleteExamMonitors(examId1);
		String deleted = (String) JSONPath.read(res, "$.deleted");
		String res0 = ExaminationTaskBusiness.queryPersonnelmonitorResult(examId1, "0", "", "", "");
		int total = (int) JSONPath.read(res0, "$.data.planUser.total");
		Assert.assertTrue(total == 0, "微服务---删除考试监控，对人员监控的应考人员的搜索进行校验，实际返回结果：" + res0);
		Assert.assertEquals(deleted, "true", "微服务---删除考试监控，实际返回结果：" + res);
	}
	@AfterClass
	public void end() {
		ExaminationTaskBusiness.deleteExamTask(examId1);
		ExaminationTaskBusiness.deleteExamTask(examId2);
	}
}
