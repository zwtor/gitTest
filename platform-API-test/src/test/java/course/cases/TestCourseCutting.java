package course.cases;

import cn.kxy.course.resources.bussiness.AppCourseBusiness;
import cn.kxy.course.resources.bussiness.ArticleBusiness;
import cn.kxy.course.resources.bussiness.CourseBusiness;
import cn.kxy.course.resources.bussiness.CourseCutting;
import cn.kxy.lecturer.business.LecturerListBusiness;
import cn.kxy.my.business.MyBusiness;
import cn.kxy.setting.bussiness.UserBusiness;
import cn.lazy.init.cases.InitStudyAuthCourse;
import com.alibaba.fastjson.JSONPath;
import com.lazy.common.utils.CommonData;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestCourseCutting extends InitStudyAuthCourse {
	String course_name = "CuttingCourse"+CommonData.getStringRandom(5);
	String resource_id = "";
	@Test(description = "获取视频的id",priority = 1)
	public void testGetVideoId() {
		String res = CourseBusiness.getCourseList("0", "", "video", "");
		resource_id = (String)JSONPath.read(res,"$.list[4].id");
	}
	@Test(description="新增原创、可下载、无自学奖励学分、全部用户的课程，保存到已发布",priority=2)
	public void testAddOriginalDownloadToAllrelease() {
		String res = CourseBusiness.addCourse(course_name, "1", "this is desription", LecturerListBusiness.getIdByKeyword(InitStudyAuthCourse.outer_name), 
				resource_id, "1", "3", "", "1", "0", "release");
		course_id = (String)JSONPath.read(res, "$.data[0]");
		String msg = (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "新增课程成功","新增课程到已发布列表实际返回结果："+res);
	}
	String course_id = "";
	@Test(description="查看我的课程列表",priority=3)
	public void testQuerMyCreateCoursesdraftList() {
		String res = MyBusiness.querMyCreateCoursesList("", course_name);
		Assert.assertTrue(res.contains("ai_upgrade_status"),"查看该课程的AI资源，实际返回结果："+res);
	}
	
	@Test(description = "查看该课程的AI资源",priority = 4)
	public void testQueryAIResource() {
		String res = CourseCutting.queryAIResource(course_id);
		Assert.assertTrue(res.contains("ai_upgrade_course_vos"),"查看该课程的AI资源，实际返回结果："+res);
	}
	String class_id = "";
	@Test(description = "查看ai切割的分类",priority =5)
	public void testQueryLevelList() {
		String res = CourseCutting.queryLevelList();
		String name = (String)JSONPath.read(res, "$[0].children[0].name");
		class_id = (String)JSONPath.read(res, "$[0].children[0].id");
		Assert.assertNotNull(name,"查看ai切割的分类"+res);
	}
	
	@Test(description = "对视频进行AI切割",priority = 6)
	public void testSubmitAICutting() {
//		String res = CourseCutting.submitAICutting(course_id, resource_id,class_id);
//		Assert.assertTrue(res.contains("true"),"对视频进行AI切割，实际返回结果："+res);
	}
	
	@Test(description = "resource类型的ai_upgrade/query接口验证",priority = 7)
	public void testQueryResourcesCutting() {
		String res = CourseCutting.queryResourcesCutting(resource_id);
		Assert.assertTrue(res.contains("actualName"),"对视频进行AI切割，实际返回结果："+res);
	}
	
	@Test(description = "ai_upgrade/query_text接口验证",priority = 8)
	public void testQueryResourcesTxtCutting() {
//		String res = CourseCutting.queryResourcesTxtCutting(resource_id);
//		Assert.assertTrue(res.contains("content"),"对视频进行AI切割，实际返回结果："+res);
	}
	
	@Test(description = "course类型ai_upgrade/query",priority = 9)
	public void testQueryCourseCutting() {
		String res = CourseCutting.queryCourseCutting(course_id);
		Assert.assertTrue(res.contains("status"),"对视频进行AI切割，实际返回结果："+res);
	}
	
	@Test(description = "ai切割时，不选分类",priority = 10)
	public void testSubmitAICuttingClassIsNull() {
		String res = CourseCutting.submitAICutting(course_id, resource_id, "");
		Assert.assertTrue(res.contains("true"),"对视频进行AI切割，实际返回结果："+res);
	}
	
	@Test(description = "手动切割视频片段",priority = 11)
	public void testUpdateSegmentations() {
		String res = CourseCutting.updateSegmentations("Segment"+CommonData.getStringRandom(5), "0101", resource_id, "03");
		Assert.assertTrue(res.contains("true"),"手动切割视频片段，实际返回结果："+res);
	}
	
	String segment_id = "";
	Long create_time ;
	@Test(description = "获取切割的片段id",priority = 12)
	public void getSegmentId() {
		String res = CourseCutting.queryResourcesCutting(resource_id);
		segment_id = (String)JSONPath.read(res, "$.segmentation_list[0].id");
		create_time = (Long)JSONPath.read(res, "$.segmentation_list[0].create_time");
	}
	@Test(description = "编辑手动切割的视频",priority = 13)
	public void updateSegmentations () {
		 String res = CourseCutting.updateSegmentations("Segment"+CommonData.getStringRandom(5), "0101",segment_id ,resource_id,String.valueOf(create_time),  "03");
		 Assert.assertTrue(res.contains("true"),"编辑手动切割的视频，实际返回结果："+res);
	}
	
	@Test(description = "删除手动切割的视频片段",priority = 14)
	public void testDeleteSegmentations() {
		String res = CourseCutting.deleteSegmentations(resource_id, segment_id);
//		Assert.assertTrue(res.contains("true"),"删除手动切割的视频片段，实际返回结果："+res);
	}
	@Test(description = "删除课程",priority = 15)
	public void deleteCourse() {
		String res=CourseBusiness.deleteCourse(course_id);
		Assert.assertTrue(res.contains("删除课程成功"),"删除课程，实际返回结果："+res);
	}
	
	@Test(description = "查看移动端热门知识点",priority = 16)
	public void queryKnowledgeSegmentation() {
		String res = CourseCutting.queryKnowledgeSegmentation();
		Assert.assertTrue(res.contains("total"),"查看移动端热门知识点，实际返回结果："+res);
	}
	
	@Test(description = "查看移动端最热",priority = 17)
	public void queryCourseByHottest() {
		String res = CourseCutting.queryCourseByType("hottest");
		Assert.assertTrue(res.contains("total"),"查看移动端最热，实际返回结果："+res);
		
	}
	@Test(description = "查看移动端最新",priority = 18)
	public void queryCourseByNewest() {
		String res = CourseCutting.queryCourseByType("newest");
		Assert.assertTrue(res.contains("total"),"查看移动端最新，实际返回结果："+res);
	}
	
	
	String ai_course_name= "AIAppLinux"+CommonData.getStringRandom(5);
	String courseId = "";
	String resourceId = "";
	@Test(description="查看app端课程列表",priority=19)
	public void testQueryAppCoursesList() {
		
		String res = CourseBusiness.addCourse(ai_course_name, "1", "this is desription", LecturerListBusiness.getIdByKeyword(outer_name), 
				ArticleBusiness.getIdByKeyword(""), "0", "2", UserBusiness.getUserId(), "0", "3", "release");
		courseId = (String)JSONPath.read(res, "$.data[0]");
		
	}
	@Test(description="查询APP端课程详情",priority=20)
	public void testQueryAppCourseInfo() {
		String res = AppCourseBusiness.queryAppCourseInfo(courseId);
		resourceId =(String)JSONPath.read(res, "$.resources[0].id");
	}
	
	@Test(description = "查看移动端Ai切割",priority = 21)
	public void queryAiLearn() {
//		String res = CourseCutting.queryAiLearn(courseId, resourceId);
//		Assert.assertTrue(res.contains("knowledge_graphs"));
	}
	
	@Test(description = "删除课程",priority = 22)
	public void deleteCourse1() {
		String res=CourseBusiness.deleteCourse(courseId);
		Assert.assertTrue(res.contains("删除课程成功"),"删除课程，实际返回结果："+res);
	}
}
