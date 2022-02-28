package cn.kxy.authentication.business;

import cn.kxy.base.business.EnterpriseData;
import cn.kxy.base.business.EnterpriseDataUrl;
import cn.kxy.base.business.TokenData;
import cn.kxy.setting.bussiness.UserBusiness;
import com.alibaba.fastjson.JSONPath;
import com.lazy.assured.utils.GetRequestTools;
import com.lazy.assured.utils.PostRequestTools;
import com.lazy.httpclient.utils.HttpRequest;

public class CertificateBusiness {

	public static String enterpriseUrl=  EnterpriseDataUrl.getEnterpriseUrl();
	
	public static String token = TokenData.getMangerToken();
	
	public static String enterpriseId=EnterpriseData.getEnterpriseId();
	
	public static String userId = UserBusiness.getUserId();
	
	public static String queryTemplateUrl = enterpriseUrl + "v2/"+enterpriseId+"/certificate_templates/query";

	public static String addUrl = enterpriseUrl + "v1/certificate/add";

	public static String queryListUrl = enterpriseUrl + "certificate/getList";

	public static String getUserbyIdUrl = enterpriseUrl + "certificate/getUserbyId";
	
	public static String modifyStatusUrl = enterpriseUrl + "certificate/modifyStatus";
	
	public static String editUrl = enterpriseUrl + "v1/certificate/edit";
	
	public static String awardUrl = enterpriseUrl + "v2/"+enterpriseId+"/certificates/save";

	public static String queryInfoUrl = enterpriseUrl+"certificate/getOne";
	
	public static String revokeUrl(String certificatesId) {
		return enterpriseUrl + "v2/" + enterpriseId + "/certificates/" + certificatesId
				+ "/users/delete";
	}
	
	public static String deleteUrl(String id) {
		return enterpriseUrl + "v2/"+enterpriseId+"/certificate/"+id+"/delete";
	}
	
	/**   
	 * @Title: deleteCret   
	 * @Description: TODO  删除证书
	 * @param: @param id
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */  
	public static String deleteCret(String id) {
		return HttpRequest.post(deleteUrl(id)).
				query("access_token", token).data("{\"access_token\":\""+token+"\"}")
				.send().body();
	}

	/**   
	 * @Title: awardCertificate   
	 * @Description: TODO(颁发证书)   
	 * @param: @param certificate_Id
	 * @param: @param certificate_reason
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String awardCertificate(String certificate_Id,String certificate_reason) {
		return PostRequestTools.RequestBodyByPost("{\r\n" + 
				"  \"certificate_Id\": \""+certificate_Id+"\", \r\n" + 
				"  \"certificate_reason\": \""+certificate_reason+"\", \r\n" + 
				"  \"user_ids\": \""+userId+"\", \r\n" + 
				"  \"access_token\": \""+token+"\"\r\n" + 
				"}", token, awardUrl);
	}
	
	public static String queryInfo(String id ) {
		return GetRequestTools.RequestQueryParamsByGet("id", id,"access_token",token, queryInfoUrl);
	}
	
	/**   
	 * @Title: queryTemplate   
	 * @Description: TODO(查询证书模板)   
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String queryTemplate() {
		return GetRequestTools.RequestQueryParamsByGet("access_token", token,"page_number","1","page_size","20", queryTemplateUrl);
	}

	/**   
	 * @Title: editCertificate   
	 * @Description: TODO(编辑证书)   
	 * @param: @param id
	 * @param: @param name
	 * @param: @param certificateCode
	 * @param: @param companyName
	 * @param: @param useCommonSeal
	 * @param: @param languageType
	 * @param: @param templateId
	 * @param: @param status
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String editCertificate(String id,String name, String certificateCode, String companyName, String useCommonSeal,
			String languageType, String templateId ) {
		
		return 	HttpRequest.post(editUrl).query("access_token",token).form("certificateCode", certificateCode).form("companyName", companyName)
				.form("expireDate", "0").form("imageUrl", "https://oss.coolcollege.cn/1789887239308840960.png").form("languageType", languageType)
				.form("logoUrl","https://oss.coolcollege.cn/1789887239308840960.png").form("message", "no_message").form("messageProperties", 
						"{\"countdown_date\":\"1\",\"created\":\"false\",\"user\":\"false\"}").form("name",name).form("permanentlyValid", "permanent")
				.form("summary", "兹证明通过认证考核，特颁发此证").form("templateId", "1").form("id", id).form("display", "{\r\n" + 
						"  \"summary\":\"true\",\r\n" + 
						"  \"commonSeal\":\""+useCommonSeal+"\",\r\n" + 
						"  \"certificateCode\":\"true\",\r\n" + 
						"  \"issueTime\":\"true\",\r\n" + 
						"  \"name\":\"true\",\r\n" + 
						"  \"department\":\"true\",\r\n" + 
						"  \"logoUrl\":\"true\",\r\n" + 
						"  \"expire\":\"true\"\r\n" + 
						"}\r\n" + 
						"").send().body();
	}
	
	/**   
	 * @Title: modifyCertificateStatus   
	 * @Description: TODO(停用或者启用证书)   
	 * @param: @param certificateId
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String modifyCertificateStatus(String certificateId) {
		return PostRequestTools.RequestFormDataByPost(token, "id", certificateId, modifyStatusUrl);
	}
	
	public static String editCertificate(String id,String name,String summary, String certificateCode, String companyName, String useCommonSeal,
			String languageType, String templateId,String is_summary,String is_commonSeal,String is_certificateCode
			,String is_issueTime,String is_name,String is_department,String is_logoUrl) {
		return 	HttpRequest.post(editUrl).query("access_token",token).form("certificateCode", certificateCode).form("companyName", companyName)
				.form("expireDate", "0").form("imageUrl", "https://oss.coolcollege.cn/1789887239308840960.png").form("languageType", languageType)
				.form("logoUrl","https://oss.coolcollege.cn/1789887239308840960.png").form("message", "no_message").form("messageProperties", 
						"{\"countdown_date\":\"1\",\"created\":\"false\",\"user\":\"false\"}").form("name",name).form("permanentlyValid", "permanent")
				.form("summary", summary).form("templateId", templateId).form("id", id).form("display", "{\r\n" + 
						"  \"summary\":\""+is_summary+"\",\r\n" + 
						"  \"commonSeal\":\""+is_commonSeal+"\",\r\n" + 
						"  \"certificateCode\":\""+is_certificateCode+"\",\r\n" + 
						"  \"issueTime\":\""+is_issueTime+"\",\r\n" + 
						"  \"name\":\""+is_name+"\",\r\n" + 
						"  \"department\":\""+is_department+"\",\r\n" + 
						"  \"logoUrl\":\""+is_logoUrl+"\",\r\n" + 
						"  \"expire\":\"true\"\r\n" + 
						"}\r\n" + 
						"").send().body();
	}
	
	/**   
	 * @Title: creatCertificate   
	 * @Description: TODO   证书优化的新增证书方法
	 * @param: @param name
	 * @param: @param summary
	 * @param: @param certificateCode
	 * @param: @param companyName
	 * @param: @param useCommonSeal
	 * @param: @param languageType
	 * @param: @param templateId
	 * @param: @param is_summary
	 * @param: @param is_commonSeal
	 * @param: @param is_certificateCode
	 * @param: @param is_issueTime
	 * @param: @param is_name
	 * @param: @param is_department
	 * @param: @param is_logoUrl
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */  
	public static String creatCertificate(String name,String summary, String certificateCode, String companyName, String useCommonSeal,
			String languageType, String templateId,String is_summary,String is_commonSeal,String is_certificateCode
			,String is_issueTime,String is_name,String is_department,String is_logoUrl) {
		
		return 	HttpRequest.post(addUrl).query("access_token",token).form("certificateCode", certificateCode).form("companyName", companyName)
				.form("expireDate", "0").form("imageUrl", "https://oss.coolcollege.cn/1789887239308840960.png").form("languageType", languageType)
				.form("logoUrl","https://oss.coolcollege.cn/1789887239308840960.png").form("message", "no_message").form("messageProperties", 
						"{\"countdown_date\":\"1\",\"created\":\"false\",\"user\":\"false\"}").form("name",name).form("permanentlyValid", "permanent")
				.form("summary", summary).form("templateId", templateId).form("display", "{\r\n" + 
						"  \"summary\":\""+is_summary+"\",\r\n" + 
						"  \"commonSeal\":\""+is_commonSeal+"\",\r\n" + 
						"  \"certificateCode\":\""+is_certificateCode+"\",\r\n" + 
						"  \"issueTime\":\""+is_issueTime+"\",\r\n" + 
						"  \"name\":\""+is_name+"\",\r\n" + 
						"  \"department\":\""+is_department+"\",\r\n" + 
						"  \"logoUrl\":\""+is_logoUrl+"\",\r\n" + 
						"  \"expire\":\"true\"\r\n" + 
						"}\r\n" + 
						"").send().body();
		
		/*return PostRequestTools.RequestFormDataByPost(token, "name", name, "summary",summary,"certificateCode",
				certificateCode, "companyName", companyName, "logoUrl",
				"https://oss.coolcollege.cn/1789887239308840960.png", "imageUrl",
				"https://oss.coolcollege.cn/1791044561745874944.png", "useCommonSeal", useCommonSeal, "languageType",
				languageType, "templateId", templateId,"display","{\r\n" + 
						"  \"summary\": \""+is_summary+"\", \r\n" + 
						"  \"commonSeal\": \""+is_commonSeal+"\", \r\n" + 
						"  \"certificateCode\": \""+is_certificateCode+"\", \r\n" + 
						"  \"issueTime\": \""+is_issueTime+"\", \r\n" + 
						"  \"name\": \""+is_name+"\", \r\n" + 
						"  \"department\": \""+is_department+"\", \r\n" + 
						"  \"logoUrl\": \""+is_logoUrl+"\"\r\n" + 
						"}", addUrl);*/
	}
	
	/**   
	 * @Title: creatCertificate   
	 * @Description: TODO( 创建证书)   
	 * @param: @param name
	 * @param: @param certificateCode
	 * @param: @param companyName
	 * @param: @param useCommonSeal
	 * @param: @param languageType
	 * @param: @param templateId
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String creatCertificate(String name, String certificateCode, String companyName, String useCommonSeal,
			String languageType, String templateId) {
		
		return 	HttpRequest.post(addUrl).query("access_token",token).form("certificateCode", certificateCode).form("companyName", companyName)
		.form("expireDate", "0").form("imageUrl", "https://oss.coolcollege.cn/1789887239308840960.png").form("languageType", languageType)
		.form("logoUrl","https://oss.coolcollege.cn/1789887239308840960.png").form("message", "no_message").form("messageProperties", 
				"{\"countdown_date\":\"1\",\"created\":\"false\",\"user\":\"false\"}").form("name",name).form("permanentlyValid", "permanent")
		.form("summary", "兹证明通过认证考核，特颁发此证").form("templateId", "1").form("display", "{\r\n" + 
				"  \"summary\":\"true\",\r\n" + 
				"  \"commonSeal\":\""+useCommonSeal+"\",\r\n" + 
				"  \"certificateCode\":\"true\",\r\n" + 
				"  \"issueTime\":\"true\",\r\n" + 
				"  \"name\":\"true\",\r\n" + 
				"  \"department\":\"true\",\r\n" + 
				"  \"logoUrl\":\"true\",\r\n" + 
				"  \"expire\":\"true\"\r\n" + 
				"}\r\n" + 
				"").send().body();
		
//		return PostRequestTools.RequestFormDataByPost(token, "name", name, "certificateCode",
//				certificateCode, "companyName", companyName, "logoUrl",
//				"https://oss.coolcollege.cn/1789887239308840960.png", "imageUrl",
//				"https://oss.coolcollege.cn/1791044561745874944.png", "useCommonSeal", useCommonSeal, "languageType",
//				languageType, "templateId", templateId, addUrl);
	}

	/**   
	 * @Title: queryCertificateList   
	 * @Description: TODO 查询证书列表()   
	 * @param: @param keyword
	 * @param: @param status
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String queryCertificateList(String keyword, String status) {
		return GetRequestTools.RequestQueryParamsByGet("keyword", keyword, "status", status, "pageNumber", "1",
				"pageSize", "20", "access_token", token, queryListUrl);
	}

	
	public static String queryCertificateList(String keyword, String status,String pageNum,String pageSize) {
		return GetRequestTools.RequestQueryParamsByGet("keyword", keyword, "status", status, "pageNumber", pageNum,
				"pageSize", pageSize, "access_token", token, queryListUrl);
	}
	
	// 根据keyword获取证书id
	public static String getIdByKeyword(String keyword) {
		String res = queryCertificateList(keyword, "");
		String id = (String) JSONPath.read(res, "$.list[0].id");
		return id;
	}

	/**   
	 * @Title: getTotalByKeyword   
	 * @Description: TODO(根据keyword获取证书总数)   
	 * @param: @param keyword
	 * @param: @return      
	 * @return: int      
	 * @throws   
	 */ 
	public static int getTotalByKeyword(String keyword) {
		String res = queryCertificateList(keyword, "");
		int total = (int) JSONPath.read(res, "$.total");
		return total;
	}

	/**   
	 * @Title: getCertificateForPeopleList   
	 * @Description: TODO(通过单个证书Id，查询获取证书的人数列表)   
	 * @param: @param id
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String getCertificateForPeopleList(String id) {
		return GetRequestTools.RequestQueryParamsByGet("id", id, "access_token", token, "type","validity",getUserbyIdUrl);
	}
	
	public static String  getIdByCertificateForPeople(String id) {
		String res =  getCertificateForPeopleList(id);
		String ids = (String)JSONPath.read(res, "$.list[0].id");
		return ids;
	}

	/**   
	 * @Title: getSingleCertificateReceiveTotal   
	 * @Description: TODO(单个证书的获取人数)   
	 * @param: @param name
	 * @param: @return      
	 * @return: int      
	 * @throws   
	 */ 
	public static int getSingleCertificateReceiveTotal(String name) {
		String res = getCertificateForPeopleList(getIdByKeyword(name));
		int total = (int) JSONPath.read(res, "$.total");
		return total;
	}
	/**   
	 * @Title: revokeCertificate   
	 * @Description: TODO(撤销证书)   
	 * @param: @param name
	 * @param: @param id
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String revokeCertificate(String certificate_record_ids, String id) {
		
		return PostRequestTools.RequestBodyByPost(
				"{\"certificate_record_ids\":[\"" + certificate_record_ids + "\"],\"access_token\":\"" + token + "\"}",
				token, revokeUrl(id));
	}

}
