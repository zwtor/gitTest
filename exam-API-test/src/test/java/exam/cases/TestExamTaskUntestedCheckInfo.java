package exam.cases;

import cn.kxy.authentication.business.CertificateBusiness;
import cn.kxy.base.business.BaseBusiness;
import cn.kxy.examination.business.ExaminationTaskBusiness;
import cn.kxy.examination.business.MyExamTaskBusiness;
import cn.kxy.examination.business.PaperBusiness;
import cn.kxy.setting.bussiness.TraineeBusiness;
import cn.kxy.setting.bussiness.UserBusiness;
import init.cases.InitExam;
import com.alibaba.fastjson.JSONPath;
import com.lazy.common.utils.CommonData;
import com.lazy.common.utils.DateUtil;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import java.math.BigDecimal;
//import io.qameta.allure.Feature;

//@Feature("考试前，人员监控，试题分析，补考明细，我的考试任务信息校验")
public class TestExamTaskUntestedCheckInfo extends InitExam{
	String exam_task_name = "examfunction" + CommonData.getStringRandom(5);
	String regularname= "Regxamfunction" + CommonData.getStringRandom(5);
	@Test(description = "未考试前，初始化数据",priority = 1)
	public void init () {
		//新增考试
		ExaminationTaskBusiness.creatRewardExamTask("1", "show", "60", "100", "2", exam_task_name,
				DateUtil.getRegularDate(0), DateUtil.getRegularDate(2), "false", UserBusiness.getUserId(),
				PaperBusiness.getIdByKeyword(BaseBusiness.paper_name), "60", "", "false", "1",
				CertificateBusiness.getIdByKeyword("AdvancedAutoTester"), "12", "0", "0", "0", "0", "true", "false",
				UserBusiness.getUserId(), "{\"missScore\":4,\"passScore\":6,\"unPassScore\":2}");
		
		ExaminationTaskBusiness.creatRewardExamTask("1", "show", "60", "100", "2", regularname,
				DateUtil.getRegularDate(1), DateUtil.getRegularDate(2), "false", UserBusiness.getUserId(),
				PaperBusiness.getIdByKeyword(BaseBusiness.paper_name), "60", "", "false", "1",
				CertificateBusiness.getIdByKeyword("AdvancedAutoTester"), "12", "0", "0", "0", "0", "true", "false",
				UserBusiness.getUserId(), "{\"missScore\":4,\"passScore\":6,\"unPassScore\":2}");
	}
	
	@Test(description="对未开始的考试列表进行校验",priority = 2)
	public void testNotYetStartExam() {
		String res = ExaminationTaskBusiness.queryList(regularname, "1", "false","");
		String title = (String)JSONPath.read(res, "$.list[0].title");
		int examPersonCount = (int)JSONPath.read(res, "$.list[0].examPersonCount");
		int examStatus = (int)JSONPath.read(res, "$.list[0].examStatus");
		int unExamNum = (int)JSONPath.read(res, "$.list[0].unExamNum");
		int pendingCount = (int)JSONPath.read(res, "$.list[0].pendingCount");
		Assert.assertEquals(title, regularname,"考试前对进行中的考试信息实际返回结果："+res);
		Assert.assertEquals(examPersonCount, 0,"考试前对进行中的考试信息实际返回结果："+res);
		Assert.assertEquals(examStatus, 0,"考试前对进行中的考试信息实际返回结果："+res);
		Assert.assertEquals(unExamNum, 1,"考试前对进行中的考试信息实际返回结果："+res);
		Assert.assertEquals(pendingCount, 0,"考试前对进行中的考试信息实际返回结果："+res);
	}

	
	@Test(description = "考试前对进行中的考试信息进行校验",priority = 3)
	public void testExamListCheckValue() {
		String res = ExaminationTaskBusiness.queryList(exam_task_name, "2", "false","");
		String title = (String)JSONPath.read(res, "$.list[0].title");
		int examPersonCount = (int)JSONPath.read(res, "$.list[0].examPersonCount");
		int examStatus = (int)JSONPath.read(res, "$.list[0].examStatus");
		int unExamNum = (int)JSONPath.read(res, "$.list[0].unExamNum");
		int pendingCount = (int)JSONPath.read(res, "$.list[0].pendingCount");
		Assert.assertEquals(title, exam_task_name,"考试前对进行中的考试信息实际返回结果："+res);
		Assert.assertEquals(examPersonCount, 0,"考试前对进行中的考试信息实际返回结果："+res);
		Assert.assertEquals(examStatus, 0,"考试前对进行中的考试信息实际返回结果："+res);
		Assert.assertEquals(unExamNum, 1,"考试前对进行中的考试信息实际返回结果："+res);
		Assert.assertEquals(pendingCount, 0,"考试前对进行中的考试信息实际返回结果："+res);
		
	}
	String exam_task_id = "";
	
	@Test(description="考试前，对未考人员信息进行校验",priority = 4)
	public void testCheckUntestedInfo() {
		exam_task_id = ExaminationTaskBusiness.getIdByKeyword(exam_task_name) ;
		String res = ExaminationTaskBusiness.queryUntestedInfo(UserBusiness.getUsername(),exam_task_id, "2");
		String name = (String) JSONPath.read(res, "$.data.pageInfo.list[0].name");
		String departmentName = (String) JSONPath.read(res, "$.data.pageInfo.list[0].departmentName");
		int total = (int) JSONPath.read(res, "$.data.pageInfo.total");
		Assert.assertTrue(!departmentName.isEmpty(), "查看缺考和未考的信息返回结果：" + res);
		Assert.assertEquals(name, UserBusiness.getUsername(), "查看缺考和未考的信息返回结果：" + res);
		Assert.assertTrue(total == 1, "查看缺考和未考的信息返回结果：" + res);
	}
	@Test(description="考试前,对我的考试任务待考试的信息校验",priority = 5)
	public void testCheckMyExamTask() {
		String res = MyExamTaskBusiness.queryMyExamTask("unfinished", exam_task_name);
		String name = (String) JSONPath.read(res, "$.list[0].title");
		int award_score = (int) JSONPath.read(res, "$.list[0].award_score");
		int makeup_count = (int) JSONPath.read(res, "$.list[0].makeup_count");
		BigDecimal total_score = (BigDecimal) JSONPath.read(res, "$.list[0].total_score");
		
		BigDecimal exptotal_score = new BigDecimal("100.0");
		String status = (String) JSONPath.read(res, "$.list[0].status");
		BigDecimal score = (BigDecimal) JSONPath.read(res, "$.list[0].score");
		
		BigDecimal expscore = new BigDecimal("0.0");
		Assert.assertEquals(name, exam_task_name,"考试前,对我的考试任务待考试的信息校验实际返回结果："+res);
		Assert.assertEquals(award_score, 6,"考试前,对我的考试任务待考试的信息校验实际返回结果："+res);
		Assert.assertEquals(makeup_count, 0,"考试前,对我的考试任务待考试的信息校验实际返回结果："+res);
		Assert.assertEquals(total_score, exptotal_score,"考试前,对我的考试任务待考试的信息校验实际返回结果："+res);
		Assert.assertEquals(status, "unfinished","考试前,对我的考试任务待考试的信息校验实际返回结果："+res);
		Assert.assertEquals(score, expscore,"考试前,对我的考试任务待考试的信息校验实际返回结果："+res);
	} 
	@Test(description="考试前，对人员监控的应考人员信息进行校验",priority = 6)
	public void testQueryPersonnelmonitorShouldBeExam() {
		String res = ExaminationTaskBusiness.queryPersonnelmonitorResult(exam_task_id, "0", "", "", "");
		String title = (String) JSONPath.read(res, "$.data.planExam.title");
		int passLine = (int) JSONPath.read(res, "$.data.planExam.passLine");
		String name = (String) JSONPath.read(res, "$.data.planUser.list[0].userName");
		String departName = (String) JSONPath.read(res, "$.data.planUser.list[0].departName");
		
		int makeupCount = (int) JSONPath.read(res, "$.data.planUser.list[0].makeupCount");
		String statusName = (String) JSONPath.read(res, "$.data.planUser.list[0].statusName");
		BigDecimal score = (BigDecimal) JSONPath.read(res, "$.data.planUser.list[0].score");
		BigDecimal expscore = new BigDecimal("0.0");
		Assert.assertEquals(makeupCount, 0,"考试前，对人员监控的应考人员信息进行校验实际返回结果："+res);
		Assert.assertEquals(score, expscore,"考试前，对人员监控的应考人员信息进行校验实际返回结果："+res);
		Assert.assertEquals(statusName, "待考试","考试前，对人员监控的应考人员信息进行校验实际返回结果："+res);
		Assert.assertTrue(!departName.isEmpty(), "考试前，对人员监控的应考人员信息进行校验(所属部门不为空)实际返回结果："+res);
		Assert.assertEquals(name, UserBusiness.getUsername(),"考试前，对人员监控的应考人员信息进行校验实际返回结果："+res);
		Assert.assertEquals(title, exam_task_name,"考试前，对人员监控的应考人员信息进行校验实际返回结果："+res);
		Assert.assertEquals(passLine, 60,"考试前，对人员监控的应考人员信息进行校验实际返回结果："+res);
	}
	@Test(description="考试前，对人员监控的及格人员信息进行校验",priority = 7)
	public void testQueryPersonnelmonitorPassInfo() {
		String res = ExaminationTaskBusiness.queryPersonnelmonitorResult(exam_task_id, "2", "", "", "");
		int total = (int) JSONPath.read(res, "$.data.planUser.total");
		Assert.assertEquals(total, 0,"考试前，对人员监控的及格人员信息进行校验实际返回结果："+res);
	}
	
	@Test(description="考试前，对人员监控的应考人员的搜索进行校验",priority = 8)
	public void testQueryPersonnelmonitorList() {
		String traine = TraineeBusiness.queryPostsList();
		String id = (String)JSONPath.read(traine, "$.posts[0].id");
		String res = ExaminationTaskBusiness.queryPersonnelmonitorResult(exam_task_id, "0", "1", id, "");
		int total = (int) JSONPath.read(res, "$.data.planUser.total");
		Assert.assertTrue(total>=0,"考试前，对人员监控的应考人员的搜索进行校验，（postId，departmentid都存在value时）实际返回结果："+res);
	}
	
	@Test(description="考试前，对人员监控的不及格人员信息进行校验",priority = 9)
	public void testQueryPersonnelmonitorFailInfo() {
		String res = ExaminationTaskBusiness.queryPersonnelmonitorResult(exam_task_id, "1", "", "", "");
		int total = (int) JSONPath.read(res, "$.data.planUser.total");
		Assert.assertEquals(total, 0,"考试前，对人员监控的不及格人员信息进行校验实际返回结果："+res);
	}
	
	@Test(description="考试前，对人员监控的未考人员信息进行校验",priority = 10)
	public void testQueryPersonnelmonitorShouldBeNotExam() {
		String res = ExaminationTaskBusiness.queryPersonnelmonitorResult(exam_task_id, "3", "", "", "");
		String title = (String) JSONPath.read(res, "$.data.planExam.title");
		int passLine = (int) JSONPath.read(res, "$.data.planExam.passLine");
		String name = (String) JSONPath.read(res, "$.data.planUser.list[0].userName");
		String departName = (String) JSONPath.read(res, "$.data.planUser.list[0].departName");
		String statusName = (String) JSONPath.read(res, "$.data.planUser.list[0].statusName");
		BigDecimal score = (BigDecimal) JSONPath.read(res, "$.data.planUser.list[0].score");
		BigDecimal expscore = new BigDecimal("0.0");
		Assert.assertEquals(score, expscore,"考试前，对人员监控的未考人员信息进行校验实际返回结果："+res);
		Assert.assertEquals(statusName, "待考试","考试前，对人员监控的未考人员信息进行校验实际返回结果："+res);
		Assert.assertTrue(!departName.isEmpty(), "考试前，对人员监控的未考人员信息进行校验实际返回结果："+res);
		Assert.assertEquals(name, UserBusiness.getUsername(),"考试前，对人员监控的未考人员信息进行校验实际返回结果："+res);
		Assert.assertEquals(title, exam_task_name,"考试前，对人员监控的未考人员信息进行校验实际返回结果："+res);
		Assert.assertEquals(passLine, 60,"考试前，对人员监控的未考人员信息进行校验实际返回结果："+res);
		
	}
	
	@Test(description="考试前，对补考明细信息进行校验",priority = 11)
	public void testQueryMakeupExamMonitors() {
		String res = ExaminationTaskBusiness.queryMakeupExamMonitors(exam_task_id, "0","","","","");
		int total = (int) JSONPath.read(res, "$.data.planUser.total");
		Assert.assertEquals(total, 0,"考试前，对补考明细信息进行校验实际返回结果："+res);
	}
	
	@Test(description = "删除考试",priority = 12)
	public void endTest () {
		String res = ExaminationTaskBusiness.deleteExamTask(exam_task_id);
		 ExaminationTaskBusiness.deleteExamTask(ExaminationTaskBusiness.getIdByKeyword(regularname));
		Reporter.log("用例执行完成，删除考试"+res);
	}
	
}
