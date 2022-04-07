package cn.kxy.setting.bussiness;

import cn.kxy.base.business.EnterpriseData;
import cn.kxy.base.business.EnterpriseDataUrl;
import cn.kxy.base.business.TokenData;
import com.lazy.assured.utils.GetRequestTools;
import com.lazy.assured.utils.PostRequestTools;
import com.lazy.httpclient.utils.HttpRequest;

public class MobileSettingBusiness {
	public static String token = TokenData.getMangerToken();
	
	public static String cmdb_url= EnterpriseDataUrl.getCmdbUrl();
	public static String enterprise_url = EnterpriseDataUrl.getEnterpriseUrl();
	public static String enterprise_id = EnterpriseData.getEnterpriseId();
	public static String module_setting_url =cmdb_url + "v2/"+enterprise_id+"/home_page/module_setting";
	
	public static String startup_url =enterprise_url + "v2/mobile/startup/pages/get";
	public static String corp_id = "dingd421e1b6e491fa9c35c2f4657eb6378f";
	
	public static String set_startup_url =enterprise_url + "v2/"+enterprise_id+"/mobile/startup/pages/create";
	
	public static String colour_getting_url = enterprise_url + "v2/mobile/colour";
	
	public static String colour_setting_url = enterprise_url + "v2/"+enterprise_id+"/mobile/colour";
	
	public static String setColor (String color) {
		return PostRequestTools.RequestBodyByPost("{\r\n" + 
				"  \"colour\": \""+color+"\", \r\n" + 
				"  \"default_check\": 1, \r\n" + 
				"  \"access_token\": \""+token+"\"\r\n" + 
				"}", token, colour_setting_url);
	}
	
	/**   
	 * @Title: setMobileColor   
	 * @Description: TODO 设置移动端首页
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */  
	public static String getMobileColor() {
		return GetRequestTools.RequestQueryParamsByGet("corp_id",corp_id, "access_token",token, colour_getting_url);
	}
	
	/**   
	 * @Title: setStartup   
	 * @Description: TODO(设置启动页配置)   
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String setStartup(String colour,String url) {
		return PostRequestTools.RequestBodyByPost("{\r\n" + 
				"  \"colour\": \""+colour+"\", \r\n" + 
				"  \"url\": \""+url+"\", \r\n" + 
				"  \"access_token\": \""+token+"\"\r\n" + 
				"}", token, set_startup_url);
	}
	
	/**   
	 * @Title: getStartUpSetting   
	 * @Description: TODO(获取启动页配置)   
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String getStartUpSetting() {
		return GetRequestTools.RequestQueryParamsByGet("access_token", token, "corp_id", corp_id, startup_url);
	}
	
	/**   
	 * @Title: getModuleSetting   
	 * @Description: TODO(获取移动端首页设置)   
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String getModuleSetting() {
		return HttpRequest.get(module_setting_url).query("access_token", token).query("enterprise_id",enterprise_id).query("user_id",UserBusiness.getUserId()).send().body();
	}

	public static String queryCustom (String type) {
		return HttpRequest.get(module_setting_url).query( "access_token",token).query("department_id","1").query("type",type).send().body();
	}
	
	
}
