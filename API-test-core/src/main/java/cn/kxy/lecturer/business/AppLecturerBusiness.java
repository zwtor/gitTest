package cn.kxy.lecturer.business;

import cn.kxy.base.business.EnterpriseData;
import cn.kxy.base.business.EnterpriseDataUrl;
import cn.kxy.base.business.TokenData;
import cn.kxy.setting.bussiness.UserBusiness;
import com.alibaba.fastjson.JSONPath;
import com.lazy.assured.utils.GetRequestTools;

public class AppLecturerBusiness {
	public static String token = TokenData.getMangerToken();
	public static String enterpriseId = EnterpriseData.getEnterpriseId();
	public static String appenterpriseUrl = EnterpriseDataUrl.getEnterpriseUrl();
	public static String userId = UserBusiness.getUserId();
	
	public static String lecturersUrl = appenterpriseUrl + "v2/"+enterpriseId+"/lecturers";
	public static String lecturer_levels_url = appenterpriseUrl + "v2/"+enterpriseId+"/lecturer_levels";
	public static String lecturersInfoUrl(String lecturersId) {
		return appenterpriseUrl + "v2/"+enterpriseId+"/lecturers/"+lecturersId+"/homepage";
	}
	
	/**   
	 * @Title: queryAppLecturersInfo   
	 * @Description: TODO(app端查看讲师详情)   
	 * @param: @param lecturersId
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String queryAppLecturersInfo(String lecturersId) {
		return GetRequestTools.RequestQueryParamsByGet("access_token",token,"user_id",userId, lecturersInfoUrl(lecturersId));
		
	}
	
	public static String getIdByKeyword(String keyword) {
		String res = queryAppLecturersList(keyword,"");
		String id = (String)JSONPath.read(res, "$.list[0].id");
		return id;
	}
	
	/**   
	 * @Title: queryApplecturerLevels   
	 * @Description: TODO(查询App讲师等级)   
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String queryApplecturerLevels() {
		return GetRequestTools.RequestQueryParamsByGet("access_token", token, "user_id",userId,lecturer_levels_url);
	}
	
	/**   
	 * @Title: queryAppLecturersList   
	 * @Description: TODO(查询APP端讲师列表)   
	 * @param: @param keyword
	 * @param: @param level
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String queryAppLecturersList(String keyword,String level) {
		return GetRequestTools.RequestQueryParamsByGet("access_token",token,"keyword",keyword, "level",level,
				"user_id",userId,"status","1",lecturersUrl);
	}
	
}
