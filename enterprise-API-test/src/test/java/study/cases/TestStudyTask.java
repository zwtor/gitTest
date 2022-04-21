package study.cases;

import cn.kxy.authentication.business.CertificateBusiness;
import cn.kxy.base.business.BaseBusiness;
import cn.kxy.course.resources.bussiness.ArticleBusiness;
import cn.kxy.course.resources.bussiness.CourseBusiness;
import cn.kxy.investigationresearch.business.QuestionnaireBusiness;
import cn.kxy.setting.bussiness.UserBusiness;
import cn.kxy.study.business.AppStudyBusiness;
import cn.kxy.study.business.MyStudyTask;
import cn.kxy.study.business.StudyTaskBusiness;
import init.cases.InitStudyAuthCourse;
import com.alibaba.fastjson.JSONPath;
import com.lazy.common.utils.CommonData;
import com.lazy.common.utils.DateUtil;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test(groups = {"studyProject"})
public class TestStudyTask extends InitStudyAuthCourse{

	String id = "";
	String study_name="Online Study Task" +CommonData.getStringRandom(3);
	public  String username = UserBusiness.getUsername();
	@Test(description="添加线上学习任务",priority=1)
	
	public void testAddOnlineTypeStudyTask() {
		String res = StudyTaskBusiness.addOnlineTypeStudyTask(study_name, DateUtil.getTimeStamp(0, ""), DateUtil.getTimeStamp(1, ""), 
				CertificateBusiness.getIdByKeyword(cert_name), ArticleBusiness.getIdByKeyword(articlename), 
				CourseBusiness.getIdByPage(course_name),QuestionnaireBusiness.getIdByKeyword(BaseBusiness.questionnaireName, "release"));
		String msg= (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "新增计划成功！","添加线上学习任务，实际返回结果:"+res);
	}
	
	@Test(description="添加同名的学习任务",priority=2)
	public void testAddSameNameOnlineTypeStudyTask() {
		String res = StudyTaskBusiness.addOnlineTypeStudyTask(study_name, DateUtil.getTimeStamp(0, ""), DateUtil.getTimeStamp(1, ""), 
				CertificateBusiness.getIdByKeyword(cert_name), ArticleBusiness.getIdByKeyword(articlename), 
				CourseBusiness.getIdByPage(course_name),QuestionnaireBusiness.getIdByKeyword(BaseBusiness.questionnaireName, "release"));
		String msg= (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "培训名称已经存在","添加同名的学习任务，实际返回结果:"+res);
	}
	
	@Test(description="查询学习任务列表",priority=3)
	public void testGetStudyTaskList() {
		String res = StudyTaskBusiness.getStudyTaskList(study_name, "true", "0", "","");
		id= (String)JSONPath.read(res, "$.list[0].id");
		int taskType= (int)JSONPath.read(res, "$.list[0].taskType");
		boolean containExam= (boolean)JSONPath.read(res, "$.list[0].containExam");
		
		String title= (String)JSONPath.read(res, "$.list[0].title");
		int finishUserCount= (int)JSONPath.read(res, "$.list[0].finishUserCount");
		String finishPercent= (String)JSONPath.read(res, "$.list[0].finishPercent");
		int onGoingUserCount= (int)JSONPath.read(res, "$.list[0].onGoingUserCount");
		int unFinishUserCount= (int)JSONPath.read(res, "$.list[0].unFinishUserCount");
		int pendingCount= (int)JSONPath.read(res, "$.list[0].pendingCount");
		String creatorName= (String)JSONPath.read(res, "$.list[0].creatorName");
		Long createTime= (Long)JSONPath.read(res, "$.list[0].createTime");
		Long beginTime= (Long)JSONPath.read(res, "$.list[0].beginTime");
		Long endTime= (Long)JSONPath.read(res, "$.list[0].endTime");
		
		Assert.assertEquals(containExam, false,"查询学习任务列表,是否显示（考）这个标签"+res);
		Assert.assertEquals(finishUserCount, 0,"查询学习任务列表,完成用户数"+res);
		Assert.assertEquals(finishPercent, "0","查询学习任务列表,完成率"+res);
		Assert.assertEquals(onGoingUserCount, 1,"查询学习任务列表,进行中数量"+res);
		Assert.assertEquals(unFinishUserCount, 0,"查询学习任务列表,未完成用户数）"+res);
		Assert.assertEquals(pendingCount, 0,"查询学习任务列表,待阅卷数量"+res);
		Assert.assertEquals(creatorName, username,"查询学习任务列表,创建人"+res);
		
		Assert.assertTrue(createTime!=null,"查询学习任务列表,创建时间内"+res);
		Assert.assertTrue(beginTime!=null,"查询学习任务列表,开始时间"+res);
		Assert.assertTrue(endTime!=null,"查询学习任务列表,结束时间"+res);
		Assert.assertEquals(title, study_name,"查询学习任务列表,任务名称"+res);
		Assert.assertEquals(taskType, 1,"查询学习任务列表,任务类型（线上）"+res);
	}
	@Test(description="延期学习任务",priority=4)
	public void testPlanStudyDelay () {
		String res = StudyTaskBusiness.planStudyDelay(id, DateUtil.getRegularDateForHHMM(2));
		String msg= (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "延期成功","延期学习任务，实际返回结果:"+res);
	}
	@Test(description="变更学习任务",priority=5)
	public void testChangeStudyUsers() {
		String res = StudyTaskBusiness.changeStudyUsers(id, "0");
		String msg= (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "变更成功","变更学习任务，实际返回结果:"+res);
	}
	@Test(description="催促学习",priority=6)
	public void testSendUrgeStudyMessage() {
		String res = StudyTaskBusiness.sendUrgeStudyMessage(id);
		String msg= (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "已经成功通知1名学员","催促学习任务，实际返回结果:"+res);
	}
	@Test(description="再次催促学习",priority=7)
	public void testAgainSendUrgeStudyMessage() {
		String res = StudyTaskBusiness.sendUrgeStudyMessage(id);
		String msg= (String)JSONPath.read(res, "$.msg");
		Assert.assertTrue(msg.contains("60分钟之内只允许发送一次提醒信息"),"再次催促学习，实际返回结果:"+res);
	}
	@Test(description="查看学习任务进行中的人员个数",priority=8)
	public void testQueryTrainMonitor() {
		String res = StudyTaskBusiness.queryTrainMonitor(username, id);
		int studyProgress = (int)JSONPath.read(res, "$.data.voList.list[0].studyProgress"); 
		String user_name = (String)JSONPath.read(res, "$.data.voList.list[0].userName"); 
		String trainner = (String)JSONPath.read(res, "$.data.voList.list[0].trainner"); 
		Assert.assertTrue(trainner!=null, "查看学习任务进行中的人员个数,所属部门"+res);
		Assert.assertEquals(user_name, username,"查看学习任务进行中的人员个数,姓名"+res);
		Assert.assertEquals(studyProgress, 0,"查看学习任务进行中的人员个数,培训进度"+res);
	}
	@Test(description="未进行学习时查看全部人员监控",priority=9)
	public void testQueryUndoUserMonitors() {
		String res = StudyTaskBusiness.queryUserMonitors(id, "true", "0", username, "0");
		String user_name = (String) JSONPath.read(res, "$.data.voList.list[0].userName");
		String trainner = (String) JSONPath.read(res, "$.data.voList.list[0].trainner");
		String postName = (String) JSONPath.read(res, "$.data.voList.list[0].postNameList[0]");
		String qualifiedStatusName = (String) JSONPath.read(res, "$.data.voList.list[0].qualifiedStatusName");
		String taskStatusName = (String) JSONPath.read(res, "$.data.voList.list[0].taskStatusName");
		int studyProgress = (int) JSONPath.read(res, "$.data.voList.list[0].studyProgress");
		int examPassCount = (int) JSONPath.read(res, "$.data.voList.list[0].examPassCount");
		
		Assert.assertEquals(taskStatusName, "进行中","未进行学习时查看全部人员监控,姓名"+res);
		Assert.assertEquals(studyProgress,0,"未进行学习时查看全部人员监控,姓名"+res);
		Assert.assertEquals(qualifiedStatusName, "不合格","未进行学习时查看全部人员监控,姓名"+res);
		Assert.assertEquals(examPassCount, 0,"未进行学习时查看全部人员监控,姓名"+res);
		Assert.assertEquals(user_name, username,"未进行学习时查看全部人员监控,姓名"+res);
		Assert.assertTrue(trainner!=null, "未进行学习时查看全部人员监控,所属部门"+res);
	}
	@Test(description="未进行学习时查看进行中人员监控",priority=10)
	public void testQuertGoingUserMonitors() {
		String res = StudyTaskBusiness.queryUserMonitors(id, "true", "0", username, "1");
		String user_name = (String) JSONPath.read(res, "$.data.voList.list[0].userName");
		String trainner = (String) JSONPath.read(res, "$.data.voList.list[0].trainner");
		String postName = (String) JSONPath.read(res, "$.data.voList.list[0].postNameList[0]");
		String qualifiedStatusName = (String) JSONPath.read(res, "$.data.voList.list[0].qualifiedStatusName");
		String taskStatusName = (String) JSONPath.read(res, "$.data.voList.list[0].taskStatusName");
		int studyProgress = (int) JSONPath.read(res, "$.data.voList.list[0].studyProgress");
		int examPassCount = (int) JSONPath.read(res, "$.data.voList.list[0].examPassCount");
		
		Assert.assertEquals(taskStatusName, "进行中","未进行学习时查看进行中监控,姓名"+res);
		Assert.assertEquals(studyProgress,0,"未进行学习时查看进行中监控,姓名"+res);
		Assert.assertEquals(qualifiedStatusName, "不合格","未进行学习时查看进行中监控,姓名"+res);
		Assert.assertEquals(examPassCount, 0,"未进行学习时查看进行中监控,姓名"+res);
		Assert.assertEquals(user_name, username,"未进行学习时查看进行中监控,姓名"+res);
		Assert.assertTrue(trainner!=null, "未进行学习时查看进行中监控,所属部门"+res);
	}
	@Test(description="未进行学习时查看已完成人员监控",priority=11)
	public void testQueryFinishUserMonitors() {
		String res = StudyTaskBusiness.queryUserMonitors(id, "true", "0", username, "2");
		int total = (int)JSONPath.read(res, "$.data.voList.total");
		Assert.assertEquals(total, 0,"未进行学习时查看已完成人员监控，个数为0，实际返回结果："+res);
	}
	@Test(description="未进行学习时查看逾期人员监控",priority=12)
	public void testQueryDelayUserMonitors() {
		String res = StudyTaskBusiness.queryUserMonitors(id, "true", "0", username, "3");
		int total = (int)JSONPath.read(res, "$.data.voList.total");
		Assert.assertEquals(total, 0,"未进行学习时查看逾期人员监控，个数为0，实际返回结果："+res);
	}
	@Test (description="无线下学习任务时，查看数据中的人员监控",priority=13)
	public void testQueryLatojaMonitor() {
		String res = StudyTaskBusiness.queryLatojaMonitor(id);
		int total = (int)JSONPath.read(res, "$.data.total");
		Assert.assertEquals(total, 0,"无线下学习任务时，查看数据中的人员监控，个数为0"+res);
	}
	
	@Test (description="未进行学习时查看问卷调研的统计数据",priority=14)
	public void testQueryLatojaMoitor() {
		String res = StudyTaskBusiness.queryInvestigates(id);
		String title_a= (String)JSONPath.read(res, "$.list[0].title");
		String create_user= (String)JSONPath.read(res, "$.list[0].create_user");
		int investigate_count = (int)JSONPath.read(res, "$.list[0].investigate_count");
		Long update_time= (Long)JSONPath.read(res, "$.list[0].update_time");
		Assert.assertTrue(update_time!=null, "未进行学习时查看问卷调研的统计数据，更新时间实际返回结果"+res);
		Assert.assertTrue(create_user!=null,"未进行学习时查看问卷调研的统计数据，创建人实际返回结果"+res);
		Assert.assertEquals(investigate_count,0,"未进行学习时查看问卷调研的统计数据，调研份数实际返回结果"+res);
		Assert.assertEquals(title_a,BaseBusiness.questionnaireName,"未进行学习时查看问卷调研的统计数据，调研的名称实际返回结果"+res);
	}
	@Test(description="未进行学习时查看考试分析的统计数据",priority=15)
	public void testQueryExamsAnalysis() {
		String res = StudyTaskBusiness.queryExamsAnalysis(id);
		String total = (String)JSONPath.read(res, "$.total");
		Assert.assertEquals(total, "0","未进行学习时查看考试分析的统计数据，个数为0"+res);
	}
	@Test(description="APP端查看线下学习详情",priority=16)
	public void testQueryAppInfo() {
		String res = AppStudyBusiness.queryInfo(id);
		String type = (String)JSONPath.read(res, "$.type");
		int study_progress = (int)JSONPath.read(res, "$.study_progress");
		int progress = (int)JSONPath.read(res, "$.progress");
		int exam_pass_count = (int)JSONPath.read(res, "$.exam_pass_count");
		int exam_remainder_count = (int)JSONPath.read(res, "$.exam_remainder_count");
		int study_progress_standard = (int)JSONPath.read(res, "$.study_progress_standard");
		Assert.assertEquals(study_progress_standard, 100,"APP端查看线下学习详情，剩余考试场次做校验，实际返回结果："+res);
		Assert.assertEquals(exam_remainder_count, 0,"APP端查看线下学习详情，剩余考试场次做校验，实际返回结果："+res);
		Assert.assertEquals(exam_pass_count, 0,"APP端查看线下学习详情,考试及格场次做校验，实际返回结果："+res);
		Assert.assertEquals(progress, 0,"APP端查看线下学习详情,总进度做校验，实际返回结果："+res);
		Assert.assertEquals(study_progress, 0,"APP端查看线下学习详情,学习进度做校验，实际返回结果："+res);
		Assert.assertEquals(type, "online","APP端查看线下学习详情,类型做校验，实际返回结果："+res);
	}
	@Test(description="查看全部培训任务",priority=17)
	public void testQueryAllList() {
		String res = MyStudyTask.queryList(study_name,"0");
		int total = (int)JSONPath.read(res, "$.total");
		int taskType = (int)JSONPath.read(res, "$.list[0].taskType");
		Assert.assertEquals(taskType, 1,"查看我的培训任务列表--任务类型进行校验，实际返回结果："+res);
		Assert.assertTrue(total>=0, "查看全部培训任务，实际返回结果："+res);
	}
	@Test(description="删除学习计划任务",priority=18)
	public void testDeleteStudyTask() {
		String res = StudyTaskBusiness.deleteStudyTask(id);
		String msg= (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "删除学习计划成功","删除考试任务，实际返回结果:"+res);
	}
	
	@Test(description="查看学习任务列表的任务个数",priority=19)
	public void testGetIntervalStatistical() {
		String res = StudyTaskBusiness.getIntervalStatistical("");
		int totalCount = (int)JSONPath.read(res, "$.totalCount");
		Assert.assertTrue(totalCount>=0,"查看学习任务列表的任务个数"+res);
	}
	
	@Test(description="查看学习任务列表的任务个数--只看自己创建",priority=20)
	public void testGetOnlySeeMeIntervalStatistical() {
		String res = StudyTaskBusiness.getIntervalStatistical("true");
		int totalCount = (int)JSONPath.read(res, "$.totalCount");
		Assert.assertTrue(totalCount>=0,"查看学习任务列表的任务个数"+res);
	}
	@Test(description = "查看群组",priority = 21)
	public void testQueryGroupList() {
//		String res = StudyTaskBusiness.queryGroupList("");
//		Assert.assertTrue(res.contains("success"));
	}
	@Test(description = "通过用户名查看群组",priority = 22)
	public void testQueryGroupListKeyword() {
//		String res = StudyTaskBusiness.queryGroupList("aaa");
//		Assert.assertTrue(res.contains("success"));
	}
}
