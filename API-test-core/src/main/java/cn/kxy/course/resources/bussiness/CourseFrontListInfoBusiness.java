package cn.kxy.course.resources.bussiness;

import cn.kxy.base.business.EnterpriseData;
import cn.kxy.base.business.EnterpriseDataUrl;
import cn.kxy.base.business.TokenData;
import cn.kxy.setting.bussiness.UserBusiness;
import com.alibaba.fastjson.JSONPath;
import com.lazy.assured.utils.GetRequestTools;
import com.lazy.assured.utils.PostRequestTools;
import com.lazy.common.utils.DateUtil;

public class CourseFrontListInfoBusiness {
	
	public static String token = TokenData.getMangerToken();
	public static String enterpriseId = EnterpriseData.getEnterpriseId();
	public static String enterpriseUrl = EnterpriseDataUrl.getEnterpriseUrl();
	public static String platformUrl = EnterpriseDataUrl.getPlatformUrl();
	
	public static String queryInfoUrl = enterpriseUrl + "course/selectOne";
	public static String queryCoursePieChartUrl= enterpriseUrl + "course/queryCoursePieChart";
	public static String getAdminCourseCountUrl=enterpriseUrl  + "admin/classify/getCourseCount";
	public static String getListAndCountUrl= enterpriseUrl + "admin/classify/getListAndCount";
	
	public static String getCourseCountUrl=enterpriseUrl +  "course/getCourseCount";
	
	public static String queryCommentListUrl =enterpriseUrl + "course/queryCommentList";
	
	public static String queryCourseRecordUrl =enterpriseUrl + "course/selectCourseRecordByCourseId";
	public static String addCommentsesUrl =enterpriseUrl + "course/comment";
	
	public static String deleteCommentUrl =enterpriseUrl + "course/deleteComment";
	public static String queryPopularCoursesUrl =platformUrl + "v2/popular_courses";
	
	public static String addPlayCountUrl=enterpriseUrl +"course/addPlayCount";
	
	public static String recommendSetUrl = enterpriseUrl + "course/recommendSet";
	
	public static String recommendRemoveUrl = enterpriseUrl + "course/recommendRemove";
	
	public static String changeClassifyUrl = enterpriseUrl + "course/changeClassify";
	
	public static String creditCourseUrl(String courseId) {
		return enterpriseUrl + "v2/"+enterpriseId+"/course/"+courseId+"/credit";
	}
	
	/**   
	 * @Title: changeCourseClassify   
	 * @Description: TODO(课程分类)   
	 * @param: @param courseId
	 * @param: @param classifyId
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String changeCourseClassify(String courseId,String classifyId) {
		return PostRequestTools.RequestFormDataByPost(token, "courseId", courseId, "classifyId",classifyId,changeClassifyUrl);
	}
	
	/**   
	 * @Title: creditCourse   
	 * @Description: TODO(设置课程学分)   
	 * @param: @param courseId
	 * @param: @param credit
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String creditCourse(String courseId,String credit) {
		return PostRequestTools.RequestFormDataByPost(token, "credit", credit,creditCourseUrl(courseId) );
	}
	
	/**   
	 * @Title: recommendSetCourse   
	 * @Description: TODO(顶置课程)   
	 * @param: @param courseId
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String recommendSetCourse(String courseId) {
		return PostRequestTools.RequestFormDataByPost(token, "id",courseId , recommendSetUrl);
	}
	
	/**   
	 * @Title: recommendRemoveCourse   
	 * @Description: TODO(取消顶置)   
	 * @param: @param courseId
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String recommendRemoveCourse(String courseId) {
		return PostRequestTools.RequestFormDataByPost(token, "id",courseId , recommendRemoveUrl);
	}
	
	
	/**   
	 * @Title: queryCourseInfo   
	 * @Description: TODO(查询详情的另一个接口)   
	 * @param: @param courseId
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String queryCourseInfoUrl (String courseId) {
		return enterpriseUrl + "v2/"+enterpriseId+"/users/"+UserBusiness.getUserId()+"/courses/"+courseId;
	}
	
	public static String queryResourcesInfoUrl(String resourcesId) {
		return enterpriseUrl +  "v2/"+enterpriseId+"/resources/"+resourcesId+"/query";
	} 
	
	public static String recommend_courses_url = platformUrl + "v2/"+enterpriseId+"/users/"+UserBusiness.getUserId()+"/recommend_courses";
	public static String getCoursesSourceListUrl(String courseId) {
		return enterpriseUrl +"v2/"+enterpriseId+"/courses/"+courseId+"/list";
	}
	
	public static String saveCourseProgressUrl(String courseId,String resourcesId) {
		return enterpriseUrl +"v2/"+enterpriseId+"/users/"+UserBusiness.getUserId()+"/courses/"+courseId+"/resources/"+resourcesId+"/save_progress";
	}
	
	public static String queryCourseInfo(String courseId) {
		return GetRequestTools.RequestQueryParamsByGet("useType","draft", "access_token",token,queryCourseInfoUrl(courseId));
	}
	
	/**   
	 * @Title: queryResourcesInfo   
	 * @Description: TODO(课程详情页，点击开始学习，查看资源详情)   
	 * @param: @param resourcesId
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String queryResourcesInfo(String resourcesId) {
		return GetRequestTools.RequestQueryParamsByGet("access_token",token, queryResourcesInfoUrl(resourcesId));
	}
	
	/**   
	 * @Title: getCoursesSourceList   
	 * @Description: TODO(查看课程的课件列表)   
	 * @param: @param courseId
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String getCoursesSourceList(String courseId) {
		return GetRequestTools.RequestQueryParamsByGet("access_token", token, getCoursesSourceListUrl(courseId));
	}
	
	public static String getCourseFirstSourceId(String courseId) {
		String res = getCoursesSourceList(courseId);
		String id = (String)JSONPath.read(res, "$.courses[0].id");
		return id;
	}
	public static String recommendCourses() {
		return GetRequestTools.RequestQueryParamsByGet("access_token",token,"page_number", "1","page_size","4","position","course_finish", recommend_courses_url);
	}
	
	/**   
	 * @Title: addPlayCount   
	 * @Description: TODO(增加播放次数)   
	 * @param: @param courseId
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String addPlayCount(String courseId) {
		return PostRequestTools.RequestFormDataByPost(token, "courseId", courseId, addPlayCountUrl);
	}

	/**   
	 * @Title: saveCourseProgress   
	 * @Description: TODO(保存课程进度)   
	 * @param: @param courseId
	 * @param: @param resourcesId
	 * @param: @param progress
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String saveCourseProgress(String courseId,String resourcesId,String progress) {
		DateUtil.getCurrentTime();
		return PostRequestTools.RequestBodyByPost("{\r\n" + 
				"  \"progress\": "+progress+", \r\n" + 
				"  \"recent_start\": 1, \r\n" + 
				"  \"tempTime\": "+DateUtil.getTimeStamp(0,"")+", \r\n" + 
				"  \"access_token\": \""+token+"\"\r\n" + 
				"}", token, saveCourseProgressUrl(courseId, resourcesId));
		
	}
	
	/**   
	 * @Title: deleteComment   
	 * @Description: TODO(删除评论)   
	 * @param: @param courseId
	 * @param: @param commentId
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String deleteComment(String courseId,String commentId) {
		return PostRequestTools.RequestFormDataByPost(token, "courseId", courseId, "commentId",commentId,deleteCommentUrl);

	}
	
	/**   
	 * @Title: addCommentScore   
	 * @Description: TODO(课程评分)   
	 * @param: @param courseId
	 * @param: @param score
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String addCommentScore(String courseId,String score) {
		return PostRequestTools.RequestFormDataByPost(token, "courseId", courseId,"score",score, addCommentsesUrl);
	}
	
	/**   
	 * @Title: addComment   
	 * @Description: TODO(添加课程评论)   
	 * @param: @param courseId
	 * @param: @param content
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String addComment(String courseId,String content) {
		return PostRequestTools.RequestFormDataByPost(token, "courseId", courseId,"content",content, addCommentsesUrl);
	}
	
	/**   
	 * @Title: queryPopularCourses   
	 * @Description: TODO(查询流行的课程列表)   
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String queryPopularCourses() {
		return GetRequestTools.RequestQueryParamsByGet("access_token", token, queryPopularCoursesUrl);

	}
	
	/**   
	 * @Title: queryCourseRecord   
	 * @Description: TODO(查询课程观看进度)   
	 * @param: @param courseId
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String queryCourseRecord(String courseId) {
		return GetRequestTools.RequestQueryParamsByGet("access_token", token,"courseId",courseId,"pageNumber","1", queryCourseRecordUrl);
	}
	
	/**   
	 * @Title: queryCommentList   
	 * @Description: TODO(查看课程评论列表)   
	 * @param: @param courseId
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String queryCommentList(String courseId) {
		return GetRequestTools.RequestQueryParamsByGet("access_token", token,"courseId",courseId,"pageNumber","1", queryCommentListUrl);
		
	}
	
	/**   
	 * @Title: getFirstCommentId   
	 * @Description: TODO(获取第一个评论的Id)   
	 * @param: @param courseId
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String getFirstCommentId(String courseId) {
		String res = queryCommentList(courseId);
		String id = (String)JSONPath.read(res, "$.list[0].id");
		return id;
	}
	
	/**   
	 * @Title: getListAndCount   
	 * @Description: TODO(查看分类的课程个数)   
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String queryClassifyCount( ) {
		return GetRequestTools.RequestQueryParamsByGet("access_token", token, "parentId","0",getListAndCountUrl);
	}
	
	/**   
	 * @Title: queryCoursePieChart   
	 * @Description: TODO(查看课程分布图)   
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String queryCoursePieChart() {
		return GetRequestTools.RequestQueryParamsByGet("access_token", token, queryCoursePieChartUrl);
		
	}
	
	/**   
	 * @Title: getCourseCounr   
	 * @Description: TODO()   
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String getCourseCount() {
		return GetRequestTools.RequestQueryParamsByGet("access_token", token,getCourseCountUrl);
	}
	
	public static int getCourseOriginalCount() {
		String res = getCourseCount();
		int total = (int)JSONPath.read(res, "$.data.originalCount");
		return total;
	}
	public static int getCourseNonOriginalCount() {
		String res = getCourseCount();
		int total = (int)JSONPath.read(res, "$.data.unOriginalCount");
		return total;
	}
	
	/**   
	 * @Title: getCourseCount   
	 * @Description: TODO(查看admin课程数量)   
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String getAdminCourseCount() {
		return GetRequestTools.RequestQueryParamsByGet("access_token", token, "parentId","0",getAdminCourseCountUrl);
	}
	
	/**   
	 * @Title: getCourseOriginalCount   
	 * @Description: TODO(获取非原创数量)   
	 * @param: @return      
	 * @return: int      
	 * @throws   
	 */ 
	public static int getCourseAdminNonOriginalCount() {
		String res = getAdminCourseCount();
		int total = (int)JSONPath.read(res, "$.data[1].count");
		return total;
	}
	
	/**   
	 * @Title: getCoursePlantBuyCount   
	 * @Description: TODO(获取平台采购数)   
	 * @param: @return      
	 * @return: int      
	 * @throws   
	 */ 
	public static int getCoursePlantBuyCount() {
		String res = getAdminCourseCount();
		int total = (int)JSONPath.read(res, "$.data[2].count");
		return total;
	}
	
	/**   
	 * @Title: getCourseOriginalCount   
	 * @Description: TODO(获取原创数量)   
	 * @param: @return      
	 * @return: int      
	 * @throws   
	 */ 
	public static int getCourseAdminOriginalCount() {
		String res = getAdminCourseCount();
		int total = (int)JSONPath.read(res, "$.data[0].count");
		return total;
	}
	
	public static int getCourseTotalCount() {
		String res = getAdminCourseCount();
		int total = (int)JSONPath.read(res, "$.data[0].count");
		return total;
	}
	
	/**   
	 * @Title: queryInfo   
	 * @Description: TODO(根据Id查看详情)   
	 * @param: @param id
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String queryInfo(String id) {
		return GetRequestTools.RequestQueryParamsByGet("id", id, "access_token",token,queryInfoUrl);
	}
	
}
