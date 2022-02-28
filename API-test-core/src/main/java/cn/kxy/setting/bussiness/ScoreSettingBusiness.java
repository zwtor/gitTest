package cn.kxy.setting.bussiness;

import cn.kxy.base.business.EnterpriseDataUrl;
import cn.kxy.base.business.TokenData;
import com.lazy.assured.utils.GetRequestTools;
import com.lazy.assured.utils.PostRequestTools;
public class ScoreSettingBusiness {
	public static String token = TokenData.getMangerToken();
	
	public static String enterpriseUrl= EnterpriseDataUrl.getEnterpriseUrl();
	
	public static String addScoreRuleUrl=enterpriseUrl  + "score/addScoreRule";
	public static String getScoreRuleUrl=enterpriseUrl  + "score/getScoreRule";
	
	//设置分数规格
	public static String addScoreRule(String courseScoreList,String studyScore,String examScore) {
		return PostRequestTools.RequestFormDataByPost(token, "courseScoreList", courseScoreList,
				"studyScore",studyScore,"examScore",examScore,addScoreRuleUrl);
	}
	//设置分数规格，供考试模块使用
	public static String addScoreRule() {
		return addScoreRule("[{\"type\":1,\"use\":true,\"score\":2,\"maxScore\":30}]",
				"[{\"finishScore\":5,\"unFinishScore\":0,\"maxScore\":500,\"use\":false}]",
				"[{\"passScore\":6,\"unPassScore\":4,\"missScore\":2,\"maxScore\":500,\"use\":false}]");
	}
	//得到设置的学分
	public static String getScoreRule() {
		return GetRequestTools.RequestParamsByGet("access_token", token, getScoreRuleUrl);
	}
	
}
