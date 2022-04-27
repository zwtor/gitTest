package newexam.examPlan;

import cn.kxy.authentication.business.CertificateBusiness;
import cn.kxy.base.business.EnterpriseData;
import cn.kxy.base.business.TokenData;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import com.lazy.assured.utils.RestAssuredRequestHandler;
import com.lazy.common.utils.ResourceFileUtil;

public class NewExamPlan {
    private final JSONObject newExamPlanURLObject = (new ResourceFileUtil()).parseJsonFile("url", "newExamPlanURL.json");
    private RestAssuredRequestHandler requestHandler;
    private ResourceFileUtil resourceFileUtil;
    private String requestBodyFolder = "requestbody/newExamPlan";

    public NewExamPlan() {
        requestHandler = new RestAssuredRequestHandler();
        resourceFileUtil = new ResourceFileUtil();
    }

    public String addDefaultNewExamPlan(String title, String status, JSONObject paperObject) {
        // set basic info
        String certificateResponse = CertificateBusiness.queryCertificateList("", "");
        String certificateId = JSONPath.read(certificateResponse, "$.list[0].id").toString();
        JSONObject requestBody = ResourceFileUtil.setJsonBodyValue(resourceFileUtil.parseJsonFile(requestBodyFolder, "addNewExamPlan.json"),
                "$.exam_certificate_id", certificateId,
                "$.status", status,
                "$.create_time", System.currentTimeMillis(),
                "$.enterprise_id", EnterpriseData.getEnterpriseId(),
                "$.title", title,
                "$.update_time", System.currentTimeMillis(),
                "$.access_token", TokenData.getMangerToken());
        // set exam paper info
        Double passLine = requestBody.getDouble("pass_line");
        requestBody = ResourceFileUtil.setJsonBodyValue(requestBody, "$.paper_id", paperObject.getJSONObject("data").getString("id"),
                "$.exam_paper_info.paper_name", paperObject.getJSONObject("data").getString("title"),
                "$.exam_paper_info.pass_score", paperObject.getJSONObject("data").getDouble("total_score") * passLine * 0.01,
                "$.exam_paper_info.total_score", paperObject.getJSONObject("data").getDouble("total_score"),
                "$.exam_paper_info.type", paperObject.getJSONObject("data").getString("type"));
        JSONArray questionList = paperObject.getJSONObject("data").getJSONArray("question_info");

        int fillBankCount = 0;
        int shortAnswerCount = 0;
        int singleCount = 0;
        for(Object questionObject : questionList) {
            JSONObject question = (JSONObject) questionObject;
            if(question.getString("type").equals("fillBlank")) {
                fillBankCount++;
            } else if(question.getString("type").equals("shortAnswer")) {
                shortAnswerCount++;
            } else if(question.getString("type").equals("single")) {
                singleCount++;
            }
        }
        requestBody = ResourceFileUtil.setJsonBodyValue(requestBody, "$.exam_paper_info.fill_blank_count", fillBankCount,
                "$.exam_paper_info.short_answer_count", shortAnswerCount);

        String addNewExamPlanURL = RestAssuredRequestHandler.buildURL(newExamPlanURLObject.getString("addNewExamPlan"), EnterpriseData.getEnterpriseId());
        return requestHandler.sendPostRequest(addNewExamPlanURL, requestBody);
    }

    public String getNewExamPlanList() {
        String getNewExamPlanListURL = RestAssuredRequestHandler.buildURL(newExamPlanURLObject.getString("getNewExamPlanList"), EnterpriseData.getEnterpriseId());
        return requestHandler.sendGetRequest(getNewExamPlanListURL,  "page_number", "1", "page_size", "20","scope","all","publish_status","published","exam_status","all");
    }

    public String copyNewExamPlan(String examPlanId) {
        String copyNewExamPlanURL = RestAssuredRequestHandler.buildURL(newExamPlanURLObject.getString("copyNewExamPlan"), EnterpriseData.getEnterpriseId(), examPlanId);
        JSONObject requestBody = ResourceFileUtil.setJsonBodyValue(resourceFileUtil.parseJsonFile(requestBodyFolder, "copyNewExamPlan.json"),
                "$.planId", examPlanId);
        return requestHandler.sendPostRequest(copyNewExamPlanURL, requestBody);
    }

    public String changeNewExamPlanStatus(String examPlanId, String status) {
        String changeStatusURL = RestAssuredRequestHandler.buildURL(newExamPlanURLObject.getString("changeStatus"), EnterpriseData.getEnterpriseId(), examPlanId);
        JSONObject requestBody = ResourceFileUtil.setJsonBodyValue(resourceFileUtil.parseJsonFile(requestBodyFolder, "changeNewExamPlanStatus.json"),
                "$.status", status);
        return requestHandler.sendPostRequest(changeStatusURL, requestBody);
    }

    public String deleteNewExamPlan(String examPlanId) {
        String changeStatusURL = RestAssuredRequestHandler.buildURL(newExamPlanURLObject.getString("deleteNewExamPlan"), EnterpriseData.getEnterpriseId(), examPlanId);
        JSONObject requestBody = ResourceFileUtil.setJsonBodyValue(resourceFileUtil.parseJsonFile(requestBodyFolder, "deleteNewExamPlan.json"));
        return requestHandler.sendPostRequest(changeStatusURL, requestBody);
    }

}
