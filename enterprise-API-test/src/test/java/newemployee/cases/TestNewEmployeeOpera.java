package newemployee.cases;

import cn.kxy.authentication.business.AppPostAuthenticationBusiness;
import cn.kxy.examination.business.PaperExportBusiness;
import cn.kxy.setting.bussiness.UserBusiness;
import cn.kxy.study.business.AppStudyBusiness;
import cn.kxy.study.business.NewEmployeeTrainBusiness;
import com.alibaba.fastjson.JSONPath;
import com.lazy.common.utils.CommonData;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestNewEmployeeOpera {
	
	String title = "NewEmployeeOpera"+CommonData.getStringRandom(5);
 
	String id = "";
	String plan_id = "";
	String user_id = UserBusiness.getUserId();
	@Test(description = "新增带有实操的新员工任务",priority = 1)
	public void testAddNewEmployeeOpera() {
		String res = NewEmployeeTrainBusiness.addNewEmployeeOpera(title);
		System.out.println("this is NewEmployee module");
		Assert.assertTrue(res.contains("新建成功"),""+res);
	}
	int status ;
	@Test(description = "获取id",priority = 2)
	public void testGetId() {
		String res = NewEmployeeTrainBusiness.queryList(title, "false", "0", "0","","");
		id = (String)JSONPath.read(res, "$.list[0].id");
		plan_id= (String)JSONPath.read(res, "$.list[0].planId");
		status = (int)JSONPath.read(res,"$.list[0].status");
	}
	@Test(description="启用新员工培训",priority = 3)
	public void testBeginTask() {
		if (status==2) {
			String res = NewEmployeeTrainBusiness.beginAndStopTask(id, "1");
			String msg = (String)JSONPath.read(res, "$.msg");
			Assert.assertEquals(msg, "启用成功","启用新员工培训,实际返回结果："+res);
		}
	}
	
	@Test(description = "按照人指派新员工培训",priority = 4)
	public void testAssignNewTaskByUserId() {
		String res = NewEmployeeTrainBusiness.assignNewTask(id, "", user_id, "", "");
		String result = (String)JSONPath.read(res, "$.result");
		Assert.assertEquals(result,"指派成功", "按照人指派新员工培训："+res);
	}
	String approve_id = "";
	@Test(description = "查询审批人列表",priority = 5)
	public void testQueryOperaApprovalList() {
		String res = NewEmployeeTrainBusiness.queryOperaApprovalList(plan_id);
		approve_id = (String)JSONPath.read(res, "$.operations[0].id");
	}
	
	@Test(description = "编辑新员工的实操审批人",priority = 6)
	public void testModifyApprovalPerson() {
		String res = NewEmployeeTrainBusiness.modifyApprovalPerson(approve_id);
		Assert.assertTrue(res.contains("true"),""+res);
	}
	String oper_id = "";
	@Test(description = "移动端查看新员工任务的实操",priority = 7)
	public void testQueryAppInfo() {
		String res = AppStudyBusiness.queryAppInfo(plan_id);
		oper_id = (String)JSONPath.read(res, "$.stages[0].course_list[0].id");
	}
	
	@Test(description = "提交实操",priority = 8)
	public void testSaveOperation() {
		String res = NewEmployeeTrainBusiness.saveOperation(oper_id, "1", "study");
		String msg = (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "提交成功","提交实操,实际返回结果："+res);
	}
	
	@Test(description = "查看新员工的实操分析数据",priority = 10)
	public void testQueryOperationAnalysis() {
		String res = AppPostAuthenticationBusiness.queryOperationAnalysis("study", plan_id);
		String name = (String)JSONPath.read(res, "$.operations[0].title");
		Assert.assertNotNull(name);
	}
	
	
	@Test(description = "查看新员工实操监控",priority = 11)
	public void testUseOperativalMonitors() {
		String res = NewEmployeeTrainBusiness.useOperativalMonitors(oper_id);
		Assert.assertTrue(res.contains("total"));
	}
	
	@Test(description = "清除所有导出数据",priority = 12)
	public void deleteAllRecord() {
		String res = PaperExportBusiness.deleteAllRecord();
		Assert.assertTrue(res.contains("OK"),"清除所有导出数据,实际返回结果："+res);
	}
	
	@Test(description = "导出新员工实操监控",priority = 13)
	public void testNewOperExport() {
		String res = NewEmployeeTrainBusiness.newOperExport(oper_id);
		Assert.assertTrue(res.contains("export success "));
	}
	
	@Test(description = "查看学习记录详情", priority= 14)
	public void queryStudyRecordInfo() {
		String res = AppStudyBusiness.queryStudyRecordInfo(plan_id);
		Assert.assertTrue(res.contains("result"),"查看学习记录详情"+res);
	}
	
	@Test(description = "导出学习记录", priority = 15)
	public void testQueryLatojasInfo() {
		String res = AppStudyBusiness.studyRecordExport(plan_id);
		Assert.assertTrue(res.contains("学习记录导出记录已生成"),"导出学习记录，实际返回结果："+res);
	}
	@Test(description = "查看导出的结果",priority = 16)
	public void exportRecordList() {
		String res = PaperExportBusiness.exportRecordList();
		String status = (String)JSONPath.read(res, "$.list[0].status");
		Assert.assertFalse(status=="FAILED", "查看导出的结果:"+res);	
	}
	@Test(description = "删除新员工任务",priority = 17)
	public void testDeleteStageEmployTrainTask() {
		String res = NewEmployeeTrainBusiness.deleteEmployTrainTask(plan_id);
		String msg = (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "删除成功","删除新员工培训,实际返回结果："+res);
	}
}
