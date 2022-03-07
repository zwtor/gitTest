package lecture.cases;

import cn.kxy.lecturer.business.LecturerLevelBusiness;
import cn.lazy.init.cases.InitStudyAuthCourse;
import com.alibaba.fastjson.JSONPath;
import com.lazy.common.utils.CommonData;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestLecturerLevel extends InitStudyAuthCourse{

	String name = "LecturerLevel"+CommonData.getStringRandom(3);
	String edit_name = "LecturerLevel"+CommonData.getStringRandom(3);
	String standard="Lecture length must be greater than 100 hours";
	
	@Test(description="新增讲师等级",priority=1)
	public void testAddLecturerLevel() {
		String res = LecturerLevelBusiness.addLecturerLevel(name,standard);
		String id = (String)JSONPath.read(res, "$.id");
		System.out.println("this is a Lecturer module");
		Assert.assertTrue(!id.isEmpty(),"新增讲师等级实际返回结果："+res);
	}
	
	@Test(description="查看讲师等级列表页",priority=2)
	public void testQueryLecturerLevelList() {
		String res = LecturerLevelBusiness.queryLecturerLevelList(name);
		
		String title = (String)JSONPath.read(res, "$.list[0].name");
		String standard01 = (String)JSONPath.read(res, "$.list[0].standard");
		Assert.assertEquals(title, name,"查看讲师等级列表页实际返回结果："+res);
		Assert.assertEquals(standard01, standard,"查看讲师等级列表页实际返回结果："+res);
	}
	
	@Test(description="查看讲师等级详情",priority=3)
	public void testQueryInfo() {
		String res = LecturerLevelBusiness.queryInfo(LecturerLevelBusiness.getIdByKeyword(name));
		String title = (String)JSONPath.read(res, "$.name");
		String standard01 = (String)JSONPath.read(res, "$.standard");
		Assert.assertEquals(title, name,"查看讲师等级列表页实际返回结果："+res);
		Assert.assertEquals(standard01, standard,"查看讲师等级列表页实际返回结果："+res);
	}
	
	@Test(description="编辑讲师等级",priority=4)
	public void testEditLecturerLevel() {
		String res = LecturerLevelBusiness.editLecturerLevel(name, edit_name, standard);
		String name_edit = (String)JSONPath.read(res, "$.name");
		Assert.assertEquals(name_edit, edit_name,"编辑讲师等级"+res);
	}
	@Test(description="对讲师等级进行排序",priority=5)
	public void testSortLecturerLevel() {
		String res = LecturerLevelBusiness.update_sortLecturerLevel(edit_name);
		String update = (String)JSONPath.read(res, "$.update");
		Assert.assertEquals(update, "true","编辑讲师等级"+res);
	}
	
	@Test(description="删除讲师等级",priority=6)
	public void testDeleteLecturerLevel() {
		String res = LecturerLevelBusiness.deleteLecturerLevel(edit_name);
		String deleted = (String)JSONPath.read(res, "$.deleted");
		String res01 = LecturerLevelBusiness.queryLecturerLevelList(edit_name);
		int total = (int)JSONPath.read(res01, "$.total");
		Assert.assertEquals(total, 0,"删除讲师等级实际返回结果："+res);
		Assert.assertEquals(deleted, "true","删除讲师等级实际返回结果："+res);
	}
	
}
