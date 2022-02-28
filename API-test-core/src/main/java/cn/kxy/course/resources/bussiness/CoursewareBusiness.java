package cn.kxy.course.resources.bussiness;

import cn.kxy.base.business.EnterpriseData;
import cn.kxy.base.business.EnterpriseDataUrl;
import cn.kxy.base.business.TokenData;
import cn.kxy.setting.bussiness.UserBusiness;
import com.alibaba.fastjson.JSONPath;
import com.lazy.assured.utils.GetRequestTools;
import com.lazy.assured.utils.PostRequestTools;
import com.lazy.httpclient.utils.HttpRequest;

public class CoursewareBusiness {
	public static String token = TokenData.getMangerToken();
	public static String enterpriseId = EnterpriseData.getEnterpriseId();
	public static String enterpriseUrl = EnterpriseDataUrl.getEnterpriseUrl();
	
	public static String addUrl = enterpriseUrl +"course/resource/classify/add";
	
	public static String deleteUrl = enterpriseUrl + "course/resource/classify/delete";
	public static String renameUrl = enterpriseUrl+"course/resource/classify/edit";
	
	public static String queryListUrl= enterpriseUrl+"course/resource/list";
	
	
	public static String queryNewListUrl= enterpriseUrl+"v2/"+enterpriseId+"/imagetext/courses/manage";
	
	
	public static String querygetListUrl= enterpriseUrl+"course/resource/getList";
	
	public static String set_visible_url = enterpriseUrl+"v2/"+enterpriseId+"/resources/batch_update_visible";
	
	public static String drag_url = enterpriseUrl + "v2/"+enterpriseId+"/resources/drag";
	
	public static String delete_doc_url = enterpriseUrl + "course/resource/delete";
	
	public static String user_id = UserBusiness.getUserId();
	
	public static String ticket_url = enterpriseUrl  + "v2/"+enterpriseId+"/ticket?access_token="+token;
	
	public static String videosUrl (String id) {
		return  enterpriseUrl + "v2/"+enterpriseId+"/users/"+user_id+"/videos/"+id+"?access_token="+token;
	}
	
	public static String Video(String id) {
		return HttpRequest.get(videosUrl(id)).send().body();
	}
	
	/**   
	 * @Title: ticketVideo   
	 * @Description: TODO 预热视频
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */  
	public static String ticketVideo() {
		return HttpRequest.get(ticket_url).send().body();
	}
	
	public static String downloadUrl (String id) {
		return enterpriseUrl + "v2/"+enterpriseId+"/users/"+user_id+"/resources/"+id+"/download?access_token="+token;
	}

	/**   
	 * @Title: downloadResource   
	 * @Description: TODO 下载资源
	 * @param: @param id
	 * @param: @return      
	 * @return: int      
	 * @throws   
	 */  
	public static int dowloadResource(String id) {
		return HttpRequest.get(downloadUrl(id)).send().statusCode();
	}
	
	/**   
	 * @Title: deleteDocument   
	 * @Description: TODO 删除上传的文件
	 * @param: @param id
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */  
	public static String deleteDocument(String id) {
		return HttpRequest.post(delete_doc_url).query("access_token",token).form("ids", id).send().body();
	}
	
	public static String dragCourseware (String classify_id,String id) {
		return PostRequestTools.RequestBodyByPost("{\r\n" + 
				"	\"classify_id\": \""+classify_id+"\",\r\n" + 
				"	\"resource_ids\": [{\r\n" + 
				"		\"id\": \""+id+"\",\r\n" + 
				"		\"type\": \"resource_folder\"\r\n" + 
				"	}],\r\n" + 
				"	\"access_token\": \""+id+"\"\r\n" + 
				"}", token, drag_url);
	}
	
	public static String setVisible(String id,String visible_range,String user_id) {
		return PostRequestTools.RequestBodyByPost("{\r\n" + 
				"	\"resource_classify_ids\": \""+id+"\",\r\n" + 
				"	\"resource_ids\": \"\",\r\n" + 
				"	\"visible_range\": \""+visible_range+"\",\r\n" + 
				"	\"department_ids\": \"\",\r\n" + 
				"	\"group_ids\": \"\",\r\n" + 
				"	\"post_ids\": \"\",\r\n" + 
				"	\"user_ids\": \""+user_id+"\",\r\n" + 
				"	\"access_token\": \""+token+"\"\r\n" + 
				"}", token, set_visible_url);
	}
	
	public static String queryGetList(String keyword) {
		return GetRequestTools.RequestQueryParamsByGet("access_token",token,"keyword",keyword,"parentId","0",
				"pageNumber","1","pageSize","20","resource_classify","0","courseFlag","1",querygetListUrl);
		
	}
	
	/**   
	 * @Title: queryList   
	 * @Description: TODO(根据关键字查询 文件夹列表)   
	 * @param: @param keyword
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String queryList(String keyword) {
		return GetRequestTools.RequestQueryParamsByGet("access_token",token,"keyword",keyword,"parent_id","0",
				"page_number","1","page_size","20","resource_classify","0","course_flag","1",queryListUrl);
	}
	
	
	public static String queryPictureArtList(String title,String course_status) {
		return GetRequestTools.RequestQueryParamsByGet("access_token",token,"title",title,"course_status",course_status,
				"page_number","1","page_size","20","course_type","all",queryNewListUrl);
	}
	
	public static String queryList(String keyword,String resource_classify) {
		return GetRequestTools.RequestQueryParamsByGet("access_token",token,"keyword",keyword,"parent_id","0",
				"page_number","1","page_size","20","resource_classify",resource_classify,"course_flag","1",queryListUrl);
	}
	public static String getIdByKeyword(String keyword) {
		String res= GetRequestTools.RequestQueryParamsByGet("access_token",token,"keyword",keyword,"excludeType","h5,zip,rar",
				"pageNumber","1","pageSize","20","resourceClassify","0",querygetListUrl);
		return  (String)JSONPath.read(res, "$.list[0].id");
	}
	/**   
	 * @Title: renameFolder   
	 * @Description: TODO(重命名文件夹)   
	 * @param: @param id
	 * @param: @param name
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String renameFolder(String id,String name) {
		return PostRequestTools.RequestFormDataByPost( token,"id", id,"name",name,renameUrl);
	}
	
	/**   
	 * @Title: deleteFolder   
	 * @Description: TODO(删除文件夹)   
	 * @param: @param id
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String deleteFolder(String id) {
		return PostRequestTools.RequestFormDataByPost( token,"id", id, deleteUrl);
	}
	
	/**   
	 * @Title: addFolder   
	 * @Description: TODO(课件管理，新增文件夹)   
	 * @param: @param name
	 * @param: @param parentId
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String addFolder(String name,String parentId,String user_id,String visibleRange) {
		return PostRequestTools.RequestFormDataByPost( token,"name", name,"userIds",user_id,"visibleRange",visibleRange,"parentId",parentId, addUrl);
	}
	
}
