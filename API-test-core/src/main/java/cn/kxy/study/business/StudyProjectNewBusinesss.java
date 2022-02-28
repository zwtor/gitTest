package cn.kxy.study.business;

import cn.kxy.base.business.EnterpriseData;
import cn.kxy.base.business.EnterpriseDataUrl;
import cn.kxy.base.business.TokenData;
import cn.kxy.setting.bussiness.ClassificationBusines;
import cn.kxy.setting.bussiness.UserBusiness;
import com.alibaba.fastjson.JSONPath;
import com.lazy.assured.utils.PostRequestTools;
import com.lazy.httpclient.utils.HttpRequest;

public class StudyProjectNewBusinesss {

	public static String token = TokenData.getMangerToken();
	public static String enterpriseId = EnterpriseData.getEnterpriseId();
	public static String enterpriseUrl = EnterpriseDataUrl.getEnterpriseUrl();
	public static String userId = UserBusiness.getUserId();
	
	public static String save_url = enterpriseUrl + "v2/"+enterpriseId+"/study_projects/save";
	
	public static String camp_periods_url = enterpriseUrl + "v2/"+enterpriseId+"/camp_periods";
	
	public static String savecamp_periods_url = enterpriseUrl + "v2/"+enterpriseId+"/camp_periods/save";
	
	public static String start_study_url(String id) {
		return enterpriseUrl + "v2/"+enterpriseId+"/users/"+userId+"/camp_periods/"+id+"/start_study";
	}
	
	public static String startStudy(String id) {
		
		return PostRequestTools.RequestBodyByPost("{}", token,"enterprise_id", enterpriseId, "user_id",userId, start_study_url(id));
		
//		
//		 HttpRequest.post(start_study_url(id)).query("access_token",token).query("enterprise_id", enterpriseId)
//				.query("user_id",userId).data("{}").send().body();
	}
	
	
	
	public static String change_status_url (String id) {
		return enterpriseUrl + "v2/"+enterpriseId+"/camp_periods/"+id+"/change_status";
	}
	
	public static String delete_url(String id) {
		return enterpriseUrl + "v2/"+enterpriseId+"/camp_periods/"+id+"/delete";
	}
	
	public static String deleteCampPeriods(String id) {
		return HttpRequest.post(delete_url(id)).query("access_token",token).data("{\"access_token\":\""+token+"\"}").send().body();
	}
	
	//normal disabled
	public static String changeStatus(String id,String status) {
		return HttpRequest.post(change_status_url(id)).query("access_token",token).data("{\"status\":\""+status+
				"\",\"access_token\":\""+token+"\"}").send().body();
	}
	
	public static String queryCampPeriods(String project_id,String status,String keyword) {
		return HttpRequest.get(camp_periods_url).query("access_token",token).query("project_id",project_id).query("status",status)
				.query("keyword",keyword).query("page_number","1").query("page_size","20").send().body();
	}
	
	public static String saveCampPeriods(String id,String stage_id,String title,String registration_start_time,String registration_end_time,String start_time
			,String end_time,String start_time_1,String end_time_1,String course_id_01,String course_id_02,String course_id_03,String course_id_04
			,String unlocking_time,String is_free,String official_price,String preferential_price) {
		return HttpRequest.post(savecamp_periods_url).query("access_token", token).data("{\n" + 
				"  \"project_id\":\""+id+"\",\n" + 
				"  \"title\":\""+title+"\",\n" + 
				"  \"registration_start_time\":"+registration_start_time+",\n" + 
				"  \"registration_end_time\":"+registration_end_time+",\n" + 
				"  \"start_time\":"+start_time+",\n" + 
				"  \"end_time\":"+end_time+",\n" + 
				"  \"schedule_settings\":[\n" + 
				"    {\n" + 
				"      \"id\":\""+stage_id+"\",\n" + 
				"      \"start_time\":"+start_time_1+",\n" + 
				"      \"end_time\":"+end_time_1+",\n" + 
				"      \"resources\":[\n" + 
				"        {\n" + 
				"          \"course_id\":\""+course_id_01+"\",\n" + 
				"          \"unlocking_time\":"+unlocking_time+"\n" + 
				"        },\n" + 
				"        {\n" + 
				"          \"course_id\":\""+course_id_02+"\",\n" + 
				"          \"unlocking_time\":"+unlocking_time+"\n" + 
				"        },\n" + 
				"        {\n" + 
				"          \"course_id\":\""+course_id_03+"\",\n" + 
				"          \"unlocking_time\":"+unlocking_time+"\n" + 
				"        },\n" + 
				"        {\n" + 
				"          \"course_id\":\""+course_id_04+"\",\n" + 
				"          \"unlocking_time\":"+unlocking_time+"\n" + 
				"        }\n" + 
				"       ]\n" + 
				"    }],\n" + 
				"  \"is_free\":\""+is_free+"\",\n" + 
				"  \"official_price\":\""+official_price+"\",\n" + 
				"  \"preferential_price\":\""+preferential_price+"\",\n" + 
				"  \"access_token\":\""+token+"\"\n" + 
				"}\n" + 
				"").send().body();
	}
	
	
	public static String saveBaseInfo(String title,String class_id ) {
		return HttpRequest.post(save_url).query("access_token", token).data("{\r\n" + 
				"  \"save_step\":\"base_info\",\r\n" + 
				"  \"base_info\":{\r\n" + 
				"    \"title\":\""+title+" \",\r\n" + 
				"  \"style_type\": \"normal\","+
				"    \"summary\":\"\",\r\n" + 
				"    \"classify\":\""+class_id+"\",\r\n" + 
				"    \"cover\":\"https://oss.coolcollege.cn/1789917624419880960.png\",\r\n" + 
				"    \"base_cover\":\"https://oss.coolcollege.cn/1789917624419880960.png\",\r\n" + 
				"    \"cover_type\":1,\r\n" + 
				"    \"ding_img_url\":\"\",\r\n" + 
				"    \"open_learning_group\":\"false\"\r\n" + 
				"  },\r\n" + 
				"  \"access_token\":\""+token+"\"\r\n" + 
				"}\r\n" + 
				"").send().body();
	}
	
	
	public static String saveStageContentDraft (String id) {
		return HttpRequest.post(save_url).query("access_token", token).data("{\r\n" + 
				"  \"save_step\":\"stage_content_draft\",\r\n" + 
				"  \"id\":\""+id+"\",\r\n" + 
				"  \"stage_content\":{\r\n" + 
				"    \"stage_pass\":\"false\",\r\n" + 
				"    \"stages\":[\r\n" + 
				"      {\r\n" + 
				"        \"content\":\"\",\r\n" + 
				"        \"stage_name\":\"step one\",\r\n" + 
				"        \"is_order\":false,\r\n" + 
				"        \"sort\":1,\r\n" + 
				"        \"resources\":[\r\n" + 
				"        ]\r\n" + 
				"      }]\r\n" + 
				"  },\r\n" + 
				"  \"access_token\":\""+token+"\"\r\n" + 
				"}\r\n" + 
				"").send().body();
	}
	
	public static String saveSettingsDraft (String id) {
		return HttpRequest.post(save_url).query("access_token", token).data("{\n" + 
				"  \"save_step\":\"settings_draft\",\n" + 
				"  \"id\":\""+id+"\",\n" + 
				"  \"settings\":{\n" + 
				"    \"study_time_limit\":0,\n" + 
				"    \"face_recognition\":\"off\",\n" + 
				"    \"progress\":100,\n" + 
				"    \"times\":1,\n" + 
				"    \"operation_times\":1,\n" + 
				"    \"finish_evaluation\":false,\n" + 
				"    \"certificate_id\":\"\",\n" + 
				"    \"is_get_score\":true,\n" + 
				"    \"study_score\":{\n" + 
				"      \"finish_score\":0,\n" + 
				"      \"unfinish_score\":0\n" + 
				"    },\n" + 
				"    \"is_all\":1,\n" + 
				"    \"user_ids\":\"\",\n" + 
				"    \"department_ids\":\"\",\n" + 
				"    \"group_ids\":\"\",\n" + 
				"    \"post_ids\":\"\",\n" + 
				"    \"supervisor_ids\":[\n" + 
				"    ],\n" + 
				"    \"supervisor_paper\":true,\n" + 
				"    \"authority_range\":false,\n" + 
				"    \"supervisor_department_ids\":[\n" + 
				"    ],\n" + 
				"    \"enroll_audit\":\"un_need\",\n" + 
				"    \"enroll_limit\":\"false\",\n" + 
				"    \"limit_count\":1,\n" + 
				"    \"open_evaluation_result\":false,\n" + 
				"    \"is_free\":true,\n" + 
				"    \"official_price\":\"\",\n" + 
				"    \"preferential_price\":\"\"\n" + 
				"  },\n" + 
				"  \"access_token\":\""+token+"\"\n" + 
				"}\n" + 
				"").send().body();
		
	}
	
	public static String saveSetting(String id) {
		return HttpRequest.post(save_url).query("access_token", token).data("{\n" + 
				"  \"save_step\":\"settings\",\n" + 
				"  \"id\":\""+id+"\",\n" + 
				"  \"settings\":{\n" + 
				"    \"study_time_limit\":0,\n" + 
				"    \"face_recognition\":\"off\",\n" + 
				"    \"progress\":100,\n" + 
				"    \"times\":1,\n" + 
				"    \"operation_times\":1,\n" + 
				"    \"finish_evaluation\":false,\n" + 
				"    \"certificate_id\":\"\",\n" + 
				"    \"is_get_score\":true,\n" + 
				"    \"study_score\":{\n" + 
				"      \"finish_score\":0,\n" + 
				"      \"unfinish_score\":0\n" + 
				"    },\n" + 
				"    \"is_all\":1,\n" + 
				"    \"user_ids\":\"\",\n" + 
				"    \"department_ids\":\"\",\n" + 
				"    \"group_ids\":\"\",\n" + 
				"    \"post_ids\":\"\",\n" + 
				"    \"supervisor_ids\":[\n" + 
				"    ],\n" + 
				"    \"supervisor_paper\":true,\n" + 
				"    \"authority_range\":false,\n" + 
				"    \"supervisor_department_ids\":[\n" + 
				"    ],\n" + 
				"    \"enroll_audit\":\"un_need\",\n" + 
				"    \"enroll_limit\":\"false\",\n" + 
				"    \"limit_count\":1,\n" + 
				"    \"open_evaluation_result\":false,\n" + 
				"    \"is_free\":true,\n" + 
				"    \"official_price\":\"\",\n" + 
				"    \"preferential_price\":\"\"\n" + 
				"  },\n" + 
				"  \"access_token\":\""+token+"\"\n" + 
				"}\n" + 
				"").send().body();
	}
	
	public static String saveStageContent(String id,String course_id,String paper_id,String questionnaire_id) {
		return HttpRequest.post(save_url).query("access_token", token).data("{\n" + 
				"  \"save_step\":\"stage_content\",\n" + 
				"  \"id\":\""+id+"\",\n" + 
				"  \"stage_content\":{\n" + 
				"    \"stage_pass\":\"false\",\n" + 
				"    \"stages\":[\n" + 
				"      {\n" + 
				"        \"content\":\"\",\n" + 
				"        \"stage_name\":\"step one\",\n" + 
				"        \"is_order\":false,\n" + 
				"        \"sort\":1,\n" + 
				"        \"resources\":[\n" + 
			
				"          {\n" + 
				"            \"course_id\":\""+course_id+"\",\n" + 
				"            \"sort\":0,\n" + 
				"            \"type\":1\n" + 
				"          },\n" + 
				"          {\n" + 
				"            \"type\":6,\n" + 
				"            \"sort\":1,\n" + 
				"            \"course_id\":\"\",\n" + 
				"            \"exam\":{\n" + 
				"              \"title\":\"randomExam\",\n" + 
				"              \"cheat_flag\":0,\n" + 
				"              \"exam_duration\":45,\n" + 
				"              \"face_recognition\":\"off\",\n" + 
				"              \"summary\":\"\",\n" + 
				"              \"mark_type\":1,\n" + 
				"              \"paper_id\":\""+paper_id+"\",\n" + 
				"              \"pass_line\":50,\n" + 
				"              \"question_banks\":[\n" + 
				"              ],\n" + 
				"              \"question_mode\":1,\n" + 
				"              \"show_knowledge\":\"show\",\n" + 
				"              \"repeat_exam\":true,\n" + 
				"              \"multiple_count\":\"\",\n" + 
				"              \"multiple_unit_score\":\"\",\n" + 
				"              \"fill_blank_count\":\"\",\n" + 
				"              \"fill_blank_unit_score\":\"\",\n" + 
				"              \"short_answer_count\":\"\",\n" + 
				"              \"short_answer_unit_score\":\"\",\n" + 
				"              \"single_count\":\"\",\n" + 
				"              \"single_unit_score\":\"\",\n" + 
				"              \"true_or_false_count\":\"\",\n" + 
				"              \"true_or_false_unit_score\":\"\",\n" + 
				"              \"total_score\":100,\n" + 
				"              \"answer_parsing\":5,\n" + 
				"              \"passing_score\":\"50.0\",\n" + 
				"              \"cut_screen_count\":-1,\n" + 
				"              \"re_exam_rule\":0,\n" + 
				"              \"re_exam_number\":0,\n" + 
				"              \"score_rule\":0,\n" + 
				"              \"view_rule\":1\n" + 
				"            }\n" + 
				"          },\n" + 
				"          {\n" + 
				"            \"sort\":2,\n" + 
				"            \"type\":9,\n" + 
				"            \"course_id\":\"\",\n" + 
				"            \"questionnaire_id\":\""+questionnaire_id+"\"\n" + 
				"          },\n" + 
				"          {\n" + 
				"            \"type\":10,\n" + 
				"            \"courseSort\":4,\n" + 
				"            \"sort\":3,\n" + 
				"            \"flag\":4,\n" + 
				"            \"course_id\":\"\",\n" + 
				"            \"title\":\"实操\",\n" + 
				"            \"content\":\"作业要求\",\n" + 
				"            \"resources\":[\n" + 
				"            ],\n" + 
				"            \"scoreSetting\":{\n" + 
				"              \"scoreSwitch\":\"off\",\n" + 
				"              \"score\":100\n" + 
				"            },\n" + 
				"            \"workflowJson\":{\n" + 
				"              \"type\":\"or\",\n" + 
				"              \"userIds\":[\n" + 
				"                \""+userId+"\"]\n" + 
				"            }\n" + 
				"          }]\n" + 
				"      }]\n" + 
				"  },\n" + 
				"  \"access_token\":\""+token+"\"\n" + 
				"}\n" + 
				"").send().body();
	}
	
	public static String addStudyProject(String title ,String class_id,String paper_id,String certificate_id,String course_id,String questionnaire_id) {
		String id="";
		String res = StudyProjectNewBusinesss.saveBaseInfo(title, class_id);
		id = (String)JSONPath.read(res, "$.id");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		StudyProjectNewBusinesss.saveStageContentDraft(id);
		 StudyProjectNewBusinesss.saveStageContent(id,
					course_id,paper_id, 
					questionnaire_id);
		 StudyProjectNewBusinesss.saveSettingsDraft(id);
		 return StudyProjectNewBusinesss.saveSetting(id);
	}
	
	public static String addStudyProjectSingUp(String title ,String paper_id,String mark_type,String repeat_exam,String answer_parsing,
			String re_exam_rule,String re_exam_number,String enroll_audit,String enroll_limit,String limit_count) {
		String id="";
		
		String res = StudyProjectNewBusinesss.saveBaseInfo(title, ClassificationBusines.getPrimaryId());
		id = (String)JSONPath.read(res, "$.id");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		StudyProjectNewBusinesss.saveStageContentDraft(id);
		HttpRequest.post(save_url).query("access_token", token).data("{\n" + 
				"  \"save_step\":\"stage_content\",\n" + 
				"  \"id\":\""+id+"\",\n" + 
				"  \"stage_content\":{\n" + 
				"    \"stage_pass\":\"false\",\n" + 
				"    \"stages\":[\n" + 
				"      {\n" + 
				"        \"content\":\"\",\n" + 
				"        \"stage_name\":\"阶段1\",\n" + 
				"        \"is_order\":false,\n" + 
				"        \"sort\":1,\n" + 
				"        \"resources\":[\n" + 
				"          {\n" + 
				"            \"type\":6,\n" + 
				"            \"sort\":0,\n" + 
				"            \"course_id\":\"\",\n" + 
				"            \"exam\":{\n" + 
				"              \"title\":\"随机考试\",\n" + 
				"              \"cheat_flag\":0,\n" + 
				"              \"exam_duration\":60,\n" + 
				"              \"face_recognition\":\"off\",\n" + 
				"              \"summary\":\"\",\n" + 
				"              \"mark_type\":"+mark_type+",\n" + 
				"              \"paper_id\":\"\",\n" + 
				"              \"pass_line\":60,\n" + 
				"              \"question_banks\":[\n" + 
				"                \""+paper_id+"\"],\n" + 
				"              \"question_mode\":2,\n" + 
				"              \"show_knowledge\":\"show\",\n" + 
				"              \"repeat_exam\":"+repeat_exam+",\n" + 
				"              \"multiple_count\":0,\n" + 
				"              \"multiple_unit_score\":0,\n" + 
				"              \"fill_blank_count\":1,\n" + 
				"              \"fill_blank_unit_score\":10,\n" + 
				"              \"fill_blank_type\":1,\n" + 
				"              \"short_answer_count\":0,\n" + 
				"              \"short_answer_unit_score\":0,\n" + 
				"              \"short_answer_type\":1,\n" + 
				"              \"single_count\":1,\n" + 
				"              \"single_unit_score\":10,\n" + 
				"              \"true_or_false_count\":0,\n" + 
				"              \"true_or_false_unit_score\":0,\n" + 
				"              \"total_score\":\"\",\n" + 
				"              \"answer_parsing\":"+answer_parsing+",\n" + 
				"              \"passing_score\":60,\n" + 
				"              \"cut_screen_count\":-1,\n" + 
				"              \"re_exam_rule\":"+re_exam_rule+",\n" + 
				"              \"re_exam_number\":"+re_exam_number+",\n" + 
				"              \"score_rule\":0,\n" + 
				"              \"view_rule\":1,\n" + 
				"              \"proportional\":[\n" + 
				"              ]\n" + 
				"            }\n" + 
				"          }]\n" + 
				"      }]\n" + 
				"  },\n" + 
				"  \"access_token\":\""+token+"\"\n" + 
				"}\n" + 
				"").send().body();
	
		 StudyProjectNewBusinesss.saveSettingsDraft(id);
		 
		return HttpRequest.post(save_url).query("access_token", token).data("{\n" + 
		 		"  \"save_step\":\"settings\",\n" + 
		 		"  \"id\":\""+id+"\",\n" + 
		 		"  \"settings\":{\n" + 
		 		"    \"study_time_limit\":\"30\",\n" + 
		 		"    \"face_recognition\":\"off\",\n" + 
		 		"    \"progress\":0,\n" + 
		 		"    \"times\":1,\n" + 
		 		"    \"operation_times\":\"\",\n" + 
		 		"    \"finish_evaluation\":false,\n" + 
		 		"    \"certificate_id\":\"\",\n" + 
		 		"    \"is_get_score\":true,\n" + 
		 		"    \"study_score\":{\n" + 
		 		"      \"finish_score\":0,\n" + 
		 		"      \"unfinish_score\":0\n" + 
		 		"    },\n" + 
		 		"    \"is_all\":3,\n" + 
		 		"    \"user_ids\":\"\",\n" + 
		 		"    \"department_ids\":\"\",\n" + 
		 		"    \"group_ids\":\"\",\n" + 
		 		"    \"post_ids\":\"\",\n" + 
		 		"    \"supervisor_ids\":[\n" + 
		 		"      \""+userId+"\"],\n" + 
		 		"    \"supervisor_paper\":true,\n" + 
		 		"    \"authority_range\":false,\n" + 
		 		"    \"supervisor_department_ids\":[\n" + 
		 		"    ],\n" + 
		 		"    \"enroll_audit\":\""+enroll_audit+"\",\n" + 
		 		"    \"enroll_limit\":\""+enroll_limit+"\",\n" + 
		 		"    \"limit_count\":"+limit_count+",\n" + 
		 		"    \"open_evaluation_result\":false,\n" + 
		 		"    \"is_free\":true,\n" + 
		 		"    \"official_price\":\"\",\n" + 
		 		"    \"preferential_price\":\"\",\n" + 
		 		"    \"open_learning_group\":\"false\"\n" + 
		 		"  },\n" + 
		 		"  \"access_token\":\"4660a678b3ffedc7ce17c4e32606d55e\"\n" + 
		 		"}\n" + 
		 		"").send().body();
		 
	}
	
}
