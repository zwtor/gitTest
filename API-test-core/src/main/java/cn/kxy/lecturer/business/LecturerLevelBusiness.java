package cn.kxy.lecturer.business;

import cn.kxy.base.business.EnterpriseData;
import cn.kxy.base.business.EnterpriseDataUrl;
import cn.kxy.base.business.TokenData;
import com.alibaba.fastjson.JSONPath;
import com.lazy.assured.utils.GetRequestTools;
import com.lazy.assured.utils.PostRequestTools;

public class LecturerLevelBusiness {
	public static String token = TokenData.getMangerToken();
	public static String enterpriseId = EnterpriseData.getEnterpriseId();
	public static String enterpriseUrl = EnterpriseDataUrl.getEnterpriseUrl();
	
	public static String addUrl=enterpriseUrl + "v2/"+enterpriseId+"/lecturer_level";
	
	public static String queryListUrl =enterpriseUrl + "v2/"+enterpriseId+"/lecturer_levels";
	
	public static String queryInfoUrl(String id) {
		return enterpriseUrl +"v2/"+enterpriseId+"/lecturer_levels/"+id+"/query";
	}
	

	public static String editUrl(String keyword) {
		return enterpriseUrl +"v2/"+enterpriseId+"/lecturer_levels/"+getIdByKeyword(keyword)+"/update";
	}
	
	public static String deleteUrl = enterpriseUrl+ "v2/"+enterpriseId+"/lecturer_level/batch_delete";
	
	public static String sortUrl (String keyword) {
		return enterpriseUrl +"v2/"+enterpriseId+"/lecturer_levels/"+getIdByKeyword(keyword)+"/update_sort";
	}
	
	/**   
	 * @Title: update_sortLecturerLevel   
	 * @Description: TODO(更新讲师等级排序)   
	 * @param: @param keyword
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String update_sortLecturerLevel(String keyword) {
		return PostRequestTools.RequestBodyByPost("{\r\n" + 
				"  \"source_id\": \""+getIdByKeyword(keyword)+"\", \r\n" + 
				"  \"destination_id\": \""+getSecondIdByKeyword()+"\", \r\n" + 
				"  \"access_token\": \""+token+"\"\r\n" + 
				"}", token, sortUrl(keyword));
	}
	
	/**   
	 * @Title: queryInfo   
	 * @Description: TODO(查询讲师等级详情)   
	 * @param: @param id
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String queryInfo(String id) {
		return GetRequestTools.RequestQueryParamsByGet("access_token", token, queryInfoUrl(id));
	}
	
	
	/**   
	 * @Title: deleteLecturerLevel   
	 * @Description: TODO(根据名称删除讲师等级)   
	 * @param: @param name
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String deleteLecturerLevel(String name) {
		return PostRequestTools.RequestBodyByPost("{\r\n" + 
						"  \"level_ids\": [\r\n" + 
						"    \""+getIdByKeyword(name)+"\"\r\n" + 
						"  ], \r\n" + 
						"  \"access_token\": \""+token+"\"\r\n" + 
						"}", token, deleteUrl);
		
	}
	/**   
	 * @Title: editLecturerLevel   
	 * @Description: TODO(编辑讲师等级)   
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String editLecturerLevel(String keyword,String edit_name,String standard) {
		return PostRequestTools.RequestBodyByPost("{\r\n" + 
				"  \"id\": \""+getIdByKeyword(keyword)+"\", \r\n" + 
				"  \"name\": \""+edit_name+"\", \r\n" + 
				"  \"standard\": \""+standard+"\", \r\n" + 
				"  \"access_token\": \""+token+"\"\r\n" + 
				"}", token, editUrl(keyword));
		
	}
	
	/**   
	 * @Title: queryLecturerLevelList   
	 * @Description: TODO(查询讲师等级列表)   
	 * @param: @param keyword
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String queryLecturerLevelList(String keyword) {
		return GetRequestTools.RequestQueryParamsByGet("access_token", token,"keyword",keyword, queryListUrl);
	}
	
	/**   
	 * @Title: addLecturerLevel   
	 * @Description: TODO(新建讲师等级)   
	 * @param: @param name
	 * @param: @param standard
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String addLecturerLevel(String name,String standard) {
		return PostRequestTools.RequestBodyByPost("{\r\n" + 
				"  \"id\": \"\", \r\n" + 
				"  \"name\": \""+name+"\", \r\n" + 
				"  \"standard\": \""+standard+"\", \r\n" + 
				"  \"access_token\": \""+token+"\"\r\n" + 
				"}", token, addUrl);
	}
	
	public static String addOutsideLecturerLevel(String name,String standard) {
		return PostRequestTools.RequestBodyByPost("{\r\n" + 
				"    \"id\": \"\",\r\n" + 
				"    \"name\": \""+name+"\",\r\n" + 
				"    \"standard\": \""+standard+"\",\r\n" + 
				"    \"type\": \"outside\",\r\n" + 
				"    \"pay_standard\": \"33\",\r\n" + 
				"    \"access_token\": \""+token+"\"\r\n" + 
				"}", token, addUrl);
	}
	
	/**   
	 * @Title: getIdByKeyword   
	 * @Description: TODO(根据关键字查id)   
	 * @param: @param keyword
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String getIdByKeyword(String keyword) {
		String res = queryLecturerLevelList(keyword);
		String id=(String)JSONPath.read(res, "$.list[0].id");
		return id;
	}
	
	/**   
	 * @Title: getIdByKeyword   
	 * @Description: TODO(根据关键字查id)   
	 * @param: @param keyword
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String getSecondIdByKeyword() {
		String res = queryLecturerLevelList("");
		String id=(String)JSONPath.read(res, "$.list[1].id");
		return id;
	}
	
}
