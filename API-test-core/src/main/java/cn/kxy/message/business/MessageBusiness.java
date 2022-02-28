package cn.kxy.message.business;

import cn.kxy.base.business.EnterpriseData;
import cn.kxy.base.business.EnterpriseDataUrl;
import cn.kxy.base.business.TokenData;
import cn.kxy.setting.bussiness.UserBusiness;
import com.alibaba.fastjson.JSONPath;
import com.lazy.httpclient.utils.HttpRequest;

public class MessageBusiness {
	public static String message_url=  EnterpriseDataUrl.getMessageUrl();
	public static String token = TokenData.getMangerToken();
	public static String enterprise_id=EnterpriseData.getEnterpriseId();
	public static String user_id = UserBusiness.getUserId();
	
	public static String message_list_url = message_url + "v2/enterpriseId/"+enterprise_id+"/userid/"+user_id+"/message_list";
	
	public static String message_count_url = message_url + "v2/enterpriseId/"+enterprise_id+"/userid/"+user_id+"/read_num_count";
	
	public static String message_type_count_url = message_url + "v2/enterpriseId/"+enterprise_id+"/userid/"+user_id+"/count";
	
	public static String delete_message_url = message_url + "v2/enterpriseId/"+enterprise_id+"/userid/"+user_id+"/batch_del";
	
	
	public static String  read_message_url = message_url + "v2/enterpriseId/"+enterprise_id+"/userid/"+user_id+"/batch_read";
	
	public static String deleteMessage() {
		
		String res =HttpRequest.get(message_url + "v2/enterpriseId/"+enterprise_id+"/userid/"+user_id+"/message_list").query("isRead","2").query("type","0")
			.query("page_size","20").query("visible","false").query("access_token",token).send().body();
		
			String id_1 = (String)JSONPath.read(res, "$.list[0].msg_id");
			String id_2 = (String)JSONPath.read(res, "$.list[1].msg_id");
			String id_3 = (String)JSONPath.read(res, "$.list[2].msg_id");
			String id_4 = (String)JSONPath.read(res, "$.list[3].msg_id");
			String id_5 = (String)JSONPath.read(res, "$.list[4].msg_id");
			String id_6 = (String)JSONPath.read(res, "$.list[5].msg_id");
			String id_7 = (String)JSONPath.read(res, "$.list[6].msg_id");
			String id_8 = (String)JSONPath.read(res, "$.list[7].msg_id");
			String id_9 = (String)JSONPath.read(res, "$.list[8].msg_id");
			String id_10 = (String)JSONPath.read(res, "$.list[10].msg_id");
			String id_11 = (String)JSONPath.read(res, "$.list[11].msg_id");
			String id_12 = (String)JSONPath.read(res, "$.list[12].msg_id");
			String id_20 = (String)JSONPath.read(res, "$.list[9].msg_id");
			String id_13 = (String)JSONPath.read(res, "$.list[13].msg_id");
			String id_14 = (String)JSONPath.read(res, "$.list[14].msg_id");
			String id_15 = (String)JSONPath.read(res, "$.list[15].msg_id");
			String id_16 = (String)JSONPath.read(res, "$.list[16].msg_id");
			String id_17 = (String)JSONPath.read(res, "$.list[17].msg_id");
			String id_18 = (String)JSONPath.read(res, "$.list[18].msg_id");
			String id_19 = (String)JSONPath.read(res, "$.list[19].msg_id");
		
			return HttpRequest.post(message_url+"/v2/enterpriseId/"+enterprise_id+"/userid/"+user_id+"/batch_del").query("access_token", token)
					.data("{\r\n" + 
							"  \"ids\":[\r\n" + 
							"    \""+id_1+"\",\r\n" + 
							"    \""+id_2+"\",\r\n" + 
							"    \""+id_3+"\",\r\n" + 
							"    \""+id_4+"\",\r\n" + 
							"    \""+id_5+"\",\r\n" + 
							"    \""+id_6+"\",\r\n" + 
							"    \""+id_7+"\",\r\n" + 
							"    \""+id_8+"\",\r\n" + 
							"    \""+id_9+"\",\r\n" + 
							"    \""+id_10+"\",\r\n" + 
							"    \""+id_11+"\",\r\n" + 
							"    \""+id_12+"\",\r\n" + 
							"    \""+id_13+"\",\r\n" + 
							"    \""+id_14+"\",\r\n" + 
							"    \""+id_15+"\",\r\n" + 
							"    \""+id_16+"\",\r\n" + 
							"    \""+id_17+"\",\r\n" + 
							"    \""+id_18+"\",\r\n" +
							"    \""+id_19+"\",\r\n" + 
							"    \""+id_20+"\"],\r\n" + 
							"  \"access_token\":\""+token+"\"\r\n" + 
							"}").send().body();
		}
	
	/**   
	 * @Title: readMessage   读取消息
	 * @Description: TODO
	 * @param: @param id
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */  
	public static String readMessage(String id) {
		return HttpRequest.post(read_message_url).query("access_token", token).data("{\"ids\":[\""+id+"\"],\"access_token\":\""+token+"\"}")
				.send().body();
	}
	/**   
	 * @Title: deleteMessage   
	 * @Description: TODO  标记消息为已读
	 * @param: @param id
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */  
	public static String deleteMessage(String id) {
		return HttpRequest.post(delete_message_url).query("access_token", token).data("{\"ids\":[\""+id+"\"],\"access_token\":\""+token+"\"}")
				.send().body();
	}
	
	/**   
	 * @Title: queryMessageTypeCount   
	 * @Description: TODO  查看不同类型的数量
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */  
	public static String queryMessageTypeCount() {
		return HttpRequest.get(message_type_count_url).query("access_token", token).send().body();
	}
	
	/**   
	 * @Title: queryMessageCount   
	 * @Description: TODO  查看message个数
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */  
	public static String queryMessageCount() {
		return HttpRequest.get(message_count_url).query("access_token", token).send().body();
	}
	
	
	/**   
	 * @Title: queryMessageList   
	 * @Description: TODO  查看消息列表
	 * @param: @param isRead
	 * @param: @param type
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */  
	public static String queryMessageList(String isRead,String type) {
		return HttpRequest.get(message_list_url).query("isRead", isRead).query("type", type).query("page_size", "20").query("visible", "false")
				.query("access_token", token).send().body();
	}
	
}
