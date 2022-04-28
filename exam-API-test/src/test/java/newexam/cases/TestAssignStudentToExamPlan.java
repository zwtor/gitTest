package newexam.cases;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import com.lazy.common.utils.CommonData;
import newexam.examPlan.NewExamPlan;
import newexam.paper.NewExamPaper;
import newexam.usercenter.UserExamTask;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

//考试任务添加学员（固定时间段、固定周期）
public class TestAssignStudentToExamPlan {
    private NewExamPlan newExamPlan;
    private NewExamPaper newExamPaper;
    private UserExamTask userExamTask;
    private List<String> examPlanIdList;

    @BeforeClass
    public void setUp() {
        newExamPlan = new NewExamPlan();
        newExamPaper = new NewExamPaper();
        userExamTask = new UserExamTask();
        examPlanIdList = new ArrayList<>();
    }

    @DataProvider
    public Object[][] simpleDataProvider() {
        Object[][] provider = new Object[][] {{"timeFrame"}, {"timeInterval"}};
        return provider;
    }

    @Test(description = "assign student to new exam plan", dataProvider = "simpleDataProvider", priority = 1)
    public void testAssignStudentToExam(String timeType) {
        String examPaperListResponse = newExamPaper.getNewPaperList(false);
        String examPaperId = JSONPath.read(examPaperListResponse, "$.data.list[0].id").toString();
        String paperQuestionResponse = newExamPaper.getNewPaperQuestionNumber(examPaperId);
        JSONObject paperQuestionObject = JSONObject.parseObject(paperQuestionResponse);

        // add and publish new exam plan
        String examTitle = "Automation" + CommonData.getStringRandom(3);
        String response = newExamPlan.addDefaultNewExamPlan(examTitle, "system", "published", paperQuestionObject);
        String examPlanId = JSONPath.read(response, "$.data.id").toString();
        Assert.assertEquals(JSONPath.read(response, "$.success").toString(), "true");
        examPlanIdList.add(examPlanId);

        // assign student
        response = newExamPlan.assignStudent(examPlanId, timeType);
        Assert.assertEquals(JSONPath.read(response, "$.success").toString(), "true");
        String taskId = JSONPath.read(response, "$.data.id").toString();
        String taskTitle = JSONPath.read(response, "$.data.title").toString();

        // verify exam task in user center
        String examTaskListResponse = userExamTask.getExamTaskList("", "unfinished");
        Assert.assertEquals(taskId, JSONPath.read(examTaskListResponse, "$.data.task_list.list[0].id"));
        Assert.assertEquals(taskTitle, JSONPath.read(examTaskListResponse, "$.data.task_list.list[0].title"));
    }

    @AfterClass
    public void tearDown() {
        // delete new added exam plan
        for(String examPlanId : examPlanIdList) {
            String response = newExamPlan.deleteNewExamPlan(examPlanId);
            Assert.assertEquals(JSONPath.read(response, "$.data.status").toString(), "deleted");
            Assert.assertEquals(JSONPath.read(response, "$.success").toString(), "true");
        }
    }
}
