package study.cases;

import cn.kxy.course.resources.bussiness.CourseBusiness;
import cn.kxy.group.a.business.ImageTextBusiness;
import cn.kxy.setting.bussiness.UserBusiness;
import cn.kxy.study.business.AppStudyBusiness;
import cn.kxy.study.business.MyElectiveTaskBusiness;
import init.cases.InitStudyAuthCourse;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import com.lazy.common.utils.CommonData;
import com.lazy.common.utils.DateUtil;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.UnsupportedEncodingException;

@Test(groups = {"studyProject"})
public class TestMyElectiveTask {

	String title = "MyElectiveTask"+CommonData.getStringRandom(3);
	String id = "";
	String username = UserBusiness.getUsername();
	String resource_id="";
	String zip_resourceId = "";
	String imagetext_id = "";
	@Test(description="1.创建图文课-发布接口", priority=1)
 	public void testImageTextAdd() throws UnsupportedEncodingException  {
 		
 		String title = "图文课" + CommonData.getStringRandom(5);
 		String baseCover = "https://oss.coolcollege.cn/1789917624419880960.png";
 		String contentJsonStr = "{\"blocks\":[{\"key\":\"85lj8\",\"text\":\"text test\",\"type\":\"unstyled\","
 				+ "\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[],\"data\":{}}],\"entityMap\":{}}";
 		String contentJson = JSONObject.parseObject(contentJsonStr).toJSONString();
 		String res = ImageTextBusiness.ImageTextAdd(baseCover,contentJson,"release","",title);
 		imagetext_id = (String) JSONPath.read(res, "$.data");
 		String msg = (String)JSONPath.read(res, "$.msg");
 		Integer code = (Integer) JSONPath.read(res, "$.code");
 		System.out.println("1.创建图文课-发布接口:"+"code="+code+","+"imagetext_id="+imagetext_id);
 		Assert.assertEquals(msg, "新增课程成功","1.创建图文课-发布接口：" + res);
 	}
 	
	
	@Test(description="新增选修任务",priority=2)
	public void testAddElectiveTask() {
		
		String res = MyElectiveTaskBusiness.addElectiveTask(title, DateUtil.getRegularDateForHHMM(0),  DateUtil.getRegularDateForHHMM(2),
				"true", imagetext_id, CourseBusiness.getIdByPage(InitStudyAuthCourse.course_name));
		String msg = (String)JSONPath.read(res, "$.msg");
		System.out.println("this is MyElectiveTask module");
		Assert.assertEquals(msg, "新增个人计划成功！","新增选修任务，实际返回结果："+res);
	}
	@Test(description="查看选修任务列表",priority=3)
	public void testQueryList() {
		String res = MyElectiveTaskBusiness.queryList(title);
		id = (String)JSONPath.read(res, "$.list[0].id");
		String name = (String)JSONPath.read(res, "$.list[0].title");
		String creatorName = (String)JSONPath.read(res, "$.list[0].creatorName");
		int process = (int)JSONPath.read(res, "$.list[0].process");
		Long beginTime = (Long)JSONPath.read(res, "$.list[0].beginTime");
		Long endTime = (Long)JSONPath.read(res, "$.list[0].endTime");
		Assert.assertEquals(creatorName, username,"查看选修任务列表--创建人进行校验，实际返回结果："+res);
		Assert.assertEquals(process, 0,"查看选修任务列表，未进行学习时，对进度进行校验，实际返回结果："+res);
		Assert.assertNotNull(beginTime, "查看选修任务列表，开始时间进行校验，实际返回结果："+res);
		Assert.assertNotNull(endTime, "查看选修任务列表，结束时间进行校验，实际返回结果："+res);
		Assert.assertEquals(name, title,"查看选修任务列表，标题进行校验，实际返回结果："+res);
	}
	
	String course_id = "";
	String zip_course_id="";
	@Test(description="查看我的选修任务详情",priority=4)
	public void testQueryInfo() {
		String res = MyElectiveTaskBusiness.queryInfo(id);
		String name = (String)JSONPath.read(res, "$.data.title");
		String creatorName = (String)JSONPath.read(res, "$.data.creatorName");
		Long beginTime = (Long)JSONPath.read(res, "$.data.beginTime");
		Long endTime = (Long)JSONPath.read(res, "$.data.endTime");
		int studyProgress = (int)JSONPath.read(res, "$.data.courseInfo[0].studyProgress");
		String cour_name = (String)JSONPath.read(res, "$.data.courseInfo[1].title");
		resource_id = (String)JSONPath.read(res, "$.data.courseInfo[0].courseId");
		zip_resourceId = (String)JSONPath.read(res, "$.data.courseInfo[0].courseInfo[0].resource_quiz_vo.resource_id");
		
		course_id = (String)JSONPath.read(res, "$.data.courseInfo[1].courseId");
		zip_course_id = (String)JSONPath.read(res, "$.data.courseInfo[1].courseInfo[0].resource_quiz_vo.resource_id");
		Assert.assertNotNull(beginTime, "查看我的选修任务详情，开始时间进行校验，实际返回结果："+res);
		Assert.assertNotNull(endTime, "查看我的选修任务详情，结束时间进行校验，实际返回结果："+res);
		Assert.assertEquals(studyProgress, 0,"查看我的选修任务详情，创建人进行校验，实际返回结果："+res);
		Assert.assertEquals(creatorName, username,"查看我的选修任务详情，创建人进行校验，实际返回结果："+res);
//		Assert.assertEquals(cour_name, course_name,"查看我的选修任务详情，课程进行校验，实际返回结果："+res);
		Assert.assertEquals(name, title,"查看我的选修任务详情，标题进行校验，实际返回结果："+res);
	}
	@Test(description="查看课件学习进度",priority=5)
	public void testGetSingleResourceProgress() {
		String res = MyElectiveTaskBusiness.getSingleResourceProgress(id, course_id, zip_resourceId);
		String msg = (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg,"获取成功！","查看课件学习进度，实际返回结果："+res);
	}
	
	@Test(description="查看课件详情",priority=6)
	public void testqueryCoursewerInfo() {
		String res = MyElectiveTaskBusiness.queryCourseInfo(resource_id);
		String name = (String)JSONPath.read(res, "$.data.name");
		Assert.assertNotNull(name,"查看课件详情，实际返回结果："+res);
	}
	
//	@Test(description="保存课件进度",priority=7)
//	public void testSaveArtProgress() {
//		String res = AppStudyBusiness.saveProgress("100",id, resource_id, zip_resourceId);
//
//		int course_progress = (int)JSONPath.read(res, "$.course_progress");
//		int total_progress = (int)JSONPath.read(res, "$.total_progress");
//		Assert.assertEquals(total_progress, 50,"保存课件进度--总进度，实际返回结果："+res);
//		Assert.assertEquals(course_progress, 100,"保存课件进度--课件进度，实际返回结果："+res);
//	}
//	@Test(description="保存课件进度后，查看选修任务列表",priority=8)
//	public void testSaveCoursewerQueryList() {
//		String res = MyElectiveTaskBusiness.queryList(title);
//		int process = (int)JSONPath.read(res, "$.list[0].process");
//		Assert.assertEquals(process, 50,"保存课件进度后，查看选修任务列表，对进度进行校验，实际返回结果："+res);
//	}
//	@Test(description="保存课件进度后，查看我的选修任务详情",priority=9)
//	public void testQuerySaveCoursewerInfo() {
//		String res = MyElectiveTaskBusiness.queryInfo(id);
//		int studyProgress = (int)JSONPath.read(res, "$.data.courseInfo[0].studyProgress");
//		int process = (int)JSONPath.read(res, "$.data.process");
//		Assert.assertEquals(process, 50,"保存课件进度后，查看我的选修任务详情，创建人进行校验，实际返回结果："+res);
//		Assert.assertEquals(studyProgress, 100,"保存课件进度后，查看我的选修任务详情，创建人进行校验，实际返回结果："+res);
//	}
	
	@Test(description="查看课程学习进度",priority=10)
	public void testGetSinglCourseProgress() {
//		String res = MyElectiveTaskBusiness.getSingleResourceProgress(id, zip_resourceId, resource_id);
//		String msg = (String)JSONPath.read(res, "$.msg");
//		Assert.assertEquals(msg,"获取成功！","查看课件学习进度，实际返回结果："+res);

	}
	
	@Test(description="查看课程详情",priority=11)
	public void testqueryCourseInfo() {
//		String res = MyElectiveTaskBusiness.queryCourseInfo(course_id);
//		String name = (String)JSONPath.read(res, "$.data.name");
//		Assert.assertNotNull(name,"查看课程详情，实际返回结果："+res);
	}
	
	@Test(description="保存课程进度",priority=12)
	public void testSaveCourseProgress() {
//		String res = AppStudyBusiness.saveProgress("100",id, course_id,zip_course_id);
//		int course_progress = (int)JSONPath.read(res, "$.course_progress");
//		int total_progress = (int)JSONPath.read(res, "$.total_progress");
//		Assert.assertEquals(total_progress, 100,"保存课程进度--总进度，实际返回结果："+res);
//		Assert.assertEquals(course_progress, 100,"保存课程进度--课件进度，实际返回结果："+res);
	}
	@Test(description="保存课件进度后，查看选修任务列表",priority=13)
	public void testSaveCourseQueryList() {
//		String res = MyElectiveTaskBusiness.queryList(title);
//		int process = (int)JSONPath.read(res, "$.list[0].process");
//		Assert.assertEquals(process, 100,"保存课件进度后，查看选修任务列表，对进度进行校验，实际返回结果："+res);
	}
	@Test(description="保存课件进度后，查看我的选修任务详情",priority=14)
	public void testQuerySaveCourseInfo() {
//		String res = MyElectiveTaskBusiness.queryInfo(id);
//		int studyProgress = (int)JSONPath.read(res, "$.data.courseInfo[1].studyProgress");
//		int process = (int)JSONPath.read(res, "$.data.process");
//		Assert.assertEquals(process, 100,"保存课件进度后，查看我的选修任务详情，创建人进行校验，实际返回结果："+res);
//		Assert.assertEquals(studyProgress, 100,"保存课件进度后，查看我的选修任务详情，创建人进行校验，实际返回结果："+res);
	}
	@Test(description="删除我的选修任务",priority=15)
	public void testDelete() {
		String res = MyElectiveTaskBusiness.deleteTask(id);
		String msg = (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "删除学习计划成功","删除我的选修任务，实际返回结果："+res);
	}
	
 	@Test(description="33.图文课删除-图文课已被删除接口", priority=16)
 	public void testCourseDeleted(){
 		String res = ImageTextBusiness.CourseDelete(imagetext_id);
 		String message = (String) JSONPath.read(res, "$.message");
 		System.out.println("33.图文课删除-图文课已被删除接口:"+"message"+message);
 	}
}
