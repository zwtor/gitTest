package course.cases;

import cn.kxy.course.resources.bussiness.ArticleBusiness;
import cn.kxy.course.resources.bussiness.CourseBusiness;
import cn.kxy.lecturer.business.LecturerListBusiness;
import cn.kxy.setting.bussiness.ClassificationBusines;
import cn.lazy.init.cases.InitStudyAuthCourse;
import com.alibaba.fastjson.JSONPath;
import com.lazy.common.utils.CommonData;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestManageCourse extends InitStudyAuthCourse{

	String course_name = "Selenium"+CommonData.getStringRandom(6);
	String id = "";
	
	@Test(description="新增课程到草稿箱",priority=1)
	public void testAddCourse() {
		String res = CourseBusiness.addCourse(course_name, "1", "this is desription", LecturerListBusiness.getIdByKeyword(outer_name), 
				ArticleBusiness.getIdByKeyword(""), "1", "1", "", "0", "3", "draft");
		String msg = (String)JSONPath.read(res, "$.msg");
		id = (String)JSONPath.read(res, "$.data[0]");
		Assert.assertEquals(msg, "保存草稿成功","新增课程到草稿箱实际返回结果："+res);
	}
	
	@Test(description="管理未发布列表页显示",priority=2)
	public void testCourseUnreleasedList() {
		String res = CourseBusiness.courseManageList(course_name, "all", "unreleased","","","all");
		String title = (String)JSONPath.read(res, "$.list[0].title");
		String course_status = (String)JSONPath.read(res, "$.list[0].course_status");
		String classify_name = (String)JSONPath.read(res, "$.list[0].classify_name");
		int is_all = (int)JSONPath.read(res, "$.list[0].is_all");
		int credit = (int)JSONPath.read(res, "$.list[0].credit");
		Assert.assertEquals(title, course_name,"管理未发布列表页显示实际返回结果:"+res);
		Assert.assertEquals(course_status, "draft","管理未发布列表页显示实际返回结果："+res);
		Assert.assertTrue(!classify_name.isEmpty(),"管理未发布列表页显示实际返回结果："+res);
		Assert.assertEquals(is_all, 1,"管理未发布列表页显示实际返回结果："+res);
		Assert.assertEquals(credit, 3,"管理未发布列表页显示实际返回结果："+res);
	}
	
	@Test(description="草稿箱的课程，不应在管理课程已发布页面显示",priority=3)
	public void testCourseReleasedList() {
		String res = CourseBusiness.courseManageList(course_name, "all", "released","","","all");
		int total = (int)JSONPath.read(res, "$.total");
		Assert.assertEquals(total, 0,"草稿箱的课程，不应在管理课程已发布页面显示实际返回结果:"+res);
	}
	
	@Test(description="草稿箱的课程，不应在课程页面显示---（queryCourseByPage）接口",priority=4)
	public void testQueryCourseByPage() {
		String res = CourseBusiness.queryCourseByPage(course_name, "", "0");
		int total = (int)JSONPath.read(res, "$.total");
		Assert.assertEquals(total, 0,"草稿箱的课程，不应在课程页面显示---（queryCourseByPage）接口实际返回结果:"+res);
	}
	
	@Test(description="发布课程",priority=5)
	public void testPublicCourse() {
		String res = CourseBusiness.releaseCancleCourse("release",id);
		String msg = (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "发布成功","新增课程到草稿箱实际返回结果："+res);
	}
	

	@Test(description="管理已发布列表页显示",priority=6)
	public void testCourseRightReleasedList() {
//		String res = CourseBusiness.courseManageList(course_name, "all", "released","","","all");
//		String title = (String)JSONPath.read(res, "$.list[0].title");
//		String classify_name = (String)JSONPath.read(res, "$.list[0].classify_name");
//		int is_all = (int)JSONPath.read(res, "$.list[0].is_all");
//		int credit = (int)JSONPath.read(res, "$.list[0].credit");
//		String can_delete = (String)JSONPath.read(res, "$.list[0].can_delete");
//		Assert.assertEquals(can_delete, "true","管理已发布列表页显示实际返回结果:"+res);
//		Assert.assertEquals(title, course_name,"管理已发布列表页显示实际返回结果:"+res);
//		Assert.assertTrue(!classify_name.isEmpty(),"管理已发布列表页显示实际返回结果："+res);
//		Assert.assertEquals(is_all, 1,"管理已发布列表页显示实际返回结果："+res);
//		Assert.assertEquals(credit, 3,"管理已发布列表页显示实际返回结果："+res);
	}
	
	@Test(description="发布课程，在课程页面显示---（queryCourseByPage）接口",priority=7)
	public void testExistQueryCourseByPage() {
//		String res = CourseBusiness.queryCourseByPage(course_name, "", "0");
//		int total = (int)JSONPath.read(res, "$.total");
//		Assert.assertEquals(total, 1,"发布课程，在课程页面显示---（queryCourseByPage）接口实际返回结果："+res);
	}
	@Test(description="调整课程分类",priority=8)
	public void testSetCourseClassify() {
		String res = CourseBusiness.setCourseClassify(id , ClassificationBusines.getPrimarySecondId());
		String msg = (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "batch set classify success","调整课程分类实际返回结果："+res);
	}
	
	@Test(description="设置课程学分",priority=9)
	public void testSetCourseScore() {
//		String res = CourseBusiness.setCourseScore(id, "5");
//		String res01 = CourseBusiness.courseManageList(course_name, "all", "released","","","course");
//		int credit = (int)JSONPath.read(res01, "$.list[0].credit");
//		String msg = (String)JSONPath.read(res, "$.msg");
//		Assert.assertEquals(credit, 5,"管理已发布列表页显示实际返回结果："+res);
//		Assert.assertEquals(msg, "batch set credit success","设置课程学分实际返回结果："+res);
	}
	
	@Test(description="设置单个课程的可见范围，设置为仅自己可见",priority=10)
	public void setSingleVisible() {
		String res = CourseBusiness.setSingleVisible(id, "", "3");
		String msg = (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "save visible success","设置单个课程的可见范围实际返回结果："+res);
	}
	
	@Test(description="批量设置课程的可见范围，设置为全员可见",priority=11)
	public void testSetCourseVisible() {
		String res = CourseBusiness.setCourseVisibleByBatch(id, "1", "");
		String msg = (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "batch set visible success","批量设置课程的可见范围，设置为全员可见实际返回结果："+res);
	}
	@Test(description="取消课程",priority=12)
	public void testCancelCourse() {
//		String res = CourseBusiness.releaseCancleCourse("cancel",id );
//		String msg = (String)JSONPath.read(res, "$.msg");
//		Assert.assertEquals(msg, "取消发布成功","取消课程实际返回结果："+res);

	}
	
	@Test(description="查看课程详情",priority=13)
	public void testQueryInfo() {
		String res = CourseBusiness.queryCourseInfo(id);
		String title = (String)JSONPath.read(res, "$.title");
		Assert.assertEquals(title, course_name,"查看课程详情实际返回结果:"+res);
	}
	
	@Test(description="删除课程",priority=14)
	public void testDeleteCourse() {
		String res = CourseBusiness.deleteCourse(id);
		String msg = (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "删除课程成功","删除课程实际返回结果："+res);
	}
	@Test(description="在添加课程页面，查询课件列表接口",dataProvider="getCourseList",priority=15)
	public void testGetCourseList(String type,String classify,String keyword,String contentType,String excludeType,int exp) {
		String res = CourseBusiness.getCourseList(classify,keyword , contentType, excludeType);
		int total = (int)JSONPath.read(res, "$.total");
		Assert.assertTrue(total>=exp,"在添加课程页面，查询课件列表接口实际返回结果："+res);
	}
	@DataProvider(name = "getCourseList")
	public Object[][] getCourseList() {
		Object[][] obj = new Object[][] { { "查询全部课程列表", "", "", "","",0 },
				{ "根据分类查看",  ClassificationBusines.getPrimaryId(), "", "","",0},
				{ "根据全部课程类型和关键字查看",  "0", "test", "","",0},{ "根据课程类型和关键字查看",  "0", "", "video","",0}
				,{ "根据课程类型和关键字查看",  "0", "", "audio","",0},{ "根据课程类型和关键字查看",  "0", "", "application","",0}
				,{ "根据课程类型和关键字查看",  "0", "", "h5","",0}};
		return obj;
	}
	@Test(description="编辑课程，选择2门课程，直接发布",priority=16)
	public void editCourseToPublic() {
//		String name= "Postman"+CommonData.getStringRandom(2);
//		String edit_name = "postman"+CommonData.getStringRandom(1);
//		CourseBusiness.addCourse(name, "1", "this is desription", LecturerListBusiness.getIdByKeyword(outer_name), 
//				ArticleBusiness.getIdByKeyword(articlename ), "1", "1", "", "0", "0", "draft");
//		
//		String id =CourseBusiness.getIdByKeyword(name, "unreleased") ;
//		
//		String info_res = CourseFrontListInfoBusiness.queryInfo(id);
//		String baseCover = (String)JSONPath.read(info_res, "$.baseCover");
//		String cover = (String)JSONPath.read(info_res, "$.cover");
//		String res = CourseBusiness.editCourse(id, edit_name, "this is a introduction",cover,baseCover, LecturerListBusiness.getIdByKeyword(outer_name), 
//				ClassificationBusines.getPrimaryId(), "[{\"resourceId\":\""+ArticleBusiness.getFirstIdByKeyword()+"\",\"sort\":0},{\"resourceId\":\""+ArticleBusiness.getSecondIdByKeyword()+"\",\"sort\":1}]", "0", "3", "", "1", "3", "release");
//		String msg = (String)JSONPath.read(res, "$.msg");
//		String delete_res=CourseBusiness.deleteCourse(id);
//		Assert.assertEquals(msg, "编辑课程成功","编辑课程到已发布实际返回结果："+res+",编辑后删除实际返回结果："+delete_res);
	}
}
