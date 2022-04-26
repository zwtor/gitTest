package newexam.question;

import cn.kxy.base.business.EnterpriseData;
import cn.kxy.setting.bussiness.ClassificationBusines;

import java.io.File;

import com.alibaba.fastjson.JSONObject;
import com.lazy.assured.utils.RestAssuredRequestHandler;
import com.lazy.common.utils.ResourceFileUtil;

public class NewQuestion {
	private final JSONObject newExamURLObject = (new ResourceFileUtil()).parseAllJsonFiles("url");
    private final String classificationId = ClassificationBusines.getPrimaryId();
    private RestAssuredRequestHandler requestHandler;
    private ResourceFileUtil resourceFileUtil;
    
    private String requestBodyFolder = "requestbody" + File.separator + "newExamQuestion";

    public NewQuestion() {
    	requestHandler = new RestAssuredRequestHandler();
        resourceFileUtil = new ResourceFileUtil();
    }
    
    public String addNewQuestionBank(String title) {
        String addNewQuestionBankURL = RestAssuredRequestHandler.buildURL(newExamURLObject.getString("addNewQuestionBank"), EnterpriseData.getEnterpriseId());
        JSONObject requestBody = ResourceFileUtil.setJsonBodyValue(resourceFileUtil.parseJsonFile(requestBodyFolder, "addNewExamQuestionBank.json"),
                "$.title", title,
                "$.classify_id", classificationId);
        return requestHandler.sendPostRequest(addNewQuestionBankURL, requestBody);
    }

    public String getQuestionBankList() {
        String getQuestionBankListURL = RestAssuredRequestHandler.buildURL(newExamURLObject.getString("getQuestionBankList"), EnterpriseData.getEnterpriseId());
        return requestHandler.sendGetRequest(getQuestionBankListURL, "status", "enable", "page_number", "1", "page_size", "15", "only_see_me", "true");
    }

    public String getQuestionList(String questionBankId) {
        String getQuestionListURL = RestAssuredRequestHandler.buildURL(newExamURLObject.getString("getQuestionList"), EnterpriseData.getEnterpriseId());
        return requestHandler.sendGetRequest(getQuestionListURL, "question_bank_ids", questionBankId,
                "difficulty", "",
                "keyword", "",
                "visible_range", "",
                "type", "",
                "page_size", "10",
                "page_number", "1");
    }
    
    public String deleteNewQuestionBank(String id) {
        String deleteNewQuestionBankURL = RestAssuredRequestHandler.buildURL(newExamURLObject.getString("deleteNewQuestionBank"), EnterpriseData.getEnterpriseId(), id);
        return requestHandler.sendPostRequest(deleteNewQuestionBankURL, null);
    }
}
