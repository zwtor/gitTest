package group.a.cases;

import cn.kxy.group.a.business.ScoringByQuestionBusiness;
import cn.kxy.setting.bussiness.ClassificationBusines;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import com.lazy.common.utils.CommonData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TestScoringByQuestion {	
	public static String questionBank_id = "";
	public static String studyPlan_id = "";
	public static String studyPlan_Exam_id = "";
	public static String sinagleChoice_id = "";
	public static String Multiplechoice_id = "";
	public static String JudgementQuestion_id = "";
	public static String blank_id = "";
	public static String short_answer_id = "";
	public static String blank_answerFirst = "";
	public static String short_answerFirst = "";
	public static String exam_plan_id = "";
	public static Double studyPlan_score = 0.0d;
	public static Double Exam_score = 0.0d;
	public static Double studyProject_exam_score = 0.0d;
	public static String projectCourse_id = "";
	public static String project_id = "";
	public static String studyProject_exam_id = "";
	public static String certificate_id = "";
	public static String qualifications_id = "";
	public static String qualifications_exam_id = "";
	public static Double qualifications_exam_score = 0.0d;
	
	String studyPlan_title = "按题给分" + CommonData.getStringRandom(5);
	String exam_title = "按题给分" + CommonData.getStringRandom(5);
	String title_project = "按题给分" + CommonData.getStringRandom(5);
	String classification_id = ClassificationBusines.getPrimaryId();
	String title_qualifications = "按题给分" + CommonData.getStringRandom(5);

	//1.新员工模块待前端开发解决完bug再写对应的脚本
	//2.当得分存在2位小数时，考试详情得分（不进位）和其他地方的得分（进位）不一致的现网bug
 	@Test(description="1.查询题库id接口", priority=1)
 	public void testResourceClassifyAdd()  {
 		//"sett"是线上可用的固定题库，填空题有2个空，简答题有2个空
 		String res = ScoringByQuestionBusiness.QueryQuestionBankList("sett");
 		System.out.println("1.查询题库id接口:");
 		questionBank_id =  (String)JSONPath.read(res, "$.data.list[0].id");
 		System.out.println("questionBank_id="+questionBank_id);
		Assert.assertNotEquals(questionBank_id,null,"1.查询题库id接口"+res);
 	}
 	
 	
 	@Test(description="2.创建有考试的学习任务-按题给分的随机试卷模式接口", priority=2)
 	public void testStudyPlanAdd()  {
 		//fillBlankType=2,shortAnswerType=2,都是按题给分，等于1是按空和按词给分
 		String fillBlankUnitScore = "15";
 		String shortAnswerUnitScore = "12";
 		String res = ScoringByQuestionBusiness.StudyPlanAdd(studyPlan_title,questionBank_id,
 				"1","10","1",fillBlankUnitScore,"2","1",shortAnswerUnitScore,"2","1","5","1","10","[]"); 		
 		System.out.println("3.创建有考试的学习任务-按题给分的随机试卷模式接口:");
 		System.out.println("2.创建有考试的学习任务-按题给分的随机试卷模式接口:");
 		studyPlan_id = (String)JSONPath.read(res, "$.data");
 		String msg = (String)JSONPath.read(res, "$.msg");
 		System.out.println("msg="+msg+","+"studyPlan_id="+studyPlan_id);
 		Assert.assertEquals(msg,"新增计划成功！","2.创建有考试的学习任务-按题给分的随机试卷模式接口"+res);
 	}
 	
 	
 	@Test(description="3.获取学习任务里的考试id接口", priority=2)
 	public void testStudyPlanExamId()  {
 		String res = ScoringByQuestionBusiness.StudyPlanExamId(studyPlan_id);
 		System.out.println("3.获取学习任务里的考试id接口:");
 		studyPlan_Exam_id = (String)JSONPath.read(res, "$.data.courseInfo[0].examId");
 		String msg = (String)JSONPath.read(res, "$.msg");
 		System.out.println("msg="+msg+","+"studyPlan_Exam_id="+studyPlan_Exam_id);
 		Assert.assertEquals(msg,"success","3.获取学习任务里的考试id接口"+res);
 	}
 	
 	
 	@Test(description="4.查询题库下的各试题id接口", priority=4)
 	public void testQueryQuestionId()  {
 		String res = ScoringByQuestionBusiness.QueryQuestionId(studyPlan_Exam_id);
 		System.out.println("4.查询题库下的各试题id接口:");
 		sinagleChoice_id = (String)JSONPath.read(res, "$.questions[0].id");
 		Multiplechoice_id = (String)JSONPath.read(res, "$.questions[1].id");
 		JudgementQuestion_id = (String)JSONPath.read(res, "$.questions[2].id");
 		blank_id = (String)JSONPath.read(res, "$.questions[3].id");
 		blank_answerFirst = (String)JSONPath.read(res, "$.questions[3].options[0].title");
 		short_answer_id = (String)JSONPath.read(res, "$.questions[4].id");
 		short_answerFirst = (String)JSONPath.read(res, "$.questions[4].options[0].title");
 		String title = (String)JSONPath.read(res, "$.title");
 		System.out.println("title="+title);
 		Assert.assertNotEquals(title,null,"4.查询题库下的各试题id接口"+res);
 	}
 		
 	
 	@Test(description="5.创建考试任务-按题给分的随机试卷模式接口", priority=5)
 	public void testExamAdd()  throws Exception{
 		SimpleDateFormat date_temp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
 		String cuurrentTime = date_temp.format(new Date());
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, 2);
		String beginTime = date_temp.format(calendar.getTime());
		Calendar calendarTemp = Calendar.getInstance();
		calendarTemp.add(Calendar.DATE, 9);
		String endTime = date_temp.format(calendarTemp.getTime());		
		String res = ScoringByQuestionBusiness.ExamAdd(beginTime,endTime,"1","2","12","1","10",questionBank_id,"2",
 				"1","2","5","1","10",exam_title,"1","10","");
 		System.out.println("5.创建考试任务-按题给分的随机试卷模式接口:");
 		exam_plan_id = (String)JSONPath.read(res, "$.data");
 		String msg = (String)JSONPath.read(res, "$.msg");
 		System.out.println("exam_plan_id="+exam_plan_id+","+"msg="+msg); 		
		Assert.assertEquals(msg,"新增考试成功！","5.创建考试任务-按题给分的随机试卷模式接口"+res);
 	}
 	
 	
 	@Test(description="6.查询考试任务编辑页信息接口", priority=6)
 	public void testQueryEditExam(){		
 		String res = ScoringByQuestionBusiness.QueryEditExam(exam_plan_id);
 		System.out.println("6.查询考试任务编辑页信息接口:");
 		String id = (String)JSONPath.read(res, "$.id");
 		System.out.println("id="+id); 		
		Assert.assertNotEquals(id,null,"6.查询考试任务编辑页信息接口"+res);
 	}
 	
 	
 	@Test(description="7.编辑考试任务-校验修改为按词给分和按空给分保存是否成功接口", priority=7)
 	public void testExamEdit()  throws Exception{
 		SimpleDateFormat date_temp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
 		String cuurrentTime = date_temp.format(new Date());
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, 2);
		String beginTime = date_temp.format(calendar.getTime());
		Calendar calendarTemp = Calendar.getInstance();
		calendarTemp.add(Calendar.DATE, 9);
		String endTime = date_temp.format(calendarTemp.getTime());	
		String title = exam_title + "编辑为按词给分";
 		String res = ScoringByQuestionBusiness.ExamEdit(beginTime,endTime,"1","12",questionBank_id,"2","1","5",title,
 				exam_plan_id,"unstarted");
 		System.out.println("7.编辑考试任务-校验修改为按词给分和按空给分保存是否成功接口:");
 		String msg = (String)JSONPath.read(res, "$.msg");
 		System.out.println("msg="+msg); 		
		Assert.assertEquals(msg,"修改考试成功！","7.编辑考试任务-校验修改为按词给分和按空给分保存是否成功接口"+res);
 	}
 	
 	
 	@Test(description="8.校验编辑的按词和按空给分是否成功接口", priority=8)
 	public void testQueryEditExamCheck(){		
 		String res = ScoringByQuestionBusiness.QueryEditExam(exam_plan_id);
 		System.out.println("8.校验编辑的按词按空给分是否成功接口:");
 		String fill_blank_type = (String)JSONPath.read(res, "$.fill_blank_type");
 		String short_answer_type = (String)JSONPath.read(res, "$.short_answer_type");
 		System.out.println("fill_blank_type="+fill_blank_type+","+"short_answer_type="+short_answer_type); 		
		Assert.assertEquals(fill_blank_type,"1","8.校验编辑的按词按空给分是否成功接口"+res);
		Assert.assertEquals(short_answer_type,"1","8.校验编辑的按词按空给分是否成功接口"+res);
 	}
 	
 	
 	@Test(description="9.编辑考试任务-校验修改为按题给分保存是否成功接口", priority=9)
 	public void testExamEditAgain() throws Exception{
 		SimpleDateFormat date_temp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
 		String beginTime = date_temp.format(new Date());
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, 7);
		String endTime = date_temp.format(calendar.getTime());
		String title = exam_title + "编辑为按题给分";
 		String res = ScoringByQuestionBusiness.ExamEdit(beginTime,endTime,"2","12",questionBank_id,"2","2","5",title,
 				exam_plan_id,"unstarted");
 		System.out.println("9.编辑考试任务-校验修改为按题给分保存是否成功接口:");
 		String msg = (String)JSONPath.read(res, "$.msg");
 		System.out.println("msg="+msg); 		
		Assert.assertEquals(msg,"修改考试成功！","9.编辑考试任务-校验修改为按题给分保存是否成功接口"+res);
 	}
 	
 	@Test(description="10.校验编辑为创建时的按题给分是否成功接口", priority=10)
 	public void testQueryEditExamCheckAgain(){		
 		String res = ScoringByQuestionBusiness.QueryEditExam(exam_plan_id);
 		System.out.println("10.校验编辑为创建时的按题给分是否成功接口:");
 		String fill_blank_type = (String)JSONPath.read(res, "$.fill_blank_type");
 		String short_answer_type = (String)JSONPath.read(res, "$.short_answer_type");
 		System.out.println("fill_blank_type="+fill_blank_type+","+"short_answer_type="+short_answer_type); 		
		Assert.assertEquals(fill_blank_type,"2","10.校验编辑为创建时的按题给分是否成功接口"+res);
		Assert.assertEquals(short_answer_type,"2","10.校验编辑为创建时的按题给分是否成功接口"+res);
 	}
 	
 	
 	
 	@Test(description="12.创建学习项目-按题给分的随机试卷模式接口", priority=12)
 	public void testStudyProjectSave()  {
 		String cover = "https://oss.coolcollege.cn/1810048818682974377.png";
        String base_cover= "https://oss.coolcollege.cn/1810048818682974377.png";
        String fill_blank_unit_score = "15";
 		String short_answer_unit_score = "12";
 		String resFirst = ScoringByQuestionBusiness.StudyProjectSaveBaseInfo(title_project,classification_id,cover,base_cover);
 		project_id = (String) JSONPath.read(resFirst, "$.id"); 
 		projectCourse_id = (String) JSONPath.read(resFirst, "$.course_id"); 
 		String resSecond = ScoringByQuestionBusiness.StudyProjectSaveStageContent(project_id,questionBank_id,"2","1","10","1",
 				fill_blank_unit_score,"2","1",short_answer_unit_score,"2","1","10","1","10","[]");		
 		String resThird = ScoringByQuestionBusiness.StudyProjectSaveSettings(project_id);
 		String result = (String) JSONPath.read(resThird, "$.result");		
 		System.out.println("12.创建学习项目-按题给分的随机试卷模式接口:"+"project_id="+project_id+","+"projectCourse_id="+projectCourse_id);
 		Assert.assertEquals(result, "true","12.创建学习项目-按题给分的随机试卷模式接口" + resThird);
 	}
 	
 	@Test(description="13.学习平台知识库下查询学习项目-自学学习项目接口", priority=13)
 	public void testStudyProjectsQuery()  {
 		String res = ScoringByQuestionBusiness.StudyProjectsQuery(title_project);
 		Integer total = (Integer) JSONPath.read(res, "$.total"); 		
 		System.out.println("13.学习平台知识库下查询学习项目-自学学习项目接口:"+"total="+total);
 		Assert.assertNotEquals(total,null,"13.学习平台知识库下查询学习项目-自学学习项目接口" + res);
 	}
 	
 	
 	@Test(description="14.学习项目详情获取考试id接口", priority=14)
 	public void testStudyProjectsDetail()  {
 		String res = ScoringByQuestionBusiness.StudyProjectsDetail(projectCourse_id);
 		studyProject_exam_id = (String) JSONPath.read(res, "$.stages[0].resources[0].exam.id");
 		System.out.println("14.学习项目详情获取考试id接口:"+"studyProject_exam_id="+studyProject_exam_id);
 		Assert.assertNotEquals(studyProject_exam_id,null,"14.学习项目详情获取考试id接口" + res);
 	}
 	
 	
 	@Test(description="21.获取证书列表接口", priority=21)
 	public void testCertificateGetList()  {
 		//任意获取一个证书id无需筛选条件
 		String res = ScoringByQuestionBusiness.CertificateGetList("Coo_cert");
 		certificate_id = (String) JSONPath.read(res, "$.list[0].id");
 		Integer total = (Integer) JSONPath.read(res, "$.total");
 		System.out.println("21.获取证书列表接口:"+"certificate_id="+certificate_id);
 		Assert.assertNotEquals(total, 0,"21.获取证书列表接口" + res);
 	}
 	
 	@Test(description="22.新建岗位认证-按题给分的随机试卷模式接口", priority=22)
 	public void testQualificationsAdd() throws UnsupportedEncodingException  {
 		String scopesTemp = "{\"department_list\":[],\"user_list\":[],\"group_list\":[],\"post_list\":[]}";
 		String scopes = JSONObject.parseObject(scopesTemp).toJSONString();
 		String questionMode = "2";
 		String fillBlankUnitScore = "14";
 		String fillBlankType = "2";
 		String shortAnswerUnitScore = "15";
 		String shortAnswerType = "2"; 		
 		String stageJsonTemp = "[{\"content\":\"\",\"stageName\":\"阶段1\",\"isOrder\":false,\"stageSort\":1,\"stageId\":\"\","
 				+ "\"startTime\":null,\"endTime\":null,\"course\":[{\"courseSort\":0,\"title\":\""+title_qualifications+"\","
 						+ "\"type\":\"ex\","
 				+ "\"cheatFlag\":0,\"faceRecognition\":\"off\",\"examDuration\":10,\"summary\":\"\",\"flag\":2,"
 				+ "\"mappingId\":\"\",\"courseId\":\"\",\"markType\":1,\"paperId\":\"\",\"paperTitle\":\"\",\"passLine\":100,"
 				+ "\"questionBankList\":\""+questionBank_id+"\",\"questionMode\":\""+questionMode+"\",\"showKnowledge\":\"show\","
 				+ "\"repeatExam\":true,\"multipleCount\":1,\"multipleUnitScore\":10,\"fillBlankCount\":1,"
 				+ "\"fillBlankUnitScore\":\""+fillBlankUnitScore+"\",\"fillBlankType\":\""+fillBlankType+"\","
 						+ "\"shortAnswerCount\":1,\"shortAnswerUnitScore\":\""+shortAnswerUnitScore+"\","
 				+ "\"shortAnswerType\":\""+shortAnswerType+"\",\"singleCount\":1,\"singleUnitScore\":10,\"trueOrFalseCount\":1,"
 				+ "\"trueOrFalseUnitScore\":10,\"totalScore\":\"\",\"answerParsing\":5,\"passingScore\":60,"
 				+ "\"cutScreenCount\":-1,\"reExamRule\":0,\"reExamNumber\":0,\"scoreRule\":0,\"viewRule\":1,"
 				+ "\"proportional\":[],\"resource_lock\":false}]}]";
 		String stageJson = JSONObject.parseArray(stageJsonTemp).toJSONString();
 		String res = ScoringByQuestionBusiness.QualificationsAdd(certificate_id,scopes,stageJson,title_qualifications);
 		String msg = (String) JSONPath.read(res, "$.msg");
 		qualifications_id = (String) JSONPath.read(res, "$.data");
 		System.out.println("22.新建岗位认证-按题给分的随机试卷模式接口:"+"msg="+msg+","+"qualifications_id="+qualifications_id);
 		Assert.assertEquals(msg, "新增计划成功！","22.新建岗位认证-按题给分的随机试卷模式接口" + res);
 	}
 	
 	
 	@Test(description="23.学习项目答题前的start接口", priority=23)
 	public void testStudyProjectsStart()  {
 		String res = ScoringByQuestionBusiness.StudyProjectsStart(projectCourse_id);
 		String result = (String) JSONPath.read(res, "$.result");
 		System.out.println("23.学习项目答题前的start接口:"+"result="+result);
 		Assert.assertEquals(result,"true","23.学习项目答题前的start接口" + res);
 	}
 	
 	
 	@Test(description="24.获取岗位认证-考试id接口", priority=24)
 	public void testQualificationsExamId()  {
 		String res = ScoringByQuestionBusiness.QualificationsExamId(qualifications_id);
 		qualifications_exam_id = (String) JSONPath.read(res, "$.stage_list[0].course_mapping_list[0].id");
 		System.out.println("24.获取岗位认证-考试id接口:"+"qualifications_exam_id="+qualifications_exam_id);
 		Assert.assertNotEquals(qualifications_exam_id,null,"24.获取岗位认证-考试id接口" + res);
 	}
 	
	
 	@Test(description="25.考试任务/学习项目/岗位认证答题前查询接口", priority=25)
 	public void testQueryExams()  {
 		String res_exam_plan = ScoringByQuestionBusiness.QueryExams(exam_plan_id);
 		String res_studyProject = ScoringByQuestionBusiness.QueryExams(studyProject_exam_id);
 		String res_qualifications = ScoringByQuestionBusiness.QueryExams(qualifications_exam_id);
 		System.out.println("25.考试任务/学习项目/岗位认证答题前查询接口:");
 		String title = (String)JSONPath.read(res_exam_plan, "$.title");
 		System.out.println("title="+title);
 		Assert.assertNotEquals(title,null,"25.考试任务/学习项目/岗位认证答题前查询接口"+res_exam_plan);
 	}
 	
 	
 	@Test(description="26.考试任务/学习任务/学习项目/岗位认证-提交试卷接口", priority=26)
 	public void testSubmitExamExamPlan()  {
 		String res_StudyPlan = ScoringByQuestionBusiness.SubmitExam(studyPlan_Exam_id,sinagleChoice_id,Multiplechoice_id,JudgementQuestion_id,
 				blank_id,short_answer_id,blank_answerFirst,short_answerFirst);
 		String res_ExamPlan = ScoringByQuestionBusiness.SubmitExam(exam_plan_id,sinagleChoice_id,Multiplechoice_id,JudgementQuestion_id,
 				blank_id,short_answer_id,blank_answerFirst,short_answerFirst);
 		String res_studyProject = ScoringByQuestionBusiness.SubmitExam(studyProject_exam_id,sinagleChoice_id,Multiplechoice_id,JudgementQuestion_id,
 				blank_id,short_answer_id,blank_answerFirst,short_answerFirst);
 		String res_qualifications = ScoringByQuestionBusiness.SubmitExam(qualifications_exam_id,sinagleChoice_id,Multiplechoice_id,JudgementQuestion_id,
 				blank_id,short_answer_id,blank_answerFirst,short_answerFirst);
 		System.out.println("26.考试任务/学习任务/学习项目-提交试卷接口:");
 		String msg_StudyPlan = (String)JSONPath.read(res_StudyPlan, "$.msg");
 		String msg_ExamPlan = (String)JSONPath.read(res_ExamPlan, "$.msg");
 		String msg_studyProject = (String)JSONPath.read(res_studyProject, "$.msg");
 		String msg_qualifications = (String)JSONPath.read(res_qualifications, "$.msg");
 		System.out.println("msg_StudyPlan="+msg_StudyPlan+","+"msg_ExamPlan="+msg_ExamPlan+","+"msg_studyProject="+
 		msg_studyProject+","+"msg_qualifications="+msg_qualifications);
 		Assert.assertEquals(msg_StudyPlan,"提交试卷成功!","26.考试任务/学习任务/学习项目/岗位认证-提交试卷接口"+res_StudyPlan);
 		Assert.assertEquals(msg_ExamPlan,"提交试卷成功!","26.考试任务/学习任务/学习项目/岗位认证-提交试卷接口"+res_StudyPlan);
 		Assert.assertEquals(msg_studyProject,"提交试卷成功!","26.考试任务/学习任务/学习项目/岗位认证-提交试卷接口"+res_studyProject);
 		Assert.assertEquals(msg_qualifications,"提交试卷成功!","26.考试任务/学习任务/学习项目/岗位认证-提交试卷接口"+res_qualifications);
 	}
 	

 	
 	@Test(description="27.获取学习任务-学员考试成绩（用于后边成绩判断）接口", priority=27)
 	public void testQueryStudyPlanScore()  {
 		String res = ScoringByQuestionBusiness.QueryStudyPlanScore(studyPlan_id);
 		System.out.println("27.获取学习任务-学员考试成绩（用于后边成绩判断）接口:");
 		String score = (String)JSONPath.read(res, "$.data.courseInfo[0].score").toString();
 		studyPlan_score =  Double.parseDouble(score);
 		String msg = (String)JSONPath.read(res, "$.msg");
 		System.out.println("msg="+msg+","+"studyPlan_score="+studyPlan_score);
 		Assert.assertEquals(msg,"success","27.获取学习任务-学员考试成绩（用于后边成绩判断）接口"+res);
 	}
 	
 	
 	@Test(description="28.获取考试任务-学员考试成绩（用于后边成绩判断）接口", priority=28)
 	public void testQueryExamsScore()  {
 		String res = ScoringByQuestionBusiness.QueryExamsScore(exam_title);
 		System.out.println("28.获取考试任务-学员考试成绩（用于后边成绩判断）接口:");
 		String score = (String)JSONPath.read(res, "$.list[0].score").toString();
 		Exam_score =  Double.parseDouble(score);
 		Integer total = (Integer)JSONPath.read(res, "$.total");
 		System.out.println("total="+total);
 		Assert.assertNotEquals(total,0,"28.获取考试任务-学员考试成绩（用于后边成绩判断）接口"+res);
 	}
 	
 	@Test(description="29.学习项目-获取学员考试成绩（用于后边成绩判断）接口", priority=29)
 	public void testStudyProjectsScore()  {
 		String res = ScoringByQuestionBusiness.StudyProjectsDetail(projectCourse_id);
 		String score = (String) JSONPath.read(res, "$.stages[0].resources[0].exam.exam_score").toString();
 		studyProject_exam_score =  Double.parseDouble(score);
 		System.out.println("29.学习项目-获取学员考试成绩（用于后边成绩判断）接口:"+"studyProject_exam_score="+studyProject_exam_score);
 		Assert.assertNotEquals(studyProject_exam_score,null,"29.学习项目-获取学员考试成绩（用于后边成绩判断）接口" + res);
 	}
 	
 	
 	@Test(description="30.岗位认证数据监控页-获取成绩（用于后边成绩判断）接口", priority=30)
 	public void testQualificationsScore()  {
 		String res = ScoringByQuestionBusiness.QualificationsMonitor(qualifications_id);
 		String score = (String) JSONPath.read(res, "$.data.vo_list.list[0].stage_list[0].course_infos[0].score").toString();
 		qualifications_exam_score =  Double.parseDouble(score);
 		System.out.println("30.岗位认证数据监控页-获取成绩（用于后边成绩判断）接口:"+"studyProject_exam_score="+studyProject_exam_score);
 		Assert.assertNotEquals(qualifications_exam_score,null,"30.岗位认证数据监控页-获取成绩（用于后边成绩判断）接口" + res);
 	}
 		
 	
 	@Test(description="32.学习任务/考试任务/学习项目/岗位认证-考试结果页-校验试卷详情页成绩是否正确接口", priority=32)
 	public void testResultScore()  {
 		String res_studyPlan = ScoringByQuestionBusiness.ResultScore(studyPlan_Exam_id);
 		String res_examPlan = ScoringByQuestionBusiness.ResultScore(exam_plan_id);
 		String res_studyProject = ScoringByQuestionBusiness.ResultScore(studyProject_exam_id);
 		String res_qualifications = ScoringByQuestionBusiness.ResultScore(qualifications_exam_id);
 		System.out.println("32.学习任务/考试任务/学习项目/岗位认证-考试结果页-校验试卷详情页成绩是否正确接口:");
 		String resultScore_studyPlan = (String)JSONPath.read(res_studyPlan, "$.score").toString();
 		Double studyPlan_resultScore = Double.parseDouble(resultScore_studyPlan);
 		String resultScore_examPlan = (String)JSONPath.read(res_examPlan, "$.score").toString();
 		Double examPlan_resultScore = Double.parseDouble(resultScore_examPlan);
 		String resultScore_studyProject = (String)JSONPath.read(res_studyProject, "$.score").toString();
 		Double studyProject_resultScore = Double.parseDouble(resultScore_studyProject);
 		String resultScore_qualifications = (String)JSONPath.read(res_qualifications, "$.score").toString();
 		Double qualifications_resultScore = Double.parseDouble(resultScore_qualifications);
 		System.out.println("studyPlan_resultScore="+studyPlan_resultScore+","+"examPlan_resultScore="+examPlan_resultScore
 				+","+"studyProject_resultScore="+studyProject_resultScore);
 		Assert.assertEquals(studyPlan_resultScore,studyPlan_score,"32.学习任务/考试任务/学习项目/岗位认证-考试结果页-校验试卷详情页成绩是否正确接口"+res_studyPlan);
 		Assert.assertEquals(examPlan_resultScore,Exam_score,"32.学习任务/考试任务/学习项目/岗位认证-考试结果页-校验试卷详情页成绩是否正确接口"+res_examPlan);
 		Assert.assertEquals(studyProject_resultScore,studyProject_exam_score,"32.学习任务/考试任务/学习项目/岗位认证-考试结果页-校验试卷详情页成绩是否正确接口"+res_studyProject);
 		Assert.assertEquals(qualifications_resultScore,qualifications_exam_score,"32.学习任务/考试任务/学习项目/岗位认证-考试结果页-校验试卷详情页成绩是否正确接口"+res_qualifications);
 	}
 	
 		 	
 	@Test(description="40.删除考试任务接口", priority=40)
 	public void testDelateExam()  {
 		String res = ScoringByQuestionBusiness.DelateExam(exam_plan_id); 
 		System.out.println("40.删除考试任务接口接口:");
 		String msg = (String)JSONPath.read(res, "$.msg");
 		System.out.println("msg="+msg);
		Assert.assertEquals(msg,"删除任务成功","40.删除考试任务接口接口"+res);
 	}
 	
 	
 	@Test(description="41.删除学习任务接口", priority=41)
 	public void testDelateStudyPlan()  {
 		String res = ScoringByQuestionBusiness.DelateStudyPlan(studyPlan_id); 
 		System.out.println("41.删除学习任务接口接口:");
 		String msg = (String)JSONPath.read(res, "$.msg");
 		System.out.println("msg="+msg);
		Assert.assertEquals(msg,"删除学习计划成功","41.删除学习任务接口接口"+res);
 	}
 	
 	
 	@Test(description="42.删除学习项目接口", priority=42)
 	public void testDeleteProject()  {
 		String res = ScoringByQuestionBusiness.DeleteProject(project_id);
 		String deleted = (String) JSONPath.read(res, "$.deleted");
 		System.out.println("42.删除学习项目接口:"+"deleted="+deleted);
 		Assert.assertEquals(deleted, "true","42.删除学习项目接口" + deleted);
 	}
 	
 	@Test(description="43.删除岗位认证接口", priority=43)
 	public void testDeleteQualifications()  {
 		String res = ScoringByQuestionBusiness.DeleteQualifications(qualifications_id);
 		String msg = (String) JSONPath.read(res, "$.msg");
 		System.out.println("43.删除岗位认证接口:"+"msg="+msg);
 		Assert.assertEquals(msg, "能力认证删除成功","43.删除岗位认证接口" + res);
 	}
 	
	

}
