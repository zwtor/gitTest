package cn.kxy.examination.business;

import cn.kxy.base.business.EnterpriseData;
import cn.kxy.base.business.ExamDataUrl;
import cn.kxy.base.business.TokenData;
import com.lazy.assured.utils.PostRequestTools;
import com.lazy.httpclient.utils.HttpRequest;

public class PaperExportBusiness {
	public static String exam_url= ExamDataUrl.getNewExamUrl();
	public static String token = TokenData.getMangerToken();
	public static String enterprise_id = EnterpriseData.getEnterpriseId();
	public static String export_url = exam_url + "paper/export/blank_paper";
	public static String export_record_composecount_url = exam_url + "v2/enterprises/"+enterprise_id+"/export_record/composeCount";
	public static String export_record_list_url = exam_url +"v2/enterprises/"+enterprise_id+"/export_record/list";
	
	public static String deleteRecordUrl (String id) {
		return exam_url + "v2/enterprises/"+enterprise_id+"/export_record/"+id+"/delete";
	}
	
	public static String delete_all_record_url = exam_url + "v2/enterprises/"+enterprise_id+"/export_record/deleteAll";
	
	/**   
	 * @Title: deleteAllRecord   
	 * @Description: TODO(清除空白试卷的所有记录)   
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */  
	public static String deleteAllRecord() {
		return PostRequestTools.RequestBodyByPost("{\"access_token\":\""+token+"\"}", token, delete_all_record_url);
	}
	
	/**   
	 * @Title: deleteRecord   
	 * @Description: TODO(删除单个记录)   
	 * @param: @param id
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */  
	public static String deleteRecord(String id) {
		return PostRequestTools.RequestBodyByPost("{\"access_token\":\""+token+"\"}", token, deleteRecordUrl(id));
	}
	
	/**   
	 * @Title: exportBlankPaper   
	 * @Description: TODO(导出空白试卷)   
	 * @param: @param id
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */  
	public static String exportBlankPaper(String id) {
		return HttpRequest.get(export_url).query("id",id).query("access_token",token).send().body();
	}
	
	/**   
	 * @Title: exportRecordComposeCount   
	 * @Description: TODO(导出记录的个数)   
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */  
	public static String exportRecordComposeCount() {
		return HttpRequest.get(export_record_composecount_url).query("access_token",token).send().body(); 
	}
	
	/**   
	 * @Title: exportRecordList   
	 * @Description: TODO(查看导出记录列表)   
	 * @param: @param id
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */  
	public static String exportRecordList() {
		return HttpRequest.get(export_record_list_url).query("page_size","1000").query("page_number", "1").query("access_token",token).send().body();
	}

}
