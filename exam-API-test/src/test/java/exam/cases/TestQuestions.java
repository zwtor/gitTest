package exam.cases;

import cn.kxy.examination.business.ExamTestQuestionsBusiness;
import cn.kxy.examination.business.ExaminationBusines;
import cn.lazy.init.cases.InitExam;
import com.alibaba.fastjson.JSONPath;
import com.lazy.common.utils.CommonData;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestQuestions extends InitExam {
	public String examName = "QuestionBank" + CommonData.getStringRandom(5);

	@BeforeClass
	public void initQue() {
		ExaminationBusines.createQuestionBank(examName, "1", "", "");
	}

	@Test(description = "新建不同类型的试题", dataProvider = "AddQuestions", priority = 1)
	public void testAddQuestions(String title, String type, String difficulty, String analysis, String knowledgePoint,
			String titleImage, String analysisImage, String answer) {

		String res = ExamTestQuestionsBusiness.addQuestions(title, type, difficulty, analysis, knowledgePoint,
				titleImage, analysisImage, answer);
		String msg = (String) JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "试题保存成功", "新建不同类型的试题 为" + type + "  的实际返回结果：" + res);
	}

	@Test(description = "新建试题时对name进行必填校验")
	public void testCheckNameAddQuestions() {
		String res = ExamTestQuestionsBusiness.addQuestions("", "1", "", "", "", "", "",
				"[{\"title\":\"1\",\"id\":\"\"},{\"title\":\"2\",\"isAnswer\":true,\"id\":\"\"}]");
		String msg = (String) JSONPath.read(res, "$.msg");

		Assert.assertEquals(msg, "试题标题不能为空", "新建试题时对name进行必填校验实际返回结果为：" + res);

	}
	
	@Test(description = "新建试题时试题类型不存在时")
	public void testCheckTypeIsExistAddQuestions() {
		String res = ExamTestQuestionsBusiness.addQuestions("aa", "9", "1", "", "", "", "",
				"[{\"title\":\"1\",\"id\":\"\"},{\"title\":\"2\",\"isAnswer\":true,\"id\":\"\"}]");
		String msg = (String) JSONPath.read(res, "$.msg");

		Assert.assertEquals(msg, "试题类型不存在", "新建试题时试题类型不存在时实际返回结果为：" + res);

	}


	@Test(description = "新建试题时对试题类型进行必填校验")
	public void testCheckTypeAddQuestions() {
		String res = ExamTestQuestionsBusiness.addQuestions("fa", "", "", "", "", "", "",
				"[{\"title\":\"1\",\"id\":\"\"},{\"title\":\"2\",\"isAnswer\":true,\"id\":\"\"}]");
		String msg = (String) JSONPath.read(res, "$.msg");

		Assert.assertEquals(msg, "试题类型不能为空", "新建试题时对试题类型进行必填校验实际返回结果为：" + res);

	}

	@Test(description = "新建试题时对试题选项进行必填校验")
	public void testCheckSelectionAddQuestions() {
		String res = ExamTestQuestionsBusiness.addQuestions("fa", "", "", "", "", "", "", "");
		String msg = (String) JSONPath.read(res, "$.msg");

		Assert.assertEquals(msg, "试题选项不能为空", "新建试题时对试题选项进行必填校验实际返回结果为：" + res);

	}

	@Test(dependsOnMethods = "testAddQuestions", description = "根据关键字查询试题列表", priority = 2)
	public void testGetQuestionsList() {
		String res = ExamTestQuestionsBusiness.getQuestionsList("Multiplechoice", "false", "2");
		String msg = (String) JSONPath.read(res, "$.list[0].title");

		Assert.assertEquals(msg, "Multiplechoice", "查询试题列表实际返回结果：" + res);
	}

	@Test(dependsOnMethods = "testAddQuestions", description = "根据不同题型查询试题列表", dataProvider = "DiffTypeQuestionsList")
	public void testDiffTypeQuestionsList(String kind, String type) {
		String res = ExamTestQuestionsBusiness.getQuestionsList("", "false", type);
		int total = (int) JSONPath.read(res, "$.total");

		Assert.assertTrue(total > 0, "根据不同题型查询试题列表实际返回结果：" + res);
	}

	@Test(dependsOnMethods = "testGetQuestionsList", description = "查看单个试题详情", priority = 3)
	public void testGetQuestionsInfo() {
		String res = ExamTestQuestionsBusiness.getQuestionsInfo(ExamTestQuestionsBusiness.getFirstExamId());
		String msg = (String) JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "success", "查看单个试题详情实际返回结果：" + res);
		String title = (String) JSONPath.read(res, "$.data.title");
		Assert.assertTrue(title != null, "查看单个试题详情实际返回结果：" + res);
	}
	
	@Test(dependsOnMethods = "testGetQuestionsInfo", description = "验证是否删除弹框", priority = 4)
	public void testOIsReferenceWithPlan() {
		String res = ExamTestQuestionsBusiness.isReferenceWithPlan(ExamTestQuestionsBusiness.getFirstExamId());
		String msg = (String) JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "success", "验证是否删除弹框实际返回结果：" + res);
	}

	@Test(description = "编辑试题")
	public void testedit() {
		String editTitle = "Multiplechoice";
		String editType = "2";
		String editDifficulty = "2";
		ExamTestQuestionsBusiness.addQuestions("sinagleChoice", "1", "1", "analysis01", "", "", "",
				"[{\"title\":\"one\",\"id\":\"\"},{\"title\":\"two\",\"isAnswer\":true,\"id\":\"\"}]");
		String res = ExamTestQuestionsBusiness.editQuestions(editTitle, editType, editDifficulty, "analysis02", "", "",
				"",
				"[{\"title\":\"02\",\"id\":\"\"},{\"title\":\"03\",\"isAnswer\":true,\"id\":\"\"},{\"title\":\"04\",\"isAnswer\":true,\"id\":\"\"}]",
				ExamTestQuestionsBusiness.getFirstExamId());
		String msg = (String) JSONPath.read(res, "$.msg");
		String info = ExamTestQuestionsBusiness.getQuestionsInfo(ExamTestQuestionsBusiness.getFirstExamId());
		String title = (String) JSONPath.read(info, "$.data.title");
		Integer type = (Integer) JSONPath.read(info, "$.data.type");
		Integer difficulty = (Integer) JSONPath.read(info, "$.data.difficulty");
		ExamTestQuestionsBusiness.deleteQuestions(ExamTestQuestionsBusiness.getFirstExamId());
		Assert.assertEquals(msg, "试题编辑成功", "编辑试题实际返回结果：" + res);
		Assert.assertEquals(title, editTitle, "编辑试题实际返回结果：" + res);
		Assert.assertEquals(String.valueOf(type), editType, "编辑试题实际返回结果：" + res);
		Assert.assertEquals(String.valueOf(difficulty), editDifficulty, "编辑试题实际返回结果：" + res);

	}

	@Test(description = "编辑试题时对试题id进行校验")
	public void testCheckEditId() {
		String res = ExamTestQuestionsBusiness.editQuestions("fa", "", "", "analysis02", "", "",
				"",
				"[{\"title\":\"02\",\"id\":\"\"},{\"title\":\"03\",\"isAnswer\":true,\"id\":\"\"},{\"title\":\"04\",\"isAnswer\":true,\"id\":\"\"}]",
				"");
		String msg = (String) JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "试题Id不能为空", "编辑试题时时对name进行校验实际返回结果为：" + res);
	}

	@Test(dependsOnMethods = "testGetQuestionsInfo", description = "删除试题", priority = 5)
	public void testDeleteQuestions() {
		for (int i = 0; i < 5; i++) {
			String res = ExamTestQuestionsBusiness.deleteQuestions(ExamTestQuestionsBusiness.getFirstExamId());
		}

	}
	@Test(description="移动试题时，对试题id进行校验",dependsOnMethods="testAddQuestions")
	public void testCheckMoveId () {
		String res = ExamTestQuestionsBusiness.moveQuestionBank(ExamTestQuestionsBusiness.getFirstExamId(), "");
		String msg = (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "调整失败,无效的题库或者试题信息","移动试题时，对试题id进行校验实际返回结果："+res);
	}

	@Test(description="移动试题时，对题库id进行校验",dependsOnMethods="testAddQuestions")
	public void testCheckmoveQuestionbankId() {
		String res = ExamTestQuestionsBusiness.moveQuestionBank("", ExaminationBusines.getIdByKeyword(examName));
		String msg = (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "调整失败,无效的题库或者试题信息","移动试题时，对试题id进行校验实际返回结果："+res);
	}
	
	@Test(description="移动试题",dependsOnMethods="testAddQuestions")
	public void testMoveQuestionBank () {
		String res = ExamTestQuestionsBusiness.moveQuestionBank(ExamTestQuestionsBusiness.getFirstExamId(), ExaminationBusines.getIdByKeyword(examName));
		String msg = (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "调整成功","移动试题实际返回结果："+res);
	}
	
	@Test(description = "验证删除试题时，id参数未传时的msg校验", priority = 4)
	public void testCheckIdDeleteQuestions() {
		String res = ExamTestQuestionsBusiness.deleteQuestions("");
		String msg = (String) JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "试题Id不能为空", "验证删除试题时，id参数未传时实际返回结果：" + res);
	}

	@DataProvider(name = "DiffTypeQuestionsList")
	public Object[][] DiffTypeQuestionsList() {
		Object[][] obj = new Object[][] { { "根据单选题类型查看", "1" }, { "根据多选题类型查看", "2" }, { "根据判断题类型查看", "3" },
				{ "根据填空题类型查看", "4" }, { "根据简答题类型查看", "5" } };
		return obj;
	}

	@DataProvider(name = "AddQuestions")
	public Object[][] AddQuestions() {
		Object[][] obj = new Object[][] { { "sinagleChoice", "1", "1", "sinagleChoice Answer analysis", "", "", "",
				"[{\"title\":\"one\",\"id\":\"\"},{\"title\":\"two\",\"id\":\"\"},{\"title\":\"three\",\"isAnswer\":true,\"id\":\"\"},{\"title\":\"four\",\"id\":\"\"}]" },
				{ "Multiplechoice", "2", "2", "Multiplechoice Answer analysis", "", "", "",
						"[{\"title\":\"01\",\"id\":\"\"},{\"title\":\"02\",\"id\":\"\"},{\"title\":\"03\",\"isAnswer\":true,\"id\":\"\"},{\"title\":\"04\",\"isAnswer\":true,\"id\":\"\"}]" },
				{ "JudgementQuestion", "3", "3", "JudgementQuestion Answer analysis", "", "", "",
						"[{\"title\":\"对\",\"id\":\"\"},{\"title\":\"错\",\"isAnswer\":true,\"id\":\"\"}]" },
				{ "1+1=[[2]],2+2=[[4]]", "4", "4", "fill-in-the-blanks test Answer analysis", "", "", "",
						"[{\"title\":\"2\",\"isAnswer\":true},{\"title\":\"4\",\"isAnswer\":true}]" },
				{ "Short Answer Questions", "5", "1", "Short Answer Questions Answer analysis", "", "", "",
						"[{\"title\":\"true\",\"isAnswer\":true,\"id\":\"\"},{\"title\":\"false\",\"isAnswer\":true,\"id\":\"\"}]" } };
		return obj;
	}

	@AfterClass
	public void endClass() {
		String res = ExaminationBusines.queryQuestionBankList(examName, "false", "");
		String id = (String) JSONPath.read(res, "$.data.list[0].id");
		ExaminationBusines.deleteQuestionBank(id);
	}

}
