package communitygroup.cases;

import com.lazy.common.utils.CommonData;
import org.testng.annotations.Test;

public class TestCommunity {

	String circle_id = "";
	String title = "Behavior"+ CommonData.getStringRandom(5);
	String topic_name01 = "";
	String topic_name02 = "";
	
	String circle_title = "Machine" + CommonData.getStringRandom(5);
	@Test(description = "新增圈子",priority = 1)
	public void testAddCircle() {
//		String res = CircleBusiness.queryStuCircle();
//		circle_id = (String)JSONPath.read(res, "$.data.content[0].id");
//		if (circle_id == null) {
//			CircleBusiness.addCircle(circle_title);
//			String res_01 = CircleBusiness.queryStuCircle();
//			circle_id = (String)JSONPath.read(res_01, "$.data.content[0].id");
//		}
	}
	
	@Test(description = "查看圈子",priority =2)
	public void testQueryAllTopic() {
//		String res = CreationBusiness.queryAllTopic();
//		topic_name01 = (String)JSONPath.read(res, "$.data[0]");
//		topic_name02 = (String)JSONPath.read(res, "$.data[1]");
	}
	int answerCount = 0;
	int articleCount = 0;
	
	String ques_id = "";
	@Test(description = "新增提问",priority = 4)
	public void testCreatQuestion() {
//		String res = CreationBusiness.creatQuestionArctile(circle_id, "question", title, topic_name01, topic_name02);
//		ques_id = (String)JSONPath.read(res, "$.data.id");
//		Assert.assertTrue(res.contains("succ"), "新增提问，实际返回结果："+res);
	}
	
	String art_title = "sspring" + CommonData.getStringRandom(5);
	String art_id = "";
	@Test(description = "新增文章",priority = 5)
	public void testCreatArctile() {
//		String res = CreationBusiness.creatQuestionArctile(circle_id, "article", art_title, topic_name01, topic_name02);
//		art_id = (String)JSONPath.read(res, "$.data.id");
//		Assert.assertTrue(res.contains("succ"), "新增提问，实际返回结果："+res);
	}
	@Test(description = "新增文章和提问时，圈子文章和提问的个数加1",priority = 6)
	public void testCount() {
//		String res = CircleBusiness.queryStuCircle();
//		int end_answerCount = (int)JSONPath.read(res, "$.data.content[0].answerCount");
//		int end_articleCount = (int)JSONPath.read(res, "$.data.content[0].articleCount");
//		Assert.assertTrue(end_answerCount>= answerCount,"新增文章和提问时，提问的个数加1"+res);
//		Assert.assertTrue(end_articleCount>= articleCount,"新增文章和提问时，圈子文章的个数加1"+res);
	}
	
	@Test(description = "回答问题",priority = 7)
	public void testAnswerQuestion() {
//		String res = QuestionManageBusiness.answerQuestion(ques_id);
//		answer_id = (String)JSONPath.read(res, "$.data.id");
//		Assert.assertTrue(res.contains("succ"), "回答问题，实际返回结果："+res);
	}
	@Test(description = "查看问题详细",priority = 8)
	public void testQueryQuestionInfo() {
//		String res = QuestionManageBusiness.queryQuestionInfo(ques_id);
//		Assert.assertTrue(res.contains(title), "查看问题详细，实际返回结果："+res);
	}
	
	@Test(description = "查看首页搜索列表",priority = 9)
	public void testQuertQuestionList() {
//		String res = QuestionManageBusiness.quertQuestionList(title);
//		question_id = (String)JSONPath.read(res, "$.data.content[0].id");
	}
	
	String answer_id = "";
	String question_id = "";
	@Test(description = "查看问题的答案列表",priority = 10)
	public void testQueryAnswer() {
//		String res = QuestionManageBusiness.queryAnswer(ques_id);
//		Assert.assertTrue(res.contains("succ"), "查看问题的答案列表，实际返回结果："+res);
	}
	@Test(description = "对回答进行点赞",priority = 11)
	public void testUnstarAnswer() {
//		String res = QuestionManageBusiness.starAnswer(question_id,answer_id,"star");
//		Assert.assertTrue(res.contains("succ"), "对回答进行点赞，实际返回结果："+res);
	}
	
	@Test(description = "对回答进行取消点赞",priority = 12)
	public void testStarAnswer() {
//		String res = QuestionManageBusiness.starAnswer(question_id,answer_id,"unstar");
//		Assert.assertTrue(res.contains("succ"), "对回答进行取消点赞，实际返回结果："+res);
	}
	@Test(description = "删除回答",priority = 13)
	public void testDeleteAnswer() {
//		String res = QuestionManageBusiness.deleteAnswer(question_id, answer_id);
//		Assert.assertTrue(res.contains("succ"), "删除回答，实际返回结果："+res);
	}
	@Test(description = "邀请回答",priority = 14)
	public void testInvitationAnswer() {
//		String res = QuestionManageBusiness.invitationAnswer(question_id);
//		Assert.assertTrue(res.contains("succ"), "邀请回答，实际返回结果："+res);
	}
	
	
	@Test(description = "对文章进行点赞",priority = 16)
	public void testStarArticle() {
//		String res = ArticleManageBusiness.starArticle(art_id);
//		Assert.assertTrue(res.contains("succ"), "对文章进行点赞，实际返回结果："+res);
	}
	
	@Test(description = "对文章进行点赞",priority = 17)
	public void testUnStarArticle() {
//		String res = ArticleManageBusiness.unstarArticle(art_id);
//		Assert.assertTrue(res.contains("succ"), "对文章进行点赞，实际返回结果："+res);
	}
	
	@Test(description = "评论文章",priority = 18)
	public void testCommentArticle() {
//		String res = ArticleManageBusiness.commentArticle(art_id);
//		Assert.assertTrue(res.contains("succ"), "评论文章，实际返回结果："+res);
	}
	
	@Test(description = "查看文章详情",priority =19)
	public void testQueryArcticleInfo() {
//		String res = ArticleManageBusiness.queryArcticleInfo(art_id);
//		Assert.assertTrue(res.contains(art_title), "查看文章详情，实际返回结果："+res);
	}
	String comment_id = "";
	@Test(description = "查看文章的评论",priority = 20)
	public void testQueryArticleComment() {
//		String res = ArticleManageBusiness.queryArticleComment(art_id);
//		comment_id = (String)JSONPath.read(res, "$.data.content[0].id");
	}
	
	@Test(description = "回复评论",priority = 21)
	public void testReplyCommentArticle() {
//		String res = ArticleManageBusiness.replyCommentArticle(art_id, comment_id);
//		System.out.println(res);
	}
	String reply_id = "";
	@Test(description = "查看文章的评论",priority = 22)
	public void testGetReplyId() {
//		String res = ArticleManageBusiness.queryArticleComment(art_id);
//		reply_id = (String)JSONPath.read(res, "$.data.content[0].reply[0].id");
	}
	
	@Test(description = "删除回复的评论",priority = 23)
	public void testDeleteReplyCommentArticle() {
//		String res = ArticleManageBusiness.deleteCommentArticle(art_id, reply_id);
//		Assert.assertTrue(res.contains("succ"), "删除回复的评论，实际返回结果："+res);
	}
	
	@Test(description = "删除评论",priority = 24)
	public void testDeleteCommentArticle() {
//		String res = ArticleManageBusiness.deleteCommentArticle(art_id, comment_id);
//		Assert.assertTrue(res.contains("succ"), "删除回复的评论，实际返回结果："+res);
	}
	
	@Test(description = "查看圈子下的文章",priority = 25)
	public void testQueryArticleByCircle() {
//		String res = ArticleManageBusiness.queryArticleByCircle(circle_id,"article");
//		System.out.println(res);
	}
	
	@Test(description = "查看圈子下的提问",priority = 26)
	public void testQueryQuestionByCircle() {
//		String res = ArticleManageBusiness.queryArticleByCircle(circle_id,"question");
//		System.out.println(res);
	}
	
	@Test(description = "删除文章",priority = 27)
	public void testDeleteArtcicle() {
//		String res = ArticleManageBusiness.deleteArtcicle(art_id, "false");
//		Assert.assertTrue(res.contains("succ"), "删除文章，实际返回结果："+res);
	}
	

	@Test(description = "删除提问",priority = 28)
	public void testDeleteQuestion() {
//		String res = QuestionManageBusiness.deleteQuestion(ques_id,"false");
//		Assert.assertTrue(res.contains("succ"), "删除提问，实际返回结果："+res);
	}
}
