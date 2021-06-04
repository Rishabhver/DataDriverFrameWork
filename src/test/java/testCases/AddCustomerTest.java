package testCases;

import static org.testng.Assert.fail;

import org.openqa.selenium.Alert;
import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import utilities.DataProviderUtil;

public class AddCustomerTest extends BaseTest {
	
	@Test(dataProviderClass=DataProviderUtil.class, dataProvider="dp" )
	public void addCustomer(String firstname, String lastname, String postCode)

	{
		
		click("BankManager_AddCustomer_CSS");
		type("BankManager_AddCustomer_FirstName_CSS", firstname);
		type("BankManager_AddCustomer_LastName_CSS", lastname);
		type("BankManager_AddCustomer_Pin_Code_CSS", postCode);
		click("BankManager_AddCustomer_Add_XPATH");
		Alert alert = driver.switchTo().alert();
		Assert.assertTrue(alert.getText().contains("Customer added successfully with customer id"), "customer added successfully");
		alert.accept();
		
		
		Assert.fail();
		
	}
}
