package com.qa.junit.testcases;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;


public class TestResultLogger implements TestWatcher, AfterAllCallback{
	
	
	private static final Logger LOG = LoggerFactory.getLogger(TestResultLogger.class);
	private List<TestResultStatus> testResultsStatus = new ArrayList<>();
	public ExtentHtmlReporter htmlreporter;
	public ExtentTest test;
	
	ExtentReports extent = createReport();
	
    private enum TestResultStatus {
        SUCCESSFUL, ABORTED, FAILED, DISABLED;
    	    }
    
    
    @Override
    public void testDisabled(ExtensionContext context, Optional<String> reason) {
        LOG.info("Test Disabled for test {}: with reason :- {}", 
          context.getDisplayName(),
          reason.orElse("No reason"));

        testResultsStatus.add(TestResultStatus.DISABLED);
    }

    @Override
    public void testSuccessful(ExtensionContext context) {
        LOG.info("Test Successful for test {}: ", context.getDisplayName());
        testResultsStatus.add(TestResultStatus.SUCCESSFUL);    
        ExtentTest test = extent.createTest(context.getDisplayName(), "Test Passed");
        
        test.log(Status.PASS, "Test Passed");
        flushReports(extent, test);
        
    }  
    
    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
       LOG.info("Test Failed for test {}: ", context.getDisplayName());
       testResultsStatus.add(TestResultStatus.FAILED);
         ExtentTest test = extent.createTest(context.getDisplayName(), "Test Failed");
       
         test.log(Status.FAIL, "Failure trace Selenium: "+ cause.toString());
         flushReports(extent, test);
    	 
    }
    
    @Override
    public void testAborted(ExtensionContext context, Throwable cause) {
        LOG.info("Test Aborted for test {}: ", context.getDisplayName());

        testResultsStatus.add(TestResultStatus.ABORTED);
    }

   
    
    @Override
    public void afterAll(ExtensionContext context) throws Exception {
        Map<TestResultStatus, Long> summary = testResultsStatus.stream()
          .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        LOG.info("Test result summary for {} {}", context.getDisplayName(), summary.toString());
    }
    
    private ExtentReports createReport() {
        htmlreporter = new ExtentHtmlReporter("./reports/extent.html");
		htmlreporter.config().setEncoding("utf-8");
		htmlreporter.config().setDocumentTitle("Automation Reports");
		htmlreporter.config().setReportName("Automation Test Results");
		htmlreporter.config().setTheme(Theme.DARK);
		
		extent = new ExtentReports();
		extent.setSystemInfo("Browser", "FF");
		extent.attachReporter(htmlreporter);
		return extent;
    } 
    
    private void flushReports(ExtentReports extent, ExtentTest test){
    	
        extent.flush();
    }

}
