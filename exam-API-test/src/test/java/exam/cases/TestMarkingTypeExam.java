package exam.cases;

import cn.kxy.authentication.business.CertificateBusiness;
import cn.kxy.base.business.BaseBusiness;
import cn.kxy.examination.business.ExaminationTaskBusiness;
import cn.kxy.examination.business.MyExamTaskBusiness;
import cn.kxy.examination.business.PaperBusiness;
import cn.kxy.setting.bussiness.UserBusiness;
import com.alibaba.fastjson.JSONPath;
import com.jayway.jsonpath.JsonPath;
import com.lazy.common.utils.CommonData;
import com.lazy.common.utils.DateUtil;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class TestMarkingTypeExam {

	String marking_type_name = "exam_marking_type"+CommonData.getStringRandom(5);
	String user_id = UserBusiness.getUserId();
	String paper_id = PaperBusiness.getIdByKeyword(BaseBusiness.paper_name);
	String cert_id = CertificateBusiness.getIdByKeyword(BaseBusiness.certificate_name) ;
	String short_blank_id = "";
	@Test (description = "新增含有填空和简答题的考试",priority = 1)
	public void testShortBlankAddExam() {
		ExaminationTaskBusiness.creatRewardExamTask("1", "hide", "10", "100", "1", marking_type_name,
				DateUtil.getRegularDate(0), DateUtil.getRegularDate(2), "false", user_id ,
				paper_id, "60", "0", "false", "2",cert_id, "12", "0", "0", "0", "0", "true", "false",
				user_id, "{\"missScore\":4,\"passScore\":6,\"unPassScore\":2}");
	}
	@Test(description = "获取简答和填空id",priority = 2)
	public void testGetshortblankid() {
		short_blank_id = ExaminationTaskBusiness.getIdByKeyword(marking_type_name);
	}
	@Test(description = "交卷",priority = 3)
	public void testsubmitFailBlankExam() {
		MyExamTaskBusiness.submitBlankExam(marking_type_name);
	}
	@SuppressWarnings("unchecked")
	@Test(description = "查看阅卷是否含有填空，简答题",priority = 4)
	public void testGetExamPlanPendingList() {
		String res = ExaminationTaskBusiness.getExamPlanPendingList(short_blank_id, "");
		List<String> list = (List<String>)JsonPath.read(res, "$.data.paperVo.showQuestionInfo");
		String title = (String)JsonPath.read(res, "$.data.paperVo.showQuestionInfo[0].title");
		Assert.assertEquals(list.size(), 2,"查看阅卷是否含有填空，简答题"+res);
		Assert.assertNotNull(title,"查看阅卷是否含有填空，简答题"+res);
	}
	@Test(description="删除考试任务",priority = 5)
	public void deleteExamTask() {
		String res = ExaminationTaskBusiness.deleteExamTask(short_blank_id);
		String msg = (String) JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "删除任务成功", "删除考试任务返回结果：" + res);
	}
	
	String short_type_name = "exam_short_type"+CommonData.getStringRandom(5);
	String short_type_id = "";
	@Test (description = "新增含有简答题的考试",priority = 6)
	public void testShortAddExam() {
		ExaminationTaskBusiness.creatRewardExamTask("1", "hide", "10", "100", "1", short_type_name,
				DateUtil.getRegularDate(0), DateUtil.getRegularDate(2), "false", user_id ,
				paper_id, "60", "0", "false", "4",cert_id, "12", "0", "0", "0", "0", "true", "false",
				user_id, "{\"missScore\":4,\"passScore\":6,\"unPassScore\":2}");
	}
	@Test(description = "获取简答",priority = 7)
	public void testGetshortid() {
		short_type_id = ExaminationTaskBusiness.getIdByKeyword(short_type_name);
	}
	@Test(description = "交卷",priority = 8)
	public void testsubmiShortExam() {
		MyExamTaskBusiness.submitBlankExam(short_type_name);
	}
	@SuppressWarnings("unchecked")
	@Test(description = "查看阅卷是否含简答题",priority = 9)
	public void testGetExamShortPlanPendingList() {
		String res = ExaminationTaskBusiness.getExamPendingList(short_type_id);
		List<String> list = (List<String>)JsonPath.read(res, "$.data.paperVo.showQuestionInfo");
		String title = (String)JsonPath.read(res, "$.data.paperVo.showQuestionInfo[0].title");
		Assert.assertEquals(list.size(), 1,"查看阅卷是否含有简答题"+res);
		Assert.assertEquals(title,"Short Answer Questions","查看阅卷是否含有简答题"+res);
	}
	@Test(description="删除含有简单题的考试任务",priority=10)
	public void deleteShortExamTask() {
		String res = ExaminationTaskBusiness.deleteExamTask(short_type_id);
		String msg = (String) JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "删除任务成功", "删除考试任务返回结果：" + res);
	}
	
	String blank_type_name = "exam_blank_type"+CommonData.getStringRandom(5);
	String blank_type_id = "";
	@Test (description = "新增含有填空题的考试",priority = 11)
	public void testblankAddExam() {
		ExaminationTaskBusiness.creatRewardExamTask("1", "hide", "10", "100", "1", blank_type_name,
				DateUtil.getRegularDate(0), DateUtil.getRegularDate(2), "false", user_id ,
				paper_id, "60", "0", "false", "3",cert_id, "12", "0", "0", "0", "0", "true", "false",
				user_id, "{\"missScore\":4,\"passScore\":6,\"unPassScore\":2}");
	}
	@Test(description = "获取填空题id",priority = 12)
	public void testGetBlankid() {
		blank_type_id = ExaminationTaskBusiness.getIdByKeyword(blank_type_name);
	}
	@Test(description = "交卷",priority = 13)
	public void testsubmiBlankExam() {
		MyExamTaskBusiness.submitBlankExam(blank_type_name);
	}
	@SuppressWarnings("unchecked")
	@Test(description = "查看阅卷是否含填空题",priority = 14)
	public void testGetExamBlankPlanPendingList() {
		String res = ExaminationTaskBusiness.getExamPendingList(blank_type_id);
		List<String> list = (List<String>)JsonPath.read(res, "$.data.paperVo.showQuestionInfo");
		String title = (String)JsonPath.read(res, "$.data.paperVo.showQuestionInfo[0].title");
		Assert.assertEquals(list.size(), 1,"查看阅卷是否含有填空题"+res);
		Assert.assertEquals(title,"1+1=(),2+2=()","查看阅卷是否含有填空题"+res);
	}
	@Test(description="删除含有填空题的考试任务",priority=15)
	public void deleteBlankExamTask() {
		String res = ExaminationTaskBusiness.deleteExamTask(blank_type_id);
		String msg = (String) JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "删除任务成功", "删除含有填空题的考试任务：" + res);
	}
	
}
