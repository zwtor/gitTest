package annoucement.cases.cases;

import cn.kxy.announcements.business.AnnouncementsBusiness;
import com.alibaba.fastjson.JSONPath;
import com.lazy.common.utils.CommonData;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestAnnouncements {

	String title ="Appreciation of famous phrases"+CommonData.getStringRandom(5);
	String des = "Time goes by so fast, people go in and out of your life. "
			+ "You must never miss the opportunity to tell these people how much they mean to you";
	String id = "";
	@Test(description="新增公告",priority=1)
	public void testAddAnnouncements() {
		String res = AnnouncementsBusiness.addAnnouncements(title, "OPEN", des,"all","");
		String insert = (String)JSONPath.read(res, "$.insert");
		System.out.println("this is a Announcement module");
		Assert.assertEquals(insert, "true","新增公告实际返回结果："+res);
	}
	@Test(description="查询公告",priority=2)
	public void testQueryAnnouncementsList () {
		String res = AnnouncementsBusiness.queryAnnouncementsList(title);
		id=(String)JSONPath.read(res, "$.list[0].id");
		String titl = (String)JSONPath.read(res, "$.list[0].title");
		Long create_time = (Long)JSONPath.read(res, "$.list[0].create_time");
		String status = (String)JSONPath.read(res, "$.list[0].status");
		Assert.assertEquals(status, "OPEN","查询公告列表，实际返回结果："+res);
		Assert.assertTrue(create_time!=null,"查询公告列表，实际返回结果："+res);
		Assert.assertEquals(titl, title,"查询公告列表，实际返回结果："+res);
	}
	@Test(description="查询公告详情",priority=3)
	public void testQuetyInfo() {
		String res = AnnouncementsBusiness.queryInfo(id, "pc");
		String name = (String)JSONPath.read(res, "$.title");
		Assert.assertEquals(name, title,"查询公告详情实际返回结果："+res);
	}
	
	@Test(description="取消顶置公告",priority=4)
	public void teststickOpenAnnouncement() {
		String res = AnnouncementsBusiness.stickAnnouncement(id, "CLOSED");
		String stick = (String)JSONPath.read(res, "$.stick");
		String res0 = AnnouncementsBusiness.queryAnnouncementsList(title);
		boolean status = (boolean)JSONPath.read(res0, "$.list[0].stick_status");
		Assert.assertEquals(status, false,"取消顶置公告后，查询公告列表，实际返回结果："+res0);
		Assert.assertEquals(stick, "true","新增公告实际返回结果："+res);
	}
	
	@Test(description="顶置公告",priority=5)
	public void teststickCloseAnnouncement() {
		String res = AnnouncementsBusiness.stickAnnouncement(id, "OPEN");
		String stick = (String)JSONPath.read(res, "$.stick");
		String res0 = AnnouncementsBusiness.queryAnnouncementsList(title);
		String status = (String)JSONPath.read(res0, "$.list[0].status");
		Assert.assertEquals(status, "OPEN","顶置公告后，查询公告列表，实际返回结果："+res0);
		Assert.assertEquals(stick, "true","新增公告实际返回结果："+res);
	}
	@Test(description="编辑公告",priority=6)
	public void testEditAnnouncements() {
		String res = AnnouncementsBusiness.editAnnouncements(id, "English appreciation" , "CLOSED", des, id,"all","");
		String update = (String)JSONPath.read(res, "$.update");
		Assert.assertEquals(update, "true","编辑公告实际返回结果："+res);
	}
	@Test(description="编辑公告时，title为null",priority=7)
	public void testEditAnnouncementsNameIsNull() {
//		String res = AnnouncementsBusiness.editAnnouncements(id, "" , "CLOSED", des, id,"all","");
//		String msg = (String)JSONPath.read(res, "$.data[0].message");
//		Assert.assertEquals(msg, "may not be empty");
	}
	
	@Test(description="删除公告",priority=8)
	public void testDeleteAnnouncements() {
		String res = AnnouncementsBusiness.deleteAnnouncements(id);
		String delete = (String)JSONPath.read(res, "$.delete");
		Assert.assertEquals(delete, "true","删除公告实际返回结果："+res);
	}
	@Test(description="新增公告时，title为null",priority=9)
	public void testAddAnnouncementsNIsNull() {
//		String res = AnnouncementsBusiness.addAnnouncements("", "OPEN", "fa ","all","");
//		String msg = (String)JSONPath.read(res, "$.data[0].message");
//		Assert.assertEquals(msg, "may not be empty");
	}
	
	@Test(description="新增公告时，状态为null",priority=10)
	public void testAddAnnouncementsNeIsNull() {
//		String res = AnnouncementsBusiness.addAnnouncements("", "OPEN", "fa ","all","");
//		String msg = (String)JSONPath.read(res, "$.data[0].message");
//		Assert.assertEquals(msg, "may not be empty");
	}
	@Test(description="新增公告时，描述为null",priority=11)
	public void testAddAnnouncementsNameIsNull() {
//		String res = AnnouncementsBusiness.addAnnouncements("", "OPEN", "fa ","all","");
//		String msg = (String)JSONPath.read(res, "$.data[0].message");
//		Assert.assertEquals(msg, "may not be empty");
	}
	@Test(description="新增公告时，可见范围e为null",priority=12)
	public void testAddAnnouncementsAtatusIsNull() {
//		String res = AnnouncementsBusiness.addAnnouncements("", "OPEN", "fa ","all","");
//		String msg = (String)JSONPath.read(res, "$.data[0].message");
//		Assert.assertEquals(msg, "may not be empty");
	}
	@Test(description="新增公告时，userid为null",priority=13)
	public void testAddAnnouncementsDesIsNull() {
//		String res = AnnouncementsBusiness.addAnnouncements("", "OPEN", "fa ","all","");
//		String msg = (String)JSONPath.read(res, "$.data[0].message");
//		Assert.assertEquals(msg, "may not be empty");
	}
	@Test(description="编辑公告时，title为null",priority=14)
	public void testAddAnnouncementsvisIsNull() {
//		String res = AnnouncementsBusiness.addAnnouncements("", "OPEN", "fa ","all","");
//		String msg = (String)JSONPath.read(res, "$.data[0].message");
//		Assert.assertEquals(msg, "may not be empty");
	}
	
}
