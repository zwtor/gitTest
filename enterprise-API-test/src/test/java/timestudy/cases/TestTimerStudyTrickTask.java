package timestudy.cases;

import cn.kxy.course.resources.bussiness.ArticleBusiness;
import cn.kxy.study.business.TimerStudyTaskTemplate;
import init.cases.InitStudyAuthCourse;
import com.alibaba.fastjson.JSONPath;
import com.lazy.common.utils.CommonData;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestTimerStudyTrickTask extends InitStudyAuthCourse{

	String title_01 = "StrickTimeStudy"+CommonData.getStringRandom(4);
	String title_02 = "StrickTimeStudy2"+CommonData.getStringRandom(4);
	String art_id = "";
	String id_01 = "";
	String id_02 = "";
	@Test(description = "新增不勾选阶段间闯关的定时学习任务",priority = 1)
	public void testAddStageTimerStudy() {
		art_id = ArticleBusiness.getIdByKeyword(articlename);
		String res = TimerStudyTaskTemplate.addStageTimerStudy(title_01, art_id, "false");
		String template_id = (String)JSONPath.read(res, "$.template_id");
		Assert.assertNotNull(template_id, "新增不勾选阶段间闯关的定时学习任务，实际返回结果："+res);
	}
	@Test(description = "查看定时学习模板详情,对阶段间字段进行校验",priority = 2)
	public void testQueryInfo() {
		String res = TimerStudyTaskTemplate.queryList(title_01, "all");
		id_01 = (String)JSONPath.read(res,"$.list[0].id");
		String info_res = TimerStudyTaskTemplate.queryInfo(id_01);
		String stage_pass = (String)JSONPath.read(info_res, "$.stage_pass");
		Assert.assertEquals(stage_pass, "false","查看定时学习模板详情,对阶段间字段进行校验，实际返回结果："+res);
	}
	@Test(description = "删除定时学习模板",priority = 3)
	public void testDeleteTimerFirstStudy() {
		String res = TimerStudyTaskTemplate.deleteTimerStudy(id_01);
		String status = (String)JSONPath.read(res, "$.status");
		Assert.assertEquals(status, "true","定时任务生效后，删除定时学习模板，实际返回结果："+res);
	}
	@Test(description = "新增不勾选阶段间闯关的定时学习任务",priority = 4)
	public void testAddStageTimerStudyIsTrue() {
		art_id = ArticleBusiness.getIdByKeyword(articlename);
		String res = TimerStudyTaskTemplate.addStageTimerStudy(title_02, art_id, "true");
		String template_id = (String)JSONPath.read(res, "$.template_id");
		Assert.assertNotNull(template_id, "新增不勾选阶段间闯关的定时学习任务，实际返回结果："+res);
	}
	@Test(description = "查看定时学习模板详情",priority = 5)
	public void testQueryInfo1() {
		String res = TimerStudyTaskTemplate.queryList(title_02, "all");
		id_02 = (String)JSONPath.read(res,"$.list[0].id");
		String info_res = TimerStudyTaskTemplate.queryInfo(id_02);
		String stage_pass = (String)JSONPath.read(info_res, "$.stage_pass");
		Assert.assertEquals(stage_pass, "true","查看定时学习模板详情,对阶段间字段进行校验，实际返回结果："+res);
	}
	@Test(description = "删除定时学习模板",priority = 6)
	public void testDeleteTimerFirstStudy1() {
		String res = TimerStudyTaskTemplate.deleteTimerStudy(id_02);
		String status = (String)JSONPath.read(res, "$.status");
		Assert.assertEquals(status, "true","定时任务生效后，删除定时学习模板，实际返回结果："+res);
	}
	
}
