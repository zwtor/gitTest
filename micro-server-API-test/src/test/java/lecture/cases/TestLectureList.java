package lecture.cases;

import cn.kxy.base.business.BaseBusiness;
import cn.kxy.course.resources.bussiness.ArticleBusiness;
import cn.kxy.course.resources.bussiness.CourseBusiness;
import cn.kxy.lecturer.business.LecturerListBusiness;
import cn.kxy.lecturer.business.LecturersGracefulBearingBusiness;
import cn.kxy.setting.bussiness.UserBusiness;
import cn.lazy.init.cases.InitStudyAuthCourse;
import com.alibaba.fastjson.JSONPath;
import com.lazy.common.utils.CommonData;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestLectureList extends InitStudyAuthCourse {
	String outer_name = "Alice"+CommonData.getStringRandom(5);
	String outer_name01="NASJKLOh"+CommonData.getStringRandom(5);
	String course_name_02 ="Python"+CommonData.getStringRandom(5);
	String user_name=UserBusiness.getUsername();
	String id01= "";
	String id02= "";
	String lecturerlevel_id = "";
	@Test(description="新增内部讲师",priority=1)
	public void testAddInsideLecturer() {
//		String res = LecturerListBusiness.addLecturer(user_name,"0", "13526231231","inside" ,UserBusiness.getUserId(), BaseBusiness.lecturerLevel, "uiauto", "selenium");
//		id01 =(String)JSONPath.read(res, "$.id");
//		lecturerlevel_id =  LecturerLevelBusiness.getIdByKeyword(BaseBusiness.lecturerLevel);
//		Assert.assertTrue(!id01.isEmpty(), "新增内部讲师实际返回结果："+res);
	}
	@Test(description="新增外部讲师",priority=2)
	public void testAddOutsideLecturer() {
		
		String res = LecturerListBusiness.addLecturer(outer_name,"1", "13526231231","outside" ,"", BaseBusiness.lecturerLevel, "auto", "jmeter");
		String id02 =(String)JSONPath.read(res, "$.id");
		LecturerListBusiness.deleteLecturerByKeyword(outer_name);
		Assert.assertTrue(!id02.isEmpty(), "新增外部讲师实际返回结果："+res);
	}
	
	@Test(description="停用讲师",priority=3)
	public void testStopLecturer() {
//		String res	= LecturerListBusiness.updateStatus("0",id01);
//		String update =(String)JSONPath.read(res, "$.update");
//		Assert.assertEquals(update, "true","停用讲师实际返回结果："+res);
	}
	
	@Test(description="查询讲师详情",priority=4)
	public void testQueryLecturerInfo() {
		
//		String res = LecturerListBusiness.queryInfo(id01);
//		String lecturer_name =(String)JSONPath.read(res, "$.lecturer_name");
//		String lecturer_describe =(String)JSONPath.read(res, "$.lecturer_describe");
//		Assert.assertEquals(lecturer_name,user_name ,"查询讲师详情实际返回结果："+res);
//		Assert.assertEquals(lecturer_describe, "selenium","查询讲师详情实际返回结果："+res);
	}
	
	@Test(description="查询已停用讲师列表页",priority=5)
	public void testUnusedLecturerList() {
//		String res=LecturerListBusiness.queryLecturerList(user_name, "0", lecturerlevel_id);
//		int sex= (int)JSONPath.read(res, "$.list[0].sex");
//		String level_name =(String)JSONPath.read(res, "$.list[0].level_name");
//		int status= (int)JSONPath.read(res, "$.list[0].status");
//		Long create_time =(Long)JSONPath.read(res, "$.list[0].create_time");
//		String create_user_name =(String)JSONPath.read(res, "$.list[0].create_user_name");
//		String lecturer_name =(String)JSONPath.read(res, "$.list[0].lecturer_name");
//		Assert.assertEquals(create_user_name, user_name,"查询已停用讲师列表页实际返回结果："+res);
//		Assert.assertEquals(lecturer_name, user_name,"查询已停用讲师列表页实际返回结果："+res);
//		Assert.assertTrue(level_name!=null, "查询已停用讲师列表页实际返回结果："+res);
//		Assert.assertTrue(create_time!=null, "查询已停用讲师列表页实际返回结果："+res);
//		Assert.assertEquals(sex, 0,"查询已停用讲师列表页实际返回结果："+res);
//		Assert.assertEquals(status, 0,"查询已停用讲师列表页实际返回结果："+res);
	}
	
	@Test(description="查询全部讲师列表页",priority=6)
	public void testAllLecturerList() {
//		String res=LecturerListBusiness.queryLecturerList(user_name, "", lecturerlevel_id);
//		int sex= (int)JSONPath.read(res, "$.list[0].sex");
//		String level_name =(String)JSONPath.read(res, "$.list[0].level_name");
//		int status= (int)JSONPath.read(res, "$.list[0].status");
//		Long create_time =(Long)JSONPath.read(res, "$.list[0].create_time");
//		String create_user_name =(String)JSONPath.read(res, "$.list[0].create_user_name");
//		String lecturer_name =(String)JSONPath.read(res, "$.list[0].lecturer_name");
//		
//		Assert.assertTrue(res.contains("course_taught_duration"),"查询全部讲师列表页实际返回结果"+res);
//		Assert.assertTrue(res.contains("offline_course_duration"),"查询全部讲师列表页实际返回结果"+res);
//		Assert.assertEquals(create_user_name, user_name,"查询全部讲师列表页实际返回结果："+res);
//		Assert.assertEquals(lecturer_name,user_name,"查询全部讲师列表页实际返回结果："+res);
//		Assert.assertTrue(level_name!=null, "查询全部讲师列表页实际返回结果："+res);
//		Assert.assertTrue(create_time!=null, "查询全部讲师列表页实际返回结果："+res);
//		Assert.assertEquals(sex, 0,"查询全部讲师列表页实际返回结果："+res);
//		Assert.assertEquals(status, 0,"查询全部讲师列表页实际返回结果："+res);
	}
	
	@Test(description="查询已启用讲师列表页",priority=7)
	public void testUsedLecturerList() {
//		LecturerListBusiness.updateStatus("1",id01);
//		String res=LecturerListBusiness.queryLecturerList(UserBusiness.getUsername(), "1", lecturerlevel_id);
//		int sex= (int)JSONPath.read(res, "$.list[0].sex");
//		String level_name =(String)JSONPath.read(res, "$.list[0].level_name");
//		int status= (int)JSONPath.read(res, "$.list[0].status");
//		Long create_time =(Long)JSONPath.read(res, "$.list[0].create_time");
//		String create_user_name =(String)JSONPath.read(res, "$.list[0].create_user_name");
//		String lecturer_name =(String)JSONPath.read(res, "$.list[0].lecturer_name");
//		
//		Assert.assertEquals(create_user_name, user_name,"查询已启用讲师列表页实际返回结果："+res);
//		Assert.assertEquals(lecturer_name,user_name,"查询已启用讲师列表页实际返回结果："+res);
//		Assert.assertTrue(level_name!=null, "查询已启用讲师列表页实际返回结果："+res);
//		Assert.assertTrue(create_time!=null, "查询已启用讲师列表页实际返回结果："+res);
//		Assert.assertEquals(sex, 0,"查询已启用讲师列表页实际返回结果："+res);
//		Assert.assertEquals(status, 1,"查询已启用讲师列表页实际返回结果："+res);
	}
	@Test(description="删除讲师",priority=8)
	public void testDeleteLecturer() {
//		String res = LecturerListBusiness.deleteLecturerByKeyword(user_name);
//		String msg = (String)JSONPath.read(res, "$.deleted");
//		Assert.assertEquals(msg, "true","删除讲师实际返回结果："+res);
	}
	@Test(description="编辑讲师并删除",priority=9)
	public void testEditLecturer() {
//		String outer_name="Smith"+CommonData.getStringRandom(5);
//		LecturerListBusiness.addLecturer(UserBusiness.getUsername(),"0", "13526231231","inside" ,UserBusiness.getUserId(), BaseBusiness.lecturerLevel, "uiauto", "selenium");
//		String res01 = LecturerListBusiness.editLecturer(outer_name,"1", "13526231231","outside" ,"", BaseBusiness.outlecturerLevel, "auto", "jmeter",LecturerListBusiness.getIdByKeyword(UserBusiness.getUsername()));
//
//		String res = LecturerListBusiness.deleteLecturerByKeyword(outer_name);
//		String msg = (String)JSONPath.read(res, "$.deleted");
//		String res_list=LecturerListBusiness.queryLecturerList(outer_name, "", "");
//		int total = (int)JSONPath.read(res_list, "$.total");
//		Assert.assertEquals(total, 0,"编辑讲师实际返回结果："+res01+"然后删除讲师实际返回结果："+res);
//		Assert.assertEquals(msg, "true","编辑讲师实际返回结果："+res01+"然后删除讲师实际返回结果："+res);
	}
	
	String out_id = "";
	String rela_id = "";
	
	@Test(description = "新增课程",priority = 10)
	public void addCourse() {
		String res = CourseBusiness.addCourse(course_name_02, "1", "this is desription", "", 
				ArticleBusiness.getIdByKeyword(""), "1", "3", "", "1", "0", "release");
		rela_id = (String)JSONPath.read(res, "$.data[0]");
	}
	
	@Test(description = "新增讲师",priority = 11)
	public void addLecturer() {
		LecturerListBusiness.addLecturer(outer_name01,"1", "13526231231","outside" ,"", BaseBusiness.lecturerLevel, "auto", "Jmeter");
	}
	
	@Test(description = "获取讲师和关联课程的id",priority = 12)
	public void getId() {
		out_id = LecturerListBusiness.getIdByKeyword(outer_name01) ;
	}
	String course_name_03 = "LMJHFGA"+CommonData.getStringRandom(5);
	String course_id_03 = "";
	@Test(description = "新增带有讲师的课程",priority = 13)
	public void addCourse1() {
		String res = CourseBusiness.addCourse(course_name_03, "1", "this is desription", out_id, 
				ArticleBusiness.getIdByKeyword(""), "1", "3", "", "1", "0", "release");
		course_id_03 = (String)JSONPath.read(res, "$.data[0]");
	}
	
	@Test(description="新增的讲师关联到课程",priority=14)
	public void testRelateCourses() {
		String res = LecturerListBusiness.relateCourses( rela_id,out_id);
		String msg = (String)JSONPath.read(res, "$.update");
//		Assert.assertEquals(msg, "true","新增的讲师关联到课程，实际返回结果："+res);
	}
	@Test(description= "查看讲师授课详情的列表",priority=15)
	public void testlecturersTeachCoursesList() {

//		String res = LecturerListBusiness.lecturersTeachCoursesList(out_id, "");
//		String title = (String)JSONPath.read(res, "$.list[0].title");
//		String course_classify_name = (String)JSONPath.read(res, "$.list[0].course_classify_name");
//		int like_count = (int)JSONPath.read(res, "$.list[0].like_count");
//		int play_count = (int)JSONPath.read(res, "$.list[0].play_count");
//		int comment_count = (int)JSONPath.read(res, "$.list[0].comment_count");
//		Long update_time = (Long)JSONPath.read(res, "$.list[0].update_time");

//		String res = LecturerListBusiness.lecturersTeachCoursesList(out_id, "");
//		String title = (String)JSONPath.read(res, "$.list[0].title");
//		String course_classify_name = (String)JSONPath.read(res, "$.list[0].course_classify_name");
//		int like_count = (int)JSONPath.read(res, "$.list[0].like_count");
//		int play_count = (int)JSONPath.read(res, "$.list[0].play_count");
//		int comment_count = (int)JSONPath.read(res, "$.list[0].comment_count");
//		Long update_time = (Long)JSONPath.read(res, "$.list[0].update_time");

//		Assert.assertNotNull(title,""+res);
//		Assert.assertNotNull(like_count,""+res);
//		Assert.assertNotNull(play_count, ""+res);
//		Assert.assertNotNull(comment_count, ""+res);
//		Assert.assertNotNull(course_classify_name, ""+res);
//		Assert.assertNotNull(update_time, ""+res);
//		Assert.assertTrue(res.contains("course_duration"),"查看讲师授课详情的列表，时长字段"+res);

	}
	
	@Test(description="讲师详情的授课记录",priority=16)
	public void testqueryInfo() {
		String res = LecturersGracefulBearingBusiness.queryInfo(out_id);
		Assert.assertTrue(res.contains("level_name"), ""+res);
	}
	
	@Test(description = "删除关联的课程",priority = 17)
	public void deleteCourse () {
		CourseBusiness.deleteCourse(rela_id);
	}
	
	@Test(description = "删除带有讲师的课程",priority = 18)
	public void deleteCourse1() {
		CourseBusiness.deleteCourse(course_id_03);
	}
	
	@Test(description = "删除讲师",priority = 19)
	public void deleteLecturerByKeyword() {
		LecturerListBusiness.deleteLecturerByKeyword(outer_name01);
		
	}
}
