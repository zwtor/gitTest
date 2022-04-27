package exam.cases;

import com.alibaba.fastjson.JSONPath;
import newexam.paper.NewExamPaper;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TestCopyExamPaper {
    private NewExamPaper newExamPaper;
    private String newPaperId;
    private String copyPaperId;

    @BeforeClass
    public void setUp(){
        newExamPaper=new NewExamPaper();
        newPaperId = JSONPath.read(newExamPaper.getNewPaperList(true),"$.data.list[0].id").toString();

    }

    @Test(description = "复制试卷", priority = 1)
    public void testCopyExamPaper(){
        String response = newExamPaper.copyExamPaper(newPaperId);
        copyPaperId=JSONPath.read(response,"$.data").toString();
        String success=JSONPath.read(response,"$.success").toString();
        Assert.assertEquals(success,"true");
        System.out.println("SUCCESS!");
    }

    @Test(description = "删除复制的试卷",dependsOnMethods="testCopyExamPaper",priority = 2)
    public void testDeleteExamPaper(){
        String res=newExamPaper.deleteNewPaper(copyPaperId);
        String data=JSONPath.read(res,"$.data").toString();
        Assert.assertEquals(data,"success");
    }

}
