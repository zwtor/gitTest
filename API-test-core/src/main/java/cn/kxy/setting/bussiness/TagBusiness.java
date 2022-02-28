package cn.kxy.setting.bussiness;

import cn.kxy.base.business.EnterpriseData;
import cn.kxy.base.business.EnterpriseDataUrl;
import cn.kxy.base.business.TokenData;
import com.lazy.httpclient.utils.HttpRequest;

public class TagBusiness {
	public static String token = TokenData.getMangerToken();
	public static String enterprise_id = EnterpriseData.getEnterpriseId();
	public static String enterprise_url = EnterpriseDataUrl.getEnterpriseUrl();
	
	public static String hot_url = enterprise_url + "v2/"+enterprise_id+"/search/tag/hot";
	
	public static String tag_types_url =enterprise_url + "v2/"+enterprise_id+"/search/tag/types";
	
	public static String type_search_url  =enterprise_url + "v2/"+enterprise_id+"/search";
	
	public static String searchType (String keyword) {
		return	HttpRequest.get(type_search_url).query("access_token",token).query("keyword", keyword).send().body();
	}
	
	public static String queryTagsTypes (String type) {
		return	HttpRequest.get(tag_types_url).query("access_token",token).send().body();
	}
	
	public static String queryHotTags (String type) {
		return	HttpRequest.get(hot_url).query("access_token",token).query("type",type).query("num","5").send().body();
	}
}
