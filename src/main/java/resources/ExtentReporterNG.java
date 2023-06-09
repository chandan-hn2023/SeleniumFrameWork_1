package resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {
	
	public static ExtentReports getReporterObject()
	{
		String path =System.getProperty("user.dir")+"\\reports\\index.html";

		ExtentSparkReporter reporter = new ExtentSparkReporter(path);

		//We can modify the report name
		reporter.config().setReportName("Web Automation Results");

		//We can modify Document title as well(The name in browser tab)
		reporter.config().setDocumentTitle("Test Results");

		//Reporter object now have some details
		//Creating main class object and attaching Reporter details to the new class
		ExtentReports extent =new ExtentReports(); //ExtentReports extentReport = new ExtentReports();

		extent.attachReporter(reporter); //Attaching the report to the main class

		extent.setSystemInfo("Tester", "Chandan HN");
		
		return extent;
	}

}
