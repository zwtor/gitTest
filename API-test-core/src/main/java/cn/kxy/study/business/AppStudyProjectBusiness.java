package cn.kxy.study.business;

import cn.kxy.base.business.EnterpriseData;
import cn.kxy.base.business.EnterpriseDataUrl;
import cn.kxy.base.business.TokenData;
import cn.kxy.setting.bussiness.UserBusiness;
import com.lazy.httpclient.utils.HttpRequest;

public class AppStudyProjectBusiness {
	public static String token = TokenData.getMangerToken();
	public static String enterpriseId = EnterpriseData.getEnterpriseId();
	public static String enterpriseUrl = EnterpriseDataUrl.getEnterpriseUrl();
	public static String userId = UserBusiness.getUserId();
	public static String add_url = enterpriseUrl + "v2/"+enterpriseId+"/study_projects/add";
	
	public static String save_url = enterpriseUrl + "v2/"+enterpriseId+"/users/"+userId+"/study_records/save";
	
	public static String course_url (String id) {
		return enterpriseUrl + "v2/"+enterpriseId+"/users/"+userId+"/study_projects/"+id+"/query";
	}
	
	public static String queryCourseInfo(String id) {
		return HttpRequest.get(course_url(id)).query("user_id",userId).query("enterprise_id",enterpriseId).query("access_token",token)
				.send().body();
	}
	
	public static String saveProess(String plan_id,String course_id,String action_type) {
		return HttpRequest.post(save_url).query("user_id",userId).query("enterprise_id",enterpriseId).query("access_token",token)
				.data("{\r\n" + 
						"	\"action_type\": \""+action_type+"\",\r\n" + 
						"	\"plan_id\": \""+plan_id+"\",\r\n" + 
						"	\"course_id\": \""+course_id+"\",\r\n" + 
						"	\"resource_id\": \""+course_id+"\",\r\n" + 
						"	\"plan_type\": \"project\"\r\n" + 
						"}").send().body();
	}
	
	public static String addAppStudyProject(String title,String course_id) {
		return HttpRequest.post(add_url).query("user_id",userId).query("enterprise_id",enterpriseId).query("access_token",token)
				.data("{\r\n" + 
						"	\"title\": \""+title+"\",\r\n" + 
						"	\"cover\": \"https://oss.coolcollege.cn/1789917624419880960.png\",\r\n" + 
						"	\"base_cover\": \"https://oss.coolcollege.cn/1789917624419880960.png\",\r\n" + 
						"	\"cover_type\": 1,\r\n" + 
						"	\"classify\": \"\",\r\n" + 
						"	\"is_get_score\": true,\r\n" + 
						"	\"certificate_id\": \"\",\r\n" + 
						"	\"study_score\": {\r\n" + 
						"		\"finish_score\": 0,\r\n" + 
						"		\"unfinish_score\": 0\r\n" + 
						"	},\r\n" + 
						"	\"stages\": [{\r\n" + 
						"		\"content\": \"\",\r\n" + 
						"		\"stage_name\": \"step one\",\r\n" + 
						"		\"is_order\": false,\r\n" + 
						"		\"sort\": 1,\r\n" + 
						"		\"resources\": [{\r\n" + 
						"			\"course_id\": \""+course_id+"\",\r\n" + 
						"			\"sort\": 0,\r\n" + 
						"			\"type\": 3\r\n" + 
						"		}]\r\n" + 
						"	}],\r\n" + 
						"	\"is_all\": 1,\r\n" + 
						"	\"user_ids\": \"\",\r\n" + 
						"	\"department_ids\": \"\",\r\n" + 
						"	\"group_ids\": \"\",\r\n" + 
						"	\"post_ids\": \"\",\r\n" + 
						"	\"ding_img_url\": \"\",\r\n" + 
						"	\"stagePass\": false,\r\n" + 
						"	\"enroll_audit\": \"un_need\",\r\n" + 
						"	\"enroll_limit\": false,\r\n" + 
						"	\"limit_count\": 1,\r\n" + 
						"	\"progress\": 100,\r\n" + 
						"	\"operationTimes\": \"\",\r\n" + 
						"	\"times\": \"\",\r\n" + 
						"	\"source_type\": \"app\"\r\n" + 
						"}").send().body();
	}
}
