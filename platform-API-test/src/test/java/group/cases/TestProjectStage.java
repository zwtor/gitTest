package group.cases;
/**
 * @author wenlingzhi
 *2021年7月6日
 */

import cn.kxy.group.a.business.ProjectStageBusiness;
import cn.kxy.setting.bussiness.ClassificationBusines;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import com.lazy.common.utils.CommonData;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestProjectStage {	
	public static String course_id1 = "";
	public static String course_id2 = "";
	public static String projectstar_id = "";
	public static String projectstarCourse_id = "";
	public static String projectnormal_id = "";
	public static String projectnormalCourse_id = "";
	public static String projectstage_id = "";
	public static String projectstageCourse_id = "";
	String classification_id = ClassificationBusines.getPrimaryId();
	String projectstar_title = "地图模式" + CommonData.getStringRandom(5);
	String projectnormal_title = "普通模式" + CommonData.getStringRandom(5);
	
 	
 	@Test(description="1.获取课件列表接口", priority=1)
 	public void testResourceGetList()  {
 		String res = ProjectStageBusiness.ResourceGetList("video");
 		System.out.println("1.获取课件列表接口:");
 		course_id1 = (String)JSONPath.read(res, "$.list[0].id");
 		course_id2 = (String)JSONPath.read(res, "$.list[1].id");
 		System.out.println("course_id1="+course_id1+","+"course_id2="+course_id2);
 		Assert.assertNotEquals(course_id1,null,"1.获取课件列表接口"+res);
 	}
 	
 	
 	@Test(description="2.创建学习项目-地图模式多个阶段-非闯关模式接口", priority=2)
 	public void testAddProjectStar()  {
 		String cover = "https://oss.coolcollege.cn/1800048808671973376.png";
        String base_cover = "https://oss.coolcollege.cn/1800048808671973376.png";
        String stage1_title = projectstar_title + "阶段1";
        String stage2_title = projectstar_title + "阶段2";
 		String resFirst = ProjectStageBusiness.StudyProjectSaveBaseInfo(projectstar_title,classification_id,"star",cover,base_cover);
 		projectstar_id = (String) JSONPath.read(resFirst, "$.id"); 
 		projectstarCourse_id = (String) JSONPath.read(resFirst, "$.course_id"); 
 		String resSecond = ProjectStageBusiness.StudyProjectSaveStageContent(stage1_title,projectstar_id,course_id1,stage2_title,course_id2);		
 		String resThird = ProjectStageBusiness.StudyProjectSaveSettings(projectstar_id,"false");
 		String result = (String) JSONPath.read(resThird, "$.result");		
 		System.out.println("2.创建学习项目-地图模式多个阶段-非闯关模式接口:"+"projectstar_id="+projectstar_id);
 		Assert.assertEquals(result, "true","2.创建学习项目-地图模式多个阶段-非闯关模式接口" + resThird);
 	}
 	
 	
 	@Test(description="3.创建学习项目-普通模式多个阶段接口", priority=3)
 	public void testAddProjectNormal()  {
 		String cover = "https://oss.coolcollege.cn/1800048808671973376.png";
        String base_cover = "https://oss.coolcollege.cn/1800048808671973376.png";
        String stage1_title = projectnormal_title + "阶段1";
        String stage2_title = projectnormal_title + "阶段2";
 		String resFirst = ProjectStageBusiness.StudyProjectSaveBaseInfo(projectnormal_title,classification_id,"normal",cover,base_cover);
 		projectnormal_id = (String) JSONPath.read(resFirst, "$.id"); 
 		projectnormalCourse_id = (String) JSONPath.read(resFirst, "$.course_id"); 
 		String resSecond = ProjectStageBusiness.StudyProjectSaveStageContent(stage1_title,projectnormal_id,course_id1,stage2_title,course_id2);		
 		String resThird = ProjectStageBusiness.StudyProjectSaveSettings(projectnormal_id,"false");
 		String result = (String) JSONPath.read(resThird, "$.result");		
 		System.out.println("3.创建学习项目-普通模式多个阶段接口:"+"projectnormal_id="+projectnormal_id);
 		Assert.assertEquals(result, "true","3.创建学习项目-普通模式多个阶段接口" + resThird);
 	}
 	
 	@Test(description="4.创建学习项目-地图模式-阶段间闯关接口", priority=4)
 	public void testAddProjectStarStage()  {
 		String cover = "https://oss.coolcollege.cn/1800048808671973376.png";
        String base_cover = "https://oss.coolcollege.cn/1800048808671973376.png";
        String title = projectstar_title + "阶段间闯关";
        String stage1_title = projectstar_title + "阶段1";
        String stage2_title = projectstar_title + "阶段2";
 		String resFirst = ProjectStageBusiness.StudyProjectSaveBaseInfo(title,classification_id,"star",cover,base_cover);
 		projectstage_id = (String) JSONPath.read(resFirst, "$.id"); 
 		projectstageCourse_id = (String) JSONPath.read(resFirst, "$.course_id"); 
 		String resSecond = ProjectStageBusiness.StudyProjectSaveStageContent(stage1_title,projectstage_id,course_id1,stage2_title,course_id2);		
 		String resThird = ProjectStageBusiness.StudyProjectSaveSettings(projectstage_id,"true");
 		String result = (String) JSONPath.read(resThird, "$.result");		
 		System.out.println("4.创建学习项目-地图模式-阶段间闯关接口:"+"projectstage_id="+projectstage_id);
 		Assert.assertEquals(result, "true","4.创建学习项目-地图模式-阶段间闯关接口" + resThird);
 	}
 		
 	
 	@Test(description="5.校验学习项目-地图模式信息接口", priority=5)
 	public void testQueryProject()  {
 		String res = ProjectStageBusiness.QueryProject(projectstar_id);
 		String style_type = (String) JSONPath.read(res, "$.style_type"); 
 		System.out.println("5.校验学习项目-地图模式信息接口:"+"style_type="+style_type);
 		Assert.assertEquals(style_type,"star","5.校验学习项目-地图模式信息接口" + res);
 	}
 	
 	
 	@Test(description="6.校验学习项目-地图模式阶段信息接口", priority=6)
 	public void testMapProject()  {
 		String res1 = ProjectStageBusiness.MapProject(projectstar_id);
 		String res2 = ProjectStageBusiness.MapProject(projectstage_id);
 		Integer process = (Integer) JSONPath.read(res1, "$.data.process"); 
 		Boolean stage_pass1 = (Boolean) JSONPath.read(res1, "$.data.stage_pass"); 
 		Boolean stage_pass2 = (Boolean) JSONPath.read(res2, "$.data.stage_pass"); 
 		System.out.println("6.校验学习项目-地图模式阶段信息接口:"+"process="+process+","+"stage_pass1="+stage_pass1+","+"stage_pass2="+stage_pass2);
 		Assert.assertSame(process,0,"6.校验学习项目-地图模式阶段信息接口" + res1);
 		Assert.assertFalse(stage_pass1,"6.校验学习项目-地图模式阶段信息接口" + res1);
 		Assert.assertTrue(stage_pass2,"6.校验学习项目-地图模式阶段信息接口" + res2);
 	}
 	
 	
 	@Test(description="7.地图模式/普通模式-学习项目自学前的start接口", priority=7)
 	public void testStartStudy()  {
 		String res1= ProjectStageBusiness.StartStudy(projectstarCourse_id);
 		String res2= ProjectStageBusiness.StartStudy(projectnormalCourse_id);
 		String res3= ProjectStageBusiness.StartStudy(projectstageCourse_id);
 		String result = (String) JSONPath.read(res1, "$.result");
 		System.out.println("7.地图模式/普通模式-学习项目自学前的start接口:"+"result="+result);
 		Assert.assertEquals(result,"true","7.地图模式/普通模式-学习项目自学前的start接口" + res1);
 	}
 	
 	
 	
 	@Test(description="8.地图模式/普通模式-PC/移动端学习完学习项目所有阶段接口", priority=8)
 	public void testSaveProcess()  {
 		long currenttime = System.currentTimeMillis();  
		String tempTime = String.valueOf(currenttime);
		//学习项目的第一阶段学习
 		String res1 = ProjectStageBusiness.SaveProcess(projectstarCourse_id,course_id1,tempTime);
 		String res2 = ProjectStageBusiness.SaveProcess(projectnormalCourse_id,course_id1,tempTime);
 		String res3 = ProjectStageBusiness.SaveProcess(projectstageCourse_id,course_id1,tempTime);
 		//学习项目的第二阶段学习
 		String res4 = ProjectStageBusiness.SaveProcess(projectstarCourse_id,course_id2,tempTime);
 		String res5 = ProjectStageBusiness.SaveProcess(projectnormalCourse_id,course_id2,tempTime);
 		String res6 = ProjectStageBusiness.SaveProcess(projectstageCourse_id,course_id2,tempTime);
 		System.out.println("8.地图模式/普通模式-PC/移动端学习完学习项目所有阶段接口:");
 		Integer progress = (Integer)JSONPath.read(res1, "$.progress");
 		System.out.println("progress="+progress);
 		Assert.assertSame(progress,100,"8.地图模式/普通模式-PC/移动端学习完学习项目所有阶段接口"+res1);
 		Assert.assertSame(progress,100,"8.地图模式/普通模式-PC/移动端学习完学习项目所有阶段接口"+res2);
 	}
 	
 	
 	@Test(description="9.查询学分排行榜接口", priority=9)
 	public void tesGetScoreRank()  {
 		long current_time = System.currentTimeMillis(); 
 		long startTemp = current_time + 24L*3600*1000; 
 		long endTemp = current_time + 24L*(-2)*3600*1000; 
 		String startTime = String.valueOf(startTemp);
 		String endTime = String.valueOf(endTemp);
 		String res = ProjectStageBusiness.GetScoreRank(endTime,startTime);
 		System.out.println("9.查询学分排行榜接口:");
 		Integer total = (Integer)JSONPath.read(res, "$.total");
 		System.out.println("total="+total);
 		Assert.assertNotEquals(total,0,"9.查询学分排行榜接口"+res);
 	}

 	
 	@Test(description="10.校验学习完学习项目-是否获得多个阶段的学分接口", priority=10)
 	public void testGetScoreRecordStudy()  {
 		long current_time = System.currentTimeMillis();  
 		long startTemp = current_time + 24L*3600*1000; 
 		long endTemp = current_time + 24L*(-2)*3600*1000; 
 		String startTime = String.valueOf(startTemp);
 		String endTime = String.valueOf(endTemp);
 		String res = ProjectStageBusiness.GetScoreRecord(endTime,startTime);
 		System.out.println("10.校验学习完学习项目-是否获得多个阶段的学分接口:");
 		JSONArray eventMsgArray = (JSONArray) JSONPath.read(res, "$.list");
 		String study_score_record_ids = "";
 		for(Object obj :eventMsgArray) {
 			JSONObject jsonObj	=	(JSONObject) JSONObject.parse(obj.toString());
 			if(jsonObj.getString("eventMsg").contains(projectstar_title)) {
 				study_score_record_ids = jsonObj.getString("id");
 			}			
 		}		
 		System.out.println("study_score_record_ids="+study_score_record_ids);
 		Assert.assertTrue(res.contains(projectstar_title),"10.校验学习完学习项目-是否获得多个阶段的学分接口"+res);
 	}
 	
 	
 	@Test(description="11.删除学习项目接口", priority=11)
 	public void testDeleteProject()  {
 		String res1 = ProjectStageBusiness.DeleteProject(projectstar_id);
 		String res2 = ProjectStageBusiness.DeleteProject(projectnormal_id);
 		String res3 = ProjectStageBusiness.DeleteProject(projectstage_id);
 		String deleted = (String) JSONPath.read(res1, "$.deleted");
 		System.out.println("11.删除学习项目接口:"+"deleted="+deleted);
 		Assert.assertEquals(deleted, "true","11.删除学习项目接口" + deleted);
 	}
 	
 	

}
