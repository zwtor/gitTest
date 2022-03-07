package my.cases;

import cn.kxy.authentication.business.AppPostAuthenticationBusiness;
import cn.kxy.authentication.business.CertificateBusiness;
import cn.kxy.authentication.business.PostAuthenticationBusiness;
import cn.kxy.my.business.AppMybusiness;
import cn.kxy.my.business.MyOperationsBusiness;
import cn.kxy.setting.bussiness.UserBusiness;
import cn.lazy.init.cases.InitStudyAuthCourse;
import com.alibaba.fastjson.JSONPath;
import com.lazy.common.utils.CommonData;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestPostAuthOperation extends InitStudyAuthCourse{

	String title = "PostScAuth"+CommonData.getStringRandom(5);
	String sc_title = "PostSc"+CommonData.getStringRandom(5);
	String qualificationsId = "";
	@Test(description = "新增带有实操的岗位认证",priority = 1)
	public void addPostAuthSc() {
		String res = MyOperationsBusiness.addPostAuthSc(title, CertificateBusiness.getIdByKeyword(cert_name), sc_title);
		String msg = (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "新增计划成功！","新增带有实操的岗位认证"+res);
	}
	
	@Test(description = "获取岗位认证id",priority = 2)
	public void testGetId () {
		String res = PostAuthenticationBusiness.queryList(title, "0");
		qualificationsId = (String)JSONPath.read(res, "$.list[0].id");
	}
	String oper_id = "";
	@Test(description = "获取岗位认证下的实操id",priority = 3)
	public void testGetOperId() {
		String res = AppPostAuthenticationBusiness.queryAppPostItems(qualificationsId);
		oper_id = (String)JSONPath.read(res, "$.stage_list[0].course_mapping_list[0].id");
	}
	
	@Test(description = "查看岗位认证实操详情",priority = 4)
	public void testQueryOperationInfo () {
		String res = AppPostAuthenticationBusiness.queryOperationInfo(oper_id);
		String operation_source = (String)JSONPath.read(res, "$.data.operation_source");
		String name  = (String)JSONPath.read(res, "$.data.title");
		Assert.assertEquals(name, sc_title,"查看岗位认证d的实操详情,title验证"+res);
		Assert.assertEquals(operation_source, "qualification","查看岗位认证实操详情,operation_source验证"+res);
	}
	@Test(description = "保存实操",priority = 5)
	public void testSaveOperation() {
		String res = MyOperationsBusiness.saveOperation(oper_id, "0", "qualification");
		String msg = (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "提交成功","保存实操,实际返回结果："+res);
	}
	@Test(description = "保存实操后，查看实操详情",priority = 6)
	public void testQueryOperationInfoBySave() {
		String res = MyOperationsBusiness.queryOperationInfo(oper_id);
		String operation_source = (String)JSONPath.read(res, "$.data.operation_source");
		String content  = (String)JSONPath.read(res, "$.data.steps[1].content");
		Assert.assertEquals(operation_source, "qualification","保存实操后，查看实操详情:"+res);
		Assert.assertNotNull(content,"保存实操后，查看实操详情:"+res);
	}
	
	@Test(description = "保存实操后，提交实操",priority = 7)
	public void testsubmitOperation() {
		String res = MyOperationsBusiness.saveOperation(oper_id, "1", "qualification");
		String msg = (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "提交成功","提交实操,实际返回结果："+res);
	}
	
	@Test(description = "提交实操后，查看实操详情",priority = 8)
	public void testSavedQueryOperationInfo() {
		String res = MyOperationsBusiness.queryOperationInfo(oper_id);
		Long time = (Long)JSONPath.read(res, "$.data.submit_time");
		Assert.assertNotNull(time,"提交实操后，查看实操详情，提交时间验证"+res);
	}
	
	
	String approval_id = "";
	String instance_id = "";
	@Test(description = "查看移动端待审核列表的实操详情",priority = 13)
	public void testQueryAppOperationInfo() {
		String res = AppMybusiness.queryOperationInfo(oper_id);
		String pc_name = (String)JSONPath.read(res, "$.data.title");
		approval_id = (String)JSONPath.read(res, "$.data.id");
		instance_id = (String)JSONPath.read(res, "$.data.instance_id");
		
		Assert.assertEquals(sc_title, pc_name,"查看实操详情,title进行验证："+res);
	}
	
	@Test(description = "查看pc我的页面待审核的实操详情",priority = 14)
	public void testQueryOperationApprovalInfo() {
		String res = MyOperationsBusiness.queryOperationApprovalInfo(oper_id,UserBusiness.getUserId());
		int status = (int)JSONPath.read(res, "$.data.status");
		Assert.assertEquals(status, 1,"查看pc我的页面待审核的实操详情，实际返回结果"+res);
	}
	
	@Test(description = "驳回实操",priority = 15)
	public void testApprovalOperationByReject() {
		String res = MyOperationsBusiness.approvalOperation(approval_id, "disagree", "2", "30", "qualification");
		String msg = (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "success","驳回实操，实际返回结果："+res);
	}
	@Test(description = "驳回实操后，再次提交实操",priority = 16)
	public void testsubmitOperationByReject() {
		String res = MyOperationsBusiness.saveOperation(oper_id, "1", "qualification");
		String msg = (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "提交成功","驳回实操后，再次提交实操,实际返回结果："+res);
	}
	@Test(description = "驳回实操后，再次提交实操，查看岗位认证详情",priority = 17)
	public void testQueryStudyInfoByReject() {
		String res = AppPostAuthenticationBusiness.queryAppPostItems(qualificationsId);
		int status =  (int)JSONPath.read(res, "$.status");
		Assert.assertEquals(status,1,"提交实操后，查看岗位认证详情,显示待审核标签"+res);
	}
	@Test(description = "驳回实操后，再次提交实操，进行审批通过",priority = 18)
	public void testApprovalOperationByAgree() {
		String res = MyOperationsBusiness.approvalOperation(approval_id, "agree", "1", "100", "qualification");
		String msg = (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "success","驳回实操，实际返回结果："+res);
	}
	
	@Test(description = "查看审核后的实操评语",priority = 19)
	public void testQueryApprovalComment() {
		String res = MyOperationsBusiness.queryApprovalComment(instance_id);
		Assert.assertTrue(res.contains("pass"),"查看审核后的实操评语，实际返回结果："+res);
		Assert.assertTrue(res.contains("reject"),"查看审核后的实操评语，实际返回结果："+res);
	}
	
	@Test(description = "查看pc端已审核",priority =20)
	public void testQueryPcApprovedOperations() {
		String res = MyOperationsBusiness.queryPcOperations(title, "2", "", "");
		String operation_name = (String)JSONPath.read(res, "$.list[0].operation_name");
		String user_name = (String)JSONPath.read(res, "$.list[0].user_name");
		Long commit_time = (Long)JSONPath.read(res, "$.list[0].commit_time");
		int status = (int)JSONPath.read(res,"$.list[0].status");
		Assert.assertEquals(status, 3,"PC端查看待审批列表,status进行校验"+res);
//		Assert.assertEquals(operation_name,sc_title ,"PC端查看待审批列表,title进行校验"+res);
//		Assert.assertNotNull(user_name,"PC端查看待审批列表，提交人验证"+res);
//		Assert.assertNotNull(commit_time,"PC端查看待审批列表，提交时间验证"+res);
	}
	
	@Test(description = "查看pc我的页面已审核的实操详情",priority = 21)
	public void testQueryOperationApprovedInfo() {
		String res = MyOperationsBusiness.queryOperationApprovalInfo(oper_id,UserBusiness.getUserId());
		int status = (int)JSONPath.read(res, "$.data.status");
		String title = (String)JSONPath.read(res, "$.data.title");
		String operation_source = (String)JSONPath.read(res, "$.data.operation_source");
		String score = (String)JSONPath.read(res, "$.data.score");
		Assert.assertEquals(score, "100.0","查看pc我的页面已审核的实操详情，实操得分验证，实际返回结果"+res);
		Assert.assertEquals(operation_source, "qualification","查看pc我的页面已审核的实操详情,operation_source验证，实际返回结果"+res);
		Assert.assertEquals(title, sc_title,"查看pc我的页面已审核的实操详情,实操标题验证，实际返回结果"+res);
		Assert.assertEquals(status, 3,"查看pc我的页面已审核的实操详情，实际返回结果"+res);
	}
	
	@Test(description ="删除岗位认证" , priority = 22)
	public void testDeleteAuthentication() {
		String res = PostAuthenticationBusiness.deleteAuthentication(qualificationsId);
		String msg = (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "能力认证删除成功","删除岗位认证，实际返回结果："+res);
	}
}
