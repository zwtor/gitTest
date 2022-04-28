package newexam.examPlan;

import cn.kxy.authentication.business.CertificateBusiness;
import cn.kxy.base.business.EnterpriseData;
import cn.kxy.base.business.TokenData;
import cn.kxy.base.business.UserProfile;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import com.lazy.assured.utils.RestAssuredRequestHandler;
import com.lazy.common.utils.ResourceFileUtil;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class NewExamPlan {
    private final JSONObject newExamPlanURLObject = (new ResourceFileUtil()).parseJsonFile("url", "newExamPlanURL.json");
    private RestAssuredRequestHandler requestHandler;
    private ResourceFileUtil resourceFileUtil;
    private UserProfile userProfile;
    private String requestBodyFolder = "requestbody/newExamPlan";

    public NewExamPlan() {
        requestHandler = new RestAssuredRequestHandler();
        resourceFileUtil = new ResourceFileUtil();
        userProfile = new UserProfile();
    }

    public String addDefaultNewExamPlan(String title, String markType, String status, JSONObject paperQuestionObject) {
        // set basic info
        String certificateResponse = CertificateBusiness.queryCertificateList("", "");
        String certificateId = JSONPath.read(certificateResponse, "$.list[0].id").toString();
        JSONObject requestBody = ResourceFileUtil.setJsonBodyValue(resourceFileUtil.parseJsonFile(requestBodyFolder, "addNewExamPlan.json"),
                "$.paper_id", paperQuestionObject.getJSONObject("data").getString("id"),
                "$.mark_type", markType,
                "$.exam_certificate_id", certificateId,
                "$.status", status,
                "$.create_time", System.currentTimeMillis(),
                "$.enterprise_id", EnterpriseData.getEnterpriseId(),
                "$.title", title,
                "$.update_time", System.currentTimeMillis(),
                "$.access_token", TokenData.getMangerToken());
        Boolean fillBank = paperQuestionObject.getJSONObject("data").getInteger("fill_blank_count") > 0;
        Boolean shorAnswer = paperQuestionObject.getJSONObject("data").getInteger("short_answer_count") > 0;
        requestBody = ResourceFileUtil.setJsonBodyValue(requestBody, "$.data.mark_question_type.fill_blank", fillBank,
                "$.data.mark_question_type.short_answer", shorAnswer);

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

    public String assignStudent(String examPlanId, String timeType) {
        JSONObject requestBody = resourceFileUtil.parseJsonFile(requestBodyFolder, "assignStudent.json");
        if(timeType.equals("timeFrame")) {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:sss");
            Date date = new Date();
            try {
                date = dateFormat.parse("2024-01-01 00:00:00:00");
            } catch (Exception e) {
                e.printStackTrace();
            }
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            long beginTimeStamp = calendar.getTimeInMillis();

            try {
                date = dateFormat.parse("2024-01-05 00:00:00:00");
            } catch (Exception e) {
                e.printStackTrace();
            }
            calendar = Calendar.getInstance();
            calendar.setTime(date);
            long endTimeStamp = calendar.getTimeInMillis();

            requestBody = ResourceFileUtil.setJsonBodyValue(requestBody, "$.assign_config.time_type", timeType,
                    "$.assign_config.begin_time", beginTimeStamp,
                    "$.assign_config.end_time", endTimeStamp,
                    "$.assign_config.interval", "");

        } else if(timeType.equals("timeInterval")) {
            requestBody = ResourceFileUtil.setJsonBodyValue(requestBody, "$.assign_config.time_type", timeType,
                    "$.assign_config.begin_time", "",
                    "$.assign_config.end_time", "",
                    "$.assign_config.interval", 5);
        }
        String userId = JSONPath.read(userProfile.getUserProfile(), "$.data.id").toString();
        requestBody = ResourceFileUtil.setJsonBodyValue(requestBody, "$.assign_object.user_list[0]", userId,
                "access_token", TokenData.getMangerToken());
        String assignStudentURl = RestAssuredRequestHandler.buildURL(newExamPlanURLObject.getString("assignStudent"), EnterpriseData.getEnterpriseId(), examPlanId);
        return requestHandler.sendPostRequest(assignStudentURl, requestBody);
    }

    public String deleteNewExamPlan(String examPlanId) {
        String changeStatusURL = RestAssuredRequestHandler.buildURL(newExamPlanURLObject.getString("deleteNewExamPlan"), EnterpriseData.getEnterpriseId(), examPlanId);
        JSONObject requestBody = ResourceFileUtil.setJsonBodyValue(resourceFileUtil.parseJsonFile(requestBodyFolder, "deleteNewExamPlan.json"));
        return requestHandler.sendPostRequest(changeStatusURL, requestBody);
    }

}
