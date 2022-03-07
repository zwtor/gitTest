package course.cases;

import init.cases.InitStudyAuthCourse;
import com.lazy.common.utils.CommonData;
import org.testng.annotations.Test;
//import io.qameta.allure.Story;

//@Story("专题课程的流程测试")
public class TestSpecialCourse extends InitStudyAuthCourse{

	String course_name ="Windows"+CommonData.getStringRandom(5);
	String courseId = "";
	String resourceId = "";
	String con = "very good";
	@Test(description="添加专题课程",priority = 1)
	public void testAddSpecialCourse() {
//		CourseBusiness.addSpecialCourse(course_name, "This is a description ", LecturerListBusiness.getIdByKeyword(outer_name), ClassificationBusines.getPrimaryId(),
//				ArticleBusiness.getSevenIdByKeyword(), ArticleBusiness.getSecondIdByKeyword(), "1", "3", "1", "5", "draft");
	}
	@Test(description="发布专题课程",priority=2)
	public void testPublicCourse() {
//		String res = CourseBusiness.releaseCancleCourse("release",MyBusiness.getMyCreateCoursesIdByKeyword("", course_name) );
//		String msg = (String)JSONPath.read(res, "$.msg");
//		Assert.assertEquals(msg, "发布成功","新增专题课程到草稿箱实际返回结果："+res);
	}
	

	@Test(description="管理已发布列表页显示专题课程",priority=3)
	public void testCourseRightReleasedList() {
//		String res = CourseBusiness.courseManageList(course_name, "all", "released","","","all");
//		courseId = (String)JSONPath.read(res, "$.list[0].course_id");
//		String title = (String)JSONPath.read(res, "$.list[0].title");
//		String classify_name = (String)JSONPath.read(res, "$.list[0].classify_name");
//		int is_all = (int)JSONPath.read(res, "$.list[0].is_all");
//		int credit = (int)JSONPath.read(res, "$.list[0].credit");
//		Assert.assertEquals(title, course_name,"管理已发布列表页显示专题课程实际返回结果:"+res);
//		Assert.assertTrue(!classify_name.isEmpty(),"管理已发布列表页显示专题课程实际返回结果："+res);
//		Assert.assertEquals(is_all, 3,"管理已发布列表页显示专题课程实际返回结果："+res);
//		Assert.assertEquals(credit, 5,"管理已发布列表页显示专题课程实际返回结果："+res);
	}
	
	@Test(description="发布专题课程，在课程页面显示---（queryCourseByPage）接口",priority=4)
	public void testExistQueryCourseByPage() {
//		String res = CourseBusiness.queryCourseByPage(course_name, "", "0");
//		int total = (int)JSONPath.read(res, "$.total");
//		Assert.assertEquals(total, 1,"发布专题课程，在课程页面显示---（queryCourseByPage）接口实际返回结果："+res);
	}
	@Test(description="调整专题课程分类",priority=5)
	public void testSetCourseClassify() {
//		String res = CourseBusiness.setCourseClassify(courseId , ClassificationBusines.getPrimarySecondId());
//		String msg = (String)JSONPath.read(res, "$.msg");
//		Assert.assertEquals(msg, "batch set classify success","调整专题课程分类实际返回结果："+res);
	}
	
	@Test(description="设置专题课程学分",priority=6)
	public void testSetCourseScore() {
//		String res = CourseBusiness.setCourseScore(courseId , "5");
//		String res01 = CourseBusiness.courseManageList(course_name, "all", "released","","","all");
//		int credit = (int)JSONPath.read(res01, "$.list[0].credit");
//		String msg = (String)JSONPath.read(res, "$.msg");
//		Assert.assertEquals(credit, 5,"管理已发布列表页显示专题课程，实际返回结果："+res);
//		Assert.assertEquals(msg, "batch set credit success","设置专题课程学分实际返回结果："+res);
	}
	
	@Test(description="设置专题课程的可见范围，设置为仅自己可见",priority=7)
	public void setSingleVisible() {
//		String res = CourseBusiness.setSingleVisible(courseId, "", "3");
//		String msg = (String)JSONPath.read(res, "$.msg");
//		Assert.assertEquals(msg, "save visible success","设置专题课程的可见范围实际返回结果："+res);
	}
	
	@Test(description="批量设置专题课程的可见范围，设置为全员可见",priority=8)
	public void testSetCourseVisible() {
//		String res = CourseBusiness.setCourseVisibleByBatch(courseId, "1", "");
//		String msg = (String)JSONPath.read(res, "$.msg");
//		Assert.assertEquals(msg, "batch set visible success","设置专题课程的可见范围实际返回结果："+res);
	}
	
	@Test(description="查询APP端专题课程详情",priority=9)
	public void testQueryAppCourseInfo() {
//		String res = AppCourseBusiness.queryAppCourseInfo(courseId);
//		resourceId =(String)JSONPath.read(res, "$.resources[0].id");
//		String title = (String)JSONPath.read(res, "$.title");
//		int award_score = (int)JSONPath.read(res, "$.award_score");
//		
//		String lecturer_name = (String)JSONPath.read(res, "$.lecturer.lecturer_name");
//		Assert.assertEquals(lecturer_name, outer_name,"查询APP端专题课程详情实际返回结果："+res);
//		Assert.assertEquals(award_score, 5,"查询APP端专题课程详情实际返回结果："+res);
//		Assert.assertEquals(title, course_name,"查询APP端专题课程详情实际返回结果："+res);
	}
	
	@Test(description="查询app端未评论时，专题课程评论个数",priority=10)
	public void testQueryAppCourseUnComment() {
//		String res = AppCourseBusiness.queryAppCourseComment(courseId);
//		int total = (int)JSONPath.read(res, "$.total");
//		Assert.assertEquals(total, 0,"查询app端未评论时，专题课程评论个数实际返回结果："+res);

	}
	@Test(description="查询APP端未观看时，专题课程观看人数",priority=11)
	public void testQueryAppViewer() {
//		String res = AppCourseBusiness.queryAppViewer(courseId);
//		int total = (int)JSONPath.read(res, "$.total");
//		Assert.assertEquals(total, 0,"查询APP端未观看时，专题课程观看人数实际返回结果："+res);
	}

	@Test(description="APP端添加专题课程评论",priority=12)
	public void testAddAppCourseComment() {
		
//		String res = AppCourseBusiness.addAppCourseComment(courseId, con);
//		String content = (String)JSONPath.read(res, "$.comment");
//		String name = (String)JSONPath.read(res, "$.name");
//		Long create_time = (Long)JSONPath.read(res, "$.create_time");
//		Assert.assertTrue(create_time!=null, "APP端添加专题课程评论实际返回结果："+res);
//		Assert.assertEquals(name, UserBusiness.getUsername(),"APP端添加专题课程评论实际返回结果："+res);
//		Assert.assertEquals(content, con,"APP端添加专题课程评论实际返回结果："+res);
	}
	@Test(description="查询app端专题课程已评论时的详情",priority=13)
	public void testQueryAppCourseComment() {
//		String res = AppCourseBusiness.queryAppCourseComment(courseId);
//		String content = (String)JSONPath.read(res, "$.list[0].comment");
//		String name = (String)JSONPath.read(res, "$.list[0].name");
//		Long create_time = (Long)JSONPath.read(res, "$.list[0].create_time");
//		Assert.assertTrue(create_time!=null, "APP端添加专题课程评论实际返回结果："+res);
//		Assert.assertEquals(name, UserBusiness.getUsername(),"APP端添加专题课程评论实际返回结果："+res);
//		Assert.assertEquals(content, con,"APP端添加专题课程评论实际返回结果："+res);
	}
	
	@Test(description="APP端保存专题课程播放次数",priority=14)
	public void testAddAppPlayCount () {
//		String res = AppCourseBusiness.addAppPlayCount(courseId);
//		String status = (String)JSONPath.read(res, "$.status");
//		Assert.assertEquals(status, "true","APP端添加专题课程评论实际返回结果："+res);
	}
	
	@Test(description="保存APP端专题课程进度",priority=15)
	public void testSaveAppProgress () {
		

	}
	
	@Test(description="查询APP端专题课程观看人数的列表",priority=16)
	public void testQueryApCourseComment() {
//		String res = AppCourseBusiness.queryAppViewer(courseId);
//		String name = (String)JSONPath.read(res, "$.list[0].name");
//		int progress  = (int)JSONPath.read(res, "$.list[0].progress");
//		Assert.assertEquals(progress, 30,"查询APP端专题课程观看人数的列表实际返回结果："+res);
//		Assert.assertEquals(name, UserBusiness.getUsername(),"查询APP端专题课程观看人数的列表实际返回结果："+res);
//		
	}
	@Test(description="APP端对专题课程点赞",priority=17)
	public void testAddAppCourseLike () {
//		String sta = "like";
//		String res = AppCourseBusiness.addAppCourseLike(courseId, sta);
//		String status = (String)JSONPath.read(res, "$.status");
//		Assert.assertEquals(status, sta,"APP端对专题课程点赞实际返回结果："+res);
	}
	@Test(description="APP端对专题课程取消点赞",priority=18)
	public void testAddAppCourseUnLike () {
//		String sta = "unlike";
//		String res = AppCourseBusiness.addAppCourseLike(courseId, sta);
//		String status = (String)JSONPath.read(res, "$.status");
//		Assert.assertEquals(status, sta,"APP端对专题课程取消点赞实际返回结果："+res);
	}
	@Test(description = "清除所有导出数据",priority = 19)
	public void deleteAllRecord() {
//		String res = PaperExportBusiness.deleteAllRecord();
//		Assert.assertTrue(res.contains("OK"),"清除所有导出数据,实际返回结果："+res);
	}
	
	@Test(description = "导出课程观看记录",priority = 20)
	public void testExportVieweSys() {
//		String res = CourseBusiness.exportVieweSys(courseId);
//		String msg = (String)JSONPath.read(res, "$.msg");
//		Assert.assertEquals(msg, "观看数据明细导出记录已生成","导出观看记录,实际返回结果："+res);
	}
	
	@Test(description = "导出课程观看记录后，在下载中心查看",priority = 21)
	public void exportRecordList() {
//		try {
//			Thread.sleep(3000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//		String res = PaperExportBusiness.exportRecordList();
//		String status = (String)JSONPath.read(res, "$.list[0].status");
//		String type = (String)JSONPath.read(res, "$.list[0].type");
//		Assert.assertFalse(status=="FAILED", "查看导出的结果:"+res);
	}
	
	@Test(description="取消专题课程",priority=22)
	public void testCancelCourse() {
//		String res = CourseBusiness.releaseCancleCourse("cancel",courseId);
//		String msg = (String)JSONPath.read(res, "$.msg");
//		Assert.assertEquals(msg, "取消发布成功","取消专题课程实际返回结果："+res);

	}
	@Test(description="删除专题课程",priority=23)
	public void testDeleteCourse() {
//		String res = CourseBusiness.deleteCourse(courseId);
//		String msg = (String)JSONPath.read(res, "$.msg");
//		Assert.assertEquals(msg, "删除课程成功","删除专题课程实际返回结果："+res);
	}
}
