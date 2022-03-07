package group.a.cases;

import cn.kxy.group.a.business.ExamMakeUpBusiness;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import com.lazy.common.utils.CommonData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TestExamMakeUp {	
	public static String paperId = "";
	public static String exam_plan_id = "";
	public static String paper_title = "";
	public static String study_plan_id = "";
	public static String studyPlan_exam_id1 = "";
	public static String studyPlan_exam_id2 = "";
	//学习任务2个考试一个允许重复考试一个不允许重复考试
	public static String title1 = "允许重复考试";
	public static String title2 = "不允许重复考试";

	String exam_name = "不重复考试允许补考" + CommonData.getStringRandom(5);
	String study_plan_name = "补考的学习任务" + CommonData.getStringRandom(5);
	
 	@Test(description="1.查询试卷列表接口", priority=1)
 	public void testPaperGetList()  {
 		String res = ExamMakeUpBusiness.PaperGetList();
 		System.out.println("1.查询试卷列表接口:");
 		paperId = (String)JSONPath.read(res, "$.list[0].id");
 		paper_title = (String)JSONPath.read(res, "$.list[0].title");
 		System.out.println("paperId="+paperId);
 		Integer total = (Integer)JSONPath.read(res, "$.total");
		Assert.assertNotEquals(total,0,"1.查询试卷列表接口"+res);
 	}
 	
 	@Test(description="2.添加考试任务-允许重复考试接口", priority=2)
 	public void testExamAdd()  throws Exception{
 		SimpleDateFormat date_temp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
 		String cuurrentTime = date_temp.format(new Date());//当前时间
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, -2);//前2天时间
		String beginTime = date_temp.format(calendar.getTime());
		Calendar calendarTemp = Calendar.getInstance();
		calendarTemp.add(Calendar.DATE, -1);//前1天时间
		String endTime = date_temp.format(calendarTemp.getTime());		
 		String res = ExamMakeUpBusiness.ExamAdd(paperId,beginTime,endTime,"true",exam_name);
 		System.out.println("2.添加考试任务-允许重复考试接口:");
 		exam_plan_id = (String)JSONPath.read(res, "$.data");
 		String msg = (String)JSONPath.read(res, "$.msg");
 		System.out.println("exam_plan_id="+exam_plan_id+","+"msg="+msg); 		
		Assert.assertEquals(msg,"新增考试成功！","2.添加考试任务-允许重复考试接口"+res);
 	}
 	
 	@Test(description="3.考试任务数据监控页发布补考入口校验接口", priority=3)
 	public void testExamResult()  {
 		String res = ExamMakeUpBusiness.ExamResult(exam_plan_id);
 		System.out.println("3.考试任务数据监控页发布补考入口校验接口:");
 		String status = (String)JSONPath.read(res, "$.data.status");
 		String msg = (String)JSONPath.read(res, "$.msg");
 		System.out.println("status="+status+","+"msg="+msg);
		Assert.assertEquals(status,"end","3.考试任务数据监控页发布补考入口校验接口"+res);
 	}
 	
 	@Test(description="4.考试任务发布补考接口", priority=4)
 	public void testAddMakeUp() throws UnsupportedEncodingException  {
 		SimpleDateFormat date_temp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
 		String startTime = date_temp.format(new Date());
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, 7);
		String endTime = date_temp.format(calendar.getTime());	
 		String res = ExamMakeUpBusiness.AddMakeUp(exam_plan_id,startTime,endTime);
 		System.out.println("4.考试任务发布补考接口:");
 		String msg = (String)JSONPath.read(res, "$.msg");
 		System.out.println("msg="+msg);
		Assert.assertEquals(msg,"补考发布成功","4.考试任务发布补考接口"+res);
 	}
 	
 	
 	@Test(description="5.考试任务发布补考后再次发布补考接口", priority=5)
 	public void testAddMakeUpAgain() throws UnsupportedEncodingException  {
 		SimpleDateFormat date_temp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
 		String startTime = date_temp.format(new Date());
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, 7);
		String endTime = date_temp.format(calendar.getTime());	
 		String res = ExamMakeUpBusiness.AddMakeUp(exam_plan_id,startTime,endTime);
 		System.out.println("5.考试任务发布补考后再次发布补考接口:");
 		String msg = (String)JSONPath.read(res, "$.msg");
 		System.out.println("msg="+msg);
		Assert.assertEquals(msg,"上次补考未结束不能补考","5.考试任务发布补考后再次发布补考接口"+res);
 	}
 	
 	@Test(description="6.PC/移动端-我的考试任务列表判断是否有补考入口接口", priority=6)
 	public void testMyExamList()  {
 		String res = ExamMakeUpBusiness.MyExamList(); 
 		System.out.println("6.PC/移动端-我的考试任务列表判断是否有补考入口接口:");
 		//测试用例同时执行就不确定该考试任务在用户考试任务列表的list里的具体哪个位置，
 		//则需要把list看做一个整体，拆成多个jsonobj,匹配到包含exam_name的就拿这个jsonobj里的replenish_exam值
 		JSONArray replenish_examArray = (JSONArray) JSONPath.read(res, "$.list");
 		String replenish_exam = "";
 		for(Object obj :replenish_examArray) {
 			JSONObject jsonObj	=	(JSONObject) JSONObject.parse(obj.toString());
 			if(jsonObj.getString("title").contains(exam_name)) {
 				replenish_exam = jsonObj.getString("replenish_exam");
 			}
 		}
 		System.out.println("exam_name="+exam_name+","+"replenish_exam="+replenish_exam);
 		Assert.assertTrue(res.contains(exam_name),"6.PC/移动端-我的考试任务列表判断是否有补考入口接口"+res);
 		Assert.assertEquals(replenish_exam,"true","6.PC/移动端-我的考试任务列表判断是否有补考入口接口"+res);
 	}
 	
 	
 	@Test(description="7.PC/移动端-考试任务点击补考出弹窗校验接口", priority=7)
 	public void testEntranceQuery() {	
 		String res = ExamMakeUpBusiness.EntranceQuery(exam_plan_id);
 		System.out.println("7.PC/移动端-考试任务点击补考出弹窗校验接口:");
 		String title = (String)JSONPath.read(res, "$.title");
 		System.out.println("title="+title);
		Assert.assertEquals(title,exam_name,"7.PC/移动端-考试任务点击补考出弹窗校验接口"+res);
 	}
 	
 	@Test(description="8.PC端-进入补考页面前校验考试check接口", priority=8)
 	public void testExamCheck() {	
 		String res = ExamMakeUpBusiness.ExamCheck(exam_plan_id);
 		System.out.println("8.PC端-进入补考页面前校验考试check接口:");
 		String can_exam = (String)JSONPath.read(res, "$.can_exam");
 		System.out.println("can_exam="+can_exam);
		Assert.assertEquals(can_exam,"true","8.PC端-进入补考页面前校验考试check接口"+res);
 	}
 	
 	@Test(description="9.PC端-进入补考页面查询考试内容接口", priority=9)
 	public void testExamDetailQuery() {	
 		String res = ExamMakeUpBusiness.ExamDetailQuery(exam_plan_id);
 		System.out.println("9.PC端-进入补考页面查询考试内容接口:");
 		String title = (String)JSONPath.read(res, "$.title");
 		System.out.println("title="+title);
		Assert.assertEquals(title,exam_name,"9.PC端-进入补考页面查询考试内容接口"+res);
 	}
 	
 	
 	@Test(description="10.创建允许/不允许重复考试的学习任务接口", priority=10)
 	public void testPlanStudyAdd() {
 		SimpleDateFormat date_temp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
 		String cuurrentTime = date_temp.format(new Date());//当前时间
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, -2);//前2天时间
		String beginTime = date_temp.format(calendar.getTime());
		Calendar calendarTemp = Calendar.getInstance();
		calendarTemp.add(Calendar.DATE, -1);//前1天时间
		String endTime = date_temp.format(calendarTemp.getTime());	
 		String res = ExamMakeUpBusiness.PlanStudyAdd(study_plan_name,beginTime,endTime,title1,paperId,paper_title,title2);
 		System.out.println("10.创建允许/不允许重复考试的学习任务接口:");
 		study_plan_id = (String)JSONPath.read(res, "$.data");
 		String msg = (String)JSONPath.read(res, "$.msg");
 		System.out.println("study_plan_id="+study_plan_id+","+"msg="+msg);
		Assert.assertEquals(msg,"新增计划成功！","10.创建允许/不允许重复考试的学习任务接口"+res);
 	}
 	
 	
 	@Test(description="11.学习任务数据监控-考试分析-获取考试id接口", priority=11)
 	public void tesStudyExamQuery() {	
 		String res = ExamMakeUpBusiness.StudyExamQuery(study_plan_id);
 		System.out.println("11.学习任务数据监控-考试分析-获取考试id接口:");
 		studyPlan_exam_id1 = (String)JSONPath.read(res, "$.list[0].id");
 		studyPlan_exam_id2 = (String)JSONPath.read(res, "$.list[1].id");
 		String is_first_page = (String)JSONPath.read(res, "$.is_first_page");
 		System.out.println("studyPlan_exam_id1="+studyPlan_exam_id1+","+"studyPlan_exam_id2="+studyPlan_exam_id2+","+"is_first_page="+is_first_page);
		Assert.assertEquals(is_first_page,"true","11.学习任务数据监控-考试分析-获取考试id接口"+res);
 	}
 	
 	
 	@Test(description="12.学习任务数据监控-考试分析-校验是否有发布补考入口接口", priority=12)
 	public void tesStudyExamAnalyse() {	
 		String resRepeatExam = ExamMakeUpBusiness.StudyExamAnalyse(studyPlan_exam_id1);
 		String resNotRepeatExam = ExamMakeUpBusiness.StudyExamAnalyse(studyPlan_exam_id2);
 		System.out.println("12.学习任务数据监控-考试分析-校验是否有发布补考入口接口:");
		String statusRepeatExam = (String)JSONPath.read(resRepeatExam, "$.data.status");
		String statusNotRepeatExam = (String)JSONPath.read(resNotRepeatExam, "$.data.status");
 		String msgRepeatExam = (String)JSONPath.read(resRepeatExam, "$.msg");
 		String msgNotRepeatExam = (String)JSONPath.read(resNotRepeatExam, "$.msg");
 		System.out.println("statusRepeatExam="+statusRepeatExam+","+"statusNotRepeatExam="+statusNotRepeatExam);
 		System.out.println("msgRepeatExam="+msgRepeatExam+","+"msgNotRepeatExam="+msgNotRepeatExam);
		Assert.assertEquals(statusRepeatExam,"end","12.学习任务数据监控-考试分析-校验是否有发布补考入口接口"+resRepeatExam);
		Assert.assertEquals(statusNotRepeatExam,"end","12.学习任务数据监控-考试分析-校验是否有发布补考入口接口"+resNotRepeatExam);
 	}
 	
 	
 	@Test(description="13.学习任务发布补考接口", priority=13)
 	public void testStudyPlanAddMakeUp() throws UnsupportedEncodingException  {
 		SimpleDateFormat date_temp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
 		String startTime = date_temp.format(new Date());
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, 7);
		String endTime = date_temp.format(calendar.getTime());	
 		String resRepeatExam = ExamMakeUpBusiness.AddMakeUp(studyPlan_exam_id1,startTime,endTime);
 		String resNotRepeatExam = ExamMakeUpBusiness.AddMakeUp(studyPlan_exam_id2,startTime,endTime);
 		System.out.println("13.学习任务发布补考接口:");
 		String msgRepeatExam = (String)JSONPath.read(resRepeatExam, "$.msg");
 		String msgNotRepeatExam = (String)JSONPath.read(resNotRepeatExam, "$.msg");
 		System.out.println("msgRepeatExam="+msgRepeatExam+","+"msgNotRepeatExam="+msgNotRepeatExam);
		Assert.assertEquals(msgRepeatExam,"补考发布成功","13.学习任务发布补考接口"+resRepeatExam);
		Assert.assertEquals(msgNotRepeatExam,"补考发布成功","13.学习任务发布补考接口"+resNotRepeatExam);
 	}
 	
 	
 	@Test(description="14.学习任务发布补考后再次发布补考接口", priority=14)
 	public void testStudyPlanAddMakeUpAgain() throws UnsupportedEncodingException  {
 		SimpleDateFormat date_temp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
 		String startTime = date_temp.format(new Date());
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, 7);
		String endTime = date_temp.format(calendar.getTime());	
 		String resRepeatExam = ExamMakeUpBusiness.AddMakeUp(studyPlan_exam_id1,startTime,endTime);
 		String resNotRepeatExam = ExamMakeUpBusiness.AddMakeUp(studyPlan_exam_id2,startTime,endTime);
 		System.out.println("14.学习任务发布补考后再次发布补考接口:");
 		String msgRepeatExam = (String)JSONPath.read(resRepeatExam, "$.msg");
 		String msgNotRepeatExam = (String)JSONPath.read(resNotRepeatExam, "$.msg");
 		System.out.println("msgRepeatExam="+msgRepeatExam+","+"msgNotRepeatExam="+msgNotRepeatExam);
		Assert.assertEquals(msgRepeatExam,"上次补考未结束不能补考","14.学习任务发布补考后再次发布补考接口"+resRepeatExam);
		Assert.assertEquals(msgNotRepeatExam,"上次补考未结束不能补考","14.学习任务发布补考后再次发布补考接口"+resNotRepeatExam);
 	}
 	
 	@Test(description="15.PC/移动端-校验我的考试任务详情是否可以补考接口", priority=15)
 	public void testMyStudyPlanExamDetail()  {
 		String res = ExamMakeUpBusiness.MyTaskDetailList(study_plan_id); 
 		System.out.println("15.PC/移动端-校验我的考试任务详情是否可以补考接口:");
 		String statusRepeatExam = (String)JSONPath.read(res, "$.data.courseInfo[0].replenish_exam");
 		String statusNotRepeatExam = (String)JSONPath.read(res, "$.data.courseInfo[1].replenish_exam");
 		String msg = (String)JSONPath.read(res, "$.msg");
 		System.out.println("statusRepeatExam="+statusRepeatExam+","+"statusNotRepeatExam="+statusNotRepeatExam);
 		System.out.println("msg="+msg);
		Assert.assertEquals(statusRepeatExam,"true","15.PC/移动端-校验我的考试任务详情是否可以补考接口"+res);
		Assert.assertEquals(statusNotRepeatExam,"true","15.PC/移动端-校验我的考试任务详情是否可以补考接口"+res);
 	}
 	
 	
 	@Test(description="16.PC/移动端-考试任务点击补考出弹窗校验接口", priority=16)
 	public void testStudyEntranceQuery() {	
 		String resRepeatExam = ExamMakeUpBusiness.EntranceQuery(studyPlan_exam_id1);
 		String resNotRepeatExam = ExamMakeUpBusiness.EntranceQuery(studyPlan_exam_id2);
 		System.out.println("7.PC/移动端-考试任务点击补考出弹窗校验接口:");
 		String titleRepeatExam = (String)JSONPath.read(resRepeatExam, "$.title");
 		String titleNotRepeatExam = (String)JSONPath.read(resNotRepeatExam, "$.title");
 		System.out.println("titleRepeatExam="+titleRepeatExam+","+"titleNotRepeatExam="+titleNotRepeatExam);
		Assert.assertEquals(titleRepeatExam,title1,"7.PC/移动端-考试任务点击补考出弹窗校验接口"+resRepeatExam);
		Assert.assertEquals(titleNotRepeatExam,title2,"7.PC/移动端-考试任务点击补考出弹窗校验接口"+resNotRepeatExam);
 	}
 	
 	
 	@Test(description="17.删除考试任务接口", priority=17)
 	public void testDelateExam()  {
 		String res = ExamMakeUpBusiness.DelateExam(exam_plan_id); 
 		System.out.println("17.删除考试任务接口接口:");
 		String msg = (String)JSONPath.read(res, "$.msg");
 		System.out.println("msg="+msg);
		Assert.assertEquals(msg,"删除任务成功","17.删除考试任务接口接口"+res);
 	}
 	
 	
 	@Test(description="18.删除学习任务接口", priority=18)
 	public void testDelateStudyPlan()  {
 		String res = ExamMakeUpBusiness.DelateStudyPlan(study_plan_id); 
 		System.out.println("18.删除学习任务接口接口:");
 		String msg = (String)JSONPath.read(res, "$.msg");
 		System.out.println("msg="+msg);
		Assert.assertEquals(msg,"删除学习计划成功","18.删除学习任务接口接口"+res);
 	}
 	

}
