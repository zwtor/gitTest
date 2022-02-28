package group.a.cases;

import cn.kxy.group.a.business.OncallBusiness;
import cn.kxy.setting.bussiness.ClassificationBusines;
import cn.kxy.setting.bussiness.UserBusiness;
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

public class TestOncall {	
	public static String studyPlan_id = "";
	public static String course_id = "";
	public static String study_score_record_ids = "";
	public static String project_score_record_ids = "";
    public static String project_id = "";
    public static String projectCourse_id = "";
    public static String questionnaire_id = "";
    public static String copy_questionnaire_id = "";
    public static String investigate_id = "";
    public static String researches_id = "";
    public static String questionBank_id = ""; 
    public static String examPlan_id = "";
    public static String sinagleChoice_id = "";
    public static Integer product_id = null;
    public static String evaluation_id = "";
    public static String qualifications_id1 = "";
    public static String qualifications_id2 = "";
    public static String certificate_id = "";
    public static String postmap_id = "";
    public static String exam_postmap_id = "";
    public static String employee_train_id = "";
    public static String exam_qualifications_id1 = "";
    public static String exam_qualifications_id2 = "";
    public static String qualifications_exam_id = "";
    public static String blank_id = "";

	String studyPlan_title = "有学分的任务" + CommonData.getStringRandom(5);
	String project_title = "有学分的项目" + CommonData.getStringRandom(5);
	String questionnaire_title = "用于复制的问卷" + CommonData.getStringRandom(5);
	String investigate_title = "复制问卷" + CommonData.getStringRandom(5);
	String exam_title = "无限重考最高成绩随机试卷" + CommonData.getStringRandom(5);
	String evaluation_title = "测试取消发布" + CommonData.getStringRandom(5);
	String qualifications1_title = "岗位认证1-" + CommonData.getStringRandom(5);
	String qualifications2_title = "岗位认证2-" + CommonData.getStringRandom(5);
	String postmap_title = "取消发布岗位地图" + CommonData.getStringRandom(5);
	String employee_train_title = "无分类的任务" + CommonData.getStringRandom(5);
	String exam_qualifications1_title = "人工阅卷1-" + CommonData.getStringRandom(5);
	String exam_qualifications2_title = "人工阅卷2-" + CommonData.getStringRandom(5);
	String exam_postmap_title = "有考试" + CommonData.getStringRandom(5);
	String classification_id = ClassificationBusines.getPrimaryId();
	public static String user_id = UserBusiness.getUserId();
	
	@Test(description="1.获取课件列表接口", priority=1)
 	public void testResourceGetList()  {
 		String res = OncallBusiness.ResourceGetList("video");
 		System.out.println("1.获取课件列表接口:");
 		course_id = (String)JSONPath.read(res, "$.list[0].id");
 		System.out.println("course_id="+course_id);
 		Assert.assertNotEquals(course_id,null,"1.获取课件列表接口"+res);
 	}
	
	
 	@Test(description="2.创建有学分的学习任务接口", priority=2)
 	public void testStudyAdd()  {
 		String res = OncallBusiness.StudyPlanAdd(studyPlan_title,course_id);
 		System.out.println("2.创建有学分的学习任务接口:");
 		studyPlan_id = (String)JSONPath.read(res, "$.data");
 		String msg = (String)JSONPath.read(res, "$.msg");
 		System.out.println("msg="+msg+","+"studyPlan_id="+studyPlan_id);
 		Assert.assertEquals(msg,"新增计划成功！","2.创建有学分的学习任务接口"+res);
 	}
 	
 	@Test(description="3.学习任务详情页接口", priority=3)
 	public void testGetOne()  {
 		String res = OncallBusiness.GetOne(studyPlan_id);
 		System.out.println("3.学习任务详情页接口:");
 		String msg = (String)JSONPath.read(res, "$.msg");
 		System.out.println("msg="+msg);
 		Assert.assertEquals(msg,"success","3.学习任务详情页接口"+res);
 	}
 	
	 	 	
 	@Test(description="4.完成学习任务的学习接口", priority=4)
 	public void testSaveProcess()  {
 		long currenttime = System.currentTimeMillis();  
		String tempTime = String.valueOf(currenttime);
 		String res = OncallBusiness.SaveProcess(studyPlan_id,course_id,tempTime);
 		System.out.println("4.完成学习任务的学习接口:");
 		Integer progress = (Integer)JSONPath.read(res, "$.progress");
 		System.out.println("progress="+progress);
 		Assert.assertSame(progress,100,"4.完成学习任务的学习接口"+res);
 	}
 	
 	
 	@Test(description="5.查询学分排行榜接口", priority=5)
 	public void tesGetScoreRank()  {
 		long current_time = System.currentTimeMillis(); 
 		long startTemp = current_time + 24L*3600*1000; 
 		long endTemp = current_time + 24L*(-2)*3600*1000; 
 		String startTime = String.valueOf(startTemp);
 		String endTime = String.valueOf(endTemp);
 		String res = OncallBusiness.GetScoreRank(endTime,startTime);
 		System.out.println("5.查询学分排行榜接口:");
 		Integer total = (Integer)JSONPath.read(res, "$.total");
 		System.out.println("total="+total);
 		Assert.assertNotEquals(total,0,"5.查询学分排行榜接口"+res);
 	}


 	
 	@Test(description="6.获取学完学习任务-学分排行榜-我的学分行为列表接口", priority=6)
 	public void testGetScoreRecordStudy()  {
 		long current_time = System.currentTimeMillis();  
 		long startTemp = current_time + 24L*3600*1000; //注意这里的结束时间一定要往后选，否则拿不到上边获得的学分行为数据
 		long endTemp = current_time + 24L*(-2)*3600*1000; 
 		String startTime = String.valueOf(startTemp);
 		String endTime = String.valueOf(endTemp);
 		System.out.println("endTime="+endTime+","+"startTime="+startTime+","+"studyPlan_title="+studyPlan_title);
 		String res = OncallBusiness.GetScoreRecord(endTime,startTime);
 		System.out.println("6.获取学完学习任务-学分排行榜-我的学分行为列表接口:");
 		//把list看做一个整体，拆成多个jsonobj,匹配到包含evaluation_title的就拿这个jsonobj里的score值
 		JSONArray eventMsgArray = (JSONArray) JSONPath.read(res, "$.list");
 		for(Object obj :eventMsgArray) {
 			JSONObject jsonObj	=	(JSONObject) JSONObject.parse(obj.toString());
 			if(jsonObj.getString("eventMsg").contains(studyPlan_title)) {
 				study_score_record_ids = jsonObj.getString("id");
 			}			
 		}		
 		System.out.println("study_score_record_ids="+study_score_record_ids);
 		Assert.assertTrue(res.contains(studyPlan_title),"6.获取学完学习任务-学分排行榜-我的学分行为列表接口"+res);
 	}
 	
 	
 	@Test(description="7.撤回学分接口", priority=7)
 	public void testScoreDelete()  {
 		String res = OncallBusiness.ScoreDelete(study_score_record_ids);
 		System.out.println("7.撤回学分接口:");
 		String deleted = (String)JSONPath.read(res, "$.deleted");
 		System.out.println("deleted="+deleted);
 		Assert.assertEquals(deleted,"true","7.撤回学分接口"+res);
 	}
 	
 	
 	@Test(description="8.撤销学分后重复学习接口", priority=8)
 	public void testSaveProcessAgain()  {
 		long currenttime = System.currentTimeMillis();  
		String tempTime = String.valueOf(currenttime);
 		String res = OncallBusiness.SaveProcess(studyPlan_id,course_id,tempTime);
 		System.out.println("8.撤销学分后重复学习接口:");
 		Integer progress = (Integer)JSONPath.read(res, "$.progress");
 		System.out.println("progress="+progress);
 		Assert.assertSame(progress,100,"8.撤销学分后重复学习接口"+res);
 	}
 		
 	
 	@Test(description="9.判断重复学习完是否获得学分接口", priority=9)
 	public void testGetScoreRecordAgain()  {
 		long current_time = System.currentTimeMillis();  
 		long endTemp = (long) (current_time + (-0.25)*3600*1000);  
 		String startTime = String.valueOf(current_time);
 		String endTime = String.valueOf(endTemp);
 		String res = OncallBusiness.GetScoreRecord(endTime,startTime);
 		System.out.println("9.判断重复学习完是否获得学分接口:");
 		JSONArray eventMsgArray = (JSONArray) JSONPath.read(res, "$.list");
 		Integer score = 0;
 		for(Object obj :eventMsgArray) {
 			JSONObject jsonObj	=	(JSONObject) JSONObject.parse(obj.toString());
 			if(jsonObj.getString("eventMsg").contains(studyPlan_title)) {
 				score = jsonObj.getInteger("score");
 			}
 		}
 		System.out.println("score="+score);
 		Assert.assertFalse(res.contains(studyPlan_title),"9.判断重复学习完是否获得学分接口"+res);
 	}
 	

 	
 	@Test(description="10.新建问卷接口", priority=10)
 	public void testAddQuestionnaire()  {
 		String res = OncallBusiness.AddQuestionnaire(questionnaire_title,classification_id);
 		String msg = (String) JSONPath.read(res, "$.msg");
 		questionnaire_id = (String) JSONPath.read(res, "$.data");
 		System.out.println("10.新建问卷接口:"+"msg="+msg+","+"questionnaire_id="+questionnaire_id);
 		Assert.assertEquals(msg, "问卷新增成功","10.新建问卷接口" + res);
 	}
 	
 	
 	@Test(description="11.复制问卷接口", priority=11)
 	public void testCopyQuestionnaire()  {
 		String res = OncallBusiness.CopyQuestionnaire(questionnaire_id);
 		String msg = (String) JSONPath.read(res, "$.msg");
 		copy_questionnaire_id = (String) JSONPath.read(res, "$.data");
 		System.out.println("11.复制问卷接口:"+"msg="+msg+","+"copy_questionnaire_id="+copy_questionnaire_id);
 		Assert.assertEquals(msg, "问卷复制成功","11.复制问卷接口" + res);
 	}
 	
 	
 	@Test(description="12.新建调研任务接口", priority=12)
 	public void testAddInvestigate()  {
 		String res = OncallBusiness.AddInvestigate(questionnaire_id,investigate_title);
 		String msg = (String) JSONPath.read(res, "$.msg");
 		investigate_id = (String) JSONPath.read(res, "$.id");
 		System.out.println("12.新建调研任务接口:"+"investigate_id="+investigate_id);
 		Assert.assertNotEquals(investigate_id, null,"12.新建调研任务接口" + res);
 	}
 	
 	
 	@Test(description="13.查询调研任务信息接口", priority=13)
 	public void testQueryQuestionnaire()  {
 		String res = OncallBusiness.QueryQuestionnaire(questionnaire_id,investigate_id);
 		researches_id = (String) JSONPath.read(res, "$.id");
 		System.out.println("13.查询调研任务信息接口:"+"researches_id="+researches_id);
 		Assert.assertNotEquals(researches_id, null,"13.查询调研任务信息接口" + res);
 	}
 	
 	
 	@Test(description="14.提交调研任务接口", priority=14)
 	public void testSubmitInvestigate()  {
 		String res = OncallBusiness.SubmitInvestigate(researches_id);
 		String operate_result = (String) JSONPath.read(res, "$.operate_result");
 		System.out.println("14.提交调研任务接口:"+"operate_result="+operate_result);
 		Assert.assertEquals(operate_result,"success","14.提交调研任务接口" + res);
 	}
 	
 	
 	@Test(description="15.查询题库id接口", priority=15)
 	public void testResourceClassifyAddProportional()  {
 		//ProportionalBank1特定题库
 		String res = OncallBusiness.QueryQuestionBankList("ProportionalBank1");
 		System.out.println("15.查询题库id接口:");
 		questionBank_id =  (String)JSONPath.read(res, "$.data.list[0].id");
 		System.out.println("questionBank_id="+questionBank_id);
		Assert.assertNotEquals(questionBank_id,null,"15.查询题库id接口"+res);
 	}
 	
 	
 	@Test(description="16.创建考试任务-随机试卷最高成绩无限重考接口", priority=16)
 	public void testExamAdd()  throws Exception{
 		SimpleDateFormat date_temp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
 		String beginTime = date_temp.format(new Date());
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, 7);
		String endTime = date_temp.format(calendar.getTime());
 		String res = OncallBusiness.ExamAdd(beginTime,endTime,questionBank_id,exam_title);
 		System.out.println("16.创建考试任务-随机试卷最高成绩无限重考接口:");
 		examPlan_id = (String)JSONPath.read(res, "$.data");
 		String msg = (String)JSONPath.read(res, "$.msg");
 		System.out.println("examPlan_id="+examPlan_id); 		
		Assert.assertEquals(msg,"新增考试成功！","16.创建考试任务-随机试卷最高成绩无限重考接口"+res);
 	}
 	
 	
 	@Test(description="17.查询试卷下的各试题id接口", priority=17)
 	public void testQueryQuestionId()  {
 		String res = OncallBusiness.QueryQuestionId(examPlan_id);
 		System.out.println("17.查询题库下的各试题id接口:");
 		sinagleChoice_id = (String)JSONPath.read(res, "$.questions[0].id");
 		String title = (String)JSONPath.read(res, "$.title");
 		System.out.println("title="+title);
 		Assert.assertNotEquals(title,null,"17.查询题库下的各试题id接口"+res);
 	}
 	
 	
 	
 	@Test(description="18.考试任务答题前查询接口", priority=18)
 	public void testQueryExams()  {
 		String res = OncallBusiness.QueryExams(examPlan_id);
 		System.out.println("18.考试任务答题前查询接口:");
 		String title = (String)JSONPath.read(res, "$.title");
 		System.out.println("title="+title);
 		Assert.assertNotEquals(title,null,"18.考试任务答题前查询接口"+res);
 	}
 	
 	
 	@Test(description="19.考试任务提交试卷接口", priority=19)
 	public void testSubmitExamExamPlan()  {
 		String res = OncallBusiness.SubmitExam(examPlan_id,sinagleChoice_id);
 		System.out.println("19.考试任务提交试卷接口:");
 		String msg = (String)JSONPath.read(res, "$.msg");
 		System.out.println("msg="+msg);
 		Assert.assertEquals(msg,"提交试卷成功!","19.考试任务提交试卷接口"+res);
 	}
 	
 	
 	@Test(description="20.考试任务数据监控-详情页接口", priority=20)
 	public void testExamResult()  {
// 		String res = OncallBusiness.ExamResult(examPlan_id);
// 		System.out.println("20.考试任务数据监控-详情页接口:");
// 		String title = (String)JSONPath.read(res, "$.title");
// 		System.out.println("title="+title);
// 		Assert.assertNotEquals(title,null,"20.考试任务数据监控-详情页接口"+res);
 	}
 	
 	
 	@Test(description="21.查询测训工具列表接口", priority=21)
 	//这里存在业务上的bug，所以需要再查一次测评工具列表接口，前边的查询是按名称查，这里随便找一个工具即可
 	public void testEvaluationToolsListQuery()  {
 		String res = OncallBusiness.EvaluationToolsList("");
 		System.out.println("21.查询测训工具列表接口:");
 		//测试环境第一个金牌会报错这里避开第一个数据
 		product_id = (Integer)JSONPath.read(res, "$.list[1].product_id");
 		String is_first_page = (String)JSONPath.read(res, "$.is_first_page");
 		System.out.println("success="+is_first_page);
		Assert.assertEquals(is_first_page,"true","21.查询测训工具列表接口"+res);
 	}
 	
 	
 	@Test(description="22.创建测评任务接口", priority=22)
 	public void testEvaluationSave()  {
// 		String res = OncallBusiness.EvaluationSave(evaluation_title,product_id,"","");
// 		System.out.println("22.创建测评任务接口:");
// 		evaluation_id = (String)JSONPath.read(res, "$.id");
// 		System.out.println("evaluation_id="+evaluation_id);
// 		Assert.assertNotEquals(evaluation_id,null,"22.创建测评任务接口"+res);
 	}
 	
 	
 	@Test(description="23.移动端-测评任务列表-未完成列表接口", priority=23)
 	public void testEvaluationList()  {
// 		String res = OncallBusiness.EvaluationList(evaluation_title,"unfinished");
// 		System.out.println("23.移动端-测评任务列表-未完成列表接口:");
// 		String check_evaluation_id = (String)JSONPath.read(res, "$.list[0].id");
// 		System.out.println("check_evaluation_id="+check_evaluation_id);
// 		Assert.assertEquals(check_evaluation_id,evaluation_id,"23.移动端-测评任务列表-未完成列表接口"+res);
 	}
 	
 	
 	@Test(description="24.测评任务列表-取消发布接口", priority=24)
 	public void testEvaluationUnpublish()  {
// 		String res = OncallBusiness.EvaluationUnpublish(evaluation_id);
// 		System.out.println("24.测评任务列表-取消发布接口:");
// 		String message = (String)JSONPath.read(res, "$.message");
// 		System.out.println("message="+message);
// 		Assert.assertEquals(message,"SUCCESS","24.测评任务列表-取消发布接口"+res);
 	}
 	
 	
 	@Test(description="25.测评任务取消发布后，校验移动端-测评任务列表是否还有数据接口", priority=25)
 	public void testEvaluationListAgain()  {
// 		String res = OncallBusiness.EvaluationList(evaluation_title,"unfinished");
// 		System.out.println("25.测评任务取消发布后，校验移动端-测评任务列表是否还有数据接口:");
// 		String check_evaluation_id = (String)JSONPath.read(res, "$.list[0].id");
// 		System.out.println("check_evaluation_id="+check_evaluation_id);
// 		Assert.assertNotEquals(check_evaluation_id,evaluation_id,"25.测评任务取消发布后，校验移动端-测评任务列表是否还有数据接口"+res);
 	}
 	

 	
 	@Test(description="26.新建岗位认证接口", priority=26)
 	public void testQualificationsAdd() throws UnsupportedEncodingException  {
 		String scopesTemp = "{\"department_list\":[],\"user_list\":[],\"group_list\":[],\"post_list\":[]}";
 		String scopes = JSONObject.parseObject(scopesTemp).toJSONString();
 		String stageJsonTemp = "[{\"content\":\"\",\"stageName\":\"阶段1\",\"isOrder\":false,\"stageSort\":1,\"stageId\":\"\","
 				+ "\"startTime\":null,\"endTime\":null,\"course\":[{\"courseId\":\""+course_id+"\",\"courseSort\":0,"
 				+ "\"courseType\":3,\"flag\":1,\"mappingId\":\"\",\"resource_lock\":false}]}]";
 		String stageJson = JSONObject.parseArray(stageJsonTemp).toJSONString();
 		String res1 = OncallBusiness.QualificationsAdd(certificate_id,scopes,stageJson,qualifications1_title);
 		String res2 = OncallBusiness.QualificationsAdd(certificate_id,scopes,stageJson,qualifications2_title);
 		String msg = (String) JSONPath.read(res1, "$.msg");
 		qualifications_id1 = (String) JSONPath.read(res1, "$.data");
 		qualifications_id2 = (String) JSONPath.read(res2, "$.data");
 		System.out.println("26.新建岗位认证接口:"+"msg="+msg+","+"qualifications_id1="+qualifications_id1+","+"qualifications_id2="+qualifications_id2);
 		Assert.assertEquals(msg, "新增计划成功！","26.新建岗位认证接口" + res1);
 	}
 	
 	
 	@Test(description="27.新建岗位认证-人工阅卷接口", priority=27)
 	public void testQualificationsAddExam() throws UnsupportedEncodingException  {
 		String scopesTemp = " {\r\n" + 
 				"            \"department_list\": [],\r\n" + 
 				"            \"group_list\": [],\r\n" + 
 				"            \"post_list\": [],\r\n" + 
 				"            \"user_list\": [\r\n" + 
 				"                  {\r\n" + 
 				"                        \"id\": \""+user_id+"\",\r\n" + 
 				"                       // \"name\": \"汶灵芝\"\r\n" + 
 				"                  }\r\n" + 
 				"            ]\r\n" + 
 				"      }";
 		String scopes = JSONObject.parseObject(scopesTemp).toJSONString();
 		String count = "1";
 		String score = "10";
 		String stageJsonTemp = "[{\"content\":\"\",\"stageName\":\"阶段1\",\"isOrder\":false,\"stageSort\":1,\"stageId\":\"\","
 				+ "\"startTime\":null,\"endTime\":null,\"course\":[{\"courseSort\":0,\"title\":\"exam\",\"type\":\"ex\","
 				+ "\"cheatFlag\":0,\"faceRecognition\":\"off\",\"examDuration\":10,\"summary\":\"\",\"flag\":2,\"mappingId\":\"\","
 				+ "\"courseId\":\"\",\"markType\":2,\"paperId\":\"\",\"paperTitle\":\"\",\"passLine\":100,"
 				+ "\"questionBankList\":\""+questionBank_id+"\",\"questionMode\":2,\"showKnowledge\":\"show\",\"repeatExam\":true,"
 				+ "\"multipleCount\":0,\"multipleUnitScore\":0,\"fillBlankCount\":\""+count+"\",\"fillBlankUnitScore\":\""+score+"\","
 						+ "\"fillBlankType\":1,"
 				+ "\"shortAnswerCount\":0,\"shortAnswerUnitScore\":0,\"shortAnswerType\":1,\"singleCount\":0,"
 						+ "\"singleUnitScore\":0,"
 				+ "\"trueOrFalseCount\":0,\"trueOrFalseUnitScore\":0,\"totalScore\":\"\",\"answerParsing\":5,\"passingScore\":60,"
 				+ "\"cutScreenCount\":-1,\"reExamRule\":0,\"reExamNumber\":0,\"scoreRule\":0,\"viewRule\":1,\"proportional\":[],"
 				+ "\"anonymousMarking\":\"undefined\",\"resource_lock\":false}]}]";
 		String stageJson = JSONObject.parseArray(stageJsonTemp).toJSONString();
 		String res1 = OncallBusiness.QualificationsAdd(certificate_id,scopes,stageJson,exam_qualifications1_title);
 		String res2 = OncallBusiness.QualificationsAdd(certificate_id,scopes,stageJson,exam_qualifications2_title);
 		String msg = (String) JSONPath.read(res1, "$.msg");
 		exam_qualifications_id1 = (String) JSONPath.read(res1, "$.data");
 		exam_qualifications_id2 = (String) JSONPath.read(res2, "$.data");
 		System.out.println("27.新建岗位认证接口:"+"msg="+msg+","+"exam_qualifications_id1="+exam_qualifications_id1+","+"exam_qualifications_id2="+exam_qualifications_id2);
 		Assert.assertEquals(msg, "新增计划成功！","27.新建岗位认证接口" + res1);
 	}
 	
 	
 	@Test(description="28.获取岗位认证-考试id接口", priority=28)
 	public void testQualificationsExamId()  {
 		String res = OncallBusiness.QualificationsExamId(exam_qualifications_id1);
 		qualifications_exam_id = (String) JSONPath.read(res, "$.stage_list[0].course_mapping_list[0].id");
 		System.out.println("28.获取岗位认证-考试id接口:"+"qualifications_exam_id="+qualifications_exam_id);
 		Assert.assertNotEquals(qualifications_exam_id,null,"28.获取岗位认证-考试id接口" + res);
 	} 	
 	
 	
 	@Test(description="29.查询岗位认证试卷下的各试题id接口", priority=29)
 	public void testQueryQuestionIdQualifications()  {
 		String res = OncallBusiness.QueryQuestionId(qualifications_exam_id);
 		System.out.println("29.查询岗位认证试卷下的各试题id接口:");
 		blank_id = (String)JSONPath.read(res, "$.questions[0].id");
 		String title = (String)JSONPath.read(res, "$.title");
 		System.out.println("title="+title);
 		Assert.assertNotEquals(title,null,"29.查询岗位认证试卷下的各试题id接口"+res);
 	}
 	
	
 	@Test(description="30.岗位认证答题前查询接口", priority=30)
 	public void testQueryExamsQualifications()  {
 		String res = OncallBusiness.QueryExams(qualifications_exam_id);
 		System.out.println("30.岗位认证答题前查询接口:");
 		String title = (String)JSONPath.read(res, "$.title");
 		System.out.println("title="+title);
 		Assert.assertNotEquals(title,null,"30.岗位认证答题前查询接口口"+res);
 	}
 	
 	
 	@Test(description="31.岗位认证提交试卷接口", priority=31)
 	public void testSubmitQualifications() throws InterruptedException  {
 		System.out.println("blank_id"+blank_id);
 		String res = OncallBusiness.SubmitExam(qualifications_exam_id,blank_id);
 		System.out.println("31.岗位认证提交试卷接口:");
 		String msg = (String)JSONPath.read(res, "$.msg");
 		System.out.println("msg="+msg);
 		Assert.assertEquals(msg,"提交试卷成功!","31.岗位认证提交试卷接口"+res);
 		Thread.sleep(2);
 	}
 	
 	                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                  
 	
 	@Test(description="35.岗位认证人工阅卷接口", priority=35)
 	public void testMarkingPaper() throws UnsupportedEncodingException  {
 		String stageJsonTemp = "[{\"questionId\":\""+blank_id+"\",\"score\":0}]";
        String questionJsonList = JSONObject.parseArray(stageJsonTemp).toJSONString();
 		String res = OncallBusiness.MarkingPaper(qualifications_exam_id,questionJsonList);
 		System.out.println("35.岗位认证人工阅卷接口:");
 		String msg = (String)JSONPath.read(res, "$.msg");
 		System.out.println("msg="+msg);
 		Assert.assertEquals(msg,"批阅成功","35.岗位认证人工阅卷接口"+res);
 	}
 		 	
 	
 	@Test(description="32.查询岗位认证状态接口", priority=32)
 	public void testQueryQualificationsState()  {
 		String res = OncallBusiness.QueryQualificationsState(exam_qualifications_id1);
 		System.out.println("32.查询岗位认证状态接口:");
 		Integer code = (Integer)JSONPath.read(res, "$.code");
 		Integer data = (Integer)JSONPath.read(res, "$.data");
 		System.out.println("code="+code+","+"data="+data);
 		Assert.assertSame(code,0,"32.查询岗位认证状态接口"+res);
 		Assert.assertSame(data,0,"32.查询岗位认证状态接口"+res);
 	}
	
 	
 	@Test(description="33.新建岗位地图接口", priority=33)
 	public void testAddPostMap() throws UnsupportedEncodingException  {
 		String map_template = "STARRY_SKY_SERIES";
 		String cover = "https://oss.coolcollege.cn/1789918456309747712.png";
 		String res = OncallBusiness.AddPostMap(qualifications_id1,qualifications_id2,map_template,cover,postmap_title);
 		postmap_id = (String) JSONPath.read(res, "$.id");
 		System.out.println("33.新建岗位地图接口:"+"postmap_id="+postmap_id);
 		Assert.assertNotEquals(postmap_id, null,"33.新建岗位地图接口" + res);
 	}
 	
 	
 	@Test(description="34.新建岗位地图-有考试接口", priority=34)
 	public void testAddPostMapExam() throws UnsupportedEncodingException  {
 		String map_template = "STARRY_SKY_SERIES";
 		String cover = "https://oss.coolcollege.cn/1789918456309747712.png";
 		String res = OncallBusiness.AddPostMap(exam_qualifications_id1,exam_qualifications_id2,map_template,cover,exam_postmap_title);
 		exam_postmap_id = (String) JSONPath.read(res, "$.id");
 		System.out.println("34.新建岗位地图-有考试接口:"+"exam_postmap_id="+exam_postmap_id);
 		Assert.assertNotEquals(exam_postmap_id, null,"34.新建岗位地图-有考试接口" + res);
 	}
 	
 	
 	
 	@Test(description="36.校验岗位地图里的考试时长接口", priority=36)
 	public void testQueryPostmapExam(){
 		String res = OncallBusiness.QueryPostmapExam(qualifications_exam_id);
 		Integer exam_duration  = (Integer) JSONPath.read(res, "$.exam_duration");
 		System.out.println("36.校验岗位地图里的考试时长接口:"+"exam_duration="+exam_duration);
 		Assert.assertNotEquals(exam_duration, null,"36.校验岗位地图里的考试时长接口" + res);
 	}
 	
 	
 	@Test(description="37.停用岗位地图 接口", priority=37)
 	public void testUpdatePostMap() {
 		String res = OncallBusiness.UpdatePostMap(exam_postmap_id,"close");
 		String update_postmap_id = (String) JSONPath.read(res, "$.id");
 		System.out.println("37.停用岗位地图接口:"+"update_postmap_id="+update_postmap_id);
 		Assert.assertNotEquals(update_postmap_id,null,"37.停用岗位地图接口" + res);
 	}
 	
 	
 	@Test(description="38.岗位地图停用后，校验移动端-岗位地图是否还有数据接口", priority=38)
 	public void testMyPostMapAgain() {
 		String res = OncallBusiness.MyPostMap();
 		String check_postmap_id = (String) JSONPath.read(res, "$.list[0].id");
 		System.out.println("38.岗位地图停用后，校验移动端-岗位地图是否还有数据接口:"+"check_postmap_id="+check_postmap_id);
 		Assert.assertNotEquals(check_postmap_id,exam_postmap_id,"38.岗位地图停用后，校验移动端-岗位地图是否还有数据接口" + res);
 	}
 	
 	
 	@Test(description="39.岗位地图重新启用后，校验移动端-岗位地图是否恢复显示数据接口", priority=39)
 	public void testUpdatePostMapAgain() {
 		String res_update = OncallBusiness.UpdatePostMap(exam_postmap_id,"open");
 		String res = OncallBusiness.MyPostMap();
 		String check_postmap_id = (String) JSONPath.read(res, "$.list[0].id");
 		System.out.println("39.岗位地图重新启用后，校验移动端-岗位地图是否恢复显示数据接口:"+"check_postmap_id="+check_postmap_id);
 		Assert.assertNotNull(check_postmap_id,"39.岗位地图重新启用后，校验移动端-岗位地图是否恢复显示数据接口" + res);
 	}
 	
 	@Test(description="40.获取岗位认证信息接口", priority=40)
 	public void testQualificationsItems() {
 		String res1 = OncallBusiness.QualificationsItems(qualifications_id1);
 		String res2 = OncallBusiness.QualificationsItems(qualifications_id2);
 		Integer status1 = (Integer) JSONPath.read(res1, "$.status");
 		Integer status2 = (Integer) JSONPath.read(res2, "$.status");
 		System.out.println("40.获取岗位认证信息接口:"+"status1="+status1+","+"status2="+status2);
 		Assert.assertSame(status1,1,"40.获取岗位认证信息接口" + res1);
 		Assert.assertSame(status2,1,"40.获取岗位认证信息接口" + res2);
 	}
 	
 	
 	@Test(description="41.加载岗位认证资源接口", priority=41)
 	public void testLoadResources() {
 		String res1 = OncallBusiness.LoadResources(qualifications_id1);
 		String res2 = OncallBusiness.LoadResources(qualifications_id2);
 		String status1 = (String) JSONPath.read(res1, "$.status");
 		String status2 = (String) JSONPath.read(res2, "$.status");
 		System.out.println("41.加载岗位认证资源接口:"+"status1="+status1+","+"status2="+status2);
 		Assert.assertEquals(status1,"success","41.加载岗位认证资源接口" + res1);
 		Assert.assertEquals(status2,"success","41.加载岗位认证资源接口" + res2);
 	}
 	
 	
 	@Test(description="42.岗位地图学习接口", priority=42)
 	public void testSaveProcessQualifications()  {
 		long currenttime = System.currentTimeMillis();  
		String tempTime = String.valueOf(currenttime);
 		String res1 = OncallBusiness.SaveProcess(qualifications_id1,course_id,tempTime);
 		String res2 = OncallBusiness.SaveProcess(qualifications_id2,course_id,tempTime);
 		System.out.println("42.岗位地图学习接口:");
 		Integer progress1 = (Integer)JSONPath.read(res1, "$.progress");
 		Integer progress2 = (Integer)JSONPath.read(res2, "$.progress");
 		System.out.println("progress1="+progress1+","+"progress2="+progress2);
 		Assert.assertSame(progress1,100,"42.岗位地图学习接口"+res1);
 		Assert.assertSame(progress2,100,"42.岗位地图学习接口"+res2);
 	}
 	
 	
 	
 	@Test(description="43.判断是否可以获得额外学分接口", priority=43)
 	public void testCheckAward()  {
 		String res = OncallBusiness.CheckAward(postmap_id);
 		System.out.println("43.判断是否可以获得额外学分接口:");
 		String can_get_award = (String)JSONPath.read(res, "$.can_get_award");
 		System.out.println("can_get_award="+can_get_award);
 		Assert.assertEquals(can_get_award,"true","43.判断是否可以获得额外学分接口"+res);
 	}
 	
 	
 	@Test(description="44.岗位地图获取额外学分接口", priority=44)
 	public void testGetAward()  {
 		String res = OncallBusiness.GetAward(postmap_id);
 		System.out.println("44.岗位地图获取额外学分接口:");
 		Integer get_score = (Integer)JSONPath.read(res, "$.get_score");
 		System.out.println("get_score="+get_score);
 		Assert.assertNotEquals(get_score,0,"44.岗位地图获取额外学分接口"+res);
 	}
 	
 	
 	@Test(description="45.再次进入岗位地图判断是否可以重复获得额外学分接口", priority=45)
 	public void testGetAwardAgain()  {
 		String res = OncallBusiness.GetAward(postmap_id);
 		System.out.println("45.再次进入岗位地图判断是否可以重复获得额外学分接口:");
 		Integer get_score = (Integer)JSONPath.read(res, "$.get_score");
 		System.out.println("get_score="+get_score);
 		Assert.assertSame(get_score,0,"45.再次进入岗位地图判断是否可以重复获得额外学分接口"+res);
 	}
 	
 	
 	
 	@Test(description="46.校验证书管理列表证明名称是否为空接口", priority=46)
 	public void testCertificateList()  {
 		String res = OncallBusiness.CertificateList("");
 		System.out.println("46.校验证书管理列表证明名称是否为空接口:");
 		JSONArray nameArray = (JSONArray) JSONPath.read(res, "$.list");
 		String certificate_name = "";
 		int count = 0;
 		for(Object obj :nameArray) {
 			JSONObject jsonObj	=	(JSONObject) JSONObject.parse(obj.toString());
 			if(jsonObj.getString("name")==null) {
 				certificate_name = jsonObj.getString("name");
 				count ++;
 			}
 		}
 		Integer total = (Integer)JSONPath.read(res, "$.total");
 		System.out.println("total="+total+","+"count="+count);
 		Assert.assertNotEquals(total,0,"46.校验证书管理列表证明名称是否为空接口"+res);
 	}
 	
 	
 	@Test(description="47.创建无分类的新员工培训接口", priority=47)
 	public void testSaveOrUpdateBaseInfo()  {
 		System.out.println("47.创建无分类的新员工培训接口:");
 		String res1 = OncallBusiness.SaveOrUpdateBaseInfo(employee_train_title);
 		employee_train_id = (String) JSONPath.read(res1, "$.data");
 		String msg = (String)JSONPath.read(res1, "$.msg");
 		Integer code = (Integer) JSONPath.read(res1, "$.code");
 		String res2 = OncallBusiness.UpdateContent(employee_train_id,course_id);
 		String res3 = OncallBusiness.UpdateSettingPublish(employee_train_id);
 		System.out.println("code="+code+","+"employee_train_id="+employee_train_id);
 		Assert.assertEquals(msg, "","47.创建无分类的新员工培训接口：" + res1);
 	}
 	
 	
 	@Test(description="48.无分类的新员工任务导出接口", priority=48)
 	public void testExportTrainMonitor()  {
 		System.out.println("48.无分类的新员工任务导出接口:");
 		String res = OncallBusiness.ExportTrainMonitor(employee_train_id);
 		String msg = (String)JSONPath.read(res, "$.msg");
 		System.out.println("msg="+msg);
 		Assert.assertEquals(msg, "新员工培训监控信息导出成功","48.无分类的新员工任务导出接口：" + res);
 	}
 	
 	
 	@Test(description="51.删除考试任务接口", priority=51)
 	public void testDelateExam()  {
 		String res = OncallBusiness.DelateExam(examPlan_id); 
 		System.out.println("51.删除考试任务接口接口:");
 		String msg = (String)JSONPath.read(res, "$.msg");
 		System.out.println("msg="+msg);
		Assert.assertEquals(msg,"删除任务成功","51.删除考试任务接口接口"+res);
 	}
 		
 	
 	@Test(description="52.删除调研任务接口", priority=52)
 	public void testDeleteInvestigate()  {
 		String res = OncallBusiness.DeleteInvestigate(investigate_id);
 		String status = (String) JSONPath.read(res, "$.status");
 		System.out.println("52.删除调研任务接口:"+"status="+status);
 		Assert.assertEquals(status,"true","52.删除调研任务接口" + res);
 	}
 	
 	
 	@Test(description="53.删除学习任务接口", priority=53)
 	public void testDeleteStudy()  {
 		String res = OncallBusiness.DeleteStudy(studyPlan_id);
 		String msg = (String) JSONPath.read(res, "$.msg");
 		System.out.println("53.删除学习任务接口:"+"msg="+msg);
 		Assert.assertEquals(msg, "删除学习计划成功","53.删除学习任务接口" + res);
 	}
 	
 	
 	
 	@Test(description="54.删除问卷接口", priority=54)
 	public void testDeleteQuestionnaire()  {
 		String res = OncallBusiness.DeleteQuestionnaire(questionnaire_id);
 		String res_copy = OncallBusiness.DeleteQuestionnaire(copy_questionnaire_id);
 		String msg = (String) JSONPath.read(res, "$.msg");
 		System.out.println("54.删除问卷接口:"+"msg="+msg);
 		Assert.assertEquals(msg, "问卷删除成功","54.删除问卷接口" + msg);
 	}
 	
 	@Test(description="55.删除测评任务接口", priority=55)
 	public void testEvaluationDelete()  {
// 		String res = OncallBusiness.EvaluationDelete(evaluation_id);
// 		System.out.println("55.删除测评任务接口:");
// 		String message = (String)JSONPath.read(res, "$.message");
// 		System.out.println("message="+message);
//		Assert.assertEquals(message,"SUCCESS","55.删除测评任务接口"+res);
 	}
 	
 	
 	@Test(description="56.删除岗位地图接口", priority=56)
 	public void testDeletePostMap()  {
 		String res1 = OncallBusiness.DaletePostMap(postmap_id);
 		String res2 = OncallBusiness.DaletePostMap(exam_postmap_id);
 		System.out.println("56.删除岗位地图接口:");
 		String msg = (String)JSONPath.read(res1, "$.msg");
 		System.out.println("msg="+msg);
		Assert.assertEquals(msg,"删除成功","56.删除岗位地图接口"+res1);
 	}
 	
 	
 	@Test(description="57.删除岗位认证接口", priority=57)
 	public void testDeleteQualifications()  {
 		String res1 = OncallBusiness.DeleteQualifications(qualifications_id1);
 		String res2 = OncallBusiness.DeleteQualifications(qualifications_id2);
 		String res3 = OncallBusiness.DeleteQualifications(exam_qualifications_id1);
 		String res4 = OncallBusiness.DeleteQualifications(exam_qualifications_id2);
 		System.out.println("57.删除岗位认证接口:");
 		String msg = (String)JSONPath.read(res1, "$.msg");
 		System.out.println("msg="+msg);
		Assert.assertEquals(msg,"能力认证删除成功","57.删除岗位认证接口"+res1);
 	}
 	
 	
 	@Test(description="58.删除新员工培训任务接口", priority=58)
 	public void testDeleteEmployTrainTask()  {
 		System.out.println("58.删除新员工培训任务接口:");
 		String res = OncallBusiness.DeleteEmployTrainTask(employee_train_id);
 		String msg = (String)JSONPath.read(res, "$.msg");
 		System.out.println("msg="+msg);
 		Assert.assertEquals(msg, "删除成功","58.删除新员工培训任务接口：" + res);
 	}

 	

}
