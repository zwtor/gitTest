package cn.kxy.group.a.business;


import cn.kxy.base.business.*;
import cn.kxy.examination.business.ExaminationTaskBusiness;
import cn.kxy.examination.business.PaperBusiness;
import cn.kxy.setting.bussiness.UserBusiness;
import cn.kxy.study.business.StudyTaskBusiness;
import com.alibaba.fastjson.JSONPath;
import com.lazy.assured.utils.GetRequestTools;
import com.lazy.common.utils.CommonData;
import com.lazy.common.utils.DateUtil;
import com.lazy.httpclient.utils.HttpRequest;

public class StationUserinfoBusiness {

	public static String enterprise_url = EnterpriseDataUrl.getEnterpriseUrl();
	public static String enterpriseId = EnterpriseData.getEnterpriseId();
	public static String exam_url = ExamDataUrl.getNewExamUrl();
	public static String enterprise_Id = EnterpriseData.getEnterpriseId();
	public static String access_token = TokenData.getMangerToken();
	public static String userId = UserBusiness.getUserId();
	public static String QueryUrl = enterprise_url + "admin/classify/getTree";
	public static String addStudytaskURL = enterprise_url + "plan/study/add";

	public static String SignLlistURL = enterprise_url + "/v2/enterprises/" + enterprise_Id + "/sign/list";
	public static String examResultURL = exam_url + "plan/exam/result";
	public static String querypaperURL = exam_url + "paper/getList";
	public static String UserRoleURL = enterprise_url + "system/role/getUsersByRoleId";


	public static String StudyTaskURL(String StudyTaskId) {
		return enterprise_url + "v2/" + enterpriseId + "/plans/" + StudyTaskId + "/monitors";
	}

	/**
	 * 签到记录列表
	 * 
	 * @param keyword
	 * @param userid
	 * @param start_time
	 * @param end_time
	 * @param remark
	 * @return
	 */
	public static String querySignlistUserinfo(String keyword, String userid, String start_time, String end_time,
			String remark) {
		return HttpRequest.get(SignLlistURL).query("keyword", keyword).query("user_id", userid)
				.query("start_time", start_time).query("end_time", end_time).query("remark", remark)
				.query("access_token", access_token).send().body();

	}

	// 查询分类
	public static String queryClassification() {
		return GetRequestTools.RequestQueryParamsByGet("parentId", 0, "access_token", access_token, QueryUrl);
	}

	// 获取一级分类的id
	public static String getPrimaryId() {
		String body = queryClassification();
		String id = (String) JSONPath.read(body, "$[0].id");
		return id;
	}

	// 新建考试任务
	public static String getexamId() {
		String exam_task_name = "exam" + CommonData.getStringRandom(6);
//		String id = ExaminationTaskBusiness.getIdByName(exam_task_name);
		return ExaminationTaskBusiness.creatExamTask("1", "show", "60", "100", "2", exam_task_name,
				DateUtil.getRegularDate(0), DateUtil.getRegularDate(2), "false", UserBusiness.getUserId(),
				PaperBusiness.getIdByKeyword(BaseBusiness.paper_name), "60", "", "false", "1", "0", "12", "0", "0", "0",
				"0", "false", "false", UserBusiness.getUserId());
	}

	//
	public static String getpaperId(String keyword) {

		return HttpRequest.get(querypaperURL).query("keyword", keyword).query("onlySeeMe", "false")
				.query("sortName", "createTime").query("sortOrder", "desc").query("pageSize", "20")
				.query("pageNumber", "1").query("access_token", access_token).send().body();

	}

	/**
	 * 考试任务人员监控列表
	 * 
	 * @param status
	 * @param departmentId
	 * @param keyword
	 * @return
	 */
	public static String getexamUserinfo(String status, String departmentId, String keyword) {
		String id = (String) JSONPath.read(getexamId(), "$.data");
		;
		String res = HttpRequest.get(examResultURL).query("planId", id).query("tabId", "0").query("status", status)
				.query("departmentId", departmentId).query("keyword", keyword).query("pageNumber", "1")
				.query("pageSize", "20").query("access_token", access_token).send().body();
		ExaminationTaskBusiness.deleteExamTask(id);
		return res;
	}

	// 创建带考试的学习任务
	public static String getStudyTaskId() {

		String study_name = "MyStudyTask" + CommonData.getStringRandom(5);
		String id = (String) JSONPath.read(getpaperId(BaseBusiness.paper_name), "$.list[0].id");
		String res = HttpRequest.post(addStudytaskURL).query("access_token", access_token).data("{\r\n"
				+ "    \"title\":\"" + study_name + "\",\r\n" + "    \"beginTime\": " + DateUtil.getTimeStamp(-7, "")
				+ ",\r\n" + "    \"endTime\": " + DateUtil.getTimeStamp(9, "") + ",\r\n" + "    \"projectId\":\"\",\r\n"
				+ "    \"summary\":\"\",\r\n" + "    \"classify_id\":\"\",\r\n" + "    \"dingImgUrl\":\"\",\r\n"
				+ "    \"departmentIds\":\"\",\r\n" + "    \"supervisor_department_ids\":[\r\n" + "\r\n" + "    ],\r\n"
				+ "    \"groupIds\":\"\",\r\n" + "    \"isGetScore\":true,\r\n" + "    \"isNoticeAll\":0,\r\n"
				+ "    \"planCertificateId\":\"\",\r\n" + "    \"postIds\":\"\",\r\n" + "    \"progress\":0,\r\n"
				+ "    \"studyScore\":{\r\n" + "        \"finishScore\":0,\r\n" + "        \"unFinishScore\":0\r\n"
				+ "    },\r\n" + "    \"scheduleSetting\":\"free_mode\",\r\n" + "    \"studyTimeLimit\":\"30\",\r\n"
				+ "    \"faceRecognition\":\"off\",\r\n" + "    \"supervisorId\":\"" + userId + "\",\r\n"
				+ "    \"supervisorPaper\":true,\r\n" + "    \"authorityRange\":false,\r\n" + "    \"times\":1,\r\n"
				+ "    \"operationTimes\":\"\",\r\n" + "    \"userIds\":\"" + userId + "\",\r\n"
				+ "    \"dingGroupIds\":\"\",\r\n" + "    \"stageJson\":[\r\n" + "        {\r\n"
				+ "            \"content\":\"\",\r\n" + "            \"stageName\":\"阶段1\",\r\n"
				+ "            \"isOrder\":false,\r\n" + "            \"stageSort\":1,\r\n"
				+ "            \"stageId\":\"\",\r\n" + "            \"startTime\":null,\r\n"
				+ "            \"endTime\":null,\r\n" + "            \"course\":[\r\n" + "                {\r\n"
				+ "                    \"courseSort\":0,\r\n" + "                    \"title\":\"exam\",\r\n"
				+ "                    \"type\":\"ex\",\r\n" + "                    \"cheatFlag\":0,\r\n"
				+ "                    \"faceRecognition\":\"off\",\r\n"
				+ "                    \"examDuration\":60,\r\n" + "                    \"summary\":\"\",\r\n"
				+ "                    \"flag\":2,\r\n" + "                    \"mappingId\":\"\",\r\n"
				+ "                    \"courseId\":\"\",\r\n" + "                    \"markType\":1,\r\n"
				+ "                    \"paperId\":\"" + id + "\",\r\n"
				+ "                    \"paperTitle\":\"exam\",\r\n" + "                    \"passLine\":60,\r\n"
				+ "                    \"questionBankList\":\"\",\r\n" + "                    \"questionMode\":1,\r\n"
				+ "                    \"showKnowledge\":\"show\",\r\n" + "                    \"repeatExam\":true,\r\n"
				+ "                    \"multipleCount\":\"\",\r\n"
				+ "                    \"multipleUnitScore\":\"\",\r\n"
				+ "                    \"fillBlankCount\":\"\",\r\n"
				+ "                    \"fillBlankUnitScore\":\"\",\r\n"
				+ "                    \"fillBlankType\":1,\r\n" + "                    \"shortAnswerCount\":\"\",\r\n"
				+ "                    \"shortAnswerUnitScore\":\"\",\r\n"
				+ "                    \"shortAnswerType\":1,\r\n" + "                    \"singleCount\":\"\",\r\n"
				+ "                    \"singleUnitScore\":\"\",\r\n"
				+ "                    \"trueOrFalseCount\":\"\",\r\n"
				+ "                    \"trueOrFalseUnitScore\":\"\",\r\n" + "                    \"totalScore\":5,\r\n"
				+ "                    \"answerParsing\":5,\r\n" + "                    \"passingScore\":\"3.0\",\r\n"
				+ "                    \"cutScreenCount\":-1,\r\n" + "                    \"reExamRule\":0,\r\n"
				+ "                    \"reExamNumber\":0,\r\n" + "                    \"scoreRule\":0,\r\n"
				+ "                    \"viewRule\":1,\r\n" + "                    \"proportional\":[\r\n" + "\r\n"
				+ "                    ],\r\n" + "                    \"resource_lock\":false\r\n"
				+ "                }\r\n" + "            ]\r\n" + "        }\r\n" + "    ],\r\n"
				+ "    \"stagePass\":false,\r\n" + "    \"finishEvaluation\":false,\r\n"
				+ "    \"openEvaluationResult\":false,\r\n" + "    \"action\":\"published\",\r\n"
				+ "    \"free\":true,\r\n" + "    \"official_price_str\":\"\",\r\n" + "    \"access_token\":\""
				+ access_token + "\"\r\n" + "}").send().body();

		return res;
	}

	/**
	 * 学习任务数据监控列表
	 * 
	 * @param keyword
	 * @param taskStatus
	 * @param departmentId
	 * @return
	 */
	public static String getStudyTaskUserinfo(String keyword, String taskStatus, String departmentId) {
		String data = (String) JSONPath.read(getStudyTaskId(), "$.data");
		String res = HttpRequest.get(StudyTaskURL(data)).query("queryTaskInfo", "true").query("qualifiedStatus", "")
				.query("keyword", "").query("taskStatus", "").query("departmentId", "").query("postId", "")
				.query("planId", data).query("pageNumber", "1").query("pageSize", "20").query("groupId", "")
				.query("access_token", access_token).send().body();
		StudyTaskBusiness.deleteStudyTask(data);
		return res;
	}

	/**
	 * 角色管理页面
	 * @param keyword
	 * @return
	 */
	public static String getUsersByRoleUserinfo(String keyword) {

		return HttpRequest.get(UserRoleURL).query("roleId", "20000000").query("pageNumber", "1").query("pageSize", "20")
				.query("keyword", keyword).query("type", "user_name").query("access_token", access_token).send().body();
	}

}
