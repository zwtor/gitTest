package study.cases;

import cn.kxy.study.business.StudyTaskBusiness;
import com.alibaba.fastjson.JSONPath;
import com.lazy.common.utils.DateUtil;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestExtremelyStudyTask {
	@Test(description = "延期任务时，不传id",priority=1)
	public void testplanStudyDelayIdIsNull() {
		String res = StudyTaskBusiness.planStudyDelay("", DateUtil.getRegularDateForHHMM(2));
		String msg = (String)JSONPath.read(res, "$.msg");
		System.out.println("this is StudyTask module");
		Assert.assertEquals(msg, "无效的参数","延期任务时，不传id"+res);
	}
	@Test(description="崔考时id不存在",priority=2)
	public void testSendUrgeStudyMessageIdNotExist() {
//		String res = StudyTaskBusiness.sendUrgeStudyMessage("");
//		String msg = (String)JSONPath.read(res, "$.msg");
//		Assert.assertEquals(msg, "学习计划不存在","崔考时id不存在，id不存在时"+res);
	}
	@Test(description="变更学习任务时，id为null",priority=3)
	public void testChangeStudyUsersIdIsNull() {
		String res = StudyTaskBusiness.changeStudyUsers("", "");
		String msg = (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "参数不符合要求","变更学习任务时，id为null"+res);
	}
	@Test(description="变更学习任务时，id不存在",priority=4)
	public void testChangeStudyUsersIdNotExist() {
		String res = StudyTaskBusiness.changeStudyUsers("2525", "");
		String msg = (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "考试计划不存在","变更学习任务时，id不存在"+res);
	}
	@Test(description="删除学习任务时，id不存在",priority=5)
	public void testChangeStudyUsersI() {
		String res = StudyTaskBusiness.deleteStudyTask("42453");
		String msg = (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "删除学习计划失败","删除学习任务时，id不存在"+res);
	}
	
}
