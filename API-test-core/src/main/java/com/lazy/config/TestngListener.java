/**  
 * All rights Reserved, Designed By www.tydic.com
 * @Title:  dsa.java   
 * @Package cn.common   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: 天源迪科技     
 * @date:   2018年2月3日 下午4:28:47   
 * @version V1.0 
 * @Copyright: 2018 www.tydic.com Inc. All rights reserved. 
 * 注意：本内容仅限于深圳天源迪科信息技术股份有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
package com.lazy.config;

import com.lazy.common.utils.FilePath;
import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import java.util.Iterator;
/**
 * 
 * @ClassName:  TestngListener   
 * @Description:TODO(重写testNg监听的方法)   
 * @author:  zhanglun
 *     
 */
public class TestngListener extends TestListenerAdapter {
	private static Logger logger = Logger.getLogger(TestngListener.class);

	@Override
	public void onTestFailure(ITestResult tr) {
		
		super.onTestFailure(tr);
		try {
		} catch (Exception e) {
			logger.info("执行此用例失败，详情请看截屏信息,访问路径："+FilePath.getScreenShotDirectory());
		}
		logger.info("The test task is Failure,For more details, please read the logs..");
	}

	@Override
	public void onTestSkipped(ITestResult tr) {
		
		super.onTestSkipped(tr);
		
		logger.info("The test task is Skipped...");
	}

	@Override
	public void onTestSuccess(ITestResult tr) {
		
		super.onTestSuccess(tr);
		
		logger.info(" The test task  finished successfully...");
	}

	@Override
	public void onTestStart(ITestResult tr) {
		
		super.onTestStart(tr);
		
		logger.info("Task successfully started...");
	}
	
	/**
	 * @see TestListenerAdapter#onFinish(ITestContext)
	 *重写onFinsh方法，优化报告显示的结果
	 */
	@Override
	public void onFinish(ITestContext context) {
		 Iterator<ITestResult> listOfFailedTests = context.getFailedTests().getAllResults().iterator();
		 
		 Iterator<ITestResult> listOfSkippedTests = context.getSkippedTests().getAllResults().iterator();
		 
	        while (listOfFailedTests.hasNext()) {
	        	
	            ITestResult failedTest = listOfFailedTests.next();
	            ITestNGMethod method = failedTest.getMethod();
	            
	            if (context.getFailedTests().getResults(method).size() > 1 ) {
	            	
	                listOfFailedTests.remove();
	            } else {
	            	
	                if (context.getPassedTests().getResults(method).size() > 0) {
	                	
	                    listOfFailedTests.remove();
	                }
	            }
	           
	        }
	        
	        while (listOfSkippedTests.hasNext()) {
	        	
	            ITestResult failedTest = listOfSkippedTests.next();
	            ITestNGMethod method = failedTest.getMethod();
	            
	            if (context.getSkippedTests().getResults(method).size()>1) {
	            	
	                listOfSkippedTests.remove();
	                
	            } else {
	                if (context.getPassedTests().getResults(method).size() > 0) {
	                	
	                    listOfSkippedTests.remove();
	                }
	            }
	           
	        }
	}
}