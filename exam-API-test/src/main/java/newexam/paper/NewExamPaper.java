package newexam.paper;

import cn.kxy.base.business.EnterpriseData;
import cn.kxy.setting.bussiness.ClassificationBusines;
import com.alibaba.fastjson.JSONObject;
import com.lazy.assured.utils.RestAssuredRequestHandler;
import com.lazy.common.utils.ResourceFileUtil;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class NewExamPaper {
    private final JSONObject newExamURLObject = (new ResourceFileUtil()).parseAllJsonFiles("url");
    private final String classificationId = ClassificationBusines.getPrimaryId();
    private final String classificationName = ClassificationBusines.getPrimaryName();
    private RestAssuredRequestHandler requestHandler;
    private ResourceFileUtil resourceFileUtil;

    private String requestBodyFolder = "requestbody" + File.separator + "newExamPaper";

    public NewExamPaper() {
        requestHandler = new RestAssuredRequestHandler();
        resourceFileUtil = new ResourceFileUtil();
    }

    public String addNewPaper(String title) {
        String addNewPaperURL = RestAssuredRequestHandler.buildURL(newExamURLObject.getString("addNewPaper"), EnterpriseData.getEnterpriseId());
        JSONObject requestBody = RestAssuredRequestHandler.setJsonBodyValue(resourceFileUtil.parseJsonFile(requestBodyFolder, "addNewExamPaper.json"),
                "$.title", title,
                "$.classify_id", classificationId,
                "$.classify_name", classificationName);
        return requestHandler.sendPostRequest(addNewPaperURL, requestBody);
    }

    public String getNewPaperDetail(String id) {
        String getNewPaperDetailURL = RestAssuredRequestHandler.buildURL(newExamURLObject.getString("getNewPaperDetail"), EnterpriseData.getEnterpriseId(), id);
        return requestHandler.sendGetRequest(getNewPaperDetailURL);
    }

    public String getNewPaperList() {
        String getNewPaperListURL = RestAssuredRequestHandler.buildURL(newExamURLObject.getString("getNewPaperList"), EnterpriseData.getEnterpriseId());
        return requestHandler.sendGetRequest(getNewPaperListURL, "page_number", "1", "page_size", "10", "visible_range", "onlyMe");
    }

    public String saveFixedPaper(String paperId, List<JSONObject> questionList) {
        JSONObject requestBody = resourceFileUtil.parseJsonFile(requestBodyFolder, "saveFixedPaper.json");
        requestBody.put("paper_question_vos", questionList);
        String saveFixedPaperURL = RestAssuredRequestHandler.buildURL(newExamURLObject.getString("saveFixedPaper"), EnterpriseData.getEnterpriseId(), paperId);
        return requestHandler.sendPostRequest(saveFixedPaperURL, requestBody);
    }

    public String updatePaperStatus(String id, String status) {
        String updatePaperStatusURL = RestAssuredRequestHandler.buildURL(newExamURLObject.getString("updatePaperStatus"), EnterpriseData.getEnterpriseId(), id);
        Map<String, String> queryParameterMap = new LinkedHashMap<String, String>() {{
            put("paperId", id);
            put("status", status);
        }};
        return requestHandler.sendPostRequest(updatePaperStatusURL,  null, queryParameterMap, null);
    }

    public String deleteNewPaper(String id) {
        String deleteNewPaperURL = RestAssuredRequestHandler.buildURL(newExamURLObject.getString("deleteNewPaper"), EnterpriseData.getEnterpriseId(), id);
        return requestHandler.sendPostRequest(deleteNewPaperURL, null);
    }
}
