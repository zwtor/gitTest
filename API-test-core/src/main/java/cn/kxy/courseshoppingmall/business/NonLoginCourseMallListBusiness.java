package cn.kxy.courseshoppingmall.business;

import cn.kxy.base.business.EnterpriseDataUrl;
import cn.kxy.base.business.TokenData;
import com.lazy.assured.utils.GetRequestTools;

public class NonLoginCourseMallListBusiness {
	public static String platformUrl=  EnterpriseDataUrl.getPlatformUrl();
	public static String token = TokenData.getMangerToken();
	
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
		return GetRequestTools.RequestQueryParamsByGet("title", title, "classifyId", classifyId, 
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
		return GetRequestTools.RequestQueryParamsByGet(IndustryUrl);
	}
	/**   
	 * @Title: getPostlist   
	 * @Description: TODO(查看岗位列表)   
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String getPostlist() {
		return GetRequestTools.RequestQueryParamsByGet(PostUrl);
	}
	/**   
	 * @Title: getClassifylist   
	 * @Description: TODO(查看领域列表)   
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String getClassifylist() {
		return GetRequestTools.RequestQueryParamsByGet(ClassifyUrl);
	}
	/**   
	 * @Title: queryTeacherCourse   
	 * @Description: TODO(查看讲师下的所有课程)   
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String queryTeacherCourse(String courseId,String teacherId) {
		return GetRequestTools.RequestQueryParamsByGet("courseId", courseId,"teacherId",teacherId,"count","2", teacherCourseUrl);
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
		return GetRequestTools.RequestQueryParamsByGet("id", id, querySelectOneUrl);
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
		return GetRequestTools.RequestQueryParamsByGet("id", id, "pageNumber", "1", "pageSize","20",getCourseResourceUrl);
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
		return GetRequestTools.RequestQueryParamsByGet("id", id,"count", "3", relatedCourseOrPackUrl);
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
		return GetRequestTools.RequestQueryParamsByGet("courseId", courseId,"count", count, relevantCourseUrl);
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
		return GetRequestTools.RequestQueryParamsByGet("id", id, getPackInfoUrl);
	}
	
	/**   
	 * @Title: getHomeCourselist   
	 * @Description: TODO(获取课程商场首页的课程列表)   
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String getHomeCourselist() {
		return GetRequestTools.RequestQueryParamsByGet(getHomeCourselistUrl);
	}
	
	/**   
	 * @Title: getPopularCourses   
	 * @Description: TODO(获取流行课程)   
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String getPopularCourses() {
		return GetRequestTools.RequestQueryParamsByGet(popular_courses_url);
	}
	
	/**   
	 * @Title: getRecommendCoursePack   
	 * @Description: TODO(获取推荐课程)   
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String getRecommendCoursePack() {
		return GetRequestTools.RequestQueryParamsByGet(getRecommendCoursePack_url);
	}
	
	/**   
	 * @Title: queryBannerInfo   
	 * @Description: TODO(banner信息获取)   
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String queryBannerInfo () {
		return GetRequestTools.RequestQueryParamsByGet("terminal", "pc","page_size","20","page_number","1",
				"position","课程商城上部", "type","release",banner_url);
	}
}
