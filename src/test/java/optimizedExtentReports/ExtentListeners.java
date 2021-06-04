package optimizedExtentReports;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import utilities.ScreenshotUtils;

public class ExtentListeners implements ITestListener {
	
	static Date d = new Date();
	static String filename = (d.toString().replace(":", "_").replace(" ", "_")+".html");
	
	private static ExtentReports extent = ExtentManager.CreateInstance(System.getProperty("user.dir")+ "\\target\\report\\"+ filename);
	public static ExtentTest test;

	public void onTestStart(ITestResult result) {
		test = extent.createTest(result.getTestClass().getName() + ":" + result.getMethod().getMethodName());
		
	}

	public void onTestSuccess(ITestResult result) {
		// to get Method name
		
		String methodName = result.getMethod().getMethodName();
		String text = "<B> Test Case: "+ methodName.toUpperCase()+ " passed";
		
		// to create label
		Markup m = MarkupHelper.createLabel(text, ExtentColor.GREEN);
		
		test.log(Status.PASS, m);
		
	}

	public void onTestFailure(ITestResult result) {
		// to Capture full error message in the report
					String errorMessage = Arrays.toString(result.getThrowable().getStackTrace());
					test.fail(errorMessage);
					
					test.log(Status.FAIL, "Test got failed");
					
					// to capture screenshot from system memory
					
					
						
						try {
							ScreenshotUtils.screenShot();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
				
					
					// to attach screenshot with failed message
					try {
						
						String screenshot = ScreenshotUtils.filename;
						test.fail("<b><font color = 'red'>Capture screeshot of failed method</font></b><br>", MediaEntityBuilder.createScreenCaptureFromPath(screenshot).build());
					} catch (IOException e) {
						// TODO Auto-generated catch block
						System.out.println(e.getMessage());
					}
					
					// to get Method name
					
					String methodName = result.getMethod().getMethodName();
					String text = "<B> Test Case: "+ methodName.toUpperCase()+ " Failed";
					
					// to create label
					Markup m = MarkupHelper.createLabel(text, ExtentColor.RED);
					test.log(Status.FAIL, m);
		
	}

	public void onTestSkipped(ITestResult result) {
		// to get Method name
		
		String methodName = result.getMethod().getMethodName();
		String text = "<B> Test Case: "+ methodName.toUpperCase()+ " Skipped";
		
		// to create label
		Markup m = MarkupHelper.createLabel(text, ExtentColor.YELLOW);
		test.log(Status.SKIP, m);
		
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	public void onFinish(ITestContext context) {
		extent.flush();    // this is mandatory because without this extent report will not  work
		
	}

}
