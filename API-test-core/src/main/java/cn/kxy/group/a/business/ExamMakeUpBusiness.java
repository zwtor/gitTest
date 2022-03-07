package cn.kxy.group.a.business;

import cn.kxy.base.business.EnterpriseData;
import cn.kxy.base.business.EnterpriseDataUrl;
import cn.kxy.base.business.ExamDataUrl;
import cn.kxy.base.business.TokenData;
import cn.kxy.setting.bussiness.UserBusiness;
import com.lazy.httpclient.utils.HttpRequest;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class ExamMakeUpBusiness {

	public static String exam_url= ExamDataUrl.getNewExamUrl();
	public static String enterprise_url = EnterpriseDataUrl.getEnterpriseUrl();
	public static String token = TokenData.getMangerToken();
	public static String enterpriseId = EnterpriseData.getEnterpriseId();
	public static String user_id = UserBusiness.getUserId();
	public static String exam_result_url = exam_url + "plan/exam/result/";
    public static String exam_add_url = exam_url + "plan/exam/add/";
    public static String paper_getList_url = exam_url + "paper/getList/";
    public static String add_makeUp_url = exam_url + "plan/exam/addMakeUpExamV2/";
    public static String myExam_list_url = exam_url + "v2/"+enterpriseId+"/users/"+user_id+"/exams/";
    public static String entrance_query_url (String exam_plan_id) {
    	return exam_url + "v2/"+enterpriseId+"/exams/"+exam_plan_id+"/query/";
    }
    public static String exams_check_url (String exam_plan_id) {
    	return exam_url + "v2/"+enterpriseId+"/users/"+user_id+"/exams/"+exam_plan_id+"/check/";
    }
    public static String exam_detail_query_url (String exam_plan_id) {
    	return exam_url + "v2/"+enterpriseId+"/users/"+user_id+"/exams/"+exam_plan_id+"/query/";
    }
    public static String exam_submit_url (String exam_plan_id) {
    	return exam_url + "v2/"+enterpriseId+"/users/"+user_id+"/exams/"+exam_plan_id+"/submit/";
    }
    public static String plan_study_add_url = enterprise_url + "plan/study/add/";
    public static String myTask_detail_url = enterprise_url + "plan/myTask/getOne/";
    public static String study_exam_query_url (String study_plan_id) {
    	return enterprise_url + "v2/"+enterpriseId+"/studies/"+study_plan_id+"/exams/";
    }
    public static String studyExam_analyse_url = exam_url + "plan/exam/result/";
    public static String delete_exam_url = exam_url + "plan/exam/delete/";
    public static String delete_study_plan_url = enterprise_url + "plan/study/delete/";

    
    
    /**   
	 * @Title: PaperGetList  
	 * @Description: TODO  查询试卷列表
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String PaperGetList(){	
		return HttpRequest.get(paper_getList_url).query("access_token", token).query("sortOrder","desc")
				.send().body();
	}
    
    
    /**   
	 * @Title: ExamAdd  
	 * @Description: TODO  添加考试任务-允许重复考试
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String ExamAdd(String paperId,String beginTime,String endTime,String repeatExam,String title) throws Exception{	
		return HttpRequest.post(exam_add_url).query("access_token", token).query("authorityRange","false")
				.query("beginTime",URLEncoder.encode(beginTime, "UTF-8"))
				.query("endTime",URLEncoder.encode(endTime, "UTF-8")).query("cheatFlag","0").query("classifyId","0")
				.query("cutScreenCount","-1").query("examCertificateId","0")
				.query("examDuration","10").query("faceRecognition","off").query("fillBlankType","1").query("isAllIn","false")
				.query("isGetScore","false").query("markType","1").query("paperId",paperId).query("passLine","100")
				.query("questionMode","1").query("reExamNumber","0").query("reExamRule","0").query("repeatExam",repeatExam)
				.query("scoreRule","0").query("shortAnswerType","1").query("showKnowledge","show").query("supervisorId",user_id)
				.query("supervisorPaper","true").query("title",title).query("totalScore","100").query("userIds",user_id)
				.send().body();
	}
	
    
	/**   
	 * @Title: ExamResult  
	 * @Description: TODO  考试任务数据监控页发布补考入口校验
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String ExamResult(String planId){	
		return HttpRequest.get(exam_result_url).query("access_token", token).query("planId",planId)
				.send().body();
	}
	
	
	/**
	 * @throws UnsupportedEncodingException    
	 * @Title: AddMakeUp  
	 * @Description: TODO  考试任务发布补考
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String AddMakeUp(String examPlanId,String startTime,String endTime) throws UnsupportedEncodingException{	
		return HttpRequest.post(add_makeUp_url).query("access_token", token).query("examPlanId",examPlanId).query("type","2")
				.query("startTime",URLEncoder.encode(startTime, "UTF-8")).query("endTime",URLEncoder.encode(endTime, "UTF-8"))
				.send().body();
	}
	
	
	/**   
	 * @Title: MyExamList  
	 * @Description: TODO  PC/移动端-我的考试任务列表判断是否有补考入口
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String MyExamList(){	
		return HttpRequest.get(myExam_list_url).query("access_token", token).query("status","all").send().body();
	}
	
	/**   
	 * @Title: EntranceQuery  
	 * @Description: TODO  PC/移动端-点击补考出弹窗校验
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String EntranceQuery(String exam_plan_id){	
		return HttpRequest.get(entrance_query_url(exam_plan_id)).query("access_token", token).send().body();
	}

	
	/**   
	 * @Title: ExamCheck  
	 * @Description: TODO  PC端-进入补考页面前校验考试check
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String ExamCheck(String exam_plan_id){	
		return HttpRequest.get(exams_check_url(exam_plan_id)).query("access_token", token).send().body();
	}
	
	/**   
	 * @Title: ExamDetailQuery  
	 * @Description: TODO  PC端-进入补考页面查询考试内容
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String ExamDetailQuery(String exam_plan_id){	
		return HttpRequest.get(exam_detail_query_url(exam_plan_id)).query("access_token", token).send().body();
	}
	
	/**   
	 * @Title: ExamDetailQuery  
	 * @Description: TODO  PC端-进入补考页面查询考试内容
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String ExamSubmit(String exam_plan_id,String question_id,String answer_id){	
		return HttpRequest.post(exam_submit_url(exam_plan_id)).query("access_token", token).data("{\r\n" + 
				"      \"questions\": [\r\n" + 
				"            {\r\n" + 
				"                  \"id\": \""+question_id+"\",\r\n" + 
				"                  \"answer\": [\r\n" + 
				"                        \""+question_id+"\"\r\n" + 
				"                  ],\r\n" + 
				"                  \"answer_image\": \"\"\r\n" + 
				"            }\r\n" + 
				"      ],\r\n" + 
				"      \"makeup_count\": 0,\r\n" + 
				"    \"access_token\":\""+token+"\"\r\n" + 
				"}\r\n" + 
				"")
				.send().body();
	}
	
	
	/**   
	 * @Title: PlanStudyAdd  
	 * @Description: TODO  创建允许/不允许重复考试的学习任务
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String PlanStudyAdd(String title,String beginTime,String endTime,String title1,String paperId,
			String paper_title,String title2){	
		return HttpRequest.post(plan_study_add_url).query("access_token", token).data("{\r\n" + 
				"      \"title\": \""+title+"\",\r\n" + 
				"      \"beginTime\": \""+beginTime+"\",\r\n" + 
				"      \"endTime\": \""+endTime+"\",\r\n" + 
				"      \"projectId\": \"\",\r\n" + 
				"      \"summary\": \"\",\r\n" + 
				"      \"classify_id\": \"\",\r\n" + 
				"      \"dingImgUrl\": \"\",\r\n" + 
				"      \"departmentIds\": \"\",\r\n" + 
				"      \"supervisor_department_ids\": [],\r\n" + 
				"      \"groupIds\": \"\",\r\n" + 
				"      \"isGetScore\": true,\r\n" + 
				"      \"isNoticeAll\": 0,\r\n" + 
				"      \"planCertificateId\": \"\",\r\n" + 
				"      \"postIds\": \"\",\r\n" + 
				"      \"progress\": 0,\r\n" + 
				"      \"studyScore\": {\r\n" + 
				"            \"finishScore\": 0,\r\n" + 
				"            \"unFinishScore\": 0\r\n" + 
				"      },\r\n" + 
				"      \"scheduleSetting\": \"free_mode\",\r\n" + 
				"      \"studyTimeLimit\": 0,\r\n" + 
				"      \"faceRecognition\": \"off\",\r\n" + 
				"      \"supervisorId\": \""+user_id+"\",\r\n" + 
				"      \"supervisorPaper\": true,\r\n" + 
				"      \"authorityRange\": false,\r\n" + 
				"      \"times\": 1,\r\n" + 
				"      \"operationTimes\": \"\",\r\n" + 
				"      \"userIds\": \""+user_id+"\",\r\n" + 
				"      \"dingGroupIds\": \"\",\r\n" + 
				"      \"stageJson\": [\r\n" + 
				"            {\r\n" + 
				"                  \"content\": \"\",\r\n" + 
				"                  \"stageName\": \"阶段1\",\r\n" + 
				"                  \"isOrder\": false,\r\n" + 
				"                  \"stageSort\": 1,\r\n" + 
				"                  \"stageId\": \"\",\r\n" + 
				"                  \"startTime\": null,\r\n" + 
				"                  \"endTime\": null,\r\n" + 
				"                  \"course\": [\r\n" + 
				"                        {\r\n" + 
				"                              \"courseSort\": 0,\r\n" + 
				"                              \"title\": \""+title1+"\",\r\n" + 
				"                              \"type\": \"ex\",\r\n" + 
				"                              \"cheatFlag\": 0,\r\n" + 
				"                              \"faceRecognition\": \"off\",\r\n" + 
				"                              \"examDuration\": 20,\r\n" + 
				"                              \"summary\": \"\",\r\n" + 
				"                              \"flag\": 2,\r\n" + 
				"                              \"mappingId\": \"\",\r\n" + 
				"                              \"courseId\": \"\",\r\n" + 
				"                              \"markType\": 1,\r\n" + 
				"                              \"paperId\": \""+paperId+"\",\r\n" + 
				"                              \"paperTitle\": \""+paper_title+"\",\r\n" + 
				"                              \"passLine\": 100,\r\n" + 
				"                              \"questionBankList\": \"\",\r\n" + 
				"                              \"questionMode\": 1,\r\n" + 
				"                              \"showKnowledge\": \"show\",\r\n" + 
				"                              \"repeatExam\": true,\r\n" + 
				"                              \"multipleCount\": \"\",\r\n" + 
				"                              \"multipleUnitScore\": \"\",\r\n" + 
				"                              \"fillBlankCount\": \"\",\r\n" + 
				"                              \"fillBlankUnitScore\": \"\",\r\n" + 
				"                              \"fillBlankType\": 1,\r\n" + 
				"                              \"shortAnswerCount\": \"\",\r\n" + 
				"                              \"shortAnswerUnitScore\": \"\",\r\n" + 
				"                              \"shortAnswerType\": 1,\r\n" + 
				"                              \"singleCount\": \"\",\r\n" + 
				"                              \"singleUnitScore\": \"\",\r\n" + 
				"                              \"trueOrFalseCount\": \"\",\r\n" + 
				"                              \"trueOrFalseUnitScore\": \"\",\r\n" + 
				"                              \"totalScore\": 30,\r\n" + 
				"                              \"answerParsing\": 5,\r\n" + 
				"                              \"passingScore\": \"30.0\",\r\n" + 
				"                              \"cutScreenCount\": -1,\r\n" + 
				"                              \"reExamRule\": 0,\r\n" + 
				"                              \"reExamNumber\": 0,\r\n" + 
				"                              \"scoreRule\": 0,\r\n" + 
				"                              \"viewRule\": 1,\r\n" + 
				"                              \"proportional\": []\r\n" + 
				"                        },\r\n" + 
				"                        {\r\n" + 
				"                              \"courseSort\": 1,\r\n" + 
				"                              \"title\": \""+title2+"\",\r\n" + 
				"                              \"type\": \"ex\",\r\n" + 
				"                              \"cheatFlag\": 0,\r\n" + 
				"                              \"faceRecognition\": \"off\",\r\n" + 
				"                              \"examDuration\": 20,\r\n" + 
				"                              \"summary\": \"\",\r\n" + 
				"                              \"flag\": 2,\r\n" + 
				"                              \"mappingId\": \"\",\r\n" + 
				"                              \"courseId\": \"\",\r\n" + 
				"                              \"markType\": 1,\r\n" + 
				"                              \"paperId\": \""+paperId+"\",\r\n" + 
				"                              \"paperTitle\": \""+paper_title+"\",\r\n" + 
				"                              \"passLine\": 100,\r\n" + 
				"                              \"questionBankList\": \"\",\r\n" + 
				"                              \"questionMode\": 1,\r\n" + 
				"                              \"showKnowledge\": \"show\",\r\n" + 
				"                              \"repeatExam\": false,\r\n" + 
				"                              \"multipleCount\": \"\",\r\n" + 
				"                              \"multipleUnitScore\": \"\",\r\n" + 
				"                              \"fillBlankCount\": \"\",\r\n" + 
				"                              \"fillBlankUnitScore\": \"\",\r\n" + 
				"                              \"fillBlankType\": 1,\r\n" + 
				"                              \"shortAnswerCount\": \"\",\r\n" + 
				"                              \"shortAnswerUnitScore\": \"\",\r\n" + 
				"                              \"shortAnswerType\": 1,\r\n" + 
				"                              \"singleCount\": \"\",\r\n" + 
				"                              \"singleUnitScore\": \"\",\r\n" + 
				"                              \"trueOrFalseCount\": \"\",\r\n" + 
				"                              \"trueOrFalseUnitScore\": \"\",\r\n" + 
				"                              \"totalScore\": 30,\r\n" + 
				"                              \"answerParsing\": 2,\r\n" + 
				"                              \"passingScore\": \"30.0\",\r\n" + 
				"                              \"cutScreenCount\": -1,\r\n" + 
				"                              \"reExamRule\": 0,\r\n" + 
				"                              \"reExamNumber\": 0,\r\n" + 
				"                              \"scoreRule\": 0,\r\n" + 
				"                              \"viewRule\": 1,\r\n" + 
				"                              \"proportional\": []\r\n" + 
				"                        }\r\n" + 
				"                  ]\r\n" + 
				"            }\r\n" + 
				"      ],\r\n" + 
				"      \"stagePass\": false,\r\n" + 
				"      \"finishEvaluation\": false,\r\n" + 
				"      \"openEvaluationResult\": false,\r\n" + 
				"      \"action\": \"published\",\r\n" + 
				"    \"access_token\":\""+token+"\"\r\n" + 
				"}\r\n" + 
				"")
				.send().body();
	}
	
	
	/**   
	 * @Title: MyTaskDetailList  
	 * @Description: TODO  PC/移动端-我的学习任务详情页补考弹窗校验 
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String MyTaskDetailList(String studyPlan_exam_id){	
		return HttpRequest.get(myTask_detail_url).query("access_token", token).query("id",studyPlan_exam_id)
				.send().body();
	}
	
	/**   
	 * @Title: StudyExamQuery  
	 * @Description: TODO  学习任务数据监控-考试分析-获取考试id
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String StudyExamQuery(String study_plan_id){	
		return HttpRequest.get(study_exam_query_url(study_plan_id)).query("access_token", token)
				.send().body();
	}
	
	
	/**   
	 * @Title: StudyExamAnalyse  
	 * @Description: TODO  学习任务数据监控-考试分析-校验是否有发布补考入口
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String StudyExamAnalyse(String studyPlan_exam_id){	
		return HttpRequest.get(studyExam_analyse_url).query("access_token", token).query("planId",studyPlan_exam_id)
				.send().body();
	}
	
	
	
	/**   
	 * @Title: StudyExamAnalyse  
	 * @Description: TODO  学习任务数据监控-考试分析-校验是否有发布补考入口
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String MyTaskDetailCheck(String studyPlan_exam_id){	
		return HttpRequest.get(myTask_detail_url).query("access_token", token).query("planId",studyPlan_exam_id)
				.send().body();
	}
	
	/**   
	 * @Title: DelateExam  
	 * @Description: TODO  删除考试任务
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String DelateExam(String exam_plan_id){	
		return HttpRequest.post(delete_exam_url).query("access_token", token).query("id",exam_plan_id)
				.send().body();
	}
	
	
	/**   
	 * @Title: DelateStudyPlan  
	 * @Description: TODO  删除学习任务
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String DelateStudyPlan(String study_plan_id){	
		return HttpRequest.post(delete_study_plan_url).query("access_token", token).query("id",study_plan_id)
				.send().body();
	}

	
	
	
}
