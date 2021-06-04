package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import utilities.DataProviderUtil;

public class Bank_Customer_Login extends BaseTest {
	
	@Test
	public void Login()
	{
		click("BankManager_CSS");
		Assert.assertTrue(isElementPresent("BankManager_AddCustomer_CSS"), "Bank Manager not logged in");
	}
	
	

}
