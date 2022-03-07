package setting.cases;

import cn.kxy.course.resources.bussiness.AppCourseBusiness;
import cn.kxy.course.resources.bussiness.CourseFrontListInfoBusiness;
import cn.kxy.setting.bussiness.ClassificationBusines;
import init.cases.InitStudyAuthCourse;
import com.lazy.common.utils.CommonData;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestClassificationSetting extends InitStudyAuthCourse {
	public String name ="FluidMmechanics" + CommonData.getStringRandom(3);
	String id = "";
	@Test(description = "添加一级分类",priority = 1)
	public void TestAddPrimaryClassification() { 
		String res = ClassificationBusines.addPrimaryClassification(name);
		String resname = ClassificationBusines.getPrimaryName();
		id= ClassificationBusines.getPrimaryId();
		Assert.assertEquals(name, resname,"添加一级分类返回的结果为："+res);
	}
	
	@Test(description = "新增分类，未被其他模块引用时，新增模块时应显示分类",priority=2)
	public void testQueryNullClassification() {
		String res = ClassificationBusines.queryClassification("","");
		Assert.assertTrue(res.contains(name),"新增分类，未被其他模块引用时，新增模块时应显示分类"+res);
	}
	@Test(description="新增分类，未被其他模块引用时，查看课程列表，不应显示",priority=3)
	public void testQueryClassifyCount() {
		String res = CourseFrontListInfoBusiness.queryClassifyCount();
	}
	
	@Test(description="新增分类，未被其他模块引用时，查看课程管理列表，不应显示",priority=4)
	public void testqueryClassification() {
		ClassificationBusines.queryClassification("course_manage","true");
	}
	@Test(description="新增分类，未被其他模块引用时，查看App移动端课程列表，不应显示",priority=5)
	public void testQueryClassificationList() {
		AppCourseBusiness.queryClassificationList();
	}
	@Test(description="新增分类，未被其他模块引用时，查看问卷管理列表，不应显示",priority=6)
	public void testQueryQuestionnaireManageClassification() {
		ClassificationBusines.queryClassification("questionnaire_manage","");
	}
	
	@Test(description="新增分类，未被其他模块引用时，查看题库管理列表，不应显示",priority=7)
	public void testQueryquestionManageClassification() {
		ClassificationBusines.queryClassification("question_manage","");
	}
	@Test(description="新增分类，未被其他模块引用时，查看试卷管理列表，不应显示",priority=8)
	public void testQuerypaperManageClassification() {
		ClassificationBusines.queryClassification("paper_manage","");
	}
	String cour_name = "Selenium"+CommonData.getStringRandom(5);
	String cour_id = "";
	@Test(description="新增引用分类课程",priority=9)
	public void testAddCourseBy() {
//		CourseBusiness.addCourseBy(cour_name, "1", "this is desription", LecturerListBusiness.getIdByKeyword(outer_name), 
//				id,ArticleBusiness.getIdByAudio("" ), "0", "2", UserBusiness.getUserId(), "0", "3", "release","");
//		String res = CourseBusiness.courseManageList(cour_name, "all", "release","","","all");
//		cour_id= (String)JSONPath.read(res, "$.list[0].course_id");
		
	}
	@Test(description="新增分类，被课程引用，查看课程列表，应显示",priority=10)
	public void testQueryClassify() {
		String res = CourseFrontListInfoBusiness.queryClassifyCount();
		Assert.assertTrue(res.contains("name"),"新增分类，被课程引用，查看课程列表，应显示"+res);
	}
	
	@Test(description="新增分类被课程引用，查看课程管理列表，应显示",priority=11)
	public void testQuerycourseManageClassif() {
		
		String res = ClassificationBusines.queryClassification("course_manage","true");
		Assert.assertTrue(res.contains("name"),"新增分类被课程引用，查看课程管理列表，应显示"+res);
	}
	@Test(description = "删除课程",priority=12)
	public void testDeleteCourse() {
//		String res = CourseBusiness.deleteCourse(cour_id);
//		System.out.println(res);
	}
	
	@Test(description="新增分类，被课程引用时，删除课程，查看课程列表，不应显示",priority=13)
	public void testDeleteQueryClassifyCount() {
		CourseFrontListInfoBusiness.queryClassifyCount();
	}
	
	@Test(description="新增分类，被课程引用时，删除课程，查看课程管理列表，不应显示",priority=14)
	public void testDeleteQueryClassification() {
		ClassificationBusines.queryClassification("course_manage","true");
	}
	
	@Test(description="删除分类",priority=15)
	public void testDeleteClassification() {
		ClassificationBusines.deleteClassification(id);
	}

}
