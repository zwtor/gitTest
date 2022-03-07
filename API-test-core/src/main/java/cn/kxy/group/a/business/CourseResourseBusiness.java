package cn.kxy.group.a.business;
/**
 * @author wenlingzhi
 *2021年5月7日
 */

import cn.kxy.base.business.EnterpriseData;
import cn.kxy.base.business.EnterpriseDataUrl;
import cn.kxy.base.business.TokenData;
import cn.kxy.setting.bussiness.UserBusiness;
import com.lazy.httpclient.utils.HttpRequest;

public class CourseResourseBusiness {

	public static String enterprise_url = EnterpriseDataUrl.getEnterpriseUrl();
	public static String token = TokenData.getMangerToken();
	public static String enterpriseId = EnterpriseData.getEnterpriseId();
	public static String user_id = UserBusiness.getUserId();
	public static String resource_classify_add_url = enterprise_url + "course/resource/classify/add/";
	public static String resource_list_url = enterprise_url + "course/resource/list/";
	public static String resource_file_add_url = enterprise_url + "course/resource/add/";
	public static String delete_resourse_url = enterprise_url + "course/resource/delete/";
	public static String delete_classify_url = enterprise_url + "course/resource/classify/delete/";


    
    /**   
	 * @Title: ResourceClassifyAdd  
	 * @Description: TODO  创建文件夹
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String ResourceClassifyAdd(String name,String parentId){	
		return HttpRequest.post(resource_classify_add_url).query("access_token", token).query("name",name)
				.query("parentId",parentId).query("visibleRange","all")
				.send().body();
	}
	
	
	/**   
	 * @Title: ResourceList  
	 * @Description: TODO  文件夹列表
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String ResourceList(String keyword,String parent_id,String resource_classify){	
		return HttpRequest.get(resource_list_url).query("access_token", token).query("keyword",keyword).query("parent_id",parent_id)
				.query("resource_classify",resource_classify).query("review","block")
				.send().body();
	}
	
	
	/**   
	 * @Title: ResourceFileAdd  
	 * @Description: TODO  上传文件
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String ResourceFileAdd(String id,String name,String size,String type,String videoId,String isDoc,
			String binaryFile,String contentType,String first_resourse_id){	
		return HttpRequest.post(resource_file_add_url).query("access_token", token).query("id",id).query("name",name)
				.query("process","0").query("uploadedSize","0").query("size",size).query("isError","false")
				.query("type",type).query("videoId",videoId).query("isDoc",isDoc).query("isCanceling","false")
				.query("isLoading","false").query("binaryFile",binaryFile).query("contentType",contentType)
				.query("discriminate","other").query("notLibrary","false").query("classifyId",first_resourse_id)
				.query("bizType","0")
				.send().body();
	}
	
	
	/**   
	 * @Title: DeleteResourse  
	 * @Description: TODO  删除课件
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String DeleteResourse(String id){	
		return HttpRequest.post(delete_resourse_url).query("access_token", token).query("ids",id)
				.send().body();
	}
	
	
	/**   
	 * @Title: DeleteClassifyResourse  
	 * @Description: TODO  删除文件夹
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String DeleteClassifyResourse(String id){	
		return HttpRequest.post(delete_classify_url).query("access_token", token).query("id",id)
				.send().body();
	}
	
	
}
