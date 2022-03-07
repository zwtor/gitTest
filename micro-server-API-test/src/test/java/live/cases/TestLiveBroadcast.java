package live.cases;

import cn.kxy.study.business.LiveBroadcastBusiness;
import com.alibaba.fastjson.JSONPath;
import com.lazy.common.utils.CommonData;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TestLiveBroadcast {

	@BeforeClass(description= "如果直播间大于5时，删除第一个直播间")
	public void init() {
		String res = LiveBroadcastBusiness.getLiveList();
		int surplus_total = (int)JSONPath.read(res, "$.data.quota_remained");
		if (surplus_total == 0) {
			String surplus_id = (String)JSONPath.read(res, "$.data.rooms[0].id");
			String list_res = LiveBroadcastBusiness.deleteLive(surplus_id);
			String msg = (String)JSONPath.read(list_res, "$.message");
			Assert.assertEquals(msg, "success","删除回放不保存到课件库的直播间，实际返回结果："+list_res);
		}
	}
	
	@Test(description = "新增直播间时，name，类型为空时",priority = 1)
	public void testAddAllNull() {
		System.out.println("this is Live module");
		String res = LiveBroadcastBusiness.addLive("", "", "");
		Assert.assertTrue(res.contains("Internal service error"), "新增直播间时，name，类型为空时："+res);
	}
	@Test(description = "新增直播间时，name为空时",priority = 2)
	public void testAddNameNull() {
		String res = LiveBroadcastBusiness.addLive("", "aff", "10");
		Assert.assertTrue(res.contains("Validation failed, check the request body"), "新增直播间时，name为空时，实际返回结果："+res);
	}
	@Test(description = "新增直播间时，type类型为空时",priority = 3)
	public void testAddTypeNull() {
		String res = LiveBroadcastBusiness.addLive("qwe", "", "10");
		Assert.assertTrue(res.contains("Validation failed, check the request body"), "新增直播间时，type类型为空时,实际返回结果："+res);
	}
	@Test(description = "新增直播间时，人数为空时",priority = 4)
	public void testAddNumNull() {
		String res = LiveBroadcastBusiness.addLive("qwe", "yes", "");
		Assert.assertTrue(res.contains("Internal service error"), "");
	}
	String live_name_01 = "MyRoom"+CommonData.getStringRandom(3);
	String live_name_02 = "LiveRoom"+CommonData.getStringRandom(3);
	String id_01 = "";
	String id_02 = "";
	@Test(description = "新增直播间,回放保存到课件库",priority = 5)
	public void testAdd() {
		String res = LiveBroadcastBusiness.getLiveList();
		int surplus_total = (int)JSONPath.read(res, "$.data.quota_remained");
		if (surplus_total == 0) {
			String surplus_id = (String)JSONPath.read(res, "$.data.rooms[0].id");
			String list_res = LiveBroadcastBusiness.deleteLive(surplus_id);
			String msg = (String)JSONPath.read(list_res, "$.message");
			Assert.assertEquals(msg, "success","删除回放不保存到课件库的直播间，实际返回结果："+list_res);
		}
		String info_res = LiveBroadcastBusiness.addLive(live_name_01, "yes", "100");
		id_01 = (String)JSONPath.read(info_res, "$.data.id");
		String msg = (String)JSONPath.read(info_res, "$.message");
		Assert.assertEquals(msg, "success","新增直播间,回放保存到课件库,实际返回结果："+info_res);
	}
	@Test(description = "新增回放保存到课件库的直播间后，查看列表是否新增成功",priority = 6)
	public void testGetLiveYesList() {
		String res = LiveBroadcastBusiness.getLiveList();
		Assert.assertTrue(res.contains(live_name_01), "新增直播间后，查看列表是否新增成功"+res);
	}
	@Test(description = "删除回放保存到课件库的直播间",priority = 7)
	public void testDeleteYesLive() {
		String res = LiveBroadcastBusiness.deleteLive(id_01);
		String msg = (String)JSONPath.read(res, "$.message");
		Assert.assertEquals(msg, "success","删除回放保存到课件库的直播间，实际返回结果："+res);
	}
	@Test(description = "新增回放保存到课件库的直播间后，查看列表是否删除成功",priority = 8)
	public void testCheckGetLiveYesList() {
		String res = LiveBroadcastBusiness.getLiveList();
		Assert.assertFalse(res.contains(live_name_01), "新增回放保存到课件库的直播间后，查看列表是否删除成功"+res);
	}
	@Test(description = "新增直播间,回放不保存到课件库",priority = 9)
	public void testAddNOLive() {
		
		String res = LiveBroadcastBusiness.getLiveList();
		int surplus_total = (int)JSONPath.read(res, "$.data.quota_remained");
		if (surplus_total == 0) {
			String surplus_id = (String)JSONPath.read(res, "$.data.rooms[0].id");
			String list_res = LiveBroadcastBusiness.deleteLive(surplus_id);
			String msg = (String)JSONPath.read(list_res, "$.message");
			Assert.assertEquals(msg, "success","删除回放不保存到课件库的直播间，实际返回结果："+list_res);
		}
		String info_res = LiveBroadcastBusiness.addLive(live_name_02, "yes", "100");
		id_02 = (String)JSONPath.read(info_res, "$.data.id");
		String msg = (String)JSONPath.read(info_res, "$.message");
		Assert.assertEquals(msg, "success","新增直播间,回放不保存到课件库,实际返回结果："+info_res);
	}
	@Test(description = "新增回放不保存到课件库的直播间后，查看列表是否新增成功",priority = 10)
	public void testGetLiveNoList() {
		String res = LiveBroadcastBusiness.getLiveList();
		Assert.assertTrue(res.contains(live_name_02), "新增回放不保存到课件库的直播间后，查看列表是否新增成功"+res);
		Assert.assertTrue(res.contains("create_user"), "新增回放不保存到课件库的直播间后，查看列表是否新增成功"+res);
	}
	@Test(description = "删除回放不保存到课件库的直播间",priority = 11)
	public void testDeleteNoLive() {
		String res = LiveBroadcastBusiness.deleteLive(id_02);
		String msg = (String)JSONPath.read(res, "$.message");
		Assert.assertEquals(msg, "success","删除回放不保存到课件库的直播间，实际返回结果："+res);
	}
	@Test(description = "删除回放不保存到课件库的直播间，查看列表是否删除成功",priority = 12)
	public void testCheckGetNoLiveYesList() {
		String res = LiveBroadcastBusiness.getLiveList();
		Assert.assertFalse(res.contains(live_name_02), "删除回放不保存到课件库的直播间，查看列表是否删除成功："+res);
	}
	
	String live_name_03 = "Live"+CommonData.getStringRandom(4);
	String live_id_03 = "";
	@Test(description = "新增直播间，供列表页查询使用",priority = 13)
	public void testAddLive() {
		String res = LiveBroadcastBusiness.getLiveList();
		int surplus_total = (int)JSONPath.read(res, "$.data.quota_remained");
		if (surplus_total == 0) {
			String surplus_id = (String)JSONPath.read(res, "$.data.rooms[0].id");
			String list_res = LiveBroadcastBusiness.deleteLive(surplus_id);
			String msg = (String)JSONPath.read(list_res, "$.message");
			Assert.assertEquals(msg, "success","删除回放不保存到课件库的直播间，实际返回结果："+list_res);
		}
		String info_res = LiveBroadcastBusiness.addLive(live_name_03, "yes", "100");
		live_id_03 = (String)JSONPath.read(info_res, "$.data.id");
		String msg = (String)JSONPath.read(info_res, "$.message");
		Assert.assertEquals(msg, "success","新增直播间，供列表页查询使用,实际返回结果："+info_res);
	}
	@Test(description = "新增直播间，直播间数进行校验",priority = 14)
	public void testGetLive() {
		String res = LiveBroadcastBusiness.getLiveList();
		Assert.assertTrue(res.contains("total_number"),"新增直播间，直播间数进行校验，实际返回结果："+res);
		Assert.assertTrue(res.contains("quota_remained"),"新增直播间，直播间数进行校验，实际返回结果："+res);
	}
	@Test(description = "删除直播间",priority = 15)
	public void testDele() {
		String res = LiveBroadcastBusiness.deleteLive(live_id_03);
		String msg = (String)JSONPath.read(res, "$.message");
		Assert.assertEquals(msg, "success","删除直播间，实际返回结果："+res);
	}
}
