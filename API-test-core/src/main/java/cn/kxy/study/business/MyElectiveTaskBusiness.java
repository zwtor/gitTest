package cn.kxy.study.business;

import cn.kxy.base.business.EnterpriseData;
import cn.kxy.base.business.EnterpriseDataUrl;
import cn.kxy.base.business.TokenData;
import cn.kxy.setting.bussiness.UserBusiness;
import com.lazy.assured.utils.GetRequestTools;
import com.lazy.assured.utils.PostRequestTools;
import com.lazy.common.utils.DateUtil;

public class MyElectiveTaskBusiness {
	public static String token = TokenData.getMangerToken();
	public static String enterpriseId = EnterpriseData.getEnterpriseId();
	public static String enterpriseUrl = EnterpriseDataUrl.getEnterpriseUrl();
	public static String userId = UserBusiness.getUserId();
	public static String list_url = enterpriseUrl + "plan/myTask/getList";
	
	public static String queryInfoUrl =enterpriseUrl + "plan/myTask/getOne";
	
	public static String queryCourseInfoUrl(String resources_id) {
		return enterpriseUrl + "v2/"+enterpriseId+"/image_text/"+resources_id+"/resource_query";
	}
	
	public static String getSingleResourceProgressUrl = enterpriseUrl +"plan/myTask/getSingleResourceProgress";
	
	public static String delete_url =enterpriseUrl + "plan/study/delete";

	public static String addUrl = enterpriseUrl + "plan/myTask/add";
	
	/**   
	 * @Title: getSingleResourceProgress   
	 * @Description: TODO()   
	 * @param: @param planId
	 * @param: @param courseId
	 * @param: @param resourceId
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String getSingleResourceProgress(String planId,String courseId,String resourceId) {
		return GetRequestTools.RequestQueryParamsByGet("planId", planId, "access_token", token,"courseId",courseId,
				"resourceId",resourceId,getSingleResourceProgressUrl);
	}
	/**   
	 * @Title: addElectiveTask   
	 * @Description: TODO(添加选修任务)   
	 * @param: @param title
	 * @param: @param beginTime
	 * @param: @param endTime
	 * @param: @param isPassThrough
	 * @param: @param resource_id
	 * @param: @param course_id
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String addElectiveTask(String title,String beginTime,String endTime,String isPassThrough,String resource_id,String course_id) {
		return PostRequestTools.RequestFormDataByPost(token, "title",title,"beginTime", beginTime,"endTime",endTime ,
				"isNoticeAll","0","isPassThrough",isPassThrough,"hasExam","0","resourceSortInfoStr","[\r\n" + 
						"  {\r\n" + 
						"    \"resourceId\": \""+resource_id+"\", \r\n" + 
						"    \"sort\": 0, \r\n" + 
						"    \"courseType\":1 \r\n" + 
						"  }, \r\n" + 
						"  {\r\n" + 
						"    \"resourceId\": \""+course_id+"\", \r\n" + 
						"    \"sort\": 1, \r\n" + 
						"    \"courseType\": 1\r\n" + 
						"  }\r\n" + 
						"]",addUrl);
	}
	
	/**   
	 * @Title: deleteTask   
	 * @Description: TODO(删除任务)   
	 * @param: @param id
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String deleteTask(String id) {
		return PostRequestTools.RequestFormDataByPost(token, "id", id, delete_url);
	}
	/**   
	 * @Title: queryCourseInfo   
	 * @Description: TODO(查看课程详情)   
	 * @param: @param resources_id
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String queryCourseInfo(String resources_id) {
		return GetRequestTools.RequestQueryParamsByGet("access_token", token, queryCourseInfoUrl(resources_id));
	}
	
	/**   
	 * @Title: queryInfo   
	 * @Description: TODO(查看详情)   
	 * @param: @param id
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String queryInfo(String id) {
		return GetRequestTools.RequestQueryParamsByGet("id", id,"access_token",token, queryInfoUrl);
	}
	/**   
	 * @Title: queryList   
	 * @Description: TODO(查看列表)   
	 * @param: @param keyword
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String queryList(String keyword) {
		return GetRequestTools.RequestQueryParamsByGet("access_token",token,"status","0","pageNumber","1","pageSize","20",
				"sortName","createTime","sortOrder","desc","type","1","timestamp",DateUtil.getCurrentTime(),"keyword",keyword,list_url);
	}
	
}
