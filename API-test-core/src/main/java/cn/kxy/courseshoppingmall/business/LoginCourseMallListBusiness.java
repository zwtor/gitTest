package cn.kxy.courseshoppingmall.business;

import cn.kxy.base.business.EnterpriseData;
import cn.kxy.base.business.EnterpriseDataUrl;
import cn.kxy.base.business.TokenData;
import cn.kxy.setting.bussiness.UserBusiness;
import com.lazy.assured.utils.GetRequestTools;
import com.lazy.assured.utils.PostRequestTools;

public class LoginCourseMallListBusiness {
	public static String platformUrl=  EnterpriseDataUrl.getPlatformUrl();
	public static String token = TokenData.getMangerToken();
	public static String enterpriseId = EnterpriseData.getEnterpriseId();
	
	public static String userId = UserBusiness.getUserId();
	public static String banner_url = platformUrl + "store/banners";
	
	public static String getRecommendCoursePack_url = platformUrl + "store/getRecommendCoursePack";
	
	public static String popular_courses_url = platformUrl + "v2/popular_courses";
	
	public static String getHomeCourselistUrl = platformUrl + "biz/course/getHomeCourselist";
	
	public static String getPackInfoUrl = platformUrl + "biz/course/pack/getInfo";
	
	public static String relevantCourseUrl = platformUrl + "store/relevantCourse";
	
	public static String relatedCourseOrPackUrl = platformUrl + "biz/course/pack/relatedCourseOrPack";
	
	public static String teacherCourseUrl = platformUrl + "store/teacherCourse";
	
	public static String getCourseResourceUrl = platformUrl +"store/getResource";
	
	public static String querySelectOneUrl = platformUrl + "store/selectOne";
	
	public static String IndustryUrl = platformUrl + "admin/Industry/getList";
	public static String PostUrl = platformUrl + "admin/post/getList";
	public static String ClassifyUrl = platformUrl + "admin/classify/getList";
	
	public static String getPageCourseListUrl = platformUrl +"biz/course/getPageCourseList";
	
	public static String phone_pop_window_url= platformUrl +"v2/"+enterpriseId+"/users/"+userId+"/phone_pop_window";
	
	public static String weatherHasInfoUrl = platformUrl + "sysUpgradeInfo/weatherHasInfo";
	
	public static String recommend_courses_url =platformUrl + "v2/"+enterpriseId+"/users/"+userId+"/recommend_courses";
	
	public static String collectionUrl = platformUrl + "shopping/collection/add";
	
	public static String cancelcollectionUrl = platformUrl + "shopping/collection/delete";
	
	public static String update_url = platformUrl + "v2/"+userId+"/users/"+userId+"/discoveries/update";
	
	/**   
	 * @Title: updateCourse   
	 * @Description: TODO(更新app端发现的课程)   
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String updateCourse() {
		return GetRequestTools.RequestQueryParamsByGet("access_token", token, "enterprise_id", enterpriseId,"user_id",userId, update_url);
	}
	
	/**   
	 * @Title: cancelCollection  
	 * @Description: TODO(取消收藏课程)   
	 * @param: @param id
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String cancelCollection(String id) {
		return PostRequestTools.RequestFormDataByPost(token, "goodsIds", id, cancelcollectionUrl);
	}
	/**   
	 * @Title: addCollection   
	 * @Description: TODO(收藏课程)   
	 * @param: @param id
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String addCollection(String id) {
		return PostRequestTools.RequestFormDataByPost(token, "id", id, collectionUrl);
	}
	
	/**   
	 * @Title: queryRecommendCourses   
	 * @Description: TODO(查看推荐课程列表)   
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String queryRecommendCourses() {
		return GetRequestTools.RequestQueryParamsByGet("access_token", token,"position", "course_mall", "page_number","1",
				"page_size","4", recommend_courses_url);
	}
	
	/**   
	 * @Title: queryWeatherHasInfo   
	 * @Description: TODO(查看是否显示版本信息)   
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String queryWeatherHasInfo() {
		return GetRequestTools.RequestQueryParamsByGet("access_token", token, weatherHasInfoUrl);
	}
	/**   
	 * @Title: queryPhonePopWindow   
	 * @Description: TODO(查看是否弹窗)   
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String queryPhonePopWindow() {
		return GetRequestTools.RequestQueryParamsByGet("access_token", token, phone_pop_window_url);
	}
	
	/**   
	 * @Title: getPageCourseList   
	 * @Description: TODO(查看课程列表)   
	 * @param: @param title
	 * @param: @param classifyId
	 * @param: @param industry
	 * @param: @param post
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String getPageCourseList(String title,String classifyId,String industry,String post) {
		return GetRequestTools.RequestQueryParamsByGet("access_token", token,"title", title, "classifyId", classifyId, 
				"industry",industry,"post",post,"pageNumber","1","pageSize","20",getPageCourseListUrl);
	}
	
	/**   
	 * @Title: getIndustrylist   
	 * @Description: TODO(查看行业列表)   
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String getIndustrylist() {
		return GetRequestTools.RequestQueryParamsByGet("access_token", token,IndustryUrl);
	}
	/**   
	 * @Title: getPostlist   
	 * @Description: TODO(查看岗位列表)   
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String getPostlist() {
		return GetRequestTools.RequestQueryParamsByGet("access_token", token,PostUrl);
	}
	/**   
	 * @Title: getClassifylist   
	 * @Description: TODO(查看领域列表)   
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String getClassifylist() {
		return GetRequestTools.RequestQueryParamsByGet("access_token", token,ClassifyUrl);
	}
	/**   
	 * @Title: queryTeacherCourse   
	 * @Description: TODO(查看讲师下的所有课程)   
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String queryTeacherCourse(String courseId,String teacherId) {
		return GetRequestTools.RequestQueryParamsByGet("access_token", token,"courseId", courseId,"teacherId",teacherId,"count","2", teacherCourseUrl);
	}
	
	/**   
	 * @Title: querySelectOneInfo   
	 * @Description: TODO(查看课程详情)   
	 * @param: @param id
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String  querySelectOneInfo(String id) {
		return GetRequestTools.RequestQueryParamsByGet("access_token", token,"id", id, querySelectOneUrl);
	}
	
	/**   
	 * @Title: getCourseResource   
	 * @Description: TODO(查看课程资源)   
	 * @param: @param id
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String getCourseResource(String id) {
		return GetRequestTools.RequestQueryParamsByGet("access_token", token,"id", id, "pageNumber", "1", "pageSize","20",getCourseResourceUrl);
	}
	
	/**   
	 * @Title: relevantCourse   
	 * @Description: TODO(查看课程套餐的相关课程)   
	 * @param: @param id
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String relatedCourseOrPack (String id ) {
		return GetRequestTools.RequestQueryParamsByGet("access_token", token,"id", id,"count", "3", relatedCourseOrPackUrl);
	}
	/**   
	 * @Title: relevantCourse   
	 * @Description: TODO(查看课程的相关课程)   
	 * @param: @param courseId
	 * @param: @param count
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String relevantCourse (String courseId,String count) {
		return GetRequestTools.RequestQueryParamsByGet("access_token", token,"courseId", courseId,"count", count, relevantCourseUrl);
	}
	
	/**   
	 * @Title: queryInfo   
	 * @Description: TODO(查看精品套餐的详情)   
	 * @param: @param id
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String queryInfo(String id) {
		return GetRequestTools.RequestQueryParamsByGet("access_token", token,"id", id, getPackInfoUrl);
	}
	
	/**   
	 * @Title: getHomeCourselist   
	 * @Description: TODO(获取课程商场首页的课程列表)   
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String getHomeCourselist() {
		return GetRequestTools.RequestQueryParamsByGet("access_token", token,getHomeCourselistUrl);
	}
	
	/**   
	 * @Title: getPopularCourses   
	 * @Description: TODO(获取流行课程)   
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String getPopularCourses() {
		return GetRequestTools.RequestQueryParamsByGet("access_token", token,popular_courses_url);
	}
	
	/**   
	 * @Title: getRecommendCoursePack   
	 * @Description: TODO(获取推荐课程)   
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String getRecommendCoursePack() {
		return GetRequestTools.RequestQueryParamsByGet("access_token", token,getRecommendCoursePack_url);
	}
	
	/**   
	 * @Title: queryBannerInfo   
	 * @Description: TODO(banner信息获取)   
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String queryBannerInfo () {
		return GetRequestTools.RequestQueryParamsByGet("access_token", token,"terminal", "pc","page_size","20","page_number","1",
				"position","课程商城上部", "type","release",banner_url);
	}
}
