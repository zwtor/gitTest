package study.cases;

import cn.kxy.group.a.business.ImageTextBusiness;
import cn.kxy.study.business.AppStudyBusiness;
import cn.kxy.study.business.MyElectiveTaskBusiness;
import cn.kxy.study.business.MyStudyTask;
import cn.kxy.study.business.StudyTaskBusiness;
import init.cases.InitStudyAuthCourse;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import com.lazy.common.utils.CommonData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.UnsupportedEncodingException;
//学习任务阶段间闯关
@Test(groups = {"studyProject"})
public class TestStudyStageTrick extends InitStudyAuthCourse{

	String name = "StudyStageTrick"+CommonData.getStringRandom(4);
	String art_name_01 = "History"+CommonData.getStringRandom(5);
	String art_name_02 = "English"+CommonData.getStringRandom(5);
	String id = "";
	String imagetext_id_01 = "";
	String imagetext_id_02 = "";
	String imagetext_id_03 = "";
	@Test(description = "添加供学习任务使用的课件",priority = 1)
	public void testAddArticle_01() throws UnsupportedEncodingException {

 		String title = "图文课" + CommonData.getStringRandom(5);
 		String baseCover = "https://oss.coolcollege.cn/1789917624419880960.png";
 		String contentJsonStr = "{\"blocks\":[{\"key\":\"85lj8\",\"text\":\"text test\",\"type\":\"unstyled\","
 				+ "\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[],\"data\":{}}],\"entityMap\":{}}";
 		String contentJson = JSONObject.parseObject(contentJsonStr).toJSONString();
 		String res = ImageTextBusiness.ImageTextAdd(baseCover,contentJson,"release","",title);
 		imagetext_id_01 = (String) JSONPath.read(res, "$.data");
 		String msg = (String)JSONPath.read(res, "$.msg");
 		Integer code = (Integer) JSONPath.read(res, "$.code");
 		System.out.println("1.创建图文课-发布接口:"+"code="+code+","+"imagetext_id="+imagetext_id_01);
 		Assert.assertEquals(msg, "新增课程成功","1.创建图文课-发布接口：" + res);
	}
	@Test(description = "添加供学习任务使用的课件",priority = 2)
	public void testAddArticle_02() throws UnsupportedEncodingException {

 		String title = "图文课" + CommonData.getStringRandom(5);
 		String baseCover = "https://oss.coolcollege.cn/1789917624419880960.png";
 		String contentJsonStr = "{\"blocks\":[{\"key\":\"85lj8\",\"text\":\"text test\",\"type\":\"unstyled\","
 				+ "\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[],\"data\":{}}],\"entityMap\":{}}";
 		String contentJson = JSONObject.parseObject(contentJsonStr).toJSONString();
 		String res = ImageTextBusiness.ImageTextAdd(baseCover,contentJson,"release","",title);
 		imagetext_id_02 = (String) JSONPath.read(res, "$.data");
 		String msg = (String)JSONPath.read(res, "$.msg");
 		Integer code = (Integer) JSONPath.read(res, "$.code");
 		System.out.println("2.创建图文课-发布接口:"+"code="+code+","+"imagetext_id="+imagetext_id_02);
 		Assert.assertEquals(msg, "新增课程成功","1.创建图文课-发布接口：" + res);
	}
	@Test(description = "获取第一个课件Id",priority = 3)
	public void testGetFirstId() throws UnsupportedEncodingException {

 		String title = "图文课" + CommonData.getStringRandom(5);
 		String baseCover = "https://oss.coolcollege.cn/1789917624419880960.png";
 		String contentJsonStr = "{\"blocks\":[{\"key\":\"85lj8\",\"text\":\"text test\",\"type\":\"unstyled\","
 				+ "\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[],\"data\":{}}],\"entityMap\":{}}";
 		String contentJson = JSONObject.parseObject(contentJsonStr).toJSONString();
 		String res = ImageTextBusiness.ImageTextAdd(baseCover,contentJson,"release","",title);
 		imagetext_id_03 = (String) JSONPath.read(res, "$.data");
 		String msg = (String)JSONPath.read(res, "$.msg");
 		Integer code = (Integer) JSONPath.read(res, "$.code");
 		System.out.println("3.创建图文课-发布接口:"+"code="+code+","+"imagetext_id="+imagetext_id_03);
 		Assert.assertEquals(msg, "新增课程成功","1.创建图文课-发布接口：" + res);
	}
	@Test(description = "获取第二个课件Id",priority = 4)
	public void testGetSecondId() {
//		 art_id_02 = ArticleBusiness.getIdByKeyword(art_name_02);
	}
	@Test(description = "获取第三个课件Id",priority = 5)
	public void testGetThirdId() {
//		 art_id_03 = ArticleBusiness.getIdByKeyword(articlename);
	}
	@Test(description = "添加阶段闯关的学习任务",priority = 6)
	public void testAddStageTrick() {
		String res = StudyTaskBusiness.addStageTrick(name, imagetext_id_01,imagetext_id_02,imagetext_id_03);
		String msg= (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "新增计划成功！","添加线上学习任务，实际返回结果:"+res);
	}
	@Test(description = "获取学习任务的id",priority = 7)
	public void testGetStudyId() {
		String res = StudyTaskBusiness.getStudyTaskList(name, "true", "0", "","");
		id= (String)JSONPath.read(res, "$.list[0].id");
	}
	String res = "";
	@Test(description = "查询web端的学习任务详情,验证第一阶段的第一个任务可以学习",priority = 8)
	public void testMyStudyTaskQueryInfo() {
		res = MyStudyTask.queryInfo(id);
		String canStartStudy= (String)JSONPath.read(res, "$.data.stageList[0].courseMappingList[0].canStartStudy");
		Assert.assertEquals(canStartStudy,"true","查询web端的学习任务详情,验证第一阶段的第一个任务可以学习,实际返回结果："+res);
	}
	
	@Test(description = "查询web端的学习任务详情,验证第一阶段的第二个任务不可以学习",priority = 9)
	public void testMyStudyTaskQueryInfoCheckSecond() {
		String canStartStudy= (String)JSONPath.read(res, "$.data.stageList[0].courseMappingList[1].canStartStudy");
		Assert.assertEquals(canStartStudy,"false","查询web端的学习任务详情,验证第一阶段的第二个任务不可以学习,实际返回结果："+res);
	}
	@Test(description = "查询web端的学习任务详情,验证第二阶段的第一个任务不可以学习",priority = 10)
	public void testMyStudyTaskQueryInfoCheckSecondStage() {
		String canStartStudy= (String)JSONPath.read(res, "$.data.stageList[1].courseMappingList[0].canStartStudy");
		Assert.assertEquals(canStartStudy,"false","查询web端的学习任务详情,验证第二阶段的第一个任务不可以学习,实际返回结果："+res);
	}
	
	@Test(description="查看我的培训任务第一阶段的第一个课件详情",priority=11)
	public void testqueryCoursewerInfo() {
		String res = MyElectiveTaskBusiness.queryCourseInfo(imagetext_id_01);
		String name = (String)JSONPath.read(res, "$.data.name");
		Assert.assertNotNull(name,"查看我的培训任务第一阶段的第一个课件详情，实际返回结果："+res);
	}
	
	String resource_id_01 = "";
	String zip_resourceId_01="";
	
	String resource_id_02 = "";
	String zip_resourceId_02="";
	
	String resource_id_03 = "";
	String zip_resourceId_03="";
	@Test(description="查看我的任务详情",priority=12)
	public void testQueryInfo() {
		String res = MyElectiveTaskBusiness.queryInfo(id);
		resource_id_01 = (String)JSONPath.read(res, "$.data.courseInfo[0].courseId");
		zip_resourceId_01 = (String)JSONPath.read(res, "$.data.courseInfo[0].courseInfo[0].resource_quiz_vo.resource_id");
		
		resource_id_02 = (String)JSONPath.read(res, "$.data.courseInfo[1].courseId");
		zip_resourceId_02 = (String)JSONPath.read(res, "$.data.courseInfo[1].courseInfo[0].resource_quiz_vo.resource_id");
		
		resource_id_03 = (String)JSONPath.read(res, "$.data.courseInfo[2].courseId");
		zip_resourceId_03 = (String)JSONPath.read(res, "$.data.courseInfo[2].courseInfo[0].resource_quiz_vo.resource_id");
		
	}
	
	@Test(description="保存我的培训任务课件进度",priority=13)
	public void testSaveArtProgress() {
		String res = AppStudyBusiness.saveProgress("100",id, resource_id_01, zip_resourceId_01);
		int course_progress = (int)JSONPath.read(res, "$.course_progress");
		Assert.assertEquals(course_progress, 100,"保存我的培训任务课件进度--课件进度，实际返回结果："+res);
	}
	String res_01 = "";
	@Test(description = "查询web端的学习任务详情,验证学完第一阶段的第一个任务时，第二个任务可以学习",priority = 14)
	public void testMyStudyTaskQueryStudyFirstInfo() {
		res_01 = MyStudyTask.queryInfo(id);
		String canStartStudy= (String)JSONPath.read(res_01, "$.data.stageList[0].courseMappingList[1].canStartStudy");
		Assert.assertEquals(canStartStudy,"true","查询web端的学习任务详情,验证学完第一阶段的第一个任务时，第二个任务可以学习,实际返回结果："+res_01);
	}
	@Test(description = "查询web端的学习任务详情,验证学完第一阶段的第一个任务时,验证第二阶段的第一个任务不可以学习",priority = 15)
	public void testMyStudyTaskQueryStudyFirstInfoCheck() {
		String canStartStudy= (String)JSONPath.read(res_01, "$.data.stageList[1].courseMappingList[0].canStartStudy");
		Assert.assertEquals(canStartStudy,"false","查询web端的学习任务详情,验证第二阶段的第一个任务不可以学习,实际返回结果："+res_01);
	}
	
	@Test(description="查看我的培训任务第一阶段的第二个课件详情",priority=16)
	public void testquerySecondCoursewerInfo() {
		String res = MyElectiveTaskBusiness.queryCourseInfo(imagetext_id_02);
		String name = (String)JSONPath.read(res, "$.data.name");
		Assert.assertNotNull(name,"查看我的培训任务第一阶段的第二个课件详情，实际返回结果："+res);
	}
	
	@Test(description="保存我的培训任务课件进度",priority=17)
	public void testSaveSecondArtProgress() {
		String res = AppStudyBusiness.saveProgress("100",id, resource_id_02, zip_resourceId_02);
		int course_progress = (int)JSONPath.read(res, "$.course_progress");
		Assert.assertEquals(course_progress, 100,"保存我的培训任务课件进度--课件进度，实际返回结果："+res);
	}
	
	String res_02 = "";
	@Test(description = "查询web端的学习任务详情,验证学完第一阶段的第一个任务时，第二个任务可以学习",priority = 18)
	public void testMyStudyTaskQueryStudySecondInfo() {
		res_02 = MyStudyTask.queryInfo(id);
		String canStartStudy= (String)JSONPath.read(res_02, "$.data.stageList[0].courseMappingList[1].canStartStudy");
		Assert.assertEquals(canStartStudy,"true","查询web端的学习任务详情,验证学完第一阶段的第一个任务时，第二个任务可以学习,实际返回结果："+res_02);
	}
	@Test(description = "查询web端的学习任务详情,验证学完第一阶段的第一个任务时,验证第二阶段的第一个任务不可以学习",priority = 19)
	public void testMyStudyTaskQueryStudySecondInfoCheck() {
		String canStartStudy= (String)JSONPath.read(res_02, "$.data.stageList[1].courseMappingList[0].canStartStudy");
		Assert.assertEquals(canStartStudy,"true","查询web端的学习任务详情,验证第二阶段的第一个任务不可以学习,实际返回结果："+res_02);
	}
	
	@Test(description = "删除学习任务",priority = 20)
	public void testDeleteStudyTask() {
		String res = StudyTaskBusiness.deleteStudyTask(id);
		String msg= (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "删除学习计划成功","删除考试任务，实际返回结果:"+res);
	}
	String title_01 = "StagePassStudy1"+CommonData.getStringRandom(4);
	String title_02 = "StagePassStudy2"+CommonData.getStringRandom(5);
	@Test(description = "新增阶段内和阶段间都不勾选的学习任务",priority = 21)
	public void testAddStagePassIsFalse() {
		String res = StudyTaskBusiness.addStagePassStudy(title_01, "false", imagetext_id_01);
		String msg= (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "新增计划成功！","新增阶段内和阶段间都不勾选的学习任务，实际返回结果:"+res);
	}
	@Test(description = "新增阶段内都不勾选,阶段间勾选的学习任务",priority = 22)
	public void testAddStagePassIsTrue() {
		String res = StudyTaskBusiness.addStagePassStudy(title_02, "true", imagetext_id_01);
		String msg= (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "新增计划成功！","新增阶段内都不勾选,阶段间勾选的学习任务，实际返回结果:"+res);
	}
	String id_01 = "";
	String id_02 = "";
	@Test(description = "获取学习任务的id",priority = 23)
	public void testGetFirstStudyId() {
		String res = StudyTaskBusiness.getStudyTaskList(title_01, "true", "0", "","");
		id_01= (String)JSONPath.read(res, "$.list[0].id");
	}
	
	@Test(description = "获取学习任务的id",priority = 24)
	public void testGetSecondStudyId() {
		String res = StudyTaskBusiness.getStudyTaskList(title_02, "true", "0", "","");
		id_02= (String)JSONPath.read(res, "$.list[0].id");
	}
	
	@Test(description = "删除学习任务",priority = 25)
	public void testDeleteFirstStudyTask() {
		String res = StudyTaskBusiness.deleteStudyTask(id_01);
		String msg= (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "删除学习计划成功","删除考试任务，实际返回结果:"+res);
	}
	@Test(description = "删除学习任务",priority = 26)
	public void testDeleteSecondStudyTask() {
		String res = StudyTaskBusiness.deleteStudyTask(id_02);
		String msg= (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "删除学习计划成功","删除考试任务，实际返回结果:"+res);
	}
	
	@Test(description="33.图文课删除-图文课已被删除接口", priority=27)
 	public void testCourse1Deleted(){
 		String res = ImageTextBusiness.CourseDelete(imagetext_id_01);
 		String message = (String) JSONPath.read(res, "$.message");
 		System.out.println("33.图文课删除-图文课已被删除接口:"+"message"+message);
 	}
	@Test(description="33.图文课删除-图文课已被删除接口", priority=28)
 	public void testCourse2Deleted(){
 		String res = ImageTextBusiness.CourseDelete(imagetext_id_02);
 		String message = (String) JSONPath.read(res, "$.message");
 		System.out.println("33.图文课删除-图文课已被删除接口:"+"message"+message);
 	}
	@Test(description="33.图文课删除-图文课已被删除接口", priority=29)
 	public void testCourse3Deleted(){
 		String res = ImageTextBusiness.CourseDelete(imagetext_id_03);
 		String message = (String) JSONPath.read(res, "$.message");
 		System.out.println("33.图文课删除-图文课已被删除接口:"+"message"+message);
 	}
}