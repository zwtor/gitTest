package cn.kxy.study.business;

import cn.kxy.base.business.EnterpriseData;
import cn.kxy.base.business.EnterpriseDataUrl;
import cn.kxy.base.business.ExamDataUrl;
import cn.kxy.base.business.TokenData;
import cn.kxy.setting.bussiness.UserBusiness;
import com.lazy.assured.utils.GetRequestTools;
import com.lazy.assured.utils.PostRequestTools;
import com.lazy.common.utils.DateUtil;

public class MySelfStudyBusiness {
	public static String token = TokenData.getMangerToken();
	public static String enterpriseId = EnterpriseData.getEnterpriseId();
	public static String enterpriseUrl = EnterpriseDataUrl.getEnterpriseUrl();
	public static String userId = UserBusiness.getUserId();
	public static String examUrl = ExamDataUrl.getNewExamUrl();
	public static String list_url = enterpriseUrl +"v2/"+enterpriseId+"/users/"+userId+"/study_projects";
	
	public static String list_course_url (String id) {
		return enterpriseUrl + "v2/"+enterpriseId+"/users/"+userId+"/study_projects/"+id+"/query";
	}
	
	public static String queryCourseRecordUrl(String id) {
		return enterpriseUrl + "v2/"+enterpriseId+"/study_projects/"+id+"/query_study_record";
	}
	
	public static String load_resources_url (String id) {
		return enterpriseUrl + "v2/"+enterpriseId+"/users/"+userId+"/study_projects/"+id+"/load_resources";
	}
	
	public static String start_study_url(String id) {
		return enterpriseUrl +"v2/"+enterpriseId+"/users/"+userId+"/study_projects/"+id+"/start_study";
	}
	
	public static String getSingleResourceProgressUrl =enterpriseUrl + "plan/myTask/getSingleResourceProgress";
	
	public static String save_progress_url(String studyId,String courseId,String resourcesId) {
		return enterpriseUrl + "v2/"+enterpriseId+"/users/"+userId+"/study_projects/"+studyId+"/courses/"+courseId+"/resources/"+resourcesId+"/save_progress";
	}
	
	public static String queryExamInfoUrl(String id) {
		return examUrl +"v2/"+enterpriseId+"/users/"+userId+"/exams/"+id+"/query";
	}
	
	public static String queryInvestigatesUrl(String id,String ins_id) {
		return enterpriseUrl +"v2/"+enterpriseId+"/studies/"+id+"/investigates/"+ins_id;
	}
	
	public static String submitUrl(String id,String investigatesId) {
		return enterpriseUrl + "v2/"+enterpriseId+"/users/"+userId+"/studies/"+id+"/investigates/"+investigatesId+"/submit";
	}
	
	public static String study_projects_count_url  = enterpriseUrl + "v2/"+enterpriseId+"/users/"+userId+"/study_projects_count";
	
	public static String deleteCourseUrl(String id) {
		return enterpriseUrl + "v2/"+enterpriseId+"/study_projects/"+id+"/delete";
	}
	
	/**   
	 * @Title: deleteCourse   
	 * @Description: TODO(删除课程)   
	 * @param: @param id
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String deleteCourse(String id) {
		return PostRequestTools.RequestBodyByPost("{\"access_token\":\""+token+"\"}", token,deleteCourseUrl(id));
	}
	
	/**   
	 * @Title: queryStudyProjectsCount   
	 * @Description: TODO(查看自学的个数)   
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String queryStudyProjectsCount() {
		return GetRequestTools.RequestQueryParamsByGet("access_token", token, study_projects_count_url);
	}
	
	/**   
	 * @Title: submit   
	 * @Description: TODO(提交调研)   
	 * @param: @param id
	 * @param: @param investigatesId
	 * @param: @param questionId_one
	 * @param: @param options_one
	 * @param: @param questionId_two
	 * @param: @param options_two
	 * @param: @param questionId_three
	 * @param: @param questionId_four
	 * @param: @param threeOption
	 * @param: @param fouroOtion
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String submit(String id,String investigatesId,String questionId_one,String options_one,String questionId_two,String options_two,String questionId_three,
			String questionId_four,String threeOption,String fouroOtion) {
		return PostRequestTools.RequestBodyByPost("{\r\n" + 
				"	\"questions\": [{\r\n" + 
				"		\"id\": \""+questionId_one+"\",\r\n" + 
				"		\"solution\": {\r\n" + 
				"			\"options\": \""+options_one+"\"\r\n" + 
				"		}\r\n" + 
				"	}, {\r\n" + 
				"		\"id\": \""+questionId_two+"\",\r\n" + 
				"		\"solution\": {\r\n" + 
				"			\"options\": \""+options_two+"\"\r\n" + 
				"		}\r\n" + 
				"	}, {\r\n" + 
				"		\"id\": \""+questionId_three+"\",\r\n" + 
				"		\"solution\": {\r\n" + 
				"			\"options\": \""+threeOption+"\",\r\n" + 
				"			\"other_answer\": \"\"\r\n" + 
				"		}\r\n" + 
				"	}, {\r\n" + 
				"		\"id\": \""+questionId_four+"\",\r\n" + 
				"		\"solution\": {\r\n" + 
				"			\"options\": \""+fouroOtion+"\",\r\n" + 
				"			\"other_answer\": \"\"\r\n" + 
				"		}\r\n" + 
				"	}]\r\n" + 
				"}", token,"study_type","study_project", submitUrl(id,investigatesId));
	}
	
	
	/**   
	 * @Title: queryInvestigatesInfo   
	 * @Description: TODO(查看调研详情)   
	 * @param: @param id
	 * @param: @param ins_id
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String queryInvestigatesInfo(String id,String ins_id) {
		return GetRequestTools.RequestQueryParamsByGet("access_token",token, "study_type","study_project",queryInvestigatesUrl(id,ins_id));
	}
	
	
	/**   
	 * @Title: queryExamInfo   
	 * @Description: TODO(查看考试详情)   
	 * @param: @param id
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String queryExamInfo(String id) {
		return GetRequestTools.RequestQueryParamsByGet("access_token", token, queryExamInfoUrl(id));
	}
	
	/**   
	 * @Title: saveProgress   
	 * @Description: TODO(保存进度)   
	 * @param: @param progress
	 * @param: @param studyId
	 * @param: @param courseId
	 * @param: @param resourcesId
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String saveProgress(String  progress,String studyId,String courseId,String resourcesId) {
		return PostRequestTools.RequestBodyByPost("{\r\n" + 
				"  \"progress\": "+progress+", \r\n" + 
				"  \"recent_start\": 1, \r\n" + 
				"  \"tempTime\": "+DateUtil.getTimeStamp(0, "")+", \r\n" + 
				"  \"access_token\": \""+token+"\"\r\n" + 
				"}", token, save_progress_url(studyId, courseId, resourcesId));
	}
	
	/**   
	 * @Title: getSingleResourceProgress   
	 * @Description: TODO(获取资源进度)   
	 * @param: @param planId
	 * @param: @param courseId
	 * @param: @param resourceId
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String getSingleResourceProgress(String planId,String courseId,String resourceId) {
		return GetRequestTools.RequestQueryParamsByGet("planId", planId, "studyType","study_project","access_token", token,"courseId",courseId,
				"resourceId",resourceId,getSingleResourceProgressUrl);
	}
	
	/**   
	 * @Title: startStudy   
	 * @Description: TODO(开始学习)   
	 * @param: @param id
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String startStudy(String id) {
		return PostRequestTools.RequestBodyByPost("{\"access_token\":\""+token+"\"}", token, start_study_url(id));
	}
	
	/**   
	 * @Title: loadResources   
	 * @Description: TODO(加载资源)   
	 * @param: @param id
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String loadResources(String id) {
		return PostRequestTools.RequestBodyByPost("{\"access_token\":\""+token+"\"}", token, load_resources_url(id));
	}
	
	/**   
	 * @Title: queryCourseRecord   
	 * @Description: TODO(查询课程观看进度)   
	 * @param: @param courseId
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String queryCourseRecord(String courseId) {
		return GetRequestTools.RequestQueryParamsByGet("access_token", token,"page_number","1","page_size","20", queryCourseRecordUrl(courseId));
	}
	
	/**   
	 * @Title: queryStudyCourseInfo   
	 * @Description: TODO(查看学习项目在课程列表的详情)   
	 * @param: @param id
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String queryStudyCourseInfo(String id) {
		return GetRequestTools.RequestQueryParamsByGet("access_token", token, list_course_url(id));
	}
	
	/**   
	 * @Title: queryMySelfStudyList   
	 * @Description: TODO(查看自学列表)   
	 * @param: @param keyword
	 * @param: @param status（unfinished--未完成，finished--已完成，空字符--全部）
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String queryMySelfStudyList(String keyword,String status) {
		return GetRequestTools.RequestQueryParamsByGet("status", status,"access_token",token,"keyword",keyword,
				"page_size","20","page_number","1",list_url);
	}
}
