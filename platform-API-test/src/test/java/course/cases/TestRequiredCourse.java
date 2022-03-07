package course.cases;

import init.cases.InitStudyAuthCourse;
import com.lazy.common.utils.CommonData;
import org.testng.annotations.Test;

public class TestRequiredCourse extends InitStudyAuthCourse{
	
	String notRequiredCourseName = "notRequiredCourse"+CommonData.getStringRandom(5);
	String cour_name = "requiredCourse"+CommonData.getStringRandom(5);
	String cour_id = "";
	@Test(description = "新增必修的课程" ,priority = 1)
	public void addCourseBy() {
//		String addCourse = CourseBusiness.addCourse(cour_name, ArticleBusiness.getIdByKeyword(""), "release","requiredCourse");
//		cour_id = (String)JSONPath.read(addCourse, "$.data[0]");
//		Assert.assertTrue(addCourse.contains("新增课程成功"),"新增必修的课程,实际返回结果："+addCourse);
	}
	
	@Test(description = "获取课程id" ,priority = 2)
	public void getId() {
//		String res = CourseBusiness.courseManageList(cour_name, "all", "release","","","all");
//		cour_id= (String)JSONPath.read(res, "$.list[0].course_id");
	}
	
	@Test(description = "查看必修课详情",priority =3)
	public void queryrequiredCourseInfo() {
//		String res = AppCourseBusiness.queryAppCourseInfo(cour_id);
//		Assert.assertTrue(res.contains(cour_name),"查看必修课详情,实际返回结果："+res);
	}
	
	@Test(description = "删除必修的课程",priority =4)
	public void delleteRequiredCourse () {
//		CourseBusiness.deleteCourse(cour_id);
	}
	
	@Test(description = "新增选修的课程" ,priority = 5)
	public void addCourseBynotRequiredCourse() {
//		 CourseBusiness.addCourse(cour_name, ArticleBusiness.getIdByKeyword(""), "release","notRequiredCourse");
	}
	
	@Test(description = "获取课程id" ,priority = 6)
	public void test() {
//		String res = CourseBusiness.courseManageList(cour_name, "all", "release","","","all");
//		cour_id= (String)JSONPath.read(res, "$.list[0].course_id");
	}
	
	@Test(description = "查看选修课详情",priority =7)
	public void querynotRequiredCourseInfo() {
//		String res = AppCourseBusiness.queryAppCourseInfo(cour_id);
//		Assert.assertTrue(res.contains(cour_name),"查看选修课详情,实际返回结果："+res);
	}
	
	@Test(description = "删除选修的课程",priority =8)
	public void delletenotRequiredCourse () {
//		String deleteCourse = CourseBusiness.deleteCourse(cour_id);
//		System.out.println(deleteCourse);
	}
}
