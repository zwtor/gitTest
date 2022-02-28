package cn.kxy.examination.business;

import cn.kxy.base.business.BaseBusiness;
import cn.kxy.base.business.EnterpriseData;
import cn.kxy.base.business.ExamDataUrl;
import cn.kxy.base.business.TokenData;
import cn.kxy.setting.bussiness.ClassificationBusines;
import com.alibaba.fastjson.JSONPath;
import com.lazy.assured.utils.GetRequestTools;
import com.lazy.assured.utils.PostRequestTools;
import com.lazy.httpclient.utils.HttpRequest;

public class PaperBusiness {
	
	public static String exam_url= ExamDataUrl.getNewExamUrl();
	public static String token = TokenData.getMangerToken();
	public static String referenceWithPlanUrl = exam_url + "paper/referenceWithPlan";
	public static String queryListUrl = exam_url + "paper/getList";

	public static String enterprise_id = EnterpriseData.getEnterpriseId();
	public static String addUrl = exam_url + "paper/add";

	public static String editUrl = exam_url + "paper/edit";

	public static String deleteUrl = exam_url + "paper/delete";

	public static String queryInfo = exam_url + "paper/getOne";

	public static String getListUrl = exam_url + "course/question/getList";

	public static String batch_update_visible_url = exam_url + "v2/"+enterprise_id+"/papers/batch_update_visible";
	
	public static String copy_url(String id) {
		return exam_url + "v2/"+enterprise_id+"/papers/"+id+"/copy";
	}
	
	/**   
	 * @Title: copyPaper   
	 * @Description: TODO   复制试卷
	 * @param: @param id
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */  
	public static String copyPaper(String id) {
		return HttpRequest.post(copy_url(id)).query("access_token", token).data("{\"access_token\":\""+token+"\"}").send().body();
	}
	
	/**   
	 * @Title: updatePaperVisible   
	 * @Description: TODO  设置试卷的可见范围
	 * @param: @param paper_id
	 * @param: @param visible_range
	 * @param: @param user_id
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */  
	public static String updatePaperVisible(String paper_id,String visible_range,String user_id) {
		return PostRequestTools.RequestBodyByPost("{\r\n" + 
				"  \"paper_ids\": \""+paper_id+"\", \r\n" + 
				"  \"visible_range\": \""+visible_range+"\", \r\n" + 
				"  \"department_ids\": \"\", \r\n" + 
				"  \"group_ids\": \"\", \r\n" + 
				"  \"post_ids\": \"\", \r\n" + 
				"  \"user_ids\": \""+user_id+"\", \r\n" + 
				"  \"access_token\": \""+token+"\"\r\n" + 
				"}", token, batch_update_visible_url);
	}
	
	// 新增试卷
	public static String creatPaper(String title, String summary, String totalScore, String passingScore,
			String passLine, String questionid01, String firstScore, String questionid02, String secondScore) {
		return PostRequestTools.RequestFormDataByPost(token, "title", title, "summary", summary,
				"totalScore", totalScore, "passingScore", passingScore, "passLine", passLine, "courseClassify",
				ClassificationBusines.getPrimaryId(), "questionVoInfo",
				"[{\"questionId\":\"" + questionid01 + "\",\"score\":" + firstScore + "},{\"questionId\":\""
						+ questionid02 + "\",\"score\":" + secondScore + "}]",
				addUrl);
	}
	
	public static String creatPaper(String title, String summary,String courseClassify, String totalScore, String passingScore,
			String passLine, String questionid01, String firstScore, String questionid02, String secondScore) {
		return PostRequestTools.RequestFormDataByPost(token, "title", title, "summary", summary,
				"totalScore", totalScore, "passingScore", passingScore, "passLine", passLine, "courseClassify",
				courseClassify, "questionVoInfo",
				"[{\"questionId\":\"" + questionid01 + "\",\"score\":" + firstScore + "},{\"questionId\":\""
						+ questionid02 + "\",\"score\":" + secondScore + "}]",
				addUrl);
	}

	public static String getFirstquestionid() {
		String res = GetRequestTools.RequestQueryParamsByGet("keyword","sinagleChoice",
				"access_token", token, getListUrl);
		String id = (String) JSONPath.read(res, "$.list[0].id");
		return id;

	}

	public static String getSecondquestionid() {

		String res = GetRequestTools.RequestQueryParamsByGet("keyword", "Multiplechoice",
				"access_token", token, getListUrl);
		String id = (String) JSONPath.read(res, "$.list[0].id");
		;

		return id;
	}

	// 编辑试卷
	public static String editPaper(String title, String summary, String totalScore, String passingScore,
			String passLine, String questionid01, String firstScore, String questionid02, String secondScore,
			String id) {
		return PostRequestTools.RequestFormDataByPost(
				token, "title", title, "summary", summary, "totalScore", totalScore, "passingScore",
				passingScore, "passLine", passLine, "courseClassify", ClassificationBusines.getPrimaryId(),
				"questionVoInfo", "[{\"questionId\":\"" + questionid01 + "\",\"score\":" + firstScore
						+ "},{\"questionId\":\"" + questionid02 + "\",\"score\":" + secondScore + "}]",
				"id", id, editUrl);
	}

	// 试卷是否被引用
	public static String isReferenceWithPlan(String id) {
		return GetRequestTools.RequestQueryParamsByGet("id", id, "access_token", token,
				referenceWithPlanUrl);

	}

	// 查询列表
	public static String queryList(String keyword, String onlySeeMe) {
		return GetRequestTools.RequestQueryParamsByGet("keyword", keyword,"sortOrder","desc","sortName","createTime", "onlySeeMe", onlySeeMe, "access_token",
				token, queryListUrl);

	}

	// 查询列表
	public static String queryList(String keyword, String courseClassify, String onlySeeMe) {
		return GetRequestTools.RequestQueryParamsByGet("keyword", keyword, "courseClassify", courseClassify,
				"onlySeeMe", onlySeeMe, "access_token", token, queryListUrl);

	}

	public static int getPaperTotal() {
		String res = queryList("", "false");
		int total = (int) JSONPath.read(res, "$.total");
		return total;
	}

	public static String queryInfo(String id) {
		return GetRequestTools.RequestQueryParamsByGet("id", id, "access_token", token, queryInfo);
	}

	// 获取第一个试卷的id
	public static String getFirstId() {
		String res = queryList("", "false");
		String id = (String) JSONPath.read(res, "$.list[0].id");
		return id;
	}

	public static String getIdByKeyword(String name) {
		String res = queryList(name, "false");
		String id = (String) JSONPath.read(res, "$.list[0].id");
		return id;

	}
	public static void main(String[] args) {
		System.out.println(getIdByKeyword(BaseBusiness.paper_name));
	}

	// 获取第一个试卷的总分
	public static String getFirstTotalScore() {
		String res = queryList("", "false");
		String totalScore = (String) JSONPath.read(res, "$.list[0].totalScore");
		return totalScore;

	}

	// 获取第一个试卷的及格分
	public static String getFirstPassingScore() {
		String res = queryList("", "false");
		String passingScore = (String) JSONPath.read(res, "$.list[0].passingScore");
		return passingScore;
	}

	// 删除试卷
	public static String deletePaper(String id) {
		return PostRequestTools.RequestFormDataByPost(token, "ids", id, deleteUrl);

	}

}
