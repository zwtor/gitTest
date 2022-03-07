package course.cases;

import cn.kxy.course.resources.bussiness.ArticleBusiness;
import cn.kxy.course.resources.bussiness.CourseBusiness;
import cn.kxy.lecturer.business.LecturerListBusiness;
import cn.kxy.setting.bussiness.UserBusiness;
import cn.lazy.init.cases.InitStudyAuthCourse;
import com.alibaba.fastjson.JSONPath;
import com.lazy.common.utils.CommonData;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestFunctionManageCourse  extends InitStudyAuthCourse{

	String course_name_01="Chinesdd"+CommonData.getStringRandom(5);
	String course_name_02="HGIOKJNJK"+CommonData.getStringRandom(5);
	
	String user_id = UserBusiness.getUserId();
	
	String id_01 = "";
	@Test(description="新增非原创、不可下载、自学奖励学分、指定用户的课程，保存到草稿箱",priority=1)
	public void testAddNonOriginalNonDownloadToUser() {
		String res = CourseBusiness.addCourse(course_name_01, "1", "this is desription", LecturerListBusiness.getIdByKeyword(outer_name), 
				ArticleBusiness.getIdByKeyword(""), "0", "2", UserBusiness.getUserId(), "0", "3", "draft");
		String msg = (String)JSONPath.read(res, "$.msg");
		id_01 = (String)JSONPath.read(res, "$.data[0]");
		Assert.assertEquals(msg, "保存草稿成功","新增课程到草稿箱实际返回结果："+res);
		
	}
	
	@Test(description = "获取第一个课程的id",priority= 2)
	public void getMyCreateCoursesIdByKeyword() {
//		id_01 = MyBusiness.getMyCreateCoursesIdByKeyword("", course_name_01);
	}
	
	@Test(description = "发布课程",priority = 3)
	public void releaseCancleCourse() {
		String res01 = CourseBusiness.releaseCancleCourse("release", id_01);
		String msg01 = (String)JSONPath.read(res01, "$.msg");
		Assert.assertEquals(msg01, "发布成功","新增课程到草稿箱实际返回结果："+res01);
	}

	@Test(description="新增原创、可下载、无自学奖励学分、全部用户的课程，保存到已发布",priority=4)
	public void testAddOriginalDownloadToAllrelease() {
		String res = CourseBusiness.addCourse(course_name_02, "1", "this is desription", LecturerListBusiness.getIdByKeyword(outer_name), 
				ArticleBusiness.getIdByKeyword(""), "1", "3", "", "1", "0", "release");
		String msg = (String)JSONPath.read(res, "$.msg");
		id_02 = (String)JSONPath.read(res, "$.data[0]");
		Assert.assertEquals(msg, "新增课程成功","新增课程到已发布列表实际返回结果："+res);
	}
	
	String id_02= "";
	@Test(description = "获取第二个课程id",priority = 5)
	public void getSecondIdByKeyword() {
//		id_02 = CourseBusiness.getIdByKeyword(course_name_02, "released");
	}
	
	@Test(description="在已发布列表将课程设置为全员可见",priority=6)
	public void testSetCourseAllPeople() {
		String res = CourseBusiness.setSingleVisible(id_01, "", "1");
		String msg = (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "save visible success","在已发布列表将课程设置为全员可见实际返回结果："+res);
	}
	
	@Test(description="通过分类，非原创，关键字筛选条件，在考试管理已发布列表页进行查看",priority=7)
	public void testQueryByClassify_Type_Keyword() {
//		String res =CourseBusiness.courseManageList(course_name_01, "all", "released", "notOriginal","","all");
//		String title=(String)JSONPath.read(res, "$.list[0].title");
//		Assert.assertEquals(title, course_name_01,"在课程列表页进行查看，queryCourseByPage接口，通过关键字，分类、非原创查看实际返回结果："+res);
	}
	
	@Test(description="在已发布列表将课程设置为指定人员可见",priority=8)
	public void testSetCourseToPeople() {
		String res = CourseBusiness.setSingleVisible(id_02, UserBusiness.getUserId(), "2");
		String msg = (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "save visible success","在已发布列表将课程设置为指定人员可见实际返回结果："+res);
	}
	
	@Test(description="通过分类，原创，关键字筛选条件，在考试管理已发布列表页进行查看",priority=9)
	public void testQueryByClassify_Type_Original_Keyword() {
//		String res =CourseBusiness.courseManageList(course_name_02, "all", "released", "original", "","all");
//		String course_type=(String)JSONPath.read(res, "$.list[0].course_type");
//		int is_all=(int)JSONPath.read(res, "$.list[0].is_all");
//		String s_credit=(String)JSONPath.read(res, "$.list[0].s_credit");
//		Assert.assertEquals(s_credit, "+0","通过分类，非原创，关键字筛选条件，在考试管理已发布列表页进行查看"+res);
//		Assert.assertEquals(course_type, "original","通过分类，原创，关键字筛选条件，在考试管理已发布列表页进行查看"+res);
//		Assert.assertEquals(is_all, 2,"通过分类，原创，关键字筛选条件，在考试管理已发布列表页进行查看"+res);
	}
	
	@Test(description="在课程列表页进行查看，queryCourseByPage接口，通过关键字，分类、原创查看",priority=10)
	public void testQueryListByClassify_Original_Keyword() {
//		String res = CourseBusiness.queryCourseByPage(course_name_02, "1", "");
//		String title= (String)JSONPath.read(res, "$.list[0].title");
//		boolean original= (boolean)JSONPath.read(res, "$.list[0].original");
//		String teacherName= (String)JSONPath.read(res, "$.list[0].teacherName");
//		int credit= (int)JSONPath.read(res, "$.list[0].credit");
//		String playCount= (String)JSONPath.read(res, "$.list[0].playCount");
//		int commentCount= (int)JSONPath.read(res, "$.list[0].commentCount");
//		int likeCount= (int)JSONPath.read(res, "$.list[0].likeCount");
//		int score= (int)JSONPath.read(res, "$.list[0].score");
//		Assert.assertEquals(score, 0,"在课程列表页进行查看，queryCourseByPage接口，通过关键字，分类、非原创查看（课程星级）实际返回结果："+res);
//
//		Assert.assertEquals(likeCount, 0,"在课程列表页进行查看，queryCourseByPage接口，通过关键字，分类、原创查看实际返回结果："+res);
//		Assert.assertEquals(commentCount, 0,"在课程列表页进行查看，queryCourseByPage接口，通过关键字，分类、原创查看实际返回结果："+res);
//		Assert.assertEquals(playCount, "0","在课程列表页进行查看，queryCourseByPage接口，通过关键字，分类、原创查看实际返回结果："+res);
//		Assert.assertEquals(original, true,"在课程列表页进行查看，queryCourseByPage接口，通过关键字，分类、原创查看实际返回结果："+res);
//		Assert.assertEquals(credit, 0,"在课程列表页进行查看，queryCourseByPage接口，通过关键字，分类、原创查看实际返回结果："+res);
//		Assert.assertEquals(teacherName, outer_name,"在课程列表页进行查看，queryCourseByPage接口，通过关键字，分类、原创查看实际返回结果："+res);
//		Assert.assertEquals(title, course_name_02,"在课程列表页进行查看，queryCourseByPage接口，通过关键字，分类、原创查看实际返回结果："+res);
	}
	
	@Test(description="在已发布列表页直接批量删除课程",priority=11)
	public void testDelete() {
		String res  = CourseBusiness.deleteCourse(id_01+","+id_02);
		String msg = (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "删除课程成功","在已发布列表页直接批量删除课程实际返回结果："+res);
	}
	String title01 = "testNgLJ"+CommonData.getStringRandom(6);
	String title02 = "MavenKH"+CommonData.getStringRandom(6);
	@Test(description="批量添加课程至草稿箱，并删除",priority =12)
	public void testAddMultiCourse() {
		String res = CourseBusiness.addMultiCourse(title01,title02,ArticleBusiness.getSecondIdByKeyword(),ArticleBusiness.getIdByKeyword(""));
		String msg = (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "保存草稿成功","在已发布列表页直接批量删除课程实际返回结果："+res);
	}
	
	String id01 = "";
	String id02 = "";
	@Test(description = "获取批量删除的课程id", priority =13)
	public void getBatchId() {
		 id01 = CourseBusiness.getIdByKeyword(title01, "unreleased");
		 id02 = CourseBusiness.getIdByKeyword(title02, "unreleased");
	}
	
	@Test(description = "删除批量新建的课程",priority =14)
	public void deleteCourse() {
		String res = CourseBusiness.deleteCourse(id01+","+id02);
		System.out.println(res);
	}
	
}
