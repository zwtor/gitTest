package course.cases;

import cn.kxy.course.resources.bussiness.ArticleBusiness;
import cn.kxy.course.resources.bussiness.CourseBusiness;
import cn.kxy.course.resources.bussiness.CourseFrontListInfoBusiness;
import cn.kxy.lecturer.business.LecturerListBusiness;
import cn.kxy.setting.bussiness.UserBusiness;
import cn.lazy.init.cases.InitStudyAuthCourse;
import com.alibaba.fastjson.JSONPath;
import com.lazy.common.utils.CommonData;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestCourseFrontInfo   {

	String course_name ="JenkinsCI"+CommonData.getStringRandom(5);
	String id = "";
	String content = "The course is good, suitable for the study at this stage, and the skills involved are very good";
	@Test(description="查看非原创课程详情，course/selectOne接口",priority=1)
	public void testCourseInto() {
		String res1 = CourseBusiness.addCourse(course_name, "1", "this is desription", LecturerListBusiness.getIdByKeyword(InitStudyAuthCourse.outer_name), 
				ArticleBusiness.getIdByKeyword(""), "0", "2", UserBusiness.getUserId(), "0", "3", "release");
		id = (String)JSONPath.read(res1, "$.data[0]");
		String res = CourseFrontListInfoBusiness.queryInfo(id);
		String introduction= (String)JSONPath.read(res, "$.introduction");
		String lecturer_name= (String)JSONPath.read(res, "$.lecturer.lecturer_name");
		String lecturer_describe= (String)JSONPath.read(res, "$.lecturer.lecturer_describe");
		String adept_field= (String)JSONPath.read(res, "$.lecturer.adept_field");
		int sex= (int)JSONPath.read(res, "$.lecturer.sex");
		String title= (String)JSONPath.read(res, "$.title");
		String playCount= (String)JSONPath.read(res, "$.playCount");
		boolean original= (boolean)JSONPath.read(res, "$.original");
		boolean buy= (boolean)JSONPath.read(res, "$.buy");
		
		Assert.assertEquals(lecturer_name, InitStudyAuthCourse.outer_name,"查看课程详情（对讲师名称进行校验）实际返回结果"+res);
		Assert.assertEquals(lecturer_describe, "jmeter","查看课程详情（对讲师描述进行校验）实际返回结果"+res);
		Assert.assertEquals(adept_field, "auto","查看课程详情（对讲师领域进行校验）实际返回结果"+res);
		Assert.assertEquals(sex, 1,"查看课程详情（对性别进行校验）实际返回结果"+res);
		Assert.assertEquals(playCount, "0","查看课程详情（对播放次数进行校验）实际返回结果"+res);
		Assert.assertEquals(original, false,"查看课程详情（对是否为原创进行校验）实际返回结果"+res);
		Assert.assertEquals(buy, false,"查看课程详情（对是否为购买的商品进行校验）实际返回结果"+res);
		Assert.assertEquals(title, course_name,"查看课程详情（对标题进行校验）实际返回结果"+res);
		Assert.assertTrue(introduction.contains("this is desription"),"查看课程详情（对描述内容进行校验）实际返回结果"+res);
	}
	@Test(description="未评论前查询评论课程列表",priority=2)
	public void testQueryUnCommentList() {
		String res = CourseFrontListInfoBusiness.queryCommentList(id);
		int total = (int)JSONPath.read(res, "$.total");
		Assert.assertEquals(total, 0,"未评论前查询评论课程列表实际返回结果："+res);
	}
	
	@Test(description="未观看前查询观看记录",priority=3)
	public void testQueryUnCourseRecord() {
		String res = CourseFrontListInfoBusiness.queryCourseRecord(id);
		int total = (int)JSONPath.read(res, "$.total");
		Assert.assertEquals(total, 0,"未观看前查询观看记录实际返回结果："+res);
	}
	
	@Test(description="查看热门课程列表",priority=4)
	public void testQueryPopularCourses() {
		String res = CourseFrontListInfoBusiness.queryPopularCourses();
		int total = (int)JSONPath.read(res, "$.total");
		Assert.assertTrue(total>=0,"查看热门课程列表实际返回结果："+res);
	}
	
	@Test(description="查看课程目录",priority=5)
	public void testGetCoursesSourceList() {
//		String res = CourseFrontListInfoBusiness.getCoursesSourceList(id);
//		String name = (String)JSONPath.read(res, "$.courses[0].name");
//		Assert.assertNotNull(name,"查看课程目录实际返回结果："+res);
	}
	
	@Test(description="评论课程",priority=6)
	public void testAddComment() {
		String res = CourseFrontListInfoBusiness.addComment(id, content);
		String msg = (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "success","评论课程实际返回结果："+res);
	}
	@Test(description="查看已评论的课程列表",priority=7)
	public void testQueryCommentEdList() {
		String res = CourseFrontListInfoBusiness.queryCommentList(id);
		String content0 =(String)JSONPath.read(res, "$.list[0].content");
		String name =(String)JSONPath.read(res, "$.list[0].name");
		
		Long createTime =(Long)JSONPath.read(res, "$.list[0].createTime");
		Assert.assertEquals(name, UserBusiness.getUsername(),"查看已评论的课程列表--（name字段）实际返回结果"+res);
		Assert.assertTrue(createTime!=null, "查看已评论的课程列表--(创建时间)实际返回结果："+res);
		Assert.assertEquals(content0, content,"查看已评论的课程列表--（评论内容）实际返回结果："+res);
	}
	

	@Test(description="给课程评分",priority=8)
	public void testAddCommentScore() {
		String score = "4";
		CourseFrontListInfoBusiness.addCommentScore(id, score);
		String res = CourseFrontListInfoBusiness.queryInfo(id);
		int score0= (int)JSONPath.read(res, "$.score");
		Assert.assertEquals(score0, 4,"给课程评分，查看课程评分详情，实际返回结果："+res);
	}
	@Test(description="保存课程观看进度",priority=9)
	public void testSaveCourseProgress () {
//		String resource_id = CourseFrontListInfoBusiness.getCourseFirstSourceId(id);
//		String res = CourseFrontListInfoBusiness.saveCourseProgress(id, resource_id, "60");
//		int progress = (int)JSONPath.read(res, "$.progress");
//		Assert.assertEquals(progress, 60,"保存课程观看进度实际返回结果："+res);
	}
	@Test(description="添加并查看播放次数",priority=10)
	public void testAddPlayCount() {
		String res = "";
		for (int i = 0; i < 2; i++) {
			 res = CourseFrontListInfoBusiness.addPlayCount(id);
		}
		String msg = (String)JSONPath.read(res, "$.msg");
		String res01 = CourseFrontListInfoBusiness.queryInfo(id);
		String playCount= (String)JSONPath.read(res01, "$.playCount");
		Assert.assertEquals(playCount, "2","添加并查看播放次数实际返回结果："+res);
		Assert.assertEquals(msg, "success","添加并查看播放次数实际返回结果："+res);
	}
	
	@Test(description="保存观看进度后，查询观看列表",priority=11)
	public void testQueryCourseRecord() {
//		String res = CourseFrontListInfoBusiness.queryCourseRecord(id);
//		int progress = (int)JSONPath.read(res, "$.list[0].progress");
//		String name = (String)JSONPath.read(res, "$.list[0].userName");
//		Long createTime = (Long)JSONPath.read(res, "$.list[0].createTime");
//		Assert.assertEquals(name, UserBusiness.getUsername(),"保存观看进度后，查询观看列表实际返回结果："+res);
//		Assert.assertEquals(progress, 60,"保存观看进度后，查询观看列表实际返回结果："+res);
//		Assert.assertTrue(createTime!=null, "保存观看进度后，查询观看列表实际返回结果："+res);
	}
	
	@Test(description= "课程管理页面查看观看人数",priority=12)
	public void testQueryViewers() {
//		String res = CourseBusiness.queryViewers(CourseBusiness.getIdByKeyword(course_name, "released"), UserBusiness.getUsername());
//		String name = (String)JSONPath.read(res, "$.list[0].name");
//		Long create_time = (Long)JSONPath.read(res, "$.list[0].create_time");
//		int progress = (int)JSONPath.read(res, "$.list[0].progress");
//		Assert.assertEquals(progress, 60,"课程管理页面查看观看人数实际返回结果："+res);
//		Assert.assertTrue(create_time!=null, "课程管理页面查看观看人数实际返回结果："+res);
//		Assert.assertEquals(name, UserBusiness.getUsername(),"课程管理页面查看观看人数实际返回结果："+res);
	}
	
	@Test(description="删除课程评论",priority=13)
	public void testDeleteComment() {
		String res = CourseFrontListInfoBusiness.deleteComment(id, CourseFrontListInfoBusiness.getFirstCommentId(id));
		String res01 = CourseFrontListInfoBusiness.queryCommentList(id);
		int total = (int)JSONPath.read(res01, "$.total");
		String msg = (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "success","评论课程实际返回结果："+res);
		Assert.assertEquals(total, 0,"删除课程的评论，查看课程评论的列表，实际返回结果："+res);
		
	}
	@Test(description="评论课程时，id为null",priority=14)
	public void testAddCommentIdIsNull() {
		String res = CourseFrontListInfoBusiness.addComment("", "");
		String msg = (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "请评论或评分","评论课程时，id为null实际返回结果："+res);
	}
	@Test(description="给课程评分，id为null",priority=15)
	public void testAddCommentScoreIdIsNull() {
		String res = CourseFrontListInfoBusiness.addCommentScore("", "");
		String msg = (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "请评论或评分","给课程评分，id为null实际返回结果："+res);
	}
	@Test(description="查看推荐的课程",priority=16)
	public void testRecommendCourses() {
		String res = CourseFrontListInfoBusiness.recommendCourses();
		String title = (String)JSONPath.read(res, "$.courses[0].title");
		Assert.assertTrue(!title.isEmpty(), "查看推荐的课程实际返回结果："+res);
	}
	
	@Test(description="查看课程列表资源详情",priority=17)
	public void testQueryResourcesInfo() {
	
	}
	@Test(description="查询课程详情，res带有课件信息的接口",priority = 18)
	public void testQueryCourseInfo() {
//		String res = CourseFrontListInfoBusiness.queryCourseInfo(id);
//		String title =(String)JSONPath.read(res, "$.resources[0].title");
//		CourseBusiness.deleteCourse(id);
//		Assert.assertNotNull(title,"查询课程详情，res带有课件信息"+res);
	}
	
}
