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

public class TestNewEmployeeData extends InitStudyAuthCourse{
	String name_01 = "NewEmployeeData"+CommonData.getStringRandom(4);
	@Test(description = "新增阶段间闯关的新员工任务",priority = 1)
	public void testAddNewEmployeeTrickISTrue() {
		String art_id = ArticleBusiness.getIdByKeyword(articlename);
		String res = NewEmployeeTrainBusiness.addNewEmployeeTrick(name_01, art_id, "true");
		String msg = (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "新建成功","在部门下新建新员工培训"+res);
	}
	String dep_id = "";
	String post_id = "";
	@Test(description = "获取部门id",priority = 2)
	public void testGetDepartmentList(){
		String res = AuthUserBusiness.GetDepartmentList();
		dep_id = (String)JSONPath.read(res, "$.children[0].id");
	}
	@Test(description = "获取岗位id",priority = 3)
	public void testGetPostId() {
		String res = AuthUserBusiness.queryUsePostList();
		post_id = (String)JSONPath.read(res, "$.posts[0].id");
	}
	
	String user_id = UserBusiness.getUserId();
	String  id ;
	int status ;
	String planId ;
	@Test(description = "获取id",priority = 4)
	public void testGetId() {
		String res = NewEmployeeTrainBusiness.queryList(name_01, "false", "0", "0","","");
		id = (String)JSONPath.read(res, "$.list[0].id");
		status = (int)JSONPath.read(res,"$.list[0].status");
		planId = (String)JSONPath.read(res, "$.list[0].planId");
	}
	
	@Test(description="启用新员工培训",priority = 5)
	public void testBeginTask() {
		if (status==2) {
			String res = NewEmployeeTrainBusiness.beginAndStopTask(id, "1");
			String msg = (String)JSONPath.read(res, "$.msg");
			Assert.assertEquals(msg, "启用成功","启用新员工培训,实际返回结果："+res);
		}
	}
	@Test(description = "按照人指派新员工培训",priority = 6)
	public void testAssignNewTaskByUserId() {
		String res = NewEmployeeTrainBusiness.assignNewTask(id, "", user_id, "", "");
		String result = (String)JSONPath.read(res, "$.result");
		Assert.assertEquals(result,"指派成功", "按照人指派新员工培训："+res);
	}
	@Test(description = "查看全部的人员监控",priority = 7)
	public void testqueryMonitorsAll() {
		String res = NewEmployeeTrainBusiness.queryMonitors(planId, "0","","","","");
		String user_name = (String)JSONPath.read(res, "$.data.voList.list[0].userName");
		Assert.assertTrue(!user_name.isEmpty(),"查看全部的人员监控"+res);
	}
	@Test(description = "查看进行中的人员监控",priority = 8)
	public void testqueryMonitorsOnGoing() {
		String res = NewEmployeeTrainBusiness.queryMonitors(planId, "1","aa","","","");
		Assert.assertTrue(res.contains("voList"),"查看进行中的人员监控"+res);
	}
	@Test(description = "查看已完成的人员监控",priority = 9)
	public void testqueryMonitorsFinish() {
		String res = NewEmployeeTrainBusiness.queryMonitors(planId, "2","",dep_id,"","");
		Assert.assertTrue(res.contains("voList"),"查看已完成的人员监控"+res);
	}
	@Test(description = "查看已逾期的人员监控",priority = 10)
	public void testqueryMonitorsOverdue() {
		String res = NewEmployeeTrainBusiness.queryMonitors(planId, "3","","",post_id,"");
		Assert.assertTrue(res.contains("voList"),"查看已逾期的人员监控"+res);
	}
	@Test(description = "查看合格的人员监控",priority = 11)
	public void testqueryMonitors() {
		String res = NewEmployeeTrainBusiness.queryMonitors(planId, "0","","",post_id,"1");
		Assert.assertTrue(res.contains("voList"),"查看合格的人员监控"+res);
	}
	@Test(description = "查看不合格的人员监控",priority = 12)
	public void testqueryMonitor() {
		String res = NewEmployeeTrainBusiness.queryMonitors(planId, "0","","",post_id,"2");
		Assert.assertTrue(res.contains("voList"),"查看不合格的人员监控"+res);
	}
	@Test(description = "对员工进行延期10天",priority = 13)
	public void testTrainEmployeeDelayTen() {
		String res = NewEmployeeTrainBusiness.trainEmployeeDelay(planId,user_id,"10");
		String msg = (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "更新成功","对员工进行延期10天"+res);
	}
	
	@Test(description = "对员工再次进行延期5天",priority = 14)
	public void testTrainEmployeeDelayFive() {
		String res = NewEmployeeTrainBusiness.trainEmployeeDelay(planId,user_id,"5");
		String msg = (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "更新成功","对员工再次进行延期5天"+res);
	}
	@Test(description = "对员工再次进行延期30天",priority = 15)
	public void testTrainEmployeeDelayAgain() {
		String res = NewEmployeeTrainBusiness.trainEmployeeDelay(planId,user_id,"30");
		String msg = (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "更新成功","对员工再次进行延期30天"+res);
	}
	
	@Test(description = "trainEmployeeDelay延期是用户为空",priority= 16)
	public void testTrainEmployeeDelayUserIsNull() {
		String res = NewEmployeeTrainBusiness.trainEmployeeDelay(planId,"","30");
		String msg = (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "计划ID、用户列表、延期天数必填","对员工再次进行延期30天"+res);
	}
	@Test(description = "trainEmployeeDelay延期是planid为空",priority= 17)
	public void testTrainEmployeeDelayPlanIdIsNull() {
		String res = NewEmployeeTrainBusiness.trainEmployeeDelay("","","30");
		String msg = (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "计划ID、用户列表、延期天数必填","对员工再次进行延期30天"+res);
	}
	@Test(description = "trainEmployeeDelay延期是延期天数为空",priority= 18)
	public void testTrainEmployeeDelayDateIsNull() {
		String res = NewEmployeeTrainBusiness.trainEmployeeDelay(planId,"","");
		String msg = (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "计划ID、用户列表、延期天数必填","对员工再次进行延期30天"+res);
	}
	
	@Test(description = "移除某个人的新员工任务",priority = 19)
	public void testDeleteMonitorByUserId() {
		String res = NewEmployeeTrainBusiness.deleteMonitorByUserId(planId, user_id);
		String deleted = (String)JSONPath.read(res, "$.deleted");
		Assert.assertEquals(deleted,"true","移除某个人的新员工任务"+res);
	}
	
	@Test(description = "移除后查看我的学习任务是否也随之移除",priority = 20)
	public void testQueryList() {
		String res = MyStudyTask.queryList(name_01, "0");
		int total = (int)JSONPath.read(res, "$.total");
		Assert.assertTrue(total==0,"移除后查看我的学习任务是否也随之移除"+res);
	}
	
	@Test(description = "删除新员工任务",priority = 21)
	public void testDeleteStageEmployTrainTask() {
		String res = NewEmployeeTrainBusiness.deleteEmployTrainTask(planId);
		String msg = (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "删除成功","删除新员工培训,实际返回结果："+res);
	}
}
