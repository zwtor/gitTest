package cn.kxy.course.resources.bussiness;

import cn.kxy.base.business.EnterpriseData;
import cn.kxy.base.business.EnterpriseDataUrl;
import cn.kxy.base.business.TokenData;
import com.alibaba.fastjson.JSONPath;
import com.lazy.assured.utils.GetRequestTools;
import com.lazy.assured.utils.PostRequestTools;
import com.lazy.httpclient.utils.HttpRequest;
import com.lazy.httpclient.utils.HttpResponse;

public class ArticleBusiness {
	public static String token = TokenData.getMangerToken();
	public static String enterpriseId = EnterpriseData.getEnterpriseId();
	public static String enterpriseUrl = EnterpriseDataUrl.getEnterpriseUrl();
	public static String addUrl = enterpriseUrl +"v2/"+enterpriseId+"/resources/add";
	public static String deleteUrl = enterpriseUrl + "course/resource/delete";
	public static String queryListUrl = enterpriseUrl + "course/resource/getList";
	public static String editUrl (String id) {
		return enterpriseUrl + "v2/"+enterpriseId+"/resources/"+id+"/update";
	}
	
	public static String queryInfoUrl(String id) {
		return enterpriseUrl +"v2/"+enterpriseId+"/resources/"+id+"/query";
	}
	
	/**   
	 * @Title: queryInfo   
	 * @Description: TODO(查看文章详情)   
	 * @param: @param id
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String queryInfo(String id) {
		return GetRequestTools.RequestQueryParamsByGet("access_token", token, queryInfoUrl(id));
	}
	
	/**   
	 * @Title: editArticle   
	 * @Description: TODO(编辑课程)   
	 * @param: @param id
	 * @param: @param name
	 * @param: @param content_type
	 * @param: @param urlcontent
	 * @param: @param content_json
	 * @param: @param resource_classify
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String editArticle(String id,String name,String content_type,String urlcontent,String content_json,String resource_classify) {
		return PostRequestTools.RequestBodyByPost("{\r\n" + 
				"  \"name\": \""+name+"\", \r\n" + 
				"  \"content_type\": \"h5/"+content_type+"\", \r\n" + 
				"  \"url\": \""+urlcontent+"\", \r\n" + 
				"  \"content_json\": \"\", \r\n" + 
				"  \"resource_classify\": \""+resource_classify+"\", \r\n" + 
				"  \"id\": \""+id+"\", \r\n" + 
				"  \"access_token\": \""+token+"\"\r\n" + 
				"}", token, editUrl(id));
	}
	
	/**   
	 * @Title: addArticle   
	 * @Description: TODO(新增图文文章)   
	 * @param: @param name
	 * @param: @param text
	 * @param: @param resource_classify
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String addArticle(String name,String text,String resource_classify) {
		return  HttpResponse.postJson(ArticleBusiness.addUrl, "{\"name\":\""+name+"\",\"content_type\":\"h5/article\","
				+ "\"url\":\"\",\"content_json\":\"{\\\"blocks\\\":[{\\\"key\\\":\\\"6lo5l\\\",\\\"text\\\":\\\""+text+"\\\","
				+ "\\\"type\\\":\\\"unstyled\\\",\\\"depth\\\":0,\\\"inlineStyleRanges\\\":[],\\\"entityRanges\\\":[],\\\"data\\\":{}}],"
				+ "\\\"entityMap\\\":{}}\",\"resource_classify\":\""+resource_classify+"\","
				+ "\"access_token\":\""+token+"\"}", "access_token", token);
	}
	
	/**   
	 * @Title: getIdByKeyword   
	 * @Description: TODO(通过关键字获取文章的id)   
	 * @param: @param keyword
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String getIdByKeyword(String keyword,String resourceClassify) {
		String res = queryArticleList(keyword,resourceClassify);
		String id = (String)JSONPath.read(res, "$.list[0].id");
		return id;
	}
	
	public static String getFirstIdByKeyword() {
		String res = queryArticleListInit("","0","application");
		String id = (String)JSONPath.read(res, "$.list[0].id");
		return id;
	}
	
	public static String getSecondIdByKeyword() {
		String res = queryArticleListInit("","0","application");
		String id = (String)JSONPath.read(res, "$.list[1].id");
		return id;
	}
	
	public static String getSevenIdByKeyword() {
		String res = queryArticleListInit("","0","application");
		String id = (String)JSONPath.read(res, "$.list[5].id");
		return id;
	}
	
	/**   
	 * @Title: queryList   
	 * @Description: TODO(根据关键字进行查询)   
	 * @param: @param keyword
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String queryArticleList(String keyword,String resourceClassify ) {
		return GetRequestTools.RequestQueryParamsByGet("resourceClassify",resourceClassify,"access_token",token,"keyword",keyword,"parentId","0",
				"pageNumber","1","pageSize","20","courseFlag","1",queryListUrl);
	}
	public static String queryArticleListInit(String keyword,String resourceClassify ,String contentType) {
		return GetRequestTools.RequestQueryParamsByGet("resourceClassify",resourceClassify,"access_token",token,"keyword",keyword,
				"pageNumber","1","pageSize","20","contentType",contentType,queryListUrl);
	}
	
	public static String getIdByKeyword(String keyword) {
		String res =GetRequestTools.RequestQueryParamsByGet("resourceClassify","0","access_token",token,"keyword",keyword,
				"pageNumber","1","pageSize","20","contentType","application","excludeType","h5,zip,rar",queryListUrl);
		String id = (String)JSONPath.read(res, "$.list[0].id");
		return id;
	}
	
	public static String getImageId() {
		String res= HttpRequest.get(enterpriseUrl + "course/queryCourseByPage?type=&title=&classifyId=&queryType=0&pageNumber=1&pageSize=12&scope=0&"
				+ "filterQuota=false&useType=&courseType=course&image_text=true&access_token="+token).send().body();
		String id = (String)JSONPath.read(res, "$.list[0].id");
		return id;
	}
	
	/**   
	 * @Title: deleteArticle   
	 * @Description: TODO(根据ids删除文章)   
	 * @param: @param ids
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String deleteArticle(String ids) {
		return PostRequestTools.RequestFormDataByPost(token, "ids", ids, deleteUrl);
	}
	
	/**   
	 * @Title: addUrlArticle   
	 * @Description: TODO(新增带URL的文章)   
	 * @param: @param name
	 * @param: @param urlcontent
	 * @param: @param resource_classify
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String addArticle(String name,String content_type,String urlcontent,String content_json,String resource_classify) {
		return PostRequestTools.RequestBodyByPost("{\r\n" + 
				"  \"name\": \""+name+"\", \r\n" + 
				"  \"content_type\": \"h5/"+content_type+"\", \r\n" + 
				"  \"url\": \""+urlcontent+"\", \r\n" + 
				"  \"content_json\": \""+content_json+"\", \r\n" + 
				"  \"resource_classify\": \""+resource_classify+"\", \r\n" + 
				"  \"access_token\": \""+token+"\"\r\n" + 
				"}", token, addUrl);
		
	}
	
	public static String addArticle1(String name,String content_type,String urlcontent,String content_json,String resource_classify) {
		return PostRequestTools.RequestBodyByPost("{\r\n" + 
				"  \"name\": \""+name+"\", \r\n" + 
				"  \"content_type\": \""+content_type+"\", \r\n" + 
				"  \"url\": \""+urlcontent+"\", \r\n" + 
				"  \"content_json\": \""+content_json+"\", \r\n" + 
				"  \"resource_classify\": \""+resource_classify+"\", \r\n" + 
				"  \"access_token\": \""+token+"\"\r\n" + 
				"}", token, addUrl);
		
	}
	
}
