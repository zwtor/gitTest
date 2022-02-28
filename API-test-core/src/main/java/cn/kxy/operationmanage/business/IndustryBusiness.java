package cn.kxy.operationmanage.business;

import cn.kxy.base.business.EnterpriseDataUrl;
import cn.kxy.base.business.TokenData;
import com.lazy.httpclient.utils.HttpRequest;

public class IndustryBusiness {
	public static String token = TokenData.getMangerToken();
	public static String platform_url = EnterpriseDataUrl.getPlatformUrl();
	
	public static String classify_url =platform_url + "admin/post/getList";
	
	/**   
	 * @Title: queryCourseClassify   
	 * @Description: TODO
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */  
	public static String queryIndustryClassify() {
		return HttpRequest.get(classify_url).query("access_token",token).query("parentId", "0")
				.send().body();
	}
}
