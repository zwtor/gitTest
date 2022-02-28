package cn.kxy.community.group;

import cn.kxy.base.business.EnterpriseData;
import cn.kxy.base.business.EnterpriseDataUrl;
import cn.kxy.base.business.TokenData;
import cn.kxy.setting.bussiness.UserBusiness;
import com.lazy.assured.utils.PostRequestTool;
import com.lazy.httpclient.utils.HttpRequest;

public class ArticleManageBusiness {
	public static String group_url=  EnterpriseDataUrl.getGroupUrl();
	public static String token = TokenData.getMangerToken();
	public static String enterprise_id=EnterpriseData.getEnterpriseId();
	public static String user_id = UserBusiness.getUserId();
	
	public static String search_url  = group_url + "search";
	
	public static String queryArcticleInfoUrl(String id) {
		return group_url + "article/"+id;
	}
	
	public static String queryArcticleCommentUrl(String id) {
		return group_url + "article/"+id+"/comment";
	}
	public static String commentArticleUrl(String id) {
		return group_url + "article/"+id+"/comment";
	}
	
	public static String commentArticleUrl(String id,String comment_id) {
		return group_url + "article/"+id+"/comment/"+comment_id;
	}
	
	public static String starArticleUrl(String id) {
		return group_url + "article/star/"+id;
	}
	
	public static String unstarArticleUrl(String id) {
		return group_url + "article/"+id+"/unstar";
	}
	
	public static String unstarArticle(String id) {
		return HttpRequest.post(unstarArticleUrl(id)).header("x-access-token",token).query("access-token",token).
				data("{\"access_token\":\""+token+"\"}").send().body();
	}
	
	public static String starArticle(String id) {
		return HttpRequest.post(starArticleUrl(id)).header("x-access-token",token).query("access-token",token).
				data("{\"access_token\":\""+token+"\"}").send().body();
	}
	
	public static String commentArticle(String id) {
		
		return PostRequestTool.post(commentArticleUrl(id), "{\"content\":\"this is a comment\","
				+ "\"userName\":\"\",\"access_token\":\""+token+"\"}", token);
		
//		return HttpRequest.post(commentArticleUrl(id)).header("x-access-token",token).query("access-token",token).data("{\"content\":\"this is a comment\","
//				+ "\"userName\":\"\",\"access_token\":\""+token+"\"}").send().body();
	}
	
	public static String deleteCommentArticle(String id,String comment_id) {
		return HttpRequest.delete(commentArticleUrl(id,comment_id)).header("x-access-token",token).query("access-token",token)
				.data("{\"access_token\":\""+token+"\"}").send().body();
	}
	
	public static String replyCommentArticle(String id,String comment_id) {
		return HttpRequest.post(commentArticleUrl(id,comment_id)).header("x-access-token",token).query("access-token",token).data("{\r\n" + 
				"  \"content\":\"reply\",\r\n" + 
				"  \"userId\":\""+user_id+"\",\r\n" + 
				"  \"userName\":\"张伦\",\r\n" + 
				"  \"access_token\":\""+token+"\"\r\n" + 
				"}\r\n" + 
				"").send().body();
	}
	
	/**   
	 * @Title: queryArticleByCircle   
	 * @Description: TODO   查询圈子下的文章列表
	 * @param: @param spaceId
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */  
	public static String queryArticleByCircle(String spaceId,String type) {
		return HttpRequest.get(group_url + type).header("x-access-token",token).query("page", "0").query("size","20").
				query("spaceId",spaceId).query("access-token",token).send().body();
	}
	
	public static String searchDiffList(String keyword,String t) {
		return HttpRequest.get(search_url).header("x-access-token",token).query("page", "0").query("size","20").query("t",t)
				.query("text",keyword).send().body();
	}
	
	public static String queryArticleComment(String id) {
		return HttpRequest.get(queryArcticleCommentUrl(id)).header("x-access-token",token).query("page", "0").query("size","20")
				.query("access-token",token).send().body();
	}
	
	public static String deleteArtcicle(String id,String is_admin) {
		return HttpRequest.delete(queryArcticleInfoUrl(id)).header("x-access-token",token).query("admin",is_admin).query("access-token",token)
				.send().body();
	}
	
	public static String queryArcticleInfo(String id) {
		return HttpRequest.get(queryArcticleInfoUrl(id)).header("x-access-token",token).query("access-token",token).send().body();
	}
}
