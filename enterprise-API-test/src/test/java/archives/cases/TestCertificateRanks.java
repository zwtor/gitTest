package archives.cases;

import cn.kxy.archives.business.CertificateRanksBusiness;
import cn.kxy.examination.business.PaperExportBusiness;
import cn.kxy.setting.bussiness.UserBusiness;
import com.alibaba.fastjson.JSONPath;
import com.lazy.common.utils.DateUtil;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestCertificateRanks {
	String user_name = UserBusiness.getUsername();
	@Test(description="根据用户名查看证书列表",priority=1)
	public void testGetCertificateRankList() {
		String res = CertificateRanksBusiness.getCertificateRankList(user_name, "", "");
		String username = (String)JSONPath.read(res, "$.list[0].user_info.name");
		Assert.assertEquals(username, user_name,""+res);
	}
	@Test(description="根据时间查看证书列表",priority=2)
	public void testGetCertificateRankListByTime() {
		String res = CertificateRanksBusiness.getCertificateRankList("", DateUtil.getTimeStamp(-90, ""),  DateUtil.getTimeStamp(-1, ""));
		System.out.println(res);
		int certificate_count = (int)JSONPath.read(res, "$.list[0].certificate_count");
		Assert.assertTrue(certificate_count>=0,"根据时间查看证书列表"+res);
	}
	@Test(description="查看证书详情--all类型",priority=3)
	public void testQueryCertificateAllDetails() {
		String res = CertificateRanksBusiness.queryCertificateDetails("0", "", "");
		Assert.assertTrue(res.contains("total"),"根据时间查看证书列表"+res);
	}
	
	@Test(description="查看证书详情--系统颁发",priority=4)
	public void testQueryCertificateSystemDetails() {
		String res = CertificateRanksBusiness.queryCertificateDetails("1", "", "");
		int total = (int)JSONPath.read(res, "$.total");
		Assert.assertNotNull(total, "查看证书详情--系统颁发:"+res);
	}
	@Test(description="查看证书详情--手动颁发",priority=5)
	public void testQueryCertificateHandDetails() {
		String res = CertificateRanksBusiness.queryCertificateDetails("3", "", "");
		int total = (int)JSONPath.read(res, "$.total");
		Assert.assertNotNull(total, "查看证书详情--手动颁发:"+res);
	}
	@Test(description = "清除所有导出数据",priority = 6)
	public void deleteAllRecord() {
		String res = PaperExportBusiness.deleteAllRecord();
		Assert.assertTrue(res.contains("OK"),"清除所有导出数据,实际返回结果："+res);
	}
	@Test(description="导出证书排行榜--用户名筛选",priority=7)
	public void testgetExportCertificateCodeByUsername() {
		int code = CertificateRanksBusiness.getExportCertificateCodeByUsername();
		Assert.assertEquals(code, 200,"导出证书排行榜--用户名筛选:"+code);
	}
	@Test(description="导出证书排行榜--时间筛选",priority=8)
	public void testGetExportScoreRankCode() {
		int code = CertificateRanksBusiness.getExportCertificateCodeByTime(DateUtil.getTimeStamp(-90, ""), DateUtil.getTimeStamp(-1, ""));
		Assert.assertEquals(code, 200,"导出证书排行榜--时间筛选:"+code);
	}
	
	@Test(description = "查看导出的结果",priority = 9)
	public void exportRecordList() {
		String res = PaperExportBusiness.exportRecordList();
		String status = (String)JSONPath.read(res, "$.list[0].status");
		Assert.assertFalse(status=="FAILED", "查看导出的结果:"+res);
	}
	
	@Test(description="导出证书排行榜",priority=10)
	public void testGetExportScoreRakde() {
//		int code = CertificateRanksBusiness.getExportCertificateCodeByTime(DateUtil.getTimeStamp(-90, ""), DateUtil.getTimeStamp(-1, ""));
//		Assert.assertEquals(code, 200,"导出证书排行榜--时间筛选:"+code);
	}
	@Test(description="导出证书排行榜",priority=11)
	public void testGetExportScoreankde() {
//		int code = CertificateRanksBusiness.getExportCertificateCodeByTime(DateUtil.getTimeStamp(-90, ""), DateUtil.getTimeStamp(-1, ""));
//		Assert.assertEquals(code, 200,"导出证书排行榜--时间筛选:"+code);
	}
	@Test(description="导出证书排行",priority=12)
	public void testGetExportScoreRnkde() {
//		int code = CertificateRanksBusiness.getExportCertificateCodeByTime(DateUtil.getTimeStamp(-90, ""), DateUtil.getTimeStamp(-1, ""));
//		Assert.assertEquals(code, 200,"导出证书排行榜--时间筛选:"+code);
	}
	@Test(description="导出证书排行榜",priority=13)
	public void testGetExportScoreRkde() {
//		int code = CertificateRanksBusiness.getExportCertificateCodeByTime(DateUtil.getTimeStamp(-90, ""), DateUtil.getTimeStamp(-1, ""));
//		Assert.assertEquals(code, 200,"导出证书排行榜--时间筛选:"+code);
	}
	@Test(description="导出证书排行榜",priority=14)
	public void testGetExportScorankde() {
//		int code = CertificateRanksBusiness.getExportCertificateCodeByTime(DateUtil.getTimeStamp(-90, ""), DateUtil.getTimeStamp(-1, ""));
//		Assert.assertEquals(code, 200,"导出证书排行榜--时间筛选:"+code);
	}
	
	
	@Test(description="导出证书排行榜",priority=15)
	public void testGetExportScorRankde() {
//		int code = CertificateRanksBusiness.getExportCertificateCodeByTime(DateUtil.getTimeStamp(-90, ""), DateUtil.getTimeStamp(-1, ""));
//		Assert.assertEquals(code, 200,"导出证书排行榜--时间筛选:"+code);
	}
	@Test(description="导出证书排行",priority=16)
	public void testGetExportScoreRankde() {
//		int code = CertificateRanksBusiness.getExportCertificateCodeByTime(DateUtil.getTimeStamp(-90, ""), DateUtil.getTimeStamp(-1, ""));
//		Assert.assertEquals(code, 200,"导出证书排行榜--时间筛选:"+code);
	}
	
}
