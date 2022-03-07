package courseshoppingmail.cases;

import cn.kxy.courseshoppingmall.business.NonLoginCourseMallListBusiness;
import com.alibaba.fastjson.JSONPath;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestNotLoginCourseShoppingmall {

	String recommendcourse_id ="";
	String courselist_id = "";
	String teacherId ="";
	String pop_course_id ="";
	@Test(description="未登录时查看课程商场的banner信息",priority=1)
	public void testQueryBannerInfo() {
		String res = NonLoginCourseMallListBusiness.queryBannerInfo();
		String title = (String)JSONPath.read(res, "$.list[0].title");
		Assert.assertTrue(title!=null, "未登录时查看课程商场的banner信息,实际返回结果：");
	}
	@Test(description="未登录时查看更多课程",priority=2)
	public void testGetHomeCourselist() {
		String res = NonLoginCourseMallListBusiness.getHomeCourselist();
		String title = (String)JSONPath.read(res, "$[0].courseList[0].title");
		String name = (String)JSONPath.read(res, "$[0].children[0].name");
		int officialPrice = (int)JSONPath.read(res, "$[0].courseList[0].officialPrice");
		String teacherName = (String)JSONPath.read(res, "$[0].courseList[0].teacherName");
		
		Assert.assertTrue(teacherName!=null, "未登录时查看更多课程--讲师名称,实际返回结果：");
		Assert.assertTrue(officialPrice>=0, "未登录时查看更多课程--价格,实际返回结果：");
		Assert.assertTrue(name!=null, "未登录时查看更多课程--分类,实际返回结果：");
		Assert.assertTrue(title!=null, "未登录时查看更多课程--标题,实际返回结果：");
	}
	@Test(description="未登录时查看热门课程",priority=3)
	public void testGetPopularCourses() {
		String res = NonLoginCourseMallListBusiness.getPopularCourses();
		String title = (String)JSONPath.read(res, "$.list[0].title");
		String scan_count = (String)JSONPath.read(res, "$.list[0].scan_count");
		int official_price = (int)JSONPath.read(res, "$.list[0].official_price");
		String teacherName = (String)JSONPath.read(res, "$.list[0].teacher_name");
		pop_course_id  =(String)JSONPath.read(res, "$.list[0].course_id");
		Assert.assertTrue(teacherName!=null, "未登录时查看热门课--讲师名称,实际返回结果：");
		Assert.assertTrue(official_price>=0, "未登录时查看热门课--价格,实际返回结果："+res);
		Assert.assertTrue(scan_count!=null, "未登录时查看热门课--浏览数,实际返回结果："+res);
		Assert.assertTrue(title!=null, "未登录时查看热门课程--title,实际返回结果：");
	}
	@Test(description="未登录时查看精品套餐列表",priority=4)
	public void testGetRecommendCoursePack() {
		String res = NonLoginCourseMallListBusiness.getRecommendCoursePack();
		String title = (String)JSONPath.read(res, "$[0].packName");
		int courseCount = (int)JSONPath.read(res, "$[0].courseCount");
		String scanCount = (String)JSONPath.read(res, "$[0].scanCount");
		int preferentialPrice = (int)JSONPath.read(res, "$[0].preferentialPrice");
		recommendcourse_id = (String)JSONPath.read(res, "$[0].id");
		Assert.assertTrue(preferentialPrice>=0,"未登录时查看精品套餐列表--价格,实际返回结果："+res);
		Assert.assertTrue(scanCount!=null, "未登录时查看精品套餐列表--浏览数,实际返回结果："+res);
		Assert.assertTrue(courseCount>=0, "未登录时查看精品套餐列表--课程数,实际返回结果："+res);
		Assert.assertTrue(title!=null, "未登录时查看精品套餐列表--title,实际返回结果："+res);
	}
	@Test(description="未登陆时查看精品课程详情",priority=5)
	public void testQueryRecommendCourseInfo() {
		String res = NonLoginCourseMallListBusiness.queryInfo(recommendcourse_id);
		String title = (String)JSONPath.read(res, "$.chapterList[0].courseList[0].title");
		String tag = (String)JSONPath.read(res, "$.tag");
		int preferentialPrice = (int)JSONPath.read(res, "$.preferentialPrice");
		int officialPrice = (int)JSONPath.read(res, "$.officialPrice");
		int courseCount = (int)JSONPath.read(res, "$.courseCount");
		int purchaseVolume = (int)JSONPath.read(res, "$.purchaseVolume");
		int validity = (int)JSONPath.read(res, "$.validity");
		Assert.assertTrue(validity>=0, "未登陆时查看精品课程详情--有效期,实际返回结果："+res);
		Assert.assertTrue(purchaseVolume>=0, "未登陆时查看精品课程详情--购买人数,实际返回结果："+res);
		Assert.assertTrue(courseCount>=0, "未登陆时查看精品课程详情--课程数,实际返回结果："+res);
		Assert.assertTrue(preferentialPrice>=0, "未登陆时查看精品课程详情--实际价,实际返回结果："+res);
		Assert.assertTrue(officialPrice>=0, "未登陆时查看精品课程详情--优惠价,实际返回结果："+res);
		Assert.assertTrue(tag!=null, "未登陆时查看精品课程详情,实际返回结果："+res);
		Assert.assertTrue(title!=null, "未登陆时查看精品课程详情,实际返回结果："+res);
	}
	@Test(description="未登陆时查看精品套餐的关联课程",priority=6)
	public void testRelevantCourse() {
		String res = NonLoginCourseMallListBusiness.relatedCourseOrPack(recommendcourse_id);
		String title = (String)JSONPath.read(res, "$[0].title");
		int officialPrice = (int)JSONPath.read(res, "$[0].officialPrice");
		courselist_id = (String)JSONPath.read(res, "$.chapterList[0].courseList[0].id");
		Assert.assertTrue(officialPrice>=0, "未登陆时查看精品课的关联课程,实际返回结果："+res);
		Assert.assertTrue(title!=null, "未登陆时查看精品课的关联课程,实际返回结果："+res);
	}
	
	@Test(description="未登陆时查看热门课程的详情",priority=7)
	public void testQueryPopularSelectOneInfo() {
		String res = NonLoginCourseMallListBusiness.querySelectOneInfo(pop_course_id);
		teacherId = (String )JSONPath.read(res, "$.teacherId");
		String title = (String)JSONPath.read(res, "$.title");
		String teacherName = (String) JSONPath.read(res, "$.teacherName");
		int preferentialPrice = (int)JSONPath.read(res, "$.preferentialPrice");
		int officialPrice = (int)JSONPath.read(res, "$.officialPrice");
		int coursewareCount = (int)JSONPath.read(res, "$.coursewareCount");
		int purchaseVolume = (int)JSONPath.read(res, "$.purchaseVolume");
		int validity = (int)JSONPath.read(res, "$.validity");
		Assert.assertTrue(validity>=0, "未登陆时查看热门课程详情--有效期,实际返回结果："+res);
		Assert.assertTrue(purchaseVolume>=0, "未登陆时查看热门课程详情--购买人数,实际返回结果："+res);
		Assert.assertTrue(coursewareCount>=0, "未登陆时查看热门课程详情--课件数,实际返回结果："+res);
		Assert.assertTrue(preferentialPrice>=0, "未登陆时查看热门课程详情--实际价,实际返回结果："+res);
		Assert.assertTrue(officialPrice>=0, "未登陆时查看热门课程详情--优惠价,实际返回结果："+res);
		Assert.assertTrue(teacherName!=null, "未登陆时查看热门课程详情,实际返回结果："+res);
		Assert.assertTrue(title!=null, "未登陆时查看热门课程详情,实际返回结果："+res);
	}
	@Test(description="未登陆时查看精品套餐的关联课程",priority=8)
	public void testcoursewareRelevantCourse() {
		System.out.println();
	}
	@Test(description="未登录时查看课程的资源",priority=9)
	public void testGetCourseResource() {
		String res = NonLoginCourseMallListBusiness.getCourseResource(pop_course_id);
		String name = (String)JSONPath.read(res, "$[0].name");
		String duration = (String)JSONPath.read(res, "$[0].duration");
		
		Assert.assertTrue(duration!=null, "未登录时查看课程的资源，实际返回结果;"+res);
		Assert.assertTrue(name!=null, "未登录时查看课程的资源,实际返回结果："+res);
	}
	
	@Test(description="未登录时查看讲师下的所有课程",priority=10)
	public void TestqueryTeacherCourse() {
		String res = NonLoginCourseMallListBusiness.queryTeacherCourse(pop_course_id, teacherId);
		String title = (String)JSONPath.read(res, "$[0].title");
		int preferentialPrice = (int)JSONPath.read(res, "$[0].preferentialPrice");
		Assert.assertTrue(preferentialPrice>=0, "未登录时查看讲师下的所有课程,实际返回结果："+res);
		Assert.assertTrue(title!=null, "未登录时查看讲师下的所有课程,实际返回结果："+res);
	}
	String industry_id = "";
	String classify_id ="";
	@Test(description="未登录时，查看行业列表",priority=11)
	public void testGetIndustrylist() {
		String res = NonLoginCourseMallListBusiness.getIndustrylist();
		String name = (String)JSONPath.read(res, "$[0].name");
		industry_id=(String)JSONPath.read(res, "$[0].children[0].id");
		Assert.assertTrue(name!=null, "未登录时，查看行业列表,实际返回结果："+res);
	}
	@Test(description="未登录时，查看岗位列表",priority=12)
	public void TestGetPostlist() {
		String res = NonLoginCourseMallListBusiness.getPostlist();
		String name = (String)JSONPath.read(res, "$[0].children[0].name");
		Assert.assertTrue(name!=null, "未登录时，查看岗位列表,实际返回结果："+res);
	}
	@Test(description="未登录时，查看领域列表",priority=13)
	public void testGetClassifylist() {
		String res = NonLoginCourseMallListBusiness.getClassifylist();
		String name = (String)JSONPath.read(res, "$[0].children[0].name");
		classify_id=(String)JSONPath.read(res, "$[0].children[0].children[0].id");
		Assert.assertTrue(name!=null, "未登录时，查看领域列表,实际返回结果："+res);
	}
	@Test(description="未登录时查看课程列表,带有搜索条件时",priority=14)
	public void testGetPageCourseList() {
		String res = NonLoginCourseMallListBusiness.getPageCourseList("", classify_id, industry_id, "");
		String title = (String)JSONPath.read(res, "$.list[0].title");
		int scanCount = (int)JSONPath.read(res, "$.list[0].scanCount");
		int officialPrice = (int)JSONPath.read(res, "$.list[0].preferentialPrice");
		String teacherName = (String)JSONPath.read(res, "$.list[0].teacherName");
		
		Assert.assertTrue(teacherName!=null, "未登录时查看课程列表,带有搜索条件时--讲师名称,实际返回结果：");
		Assert.assertTrue(officialPrice>=0, "未登录时查看课程列表,带有搜索条件时--价格,实际返回结果：");
		Assert.assertTrue(scanCount>=0, "未登录时查看课程列表,带有搜索条件时--浏览人数,实际返回结果：");
		Assert.assertTrue(title!=null, "未登录时查看课程列表,带有搜索条件时--标题,实际返回结果：");
	}
}
