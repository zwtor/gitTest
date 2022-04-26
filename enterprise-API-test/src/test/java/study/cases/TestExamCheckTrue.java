package study.cases;

import cn.kxy.base.business.BaseBusiness;
import cn.kxy.examination.business.MyExamTaskBusiness;
import cn.kxy.examination.business.PaperBusiness;
import cn.kxy.study.business.ExamOptimizationStudy;
import cn.kxy.study.business.MyStudyTask;
import cn.kxy.study.business.StudyTaskBusiness;
import com.alibaba.fastjson.JSONPath;
import com.lazy.common.utils.CommonData;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test(groups = {"studyProject"})
public class TestExamCheckTrue {

	String title  = "ExamStudy"+CommonData.getStringRandom(5);
	String id = "";
	@Test(description = "新增考试优化的学习任务",priority = 1)
	public void addExamStudy() {
		String res = ExamOptimizationStudy.addExamStudy(title, PaperBusiness.getIdByKeyword(BaseBusiness.pass_paper_name));
		String msg = (String) JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "新增计划成功！", "新增考试优化的学习任务，实际返回结果:" + res);
	}
	@Test(description="查看我的培训任务列表",priority=2)
	public void testQueryList() {
		String res = MyStudyTask.queryList(title,"1");
		id = (String)JSONPath.read(res, "$.list[0].id");
	}
	String exam_id= "";
	@Test(description="查看我的培训任务详情",priority=3)
	public void testQueryInfo() {
		String res = MyStudyTask.queryInfo(id);
		exam_id = (String)JSONPath.read(res, "$.data.courseInfo[0].examId");
	}
	@Test(description = "提交试卷",priority = 4)
	public void submitBlankExam() {
		String res = MyExamTaskBusiness.submitBlankExamById(exam_id);
		String msg = (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "提交试卷成功!",""+res);
	}
	
	@Test(description = "不显示答案解析，只显示对错",priority = 5)
	public void queryExamResultById() {
		String res = MyExamTaskBusiness.queryExamResultById(exam_id);
		String mark_type = (String)JSONPath.read(res, "$.mark_type");
		
		int show_answer = (int)JSONPath.read(res, "$.show_answer");
		Assert.assertEquals(mark_type, "system_mark");
		Assert.assertEquals(show_answer, 2,"不显示答案解析，只显示对错"+res);
	}
	
	@Test(description = "删除学习任务",priority = 6)
	public void deleteStudyTask() {
		String res = StudyTaskBusiness.deleteStudyTask(id);
		String msg = (String) JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "删除学习计划成功", "删除考试任务，实际返回结果:" + res);
	}
}
