package e2e.resources;

import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import e2e.pageObjects.AbstractComponents;

public class ExtentReportsNG extends AbstractComponents{
	
	ExtentReportsNG(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	public static ExtentReports extentReports() {
		String reportsPath = System.getProperty("user.dir") + "\\ExtentReports\\TestReport_"+returnCurrentDateTime()+".html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(reportsPath);
		reporter.config().setDocumentTitle("AutomationTestResults");
		reporter.config().setReportName("Test Report");
		
		ExtentReports extent = new ExtentReports();
		extent.attachReporter(reporter);
		return extent;
		
	}
	
	

}
