package exam.cases;

import cn.kxy.base.business.BaseBusiness;
import cn.kxy.examination.business.PaperBusiness;
import cn.kxy.examination.business.TimerExamTaskBusiness;
import cn.kxy.setting.bussiness.UserBusiness;
import com.alibaba.fastjson.JSONPath;
import com.lazy.common.utils.CommonData;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestTimeAuthRangeExam {
	public  String timer_name = "AuthRangeFalseTimerExam" + CommonData.getStringRandom(5);
	String time_id = "";
	@Test(description = "新增定时考试任务，不勾选监督人只能查看管辖范围内数据",priority=1)
	public void testCreatTimerExamTask() {
		String res = TimerExamTaskBusiness.creatTimerExamTask("60", "100", PaperBusiness.getIdByKeyword(BaseBusiness.paper_name), timer_name,
				UserBusiness.getUserId(), "45", "60", "10","false");
		Assert.assertTrue(res.contains("template_id"),"，不勾选监督人只能查看管辖范围内数据，实际返回结果："+res);
	}
	
	@Test(description = "获取id",priority = 2)
	public void testGetFalseId() {
		String timer_res = TimerExamTaskBusiness.queryTimerExamTask(timer_name, "all");
		time_id = (String)JSONPath.read(timer_res, "$.list[0].id");
	}
	@Test(description = "查看定时考试任务详情--不勾选监督人只能查看管辖范围内数据",priority = 3)
	public void testQuetyInfoTimerExam() {
		String res = TimerExamTaskBusiness.quetyInfoTimerExam(time_id);
		String authority_range = (String)JSONPath.read(res, "$.authority_range");
//		Assert.assertEquals(authority_range, "false","查看定时考试任务详情--不勾选监督人只能查看管辖范围内数据"+res);
	}
	@Test(description = "删除定时考试任务",priority = 4)
	public void endTest () {
		TimerExamTaskBusiness.deleteTimerExamTask(time_id);
	}
	
	public  String timer_name_1 = "AuthRangeTrueTimerExam" + CommonData.getStringRandom(5);
	String time_id_1 = "";
	@Test(description = "新增定时考试任务，不勾选监督人只能查看管辖范围内数据",priority=5)
	public void testCreatTimerTrueExamTask() {
		String res = TimerExamTaskBusiness.creatTimerExamTask("60", "100", PaperBusiness.getIdByKeyword(BaseBusiness.paper_name), timer_name_1,
				UserBusiness.getUserId(), "45", "60", "10","true");
		Assert.assertTrue(res.contains("template_id"),"，不勾选监督人只能查看管辖范围内数据，实际返回结果："+res);
	}
	
	@Test(description = "获取id",priority = 6)
	public void testGetTrueId() {
		String timer_res = TimerExamTaskBusiness.queryTimerExamTask(timer_name_1, "all");
		time_id_1 = (String)JSONPath.read(timer_res, "$.list[0].id");
	}
	@Test(description = "查看定时考试任务详情",priority = 7)
	public void testQuetyTrueInfoTimerExam() {
//		String res = TimerExamTaskBusiness.quetyInfoTimerExam(time_id_1);
//		String authority_range = (String)JSONPath.read(res, "$.authority_range");
//		Assert.assertEquals(authority_range, "true","新增考试任务--勾选监督人只能查看管辖范围内数据"+res);
	}
	@Test(description = "删除定时考试任务",priority = 8)
	public void deleteTimeTask () {
		TimerExamTaskBusiness.deleteTimerExamTask(time_id_1);
	}
	
	
}
