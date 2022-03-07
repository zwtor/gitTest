package setting.cases;

import cn.kxy.setting.bussiness.EnterpriseSettingBusiness;
import cn.kxy.setting.bussiness.MobileSettingBusiness;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class TestOperationSetting {

	@Test(description = "查看pc自定义",priority = 1)
	public void queryCustomByPc() {
		String queryCustom = MobileSettingBusiness.queryCustom("pc");
		assertTrue(queryCustom.contains("custom_name"),""+queryCustom);
	}
	
	@Test(description = "查看banner",priority = 2)
	public void queryCustomByBanner() {
		String queryCustom = MobileSettingBusiness.queryCustom("banner");
		assertTrue(queryCustom.contains("menus"),""+queryCustom);
	}
	
	@Test(description = "",priority = 3)
	public void queryCstomerServiceSwitch() {
		String res = EnterpriseSettingBusiness.queryCstomerServiceSwitch();
		assertTrue(res.contains("description"),""+res);
	}
	@Test(description = "",priority = 4)
	public void queryHomeCustomize() {
		String res = EnterpriseSettingBusiness.queryHomeCustomize();
		assertTrue(res.contains("list"),""+res);
	}
	@Test(description = "",priority = 5)
	public void queryHomeLecturers() {
		String res = EnterpriseSettingBusiness.queryHomeLecturers();
		assertTrue(res.contains("row"),""+res);
	} 
	@Test(description = "",priority = 6)
	public void queryIndexScoreRank() {
		String res = EnterpriseSettingBusiness.queryIndexScoreRank();
		assertTrue(res.contains("ranks"),""+res);
	}
	@Test(description = "",priority = 7)
	public void querySyncStandard() {
		String res = EnterpriseSettingBusiness.querySyncStandard();
		assertTrue(res.contains("organizes"),""+res);
	}
}
