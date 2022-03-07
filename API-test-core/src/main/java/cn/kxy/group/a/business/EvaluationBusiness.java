package cn.kxy.group.a.business;

/**
 * @author wenlingzhi
 *2021年4月25日
 */

import cn.kxy.base.business.EnterpriseData;
import cn.kxy.base.business.EnterpriseDataUrl;
import cn.kxy.base.business.TokenData;
import cn.kxy.setting.bussiness.UserBusiness;
import com.lazy.common.utils.DateUtil;
import com.lazy.httpclient.utils.HttpRequest;

public class EvaluationBusiness {

	public static String evaluation_url = EnterpriseDataUrl.getEvaluationUrl();
	public static String enterprise_url = EnterpriseDataUrl.getEnterpriseUrl();
	public static String token = TokenData.getMangerToken();
	public static String enterpriseId = EnterpriseData.getEnterpriseId();
	public static String user_id = UserBusiness.getUserId();
	
	public static String ability_add_url = evaluation_url + "v2/"+enterpriseId+"/evaluation/ability/add/";
	public static String ability_query_url = evaluation_url + "v2/"+enterpriseId+"/evaluation/ability/query/";
	public static String ability_update_url(String ability_id) {
		return evaluation_url + "v2/"+enterpriseId+"/evaluation/ability/update/";
	}
	public static String setting_visible_url = evaluation_url + "v2/"+enterpriseId+"/user/"+user_id+"/evaluation/ability/setting_visible/";
	public static String evaluation_model_save_url = evaluation_url + "v2/"+enterpriseId+ "/evaluation_model/save/";
	public static String model_visible_range_setting_url = evaluation_url + "v2/"+enterpriseId+ "/evaluation_model/visible_range_setting/";
	public static String evaluation_models_list_url = evaluation_url + "v2/"+enterpriseId+ "/evaluation_models/";
	public static String delete_ability_url = evaluation_url + "v2/"+enterpriseId+ "/evaluation/abilities/delete/";
	public static String delete_model_url (String model_id) {
		return evaluation_url + "v2/"+enterpriseId+"/evaluation_model/"+model_id+"/delete/";
	}
	public static String tools_now_list_url = enterprise_url + "v2/"+enterpriseId+"/evaluation/tools_now_list";
	public static String add_tools_url = enterprise_url + "v2/"+enterpriseId+"/user/"+user_id+"/evaluation/add_tools";	
	public static String evaluation_tools_list_url = enterprise_url + "v2/"+enterpriseId+"/evaluation/tools/";
	public static String delete_evaluation_tool_url (String evaluation_tool_id) {
		return enterprise_url + "v2/"+enterpriseId+"/user/"+user_id+"/evaluation/"+evaluation_tool_id+"/delete/";
	}
	public static String evaluation_save_url = evaluation_url + "v1/"+enterpriseId+ "/evaluation/save/";
	public static String evaluation_unpublish_url(String evaluation_id) {
		return evaluation_url + "v1/"+enterpriseId+ "/evaluation/"+evaluation_id+"/unpublish/";
	}
	public static String evaluation_delete_url(String evaluation_id) {
		return evaluation_url + "v1/"+enterpriseId+ "/evaluation/"+evaluation_id+"/delete/";
	}
	public static String evaluation_submit_url(String evaluation_id) {
		return evaluation_url + "v2/"+enterpriseId+ "/users/"+user_id+"/evaluation/"+evaluation_id+"/submit/";
	}
	public static String ability_levels_url = evaluation_url + "v2/"+enterpriseId +"/evaluation/ability/levels/query/";
	public static String evaluation_model_report_url (String evaluation_id) {
		return evaluation_url + "v2/"+enterpriseId+ "/user/"+user_id+"/evaluation/"+evaluation_id+"/model/report/";
	}
	public static String evaluation_detail_query_url (String evaluation_id) {
		return enterprise_url + "v2/"+enterpriseId+ "/users/"+user_id+"/evaluation/"+evaluation_id+"/query/";
	}
	public static String my_evaluation_list_url = evaluation_url + "v2/"+enterpriseId +"/user/"+user_id+"/my_evaluation_list/";
	public static String getScoreRecord_url = enterprise_url + "score/getScoreRecord/";
	public static String ability_levels_list_url = evaluation_url + "v2/"+enterpriseId +"/evaluation/ability/levels/query/";
	public static String studyPlan_add_url = enterprise_url + "plan/study/add/";
	public static String resource_getList_url = enterprise_url + "course/resource/getList/";
	public static String myTask_getList_url = enterprise_url + "plan/myTask/getList/";
	public static String getEvaluationId_url = enterprise_url + "plan/myTask/getOne/";
	public static String open_evaluation_url (String studyPlan_evaluation_id) {
		return evaluation_url + "v1/"+enterpriseId+ "/user/"+user_id+"/evaluation/"+studyPlan_evaluation_id+"/open_evaluation/";
	}
	public static String study_projects_save_url = enterprise_url + "v2/"+enterpriseId +"/study_projects/save/";
	public static String study_projects_query_url = enterprise_url + "course/queryCourseByPage/";
	public static String study_project_detail_url(String project_id) {
		return enterprise_url + "v2/"+enterpriseId+"/users/"+user_id+"/study_projects/"+project_id+"/query/";
	}
	public static String start_study_url(String project_id) {
		return enterprise_url + "v2/"+enterpriseId+"/users/"+user_id+"/study_projects/"+project_id+"/start_study/";
	}
	public static String study_project_delete_url (String project_id) {
		return enterprise_url + "v2/"+enterpriseId +"/study_projects/"+project_id+"/delete/";
	}
	public static String delete_study_url = enterprise_url + "plan/study/delete/";
	public static String evaluation_list_url = evaluation_url + "v1/"+enterpriseId+"/evaluation/list/";
	public static String evaluation_detail_url (String evaluation_id) {
		return evaluation_url + "v1/"+enterpriseId+"/evaluation/"+evaluation_id+"/detail/";
	}
	public static String evaluation_detail_list_url (String evaluation_id) {
		return evaluation_url + "v2/"+enterpriseId+"/evaluation/"+evaluation_id+"/list/";
	}
	public static String evaluation_export_url (String evaluation_id) {
		return evaluation_url + "v1/"+enterpriseId+"/evaluation/"+evaluation_id+"/export/";
	}
	public static String evaluation_remind_url (String evaluation_id) {
		return evaluation_url + "v2/"+enterpriseId+"/evaluation/tasks/"+evaluation_id+"/remind/";
	}
	public static String evaluation_improve_remind_url (String evaluation_id) {
		return evaluation_url + "v2/"+enterpriseId+"/evaluation/tasks/"+evaluation_id+"/improve/remind/";
	}
	public static String evaluation_publish_url (String evaluation_id) {
		return evaluation_url + "v1/"+enterpriseId+"/evaluation/"+evaluation_id+"/publish/";
	}
	public static String evaluation_update_query_url (String evaluation_id) {
		return evaluation_url + "v1/"+enterpriseId+"/evaluation/"+evaluation_id+"/query/";
	}
	public static String evaluation_update_url (String evaluation_id) {
		return evaluation_url + "v1/"+enterpriseId+"/evaluation/"+evaluation_id+"/update/";
	}
	public static String evaluation_query_url(String evaluation_id) {
		return enterprise_url + "v2/"+enterpriseId+"/users/"+user_id+"/evaluation/"+evaluation_id+"/query/";
	}
	public static String queryCourseByPage_url = enterprise_url + "course/queryCourseByPage/";
	public static String set_ability_training_url(String evaluation_id) {
		return evaluation_url + "v2/"+enterpriseId+"/evaluation/set_ability_training/";
	}
	public static String study_promotion_course_url(String evaluation_id,String promotion_course_id) {
		return enterprise_url +"v2/"+enterpriseId+"/users/"+user_id+"/plan/"+evaluation_id+"/courses/"+promotion_course_id;
	}

	
	/**   
	 * @Title: TutorsLeverAdd  
	 * @Description: TODO  创建测评能力项
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String AbilityAdd(String abilityName){	
		return HttpRequest.post(ability_add_url).query("access_token", token).data("{\r\n" + 
				"      \"abilityName\": \""+abilityName+"\",\r\n" + 
				"      \"abilityType\": \"common\",\r\n" + 
				"      \"description\": \"test\",\r\n" + 
				"      \"abilityLevels\": [\r\n" + 
				"            {\r\n" + 
				"                  \"id\": \"1\",\r\n" + 
				"                  \"description\": \"test1\"\r\n" + 
				"            },\r\n" + 
				"            {\r\n" + 
				"                  \"id\": \"2\",\r\n" + 
				"                  \"description\": \"test2\"\r\n" + 
				"            },\r\n" + 
				"            {\r\n" + 
				"                  \"id\": \"3\",\r\n" + 
				"                  \"description\": \"test3\"\r\n" + 
				"            },\r\n" + 
				"            {\r\n" + 
				"                  \"id\": \"4\",\r\n" + 
				"                  \"description\": \"test4\"\r\n" + 
				"            },\r\n" + 
				"            {\r\n" + 
				"                  \"id\": \"5\",\r\n" + 
				"                  \"description\": \"test5\"\r\n" + 
				"            }\r\n" + 
				"      ],\r\n" + 
				"    \"access_token\":\""+token+"\"\r\n" + 
				"}\r\n" + 
				"")
				.send().body();
	}
	
	
	/**   
	 * @Title: AbilityQuery  
	 * @Description: TODO  查询测训能力项列表
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String AbilityQuery(String source,String name,String ability_type) {		
		return HttpRequest.get(ability_query_url).query("access_token", token).query("page_number","1").query("page_size","20")
				.query("source",source).query("name",name).query("ability_type",ability_type)
				.send().body();
	}
	
	/**   
	 * @Title: AbilityUpdate  
	 * @Description: TODO  编辑测评能力项
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String AbilityUpdate(String ability_id,String abilityName){	
		return HttpRequest.post(ability_update_url(ability_id)).query("access_token", token).data("{\r\n" + 
				"      \"id\": \""+ability_id+"\",\r\n" + 
				"      \"abilityName\": \""+abilityName+"\",\r\n" + 
				"      \"abilityType\": \"common\",\r\n" + 
				"      \"description\": \"test\",\r\n" + 
				"      \"abilityLevels\": [\r\n" + 
				"            {\r\n" + 
				"                  \"id\": \"1\",\r\n" + 
				"                  \"description\": \"test1\"\r\n" + 
				"            },\r\n" + 
				"            {\r\n" + 
				"                  \"id\": \"2\",\r\n" + 
				"                  \"description\": \"test2\"\r\n" + 
				"            },\r\n" + 
				"            {\r\n" + 
				"                  \"id\": \"3\",\r\n" + 
				"                  \"description\": \"test3\"\r\n" + 
				"            },\r\n" + 
				"            {\r\n" + 
				"                  \"id\": \"4\",\r\n" + 
				"                  \"description\": \"test4\"\r\n" + 
				"            },\r\n" + 
				"            {\r\n" + 
				"                  \"id\": \"5\",\r\n" + 
				"                  \"description\": \"test5\"\r\n" + 
				"            }\r\n" + 
				"      ],\r\n" + 
				"    \"access_token\":\""+token+"\"\r\n" + 
				"}\r\n" + 
				"")
				.send().body();
	}
	
	/**   
	 * @Title: SettingVisible  
	 * @Description: TODO  设置可见范围
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String SettingVisible(String ability_id,String visible_range){	
		return HttpRequest.post(setting_visible_url).query("access_token", token).data("{\r\n" + 
				"      \"ability_ids\": \""+ability_id+"\",\r\n" + 
				"      \"visible_range\": \""+visible_range+"\",\r\n" + 
				"      \"department_ids\": \"\",\r\n" + 
				"      \"group_ids\": \"\",\r\n" + 
				"      \"post_ids\": \"\",\r\n" + 
				"      \"user_ids\": \"\",\r\n" + 
				"      \"access_token\":\""+token+"\"\r\n" + 
				"}\r\n" + 
				"")
				.send().body();
	}
	
	/**   
	 * @Title: EvaluationModelSave  
	 * @Description: TODO  创建测评模型
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String EvaluationModelSave(String model_name,String ability_id){	
		return HttpRequest.post(evaluation_model_save_url).query("access_token", token).data("{\r\n" + 
				"      \"model_name\": \""+model_name+"\",\r\n" + 
				"      \"id\": \"\",\r\n" + 
				"      \"model_desc\": \"\",\r\n" + 
				"      \"ability_ids\": [\r\n" + 
				"            \""+ability_id+"\"\r\n" + 
				"      ],\r\n" + 
				"      \"model_type\": \"ability\",\r\n" + 
				"      \"visible_range\": \"all\",\r\n" + 
				"      \"group_ids\": \"\",\r\n" + 
				"      \"post_ids\": \"\",\r\n" + 
				"      \"user_ids\": \"\",\r\n" + 
				"      \"department_ids\": \"\",\r\n" + 
				"      \"access_token\":\""+token+"\"\r\n" + 
				"}\r\n" + 
				"")
				.send().body();
	}
	
	/**   
	 * @Title: EvaluationModelEdit  
	 * @Description: TODO  编辑测评模型
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String EvaluationModelEdit(String model_name,String model_id,String ability_id){	
		return HttpRequest.post(evaluation_model_save_url).query("access_token", token).data("{\r\n" + 
				"      \"model_name\": \""+model_name+"\",\r\n" + 
				"      \"id\": \""+model_id+"\",\r\n" + 
				"      \"model_desc\": \"\",\r\n" + 
				"      \"ability_ids\": [\r\n" + 
				"            \""+ability_id+"\"\r\n" + 
				"      ],\r\n" + 
				"      \"model_type\": \"ability\",\r\n" +
				"      \"visible_range\": \"all\",\r\n" + 
				"      \"group_ids\": \"\",\r\n" + 
				"      \"post_ids\": \"\",\r\n" + 
				"      \"user_ids\": \"\",\r\n" + 
				"      \"department_ids\": \"\",\r\n" + 
				"      \"access_token\":\""+token+"\"\r\n" + 
				"}\r\n" + 
				"")
				.send().body();
	}
	
	/**   
	 * @Title: ModelVisibleRangeSetting  
	 * @Description: TODO  测评模型可见范围
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String ModelVisibleRangeSetting(String model_id){	
		return HttpRequest.post(model_visible_range_setting_url).query("access_token", token).data("{\r\n" + 
				"      \"ids\": [\r\n" + 
				"            \""+model_id+"\"\r\n" + 
				"      ],\r\n" + 
				"      \"visible_range\": \"all\",\r\n" + 
				"      \"department_ids\": \"\",\r\n" + 
				"      \"group_ids\": \"\",\r\n" + 
				"      \"user_ids\": \"\",\r\n" + 
				"      \"post_ids\": \"\",\r\n" + 
				"      \"access_token\":\""+token+"\"\r\n" + 
				"}\r\n" + 
				"")
				.send().body();
	}
	
	/**   
	 * @Title: EvaluationModelsList  
	 * @Description: TODO  测训模型列表
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String EvaluationModelsList(String only_see_me,String key_word) {		
		return HttpRequest.get(evaluation_models_list_url).query("access_token", token).query("only_see_me",only_see_me)
				.query("key_word",key_word).send().body();
	}
	
	
	/**   
	 * @Title: DeleteAbility  
	 * @Description: TODO  删除测训能力项
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String DeleteAbility(String ability_ids) {		
		return HttpRequest.post(delete_ability_url).query("access_token", token).query("ability_ids",ability_ids).data("{\r\n" + 
				"      \"access_token\":\""+token+"\"\r\n" + 
				"}\r\n" + 
				"")
				.send().body();
	}
	
	
	/**   
	 * @Title: DeleteModel  
	 * @Description: TODO  删除测训模型
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String DeleteModel(String model_id) {		
		return HttpRequest.post(delete_model_url(model_id)).query("access_token", token).data("{\r\n" + 
				"      \"access_token\":\""+token+"\"\r\n" + 
				"}\r\n" + 
				"")
				.send().body();
	}
	
	
	/**   
	 * @Title: toolsNowList  
	 * @Description: TODO  添加测训工具时弹窗内的工具数据
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String toolsNowList() {		
	    return HttpRequest.get(tools_now_list_url).query("access_token", token).send().body();
	}
	
	
	/**   
	 * @Title: AddTools  
	 * @Description: TODO  添加测训工具
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String AddTools(String tool_id) {		
		return HttpRequest.post(add_tools_url).query("access_token", token).data("{\r\n" + 
				"      \"evaluation_tools\": [\r\n" + 
				"            \""+tool_id+"\"\r\n" + 
				"      ],\r\n" + 
				"      \"access_token\":\""+token+"\"\r\n" + 
				"}\r\n" + 
				"")
				.send().body();
	}
	
	/**   
	 * @Title: EvaluationToolsList  
	 * @Description: TODO  测训工具列表-按名称查询
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String EvaluationToolsList(String keyword) {		
	    return HttpRequest.get(evaluation_tools_list_url).query("access_token", token).query("keyword",keyword).send().body();
	}
	
	/**   
	 * @Title: DeleteEvaluationTool  
	 * @Description: TODO  删除测训工具
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String DeleteEvaluationTool(String evaluation_tool_id) {		
		return HttpRequest.post(delete_evaluation_tool_url(evaluation_tool_id)).query("access_token", token).data("{\r\n" + 
				"      \"access_token\":\""+token+"\"\r\n" + 
				"}\r\n" + 
				"")
				.send().body();
	}
	
	
	/**   
	 * @Title: EvaluationSave  
	 * @Description: TODO  创建测评任务
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String EvaluationSave(String title,String model_id,String term_type,String start_time,
			String end_time,String open_result,String is_get_score,String finish_promote,String status,
			String evaluation_tool_type,String evaluation_way_self,String evaluation_way_superior) {		
		return HttpRequest.post(evaluation_save_url).query("access_token", token).data("{\r\n" + 
				"      \"title\": \""+title+"\",\r\n" + 
				"      \"product_id\": \""+model_id+"\",\r\n" + 
				"      \"term_type\": \""+term_type+"\",\r\n" + 
				"      \"start_time\": \""+start_time+"\",\r\n" + 
				"      \"end_time\": \""+end_time+"\",\r\n" + 
				"      \"open_result\": \""+open_result+"\",\r\n" + 
				"      \"supervisor_Id\": \""+user_id+"\",\r\n" + 
				"      \"source\": \"evaluation\",\r\n" + 
				"      \"user_ids\": \""+user_id+"\",\r\n" + 
				"      \"department_ids\": \"\",\r\n" + 
				"      \"group_ids\": \"\",\r\n" + 
				"      \"post_ids\": \"\",\r\n" + 
				"      \"authority_range\": false,\r\n" + 
				"      \"status\": \""+status+"\",\r\n" + 
				"      \"model_id\": \"\",\r\n" + 
				"      \"finish_promote\": \""+finish_promote+"\",\r\n" + 
				"      \"certificate_id\": \"\",\r\n" + 
				"      \"is_get_score\": \""+is_get_score+"\",\r\n" + 
				"      \"study_score\": {\r\n" + 
				"            \"finish_score\": 1\r\n" + 
				"      },\r\n" + 
				"      \"evaluation_tool_type\": \""+evaluation_tool_type+"\",\r\n" + 
				"      \"evaluation_way_self\": \""+evaluation_way_self+"\",\r\n" + 
				"      \"evaluation_way_superior\": \""+evaluation_way_superior+"\",\r\n" + 
				"      \"access_token\":\""+token+"\"\r\n" + 
				"}\r\n" + 
				"")
				.send().body();
	}

	
	/**   
	 * @Title: EvaluationUnpublish  
	 * @Description: TODO  取消测评任务
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String EvaluationUnpublish(String evaluation_id) {		
		return HttpRequest.post(evaluation_unpublish_url(evaluation_id)).query("access_token", token).data("{\r\n" + 
				"      \"access_token\":\""+token+"\"\r\n" + 
				"}\r\n" + 
				"")
				.send().body();
	}
	
	
	/**   
	 * @Title: EvaluationPublish  
	 * @Description: TODO  发布测评任务
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String EvaluationPublish(String evaluation_id) {		
		return HttpRequest.post(evaluation_publish_url(evaluation_id)).query("access_token", token).data("{\r\n" + 
				"      \"access_token\":\""+token+"\"\r\n" + 
				"}\r\n" + 
				"")
				.send().body();
	}
	
	/**   
	 * @Title: EvaluationDelete  
	 * @Description: TODO  删除测评任务
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String EvaluationDelete(String evaluation_id) {		
		return HttpRequest.post(evaluation_delete_url(evaluation_id)).query("access_token", token).data("{\r\n" + 
				"      \"access_token\":\""+token+"\"\r\n" + 
				"}\r\n" + 
				"")
				.send().body();
	}
	
	/**   
	 * @Title: AbilityLevels  
	 * @Description: TODO  能力项等级
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String AbilityLevels() {		
	    return HttpRequest.get(ability_levels_url).query("access_token", token).send().body();
	}
	
	/**   
	 * @Title: EvaluationSubmit  
	 * @Description: TODO  开始测评
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String EvaluationSubmit(String evaluation_id,String lever_id,String model_id) {		
		return HttpRequest.post(evaluation_submit_url(evaluation_id)).query("access_token", token).data("{\r\n" + 
				"      \"submit_list\": [\r\n" + 
				"            {\r\n" + 
				"                  \"level_id\": \""+lever_id+"\",//答题时选择的答案，即能力等级id\r\n" + 
				"                  \"id\": \""+model_id+"\"//能力模型id，如果创建测评任务时选择多个模型，这里就会有多个字典，一个字典里是一个模型和一个答案（能力等级）\r\n" + 
				"            }\r\n" + 
				"      ],\r\n" + 
				"      \"access_token\":\""+token+"\"\r\n" + 
				"}\r\n" + 
				"")
				.send().body();
	}
	
	/**   
	 * @Title: EvaluationModelReport  
	 * @Description: TODO  PC端-查看能力模型类型的测评报告，工具类型是调用的第三方接口不做处理
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String EvaluationModelReport(String evaluation_id) {		
	    return HttpRequest.get(evaluation_model_report_url(evaluation_id)).query("access_token", token).send().body();
	}
	
	/**   
	 * @Title: EvaluationDetailQuery  
	 * @Description: TODO  PC端-学员端-学习进度+合格状态校验
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String EvaluationDetailQuery(String evaluation_id) {		
	    return HttpRequest.get(evaluation_detail_query_url(evaluation_id)).query("access_token", token).send().body();
	}
	
	
	/**   
	 * @Title: MyEvaluationList  
	 * @Description: TODO  PC端-我的测评任务列表
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String MyEvaluationList(String status,String keyword,String type) {		
	    return HttpRequest.get(my_evaluation_list_url).query("access_token", token).query("status",status)
	    		.query("keyword",keyword).query("type",type)
	    		.send().body();
	}
	
	
	/**   
	 * @Title: MobileMyEvaluationList  
	 * @Description: TODO  移动端-我的测评任务列表
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String MobileMyEvaluationList(String status,String keyword) {		
	    return HttpRequest.get(my_evaluation_list_url).query("access_token", token).query("status",status)
	    		.query("keyword",keyword)
	    		.send().body();
	}
	
	
	/**   
	 * @Title: GetScoreRecord  
	 * @Description: TODO  我的学分记录列表
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String GetScoreRecord() {		
	    return HttpRequest.get(getScoreRecord_url).query("access_token", token).send().body();
	}
	
	
	/**   
	 * @Title: AbilityLevelsList  
	 * @Description: TODO  查询能力项等级列表
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String AbilityLevelsList() {		
	    return HttpRequest.get(ability_levels_list_url).query("access_token", token).send().body();
	}
	
	
	/**   
	 * @Title: StudyPlanAdd  
	 * @Description: TODO  创建有岗位测评的学习任务
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String StudyPlanAdd(String title,String planCertificateId,String productId,String courseId) {		
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
	    		"      \"planCertificateId\": \""+planCertificateId+"\",\r\n" + 
	    		"      \"postIds\": \"\",\r\n" + 
	    		"      \"progress\": 100,\r\n" + 
	    		"      \"studyScore\": {\r\n" + 
	    		"            \"finishScore\": 5,\r\n" + 
	    		"            \"unFinishScore\": 0\r\n" + 
	    		"      },\r\n" + 
	    		"      \"scheduleSetting\": \"free_mode\",\r\n" + 
	    		"      \"studyTimeLimit\": 0,\r\n" + 
	    		"      \"faceRecognition\": \"off\",\r\n" + 
	    		"      \"supervisorId\": \""+user_id+"\",\r\n" + 
	    		"      \"supervisorPaper\": true,\r\n" + 
	    		"      \"authorityRange\": false,\r\n" + 
	    		"      \"times\": \"\",\r\n" + 
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
	    		"                              \"flag\": 6,\r\n" + 
	    		"                              \"title\": \""+title+"\",\r\n" + 
	    		"                              \"productId\": \""+productId+"\",\r\n" + 
	    		"                              \"modelId\": 0,\r\n" + 
	    		"                              \"id\": \"\",\r\n" + 
	    		"                              \"mappingId\": \"\",\r\n" + 
	    		"                              \"resource_lock\": false\r\n" + 
	    		"                        },\r\n" + 
	    		"                        {\r\n" + 
	    		"                              \"courseId\": \""+courseId+"\",\r\n" + 
	    		"                              \"courseSort\": 1,\r\n" + 
	    		"                              \"courseType\": 3,\r\n" + 
	    		"                              \"flag\": 1,\r\n" + 
	    		"                              \"mappingId\": \"\",\r\n" + 
	    		"                              \"resource_lock\": false\r\n" + 
	    		"                        }\r\n" + 
	    		"                  ]\r\n" + 
	    		"            }\r\n" + 
	    		"      ],\r\n" + 
	    		"      \"stagePass\": false,\r\n" + 
	    		"      \"finishEvaluation\": true,\r\n" + 
	    		"      \"openEvaluationResult\": false,\r\n" + 
	    		"      \"action\": \"published\",\r\n" + 
	    		"      \"free\": true,\r\n" + 
	    		"      \"official_price_str\": \"\",\r\n" + 
	    		"    \"access_token\":\""+token+"\"\r\n" + 
				"}\r\n" + 
				"")
	    		.send().body();
	}
	
	
	/**   
	 * @Title: ResourceGetList  
	 * @Description: TODO  获取课件列表
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String ResourceGetList() {		
	    return HttpRequest.get(resource_getList_url).query("access_token", token).query("resourceClassify","0")
	    		.send().body();
	}
	
	
	/**   
	 * @Title: MyTaskGetList   
	 * @Description: TODO  校验学员端-我的学习任务列表有数据
	 * @param: @param keyword
	 * @param: @param type
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */  
	public static String MyTaskGetList(String keyword) {
		return HttpRequest.get(myTask_getList_url).query("pageNumber","1").query("pageSize","20").query("keyword",keyword)
				.query("access_token", token).send().body();	
	}
	
	
	
	/**   
	 * @Title: GetEvaluationId   
	 * @Description: TODO  获取学习任务里岗位测评id
	 * @param: @param keyword
	 * @param: @param type
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */  
	public static String GetEvaluationId(String id) {
		return HttpRequest.get(getEvaluationId_url).query("access_token", token).query("id",id)
				.send().body();	
	}
	
	
	
	/**   
	 * @Title: OpenEvaluation   
	 * @Description: TODO  进入测评答题
	 * @param: @param keyword
	 * @param: @param type
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */  
	public static String OpenEvaluation(String studyPlan_evaluation_id) {
		return HttpRequest.post(open_evaluation_url(studyPlan_evaluation_id)).query("access_token", token).data("{\r\n" + 
				"      \"access_token\": \""+token+"\"\r\n" + 
				"}")
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
	public static String StudyProjectSaveStageContent(String project_id,String courseId,String productId) {		
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
				"                                    \"course_id\": \""+courseId+"\",\r\n" + 
				"                                    \"sort\": 0,\r\n" + 
				"                                    \"type\": 3,\r\n" + 
				"                                    \"resource_lock\": false\r\n" + 
				"                              },\r\n" + 
				"                              {\r\n" + 
				"                                    \"sort\": 1,\r\n" + 
				"                                    \"flag\": 6,\r\n" + 
				"                                    \"type\": 11,\r\n" + 
				"                                    \"evaluation\": {\r\n" + 
				"                                          \"id\": \"\",\r\n" + 
				"                                          \"title\": \"测评\",\r\n" + 
				"                                          \"product_id\": \""+productId+"\",\r\n" + 
				"                                          \"model_id\": 0\r\n" + 
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
		return HttpRequest.post(study_projects_save_url).query("access_token", token).data("{\r\n" + 
				"      \"save_step\": \"settings\",\r\n" + 
				"      \"id\": \""+project_id+"\",\r\n" + 
				"      \"settings\": {\r\n" + 
				"            \"study_time_limit\": 0,\r\n" + 
				"            \"face_recognition\": \"off\",\r\n" + 
				"            \"progress\": 100,\r\n" + 
				"            \"times\": \"\",\r\n" + 
				"            \"operation_times\": \"\",\r\n" + 
				"            \"finish_evaluation\": true,\r\n" + 
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
	 * @Title: DeleteStudy  
	 * @Description: TODO 删除学习任务
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String DeleteStudy(String study_id) {		
		return HttpRequest.post(delete_study_url).query("access_token", token).query("id",study_id).send().body();
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
	 * @Title: EvaluationList  
	 * @Description: TODO 测评任务列表
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String EvaluationList(String only_see_me,String keyword,String status) {		
		return HttpRequest.get(evaluation_list_url).query("access_token", token).query("page_number","1").query("page_size","20")
				.query("only_see_me",only_see_me).query("keyword",keyword).query("status",status).query("product_id","")
				.send().body();
	}
	
	/**   
	 * @Title: EvaluationDetail  
	 * @Description: TODO 测评任务数据页-基础信息
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String EvaluationDetail(String evaluation_id) {		
		return HttpRequest.get(evaluation_detail_url(evaluation_id)).query("access_token", token)
				.send().body();
	}
	
	
	/**   
	 * @Title: EvaluationDetailList
	 * @Description: TODO 测评任务数据页-列表数据
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String EvaluationDetailList(String evaluation_id,String keyword,String status,String promote_status) {		
		return HttpRequest.get(evaluation_detail_list_url(evaluation_id)).query("access_token", token).query("page_number","1")
				.query("page_size","20").query("keyword",keyword).query("status",status).query("promote_status",promote_status)
				.send().body();
	}
	
	
	/**   
	 * @Title: EvaluationExport
	 * @Description: TODO 测评任务数据页-导出
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String EvaluationExport(String evaluation_id) {		
		return HttpRequest.get(evaluation_export_url(evaluation_id)).query("access_token", token).query("user_ids",user_id)
				.send().body();
	}
	
	/**   
	 * @Title: EvaluationRemind
	 * @Description: TODO 测评任务数据页-催促测评
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String EvaluationRemind(String evaluation_id) {		
		return HttpRequest.get(evaluation_remind_url(evaluation_id)).query("access_token", token)
				.send().body();
	}
	
	/**   
	 * @Title: EvaluationImproveRemind
	 * @Description: TODO 测评任务数据页-催促提升
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String EvaluationImproveRemind(String evaluation_id) {		
		return HttpRequest.get(evaluation_improve_remind_url(evaluation_id)).query("access_token", token)
				.send().body();
	}

	
	/**   
	 * @Title: EvaluationUpdateQuery
	 * @Description: TODO 获取测评任务编辑页信息
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String EvaluationUpdateQuery(String evaluation_id) {		
		return HttpRequest.get(evaluation_update_query_url(evaluation_id)).query("access_token", token)
				.send().body();
	}
	
	/**   
	 * @Title: EvaluationUpdate
	 * @Description: TODO 测评任务-编辑
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String EvaluationUpdate(String evaluation_id,String title,String model_id,String term_type,
			String open_result,String status,String is_get_score,String evaluation_tool_type,String evaluation_way_self,
			String evaluation_way_superior) {		
		return HttpRequest.post(evaluation_update_url(evaluation_id)).query("access_token", token).data("{\r\n" + 
				"      \"title\": \""+title+"\",\r\n" + 
				"      \"product_id\": \""+model_id+"\",\r\n" + 
				"      \"term_type\": \""+term_type+"\",\r\n" + 
				"      \"start_time\": \"\",\r\n" + 
				"      \"end_time\": \"\",\r\n" + 
				"      \"open_result\": \""+open_result+"\",\r\n" + 
				"      \"supervisor_Id\": \""+user_id+"\",\r\n" + 
				"      \"source\": \"evaluation\",\r\n" + 
				"      \"user_ids\": \""+user_id+"\",\r\n" + 
				"      \"department_ids\": \"\",\r\n" + 
				"      \"group_ids\": \"\",\r\n" + 
				"      \"post_ids\": \"\",\r\n" + 
				"      \"authority_range\": false,\r\n" + 
				"      \"status\": \""+status+"\",\r\n" + 
				"      \"model_id\": \"\",\r\n" + 
				"      \"finish_promote\": false,\r\n" + 
				"      \"certificate_id\": \"\",\r\n" + 
				"      \"is_get_score\": \""+is_get_score+"\",\r\n" + 
				"      \"study_score\": {\r\n" + 
				"            \"finish_score\": 1\r\n" + 
				"      },\r\n" + 
				"      \"evaluation_tool_type\": \""+evaluation_tool_type+"\",\r\n" + 
				"      \"evaluation_way_self\": \""+evaluation_way_self+"\",\r\n" + 
				"      \"evaluation_way_superior\": \""+evaluation_way_superior+"\",\r\n" + 
				"      \"access_token\": \""+token+"\"\r\n" + 
				"}")
				.send().body();
	}

	
	
	/**   
	 * @Title: EvaluationQuery
	 * @Description: TODO    移动端获取测评任务的course_id
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String EvaluationQuery(String evaluation_id) {		
		return HttpRequest.get(evaluation_query_url(evaluation_id)).query("access_token", token)
				.send().body();
	}
	
	
	
	
	/**   
	 * @Title: QueryCourseByPage
	 * @Description: TODO    获取课程列表
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String QueryCourseByPage() {		
		return HttpRequest.get(queryCourseByPage_url).query("access_token", token).query("queryType","0").query("scope","0")
				.query("filterQuota","false").query("courseType","course")
				.send().body();
	}
	
	
	
	/**   
	 * @Title: SetAbilityTraining
	 * @Description: TODO    设置提升方案课程
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String SetAbilityTraining(String evaluation_id,String promotion_course_id) {		
		return HttpRequest.post(set_ability_training_url(evaluation_id)).query("access_token", token).query("source","self")
				.data("{\r\n" + 
				"      \"is_batch_operation\": false,\r\n" + 
				"      \"ids\": [\r\n" + 
				"            \""+evaluation_id+"\"\r\n" + 
				"      ],\r\n" + 
				"      \"training_courses\": [\r\n" + 
				"            {\r\n" + 
				"                  \"level\": \"1\",\r\n" + 
				"                  \"course_ids\": [\r\n" + 
				"                        \""+promotion_course_id+"\"\r\n" + 
				"                  ]\r\n" + 
				"            },\r\n" + 
				"            {\r\n" + 
				"                  \"level\": \"2\",\r\n" + 
				"                  \"course_ids\": []\r\n" + 
				"            },\r\n" + 
				"            {\r\n" + 
				"                  \"level\": \"3\",\r\n" + 
				"                  \"course_ids\": []\r\n" + 
				"            },\r\n" + 
				"            {\r\n" + 
				"                  \"level\": \"4\",\r\n" + 
				"                  \"course_ids\": []\r\n" + 
				"            },\r\n" + 
				"            {\r\n" + 
				"                  \"level\": \"5\",\r\n" + 
				"                  \"course_ids\": []\r\n" + 
				"            }\r\n" + 
				"      ],\r\n" + 
				"      \"access_token\": \""+token+"\"\r\n" + 
				"}")
				.send().body();
	}
	
	
	
	/**   
	 * @Title: StudyCourse_id
	 * @Description: TODO    移动端学习提升方案的课程
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String StudyPromotionCourse(String evaluation_id,String promotion_course_id) {		
		return HttpRequest.get(study_promotion_course_url(evaluation_id,promotion_course_id)).query("access_token", token)
				.query("study_type","evaluation")
				.send().body();
	}
	
}
