package testCases;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import base.BaseTest;
import utilities.DataProviderUtil;

public class TestCase3 extends BaseTest {
	
	@Test(dataProviderClass  =DataProviderUtil.class, dataProvider="dp" )
	public void thirdTest(String name, String number, String email, String city, String id, String password)
	{
		type("Way2Aut_name_XPATH", name);
		type("Way2Aut_number_XPATH", number);
		type("Way2Aut_email_XPATH ", email);
		
		type("Way2Aut_City_XPATH ", city);
		type("Way2Aut_ID_XPATH ",id);
		type("Way2Aut_pwd_XPATH ",password);
		click("Way2Aut_submit_XPATH");
		
	}

}
