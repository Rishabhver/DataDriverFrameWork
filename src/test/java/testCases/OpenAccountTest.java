package testCases;

import org.openqa.selenium.Alert;
import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import utilities.DataProviderUtil;

public class OpenAccountTest extends BaseTest {
	
	@Test(dataProviderClass=DataProviderUtil.class, dataProvider="dp" )
	public void OpenAccount(String customer, String currency)

	{
		
		click("BankManager_OpenAccount_Add_XPATH");
		select("BankManager_OpenAccount_customerDropdown_CSS", customer);
		select("BankManager_OpenAccount_currencyDropdown_CSS", currency);
		click("BankManager_OpenAccount_process_XPATH");
		
		
		Alert alert = driver.switchTo().alert();
		Assert.assertTrue(alert.getText().contains("Account created successfully with account Number"), "customer added successfully");
		
		
		alert.accept();
		
		
		
		
		
	}
}
