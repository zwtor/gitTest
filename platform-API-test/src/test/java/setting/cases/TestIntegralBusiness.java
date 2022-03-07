package setting.cases;

import cn.kxy.course.resources.bussiness.ArticleBusiness;
import cn.kxy.course.resources.bussiness.CourseBusiness;
import cn.kxy.lecturer.business.LecturerListBusiness;
import cn.kxy.setting.bussiness.AdvanceSettingBusiness;
import cn.kxy.setting.bussiness.IntegralBusiness;
import cn.kxy.study.business.AppStudyBusiness;
import cn.kxy.study.business.MyStudyTask;
import cn.kxy.study.business.StudyTaskBusiness;
import init.cases.InitStudyAuthCourse;
import com.alibaba.fastjson.JSONPath;
import com.lazy.common.utils.CommonData;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestIntegralBusiness {
	
	String rule_group_id_01 = "";
	String rule_group_id_02 = "";
	String rule_group_id_03 = "";
	String rule_group_id_04 = "";
	String rule_group_id_05 = "";
	String rule_group_id_06 = "";
	String rule_group_id_07 = "";
	String credit_exchange_group = "";
	String course_name_02="Nalse"+CommonData.getStringRandom(6);
	@Test(description = "查看积分规则设置",priority=1)
	public void queryReewardRules() {
		String res = AdvanceSettingBusiness.queryReewardRules("1001");
		credit_exchange_group = (String)JSONPath.read(res, "$.credit_exchange_group");
		rule_group_id_01 = (String)JSONPath.read(res, "$.rules.list[0].rule_group_id");
		rule_group_id_02 = (String)JSONPath.read(res, "$.rules.list[1].rule_group_id");
		rule_group_id_03 = (String)JSONPath.read(res, "$.rules.list[2].rule_group_id");
		rule_group_id_04 = (String)JSONPath.read(res, "$.rules.list[3].rule_group_id");
		rule_group_id_05 = (String)JSONPath.read(res, "$.rules.list[4].rule_group_id");
		rule_group_id_06 = (String)JSONPath.read(res, "$.rules.list[5].rule_group_id");
		rule_group_id_07 = (String)JSONPath.read(res, "$.rules.list[6].rule_group_id");
	}
	
	@Test(description = "保存积分规则",priority = 2)
	public void testSaveRewardRules() {
//		IntegralBusiness.saveRewardRules(credit_exchange_group, DateUtil.getTimeStamp(0, ""), rule_group_id_01, rule_group_id_02,
//				rule_group_id_03, rule_group_id_04, rule_group_id_05, rule_group_id_06, rule_group_id_07);
//		Assert.assertTrue(res.contains("success"),"保存积分规则"+res);
	}
	
	String today_socre = "";
	@Test(priority = 3)
	public void testqueryMyIntegral() {
		String res = IntegralBusiness.queryMyIntegral();
		today_socre = (String)JSONPath.read(res, "$.today");
	}
	String course_id = "";
	@Test(description="新增原创、可下载、无自学奖励学分、全部用户的课程，保存到已发布",priority=4)
	public void testAddOriginalDownloadToAllrelease() {
		String res = CourseBusiness.addCourse(course_name_02, "1", "this is desription", LecturerListBusiness.getIdByKeyword(InitStudyAuthCourse.outer_name), 
				ArticleBusiness.getIdByKeyword("" ), "1", "3", "", "1", "0", "release");
		course_id = (String)JSONPath.read(res, "$.data[0]");
		String msg = (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "新增课程成功","新增课程到已发布列表实际返回结果："+res);
	}
	
	String course_id_01 = "";
	String course_id_02 = "";
	String course_id_03 = "";
	String course_id_04 = "";
	String title = "IntegralStudy"+CommonData.getStringRandom(5);
	
	@Test(description = "获取视频的id",priority = 5)
	public void testGetVideoId() {
		String res = CourseBusiness.getCourseList("0", "", "video", "");
		course_id_01 = (String)JSONPath.read(res,"$.list[0].id");
	}
	
	@Test(description = "获取音频的id",priority = 6)
	public void testGetaudioId() {
		String res = CourseBusiness.getCourseList("0", "", "audio", "");
		course_id_02 = (String)JSONPath.read(res,"$.list[0].id");
	}
	
	@Test(description = "获取文章的id",priority =7)
	public void testGeth5Id() {
		String res = CourseBusiness.getCourseList("0", "", "audio", "");
		course_id_03 = (String)JSONPath.read(res,"$.list[0].id");
	}
	
	@Test(description = "获取文档的id",priority = 8)
	public void testGetapplicationId() {
		String res = CourseBusiness.getCourseList("0", "", "application", "");
		course_id_04 = (String)JSONPath.read(res,"$.list[2].id");
	}
	
	@Test(description = "新增学习任务",priority = 9)
	public void testAddStudyTask() {
		String res = IntegralBusiness.addStudyTask(title, course_id_01, course_id_02, course_id_03, course_id_04);
		String msg= (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "新增计划成功！","添加线下学习任务，实际返回结果:"+res);
	}
	String study_id = "";
	@Test(description = "获取学习的id",priority = 10)
	public void testGetId() {
		String list_res = MyStudyTask.queryList(title,"0");
		study_id = (String)JSONPath.read(list_res, "$.list[0].id");
	}
	@Test(description = "APP端加载混合学习资源", priority = 11)
	public void testLoadResources() {
		String res = AppStudyBusiness.loadResource(study_id);
		String status = (String) JSONPath.read(res, "$.status");
		Assert.assertEquals(status, "success", "APP端加载混合学习资源,实际返回结果:" + res);
	}
	@Test(description = "APP端查看混合学习详情", priority = 12)
	public void testQueryAppInfo() {
		String res = AppStudyBusiness.queryInfo(study_id);
		Assert.assertTrue(res.contains("title"));
	}
	
	@Test(description = "保存第一个课件的进度", priority = 13)
	public void testsaveFirstProgress() {
//		String res = AppStudyBusiness.saveProgress("100", study_id, course_id_01, course_id_01);
//		int course_progress = (int) JSONPath.read(res, "$.course_progress");
//		Assert.assertEquals(course_progress, 100, "保存学习任务中的课件进度，实际返回结果：" + res);
	}
	
	@Test(description = "保存第二个课件的进度", priority = 14)
	public void testsaveSecondProgress() {
		String res = AppStudyBusiness.saveProgress("100", study_id, course_id_02, course_id_02);
		int course_progress = (int) JSONPath.read(res, "$.course_progress");
		Assert.assertEquals(course_progress, 100, "保存学习任务中的课件进度，实际返回结果：" + res);
	}
	
	@Test(description = "保存第三个课件的进度", priority = 15)
	public void testsaveThirdProgress() {
		String res = AppStudyBusiness.saveProgress("100", study_id, course_id_03, course_id_03);
		System.out.println(res);
		int course_progress = (int) JSONPath.read(res, "$.course_progress");
		Assert.assertEquals(course_progress, 100, "保存学习任务中的课件进度，实际返回结果：" + res);
	}
	@Test(description = "保存第四个课件的进度", priority = 16)
	public void testsavefourProgress() {
//		String res = AppStudyBusiness.saveProgress("100", study_id, course_id_04, course_id_04);
//		int course_progress = (int) JSONPath.read(res, "$.course_progress");
//		Assert.assertEquals(course_progress, 100, "保存学习任务中的课件进度，实际返回结果：" + res);
	}
	
	@Test(description = "查看我的积分列表",priority = 17)
	public void testQueryMyIntegralList() {
		String res = IntegralBusiness.queryMyIntegralList("", "");
//		Assert.assertTrue(res.con);
	}
	@Test(description = "学完后对所学积分进行验证",priority= 18)
	public void queryMyIntegral() {
		String res = IntegralBusiness.queryMyIntegral();
//		String today_socre_01 = (String)JSONPath.read(res, "$.today");
//		int today_score_before = Integer.valueOf(today_socre);
//		int today_score_late = Integer.valueOf(today_socre_01);
//		Assert.assertTrue(today_score_late > today_score_before,"学完后对所学积分进行验证,之前的积分为："+today_score_before+",学完后获得的积分："+today_score_late);
	}
	@Test(description = "删除学习任务",priority = 19)
	public void deleteStudyTask() {
		String res = StudyTaskBusiness.deleteStudyTask(study_id);
		CourseBusiness.deleteCourse(course_id);
		String msg = (String) JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "删除学习计划成功", "删除考试任务，实际返回结果:" + res);
	}
}
