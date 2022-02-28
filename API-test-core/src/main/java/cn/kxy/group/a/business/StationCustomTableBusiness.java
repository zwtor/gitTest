package cn.kxy.group.a.business;


import cn.kxy.base.business.EnterpriseData;
import cn.kxy.base.business.EnterpriseDataUrl;
import cn.kxy.base.business.TokenData;
import cn.kxy.setting.bussiness.UserBusiness;
import com.alibaba.fastjson.JSONPath;
import com.lazy.httpclient.utils.HttpRequest;

public class StationCustomTableBusiness {

	public static String enterprise_url = EnterpriseDataUrl.getEnterpriseUrl();
	public static String enterpriseId = EnterpriseData.getEnterpriseId();
	public static String access_token = TokenData.getMangerToken();
	public static String userId = UserBusiness.getUserId();
	public static String custom_sava = enterprise_url + "v2/" + enterpriseId + "/custom_table/" + userId + "/save";

	public static String customtrain_tablehead(String table_key) {
		return enterprise_url + "v2/" + enterpriseId + "/table_key/" + table_key + "/user/" + userId
				+ "/get_custom_head";
	}

	/**
	 * 获取表头
	 * @param table_key
	 * @return
	 */
	public static String getcustomtrain_tablehead(String table_key) {
		return HttpRequest.get(customtrain_tablehead(table_key)).query("access_token", access_token).send().body();
	}

	
	
	/**
	 * 
	 * @param table_key
	 * @return
	 */
	public static String savaReset_tablehead (String table_key) {
		HttpRequest.post(custom_sava).query("access_token", access_token).data(
				"{\r\n" + 
				"    \"fields\":[\r\n" + 
				"\r\n" + 
				"    ],\r\n" + 
				"    \"key\":\""+table_key+"\"\",\r\n" + 
				"    \"access_token\":\""+access_token+"\"\"\r\n" + 
				"}"
				).send().body();
		
		
		
		return null;
	}
	/**
	 * 修改【个人中心-培训档案-任务档案】表格字段
	 * @param table_key
	 * @param fales
	 * @return
	 */
	public static String savaArchives_tablehead(String table_key,Boolean fales) {

		return 	HttpRequest.post(custom_sava).query("access_token", access_token).data(
				"{\r\n" + 
				"    \"fields\":[\r\n" + 
				"        {\r\n" + 
				"            \"field\":\"planName\",\r\n" + 
				"            \"show\":"+fales+",\r\n" + 
				"            \"fixed\":\"left\"\r\n" + 
				"        },\r\n" + 
				"        {\r\n" + 
				"            \"field\":\"userTrainBeginTime\",\r\n" + 
				"            \"show\":"+fales+",\r\n" + 
				"            \"fixed\":null\r\n" + 
				"        },\r\n" + 
				"        {\r\n" + 
				"            \"field\":\"taskTerm\",\r\n" + 
				"            \"show\":"+fales+",\r\n" + 
				"            \"fixed\":null\r\n" + 
				"        },\r\n" + 
				"        {\r\n" + 
				"            \"field\":\"taskType\",\r\n" + 
				"            \"show\":"+fales+",\r\n" + 
				"            \"fixed\":null\r\n" + 
				"        },\r\n" + 
				"        {\r\n" + 
				"            \"field\":\"progressOrScore\",\r\n" + 
				"            \"show\":"+fales+",\r\n" + 
				"            \"fixed\":null\r\n" + 
				"        },\r\n" + 
				"        {\r\n" + 
				"            \"field\":\"statusName\",\r\n" + 
				"            \"show\":"+fales+",\r\n" + 
				"            \"fixed\":null\r\n" + 
				"        },\r\n" + 
				"        {\r\n" + 
				"            \"field\":\"certificateName\",\r\n" + 
				"            \"show\":"+fales+",\r\n" + 
				"            \"fixed\":null\r\n" + 
				"        },\r\n" + 
				"        {\r\n" + 
				"            \"field\":\"studyScoreStr\",\r\n" + 
				"            \"show\":"+fales+",\r\n" + 
				"            \"fixed\":null\r\n" + 
				"        }\r\n" + 
				"    ],\r\n" + 
				"    \"key\":\""+table_key+"\",\r\n" + 
				"    \"access_token\":\""+access_token+"\"\r\n" + 
				"}")
		.send().body();
	
	}

	/**
	 * 【个人中心-培训记录-证书记录】表头显示隐藏修改
	 * @param table_key
	 * @param fales
	 * @return
	 */
	
	public static String saveCertificate_tablehead(String table_key,Boolean fales) {
		
		return HttpRequest.post(custom_sava).query("access_token", access_token).data(
				"{\r\n" + 
				"	\"fields\":\r\n" + 
				"	[{\r\n" + 
				"		\"field\":\"certificate_id\",\r\n" + 
				"       \"show\":"+fales+",\r\n" + 
				"		\r\n" + 
				"	},{\r\n" + 
				"		\"field\":\"certificate_name\",\r\n" + 
				"       \"show\":"+fales+",\r\n" + 
				"		\r\n" + 
				"	},{\r\n" + 
				"		\"field\":\"source_name\",\r\n" + 
				"       \"show\":"+fales+",\r\n" + 
				"		\r\n" + 
				"	},{\r\n" + 
				"		\"field\":\"create_time\",\r\n" + 
				"       \"show\":"+fales+",\r\n" + 
				"		\r\n" + 
				"	},{\r\n" + 
				"		\"field\":\"source_type_name\",\r\n" + 
				"       \"show\":"+fales+",\r\n" + 
				"		\r\n" + 
				"	},{\r\n" + 
				"		\"field\":\"overdue_date\",\r\n" + 
				"       \"show\":"+fales+",\r\n" + 
				"		\r\n" + 
				"	}],\r\n" + 
				"    \"key\":\""+table_key+"\",\r\n" + 
				"    \"access_token\":\""+access_token+"\"\r\n" + 
				"	\r\n" + 
				"}"
				).send().body();

	}
	
	/**
	 * 【培训-学习-学习任务】列表字段
	 * @param table_key
	 * @param fales
	 * @return
	 */
	public static String savaStudytask_tablehead(String table_key,Boolean fales) {
		
		return HttpRequest.post(custom_sava).query("access_token", access_token).data(
				"{\r\n" + 
				"	\"fields\":\r\n" + 
				"	[{\r\n" + 
				"		\"field\":\"id\",\r\n" + 
				"       \"show\":"+fales+",\r\n" + 
				"		\"fixed\":\"left\"\r\n" + 
				"	},{\r\n" + 
				"		\"field\":\"title\",\r\n" + 
				"       \"show\":"+fales+",\r\n" + 
				"		\"fixed\":\"left\"\r\n" + 
				"	},{\r\n" + 
				"		\"field\":\"classify_name\",\r\n" + 
				"       \"show\":"+fales+",\r\n" + 
				"	},{\r\n" + 
				"		\"field\":\"finishUserCount\",\r\n" + 
				"       \"show\":"+fales+",\r\n" + 
				"	},{\r\n" + 
				"		\"field\":\"onGoingUserCount\",\r\n" + 
				"       \"show\":"+fales+",\r\n" + 
				"	},{\r\n" + 
				"		\"field\":\"unFinishUserCount\",\r\n" + 
				"       \"show\":"+fales+",\r\n" + 
				"	},{\r\n" + 
				"		\"field\":\"finishPercent\",\r\n" + 
				"       \"show\":"+fales+",\r\n" + 
				"	},{\r\n" + 
				"		\"field\":\"qualifiedRate\",\r\n" + 
				"       \"show\":"+fales+",\r\n" + 
				"	},{\r\n" + 
				"		\"field\":\"joinRate\",\r\n" + 
				"       \"show\":"+fales+",\r\n" + 
				"	},{\r\n" + 
				"		\"field\":\"pendingCount\",\r\n" + 
				"       \"show\":"+fales+",\r\n" + 
				"	},{\r\n" + 
				"		\"field\":\"creatorName\",\r\n" + 
				"       \"show\":"+fales+",\r\n" + 
				"	},{\r\n" + 
				"		\"field\":\"createTime\",\r\n" + 
				"       \"show\":"+fales+",\r\n" + 
				"	},{\r\n" + 
				"		\"field\":\"beginTime\",\r\n" + 
				"       \"show\":"+fales+",\r\n" + 
				"	},{\r\n" + 
				"		\"field\":\"operate\",\r\n" + 
				"       \"show\":"+fales+",\r\n" + 
				"		\"fixed\":\"right\"\r\n" + 
				"	}],\r\n" + 
				"    \"key\":\""+table_key+"\",\r\n" + 
				"    \"access_token\":\""+access_token+"\"\r\n" + 
				"} "
				).send().body();
	}
	
	/**
	 * 【知识库-学习资源-学习项目】列表表头字段
	 * @param table_key
	 * @param fales
	 * @return
	 */
	public static String savaStudyproject_tablehead(String table_key,Boolean fales) {
		
		return HttpRequest.post(custom_sava).query("access_token", access_token).data(
				"{\r\n" + 
				"	\"fields\":\r\n" + 
				"	[{\r\n" + 
				"		\"field\":\"id\",\r\n" + 
				"       \"show\":"+fales+",\r\n" + 
				"		\"fixed\":\"left\"\r\n" + 
				"	},{\r\n" + 
				"		\"field\":\"classify_name\",\r\n" + 
				"       \"show\":"+fales+",\r\n" + 
				"	},{\r\n" + 
				"		\"field\":\"create_user_name\",\r\n" + 
				"       \"show\":"+fales+",\r\n" + 
				"	},{\r\n" + 
				"		\"field\":\"lecturer_name\",\r\n" + 
				"       \"show\":"+fales+",\r\n" + 
				"	},{\r\n" + 
				"		\"field\":\"need_enroll\",\r\n" + 
				"       \"show\":"+fales+",\r\n" + 
				"	},{\r\n" + 
				"		\"field\":\"enroll_user_count\",\r\n" + 
				"       \"show\":"+fales+",\r\n" + 
				"	},{\r\n" + 
				"		\"field\":\"assign_count\",\r\n" + 
				"       \"show\":"+fales+",\r\n" + 
				"	},{\r\n" + 
				"		\"field\":\"study_count\",\r\n" + 
				"       \"show\":"+fales+",\r\n" + 
				"	},{\r\n" + 
				"		\"field\":\"update_time\",\r\n" + 
				"       \"show\":"+fales+",\r\n" + 
				"	},{\r\n" + 
				"		\"field\":\"statusMap\",\r\n" + 
				"       \"show\":"+fales+",\r\n" + 
				"	},{\r\n" + 
				"		\"field\":\"operate\",\r\n" + 
				"       \"show\":"+fales+",\r\n" + 
				"		\"fixed\":\"right\"\r\n" + 
				"	}],\r\n" + 
				"    \"key\":\""+table_key+"\",\r\n" + 
				"    \"access_token\":\""+access_token+"\"\r\n" + 
				"}"
				).send().body();
	}
	
	/**
	 * 【知识库-考试资源-试卷】列表表头字段
	 * @param table_key
	 * @param fales
	 * @return
	 */
	public static String savaexampaper_tablehead(String table_key,Boolean fales) {
		
		return HttpRequest.post(custom_sava).query("access_token", access_token).data(
				"{\r\n" + 
				"	\"fields\":\r\n" + 
				"	[{\r\n" + 
				"		\"field\":\"title\",\r\n" + 
				"       \"show\":"+fales+"\r\n" + 
				"	},{\r\n" + 
				"		\"field\":\"paperTotal\",\r\n" + 
				"       \"show\":"+fales+"\r\n" + 
				"	},{\r\n" + 
				"		\"field\":\"totalScore\",\r\n" + 
				"       \"show\":"+fales+"\r\n" + 
				"	},{\r\n" + 
				"		\"field\":\"passingScore\",\r\n" + 
				"       \"show\":"+fales+"\r\n" + 
				"	},{\r\n" + 
				"		\"field\":\"createTimeLabel\",\r\n" + 
				"       \"show\":"+fales+"\r\n" + 
				"	},{\r\n" + 
				"		\"field\":\"visibleRange\",\r\n" + 
				"       \"show\":"+fales+"\r\n" + 
				"	},{\r\n" + 
				"		\"field\":\"creatorName\",\r\n" + 
				"       \"show\":"+fales+"\r\n" + 
				"	},{\r\n" + 
				"		\"field\":\"operate\",\r\n" + 
				"       \"show\":"+fales+"\r\n" + 
				"	}],\r\n" + 
				"    \"key\":\""+table_key+"\",\r\n" + 
				"    \"access_token\":\""+access_token+"\"\r\n" + 
				"}"
				).send().body();
	}
	
	
	/**
	 * 【培训-考试-考试任务】列表表头字段
	 * @param table_key
	 * @param fales
	 * @return
	 */
	public static String savaexamtask_tablehead(String table_key,Boolean fales) {
		
		
		return HttpRequest.post(custom_sava).query("access_token", access_token).data(
				"{\r\n" + 
				"    \"fields\":[\r\n" + 
				"        {\r\n" + 
				"            \"field\":\"title\",\r\n" + 
				"       	 \"show\":"+fales+",\r\n" + 
				"            \"fixed\":\"left\"\r\n" + 
				"        },\r\n" + 
				"        {\r\n" + 
				"            \"field\":\"classifyName\",\r\n" + 
				"       	 \"show\":"+fales+",\r\n" + 
				"            \"fixed\":null\r\n" + 
				"        },\r\n" + 
				"        {\r\n" + 
				"            \"field\":\"examNum\",\r\n" + 
				"       	 \"show\":"+fales+",\r\n" + 
				"            \"fixed\":null\r\n" + 
				"        },\r\n" + 
				"        {\r\n" + 
				"            \"field\":\"qualifiedNum\",\r\n" + 
				"       	 \"show\":"+fales+",\r\n" + 
				"            \"fixed\":null\r\n" + 
				"        },\r\n" + 
				"        {\r\n" + 
				"            \"field\":\"unqualifiedNum\",\r\n" + 
				"       	 \"show\":"+fales+",\r\n" + 
				"            \"fixed\":null\r\n" + 
				"        },\r\n" + 
				"        {\r\n" + 
				"            \"field\":\"unExamNum\",\r\n" + 
				"       	 \"show\":"+fales+",\r\n" + 
				"            \"fixed\":null\r\n" + 
				"        },\r\n" + 
				"        {\r\n" + 
				"            \"field\":\"qualifiedRate\",\r\n" + 
				"       	 \"show\":"+fales+",\r\n" + 
				"            \"fixed\":null\r\n" + 
				"        },\r\n" + 
				"        {\r\n" + 
				"            \"field\":\"joinRate\",\r\n" + 
				"       	 \"show\":"+fales+",\r\n" + 
				"            \"fixed\":null\r\n" + 
				"        },\r\n" + 
				"        {\r\n" + 
				"            \"field\":\"unExamRate\",\r\n" + 
				"       	 \"show\":"+fales+",\r\n" + 
				"            \"fixed\":null\r\n" + 
				"        },\r\n" + 
				"        {\r\n" + 
				"            \"field\":\"pendingCount\",\r\n" + 
				"       	 \"show\":"+fales+",\r\n" + 
				"            \"fixed\":null\r\n" + 
				"        },\r\n" + 
				"        {\r\n" + 
				"            \"field\":\"creatorName\",\r\n" + 
				"       	 \"show\":"+fales+",\r\n" + 
				"            \"fixed\":null\r\n" + 
				"        },\r\n" + 
				"        {\r\n" + 
				"            \"field\":\"createTime\",\r\n" + 
				"       	 \"show\":"+fales+",\r\n" + 
				"            \"fixed\":null\r\n" + 
				"        },\r\n" + 
				"        {\r\n" + 
				"            \"field\":\"beginTime\",\r\n" + 
				"       	 \"show\":"+fales+",\r\n" + 
				"            \"fixed\":null\r\n" + 
				"        },\r\n" + 
				"        {\r\n" + 
				"            \"field\":\"operate\",\r\n" + 
				"       	 \"show\":"+fales+",\r\n" + 
				"            \"fixed\":\"right\"\r\n" + 
				"        }\r\n" + 
				"    ],\r\n" + 
				"    \"key\":\""+table_key+"\",\r\n" + 
				"    \"access_token\":\""+access_token+"\"\r\n" + 
				"}"
				).send().body();
	}
	
	
	/**
	 * 【人才发展-练习-PK赛】列表表头字段
	 * @param table_key
	 * @param fales
	 * @return
	 */
	public static String savaPKmatch_tablehead(String table_key,Boolean fales) {
		
		return HttpRequest.post(custom_sava).query("access_token", access_token).data(
				"{\r\n" + 
				"    \"fields\":[\r\n" + 
				"        {\r\n" + 
				"            \"field\":\"title\",\r\n" + 
				"       	 \"show\":"+fales+"\r\n" + 
				"        },\r\n" + 
				"        {\r\n" + 
				"            \"field\":\"type\",\r\n" + 
				"       	 \"show\":"+fales+"\r\n" + 
				"        },\r\n" + 
				"        {\r\n" + 
				"            \"field\":\"question_count\",\r\n" + 
				"       	 \"show\":"+fales+"\r\n" + 
				"        },\r\n" + 
				"        {\r\n" + 
				"            \"field\":\"create_user\",\r\n" + 
				"       	 \"show\":"+fales+"\r\n" + 
				"        },\r\n" + 
				"        {\r\n" + 
				"            \"field\":\"create_time\",\r\n" + 
				"       	 \"show\":"+fales+"\r\n" + 
				"        },\r\n" + 
				"        {\r\n" + 
				"            \"field\":\"operate\",\r\n" + 
				"       	 \"show\":"+fales+"\r\n" + 
				"        }\r\n" + 
				"    ],\r\n" + 
				"    \"key\":\""+table_key+"\",\r\n" + 
				"    \"access_token\":\""+access_token+"\"\r\n" + 
				"}"
				).send().body();
	}
	
	/**
	 * 【人才发展-测评-测训任务】列表表头字段
	 * @param table_key
	 * @param fales
	 * @return
	 */
	public static String savaTraintask_tablehead(String table_key,Boolean fales) {
		
		return HttpRequest.post(custom_sava).query("access_token", access_token).data(
				"{\r\n" + 
				"    \"fields\":[\r\n" + 
				"        {\r\n" + 
				"            \"field\":\"title\",\r\n" + 
				"       	 \"show\":"+fales+"\r\n" + 
				"        },\r\n" + 
				"        {\r\n" + 
				"            \"field\":\"product_name\",\r\n" + 
				"       	 \"show\":"+fales+"\r\n" + 
				"        },\r\n" + 
				"        {\r\n" + 
				"            \"field\":\"finishedCondition\",\r\n" + 
				"       	 \"show\":"+fales+"\r\n" + 
				"        },\r\n" + 
				"        {\r\n" + 
				"            \"field\":\"create_user_name\",\r\n" + 
				"       	 \"show\":"+fales+"\r\n" + 
				"        },\r\n" + 
				"        {\r\n" + 
				"            \"field\":\"update_time\",\r\n" + 
				"       	 \"show\":"+fales+"\r\n" + 
				"        },\r\n" + 
				"        {\r\n" + 
				"            \"field\":\"operation\",\r\n" + 
				"       	 \"show\":"+fales+"\r\n" + 
				"        }\r\n" + 
				"    ],\r\n" + 
				"    \"key\":\""+table_key+"\",\r\n" + 
				"    \"access_token\":\""+access_token+"\"\r\n" + 
				"}"
				).send().body();
	}
	
	/**
	 * 【人才发展-认证-岗位认证】列表表头字段
	 * @param table_key
	 * @param fales
	 * @return
	 */
	
	public static String savaAuthen_tablehead(String table_key,Boolean fales) {
		
		return HttpRequest.post(custom_sava).query("access_token", access_token).data(
				"{\r\n" + 
				"    \"fields\":[\r\n" + 
				"        {\r\n" + 
				"            \"field\":\"title\",\r\n" + 
				"       	 \"show\":"+fales+"\r\n" + 
				"        },\r\n" + 
				"        {\r\n" + 
				"            \"field\":\"studyCount\",\r\n" + 
				"       	 \"show\":"+fales+"\r\n" + 
				"        },\r\n" + 
				"        {\r\n" + 
				"            \"field\":\"examCount\",\r\n" + 
				"       	 \"show\":"+fales+"\r\n" + 
				"        },\r\n" + 
				"        {\r\n" + 
				"            \"field\":\"operationCount\",\r\n" + 
				"       	 \"show\":"+fales+"\r\n" + 
				"        },\r\n" + 
				"        {\r\n" + 
				"            \"field\":\"pendingCount\",\r\n" + 
				"       	 \"show\":"+fales+"\r\n" + 
				"        },\r\n" + 
				"        {\r\n" + 
				"            \"field\":\"status\",\r\n" + 
				"       	 \"show\":"+fales+"\r\n" + 
				"        },\r\n" + 
				"        {\r\n" + 
				"            \"field\":\"createName\",\r\n" + 
				"       	 \"show\":"+fales+"\r\n" + 
				"        },\r\n" + 
				"        {\r\n" + 
				"            \"field\":\"updateTime\",\r\n" + 
				"       	 \"show\":"+fales+"\r\n" + 
				"        },\r\n" + 
				"        {\r\n" + 
				"            \"field\":\"qualificationUserCount\",\r\n" + 
				"       	 \"show\":"+fales+"\r\n" + 
				"        },\r\n" + 
				"        {\r\n" + 
				"            \"field\":\"operate\",\r\n" + 
				"       	 \"show\":"+fales+"\r\n" + 
				"        }\r\n" + 
				"    ],\r\n" + 
				"    \"key\":\""+table_key+"\",\r\n" + 
				"    \"access_token\":\""+access_token+"\"\r\n" + 
				"}"
				).send().body();
	}
	
	/**
	 * 【报表-排行-学分排行榜】列表表头字段
	 * @param table_key
	 * @param fales
	 * @return
	 */
	public static String savaCreditRank_tablehead(String table_key,Boolean fales) {
		
		return HttpRequest.post(custom_sava).query("access_token", access_token).data(
				"{\r\n" + 
				"    \"fields\":[\r\n" + 
				"        {\r\n" + 
				"            \"field\":\"rank\",\r\n" + 
				"       	 \"show\":"+fales+",\r\n" + 
				"            \"fixed\":\"left\"\r\n" + 
				"        },\r\n" + 
				"        {\r\n" + 
				"            \"field\":\"user_info_name\",\r\n" + 
				"       	 \"show\":"+fales+",\r\n" + 
				"            \"fixed\":\"left\"\r\n" + 
				"        },\r\n" + 
				"        {\r\n" + 
				"            \"field\":\"user_info_jobnumber\",\r\n" + 
				"       	 \"show\":"+fales+"\r\n" + 
				"        },\r\n" + 
				"        {\r\n" + 
				"            \"field\":\"user_info_deptName\",\r\n" + 
				"       	 \"show\":"+fales+"\r\n" + 
				"        },\r\n" + 
				"        {\r\n" + 
				"            \"field\":\"user_info_postName\",\r\n" + 
				"       	 \"show\":"+fales+"\r\n" + 
				"        },\r\n" + 
				"        {\r\n" + 
				"            \"field\":\"user_info_loginEmail\",\r\n" + 
				"       	 \"show\":"+fales+"\r\n" + 
				"        },\r\n" + 
				"        {\r\n" + 
				"            \"field\":\"user_info_joinTime\",\r\n" + 
				"       	 \"show\":"+fales+"\r\n" + 
				"        },\r\n" + 
				"        {\r\n" + 
				"            \"field\":\"user_info_column1\",\r\n" + 
				"       	 \"show\":"+fales+"\r\n" + 
				"        },\r\n" + 
				"        {\r\n" + 
				"            \"field\":\"user_info_column2\",\r\n" + 
				"       	 \"show\":"+fales+"\r\n" + 
				"        },\r\n" + 
				"        {\r\n" + 
				"            \"field\":\"user_info_column3\",\r\n" + 
				"       	 \"show\":"+fales+"\r\n" + 
				"        },\r\n" + 
				"        {\r\n" + 
				"            \"field\":\"user_info_column4\",\r\n" + 
				"       	 \"show\":"+fales+"\r\n" + 
				"        },\r\n" + 
				"        {\r\n" + 
				"            \"field\":\"score\",\r\n" + 
				"       	 \"show\":"+fales+",\r\n" + 
				"            \"fixed\":\"right\"\r\n" + 
				"        },\r\n" + 
				"        {\r\n" + 
				"            \"field\":\"operate\",\r\n" + 
				"       	 \"show\":"+fales+",\r\n" + 
				"            \"fixed\":\"right\"\r\n" + 
				"        }\r\n" + 
				"    ],\r\n" + 
				"    \"key\":\""+table_key+"\",\r\n" + 
				"    \"access_token\":\""+access_token+"\"\r\n" + 
				"}"
				).send().body();
	}
	
	
	
	public static String savaScore_tablehead(String table_key,Boolean fales) {
		 
		 HttpRequest.post(custom_sava).query("access_token", access_token).data("").send().body();
		 
		 return null;
	 }
	 
	 
	
	public static void main(String[] args) {
		
//		System.out.println(savaCreditRank_tablehead("c40f443e-c723-4ab1-8921-2f5433137f5f",true));
		String res = savaArchives_tablehead("c40f443e-c723-4ab1-8921-2f5433137f5f",true);
		Boolean show = (Boolean) JSONPath.read(res, "$.data.fields[0].show");
		System.out.println(show);
//		System.out.println(getcustomtrain_tablehead("c40f443e-c723-4ab1-8921-2f5433137f5f"));
//		String res = getUsersByRoleUserinfo("");
//		JSONObject uesrinfo = (JSONObject) JSONPath.read(res, "$.userList.list[0].user_info");
//		String id = ExaminationTaskBusiness.getIdByName(exam_task_name);
//		JSONObject uesrinfo = (JSONObject) JSONPath.read(res, "$.data"); 
//		System.out.println(uesrinfo);
		
	}

}
