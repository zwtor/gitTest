package newexam.paper;

import cn.kxy.base.business.EnterpriseData;
import cn.kxy.setting.bussiness.ClassificationBusines;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import com.lazy.assured.utils.RestAssuredRequestHandler;
import com.lazy.common.utils.ResourceFileUtil;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class NewExamPaper {
    private final JSONObject newExamURLObject = (new ResourceFileUtil()).parseJsonFile("url", "newExamPaperURL.json");
    private final String classificationId = ClassificationBusines.getPrimaryId();
    private final String classificationName = ClassificationBusines.getPrimaryName();
    private RestAssuredRequestHandler requestHandler;
    private ResourceFileUtil resourceFileUtil;

    private String requestBodyFolder = "requestbody/newExamPaper";

    public NewExamPaper() {
        requestHandler = new RestAssuredRequestHandler();
        resourceFileUtil = new ResourceFileUtil();
    }

    public String addNewPaper(String title) {
        String addNewPaperURL = RestAssuredRequestHandler.buildURL(newExamURLObject.getString("addNewPaper"), EnterpriseData.getEnterpriseId());
        JSONObject requestBody = ResourceFileUtil.setJsonBodyValue(resourceFileUtil.parseJsonFile(requestBodyFolder, "addNewExamPaper.json"),
                "$.title", title,
                "$.classify_id", classificationId,
                "$.classify_name", classificationName);
        return requestHandler.sendPostRequest(addNewPaperURL, requestBody);
    }

    public String getNewPaperDetail(String id) {
        String getNewPaperDetailURL = RestAssuredRequestHandler.buildURL(newExamURLObject.getString("getNewPaperDetail"), EnterpriseData.getEnterpriseId(), id);
        return requestHandler.sendGetRequest(getNewPaperDetailURL);
    }

    public String getNewPaperQuestionNumber(String id) {
        String getNewPaperQuestionNumberURL = RestAssuredRequestHandler.buildURL(newExamURLObject.getString("getNewPaperQuestionNumber"), EnterpriseData.getEnterpriseId(), id);
        return requestHandler.sendGetRequest(getNewPaperQuestionNumberURL);
    }

    public String getNewPaperList(Boolean onlySeeMe) {
        String getNewPaperListURL = RestAssuredRequestHandler.buildURL(newExamURLObject.getString("getNewPaperList"), EnterpriseData.getEnterpriseId());
        String visibleRange = onlySeeMe? "onlyMe" : "All";
        return requestHandler.sendGetRequest(getNewPaperListURL, "page_number", "1", "page_size", "10", "visible_range", visibleRange);
    }

    // get the first paper which contain fill blank or show answer question
    public String getPaperWithSubjectiveQuestion() {
        String examPaperListResponse = getNewPaperList(false);
        JSONArray paperList = JSONObject.parseArray(JSONPath.read(examPaperListResponse, "$.data.list").toString());
        for(Object object : paperList) {
            JSONObject paperObject = (JSONObject) object;
            String paperId = paperObject.getString("id");
            String questionResponse = getNewPaperQuestionNumber(paperId);
            if(Integer.parseInt(JSONPath.read(questionResponse, "$.data.fill_blank_count").toString()) > 0 ) {
                return questionResponse;
            }
        }
        return null;
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

    public String copyExamPaper(String paperId){
        String copyExamPaperURL= RestAssuredRequestHandler.buildURL(newExamURLObject.getString("copyPaper"),EnterpriseData.getEnterpriseId(),paperId);
        return requestHandler.sendPostRequest(copyExamPaperURL,null);
    }
}
