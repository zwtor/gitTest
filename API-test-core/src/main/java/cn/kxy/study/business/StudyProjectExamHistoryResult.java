package cn.kxy.study.business;

import cn.kxy.base.business.EnterpriseData;
import cn.kxy.base.business.EnterpriseDataUrl;
import cn.kxy.base.business.ExamDataUrl;
import cn.kxy.base.business.TokenData;
import cn.kxy.setting.bussiness.ClassificationBusines;
import cn.kxy.setting.bussiness.UserBusiness;
import com.alibaba.fastjson.JSONPath;
import com.lazy.httpclient.utils.HttpRequest;

public class StudyProjectExamHistoryResult {
	public static String token = TokenData.getMangerToken();
	public static String enterprise_id = EnterpriseData.getEnterpriseId();
	public static String enterprise_url = EnterpriseDataUrl.getEnterpriseUrl();
	public static String exam_url = ExamDataUrl.getNewExamUrl();
	public static String user_id = UserBusiness.getUserId();
	public static String class_id = ClassificationBusines.getPrimaryId();
	public static String add_study_project_task_url =enterprise_url + "v2/"+enterprise_id+"/study_projects/add";
	public static String save_url = enterprise_url + "v2/"+enterprise_id+"/study_projects/save";
	
	public static String addHistoryResultProject(String title ,String paper_id,String score_rule) {
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
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(id);
		String body = HttpRequest.post(save_url).query("access_token", token).data("{\r\n" + 
				"  \"save_step\":\"stage_content\",\r\n" + 
				"  \"id\":\""+id+"\",\r\n" + 
				"  \"stage_content\":{\r\n" + 
				"    \"stage_pass\":\"false\",\r\n" + 
				"    \"stages\":[\r\n" + 
				"      {\r\n" + 
				"        \"content\":\"\",\r\n" + 
				"        \"stage_name\":\"阶段1\",\r\n" + 
				"        \"is_order\":false,\r\n" + 
				"        \"sort\":1,\r\n" + 
				"        \"resources\":[\r\n" + 
				"          {\r\n" + 
				"            \"type\":6,\r\n" + 
				"            \"sort\":0,\r\n" + 
				"            \"course_id\":\"\",\r\n" + 
				"            \"exam\":{\r\n" + 
				"              \"title\":\"SettledPaper\",\r\n" + 
				"              \"cheat_flag\":0,\r\n" + 
				"              \"exam_duration\":45,\r\n" + 
				"              \"face_recognition\":\"off\",\r\n" + 
				"              \"summary\":\"this is a summary\",\r\n" + 
				"              \"mark_type\":1,\r\n" + 
				"              \"paper_id\":\""+paper_id+"\",\r\n" + 
				"              \"pass_line\":60,\r\n" + 
				"              \"question_banks\":[\r\n" + 
				"              ],\r\n" + 
				"              \"question_mode\":1,\r\n" + 
				"              \"show_knowledge\":\"show\",\r\n" + 
				"              \"repeat_exam\":true,\r\n" + 
				"              \"multiple_count\":\"\",\r\n" + 
				"              \"multiple_unit_score\":\"\",\r\n" + 
				"              \"fill_blank_count\":\"\",\r\n" + 
				"              \"fill_blank_unit_score\":\"\",\r\n" + 
				"              \"fill_blank_type\":1,\r\n" + 
				"              \"short_answer_count\":\"\",\r\n" + 
				"              \"short_answer_unit_score\":\"\",\r\n" + 
				"              \"short_answer_type\":1,\r\n" + 
				"              \"single_count\":\"\",\r\n" + 
				"              \"single_unit_score\":\"\",\r\n" + 
				"              \"true_or_false_count\":\"\",\r\n" + 
				"              \"true_or_false_unit_score\":\"\",\r\n" + 
				"              \"total_score\":100,\r\n" + 
				"              \"answer_parsing\":5,\r\n" + 
				"              \"passing_score\":\"60.0\",\r\n" + 
				"              \"cut_screen_count\":-1,\r\n" + 
				"              \"re_exam_rule\":1,\r\n" + 
				"              \"re_exam_number\":0,\r\n" + 
				"              \"score_rule\":"+score_rule+",\r\n" + 
				"              \"view_rule\":1,\r\n" + 
				"              \"proportional\":[\r\n" + 
				"              ]\r\n" + 
				"            }\r\n" + 
				"          }]\r\n" + 
				"      }]\r\n" + 
				"  },\r\n" + 
				"  \"access_token\":\"4660a678b3ffedc7ce17c4e32606d55e\"\r\n" + 
				"}\r\n" + 
				"").send().body();
		
		 String saveSettingsDraft = StudyProjectNewBusinesss.saveSettingsDraft(id);
		 return StudyProjectNewBusinesss.saveSetting(id);
	}
	
}
