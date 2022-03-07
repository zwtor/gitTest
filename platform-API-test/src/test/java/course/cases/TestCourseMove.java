package course.cases;

import cn.kxy.course.resources.bussiness.CoursewareBusiness;
import com.alibaba.fastjson.JSONPath;
import com.lazy.common.utils.CommonData;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestCourseMove {

	String fold_name = "FatherFolder"+CommonData.getStringRandom(5);
	String child_name = "ChildFolder"+CommonData.getStringRandom(5);
	String father_id = "";
	String child_id = "";
	@Test(description = "添加父文件夹",priority = 1)
	public void testAddFatherFolder() {
		String res = CoursewareBusiness.addFolder(fold_name, "0","","all");
		String msg = (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "文件夹新增成功","在课件管理页面新增文件夹实际返回结果："+res);
	}
	
	@Test(description = "添加子文件夹，供移动文件使用",priority = 2)
	public void testAddChildFolder() {
		String res = CoursewareBusiness.addFolder(child_name, "0","","mySelf");
		String msg = (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "文件夹新增成功","在课件管理页面新增文件夹实际返回结果："+res);
	}
	@Test(description = "获取父文件夹的id",priority = 3)
	public void testGetFatherFolderId() {
		String res= CoursewareBusiness.queryList(fold_name);
		father_id= (String)JSONPath.read(res, "$.list[0].resource.id");
	}
	@Test(description = "获取子文件夹的id",priority = 4)
	public void testGetChildFolderId() {
		String res= CoursewareBusiness.queryList(child_name);
		child_id= (String)JSONPath.read(res, "$.list[0].resource.id");
	}
	@Test(description = "移动文件夹",priority = 5)
	public void testDragCourseware() {
		String res = CoursewareBusiness.dragCourseware(father_id, child_id);
		String status = (String)JSONPath.read(res, "$.status");
		Assert.assertEquals(status, "true","移动文件夹,实际返回结果："+res);
	}
	@Test(description = "移动后，查看父文件夹列表",priority = 6)
	public void testQueryFatherFolderList() {
		String res= CoursewareBusiness.queryList(fold_name);
		String visibleRange = (String)JSONPath.read(res, "$.list[0].resource.visibleRange");
		Assert.assertEquals(visibleRange,"all");
	}
	
	@Test(description = "移动后，查看子文件夹列表",priority = 7)
	public void testQueryChildFolderList() {
		String res= CoursewareBusiness.queryList(child_name);
		String visibleRange = (String)JSONPath.read(res, "$.list[0].resource.visibleRange");
		Assert.assertEquals(visibleRange,"all");
	}
	
	@Test(description = "删除子文件夹",priority = 8)
	public void testDeleteChildFolder() {
		String res = CoursewareBusiness.deleteFolder(child_id);
		String msg = (String)JSONPath.read(res, "$.msg"); 
		Assert.assertEquals(msg,"文件夹删除成功","删除子文件夹，实际返回结果："+res);
	}
	@Test(description = "删除父文件夹",priority = 9)
	public void testDeleteFatherFolder() {
		String res = CoursewareBusiness.deleteFolder(father_id);
		String msg = (String)JSONPath.read(res, "$.msg"); 
		Assert.assertEquals(msg,"文件夹删除成功","删除父文件夹，实际返回结果："+res);
	}
}
