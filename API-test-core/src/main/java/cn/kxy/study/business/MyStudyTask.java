package cn.kxy.study.business;

import cn.kxy.base.business.EnterpriseData;
import cn.kxy.base.business.EnterpriseDataUrl;
import cn.kxy.base.business.TokenData;
import cn.kxy.setting.bussiness.UserBusiness;
import com.lazy.assured.utils.GetRequestTools;
import com.lazy.common.utils.DateUtil;

public class MyStudyTask {
	public static String token = TokenData.getMangerToken();
	public static String enterpriseId = EnterpriseData.getEnterpriseId();
	public static String enterpriseUrl = EnterpriseDataUrl.getEnterpriseUrl();
	public static String userId = UserBusiness.getUserId();
	public static String list_url = enterpriseUrl + "plan/myTask/getList";
	public static String queryInfoUrl =enterpriseUrl + "plan/myTask/getOne";
	public static String queryInvestigatesInfoUrl(String id,String investigatesId) {
		return enterpriseUrl +  "v2/"+enterpriseId+"/studies/"+id+"/investigates/"+investigatesId;
	}
	
	public static String queryInvestigatesInfo(String id,String investigatesId) {
		return GetRequestTools.RequestQueryParamsByGet("access_token",token,"study_type","", queryInvestigatesInfoUrl(id,investigatesId)) ;
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
	public static String queryList(String keyword,String status) {
		return GetRequestTools.RequestQueryParamsByGet("access_token",token,"status",status,"pageNumber","1","pageSize","20",
				"sortName","createTime","sortOrder","desc","type","2","timestamp",DateUtil.getCurrentTime(),"keyword",keyword,list_url);
	}
}
