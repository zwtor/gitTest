package authentication.cases;

import cn.kxy.authentication.business.AppPostAuthenticationBusiness;
import cn.kxy.authentication.business.CertificateBusiness;
import cn.kxy.authentication.business.PostAuthenticationBusiness;
import cn.kxy.base.business.BaseBusiness;
import cn.kxy.course.resources.bussiness.ArticleBusiness;
import cn.kxy.course.resources.bussiness.CourseBusiness;
import cn.kxy.examination.business.PaperBusiness;
import cn.kxy.examination.business.PaperExportBusiness;
import cn.kxy.my.business.AppMybusiness;
import cn.kxy.my.business.MyBusiness;
import cn.kxy.setting.bussiness.UserBusiness;
import init.cases.InitStudyAuthCourse;
import com.alibaba.fastjson.JSONPath;
import com.lazy.common.utils.CommonData;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TestAppPostAuthentication extends InitStudyAuthCourse{

	String des = "This is a PostAuthentication Desc";
	String qualificationsId="";
	String post_name = "COO_Post"+CommonData.getStringRandom(5);
	String id = "";
	String user_name = UserBusiness.getUsername();
	String courseId = "";
	String articleId = "";
	String resourcesId = "";
	String operationId = "";
	String examId = "";
	@BeforeClass(description="初始化数据---添加岗位认证")
	public void init() {
		String res2 = CertificateBusiness.queryCertificateList(cert_name, "1");
		int total2 = (int) JSONPath.read(res2, "$.total");
		if (total2 == 0) {
			CertificateBusiness.creatCertificate(cert_name, "kxyTest0", "Sinpoes", "true", "chinese", "2");
		}
		String res = PostAuthenticationBusiness.addPostAuthentication(post_name, des, CertificateBusiness.getIdByKeyword(cert_name), 
				"0", ArticleBusiness.getIdByKeyword(articlename), CourseBusiness.getIdByPage(course_name), 
				"shicao", "This is a sc desc", "exam", "120",PaperBusiness.getIdByKeyword(BaseBusiness.pass_paper_name) ,  BaseBusiness.pass_paper_name);
		String msg = (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "新增计划成功！","添加岗位认证"+res);
	}
	@Test(description="测试APP端岗位认证列表",priority=1)
	public void testQueryAppPostList() {
		String res = AppPostAuthenticationBusiness.queryAppPostList("1");
		id= AppPostAuthenticationBusiness.getAppPostIdByStatus("1");
	
		String title = (String)JSONPath.read(res, "$.list[0].title");
		int progress =(int)JSONPath.read(res, "$.list[0].progress");
		Assert.assertEquals(progress,0,"测试APP端岗位认证列表，实际返回结果："+res );
		Assert.assertEquals(title, post_name,"测试APP端岗位认证列表，实际返回结果："+res);
	}
	@Test(description="查看APP端开始认证页面的岗位认证信息",priority=2)
	public void testQueryStartAuthenticationInfo() {
		String res = AppPostAuthenticationBusiness.queryStartAuthenticationInfo(id);
		String title = (String)JSONPath.read(res, "$.title");
		String summary = (String)JSONPath.read(res, "$.summary");
		String name = (String)JSONPath.read(res, "$.name");
		int progress =(int)JSONPath.read(res, "$.progress");
		
		Assert.assertEquals(name,user_name,"查看APP端开始认证页面的岗位认证信息，实际返回结果："+res );
		Assert.assertEquals(progress,0,"查看APP端开始认证页面的岗位认证信息，实际返回结果："+res );
		Assert.assertEquals(title, post_name,"查看APP端开始认证页面的岗位认证信息，实际返回结果："+res);
		Assert.assertEquals(summary, des,"查看APP端开始认证页面的岗位认证信息，实际返回结果："+res);
		
	}
	@Test(description="查看APP端开始认证页面的状态",priority=3)
	public void testQueryAppPostState() {
		String res = AppPostAuthenticationBusiness.queryAppPostState(id);
		String msg = (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "认证正常!","查看APP端开始认证页面的状态，实际返回结果："+res);
	}

	@Test(description="APP端查看岗位认证详情",priority=4)
	public void testQueryAppPostItems() {
		String res = AppPostAuthenticationBusiness.queryAppPostItems(id);
		String title = (String)JSONPath.read(res, "$.title");
		articleId = (String)JSONPath.read(res, "$.stage_list[0].course_mapping_list[0].id");
		courseId=(String)JSONPath.read(res, "$.stage_list[0].course_mapping_list[1].id");
		examId = (String)JSONPath.read(res, "$.stage_list[0].course_mapping_list[2].id");
		operationId=(String)JSONPath.read(res, "$.stage_list[0].course_mapping_list[3].id");
		
		String name01 = (String)JSONPath.read(res, "$.stage_list[0].course_mapping_list[0].title");
		String name02 = (String)JSONPath.read(res, "$.stage_list[0].course_mapping_list[1].title");
		String name03 = (String)JSONPath.read(res, "$.stage_list[0].course_mapping_list[2].title");
		String name04 = (String)JSONPath.read(res, "$.stage_list[0].course_mapping_list[3].title");
		int status = (int)JSONPath.read(res, "$.status");
		
		String first_type = (String)JSONPath.read(res, "$.stage_list[0].course_mapping_list[0].type");
		String second_type = (String)JSONPath.read(res, "$.stage_list[0].course_mapping_list[1].type");
		String third_type = (String)JSONPath.read(res, "$.stage_list[0].course_mapping_list[2].type");
		String four_type = (String)JSONPath.read(res, "$.stage_list[0].course_mapping_list[3].type");
		
		Assert.assertEquals(first_type, "article", "APP端查看岗位认证详情,学习类型字段校验，实际返回结果：" + res);
		Assert.assertEquals(second_type, "crs", "APP端查看岗位认证详情,学习类型字段校验，实际返回结果：" + res);
		Assert.assertEquals(third_type, "ex", "APP端查看岗位认证详情,学习类型字段校验，实际返回结果：" + res);
		Assert.assertEquals(four_type, "sc", "APP端查看岗位认证详情,学习类型字段校验，实际返回结果：" + res);
		Assert.assertEquals(status,1,"APP端查看岗位认证详情，实际返回结果："+res);
		Assert.assertEquals(name04,"shicao" ,"APP端查看岗位认证详情，实际返回结果："+res);
		Assert.assertEquals(name03,"exam" ,"APP端查看岗位认证详情，实际返回结果："+res);
		Assert.assertEquals(name02,course_name ,"APP端查看岗位认证详情，实际返回结果："+res);
		Assert.assertEquals(name01,articlename ,"APP端查看岗位认证详情，实际返回结果："+res);
		Assert.assertEquals(title, post_name,"APP端查看岗位认证详情，实际返回结果："+res);
		
	}
	@Test(description="加载APP端岗位认证资源",priority=5)
	public void testLoadResource() {
		String res = AppPostAuthenticationBusiness.loadResource(id);
		String status = (String)JSONPath.read(res, "$.status");
		Assert.assertEquals(status, "success","加载APP端岗位认证资源，实际返回结果："+res);
	}
	@Test(description="APP端开始岗位认证 （进度为0时），在web端查看认证人员信息",priority=6)
	public void testQueryAuthenticationPeopleList() {
		String res = PostAuthenticationBusiness.queryAuthenticationPeopleList(id, "", "0", "");
		
		String name = (String)JSONPath.read(res, "$.data.list[0].name");
		int progress = (int)JSONPath.read(res, "$.data.list[0].progress");
		String statusName = (String)JSONPath.read(res, "$.data.list[0].statusName");
		Assert.assertEquals(statusName,"进行中", "APP端开始岗位认证 （进度为0时），在web端查看认证人员信息，实际返回结果："+res);
		Assert.assertEquals(progress,0, "APP端开始岗位认证 （进度为0时），在web端查看认证人员信息，实际返回结果："+res);
		Assert.assertEquals(name,user_name, "APP端开始岗位认证 （进度为0时），在web端查看认证人员信息，实际返回结果："+res);
	}
	
	@Test(description="APP端开始岗位认证 （进度为0时），在web端查看监控数据",priority = 7)
	public void testAppMonitorsQualifications() {
		String res = PostAuthenticationBusiness.monitorsQualifications(id, user_name, "2", "1");
		String status_name = (String)JSONPath.read(res, "$.data.vo_list.list[0].status_name");
		int progress = (int)JSONPath.read(res, "$.data.vo_list.list[0].progress");
		int stage_progress = (int)JSONPath.read(res, "$.data.vo_list.list[0].stage_list[0].stage_progress");
		int study_progress0 = (int)JSONPath.read(res, "$.data.vo_list.list[0].stage_list[0].course_infos[0].study_progress");
		int study_progress1 = (int)JSONPath.read(res, "$.data.vo_list.list[0].stage_list[0].course_infos[1].study_progress");
		int study_progress2 = (int)JSONPath.read(res, "$.data.vo_list.list[0].stage_list[0].course_infos[2].study_progress");
		int study_progress3 = (int)JSONPath.read(res, "$.data.vo_list.list[0].stage_list[0].course_infos[3].study_progress");
	
		Assert.assertEquals(study_progress0, 0,"APP端开始岗位认证 （进度为0时），在web端查看监控数据"+res);
		Assert.assertEquals(study_progress1, 0,"APP端开始岗位认证 （进度为0时），在web端查看监控数据"+res);
		Assert.assertEquals(study_progress2, 0,"APP端开始岗位认证 （进度为0时），在web端查看监控数据"+res);
		Assert.assertEquals(study_progress3, 0,"APP端开始岗位认证 （进度为0时），在web端查看监控数据"+res);
		Assert.assertEquals(stage_progress, 0,"APP端开始岗位认证 （进度为0时），在web端查看监控数据"+res);
		Assert.assertEquals(progress, 0,"APP端开始岗位认证 （进度为0时），在web端查看监控数据"+res);
		Assert.assertEquals(status_name, "进行中","APP端开始岗位认证 （进度为0时），在web端查看监控数据"+res);
	}
	@Test(description="APP端开始岗位认证 （进度为0时），在web端查看学习记录",priority=8)
	public void testQueryStudyRecord() {
		String res = PostAuthenticationBusiness.queryStudyRecord(id);
		String title01  = (String)JSONPath.read(res, "$.data[0].courseInfo[0].title");
		int studyProgress = (int)JSONPath.read(res, "$.data[0].courseInfo[0].studyProgress");
		int studyProgress0 = (int)JSONPath.read(res, "$.data[0].courseInfo[1].studyProgress");
		String title02  = (String)JSONPath.read(res, "$.data[0].courseInfo[1].title");
		
		Assert.assertEquals(title01, articlename,"APP端开始岗位认证 （进度为0时），在web端查看学习记录"+res);
		Assert.assertEquals(title02, course_name,"APP端开始岗位认证 （进度为0时），在web端查看学习记录"+res);
		Assert.assertEquals(studyProgress, 0,"APP端开始岗位认证 （进度为0时），在web端查看学习记录"+res);
		Assert.assertEquals(studyProgress0, 0,"APP端开始岗位认证 （进度为0时），在web端查看学习记录"+res);
	}
	
	@Test(description="保存APP端岗位认证文章的进度",priority=9)
	public void testSaveArticleProgress() {
		String res = AppPostAuthenticationBusiness.saveProgress("100", id, articleId, articleId);
		int course_progress = (int)JSONPath.read(res, "$.course_progress");
		int total_progress = (int)JSONPath.read(res, "$.total_progress");
		Assert.assertEquals(course_progress, 100,"保存APP端岗位认证文章的进度，实际返回结果："+res);
		Assert.assertEquals(total_progress, 25,"保存APP端岗位认证文章的进度，实际返回结果："+res);
	}
	
	@Test(description="查看APP端岗位认证的课程详情(studies)",priority=10)
	public void testQueryPostAuthStudiesCourseInfo() {
		String res = AppPostAuthenticationBusiness.queryPostAuthCourseInfo(id, courseId);
		String title = (String)JSONPath.read(res, "$.title");
		int progress = (int)JSONPath.read(res, "$.progress");
		Assert.assertEquals(progress,0,"查看岗位认证的课程详情(studies),"+res );
		Assert.assertEquals(title,course_name ,"查看岗位认证的课程详情(studies),"+res);
	}
	@Test(description="查看APP端岗位认证的课程详情",priority=11)
	public void testQueryPostAuthCourseoyherInfo() {
		String res = AppPostAuthenticationBusiness.queryPostAuthCourseInfo(courseId);
		resourcesId = (String)JSONPath.read(res, "$.resources[0].id");
		String title = (String)JSONPath.read(res, "$.title");
//		String lecturer_name = (String)JSONPath.read(res, "$.lecturer.lecturer_name");
//		Assert.assertEquals(lecturer_name,outer_name ,"查看岗位认证的课程详情,"+res);
		Assert.assertEquals(title,course_name ,"查看岗位认证的课程详情,"+res);
	}
	
	@Test(description="保存APP端岗位认证的课程进度",priority=12)
	public void testSaveCourseProgress() {
//		String res = AppPostAuthenticationBusiness.saveProgress("100", id, courseId, resourcesId);
//		int course_progress = (int)JSONPath.read(res, "$.course_progress");
//		int total_progress = (int)JSONPath.read(res, "$.total_progress");
//		Assert.assertEquals(course_progress, 100,"保存APP端岗位认证的课程进度，实际返回结果："+res);
//		Assert.assertEquals(total_progress, 50,"保存APP端岗位认证的课程进度，实际返回结果："+res);
	}
	
	@Test(description="APP端对岗位认证的课程进行评论",priority=13)
	public void testAddPostAuJthCourseComment() {
		String res = AppPostAuthenticationBusiness.addPostAuJthCourseComment(courseId, "good");
		String comment  = (String)JSONPath.read(res, "$.comment");
		Assert.assertEquals(comment, "good","APP端对岗位认证的课程进行评论，实际返回结果："+res);
	}
	
	@Test(description="APP端开始岗位认证 进度为一半时，在web端查看认证人员信息",priority=14)
	public void testQueryAuthenticationProgressPeopleList() {
//		String res = PostAuthenticationBusiness.queryAuthenticationPeopleList(id, "", "0", "");
//		
//		String name = (String)JSONPath.read(res, "$.data.list[0].name");
//		String statusName = (String)JSONPath.read(res, "$.data.list[0].statusName");
//		int progress = (int)JSONPath.read(res, "$.data.list[0].progress");
//		Assert.assertEquals(progress,50, "APP端开始岗位认证 进度为一半时，在web端查看认证人员信息，实际返回结果："+res);
//		Assert.assertEquals(statusName,"进行中", "APP端开始岗位认证 进度为一半时，在web端查看认证人员信息，实际返回结果："+res);
//		Assert.assertEquals(name,user_name, "APP端开始岗位认证 进度为一半时，在web端查看认证人员信息，实际返回结果："+res);
	}
	
	@Test(description="APP端开始岗位认证进度为一半时，在web端查看监控数据",priority = 15)
	public void testAppMonitorsRecordQualifications() {
//		String res = PostAuthenticationBusiness.monitorsQualifications(id, user_name, "2", "1");
//		String status_name = (String)JSONPath.read(res, "$.data.vo_list.list[0].status_name");
//		int progress = (int)JSONPath.read(res, "$.data.vo_list.list[0].progress");
//		int stage_progress = (int)JSONPath.read(res, "$.data.vo_list.list[0].stage_list[0].stage_progress");
//		int study_progress0 = (int)JSONPath.read(res, "$.data.vo_list.list[0].stage_list[0].course_infos[0].study_progress");
//		int study_progress1 = (int)JSONPath.read(res, "$.data.vo_list.list[0].stage_list[0].course_infos[1].study_progress");
//		int study_progress2 = (int)JSONPath.read(res, "$.data.vo_list.list[0].stage_list[0].course_infos[2].study_progress");
//		int study_progress3 = (int)JSONPath.read(res, "$.data.vo_list.list[0].stage_list[0].course_infos[3].study_progress");
//	
//		Assert.assertEquals(study_progress0, 100,"APP端开始岗位认证 进度为一半时，在web端查看监控数据"+res);
//		Assert.assertEquals(study_progress1, 100,"APP端开始岗位认证进度为一半时，在web端查看监控数据"+res);
//		Assert.assertEquals(study_progress2, 0,"APP端开始岗位认证进度为一半时，在web端查看监控数据"+res);
//		Assert.assertEquals(study_progress3, 0,"APP端开始岗位认证 进度为一半时，在web端查看监控数据"+res);
//		Assert.assertEquals(stage_progress, 50,"APP端开始岗位认证 进度为一半时，在web端查看监控数据"+res);
//		Assert.assertEquals(progress, 50,"APP端开始岗位认证 进度为一半时，在web端查看监控数据"+res);
//		Assert.assertEquals(status_name, "进行中","APP端开始岗位认证 进度为一半时，在web端查看监控数据"+res);
	}
	
	@Test(description="APP端开始岗位认证进度为一半时 ，在web端查看学习记录",priority=16)
	public void testQueryStudyHaveRecord() {
//		String res = PostAuthenticationBusiness.queryStudyRecord(id);
//		String title01  = (String)JSONPath.read(res, "$.data[0].courseInfo[0].title");
//		int studyProgress = (int)JSONPath.read(res, "$.data[0].courseInfo[0].studyProgress");
//		int studyProgress0 = (int)JSONPath.read(res, "$.data[0].courseInfo[1].studyProgress");
//		String title02  = (String)JSONPath.read(res, "$.data[0].courseInfo[1].title");
//		Assert.assertEquals(title01, articlename,"APP端开始岗位认证进度为一半时，在web端查看学习记录"+res);
//		Assert.assertEquals(title02, course_name,"APP端开始岗位认证进度为一半时，在web端查看学习记录"+res);
//		Assert.assertEquals(studyProgress, 100,"APP端开始岗位认证 进度为一半时，在web端查看学习记录"+res);
//		Assert.assertEquals(studyProgress0, 100,"APP端开始岗位认证进度为一半时，在web端查看学习记录"+res);
	}
	@Test(description="查看APP端开始认证页面的岗位认证信息(进度为一半时)",priority=17)
	public void testQueryStartHalfAuthenticationInfo() {
//		String res = AppPostAuthenticationBusiness.queryStartAuthenticationInfo(id);
//		String title = (String)JSONPath.read(res, "$.title");
//		String summary = (String)JSONPath.read(res, "$.summary");
//		String name = (String)JSONPath.read(res, "$.name");
//		int progress =(int)JSONPath.read(res, "$.progress");
//		
//		Assert.assertEquals(name,user_name,"查看APP端开始认证页面的岗位认证信息（进度为一半时），实际返回结果："+res );
//		Assert.assertEquals(progress,50,"查看APP端开始认证页面的岗位认证信息（进度为一半时），实际返回结果："+res );
//		Assert.assertEquals(title, post_name,"查看APP端开始认证页面的岗位认证信息（进度为一半时），实际返回结果："+res);
//		Assert.assertEquals(summary, des,"查看APP端开始认证页面的岗位认证信息（进度为一半时），实际返回结果："+res);
	}
	@Test(description="测试APP端岗位认证列表（进度为一半时）",priority=18)
	public void testQueryAppHalfPostAuthList() {
//		String res = AppPostAuthenticationBusiness.queryAppPostList("1");
//		String title = (String)JSONPath.read(res, "$.list[0].title");
//		int progress =(int)JSONPath.read(res, "$.list[0].progress");
//		Assert.assertEquals(progress,50,"测试APP端岗位认证列表（进度为一半时），实际返回结果："+res );
//		Assert.assertEquals(title, post_name,"测试APP端岗位认证列表（进度为一半时），实际返回结果："+res);
	}
	
	@Test(description="APP端查看实操考核详情",priority=19)
	public void testQueryOperationInfo() {
		String res = AppPostAuthenticationBusiness.queryOperationInfo(operationId);
		String desp = (String)JSONPath.read(res, "$.data.content");
		Assert.assertEquals(desp, "This is a sc desc","APP端查看实操考核详情,实际返回结果："+res);
	}
	
	@Test(description="APP端保存实操练习",priority=20)
	public void testSaveOperation() {
		String res = AppPostAuthenticationBusiness.saveOperation(operationId, "0", "I do not know");
		String msg = (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "提交成功","APP端保存实操练习，实际返回结果："+res);
	}
	@Test(description="APP端查看保存后的实操考核",priority=21)
	public void testQueryOperationHaveContentInfo() {
		String res = AppPostAuthenticationBusiness.queryOperationInfo(operationId);
		String content = (String)JSONPath.read(res, "$.data.steps[0].content");
		Assert.assertEquals(content, "I do not know","APP端查看实操考核详情,实际返回结果："+res);
	}
	@Test(description="APP端提交实操练习",priority=22) 
	public void testSubmitOperation() {
		String res = AppPostAuthenticationBusiness.saveOperation(operationId, "1", "I do not know");
		String msg = (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "提交成功","APP端保存实操练习，实际返回结果："+res);
	}

	@Test(description="APP端开始岗位认证，提交实操考核后，在web端查看待审核监控数据",priority = 23)
	public void testAppMonitorsOperaRecordQualifications() {
		String res = PostAuthenticationBusiness.monitorsQualifications(id, "", "3", "");
		int operation_status = (int)JSONPath.read(res, "$.data.vo_list.list[0].stage_list[0].course_infos[3].operation_status");
		Assert.assertEquals(operation_status, 1,"APP端开始岗位认证，提交实操考核后，在web端查看监控数据"+res);
	}
	
	@Test(description="APP端开始岗位认证，提交实操考核后，在web端查看认证人员信息",priority=24)
	public void testQueryAuthenticationOperaPeopleList() {
//		String res = PostAuthenticationBusiness.queryAuthenticationPeopleList(id, "", "0", "");
//		String name = (String)JSONPath.read(res, "$.data.list[0].name");
//		String statusName = (String)JSONPath.read(res, "$.data.list[0].statusName");
//		int progress = (int)JSONPath.read(res, "$.data.list[0].progress");
//		Assert.assertEquals(progress,50, "APP端开始岗位认证，提交实操考核后，在web端查看认证人员信息，实际返回结果："+res);
//		Assert.assertEquals(statusName,"待审核", "APP端开始岗位认证 ，提交实操考核后，在web端查看认证人员信息，实际返回结果："+res);
//		Assert.assertEquals(name,user_name, "APP端开始岗位认证，提交实操考核后，在web端查看认证人员信息，实际返回结果："+res);
	}
	
	@Test(description="APP端提交实操考核后，查看岗位认证，实操考核的状态",priority=25)
	public void testQueryAppPorgressPostItems() {
		String res = AppPostAuthenticationBusiness.queryAppPostItems(id);
		int status = (int)JSONPath.read(res, "$.stage_list[0].course_mapping_list[3].status");
		Assert.assertEquals(status,1,"APP端提交实操考核后，查看岗位认证，实操考核的状态，实际返回结果："+res);
	}
	@Test(description="APP端驳回实操考核",priority=26)
	public void testDisapprovePostAuth() {
		String res = AppMybusiness.approvePostAuth(AppMybusiness.getAuthApproveInfoId(operationId), "diss", "2");
		String msg = (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "success","APP端驳回实操考核,实际返回结果："+res);
	}
	@Test(description="APP端开始岗位认证，提交实操考核被驳回后，在web端查看待审核监控数据",priority = 27)
	public void testAppMonitorsOperaDissQualifications() {
		String res = PostAuthenticationBusiness.monitorsQualifications(id, "", "2", "1");
		int status = (int)JSONPath.read(res, "$.data.vo_list.list[0].stage_list[0].course_infos[3].operation_status");
		Assert.assertEquals(status, 2,"APP端开始岗位认证，提交实操考核被驳回后，在web端查看监控数据"+res);
	}
	
	@Test(description="APP端提交实操考核后，提交实操考核被驳回后，实操考核的状态",priority=28)
	public void testQueryAppPostScDissItems() {
		String res = AppPostAuthenticationBusiness.queryAppPostItems(id);
		int status = (int)JSONPath.read(res, "$.stage_list[0].course_mapping_list[3].status");
		Assert.assertEquals(status,2,"APP端提交实操考核后，提交实操考核被驳回后，实操考核的状态，实际返回结果："+res);
	}
	@Test(description="APP端查看被驳回后的实操考核详情",priority=29)
	public void testQueryOperationDissInfo() {
		String res = AppPostAuthenticationBusiness.queryOperationInfo(operationId);
		String suggest = (String)JSONPath.read(res, "$.data.suggest");
		int status = (int)JSONPath.read(res, "$.data.status");
		Assert.assertEquals(status, 2,"APP端查看被驳回后的实操考核详情,实际返回结果："+res);
		Assert.assertEquals(suggest, "diss","APP端查看被驳回后的实操考核详情,实际返回结果："+res);
	}
	@Test(description="APP端被驳回后重新提交实操练习",priority=30) 
	public void testResubmitOperation() {
		String res = AppPostAuthenticationBusiness.saveOperation(operationId, "1", "I do not know");
		String msg = (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "提交成功","APP端被驳回后重新提交实操练习，实际返回结果："+res);
	}
	
	@Test(description="APP端驳回实操考核后，重新提交并通过审核",priority=31)
	public void testAgreeapprovePostAuth() {
		String res = AppMybusiness.approvePostAuth(AppMybusiness.getAuthApproveInfoId(operationId), "Agree", "3");
		String msg = (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "success","APP端驳回实操考核后，重新提交并通过审核,实际返回结果："+res);
	}
	
	@Test(description="查看APP端开始认证页面的岗位认证信息(进度为75%时)",priority=32)
	public void testQueryStartScAuthenticationInfo() {
//		String res = AppPostAuthenticationBusiness.queryStartAuthenticationInfo(id);
//		int progress =(int)JSONPath.read(res, "$.progress");
//		Assert.assertEquals(progress,75,"查看APP端开始认证页面的岗位认证信息（进度为75%时），实际返回结果："+res );
		
	}
	@Test(description="测试APP端岗位认证列表（进度为75%时）",priority=33)
	public void testQueryAppScPostAuthList() {
//		String res = AppPostAuthenticationBusiness.queryAppPostList("1");
//		int progress =(int)JSONPath.read(res, "$.list[0].progress");
//		Assert.assertEquals(progress,75,"测试APP端岗位认证列表（进度为75%时），实际返回结果："+res );
	}
	
	@Test(description="APP端开始岗位认证 进度为75%时，在web端查看认证人员信息",priority=34)
	public void testQueryAuthScPeopleList() {
//		String res = PostAuthenticationBusiness.queryAuthenticationPeopleList(id, "", "0", "");
//		int progress = (int)JSONPath.read(res, "$.data.list[0].progress");
//		Assert.assertEquals(progress,75, "APP端开始岗位认证 进度为75%时，在web端查看认证人员信息，实际返回结果："+res);
	}
	
	@Test(description="APP端开始岗位认证进度为一半时，在web端查看监控数据",priority = 35)
	public void testAppMonitorsScQualifications() {
//		String res = PostAuthenticationBusiness.monitorsQualifications(id, user_name, "2", "1");
//		String status_name = (String)JSONPath.read(res, "$.data.vo_list.list[0].status_name");
//		int progress = (int)JSONPath.read(res, "$.data.vo_list.list[0].progress");
//		int stage_progress = (int)JSONPath.read(res, "$.data.vo_list.list[0].stage_list[0].stage_progress");
//		int study_progress0 = (int)JSONPath.read(res, "$.data.vo_list.list[0].stage_list[0].course_infos[0].study_progress");
//		int study_progress1 = (int)JSONPath.read(res, "$.data.vo_list.list[0].stage_list[0].course_infos[1].study_progress");
//		int study_progress2 = (int)JSONPath.read(res, "$.data.vo_list.list[0].stage_list[0].course_infos[3].study_progress");
//		int study_progress3 = (int)JSONPath.read(res, "$.data.vo_list.list[0].stage_list[0].course_infos[2].study_progress");
//	
//		Assert.assertEquals(study_progress0, 100,"APP端开始岗位认证 进度为一半时，在web端查看监控数据"+res);
//		Assert.assertEquals(study_progress1, 100,"APP端开始岗位认证进度为一半时，在web端查看监控数据"+res);
//		Assert.assertEquals(study_progress2, 100,"APP端开始岗位认证进度为一半时，在web端查看监控数据"+res);
//		Assert.assertEquals(study_progress3, 0,"APP端开始岗位认证 进度为一半时，在web端查看监控数据"+res);
//		Assert.assertEquals(stage_progress, 75,"APP端开始岗位认证 进度为一半时，在web端查看监控数据"+res);
//		Assert.assertEquals(progress, 75,"APP端开始岗位认证 进度为一半时，在web端查看监控数据"+res);
//		Assert.assertEquals(status_name, "进行中","APP端开始岗位认证 进度为一半时，在web端查看监控数据"+res);
	}
	
	@Test(description="在APP端查看岗位认证考试概要",priority=36)
	public void testQueryExamListInfo() {
		String res = AppPostAuthenticationBusiness.queryExamListInfo(examId);
		int certificate_count  =(int)JSONPath.read(res,"$.certificate_count");
		String exam_title = (String)JSONPath.read(res, "$.title");
		Assert.assertEquals(exam_title, "exam","查看岗位认证考试的概要，实际返回结果："+res);
		Assert.assertEquals(certificate_count, 0,"查看岗位认证考试的概要，实际返回结果："+res);
	}
	@Test(description="在APP端查看岗位认证的考试是否可以进行考试",priority=37)
	public void testCheckIsCanExam() {
		String res = AppPostAuthenticationBusiness.checkIsCanExam(examId);
		String can_exam = (String)JSONPath.read(res, "$.can_exam");
		Assert.assertEquals(can_exam, "true","查看岗位认证的考试是否可以进行考试，实际返回结果："+res);
	}
	
	@Test(description="在APP端查看岗位认证考试详情",priority=38)
	public void testQueryExamInfo() {
		String res = AppPostAuthenticationBusiness.queryExamInfo(examId);
		String exam_title = (String)JSONPath.read(res, "$.title");
		String summary = (String)JSONPath.read(res, "$.summary");
		Assert.assertEquals(summary, "instruction","查看岗位认证考试详情，实际返回结果："+res);
		Assert.assertEquals(exam_title, "exam","查看岗位认证考试详情，实际返回结果："+res);
	}
	@Test(description="在APP端在岗位认证提交考试",priority=39)
	public void testsubmitBlankExam() {
		String res = AppPostAuthenticationBusiness.submitBlankExam(examId);
		String msg = (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "提交试卷成功!","在岗位认证提交考试，实际返回结果："+res);
	}
	
	@Test(description="在APP端在岗位认证查看考试结果",priority=40)
	public void testqueryExamResult() {
		String res = AppPostAuthenticationBusiness.queryExamResult(examId);
		String status = (String)JSONPath.read(res, "$.status");
		String correct_rate = (String)JSONPath.read(res, "$.correct_rate");
		Assert.assertEquals(correct_rate, "0%","在岗位认证查看考试结果:"+res);
		Assert.assertEquals(status, "failed","在岗位认证查看考试结果:"+res);
	}
	
	@Test(description="在APP端岗位认证查看考试的答案解析",priority=41)
	public void testqueryExamanswerAnalysis() {
		String res = AppPostAuthenticationBusiness.queryExamanswer_analysis(examId);
		String title = (String)JSONPath.read(res, "$.title");
		Assert.assertTrue(title.equals("exam"),"在岗位认证查看考试的答案解析，实际返回结果："+res);
	}
	@Test(description="APP端岗位认证中的考试失败后，查看考试的状态为未通过",priority=42)
	public void testQueryAppPostFailExamItems() {
		String res = AppPostAuthenticationBusiness.queryAppPostItems(id);
		String is_pass = (String)JSONPath.read(res, "$.stage_list[0].course_mapping_list[2].is_pass");
		int status = (int)JSONPath.read(res, ".stage_list[0].course_mapping_list[2].exam_status");
		Assert.assertEquals(status,2,"APP端岗位认证中的考试失败后，查看考试的状态为未通过，实际返回结果："+res);
		Assert.assertEquals(is_pass, "false","APP端岗位认证中的考试失败后，查看考试的状态为未通过，实际返回结果："+res);
	}
	
	@Test (description="APP端岗位认证中，进行重考，并考试通过",priority=43)
	public void testSubmitPassExam() {
		String res = AppPostAuthenticationBusiness.submitPassExam(examId);
		String msg = (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "提交试卷成功!","APP端岗位认证中，进行重考，并考试通过，实际返回结果："+res);
	}
	@Test(description="APP端查看已认证的岗位认证列表",priority=44)
	public void testQueryAppPostedList() {
//		String res = AppPostAuthenticationBusiness.queryAppPostList("2");
//		String title = (String)JSONPath.read(res, "$.list[0].title");
//		int progress = (int)JSONPath.read(res, "$.list[0].progress");
//		Assert.assertEquals(progress, 100,"APP端查看已认证的岗位认证列表"+res);
//		Assert.assertNotNull(title,"APP端查看已认证的岗位认证列表"+res);
	}
	
	@Test(description="认证通过后，查看APP端开始认证页面的状态",priority=45)
	public void testQueryAppPostedState() {
		String res = AppPostAuthenticationBusiness.queryAppPostState(id);
		int data = (int)JSONPath.read(res, "$.data");
		Assert.assertNotNull(data,"认证通过后，查看APP端开始认证页面的状态，实际返回结果："+res);
	}
	
	@Test(description="APP端岗位认证通过后，在web端查看认证人员信息",priority=46)
	public void testQueryAuthPassPeopleList() {
		String res = PostAuthenticationBusiness.queryAuthenticationPeopleList(id, "", "0", "");
		int progress = (int)JSONPath.read(res, "$.data.list[0].progress");
		String statusName = (String)JSONPath.read(res, "$.data.list[0].statusName");
		Assert.assertNotNull(statusName,"APP端岗位认证通过后，在web端查看认证人员信息，实际返回结果："+res);
		Assert.assertNotNull(progress,"APP端岗位认证通过后，在web端查看认证人员信息，实际返回结果："+res);
	}
	String enty_id = "";
	@Test(description="APP端岗位认证通过后，在web端查看监控数据",priority = 47)
	public void testAppMonitorsPassQualifications() {
		String res = PostAuthenticationBusiness.monitorsQualifications(id, "", "0", "");
		enty_id = (String)JSONPath.read(res, "$.data.qualification_data.id");
		String  create_name = (String)JSONPath.read(res, "$.data.qualification_data.create_name");
		String title = (String)JSONPath.read(res, "$.data.qualification_data.title");
		Long update_time = (Long)JSONPath.read(res, "$.data.qualification_data.update_time");
		String summary = (String)JSONPath.read(res, "$.data.qualification_data.summary");
		Assert.assertNotNull(summary,"APP端岗位认证通过后，在web端查看监控数据"+res);
		Assert.assertNotNull(update_time,"APP端岗位认证通过后，在web端查看监控数据"+res);
		Assert.assertNotNull(create_name,"APP端岗位认证通过后，在web端查看监控数据"+res);
		Assert.assertNotNull(title,"APP端岗位认证通过后，在web端查看监控数据"+res);
	}
	@Test(description="岗位认证通过后，在web端查看考试结果",priority=48)
	public void testQueryExamResult() {
		String res = PostAuthenticationBusiness.queryExamResult(examId);
		String correct_rate = (String)JSONPath.read(res, "$.correct_rate");
		String status = (String)JSONPath.read(res, "$.status");
		Assert.assertEquals(status, "passed","岗位认证通过后，查看的考试结果，实际返回结果："+res);
		Assert.assertEquals(correct_rate, "100%","岗位认证通过后，查看的考试结果，实际返回结果："+res);
	}
	@Test(description="岗位认证通过后，web端查看考试记录",priority=49)
	public void testQueryExamRecord() {
		String res = PostAuthenticationBusiness.queryExamRecord(id);
		String courseName = (String)JSONPath.read(res, "$.data[0].courseInfo[0].courseName");
		Assert.assertEquals(courseName, "exam","岗位认证通过后，web端查看考试记录，实际返回结果："+res);
	}
	@Test(description="岗位认证通过后，查看我的证书列表是否获取到证书",priority=50)
	public void testGetMyCertificateName() {
		String certname = MyBusiness.getMyCertificateName(cert_name);
		Assert.assertEquals(certname, cert_name,"岗位认证通过后，查看我的证书列表是否获取到证书"+certname);
	}
	
	
	
	String analy_id = "";
	@Test(description = "查看岗位认证的实操分析数据",priority = 51)
	public void testQueryOperationAnalysis() {
		String res = AppPostAuthenticationBusiness.queryOperationAnalysis("qualification", enty_id);
		analy_id = (String)JSONPath.read(res, "$.operations[0].id");
		String already_submit_count = (String)JSONPath.read(res, "$.operations[0].already_submit_count");
		String title = (String)JSONPath.read( res, "$.operations[0].title");
		Assert.assertEquals(title, "shicao","查看岗位认证的实操分析数据"+res);
		Assert.assertEquals(already_submit_count, "1","查看岗位认证的实操分析数据"+res);
	}
	
	@Test(description = "查看岗位认证的作业分析明显_All",priority = 52)
	public void testQueryPostOperAllList() {
		String res = AppPostAuthenticationBusiness.queryPostOperList(analy_id,"","");
		String name = (String)JSONPath.read(res, "$.page_info.list[0].user_name");
		String submit_status = (String)JSONPath.read(res, "$.page_info.list[0].submit_status");
		String approve_status = (String)JSONPath.read(res, "$.page_info.list[0].approve_status");
		Long submit_time = (Long)JSONPath.read(res, "$.page_info.list[0].submit_time");
		Assert.assertNotNull(name, "查看岗位认证的作业分析明显"+res);
		Assert.assertNotNull(submit_time, "查看岗位认证的作业分析明显"+res);
		Assert.assertEquals(submit_status, "submitted","查看岗位认证的作业分析明显"+res);
		Assert.assertEquals(approve_status,"pass", "查看岗位认证的作业分析明显"+res);
	}
	
	@Test(description = "查看岗位认证的作业分析明显_已提交",priority = 53)
	public void testQueryPostOperSubmittedList() {
		String res = AppPostAuthenticationBusiness.queryPostOperList(analy_id,"submitted","");
		String name = (String)JSONPath.read(res, "$.page_info.list[0].user_name");
		String submit_status = (String)JSONPath.read(res, "$.page_info.list[0].submit_status");
		String approve_status = (String)JSONPath.read(res, "$.page_info.list[0].approve_status");
		Long submit_time = (Long)JSONPath.read(res, "$.page_info.list[0].submit_time");
		Assert.assertNotNull(name, "查看岗位认证的作业分析明显"+res);
		Assert.assertNotNull(submit_time, "查看岗位认证的作业分析明显"+res);
		Assert.assertEquals(submit_status, "submitted","查看岗位认证的作业分析明显"+res);
		Assert.assertEquals(approve_status,"pass", "查看岗位认证的作业分析明显"+res);
	}
	
	@Test(description = "查看岗位认证的作业分析明显_未提交",priority = 54)
	public void testQueryPostOperDraftList() {
		String res = AppPostAuthenticationBusiness.queryPostOperList(analy_id,"draft","");
		Assert.assertTrue(res.contains("total"),"查看岗位认证的作业分析明显"+res);
	}
	
	@Test(description = "查看岗位认证的作业分析明显_通过",priority = 55)
	public void testQueryPostOperPassList() {
		String res = AppPostAuthenticationBusiness.queryPostOperList(analy_id,"submitted","pass");
		String name = (String)JSONPath.read(res, "$.page_info.list[0].user_name");
		String submit_status = (String)JSONPath.read(res, "$.page_info.list[0].submit_status");
		String approve_status = (String)JSONPath.read(res, "$.page_info.list[0].approve_status");
		Long submit_time = (Long)JSONPath.read(res, "$.page_info.list[0].submit_time");
		Assert.assertNotNull(name, "查看岗位认证的作业分析明显"+res);
		Assert.assertNotNull(submit_time, "查看岗位认证的作业分析明显"+res);
		Assert.assertEquals(submit_status, "submitted","查看岗位认证的作业分析明显"+res);
		Assert.assertEquals(approve_status,"pass", "查看岗位认证的作业分析明显"+res);
	}
	
	@Test(description = "查看岗位认证的作业分析明显_未通过",priority = 56)
	public void testQueryPostOperRejectList() {
		String res = AppPostAuthenticationBusiness.queryPostOperList(analy_id,"","reject");
		Assert.assertTrue(res.contains("total"),"查看岗位认证的作业分析明显"+res);
	}
	
	@Test(description = "查看岗位认证的作业分析明显_待审批",priority = 57)
	public void testQueryPostOperWaitList() {
		String res = AppPostAuthenticationBusiness.queryPostOperList(analy_id,"","wait");
		Assert.assertTrue(res.contains("total"),"查看岗位认证的作业分析明显"+res);
	}
	
	@Test(description = "清除所有导出数据",priority = 58)
	public void deleteAllRecord() {
		String res = PaperExportBusiness.deleteAllRecord();
		Assert.assertTrue(res.contains("OK"),"清除所有导出数据,实际返回结果："+res);
	}
	
	@Test(description = "导出实操作业分析",priority = 59)
	public void testOperationsUseMonitorsExport() {
		String res = AppPostAuthenticationBusiness.operationsUseMonitorsExport(analy_id);
		Assert.assertTrue(res.contains("export success"),"导出实操作业分析"+res);
	}
	
	@Test(description = "导出实操作业分析后，在下载中心查看",priority = 60)
	public void exportRecordList() {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		String res = PaperExportBusiness.exportRecordList();
		String status = (String)JSONPath.read(res, "$.list[0].status");
		Assert.assertFalse(status=="FAILED", "查看导出的结果:"+res);
	}
	
	@Test(description = "查看岗位认证实操的详情的作业记录",priority = 61)
	public void testQueryOperInfo() {
		String res = AppPostAuthenticationBusiness.queryOperInfo(analy_id);
		String operation_source = (String)JSONPath.read(res, "$.data.operation_source");
		String suggest = (String)JSONPath.read(res, "$.data.suggest");
		String title = (String)JSONPath.read(res, "$.data.title");
		String content = (String)JSONPath.read(res, "$.data.steps[0].content");
		String score_switch = (String)JSONPath.read(res, "$.data.score_setting.score_switch");
		String operation = (String)JSONPath.read(res, "$.data.approval_record[0].operation");
		String operation_1 = (String)JSONPath.read(res, "$.data.approval_record[1].operation");
		
		Assert.assertEquals(operation_source, "qualification","查看岗位认证实操的详情的作业记录"+res);
		Assert.assertEquals(suggest, "Agree","查看岗位认证实操的详情的作业记录"+res);
		Assert.assertEquals(title, "shicao","查看岗位认证实操的详情的作业记录"+res);
		Assert.assertEquals(content, "I do not know","查看岗位认证实操的详情的作业记录"+res);
		Assert.assertEquals(score_switch, "off","查看岗位认证实操的详情的作业记录"+res);
		Assert.assertEquals(operation, "creation","查看岗位认证实操的详情的作业记录"+res);
		Assert.assertEquals(operation_1, "pass","查看岗位认证实操的详情的作业记录"+res);
		
	}
	
	@Test(priority = 62)
	public void end () {
		PostAuthenticationBusiness.deleteAuthentication(id);
	}

}
