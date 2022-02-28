package timestudy.cases;

import cn.kxy.base.business.BaseBusiness;
import cn.kxy.course.resources.bussiness.ArticleBusiness;
import cn.kxy.course.resources.bussiness.CourseBusiness;
import cn.kxy.examination.business.PaperBusiness;
import cn.kxy.setting.bussiness.UserBusiness;
import cn.kxy.study.business.TimerStudyTaskTemplate;
import init.cases.InitStudyAuthCourse;
import com.alibaba.fastjson.JSONPath;
import com.lazy.common.utils.CommonData;
import com.lazy.common.utils.DateUtil;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestTimerStudyTask extends InitStudyAuthCourse{

	String timer_name = "TimerStudyTask"+CommonData.getStringRandom(5);
	String schedule_id = "";
	String user_name = UserBusiness.getUsername();
	String id = "";
	@Test(description="新增单次定时学习任务",priority=1)
	public void testAddSingleTimerStudy() {
		String res = TimerStudyTaskTemplate.addSingleTimerStudy(timer_name, DateUtil.getRegularDateForHHMM(0),  
				DateUtil.getRegularDateForHHMM(0), 
				CourseBusiness.getIdByPage(course_name), PaperBusiness.getIdByKeyword(BaseBusiness.pass_paper_name), BaseBusiness.pass_paper_name);
//		try {
//			Thread.sleep(60000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
		System.out.println("this is TimeStudyTask module");
		String template_id = (String)JSONPath.read(res, "$.template_id");
		Assert.assertNotNull(template_id, "新增单次定时学习任务，实际返回结果："+res);
	}
	@Test(description="查看定时学习模板全部列表",priority=2)
	public void testQueryAllList() {
		String res = TimerStudyTaskTemplate.queryList(timer_name, "all");
		id = (String)JSONPath.read(res,"$.list[0].id");
		String create_user = (String)JSONPath.read(res, "$.list[0].create_user");
		String title = (String)JSONPath.read(res, "$.list[0].title");
		String type = (String)JSONPath.read(res, "$.list[0].type");
		schedule_id = (String)JSONPath.read(res, "$.list[0].schedule_id");
		Long create_time = (Long)JSONPath.read(res, "$.list[0].create_time");
		int remain_times = (int)JSONPath.read(res, "$.list[0].remain_times");
		Assert.assertEquals(create_user, user_name,"查看定时学习模板全部列表--创建人校验,实际返回结果："+res);
		Assert.assertEquals(title, timer_name,"查看定时学习模板全部列表--标题校验,实际返回结果："+res);
		Assert.assertEquals(type, "once","查看定时学习模板全部列表--类型校验,实际返回结果："+res);
		Assert.assertNotNull(create_time,"查看定时学习模板全部列表--创建时间校验,实际返回结果："+res);
//		Assert.assertEquals(remain_times,0,"查看定时学习模板全部列表--剩余任务次数校验,实际返回结果："+res);
	}
	@Test(description="启用定时学习模板",priority=3)
	public void testOpenStatus() {
		String res = TimerStudyTaskTemplate.openStatus(id, schedule_id, DateUtil.getRegularDateForHHMM(2));
		String scheduler_id = (String)JSONPath.read(res, "$.scheduler_id");
		Assert.assertNotNull(scheduler_id, "启用定时学习模板，实际返回结果："+res);
	}
	@Test(description="启用定时学习模板后，在已启用的列表查看定时学习模板",priority=4)
	public void testQueryOpenList() {
//		String res = TimerStudyTaskTemplate.queryList(timer_name, "on");
//		String title = (String)JSONPath.read(res, "$.list[0].title");
//		String status = (String)JSONPath.read(res, "$.list[0].status");
//		Assert.assertEquals(status, "on","启用定时学习模板后 ，在已启用的列表查看定时学习模板，实际返回结果："+res);
//		Assert.assertEquals(title, timer_name,"启用定时学习模板后，在已启用的列表查看定时学习模板--标题校验,实际返回结果："+res);
	}
	
	@Test(description="停用定时学习模板",priority=5)
	public void testUpdateCloseStatus() {
		String res = TimerStudyTaskTemplate.updateCloseStatus(id);
		String status = (String)JSONPath.read(res, "$.status");
		Assert.assertEquals(status, "true","停用定时学习模板，实际返回结果："+res);
	}
	@Test(description="在已停用的列表查看定时学习模板",priority=6)
	public void testQueryOffList() {
		String res = TimerStudyTaskTemplate.queryList(timer_name, "off");
		String title = (String)JSONPath.read(res, "$.list[0].title");
		Assert.assertEquals(title, timer_name,"在已停用的列表查看定时学习模板--标题校验,实际返回结果："+res);
	}
	@Test(description="查看调度时间信息",priority=7)
	public void testQuerySchedulers() {
		String res = TimerStudyTaskTemplate.querySchedulers(schedule_id);
		String type = (String)JSONPath.read(res, "$.type");
		Assert.assertEquals(type, "period","查看调度时间信息--类型校验,实际返回结果："+res);
	}
	@Test(description="查看定时学习模板详情",priority=8)
	public void testQueryInfo() {
		String res = TimerStudyTaskTemplate.queryInfo(id);
		String title =(String)JSONPath.read(res, "$.title");
		Assert.assertEquals(title, timer_name,"查看定时学习模板详情,标题进行校验："+res);
	}
	
	String study_id = "";
	String my_study_id = "";
	String courseId="";
	String examId = "";
	@Test(description="定时任务生效后，查看学习任务列表",priority=9)
	public void testGetStudyTaskList() {
//		String res = StudyTaskBusiness.getStudyTaskList(timer_name, "true", "0", "","");
//		String title= (String)JSONPath.read(res, "$.list[0].title"); 
//		study_id = (String)JSONPath.read(res, "$.list[0].id");
//		Assert.assertTrue(title.contains(timer_name),"定时任务生效后，查看学习任务列表,任务名称"+res);
	}
	
	@Test(description="定时任务生效后，查看我的培训任务列表",priority=10)
	public void testQueryList() {
//		String res = MyStudyTask.queryList(timer_name,"0");
//		String name = (String)JSONPath.read(res, "$.list[0].title");
//		my_study_id = (String)JSONPath.read(res, "$.list[0].id");
//		Assert.assertTrue(name.contains(timer_name),"定时任务生效后，查询学习任务列表--任务名称"+res);
	}
	@Test(description="定时任务生效后，在学习任务延期定时学习任务",priority=11)
	public void testPlanStudyDelay () {
//		String res = StudyTaskBusiness.planStudyDelay(study_id, DateUtil.getRegularDateForHHMM(2));
//		String msg= (String)JSONPath.read(res, "$.msg");
//		Assert.assertEquals(msg, "延期成功","定时任务生效后，在学习任务延期定时学习任务，实际返回结果:"+res);
	}
	@Test(description="定时任务生效后，在学习任务变更定时学习任务",priority=12)
	public void testChangeStudyUsers() {
//		String res = StudyTaskBusiness.changeStudyUsers(study_id, "0");
//		String msg= (String)JSONPath.read(res, "$.msg");
//		Assert.assertEquals(msg, "变更成功","定时任务生效后，在学习任务变更定时学习任务，实际返回结果:"+res);
	}
	@Test(description="定时任务生效后，在学习任务催促定时学习任务",priority=13)
	public void testSendUrgeStudyMessage() {
//		String res = StudyTaskBusiness.sendUrgeStudyMessage(study_id);
//		String msg= (String)JSONPath.read(res, "$.msg");
//		Assert.assertEquals(msg, "已经成功通知1名学员","定时任务生效后，在学习任务催促定时学习任务，实际返回结果:"+res);
	}
	@Test(description="定时任务生效后，查看我的培训任务详情",priority=14)
	public void testQueryMyTimerStudyInfo() {
//		String res = MyStudyTask.queryInfo(my_study_id);
//		String name = (String)JSONPath.read(res, "$.data.title");
//		courseId = (String)JSONPath.read(res, "$.data.courseInfo[0].courseId");
//		examId = (String)JSONPath.read(res, "$.data.courseInfo[1].examId");
//		Assert.assertTrue(name.contains(timer_name),"定时任务生效后，查看我的培训任务详情，标题进行校验，实际返回结果："+res);
		
	}
	
	@Test(description="定时任务生效后，保存我的培训任务课件进度",priority=15)
	public void testSaveArtProgress() {
//		String res = AppStudyBusiness.saveProgress("100",my_study_id, courseId, courseId);
//		int course_progress = (int)JSONPath.read(res, "$.course_progress");
//		int total_progress = (int)JSONPath.read(res, "$.total_progress");
//		Assert.assertEquals(total_progress, 33,"定时任务生效后，保存我的培训任务课件进度--总进度，实际返回结果："+res);
//		Assert.assertEquals(course_progress, 100,"定时任务生效后，保存我的培训任务课件进度--课件进度，实际返回结果："+res);
	}
	@Test(description="定时任务生效后，查看我的培训任务课程详情",priority=16)
	public void testqueryCourseInfo() {
//		String res = MyElectiveTaskBusiness.queryCourseInfo(courseId);
//		String name = (String)JSONPath.read(res, "$.title");
//		Assert.assertNotNull(name,"定时任务生效后，查看我的培训任务课程详情，实际返回结果："+res);
	}
	@Test(description="定时任务生效后，保存我的培训任务课程进度",priority=17)
	public void testSaveCourseProgress() {
//		String res = AppStudyBusiness.saveProgress("100",my_study_id, resourceId,courseId);
//		int course_progress = (int)JSONPath.read(res, "$.course_progress");
//		Assert.assertEquals(course_progress, 100,"定时任务生效后，保存我的培训任务课程进度--课件进度，实际返回结果："+res);
	}
	@Test(description = "定时任务生效后，在我的培训任务查看学习任务考试详情", priority = 18)
	public void testQueryExamInfo() {
//		String res = AppStudyBusiness.queryExamInfo(examId);
//		String exam_title = (String) JSONPath.read(res, "$.title");
//		Assert.assertEquals(exam_title, "exam", "定时任务生效后，在我的培训任务查看学习任务考试详情，实际返回结果：" + res);
	}
	@Test(description = "定时任务生效后，在我的培训任务提交考试", priority = 19)
	public void testsubmitBlankExam() {
//		String res = AppStudyBusiness.submitBlankExam(examId);
//		String msg = (String) JSONPath.read(res, "$.msg");
//		Assert.assertEquals(msg, "提交试卷成功!", "定时任务生效后，在学习任务提交考试，实际返回结果：" + res);
	}
	
	@Test(description="定时任务生效后，删除考试任务",priority=20)
	public void testDeleteStudyTask() {
//		String res = StudyTaskBusiness.deleteStudyTask(study_id);
//		String msg= (String)JSONPath.read(res, "$.msg");
//		Assert.assertEquals(msg, "删除学习计划成功","定时任务生效后，删除考试任务，实际返回结果:"+res);
	}
	
	@Test(description="定时任务生效后，删除定时学习模板",priority=21)
	public void testDeleteTimerStudy() {
		String res = TimerStudyTaskTemplate.deleteTimerStudy(id);
		String status = (String)JSONPath.read(res, "$.status");
		Assert.assertEquals(status, "true","定时任务生效后，删除定时学习模板，实际返回结果："+res);
	}
	@Test(description="删除定时学习模板后，在列表页查看时，不应显示",priority=22)
	public void testQueryDeleteList() {
//		String res = TimerStudyTaskTemplate.queryList(timer_name, "all");
//		int total = (int)JSONPath.read(res, "$.total");
//		Assert.assertEquals(total,0,"删除定时学习模板后，在列表页查看时，不应显示数据--total校验,实际返回结果："+res);
	}
	
	String schedulers_name = "LoopTimerStudyTask"+CommonData.getStringRandom(3);
	String loop_id = "";
	String loop_schedule_id = "";
	String edit_name = "EditLoopTimerStudyTask"+CommonData.getStringRandom(3);
	@Test(description="新增循环的定时学习模板",priority=23)
	public void testAddschedulersTimerStudyTask() {
		String res = TimerStudyTaskTemplate.addschedulersTimerStudyTask(schedulers_name, DateUtil.getRegularDateForHHMM(1), 
				ArticleBusiness.getIdByKeyword(articlename),"false");
		String template_id = (String)JSONPath.read(res, "$.template_id");
		String list_res = TimerStudyTaskTemplate.queryList(schedulers_name, "all");
		loop_id = (String)JSONPath.read(list_res,"$.list[0].id");
		loop_schedule_id = (String)JSONPath.read(res, "$.list[0].schedule_id");
		Assert.assertNotNull(template_id, "新增单次定时学习任务，实际返回结果："+res);
	}
	@Test(description="编辑定时学习模板",priority=24)
	public void testEditTimerStudy() {
		String res = TimerStudyTaskTemplate.editTimerStudy(loop_id, edit_name, DateUtil.getRegularDateForHHMM(2), loop_schedule_id, ArticleBusiness.getIdByKeyword(articlename));
		String template_id = (String)JSONPath.read(res, "$.template_id");
		Assert.assertNotNull(template_id, "编辑定时学习模板，实际返回结果："+res);
	}
	@Test(description="编辑定时学习模板，在列表页查看时，不应显示",priority=25)
	public void testQueryEditList() {
		String res = TimerStudyTaskTemplate.queryList(edit_name, "all");
		String title = (String)JSONPath.read(res, "$.list[0].title");
		Assert.assertEquals(title, edit_name,"编辑定时学习模板--标题校验,实际返回结果："+res);
	}
	@Test(description="删除定时学习模板",priority=26)
	public void testEditLaterDeleteTimerStudy() {
		String res = TimerStudyTaskTemplate.deleteTimerStudy(loop_id);
		String status = (String)JSONPath.read(res, "$.status");
		Assert.assertEquals(status, "true","编辑定时学习模板，删除定时学习模板，实际返回结果："+res);
	}
	
	/*
	 * @Test public void test() { for (int i = 0; i < 105; i++) { String timer_res =
	 * TimerExamTaskBusiness.queryTimerExamTask("xam", "all");
	 * 
	 * String id = (String)JSONPath.read(timer_res, "$.list[0].id");
	 * TimerExamTaskBusiness.deleteTimerExamTask(id); }
	 * 
	 * for (int i = 0; i < 100; i++) { String id =
	 * ExaminationTaskBusiness.getIdByName("xam");
	 * ExaminationTaskBusiness.deleteExamTask(id); } }
	 */
}
