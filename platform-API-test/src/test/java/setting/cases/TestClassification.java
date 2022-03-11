package setting.cases;

import init.cases.InitExam;
import com.lazy.common.utils.CommonData;
import org.testng.annotations.Test;

public class TestClassification extends InitExam{
	public String name ="first" + CommonData.getStringRandom(5);
//	@BeforeClass
//	public void initDrag() {
//		if(ClassificationBusines.getPrimaryId()==null ) {
//			ClassificationBusines.addPrimaryClassification("01");
//			ClassificationBusines.addPrimaryClassification("02");
//		}
//	}
	
	@Test(description = "添加一级分类",priority = 1)
	public void TestAddPrimaryClassification() {
//		String res = ClassificationBusines.addPrimaryClassification(name);
//		String resname = ClassificationBusines.getPrimaryName();
//		Assert.assertEquals(name, resname,"添加一级分类返回的结果为："+res);
	}
	
	
	@Test(description = "添加二级分类",priority = 2)
	public void TestAddSecondaryClassification() {
	
//		String se_name = "second"+CommonData.getStringRandom(5);
//		String res = ClassificationBusines.addSecondaryClassification(se_name, ClassificationBusines.getPrimaryId());
//		String resname = ClassificationBusines.getSecondaryName();
//		Assert.assertEquals(se_name,resname,"添加二级分类返回的结果为："+res);
	}
	
	@Test(description = "查询分类" ,priority = 3)
	public void TestQueryClassification() {
//		String res = ClassificationBusines.queryClassification();
//		Assert.assertTrue(!name.isEmpty(),"查询分类返回的结果为："+res);
	}
	
	@Test(description = "修改一级分类",priority = 4)
	public void TestRenameClassification() {
		
//		String re_name = "ce"+CommonData.getStringRandom(6);
//		String res = ClassificationBusines.renameClassification(re_name,ClassificationBusines.getPrimaryId());
//		
//		Assert.assertTrue(re_name.equals(ClassificationBusines.getPrimaryName()),"修改一级分类的返回结果："+res);
	}
	@Test(description = "修改二级分类",priority = 5)
	public void TestRenameSecondaryClassification() {
//		String edit_name = "edit"+CommonData.getStringRandom(5);
//		String res = ClassificationBusines.renameClassification(edit_name,ClassificationBusines.getSecondaryId());
//		
//		Assert.assertTrue(edit_name.equals(ClassificationBusines.getSecondaryName()),"修改二级分类的返回结果："+res);
	}
	
	@Test(description = "验证未删除二级分类的时候，点击删除按钮的弹窗提示",priority = 6)
	public void TestisReferenceClassification() {
		
//		String res = ClassificationBusines.isReferenceClassification(ClassificationBusines.getPrimaryId());
//		
//		String msg = (String) JSONPath.read(res, "$.msg");
//		
//		Assert.assertTrue(msg.contains("请先解除关联再进行删除"),"删除一级分类的返回结果："+res);
	}
	
	@Test(description = "验证删除二级分类的弹窗提示",priority = 7)
	public void TestReferenceClassification() {
		
//		String res = ClassificationBusines.isReferenceClassification(ClassificationBusines.getSecondaryId());
//		
//		String msg = (String) JSONPath.read(res, "$.msg");
//		
//		Assert.assertEquals("是否要删除该分类",msg,"删除二级分类的返回结果："+res);
	}
	
	@Test(description = "验证逐级删除分类",priority = 8)
	public void TestDeleteClassification() {
		
//		String res01 = ClassificationBusines.deleteClassification(ClassificationBusines.getSecondaryId());
//		
//		String res02 = ClassificationBusines.deleteClassification(ClassificationBusines.getPrimaryId());
//		
//		String msg01 = (String) JSONPath.read(res01, "$.msg");
//		
//		String msg02 = (String) JSONPath.read(res01, "$.msg");
//		
//		Assert.assertEquals("分类删除成功",msg01,"删除二级分类的返回结果："+res01);
//		
//		Assert.assertEquals("分类删除成功",msg02,"删除一级分类的返回结果："+res02);
	}
	
//	@Test(description = "验证子父级分类互相拖曳",priority =10)
//	public void TestDragClassification(String parent_id, String base_id, String type,String exp) {
		
//		
//		String res = ClassificationBusines.dragClassification(ClassificationBusines.getPrimaryId(), parent_id, base_id, type);
//		
//		String msg = (String) JSONPath.read(res, "$.status");
//
//		Assert.assertEquals(exp,msg,"分类拖曳的返回结果："+res);
//	}
//	@DataProvider(name = "DragClassification")
//	public Object[][] DataProviders() {
//		Object[][] obj = new Object[][] { { ClassificationBusines.getPrimaryId(),  ClassificationBusines.getPrimarySecondId(),"inner","sucess"},
//			{ ClassificationBusines.getPrimaryId(), ClassificationBusines.getPrimarySecondId() ,"before","sucess"}, 
//			{ ClassificationBusines.getPrimaryId(), ClassificationBusines.getPrimarySecondId() ,"after","sucess"},
//			 };
//		return obj;
//	}

}
