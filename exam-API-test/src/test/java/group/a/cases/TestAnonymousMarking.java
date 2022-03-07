package group.a.cases;

import cn.kxy.group.a.business.AnonymousMarkingBusiness;
import cn.kxy.setting.bussiness.ClassificationBusines;
import com.alibaba.fastjson.JSONPath;
import com.lazy.common.utils.CommonData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TestAnonymousMarking {	
	public static String questionBank_id = "";
	public static String paperId = "";
	public static String exam_plan_blank_id = "";
	public static String exam_plan_id = "";
	public static String exam_false_id = "";
	public static String studyplan_id = "";
	public static String employee_train_id = "";
	
	String exam_blank_title = "填空题匿名"+ CommonData.getStringRandom(5);
	String exam_plan_title = "填空题和简答题匿名"+ CommonData.getStringRandom(5);
	String exam_false_title = "填空题和简答题都不匿名"+ CommonData.getStringRandom(5);
	String studyplan_title = "考试匿名阅卷"+ CommonData.getStringRandom(5);
	String train_name =  "考试匿名阅卷"+ CommonData.getStringRandom(5);
	String classification_id = ClassificationBusines.getPrimaryId();
	String title1 = "填空题和简答题都匿名";
 	String title2 = "只有填空题匿名";
 	String title3 = "填空题和简答题都不匿名";
 	String project_title = "考试匿名阅卷"+ CommonData.getStringRandom(5);

	
 	@Test(description="1.查询题库id接口", priority=1)
 	public void testResourceClassifyAdd()  {
 		String res = AnonymousMarkingBusiness.QueryQuestionBankList("ProportionalBank1");
 		System.out.println("1.查询题库id接口:");
 		questionBank_id =  (String)JSONPath.read(res, "$.data.list[0].id");
 		System.out.println("questionBank_id="+questionBank_id);
		Assert.assertNotEquals(questionBank_id,null,"1.查询题库id接口"+res);
 	}
 		
 	
 	@Test(description="2.添加考试任务-只有填空题匿名阅卷接口", priority=2)
 	public void testExamAdd()  throws Exception{
 		SimpleDateFormat date_temp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
 		String cuurrentTime = date_temp.format(new Date());
		Calendar calendarTemp = Calendar.getInstance();
		calendarTemp.add(Calendar.DATE, 7);
		String endTime = date_temp.format(calendarTemp.getTime());
		String res = AnonymousMarkingBusiness.ExamAdd("true","3",cuurrentTime,endTime,"1","2","10","0","0",
				"0",questionBank_id,"2","1","1","10","0","0",exam_blank_title,"0","0","");
 		System.out.println("2.添加考试任务-只有填空题匿名阅卷接口:");
 		exam_plan_blank_id = (String)JSONPath.read(res, "$.data");
 		String msg = (String)JSONPath.read(res, "$.msg");
 		System.out.println("exam_plan_blank_id="+exam_plan_blank_id+","+"msg="+msg); 		
		Assert.assertEquals(msg,"新增考试成功！","2.添加考试任务-只有填空题匿名阅卷接口"+res);
 	}
 		
 	
 	@Test(description="3.添加考试任务-填空题和简答题都匿名阅卷接口", priority=3)
 	public void testExamAddTrue()  throws Exception{
 		SimpleDateFormat date_temp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
 		String cuurrentTime = date_temp.format(new Date());
		Calendar calendarTemp = Calendar.getInstance();
		calendarTemp.add(Calendar.DATE, 7);
		String endTime = date_temp.format(calendarTemp.getTime());		
		String res = AnonymousMarkingBusiness.ExamAdd("true","2",cuurrentTime,endTime,"1","2","10","0","0",
				"0",questionBank_id,"2","1","1","10","0","0",exam_plan_title,"0","0","");
 		System.out.println("3.添加考试任务-填空题和简答题都匿名阅卷接口:");
 		exam_plan_id = (String)JSONPath.read(res, "$.data");
 		String msg = (String)JSONPath.read(res, "$.msg");
 		System.out.println("exam_plan_id="+exam_plan_id+","+"msg="+msg); 		
		Assert.assertEquals(msg,"新增考试成功！","3.添加考试任务-填空题和简答题都匿名阅卷接口"+res);
 	}
 		
 	
 	@Test(description="4.添加考试任务-填空题和简答题都不匿名阅卷接口", priority=4)
 	public void testExamAddFalse()  throws Exception{
 		SimpleDateFormat date_temp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
 		String cuurrentTime = date_temp.format(new Date());
		Calendar calendarTemp = Calendar.getInstance();
		calendarTemp.add(Calendar.DATE, 7);
		String endTime = date_temp.format(calendarTemp.getTime());
		String res = AnonymousMarkingBusiness.ExamAdd("false","2",cuurrentTime,endTime,"1","2","10","0","0",
				"0",questionBank_id,"2","1","1","10","0","0",exam_false_title,"0","0","");
 		System.out.println("4.添加考试任务-填空题和简答题都不匿名阅卷接口:");
 		exam_false_id = (String)JSONPath.read(res, "$.data");
 		String msg = (String)JSONPath.read(res, "$.msg");
 		System.out.println("exam_false_id="+exam_false_id+","+"msg="+msg); 		
		Assert.assertEquals(msg,"新增考试成功！","4.添加考试任务-填空题和简答题都不匿名阅卷接口"+res);
 	}
 	
 	
 	@Test(description="5.创建学习任务-填空和简答都匿名+只匿名填空+填空和简答都不匿名三个考试接口", priority=5)
 	public void testStudyPlanAdd()  {
 		String res = AnonymousMarkingBusiness.StudyPlanAdd(studyplan_title,title1,"2",questionBank_id,"1","10","1","10","true",title2,
 				"3",title3,"2","false"); 		
 		System.out.println("5.创建学习任务-填空和简答都匿名+只匿名填空+填空和简答都不匿名三个考试接口:");
 		studyplan_id = (String)JSONPath.read(res, "$.data");
 		String msg = (String)JSONPath.read(res, "$.msg");
 		System.out.println("msg="+msg+","+"studyplan_id="+studyplan_id);
 		Assert.assertEquals(msg,"新增计划成功！","5.创建学习任务-填空和简答都匿名+只匿名填空+填空和简答都不匿名三个考试接口口"+res);
 	}
 	
 	
 	
 	public static String project_id = "";
 	public static String projectCourse_id = "";
 	@Test(description="6.创建学习项目-填空和简答都匿名+只匿名填空+填空和简答都不匿名三个考试接口", priority=6)
 	public void testStudyProjectSave()  {
 		String cover = "https://oss.coolcollege.cn/1810048818682974377.png";
        String base_cover= "https://oss.coolcollege.cn/1810048818682974377.png";
 		String resFirst = AnonymousMarkingBusiness.StudyProjectSaveBaseInfo(project_title,classification_id,"normal",cover,base_cover);
 		project_id = (String) JSONPath.read(resFirst, "$.id"); 
 		projectCourse_id = (String) JSONPath.read(resFirst, "$.course_id");	
 		String resSecond = AnonymousMarkingBusiness.StudyProjectSaveStageContent(project_id,title1,"2",questionBank_id,"1","10","1","10",
 				"true",title2,"3",title3,"2","false");	
 		String resThird = AnonymousMarkingBusiness.StudyProjectSaveSettings(project_id);
 		String result = (String) JSONPath.read(resThird, "$.result");		
 		System.out.println("6.创建学习项目-填空和简答都匿名+只匿名填空+填空和简答都不匿名三个考试接口:"+"project_id="+project_id+","+"projectCourse_id="+projectCourse_id+","
 		+"resSecond="+resSecond);
 		Assert.assertEquals(result, "true","6.创建学习项目-填空和简答都匿名+只匿名填空+填空和简答都不匿名三个考试接口" + resThird);
 	}
 	
 	
 	public static String project_exam_id = "";
 	public static String project_blank_exam_id = "";
 	public static String project_false_exam_id = "";
 	@Test(description="7.学习项目详情获取考试id接口", priority=7)
 	public void testStudyProjectsDetail()  {
 		String res = AnonymousMarkingBusiness.StudyProjectsDetail(projectCourse_id);
 		project_exam_id = (String) JSONPath.read(res, "$.stages[0].resources[0].exam.id");
 		project_blank_exam_id = (String) JSONPath.read(res, "$.stages[0].resources[1].exam.id");
 		project_false_exam_id = (String) JSONPath.read(res, "$.stages[0].resources[2].exam.id");
 		System.out.println("7.学习项目详情获取考试id接口:"+"project_exam_id="+project_exam_id+","+"project_blank_exam_id="+
 				project_blank_exam_id+","+"project_false_exam_id="+project_false_exam_id);
 		Assert.assertNotEquals(project_exam_id,null,"7.学习项目详情获取考试id接口" + res);
 	}
 	
 	
 	@Test(description="8.创建新员工培训-填空和简答都匿名+只匿名填空+填空和简答都不匿名三个考试接口", priority=8)
 	public void testSaveOrUpdateBaseInfo()  {
 		System.out.println("8.创建新员工培训-填空和简答都匿名+只匿名填空+填空和简答都不匿名三个考试接口:");
 		String current_time = String.valueOf(System.currentTimeMillis());
 		String resFirst = AnonymousMarkingBusiness.SaveOrUpdateBaseInfo(train_name,current_time);
 		employee_train_id = (String) JSONPath.read(resFirst, "$.data");
 		String resSecond = AnonymousMarkingBusiness.UpdateContent(employee_train_id,title1,"2",questionBank_id,"1","10","1","10",
 				"true",title2,"3",title3,"2","false");
 		String resThird = AnonymousMarkingBusiness.UpdateSettingPublish(employee_train_id);		
 		String msg = (String)JSONPath.read(resSecond, "$.msg");
 		System.out.println("employee_train_id="+employee_train_id);
 		Assert.assertEquals(msg, "","8.创建新员工培训-填空和简答都匿名+只匿名填空+填空和简答都不匿名三个考试接口：" + resSecond);
 	}
 	
 	
 	public static String assign_id = "";
 	@Test(description="9.获取新员工培训任务列表接口", priority=9)
 	public void testGetList()  {
 		System.out.println("9.获取新员工培训任务列表接口:");
 		String res = AnonymousMarkingBusiness.GetList(train_name);
 		assign_id = (String) JSONPath.read(res, "$.list[0].id");
 		System.out.println("assign_id="+assign_id);
 		Assert.assertNotEquals(assign_id, "","9.获取新员工培训任务列表接口：" + res);
 	}
 	
 	@Test(description="10.新员工培训指派接口", priority=10)
 	public void testEmployeeTrainAssign()  {
 		System.out.println("10.新员工培训指派接口:");
 		String res = AnonymousMarkingBusiness.EmployeeTrainAssign(assign_id);
 		String result = (String) JSONPath.read(res, "$.result");
 		System.out.println("result="+result);
 		Assert.assertEquals(result, "指派成功","10.新员工培训指派接口：" + res);
 	}
 	
 	
 	public static String studyplan_exam_id = "";
 	public static String studyplan_blank_exam_id = "";
 	public static String studyplan_false_exam_id = "";
 	public static String employeetrain_exam_id = "";
 	public static String employeetrain_blank_exam_id = "";
 	public static String employeetrain_false_exam_id = "";
 	@Test(description="11.获取学习任务/新员工培训里的考试id接口", priority=11)
 	public void testStudyPlanExamId()  {
 		String res_studyplan = AnonymousMarkingBusiness.StudyPlanExamId(studyplan_id);
 		String res_employeetrain = AnonymousMarkingBusiness.StudyPlanExamId(employee_train_id);
 		System.out.println("11.获取学习任务/新员工培训里的考试id接口:");
 		studyplan_exam_id = (String)JSONPath.read(res_studyplan, "$.data.courseInfo[0].examId");
 		studyplan_blank_exam_id = (String)JSONPath.read(res_studyplan, "$.data.courseInfo[1].examId");
 		studyplan_false_exam_id = (String)JSONPath.read(res_studyplan, "$.data.courseInfo[2].examId");
 		employeetrain_exam_id = (String)JSONPath.read(res_employeetrain, "$.data.courseInfo[0].examId");
 		employeetrain_blank_exam_id = (String)JSONPath.read(res_employeetrain, "$.data.courseInfo[1].examId");
 		employeetrain_false_exam_id = (String)JSONPath.read(res_employeetrain, "$.data.courseInfo[2].examId");
 		String msg = (String)JSONPath.read(res_studyplan, "$.msg");
 		System.out.println("msg="+msg+","+"studyplan_exam_id="+studyplan_exam_id+","+"studyplan_blank_exam_id="+studyplan_blank_exam_id
 				+","+"studyplan_false_exam_id="+studyplan_false_exam_id+","+"employeetrain_exam_id="+employeetrain_exam_id+","+
 				"employeetrain_blank_exam_id="+employeetrain_blank_exam_id+","+"employeetrain_false_exam_id="+employeetrain_false_exam_id);
 		Assert.assertEquals(msg,"success","11.获取学习任务/新员工培训里的考试id接口"+res_studyplan);
 	}
 	

 	
 	@Test(description="12.PC端/移动端-考试任务/学习任务/新员工培训考试前check接口", priority=12)
 	public void testExamCheck() {	
 		String res = AnonymousMarkingBusiness.ExamCheck(exam_plan_id);
 		String res_blank = AnonymousMarkingBusiness.ExamCheck(exam_plan_blank_id);
 		String res_false = AnonymousMarkingBusiness.ExamCheck(exam_false_id);
 		String res_studyplan = AnonymousMarkingBusiness.ExamCheck(studyplan_exam_id);
 		String res_studyplan_blank = AnonymousMarkingBusiness.ExamCheck(studyplan_blank_exam_id);
 		String res_studyplan_false = AnonymousMarkingBusiness.ExamCheck(studyplan_false_exam_id);
 		String res_employeetrain = AnonymousMarkingBusiness.ExamCheck(employeetrain_exam_id);
 		String res_employeetrain_blank = AnonymousMarkingBusiness.ExamCheck(employeetrain_blank_exam_id);
 		String res_employeetrain_false = AnonymousMarkingBusiness.ExamCheck(employeetrain_false_exam_id);
 		System.out.println("12.PC端/移动端-考试任务/学习任务/新员工培训考试前check接口:");
 		String can_exam = (String)JSONPath.read(res, "$.can_exam");
 		System.out.println("can_exam="+can_exam);
		Assert.assertEquals(can_exam,"true","12.PC端/移动端-考试任务/学习任务/新员工培训考试前check接口"+res);
 	}
 	
 	
 	@Test(description="13.学习项目答题前的start接口", priority=13)
 	public void testStudyProjectsStart()  {
 		String res = AnonymousMarkingBusiness.StudyProjectsStart(projectCourse_id);
 		String result = (String) JSONPath.read(res, "$.result");
 		System.out.println("13.学习项目答题前的start接口:"+"result="+result);
 		Assert.assertEquals(result,"true","13.学习项目答题前的start接口" + res);
 	}
 	
 	
 	@Test(description="14.考试任务/学习项目/新员工培训答题前query接口", priority=14)
 	public void testExamDetailQuery() {	
 		String res_exam = AnonymousMarkingBusiness.ExamDetailQuery(exam_plan_id);
 		String res_exam_blank = AnonymousMarkingBusiness.ExamDetailQuery(exam_plan_blank_id);
 		String res_exam_false = AnonymousMarkingBusiness.ExamDetailQuery(exam_false_id);
 		String res_project = AnonymousMarkingBusiness.ExamDetailQuery(project_exam_id);
 		String res_project_blank = AnonymousMarkingBusiness.ExamDetailQuery(project_blank_exam_id);
 		String res_project_false = AnonymousMarkingBusiness.ExamDetailQuery(project_false_exam_id);
 		String res_employeetrain = AnonymousMarkingBusiness.ExamDetailQuery(employeetrain_exam_id);
 		String res_employeetrain_blank = AnonymousMarkingBusiness.ExamDetailQuery(employeetrain_blank_exam_id);
 		String res_employeetrain_false = AnonymousMarkingBusiness.ExamDetailQuery(employeetrain_false_exam_id);
 		System.out.println("14.考试任务/学习项目/新员工培训答题前query接口:");
 		String title = (String)JSONPath.read(res_exam, "$.title");
 		String project_title = (String)JSONPath.read(res_project, "$.title");
 		System.out.println("title="+title);
		Assert.assertEquals(title,exam_plan_title,"14.考试任务/学习项目/新员工培训答题前query接口"+res_exam);
 	}
 	
 	
 	public static String blank_id = "";
 	public static String short_id = "";
 	public static String blank_blank_id = "";
 	public static String blank_short_id = "";
 	public static String false_blank_id = "";
 	public static String false_short_id = "";
 	public static String studyplan_blank_id1 = "";
 	public static String studyplan_short_id1 = "";
 	public static String studyplan_blank_id2 = "";
 	public static String studyplan_short_id2 = "";
 	public static String studyplan_blank_id3 = "";
 	public static String studyplan_short_id3 = "";
 	public static String project_blank_id1 = "";
 	public static String project_short_id1 = "";
 	public static String project_blank_id2 = "";
 	public static String project_short_id2 = "";
 	public static String project_blank_id3 = "";
 	public static String project_short_id3 = "";
 	public static String employeetrain_blank_id1 = "";
 	public static String employeetrain_short_id1 = "";
 	public static String employeetrain_blank_id2 = "";
 	public static String employeetrain_short_id2 = "";
 	public static String employeetrain_blank_id3 = "";
 	public static String employeetrain_short_id3 = "";
 	@Test(description="15.查询题库下的各试题id接口", priority=15)
 	public void testQueryQuestionId()  {
 		String res = AnonymousMarkingBusiness.QueryQuestionId(exam_plan_id);
 		String res_blank = AnonymousMarkingBusiness.QueryQuestionId(exam_plan_blank_id);
 		String res_false = AnonymousMarkingBusiness.QueryQuestionId(exam_false_id);
 		String res_studyplan = AnonymousMarkingBusiness.QueryQuestionId(studyplan_exam_id);
 		String res_studyplan_blank = AnonymousMarkingBusiness.QueryQuestionId(studyplan_blank_exam_id);
 		String res_studyplan_false = AnonymousMarkingBusiness.QueryQuestionId(studyplan_false_exam_id);
 		String res_project = AnonymousMarkingBusiness.QueryQuestionId(project_exam_id);
 		String res_project_blank = AnonymousMarkingBusiness.QueryQuestionId(project_blank_exam_id);
 		String res_project_false = AnonymousMarkingBusiness.QueryQuestionId(project_false_exam_id);
 		String res_employeetrain = AnonymousMarkingBusiness.QueryQuestionId(employeetrain_exam_id);
 		String res_employeetrain_blank = AnonymousMarkingBusiness.QueryQuestionId(employeetrain_blank_exam_id);
 		String res_employeetrain_false = AnonymousMarkingBusiness.QueryQuestionId(employeetrain_false_exam_id);
 		System.out.println("15.查询题库下的各试题id接口:");
 		//考试任务里的
 		blank_id = (String)JSONPath.read(res, "$.questions[0].id");
 		short_id = (String)JSONPath.read(res, "$.questions[1].id");
 		blank_blank_id = (String)JSONPath.read(res_blank, "$.questions[0].id");
 		blank_short_id = (String)JSONPath.read(res_blank, "$.questions[1].id");
 		String title = (String)JSONPath.read(res_blank, "$.title");
 		false_blank_id = (String)JSONPath.read(res_false, "$.questions[0].id");
 		false_short_id = (String)JSONPath.read(res_false, "$.questions[1].id");
 		//学习任务里的
 		studyplan_blank_id1 = (String)JSONPath.read(res_studyplan, "$.questions[0].id");
 		studyplan_short_id1 = (String)JSONPath.read(res_studyplan, "$.questions[1].id");
 		studyplan_blank_id2 = (String)JSONPath.read(res_studyplan_blank, "$.questions[0].id");
 		studyplan_short_id2 = (String)JSONPath.read(res_studyplan_blank, "$.questions[1].id");
 		studyplan_blank_id3 = (String)JSONPath.read(res_studyplan_false, "$.questions[0].id");
 		studyplan_short_id3 = (String)JSONPath.read(res_studyplan_false, "$.questions[1].id");
 		//学习项目的
 		project_blank_id1 = (String)JSONPath.read(res_project, "$.questions[0].id");
 		project_short_id1 = (String)JSONPath.read(res_project, "$.questions[1].id");
 		project_blank_id2 = (String)JSONPath.read(res_project_blank, "$.questions[0].id");
 		project_short_id2 = (String)JSONPath.read(res_project_blank, "$.questions[1].id");
 		project_blank_id3 = (String)JSONPath.read(res_project_false, "$.questions[0].id");
 		project_short_id3 = (String)JSONPath.read(res_project_false, "$.questions[1].id");
 		//新员工培训的
 		employeetrain_blank_id1 = (String)JSONPath.read(res_employeetrain, "$.questions[0].id");
 		employeetrain_short_id1 = (String)JSONPath.read(res_employeetrain, "$.questions[1].id");
 		employeetrain_blank_id2 = (String)JSONPath.read(res_employeetrain_blank, "$.questions[0].id");
 		employeetrain_short_id2 = (String)JSONPath.read(res_employeetrain_blank, "$.questions[1].id");
 		employeetrain_blank_id3 = (String)JSONPath.read(res_employeetrain_false, "$.questions[0].id");
 		employeetrain_short_id3 = (String)JSONPath.read(res_employeetrain_false, "$.questions[1].id");
 		System.out.println("title="+title);
 		Assert.assertNotEquals(title,null,"15.查询题库下的各试题id接口"+res);
 	}
 		
 	
 	@Test(description="16.考试任务/学习任务/学习项目/新员工培训-提交试卷接口", priority=16)
 	public void testSubmitExamExamPlan()  {
 		String res_exam = AnonymousMarkingBusiness.SubmitExam(exam_plan_id,blank_id,short_id);
 		String res_exam_blank = AnonymousMarkingBusiness.SubmitExam(exam_plan_blank_id,blank_blank_id,blank_short_id);
 		String res_exam_false = AnonymousMarkingBusiness.SubmitExam(exam_false_id,false_blank_id,false_short_id);
 		String res_studyplan = AnonymousMarkingBusiness.SubmitExam(studyplan_exam_id,studyplan_blank_id1,studyplan_short_id1);
 		String res_studyplan_blank = AnonymousMarkingBusiness.SubmitExam(studyplan_blank_exam_id,studyplan_blank_id2,studyplan_short_id2);
 		String res_studyplan_false = AnonymousMarkingBusiness.SubmitExam(studyplan_false_exam_id,studyplan_blank_id3,studyplan_short_id3);
 		String res_project = AnonymousMarkingBusiness.SubmitExam(project_exam_id,project_blank_id1,project_short_id1);
 		String res_project_blank = AnonymousMarkingBusiness.SubmitExam(project_blank_exam_id,project_blank_id2,project_short_id2);
 		String res_project_false = AnonymousMarkingBusiness.SubmitExam(project_false_exam_id,project_blank_id3,project_short_id3);
 		String res_employeetrain = AnonymousMarkingBusiness.SubmitExam(employeetrain_exam_id,employeetrain_blank_id1,employeetrain_short_id1);
 		String res_employeetrain_blank = AnonymousMarkingBusiness.SubmitExam(employeetrain_blank_exam_id,employeetrain_blank_id2,employeetrain_short_id2);
 		String res_employeetrain_false = AnonymousMarkingBusiness.SubmitExam(employeetrain_false_exam_id,employeetrain_blank_id3,employeetrain_short_id3);
 		System.out.println("16.考试任务/学习任务/学习项目/新员工培训-提交试卷接口:");
 		String msg_exam = (String)JSONPath.read(res_exam, "$.msg");
 		String msg_blank = (String)JSONPath.read(res_exam_blank, "$.msg");
 		String msg_false = (String)JSONPath.read(res_exam_false, "$.msg");
 		String msg_studyplan = (String)JSONPath.read(res_studyplan, "$.msg");
 		String msg_studyplan_blank = (String)JSONPath.read(res_studyplan_blank, "$.msg");
 		String msg_studyplan_false = (String)JSONPath.read(res_studyplan_false, "$.msg");
 		String msg_project = (String)JSONPath.read(res_project, "$.msg");
 		String msg_project_blank = (String)JSONPath.read(res_project_blank, "$.msg");
 		String msg_project_false = (String)JSONPath.read(res_project_false, "$.msg");
 		String msg_employeetrain = (String)JSONPath.read(res_employeetrain, "$.msg");
 		String msg_employeetrain_blank = (String)JSONPath.read(res_employeetrain_blank, "$.msg");
 		String msg_employeetrain_false = (String)JSONPath.read(res_employeetrain_false, "$.msg");
 		System.out.println("msg_exam="+msg_exam+","+"msg_blank="+msg_blank+","+"msg_false="+msg_false+","+"msg_studyplan="+msg_studyplan
 				+","+"msg_studyplan_blank="+msg_studyplan_blank+","+"msg_studyplan_false="+msg_studyplan_false+","
 				+"msg_project="+msg_project+","+"msg_project_blank="+msg_project_blank+","+"msg_project_false="+msg_project_false
 				+","+"msg_employeetrain="+msg_employeetrain+","+"msg_employeetrain_blank="+msg_employeetrain_blank+","
 				+"msg_employeetrain_false="+msg_employeetrain_false);
 		Assert.assertEquals(msg_exam,"提交试卷成功!","16.考试任务/学习任务/学习项目/新员工培训-提交试卷接口"+res_exam);
 	}
 	
 	
 	
 	@Test(description="17.校验阅卷页是否匿名接口", priority=17)
 	public void testGetExamPlanPendingList()  {
 		String res_exam = AnonymousMarkingBusiness.GetExamPlanPendingList(exam_plan_id);
 		String res_exam_blank = AnonymousMarkingBusiness.GetExamPlanPendingList(exam_plan_blank_id);
 		String res_exam_false = AnonymousMarkingBusiness.GetExamPlanPendingList(exam_false_id);
 		String res_studyplan = AnonymousMarkingBusiness.GetExamPlanPendingList(studyplan_exam_id);
 		String res_studyplan_blank = AnonymousMarkingBusiness.GetExamPlanPendingList(studyplan_blank_exam_id);
 		String res_studyplan_false = AnonymousMarkingBusiness.GetExamPlanPendingList(studyplan_false_exam_id);
 		String res_project = AnonymousMarkingBusiness.GetExamPlanPendingList(project_exam_id);
 		String res_project_blank = AnonymousMarkingBusiness.GetExamPlanPendingList(project_blank_exam_id);
 		String res_project_false = AnonymousMarkingBusiness.GetExamPlanPendingList(project_false_exam_id);
 		String res_employeetrain = AnonymousMarkingBusiness.GetExamPlanPendingList(employeetrain_exam_id);
 		String res_employeetrain_blank = AnonymousMarkingBusiness.GetExamPlanPendingList(employeetrain_blank_exam_id);
 		String res_employeetrain_false = AnonymousMarkingBusiness.GetExamPlanPendingList(employeetrain_false_exam_id);
 		System.out.println("17.校验阅卷页是否匿名接口:");
 		String userName = (String)JSONPath.read(res_exam, "$.data.userList[0].userName");
 		String userName_blank = (String)JSONPath.read(res_exam_blank, "$.data.userList[0].userName");
 		String userName_false = (String)JSONPath.read(res_exam_false, "$.data.userList[0].userName");
 		String userName_studyplan = (String)JSONPath.read(res_studyplan, "$.data.userList[0].userName");
 		String userName_studyplan_blank = (String)JSONPath.read(res_studyplan_blank, "$.data.userList[0].userName");
 		String userName_studyplan_false = (String)JSONPath.read(res_studyplan_false, "$.data.userList[0].userName");
 		String userName_project = (String)JSONPath.read(res_project, "$.data.userList[0].userName");
 		String userName_project_blank = (String)JSONPath.read(res_project_blank, "$.data.userList[0].userName");
 		String userName_project_false = (String)JSONPath.read(res_project_false, "$.data.userList[0].userName");
 		String userName_employeetrain = (String)JSONPath.read(res_employeetrain, "$.data.userList[0].userName");
 		String userName_employeetrain_blank = (String)JSONPath.read(res_employeetrain_blank, "$.data.userList[0].userName");
 		String userName_employeetrain_false = (String)JSONPath.read(res_employeetrain_false, "$.data.userList[0].userName");
 		System.out.println("userName="+userName+","+"userName_blank="+userName_blank+","+"userName_false="+userName_false+","
 				+"userName_studyplan="+userName_studyplan+","+"userName_studyplan_blank="+userName_studyplan_blank+","+
 				"userName_studyplan_false="+userName_studyplan_false+","+"userName_project="+userName_project+","
 				+"userName_project_blank="+userName_project_blank+","+"userName_project_false="+userName_project_false+","
 				+"userName_employeetrain="+userName_employeetrain+","+"userName_employeetrain_blank="+userName_employeetrain_blank
 				+","+"userName_employeetrain_false="+userName_employeetrain_false);
 		Assert.assertEquals(userName,"***","17.校验阅卷页是否匿名接口"+res_exam);
 		Assert.assertEquals(userName_blank,"***","17.校验阅卷页是否匿名接口"+res_exam_blank);
 		Assert.assertNotEquals(userName_false,"***","17.校验阅卷页是否匿名接口"+res_exam_false);
 	}
 	
 	
 	
 	@Test(description="18.校验待阅卷弹窗是否匿名接口", priority=18)
 	public void testExamMarkings()  {
 		String res_studyplan = AnonymousMarkingBusiness.ExamMarkings(studyplan_id); 
 		String res_employee = AnonymousMarkingBusiness.ExamMarkings(employee_train_id);
 		System.out.println("18.校验待阅卷弹窗是否匿名接口:");
 		String name_studyplan = (String)JSONPath.read(res_studyplan, "$.page_info.list[0].name");
 		String name_studyplan_blank = (String)JSONPath.read(res_studyplan, "$.page_info.list[1].name");
 		String name_studyplan_false = (String)JSONPath.read(res_studyplan, "$.page_info.list[2].name");
 		String name_employee = (String)JSONPath.read(res_employee, "$.page_info.list[0].name");
 		String name_employee_blank = (String)JSONPath.read(res_employee, "$.page_info.list[1].name");
 		String name_employee_false = (String)JSONPath.read(res_employee, "$.page_info.list[2].name");
 		System.out.println("name_studyplan="+name_studyplan+","+"name_employee="+name_employee);
		Assert.assertEquals(name_studyplan,"**","18.校验待阅卷弹窗是否匿名接口"+res_studyplan);
		Assert.assertEquals(name_studyplan_blank,"**","18.校验待阅卷弹窗是否匿名接口"+res_studyplan);
		Assert.assertNotEquals(name_studyplan_false,"**","18.校验待阅卷弹窗是否匿名接口"+res_studyplan);
		Assert.assertEquals(name_employee,"**","18.校验待阅卷弹窗是否匿名接口"+res_employee);
		Assert.assertEquals(name_employee_blank,"**","18.校验待阅卷弹窗是否匿名接口"+res_employee);
		Assert.assertNotEquals(name_employee_false,"**","18.校验待阅卷弹窗是否匿名接口"+res_employee);
 	}

 	
 	@Test(description="19.删除考试任务接口", priority=19)
 	public void testDelateExam()  {
 		String res = AnonymousMarkingBusiness.DelateExam(exam_plan_id); 
 		String res_blank = AnonymousMarkingBusiness.DelateExam(exam_plan_blank_id);
 		String res_false = AnonymousMarkingBusiness.DelateExam(exam_false_id);
 		System.out.println("19.删除考试任务接口接口:");
 		String msg = (String)JSONPath.read(res, "$.msg");
 		System.out.println("msg="+msg);
		Assert.assertEquals(msg,"删除任务成功","19.删除考试任务接口接口"+res);
 	}
 	
 	
 	@Test(description="20.删除学习任务接口", priority=20)
 	public void testDelateStudyPlan()  {
 		String res = AnonymousMarkingBusiness.DelateStudyPlan(studyplan_id); 
 		System.out.println("20.删除学习任务接口接口:");
 		String msg = (String)JSONPath.read(res, "$.msg");
 		System.out.println("msg="+msg);
		Assert.assertEquals(msg,"删除学习计划成功","20.删除学习任务接口接口"+res);
 	}
 	
 	
 	@Test(description="21.删除学习项目接口", priority=21)
 	public void testDeleteProject()  {
 		String res = AnonymousMarkingBusiness.DeleteProject(project_id);
 		String deleted = (String) JSONPath.read(res, "$.deleted");
 		System.out.println("21.删除学习项目接口:"+"deleted="+deleted);
 		Assert.assertEquals(deleted, "true","21.删除学习项目接口" + deleted);
 	}
 	
 	@Test(description="22.删除新员工培训任务接口", priority=22)
 	public void testDeleteEmployTrainTask()  {
 		System.out.println("22.删除新员工培训任务接口:");
 		String res = AnonymousMarkingBusiness.DeleteEmployTrainTask(employee_train_id);
 		String msg = (String)JSONPath.read(res, "$.msg");
 		System.out.println("msg="+msg);
 		Assert.assertEquals(msg, "删除成功","22.删除新员工培训任务接口：" + res);
 	}

 	
 	

}
