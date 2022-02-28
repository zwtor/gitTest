package com.lazy.config;

import org.testng.Reporter;

public class ResultCount {

	public static int [] getResultCount() {
		int [] counts=new int [3];
		
		
		
		
		
		counts[0]=Reporter.getCurrentTestResult().getTestContext().getPassedTests().size();
		
		counts[1]=Reporter.getCurrentTestResult().getTestContext().getFailedTests().size();
		
		counts[2]=Reporter.getCurrentTestResult().getTestContext().getSkippedTests().size();
		return counts;
	}
	
}
