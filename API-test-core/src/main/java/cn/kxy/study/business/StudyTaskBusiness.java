package cn.kxy.study.business;

import cn.kxy.base.business.BaseBusiness;
import cn.kxy.base.business.EnterpriseData;
import cn.kxy.base.business.EnterpriseDataUrl;
import cn.kxy.base.business.TokenData;
import cn.kxy.setting.bussiness.UserBusiness;
import com.lazy.assured.utils.GetRequestTools;
import com.lazy.assured.utils.PostRequestTools;
import com.lazy.common.utils.DateUtil;
import com.lazy.httpclient.utils.HttpRequest;
import com.lazy.httpclient.utils.HttpResponse;
import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class StudyTaskBusiness {
	public static String token = TokenData.getMangerToken();
	public static String enterpriseId = EnterpriseData.getEnterpriseId();
	public static String enterpriseUrl = EnterpriseDataUrl.getEnterpriseUrl();
	public static String userId = UserBusiness.getUserId();
	public static String add_study_task_url =enterpriseUrl + "plan/study/add";

	public static String sendUrgeStudyMessageUrl =enterpriseUrl + "plan/study/sendUrgeStudyMessage";
	
	public static String planStudyDelayUrl = enterpriseUrl + "plan/study/planStudyDelay";
	
	public static String changeStudyUsersUrl = enterpriseUrl + "plan/study/changeStudyUsers";
	
	public static String getListUrl = enterpriseUrl + "plan/study/getList";
	
	public static String monitorsUrl(String id) {
		return enterpriseUrl + "v2/"+enterpriseId+"/plans/"+id+"/monitors";
	}
	
	public static String study_record_url (String id) {
		return enterpriseUrl + "v2/"+enterpriseId+"/plans/"+id+"/study_record";
	}
	public static String queryLatojaMonitorUrl =enterpriseUrl + "plan/employeeTrain/queryLatojaMonitor";
	
	public static String queryLatojaMonitorDetailListUrl = enterpriseUrl +"plan/employeeTrain/queryLatojaMonitorDetailList";
	
	public static String investigatesUrl(String id) {
		return enterpriseUrl + "v2/"+enterpriseId+"/studies/"+id+"/investigates";
	}
	
	public static String  queryExamsAnalysisUrl(String id) {
		return enterpriseUrl + "v2/"+enterpriseId+"/studies/"+id+"/exams";
	}
	
	public static String delete_url = enterpriseUrl +"plan/study/delete";
	
	public static String queryInfoUrl = enterpriseUrl +"plan/employeeTrain/getEmployeeTrainTask";
	
	public static String queryTrainMonitorUrl = enterpriseUrl + "plan/employeeTrain/queryTrainMonitor";
	
	public static String edit_url = enterpriseUrl + "plan/study/edit";
	
	public static String investigatesResultUrl (String id,String investigates) {
		return enterpriseUrl +"v2/"+enterpriseId+"/studies/"+id+"/investigates/"+investigates+"/result";
	}
	
	public static String  investigatesInfoUrl (String id,String investigates) {
		return enterpriseUrl +"v2/"+enterpriseId+"/studies/"+id+"/investigates/"+investigates+"/users";
	}
	
	public static String investigatesStatisticsInfoUrl(String investigates) {
		return enterpriseUrl + "v2/"+enterpriseId+"/investigates/"+investigates+"/statistics";
	}
	
	public static String monitors_export_url (String id) {
		return enterpriseUrl +"v2/"+enterpriseId+"/plans/"+id+"/monitors_export";
	}
	
	public static String getIntervalStatisticalUrl =enterpriseUrl + "plan/study/getIntervalStatistical";
	
	public static String studyExamMarkUrl (String id) {
		return enterpriseUrl + "v2/"+enterpriseId+"/studies/"+id+"/exam_markings";
	}
	
	public static String plan_study_export_url =enterpriseUrl + "v2/"+enterpriseId+"/plan_study_export";
	
	public static String exportPlanStudy () {
		return HttpRequest.get(plan_study_export_url).query("access_token",token).query("only_see_me","false").query("query_interval_type","0")
				.query("begin_time","").query("end_time","").send().body();
	}
	
	public static String group_liist_url = enterpriseUrl + "v2/"+enterpriseId+"/group/list";
	
	public static String cancel_publish_plan_url =enterpriseUrl +  "v2/"+enterpriseId+"/employees/cancel_publish_plan";
	
	public static String cancelPublishPlan(String plan_id) {
		return HttpRequest.post(cancel_publish_plan_url).query("access_token", token).form("plan_id",plan_id).send().body();
	}
	
	public static String queryGroupList(String keyword) {
		return HttpRequest.get(group_liist_url).query("access_token", token).query("keyword", keyword).send().body();
	}
	
	/**   
	 * @Title: addAuthorityRangeStudy   
	 * @Description: TODO  添加开启管辖范围的学习任务   后续可拓展至倍智测评，人脸识别的用例
	 * @param: @param title
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */  
	public static String addAuthorityRangeStudy(String title ,String beginTime ,String endTime,String authorityRange ,String courseId) {
		return PostRequestTools.RequestBodyByPost("{\r\n" + 
				"  \"title\":\""+title+" \",\r\n" + 
				"  \"beginTime\":"+beginTime+",\r\n" + 
				"  \"endTime\":"+endTime+",\r\n" + 
				"  \"summary\":\"\",\r\n" + 
				"  \"dingImgUrl\":\"\",\r\n" + 
				"  \"departmentIds\":\"\",\r\n" + 
				"  \"supervisor_department_ids\":[\r\n" + 
				"  ],\r\n" + 
				"  \"groupIds\":\"\",\r\n" + 
				"  \"isGetScore\":true,\r\n" + 
				"  \"isNoticeAll\":0,\r\n" + 
				"  \"planCertificateId\":\"\",\r\n" + 
				"  \"postIds\":\"\",\r\n" + 
				"  \"progress\":100,\r\n" + 
				"  \"studyScore\":{\r\n" + 
				"    \"finishScore\":0,\r\n" + 
				"    \"unFinishScore\":0\r\n" + 
				"  },\r\n" + 
				"  \"studyTimeLimit\":\"30\",\r\n" + 
				"  \"faceRecognition\":\"off\",\r\n" + 
				"  \"supervisorId\":\""+userId+"\",\r\n" + 
				"  \"supervisorPaper\":true,\r\n" + 
				"  \"authorityRange\":"+authorityRange+",\r\n" + 
				"  \"times\":\"\",\r\n" + 
				"  \"operationTimes\":\"\",\r\n" + 
				"  \"userIds\":\""+userId+"\",\r\n" + 
				"  \"stageJson\":[\r\n" + 
				"    {\r\n" + 
				"      \"content\":\"\",\r\n" + 
				"      \"stageName\":\"阶段一\",\r\n" + 
				"      \"isOrder\":false,\r\n" + 
				"      \"stageSort\":1,\r\n" + 
				"      \"course\":[\r\n" + 
				"        {\r\n" + 
				"          \"courseId\":\""+courseId+"\",\r\n" + 
				"          \"courseSort\":0,\r\n" + 
				"          \"courseType\":1,\r\n" + 
				"          \"flag\":1\r\n" + 
				"        }]\r\n" + 
				"    }],\r\n" + 
				"  \"stagePass\":false,\r\n" + 
				"  \"finishEvaluation\":false,\r\n" + 
				"  \"openEvaluationResult\":false,\r\n" + 
				"  \"access_token\":\""+token+"\"\r\n" + 
				"}", token, add_study_task_url);
	}
	
	/**   
	 * @Title: queryStudyExamMark   
	 * @Description: TODO  查看学习任务的待阅卷列表
	 * @param: @param id
	 * @param: @param keyword
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */  
	public static String queryStudyExamMark(String id,String keyword) {
		return HttpRequest.get(studyExamMarkUrl(id)).query("page_number", "1").query("page_size", "10").query("keyword",keyword)
				.query("access_token", token).send().body();
	}
	
	public static String addStagePassStudy(String title ,String stagePass,String art_id) {
		return PostRequestTools.RequestBodyByPost("{\r\n" + 
				"	\"title\": \""+title+"\",\r\n" + 
				"	\"beginTime\": "+DateUtil.getTimeStamp(0, "")+",\r\n" + 
				"	\"endTime\": "+DateUtil.getTimeStamp(7, "")+",\r\n" + 
				"	\"dingImgUrl\": \"\",\r\n" + 
				"	\"departmentIds\": \"\",\r\n" + 
				"	\"groupIds\": \"\",\r\n" + 
				"	\"isGetScore\": true,\r\n" + 
				"	\"isNoticeAll\": 0,\r\n" + 
				"	\"planCertificateId\": \"\",\r\n" + 
				"	\"postIds\": \"\",\r\n" + 
				"	\"progress\": 100,\r\n" + 
				"	\"studyScore\": {\r\n" + 
				"		\"finishScore\": 0,\r\n" + 
				"		\"unFinishScore\": 0\r\n" + 
				"	},\r\n" + 
				"	\"studyTimeLimit\": 0,\r\n" + 
				"	\"supervisorId\": \""+userId+"\",\r\n" + 
				"	\"supervisorPaper\": true,\r\n" + 
				"	\"times\": \"\",\r\n" + 
				"	\"userIds\": \""+userId+"\",\r\n" + 
				"	\"stageJson\": [{\r\n" + 
				"		\"content\": \"\",\r\n" + 
				"		\"stageName\": \"stageone\",\r\n" + 
				"		\"isOrder\": false,\r\n" + 
				"		\"stageSort\": 1,\r\n" + 
				"		\"course\": [{\r\n" + 
				"			\"courseId\": \""+art_id+"\",\r\n" + 
				"			\"courseSort\": 0,\r\n" + 
				"			\"courseType\": 1,\r\n" + 
				"			\"flag\": 1\r\n" + 
				"		}]\r\n" + 
				"	}],\r\n" + 
				"	\"stagePass\": "+stagePass+",\r\n" + 
				"	\"access_token\": \""+token+"\"\r\n" + 
				"}", token, add_study_task_url);
	}
	
	/**   
	 * @Title: getIntervalStatistical   
	 * @Description: TODO(获取列表页任务的个数)   
	 * @param: @param onlySeeMe
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */  
	public static String getIntervalStatistical(String onlySeeMe) {
		return GetRequestTools.RequestQueryParamsByGet("access_token", token, "onlySeeMe", onlySeeMe, getIntervalStatisticalUrl);
	}
	
	public static String addStageTrick(String name,String course_id_01,String course_id_02,String course_id_03) {
		return PostRequestTools.RequestBodyByPost("{\r\n" + 
				"	\"title\": \""+name+"\",\r\n" + 
				"	\"beginTime\": "+DateUtil.getTimeStamp(0, "")+",\r\n" + 
				"	\"endTime\": "+DateUtil.getTimeStamp(5, "")+",\r\n" + 
				"	\"dingImgUrl\": \"\",\r\n" + 
				"	\"departmentIds\": \"\",\r\n" + 
				"	\"groupIds\": \"\",\r\n" + 
				"	\"isGetScore\": true,\r\n" + 
				"	\"isNoticeAll\": 0,\r\n" + 
				"	\"planCertificateId\": \"\",\r\n" + 
				"   \"scheduleSetting\":\"free_mode\","+
				"	\"postIds\": \"\",\r\n" + 
				"	\"progress\": 100,\r\n" + 
				"	\"studyScore\": {\r\n" + 
				"		\"finishScore\": 0,\r\n" + 
				"		\"unFinishScore\": 0\r\n" + 
				"	},\r\n" + 
				"	\"studyTimeLimit\": 0,\r\n" + 
				"	\"supervisorId\": \""+userId+"\",\r\n" + 
				"	\"supervisorPaper\": true,\r\n" + 
				"	\"times\": \"\",\r\n" + 
				"	\"userIds\": \""+userId+"\",\r\n" + 
				"	\"stageJson\": [{\r\n" + 
				"		\"content\": \"\",\r\n" + 
				"		\"stageName\": \"stage one\",\r\n" + 
				"		\"isOrder\": true,\r\n" + 
				"		\"stageSort\": 1,\r\n" + 
				"		\"course\": [{\r\n" + 
				"			\"courseId\": \""+course_id_01+"\",\r\n" + 
				"			\"courseSort\": 0,\r\n" + 
				"			\"courseType\": 1,\r\n" + 
				"			\"flag\": 1\r\n" + 
				"		}, {\r\n" + 
				"			\"courseId\": \""+course_id_02+"\",\r\n" + 
				"			\"courseSort\": 1,\r\n" + 
				"			\"courseType\": 1,\r\n" + 
				"			\"flag\": 1\r\n" + 
				"		}]\r\n" + 
				"	}, {\r\n" + 
				"		\"content\": \"\",\r\n" + 
				"		\"stageName\": \"stage two\",\r\n" + 
				"		\"isOrder\": true,\r\n" + 
				"		\"stageSort\": 2,\r\n" + 
				"		\"course\": [{\r\n" + 
				"			\"courseId\": \""+course_id_03+"\",\r\n" + 
				"			\"courseSort\": 0,\r\n" + 
				"			\"courseType\": 1,\r\n" + 
				"			\"flag\": 1\r\n" + 
				"		}]\r\n" + 
				"	}],\r\n" + 
				"	\"stagePass\": true,\r\n" + 
				"	\"access_token\": \""+token+"\"\r\n" + 
				"}", token, add_study_task_url);
	}
	
	/**   
	 * @Title: addRandomExam   
	 * @Description: TODO  添加随机考试的学习任务
	 * @param: @param title
	 * @param: @param question_bank_id
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */  
	public static String addRandomExam(String title,String question_bank_id) {
		return PostRequestTools.RequestBodyByPost("{\r\n" + 
				"  \"title\": \""+title+"\", \r\n" + 
				"  \"beginTime\": "+DateUtil.getTimeStamp(0, "")+", \r\n" + 
				"  \"endTime\": "+DateUtil.getTimeStamp(3, "")+", \r\n" + 
				"  \"dingImgUrl\": \"\", \r\n" + 
				"  \"departmentIds\": \"\", \r\n" + 
				"  \"groupIds\": \"\", \r\n" + 
				"  \"isGetScore\": true, \r\n" + 
				"  \"isNoticeAll\": 0, \r\n" + 
				"  \"planCertificateId\": \"\", \r\n" + 
				"  \"postIds\": \"\", \r\n" + 
				"  \"progress\": 0, \r\n" + 
				"  \"studyScore\": {\r\n" + 
				"    \"finishScore\": 0, \r\n" + 
				"    \"unFinishScore\": 0\r\n" + 
				"  }, \r\n" + 
				"  \"studyTimeLimit\": 0, \r\n" + 
				"  \"supervisorId\": \""+userId+"\", \r\n" + 
				"  \"supervisorPaper\": true, \r\n" + 
				"  \"times\": 1, \r\n" + 
				"  \"userIds\": \""+userId+"\", \r\n" + 
				"  \"stageJson\": [\r\n" + 
				"    {\r\n" + 
				"      \"content\": \"\", \r\n" + 
				"      \"stageName\": \"step one\", \r\n" + 
				"      \"isOrder\": false, \r\n" + 
				"      \"stageSort\": 1, \r\n" + 
				"      \"course\": [\r\n" + 
				"        {\r\n" + 
				"          \"courseSort\": 0, \r\n" + 
				"          \"title\": \"exam\", \r\n" + 
				"          \"type\": \"ex\", \r\n" + 
				"          \"cheatFlag\": 0, \r\n" + 
				"          \"examDuration\": 45, \r\n" + 
				"          \"summary\": \"\", \r\n" + 
				"          \"flag\": 2, \r\n" + 
				"          \"courseId\": \"\", \r\n" + 
				"          \"markType\": 1, \r\n" + 
				"          \"paperId\": \"\", \r\n" + 
				"          \"paperTitle\": \"\", \r\n" + 
				"          \"passLine\": 20, \r\n" + 
				"          \"questionBankList\": \""+question_bank_id+"\", \r\n" + 
				"          \"questionMode\": 2, \r\n" + 
				"          \"showKnowledge\": \"show\", \r\n" + 
				"          \"repeatExam\": false, \r\n" + 
				"          \"multipleCount\": 0, \r\n" + 
				"          \"multipleUnitScore\": 0, \r\n" + 
				"          \"fillBlankCount\": 0, \r\n" + 
				"          \"fillBlankUnitScore\": 0, \r\n" + 
				"          \"shortAnswerCount\": 0, \r\n" + 
				"          \"shortAnswerUnitScore\": 0, \r\n" + 
				"          \"singleCount\": 1, \r\n" + 
				"          \"singleUnitScore\": 20, \r\n" + 
				"          \"trueOrFalseCount\": 0, \r\n" + 
				"          \"trueOrFalseUnitScore\": 0, \r\n" + 
				"          \"totalScore\": \"\", \r\n" + 
				"          \"answerParsing\": 2, \r\n" + 
				"          \"passingScore\": 60, \r\n" + 
				"          \"cutScreenCount\": -1, \r\n" + 
				"          \"reExamRule\": 0, \r\n" + 
				"          \"reExamNumber\": 0, \r\n" + 
				"          \"scoreRule\": 0,\r\n" + 
				"          \"viewRule\": 1\r\n" +
				"        }\r\n" + 
				"      ]\r\n" + 
				"    }\r\n" + 
				"  ], \r\n" + 
				"  \"access_token\": \""+token+"\"\r\n" + 
				"}", token, add_study_task_url);
	}
	
	/**添加防切屏，试题顺序打乱的学习考试任务
	 * @param title
	 * @param beginTime
	 * @param endTime
	 * @param cheatFlag
	 * @param cutScreenCount
	 * @return
	 */
	public static String addCheatFlagCutScreenCountOfExam (String title,String beginTime,String endTime,String paperId,String cheatFlag,String cutScreenCount ) {
		return HttpResponse.postJson(add_study_task_url, "{\r\n" + 
				"  \"title\": \""+title+"\", \r\n" + 
				"  \"beginTime\": "+beginTime+", \r\n" + 
				"  \"endTime\": "+endTime+", \r\n" + 
				"  \"dingImgUrl\": \"\", \r\n" + 
				"  \"departmentIds\": \"\", \r\n" + 
				"  \"groupIds\": \"\", \r\n" + 
				"  \"isGetScore\": true, \r\n" + 
				"  \"isNoticeAll\": 0, \r\n" + 
				"  \"planCertificateId\": \"\", \r\n" + 
				"  \"postIds\": \"\", \r\n" + 
				"  \"progress\": 0, \r\n" + 
				"  \"studyScore\": {\r\n" + 
				"    \"finishScore\": 0, \r\n" + 
				"    \"unFinishScore\": 0\r\n" + 
				"  }, \r\n" + 
				"  \"studyTimeLimit\": 0, \r\n" + 
				"  \"supervisorId\": \""+userId+"\", \r\n" + 
				"  \"supervisorPaper\": true, \r\n" + 
				"  \"times\": 1, \r\n" + 
				"  \"userIds\": \""+userId+"\", \r\n" + 
				"  \"stageJson\": [\r\n" + 
				"    {\r\n" + 
				"      \"content\": \"\", \r\n" + 
				"      \"stageName\": \"step one\", \r\n" + 
				"      \"isOrder\": false, \r\n" + 
				"      \"stageSort\": 1, \r\n" + 
				"      \"course\": [\r\n" + 
				"        {\r\n" + 
				"          \"courseSort\": 0, \r\n" + 
				"          \"title\": \"exam\", \r\n" + 
				"          \"type\": \"ex\", \r\n" + 
				"          \"cheatFlag\": "+cheatFlag+", \r\n" + 
				"          \"examDuration\": 45, \r\n" + 
				"          \"summary\": \"\", \r\n" + 
				"          \"flag\": 2, \r\n" + 
				"          \"courseId\": \"\", \r\n" + 
				"          \"markType\": 1, \r\n" + 
				"          \"paperId\": \"\", \r\n" + 
				"          \"paperTitle\": \"\", \r\n" + 
				"          \"passLine\": 45, \r\n" + 
				"          \"questionBankList\": \""+paperId+"\", \r\n" + 
				"          \"questionMode\": 2, \r\n" + 
				"          \"showKnowledge\": \"show\", \r\n" + 
				"          \"repeatExam\": true, \r\n" + 
				"          \"multipleCount\": 0, \r\n" + 
				"          \"multipleUnitScore\": 0, \r\n" + 
				"          \"fillBlankCount\": 1, \r\n" + 
				"          \"fillBlankUnitScore\": 1, \r\n" + 
				"          \"shortAnswerCount\": 0, \r\n" + 
				"          \"shortAnswerUnitScore\": 0, \r\n" + 
				"          \"singleCount\": 1, \r\n" + 
				"          \"singleUnitScore\": 1, \r\n" + 
				"          \"trueOrFalseCount\": 0, \r\n" + 
				"          \"trueOrFalseUnitScore\": 0, \r\n" + 
				"          \"totalScore\": \"\", \r\n" + 
				"          \"answerParsing\": 5, \r\n" + 
				"          \"passingScore\": 60, \r\n" + 
				"          \"cutScreenCount\": -1, \r\n" + 
				"          \"reExamRule\": 0, \r\n" + 
				"          \"reExamNumber\": 0, \r\n" + 
				"          \"viewRule\": 1, \r\n" +
				"          \"scoreRule\": 0\r\n" + 
				"        }\r\n" + 
				"      ]\r\n" + 
				"    }\r\n" + 
				"  ], \r\n" + 
				"  \"access_token\": \""+token+"\"\r\n" + 
				"}", "access_token", token);
	}
	
	/**   
	 * @Title: getExportMonitorsCode   
	 * @Description: TODO(导出学习数据)   
	 * @param: @param id
	 * @param: @return      
	 * @return: int      
	 * @throws   
	 */ 
	public static int getExportMonitorsCode(String id,String taskStatus) {
		return HttpResponse.getstatusCode(monitors_export_url(id), token, "departmentId", "1","taskStatus",taskStatus);
	}
	
	/**   
	 * @Title: queryInvestigatesStatisticsInfo   
	 * @Description: TODO(查看调研统计结果)   
	 * @param: @param investigates
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String queryInvestigatesStatisticsInfo(String investigates) {
		return GetRequestTools.RequestQueryParamsByGet("access_token", token, investigatesStatisticsInfoUrl(investigates));
	}
	
	/**   
	 * @Title: queryInvestigatesResulByUserId   
	 * @Description: TODO(查看调研详情)   
	 * @param: @param id
	 * @param: @param investigates
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String queryInvestigatesResulByUserId (String id,String investigates) {
		return GetRequestTools.RequestQueryParamsByGet("access_token",token,"user_id",userId, investigatesResultUrl(id,investigates));
	}
	/**   
	 * @Title: queryInvestigatesResul   
	 * @Description: TODO(问卷调研明细)   
	 * @param: @param id
	 * @param: @param investigates
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String queryInvestigatesResult (String id,String investigates) {
		return GetRequestTools.RequestQueryParamsByGet("access_token",token, investigatesResultUrl(id,investigates));
	}
	
	/**   
	 * @Title: editStudyTask   
	 * @Description: TODO(编辑学习任务)   
	 * @param: @param title
	 * @param: @param id
	 * @param: @param beginTime
	 * @param: @param endTime
	 * @param: @param xx_id
	 * @param: @param beginTimeStr
	 * @param: @param endTimeStr
	 * @param: @param lecturerId
	 * @param: @param clockTime
	 * @param: @param la_id
	 * @param: @param latojaId
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String editStudyTask(String title,String id ,String beginTime,String endTime,String xx_id,String beginTimeStr,
			String endTimeStr,String lecturerId,String clockTime,String la_id,String latojaId) {
		return PostRequestTools.RequestBodyByPost("{\r\n" + 
				"  \"title\": \""+title+"\", \r\n" + 
				"  \"beginTime\": "+beginTime+", \r\n" + 
				"  \"endTime\": "+endTime+", \r\n" + 
				"  \"dingImgUrl\": \"\", \r\n" + 
				"  \"departmentIds\": \"\", \r\n" + 
				"  \"groupIds\": \"\", \r\n" + 
				"  \"isGetScore\": true, \r\n" + 
				"  \"isNoticeAll\": 0, \r\n" + 
				"  \"planCertificateId\": \"\", \r\n" + 
				"  \"postIds\": \"\", \r\n" + 
				"  \"progress\": 100, \r\n" + 
				"  \"studyScore\": {\r\n" + 
				"    \"finishScore\": 0, \r\n" + 
				"    \"unFinishScore\": 0\r\n" + 
				"  }, \r\n" + 
				"  \"studyTimeLimit\": 0, \r\n" + 
				"  \"supervisorId\": \""+userId+"\", \r\n" + 
				"  \"supervisorPaper\": true, \r\n" + 
				"  \"times\": \"\", \r\n" + 
				"  \"userIds\": \""+userId+"\", \r\n" + 
				"    \"stagePass\": false,\n" + 
				"  \"stageJson\": [\r\n" + 
				"    {\r\n" + 
				"      \"content\": \"\", \r\n" + 
				"      \"stageName\": \"step one\", \r\n" + 
				"      \"isOrder\": false, \r\n" + 
				"      \"stageSort\": 1, \r\n" + 
				"      \"course\": [\r\n" + 
				"        {\r\n" + 
				"          \"courseSort\": 0, \r\n" + 
				"          \"flag\": 3, \r\n" + 
				"          \"id\": \""+xx_id+"\", \r\n" + 
				"          \"type\": \"xx\", \r\n" + 
				"          \"address\": \"xi'an yanta qu\", \r\n" + 
				"          \"title\": \"offline traner\", \r\n" + 
				"          \"introduction\": \"<p>des</p>\", \r\n" + 
				"          \"beginTimeStr\": \""+beginTimeStr+"\", \r\n" + 
				"          \"endTimeStr\": \""+endTimeStr+"\", \r\n" + 
				"          \"lecturerId\": \""+lecturerId+"\", \r\n" + 
				"          \"bizLatojaClockTimeList\": [\r\n" + 
				"            {\r\n" + 
				"              \"clockTime\": "+clockTime+", \r\n" + 
				"              \"id\": \""+la_id+"\", \r\n" + 
				"              \"latojaId\": \""+latojaId+"\", \r\n" + 
				"              \"noSignCount\": 0, \r\n" + 
				"              \"signCount\": 0, \r\n" + 
				"              \"signCourse\": \"\", \r\n" + 
				"              \"sort\": 0\r\n" + 
				"            }\r\n" + 
				"          ], \r\n" + 
				"          \"directors\": \""+userId+"\", \r\n" + 
				"          \"qrcodeType\": \"statics\"\r\n" + 
				"        }\r\n" + 
				"      ]\r\n" + 
				"    }\r\n" + 
				"  ], \r\n" + 
				"  \"id\": \""+id+"\", \r\n" + 
				"  \"planStatus\": \"unfinished\", \r\n" + 
				"  \"access_token\": \"fff95e7afbdf286498036ffed36181d9\"\r\n" + 
				"}", token, edit_url);
	}
	/**   
	 * @Title: queryTrainMonitor   
	 * @Description: TODO(查看学习任务进行中的人员个数)   
	 * @param: @param keyword
	 * @param: @param id
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String queryTrainMonitor(String keyword,String id) {
		HttpResponse res=HttpRequest.get(queryTrainMonitorUrl).query("planId", id).query("access_token", token).
				query("keyword", keyword).query("taskStatus","1").query("pageNumber", "1").query("pageSize", "5").send();
		HttpEntity resEntity = res.response().getEntity();  
		String msg = "" ;
        try {
			 msg = EntityUtils.toString(resEntity, "utf-8");
		} catch (ParseException | IOException e) {
			e.printStackTrace();
		}
		return msg;
	}
	
	/**   
	 * @Title: queryInfo   
	 * @Description: TODO(查看学习任务详情)   
	 * @param: @param id
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String queryInfo(String id) {
		return GetRequestTools.RequestQueryParamsByGet("planId", id,"access_token",token, queryInfoUrl);
	}
	
	/**   
	 * @Title: deleteStudyTask   
	 * @Description: TODO(删除学习任务)   
	 * @param: @param id
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String deleteStudyTask(String id) {
		return PostRequestTools.RequestFormDataByPost(token, "id", id, delete_url);
	}
	
	/**   
	 * @Title: queryExamsAnalysis   
	 * @Description: TODO(查看考试试题分析)   
	 * @param: @param id
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String queryExamsAnalysis(String id) {
		return GetRequestTools.RequestQueryParamsByGet("pageNumber","1","pageSize","20","access_token",token,queryExamsAnalysisUrl(id));
	}
	
	/**   
	 * @Title: queryInvestigates   
	 * @Description: TODO(查看问卷调查)   
	 * @param: @param id
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String queryInvestigates (String id) {
		return GetRequestTools.RequestQueryParamsByGet("pageNumber","1","pageSize","20","access_token",token,investigatesUrl(id));
	}
	
	/**   
	 * @Title: queryLatojaMonitorDetailList   
	 * @Description: TODO(查看线下签到详情)   
	 * @param: @param id
	 * @param: @param latojaId
	 * @param: @param keyword
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String queryLatojaMonitorDetailList(String id,String latojaId,String keyword) {
		return GetRequestTools.RequestQueryParamsByGet("planId", id,"latojaId",latojaId, "keyword",keyword,"departmentId","1",
				"pageNumber","1","pageSize","20","access_token",token,queryLatojaMonitorDetailListUrl);
	}
	
	/**   
	 * @Title: queryLatojaMonitor   
	 * @Description: TODO(查看线下培训)   
	 * @param: @param id
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String queryLatojaMonitor(String id) {
		return GetRequestTools.RequestQueryParamsByGet("pageNumber", "1", "pageSize", "20","planId",id,"access_token",token, queryLatojaMonitorUrl);
	}
	/**   
	 * @Title: queryStudyRecord   
	 * @Description: TODO(查看学习记录)   
	 * @param: @param id
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String  queryStudyRecord(String id) {
		return GetRequestTools.RequestQueryParamsByGet("user_id", userId,"access_token",token, study_record_url(id));
	}
	
	/**   
	 * @Title: quertUserMonitors   
	 * @Description: TODO(查看人员监控)   
	 * @param: @param id
	 * @param: @param queryTaskInfo
	 * @param: @param qualifiedStatus
	 * @param: @param keyword
	 * @param: @param taskStatus
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String queryUserMonitors(String id,String queryTaskInfo,String qualifiedStatus,String keyword,String taskStatus) {
		return GetRequestTools.RequestQueryParamsByGet("queryTaskInfo", queryTaskInfo, "qualifiedStatus", qualifiedStatus,"keyword",keyword,
				"taskStatus",taskStatus,"departmentId","1","planId",id,"pageNumber","1","pageSize","20","access_token",token,monitorsUrl(id));
	}

	/**
	 * @Title: getStudyTaskList
	 * @Description: TODO(查看学习任务列表)
	 * @param: @param keyword
	 * @param: @param onlySeeMe
	 * @param: @param queryIntervalType
	 * @param: @param projectId
	 * @param: @return
	 * @return: String
	 * @throws
	 */
	public static String getStudyTaskList(String keyword,String onlySeeMe,String queryIntervalType,String projectId,String planStatus) {
		return GetRequestTools.RequestQueryParamsByGet("keyword", keyword, "onlySeeMe", onlySeeMe,"queryIntervalType",
				queryIntervalType, "pageSize","20","pageNumber","1","projectId",projectId,"planStatus",planStatus,"access_token",token,getListUrl);
	}
	
	/**   
	 * @Title: changeStudyUsers   
	 * @Description: TODO(变更学习任务)   
	 * @param: @param id
	 * @param: @param isAllIn
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String changeStudyUsers(String id,String isAllIn) {
		return PostRequestTools.RequestFormDataByPost(token, "id", id, "isAllIn",isAllIn ,"isNoticeAll","0","userIds",userId, changeStudyUsersUrl);
	}
	
	/**   
	 * @Title: planStudyDelay   
	 * @Description: TODO(延期学习任务)   
	 * @param: @param id
	 * @param: @param endTime
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String planStudyDelay(String id,String endTime) {
		return PostRequestTools.RequestFormDataByPost(token, "endTime", endTime, "id", id, planStudyDelayUrl);
	}
	
	/**   
	 * @Title: sendUrgeStudyMessage   
	 * @Description: TODO(催促学习)   
	 * @param: @param planId
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String sendUrgeStudyMessage(String planId) {
		return GetRequestTools.RequestQueryParamsByGet("planId", planId,"access_token",token, sendUrgeStudyMessageUrl);
	}
	
	public static String addOfflineTypeStudyTask  (String title,String beginTime,String endTime ,
			String beginTimeStr,String endTimeStr,String lecturerId,String s_time,String e_time) {
		
	return	HttpRequest.post(add_study_task_url).query("access_token",token).data("{\n" + 
			"    \"title\": \""+title+"\",\n" + 
			"    \"beginTime\": "+beginTime+",\n" + 
			"    \"endTime\": "+endTime+",\n" + 
			"    \"projectId\": \"\",\n" + 
			"    \"summary\": \"\",\n" + 
			"    \"classify_id\": \"\",\n" + 
			"    \"dingImgUrl\": \"\",\n" + 
			"    \"departmentIds\": \"\",\n" + 
			"    \"supervisor_department_ids\": [],\n" + 
			"    \"groupIds\": \"\",\n" + 
			"    \"isGetScore\": true,\n" + 
			"    \"isNoticeAll\": 0,\n" + 
			"    \"planCertificateId\": \"\",\n" + 
			"    \"postIds\": \"\",\n" + 
			"    \"progress\": 100,\n" + 
			"    \"studyScore\": {\n" + 
			"        \"finishScore\": 0,\n" + 
			"        \"unFinishScore\": 0\n" + 
			"    },\n" + 
			"    \"scheduleSetting\": \"free_mode\",\n" + 
			"    \"studyTimeLimit\": \"30\",\n" + 
			"    \"faceRecognition\": \"off\",\n" + 
			"    \"supervisorId\": \""+userId+"\",\n" + 
			"    \"supervisorPaper\": true,\n" + 
			"    \"authorityRange\": false,\n" + 
			"    \"times\": \"\",\n" + 
			"    \"operationTimes\": \"\",\n" + 
			"    \"userIds\": \""+userId+"\",\n" + 
			"    \"dingGroupIds\": \"\",\n" + 
			"    \"stageJson\": [\n" + 
			"        {\n" + 
			"            \"content\": \"\",\n" + 
			"            \"stageName\": \"阶段1\",\n" + 
			"            \"isOrder\": false,\n" + 
			"            \"stageSort\": 1,\n" + 
			"            \"stageId\": \"\",\n" + 
			"            \"startTime\": null,\n" + 
			"            \"endTime\": null,\n" + 
			"            \"course\": [\n" + 
			"                {\n" + 
			"                    \"courseSort\": 0,\n" + 
			"                    \"flag\": 3,\n" + 
			"                    \"id\": \"\",\n" + 
			"                    \"type\": \"xx\",\n" + 
			"                    \"address\": \"大山发顺丰\",\n" + 
			"                    \"title\": \"线下签到的名称\",\n" + 
			"                    \"introduction\": \"<p>这是一个描述</p>\",\n" + 
			"                    \"beginTimeStr\": \""+beginTimeStr+"\",\n" + 
			"                    \"endTimeStr\": \""+endTimeStr+"\",\n" + 
			"                    \"bizLatojaClockTimeList\": [\n" + 
			"                        {\n" + 
			"                            \"latojaId\": \"\",\n" + 
			"                            \"clockTime\": "+s_time+",\n" + 
			"                            \"endTime\": "+e_time+",\n" + 
			"                            \"sort\": 0,\n" + 
			"                            \"id\": \"\"\n" + 
			"                        }\n" + 
			"                    ],\n" + 
			"                    \"directors\": \""+userId+"\",\n" + 
			"                    \"qrcodeType\": \"statics\",\n" + 
			"                    \"mappingId\": \"\",\n" + 
			"                    \"assessment\": []\n" + 
			"                }\n" + 
			"            ]\n" + 
			"        }\n" + 
			"    ],\n" + 
			"    \"stagePass\": false,\n" + 
			"    \"finishEvaluation\": false,\n" + 
			"    \"openEvaluationResult\": false,\n" + 
			"    \"action\": \"published\",\n" + 
			"    \"access_token\": \""+token+"\"\n" + 
			"}").send().body();
		
	}
	
	public static String addOfflineTypeStudyTask  (String title,String beginTime,String endTime ,
			String beginTimeStr,String endTimeStr,String lecturerId,String clockTime) {
		return PostRequestTools.RequestBodyByPost("{\r\n" + 
				"  \"title\": \""+title+"\", \r\n" + 
				"  \"beginTime\": "+beginTime+", \r\n" + 
				"  \"endTime\": "+endTime+", \r\n" + 
				"  \"dingImgUrl\": \"\", \r\n" + 
				"  \"departmentIds\": \"\", \r\n" + 
				"  \"groupIds\": \"\", \r\n" + 
				"  \"isGetScore\": true, \r\n" + 
				"  \"isNoticeAll\": 0, \r\n" + 
				"  \"planCertificateId\": \"\", \r\n" + 
				"  \"postIds\": \"\", \r\n" + 
				"  \"progress\": 100, \r\n" + 
				"  \"studyScore\": {\r\n" + 
				"    \"finishScore\": 0, \r\n" + 
				"    \"unFinishScore\": 0\r\n" + 
				"  }, \r\n" + 
				"  \"studyTimeLimit\": 0, \r\n" + 
				"  \"supervisorId\": \""+userId+"\", \r\n" + 
				"  \"supervisorPaper\": true, \r\n" + 
				"  \"times\": \"\", \r\n" + 
				"  \"userIds\": \""+userId+"\", \r\n" + 
				"  \"stageJson\": [\r\n" + 
				"    {\r\n" + 
				"      \"content\": \"\", \r\n" + 
				"      \"stageName\": \"step one\", \r\n" + 
				"      \"isOrder\": false, \r\n" + 
				"      \"stageSort\": 1, \r\n" + 
				"      \"course\": [\r\n" + 
				"        {\r\n" + 
				"          \"courseSort\": 0, \r\n" + 
				"          \"flag\": 3, \r\n" + 
				"          \"id\": \"\", \r\n" + 
				"          \"type\": \"xx\", \r\n" + 
				"          \"address\": \"xi'an yanta qu\", \r\n" + 
				"          \"title\": \"offline traner\", \r\n" + 
				"          \"introduction\": \"<p>des</p>\", \r\n" + 
				"          \"beginTimeStr\": \""+beginTimeStr+"\", \r\n" + 
				"          \"endTimeStr\": \""+endTimeStr+"\", \r\n" + 
				"          \"lecturerId\": \""+lecturerId+"\", \r\n" + 
				"          \"bizLatojaClockTimeList\": [\r\n" + 
				"            {\r\n" + 
				"              \"latojaId\": \"\", \r\n" + 
				"              \"clockTime\": "+clockTime+", \r\n" + 
				"              \"sort\": 0, \r\n" + 
				"              \"id\": \"\"\r\n" + 
				"            }\r\n" + 
				"          ], \r\n" + 
				"          \"directors\": \""+userId+"\", \r\n" + 
				"          \"qrcodeType\": \"statics\"\r\n" + 
				"        }\r\n" + 
				"      ]\r\n" + 
				"    }\r\n" + 
				"  ], \r\n" + 
				"  \"access_token\": \""+token+"\"\r\n" + 
				"}", token, add_study_task_url);
	}
	
	public static String addOnlineTypeStudyTask(String title,String beginTime,String endTime,String planCertificateId,String artId,String courseId
			,String questionnaireId) {
		return PostRequestTools.RequestBodyByPost("{\r\n" + 
				"  \"title\": \""+title+"\", \r\n" + 
				"  \"beginTime\": "+beginTime+", \r\n" + 
				"  \"endTime\": "+endTime+", \r\n" + 
				"  \"dingImgUrl\": \"\", \r\n" + 
				"  \"departmentIds\": \"\", \r\n" + 
				"  \"groupIds\": \"\", \r\n" + 
				"  \"isGetScore\": true, \r\n" + 
				"  \"isNoticeAll\": 0, \r\n" + 
				"  \"planCertificateId\": \""+planCertificateId+"\", \r\n" + 
				"  \"postIds\": \"\", \r\n" + 
				"  \"progress\": 100, \r\n" + 
				"  \"studyScore\": {\r\n" + 
				"    \"finishScore\": 6, \r\n" + 
				"    \"unFinishScore\": 4\r\n" + 
				"  }, \r\n" + 
				"  \"studyTimeLimit\": 1, \r\n" + 
				"  \"supervisorId\": \""+userId+"\", \r\n" + 
				"  \"supervisorPaper\": true, \r\n" + 
				"  \"times\": \"\", \r\n" + 
				"  \"userIds\": \""+userId+"\", \r\n" + 
				"  \"stageJson\": [\r\n" + 
				"    {\r\n" + 
				"      \"content\": \"\", \r\n" + 
				"      \"stageName\": \"Step one\", \r\n" + 
				"      \"isOrder\": true, \r\n" + 
				"      \"stageSort\": 1, \r\n" + 
				"      \"course\": [\r\n" + 
				"        {\r\n" + 
				"          \"courseId\": \""+artId+"\", \r\n" + 
				"          \"courseSort\": 0, \r\n" + 
				"          \"courseType\": 3, \r\n" + 
				"          \"flag\": 1\r\n" + 
				"        }, \r\n" + 
				"        {\r\n" + 
				"          \"courseId\": \""+courseId+"\", \r\n" + 
				"          \"courseSort\": 1, \r\n" + 
				"          \"courseType\": 1, \r\n" + 
				"          \"flag\": 1\r\n" + 
				"        }, \r\n" + 
				"        {\r\n" + 
				"          \"courseSort\": 2, \r\n" + 
				"          \"courseType\": 9, \r\n" + 
				"          \"flag\": 4, \r\n" + 
				"          \"id\": \""+questionnaireId+"\", \r\n" + 
				"          \"questionnaireId\": \""+questionnaireId+"\", \r\n" + 
				"          \"title\": \""+BaseBusiness.questionnaireName+"\", \r\n" + 
				"          \"type\": \"questionnaire\"\r\n" + 
				"        }\r\n" + 
				"      ]\r\n" + 
				"    }\r\n" + 
				"  ], \r\n" + 
				"  \"access_token\": \""+token+"\"\r\n" + 
				"}", token, add_study_task_url);
	}
	
	public static String  addMixSettledExamStudyTask(String title,String beginTime,String endTime,String planCertificateId,
			String artId,String courseId,String paperId,String beginTimeStr,String endTimeStr,String lecturerId,
			String clockTime,String questionnaireId,String e_time) {
		return PostRequestTools.RequestBodyByPost("{\r\n" + 
				"  \"title\": \""+title+"\", \r\n" + 
				"  \"beginTime\": "+beginTime+", \r\n" + 
				"  \"endTime\": "+endTime+", \r\n" + 
				"  \"dingImgUrl\": \"\", \r\n" + 
				"  \"departmentIds\": \"\", \r\n" + 
				"  \"groupIds\": \"\", \r\n" + 
				"  \"isGetScore\": true, \r\n" + 
				"  \"isNoticeAll\": 0, \r\n" + 
				"  \"planCertificateId\": \""+planCertificateId+"\", \r\n" + 
				"  \"postIds\": \"\", \r\n" + 
				"  \"progress\": 100, \r\n" + 
				"  \"studyScore\": {\r\n" + 
				"    \"finishScore\": 8, \r\n" + 
				"    \"unFinishScore\": 6\r\n" + 
				"  }, \r\n" + 
				"  \"studyTimeLimit\": 0, \r\n" + 
				"  \"supervisorId\": \""+userId+"\", \r\n" + 
				"  \"supervisorPaper\": true, \r\n" + 
				"  \"times\": 1, \r\n" + 
				"  \"userIds\": \""+userId+"\", \r\n" + 
				"  \"stageJson\": [\r\n" + 
				"    {\r\n" + 
				"      \"content\": \"\", \r\n" + 
				"      \"stageName\": \"step one\", \r\n" + 
				"      \"isOrder\": false, \r\n" + 
				"      \"stageSort\": 1, \r\n" + 
				"      \"course\": [\r\n" + 
				"        {\r\n" + 
				"          \"courseId\": \""+artId+"\", \r\n" + 
				"          \"courseSort\": 0, \r\n" + 
				"          \"courseType\": 3, \r\n" + 
				"          \"flag\": 1\r\n" + 
				"        }, \r\n" + 
				"        {\r\n" + 
				"          \"courseId\": \""+courseId+"\", \r\n" + 
				"          \"courseSort\": 1, \r\n" + 
				"          \"courseType\": 1, \r\n" + 
				"          \"flag\": 1\r\n" + 
				"        }, \r\n" + 
				"        {\r\n" + 
				"          \"courseSort\": 2, \r\n" + 
				"          \"title\": \"exam\", \r\n" + 
				"          \"type\": \"ex\", \r\n" + 
				"          \"cheatFlag\": 0, \r\n" + 
				"          \"examDuration\": 45, \r\n" + 
				"          \"flag\": 2, \r\n" + 
				"          \"courseId\": \"\", \r\n" + 
				"          \"markType\": 1, \r\n" + 
				"          \"paperId\": \""+paperId+"\", \r\n" + 
				"          \"paperTitle\": \""+BaseBusiness.pass_paper_name+"\", \r\n" +
				
				"          \"summary\":\"this is a summary\",\r\n"+
				"          \"passLine\": 60, \r\n" + 
				"          \"questionBankList\": \"\", \r\n" + 
				"          \"questionMode\": 1, \r\n" + 
				"          \"showKnowledge\": \"show\", \r\n" + 
				"          \"repeatExam\": false, \r\n" + 
				"          \"multipleCount\": \"\", \r\n" + 
				"          \"multipleUnitScore\": \"\", \r\n" + 
				"          \"fillBlankCount\": \"\", \r\n" + 
				"          \"fillBlankUnitScore\": \"\", \r\n" + 
				"          \"shortAnswerCount\": \"\", \r\n" + 
				"          \"shortAnswerUnitScore\": \"\", \r\n" + 
				"          \"singleCount\": \"\", \r\n" + 
				"          \"singleUnitScore\": \"\", \r\n" + 
				"          \"trueOrFalseCount\": \"\", \r\n" + 
				"          \"trueOrFalseUnitScore\": \"\", \r\n" + 
				"          \"totalScore\": 100, \r\n" + 
				"          \"answerParsing\": 2, \r\n" + 
				"          \"passingScore\": \"60.0\", \r\n" + 
				"          \"cutScreenCount\": 0, \r\n" + 
				"          \"reExamRule\": 0, \r\n" + 
				"          \"reExamNumber\": 0, \r\n" + 
				"          \"viewRule\": 1, \r\n" +
				"          \"scoreRule\": 0\r\n" + 
				"        }, \r\n" + 
				"        {\r\n" + 
				"          \"courseSort\": 3, \r\n" + 
				"          \"flag\": 3, \r\n" + 
				"          \"id\": \"\", \r\n" + 
				"          \"type\": \"xx\", \r\n" + 
				"          \"address\": \"xi'an\", \r\n" + 
				"          \"title\": \"offline trainer\", \r\n" + 
				"          \"introduction\": \"<p>this is des</p>\", \r\n" + 
				"          \"beginTimeStr\": \""+beginTimeStr+"\", \r\n" + 
				"          \"endTimeStr\": \""+endTimeStr+"\", \r\n" + 
				"          \"lecturerId\": \""+lecturerId+"\", \r\n" + 
				"          \"bizLatojaClockTimeList\": [\r\n" + 
				"            {\r\n" + 
				"              \"latojaId\": \"\", \r\n" + 
				"              \"clockTime\": "+clockTime+", \r\n" + 
				"				\"endTime\":"+e_time+","+
				"              \"sort\": 0\r\n" + 
				"            }\r\n" + 
				"          ], \r\n" + 
				"          \"directors\": \""+userId+"\", \r\n" + 
				"          \"qrcodeType\": \"statics\"\r\n" + 
				"        }, \r\n" + 
				"        {\r\n" + 
				"          \"courseSort\": 4, \r\n" + 
				"          \"courseType\": 9, \r\n" + 
				"          \"flag\": 4, \r\n" + 
				"          \"id\": \""+questionnaireId+"\", \r\n" + 
				"          \"questionnaireId\": \""+questionnaireId+"\", \r\n" + 
				"          \"title\": \"questionnaire_name\", \r\n" + 
				"          \"type\": \"questionnaire\"\r\n" + 
				"        }\r\n" + 
				"      ]\r\n" + 
				"    }\r\n" + 
				"  ], \r\n" + 
				"  \"access_token\": \""+token+"\"\r\n" + 
				"}", token, add_study_task_url);
	}
	
	
	public static String  addMixSettledExamStudyTask(String title,String beginTime,String endTime,String planCertificateId,
			String artId,String courseId,String paperId,String beginTimeStr,String endTimeStr,String lecturerId,
			String clockTime,String questionnaireId) {
		return PostRequestTools.RequestBodyByPost("{\r\n" + 
				"  \"title\": \""+title+"\", \r\n" + 
				"  \"beginTime\": "+beginTime+", \r\n" + 
				"  \"endTime\": "+endTime+", \r\n" + 
				"  \"dingImgUrl\": \"\", \r\n" + 
				"  \"departmentIds\": \"\", \r\n" + 
				"  \"groupIds\": \"\", \r\n" + 
				"  \"isGetScore\": true, \r\n" + 
				"  \"isNoticeAll\": 0, \r\n" + 
				"  \"planCertificateId\": \""+planCertificateId+"\", \r\n" + 
				"  \"postIds\": \"\", \r\n" + 
				"  \"progress\": 100, \r\n" + 
				"  \"studyScore\": {\r\n" + 
				"    \"finishScore\": 8, \r\n" + 
				"    \"unFinishScore\": 6\r\n" + 
				"  }, \r\n" + 
				"  \"studyTimeLimit\": 0, \r\n" + 
				"  \"supervisorId\": \""+userId+"\", \r\n" + 
				"  \"supervisorPaper\": true, \r\n" + 
				"  \"times\": 1, \r\n" + 
				"  \"userIds\": \""+userId+"\", \r\n" + 
				"  \"stageJson\": [\r\n" + 
				"    {\r\n" + 
				"      \"content\": \"\", \r\n" + 
				"      \"stageName\": \"step one\", \r\n" + 
				"      \"isOrder\": false, \r\n" + 
				"      \"stageSort\": 1, \r\n" + 
				"      \"course\": [\r\n" + 
				"        {\r\n" + 
				"          \"courseId\": \""+artId+"\", \r\n" + 
				"          \"courseSort\": 0, \r\n" + 
				"          \"courseType\": 3, \r\n" + 
				"          \"flag\": 1\r\n" + 
				"        }, \r\n" + 
				"        {\r\n" + 
				"          \"courseId\": \""+courseId+"\", \r\n" + 
				"          \"courseSort\": 1, \r\n" + 
				"          \"courseType\": 1, \r\n" + 
				"          \"flag\": 1\r\n" + 
				"        }, \r\n" + 
				"        {\r\n" + 
				"          \"courseSort\": 2, \r\n" + 
				"          \"title\": \"exam\", \r\n" + 
				"          \"type\": \"ex\", \r\n" + 
				"          \"cheatFlag\": 0, \r\n" + 
				"          \"examDuration\": 45, \r\n" + 
				"          \"flag\": 2, \r\n" + 
				"          \"courseId\": \"\", \r\n" + 
				"          \"markType\": 1, \r\n" + 
				"          \"paperId\": \""+paperId+"\", \r\n" + 
				"          \"paperTitle\": \""+BaseBusiness.pass_paper_name+"\", \r\n" +
				
				"          \"summary\":\"this is a summary\",\r\n"+
				"          \"passLine\": 60, \r\n" + 
				"          \"questionBankList\": \"\", \r\n" + 
				"          \"questionMode\": 1, \r\n" + 
				"          \"showKnowledge\": \"show\", \r\n" + 
				"          \"repeatExam\": false, \r\n" + 
				"          \"multipleCount\": \"\", \r\n" + 
				"          \"multipleUnitScore\": \"\", \r\n" + 
				"          \"fillBlankCount\": \"\", \r\n" + 
				"          \"fillBlankUnitScore\": \"\", \r\n" + 
				"          \"shortAnswerCount\": \"\", \r\n" + 
				"          \"shortAnswerUnitScore\": \"\", \r\n" + 
				"          \"singleCount\": \"\", \r\n" + 
				"          \"singleUnitScore\": \"\", \r\n" + 
				"          \"trueOrFalseCount\": \"\", \r\n" + 
				"          \"trueOrFalseUnitScore\": \"\", \r\n" + 
				"          \"totalScore\": 100, \r\n" + 
				"          \"answerParsing\": 2, \r\n" + 
				"          \"passingScore\": \"60.0\", \r\n" + 
				"          \"cutScreenCount\": 0, \r\n" + 
				"          \"reExamRule\": 0, \r\n" + 
				"          \"reExamNumber\": 0, \r\n" + 
				"          \"viewRule\": 1, \r\n" +
				"          \"scoreRule\": 0\r\n" + 
				"        }, \r\n" + 
				"        {\r\n" + 
				"          \"courseSort\": 3, \r\n" + 
				"          \"flag\": 3, \r\n" + 
				"          \"id\": \"\", \r\n" + 
				"          \"type\": \"xx\", \r\n" + 
				"          \"address\": \"xi'an\", \r\n" + 
				"          \"title\": \"offline trainer\", \r\n" + 
				"          \"introduction\": \"<p>this is des</p>\", \r\n" + 
				"          \"beginTimeStr\": \""+beginTimeStr+"\", \r\n" + 
				"          \"endTimeStr\": \""+endTimeStr+"\", \r\n" + 
				"          \"lecturerId\": \""+lecturerId+"\", \r\n" + 
				"          \"bizLatojaClockTimeList\": [\r\n" + 
				"            {\r\n" + 
				"              \"latojaId\": \"\", \r\n" + 
				"              \"clockTime\": "+clockTime+", \r\n" + 
				"              \"sort\": 0\r\n" + 
				"            }\r\n" + 
				"          ], \r\n" + 
				"          \"directors\": \""+userId+"\", \r\n" + 
				"          \"qrcodeType\": \"statics\"\r\n" + 
				"        }, \r\n" + 
				"        {\r\n" + 
				"          \"courseSort\": 4, \r\n" + 
				"          \"courseType\": 9, \r\n" + 
				"          \"flag\": 4, \r\n" + 
				"          \"id\": \""+questionnaireId+"\", \r\n" + 
				"          \"questionnaireId\": \""+questionnaireId+"\", \r\n" + 
				"          \"title\": \"questionnaire_name\", \r\n" + 
				"          \"type\": \"questionnaire\"\r\n" + 
				"        }\r\n" + 
				"      ]\r\n" + 
				"    }\r\n" + 
				"  ], \r\n" + 
				"  \"access_token\": \""+token+"\"\r\n" + 
				"}", token, add_study_task_url);
	}
	
	
	public static String  addMixSettledExamStudyTask(String title,String beginTime,String endTime,String planCertificateId,
			String artId,String courseId,String paperId,String beginTimeStr,String endTimeStr,
			String questionnaireId) {
		return PostRequestTools.RequestBodyByPost("{\r\n" + 
				"  \"title\": \""+title+"\", \r\n" + 
				"  \"beginTime\": "+beginTime+", \r\n" + 
				"  \"endTime\": "+endTime+", \r\n" + 
				"  \"dingImgUrl\": \"\", \r\n" + 
				"  \"departmentIds\": \"\", \r\n" + 
				"  \"groupIds\": \"\", \r\n" + 
				"  \"isGetScore\": true, \r\n" + 
				"  \"isNoticeAll\": 0, \r\n" + 
				"  \"planCertificateId\": \""+planCertificateId+"\", \r\n" + 
				"  \"postIds\": \"\", \r\n" + 
				"  \"progress\": 100, \r\n" + 
				"  \"studyScore\": {\r\n" + 
				"    \"finishScore\": 8, \r\n" + 
				"    \"unFinishScore\": 6\r\n" + 
				"  }, \r\n" + 
				"  \"studyTimeLimit\": 0, \r\n" + 
				"  \"supervisorId\": \""+userId+"\", \r\n" + 
				"  \"supervisorPaper\": true, \r\n" + 
				"  \"times\": 1, \r\n" + 
				"  \"userIds\": \""+userId+"\", \r\n" + 
				"  \"stageJson\": [\r\n" + 
				"    {\r\n" + 
				"      \"content\": \"\", \r\n" + 
				"      \"stageName\": \"step one\", \r\n" + 
				"      \"isOrder\": false, \r\n" + 
				"      \"stageSort\": 1, \r\n" + 
				"      \"course\": [\r\n" + 
				"        {\r\n" + 
				"          \"courseId\": \""+artId+"\", \r\n" + 
				"          \"courseSort\": 0, \r\n" + 
				"          \"courseType\": 1, \r\n" + 
				"          \"flag\": 1\r\n" + 
				"        }, \r\n" + 
				"        {\r\n" + 
				"          \"courseId\": \""+courseId+"\", \r\n" + 
				"          \"courseSort\": 1, \r\n" + 
				"          \"courseType\": 1, \r\n" + 
				"          \"flag\": 1\r\n" + 
				"        }, \r\n" + 
				"        {\r\n" + 
				"          \"courseSort\": 2, \r\n" + 
				"          \"title\": \"exam\", \r\n" + 
				"          \"type\": \"ex\", \r\n" + 
				"          \"cheatFlag\": 0, \r\n" + 
				"          \"examDuration\": 45, \r\n" + 
				"          \"flag\": 2, \r\n" + 
				"          \"courseId\": \"\", \r\n" + 
				"          \"markType\": 1, \r\n" + 
				"          \"paperId\": \""+paperId+"\", \r\n" + 
				"          \"paperTitle\": \""+BaseBusiness.pass_paper_name+"\", \r\n" +
				
				"          \"summary\":\"this is a summary\",\r\n"+
				"          \"passLine\": 60, \r\n" + 
				"          \"questionBankList\": \"\", \r\n" + 
				"          \"questionMode\": 1, \r\n" + 
				"          \"showKnowledge\": \"show\", \r\n" + 
				"          \"repeatExam\": false, \r\n" + 
				"          \"multipleCount\": \"\", \r\n" + 
				"          \"multipleUnitScore\": \"\", \r\n" + 
				"          \"fillBlankCount\": \"\", \r\n" + 
				"          \"fillBlankUnitScore\": \"\", \r\n" + 
				"          \"shortAnswerCount\": \"\", \r\n" + 
				"          \"shortAnswerUnitScore\": \"\", \r\n" + 
				"          \"singleCount\": \"\", \r\n" + 
				"          \"singleUnitScore\": \"\", \r\n" + 
				"          \"trueOrFalseCount\": \"\", \r\n" + 
				"          \"trueOrFalseUnitScore\": \"\", \r\n" + 
				"          \"totalScore\": 100, \r\n" + 
				"          \"answerParsing\": 2, \r\n" + 
				"          \"passingScore\": \"60.0\", \r\n" + 
				"          \"cutScreenCount\": 0, \r\n" + 
				"          \"reExamRule\": 0, \r\n" + 
				"          \"reExamNumber\": 0, \r\n" + 
				"          \"viewRule\": 1, \r\n" +
				"          \"scoreRule\": 0\r\n" + 
				"        }, \r\n" + 
				
				"        {\r\n" + 
				"          \"courseSort\": 4, \r\n" + 
				"          \"courseType\": 9, \r\n" + 
				"          \"flag\": 4, \r\n" + 
				"          \"id\": \""+questionnaireId+"\", \r\n" + 
				"          \"questionnaireId\": \""+questionnaireId+"\", \r\n" + 
				"          \"title\": \"questionnaire_name\", \r\n" + 
				"          \"type\": \"questionnaire\"\r\n" + 
				"        }\r\n" + 
				"      ]\r\n" + 
				"    }\r\n" + 
				"  ], \r\n" + 
				"  \"access_token\": \""+token+"\"\r\n" + 
				"}", token, add_study_task_url);
	}
}