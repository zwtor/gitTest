package localstorage.cases;

import cn.kxy.localstorage.business.StorageBusiness;
import com.alibaba.fastjson.JSONPath;
import com.lazy.common.utils.CommonData;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestLocalStorage {

	String name = "LocalStoreg" + CommonData.getStringRandom(5);
	String host = "https://local.storage.cn"+CommonData.getStringRandom(5);
	String id = "";
	@Test(description = "新增存储器",priority = 1)
	public void testAddLocalstorage() {
		String res = StorageBusiness.addLocalstorage(name, host, "LOCAL");
		id = (String)JSONPath.read(res, "$.data.id");
		String u_name = (String)JSONPath.read(res, "$.data.name");
		Assert.assertEquals(name, u_name,"新增存储器实际返回结果："+res);
	}
	
	@Test(description = "查看存储器列表",priority = 2)
	public void queryResoureList() {
		String res = StorageBusiness.queryResoureList();
		id = (String)JSONPath.read(res, "$.data[0].id");
	}
	
	@Test(description = "编辑存储器",priority = 3)
	public void editLocalstorage() {
		StorageBusiness.editLocalstorage(id,name, host+CommonData.getStringRandom(3), "LOCAL");
	}
	
	@Test(description = "关闭存储上传",priority = 4)
	public void changeStorageStatus() {
		if (id==null) {
			
		}else {
			String res = StorageBusiness.changeStorageStatus(id, "false");
			Assert.assertTrue(res.contains("success"),"关闭存储上传，实际返回结果："+res);
		}
	}
	
	@Test(description = "开启存储上传",priority = 5)
	public void changeStorageCloseStatus() {
		if (id!=null) {
			String res = StorageBusiness.changeStorageStatus(id, "true");
			Assert.assertTrue(res.contains("success"),"开启存储上传，实际返回结果："+res);
		}
		
	}
	
	@Test(description = "新增ip已经存在的存储器",priority = 6)
	public void testAddLocalIpRepeatstorage() {
		String res = StorageBusiness.addLocalstorage(name, host, "LOCAL");
		Assert.assertTrue(res.contains("80000"),"新增ip已经存在的存储器，实际返回结果："+res);
	}
	
	@Test(description = "关闭存储上传id不存在时",priority = 7)
	public void changeStorageStatusIdNull() {
		String res = StorageBusiness.changeStorageStatus("52533", "false");
		Assert.assertTrue(res.contains("70001"),"关闭存储上传id不存在时，实际返回结果："+res);
	}
	
	@Test(description= "查看存储器的日志",priority = 8)
	public void queryStoarageLog() {
		String res = StorageBusiness.queryStoarageLog(id);
		Assert.assertTrue(res.contains("data"),"查看存储器的日志，实际返回结果："+res);

	}
	
	@Test(description = "删除存储",priority = 9)
	public void deleteStorage() {
		if (id!=null) {
			String res = StorageBusiness.deleteStorage(id);
			Assert.assertTrue(res.contains("SUCCESS"),"删除存储，实际返回结果："+res);
		}
	}
	
}
