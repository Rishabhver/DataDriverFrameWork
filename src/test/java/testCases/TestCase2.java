package testCases;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import utilities.DataProviderUtil;

public class TestCase2 {
	
	
	@Test(dataProviderClass  =DataProviderUtil.class, dataProvider="dp" )
	public void LoginTest(String username, String password)
	{
		System.out.println(username+"----"+password);
	}

}
