package setting.cases;

import cn.kxy.setting.bussiness.OperationLogBusiness;
import cn.kxy.setting.bussiness.UserBusiness;
import com.alibaba.fastjson.JSONPath;
import com.lazy.common.utils.DateUtil;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestOperationLog {

	String event_type="";
	String username= UserBusiness.getUsername();
	@Test(description="查看操作日志类型",priority=1)
	public void testQueryEventTypes() {
		String queryEventTypes = OperationLogBusiness.queryEventTypes();
		String name = (String)JSONPath.read(queryEventTypes, "$.types[0].event_name");
		Assert.assertNotNull(name,""+queryEventTypes);
	}

	@Test(description="查看日志列表，只输入日志类型",priority=2)
	public void testQueryLogDetailByLogTyoe() {
		String res = OperationLogBusiness.queryLogDetail("", "","", "");

	}
	
	@Test(description="查看日志列表，只输入时间",priority=3)
	public void testQueryLogDetailByTime() {
		String res = OperationLogBusiness.queryLogDetail(DateUtil.getTimeStamp(-20, ""), DateUtil.getTimeStamp(-1, ""),"", "");
		String record_time =(String)JSONPath.read(res, "$.audits[0].record_time");
		String event_time =(String)JSONPath.read(res, "$.audits[0].event_time");
		String message =(String)JSONPath.read(res, "$.audits[0].message");
		String event_type =(String)JSONPath.read(res, "$.audits[0].event_type");
		
		Assert.assertNotNull(record_time,"查看日志列表，对日志记录时间进行校验"+res);
		Assert.assertNotNull(event_time,"查看日志列表，对日志事件时间进行校验"+res);
		Assert.assertNotNull(message,"查看日志列表，对日志message进行校验"+res);
		Assert.assertNotNull(event_type,"查看日志列表，对日志操作类型进行校验"+res);
	}
	
	@Test(description="查看日志列表，无搜索条件时",priority=4)
	public void testQueryLogDetailByUsername() {
		String res = OperationLogBusiness.queryLogDetail("", "", "","");
	}
}
