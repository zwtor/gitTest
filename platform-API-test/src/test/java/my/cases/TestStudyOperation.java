package my.cases;

import cn.kxy.authentication.business.AppPostAuthenticationBusiness;
import cn.kxy.examination.business.PaperExportBusiness;
import cn.kxy.my.business.AppMybusiness;
import cn.kxy.my.business.MyOperationsBusiness;
import cn.kxy.setting.bussiness.UserBusiness;
import cn.kxy.study.business.AppStudyBusiness;
import cn.kxy.study.business.StudyTaskBusiness;
import com.alibaba.fastjson.JSONPath;
import com.lazy.common.utils.CommonData;
import com.lazy.common.utils.DateUtil;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestStudyOperation {

	String title = "StudyOpertion" + CommonData.getStringRandom(5);
	String pc_title = "MyOperation" + CommonData.getStringRandom(5);
	String study_id = "";
	@Test(description = "新增带有实操的学习任务",priority = 1)
	public void testAddStudyOperation() {
		String res = MyOperationsBusiness.addStudyOperation(title, DateUtil.getTimeStamp(0, ""),
				DateUtil.getTimeStamp(5, ""), UserBusiness.getUserId(), pc_title);
		String msg= (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "新增计划成功！","添加线上学习任务，实际返回结果:"+res);
	}
	@Test(description = "获取学习的id",priority = 2)
	public void testGetId() {
		String res = StudyTaskBusiness.getStudyTaskList(title, "false", "0", "");
		study_id = (String)JSONPath.read(res, "$.list[0].id");
	}
	
	String oper_id = "";
	@Test(description = "查看移动端带有实操的学习任务详情,获取实操id" , priority = 3)
	public void testQueryOperationStudyInfo() {
		String res = AppStudyBusiness.queryInfo(study_id);
		oper_id = (String)JSONPath.read(res, "$.stages[0].course_list[0].id");
	}
	
	@Test(description = "查看实操详情",priority = 4)
	public void testQueryOperationInfo() {
		String res = MyOperationsBusiness.queryOperationInfo(oper_id);
		String pc_name = (String)JSONPath.read(res, "$.data.title");
		Assert.assertNotSame(pc_title, pc_name,"查看实操详情,title进行验证："+res);
	}
	
	@Test(description = "保存实操",priority = 5)
	public void testSaveOperation() {
		String res = MyOperationsBusiness.saveOperation(oper_id, "0", "study");
		String msg = (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "提交成功","保存实操,实际返回结果："+res);
	}
	@Test(description = "保存实操后，查看实操详情",priority = 6)
	public void testQueryOperationInfoBySave() {
		String res = MyOperationsBusiness.queryOperationInfo(oper_id);
		String operation_source = (String)JSONPath.read(res, "$.data.operation_source");
		String content  = (String)JSONPath.read(res, "$.data.steps[1].content");
		Assert.assertEquals(operation_source, "study","保存实操后，查看实操详情:"+res);
		Assert.assertNotNull(content,"保存实操后，查看实操详情:"+res);
	}
	
	@Test(description = "保存实操后，提交实操",priority = 7)
	public void testsubmitOperation() {
		String res = MyOperationsBusiness.saveOperation(oper_id, "1", "study");
		String msg = (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "提交成功","提交实操,实际返回结果："+res);
	}
	
	@Test(description = "提交实操后，查看学习任务详情",priority = 8)
	public void testQueryStudyInfo() {
		String res = AppStudyBusiness.queryInfo(study_id);
		String status = (String)JSONPath.read(res, "$.stages[0].course_list[0].status");
		Assert.assertEquals(status,"1","提交实操后，查看学习任务详情,显示待审核标签"+res);
	}
	
	@Test(description = "提交实操后，查看实操详情",priority = 9)
	public void testSavedQueryOperationInfo() {
		String res = MyOperationsBusiness.queryOperationInfo(oper_id);
		Long time = (Long)JSONPath.read(res, "$.data.submit_time");
		Assert.assertNotNull(time,"提交实操后，查看实操详情，提交时间验证"+res);
	}
	
	@Test(description = "移动端查看待审批列表---通过状态查询",priority = 10)
	public void testQueryOperationApproveList() {
		String res = AppMybusiness.queryOperationApproveList("1");
		String name = (String)JSONPath.read(res, "$.list[0].operation_review_list[0].operation_name");
		int status = (int)JSONPath.read(res, "$.list[0].operation_review_list[0].status");
		String stu_title = (String)JSONPath.read(res, "$.list[0].title");
		Long time = (Long)JSONPath.read(res, "$.list[0].commit_time");
		Assert.assertNotNull(time,"移动端查看待审批列表，提交时间验证"+res);
		Assert.assertEquals(status, 1,"移动端查看待审批列表,status进行校验"+res);
		Assert.assertNotNull(name,"移动端查看待审批列表,实操name进行校验"+res);
		Assert.assertNotNull(stu_title ,"移动端查看待审批列表,title进行校验"+res);
	}
	
	@Test(description = "查看pc端我的实操待审核列表",priority = 11)
	public void testQueryPcOperationByStatus() {
		String res = MyOperationsBusiness.queryPcOperations("", "1", "", "");
		Assert.assertTrue(res.contains("total"),"查看pc端我的实操待审核列表,实际返回结果"+res);
	}
	
	@Test(description = "查看pc端我的实操待审核列表---通过标题查询",priority = 12)
	public void testQueryPcOperationByKeyword() {
		String res = MyOperationsBusiness.queryPcOperations(title, "1", "", "");
		String stu_title = (String)JSONPath.read(res, "$.list[0].title");
		String operation_name = (String)JSONPath.read(res, "$.list[0].operation_name");
		String user_name = (String)JSONPath.read(res, "$.list[0].user_name");
		Long commit_time = (Long)JSONPath.read(res, "$.list[0].commit_time");
		int status = (int)JSONPath.read(res,"$.list[0].status");
		Assert.assertEquals(status, 1,"PC端查看待审批列表,status进行校验"+res);
		Assert.assertEquals(stu_title,title ,"PC端查看待审批列表,title进行校验"+res);
		Assert.assertEquals(operation_name,pc_title ,"PC端查看待审批列表,title进行校验"+res);
		Assert.assertNotNull(user_name,"PC端查看待审批列表，提交人验证"+res);
		Assert.assertNotNull(commit_time,"PC端查看待审批列表，提交时间验证"+res);
	}
	
	@Test(description = "查看pc端我的实操待审核列表---通过时间查询",priority = 13)
	public void testQueryPcOperationByTime() {
		String res = MyOperationsBusiness.queryPcOperations("", "1", DateUtil.getTimeStamp(-10, ""), DateUtil.getTimeStamp(0, ""));
		Assert.assertTrue(res.contains("total"),"查看pc端我的实操待审核列表---通过时间查询,实际返回结果"+res);
	}
	
	String approval_id = "";
	String instance_id = "";
	@Test(description = "查看移动端待审核列表的实操详情",priority = 14)
	public void testQueryAppOperationInfo() {
		String res = AppMybusiness.queryOperationInfo(oper_id);
		String pc_name = (String)JSONPath.read(res, "$.data.title");
		approval_id = (String)JSONPath.read(res, "$.data.id");
		instance_id = (String)JSONPath.read(res, "$.data.instance_id");
		
		Assert.assertEquals(pc_title, pc_name,"查看实操详情,title进行验证："+res);
	}
	
	@Test(description = "查看pc我的页面待审核的实操详情",priority = 15)
	public void testQueryOperationApprovalInfo() {
		String res = MyOperationsBusiness.queryOperationApprovalInfo(oper_id,UserBusiness.getUserId());
		int status = (int)JSONPath.read(res, "$.data.status");
		Assert.assertEquals(status, 1,"查看pc我的页面待审核的实操详情，实际返回结果"+res);
	}
	
	@Test(description = "驳回实操",priority = 16)
	public void testApprovalOperationByReject() {
		String res = MyOperationsBusiness.approvalOperation(approval_id, "disagree", "2", "30", "study");
		String msg = (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "success","驳回实操，实际返回结果："+res);
	}
	@Test(description = "驳回实操后，再次提交实操",priority = 17)
	public void testsubmitOperationByReject() {
		String res = MyOperationsBusiness.saveOperation(oper_id, "1", "study");
		String msg = (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "提交成功","驳回实操后，再次提交实操,实际返回结果："+res);
	}
	@Test(description = "驳回实操后，再次提交实操，查看学习任务详情",priority = 18)
	public void testQueryStudyInfoByReject() {
		String res = AppStudyBusiness.queryInfo(study_id);
		String status = (String)JSONPath.read(res, "$.stages[0].course_list[0].status");
		Assert.assertEquals(status,"1","提交实操后，查看学习任务详情,显示待审核标签"+res);
	}
	@Test(description = "驳回实操后，再次提交实操，进行审批通过",priority = 19)
	public void testApprovalOperationByAgree() {
		String res = MyOperationsBusiness.approvalOperation(approval_id, "agree", "1", "100", "study");
		String msg = (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "success","驳回实操，实际返回结果："+res);
	}
	
	@Test(description = "查看审核后的实操评语",priority = 20)
	public void testQueryApprovalComment() {
		String res = MyOperationsBusiness.queryApprovalComment(instance_id);
		Assert.assertTrue(res.contains("pass"),"查看审核后的实操评语，实际返回结果："+res);
		Assert.assertTrue(res.contains("reject"),"查看审核后的实操评语，实际返回结果："+res);
	}
	
	
	@Test(description = "查看pc端已审核",priority =21)
	public void testQueryPcApprovedOperations() {
		String res = MyOperationsBusiness.queryPcOperations(title, "2", "", "");
		String stu_title = (String)JSONPath.read(res, "$.list[0].title");
		String operation_name = (String)JSONPath.read(res, "$.list[0].operation_name");
		String user_name = (String)JSONPath.read(res, "$.list[0].user_name");
		Long commit_time = (Long)JSONPath.read(res, "$.list[0].commit_time");
		int status = (int)JSONPath.read(res,"$.list[0].status");
		Assert.assertEquals(status, 3,"PC端查看待审批列表,status进行校验"+res);
		Assert.assertEquals(stu_title,title ,"PC端查看待审批列表,title进行校验"+res);
		Assert.assertEquals(operation_name,pc_title ,"PC端查看待审批列表,title进行校验"+res);
		Assert.assertNotNull(user_name,"PC端查看待审批列表，提交人验证"+res);
		Assert.assertNotNull(commit_time,"PC端查看待审批列表，提交时间验证"+res);
	}
	
	@Test(description = "查看pc我的页面已审核的实操详情",priority = 22)
	public void testQueryOperationApprovedInfo() {
		String res = MyOperationsBusiness.queryOperationApprovalInfo(oper_id,UserBusiness.getUserId());
		int status = (int)JSONPath.read(res, "$.data.status");
		String title = (String)JSONPath.read(res, "$.data.title");
		String content = (String)JSONPath.read(res, "$.data.content");
		String score = (String)JSONPath.read(res, "$.data.score");
		Assert.assertEquals(score, "100.0","查看pc我的页面已审核的实操详情，实操得分验证，实际返回结果"+res);
		Assert.assertEquals(content, "this is a content","查看pc我的页面已审核的实操详情,content验证，实际返回结果"+res);
		Assert.assertEquals(title, pc_title,"查看pc我的页面已审核的实操详情,实操标题验证，实际返回结果"+res);
		Assert.assertEquals(status, 3,"查看pc我的页面已审核的实操详情，实际返回结果"+res);
	}
	
	@Test(description = "清除所有导出数据",priority = 23)
	public void deleteAllRecord() {
		String res = PaperExportBusiness.deleteAllRecord();
		Assert.assertTrue(res.contains("OK"),"清除所有导出数据,实际返回结果："+res);
	}
	
	@Test(description = "导出实操作业分析",priority = 24)
	public void testOperationsUseMonitorsExport() {
		String res = AppPostAuthenticationBusiness.operationsUseMonitorsExport(oper_id);
		Assert.assertTrue(res.contains("export success"),"导出实操作业分析"+res);
	}
	
	@Test(description = "导出实操作业分析后，在下载中心查看",priority = 25)
	public void exportRecordList() {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		String res = PaperExportBusiness.exportRecordList();
		String status = (String)JSONPath.read(res, "$.list[0].status");
		String type = (String)JSONPath.read(res, "$.list[0].type");
		Assert.assertFalse(status=="FAILED", "查看导出的结果:"+res);
		Assert.assertTrue(res.contains("OPERATION_ANALYZE_EXPORT") ,res);
	}
	
	@Test(description="删除学习计划任务",priority= 26)
	public void testDeleteStudyTask() {
		String res = StudyTaskBusiness.deleteStudyTask(study_id);
		String msg= (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "删除学习计划成功","删除学习计划任务，实际返回结果:"+res);
	}
}
