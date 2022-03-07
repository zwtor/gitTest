package data.cases;

import cn.kxy.base.business.BaseBusiness;
import cn.kxy.course.resources.bussiness.ArticleBusiness;
import cn.kxy.course.resources.bussiness.CourseBusiness;
import cn.kxy.data.business.DataBusiness;
import cn.kxy.examination.business.ExaminationTaskBusiness;
import cn.kxy.examination.business.PaperBusiness;
import cn.kxy.lecturer.business.LecturerListBusiness;
import cn.kxy.setting.bussiness.UserBusiness;
import init.cases.InitStudyAuthCourse;
import com.alibaba.fastjson.JSONPath;
import com.lazy.common.utils.CommonData;
import com.lazy.common.utils.DateUtil;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestData extends InitStudyAuthCourse{

	String study_name = "DataStudy"+CommonData.getStringRandom(5);
	String course_name = "DataCourse"+CommonData.getStringRandom(5);
	String exam_name = "DataExam"+CommonData.getStringRandom(5);
	
	String course_id = "";
	String study_id = "";
	String exam_id = "";
	
	int course_count ;
	int study_count ;
	int exam_count ;
	
	
	@Test(description = "查看数据初始值",priority = 1)
	public void queryDataSummary() {
		String sum_res = DataBusiness.queryDataSummary();
		System.out.println("this is a data module");
		exam_count = (int)JSONPath.read(sum_res, "$.examPlanCount");
		study_count = (int)JSONPath.read(sum_res, "$.planCount");
		course_count = (int)JSONPath.read(sum_res, "$.enterpriseCourseCount");
	}
	
	@Test(description = "新增课程",priority = 2)
	public void addCourse() {
		String addCourse = CourseBusiness.addCourse(course_name, "1", "this is desription", LecturerListBusiness.getIdByKeyword(outer_name), 
				ArticleBusiness.getIdByKeyword("" ), "0", "2", UserBusiness.getUserId(), "0", "3", "release");
		course_id = (String)JSONPath.read(addCourse, "$.data[0]");
		
	}
	@Test(description = "获取课程id",priority = 3)
	public void getIdByPage() {
//		String res = MyBusiness.querMyCreateCoursesList("", course_name);
//		course_id = (String)JSONPath.read(res, "$.list[0].course_id");
	}
	
	@Test(description = "新增学习任务",priority = 4)
	public void addOfflineTypeStudyTask() {
//		String res = StudyTaskBusiness.addOfflineTypeStudyTask(study_name, DateUtil.getTimeStamp(0, ""), DateUtil.getTimeStamp(3, ""),
//				DateUtil.getRegularDateForHHMM(1), DateUtil.getRegularDateForHHMM(2), LecturerListBusiness.getIdByKeyword(outer_name), DateUtil.getTimeStamp(1, ""));
//		String msg= (String)JSONPath.read(res, "$.msg");
//		Assert.assertEquals(msg, "新增计划成功！","添加线下学习任务，实际返回结果:"+res);
	}
	
	@Test(description = "获取学习id",priority = 5)
	public void getStudyId() {
//		String list_res = StudyTaskBusiness.getStudyTaskList(study_name, "true", "2", "");
//		study_id = (String)JSONPath.read(list_res, "$.list[0].id");
	}
	
	@Test(description = "新增考试",priority = 6)
	public void creatRewardExamTask() {
		ExaminationTaskBusiness.creatRewardExamTask("1", "hide", "60", "100", "4", exam_name,
				DateUtil.getRegularDate(0), DateUtil.getRegularDate(2), "false", UserBusiness.getUserId(),
				PaperBusiness.getIdByKeyword(BaseBusiness.pass_paper_name), "60", "0", "true", "1", "0", "60", "0", "0",
				"0", "0", "false", "false", UserBusiness.getUserId(),
				"{\"missScore\":4,\"passScore\":6,\"unPassScore\":2}");
	}
	@Test(description = "获取考试的id",priority = 7)
	public void getExamId() {
		String exam_list = ExaminationTaskBusiness.queryList(exam_name, "0", "false");
		exam_id= (String)JSONPath.read(exam_list, "$.list[0].id");
	}
	int study_end_count ;
	int exam_end_count ;
	
	int planFinishCount ;
	int examPlanPassCount ;
	int enterpriseCourseCount ;
	int platformCourseCount ;
	@Test(description="查看培训数据",priority= 8 )
	public void testQueryDataSummary() {
		String sum_res = DataBusiness.queryDataSummary();
		exam_end_count = (int)JSONPath.read(sum_res, "$.examPlanCount");
		study_end_count = (int)JSONPath.read(sum_res, "$.planCount");
		enterpriseCourseCount =(int)JSONPath.read(sum_res, "$.enterpriseCourseCount");
		planFinishCount  =(int)JSONPath.read(sum_res, "$.planFinishCount");
		examPlanPassCount  =(int)JSONPath.read(sum_res, "$.examPlanPassCount");
		platformCourseCount =(int)JSONPath.read(sum_res, "$.platformCourseCount");
//		Assert.assertTrue(exam_end_count>exam_count,"培训数据的考试总数校验"+sum_res);
//		Assert.assertTrue(enterpriseCourseCount>course_count,"培训数据的自建课程总数校验"+sum_res);
	}
	@Test(description="查看考试统计",priority= 9)
	public void testQueryExamStatisics() {
		String res = DataBusiness.queryExamStatisics("", "");
		int total = (int)JSONPath.read(res, "$[0].totalCount");
		Assert.assertTrue(total>=0,"查看考试统计，实际返回结果："+res);
	}
	@Test(description="查看学习统计",priority= 10)
	public void testQueryLearnStatisics() {
		String res = DataBusiness.queryLearnStatisics("", "");
		int totalCount = (int)JSONPath.read(res, "$[0].totalCount");
		Assert.assertTrue(totalCount>=0,"查看学习统计，实际返回结果："+res);
	}
	@Test(description="查看开通用户数量",priority=11)
	public void testQueryUserCount() {
		String res = DataBusiness.queryUserCount();
		Assert.assertNotNull(res, "查看开通用户数量,实际返回结果："+res);
	}

	@Test(description="考试及格后，查看数据报表",priority=12)
	public void testPassExamData() {
//		int startcount = DataBusiness.getexamPlanCount();
//		String name = "ExamDataPassed"+CommonData.getStringRandom(2);
//		ExaminationTaskBusiness.passToDoExam(name);
//		int endcount = DataBusiness.getexamPlanCount();
//		ExaminationTaskBusiness.deleteExamTask(ExaminationTaskBusiness.getIdByKeyword(name));
//		Assert.assertTrue(endcount-startcount==1,"考试及格后，查看数据报表实际返回结果："+DataBusiness.queryDataSummary());
	}
	@Test(description="查看APP的数据统计",priority= 13)
	public void testQueryAppData() {
//		String res = DataBusiness.queryAppData();
//		int plan_count = (int)JSONPath.read(res, "$.plan_count");
//		int plan_finish_count = (int)JSONPath.read(res, "$.plan_finish_count");
//		int exam_plan_count = (int)JSONPath.read(res, "$.exam_plan_count");
//		int exam_plan_finish_count = (int)JSONPath.read(res, "$.exam_plan_finish_count");
////		int course_count = (int)JSONPath.read(res, "$.course_count");
//		int enterprise_course_count = (int)JSONPath.read(res, "$.enterprise_course_count");
//		
//		Assert.assertEquals(plan_count, study_end_count,"企业端和app端的数据应保持一致，总学习任务数实际返回结果："+res);
//		Assert.assertEquals(plan_finish_count, planFinishCount,"企业端和app端的数据应保持一致，完成的学习任务数实际返回结果："+res);
//		Assert.assertEquals(exam_plan_count, exam_end_count,"企业端和app端的数据应保持一致，总考试任务数实际返回结果："+res);
//		Assert.assertEquals(exam_plan_finish_count, examPlanPassCount,"企业端和app端的数据应保持一致，已完成的考试任务数实际返回结果："+res);
////		Assert.assertEquals(course_count, enterpriseCourseCount + platformCourseCount,"企业端和app端的数据应保持一致，总课程数实际返回结果："+res);
//		Assert.assertEquals(enterprise_course_count, enterpriseCourseCount,"企业端和app端的数据应保持一致，企业自建课程数实际返回结果："+res);
	}

	@Test(description = "查看未完成的学习任务数据",priority = 14)
	public void testUnstartedPlan() {
		String res = DataBusiness.queryPlanData("", "unstarted");
		int total = (int)JSONPath.read(res, "$.total");
		Assert.assertTrue(total>=0,"查看未完成的学习任务数据，实际返回结果："+res);
	}
	@Test(description = "查看进行中的学习任务数据",priority = 15)
	public void testUnfinishedPlan() {
		String res = DataBusiness.queryPlanData("", "unfinished");
		int total = (int)JSONPath.read(res, "$.total");
		Assert.assertTrue(total>=0,"查看进行中的学习任务数据，实际返回结果："+res);
	}
	
	@Test(description = "查看已完成的学习任务数据",priority = 16)
	public void testFinishedPlan() {
		String res = DataBusiness.queryPlanData("", "finished");
		int total = (int)JSONPath.read(res, "$.total");
		Assert.assertTrue(total>=0,"查看已完成的学习任务数据，实际返回结果："+res);
	}
	String plan_id = "";
	@Test(description = "通过关键字查看进行中的学习任务数据",priority=17)
	public void testQueryPlanDataByKeyword() {
//		String res = DataBusiness.queryPlanData(study_name, "unfinished");
//		String title = (String)JSONPath.read(res, "$.list[0].title");
//		String create_user_name = (String)JSONPath.read(res, "$.list[0].create_user_name");
//		Long begin_time = (Long)JSONPath.read(res, "$.list[0].begin_time");
//		Long end_time = (Long)JSONPath.read(res, "$.list[0].end_time");
//		int finished_count = (int)JSONPath.read(res, "$.list[0].finished_count");
//		int ongoing_count = (int)JSONPath.read(res, "$.list[0].ongoing_count");
//		plan_id  = (String)JSONPath.read(res, "$.list[0].id");
//		Assert.assertEquals(title, study_name,"通过关键字查看进行中的学习任务数据,title实际返回结果："+res);
//		Assert.assertEquals(create_user_name, UserBusiness.getUsername(),"通过关键字查看进行中的学习任务数据,create_user_name实际返回结果："+res);
//		Assert.assertNotNull(begin_time,"通过关键字查看进行中的学习任务数据,begin_time实际返回结果："+res);
//		Assert.assertNotNull(end_time,"通过关键字查看进行中的学习任务数据,end_time实际返回结果："+res);
//		Assert.assertEquals(finished_count, 0,"通过关键字查看进行中的学习任务数据,finished_count实际返回结果："+res);
//		Assert.assertEquals(ongoing_count, 1,"通过关键字查看进行中的学习任务数据,ongoing_count实际返回结果："+res);
		
	}
	@Test(description = "查看数据---学习任务列表的任务详情",priority = 18)
	public void testQueryStudyDataInfo() {
//		String res = DataBusiness.queryStudyDataInfo(plan_id);
//		String title = (String)JSONPath.read(res, "$.plan_base.title");
//		String create_user_name = (String)JSONPath.read(res, "$.plan_base.create_user_name");
//		Long begin_time = (Long)JSONPath.read(res, "$.plan_base.begin_time");
//		Long end_time = (Long)JSONPath.read(res, "$.plan_base.end_time");
//		int finished_count = (int)JSONPath.read(res, "$.plan_base.finished_count");
//		int ongoing_count = (int)JSONPath.read(res, "$.plan_base.ongoing_count");
//		
//		Assert.assertEquals(title, study_name,"查看数据---学习任务列表的任务详情,title实际返回结果："+res);
//		Assert.assertEquals(create_user_name, UserBusiness.getUsername(),"查看数据---学习任务列表的任务详情,create_user_name实际返回结果："+res);
//		Assert.assertNotNull(begin_time,"查看数据---学习任务列表的任务详情,begin_time实际返回结果："+res);
//		Assert.assertNotNull(end_time,"查看数据---学习任务列表的任务详情,end_time实际返回结果："+res);
//		Assert.assertEquals(finished_count, 0,"查看数据---学习任务列表的任务详情,finished_count实际返回结果："+res);
//		Assert.assertEquals(ongoing_count, 1,"查看数据---学习任务列表的任务详情,ongoing_count实际返回结果："+res);
	}
	
	
	@Test(description = "查看未完成的考试任务数据",priority = 19)
	public void testUnstartedExam() {
		String res = DataBusiness.queryExamData("", "unstarted");
		int total = (int)JSONPath.read(res, "$.total");
		Assert.assertTrue(total>=0,"查看未完成的考试任务数据，实际返回结果："+res);
	}
	@Test(description = "查看进行中的考试任务数据",priority = 20)
	public void testUnfinishedExam() {
		String res = DataBusiness.queryExamData("", "unfinished");
		int total = (int)JSONPath.read(res, "$.total");
		Assert.assertTrue(total>=0,"查看进行中的考试任务数据，实际返回结果："+res);
	}
	
	@Test(description = "查看已完成的考试任务数据",priority = 21)
	public void testFinishedExam() {
		String res = DataBusiness.queryExamData("", "finished");
		int total = (int)JSONPath.read(res, "$.total");
		Assert.assertTrue(total>=0,"查看已完成的考试任务数据，实际返回结果："+res);
	}
	String exam_data_id = "";
	@Test(description = "通过关键字查看进行中的学习任务数据",priority = 22)
	public void testQueryExamDataByKeyWord() {
		String res = DataBusiness.queryExamData(exam_name, "unfinished");
		String title = (String)JSONPath.read(res, "$.list[0].title");
		String create_user_name = (String)JSONPath.read(res, "$.list[0].create_user_name");
		Long begin_time = (Long)JSONPath.read(res, "$.list[0].begin_time");
		Long end_time = (Long)JSONPath.read(res, "$.list[0].end_time");
		int unpass_count = (int)JSONPath.read(res, "$.list[0].unpass_count");
		int exam_count = (int)JSONPath.read(res, "$.list[0].exam_count");
		exam_data_id  = (String)JSONPath.read(res, "$.list[0].id");
		Assert.assertEquals(title, exam_name,"通过关键字查看进行中的学习任务数据,title实际返回结果："+res);
//		Assert.assertEquals(create_user_name, UserBusiness.getUsername(),"通过关键字查看进行中的学习任务数据,create_user_name实际返回结果："+res);
		Assert.assertNotNull(begin_time,"通过关键字查看进行中的学习任务数据,begin_time实际返回结果："+res);
		Assert.assertNotNull(end_time,"通过关键字查看进行中的学习任务数据,end_time实际返回结果："+res);
		Assert.assertEquals(unpass_count, 0,"通过关键字查看进行中的学习任务数据,unpass_count实际返回结果："+res);
		Assert.assertEquals(exam_count, 1,"通过关键字查看进行中的学习任务数据,exam_count实际返回结果："+res);
	}
	
	@Test(description = "查看移动端考试数据列表的考试详情--未考列表",priority = 23)
	public void testQueryExamDataAbsenceInfo() {
		String res = DataBusiness.queryExamDataInfo(exam_data_id, "absence");
//		String name = (String)JSONPath.read(res, "$.data.list[0].name");
		String title = (String)JSONPath.read(res, "$.exam_base.title");
		int unpass_count = (int)JSONPath.read(res, "$.exam_base.unpass_count");
		int pass_count = (int)JSONPath.read(res, "$.exam_base.pass_count");
//		String create_user_name = (String)JSONPath.read(res, "$.exam_base.create_user_name");
//		Assert.assertEquals(create_user_name, UserBusiness.getUsername(),""+res);
		Assert.assertEquals(pass_count, 0,"查看移动端考试数据列表的考试详情--未考列表"+res);
		Assert.assertEquals(unpass_count, 0,"查看移动端考试数据列表的考试详情--未考列表"+res);
		Assert.assertEquals(title, exam_name,"查看移动端考试数据列表的考试详情--未考列表"+res);
//		Assert.assertEquals(name, UserBusiness.getUsername(),"查看移动端考试数据列表的考试详情--未考列表"+res);
	}
	@Test(description = "查看移动端考试数据列表的考试详情--及格列表",priority = 24)
	public void testQueryExamDataPassedInfo() {
		String res = DataBusiness.queryExamDataInfo(exam_data_id, "passed");
		int total = (int)JSONPath.read(res, "$.data.total");
		Assert.assertTrue(total>=0,"查看移动端考试数据列表的考试详情--及格列表"+res);
	}
	
	@Test(description = "查看移动端考试数据列表的考试详情--不及格列表",priority = 25)
	public void testQueryExamDataFailedInfo() {
		String res = DataBusiness.queryExamDataInfo(exam_data_id, "failed");
		int total = (int)JSONPath.read(res, "$.data.total");
		Assert.assertTrue(total>=0,"查看移动端考试数据列表的考试详情--不及格列表"+res);
	}
	
	@Test(description = "查看移动端考试数据列表的考试详情--待阅卷",priority = 26)
	public void testQueryExamDataScoringInfo() {
		String res = DataBusiness.queryExamDataInfo(exam_data_id, "scoring");
		int total = (int)JSONPath.read(res, "$.data.total");
		Assert.assertTrue(total>=0,"查看移动端考试数据列表的考试详情--待阅卷列表"+res);
	}
	@Test(description = "删除考试",priority = 27)
	public void deleteExamTask() {
		ExaminationTaskBusiness.deleteExamTask(exam_id);
	}
	@Test(description = "删除学习任务",priority = 28)
	public void deleteStudyTask() {
//		StudyTaskBusiness.deleteStudyTask(study_id);
	}
	
	@Test(description = "删除课程",priority = 29)
	public void deleteCourse() {
		CourseBusiness.deleteCourse(course_id);
	}
}
