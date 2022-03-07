package cn.kxy.group.a.business;

import cn.kxy.base.business.EnterpriseData;
import cn.kxy.base.business.EnterpriseDataUrl;
import cn.kxy.base.business.ExamDataUrl;
import cn.kxy.base.business.TokenData;
import cn.kxy.setting.bussiness.UserBusiness;
import com.lazy.httpclient.utils.HttpRequest;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class ShowResultBusiness {

	public static String enterprise_url = EnterpriseDataUrl.getEnterpriseUrl();
	public static String exam_url= ExamDataUrl.getNewExamUrl();
	public static String token = TokenData.getMangerToken();
	public static String enterpriseId = EnterpriseData.getEnterpriseId();
	public static String user_id = UserBusiness.getUserId();
	public static String exam_add_url = exam_url + "plan/exam/add/";
    public static String paper_getList_url = exam_url + "paper/getList/";
    public static String exams_check_url (String exam_id) {
    	return exam_url + "v2/"+enterpriseId+"/users/"+user_id+"/exams/"+exam_id+"/check/";
    }
    public static String question_id_url (String exam_id) {
		return exam_url + "v2/"+enterpriseId+"/users/"+user_id+"/exams/"+exam_id+"/query/";
	}
    public static String submit_exam_url (String exam_id) {
		return exam_url + "v2/"+enterpriseId+"/users/"+user_id+"/exams/"+exam_id+"/submit/";
	}
    public static String exam_query_url = exam_url + "v2/"+enterpriseId+"/users/"+user_id+"/exams/";
    public static String getUserTrainArchivesDetailList_url = enterprise_url + "report/trainArchives/getUserTrainArchivesDetailList/";
    public static String delete_exam_url = exam_url + "plan/exam/delete/";
    public static String certificate_list_url = enterprise_url + "certificate/getList/";
    public static String qualifications_add_url = enterprise_url + "v1/qualifications/";
    public static String qualifications_exam_url (String qualifications_id) {
		return enterprise_url + "v2/"+enterpriseId +"/qualifications/"+qualifications_id+"/items/";
	}
    public static String detele_qualifications_url (String qualifications_id) {
		return enterprise_url + "v1/qualifications/"+qualifications_id +"/delete/";
	}

    
	 /**   
		 * @Title: PaperGetList  
		 * @Description: TODO  查询试卷列表
		 * @param: @return      
		 * @return: String      
		 * @throws   
	*/ 
	public static String PaperGetList(String keyword){	
		return HttpRequest.get(paper_getList_url).query("access_token", token).query("sortOrder","desc").query("keyword",keyword)
				.send().body();
	}
	    
	    
	 /**   
		 * @Title: ExamAdd  
		 * @Description: TODO  添加考试任务-允许查看考试结果
		 * @param: @return      
		 * @return: String      
		 * @throws   
     */ 
	public static String ExamAdd(String beginTime,String endTime,String paperId,String showResult,String title) throws Exception{	
		return HttpRequest.post(exam_add_url).query("access_token", token).query("beginTime",URLEncoder.encode(beginTime, "UTF-8"))
				.query("endTime",URLEncoder.encode(endTime, "UTF-8")).query("answerParsing","1").query("authorityRange","false")
				.query("cheatFlag","0").query("cutScreenCount","-1").query("examCertificateId","0").query("examDuration","10")
				.query("faceRecognition","off").query("isAllIn","false").query("isGetScore","false").query("markType","1")
				.query("paperId",paperId).query("passLine","100").query("passingScore","30").query("questionMode","1")
				.query("reExamNumber","0").query("reExamRule","0").query("repeatExam","true").query("scoreRule","0")
				.query("showKnowledge","show").query("showResult",showResult).query("supervisorId",user_id).query("supervisorPaper","true")
				.query("title",title).query("totalScore","30").query("userIds",user_id).query("viewRule","1")
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
	 * @Title: SubmitExam  
	 * @Description: TODO  提交试卷
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String SubmitExam(String exam_id,String multiplechoice_id1,String multiplechoice_id2,String multiplechoice_id3){	
		return HttpRequest.post(submit_exam_url(exam_id)).query("access_token", token).data("{\r\n" + 
				"      \"questions\": [\r\n" + 
				"            {\r\n" + 
				"                  \"id\": \""+multiplechoice_id1+"\",\r\n" + 
				"                  \"answer\": [],\r\n" + 
				"                  \"answer_image\": \"\"\r\n" + 
				"            },\r\n" + 
				"            {\r\n" + 
				"                  \"id\": \""+multiplechoice_id2+"\",\r\n" + 
				"                  \"answer\": [],\r\n" + 
				"                  \"answer_image\": \"\"\r\n" + 
				"            },\r\n" + 
				"            {\r\n" + 
				"                  \"id\": \""+multiplechoice_id3+"\",\r\n" + 
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
	 * @Title: ExamQuery  
	 * @Description: TODO  学员端查询考试任务
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String ExamQuery(String keyword){	
		return HttpRequest.get(exam_query_url).query("access_token", token).query("keyword",keyword).query("status","all")
				.send().body();
	}
	
	
	
	/**   
	 * @Title: GetUserTrainArchivesDetailList  
	 * @Description: TODO  培训档案任务列表
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String GetUserTrainArchivesDetailList(String trainType){	
		return HttpRequest.get(getUserTrainArchivesDetailList_url).query("access_token", token).query("client_flag","student")
				.query("trainType",trainType)
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
