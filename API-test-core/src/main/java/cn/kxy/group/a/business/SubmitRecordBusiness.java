package cn.kxy.group.a.business;


import cn.kxy.base.business.EnterpriseData;
import cn.kxy.base.business.EnterpriseDataUrl;
import cn.kxy.base.business.TokenData;
import cn.kxy.setting.bussiness.UserBusiness;
import com.lazy.common.utils.DateUtil;
import com.lazy.httpclient.utils.HttpRequest;

public class SubmitRecordBusiness {

	public static String enterprise_url = EnterpriseDataUrl.getEnterpriseUrl();
	public static String enterpriseId = EnterpriseData.getEnterpriseId();
	public static String enterprise_Id = EnterpriseData.getEnterpriseId();
	public static String access_token = TokenData.getMangerToken();
	public static String userId = UserBusiness.getUserId();
	public static String recordsettingURL = enterprise_url + "v2/"+enterprise_Id+"/switches/c94f3302b6774b6792aebe5c520508e1/setting";
	public static String creatrecordURL = enterprise_url + "v1/"+enterprise_Id+"/submit/save";
	public static String recordListURL = enterprise_url + "v1/"+enterprise_Id+"/user/"+userId+"/submit/record";
	public static String recordreviewURL(String record_id) {
		return enterprise_url + "v1/"+enterprise_Id+"/submit/"+record_id+"/review";
	}
	public static String recordmanagementURL = enterprise_url + "v1/"+enterprise_Id+"/user/"+userId+"/submit/management";
	
	
	//提报管理列表
	public static String recordManagementList(String status, String classify_id, String start_time, String end_time) {
		
		return HttpRequest.get(recordmanagementURL).query("status", status).query("classify_id", classify_id)
				.query("start_time", start_time).query("end_time", end_time).query("page_number", "1")
				.query("page_size", "20").query("access_token", access_token).send().body();
	}

	
	//审批提报
	public static String recordReview(String record_id, String instance_id,String score,int status) {
		
		return HttpRequest.post(recordreviewURL(record_id)).query("access_token", access_token).data(
				 "{\r\n"
				 + "    \"draft\":false,\r\n"
				 + "    \"suggest\":\"11111111\",\r\n"
				 + "    \"score\":\""+score+"\",\r\n"
				 + "    \"status\":"+status+",\r\n"
				 + "    \"resources\":[\r\n"
				 + "\r\n"
				 + "    ],\r\n"
				 + "    \"instance_id\":\""+instance_id+"\",\r\n"
				 + "    \"access_token\":\""+access_token+"\"\r\n"
				 + "}").send().body();
	}
	//查看提报列表
	public static String recordList(String status, String keyword) {
		
		return HttpRequest.get(recordListURL).query("status", status).query("keyword", keyword).query("page_number", "1")
				.query("page_size", "20").query("access_token", access_token).send().body();
	}
	//关闭提报审批人
	public static String ClosedRecordPre(String status) {
		
		String approvers = "";
		if (status.equals("OPEN")) {
			approvers = "["+userId+"]";
		}else {
			approvers = "[]";
		}
		
		return HttpRequest.post(recordsettingURL).query("access_token", access_token).data(
				"{\r\n"
				+ "    \"type\":\"submit_report\",\r\n"
				+ "    \"switch_status\":\""+status+"\",\r\n"
				+ "    \"approvers\":"+approvers+",\r\n"
				+ "    \"actions\":\"\",\r\n"
				+ "    \"access_token\":\""+access_token+"\"\r\n"
				+ "}").send().body();
		
	}
	
	//创建提报
	public static String creatRecord(String recordName, String recordAddress, String recordContent, String submit_status) {
		
		return HttpRequest.post(creatrecordURL).query("access_token", access_token).data(
				"{\r\n"
				+ "    \"title\":\""+recordName+"\",\r\n"
				+ "    \"participation_time\":"+DateUtil.getOneHourLaterStamp()+",\r\n"
				+ "    \"classify_id\":\"\",\r\n"
				+ "    \"duration\":\"\",\r\n"
				+ "    \"head\":\"\",\r\n"
				+ "    \"address\":\""+recordAddress+"\",\r\n"
				+ "    \"content\":\""+recordContent+"\",\r\n"
				+ "    \"resource_ids\":\"\",\r\n"
				+ "    \"submit_status\":\""+submit_status+"\",\r\n"
				+ "    \"id\":\"\",\r\n"
				+ "    \"access_token\":\""+access_token+"\"\r\n"
				+ "}").send().body();
	}
	
	//删除提报
	public static String deleteRecord(String record_id) {
		String deleterecordURL = enterprise_url + "v1/"+enterprise_Id+"/submit/"+record_id+"/delete";
		return HttpRequest.post(deleterecordURL).query("access_token", access_token).data(
				"{\"access_token\":\""+access_token+"\"}").send().body();
	}


}
