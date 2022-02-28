package study.cases;

import cn.kxy.authentication.business.CertificateBusiness;
import cn.kxy.base.business.BaseBusiness;
import cn.kxy.course.resources.bussiness.CourseBusiness;
import cn.kxy.examination.business.PaperBusiness;
import cn.kxy.group.a.business.ImageTextBusiness;
import cn.kxy.investigationresearch.business.QuestionnaireBusiness;
import cn.kxy.setting.bussiness.UserBusiness;
import cn.kxy.study.business.AppStudyBusiness;
import cn.kxy.study.business.MyElectiveTaskBusiness;
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

public class TestMyStudyTask extends InitStudyAuthCourse{

	String study_name = "MyStudyTask"+CommonData.getStringRandom(5);
	String id = "";
	String username = UserBusiness.getUsername();
	String courseId="";
	String resourceId = "";
	String examId = "";
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
	@Test(description = "新增学习计划",priority = 2)
	public void init() {
		String res = StudyTaskBusiness.addMixSettledExamStudyTask(study_name, DateUtil.getTimeStamp(0, ""),
				DateUtil.getTimeStamp(3, ""), CertificateBusiness.getIdByKeyword(cert_name),
				imagetext_id, CourseBusiness.getIdByPage(course_name),
				PaperBusiness.getIdByKeyword(BaseBusiness.pass_paper_name), DateUtil.getRegularDateForHHMM(0),
				DateUtil.getRegularDateForHHMM(2), 
				QuestionnaireBusiness.getIdByKeyword(BaseBusiness.questionnaireName, "release"));
		String msg = (String) JSONPath.read(res, "$.msg");
		System.out.println("this is MyStudyTask module");
		Assert.assertEquals(msg, "新增计划成功！", "添加混合学习任务，实际返回结果:" + res);
	}
	@Test(description="查看我的培训任务列表",priority=3)
	public void testQueryList() {
		String res = MyStudyTask.queryList(study_name,"1");
		id = (String)JSONPath.read(res, "$.list[0].id");
		String name = (String)JSONPath.read(res, "$.list[0].title");
		String creatorName = (String)JSONPath.read(res, "$.list[0].creatorName");
		int process = (int)JSONPath.read(res, "$.list[0].process");
		int award_score = (int)JSONPath.read(res, "$.list[0].award_score");
		Long beginTime = (Long)JSONPath.read(res, "$.list[0].beginTime");
		Long endTime = (Long)JSONPath.read(res, "$.list[0].endTime");
		
		int taskType = (int)JSONPath.read(res, "$.list[0].taskType");
		Assert.assertEquals(taskType, 1,"查看我的培训任务列表--任务类型进行校验，实际返回结果："+res);
		Assert.assertEquals(creatorName, username,"查看我的培训任务列表--创建人进行校验，实际返回结果："+res);
		Assert.assertEquals(award_score, 8,"查看我的培训任务列表,学分进行校验 ，实际返回结果："+res);
		Assert.assertEquals(process, 0,"查看我的培训任务列表，未进行学习时，对进度进行校验，实际返回结果："+res);
		Assert.assertNotNull(beginTime, "查看我的培训任务列表，开始时间进行校验，实际返回结果："+res);
		Assert.assertNotNull(endTime, "查看我的培训任务列表，结束时间进行校验，实际返回结果："+res);
		Assert.assertEquals(name, study_name,"查看我的培训任务列表，标题进行校验，实际返回结果："+res);
	}
	String zip_resourceId = "";
	@Test(description="查看我的培训任务详情",priority=4)
	public void testQueryInfo() {
		String res = MyStudyTask.queryInfo(id);
		String name = (String)JSONPath.read(res, "$.data.title");
		String creatorName = (String)JSONPath.read(res, "$.data.creatorName");
		Long beginTime = (Long)JSONPath.read(res, "$.data.beginTime");
		Long endTime = (Long)JSONPath.read(res, "$.data.endTime");
		int process = (int)JSONPath.read(res, "$.data.process");
		String cour_name = (String)JSONPath.read(res, "$.data.courseInfo[1].title");
		courseId = (String)JSONPath.read(res, "$.data.courseInfo[0].courseId");
		zip_resourceId = (String)JSONPath.read(res, "$.data.courseInfo[0].courseInfo[0].resource_quiz_vo.resource_id");
		resourceId = (String)JSONPath.read(res, "$.data.courseInfo[1].courseId");
		examId = (String)JSONPath.read(res, "$.data.courseInfo[2].examId");
		int totalExamCount = (int)JSONPath.read(res, "$.data.totalExamCount");
		int unExamCount = (int)JSONPath.read(res, "$.data.unExamCount");
		
		String statusName = (String)JSONPath.read(res, "$.data.courseInfo[2].statusName");
		Assert.assertEquals(statusName, "待考试","查看我的培训任务详情，考试状态进行校验，实际返回结果："+res);
		Assert.assertEquals(totalExamCount, 1,"查看我的培训任务详情，考试次数进行校验，实际返回结果："+res);
		Assert.assertEquals(unExamCount, 0,"查看我的培训任务详情，未考次数进行校验，实际返回结果："+res);
		Assert.assertNotNull(beginTime, "查看我的培训任务详情，开始时间进行校验，实际返回结果："+res);
		Assert.assertNotNull(endTime, "查看我的培训任务详情，结束时间进行校验，实际返回结果："+res);
		Assert.assertEquals(process, 0,"查看我的培训任务详情，进度进行校验，实际返回结果："+res);
		Assert.assertEquals(creatorName, username,"查看我的培训任务详情，创建人进行校验，实际返回结果："+res);
		Assert.assertEquals(cour_name, course_name,"查看我的培训任务详情，课程进行校验，实际返回结果："+res);
		Assert.assertEquals(name, study_name,"查看我的培训任务详情，标题进行校验，实际返回结果："+res);
	}
	@Test(description="查看我的培训任务课件详情",priority=5)
	public void testqueryCoursewerInfo() {
		String res = MyElectiveTaskBusiness.queryCourseInfo(courseId);
		String name = (String)JSONPath.read(res, "$.data.name");
		Assert.assertNotNull(name,"查看我的培训任务课件详情，实际返回结果："+res);
	}
	
	@Test(description="保存我的培训任务课件进度",priority=6)
	public void testSaveArtProgress() {
		String res = AppStudyBusiness.saveProgress("100",id, courseId, zip_resourceId);
		int course_progress = (int)JSONPath.read(res, "$.course_progress");
		int total_progress = (int)JSONPath.read(res, "$.total_progress");
		Assert.assertEquals(total_progress, 25,"保存我的培训任务课件进度--总进度，实际返回结果："+res);
		Assert.assertEquals(course_progress, 100,"保存我的培训任务课件进度--课件进度，实际返回结果："+res);
	}
	@Test(description="查看我的培训任务课程详情",priority=7)
	public void testqueryCourseInfo() {
		String res = MyElectiveTaskBusiness.queryCourseInfo(courseId);
		String name = (String)JSONPath.read(res, "$.data.name");
		Assert.assertNotNull(name,"查看我的培训任务课程详情，实际返回结果："+res);
	}
	@Test(description="保存我的培训任务课程进度",priority=7)
	public void testSaveCourseProgress() {
//		String res = AppStudyBusiness.saveProgress("100",id, resourceId,courseId);
//		int course_progress = (int)JSONPath.read(res, "$.course_progress");
//		int total_progress = (int)JSONPath.read(res, "$.total_progress");
//		Assert.assertEquals(total_progress, 50,"保存我的培训任务课程进度--总进度，实际返回结果："+res);
//		Assert.assertEquals(course_progress, 100,"保存我的培训任务课程进度--课件进度，实际返回结果："+res);
	}
	@Test(description = "在我的培训任务查看学习任务考试详情", priority = 8)
	public void testQueryExamInfo() {
		String res = AppStudyBusiness.queryExamInfo(examId);
		String exam_title = (String) JSONPath.read(res, "$.title");
		String summary = (String) JSONPath.read(res, "$.summary");
		Assert.assertEquals(summary, "this is a summary", "在我的培训任务查看学习任务考试详情，实际返回结果：" + res);
		Assert.assertEquals(exam_title, "exam", "在我的培训任务查看学习任务考试详情，实际返回结果：" + res);
	}
	@Test(description = "在我的培训任务提交考试", priority = 9)
	public void testsubmitBlankExam() {
		String res = AppStudyBusiness.submitBlankExam(examId);
		String msg = (String) JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "提交试卷成功!", "在我的培训任务提交考试，实际返回结果：" + res);
	}
	@Test(description="查看全部培训任务",priority= 10)
	public void testQueryAllList() {
		String res = MyStudyTask.queryList(study_name,"0");
		int total = (int)JSONPath.read(res, "$.total");
		Assert.assertTrue(total>=0, "查看全部培训任务，实际返回结果："+res);
	}
	@Test(description="查看已完成培训任务",priority=11)
	public void testQueryFinishedList() {
		String res = MyStudyTask.queryList(study_name,"2");
		int total = (int)JSONPath.read(res, "$.total");
		Assert.assertTrue(total>=0, "查看已完成培训任务，实际返回结果："+res);
	}
	@Test(description = "删除学习任务",priority = 12)
	public void deleteStudyTask() {
		String res = StudyTaskBusiness.deleteStudyTask(id);
		String msg = (String) JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "删除学习计划成功", "删除考试任务，实际返回结果:" + res);
	}
	
	@Test(description="33.图文课删除-图文课已被删除接口", priority=16)
 	public void testCourseDeleted(){
 		String res = ImageTextBusiness.CourseDelete(imagetext_id);
 		String message = (String) JSONPath.read(res, "$.message");
 		System.out.println("33.图文课删除-图文课已被删除接口:"+"message"+message);
 	}
}
