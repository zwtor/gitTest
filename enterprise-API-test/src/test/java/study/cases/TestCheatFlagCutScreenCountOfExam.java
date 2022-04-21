package study.cases;

import cn.kxy.base.business.BaseBusiness;
import cn.kxy.examination.business.ExaminationBusines;
import cn.kxy.study.business.AppStudyBusiness;
import cn.kxy.study.business.StudyTaskBusiness;
import com.alibaba.fastjson.JSONPath;
import com.lazy.common.utils.CommonData;
import com.lazy.common.utils.DateUtil;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test(groups = {"studyProject"})
public class TestCheatFlagCutScreenCountOfExam {

	String id = "";
	@Test(description="新增带有考试学习任务",priority= 1)
	public void testForbidCheatFlagCutScreen() {
		String title = "CheatFlagCutScreen"+CommonData.getStringRandom(5);
		StudyTaskBusiness.addCheatFlagCutScreenCountOfExam(title, DateUtil.getTimeStamp(0, ""),  DateUtil.getTimeStamp(3, ""),
				 ExaminationBusines.getIdByKeyword(BaseBusiness.examPassName),"0", "-1");
		String res = StudyTaskBusiness.getStudyTaskList(title, "true", "0", "","");
		id= (String)JSONPath.read(res, "$.list[0].id");
		
	}
	@Test(description="加载资源",priority= 2)
	public void loadResource() {
		AppStudyBusiness.loadResource(id);
	}
	String info_res = "";
	@Test(description="查看学习任务详情",priority= 3)
	public void queryInfo() {
		info_res = AppStudyBusiness.queryInfo(id);
		
	}
	@Test(description="检测防切屏状态",priority= 4)
	public void queryExamListInfo() {
		String exam_id = (String)JSONPath.read(info_res, "$.stages[0].course_list[0].id");
		String exam_res = AppStudyBusiness.queryExamListInfo(exam_id);
		int cut_screen_count = (int)JSONPath.read(exam_res, "$.cut_screen_count");
		Assert.assertEquals(cut_screen_count, -1,""+exam_res);
	}
	@Test(description="删除任务",priority= 5)
	public void deleteStudyTask() {
		StudyTaskBusiness.deleteStudyTask(id);
	}
}
