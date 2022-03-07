package historydata.cases;

import cn.kxy.authentication.business.CertificateBusiness;
import cn.kxy.base.business.BaseBusiness;
import cn.kxy.examination.business.ExaminationTaskBusiness;
import cn.kxy.examination.business.PaperBusiness;
import cn.kxy.setting.bussiness.UserBusiness;
import com.alibaba.fastjson.JSONPath;
import com.lazy.common.utils.DateUtil;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TestExamHistoryData {

	static String exam_pass_name = "ExamHistoryData";
	String id = "";
	@BeforeClass
	public void init() {
		id = ExaminationTaskBusiness.getIdByKeyword(exam_pass_name);
		if (id==null || id =="") {
			System.out.println("历史考试不存在，重新新增考试");
			ExaminationTaskBusiness.creatRewardExamTask("1", "hide", "10", "100", "1", exam_pass_name,
					DateUtil.getRegularDate(0), DateUtil.getRegularDate(360), "false", UserBusiness.getUserId(),
					PaperBusiness.getIdByKeyword(BaseBusiness.pass_paper_name), "60", "0", "true", "2",
					CertificateBusiness.getIdByKeyword(BaseBusiness.certificate_name), "12", "0", "1", "0", "0", "true", "true",
					UserBusiness.getUserId(), "{\"missScore\":4,\"passScore\":6,\"unPassScore\":2}");
			id = ExaminationTaskBusiness.getIdByKeyword(exam_pass_name);
		}else {
			System.out.println("历史考试存在，无需新增考试");
		}
	}
	@Test(description = "对历史考试数据重新考试",priority = 1)
	public void testReExamHistoryData() {
		String res = ExaminationTaskBusiness.submitPassByIdExam(id);
		String msg = (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "提交试卷成功!","对历史数据重新考试,提交试卷实际返回结果:"+res);
	}
	
	@Test(description = "查看历史考试数据待阅卷详情",priority=2)
	public void testQueryExamPlanPending() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String res = ExaminationTaskBusiness.getExamPlanPendingList(exam_pass_name);
		String examname = (String) JSONPath.read(res, "$.data.userList[0].examName");
		String analysis = (String) JSONPath.read(res, "$.data.paperVo.showQuestionInfo[0].analysis");

		Assert.assertEquals(analysis, "JudgementQuestion Answer analysis", "查看历史考试数据待阅卷详情，实际返回结果：" + res);
		Assert.assertEquals(examname, BaseBusiness.pass_paper_name, "查看历史考试数据待阅卷详情，实际返回结果：" + res);
	}
	
	@Test(description = "对历史数据试题进行批阅",priority = 3)
	public void testMarkingExam() {
		String res = ExaminationTaskBusiness.getExamPlanPendingList(exam_pass_name);
		String id1 =(String)JSONPath.read(res, "$.data.paperVo.showQuestionInfo[0].answer.bizQuestionOptionList[0].questionId");
		String res01 = ExaminationTaskBusiness.submitMarkingPaper(id, id1, "60");
		String msg=(String)JSONPath.read(res01,"$.msg");
		Assert.assertEquals(msg, "批阅成功","对试题进行批阅实际返回结果："+res01);
	}
	@Test(description="考试前，对人员监控的应考人员信息进行校验",priority = 4)
	public void testQueryPersonnelmonitorShouldBeExam() {
		String res = ExaminationTaskBusiness.queryPersonnelmonitorResult(id, "0", "", "", "");
		String title = (String) JSONPath.read(res, "$.data.planExam.title");
		String name = (String) JSONPath.read(res, "$.data.planUser.list[0].userName");
		Assert.assertEquals(name, UserBusiness.getUsername(),"考试前，对人员监控的应考人员信息进行校验实际返回结果："+res);
		Assert.assertEquals(title, exam_pass_name,"考试前，对人员监控的应考人员信息进行校验实际返回结果："+res);
	}
	
	@Test(description="对历史考试数据，对试题分析的信息进行校验",priority=5)
	public void testQueryQuestionAnalysis() {
		String res = ExaminationTaskBusiness.queryQuestionAnalysis(id, "sinagleChoice", "1");
		Assert.assertTrue(res.contains("查询成功"),res);
	}
	
	@Test(description="查看历史考试数据补考明细",priority=6)
 	public void testQueryMakeupExamMonitorsById () {
 		String res = ExaminationTaskBusiness.queryMakeupExamMonitorsById(id,"0","","","");
 		String msg = (String)JSONPath.read(res, "$.msg");
 		Assert.assertEquals(msg,"Ok！","查看补考明细,考试次数进行校验"+res);
 	}
	
}
