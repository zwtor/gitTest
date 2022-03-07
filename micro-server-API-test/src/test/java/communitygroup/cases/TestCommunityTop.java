package communitygroup.cases;

import cn.kxy.community.group.ArticleManageBusiness;
import cn.kxy.community.group.CircleBusiness;
import cn.kxy.community.group.CreationBusiness;
import cn.kxy.community.group.QuestionManageBusiness;
import com.alibaba.fastjson.JSONPath;
import com.lazy.common.utils.CommonData;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestCommunityTop {
	String circle_id = "";
	String title = "Behavior"+ CommonData.getStringRandom(5);
	String topic_name01 = "";
	String topic_name02 = "";
	
	String circle_title = "Machine" + CommonData.getStringRandom(5);
	@Test(description = "新增圈子",priority = 1)
	public void testAddCircle() {
		String res = CircleBusiness.queryStuCircle();
		circle_id = (String)JSONPath.read(res, "$.data.content[0].id");
		if (circle_id == null) {
			CircleBusiness.addCircle(circle_title);
			String res_01 = CircleBusiness.queryStuCircle();
			circle_id = (String)JSONPath.read(res_01, "$.data.content[0].id");
		}
	}
	
	@Test(description = "查看圈子",priority =2)
	public void testQueryAllTopic() {
		String res = CreationBusiness.queryAllTopic();
		topic_name01 = (String)JSONPath.read(res, "$.data[0]");
		topic_name02 = (String)JSONPath.read(res, "$.data[1]");
	}
	
	String ques_id = "";
	@Test(description = "新增提问",priority = 3)
	public void testCreatQuestion() {
		String res = CreationBusiness.creatQuestionArctile(circle_id, "question", title, topic_name01, topic_name02);
		ques_id = (String)JSONPath.read(res, "$.data.id");
		Assert.assertTrue(res.contains("succ"), "新增提问，实际返回结果："+res);
	}
	
	String art_title = "sspring" + CommonData.getStringRandom(5);
	String art_id = "";
	@Test(description = "新增文章",priority = 4)
	public void testCreatArctile() {
		String res = CreationBusiness.creatQuestionArctile(circle_id, "article", art_title, topic_name01, topic_name02);
		art_id = (String)JSONPath.read(res, "$.data.id");
		Assert.assertTrue(res.contains("succ"), "新增提问，实际返回结果："+res);
	}
	String answer_id = "";
	@Test(description = "回答问题",priority = 5)
	public void testAnswerQuestion() {
		String res = QuestionManageBusiness.answerQuestion(ques_id);
		answer_id = (String)JSONPath.read(res, "$.data.id");
		Assert.assertTrue(res.contains("succ"), "回答问题，实际返回结果："+res);
	}
	@Test(description = "置顶文章",priority = 6)
	public void pinkArtAnswer() {
		String res = CreationBusiness.pinkArt(art_id, "1");
		System.out.println(res);
	}
	@Test(description = "取消置顶文章",priority = 7)
	public void cancelpinkArtAnswer() {
		String res = CreationBusiness.pinkArt(art_id, "0");
		System.out.println(res);
	}
	
	@Test(description = "置顶回答",priority = 8)
	public void pinkAnswer() {
		String res = CreationBusiness.pinkAnswer(ques_id, answer_id, "1");
		System.out.println(res);
	}
	@Test(description = "取消置顶回答",priority = 9)
	public void cancelpinkAnswer() {
		String res = CreationBusiness.pinkAnswer(ques_id, answer_id, "0");
		System.out.println(res);
	}
	
	
	
	@Test(description = "删除文章",priority = 10)
	public void testDeleteArtcicle1() {
		String res = ArticleManageBusiness.deleteArtcicle(art_id, "false");
		Assert.assertTrue(res.contains("succ"), "删除文章，实际返回结果："+res);
	}
	

	@Test(description = "删除提问",priority = 11)
	public void testDeleteQuestion1() {
		String res = QuestionManageBusiness.deleteQuestion(ques_id,"false");
		Assert.assertTrue(res.contains("succ"), "删除提问，实际返回结果："+res);
	}
	
	
	@Test(description = "新增圈子",priority = 12)
	public void testAddCircle1() {
//		String res = CircleBusiness.queryStuCircle();
//		circle_id = (String)JSONPath.read(res, "$.data.content[0].id");
//		if (circle_id == null) {
//			CircleBusiness.addCircle(circle_title);
//			String res_01 = CircleBusiness.queryStuCircle();
//			circle_id = (String)JSONPath.read(res_01, "$.data.content[0].id");
//		}
	}
	
	@Test(description = "查看圈子",priority =13)
	public void testQueryAllTopic1() {
//		String res = CreationBusiness.queryAllTopic();
//		topic_name01 = (String)JSONPath.read(res, "$.data[0]");
//		topic_name02 = (String)JSONPath.read(res, "$.data[1]");
	}
	
	String ques_id1 = "";
	@Test(description = "新增提问",priority = 14)
	public void testCreatQuestion2() {
//		String res = CreationBusiness.creatQuestionArctile(circle_id, "question", title, topic_name01, topic_name02);
//		ques_id = (String)JSONPath.read(res, "$.data.id");
//		Assert.assertTrue(res.contains("succ"), "新增提问，实际返回结果："+res);
	}
	
	String art_title1 = "sspring" + CommonData.getStringRandom(5);
	String art_id1 = "";
	@Test(description = "新增文章",priority = 15)
	public void testCreatArctile1() {
//		String res = CreationBusiness.creatQuestionArctile(circle_id, "article", art_title, topic_name01, topic_name02);
//		art_id = (String)JSONPath.read(res, "$.data.id");
//		Assert.assertTrue(res.contains("succ"), "新增提问，实际返回结果："+res);
	}
	String answer_id1 = "";
	@Test(description = "回答问题",priority = 16)
	public void testAnswerQuestion1() {
//		String res = QuestionManageBusiness.answerQuestion(ques_id);
//		answer_id = (String)JSONPath.read(res, "$.data.id");
//		Assert.assertTrue(res.contains("succ"), "回答问题，实际返回结果："+res);
	}
	@Test(description = "置顶文章",priority = 17)
	public void pinkArtAnswer1() {
//		String res = CreationBusiness.pinkArt(art_id, "1");
//		System.out.println(res);
	}
	@Test(description = "取消置顶文章",priority = 18)
	public void cancelpinkArtAnswer1() {
//		String res = CreationBusiness.pinkArt(art_id, "0");
//		System.out.println(res);
	}
	
	@Test(description = "置顶回答",priority = 19)
	public void pinkAnswer1() {
//		String res = CreationBusiness.pinkAnswer(ques_id, answer_id, "1");
//		System.out.println(res);
	}
	@Test(description = "取消置顶回答",priority = 20)
	public void cancelpinkAnswer1() {
//		String res = CreationBusiness.pinkAnswer(ques_id, answer_id, "0");
//		System.out.println(res);
	}
	
	
	@Test(description = "删除文章",priority = 21)
	public void testDeleteArtcicle() {
//		String res = ArticleManageBusiness.deleteArtcicle(art_id, "false");
//		Assert.assertTrue(res.contains("succ"), "删除文章，实际返回结果："+res);
	}
	

	@Test(description = "删除提问",priority = 22)
	public void testDeleteQuestion() {
//		String res = QuestionManageBusiness.deleteQuestion(ques_id,"false");
//		Assert.assertTrue(res.contains("succ"), "删除提问，实际返回结果："+res);
	}
	
}
