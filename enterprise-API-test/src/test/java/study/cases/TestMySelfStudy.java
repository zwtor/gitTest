package study.cases;

import cn.kxy.authentication.business.CertificateBusiness;
import cn.kxy.base.business.BaseBusiness;
import cn.kxy.course.resources.bussiness.ArticleBusiness;
import cn.kxy.course.resources.bussiness.CourseBusiness;
import cn.kxy.course.resources.bussiness.CourseFrontListInfoBusiness;
import cn.kxy.examination.business.ExaminationTaskBusiness;
import cn.kxy.examination.business.PaperBusiness;
import cn.kxy.investigationresearch.business.QuestionnaireBusiness;
import cn.kxy.lecturer.business.LecturerListBusiness;
import cn.kxy.setting.bussiness.ClassificationBusines;
import cn.kxy.setting.bussiness.UserBusiness;
import cn.kxy.study.business.MySelfStudyBusiness;
import cn.kxy.study.business.StudyProjectBusiness;
import cn.kxy.study.business.StudyProjectNewBusinesss;
import init.cases.InitStudyAuthCourse;
import com.alibaba.fastjson.JSONPath;
import com.lazy.common.utils.CommonData;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test(groups = {"studyProject"})
public class TestMySelfStudy extends InitStudyAuthCourse{

	String self_name = "SelfStudyProject"+CommonData.getStringRandom(5);
	String self_course_name = "self_course"+CommonData.getStringRandom(5);
	String classification_id = ClassificationBusines.getPrimaryId();
	String study_id = "";
	String cour_id = "";
	String user_name = UserBusiness.getUsername();
	String content = "The course is good, suitable for the study at this stage, and the skills involved are very good";
	String courseId="";
	String resourceId = "";
	String exam_id ="";
	String ins_id= "";
	String ques_id ="";
	@Test(description = "新增学习项目",priority =1)
	public void initMyselfStudy () {
		//新增学习项目
		String res = StudyProjectNewBusinesss.addStudyProject(self_name, classification_id,
				PaperBusiness.getIdByKeyword(BaseBusiness.pass_paper_name),
				CertificateBusiness.getIdByKeyword(cert_name),
				CourseBusiness.getIdByPage(course_name),
				QuestionnaireBusiness.getIdByKeyword(BaseBusiness.questionnaireName, "release"));
		String id = (String) JSONPath.read(res, "$.id");
		//新增课程
		System.out.println("this is MySelfStudy module");
		study_id =(String)JSONPath.read(res, "$.course_id");
		Assert.assertNotNull(id, "新增学习项目" + res);
	}
	@Test(description = "新增课程",priority = 2)
	public void addCourse() {
		String cour_res = CourseBusiness.addCourse(self_course_name, "1", "this is desription", LecturerListBusiness.getIdByKeyword(outer_name), 
				ArticleBusiness.getIdByKeyword(""), "1", "3", "", "1", "0", "release");
		String msg = (String)JSONPath.read(cour_res, "$.msg");
		cour_id = (String)JSONPath.read(cour_res, "$.data[0]");
		Assert.assertEquals(msg, "新增课程成功","新增课程到草稿箱实际返回结果："+cour_res);
		
	}
	
	@Test(description = "获取课程的id",priority =3)
	public void getIdByPage() {
//		cour_id=  CourseBusiness.getIdByPage(self_course_name);
	}
	
	@Test(description= "查看课程详情",priority = 4)
	public void queryInfo() {
		CourseFrontListInfoBusiness.queryInfo(cour_id);
	}
	
	String resource_id = "";
	@Test(description = "获取资源的id",priority = 5)
	public void getCourseFirstSourceId() {
		resource_id = CourseFrontListInfoBusiness.getCourseFirstSourceId(cour_id);
	}
	
	@Test(description = "保存课程进度",priority = 6)
	public void saveCourseProgress() {
//		CourseFrontListInfoBusiness.saveCourseProgress(cour_id, resource_id, "60");
//		int progress = (int)JSONPath.read(res, "$.progress");
//		Assert.assertEquals(progress, 60,"保存课程观看进度实际返回结果："+res);
	}
	
	@Test(description="在我的自学列表，查看未完成状态的课程信息",priority=7)
	public void testQueryMySelfCourseList() {
//		String un_res = MySelfStudyBusiness.queryMySelfStudyList(self_course_name, "unfinished");
//		String title = (String)JSONPath.read(un_res, "$.list[0].title");
//		int biz_type = (int)JSONPath.read(un_res, "$.list[0].biz_type");
//		int process = (int)JSONPath.read(un_res, "$.list[0].process");
//		Assert.assertEquals(process, 60,"在我的自学列表，查看未完成状态的课程信息，进度进行校验"+un_res);
//		Assert.assertEquals(biz_type, 0,"在我的自学列表，查看未完成状态的课程信息，自学类型为课程"+un_res);
//		Assert.assertEquals(title, self_course_name,"在我的自学列表，查看未完成状态的课程信息，标题进行校验"+un_res);
		
	}
	
	
	@Test(description="删除课程后，在我的自学查看是否被删除",priority=8)
	public  void testDeleCourse() {
		CourseBusiness.deleteCourse(cour_id);
		String res = MySelfStudyBusiness.queryMySelfStudyList(self_course_name, "");
		int total = (int)JSONPath.read(res, "$.total");
		Assert.assertEquals(total, 0,"删除课程后，在我的自学查看时，不应显示"+res);
	}
	
	@Test(description="学习项目，自学后，在课程页加载学习项目的课程资源",priority=9)
	public void testLoadResources() {
		String res = MySelfStudyBusiness.loadResources(study_id);
		String status = (String)JSONPath.read(res, "$.status");
		Assert.assertEquals(status, "success","学习项目，自学后，在课程页加载学习项目的课程资源"+res);
	}
	
	@Test(description="学习项目，自学后，在课程页，查看其详情",priority=10)
	public void testQueryStudyCourseInfo() {
		String res = MySelfStudyBusiness.queryStudyCourseInfo(study_id);
		int resource_count = (int)JSONPath.read(res, "$.resource_count");
		int study_count = (int)JSONPath.read(res, "$.study_count");
		
		courseId = (String)JSONPath.read(res, "$.stages[0].resources[0].course_id");
		exam_id = (String )JSONPath.read(res, "$.stages[0].resources[1].course_id");
		ins_id = (String)JSONPath.read(res, "$.stages[0].resources[2].course_id");
		ques_id = (String)JSONPath.read(res, "$.stages[0].resources[3].course_id"); 
		Assert.assertEquals(study_count, 0,"学习项目，自学后，在课程页，查看其详情，学习人数校验"+res);
		Assert.assertEquals(resource_count, 4,"学习项目，自学后，在课程页，查看其详情，任务项校验"+res);
	}
	
	@Test(description="学习项目，自学后，在课程页评论",priority=11)
	public void testAddComment() {
		String res = CourseFrontListInfoBusiness.addComment(study_id,content);
		String msg = (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "success","学习项目，自学后，在课程页评论实际返回结果："+res);
	}
	
	@Test(description="学习项目，自学后，在课程页，给学习项目的课程评分",priority=12)
	public void testAddCommentScore() {
		String score = "4";
		CourseFrontListInfoBusiness.addCommentScore(study_id, score);
		String res = CourseFrontListInfoBusiness.queryInfo(study_id);
		int score0= (int)JSONPath.read(res, "$.score");
		Assert.assertEquals(score0, 4,"学习项目，自学后，在课程页，查看课程评分详情，实际返回结果："+res);
	}
	
	@Test(description="学习项目，自学后，在课程页查询课程观看记录",priority=13)
	public void testQueryUnCourseRecord() {
		String res = MySelfStudyBusiness.queryCourseRecord(study_id);
		int total = (int)JSONPath.read(res, "$.total");
		Assert.assertEquals(total, 0,"未学习项目，自学后，在课程页查询课程观看记录,实际返回结果："+res);
	}
	@Test(description="查看已评论的学习项目课程列表",priority=14)
	public void testQueryCommentEdList() {
		String res = CourseFrontListInfoBusiness.queryCommentList(study_id);
		String content0 =(String)JSONPath.read(res, "$.list[0].content");
		String name =(String)JSONPath.read(res, "$.list[0].name");
		
		Long createTime =(Long)JSONPath.read(res, "$.list[0].createTime");
		Assert.assertEquals(name, UserBusiness.getUsername(),"查看已评论的学习项目课程列表--（name字段）实际返回结果"+res);
		Assert.assertTrue(createTime!=null, "查看已评论的学习项目课程列表--(创建时间)实际返回结果："+res);
		Assert.assertEquals(content0, content,"查看已评论的学习项目课程列表--（评论内容）实际返回结果："+res);
	}
	@Test(description="学习项目，自学后，在课程页开始学习----start_study接口",priority=15)
	public void testStartStudy() {
		String res = MySelfStudyBusiness.startStudy(study_id);
		String result = (String)JSONPath.read(res, "$.result");
		Assert.assertEquals(result, "true","学习项目，自学后，在课程页开始学习"+res);
	}
	@Test(description="学习项目，自学后，在课程查看课件学习进度",priority=16)
	public void testGetSingleResourceProgress() {
//		String res = MySelfStudyBusiness.getSingleResourceProgress(study_id, cour_id, cour_id);
//		String name = (String)JSONPath.read(res, "$.data.name");
//		Assert.assertNotNull(name,"学习项目，自学后，在课程查看课件学习进度，实际返回结果："+res);
	}
	
	@Test(description="学习项目，自学后，在课程查看课件详情",priority=17)
	public void testqueryCoursewerInfo() {
//		String res = MyElectiveTaskBusiness.queryCourseInfo(resourceId);
//		String name = (String)JSONPath.read(res, "$.title");
//		Assert.assertNotNull(name,"学习项目，自学后，在课程查看课件学习进度，实际返回结果："+res);
	}
	
	@Test(description="学习项目，自学后，在课程保存课件进度",priority=18)
	public void testSaveArtProgress() {
//		String res = MySelfStudyBusiness.saveProgress("100",study_id, cour_id, cour_id);
//		
//		int course_progress = (int)JSONPath.read(res, "$.course_progress");
//		int total_progress = (int)JSONPath.read(res, "$.total_progress");
//		Assert.assertEquals(total_progress, 20,"学习项目，自学后，在课程保存课件进度--总进度，实际返回结果："+res);
//		Assert.assertEquals(course_progress, 100,"学习项目，自学后，在课程保存课件进度--课件进度，实际返回结果："+res);
	}
	@Test(description="学习项目，自学后，在课程查看课程学习进度",priority=19)
	public void testGetSinglCourseProgress() {
//		String res = MySelfStudyBusiness.getSingleResourceProgress(study_id, courseId, resourceId);
//		String name = (String)JSONPath.read(res, "$.data.name");
//		Assert.assertNotNull(name,"学习项目，自学后，在课程查看课程学习进度，实际返回结果："+res);
	}
	
	@Test(description="学习项目，自学后，在课程查看课程详情",priority=20)
	public void testqueryCourseInfo() {
//		String res = MyElectiveTaskBusiness.queryCourseInfo(resourceId);
//		String name = (String)JSONPath.read(res, "$.title");
//		Assert.assertNotNull(name,"学习项目，自学后，在课程查看课程详情，实际返回结果："+res);
	}
	
	@Test(description="学习项目，自学后，在课程保存课程进度",priority=21)
	public void testSaveCourseProgress() {
//		String res = MySelfStudyBusiness.saveProgress("100",study_id, courseId,resourceId);
//		int course_progress = (int)JSONPath.read(res, "$.course_progress");
//		int total_progress = (int)JSONPath.read(res, "$.total_progress");
//		Assert.assertEquals(total_progress, 50,"学习项目，自学后，在课程保存课程进度--总进度，实际返回结果："+res);
//		Assert.assertEquals(course_progress, 100,"学习项目，自学后，在课程保存课程进度--课件进度，实际返回结果："+res);
	}
	@Test(description="学习项目，自学后，在课程查看考试详情",priority=22)
	public void testQueryExamInfo() {
		String res = MySelfStudyBusiness.queryExamInfo(exam_id);
		String title= (String)JSONPath.read(res, "$.title");
		Assert.assertNotNull(title, "学习项目，自学后，在课程查看考试详情："+res);
	}
	@Test(description="学习项目，自学后，在课程提交考试",priority=23)
	public void testSubmitPassByIdExam () {
		String res = ExaminationTaskBusiness.submitPassByIdExam(exam_id);
		String msg = (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg,"提交试卷成功!","学习项目，自学后，在课程提交考试"+res);
	}
	String questionId_one = "";
	String options_one = "";
	String questionId_two = "";
	String options_two = "";
	String questionId_three = "";
	String questionId_four = "";
	@Test(description="学习项目，自学后，在课程查看调研任务详情",priority=24)
	public void testQueryInvestigatesInfo() {
		String res = MySelfStudyBusiness.queryInvestigatesInfo(study_id, ins_id);
		String title = (String)JSONPath.read(res, "$.title");
		questionId_one = (String) JSONPath.read(res, "$.question[0].id");
		options_one = (String) JSONPath.read(res, "$.question[0].options[0].id");
		questionId_two = (String) JSONPath.read(res, "$.question[1].id");
		options_two = (String) JSONPath.read(res, "$.question[1].options[0].id");
		questionId_three = (String) JSONPath.read(res, "$.question[2].id");
		questionId_four = (String) JSONPath.read(res, "$.question[3].id");
		Assert.assertEquals(title, BaseBusiness.questionnaireName,"学习项目，自学后，在课程查看调研任务详情"+res);
	}
	@Test(description="在我的自学未完成列表，查看学习项目",priority=25)
	public void testQueryMySelfStudyList() {
		String res = MySelfStudyBusiness.queryMySelfStudyList(self_name, "unfinished");
		String title = (String)JSONPath.read(res, "$.list[0].title");
		int biz_type = (int)JSONPath.read(res, "$.list[0].biz_type");
		Assert.assertEquals(biz_type, 3,"在我的自学列表，查看未完成状态的课程信息，自学类型为学习项目"+res);
		Assert.assertNotNull(title,"在我的自学列表，查看未完成状态的课程信息，标题进行校验"+res);
	}

	@Test(description = "学习项目，自学后，在课程列表提交问卷", priority = 26)
	public void testSubmitAnonymousStatistics() {
		String res = MySelfStudyBusiness.submit(study_id, ins_id, questionId_one, options_one, questionId_two,
				options_two, questionId_three, questionId_four, "test", "4");
		String operate_result = (String) JSONPath.read(res, "$.operate_result");
		Assert.assertEquals(operate_result, "success", "学习项目，自学后，在课程提交问卷，实际返回结果：" + res);
	}
	@Test(description="学习项目，自学完成后，在课程详情页查看进度",priority=27)
	public void testQueryStudyCoursePassInfo() {
//		String res = MySelfStudyBusiness.queryStudyCourseInfo(study_id);
//		int process = (int)JSONPath.read(res, "$.process");
//		Assert.assertEquals(process, 100,"学习项目，自学完成后，在课程详情页查看进度，实际返回结果："+res);
	}
	
	@Test(description="在我的自学已完成列表，查看学习项目",priority=28)
	public void testQueryMySelfFinishStudyList() {
//		String res = MySelfStudyBusiness.queryMySelfStudyList(self_name, "finished");
//		String title = (String)JSONPath.read(res, "$.list[0].title");
//		int biz_type = (int)JSONPath.read(res, "$.list[0].biz_type");
//		Assert.assertEquals(biz_type, 3,"在我的自学列表，查看已完成状态的课程信息，自学类型为学习项目"+res);
//		Assert.assertEquals(title, self_name,"在我的自学列表，查看已完成状态的课程信息，标题进行校验"+res);
	}
	@Test(description="查看我的自学不同状态的个数",priority=29)
	public void testQueryStudyProjectsCount() {
		String res = MySelfStudyBusiness.queryStudyProjectsCount();
		int un_finish_count = (int)JSONPath.read(res, "$.un_finish_count");
		int total_count = (int)JSONPath.read(res, "$.total_count");
		int finish_count = (int)JSONPath.read(res, "$.finish_count");
		Assert.assertNotNull(total_count, "查看我的自学不同状态的总数,实际返回结果："+res);
		Assert.assertNotNull(finish_count, "查看我的自学不同状态的完成数,实际返回结果："+res);
		Assert.assertNotNull(un_finish_count, "查看我的自学不同状态的未完成数,实际返回结果："+res);
	}
	String study_pro_id = "";
	@Test(description="自学后，在学习项目列表查看自学人数",dependsOnMethods = "initMyselfStudy", priority=30)
	public void testQueryLearningProjectList() {
		String res = StudyProjectBusiness.queryLearningProjectList(self_name, classification_id);
		study_pro_id= (String)JSONPath.read(res, "$.list[0].id");
		int total = (int)JSONPath.read(res, "$.list[0].study_count");
		Assert.assertEquals(total, 1,"自学后，在学习项目列表查看自学人数，实际返回结果"+res);
	}
	
	@Test(description="自学完成后，停用学习项目，查看学习项目的课程详情的状态",priority=31)
	public void testQueryStudyCourseStatusInfo() {
		
		StudyProjectBusiness.updateStatus(study_pro_id, "disabled");
	
		String res = MySelfStudyBusiness.queryStudyCourseInfo(study_id);
		String status = (String)JSONPath.read(res, "$.status");
		Assert.assertEquals(status, "disabled","自学完成后，停用学习项目，查看学习项目的课程详情的状态，实际返回结果"+res);
	}
	
	@Test(description="停用学习项目后，在课程列表页（queryCourseByPage接口）不应显示",priority=32)
	public void testQueryCourseByPage() {
		String res = CourseBusiness.queryCourseByPage(self_name, "", "0");
		int total =(int)JSONPath.read(res, "$.total");
		Assert.assertEquals(total, 0,"停用学习项目后，在课程列表页（queryCourseByPage接口）不应显示，实际返回结果："+res);
	}
	
	@Test(description="停用学习项目后，在课程管理列表页（courses/manage接口）不应显示",priority=33)
	public void testCourseManageList() {
		String res= CourseBusiness.courseManageList(self_name, "all", "released","","","project");
		int total =(int)JSONPath.read(res, "$.total");
		Assert.assertEquals(total, 0,"停用学习项目后，在课程管理列表页（courses/manage接口）不应显示，实际返回结果："+res);
	}
	
	@Test(description="自学完成后，停用学习项目后再启用，查看学习项目的课程详情的状态",priority=34)
	public void testQueryStudyCourseOpenStatusInfo() {
		StudyProjectBusiness.updateStatus(study_pro_id, "normal");
		String res = MySelfStudyBusiness.queryStudyCourseInfo(study_id);
		String status = (String)JSONPath.read(res, "$.status");
//		Assert.assertEquals(status, "normal","自学完成后，停用学习项目后再启用，查看学习项目的课程详情的状态，实际返回结果"+res);
	}
	@Test(description="自学完成后 ，删除课程时，学习项目也被删除",priority=35)
	public void testQueryLearnProjectStatusList() {
		MySelfStudyBusiness.deleteCourse(study_pro_id);
		String res = StudyProjectBusiness.queryLearningProjectList(self_name, classification_id);
		int total =(int)JSONPath.read(res, "$.total");
		Assert.assertEquals(total, 0,"自学完成后 ，删除课程时，不应在我的自学列表显示，实际返回结果："+res);
	}
	@Test(description="自学完成后 ，删除课程时，不应在我的自学列表显示",priority=36)
	public void testQueryNonMySelfStudyList() {
		String list_res = MySelfStudyBusiness.queryMySelfStudyList(self_name, "");
		int total =(int)JSONPath.read(list_res, "$.total");
		Assert.assertEquals(total, 0,"自学完成后 ，删除课程时，不应在我的自学列表显示，实际返回结果："+list_res);
	}
}
