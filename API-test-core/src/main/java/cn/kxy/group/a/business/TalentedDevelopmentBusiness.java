package cn.kxy.group.a.business;
/**
 * @author wenlingzhi
 *2021年9月1日
 */

import cn.kxy.base.business.EnterpriseData;
import cn.kxy.base.business.EnterpriseDataUrl;
import cn.kxy.base.business.TokenData;
import cn.kxy.setting.bussiness.UserBusiness;
import com.lazy.httpclient.utils.HttpRequest;

public class TalentedDevelopmentBusiness {
	public static String enterprise_url = EnterpriseDataUrl.getEnterpriseUrl();
	public static String talentability_url = EnterpriseDataUrl.getTalentAbilityUrl();
	public static String evaluation_url = EnterpriseDataUrl.getEvaluationUrl();
	public static String dictionaryservice_url = EnterpriseDataUrl.getDictionaryServiceUrl();
	public static String token = TokenData.getMangerToken();
	public static String enterpriseId = EnterpriseData.getEnterpriseId();
	public static String user_id = UserBusiness.getUserId();
	public static String add_passage_classify_url = talentability_url + "v1/"+enterpriseId+"/post/passage_classify/";
	public static String add_passage_url = talentability_url + "v1/"+enterpriseId+"/post/post_passages/";
	public static String delete_passage_url (String classify_id) {
		return talentability_url + "v1/"+enterpriseId+"/post/passage_classify/"+classify_id;
	}
	public static String get_userpost_url = enterprise_url + "v2/"+enterpriseId+"/users/";
	public static String post_mapping_url = talentability_url + "v1/"+enterpriseId+"/post/post_mapping/";
	public static String post_ranks_url = talentability_url + "v1/"+enterpriseId+"/post/post_ranks/";
	public static String delete_post_rank_url (String rank_id) {
		return talentability_url + "v1/"+enterpriseId+"/post/post_ranks/"+rank_id;
	}
	public static String delete_post_mapping_url (String delete_id) {
		return talentability_url + "v1/"+enterpriseId+"/post/post_mapping/"+delete_id;
	}
	public static String delete_post_passages_url (String passage_id) {
		return talentability_url + "v1/"+enterpriseId+"/post/post_passages/"+passage_id;
	}
	public static String query_passage_classify_url = talentability_url + "v1/"+enterpriseId+"/post/passage_classify/";
	public static String query_post_passages_url (String classify_id) {
		return talentability_url + "v1/"+enterpriseId+"/post/post_passages/"+classify_id;
	}
	public static String post_qualifications_list_url = talentability_url + "v1/"+enterpriseId+"/post/qualifications/list/";
	public static String find_structure_url = talentability_url + "v1/"+enterpriseId+"/classify/find_structure/";
	public static String ability_model_list_url = talentability_url + "v1/"+enterpriseId+"/post/ability_model/list/";
	public static String ability_model_detail_url (String post_id) {
		return talentability_url + "v1/"+enterpriseId+"/post/ability_model/"+post_id;
	}
	public static String ability_model_base_url = talentability_url + "v1/"+enterpriseId+"/post/ability_model/";
	public static String comparison_url = talentability_url + "v1/"+enterpriseId+"/post/qualifications/comparison/";
	public static String search_url = dictionaryservice_url + "v2/dict/search/";
	public static String qualifications_detail_url (String qualification_id) {
		return talentability_url + "v1/"+enterpriseId+"/post/qualifications/"+qualification_id;
	}
	public static String project_list_url = enterprise_url + "v2/"+enterpriseId+"/study_projects/talent/list/";
	public static String post_project_url = talentability_url + "v1/"+enterpriseId+"/post/ability_model/post_project/";
	public static String submit_ability_model_url (String post_ability_mode_id) {
		return talentability_url + "v1/"+enterpriseId+"/post/ability_model/issue/"+post_ability_mode_id;
	}
	public static String scan_ability_model_url (String post_id) {
		return talentability_url + "v1/"+enterpriseId+"/post/ability_model/"+post_id;
	}
	public static String ability_model_export_url = talentability_url + "v1/"+enterpriseId+"/post/ability_model/project/export/";
	public static String disable_qualifications_url (String qualification_id) {
		return talentability_url + "v1/"+enterpriseId+"/post/qualifications/disable/"+qualification_id;
	}
	public static String qualification_post_url = talentability_url + "v1/"+enterpriseId+"/post/qualification_post/list/";
	public static String evaluation_models_url = evaluation_url + "v2/"+enterpriseId+"/evaluation_models/";
	public static String evaluation_result_url (String evaluation_models_id) {
		return talentability_url + "v1/"+enterpriseId+"/post/eva_result/" + evaluation_models_id;
	}
	public static String evaluation_result_detail_url (String eva_result_id) {
		return talentability_url + "v1/"+enterpriseId+"/post/eva_user_result/" + eva_result_id;
	}
	public static String config_save_url = evaluation_url + "v2/"+enterpriseId+"/post/plan/config/save/";
	public static String user_post = talentability_url + "v1/"+enterpriseId+"/post/post_passages/user/post/";
	public static String post_passages_url (String post_id) {
		return talentability_url + "v1/"+enterpriseId+"/post/post_passages/post/" + post_id;
	}
	public static String model_detail_url (String post_id) {
		return talentability_url + "v1/"+enterpriseId+"/post/ability_model_detail/post/" + post_id;
	}
	public static String linkInfo_url (String qualification_id) {
		return talentability_url + "v1/"+enterpriseId+"/post/qualification/linkInfo/" + qualification_id;
	}
	public static String add_qualification_url  = talentability_url + "v1/"+enterpriseId+"/post/qualifications/";
	public static String ability_list_url = evaluation_url + "v2/"+enterpriseId+"/evaluation/ability/query/";
	public static String delete_qualification_url (String qualification_id) {
		return talentability_url + "v1/"+enterpriseId+"/post/qualifications/"+qualification_id;
	}
	
	/**   
	 * @Title: AddPassageClassify  
	 * @Description: TODO      新增岗位通道分类
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String AddPassageClassify(String classifyname,String sort) {	
		return HttpRequest.post(add_passage_classify_url).query("access_token", token).data("{\r\n" + 
				"      \"enterprise_id\": \""+enterpriseId+"\",\r\n" + 
				"      \"name\": \""+classifyname+"\",\r\n" + 
				"      \"sort\": \""+sort+"\",\r\n" + 
				"      \"access_token\": \""+token+"\"\r\n" + 
				"}")
				.send().body();
	}
		
	
	/**   
	 * @Title: AddPassage  
	 * @Description: TODO      新增岗位通道
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String AddPassage(String passagename,String sort,String classify_id) {	
		return HttpRequest.post(add_passage_url).query("access_token", token).data("{\r\n" + 
				"      \"app_id\": \"zlnt\",\r\n" + 
				"      \"enterprise_id\": \""+enterpriseId+"\",\r\n" + 
				"      \"name\": \""+passagename+"\",\r\n" + 
				"      \"sort\": \""+sort+"\",\r\n" + 
				"      \"classify_id\": \""+classify_id+"\",\r\n" + 
				"      \"access_token\": \""+token+"\"\r\n" + 
				"}")
				.send().body();
	}
	
	
	
	/**   
	 * @Title: DeletePassageClassify  
	 * @Description: TODO      删除岗位通道分类
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String DeletePassageClassify(String classify_id) {	
		return HttpRequest.delete(delete_passage_url(classify_id)).query("access_token", token).data("{\r\n" + 
				"      \"access_token\": \""+token+"\"\r\n" + 
				"}")
				.send().body();
	}
	
	
	
	/**   
	 * @Title: EditPassageClassify  
	 * @Description: TODO      编辑岗位通道分类
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String EditPassageClassify(String name,String sort,String classify_id) {	
		return HttpRequest.put(add_passage_classify_url).query("access_token", token).data("{\r\n" + 
				"      \"enterprise_id\": \""+enterpriseId+"\",\r\n" + 
				"      \"name\": \""+name+"\",\r\n" + 
				"      \"sort\": \""+sort+"\",\r\n" + 
				"      \"id\": \""+classify_id+"\",\r\n" + 
				"      \"access_token\": \""+token+"\"\r\n" + 
				"}")
				.send().body();
	}
	
	
	/**   
	 * @Title: EditPassage  
	 * @Description: TODO      编辑岗位通道
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String EditPassage(String name,String passage_id,String classify_id) {	
		return HttpRequest.put(add_passage_url).query("access_token", token).data("{\r\n" + 
				"      \"app_id\": \"zlnt\",\r\n" + 
				"      \"enterprise_id\": \""+enterpriseId+"\",\r\n" + 
				"      \"name\": \""+name+"\",\r\n" + 
				"      \"sort\": 20,\r\n" + 
				"      \"id\": \""+passage_id+"\",\r\n" + 
				"      \"classify_id\": \""+classify_id+"\",\r\n" + 
				"      \"access_token\": \""+token+"\"\r\n" + 
				"}")
				.send().body();
	}
	
	
	
	/**   
	 * @Title: GetUserPost  
	 * @Description: TODO     查询用户所属岗位信息
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String GetUserPost(String user_name) {	
		return HttpRequest.get(get_userpost_url).query("access_token", token).query("user_name",user_name)
				.query("user_type","active").query("filter_auth","true")
				.send().body();
	}
	
	
	
	/**   
	 * @Title: PostMapping  
	 * @Description: TODO     给岗位通道添加岗位
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String PostMapping(String rank_id,String passage_id,String post_id,String post_name) {	
		return HttpRequest.post(post_mapping_url).query("access_token", token).data("{\r\n" + 
				"      \"app_id\": \"zlnt\",\r\n" + 
				"      \"enterprise_id\": \""+enterpriseId+"\",\r\n" + 
				"      \"rank_id\": \""+rank_id+"\",\r\n" + 
				"      \"passage_id\": \""+passage_id+"\",\r\n" + 
				"      \"post_id\": [\r\n" + 
				"            \""+post_id+"\"\r\n" + 
				"      ],\r\n" + 
				"      \"post_name\": [\r\n" + 
				"            \""+post_name+"\"\r\n" + 
				"      ],\r\n" + 
				"      \"access_token\": \""+token+"\"\r\n" + 
				"}")
				.send().body();
	}
	
	
	
	/**   
	 * @Title: PostRanks  
	 * @Description: TODO     查询职级id
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String PostRanks() {	
		return HttpRequest.get(post_ranks_url).query("access_token", token)
				.send().body();
	}
	
	
	/**   
	 * @Title: AddPostRanks  
	 * @Description: TODO     新增职级
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String AddPostRanks(String name,String sort) {	
		return HttpRequest.post(post_ranks_url).query("access_token", token).data("{\r\n" + 
				"      \"app_id\": \"zlnt\",\r\n" + 
				"      \"enterprise_id\": \""+enterpriseId+"\",\r\n" + 
				"      \"name\": \""+name+"\",\r\n" + 
				"      \"sort\": \""+sort+"\",\r\n" + 
				"      \"access_token\": \""+token+"\"\r\n" + 
				"}")
				.send().body();
	}
	
	
	/**   
	 * @Title: DeletePostRanks  
	 * @Description: TODO     删除职级
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String DeletePostRanks(String rank_id) {	
		return HttpRequest.delete(delete_post_rank_url(rank_id)).query("access_token", token).data("{\r\n" + 
				"      \"access_token\": \""+token+"\"\r\n" + 
				"}")
				.send().body();
	}
	
	
	/**   
	 * @Title: EditPostRanks  
	 * @Description: TODO     编辑职级
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String EditPostRanks(String name,String sort,String rank_id) {	
		return HttpRequest.get(post_ranks_url).query("access_token", token).data("{\r\n" + 
				"      \"app_id\": \"zlnt\",\r\n" + 
				"      \"enterprise_id\": \""+enterpriseId+"\",\r\n" + 
				"      \"name\": \""+name+"\",\r\n" + 
				"      \"sort\": \""+sort+"\",\r\n" + 
				"      \"id\": \""+rank_id+"\",\r\n" + 
				"      \"access_token\": \""+token+"\"\r\n" + 
				"}")
				.send().body();
	}
	
	
	
	/**   
	 * @Title: DeletePostMapping  
	 * @Description: TODO      删除岗位
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String DeletePostMapping(String delete_id) {	
		return HttpRequest.delete(delete_post_mapping_url(delete_id)).query("access_token", token)
				.send().body();
	}
	
	
	/**   
	 * @Title: DeletePostPassages  
	 * @Description: TODO      删除岗位通道
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String DeletePostPassages(String passage_id) {	
		return HttpRequest.delete(delete_post_passages_url(passage_id)).query("access_token", token)
				.send().body();
	}
	
	
	/**   
	 * @Title: QueryDeleteId  
	 * @Description: TODO      查询删除岗位时的id
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String QueryDeleteId(String passage_id) {	
		return HttpRequest.get(delete_post_passages_url(passage_id)).query("access_token", token)
				.send().body();
	}
	
	
	
	
	/**   
	 * @Title: QueryPassageClassify  
	 * @Description: TODO      查询岗位通道分类列表
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String QueryPassageClassify() {	
		return HttpRequest.get(query_passage_classify_url).query("access_token", token)
				.send().body();
	}
	
	
	/**   
	 * @Title: QueryPostPassages  
	 * @Description: TODO      查询岗位通道分类下的岗位通道
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String QueryPostPassages(String classify_id) {	
		return HttpRequest.get(query_post_passages_url(classify_id)).query("access_token", token)
				.send().body();
	}
	
	
	
	/**   
	 * @Title: PostQualificationsList  
	 * @Description: TODO      岗位任职资格查询
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String PostQualificationsList(String name,String status) {	
		return HttpRequest.post(post_qualifications_list_url).query("access_token", token).data("{\r\n" + 
				"      \"name\": \"\",\r\n" + 
				"      \"page_number\": 1,\r\n" + 
				"      \"page_size\": 20,\r\n" +
				"      \"name\": \""+name+"\",\r\n" +
				"      \"status\": \""+status+"\",\r\n" + 
				"      \"classify_id\": \"\",\r\n" + 
				"      \"sort\": \"desc\",\r\n" + 
				"      \"enterprise_id\": \""+enterpriseId+"\",\r\n" + 
				"      \"access_token\": \""+token+"\"\r\n" + 
				"}")
				.send().body();
	}
	
	
	
	/**   
	 * @Title: FindStructure  
	 * @Description: TODO      获取任职资格资格分类
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String FindStructure() {	
		return HttpRequest.post(find_structure_url).query("access_token", token).data("{\r\n" + 
				"      \"enterprise_id\": \""+enterpriseId+"\",\r\n" + 
				"      \"access_token\": \""+token+"\"\r\n" + 
				"}")
				.send().body();
	}
	
	
	
	/**   
	 * @Title: AbilityModelList  
	 * @Description: TODO      获取学习方案所有岗位组
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String AbilityModelList(String post_name,String class_status) {	
		return HttpRequest.post(ability_model_list_url).query("access_token", token).data("{\r\n" + 
				"      \"app_id\": \"zInt\",\r\n" + 
				"      \"post_name\": \""+post_name+"\",\r\n" + 
				"      \"class_status\": \""+class_status+"\",\r\n" + 
				"      \"post_group_id\": \"\",\r\n" + 
				"      \"page_number\": 1,\r\n" + 
				"      \"page_size\": 20,\r\n" + 
				"      \"enterprise_id\": \""+enterpriseId+"\",\r\n" + 
				"      \"access_token\": \""+token+"\"\r\n" + 
				"}")
				.send().body();
	}
	
	
	/**   
	 * @Title: AbilityModelDetail  
	 * @Description: TODO      配置方案详情
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String AbilityModelDetail(String post_id) {	
		return HttpRequest.get(ability_model_detail_url(post_id)).query("access_token", token)
				.send().body();
	}
	
	
	
	
	/**   
	 * @Title: AbilityModelBase  
	 * @Description: TODO      配置方案-岗位信息设置
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String AbilityModelBase(String postName,String postChannel,String superior_post,int function_id,String name,
			String post_ability_mode_id,String post_id) {	
		return HttpRequest.put(ability_model_base_url).query("access_token", token).data("{\r\n" + 
				"      \"postName\": \""+postName+"\",\r\n" + 
				"      \"postChannel\": \""+postChannel+"\",\r\n" + 
				"      \"positions\": \"P1\",\r\n" + 
				"      \"superior_post\": \""+superior_post+"\",\r\n" + 
				"      \"function_id\": \""+function_id+"\",\r\n" + 
				"      \"qualification_description\": \"qualification_description test\",\r\n" + 
				"      \"post_description\": \"post_description test\",\r\n" + 
				"      \"superior_post_name\": [\r\n" + 
				"            {\r\n" + 
				"                  \"name\": \""+name+"\",\r\n" + 
				"                  \"id\": \""+superior_post+"\"\r\n" + 
				"            }\r\n" + 
				"      ],\r\n" + 
				"      \"id\": \""+post_ability_mode_id+"\",\r\n" + 
				"      \"post_id\": \""+post_id+"\",\r\n" + 
				"      \"enterprise_id\": \""+enterpriseId+"\",\r\n" + 
				"      \"type\": \"put\",\r\n" + 
				"      \"access_token\": \""+token+"\"\r\n" + 
				"}")
				.send().body();
	}
	
	
	
	/**   
	 * @Title: Search  
	 * @Description: TODO      查询经验/教育列表
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String Search(String key) {	
		return HttpRequest.get(search_url).query("access_token", token).query("key",key)
				.send().body();
	}
	
	
	
	/**   
	 * @Title: QualificationsDetail  
	 * @Description: TODO      岗位任职资格详情
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String QualificationsDetail(String qualification_id) {	
		return HttpRequest.get(qualifications_detail_url(qualification_id)).query("access_token", token)
				.send().body();
	}
	
	
	/**   
	 * @Title: Comparison  
	 * @Description: TODO      配置方案-任职资格设置
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String Comparison(String post_name,String level,String classify_id,String education_id,String experience,String create_time,
			String emphasis_task_list_id,String emphasis_task_list_name,String qualification_id,Integer emphasis_task_list_sort,
			String emphasis_certificate_list_id,String emphasis_certificate_list_name,Integer emphasis_certificate_list_sort,
			String emphasis_certificate_list_type,String update_time,String ability_classify_list_id,String ability_classify_list_name,
			String ability_id1,String ability_name1,Integer ability_level1,String ability_source1,String ability_id2,String ability_name2,
			Integer ability_level2,String ability_source2,String ability_id3,String ability_name3,Integer ability_level3,
			String ability_source3,String ability_classify_list_id2,String ability_classify_list_name2,String manage_ability_id1,
			String manage_ability_name1,Integer manage_ability_level1,String manage_ability_source1,String manage_ability_id2,
			String manage_ability_name2,Integer manage_ability_level2,String manage_ability_source2,String manage_ability_id3,
			String manage_ability_name3,Integer manage_ability_level3,String manage_ability_source3,String manage_ability_id4,
			String manage_ability_name4,Integer manage_ability_level4,String manage_ability_source4,String post_id,Integer status) {	
		return HttpRequest.put(comparison_url).query("access_token", token).data("{\r\n" + 
				"      \"name\": \""+post_name+"\",\r\n" + 
				"      \"level\": \""+level+"\",\r\n" + 
				"      \"classify_id\": \""+classify_id+"\",\r\n" + 
				"      \"educational_background\": \""+education_id+"\",\r\n" + 
				"      \"experience\": \""+experience+"\",\r\n" + 
				"      \"post_emphasis_task\": [\r\n" + 
				"            {\r\n" + 
				"                  \"app_id\": \"zlnt\",\r\n" + 
				"                  \"create_time\": \""+create_time+"\",\r\n" + 
				"                  \"description\": \"description  test\",\r\n" + 
				"                  \"enterprise_id\": \""+enterpriseId+"\",\r\n" + 
				"                  \"id\": \""+emphasis_task_list_id+"\",\r\n" + 
				"                  \"name\": \""+emphasis_task_list_name+"\",\r\n" + 
				"                  \"qualification_id\": \""+qualification_id+"\",\r\n" + 
				"                  \"sort\": \""+emphasis_task_list_sort+"\",\r\n" + 
				"                  \"type\": \""+emphasis_certificate_list_type+"\",\r\n" + 
				"                  \"update_time\": \""+update_time+"\"\r\n" + 
				"            }\r\n" + 
				"      ],\r\n" + 
				"      \"post_emphasis_certificate\": [\r\n" + 
				"            {\r\n" + 
				"                  \"app_id\": \"zlnt\",\r\n" + 
				"                  \"create_time\": \""+create_time+"\",\r\n" + 
				"                  \"description\": \"description test\",\r\n" + 
				"                  \"enterprise_id\": \""+enterpriseId+"\",\r\n" + 
				"                  \"id\": \""+emphasis_certificate_list_id+"\",\r\n" + 
				"                  \"name\": \""+emphasis_certificate_list_name+"\",\r\n" + 
				"                  \"qualification_id\": \""+qualification_id+"\",\r\n" + 
				"                  \"sort\": "+emphasis_certificate_list_sort+",\r\n" + 
				"                  \"type\": \""+emphasis_certificate_list_type+"\",\r\n" + 
				"                  \"update_time\": \""+update_time+"\"\r\n" + 
				"            }\r\n" + 
				"      ],\r\n" + 
				"      \"post_qualifications_ability_classify\": [\r\n" + 
				"            {\r\n" + 
				"                  \"app_id\": \"zlnt\",\r\n" + 
				"                  \"create_time\": \""+create_time+"\",\r\n" + 
				"                  \"enterprise_id\": \""+enterpriseId+"\",\r\n" + 
				"                  \"id\": \""+ability_classify_list_id+"\",\r\n" + 
				"                  \"name\": \""+ability_classify_list_name+"\",\r\n" + 
				"                  \"post_qualifications_ability\": [\r\n" + 
				"                        {\r\n" + 
				"                              \"ability_id\": \""+ability_id1+"\",\r\n" + 
				"                              \"ability_name\": \""+ability_name1+"\",\r\n" + 
				"                              \"ability_level\": \""+ability_level1+"\",\r\n" + 
				"                              \"behavior_desc\": \"\",\r\n" + 
				"                              \"source\": \""+ability_source1+"\"\r\n" + 
				"                        },\r\n" + 
				"                        {\r\n" + 
				"                              \"ability_id\": \""+ability_id2+"\",\r\n" + 
				"                              \"ability_name\": \""+ability_name2+"\",\r\n" + 
				"                              \"ability_level\": \""+ability_level2+"\",\r\n" + 
				"                              \"behavior_desc\": \"\",\r\n" + 
				"                              \"source\": \""+ability_source2+"\"\r\n" + 
				"                        },\r\n" + 
				"                        {\r\n" + 
				"                              \"ability_id\": \""+ability_id3+"\",\r\n" + 
				"                              \"ability_name\": \""+ability_name3+"\",\r\n" + 
				"                              \"ability_level\": \""+ability_level3+"\",\r\n" + 
				"                              \"behavior_desc\": \"\",\r\n" + 
				"                              \"source\": \""+ability_source3+"\"\r\n" + 
				"                        }\r\n" + 
				"                  ],\r\n" + 
				"                  \"qualification_id\": \""+qualification_id+"\",\r\n" + 
				"                  \"sort\": 0,\r\n" + 
				"                  \"update_time\": \""+update_time+"\"\r\n" + 
				"            },\r\n" + 
				"            {\r\n" + 
				"                  \"app_id\": \"zlnt\",\r\n" + 
				"                  \"create_time\":  \""+create_time+"\",\r\n" + 
				"                  \"enterprise_id\": \""+enterpriseId+"\",\r\n" + 
				"                  \"id\": \""+ability_classify_list_id2+"\",\r\n" + 
				"                  \"name\": \""+ability_classify_list_name2+"\",\r\n" + 
				"                  \"post_qualifications_ability\": [\r\n" + 
				"                        {\r\n" + 
				"                              \"ability_id\": \""+manage_ability_id1+"\",\r\n" + 
				"                              \"ability_name\": \""+manage_ability_name1+"\",\r\n" + 
				"                              \"ability_level\": \""+manage_ability_level1+"\",\r\n" + 
				"                              \"behavior_desc\": \"\",\r\n" + 
				"                              \"source\": \""+manage_ability_source1+"\"\r\n" + 
				"                        },\r\n" + 
				"                        {\r\n" + 
				"                              \"ability_id\": \""+manage_ability_id2+"\",\r\n" + 
				"                              \"ability_name\": \""+manage_ability_name2+"\",\r\n" + 
				"                              \"ability_level\": \""+manage_ability_level2+"\",\r\n" + 
				"                              \"behavior_desc\": \"\",\r\n" + 
				"                              \"source\": \""+manage_ability_source2+"\"\r\n" + 
				"                        },\r\n" + 
				"                        {\r\n" + 
				"                              \"ability_id\": \""+manage_ability_id3+"\",\r\n" + 
				"                              \"ability_name\": \""+manage_ability_name3+"\",\r\n" + 
				"                              \"ability_level\": \""+manage_ability_level3+"\",\r\n" + 
				"                              \"behavior_desc\": \"\",\r\n" + 
				"                              \"source\": \""+manage_ability_source3+"\"\r\n" + 
				"                        },\r\n" + 
				"                        {\r\n" + 
				"                              \"ability_id\": \""+manage_ability_id4+"\",\r\n" + 
				"                              \"ability_name\": \""+manage_ability_name4+"\",\r\n" + 
				"                              \"ability_level\": \""+manage_ability_level4+"\",\r\n" + 
				"                              \"behavior_desc\": \"\",\r\n" + 
				"                              \"source\": \""+manage_ability_source4+"\"\r\n" + 
				"                        }\r\n" + 
				"                  ],\r\n" + 
				"                  \"qualification_id\": \""+qualification_id+"\",\r\n" + 
				"                  \"sort\": 1,\r\n" + 
				"                  \"update_time\": \""+update_time+"\"\r\n" + 
				"            }\r\n" + 
				"      ],\r\n" + 
				"      \"post_name_list\": [\r\n" + 
				"            {\r\n" + 
				"                  \"post_id\": \""+post_id+"\",\r\n" + 
				"                  \"post_name\": \""+post_name+"\",\r\n" + 
				"                  \"status\": \""+status+"\",\r\n" + 
				"                  \"update_time\": \""+update_time+"\"\r\n" + 
				"            }\r\n" + 
				"      ],\r\n" + 
				"      \"enterprise_id\": \""+enterpriseId+"\",\r\n" + 
				"      \"id\": \""+qualification_id+"\",\r\n" + 
				"      \"type\": \"put\",\r\n" + 
				"      \"access_token\": \""+token+"\"\r\n" + 
				"}")
				.send().body();
	}
	
	
	
	/**   
	 * @Title: ProjectList  
	 * @Description: TODO      学习项目列表
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String ProjectList() {	
		return HttpRequest.get(project_list_url).query("access_token", token).query("only_see_me","false")
				.send().body();
	}
	
	
	
	/**   
	 * @Title: PostProject  
	 * @Description: TODO      配置方案-学习方案
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String PostProject(String post_ability_mode_id,String project_id) {	
		return HttpRequest.put(post_project_url).query("access_token", token).data("{\r\n" + 
				"      \"id\": \""+post_ability_mode_id+"\",\r\n" + 
				"      \"project_list\": [\r\n" + 
				"            {\r\n" + 
				"                  \"project_id\": \""+project_id+"\",\r\n" + 
				"                  \"sort\": 0\r\n" + 
				"            }\r\n" + 
				"      ],\r\n" + 
				"      \"enterprise_id\": \""+enterpriseId+"\",\r\n" + 
				"      \"access_token\": \""+token+"\"\r\n" + 
				"}")
				.send().body();
	}
	
	
	/**   
	 * @Title: SubmitAbilityModel  
	 * @Description: TODO      
	 * @param: @return      配置方案-确认信息提交
	 * @return: String      
	 * @throws   
	 */ 
	public static String SubmitAbilityModel(String post_ability_mode_id) {	
		return HttpRequest.put(submit_ability_model_url(post_ability_mode_id)).query("access_token", token).query("enterprise_id",enterpriseId)
				.send().body();
	}
	
	
	
	/**   
	 * @Title: AbilityModelExport  
	 * @Description: TODO      
	 * @param: @return      岗位学习方案列表-导出
	 * @return: String      
	 * @throws   
	 */ 
	public static String AbilityModelExport() {	
		return HttpRequest.get(ability_model_export_url).query("access_token", token).query("class_status","2")
				.query("sort","desc")
				.send().body();
	}
	
	
	
	/**   
	 * @Title: ScanAbilityModel  
	 * @Description: TODO      
	 * @param: @return      岗位学习方案-预览
	 * @return: String      
	 * @throws   
	 */ 
	public static String ScanAbilityModel(String post_id) {	
		return HttpRequest.get(scan_ability_model_url(post_id)).query("access_token", token)
				.send().body();
	}
	
	
	/**   
	 * @Title: DisableQualifications  
	 * @Description: TODO      
	 * @param: @return      岗位任职资格列表-关闭/开启
	 * @return: String      
	 * @throws   
	 */ 
	public static String DisableQualifications(String qualification_id) {	
		return HttpRequest.get(disable_qualifications_url(qualification_id)).query("access_token", token).query("id",qualification_id)
				.send().body();
	}
	
	
	
	/**   
	 * @Title: QualificationPost  
	 * @Description: TODO      
	 * @param: @return      岗位认证资格列表-关联岗位查询
	 * @return: String      
	 * @throws   
	 */ 
	public static String QualificationPost(String qualification_id) {	
		return HttpRequest.get(qualification_post_url).query("access_token", token).query("qualification_id",qualification_id)
				.query("page_size","5").query("page_number","1")
				.send().body();
	}
	
	
	
	/**   
	 * @Title: EvaluationModels  
	 * @Description: TODO      
	 * @param: @return      评估结果导入-岗位能力模型左侧列表
	 * @return: String      
	 * @throws   
	 */ 
	public static String EvaluationModels() {	
		return HttpRequest.get(evaluation_models_url).query("access_token", token).query("model_type","post")
				.query("page_size","20").query("page_number","1").query("key_word","")
				.send().body();
	}

	
	/**   
	 * @Title: EvaluationResult  
	 * @Description: TODO      
	 * @param: @return      评估结果导入-能力模型右侧大列表
	 * @return: String      
	 * @throws   
	 */ 
	public static String EvaluationResult(String evaluation_models_id) {	
		return HttpRequest.get(evaluation_result_url(evaluation_models_id)).query("access_token", token)
				.query("page_size","20").query("page_number","1")
				.send().body();
	}
	
	
	/**   
	 * @Title: EvaluationResultDetail  
	 * @Description: TODO      
	 * @param: @return      评估结果导入-能力模型右侧大列表-详情
	 * @return: String      
	 * @throws   
	 */ 
	public static String EvaluationResultDetail(String eva_result_id) {	
		return HttpRequest.get(evaluation_result_detail_url(eva_result_id)).query("access_token", token)
				.query("page_size","20").query("page_number","1").query("name","")
				.send().body();
	}
	
	
	
	/**   
	 * @Title: ConfigSave  
	 * @Description: TODO      
	 * @param: @return      高级设置-参数设置
	 * @return: String      
	 * @throws   
	 */ 
	public static String ConfigSave(String pc_mode) {	
		return HttpRequest.post(config_save_url).query("access_token", token).data("{\r\n" + 
				"      \"qualification\": \"1\",\r\n" + 
				"      \"visible_type\": \"0\",\r\n" + 
				"      \"post_num\": \"1\",\r\n" + 
				"      \"app_mode\": \"1\",\r\n" + 
				"      \"pc_mode\": \""+pc_mode+"\",\r\n" + 
				"      \"enterprise_id\": \""+enterpriseId+"\",\r\n" + 
				"      \"access_token\": \""+token+"\"\r\n" + 
				"}")
				.send().body();
	}
	
	
	
	/**   
	 * @Title: UserPost  
	 * @Description: TODO      
	 * @param: @return      学习平台-用户所属岗位
	 * @return: String      
	 * @throws   
	 */ 
	public static String UserPost() {	
		return HttpRequest.get(user_post).query("access_token", token)
				.send().body();
	}
	
	
	/**   
	 * @Title: PostPassages  
	 * @Description: TODO      
	 * @param: @return      学习平台-岗位所属的岗位通道
	 * @return: String      
	 * @throws   
	 */ 
	public static String PostPassages(String post_id,String post_name) {	
		return HttpRequest.get(post_passages_url(post_id)).query("access_token", token).query("post_name",post_name)
				.send().body();
	}
	
	
	/**   
	 * @Title: ModelDetail  
	 * @Description: TODO      
	 * @param: @return      学习平台-岗位详情
	 * @return: String      
	 * @throws   
	 */ 
	public static String ModelDetail(String post_id,String rank_id) {	
		return HttpRequest.get(model_detail_url(post_id)).query("access_token", token).query("rank_id",rank_id)
				.send().body();
	}
	
	
	/**   
	 * @Title: LinkInfo  
	 * @Description: TODO      
	 * @param: @return      学习平台-岗位详情-查看全部证书/任务
	 * @return: String      
	 * @throws   
	 */ 
	public static String LinkInfo(String qualification_id,String type) {	
		return HttpRequest.get(linkInfo_url(qualification_id)).query("access_token", token).query("type",type)
				.send().body();
	}
	
	
	/**   
	 * @Title: AddQualification  
	 * @Description: TODO      
	 * @param: @return      新增岗位任职资格
	 * @return: String      
	 * @throws   
	 */ 
	public static String AddQualification(String name,String classify_id,String educational_background,String experience_id,
			String ability_id,String ability_name,String ability_level) {	
		return HttpRequest.post(add_qualification_url).query("access_token", token).data("{\r\n" + 
				"      \"name\": \""+name+"\",\r\n" + 
				"      \"level\": \"level test\",\r\n" + 
				"      \"classify_id\": \""+classify_id+"\",\r\n" + 
				"      \"educational_background\": \""+educational_background+"\",\r\n" + 
				"      \"experience\": \""+experience_id+"\",\r\n" + 
				"      \"post_emphasis_task\": [\r\n" + 
				"            {\r\n" + 
				"                  \"name\": \"task test\",\r\n" + 
				"                  \"description\": \"description test\",\r\n" + 
				"                  \"sort\": 0\r\n" + 
				"            }\r\n" + 
				"      ],\r\n" + 
				"      \"post_qualifications_ability_classify\": [\r\n" + 
				"            {\r\n" + 
				"                  \"name\": \"ability test\",\r\n" + 
				"                  \"post_qualifications_ability\": [\r\n" + 
				"                        {\r\n" + 
				"                              \"ability_id\": \""+ability_id+"\",\r\n" + 
				"                              \"ability_name\": \""+ability_name+"\",\r\n" + 
				"                              \"ability_level\": \""+ability_level+"\",\r\n" + 
				"                              \"behavior_desc\": \"\",\r\n" + 
				"                              \"source\": \"tool\"\r\n" + 
				"                        }\r\n" + 
				"                  ],\r\n" + 
				"                  \"sort\": 0\r\n" + 
				"            }\r\n" + 
				"      ],\r\n" + 
				"      \"enterprise_id\": \""+enterpriseId+"\",\r\n" + 
				"      \"access_token\": \""+token+"\"\r\n" + 
				"}")
				.send().body();
	}
	
	
	
	/**   
	 * @Title: AbilityList  
	 * @Description: TODO      
	 * @param: @return      能力要求列表
	 * @return: String      
	 * @throws   
	 */ 
	public static String AbilityList(String need_levels,String name,String ability_type) {	
		return HttpRequest.get(ability_list_url).query("access_token", token).query("page_number","1").query("page_size","10")
				.query("need_levels",need_levels).query("name",name).query("ability_type",ability_type)
				.send().body();
	}
	
	/**   
	 * @Title: DeleteQualification  
	 * @Description: TODO      
	 * @param: @return      删除任职资格
	 * @return: String      
	 * @throws   
	 */ 
	public static String DeleteQualification(String qualification_id) {	
		return HttpRequest.delete(delete_qualification_url(qualification_id)).query("access_token", token).data("{\r\n" + 
				"      \"access_token\": \""+token+"\"\r\n" + 
				"}")
				.send().body();
	}
}
