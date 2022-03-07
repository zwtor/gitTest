package extremely.cases;

import init.cases.InitStudyAuthCourse;
import com.lazy.common.utils.CommonData;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TestClassificationDelete extends InitStudyAuthCourse{

	String class_name = "Biology"+CommonData.getStringRandom(3);
	String quote_course_name = "QuoteCourse"+CommonData.getStringRandom(3);
    String question_bank_name = "QuoteQuestionBank"+CommonData.getStringRandom(4);
    String ques_name = "QuoteQuestionnaire"+CommonData.getStringRandom(3);
    String course_id = "";
	String class_id = "";
	String art_id = "";
	String question_bank_id = "";
	String ques_id = "";
	@BeforeClass
	public void init() {
//		//获取初始化文章id
//		ClassificationBusines.addPrimaryClassification(class_name);
//		class_id = ClassificationBusines.getPrimaryId();
//		String res= CoursewareBusiness.queryPictureArtList(articlename, "released");
//		art_id= (String)JSONPath.read(res, "$.list[0].course_id");
//		//添加课程
//		CourseBusiness.addCourse(quote_course_name,class_id, "1", "this is desription", LecturerListBusiness.getIdByKeyword(outer_name), 
//				art_id, "0", "2", UserBusiness.getUserId(), "0", "3", "release");
//		String course_res = CourseBusiness.courseManageList(quote_course_name, "all", "released","","","all");
//		course_id = (String)JSONPath.read(course_res, "$.list[0].course_id");
//		//新增题库
//		ExaminationBusines.createQuestionBank(question_bank_name,class_id, "1", "", "");
//		String que_res = ExaminationBusines.queryQuestionBankList(question_bank_name, "false", "");
//		question_bank_id = (String)JSONPath.read(que_res, "$.data.list[0].id");
//		//新增问卷
//		QuestionnaireBusiness.addNormalQuestionnaire(ques_name,class_id, "this is a description", "release");
//		ques_id = QuestionnaireBusiness.getIdByKeyword(ques_name,"release");
	}
	
	@Test(description="分类被引用时，delete/check校验是否可以删除",priority=1)
	public void testDeleteCheckData() {
//		String res = BaseBusiness.deleteCheckData(class_id, "classify");
//		String bank_type_name = (String)JSONPath.read(res, "$.list[0].type_name");
//		String course_type_name = (String)JSONPath.read(res, "$.list[1].type_name");
//		String ques_type_name = (String)JSONPath.read(res, "$.list[2].type_name");
//		String bank_title = (String)JSONPath.read(res, "$.list[0].title");
//		String course_title = (String)JSONPath.read(res, "$.list[1].title");
//		String ques_title = (String)JSONPath.read(res, "$.list[2].title");
//		Assert.assertEquals(bank_type_name, "题库","分类被引用时，delete/check校验是否可以删除,题库类型进行校验："+res);
//		Assert.assertEquals(course_type_name, "课程","分类被引用时，delete/check校验是否可以删除,课程类型进行校验："+res);
//		Assert.assertEquals(ques_type_name, "问卷","分类被引用时，delete/check校验是否可以删除,问卷类型进行校验："+res);
//		Assert.assertEquals(bank_title,question_bank_name ,"分类被引用时，delete/check接口，题库标题进行校验"+res);
//		Assert.assertEquals(course_title,quote_course_name,"分类被引用时，delete/check接口，课程标题进行校验"+res);
//		Assert.assertEquals(ques_title, ques_name,"分类被引用时，delete/check接口，问卷标题进行校验"+res);
	}
	@Test(description= "问卷删除后，再次删除分类",priority=2)
	public void testCancelQuestionnaire() {
//		QuestionnaireBusiness.cancelQuestionnaire(ques_name, "release");
//		String class_res = BaseBusiness.deleteCheckData(class_id, "classify");
//		String res = QuestionnaireBusiness.deleteQuestionnaire(ques_name, "draft");
//		String msg = (String)JSONPath.read(res, "$.msg") ;
//		String bank_title = (String)JSONPath.read(class_res, "$.list[0].title");
//		String course_title = (String)JSONPath.read(class_res, "$.list[1].title");
//		Assert.assertEquals(msg, "问卷删除成功","实际返回结果："+res);
//		Assert.assertEquals(bank_title,question_bank_name ,"问卷删除后，再次删除分类，delete/check接口，题库标题进行校验"+res);
//		Assert.assertEquals(course_title,quote_course_name,"问卷删除后，再次删除分类，delete/check接口，课程标题进行校验"+res);
	}
	@Test(description="删除课程后，再次删除分类",priority=3)
	public void testDeleteCourse() {
//		String res = CourseBusiness.deleteCourse(course_id);
//		String class_res = BaseBusiness.deleteCheckData(class_id, "classify");
//		String bank_title = (String)JSONPath.read(class_res, "$.list[0].title");
//		String msg = (String)JSONPath.read(res, "$.msg");
//		Assert.assertEquals(bank_title,question_bank_name ,"课程删除后，再次删除分类，delete/check接口，题库标题进行校验"+res);
//		Assert.assertEquals(msg, "删除课程成功","删除课程实际返回结果："+res);
	}
	@Test(description="删除题库后，再次删除分类",priority=4)
	public void testDeleteQuestionBank() {
//		String res = ExaminationBusines.deleteQuestionBank(question_bank_id);
//		String msg = (String)JSONPath.read(res, "$.msg");
//		Assert.assertEquals(msg, "删除成功");
	}
	@Test(description="被引用的模块删除完后，分类应可删除",priority=5)
	public void testDeleteClassification() {
//		String res = ClassificationBusines.deleteClassification(class_id);
//		String msg =(String)JSONPath.read(res, "$.msg");
//		Assert.assertEquals(msg, "分类删除成功","被引用的模块删除完后，分类应可删除,实际返回结果："+res);
	}
	
}
