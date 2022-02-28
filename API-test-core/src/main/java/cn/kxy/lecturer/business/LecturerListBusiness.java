package cn.kxy.lecturer.business;

import cn.kxy.base.business.EnterpriseData;
import cn.kxy.base.business.EnterpriseDataUrl;
import cn.kxy.base.business.TokenData;
import cn.kxy.setting.bussiness.UserBusiness;
import com.alibaba.fastjson.JSONPath;
import com.lazy.assured.utils.GetRequestTools;
import com.lazy.assured.utils.PostRequestTools;
import com.lazy.common.utils.DateUtil;
import com.lazy.httpclient.utils.HttpRequest;
import com.lazy.httpclient.utils.HttpResponse;

import java.util.ArrayList;

public class LecturerListBusiness {
	public static String token = TokenData.getMangerToken();
	public static String enterpriseId = EnterpriseData.getEnterpriseId();
	public static String enterpriseUrl = EnterpriseDataUrl.getEnterpriseUrl();
	public static String addUrl = enterpriseUrl + "v2/"+enterpriseId+"/lecturers/add";
	public static String querylistUrl=enterpriseUrl+"v2/"+enterpriseId+"/lecturers";
	public static String user_id = UserBusiness.getUserId();
	public static String lecturersTeachCoursesListUrl(String id) {
		return enterpriseUrl + "v2/"+enterpriseId+"/lecturers/"+id+"/courses";
	}
	
	public static String relateCoursesUrl(String id) {
		return enterpriseUrl + "v2/"+enterpriseId+"/lecturers/"+id+"/courses";
	}
	
	public static String editUrl (String id) {
		return enterpriseUrl + "v2/"+enterpriseId+"/lecturers/"+id+"/update";
	}
	public static String updateUrl(String id) {
		return enterpriseUrl + "v2/"+enterpriseId+"/lecturers/"+id+"/update_status";
	}
	
	public static String deleteUrl(String id) {
		return enterpriseUrl + "v2/"+enterpriseId+"/lecturers/"+id+"/delete";
	}
	
	public static String queryInfoUrl(String id) {
		return enterpriseUrl +"v2/"+enterpriseId+"/lecturers/"+id+"/query";
	}
	
	public static String queryLecturerLatojasUrl  (String lecturer_id) {
		return enterpriseUrl + "v2/"+enterpriseId+"/lecturer/"+lecturer_id+"/latojas";
	}
	
	public static String exportLatojasUrl (String lecturer_id) {
		return enterpriseUrl + "v2/"+enterpriseId+"/lecturer/"+lecturer_id+"/latojas/export";
	}
	
	public static String countermand_url = enterpriseUrl + "v2/"+enterpriseId+"/integral/countermand";
	
	public static String add_integral_lecturers_url = enterpriseUrl + "v2/"+enterpriseId+"/lecturers/integral/add";
	
	public static String integralListExportUrl (String lecture_id) {
		return enterpriseUrl + "v2/"+enterpriseId+"/lecture/"+lecture_id+"/integral_list_export";
	}	
	
	public static String export_lecture_url = enterpriseUrl + "v2/"+enterpriseId+"/lecture/export";
	
	/**   
	 * @Title: exportLectureList   
	 * @Description: TODO  导出讲师列表
	 * @param: @param keyword
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */  
	public static  String exportLectureList(String keyword,String status) {
		return HttpRequest.get(export_lecture_url).query("access_token", token).query("keyword",keyword).query("status", status).send().body();
	}
	
	public static String queryIntegralDetails(String begin_time,String end_time,String user_id) {
		return HttpRequest.get(enterpriseUrl + "v2/"+enterpriseId+"/integral/"+user_id+"/details")
				.query("access_token",token).query("page_number","1").query("page_size","10")
				.query("begin_time",begin_time).query("end_time",end_time).send().body();
	}
	
	
	/**   
	 * @Title: exportIntegralList   
	 * @Description: TODO   导出讲师的积分列表
	 * @param: @param lecture_id
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */  
	public static String exportIntegralList(String lecture_id,String start_time,String end_time) {
		return HttpRequest.post(integralListExportUrl(lecture_id)).query("access_token", token).data("{\n" + 
				"  \"start_time\":\""+start_time+"\",\n" + 
				"  \"end_time\":\""+end_time+"\",\n" + 
				"  \"access_token\":\""+token+"\"\n" + 
				"}\n" + 
				"").send().body();
	}
	
	/**   
	 * @Title: addIntegralLecturers   
	 * @Description: TODO  手动导入讲师积分
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */  
	public static String addIntegralLecturers(String user_id,String points) {
		return HttpRequest.post(add_integral_lecturers_url).query("access_token", token).data("{\n" + 
				"  \"behavior\":\"add  integral lecturers\",\n" + 
				"  \"points\":"+points+",\n" + 
				"  \"time\":"+DateUtil.getTimeStamp(-1, "")+",\n" + 
				"  \"user_ids\":\""+user_id+"\",\n" + 
				"  \"access_token\":\""+token+"\"\n" + 
				"}").send().body();
	}
	
	
	
	
	/**   
	 * @Title: countermandIntegral   
	 * @Description: TODO  撤回讲师积分
	 * @param: @param list
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */  
	public static String countermandIntegral(ArrayList<String> list) {
		return HttpRequest.post(countermand_url).query("access_token", token).data("{\n" + 
				"  \"list\":"+list+",\n" + 
				"  \"user_id\":\""+user_id+"\",\n" + 
				"  \"access_token\":\""+token+"\"\n" + 
				"}\n" + 
				"").send().body();
	}
	
	/**   
	 * @Title: exportLatojas   
	 * @Description: TODO  导出讲师的线下签到详情
	 * @param: @param lecturer_id
	 * @param: @param keyword
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */  
	public static String exportLatojas(String lecturer_id,String keyword) {
		return HttpRequest.get(exportLatojasUrl(lecturer_id)).query("access_token", token).query("keyword",keyword).send().body();
	}
	
	/**   
	 * @Title: queryLecturerLatojas   
	 * @Description: TODO  查看讲师列表的线下签到详情
	 * @param: @param lecturer_id
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */  
	public static String queryLecturerLatojas (String lecturer_id,String keyword) {
		return HttpRequest.get(queryLecturerLatojasUrl(lecturer_id)).query("access_token", token).query("page_number","1")
				.query("page_size","20").query("keyword",keyword).send().body();
	}
	
	/**   
	 * @Title: queryInfo   
	 * @Description: TODO(查询讲师详情)   
	 * @param: @param id
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String queryInfo(String id) {
		return GetRequestTools.RequestQueryParamsByGet("access_token", token, queryInfoUrl(id));
	}
	
	/**   
	 * @Title: lecturersTeachCoursesList   
	 * @Description: TODO(讲师授课的列表)   
	 * @param: @param lecturersId
	 * @param: @param keyword
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String lecturersTeachCoursesList(String lecturersId,String keyword) {
		return GetRequestTools.RequestQueryParamsByGet("access_token", token,"keyword",keyword,"page_number","1","page_size","20",lecturersTeachCoursesListUrl(lecturersId));
	}
	
	/**   
	 * @Title: relateCourses   
	 * @Description: TODO(讲师关联课程)   
	 * @param: @param course_ids
	 * @param: @param id
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String relateCourses(String course_ids,String id) {
		return PostRequestTools.RequestBodyByPost("{\"course_ids\":[\""+course_ids+"\"],\"access_token\":\""+token+"\"}", token, relateCoursesUrl(id));
	}
	
	
	
	public static String editLecturer(String name,String sex,String mobile,String type,String userid,String levelName,String adept_field,String lecturer_describe,String id) {
		return HttpResponse.postJson(editUrl(id), "{\r\n" + 
				"  \"lecturer_name\": \""+name+"\", \r\n" + 
				"  \"avatar\": \"\", \r\n" + 
				"  \"sex\": "+sex+", \r\n" + 
				"  \"mobile\": \""+mobile+"\", \r\n" + 
				"  \"type\": \""+type+"\", \r\n" + 
				"  \"user_id\": \""+userid+"\", \r\n" + 
				"  \"level\": \""+LecturerLevelBusiness.getIdByKeyword(levelName)+"\", \r\n" + 
				"  \"adept_field\": \""+adept_field+"\", \r\n" + 
				"  \"lecturer_describe\": \""+lecturer_describe+"\", \r\n" + 
				"  \"access_token\": \""+token+"\"\r\n" + 
				"}", "access_token", token);
		
	}
	
	/**   
	 * @Title: deleteLecturer   
	 * @Description: TODO(删除讲师)   
	 * @param: @param id
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String deleteLecturer(String id) {
		return PostRequestTools.RequestBodyByPost("{\"access_token\":\""+token+"\"}", token, deleteUrl(id));
	}

	public static String deleteLecturerByKeyword(String keyword) {
		int status = getStatusByKeyword(keyword);
		if (status==1) {
			updateStatus("0",getIdByKeyword(keyword));
		}
		String res = deleteLecturer(getIdByKeyword(keyword));
		return res;
	}
	
	/**   
	 * @Title: updateStatus   
	 * @Description: TODO(启用或者停用讲师)   
	 * @param: @param status
	 * @param: @param id
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String updateStatus(String status ,String id) {
		return PostRequestTools.RequestBodyByPost("{\r\n" + 
				"  \"status\": "+status+", \r\n" + 
				"  \"access_token\": \""+token+"\"\r\n" + 
				"}", token, updateUrl(id));
	}
	/**   
	 * @Title: getIdByKeyword   
	 * @Description: TODO(通过name获取id)   
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String getIdByKeyword(String keyword) {
		String res = queryLecturerList(keyword,"","");
		String id =(String)JSONPath.read(res, "$.list[0].id");
		return id;
	}
	
	public static String getIdByKeyword() {
		String res = queryLecturerList("","1","");
		String id =(String)JSONPath.read(res, "$.list[0].id");
		return id;
	}
	
	public static String getlevelByKeyword(String keyword) {
		String res = queryLecturerList(keyword,"","");
		String id =(String)JSONPath.read(res, "$.list[0].level");
		return id;
	}
	
	public static int getStatusByKeyword(String keyword) {
		String res = queryLecturerList(keyword,"","");
		int status =(int)JSONPath.read(res, "$.list[0].status");
		return status;
	}
	
	/**   
	 * @Title: queryLecturerList   
	 * @Description: TODO(查询讲师列表)   
	 * @param: @param keyword
	 * @param: @param status
	 * @param: @param level
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String queryLecturerList(String keyword,String status,String level) {
		return GetRequestTools.RequestQueryParamsByGet("keyword", keyword, "status",status,"level",level,"access_token",token,querylistUrl);
	}
	
	/**   
	 * @Title: addLecturer   
	 * @Description: TODO(新增讲师)   
	 * @param: @param name
	 * @param: @param sex
	 * @param: @param mobile
	 * @param: @param type
	 * @param: @param userid
	 * @param: @param levelName
	 * @param: @param adept_field
	 * @param: @param lecturer_describe
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String addLecturer(String name,String sex,String mobile,String type,String userid,String levelName,String adept_field,String lecturer_describe) {
		return HttpResponse.postJson(addUrl, "{\n" + 
				"    \"lecturer_name\": \""+name+"\",\n" + 
				"    \"avatar\": \"https://oss.coolcollege.cn/1790708689137700864.png\",\n" + 
				"    \"sex\": "+sex+",\n" + 
				"    \"mobile\": \"\",\n" + 
				"    \"type\": \""+type+"\",\n" + 
				"    \"user_id\": \"\",\n" + 
				"    \"level\": \""+LecturerLevelBusiness.getIdByKeyword(levelName)+"\",\n" + 
				"    \"adept_field\": \"\",\n" + 
				"    \"lecturer_describe\": \""+lecturer_describe+"\",\n" + 
				"    \"level_start_time\": "+DateUtil.getTimeStamp(1, "")+",\n" + 
				"    \"access_token\": \""+token+"\"\n" + 
				"}", "access_token", token);
		
	}
}
