package cn.kxy.setting.bussiness;

import cn.kxy.base.business.EnterpriseData;
import cn.kxy.base.business.EnterpriseDataUrl;
import cn.kxy.base.business.TokenData;
import com.lazy.httpclient.utils.HttpRequest;

public class JurisdictionBusiness {
	public static String token = TokenData.getMangerToken();
	public static String enterprise_id = EnterpriseData.getEnterpriseId();
	public static String enterprise_url= EnterpriseDataUrl.getEnterpriseUrl();
	public static String user_id = UserBusiness.getUserId();
	public static String user_name = UserBusiness.getUsername();
	
	public static String jurisdiction_setting_url = enterprise_url + "v2/"+enterprise_id+"/settings/jurisdiction_setting";
	
	public static String authority_ranges_url = enterprise_url + "v2/"+enterprise_id+"/authority_ranges";
	
	/**   
	 * @Title: queryJurisdictionSetting   
	 * @Description: TODO  查询管辖范围的设置
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */  
	public static String queryJurisdictionSetting () {
		return HttpRequest.get(jurisdiction_setting_url).query("access_token", token).send().body();
	}
	
	/**   
	 * @Title: queryAuthorityRanges   
	 * @Description: TODO  查询管辖范围
	 * @param: @param user_id
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */  
	public static String queryAuthorityRanges (String user_id) {
		return HttpRequest.get(authority_ranges_url).query("user_id",user_id).query("access_token",token).send().body();
	}
	
	/**   
	 * @Title: saveAuthorityRanges   
	 * @Description: TODO   设置管辖范围
	 * @param: @param source_user_id
	 * @param: @param range_type
	 * @param: @param goal_user_id
	 * @param: @param department_ids
	 * @param: @param position_ids
	 * @param: @param user_group_ids
	 * @param: @param project_view_ranges
	 * @param: @param authority_tasks
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */  
	public static String saveAuthorityRanges(String source_user_id,String range_type,String goal_user_id,String department_ids,String position_ids,String user_group_ids,
			String project_view_ranges,String authority_tasks) {
		return HttpRequest.post(authority_ranges_url).query("access_token", token).contentType("application/json;charset=UTF-8").data("{\r\n" + 
				"	\"user_ids\": [\r\n" + 
				"		\""+source_user_id+"\"\r\n" + 
				"	],\r\n" + 
				"	\"range_type\": \""+range_type+"\",\r\n" + 
				"	\"custom_range\": {\r\n" + 
				"		\"user_ids\": [\r\n" + 
				"			\""+goal_user_id+"\"\r\n" + 
				"		],\r\n" + 
				"		\"department_ids\": [\r\n" + 
				"			\""+department_ids+"\"\r\n" + 
				"		],\r\n" + 
				"		\"position_ids\": [\r\n" + 
				"			\""+position_ids+"\"\r\n" + 
				"		],\r\n" + 
				"		\"user_group_ids\": [\r\n" + 
				"			\""+user_group_ids+"\"\r\n" + 
				"		]\r\n" + 
				"	},\r\n" + 
				"	\"project_view_ranges\": [\r\n" + 
				"		\""+project_view_ranges+"\"\r\n" + 
				"	],\r\n" + 
				"	\"authority_tasks\": [\r\n" + 
				"		\""+authority_tasks+"\"\r\n" + 
				"	],\r\n" + 
				"	\"access_token\": \""+token+"\"\r\n" + 
				"}").send().body();
	}
}
