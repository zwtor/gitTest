package cn.kxy.lecturer.business;

import cn.kxy.base.business.EnterpriseData;
import cn.kxy.base.business.EnterpriseDataUrl;
import cn.kxy.base.business.TokenData;
import com.alibaba.fastjson.JSONPath;
import com.lazy.assured.utils.GetRequestTools;

public class LecturersGracefulBearingBusiness {
	public static String token = TokenData.getMangerToken();
	public static String enterpriseId = EnterpriseData.getEnterpriseId();
	public static String enterpriseUrl = EnterpriseDataUrl.getEnterpriseUrl();
	public static String queryListUrl= enterpriseUrl +"v2/"+enterpriseId+"/lecturers";
	
	public static String queryInfoUrl(String id) {
		return enterpriseUrl + "v2/"+enterpriseId+"/lecturers/"+id+"/homepage";
	}
	
	/**   
	 * @Title: queryInfo   
	 * @Description: TODO(查询讲师详情)   
	 * @param: @param id
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String queryInfo(String id) {
		return GetRequestTools.RequestQueryParamsByGet("access_token", token, queryInfoUrl(id));
	}
	
	/**   
	 * @Title: getIdByKeyword   
	 * @Description: TODO(通过关键字获取id)   
	 * @param: @param name
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String getIdByKeyword(String name) {
		String res = queryList(name,"","");
		String id = (String) JSONPath.read(res, "$.list[0].id");
		return id;
		
	}
	
	/**   
	 * @Title: queryList   
	 * @Description: TODO(查看讲师风采列表)   
	 * @param: @param keyword
	 * @param: @param level
	 * @param: @param label
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String queryList(String keyword,String level,String label) {
		return GetRequestTools.RequestQueryParamsByGet("access_token",token,"keyword",keyword,"level", level,"label",label,"status","1" ,queryListUrl);
	}
}
