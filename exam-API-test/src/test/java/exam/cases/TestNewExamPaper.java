package exam.cases;

import com.lazy.common.utils.DataDrivenLoader;
import newexam.paper.NewExamPaper;
import newexam.question.NewQuestion;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import com.lazy.common.utils.ResourceFileUtil;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

@Test(groups = {"CI"})
public class TestNewExamPaper {
    private NewExamPaper newExamPaper;
    private NewQuestion newQuestion;
    private ResourceFileUtil resourceFileUtil;
    private String paperTitle;
    private String paperId;

    @BeforeClass
    public void setUp() {
        newExamPaper = new NewExamPaper();
        newQuestion = new NewQuestion();
        resourceFileUtil = new ResourceFileUtil();
//        paperTitle = "Automation" + CommonData.getStringRandom(3);
    }

    @DataProvider
    public Object[][] simpleDataProvider() {
//        Object[][] provider = new Object[][] {{"Automation1"}, {"Automation2"}};
        Object[][] provider = new Object[2][1];
        provider[0][0] = "Automation1";
        provider[1][0] = "Automation2";
        return provider;
    }

    @DataProvider
    public Object[][] excelDataProvider(Method method) {
        Object[][] testData = DataDrivenLoader.loadExcelData("TestNewExamPaperData.xlsx", method.getDeclaringClass().getName(), method.getName());
        return testData;
    }

    @Test(description = "add new exam paper", dataProvider = "excelDataProvider", priority = 1)
    public void testAddNewExamPaper(String paperTitle, String test) {
        String response = newExamPaper.addNewPaper(paperTitle);
        paperId = JSONPath.read(response, "$.data").toString();
        Assert.assertEquals("true", JSONPath.read(response,"$.success").toString());
        System.out.println("paper title: " + paperTitle);
        System.out.println(test);
        System.out.println("paper ID: " + paperId);
        DataDrivenLoader.updateTestData("TestNewExamPaperData.xlsx", "exam.cases.TestNewExamPaper", "testGetNewExamPaper", "paperId", paperId);
        DataDrivenLoader.updateTestData("TestNewExamPaperData.xlsx", "exam.cases.TestNewExamPaper", "testDeleteNewExamPaper", "paperId", paperId);
    }

    @Test(description = "get new exam paper detail", dataProvider = "excelDataProvider", dependsOnMethods = "testAddNewExamPaper", priority = 2)
    public void testGetNewExamPaper(String paperTitle, String paperId) {
        String response = newExamPaper.getNewPaperDetail(paperId);
        Assert.assertEquals(paperId, JSONPath.read(response, "$.data.id").toString());
        Assert.assertEquals(paperTitle, JSONPath.read(response, "$.data.title").toString());
        System.out.println("-------in testGetNewExamPaper method---------");
        System.out.println(paperId);
    }

    @Test(description = "get new exam papers list created by me", dependsOnMethods ="testAddNewExamPaper", priority = 3)
    public void testGetNewExamPaperList() {
        String response = newExamPaper.getNewPaperList();
        Integer count = Integer.valueOf(JSONPath.read(response,"$.data.total").toString());
        Assert.assertTrue(count > 0);
        Assert.assertTrue(JSONPath.read(response,"$.data.list[0].title").toString().startsWith("Automation"), String.format("result of get exam list is: %s", response));
    }

    @Test(description = "add question for exam paper", dependsOnMethods ="testAddNewExamPaper", priority = 4)
    public void testAddQuestionToPaper() {
        List<Integer> scoreList = new ArrayList<Integer>() {{
            add(10);
            add(20);
        }};
        List<Integer> missedScoreList = new ArrayList<Integer>() {{
            add(0);
            add(0);
        }};
        List<Integer> orderIdList = new ArrayList<Integer>() {{
            add(1);
            add(2);
        }};
        JSONObject bodyTemplate = resourceFileUtil.parseJsonFile("requestbody" + File.separator + "newExamPaper", "saveFixedPaper.json");
        JSONObject questionTemplate = (JSONObject) bodyTemplate.getJSONArray("paper_question_vos").get(0);
        List<JSONObject> sendQuestionList = new ArrayList<>();

        String response = newQuestion.getQuestionBankList();
        String questionBankId = JSONPath.read(response, "$.data.list[0].id").toString();
        response = newQuestion.getQuestionList(questionBankId);
        JSONArray receivedQuestionList = JSONArray.parseArray(JSONPath.read(response, "$.data.list").toString());
        for(int i = 0;i <scoreList.size();i++) {
            JSONObject singleQuestionObject = (JSONObject) questionTemplate.clone();
            String questionId = ((JSONObject) receivedQuestionList.get(i)).getString("id");
            singleQuestionObject.put("question_id", questionId);
            singleQuestionObject.put("score", scoreList.get(i));
            singleQuestionObject.put("missed_score", missedScoreList.get(i));
            singleQuestionObject.put("order_id", orderIdList.get(i));
            sendQuestionList.add(singleQuestionObject);
        }

        response = newExamPaper.saveFixedPaper(paperId, sendQuestionList);
        Assert.assertEquals("success", JSONPath.read(response, "$.data"));
    }

    @Test(description = "disable and enable new exam paper", dependsOnMethods = {"testAddNewExamPaper", "testAddQuestionToPaper"}, priority = 5)
    public void testUpdateExamPaperStatus() {
        String testStatus1 = "disable";
        String testStatus2 = "enable";

        String response = newExamPaper.updatePaperStatus(paperId, testStatus1);
        Assert.assertEquals("success", JSONPath.read(response, "$.data"));
        response = newExamPaper.getNewPaperDetail(paperId);
        Assert.assertEquals(testStatus1, JSONPath.read(response, "$.data.status"));

        response = newExamPaper.updatePaperStatus(paperId, testStatus2);
        Assert.assertEquals("success", JSONPath.read(response, "$.data"));
        response = newExamPaper.getNewPaperDetail(paperId);
        Assert.assertEquals(testStatus2, JSONPath.read(response, "$.data.status"));
    }

    @Test(description = "delete new created exam paper", dataProvider = "excelDataProvider", dependsOnMethods = "testAddNewExamPaper", priority = 6)
    public void testDeleteNewExamPaper(String paperId) {
        String response = newExamPaper.deleteNewPaper(paperId);
        Assert.assertEquals("success", JSONPath.read(response, "$.data"));
    }
}
