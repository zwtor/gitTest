package group.a.cases;

/**
 * @author wenlingzhi
 *2021年1月11日
 */

import org.testng.annotations.Test;

public class TestTinteraction {	
//	public static String pk_id = "";
//	public static String second_pk_id = "";
//	public static String third_pk_id = "";
//	public static String instance_id = "";
//	public static String first_questions_id = "";
//	public static String type = "";
//	String bank_id ="";
//	String title = "Pk_Game" + CommonData.getStringRandom(5);
	
 	@Test(description="1.创建多人对战PK赛接口", priority=1)
 	public void testCompetitionSave()  {
// 		String res = ExaminationBusines.queryQuestionBankList(BaseBusiness.examPassNameByPractice, "false", "");
// 		bank_id =  (String)JSONPath.read(res, "$.data.list[0].id");
// 		String res_multiple = InteractionBusiness.CompetitionSave(title,"multiple","part",bank_id,"2","1");
// 		String msg_multiple = (String) JSONPath.read(res_multiple, "$.message");
// 		System.out.println("1.创建多人对战接口:"+msg_multiple);
// 		Assert.assertEquals(msg_multiple,"新增成功", "1.创建多人对战接口：" + res_multiple);
 	}
 	
 	
 	@Test(description="2.创建多人对战PK赛-名称已存在接口", priority=2)
 	public void testCompetitionSaveExitsName()  {
// 		String res_multiple = InteractionBusiness.CompetitionSave(title,"multiple","part",bank_id,"2","1");
// 		String msg_multiple = (String) JSONPath.read(res_multiple, "$.message");
// 		System.out.println("2.创建多人对战PK赛-名称已存在接口:"+msg_multiple);
// 		Assert.assertEquals(msg_multiple,"PK赛名称重复，请重新输入", "2.创建多人对战PK赛-名称已存在接口：" + res_multiple);
 	}
 	
 	@Test(description="3.创建多人对战PK赛-未设置积分接口", priority=3)
 	public void testCompetitionSaveNoPoints()  {
// 		String titleTemp = title + "无积分";
// 		String res_multiple = InteractionBusiness.CompetitionSave(titleTemp,"multiple","part",bank_id,"0","0");
// 		String msg_multiple = (String) JSONPath.read(res_multiple, "$.message");
// 		System.out.println("3.创建多人对战PK赛-未设置积分接口:"+msg_multiple);
// 		Assert.assertEquals(msg_multiple,"新增成功", "3.创建多人对战PK赛-未设置积分接口：" + res_multiple);
 	}
 	
 	
 	@Test(description="4.创建多人对战PK赛-可见范围全公司接口", priority=4)
 	public void testCompetitionSaveAll()  {
// 		String titleTemp = title + "可见范围全公司";
// 		String res_multiple = InteractionBusiness.CompetitionSave(titleTemp,"multiple","all",bank_id,"0","0");
// 		String msg_multiple = (String) JSONPath.read(res_multiple, "$.message");
// 		System.out.println("4.创建多人对战PK赛-可见范围全公司接口:"+msg_multiple);
// 		Assert.assertEquals(msg_multiple,"新增成功", "4.创建多人对战PK赛-可见范围全公司接口：" + res_multiple);
 	}
 	
 	
 	@Test(description="5.查询PK赛列表接口", priority=5)
 	public void testCompetitionList( ) {
// 		String res = InteractionBusiness.CompetitionList(title,"");
// 		pk_id = (String) JSONPath.read(res, "$.data.list[0].id");
// 		second_pk_id = (String) JSONPath.read(res, "$.data.list[1].id");
// 		third_pk_id = (String) JSONPath.read(res, "$.data.list[2].id");
// 		type = (String) JSONPath.read(res, "$.data.list[0].type");
// 		String msg = (String)JSONPath.read(res, "$.message");
// 		String resultCode = (String)JSONPath.read(res, "$.resultCode");
// 		System.out.println("5.查询PK赛列表接口:"+"resultCode="+resultCode+","+"pk_id="+pk_id);
// 		Assert.assertEquals(msg, "","5.查询PK赛列表接口：" + res);
 	}
 	
 	@Test(description="6.编辑PK赛接口", priority=6)
 	public void testCompetitonEdit( ) {
// 		title = title + "编辑";
// 		String res_multiple = InteractionBusiness.CompetitonEdit(pk_id,title,"multiple","part",bank_id,"2","1");
// 		String resultCode = (String)JSONPath.read(res_multiple, "$.resultCode");
// 		String msg = (String)JSONPath.read(res_multiple, "$.message");
// 		System.out.println("6.编辑PK赛接口："+"msg="+msg+","+"resultCode="+resultCode);
// 		Assert.assertEquals(msg, "success","6.编辑PK赛接口：" + res_multiple);
 	}
 	
 	@Test(description="7.移动端发起PK邀请接口", priority=7)
 	public void testCompetitionInvite( ) {
// 		String res = InteractionBusiness.CompetitionInvite(pk_id);
// 		String msg = (String)JSONPath.read(res, "$.message");
// 		instance_id = (String)JSONPath.read(res, "$.data.instance_id");
// 		System.out.println("7.移动端发起PK邀请接口："+res);
// 		Assert.assertEquals(msg, "SUCCESS","7.移动端发起PK邀请接口：" + res);
 	}
 	
 	@Test(description = "8.移动端发起PK邀请-先删除PK赛接口",priority = 8)
 	public void testDeletePkGameDeleted() {
// 		String res = InteractionBusiness.deletePkGame(second_pk_id);
// 		Assert.assertTrue(res.contains("删除成功"),"删除pk赛，实际返回值：" + res);
 	}
 	
 	
 	@Test(description="9.移动端发起PK邀请-PK赛已删除接口", priority=9)
 	public void testCompetitionInviteDeleted( ) {
// 		String res = InteractionBusiness.CompetitionInvite(second_pk_id);
// 		String msg = (String)JSONPath.read(res, "$.message");
// 		System.out.println("9.移动端发起PK邀请-PK赛已删除接口："+res);
// 		Assert.assertEquals(msg, "比赛已删除","9.移动端发起PK邀请-PK赛已删除接口：" + res);
 	}
 	
 	
 	@Test(description="10.移动端PK赛首页查询接口", priority=10)
 	public void testMyHome( ) {
// 		String res = InteractionBusiness.MyHome();
// 		String msg = (String)JSONPath.read(res, "$.message");
// 		System.out.println("10.移动端PK赛首页查询接口:"+"msg="+msg);
// 		Assert.assertEquals(msg, "success","10.移动端PK赛首页查询接口：" + res);
 	}
 	
 	@Test(description="11.移动端我的PK待处理列表查询接口", priority=11)
 	public void testMyPKList( ) {
 		//待处理列表
// 		String res = InteractionBusiness.MyPkList("pending");
// 		Assert.assertTrue(res.contains("success"),"11.移动端我的PK待处理列表查询接口：" + res);
 	}
 	
 	@Test(description = "12.移动端我的PK已应战列表查询接口",priority = 12)
 	public void testMyPkChallengedList() {
// 		String res = InteractionBusiness.MyPkList("challenged");
// 		Assert.assertTrue(res.contains("success"),"12.移动端我的PK已应战列表查询接口：" + res);
 	}
 	
 	@Test(description = "13.移动端我的PK已参战列表查询接口",priority = 13)
 	public void testMyPkEndList() {
// 		String res = InteractionBusiness.MyPkList("end");
// 		Assert.assertTrue(res.contains("success"),"13.移动端我的PK已参战列表查询接口" + res);
 	}
 	
 	@Test(description="14.开始对战获取试卷内容接口", priority=14)
 	public void testPkStart( ) {
// 		String res = InteractionBusiness.PkStart(instance_id);
// 		first_questions_id = (String)JSONPath.read(res, "$.data.questions[0].options[0].id");
// 		String msg = (String)JSONPath.read(res, "$.message");
// 		System.out.println("14.开始对战获取试卷内容接口:"+"first_questions_id="+first_questions_id+","+"msg="+msg);
// 		Assert.assertEquals(msg, "SUCCESS","14.开始对战获取试卷内容接口：" + res);
 	}
 	
 	@Test(description="15.开始对战-status状态检验接口", priority=15)
 	public void testMyPkStatus( ) {
// 		String res = InteractionBusiness.MyPkStatus(pk_id,instance_id);
// 		String msg = (String)JSONPath.read(res, "$.message");
// 		System.out.println("15.开始对战status状态检验接口:"+"msg="+msg);
// 		Assert.assertEquals(msg, "SUCCESS","15.开始对战status状态检验接口：" + res);
 	}
 	
 	@Test(description="16.开始对战-detail状态检验接口", priority=16)
 	public void testMyPkDetail( ) {
// 		String res = InteractionBusiness.MyPkDetail(pk_id);
// 		String resultCode = (String)JSONPath.read(res, "$.resultCode");
// 		System.out.println("16.开始对战detail状态检验接口:"+"resultCode="+resultCode);
// 		Assert.assertEquals(resultCode, "200","16.开始对战detail状态检验接口：" + res);
 	}
 	
 	@Test(description="17.开始对战保存答案接口", priority=17)
 	public void testPKSave( ) {
// 		String res = InteractionBusiness.PKSave(instance_id,first_questions_id);
// 		System.out.println("res:"+res);
// 		String msg = (String)JSONPath.read(res, "$.message");
// 		System.out.println("17.开始对战保存答案接口:"+"msg="+msg);
// 		Assert.assertEquals(msg, "SUCCESS","17.开始对战保存答案接口：" + res);
 	}
 	
 	@Test(description="18.获取试题得分接口", priority=18)
 	public void testPkStatistics( ) {
// 		String res = InteractionBusiness.PkStatistics(instance_id,type);
// 		String msg = (String)JSONPath.read(res, "$.message");
// 		System.out.println("18.获取试题得分接口:"+"msg="+msg);
// 		Assert.assertEquals(msg, "SUCCESS","18.获取试题得分接口：" + res);
 	}
 	
 	@Test(description="19.提交试卷接口", priority=19)
 	public void testPkAnswer( ) {
// 		String res = InteractionBusiness.PkAnswer(instance_id,type);
// 		String msg = (String)JSONPath.read(res, "$.message");
// 		System.out.println("19.提交试卷接口:"+"msg="+msg);
// 		Assert.assertEquals(msg, "SUCCESS","19.提交试卷接口：" + res);
 	}
 	
 	@Test(description="20.获取得分或者结果接口", priority=20)
 	public void testPkResult( ) {
// 		String res = InteractionBusiness.PkResult(instance_id,type);
// 		String msg = (String)JSONPath.read(res, "$.message");
// 		System.out.println("20.获取得分或者结果接口:"+"msg="+msg);
// 		Assert.assertEquals(msg, "SUCCESS","20.获取得分或者结果接口：" + res);
 	}
 	
 	@Test(description="21.答题记录列表接口", priority=21)
 	public void testPkAnswerList( ) {
// 		String res = InteractionBusiness.PkAnswerList(type);
// 		String msg = (String)JSONPath.read(res, "$.message");
// 		System.out.println("21.答题记录列表接口:"+"msg="+msg);
// 		Assert.assertTrue(res.contains("success"),"21.答题记录列表接口：" + res);
 	}
 	
 	@Test(description="22.PK赛排行榜接口", priority=22)
 	public void testPkTopRanking( ) {
// 		String res = InteractionBusiness.PkTopRanking();
// 		String msg = (String)JSONPath.read(res, "$.message");
// 		System.out.println("22.PK赛排行榜接口:"+"msg="+msg);
// 		Assert.assertEquals(msg, "success","22.PK赛排行榜接口：" + res);
 	}
 	
 	@Test(description = "23.查看数据列表",priority = 23)
 	public void testQueryDataList() {
// 		String res = InteractionBusiness.queryDataList(pk_id, "", type);
// 		Assert.assertTrue(res.contains(exp),"删除pk赛，实际返回值：" + res);
 	}
 	
 	@Test(description="24.查询PK赛多人对战列表", priority=24)
 	public void testCompetitionMultipleList( ) {
// 		String res = InteractionBusiness.CompetitionList(title,"multiple");
// 		String resultCode = (String)JSONPath.read(res, "$.resultCode");
// 		Assert.assertEquals(resultCode, "200","24.查询PK赛多人对战列表：" + res);
 	}
 	
 	@Test(description="25.查询PK赛多人组队Pk列表", priority=25)
 	public void testCompetitionTeamList( ) {
// 		String res = InteractionBusiness.CompetitionList(title,"team");
// 		String resultCode = (String)JSONPath.read(res, "$.resultCode");
// 		Assert.assertEquals(resultCode, "200","25.查询PK赛多人组队Pk列表：" + res);
 	}
 	
 	@Test(description = "26.导出列表",priority = 26)
 	public void testExportList() {
// 		String res = InteractionBusiness.exportList("");
// 		Assert.assertTrue(res.contains("export success"),"导出列表，实际返回值：" + res);
 	}
 	
 	@Test(description = "27.导出数据列表",priority = 27)
 	public void testExportDataList() {
// 		String res = InteractionBusiness.exportDataList(pk_id);
// 		Assert.assertTrue(res.contains("export success"),"导出数据列表，实际返回值：" + res);
 	}
 	
 	
 	@Test(description="28.PK赛报表列表", priority=28)
 	public void testPkReportList( ) {
// 		String res = InteractionBusiness.PkReportList("");
// 		String resultCode = (String)JSONPath.read(res, "$.resultCode");
// 		Assert.assertEquals(resultCode, "200","28.PK赛报表列表：" + res);
 	}
 	
 	
 	@Test(description="29.PK赛报表参赛结果明细", priority=29)
 	public void testUserReportList( ) {
// 		String res = InteractionBusiness.UserReportList("","","");
// 		String resultCode = (String)JSONPath.read(res, "$.resultCode");
// 		Assert.assertEquals(resultCode, "200","29.PK赛报表参赛结果明细：" + res);
 	}
 	
 	
 	@Test(description = "30.删除pk赛",priority = 30)
 	public void testDeletePkGame() {
// 		String res = InteractionBusiness.deletePkGame(pk_id);
// 		Assert.assertTrue(res.contains("删除成功"),"删除pk赛，实际返回值：" + res);
 	}
 	
 	@Test(description = "31.删除pk赛-未设置积分",priority = 31)
 	public void testDeletePkGameNoPoints() {
// 		String res = InteractionBusiness.deletePkGame(third_pk_id);
// 		Assert.assertTrue(res.contains("删除成功"),"删除pk赛，实际返回值：" + res);
 	}
 	
// 	@DataProvider(name = "datalist")
//	public Object[][] queryCreationtype() {
//		Object[][] obj = new Object[][] {  { "查看全部列表", "", "success" },
//				{ "查看未参与列表", "participation", "success" } ,{ "查看已参与列表", "not_participating", "success" }};
//		return obj;
//	}
// 	
}
