package cn.kxy.course.resources.bussiness;

import cn.kxy.base.business.EnterpriseData;
import cn.kxy.base.business.EnterpriseDataUrl;
import cn.kxy.base.business.TokenData;
import cn.kxy.setting.bussiness.ClassificationBusines;
import com.alibaba.fastjson.JSONPath;
import com.lazy.assured.utils.GetRequestTools;
import com.lazy.assured.utils.PostRequestTools;
import com.lazy.httpclient.utils.HttpRequest;
import com.lazy.httpclient.utils.HttpResponse;
import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class CourseBusiness {
	public static String token = TokenData.getMangerToken();
	public static String enterpriseId = EnterpriseData.getEnterpriseId();
	public static String enterpriseUrl = EnterpriseDataUrl.getEnterpriseUrl();
	public static String addBatchCourseUrl = enterpriseUrl + "course/addBatchCourse";
	public static String addUrl = enterpriseUrl + "course/add";
	public static String setClassifyUrl = enterpriseUrl + "v2/"+enterpriseId+"/courses/classify";
	public static String setScoreUrl = enterpriseUrl + "v2/"+enterpriseId+"/courses/credit";
	public static String setVisibleUrl=enterpriseUrl +"v2/"+enterpriseId+"/courses/batch_set_visible";
	
	public static String queryCourseByPageUrl= enterpriseUrl + "course/queryCourseByPage";
	public static String queryCourseByPageFastUrl= enterpriseUrl + "course/queryCourseByPage_fast";
	
	public static String deleteUrl = enterpriseUrl + "course/delete";

	//管理页面查看观看人数
	public static String  queryViewersUrl(String courseId) {
		return enterpriseUrl +"v2/"+enterpriseId+"/courses/"+courseId+"/viewers";
	}
	
	public static String courseManageUrl= enterpriseUrl + "v2/"+enterpriseId+"/courses/manage";
	
	public static String getCourseListUrl = enterpriseUrl + "course/resource/getList";
	
	public static String editUrl = enterpriseUrl + "course/edit";
	
	public static String queryInfoUrl = enterpriseUrl + "course/selectOne";
	
	
	public static String setVisibleUrl (String id) {
		return enterpriseUrl +"v2/"+enterpriseId+"/courses/"+id+"/save_visible";
	}
	
	public static String releaseCancleUrl(String coursesId) {
		return enterpriseUrl +"v2/"+enterpriseId+"/courses/"+coursesId+"/release";
	}
	public static String course_export_url = enterpriseUrl + "v2/"+enterpriseId+"/courses/export";
	
	public static String export_viewer_sys_url = enterpriseUrl + "v2/"+enterpriseId+"/courses/export_viewer_sys";
	
	public static String exportVieweSys(String course_Ids) {
		return HttpRequest.get(export_viewer_sys_url).query("access_token",token).query("course_Ids",course_Ids).send().body();
	}
	
	/**   
	 * @Title: getcourseExportCode   
	 * @Description: TODO(获取课程导出)   
	 * @param: @param course_status
	 * @param: @param course_type
	 * @param: @return      
	 * @return: int      
	 * @throws   
	 */ 
	public static int getcourseExportCode(String course_status,String course_type) {
		return HttpResponse.getstatusCode(course_export_url, token, "page_number","1","page_size","10","course_status",course_status,"course_type",course_type);
	}
	
	/**   
	 * @Title: queryCourseInfo   
	 * @Description: TODO(查看课程详情)   
	 * @param: @param id
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String queryCourseInfo(String id) {
		return GetRequestTools.RequestQueryParamsByGet("id", id,"access_token",token, queryInfoUrl);
	}
	/**   
	 * @Title: QueryViewers   
	 * @Description: TODO(课程管理页面查看观看人数)   
	 * @param: @param courseId
	 * @param: @param keyword
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String queryViewers(String courseId,String keyword) {
		return GetRequestTools.RequestQueryParamsByGet("access_token",token,"keyword", keyword,"page_number","1","page_size","10", queryViewersUrl(courseId));
		
	}
	
	/**   
	 * @Title: addMultiCourse   
	 * @Description: TODO(添加多个课程)   
	 * @param: @param title
	 * @param: @param title2
	 * @param: @param resource01
	 * @param: @param resource02
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String addMultiCourse(String title,String title2,String resource01,String resource02) {
		return PostRequestTools.RequestFormDataByPost(token, "batchCourse", "[\r\n" + 
				"  {\r\n" + 
				"    \"title\": \""+title+"\", \r\n" + 
				"    \"introduction\": \"\", \r\n" + 
				"    \"coverType\": 1, \r\n" + 
				"    \"cover\": \"\", \r\n" + 
				"    \"baseCover\": \"https://oss.coolcollege.cn/1789917624419880960.png\", \r\n" + 
				"    \"teacherId\": \"\", \r\n" + 
				"    \"courseClassify\": \"999\", \r\n" + 
				"    \"resource\": \""+resource01+"\", \r\n" + 
				"    \"original\": 1, \r\n" + 
				"    \"isAll\": 1, \r\n" + 
				"    \"groupIds\": \"\", \r\n" + 
				"    \"departmentIds\": \"\", \r\n" + 
				"    \"userIds\": \"\", \r\n" + 
				"    \"postIds\": \"\", \r\n" + 
				"    \"download\": 0, \r\n" + 
				"    \"credit\": 0, \r\n" + 
				"    \"init_type\": \"draft\"\r\n" + 
				"  }, \r\n" + 
				"  {\r\n" + 
				"    \"title\": \""+title2+"\", \r\n" + 
				"    \"introduction\": \"\", \r\n" + 
				"    \"coverType\": 1, \r\n" + 
				"    \"cover\": \"\", \r\n" + 
				"    \"baseCover\": \"https://oss.coolcollege.cn/1789917624419880960.png\", \r\n" + 
				"    \"teacherId\": \"\", \r\n" + 
				"    \"courseClassify\": \"999\", \r\n" + 
				"    \"resource\": \""+resource02+"\", \r\n" + 
				"    \"original\": 1, \r\n" + 
				"    \"isAll\": 1, \r\n" + 
				"    \"groupIds\": \"\", \r\n" + 
				"    \"departmentIds\": \"\", \r\n" + 
				"    \"userIds\": \"\", \r\n" + 
				"    \"postIds\": \"\", \r\n" + 
				"    \"download\": 0, \r\n" + 
				"    \"credit\": 0, \r\n" + 
				"    \"init_type\": \"draft\"\r\n" + 
				"  }\r\n" + 
				"]", addBatchCourseUrl);
	}
	
	/**   
	 * @Title: editCourse   
	 * @Description: TODO(编辑课程)   
	 * @param: @param id
	 * @param: @param title
	 * @param: @param introduction
	 * @param: @param baseCover
	 * @param: @param cover
	 * @param: @param teacherId
	 * @param: @param courseClassify
	 * @param: @param resource
	 * @param: @param original
	 * @param: @param isAll
	 * @param: @param userIds
	 * @param: @param download
	 * @param: @param credit
	 * @param: @param init_type
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String editCourse(String id,String title,String introduction,String baseCover,String cover,String teacherId
			,String courseClassify,String resource,String original,String isAll,String userIds,String download,
			String credit,String init_type) {
		return PostRequestTools.RequestFormDataByPost(token, "id", id,"title",title, "introduction",introduction,
				"coverType","1","cover",cover,"baseCover",baseCover,"teacherId",teacherId,"courseClassify",courseClassify,
				"resource",resource,"original",original,"isAll",isAll,"userIds",userIds,"download",download,
				"credit",credit,"init_type",init_type,editUrl);
	}
	
	/**   
	 * @Title: getCourseList   
	 * @Description: TODO(添加课程时，对可见进行筛选)   
	 * @param: @param resourceClassify
	 * @param: @param keyword
	 * @param: @param contentType
	 * @param: @param excludeType
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String getCourseList(String resourceClassify,String keyword,String contentType,String excludeType) {
		return GetRequestTools.RequestQueryParamsByGet("resourceClassify", resourceClassify, "keyword",keyword,
				"contentType",contentType,"pageNumber","1","pageSize","10","excludeType",excludeType,
				"access_token",token,getCourseListUrl);
	}
	
	/**   
	 * @Title: setSingleVisible   
	 * @Description: TODO(设置单个课程的可见范围)   
	 * @param: @param id
	 * @param: @param user_ids
	 * @param: @param is_all
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String setSingleVisible(String id,String user_ids,String is_all) {
		return PostRequestTools.RequestFormDataByPost(token, "user_ids", user_ids,"is_all",is_all, setVisibleUrl(id));
	}
	
	/**   
	 * @Title: getIdByKeyword   
	 * @Description: TODO(根据状态和标题获取id)   
	 * @param: @param title
	 * @param: @param course_status
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String getIdByKeyword(String title,String course_status) {
		String res = courseManageList(title,"all",course_status,"","","all");
		String id =(String)JSONPath.read(res, "$.list[0].course_id");
		return id;
	}
	
	/**   
	 * @Title: courseManageList   
	 * @Description: TODO(课程管理列表)   
	 * @param: @param title
	 * @param: @param view_range
	 * @param: @param course_status
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String courseManageList(String title,String view_range,String course_status,String type,String classify_id,String course_type) {
		HttpResponse res=HttpRequest.get(courseManageUrl).query("title", title).query("access_token", token).
				query("view_range", view_range).query("course_status",course_status).query("classify_id",classify_id).query("page_number", "1").
				query("page_size", "20").query("course_type", course_type).query("type", type).send();
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
	 * @Title: queryCourseByPage   
	 * @Description: TODO(课程列表页查看)   
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String queryCourseByPage(String title,String queryType,String classifyId) {
		return GetRequestTools.RequestQueryParamsByGet("title",title,"queryType",queryType, "classifyId",classifyId,
				"access_token",token,"pageNumber","1","pageSize","20",queryCourseByPageUrl);
	}
	
	public static String queryCourseByPageFast(String title) {
		return GetRequestTools.RequestQueryParamsByGet("title",title,"queryType","0", "classifyId","",
				"access_token",token,"pageNumber","1","pageSize","20","courseType","course","auth_type","refer",queryCourseByPageFastUrl);
	}
	
	public static String queryCourseByPage(String title) {
		return GetRequestTools.RequestQueryParamsByGet("title",title,"queryType","", "classifyId","0",
				"access_token",token,"pageNumber","1","courseType","all","pageSize","20",queryCourseByPageUrl);
	}
	
	public static String queryCourseArtByPage(String title) {
		return GetRequestTools.RequestQueryParamsByGet("title",title,"queryType","0", "scope","0","filterQuota","false",
				"access_token",token,"pageNumber","1","courseType","course","pageSize","20","image_text","true",queryCourseByPageUrl);
	}
	
	public static String queryCourseBystudy_project(String title) {
		return GetRequestTools.RequestQueryParamsByGet("title",title,"statusType","all", "sortType","all",
				"access_token",token,"pageNumber","1","classifyType","study_project","pageSize","20",queryCourseByPageUrl);
	}
	
	/**   
	 * @Title: queryCourseByPage   
	 * @Description: TODO   最新改版
	 * @param: @param title
	 * @param: @param timestamp
	 * @param: @param queryType
	 * @param: @param statusType
	 * @param: @param sortType
	 * @param: @param classifyType
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */  
	public static String queryCourseByPage (String title,String timestamp,String queryType,String statusType,String sortType,String classifyType,String order) {
		return HttpRequest.get(queryCourseByPageUrl).query("access_token",token).query("title",title).query("pageNumber","1")
				.query("pageSize","20").query("queryType",queryType).query("statusType",statusType).query("sortType",sortType)
				.query("classifyType",classifyType).query("order",order).send().body();
	}
	
	public static String getIdByPage(String title) {
		String res = queryCourseByPageFast(title);
		String id = (String)JSONPath.read(res, "$.list[0].id");
		return id;
	}
	
	/**   
	 * @Title: setCourseVisible   
	 * @Description: TODO(批量设置课程的可见范围)   
	 * @param: @param course_ids
	 * @param: @param is_all
	 * @param: @param user_ids
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String setCourseVisibleByBatch(String course_ids,String is_all,String user_ids) {
		return PostRequestTools.RequestBodyByPost("{\r\n" + 
				"  \"course_ids\": \""+course_ids+"\", \r\n" + 
				"  \"project_ids\": \"\", \r\n" + 
				"  \"department_ids\": \"\", \r\n" + 
				"  \"group_ids\": \"\", \r\n" + 
				"  \"is_all\": "+is_all+", \r\n" + 
				"  \"post_ids\": \"\", \r\n" + 
				"  \"user_ids\": \""+user_ids+"\", \r\n" + 
				"  \"access_token\": \""+token+"\"\r\n" + 
				"}", token, setVisibleUrl);
	}
	
	/**   
	 * @Title: setCourseClassify   
	 * @Description: TODO(设置课程的分类)   
	 * @param: @param course_ids
	 * @param: @param classify_id
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String setCourseClassify(String course_ids,String classify_id) {
		return PostRequestTools.RequestFormDataByPost(token, "course_ids", course_ids, "classify_id",classify_id,setClassifyUrl);
		
	}
	
	/**   
	 * @Title: setCourseScore   
	 * @Description: TODO(设置学分)   
	 * @param: @param course_ids
	 * @param: @param score
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String setCourseScore(String course_ids,String score) {
		return PostRequestTools.RequestFormDataByPost(token, "course_ids", course_ids, "credit",score,setScoreUrl);
	}
	
	/**   
	 * @Title: deleteCourse   
	 * @Description: TODO(删除课程)   
	 * @param: @param ids
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String deleteCourse (String ids) {
		return PostRequestTools.RequestFormDataByPost(token, "ids", ids, deleteUrl);
	}
	
	/**   
	 * @Title: releaseCancleCourse   
	 * @Description: TODO(对课程进行取消--cancel  发布--release )   
	 * @param: @param action
	 * @param: @param coursesId
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String releaseCancleCourse(String action,String coursesId) {
		return PostRequestTools.RequestFormDataByPost(token, "action", action, releaseCancleUrl(coursesId));
	}
	
	/**   
	 * @Title: addCourse   
	 * @Description: TODO(添加专题课程  release是发布，draft存草稿)   
	 * @param: @param title
	 * @param: @param introduction
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String addSpecialCourse(String title,String introduction,String teacherId,String courseClassify,String resource1 ,
			String resource2,String original,String isAll,String download,String credit,String init_type) {
		
		return HttpRequest.post(addUrl).query("access_token", token).form("title", title).form("introduction",introduction+"<p></p>").form("coverType","1")
		.form("baseCover","https://oss.coolcollege.cn/1789917624419880960.png").form("teacherId",teacherId).form("courseClassify",courseClassify).
		form("resource","[{\"resourceId\":\""+resource1+"\",\"sort\":0},{\"resourceId\":\""+resource2+"\",\"sort\":1}]").form("original",original)
		.form("isAll",isAll).form("download",download).form("credit",credit).form("init_type",init_type).send().body();
		
	}
	
	
	
	/**   
	 * @Title: addCourse   
	 * @Description: TODO(上传课程)   
	 * @param: @param title
	 * @param: @param coverType
	 * @param: @param introduction
	 * @param: @param teacherId
	 * @param: @param resourceid
	 * @param: @param original
	 * @param: @param isAll
	 * @param: @param userIds
	 * @param: @param download
	 * @param: @param credit
	 * @param: @param init_type
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String addCourse(String title,String coverType,String introduction,String teacherId,String resourceid,String original,String
			isAll,String userIds,String download,String credit,String init_type) {
		return PostRequestTools.RequestFormDataByPost(token, "batchCourse", "[\r\n" + 
				"  {\r\n" + 
				"    \"title\": \""+title+"\", \r\n" + 
				"    \"introduction\": \""+introduction+"<p></p>\", \r\n" + 
				"    \"coverType\": "+coverType+", \r\n" + 
				"    \"cover\": \"\", \r\n" + 
				"    \"baseCover\": \"https://oss.coolcollege.cn/1789917624419880960.png\", \r\n" + 
				"    \"teacherId\": \""+teacherId+"\", \r\n" + 
				"    \"courseClassify\": \""+ClassificationBusines.getPrimaryId()+"\", \r\n" + 
				"    \"resource\": \""+resourceid+"\", \r\n" + 
				"    \"original\": "+original+", \r\n" + 
				"    \"isAll\": "+isAll+", \r\n" + 
				"    \"groupIds\": \"\", \r\n" + 
				"    \"departmentIds\": \"\", \r\n" + 
				"    \"userIds\": \""+userIds+"\", \r\n" + 
				"    \"postIds\": \"\", \r\n" + 
				"    \"download\": "+download+", \r\n" + 
				"    \"credit\": "+credit+", \r\n" + 
				"    \"init_type\": \""+init_type+"\"\r\n" + 
				"  }\r\n" + 
				"]", addBatchCourseUrl);
	}
	
	public static String addCourseBy(String title,String coverType,String introduction,String teacherId,String courseClassify, String resourceid,String original,String
			isAll,String userIds,String download,String credit,String init_type) {
		return PostRequestTools.RequestFormDataByPost(token, "batchCourse", "[\r\n" + 
				"  {\r\n" + 
				"    \"title\": \""+title+"\", \r\n" + 
				"    \"introduction\": \""+introduction+"<p></p>\", \r\n" + 
				"    \"coverType\": "+coverType+", \r\n" + 
				"    \"cover\": \"\", \r\n" + 
				"    \"baseCover\": \"https://oss.coolcollege.cn/1789917624419880960.png\", \r\n" + 
				"    \"teacherId\": \""+teacherId+"\", \r\n" + 
				"    \"courseClassify\": \""+courseClassify+"\", \r\n" + 
				"    \"resource\": \""+resourceid+"\", \r\n" + 
				"    \"original\": "+original+", \r\n" + 
				"    \"isAll\": "+isAll+", \r\n" + 
				"    \"groupIds\": \"\", \r\n" + 
				"    \"departmentIds\": \"\", \r\n" + 
				"    \"userIds\": \""+userIds+"\", \r\n" + 
				"    \"postIds\": \"\", \r\n" + 
				"    \"download\": "+download+", \r\n" + 
				"    \"credit\": "+credit+", \r\n" + 
				"    \"init_type\": \""+init_type+"\"\r\n" + 
				"  }\r\n" + 
				"]", addBatchCourseUrl);
	}
	
	
	/**   
	 * @Title: addCourse   
	 * @Description: TODO(上传带有分类参数课程)   
	 * @param: @param title
	 * @param: @param coverType
	 * @param: @param introduction
	 * @param: @param teacherId
	 * @param: @param resourceid
	 * @param: @param original
	 * @param: @param isAll
	 * @param: @param userIds
	 * @param: @param download
	 * @param: @param credit
	 * @param: @param init_type
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String addCourse(String title,String courseClassify,String coverType,String introduction,String teacherId,String resourceid,String original,String
			isAll,String userIds,String download,String credit,String init_type) {
		return PostRequestTools.RequestFormDataByPost(token, "batchCourse", "[\r\n" + 
				"  {\r\n" + 
				"    \"title\": \""+title+"\", \r\n" + 
				"    \"introduction\": \""+introduction+"<p></p>\", \r\n" + 
				"    \"coverType\": "+coverType+", \r\n" + 
				"    \"cover\": \"\", \r\n" + 
				"    \"baseCover\": \"https://oss.coolcollege.cn/1789917624419880960.png\", \r\n" + 
				"    \"teacherId\": \""+teacherId+"\", \r\n" + 
				"    \"courseClassify\": \""+courseClassify+"\", \r\n" + 
				"    \"resource\": \""+resourceid+"\", \r\n" + 
				"    \"original\": "+original+", \r\n" + 
				"    \"isAll\": "+isAll+", \r\n" + 
				"    \"groupIds\": \"\", \r\n" + 
				"    \"departmentIds\": \"\", \r\n" + 
				"    \"userIds\": \""+userIds+"\", \r\n" + 
				"    \"postIds\": \"\", \r\n" + 
				"    \"download\": "+download+", \r\n" + 
				"    \"credit\": "+credit+", \r\n" + 
				"    \"init_type\": \""+init_type+"\"\r\n" + 
				"  }\r\n" + 
				"]", addBatchCourseUrl);
	}
}
