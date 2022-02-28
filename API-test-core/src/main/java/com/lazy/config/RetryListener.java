/**  
 * All rights Reserved, Designed By www.tydic.com
 * @Title:  sd.java   
 * @Package cn.common   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: 天源迪科技     
 * @date:   2018年2月3日 下午4:20:29   
 * @version V1.0 
 * @Copyright: 2018 www.tydic.com Inc. All rights reserved. 
 * 注意：本内容仅限于深圳天源迪科信息技术股份有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
package com.lazy.config;

import org.testng.IAnnotationTransformer;
import org.testng.IRetryAnalyzer;
import org.testng.annotations.ITestAnnotation;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
/**
 * 
 * @ClassName:  RetryListener   
 * @Description:TODO(重写testNg监听的方法)   
 * @author:  zhanglun
 * @date:   2018年5月8日 上午9:34:59   
 *     
 */
public class RetryListener implements IAnnotationTransformer {
	    @SuppressWarnings("rawtypes")
		public void transform(ITestAnnotation annotation, Class testClass,
	            Constructor testConstructor, Method testMethod) {
	    	
	        IRetryAnalyzer retry = annotation.getRetryAnalyzer();
	        
	        if (retry == null) {
	        	
	            annotation.setRetryAnalyzer(TestngRetry.class);
	        }
	    }
	}
