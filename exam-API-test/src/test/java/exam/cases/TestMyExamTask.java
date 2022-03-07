package exam.cases;

import cn.kxy.examination.business.ExaminationTaskBusiness;
import cn.kxy.examination.business.MyExamTaskBusiness;
import cn.kxy.setting.bussiness.UserBusiness;
import init.cases.InitExam;
import com.alibaba.fastjson.JSONPath;
import com.lazy.common.utils.CommonData;
import com.lazy.common.utils.DateUtil;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.math.BigDecimal;

public class TestMyExamTask extends InitExam{
	
	public static String base_exam_name = "regExamTask"+CommonData.getStringRandom(3);
	
	@BeforeClass
	public void initMyExamtask() {
		ExaminationTaskBusiness.creatExamTask("1", "show", "60", "100", "2",base_exam_name,
				DateUtil.getRegularDate(0), DateUtil.getRegularDate(2), "false",
				UserBusiness.getUserId(),ExaminationTaskBusiness.getPaperId(), "60", "", "false", "1", "0", "60",
				"0", "0", "0", "0", "false", "false", UserBusiness.getUserId());
	}

	@Test(description = "查询我的考试任务列表",dataProvider = "QueryMyExamList",priority = 1) 
	public void testQueryMyExamList(String type,String status ,int exp) {
		String res = MyExamTaskBusiness.queryMyExamTask(status, "");
		int total = (int)JSONPath.read(res, "$.total");
		Assert.assertTrue(total>=exp,"查询我的考试任务列表,类型为："+type +"  实际返回结果:"+res);
	}
	@Test(description= "检查是否可以考试",priority = 2)
	public void testCheckIsCanExam() {
		String res = MyExamTaskBusiness.checkIsCanExam(base_exam_name);
		String can_exam = (String)JSONPath.read(res,"$.can_exam");
		Assert.assertEquals(can_exam, "true","检查是否可以考试实际返回结果:"+res);
	}
	@Test(description="交卷",dependsOnMethods ="testCheckIsCanExam")
	public void testSubmitBlankExam() {
		String res = MyExamTaskBusiness.submitBlankExam(base_exam_name);
		String msg= (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "提交试卷成功!","交卷实际返回结果："+res);
	}
	
	@Test(description="查看答案解析",dependsOnMethods ="testSubmitBlankExam")
	public void testQueryAnswerAnalysis() {
		String res = MyExamTaskBusiness.queryAnswerAnalysis(base_exam_name);
		String title= (String)JSONPath.read(res, "$.analysis[0].title");
		Assert.assertTrue(title!=null,"查看答案解析实际返回结果："+res);
	}
	
	@Test(description = "查询考试结果",dependsOnMethods= "testQueryAnswerAnalysis")
	public void testQueryExamResult() {
		String res = MyExamTaskBusiness.queryExamResult(base_exam_name);
		BigDecimal score  = (BigDecimal)JSONPath.read(res, "$.score");
		BigDecimal c = new BigDecimal("0.0");
		Assert.assertEquals(score,c, "查询考试结果实际返回："+res);
	}
	
	@DataProvider(name = "QueryMyExamList")
	public Object[][] QueryMyExamList() {
		Object[][] obj = new Object[][] { { "全部", "all", 0 }, { "未完成", "unfinished", 0 }, { "及格", "passed", 0 }, { "不及格", "failed", 0 } , { "阅卷中", "scoring", 0 }, { "缺考", "absence", 0 }};
		return obj;
	}
	@AfterClass
	public void endTest () {
		ExaminationTaskBusiness.deleteExamTask(ExaminationTaskBusiness.getIdByKeyword(base_exam_name));
	}
	
}
