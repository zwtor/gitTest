package studyproject.cases;

import cn.kxy.setting.bussiness.UserBusiness;
import com.lazy.common.utils.CommonData;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(groups = {"studyProject"})
public class TestStudyProjectSignUpWebApprove {

//	String title ="SignUpStudyProject"+CommonData.getStringRandom(3);
	String study_id = "";
	String user_name = UserBusiness.getUsername();
	String course_id = "";
	String enroll_id = "";
	@BeforeClass
	public void initStudySignUp() {
//		String res = StudyProjectNewBusinesss.addStudyProjectSingUp(title, PaperBusiness.getIdByKeyword(BaseBusiness.pass_paper_name), "1", 
//				"true", "5", "0", "0", "manual_audit", "true", "3");
//		System.out.println(res);
//		
//		study_id = (String) JSONPath.read(res, "$.id");
//		course_id = (String)JSONPath.read(res, "$.course_id");
//		System.out.println(course_id);
//		StudyProjectBusiness.updateStatus(study_id, "normal");
//		String id = (String)JSONPath.read(res, "$.id");
//		Assert.assertNotNull(id, "新增学习项目成功："+res);
	}

	@Test(description="报名前查看学习项目详情页",priority=1)
	public void testSignUpQueryInfo () {
//		String res = MySelfStudyBusiness.queryStudyCourseInfo(course_id);
//		System.out.println(res);
//		int enroll_user_count = (int)JSONPath.read(res, "$.enroll_user_count");
//		enroll_id = (String)JSONPath.read(res, "$.enroll_id");
//		String enroll_status = (String)JSONPath.read(res, "$.enroll_status");
//		Assert.assertEquals(enroll_status, "unlimited","报名前查看学习项目详情页,enroll_status字段进行校验"+res);
//		Assert.assertEquals(enroll_user_count, 0,"报名前查看学习项目详情页,报名人数应为0"+res);
	}
	
	@Test(description="报名前，学习项目列表的报名人数应为0",priority=2)
	public void testQueryLearningProjectList() {
//		String res = StudyProjectBusiness.queryLearningProjectList(title, "");
//		int count = (int)JSONPath.read(res, "$.list[0].enroll_user_count");
//		Assert.assertEquals(count, 0,"报名前，学习项目列表的报名人数应为0"+res);
	}
	
	@Test(description="对学习项目进行报名",priority=3)
	public void testSignUp() {
//		String res = StudyProjectBusiness.signUp(enroll_id);
//		String result = (String)JSONPath.read(res, "$.result");
//		Assert.assertEquals(result, "true");
	}
	
	@Test(description="报名后 ，查看课程详情，状态应为待审批",priority=4)
	public void testQueryStudyCourseInfo() {
//		String res = MySelfStudyBusiness.queryStudyCourseInfo(course_id);
//		String enroll_status = (String)JSONPath.read(res, "$.enroll_status");
//		Assert.assertEquals(enroll_status, "wait","报名前查看学习项目详情页,报名人数应为0"+res);
	}
	@Test(description="报名后，学习项目列表的报名人数应为1",priority=5)
	public void testQueryLearningProjectSignUpList() {
//		String res = StudyProjectBusiness.queryLearningProjectList(title, "");
//		int count = (int)JSONPath.read(res, "$.list[0].enroll_user_count");
	}
	@Test(description="查看web端审核列表--全部",priority=6)
	public void testSignUpAllList() {
//		String res = StudyProjectBusiness.signUpList(enroll_id, user_name, "");
//		String name = (String)JSONPath.read(res, "$.list[0].user_name");
//		String department_name = (String)JSONPath.read(res, "$.list[0].department_name");
//		long create_time = (long)JSONPath.read(res, "$.list[0].create_time");
//		String status = (String)JSONPath.read(res, "$.list[0].status");
//		Assert.assertEquals(name, user_name,"查看web端审核列表--全部，名称进行校验"+res);
//		Assert.assertNotNull(department_name, "查看web端审核列表--全部，部门名称进行校验"+res);
//		Assert.assertNotNull(create_time, "查看web端审核列表--全部，创建时间进行校验"+res);
//		Assert.assertNotNull(status, "查看web端审核列表--全部，状态进行校验"+res);
	}
	
	@Test(description="查看web端审核列表--待审核",priority=7)
	public void testSignUpAllWaitList() {
//		String res = StudyProjectBusiness.signUpList(enroll_id, "", "wait");
//		String status = (String)JSONPath.read(res, "$.list[0].status");
//		Assert.assertEquals(status, "wait","查看web端审核列表--全部，名称进行校验"+res);
	}
	@Test(description="批量拒绝报名审批",priority=8)
	public void testApproveSignUpBatchReject() {
//		String res = StudyProjectBusiness.batchApproveSignUp(enroll_id, "reject");
//		String result = (String)JSONPath.read(res, "$.result");
//		Assert.assertEquals(result, "true","批量拒绝报名审批"+res);
	}
	
	
	@Test(description="批量拒绝后查看web端审核列表--已拒绝",priority=9)
	public void testSignUpRejectList() {
//		String res = StudyProjectBusiness.signUpList(enroll_id, "", "reject");
//		String status = (String)JSONPath.read(res, "$.list[0].status");
//		Assert.assertEquals(status, "reject","查看web端审核列表--已拒绝，名称进行校验"+res);
	}
	

	@Test(description="批量拒绝后 ，查看课程详情，状态应为报名未通过",priority=10)
	public void testQueryStudyCourseBatchRejectInfo() {
//		String res = MySelfStudyBusiness.queryStudyCourseInfo(course_id);
//		String enroll_status = (String)JSONPath.read(res, "$.enroll_status");
//		Assert.assertEquals(enroll_status, "reject","批量拒绝后 ，查看课程详情，状态应为报名未通过"+res);
	}
	
	@Test(description="批量同意报名审批",priority=11)
	public void testApproveSignUBatchPpass() {
//		String res = StudyProjectBusiness.batchApproveSignUp(enroll_id, "pass");
//		String result = (String)JSONPath.read(res, "$.result");
//		Assert.assertEquals(result, "true","批量同意报名审批"+res);
	}
	
	@Test(description="批量同意后，查看web端审核列表--已通过",priority=12)
	public void testSignUpAllBatchPassList() {
//		String res = StudyProjectBusiness.signUpList(enroll_id, "", "pass");
//		String status = (String)JSONPath.read(res, "$.list[0].status");
//		Assert.assertEquals(status, "pass","查看web端审核列表--已通过，名称进行校验"+res);
	}
	
	@Test(description="批量拒绝后 ，查看课程详情，状态应为报名未通过",priority=13)
	public void testQueryStudyCoursePassInfo() {
//		String res = MySelfStudyBusiness.queryStudyCourseInfo(course_id);
//		String enroll_status = (String)JSONPath.read(res, "$.enroll_status");
//		Assert.assertEquals(enroll_status, "pass","批量拒绝后 ，查看课程详情，状态应为报名未通过"+res);
	}
	
	@Test(description="单个拒绝报名审批",priority=14)
	public void testApproveSignUpReject() {
//		String res = StudyProjectBusiness.approveSignUp(enroll_id, "reject");
//		String result = (String)JSONPath.read(res, "$.result");
//		Assert.assertEquals(result, "true","单个拒绝报名审批"+res);
	}
	
	@Test(description="查看web端审核列表--单个已拒绝",priority=15)
	public void testSignUpAllRejectList() {
//		String res = StudyProjectBusiness.signUpList(enroll_id, "", "reject");
//		String status = (String)JSONPath.read(res, "$.list[0].status");
//		Assert.assertEquals(status, "reject","查看web端审核列表--单个已拒绝，名称进行校验"+res);
	}
	@Test(description="单个同意报名审批",priority=16)
	public void testApproveSignUPpass() {
//		String res = StudyProjectBusiness.approveSignUp(enroll_id, "pass");
//		String result = (String)JSONPath.read(res, "$.result");
//		Assert.assertEquals(result, "true","单个同意报名审批"+res);
	}
	
	@Test(description="查看web端审核列表--单个已通过",priority=17)
	public void testSignUpAllPassList() {
//		String res = StudyProjectBusiness.signUpList(enroll_id, "", "pass");
//		String status = (String)JSONPath.read(res, "$.list[0].status");
//		Assert.assertEquals(status, "pass","查看web端审核列表--单个已通过，名称进行校验"+res);
	}
	@Test(description="开始学习",priority=18)
	public void testStartStudy() {
//		String res = MySelfStudyBusiness.startStudy(course_id);
//		String result = (String)JSONPath.read(res, "$.result");
//		Assert.assertEquals(result, "true","学习项目，自学后，在课程页开始学习"+res);
	}

	String auto_name = "AutoSignUpStudyProject"+CommonData.getStringRandom(5);
	String auto_course_id = "";
	String auto_enroll_id = "";
	String auto_study_id = "";
	@Test(description="新增自动审批的学习项目",priority=19)
	public void testAddStudyProjectAutoSingUp() {
//		String res = StudyProjectNewBusinesss.addStudyProjectSingUp(auto_name, PaperBusiness.getIdByKeyword(BaseBusiness.pass_paper_name), "1", 
//				"true", "5", "0", "0", "automatic_audit", "true", "3");
//		auto_study_id = (String) JSONPath.read(res, "$.id");
//		auto_course_id = (String)JSONPath.read(res, "$.course_id");
//		String id = (String)JSONPath.read(res, "$.id");
//		StudyProjectBusiness.updateStatus(auto_study_id, "normal");
//		Assert.assertNotNull(id, "新增自动审批学习项目："+res);
	}
	
	@Test(description="查询自动审批的课程详情",priority=20)
	public void testQueryStudyAutoCourseInfo() {
//		String res = MySelfStudyBusiness.queryStudyCourseInfo(auto_course_id);
//		auto_enroll_id = (String)JSONPath.read(res, "$.enroll_id");
//		String enroll_status = (String)JSONPath.read(res, "$.enroll_status");
//		int limit_count = (int)JSONPath.read(res, "$.limit_count");
//		Assert.assertEquals(limit_count, 3,"报名前查看学习项目详情页,报名限制人数应为3"+res);
//		Assert.assertEquals(enroll_status, "limited","报名前查看学习项目详情页,enroll_status字段进行校验"+res);
	}
	@Test(description="对学习项目进行报名,自动审批",priority=21)
	public void testAutoSignUp() {
//		String res = StudyProjectBusiness.signUp(auto_enroll_id);
//		String result = (String)JSONPath.read(res, "$.result");
//		Assert.assertEquals(result, "true","对学习项目进行报名,自动审批"+res);
	}
	@Test(description="自动审批后 ，查看课程详情，状态应为报名通过",priority=22)
	public void testQueryStudyCourseRejectInfo() {
//		String res = MySelfStudyBusiness.queryStudyCourseInfo(auto_course_id);
//		String enroll_status = (String)JSONPath.read(res, "$.enroll_status");
//		Assert.assertEquals(enroll_status, "pass","自动审批后 ，查看课程详情，状态应为报名通过"+res);
	}
	
	@Test(description="自动审批过后，再对自动审批过的员工进行拒绝操作",priority=23)
	public void testAutoApproveSignUpReject() {
//		String res = StudyProjectBusiness.approveSignUp(auto_enroll_id, "reject");
//		String result = (String)JSONPath.read(res, "$.result");
//		Assert.assertEquals(result, "true","自动审批过后，再对自动审批过的员工进行拒绝操作"+res);
	}
	
	@Test(description="自动审批后 ，再对自动审批过的员工进行拒绝操作，查看课程详情，状态应为未通过",priority=24)
	public void testQueryStudyAutoCourseRejectInfo() {
//		String res = MySelfStudyBusiness.queryStudyCourseInfo(auto_course_id);
//		String enroll_status = (String)JSONPath.read(res, "$.enroll_status");
//		Assert.assertEquals(enroll_status, "reject","自动审批后 ，再对自动审批过的员工进行拒绝操作，查看课程详情，状态应为未通过"+res);
	}
	
	@Test(description="新增手动审批的学习项目，报名成功后，删除学习项目，在移动端审批管理的报名审批个数也应递减",priority=25)
	public void testApprovalTotalCount() {
		
	}
	
	@Test(priority = 26)
	public void end() {
//		String res = StudyProjectBusiness.deleteStudyProject(study_id);
//		StudyProjectBusiness.deleteStudyProject(auto_study_id);
//		String deleted = (String) JSONPath.read(res, "$.deleted");
//		Assert.assertEquals(deleted, "true", "删除学习项目,实际返回结果：" + res);
	}
	
}
