package integralmail.cases;

import cn.kxy.integralmall.business.AppIntegralMallBusiness;
import cn.kxy.integralmall.business.IntegralMallBusiness;
import cn.kxy.setting.bussiness.UserBusiness;
import com.alibaba.fastjson.JSONPath;
import com.lazy.common.utils.CommonData;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestAppIntegralmall {

	String good_name = "NotebookComputer"+CommonData.getStringRandom(5);
	
	String id = "";
	String user_name = UserBusiness.getUsername();
	
	@Test(priority = 1)
	public void initIntegralmall() {
		IntegralMallBusiness.addRepeatExchangeGoods(good_name, "2", "20");
		
	}
	
	@Test(priority = 2)
	public void testqueryIntegralMallList() {
		String res = IntegralMallBusiness.queryIntegralMallList("", good_name);
		id= (String)JSONPath.read(res, "$.list[0].id");
		
	}
	
	@Test(priority = 3)
	public void testupdateStatus() {
		IntegralMallBusiness.updateStatus("up", id);
	}
	
	@Test(description="查看可用积分",priority=4)
	public void testQueryMyRemainScore() {
		String res = AppIntegralMallBusiness.queryMyRemainScore();
		int remain_score = (int)JSONPath.read(res, "$.remain_score");
		String avatar = (String)JSONPath.read(res, "$.avatar");
		Assert.assertNotNull(avatar, "查看可用积分"+res);
		Assert.assertNotNull(remain_score, "查看可用积分"+res);
	}
	
	@Test(description="查看移动端积分商城列表",priority=5)
	public void testQueryGoodList () {
//		String res = AppIntegralMallBusiness.queryGoodList();
//		String name = (String)JSONPath.read(res, "$.list[0].name");
//		int score = (int)JSONPath.read(res, "$.list[0].score");
//		int order_count = (int)JSONPath.read(res, "$.list[0].order_count");
//		int remain_inventory = (int)JSONPath.read(res, "$.list[0].remain_inventory");
//		Assert.assertNotNull(name, "查看移动端积分商城列表，名称进行校验"+res);
//		Assert.assertNotNull(score, "查看移动端积分商城列表，积分进行校验"+res);
//		Assert.assertNotNull(order_count, "查看移动端积分商城列表，兑换人数进行兑换"+res);
//		Assert.assertNotNull(remain_inventory, "查看移动端积分商城列表，库存进行校验"+res);
	}
	
	@Test(description="查看移动端积分商品详情",priority=6)
	public void testQueryGoodsInfo() {
		String res = AppIntegralMallBusiness.queryGoodsInfo(id);
		String description = (String)JSONPath.read(res, "$.description");
		String receive_address = (String)JSONPath.read(res, "$.receive_address");
		String deliver_user_contact = (String)JSONPath.read(res, "$.deliver_user_contact");
		String deliver_user_name = (String)JSONPath.read(res, "$.deliver_user_name");
		int remain_inventory = (int)JSONPath.read(res, "$.remain_inventory");
		boolean repeat_exchange = (boolean)JSONPath.read(res, "$.repeat_exchange");
		Assert.assertTrue(repeat_exchange,"查看移动端积分商品详情,是否重复兑换进行校验"+res);
		Assert.assertEquals(remain_inventory, 20,"查看移动端积分商品详情,领取地址进行校验"+res);
		Assert.assertEquals(receive_address, "xian","查看移动端积分商品详情,领取地址进行校验"+res);
		Assert.assertTrue(description.contains("des"),"查看移动端积分商品详情,描述进行校验"+res);
		Assert.assertEquals(deliver_user_contact, "16666668686","查看移动端积分商品详情,联系方式进行校验"+res);
		Assert.assertNotNull(deliver_user_name,"查看移动端积分商品详情,奖品发放人进行校验"+res);
	}
	String order_id = "";
	@Test(description="兑换商品",priority=7)
	public void testExchangeGoods() {
		String res = AppIntegralMallBusiness.exchangeGoods(id);
		System.out.println(res);
		long order_time = (long)JSONPath.read(res, "$.order_time");
		String receive_address = (String)JSONPath.read(res, "$.receive_address");
		String deliver_user_contact = (String)JSONPath.read(res, "$.deliver_user_contact");
		String deliver_user_name = (String)JSONPath.read(res, "$.deliver_user_name");
		order_id = (String)JSONPath.read(res, "$.id");		
		Assert.assertEquals(receive_address, "xian","兑换商品,领取地址进行校验"+res);
		Assert.assertNotNull(deliver_user_name, "兑换商品,奖品发放人进行校验"+res);
		Assert.assertEquals(deliver_user_contact, "16666668686","兑换商品,联系方式进行校验"+res);
		Assert.assertNotNull(order_time,"兑换商品,兑换时间进行校验"+res);
	}
	
	@Test(description="兑换后查看移动端商品库存",priority=8)
	public void testExchangeGoodsQueryGoodsInfo() {
		String res = AppIntegralMallBusiness.queryGoodsInfo(id);
		int remain_inventory = (int)JSONPath.read(res, "$.remain_inventory");
		Assert.assertEquals(remain_inventory, 19,"兑换后查看移动端商品库存,剩余库存进行校验"+res);
	}
	
	@Test(description="查询APP端兑换记录--未领取状态",priority=9)
	public void testQueryNotReceiveMyOrderRecords() {
		String res = AppIntegralMallBusiness.queryMyOrderRecords("not_receive");
		String name = (String)JSONPath.read(res, "$.list[0].good_name");
		long order_time = (long)JSONPath.read(res, "$.list[0].order_time");
		String receive_address = (String)JSONPath.read(res, "$.list[0].receive_address");
		Assert.assertEquals(name,good_name, "查询APP端兑换记录--未领取状态，名称进行校验"+res);
		Assert.assertNotNull(order_time, "查询APP端兑换记录--未领取状态，兑换时间进行校验"+res);
		Assert.assertNotNull(receive_address, "查询APP端兑换记录--未领取状态，领取地址进行校验"+res);
	}
	@Test(description="web端查看兑换明细--未领取",priority=10)
	public void testQueryExchangeDetail() {
		String res = IntegralMallBusiness.queryExchangeDetail("not_receive",id,user_name);
		String status = (String)JSONPath.read(res, "$.list[0].status");
		String username = (String)JSONPath.read(res, "$.list[0].user_name");
		String department_name = (String)JSONPath.read(res, "$.list[0].department_name");
		Assert.assertEquals(status,"not_receive", "web端查看兑换明细--未领取，状态进行校验"+res);
		Assert.assertNotNull(department_name, "web端查看兑换明细--未领取，部门进行校验"+res);
		Assert.assertEquals(username,user_name, "web端查看兑换明细--未领取，用户名进行校验"+res);
	}
	
	@Test(description="存在未领取的员工时，不允许删除商品",priority=11)
	public void testExistDeleteGoods() {
		String res = IntegralMallBusiness.deleteGoods(id);
		String message = (String)JSONPath.read(res, "$.message");
		Assert.assertEquals(message, "someone has this good,delete error","存在未领取的员工时，不允许删除商品"+res);
	}
	
	@Test(description="扫码领取商品",priority=12)
	public void testScanQrcode() {
		String res = AppIntegralMallBusiness.scanQrcode(order_id);
		String receive_address = (String)JSONPath.read(res, "$.receive_address");
		int order_count = (int)JSONPath.read(res, "$.order_count");
		String deliver_user_name = (String)JSONPath.read(res, "$.deliver_user_name");
		Assert.assertEquals(receive_address,"xian", "扫码领取商品，领取地址进行校验"+res);
		Assert.assertEquals(deliver_user_name,user_name, "扫码领取商品，奖品发放人进行校验"+res);
		Assert.assertEquals(order_count,1, "扫码领取商品，领取次数进行校验"+res);
	}
	@Test(description="查询APP端兑换记录--已领取状态",priority=13)
	public void testQueryMyOrderReceivedRecords() {
		String res = AppIntegralMallBusiness.queryMyOrderRecords("received");
		int size = (int)JSONPath.read(res, "$.size");
		Assert.assertNotNull(size, "查询APP端兑换记录--已领取状态，名称进行校验"+res);
	}
	@Test(description="web端查看兑换明细--已领取",priority=14)
	public void testQueryExchangeReceivedDetail() {
		String res = IntegralMallBusiness.queryExchangeDetail("received",id,user_name);
		String status = (String)JSONPath.read(res, "$.list[0].status");
		String username = (String)JSONPath.read(res, "$.list[0].user_name");
		String department_name = (String)JSONPath.read(res, "$.list[0].department_name");
		long order_time = (long)JSONPath.read(res, "$.list[0].order_time");
		Assert.assertNotNull(order_time, "web端查看兑换明细--已领取，领取时间进行校验"+res);
		Assert.assertEquals(status,"received", "web端查看兑换明细--已领取，状态进行校验"+res);
		Assert.assertNotNull(department_name, "web端查看兑换明细--已领取，部门进行校验"+res);
		Assert.assertEquals(username,user_name, "web端查看兑换明细--已领取，用户名进行校验"+res);
	}
	
	@Test(description="兑换后再次兑换商品",priority=15)
	public void testAgainExchangeGoods() {
		String res = AppIntegralMallBusiness.exchangeGoods(id);
		long order_time = (long)JSONPath.read(res, "$.order_time");
		String anagin_order_id = (String)JSONPath.read(res, "$.id");	
		String receive_address = (String)JSONPath.read(res, "$.receive_address");
		String deliver_user_contact = (String)JSONPath.read(res, "$.deliver_user_contact");
		String deliver_user_name = (String)JSONPath.read(res, "$.deliver_user_name");
		AppIntegralMallBusiness.scanQrcode(anagin_order_id);
		Assert.assertEquals(receive_address, "xian","兑换后再次兑换商品,领取地址进行校验"+res);
		Assert.assertNotNull(deliver_user_name, "兑换后再次兑换商品,奖品发放人进行校验"+res);
		Assert.assertEquals(deliver_user_contact, "16666668686","兑换后再次兑换商品,联系方式进行校验"+res);
		Assert.assertNotNull(order_time,"兑换后再次兑换商品,兑换时间进行校验"+res);
	}
	
	@Test(description="编辑时检查名称--check_good_name接口",priority=16)
	public void testCheckGoodName() {
		String edit_name = "BlueHeadset"+CommonData.getStringRandom(5);
		String res = IntegralMallBusiness.checkGoodName(id, edit_name);
		String name = (String)JSONPath.read(res, "$.name");
		Assert.assertEquals(name, edit_name,"编辑时检查名称"+res);
	}
	
	@Test(description="编辑商品",priority=17)
	public void testEditGoods() {
		String res = IntegralMallBusiness.editGoods(id, good_name);
		String edit_id = (String)JSONPath.read(res, "$.id");
		Assert.assertNotNull(edit_id, "编辑商品返回结果："+res);
	}
	
	@Test(description="将商品编辑不允许重复兑换，移动端查看商品详情",priority=18)
	public void testEditQueryGoodsInfo() {
		String res = AppIntegralMallBusiness.queryGoodsInfo(id);
		boolean repeat_exchange = (boolean)JSONPath.read(res, "$.repeat_exchange");
		String has_ordered = (String)JSONPath.read(res, "$.has_ordered");
		Assert.assertEquals(has_ordered, "true","将商品编辑不允许重复兑换，移动端查看商品详情,是否已经兑换进行校验"+res);
		Assert.assertFalse(repeat_exchange,"将商品编辑不允许重复兑换，移动端查看商品详情,是否重复兑换进行校验"+res);
	}
	
	@Test(description = "删除积分商品",priority = 19)
	public void end() {
		IntegralMallBusiness.deleteGoods(id);
	}
}
