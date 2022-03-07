package cn.kxy.group.a.business;

/**
 * @author wenlingzhi
 *2021年5月8日
 */

import cn.kxy.base.business.EnterpriseData;
import cn.kxy.base.business.EnterpriseDataUrl;
import cn.kxy.base.business.TokenData;
import cn.kxy.setting.bussiness.UserBusiness;
import com.lazy.httpclient.utils.HttpRequest;

public class HourRankingBusiness {

	public static String enterprise_url = EnterpriseDataUrl.getEnterpriseUrl();
	public static String token = TokenData.getMangerToken();
	public static String enterpriseId = EnterpriseData.getEnterpriseId();
	public static String user_id = UserBusiness.getUserId();
	public static String query_switchesId_url = enterprise_url + "v2/"+enterpriseId+"/settings/";
	public static String switch_setting_url (String switch_id) {
		return enterprise_url + "v2/"+enterpriseId+"/switches/"+switch_id+"/setting/";
	}
	public static String user_class_hour_ranking_url = enterprise_url + "v2/"+enterpriseId+"/user_class_hour_ranking/";
	public static String user_departments_url = enterprise_url + "v2/"+enterpriseId+"/departments/score/mobile/";
	public static String dept_class_hour_ranking_url = enterprise_url + "v2/"+enterpriseId+"/dept_class_hour_ranking/";
	public static String getScoreRecord_url = enterprise_url + "/score/getScoreRecord/";
	public static String scoreToday_url = enterprise_url + "v2/"+enterpriseId+"/users/"+user_id+"/scores/";

    
    /**   
	 * @Title: QuerySwitchesId  
	 * @Description: TODO  获取学时排行榜按钮id
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String QuerySwitchesId(){	
		return HttpRequest.get(query_switchesId_url).query("access_token", token)
				.send().body();
	}
	
	/**   
	 * @Title: SwitchSetting  
	 * @Description: TODO  学时排行榜按钮开关
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String SwitchSetting(String switch_id,String type,String switch_status,String actions){			
//		String data = "{" + 
//				"      \"type\": \""+type+"\",\r\n" + 
//				"      \"switch_status\": \""+switch_status+"\",\r\n" + 
//				"      \"actions\": "+actions+",\r\n" + 
//				"      \"access_token\":\""+token+"\"\r\n" + 
//				"}\r\n" + 
//				"";
//		//JSONObject o = JSONObject.parseObject(data);
//		return HttpRequest.post(switch_setting_url(switch_id)).query("access_token", token).data(data)
//				.send().body();
		return HttpRequest.post(switch_setting_url(switch_id)).query("access_token", token).data("{\r\n" + 
				"      \"type\": \""+type+"\",\r\n" + 
				"      \"switch_status\": \""+switch_status+"\",\r\n" + 
				"      \"actions\": "+actions+",\r\n" + 
				"      \"access_token\":\""+token+"\"\r\n" + 
				"}\r\n" + 
				"")
				.send().body();	
	}
	
	/**   
	 * @Title: UserClassHourRanking  
	 * @Description: TODO  移动端-我的页面检验学时入口
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String UserClassHourRanking(){	
		return HttpRequest.get(user_class_hour_ranking_url).query("access_token", token).query("user_id",user_id)
				.send().body();
	}
	
	
	/**   
	 * @Title: MyUserClassHourRanking  
	 * @Description: TODO  移动端-我的学时页-我的学时和排名
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String MyUserClassHourRanking(String dept_id){	
		return HttpRequest.get(user_class_hour_ranking_url).query("access_token", token).query("dept_id",dept_id)
				.query("user_id",user_id)
				.send().body();
	}
	
	
	/**   
	 * @Title: UserDepartments  
	 * @Description: TODO  移动端-获取当前用户所在所有部门
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String UserDepartments(){	
		return HttpRequest.get(user_departments_url).query("access_token", token).query("user_id",user_id)
				.send().body();
	}
	
	
	/**   
	 * @Title: DeptClassHourRanking  
	 * @Description: TODO  移动端-我的学时页-部门排行
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String DeptClassHourRanking(String dept_id){	
		return HttpRequest.get(dept_class_hour_ranking_url).query("access_token", token).query("dept_id",dept_id)
				.query("user_id",user_id)
				.send().body();
	}
		
	/**   
	 * @Title: getScoreRecord  
	 * @Description: TODO  移动端-我的学分-学分详情
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String GetScoreRecord(){	
		return HttpRequest.get(getScoreRecord_url).query("access_token", token).query("user_id",user_id)
				.send().body();
	}
	
	/**   
	 * @Title: ScoreToday  
	 * @Description: TODO  移动端-我的学分-今日学分
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String ScoreToday(){	
		return HttpRequest.get(scoreToday_url).query("access_token", token).query("user_id",user_id)
				.send().body();
	}
	
	
}
