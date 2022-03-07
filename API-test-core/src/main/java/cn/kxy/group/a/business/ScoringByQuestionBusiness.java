package cn.kxy.group.a.business;

import cn.kxy.base.business.EnterpriseData;
import cn.kxy.base.business.EnterpriseDataUrl;
import cn.kxy.base.business.ExamDataUrl;
import cn.kxy.base.business.TokenData;
import cn.kxy.setting.bussiness.UserBusiness;
import com.lazy.common.utils.DateUtil;
import com.lazy.httpclient.utils.HttpRequest;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.List;

public class ScoringByQuestionBusiness {

	public static String enterprise_url = EnterpriseDataUrl.getEnterpriseUrl();
	public static String exam_url= ExamDataUrl.getNewExamUrl();
	public static String token = TokenData.getMangerToken();
	public static String enterpriseId = EnterpriseData.getEnterpriseId();
	public static String user_id = UserBusiness.getUserId();
	public static String queryQuestionBankList_url = exam_url + "questionBank/queryQuestionBankList/";
	public static String studyPlan_add_url = enterprise_url + "plan/study/add/";
	public static String studyPlan_examId_url = enterprise_url + "plan/myTask/getOne/";
	public static String question_id_url (String studyPlan_ExamId) {
		return exam_url + "v2/"+enterpriseId+"/users/"+user_id+"/exams/"+studyPlan_ExamId+"/query/";
	}
	public static String submit_exam_url (String studyPlan_ExamId) {
		return exam_url + "v2/"+enterpriseId+"/users/"+user_id+"/exams/"+studyPlan_ExamId+"/submit/";
	}
	public static String exam_add_url = exam_url + "plan/exam/add/";
	public static String query_editExam_url (String exam_plan_id) {
		return exam_url + "v2/"+enterpriseId+"/exams/"+exam_plan_id;
	}
	public static String result_score_url (String exam_plan_id) {
		return exam_url + "v2/"+enterpriseId+"/exams/"+exam_plan_id+"/result/";
	}
	public static String exam_edit_url = exam_url + "plan/exam/edit/";
	public static String query_exams_url (String exam_plan_id) {
		return exam_url + "v2/"+enterpriseId+"/users/"+user_id+"/exams/"+exam_plan_id+"/query/";
	}
	public static String query_exams_score_url = exam_url + "v2/"+enterpriseId+"/users/"+user_id+"/exams/";
	public static String delete_exam_url = exam_url + "plan/exam/delete/";
    public static String delete_study_plan_url = enterprise_url + "plan/study/delete/";
    public static String studyProject_add_url = enterprise_url + "v2/"+enterpriseId+"/study_projects/save/";
    public static String study_projects_query_url = enterprise_url + "course/queryCourseByPage/";
    public static String study_project_detail_url(String project_id) {
		return enterprise_url + "v2/"+enterpriseId+"/users/"+user_id+"/study_projects/"+project_id+"/query/";
	}
    public static String study_project_start_url(String projectCourse_id) {
		return enterprise_url + "v2/"+enterpriseId+"/users/"+user_id+"/study_projects/"+projectCourse_id+"/start_study/";
	}
    public static String study_project_delete_url (String project_id) {
		return enterprise_url + "v2/"+enterpriseId +"/study_projects/"+project_id+"/delete/";
	}
    public static String qualifications_add_url = enterprise_url + "v1/qualifications/";
    public static String certificate_list_url = enterprise_url + "certificate/getList/";
    public static String qualifications_exam_url (String qualifications_id) {
		return enterprise_url + "v2/"+enterpriseId +"/qualifications/"+qualifications_id+"/items/";
	}
    public static String qualifications_monitors_url (String qualifications_id) {
		return enterprise_url + "v2/"+enterpriseId +"/qualifications/"+qualifications_id+"/monitors/";
	}
    public static String detele_qualifications_url (String qualifications_id) {
		return enterprise_url + "v1/qualifications/"+qualifications_id +"/delete/";
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
	 * @Title: StudyPlanAdd  
	 * @Description: TODO  创建有考试的学习任务-按题给分的随机试卷模式
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String StudyPlanAdd(String title,String questionBank_id,String multipleCount,String multipleUnitScore,
			String fillBlankCount,String fillBlankUnitScore,String fillBlankType,String shortAnswerCount,
			String shortAnswerUnitScore,String shortAnswerType,String singleCount,String singleUnitScore,String trueOrFalseCount,
			String trueOrFalseUnitScore,String proportional){			
		return HttpRequest.post(studyPlan_add_url).query("access_token", token).data("{\r\n" + 
				"      \"title\": \""+title+"\",\r\n" + 
	    		"      \"beginTime\": "+DateUtil.getTimeStamp(-7, "")+",\r\n" + 
				"      \"endTime\": "+DateUtil.getTimeStamp(9, "")+",\r\n" +
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
				"                              \"title\": \"有考试\",\r\n" + 
				"                              \"type\": \"ex\",\r\n" + 
				"                              \"cheatFlag\": 0,\r\n" + 
				"                              \"faceRecognition\": \"off\",\r\n" + 
				"                              \"examDuration\": 20,\r\n" + 
				"                              \"summary\": \"\",\r\n" + 
				"                              \"flag\": 2,\r\n" + 
				"                              \"mappingId\": \"\",\r\n" + 
				"                              \"courseId\": \"\",\r\n" + 
				"                              \"markType\": 1,\r\n" + 
				"                              \"paperId\": \"\",\r\n" + 
				"                              \"paperTitle\": \"\",\r\n" + 
				"                              \"passLine\": 100,\r\n" + 
				"                              \"questionBankList\": \""+questionBank_id+"\",\r\n" + 
				"                              \"questionMode\": 2,\r\n" + 
				"                              \"showKnowledge\": \"show\",\r\n" + 
				"                              \"repeatExam\": true,\r\n" + 
				"                              \"multipleCount\": \""+multipleCount+"\",\r\n" + 
				"                              \"multipleUnitScore\": \""+multipleUnitScore+"\",\r\n" + 
				"                              \"fillBlankCount\": \""+fillBlankCount+"\",\r\n" + 
				"                              \"fillBlankUnitScore\": \""+fillBlankUnitScore+"\",\r\n" + 
				"                              \"fillBlankType\": \""+fillBlankType+"\",\r\n" + 
				"                              \"shortAnswerCount\":\""+shortAnswerCount+"\",\r\n" + 
				"                              \"shortAnswerUnitScore\": \""+shortAnswerUnitScore+"\",\r\n" + 
				"                              \"shortAnswerType\": \""+shortAnswerType+"\",\r\n" + 
				"                              \"singleCount\": \""+singleCount+"\",\r\n" + 
				"                              \"singleUnitScore\": \""+singleUnitScore+"\",\r\n" + 
				"                              \"trueOrFalseCount\": \""+trueOrFalseCount+"\",\r\n" + 
				"                              \"trueOrFalseUnitScore\": \""+trueOrFalseUnitScore+"\",\r\n" + 
				"                              \"totalScore\": \"\",\r\n" + 
				"                              \"answerParsing\": 5,\r\n" + 
				"                              \"passingScore\": 60,\r\n" + 
				"                              \"cutScreenCount\": -1,\r\n" + 
				"                              \"reExamRule\": 0,\r\n" + 
				"                              \"reExamNumber\": 0,\r\n" + 
				"                              \"scoreRule\": 0,\r\n" + 
				"                              \"viewRule\": 1,\r\n" + 
				"                              \"proportional\": "+proportional+",\r\n" + 
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
	 * @Title: StudyPlanExamId  
	 * @Description: TODO  查询题库下的各试题id
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String QueryQuestionId(String studyPlan_ExamId){	
		return HttpRequest.get(question_id_url(studyPlan_ExamId)).query("access_token", token)
				.send().body();
	}
	
	/**   
	 * @Title: SubmitExam  
	 * @Description: TODO  提交试卷
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String SubmitExam(String studyPlan_ExamId,String sinagleChoice_id,String Multiplechoice_id,
			String JudgementQuestion_id,String blank_id,String short_answer_id,String blank_answerFirst,
			String short_answerFirst){	
		return HttpRequest.post(submit_exam_url(studyPlan_ExamId)).query("access_token", token).data("{\r\n" + 
				"      \"questions\": [\r\n" + 
				"            {\r\n" + 
				"                  \"id\": \""+sinagleChoice_id+"\",\r\n" + 
				"                  \"answer\": [],\r\n" + 
				"                  \"answer_image\": \"\"\r\n" + 
				"            },\r\n" + 
				"            {\r\n" + 
				"                  \"id\": \""+Multiplechoice_id+"\",\r\n" + 
				"                  \"answer\": [],\r\n" + 
				"                  \"answer_image\": \"\"\r\n" + 
				"            },\r\n" + 
				"            {\r\n" + 
				"                  \"id\": \""+JudgementQuestion_id+"\",\r\n" + 
				"                  \"answer\": [],\r\n" + 
				"                  \"answer_image\": \"\"\r\n" + 
				"            },\r\n" + 
				"            {\r\n" + 
				"                  \"id\": \""+blank_id+"\",\r\n" + 
				"                  \"answer\": [\r\n" + 
				"                        \""+blank_answerFirst+"\",\r\n" + 
				"                        \"\"\r\n" + 
				"                  ],\r\n" + 
				"                  \"answer_image\": \"\"\r\n" + 
				"            },\r\n" + 
				"            {\r\n" + 
				"                  \"id\": \""+short_answer_id+"\",\r\n" + 
				"                  \"answer\": [\r\n" + 
				"                        \""+short_answerFirst+"\"\r\n" + 
				"                  ],\r\n" + 
				"                  \"answer_image\": \"\"\r\n" + 
				"            }\r\n" + 
				"      ],\r\n" + 
				"      \"makeup_count\": 0,\r\n" + 
				"      \"access_token\": \""+token+"\"\r\n" + 
				"}")
				.send().body();
	}
	
	
	/**   
	 * @Title: ExamAdd  
	 * @Description: TODO  创建考试任务-按题给分的随机试卷模式
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String ExamAdd(String beginTime,String endTime,String fillBlankCount,String fillBlankType,
			String fillBlankUnitScore,String multipleCount,String multipleUnitScore,
			String questionBank_id,String questionMode,String shortAnswerCount,String shortAnswerType,
			String shortAnswerUnitScore,String singleCount,String singleUnitScore,String title,String trueOrFalseCount,
			String trueOrFalseUnitScore,String proportional) throws Exception{	
		return HttpRequest.post(exam_add_url).query("access_token", token).query("authorityRange","false")
				.query("beginTime",URLEncoder.encode(beginTime, "UTF-8"))
				.query("endTime",URLEncoder.encode(endTime, "UTF-8")).query("cheatFlag","0")
				.query("cutScreenCount","-1").query("examCertificateId","0")
				.query("examDuration","20").query("faceRecognition","off").query("fillBlankCount",fillBlankCount)
				.query("fillBlankType",fillBlankType).query("fillBlankUnitScore",fillBlankUnitScore).query("isAllIn","false")
				.query("isGetScore","false").query("markType","1").query("multipleCount",multipleCount)
				.query("multipleUnitScore",multipleUnitScore)
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
	 * @Title: QueryEditExam  
	 * @Description: TODO  查询考试任务编辑页信息
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String QueryEditExam(String exam_plan_id){	
		return HttpRequest.get(query_editExam_url(exam_plan_id)).query("access_token", token)
				.send().body();
	}
	
	
	/**   
	 * @Title: QueryStudyPlanScore  
	 * @Description: TODO  获取学习任务-学员考试成绩
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String QueryStudyPlanScore(String exam_plan_id){	
		return HttpRequest.get(studyPlan_examId_url).query("access_token", token).query("id",exam_plan_id)
				.send().body();
	}
	
	
	/**   
	 * @Title: ResultScore  
	 * @Description: TODO  学习任务-考试结果页-成绩
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String ResultScore(String exam_plan_id){	
		return HttpRequest.get(result_score_url(exam_plan_id)).query("access_token", token).query("userId",user_id)
				.send().body();
	}
	
	
	
	/**   
	 * @Title: ExamEdit  
	 * @Description: TODO  编辑考试任务-修改为按题给分按空给分
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String ExamEdit(String beginTime,String endTime,String fillBlankType,String fillBlankUnitScore,
			String questionBank_id,String questionMode,String shortAnswerType,String shortAnswerUnitScore,String title,
			String exam_plan_id,String planStatus) throws Exception{	
		return HttpRequest.post(exam_edit_url).query("access_token", token).query("authorityRange","false")
				.query("beginTime",URLEncoder.encode(beginTime, "UTF-8"))
				.query("endTime",URLEncoder.encode(endTime, "UTF-8")).query("cheatFlag","0")
				.query("cutScreenCount","-1").query("examCertificateId","0")
				.query("examDuration","20").query("faceRecognition","off").query("fillBlankCount","1")
				.query("fillBlankType",fillBlankType).query("fillBlankUnitScore",fillBlankUnitScore).query("isAllIn","false")
				.query("isGetScore","false").query("markType","1").query("multipleCount","1").query("multipleUnitScore","10")
				.query("passLine","100").query("passingScore","100").query("questionBankId",questionBank_id)
				.query("questionMode",questionMode).query("reExamNumber","0").query("reExamRule","0").query("repeatExam","true")
				.query("scoreRule","0").query("shortAnswerCount","1").query("shortAnswerType",shortAnswerType)
				.query("shortAnswerUnitScore",shortAnswerUnitScore).query("showKnowledge","show").query("singleCount","1")
				.query("singleUnitScore","10").query("supervisorId",user_id).query("supervisorPaper","true").query("title",title)
				.query("trueOrFalseCount","1").query("trueOrFalseUnitScore","10").query("userIds",user_id).query("viewRule","1")
				.query("id",exam_plan_id).query("planStatus",planStatus)
				.send().body();
	}
	
	
	/**   
	 * @Title: QueryExams  
	 * @Description: TODO  考试任务答题前查询接口
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String QueryExams(String exam_plan_id){	
		return HttpRequest.get(query_exams_url(exam_plan_id)).query("access_token", token)
				.send().body();
	}
	
	
	/** 
	 * @Title: QueryExamsScore  
	 * @Description: TODO  考试任务-获取学员考试成绩
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String QueryExamsScore(String keyword){	
		return HttpRequest.get(query_exams_score_url).query("access_token", token).query("keyword",keyword).query("status","all")
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
	
	/**   
	 * @Title: StudyProjectSaveBaseInfo  
	 * @Description: TODO 创建学习项目第一步
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String StudyProjectSaveBaseInfo(String title,String class_id,String cover,String base_cover) {		
		return HttpRequest.post(studyProject_add_url).query("access_token", token).data("{\r\n" + 
				"      \"save_step\": \"base_info\",\r\n" + 
				"      \"base_info\": {\r\n" + 
				"            \"title\": \""+title+"\",\r\n" + 
				"            \"lecturer_id\": 0,\r\n" + 
				"            \"summary\": \"\",\r\n" + 
				"  \"style_type\": \"normal\","+
				"            \"classify\": \""+class_id+"\",\r\n" + 
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
	public static String StudyProjectSaveStageContent(String project_id,String questionBank_id,String question_mode,
			String multiple_count,String multiple_unit_score,String fill_blank_count,String fill_blank_unit_score,
			String fill_blank_type,String short_answer_count,String short_answer_unit_score,String short_answer_type,
			String single_count,String single_unit_score,String true_or_false_count,String true_or_false_unit_score,
			String proportional) {		
		List<String> questionBank = Arrays.asList(questionBank_id.split(","));
		return HttpRequest.post(studyProject_add_url).query("access_token", token).data("{\r\n" + 
				"      \"save_step\": \"stage_content\",\r\n" + 
				"      \"id\": \""+project_id+"\",\r\n" + 
				"      \"stage_content\": {\r\n" + 
				"            \"stage_pass\": \"false\",\r\n" + 
				"            \"stages\": [\r\n" + 
				"                  {\r\n" + 
				"                        \"content\": \"\",\r\n" + 
				"                        \"stage_name\": \"阶段1\",\r\n" + 
				"                        \"is_order\": false,\r\n" + 
				"                        \"sort\": 1,\r\n" + 
				"                        \"resources\": [\r\n" + 
				"                              {\r\n" + 
				"                                    \"type\": 6,\r\n" + 
				"                                    \"sort\": 0,\r\n" + 
				"                                    \"course_id\": \"\",\r\n" + 
				"                                    \"exam\": {\r\n" + 
				"                                          \"title\": \"有考试\",\r\n" + 
				"                                          \"cheat_flag\": 0,\r\n" + 
				"                                          \"exam_duration\": 20,\r\n" + 
				"                                          \"face_recognition\": \"off\",\r\n" + 
				"                                          \"summary\": \"\",\r\n" + 
				"                                          \"mark_type\": 1,\r\n" + 
				"                                          \"paper_id\": \"\",\r\n" + 
				"                                          \"pass_line\": 100,\r\n" + 
				"                                          \"question_banks\": "+questionBank+",\r\n" + 
				"                                          \"question_mode\": \""+question_mode+"\",\r\n" + 
				"                                          \"show_knowledge\": \"show\",\r\n" + 
				"                                          \"repeat_exam\": true,\r\n" + 
				"                                          \"multiple_count\": \""+multiple_count+"\",\r\n" + 
				"                                          \"multiple_unit_score\": \""+multiple_unit_score+"\",\r\n" + 
				"                                          \"fill_blank_count\": \""+fill_blank_count+"\",\r\n" + 
				"                                          \"fill_blank_unit_score\": \""+fill_blank_unit_score+"\",\r\n" + 
				"                                          \"fill_blank_type\": \""+fill_blank_type+"\",\r\n" + 
				"                                          \"short_answer_count\": \""+short_answer_count+"\",\r\n" + 
				"                                          \"short_answer_unit_score\": \""+short_answer_unit_score+"\",\r\n" + 
				"                                          \"short_answer_type\": \""+short_answer_type+"\",\r\n" + 
				"                                          \"single_count\": \""+single_count+"\",\r\n" + 
				"                                          \"single_unit_score\": \""+single_unit_score+"\",\r\n" + 
				"                                          \"true_or_false_count\": \""+true_or_false_count+"\",\r\n" + 
				"                                          \"true_or_false_unit_score\": \""+true_or_false_unit_score+"\",\r\n" + 
				"                                          \"total_score\": \"\",\r\n" + 
				"                                          \"answer_parsing\": 5,\r\n" + 
				"                                          \"passing_score\": 60,\r\n" + 
				"                                          \"cut_screen_count\": -1,\r\n" + 
				"                                          \"re_exam_rule\": 0,\r\n" + 
				"                                          \"re_exam_number\": 0,\r\n" + 
				"                                          \"score_rule\": 0,\r\n" + 
				"                                          \"view_rule\": 1,\r\n" + 
				"                                          \"proportional\": "+proportional+",\r\n" + 
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
	 * @Title: StudyProjectsQuery   
	 * @Description: TODO  学习平台知识库下查询学习项目
	 * @param: @param keyword
	 * @param: @param type
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */  
	public static String StudyProjectsQuery(String title) {
		return HttpRequest.get(study_projects_query_url).query("access_token", token).query("title",title)
				.query("classifyType","all")
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
	 * @throws UnsupportedEncodingException    
	 * @Title: DeleteStudy  
	 * @Description: TODO 添加岗位认证
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String QualificationsAdd(String certificateId,String scopes,String stageJson,String title) throws UnsupportedEncodingException {		
		return HttpRequest.post(qualifications_add_url).query("access_token", token).query("certificateId",certificateId)
				.query("scopes",URLEncoder.encode(scopes, "utf-8")).query("stageJson",URLEncoder.encode(stageJson, "utf-8"))
				.query("stagePass","false").query("studyTimeLimit","0").query("summary","test").query("title",title)
				.query("visibleRange","part").send().body();
	}
	
	/**   
	 * @Title: CertificateGetList  
	 * @Description: TODO 获取证书列表
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String CertificateGetList(String keyword) {		
		return HttpRequest.get(certificate_list_url).query("access_token", token).query("keyword",keyword)
				.send().body();
	}
	
	
	/**   
	 * @Title: QualificationsExamId  
	 * @Description: TODO 获取岗位认证-考试id
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String QualificationsExamId(String qualifications_id) {		
		return HttpRequest.get(qualifications_exam_url(qualifications_id)).query("access_token", token).send().body();
	}
	
	/**   
	 * @Title: QualificationsMonitor  
	 * @Description: TODO 岗位认证数据监控页-获取成绩
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String QualificationsMonitor(String qualifications_id) {		
		return HttpRequest.get(qualifications_monitors_url(qualifications_id)).query("access_token", token).send().body();
	}
	
	/**   
	 * @Title: DeleteQualifications  
	 * @Description: TODO 删除岗位认证
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String DeleteQualifications(String qualifications_id) {		
		return HttpRequest.post(detele_qualifications_url(qualifications_id)).query("access_token", token).send().body();
	}

	
	
}
