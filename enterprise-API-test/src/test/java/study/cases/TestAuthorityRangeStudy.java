package study.cases;

import cn.kxy.course.resources.bussiness.CourseBusiness;
import cn.kxy.study.business.MyStudyTask;
import cn.kxy.study.business.StudyTaskBusiness;
import init.cases.InitStudyAuthCourse;
import com.alibaba.fastjson.JSONPath;
import com.lazy.common.utils.CommonData;
import com.lazy.common.utils.DateUtil;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test(groups = {"studyProject"})
public class TestAuthorityRangeStudy extends InitStudyAuthCourse {

	String title_0 = "AuthorityStudyFalse" + CommonData.getStringRandom(5);
	String id_0 = "";
	@Test(description = "添加不勾选管辖范围的学习任务", priority = 1)
	public void testAddAuthorityRangeStudy() {
		String res = StudyTaskBusiness.addAuthorityRangeStudy(title_0, DateUtil.getTimeStamp(0, ""),
				DateUtil.getTimeStamp(3, ""), "false", CourseBusiness.getIdByPage(course_name));
		String msg = (String) JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "新增计划成功！", "添加不勾选管辖范围的学习任务，实际返回结果:" + res);
		String list_res = MyStudyTask.queryList(title_0,"0");
		id_0 = (String)JSONPath.read(list_res, "$.list[0].id");
	}
	
	@Test(description = "查看数据列表不应显示显示数据受管辖范围影响", priority = 2)
	public void testQueryTrainMonitor() {
		String res = StudyTaskBusiness.queryTrainMonitor("", id_0);
		String authorityRange = (String) JSONPath.read(res, "$.data.authorityRange");
		Assert.assertEquals(authorityRange, "false","查看数据列表是否显示数据受管辖范围影响：" + res);
	}
	
	@Test(description = "查看学习任务详情--不勾选管辖范围的情况",priority = 3)
	public void testQueryInfoFalse() {
		String res = StudyTaskBusiness.queryInfo(id_0);
		String authorityRange = (String)JSONPath.read(res, "$.bizPlanVo.authorityRange");
		Assert.assertEquals(authorityRange, "false","查看学习任务详情--不勾选管辖范围的情况：" + res);
	}
	
	@Test(description = "删除不勾选管辖范围的学习任务",priority = 4)
	public void deleteStudyTask() {
		String res = StudyTaskBusiness.deleteStudyTask(id_0);
		String msg = (String) JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "删除学习计划成功", "删除不勾选管辖范围的学习任务，实际返回结果:" + res);
	}
	
	String title_1 = "AuthorityStudyTrue" + CommonData.getStringRandom(5);
	String id_1 = "";
	
	@Test(description = "添加勾选管辖范围的学习任务", priority = 5)
	public void testAddAuthorityRangeStudyTrue() {
		String res = StudyTaskBusiness.addAuthorityRangeStudy(title_1, DateUtil.getTimeStamp(0, ""),
				DateUtil.getTimeStamp(3, ""), "true", CourseBusiness.getIdByPage(course_name));
		String msg = (String) JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "新增计划成功！", "添加勾选管辖范围的学习任务，实际返回结果:" + res);
		String list_res = MyStudyTask.queryList(title_1,"0");
		id_1 = (String)JSONPath.read(list_res, "$.list[0].id");
	}
	
	@Test(description = "查看数据列表应显示显示数据受管辖范围影响", priority = 6)
	public void testQueryTrainMonitorTrue() {
		String res = StudyTaskBusiness.queryTrainMonitor("", id_1);
		String authorityRange = (String) JSONPath.read(res, "$.data.authorityRange");
		Assert.assertEquals(authorityRange, "true","查看数据列表显示数据受管辖范围影响：" + res);
	}
	
	@Test(description = "查看学习任务详情--勾选管辖范围的情况",priority = 7)
	public void testQueryInfoTrue() {
		String res = StudyTaskBusiness.queryInfo(id_1);
		String authorityRange = (String)JSONPath.read(res, "$.bizPlanVo.authorityRange");
		Assert.assertEquals(authorityRange, "true","查看学习任务详情--勾选管辖范围的情况：" + res);
	}
	
	@Test(description = "删除勾选管辖范围的学习任务",priority = 8)
	public void deleteStudyTaskTrue() {
		String res = StudyTaskBusiness.deleteStudyTask(id_1);
		String msg = (String) JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "删除学习计划成功", "删除勾选管辖范围的学习任务，实际返回结果:" + res);
	}
	
}
