package lecture.cases;

import cn.kxy.base.business.BaseBusiness;
import cn.kxy.lecturer.business.AppLecturerBusiness;
import cn.kxy.lecturer.business.LecturerListBusiness;
import init.cases.InitStudyAuthCourse;
import com.alibaba.fastjson.JSONPath;
import com.lazy.common.utils.CommonData;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestAppLecturer extends InitStudyAuthCourse{
	String outer_name = "Smith"+CommonData.getStringRandom(1);
	@Test(description ="查看App端讲师等级列表",priority=1)
	public void testQueryApplecturerLevels() {
		String res = AppLecturerBusiness.queryApplecturerLevels();
		String name = (String)JSONPath.read(res, "$.list[0].name");
		Assert.assertTrue(!name.isEmpty(), "查看讲师等级列表，实际返回结果："+res);
	}
	
	@Test(description = "新增外部讲师",priority = 2)
	public void testAdd() {
		LecturerListBusiness.addLecturer(outer_name,"1", "13526231231","outside" ,"", BaseBusiness.lecturerLevel, "auto", "Jenkins");
	}
	
	@Test(description="查看app端讲师列表",priority=3) 
	public void testQueryAppLecturersList() {


//		String res = AppLecturerBusiness.queryAppLecturersList(outer_name, LecturerListBusiness.getlevelByKeyword(outer_name));
////		String adept_field = (String)JSONPath.read(res, "$.list[0].adept_field");
//		String lecturer_name = (String)JSONPath.read(res, "$.list[0].lecturer_name");
//		int sex = (int)JSONPath.read(res, "$.list[0].sex");
//		String level_name = (String)JSONPath.read(res, "$.list[0].level_name");
////		Assert.assertEquals(adept_field, "auto",""+res);
//		Assert.assertEquals(lecturer_name, outer_name,""+res);
//		Assert.assertEquals(sex, 1,""+res);
//		Assert.assertEquals(level_name, BaseBusiness.lecturerLevel,""+res);

		String res = AppLecturerBusiness.queryAppLecturersList(outer_name, LecturerListBusiness.getlevelByKeyword(outer_name));
//		String adept_field = (String)JSONPath.read(res, "$.list[0].adept_field");
		String lecturer_name = (String)JSONPath.read(res, "$.list[0].lecturer_name");
		int sex = (int)JSONPath.read(res, "$.list[0].sex");
		String level_name = (String)JSONPath.read(res, "$.list[0].level_name");
//		Assert.assertEquals(adept_field, "auto",""+res);
		Assert.assertEquals(lecturer_name, outer_name,""+res);
		Assert.assertEquals(sex, 1,""+res);
		Assert.assertEquals(level_name, BaseBusiness.outlecturerLevel,""+res);

		
	}
	@Test(description="查询app端讲师详情",priority=4)
	public void testQueryAppLecturersInfo() {
		

//		String id = AppLecturerBusiness.getIdByKeyword(outer_name);
//		String res = AppLecturerBusiness.queryAppLecturersInfo(id);
////		String adept_field = (String)JSONPath.read(res, "$.lecturer.adept_field");
//		String lecturer_name = (String)JSONPath.read(res, "$.lecturer.lecturer_name");
//		int sex = (int)JSONPath.read(res, "$.lecturer.sex");
//		String level_name = (String)JSONPath.read(res, "$.lecturer.level_name");
////		Assert.assertEquals(adept_field, "auto",""+res);
//		Assert.assertEquals(lecturer_name, outer_name,""+res);
//		Assert.assertEquals(sex, 1,""+res);
//		Assert.assertEquals(level_name, BaseBusiness.lecturerLevel,""+res);

		String id = AppLecturerBusiness.getIdByKeyword(outer_name);
		String res = AppLecturerBusiness.queryAppLecturersInfo(id);
//		String adept_field = (String)JSONPath.read(res, "$.lecturer.adept_field");
		String lecturer_name = (String)JSONPath.read(res, "$.lecturer.lecturer_name");
		int sex = (int)JSONPath.read(res, "$.lecturer.sex");
		String level_name = (String)JSONPath.read(res, "$.lecturer.level_name");
//		Assert.assertEquals(adept_field, "auto",""+res);
		Assert.assertEquals(lecturer_name, outer_name,""+res);
		Assert.assertEquals(sex, 1,""+res);
		Assert.assertEquals(level_name, BaseBusiness.outlecturerLevel,""+res);

	}
	@Test(priority = 5)
	public void end() {
		LecturerListBusiness.deleteLecturerByKeyword(outer_name);
	}
}
