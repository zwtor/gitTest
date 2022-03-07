package course.cases;

import cn.kxy.course.resources.bussiness.AppCourseBusiness;
import cn.kxy.course.resources.bussiness.ArticleBusiness;
import cn.kxy.course.resources.bussiness.CourseBusiness;
import cn.kxy.lecturer.business.LecturerListBusiness;
import init.cases.InitStudyAuthCourse;
import com.alibaba.fastjson.JSONPath;
import com.lazy.common.utils.CommonData;
import org.testng.Assert;
import org.testng.annotations.Test;
//新建一个图文课件，然后用它新建课程，然后把这个课件修改成链接型的课件，这时在移动端打开还是以图文方式打开，因为课件类型移动端返回的还是图文类型的bug
public class TestOncallCourse extends InitStudyAuthCourse{
	String name = "Phy" + CommonData.getStringRandom(5);
	String art_id = "";
	@Test(description = "在主目录下新增图文文章", priority = 1)
	public void testAddlinkArticle() {
		String res = ArticleBusiness.addArticle(name, "h5/link", "http://www.baidu.com", "", "0");
		String ids = (String) JSONPath.read(res, "$.id");
		art_id = ids;
		
		Assert.assertTrue(!ids.isEmpty(), "在主目录下新增url文章实际返回结果：" + res);
	}
	String course_name = "Math"+CommonData.getStringRandom(4);
	String id = "";
	@Test(description = "将课件变为图文类型",priority = 2)
	public void edit() {
		ArticleBusiness.editArticle(art_id, name, "h5/article", "",
				"{\"blocks\":[{\"key\":\"b2glt\",\"text\":\"this is a descption\",\"type\":\"unstyled\","
						+ "\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[],\"data\":{}}],\"entityMap\":{}}",
				"0");
	}
	@Test(description="新增课程到草稿箱",priority=3)
	public void testAddCourse() {
		String res = CourseBusiness.addCourse(course_name, "1", "this is desription", LecturerListBusiness.getIdByKeyword(outer_name), 
				art_id, "1", "1", "", "0", "3", "draft");
		String msg = (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "保存草稿成功","新增课程到草稿箱实际返回结果："+res);
	}
	
	@Test(description="发布课程",priority=4)
	public void testCourseUnreleasedList() {
		String res = CourseBusiness.courseManageList(course_name, "all", "unreleased","","","all");
		id= (String)JSONPath.read(res, "$.list[0].course_id");
		CourseBusiness.releaseCancleCourse("release",id);
	}
	String courseId = "";
	@Test(description =  "查看未修改文章前的app端课程详情",priority = 5)
	public void testqueryAppCoursesList() {
		String info_res = AppCourseBusiness.queryAppCourseInfo(id);
		String type = (String)JSONPath.read(info_res, "$.resources[0].contentType");
		Assert.assertEquals(type, "h5/h5/article","查看未修改文章前的app端课程详情"+info_res);
	}
	@Test(description = "将图文文章编辑为链接的文章", priority = 6)
	public void testEditlinkArticle() {
		String res = ArticleBusiness.editArticle(art_id, name, "h5/link", "",
				"https://www.baidu.com","0");
		String name01 = (String) JSONPath.read(res, "$.name");
		Assert.assertEquals(name01, name, "将图文文章编辑为链接的文章，实际返回结果" + res);
	}
	@Test(description =  "查看app端课程详情",priority = 7)
	public void testqueryAppCourseInfo() {
		String info_res = AppCourseBusiness.queryAppCourseInfo(id);
		String type = (String)JSONPath.read(info_res, "$.resources[0].contentType");
		Assert.assertEquals(type, "h5/h5/link","查看未修改文章前的app端课程详情"+info_res);
	}
	@Test(description = "测试完成后删除课程",priority = 8)
	public void testdeleteCourse() {
		CourseBusiness.deleteCourse(id);
		
	}
	@Test(description = "测试完成后删除文章",priority = 9)
	public void testdeleteArticle() {
		ArticleBusiness.deleteArticle(art_id);
	}
}
