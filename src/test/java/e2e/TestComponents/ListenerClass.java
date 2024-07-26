package e2e.TestComponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import e2e.resources.ExtentReportsNG;

public class ListenerClass extends BaseTest implements ITestListener {
	
	WebDriver driver;
	ExtentReports extent = ExtentReportsNG.extentReports();
	ThreadLocal<ExtentTest> safeTest = new ThreadLocal<ExtentTest>();
	ExtentTest test;
	String screenShotPath;
	
	

	public void onTestStart(ITestResult result) {
		test = extent.createTest(result.getMethod().getMethodName());
		safeTest.set(test);
	}

	public void onTestSuccess(ITestResult result) {
		safeTest.get().pass(result.getMethod().getMethodName());
	}

	public void onTestFailure(ITestResult result) {
		safeTest.get().fail(result.getMethod().getMethodName());
		safeTest.get().log(Status.FAIL, result.getThrowable());
		
		
		try {
			driver = (WebDriver)result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (Exception e) {
			e.printStackTrace();
		} 
		try {
			screenShotPath = getscreenShotPath(driver, result.getMethod().getMethodName());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		safeTest.get().addScreenCaptureFromPath(screenShotPath);
		
	}

	public void onTestSkipped(ITestResult result) {
		// not implemented
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// not implemented
	}

	public void onTestFailedWithTimeout(ITestResult result) {
		onTestFailure(result);
	}

	public void onStart(ITestContext context) {
		// not implemented
	}

	public void onFinish(ITestContext context) {
		extent.flush();
	}

}
