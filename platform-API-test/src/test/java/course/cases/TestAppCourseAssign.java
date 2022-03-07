package course.cases;

import cn.kxy.course.resources.bussiness.AppCourseBusiness;
import cn.kxy.course.resources.bussiness.ArticleBusiness;
import cn.kxy.course.resources.bussiness.CourseBusiness;
import cn.kxy.lecturer.business.LecturerListBusiness;
import cn.kxy.setting.bussiness.UserBusiness;
import cn.kxy.study.business.MyStudyTask;
import cn.kxy.study.business.StudyTaskBusiness;
import init.cases.InitStudyAuthCourse;
import com.alibaba.fastjson.JSONPath;
import com.lazy.common.utils.CommonData;
import com.lazy.common.utils.DateUtil;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestAppCourseAssign extends InitStudyAuthCourse {

	String app_course_name = "AppCourseAssign"+CommonData.getStringRandom(5);
	
	String course_id = "";
	String user_name = UserBusiness.getUsername();
	@Test (description = "新增课程" , priority = 1)
	public void initAppCourse() {
		String res= CourseBusiness.addCourse(app_course_name, "1", "this is desription", LecturerListBusiness.getIdByKeyword(outer_name), 
				ArticleBusiness.getIdByKeyword(""), "0", "2", UserBusiness.getUserId(), "0", "3", "release");
		course_id = (String)JSONPath.read(res, "$.data[0]");
		Assert.assertTrue(res.contains("新增课程成功"),""+res);
	}
	@Test(description="指派课程",priority=2)
	public void testAssignCourse() {
		String res = AppCourseBusiness.assignCourse(course_id, DateUtil.getRegularDateForHHMM(3));
		String plan_id = (String)JSONPath.read(res, "$.plan_id");
		Assert.assertNotNull(plan_id,"指派课程"+res);
	}
	@Test(description="查看追踪未完成列表",priority=3)
	public void testQueryTrackCourseUnfinishedList() {
		String res = AppCourseBusiness.queryTrackCourseList(course_id, "unfinished");
		Assert.assertTrue(res.contains("end_row"),"查看追踪未完成列表实际返回："+res);
	}
	String study_id = "";
	String my_course_id = "";
	@Test(description="在我的培训列表任务查看指派的课程",priority=4)
	public void testQueryList() {
		String list_res = MyStudyTask.queryList(app_course_name,"0");
		study_id = (String)JSONPath.read(list_res, "$.list[0].id");
		int type = (int)JSONPath.read(list_res, "$.list[0].bizType");
		Assert.assertEquals(type, 3,"在我的培训任务列表查看指派的课程"+list_res);
	}
	@Test(description="对指派的课程进行学习",priority=5)
	public void testQueryInfoSaveProgress() {
		MyStudyTask.queryInfo(study_id);
	}
	@Test(description="查看追踪已完成列表",priority=6)
	public void testQueryTrackCourseList() {
		String res = AppCourseBusiness.queryTrackCourseList(course_id, "finished");
		int progress = (int)JSONPath.read(res, "$.list[0].progress");
		Assert.assertEquals(progress, 0,"查看追踪已完成列表，对进度进行校验，实际返回结果"+res);
	}
	
	@Test(description="查看追踪全部列表",priority=7)
	public void testQueryTrackCourseAllList() {
		String res = AppCourseBusiness.queryTrackCourseList(course_id, "all");
		int progress = (int)JSONPath.read(res, "$.list[0].progress");
		Assert.assertEquals(progress, 0,"查看追踪全部列表，对进度进行校验，实际返回结果"+res);
	}
	@Test(description="查看追踪逾期列表",priority=8)
	public void testQueryTrackCourseOverdueList() {
		String res = AppCourseBusiness.queryTrackCourseList(course_id, "overdue");
		int total = (int)JSONPath.read(res, "$.total");
		Assert.assertNotNull(total,"查看追踪逾期列表，实际返回结果"+res);
	}
	@Test(description="指派课程后，在学习任务列表查看",priority=9)
	public void testGetStudyTaskList() {
		String res = StudyTaskBusiness.getStudyTaskList(app_course_name, "false", "0", "");
		String title = (String)JSONPath.read(res, "$.list[0].title");
		int type = (int)JSONPath.read(res, "$.list[0].bizType");
		Assert.assertEquals(type, 3,"指派课程后，在学习任务列表查看，实际返回结果："+res);
		Assert.assertEquals(title, app_course_name,"指派课程后，在学习任务列表查看，实际返回结果："+res);
	}
	
	@Test(priority = 10)
	public void end () {
		StudyTaskBusiness.deleteStudyTask(study_id);
		CourseBusiness.deleteCourse(course_id);
	}
}
