package group.cases;

import cn.kxy.authentication.business.PositionAuthentication;
import cn.kxy.group.a.business.ScoringByQuestionBusiness;
import cn.kxy.setting.bussiness.ClassificationBusines;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import com.lazy.common.utils.CommonData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class TestProportional {	
	public static String proportional_questionBank_id = "";
	public static String proportional_questionBank_id1 = "";
	public static String proportional_questionBank_id2 = "";
	public static String certificate_id = "";
	public static String proportional_examPlan_id = "";
	public static String lowProportional_examPlan_id = "";
	public static String zeroProportional_examPlan_id = "";
	public static Integer proportional_question_count = null;
	public static Integer lowProportional_question_count = null;
	public static Integer zeroProportional_question_count = null;
	public static String proportional_studyPlan_id = "";
	public static String lowProportional_studyPlan_id = "";
	public static String zeroProportional_studyPlan_id = "";
	public static String proportional_studyPlan_exam_id = "";
	public static String lowProportional_studyPlan_exam_id = "";
	public static String zeroProportional_studyPlan_exam_id = "";
	public static String proportional_projectCourse_id = "";
	public static String proportional_project_id = "";
	public static String lowProportional_projectCourse_id = "";
	public static String lowProportional_project_id = "";
	public static String zeroProportional_projectCourse_id = "";
	public static String zeroProportional_project_id = "";
	public static String proportional_studyProject_exam_id = "";
	public static String lowProportional_studyProject_exam_id = "";
	public static String zeroProportional_studyProject_exam_id = "";
	public static String proportional_qualifications_id = "";
	public static String lowProportional_qualifications_id = "";
	public static String zeroProportional_qualifications_id = "";
	public static String proportional_qualifications_exam_id = "";
	public static String lowProportional_qualifications_exam_id = "";
	public static String zeroProportional_qualifications_exam_id = "";
	
	String proportional_exam_title = "按权重抽题" + CommonData.getStringRandom(5);
	String proportional_studyPlan_title = "按权重抽题" + CommonData.getStringRandom(5);
	String proportional_title_project = "按权重抽题" + CommonData.getStringRandom(5);
	String classification_id = ClassificationBusines.getPrimaryId();
	String proportional_title_qualifications = "按权重抽题" + CommonData.getStringRandom(5);
	String singleCount = "10";//按权重抽题抽取的单选题题数,任务下共14道题

 	
 	@Test(description="1.查询题库id-权重抽题接口", priority=1)
 	public void testResourceClassifyAddProportional()  {
 		String res = ScoringByQuestionBusiness.QueryQuestionBankList("ProportionalBank");
 		System.out.println("1.查询题库id-权重抽题接口:");
 		proportional_questionBank_id1 =  (String)JSONPath.read(res, "$.data.list[0].id");
 		proportional_questionBank_id2 =  (String)JSONPath.read(res, "$.data.list[1].id");
 		proportional_questionBank_id = proportional_questionBank_id1 + "," + proportional_questionBank_id2;
 		System.out.println("proportional_questionBank_id="+proportional_questionBank_id);
		Assert.assertNotEquals(proportional_questionBank_id,null,"1.查询题库id-权重抽题接口"+res);
 	}
 			
 	
 	@Test(description="2.获取证书列表接口", priority=2)
 	public void testCertificateGetList()  {
 		String res = ScoringByQuestionBusiness.CertificateGetList("Coo_cert");
 		certificate_id = (String) JSONPath.read(res, "$.list[0].id");
 		Integer total = (Integer) JSONPath.read(res, "$.total");
 		System.out.println("2.获取证书列表接口:"+"certificate_id="+certificate_id);
 		Assert.assertNotEquals(total, 0,"2.获取证书列表接口" + res);
 	}
 	
 		
 	@Test(description="3.创建考试任务-按权重抽题，各题库够抽接口", priority=3)
 	public void testExamAddProportional()  throws Exception{
 		SimpleDateFormat date_temp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
 		String beginTime = date_temp.format(new Date());
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, 7);
		String endTime = date_temp.format(calendar.getTime());
		Integer proportion1 = 20;
		Integer proportion2 = 80;
		String proportionalTemp = "[{\"proportion\":"+proportion1+",\"name\":\"ProportionalBank2\","
				+ "\"id\":"+proportional_questionBank_id1+"},{\"proportion\":"+proportion2+",\"name\":\"ProportionalBank1\","
						+ "\"id\":\""+proportional_questionBank_id2+"\"}]";		
 		String proportional = JSONObject.parseArray(proportionalTemp).toJSONString();
 		String res = ScoringByQuestionBusiness.ExamAdd(beginTime,endTime,"1","2","12","1","10",proportional_questionBank_id,"2",
 				"1","2","5",singleCount,"5",proportional_exam_title,"1","10",URLEncoder.encode(proportional, "UTF-8"));
 		System.out.println("3.创建考试任务-按权重抽题，各题库够抽接口:");
 		proportional_examPlan_id = (String)JSONPath.read(res, "$.data");
 		String msg = (String)JSONPath.read(res, "$.msg");
 		System.out.println("proportional_exam_plan_id="+proportional_examPlan_id+","+"msg="+msg); 		
		Assert.assertEquals(msg,"新增考试成功！","3.创建考试任务-按权重抽题，各题库够抽接口"+res);
 	}
 	
 	
 	@Test(description="4.创建考试任务-按权重抽题-小题库权重大不够抽接口", priority=4)
 	public void testExamAddLowProportional()  throws Exception{
 		SimpleDateFormat date_temp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
 		String beginTime = date_temp.format(new Date());
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, 7);
		String endTime = date_temp.format(calendar.getTime());	
		String title = proportional_exam_title + "-小题库权重大";
		Integer proportion1 = 80;
		Integer proportion2 = 20;
		String proportionalTemp = "[{\"proportion\":"+proportion1+",\"name\":\"ProportionalBank2\","
				+ "\"id\":"+proportional_questionBank_id1+"},{\"proportion\":"+proportion2+",\"name\":\"ProportionalBank1\","
						+ "\"id\":\""+proportional_questionBank_id2+"\"}]";		
 		String proportional = JSONObject.parseArray(proportionalTemp).toJSONString();
 		String res = ScoringByQuestionBusiness.ExamAdd(beginTime,endTime,"1","2","12","1","10",proportional_questionBank_id,"2",
 				"1","2","5",singleCount,"5",title,"1","10",URLEncoder.encode(proportional, "UTF-8"));
 		System.out.println("4.创建考试任务-按权重抽题-小题库权重大不够抽接口:");
 		lowProportional_examPlan_id = (String)JSONPath.read(res, "$.data");
 		String msg = (String)JSONPath.read(res, "$.msg");
 		System.out.println("lowProportional_examPlan_id="+lowProportional_examPlan_id+","+"msg="+msg); 		
		Assert.assertEquals(msg,"新增考试成功！","4.创建考试任务-按权重抽题-小题库权重大不够抽接口"+res);
 	}
 	
 	
 	@Test(description="5.创建考试任务-按权重抽题-存在0权重题库，非0权重的题库不够抽接口", priority=5)
 	public void testExamAddLowZeroProportional()  throws Exception{
 		SimpleDateFormat date_temp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
 		String beginTime = date_temp.format(new Date());
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, 7);
		String endTime = date_temp.format(calendar.getTime());
		String title = proportional_exam_title + "-权重存在0";
		Integer proportion1 = 20;
		Integer proportion2 = 0;
		String proportionalTemp = "[{\"proportion\":"+proportion1+",\"name\":\"ProportionalBank2\","
				+ "\"id\":"+proportional_questionBank_id1+"},{\"proportion\":"+proportion2+",\"name\":\"ProportionalBank1\","
						+ "\"id\":\""+proportional_questionBank_id2+"\"}]";		
 		String proportional = JSONObject.parseArray(proportionalTemp).toJSONString();
 		String res = ScoringByQuestionBusiness.ExamAdd(beginTime,endTime,"1","2","12","1","10",proportional_questionBank_id,"2",
 				"1","2","5",singleCount,"5",title,"1","10",URLEncoder.encode(proportional, "UTF-8"));
 		System.out.println("5.创建考试任务-按权重抽题-存在0权重题库，非0权重的题库不够抽接口:");
 		zeroProportional_examPlan_id = (String)JSONPath.read(res, "$.data");
 		String msg = (String)JSONPath.read(res, "$.msg");
 		System.out.println("lowProportional_examPlan_id="+lowProportional_examPlan_id+","+"msg="+msg); 		
		Assert.assertEquals(msg,"新增考试成功！","5.创建考试任务-按权重抽题-存在0权重题库，非0权重的题库不够抽接口"+res);
 	}
  		
 	
 	@Test(description="6.创建有考试的学习任务-按权重抽题，各题库够抽接口", priority=6)
 	public void testStudyPlanAddProportional() throws UnsupportedEncodingException  {
 		String fillBlankUnitScore = "15";
 		String shortAnswerUnitScore = "12";
 		Integer proportion1 = 20;
 		Integer proportion2 = 80;
 		String proportionalTemp = "[{\"proportion\":"+proportion1+",\"name\":\"ProportionalBank2\","
				+ "\"id\":"+proportional_questionBank_id1+"},{\"proportion\":"+proportion2+",\"name\":\"ProportionalBank1\","
						+ "\"id\":\""+proportional_questionBank_id2+"\"}]";		
 		String proportional = JSONObject.parseArray(proportionalTemp).toJSONString();
 		String res = ScoringByQuestionBusiness.StudyPlanAdd(proportional_studyPlan_title,proportional_questionBank_id,
 				"1","10","1",fillBlankUnitScore,"2","1",shortAnswerUnitScore,"2",singleCount,"5","1","10",proportional);		
 		System.out.println("6.创建有考试的学习任务-按权重抽题，各题库够抽接口:");
 		proportional_studyPlan_id = (String)JSONPath.read(res, "$.data");
 		String msg = (String)JSONPath.read(res, "$.msg");
 		System.out.println("msg="+msg+","+"proportional_studyPlan_id="+proportional_studyPlan_id);
 		Assert.assertEquals(msg,"新增计划成功！","6.创建有考试的学习任务-按权重抽题，各题库够抽接口"+res);
 	}
 	
 	
 	@Test(description="7.创建有考试的学习任务-按权重抽题-小题库权重大不够抽接口", priority=7)
 	public void testStudyPlanAdlowProportional() throws UnsupportedEncodingException  {
 		String fillBlankUnitScore = "15";
 		String shortAnswerUnitScore = "12";
 		Integer proportion1 = 80;
 		Integer proportion2 = 20;
 		String title = proportional_studyPlan_title + "-小题库权重大";
 		String proportionalTemp = "[{\"proportion\":"+proportion1+",\"name\":\"ProportionalBank2\","
				+ "\"id\":"+proportional_questionBank_id1+"},{\"proportion\":"+proportion2+",\"name\":\"ProportionalBank1\","
						+ "\"id\":\""+proportional_questionBank_id2+"\"}]";		
 		String proportional = JSONObject.parseArray(proportionalTemp).toJSONString();
 		String res = ScoringByQuestionBusiness.StudyPlanAdd(title,proportional_questionBank_id,
 				"1","10","1",fillBlankUnitScore,"2","1",shortAnswerUnitScore,"2",singleCount,"5","1","10",proportional);
 		System.out.println("7.创建有考试的学习任务-按权重抽题-小题库权重大不够抽接口:");
 		lowProportional_studyPlan_id = (String)JSONPath.read(res, "$.data");
 		String msg = (String)JSONPath.read(res, "$.msg");
 		System.out.println("msg="+msg+","+"lowProportional_studyPlan_id="+lowProportional_studyPlan_id);
 		Assert.assertEquals(msg,"新增计划成功！","7.创建有考试的学习任务-按权重抽题-小题库权重大不够抽接口"+res);
 	}
 	
 	
 	
 	@Test(description="8.创建有考试的学习任务-按权重抽题-存在0权重题库，非0权重的题库不够抽接口", priority=8)
 	public void testStudyPlanAdzeroProportional() throws UnsupportedEncodingException  {
 		String fillBlankUnitScore = "15";
 		String shortAnswerUnitScore = "12";
 		Integer proportion1 = 80;
 		Integer proportion2 = 0;
 		String title = proportional_studyPlan_title + "-权重存在0";
 		String proportionalTemp = "[{\"proportion\":"+proportion1+",\"name\":\"ProportionalBank2\","
				+ "\"id\":"+proportional_questionBank_id1+"},{\"proportion\":"+proportion2+",\"name\":\"ProportionalBank1\","
						+ "\"id\":\""+proportional_questionBank_id2+"\"}]";		
 		String proportional = JSONObject.parseArray(proportionalTemp).toJSONString();
 		String res = ScoringByQuestionBusiness.StudyPlanAdd(title,proportional_questionBank_id,
 				"1","10","1",fillBlankUnitScore,"2","1",shortAnswerUnitScore,"2",singleCount,"5","1","10",proportional);
 		System.out.println("8.创建有考试的学习任务-按权重抽题-存在0权重题库，非0权重的题库不够抽接口:");
 		zeroProportional_studyPlan_id = (String)JSONPath.read(res, "$.data");
 		String msg = (String)JSONPath.read(res, "$.msg");
 		System.out.println("msg="+msg+","+"zeroProportional_studyPlan_id="+zeroProportional_studyPlan_id);
 		Assert.assertEquals(msg,"新增计划成功！","8.创建有考试的学习任务-按权重抽题-存在0权重题库，非0权重的题库不够抽接口"+res);
 	}
 	
 	
 	@Test(description="9.获取权重抽题学习任务里的考试接口", priority=9)
 	public void testStudyPlanExamIdProportional()  {
 		String res_proportional = ScoringByQuestionBusiness.StudyPlanExamId(proportional_studyPlan_id);
 		String res_lowProportional = ScoringByQuestionBusiness.StudyPlanExamId(lowProportional_studyPlan_id);
 		String res_zeroProportional = ScoringByQuestionBusiness.StudyPlanExamId(zeroProportional_studyPlan_id);
 		System.out.println("9.获取权重抽题学习任务里的考试接口:");
 		proportional_studyPlan_exam_id = (String)JSONPath.read(res_proportional, "$.data.courseInfo[0].examId");
 		lowProportional_studyPlan_exam_id = (String)JSONPath.read(res_lowProportional, "$.data.courseInfo[0].examId");
 		zeroProportional_studyPlan_exam_id = (String)JSONPath.read(res_zeroProportional, "$.data.courseInfo[0].examId");
 		String msg = (String)JSONPath.read(res_proportional, "$.msg");
 		System.out.println("msg="+msg+","+"proportional_studyPlan_exam_id="+proportional_studyPlan_exam_id+","+
 		"lowProportional_studyPlan_exam_id="+lowProportional_studyPlan_exam_id+","+"zeroProportional_studyPlan_exam_id="
 				+zeroProportional_studyPlan_exam_id);
 		Assert.assertEquals(msg,"success","9.获取权重抽题学习任务里的考试接口"+res_proportional);
 	}
 	
 	
 	@Test(description="10.创建学习项目-按权重抽题，各题库够抽接口", priority=10)
 	public void testStudyProjectSaveProportional()  {
 		String cover = "https://oss.coolcollege.cn/1810048818682974377.png";
        String base_cover= "https://oss.coolcollege.cn/1810048818682974377.png";
 		String resFirst = ScoringByQuestionBusiness.StudyProjectSaveBaseInfo(proportional_title_project,classification_id,cover,
 				base_cover);
 		proportional_project_id = (String) JSONPath.read(resFirst, "$.id"); 
 		proportional_projectCourse_id = (String) JSONPath.read(resFirst, "$.course_id"); 
 		String fill_blank_unit_score = "15";
 		String short_answer_unit_score = "12";
 		Integer proportion1 = 20;
 		Integer proportion2 = 80;
 		String proportionalTemp = "[{\"proportion\":"+proportion1+",\"name\":\"ProportionalBank2\","
				+ "\"id\":"+proportional_questionBank_id1+"},{\"proportion\":"+proportion2+",\"name\":\"ProportionalBank1\","
						+ "\"id\":\""+proportional_questionBank_id2+"\"}]";		
 		String proportional = JSONObject.parseArray(proportionalTemp).toJSONString();			
 		String resSecond = ScoringByQuestionBusiness.StudyProjectSaveStageContent(proportional_project_id,proportional_questionBank_id,"2","1","10","1",
 				fill_blank_unit_score,"2","1",short_answer_unit_score,"2",singleCount,"5","1","10",proportional);		
 		String resThird = ScoringByQuestionBusiness.StudyProjectSaveSettings(proportional_project_id);
 		System.out.println("resSecond="+resSecond+","+"resThird="+resThird);
 		String result = (String) JSONPath.read(resThird, "$.result");		
 		System.out.println("10.创建学习项目-按权重抽题，各题库够抽接口:"+"proportional_project_id="+proportional_project_id
 				+","+"proportional_projectCourse_id="+proportional_projectCourse_id);
 		Assert.assertEquals(result, "true","10.创建学习项目-按权重抽题，各题库够抽接口" + resThird);
 	}
 	
 	
 	@Test(description="11.创建学习项目-按权重抽题-小题库权重大不够抽接口", priority=11)
 	public void testStudyProjectSavelowProportional()  {
 		String cover = "https://oss.coolcollege.cn/1810048818682974377.png";
        String base_cover= "https://oss.coolcollege.cn/1810048818682974377.png";
        String title = proportional_studyPlan_title + "-小题库权重大";
 		String resFirst = ScoringByQuestionBusiness.StudyProjectSaveBaseInfo(title,classification_id,cover,base_cover);
 		lowProportional_project_id = (String) JSONPath.read(resFirst, "$.id"); 
 		lowProportional_projectCourse_id = (String) JSONPath.read(resFirst, "$.course_id"); 
 		String fill_blank_unit_score = "15";
 		String short_answer_unit_score = "12";
 		Integer proportion1 = 80;
 		Integer proportion2 = 20;
 		String proportionalTemp = "[{\"proportion\":"+proportion1+",\"name\":\"ProportionalBank2\","
				+ "\"id\":"+proportional_questionBank_id1+"},{\"proportion\":"+proportion2+",\"name\":\"ProportionalBank1\","
						+ "\"id\":\""+proportional_questionBank_id2+"\"}]";		
 		String proportional = JSONObject.parseArray(proportionalTemp).toJSONString();
 		String resSecond = ScoringByQuestionBusiness.StudyProjectSaveStageContent(lowProportional_project_id,proportional_questionBank_id,"2","1","10","1",
 				fill_blank_unit_score,"2","1",short_answer_unit_score,"2",singleCount,"5","1","10",proportional);		
 		String resThird = ScoringByQuestionBusiness.StudyProjectSaveSettings(lowProportional_project_id);
 		String result = (String) JSONPath.read(resThird, "$.result");		
 		System.out.println("11.创建学习项目-按权重抽题-小题库权重大不够抽接口:"+"lowProportional_project_id="+lowProportional_project_id+","
 		+"lowProportional_projectCourse_id="+lowProportional_projectCourse_id);
 		Assert.assertEquals(result, "true","11.创建学习项目-按权重抽题-小题库权重大不够抽接口" + resThird);
 	}
 	
 	
 	@Test(description="12.创建学习项目-按权重抽题-存在0权重题库，非0权重的题库不够抽接口", priority=12)
 	public void testStudyProjectSavezeroProportional()  {
 		String cover = "https://oss.coolcollege.cn/1810048818682974377.png";
        String base_cover= "https://oss.coolcollege.cn/1810048818682974377.png";
        String title = proportional_studyPlan_title + "-权重存在0";
 		String resFirst = ScoringByQuestionBusiness.StudyProjectSaveBaseInfo(title,classification_id,cover,base_cover);
 		zeroProportional_project_id = (String) JSONPath.read(resFirst, "$.id"); 
 		zeroProportional_projectCourse_id = (String) JSONPath.read(resFirst, "$.course_id"); 
 		String fill_blank_unit_score = "15";
 		String short_answer_unit_score = "12";
 		Integer proportion1 = 80;
 		Integer proportion2 = 0;
 		String proportionalTemp = "[{\"proportion\":"+proportion1+",\"name\":\"ProportionalBank2\","
				+ "\"id\":"+proportional_questionBank_id1+"},{\"proportion\":"+proportion2+",\"name\":\"ProportionalBank1\","
						+ "\"id\":\""+proportional_questionBank_id2+"\"}]";		
 		String proportional = JSONObject.parseArray(proportionalTemp).toJSONString();
 		String resSecond = ScoringByQuestionBusiness.StudyProjectSaveStageContent(zeroProportional_project_id,proportional_questionBank_id,"2","1","10","1",
 				fill_blank_unit_score,"2","1",short_answer_unit_score,"2",singleCount,"5","1","10",proportional);		
 		String resThird = ScoringByQuestionBusiness.StudyProjectSaveSettings(zeroProportional_project_id);
 		String result = (String) JSONPath.read(resThird, "$.result");		
 		System.out.println("12.创建学习项目-按权重抽题-存在0权重题库，非0权重的题库不够抽接口:"+"zeroProportional_project_id="+
 		zeroProportional_project_id+","+"zeroProportional_projectCourse_id="+zeroProportional_projectCourse_id);
 		Assert.assertEquals(result, "true","12.创建学习项目-按权重抽题-存在0权重题库，非0权重的题库不够抽接口" + resThird);
 	}
 	
 	
 	@Test(description="13.获取权重抽题学习项目里的考试接口", priority=13)
 	public void testStudyProjectsProportional()  {
 		String res_proportional = ScoringByQuestionBusiness.StudyProjectsDetail(proportional_projectCourse_id);
 		String res_lowProportional = ScoringByQuestionBusiness.StudyProjectsDetail(lowProportional_projectCourse_id);
 		String res_zeroProportional = ScoringByQuestionBusiness.StudyProjectsDetail(zeroProportional_projectCourse_id);
 		proportional_studyProject_exam_id = (String) JSONPath.read(res_proportional, "$.stages[0].resources[0].exam.id");
 		lowProportional_studyProject_exam_id = (String) JSONPath.read(res_lowProportional, "$.stages[0].resources[0].exam.id");
 		zeroProportional_studyProject_exam_id = (String) JSONPath.read(res_zeroProportional, "$.stages[0].resources[0].exam.id");
 		System.out.println("13.获取权重抽题学习项目里的考试接口:"+"proportional_studyProject_exam_id="+proportional_studyProject_exam_id+","+
 		"lowProportional_studyProject_exam_id="+lowProportional_studyProject_exam_id+","+"zeroProportional_studyProject_exam_id="+
 				zeroProportional_studyProject_exam_id);
// 		Assert.assertNotEquals(proportional_studyProject_exam_id,null,"13.获取权重抽题学习项目里的考试接口" + res_proportional);
 	}
 	
 	
 	@Test(description="14.新建岗位认证-按权重抽题，各题库够抽接口", priority=14)
 	public void testQualificationsAddproportional() {
		PositionAuthentication positionAuthentication = new PositionAuthentication();
		String proportion1 = "20";
		String proportion2 = "80";

		JSONArray stageJsonList = new JSONArray();
		JSONObject randomPaperObject = positionAuthentication.createRandomPaperStageInAuthentication(1, proportion1, proportion2);
		stageJsonList.add(randomPaperObject);

		String authenticationTitle = "Automation" + CommonData.getStringRandom(3);
		String res = positionAuthentication.createAuthentication(authenticationTitle, stageJsonList);
 		String msg = (String) JSONPath.read(res, "$.msg");
 		proportional_qualifications_id = (String) JSONPath.read(res, "$.data");
 		System.out.println("14.新建岗位认证-按权重抽题，各题库够抽接口:"+"msg="+msg+","+"proportional_qualifications_id="+ proportional_qualifications_id);
 		Assert.assertEquals(msg, "新增计划成功！","14.新建岗位认证-按权重抽题，各题库够抽接口" + res);
 	}
 	
 	
 	@Test(description="15.新建岗位认证-按权重抽题-小题库权重大不够抽接口", priority=15)
 	public void testQualificationsAddlowproportional() throws UnsupportedEncodingException  {
		PositionAuthentication positionAuthentication = new PositionAuthentication();
		String proportion1 = "80";
		String proportion2 = "20";

		JSONArray stageJsonList = new JSONArray();
		JSONObject randomPaperObject = positionAuthentication.createRandomPaperStageInAuthentication(1, proportion1, proportion2);
		stageJsonList.add(randomPaperObject);

		String authenticationTitle = "Automation" + CommonData.getStringRandom(3);
		String res = positionAuthentication.createAuthentication(authenticationTitle, stageJsonList);
		String msg = (String) JSONPath.read(res, "$.msg");
 		lowProportional_qualifications_id = (String) JSONPath.read(res, "$.data");
 		System.out.println("15.新建岗位认证-按权重抽题-小题库权重大不够抽接口:"+"msg="+msg+","+"lowProportional_qualifications_id="+lowProportional_qualifications_id);
 		Assert.assertEquals(msg, "新增计划成功！","15.新建岗位认证-按权重抽题-小题库权重大不够抽接口" + res);
 	}
 	
 	
 	@Test(description="16.新建岗位认证-按权重抽题-存在0权重题库，非0权重的题库不够抽接口", priority=16)
 	public void testQualificationsAddzeroproportional() throws UnsupportedEncodingException  {
		PositionAuthentication positionAuthentication = new PositionAuthentication();
		String proportion1 = "80";
		String proportion2 = "0";

		JSONArray stageJsonList = new JSONArray();
		JSONObject randomPaperObject = positionAuthentication.createRandomPaperStageInAuthentication(1, proportion1, proportion2);
		stageJsonList.add(randomPaperObject);

		String authenticationTitle = "Automation" + CommonData.getStringRandom(3);
		String res = positionAuthentication.createAuthentication(authenticationTitle, stageJsonList);
		String msg = (String) JSONPath.read(res, "$.msg");
 		zeroProportional_qualifications_id = (String) JSONPath.read(res, "$.data");
 		System.out.println("16.新建岗位认证-按权重抽题-存在0权重题库，非0权重的题库不够抽接口:"+"msg="+msg+","+"zeroProportional_qualifications_id="
 		+zeroProportional_qualifications_id);
 		Assert.assertEquals(msg, "新增计划成功！","16.新建岗位认证-按权重抽题-存在0权重题库，非0权重的题库不够抽接口" + res);
 	}
 	
 	
 	@Test(description="17.校验考试任务按权重抽取的题库下试题数量+校验试题是否重复接口", priority=17)
 	public void testQueryQuestionExamPlan()  {
 		//抽取的题库下试题总数
 	    Integer count = Integer.valueOf(singleCount) + 4;//一道多选题，一道判断题，一道填空题，一道简答题，除单选其他题共4道
 	    
 		String res_proportional = ScoringByQuestionBusiness.QueryQuestionId(proportional_examPlan_id);
 		String res_lowProportional = ScoringByQuestionBusiness.QueryQuestionId(lowProportional_examPlan_id);
 		String res_zeroProportional = ScoringByQuestionBusiness.QueryQuestionId(zeroProportional_examPlan_id);
 		System.out.println("17.校验考试任务按权重抽取的题库下试题数量+校验试题是否重复接口:");
 		proportional_question_count = (Integer)JSONPath.read(res_proportional, "$.questions[-1].order_id");
 		lowProportional_question_count = (Integer)JSONPath.read(res_lowProportional, "$.questions[-1].order_id");
 		zeroProportional_question_count = (Integer)JSONPath.read(res_zeroProportional, "$.questions[-1].order_id"); 		
 		List<String> idList = new ArrayList(); 		
 		JSONArray parseArray = (JSONArray) JSONPath.read(res_proportional, "$.questions");		
 		for(Object obj  :parseArray) {
 			JSONObject jsonObj  = (JSONObject)obj;
 			String idStr = jsonObj.getString("id"); 			
 			idList.add(idStr);
 		}	
 		//判断是否存在重复的试题id
 		long noReapeatCount = idList.stream().distinct().count();
 		boolean repeat = noReapeatCount < idList.size();
 		if(repeat) {
 			System.err.println("存在重复试题");
 			return;
 		}		 		
 		System.out.println("examPlan_proportional_question_count="+proportional_question_count+","+"examPlan_lowProportional_question_count="
 		+lowProportional_question_count+","+"examPlan_zeroProportional_question_count="+zeroProportional_question_count); 
 		Assert.assertEquals(proportional_question_count,count,"17.校验考试任务按权重抽取的题库下试题数量+校验试题是否重复接口"+res_proportional);
 		Assert.assertEquals(lowProportional_question_count,count,"17.校验考试任务按权重抽取的题库下试题数量+校验试题是否重复接口"+res_proportional);
 		Assert.assertEquals(zeroProportional_question_count,count,"17.校验考试任务按权重抽取的题库下试题数量+校验试题是否重复接口"+res_proportional);
 	}
 	
 	
 	@Test(description="18.校验学习任务按权重抽取的题库下试题数量+校验试题是否重复接口", priority=18)
 	public void testQueryQuestionStudyProject()  {
 	    Integer count = Integer.valueOf(singleCount) + 4;	    
 		String res_proportional = ScoringByQuestionBusiness.QueryQuestionId(proportional_studyPlan_exam_id);
 		String res_lowProportional = ScoringByQuestionBusiness.QueryQuestionId(lowProportional_studyPlan_exam_id);
 		String res_zeroProportional = ScoringByQuestionBusiness.QueryQuestionId(zeroProportional_studyPlan_exam_id);
 		System.out.println("18.校验学习项目按权重抽取的题库下试题数量+校验试题是否重复接口:");
 		proportional_question_count = (Integer)JSONPath.read(res_proportional, "$.questions[-1].order_id");
 		lowProportional_question_count = (Integer)JSONPath.read(res_lowProportional, "$.questions[-1].order_id");
 		zeroProportional_question_count = (Integer)JSONPath.read(res_zeroProportional, "$.questions[-1].order_id"); 		
 		List<String> idList = new ArrayList(); 		
 		JSONArray parseArray = (JSONArray) JSONPath.read(res_proportional, "$.questions");		
 		for(Object obj  :parseArray) {
 			JSONObject jsonObj  = (JSONObject)obj;
 			String idStr = jsonObj.getString("id"); 			
 			idList.add(idStr);
 		}	
 		long noReapeatCount = idList.stream().distinct().count();
 		boolean repeat = noReapeatCount < idList.size();
 		if(repeat) {
 			System.err.println("存在重复试题");
 			return;
 		}		 		
 		System.out.println("studyPlan_proportional_question_count="+proportional_question_count+","+
 		"studyPlan_lowProportional_question_count="+lowProportional_question_count+","+
 				"studyPlan_zeroProportional_question_count="+zeroProportional_question_count); 
 		Assert.assertEquals(proportional_question_count,count,"18.校验学习项目按权重抽取的题库下试题数量+校验试题是否重复接口"+res_proportional);
 		Assert.assertEquals(lowProportional_question_count,count,"18.校验学习项目按权重抽取的题库下试题数量+校验试题是否重复接口"+res_proportional);
 		Assert.assertEquals(zeroProportional_question_count,count,"18.校验学习项目按权重抽取的题库下试题数量+校验试题是否重复接口"+res_proportional);
 	}
 	
 	
 	
 	@Test(description="19.学习项目获取试题前start接口", priority=19)
 	public void testStudyProjectsBeforeStart()  {
 		String res_proportional = ScoringByQuestionBusiness.StudyProjectsStart(proportional_projectCourse_id);
 		String res_lowProportional = ScoringByQuestionBusiness.StudyProjectsStart(lowProportional_projectCourse_id);
 		String res_zeroProportional = ScoringByQuestionBusiness.StudyProjectsStart(zeroProportional_projectCourse_id);
 		String result = (String) JSONPath.read(res_proportional, "$.result");
 		System.out.println("19.学习项目获取试题前start接口:"+"result="+result);
 		Assert.assertEquals(result,"true","19.学习项目获取试题前start接口" + res_proportional);
 	}
 	
 	
 	@Test(description="20.校验学习项目按权重抽取的题库下试题数量+校验试题是否重复接口", priority=20)
 	public void testQueryQuestionStudyPlan()  {
 	    Integer count = Integer.valueOf(singleCount) + 4;	    
 		String res_proportional = ScoringByQuestionBusiness.QueryQuestionId(proportional_studyProject_exam_id);
 		String res_lowProportional = ScoringByQuestionBusiness.QueryQuestionId(lowProportional_studyProject_exam_id);
 		String res_zeroProportional = ScoringByQuestionBusiness.QueryQuestionId(zeroProportional_studyProject_exam_id);
 		System.out.println("20.校验学习项目按权重抽取的题库下试题数量+校验试题是否重复接口:");
 		proportional_question_count = (Integer)JSONPath.read(res_proportional, "$.questions[-1].order_id");
 		lowProportional_question_count = (Integer)JSONPath.read(res_lowProportional, "$.questions[-1].order_id");
 		zeroProportional_question_count = (Integer)JSONPath.read(res_zeroProportional, "$.questions[-1].order_id"); 
 		System.out.println("res_proportional="+res_proportional+","+"proportional_question_count="+proportional_question_count);
 		List<String> idList = new ArrayList(); 		
 		JSONArray parseArray = (JSONArray) JSONPath.read(res_proportional, "$.questions");		
 		for(Object obj  :parseArray) {
 			JSONObject jsonObj  = (JSONObject)obj;
 			String idStr = jsonObj.getString("id"); 			
 			idList.add(idStr);
 		}	
 		long noReapeatCount = idList.stream().distinct().count();
 		boolean repeat = noReapeatCount < idList.size();
 		if(repeat) {
 			System.err.println("存在重复试题");
 			return;
 		}		 		
 		System.out.println("studyPlan_proportional_question_count="+proportional_question_count+","+
 		"studyPlan_lowProportional_question_count="+lowProportional_question_count+","+
 				"studyPlan_zeroProportional_question_count="+zeroProportional_question_count); 
 		Assert.assertEquals(proportional_question_count,count,"20.校验学习项目按权重抽取的题库下试题数量+校验试题是否重复接口"+res_proportional);
 		Assert.assertEquals(lowProportional_question_count,count,"20.校验学习项目按权重抽取的题库下试题数量+校验试题是否重复接口"+res_proportional);
 		Assert.assertEquals(zeroProportional_question_count,count,"20.校验学习项目按权重抽取的题库下试题数量+校验试题是否重复接口"+res_proportional);
 	}
 	
 	
 	@Test(description="21.获取按权重抽题里岗位认证的考试id接口", priority=21)
 	public void testQualificationsExam()  {
 		String res_proportional = ScoringByQuestionBusiness.QualificationsExamId(proportional_qualifications_id);
 		String res_lowProportional = ScoringByQuestionBusiness.QualificationsExamId(lowProportional_qualifications_id);
 		String res_zeroProportional = ScoringByQuestionBusiness.QualificationsExamId(zeroProportional_qualifications_id);
 		proportional_qualifications_exam_id = (String) JSONPath.read(res_proportional, "$.stage_list[0].course_mapping_list[0].id");
 		lowProportional_qualifications_exam_id = (String) JSONPath.read(res_lowProportional, "$.stage_list[0].course_mapping_list[0].id");
 		zeroProportional_qualifications_exam_id = (String) JSONPath.read(res_zeroProportional, "$.stage_list[0].course_mapping_list[0].id");
 		System.out.println("21.获取岗位认证-考试id接口:"+"proportional_qualifications_exam_id="+proportional_qualifications_exam_id+","+
 		"lowProportional_qualifications_exam_id="+lowProportional_qualifications_exam_id+","+"zeroProportional_qualifications_exam_id="+
 		zeroProportional_qualifications_exam_id);
 		Assert.assertNotEquals(proportional_qualifications_exam_id,null,"21.获取岗位认证-考试id接口" + res_proportional);
 	}
 	
 	
 	@Test(description="22.权重抽题中岗位认证答题前查询接口", priority=22)
 	public void testQueryExamsProportional()  {
 		Integer count = Integer.valueOf(singleCount) + 4;	
 		String res_proportional = ScoringByQuestionBusiness.QueryExams(proportional_qualifications_exam_id);
 		String res_lowProportional = ScoringByQuestionBusiness.QueryExams(lowProportional_qualifications_exam_id);
 		String res_zeroProportional = ScoringByQuestionBusiness.QueryExams(zeroProportional_qualifications_exam_id);
 		System.out.println("22.权重抽题中岗位认证答题前查询接口:");
 		proportional_question_count = (Integer)JSONPath.read(res_proportional, "$.questions[-1].order_id");
 		lowProportional_question_count = (Integer)JSONPath.read(res_lowProportional, "$.questions[-1].order_id");
 		zeroProportional_question_count = (Integer)JSONPath.read(res_zeroProportional, "$.questions[-1].order_id"); 
 		System.out.println("res_proportional="+res_proportional+","+"proportional_question_count="+proportional_question_count);
 		List<String> idList = new ArrayList(); 		
 		JSONArray parseArray = (JSONArray) JSONPath.read(res_proportional, "$.questions");		
 		for(Object obj  :parseArray) {
 			JSONObject jsonObj  = (JSONObject)obj;
 			String idStr = jsonObj.getString("id"); 			
 			idList.add(idStr);
 		}	
 		long noReapeatCount = idList.stream().distinct().count();
 		boolean repeat = noReapeatCount < idList.size();
 		if(repeat) {
 			System.err.println("存在重复试题");
 			return;
 		}		 		
 		System.out.println("qualifications_proportional_question_count="+proportional_question_count+","+
 		"qualifications_lowProportional_question_count="+lowProportional_question_count+","+
 				"qualifications_zeroProportional_question_count="+zeroProportional_question_count); 
 		Assert.assertEquals(proportional_question_count,count,"22.校验岗位认证按权重抽取的题库下试题数量+校验试题是否重复接口"+res_proportional);
 		Assert.assertEquals(lowProportional_question_count,count,"22.校验岗位认证按权重抽取的题库下试题数量+校验试题是否重复接口"+res_proportional);
 		Assert.assertEquals(zeroProportional_question_count,count,"22.校验岗位认证按权重抽取的题库下试题数量+校验试题是否重复接口"+res_proportional);
 	}

 		 	
 	@Test(description="23.删除考试任务接口", priority=23)
 	public void testDelateExam()  {
 		String res_proportional = ScoringByQuestionBusiness.DelateExam(proportional_examPlan_id); 
 		String res_lowProportional = ScoringByQuestionBusiness.DelateExam(lowProportional_examPlan_id); 
 		String res_zeroProportional = ScoringByQuestionBusiness.DelateExam(zeroProportional_examPlan_id); 
 		System.out.println("23.删除考试任务接口接口:");
 		String msg = (String)JSONPath.read(res_proportional, "$.msg");
 		System.out.println("msg="+msg);
		Assert.assertEquals(msg,"删除任务成功","23.删除考试任务接口接口"+res_proportional);
 	}
 	
 	
 	@Test(description="24.删除学习任务接口", priority=24)
 	public void testDelateStudyPlan()  {
 		String res_proportional = ScoringByQuestionBusiness.DelateStudyPlan(proportional_studyPlan_id); 
 		String res_lowProportional = ScoringByQuestionBusiness.DelateStudyPlan(lowProportional_studyPlan_id); 
 		String res_zeroProportional = ScoringByQuestionBusiness.DelateStudyPlan(zeroProportional_studyPlan_id); 
 		System.out.println("24.删除学习任务接口:");
 		String msg = (String)JSONPath.read(res_proportional, "$.msg");
 		System.out.println("msg="+msg);
 	}
 	
 	
 	@Test(description="25.删除学习项目接口", priority=25)
 	public void testDeleteProject()  {
 		String res_proportional = ScoringByQuestionBusiness.DeleteProject(proportional_project_id);
 		String res_lowProportional = ScoringByQuestionBusiness.DeleteProject(lowProportional_project_id);
 		String res_zeroProportional = ScoringByQuestionBusiness.DeleteProject(zeroProportional_project_id);
 		String deleted = (String) JSONPath.read(res_proportional, "$.deleted");
 		System.out.println("25.删除学习项目接口:"+"deleted="+deleted);
 	}
 	
 	@Test(description="26.删除岗位认证接口", priority=26)
 	public void testDeleteQualifications()  {
 		String res_proportional = ScoringByQuestionBusiness.DeleteQualifications(proportional_qualifications_id);
 		String res_lowProportional = ScoringByQuestionBusiness.DeleteQualifications(lowProportional_qualifications_id);
 		String res_zeroProportional = ScoringByQuestionBusiness.DeleteQualifications(zeroProportional_qualifications_id);
 		String msg = (String) JSONPath.read(res_proportional, "$.msg");
 		System.out.println("26.删除岗位认证接口:"+"msg="+msg);
 	}
 		

}
