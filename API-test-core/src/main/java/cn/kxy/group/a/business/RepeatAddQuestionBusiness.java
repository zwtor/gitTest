package cn.kxy.group.a.business;

import cn.kxy.authentication.business.CertificateBusiness;
import cn.kxy.base.business.EnterpriseData;
import cn.kxy.base.business.EnterpriseDataUrl;
import cn.kxy.base.business.TokenData;
import cn.kxy.setting.bussiness.UserBusiness;
import com.alibaba.fastjson.JSONPath;
import com.lazy.common.utils.CommonData;
import com.lazy.common.utils.DateUtil;
import com.lazy.httpclient.utils.HttpRequest;

public class RepeatAddQuestionBusiness {

	public static String enterprise_url = EnterpriseDataUrl.getEnterpriseUrl();
	public static String enterprise_id = EnterpriseData.getEnterpriseId();
	public static String access_token = TokenData.getMangerToken();
	public static String user_id = UserBusiness.getUserId();
	
	public static String certificateaddURL = enterprise_url + "v1/certificate/add";
	public static String certificateaDisableURL = enterprise_url + "certificate/modifyStatus";
	public static String qualificationsaddURL = enterprise_url + "v1/qualifications";
	public static String addOrUpdateURL = enterprise_url + "v2/"+enterprise_id+"/studyTemplate/addOrUpdate";
	public static String addSaveupdateURL = enterprise_url + "v2/"+enterprise_id+"/employees/save_or_update_base_info";
	public static String addUpdatecontentURL = enterprise_url + "v2/"+enterprise_id+"/employees/update_content";
	public static String addUpdatesettingURL = enterprise_url + "v2/"+enterprise_id+"/employees/update_setting_publish";
	public static String deleteEmployTrainTaskURL = enterprise_url + "plan/employeeTrain/deleteEmployTrainTask";

	public static String qualificationdeleteURL(String id) {
		return enterprise_url + "v1/qualifications/" + id + "/delete";
	}

	public static String title = "qualifications" + CommonData.getStringRandom(3);
	public static String name = "Certificate" + CommonData.getStringRandom(3);

	/**
	 * @Title: creatCertificate @Description: TODO( 创建证书) @param: @param
	 *         name @param: @param certificateCode @param: @param
	 *         companyName @param: @param useCommonSeal @param: @param
	 *         languageType @param: @return @return: String @throws
	 */
	public static String creatCertificate(String name, String certificateCode, String companyName, String useCommonSeal,
			String languageType) {

		return HttpRequest.post(certificateaddURL).query("access_token", access_token)
				.form("certificateCode", certificateCode).form("companyName", companyName).form("expireDate", "0")
				.form("imageUrl", "https://oss.coolcollege.cn/1791044561745874944.png")
				.form("languageType", languageType)
				.form("logoUrl", "https://oss.coolcollege.cn/1789887239308840960.png").form("message", "no_message")
				.form("messageProperties", "{\"countdown_date\":\"1\",\"created\":\"false\",\"user\":\"false\"}")
				.form("name", name).form("permanentlyValid", "permanent").form("summary", "兹证明通过认证考核，特颁发此证")
				.form("templateId", "1")
				.form("display",
						"{\r\n" + "  \"summary\":\"true\",\r\n" + "  \"commonSeal\":\"" + useCommonSeal + "\",\r\n"
								+ "  \"certificateCode\":\"true\",\r\n" + "  \"issueTime\":\"true\",\r\n"
								+ "  \"name\":\"true\",\r\n" + "  \"department\":\"true\",\r\n"
								+ "  \"logoUrl\":\"true\",\r\n" + "  \"expire\":\"true\"\r\n" + "}\r\n" + "")
				.send().body();

	}

	// 获取证书id
	public static String getcertificateId(String name) {

		return CertificateBusiness.getIdByKeyword(name);
	}

	/**
	 * 岗位认证添加单个问卷
	 * @param ques_id
	 * @param ficate_id
	 * @return
	 */
	public static String creatQualifications(String ques_id,String ficate_id) {
		return HttpRequest.post(qualificationsaddURL).query("access_token", access_token).form("title", title)
				.form("summary", "认证描述").form("certificateId", ficate_id)
				.form("scopes", "{\"department_list\":[],\"user_list\":[],\"group_list\":[],\"post_list\":[]}")
				.form("stageJson",
						"[\r\n" + "    {\r\n" + "        \"content\":\"\",\r\n" + "        \"stageName\":\"阶段1\",\r\n"
								+ "        \"isOrder\":false,\r\n" + "        \"stageSort\":1,\r\n"
								+ "        \"stageId\":\"\",\r\n" + "        \"startTime\":null,\r\n"
								+ "        \"endTime\":null,\r\n" + "        \"course\":[\r\n" + "            {\r\n"
								+ "                \"courseId\":\"\",\r\n" + "                \"courseType\":9,\r\n"
								+ "                \"flag\":9,\r\n" + "                \"courseSort\":0,\r\n"
								+ "                \"investigate\":{\r\n" + "                    \"id\":\"\",\r\n"
								+ "                    \"title\":\"调研名称11\",\r\n"
								+ "                    \"questionnaire_id\":\"" + ques_id + "\"\r\n"
								+ "                },\r\n" + "                \"resource_lock\":false\r\n"
								+ "            }\r\n" + "        ]\r\n" + "    }\r\n" + "]")
				.form("stagePass", "false").form("studyTimeLimit", "0").form("visibleRange", "all").send().body();
		
		
	
	

	}

	/**
	 * 岗位认证添加多次添加同一个问卷
	 * @param ques_id
	 * @param ficate_id
	 * @return
	 */
	public static String creatTwoQualification(String ques_id,String ficate_id) {
		return HttpRequest.post(qualificationsaddURL).query("access_token", access_token).form("title", title)
				.form("summary", "认证描述").form("certificateId", ficate_id)
				.form("scopes", "{\"department_list\":[],\"user_list\":[],\"group_list\":[],\"post_list\":[]}")
				.form("stageJson",
						"[\r\n" + 
						"    {\r\n" + 
						"        \"content\":\"\",\r\n" + 
						"        \"stageName\":\"阶段1\",\r\n" + 
						"        \"isOrder\":false,\r\n" + 
						"        \"stageSort\":1,\r\n" + 
						"        \"stageId\":\"\",\r\n" + 
						"        \"startTime\":null,\r\n" + 
						"        \"endTime\":null,\r\n" + 
						"        \"course\":[\r\n" + 
						"            {\r\n" + 
						"                \"courseId\":\"\",\r\n" + 
						"                \"courseType\":9,\r\n" + 
						"                \"flag\":9,\r\n" + 
						"                \"courseSort\":0,\r\n" + 
						"                \"investigate\":{\r\n" + 
						"                    \"id\":\"\",\r\n" + 
						"                    \"title\":\"调研名称11\",\r\n" + 
						"                    \"questionnaire_id\":\""+ques_id+"\"\r\n" + 
						"                },\r\n" + 
						"                \"resource_lock\":false\r\n" + 
						"            },\r\n" + 
						"            {\r\n" + 
						"                \"courseId\":\"\",\r\n" + 
						"                \"courseType\":9,\r\n" + 
						"                \"flag\":9,\r\n" + 
						"                \"courseSort\":1,\r\n" + 
						"                \"investigate\":{\r\n" + 
						"                    \"id\":\"\",\r\n" + 
						"                    \"title\":\"调研名称11\",\r\n" + 
						"                    \"questionnaire_id\":\""+ques_id+"\"\r\n" + 
						"                },\r\n" + 
						"                \"resource_lock\":false\r\n" + 
						"            }\r\n" + 
						"        ]\r\n" + 
						"    }\r\n" + 
						"]")
				.form("stagePass", "false").form("studyTimeLimit", "0").form("visibleRange", "all").send().body();
	}
	
	
	
	
	public static String creatTimedtask(String taskName, String ques_id) {
	
		String res = HttpRequest.post(addOrUpdateURL).query("access_token", access_token).data("{\r\n" + 
				"    \"operation\":\"create_study_template\",\r\n" + 
				"    \"save_step\":\"base_info\",\r\n" + 
				"    \"base_info\":{\r\n" + 
				"        \"title\":\""+taskName+"\",\r\n" + 
				"        \"summary\":\"\",\r\n" + 
				"        \"classify_id\":\"\",\r\n" + 
				"        \"plan_term\":2,\r\n" + 
				"        \"term_type\":\"D\",\r\n" + 
				"        \"ding_img_url\":\"\"\r\n" + 
				"    },\r\n" + 
				"    \"access_token\":\""+access_token+"\"\r\n" + 
				"}").send().body();

		String template_id = (String)JSONPath.read(res, "$.data.id");
		
		HttpRequest.post(addOrUpdateURL).query("access_token", access_token).data("{\r\n" + 
				"    \"operation\":\"create_study_template\",\r\n" + 
				"    \"template_id\":\""+template_id+"\",\r\n" + 
				"    \"save_step\":\"stage_content_draft\",\r\n" + 
				"    \"stage_content\":{\r\n" + 
				"        \"stage_json\":[\r\n" + 
				"            {\r\n" + 
				"                \"content\":\"\",\r\n" + 
				"                \"stage_name\":\"阶段1\",\r\n" + 
				"                \"order\":\"false\",\r\n" + 
				"                \"sort\":1,\r\n" + 
				"                \"course\":[\r\n" + 
				"                    {\r\n" + 
				"                        \"course_id\":\"\",\r\n" + 
				"                        \"course_type\":9,\r\n" + 
				"                        \"flag\":9,\r\n" + 
				"                        \"course_sort\":0,\r\n" + 
				"                        \"investigate\":{\r\n" + 
				"                            \"id\":\"\",\r\n" + 
				"                            \"title\":\"123\",\r\n" + 
				"                            \"questionnaire_id\":\""+ques_id+"\"\r\n" + 
				"                        }\r\n" + 
				"                    }\r\n" + 
				"                ]\r\n" + 
				"            }\r\n" + 
				"        ],\r\n" + 
				"        \"stage_pass\":\"false\"\r\n" + 
				"    },\r\n" + 
				"    \"access_token\":\""+access_token+"\"\r\n" + 
				"}").send().body();
	
		HttpRequest.post(addOrUpdateURL).query("access_token", access_token).data("{\r\n" + 
				"    \"operation\":\"create_study_template\",\r\n" + 
				"    \"template_id\":\""+template_id+"\",\r\n" + 
				"    \"save_step\":\"settings\",\r\n" + 
				"    \"settings\":{\r\n" + 
				"        \"get_score\":true,\r\n" + 
				"        \"plan_certificate_id\":\"\",\r\n" + 
				"        \"progress\":100,\r\n" + 
				"        \"get_score_info\":{\r\n" + 
				"            \"finish_score\":0,\r\n" + 
				"            \"unfinish_score\":0\r\n" + 
				"        },\r\n" + 
				"        \"is_all_in\":false,\r\n" + 
				"        \"user_ids\":\""+user_id+"\",\r\n" + 
				"        \"department_ids\":\"\",\r\n" + 
				"        \"group_ids\":\"\",\r\n" + 
				"        \"post_ids\":\"\",\r\n" + 
				"        \"scheduler_json\":{\r\n" + 
				"            \"interval\":1,\r\n" + 
				"            \"next_time\":\""+DateUtil.getRegularDateForHHMMSS(1)+"\",\r\n" + 
				"            \"start_time\":\""+DateUtil.getRegularDateForHHMMSS(1)+"\",\r\n" + 
				"            \"times\":1,\r\n" + 
				"            \"type\":\"period\",\r\n" + 
				"            \"scheduler_id\":\"\",\r\n" + 
				"            \"status\":\"on\"\r\n" + 
				"        },\r\n" + 
				"        \"study_time_limit\":0,\r\n" + 
				"        \"face_recognition\":\"off\",\r\n" + 
				"        \"supervisor_id\":\""+user_id+"\",\r\n" + 
				"        \"supervisor_paper\":true,\r\n" + 
				"        \"authority_range\":false,\r\n" + 
				"        \"times\":\"\",\r\n" + 
				"        \"stage_json\":[\r\n" + 
				"            {\r\n" + 
				"                \"content\":\"\",\r\n" + 
				"                \"stage_name\":\"阶段1\",\r\n" + 
				"                \"order\":\"false\",\r\n" + 
				"                \"sort\":1,\r\n" + 
				"                \"course\":[\r\n" + 
				"                    {\r\n" + 
				"                        \"course_id\":\"\",\r\n" + 
				"                        \"course_type\":9,\r\n" + 
				"                        \"flag\":9,\r\n" + 
				"                        \"course_sort\":0,\r\n" + 
				"                        \"investigate\":{\r\n" + 
				"                            \"id\":\"\",\r\n" + 
				"                            \"title\":\"123\",\r\n" + 
				"                            \"questionnaire_id\":\""+ques_id+"\"\r\n" + 
				"                        }\r\n" + 
				"                    }\r\n" + 
				"                ]\r\n" + 
				"            }\r\n" + 
				"        ],\r\n" + 
				"        \"stage_pass\":\"false\"\r\n" + 
				"    },\r\n" + 
				"    \"access_token\":\""+access_token+"\"\r\n" + 
				"}").send().body();
		return template_id;
	}
	
	
	//删除定时任务
	public static String deleteTimedtask(String template_id) {
		
		String deleteStudyURL = enterprise_url + "v2/"+enterprise_id+"/templates/"+template_id+"/delete_study";
		return HttpRequest.post(deleteStudyURL).query("access_token", access_token).form("access_token", access_token).send().body();
	}
	
	/**
	 * 创建新员工任务
	 * @param enployName
	 * @param ques_id
	 * @return
	 */
	
	public static String creatNewEmployeeTask(String enployName, String ques_id) {
		
		String res = HttpRequest.post(addSaveupdateURL).query("access_token", access_token).data(
				"{\r\n" + 
				"    \"train_name\":\""+enployName+"\",\r\n" + 
				"    \"train_limit\":1,\r\n" + 
				"    \"summary\":\"\",\r\n" + 
				"    \"classify_id\":\"\",\r\n" + 
				"    \"effect_time\":"+DateUtil.getOneHourLaterStamp()+",\r\n" + 
				"    \"access_token\":\""+access_token+"\"\r\n" + 
				"}").send().body();
		
		
		String plan_id = (String)JSONPath.read(res, "$.data");
	
		HttpRequest.post(addUpdatecontentURL).query("access_token", access_token).data(
				"{\r\n" + 
				"    \"plan_id\":\""+plan_id+"\",\r\n" + 
				"    \"stage_json\":[\r\n" + 
				"        {\r\n" + 
				"            \"content\":\"\",\r\n" + 
				"            \"stage_name\":\"阶段1\",\r\n" + 
				"            \"is_order\":false,\r\n" + 
				"            \"stage_sort\":1,\r\n" + 
				"            \"resources\":[\r\n" + 
				"                {\r\n" + 
				"                    \"course_id\":\"\",\r\n" + 
				"                    \"course_type\":9,\r\n" + 
				"                    \"flag\":4,\r\n" + 
				"                    \"course_sort\":0,\r\n" + 
				"                    \"investigate\":{\r\n" + 
				"                        \"id\":\"\",\r\n" + 
				"                        \"title\":\"123\",\r\n" + 
				"                        \"questionnaire_id\":\""+ques_id+"\"\r\n" + 
				"                    }\r\n" + 
				"                }\r\n" + 
				"            ]\r\n" + 
				"        }\r\n" + 
				"    ],\r\n" + 
				"    \"stage_pass\":false,\r\n" + 
				"    \"sync_progress\":\"false\",\r\n" + 
				"    \"access_token\":\""+access_token+"\"\r\n" + 
				"}").send().body();
		
		HttpRequest.post(addUpdatesettingURL).query("access_token", access_token).data(
				"{\r\n" + 
				"    \"plan_id\":\""+plan_id+"\",\r\n" + 
				"    \"save_step\":3,\r\n" + 
				"    \"override_sub_dept\":1,\r\n" + 
				"    \"is_new_employee\":false,\r\n" + 
				"    \"department_ids\":\"1\",\r\n" + 
				"    \"post_ids\":\"\",\r\n" + 
				"    \"study_time_limit\":0,\r\n" + 
				"    \"face_recognition\":\"off\",\r\n" + 
				"    \"progress\":100,\r\n" + 
				"    \"times\":\"\",\r\n" + 
				"    \"operation_times\":\"\",\r\n" + 
				"    \"certificate_id\":\"\",\r\n" + 
				"    \"study_score\":\"{\\\"finishScore\\\":0,\\\"unFinishScore\\\":0}\",\r\n" + 
				"    \"supervisor_id\":\""+user_id+"\",\r\n" + 
				"    \"supervisor_paper\":true,\r\n" + 
				"    \"is_get_score\":true,\r\n" + 
				"    \"access_token\":\""+access_token+"\"\r\n" + 
				"}").send().body();
		
		return plan_id;
	}
	
	//删除新员工任务
	public static String deleteEmployTask(String planId) {
		return HttpRequest.post(deleteEmployTrainTaskURL).query("planId", planId).query("access_token", access_token).data(
				"{\"access_token\":\"ea8a9cfe052e777496e0d8836632a18b\"}").send().body();
	}
	
	// 删除岗位认证
	public static String deletequalifications(String id) {
		return HttpRequest.post(qualificationdeleteURL(id)).query("currentAuthId", id)
				.query("access_token", access_token).data("{\"access_token\":\"\"" + access_token + "}").send().body();
	}

	/**
	 * 先停用证书，再删除证书
	 * 
	 * @return
	 */
	public static String deletecertificate(String id) {


		HttpRequest.post(certificateaDisableURL).query("access_token", access_token).form("id", id).send().body();
		String certificateadeleteURL = enterprise_url + "v2/" + enterprise_id + "/certificate/" + id + "/delete";
		return HttpRequest.post(certificateadeleteURL).query("access_token", access_token)
				.data("{\"access_token\":\"" + access_token + "\"}").send().body();
	}

}
