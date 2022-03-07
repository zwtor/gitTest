package exam.cases;

import cn.kxy.authentication.business.CertificateBusiness;
import cn.kxy.base.business.BaseBusiness;
import cn.kxy.examination.business.ExaminationTaskBusiness;
import cn.kxy.examination.business.MyExamTaskBusiness;
import cn.kxy.examination.business.PaperBusiness;
import cn.kxy.setting.bussiness.UserBusiness;
import init.cases.InitExam;
import com.alibaba.fastjson.JSONPath;
import com.lazy.common.utils.CommonData;
import com.lazy.common.utils.DateUtil;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.math.BigDecimal;

public class TestFailExamTask extends InitExam{
	String exam_fail_task_name = "Failexam" + CommonData.getStringRandom(10);
	String id = "";
	@Test(description="验证考试不及格时，依旧获得学分",priority=1)
	public void testExamFailGetScore() {
		ExaminationTaskBusiness.creatRewardExamTask("1", "show", "60", "100", "2", exam_fail_task_name,
				DateUtil.getRegularDate(0), DateUtil.getRegularDate(2), "false", UserBusiness.getUserId(),
				PaperBusiness.getIdByKeyword(BaseBusiness.paper_name), "60", "", "false", "1",
				CertificateBusiness.getIdByKeyword("AdvancedAutoTester"), "12", "0", "0", "0", "0", "true", "false",
				UserBusiness.getUserId(), "{\"missScore\":4,\"passScore\":6,\"unPassScore\":2}");
		MyExamTaskBusiness.submitFailBlankExam(exam_fail_task_name);
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		id = ExaminationTaskBusiness.getIdByKeyword(exam_fail_task_name);
	}
	
	@Test(description="考试不及格时，考试列表页显示",priority=2)
	public void testExamTaskFailList() {
		String res = ExaminationTaskBusiness.queryUntestedInfo(UserBusiness.getUsername(),id , "1");
	
		String name = (String) JSONPath.read(res, "$.data.pageInfo.list[0].name");
		String departmentName = (String) JSONPath.read(res, "$.data.pageInfo.list[0].departmentName");
		Assert.assertTrue(!departmentName.isEmpty(), "考试不及格时，考试列表页显示实际返回结果：" + res);
		Assert.assertEquals(name, UserBusiness.getUsername(), "考试不及格时，考试列表页显示实际返回结果：" + res);
	}
	
	@Test(description="设置不允许重复考试时，检查是否允许考试",priority=3) 
	public void test() {
		String res = MyExamTaskBusiness.checkIsCanExam(exam_fail_task_name);
		String can_exam = (String)JSONPath.read(res, "$.can_exam");
		String has_submited = (String)JSONPath.read(res, "$.has_submited");
		String status = (String)JSONPath.read(res, "$.status");
		Assert.assertEquals(can_exam, "false","设置不允许重复考试时，检查是否允许考试实际返回结果："+res);
		Assert.assertEquals(has_submited, "true","设置不允许重复考试时，检查是否允许考试实际返回结果："+res);
		Assert.assertEquals(status, "exam_attend","设置不允许重复考试时，检查是否允许考试实际返回结果："+res);
	}
	
	
	@Test(description="考试不及格后，对人员监控的不及格人员信息进行校验",priority=4)
	public void testQueryPersonnelmonitorFailInfo() {
		String res = ExaminationTaskBusiness.queryPersonnelmonitorResult(id, "1", "", "", "");
		String title = (String) JSONPath.read(res, "$.data.planExam.title");
		int passLine = (int) JSONPath.read(res, "$.data.planExam.passLine");
		int makeupCount = (int) JSONPath.read(res, "$.data.planUser.list[0].makeupCount");
		
		String name = (String) JSONPath.read(res, "$.data.planUser.list[0].userName");
		String departName = (String) JSONPath.read(res, "$.data.planUser.list[0].departName");
		String statusName = (String) JSONPath.read(res, "$.data.planUser.list[0].statusName");
		BigDecimal score = (BigDecimal) JSONPath.read(res, "$.data.planUser.list[0].score");
		BigDecimal expscore = new BigDecimal("0.0");
		Assert.assertEquals(score, expscore,"考试不及格后，对人员监控的不及格人员信息进行校验实际返回结果："+res);
		Assert.assertEquals(statusName, "不合格","考试不及格后，对人员监控的不及格人员信息进行校验实际返回结果："+res);
		Assert.assertTrue(!departName.isEmpty(), "考试不及格后，对人员监控的不及格人员信息进行校验实际返回结果："+res);
		Assert.assertEquals(name, UserBusiness.getUsername(),"考试不及格后，对人员监控的不及格人员信息进行校验实际返回结果："+res);
		Assert.assertEquals(title, exam_fail_task_name,"考试不及格后，对人员监控的不及格人员信息进行校验实际返回结果："+res);
		Assert.assertEquals(passLine, 60,"考试不及格后，对人员监控的不及格人员信息进行校验实际返回结果："+res);
		Assert.assertEquals(makeupCount, 1,"考试不及格后，对人员监控的不及格人员信息进行校验实际返回结果："+res);
	}
	
	@Test(description="考试不及格后，对试题分析信息进行校验",dataProvider="QueryQuestionAnalysis",priority=5)
	public void testQueryQuestionAnalysis(String kind,String name,String type,String exp ) {
		String res = ExaminationTaskBusiness.queryQuestionAnalysis(id, name, type);
		String questionBankName = (String)JSONPath.read(res, "$.data.list[0].questionBankName");
		String title = (String)JSONPath.read(res, "$.data.list[0].title");
		String msg = (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, exp,"考试及格后，对试题分析的信息进行校验"+res);
		Assert.assertEquals(title, name,"考试及格后，对试题分析的信息进行校验"+res);
		Assert.assertNotNull(questionBankName,"考试及格后，对试题分析的信息进行校验"+res);
	}
	
	@DataProvider(name = "QueryQuestionAnalysis")
	public Object[][] QueryQuestionAnalysis() {
		Object[][] obj = new Object[][] { { "查询单选题", "sinagleChoice", "1", "查询成功" },
				{ "查询多选题", "Multiplechoice", "2","查询成功"},
				{ "查询判断题", "JudgementQuestion", "3","查询成功" } };
		return obj;
	}
	@AfterClass
	public void endTest() {
		ExaminationTaskBusiness.deleteExamTask(id);
		Reporter.log("不及格的考试任务用例测试结束，删除考试任务");
	}
	
}
