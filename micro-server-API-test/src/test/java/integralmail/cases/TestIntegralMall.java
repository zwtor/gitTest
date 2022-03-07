package integralmail.cases;

import cn.kxy.integralmall.business.IntegralMallBusiness;
import com.alibaba.fastjson.JSONPath;
import com.lazy.common.utils.CommonData;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestIntegralMall {

	String goods_name = "BluetoothHeadSet"+CommonData.getStringRandom(5);
	String good_id ="";
	@Test(description="新增积分商品",priority=1)
	public void testAddRepeatExchangeGoods() {
		String res = IntegralMallBusiness.addRepeatExchangeGoods(goods_name, "2", "20");
		String id = (String)JSONPath.read(res, "$.id");
		System.out.println("this is a IntegralMall module");
		Assert.assertNotNull(id, "新增积分商品，实际返回结果："+res);
	}
	@Test(description="查看积分商城的商品列表--All",priority=2)
	public void testQueryIntegralMallList() {
		String res = IntegralMallBusiness.queryIntegralMallList("", goods_name); 
		good_id = (String)JSONPath.read(res, "$.list[0].id");
		String title = (String)JSONPath.read(res, "$.list[0].name");
		int score = (int)JSONPath.read(res, "$.list[0].score");
		boolean repeat_exchange = (boolean)JSONPath.read(res, "$.list[0].repeat_exchange");
		String update_user_name = (String)JSONPath.read(res, "$.list[0].update_user_name");
		
		int remain_inventory = (int)JSONPath.read(res, "$.list[0].remain_inventory");
		String status = (String)JSONPath.read(res, "$.list[0].status");
		Assert.assertEquals(status,"up","查看商品列表，对发布状态进行校验"+res);
		Assert.assertEquals(repeat_exchange,true,"查看商品列表，对所需积分进行校验"+res);
//		Assert.assertEquals(update_user_name,UserBusiness.getUsername(),"查看商品列表，对更新人 进行校验"+res);
		Assert.assertEquals(score,2,"查看商品列表，对所需积分进行校验"+res);
		Assert.assertEquals(remain_inventory,20,"查看商品列表，对剩余库存进行校验"+res);
		Assert.assertEquals(title,goods_name,"查看商品列表，对名称进行校验"+res);
	}
	@Test(description="下架积分商品",priority=3)
	public void testUpdateDownStatus() {
		String res = IntegralMallBusiness.updateStatus("down", good_id);
		String title = (String)JSONPath.read(res, "$.name");
		Assert.assertEquals(title, goods_name,"下架积分商品，实际返回结果："+res);
	}
	@Test(description="下架商品后在已下架列表查看",priority=4)
	public void testQueryIntegralMallDownList() {
		String res = IntegralMallBusiness.queryIntegralMallList("down", goods_name); 
		String status = (String)JSONPath.read(res, "$.list[0].status");
		Assert.assertEquals(status,"down","查看已下架商品列表，对发布状态进行校验"+res);
	}
	
	@Test(description="查看积分商品的详情",priority=5)
	public void testQueryGoodInfo() {
		String res = IntegralMallBusiness.queryGoodInfo(good_id);
		String title = (String)JSONPath.read(res, "$.name");
		Assert.assertEquals(title, goods_name,"查看积分商品的详情，实际返回结果"+res);
	}
	
	@Test(description="上架积分商品",priority=6)
	public void testUpdateUpStatus() {
		String res = IntegralMallBusiness.updateStatus("up", good_id);
		String title = (String)JSONPath.read(res, "$.name");
		Assert.assertEquals(title, goods_name,"上架积分商品，实际返回结果："+res);
	}
	@Test(description="上架商品后在已上架列表查看",priority=7)
	public void testQueryIntegralMallUpList() {
		String res = IntegralMallBusiness.queryIntegralMallList("up", goods_name); 
		String status = (String)JSONPath.read(res, "$.list[0].status");
		Assert.assertEquals(status,"up","查看已上架商品列表，对发布状态进行校验"+res);
	}
	
	@Test(description="删除商品",priority=8)
	public void testDeleteGoods() {
		String res = IntegralMallBusiness.deleteGoods(good_id);
		String status = (String)JSONPath.read(res, "$.status");
		Assert.assertEquals(status, "success","删除积分商城的商品，实际返回结果："+res);
	}
	
	@Test(description= "新增商品时name为null", priority = 9)
	public void testNameIsnull() {
//		String res = IntegralMallBusiness.addRepeatExchangeGoods("", "2", "20");
//		String id = (String)JSONPath.read(res, "$.id");
//		System.out.println(res);
//		Assert.assertNotNull(id, "新增积分商品，实际返回结果："+res);
	}
	
	@Test(description= "新增商品时score为null", priority = 10)
	public void testScoreIsnull() {
//		String res = IntegralMallBusiness.addRepeatExchangeGoods("", "", "20");
//		String id = (String)JSONPath.read(res, "$.id");
//		System.out.println(res);
//		Assert.assertNotNull(id, "新增积分商品，实际返回结果："+res);
	}
	
	@Test(description= "新增商品时个数为null", priority = 9)
	public void test() {
//		String res = IntegralMallBusiness.addRepeatExchangeGoods("", "2", "");
//		String id = (String)JSONPath.read(res, "$.id");
//		System.out.println(res);
//		Assert.assertNotNull(id, "新增积分商品，实际返回结果："+res);
	}
	
	
}
