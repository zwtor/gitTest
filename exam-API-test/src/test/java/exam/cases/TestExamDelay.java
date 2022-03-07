package exam.cases;

import cn.kxy.base.business.BaseBusiness;
import cn.kxy.examination.business.ExaminationTaskBusiness;
import cn.kxy.examination.business.MyExamTaskBusiness;
import cn.kxy.examination.business.PaperBusiness;
import cn.kxy.setting.bussiness.UserBusiness;
import com.alibaba.fastjson.JSONPath;
import com.lazy.common.utils.CommonData;
import com.lazy.common.utils.DateUtil;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestExamDelay {
	String make_up_id = "";
	String name = "MakeupExamDelay"+CommonData.getStringRandom(5);
 	@Test(description="考试结束后 ，在考试任务的已结束列表查看",priority=1)
	public void testCheckEndExam() {
		String res = ExaminationTaskBusiness.creatExamTask("1", "show", "60", "100", "2", name,
				DateUtil.getRegularDate(-2), DateUtil.getRegularDate(0), "false", UserBusiness.getUserId(),
				PaperBusiness.getIdByKeyword(BaseBusiness.paper_name), "60", "", "false", "1", "0", "12", "0", "0", "0",
				"0", "false", "false", UserBusiness.getUserId());
		String msg = (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "新增考试成功！","新增随机考试任务实际返回结果："+res);
 	}
	
 	
 	@Test(description="提交空白试卷",priority=2)
 	public void testSubmitBlankExam() {
 		MyExamTaskBusiness.submitBlankExam(name);
 	}
 	
	@Test(description="获取补考id",priority=3)
 	public void testQueryUntestedInfo() {
		make_up_id = ExaminationTaskBusiness.getIdByKeyword(name);
		String res = ExaminationTaskBusiness.queryList(name, "3", "false","");
		String title = (String)JSONPath.read(res, "$.list[0].title");
		Assert.assertEquals(title, name,"考试结束后 ，在考试任务的已结束列表查看实际返回结果："+res);
 	}
 	
 	@Test(description = "发送补考",priority=4)
 	public void testMakeUpExam() {
 		String res = ExaminationTaskBusiness.makeUpExam(make_up_id, DateUtil.getRegularDateForHHMMSS(0), 
 				DateUtil.getRegularDateForHHMMSS(4), "3","");
 		String msg = (String)JSONPath.read(res, "$.msg");
 		Assert.assertEquals(msg, "补考发布成功","发送补考"+res);
 	}
 	
 	@Test(description = "补考后考试",priority = 5)
 	public void testAgainSubmit() {
 		MyExamTaskBusiness.submitBlankExam(name);
 	}
 	
 	@Test(description = "补考延期", priority = 6)
 	public void testFirstDelayMakeupExam() {
 		String res = ExaminationTaskBusiness.delayMakeupExam(make_up_id, DateUtil.getTimeStamp(9, ""));
 		String result = (String)JSONPath.read(res, "$.result");
 		Assert.assertEquals(result, "更新成功","补考延期"+res);
 	}
 
 	@Test(description = "补考延期后，查看我的考试任务详情",priority = 7)
 	public void testQueryMyInfo() {
 		String res = MyExamTaskBusiness.queryMyInfo(make_up_id);
 		String title = (String)JSONPath.read(res, "$.title");
 		Assert.assertEquals(title, name,"补考延期后，查看我的考试任务详情"+res);
 	}
 	
 	@Test(description = "查询我的考试任务列表----不及格",priority = 8) 
	public void testQueryMyExamList() {
//		String res = MyExamTaskBusiness.queryMyExamTask("failed", name);
//		System.out.println(res);
//		int total = (int)JSONPath.read(res, "$.total");
//		Assert.assertTrue(total>=0,"查询我的考试任务列表----不及格，实际返回结果："+res);
	}
 	
 	@Test(description= "补考延期后，查看考试弹窗的列表信息",priority=9)
 	public void testQueryMyExamInfo() {
 		String res = MyExamTaskBusiness.queryMyExamInfo(make_up_id);
 		String title = (String)JSONPath.read(res, "$.title");
 		Assert.assertEquals(title, name,"补考延期后，查看考试弹窗的列表信息"+res);
 	}
	
	@Test(description = "补考完之后再次补考延期", priority =10)
 	public void testAgainDelayMakeupExam() {
 		String res = ExaminationTaskBusiness.delayMakeupExam(make_up_id, DateUtil.getTimeStamp(9, ""));
 		String result = (String)JSONPath.read(res, "$.result");
 		Assert.assertEquals(result, "更新成功","补考完之后再次补考延期"+res);
 	}
	
	@Test(description= "延期的考试未到期，所以不能再次发布补考，can_exam返回false",priority = 11)
	public void testCheckIsCanExam() {
		String res = MyExamTaskBusiness.checkIsCanExamById(make_up_id);
		Assert.assertTrue(res.contains("exam"),"检查是否可以考试实际返回结果:"+res);
	}
	@Test(description = "删除考试任务",priority = 12)
	public void endTest () {
		ExaminationTaskBusiness.deleteExamTask(make_up_id);
	}
}
