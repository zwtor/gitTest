package cn.kxy.authentication.business;

import cn.kxy.base.business.TokenData;
import cn.kxy.base.business.UserProfile;
import cn.kxy.examination.business.ExaminationBusines;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import com.lazy.assured.utils.RestAssuredRequestHandler;
import com.lazy.common.utils.ResourceFileUtil;

import java.io.File;
import java.util.List;

public class PositionAuthentication {
    private final JSONObject positionAuthenticationURLObject = (new ResourceFileUtil()).parseJsonFile("url", "positionAuthentication.json");
    private RestAssuredRequestHandler requestHandler;
    private ResourceFileUtil resourceFileUtil;

    private String requestBodyFolder = "requestbody/positionAuthentication";

    public PositionAuthentication() {
        requestHandler = new RestAssuredRequestHandler();
        resourceFileUtil = new ResourceFileUtil();
    }

    public String createAuthentication (String title, List stageList) {
        String createAuthenticationURL = RestAssuredRequestHandler.buildURL(positionAuthenticationURLObject.getString("createAuthentication"));
        JSONObject requestBody = resourceFileUtil.parseJsonFile(requestBodyFolder, "createAuthentication.json");
        requestBody.put("title", title);
        requestBody.put("stageJson", stageList);

        UserProfile userProfile = new UserProfile();
        String userProfileResponse = userProfile.getUserProfile();
        String userId = JSONPath.read(userProfileResponse, "$.data.id").toString();
        String userName = JSONPath.read(userProfileResponse, "$.data.name").toString();
        String userType = JSONPath.read(userProfileResponse, "$.data.type").toString();
        requestBody = ResourceFileUtil.setJsonBodyValue(requestBody, "$.scopes.user_list[0].id", userId,
                "$.scopes.user_list[0].name", userName,
                "$.scopes.user_list[0].type", userType,
                "$.access_token", TokenData.getMangerToken());

        return requestHandler.sendPostRequest(createAuthenticationURL, requestBody);
    }

    public JSONObject createCourseStageInAuthentication(int stageSort, String courseId) {
        JSONObject courseObject = resourceFileUtil.parseJsonFile(requestBodyFolder, "courseStageInAuthentication.json");
        courseObject = ResourceFileUtil.setJsonBodyValue(courseObject, "$.stageSort", stageSort,
                "$.stageName", "阶段" + stageSort,
                "$.course[0].courseId", courseId);
        return courseObject;
    }

    public JSONObject createRandomPaperStageInAuthentication(int stageSort, String proportion1, String proportion2) {
        JSONObject examObject = resourceFileUtil.parseJsonFile(requestBodyFolder, "randomPaperStageInAuthentication.json");
        String questionBankResponse = ExaminationBusines.queryQuestionBankList("", "", "");
        String questionBank1 = JSONPath.read(questionBankResponse, "$.data.list[0].id").toString();
        String questionBank2 = JSONPath.read(questionBankResponse, "$.data.list[2].id").toString();

        examObject = ResourceFileUtil.setJsonBodyValue(examObject, "$.stageSort", stageSort,
                "$.stageName", "阶段" + String.valueOf(stageSort),
                "$.course[0].questionBankList", String.format("%s,%s", questionBank1, questionBank2),
                "$.course[0].proportional[0].proportion", proportion1,
                "$.course[0].proportional[1].proportion", proportion2);
        return examObject;
    }

}
