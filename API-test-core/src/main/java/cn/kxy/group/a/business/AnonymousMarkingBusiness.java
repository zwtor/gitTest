package cn.kxy.group.a.business;

import cn.kxy.base.business.EnterpriseData;
import cn.kxy.base.business.EnterpriseDataUrl;
import cn.kxy.base.business.ExamDataUrl;
import cn.kxy.base.business.TokenData;
import cn.kxy.setting.bussiness.UserBusiness;
import com.lazy.common.utils.DateUtil;
import com.lazy.httpclient.utils.HttpRequest;

import java.net.URLEncoder;

public class AnonymousMarkingBusiness {

	public static String enterprise_url = EnterpriseDataUrl.getEnterpriseUrl();
	public static String exam_url= ExamDataUrl.getNewExamUrl();
	public static String token = TokenData.getMangerToken();
	public static String enterpriseId = EnterpriseData.getEnterpriseId();
	public static String user_id = UserBusiness.getUserId();
	public static String queryQuestionBankList_url = exam_url + "questionBank/queryQuestionBankList/";
	public static String exam_add_url = exam_url + "plan/exam/add/";
	public static String exams_check_url (String exam_id) {
    	return exam_url + "v2/"+enterpriseId+"/users/"+user_id+"/exams/"+exam_id+"/check/";
    }
	public static String question_id_url (String exam_id) {
		return exam_url + "v2/"+enterpriseId+"/users/"+user_id+"/exams/"+exam_id+"/query/";
	}
	public static String exam_detail_query_url (String exam_id) {
    	return exam_url + "v2/"+enterpriseId+"/users/"+user_id+"/exams/"+exam_id+"/query/";
    }
	public static String submit_exam_url (String exam_id) {
		return exam_url + "v2/"+enterpriseId+"/users/"+user_id+"/exams/"+exam_id+"/submit/";
	}
	public static String getExamPlanPendingList_url = exam_url + "plan/exam/getExamPlanPendingList/";
	public static String delete_exam_url = exam_url + "plan/exam/delete/";
	public static String studyPlan_add_url = enterprise_url + "plan/study/add/";
	public static String studyPlan_examId_url = enterprise_url + "plan/myTask/getOne/";
	public static String delete_study_plan_url = enterprise_url + "plan/study/delete/";
	public static String studyProject_add_url = enterprise_url + "v2/"+enterpriseId+"/study_projects/save/";
	public static String study_project_detail_url(String project_id) {
		return enterprise_url + "v2/"+enterpriseId+"/users/"+user_id+"/study_projects/"+project_id+"/query/";
	}
	public static String study_project_start_url(String projectCourse_id) {
		return enterprise_url + "v2/"+enterpriseId+"/users/"+user_id+"/study_projects/"+projectCourse_id+"/start_study/";
	}
	public static String save_or_update_base_info_url = enterprise_url + "v2/"+enterpriseId+"/employees/save_or_update_base_info/";
	public static String update_content_url = enterprise_url + "v2/"+enterpriseId+"/employees/update_content/";
	public static String update_setting_publish_url = enterprise_url + "v2/"+enterpriseId+"/employees/update_setting_publish/";
	public static String getList_url = enterprise_url + "plan/employeeTrain/getList/";
	public static String employee_train_assign_url (String assign_id) {
		return enterprise_url + "v2/"+enterpriseId+"/employee_train/"+"/"+assign_id+"/"+"/assign/";
	}
	public static String study_project_delete_url (String project_id) {
		return enterprise_url + "v2/"+enterpriseId +"/study_projects/"+project_id+"/delete/";
	}
	public static String delete_employ_train_task = enterprise_url + "/plan/employeeTrain/deleteEmployTrainTask/";
	public static String exam_markings_url (String id) {
		return enterprise_url + "v2/"+enterpriseId +"/studies/"+id+"/exam_markings/";
	}
	
	
	/**   
	 * @Title: QueryQuestionBankList  
	 * @Description: TODO  查询题库id
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String QueryQuestionBankList(String keyword){	
		return HttpRequest.get(queryQuestionBankList_url).query("access_token", token).query("keyword",keyword)
				.send().body();
	}
	
	
	/**   
	 * @Title: ExamAdd  
	 * @Description: TODO  创建考试任务-匿名阅卷
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String ExamAdd(String anonymousMarking,String markType,String beginTime,String endTime,String fillBlankCount,
			String fillBlankType,String fillBlankUnitScore,String multipleCount,String multipleUnitScore,String multipleMissedScore,
			String questionBank_id,String questionMode,String shortAnswerCount,String shortAnswerType,
			String shortAnswerUnitScore,String singleCount,String singleUnitScore,String title,String trueOrFalseCount,
			String trueOrFalseUnitScore,String proportional) throws Exception{	
		return HttpRequest.post(exam_add_url).query("access_token", token).query("anonymousMarking",anonymousMarking)
				.query("markType",markType).query("authorityRange","false")
				.query("beginTime",URLEncoder.encode(beginTime, "UTF-8"))
				.query("endTime",URLEncoder.encode(endTime, "UTF-8")).query("cheatFlag","0")
				.query("cutScreenCount","-1").query("examCertificateId","0")
				.query("examDuration","20").query("faceRecognition","off").query("fillBlankCount",fillBlankCount)
				.query("fillBlankType",fillBlankType).query("fillBlankUnitScore",fillBlankUnitScore).query("isAllIn","false")
				.query("isGetScore","false").query("multipleCount",multipleCount)
				.query("multipleUnitScore",multipleUnitScore).query("multipleMissedScore",multipleMissedScore)
				.query("passLine","100").query("passingScore","100").query("questionBankId",questionBank_id)
				.query("questionMode",questionMode).query("reExamNumber","0").query("reExamRule","0").query("repeatExam","true")
				.query("scoreRule","0").query("shortAnswerCount",shortAnswerCount).query("shortAnswerType",shortAnswerType)
				.query("shortAnswerUnitScore",shortAnswerUnitScore).query("showKnowledge","show").query("singleCount",singleCount)
				.query("singleUnitScore",singleUnitScore).query("supervisorId",user_id).query("supervisorPaper","true")
				.query("title",title)
				.query("trueOrFalseCount",trueOrFalseCount).query("trueOrFalseUnitScore",trueOrFalseUnitScore).query("userIds",user_id).query("viewRule","1")
				.query("proportional",proportional)
				.send().body();
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
	 * @Title: StudyPlanExamId  
	 * @Description: TODO  查询题库下的各试题id
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String QueryQuestionId(String exam_id){	
		return HttpRequest.get(question_id_url(exam_id)).query("access_token", token)
				.send().body();
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
	 * @Title: SubmitExam  
	 * @Description: TODO  提交试卷
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String SubmitExam(String exam_id,String blank_id,String short_id){	
		return HttpRequest.post(submit_exam_url(exam_id)).query("access_token", token).data("{\r\n" + 
				"      \"questions\": [\r\n" + 
				"            {\r\n" + 
				"                  \"id\": \""+blank_id+"\",\r\n" + 
				"                  \"answer\": [],\r\n" + 
				"                  \"answer_image\": \"\"\r\n" + 
				"            },\r\n" + 
				"            {\r\n" + 
				"                  \"id\": \""+short_id+"\",\r\n" + 
				"                  \"answer\": [],\r\n" + 
				"                  \"answer_image\": \"\"\r\n" + 
				"            }\r\n" + 
				"      ],\r\n" + 
				"      \"makeup_count\": 0,\r\n" + 
				"      \"access_token\": \""+token+"\"\r\n" + 
				"}")
				.send().body();
	}
	
	
	
	/**   
	 * @Title: GetExamPlanPendingList  
	 * @Description: TODO  阅卷页
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String GetExamPlanPendingList(String planId){	
		return HttpRequest.get(getExamPlanPendingList_url).query("access_token", token).query("planId",planId)
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
	 * @Title: StudyPlanAdd  
	 * @Description: TODO  创建学习任务-填空和简答都匿名+只匿名填空+填空和简答都不匿接口
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String StudyPlanAdd(String title,String title1,String markType1,String questionBankList,String fillBlankCount,
			String fillBlankUnitScore,String shortAnswerCount,String shortAnswerUnitScore,String anonymousMarking1,String title2,
			String markType2,String title3,String markType3,String anonymousMarking2){			
		return HttpRequest.post(studyPlan_add_url).query("access_token", token).data("{\r\n" + 
				"      \"title\": \""+title+"\",\r\n" + 
	    		"      \"beginTime\": "+DateUtil.getTimeStamp(-7, "")+",\r\n" + 
				"      \"endTime\": "+DateUtil.getTimeStamp(9, "")+",\r\n" +
				"      \"projectId\": \"\",\r\n" + 
				"      \"summary\": \"\",\r\n" + 
				"      \"sync_progress\": \"false\",\r\n" + 
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
				"      \"studyTimeLimit\": \"30\",\r\n" + 
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
				"                              \"examDuration\": 10,\r\n" + 
				"                              \"summary\": \"\",\r\n" + 
				"                              \"flag\": 2,\r\n" + 
				"                              \"mappingId\": \"\",\r\n" + 
				"                              \"courseId\": \"\",\r\n" + 
				"                              \"markType\": \""+markType1+"\",\r\n" + 
				"                              \"paperId\": \"\",\r\n" + 
				"                              \"paperTitle\": \"\",\r\n" + 
				"                              \"passLine\": 100,\r\n" + 
				"                              \"questionBankList\": \""+questionBankList+"\",\r\n" + 
				"                              \"questionMode\": 2,\r\n" + 
				"                              \"showKnowledge\": \"show\",\r\n" + 
				"                              \"repeatExam\": true,\r\n" + 
				"                              \"multipleCount\": 0,\r\n" + 
				"                              \"multipleUnitScore\": 0,\r\n" + 
				"                              \"fillBlankCount\": \""+fillBlankCount+"\",\r\n" + 
				"                              \"fillBlankUnitScore\": \""+fillBlankUnitScore+"\",\r\n" + 
				"                              \"fillBlankType\": 1,\r\n" + 
				"                              \"shortAnswerCount\": \""+shortAnswerCount+"\",\r\n" + 
				"                              \"shortAnswerUnitScore\": \""+shortAnswerUnitScore+"\",\r\n" + 
				"                              \"shortAnswerType\": 1,\r\n" + 
				"                              \"singleCount\": 0,\r\n" + 
				"                              \"singleUnitScore\": 0,\r\n" + 
				"                              \"trueOrFalseCount\": 0,\r\n" + 
				"                              \"trueOrFalseUnitScore\": 0,\r\n" + 
				"                              \"totalScore\": \"\",\r\n" + 
				"                              \"answerParsing\": 5,\r\n" + 
				"                              \"passingScore\": 60,\r\n" + 
				"                              \"cutScreenCount\": -1,\r\n" + 
				"                              \"reExamRule\": 0,\r\n" + 
				"                              \"reExamNumber\": 0,\r\n" + 
				"                              \"scoreRule\": 0,\r\n" + 
				"                              \"viewRule\": 1,\r\n" + 
				"                              \"proportional\": [],\r\n" + 
				"                              \"anonymousMarking\": \""+anonymousMarking1+"\",\r\n" + 
				"                              \"resource_lock\": false\r\n" + 
				"                        },\r\n" + 
				"                        {\r\n" + 
				"                              \"courseSort\": 1,\r\n" + 
				"                              \"title\": \""+title2+"\",\r\n" + 
				"                              \"type\": \"ex\",\r\n" + 
				"                              \"cheatFlag\": 0,\r\n" + 
				"                              \"faceRecognition\": \"off\",\r\n" + 
				"                              \"examDuration\": 10,\r\n" + 
				"                              \"summary\": \"\",\r\n" + 
				"                              \"flag\": 2,\r\n" + 
				"                              \"mappingId\": \"\",\r\n" + 
				"                              \"courseId\": \"\",\r\n" + 
				"                              \"markType\": \""+markType2+"\",\r\n" + 
				"                              \"paperId\": \"\",\r\n" + 
				"                              \"paperTitle\": \"\",\r\n" + 
				"                              \"passLine\": 100,\r\n" + 
				"                              \"questionBankList\": \""+questionBankList+"\",\r\n" + 
				"                              \"questionMode\": 2,\r\n" + 
				"                              \"showKnowledge\": \"show\",\r\n" + 
				"                              \"repeatExam\": true,\r\n" + 
				"                              \"multipleCount\": 0,\r\n" + 
				"                              \"multipleUnitScore\": 0,\r\n" + 
				"                              \"fillBlankCount\": \""+fillBlankCount+"\",\r\n" + 
				"                              \"fillBlankUnitScore\": \""+fillBlankUnitScore+"\",\r\n" + 
				"                              \"fillBlankType\":  1,\r\n" + 
				"                              \"shortAnswerCount\": \""+shortAnswerCount+"\",\r\n" + 
				"                              \"shortAnswerUnitScore\": \""+shortAnswerUnitScore+"\",\r\n" + 
				"                              \"shortAnswerType\": 1,\r\n" + 
				"                              \"singleCount\": 0,\r\n" + 
				"                              \"singleUnitScore\": 0,\r\n" + 
				"                              \"trueOrFalseCount\": 0,\r\n" + 
				"                              \"trueOrFalseUnitScore\": 0,\r\n" + 
				"                              \"totalScore\": \"\",\r\n" + 
				"                              \"answerParsing\": 5,\r\n" + 
				"                              \"passingScore\": 60,\r\n" + 
				"                              \"cutScreenCount\": -1,\r\n" + 
				"                              \"reExamRule\": 0,\r\n" + 
				"                              \"reExamNumber\": 0,\r\n" + 
				"                              \"scoreRule\": 0,\r\n" + 
				"                              \"viewRule\": 1,\r\n" + 
				"                              \"proportional\": [],\r\n" + 
				"                              \"anonymousMarking\": \""+anonymousMarking1+"\",\r\n" + 
				"                              \"resource_lock\": false\r\n" + 
				"                        },\r\n" + 
				"                        {\r\n" + 
				"                              \"courseSort\": 2,\r\n" + 
				"                              \"title\": \""+title3+"\",\r\n" + 
				"                              \"type\": \"ex\",\r\n" + 
				"                              \"cheatFlag\": 0,\r\n" + 
				"                              \"faceRecognition\": \"off\",\r\n" + 
				"                              \"examDuration\": 20,\r\n" + 
				"                              \"summary\": \"\",\r\n" + 
				"                              \"flag\": 2,\r\n" + 
				"                              \"mappingId\": \"\",\r\n" + 
				"                              \"courseId\": \"\",\r\n" + 
				"                              \"markType\": \""+markType3+"\",\r\n" + 
				"                              \"paperId\": \"\",\r\n" + 
				"                              \"paperTitle\": \"\",\r\n" + 
				"                              \"passLine\": 100,\r\n" + 
				"                              \"questionBankList\": \""+questionBankList+"\",\r\n" + 
				"                              \"questionMode\": 2,\r\n" + 
				"                              \"showKnowledge\": \"show\",\r\n" + 
				"                              \"repeatExam\": true,\r\n" + 
				"                              \"fillBlankCount\": \""+fillBlankCount+"\",\r\n" + 
				"                              \"fillBlankUnitScore\": \""+fillBlankUnitScore+"\",\r\n" + 
				"                              \"shortAnswerCount\": \""+shortAnswerCount+"\",\r\n" + 
				"                              \"shortAnswerUnitScore\": \""+shortAnswerUnitScore+"\",\r\n" + 
				"                              \"fillBlankType\": 1,\r\n" + 
				"                              \"shortAnswerCount\": 1,\r\n" + 
				"                              \"shortAnswerUnitScore\": 10,\r\n" + 
				"                              \"shortAnswerType\": 1,\r\n" + 
				"                              \"singleCount\": 0,\r\n" + 
				"                              \"singleUnitScore\": 0,\r\n" + 
				"                              \"trueOrFalseCount\": 0,\r\n" + 
				"                              \"trueOrFalseUnitScore\": 0,\r\n" + 
				"                              \"totalScore\": \"\",\r\n" + 
				"                              \"answerParsing\": 5,\r\n" + 
				"                              \"passingScore\": 60,\r\n" + 
				"                              \"cutScreenCount\": -1,\r\n" + 
				"                              \"reExamRule\": 0,\r\n" + 
				"                              \"reExamNumber\": 0,\r\n" + 
				"                              \"scoreRule\": 0,\r\n" + 
				"                              \"viewRule\": 1,\r\n" + 
				"                              \"proportional\": [],\r\n" + 
				"                              \"anonymousMarking\": \""+anonymousMarking2+"\",\r\n" + 
				"                              \"resource_lock\": false\r\n" + 
				"                        }\r\n" + 
				"                  ]\r\n" + 
				"            }\r\n" + 
				"      ],\r\n" + 
				"      \"stagePass\": false,\r\n" + 
				"      \"finishEvaluation\": false,\r\n" + 
				"      \"openEvaluationResult\": false,\r\n" + 
				"      \"action\": \"published\",\r\n" + 
				"      \"free\": true,\r\n" + 
				"      \"official_price_str\": \"\",\r\n" + 
				"      \"access_token\": \""+token+"\"\r\n" + 
				"}")
				.send().body();	
	}
	
	
	/**   
	 * @Title: StudyPlanExamId  
	 * @Description: TODO  获取学习任务里的考试id
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String StudyPlanExamId(String studyPlan_id){	
		return HttpRequest.get(studyPlan_examId_url).query("access_token", token).query("id",studyPlan_id)
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
	
	
	/**   
	 * @Title: StudyProjectSaveBaseInfo  
	 * @Description: TODO 创建学习项目第一步
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String StudyProjectSaveBaseInfo(String title,String class_id,String style_type,String cover,String base_cover) {		
		return HttpRequest.post(studyProject_add_url).query("access_token", token).data("{\r\n" + 
				"      \"save_step\": \"base_info\",\r\n" + 
				"      \"base_info\": {\r\n" + 
				"            \"title\": \""+title+"\",\r\n" + 
				"            \"lecturer_id\": 0,\r\n" + 
				"            \"summary\": \"\",\r\n" + 
				"            \"classify\": \""+class_id+"\",\r\n" + 
				"            \"style_type\": \""+style_type+"\",\r\n" + 
				"            \"cover\": \""+cover+"\",\r\n" + 
				"            \"base_cover\": \""+base_cover+"\",\r\n" + 
				"            \"cover_type\": 1,\r\n" + 
				"            \"ding_img_url\": \"\"\r\n" + 
				"      },\r\n" + 
				"    \"access_token\":\""+token+"\"\r\n" + 
				"}\r\n" + 
				"")
				.send().body();
	}
	
	
	/**   
	 * @Title: StudyProjectSaveStageContent  
	 * @Description: TODO 创建学习项目第二步
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String StudyProjectSaveStageContent(String project_id,String title1,String mark_type1,String question_banks,
			String fill_blank_count,String fill_blank_unit_score,String short_answer_count,String short_answer_unit_score,
			String anonymous_marking1,String title2,String mark_type2,String title3,String mark_type3,String anonymous_marking2) {
		return HttpRequest.post(studyProject_add_url).query("access_token", token).data("{\r\n" + 
				"      \"save_step\": \"stage_content\",\r\n" + 
				"      \"id\": \""+project_id+"\",\r\n" + 
				"      \"stage_content\": {\r\n" + 
				"            \"stages\": [\r\n" + 
				"                  {\r\n" + 
				"                        \"id\": \"\",\r\n" + 
				"                        \"content\": \"\",\r\n" + 
				"                        \"stage_name\": \"阶段1\",\r\n" + 
				"                        \"is_order\": false,\r\n" + 
				"                        \"sort\": 1,\r\n" + 
				"                        \"progress\": \"\",\r\n" + 
				"                        \"exam_times\": 1,\r\n" + 
				"                        \"operation_times\": \"\",\r\n" + 
				"                        \"finish_evaluation\": \"false\",\r\n" + 
				"                        \"is_get_score\": \"\",\r\n" + 
				"                        \"certificate_id\": \"\",\r\n" + 
				"                        \"resources\": [\r\n" + 
				"                              {\r\n" + 
				"                                    \"type\": 6,\r\n" + 
				"                                    \"sort\": 0,\r\n" + 
				"                                    \"course_id\": \"\",\r\n" + 
				"                                    \"exam\": {\r\n" + 
				"                                          \"title\": \""+title1+"\",\r\n" + 
				"                                          \"cheat_flag\": 0,\r\n" + 
				"                                          \"exam_duration\": 10,\r\n" + 
				"                                          \"face_recognition\": \"off\",\r\n" + 
				"                                          \"summary\": \"\",\r\n" + 
				"                                          \"mark_type\": \""+mark_type1+"\",\r\n" + 
				"                                          \"paper_id\": \"\",\r\n" + 
				"                                          \"pass_line\": 100,\r\n" + 
				"                                          \"question_banks\": [\r\n" + 
				"                                                \""+question_banks+"\"\r\n" + 
				"                                          ],\r\n" + 
				"                                          \"question_mode\": 2,\r\n" + 
				"                                          \"show_knowledge\": \"show\",\r\n" + 
				"                                          \"repeat_exam\": true,\r\n" + 
				"                                          \"multiple_count\": 0,\r\n" + 
				"                                          \"multiple_unit_score\": 0,\r\n" + 
				"                                          \"fill_blank_count\": \""+fill_blank_count+"\",\r\n" + 
				"                                          \"fill_blank_unit_score\":\""+fill_blank_unit_score+"\",\r\n" + 
				"                                          \"fill_blank_type\": 1,\r\n" + 
				"                                          \"short_answer_count\": \""+short_answer_count+"\",\r\n" + 
				"                                          \"short_answer_unit_score\": \""+short_answer_unit_score+"\",\r\n" + 
				"                                          \"short_answer_type\": 1,\r\n" + 
				"                                          \"single_count\": 0,\r\n" + 
				"                                          \"single_unit_score\": 0,\r\n" + 
				"                                          \"true_or_false_count\": 0,\r\n" + 
				"                                          \"true_or_false_unit_score\": 0,\r\n" + 
				"                                          \"total_score\": \"\",\r\n" + 
				"                                          \"answer_parsing\": 5,\r\n" + 
				"                                          \"passing_score\": 60,\r\n" + 
				"                                          \"cut_screen_count\": -1,\r\n" + 
				"                                          \"re_exam_rule\": 0,\r\n" + 
				"                                          \"re_exam_number\": 0,\r\n" + 
				"                                          \"score_rule\": 0,\r\n" + 
				"                                          \"view_rule\": 1,\r\n" + 
				"                                          \"proportional\": [],\r\n" + 
				"                                          \"anonymous_marking\": \""+anonymous_marking1+"\"\r\n" + 
				"                                    },\r\n" + 
				"                                    \"resource_lock\": false\r\n" + 
				"                              },\r\n" + 
				"                              {\r\n" + 
				"                                    \"type\": 6,\r\n" + 
				"                                    \"sort\": 1,\r\n" + 
				"                                    \"course_id\": \"\",\r\n" + 
				"                                    \"exam\": {\r\n" + 
				"                                          \"title\": \""+title2+"\",\r\n" + 
				"                                          \"cheat_flag\": 0,\r\n" + 
				"                                          \"exam_duration\": 20,\r\n" + 
				"                                          \"face_recognition\": \"off\",\r\n" + 
				"                                          \"summary\": \"\",\r\n" + 
				"                                          \"mark_type\": \""+mark_type2+"\",\r\n" + 
				"                                          \"paper_id\": \"\",\r\n" + 
				"                                          \"pass_line\": 100,\r\n" + 
				"                                          \"question_banks\": [\r\n" + 
				"                                                \""+question_banks+"\"\r\n" + 
				"                                          ],\r\n" + 
				"                                          \"question_mode\": 2,\r\n" + 
				"                                          \"show_knowledge\": \"show\",\r\n" + 
				"                                          \"repeat_exam\": true,\r\n" + 
				"                                          \"multiple_count\": 0,\r\n" + 
				"                                          \"multiple_unit_score\": 0,\r\n" + 
				"                                          \"fill_blank_count\": \""+fill_blank_count+"\",\r\n" + 
				"                                          \"fill_blank_unit_score\":\""+fill_blank_unit_score+"\",\r\n" + 
				"                                          \"fill_blank_type\": 1,\r\n" + 
				"                                          \"short_answer_count\": \""+short_answer_count+"\",\r\n" + 
				"                                          \"short_answer_unit_score\": \""+short_answer_unit_score+"\",\r\n" + 
				"                                          \"short_answer_type\": 1,\r\n" + 
				"                                          \"single_count\": 0,\r\n" + 
				"                                          \"single_unit_score\": 0,\r\n" + 
				"                                          \"true_or_false_count\": 0,\r\n" + 
				"                                          \"true_or_false_unit_score\": 0,\r\n" + 
				"                                          \"total_score\": \"\",\r\n" + 
				"                                          \"answer_parsing\": 5,\r\n" + 
				"                                          \"passing_score\": 60,\r\n" + 
				"                                          \"cut_screen_count\": -1,\r\n" + 
				"                                          \"re_exam_rule\": 0,\r\n" + 
				"                                          \"re_exam_number\": 0,\r\n" + 
				"                                          \"score_rule\": 0,\r\n" + 
				"                                          \"view_rule\": 1,\r\n" + 
				"                                          \"proportional\": [],\r\n" + 
				"                                          \"anonymous_marking\": \""+anonymous_marking1+"\"\r\n" + 
				"                                    },\r\n" + 
				"                                    \"resource_lock\": false\r\n" + 
				"                              },\r\n" + 
				"                              {\r\n" + 
				"                                    \"type\": 6,\r\n" + 
				"                                    \"sort\": 2,\r\n" + 
				"                                    \"course_id\": \"\",\r\n" + 
				"                                    \"exam\": {\r\n" + 
				"                                          \"title\": \""+title3+"\",\r\n" + 
				"                                          \"cheat_flag\": 0,\r\n" + 
				"                                          \"exam_duration\": 20,\r\n" + 
				"                                          \"face_recognition\": \"off\",\r\n" + 
				"                                          \"summary\": \"\",\r\n" + 
				"                                          \"mark_type\": \""+mark_type3+"\",\r\n" + 
				"                                          \"paper_id\": \"\",\r\n" + 
				"                                          \"pass_line\": 100,\r\n" + 
				"                                          \"question_banks\": [\r\n" + 
				"                                                \""+question_banks+"\"\r\n" + 
				"                                          ],\r\n" + 
				"                                          \"question_mode\": 2,\r\n" + 
				"                                          \"show_knowledge\": \"show\",\r\n" + 
				"                                          \"repeat_exam\": true,\r\n" + 
				"                                          \"multiple_count\": 0,\r\n" + 
				"                                          \"multiple_unit_score\": 0,\r\n" + 
				"                                          \"fill_blank_count\": \""+fill_blank_count+"\",\r\n" + 
				"                                          \"fill_blank_unit_score\":\""+fill_blank_unit_score+"\",\r\n" + 
				"                                          \"fill_blank_type\": 1,\r\n" + 
				"                                          \"short_answer_count\": \""+short_answer_count+"\",\r\n" + 
				"                                          \"short_answer_unit_score\": \""+short_answer_unit_score+"\",\r\n" + 
				"                                          \"short_answer_type\": 1,\r\n" + 
				"                                          \"single_count\": 0,\r\n" + 
				"                                          \"single_unit_score\": 0,\r\n" + 
				"                                          \"true_or_false_count\": 0,\r\n" + 
				"                                          \"true_or_false_unit_score\": 0,\r\n" + 
				"                                          \"total_score\": \"\",\r\n" + 
				"                                          \"answer_parsing\": 5,\r\n" + 
				"                                          \"passing_score\": 60,\r\n" + 
				"                                          \"cut_screen_count\": -1,\r\n" + 
				"                                          \"re_exam_rule\": 0,\r\n" + 
				"                                          \"re_exam_number\": 0,\r\n" + 
				"                                          \"score_rule\": 0,\r\n" + 
				"                                          \"view_rule\": 1,\r\n" + 
				"                                          \"proportional\": [],\r\n" + 
				"                                          \"anonymous_marking\": \""+anonymous_marking2+"\"\r\n" + 
				"                                    },\r\n" + 
				"                                    \"resource_lock\": false\r\n" + 
				"                              }\r\n" + 
				"                        ]\r\n" + 
				"                  }\r\n" + 
				"            ]\r\n" + 
				"      },\r\n" + 
				"      \"access_token\": \""+token+"\"\r\n" + 
				"}")
				.send().body();
	}
	
	
	/**   
	 * @Title: StudyProjectSaveSettings  
	 * @Description: TODO 创建学习项目第三步
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String StudyProjectSaveSettings(String project_id) {		
		return HttpRequest.post(studyProject_add_url).query("access_token", token).data("{\r\n" + 
				"      \"save_step\": \"settings\",\r\n" + 
				"      \"id\": \""+project_id+"\",\r\n" + 
				"      \"settings\": {\r\n" + 
				"            \"study_time_limit\": 0,\r\n" + 
				"            \"face_recognition\": \"off\",\r\n" + 
				"            \"progress\": 0,\r\n" + 
				"            \"times\": 1,\r\n" + 
				"            \"operation_times\": \"\",\r\n" + 
				"            \"finish_evaluation\": false,\r\n" + 
				"            \"certificate_id\": \"\",\r\n" + 
				"            \"is_get_score\": true,\r\n" + 
				"            \"study_score\": {\r\n" + 
				"                  \"finish_score\": 0,\r\n" + 
				"                  \"unfinish_score\": 0\r\n" + 
				"            },\r\n" + 
				"            \"is_all\": 1,\r\n" + 
				"            \"user_ids\": \"\",\r\n" + 
				"            \"department_ids\": \"\",\r\n" + 
				"            \"group_ids\": \"\",\r\n" + 
				"            \"post_ids\": \"\",\r\n" + 
				"            \"supervisor_ids\": [\r\n" + 
				"                  \""+user_id+"\"\r\n" + 
				"            ],\r\n" + 
				"            \"supervisor_paper\": true,\r\n" + 
				"            \"authority_range\": false,\r\n" + 
				"            \"supervisor_department_ids\": [],\r\n" + 
				"            \"enroll_audit\": \"un_need\",\r\n" + 
				"            \"enroll_limit\": \"false\",\r\n" + 
				"            \"limit_count\": 1,\r\n" + 
				"            \"open_evaluation_result\": false,\r\n" + 
				"            \"is_free\": true,\r\n" + 
				"            \"official_price\": \"\",\r\n" + 
				"            \"preferential_price\": \"\",\r\n" + 
				"            \"open_learning_group\": \"false\"\r\n" + 
				"      },\r\n" + 
				"      \"access_token\": \""+token+"\"\r\n" + 
				"}")
				.send().body();
	}
	
	
	/**   
	 * @Title: StudyProjectsDetail   
	 * @Description: TODO  学习项目详情页+校验合格标准
	 * @param: @param keyword
	 * @param: @param type
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */  
	public static String StudyProjectsDetail(String project_id) {
		return HttpRequest.get(study_project_detail_url(project_id)).query("access_token", token)
				.send().body();	
	}
	
	
	/**   
	 * @Title: StudyProjectsStart 
	 * @Description: TODO  学习项目答题前的start接口
	 * @param: @param keyword
	 * @param: @param type
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */  
	public static String StudyProjectsStart(String projectCourse_id) {
		return HttpRequest.post(study_project_start_url(projectCourse_id)).query("access_token", token).data("{\r\n" + 
				"      \"access_token\": \""+token+"\"\r\n" + 
				"}")
				.send().body();	
	}
	
	
	/**   
	 * @Title: SaveOrUpdateBaseInfo  
	 * @Description: TODO  新员工培训第一步
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String SaveOrUpdateBaseInfo(String train_name,String current_time) {	
		
		return HttpRequest.post(save_or_update_base_info_url).query("access_token", token).data("{\r\n" + 
				"  \"train_name\":\""+train_name+"\",\r\n" + 
				"  \"train_limit\":\"2\",\r\n" + 
				"  \"summary\":\"\",\r\n" + 
				"  \"classify_id\":\"\",\r\n" + 
				"  \"effect_time\":"+current_time+",\r\n" + 
				"  \"access_token\": \""+token+"\"\r\n" + 
				"}")
				.send().body();		
	}
	
	
	/**   
	 * @Title: UpdateContent  
	 * @Description: TODO  新员工培训第二步
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String UpdateContent(String employee_train_id,String title1,String mark_type1,String question_banks,
			String fill_blank_count,String fill_blank_unit_score,String short_answer_count,String short_answer_unit_score,
			String anonymous_marking1,String title2,String mark_type2,String title3,String mark_type3,String anonymous_marking2) {
		return HttpRequest.post(update_content_url).query("access_token", token).data("{\r\n" + 
				"      \"plan_id\": \""+employee_train_id+"\",\r\n" + 
				"      \"stage_json\": [\r\n" + 
				"            {\r\n" + 
				"                  \"content\": \"\",\r\n" + 
				"                  \"stage_name\": \"阶段1\",\r\n" + 
				"                  \"is_order\": false,\r\n" + 
				"                  \"stage_sort\": 1,\r\n" + 
				"                  \"resources\": [\r\n" + 
				"                        {\r\n" + 
				"                              \"exam\": {\r\n" + 
				"                                    \"id\": \"\",\r\n" + 
				"                                    \"title\": \""+title1+"\",\r\n" + 
				"                                    \"cheat_flag\": 0,\r\n" + 
				"                                    \"exam_duration\": 10,\r\n" + 
				"                                    \"face_recognition\": \"off\",\r\n" + 
				"                                    \"summary\": \"\",\r\n" + 
				"                                    \"mark_type\": \""+mark_type1+"\",\r\n" + 
				"                                    \"paper_id\": \"\",\r\n" + 
				"                                    \"paper_name\": \"\",\r\n" + 
				"                                    \"pass_line\": 100,\r\n" + 
				"                                    \"question_banks\": [\r\n" + 
				"                                          \""+question_banks+"\"\r\n" + 
				"                                    ],\r\n" + 
				"                                    \"question_mode\": 2,\r\n" + 
				"                                    \"show_knowledge\": \"show\",\r\n" + 
				"                                    \"repeat_exam\": true,\r\n" + 
				"                                    \"multiple_count\": 0,\r\n" + 
				"                                    \"multiple_unit_score\": 0,\r\n" + 
				"                                    \"fill_blank_count\": \""+fill_blank_count+"\",\r\n" + 
				"                                    \"fill_blank_unit_score\":\""+fill_blank_unit_score+"\",\r\n" + 
				"                                    \"fill_blank_type\": 1,\r\n" + 
				"                                    \"short_answer_type\": 1,\r\n" + 
				"                                    \"short_answer_count\": \""+short_answer_count+"\",\r\n" + 
				"                                    \"short_answer_unit_score\": \""+short_answer_unit_score+"\",\r\n" + 
				"                                    \"single_count\": 0,\r\n" + 
				"                                    \"single_unit_score\": 0,\r\n" + 
				"                                    \"true_or_false_count\": 0,\r\n" + 
				"                                    \"true_or_false_unit_score\": 0,\r\n" + 
				"                                    \"total_score\": \"\",\r\n" + 
				"                                    \"answer_parsing\": 5,\r\n" + 
				"                                    \"passing_score\": 60,\r\n" + 
				"                                    \"cut_screen_count\": -1,\r\n" + 
				"                                    \"re_exam_rule\": 0,\r\n" + 
				"                                    \"re_exam_number\": 0,\r\n" + 
				"                                    \"score_rule\": 0,\r\n" + 
				"                                    \"view_rule\": 1,\r\n" + 
				"                                    \"proportional\": [],\r\n" + 
				"                                    \"anonymous_marking\": \""+anonymous_marking1+"\"\r\n" + 
				"                              },\r\n" + 
				"                              \"flag\": 2,\r\n" + 
				"                              \"course_sort\": 0,\r\n" + 
				"                              \"course_id\": \"\",\r\n" + 
				"                              \"course_type\": 6\r\n" + 
				"                        },\r\n" + 
				"                        {\r\n" + 
				"                              \"exam\": {\r\n" + 
				"                                    \"id\": \"\",\r\n" + 
				"                                    \"title\": \""+title2+"\",\r\n" + 
				"                                    \"cheat_flag\": 0,\r\n" + 
				"                                    \"exam_duration\": 10,\r\n" + 
				"                                    \"face_recognition\": \"off\",\r\n" + 
				"                                    \"summary\": \"\",\r\n" + 
				"                                    \"mark_type\": \""+mark_type2+"\",\r\n" + 
				"                                    \"paper_id\": \"\",\r\n" + 
				"                                    \"paper_name\": \"\",\r\n" + 
				"                                    \"pass_line\": 100,\r\n" + 
				"                                    \"question_banks\": [\r\n" + 
				"                                          \""+question_banks+"\"\r\n" + 
				"                                    ],\r\n" + 
				"                                    \"question_mode\": 2,\r\n" + 
				"                                    \"show_knowledge\": \"show\",\r\n" + 
				"                                    \"repeat_exam\": true,\r\n" + 
				"                                    \"multiple_count\": 0,\r\n" + 
				"                                    \"multiple_unit_score\": 0,\r\n" + 
				"                                    \"fill_blank_count\": \""+fill_blank_count+"\",\r\n" + 
				"                                    \"fill_blank_unit_score\":\""+fill_blank_unit_score+"\",\r\n" + 
				"                                    \"fill_blank_type\": 1,\r\n" + 
				"                                    \"short_answer_type\": 1,\r\n" + 
				"                                    \"short_answer_count\": \""+short_answer_count+"\",\r\n" + 
				"                                    \"short_answer_unit_score\": \""+short_answer_unit_score+"\",\r\n" + 
				"                                    \"single_count\": 0,\r\n" + 
				"                                    \"single_unit_score\": 0,\r\n" + 
				"                                    \"true_or_false_count\": 0,\r\n" + 
				"                                    \"true_or_false_unit_score\": 0,\r\n" + 
				"                                    \"total_score\": \"\",\r\n" + 
				"                                    \"answer_parsing\": 5,\r\n" + 
				"                                    \"passing_score\": 60,\r\n" + 
				"                                    \"cut_screen_count\": -1,\r\n" + 
				"                                    \"re_exam_rule\": 0,\r\n" + 
				"                                    \"re_exam_number\": 0,\r\n" + 
				"                                    \"score_rule\": 0,\r\n" + 
				"                                    \"view_rule\": 1,\r\n" + 
				"                                    \"proportional\": [],\r\n" + 
				"                                    \"anonymous_marking\": \""+anonymous_marking1+"\"\r\n" + 
				"                              },\r\n" + 
				"                              \"flag\": 2,\r\n" + 
				"                              \"course_sort\": 1,\r\n" + 
				"                              \"course_id\": \"\",\r\n" + 
				"                              \"course_type\": 6\r\n" + 
				"                        },\r\n" + 
				"                        {\r\n" + 
				"                              \"exam\": {\r\n" + 
				"                                    \"id\": \"\",\r\n" + 
				"                                    \"title\": \""+title3+"\",\r\n" + 
				"                                    \"cheat_flag\": 0,\r\n" + 
				"                                    \"exam_duration\": 10,\r\n" + 
				"                                    \"face_recognition\": \"off\",\r\n" + 
				"                                    \"summary\": \"\",\r\n" + 
				"                                    \"mark_type\": \""+mark_type3+"\",\r\n" + 
				"                                    \"paper_id\": \"\",\r\n" + 
				"                                    \"paper_name\": \"\",\r\n" + 
				"                                    \"pass_line\": 100,\r\n" + 
				"                                    \"question_banks\": [\r\n" + 
				"                                          \""+question_banks+"\"\r\n" + 
				"                                    ],\r\n" + 
				"                                    \"question_mode\": 2,\r\n" + 
				"                                    \"show_knowledge\": \"show\",\r\n" + 
				"                                    \"repeat_exam\": true,\r\n" + 
				"                                    \"multiple_count\": 0,\r\n" + 
				"                                    \"multiple_unit_score\": 0,\r\n" + 
				"                                    \"fill_blank_count\": \""+fill_blank_count+"\",\r\n" + 
				"                                    \"fill_blank_unit_score\":\""+fill_blank_unit_score+"\",\r\n" + 
				"                                    \"fill_blank_type\": 1,\r\n" + 
				"                                    \"short_answer_type\": 1,\r\n" + 
				"                                    \"short_answer_count\": \""+short_answer_count+"\",\r\n" + 
				"                                    \"short_answer_unit_score\": \""+short_answer_unit_score+"\",\r\n" + 
				"                                    \"single_count\": 0,\r\n" + 
				"                                    \"single_unit_score\": 0,\r\n" + 
				"                                    \"true_or_false_count\": 0,\r\n" + 
				"                                    \"true_or_false_unit_score\": 0,\r\n" + 
				"                                    \"total_score\": \"\",\r\n" + 
				"                                    \"answer_parsing\": 5,\r\n" + 
				"                                    \"passing_score\": 60,\r\n" + 
				"                                    \"cut_screen_count\": -1,\r\n" + 
				"                                    \"re_exam_rule\": 0,\r\n" + 
				"                                    \"re_exam_number\": 0,\r\n" + 
				"                                    \"score_rule\": 0,\r\n" + 
				"                                    \"view_rule\": 1,\r\n" + 
				"                                    \"proportional\": [],\r\n" + 
				"                                    \"anonymous_marking\": \""+anonymous_marking2+"\"\r\n" + 
				"                              },\r\n" + 
				"                              \"flag\": 2,\r\n" + 
				"                              \"course_sort\": 2,\r\n" + 
				"                              \"course_id\": \"\",\r\n" + 
				"                              \"course_type\": 6\r\n" + 
				"                        }\r\n" + 
				"                  ]\r\n" + 
				"            }\r\n" + 
				"      ],\r\n" + 
				"      \"stage_pass\": false,\r\n" + 
				"      \"sync_progress\": \"false\",\r\n" + 
				"      \"access_token\": \""+token+"\"\r\n" + 
				"}")
				.send().body();
	}
	
	/**   
	 * @Title: UpdateSettingPublish  
	 * @Description: TODO  新员工培训第三步
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String UpdateSettingPublish(String employee_train_id) {		
		return HttpRequest.post(update_setting_publish_url).query("access_token", token).data("{\r\n" + 
				"      \"plan_id\": \""+employee_train_id+"\",\r\n" + 
				"      \"save_step\": 3,\r\n" + 
				"      \"override_sub_dept\": 1,\r\n" + 
				"      \"is_new_employee\": false,\r\n" + 
				"      \"department_ids\": \"1\",\r\n" + 
				"      \"post_ids\": \"\",\r\n" + 
				"      \"study_time_limit\": \"30\",\r\n" + 
				"      \"face_recognition\": \"off\",\r\n" + 
				"      \"progress\": 0,\r\n" + 
				"      \"times\": 1,\r\n" + 
				"      \"operation_times\": \"\",\r\n" + 
				"      \"certificate_id\": \"\",\r\n" + 
				"      \"study_score\": \"{\\\"finishScore\\\":0,\\\"unFinishScore\\\":0}\",\r\n" + 
				"      \"supervisor_id\": \""+user_id+"\",\r\n" + 
				"      \"supervisor_paper\": true,\r\n" + 
				"      \"is_get_score\": true,\r\n" + 
				"      \"access_token\": \""+token+"\"\r\n" + 
				"}")
				.send().body();
	}
	
	
	/**   
	 * @Title: GetList  
	 * @Description: TODO  获取新员工培训任务列表
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String GetList(String keyword) {		
		return HttpRequest.get(getList_url).query("access_token", token).query("keyword",keyword)
				.send().body();
	}
	
	/**   
	 * @Title: EmployeeTrainAssign  
	 * @Description: TODO  新员工培训指派
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String EmployeeTrainAssign(String assign_id) {		
		return HttpRequest.post(employee_train_assign_url(assign_id)).query("access_token", token).data("{\r\n" + 
				"    \"group_ids\": [],\r\n" + 
				"    \"user_ids\": [\r\n" + 
				"        \""+user_id+"\"\r\n" + 
				"    ],\r\n" + 
				"    \"department_ids\": [],\r\n" + 
				"    \"post_ids\": [],\r\n" + 
				"    \"access_token\":\""+token+"\"\r\n" + 
				"}")
				.send().body();
	}
	
	
	
	/**   
	 * @Title: DeleteProject  
	 * @Description: TODO 删除学习项目
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String DeleteProject(String project_id) {		
		return HttpRequest.post(study_project_delete_url(project_id)).query("access_token", token).send().body();
	}
	
	
	/**   
	 * @Title: DeleteEmployTrainTask  
	 * @Description: TODO  删除新员工培训任务
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */
	public static String DeleteEmployTrainTask(String planId) {
		return HttpRequest.post(delete_employ_train_task).query("access_token", token).query("planId",planId).
				send().body();
	}
	
	
	/**   
	 * @Title: ExamMarkings  
	 * @Description: TODO  待阅卷弹窗匿名校验
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */
	public static String ExamMarkings(String id) {
		return HttpRequest.get(exam_markings_url(id)).query("access_token", token)
				.send().body();
	}
	

	
}
