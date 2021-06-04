package utilities;

import java.lang.reflect.Method;

import org.testng.annotations.DataProvider;

import base.BaseTest;

public class DataProviderUtil extends BaseTest{
	
	
	@DataProvider(name="dp")
	public static Object[][] getData(Method m)
	{
	
		String sheetName = m.getName(); 

		int rowCount = excel.getRowCount(sheetName);  // excel is th object reference of class excelReader define in base class
		System.out.println("rowcount is"+ rowCount);
		int colCount = excel.getColumnCount(sheetName);
		System.out.println("column count is" + colCount);
		Object[][] data = new Object[rowCount-1][colCount];
		
		for(int rows = 2;  rows<=rowCount; rows++)
		{
			for(int cols = 0; cols<colCount; cols++)
			{
				// data[0][0] = excel.getCellData(sheetName, 0, 2);
				data[rows-2][cols] = excel.getCellData(sheetName, cols, rows);
				
			}
			
		
			
		}
		
		return data;
		
	}

}
