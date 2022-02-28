package timestudy.cases;

import cn.kxy.course.resources.bussiness.ArticleBusiness;
import cn.kxy.study.business.TimerStudyTaskTemplate;
import init.cases.InitStudyAuthCourse;
import com.alibaba.fastjson.JSONPath;
import com.lazy.common.utils.CommonData;
import com.lazy.common.utils.DateUtil;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestAuthRangeTimeStudy extends InitStudyAuthCourse{
	String schedulers_name = "AuthFalseTimerStudyTask"+CommonData.getStringRandom(5);
	String loop_id = "";
	@Test(description="新增循环定时学习模板---不勾选监督人只能查看管辖范围内数据",priority=1)
	public void testAddschedulersTimerStudyTask() {
		String res = TimerStudyTaskTemplate.addschedulersTimerStudyTask(schedulers_name, DateUtil.getRegularDateForHHMM(1), 
				ArticleBusiness.getIdByKeyword(articlename),"false");
		String template_id = (String)JSONPath.read(res, "$.template_id");
		Assert.assertNotNull(template_id, "新增循环定时学习模板---不勾选监督人只能查看管辖范围内数据，实际返回结果："+res);
	}
	
	@Test(description = "获取id",priority = 2)
	public void testGetFalseId() {
		String list_res = TimerStudyTaskTemplate.queryList(schedulers_name, "all");
		loop_id = (String)JSONPath.read(list_res,"$.list[0].id");
	}
	@Test(description = "查看详情--不勾选监督人只能查看管辖范围内数据",priority =3)
	public void testQueryFalseInfo() {
		String res = TimerStudyTaskTemplate.queryInfo(loop_id);
		String authority_range = (String) JSONPath.read(res, "$.authority_range");
		Assert.assertEquals(authority_range, "false","查看详情--不勾选监督人只能查看管辖范围内数据"+res);
	}
	
	@Test(description="删除定时学习模板",priority=4)
	public void testDeleteTimerStudyFalse() {
		String res = TimerStudyTaskTemplate.deleteTimerStudy(loop_id);
		String status = (String)JSONPath.read(res, "$.status");
		Assert.assertEquals(status, "true","删除定时学习模板，实际返回结果："+res);
	}
	
	String schedulers_name_1 = "AuthTimerStudyTaskTrue"+CommonData.getStringRandom(5);
	String loop_id_1 = "";
	@Test(description="新增循环定时学习模板---勾选监督人只能查看管辖范围内数据",priority=5)
	public void testAddschedulersTimerStudyTaskTrue() {
		String res = TimerStudyTaskTemplate.addschedulersTimerStudyTask(schedulers_name_1, DateUtil.getRegularDateForHHMM(1), 
				ArticleBusiness.getIdByKeyword(articlename),"true");
		String template_id = (String)JSONPath.read(res, "$.template_id");
		Assert.assertNotNull(template_id, "新增循环定时学习模板---不勾选监督人只能查看管辖范围内数据，实际返回结果："+res);
	}
	
	@Test(description = "获取id",priority = 6)
	public void testGetTrueId() {
		String list_res = TimerStudyTaskTemplate.queryList(schedulers_name_1, "all");
		loop_id_1 = (String)JSONPath.read(list_res,"$.list[0].id");
	}
	@Test(description = "查看详情--勾选监督人只能查看管辖范围内数据",priority =7)
	public void testQueryTrueInfo() {
		String res = TimerStudyTaskTemplate.queryInfo(loop_id_1);
		String authority_range = (String) JSONPath.read(res, "$.authority_range");
		Assert.assertEquals(authority_range, "true","查看详情--不勾选监督人只能查看管辖范围内数据"+res);
	}
	
	@Test(description="删除定时学习模板",priority=8)
	public void testDeleteTimerStudyTrue() {
		String res = TimerStudyTaskTemplate.deleteTimerStudy(loop_id_1);
		String status = (String)JSONPath.read(res, "$.status");
		Assert.assertEquals(status, "true","删除定时学习模板，实际返回结果："+res);
	}
	
}
