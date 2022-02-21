package DriverFactory;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import CommonFunctionLibrary.Functionlibrary;
import Utilities.Excelfileutil;

public class Driverscript {
	public static WebDriver driver;
//  add extent report
public static ExtentReports report;
public static ExtentTest logger;

public static Excelfileutil excel;

public  void startTest() throws Throwable
{
// creating object for constructor
 excel=new Excelfileutil();
//module sheet
for(int i=1;i<=Excelfileutil.rowcount("mastertestcases");i++)
{
	
	// defining modulestatus 
	String Modulestatus ="";
	
	
	if(excel.getdata("mastertestcases",i,2).equalsIgnoreCase("y"))
	{
		String TCmodule = excel.getdata("mastertestcases",i,1);
		// generate extent report
		report = new ExtentReports("./Reports/"+TCmodule+".html"+"_"+Functionlibrary.generateDate());
		logger = report.startTest(TCmodule);
	    int rowcount =	excel.rowcount(TCmodule);
		for(int j=1;j<=rowcount;j++)
		{
		String DESCRIPTION = excel.getdata(TCmodule,j,0);
		String OBJECT_TYPE =excel.getdata(TCmodule,j,1);
		String LOCATOR_TYPE =excel.getdata(TCmodule,j,2);
		String LOCATOR_VALUE = excel.getdata(TCmodule,j,3);
		String TEST_DATA =excel.getdata(TCmodule,j,4);
		//int test_data=excel.getdata(TCmodule,j,5);
		try{
		if(OBJECT_TYPE.equalsIgnoreCase("startbrowser"))
		{
		driver = Functionlibrary.startbrowser(driver);
		logger.log(LogStatus.INFO, DESCRIPTION);
		}
		if(OBJECT_TYPE.equalsIgnoreCase("openapplication"))
		{
			Functionlibrary.openapplication(driver);	
			logger.log(LogStatus.INFO, DESCRIPTION);
		}
		if(OBJECT_TYPE.equalsIgnoreCase("clickaction"))
		{
			Functionlibrary.clickaction(driver, LOCATOR_TYPE, LOCATOR_VALUE);
			logger.log(LogStatus.INFO, DESCRIPTION);
		}
		if(OBJECT_TYPE.equalsIgnoreCase("typeaction"))
		{
			Functionlibrary.typeaction(driver, LOCATOR_TYPE, LOCATOR_VALUE, TEST_DATA);
			logger.log(LogStatus.INFO, DESCRIPTION);
		}
		if(OBJECT_TYPE.equalsIgnoreCase("waiting"))
		{
		Functionlibrary.waiting(driver, LOCATOR_TYPE, LOCATOR_VALUE);
		logger.log(LogStatus.INFO, DESCRIPTION);
		}
		if(OBJECT_TYPE.equalsIgnoreCase("closeapplication"))
		{
		Functionlibrary.closeapplication(driver);
		logger.log(LogStatus.INFO, DESCRIPTION);
		}
		if(OBJECT_TYPE.equalsIgnoreCase("titlevalidation"))
		{
		Functionlibrary.titlevalidation(driver,TEST_DATA);
		logger.log(LogStatus.INFO, DESCRIPTION);
		}
		if(OBJECT_TYPE.equalsIgnoreCase("dropdownaction"))
		{
			Functionlibrary.dropdownaction(driver, LOCATOR_TYPE, LOCATOR_VALUE, TEST_DATA);
			logger.log(LogStatus.INFO, DESCRIPTION);
		}
		
		if(OBJECT_TYPE.equalsIgnoreCase("Scrolldownaction"))
		{
			Functionlibrary.Scrolldownaction(driver);
			logger.log(LogStatus.INFO, DESCRIPTION);
		}
		
		
		
		Excelfileutil.setData(TCmodule, j, 5,"passed");
		Modulestatus = "true";
		logger.log(LogStatus.PASS, DESCRIPTION+"passed");
		
		/*//Take screenshot
		File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(src,new File("./Screenshot/"+DESCRIPTION+"_"+Functionlibrary.generateDate()+".jpg"));
		
	// add screenshot to extent report
		 String image = logger.addScreenCapture("./Screenshot/"+DESCRIPTION+"_"+Functionlibrary.generateDate()+".jpg");
		 logger.log(LogStatus.PASS,image);*/
		}
		catch(Exception e)
		{
			Excelfileutil.setData(TCmodule,j,5,"failed");	
		Modulestatus =  "false";
		logger.log(LogStatus.FAIL, DESCRIPTION+"failed");
		
		//take screenshot
			File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(src,new File("./Screenshot/"+DESCRIPTION+"_"+Functionlibrary.generateDate()+".jpg"));
			
		// add screenshot to extent report
			 String image = logger.addScreenCapture("./Screenshot/"+DESCRIPTION+"_"+Functionlibrary.generateDate()+".jpg");
			 logger.log(LogStatus.FAIL,image);
		break;
		}
		
		catch(AssertionError a) // to handle assert type error
		{
			Excelfileutil.setData(TCmodule,j,5,"failed");	
		Modulestatus =  "false";
		
		//take screenshot
		File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(src,new File("./Screenshot/"+DESCRIPTION+"_"+Functionlibrary.generateDate()+".jpg"));
		
	    // add screenshot to extent report
		 String image = logger.addScreenCapture("./Screenshot/"+DESCRIPTION+"_"+Functionlibrary.generateDate()+".jpg");
		 logger.log(LogStatus.FAIL,image);
		
		break;
		}
		}
		if(Modulestatus.equalsIgnoreCase("true"))
		{
			Excelfileutil.setData("mastertestcases",i, 3,"passed");
		}
		else
		{
			Excelfileutil.setData("mastertestcases",i ,3, "failed");
		}
	}
	else
	{
		Excelfileutil.setData("mastertestcases",i, 3,"notexecuted");
	}
	report.endTest(logger);
	report.flush();
}	 
}
}
