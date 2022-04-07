package cn.kxy.setting.bussiness;

import cn.kxy.base.business.EnterpriseData;
import cn.kxy.base.business.EnterpriseDataUrl;
import cn.kxy.base.business.TokenData;
import com.lazy.assured.utils.GetRequestTools;
import com.lazy.httpclient.utils.HttpRequest;
import io.restassured.RestAssured;
import io.restassured.config.SSLConfig;

import static io.restassured.RestAssured.given;

public class EnterpriseSettingBusiness {
	public static String token = TokenData.getMangerToken();
	public static String enterpriseId = EnterpriseData.getEnterpriseId();
	public static String enterpriseUrl = EnterpriseDataUrl.getEnterpriseUrl();
	public static String userId = UserBusiness.getUserId();

	public static String setUrl = enterpriseUrl + "profile/setConfig";

	public static String getUrl = enterpriseUrl + "profile/getConfig";

	public static String online_customer_service_switch_url = enterpriseUrl  + "v2/"+enterpriseId+"/settings/online_customer_service_switch";

	public static String sync_standard_url =  enterpriseUrl + "v2/"+enterpriseId+"/multi_terminal_login/sync_standard";

	public static String home_customize_url = enterpriseUrl + "v2/"+enterpriseId+"/home_customize/departments";

	public static String query_index_score_rank_url = enterpriseUrl + "v2/"+enterpriseId+"/query_index_score_rank";

	public static String home_lecturers_url = enterpriseUrl +  "v2/"+enterpriseId+"/home_lecturers";

	public static String queryHomeLecturers() {
		return HttpRequest.get(home_lecturers_url).query("page_number","1").query("page_size", "4").
				query("status","1").query("access_token", token).send().body();
	}


	public static String queryIndexScoreRank() {
		return HttpRequest.get(query_index_score_rank_url).query("user_id",userId).query("access_token", token).send().body();
	}

	public static String  queryHomeCustomize() {
		return HttpRequest.get(home_customize_url).query("type","pc").query("access_token", token).send().body();
	}


	public static String  queryCstomerServiceSwitch() {
		return HttpRequest.get(online_customer_service_switch_url).query("access_token", token).send().body();
	}

	public static String  querySyncStandard() {
		return HttpRequest.get(sync_standard_url).query("access_token", token).send().body();
	}


	/**   
	 * @Title: getEnterpriseSetting   
	 * @Description: TODO(获取企业设置)   
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String getEnterpriseSetting() {
		return GetRequestTools.RequestQueryParamsByGet("access_token", token, getUrl);
	}

	
	/**   
	 * @Title: saveEnterpriseSetting   
	 * @Description: TODO(企业设置)   
	 * @param: @param id
	 * @param: @param logo
	 * @param: @param name
	 * @param: @param mobile
	 * @param: @param urlMobile
	 * @param: @param dingCorpId
	 * @param: @param urlQRCode
	 * @param: @param desc_qrcode
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String saveEnterpriseSetting(String id, String logo, String name, String mobile, String urlMobile,
			String dingCorpId, String urlQRCode, String desc_qrcode) {
		return given().config((RestAssured.config().sslConfig(new SSLConfig().relaxedHTTPSValidation())))
				.queryParam("id", id).queryParam("logo", logo).queryParam("name", name).queryParam("mobile", mobile)
				.queryParam("urlMobile", urlMobile).queryParam("dingCorpId", dingCorpId)
				.queryParam("urlQRCode", urlQRCode).queryParam("desc_qrcode", desc_qrcode)
				.queryParam("access_token", token).body("{\"access_token\":\"" + token + "\"}").post(setUrl).asString();
	}

}
