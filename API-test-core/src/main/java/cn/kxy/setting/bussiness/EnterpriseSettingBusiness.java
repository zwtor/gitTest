package cn.kxy.setting.bussiness;

import cn.kxy.base.business.EnterpriseData;
import cn.kxy.base.business.EnterpriseDataUrl;
import cn.kxy.base.business.TokenData;
import com.lazy.assured.utils.GetRequestTools;
import io.restassured.RestAssured;
import io.restassured.config.SSLConfig;

import static io.restassured.RestAssured.given;

public class EnterpriseSettingBusiness {
	public static String token = TokenData.getMangerToken();
	public static String enterpriseId = EnterpriseData.getEnterpriseId();
	public static String enterpriseUrl = EnterpriseDataUrl.getEnterpriseUrl();

	public static String setUrl = enterpriseUrl + "profile/setConfig";

	public static String getUrl = enterpriseUrl + "profile/getConfig";

	
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
