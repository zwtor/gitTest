package exam.cases;

import com.lazy.common.utils.CommonData;
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
public class TestNewExamQuestion {
    private NewQuestion newQuestion;
    private ResourceFileUtil resourceFileUtil;
    private String questionId;
    private String questionBankTitle;
    

    @BeforeClass
    public void setUp() {
        newQuestion = new NewQuestion();
        resourceFileUtil = new ResourceFileUtil();
        questionBankTitle = "questionBankAutomation" + CommonData.getStringRandom(3);
        
    }

//    @DataProvider
//    public Object[][] simpleDataProvider() {
////        Object[][] provider = new Object[][] {{"Automation1"}, {"Automation2"}};
//        Object[][] provider = new Object[2][1];
//        provider[0][0] = "Automation1";
//        provider[1][0] = "Automation2";
//        return provider;
//    }
//
//    @DataProvider
//    public Object[][] excelDataProvider(Method method) {
//        Object[][] testData = DataDrivenLoader.loadExcelData("TestNewExamPaperData.xlsx", method.getDeclaringClass().getName(), method.getName());
//        return testData;
//    }
    
    

    @Test(description = "add new exam question bank", priority = 1)
    public void testAddNewExamQuestionBank(){
        String response = newQuestion.addNewQuestionBank(questionBankTitle);
        questionId = JSONPath.read(response, "$.data").toString();
        Assert.assertEquals("true", JSONPath.read(response,"$.success").toString());
        System.out.println("questionBank Title: " + questionBankTitle);
        System.out.println("questionId: " + questionId);
    }

    

    @Test(description = "delete new created exam question bank",dependsOnMethods ="testAddNewExamQuestionBank",priority = 2)
    public void testDeleteNewExamQuestionBank() {
        String response = newQuestion.deleteNewQuestionBank(questionId);
        Assert.assertEquals("true", JSONPath.read(response, "$.success"));
    }
    
    
}
