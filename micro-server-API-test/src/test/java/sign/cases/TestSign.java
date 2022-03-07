package sign.cases;

import cn.kxy.examination.business.PaperExportBusiness;
import cn.kxy.setting.bussiness.UserBusiness;
import cn.kxy.sign.business.SignBusiness;
import com.alibaba.fastjson.JSONPath;
import com.jayway.jsonpath.JsonPath;
import com.lazy.common.utils.DateUtil;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestSign {

	@Test(description="员工进行签到",priority = 1)
	public void testSign() {
		String res = SignBusiness.sign();
		System.out.println("this is a Sign module");
		String msg = (String)JSONPath.read(res, "$.msg");
		if ("has signed"==msg) {
			Assert.assertEquals(msg,"has signed","签到成功后再次签到"+res);
		}else if ("success"==msg) {
			Assert.assertEquals(msg,"success","当天第一次签到成功"+res);
		}
	}
	
	@Test(description="移动端查看签到列表",priority = 2)
	public void testQuerySignList() {
		String res = SignBusiness.querySignList();
		Assert.assertTrue(res.contains("list"),"查看签到列表实际返回结果"+res);
	}
	@Test(description="查看签到记录",priority = 3)
	public void testQuerySignRecords() {
		String res = SignBusiness.querySignRecords("202007");
		String msg = (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "success","查看签到记录实际返回结果："+res);
	}
	
	@Test(description="web端根据时间查看签到列表",priority = 4)
	public void testWebQuerySignListByTime() {
		String res = SignBusiness.querySignList(DateUtil.getTimeStamp(-7, ""),DateUtil.getTimeStamp(0, ""),"");
		Assert.assertTrue(res.contains("list"),"查看签到列表实际返回结果"+res);
	}
	@Test(description="web端根据时间查看签到列表",priority = 5)
	public void testWebQuerySignListByName() {
		String res = SignBusiness.querySignList("","",UserBusiness.getUsername());
		Assert.assertTrue(res.contains("list"),"查看签到列表实际返回结果"+res);
	}
	@Test(description = "查看签到弹窗",priority = 6)
	public void testQuerySignPop() {
		String res = SignBusiness.querySignPop();
		Assert.assertTrue(res.contains("code"),"查看签到弹窗,实际返回结果;"+res);
	}
	
	@Test(description = "清除所有导出数据",priority = 7)
	public void deleteAllRecord() {
		String res = PaperExportBusiness.deleteAllRecord();
		Assert.assertTrue(res.contains("OK"),"清除所有导出数据,实际返回结果："+res);
	}
	
	@Test(description = "根据时间查询导出数据" , priority = 8)
	public void testDownloadSignByTime() {
		String res = SignBusiness.downloadSign(DateUtil.getTimeStamp(-7, ""),DateUtil.getTimeStamp(0, ""),"");
		String msg = (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg,"签到记录已生成","查看签到弹窗,实际返回结果;"+res);
	}
	@Test(description = "根据名称查询导出数据" , priority = 9)
	public void testDownloadSignByName() {
		String res = SignBusiness.downloadSign("","",UserBusiness.getUsername());
		String msg = (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg,"签到记录已生成","查看签到弹窗,实际返回结果;"+res);
	}
	
	@Test(description = "查看导出的结果",priority = 10)
	public void exportRecordList() {
		String res = PaperExportBusiness.exportRecordList();
		String status1 = (String)JSONPath.read(res, "$.list[0].status");
		String status2 = (String)JSONPath.read(res, "$.list[1].status");
		Assert.assertFalse(status1=="FAILED", "查看导出的结果:"+res);	
		Assert.assertFalse(status2=="FAILED", "查看导出的结果:"+res);
	}
	@Test(description = "生成模板一的海报",priority =11)
	public void generateFirstPoster() {
		String res = SignBusiness.generatePoster("1");
		String url = JsonPath.read(res, "$.data.url");
		Assert.assertNotNull(url,"生成模板一的海报,"+res);
	}
	
	@Test(description = "生成模板二的海报",priority =12)
	public void generateSecondPoster() {
		String res = SignBusiness.generatePoster("2");
		String url = JsonPath.read(res, "$.data.url");
		Assert.assertNotNull(url,"生成模板二的海报,"+res);
	}
}
