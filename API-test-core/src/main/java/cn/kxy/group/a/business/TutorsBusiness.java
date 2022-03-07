package cn.kxy.group.a.business;

/**
 * @author wenlingzhi
 *2021年2月4日
 */

import cn.kxy.base.business.EnterpriseData;
import cn.kxy.base.business.EnterpriseDataUrl;
import cn.kxy.base.business.TokenData;
import cn.kxy.setting.bussiness.UserBusiness;
import com.lazy.httpclient.utils.HttpRequest;

public class TutorsBusiness {

	public static String enterprise_url = EnterpriseDataUrl.getTutorsUrl();
	public static String Enterprise_url = EnterpriseDataUrl.getEnterpriseUrl();
	public static String token = TokenData.getMangerToken();
	public static String enterpriseId = EnterpriseData.getEnterpriseId();
	public static String user_id = UserBusiness.getUserId();
	public static String tutors_lever_add_url = enterprise_url + "v1/"+enterpriseId+"/tutors/levels/add/";

	public static String update_status_url (String tutor_id) {
		return enterprise_url + "v1/"+enterpriseId+"/tutors/"+"/"+tutor_id+"/"+"/update/status/";
	}

	public static String update_tutors_lever_url(String lever_id) {
		return enterprise_url + "v1/"+enterpriseId+"/tutors/levels/"+lever_id+"/update/";
	}
	public static String tutors_add_url = enterprise_url + "v1/"+enterpriseId+"/tutors/add/";
	public static String update_tutors_url(String tutor_id) {
		return enterprise_url + "v1/"+enterpriseId+"/tutors/"+"/"+tutor_id+"/"+"/update/";
	}
	public static String apprentice_add_url (String tutor_id) {
		return enterprise_url + "v1/"+enterpriseId+"/tutors/"+"/"+tutor_id+"/"+"/apprentice/add/";
	}
	public static String save_or_update_base_info_url = Enterprise_url + "v2/"+enterpriseId+"/employees/save_or_update_base_info/";
	public static String update_content_url = Enterprise_url + "v2/"+enterpriseId+"/employees/update_content/";
	public static String update_setting_publish_url = Enterprise_url + "v2/"+enterpriseId+"/employees/update_setting_publish/";
	public static String getList_url = Enterprise_url + "plan/employeeTrain/getList/";
	public static String employee_train_assign_url (String assign_id) {
		return Enterprise_url + "v2/"+enterpriseId+"/employee_train/"+"/"+assign_id+"/"+"/assign/";
	}
	//获取选择导师和辅导接口路径里的id
	public static String getOne_url = Enterprise_url + "plan/myTask/getOne/";
	public static String save_url (String save_course_id) {
		return Enterprise_url + "v2/"+enterpriseId+"/users/"+user_id+"/operations/"+"/"+save_course_id+"/"+"/save/";
	}
	public static String choose_tutor_url (String employee_train_id,String choose_tutor_course_id) {
		return Enterprise_url + "v2/"+enterpriseId+"/users/"+user_id+"/plans/"+employee_train_id+"/tutorials/"+
	          choose_tutor_course_id+"/choose_tutor/"+user_id;
	}
	public static String apprentices_list_url(String tutor_id) {
		return enterprise_url + "v1/"+enterpriseId+"/tutors/"+ tutor_id+"/apprentices/list/";
	}
	//待辅导列表
	public static String tutorials_list_url = Enterprise_url + "v2/"+enterpriseId+"/users/"+user_id+"/tutorials/list/";
	//待辅导详情页
	public static String tutorials_detail_url (String tutorials_id) {
		return Enterprise_url + "v2/"+enterpriseId+"/tutorials/"+tutorials_id+"/apprentice/list/";
	}
	//辅导进行辅导
	public static String tutorials_submit_url (String tutorials_id) {
		return Enterprise_url + "v2/"+enterpriseId+"/users/"+user_id+"/tutorials/"+tutorials_id+"/submit/";
	}
	//学员评估辅导
	public static String tutorials_evaluation_url (String employee_train_id,String tutorials_id) {
		return Enterprise_url + "v2/"+enterpriseId+"/plans/"+employee_train_id+"/tutorials/"+tutorials_id+"/evaluation/";
	}
	//学员查看辅导详情
	public static String tutorials_info_url (String tutorials_id) {
		return Enterprise_url + "v2/"+enterpriseId+"/tutorials/"+tutorials_id+"/tutors/"+user_id+"/apprentices/"+user_id+"/info/";
	}
	
	public static String tutor_list_url = enterprise_url + "v1/"+enterpriseId+"/tutors/list/";
	public static String lever_delete_url (String lever_id) {
		return enterprise_url + "v1/"+enterpriseId+"/tutors/levels/"+lever_id+"/delete/";
	} 
	public static String lever_list_url = enterprise_url + "v1/"+enterpriseId+"/tutors/levels/list/";
	public static String tutor_delete_url (String tutor_id) {
		return enterprise_url + "v1/"+enterpriseId+"/tutors/"+tutor_id+"/delete/";
	} 
	public static String delete_employ_train_task = Enterprise_url + "/plan/employeeTrain/deleteEmployTrainTask/";
	public static String employ_train_monitors_task(String employee_train_id) {
		return Enterprise_url + "v2/"+enterpriseId+ "/plans/"+employee_train_id+"/monitors/";
	}
	public static String downLoadTemplate_url = Enterprise_url + "v2/"+enterpriseId+"/tutor/downLoadTemplate/";
	public static String lecturer_downLoadTemplate_url = Enterprise_url + "v2/"+enterpriseId+"/lecturer/downLoadTemplate/";
	
	
	/**   
	 * @Title: TutorsLeverAdd  
	 * @Description: TODO  创建导师等级
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String TutorsLeverAdd(String name) {	
		return HttpRequest.post(tutors_lever_add_url).query("access_token", token).data("{\r\n" + 
				"  \"name\":\""+name+"\",\r\n" + 
				"  \"standard\":\"test standard\",\r\n" + 
				"  \"access_token\":\""+token+"\"\r\n" + 
				"}\r\n" + 
				"")
				.send().body();
	}
	
	/**   
	 * @Title: UpDateStatus  
	 * @Description: TODO  开启/停用导师
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String UpDateStatus(String tutor_id,String status) {	
		return HttpRequest.post(update_status_url(tutor_id)).query("access_token", token).data("{\r\n" + 
				"  \"status\":\""+status+"\",\r\n" + 
				"  \"access_token\":\""+token+"\"\r\n" + 
				"}\r\n" + 
				"")
				.send().body();
	}
	
	
	/**   
	 * @Title: UpdateTutorsLever  
	 * @Description: TODO  编辑导师等级
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String UpdateTutorsLever(String lever_id,String LeverName) {	
		return HttpRequest.post(update_tutors_lever_url(lever_id)).query("access_token", token).data("{\r\n" + 
				"    \"id\": \""+lever_id+"\",\r\n" + 
				"    \"name\": \""+LeverName+"\",\r\n" + 
				"    \"standard\": \"edit standard\",\r\n" + 
				"    \"access_token\":\""+token+"\"\r\n" + 
				"}\r\n" + 
				"")
				.send().body();
	}
	
	/**   
	 * @Title: TutorsLeverAdd  
	 * @Description: TODO  创建导师
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String TutorsAdd(String level_id) {	
		return HttpRequest.post(tutors_add_url).query("access_token", token).data("{\r\n" + 
				"  \"user_id\":\""+user_id+"\",\r\n" + 
				"  \"gender\":\"female\",\r\n" + 
				"  \"level_id\":\""+level_id+"\",\r\n" + 
				"  \"description\":\"\",\r\n" + 
				"  \"avatar\":\"\",\r\n" + 
				"  \"access_token\":\""+token+"\"\r\n" + 
				"}\r\n" + 
				"")
				.send().body();
	}
	
	/**   
	 * @Title: UpdateTutors  
	 * @Description: TODO  编辑导师
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String UpdateTutors(String tutor_id) {	
		return HttpRequest.post(update_tutors_url(tutor_id)).query("access_token", token).data("{\r\n" + 
				"  \"user_id\":\""+user_id+"\",\r\n" + 
				"  \"gender\":\"female\",\r\n" + 
				"  \"level_id\":\"\",\r\n" + 
				"  \"description\":\"编辑导师信息\",\r\n" + 
				"  \"department_id\":\"\",\r\n" + 
				"  \"avatar\":\"\",\r\n" + 
				"  \"access_token\":\""+token+"\"\r\n" + 
				"}\r\n" + 
				"")
				.send().body();
	}
	
	/**   
	 * @Title: ApprenticeAdd  
	 * @Description: TODO  添加带教人员接口
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String ApprenticeAdd(String tutor_id) {
		return HttpRequest.post(apprentice_add_url(tutor_id)).query("access_token", token).data("{\r\n" + 				
				"  \"user_ids\": [\r\n" + 
				"    \""+user_id+"\"\r\n" + 
				"  ], \r\n" + 				
				"	\"access_token\": \""+token+"\"\r\n" + 
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
	public static String SaveOrUpdateBaseInfo(String train_name) {	
		String current_time = String.valueOf(System.currentTimeMillis());
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
	public static String UpdateContent(String employee_train_id) {
		return HttpRequest.post(update_content_url).query("access_token", token).data("{\r\n" + 
				"    \"plan_id\": \""+employee_train_id+"\",\r\n" + 
				"    \"stage_json\": [\r\n" + 
				"        {\r\n" + 
				"            \"content\": \"\",\r\n" + 
				"            \"stage_name\": \"阶段一\",\r\n" + 
				"            \"is_order\": false,\r\n" + 
				"            \"stage_sort\": 1,\r\n" + 
				"            \"resources\": [\r\n" + 
				"                {\r\n" + 
				"                    \"operation\": {\r\n" + 
				"                        \"id\": \"\",\r\n" + 
				"                        \"title\": \"导师制\",\r\n" + 
				"                        \"content\": \"test\",\r\n" + 
				"                        \"type\": \"sc\",\r\n" + 
				"                        \"resources\": [],\r\n" + 
				"                        \"score_setting\": {\r\n" + 
				"                            \"scoreSwitch\": \"off\",\r\n" + 
				"                            \"score\": 100\r\n" + 
				"                        },\r\n" + 
				"                        \"workflow_json\": {\r\n" + 
				"                            \"type\": \"tutor\",\r\n" + 
				"                            \"userIds\": []\r\n" + 
				"                        },\r\n" + 
				"                        \"sort\": 0\r\n" + 
				"                    },\r\n" + 
				"                    \"course_type\": 10,\r\n" + 
				"                    \"course_sort\": 0,\r\n" + 
				"                    \"flag\": 5\r\n" + 
				"                },\r\n" + 
				"                {\r\n" + 
				"                    \"course_id\": \"\",\r\n" + 
				"                    \"tutorials\": {\r\n" + 
				"                        \"id\": \"\",\r\n" + 
				"                        \"title\": \"导师制\",\r\n" + 
				"                        \"content\": \"test辅导\",\r\n" + 
				"                        \"model\": \"resource_evaluation\",\r\n" + 
				"                        \"tutor_model\": \"tutor\",\r\n" + 
				"                        \"evaluation_type\": \"satisfaction\",\r\n" + 
				"                        \"user_id\": \"\",\r\n" + 
				"                        \"user_name\": \"\"\r\n" + 
				"                    },\r\n" + 
				"                    \"course_sort\": 1,\r\n" + 
				"                    \"course_type\": 12,\r\n" + 
				"                    \"flag\": 7\r\n" + 
				"                }\r\n" + 
				"            ]\r\n" + 
				"        }\r\n" + 
				"    ],\r\n" + 
				"    \"stage_pass\": false,\r\n" + 
				"    \"access_token\":\""+token+"\"\r\n" + 
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
				"    \"plan_id\": \""+employee_train_id+"\",\r\n" + 
				"    \"save_step\": 3,\r\n" + 
				"    \"override_sub_dept\": 1,\r\n" + 
				"    \"is_new_employee\": false,\r\n" + 
				"    \"department_ids\": \"1\",\r\n" + 
				"    \"post_ids\": \"\",\r\n" + 
				"    \"study_time_limit\": 0,\r\n" + 
				"    \"face_recognition\": \"off\",\r\n" + 
				"    \"progress\": 100,\r\n" + 
				"    \"times\": \"\",\r\n" + 
				"    \"operation_times\": 1,\r\n" + 
				"    \"certificate_id\": \"\",\r\n" + 
				"    \"study_score\": \"{\\\"finishScore\\\":0,\\\"unFinishScore\\\":0}\",\r\n" + 
				"    \"supervisor_id\": \""+user_id+"\",\r\n" + 
				"    \"supervisor_paper\": true,\r\n" + 
				"    \"is_get_score\": true,\r\n" + 
				"    \"access_token\":\""+token+"\"\r\n" + 
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
	 * @Title: GetOne  
	 * @Description: TODO  获取选择导师和辅导接口路径里的id
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String GetOne(String employee_train_id) {		
		return HttpRequest.get(getOne_url).query("access_token", token).query("id",employee_train_id).send().body();
	}
	
//	/**   
//	 * @Title: save  
//	 * @Description: TODO  选择导师
//	 * @param: @return      
//	 * @return: String      
//	 * @throws   
//	 */ 
//	public static String Save(String save_course_id) {		
//		return HttpRequest.post(save_url(save_course_id)).query("access_token", token).data("{\r\n" + 
//				"    \"approval_user\": \""+user_id+"\",\r\n" + 
//				"    \"operation\": [\r\n" + 
//				"        {\r\n" + 
//				"            \"content\": \"test\",\r\n" + 
//				"            \"step\": 1,\r\n" + 
//				"            \"resources\": []\r\n" + 
//				"        }\r\n" + 
//				"    ],\r\n" + 
//				"    \"operation_source\": \"study\",\r\n" + 
//				"    \"access_token\":\""+token+"\"\r\n" + 
//				"}")
//				.send().body();
//	}
	
	/**   
	 * @Title: ChooseTutor  
	 * @Description: TODO  选择导师辅导
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String ChooseTutor(String employee_train_id,String choose_tutor_course_id) {	
		return HttpRequest.post(choose_tutor_url(employee_train_id,choose_tutor_course_id)).query("access_token", token).
				send().body();
	}
	
	/**   
	 * @Title: ApprenticesList  
	 * @Description: TODO  导师带教详情
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String ApprenticesList(String tutor_id) {	
		return HttpRequest.get(apprentices_list_url(tutor_id)).query("access_token", token).
				send().body();
	}
	
	/**   
	 * @Title: TutorialsList  
	 * @Description: TODO  辅导-待辅导列表
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String TutorialsList(String keyword,String status) {	
		return HttpRequest.get(tutorials_list_url).query("access_token", token).query("keyword",keyword)
				.query("status",status).send().body();
	}
	
	/**   
	 * @Title: TutorialsDetail  
	 * @Description: TODO  待辅导详情页
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String TutorialsDetail(String tutorials_id,String status) {	
		return HttpRequest.get(tutorials_detail_url(tutorials_id)).query("access_token", token).query("status",status).
				send().body();
	}
	
	/**   
	 * @Title: TutorialsSubmit  
	 * @Description: TODO  辅导进行辅导
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String TutorialsSubmit(String tutorials_id) {	
		return HttpRequest.post(tutorials_submit_url(tutorials_id)).query("access_token", token).data("{\r\n" + 
				"    \"user_ids\": [\r\n" + 
				"        \""+user_id+"\"\r\n" + 
				"    ],\r\n" + 
				"    \"status\": \"1\",\r\n" + 
				"    \"steps\": [\r\n" + 
				"        {\r\n" + 
				"            \"content\": \"tutorials  test\",\r\n" + 
				"            \"step\": 1,\r\n" + 
				"            \"resources\": []\r\n" + 
				"        }\r\n" + 
				"    ],\r\n" + 
				"    \"access_token\":\""+token+"\"\r\n" + 
				"}").
				send().body();
	}
	
	/**   
	 * @Title: TutorialsEvaluation  
	 * @Description: TODO  学员评估辅导
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String TutorialsEvaluation(String employee_train_id,String tutorials_id) {	
		return HttpRequest.post(tutorials_evaluation_url(employee_train_id,tutorials_id)).query("access_token", token).data("{\r\n" + 
				"	\"evaluation\": \"evaluation test\",\r\n" + 
				"	\"score\": \"10\",\r\n" + 
				"   \"access_token\":\""+token+"\"\r\n" + 
				"}").
				send().body();
	}
	
	/**   
	 * @Title: TutorialsInfo  
	 * @Description: TODO  学员查看辅导详情
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String TutorialsInfo(String tutorials_id,String status) {	
		return HttpRequest.get(tutorials_info_url(tutorials_id)).query("access_token", token).query("status",status).
				send().body();
	}
	
	/**   
	 * @Title: TutorList  
	 * @Description: TODO  查询导师列表
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String TutorList( ) {	
		return HttpRequest.get(tutor_list_url).query("access_token", token).
				send().body();
	}
	
	public static String TutorList(String status) {	
		return HttpRequest.get(tutor_list_url).query("access_token", token).query("status",status).
				send().body();
	}
	
	/**   
	 * @Title: LeverDelete  
	 * @Description: TODO  删除导师等级
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String LeverDelete(String lever_id) {	
		return HttpRequest.post(lever_delete_url(lever_id)).query("access_token", token).
				send().body();
	}
	
	/**   
	 * @Title: LeverList  
	 * @Description: TODO  查询导师等级列表
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String LeverList(String keyword) {	
		return HttpRequest.get(lever_list_url).query("access_token", token).query("keyword",keyword)
				.send().body();
	}
	
	/**   
	 * @Title: LeverDelete  
	 * @Description: TODO  删除导师
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String ToturDelete(String tutor_id) {	
		return HttpRequest.post(tutor_delete_url(tutor_id)).query("access_token", token).
				send().body();
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
	 * @Title: EmployTrainMonitorsTask  
	 * @Description: TODO  新员工培训数据监控按时间筛选
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */
	public static String EmployTrainMonitorsTask(String employee_train_id,String start_time,String end_time) {
		return HttpRequest.get(employ_train_monitors_task(employee_train_id)).query("access_token", token).query("queryTaskInfo","true")
				.query("taskStatus","0").query("planId",employee_train_id).query("start_time",start_time)
				.query("end_time",end_time)
				.send().body();
	}
	
	/**   
	 * @Title: DownLoadTemplate  
	 * @Description: TODO  导师下载模板
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String DownLoadTemplate(String template_type) {	
		return HttpRequest.get(downLoadTemplate_url).query("access_token", token).query("template_type",template_type)
				.send().body();
	}
	
	
	
	/**   
	 * @Title: LecturerDownLoadTemplate  
	 * @Description: TODO  讲师下载模板
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String LecturerDownLoadTemplate(String template_type) {	
		return HttpRequest.get(lecturer_downLoadTemplate_url).query("access_token", token).query("template_type",template_type)
				.send().body();
	}

	
	
}
