package cn.kxy.examination.business;

import cn.kxy.base.business.EnterpriseData;
import cn.kxy.base.business.ExamDataUrl;
import cn.kxy.base.business.TokenData;
import cn.kxy.setting.bussiness.UserBusiness;
import com.lazy.assured.utils.DeleteRequestTools;
import com.lazy.httpclient.utils.HttpRequest;
import com.lazy.httpclient.utils.HttpResponse;
import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class SoaExamBusiness {
	public static String exam_url= ExamDataUrl.getNewExamUrl();
	public static String enterpriseId = EnterpriseData.getEnterpriseId();
	public static String token = TokenData.getMangerToken();
	public static String userId= UserBusiness.getUserId();
	public static String batch_query_url =exam_url + "/v2/"+enterpriseId+"/users/"+userId+"/exams/batch_query";
	
	public static String batch_query_exam_url=exam_url + "/v2/"+enterpriseId+"/exams/batch_query";
	
	public static String batch_query_exam_monitorsUrl =exam_url + "/v2/"+enterpriseId+"/exam_monitors/batch_query";
	
	public static String delete_exam_paper_mappings_url = exam_url + "/v2/"+enterpriseId+"/exam_paper_mappings";
	
	public static String exam_monitors_url =exam_url +"/v2/"+enterpriseId+"/exam_monitors";
	
	public static String query_marking_users_url = exam_url +"/v2/"+enterpriseId+"/marking_users";
	
	/**   
	 * @Title: queryMarkingUsers   
	 * @Description: TODO(查看待阅卷人员)   
	 * @param: @param examId1
	 * @param: @param examId2
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String queryMarkingUsers(String examId1) {
		HttpResponse res=HttpRequest.get(query_marking_users_url).query("access_token", token).
				query("exam_ids", examId1).send();
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
	 * @Title: addExamMonitors   
	 * @Description: TODO(新增考试监控)   
	 * @param: @param examId1
	 * @param: @param examId2
	 * @param: @param begin_time
	 * @param: @param end_time
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String addExamMonitors(String examId1,String begin_time,String end_time) {
		
		return HttpResponse.postJson(exam_monitors_url, "{\r\n" + 
				"  \"exam_ids\": [\r\n" + 
				"    \""+examId1+"\"\r\n" + 
				"  ], \r\n" + 
				"  \"user_ids\": [\r\n" + 
				"    \""+userId+"\"\r\n" + 
				"  ], \r\n" + 
				"  \"begin_time\": \""+begin_time+"\", \r\n" + 
				"  \"end_time\": \""+end_time+"\"\r\n" + 
				"}", "access_token", token);
	}
	
	/**   
	 * @Title: deleteExamMonitors   
	 * @Description: TODO(删除考试监控)   
	 * @param: @param examId1
	 * @param: @param examId2
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String deleteExamMonitors(String examId1) {
		return  DeleteRequestTools.RequestBodyByDelete(exam_monitors_url+"?access_token="+token,"{\r\n" + 
				"	\"exam_ids\": [\""+examId1+"\"],\r\n" + 
				"	\"user_ids\": [\""+userId+"\"]\r\n" + 
				"}");
	}
	/**   
	 * @Title: deleteExamPaperMappings   
	 * @Description: TODO(删除考试试卷关系)   
	 * @param: @param examId1
	 * @param: @param examId2
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String deleteExamPaperMappings(String examId1,String examId2) {
		return DeleteRequestTools.RequestBodyByDelete(delete_exam_paper_mappings_url+"?access_token="+token, "{\r\n" + 
				"	\"exam_ids\": [\""+examId1+"\",\""+examId2+"\"]\r\n" + 
				"}");
	}
	
	
	/**   
	 * @Title: batchQueryExamMonitors   
	 * @Description: TODO(批量查询考试监控信息)   
	 * @param: @param examId1
	 * @param: @param examId2
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String batchQueryExamMonitors (String examId1,String examId2) {
		return HttpResponse.postJson(batch_query_exam_monitorsUrl, "{\r\n" + 
				"	\"exam_ids\": [\""+examId1+"\",\""+examId2+"\"],\r\n" + 
				"	\"user_ids\": [\""+userId+"\"]\r\n" + 
				"}", "access_token", token);
	}
	
	/**   
	 * @Title: batchQueryExamInfo   
	 * @Description: TODO(批量查询考试任务详情)   
	 * @param: @param examId1
	 * @param: @param examId2
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String batchQueryExamInfo (String examId1,String examId2) {
		return HttpResponse.postJson(batch_query_exam_url, "{\r\n" + 
				"  \"exam_ids\": [\r\n" + 
				"    \""+examId1+"\", \r\n" + 
				"    \""+examId2+"\"\r\n" + 
				"  ]\r\n" + 
				"}", "access_token", token);
	}
	
	/**   
	 * @Title: batchQueryExamPeopleInfo   
	 * @Description: TODO(批量查询人员的考试任务信息)   
	 * @param: @param examId
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String batchQueryExamPeopleInfo(String examId1,String examId2) {
		
		return HttpResponse.postJson(batch_query_url, "{\r\n" + 
				"  \"exam_ids\": [\r\n" + 
				"    \""+examId1+"\", \r\n" + 
				"    \""+examId2+"\"\r\n" + 
				"  ]\r\n" + 
				"}", "access_token", token);
	}
}
