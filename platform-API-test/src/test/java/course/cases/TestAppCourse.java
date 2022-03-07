package course.cases;

import cn.kxy.course.resources.bussiness.AppCourseBusiness;
import cn.lazy.init.cases.InitStudyAuthCourse;
import com.alibaba.fastjson.JSONPath;
import com.lazy.common.utils.CommonData;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestAppCourse extends InitStudyAuthCourse{

	String course_name= "AppKLBGLinux"+CommonData.getStringRandom(5);
	String classificationId = "";
	String courseId = "";
	String resourceId = "";
	String con = "very good";
	@Test(description="查询app端课程分类列表",priority=1)
	public void testQueryClassificationList() {
		String queryClassificationList = AppCourseBusiness.queryClassificationList();
		String name = (String)JSONPath.read(queryClassificationList, "$.classify[0].name");
		classificationId = (String)JSONPath.read(queryClassificationList, "$.classify[0].id");
		Assert.assertTrue(!name.isEmpty(), "查询课程分类列表实际返回结果："+queryClassificationList);
	}
	
	@Test(description="查看app端课程列表",priority=2)
	public void testQueryAppCoursesList() {
		
//		String addCourse = CourseBusiness.addCourse(course_name, "1", "this is desription", LecturerListBusiness.getIdByKeyword(outer_name), 
//				ArticleBusiness.getIdByKeyword(""), "0", "2", UserBusiness.getUserId(), "0", "3", "release");
//		String res = AppCourseBusiness.queryAppCoursesList("", course_name);
//		
//		courseId = (String)JSONPath.read(addCourse, "$.data[0]");
//		String title = (String)JSONPath.read(res, "$.list[0].title");
//		int play_count = (int)JSONPath.read(res, "$.list[0].play_count");
//		int star_level = (int)JSONPath.read(res, "$.list[0].star_level");
//		Assert.assertEquals(title, course_name,"查看app端课程列表实际返回结果："+res);
//		Assert.assertEquals(play_count, 0,"查看app端课程列表实际返回结果："+res);
//		Assert.assertEquals(star_level, 0,"查看app端课程列表实际返回结果："+res);
		
	}
	@Test(description="查询APP端课程详情",priority=3)
	public void testQueryAppCourseInfo() {
//		String res = AppCourseBusiness.queryAppCourseInfo(courseId);
//		resourceId =(String)JSONPath.read(res, "$.resources[0].id");
//		String title = (String)JSONPath.read(res, "$.title");
//		String introduction = (String)JSONPath.read(res, "$.introduction");
//		int award_score = (int)JSONPath.read(res, "$.award_score");
//		
//		String lecturer_name = (String)JSONPath.read(res, "$.lecturer.lecturer_name");
//		Assert.assertEquals(lecturer_name, outer_name,"查询APP端课程详情实际返回结果："+res);
//		Assert.assertEquals(award_score, 3,"查询APP端课程详情实际返回结果："+res);
//		Assert.assertTrue(introduction.contains("this is desription"), "查询APP端课程详情实际返回结果："+res);
//		Assert.assertEquals(title, course_name,"查询APP端课程详情实际返回结果："+res);
	}
	
	@Test(description="查询app端未评论时，课程评论个数",priority=4)
	public void testQueryAppCourseUnComment() {
//		String res = AppCourseBusiness.queryAppCourseComment(courseId);
//		int total = (int)JSONPath.read(res, "$.total");
//		Assert.assertEquals(total, 0,"查询app端未评论时，课程评论个数实际返回结果："+res);

	}
	@Test(description="查询APP端未观看时，课程观看人数",priority=5)
	public void testQueryAppViewer() {
//		String res = AppCourseBusiness.queryAppViewer(courseId);
//		int total = (int)JSONPath.read(res, "$.total");
//		Assert.assertEquals(total, 0,"查询APP端未观看时，观看人数实际返回结果："+res);
	}

	@Test(description="APP端添加课程评论",priority=6)
	public void testAddAppCourseComment() {
		
//		String res = AppCourseBusiness.addAppCourseComment(courseId, con);
//		String content = (String)JSONPath.read(res, "$.comment");
//		String name = (String)JSONPath.read(res, "$.name");
//		Long create_time = (Long)JSONPath.read(res, "$.create_time");
//		Assert.assertTrue(create_time!=null, "APP端添加课程评论实际返回结果："+res);
//		Assert.assertEquals(name, UserBusiness.getUsername(),"APP端添加课程评论实际返回结果："+res);
//		Assert.assertEquals(content, con,"APP端添加课程评论实际返回结果："+res);
	}
	@Test(description="查询app端课程已评论时的详情",priority=7)
	public void testQueryAppCourseComment() {
//		String res = AppCourseBusiness.queryAppCourseComment(courseId);
//		String content = (String)JSONPath.read(res, "$.list[0].comment");
//		String name = (String)JSONPath.read(res, "$.list[0].name");
//		Long create_time = (Long)JSONPath.read(res, "$.list[0].create_time");
//		Assert.assertTrue(create_time!=null, "APP端添加课程评论实际返回结果："+res);
//		Assert.assertEquals(name, UserBusiness.getUsername(),"APP端添加课程评论实际返回结果："+res);
//		Assert.assertEquals(content, con,"APP端添加课程评论实际返回结果："+res);
	}
	
	@Test(description="APP端保存课程播放次数",priority=8)
	public void testAddAppPlayCount () {
//		String res = AppCourseBusiness.addAppPlayCount(courseId);
//		String status = (String)JSONPath.read(res, "$.status");
//		Assert.assertEquals(status, "true","APP端添加课程评论实际返回结果："+res);
	}
	
	@Test(description="查询APP端课程观看人数的列表",priority=10)
	public void testQueryApCourseComment() {
//		String res = AppCourseBusiness.queryAppViewer(courseId);
//		String name = (String)JSONPath.read(res, "$.list[0].name");
//		int progress  = (int)JSONPath.read(res, "$.list[0].progress");
//		Assert.assertEquals(progress, 60,"查询APP端课程观看人数的列表实际返回结果："+res);
//		Assert.assertEquals(name, UserBusiness.getUsername(),"查询APP端课程观看人数的列表实际返回结果："+res);
		
	}
	@Test(description="APP端对课程点赞",priority=11)
	public void testAddAppCourseLike () {
//		String sta = "like";
//		String res = AppCourseBusiness.addAppCourseLike(courseId, sta);
//		String status = (String)JSONPath.read(res, "$.status");
//		Assert.assertEquals(status, sta,"APP端对课程点赞实际返回结果："+res);
	}
	
	@Test (description="查看已评论、已观看、已点赞的APP端课程列表",priority=12)
	public void testQueryAppCoursesListToCommentViewLike () {
//		String res = AppCourseBusiness.queryAppCoursesList("", course_name);
//		int count = (int)JSONPath.read(res, "$.list[0].play_count");
//		Assert.assertEquals(count, 1,"查看已评论、已观看、已点赞的APP端课程列表实际返回结果："+res);
	}
	@Test(description = "查看已评论、已观看、已点赞的B端课程列表",priority=13)
	public void testQueryCourseByPage() {
//		String res = CourseBusiness.queryCourseByPage(course_name, "2", "");
//		int comment_count = (int)JSONPath.read(res, "$.list[0].commentCount");
//		String playCount = (String)JSONPath.read(res, "$.list[0].playCount");
//		int likeCount = (int)JSONPath.read(res, "$.list[0].likeCount");
//		Assert.assertEquals(comment_count, 1,"查看已评论、已观看、已点赞的B端课程列表实际返回结果："+res);
//		Assert.assertEquals(playCount, "1","查看已评论、已观看、已点赞的B端课程列表实际返回结果："+res);
//		Assert.assertEquals(likeCount, 1,"查看已评论、已观看、已点赞的B端课程列表实际返回结果："+res);
	}
	@Test(description = "查看已评论、已观看、已点赞的B端课程管理列表",priority=14)
	public void testCourseManageList() {
//		String res = CourseBusiness.courseManageList(course_name, "all", "released","","","all");
//		String comments  = (String)JSONPath.read(res, "$.list[0].comments");
//		String likes  = (String)JSONPath.read(res, "$.list[0].likes");
//		String plays  = (String)JSONPath.read(res, "$.list[0].plays");
//		int study_count  = (int)JSONPath.read(res, "$.list[0].study_count");
//		Assert.assertEquals(comments, "1","查看已评论、已观看、已点赞的B端课程管理列表实际返回结果："+res);
//		Assert.assertEquals(likes,  "1","查看已评论、已观看、已点赞的B端课程管理列表实际返回结果："+res);
//		Assert.assertEquals(plays,  "1","查看已评论、已观看、已点赞的B端课程管理列表实际返回结果："+res);
//		Assert.assertEquals(study_count, 1,"查看已评论、已观看、已点赞的B端课程管理列表实际返回结果："+res);
	}
	@Test(description="在B端课程管理列表,查看观看人数",priority=15)
	public void testQueryViewers() {
//		String res = CourseBusiness.queryViewers(courseId, UserBusiness.getUsername());
//		String name = (String)JSONPath.read(res, "$.list[0].name");
//		int progress  = (int)JSONPath.read(res, "$.list[0].progress");
//		Long create_time  = (Long)JSONPath.read(res, "$.list[0].create_time");
//		Assert.assertTrue(create_time!=null, "在B端课程管理列表,查看观看人数实际返回结果："+res);
//		Assert.assertEquals(progress, 60,"在B端课程管理列表,查看观看人数实际返回结果："+res);
//		Assert.assertEquals(name, UserBusiness.getUsername(),"在B端课程管理列表,查看观看人数实际返回结果："+res);
	}
	@Test(description="APP端对课程取消点赞",priority=16)
	public void testAddAppCourseUnLike () {
//		String sta = "unlike";
//		String res = AppCourseBusiness.addAppCourseLike(courseId, sta);
//		String status = (String)JSONPath.read(res, "$.status");
//		Assert.assertEquals(status, sta,"APP端对课程取消点赞实际返回结果："+res);
	}
	
	@Test(description="查看APP端推荐的课程，recommend_courses接口",priority=17)
	public void testQueryAppreCommendCourses() {
//		String res = AppCourseBusiness.queryAppreCommendCourses();
//		String title = (String)JSONPath.read(res, "$.courses[0].title");
//		Assert.assertTrue(!title.isEmpty(), "查看APP端推荐的课程，实际返回结果："+res);
	}
	@Test(priority = 18)
	public void end () {
//		CourseBusiness.deleteCourse(courseId);
	}
	
	@Test(description = "获取移动端素材列表",priority = 19)
	public void getMaterialsList() {
//		String materialsList = AppCourseBusiness.getMaterialsList();
//		Assert.assertTrue(materialsList.contains("success"), "获取移动端素材列表，实际返回结果："+materialsList);
	}
	
}
