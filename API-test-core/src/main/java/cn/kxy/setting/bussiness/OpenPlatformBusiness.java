package cn.kxy.setting.bussiness;

import cn.kxy.base.business.EnterpriseData;
import cn.kxy.base.business.EnterpriseDataUrl;
import cn.kxy.base.business.TokenData;
import com.lazy.httpclient.utils.HttpRequest;

public class OpenPlatformBusiness {
	public static String token = TokenData.getMangerToken();
	public static String openplatform_url = EnterpriseDataUrl.getOpenPlatFormUrl();
	public static String enterprise_id = EnterpriseData.getEnterpriseId();
	public static String enterprise_url = EnterpriseDataUrl.getEnterpriseUrl();
	
	public static String queryApikeyUrl ="https://openapi.coolcollege.cn/" + "api/v2/"+enterprise_id+"/apikey";
	public static String getAuthorizeListUrl = enterprise_url + "v1/wechatPlatform/getAuthorizeList";
	
	public static String event_back_detail_url = enterprise_url + "v2/951057547274620933/event_back/detail";
	
	public static String event_back_event_type_url = enterprise_url + "v2/"+enterprise_id+"/event_back/event_type";
	
	
	public static String queryEventBackType() {
		return HttpRequest.get(event_back_event_type_url).query("access_token",token).send().body();
	}
	
	public static String queryEventBackDetail() {
		return HttpRequest.get(event_back_detail_url).query("access_token",token).send().body();
	}
	
	public static String queryAuthorizeList () {
		return HttpRequest.get(getAuthorizeListUrl).query("access_token",token).query("enterprise_id",enterprise_id).
				query("app_id","cool").send().body();
	}
	
	public static String queryApiKey() {
		return HttpRequest.get(queryApikeyUrl).query("access_token",token).send().body();
	}
	
}
