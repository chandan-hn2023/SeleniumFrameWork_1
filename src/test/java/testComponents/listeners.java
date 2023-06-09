package testComponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import resources.ExtentReporterNG;

public class listeners extends baseTest implements ITestListener { //ITestListener - Is an interface provided TestNG
	
	
//Right click(on the Listeners class ) -> go to source-> click on overide/implement methods -> select the check boxes for the ITest listener
	
	ExtentTest test;
	ExtentReports extent = ExtentReporterNG.getReporterObject();
	
	//To overcome Concurreny problem(While rinning parallel tests) we use thread
	ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();
	
	
	@Override
	public void onTestStart(ITestResult result) {
		
		test = extent.createTest(result.getMethod().getMethodName()); //result.getMethod().getMethodName() - Test case name
		
		//Pushing the "test" object into Thread Local
		extentTest.set(test); //ThreadLocal will assign unique thread ID to "test"
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		
		extentTest.get().log(Status.PASS, "The test is passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		
		//test.fail(result.getThrowable());
		extentTest.get().fail(result.getThrowable());
		
		//To get the driver information
		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (Exception e1) {
			
			e1.printStackTrace();
		} 
		
		//Take the screenshot
		String filePath = null;
		try {
			filePath = getScreenShot(result.getMethod().getMethodName(),driver); //result.getMethod().getMethodName() - Test case name
		} catch (IOException e) {
			
			e.printStackTrace();
		} 
		
		//Attaching screenshot into the report
		extentTest.get().addScreenCaptureFromPath(filePath, result.getMethod().getMethodName()); //result.getMethod().getMethodName() - Test case name
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestSkipped(result);
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedWithTimeout(result);
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		ITestListener.super.onStart(context);
	}

	@Override
	public void onFinish(ITestContext context) {
		extent.flush();
	} 
	
 
	
	

}
