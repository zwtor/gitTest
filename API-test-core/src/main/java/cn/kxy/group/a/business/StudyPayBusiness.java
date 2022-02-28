package cn.kxy.group.a.business;

import cn.kxy.base.business.EnterpriseData;
import cn.kxy.base.business.EnterpriseDataUrl;
import cn.kxy.base.business.TokenData;
import cn.kxy.setting.bussiness.UserBusiness;
import com.lazy.common.utils.DateUtil;
import com.lazy.httpclient.utils.HttpRequest;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class StudyPayBusiness {

	public static String enterprise_url = EnterpriseDataUrl.getEnterpriseUrl();
	public static String token = TokenData.getMangerToken();
	public static String enterpriseId = EnterpriseData.getEnterpriseId();
	public static String user_id = UserBusiness.getUserId();
	public static String account_info_url = enterprise_url + "v2/"+ enterpriseId+"/account_info/";
	public static String account_info_check_url = enterprise_url + "v2/wxpay/"+ enterpriseId+"/account_info_check/";
	public static String study_add_url = enterprise_url + "plan/study/add/";
	public static String myTask_url = enterprise_url + "plan/myTask/getOne/";
	public static String study_query_url (String StudyPlan_id) {
		return enterprise_url + "v2/"+enterpriseId+"/users/"+user_id+"/studies/"+StudyPlan_id+"/query/";
	}
	public static String query_order_url (String StudyPlan_id) {
		return enterprise_url + "v2/"+enterpriseId+"/pay/query_order/";
	}
	public static String create_order_url = enterprise_url + "v2/"+enterpriseId+"/pay/create_order/";
	public static String goods_pay_url = enterprise_url + "v2/"+enterpriseId+"/goods/pay/";
	public static String studyPlan_delete_url = enterprise_url + "plan/study/delete/";
	public static String study_projects_save_url = enterprise_url + "v2/"+enterpriseId +"/study_projects/save/";
	public static String studyProject_query_url (String projectCourse_id) {
		return enterprise_url + "v2/"+enterpriseId +"/users/"+user_id+"/study_projects/"+projectCourse_id+"/query/";
	}
	public static String account_info_configured_url = enterprise_url + "v2/wxpay/"+enterpriseId+"/account_info_configured/";
	public static String study_project_delete_url (String project_id) {
		return enterprise_url + "v2/"+enterpriseId +"/study_projects/"+project_id+"/delete/";
	}
	public static String goods_order_url = enterprise_url + "v2/"+enterpriseId +"/goods_order/";
	public static String query_account_info_url = enterprise_url + "v2/"+enterpriseId +"/account_info/";
	public static String query_wxaccount_info_url = enterprise_url + "v2/wxpay/"+enterpriseId +"/account_info/";
	public static String studyProject_monitor_url (String project_id) {
		return enterprise_url + "v2/"+enterpriseId +"/study_project/"+project_id+"/study_project_transaction/";
	}
	public static String studyProject_monitor_export_url (String project_id) {
		return enterprise_url + "v2/"+enterpriseId +"/study_project/"+project_id+"/study_project_transaction_export/";
	}
	public static String enrollments_url (String project_id) {
		return enterprise_url + "v2/"+enterpriseId+"/users/"+user_id+"/enrollments/"+project_id;
	}
	
	
	/**   
	 * @Title: AccountInfo   
	 * @Description: TODO  支付账户设置-支付宝
	 * @param: @param keyword
	 * @param: @param type
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */  
	public static String AccountInfo(String enterprise_app_id,String enterprise_private_key,String enterprise_public_key) {
		return HttpRequest.post(account_info_url).query("access_token", token).data("{\r\n" + 
				"      \"enterprise_app_id\": \""+enterprise_app_id+"\",\r\n" + 
				"      \"enterprise_private_key\": \""+enterprise_private_key+"\",\r\n" + 
				"      \"enterprise_public_key\": \""+enterprise_public_key+"\",\r\n" + 
				"      \"access_token\": \""+token+"\"\r\n" + 
				"}")
				.send().body();	
	}
	
	
	/**   
	 * @Title: AccountInfoCheck  
	 * @Description: TODO  支付账户设置-微信
	 * @param: @param keyword
	 * @param: @param type
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */  
	public static String AccountInfoCheck(String app_id,String mch_id,String mch_serial_no,String api_v3_Key,String private_key) {
		return HttpRequest.post(account_info_check_url).query("access_token", token).data("{\r\n" + 
				"      \"app_id\": \""+app_id+"\",\r\n" + 
				"      \"mch_id\": \""+mch_id+"\",\r\n" + 
				"      \"mch_serial_no\": \""+mch_serial_no+"\",\r\n" + 
				"      \"api_v3_Key\": \""+api_v3_Key+"\",\r\n" + 
				"      \"private_key\": \""+private_key+"\",\r\n" + 
				"      \"access_token\": \""+token+"\"\r\n" + 
				"}")
				.send().body();	
	}


    
	/**   
	 * @Title: StudyAdd  
	 * @Description: TODO  创建学习任务
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String StudyAdd(String title,String official_price_str) {		
		return HttpRequest.post(study_add_url).query("access_token", token).data("{\r\n" + 
				"      \"title\": \""+title+"\",\r\n" + 
				"      \"beginTime\": "+DateUtil.getTimeStamp(0, "")+",\r\n" + 
				"      \"endTime\": "+DateUtil.getTimeStamp(7, "")+",\r\n" +
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
				"      \"times\": \"\",\r\n" + 
				"      \"operationTimes\": 1,\r\n" + 
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
				"                              \"courseType\": 10,\r\n" + 
				"                              \"courseSort\": 0,\r\n" + 
				"                              \"flag\": 5,\r\n" + 
				"                              \"type\": \"sc\",\r\n" + 
				"                              \"id\": \"\",\r\n" + 
				"                              \"title\": \""+title+"\",\r\n" + 
				"                              \"content\": \""+title+"\",\r\n" + 
				"                              \"resources\": [],\r\n" + 
				"                              \"scoreSetting\": {\r\n" + 
				"                                    \"scoreSwitch\": \"off\",\r\n" + 
				"                                    \"score\": 100\r\n" + 
				"                              },\r\n" + 
				"                              \"workflowJson\": {\r\n" + 
				"                                    \"type\": \"or\",\r\n" + 
				"                                    \"userIds\": [\r\n" + 
				"                                          \""+user_id+"\"\r\n" + 
				"                                    ]\r\n" + 
				"                              },\r\n" + 
				"                              \"mappingId\": \"\",\r\n" + 
				"                              \"job_type\": \"common\",\r\n" + 
				"                              \"resource_lock\": false\r\n" + 
				"                        }\r\n" + 
				"                  ]\r\n" + 
				"            }\r\n" + 
				"      ],\r\n" + 
				"      \"stagePass\": false,\r\n" + 
				"      \"finishEvaluation\": false,\r\n" + 
				"      \"openEvaluationResult\": false,\r\n" + 
				"      \"action\": \"published\",\r\n" + 
				"      \"free\": false,\r\n" + 
				"      \"official_price_str\": \""+official_price_str+"\",\r\n" + 
				"      \"access_token\": \""+token+"\"\r\n" + 
				"}")
				.send().body();
	}
	
	
	/**   
	 * @Title: GetFreeType  
	 * @Description: TODO  PC端-校验学习任务详情页付费类型
	 * @param: @param keyword
	 * @param: @param type
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */  
	public static String GetFreeType(String id) {
		return HttpRequest.get(myTask_url).query("access_token", token).query("id",id)
				.send().body();	
	}
	
	
	/**   
	 * @Title: StudyQuery  
	 * @Description: TODO  移动端-校验学习任务详情页付费类型
	 * @param: @param keyword
	 * @param: @param type
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */  
	public static String StudyQuery(String StudyPlan_id) {
		return HttpRequest.get(study_query_url(StudyPlan_id)).query("access_token", token).send().body();	
	}
	
	
	/**   
	 * @Title: QueryOrder    PC端/移动端-校验支付状态
	 * @param: @param keyword
	 * @param: @param type
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */  
	public static String QueryOrder(String StudyPlan_id) {
		return HttpRequest.get(query_order_url(StudyPlan_id)).query("access_token", token).query("goods_id",StudyPlan_id)
				.send().body();	
	}
	
	
	
	/**   
	 * @Title: CreateOrder   
	 * @param: @param keyword
	 * @param: @param type    PC端-生成订单
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */  
	public static String CreateOrder(String terminal_type,String pay_channel,String goods_id,String goods_type) {
		return HttpRequest.post(create_order_url).query("access_token", token).data("{\r\n" + 
				"      \"terminal_type\": \""+terminal_type+"\",\r\n" + 
				"      \"pay_channel\": \""+pay_channel+"\",\r\n" +
				"      \"goods_id\": \""+goods_id+"\",\r\n" + 
				"      \"goods_type\": \""+goods_type+"\",\r\n" + 
				"      \"access_token\": \""+token+"\"\r\n" + 
				"}")
				.send().body();	
	}
	
	
	/**   
	 * @Title: GoodsPay   
	 * @param: @param keyword
	 * @param: @param type   钉钉小程序生成订单
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */  
	public static String GoodsPay(String goods_id,String goods_type) {
		return HttpRequest.post(goods_pay_url).query("access_token", token).data("{\r\n" + 
				"	\"goods_id\": \""+goods_id+"\",\r\n" + 
				"	\"goods_type\": \""+goods_type+"\"\r\n" + 
				"}")
				.send().body();	
	}
	
	
	/**   
	 * @Title: StudyPlanDelete   
	 * @Description: TODO  学习任务删除
	 * @param: @param keyword
	 * @param: @param type
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */  
	public static String StudyPlanDelete(String id) {
		return HttpRequest.post(studyPlan_delete_url).query("access_token", token).query("id",id)
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
		return HttpRequest.post(study_projects_save_url).query("access_token", token).data("{\r\n" + 
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
	public static String StudyProjectSaveStageContent(String project_id) {		
		return HttpRequest.post(study_projects_save_url).query("access_token", token).data("{\r\n" + 
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
				"                                    \"type\": 10,\r\n" + 
				"                                    \"courseSort\": 0,\r\n" + 
				"                                    \"sort\": 0,\r\n" + 
				"                                    \"flag\": 4,\r\n" + 
				"                                    \"course_id\": \"\",\r\n" + 
				"                                    \"title\": \"test\",\r\n" + 
				"                                    \"content\": \"test\",\r\n" + 
				"                                    \"resources\": [],\r\n" + 
				"                                    \"scoreSetting\": {\r\n" + 
				"                                          \"scoreSwitch\": \"off\",\r\n" + 
				"                                          \"score\": 100\r\n" + 
				"                                    },\r\n" + 
				"                                    \"workflowJson\": {\r\n" + 
				"                                          \"type\": \"tutor\",\r\n" + 
				"                                          \"userIds\": []\r\n" + 
				"                                    }\r\n" + 
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
	public static String StudyProjectSaveSettings(String project_id,String enroll_audit,String enroll_limit,String limit_count,
			String official_price,String preferential_price) {		
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
				"            \"is_all\": 3,\r\n" + 
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
				"            \"enroll_audit\": \""+enroll_audit+"\",\r\n" + 
				"            \"enroll_limit\": \""+enroll_limit+"\",\r\n" + 
				"            \"limit_count\": \""+limit_count+"\",\r\n" + 
				"            \"open_evaluation_result\": false,\r\n" + 
				"            \"is_free\": false,\r\n" + 
				"            \"official_price\": \""+official_price+"\",\r\n" + 
				"            \"preferential_price\": \""+preferential_price+"\",\r\n" + 
				"            \"open_learning_group\": \"false\"\r\n" + 
				"      },\r\n" + 
				"      \"access_token\": \""+token+"\"\r\n" + 
				"}")
				.send().body();
	}
	
	
	/**   
	 * @Title: StudyProjectQuery   
	 * @Description: TODO  
	 * @param: @param keyword   校验学习项目详情页报名入口
	 * @param: @param type
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */  
	public static String StudyProjectQuery(String projectCourse_id) {
		return HttpRequest.get(studyProject_query_url(projectCourse_id)).query("access_token", token)
				.send().body();	
	}
	
	
	
	/**   
	 * @Title: AccountInfoConfigured   
	 * @Description: TODO  
	 * @param: @param keyword   PC端/移动端-校验支付方式
	 * @param: @param type
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */  
	public static String AccountInfoConfigured() {
		return HttpRequest.get(account_info_configured_url).query("access_token", token)
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
	 * @Title: GoodsOrder  
	 * @Description: TODO 交易流水
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String GoodsOrder(String start,String end,String pay_channel) throws UnsupportedEncodingException {		
		return HttpRequest.get(goods_order_url).query("access_token", token).query("start",URLEncoder.encode(start, "UTF-8"))
				.query("end",URLEncoder.encode(end, "UTF-8"))
				.query("page_number","1").query("page_size","10").query("pay_channel",pay_channel)
				.send().body();
	}
	
	
	
	/**
	 * @Title: QueryAccountInfo  
	 * @Description: TODO 支付设置-支付宝
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String QueryAccountInfo(){		
		return HttpRequest.get(query_account_info_url).query("access_token", token)
				.send().body();
	}
	
	/**
	 * @Title: QueryWxAccountInfo  
	 * @Description: TODO 支付设置-微信
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String QueryWxAccountInfo(){		
		return HttpRequest.get(query_wxaccount_info_url).query("access_token", token)
				.send().body();
	}
	
	
	/**
	 * @throws UnsupportedEncodingException 
	 * @Title: StudyProjectTransaction  
	 * @Description: TODO 学习项目-数据监控-交易数据
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String StudyProjectTransaction(String project_id,String begin_time,String end_time,String depart_id,String keyword) 
			throws UnsupportedEncodingException{		
		return HttpRequest.get(studyProject_monitor_url(project_id)).query("access_token", token)
				.query("begin_time",URLEncoder.encode(begin_time, "UTF-8")).query("end_time",URLEncoder.encode(end_time, "UTF-8"))
				.query("depart_id",depart_id).query("keyword",keyword)
				.send().body();
	}
	
	
	/**
	 * @Title: StudyProjectTransactionExport  
	 * @Description: TODO 学习项目-数据监控-交易数据导出
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String StudyProjectTransactionExport(String project_id,String study_project_name) {		
		return HttpRequest.get(studyProject_monitor_export_url(project_id)).query("access_token", token).query("study_project_name",study_project_name)
				.send().body();
	}
	
	
	/**
	 * @Title: Enrollments  
	 * @Description: TODO 学习项目-报名
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String Enrollments(String project_id) {		
		return HttpRequest.post(enrollments_url(project_id)).query("access_token", token).data("{\r\n" + 
				"      \"access_token\": \""+token+"\"\r\n" + 
				"}")
				.send().body();
	}
			
			
			
	
	
	
	
	
}
