package course.cases;

import cn.kxy.course.resources.bussiness.CourseBusiness;
import com.lazy.common.utils.DateUtil;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestCourseRevision {

	@Test(description = "查看不同类型的课程",priority = 1,dataProvider = "statusKey")
	public void testQueryCourseByPage(String type ,String statusType,String classifyType) {
		String res = CourseBusiness.queryCourseByPage("", DateUtil.getTimeStamp(0, ""), "", statusType, "all",classifyType,"");
		Assert.assertTrue(res.contains("total"),"查看不同类型的课程,实际返回结果："+res);
	}
	
	@Test(description = "查看按照最新， 最热，评论，点赞的课程排序",priority = 2,dataProvider = "status")
	public void test(String type ,String sortType,String ordertype) {
		String res = CourseBusiness.queryCourseByPage("", DateUtil.getTimeStamp(0, ""), "", "all", sortType,"course",ordertype);
		Assert.assertTrue(res.contains("total"),"查看不同类型的课程,实际返回结果："+res);
	}
	
	@DataProvider(name = "status")
	public Object[][] status() {
		Object[][] obj = new Object[][] { 
			{"查看最新","newest",""},{"查看最热","hottest",""},
			{"查看评论升序","comment","asc"},{"查看评论降序","comment","desc"},
			{"查看点赞升序","like","asc"},{"查看点赞降序","like","desc"}
			};
		return obj;
	}
	
	@DataProvider(name = "statusKey")
	public Object[][] statusKey() {
		Object[][] obj = new Object[][] { 
			{"查看全部","all","all"},{"查看全部未学习","noStudy","all"},{"查看全部学习中","studying","all"},{"查看全部已学完","finished","all"},
			{"查看课程全部","all","course"},{"查看课程全部未学习","noStudy","course"},{"查看课程全部学习中","studying","course"},{"查看课程全部已学完","finished","course"},
			{"查看微课全部","all","micro_course"},{"查看微课全部未学习","noStudy","micro_course"},{"查看微课全部学习中","studying","micro_course"},{"查看全部微课已学完","finished","micro_course"},
			{"查看学习项目全部","all","study_project"},{"查看学习项目全部未学习","noStudy","study_project"},{"查看学习项目全部学习中","studying","study_project"},
			{"查看学习项目全部已学完","finished","study_project"},
			
			{"查看图文课全部","all","imageText"},{"查看图文课未学习","noStudy","imageText"},{"查看图文课学习中","studying","imageText"},
			{"查看图文课已学完","finished","imageText"},
			
			{"查看直播课全部","all","live_course"},{"查看直播课未学习","noStudy","live_course"},{"查看直播课学习中","studying","live_course"},
			{"查看直播课已学完","finished","live_course"},
		};
		return obj;
	}
	
	
}
