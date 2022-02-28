package cn.kxy.study.business;

import cn.kxy.base.business.EnterpriseData;
import cn.kxy.base.business.EnterpriseDataUrl;
import cn.kxy.base.business.TokenData;
import cn.kxy.setting.bussiness.UserBusiness;
import com.lazy.assured.utils.PostRequestTools;
import com.lazy.common.utils.DateUtil;

public class ExamOptimizationStudy {
	public static String token = TokenData.getMangerToken();
	public static String enterpriseId = EnterpriseData.getEnterpriseId();
	public static String enterpriseUrl = EnterpriseDataUrl.getEnterpriseUrl();
	public static String userId = UserBusiness.getUserId();
	public static String add_study_task_url =enterpriseUrl + "plan/study/add";
	
	/**   
	 * @Title: addExamStudy   
	 * @Description: TODO 新增只查看对错的考试
	 * @param: @param title
	 * @param: @param paperId
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */  
	public static String addExamStudy(String title,String paperId) {
		return PostRequestTools.RequestBodyByPost("{\r\n" + 
				"  \"title\":\""+title+"\",\r\n" + 
				"  \"beginTime\":"+DateUtil.getTimeStamp(0, "")+",\r\n" + 
				"  \"endTime\":"+DateUtil.getTimeStamp(7, "")+",\r\n" + 
				"  \"summary\":\"this is a summary\",\r\n" + 
				"  \"dingImgUrl\":\"\",\r\n" + 
				"  \"departmentIds\":\"\",\r\n" + 
				"  \"supervisor_department_ids\":[\r\n" + 
				"  ],\r\n" + 
				"  \"groupIds\":\"\",\r\n" + 
				"  \"isGetScore\":true,\r\n" + 
				"  \"isNoticeAll\":0,\r\n" + 
				"  \"planCertificateId\":\"\",\r\n" + 
				"  \"postIds\":\"\",\r\n" + 
				"  \"progress\":0,\r\n" + 
				"  \"studyScore\":{\r\n" + 
				"    \"finishScore\":0,\r\n" + 
				"    \"unFinishScore\":0\r\n" + 
				"  },\r\n" + 
				"  \"studyTimeLimit\":0,\r\n" + 
				"  \"faceRecognition\":\"off\",\r\n" + 
				"  \"supervisorId\":\""+userId+"\",\r\n" + 
				"  \"supervisorPaper\":true,\r\n" + 
				"  \"authorityRange\":false,\r\n" + 
				"  \"times\":1,\r\n" + 
				"  \"operationTimes\":\"\",\r\n" + 
				"  \"userIds\":\""+userId+"\",\r\n" + 
				"  \"stageJson\":[\r\n" + 
				"    {\r\n" + 
				"      \"content\":\"\",\r\n" + 
				"      \"stageName\":\"step one\",\r\n" + 
				"      \"isOrder\":false,\r\n" + 
				"      \"stageSort\":1,\r\n" + 
				"      \"course\":[\r\n" + 
				"        {\r\n" + 
				"          \"courseSort\":0,\r\n" + 
				"          \"title\":\"paperCopys53ZG0903172742\",\r\n" + 
				"          \"type\":\"ex\",\r\n" + 
				"          \"cheatFlag\":0,\r\n" + 
				"          \"faceRecognition\":\"off\",\r\n" + 
				"          \"examDuration\":45,\r\n" + 
				"          \"summary\":\"instruction\",\r\n" + 
				"          \"flag\":2,\r\n" + 
				"          \"courseId\":\"\",\r\n" + 
				"          \"markType\":1,\r\n" + 
				"          \"paperId\":\""+paperId+"\",\r\n" + 
				"          \"paperTitle\":\"paperCopys53ZG0903172742\",\r\n" + 
				"          \"passLine\":60,\r\n" + 
				"          \"questionBankList\":\"\",\r\n" + 
				"          \"questionMode\":1,\r\n" + 
				"          \"showKnowledge\":\"show\",\r\n" + 
				"          \"repeatExam\":true,\r\n" + 
				"          \"multipleCount\":\"\",\r\n" + 
				"          \"multipleUnitScore\":\"\",\r\n" + 
				"          \"fillBlankCount\":\"\",\r\n" + 
				"          \"fillBlankUnitScore\":\"\",\r\n" + 
				"          \"shortAnswerCount\":\"\",\r\n" + 
				"          \"shortAnswerUnitScore\":\"\",\r\n" + 
				"          \"singleCount\":\"\",\r\n" + 
				"          \"singleUnitScore\":\"\",\r\n" + 
				"          \"trueOrFalseCount\":\"\",\r\n" + 
				"          \"trueOrFalseUnitScore\":\"\",\r\n" + 
				"          \"totalScore\":150,\r\n" + 
				"          \"answerParsing\":5,\r\n" + 
				"          \"passingScore\":\"90.0\",\r\n" + 
				"          \"cutScreenCount\":-1,\r\n" + 
				"          \"reExamRule\":0,\r\n" + 
				"          \"reExamNumber\":0,\r\n" + 
				"          \"scoreRule\":0,\r\n" + 
				"          \"viewRule\":2\r\n" + 
				"        }]\r\n" + 
				"    }],\r\n" + 
				"  \"stagePass\":false,\r\n" + 
				"  \"finishEvaluation\":false,\r\n" + 
				"  \"openEvaluationResult\":false,\r\n" + 
				"  \"access_token\":\""+token+"\"\r\n" + 
				"}\r\n" + 
				"", token, add_study_task_url);
	}
}
