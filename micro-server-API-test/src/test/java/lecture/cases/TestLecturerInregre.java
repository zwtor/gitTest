package lecture.cases;

import cn.kxy.base.business.BaseBusiness;
import cn.kxy.examination.business.PaperExportBusiness;
import cn.kxy.lecturer.business.LecturerListBusiness;
import init.cases.InitStudyAuthCourse;
import com.alibaba.fastjson.JSONPath;
import com.lazy.common.utils.CommonData;
import com.lazy.common.utils.DateUtil;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestLecturerInregre extends InitStudyAuthCourse{
	
	String outer_name = "LKMKHB" + CommonData.getStringRandom(5);
	
	@Test(description="新增外部讲师",priority=1)
	public void testAddOutsideLecturer() {
		String res = LecturerListBusiness.addLecturer(outer_name,"1", "13526231231","outside" ,"", BaseBusiness.outlecturerLevel, "auto", "jmeter");
		String id02 =(String)JSONPath.read(res, "$.id");
		Assert.assertTrue(!id02.isEmpty(), "新增外部讲师实际返回结果："+res);
	}
	String id = "";
	@Test(description = "获取讲师id",priority = 2)
	public void getLecturerId() {
		id = LecturerListBusiness.getIdByKeyword(outer_name);
	}
	
	@Test(description = "导入外部讲师积分",priority = 3)
	public void testAddIntegralLecturers() {
		String res = LecturerListBusiness.addIntegralLecturers(id, "5");
		Assert.assertTrue(res.contains("true"),"手动导入积分,实际返回结果："+res);
	}
	
	@Test(description = "查看导入的讲师积分-----输入时间查询",priority = 5)
	public void testQueryIntegralDetailsByTime() {
		String res = LecturerListBusiness.queryIntegralDetails(DateUtil.getTimeStamp(-7, ""), DateUtil.getTimeStamp(0, ""), id);
		Assert.assertTrue(res.contains("total"),"查看导入的讲师积分-----输入时间查询"+res);
	}
	
	@Test(description = "清除所有导出数据",priority = 6)
	public void deleteAllRecord() {
		String res = PaperExportBusiness.deleteAllRecord();
		Assert.assertTrue(res.contains("OK"),"清除所有导出数据,实际返回结果："+res);
	}
	
	@Test(description = "导出讲师积分详情",priority = 7)
	public void testExportIntegralList() {
//		String res = LecturerListBusiness.exportIntegralList(id,DateUtil.getTimeStamp(-7, ""), DateUtil.getTimeStamp(0, ""));
//		Assert.assertTrue(res.contains("导出列表成功"),"导出讲师积分详情"+res);
	}
	
	@Test(description = "导出讲师列表",priority = 8)
	public void testExportLectureListAll() {
//		String res =  LecturerListBusiness.exportLectureList("","");
//		Assert.assertTrue(res.contains("讲师列表汇总明细导出成功"),"导出讲师列表，实际返回结果："+res);
	}
	@Test(description = "通过关键字---导出讲师列表",priority = 9)
	public void testExportLectureListByKeyWord() {
//		String res =  LecturerListBusiness.exportLectureList(outer_name,"");
//		Assert.assertTrue(res.contains("讲师列表汇总明细导出成功"),"导出讲师列表，实际返回结果："+res);
	}
	
	@Test(description = "通过未启用状态---导出讲师列表",priority = 10)
	public void testExportLectureListByCloseStatus() {
//		String res =  LecturerListBusiness.exportLectureList("","0");
//		Assert.assertTrue(res.contains("讲师列表汇总明细导出成功"),"导出讲师列表，实际返回结果："+res);
	}
	
	@Test(description = "通过已启用状态---导出讲师列表",priority = 11)
	public void testExportLectureListByStatus() {
//		String res =  LecturerListBusiness.exportLectureList("","1");
//		Assert.assertTrue(res.contains("讲师列表汇总明细导出成功"),"导出讲师列表，实际返回结果："+res);
	}
	
	@Test(description = "导出讲师列表，在下载中心查看",priority  = 12)
	public void exportRecordList() {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		String res = PaperExportBusiness.exportRecordList();
		String status = (String)JSONPath.read(res, "$.list[0].status");
		String status_1 = (String)JSONPath.read(res, "$.list[1].status");
		String status_2 = (String)JSONPath.read(res, "$.list[2].status");
		String status_3 = (String)JSONPath.read(res, "$.list[3].status");
		Assert.assertFalse(status=="FAILED", "查看导出的结果:"+res);
		Assert.assertFalse(status_1=="FAILED", "查看导出的结果:"+res);
		Assert.assertFalse(status_2=="FAILED", "查看导出的结果:"+res);
		Assert.assertFalse(status_3=="FAILED", "查看导出的结果:"+res);
	}
	
//	ArrayList<String> list ;
	@Test(description = "查看导入的讲师积分---不输入时间查询",priority = 13)
	public void testQueryIntegralDetails() {
//		try {
//			Thread.sleep(3000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//		String res = LecturerListBusiness.queryIntegralDetails("", "", id);
//		Assert.assertTrue(res.contains("积分导入"),"验证是否导入成功"+res);
//		Assert.assertTrue(res.contains("add  integral lecturers"),"验证是否导入成功"+res);
	}
	
	@Test(description = "撤回讲师积分",priority = 14)
	public void testCountermandIntegral() {
//		String res = LecturerListBusiness.countermandIntegral(list);
//		Assert.assertTrue(res.contains("成功"),"撤回讲师积分,实际返回结果："+res);
	}
	
	@Test(description = "删除讲师",priority = 15)
	public void deleteLecturerByKeyword() {
		String res = LecturerListBusiness.deleteLecturerByKeyword(outer_name);
		String msg = (String)JSONPath.read(res, "$.deleted");
		Assert.assertEquals(msg, "true","删除讲师实际返回结果："+res);
	}
	
}
