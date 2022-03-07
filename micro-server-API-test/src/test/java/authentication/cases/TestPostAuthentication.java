package authentication.cases;

import cn.kxy.authentication.business.CertificateBusiness;
import cn.kxy.authentication.business.PostAuthenticationBusiness;
import cn.kxy.base.business.BaseBusiness;
import cn.kxy.course.resources.bussiness.ArticleBusiness;
import cn.kxy.course.resources.bussiness.CourseBusiness;
import cn.kxy.examination.business.PaperBusiness;
import cn.kxy.examination.business.PaperExportBusiness;
import cn.kxy.setting.bussiness.UserBusiness;
import cn.lazy.init.cases.InitStudyAuthCourse;
import com.alibaba.fastjson.JSONPath;
import com.lazy.common.utils.CommonData;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestPostAuthentication extends InitStudyAuthCourse {

	String post_name = "settle_post"+CommonData.getStringRandom(2);
	String des = "This is a PostAuthentication Desc";
	String qualificationsId="";
	String edit_name = "CMO_Post"+CommonData.getStringRandom(2);
	@Test(description="添加岗位认证",priority=1)
	public void testAddPostAuthentication() {
		String res = PostAuthenticationBusiness.addPostAuthentication(post_name, des, CertificateBusiness.getIdByKeyword(cert_name), 
				"0", ArticleBusiness.getIdByKeyword(articlename), CourseBusiness.getIdByPage(course_name), 
				"sc", "This is a sc desc", "exam", "120",PaperBusiness.getIdByKeyword(BaseBusiness.pass_paper_name) ,  BaseBusiness.pass_paper_name);
		String msg = (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "新增计划成功！","添加岗位认证"+res);
	}
	@Test(description="查询岗位认证列表（全部）",priority=2)
	public void testQueryList() {
		String res = PostAuthenticationBusiness.queryList(post_name, "0");
		String name = (String)JSONPath.read(res, "$.list[0].title");
		int studyCount = (int)JSONPath.read(res, "$.list[0].studyCount");
		int operationCount = (int)JSONPath.read(res, "$.list[0].operationCount");
		int examCount = (int)JSONPath.read(res, "$.list[0].examCount");
		int pendingCount = (int)JSONPath.read(res, "$.list[0].pendingCount");
		int qualificationUserCount = (int)JSONPath.read(res, "$.list[0].qualificationUserCount");
		String createName = (String)JSONPath.read(res, "$.list[0].createName");
		qualificationsId = (String)JSONPath.read(res, "$.list[0].id");
		String statusName = (String)JSONPath.read(res, "$.list[0].statusName");
		int status = (int)JSONPath.read(res, "$.list[0].status");

		Assert.assertEquals(statusName, "启用","查询岗位认证列表（全部）实际返回结果："+res);
		Assert.assertEquals(status, 1,"查询岗位认证列表（全部）实际返回结果："+res);
		Assert.assertEquals(qualificationUserCount, 0,"查询岗位认证列表（全部）实际返回结果："+res);
		Assert.assertEquals(createName, UserBusiness.getUsername(),"查询岗位认证列表（全部）实际返回结果："+res);
		Assert.assertEquals(pendingCount, 0,"查询岗位认证列表（全部）实际返回结果："+res);
		Assert.assertEquals(examCount, 1,"查询岗位认证列表（全部）实际返回结果："+res);
		Assert.assertEquals(operationCount, 1,"查询岗位认证列表（全部）实际返回结果："+res);
		Assert.assertEquals(studyCount, 2,"查询岗位认证列表（全部）实际返回结果："+res);
		Assert.assertEquals(name, post_name,"查询岗位认证列表（全部）实际返回结果："+res);
	}
	@Test(description="停用岗位认证",priority=3)
	public void testShutdownPost() {
		String res = PostAuthenticationBusiness.StartShutdownPost(qualificationsId, "2");
		String msg = (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "能力认证状态更新成功！","停用岗位认证实际返回结果："+res);
	}
	
	@Test(description="查看岗位认证已停用列表",priority=4)
	public void testShutdownPostList() {
		String res = PostAuthenticationBusiness.queryList(post_name, "2");
		String name = (String)JSONPath.read(res, "$.list[0].title");
		int studyCount = (int)JSONPath.read(res, "$.list[0].studyCount");
		int operationCount = (int)JSONPath.read(res, "$.list[0].operationCount");
		int examCount = (int)JSONPath.read(res, "$.list[0].examCount");
		int pendingCount = (int)JSONPath.read(res, "$.list[0].pendingCount");
		int qualificationUserCount = (int)JSONPath.read(res, "$.list[0].qualificationUserCount");
		String createName = (String)JSONPath.read(res, "$.list[0].createName");
		qualificationsId = (String)JSONPath.read(res, "$.list[0].id");
		String statusName = (String)JSONPath.read(res, "$.list[0].statusName");
		int status = (int)JSONPath.read(res, "$.list[0].status");

		Assert.assertEquals(statusName, "停用","查看岗位认证已停用列表实际返回结果："+res);
		Assert.assertEquals(status, 2,"查看岗位认证已停用列表实际返回结果："+res);
		Assert.assertEquals(qualificationUserCount, 0,"查看岗位认证已停用列表实际返回结果："+res);
		Assert.assertEquals(createName, UserBusiness.getUsername(),"查看岗位认证已停用列表实际返回结果："+res);
		Assert.assertEquals(pendingCount, 0,"查看岗位认证已停用列表实际返回结果："+res);
		Assert.assertEquals(examCount, 1,"查看岗位认证已停用列表实际返回结果："+res);
		Assert.assertEquals(operationCount, 1,"查看岗位认证已停用列表实际返回结果："+res);
		Assert.assertEquals(studyCount, 2,"查看岗位认证已停用列表实际返回结果："+res);
		Assert.assertEquals(name, post_name,"查看岗位认证已停用列表实际返回结果："+res);
	}
	
	@Test(description="查询岗位认证详情",priority=5)
	public void testQueryInfo() {
		String res = PostAuthenticationBusiness.queryInfo(qualificationsId);
		String msg = (String)JSONPath.read(res, "$.msg");
		String title = (String)JSONPath.read(res, "$.data.title");
		Assert.assertEquals(title, post_name,"查询岗位认证详情实际返回结果："+res);
		Assert.assertEquals(msg, "查询成功","查询岗位认证详情实际返回结果："+res);
	}
	
	
	@Test(description="编辑岗位认证",priority=6)
	public void testEditPostAuthentication() {
		
		String res = PostAuthenticationBusiness.editPostAuthentication(qualificationsId,edit_name, des, CertificateBusiness.getIdByKeyword(cert_name), 
				"0", ArticleBusiness.getIdByKeyword(articlename ), CourseBusiness.getIdByPage(course_name), 
				"sc", "This is a sc desc", "exam", "120",PaperBusiness.getIdByKeyword(BaseBusiness.pass_paper_name) ,  BaseBusiness.pass_paper_name);
		String msg = (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "编辑计划成功！","编辑岗位认证"+res);
	}
	
	@Test(description="编辑后，查看岗位认证列表",priority=7)
	public void testEditPostList() {
		String res = PostAuthenticationBusiness.queryList(edit_name, "2");
		String name = (String)JSONPath.read(res, "$.list[0].title");
		Assert.assertEquals(name, edit_name,"编辑后，查看岗位认证列表实际返回结果："+res);
	}
	
	@Test(description="启用岗位认证",priority=8)
	public void testStartPost() {
		String res = PostAuthenticationBusiness.StartShutdownPost(qualificationsId, "1");
		String msg = (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "能力认证状态更新成功！","启用岗位认证实际返回结果："+res);
	}
	
	@Test(description="查看岗位认证已启用列表",priority=9)
	public void testStartPostList() {
		String res = PostAuthenticationBusiness.queryList(edit_name, "1");
		String name = (String)JSONPath.read(res, "$.list[0].title");
		int operationCount = (int)JSONPath.read(res, "$.list[0].operationCount");
		int examCount = (int)JSONPath.read(res, "$.list[0].examCount");
		int pendingCount = (int)JSONPath.read(res, "$.list[0].pendingCount");
		int qualificationUserCount = (int)JSONPath.read(res, "$.list[0].qualificationUserCount");
		String createName = (String)JSONPath.read(res, "$.list[0].createName");
		qualificationsId = (String)JSONPath.read(res, "$.list[0].id");
		String statusName = (String)JSONPath.read(res, "$.list[0].statusName");
		int status = (int)JSONPath.read(res, "$.list[0].status");

		Assert.assertEquals(statusName, "启用","查看岗位认证已启用列表实际返回结果："+res);
		Assert.assertEquals(status, 1,"查看岗位认证已启用列表实际返回结果："+res);
		Assert.assertEquals(qualificationUserCount, 0,"查看岗位认证已启用列表实际返回结果："+res);
		Assert.assertEquals(createName, UserBusiness.getUsername(),"查看岗位认证已启用列表实际返回结果："+res);
		Assert.assertEquals(pendingCount, 0,"查看岗位认证已启用列表实际返回结果："+res);
		Assert.assertEquals(examCount, 1,"查看岗位认证已启用列表实际返回结果："+res);
		Assert.assertEquals(operationCount, 1,"查看岗位认证已启用列表实际返回结果："+res);
		Assert.assertEquals(name, edit_name,"查看岗位认证已启用列表实际返回结果："+res);
	}
	
	@Test(description= "编辑已启用的岗位认证提示语校验",priority=10)
	public void testEditStartPostAuthentication() {
		
		String res = PostAuthenticationBusiness.editPostAuthentication(qualificationsId,edit_name, des, CertificateBusiness.getIdByKeyword(cert_name), 
				"0", ArticleBusiness.getIdByKeyword(articlename ), CourseBusiness.getIdByPage(course_name), 
				"sc", "This is a sc desc", "exam", "120",PaperBusiness.getIdByKeyword(BaseBusiness.pass_paper_name) ,  BaseBusiness.pass_paper_name);
		String msg = (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "编辑能力认证任务失败，任务正在启用！","编辑已启用的岗位认证提示语校验,实际返回结果："+res);
	}
/*	@Test(description="更改审批人为部门主管",priority=11)
	public void testChangeLeadApprover() {
		String res = PostAuthenticationBusiness.changeApprover(qualificationsId, "lead", "");
		String type = (String)JSONPath.read(res, "$.type");
		Assert.assertEquals(type, "lead","更改审批人为部门主管，实际返回结果："+res);
	}
	
	@Test(description="更改审批人为或签审批",priority=12)
	public void testChangeApprover() {
		String res = PostAuthenticationBusiness.changeApprover(qualificationsId, "or", UserBusiness.getUserId());
		String type = (String)JSONPath.read(res, "$.type");
		Assert.assertEquals(type, "or","更改审批人为或签审批，实际返回结果："+res);
	}*/
	
	@Test(description="未认证前查看岗位认证不同状态下的数据监控",dataProvider="monitorsQualifications",priority=13)
	public void testMonitorsQualifications(String kind,String type) {
		String username = UserBusiness.getUsername();
		String res = PostAuthenticationBusiness.monitorsQualifications(qualificationsId, "", type, "1");
		String title = (String)JSONPath.read(res, "$.data.qualification_data.title");
		String summary = (String)JSONPath.read(res, "$.data.qualification_data.summary");
		String create_name = (String)JSONPath.read(res, "$.data.qualification_data.create_name");
		int total_count = (int)JSONPath.read(res, "$.data.total_count");
		
		Assert.assertEquals(total_count, 0,"未认证前查看岗位认证的数据监控---"+type+"，实际返回结果："+res);
		Assert.assertEquals(summary, des,"未认证前查看岗位认证的数据监控---"+type+"，实际返回结果："+res);
		Assert.assertEquals(create_name, username,"未认证前查看岗位认证的数据监控---"+type+"，实际返回结果："+res);
		Assert.assertEquals(title, edit_name,"未认证前查看岗位认证的数据监控---"+type+"，实际返回结果："+res);
	}
	
	@Test(description = "清除所有导出数据",priority = 14)
	public void deleteAllRecord() {
		String res = PaperExportBusiness.deleteAllRecord();
		Assert.assertTrue(res.contains("OK"),"清除所有导出数据,实际返回结果："+res);
	}
	
	@Test(description="导出岗位认证全部状态数据",priority=15)
	public void testGetMonitorsExportAllCode() {
		int code = PostAuthenticationBusiness.getMonitorsExportCode(qualificationsId, "0");
		Assert.assertEquals(code, 200,"导出岗位认证全部状态数据:"+code);
	}
	@Test(description="导出岗位认证已完成状态数据",priority=16)
	public void testGetMonitorsExportFinishCode() {
		int code = PostAuthenticationBusiness.getMonitorsExportCode(qualificationsId, "1");
		Assert.assertEquals(code, 200,"导出岗位认证已完成状态数据:"+code);
	}
	@Test(description="导出岗位认证进行中数据",priority=17)
	public void testGetMonitorsExportOngoingCode() {
		int code = PostAuthenticationBusiness.getMonitorsExportCode(qualificationsId, "2");
		Assert.assertEquals(code, 200,"导出岗位认证进行中数据:"+code);
	}
	@Test(description="导出岗位认证待审核数据",priority=18)
	public void testGetMonitorsExportPedingCode() {
		int code = PostAuthenticationBusiness.getMonitorsExportCode(qualificationsId, "3");
		Assert.assertEquals(code, 200,"导出岗位认证待审核数据:"+code);
	}
	
	@Test(description = "导出员工数据后，在下载中心查看",priority  = 19)
	public void exportRecordList() {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		String res = PaperExportBusiness.exportRecordList();
		String status = (String)JSONPath.read(res, "$.list[0].status");
		String type = (String)JSONPath.read(res, "$.list[0].type");
		String status_1 = (String)JSONPath.read(res, "$.list[1].status");
		String type_1 = (String)JSONPath.read(res, "$.list[1].type");
		String status_2 = (String)JSONPath.read(res, "$.list[2].status");
		String type_2 = (String)JSONPath.read(res, "$.list[2].type");
		String status_3 = (String)JSONPath.read(res, "$.list[3].status");
//		Assert.assertEquals(type, "PERSONNEL_MONITOR_EXPORT","导出员工数据后，在下载中心查看"+res);
//		Assert.assertEquals(type_1, "PERSONNEL_MONITOR_EXPORT","导出员工数据后，在下载中心查看"+res);
//		Assert.assertEquals(type_2, "PERSONNEL_MONITOR_EXPORT","导出员工数据后，在下载中心查看"+res);
		Assert.assertFalse(status=="FAILED", "查看导出的结果:"+res);
		Assert.assertFalse(status_1=="FAILED", "查看导出的结果:"+res);
		Assert.assertFalse(status_3=="FAILED", "查看导出的结果:"+res);
		Assert.assertFalse(status_2=="FAILED", "查看导出的结果:"+res);
	}
	
	
	@Test(description="删除岗位认证",priority=20)
	public void testDeleteAuthentication() {
		String res = PostAuthenticationBusiness.deleteAuthentication(qualificationsId);
		String msg = (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "能力认证删除成功","删除岗位认证，实际返回结果："+res);
	}

	@DataProvider(name = "monitorsQualifications")
	public Object[][] monitorsQualifications() {
		Object[][] obj = new Object[][] { 
			{"查看全部","0"},{"查看进行中","2"},{"查看待审核","3"},{"查看已完成","1"}
			};
		return obj;
	}
	
}
