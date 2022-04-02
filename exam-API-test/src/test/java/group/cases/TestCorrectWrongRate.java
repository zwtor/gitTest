package group.cases;

import cn.kxy.group.a.business.CorrectWrongRateBusiness;
import cn.kxy.setting.bussiness.ClassificationBusines;
import com.alibaba.fastjson.JSONPath;
import com.lazy.common.utils.CommonData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TestCorrectWrongRate {	
	public static String questionBank_id = "";
	public static String paperId = "";
	public static String paper_title = "";
	public static String exam_paper_plan_id = "";
	public static String exam_bank_plan_id = "";
	public static String employee_train_id = "";
	
	String exam_paper_title = "漏题给分固定试卷" + CommonData.getStringRandom(5);
	String exam_bank_title = "漏题给分随机试卷" + CommonData.getStringRandom(5);
	String studyPlan_title = "漏题给分" + CommonData.getStringRandom(5);
	String project_title = "漏题给分" + CommonData.getStringRandom(5);
	String train_name = "漏题给分" + CommonData.getStringRandom(5);
	String classification_id = ClassificationBusines.getPrimaryId();
	String multipleCount = "3";
	String multipleUnitScore = "10";
	String multipleMissedScore = "2";

 	@Test(description="1.查询题库id接口", priority=1)
 	public void testResourceClassifyAdd()  {
 		//"CorrectWrongRateBank"，多选题的答案是多个但不是所有选项
 		String res = CorrectWrongRateBusiness.QueryQuestionBankList("CorrectWrongRateBank");
 		System.out.println("1.查询题库id接口:");
 		questionBank_id =  (String)JSONPath.read(res, "$.data.list[0].id");
 		paper_title = (String)JSONPath.read(res, "$.list[0].title");
 		System.out.println("questionBank_id="+questionBank_id);
		Assert.assertNotEquals(questionBank_id,null,"1.查询题库id接口"+res);
 	}
 	
 	
 	@Test(description="2.查询试卷列表接口", priority=2)
 	public void testPaperGetList()  {
 		//"CorrectWrongRateBankPaper"，设置了漏题给分的固定试卷
 		String res = CorrectWrongRateBusiness.PaperGetList("CorrectWrongRateBankPaper");
 		System.out.println("2.查询试卷列表接口:");
 		paperId = (String)JSONPath.read(res, "$.list[0].id");
 		paper_title = (String)JSONPath.read(res, "$.list[0].title");
 		System.out.println("paperId="+paperId);
 		Integer total = (Integer)JSONPath.read(res, "$.total");
		Assert.assertNotEquals(total,0,"2.查询试卷列表接口"+res);
 	}
 	
 	
 	@Test(description="3.添加漏题给分的考试任务-固定试卷模式接口", priority=3)
 	public void testExamAdd()  throws Exception{
 		SimpleDateFormat date_temp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
 		String cuurrentTime = date_temp.format(new Date());//当前时间
		Calendar calendarTemp = Calendar.getInstance();
		calendarTemp.add(Calendar.DATE, 7);
		String endTime = date_temp.format(calendarTemp.getTime());		
 		String res = CorrectWrongRateBusiness.ExamAdd(paperId,cuurrentTime,endTime,"true",exam_paper_title);
 		System.out.println("3.添加漏题给分的考试任务-固定试卷模式接口:");
 		exam_paper_plan_id = (String)JSONPath.read(res, "$.data");
 		String msg = (String)JSONPath.read(res, "$.msg");
 		System.out.println("exam_paper_plan_id="+exam_paper_plan_id+","+"msg="+msg); 		
		Assert.assertEquals(msg,"新增考试成功！","3.添加漏题给分的考试任务-固定试卷模式接口"+res);
 	}
 	
 	
 	@Test(description="4.添加漏题给分的考试任务-随机试卷模式接口", priority=4)
 	public void testExamAddBank()  throws Exception{
 		SimpleDateFormat date_temp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
 		String cuurrentTime = date_temp.format(new Date());
		Calendar calendarTemp = Calendar.getInstance();
		calendarTemp.add(Calendar.DATE, 7);
		String endTime = date_temp.format(calendarTemp.getTime());
		String res = CorrectWrongRateBusiness.ExamAddbank(cuurrentTime,endTime,"0","1","0",multipleCount,multipleUnitScore,
				multipleMissedScore,questionBank_id,"2","0","1","0","0","0",exam_bank_title,"0","0","");
 		System.out.println("4.添加漏题给分的考试任务-随机试卷模式接口:");
 		exam_bank_plan_id = (String)JSONPath.read(res, "$.data");
 		String msg = (String)JSONPath.read(res, "$.msg");
 		System.out.println("exam_bank_plan_id="+exam_bank_plan_id+","+"msg="+msg); 		
		Assert.assertEquals(msg,"新增考试成功！","4.添加漏题给分的考试任务-随机试卷模式接口"+res);
 	}

 	
 	public static String multiplechoice_id1 = "";
 	public static String multiplechoice1_option1 = "";
 	public static String multiplechoice1_option2 = "";
 	public static String multiplechoice_id2 = "";
 	public static String multiplechoice2_option1 = "";
 	public static String multiplechoice2_option2 = "";
 	public static String multiplechoice2_option3 = "";
 	public static String multiplechoice_id3 = "";
 	public static String multiplechoice3_option1 = "";
 	public static String multiplechoice3_option2 = "";
 	public static String multiplechoice3_option3 = "";
 	public static String multiplechoice3_option4 = "";
 	
 	@Test(description="5.查询题库下的各试题id及答案id接口", priority=5)
 	public void testQueryQuestionIdBank()  {
 		String res = CorrectWrongRateBusiness.QueryQuestionId(exam_bank_plan_id);
 		System.out.println("5.查询题库下的各试题id及答案id接口:");
 		//第一题漏选
 		multiplechoice_id1 = (String)JSONPath.read(res, "$.questions[0].id");
 		multiplechoice1_option1 = (String)JSONPath.read(res, "$.questions[0].options[0].id");
 		multiplechoice1_option2 = (String)JSONPath.read(res, "$.questions[0].options[1].id");
 		//第二题全对
 		multiplechoice_id2 = (String)JSONPath.read(res, "$.questions[1].id");
 		multiplechoice2_option1 = (String)JSONPath.read(res, "$.questions[1].options[0].id");
 		multiplechoice2_option2 = (String)JSONPath.read(res, "$.questions[1].options[1].id");
 		multiplechoice2_option3 = (String)JSONPath.read(res, "$.questions[1].options[2].id");
 		//第三题多选
 		multiplechoice_id3 = (String)JSONPath.read(res, "$.questions[2].id");
 		multiplechoice3_option1 = (String)JSONPath.read(res, "$.questions[2].options[0].id");
 		multiplechoice3_option2 = (String)JSONPath.read(res, "$.questions[2].options[1].id");
 		multiplechoice3_option3 = (String)JSONPath.read(res, "$.questions[2].options[2].id");
 		multiplechoice3_option4 = (String)JSONPath.read(res, "$.questions[2].options[3].id");
 		String title = (String)JSONPath.read(res, "$.title");
 		System.out.println("title="+title);
 		Assert.assertNotEquals(title,null,"5.查询题库下的各试题id及答案id接口"+res);
 	}
 	
 	public static String studyPlan_id = "";
 	public static String title1 = "漏题给分-固定试卷";
 	public static String title2 = "漏题给分-随机试卷";
 	@Test(description="6.创建学习任务-有固定试卷模式+随机试卷模式接口", priority=6)
 	public void testStudyPlanAdd()  {
 		String res = CorrectWrongRateBusiness.StudyPlanAdd(studyPlan_title,title1,title2,paperId,paper_title,questionBank_id,
 				multipleCount,multipleUnitScore,multipleMissedScore); 		
 		System.out.println("6.创建学习任务-有固定试卷模式+随机试卷模式接口:");
 		studyPlan_id = (String)JSONPath.read(res, "$.data");
 		String msg = (String)JSONPath.read(res, "$.msg");
 		System.out.println("msg="+msg+","+"studyPlan_id="+studyPlan_id);
 		Assert.assertEquals(msg,"新增计划成功！","6.创建学习任务-有固定试卷模式+随机试卷模式接口"+res);
 	}
 	
 	
 	public static String project_id = "";
 	public static String projectCourse_id = "";
 	@Test(description="7.创建学习项目-有固定试卷模式+随机试卷模式接口", priority=7)
 	public void testStudyProjectSave()  {
 		String cover = "https://oss.coolcollege.cn/1810048818682974377.png";
        String base_cover= "https://oss.coolcollege.cn/1810048818682974377.png";
        String multipleCount = "3";
 		String resFirst = CorrectWrongRateBusiness.StudyProjectSaveBaseInfo(project_title,classification_id,"normal",cover,base_cover);
 		project_id = (String) JSONPath.read(resFirst, "$.id"); 
 		projectCourse_id = (String) JSONPath.read(resFirst, "$.course_id");	
 		String resSecond = CorrectWrongRateBusiness.StudyProjectSaveStageContent(project_id,title1,paperId,title2,questionBank_id,
 				multipleCount,multipleUnitScore,multipleMissedScore);	
 		String resThird = CorrectWrongRateBusiness.StudyProjectSaveSettings(project_id);
 		String result = (String) JSONPath.read(resThird, "$.result");		
 		System.out.println("7.创建学习项目-有固定试卷模式+随机试卷模式接口:"+"project_id="+project_id+","+"projectCourse_id="+projectCourse_id+","
 		+"resSecond="+resSecond);
 		Assert.assertEquals(result, "true","7.创建学习项目-有固定试卷模式+随机试卷模式接口" + resThird);
 	}
 	
 	
 	@Test(description="8.创建新员工培训-有固定试卷模式+随机试卷模式接口", priority=8)
 	public void testSaveOrUpdateBaseInfo()  {
 		System.out.println("8.创建新员工培训-有固定试卷模式+随机试卷模式接口:");
 		String resFirst = CorrectWrongRateBusiness.SaveOrUpdateBaseInfo(train_name);
 		employee_train_id = (String) JSONPath.read(resFirst, "$.data");
 		String resSecond = CorrectWrongRateBusiness.UpdateContent(employee_train_id,title1,paperId,paper_title,title2,questionBank_id,
 				multipleCount,multipleUnitScore,multipleMissedScore);
 		String resThird = CorrectWrongRateBusiness.UpdateSettingPublish(employee_train_id);		
 		String msg = (String)JSONPath.read(resSecond, "$.msg");
 		System.out.println("employee_train_id="+employee_train_id);
 		Assert.assertEquals(msg, "","8.创建新员工培训-有固定试卷模式+随机试卷模式接口：" + resSecond);
 	}
 	
 	
 	public static String assign_id = "";
 	@Test(description="9.获取新员工培训任务列表接口", priority=9)
 	public void testGetList()  {
 		System.out.println("9.获取新员工培训任务列表接口:");
 		String res = CorrectWrongRateBusiness.GetList(train_name);
 		assign_id = (String) JSONPath.read(res, "$.list[0].id");
 		System.out.println("assign_id="+assign_id);
 		Assert.assertNotEquals(assign_id, "","9.获取新员工培训任务列表接口：" + res);
 	}
 	
 	@Test(description="10.新员工培训指派接口", priority=10)
 	public void testEmployeeTrainAssign()  {
 		System.out.println("10.新员工培训指派接口:");
 		String res = CorrectWrongRateBusiness.EmployeeTrainAssign(assign_id);
 		String result = (String) JSONPath.read(res, "$.result");
 		System.out.println("result="+result);
 		Assert.assertEquals(result, "指派成功","10.新员工培训指派接口：" + res);
 	}
 	
 	
 	public static String studyplan_paper_exam_id = "";
 	public static String studyplan_bank_exam_id = "";
 	public static String employeetrain_paper_exam_id = "";
 	public static String employeetrain_bank_exam_id = "";
 	@Test(description="11.获取学习任务/新员工培训里的考试id接口", priority=11)
 	public void testStudyPlanExamId()  {
 		String res_studyplan = CorrectWrongRateBusiness.StudyPlanExamId(studyPlan_id);
 		String res_employeetrain = CorrectWrongRateBusiness.StudyPlanExamId(employee_train_id);
 		System.out.println("11.获取学习任务/新员工培训里的考试id接口:");
 		studyplan_paper_exam_id = (String)JSONPath.read(res_studyplan, "$.data.courseInfo[0].examId");
 		studyplan_bank_exam_id = (String)JSONPath.read(res_studyplan, "$.data.courseInfo[1].examId");
 		employeetrain_paper_exam_id = (String)JSONPath.read(res_employeetrain, "$.data.courseInfo[0].examId");
 		employeetrain_bank_exam_id = (String)JSONPath.read(res_employeetrain, "$.data.courseInfo[1].examId");
 		String msg = (String)JSONPath.read(res_studyplan, "$.msg");
 		System.out.println("msg="+msg+","+"studyplan_paper_exam_id="+studyplan_paper_exam_id+","+"studyplan_bank_exam_id="+studyplan_bank_exam_id
 				+","+"employeetrain_paper_exam_id="+employeetrain_paper_exam_id+","+"employeetrain_bank_exam_id="+employeetrain_bank_exam_id);
 		Assert.assertEquals(msg,"success","11.获取学习任务/新员工培训里的考试id接口"+res_studyplan);
 	}
 	
 	
 	public static String studyproject_paper_exam_id = "";
 	public static String studyproject_bank_exam_id = "";
 	@Test(description="12.学习项目详情获取考试id接口", priority=12)
 	public void testStudyProjectsDetail()  {
 		String res = CorrectWrongRateBusiness.StudyProjectsDetail(projectCourse_id);
 		studyproject_paper_exam_id = (String) JSONPath.read(res, "$.stages[0].resources[0].exam.id");
 		studyproject_bank_exam_id = (String) JSONPath.read(res, "$.stages[0].resources[1].exam.id");
 		System.out.println("12.学习项目详情获取考试id接口:"+"studyproject_paper_exam_id="+studyproject_paper_exam_id+","+"studyproject_bank_exam_id="+
 				studyproject_bank_exam_id);
 		Assert.assertNotEquals(studyproject_paper_exam_id,null,"12.学习项目详情获取考试id接口" + res);
 	}
 	
 	
 	@Test(description="13.PC端/移动端-考试任务/学习任务/新员工培训考试前check接口", priority=13)
 	public void testExamCheck() {	
 		String res = CorrectWrongRateBusiness.ExamCheck(exam_paper_plan_id);
 		String res_studyplan_paper = CorrectWrongRateBusiness.ExamCheck(studyplan_paper_exam_id);
 		String res_studyplan_bank = CorrectWrongRateBusiness.ExamCheck(studyplan_bank_exam_id);
 		String res_employeetrain_paper = CorrectWrongRateBusiness.ExamCheck(employeetrain_paper_exam_id);
 		String res_employeetrain_bank = CorrectWrongRateBusiness.ExamCheck(employeetrain_bank_exam_id);
 		System.out.println("13.PC端/移动端-考试任务/学习任务/新员工培训考试前check接口:");
 		String can_exam = (String)JSONPath.read(res, "$.can_exam");
 		System.out.println("can_exam="+can_exam);
		Assert.assertEquals(can_exam,"true","13.PC端/移动端-考试任务/学习任务/新员工培训考试前check接口"+res);
 	}
 	
 	
 	@Test(description="14.学习项目答题前的start接口", priority=14)
 	public void testStudyProjectsStart()  {
 		String res = CorrectWrongRateBusiness.StudyProjectsStart(projectCourse_id);
 		String result = (String) JSONPath.read(res, "$.result");
 		System.out.println("14.学习项目答题前的start接口:"+"result="+result);
 		Assert.assertEquals(result,"true","14.学习项目答题前的start接口" + res);
 	}
 	
 	
 	@Test(description="15.考试任务/学习项目/新员工培训答题前query接口", priority=15)
 	public void testExamDetailQuery() {	
 		String res_exam = CorrectWrongRateBusiness.ExamDetailQuery(exam_paper_plan_id);
 		String res_studyplan_paper = CorrectWrongRateBusiness.ExamDetailQuery(studyplan_paper_exam_id);
 		String res_studyplan_bank = CorrectWrongRateBusiness.ExamDetailQuery(studyplan_bank_exam_id);
 		String res_studyproject_paper = CorrectWrongRateBusiness.ExamDetailQuery(studyproject_paper_exam_id);
 		String res_studyproject_bank = CorrectWrongRateBusiness.ExamDetailQuery(studyproject_bank_exam_id);
 		String res_employeetrain_paper = CorrectWrongRateBusiness.ExamDetailQuery(employeetrain_paper_exam_id);
 		String res_employeetrain_bank = CorrectWrongRateBusiness.ExamDetailQuery(employeetrain_bank_exam_id);
 		System.out.println("15.考试任务/学习项目/新员工培训答题前query接口:");
 		String title = (String)JSONPath.read(res_exam, "$.title");
 		System.out.println("title="+title);
		Assert.assertEquals(title,exam_paper_title,"15.考试任务/学习项目/新员工培训答题前query接口"+res_exam);
 	}
 	
 	
 	@Test(description="16.考试任务/学习任务/学习项目/新员工培训-提交试卷-覆盖所有答题组合接口", priority=16)
 	public void testSubmitExamExamPlan()  {
 		//第一题漏选给漏选的分，第二题选对给满分，第三题多选给0分，覆盖所有场景
 		String res_exam_peper = CorrectWrongRateBusiness.SubmitExam(exam_paper_plan_id,multiplechoice_id1,multiplechoice1_option1,
 				multiplechoice1_option2,multiplechoice_id2,multiplechoice2_option1,multiplechoice2_option2,multiplechoice2_option3,
 				multiplechoice_id3,multiplechoice3_option1,multiplechoice3_option2,multiplechoice3_option3,multiplechoice3_option4);
 		String res_exam_bank = CorrectWrongRateBusiness.SubmitExam(exam_bank_plan_id,multiplechoice_id1,multiplechoice1_option1,
 				multiplechoice1_option2,multiplechoice_id2,multiplechoice2_option1,multiplechoice2_option2,multiplechoice2_option3,
 				multiplechoice_id3,multiplechoice3_option1,multiplechoice3_option2,multiplechoice3_option3,multiplechoice3_option4);
 		String res_studyplan_paper = CorrectWrongRateBusiness.SubmitExam(studyplan_paper_exam_id,multiplechoice_id1,multiplechoice1_option1,
 				multiplechoice1_option2,multiplechoice_id2,multiplechoice2_option1,multiplechoice2_option2,multiplechoice2_option3,
 				multiplechoice_id3,multiplechoice3_option1,multiplechoice3_option2,multiplechoice3_option3,multiplechoice3_option4);
 		String res_studyplan_bank = CorrectWrongRateBusiness.SubmitExam(studyplan_bank_exam_id,multiplechoice_id1,multiplechoice1_option1,
 				multiplechoice1_option2,multiplechoice_id2,multiplechoice2_option1,multiplechoice2_option2,multiplechoice2_option3,
 				multiplechoice_id3,multiplechoice3_option1,multiplechoice3_option2,multiplechoice3_option3,multiplechoice3_option4);
 		String res_studyproject_paper = CorrectWrongRateBusiness.SubmitExam(studyproject_paper_exam_id,multiplechoice_id1,multiplechoice1_option1,
 				multiplechoice1_option2,multiplechoice_id2,multiplechoice2_option1,multiplechoice2_option2,multiplechoice2_option3,
 				multiplechoice_id3,multiplechoice3_option1,multiplechoice3_option2,multiplechoice3_option3,multiplechoice3_option4);
 		String res_studyproject_bank = CorrectWrongRateBusiness.SubmitExam(studyproject_bank_exam_id,multiplechoice_id1,multiplechoice1_option1,
 				multiplechoice1_option2,multiplechoice_id2,multiplechoice2_option1,multiplechoice2_option2,multiplechoice2_option3,
 				multiplechoice_id3,multiplechoice3_option1,multiplechoice3_option2,multiplechoice3_option3,multiplechoice3_option4);
 		String res_employeetrain_paper = CorrectWrongRateBusiness.SubmitExam(employeetrain_paper_exam_id,multiplechoice_id1,multiplechoice1_option1,
 				multiplechoice1_option2,multiplechoice_id2,multiplechoice2_option1,multiplechoice2_option2,multiplechoice2_option3,
 				multiplechoice_id3,multiplechoice3_option1,multiplechoice3_option2,multiplechoice3_option3,multiplechoice3_option4);
 		String res_employeetrain_bank = CorrectWrongRateBusiness.SubmitExam(employeetrain_bank_exam_id,multiplechoice_id1,multiplechoice1_option1,
 				multiplechoice1_option2,multiplechoice_id2,multiplechoice2_option1,multiplechoice2_option2,multiplechoice2_option3,
 				multiplechoice_id3,multiplechoice3_option1,multiplechoice3_option2,multiplechoice3_option3,multiplechoice3_option4);
 		System.out.println("16.考试任务/学习任务/学习项目/新员工培训-提交试卷接口:");
 		String msg_exam_peper = (String)JSONPath.read(res_exam_peper, "$.msg");
 		String msg_exam_bank = (String)JSONPath.read(res_exam_bank, "$.msg");
 		String msg_studyplan_paper = (String)JSONPath.read(res_studyplan_paper, "$.msg");
 		String msg_studyplan_bank = (String)JSONPath.read(res_studyplan_bank, "$.msg");
 		String msg_studyproject_paper = (String)JSONPath.read(res_studyproject_paper, "$.msg");
 		String msg_studyproject_bank = (String)JSONPath.read(res_studyproject_bank, "$.msg");
 		String msg_employeetrain_paper = (String)JSONPath.read(res_employeetrain_paper, "$.msg");
 		String msg_employeetrain_bank = (String)JSONPath.read(res_employeetrain_bank, "$.msg");
 		System.out.println("msg_exam_peper="+msg_exam_peper+","+"msg_exam_bank="+msg_exam_bank+","+"msg_studyplan_paper="+msg_studyplan_paper
 				+","+"msg_studyplan_bank="+msg_studyplan_bank+","+"msg_studyproject_paper="+msg_studyproject_paper+","+"msg_studyproject_bank="+
 				msg_studyproject_bank+","+"msg_employeetrain_paper="+msg_employeetrain_paper+","+"msg_employeetrain_bank="+msg_employeetrain_bank);
 		Assert.assertEquals(msg_exam_peper,"提交试卷成功!","16.考试任务/学习任务/学习项目/新员工培训-提交试卷-覆盖所有答题组合接口"+res_exam_peper);
 		//Assert.assertEquals(msg_exam_bank,"提交试卷成功!","16.考试任务/学习任务/学习项目/新员工培训-提交试卷-覆盖所有答题组合接口"+res_exam_bank);
 	}
 	
 	
 	public static Double exam_paper_score = 0.0d;
 	public static Double exam_bank_score = 0.0d; 
 	//public static String scoreTemp = multipleUnitScore + multipleMissedScore;
 	//public static Double score =  Double.parseDouble(scoreTemp);
 	public static Double score = 12.0; //每道题10分，漏题2分，3种答题的组合总分=12分
 	@Test(description="17.校验考试任务-多种答题方式下漏题给分的得分是否正确接口", priority=17)
 	public void testQueryExamsScore()  {
 		String res_paper = CorrectWrongRateBusiness.QueryExamsScore(exam_paper_title);
 		String res_bank = CorrectWrongRateBusiness.QueryExamsScore(exam_bank_title);
 		System.out.println("17.校验考试任务-多种答题方式下漏题给分的得分是否正确接口:");
 		String score_paper = (String)JSONPath.read(res_paper, "$.list[0].score").toString();
 		String score_bank = (String)JSONPath.read(res_bank, "$.list[0].score").toString();
 		exam_paper_score =  Double.parseDouble(score_paper);
 		exam_bank_score =  Double.parseDouble(score_bank);
 		Integer total_paper = (Integer)JSONPath.read(res_paper, "$.total");
 		Integer total_bank = (Integer)JSONPath.read(res_bank, "$.total");
 		System.out.println("total_paper="+total_paper+","+"total_bank="+total_bank);
 		Assert.assertEquals(exam_paper_score,score,"17.校验考试任务-多种答题方式下漏题给分的得分是否正确接口"+res_paper);
 		Assert.assertEquals(exam_bank_score,score,"17.校验考试任务-多种答题方式下漏题给分的得分是否正确接口"+res_bank);
 	}
 	
 	
 	public static Double studyplan_paper_score = 0.0d;
 	public static Double studyplan_bank_score = 0.0d;
 	@Test(description="18.校验学习任务-多种答题方式下漏题给分的得分是否正确接口", priority=18)
 	public void testQueryStudyPlanScore()  {
 		String res = CorrectWrongRateBusiness.QueryStudyPlanScore(studyPlan_id);
 		System.out.println("18.校验学习任务-多种答题方式下漏题给分的得分是否正确接口:");
 		String score_paper = (String)JSONPath.read(res, "$.data.courseInfo[0].score").toString();
 		String score_bank = (String)JSONPath.read(res, "$.data.courseInfo[1].score").toString();
 		studyplan_paper_score =  Double.parseDouble(score_paper);
 		studyplan_bank_score =  Double.parseDouble(score_bank);
 		String msg = (String)JSONPath.read(res, "$.msg");
 		System.out.println("msg="+msg+","+"studyplan_paper_score="+studyplan_paper_score);
 		Assert.assertEquals(studyplan_paper_score,score,"18.校验学习任务-多种答题方式下漏题给分的得分是否正确接口"+res);
 		Assert.assertEquals(studyplan_bank_score,score,"18.校验学习任务-多种答题方式下漏题给分的得分是否正确接口"+res);
 	}
 	
 	
 	public static Double studyproject_paper_score = 0.0d;
 	public static Double studyproject_bank_score = 0.0d;
 	@Test(description="19.学习项目-多种答题方式下漏题给分的得分是否正确接口口", priority=19)
 	public void testStudyProjectsScore()  {
 		String res = CorrectWrongRateBusiness.StudyProjectsDetail(projectCourse_id);
 		String score_paper = (String) JSONPath.read(res, "$.stages[0].resources[0].exam.exam_score").toString();
 		String score_bank = (String) JSONPath.read(res, "$.stages[0].resources[1].exam.exam_score").toString();
 		studyproject_paper_score =  Double.parseDouble(score_paper);
 		studyproject_bank_score =  Double.parseDouble(score_bank);
 		System.out.println("19.学习项目-漏题给分的得分是否正确接口:"+"studyproject_paper_score="+studyproject_paper_score);
 		Assert.assertEquals(studyproject_paper_score,score,"19.学习项目-多种答题方式下漏题给分的得分是否正确接口" + res);
 		Assert.assertEquals(studyproject_bank_score,score,"19.学习项目-多种答题方式下漏题给分的得分是否正确接口" + res);
 	}
 	
 	
 	public static Double employeetrain_paper_score = 0.0d;
 	public static Double employeetrain_bank_score = 0.0d;
 	@Test(description="20.新员工培训-多种答题方式下漏题给分的得分是否正确接口", priority=20)
 	public void testStudyPlanScore()  {
 		String res = CorrectWrongRateBusiness.StudyPlanExamId(employee_train_id);
 		String score_paper = (String)JSONPath.read(res, "$.data.courseInfo[0].score").toString();;
 		String score_bank = (String)JSONPath.read(res, "$.data.courseInfo[0].score").toString();;
 		employeetrain_paper_score = Double.parseDouble(score_paper);
 		employeetrain_bank_score = Double.parseDouble(score_bank);
 		System.out.println("20.新员工培训-多种答题方式下漏题给分的得分是否正确接口:"+"employeetrain_paper_score="+employeetrain_paper_score+","
 				+"employeetrain_bank_score="+employeetrain_bank_score);
 		Assert.assertEquals(employeetrain_paper_score,score,"20.新员工培训-多种答题方式下漏题给分的得分是否正确接口" + res);
 		Assert.assertEquals(employeetrain_bank_score,score,"20.新员工培训-多种答题方式下漏题给分的得分是否正确接口" + res);
 	}
 		
 	
 	@Test(description="21.删除考试任务接口", priority=21)
 	public void testDelateExam()  {
 		String res_paper = CorrectWrongRateBusiness.DelateExam(exam_paper_plan_id); 
 		String res_bank = CorrectWrongRateBusiness.DelateExam(exam_bank_plan_id);
 		System.out.println("21.删除考试任务接口接口:");
 		String msg = (String)JSONPath.read(res_paper, "$.msg");
 		System.out.println("msg="+msg);
		Assert.assertEquals(msg,"删除任务成功","21.删除考试任务接口接口"+res_paper);
 	}
 	
 	
 	@Test(description="22.删除学习任务接口", priority=22)
 	public void testDelateStudyPlan()  {
 		String res = CorrectWrongRateBusiness.DelateStudyPlan(studyPlan_id); 
 		System.out.println("22.删除学习任务接口接口:");
 		String msg = (String)JSONPath.read(res, "$.msg");
 		System.out.println("msg="+msg);
		Assert.assertEquals(msg,"删除学习计划成功","22.删除学习任务接口接口"+res);
 	}
 	
 	
 	@Test(description="23.删除学习项目接口", priority=23)
 	public void testDeleteProject()  {
 		String res = CorrectWrongRateBusiness.DeleteProject(project_id);
 		String deleted = (String) JSONPath.read(res, "$.data.deleted");
 		Assert.assertEquals(deleted, "true","23.删除学习项目接口" + deleted);
 	}
 	
 	@Test(description="24.删除新员工培训任务接口", priority=24)
 	public void testDeleteEmployTrainTask()  {
 		System.out.println("24.删除新员工培训任务接口:");
 		String res = CorrectWrongRateBusiness.DeleteEmployTrainTask(employee_train_id);
 		String msg = (String)JSONPath.read(res, "$.msg");
 		System.out.println("msg="+msg);
 		Assert.assertEquals(msg, "删除成功","24.删除新员工培训任务接口：" + res);
 	}
 	
 	

}
