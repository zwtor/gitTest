package setting.cases;

import cn.kxy.setting.bussiness.IntegralBusiness;
import com.jayway.jsonpath.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;

public class TestIntegralCentre {

	@Test(description = "查看连续签到规则", priority = 1)
	public void testQuerySignRuleList() {
		String res = IntegralBusiness.querySignRuleList();
		Assert.assertTrue(res.contains("total"));
	}
	
	ArrayList<String> lecter;
	
	@Test(description = "查看讲师积分规则", priority = 2)
	public void testQueryLecturerRuleList() {
		String res = IntegralBusiness.queryLecturerRuleList();
		lecter = JsonPath.read(res, "$.rules.list");
		Assert.assertTrue(res.contains("学习项目"));
	}
	
	ArrayList<String> with_id ;
	
	@Test(description = "获取撤回的集合",priority = 3)
	public void getWithdrawId() {
//		String res = IntegralBusiness.queryIntegralDetails("", "","2");
//		with_id = JsonPath.read(res, "$.list");
	}
	
	@Test(description = "撤回积分排行榜的积分",priority = 4)
	public void testCountermandIntegral() {
//		String res = LecturerListBusiness.countermandIntegral(with_id);
//		Assert.assertTrue(res.contains("成功"),"撤回积分排行榜的积分,实际返回结果："+res);
	}
	
	@Test(description = "保存连续签到规则",priority = 5)
	public void testSaveSignRule() {
		String res = IntegralBusiness.saveSignRule();
		Assert.assertTrue(res.contains("成功"),"保存连续签到规则,实际返回结果："+res);
	}
	
	@Test(description = "保存讲师积分规则",priority = 6)
	public void saveLecturerRule() {
		String res = IntegralBusiness.saveLecturerRule(lecter);
		Assert.assertTrue(res.contains("成功"),"保存讲师积分规则,实际返回结果："+res);
	}
	
	@Test(description = "手动导入积分",priority = 7)
	public void testAddIntegral() {
		String res = IntegralBusiness.addIntegral("5","add score");
		Assert.assertTrue(res.contains("true"),"手动导入积分,实际返回结果："+res);
	}
	@Test(description = "验证是否导入成功",priority = 9)
	public void queryIntegralDetails() {
		String res = IntegralBusiness.queryIntegralDetails("", "","2");
		Assert.assertTrue(res.contains("add score"),"验证是否导入成功"+res);
	}
	
	@Test(description = "手动扣减积分",priority = 10)
	public void testIntegral() {
		String res = IntegralBusiness.addIntegral("-1","reduce score");
		Assert.assertTrue(res.contains("true"),"手动扣减积分,实际返回结果："+res);
	}
	@Test(description = "验证积分是否扣减成功",priority = 11)
	public void queryReduceIntegralDetails() {
//		String res = IntegralBusiness.queryIntegralDetails("", "","2");
//		Assert.assertTrue(res.contains("reduce score"),"验证积分是否扣减成功"+res);
	}
}
