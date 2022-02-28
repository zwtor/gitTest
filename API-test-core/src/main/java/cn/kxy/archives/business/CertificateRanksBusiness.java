package cn.kxy.archives.business;

import cn.kxy.base.business.EnterpriseData;
import cn.kxy.base.business.EnterpriseDataUrl;
import cn.kxy.base.business.TokenData;
import cn.kxy.setting.bussiness.UserBusiness;
import com.lazy.assured.utils.GetRequestTools;
import com.lazy.httpclient.utils.HttpResponse;

public class CertificateRanksBusiness {
	public static String enterpriseUrl=  EnterpriseDataUrl.getEnterpriseUrl();
	public static String token = TokenData.getMangerToken();
	public static String enterpriseId=EnterpriseData.getEnterpriseId();
	public static String userId = UserBusiness.getUserId();
	
	public static String certificate_ranks_url = enterpriseUrl + "v2/"+enterpriseId+"/certificate_ranks";
	
	public static String certificate_details_url =enterpriseUrl + "v2/"+enterpriseId+"/certificate_details";
	
	public static String certificate_ranks_export_url = enterpriseUrl + "v2/"+enterpriseId+"/certificate_ranks_export";
	
	public static int getExportCertificateCodeByTime(String begin_time,String end_time) {
		return HttpResponse.getstatusCode(certificate_ranks_export_url, token, "begin_time",begin_time,"end_time",end_time);
	}
	
	/**   
	 * @Title: getExportCertificateCodeByUsername   
	 * @Description: TODO(导出证书排行榜的数据)   
	 * @param: @return      
	 * @return: int      
	 * @throws   
	 */ 
	public static int getExportCertificateCodeByUsername() {
		return HttpResponse.getstatusCode(certificate_ranks_export_url, token, "keyword", UserBusiness.getUsername());
	}
	/**   
	 * @Title: queryCertificateDetails   
	 * @Description: TODO(查看证书详情)   
	 * @param: @param source_type
	 * @param: @param begin_time
	 * @param: @param end_time
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String queryCertificateDetails(String source_type,String begin_time,String end_time) {
		return GetRequestTools.RequestQueryParamsByGet("source_type", source_type,"user_id",userId,"begin_time",begin_time,
				"end_time",end_time,"page_number","1","page_size","20","access_token",token, certificate_details_url);
	}
	
	/**   
	 * @Title: getCertificateRankList   
	 * @Description: TODO(查看证书列表)   
	 * @param: @param keyword
	 * @param: @param begin_time
	 * @param: @param end_time
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String getCertificateRankList(String keyword,String begin_time,String end_time) {
		return GetRequestTools.RequestQueryParamsByGet("keyword",keyword,"department_id","1","begin_time",begin_time,
				"end_time",end_time,"page_number","1","page_size","20","access_token",token,certificate_ranks_url);
	}
	
}
