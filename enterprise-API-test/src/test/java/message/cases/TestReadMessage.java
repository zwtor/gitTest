package message.cases;

import cn.kxy.message.business.MessageBusiness;
import com.alibaba.fastjson.JSONPath;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestReadMessage {
	
	String id = "";
	@Test(description = "获取id",priority = 1)
	public void testGetId() {
		String res = MessageBusiness.queryMessageList("1", "0");
		id = (String)JSONPath.read(res, "$.list[0].id");
		String content = (String)JSONPath.read(res, "$.list[0].content");
		Assert.assertNotNull(content,"查看已读的学习提醒消息，实际返回结果："+res);
	}
	@Test(description = "查看已读消息--all",priority = 2)
	public void testAllMessage() {
		String res = MessageBusiness.queryMessageList("1", "0");
		String content = (String)JSONPath.read(res, "$.list[0].content");
		Assert.assertNotNull(content,"查看全部消息，实际返回结果："+res);
	}
	
	
	@Test(description = "查看已读公告通知消息",priority = 3)
	public void testQueryMessageListNotice() {
		String res = MessageBusiness.queryMessageList("1", "2");
		Assert.assertTrue(res.contains("success"),"查看已读公告通知消息，实际返回结果："+res);
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
		Assert.assertNotNull(unRead_num,"查看消息总数"+res);
		Assert.assertNotNull(read_num,"查看消息总数"+res);
	}
	
	@Test(description = "标记消息为已读",priority = 5)
	public void testDeleteMessage() {
		String res = MessageBusiness.readMessage(id);
		Assert.assertTrue(res.contains("success"),""+res);
	}
	
	@Test(description = "标记消息为已读后，已读消息数加1",priority = 6)
	public void testMessageCount() {
		MessageBusiness.queryMessageCount();
	}
	
	@Test(description = "查看已读的学习提醒消息",priority = 7)
	public void testQueryMessageListMind() {
		String res = MessageBusiness.queryMessageList("1", "1");
		String content = (String)JSONPath.read(res, "$.list[0].content");
		Long creat_time = (Long)JSONPath.read(res, "$.list[0].create_time");
		Assert.assertNotNull(content,"查看已读的学习提醒消息，实际返回结果："+res);
		Assert.assertNotNull(creat_time,"查看已读的学习提醒消息，实际返回结果："+res);
	}
	

	@Test(description = "清除消息",priority = 8)
	public void deleteMessage() {
//		for (int i = 0; i < 15; i++) {
//			String res = MessageBusiness.deleteMessage();
//			System.out.println(res+i);
//		}
		
	}
}
