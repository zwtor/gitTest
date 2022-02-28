package cn.kxy.group.a.business;

import cn.kxy.base.business.EnterpriseData;
import cn.kxy.base.business.EnterpriseDataUrl;
import cn.kxy.base.business.TokenData;
import cn.kxy.setting.bussiness.UserBusiness;
import com.alibaba.fastjson.JSONObject;
import com.lazy.httpclient.utils.HttpRequest;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class ImageTextBusiness {

	public static String enterprise_url = EnterpriseDataUrl.getEnterpriseUrl();
	public static String share_url = EnterpriseDataUrl.getShareUrl();
	public static String apigateway_url = EnterpriseDataUrl.getApigateway();
	public static String token = TokenData.getMangerToken();
	public static String enterpriseId = EnterpriseData.getEnterpriseId();
	public static String user_id = UserBusiness.getUserId();
	
	public static String imagetext_add_url = enterprise_url + "course/imagetext/add/";
	public static String imagetext_edit_url = enterprise_url + "course/imagetext/edit/";
	public static String released_list_url = enterprise_url + "v2/"+enterpriseId+"/imagetext/courses/manage/";
    public static String add_concatperson_url = share_url + "v1/"+enterpriseId + "/market/concat/person/concatPerson/addConcatPersonInfo/";
	public static String groupUser_url = share_url + "v1/"+enterpriseId + "/market/concat/group/concatPersonGroup/groupUser/root/";	
	public static String recommendSet_url = enterprise_url + "course/recommendSet/";
	public static String recommendRemove_url = enterprise_url + "course/recommendRemove/";
	public static String selectImageTextRecord_url = enterprise_url + "course/selectImageTextRecord/";
	public static String study_info_url(String imagetext_id) {
		return apigateway_url + "v1/"+enterpriseId+"/user/"+user_id+"/courses/"+imagetext_id+"/study_info/";
	}
	public static String listApply_url = apigateway_url + "v1/"+enterpriseId+"/market/apply/apply/listApply/";
	public static String getClueList_url = enterprise_url + "v1/clue/getClueList/";
	
	public static String add_share_url = share_url + "/v1/knowledge/share/addShareConfig/";
	
			
	public static String course_cancel_url(String imagetext_id) {
		return enterprise_url + "v2/"+enterpriseId+"/courses/"+imagetext_id+"/release/";
	}
	public static String course_delete_url = enterprise_url + "course/delete/";
	public static String delete_ConcatPersonInfo_url = apigateway_url + "v1/"+enterpriseId+"/market/concat/person/concatPerson/deleteConcatPersonInfo/";
	
	
	/**
	 * @throws UnsupportedEncodingException    
	 * @Title: ImageTextAdd  
	 * @Description: TODO  创建图文课
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String ImageTextAdd(String baseCover,String contentJson,String init_type,String teacherId, String title) throws UnsupportedEncodingException {	
		return HttpRequest.post(imagetext_add_url).query("access_token", token).query("baseCover",baseCover).query("biz_type","6").query("contentJson",URLEncoder.encode(contentJson, "utf-8"))
				.query("cover","").query("coverType","1").query("credit","0").query("departmentIds","").query("groupIds","").query("init_type",init_type)
				.query("isAll","1").query("original","1").query("studyTimeLimit","").query("teacherId",teacherId).query("title",title).
				send().body();
	}
	
	
	
	/**
	 * @throws UnsupportedEncodingException    
	 * @Title: ImageTextEdit  
	 * @Description: TODO  编辑图文课
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String ImageTextEdit(String baseCover,String contentJson,String teacherId, String title,String imagetext_id) throws UnsupportedEncodingException {	
		return HttpRequest.post(imagetext_edit_url).query("access_token", token).query("baseCover",baseCover).query("biz_type","6").query("contentJson",URLEncoder.encode(contentJson, "utf-8"))
				.query("cover","").query("coverType","1").query("credit","0").query("departmentIds","").query("groupIds","").query("init_type","release")
				.query("isAll","1").query("original","1").query("studyTimeLimit","").query("teacherId",teacherId).query("title",title).query("id",imagetext_id).
				send().body();
	}
	
	/**   
	 * @Title: ImageTextReleasedList  
	 * @Description: TODO  已发布/未发布的图文课列表
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String ImageTextReleasedList(String title,String view_sharing,String course_status) {		
		return HttpRequest.get(released_list_url).query("access_token", token).query("title",title).query("page_number","1").
				query("page_size","20").query("view_sharing",view_sharing).query("course_status",course_status).query("course_type","all")
				.send().body();
	}
	
	/**   
	 * @Title: AddConcatperson  
	 * @Description: TODO  添加联系人
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String AddConcatperson(String name,String phone_num) {		
		return HttpRequest.post(add_concatperson_url).query("access_token", token).data("{\r\n" + 
				"    \"name\": \""+name+"\",\r\n" + 
				"    \"phone_num\": \""+phone_num+"\",\r\n" + 
				"    \"gender\": \"\",\r\n" + 
				"    \"position\": \"\",\r\n" + 
				"    \"email\": \"\",\r\n" + 
				"    \"wechat_id\": \"\",\r\n" + 
				"    \"source\": \"normal\",\r\n" + 
				"    \"group_ids\": [\r\n" + 
				"        \"default\"\r\n" + 
				"    ],\r\n" + 
				"    \"access_token\":\""+token+"\"\r\n" + 
				"}\r\n" + 
				"")
				.send().body();

	}
	
	/**   
	 * @Title: GroupUserInfo  
	 * @Description: TODO  查询联系人
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String GroupUserInfo() {		
		return HttpRequest.get(groupUser_url).query("access_token", token).query("page_number","1").query("page_size","20")
				.send().body();
	}
	
	
	/**   
	 * @Title: RecommendSet  
	 * @Description: TODO  置顶图文课
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String RecommendSet(String imagetext_id) {		
		return HttpRequest.post(recommendSet_url).query("access_token", token).query("id",imagetext_id)
				.send().body();
	}
	
	
	/**   
	 * @Title: RecommendRemove  
	 * @Description: TODO  取消置顶图文课
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String RecommendRemove(String imagetext_id) {		
		return HttpRequest.post(recommendRemove_url).query("access_token", token).query("id",imagetext_id)
				.send().body();
	}
	
	
	/**
	 * @throws UnsupportedEncodingException    
	 * @Title: AddShare   
	 * @Description: TODO  分享
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 

	public static String AddShare(String imagetext_id,String auth_wechat,String open_share,String share_type,
			String official_account_app_id,String share_image_url,String concat_person_ids,String register_status,
			String enterpriseId) throws UnsupportedEncodingException {
		String str = "{\r\n" + 
				"    \"source_knowledge_id\": \""+imagetext_id+"\",\r\n" + 
				"    \"knowledge_type\": \"course\",\r\n" + 
				"    \"auth_wechat\": \""+auth_wechat+"\",\r\n" + 
				"    \"open_share\": \""+open_share+"\",\r\n" + 
				"    \"share_type\": \""+share_type+"\",\r\n" + 
				"    \"official_account_app_id\": \""+official_account_app_id+"\",\r\n" + 
				"    \"official_account\": \"吉麟服务号test1\",\r\n" + 
				"    \"share_title\": \"图文课编辑1I33C\",\r\n" + 
				"    \"share_desc\": \"\",\r\n" + 
				"    \"share_image_url\": \""+share_image_url+"\",\r\n" + 
				"    \"concat_person_group_ids\": \"\",\r\n" + 
				"    \"concat_person_ids\": \""+concat_person_ids+"\",\r\n" + 
				"    \"register_status\": \""+register_status+"\",\r\n" + 
				"    \"enterprise_id\": \""+enterpriseId+"\",\r\n" + 
				"    \"app_id\": \"cool\",\r\n" + 
				"    \"user_id\": \""+user_id+"\",\r\n" + 
				"    \"access_token\":\""+token+"\"\r\n" + 
				"}\r\n";
		Object strTemp = JSONObject.parse(str);		
		return HttpRequest.post(add_share_url).query("access_token", token).data(JSONObject.toJSONString(strTemp))
				.send().body();
	}
	
	
	/**   
	 * @Title: SelectImageTextRecord  
	 * @Description: TODO  图文课数据监控-学员学习数据
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String SelectImageTextRecord(String imagetext_id) {		
		return HttpRequest.get(selectImageTextRecord_url).query("access_token", token).query("pageNumber","1")
				.query("pageSize","20").query("courseId",imagetext_id)
				.send().body();
	}
	
	/**   
	 * @Title: StudyInfo  
	 * @Description: TODO  图文课数据监控-客户学习数据
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String StudyInfo(String imagetext_id) {		
		return HttpRequest.get(study_info_url(imagetext_id)).query("access_token", token).query("page_number","1")
				.query("page_size","20").query("taskStatus","0").query("linkman_group","")
				.send().body();
	}
	
	
	/**   
	 * @Title: ListApply  
	 * @Description: TODO  图文课数据监控-报名管理
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String ListApply(String imagetext_id) {		
		return HttpRequest.get(listApply_url).query("access_token", token).query("page_num","1")
				.query("page_size","20").query("course_id",imagetext_id)
				.send().body();
	}
	
	
	/**   
	 * @Title: GetClueList   
	 * @Description: TODO  图文课数据监控-推广数据
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String GetClueList(String imagetext_id,String auth_wechat,String open_share,String share_type,
			String official_account_app_id,String share_image_url,String concat_person_ids,String register_status,
			String enterpriseId) {		
		return HttpRequest.post(getClueList_url).query("access_token", token).data("{\r\n" + 
				"  \"access_token\":\""+token+"\"\r\n" + 
				"    \"source_knowledge_id\": \""+imagetext_id+"\",\r\n" + 
				"    \"knowledge_type\": \"course\",\r\n" + 
				"    \"auth_wechat\": \""+auth_wechat+"\",\r\n" + 
				"    \"open_share\": \""+open_share+"\",\r\n" + 
				"    \"share_type\": \""+share_type+"\",\r\n" + 
				"    \"official_account_app_id\": \""+official_account_app_id+"\",\r\n" + 
				"    \"official_account\": \"吉麟服务号test1\",\r\n" + 
				"    \"share_title\": \"图文课编辑1I33C\",\r\n" + 
				"    \"share_desc\": \"\",\r\n" + 
				"    \"share_image_url\": \""+share_image_url+"\",\r\n" + 
				"    \"concat_person_group_ids\": \"\",\r\n" + 
				"    \"concat_person_ids\": \""+concat_person_ids+"\",\r\n" + 
				"    \"register_status\": \""+register_status+"\",\r\n" + 
				"    \"enterprise_id\": \""+enterpriseId+"\",\r\n" + 
				"    \"app_id\": \"cool\",\r\n" + 
				"    \"user_id\": \""+user_id+"\",\r\n" + 
				"    \"access_token\":\""+token+"\"\r\n" + 
				"}\r\n" + 
				"")
				.send().body();
	}

	
	
	/**   
	 * @Title: CourseCancel  
	 * @Description: TODO  图文课取消发布
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String CourseCancel(String imagetext_id,String action) {		
		return HttpRequest.post(course_cancel_url(imagetext_id)).query("access_token", token).query("action",action)
				.send().body();
	}
	
	
	/**   
	 * @Title: CourseDelete  
	 * @Description: TODO  图文课删除
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String CourseDelete(String imagetext_id) {		
		return HttpRequest.post(course_delete_url).query("access_token", token).query("ids",imagetext_id)
				.send().body();
	}
	
	
	/**   
	 * @Title: DeleteConcatPersonInfo  
	 * @Description: TODO  删除联系人
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String DeleteConcatPersonInfo(String concat_person_ids) {		
		return HttpRequest.post(delete_ConcatPersonInfo_url).query("access_token", token).data("{\r\n" + 
				"    \"id_list\": [\r\n" + 
				"        \""+concat_person_ids+"\"\r\n" + 
				"    ],\r\n" + 
				"    \"access_token\":\""+token+"\"\r\n" + 
				"}\r\n" + 
				"")
				.send().body();
	}
	
}
