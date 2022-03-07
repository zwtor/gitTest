package setting.cases;

import cn.kxy.setting.bussiness.AdvanceSettingBusiness;
import cn.kxy.setting.bussiness.IntegralBusiness;
import cn.kxy.setting.bussiness.UserBusiness;
import com.alibaba.fastjson.JSONPath;
import com.lazy.common.utils.DateUtil;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestIntegral {

	@Test(description = "查看积分规则设置",priority=1)
	public void queryReewardRules() {
		String res = AdvanceSettingBusiness.queryReewardRules("1001");
		Assert.assertTrue(res.contains("daily_integral_status"),"查看积分规则设置"+res);
	}
	@Test(description = "不输入条件查询积分排行榜",priority = 2)
	public void testQueryIntegralRank() {
		String res = IntegralBusiness.queryIntegralRank("", "", "");
		int  score = (int)JSONPath.read(res, "$.list[0].accumulate_score");
		Assert.assertNotNull(score);
	}
	
	@Test(description = "根据时间查询积分排行榜",priority = 3)
	public void testQueryIntegralRankByTime() {
		String res = IntegralBusiness.queryIntegralRank(DateUtil.getTimeStamp(-9, ""), DateUtil.getTimeStamp( 0, ""), "");
		int  score = (int)JSONPath.read(res, "$.list[0].accumulate_score");
		Assert.assertNotNull(score);
	}
	
	@Test(description = "根据名称查询积分排行榜",priority = 4)
	public void testQueryIntegralRankByName() {
		String res = IntegralBusiness.queryIntegralRank("","", UserBusiness.getUsername());
		int  score = (int)JSONPath.read(res, "$.list[0].accumulate_score");
		Assert.assertNotNull(score);
	}
	

	@Test(description = "输入不存在的名称查询积分排行榜",priority = 5)
	public void testQueryIntegralRankByNoName() {
		String res = IntegralBusiness.queryIntegralRank("","", "12412");
		Assert.assertTrue(res.contains("is_first_page"));
	}
	
	@Test(description = "查看我的今日得分，累计积分",priority = 6)
	public void testQueryMyIntegral() {
		String res = IntegralBusiness.queryMyIntegral();
		Assert.assertTrue(res.contains("total"));
	}
	
	@Test(description = "查看积分详情",priority = 7)
	public void queryIntegralDetails() {
//		String res = IntegralBusiness.queryIntegralDetails("","");
//		Assert.assertTrue(res.contains("is_first_page"));
	}
	
	@Test(description = "通过时间查看积分详情",priority = 8)
	public void testQueryIntegralDetails() {
		String res = IntegralBusiness.queryIntegralDetails(DateUtil.getTimeStamp(-7, ""), DateUtil.getTimeStamp(0, ""));
		Assert.assertTrue(res.contains("is_first_page"));
	}
	@Test(description = "获取积分排行榜导出code",priority = 9)
	public void testGetRankExportCode() {
		int code = IntegralBusiness.getRankExportCode();
		Assert.assertEquals(code, 200,"获取积分排行榜导出code:"+code);
	}
	
	@Test(description = "导出全部的积分详情",priority = 10)
	public void testQueryIntegralDetailExport() {
		String res = IntegralBusiness.queryIntegralDetailExport("", "");
		Assert.assertTrue(res.contains("导出成功"));
	}
	
	@Test(description = "根据时间导出积分详情",priority = 11)
	public void test() {
		String res = IntegralBusiness.queryIntegralDetailExport(DateUtil.getTimeStamp(-7, ""), DateUtil.getTimeStamp(0, ""));
		Assert.assertTrue(res.contains("导出成功"));
	}
}
