package testCases;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.BaseTest;
import utilities.DataProviderUtil;
import utilities.ExcelReader;

public class TestCase1 extends BaseTest {
	
	/*
	 * @Test public void login() throws InterruptedException {
	 * type("facebook_username_ID","rishab4verma@gmail.com");
	 * type("facebook_password_ID","xyz");
	 * 
	 * 
	 * }
	 * 
	 */
	
	  @Test(dataProviderClass = DataProviderUtil.class, dataProvider="dp")
	  public void secondTest(String username) {
	  
	  type("google_username_XPATH", username); 
	  click("google_click_XPATH");
	  
	  
	  }
	 
	
	

}
