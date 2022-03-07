package lecture.cases;

import cn.kxy.lecturer.business.LecturerLevelBusiness;
import com.alibaba.fastjson.JSONPath;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestExtremelyLecturerLevel {

	@Test(description="name为null时，新增讲师等级")
	public void test () {
		String res=LecturerLevelBusiness.addLecturerLevel("", "");
		String msg = (String)JSONPath.read(res, "$.data[0].message");
		Assert.assertEquals(msg, "name not empty","name为null时，新增讲师等级实际返回结果："+res);
	}
	@Test(description="查询讲师列表列表，keyword为null时")
	public void testqueryLecturerLevelListKeywordIsNull() {
		String res = LecturerLevelBusiness.queryLecturerLevelList("");
		int total = (int)JSONPath.read(res, "$.total");
		Assert.assertTrue(total>=0,"查询讲师列表列表，keyword为null时，实际返回结果："+res);
	}
}
