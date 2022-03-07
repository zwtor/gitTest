package cn.kxy.group.a.business;

import cn.kxy.base.business.EnterpriseData;
import cn.kxy.base.business.EnterpriseDataUrl;
import cn.kxy.base.business.TokenData;
import cn.kxy.setting.bussiness.UserBusiness;
import com.lazy.httpclient.utils.HttpRequest;

import java.io.UnsupportedEncodingException;

public class ProjectStageBusiness {

	public static String enterprise_url = EnterpriseDataUrl.getEnterpriseUrl();
	public static String token = TokenData.getMangerToken();
	public static String enterpriseId = EnterpriseData.getEnterpriseId();
	public static String user_id = UserBusiness.getUserId();
	public static String resource_getList_url = enterprise_url + "course/resource/getList/";
	public static String study_projects_save_url = enterprise_url + "v2/"+enterpriseId +"/study_projects/save/";
	public static String study_project_delete_url (String project_id) {
		return enterprise_url + "v2/"+enterpriseId +"/study_projects/"+project_id+"/delete/";
	}
	public static String study_project_query_url (String project_id) {
		return enterprise_url + "v2/"+enterpriseId +"/study_projects/"+project_id+"/query/";
	}
	public static String study_project_map_url (String project_id) {
		return enterprise_url + "v2/"+enterpriseId +"/study_projects/"+project_id+"/user/"+user_id+"/map/";
	}
	public static String start_study_url(String project_id) {
		return enterprise_url + "v2/"+enterpriseId+"/users/"+user_id+"/study_projects/"+project_id+"/start_study/";
	}
	public static String save_progress_url(String projectCourse_id,String course_id) {
		return enterprise_url + "v2/"+enterpriseId+"/users/"+user_id+"/study_projects/"+projectCourse_id+"/courses/"+course_id+"/resources/"+course_id+"/save_progress/";
	}
	public static String getScoreRank_url = enterprise_url + "/score/getScoreRank/";
	public static String getScoreRecord_url = enterprise_url + "score/getScoreRecord/";
	
	
	/**   
	 * @Title: ResourceGetList  
	 * @Description: TODO  获取课件列表
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String ResourceGetList(String contentType) {		
	    return HttpRequest.get(resource_getList_url).query("access_token", token).query("resourceClassify","0").query("contentType",contentType)
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
		return HttpRequest.post(study_projects_save_url).query("access_token", token).data("{\r\n" + 
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
	 * @Description: TODO 创建学习项目第二步多个阶段内容
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String StudyProjectSaveStageContent(String title1,String project_id,String course_id1,String title2,String course_id2) {		
		return HttpRequest.post(study_projects_save_url).query("access_token", token).data("{\r\n" + 
				"      \"save_step\": \"stage_content\",\r\n" + 
				"      \"id\": \""+project_id+"\",\r\n" + 
				"      \"stage_content\": {\r\n" + 
				"            \"stages\": [\r\n" + 
				"                  {\r\n" + 
				"                        \"id\": \"\",\r\n" + 
				"                        \"content\": \"\",\r\n" + 
				"                        \"stage_name\": \""+title1+"\",\r\n" + 
				"                        \"is_order\": false,\r\n" + 
				"                        \"sort\": 1,\r\n" + 
				"                        \"progress\": 100,\r\n" + 
				"                        \"exam_times\": \"\",\r\n" + 
				"                        \"operation_times\": \"\",\r\n" + 
				"                        \"finish_evaluation\": \"false\",\r\n" + 
				"                        \"is_get_score\": 1,\r\n" + 
				"                        \"certificate_id\": \"\",\r\n" + 
				"                        \"resources\": [\r\n" + 
				"                              {\r\n" + 
				"                                    \"course_id\": \""+course_id1+"\",\r\n" + 
				"                                    \"sort\": 0,\r\n" + 
				"                                    \"type\": 3,\r\n" + 
				"                                    \"resource_lock\": false\r\n" + 
				"                              }\r\n" + 
				"                        ]\r\n" + 
				"                  },\r\n" + 
				"                  {\r\n" + 
				"                        \"id\": \"\",\r\n" + 
				"                        \"content\": \"\",\r\n" + 
				"                        \"stage_name\": \""+title2+"\",\r\n" + 
				"                        \"is_order\": false,\r\n" + 
				"                        \"sort\": 2,\r\n" + 
				"                        \"progress\": 100,\r\n" + 
				"                        \"exam_times\": \"\",\r\n" + 
				"                        \"operation_times\": \"\",\r\n" + 
				"                        \"finish_evaluation\": \"false\",\r\n" + 
				"                        \"is_get_score\": 2,\r\n" + 
				"                        \"certificate_id\": \"\",\r\n" + 
				"                        \"resources\": [\r\n" + 
				"                              {\r\n" + 
				"                                    \"course_id\": \""+course_id2+"\",\r\n" + 
				"                                    \"sort\": 0,\r\n" + 
				"                                    \"type\": 3,\r\n" + 
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
	public static String StudyProjectSaveSettings(String project_id,String stage_pass) {		
		return HttpRequest.post(study_projects_save_url).query("access_token", token).data("{\r\n" + 
				"      \"save_step\": \"settings\",\r\n" + 
				"      \"id\": \""+project_id+"\",\r\n" + 
				"      \"settings\": {\r\n" + 
				"            \"study_time_limit\": 0,\r\n" + 
				"            \"face_recognition\": \"off\",\r\n" + 
				"            \"progress\": 100,\r\n" + 
				"            \"times\": \"\",\r\n" + 
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
				"            \"supervisor_ids\": [],\r\n" + 
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
				"            \"open_learning_group\": \"false\",\r\n" + 
				"            \"exam_lock\": \"done\",\r\n" + 
				"            \"sync_progress\": \"false\",\r\n" + 
				"            \"stage_pass\": \""+stage_pass+"\"\r\n" + 
				"      },\r\n" + 
				"      \"access_token\": \""+token+"\"\r\n" + 
				"}")
				.send().body();
	}
	
	
	/**   
	 * @Title: QueryProject  
	 * @Description: TODO 查询学习项目信息
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String QueryProject(String project_id) {		
		return HttpRequest.get(study_project_query_url(project_id)).query("access_token", token).send().body();
	}
	
	
	/**   
	 * @Title: MapProject  
	 * @Description: TODO 查询学习项目阶段信息
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String MapProject(String project_id) {		
		return HttpRequest.get(study_project_map_url(project_id)).query("access_token", token).send().body();
	}
	
	/**   
	 * @Title: StartStudy   
	 * @Description: TODO  进入岗位测评前的start
	 * @param: @param keyword
	 * @param: @param type
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */  
	public static String StartStudy(String project_id) {
		return HttpRequest.post(start_study_url(project_id)).query("access_token", token)
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
	 * @Title: SaveProcess  
	 * @Description: TODO  完成学习任务
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String SaveProcess(String studyPlan_id,String course_id,String tempTime) {		
	    return HttpRequest.post(save_progress_url(studyPlan_id,course_id)).query("access_token", token).data("{\r\n" + 
	    		"      \"progress\": 100,\r\n" + 
	    		"      \"recent_start\": 0,\r\n" + 
	    		"      \"tempTime\": \""+tempTime+"\",\r\n" + 
	    		"      \"access_token\": \""+token+"\"\r\n" + 
	    		"}")
	    		.send().body();
	}
	
	/**
	 * @throws UnsupportedEncodingException    
	 * @Title: GetScoreRank  
	 * @Description: TODO  查询学分排行榜
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */
	public static String GetScoreRank(String startTime,String endTime){
		return HttpRequest.get(getScoreRank_url).query("access_token", token).query("startTime",startTime)
				.query("endTime",endTime)
				.send().body();
	}
	
	
	/**   
	 * @Title: GetScoreRecord  
	 * @Description: TODO 获取学分排行榜-我的学分行为列表
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String GetScoreRecord(String startTime,String endTime) {		
	    return HttpRequest.get(getScoreRecord_url).query("access_token", token).query("userId",user_id)
	    		.query("startTime",startTime).query("endTime",endTime)
	    		.send().body();
	}
	
	
}
