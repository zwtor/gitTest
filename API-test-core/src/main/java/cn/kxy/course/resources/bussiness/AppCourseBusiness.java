package cn.kxy.course.resources.bussiness;

import cn.kxy.base.business.EnterpriseData;
import cn.kxy.base.business.EnterpriseDataUrl;
import cn.kxy.base.business.TokenData;
import cn.kxy.setting.bussiness.UserBusiness;
import com.alibaba.fastjson.JSONPath;
import com.lazy.assured.utils.GetRequestTools;
import com.lazy.assured.utils.PostRequestTools;

public class AppCourseBusiness {
	public static String token = TokenData.getMangerToken();
	public static String enterpriseId = EnterpriseData.getEnterpriseId();
	public static String appenterpriseUrl = EnterpriseDataUrl.getEnterpriseUrl();
	public static String appPlatformUrl = EnterpriseDataUrl.getPlatformUrl();
	static String userId = UserBusiness.getUserId();
	public static String queryCoursesListUrl= appenterpriseUrl +"v2/"+enterpriseId+"/users/"+userId+"/courses";
	
	public static String resource_classification_url= appenterpriseUrl + "v2/"+enterpriseId+"/resource_classification";
	
	public static String queryAppCourseInfoUrl(String courseId) {
		return appenterpriseUrl + "v2/"+enterpriseId+"/users/"+userId+"/courses/"+courseId;
	}
	
	public static String queryAppCourseCommentUrl(String courseId) {
		return appenterpriseUrl + "v2/"+enterpriseId+"/courses/"+courseId+"/comments";
	}
	
	public static String addCommentUrl(String courseId) {
		return appenterpriseUrl + "v2/"+enterpriseId+"/users/"+userId+"/courses/"+courseId+"/comment_commit";
	}
	
	public static String add_play_count_url(String courseId) {
		return appenterpriseUrl + "v2/"+enterpriseId+"/courses/"+courseId+"/add_play_count";
	}
	
	public static String addLikeUrl(String courseId) {
		return appenterpriseUrl + "v2/"+enterpriseId+"/users/"+userId+"/courses/"+courseId+"/like";
	}
	
	public static String save_progress_url(String courseId,String resourceId) {
		return appenterpriseUrl +"v2/"+enterpriseId+"/users/"+userId+"/courses/"+courseId+"/resources/"+resourceId+"/save_progress";
	}
	
	public static String  queryappviewersUrl(String courseId) {
		return appenterpriseUrl + "v2/"+enterpriseId+"/courses/"+courseId+"/viewers";
	}
	
	public static String recommend_courses_url = appPlatformUrl + "v2/"+enterpriseId+"/users/"+userId+"/recommend_courses";
	
	public static String assignUrl(String id) {
		return appenterpriseUrl + "v2/"+enterpriseId+"/courses/"+id+"/assign";
	}
	
	public static String trackCourseUrl(String id) {
		return appenterpriseUrl + "v2/"+enterpriseId+"/courses/"+id+"/track";
	}
	
	public static String assignStudyProjectUrl(String id) {
		return appenterpriseUrl + "v2/"+enterpriseId+"/study_projects/"+id+"/assign";
	}
	
	public static String getListUrl = appenterpriseUrl + "plan/study/getList";
	
	/**   
	 * @Title: getStudyTaskList   
	 * @Description: TODO   指派学习下项目后根据不同状态查看
	 * @param: @param keyword
	 * @param: @param onlySeeMe
	 * @param: @param queryIntervalType
	 * @param: @param projectId
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */  
	public static String getStudyTaskList(String keyword,String onlySeeMe,String queryIntervalType,String projectId) {
		return GetRequestTools.RequestQueryParamsByGet("keyword", keyword, "onlySeeMe", onlySeeMe,"queryIntervalType",
				queryIntervalType, "pageSize","20","pageNumber","1","user_id",userId,"projectId",projectId,"access_token",token,getListUrl);
	}
	
	
	
	/**   
	 * @Title: queryYrackCourseList   
	 * @Description: TODO(查询课程跟踪列表)   
	 * @param: @param id
	 * @param: @param status (all--全部   unfinished  finished  overdue )
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String queryTrackCourseList (String id,String status) {
		return GetRequestTools.RequestQueryParamsByGet("access_token",token, "page_number", "1", "page_size","10", 
				"enterprise_id",enterpriseId,"user_id",userId,"status",status,trackCourseUrl(id));
	}
	
	public static String assignStudyProjectCourse (String id,String plan_begin_time,String plan_end_time) {
		return PostRequestTools.RequestBodyByPost("{\r\n" + 
				"	\"plan_title\": \"\",\r\n" + 
				"	\"plan_begin_time\": "+plan_begin_time+",\r\n" + 
				"	\"plan_end_time\": "+plan_end_time+",\r\n" + 
				"	\"is_notice_all\": false,\r\n" + 
				"	\"department_ids\": [],\r\n" + 
				"	\"user_ids\": [\""+userId+"\"],\r\n" + 
				"	\"post_ids\": [],\r\n" + 
				"	\"group_ids\": [],\r\n" + 
				"	\"supervisor_ids\": []\r\n" + 
				"}", token, "enterprise_id",enterpriseId,"user_id",userId,assignStudyProjectUrl(id));
	}
	
	/**   
	 * @Title: assignCourse   
	 * @Description: TODO(指派课程)   
	 * @param: @param id
	 * @param: @param time
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String assignCourse (String id,String time) {
		return PostRequestTools.RequestBodyByPost("{\r\n" + 
				"	\"end_time\": \""+time+"\",\r\n" + 
				"	\"departments\": [],\r\n" + 
				"	\"learners\": [\""+userId+"\"]\r\n" + 
				"}", token, "enterprise_id",enterpriseId,"user_id",userId,assignUrl(id));
	}
	
	public static String queryAppreCommendCourses() {
		return GetRequestTools.RequestParamsByGet("access_token", token,"position","course_finish","page_number","1","page_size","2", recommend_courses_url);
	}
	
	/**   
	 * @Title: queryClassificationList   
	 * @Description: TODO(查询分类列表)   
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String queryClassificationList() {
		return GetRequestTools.RequestQueryParamsByGet("access_token",token, "user_id",userId,resource_classification_url);
	}
	
	/**   
	 * @Title: queryAppViewer   
	 * @Description: TODO(APP端查看课程观看人数)   
	 * @param: @param courseId
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String queryAppViewer(String courseId) {
		return GetRequestTools.RequestQueryParamsByGet("access_token",token, "page_number","1","page_size","10","user_id",userId,queryappviewersUrl(courseId));
		
	}
	
	/**   
	 * @Title: saveAppProgress   
	 * @Description: TODO(保存课程进度)   
	 * @param: @param courseId
	 * @param: @param resourceId
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String  saveAppProgress(String courseId,String resourceId,String progress ) {
		return PostRequestTools.RequestBodyByPost("{ \"progress\": "+progress+" }", token, save_progress_url(courseId, resourceId));
	}
	
	public static String  saveAppProgress(String courseId,String resourceId) {
		return PostRequestTools.RequestBodyByPost("{ \"progress\": "+10+" }", token,"","", save_progress_url(courseId, resourceId));
	}
	
	/**   
	 * @Title: addPlayCount   
	 * @Description: TODO(app端增加播放次数)   
	 * @param: @param courseId
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String addAppPlayCount(String courseId) {
		return PostRequestTools.RequestQueryParamByPost("access_token",token, add_play_count_url(courseId));
	}
	
	/**   
	 * @Title: addAppCourseComment   
	 * @Description: TODO(app端课程点赞和取消点赞，like--点赞，unlike--取消点赞)   
	 * @param: @param courseId
	 * @param: @param comment
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String addAppCourseLike(String courseId,String status) {
		return PostRequestTools.RequestBodyByPost("{ \"status\": \""+status+"\" }", token, addLikeUrl(courseId));
		
	}
	
	
	/**   
	 * @Title: addAppCourseComment   
	 * @Description: TODO(app端课程的添加评论)   
	 * @param: @param courseId
	 * @param: @param comment
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String addAppCourseComment(String courseId,String comment) {
		return PostRequestTools.RequestBodyByPost("{ \"comment\": \""+comment+"\" }", token, addCommentUrl(courseId));
		
	}
	
	/**   
	 * @Title: queryAppCourseComment   
	 * @Description: TODO(查询app端课程评论)   
	 * @param: @param courseId
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String queryAppCourseComment(String courseId) {
		return GetRequestTools.RequestQueryParamsByGet("user_id", userId, "access_token",token,"page_number","1",
				"page_size","5",queryAppCourseCommentUrl(courseId));
	}
	
	/**   
	 * @Title: queryAppCourseInfo   
	 * @Description: TODO(查看app端的课程详情)   
	 * @param: @param courseId
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String queryAppCourseInfo(String courseId) {
		return GetRequestTools.RequestQueryParamsByGet("access_token", token,"source","course_center", queryAppCourseInfoUrl(courseId));
	}
	
	/**   
	 * @Title: queryCoursesList   
	 * @Description: TODO(查询app端课程列表)   
	 * @param: @param classify_id
	 * @param: @param keyword
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String queryAppCoursesList (String classify_id,String keyword) {
		return GetRequestTools.RequestQueryParamsByGet("access_token", token,"classify_id",classify_id,"keyword",keyword,"page_number","1",
				"page_size","20","status","studying","type","normal",queryCoursesListUrl);
	}
	
	/**   
	 * @Title: getIdByKeyword   
	 * @Description: TODO(根据关键字获取Id)   
	 * @param: @param classify_id
	 * @param: @param keyword
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String getAppIdByKeyword(String classify_id,String keyword) {
		String res = queryAppCoursesList(classify_id, keyword);
		String id = (String)JSONPath.read(res, "$.list[0].id");
		return id;
	}
	
}
