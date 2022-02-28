package study.cases;

import init.cases.InitStudyAuthCourse;
import com.lazy.common.utils.CommonData;
import org.testng.annotations.Test;
//import io.qameta.allure.Story;

//@Story("专题课程的流程测试")
public class TestSpecialStudy extends InitStudyAuthCourse{

	String course_name ="Windows"+CommonData.getStringRandom(5);
	String courseId = "";
	String resourceId = "";
	String con = "very good";
	@Test(description="添加专题课程",priority = 1)
	public void testAddSpecialCourse() {
//		CourseBusiness.addSpecialCourse(course_name, "This is a description ", LecturerListBusiness.getIdByKeyword(outer_name), ClassificationBusines.getPrimaryId(),
//				ArticleBusiness.getFirstIdByKeyword(), ArticleBusiness.getSecondIdByKeyword(), "1", "3", "1", "5", "draft");
	}
	@Test(description="发布专题课程",priority=2)
	public void testPublicCourse() {
//		 CourseBusiness.releaseCancleCourse("release",MyBusiness.getMyCreateCoursesIdByKeyword("", course_name) );
	}
	

	@Test(description="管理已发布列表页显示专题课程",priority=3)
	public void testCourseRightReleasedList() {
//		String res = CourseBusiness.courseManageList(course_name, "all", "released","","","all");
//		courseId = (String)JSONPath.read(res, "$.list[0].course_id");
	}
	
	@Test(description="发布专题课程，在课程页面显示---（queryCourseByPage）接口",priority=4)
	public void testExistQueryCourseByPage() {
//		CourseBusiness.queryCourseByPage(course_name, "", "0");
	}
	@Test(description="调整专题课程分类",priority=5)
	public void testSetCourseClassify() {
//		CourseBusiness.setCourseClassify(courseId , ClassificationBusines.getPrimarySecondId());
	}
	
	@Test(description="设置专题课程学分",priority=6)
	public void testSetCourseScore() {
//		CourseBusiness.setCourseScore(courseId , "5");
	}
	
	@Test(description="设置专题课程的可见范围，设置为仅自己可见",priority=7)
	public void setSingleVisible() {
//		 CourseBusiness.setSingleVisible(courseId, "", "3");
	}
	
	@Test(description="批量设置专题课程的可见范围，设置为全员可见",priority=8)
	public void testSetCourseVisible() {
//		CourseBusiness.setCourseVisibleByBatch(courseId, "1", "");
	}
	
	@Test(description="查询APP端专题课程详情",priority=9)
	public void testQueryAppCourseInfo() {
//		String res = AppCourseBusiness.queryAppCourseInfo(courseId);
//		resourceId =(String)JSONPath.read(res, "$.resources[0].id");
		
	}
	
	@Test(description="查询app端未评论时，专题课程评论个数",priority=10)
	public void testQueryAppCourseUnComment() {
//		AppCourseBusiness.queryAppCourseComment(courseId);
	}
	@Test(description="查询APP端未观看时，专题课程观看人数",priority=11)
	public void testQueryAppViewer() {
//		AppCourseBusiness.queryAppViewer(courseId);
	}

	@Test(description="APP端添加专题课程评论",priority=12)
	public void testAddAppCourseComment() {
//		 AppCourseBusiness.addAppCourseComment(courseId, con);
	}
	@Test(description="查询app端专题课程已评论时的详情",priority=13)
	public void testQueryAppCourseComment() {
//		AppCourseBusiness.queryAppCourseComment(courseId);
	}
	
	@Test(description="APP端保存专题课程播放次数",priority=14)
	public void testAddAppPlayCount () {
//		AppCourseBusiness.addAppPlayCount(courseId);
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
//		AppCourseBusiness.addAppCourseLike(courseId, sta);
	}
	@Test(description="APP端对专题课程取消点赞",priority=18)
	public void testAddAppCourseUnLike () {
//		String sta = "unlike";
//		AppCourseBusiness.addAppCourseLike(courseId, sta);
	}
	@Test(description = "清除所有导出数据",priority = 19)
	public void deleteAllRecord() {
//		 PaperExportBusiness.deleteAllRecord();
	}
	
	@Test(description = "导出课程观看记录",priority = 20)
	public void testExportVieweSys() {
//		 CourseBusiness.exportVieweSys(courseId);
	}
	
	@Test(description = "导出课程观看记录后，在下载中心查看",priority = 21)
	public void exportRecordList() {
//		 PaperExportBusiness.exportRecordList();
	}
	
	@Test(description="取消专题课程",priority=22)
	public void testCancelCourse() {
//		CourseBusiness.releaseCancleCourse("cancel",courseId);
	}
	@Test(description="删除专题课程",priority=23)
	public void testDeleteCourse() {
//		CourseBusiness.deleteCourse(courseId);
	}
}
