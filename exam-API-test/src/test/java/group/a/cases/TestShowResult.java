package group.a.cases;

import cn.kxy.group.a.business.ShowResultBusiness;
import cn.kxy.setting.bussiness.ClassificationBusines;
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

public class TestShowResult {	
	public static String paperId = "";
	public static String paper_title = "";
	public static String exam_plan_true_id = "";
	public static String exam_plan_false_id = "";
	public static String qualifications_id = "";
	public static String qualifications_exam_true_id = "";
	public static String qualifications_exam_false_id = "";
	
	String exam_title1 = "允许查看考试结果" + CommonData.getStringRandom(5);
	String exam_title2 = "不允许查看考试结果" + CommonData.getStringRandom(5);
	String title_qualifications = "设置考试结果查看" + CommonData.getStringRandom(5);
	String title1 = "允许查看考试结果";
	String title2 = "不允许查看考试结果";
	String classification_id = ClassificationBusines.getPrimaryId();

	//考试结果隐藏都采用固定试卷模式
	@Test(description="1.查询试卷列表接口", priority=1)
 	public void testPaperGetList()  {
 		String res = ShowResultBusiness.PaperGetList("CorrectWrongRateBankPaper");
 		System.out.println("1.查询试卷列表接口:");
 		paperId = (String)JSONPath.read(res, "$.list[0].id");
 		paper_title = (String)JSONPath.read(res, "$.list[0].title");
 		System.out.println("paperId="+paperId);
 		Integer total = (Integer)JSONPath.read(res, "$.total");
		Assert.assertNotEquals(total,0,"1.查询试卷列表接口"+res);
 	}
 	
 	@Test(description="2.添加考试任务-允许查看考试结果接口", priority=2)
 	public void testExamAdd()  throws Exception{
 		SimpleDateFormat date_temp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
 		String cuurrentTime = date_temp.format(new Date());//当前时间
		Calendar calendarTemp = Calendar.getInstance();
		calendarTemp.add(Calendar.DATE, 7);
		String endTime = date_temp.format(calendarTemp.getTime());	
 		String res = ShowResultBusiness.ExamAdd(cuurrentTime,endTime,paperId,"true",exam_title1);
 		System.out.println("2.添加考试任务-允许查看考试结果接口:");
 		exam_plan_true_id = (String)JSONPath.read(res, "$.data");
 		String msg = (String)JSONPath.read(res, "$.msg");
 		System.out.println("exam_plan_true_id="+exam_plan_true_id+","+"msg="+msg); 		
		Assert.assertEquals(msg,"新增考试成功！","2.添加考试任务-允许查看考试结果接口"+res);
 	}
 	
 	
 	@Test(description="3.添加考试任务-不允许查看考试结果接口", priority=3)
 	public void testExamAddFalse()  throws Exception{
 		SimpleDateFormat date_temp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
 		String cuurrentTime = date_temp.format(new Date());//当前时间
		Calendar calendarTemp = Calendar.getInstance();
		calendarTemp.add(Calendar.DATE, 7);
		String endTime = date_temp.format(calendarTemp.getTime());	
 		String res = ShowResultBusiness.ExamAdd(cuurrentTime,endTime,paperId,"false",exam_title2);
 		System.out.println("3.添加考试任务-不允许查看考试结果接口:");
 		exam_plan_false_id = (String)JSONPath.read(res, "$.data");
 		String msg = (String)JSONPath.read(res, "$.msg");
 		System.out.println("exam_plan_false_id="+exam_plan_false_id+","+"msg="+msg); 		
		Assert.assertEquals(msg,"新增考试成功！","3.添加考试任务-不允许查看考试结果接口"+res);
 	}
 	
 	public static String certificate_id = "";
 	@Test(description="4.获取证书列表接口", priority=4)
 	public void testCertificateGetList()  {
 		String res = ShowResultBusiness.CertificateGetList("Coo_cert");
 		certificate_id = (String) JSONPath.read(res, "$.list[0].id");
 		Integer total = (Integer) JSONPath.read(res, "$.total");
 		System.out.println("4.获取证书列表接口:"+"certificate_id="+certificate_id);
 		Assert.assertNotEquals(total, 0,"4.获取证书列表接口" + res);
 	}
 	
 	
 	@Test(description="5.新建岗位认证-考试结果隐藏+不隐藏接口", priority=5)
 	public void testQualificationsAdd() throws UnsupportedEncodingException  {
 		String scopesTemp = "{\"department_list\":[],\"user_list\":[],\"group_list\":[],\"post_list\":[]}";
 		String scopes = JSONObject.parseObject(scopesTemp).toJSONString();	
 		String show_result1 = "true";
 		String show_result2 = "false";
 		String stageJsonTemp = "[{\"content\":\"\",\"stageName\":\"阶段1\",\"isOrder\":false,\"stageSort\":1,\"stageId\":\"\","
 				+ "\"startTime\":null,\"endTime\":null,\"course\":[{\"courseSort\":0,\"title\":\""+title1+"\",\"type\":\"ex\","
 				+ "\"cheatFlag\":0,\"faceRecognition\":\"off\",\"examDuration\":10,\"summary\":\"\",\"flag\":2,\"mappingId\":\"\","
 				+ "\"courseId\":\"\",\"markType\":1,\"paperId\":\""+paperId+"\",\"paperTitle\":\"CorrectWrongRateBankPaper\","
 				+ "\"passLine\":100,\"questionBankList\":\"\",\"questionMode\":1,\"showKnowledge\":\"show\",\"repeatExam\":true,"
 				+ "\"multipleCount\":\"\",\"multipleUnitScore\":\"\",\"fillBlankCount\":\"\",\"fillBlankUnitScore\":\"\","
 				+ "\"fillBlankType\":1,\"shortAnswerCount\":\"\",\"shortAnswerUnitScore\":\"\",\"shortAnswerType\":1,"
 				+ "\"singleCount\":\"\",\"singleUnitScore\":\"\",\"trueOrFalseCount\":\"\",\"trueOrFalseUnitScore\":\"\","
 				+ "\"multipleMissedScore\":\"\",\"totalScore\":30,\"answerParsing\":1,\"passingScore\":\"30.0\",\"cutScreenCount\":-1,"
 				+ "\"reExamRule\":0,\"reExamNumber\":0,\"scoreRule\":0,\"viewRule\":1,\"proportional\":[],"
 				+ "\"anonymousMarking\":\"undefined\",\"show_result\":\""+show_result1+"\",\"resource_lock\":false},{\"courseSort\":1,"
 				+ "\"title\":\""+title2+"\",\"type\":\"ex\",\"cheatFlag\":0,\"faceRecognition\":\"off\",\"examDuration\":10,"
 				+ "\"summary\":\"\",\"flag\":2,\"mappingId\":\"\",\"courseId\":\"\",\"markType\":1,\"paperId\":\""+paperId+"\","
 				+ "\"paperTitle\":\"CorrectWrongRateBankPaper\",\"passLine\":100,\"questionBankList\":\"\",\"questionMode\":1,"
 				+ "\"showKnowledge\":\"show\",\"repeatExam\":true,\"multipleCount\":\"\",\"multipleUnitScore\":\"\","
 				+ "\"fillBlankCount\":\"\",\"fillBlankUnitScore\":\"\",\"fillBlankType\":1,\"shortAnswerCount\":\"\","
 				+ "\"shortAnswerUnitScore\":\"\",\"shortAnswerType\":1,\"singleCount\":\"\",\"singleUnitScore\":\"\","
 				+ "\"trueOrFalseCount\":\"\",\"trueOrFalseUnitScore\":\"\",\"multipleMissedScore\":\"\",\"totalScore\":30,"
 				+ "\"answerParsing\":1,\"passingScore\":\"30.0\",\"cutScreenCount\":-1,\"reExamRule\":0,\"reExamNumber\":0,"
 				+ "\"scoreRule\":0,\"viewRule\":1,\"proportional\":[],\"anonymousMarking\":\"undefined\",\"show_result\":\""+show_result2+"\","
 				+ "\"resource_lock\":false}]}]";
 		String stageJson = JSONObject.parseArray(stageJsonTemp).toJSONString();
 		String res = ShowResultBusiness.QualificationsAdd(certificate_id,scopes,stageJson,title_qualifications);
 		String msg = (String) JSONPath.read(res, "$.msg");
 		qualifications_id = (String) JSONPath.read(res, "$.data");
 		System.out.println("5.新建岗位认证-考试结果隐藏+不隐藏接口:"+"msg="+msg+","+"qualifications_id="+qualifications_id);
 		Assert.assertEquals(msg, "新增计划成功！","5.新建岗位认证-考试结果隐藏+不隐藏接口" + res);
 	}
 	
 	
 	@Test(description="6.获取岗位认证-考试id接口", priority=6)
 	public void testQualificationsExamId()  {
 		String res = ShowResultBusiness.QualificationsExamId(qualifications_id);
 		qualifications_exam_true_id = (String) JSONPath.read(res, "$.stage_list[0].course_mapping_list[0].id");
 		qualifications_exam_false_id = (String) JSONPath.read(res, "$.stage_list[0].course_mapping_list[1].id");
 		System.out.println("6.获取岗位认证-考试id接口:"+"qualifications_exam_true_id="+qualifications_exam_true_id+","
 				+"qualifications_exam_false_id="+qualifications_exam_false_id);
 		Assert.assertNotEquals(qualifications_exam_true_id,null,"6.获取岗位认证-考试id接口" + res);
 	}
 	

 	@Test(description="7.PC端/移动端-考试任务/岗位认证前check接口", priority=7)
 	public void testExamCheck() {	
 		String res_exam_true = ShowResultBusiness.ExamCheck(exam_plan_true_id);
 		String res_exam_false = ShowResultBusiness.ExamCheck(exam_plan_false_id);
 		String res_qualifications_true = ShowResultBusiness.ExamCheck(qualifications_exam_true_id);
 		String res_qualifications_false = ShowResultBusiness.ExamCheck(qualifications_exam_false_id);
 		System.out.println("7.PC端/移动端-考试任务/岗位认证前check接口:");
 		String can_exam = (String)JSONPath.read(res_exam_true, "$.can_exam");
 		System.out.println("can_exam="+can_exam);
		Assert.assertEquals(can_exam,"true","7.PC端/移动端-考试任务/岗位认证前check接口"+res_exam_true);
 	}
 	
 	
 	public static String multiplechoice_id1 = "";
 	public static String multiplechoice_id2 = "";
 	public static String multiplechoice_id3 = "";	
 	@Test(description="8.考试任务/岗位认证答题前query+查询各试题id及答案id接口", priority=8)
 	public void testQueryQuestionIdBank()  {
 		String res_exam_true = ShowResultBusiness.QueryQuestionId(exam_plan_true_id);
 		String res_exam_false = ShowResultBusiness.QueryQuestionId(exam_plan_false_id);
 		String res_qualifications_true = ShowResultBusiness.QueryQuestionId(qualifications_exam_true_id);
 		String res_qualifications_false = ShowResultBusiness.QueryQuestionId(qualifications_exam_false_id);
 		System.out.println("8.考试任务/岗位认证答题前query+查询各试题id及答案id接口:");
 		multiplechoice_id1 = (String)JSONPath.read(res_exam_true, "$.questions[0].id");
 		multiplechoice_id2 = (String)JSONPath.read(res_exam_true, "$.questions[1].id");
 		multiplechoice_id3 = (String)JSONPath.read(res_exam_true, "$.questions[2].id");
 		String title = (String)JSONPath.read(res_exam_true, "$.title");
 		System.out.println("title="+title);
 		Assert.assertNotEquals(title,null,"8.考试任务/岗位认证答题前query+查询各试题id及答案id接口"+res_exam_true);
 	}
 	
 	
 	@Test(description="9.考试任务/岗位认证-提交试卷接口", priority=9)
 	public void testSubmitExamExamPlan()  {
 		String res_exam_true = ShowResultBusiness.SubmitExam(exam_plan_true_id,multiplechoice_id1,multiplechoice_id2,multiplechoice_id3);
 		String res_exam_false = ShowResultBusiness.SubmitExam(exam_plan_false_id,multiplechoice_id1,multiplechoice_id2,multiplechoice_id3);
 		String res_qualifications_true = ShowResultBusiness.SubmitExam(qualifications_exam_true_id,multiplechoice_id1,multiplechoice_id2,multiplechoice_id3);
 		String res_qualifications_false = ShowResultBusiness.SubmitExam(qualifications_exam_false_id,multiplechoice_id1,multiplechoice_id2,multiplechoice_id3);
 		System.out.println("9.考试任务/岗位认证-提交试卷接口:");
 		String msg_exam_peper = (String)JSONPath.read(res_exam_true, "$.msg");
 		System.out.println("msg_exam_peper="+msg_exam_peper);
 		Assert.assertEquals(msg_exam_peper,"提交试卷成功!","9.考试任务/岗位认证-提交试卷接口"+res_exam_true);
 	}
 	
 	
 	@Test(description="10.校验学员端考试任务是否隐藏考试结果接口", priority=10)
 	public void testExamQuery()  {
 		String res_exam_true = ShowResultBusiness.ExamQuery(exam_title1);
 		String res_exam_false = ShowResultBusiness.ExamQuery(exam_title2);
 		System.out.println("10.校验学员端考试任务是否隐藏考试结果接口:");
 		String show_answer_analysis_true = (String)JSONPath.read(res_exam_true, "$.list[0].show_answer_analysis");
 		String show_answer_analysis_false = (String)JSONPath.read(res_exam_false, "$.list[0].show_answer_analysis");
 		Boolean show_result_true = (Boolean)JSONPath.read(res_exam_true, "$.list[0].show_result");
 		Boolean show_result_false = (Boolean)JSONPath.read(res_exam_false, "$.list[0].show_result");
 		System.out.println("show_answer_analysis_true="+show_answer_analysis_true+","+"show_answer_analysis_false="+show_answer_analysis_false
 				+","+"show_result_true="+show_result_true+","+"show_result_false="+show_result_false);
 		Assert.assertTrue(show_result_true,"10.校验学员端考试任务是否隐藏考试结果接口"+res_exam_true);
 		Assert.assertFalse(show_result_false,"10.校验学员端考试任务是否隐藏考试结果接口"+res_exam_true);
 	}
 	
 	
 	@Test(description="11.校验培训档案中任务是否隐藏考试结果接口", priority=11)
 	public void testGetUserTrainArchivesDetailList()  {
 		String res_exam = ShowResultBusiness.GetUserTrainArchivesDetailList("4");
 		String progressOrScore_true = "";
 		String progressOrScore_false = "";
 		//把list看做一个整体，拆成多个jsonobj,匹配到包含evaluation_title的就拿这个jsonobj里的score值
 		JSONArray planNameTrueArray = (JSONArray) JSONPath.read(res_exam, "$.data.detailList.list");
 		for(Object obj :planNameTrueArray) {
 			JSONObject jsonObj	=	(JSONObject) JSONObject.parse(obj.toString());
 			if(jsonObj.getString("planName").contains(exam_title1)) {
 				progressOrScore_true = jsonObj.getString("progressOrScore");
 			}			
 		}	
 		for(Object obj :planNameTrueArray) {
 			JSONObject jsonObj	=	(JSONObject) JSONObject.parse(obj.toString());
 			if(jsonObj.getString("planName").contains(exam_title2)) {
 				progressOrScore_false = jsonObj.getString("progressOrScore");
 			}			
 		}
 		System.out.println("11.校验培训档案中任务是否隐藏考试结果接口:");
 		System.out.println("progressOrScore_true="+progressOrScore_true+","+"progressOrScore_false="+progressOrScore_false);
 		Assert.assertNotEquals(progressOrScore_true,"--","21.校验培训档案中任务是否隐藏考试结果接口"+res_exam);
 		Assert.assertEquals(progressOrScore_false,"--","11.校验培训档案中任务是否隐藏考试结果接口"+res_exam);
 	}

 	
 	
 	@Test(description="12.删除考试任务接口", priority=12)
 	public void testDelateExam()  {
 		String res_exam_true= ShowResultBusiness.DelateExam(exam_plan_true_id); 
 		String res_exam_false= ShowResultBusiness.DelateExam(exam_plan_false_id);
 		System.out.println("12.删除考试任务接口接口:");
 		String msg = (String)JSONPath.read(res_exam_true, "$.msg");
 		System.out.println("msg="+msg);
		Assert.assertEquals(msg,"删除任务成功","12.删除考试任务接口接口"+res_exam_true);
 	}
 	
 	
 	@Test(description="13.删除岗位认证接口", priority=13)
 	public void testDeleteQualifications()  {
 		String res = ShowResultBusiness.DeleteQualifications(qualifications_id);
 		String msg = (String) JSONPath.read(res, "$.msg");
 		System.out.println("13.删除岗位认证接口:"+"msg="+msg);
 		Assert.assertEquals(msg, "能力认证删除成功","13.删除岗位认证接口" + res);
 	}
 	
	

}
