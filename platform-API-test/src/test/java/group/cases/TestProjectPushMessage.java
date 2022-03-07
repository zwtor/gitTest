package group.cases;

import cn.kxy.group.a.business.ProjectPushMessageBusiness;
import cn.kxy.setting.bussiness.ClassificationBusines;
import com.alibaba.fastjson.JSONPath;
import com.google.common.base.Splitter;
import com.lazy.common.utils.CommonData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Map;

public class TestProjectPushMessage {	
	public static String questionBank_id = "";

	String title1 = "第一个考试";
 	String title2 = "第二个考试";
 	String title3 = "第三个考试";
 	String project_title1 = "推送全部内容"+ CommonData.getStringRandom(5);
 	String project_title2 = "推送特定内容"+ CommonData.getStringRandom(5);
 	String classification_id = ClassificationBusines.getPrimaryId();
	
 	
	@Test(description="1.查询题库id接口", priority=1)
 	public void testResourceClassifyAdd()  {
 		String res = ProjectPushMessageBusiness.QueryQuestionBankList("ProportionalBank1");
 		System.out.println("1.查询题库id接口:");
 		questionBank_id =  (String)JSONPath.read(res, "$.data.list[0].id");
 		System.out.println("questionBank_id="+questionBank_id);
		Assert.assertNotEquals(questionBank_id,null,"1.查询题库id接口"+res);
 	}
	
	
	public static String project_all_id = "";
 	public static String projectCourse_all_id = "";
 	String cover = "https://oss.coolcollege.cn/1810048818682974377.png";
    String base_cover= "https://oss.coolcollege.cn/1810048818682974377.png";
 	@Test(description="2.创建学习项目-用于推送完整内容接口", priority=2)
 	public void testStudyProjectSave()  {		
 		String resFirst = ProjectPushMessageBusiness.StudyProjectSaveBaseInfo(project_title1,classification_id,"normal",cover,base_cover);
 		project_all_id = (String) JSONPath.read(resFirst, "$.id"); 
 		projectCourse_all_id = (String) JSONPath.read(resFirst, "$.course_id");	
 		String resSecond = ProjectPushMessageBusiness.StudyProjectSaveStageContent(project_all_id,title1,"2",questionBank_id,"1","10","1","10",
 				"true",title2,"3",title3,"2","false");	
 		String resThird = ProjectPushMessageBusiness.StudyProjectSaveSettings(project_all_id);
 		String result = (String) JSONPath.read(resThird, "$.result");		
 		System.out.println("2.创建学习项目-用于推送完整内容接口:"+"project_all_id="+project_all_id+","+"projectCourse_all_id="+projectCourse_all_id+","
 		+"resSecond="+resSecond);
 		Assert.assertEquals(result, "true","2.创建学习项目-用于推送完整内容接口" + resThird);
 	}
 	
 	
 	public static String project_part_id = "";
 	public static String projectCourse_part_id = "";
 	@Test(description="3.创建学习项目-用于推送特定内容接口", priority=3)
 	public void testStudyProjectSavePart()  {
 		String resFirst = ProjectPushMessageBusiness.StudyProjectSaveBaseInfo(project_title2,classification_id,"normal",cover,base_cover);
 		project_part_id = (String) JSONPath.read(resFirst, "$.id"); 
 		projectCourse_part_id = (String) JSONPath.read(resFirst, "$.course_id");	
 		String resSecond = ProjectPushMessageBusiness.StudyProjectSaveStageContent(project_part_id,title1,"2",questionBank_id,"1","10","1","10",
 				"true",title2,"3",title3,"2","false");	
 		String resThird = ProjectPushMessageBusiness.StudyProjectSaveSettings(project_part_id);
 		String result = (String) JSONPath.read(resThird, "$.result");		
 		System.out.println("3.创建学习项目-用于推送特定内容接口:"+"project_part_id="+project_part_id+","+"projectCourse_part_id="+projectCourse_part_id+","
 		+"resSecond="+resSecond);
 		Assert.assertEquals(result, "true","3.创建学习项目-用于推送特定内容接口" + resThird);
 	}
 	
 	
 	public static String course_all_id = "";
 	public static String course_part_id = "";
 	@Test(description="4.获取学习项目中资源信息接口", priority=4)
 	public void testProjectQuery()  {
 		String res_all = ProjectPushMessageBusiness.ProjectQuery(project_all_id);
 		String res_part = ProjectPushMessageBusiness.ProjectQuery(project_part_id);
 		System.out.println("4.获取学习项目中资源信息接口:");
 		course_all_id =  (String)JSONPath.read(res_all, "$.stages[0].resources[0].course_id");
 		course_part_id =  (String)JSONPath.read(res_part, "$.stages[0].resources[0].course_id");
 		System.out.println("course_all_id="+course_all_id+","+"course_part_id="+course_part_id);
		Assert.assertNotEquals(course_all_id,null,"4.获取学习项目中资源信息接口"+res_all);
 	}
 	
 	
 	
 	@Test(description="5.学习项目推送完整内容接口", priority=5)
 	public void testPushMessageAll()  {
 		String res = ProjectPushMessageBusiness.PushMessage(project_all_id);
 		System.out.println("5.学习项目推送完整内容接口:");
 		String result =  (String)JSONPath.read(res, "$.result");
 		System.out.println("result="+result);
		Assert.assertEquals(result,"true","5.学习项目推送完整内容接口"+res);
 	}
 	
 	
 	@Test(description="6.学习项目推送特定内容接口", priority=6)
 	public void testPushMessageAppoint()  {
 		String res = ProjectPushMessageBusiness.PushMessageAppoint(project_part_id,course_part_id);
 		System.out.println("6.学习项目推送特定内容接口:");
 		String result =  (String)JSONPath.read(res, "$.result");
 		System.out.println("result="+result);
		Assert.assertEquals(result,"true","6.学习项目推送特定内容接口"+res);
 	}
 	
 	
 	//用于后边方法使用
 	public static String getParam(String url, String name) {
 	    String params = url.substring(url.indexOf("?") + 1, url.length());
 	    Map<String, String> split = Splitter.on("&").withKeyValueSeparator("=").split(params);
 	    return split.get(name);
 	}	
 	
 	
 	@Test(description="7.消息中心列表-校验是否收到消息+重点课标识接口", priority=7)
 	public void testMessageList()  {
// 		String res = ProjectPushMessageBusiness.MessageList();		
// 		String message_url_all = "";
// 		JSONArray contentArray = (JSONArray) JSONPath.read(res, "$.list");
// 		for(Object obj :contentArray) {
// 			JSONObject jsonObj	=	(JSONObject) JSONObject.parse(obj.toString());
// 			if(jsonObj.getString("content").contains(project_title1)) {
// 				message_url_all = jsonObj.getString("message_url");
// 			}			
// 		}
// 		String all_all_checked = getParam(message_url_all,"all_checked");//获取message_url字符串中all_checked的值
// 		String message_url_part = "";
// 		for(Object obj :contentArray) {
// 			JSONObject jsonObj	=	(JSONObject) JSONObject.parse(obj.toString());
// 			if(jsonObj.getString("content").contains(project_title2)) {
// 				message_url_part = jsonObj.getString("message_url");
// 			}			
// 		}	
// 		String part_all_checked = getParam(message_url_part,"all_checked");
// 		System.out.println("7.消息中心列表-校验是否收到消息+重点课标识接口:");
// 		String code =  (String)JSONPath.read(res, "$.code");
// 		System.out.println("code="+code);		
//		Assert.assertEquals(all_all_checked,"true","7.消息中心列表-校验是否收到消息+重点课标识接口"+res);
//		Assert.assertEquals(part_all_checked,"false","7.消息中心列表-校验是否收到消息+重点课标识接口"+res);
// 		Assert.assertEquals(code,"success","7.消息中心列表-校验是否收到消息+重点课标识接口"+res);
 	}
 	
 	
 	@Test(description="8.删除学习项目接口", priority=8)
 	public void testDeleteProject()  {
 		String res_all = ProjectPushMessageBusiness.DeleteProject(project_all_id);
 		String res_part = ProjectPushMessageBusiness.DeleteProject(project_part_id);
 		String deleted = (String) JSONPath.read(res_all, "$.deleted");
 		System.out.println("8.删除学习项目接口:"+"deleted="+deleted);
 		Assert.assertEquals(deleted, "true","8.删除学习项目接口" + deleted);
 	}
 	
 	
 	

}
