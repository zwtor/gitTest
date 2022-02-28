package cn.kxy.integralmall.business;

import cn.kxy.base.business.EnterpriseData;
import cn.kxy.base.business.EnterpriseDataUrl;
import cn.kxy.base.business.TokenData;
import cn.kxy.setting.bussiness.UserBusiness;
import com.lazy.assured.utils.GetRequestTools;
import com.lazy.httpclient.utils.HttpRequest;

public class AppIntegralMallBusiness {
	public static String token = TokenData.getMangerToken();
	public static String enterprise_id = EnterpriseData.getEnterpriseId();
	public static String enterpriseUrl = EnterpriseDataUrl.getEnterpriseUrl();
	public static String username = UserBusiness.getUsername();
	public static String user_id = UserBusiness.getUserId();
	
	public static String goods_url = enterpriseUrl + "v2/"+enterprise_id+"/users/"+user_id+"/integral_mall/goods";
	
	public static String my_remain_score_url =enterpriseUrl + "v2/"+enterprise_id+"/users/"+user_id+"/my_remain_score";
	
	public static String queryGoodsInfoUrl(String id) {
		return enterpriseUrl + "v2/"+enterprise_id+"/users/"+user_id+"/integral_mall/goods/"+id+"/detail";
	}
	
	public static String exchangeGoodsUrl(String id) {
		return enterpriseUrl + "v2/"+enterprise_id+"/users/"+user_id+"/integral_mall/goods/"+id+"/order";
	}
	
	public static String scan_qrcode_url(String order_id) {
		return enterpriseUrl + "v2/"+enterprise_id+"/users/"+user_id+"/integral_mall/orders/"+order_id+"/scan_qrcode";
	}
	
	public static String my_order_records_url = enterpriseUrl + "v2/"+enterprise_id+"/users/"+user_id+"/integral_mall/my_order_records";
	
	/**   
	 * @Title: queryMyOrderRecords   
	 * @Description: TODO(查看我的兑换记录)   
	 * @param: @param status (not_receive--未兑换   received--已兑换)
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String queryMyOrderRecords(String status) {
		return GetRequestTools.RequestQueryParamsByGet("access_token", token,"enterprise_id", enterprise_id, "user_id",user_id, 
				"page_size", "10","page_number","1","status",status, my_order_records_url);
	}
	
	/**   
	 * @Title: scanQrcode   
	 * @Description: TODO(兑换奖品)   
	 * @param: @param order_id
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String scanQrcode(String order_id) {
		 String res = HttpRequest.post(scan_qrcode_url(order_id)).query("enterprise_id", enterprise_id).query("user_id", user_id).
				 query("access_token", token).contentType("application/json;charset=UTF-8").data("{\r\n" + 
				 		"	\"deliver_user\": \""+user_id+"\"\r\n" + 
				 		"}")
					.send().body();
		 return res;
	}
	
	/**   
	 * @Title: exchangeGoods   
	 * @Description: TODO(兑换商品)   
	 * @param: @param id
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String exchangeGoods(String id) {
		 String res = HttpRequest.post(exchangeGoodsUrl(id)).query("enterprise_id", enterprise_id).query("user_id", user_id).
				 query("access_token", token).contentType("application/json;charset=UTF-8")
					.send().body();
		 return res;
	}
	
	/**   
	 * @Title: queryGoodsInfo   
	 * @Description: TODO(查看移动端积分详情)   
	 * @param: @param id
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String queryGoodsInfo(String id) {
		return GetRequestTools.RequestQueryParamsByGet("access_token", token,"enterprise_id", enterprise_id, "user_id",user_id, queryGoodsInfoUrl(id));
	}
	
	
	/**   
	 * @Title: queryMyRemainScore   
	 * @Description: TODO(查看app积分)   
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String queryMyRemainScore() {
		return GetRequestTools.RequestQueryParamsByGet("access_token", token,"enterprise_id", enterprise_id, "user_id",user_id, my_remain_score_url);
	}
	
	/**   
	 * @Title: queryGoodList   
	 * @Description: TODO(查看积分商城列表)   
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String queryGoodList() {
		return GetRequestTools.RequestQueryParamsByGet("access_token", token,"page_number","1","page_size","10",
				"enterprise_id", enterprise_id, "user_id",user_id, goods_url);
	}
	
	
}
