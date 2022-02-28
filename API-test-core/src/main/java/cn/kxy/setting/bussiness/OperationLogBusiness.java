package cn.kxy.setting.bussiness;

import cn.kxy.base.business.EnterpriseData;
import cn.kxy.base.business.EnterpriseDataUrl;
import cn.kxy.base.business.TokenData;
import com.lazy.assured.utils.GetRequestTools;

public class OperationLogBusiness {
	
	public static String token = TokenData.getMangerToken();
	public static String feedUrl= EnterpriseDataUrl.getFeedUrl();
	public static String enterpriseId = EnterpriseData.getEnterpriseId();
	public static String event_types_url =feedUrl + "v2/"+enterpriseId+"/audits/event_types";
	public static String log_url = feedUrl +"v2/"+enterpriseId+"/audits";
	
	/**   
	 * @Title: queryEventTypes   
	 * @Description: TODO(查看日志类型)   
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String queryEventTypes() {
		return GetRequestTools.RequestQueryParamsByGet("access_token", token, event_types_url);
	}
	/**   
	 * @Title: queryLogDetail   
	 * @Description: TODO(查看日志详情列表)   
	 * @param: @param start_time
	 * @param: @param end_time
	 * @param: @param event_type
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String queryLogDetail(String start_time,String end_time,String user_name,String event_type) {
		return  GetRequestTools.RequestQueryParamsByGet("event_type",event_type,"user_name",user_name,"end_time",end_time,"access_token", token, "page_size", "20","start_time",start_time, log_url);
	}
	
}
