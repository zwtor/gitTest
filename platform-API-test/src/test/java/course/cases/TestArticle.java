package course.cases;

import cn.kxy.course.resources.bussiness.ArticleBusiness;
import cn.lazy.init.cases.InitStudyAuthCourse;
import com.alibaba.fastjson.JSONPath;
import com.lazy.common.utils.CommonData;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestArticle extends InitStudyAuthCourse{
	String name = "Chinese" + CommonData.getStringRandom(5);
	String edit_name = "editChinese" + CommonData.getStringRandom(5);
	String url_name = "url"+CommonData.getStringRandom(5);
	String edit_url_name = "editurl"+CommonData.getStringRandom(5);
	String id_01 = "";

	String id_02="";
	@Test(description = "在主目录下新增url文章", priority = 1)
	public void testAddlinkArticle() {
		String res = ArticleBusiness.addArticle(url_name, "h5/link", "http://www.baidu.com", "", "0");
		String ids = (String) JSONPath.read(res, "$.id");
		id_01 = ids;
		Assert.assertTrue(!ids.isEmpty(), "在主目录下新增url文章实际返回结果：" + res);
	}

	@Test(description = "在主目录下编辑图文类型的文章", priority = 2)
	public void testEditlinkArticle() {
		String res = ArticleBusiness.editArticle(id_01, edit_url_name, "h5/article", "",
				"{\"blocks\":[{\"key\":\"b2glt\",\"text\":\"this is a descption\",\"type\":\"unstyled\","
						+ "\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[],\"data\":{}}],\"entityMap\":{}}",
				"0");
		String name01 = (String) JSONPath.read(res, "$.name");
		Assert.assertEquals(name01, edit_url_name, "在主目录下编辑图文类型的文章实际返回结果" + res);
	}

	@Test(description = "在主目录下查询文章列表", priority = 3)
	public void testQuerylinkArticleList() {
//		String res = CoursewareBusiness.queryPictureArtList(edit_url_name, "released");
//		String name01 = (String) JSONPath.read(res, "$.list[0].resource.title");
//		String creatorName = (String) JSONPath.read(res, "$.list[0].resource.creatorName");
//		String contentType = (String) JSONPath.read(res, "$.list[0].resource.contentType");
//		Assert.assertEquals(contentType, "h5/h5/article", "在主目录下查询文章列表实际返回结果" + res);
//		Assert.assertEquals(creatorName, UserBusiness.getUsername(), "在主目录下查询文章列表实际返回结果" + res);
//		Assert.assertEquals(name01, edit_url_name, "在主目录下查询文章列表实际返回结果" + res);
	}

	
	
	@Test(description = "在主目录下删除图文类型的文章", priority = 4)
	public void testDeletelinkArticlet() {
		String res = ArticleBusiness.deleteArticle(id_01);
		String msg = (String) JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "删除课件成功", "在主目录下删除图文类型的文章实际返回结果：" + res);
	}

	@Test(description = "在文件夹下新增图文类型的文章", priority = 5)
	public void testAddArticle() {
		String res = ArticleBusiness.addArticle(name, "h5/link", "http://www.baidu.com", "","0");
		String ids = (String) JSONPath.read(res, "$.id"); 
		id_02 = ids;
		Assert.assertTrue(!ids.isEmpty(), "在主目录下新增图文类型的文章实际返回结果：" + res);
	}
	
	@Test(description = "在文件夹下编辑图文类型的文章", priority = 6)
	public void testEditArticle() {
		String res = ArticleBusiness.editArticle(id_02, edit_name, "h5/article", "",
				"{\"blocks\":[{\"key\":\"b2glt\",\"text\":\"this is a descption\",\"type\":\"unstyled\","
						+ "\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[],\"data\":{}}],\"entityMap\":{}}",
				"0");
		String name01 = (String) JSONPath.read(res, "$.name");
		Assert.assertEquals(name01, edit_name, "在主目录下编辑图文类型的文章实际返回结果" + res);
	}

	@Test(description = "在文件夹下查询文章列表", priority = 7)
	public void testQueryArticleList() {
//		String res = CoursewareBusiness.queryPictureArtList(edit_name, "released");
//		String name01 = (String) JSONPath.read(res, "$.list[0].resource.title");
//		String creatorName = (String) JSONPath.read(res, "$.list[0].resource.creatorName");
//		String contentType = (String) JSONPath.read(res, "$.list[0].resource.contentType");
//		Assert.assertEquals(contentType, "h5/h5/article", "在主目录下查询文章列表实际返回结果" + res);
//		Assert.assertEquals(creatorName, UserBusiness.getUsername(), "在主目录下查询文章列表实际返回结果" + res);
//		Assert.assertEquals(name01, edit_name, "在主目录下查询文章列表实际返回结果" + res);
	}

	@Test(description="查询文章详情",priority=8)
	public void testQueryInfo() {
		String res = ArticleBusiness.queryInfo(id_02);
		String name = (String) JSONPath.read(res, "$.name");
		Assert.assertEquals(name, edit_name, "在主目录下查询文章详情,实际返回结果：" + res);
	}
	  @Test(description = "在文件夹下删除图文类型的文章", priority = 9) 
	  public void testDeleteArticlet() { 
		  String res = ArticleBusiness.deleteArticle(id_02);
		  String msg = (String) JSONPath.read(res, "$.msg"); 
		  Assert.assertEquals(msg,"删除课件成功", "在主目录下删除图文类型的文章实际返回结果：" + res); 
	  }
	 
}
