package cn.kxy.operationmanage.business;

import cn.kxy.base.business.EnterpriseDataUrl;
import cn.kxy.base.business.TokenData;
import com.lazy.httpclient.utils.HttpRequest;

public class CompanyOperationBusiness {

	public static String token = TokenData.getMangerToken();
	public static String platform_url = EnterpriseDataUrl.getPlatformUrl();
	
	public static String getPerson_url = platform_url + "sys/parameter/getPerson";
	
	public static String getSelectListWithAll_url = platform_url + "followRecord/getSelectListWithAll";
	
	public static String getIndustryList_url = platform_url + "admin/enterprise/getIndustryList";
	
	public static String getEnterpriseList_url = platform_url + "admin/enterprise/getList";
	
	/**   
	 * @Title: getEnterpriseList   
	 * @Description: TODO  获取企业列表
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */  
	public static String getEnterpriseList() {
		return HttpRequest.get(getEnterpriseList_url).query("access_token", token).query("exportCount", "20")
				.query("sortName", "createTime").query("sortOrder", "desc").query("pageNumber", "1").
				query("pageSize", "20").query("flag","0").send().body();
	}
	
	
	/**   
	 * @Title: getIndustryList   
	 * @Description: TODO  获取行业筛选的列表
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */  
	public static String getIndustryList() {
		return HttpRequest.get(getIndustryList_url).query("access_token",token).send().body();
	}
	
	/**   
	 * @Title: getPerson   
	 * @Description: TODO  (获取个人信息)
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */  
	public static String getPerson() {
		return HttpRequest.get(getPerson_url).query("access_token", token).send().body();
	}
	
	/**   
	 * @Title: aString   
	 * @Description: TODO  查看项目阶段下拉框的内容
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */  
	public static String getSelectListWithAll () {
		return HttpRequest.get(getSelectListWithAll_url).query("access_token", token).send().body();
	}
	
	
}
