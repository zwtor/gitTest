package cn.kxy.group.a.business;

import cn.kxy.base.business.EnterpriseData;
import cn.kxy.base.business.EnterpriseDataUrl;
import cn.kxy.base.business.TokenData;
import cn.kxy.setting.bussiness.UserBusiness;
import com.lazy.common.utils.DateUtil;
import com.lazy.httpclient.utils.HttpRequest;

import java.io.UnsupportedEncodingException;

public class CertificateBusiness {
	public static String enterprise_url = EnterpriseDataUrl.getEnterpriseUrl();
	public static String token = TokenData.getMangerToken();
	public static String enterpriseId = EnterpriseData.getEnterpriseId();
	public static String user_id = UserBusiness.getUserId();
	public static String add_certificate_url = enterprise_url + "v2/"+enterpriseId+"/certificate/add/";
	public static String certificate_templates_url = enterprise_url + "v2/"+enterpriseId+"/certificate_templates/query/";
	public static String certificate_list_url = enterprise_url + "certificate/getList/";
	public static String certificate_detail_url (String certificate_id) {
		return enterprise_url + "v2/"+enterpriseId+"/certificate/"+certificate_id+"/detail/";
	}

	public static String query_relation_url (String certificate_id) {
		return enterprise_url + "v2/"+enterpriseId+"/certificate/"+certificate_id+"/query_relation/";
	}
	public static String certificates_export_url = enterprise_url + "v2/"+enterpriseId+"/certificates_export/";
	public static String edit_certificate_url = enterprise_url + "v2/"+enterpriseId+"/certificate/edit/";
	public static String save_certificate_url = enterprise_url + "v2/"+enterpriseId+"/certificates/save/";
	public static String status_certificate_url = enterprise_url + "/certificate/modifyStatus/";
	public static String delete_certificate_url (String certificate_id) {
		return enterprise_url + "v2/"+enterpriseId+"/certificate/"+certificate_id+"/delete/";
	}
	public static String remind_url = enterprise_url + "v2/"+enterpriseId+"/certificate/users/remind/";
	public static String user_export_url (String certificate_id) {
		return enterprise_url + "v2/"+enterpriseId+"/certificate/"+certificate_id+"/export/";
	}
	public static String delete_user_url (String certificate_id) {
		return enterprise_url + "v2/"+enterpriseId+"/certificates/"+certificate_id+"/users/delete/";
	}
	public static String resource_getList_url = enterprise_url + "course/resource/getList/";
	public static String studyplan_add_url = enterprise_url + "plan/study/add/";
	public static String getOne_url = enterprise_url + "plan/myTask/getOne/";
	public static String save_progress_url(String studyPlan_id,String course_id) {
		return enterprise_url + "v2/"+enterpriseId+"/users/"+user_id+"/studies/"+studyPlan_id+"/courses/"+course_id+"/resources/"+course_id+"/save_progress/";
	}
	public static String certificates_user_url = enterprise_url + "v2/"+enterpriseId+"/certificates_user/";
	public static String certificate_user_url (String certificate_id) {
		return enterprise_url + "v2/"+enterpriseId+"/certificate/"+certificate_id+"/user/";
	}
	public static String delete_study_url = enterprise_url + "plan/study/delete/";

	
	/**   
	 * @Title: CertificateTemplates  
	 * @Description: TODO     查询证书样式
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String CertificateTemplates() {	
		return HttpRequest.get(certificate_templates_url).query("access_token", token).query("style","vertical").query("page_number","1")
				.query("page_size","10").query("template_type","")
				.send().body();
	}
	
	
	/**   
	 * @Title: AddCertificate  
	 * @Description: TODO     新增证书
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String AddCertificate(String name,String language_type,String image_url,String template_id,String logo_url,
			String common_seal_url) {	
		return HttpRequest.post(add_certificate_url).query("access_token", token).data("{\r\n" + 
				"      \"name\": \""+name+"\",\r\n" + 
				"      \"language_type\": \""+language_type+"\",\r\n" + 
				"      \"layout_id\": \"verticalLeft\",\r\n" + 
				"      \"template_id\": \""+template_id+"\",\r\n" + 
				"      \"image_url\": \""+image_url+"\",\r\n" + 
				"      \"title\": \""+name+"\",\r\n" + 
				"      \"certificate_code\": \"11112222\",\r\n" + 
				"      \"summary\": \"兹证明通过认证考核，特颁发此证\",\r\n" + 
				"      \"expire_date\": 0,\r\n" + 
				"      \"permanently_valid\": \"permanent\",\r\n" + 
				"      \"message\": \"send_message\",\r\n" + 
				"      \"message_properties\": \"{\\\"countdown_date\\\":1,\\\"created\\\":true,\\\"user\\\":true,\\\"nominee\\\":{\\\"checked\\\":false,\\\"users\\\":[]}}\",\r\n" + 
				"      \"logo_url\": \""+logo_url+"\",\r\n" + 
				"      \"common_seal\": {\r\n" + 
				"            \"type\": \"name\",\r\n" + 
				"            \"company_name\": \"company_name test\",\r\n" + 
				"            \"common_seal_url\": \""+common_seal_url+"\"\r\n" + 
				"      },\r\n" + 
				"      \"isOperating\": false,\r\n" + 
				"      \"display\": {\r\n" + 
				"            \"title\": {\r\n" + 
				"                  \"checked\": true,\r\n" + 
				"                  \"settings\": \"{\\\"font_family\\\":\\\"Pl-Song\\\",\\\"font_size\\\":\\\"96\\\",\\\"color\\\":\\\"#1F2B2B\\\",\\\"line_height\\\":\\\"1.4\\\"}\"\r\n" + 
				"            },\r\n" + 
				"            \"summary\": {\r\n" + 
				"                  \"checked\": true,\r\n" + 
				"                  \"settings\": \"{\\\"font_family\\\":\\\"Pl-Song\\\",\\\"font_size\\\":\\\"42\\\",\\\"color\\\":\\\"#27282D\\\",\\\"line_height\\\":\\\"1.5\\\"}\"\r\n" + 
				"            },\r\n" + 
				"            \"certificate_code\": {\r\n" + 
				"                  \"checked\": true\r\n" + 
				"            },\r\n" + 
				"            \"department\": {\r\n" + 
				"                  \"checked\": true\r\n" + 
				"            },\r\n" + 
				"            \"picture\": {\r\n" + 
				"                  \"checked\": true\r\n" + 
				"            },\r\n" + 
				"            \"logo_url\": {\r\n" + 
				"                  \"checked\": true\r\n" + 
				"            },\r\n" + 
				"            \"common_seal\": {\r\n" + 
				"                  \"checked\": true,\r\n" + 
				"                  \"settings\": \"{\\\"font_family\\\":\\\"Pl-Song\\\",\\\"font_size\\\":\\\"52\\\",\\\"color\\\":\\\"#d64f4f\\\",\\\"line_height\\\":\\\"1\\\"}\"\r\n" + 
				"            },\r\n" + 
				"            \"custom_field\": {\r\n" + 
				"                  \"field\": [\r\n" + 
				"                        \"issue_date\",\r\n" + 
				"                        \"effect_date\",\r\n" + 
				"                        \"overdue_date\",\r\n" + 
				"                  ],\r\n" + 
				"                  \"settings\": \"{\\\"font_family\\\":\\\"Pl-Song\\\",\\\"font_size\\\":\\\"36\\\",\\\"color\\\":\\\"#2F3033\\\",\\\"line_height\\\":\\\"1.4\\\"}\"\r\n" + 
				"            }\r\n" + 
				"      },\r\n" + 
				"      \"style\": \"vertical\",\r\n" + 
				"      \"company_name\": \"company_name test\",\r\n" + 
				"      \"common_seal_url\": \"\",\r\n" + 
				"      \"access_token\": \""+token+"\"\r\n" + 
				"}")
				.send().body();
	}
	
	
	
	/**   
	 * @Title: CertificateList  
	 * @Description: TODO     查询证书列表
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String CertificateList(String keyword,String status) {	
		return HttpRequest.get(certificate_list_url).query("access_token", token).query("keyword",keyword).query("pageNumber","1")
				.query("pageSize","20").query("status",status)
				.send().body();
	}
	
	
	/**   
	 * @Title: CertificateDetail  
	 * @Description: TODO     证书列表-查看-有效人员
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String CertificateDetail(String certificate_id) {	
		return HttpRequest.get(certificate_detail_url(certificate_id)).query("access_token", token)
				.send().body();
	}
	
	

	

	/**   
	 * @Title: QueryRelation  
	 * @Description: TODO     证书列表-查看-关联关系
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String QueryRelation(String certificate_id) {	
		return HttpRequest.get(query_relation_url(certificate_id)).query("access_token", token).query("page_number","1")
				.query("page_size","20")
				.send().body();
	}
	
	
	
	/**   
	 * @Title: CertificatesExport  
	 * @Description: TODO     证书列表导出
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String CertificatesExport(String query_interval_type,String status) {	
		return HttpRequest.get(certificates_export_url).query("access_token", token).query("query_interval_type",query_interval_type)
				.query("status",status)
				.send().body();
	}
	
	
	
	/**   
	 * @Title: EditCertificate  
	 * @Description: TODO     编辑证书
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String EditCertificate(String logo_url,String title,String certificate_id,String create_time,String image_url,
			String language_type,String preview_url,String template_id,String common_seal_url) {	
		return HttpRequest.post(edit_certificate_url).query("access_token", token).data("{\r\n" + 
				"      \"certificate_code\": \"11112222\",\r\n" + 
				"      \"layout_id\": \"verticalLeft\",\r\n" + 
				"      \"create_user_name\": null,\r\n" + 
				"      \"logo_url\": \""+logo_url+"\",\r\n" + 
				"      \"title\": \""+title+"\",\r\n" + 
				"      \"use_common_seal\": null,\r\n" + 
				"      \"message_properties\": \"{\\\"countdown_date\\\":1,\\\"created\\\":true,\\\"user\\\":true,\\\"nominee\\\":{\\\"checked\\\":false,\\\"users\\\":[]}}\",\r\n" + 
				"      \"permanently_valid\": \"permanent\",\r\n" + 
				"      \"id\": \""+certificate_id+"\",\r\n" + 
				"      \"summary\": \"兹证明通过认证考核，特颁发此证\",\r\n" + 
				"      \"create_time\": \""+create_time+"\",\r\n" + 
				"      \"image_url\": \""+image_url+"\",\r\n" + 
				"      \"display\": {\r\n" + 
				"            \"certificate_code\": {\r\n" + 
				"                  \"checked\": true\r\n" + 
				"            },\r\n" + 
				"            \"department\": {\r\n" + 
				"                  \"checked\": true\r\n" + 
				"            },\r\n" + 
				"            \"picture\": {\r\n" + 
				"                  \"checked\": true\r\n" + 
				"            },\r\n" + 
				"            \"logo_url\": {\r\n" + 
				"                  \"checked\": true\r\n" + 
				"            },\r\n" + 
				"            \"title\": {\r\n" + 
				"                  \"checked\": true,\r\n" + 
				"                  \"settings\": \"{\\\"font_family\\\":\\\"Pl-Song\\\",\\\"font_size\\\":\\\"96\\\",\\\"color\\\":\\\"#1F2B2B\\\",\\\"line_height\\\":\\\"1.4\\\"}\"\r\n" + 
				"            },\r\n" + 
				"            \"summary\": {\r\n" + 
				"                  \"checked\": true,\r\n" + 
				"                  \"settings\": \"{\\\"font_family\\\":\\\"Pl-Song\\\",\\\"font_size\\\":\\\"42\\\",\\\"color\\\":\\\"#27282D\\\",\\\"line_height\\\":\\\"1.5\\\"}\"\r\n" + 
				"            },\r\n" + 
				"            \"common_seal\": {\r\n" + 
				"                  \"checked\": true,\r\n" + 
				"                  \"settings\": \"{\\\"font_family\\\":\\\"Pl-Song\\\",\\\"font_size\\\":\\\"52\\\",\\\"color\\\":\\\"#d64f4f\\\",\\\"line_height\\\":\\\"1\\\"}\"\r\n" + 
				"            },\r\n" + 
				"            \"custom_field\": {\r\n" + 
				"                  \"field\": [\r\n" + 
				"                        \"issue_date\",\r\n" + 
				"                        \"effect_date\",\r\n" + 
				"                        \"overdue_date\"\r\n" + 
				"                  ],\r\n" + 
				"                  \"settings\": \"{\\\"font_family\\\":\\\"Pl-Song\\\",\\\"font_size\\\":\\\"36\\\",\\\"color\\\":\\\"#2F3033\\\",\\\"line_height\\\":\\\"1.4\\\"}\"\r\n" + 
				"            }\r\n" + 
				"      },\r\n" + 
				"      \"new_version\": \"true\",\r\n" + 
				"      \"language_type\": \""+language_type+"\",\r\n" + 
				"      \"message\": \"send_message\",\r\n" + 
				"      \"overdue_user_count\": null,\r\n" + 
				"      \"common_seal_url\": \"\",\r\n" + 
				"      \"expire_date\": 0,\r\n" + 
				"      \"user_count\": null,\r\n" + 
				"      \"preview_url\": \""+preview_url+"\",\r\n" + 
				"      \"company_name\": \"company_name test\",\r\n" + 
				"      \"name\": \""+title+"\",\r\n" + 
				"      \"template_id\": \""+template_id+"\",\r\n" + 
				"      \"style\": \"vertical\",\r\n" + 
				"      \"create_user\": \""+user_id+"\",\r\n" + 
				"      \"status\": 1,\r\n" + 
				"      \"common_seal\": {\r\n" + 
				"            \"type\": \"name\",\r\n" + 
				"            \"company_name\": \"company_name test\",\r\n" + 
				"            \"common_seal_url\": \""+common_seal_url+"\"\r\n" + 
				"      },\r\n" + 
				"      \"access_token\": \""+token+"\"\r\n" + 
				"}")
				.send().body();
	}
	
	
	/**   
	 * @Title: SaveCertificates  
	 * @Description: TODO     颁发证书
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String SaveCertificates(String issue_date,String effect_date,String certificate_id) {	
		return HttpRequest.post(save_certificate_url).query("access_token", token).data("{\r\n" + 
				"      \"groupIds\": \"\",\r\n" + 
				"      \"departmentIds\": \"\",\r\n" + 
				"      \"userIds\": \""+user_id+"\",\r\n" + 
				"      \"postIds\": \"\",\r\n" + 
				"      \"issue_date\": \""+issue_date+"\",\r\n" + 
				"      \"effect_date\": \""+effect_date+"\",\r\n" + 
				"      \"certificate_reason\": \"颁发证书测试\",\r\n" + 
				"      \"certificate_source\": \"颁发证书测试\",\r\n" + 
				"      \"certificate_Id\": \""+certificate_id+"\",\r\n" + 
				"      \"access_token\": \""+token+"\"\r\n" + 
				"}")
				.send().body();
	}
	
	
	
	/**   
	 * @Title: StatusCertificates  
	 * @Description: TODO     修改证书状态
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String StatusCertificates(String certificate_id) {	
		return HttpRequest.post(status_certificate_url).query("access_token", token).query("id",certificate_id)
				.send().body();
	}
	
	
	/**   
	 * @Title: DeleteCertificates  
	 * @Description: TODO     删除证书
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String DeleteCertificates(String certificate_id,Boolean need_revoke) {	
		return HttpRequest.post(delete_certificate_url(certificate_id)).query("access_token", token).data("{\r\n" + 
				"      \"need_revoke\": \""+need_revoke+"\",\r\n" + 
				"      \"access_token\": \""+token+"\"\r\n" + 
				"}")
				.send().body();
	}
	
	
	
	/**   
	 * @Title: RemindCertificates  
	 * @Description: TODO     证书列表-查看-有效人员-提醒
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String RemindCertificates(String record_id) {	
		return HttpRequest.post(remind_url).query("access_token", token).data("{\r\n" + 
				"      \"type\": \"validity\",\r\n" + 
				"      \"certificate_record_ids\": [\r\n" + 
				"            \""+record_id+"\"\r\n" + 
				"      ],\r\n" + 
				"      \"access_token\": \""+token+"\"\r\n" + 
				"}")
				.send().body();
	}
	
	
	/**   
	 * @Title: UserExport  
	 * @Description: TODO     证书列表-查看-有效人员-导出数据
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String UserExport(String certificate_id,String status,String start_date,String end_date,String date_type,String type) {	
		return HttpRequest.get(user_export_url(certificate_id)).query("access_token", token).query("status",status)
				.query("start_date",start_date).query("end_date",end_date).query("date_type",date_type).query("type",type)
				.query("page_number","1")
				.send().body();
	}
	
	
	/**   
	 * @Title: DeleteUser  
	 * @Description: TODO     证书列表-查看-有效人员-撤销
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String DeleteUser(String certificate_id,String record_id) {	
		return HttpRequest.post(delete_user_url(certificate_id)).query("access_token", token).data("{\r\n" + 
				"      \"revoke_reason\": \"revoke test\",\r\n" + 
				"      \"certificate_record_ids\": [\r\n" + 
				"            \""+record_id+"\"\r\n" + 
				"      ],\r\n" + 
				"      \"access_token\": \""+token+"\"\r\n" + 
				"}")
				.send().body();
	}
	
	
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
	 * @Title: StudyAdd  
	 * @Description: TODO  创建学习任务
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String StudyPlanAdd(String title,String certificate_id,String courseId) {		
	    return HttpRequest.post(studyplan_add_url).query("access_token", token).data("{\r\n" + 
	    		"      \"title\": \""+title+"\",\r\n" + 
	    		"      \"beginTime\": "+DateUtil.getTimeStamp(-7, "")+",\r\n" + 
				"      \"endTime\": "+DateUtil.getTimeStamp(9, "")+",\r\n" +
	    		"      \"projectId\": \"\",\r\n" + 
	    		"      \"summary\": \"\",\r\n" + 
	    		"      \"sync_progress\": \"false\",\r\n" + 
	    		"      \"classify_id\": \"\",\r\n" + 
	    		"      \"dingImgUrl\": \"\",\r\n" + 
	    		"      \"departmentIds\": \"\",\r\n" + 
	    		"      \"supervisor_department_ids\": [],\r\n" + 
	    		"      \"groupIds\": \"\",\r\n" + 
	    		"      \"isGetScore\": true,\r\n" + 
	    		"      \"isNoticeAll\": 0,\r\n" + 
	    		"      \"planCertificateId\": \""+certificate_id+"\",\r\n" + 
	    		"      \"postIds\": \"\",\r\n" + 
	    		"      \"progress\": 100,\r\n" + 
	    		"      \"studyScore\": {\r\n" + 
	    		"            \"finishScore\": 0,\r\n" + 
	    		"            \"unFinishScore\": 0\r\n" + 
	    		"      },\r\n" + 
	    		"      \"scheduleSetting\": \"free_mode\",\r\n" + 
	    		"      \"studyTimeLimit\": \"30\",\r\n" + 
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
	    		"                              \"courseId\": \""+courseId+"\",\r\n" + 
	    		"                              \"courseSort\": 0,\r\n" + 
	    		"                              \"courseType\": 3,\r\n" + 
	    		"                              \"flag\": 1,\r\n" + 
	    		"                              \"mappingId\": \"\",\r\n" + 
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
	 * @Title: GetOne  
	 * @Description: TODO  学习任务详情页
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String GetOne(String studyplan_id) {		
	    return HttpRequest.get(getOne_url).query("access_token", token).query("id",studyplan_id)
	    		.send().body();
	}
	
	
	/**   
	 * @Title: SaveProcess  
	 * @Description: TODO  完成学习任务
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String SaveProcess(String studyplan_id,String course_id,String tempTime) {		
	    return HttpRequest.post(save_progress_url(studyplan_id,course_id)).query("access_token", token).data("{\r\n" + 
	    		"      \"progress\": 100,\r\n" + 
	    		"      \"recent_start\": 0,\r\n" + 
	    		"      \"tempTime\": \""+tempTime+"\",\r\n" + 
	    		"      \"access_token\": \""+token+"\"\r\n" + 
	    		"}")
	    		.send().body();
	}
	
	
	/**   
	 * @Title: CertificatesUser  
	 * @Description: TODO  我的证书记录
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String CertificatesUser(String source_type,String start_date,String date_type,String keyword,String end_date) {		
	    return HttpRequest.get(certificates_user_url).query("access_token", token).query("source_type",source_type).query("user_id",user_id)
	    		.query("start_date",start_date).query("date_type",date_type).query("keyword",keyword).query("end_date",end_date)
	    		.query("page_number","1").query("page_size","20")
	    		.send().body();
	}
	
	
	/**
	 * @throws UnsupportedEncodingException    
	 * @Title: CertificatesUsers  
	 * @Description: TODO  证书列表-查看-有效人员数据
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String CertificatesUsers(String certificate_id,String keyword,String status,String start_date,String end_date,
			String date_type,String type) {		
		return HttpRequest.get(certificate_user_url(certificate_id)).query("access_token", token).query("keyword",keyword)
		.query("status",status).query("start_date",start_date).query("end_date",end_date).query("date_type",date_type)
		.query("type",type).query("page_number","1").query("page_size","20")
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
	
	
	
}
