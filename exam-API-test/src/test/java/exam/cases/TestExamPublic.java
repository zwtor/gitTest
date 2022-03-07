package exam.cases;

import cn.kxy.base.business.BaseBusiness;
import cn.kxy.examination.business.ExaminationTaskBusiness;
import cn.kxy.examination.business.PaperBusiness;
import cn.kxy.setting.bussiness.UserBusiness;
import com.alibaba.fastjson.JSONPath;
import com.lazy.common.utils.CommonData;
import com.lazy.common.utils.DateUtil;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestExamPublic {

	String id = "";
	@Test(description = "查看考试未完成列表" , priority =1)
	public void queryUnpublishedList() {
		String res = ExaminationTaskBusiness.queryList("", "0", "false","unpublished");
		int total = (int) JSONPath.read(res, "$.total");
		Assert.assertTrue(total >= 0, "查看考试未完成列表返回结果：" + res);
	}
	
	@Test(description = "查看考试已完成列表" , priority =2)
	public void queryPublishedList() {
		String res = ExaminationTaskBusiness.queryList("", "0", "false","published");
		int total = (int) JSONPath.read(res, "$.total");
		Assert.assertTrue(total >= 0, "查看考试已完成列表返回结果：" + res);
	}
	
	String exam_task_name = "PublicExam" + CommonData.getStringRandom(6);
	@Test(description = "新增考试",priority = 3)
	public void creatExamTask() {
		ExaminationTaskBusiness.creatExamTask("1", "show", "60", "100", "2", exam_task_name,
				DateUtil.getRegularDate(0), DateUtil.getRegularDate(2), "false", UserBusiness.getUserId(),
				PaperBusiness.getIdByKeyword(BaseBusiness.paper_name), "60", "", "false", "1", "0", "12", "0", "0", "0",
				"0", "false", "false", UserBusiness.getUserId());
	}
	
	@Test(description = "获取考试id",priority = 4)
	public void getId() {
		id = ExaminationTaskBusiness.getIdByName(exam_task_name);
	}
	
	@Test(description= "取消发布考试",priority =5)
	public void canclePublishExam() {
		String res = ExaminationTaskBusiness.publishExam(id,"unpublished");
		Assert.assertTrue(res.contains("msg"),"取消发布考试"+res);
	}
	
	@Test(description= "发布考试",priority =6)
	public void publishExam() {
		String res = ExaminationTaskBusiness.publishExam(id,"published");
		Assert.assertTrue(res.contains("msg"),"发布考试"+res);

	}
	@Test(description = "删除考试",priority = 7)
	public void deleteExamTask () {
		ExaminationTaskBusiness.deleteExamTask(id);
	}
	
}
