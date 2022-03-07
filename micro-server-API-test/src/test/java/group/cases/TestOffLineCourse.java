package group.cases;

/**
 * @author wenlingzhi
 *2021年8月18日
 */

import org.testng.annotations.Test;

public class TestOffLineCourse {	
//	public static String lecturer_id = "";
//	public static String lever_id = "";
//	
//	String name = "讲师等级测试" + CommonData.getStringRandom(5);
//	String studyplan_title = "线下课的学习任务" + CommonData.getStringRandom(5);
//	String project_title = "线下课的学习项目"+ CommonData.getStringRandom(5);
// 	String classification_id = ClassificationBusines.getPrimaryId();
// 	String username = UserBusiness.getUsername();
 	
 	
 	@Test(description="1.创建内部讲师等级接口", priority=1)
 	public void testLecturerLeverAdd()  {
// 		String res = OffLineCourseBusiness.LecturerLeverAdd(name,"test description","inside","1");
// 		System.out.println("1.创建内部讲师等级接口:");
// 		lever_id = (String) JSONPath.read(res, "$.id");
// 		System.out.println("lever_id="+lever_id);
// 		Assert.assertNotEquals(lever_id,null,"1.创建内部讲师等级接口：" + res);
 	}

 	@Test(description="2.新建内部讲师接口", priority=2)
 	public void testAddLecturers()  {
// 		System.out.println("2.新建内部讲师接口:");
// 		String avatar = "https://static-legacy.dingtalk.com/media/lADPGoGu-6DKjdnNAcvNAcs_459_459.jpg";
// 		long current_time = System.currentTimeMillis();
// 		String start_timeTemp = String.valueOf(current_time);
//		String res_username = OffLineCourseBusiness.GetLecturter(username);
//	 	lecturer_id = (String) JSONPath.read(res_username, "$.list[0].id");
//	 	if(lecturer_id == null) {
//	 		String res = LecturerBusiness.AddLecturers(username,avatar,"inside",lever_id,start_timeTemp);
//	 		lecturer_id = (String) JSONPath.read(res, "$.id");
//	 	}
//	 	else{
//	 		//lecturer_name = username;
//	 		System.out.println("username="+username+","+"lecturer_id="+lecturer_id);
//	 	}	
//	 	System.out.println("username="+username+","+"lecturer_id="+lecturer_id);
// 		Assert.assertNotEquals(lecturer_id,null,"2.新建内部讲师接口：" + res_username);
 	}
 	
 	
 	@Test(description="3.启用讲师接口", priority=3)
 	public void testUpdateStatus()  {
// 		System.out.println("3.启用讲师接口:");
// 		String res = OffLineCourseBusiness.UpdateStatus(lecturer_id,"1");
// 		String update = (String) JSONPath.read(res, "$.update");
// 		System.out.println("update="+update);
// 		Assert.assertEquals(update,"true","3.启用讲师接口：" + res);
 	}
 	
 	
 	public static String studyplan_id = "";
 	@Test(description="4.创建线下课的学习任务接口", priority=4)
 	public void testStudyPlanAdd()  {
// 		System.out.println("4.创建线下课的学习任务接口:");
// 		String cover = "https://oss.coolcollege.cn/1807912811305766912.png";
// 		String course_duration = "4319";
// 		long current_time = System.currentTimeMillis();
// 		long endTemp = current_time + 24L*7*3600*1000; 
// 		long startTemp = current_time + 24L*2*3600*1000; 
// 		//long endtemp = current_time + 24L*4*3600*1000; 
// 		String start_timeTemp = String.valueOf(current_time);
// 		String end_timeTemp = String.valueOf(endTemp);
// 		String start = String.valueOf(startTemp);
// 		//String end = String.valueOf(endtemp);
// 		String res = OffLineCourseBusiness.StudyPlanAdd(studyplan_title,start_timeTemp,end_timeTemp,cover,start_timeTemp,start,
// 				course_duration,lecturer_id,"star","","");
// 		studyplan_id = (String)JSONPath.read(res, "$.data");
// 		String msg = (String)JSONPath.read(res, "$.msg");
// 		System.out.println("msg="+msg+","+"studyplan_id="+studyplan_id);
// 		Assert.assertEquals(msg,"新增计划成功！","4.创建线下课的学习任务接口"+res);
 	}
 	
 	
 	public static String project_id = "";
 	public static String project_course_id = "";
 	@Test(description="4.创建线下课的学习项目接口", priority=4)
 	public void testStudyProjectSave()  {
// 		String cover = "https://oss.coolcollege.cn/1810048818682974377.png";
// 	    String base_cover= "https://oss.coolcollege.cn/1810048818682974377.png";
// 		String course_duration = "4319";
// 		long current_time = System.currentTimeMillis();
// 		long endTemp = current_time + 24L*7*3600*1000; 
// 		long startTemp = current_time + 24L*2*3600*1000; 
// 		String start_timeTemp = String.valueOf(current_time);
// 		String end_timeTemp = String.valueOf(endTemp);
// 		String start = String.valueOf(startTemp);
// 		String resFirst = OffLineCourseBusiness.StudyProjectSaveBaseInfo(project_title,lecturer_id,classification_id,"normal",cover,
// 				base_cover);
// 		project_id = (String) JSONPath.read(resFirst, "$.id"); 
// 		project_course_id = (String) JSONPath.read(resFirst, "$.course_id");
// 		String resSecond = OffLineCourseBusiness.StudyProjectSaveStageContent(project_id,project_title,cover,start_timeTemp,end_timeTemp,
// 				course_duration,lecturer_id);	
// 		String resThird = OffLineCourseBusiness.StudyProjectSaveSettings(project_id);
// 		String result = (String) JSONPath.read(resThird, "$.result");		
// 		System.out.println("4.创建线下课的学习项目接口:"+"project_id="+project_id);
// 		Assert.assertEquals(result, "true","4.创建线下课的学习项目接口" + resThird);
 	}
 	
 	
 	
 	public static String offlinecourse_id = "";
 	@Test(description="5.获取学习任务-线下课id接口", priority=5)
 	public void testGetOne()  {
// 		System.out.println("5.获取学习任务-线下课id接口:");
// 		String res = OffLineCourseBusiness.GetOne(studyplan_id);
// 		offlinecourse_id = (String) JSONPath.read(res, "$.data.courseInfo[0].courseId");
// 		System.out.println("offlinecourse_id="+offlinecourse_id);
// 		Assert.assertNotEquals(offlinecourse_id,null,"5.获取学习任务-线下课id接口：" + res);
 	}
 	
 	
 	public static String project_offlinecourse_id = "";
 	@Test(description="6.获取学习项目-线下课id接口", priority=6)
 	public void testProjectOffLineCourse()  {
// 		System.out.println("6.获取学习项目-线下课id接口:");
// 		String res = OffLineCourseBusiness.ProjectProcess(project_course_id);
// 		project_offlinecourse_id = (String) JSONPath.read(res, "$.stages[0].resources[0].course_id");
// 		System.out.println("project_offlinecourse_id="+project_offlinecourse_id);
// 		Assert.assertNotEquals(project_offlinecourse_id,null,"6.获取学习项目-线下课id接口：" + res);
 	}
 	
 	
 		
 	public static String studyplan_sign_id = "";
 	public static String project_sign_id = "";
 	@Test(description="7.获取学习任务/学习项目-线下课二维码id接口", priority=7)
 	public void testSignAnalysis()  {
// 		System.out.println("6.获取学习任务/学习项目-线下课二维码id接口:");
// 		String res_studyplan = OffLineCourseBusiness.SignAnalysis(offlinecourse_id);
// 		String res_project = OffLineCourseBusiness.SignAnalysis(project_offlinecourse_id);
// 		studyplan_sign_id = (String) JSONPath.read(res_studyplan, "$.data[0].id");
// 		project_sign_id = (String) JSONPath.read(res_project, "$.data[0].id");
// 		Boolean success = (Boolean) JSONPath.read(res_studyplan, "$.success");
// 		System.out.println("studyplan_sign_id="+studyplan_sign_id+","+"project_sign_id="+project_sign_id);
// 		Assert.assertTrue(success,"7.获取学习任务/学习项目-线下课二维码id接口：" + res_studyplan);
 	}
 	
 	
 	@Test(description="8.学习任务/学习项目线下课考勤-签到接口", priority=8)
 	public void testSign()  {
// 		System.out.println("8.学习任务/学习项目线下课考勤-签到接口:");
// 		String res_studyplan = OffLineCourseBusiness.Sign(studyplan_sign_id,"sign_in");
// 		String res_project = OffLineCourseBusiness.Sign(project_sign_id,"sign_in");
// 		Boolean success_studyplan = (Boolean) JSONPath.read(res_studyplan, "$.success");
// 		Boolean success_project = (Boolean) JSONPath.read(res_project, "$.success");
// 		System.out.println("success_studyplan="+success_studyplan+","+"success_project="+success_project);
// 		Assert.assertTrue(success_studyplan,"8.学习任务/学习项目线下课考勤-签到接口：" + res_studyplan);
 	}
 	
 	
 	@Test(description="9.学习任务/学习项目线下课考勤-签退接口", priority=9)
 	public void testSignOut()  {
// 		System.out.println("9.学习任务/学习项目线下课考勤-签退接口:");
// 		String res_studyplan = OffLineCourseBusiness.Sign(studyplan_sign_id,"sign_out");
// 		String res_project = OffLineCourseBusiness.Sign(project_sign_id,"sign_out");
// 		Boolean success_studyplan = (Boolean) JSONPath.read(res_studyplan, "$.success");
// 		Boolean success_project = (Boolean) JSONPath.read(res_project, "$.success");
// 		System.out.println("success_studyplan="+success_studyplan+","+"success_project="+success_project);
// 		Assert.assertTrue(success_studyplan,"9.学习任务/学习项目线下课考勤-签退接口：" + res_studyplan);
 	}
 	
 	
 	@Test(description="10.校验学习任务学习进度情况接口", priority=10)
 	public void testGetOneAgain()  {
// 		System.out.println("10.校验学习任务学习进度情况接口:");
// 		String res = OffLineCourseBusiness.GetOne(studyplan_id);
// 		Integer progress = (Integer) JSONPath.read(res, "$.data.actualStudyProgress");
// 		System.out.println("progress="+progress);
// 		Assert.assertSame(progress,100,"10.校验学习任务学习进度情况接口：" + res);
 	}
 	
 	
 	public static String project_offline_course_setting_list_id = "";
 	public static String project_offline_course_sign_config_list_id = "";
 	@Test(description="11.校验学习项目学习进度情况接口", priority=11)
 	public void testProjectProcess()  {
// 		System.out.println("11.校验学习项目学习进度情况接口:");
// 		String res = OffLineCourseBusiness.ProjectProcess(project_course_id);
// 		project_offline_course_setting_list_id = (String) JSONPath.read(res, "$.stages[0].resources[0].offline_course_setting_list[0].id");
// 		project_offline_course_sign_config_list_id =(String) JSONPath.read(res, "$.stages[0].resources[0].offline_course_sign_config_list[0].id");
// 		Integer progress = (Integer) JSONPath.read(res, "$.process");
// 		System.out.println("progress="+progress);
// 		Assert.assertSame(progress,100,"11.校验学习项目学习进度情况接口：" + res);
 	}
 	
 	
// 	public static String stageId = "";
// 	public static String courseId = "";
// 	public static String mappingId = "";
// 	public static String offline_course_setting_list_id = "";
// 	public static String offline_course_sign_config_list_id = "";
 	@Test(description="12.获取学习任务详情接口", priority=12)
 	public void testGetEmployeeTrainTask()  {
// 		System.out.println("12.获取学习任务详情接口:");
// 		String res = OffLineCourseBusiness.GetEmployeeTrainTask(studyplan_id);
// 		stageId = (String) JSONPath.read(res, "$.stageList[0].courseMappingList[0].stageId");
// 		courseId = (String) JSONPath.read(res, "$.stageList[0].courseMappingList[0].courseId");
// 		mappingId = (String) JSONPath.read(res, "$.stageList[0].courseMappingList[0].mappingId");
// 		offline_course_setting_list_id = 
// 				(String) JSONPath.read(res, "$.stageList[0].courseMappingList[0].offline_course_vo.offline_course_setting_list[0].id");
// 		offline_course_sign_config_list_id = 
// 				(String) JSONPath.read(res, "$.stageList[0].courseMappingList[0].offline_course_vo.offline_course_sign_config_list[0].id");
// 		System.out.println("stageId="+stageId+","+"courseId="+courseId);
// 		Assert.assertNotEquals(stageId,null,"12.获取学习任务详情接口：" + res);
 	}
 	
 	
 	
 	public static String evaluate_id = "";
 	public static String project_evaluate_id = "";
 	@Test(description="13.我的评价-给学员评列表接口", priority=13)
 	public void testEvaluateList()  {
// 		System.out.println("13.我的评价-给学员评列表接口:");
// 		String res1 = OffLineCourseBusiness.EvaluateList("trainee","","unfinished","myEvaluate");//未开始
// 		String res2 = OffLineCourseBusiness.EvaluateList("trainee","","finished","myEvaluate");//已开始
// 		String res3 = OffLineCourseBusiness.EvaluateList("trainee","","settled","myEvaluate");//已结算
// 		String res4 = OffLineCourseBusiness.EvaluateList("trainee",studyplan_title,"unfinished","myEvaluate");//按任务名查询
// 		Boolean success = (Boolean) JSONPath.read(res1, "$.success");
// 		evaluate_id = (String) JSONPath.read(res1, "$.data.list[1].evaluate_id");
// 		project_evaluate_id = (String) JSONPath.read(res1, "$.data.list[0].evaluate_id");
// 		System.out.println("success="+success+","+"evaluate_id"+evaluate_id+","+"project_evaluate_id="+project_evaluate_id);
// 		Assert.assertTrue(success,"13.我的评价-给学员评列表接口：" + res1);
 	}
 	
 	
 	
 	@Test(description="14.给学员评价-评价前弹窗数据校验接口", priority=14)
 	public void testEvaluateing()  {
// 		System.out.println("14.给学员评价-评价前弹窗数据校验接口:");
// 		String res1 = OffLineCourseBusiness.EvaluateUserList(offlinecourse_id,"evaluateing","false",evaluate_id);
// 		String res2 = OffLineCourseBusiness.EvaluateUserList(project_offlinecourse_id,"evaluateing","false",project_evaluate_id);
// 		Boolean success1 = (Boolean) JSONPath.read(res1, "$.success");
// 		Boolean success2 = (Boolean) JSONPath.read(res2, "$.success");
// 		System.out.println("success1="+success1+","+"success2="+success2);
// 		Assert.assertTrue(success1,"14.给学员评价-评价前弹窗数据校验接口：" + res1);
 	}
 	
 	
 	@Test(description="15.给学员评价接口", priority=15)
 	public void testEvaluateStudent()  {
// 		System.out.println("15.给学员评价接口:");
// 		String res1 = OffLineCourseBusiness.EvaluateStudent(offlinecourse_id,evaluate_id);
// 		String res2 = OffLineCourseBusiness.EvaluateStudent(project_offlinecourse_id,project_evaluate_id);
// 		Boolean success1 = (Boolean) JSONPath.read(res1, "$.success");
// 		Boolean success2 = (Boolean) JSONPath.read(res2, "$.success");
// 		System.out.println("success1="+success1+","+"success2="+success2);
// 		Assert.assertTrue(success1,"15.给学员评价接口：" + res1);
 	}
 	
 	
 	@Test(description="16.给学员评价-评价后弹窗数据校验接口", priority=16)
 	public void testEvaluated()  {
// 		System.out.println("16.给学员评价-评价后弹窗数据校验接口:");
// 		String res1 = OffLineCourseBusiness.EvaluateUserList(offlinecourse_id,"evaluated","true",evaluate_id);
// 		String res2 = OffLineCourseBusiness.EvaluateUserList(project_offlinecourse_id,"evaluated","true",project_evaluate_id);
// 		Boolean success1 = (Boolean) JSONPath.read(res1, "$.success");
// 		Boolean success2 = (Boolean) JSONPath.read(res2, "$.success");
// 		System.out.println("success1="+success1+","+"success2="+success2);
// 		Assert.assertTrue(success1,"16.给学员评价-评价后弹窗数据校验接口：" + res1);
 	}
 	
 	
 	
 	@Test(description="17.我的评价-给讲师评列表接口", priority=17)
 	public void testEvaluateListLecurter()  {
// 		System.out.println("17.我的评价-给讲师评列表接口:");
// 		String res1 = OffLineCourseBusiness.EvaluateList("lecturer","","unfinished","myEvaluate");//未开始
// 		String res2 = OffLineCourseBusiness.EvaluateList("lecturer","","finished","myEvaluate");//已开始
// 		String res3 = OffLineCourseBusiness.EvaluateList("lecturer","","settled","myEvaluate");//已结算
// 		Boolean success = (Boolean) JSONPath.read(res1, "$.success");
// 		System.out.println("success="+success);
// 		Assert.assertTrue(success,"17.我的评价-给讲师评列表接口：" + res1);
 	}
 	
 	
 	
 	@Test(description="18.给讲师评价接口", priority=18)
 	public void testEvaluateLecurter()  {
// 		System.out.println("18.给讲师评价接口:");
// 		String res1 = OffLineCourseBusiness.EvaluateLecurter(offlinecourse_id,evaluate_id);
// 		String res2 = OffLineCourseBusiness.EvaluateLecurter(project_offlinecourse_id,project_evaluate_id);
// 		Boolean success1 = (Boolean) JSONPath.read(res1, "$.success");
// 		Boolean success2 = (Boolean) JSONPath.read(res2, "$.success");
// 		System.out.println("success1="+success1+","+"success2="+success2);
// 		Assert.assertTrue(success1,"18.给讲师评价接口：" + res1);
 	}
 	
 	
 	@Test(description="19.查看给讲师评价详情接口", priority=19)
 	public void testLecurterEvaluateDetail()  {
// 		System.out.println("19.查看给讲师评价详情接口:");
// 		String res1 = OffLineCourseBusiness.EvaluateDetail(offlinecourse_id,evaluate_id,"true",offlinecourse_id);
// 		String res2 = OffLineCourseBusiness.EvaluateDetail(project_offlinecourse_id,project_evaluate_id,"true",project_offlinecourse_id);
// 		Boolean success1 = (Boolean) JSONPath.read(res1, "$.success");
// 		Boolean success2 = (Boolean) JSONPath.read(res2, "$.success");
// 		System.out.println("success1="+success1+","+"success2="+success2);
// 		Assert.assertTrue(success1,"19.查看给讲师评价详情接口：" + res1);
 	}
 	
 	
 	
 	@Test(description="20.给课程评价接口", priority=20)
 	public void testEvaluateCourse()  {
// 		System.out.println("20.给课程评价接口:");
// 		String res1 = OffLineCourseBusiness.EvaluateLecurter(offlinecourse_id,evaluate_id);
// 		String res2 = OffLineCourseBusiness.EvaluateLecurter(project_offlinecourse_id,project_evaluate_id);
// 		Boolean success1 = (Boolean) JSONPath.read(res1, "$.success");
// 		Boolean success2 = (Boolean) JSONPath.read(res2, "$.success");
// 		System.out.println("success1="+success1+","+"success2="+success2);
// 		Assert.assertTrue(success1,"20.给课程评价接口：" + res1);
 	}
 	
 	
 	@Test(description="21.查看给课程评价详情接口", priority=21)
 	public void testCourseEvaluateDetail()  {
// 		System.out.println("21.查看给课程评价详情接口:");
// 		String res1 = OffLineCourseBusiness.EvaluateDetail(offlinecourse_id,evaluate_id,"true",offlinecourse_id);
// 		String res2 = OffLineCourseBusiness.EvaluateDetail(project_offlinecourse_id,project_evaluate_id,"true",project_offlinecourse_id);
// 		Boolean success1 = (Boolean) JSONPath.read(res1, "$.success");
// 		Boolean success2 = (Boolean) JSONPath.read(res2, "$.success");
// 		System.out.println("success1="+success1+","+"success2="+success2);
// 		Assert.assertTrue(success1,"21.查看给课程评价详情接口：" + res1);
 	}
 	
 	
 	@Test(description="22.评价我的-学员对我的评价接口", priority=22)
 	public void testEvaluateListMy()  {
// 		System.out.println("22.评价我的-学员对我的评价接口:");
// 		String res1 = OffLineCourseBusiness.EvaluateList("trainee","","unfinished","evaluateMy");//未开始
// 		String res2 = OffLineCourseBusiness.EvaluateList("trainee","","finished","evaluateMy");//已开始
// 		String res3 = OffLineCourseBusiness.EvaluateList("trainee","","settled","evaluateMy");//已结算
// 		String res4 = OffLineCourseBusiness.EvaluateList("trainee",studyplan_title,"unfinished","evaluateMy");//按任务名查询
// 		Boolean success = (Boolean) JSONPath.read(res1, "$.success");
// 		System.out.println("success="+success);
// 		Assert.assertTrue(success,"22.评价我的-学员对我的评价接口：" + res1);
 	}
 	
 	
 	@Test(description="23.评价我的-查看学员对我的评价详情接口", priority=23)
 	public void testEvaluateListMyDetail()  {
// 		System.out.println("23.评价我的-查看学员对我的评价详情接口:");
// 		String res1 = OffLineCourseBusiness.EvaluateMyDetail(offlinecourse_id,evaluate_id,lecturer_id);
// 		String res2 = OffLineCourseBusiness.EvaluateMyDetail(project_offlinecourse_id,project_evaluate_id,lecturer_id);
// 		Boolean success1 = (Boolean) JSONPath.read(res1, "$.success");
// 		Boolean success2 = (Boolean) JSONPath.read(res2, "$.success");
// 		System.out.println("success1="+success1+","+"success2="+success2);
// 		Assert.assertTrue(success1,"23.评价我的-查看学员对我的评价详情接口：" + res1);
 	}
 	
 	
 	@Test(description="24.评价我的-讲师对我的评价接口", priority=24)
 	public void testEvaluateListMyLecurter()  {
// 		System.out.println("24.评价我的-讲师对我的评价接口:");
// 		String res1 = OffLineCourseBusiness.EvaluateList("lecturer","","unfinished","evaluateMy");//未开始
// 		String res2 = OffLineCourseBusiness.EvaluateList("lecturer","","finished","evaluateMy");//已开始
// 		String res3 = OffLineCourseBusiness.EvaluateList("lecturer","","settled","evaluateMy");//已结算
// 		Boolean success = (Boolean) JSONPath.read(res1, "$.success");
// 		System.out.println("success="+success);
// 		Assert.assertTrue(success,"24.评价我的-讲师对我的评价接口：" + res1);
 	}
 	
 	
 	@Test(description="25.评价我的-查看讲师对我的评价详情接口", priority=25)
 	public void testEvaluateMyLecurterDetail()  {
// 		System.out.println("25.评价我的-查看讲师对我的评价详情接口:");
// 		String res1 = OffLineCourseBusiness.EvaluateMyLecurterDetail(offlinecourse_id,evaluate_id,"lecturer");
// 		String res2 = OffLineCourseBusiness.EvaluateMyLecurterDetail(project_offlinecourse_id,project_evaluate_id,"lecturer");
// 		Boolean success1 = (Boolean) JSONPath.read(res1, "$.success");
// 		Boolean success2 = (Boolean) JSONPath.read(res2, "$.success");
// 		System.out.println("success1="+success1+","+"success2="+success2);
// 		Assert.assertTrue(success1,"25.评价我的-查看讲师对我的评价详情接口：" + res1);
 	}
 	
 	
 	@Test(description="26.学习任务数据-线下课校验接口", priority=26)
 	public void testStudyPlanMonitor()  {
// 		System.out.println("26.学习任务数据-线下课校验接口:");
// 		String res1 = OffLineCourseBusiness.PlanMonitor(studyplan_id,"study_plan");
// 		String res2 = OffLineCourseBusiness.PlanMonitor(project_id,"study_project");
// 		Boolean success1 = (Boolean) JSONPath.read(res1, "$.success");
// 		Boolean success2 = (Boolean) JSONPath.read(res2, "$.success");
// 		System.out.println("success1="+success1+","+"success2="+success2);
// 		Assert.assertTrue(success1,"26.学习任务数据-线下课校验接口：" + res1);
 	}
 	
 	
 	@Test(description="27.学习任务/学习项目数据-线下课-人员监控数据校验接口", priority=27)
 	public void testOfflineCoursesMonitor()  {
// 		System.out.println("27.学习任务/学习项目数据-线下课-人员监控数据校验接口:");
// 		String res1 = OffLineCourseBusiness.OfflineCoursesMonitor(offlinecourse_id,studyplan_id,"");
// 		String res2 = OffLineCourseBusiness.OfflineCoursesMonitor(project_offlinecourse_id,project_id,"");
// 		Boolean success1 = (Boolean) JSONPath.read(res1, "$.success");
// 		Boolean success2 = (Boolean) JSONPath.read(res2, "$.success");
// 		System.out.println("success1="+success1+","+"success2="+success2);
// 		Assert.assertTrue(success1,"27.学习任务/学习项目数据-线下课-人员监控数据校验接口：" + res1);
 	}
 	
 	
 	@Test(description="28.学习任务/学习项目数据-线下课-考勤详情数据校验接口", priority=28)
 	public void testSignMonitor()  {
// 		System.out.println("28.学习任务/学习项目数据-线下课-考勤详情数据校验接口:");
// 		String res1 = OffLineCourseBusiness.SignMonitor(offlinecourse_id,studyplan_id,"",studyplan_sign_id);
// 		String res2 = OffLineCourseBusiness.SignMonitor(project_offlinecourse_id,project_id,"",project_sign_id);
// 		Boolean success1 = (Boolean) JSONPath.read(res1, "$.success");
// 		Boolean success2 = (Boolean) JSONPath.read(res2, "$.success");
// 		System.out.println("success1="+success1+","+"success2="+success2);
// 		Assert.assertTrue(success1,"28.学习任务/学习项目数据-线下课-考勤详情数据校验接口：" + res1);
 	}
 	
 	
 	@Test(description="29.学习任务/学习项目数据-线下课-评价分析列表数据校验接口", priority=29)
 	public void testEvaluateAnalysis()  {
// 		System.out.println("29.学习任务/学习项目数据-线下课-评价分析列表数据校验接口:");
// 		String res1 = OffLineCourseBusiness.EvaluateAnalysis(offlinecourse_id);
// 		String res2 = OffLineCourseBusiness.EvaluateAnalysis(project_offlinecourse_id);
// 		Boolean success1 = (Boolean) JSONPath.read(res1, "$.success");
// 		Boolean success2 = (Boolean) JSONPath.read(res2, "$.success");
// 		System.out.println("success1="+success1+","+"success2="+success2);
// 		Assert.assertTrue(success1,"29.学习任务/学习项目数据-线下课-评价分析列表数据校验接口：" + res1);
 	}
 	
 	
 	@Test(description="30.学习任务/学习项目数据-线下课-评价分析-评价详情数据校验接口", priority=30)
 	public void testEvaluateAnalysisDatail()  {
// 		System.out.println("30.学习任务/学习项目数据-线下课-评价分析-评价详情数据校验接口:");
// 		//讲师评价学员详情
// 		String res1 = OffLineCourseBusiness.EvaluateAnalysisDatail(offlinecourse_id,"trainee",evaluate_id,"score",lecturer_id);
// 		String res4 = OffLineCourseBusiness.EvaluateAnalysisDatail(project_offlinecourse_id,"trainee",project_evaluate_id,"score",lecturer_id);
// 		//学员评价讲师详情
// 		String res2 = OffLineCourseBusiness.EvaluateAnalysisDatail(offlinecourse_id,"trainee",evaluate_id,"star",lecturer_id);
// 		String res5 = OffLineCourseBusiness.EvaluateAnalysisDatail(project_offlinecourse_id,"trainee",project_evaluate_id,"star",lecturer_id);
// 		//学员评价课程详情
// 		String res3 = OffLineCourseBusiness.EvaluateAnalysisDatail(offlinecourse_id,"course",evaluate_id,"star",lecturer_id);
// 		String res6 = OffLineCourseBusiness.EvaluateAnalysisDatail(project_offlinecourse_id,"course",project_evaluate_id,"star",lecturer_id);
// 		Boolean success1 = (Boolean) JSONPath.read(res1, "$.success");
// 		Boolean success2 = (Boolean) JSONPath.read(res2, "$.success");
// 		System.out.println("success1="+success1+","+"success2="+success2);
// 		Assert.assertTrue(success1,"30.学习任务/学习项目数据-线下课-评价分析-评价详情数据校验接口：" + res1);
 	}
 	
 		
 	
 	@Test(description="31.编辑学习任务培训时间接口", priority=31)
 	public void testStudyPlanEdit()  {
// 		System.out.println("31.编辑学习任务培训时间接口:");
// 		String cover = "https://oss.coolcollege.cn/1807912811305766912.png";
// 		String course_duration = "5";
// 		long current_time = System.currentTimeMillis();
// 		long endTemp = current_time + 24L*7*3600*1000; 
// 		long startTemp = current_time + 24L*(-2)*3600*1000; 
// 		String start_timeTemp = String.valueOf(current_time);
// 		String end_timeTemp = String.valueOf(endTemp);
// 		String start = String.valueOf(startTemp);
// 		String res = OffLineCourseBusiness.StudyPlanEdit(studyplan_title,start,end_timeTemp,stageId,courseId,mappingId,
// 				cover,start_timeTemp,start_timeTemp,offline_course_setting_list_id,course_duration,lecturer_id,
// 				offline_course_sign_config_list_id,studyplan_id);
// 		String msg = (String)JSONPath.read(res, "$.msg");
// 		System.out.println("msg="+msg);
// 		Assert.assertEquals(msg,"修改计划成功！","31.编辑学习任务培训时间接口:"+res);
 	}
 	
 	
 	
 	@Test(description="32.编辑学习项目时间接口", priority=32)
 	public void testEditProject()  {
// 		String cover = "https://oss.coolcollege.cn/1810048818682974377.png";
// 	    String base_cover= "https://oss.coolcollege.cn/1810048818682974377.png";
// 		String course_duration = "4319";
// 		long current_time = System.currentTimeMillis();
// 		long endTemp = current_time + 24L*(-2)*3600*1000; 
// 		String start_timeTemp = String.valueOf(current_time);
// 		String end_timeTemp = String.valueOf(endTemp);
// 		String resSecond = OffLineCourseBusiness.EditProject(project_id,project_offlinecourse_id,project_title,cover,
// 				end_timeTemp,start_timeTemp,
// 				course_duration,project_offline_course_setting_list_id,lecturer_id,project_offline_course_sign_config_list_id);	
// 		String result = (String) JSONPath.read(resSecond, "$.result");		
// 		System.out.println("32.编辑学习项目时间接口:"+"project_id="+project_id);
// 		Assert.assertEquals(result, "true","32.编辑学习项目时间接口" + resSecond);
 	}
 	
 	

 	@Test(description="33.线下课总结接口", priority=33)
 	public void testOfflineCoursesSummary()  {
// 		System.out.println("33.线下课总结接口:");
// 		String summary = "summary test";
// 		String res1 = OffLineCourseBusiness.OfflineCoursesSummary(offlinecourse_id,summary);
// 		String res2 = OffLineCourseBusiness.OfflineCoursesSummary(project_offlinecourse_id,summary);
// 		Boolean success1 = (Boolean) JSONPath.read(res1, "$.success");
// 		Boolean success2 = (Boolean) JSONPath.read(res2, "$.success");
// 		System.out.println("success1="+success1+","+"success2="+success2);
// 		Assert.assertTrue(success1,"33.线下课总结接口：" + res1);
 	}
 	
 	
 	@Test(description="34.线下课总结-校验是否允许重复总结接口", priority=34)
 	public void testOfflineCoursesSummaryAgain()  {
// 		System.out.println("34.线下课总结-校验是否允许重复总结接口:");
// 		String summary = "summary test";
// 		String res1 = OffLineCourseBusiness.OfflineCoursesSummary(offlinecourse_id,summary);
// 		String res2 = OffLineCourseBusiness.OfflineCoursesSummary(project_offlinecourse_id,summary);
// 		Boolean success1 = (Boolean) JSONPath.read(res1, "$.success");
// 		Boolean success2 = (Boolean) JSONPath.read(res2, "$.success");
// 		System.out.println("success1="+success1+","+"success2="+success2);
// 		Assert.assertTrue(success1,"34.线下课总结-校验是否允许重复总结接口：" + res1);
 	}
 	
 	
 	@Test(description="35.线下课结算接口", priority=35)
 	public void testOfflineCoursesSettlement()  {
// 		System.out.println("35.线下课结算接口:");
// 		String res1 = OffLineCourseBusiness.OfflineCoursesSettlement(offlinecourse_id,lecturer_id);
// 		String res2 = OffLineCourseBusiness.OfflineCoursesSettlement(project_offlinecourse_id,lecturer_id);
// 		Boolean success1 = (Boolean) JSONPath.read(res1, "$.success");
// 		Boolean success2 = (Boolean) JSONPath.read(res2, "$.success");
// 		System.out.println("success1="+success1+","+"success2="+success2);
// 		Assert.assertTrue(success1,"35.线下课结算接口：" + res1);
 	}
 	
 	
 	
 	@Test(description="36.线下课结算-校验是否允许重复结算接口", priority=36)
 	public void testOfflineCoursesSettlementAgain()  {
// 		System.out.println("36.线下课结算-校验是否允许重复结算接口:");
// 		String res1 = OffLineCourseBusiness.OfflineCoursesSettlement(offlinecourse_id,lecturer_id);
// 		String res2 = OffLineCourseBusiness.OfflineCoursesSettlement(project_offlinecourse_id,lecturer_id);
// 		String message1 = (String) JSONPath.read(res1, "$.message");
// 		String message2 = (String) JSONPath.read(res2, "$.message");
// 		System.out.println("message1="+message1+","+"message2="+message2);
// 		Assert.assertEquals(message1,"offline course can not settlement","36.线下课结算-校验是否允许重复结算接口：" + res1);
 	}
 	
 	
 	@Test(description="37.学习任务/学习项目-线下课导出接口", priority=37)
 	public void testOfflineCourseExport()  {
// 		System.out.println("37.学习任务/学习项目-线下课导出接口:");
// 		String res1 = OffLineCourseBusiness.OfflineCourseExport(studyplan_id,"study_plan");
// 		String res2 = OffLineCourseBusiness.OfflineCourseExport(project_id,"study_project");
// 		Boolean success1 = (Boolean) JSONPath.read(res1, "$.success");
// 		Boolean success2 = (Boolean) JSONPath.read(res2, "$.success");
// 		System.out.println("success1="+success1+","+"success2="+success2);
// 		Assert.assertTrue(success1,"37.学习任务/学习项目-线下课导出接口：" + res1);
 	}
 	
 	
 	@Test(description="38.学习任务/学习项目-线下课-人员监控导出接口", priority=38)
 	public void testOfflineCourseMonitorExport()  {
// 		System.out.println("38.学习任务/学习项目-线下课-人员监控导出接口:");
// 		String res1 = OffLineCourseBusiness.OfflineCourseMonitorExport(offlinecourse_id,studyplan_id);
// 		String res2 = OffLineCourseBusiness.OfflineCourseMonitorExport(project_offlinecourse_id,project_id);
// 		Boolean success1 = (Boolean) JSONPath.read(res1, "$.success");
// 		Boolean success2 = (Boolean) JSONPath.read(res2, "$.success");
// 		System.out.println("success1="+success1+","+"success2="+success2);
// 		Assert.assertTrue(success1,"38.学习任务/学习项目-线下课-人员监控导出接口：" + res1);
 	}
 	
 	
 	@Test(description="39.学习任务/学习项目-线下课-考勤分析导出接口", priority=39)
 	public void testSignExport()  {
// 		System.out.println("39.学习任务/学习项目-线下课-考勤分析导出接口:");
// 		String res1 = OffLineCourseBusiness.SignExport(offlinecourse_id);
// 		String res2 = OffLineCourseBusiness.SignExport(project_offlinecourse_id);
// 		Boolean success1 = (Boolean) JSONPath.read(res1, "$.success");
// 		Boolean success2 = (Boolean) JSONPath.read(res2, "$.success");
// 		System.out.println("success1="+success1+","+"success2="+success2);
// 		Assert.assertTrue(success1,"39.学习任务/学习项目-线下课-考勤分析导出接口：" + res1);
 	}
 	
 	
 	@Test(description="40.学习任务/学习项目-线下课-评价分析-评价详情导出接口", priority=40)
 	public void testEvaluateAnalysisExport()  {
// 		System.out.println("40.学习任务/学习项目-线下课-评价分析-评价详情导出接口:");
// 		String res1 = OffLineCourseBusiness.EvaluateAnalysisExport(offlinecourse_id,evaluate_id,"score",lecturer_id,"trainee");
// 		String res2 = OffLineCourseBusiness.EvaluateAnalysisExport(offlinecourse_id,evaluate_id,"star",lecturer_id,"lecturer");
// 		String res3 = OffLineCourseBusiness.EvaluateAnalysisExport(offlinecourse_id,evaluate_id,"star",lecturer_id,"course");
// 		String res4 = OffLineCourseBusiness.EvaluateAnalysisExport(project_offlinecourse_id,project_evaluate_id,"score",lecturer_id,"trainee");
// 		String res5 = OffLineCourseBusiness.EvaluateAnalysisExport(project_offlinecourse_id,project_evaluate_id,"star",lecturer_id,"lecturer");
// 		String res6 = OffLineCourseBusiness.EvaluateAnalysisExport(project_offlinecourse_id,project_evaluate_id,"star",lecturer_id,"course");
// 		Boolean success1 = (Boolean) JSONPath.read(res1, "$.success");
// 		Boolean success2 = (Boolean) JSONPath.read(res2, "$.success");
// 		System.out.println("success1="+success1+","+"success2="+success2);
// 		Assert.assertTrue(success1,"40.学习任务/学习项目-线下课-评价分析-评价详情导出接口：" + res1);
 	}
 	
 	

 	@Test(description="41.讲师列表-查看-校验授课明细数据接口", priority=41)
 	public void testCoursesDetail()  {
// 		System.out.println("41.讲师列表-查看-校验授课明细数据接口:");
// 		String res = OffLineCourseBusiness.CoursesDetail(lecturer_id,"","","");
// 		String title_data = "";
// 		JSONArray eventMsgArray = (JSONArray) JSONPath.read(res, "$.list");
// 		for(Object obj :eventMsgArray) {
// 			JSONObject jsonObj	=	(JSONObject) JSONObject.parse(obj.toString());
// 			if(jsonObj.getString("title").contains(studyplan_title)) {
// 				title_data = jsonObj.getString("title");
// 			}			
// 		}				
// 		Integer total = (Integer) JSONPath.read(res, "$.total");
// 		System.out.println("title_data="+title_data);
// 		Assert.assertNotEquals(total,null,"41.讲师列表-查看-校验授课明细数据接口：" + res);
 	}
 	
 	
 	@Test(description="42.讲师列表-查看-校验积分明细数据接口", priority=42)
 	public void testScoreDetail()  {
// 		System.out.println("42.讲师列表-查看-校验积分明细数据接口:");
// 		String res = OffLineCourseBusiness.ScoreDetail("","");	
// 		String title_data = "";
// 		JSONArray eventMsgArray = (JSONArray) JSONPath.read(res, "$.data.page_info.list");
// 		for(Object obj :eventMsgArray) {
// 			JSONObject jsonObj	=	(JSONObject) JSONObject.parse(obj.toString());
// 			if(jsonObj.getString("content").contains(studyplan_title)) {
// 				title_data = jsonObj.getString("content");
// 			}			
// 		}	 		
// 		String msg = (String) JSONPath.read(res, "$.msg");
// 		System.out.println("msg="+msg);
// 		Assert.assertEquals(msg,"ok","42.讲师列表-查看-校验积分明细数据接口：" + res);
 	} 	
 		
 	
 	@Test(description="43.讲师列表-查看-校验课酬明细数据接口", priority=43)
 	public void testClassPay()  {
// 		System.out.println("43.讲师列表-查看-校验课酬明细数据接口:");
// 		String res = OffLineCourseBusiness.ClassPay(lecturer_id,"","");
// 		Double actual_class_pay = 0.0d;
// 		JSONArray eventMsgArray = (JSONArray) JSONPath.read(res, "$.data.page_info.list");
// 		for(Object obj :eventMsgArray) {
// 			JSONObject jsonObj	=	(JSONObject) JSONObject.parse(obj.toString());
// 			if(jsonObj.getString("event_msg").contains(studyplan_title)) {
// 				actual_class_pay = jsonObj.getDouble("actual_class_pay");
// 			}			
// 		}	
// 		String msg = (String) JSONPath.read(res, "$.msg");
// 		System.out.println("actual_class_pay="+actual_class_pay);
// 		Assert.assertEquals(msg,"ok","43.讲师列表-查看-校验课酬明细数据接口：" + res);
 	}
 	
 		
 	
 	@Test(description="44.删除学习任务接口", priority=44)
 	public void testDeleteStudy()  {
// 		String res = OffLineCourseBusiness.DeleteStudy(studyplan_id);
// 		String msg = (String) JSONPath.read(res, "$.msg");
// 		System.out.println("44.删除学习任务接口:"+"msg="+msg);
// 		Assert.assertEquals(msg, "删除学习计划成功","44.删除学习任务接口" + msg);
 	}
 	
 	
 	@Test(description="45.删除学习项目接口", priority=45)
 	public void testDeleteProject()  {
// 		String res = OffLineCourseBusiness.DeleteProject(project_id);
// 		String deleted = (String) JSONPath.read(res, "$.deleted");
// 		System.out.println("45.删除学习项目接口:"+"deleted="+deleted);
// 		Assert.assertEquals(deleted, "true","45.删除学习项目接口" + deleted);
 	}
 		
 	
 	@Test(description="46.讲师等级删除接口", priority=46)
 	public void testDeleteLecturerLever()  {
// 		System.out.println("46.讲师等级删除接口:");
// 		String res = OffLineCourseBusiness.DeleteLecturerLever(lever_id);
// 		String delete = (String) JSONPath.read(res, "$.deleted");
// 		System.out.println("delete="+delete);
// 		Assert.assertEquals(delete,"true","46.讲师等级删除接口：" + res);
 	}
 	
 	
 	
}
