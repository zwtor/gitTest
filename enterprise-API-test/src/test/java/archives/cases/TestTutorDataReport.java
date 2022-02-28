package archives.cases;

import cn.kxy.base.business.EnterpriseData;
import cn.kxy.base.business.EnterpriseDataUrl;
import cn.kxy.base.business.TokenData;
import cn.kxy.setting.bussiness.UserBusiness;
import com.lazy.httpclient.utils.HttpRequest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestTutorDataReport {

	@Test(description = "查看实操报表",priority = 1)
	public void queryTutorDataReport() {
		String res = HttpRequest.get(EnterpriseDataUrl.getEnterpriseUrl()+"/v2/"+EnterpriseData.getEnterpriseId()+"/tutor_data_report")
		.query("job_type","all").query("page_number","1").query("page_size","20").query("access_token",TokenData.getMangerToken())
		.send().body();
		Assert.assertTrue(res.contains("success"),"查看实操报表，实际返回结果"+res);
	}
	
	@Test(description = "通过姓名查看实操报表",priority = 2 )
	public void queryTutorDataReportByName() {
		String res = HttpRequest.get(EnterpriseDataUrl.getEnterpriseUrl()+"/v2/"+EnterpriseData.getEnterpriseId()+"/tutor_data_report")
		.query("job_type","all").query("page_number","1").query("key_words",UserBusiness.getUsername()).query("page_size","20").query("access_token",TokenData.getMangerToken())
		.send().body();
		Assert.assertTrue(res.contains("success"),"查看实操报表，实际返回结果"+res);
	}
	
	@Test(description = "导出实操报表",priority = 3)
	public void exportData() {
		String res = HttpRequest.get(EnterpriseDataUrl.getEnterpriseUrl()+"/v2/"+EnterpriseData.getEnterpriseId()+"/tutor_report_data/export")
				.query("job_type","all").query("page_number","1").query("key_words",UserBusiness.getUsername()).query("page_size","20").query("access_token",TokenData.getMangerToken())
				.send().body();
		Assert.assertTrue(res.contains("success"),"查看实操报表，实际返回结果"+res);
	}
	
}
