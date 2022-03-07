package my.cases;

import cn.kxy.authentication.business.CertificateBusiness;
import cn.kxy.base.business.BaseBusiness;
import cn.kxy.course.resources.bussiness.ArticleBusiness;
import cn.kxy.course.resources.bussiness.CourseBusiness;
import cn.kxy.courseshoppingmall.business.LoginCourseMallListBusiness;
import cn.kxy.examination.business.ExaminationTaskBusiness;
import cn.kxy.examination.business.PaperBusiness;
import cn.kxy.examination.business.PaperExportBusiness;
import cn.kxy.lecturer.business.LecturerListBusiness;
import cn.kxy.my.business.MyBusiness;
import cn.kxy.setting.bussiness.UserBusiness;
import init.cases.InitStudyAuthCourse;
import com.alibaba.fastjson.JSONPath;
import com.lazy.common.utils.CommonData;
import com.lazy.common.utils.DateUtil;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestMy extends InitStudyAuthCourse {

	String exam_my_name = "MyArchivesExam"+CommonData.getStringRandom(3);
	String my_cour_name = "MyCourse"+CommonData.getStringRandom(2);
	String my_course_id = "";
	String exam_id = "";
	
	
	@Test(priority=1)
	public void testAddCourse() {
		CourseBusiness.addCourse(my_cour_name, "1", "this is desription", LecturerListBusiness.getIdByKeyword(outer_name), 
				ArticleBusiness.getIdByKeyword(articlename), "1", "1", "", "0", "3", "draft");
	}
	@Test(priority=2)
	public void testgetIdByKeyword() {
		my_course_id =CourseBusiness.getIdByKeyword(my_cour_name, "unreleased") ;
	}
	
	@Test(priority=3)
	public void testcreatRewardExamTask() {
		 ExaminationTaskBusiness.creatRewardExamTask("1", "hide", "10", "100", "1", exam_my_name,
					DateUtil.getRegularDate(0), DateUtil.getRegularDate(2), "false", UserBusiness.getUserId(),
					PaperBusiness.getIdByKeyword(BaseBusiness.pass_paper_name), "60", "0", "false", "2",
					CertificateBusiness.getIdByKeyword(BaseBusiness.certificate_name), "12", "0", "0", "0", "0", "true", "false",
					UserBusiness.getUserId(), "{\"missScore\":4,\"passScore\":6,\"unPassScore\":2}");
	}
	@Test(priority=4)
	public void testgetExamIdByKeyword() {
		exam_id = ExaminationTaskBusiness.getIdByKeyword(exam_my_name);
	}
	
	@Test(priority=5)
	public void testSubmitPassExam() {
		ExaminationTaskBusiness.submitPassExam(exam_my_name);
	}
	@Test(description="查看个人订单-all",priority=6)
	public void testMyOrderAllPersonal() {
		String res = MyBusiness.queryMyOrder("3", "");
		int total = (int)JSONPath.read(res, "$.total");
		Assert.assertTrue(total>=0, "查看个人全部的订单,实际返回结果："+res);
	}
	@Test(description="查看个人订单-待付款",priority=7)
	public void testMyOrderBuyPersonal() {
		String res = MyBusiness.queryMyOrder("3", "0");
		int total = (int)JSONPath.read(res, "$.total");
		Assert.assertTrue(total>=0, "查看个人待付款的订单,实际返回结果："+res);
	}
	@Test(description="查看个人订单-已付款",priority=8)
	public void testMyOrderPersonal() {
		String res = MyBusiness.queryMyOrder("3", "1");
		int total = (int)JSONPath.read(res, "$.total");
		Assert.assertTrue(total>=0, "查看个人已付款订单,实际返回结果："+res);
	}
	@Test(description="查看企业订单-all",priority=9)
	public void testMyOrderAllEnterprise() {
		String res = MyBusiness.queryMyOrder("2", "");
		int total = (int)JSONPath.read(res, "$.total");
		Assert.assertTrue(total>=0, "查看企业全部的订单,实际返回结果："+res);
	}
	@Test(description="查看企业订单-待付款",priority=10)
	public void testMyOrderBuyEnterprise() {
		String res = MyBusiness.queryMyOrder("2", "0");
		int total = (int)JSONPath.read(res, "$.total");
		Assert.assertTrue(total>=0, "查看企业待付款的订单,实际返回结果："+res);
	}
	@Test(description="查看企业订单-已付款",priority=11)
	public void testMyOrderEnterprise() {
		String res = MyBusiness.queryMyOrder("2", "1");
		int total = (int)JSONPath.read(res, "$.total");
		Assert.assertTrue(total>=0, "查看企业已付款订单,实际返回结果："+res);
	}
	@Test(description="查看我的购买",priority=12)
	public void testQueryMypurchase() {
		String res = MyBusiness.queryMypurchase();
		int total = (int)JSONPath.read(res, "$.total");
		Assert.assertTrue(total>=0, "查看我的购买,实际返回结果："+res);
	}
	@Test(description="查看我的学分总览",priority=13)
	public void testGetScoreSummary() {
		String res = MyBusiness.getScoreSummary();
		int score_total = (int)JSONPath.read(res, "$.totalScore");
		int todayScore = (int)JSONPath.read(res, "$.todayScore");
		Assert.assertNotNull(score_total, "查看我的学分总览,总学分校验，实际返回结果："+res);
		Assert.assertNotNull(todayScore, "查看我的学分总览,今日学分校验，实际返回结果："+res);
	}
	
	@Test(description = "清除所有导出数据",priority = 14)
	public void deleteAllRecord() {
		String res = PaperExportBusiness.deleteAllRecord();
		Assert.assertTrue(res.contains("OK"),"清除所有导出数据,实际返回结果："+res);
	}
	@Test(description="导出我的学分记录",priority=15)
	public void testGetExportScoreRecordCode() {
		int code = MyBusiness.getExportScoreRecordCode();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Assert.assertEquals(code, 200,"导出我的学分记录数据:"+code);
	}
	
	@Test(description="查看我的学分列表，通过时间查询",priority=17)
	public void queryScoreRecordListByTime() {
		String res = MyBusiness.queryScoreRecordList(DateUtil.getTimeStamp(-90, ""), DateUtil.getTimeStamp(-1, ""));
		Assert.assertTrue(res.contains("page"), "查看我的学分列表，通过时间查询，实际返回结果："+res);
	}
	@Test(description="查看我的证书列表-all",priority=18)
	public void testQueryMyCertificateByAllType() {
		String res = MyBusiness.queryMyCertificate("0", "", "", "");
		String source_name = (String)JSONPath.read(res, "$.list[0].source_name");
		String source_type_name = (String)JSONPath.read(res, "$.list[0].source_type_name");
		long create_time = (long)JSONPath.read(res, "$.list[0].create_time");
		Assert.assertNotNull(source_name, "查看我的证书列表-all，实际返回结果："+res);
		Assert.assertNotNull(source_type_name, "查看我的证书列表-all，实际返回结果："+res);
		Assert.assertNotNull(create_time, "查看我的证书列表-all，实际返回结果："+res);
	}
	@Test(description="查看我的证书列表-系统",priority=19)
	public void testQueryMyCertificateBySystemType() {
		String res = MyBusiness.queryMyCertificate("1", "", "", "");
		int total = (int)JSONPath.read(res, "$.total");
		Assert.assertTrue(total>=0, "查看我的证书列表-系统"+res);
	}
	@Test(description="查看我的证书列表-手动",priority=20)
	public void testQueryMyCertificateByHandType() {
		String res = MyBusiness.queryMyCertificate("3", "", "", "");
		int total = (int)JSONPath.read(res, "$.total");
		Assert.assertTrue(total>=0, "查看我的证书列表-手动"+res);
	}
	@Test(description="查看我的证书列表-手动",priority=21)
	public void testQueryMyCertificateByTime() {
		String res = MyBusiness.queryMyCertificate("0",DateUtil.getTimeStamp(-90, ""), DateUtil.getTimeStamp(-1, ""), "");
		int total = (int)JSONPath.read(res, "$.total");
		Assert.assertTrue(total>=0, "查看我的证书列表-手动"+res);
	}
	@Test(description="查看我的证书列表-手动",priority=22)
	public void testQueryMyCertificateByKeyword() {
		String res = MyBusiness.queryMyCertificate("0", "", "", "Coo");
		int total = (int)JSONPath.read(res, "$.total");
		Assert.assertTrue(total>=0, "查看我的证书列表-手动"+res);
	}
	@Test(description="导出我的证书信息",priority=23)
	public void testGetCertificateDetailsCode() {
		int code = MyBusiness.getCertificateDetailsCode();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Assert.assertEquals(code, 200,"导出我的证书信息数据:"+code);
	}
	String pop_course_id ="";
	@Test(description="查看我的收藏列表",priority=24)
	public void testAddCollection() {
		String pop_res = LoginCourseMallListBusiness.getPopularCourses();
		pop_course_id  =(String)JSONPath.read(pop_res, "$.list[0].course_id");
		String res = LoginCourseMallListBusiness.querySelectOneInfo(pop_course_id);
		boolean collection = (boolean)JSONPath.read(res, "$.collection");
		if (collection) {
			LoginCourseMallListBusiness.cancelCollection(pop_course_id);
		}
		String add_res = LoginCourseMallListBusiness.addCollection(pop_course_id);
		String msg = (String)JSONPath.read(add_res, "$.msg");
		
		String coll_res = MyBusiness.queryMyCollection();
		String cour_name = (String)JSONPath.read(coll_res, "$.list[0].name");
		LoginCourseMallListBusiness.cancelCollection(pop_course_id);
		Assert.assertNotNull(cour_name, "我的收藏列表查看"+res);
		Assert.assertEquals(msg, "新增收藏成功","收藏课程，实际返回结果："+add_res);
	}
	@Test(description="查看我的创建列表",priority=25)
	public void testQuerMyCreateCoursesAllList () {
		String res = MyBusiness.querMyCreateCoursesList("", my_cour_name);
		String title = (String)JSONPath.read(res, "$.list[0].title");
		String classify_name = (String)JSONPath.read(res, "$.list[0].classify_name");
		String course_status = (String)JSONPath.read(res, "$.list[0].course_status");
		long update_date = (long)JSONPath.read(res, "$.list[0].update_date");
		Assert.assertEquals(title,my_cour_name, "查询我的创建列表，名称进行校验 "+res);
		Assert.assertEquals(course_status, "draft","查询我的创建列表，课程状态进行校验"+res);
		Assert.assertNotNull(classify_name, "查询我的创建列表，分类进行校验 "+res);
		Assert.assertNotNull(update_date, "查询我的创建列表，更新名称进行校验 "+res);
	}
	@Test(description="查看我的课程列表--草稿状态",priority=26)
	public void testQuerMyCreateCoursesdraftList() {
		String res = MyBusiness.querMyCreateCoursesList("draft", "");
		int total = (int)JSONPath.read(res, "$.total");
		Assert.assertTrue(total>=0, "查看我的课程列表--草稿状态"+res);
	}
	@Test(description="查看我的课程列表--审批中状态",priority=27)
	public void testQuerMyCreateCoursesapprovingList() {
		String res = MyBusiness.querMyCreateCoursesList("approving", "");
		int total = (int)JSONPath.read(res, "$.total");
		Assert.assertTrue(total>=0, "查看我的课程列表--审批中状态"+res);
	}
	@Test(description="查看我的课程列表--拒绝状态",priority=28)
	public void testQuerMyCreateCoursesrejectList() {
		String res = MyBusiness.querMyCreateCoursesList("reject", "");
		int total = (int)JSONPath.read(res, "$.total");
		Assert.assertTrue(total>=0, "查看我的课程列表--拒绝状态"+res);
	}
	@Test(description="查看我的课程列表--发布状态",priority=29)
	public void testQuerMyCreateCoursesreleasedList() {
		String res = MyBusiness.querMyCreateCoursesList("released", "");
		int total = (int)JSONPath.read(res, "$.total");
		Assert.assertTrue(total>=0, "查看我的课程列表--发布状态"+res);
	}
	@Test(description="查看我的课程列表--发布中状态",priority=30)
	public void testQuerMyCreateCoursesreleasingList() {
		String res = MyBusiness.querMyCreateCoursesList("releasing", "");
		int total = (int)JSONPath.read(res, "$.total");
		Assert.assertTrue(total>=0, "查看我的课程列表--发布中状态"+res);
	}
	@Test(description="查看我的课程列表--发布失败状态",priority=31)
	public void testQuerMyCreateCoursesrelease_failedList() {
		String res = MyBusiness.querMyCreateCoursesList("release_failed", "");
		int total = (int)JSONPath.read(res, "$.total");
		Assert.assertTrue(total>=0, "查看我的课程列表--发布失败状态"+res);
	}
	
	@Test(description="查看我的认证档案--查询条件为All",priority=32)
	public void testQueryUserQualificationAllArchives() {
//		String res = MyBusiness.queryUserQualificationArchives("all");
//		String name = (String)JSONPath.read(res, "$.details.list[0].qualification_name");
//		int progress = (int)JSONPath.read(res, "$.details.list[0].progress");
//		String status = (String)JSONPath.read(res, "$.details.list[0].status");
//		String is_get_certificate = (String)JSONPath.read(res, "$.details.list[0].is_get_certificate");
//		int certificate_count = (int)JSONPath.read(res, "$.user.certificate_count");
//		int finished_count = (int)JSONPath.read(res, "$.user.finished_count");
//		int studying_count = (int)JSONPath.read(res, "$.user.studying_count");
//		int approving_count = (int)JSONPath.read(res, "$.user.approving_count");
//		
//		Assert.assertTrue(certificate_count>=0, "查看我的认证档案--All，证书个数进行校验"+res);
//		Assert.assertTrue(finished_count>=0, "查看我的认证档案--All，已完成个数进行校验"+res);
//		Assert.assertTrue(studying_count>=0, "查看我的认证档案--All，进行中个数进行校验"+res);
//		Assert.assertTrue(approving_count>=0, "查看我的认证档案--All，待审核个数进行校验"+res);
//		
//		Assert.assertNotNull(name,"查看我的认证档案--All，名称进行校验"+res);
//		Assert.assertNotNull(progress,"查看我的认证档案--All，进度进行校验"+res);
//		Assert.assertNotNull(status,"查看我的认证档案--All，状态进行校验"+res);
//		Assert.assertNotNull(is_get_certificate,"查看我的认证档案--All，证书是否获得"+res);
	}
	
	@Test(description="查看我的认证档案--进行中",priority=33)
	public void testQueryUserQualificationStudyingArchives() {
		String res = MyBusiness.queryUserQualificationArchives("studying");
		int total = (int)JSONPath.read(res, "$.details.total");
		Assert.assertTrue(total>=0, "查看我的认证档案--进行中"+res);
		
	}
	@Test(description="查看我的认证档案--审批中",priority=34)
	public void testQueryUserQualificationApprovingArchives() {
		String res = MyBusiness.queryUserQualificationArchives("approving");
		int total = (int)JSONPath.read(res, "$.details.total");
		Assert.assertTrue(total>=0, "查看我的认证档案--审批中"+res);
	}
	
	@Test(description="查看我的认证档案--已完成",priority=35)
	public void testQueryUserQualificationFinishedArchives() {
		String res = MyBusiness.queryUserQualificationArchives("finished");
		int total = (int)JSONPath.read(res, "$.details.total");
		Assert.assertTrue(total>=0, "查看我的认证档案--已完成"+res);
	}
	
	@Test(description="查看我的自学档案--all",priority=36)
	public void testQueryUserCourseAllArchives() {
		String res = MyBusiness.queryUserCourseArchives("");
		String name = (String)JSONPath.read(res, "$.details.list[0].course_name");
		long update_time = (long)JSONPath.read(res, "$.details.list[0].update_time");
		int progress = (int)JSONPath.read(res, "$.details.list[0].progress");
		String status = (String)JSONPath.read(res, "$.details.list[0].status");
		int score = (int)JSONPath.read(res, "$.details.list[0].score");
		int finished_count = (int)JSONPath.read(res, "$.user.finished_count");
		int studying_count = (int)JSONPath.read(res, "$.user.studying_count");
		int lscore = (int)JSONPath.read(res, "$.user.score");
		
		Assert.assertTrue(finished_count>=0, "查看我的自学档案--all，完成数"+res);
		Assert.assertTrue(studying_count>=0, "查看我的自学档案--all，进行中的数量"+res);
		Assert.assertTrue(lscore>=0, "查看我的自学档案--all，学分"+res);
		Assert.assertNotNull(name,"查看我的自学档案--All，名称进行校验"+res);
		Assert.assertNotNull(progress,"查看我的自学档案--All，进度进行校验"+res);
		Assert.assertNotNull(status,"查看我的自学档案--All，状态进行校验"+res);
		Assert.assertNotNull(update_time,"查看我的自学档案--All，更新时间进行校验"+res);
		Assert.assertNotNull(score,"查看我的自学档案--All，学分进行校验"+res);
	}
	
	@Test(description="查看我的自学档案--finished",priority=37)
	public void testQueryUserCourseFinishedArchives() {
		String res = MyBusiness.queryUserCourseArchives("finished");
		int total = (int)JSONPath.read(res, "$.details.total");
		Assert.assertTrue(total>=0, "查看我的自学档案--已完成"+res);
	}
	
	@Test(description="查看我的自学档案--进行中",priority=38)
	public void testQueryUserCourseStudyingArchives() {
		String res = MyBusiness.queryUserCourseArchives("studying");
		int total = (int)JSONPath.read(res, "$.details.total");
		Assert.assertTrue(total>=0, "查看我的自学档案--进行中"+res);
	}
	
	@Test(description="导出我的认证档案",priority=39)
	public void testGetUserQualificationArchivesExportCode() {
		int code = MyBusiness.getUserQualificationArchivesExportCode();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Assert.assertEquals(code, 200,"导出我的认证档案:"+code);
	}
	
	@Test(description="导出我的自学档案",priority=40)
	public void testGetUserCourseArchivesExportCode() {
		int code = MyBusiness.getUserCourseArchivesExportCode();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Assert.assertEquals(code, 200,"导出我的自学档案:"+code);
	}
	
	@Test(description="查看我的档案--考试任务",priority=41)
	public void testGetUserTrainArchivesDetailExamList() {
		String res = MyBusiness.getUserTrainArchivesDetailList("","","4");
		String title = (String)JSONPath.read(res, "$.data.detailList.list[0].planName");
		String userTrainBeginTime = (String)JSONPath.read(res, "$.data.detailList.list[0].userTrainBeginTime");
		String taskTerm = (String)JSONPath.read(res, "$.data.detailList.list[0].taskTerm");
		String taskType = (String)JSONPath.read(res, "$.data.detailList.list[0].taskType");
		String progressOrScore = (String)JSONPath.read(res, "$.data.detailList.list[0].progressOrScore");
		String certificateName = (String)JSONPath.read(res, "$.data.detailList.list[0].certificateName");
		String studyScoreStr = (String)JSONPath.read(res, "$.data.detailList.list[0].studyScoreStr");
		Assert.assertNotNull(title, "查看我的档案--考试任务"+res);
		Assert.assertNotNull(userTrainBeginTime, "查看我的档案--考试任务"+res);
		Assert.assertNotNull(taskTerm, "查看我的档案--考试任务"+res);
		Assert.assertNotNull(taskType, "查看我的档案--考试任务"+res);
		Assert.assertNotNull(progressOrScore, "查看我的档案--考试任务"+res);
		Assert.assertNotNull(certificateName, "查看我的档案--考试任务"+res);
		Assert.assertNotNull(studyScoreStr, "查看我的档案--考试任务"+res);
	}
	
	@Test(description="查看我的档案--全部",priority=42)
	public void testGetUserTrainArchivesDetailAllList() {
		String res = MyBusiness.getUserTrainArchivesDetailList("","","0");
		String msg = (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "查询成功","查看我的档案--全部"+res);
	}
	@Test(description="查看我的档案--新员工培训",priority=43)
	public void testGetUserTrainArchivesDetaiNewlList() {
		String res = MyBusiness.getUserTrainArchivesDetailList("","","1");
		String msg = (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "查询成功","查看我的档案--全部"+res);
	}
	@Test(description="查看我的档案--学习任务",priority=44)
	public void testGetUserTrainArchivesDetailStudyList() {
		String res = MyBusiness.getUserTrainArchivesDetailList("","","2");
		String msg = (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "查询成功","查看我的档案--全部"+res);
	}
	@Test(description="查看我的档案--选修任务",priority=45)
	public void testGetUserTrainArchivesDetailEleList() {
		String res = MyBusiness.getUserTrainArchivesDetailList("","","3");
		String msg = (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "查询成功","查看我的档案--全部"+res);
	}
	@Test(description = "查看导出的结果",priority = 46)
	public void exportRecordList() {
		String res = PaperExportBusiness.exportRecordList();
		String status1 = (String)JSONPath.read(res, "$.list[0].status");
		String status2 = (String)JSONPath.read(res, "$.list[1].status");
		String status3 = (String)JSONPath.read(res, "$.list[2].status");
		String status4 = (String)JSONPath.read(res, "$.list[3].status");
		Assert.assertFalse(status1=="FAILED", "查看导出的结果:"+res);
		Assert.assertFalse(status3=="FAILED", "查看导出的结果:"+res);
		Assert.assertFalse(status2=="FAILED", "查看导出的结果:"+res);
		Assert.assertFalse(status4=="FAILED", "查看导出的结果:"+res);
		
		Assert.assertTrue(res.contains("ESELF_STUDY_FILE_DETAIL_EXPORT") ,res);
		Assert.assertTrue(res.contains("AUTH_FILE_EXPORT") ,res);
		Assert.assertTrue(res.contains("EXERCISES_EXPORT") ,res);
		Assert.assertTrue(res.contains("CREDIT_SORT_DETAILS_EXPORT") ,res);
	}
	
	@Test(priority = 47)
	public void end() {
		String delete_res=CourseBusiness.deleteCourse(my_course_id);
		ExaminationTaskBusiness.deleteExamTask(exam_id);
		System.out.println(delete_res);
	}
}
