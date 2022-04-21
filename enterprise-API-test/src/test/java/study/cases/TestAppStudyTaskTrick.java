package study.cases;

import cn.kxy.group.a.business.ImageTextBusiness;
import cn.kxy.study.business.AppStudyBusiness;
import cn.kxy.study.business.StudyTaskBusiness;
import init.cases.InitStudyAuthCourse;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import com.jayway.jsonpath.JsonPath;
import com.lazy.common.utils.CommonData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.UnsupportedEncodingException;

//测试移动端阶段间闯关
@Test(groups = {"studyProject"})
public class TestAppStudyTaskTrick extends InitStudyAuthCourse{

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
	@Test(description = "加载资源",priority = 8)
	public void testLoadResource() {
		AppStudyBusiness.loadResource(id);
	}
	
	String resource_id_01 = "";
	String zip_resourceId_01="";
	
	String resource_id_02 = "";
	String zip_resourceId_02="";
	
	String resource_id_03 = "";
	String zip_resourceId_03="";
	
	@Test(description = "查看移动端学习任务详情",priority =9)
	public void testQueryAppInfo() {
		
		String res = AppStudyBusiness.queryInfo(id);
		String first_stage_can = (String)JSONPath.read(res, "$.stages[0].can_start_study");
		String first_can = (String)JSONPath.read(res, "$.stages[0].course_list[0].can_start_study");
		String second_can = (String)JSONPath.read(res, "$.stages[0].course_list[1].can_start_study");
		String second_stage_can = (String)JSONPath.read(res, "$.stages[1].can_start_study");
		String second_stage_first_can = (String)JSONPath.read(res, "$.stages[1].course_list[0].can_start_study");
		
		resource_id_01 = (String)JSONPath.read(res, "$.stages[0].course_list[0].id");
		zip_resourceId_01 = (String)JSONPath.read(res, "$.stages[0].course_list[0].resource_id");
		
		resource_id_02 = (String)JSONPath.read(res, "$.stages[0].course_list[1].id");
		zip_resourceId_02 = (String)JSONPath.read(res, "$.stages[0].course_list[1].resource_id");
		
		resource_id_03 = (String)JSONPath.read(res, "$.stages[1].course_list[0].id");
		zip_resourceId_03 = (String)JSONPath.read(res, "$.stages[1].course_list[0].resource_id");
		
		Assert.assertEquals(first_stage_can, "true","验证第一阶段可学习，实际返回结果："+res);
		Assert.assertEquals(first_can, "true","验证第一阶段的第一个任务可学习，实际返回结果："+res);
		Assert.assertEquals(second_can, "false","验证第一阶段的第二个任务不可学习，实际返回结果："+res);
		Assert.assertEquals(second_stage_can, "false","验证第二阶段不可学习，实际返回结果："+res);
		Assert.assertEquals(second_stage_first_can, "false","验证第二阶段的第一个任务不可学习，实际返回结果："+res);

	}
	@Test(description = "保存第一阶段的第一个任务的进度",priority = 10)
	public void testSaveProgress() {
		AppStudyBusiness.saveProgress("100", id, resource_id_01, zip_resourceId_01);
	}
	@Test(description = "保存第一阶段的第一个任务的进度后，查看学习任务详情",priority = 11)
	public void testQueryInfo () {
		String res = AppStudyBusiness.queryInfo(id);
		String first_stage_can = (String)JSONPath.read(res, "$.stages[0].can_start_study");
		String first_can = (String)JSONPath.read(res, "$.stages[0].course_list[0].can_start_study");
		String second_can = (String)JSONPath.read(res, "$.stages[0].course_list[1].can_start_study");
		String second_stage_can = (String)JSONPath.read(res, "$.stages[1].can_start_study");
		String second_stage_first_can = (String)JSONPath.read(res, "$.stages[1].course_list[0].can_start_study");
		Assert.assertEquals(first_stage_can, "true","保存第一阶段的第一个任务的进度后，查看学习任务详情，实际返回结果："+res);
		Assert.assertEquals(first_can, "true","保存第一阶段的第一个任务的进度后，查看学习任务详情，实际返回结果："+res);
		Assert.assertEquals(second_can, "true","保存第一阶段的第一个任务的进度后，查看学习任务详情，实际返回结果："+res);
		Assert.assertEquals(second_stage_can, "false","保存第一阶段的第一个任务的进度后，查看学习任务详情，实际返回结果："+res);
		Assert.assertEquals(second_stage_first_can, "false","保存第一阶段的第一个任务的进度后，查看学习任务详情，实际返回结果："+res);
	}
	
	@Test(description = "保存第一阶段的第一个任务的进度",priority = 12)
	public void testSaveSecondProgress() {
		AppStudyBusiness.saveProgress("100", id,  resource_id_02, zip_resourceId_02);
	}
	@Test(description = "保存第一阶段的第一个任务的进度后，查看学习任务详情",priority = 13)
	public void testQueryAppStudyInfo () {
		String res = AppStudyBusiness.queryInfo(id);
		String first_stage_can = (String)JSONPath.read(res, "$.stages[0].can_start_study");
		String first_can = (String)JSONPath.read(res, "$.stages[0].course_list[0].can_start_study");
		String second_can = (String)JSONPath.read(res, "$.stages[0].course_list[1].can_start_study");
		String second_stage_can = (String)JSONPath.read(res, "$.stages[1].can_start_study");
		String second_stage_first_can = (String)JSONPath.read(res, "$.stages[1].course_list[0].can_start_study");
		Assert.assertEquals(first_stage_can, "true","保存第一阶段的第一个任务的进度后，查看学习任务详情，实际返回结果："+res);
		Assert.assertEquals(first_can, "true","保存第一阶段的第一个任务的进度后，查看学习任务详情，实际返回结果："+res);
		Assert.assertEquals(second_can, "true","保存第一阶段的第一个任务的进度后，查看学习任务详情，实际返回结果："+res);
		Assert.assertEquals(second_stage_can, "true","保存第一阶段的第一个任务的进度后，查看学习任务详情，实际返回结果："+res);
		Assert.assertEquals(second_stage_first_can, "true","保存第一阶段的第一个任务的进度后，查看学习任务详情，实际返回结果："+res);
	}
	
	@Test(description = "查看学习任务的合格率，参与率", priority =14)
	public void getStudyTaskList() {
		String res = StudyTaskBusiness.getStudyTaskList(name, "false", "0", "","");
		String joinRate = JsonPath.read(res, "$.list[0].joinRate");
		Assert.assertEquals(joinRate,"100", "查看学习任务的合格率，参与率，实际返回结果:"+res);
	}
	
	@Test(description = "删除学习任务",priority = 15)
	public void testDeleteStudyTask() {
		String res = StudyTaskBusiness.deleteStudyTask(id);
		String msg= (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "删除学习计划成功","删除考试任务，实际返回结果:"+res);
	}
	@Test(description="16.图文课删除-图文课已被删除接口", priority=16)
 	public void testCourse1Deleted(){
 		String res = ImageTextBusiness.CourseDelete(imagetext_id_01);
 		String message = (String) JSONPath.read(res, "$.message");
 		System.out.println("33.图文课删除-图文课已被删除接口:"+"message"+message);
 	}
	@Test(description="17.图文课删除-图文课已被删除接口", priority=17)
 	public void testCourse2Deleted(){
 		String res = ImageTextBusiness.CourseDelete(imagetext_id_02);
 		String message = (String) JSONPath.read(res, "$.message");
 		System.out.println("33.图文课删除-图文课已被删除接口:"+"message"+message);
 	}
	@Test(description="18.图文课删除-图文课已被删除接口", priority=18)
 	public void testCourse3Deleted(){
 		String res = ImageTextBusiness.CourseDelete(imagetext_id_03);
 		String message = (String) JSONPath.read(res, "$.message");
 		System.out.println("33.图文课删除-图文课已被删除接口:"+"message"+message);
 	}
}
