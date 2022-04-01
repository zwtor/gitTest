package exam.cases;

import cn.kxy.base.business.BaseBusiness;
import cn.kxy.examination.business.ExaminationTaskBusiness;
import cn.kxy.examination.business.PaperBusiness;
import cn.kxy.examination.business.TimerExamTaskBusiness;
import cn.kxy.my.business.MyBusiness;
import init.cases.InitExam;
import com.alibaba.fastjson.JSONPath;
import com.lazy.common.utils.CommonData;
import com.lazy.common.utils.DateUtil;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestTimerExamTask extends InitExam{
	String exam_task_name = "Timerexam" + CommonData.getStringRandom(4);
	int start_score =0;
	String exam_id_01 = "";
	String time_id_01 = "";
	@Test(description = "新增定时考试任务",priority=1)
	public void testCreatTimerExamTask() {
		start_score= MyBusiness.getMyTotalScore();
		String res = TimerExamTaskBusiness.creatTimerOtherExamTask(PaperBusiness.getIdByKeyword(BaseBusiness.paper_name),"1",exam_task_name,"false","1","0","0",BaseBusiness.certificate_name);
		try {
			Thread.sleep(61000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(res);
		System.out.println("---------------");
		res = ExaminationTaskBusiness.queryList(exam_task_name, "0", "false","");
		System.out.println(res);
		System.out.println("---------------");
		exam_id_01 = (String)JSONPath.read(res, "$.list[0].id");
		time_id_01 = TimerExamTaskBusiness.getIdByKeyword(exam_task_name);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("exam id: " + exam_id_01);
		System.out.println("time id: " + time_id_01);
	}
	
	@Test(description = "定时考试任务，催考提醒", priority=2 )
	public void testRemindExam() {
		String res01 = ExaminationTaskBusiness.remindExam(exam_id_01, "2");
		String msg = (String) JSONPath.read(res01, "$.msg");
		Assert.assertEquals(msg, "成功通知到1位考生", "催考提醒返回结果：" + res01);
	}
	@Test(description = "延期考试任务",priority=3)
	public void testDelayExamTask() {
		String res01 = ExaminationTaskBusiness.delayExamTask(DateUtil.getRegularDate(5),exam_id_01);
		String msg = (String) JSONPath.read(res01, "$.msg");
		Assert.assertEquals(msg, "延期成功", "延期考试任务的返回结果：" + res01);
	}
	@Test(description="定时考试任务交卷",priority=4)
	public void testTimerSubmitExam() {
//		String res = MyExamTaskBusiness.submitFailBlankExam(exam_task_name);
//		String msg = (String)JSONPath.read(res, "$.msg");
//		Assert.assertEquals(msg, "提交试卷成功!","定时考试任务交卷实际返回结果："+res);
	}
	@Test(description = "删除定时考试任务",priority = 5)
	public void endTest () {
		TimerExamTaskBusiness.deleteTimerExamTask(time_id_01);
		ExaminationTaskBusiness.deleteExamTask(exam_id_01);
	}
}
