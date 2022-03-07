package exam.cases;

import cn.kxy.examination.business.PaperBusiness;
import cn.kxy.setting.bussiness.ClassificationBusines;
import cn.lazy.init.cases.InitExam;
import com.alibaba.fastjson.JSONPath;
import com.lazy.common.utils.CommonData;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestPaperCases extends InitExam{

	public static  String paperName = "paper" +CommonData.getStringRandom(5);
	
	public static  String editname = "pper" + CommonData.getStringRandom(5);
	
	String id= "";
	
	public  String newSummary = "instruction";
	@Test(description = "新增试卷")
	public void testCreatPaper() {
		String res = PaperBusiness.creatPaper(paperName, "instruction", "150", "90", "60",PaperBusiness.getFirstquestionid() , "100", PaperBusiness.getSecondquestionid(), "50");
		String msg = (String) JSONPath.read(res, "$.msg");
		String list_res = PaperBusiness.queryList(paperName, "false");
		id = (String)JSONPath.read(list_res, "$.list[0].id");
		Assert.assertEquals(msg, "新增试卷成功！" ,"新增试卷返回结果："+res);
	}
	
	@Test(description ="新增试卷时，对名称进行校验")
	public void testCheckNamePaper() {
		String res = PaperBusiness.creatPaper("", "instruction", "150", "90", "60",PaperBusiness.getFirstquestionid() , "100", PaperBusiness.getSecondquestionid(), "50");
		String msg = (String) JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "试卷名称不能为空！" ,"新增试卷时，对名称进行校验实际返回结果："+res);
	}
	
	@Test(description ="新增试卷时，对及格线进行校验")
	public void testCheckNamePassline() {
		
		String res = PaperBusiness.creatPaper("paper", "instruction", "150", "90", "",PaperBusiness.getFirstquestionid() , "100", PaperBusiness.getSecondquestionid(), "50");
		String msg = (String) JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "试卷及格线不能为空！" ,"新增试卷时，对及格线进行校验实际返回结果："+res);
	}
	
	@Test(description = "编辑试卷",dependsOnMethods = "testCreatPaper")
	public void testEditPaper() {
		String res = PaperBusiness.editPaper(editname, "instruction", "150", "90", "60",PaperBusiness.getFirstquestionid() , "100", PaperBusiness.getSecondquestionid(), "50",id);
		String msg = (String) JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "修改试卷成功！" ,"修改试卷返回结果："+res);
	}
	
	@Test(description = "编辑试卷时，不传Id",dependsOnMethods = "testCreatPaper")
	public void testCheckIdEditPaper() {
		String res = PaperBusiness.editPaper(editname, "instruction", "150", "90", "60",PaperBusiness.getFirstquestionid() , "100", PaperBusiness.getSecondquestionid(), "50","");
		String msg = (String) JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "试卷id不能为空！" ,"编辑试卷时，不传Id返回结果："+res);
	}
	
	@Test(description = "编辑试卷时，不传name",dependsOnMethods = "testCreatPaper")
	public void testCheckNameEditPaper() {
		String res = PaperBusiness.editPaper("", "instruction", "150", "90", "60",PaperBusiness.getFirstquestionid() , "100", PaperBusiness.getSecondquestionid(), "50",id);
		String msg = (String) JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "考试名称不能为空！" ,"编辑试卷时，不传name返回结果："+res);
	}
	
	@Test(description = "编辑试卷时，不传及格线",dependsOnMethods = "testCreatPaper")
	public void testCheckPasslineEditPaper() {
		String res = PaperBusiness.editPaper("test", "instruction", "150", "90", "",PaperBusiness.getFirstquestionid() , "100", PaperBusiness.getSecondquestionid(), "50",id);
		String msg = (String) JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "试卷及格线不能为空！" ,"编辑试卷时，不传name返回结果："+res);
	}
	
	
	@Test(description = "指定keyword查询试卷",dependsOnMethods = "testEditPaper")
	public void testQueryKeywordPaper() {
		String res = PaperBusiness.queryList(editname, "false");
		String title = (String) JSONPath.read(res, "$.list[0].title");
		int totle = (int) JSONPath.read(res, "$.total");
		Assert.assertEquals(title,editname,"查询试卷返回结果："+res);
		Assert.assertTrue(totle==1,"查询试卷返回结果："+res);
	}
	
	@Test(description = "查询试卷",dependsOnMethods = "testEditPaper")
	public void testQueryPaper() {
		String res = PaperBusiness.queryList("", "false");
		int totle = (int) JSONPath.read(res, "$.total");
		Assert.assertTrue(totle>1,"查询试卷返回结果："+res);
	}
	
	@Test(description = "不同参数查看试卷",dataProvider="QueryPaperByType")
	public void testQueryPaperByType(String type ,String keyword,String courseClassify,String onlySeeme) {
		String res = PaperBusiness.queryList(keyword,courseClassify, onlySeeme);
		int totle = (int) JSONPath.read(res, "$.total");
		Assert.assertTrue(totle>=0,"查询试卷返回结果："+res);
	}
	
	@Test(description = "试卷预览",dependsOnMethods ="testQueryPaper")
	public void testQueryInfo() {
		String res = PaperBusiness.queryInfo(id);
		String summary = (String) JSONPath.read(res, "$.summary");
		String title = (String) JSONPath.read(res, "$.title");
		Assert.assertEquals(summary, newSummary ,"试卷预览试卷返回结果："+res);
		Assert.assertEquals(title, editname ,"预览试卷返回结果："+res);
	}
	@Test(description = "删除试卷",dependsOnMethods = "testQueryInfo")
	public void testDeletePaper() {
		int start = PaperBusiness.getPaperTotal();
		String res = PaperBusiness.deletePaper(id);
		String msg = (String) JSONPath.read(res, "$.msg");
		int end = PaperBusiness.getPaperTotal();
		Assert.assertEquals(msg, "删除试卷成功","删除试卷返回结果："+res);
		Assert.assertTrue(start-end==1);
	}
	
	@Test(description = "删除试卷")
	public void testDeletePaperIdIsNull() {
		String res = PaperBusiness.deletePaper("1");
		String msg = (String) JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "删除试卷失败","删除试卷返回结果："+res);
	}
	
	@DataProvider(name = "QueryPaperByType")
	public Object[][] queryPaperByType() {
		Object[][] obj = new Object[][] { { "不填关键字进行查询", "",ClassificationBusines.getPrimaryId(),"false" },{ "不填分类进行查询", "","","false" },
			{ "不填OnlySeeme时", "",ClassificationBusines.getPrimaryId(),"" },{ "都不填写时", "","","" } };
		return obj;
	}
}
