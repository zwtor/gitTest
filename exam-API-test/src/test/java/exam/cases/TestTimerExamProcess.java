package exam.cases;

import cn.kxy.base.business.BaseBusiness;
import cn.kxy.examination.business.PaperBusiness;
import cn.kxy.examination.business.TimerExamTaskBusiness;
import cn.kxy.setting.bussiness.UserBusiness;
import com.alibaba.fastjson.JSONPath;
import com.lazy.common.utils.CommonData;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestTimerExamProcess {
	public static String timer_name = "TimerExam" + CommonData.getStringRandom(3);
	public static String edit_name = "editTimerExam" + CommonData.getStringRandom(2);
	String time_id = "";
	String scheduler_id = "";
	
	@Test(description = "新增定时考试任务",priority=1)
	public void testCreatTimerExamTask() {
		String res = TimerExamTaskBusiness.creatTimerExamTask("60", "100", PaperBusiness.getIdByKeyword(BaseBusiness.paper_name), timer_name,
				UserBusiness.getUserId(), "45", "60", "10","false");
		Assert.assertTrue(res.contains("template_id"),"新增定时考试任务，实际返回结果："+res);
	}

	@Test(description = "新增定时考试任务时，对参数进行校验",dataProvider ="CheckCreatTimerExamTask",priority=2)
	public void testCheckCreatTimerExamTask(String type,String  pass_line,String total_score,String paper_ids,String title,String userid,String exam_duration
			,String passing_score,String plan_term,String exp) {
		String res = TimerExamTaskBusiness.creatTimerExamTask(pass_line, total_score, paper_ids, title,
				userid, exam_duration, passing_score, plan_term,"false");
		String msg = (String) JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, exp, "新增定时考试任务时，对参数进行校验实际返回结果：" + res);
	}

	@Test(description = "编辑定时考试任务时，对参数进行校验",dataProvider ="CheckCreatTimerExamTask",priority=3)
	public void testCheckEditTimerExamTask(String type,String  pass_line,String total_score,String paper_ids,String title,String userid,String exam_duration
			,String passing_score,String plan_term,String exp) {
		String res = TimerExamTaskBusiness.creatTimerExamTask(pass_line, total_score, paper_ids, title,
				userid, exam_duration, passing_score, plan_term,"false");
		String msg = (String) JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, exp, "编辑定时考试任务时，对参数进行校验实际返回结果：" + res);
	}
	
	@Test(description = "查看全部定时考试任务列表 ",priority=4)
	public void testQueryTimerExamTask() {
		
		String timer_res = TimerExamTaskBusiness.queryTimerExamTask(timer_name, "all");
		
		time_id = (String)JSONPath.read(timer_res, "$.list[0].id");
		scheduler_id = (String)JSONPath.read(timer_res, "$.list[0].schedule_id");
		String title = (String) JSONPath.read(timer_res, "$.list[0].title");
		String user_name = (String)JSONPath.read(timer_res, "$.list[0].update_user");
		String status = (String)JSONPath.read(timer_res, "$.list[0].status");
		String type = (String)JSONPath.read(timer_res, "$.list[0].type");
		Long update_time = (Long)JSONPath.read(timer_res, "$.list[0].update_time");
		
		Assert.assertNotNull(user_name,"查看全部定时考试任务列表 ,更新人进行校验，实际返回结果"+timer_res);
		Assert.assertEquals(type, "once","查看全部定时考试任务列表 ,状态进行校验，实际返回结果:"+timer_res);
		Assert.assertEquals(status, "on","查看全部定时考试任务列表 ,状态进行校验，实际返回结果:"+timer_res);
		Assert.assertNotNull(update_time,"查看全部定时考试任务列表 ,更新时间进行校验，实际返回结果"+timer_res);
		Assert.assertEquals(title, timer_name, "查看全部定时考试任务列表 实际返回结果：" + timer_res);
	}

	@Test(description = "停用定时考试任务 ", priority=5)
	public void testCloseStatusTimerExam() {
		if (TimerExamTaskBusiness.getFirstStatus(timer_name).equals("on")) {
			String res = TimerExamTaskBusiness.updateStatusTimerExam("off",time_id);
			Assert.assertEquals(TimerExamTaskBusiness.getFirstStatus(timer_name), "off", "停用定时考试任务实际返回结果;" + res);
		} else {
			Reporter.log("任务的状态是停用状态 ，无法停用");
		}

	}
	
	@Test(description = "查看已停用定时考试任务列表 ", priority=6)
	public void testCloseQueryTimerExamTask() {
		String res = TimerExamTaskBusiness.queryTimerExamTask("", "off");
		String title = (String) JSONPath.read(res, "$.list[0].title");

		int total = (int) JSONPath.read(res, "$.total");
		Assert.assertEquals(title, timer_name, "查看已停用定时考试任务列表实际返回结果：" + res);
		Assert.assertTrue(total >= 0, "查看已停用定时考试任务列表实际返回结果：" + res);
	}

	@Test(description = "查看定时考试任务详情", priority=7)
	public void testQuetyInfoTimerExam() {
//		String res = TimerExamTaskBusiness.quetyInfoTimerExam(time_id);
//		String name = (String) JSONPath.read(res, "$.title");
//		Assert.assertEquals(name, timer_name, "查看定时考试任务详情实际返回结果：" + res);
	}

	@Test(description = "单次启用定时考试任务 ",priority=8)
	public void testOpenOnceStatusTimerExam() {
		if (TimerExamTaskBusiness.getFirstStatus(timer_name).equals("off")) {
			String res = TimerExamTaskBusiness.openStatusTimerExam(time_id,scheduler_id);
			Assert.assertEquals(TimerExamTaskBusiness.getFirstStatus(timer_name), "on", "单次启用定时考试任务实际返回结果;" + res);
		} else {
			Reporter.log("任务的状态是启用状态 ，无法停用");
		}

	}

	@Test(description = "循环启用定时考试任务 ",priority=9)
	public void testOpenLoopStatusTimerExam() {
		if (TimerExamTaskBusiness.getFirstStatus(timer_name).equals("off")) {
			String res = TimerExamTaskBusiness.openStatusLoopTimerExam("2","5",time_id,scheduler_id);
			Assert.assertEquals(TimerExamTaskBusiness.getFirstStatus(timer_name), "on", "循环启用定时考试任务实际返回结果;" + res);
		} else {
			Reporter.log("任务的状态是启用状态 ，无法停用");
		}
	}
	
	@Test(description = "查看已启用定时考试任务列表 ",priority=10)
	public void testOpenQueryTimerExamTask() {
		String res = TimerExamTaskBusiness.queryTimerExamTask("", "on");
		String title = (String) JSONPath.read(res, "$.list[0].title");

		int total = (int) JSONPath.read(res, "$.total");
		Assert.assertEquals(title, timer_name, "查看已启用定时考试任务列表 实际返回结果：" + res);
		Assert.assertTrue(total >=1, "查看已启用定时考试任务列表 实际返回结果：" + res);
	}
	@Test(description = "编辑定时考试任务", priority=11)
	public void testEditTimerExamTask() {
//		TimerExamTaskBusiness.editTimeExamTask("50", "20", PaperBusiness.getIdByKeyword(BaseBusiness.paper_name), edit_name,
//				UserBusiness.getUserId(), "60", "10", "20",time_id,scheduler_id);
//		String res = TimerExamTaskBusiness.quetyInfoTimerExam(time_id);
//		String name = (String) JSONPath.read(res, "$.title");
//		Assert.assertEquals(name, edit_name, "编辑定时考试任务实际返回结果：" + res);
	}
	@Test(description = "删除定时考试任务 ",priority=12)
	public void testDeleteTimerExamTask() {
		int start = TimerExamTaskBusiness.getTotal();
		String res = TimerExamTaskBusiness.deleteTimerExamTask(time_id);
		int end = TimerExamTaskBusiness.getTotal();
		String msg = (String) JSONPath.read(res, "$.status");
		Assert.assertTrue(start-end==1,"删除定时考试任务实际返回结果："+res);
		Assert.assertEquals(msg, "true","删除定时考试任务实际返回结果："+res);
	}
	@DataProvider(name = "CheckCreatTimerExamTask")
	public Object[][] creatTimerExamTask() {
		Object[][] obj = new Object[][] { {"对定时考试模板名称进行校验","60", "100", PaperBusiness.getIdByKeyword(BaseBusiness.paper_name), "",
			UserBusiness.getUserId(), "45", "60", "10","考试名称不能为空！"},
			{"对分配对象做非空校验","60", "100", PaperBusiness.getIdByKeyword(BaseBusiness.paper_name), timer_name,
				"", "45", "60", "15","请选择分配对象为空！"}};
		return obj;
	}
}
