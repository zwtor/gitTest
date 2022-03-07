package course.cases;

import cn.kxy.course.resources.bussiness.MicroCourseBusines;
import com.alibaba.fastjson.JSONPath;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestMicroCourse {
	@Test(description = "查看微课已发布列表",priority = 1)
	public void testQueryPublicMicroCourseList() {
		String res = MicroCourseBusines.queryMicroCourseList("ISSUED");
		String total = (String)JSONPath.read(res, "$.total");
		Assert.assertNotNull(total, "查看微课已发布列表，实际返回结果："+res);
	}
	@Test(description = "查看微课草稿箱列表",priority = 2)
	public void testQueryDraftMicroCourseList() {
		String res = MicroCourseBusines.queryMicroCourseList("NOT_ISSUED");
		String total = (String)JSONPath.read(res, "$.total");
		Assert.assertNotNull(total, "查看微课草稿箱列表，实际返回结果："+res);
	}
	@Test(description = "查看微课待审批列表",priority = 3)
	public void testQueryApprovalMicroCourseList() {
		String res = MicroCourseBusines.queryMicroCourseList("APPROVAL");
		String total = (String)JSONPath.read(res, "$.total");
		Assert.assertNotNull(total, "查看微课待审批列表，实际返回结果："+res);
	}
	@Test(description = "状态为空时,查看微课列表",priority = 4)
	public void testQueryMicroCourseListStatusIsEmpty() {
		String res = MicroCourseBusines.queryMicroCourseList("");
		String total = (String)JSONPath.read(res, "$.total");
		Assert.assertNotNull(total, "状态为空时,查看微课列表，实际返回结果："+res);
	}
	@Test(description = "状态为null时,查看微课列表",priority = 5)
	public void testQueryMicroCourseListStatusIsNull() {
		String res = MicroCourseBusines.queryMicroCourseList(null);
		String total = (String)JSONPath.read(res, "$.total");
		Assert.assertNotNull(total, "状态为null时,查看微课列表，实际返回结果："+res);
	}
	@Test(description = "状态不存在时,查看微课列表",priority = 6)
	public void testqueryMicroCourseList() {
		String res = MicroCourseBusines.queryMicroCourseList("adad");
		String total = (String)JSONPath.read(res, "$.total");
		Assert.assertNotNull(total, "状态不存在时,查看微课列表，实际返回结果："+res);
	}
	@Test(description = "查看微课选人后的人数",priority = 7)
	public void testQueryDistinctNum() {
		String res = MicroCourseBusines.queryDistinctNum();
		int user_count = (int)JSONPath.read(res, "$.user_count");
		Assert.assertTrue(user_count>=0, "查看微课选人后的人数,实际返回结果："+res);
	}
}
