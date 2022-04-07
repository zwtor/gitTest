package cn.kxy.setting.bussiness;

import cn.kxy.base.business.EnterpriseData;
import cn.kxy.base.business.EnterpriseDataUrl;
import cn.kxy.base.business.TokenData;
import com.lazy.httpclient.utils.HttpRequest;

public class LoginPageSettingBusiness {

	public static String token = TokenData.getMangerToken();
	public static String cmdb_url= EnterpriseDataUrl.getCmdbUrl();
	public static String enterprise_url = EnterpriseDataUrl.getEnterpriseUrl();
	
	public static String enterprise_id = EnterpriseData.getEnterpriseId();
	
	public static String pcandmobiledomain_url = cmdb_url + "v2/enterprises/"+enterprise_id+"/enterprise_config/pcandmobiledomain";
	
	public static String query_domain_url =  cmdb_url + "v2/enterprises/"+enterprise_id+"/enterprise_config/domain/query";
	
	public static String query_login_image_url = cmdb_url + "v2/enterprises/"+enterprise_id+"/enterprise_config/login/image/query";
	
	public static String login_method_url = cmdb_url + "v2/enterprises/"+enterprise_id+"/enterprise_login_config/get";
	
	public static String queryCmsPage () {
		return HttpRequest.get(EnterpriseDataUrl.getCmsUrl()+ "cms/v1/enterprises/cmsPage/"+enterprise_id+"/checkFeature").query("businessType","visitor").query("access_token",token).send().body();
	}
	
	public static String queryLoginMethod () {
		return HttpRequest.get(login_method_url).query("access_token",token).send().body();
	}
	
	public static String queryLoginImage () {
		return HttpRequest.get(query_login_image_url).query("access_token",token).send().body();
	}
	
	public static String queryDomain () {
		return HttpRequest.get(query_domain_url).query("access_token",token).send().body();
	}
	
	public static String queryPcandmobileDomain () {
		return HttpRequest.get(pcandmobiledomain_url).query("access_token",token).send().body();
	}
	
}
