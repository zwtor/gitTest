package cn.kxy.course.resources.bussiness;

import cn.kxy.base.business.EnterpriseData;
import cn.kxy.base.business.EnterpriseDataUrl;
import cn.kxy.base.business.TokenData;
import cn.kxy.setting.bussiness.UserBusiness;
import com.lazy.httpclient.utils.HttpRequest;

public class CourseCutting {

	public static String token = TokenData.getMangerToken();
	public static String enterprise_id = EnterpriseData.getEnterpriseId();
	public static String enterprise_url = EnterpriseDataUrl.getEnterpriseUrl();
	public static String platform_url = EnterpriseDataUrl.getPlatformUrl();
	public static String user_id = UserBusiness.getUserId();
	public static String list_by_level_url = platform_url + "v2/classify/list_by_level";
	
	public static String batch_submit_url = enterprise_url + "v2/"+enterprise_id+"/ai_upgrade/batch_submit";
	
	public static String query_ai_resource_url = enterprise_url + "v2/"+enterprise_id+"/query_ai_resource";
	
	public static String queryCoursesUrl(String course_id) {
		return enterprise_url + "v2/"+enterprise_id+"/courses/"+course_id+"/ai_upgrade/query";
	}
	public static String queryResourcesTxtUrl (String resources_id) {
		return enterprise_url + "v2/"+enterprise_id+"/resources/"+resources_id+"/ai_upgrade/query_text";
	}
	
	public static String queryResourcesUrl(String resources_id) {
		return  enterprise_url + "v2/"+enterprise_id+"/resources/"+resources_id+"/ai_upgrade/query";
	}
	
	public static String updateSegmentationsUrl(String resources_id) {
		return enterprise_url + "v2/"+enterprise_id+"/resources/"+resources_id+"/segmentations/update";
	}
	
	public static String deleteSegmentationsUrl (String resources_id,String segmentations_id) {
		return enterprise_url + "v2/"+enterprise_id+"/resources/"+resources_id+"/segmentations/"+segmentations_id+"/delete";
	}
	
	public static String knowledge_segmentation_url = enterprise_url + "v2/"+enterprise_id+"/knowledge_segmentation/list";
	
	public static String courses_url = enterprise_url + "v2/"+enterprise_id+"/users/"+user_id+"/courses";
	
	public static String ai_learning_url (String course_id,String resources_id) {
		return enterprise_url + "v2/"+enterprise_id+"/users/"+user_id+"/courses/"+course_id+"/resources/"+resources_id+"/ai_learning";
	}
	
	public static String queryAiLearn(String course_id,String resources_id) {
		return HttpRequest.get(ai_learning_url(course_id, resources_id)).query("access_token",token).query("enterprise_id",enterprise_id).
				query("user_id",user_id).send().body();
	}
	
	/**   
	 * @Title: queryCourseByType   
	 * @Description: TODO
	 * @param: @param sort_type   hottest    newest 
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */  
	public static String queryCourseByType (String sort_type) {
		return HttpRequest.get(courses_url).query("access_token",token).query("enterprise_id",enterprise_id).
				query("user_id",user_id).query("classify_id","0").query("sort_type",sort_type).
				query("status","studying").query("page_number","1").query("page_size","5").query("type","normal").send().body();
	}
	
	public static String queryKnowledgeSegmentation() {
		return HttpRequest.get(knowledge_segmentation_url).query("access_token",token).query("enterprise_id",enterprise_id).
				query("user_id",user_id).query("page_number","1").query("page_size","5").send().body();
	}
	
	/**   
	 * @Title: queryAIResource   
	 * @Description: TODO   查看AI资源
	 * @param: @param course_ids
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */  
	public static String queryAIResource(String course_ids) {
		return HttpRequest.get(query_ai_resource_url).query("access_token",token).query("course_ids",course_ids).send().body();
	}
	
	/**   
	 * @Title: submitAICutting   
	 * @Description: TODO  AI切割
	 * @param: @param course_id
	 * @param: @param resource_id
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */  
	public static String submitAICutting(String course_id,String resource_id,String classify_id) {
		return HttpRequest.post(batch_submit_url).query("access_token",token).data("{\r\n" + 
				"  \"resourceClassifyVoList\":[\r\n" + 
				"    {\r\n" + 
				"      \"resourceId\":\""+resource_id+"\",\r\n" + 
				"      \"classify_id\":\""+classify_id+"\","+
				"      \"course_id\":\""+course_id+"\"\r\n" + 
				"    }],\r\n" + 
				"  \"access_token\":\""+token+"\"\r\n" + 
				"}\r\n" + 
				"").send().body();
	}
	
	public static String queryLevelList() {
		return HttpRequest.get(list_by_level_url).query("access_token",token).query("level","2").query("is_layered","true").send().body();
	}
	
	/**   
	 * @Title: deleteSegmentations   
	 * @Description: TODO  删除资源的片段
	 * @param: @param resources_id
	 * @param: @param segmentations_id
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */  
	public static String deleteSegmentations(String resources_id,String segmentations_id) {
		return HttpRequest.post(deleteSegmentationsUrl(resources_id,segmentations_id)).query("access_token",token).data("{\"access_token\":\""+token+"\"}").send().body();
	}
	
	public static String updateSegmentations (String name ,String tags,String id,String resources_id,String create_time,String end_time) {
		return HttpRequest.post(updateSegmentationsUrl(resources_id)).query("access_token",token).data("{\r\n" + 
				"  \"click_count\":\"0\",\r\n" + 
				"  \"create_time\":"+create_time+",\r\n" + 
				"  \"duration\":\"5\",\r\n" + 
				"  \"end_time\":\""+end_time+"\",\r\n" + 
				"  \"id\":\""+id+"\",\r\n" + 
				"  \"name\":\""+name+"\",\r\n" + 
				"  \"resource_id\":\""+resources_id+"\",\r\n" + 
				"  \"snapshot_url\":\"https://video.coolcollege.cn/3d498feefb5e449cac1e72a64ad14952/snapshots/normal/3C87C381-175CFAB2288-1950-1004-465-8181900001.jpg\",\r\n" + 
				"  \"start_time\":\"0\",\r\n" + 
				"  \"tags\":[\r\n" + 
				"    \""+tags+"\"],\r\n" + 
				"  \"access_token\":\""+token+"\"\r\n" + 
				"}\r\n" + 
				"").send().body();
	}
	
	/**   
	 * @Title: updateSegmentations   
	 * @Description: TODO   保存片段
	 * @param: @param name
	 * @param: @param tags
	 * @param: @param resources_id
	 * @param: @param end_time
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */  
	public static String updateSegmentations (String name ,String tags,String resources_id,String end_time) {
		return HttpRequest.post(updateSegmentationsUrl(resources_id)).query("access_token",token).data("{\r\n" + 
				"  \"start_time\":0,\r\n" + 
				"  \"end_time\":"+end_time+",\r\n" + 
				"  \"name\":\""+name+"\",\r\n" + 
				"  \"tags\":[\r\n" + 
				"    \""+tags+"\"],\r\n" + 
				"  \"access_token\":\""+token+"\"\r\n" + 
				"}\r\n" + 
				"").send().body();
	}
	
	public static String queryCourseCutting(String course_id) {
		return HttpRequest.get(queryCoursesUrl(course_id)).query("access_token",token).send().body();
	}
	/**   
	 * @Title: queryResourcesCutting   
	 * @Description: TODO    查看ai切割的个数
	 * @param: @param course_id
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */  
	public static String queryResourcesCutting(String resources_id) {
		return HttpRequest.get(queryResourcesUrl(resources_id)).query("access_token",token).send().body();
	}
	
	public static String queryResourcesTxtCutting(String resources_id) {
		return HttpRequest.get(queryResourcesTxtUrl(resources_id)).query("access_token",token).send().body();
	}
	
	
}
