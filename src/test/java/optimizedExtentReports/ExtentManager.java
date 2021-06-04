package optimizedExtentReports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager {
	
	private static ExtentReports extent;
	
	public static ExtentReports CreateInstance(String filename)
	{
		ExtentSparkReporter htmlReporter = new ExtentSparkReporter(filename);
		
		htmlReporter.config().setEncoding("utf-8");
		htmlReporter.config().setDocumentTitle("Risahbh Verma automation report");
		htmlReporter.config().setReportName("Test Results");
		htmlReporter.config().setTheme(Theme.STANDARD);
		
		
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Tester name", "Rishabh Verma");
		extent.setSystemInfo("organization", "HCL");
		extent.setSystemInfo("Build number", "MZT-164");
		
		return extent;
	}
	

}
