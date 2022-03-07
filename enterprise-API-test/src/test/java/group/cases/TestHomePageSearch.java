package group.cases;
/**
 * @author wenlingzhi
 *2021年8月31日
 */

import cn.kxy.group.a.business.HomePageSearchBusiness;
import cn.kxy.setting.bussiness.ClassificationBusines;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import com.lazy.common.utils.CommonData;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestHomePageSearch {	
	public static String lever_id = "";
	public static String lecturer_id = "";

	String project_title = "首页搜索学习项目" + CommonData.getStringRandom(5);
	String lecturer_name = "首页搜索讲师" + CommonData.getStringRandom(5);
	String studyplan_title = "首页搜索学习任务" + CommonData.getStringRandom(5);
	String classification_id = ClassificationBusines.getPrimaryId();
 	
 	
 	public static String project_id = "";
 	public static String project_course_id = "";
 	@Test(description="1.创建学习项目接口", priority=1)
 	public void testStudyProjectSave()  {
 		String cover = "https://oss.coolcollege.cn/1810048818682974377.png";
 	    String base_cover= "https://oss.coolcollege.cn/1810048818682974377.png";
 		String course_duration = "4319";
 		long current_time = System.currentTimeMillis();
 		long endTemp = current_time + 24L*7*3600*1000; 
 		long startTemp = current_time + 24L*2*3600*1000; 
 		String start_timeTemp = String.valueOf(current_time);
 		String end_timeTemp = String.valueOf(endTemp);
 		String start = String.valueOf(startTemp);
 		String resFirst = HomePageSearchBusiness.StudyProjectSaveBaseInfo(project_title,lecturer_id,classification_id,"normal",cover,
 				base_cover);
 		project_id = (String) JSONPath.read(resFirst, "$.id"); 
 		project_course_id = (String) JSONPath.read(resFirst, "$.course_id");
 		String resSecond = HomePageSearchBusiness.StudyProjectSaveStageContent(project_id,project_title,cover,start_timeTemp,end_timeTemp,
 				course_duration,lecturer_id);	
 		String resThird = HomePageSearchBusiness.StudyProjectSaveSettings(project_id);
 		String result = (String) JSONPath.read(resThird, "$.result");		
 		System.out.println("1.创建学习项目接口:"+"project_id="+project_id);
 		Assert.assertEquals(result, "true","1.创建学习项目接口" + resThird);
 	}
 	
 	
 	public static String studyplan_id = "";
 	@Test(description="2.创建学习任务接口", priority=2)
 	public void testStudyPlanAdd()  {
// 		System.out.println("4.创建学习任务接口:");
// 		String cover = "https://oss.coolcollege.cn/1807912811305766912.png";
// 		String course_duration = "4319";
// 		long current_time = System.currentTimeMillis();
// 		long endTemp = current_time + 24L*7*3600*1000; 
// 		long startTemp = current_time + 24L*2*3600*1000; 
// 		String start_timeTemp = String.valueOf(current_time);
// 		String end_timeTemp = String.valueOf(endTemp);
// 		String start = String.valueOf(startTemp);
// 		String res = HomePageSearchBusiness.StudyPlanAdd(studyplan_title,start_timeTemp,end_timeTemp,cover,start_timeTemp,start,
// 				course_duration,lecturer_id,"star","","");
// 		studyplan_id = (String)JSONPath.read(res, "$.data");
// 		String msg = (String)JSONPath.read(res, "$.msg");
// 		System.out.println("msg="+msg+","+"studyplan_id="+studyplan_id);
// 		Assert.assertEquals(msg,"新增计划成功！","2.创建学习任务接口"+res);
 	}
 	
 	
 	@Test(description="3.移动端首页搜索-课程接口", priority=3)
 	public void testSearchCourse()  {
 		String res = HomePageSearchBusiness.SearchCourse(project_title);
 		System.out.println("3.移动端首页搜索-课程接口:");
 		String title_data = "";
 		JSONArray eventMsgArray = (JSONArray) JSONPath.read(res, "$.list");
 		for(Object obj :eventMsgArray) {
 			JSONObject jsonObj	=	(JSONObject) JSONObject.parse(obj.toString());
 			if(jsonObj.getString("title").contains(project_title)) {
 				title_data = jsonObj.getString("title");
 			}			
 		}				
 		System.out.println("title_data="+title_data);
 		Assert.assertNotEquals(title_data,null,"3.移动端首页搜索-课程接口：" + res);
 	}
 	
 	
 	@Test(description="4.移动端首页搜索-任务接口", priority=4)
 	public void testSearchTask()  {
 		String res = HomePageSearchBusiness.SearchTask(studyplan_title);
 		System.out.println("4.移动端首页搜索-任务接口:");
 		String title_data = "";
 		JSONArray eventMsgArray = (JSONArray) JSONPath.read(res, "$.list");
 		for(Object obj :eventMsgArray) {
 			JSONObject jsonObj	=	(JSONObject) JSONObject.parse(obj.toString());
 			if(jsonObj.getString("title").contains(studyplan_title)) {
 				title_data = jsonObj.getString("title");
 			}			
 		}				
 		System.out.println("title_data="+title_data);
 		Assert.assertNotEquals(title_data,null,"4.移动端首页搜索-任务接口：" + res);
 	}

 	
 	@Test(description="5.移动端首页搜索-讲师接口", priority=5)
 	public void testSearchLecturer()  {
 		String res = HomePageSearchBusiness.SearchLecturer("Trinity");
 		System.out.println("5.移动端首页搜索-讲师接口:");
 		String title_data = "";
 		JSONArray eventMsgArray = (JSONArray) JSONPath.read(res, "$.list");
 		for(Object obj :eventMsgArray) {
 			JSONObject jsonObj	=	(JSONObject) JSONObject.parse(obj.toString());
 			if(jsonObj.getString("lecturer_name").contains("Trinity")) {
 				title_data = jsonObj.getString("lecturer_name");
 			}			
 		}				
 		System.out.println("title_data="+title_data);
 		Assert.assertNotEquals(title_data,null,"5.移动端首页搜索-讲师接口：" + res);
 	}
 	
 	
 	@Test(description="6.移动端首页搜索-猜你喜欢接口", priority=6)
 	public void testSearchGessUList()  {
 		String res = HomePageSearchBusiness.SearchGessUList("10");
 		System.out.println("6.移动端首页搜索-猜你喜欢接口:");
 		String msg = (String) JSONPath.read(res, "$.msg");
 		System.out.println("msg="+msg);
 		Assert.assertEquals(msg,"","6.移动端首页搜索-猜你喜欢接口：" + res);
 	}
 	
 	
 	
 	@Test(description="7.移动端首页搜索-历史搜索接口", priority=7)
 	public void testSearchHistory()  {
 		String res = HomePageSearchBusiness.SearchHistory("10");
 		System.out.println("7.移动端首页搜索-历史搜索接口:");
 		String msg = (String) JSONPath.read(res, "$.msg");
 		System.out.println("msg="+msg);
 		Assert.assertEquals(msg,"","7.移动端首页搜索-历史搜索接口：" + res);
 	}
 	
 	
 	
 	@Test(description="8.删除学习任务接口", priority=8)
 	public void testDeleteStudy()  {
// 		String res = HomePageSearchBusiness.DeleteStudy(studyplan_id);
// 		String msg = (String) JSONPath.read(res, "$.msg");
// 		System.out.println("8.删除学习任务接口:"+"msg="+msg);
// 		Assert.assertEquals(msg, "删除学习计划成功","8.删除学习任务接口" + msg);
 	}
 	
 	
 	@Test(description="9.删除学习项目接口", priority=9)
 	public void testDeleteProject()  {
 		String res = HomePageSearchBusiness.DeleteProject(project_id);
 		String deleted = (String) JSONPath.read(res, "$.deleted");
 		System.out.println("9.删除学习项目接口:"+"deleted="+deleted);
 		Assert.assertEquals(deleted, "true","9.删除学习项目接口" + deleted);
 	}
 		

}
