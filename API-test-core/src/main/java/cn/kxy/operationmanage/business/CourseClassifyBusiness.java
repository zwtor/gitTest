package cn.kxy.operationmanage.business;

import cn.kxy.base.business.EnterpriseDataUrl;
import cn.kxy.base.business.TokenData;
import com.lazy.httpclient.utils.HttpRequest;

public class CourseClassifyBusiness {
	public static String token = TokenData.getMangerToken();
	public static String platform_url = EnterpriseDataUrl.getPlatformUrl();
	
	public static String classify_url =platform_url + "admin/classify/getList";
	
	/**   
	 * @Title: queryCourseClassify   
	 * @Description: TODO
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */  
	public static String queryCourseClassify() {
		return HttpRequest.get(classify_url).query("access_token",token).query("sortName", "id")
				.query("sortOrder", "desc").query("pageNumber", "1").query("pageSize", "20").send().body();
	}
}
