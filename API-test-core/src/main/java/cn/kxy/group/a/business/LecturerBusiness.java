package cn.kxy.group.a.business;
/**
 * @author wenlingzhi
 *2021年8月12日
 */

import cn.kxy.base.business.EnterpriseData;
import cn.kxy.base.business.EnterpriseDataUrl;
import cn.kxy.base.business.TokenData;
import cn.kxy.setting.bussiness.UserBusiness;
import com.lazy.httpclient.utils.HttpRequest;

public class LecturerBusiness {
	public static String enterprise_url = EnterpriseDataUrl.getEnterpriseUrl();
	public static String token = TokenData.getMangerToken();
	public static String enterpriseId = EnterpriseData.getEnterpriseId();
	public static String user_id = UserBusiness.getUserId();
	public static String lecturer_level_add_url = enterprise_url + "v2/"+enterpriseId+"/lecturer_level/";
	public static String edit_lever_url (String lever_id) {
		return enterprise_url + "v2/"+enterpriseId+"/lecturer_levels/"+lever_id+"/update/";
	}
	public static String lecturer_levels_list_url = enterprise_url + "v2/"+enterpriseId+"/lecturer_levels/";
	public static String query_lecturer_levels_url(String lever_id) {
		return enterprise_url + "v2/"+enterpriseId+"/lecturer_levels/"+lever_id+"/query/";
	}
	public static String delete_lecturer_level_url = enterprise_url + "v2/"+enterpriseId+"/lecturer_level/batch_delete/";
	public static String add_lecturers_url = enterprise_url + "v2/"+enterpriseId+"/lecturers/add/";
	public static String lecturers_list_url = enterprise_url + "v2/"+enterpriseId+"/lecturers/";
	public static String base_info_url(String lecturer_id) {
		return enterprise_url + "v2/"+enterpriseId+"/lecturer/"+lecturer_id+"/base_info/";
	}
	public static String courses_detail_url(String lecturer_id) {
		return enterprise_url + "v2/"+enterpriseId+"/lecturers/"+lecturer_id+"/courses/";
	}
	public static String export_base_info_url = enterprise_url + "v2/"+enterpriseId+"/lecturer/export_base_info/";
	public static String score_detail_url = enterprise_url + "v2/"+enterpriseId+"/integral/"+user_id+"/get_score_detail_page/";
	public static String class_pay_url (String lecturer_id) {
		return enterprise_url + "v2/"+enterpriseId+"/lecturer/"+lecturer_id+"/query_class_pay/";
	}
	public static String delete_lecturer_url(String lecturer_id) {
		return enterprise_url + "v2/"+enterpriseId+"/lecturers/"+lecturer_id+"/delete/";
	}
	public static String update_lecturer_url(String lecturer_id) {
		return enterprise_url + "v2/"+enterpriseId+"/lecturers/"+lecturer_id+"/update/";
	}
	public static String queryCourseByPage_url = enterprise_url + "course/queryCourseByPage/";
	public static String courses_url(String course_id) {
		return enterprise_url + "v2/"+enterpriseId+"/lecturers/"+course_id+"/courses/";
	}
	public static String update_status_url(String lecturer_id) {
		return enterprise_url + "v2/"+enterpriseId+"/lecturers/"+lecturer_id+"/update_status/";
	}
	public static String lecture_export_url = enterprise_url + "v2/"+enterpriseId+"/lecture/export/";
    public static String getlecturter_url = enterprise_url + "v2/"+enterpriseId+"/lecturers/";
	
	
	/**   
	 * @Title: GetLecturter  
	 * @Description: TODO      查询讲师
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String GetLecturter(String keyword) {	
		return HttpRequest.get(getlecturter_url).query("access_token", token).query("keyword",keyword).query("status","")
				.send().body();
	}
	
	
	/**   
	 * @Title: lecturerLeverAdd  
	 * @Description: TODO  创建讲师等级
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String LecturerLeverAdd(String name,String standard,String type,String pay_standard) {	
		return HttpRequest.post(lecturer_level_add_url).query("access_token", token).data("{\r\n" + 
				"      \"id\": \"\",\r\n" + 
				"      \"name\": \""+name+"\",\r\n" + 
				"      \"standard\": \""+standard+"\",\r\n" + 
				"      \"type\": \""+type+"\",\r\n" + 
				"      \"pay_standard\": \""+pay_standard+"\",\r\n" + 
				"      \"access_token\": \""+token+"\"\r\n" + 
				"}")
				.send().body();
	}
	
	
	
	/**   
	 * @Title: LecturerLeverEdit  
	 * @Description: TODO  编辑讲师等级
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String LecturerLeverEdit(String lever_id,String name,String type) {	
		return HttpRequest.post(edit_lever_url(lever_id)).query("access_token", token).data("{\r\n" + 
				"      \"id\": \"\",\r\n" + 
				"      \"name\": \""+name+"\",\r\n" + 
				"      \"standard\": \"test description\",\r\n" + 
				"      \"type\": \""+type+"\",\r\n" + 
				"      \"pay_standard\": \"1\",\r\n" + 
				"      \"access_token\": \""+token+"\"\r\n" + 
				"}")
				.send().body();
	}
	
	
	
	
	/**   
	 * @Title: LecturerLeverList  
	 * @Description: TODO  讲师等级列表查询
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String LecturerLeverList(String keyword,String type) {	
		return HttpRequest.get(lecturer_levels_list_url).query("access_token", token).query("keyword",keyword).query("status","")
				.query("level","").query("type",type)
				.send().body();
	}

	
	
	/**   
	 * @Title: LecturerLeverList  
	 * @Description: TODO  讲师等级列表-查看
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String QueryLecturerLever(String lever_id) {	
		return HttpRequest.get(query_lecturer_levels_url(lever_id)).query("access_token", token)
				.send().body();
	}
	
	
	
	
	/**   
	 * @Title: DeleteLecturerLever  
	 * @Description: TODO      删除讲师等级
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String DeleteLecturerLever(String lever_id) {	
		return HttpRequest.post(delete_lecturer_level_url).query("access_token", token).data("{\r\n" + 
				"      \"level_ids\": [\r\n" + 
				"            \""+lever_id+"\"\r\n" + 
				"      ],\r\n" + 
				"      \"access_token\": \""+token+"\"\r\n" + 
				"}")
				.send().body();
	}
	
	
	
	
	/**   
	 * @Title: AddLecturers  
	 * @Description: TODO      添加讲师
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String AddLecturers(String lecturer_name,String avatar,String type,String lever_id,String level_start_time) {	
		return HttpRequest.post(add_lecturers_url).query("access_token", token).data("{\r\n" + 
				"      \"lecturer_name\": \""+lecturer_name+"\",\r\n" + 
				"      \"avatar\": \""+avatar+"\",\r\n" + 
				"      \"sex\": 0,\r\n" + 
				"      \"mobile\": \"\",\r\n" + 
				"      \"type\": \""+type+"\",\r\n" + 
				"      \"user_id\": \""+user_id+"\",\r\n" + 
				"      \"level\": \""+lever_id+"\",\r\n" + 
				"      \"adept_field\": \"\",\r\n" + 
				"      \"lecturer_describe\": \"\",\r\n" + 
				"      \"level_start_time\": \""+level_start_time+"\",\r\n" + 
				"      \"access_token\": \""+token+"\"\r\n" + 
				"}")
				.send().body();
	}
	
	
	
	/**   
	 * @Title: QueryLecturers  
	 * @Description: TODO      讲师列表查询
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String QueryLecturers(String keyword,String status,String level,String level_start_time,String level_end_time) {	
		return HttpRequest.get(lecturers_list_url).query("access_token", token).query("keyword",keyword).query("status",status)
				.query("level",level).query("level_start_time",level_start_time).query("level_end_time",level_end_time)
				.query("page_number","1").query("page_size","20")
				.send().body();
	}
	
	
	
	/**   
	 * @Title: BaseInfo  
	 * @Description: TODO      讲师列表-查看-基本信息
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String BaseInfo(String lecturer_id) {	
		return HttpRequest.get(base_info_url(lecturer_id)).query("access_token", token)
				.send().body();
	}
	
	
	/**   
	 * @Title: CoursesDetail  
	 * @Description: TODO      讲师列表-查看-授课明细
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String CoursesDetail(String lecturer_id,String start_time,String end_time,String biz_type) {	
		return HttpRequest.get(courses_detail_url(lecturer_id)).query("access_token", token).query("start_time",start_time)
				.query("end_time",end_time).query("biz_type",biz_type)
				.send().body();
	}
	
	
	
	/**   
	 * @Title: ExportBaseInfo  
	 * @Description: TODO      讲师列表-查看-授课明细导出
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String ExportBaseInfo(String lecturer_id,String lecturer_record_type) {	
		return HttpRequest.post(export_base_info_url).query("access_token", token).data("{\r\n" + 
				"      \"start_time\": \"\",\r\n" + 
				"      \"end_time\": \"\",\r\n" + 
				"      \"lecturer_record_type\": \""+lecturer_record_type+"\",\r\n" + 
				"      \"lecturer_id\": \""+lecturer_id+"\",\r\n" + 
				"      \"access_token\": \""+token+"\"\r\n" + 
				"}")
				.send().body();
	}
	
	
	
	/**   
	 * @Title: ExportInfo  
	 * @Description: TODO      讲师列表-明细记录导出
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String ExportInfo(String lecturer_record_type,String keyword) {	
		return HttpRequest.post(export_base_info_url).query("access_token", token).data("{\r\n" + 
				"      \"lecturer_record_type\": \""+lecturer_record_type+"\",\r\n" + 
				"      \"start_time\": \"\",\r\n" + 
				"      \"end_time\": \"\",\r\n" + 
				"      \"keyword\": \""+keyword+"\",\r\n" + 
				"      \"status\": \"\",\r\n" + 
				"      \"level\": \"\",\r\n" + 
				"      \"level_start_time\": \"\",\r\n" + 
				"      \"level_end_time\": \"\",\r\n" + 
				"      \"access_token\": \""+token+"\"\r\n" + 
				"}")
				.send().body();
	}
	
	
	
	/**   
	 * @Title: ScoreDetail  
	 * @Description: TODO      讲师列表-查看-积分明细
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String ScoreDetail(String begin_time,String end_time) {	
		return HttpRequest.get(score_detail_url).query("access_token", token).query("begin_time",begin_time)
				.query("end_time",end_time).query("type","8")
				.send().body();
	}
	
	
	/**   
	 * @Title: ClassPay  
	 * @Description: TODO      讲师列表-查看-课酬明细
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String ClassPay(String lecturer_id,String start_time,String end_time) {	
		return HttpRequest.get(class_pay_url(lecturer_id)).query("access_token", token).query("start_time",start_time)
				.query("end_time",end_time).query("type","8")
				.send().body();
	}
	
	
	
	/**   
	 * @Title: DeleteLecturer  
	 * @Description: TODO      删除讲师
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String DeleteLecturer(String lecturer_id) {	
		return HttpRequest.post(delete_lecturer_url(lecturer_id)).query("access_token", token)
				.send().body();
	}
	
	
	
	/**   
	 * @Title: UpdateLecturer  
	 * @Description: TODO      编辑讲师
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String UpdateLecturer(String lecturer_id,String lecturer_name,String avatar,String type,String level,
			String level_start_time) {	
		return HttpRequest.post(update_lecturer_url(lecturer_id)).query("access_token", token).data("{\r\n" + 
				"      \"lecturer_name\": \""+lecturer_name+"\",\r\n" + 
				"      \"avatar\": \""+avatar+"\",\r\n" + 
				"      \"sex\": 0,\r\n" + 
				"      \"mobile\": \"\",\r\n" + 
				"      \"type\": \""+type+"\",\r\n" + 
				"      \"level\": \""+level+"\",\r\n" + 
				"      \"adept_field\": \"\",\r\n" + 
				"      \"lecturer_describe\": \"\",\r\n" + 
				"      \"level_start_time\": \""+level_start_time+"\",\r\n" + 
				"      \"access_token\": \""+token+"\"\r\n" + 
				"}")
				.send().body();
	}
	
	
	
	
	/**   
	 * @Title: QueryCourseByPage  
	 * @Description: TODO      查询课程列表
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String QueryCourseByPage() {	
		return HttpRequest.get(queryCourseByPage_url).query("access_token", token).query("queryType","0").query("scope","2")
				.query("courseType","all").query("image_text","")
				.send().body();
	}
	
	
	
	/**   
	 * @Title: Course  
	 * @Description: TODO      关联课程
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String Course(String course_id,String lecturer_name) {	
		return HttpRequest.post(courses_url(course_id)).query("access_token", token).data("{\r\n" + 
				"      \"course_ids\": [\r\n" + 
				"            \""+course_id+"\"\r\n" + 
				"      ],\r\n" + 
				"      \"lecturer_name\": \""+lecturer_name+"\",\r\n" + 
				"      \"access_token\": \""+token+"\"\r\n" + 
				"}")
				.send().body();
	}
	
	
	/**   
	 * @Title: UpdateStatus  
	 * @Description: TODO     开启/停用讲师状态
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String UpdateStatus(String lecturer_id,String status) {	
		return HttpRequest.post(update_status_url(lecturer_id)).query("access_token", token).data("{\r\n" + 
				"      \"status\": \""+status+"\",\r\n" + 
				"      \"access_token\": \""+token+"\"\r\n" + 
				"}")
				.send().body();
	}
	
	
	
	
	/**   
	 * @Title: LectureExport  
	 * @Description: TODO     讲师列表导出
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String LectureExport(String keyword) {	
		return HttpRequest.get(lecture_export_url).query("access_token", token).query("keyword",keyword)
				.query("status","").query("level","").query("level_start_time","").query("level_end_time","")
				.send().body();
	}
	
	
}
