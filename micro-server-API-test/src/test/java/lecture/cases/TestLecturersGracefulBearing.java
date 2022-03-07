package lecture.cases;

import cn.kxy.base.business.BaseBusiness;
import cn.kxy.lecturer.business.LecturerLevelBusiness;
import cn.kxy.lecturer.business.LecturerListBusiness;
import cn.kxy.lecturer.business.LecturersGracefulBearingBusiness;
import cn.kxy.setting.bussiness.UserBusiness;
import cn.lazy.init.cases.InitStudyAuthCourse;
import com.alibaba.fastjson.JSONPath;
import com.lazy.common.utils.CommonData;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestLecturersGracefulBearing extends InitStudyAuthCourse{

	String outer_name = "Allenlor"+CommonData.getStringRandom(5);
	@Test (description="查询全部讲师风采列表",priority=1)
	public void testAllLecturersGracefulBearingList() {
		LecturerListBusiness.addLecturer(outer_name,"1", "13526231231","outside" ,"", BaseBusiness.lecturerLevel, "auto", "jmeter");
		String res= LecturersGracefulBearingBusiness.queryList("", "", "");
		int total = (int)JSONPath.read(res, "$.total");
		int status = (int)JSONPath.read(res, "$.list[0].status");
		Assert.assertEquals(status, 1,"查询全部讲师风采列表实际返回结果："+res);
		Assert.assertTrue(total>1,"查询全部讲师风采列表实际返回结果："+res);
	}
	@Test(description="根据关键字，等级进行查询,查看外部讲师",priority=2)
	public void testLecturersGracefulBearingListByName() {
		String res= LecturersGracefulBearingBusiness.queryList(outer_name, LecturerLevelBusiness.getIdByKeyword(BaseBusiness.lecturerLevel),  BaseBusiness.lecturerLevel);
		int sex= (int)JSONPath.read(res, "$.list[0].sex");
		String level_name =(String)JSONPath.read(res, "$.list[0].level_name");
		int status= (int)JSONPath.read(res, "$.list[0].status");
		Long create_time =(Long)JSONPath.read(res, "$.list[0].create_time");
		String lecturer_name =(String)JSONPath.read(res, "$.list[0].lecturer_name");
		String type =(String)JSONPath.read(res, "$.list[0].type");
		LecturerListBusiness.deleteLecturerByKeyword(outer_name);
		Assert.assertEquals(type, "outside","根据关键字，等级进行查询实际返回结果："+res);
		Assert.assertEquals(lecturer_name, outer_name,"根据关键字，等级进行查询实际返回结果："+res);
		Assert.assertTrue(level_name!=null, "根据关键字，等级进行查询实际返回结果："+res);
		Assert.assertTrue(create_time!=null, "根据关键字，等级进行查询实际返回结果："+res);
		Assert.assertEquals(sex, 1,"根据关键字，等级进行查询实际返回结果："+res);
		Assert.assertEquals(status, 1,"根据关键字，等级进行查询实际返回结果："+res);
	}
	
	@Test(description="讲师停用后，不在讲师风采列表页显示",priority=3)
	public void testStopDisAppeare() {
//		String outername = "Allenlor"+CommonData.getStringRandom(1);
//		LecturerListBusiness.addLecturer(outername,"1", "13526231231","outside" ,"", BaseBusiness.lecturerLevel, "auto", "jmeter");
//		LecturerListBusiness.updateStatus("0", LecturerListBusiness.getIdByKeyword(outername));
//		String res= LecturersGracefulBearingBusiness.queryList(outername, "",  "");
//		int total = (int)JSONPath.read(res, "$.total");
//		LecturerListBusiness.deleteLecturerByKeyword(outername);
//		Assert.assertEquals(total, 0,"讲师停用后，不在讲师风采列表页显示"+res);
	}
	
	@Test(description = "新增内部讲师",priority = 4)
	public void testAddInside() {
		LecturerListBusiness.addLecturer(UserBusiness.getUsername(),"0", "13526231231","inside" ,UserBusiness.getUserId(), BaseBusiness.lecturerLevel, "uiauto", "selenium");

	}
	
	@Test(description="在讲师风采列表页，查看内部讲师",priority=5)
	public void testQueryInside() {
//		String res= LecturersGracefulBearingBusiness.queryList(UserBusiness.getUsername(), LecturerLevelBusiness.getIdByKeyword(BaseBusiness.lecturerLevel),  BaseBusiness.lecturerLevel);
//		int sex= (int)JSONPath.read(res, "$.list[0].sex");
//		String level_name =(String)JSONPath.read(res, "$.list[0].level_name");
//		int status= (int)JSONPath.read(res, "$.list[0].status");
//		Long create_time =(Long)JSONPath.read(res, "$.list[0].create_time");
//		String create_user_name =(String)JSONPath.read(res, "$.list[0].create_user_name");
//		String lecturer_name =(String)JSONPath.read(res, "$.list[0].lecturer_name");
//		String type =(String)JSONPath.read(res, "$.list[0].type");
//		Assert.assertEquals(type, "inside","根据关键字，等级进行查询实际返回结果："+res);
//		Assert.assertEquals(create_user_name, UserBusiness.getUsername(),"根据关键字，等级进行查询实际返回结果："+res);
//		Assert.assertEquals(lecturer_name, UserBusiness.getUsername(),"根据关键字，等级进行查询实际返回结果："+res);
//		Assert.assertTrue(level_name!=null, "根据关键字，等级进行查询实际返回结果："+res);
//		Assert.assertTrue(create_time!=null, "根据关键字，等级进行查询实际返回结果："+res);
//		Assert.assertEquals(sex, 0,"根据关键字，等级进行查询实际返回结果："+res);
//		Assert.assertEquals(status, 1,"根据关键字，等级进行查询实际返回结果："+res);
	}
	

	@Test(description = "删除内部讲师",priority = 6)
	public void testDeleteLecturerByKeyword() {
//		LecturerListBusiness.deleteLecturerByKeyword(UserBusiness.getUsername());
	}
	
	String out_name = "Smith"+CommonData.getStringRandom(5);;
	@Test(description = "",priority = 7)
	public void testAddOutsideTeacher() {
		LecturerListBusiness.addLecturer(out_name,"1", "13526231231","outside" ,"", BaseBusiness.lecturerLevel, "auto", "jmeter");
	}
	
	@Test(description="查看讲师风采的讲师详情",priority=8)
	public void testQueryLecturerInfo() {
		
		String res = LecturersGracefulBearingBusiness.queryInfo(LecturersGracefulBearingBusiness.getIdByKeyword(out_name));
		int sex= (int)JSONPath.read(res, "$.lecturer.sex");
		String level_name =(String)JSONPath.read(res, "$.lecturer.level_name");
		int status= (int)JSONPath.read(res, "$.lecturer.status");
		Long create_time =(Long)JSONPath.read(res, "$.lecturer.create_time");
		String lecturer_name =(String)JSONPath.read(res, "$.lecturer.lecturer_name");
		
		String type =(String)JSONPath.read(res, "$.lecturer.type");
//		String adept_field =(String)JSONPath.read(res, "$.lecturer.adept_field");
		String lecturer_describe =(String)JSONPath.read(res, "$.lecturer.lecturer_describe");
		int total= (int)JSONPath.read(res, "$.courses.total");
		Assert.assertEquals(total, 0,"查看讲师风采的讲师详情(新讲师无课程关联)实际返回结果："+res);
//		Assert.assertEquals(adept_field, "auto","查看讲师风采的讲师详情实际返回结果："+res);
		Assert.assertEquals(lecturer_describe, "jmeter","查看讲师风采的讲师详情实际返回结果："+res);
		Assert.assertEquals(type, "outside","查看讲师风采的讲师详情实际返回结果："+res);
		Assert.assertEquals(lecturer_name, out_name,"查看讲师风采的讲师详情实际返回结果："+res);
		Assert.assertTrue(level_name!=null, "查看讲师风采的讲师详情实际返回结果："+res);
		Assert.assertTrue(create_time!=null, "查看讲师风采的讲师详情实际返回结果："+res);
		Assert.assertEquals(sex, 1,"查看讲师风采的讲师详情实际返回结果："+res);
		Assert.assertEquals(status, 1,"查看讲师风采的讲师详情实际返回结果："+res);
	}
	@Test(description = "删除外部讲师",priority = 9)
	public void test() {
		LecturerListBusiness.deleteLecturerByKeyword(out_name);
	}
	
}
