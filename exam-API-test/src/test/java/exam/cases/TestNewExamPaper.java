package exam.cases;

import cn.kxy.examination.business.NewExamPaper;
import com.alibaba.fastjson.JSONPath;
import com.lazy.common.utils.CommonData;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TestNewExamPaper {
    private NewExamPaper newExamPaper;
    private String testTitle;
    private String paperID;

    @BeforeClass
    public void setUp() {
        newExamPaper = new NewExamPaper();
        testTitle = "Automation" + CommonData.getStringRandom(3);
    }

    @Test
    public void testAddNewExamPaper() {
        String response = newExamPaper.addNewPaper(testTitle);
        paperID = JSONPath.read(response, "$.data").toString();
        Assert.assertEquals("true", JSONPath.read(response,"$.success").toString());
        System.out.println(response);
    }

    @Test
    public void testGetNewExamPaperList() {
        String response = newExamPaper.getNewPaperList();
        Integer count = Integer.valueOf(JSONPath.read(response,"$.data.total").toString());
        Assert.assertTrue(count > 0);
        Assert.assertTrue(JSONPath.read(response,"$.data.list[0].title").toString().startsWith("Automation"), String.format("result of get exam list is: %s", response));
    }
}
