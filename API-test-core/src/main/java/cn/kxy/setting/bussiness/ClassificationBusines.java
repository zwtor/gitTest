package cn.kxy.setting.bussiness;

import cn.kxy.base.business.EnterpriseDataUrl;
import cn.kxy.base.business.TokenData;
import com.alibaba.fastjson.JSONPath;
import com.lazy.assured.utils.GetRequestTools;
import com.lazy.assured.utils.PostRequestTools;

public class ClassificationBusines {
	
	public static String classificaUrl= EnterpriseDataUrl.getEnterpriseUrl();

	public static String AddUrl = classificaUrl + "admin/classify/add";

	public static String EditUrl = classificaUrl + "admin/classify/edit";

	public static String isReferenceUrl = classificaUrl + "admin/classify/isReference";

	public static String DeleteUrl = classificaUrl + "admin/classify/delete";

	public static String QueryUrl = classificaUrl + "admin/classify/getTree";

	public static String DragUrl = classificaUrl + "admin/classify/drag_drop";

	public static String token = TokenData.getMangerToken();
	
	// 新增一级分类
	public static String addPrimaryClassification(String name) {
		return PostRequestTools.RequestFormDataByPost(token, "name", name, AddUrl);

	}
	// 新增二级分类
	public static String addSecondaryClassification(String name, String parentId) {
		return PostRequestTools.RequestFormDataByPost(token, "name", name, "parentId",
				parentId, AddUrl);
	}

	// 重命名分类
	public static String renameClassification(String name, String Id) {
		return PostRequestTools.RequestFormDataByPost(token, "name", name, "id", Id, EditUrl);

	}
	
	

	// 询问是否删除分类
	public static String isReferenceClassification(String Id) {
		return GetRequestTools.RequestQueryParamsByGet("access_token", token, "id", Id, isReferenceUrl);
	}

	// 删除分类
	public static String deleteClassification(String id) {
		return PostRequestTools.RequestFormDataByPost(token, "id", id, DeleteUrl);

	}

	// 查询分类

	public static String queryClassification() {
		return GetRequestTools.RequestQueryParamsByGet("parentId", 0, "access_token", token, QueryUrl);

	}
	
	/**   
	 * @Title: queryClassification   
	 * @Description: TODO  根据类型查询分类
	 * @param: @param queryType
	 * @param: @param unclassified
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */  
	public static String queryClassification(String queryType,String unclassified) {
		return GetRequestTools.RequestQueryParamsByGet("queryType", queryType,"unclassified",unclassified, "access_token", token, QueryUrl);

	}

	// 拖曳分类

	public static String dragClassification(String classify_drag, String parent_id, String base_id, String type) {
		return PostRequestTools.RequestBodyByPost("{\"classify_drag\":[\"" + classify_drag + "\"],\"classify_drop\":"
				+ "{\"parent_id\":" + parent_id + ",\"base_id\":\"" + base_id + "\",\"type\":\"" + type
				+ "\"},\"access_token\":\"" + token + "\"}", token, DragUrl);

	}
	
	//获取一级分类的name
	public static String getPrimaryName() {
		String body =ClassificationBusines.queryClassification();
		String name=(String) JSONPath.read(body, "$[0].name");
		return name;
		
	}
	//获取一级分类的id
	
	public static String getPrimaryId() {
		String body =ClassificationBusines.queryClassification();
		String id=(String) JSONPath.read(body, "$[0].id");
		return id;
		
	}
	//获取一级第二个分类id
	public static String getPrimarySecondId() {
		String body =ClassificationBusines.queryClassification();
		String id=(String) JSONPath.read(body, "$[1].id");
		return id;
		
	}
	//获取二级分类的name
	public static String getSecondaryName() {
		String body =ClassificationBusines.queryClassification();
		String name=(String) JSONPath.read(body, "$[0].children[0].name");
		return name;
		
	}
	//获取二级分类的id
		public static String getSecondaryId() {
			String body =ClassificationBusines.queryClassification();
			String name=(String) JSONPath.read(body, "$[0].children[0].id");
			return name;
			
		}
	

}
