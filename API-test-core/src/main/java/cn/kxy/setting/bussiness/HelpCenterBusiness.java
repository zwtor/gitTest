package cn.kxy.setting.bussiness;

import cn.kxy.base.business.EnterpriseData;
import cn.kxy.base.business.EnterpriseDataUrl;
import cn.kxy.base.business.TokenData;
import com.lazy.httpclient.utils.HttpRequest;

public class HelpCenterBusiness {
	public static String token = TokenData.getMangerToken();
	public static String enterprise_id = EnterpriseData.getEnterpriseId();
	public static String cmdb_url = EnterpriseDataUrl.getCmdbUrl();
	public static String enterprise_url = EnterpriseDataUrl.getEnterpriseUrl();
	
	
	public static String help_url = cmdb_url + "v2/crm/get_help_content";
	
	public static String change_help_status_url=enterprise_url + "v2/"+enterprise_id+"/users/"+UserBusiness.getUserId()+"/help/config";
	
	public static String  changeHelpStatus(String help_status) {
		return HttpRequest.post(change_help_status_url).query("access_token", token).data("{\"help_status\":\""+help_status+"\",\"access_token\":\""+token+"\"}").send().body();
	}
	
	public static String getHelpContent (String key) {
		return HttpRequest.get(help_url).query("access_token", token).query("key", key).send().body();
	}
	
}
