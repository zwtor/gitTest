package cn.kxy.investigationresearch.business;

import cn.kxy.base.business.EnterpriseData;
import cn.kxy.base.business.EnterpriseDataUrl;
import cn.kxy.base.business.TokenData;
import cn.kxy.setting.bussiness.ClassificationBusines;
import com.alibaba.fastjson.JSONPath;
import com.lazy.assured.utils.GetRequestTools;
import com.lazy.assured.utils.PostRequestTools;
import com.lazy.httpclient.utils.HttpRequest;
import com.lazy.httpclient.utils.HttpResponse;
import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.util.EntityUtils;

import java.io.IOException;


public class QuestionnaireBusiness {
	public static String token = TokenData.getMangerToken();
	public static String enterpriseId = EnterpriseData.getEnterpriseId();
	public static String enterpriseUrl = EnterpriseDataUrl.getEnterpriseUrl();
	public static String addUrl=enterpriseUrl+"v2/"+enterpriseId+"/questionnaire";
	public static String queryListUrl = enterpriseUrl + "v2/" + enterpriseId + "/questionnaires";
	
	public static String batch_update_visible_url = enterpriseUrl + "v2/"+enterpriseId+"/questionnaire/batch_update_visible";
	
	public static String cancelUrl(String name, String status) {
		return enterpriseUrl + "v2/" + enterpriseId + "/questionnaire/" + getIdByKeyword(name, status) + "/cancel";
	}
	
	public static String cancelUrl(String id) {
		return enterpriseUrl + "v2/" + enterpriseId + "/questionnaire/" + id+ "/cancel";
	}
	
	
	public static String publicUrl(String name, String status) {
		return enterpriseUrl + "v2/" + enterpriseId + "/questionnaire/" + getIdByKeyword(name, status) + "/release";
	}
	
	public static String queryInfoUrl(String name, String status) {
		return enterpriseUrl+"v2/"+enterpriseId+"/questionnaire/"+getIdByKeyword(name, status);
	}
	
	public static String queryInfoUrl(String id) {
		return enterpriseUrl+"v2/"+enterpriseId+"/questionnaire/"+id;
	}
	
	public static String deleteUrl(String name, String status) {
		return enterpriseUrl + "v2/" + enterpriseId + "/questionnaire/" + getIdByKeyword(name, status) + "/delete";
	}
	
	public static String deleteUrl(String id) {
		return enterpriseUrl + "v2/" + enterpriseId + "/questionnaire/" + id+ "/delete";
	}
	
	public static String editUrl(String id) {
		return enterpriseUrl + "v2/" + enterpriseId + "/questionnaire/" +id + "/edit";
	}

	public static String copyUrl(String id) {
		return enterpriseUrl + "v2/questionnaire/"+id+"/copy";
	}
	
	public static String setVisible(String id,String visible_range,String user_id) {
		return PostRequestTools.RequestBodyByPost("{\r\n" + 
				"  \"ids\": \""+id+"\", \r\n" + 
				"  \"visible_range\": \""+visible_range+"\", \r\n" + 
				"  \"department_ids\": \"\", \r\n" + 
				"  \"group_ids\": \"\", \r\n" + 
				"  \"post_ids\": \"\", \r\n" + 
				"  \"user_ids\": \""+user_id+"\", \r\n" + 
				"  \"access_token\": \""+token+"\"\r\n" + 
				"}", token, batch_update_visible_url);
	}

	public static String copyQuestionnaire(String id) {
		return HttpRequest.get(copyUrl(id)).query("access_token", token).send().body();
	}
	
	public static String queryInfo(String name, String status) {
		return GetRequestTools.RequestQueryParamsByGet("access_token", token, queryInfoUrl(name, status));
	}
	
	public static String queryInfo(String id) {
		return GetRequestTools.RequestQueryParamsByGet("access_token", token, queryInfoUrl(id));
	}
	
	
	public static String editQuestionnaire(String title,String description,String type,String id) {
		return PostRequestTools.RequestBodyByPost("{\r\n" + 
				"  \"title\": \""+title+"\", \r\n" + 
				"  \"course_classifys\": [\r\n" + 
				"    \""+ClassificationBusines.getPrimaryId()+"\"\r\n" + 
				"  ], \r\n" + 
				"  \"description\": \""+description+"\", \r\n" + 
				"  \"type\": \""+type+"\", \r\n" + 
				"  \"questions\": [\r\n" + 
				"    {\r\n" + 
				"      \"title\": \"can you use selenium\", \r\n" + 
				"      \"type\": \"single\", \r\n" + 
				"      \"order_id\": 1, \r\n" + 
				"      \"must\": \"true\", \r\n" + 
				"      \"other\": true, \r\n" + 
				"      \"options\": [\r\n" + 
				"        {\r\n" + 
				"          \"order_id\": 1, \r\n" + 
				"          \"title\": \"yes\", \r\n" + 
				"          \"other\": false\r\n" + 
				"        }, \r\n" + 
				"        {\r\n" + 
				"          \"order_id\": 2, \r\n" + 
				"          \"title\": \"no\", \r\n" + 
				"          \"other\": false\r\n" + 
				"        }, \r\n" + 
				"        {\r\n" + 
				"          \"order_id\": 3, \r\n" + 
				"          \"title\": \"其他\", \r\n" + 
				"          \"other\": true\r\n" + 
				"        }\r\n" + 
				"      ]\r\n" + 
				"    }\r\n" + 
				"  ], \r\n" + 
				"  \"id\": \""+id+"\", \r\n" + 
				"  \"access_token\": \"b7d84a3ba75dfe6cdd81af6ccff8a4d9\"\r\n" + 
				"}", token, editUrl(id));
	}
	
	public static String addQuestionnaire(String title,String must_one ,String must_two,String must_three,String must_four,String multiple_rows) {
		return PostRequestTools.RequestBodyByPost("{\r\n" + 
				"  \"title\": \""+title+"\", \r\n" + 
				"  \"course_classifys\": [\r\n" + 
				"    \""+ClassificationBusines.getPrimaryId()+"\"\r\n" + 
				"  ], \r\n" + 
				"  \"description\": \"Questionnaire Description\", \r\n" + 
				"  \"type\": \"release\", \r\n" + 
				"  \"questions\": [\r\n" + 
				"    {\r\n" + 
				"      \"title\": \"can you use selenium\", \r\n" + 
				"      \"type\": \"single\", \r\n" + 
				"      \"order_id\": 1, \r\n" + 
				"      \"must\": \""+must_one+"\", \r\n" + 
				"      \"other\": true, \r\n" + 
				"      \"options\": [\r\n" + 
				"        {\r\n" + 
				"          \"order_id\": 1, \r\n" + 
				"          \"title\": \"yes\", \r\n" + 
				"          \"other\": false\r\n" + 
				"        }, \r\n" + 
				"        {\r\n" + 
				"          \"order_id\": 2, \r\n" + 
				"          \"title\": \"no\", \r\n" + 
				"          \"other\": false\r\n" + 
				"        }, \r\n" + 
				"        {\r\n" + 
				"          \"order_id\": 3, \r\n" + 
				"          \"title\": \"其他\", \r\n" + 
				"          \"other\": true\r\n" + 
				"        }\r\n" + 
				"      ]\r\n" + 
				"    }, \r\n" + 
				"    {\r\n" + 
				"      \"title\": \"how do you thoink about httpclient\", \r\n" + 
				"      \"type\": \"multiple\", \r\n" + 
				"      \"order_id\": 2, \r\n" + 
				"      \"must\": \""+must_two+"\", \r\n" + 
				"      \"other\": false, \r\n" + 
				"      \"options\": [\r\n" + 
				"        {\r\n" + 
				"          \"order_id\": 1, \r\n" + 
				"          \"title\": \"very good\", \r\n" + 
				"          \"other\": false\r\n" + 
				"        }, \r\n" + 
				"        {\r\n" + 
				"          \"order_id\": 2, \r\n" + 
				"          \"title\": \"just so so\", \r\n" + 
				"          \"other\": false\r\n" + 
				"        }, \r\n" + 
				"        {\r\n" + 
				"          \"order_id\": 3, \r\n" + 
				"          \"title\": \"perfect\", \r\n" + 
				"          \"other\": false\r\n" + 
				"        }\r\n" + 
				"      ]\r\n" + 
				"    }, \r\n" + 
				"    {\r\n" + 
				"      \"title\": \"List selenium method\", \r\n" + 
				"      \"type\": \"input\", \r\n" + 
				"      \"order_id\": 3, \r\n" + 
				"      \"must\": \""+must_three+"\", \r\n" + 
				"      \"multiple_rows\": \""+multiple_rows+"\"\r\n" + 
				"    }, \r\n" + 
				"    {\r\n" + 
				"      \"title\": \"What's your score for selenium\", \r\n" + 
				"      \"type\": \"score\", \r\n" + 
				"      \"order_id\": 4, \r\n" + 
				"      \"must\": \""+must_four+"\"\r\n" + 
				"    }\r\n" + 
				"  ], \r\n" + 
				"  \"access_token\": \"b7d84a3ba75dfe6cdd81af6ccff8a4d9\"\r\n" + 
				"}", token, addUrl);
	}
	
	
	/**   
	 * @Title: addNormalQuestionnaire   
	 * @Description: TODO(新增常规问卷，供流程使用)   
	 * @param: @param title
	 * @param: @param description
	 * @param: @param type
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	
	
	public static String addNormalQuestionnaire(String title,String description,String type) {
		return PostRequestTools.RequestBodyByPost("{\r\n" + 
				"  \"title\": \""+title+"\", \r\n" + 
				"  \"course_classifys\": [\r\n" + 
				"    \""+ClassificationBusines.getPrimaryId()+"\"\r\n" + 
				"  ], \r\n" + 
				"  \"description\": \""+description+"\", \r\n" + 
				"  \"type\": \""+type+"\", \r\n" + 
				"  \"questions\": [\r\n" + 
				"    {\r\n" + 
				"      \"title\": \"can you use selenium\", \r\n" + 
				"      \"type\": \"single\", \r\n" + 
				"      \"order_id\": 1, \r\n" + 
				"      \"must\": \"true\", \r\n" + 
				"      \"other\": true, \r\n" + 
				"      \"options\": [\r\n" + 
				"        {\r\n" + 
				"          \"order_id\": 1, \r\n" + 
				"          \"title\": \"yes\", \r\n" + 
				"          \"other\": false\r\n" + 
				"        }, \r\n" + 
				"        {\r\n" + 
				"          \"order_id\": 2, \r\n" + 
				"          \"title\": \"no\", \r\n" + 
				"          \"other\": false\r\n" + 
				"        }, \r\n" + 
				"        {\r\n" + 
				"          \"order_id\": 3, \r\n" + 
				"          \"title\": \"other thing\", \r\n" + 
				"          \"other\": true\r\n" + 
				"        }\r\n" + 
				"      ]\r\n" + 
				"    }\r\n" + 
				"  ], \r\n" + 
				"  \"access_token\": \""+token+"\"\r\n" + 
				"}", token, addUrl);
	}
	
	/**   
	 * @Title: queryLsit   
	 * @Description: TODO(查看问卷调查列表)   
	 * @param: @param keyword
	 * @param: @param create_status
	 * @param: @param course_classify
	 * @param: @param status
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String queryLsit(String keyword, String create_status, String course_classify, String status) {
		HttpResponse res=HttpRequest.get(queryListUrl).query("keyword", keyword).query("create_status", create_status).
				query("course_classify", course_classify).query("status",status).query("access_token", token).
				query("page_size", "20").query("page_number", "1").send();
		HttpEntity resEntity = res.response().getEntity();  
		String msg = "" ;
        try {
			 msg = EntityUtils.toString(resEntity, "utf-8");
		} catch (ParseException | IOException e) {
			e.printStackTrace();
		}
		return msg;  
	}
	/**   
	 * @Title: cancelQuestionnaire   
	 * @Description: TODO(取消发布)   
	 * @param: @param name
	 * @param: @param status
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String cancelQuestionnaire(String name, String status) {
		return PostRequestTools.RequestBodyByPost("{\"access_token\":\""+token+"\"}", token, cancelUrl(name, status));
	}
	
	public static String cancelQuestionnaire(String id) {
		return PostRequestTools.RequestBodyByPost("{\"access_token\":\""+token+"\"}", token, cancelUrl(id));
	}
	
	/**   
	 * @Title: cancelQuestionnaire   
	 * @Description: TODO(发布问卷)   
	 * @param: @param name
	 * @param: @param status
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String publicQuestionnaire(String name, String status) {
		return PostRequestTools.RequestBodyByPost("{\"access_token\":\""+token+"\"}", token, publicUrl(name, status));

	}
	/**   
	 * @Title: cancelQuestionnaire   
	 * @Description: TODO(取消发布)   
	 * @param: @param name
	 * @param: @param status
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String deleteQuestionnaire(String name, String status) {
		return PostRequestTools.RequestBodyByPost("{\"access_token\":\""+token+"\"}", token, deleteUrl(name, status));

	}
	
	public static String deleteQuestionnaire(String id) {
		return PostRequestTools.RequestBodyByPost("{\"access_token\":\""+token+"\"}", token, deleteUrl(id));

	}
	/**   
	 * @Title: getIdByKeyword   
	 * @Description: TODO(根据关键字和状态（草稿箱、已发布）获取id)   
	 * @param: @param name
	 * @param: @param status
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String getIdByKeyword(String name, String status) {
		String res = queryLsit(name, "all", "", status);
		String id = (String) JSONPath.read(res, "$.questionnaires.list[0].id");
		return id;
	}
	
	/**   
	 * @Title: addNormalQuestionnaire   
	 * @Description: TODO(新增常规问卷，供流程使用)   
	 * @param: @param title
	 * @param: @param description
	 * @param: @param type
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	
	
	public static String addNormalQuestionnaire(String title,String course_classifys,String description,String type) {
		return PostRequestTools.RequestBodyByPost("{\r\n" + 
				"  \"title\": \""+title+"\", \r\n" + 
				"  \"course_classifys\": [\r\n" + 
				"    \""+course_classifys+"\"\r\n" + 
				"  ], \r\n" + 
				"  \"description\": \""+description+"\", \r\n" + 
				"  \"type\": \""+type+"\", \r\n" + 
				"  \"questions\": [\r\n" + 
				"    {\r\n" + 
				"      \"title\": \"can you use selenium\", \r\n" + 
				"      \"type\": \"single\", \r\n" + 
				"      \"order_id\": 1, \r\n" + 
				"      \"must\": \"true\", \r\n" + 
				"      \"other\": true, \r\n" + 
				"      \"options\": [\r\n" + 
				"        {\r\n" + 
				"          \"order_id\": 1, \r\n" + 
				"          \"title\": \"yes\", \r\n" + 
				"          \"other\": false\r\n" + 
				"        }, \r\n" + 
				"        {\r\n" + 
				"          \"order_id\": 2, \r\n" + 
				"          \"title\": \"no\", \r\n" + 
				"          \"other\": false\r\n" + 
				"        }, \r\n" + 
				"        {\r\n" + 
				"          \"order_id\": 3, \r\n" + 
				"          \"title\": \"other thing\", \r\n" + 
				"          \"other\": true\r\n" + 
				"        }\r\n" + 
				"      ]\r\n" + 
				"    }\r\n" + 
				"  ], \r\n" + 
				"  \"access_token\": \""+token+"\"\r\n" + 
				"}", token, addUrl);
	}
}
