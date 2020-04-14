package com.crm.qa.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.crm.qa.base.Testbase;

public class TestutillForwaits extends Testbase{

// we can keep this in properties file as well
	public static long PAGE_LOAD_TIMEOUT = 30; // global and public variable
	public static long IMPLICIT_WAIT = 30; // global and public variable 
// creating common method to swith to framae	
	public void SwitchToFrame() {
		driver.switchTo().frame("mainpanel");
	}
	
//reusable xls_reader method
	
		static Workbook book;
		static Sheet sheet;
		
		public static String TESTDATA_SHEET_PATH = "C:\\Users\\avsub\\eclipse-workspace\\FreeCRMtest\\src\\main\\java"
				+ "\\com\\crm\\qa\\testdata\\CRMtestData.xls";

		
		public static Object[][] getTestData(String sheetName) throws InvalidFormatException
		{
			
			FileInputStream file= null;
			try
			{
				file= new FileInputStream(TESTDATA_SHEET_PATH);
			}
			catch(FileNotFoundException e)
			{
				e.printStackTrace();
			}
			try {
				book = WorkbookFactory.create(file);
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}
			sheet = book.getSheet(sheetName);
			
			Object[][] inputData= new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
			for(int i=0; i<sheet.getLastRowNum();i++)
			{
				for( int j=0; j<sheet.getRow(0).getLastCellNum();j++)
				{
					inputData[i][j]= sheet.getRow(i+1).getCell(j).toString();
				}
			}
			return inputData;
			
					
		}

		public static void takeScreenshotAtEndOfTest() throws IOException {
			File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			String currentDir = System.getProperty("user.dir");

			FileUtils.copyFile(scrFile, new File(currentDir + "/screenshots/" + System.currentTimeMillis() + ".png"));

			
}
}
		
