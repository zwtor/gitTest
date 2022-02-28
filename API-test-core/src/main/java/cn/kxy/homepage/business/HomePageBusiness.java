package cn.kxy.homepage.business;

import cn.kxy.base.business.EnterpriseDataUrl;
import cn.kxy.base.business.TokenData;
import com.lazy.assured.utils.GetRequestTools;
import com.lazy.httpclient.utils.HttpRequest;

public class HomePageBusiness {
	public static String token = TokenData.getMangerToken();
	public static String platform_url = EnterpriseDataUrl.getPlatformUrl();
	public static String enterprise_url = EnterpriseDataUrl.getEnterpriseUrl();
	
	public static String enterpriseMonthList_url = platform_url + "index/param/platform/enterpriseMonthList";
	public static String enterpriseWeekList_url = platform_url + "index/param/platform/enterpriseWeekList";
	public static String enterpriseUserWeekList_url = platform_url + "index/param/platform/enterpriseUserWeekList";
	public static String bizCourseWeekList_url = platform_url + "index/param/bizCourseWeekList";
	public static String bizOrderWeekList_url = platform_url + "index/param/bizOrderWeekList";
	
	public static String enterpriseUserMonthList_url = platform_url + "index/param/platform/enterpriseUserMonthList";
	
	public static String bizCourseMonthList_url = platform_url + "index/param/bizCourseMonthList";
	
	public static String bizOrderMonthList_url = platform_url + "index/param/bizOrderMonthList";
	public static String scanCountOrderList_url = platform_url + "index/param/course/scanCountOrderList";
	
	public static String playCountOrderList_url = platform_url + "index/param/course/playCountOrderList";
	public static String industry_url = platform_url + "index/param/industry/orderList";
	
	public static String authTypeList_url =platform_url + "index/param/authTypeList";
	
	public static String position_url =platform_url + "index/param/position/orderList";
	
	public static String data_pic_url = enterprise_url + "v2/home_page/data_pic";
	
	public static String queryDataPic() {
		return HttpRequest.get(data_pic_url).query("access_token", token).send().body();
	}
	
	/**   
	 * @Title: queryPositionList   
	 * @Description: TODO 查看开通职位分布
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */  
	public static String queryPositionList() {
		return HttpRequest.get(position_url).query("access_token", token).send().body();
	}
	/**   
	 * @Title: queryIndustryList   
	 * @Description: TODO 查看企业行业分布
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */  
	public static String queryIndustryList() {
		return HttpRequest.get(industry_url).query("access_token", token).send().body();
	}
	
	/**   
	 * @Title: queryAuthTypeList   
	 * @Description: TODO  
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */  
	public static String queryAuthTypeList() {
		return GetRequestTools.RequestQueryParamsByGet("access_token", token,authTypeList_url);
	}
	
	/**   
	 * @Title: queryPlayCountOrderList   
	 * @Description: TODO 查看课程播放排行榜
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */  
	public static String queryPlayCountOrderList() {
		return HttpRequest.get(playCountOrderList_url).query("access_token", token).send().body();
	}
	/**   
	 * @Title: queryscanCountOrderList   
	 * @Description: TODO 查看课程浏览排行榜
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */  
	public static String queryscanCountOrderList() {
		return HttpRequest.get(scanCountOrderList_url).query("access_token", token).send().body();
	}
	
	/**   
	 * @Title: queryBizOrderMonthList   
	 * @Description: TODO  查看每月的订单数
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */  
	public static String queryBizOrderMonthList() {
		return HttpRequest.get(bizOrderMonthList_url).query("access_token", token).send().body();
	}
	
	
	
	/**   
	 * @Title: queryBizCourseMonthList   
	 * @Description: TODO  查看每月课程数
	 * @param: @return       
	 * @return: String      
	 * @throws   
	 */  
	public static String queryBizCourseMonthList() {
		return HttpRequest.get(bizCourseMonthList_url).query("access_token", token).send().body();
	}
	
	/**   
	 * @Title: queryEnterpriseUserMonthList   
	 * @Description: TODO  查看每月用户数
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */  
	public static String queryEnterpriseUserMonthList() {
		return HttpRequest.get(enterpriseUserMonthList_url).query("access_token", token).send().body();
	}
	
	/**   
	 * @Title: queryBizOrderWeekList_urlList   
	 * @Description: TODO 查看每周订单数
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */  
	public static String queryBizOrderWeekList() {
		return HttpRequest.get(bizOrderWeekList_url).query("access_token", token).send().body();
	}
	/**   
	 * @Title: queryBizCourseWeekListList  
	 * @Description: TODO 查看每周发布的课程数
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */  
	public static String queryBizCourseWeekList() {
		return HttpRequest.get(bizCourseWeekList_url).query("access_token", token).send().body();
	}
	/**   
	 * @Title: queryEnterpriseUserWeekList
	 * @Description: TODO 查看每周企业用户数
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */  
	public static String queryEnterpriseUserWeekList() {
		return HttpRequest.get(enterpriseUserWeekList_url).query("access_token", token).send().body();
	}
	/**   
	 * @Title: queryEnterpriseWeekList   
	 * @Description: TODO 查看每周企业数
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */  
	public static String queryEnterpriseWeekList() {
		return HttpRequest.get(enterpriseWeekList_url).query("access_token", token).send().body();
	}
	
	
	
	/**   
	 * @Title: queryEnterpriseMonthList   
	 * @Description: TODO 查看每月企业用户数
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */  
	public static String queryEnterpriseMonthList() {
		return HttpRequest.get(enterpriseMonthList_url).query("access_token", token).send().body();
	}
	
}
