package course.cases;

import cn.kxy.course.resources.bussiness.CourseBusiness;
import cn.kxy.examination.business.PaperExportBusiness;
import com.alibaba.fastjson.JSONPath;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestCourseExport {

	@Test(description = "清除所有导出数据",priority = 1)
	public void deleteAllRecord() {
		String res = PaperExportBusiness.deleteAllRecord();
		Assert.assertTrue(res.contains("OK"),"清除所有导出数据,实际返回结果："+res);
	}
	
	@Test (description="导出已发布课程数据",priority=2)
	public void testGetcourseAllExportCode() {
		int code = CourseBusiness.getcourseExportCode("released","all");
		Assert.assertEquals(code, 200,"导出未发布课程数据:"+code);
	}
	@Test (description="导出已发布的原创课程数据",priority=3)
	public void testGetcourseOriginalExportCode() {
		int code = CourseBusiness.getcourseExportCode("released","original");
		Assert.assertEquals(code, 200,"导出已发布的原创课程数据:"+code);
	}
	@Test (description="导出已发布的非原创课程数据",priority=4)
	public void testGetcoursenotOriginalExportCode() {
		int code = CourseBusiness.getcourseExportCode("released","notOriginal");
		Assert.assertEquals(code, 200,"导出已发布的非原创课程数据:"+code);
	}
	
	@Test (description="导出已发布的采购课程数据",priority=5)
	public void testGetcoursenotPlatformExportCode() {
		int code = CourseBusiness.getcourseExportCode("released","platform");
		Assert.assertEquals(code, 200,"导出已发布的采购课程数据:"+code);
	}
	@Test (description="导出未发布课程数据",priority=6)
	public void getcourseExportCode() {
		int code = CourseBusiness.getcourseExportCode("unreleased","all");
		Assert.assertEquals(code, 200,"导出未发布课程数据:"+code);
	}
	
	@Test(description = "导出课程后，在下载中心查看",priority = 7)
	public void exportRecordList() {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		String res = PaperExportBusiness.exportRecordList();
		String status = (String)JSONPath.read(res, "$.list[0].status");
		String status1 = (String)JSONPath.read(res, "$.list[1].status");
		String status2 = (String)JSONPath.read(res, "$.list[2].status");
		String status3 = (String)JSONPath.read(res, "$.list[3].status");
		String status4 = (String)JSONPath.read(res, "$.list[4].status");
		Assert.assertFalse(status=="FAILED", "导出课程后，在下载中心查看"+res);	
		Assert.assertFalse(status1=="FAILED", "导出课程后，在下载中心查看"+res);
		Assert.assertFalse(status2=="FAILED", "导出课程后，在下载中心查看"+res);
		Assert.assertFalse(status3=="FAILED", "导出课程后，在下载中心查看"+res);
		Assert.assertFalse(status4=="FAILED", "导出课程后，在下载中心查看"+res);
		Assert.assertTrue(res.contains("COURSE_DETAILS_EXPORT") ,"导出课程后，在下载中心查看"+res);
	}
}
