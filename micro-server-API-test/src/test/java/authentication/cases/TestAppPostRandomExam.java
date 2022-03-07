package authentication.cases;

import cn.kxy.authentication.business.AppPostAuthenticationBusiness;
import cn.kxy.authentication.business.CertificateBusiness;
import cn.kxy.authentication.business.PostAuthenticationBusiness;
import cn.kxy.base.business.BaseBusiness;
import cn.kxy.examination.business.ExaminationBusines;
import cn.kxy.my.business.AppMybusiness;
import cn.kxy.my.business.MyBusiness;
import cn.kxy.setting.bussiness.UserBusiness;
import cn.lazy.init.cases.InitStudyAuthCourse;
import com.alibaba.fastjson.JSONPath;
import com.lazy.common.utils.CommonData;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TestAppPostRandomExam extends InitStudyAuthCourse {

	String post_name = "CEO_Post" + CommonData.getStringRandom(5);
	String user_name = UserBusiness.getUsername();
	String id = "";
	String operationId = "";
	String examId = "";
	String questionId ="";
	String planId ="";
	@BeforeClass
	public void init () {
		BaseBusiness.addPassExam();
	}
	@Test(description="添加岗位认证,含随机考试，分为2个阶段",priority=1)
	public void testAddPostAuthRandomExam() {
		String res = PostAuthenticationBusiness.addPostAuthRandomExam(post_name, CertificateBusiness.getIdByKeyword(cert_name),
				ExaminationBusines.getIdByKeyword(BaseBusiness.examPassName));
		String msg = (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "新增计划成功！","添加岗位认证含随机考试，分为2个阶段"+res);
	}
	@Test(description="查看APP端开始认证页面的岗位认证信息--含随机考试，分为2个阶段",priority=2)
	public void testQueryAppPostList() {
		String queryList = PostAuthenticationBusiness.queryList(post_name, "0");
		id= (String)JSONPath.read(queryList, "$.list[0].id");
		String res = AppPostAuthenticationBusiness.queryStartAuthenticationInfo(id);
		String title = (String)JSONPath.read(res, "$.title");
		String name = (String)JSONPath.read(res, "$.name");
		int progress =(int)JSONPath.read(res, "$.progress");
		
		Assert.assertEquals(name,user_name,"查看APP端开始认证页面的岗位认证信息--含随机考试，分为2个阶段，实际返回结果："+res );
		Assert.assertEquals(progress,0,"查看APP端开始认证页面的岗位认证信息--含随机考试，分为2个阶段，实际返回结果："+res );
		Assert.assertEquals(title, post_name,"查看APP端开始认证页面的岗位认证信息--含随机考试，分为2个阶段，实际返回结果："+res);
	}
	@Test(description="查看APP端开始认证页面的状态--含随机考试，分为2个阶段",priority=3)
	public void testQueryAppPostState() {
		String res = AppPostAuthenticationBusiness.queryAppPostState(id);
		String msg = (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "认证正常!","查看APP端开始认证页面的状态--含随机考试，分为2个阶段，实际返回结果："+res);
	}

	@Test(description="APP端查看岗位认证详情--含随机考试，分为2个阶段",priority=4)
	public void testQueryAppPostItems() {
		String res = AppPostAuthenticationBusiness.queryAppPostItems(id);
		String title = (String)JSONPath.read(res, "$.title");
		
		examId = (String)JSONPath.read(res, "$.stage_list[0].course_mapping_list[0].id");
		operationId=(String)JSONPath.read(res, "$.stage_list[0].course_mapping_list[1].id");
		
		String exam = (String)JSONPath.read(res, "$.stage_list[0].course_mapping_list[0].name");
		String name02 = (String)JSONPath.read(res, "$.stage_list[0].course_mapping_list[1].title");
		int status = (int)JSONPath.read(res, "$.stage_list[0].course_mapping_list[0].plan_exam_vos.mark_type");
		Assert.assertEquals(status, 3,"查看岗位认证考试详情--含随机考试，分为2个阶段，待阅卷状态实际返回结果："+res);
		Assert.assertEquals(name02,"sc" ,"APP端查看岗位认证详情--含随机考试，分为2个阶段，实际返回结果："+res);
		Assert.assertEquals(exam,"randomexam" ,"APP端查看岗位认证详情--含随机考试，分为2个阶段，实际返回结果："+res);
		Assert.assertEquals(title, post_name,"APP端查看岗位认证详情--含随机考试，分为2个阶段，实际返回结果："+res);
	}
	@Test(description="在APP端查看岗位认证考试详情--含随机考试，分为2个阶段",priority=5)
	public void testQueryExamInfo() {
		String res = AppPostAuthenticationBusiness.queryExamInfo(examId);
		String exam_title = (String)JSONPath.read(res, "$.title");
		Assert.assertEquals(exam_title, "randomexam","查看岗位认证考试详情--含随机考试，分为2个阶段，实际返回结果："+res);
	}
	@Test(description="在APP端在岗位认证提交考试--含随机考试，分为2个阶段",priority=6)
	public void testsubmitBlankExam() {
		String res = AppPostAuthenticationBusiness.submitPassExam(examId);
		String msg = (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "提交试卷成功!","在岗位认证提交考试--含随机考试，分为2个阶段，实际返回结果："+res);
	}
	@Test(description="在web端岗位认证，查看待阅卷的人员列表",priority=7)
	public void testQueryExamMarking() {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		String res = PostAuthenticationBusiness.queryExamMarking("", id);
		String paper_status = (String)JSONPath.read(res, "$.list[0].paper_status");
		String paper_name = (String)JSONPath.read(res, "$.list[0].paper_name");
		String name = (String)JSONPath.read(res, "$.list[0].name");
		planId = (String)JSONPath.read(res, "$.list[0].plan_id");
		Assert.assertEquals(name, user_name,"在web端岗位认证，查看待阅卷的人员列表,实际返回结果："+res);
		Assert.assertEquals(paper_name, "randomexam","在web端岗位认证，查看待阅卷的人员列表,实际返回结果："+res);
		Assert.assertEquals(paper_status, "待阅卷","在web端岗位认证，查看待阅卷的人员列表,实际返回结果："+res);
	}
	@Test(description="在web端岗位认证，查看待阅卷的信息列表",priority=8)
	public void testGetTrainExamPlanPendingList() {
		String res = PostAuthenticationBusiness.getTrainExamPlanPendingList(planId);
		questionId  = (String)JSONPath.read(res, "$.data.paperVo.showQuestionInfo[0].id");
		Assert.assertTrue(res.contains("查询成功"),"在web端对岗位认证的考试进行阅卷后，在web端查看监控数据"+res);
	}
	@Test(description="在web端对岗位认证的考试进行阅卷",priority=9)
	public void testSubmitMarkingPaper() {
		String res = PostAuthenticationBusiness.submitMarkingPaper(planId, questionId, "60");
		String msg = (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "批阅成功","在web端对岗位认证的考试进行阅卷，实际返回结果："+res);
	}
	@Test(description="在web端对岗位认证的考试进行阅卷后，在web端查看监控数据",priority = 10)
	public void testAppMonitorsPassQualifications() {
		String res = PostAuthenticationBusiness.monitorsQualifications(id, user_name, "0", "2");
		Assert.assertTrue(res.contains("查询成功"),"在web端对岗位认证的考试进行阅卷后，在web端查看监控数据"+res);
	}
	@Test(description="保存实操考核并审核通过--含随机考试，分为2个阶段",priority=11)
	public void testSaveOperation() {
		AppPostAuthenticationBusiness.saveOperation(operationId, "1", "I do not know");
		String res = AppMybusiness.approvePostAuth(AppMybusiness.getAuthApproveInfoId(operationId), "Agree", "3");
		String msg = (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "success","保存实操考核并审核通过--含随机考试，分为2个阶段，实际返回结果："+res);
	}
	@Test(description="岗位认证通过后--含随机考试，分为2个阶段，查看我的证书列表是否获取到证书",priority=12)
	public void testgetMyCertificateName() {
		String certname = MyBusiness.getMyCertificateName(cert_name);
		Assert.assertEquals(certname, cert_name,"岗位认证通过后--含随机考试，分为2个阶段，查看我的证书列表是否获取到证书:"+certname);
	}
	
	@Test(description = "导出认证人员的数据",priority = 13)
	public void getExportQualificationUserCode () {
		int code = PostAuthenticationBusiness.getExportQualificationUserCode(id);
		Assert.assertEquals(code, 200,"导出认证人员的数据");
	}
	
	@Test (priority = 14)
	public void deleteAuthentication () {
		PostAuthenticationBusiness.deleteAuthentication(id);
	}
}
