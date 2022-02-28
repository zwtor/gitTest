package message.cases;

import cn.kxy.message.business.MessageBusiness;
import com.alibaba.fastjson.JSONPath;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestUnReadMessage {

	String id = "";
	@Test(description = "查看未读消息--all",priority = 1)
	public void testAllMessage() {
		String res = MessageBusiness.queryMessageList("2", "0");
		String content = (String)JSONPath.read(res, "$.list[0].content");
		Assert.assertNotNull(content,"查看全部消息，实际返回结果："+res);
	}
	
	@Test(description = "查看未读的学习提醒消息",priority = 2)
	public void testQueryMessageListMind() {
		String res = MessageBusiness.queryMessageList("2", "1");
		id = (String)JSONPath.read(res, "$.list[0].id");
		String content = (String)JSONPath.read(res, "$.list[0].content");
		String type = (String)JSONPath.read(res, "$.list[0].type");
		Long creat_time = (Long)JSONPath.read(res, "$.list[0].create_time");
		Assert.assertEquals(type, "1","查看未读的学习提醒消息，消息类型进行验证"+res);
		Assert.assertNotNull(content,"查看未读的学习提醒消息，实际返回结果："+res);
		Assert.assertNotNull(creat_time,"查看未读的学习提醒消息，实际返回结果："+res);
	}
	
	@Test(description = "查看未读公告通知消息",priority = 3)
	public void testQueryMessageListNotice() {
		String res = MessageBusiness.queryMessageList("2", "2");
		String content = (String)JSONPath.read(res, "$.list[0].content");
		String type = (String)JSONPath.read(res, "$.list[0].type");
		Assert.assertEquals(type, "2","查看未读公告通知消息，消息类型进行验证"+res);
		Assert.assertNotNull(content,"查看未读公告通知消息，实际返回结果："+res);
	}
	String unRead = "";
	String read = "";
	int unRead_num ;
	int read_num ;
	@Test (description = "查看消息数",priority = 4)
	public void testQueryMessageCount() {
		String res = MessageBusiness.queryMessageCount();
		unRead = (String)JSONPath.read(res, "$.noRead");
		read = (String)JSONPath.read(res, "$.yesRead");
		unRead_num = Integer.valueOf(unRead);
		read_num = Integer.valueOf(read);
	}
	
	@Test(description = "删除消息",priority = 5)
	public void testDeleteMessage() {
		String res = MessageBusiness.deleteMessage(id);
		Assert.assertTrue(res.contains("success"),""+res);
	}
	
	@Test(description = "删除消息后对未读消息个数验证",priority = 6)
	public void testMessageCount() {
		MessageBusiness.queryMessageCount();
	}
	
}
