package extremely.cases;

import cn.kxy.base.business.BaseBusiness;
import cn.kxy.examination.business.ExamTestQuestionsBusiness;
import cn.kxy.examination.business.ExaminationBusines;
import cn.kxy.examination.business.PaperBusiness;
import com.alibaba.fastjson.JSONPath;
import com.lazy.common.utils.CommonData;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TestQuestionBankDelete {

	String question_bank_name = "DeleteQuestionBank"+CommonData.getStringRandom(3);
	String question_bank_id = "";
	String sinagleChoice_title_name = "DeleteSinagleChoice"+CommonData.getStringRandom(5);
	String paper_name1 = "DelePaper"+CommonData.getStringRandom(3);
	String paper_name2 = "DelePaper"+CommonData.getStringRandom(5);
	String question_id = "";
	String paper_id1 = "";
	String paper_id2 = "";
	@BeforeClass
	public void init() {
		ExaminationBusines.createQuestionBank(question_bank_name, "1", "", "");
		String res = ExaminationBusines.queryQuestionBankList(question_bank_name, "false", "");
		question_bank_id = (String)JSONPath.read(res, "$.data.list[0].id");
		ExamTestQuestionsBusiness.addQuestions(sinagleChoice_title_name, question_bank_id,"1", "1", "analysis01", "knowledgepoint-jmeter", "", "",
				"[{\"title\":\"one\",\"id\":\"\"},{\"title\":\"two\",\"isAnswer\":true,\"id\":\"\"}]");
		String res_list = ExamTestQuestionsBusiness.getQuestionsList(sinagleChoice_title_name, question_bank_id, "false", "");
		question_id = (String)JSONPath.read(res_list, "$.list[0].id");
		PaperBusiness.creatPaper(paper_name1, "instruction", "150", "90", "60" ,question_id, "100", PaperBusiness.getSecondquestionid(), "50");
		paper_id1 = PaperBusiness.getIdByKeyword(paper_name1) ;
		
		PaperBusiness.creatPaper(paper_name2, "instruction", "150", "90", "60" ,question_id, "100", PaperBusiness.getSecondquestionid(), "50");
		paper_id2 = PaperBusiness.getIdByKeyword(paper_name2) ;
	}
	
	@Test(description="题库的试题被引用时，isReferenceWithPlan接口判断是否被引用",priority=1)
	public void testIsReferenceWithPlan () {
		String res = ExamTestQuestionsBusiness.isReferenceWithPlan(question_id);
		boolean data = (boolean)JSONPath.read(res, "$.data");
		Assert.assertTrue(data, "题库的试题被引用时，isReferenceWithPlan接口判断是否被引用,此时应返回true"+res);
	}
	
	@Test(description="题库被引用时，delete/check校验是否可以删除",priority=2)
	public void testDeleteCheckData() {
		String res = BaseBusiness.deleteCheckData(question_id,"question");
		String title1 = (String)JSONPath.read(res, "$.list[0].title");
		String title2 = (String)JSONPath.read(res, "$.list[1].title");
		String type_name1 = (String)JSONPath.read(res, "$.list[0].type_name");
		String type_name2 = (String)JSONPath.read(res, "$.list[1].type_name");
		Assert.assertEquals(title1, paper_name1,"delete/check校验是否可以删除，名称进行校验"+res);
		Assert.assertEquals(title2, paper_name2,"delete/check校验是否可以删除，名称进行校验"+res);
		Assert.assertEquals(type_name1, "试卷","delete/check校验是否可以删除，类型名称进行校验"+res);
		Assert.assertEquals(type_name2, "试卷","delete/check校验是否可以删除，类型名称进行校验"+res);
	}
	@Test(description="删除一个试卷后，再次删除题库",priority=3)
	public void testDeleteAgainCheckData () {
		PaperBusiness.deletePaper(paper_id1);
		String res = BaseBusiness.deleteCheckData(question_id,"question");
		int size = (int)JSONPath.read(res, "$.size");
		Assert.assertEquals(size, 1,"删除一个试卷后，再次删除题库，实际返回结果："+res);
	}
	@Test(description="删除最后一个试卷后，再次校验是否可以删除题库",priority=4)
	public void testDeletePaper() {
		PaperBusiness.deletePaper(paper_id2);
		String res = BaseBusiness.deleteCheckData(question_id,"question");
		int size = (int)JSONPath.read(res, "$.size");
		Assert.assertEquals(size, 0,"删除一个试卷后，再次删除题库，实际返回结果："+res);
	}
	@Test(description="题库的试题被引用时，isReferenceWithPlan接口判断是否被引用",priority=5)
	public void testIsReferenceWithPlanBydelete () {
		String res = ExamTestQuestionsBusiness.isReferenceWithPlan(question_id);
		boolean data = (boolean)JSONPath.read(res, "$.data");
		Assert.assertFalse(data, "题库的试题被引用时，isReferenceWithPlan接口判断是否被引用,此时应返回false"+res);
	}
	@Test(description="被引用的模块删除完后，题库下的试题应可删除",priority=6)
	public void testDeleteQuestions() {
		String res = ExamTestQuestionsBusiness.deleteQuestions(question_id);
		String msg = (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "试题删除成功","被引用的模块删除完后，题库下的试题应可删除"+res);
	}
	@AfterClass
	public void end () {
		String res = ExaminationBusines.deleteQuestionBank(question_bank_id);
		String msg = (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "删除成功");
	}
	
}
