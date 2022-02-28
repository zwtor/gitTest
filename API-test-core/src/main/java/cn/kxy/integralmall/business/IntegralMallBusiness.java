package cn.kxy.integralmall.business;

import cn.kxy.base.business.EnterpriseData;
import cn.kxy.base.business.EnterpriseDataUrl;
import cn.kxy.base.business.TokenData;
import cn.kxy.setting.bussiness.UserBusiness;
import com.lazy.assured.utils.GetRequestTools;
import com.lazy.assured.utils.PostRequestTools;

public class IntegralMallBusiness {
	public static String token = TokenData.getMangerToken();
	public static String enterpriseId = EnterpriseData.getEnterpriseId();
	public static String enterpriseUrl = EnterpriseDataUrl.getEnterpriseUrl();
	public static String username = UserBusiness.getUsername();
	public static String userId = UserBusiness.getUserId();
	public static String query_list_url =enterpriseUrl + "v2/"+enterpriseId+"/integral_mall/goods";

	public static String queryInfoUrl(String id) {
		return enterpriseUrl + "v2/"+enterpriseId+"/integral_mall/goods/"+id;
	}
	
	public static String exchangeDetailUrl(String id) {
		return enterpriseUrl+"v2/"+enterpriseId+"/integral_mall/goods/"+id+"/orders";
	}
	
	public static String addUrl =enterpriseUrl + "v2/"+enterpriseId+"/integral_mall/good";
	
	public static String updateStatusUrl (String id) {
		return enterpriseUrl + "v2/"+enterpriseId+"/integral_mall/goods/"+id+"/status";
	}
	public static String deleteUrl(String id) {
		return enterpriseUrl + "v2/"+enterpriseId+"/users/"+userId+"/goods/"+id+"/delete";
	}
	
	public static String deleteGoods(String id) {
		return PostRequestTools.RequestBodyByPost("{\"access_token\":\""+token+"\"}", token, deleteUrl( id));
	}
	
	public static String goodInfoUrl (String id) {
		return  enterpriseUrl + "v2/"+enterpriseId+"/integral_mall/goods/"+id;
	}
	public static String check_good_name_url = enterpriseUrl + "v2/1067985194709028888/integral_mall/check_good_name";
	
	/**   
	 * @Title: editGoods   
	 * @Description: TODO(编辑商品)   
	 * @param: @param id
	 * @param: @param name
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String editGoods(String id,String name) {
		return PostRequestTools.RequestBodyByPost("{\r\n" + 
				"  \"name\": \""+name+"\", \r\n" + 
				"  \"image\": \"\", \r\n" + 
				"  \"description\": \"des<p></p>\", \r\n" + 
				"  \"score\": 2, \r\n" + 
				"  \"repeat_exchange\": false, \r\n" + 
				"  \"inventory\": 20, \r\n" + 
				"  \"expire_time\": \"\", \r\n" + 
				"  \"is_time_limited_str\": \"false\", \r\n" + 
				"  \"deliver_user_name\": \"Allen\", \r\n" + 
				"  \"deliver_user_contact\": \"16666668686\", \r\n" + 
				"  \"receive_address\": \"xian\", \r\n" + 
				"  \"scopes\": {\r\n" + 
				"    \"department_list\": [ ], \r\n" + 
				"    \"user_list\": [\r\n" + 
				"      {\r\n" + 
				"        \"id\": \""+userId+"\", \r\n" + 
				"        \"name\": \""+username+"\"\r\n" + 
				"      }\r\n" + 
				"    ], \r\n" + 
				"    \"group_list\": [ ], \r\n" + 
				"    \"post_list\": [ ]\r\n" + 
				"  }, \r\n" + 
				"  \"access_token\": \"86a14eb20ef08e4a6d2e72574a5d94c4\"\r\n" + 
				"}", token, goodInfoUrl(id));
	}
	
	/**   
	 * @Title: checkGoodName   
	 * @Description: TODO(检查商品名称)   
	 * @param: @param id
	 * @param: @param name
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String checkGoodName(String id,String name) {
		return GetRequestTools.RequestQueryParamsByGet("operation", "edit", "access_token", token, "good_name",name,
				"id",id,check_good_name_url);
	}
	
	/**   
	 * @Title: queryGoodInfo   
	 * @Description: TODO(查看商品详情)   
	 * @param: @param id
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String queryGoodInfo (String id) {
		return GetRequestTools.RequestQueryParamsByGet("access_token", token, goodInfoUrl(id));
	}
	
	
	
	/**   
	 * @Title: queryMyOrderRecords   
	 * @Description: TODO(查看兑换明细)   
	 * @param: @param status (not_receive--未兑换   received--已兑换)
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String queryExchangeDetail(String status,String id,String keyword) {
		return GetRequestTools.RequestQueryParamsByGet("access_token", token,"keyword",keyword, 
				"page_size", "10","page_number","1","department_id","1","status",status, exchangeDetailUrl(id));
	}
	/**   
	 * @Title: updateStatus   
	 * @Description: TODO(更新状态)   
	 * @param: @param status (down--下架，up--上架)
	 * @param: @param id
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String updateStatus(String status,String id) {
		return PostRequestTools.RequestBodyByPost("{\"access_token\":\""+token+"\"}", token, "status", status, updateStatusUrl(id));
	}
	
	/**   
	 * @Title: addRepeatExchangeGoods   
	 * @Description: TODO(添加商品)   
	 * @param: @param name
	 * @param: @param score
	 * @param: @param inventory
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String addRepeatExchangeGoods(String name,String score,String inventory) {
		return PostRequestTools.RequestBodyByPost("{\r\n" + 
				"  \"name\": \""+name+"\", \r\n" + 
				"  \"image\": \"\", \r\n" + 
				"  \"description\": \"des<p></p>\", \r\n" + 
				"  \"score\": \""+score+"\", \r\n" + 
				"  \"repeat_exchange\": true, \r\n" + 
				"  \"inventory\": \""+inventory+"\", \r\n" + 
				"  \"expire_time\": \"\", \r\n" + 
				"  \"is_time_limited_str\": \"false\", \r\n" + 
				"  \"deliver_user_name\": \""+"Allen"+"\", \r\n" + 
				"  \"deliver_user_contact\": \"16666668686\", \r\n" + 
				"  \"receive_address\": \"xian\", \r\n" + 
				"  \"scopes\": {\r\n" + 
				"    \"department_list\": [ ], \r\n" + 
				"    \"user_list\": [\r\n" + 
				"      {\r\n" + 
				"        \"id\": \""+userId+"\", \r\n" + 
				"        \"name\": \""+username+"\"\r\n" + 
				"      }\r\n" + 
				"    ], \r\n" + 
				"    \"group_list\": [ ], \r\n" + 
				"    \"post_list\": [ ]\r\n" + 
				"  }, \r\n" + 
				"  \"access_token\": \""+token+"\"\r\n" + 
				"}", token, addUrl);
	}
	
	/**   
	 * @Title: queryIntegralMallInfo   
	 * @Description: TODO(查看商品详情)   
	 * @param: @param id
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String queryIntegralMallInfo(String id) {
		return GetRequestTools.RequestQueryParamsByGet("access_token",token, queryInfoUrl(id));
	}
	
	/**   
	 * @Title: exchangeDetail   
	 * @Description: TODO(查看兑换明细received--已领取，not_receive--未领取)   
	 * @param: @param status
	 * @param: @param keyword
	 * @param: @param department_id
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String exchangeDetail(String status,String keyword,String department_id) {
		return GetRequestTools.RequestQueryParamsByGet("department_id",department_id,"page_number", "1","page_size","20","status",status,
				"keyword",keyword,"access_token",token,query_list_url);
	}
	
	/**   
	 * @Title: queryIntegralMallList   
	 * @Description: TODO(查看积分商城列表)   
	 * @param: @param status
	 * @param: @param keyword
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String queryIntegralMallList(String status,String keyword) {
		return GetRequestTools.RequestQueryParamsByGet("page_number", "1","page_size","20","status",status,
				"keyword",keyword,"access_token",token,query_list_url);
	}
}
