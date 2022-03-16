package cn.kxy.examination.business;

import cn.kxy.base.business.EnterpriseData;
import cn.kxy.setting.bussiness.ClassificationBusines;
import com.alibaba.fastjson.JSONObject;
import com.lazy.assured.utils.RestAssuredRequestHandler;
import com.lazy.common.utils.ResourceFileUtil;

public class NewExamPaper {
    private final JSONObject newExamURLObject = (new ResourceFileUtil()).parseAllJsonFiles("url");
    private final String classificationId = ClassificationBusines.getPrimaryId();
    private final String classificationName = ClassificationBusines.getPrimaryName();
    private RestAssuredRequestHandler requestHandler;
    private ResourceFileUtil resourceFileUtil;

    public NewExamPaper() {
        requestHandler = new RestAssuredRequestHandler();
        resourceFileUtil = new ResourceFileUtil();
    }

    public String addNewPaper(String title) {
        String addNewPaperURL = RestAssuredRequestHandler.buildURL(newExamURLObject.getString("addNewPaper"), EnterpriseData.getEnterpriseId());
        JSONObject requestBody = RestAssuredRequestHandler.setJsonBodyValue(resourceFileUtil.parseJsonFile("requestbody", "addNewExamPaper.json"),
                "$.title", title,
                "$.classify_id", classificationId,
                "$.classify_name", classificationName);
        return requestHandler.sendPostRequest(addNewPaperURL, requestBody);
    }

    public String getNewPaperList() {
        String getNewPaperListURL = RestAssuredRequestHandler.buildURL(newExamURLObject.getString("getNewPaperList"), EnterpriseData.getEnterpriseId());
        return requestHandler.sendGetRequest(getNewPaperListURL, "page_number", "1", "page_size", "10", "visible_range", "onlyMe");
    }
}
