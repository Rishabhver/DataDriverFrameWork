package base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.Status;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;


import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.Config;
import optimizedExtentReports.ExtentListeners;
import utilities.DbManager1;
import utilities.ExcelReader;



public class BaseTest {
	
	// TO Import libraries   --> ctrl+shift+o
	
	/*
	 * It will be the base class for
	 * WebDriver
	 * TestNg
	 * Screenshot
	 * log4j
	 * properties
	 * Keyword
	 * DataBase
	 * JavamMail
	 * Extent
	 * Listeners
	 * Excel
	 */

	public static WebDriver driver;
	//public static Logger log = Logger.getLogger(BaseTest.class.getName());
	public static Logger log = org.apache.log4j.Logger.getLogger(BaseTest.class.getName());
	public static Properties OR = new Properties();
	public static Properties config = new Properties();
	public static FileInputStream fis ;
	public static ExcelReader excel = new ExcelReader("C:\\Users\\lenovo\\eclipse-workspace\\DataDrivernFramework\\src\\test\\resources\\excel\\TestData.xlsx");
	public static WebDriverWait wait;
	public static WebElement dropdown;
	
	
	// These are the keywords : like we will use keyword Click() to perform future click operations
	public static void click(String key)
	{
		if(key.endsWith("_XPATH"))
		{
		driver.findElement(By.xpath(OR.getProperty(key))).click();
		}else if(key.endsWith("_CSS"))
		{
		driver.findElement(By.cssSelector(OR.getProperty(key))).click();
		}else if(key.endsWith("_ID"))
		{
		driver.findElement(By.id(OR.getProperty(key))).click();
		}
		
		System.out.println("Element is clicked");
		
	log.info("element is clicked --->" + key);
	
	// to add logs in listeners ---> 
	ExtentListeners.test.log(Status.INFO, "element is clicked --->" + key );
	}
	
	
	
	public static void type(String key, String value)
	{
		if(key.endsWith("_XPATH"))
		{
		driver.findElement(By.xpath(OR.getProperty(key))).sendKeys(value);
		}else if(key.endsWith("_CSS"))
		{
		driver.findElement(By.cssSelector(OR.getProperty(key))).sendKeys(value);
		}
		else if(key.endsWith("_ID"))
		{
		driver.findElement(By.id(OR.getProperty(key))).sendKeys(value);
		}
		
		log.info("typinf in an elemnt-->" +key +" and value is -->" +value);
		ExtentListeners.test.log(Status.INFO, "typinf in an elemnt-->" +key );
	}
	
	public static boolean isElementPresent(String key)
	{
		try {
		if(key.endsWith("_XPATH "))
		{
			driver.findElement(By.xpath(OR.getProperty(key)));
			
			
		}else if(key.endsWith("_CSS "))
		{
			driver.findElement(By.cssSelector(OR.getProperty(key)));
			
		}
		log.info("Finding Element" +key);
		
		// to add logs in listeners ---> 
		ExtentListeners.test.log(Status.INFO, "finding element --->" + key );
		return true;
		
		}
		catch(Throwable t)
		{
			log.info("Element not fount" + "Error  message is -->" + t.getMessage());
			
			// to add logs in listeners ---> 
			ExtentListeners.test.log(Status.INFO, "Element not fount" + "Error  message is -->" );
			return false;
		}
	}
		
	public static void select(String key ,String value)
	{
		if(key.endsWith("_XPATH"))
		{
		dropdown = driver.findElement(By.xpath(OR.getProperty(key)));
		}else if(key.endsWith("_CSS"))
		{
			dropdown = driver.findElement(By.cssSelector(OR.getProperty(key)));
		}else if(key.endsWith("_ID"))
		{
			dropdown = driver.findElement(By.id(OR.getProperty(key)));
		}
		
	     Select select = new Select(dropdown);
	     select.selectByVisibleText(value);
		
	log.info("element is Selected--->" + key);
	
	// to add logs in listeners ---> 
	ExtentListeners.test.log(Status.INFO, "element is selected --->" + key + value);
	}
		
		
		
	
	
	
	@BeforeSuite
	public void setUp() throws SQLException
	{
		
		if(driver==null)
		{
			PropertyConfigurator.configure("C:\\Users\\lenovo\\eclipse-workspace\\DataDrivernFramework\\src\\test\\resources\\properties\\log4j.properties");
			try {
				fis = new FileInputStream("C:\\Users\\lenovo\\eclipse-workspace\\DataDrivernFramework\\src\\test\\resources\\properties\\OR.properties");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				OR.load(fis);
				log.info("OR file is loaded");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try {
				fis = new FileInputStream("C:\\Users\\lenovo\\eclipse-workspace\\DataDrivernFramework\\src\\test\\resources\\properties\\Config.properties");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				config.load(fis);
				log.info("Config file is loaded!!!!!");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if(config.getProperty("browser").equals("chrome"))
			{
				WebDriverManager.chromedriver().setup();
				driver= new ChromeDriver();
				log.info("Chrome browser launched");
			}else if(config.getProperty("browser").equals("firefox"))
			{
				WebDriverManager.firefoxdriver().setup();
				driver= new FirefoxDriver();
				log.info("Firefox browser launched");
			}
			
			driver.get(config.getProperty("testsiteurl"));
			log.info("Navigating to  URl" + config.getProperty("testsiteurl"));
			DbManager1.setMySqlDbConnection();
			log.info("Db connection Established");
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Integer.parseInt(config.getProperty("implicit.wait")), TimeUnit.SECONDS);
			// for explicit wait
			wait = new WebDriverWait(driver, Integer.parseInt(config.getProperty("explicit.wait")));
			
		}
		
		
		
	}
	
	
	
	/*
	 * @AfterSuite public void tearDown() { driver.quit();
	 * log.info("Test Execution completed"); }
	 */
	 
	
}
