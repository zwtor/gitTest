package exam.cases;

import cn.kxy.examination.business.PaperBusiness;
import cn.kxy.examination.business.PaperExportBusiness;
import init.cases.InitExam;
import com.alibaba.fastjson.JSONPath;
import com.lazy.common.utils.CommonData;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestExportBlankPaper extends InitExam{
	public static  String paperName = "paper" +CommonData.getStringRandom(5);
	String id= "";
	public  String newSummary = "instruction";
	@Test(description = "新增试卷")
	public void testCreatPaper() {
		String res = PaperBusiness.creatPaper(paperName, "instruction", "150", "90", "60",PaperBusiness.getFirstquestionid() , "100", PaperBusiness.getSecondquestionid(), "50");
		String msg = (String) JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "新增试卷成功！" ,"新增试卷返回结果："+res);
	}
	@Test(description = "获取试卷id",priority = 2)
	public void testGetPaperId() {
		String list_res = PaperBusiness.queryList(paperName, "false");
		id = (String)JSONPath.read(list_res, "$.list[0].id");
	}
	
	String paper_export_id = "";
	@Test(description = "清除导出记录",priority = 3)
	public void testDeleteAllRecord() {
		String res = PaperExportBusiness.deleteAllRecord();
		String msg = (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "OK","清除导出记录,实际返回结果:"+res);
	}
	@Test(description = "导出空白记录",priority = 4)
	public void testExportBlankPaper() {
		String res = PaperExportBusiness.exportBlankPaper(id);
		String msg = (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "试卷导出记录已生成","导出空白记录,实际返回结果:"+res);
	}
	@Test(description = "未点击导出记录按钮时导出列表个数",priority = 5)
	public void testExportRecordComposeCount() {
		String res = PaperExportBusiness.exportRecordComposeCount();
		String msg = (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "OK","导出列表个数,实际返回结果:"+res);
	}
	
	@Test(description = "导出列表个数",priority = 6)
	public void testExportRecordComposeCount11() {
		String res = PaperExportBusiness.exportRecordComposeCount();
		String msg = (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "OK","导出列表个数,实际返回结果:"+res);
	}
	
	@Test(description = "导出空白试卷列表",priority = 7)
	public void testExportRecordList() {
		String res = PaperExportBusiness.exportRecordList();
		paper_export_id = (String)JSONPath.read(res, "$.list[0].id");
		String title = (String)JSONPath.read(res, "$.list[0].title");
		Assert.assertNotNull(title, "导出空白试卷列表，实际返回结果："+res);
	}
	@Test(description = "删除单个导出记录",priority = 8)
	public void testDeleteRecord() {
		String res = PaperExportBusiness.deleteRecord(paper_export_id);
		String msg = (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "OK","导出列表个数,实际返回结果:"+res);
		
	}
	
	@Test(description = "同一个试卷再次导出空白记录",priority = 9)
	public void testExportBlankPaperAgain() {
		String res = PaperExportBusiness.exportBlankPaper(id);
		String msg = (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "试卷导出记录已生成","同一个试卷再次导出空白记录,实际返回结果:"+res);
	}
	
	@Test(description = "查看导出的结果",priority = 10)
	public void exportRecordList() {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		String res = PaperExportBusiness.exportRecordList();
		String status = (String)JSONPath.read(res, "$.list[0].status");
		Assert.assertFalse(status=="FAILED", "查看导出的结果:"+res);
	}
	
	
	@Test(description = "删除试卷",priority = 11)
	public void testDeletePaperIdIsNull() {
		String res = PaperBusiness.deletePaper(id);
		String msg = (String) JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "删除试卷成功","删除试卷返回结果："+res);
	}
	@Test(description = "id不存在时，删除单个导出记录",priority = 12)
	public void testDeleteRecordIdIsNotExist() {
		String res = PaperExportBusiness.deleteRecord("5232");
		String msg = (String) JSONPath.read(res, "$.message");
		Assert.assertEquals(msg, "resource not found","id不存在时，删除单个导出记录返回结果："+res);

	}
	@Test(description = "id为null时，删除单个导出记录",priority = 13)
	public void testDeleteRecordIdIsNull() {
		String res = PaperExportBusiness.deleteRecord(null);
		String error = (String) JSONPath.read(res, "$.error");
		Assert.assertEquals(error, "Not Found","id为null时，删除单个导出记录,实际返回结果："+res);
		
	}
}
