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

public class ExaminationBusines {
	
	public static String exam_url= ExamDataUrl.getNewExamUrl();
	public static String token = TokenData.getMangerToken();

	public static String AddUrl = exam_url + "questionBank/createQuestionBank";

	public static String editUrl = exam_url + "questionBank/editQuestionBank";

	public static String deleteUrl = exam_url + "questionBank/deleteQuestionBank";

	public static String queryUrl = exam_url + "questionBank/queryQuestionBankList";

	public static String querysingleUrl =exam_url + "questionBank/queryQuesyionBankInfo";

	public static String selectUrl = exam_url +  "questionBank/selectQuestionBankList";
	
	public static String enterprise_id = EnterpriseData.getEnterpriseId();
	
	public static String setVisibilityRangeUrl = exam_url
			+ "v2/"+enterprise_id+"/question_banks/visual_ranges/update";

	public static String export_questionUrl (String id) {
		return exam_url + "v2/"+enterprise_id+"/questionBanks/"+id+"/export_question";
	}
	
	public static  String exportQuestion(String id) {
		return HttpRequest.get(export_questionUrl(id)).query("access_token",token).query("access_token",token).send().body();
	}
	
	// 新增题库
	public static String createQuestionBank(String name, String isAll, String departmentIds, String userIds) {
		return PostRequestTools.RequestFormDataByPost(token, "courseClassify",
				ClassificationBusines.getPrimaryId(), "name", name, "isAll", isAll, "departmentIds", departmentIds,
				"userIds", userIds, AddUrl);

	}
	
	// 新增题库
		public static String createQuestionBank(String name, String courseClassify,String isAll, String departmentIds, String userIds) {
			return PostRequestTools.RequestFormDataByPost(token, "courseClassify",
					courseClassify, "name", name, "isAll", isAll, "departmentIds", departmentIds,
					"userIds", userIds, AddUrl);

		}
	
	public static String queryListBank () {
		return GetRequestTools.RequestParamsByGet("access_token", token, selectUrl);
		
	}
	
	// 对题库输入项进行校验
		public static String createCheckQuestionBank(String classification,String name, String isAll, String departmentIds, String userIds) {
			return PostRequestTools.RequestFormDataByPost(token, "courseClassify",
					classification, "name", name, "isAll", isAll, "departmentIds", departmentIds,
					"userIds", userIds, AddUrl);

		}

	// 查询单个题库信息
	public static String queryQuesyionBankInfo(String id) {
		return GetRequestTools.RequestQueryParamsByGet("id", id, "access_token", token, querysingleUrl);

	}
	// 编辑题库信息

	public static String editQuestionBank(String name, String isAll, String departmentIds, String userIds, String id) {

		return PostRequestTools.RequestFormDataByPost(token, "courseClassify",
				ClassificationBusines.getPrimaryId(), "name", name, "isAll", isAll, "userIds", userIds, "id", id,
				editUrl);
	}

	// 删除题库
	public static String deleteQuestionBank(String id) {

		return PostRequestTools.RequestFormDataByPost(token, "id", id, deleteUrl);

	}

	public static String getFirstId() {
		String res = queryQuestionBankList("", "", "");
		String id = (String) JSONPath.read(res, "$.data.list[0].id");
		return id;
	}
	
	public static String getIdByKeyword(String name) {
		String res = queryQuestionBankList(name, "", "");
		String id = (String) JSONPath.read(res, "$.data.list[0].id");
		return id;
	}
	
	public static String getIdByKeyword() {
		String res = queryQuestionBankList(BaseBusiness.examName, "", "");
		String id = (String) JSONPath.read(res, "$.data.list[0].id");
		return id;
	}
	public static void main(String[] args) {
		System.out.println(getIdByKeyword());
	}
	public static String getFirstName() {
		String res = queryQuestionBankList("", "false", "");
		String name = (String) JSONPath.read(res, "$.data.list[0].name");
		return name;
	}

	//获取试题总个数
	public static int getFirstTotalCount() {
		String res = queryQuestionBankList("", "false", "");
		int id = (int) JSONPath.read(res, ".data.list[0].totalCount");
		return id;
	}
	
	public static String getSecondId() {
		String res = queryQuestionBankList("", "false", "");
		String id = (String) JSONPath.read(res, "$.data.list[1].id");
		return id;
	}

	// 查询题库列表
	public static String queryQuestionBankList(String keyword, String onlySeeMe, String courseClassify) {

		return GetRequestTools.RequestQueryParamsByGet("keyword", keyword, "onlySeeMe", onlySeeMe, "pageSize", "20",
				"pageNumber", "1", "access_token", token, queryUrl);

	}

	public static String setVisibilityRange(String id, String isAll) {
		return PostRequestTools.RequestBodyByPost(
				"{\"question_bank_ids\":\"" + id + "\",\"department_ids\":\"\",\"user_ids\":\"\",\"is_all\":" + isAll
						+ ",\"access_token\":\"14825f466b4fb443cad6cd8870ffb604\"}",
				token, setVisibilityRangeUrl);

	}

}