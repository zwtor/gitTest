package study.cases;

import cn.kxy.setting.bussiness.UserBusiness;
import com.lazy.common.utils.CommonData;
import org.testng.annotations.Test;

@Test(groups = {"studyProject"})
public class TestOfflineTypeOutLecturerStudyTask {

	String study_name = "Offline Study Task" + CommonData.getStringRandom(7);
	String id = "";
	String lagId = "";
	String user_name = UserBusiness.getUsername();
	String lec_name = "TUHBB"+CommonData.getStringRandom(5);
	@Test(description="新增外部讲师",priority=1)
	public void testAddOutsideLecturer() {
//		String res = LecturerListBusiness.addLecturer(lec_name,"1", "13526231231","outside" ,"", BaseBusiness.lecturerLevel, "", "jmeter");
//		String id02 =(String)JSONPath.read(res, "$.id");
//		Assert.assertTrue(!id02.isEmpty(), "新增外部讲师实际返回结果："+res);
	}
	String lec_id = "";
	@Test(description = "获取讲师id",priority = 2)
	public void getLecturerId() {
//		lec_id = LecturerListBusiness.getIdByKeyword(lec_name);
//		LecturerListBusiness.updateStatus("1", lec_id);
	}
	
	
	@Test(description="添加线下学习任务",priority=3)
	public void testAddOfflineTypeStudyTask() {
		
//		String date = DateUtil.getTimeStamp(1, "");
//		
//		String date2 = DateUtil.getTimeStamp(2, "");
//		String res = StudyTaskBusiness.addOfflineTypeStudyTask(study_name, DateUtil.getTimeStamp(0, ""), DateUtil.getTimeStamp(3, ""),
//				DateUtil.getDateTimeStamp(date), DateUtil.getDateTimeStamp(date2), 
//				lec_id, date,date2);
//		String msg= (String)JSONPath.read(res, "$.msg");
//		System.out.println();
//		Assert.assertEquals(msg, "新增计划成功！","添加线下学习任务，实际返回结果:"+res);
	}
	@Test(description="查看全部培训任务",priority=4)
	public void testQueryAllList() {
//		String res = MyStudyTask.queryList(study_name,"0");
//		int total = (int)JSONPath.read(res, "$.total");
//		int taskType = (int)JSONPath.read(res, "$.list[0].taskType");
//		Assert.assertEquals(taskType, 2,"查看我的培训任务列表--任务类型进行校验，实际返回结果："+res);
//		Assert.assertTrue(total>=0, "查看全部培训任务，实际返回结果："+res);
	}
	@Test(description="新增线下签到学习任务，在列表页查看任务类型",priority=5)
	public void testGetStudyTaskList() {
//		String res = StudyTaskBusiness.getStudyTaskList(study_name, "true", "2", "","");
//		id= (String)JSONPath.read(res, "$.list[0].id");
//		int taskType= (int)JSONPath.read(res, "$.list[0].taskType");
//		Assert.assertEquals(taskType, 2,"查询学习任务列表,任务类型（线下）"+res);
	}

	@Test(description="存在线下培训时，查看数据监控的线下培训列表",priority=6)
	public void testQueryLatojaMonitor() {
//		String res = StudyTaskBusiness.queryLatojaMonitor(id);
//		Long beginTime = (Long)JSONPath.read(res, "$.data.list[0].beginTime");
//		Long endTime = (Long)JSONPath.read(res, "$.data.list[0].endTime");
//		int shouldCount = (int)JSONPath.read(res, "$.data.list[0].shouldCount");
//		int absentCount = (int)JSONPath.read(res, "$.data.list[0].absentCount");
//		int actualCount = (int)JSONPath.read(res, "$.data.list[0].actualCount");
//		lagId = (String)JSONPath.read(res, "$.data.list[0].bizLatojaClockTimes[0].latojaId");
//		Assert.assertEquals(shouldCount, 1,"存在线下培训时，查看数据监控的线下培训列表---应到人数，实际返回结果："+res);
//		Assert.assertEquals(absentCount, 1,"存在线下培训时，查看数据监控的线下培训列表---缺席人数，实际返回结果："+res);
//		Assert.assertEquals(actualCount, 0,"存在线下培训时，查看数据监控的线下培训列表---实到人数，实际返回结果："+res);
//		Assert.assertTrue(endTime!=null, "存在线下培训时，查看数据监控的线下培训列--开始时间，实际返回结果："+res);
//		Assert.assertTrue(beginTime!=null, "存在线下培训时，查看数据监控的线下培训列表---结束时间，实际返回结果："+res);
	}
	@Test(description="查看线下签到详情",priority=7)
	public void testQueryLatojaMonitorDetailList() {
//		String res = StudyTaskBusiness.queryLatojaMonitorDetailList(id, lagId, UserBusiness.getUsername());
//		String departName = (String)JSONPath.read(res, "$.data.list.list[0].departName");
//		String processStr = (String)JSONPath.read(res, "$.data.list.list[0].processStr");
//		Long beginTime = (Long)JSONPath.read(res, "$.data.latojaVo.beginTime");
//		Assert.assertTrue(beginTime!=null, "查看线下签到详情--开始时间，实际返回结果："+res);
//		Assert.assertEquals(processStr, "0%","查看线下签到详情--培训进度，实际返回结果："+res);
//		Assert.assertTrue(departName!=null, "查看线下签到详情--所属部门，实际返回结果："+res);
	}
	@Test(description="无调查问卷到时，数据监控不应显示数据",priority=8)
	public void testQueryInvestigates() {
//		String res = StudyTaskBusiness.queryInvestigates(id);
//		int total = (int)JSONPath.read(res, "$.total");
//		Assert.assertEquals(total, 0,"无调查问卷到时，数据监控不应显示数据，实际返回结果："+res);
	}
	
	@Test(description="APP端查看线下学习列表",priority=9)
	public void testQueryAPPStudyList() {
//		String res = AppStudyBusiness.queryStudyList("unfinished");
//		int progress = (int)JSONPath.read(res, "$.list[0].progress");
//		Assert.assertTrue(progress>=0,"APP端查看线下学习列表,进度做校验，实际返回结果："+res);
	}
	@Test(description="APP端查看线下学习详情",priority=10)
	public void testQueryAppInfo() {
//		String res = AppStudyBusiness.queryInfo(id);
//		String type = (String)JSONPath.read(res, "$.type");
//		int study_progress = (int)JSONPath.read(res, "$.study_progress");
//		int progress = (int)JSONPath.read(res, "$.progress");
//		int exam_pass_count = (int)JSONPath.read(res, "$.exam_pass_count");
//		int exam_remainder_count = (int)JSONPath.read(res, "$.exam_remainder_count");
//		int study_progress_standard = (int)JSONPath.read(res, "$.study_progress_standard");
//		Assert.assertEquals(study_progress_standard, 100,"APP端查看线下学习详情，剩余考试场次做校验，实际返回结果："+res);
//		Assert.assertEquals(exam_remainder_count, 0,"APP端查看线下学习详情，剩余考试场次做校验，实际返回结果："+res);
//		Assert.assertEquals(exam_pass_count, 0,"APP端查看线下学习详情,考试及格场次做校验，实际返回结果："+res);
//		Assert.assertEquals(progress, 0,"APP端查看线下学习详情,总进度做校验，实际返回结果："+res);
//		Assert.assertEquals(study_progress, 0,"APP端查看线下学习详情,学习进度做校验，实际返回结果："+res);
//		Assert.assertEquals(type, "offline","APP端查看线下学习详情,类型做校验，实际返回结果："+res);
		
	}
	@Test(description="线下签到不存在时，查看线下签到详情",priority=11)
	public void testQueryLatojaMonitorUnExistDetai() {
//		String res = StudyTaskBusiness.queryLatojaMonitorDetailList(id, "12121", UserBusiness.getUsername());
//		String msg = (String)JSONPath.read(res, "$.msg");
//		Assert.assertEquals(msg, "查询线下培训不存在","线下签到不存在时，查看线下签到详情"+res);
	}
	
	@Test(description="删除线下学习任务",priority=13)
	public void testDeleteStudyTask() {
//		String res = StudyTaskBusiness.deleteStudyTask(id);
//		String msg= (String)JSONPath.read(res, "$.msg");
//		Assert.assertEquals(msg, "删除学习计划成功","删除考试任务，实际返回结果:"+res);
	}
	String no_start_name = "NotStartStudyTask"+CommonData.getStringRandom(3);
	String no_start_id = "";
	@Test(description="新增一个未开始的任务，在未开始列表查看",priority=14)
	public void testNotStartTask () {
//		System.out.println();
	}
	
	String over_name = "OverStudyTask"+CommonData.getStringRandom(15);
	String over_id = "";
	@Test(description="在已结束列表查看",priority=15)
	public void testOverStartTask () {
//		StudyTaskBusiness.addOfflineTypeStudyTask(over_name, DateUtil.getTimeStamp(-5, ""), DateUtil.getTimeStamp(-1, ""),
//				DateUtil.getRegularDateForHHMM(1), DateUtil.getRegularDateForHHMM(2), lec_id, DateUtil.getTimeStamp(1, ""), DateUtil.getTimeStamp(2, ""));
//		String res = StudyTaskBusiness.getStudyTaskList(over_name, "true", "3", "");
//		int unFinishUserCount = (int)JSONPath.read(res, "$.list[0].unFinishUserCount");
//		over_id= (String)JSONPath.read(res, "$.list[0].id");
//		String title= (String)JSONPath.read(res, "$.list[0].title");
//		String aa = StudyTaskBusiness.deleteStudyTask(over_id);
//		String msg= (String)JSONPath.read(aa, "$.msg");
//		Assert.assertEquals(msg, "删除学习计划成功","在已结束列表查看,删除考试任务，实际返回结果:"+aa);
//		Assert.assertEquals(title, over_name,"在已结束列表查看--标题，实际返回结果"+res);
//		Assert.assertEquals(unFinishUserCount, 1,"在已结束列表查看--逾期人数，实际返回结果："+res);
	}
	String offline_name= "offline_study_name"+CommonData.getStringRandom(8);
	String offline_id= "";
	String xx_Id = "";
	String latojasId = "";
	@Test(description="新增正常签到的线下学习任务",priority=16)
	public void testAddOfflineXXStudyTask() {
		
//		String date = DateUtil.getOneHourLaterStamp();
//		
//		String date2 =DateUtil.getTimeStamp(2, "");
//		
//		StudyTaskBusiness.addOfflineTypeStudyTask(offline_name, DateUtil.getTimeStamp(0, ""), DateUtil.getTimeStamp(2, ""),
//				DateUtil.getDateTimeStamp(date), DateUtil.getDateTimeStamp(date2), lec_id, date, date2);
//		String res = AppStudyBusiness.queryStudyList("unfinished");
//		offline_id=(String) JSONPath.read(res, "$.list[0].id");
//		AppStudyBusiness.loadResource(offline_id);
//		String info_res = AppStudyBusiness.queryInfo(offline_id);
//		xx_Id = (String) JSONPath.read(info_res, "$.stages[0].course_list[0].id");
//		String LatojasInfo_res = AppStudyBusiness.queryLatojasInfo(offline_id, xx_Id);
//		latojasId = (String) JSONPath.read(LatojasInfo_res, "$.sign_in_list[0].id");
	}
	@Test(description = "APP端在签到时间2小时之内签到", priority = 17)
	public void testOfflineSignIn() {
//		String res = AppStudyBusiness.offlineSignIn(latojasId);
//		String status = (String) JSONPath.read(res, "$.status");
//		Assert.assertEquals(status, "signed", "APP端在签到时间2小时之内签到" + res);
	}

	@Test(description = "APP端在签到时间2小时之内签到，查看学习任务线下签到详情", priority = 18)
	public void testQueryLateSignLatojasInfo() {
//		String res = AppStudyBusiness.queryLatojasInfo(offline_id, xx_Id);
//		String status = (String) JSONPath.read(res, "$.sign_in_list[0].status");
//		Assert.assertEquals(status, "signed", "APP端在签到时间2小时之内签到，查看学习任务线下签到详情--签到状态，实际返回结果：" + res);
	}


	@Test(description = "APP端在签到时间2小时之内签到，在学习任务详情查看线下签到的状态和进度", priority = 19)
	public void testQueryAppOfflineSignInInfo() {
//		String res = AppStudyBusiness.queryInfo(offline_id);
//		int xx_progress = (int) JSONPath.read(res, "$.stages[0].course_list[0].progress");
//		Assert.assertEquals(xx_progress, 100, "APP端在签到时间2小时之内签到，在学习任务详情查看线下签到的状态和进度，实际返回结果：" + res);
	}

	@Test(description = "APP端在签到时间2小时之内签到，Web端查看数据监控的线下培训列表", priority = 20)
	public void testQueryLatojaSuccessMonitor() {
//		String res = StudyTaskBusiness.queryLatojaMonitor(offline_id);
//		Long beginTime = (Long) JSONPath.read(res, "$.data.list[0].beginTime");
//		Long endTime = (Long) JSONPath.read(res, "$.data.list[0].endTime");
//		int shouldCount = (int) JSONPath.read(res, "$.data.list[0].shouldCount");
//		int absentCount = (int) JSONPath.read(res, "$.data.list[0].absentCount");
//		int actualCount = (int) JSONPath.read(res, "$.data.list[0].actualCount");
//		Assert.assertEquals(shouldCount, 1, "APP端在签到时间2小时之内签到，查看数据监控的线下培训列表---应到人数，实际返回结果：" + res);
//		Assert.assertEquals(absentCount, 0, "APP端在签到时间2小时之内签到，查看数据监控的线下培训列表---缺席人数，实际返回结果：" + res);
//		Assert.assertEquals(actualCount, 1, "APP端在签到时间2小时之内签到，查看数据监控的线下培训列表---实到人数，实际返回结果：" + res);
//		Assert.assertTrue(endTime != null, "APP端在签到时间2小时之内签到，查看数据监控的线下培训列--开始时间，实际返回结果：" + res);
//		Assert.assertTrue(beginTime != null, "APP端在签到时间2小时之内签到，查看数据监控的线下培训列表---结束时间，实际返回结果：" + res);
	}

	@Test(description = "APP端在签到时间2小时之内签到，查看线下签到详情", priority = 21)
	public void testQueryLatojaSuccessMonitorDetailList() {
//		String res = StudyTaskBusiness.queryLatojaMonitorDetailList(offline_id, xx_Id, UserBusiness.getUsername());
//		String processStr = (String) JSONPath.read(res, "$.data.list.list[0].processStr");
//		Long beginTime = (Long) JSONPath.read(res, "$.data.latojaVo.beginTime");
//		Long endTime = (Long) JSONPath.read(res, "$.data.latojaVo.endTime");
//		String clockTimeRecord = (String) JSONPath.read(res, "$.data.list.list[0].clockTimeRecord");
//		Assert.assertTrue(clockTimeRecord != null, "APP端在签到时间2小时之内签到，查看线下签到详情--签到时间，实际返回结果：" + res);
//		Assert.assertTrue(beginTime != null, "APP端在签到时间2小时之内签到，查看线下签到详情--开始时间，实际返回结果：" + res);
//		Assert.assertTrue(endTime != null, "APP端在签到时间2小时之内签到，查看线下签到详情--结束时间，实际返回结果：" + res);
//		Assert.assertEquals(processStr, "100%", "APP端在签到时间2小时之内签到，查看线下签到详情--培训进度，实际返回结果：" + res);
	}
	@Test(description = "删除任务",priority = 22)
	public void deleteStudyTask () {
//		StudyTaskBusiness.deleteStudyTask(offline_id);
	}
	@Test(description = "删除讲师",priority = 23)
	public void deleteLecturer() {
//		LecturerListBusiness.deleteLecturerByKeyword(lec_name);
	}
	
}
