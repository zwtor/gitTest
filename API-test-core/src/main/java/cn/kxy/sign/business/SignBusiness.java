package cn.kxy.sign.business;

import cn.kxy.base.business.EnterpriseData;
import cn.kxy.base.business.EnterpriseDataUrl;
import cn.kxy.base.business.TokenData;
import cn.kxy.setting.bussiness.UserBusiness;
import com.lazy.httpclient.utils.HttpRequest;

public class SignBusiness {

	public static String enterprise_url = EnterpriseDataUrl.getEnterpriseUrl();
	public static String token = TokenData.getMangerToken();
	public static String enterpriseId = EnterpriseData.getEnterpriseId();
	
	public static String user_id = UserBusiness.getUserId();
	
	public static String sign_url = enterprise_url + "v2/enterprises/"+enterpriseId+"/sign/list";
	
	public static String sign_records_url = enterprise_url + "v2/enterprises/"+enterpriseId+"/users/"+user_id+"/sign/records";
	
	public static String sign_pop_url = enterprise_url + "v2/enterprises/"+enterpriseId+"/users/"+user_id+"/sign/pop";
	
	
	public static String sign = enterprise_url + "v2/enterprises/"+enterpriseId+"/users/"+user_id+"/sign/today";
	
	public static String download_url =enterprise_url + "v2/enterprises/"+enterpriseId+"/sign/export";
	
	public static String sign() {
		return HttpRequest.post(sign).contentType("application/json;charset=UTF-8").query("access_token", token).data("{\"access_token\":\""+token+"\"}").send().body();
	}
	
	/**   
	 * @Title: querySignList   
	 * @Description: TODO  移动端查看签到列表
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */  
	public static String querySignList() {
		return HttpRequest.get(sign_url).query("access_token", token).query("enterprise_id", enterpriseId).send().body();
	}
	
	/**   
	 * @Title: querySignList   
	 * @Description: TODO  查看web端签到记录列表
	 * @param: @param start_time 
	 * @param: @param end_time
	 * @param: @param name
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */  
	public static String querySignList(String start_time,String end_time,String name) {
		return HttpRequest.get(sign_url).query("access_token", token).query("start_time", start_time).query("end_time", end_time).
				query("enterprise_id", enterpriseId).send().body();
	}
	
	/**   
	 * @Title: querySignRecords   
	 * @Description: TODO  查看签到记录
	 * @param: @param year_month
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */  
	public static String querySignRecords(String year_month) {
		return  HttpRequest.get(sign_records_url).query("access_token", token).query("enterprise_id", enterpriseId)
				.query("user_id", user_id).query("year_month",year_month).send().body();
	}
	
	/**   
	 * @Title: querySignPop   
	 * @Description: TODO   查看签到弹窗
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */  
	public static String querySignPop() {
		
		return HttpRequest.get(sign_pop_url).query("access_token", token).query("client", "mobile").
				query("enterprise_id", enterpriseId).query("user_id", user_id).send().body();
	}
	
	/**   
	 * @Title: downloadSign   
	 * @Description: TODO  下载签到记录
	 * @param: @param start_time
	 * @param: @param end_time
	 * @param: @param name
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */  
	public static String downloadSign(String start_time,String end_time,String name) {
		return  HttpRequest.get(download_url).query("start_time",start_time).query("end_time", end_time).query("access_token", token)
				.send().body();
	}
}
