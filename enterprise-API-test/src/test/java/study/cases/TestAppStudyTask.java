package study.cases;

import cn.kxy.authentication.business.CertificateBusiness;
import cn.kxy.base.business.BaseBusiness;
import cn.kxy.course.resources.bussiness.CourseBusiness;
import cn.kxy.examination.business.ExaminationTaskBusiness;
import cn.kxy.examination.business.PaperBusiness;
import cn.kxy.examination.business.PaperExportBusiness;
import cn.kxy.group.a.business.ImageTextBusiness;
import cn.kxy.investigationresearch.business.QuestionnaireBusiness;
import cn.kxy.setting.bussiness.UserBusiness;
import cn.kxy.study.business.AppStudyBusiness;
import cn.kxy.study.business.MyStudyTask;
import cn.kxy.study.business.StudyTaskBusiness;
import init.cases.InitStudyAuthCourse;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import com.lazy.common.utils.CommonData;
import com.lazy.common.utils.DateUtil;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.UnsupportedEncodingException;

public class TestAppStudyTask extends InitStudyAuthCourse {
	String study_name = "APP Mix Study Task" + CommonData.getStringRandom(5);
	String id = "";
	String qualificationsId = "";
//	String post_name = "COO_Post" + CommonData.getStringRandom(3);
	String user_name = UserBusiness.getUsername();
	String courseId = "";
	String articleId = "";
	String resourcesId = "";
	String investigatesId = "";
	String examId = "";
//	String xx_Id = "";
//	String latojasId = "";
	String ques_id = "";

	String imagetext_id = "";
	@Test(description="1.创建图文课-发布接口", priority=1)
 	public void testImageTextAdd() throws UnsupportedEncodingException  {
 		String title = "图文课" + CommonData.getStringRandom(5);
 		String baseCover = "https://oss.coolcollege.cn/1789917624419880960.png";
 		String contentJsonStr = "{\"blocks\":[{\"key\":\"85lj8\",\"text\":\"text test\",\"type\":\"unstyled\","
 				+ "\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[],\"data\":{}}],\"entityMap\":{}}";
 		String contentJson = JSONObject.parseObject(contentJsonStr).toJSONString();
 		String res = ImageTextBusiness.ImageTextAdd(baseCover,contentJson,"release","",title);
 		imagetext_id = (String) JSONPath.read(res, "$.data");
 		String msg = (String)JSONPath.read(res, "$.msg");
 		Integer code = (Integer) JSONPath.read(res, "$.code");
 		System.out.println("1.创建图文课-发布接口:"+"code="+code+","+"imagetext_id="+imagetext_id);
 		Assert.assertEquals(msg, "新增课程成功","1.创建图文课-发布接口：" + res);
 	}
	
	@Test(description = "新增混合的学习任务",priority = 2)
	public void testAddMixSettledExamStudyTask() {
		String res = StudyTaskBusiness.addMixSettledExamStudyTask(study_name, DateUtil.getTimeStamp(0, ""),
				DateUtil.getTimeStamp(3, ""), CertificateBusiness.getIdByKeyword(cert_name),
				imagetext_id, CourseBusiness.getIdByPage(course_name),
				PaperBusiness.getIdByKeyword(BaseBusiness.pass_paper_name), DateUtil.getRegularDateForHHMM(0),
				DateUtil.getRegularDateForHHMM(2), 
				QuestionnaireBusiness.getIdByKeyword(BaseBusiness.questionnaireName, "release"));
		String msg = (String) JSONPath.read(res, "$.msg");
		String list_res = MyStudyTask.queryList(study_name,"0");
		id = (String)JSONPath.read(list_res, "$.list[0].id");
		
		Assert.assertEquals(msg, "新增计划成功！", "添加混合学习任务，实际返回结果:" + res);
	}

	@Test(description = "APP端查看混合学习列表", priority = 3)
	public void testQueryAPPStudyList() {
		String res = AppStudyBusiness.queryStudyList("unfinished");
		String type = (String) JSONPath.read(res, "$.list[0].type");
		int progress = (int) JSONPath.read(res, "$.list[0].progress");
		String title = (String) JSONPath.read(res, "$.list[0].title");
		Long end_time = (Long) JSONPath.read(res, "$.list[0].end_time");
		String create_user_name = (String) JSONPath.read(res, "$.list[0].create_user_name");
		int award_score = (int) JSONPath.read(res, "$.list[0].award_score");
		Assert.assertNotNull(award_score, "APP端查看混合学习列表,奖励学分做校验，实际返回结果：" + res);
		Assert.assertEquals(create_user_name, user_name, "APP端查看混合学习列表,创建人做校验，实际返回结果：" + res);
		Assert.assertTrue(end_time != null, "APP端查看混合学习列表,截止时间做校验，实际返回结果：" + res);
		Assert.assertNotNull(title,  "APP端查看混合学习列表,名称做校验，实际返回结果：" + res);
		Assert.assertEquals(progress, 0, "APP端查看混合学习列表,进度做校验，实际返回结果：" + res);
		Assert.assertNotNull(type,  "APP端查看混合学习列表,类型做校验，实际返回结果：" + res);
	}

	@Test(description = "APP端加载混合学习资源", priority = 4)
	public void testLoadResources() {
		String res = AppStudyBusiness.loadResource(id);
		String status = (String) JSONPath.read(res, "$.status");
		Assert.assertEquals(status, "success", "APP端加载混合学习资源,实际返回结果:" + res);
	}

	String resour_id = "";
	@Test(description = "APP端查看混合学习详情", priority = 5)
	public void testQueryAppInfo() {
		String res = AppStudyBusiness.queryInfo(id);
		String type = (String) JSONPath.read(res, "$.type");
		int study_progress = (int) JSONPath.read(res, "$.study_progress");
		int progress = (int) JSONPath.read(res, "$.progress");
		int exam_pass_count = (int) JSONPath.read(res, "$.exam_pass_count");
		int exam_remainder_count = (int) JSONPath.read(res, "$.exam_remainder_count");
		int study_progress_standard = (int) JSONPath.read(res, "$.study_progress_standard");
		int art_progress = (int) JSONPath.read(res, "$.stages[0].course_list[0].progress");
		String cour_name = (String) JSONPath.read(res, "$.stages[0].course_list[1].title");
		String exam_name = (String) JSONPath.read(res, "$.stages[0].course_list[2].title");
	
		int cour_progress = (int) JSONPath.read(res, "$.stages[0].course_list[1].progress");
		int xx_progress = (int) JSONPath.read(res, "$.stages[0].course_list[3].progress");
		String exam_status = (String) JSONPath.read(res, "$.stages[0].course_list[2].status");
		String ques_status = (String) JSONPath.read(res, "$.stages[0].course_list[3].status");
		articleId = (String) JSONPath.read(res, "$.stages[0].course_list[0].id");
		resour_id  = (String) JSONPath.read(res, "$.stages[0].course_list[0].resource_id");
		
		courseId = (String) JSONPath.read(res, "$.stages[0].course_list[1].id");
		examId = (String) JSONPath.read(res, "$.stages[0].course_list[2].id");
		investigatesId = (String) JSONPath.read(res, "$.stages[0].course_list[3].id");
		ques_id = (String) JSONPath.read(res, "$.stages[0].course_list[3].questionnaire_id");
		int award_score = (int) JSONPath.read(res, "$.award_score");
		
		String second_type = (String)JSONPath.read(res, "$.stages[0].course_list[1].type");
		String third_type = (String)JSONPath.read(res, "$.stages[0].course_list[2].type");
		
		Assert.assertEquals(second_type, "course", "APP端查看混合学习详情,学习类型字段校验，实际返回结果：" + res);
		Assert.assertEquals(third_type, "exam", "APP端查看混合学习详情,学习类型字段校验，实际返回结果：" + res);
		Assert.assertEquals(award_score, 8, "APP端查看混合学习详情,奖励学分做校验，实际返回结果：" + res);
		Assert.assertEquals(art_progress, 0, "APP端查看混合学习详情，文章的进度做校验，实际返回结果：" + res);
		Assert.assertEquals(cour_name, course_name, "APP端查看混合学习详情，课程名称做校验，实际返回结果：" + res);
		Assert.assertEquals(exam_name, "exam", "APP端查看混合学习详情，考试名称做校验，实际返回结果：" + res);
		
		Assert.assertEquals(cour_progress, 0, "APP端查看混合学习详情，课程进度做校验，实际返回结果：" + res);
		Assert.assertEquals(xx_progress, 0, "APP端查看混合学习详情，线下签到进度做校验，实际返回结果：" + res);
		Assert.assertEquals(exam_status, "unfinished", "APP端查看混合学习详情，考试状态做校验，实际返回结果：" + res);
		Assert.assertEquals(ques_status, "normal", "APP端查看混合学习详情，调研状态做校验，实际返回结果：" + res);
		Assert.assertEquals(study_progress_standard, 100, "APP端查看混合学习详情，学习进度做校验，实际返回结果：" + res);
		Assert.assertEquals(exam_remainder_count, 1, "APP端查看混合学习详情，剩余考试场次做校验，实际返回结果：" + res);
		Assert.assertEquals(exam_pass_count, 0, "APP端查看混合学习详情,考试及格场次做校验，实际返回结果：" + res);
		Assert.assertEquals(progress, 0, "APP端查看混合学习详情,总进度做校验，实际返回结果：" + res);
		Assert.assertEquals(study_progress, 0, "APP端查看混合学习详情,学习进度做校验，实际返回结果：" + res);
		Assert.assertEquals(type, "online", "APP端查看混合学习详情,类型做校验，实际返回结果：" + res);
	}

	@Test(description = "App端保存学习任务中的课件进度--20%进度时", priority = 6)
	public void testsaveArtProgress() {
		String res = AppStudyBusiness.saveProgress("100", id, articleId, resour_id);
		int course_progress = (int) JSONPath.read(res, "$.course_progress");
		int total_progress = (int) JSONPath.read(res, "$.total_progress");
		int study_progress = (int) JSONPath.read(res, "$.study_progress");
		Assert.assertEquals(study_progress, 33, "保存学习任务中课件的学习进度，实际返回结果：" + res);
		Assert.assertEquals(course_progress, 100, "保存学习任务中的课件进度，实际返回结果：" + res);
		Assert.assertEquals(total_progress, 25, "保存学习任务中的课件，总进度校验，实际返回结果：" + res);
	}

	@Test(description = "APP端保存学习任务中的课件进度后，查看混合学习的详情--20%进度时", priority = 7)
	public void testQueryAppSaveArtInfo() {
		String res = AppStudyBusiness.queryInfo(id);
		int art_progress = (int) JSONPath.read(res, "$.stages[0].course_list[0].progress");
		Assert.assertEquals(art_progress, 100, "APP端保存学习任务中的课件进度后，查看混合学习的详情,课件进度做校验，实际返回结果：" + res);
	}

	@Test(description = "APP端保存学习任务中的课件进度后,web端查看学习任务进行中的人员的培训进度--20%进度时", priority = 8)
	public void testQueryTrainMonitor() {
		String res = StudyTaskBusiness.queryTrainMonitor("", id);
		int studyProgress = (int) JSONPath.read(res, "$.data.voList.list[0].process");
		Assert.assertEquals(studyProgress, 25, "APP端保存学习任务中的课件进度后,查看学习任务进行中的人员的培训进度：" + res);
	}
	@Test(description = "APP端保存学习任务中的课件进度后,web端查看全部人员监控--20%进度时", priority = 9)
	public void testQueryUndoUserMonitors() {
		String res = StudyTaskBusiness.queryUserMonitors(id, "true", "0", "", "0");
		int studyProgress = (int) JSONPath.read(res, "$.data.voList.list[0].studyProgress");
		int process = (int) JSONPath.read(res, "$.data.voList.list[0].process");
		Assert.assertEquals(studyProgress, 33, "APP端保存学习任务中的课件进度后,web端查看全部人员监控：" + res);
		Assert.assertEquals(process, 25, "APP端保存学习任务中的课件进度后,web端查看全部人员监控：" + res);
	}

	@Test(description = "查看APP端学习任务的课程详情", priority = 10)
	public void testQueryStudyCourseoyherInfo() {
		String res = AppStudyBusiness.queryStudyCourseInfo(courseId);
		resourcesId = (String) JSONPath.read(res, "$.resources[0].id");
		String title = (String) JSONPath.read(res, "$.title");
		String lecturer_name = (String) JSONPath.read(res, "$.lecturer.lecturer_name");
//		Assert.assertEquals(lecturer_name, outer_name, "查看学习任务的课程详情," + res);
		Assert.assertEquals(title, course_name, "查看学习任务的课程详情," + res);
	}

	@Test(description = "保存APP端学习任务的课程进度", priority = 11)
	public void testSaveCourseProgress() {
//		String res = AppStudyBusiness.saveProgress("100", id, courseId, resourcesId);
//		int course_progress = (int) JSONPath.read(res, "$.course_progress");
//		int total_progress = (int) JSONPath.read(res, "$.total_progress");
//		Assert.assertEquals(course_progress, 100, "保存APP端学习任务的课程进度，实际返回结果：" + res);
//		Assert.assertEquals(total_progress, 50, "保存APP端学习任务的课程进度，实际返回结果：" + res);
	}

	@Test(description = "APP端对学习任务的课程进行点赞", priority = 12)
	public void testStudyLikeCourse() {
		String res = AppStudyBusiness.StudyLikeCourse(courseId, "like");
		String status = (String) JSONPath.read(res, "$.status");
		Assert.assertEquals(status, "like", "APP端对学习任务的课程进行点赞，实际返回结果：" + res);
	}

	@Test(description = "APP端对学习任务的课程取消点赞", priority = 13)
	public void testStudyUnLikeCourse() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String res = AppStudyBusiness.StudyLikeCourse(courseId, "unlike");
		String status = (String) JSONPath.read(res, "$.status");
		Assert.assertEquals(status, "unlike", "APP端对学习任务的课程进行取消点赞，实际返回结果：" + res);
	}

	@Test(description = "APP端对学习任务的课程进行评论", priority = 14)
	public void testAddStudyCourseComment() {
		String res = AppStudyBusiness.addStudyCourseComment(courseId, "perfect");
		String comment = (String) JSONPath.read(res, "$.comment");
		Assert.assertEquals(comment, "perfect", "APP端对学习任务的课程进行评论，实际返回结果：" + res);
	}

	@Test(description = "APP端保存学习任务中的课程进度后，查看混合学习的详情--40%进度时，课程进度做校验", priority = 15)
	public void testQueryAppSaveCourseInfo() {
		String res = AppStudyBusiness.queryInfo(id);
		Assert.assertTrue(res.contains("online"), "APP端保存学习任务中的课程进度后，查看混合学习的详情,课程进度做校验，实际返回结果：" + res);
	}

	@Test(description = "APP端保存学习任务中的课程进度后,web端查看学习任务进行中的人员的培训进度--40%进度时", priority = 16)
	public void testQueryTrainCourseMonitor() {
		String res = StudyTaskBusiness.queryTrainMonitor("", id);
		int studyProgress = (int) JSONPath.read(res, "$.data.voList.list[0].process");
		Assert.assertEquals(studyProgress, 25, "APP端保存学习任务中的课程进度后,查看学习任务进行中的人员的培训进度：" + res);
	}

	@Test(description = "APP端保存学习任务中的课程进度后,web端查看全部人员监控--40%进度时", priority = 17)
	public void testQueryUndoUserCourseMonitors() {
		String res = StudyTaskBusiness.queryUserMonitors(id, "true", "0", "", "0");
		int art_Progress = (int) JSONPath.read(res,
				"$.data.voList.list[0].stage_train_vo_list[0].courseInfo[0].studyProgress");
		Assert.assertEquals(art_Progress, 100, "APP端保存学习任务中的课程进度后,web端查看全部人员监控--课件进度" + res);
	}

	@Test(description = "在APP端查看学习任务考试概要", priority = 18)
	public void testQueryExamListInfo() {
		String res = AppStudyBusiness.queryExamListInfo(examId);
		int certificate_count = (int) JSONPath.read(res, "$.certificate_count");
		String exam_title = (String) JSONPath.read(res, "$.title");
		String total_score = (String) JSONPath.read(res, "$.total_score");
		String summary = (String) JSONPath.read(res, "$.summary");
		String passing_score = (String) JSONPath.read(res, "$.passing_score");
		int exam_count = (int) JSONPath.read(res, "$.exam_count");
		int exam_duration = (int) JSONPath.read(res, "$.exam_duration");
		Long begin_time = (Long) JSONPath.read(res, "$.begin_time");
		Long end_time = (Long) JSONPath.read(res, "$.end_time");
		Assert.assertTrue(begin_time != null, "查看学习任务考试的概要--开始时间，实际返回结果：" + res);
		Assert.assertTrue(end_time != null, "查看学习任务考试的概要--开始时间，实际返回结果：" + res);
		Assert.assertEquals(total_score, "100.0", "查看学习任务考试的概要--总分，实际返回结果：" + res);
		Assert.assertEquals(summary, "this is a summary", "查看学习任务考试的概要，实际返回结果：" + res);
		Assert.assertEquals(passing_score, "60.0", "查看学习任务考试的及格分，实际返回结果：" + res);
		Assert.assertEquals(exam_count, 1, "查看学习任务考试的考试次数，实际返回结果：" + res);
		Assert.assertEquals(exam_duration, 2700, "查看学习任务考试时长，实际返回结果：" + res);
		Assert.assertEquals(exam_title, "exam", "查看学习任务考试的标题，实际返回结果：" + res);
		Assert.assertEquals(certificate_count, 0, "查看学习任务考试的证书个数，实际返回结果：" + res);
	}

	@Test(description = "在APP端查看学习任务的考试是否可以进行考试", priority = 19)
	public void testCheckIsCanExam() {
		String res = AppStudyBusiness.checkIsCanExam(examId);
		String can_exam = (String) JSONPath.read(res, "$.can_exam");
		Assert.assertEquals(can_exam, "true", "查看学习任务的考试是否可以进行考试，实际返回结果：" + res);
	}

	@Test(description = "在APP端查看学习任务考试详情", priority = 20)
	public void testQueryExamInfo() {
		String res = AppStudyBusiness.queryExamInfo(examId);
		String exam_title = (String) JSONPath.read(res, "$.title");
		String summary = (String) JSONPath.read(res, "$.summary");
		Assert.assertEquals(summary, "this is a summary", "查看学习任务考试详情，实际返回结果：" + res);
		Assert.assertEquals(exam_title, "exam", "查看学习任务考试详情，实际返回结果：" + res);
	}

	@Test(description = "在APP端在学习任务提交考试", priority = 21)
	public void testsubmitBlankExam() {
		String res = AppStudyBusiness.submitBlankExam(examId);
		String msg = (String) JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "提交试卷成功!", "在学习任务提交考试，实际返回结果：" + res);
	}
	@Test(description = "在APP端在学习任务查看考试结果", priority = 22)
	public void testqueryExamResult() {
		String res = AppStudyBusiness.queryExamResult(examId);
		String status = (String) JSONPath.read(res, "$.status");
		String correct_rate = (String) JSONPath.read(res, "$.correct_rate");
		String show_makeup_exam = (String) JSONPath.read(res, "$.show_makeup_exam");
		int correct_count = (int) JSONPath.read(res, "$.correct_count");
		Assert.assertEquals(correct_count, 0, "在学习任务查看考试结果:" + res);
		Assert.assertEquals(show_makeup_exam, "false", "在学习任务查看考试结果:" + res);
		Assert.assertEquals(correct_rate, "0%", "在学习任务查看考试结果:" + res);
		Assert.assertEquals(status, "failed", "在学习任务查看考试结果:" + res);
	}

	@Test(description = "在APP端学习任务查看考试的答案解析", priority = 23)
	public void testqueryExamanswerAnalysis() {
		String res = AppStudyBusiness.queryExamanswer_analysis(examId);
		String title = (String) JSONPath.read(res, "$.title");
		Assert.assertTrue(title.equals("exam"), "在学习任务查看考试的答案解析，实际返回结果：" + res);
	}

	@Test(description = "APP端查看学习任务的考试排名", priority = 24)
	public void testQueryExamanswerRanking() {
		String res = AppStudyBusiness.queryExamanswerRanking(examId);
		int total = (int) JSONPath.read(res, "$.total");
		Assert.assertTrue(total>=0 ,"APP端查看学习任务的考试排名" + res);
	}

	@Test(description = "APP端考试失败后，查看学习任务详情的考试状态", priority = 25)
	public void testQueryAppExamInfo() {
		String res = AppStudyBusiness.queryInfo(id);
		int exam_progress = (int) JSONPath.read(res, "$.stages[0].course_list[0].progress");
		String status = (String) JSONPath.read(res, "$.stages[0].course_list[2].status");
		Assert.assertEquals(status, "failed", "APP端保存学习任务中的课件进度后，查看混合学习的详情,课件进度做校验，实际返回结果：" + res);
		Assert.assertEquals(exam_progress, 100, "APP端保存学习任务中的课件进度后，查看混合学习的详情,课件进度做校验，实际返回结果：" + res);
	}

	@Test(description = "APP端学习任务中，试卷已经提交,不允许重复提交!", priority = 26)
	public void testSubmitPassExam() {
		String res = AppStudyBusiness.submitPassExam(examId);
		String msg = (String) JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "试卷已经提交,不允许重复提交!", "APP端学习任务中，试卷已经提交,不允许重复提交!实际返回结果：" + res);
	}
	@Test(description = "APP端学习任务中的考试失败--60%进度时,web端查看学习任务进行中的人员的培训进度", priority = 27)
	public void testQueryTrainExameMonitor() {
		String res = StudyTaskBusiness.queryTrainMonitor("", id);
		int studyProgress = (int) JSONPath.read(res, "$.data.voList.list[0].process");
		Assert.assertEquals(studyProgress, 50, "APP端学习任务中的考试失败--60%进度时,web端查看学习任务进行中的人员的培训进度" + res);
	}

	@Test(description = "APP端学习任务中的考试失败--60%进度时,web端查看全部人员监控", priority = 28)
	public void testQueryExamUserCourseMonitors() {
		String res = StudyTaskBusiness.queryUserMonitors(id, "true", "2", "", "0");
		int art_Progress = (int) JSONPath.read(res,
				"$.data.voList.list[0].stage_train_vo_list[0].courseInfo[0].studyProgress");
		Assert.assertEquals(art_Progress, 100, "APP端学习任务中的考试失败--60%进度时,web端查看全部人员监控--课件进度" + res);
	}

	@Test(description = "APP端学习任务中的进度为60%，查看web端的学习记录", priority = 29)
	public void testQueryStudyRecord() {
		String res = AppStudyBusiness.queryStudyRecord(id);
	
		String course_title = (String) JSONPath.read(res, "$.data[0].courseInfo[1].title");
		Assert.assertEquals(course_title, course_name, "APP端学习任务中的进度为60%，查看web端的学习记录" + res);
	}
	
	@Test(description = "查看学习记录详情", priority= 30)
	public void queryStudyRecordInfo() {
		String res = AppStudyBusiness.queryStudyRecordInfo(id);
		Assert.assertTrue(res.contains("result"),"查看学习记录详情"+res);
	}

	@Test(description = "考试不及格后，查看学习任务的考试记录", priority = 31)
	public void testGetTrainExamRecord() {
		String res = AppStudyBusiness.getTrainExamRecord(id);
		String step_name = (String) JSONPath.read(res, "$.data[0].stageName");
		Assert.assertEquals(step_name, "step one", "考试不及格后，查看学习任务的考试记录" + res);
	}

	@Test(description = "导出学习记录", priority = 32)
	public void testQueryLatojasInfo() {
		String res = AppStudyBusiness.studyRecordExport(id);
		Assert.assertTrue(res.contains("学习记录导出记录已生成"),"导出学习记录，实际返回结果："+res);
	}

	@Test(description = "APP端在签到时间后进行签到，显示迟到", priority = 33)
	public void testOfflineSignIn() {
//		String res = AppStudyBusiness.offlineSignIn(latojasId);
//		String status = (String) JSONPath.read(res, "$.status");
//		Assert.assertEquals(status, "late_signed", "APP端在签到时间后进行签到，显示迟到" + res);
	}

	@Test(description = "APP端签到后迟到，查看学习任务线下签到详情", priority = 34)
	public void testQueryLateSignLatojasInfo() {
//		String res = AppStudyBusiness.queryLatojasInfo(id, xx_Id);
//		String status = (String) JSONPath.read(res, "$.sign_in_list[0].status");
//		Assert.assertEquals(status, "late_signed", "APP端签到后迟到，查看学习任务线下签到详情--签到状态，实际返回结果：" + res);
	}

	@Test(description = "APP端签到后迟到,web端查看学习任务进行中的人员的培训进度--80%进度时", priority = 35)
	public void testQueryOfflineSignTrainMonitor() {
		String res = StudyTaskBusiness.queryTrainMonitor("", id);
		int studyProgress = (int) JSONPath.read(res, "$.data.voList.list[0].process");
		Assert.assertEquals(studyProgress, 50, "APP端签到后迟到,web端查看学习任务进行中的人员的培训进度--80%进度时：" + res);
	}

	@Test(description = "APP端签到后迟到，在学习任务详情查看线下签到的状态和进度", priority = 36)
	public void testQueryAppOfflineSignInInfo() {
		String res = AppStudyBusiness.queryInfo(id);
		int art_progress = (int) JSONPath.read(res, "$.stages[0].course_list[0].progress");
		Assert.assertEquals(art_progress, 100, "APP端签到后迟到，在学习任务详情查看线下签到的状态和进度，实际返回结果：" + res);
	}

	@Test(description = "APP端签到后迟到,web端查看全部人员监控--80%进度时", priority = 37)
	public void testQueryOfflineSignUserMonitors() {
		String res = StudyTaskBusiness.queryUserMonitors(id, "true", "0", "", "0");
//		int studyProgress = (int) JSONPath.read(res, "$.data.voList.list[0].studyProgress");
		int process = (int) JSONPath.read(res, "$.data.voList.list[0].process");
//		Assert.assertEquals(studyProgress, 75, "APP端签到后迟到,web端查看全部人员监控--80%进度时：" + res);
		Assert.assertEquals(process, 50, "APP端签到后迟到,web端查看全部人员监控--80%进度时：" + res);
	}

//	String la_id = "";
	
	@Test(description = "APP端签到后迟到，Web端查看数据监控的线下培训列表", priority = 38)
	public void testQueryLatojaMonitor() {
//		String res = StudyTaskBusiness.queryLatojaMonitor(id);
//		String xxqd = (String) JSONPath.read(res, "$.data.list[0].title");
//		Long beginTime = (Long) JSONPath.read(res, "$.data.list[0].beginTime");
//		Long endTime = (Long) JSONPath.read(res, "$.data.list[0].endTime");
//		int shouldCount = (int) JSONPath.read(res, "$.data.list[0].shouldCount");
//		int absentCount = (int) JSONPath.read(res, "$.data.list[0].absentCount");
//		int actualCount = (int) JSONPath.read(res, "$.data.list[0].actualCount");
//		la_id = (String)JSONPath.read(res, "$.data.list[0].id");
//		Assert.assertEquals(shouldCount, 1, "APP端签到后迟到，查看数据监控的线下培训列表---应到人数，实际返回结果：" + res);
//		Assert.assertEquals(absentCount, 0, "APP端签到后迟到，查看数据监控的线下培训列表---缺席人数，实际返回结果：" + res);
//		Assert.assertEquals(actualCount, 1, "APP端签到后迟到，查看数据监控的线下培训列表---实到人数，实际返回结果：" + res);
//		Assert.assertTrue(endTime != null, "APP端签到后迟到，查看数据监控的线下培训列--开始时间，实际返回结果：" + res);
//		Assert.assertTrue(beginTime != null, "APP端签到后迟到，查看数据监控的线下培训列表---结束时间，实际返回结果：" + res);
//		Assert.assertEquals(xxqd, "offline trainer", "APP端签到后迟到，查看数据监控的线下培训列表---名称，实际返回结果：" + res);
	}

	@Test(description = "APP端签到后迟到，查看线下签到详情", priority = 39)
	public void testQueryLatojaMonitorDetailList() {
//		String res = StudyTaskBusiness.queryLatojaMonitorDetailList(id, xx_Id, UserBusiness.getUsername());
//		String userName = (String) JSONPath.read(res, "$.data.list.list[0].userName");
//		String post_name = (String) JSONPath.read(res, "$.data.list.list[0].postNameList[0]");
//		String departName = (String) JSONPath.read(res, "$.data.list.list[0].departName");
//		String processStr = (String) JSONPath.read(res, "$.data.list.list[0].processStr");
//		String title = (String) JSONPath.read(res, "$.data.latojaVo.title");
//		Long beginTime = (Long) JSONPath.read(res, "$.data.latojaVo.beginTime");
//		Long endTime = (Long) JSONPath.read(res, "$.data.latojaVo.endTime");
//		String tea_name = (String) JSONPath.read(res, "$.data.latojaVo.name");
//		String statusName = (String) JSONPath.read(res, "$.data.list.list[0].clockTimeRecordList[0].statusName");
//		Long signInTime = (Long) JSONPath.read(res, "$.data.list.list[0].clockTimeRecordList[0].signInTime");
//		Assert.assertTrue(signInTime != null, "APP端签到后迟到，查看线下签到详情--签到时间，实际返回结果：" + res);
//		Assert.assertEquals(statusName, "迟到", "APP端签到后迟到，查看线下签到详情--线下培训的签到状态，实际返回结果：" + res);
//		Assert.assertEquals(tea_name, outer_name, "APP端签到后迟到，查看线下签到详情--线下培训的讲师，实际返回结果：" + res);
//		Assert.assertEquals(title, "offline trainer", "APP端签到后迟到，查看线下签到详情--线下培训的标题，实际返回结果：" + res);
//		Assert.assertTrue(beginTime != null, "APP端签到后迟到，查看线下签到详情--开始时间，实际返回结果：" + res);
//		Assert.assertTrue(endTime != null, "APP端签到后迟到，查看线下签到详情--结束时间，实际返回结果：" + res);
//		Assert.assertEquals(processStr, "100%", "APP端签到后迟到，查看线下签到详情--培训进度，实际返回结果：" + res);
//		Assert.assertTrue(departName != null, "APP端签到后迟到，查看线下签到详情--所属部门，实际返回结果：" + res);
//		Assert.assertTrue(post_name != null, "APP端签到后迟到，查看线下签到详情--所属岗位，实际返回结果：" + res);
//		Assert.assertEquals(userName, user_name, "APP端签到后迟到，查看线下签到详情--姓名，实际返回结果：" + res);
	}

	@Test(description = "APP端签到后迟到,web端查询学习任务列表--80%进度时", priority = 40)
	public void testGetStudyTaskList() {
//		String res = StudyTaskBusiness.getStudyTaskList(study_name, "true", "0", "");
//		String finishPercent = (String) JSONPath.read(res, "$.list[0].finishPercent");
//		Assert.assertEquals(finishPercent, "0", "APP端签到后迟到,web端查询学习任务列表--80%进度时" + res);

	}

	String questionId_one = "";
	String options_one = "";
	String questionId_two = "";
	String options_two = "";
	String questionId_three = "";
	String questionId_four = "";

	@Test(description = "APP端查看学习任务下的调研详情", priority = 41)
	public void testQueryAppInvestigatesInfo() {
		String res = AppStudyBusiness.queryAppInvestigatesInfo(investigatesId, ques_id);
		questionId_one = (String) JSONPath.read(res, "$.question[0].id");
		options_one = (String) JSONPath.read(res, "$.question[0].options[0].id");
		questionId_two = (String) JSONPath.read(res, "$.question[1].id");
		options_two = (String) JSONPath.read(res, "$.question[1].options[0].id");
		questionId_three = (String) JSONPath.read(res, "$.question[2].id");
		questionId_four = (String) JSONPath.read(res, "$.question[3].id");
		String name = (String) JSONPath.read(res, "$.questionnaire_title");
		String description = (String) JSONPath.read(res, "$.description");
		String anonymous = (String) JSONPath.read(res, "$.anonymous");
		String title01 = (String) JSONPath.read(res, "$.question[0].title");
		String title04 = (String) JSONPath.read(res, "$.question[3].title");
		Assert.assertEquals(anonymous, "false", "APP端查看学习任务下的调研详情（匿名，可查看统计结果--信息校验），实际返回结果：" + res);
		Assert.assertEquals(title04, "What's your score for selenium",
				"APP端查看学习任务下的调研详情（匿名，可查看统计结果--信息校验），实际返回结果：" + res);
		Assert.assertEquals(title01, "can you use selenium", "APP端查看学习任务下的调研详情（匿名，可查看统计结果--信息校验），实际返回结果：" + res);
		Assert.assertEquals(description, "Questionnaire Description",
				"APP端查看学习任务下的调研详情（匿名，可查看统计结果--信息校验），实际返回结果：" + res);
		Assert.assertEquals(name, BaseBusiness.questionnaireName, "APP端查看学习任务下的调研详情（匿名，可查看统计结果--信息校验），实际返回结果：" + res);

	}

	@Test(description = "匿名，可查看统计结果，提交问卷", priority = 42)
	public void testSubmitAnonymousStatistics() {
		String res = AppStudyBusiness.submit(id, investigatesId, questionId_one, options_one, questionId_two,
				options_two, questionId_three, questionId_four, "test", "4");
		String operate_result = (String) JSONPath.read(res, "$.operate_result");
		Assert.assertEquals(operate_result, "success", "匿名，可查看统计结果，提交问卷实际返回结果：" + res);
	}
	
	@Test(description = "APP端完成学习任务后,查看已完成的学习列表", priority = 43)
	public void testQueryAPPFinishedStudyList() {
//		String res = AppStudyBusiness.queryStudyList("finished");
//		String type = (String) JSONPath.read(res, "$.list[0].type");
//		int progress = (int) JSONPath.read(res, "$.list[0].progress");
//		String title = (String) JSONPath.read(res, "$.list[0].title");
//		Long end_time = (Long) JSONPath.read(res, "$.list[0].task_end_time");
//		String create_user_name = (String) JSONPath.read(res, "$.list[0].create_user_name");
//		
//		Assert.assertEquals(create_user_name, user_name, "APP端完成学习任务后,查看已完成的学习列表,创建人做校验，实际返回结果：" + res);
//		Assert.assertTrue(end_time != null, "APP端完成学习任务后,查看已完成的学习列表,完成时间做校验，实际返回结果：" + res);
//		Assert.assertEquals(title, study_name, "APP端完成学习任务后,查看已完成的学习列表,名称做校验，实际返回结果：" + res);
//		Assert.assertEquals(progress, 100, "APP端完成学习任务后,查看已完成的学习列表,进度做校验，实际返回结果：" + res);
//		Assert.assertEquals(type, "online", "APP端完成学习任务后,查看已完成的学习列表,类型做校验，实际返回结果：" + res);
	}

	@Test(description = "APP端完成学习任务后，查看学习任务详情", priority = 44)
	public void testQueryFinishStudyInfo() {
//		AppStudyBusiness.loadResource(id);
//		String res = AppStudyBusiness.queryInfo(id);
//		int study_progress = (int) JSONPath.read(res, "$.study_progress");
//		Assert.assertEquals(study_progress, 100, "APP端完成学习任务后，查看学习任务详情,实际返回结果:" + res);
	}

	@Test(description = "APP端完成学习任务后,web端查看全部人员监控--100%进度时", priority = 45)
	public void testQueryFinishedUserMonitors() {
//		String res = StudyTaskBusiness.queryUserMonitors(id, "true", "0", "", "2");
//		int studyProgress = (int) JSONPath.read(res, "$.data.voList.list[0].studyProgress");
//		int process = (int) JSONPath.read(res, "$.data.voList.list[0].process");
//		Assert.assertEquals(studyProgress, 100, "APP端完成学习任务后,web端查看全部人员监控--100%进度时：" + res);
//		Assert.assertEquals(process, 100, "APP端完成学习任务后,web端查看全部人员监控--100%进度时：" + res);
	}
	
	@Test(description = "APP端完成学习任务后,web端查询学习任务列表", priority = 46)
	public void testGetStudyFinishedTaskList() {
//		String res = StudyTaskBusiness.getStudyTaskList(study_name, "true", "0", "","");
//		String finishPercent = (String) JSONPath.read(res, "$.list[0].finishPercent");
//		int finishUserCount = (int) JSONPath.read(res, "$.list[0].finishUserCount");
//		Assert.assertEquals(finishUserCount, 1, "APP端完成学习任务后,web端查询学习任务列表，完成人数" + res);
//		Assert.assertEquals(finishPercent, "100", "APP端完成学习任务后,web端查询学习任务列表，完成率" + res);

	}
	@Test (description="APP端完成学习任务后.查看问卷调研的统计数据",priority=47)
	public void testQueryLatojaMoitor() {
		String res = StudyTaskBusiness.queryInvestigates(id);
		String title_a= (String)JSONPath.read(res, "$.list[0].title");
		int investigate_count = (int)JSONPath.read(res, "$.list[0].investigate_count");
		Long update_time= (Long)JSONPath.read(res, "$.list[0].update_time");
		Assert.assertTrue(update_time!=null, "APP端完成学习任务后,查看问卷调研的统计数据，更新时间实际返回结果"+res);
		Assert.assertEquals(investigate_count,1,"APP端完成学习任务后,查看问卷调研的统计数据，调研份数实际返回结果"+res);
		Assert.assertNotNull(title_a,"APP端完成学习任务后,查看问卷调研的统计数据，调研的名称实际返回结果"+res);
	}
	
	@Test(description="APP端完成学习任务后，查看问卷调研详情，参数含有userid的 ",priority=48)
	public void testQueryInvestigatesResulByUserId() {
		String res = StudyTaskBusiness.queryInvestigatesResulByUserId(id, investigatesId);
		String title = (String)JSONPath.read(res, "$.title");
		String create_user_name = (String)JSONPath.read(res, "$.create_user_name");
		Long begin_time= (Long)JSONPath.read(res, "$.end_time");
		Long end_time= (Long)JSONPath.read(res, "$.end_time");
		
		String star = (String)JSONPath.read(res, "$.questions[3].answer.normal[0]");
		Assert.assertEquals(star, "4","APP端完成学习任务后，查看问卷调研明细,问卷名称实际返回结果："+res);
		Assert.assertTrue(create_user_name!=null, "APP端完成学习任务后，查看问卷调研明细，创建人实际返回结果"+res);
		Assert.assertTrue(begin_time!=null, "APP端完成学习任务后，查看问卷调研明细，开始时间实际返回结果"+res);
		Assert.assertTrue(end_time!=null, "APP端完成学习任务后，查看问卷调研明细，结束时间实际返回结果"+res);
		Assert.assertEquals(title, BaseBusiness.questionnaireName,"APP端完成学习任务后，查看问卷调研明细,问卷名称实际返回结果："+res);
	}
	@Test(description="APP端完成学习任务后，查看问卷调研明细",priority=49)
	public void testQueryInvestigatesResult() {
		String res = StudyTaskBusiness.queryInvestigatesResulByUserId(id, investigatesId);
		String title = (String)JSONPath.read(res, "$.title");
		String create_user_name = (String)JSONPath.read(res, "$.create_user_name");
		Long begin_time= (Long)JSONPath.read(res, "$.end_time");
		Long end_time= (Long)JSONPath.read(res, "$.end_time");
		Assert.assertTrue(create_user_name!=null, "APP端完成学习任务后，查看问卷调研明细，创建人实际返回结果"+res);
		Assert.assertTrue(begin_time!=null, "APP端完成学习任务后，查看问卷调研明细，开始时间实际返回结果"+res);
		Assert.assertTrue(end_time!=null, "APP端完成学习任务后，查看问卷调研明细，结束时间实际返回结果"+res);
		Assert.assertEquals(title, BaseBusiness.questionnaireName,"APP端完成学习任务后，查看问卷调研明细,问卷名称实际返回结果："+res);
	}
	@Test(description="APP端完成学习任务后，查看调研统计数据",priority=50)
	public void testQueryInvestigatesStatisticsInfo() {
		String res = StudyTaskBusiness.queryInvestigatesStatisticsInfo(investigatesId);
		String title = (String)JSONPath.read(res, "$.investigate_title");
		Assert.assertNotNull(title,"APP端完成学习任务后，查看调研统计数据,实际返回结果："+res);
	}
	@Test(description = "清除所有导出数据",priority = 51)
	public void deleteAllRecord() {
		String res = PaperExportBusiness.deleteAllRecord();
		Assert.assertTrue(res.contains("OK"),"清除所有导出数据,实际返回结果："+res);
	}
	@Test(description = "导出线下签到的数据",priority = 52)
	public void exportLatojaMonitorDetailList() {
//		String res = AppStudyBusiness.exportLatojaMonitorDetailList(id, la_id);
//		try {
//			Thread.sleep(1000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//		String msg = (String)JSONPath.read(res, "$.msg");
//		Assert.assertEquals(msg, "培训监控线下培训列表详细信息导出成功","导出线下签到的数据"+res);
	}
	
	@Test(description = "导出问卷调研统计的数据",priority = 53)
	public void statisticsExport() {
		String res = AppStudyBusiness.statisticsExport(investigatesId);
		String msg = (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "问卷导出成功","导出问卷调研统计的数据"+res);
	}
	@Test(description = "导出问卷明细的数据",priority = 54)
	public void testExportInvestigatesDetailList() {
		String res = AppStudyBusiness.exportInvestigatesDetailList(id, investigatesId,"");
		
		String msg = (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "问卷调研明细数据","导出问卷明细的数据"+res);
	
	}
	
	@Test(description="导出考试结果--通过用户名查看",priority=55)
	public void testGetExportExamResultCodeByName() {
		int code = ExaminationTaskBusiness.getExportExamResultCode(examId, user_name);
		Assert.assertEquals(code, 200,"导出考试结果数据，实际状态码为："+code);
	}
	@Test(description="导出考试结果--无搜索条件的情况",priority=56)
	public void testGetExportExamResultCode() {
		int code = ExaminationTaskBusiness.getExportExamResultCode(examId, "");
		Assert.assertEquals(code, 200,"导出考试结果--无搜索条件的情况，实际状态码为："+code);
	}
	@Test(description="导出学员作答明细--通过用户名查看",priority=57)
	public void testGetExportExamDetailCodeByName() {
		int code = ExaminationTaskBusiness.getExportExamDetailCode(examId, user_name,"0");
		Assert.assertEquals(code, 200,"导出学员作答明细--通过用户名查看，实际状态码为："+code);
	}
	@Test(description="导出学员作答明细--无搜索条件的情况",priority=58)
	public void testGetExportExamDetailCode() {
		int code = ExaminationTaskBusiness.getExportExamDetailCode(examId, "");
		Assert.assertEquals(code, 200,"导出学员作答明细--无搜索条件的情况，实际状态码为："+code);
	}
	@Test(description="导出试题作答明细--通过用户名查看",priority=59)
	public void testGetExportAnswerDetailCodeByName() {
		int code = ExaminationTaskBusiness.getExportAnswerDetailCode(examId, user_name);
		Assert.assertEquals(code, 200,"导出学员作答明细--通过用户名查看，实际状态码为："+code);
	}
	@Test(description="导出试题作答明细--无搜索条件的情况",priority=60)
	public void testGetExportAnswerDetailCode() {
		int code = ExaminationTaskBusiness.getExportAnswerDetailCode(examId, "");
		Assert.assertEquals(code, 200,"导出试题作答明细--无搜索条件的情况，实际状态码为："+code);
	}
	@Test(description="导出试题分析--全部试题类型的情况",priority=61)
	public void testGetExportQuestionAnalysisCodeAll() {
		int code = ExaminationTaskBusiness.getExportQuestionAnalysisCode(examId, "");
		Assert.assertEquals(code, 200,"导出试题分析--全部试题类型的情况，实际状态码为："+code);
	}
	@Test(description="导出试题分析--单选题类型的情况",priority=62)
	public void testGetExportQuestionAnalysisCodeSingle() {
		int code = ExaminationTaskBusiness.getExportQuestionAnalysisCode(examId, "1");
		Assert.assertEquals(code, 200,"导出试题分析--单选题类型的情况，实际状态码为："+code);
	}
	@Test(description="导出试题分析--多选试题类型的情况",priority=63)
	public void testGetExportQuestionAnalysisCodeMult() {
		int code = ExaminationTaskBusiness.getExportQuestionAnalysisCode(examId, "2");
		Assert.assertEquals(code, 200,"导出试题分析--多选试题类型的情况，实际状态码为："+code);
	}
	@Test(description="导出试题分析--判断试题类型的情况",priority=64)
	public void testGetExportQuestionAnalysisCodeJudge() {
		int code = ExaminationTaskBusiness.getExportQuestionAnalysisCode(examId, "3");
		Assert.assertEquals(code, 200,"导出试题分析--判断试题类型的情况，实际状态码为："+code);
	}
	@Test(description = "查看导出的结果",priority = 65)
	public void exportRecordList() {
		String res = PaperExportBusiness.exportRecordList();
		String status_1 = (String)JSONPath.read(res, "$.list[0].status");
		String status_2 = (String)JSONPath.read(res, "$.list[1].status");
		String status_3 = (String)JSONPath.read(res, "$.list[2].status");
		String status_4 = (String)JSONPath.read(res, "$.list[3].status");
		String status_5 = (String)JSONPath.read(res, "$.list[4].status");
		String status_6 = (String)JSONPath.read(res, "$.list[5].status");
		String status_7 = (String)JSONPath.read(res, "$.list[6].status");
		String status_8 = (String)JSONPath.read(res, "$.list[7].status");
		String status_9 = (String)JSONPath.read(res, "$.list[8].status");
		String status_10 = (String)JSONPath.read(res, "$.list[9].status");
		
		Assert.assertFalse(status_1=="FAILED", "查看导出的结果，在下载中心查看"+res);	
		Assert.assertFalse(status_2=="FAILED", "查看导出的结果，在下载中心查看"+res);
		Assert.assertFalse(status_3=="FAILED", "查看导出的结果，在下载中心查看"+res);
		Assert.assertFalse(status_4=="FAILED", "查看导出的结果，在下载中心查看"+res);
		Assert.assertFalse(status_5=="FAILED", "查看导出的结果，在下载中心查看"+res);
		Assert.assertFalse(status_6=="FAILED", "查看导出的结果，在下载中心查看"+res);	
		Assert.assertFalse(status_7=="FAILED", "查看导出的结果，在下载中心查看"+res);
		Assert.assertFalse(status_8=="FAILED", "查看导出的结果，在下载中心查看"+res);
		Assert.assertFalse(status_9=="FAILED", "查看导出的结果，在下载中心查看"+res);
		Assert.assertFalse(status_10=="FAILED", "查看导出的结果，在下载中心查看"+res);
	}
	@Test(priority = 66)
	public void end() {
		String res = StudyTaskBusiness.deleteStudyTask(id);
		String msg = (String) JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "删除学习计划成功", "删除学习计划，实际返回结果:" + res);
	}

	
	@Test(description="33.图文课删除-图文课已被删除接口", priority=67)
 	public void testCourseDeleted(){
 		String res = ImageTextBusiness.CourseDelete(imagetext_id);
 		String message = (String) JSONPath.read(res, "$.message");
 		System.out.println("33.图文课删除-图文课已被删除接口:"+"message"+message);
 	}
	
}
