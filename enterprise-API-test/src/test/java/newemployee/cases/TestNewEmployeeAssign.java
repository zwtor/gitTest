package newemployee.cases;

import cn.kxy.course.resources.bussiness.ArticleBusiness;
import cn.kxy.setting.bussiness.AuthUserBusiness;
import cn.kxy.setting.bussiness.UserBusiness;
import cn.kxy.study.business.MyStudyTask;
import cn.kxy.study.business.NewEmployeeTrainBusiness;
import init.cases.InitStudyAuthCourse;
import com.alibaba.fastjson.JSONPath;
import com.lazy.common.utils.CommonData;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestNewEmployeeAssign extends InitStudyAuthCourse{
	String name_01 = "NewEmployeeAssign"+CommonData.getStringRandom(5);
	String id_01 = "";
	String planId_01 = "";
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
	String user_id = UserBusiness.getUserId();
	String dep_id = "";
	String post_id = "";
	String group_id = "";
	@Test(description = "获取部门id",priority = 3)
	public void testGetDepartmentList(){
		String res = AuthUserBusiness.GetDepartmentList();
		dep_id = (String)JSONPath.read(res, "$.children[0].id");
	}
	
	@Test(description = "获取用户组id",priority = 4)
	public void testGetGroupId() {
		String res = AuthUserBusiness.queryUserGroupList();
		group_id = (String)JSONPath.read(res, "$.groups[0].id");
	}
	
	@Test(description = "获取岗位id",priority = 5)
	public void testGetPostId() {
		String res = AuthUserBusiness.queryUsePostList();
		post_id = (String)JSONPath.read(res, "$.posts[0].id");
	}
	
	@Test(description="启用新员工培训",priority = 6)
	public void testBeginTask() {
		if (status==2) {
			String res = NewEmployeeTrainBusiness.beginAndStopTask(id_01, "1");
			String msg = (String)JSONPath.read(res, "$.msg");
			Assert.assertEquals(msg, "启用成功","启用新员工培训,实际返回结果："+res);
		}
	}
	@Test(description = "按照人指派新员工培训",priority = 7)
	public void testAssignNewTaskByUserId() {
		String res = NewEmployeeTrainBusiness.assignNewTask(id_01, "", user_id, "", "");
		String result = (String)JSONPath.read(res, "$.result");
//		Assert.assertEquals(result,"指派成功", "按照人指派新员工培训："+res);
	}
	
	@Test(description = "按照用户组指派新员工培训",priority = 8)
	public void testAssignNewTaskByGroupId() {
		String res = NewEmployeeTrainBusiness.assignNewTask(id_01, group_id, "", "", "");
		String result = (String)JSONPath.read(res, "$.result");
//		Assert.assertEquals(result,"指派成功", "按照用户组指派新员工培训："+res);
	}
	@Test(description = "按照部门指派新员工培训",priority = 9)
	public void testAssignNewTaskByDepartmentId() {
		String res = NewEmployeeTrainBusiness.assignNewTask(id_01, "", "", dep_id, "");
		String result = (String)JSONPath.read(res, "$.result");
//		Assert.assertEquals(result,"指派成功", "按照部门指派新员工培训："+res);
	}
	@Test(description = "按照岗位指派新员工培训",priority = 10)
	public void testAssignNewTaskByPostId() {
		String res = NewEmployeeTrainBusiness.assignNewTask(id_01, "", "", "", post_id);
		String result = (String)JSONPath.read(res, "$.result");
//		Assert.assertEquals(result,"指派成功", "按照岗位指派新员工培训："+res);
	}
	
	@Test(description = "新员工任务指派成功后，在我的学习任务列表查看",priority = 11)
	public void testQueryList() {
		String res = MyStudyTask.queryList(name_01, "0");
		int taskType = (int)JSONPath.read(res, "$.list[0].taskType");
		Assert.assertEquals(taskType, 4,"查看我的培训任务列表--任务类型为新员工培训进行校验，实际返回结果："+res);
	}
	
	@Test(description = "删除新员工任务",priority = 12)
	public void testDeleteStageEmployTrainTask() {
		String res = NewEmployeeTrainBusiness.deleteEmployTrainTask(planId_01);
		String msg = (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "删除成功","删除新员工培训,实际返回结果："+res);
	}
}
