package utilities;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;

import base.BaseTest;

public class ScreenshotUtils extends BaseTest {
	
	public static String filename;
	
	
	public static void screenShot() throws IOException
	{
		// for unique file name
		Date d = new Date();
		 filename = (d.toString().replace(":", "_").replace(" ", "_")+".jpeg");
		
		File screenshotsource = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE); 
		File ScreenshotDestination = new File(System.getProperty("user.dir")+ "\\target\\report\\"+ filename);
		FileUtils.copyFile(screenshotsource,ScreenshotDestination );
	}
	
	public static void screenShotForWebElement(WebElement element) throws IOException
	{
		// for unique file name
		Date d = new Date();
		 filename = (d.toString().replace(":", "_").replace(" ", "_")+".jpeg");
		
		File screenshotsource = ((TakesScreenshot) element).getScreenshotAs(OutputType.FILE); 
		File ScreenshotDestination = new File(System.getProperty("user.dir")+ "\\ reports\\" + filename);
		FileUtils.copyFile(screenshotsource,ScreenshotDestination );
	}

}
