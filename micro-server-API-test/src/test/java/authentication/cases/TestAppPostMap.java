package authentication.cases;

import cn.kxy.authentication.business.*;
import cn.kxy.base.business.BaseBusiness;
import cn.kxy.course.resources.bussiness.ArticleBusiness;
import cn.kxy.course.resources.bussiness.CourseBusiness;
import cn.kxy.examination.business.PaperBusiness;
import cn.kxy.my.business.AppMybusiness;
import init.cases.InitStudyAuthCourse;
import com.alibaba.fastjson.JSONPath;
import com.lazy.common.utils.CommonData;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestAppPostMap extends InitStudyAuthCourse{

	String post_map_id = "";

	String cert_name_01 = "COO_CM" + CommonData.getStringRandom(5);
	String cert_name_02 = "COO_CM" + CommonData.getStringRandom(5);
	String post_map_name = "MapPost" + CommonData.getStringRandom(5);
	String post_map_edit_name = "Post certification of financial" + CommonData.getStringRandom(5);
	String des = "this is des";
	String id = "";
	String post_name1 = "Chief Operating Officer" + CommonData.getStringRandom(5);
	String post_name2 = "Chief Operating Officer" + CommonData.getStringRandom(5);
	String qualification_id1 = "";
	String qualification_id2 = "";

	String courseId_one = "";
	String articleId_one = "";
	String resourcesId_one = "";
	String operationId_one = "";
	String examId_one = "";

	String courseId_two = "";
	String articleId_two = "";
	String resourcesId_two = "";
	String operationId_two = "";
	String examId_two = "";

	@Test(description = "新增第一个证书",priority = 1)
	public void initAppPostMap() {
		CertificateBusiness.creatCertificate(cert_name_01, "kxyTest0", "Sinpoes", "true", "chinese", "2");
	}
	
	@Test(description = "新增第一个证书",priority = 2)
	public void testCreatCertificate() {
		CertificateBusiness.creatCertificate(cert_name_02, "kxyTest0", "Sinpoes", "true", "chinese", "2");
	}
	
	@Test(description = "新增第一个岗位认证",priority = 3)																								
	public void testAddFirstPostAuthentication() {
		String res1 = PostAuthenticationBusiness.addPostAuthentication(post_name1, des,
				CertificateBusiness.getIdByKeyword(cert_name_01), "0",
				ArticleBusiness.getIdByKeyword(articlename),
				CourseBusiness.getIdByPage(course_name), "sc", "This is a sc desc", "exam", "120",
				PaperBusiness.getIdByKeyword(BaseBusiness.pass_paper_name), BaseBusiness.pass_paper_name);
		String msg1 = (String) JSONPath.read(res1, "$.msg");
		Assert.assertEquals(msg1, "新增计划成功！", "添加岗位认证" + res1);
	}
	
	@Test(description = "新增第二个岗位认证",priority = 4)
	public void testAddSecondPostAuthentication() {
		
		String res2 = PostAuthenticationBusiness.addPostAuthentication(post_name2, des,
				CertificateBusiness.getIdByKeyword(cert_name_02), "0",
				ArticleBusiness.getIdByKeyword(articlename),
				CourseBusiness.getIdByPage(course_name), "sc", "This is a sc desc", "exam", "120",
				PaperBusiness.getIdByKeyword(BaseBusiness.pass_paper_name), BaseBusiness.pass_paper_name);
		String msg = (String) JSONPath.read(res2, "$.msg");
		Assert.assertEquals(msg, "新增计划成功！", "添加岗位认证" + res2);
	}
	
	@Test(description = "获取第一个岗位认证的id",priority = 5)
	public void testGetFirstPostId() {
		String queryList1 = PostAuthenticationBusiness.queryList(post_name1, "0");
		qualification_id1 = (String) JSONPath.read(queryList1, "$.list[0].id");
		
	}
	
	@Test(description = "获取第二个岗位认证的id",priority= 6)
	public void testGetSecondPostId() {
		String queryList2 = PostAuthenticationBusiness.queryList(post_name2, "0");
		qualification_id2 = (String) JSONPath.read(queryList2, "$.list[0].id");
		
	}
	
	@Test(description = "新增岗位地图",priority= 7)
	public void testAddPostMap() {
		String res = PostMapBusiness.addPostMap(post_map_name,"part", des, qualification_id1, qualification_id2,"STARRY_SKY_SERIES");
		id = (String) JSONPath.read(res, "$.id");
		PostMapBusiness.editPostMap(id, qualification_id1, qualification_id2, post_map_edit_name);
		Assert.assertTrue(id != null, "新增岗位地图，实际返回结果：" + res);
	}
	
	@Test(description = "获取岗位地图的id",priority= 8)
	public void testPostMapsList() {
		String list_res = PostMapBusiness.queryPostMapsList(post_map_edit_name, "");
		post_map_id = (String) JSONPath.read(list_res, "$.list[0].id");
	}

	@Test(description = "查看APP端未进行岗位地图认证的人数", priority = 9)
	public void testGetPostMapNotStartedCount() {
		String res = AppPostMapBusiness.getPostMapNotStartedCount();
		int total = (int) JSONPath.read(res, "$.not_started_count");
		Assert.assertTrue(total >= 0, "查看APP端未进行岗位地图认证的人数，实际返回结果：" + res);
	}

	@Test(description = "查看APP端岗位地图列表", priority = 10)
	public void testQueryList() {
		String res = AppPostMapBusiness.queryList();
		String name = (String) JSONPath.read(res, "$.list[0].name");
		int finished_progress = (int) JSONPath.read(res, "$.list[0].finished_progress");
		Assert.assertEquals(name, post_map_edit_name, "查看APP端岗位地图列表，实际返回结果：" + res);
		Assert.assertEquals(finished_progress, 0, "查看APP端岗位地图列表，实际返回结果：" + res);
	}

	@Test(description = "查看APP端岗位地图描述", priority = 11)
	public void testQueryIntroduction() {
		String res = AppPostMapBusiness.queryIntroduction(post_map_id);
		String name = (String) JSONPath.read(res, "$.name");
		String introduction = (String) JSONPath.read(res, "$.introduction");
		Assert.assertEquals(introduction, "this is des", "查看APP端岗位地图描述，实际返回结果：" + res);
		Assert.assertEquals(name, post_map_edit_name, "查看APP端岗位地图描述，实际返回结果：" + res);
	}

	@Test(description = "查看APP端岗位地图概要信息", priority = 12)
	public void testQueryListInfo() {
		String res = AppPostMapBusiness.queryListInfo(post_map_id);
		String checkpoint_name1 = (String) JSONPath.read(res, "$.checkpoint_list[0].checkpoint_name");
		String checkpoint_name2 = (String) JSONPath.read(res, "$.checkpoint_list[1].checkpoint_name");
		int unlock_count = (int) JSONPath.read(res, "$.checkpoint_list[0].unlock_count");
		int checkpoint_progress = (int) JSONPath.read(res, "$.checkpoint_list[0].checkpoint_progress");

		Assert.assertEquals(checkpoint_progress, 0, "查看APP端岗位地图概要信息，实际返回结果：" + res);
		Assert.assertEquals(unlock_count, 0, "查看APP端岗位地图概要信息，实际返回结果：" + res);
		Assert.assertEquals(checkpoint_name1, post_name1, "查看APP端岗位地图概要信息，实际返回结果：" + res);
		Assert.assertEquals(checkpoint_name2, post_name2, "查看APP端岗位地图概要信息，实际返回结果：" + res);
	}

	@Test(description = "APP端未进行岗位地图认证，查看弹窗信息", priority = 13)
	public void testpopWindow() {
		String res = AppPostMapBusiness.popWindow(post_map_id);
		int get_score = (int) JSONPath.read(res, "$.get_score");
		Assert.assertEquals(get_score, 0, "APP端未进行岗位地图认证，查看弹窗信息，实际返回结果：" + res);
	}

	@Test(description = "APP端未进行岗位地图认证，获取额外奖励", priority = 14)
	public void testGetAward() {
		String res = AppPostMapBusiness.getAward(post_map_id);
		int get_score = (int) JSONPath.read(res, "$.get_score");
		int total_score = (int) JSONPath.read(res, "$.total_score");
		Assert.assertEquals(get_score, 0, "APP端未进行岗位地图认证，获取额外奖励，实际返回结果：" + res);
		Assert.assertEquals(total_score, 0, "APP端未进行岗位地图认证，获取额外奖励，实际返回结果：" + res);
	}

	@Test(description = "APP端查看岗位地图第一个岗位的详情,APP端保存文章和课程的进度", priority = 15)
	public void testQueryAppPostItems() {
		String res = AppPostMapBusiness.queryAppPostItems(qualification_id1);
		AppPostAuthenticationBusiness.loadResource(qualification_id1);
		articleId_one = (String) JSONPath.read(res, "$.stage_list[0].course_mapping_list[0].id");
		courseId_one = (String) JSONPath.read(res, "$.stage_list[0].course_mapping_list[1].id");
		operationId_one = (String) JSONPath.read(res, "$.stage_list[0].course_mapping_list[3].id");
		examId_one = (String) JSONPath.read(res, "$.stage_list[0].course_mapping_list[2].id");
		String res0 = AppPostAuthenticationBusiness.queryPostAuthCourseInfo(courseId_one);
		resourcesId_one = (String) JSONPath.read(res0, "$.resources[0].id");
		String res1 = AppPostAuthenticationBusiness.saveProgress("100", qualification_id1, articleId_one,
				articleId_one);
		int course_progress = (int) JSONPath.read(res1, "$.course_progress");
		Assert.assertEquals(course_progress, 100, "APP端查看岗位地图第一个岗位的详情,APP端保存文章进度，实际返回结果：" + res1);
	}

	@Test(description = "APP端对第一个岗位地图进行认证完成25%时，APP端查看岗位地图列表进度", priority = 16)
	public void testPostMapProgress() {
		String res = AppPostMapBusiness.queryList();
		int finished_progress = (int) JSONPath.read(res, "$.list[0].finished_progress");
		Assert.assertEquals(finished_progress, 12, "APP端对第一个岗位地图进行认证完成25%时，APP端查看岗位地图列表进度，实际返回结果：" + res);
	}

	@Test(description = "APP端对第一个岗位地图进行认证完成25%时，App端岗位地图详情页的进度校验", priority = 17)
	public void testQueryListInfoProgress() {
		String res = AppPostMapBusiness.queryListInfo(post_map_id);
		int total_progress = (int) JSONPath.read(res, "$.total_progress");
		int unlock_count = (int) JSONPath.read(res, "$.checkpoint_list[0].unlock_count");
		int unlock_count_2 = (int) JSONPath.read(res, "$.checkpoint_list[1].unlock_count");
		int checkpoint_progress = (int) JSONPath.read(res, "$.checkpoint_list[0].checkpoint_progress");
		Assert.assertEquals(checkpoint_progress, 25, "APP端对第一个岗位地图进行认证完成25%时，App端岗位地图详情页的第一个岗位地图进度校验，，实际返回结果：" + res);
		Assert.assertEquals(unlock_count, 1, "APP端对第一个岗位地图进行认证完成25%时，App端岗位地图详情页的进度校验，实际返回结果：" + res);
		Assert.assertEquals(total_progress, 12, "APP端对第一个岗位地图进行认证完成25%时，App端岗位地图详情页的进度校验，总进度，实际返回结果：" + res);
		Assert.assertEquals(unlock_count_2, 0, "APP端对第一个岗位地图进行认证完成25%时，App端岗位地图详情页的进度校验--第二个认证未解锁，实际返回结果：" + res);
	}

	@Test(description = "第一个岗位地图进行认证完成25%时，查询岗位地图列表--参与员工数进行校验", priority = 18)
	public void testQueryPostMapsList() {
		String res = PostMapBusiness.queryPostMapsList(post_map_edit_name, "");
		int user_count = (int) JSONPath.read(res, "$.list[0].user_count");
		Assert.assertEquals(user_count, 1, "第一个岗位地图进行认证完成25%时，查询岗位地图列表--全部,(参与员工数进行校验)" + res);
	}

	@Test(description = "第一个岗位地图进行认证完成25%时，查看岗位地图统计数据", priority = 19)
	public void testMonitorPostMap() {
		String res = PostMapBusiness.monitorPostMap(id, "");
		String name = (String) JSONPath.read(res, "$.list[0].name");
		int complete_checkpoint_count = (int) JSONPath.read(res, "$.list[0].complete_checkpoint_count");
		int progress = (int) JSONPath.read(res, "$.list[0].progress");
		int checkpoint_total_count = (int) JSONPath.read(res, "$.list[0].checkpoint_total_count");
		int certificate_count = (int) JSONPath.read(res, "$.list[0].certificate_count");
		int score = (int) JSONPath.read(res, "$.list[0].score");

		Assert.assertEquals(score, 0, "第一个岗位地图进行认证完成25%时，查看岗位地图统计数据--分数，实际返回结果：" + res);
		Assert.assertEquals(certificate_count, 0, "第一个岗位地图进行认证完成25%时，查看岗位地图统计数据--证书个数，实际返回结果：" + res);
		Assert.assertEquals(checkpoint_total_count, 2, "第一个岗位地图进行认证完成25%时，查看岗位地图统计数据--总关卡，实际返回结果：" + res);
		Assert.assertEquals(complete_checkpoint_count, 1, "第一个岗位地图进行认证完成25%时，查看岗位地图统计数据--当前关卡，实际返回结果：" + res);
		Assert.assertEquals(progress, 12, "第一个岗位地图进行认证完成25%时，查看岗位地图统计数据--进度校验，实际返回结果：" + res);
//		Assert.assertEquals(name, UserBusiness.getUsername(), "第一个岗位地图进行认证完成25%时，查看岗位地图统计数据--名称校验，实际返回结果：" + res);
	}

	@Test(description = "APP端对第一个岗位地图进行认证,考试成功、实操考核审核通过并通过认证", priority = 20)
	public void testFinishFirstPostMap() {
		AppPostAuthenticationBusiness.saveProgress("100", qualification_id1, courseId_one, resourcesId_one);
		AppPostAuthenticationBusiness.saveOperation(operationId_one, "1", "I do not know");
		AppMybusiness.approvePostAuth(AppMybusiness.getAuthApproveInfoId(operationId_one), "Agree", "3");
		AppPostAuthenticationBusiness.queryExamInfo(examId_one);
		String res = AppPostAuthenticationBusiness.submitPassExam(examId_one);
		String msg = (String) JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "提交试卷成功!", "APP端对第一个岗位地图进行认证,考试成功、实操考核审核通过并通过认证，实际返回结果：" + res);
	}
	
	@Test(description = "第一个岗位地图进行认证完成时，查看岗位地图统计数据", priority = 21)
	public void testMonitorFinishFirstPostMap() {
		String res = PostMapBusiness.monitorPostMap(id, "");
		int progress = (int) JSONPath.read(res, "$.list[0].progress");
		int checkpoint_total_count = (int) JSONPath.read(res, "$.list[0].checkpoint_total_count");
		Assert.assertNotNull(checkpoint_total_count, "第一个岗位地图进行认证完成时，查看岗位地图统计数据--总关卡，实际返回结果：" + res);
		Assert.assertNotNull(progress,"第一个岗位地图进行认证完成时，查看岗位地图统计数据--进度校验，实际返回结果：" + res);
	}

	@Test(description = "第一个岗位地图进行认证完成时，查看弹窗信息", priority = 22)
	public void testpopFinishFirstPostMapWindow() {
		String res = AppPostMapBusiness.popWindow(post_map_id);
		System.out.println(res);
	}

	@Test(description = "第一个岗位地图进行认证完成时，获取额外奖励", priority = 23)
	public void testFinishFirstPostMapGetAward() {
		String res = AppPostMapBusiness.getAward(post_map_id);
		int get_score = (int) JSONPath.read(res, "$.get_score");
		int total_score = (int) JSONPath.read(res, "$.total_score");
		Assert.assertTrue(get_score>=0,"APP端未进行岗位地图认证，获取额外奖励，实际返回结果："+res);
		Assert.assertTrue(total_score>=0,"APP端未进行岗位地图认证，获取额外奖励，实际返回结果："+res);
		 
	}

	@Test(description = "APP端查看岗位地图第二个岗位的详情,并完成认证", priority = 24)
	public void testQueryAppScecondPostItems() {
		String res = AppPostMapBusiness.queryAppPostItems(qualification_id2);
		AppPostAuthenticationBusiness.loadResource(qualification_id2);
		articleId_two = (String) JSONPath.read(res, "$.stage_list[0].course_mapping_list[0].id");
		courseId_two = (String) JSONPath.read(res, "$.stage_list[0].course_mapping_list[1].id");
		examId_two = (String) JSONPath.read(res, "$.stage_list[0].course_mapping_list[2].id");
		operationId_two = (String) JSONPath.read(res, "$.stage_list[0].course_mapping_list[3].id");
		String res0 = AppPostAuthenticationBusiness.queryPostAuthCourseInfo(courseId_two);
		resourcesId_two = (String) JSONPath.read(res0, "$.resources[0].id");
		AppPostAuthenticationBusiness.saveProgress("100", qualification_id2, articleId_two, articleId_two);
		AppPostAuthenticationBusiness.saveProgress("100", qualification_id2, courseId_two, resourcesId_two);
		AppPostAuthenticationBusiness.saveOperation(operationId_two, "1", "I do not know");
		AppMybusiness.approvePostAuth(AppMybusiness.getAuthApproveInfoId(operationId_two), "Agree", "3");
		AppPostAuthenticationBusiness.queryExamInfo(examId_two);
		String res2 = AppPostAuthenticationBusiness.submitPassExam(examId_two);
		String msg = (String) JSONPath.read(res2, "$.msg");
		Assert.assertEquals(msg, "提交试卷成功!", "APP端对第一个岗位地图进行认证,考试成功、实操考核审核通过并通过认证，实际返回结果：" + res2);
	}

	@Test(description = "岗位地图进行认证完成时，查看弹窗信息", priority = 25)
	public void testpopFinishAllPostMapWindow() {
		String res = AppPostMapBusiness.popWindow(post_map_id);
		System.out.println("岗位地图进行认证完成时，查看弹窗信息:"+res);
//		Assert.assertTrue(res.contains(""), "岗位地图进行认证完成时，查看弹窗信息"+res);
	}

	@Test(description = "第一个岗位地图全部认证完成时，查看岗位地图统计数据", priority = 26)
	public void testMonitorFinishAllPostMap() {
		String res = PostMapBusiness.monitorPostMap(id, "");
		int progress = (int) JSONPath.read(res, "$.list[0].progress");
		Assert.assertNotNull(progress, "第一个岗位地图进行认证完成时，查看岗位地图统计数据--进度校验，实际返回结果：" + res);
	}
	@Test(description="岗位地图认证完后,删除岗位地图",priority=27)
	public void testDeleteMap() {
		String res = PostMapBusiness.deleteMap(post_map_id);
		String msg = (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "删除成功","删除岗位地图"+res);
	}
	@Test(description="岗位地图认证完后，删除岗位认证",priority=28)
	public void testDeleteAuthentication() {
		PostAuthenticationBusiness.deleteAuthentication(qualification_id1);
		String res = PostAuthenticationBusiness.deleteAuthentication(qualification_id2);
		String msg = (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "能力认证删除成功","删除岗位地图"+res);
	}

	String id01 = "";
	int status ;
	@Test(description = "获取第一个证书的id",priority = 29)
	public void testGetFirstId() {
		String res = CertificateBusiness.queryCertificateList(cert_name_01, "");
		id01 = (String)JSONPath.read(res, "$.list[0].id");
		status = (int)JSONPath.read(res, "$.list[0].status");
	}
	
	@Test(description = "停用证书",priority = 30)
	public void testmodifyCertificateStatus() {
		if (status ==1) {
			String res = CertificateBusiness.modifyCertificateStatus(id01);
			Assert.assertTrue(res.contains("修改证书状态成功！"),""+res);
		}
	}
	
	@Test(description = "删除第一个证书",priority = 31)
	public void testDeleteFirstCret() {
		String res = CertificateBusiness.deleteCret(id01);
		String msg = (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "删除证书成功！","删除第一个证书"+res);
	}
	String id02 = "";
	int status2 ;
	@Test(description = "获取第二个证书的id",priority = 32)
	public void testGetSecondId() {
		String res = CertificateBusiness.queryCertificateList(cert_name_02, "");
		id02 = (String)JSONPath.read(res, "$.list[0].id");
		status2 = (int)JSONPath.read(res, "$.list[0].status");
		
	}
	@Test(description = "停用证书",priority = 33)
	public void testmodifyCertificateStatus1() {
		if (status2 ==1) {
			String res = CertificateBusiness.modifyCertificateStatus(id02);
			Assert.assertTrue(res.contains("修改证书状态成功！"),""+res);
		}
	}
	@Test(description = "删除第二个证书",priority = 34)
	public void testDeleteSecondCret() {
		String res = CertificateBusiness.deleteCret(id02);
		String msg = (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "删除证书成功！","删除第二个证书"+res);
	}
}
