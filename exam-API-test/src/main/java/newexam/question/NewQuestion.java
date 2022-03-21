package newexam.question;

import cn.kxy.base.business.EnterpriseData;
import com.alibaba.fastjson.JSONObject;
import com.lazy.assured.utils.RestAssuredRequestHandler;
import com.lazy.common.utils.ResourceFileUtil;

public class NewQuestion {
    private final JSONObject newExamURLObject = (new ResourceFileUtil()).parseAllJsonFiles("url");
    private RestAssuredRequestHandler requestHandler;

    public NewQuestion() {
        requestHandler = new RestAssuredRequestHandler();
    }

    public String getQuestionBankList() {
        String getQuestionBankListURL = RestAssuredRequestHandler.buildURL(newExamURLObject.getString("getQuestionBankList"), EnterpriseData.getEnterpriseId());
        return requestHandler.sendGetRequest(getQuestionBankListURL, "status", "enable", "page_number", "1", "page_size", "15");
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
}
