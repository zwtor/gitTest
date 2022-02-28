package newemployee.cases;

import cn.kxy.course.resources.bussiness.ArticleBusiness;
import cn.kxy.setting.bussiness.AuthUserBusiness;
import cn.kxy.study.business.NewEmployeeTrainBusiness;
import init.cases.InitStudyAuthCourse;
import com.alibaba.fastjson.JSONPath;
import com.lazy.common.utils.CommonData;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestNewEmployeeTrick extends InitStudyAuthCourse{

	String name_01 = "NewEmployeeTrick"+CommonData.getStringRandom(4);
	String name_02 = "NewEmployeeTrick2"+CommonData.getStringRandom(4);
	String id_01 = "";
	String planId_01 = "";
	String id_02 = "";
	String planId_02 = "";
	@Test(description = "新增阶段间闯关的新员工任务",priority = 1)
	public void testAddNewEmployeeTrick() {
		String art_id = ArticleBusiness.getIdByKeyword(articlename);
		String res = NewEmployeeTrainBusiness.addNewEmployeeTrick(name_01, art_id, "false");
		String msg = (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "新建成功","在部门下新建新员工培训"+res);
	}
	@Test(description = "查看新员工任务详情,阶段stagePass字段校验",priority = 2)
	public void testQueryInfo() {
		String res = NewEmployeeTrainBusiness.queryList(name_01, "false", "0", "0","","");
		id_01 = (String)JSONPath.read(res, "$.list[0].id");
		planId_01 = (String)JSONPath.read(res, "$.list[0].planId");
		String info_res = NewEmployeeTrainBusiness.queryInfo(id_01);
		Boolean stagePass = (Boolean)JSONPath.read(info_res, "$.stagePass");
		Assert.assertFalse( stagePass,"查看新员工任务详情,阶段stagePass字段校验，实际返回结果："+res);
		
	}
	@Test(description = "删除新员工任务",priority = 3)
	public void testDeleteEmployTrainTask() {
		String res = NewEmployeeTrainBusiness.deleteEmployTrainTask(planId_01);
		String msg = (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "删除成功","删除新员工培训,实际返回结果："+res);
	}
	@Test(description = "新增阶段间闯关的新员工任务",priority = 4)
	public void testAddNewEmployeeTrickISTrue() {
		String art_id = ArticleBusiness.getIdByKeyword(articlename);
		String res = NewEmployeeTrainBusiness.addNewEmployeeTrick(name_01, art_id, "true");
		String msg = (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "新建成功","在部门下新建新员工培训"+res);
	}
	@Test(description = "查看新员工任务详情,阶段stagePass字段校验",priority = 5)
	public void testQueryStagessInfo() {
		String res = NewEmployeeTrainBusiness.queryList(name_01, "false", "0", "0","","");
		id_01 = (String)JSONPath.read(res, "$.list[0].id");
		planId_01 = (String)JSONPath.read(res, "$.list[0].planId");
		String info_res = NewEmployeeTrainBusiness.queryInfo(id_01);
		Boolean stagePass = (Boolean)JSONPath.read(info_res, "$.stagePass");
		Assert.assertTrue(stagePass, "查看新员工任务详情,阶段stagePass字段校验，实际返回结果："+res);
	}
	String dep_id = "";
	String post_id = "";
	@Test(description = "获取部门id",priority = 6)
	public void testGetDepartmentList(){
		String res = AuthUserBusiness.GetDepartmentList();
		dep_id = (String)JSONPath.read(res, "$.children[0].id");
	}
	@Test(description = "获取岗位id",priority = 7)
	public void testGetPostId() {
		String res = AuthUserBusiness.queryUsePostList();
		post_id = (String)JSONPath.read(res, "$.posts[0].id");
	}
	
	@Test(description = "根据部门查看新员工学习列表",priority =8)
	public void testlistByDep() {
		String res = NewEmployeeTrainBusiness.queryList("", "false", "0", "0",dep_id,"");
		Assert.assertTrue(res.contains("list"), "根据部门查看新员工学习列表，实际返回结果："+res);
	}
	
	@Test(description = "根据岗位查看新员工学习列表",priority =9)
	public void testListByPost() {
		String res = NewEmployeeTrainBusiness.queryList("", "false", "0", "0","",post_id);
		Assert.assertTrue(res.contains("list"), "根据岗位查看新员工学习列表，实际返回结果："+res);

	}
	@Test(description = "根据部门，岗位查看新员工学习列表",priority =10)
	public void testListPostDep() {
		String res = NewEmployeeTrainBusiness.queryList("", "false", "0", "0",dep_id,post_id);
		Assert.assertTrue(res.contains("list"), "根据部门，岗位查看新员工学习列表，实际返回结果："+res);
	}
	@Test(description = "查看新员工任务详情,阶段stagePass字段校验",priority = 11)
	public void queryInfo() {
		String res = NewEmployeeTrainBusiness.queryList(name_01, "false", "0", "0","","");
		id_01 = (String)JSONPath.read(res, "$.list[0].id");
		planId_01 = (String)JSONPath.read(res, "$.list[0].planId");
		String info_res = NewEmployeeTrainBusiness.queryInfo(id_01);
		Boolean stagePass = (Boolean)JSONPath.read(info_res, "$.stagePass");
		Assert.assertTrue(stagePass, "查看新员工任务详情,阶段stagePass字段校验，实际返回结果："+res);
	}
	
	@Test(description = "删除新员工任务",priority = 12)
	public void testDeleteStageEmployTrainTask() {
		String res = NewEmployeeTrainBusiness.deleteEmployTrainTask(planId_01);
		String msg = (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "删除成功","删除新员工培训,实际返回结果："+res);
	}
}
