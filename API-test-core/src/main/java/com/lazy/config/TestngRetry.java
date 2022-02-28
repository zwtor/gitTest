/**  
 * All rights Reserved, Designed By www.tydic.com
 * @Title:  sd.java   
 * @Package cn.common   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: 天源迪科技     
 * @date:   2018年2月3日 下午4:17:03   
 * @version V1.0 
 * @Copyright: 2018 www.tydic.com Inc. All rights reserved. 
 * 注意：本内容仅限于深圳天源迪科信息技术股份有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
package com.lazy.config;

import org.apache.log4j.Logger;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
import org.testng.Reporter;

/**
 * 
 * @ClassName:  TestngRetry   
 * @Description:TODO(重写testNg监听的方法)   
 * @author:  zhanglun
 * @date:   2018年5月8日 上午9:35:15   
 *     
 */
public class TestngRetry implements IRetryAnalyzer {
	    private static Logger logger = Logger.getLogger(TestngRetry.class);
	    private static int maxRetryCount = 2;
	    private int retryCount = 1;

	    public boolean retry(ITestResult result) {
	        if (retryCount <= maxRetryCount) {
	        	
	            String message = "Running retry for '" + result.getName()
	                    + "' on class " + this.getClass().getName() + " Retrying "
	                    + retryCount + " times";
	            logger.info(message);
	            
	            Reporter.setCurrentTestResult(result);
	            
	            logger.info("RunCount=" + (retryCount + 1));
	            
	            retryCount++;
	            return true;
	        }
	        return false;
	    }
	}

 
