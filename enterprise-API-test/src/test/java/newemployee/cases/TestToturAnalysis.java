package newemployee.cases;

import cn.kxy.course.resources.bussiness.ArticleBusiness;
import cn.kxy.setting.bussiness.UserBusiness;
import cn.kxy.study.business.NewEmployeeTrainBusiness;
import init.cases.InitStudyAuthCourse;
import com.alibaba.fastjson.JSONPath;
import com.lazy.common.utils.CommonData;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestToturAnalysis extends InitStudyAuthCourse{
	String name_01 = "NewEmployeeAssign"+CommonData.getStringRandom(5);
	String id_01 = "";
	String planId_01 = "";
	String user_id = UserBusiness.getUserId();
	@Test(description = "新增新员工任务，供指派时使用",priority = 1)
	public void testAddNewEmployeeTrick() {
		String art_id = ArticleBusiness.getIdByKeyword(articlename);
		String res = NewEmployeeTrainBusiness.addNewEmployeeTrick(name_01, art_id, "false");
		String msg = (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "新建成功","在部门下新建新员工培训"+res);
	}
	int status ;
	@Test(description = "获取id",priority = 2)
	public void testGetId() {
		String res = NewEmployeeTrainBusiness.queryList(name_01, "false", "0", "0","","");
		id_01 = (String)JSONPath.read(res, "$.list[0].id");
		planId_01 = (String)JSONPath.read(res, "$.list[0].planId");
		status = (int)JSONPath.read(res,"$.list[0].status");
	}
	
	@Test(description = "辅导分析",priority = 3)
	public void tutorialsAnalysis() {
		String res = NewEmployeeTrainBusiness.tutorialsAnalysis("", planId_01);
		Assert.assertTrue(res.contains("succ"),"辅导分析:"+res);
	}
	
	@Test(description = "删除新员工任务",priority = 4)
	public void testDeleteStageEmployTrainTask() {
		String res = NewEmployeeTrainBusiness.deleteEmployTrainTask(planId_01);
		String msg = (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "删除成功","删除新员工培训,实际返回结果："+res);
	}
	
	@Test(description = "",priority = 5)
	public void testANewEmployeeTrick() {
//		String art_id = ArticleBusiness.getIdByKeyword(articlename);
//		String res = NewEmployeeTrainBusiness.addNewEmployeeTrick(name_01, art_id, "false");
//		String msg = (String)JSONPath.read(res, "$.msg");
//		Assert.assertEquals(msg, "新建成功","在部门下新建新员工培训"+res);
	}
	@Test(description = "",priority = 6)
	public void testGed() {
//		String res = NewEmployeeTrainBusiness.queryList(name_01, "false", "0", "0","","");
//		id_01 = (String)JSONPath.read(res, "$.list[0].id");
//		planId_01 = (String)JSONPath.read(res, "$.list[0].planId");
//		status = (int)JSONPath.read(res,"$.list[0].status");
	}
	
	@Test(description = "",priority = 7)
	public void tutorialsAnals() {
//		String res = NewEmployeeTrainBusiness.tutorialsAnalysis("", planId_01);
//		Assert.assertTrue(res.contains("succ"),"辅导分析:"+res);
	}
	
	@Test(description = "",priority = 8)
	public void testDeleteStageEmployTraiask() {
//		String res = NewEmployeeTrainBusiness.deleteEmployTrainTask(planId_01);
//		String msg = (String)JSONPath.read(res, "$.msg");
//		Assert.assertEquals(msg, "删除成功","删除新员工培训,实际返回结果："+res);
	}
}
