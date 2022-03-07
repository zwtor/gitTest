package exam.cases;

import cn.kxy.authentication.business.CertificateBusiness;
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

public class TestReMarking {
		String name = "ReMarking" + CommonData.getStringRandom(5);
		String exam_id= "";
		@Test(description = "初始化重新阅卷的数据",priority = 1)
		public void testCreatRewardExamTask() {
			ExaminationTaskBusiness.creatRewardExamTask("1", "hide", "10", "100", "1", name,
					DateUtil.getRegularDate(0), DateUtil.getRegularDate(2), "false", UserBusiness.getUserId(),
					PaperBusiness.getIdByKeyword(BaseBusiness.pass_paper_name), "60", "0", "false", "2",
					CertificateBusiness.getIdByKeyword(BaseBusiness.certificate_name), "12", "0", "0", "0", "0", "true", "false",
					UserBusiness.getUserId(), "{\"missScore\":4,\"passScore\":6,\"unPassScore\":2}");
			
		}
		
		@Test(description = "提交试卷",priority = 2)
		public void testSubmitPassExam() {
			ExaminationTaskBusiness.submitPassExam(name);
		}
		@Test(description = "获取考试id", priority = 3)
		public void testGetId() {
			exam_id = ExaminationTaskBusiness.getIdByKeyword(name);
		}
		@Test(description = "批阅试卷",priority = 4)
		public void testSubmitMarkingPaper() {
			String res = ExaminationTaskBusiness.getExamPlanPendingList(name);
			String id =(String)JSONPath.read(res, "$.data.paperVo.showQuestionInfo[0].answer.bizQuestionOptionList[0].questionId");
			String res0 = ExaminationTaskBusiness.submitMarkingPaper(exam_id, id, "60");
			String msg = (String)JSONPath.read(res0, "$.msg");
			Assert.assertEquals(msg,"批阅成功",""+res0);
		}
		@Test(description = "查看试题分析页面是否显示重新阅卷按钮",priority = 5)
		public void testQueryAnswerAnalysis() {
			String res = MyExamTaskBusiness.queryAnswerAnalysis(name);
			String repeat_marking = (String)JSONPath.read(res, "$.repeat_marking");
			Assert.assertEquals(repeat_marking, "true",""+res);
		}
		
		@Test(description = "再次批阅试卷",priority = 6)
		public void testSubmitMarkingPaperAgain() {
			String res = ExaminationTaskBusiness.getExamPlanPendingList(exam_id,"1");
			String id =(String)JSONPath.read(res, "$.data.paperVo.showQuestionInfo[0].answer.bizQuestionOptionList[1].questionId");
			String res0 = ExaminationTaskBusiness.submitMarkingPaper(exam_id, id, "60");
			String msg = (String)JSONPath.read(res0, "$.msg");
			Assert.assertEquals(msg,"批阅成功",""+res0);
		}
		@Test(description = "再次批阅试卷后，查看试题分析页面是否显示重新阅卷按钮",priority = 7)
		public void testQueryAnswerAnalysisAgain() {
			String res = MyExamTaskBusiness.queryAnswerAnalysis(name);
			String repeat_marking = (String)JSONPath.read(res, "$.repeat_marking");
			Assert.assertEquals(repeat_marking, "true",""+res);
		}
		@Test(description = "删除考试任务",priority = 8)
		public void deleteExamTask() {
			ExaminationTaskBusiness.deleteExamTask(exam_id);
		}
		String exam_marking_name = "makrkingNumExam"+CommonData.getStringRandom(9);
		String mark_id = "";
		@Test(description = "新增人工阅卷的考试",priority = 9)
		public void creatRewardExamTask() {
			
			ExaminationTaskBusiness.creatRewardExamTask("1", "hide", "10", "100", "1", exam_marking_name,
					DateUtil.getRegularDate(0), DateUtil.getRegularDate(2), "false", UserBusiness.getUserId(),
					PaperBusiness.getIdByKeyword(BaseBusiness.paper_name), "60", "0", "false", "2",
					CertificateBusiness.getIdByKeyword(BaseBusiness.certificate_name), "12", "0", "0", "0", "0", "true", "false",
					UserBusiness.getUserId(), "{\"missScore\":4,\"passScore\":6,\"unPassScore\":2}");
			mark_id = ExaminationTaskBusiness.getIdByKeyword(exam_marking_name);
			MyExamTaskBusiness.submitBlankExamById(mark_id);
		}
		@Test(description = "查看待阅卷列表的信息",priority = 10)
		public void testGetExamPlanPendingList() {
//			String res = ExaminationTaskBusiness.getExamPendingList(mark_id);
//			List<String> list_res = JsonPath.read(res, "$.data.paperVo.showQuestionInfo");
//			Assert.assertTrue(list_res.size()==2,"查看待阅卷列表的信息,待阅卷个数验证"+list_res);
//			Assert.assertTrue(res.contains("Short Answer Questions"),"查看待阅卷列表的信息"+res);
//			Assert.assertTrue(res.contains("JudgementQuestion Answer analysis"),"查看待阅卷列表的信息"+res);
		}
		
		@Test(description = "删除考试任务",priority = 11)
		public void testdeleteExamTask() {
			ExaminationTaskBusiness.deleteExamTask(mark_id);
		}
}
