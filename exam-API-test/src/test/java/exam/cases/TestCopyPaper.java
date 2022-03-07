package exam.cases;

import cn.kxy.examination.business.PaperBusiness;
import com.alibaba.fastjson.JSONPath;
import com.lazy.common.utils.CommonData;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestCopyPaper {
	
	public static  String paperName = "PaperCopy" +CommonData.getStringRandom(5);
	String id= "";
	@Test(description = "新增试卷",priority = 1)
	public void testCreatPaper() {
		String res = PaperBusiness.creatPaper(paperName, "instruction", "150", "90", "60",PaperBusiness.getFirstquestionid() , "100", PaperBusiness.getSecondquestionid(), "50");
		String msg = (String) JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "新增试卷成功！" ,"新增试卷返回结果："+res);
	}
	
	@Test(description = "获取试卷的id",priority = 2)
	public void testGetId() {
		String list_res = PaperBusiness.queryList(paperName, "false");
		id = (String)JSONPath.read(list_res, "$.list[0].id");
	}
	@Test(description = "复制试卷",priority = 3)
	public void testCopyPaper() {
		String res = PaperBusiness.copyPaper(id);
		Assert.assertTrue(res.contains("copied"),"复制试卷"+res);
	}
	
	String first_id = "";
	@Test(description = "获取试卷复制后的id" , priority = 4)
	public void testGetCopyId() {
		String list_res = PaperBusiness.queryList("", "false");
		first_id = (String)JSONPath.read(list_res, "$.list[0].id");
	}
	
	@Test(description = "查看复制的试卷详情", priority = 5)
	public void testQueryInfo() {
		String res = PaperBusiness.queryInfo(first_id);
		String summary = (String) JSONPath.read(res, "$.summary");
		String title = (String) JSONPath.read(res, "$.title");
		Assert.assertNotNull(summary ,"查看复制的试卷详情："+res);
		Assert.assertNotNull(title ,"查看复制的试卷详情："+res);
	}
	
	@Test(description = "删除试卷", priority = 6)
	public void testDeletePaper() {
		String res = PaperBusiness.deletePaper(id);
		String msg = (String) JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "删除试卷成功","删除试卷返回结果："+res);
	}
	@Test(description = "删除复制的试卷", priority = 7)
	public void testDeleteCopyPaper() {
		String res = PaperBusiness.deletePaper(first_id);
		String msg = (String) JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "删除试卷成功","删除复制的试卷返回结果："+res);
	}
}
