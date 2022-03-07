package cn.kxy.group.a.business;
/**
 * @author wenlingzhi
 *2021年7月26日
 */

import cn.kxy.base.business.EnterpriseData;
import cn.kxy.base.business.EnterpriseDataUrl;
import cn.kxy.base.business.ExamDataUrl;
import cn.kxy.base.business.TokenData;
import cn.kxy.setting.bussiness.UserBusiness;
import com.lazy.httpclient.utils.HttpRequest;

public class ProjectPushMessageBusiness {

	public static String enterprise_url = EnterpriseDataUrl.getEnterpriseUrl();
	public static String exam_url= ExamDataUrl.getNewExamUrl();
	public static String message_url=  EnterpriseDataUrl.getMessageUrl();
	public static String token = TokenData.getMangerToken();
	public static String enterpriseId = EnterpriseData.getEnterpriseId();
	public static String user_id = UserBusiness.getUserId();
	public static String queryQuestionBankList_url = exam_url + "questionBank/queryQuestionBankList/";
	public static String studyProject_add_url = enterprise_url + "v2/"+enterpriseId+"/study_projects/save/";
	public static String push_message_url (String project_id) {
		return enterprise_url + "v2/"+enterpriseId+"/study_projects/"+project_id+"/push_message/";
	}
	public static String project_query_url (String project_id) {
		return enterprise_url + "v2/"+enterpriseId+"/study_projects/"+project_id+"/query/";
	}
	public static String message_list_url = message_url + "v2/enterpriseId/"+enterpriseId+"/userid/"+user_id+"/message_list/";
	public static String study_project_delete_url (String project_id) {
		return enterprise_url + "v2/"+enterpriseId +"/study_projects/"+project_id+"/delete/";
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
	 * @Title: PushMessage  
	 * @Description: TODO 推送消息-完整内容
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String PushMessage(String project_id) {		
		return HttpRequest.post(push_message_url(project_id)).query("access_token", token).data("{\r\n" + 
				"      \"user_ids\": \"1791680808503873536\",\r\n" + 
				"      \"department_ids\": \"\",\r\n" + 
				"      \"group_ids\": \"\",\r\n" + 
				"      \"post_ids\": \"\",\r\n" + 
				"      \"push_type\": \"is_all\",\r\n" + 
				"      \"access_token\": \""+token+"\"\r\n" + 
				"}")
				.send().body();
	}
	
	
	/**   
	 * @Title: PushMessage  
	 * @Description: TODO 推送消息-指定内容
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String PushMessageAppoint(String project_id,String course_id) {		
		return HttpRequest.post(push_message_url(project_id)).query("access_token", token).data("{\r\n" + 
				"      \"user_ids\": \""+user_id+"\",\r\n" + 
				"      \"department_ids\": \"\",\r\n" + 
				"      \"group_ids\": \"\",\r\n" + 
				"      \"post_ids\": \"\",\r\n" + 
				"      \"push_type\": \"appoint\",\r\n" + 
				"      \"course_ids\": \""+course_id+"\",\r\n" + 
				"      \"stage_ids\": \"\",\r\n" + 
				"      \"access_token\": \""+token+"\"\r\n" + 
				"}")
				.send().body();
	}
	
	
	
	/**   
	 * @Title: ProjectQuery  
	 * @Description: TODO 获取学习项目中资源信息
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String ProjectQuery(String project_id) {		
		return HttpRequest.get(project_query_url(project_id)).query("access_token", token)
				.send().body();
	}
	
	
	
	/**   
	 * @Title: MessageList  
	 * @Description: TODO    消息中心消息列表
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String MessageList() {		
		return HttpRequest.get(message_list_url).query("access_token", token).query("isRead","2").query("type","0")
				.query("visible","false")
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

	
	
	
}
