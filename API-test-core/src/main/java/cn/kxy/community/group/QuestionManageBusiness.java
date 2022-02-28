package cn.kxy.community.group;

import cn.kxy.base.business.EnterpriseData;
import cn.kxy.base.business.EnterpriseDataUrl;
import cn.kxy.base.business.TokenData;
import cn.kxy.setting.bussiness.UserBusiness;
import com.lazy.assured.utils.PostRequestTool;
import com.lazy.httpclient.utils.HttpRequest;
import okhttp3.*;

import java.io.IOException;


public class QuestionManageBusiness {
	public static String group_url=  EnterpriseDataUrl.getGroupUrl();
	public static String token = TokenData.getMangerToken();
	public static String enterprise_id=EnterpriseData.getEnterpriseId();
	public static String user_id = UserBusiness.getUserId();
	
	public static String search_url  = group_url + "search";
	
	public static String list_url =group_url +  "question";
	
	public static String queryQuestionInfoUrl(String id) {
		return group_url + "question/"+id;
	}
	
	public static String queryAnswerUrl(String id) {
		return group_url + "question/"+id+"/answer";
	}
	
	public static String invitationAnswerUrl(String id) {
		return group_url + "question/"+id+"/invitation";
	}
	public static String answerUrl (String question_id) {
		return group_url + "question/"+question_id+"/answer";
	}
	
	public static String deleteAnswerUrl(String question_id,String answer_id) {
		return group_url + "question/"+question_id+"/answer/"+answer_id;
	}
	
	public static String starAnswerUrl(String question_id,String answer_id,String type) {
		return group_url + "question/"+question_id+"/answer/"+answer_id+"/"+type;
	}
	
	public static String invitationAnswer (String question_id){
		return PostRequestTool.post(invitationAnswerUrl(question_id),
				"{\"departIds\":[],\"userIds\":[\""+user_id+"\"],\"access_token\":\""+token+"\"}", token);
	}
	
	/**   
	 * @Title: starAnswer   
	 * @Description: TODO   对回答进行点赞  取消点赞
	 * @param: @param question_id
	 * @param: @param answer_id
	 * @param: @param type  点赞：star 取消点赞： unstar
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */  
	public static String starAnswer(String question_id,String answer_id,String type) {
		
		return PostRequestTool.post(starAnswerUrl(question_id, answer_id, type),
				"{\"access_token\":\""+token+"\"}", token);
	}
	
	/**   
	 * @Title: deleteAnswer   
	 * @Description: TODO  删除回答
	 * @param: @param question_id
	 * @param: @param answer_id
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */  
	public static String deleteAnswer(String question_id,String answer_id) {
		return HttpRequest.delete(deleteAnswerUrl(question_id,answer_id)).header("x-access-token",token).
				query("access-token",token).data("{\"access_token\":\""+token+"\"}").send().body();
	}
	
	/**   
	 * @Title: answerQuestion   
	 * @Description: TODO  回答问题
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */  
	public static String answerQuestion(String id) {
		
		OkHttpClient client = new OkHttpClient();
		final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
		@SuppressWarnings("deprecation")
		RequestBody body = RequestBody.create(JSON, "{\r\n" + 
				"  \"content\":\"{\\\"type\\\":\\\"pc\\\",\\\"content\\\":\\\"{\\\\\\\"blocks\\\\\\\":[{\\\\\\\"key\\\\\\\":\\\\\\\"cppbg\\\\\\\",\\\\\\\"text\\\\\\\":\\\\\\\"这是一个回答\\\\\\\",\\\\\\\"type\\\\\\\":\\\\\\\"unstyled\\\\\\\",\\\\\\\"depth\\\\\\\":0,\\\\\\\"inlineStyleRanges\\\\\\\":[],\\\\\\\"entityRanges\\\\\\\":[],\\\\\\\"data\\\\\\\":{}}],\\\\\\\"entityMap\\\\\\\":{}}\\\"}\",\r\n" + 
				"  \"userId\":\""+user_id+"\",\r\n" + 
				"  \"userName\":\""+UserBusiness.getUsername()+"\",\r\n" + 
				"  \"access_token\":\""+token+"\"\r\n" + 
				"}");
		Request request = new Request.Builder().url(answerUrl(id)).addHeader("x-access-token", token).post(body).build();
		String result="";
		try {
			Response response = client.newCall(request).execute();
			result = response.body().string();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return result ;
		
		
	}
	
	/**   
	 * @Title: queryList   
	 * @Description: TODO
	 * @param: @param type_url  不同列表页：圈子--space/1   文章--article  问答--question  pc端热门 --all  问答--qa
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */  
	public static String queryList(String type_url,String text) {
		return HttpRequest.get(group_url + type_url).header("x-access-token",token).query("page", "0").query("size","20")
				.query("access-token",token).query("text",text).send().body();
	}
	
	public static String quertQuestionList(String keyword) {
		return HttpRequest.get(search_url).header("x-access-token",token).query("page", "0").query("size","20").query("t","QUESTION")
				.query("text",keyword).send().body();
	}
	
	public static String queryAnswer(String id) {
		return HttpRequest.get(queryAnswerUrl(id)).header("x-access-token",token).query("page", "0").query("size","20")
				.query("access-token",token).send().body();
	}
	
	public static String deleteQuestion(String id,String is_admin) {
		return HttpRequest.delete(queryQuestionInfoUrl(id)).header("x-access-token",token).query("admin",is_admin).query("access-token",token)
				.send().body();
	}
	
	public static String queryQuestionInfo(String id) {
		return HttpRequest.get(queryQuestionInfoUrl(id)).header("x-access-token",token).query("access-token",token).send().body();
	}
}
