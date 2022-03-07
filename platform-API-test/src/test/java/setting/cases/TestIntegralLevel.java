package setting.cases;

import cn.kxy.setting.bussiness.IntegralBusiness;
import com.alibaba.fastjson.JSONPath;
import com.lazy.common.utils.CommonData;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestIntegralLevel {

	String name  = "level"+CommonData.getStringRandom(5);
	String id = "";
	@Test(description = "保存积分等级",priority= 1)
	public void saveIntegralLevel() {
		String res = IntegralBusiness.saveIntegralLevel(name);
		Assert.assertTrue(res.contains("新增成功"),"保存积分等级实际返回结果："+res);
	}
	@Test(description = "查询积分等级列表",priority = 2)
	public void queryIntegralLevelList() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		String res = IntegralBusiness.queryIntegralLevelList();
		id = (String)JSONPath.read(res, "$.list[1].id");
		Assert.assertTrue(res.contains(name),"查询积分等级列表，实际返回结果："+res);
	}
	String edit_name = "edit_level"+CommonData.getStringRandom(5);
	@Test(description = "编辑积分等级",priority = 3)
	public void editIntegralLevel() {
//		String res = IntegralBusiness.editIntegralLevel(id, name);
//		Assert.assertTrue(res.contains("更新成功"),"编辑积分等级，实际返回结果："+res);
	}
	
	@Test(description = "删除积分等级",priority = 4)
	public void deleteIntegralLevel() {
		String res = IntegralBusiness.deleteIntegralLevel(id);
		Assert.assertTrue(res.contains("删除"),"删除积分等级，实际返回结果："+res);
	}
}
