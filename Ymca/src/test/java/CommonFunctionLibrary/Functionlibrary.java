package CommonFunctionLibrary;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;

import java.text.SimpleDateFormat;
import java.util.Date;


import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.gargoylesoftware.htmlunit.javascript.background.JavaScriptExecutor;

import org.openqa.selenium.JavascriptExecutor;

import Utilities.Propertiesfileutil;



public class Functionlibrary {

	public static SoftAssert sa;
	public static JavaScriptExecutor js;
	
	// method for kickstart of browser // returntype is WebDriver (.......................)
	public  static WebDriver startbrowser(WebDriver driver) throws Throwable
	{
		if(Propertiesfileutil.getvalueforkey("Browser").equalsIgnoreCase("firefox"))
						
					{
			System.setProperty("webdriver.gecko.driver","E:\\ahmed\\eclipse-workspace\\Adactin\\geckodriver.exe");
		driver = new FirefoxDriver();
		}
		else if(Propertiesfileutil.getvalueforkey("Browser").equalsIgnoreCase("chrome"))
		{
			//System.setProperty("webdriver.chrome.driver","C:\\Users\\Ahmed\\Downloads\\chromedriver_win32 (2)");
			driver = new ChromeDriver();
		}
		else if(Propertiesfileutil.getvalueforkey("Browser").equalsIgnoreCase("ie"))
		{
			System.setProperty("webdriver.ie.driver","commonjarfiles/IEDriverServer.exe");
			driver  = new InternetExplorerDriver();
		}
		return driver;
	}
	//method for open application
	public static void openapplication(WebDriver driver) throws Throwable
	{
	driver.manage().window().maximize();
	driver.get(Propertiesfileutil.getvalueforkey("url"));
	}
	//method for close application
	public static void closeapplication(WebDriver driver)
	{
	driver.close();	
	}
	//method for click operation
	public static void clickaction(WebDriver driver , String  locatortype ,String locatorvalue)
	{
	if(locatortype.equalsIgnoreCase("id"))
	{
	driver.findElement(By.id(locatorvalue)).click();	
	}	
	else if(locatortype.equalsIgnoreCase("name"))
	{
	driver.findElement(By.name(locatorvalue)).click();
	}
	else if(locatortype.equalsIgnoreCase("xpath"))
	{
		driver.findElement(By.xpath(locatorvalue)).click();
	}
	else if(locatortype.equalsIgnoreCase("linkText"))
	{
		driver.findElement(By.linkText(locatorvalue)).click();
	}
	else if(locatortype.equalsIgnoreCase("partialLinkText"))
	{
	driver.findElement(By.partialLinkText(locatorvalue)).click();	
	}
	else if(locatortype.equalsIgnoreCase("tagName"))
	{
		driver.findElement(By.tagName(locatorvalue)).click();
	}
	}
	//method for actions
	public static  void typeaction(WebDriver driver,String locatortype,String locatorvalue,String data) throws Throwable
	{
		if(locatortype.equalsIgnoreCase("id"))
		{
			driver.findElement(By.id(locatorvalue)).clear();
			Thread.sleep(2000);
			driver.findElement(By.id(locatorvalue)).sendKeys(data);
		}
		else if(locatortype.equalsIgnoreCase("name"))
		{
			driver.findElement(By.name(locatorvalue)).sendKeys(data);
		}
		else if(locatortype.equalsIgnoreCase("xpath"))
		{
		driver.findElement(By.xpath(locatorvalue)).clear();
		Thread.sleep(2000);
		driver.findElement(By.xpath(locatorvalue)).sendKeys(data);
		
		}
	}
	//method for wait for element to display statement
	public static void waiting(WebDriver driver,String locatortype ,String locatorvalue)
	{
	WebDriverWait wait = new WebDriverWait(driver,30);	
	if(locatortype.equalsIgnoreCase("id"))
	{
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(locatorvalue)));
	}
	else if (locatortype.equalsIgnoreCase("name"))
	{
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(locatorvalue)));	
	}
	else if(locatortype.equalsIgnoreCase("xpath"))
	{
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locatorvalue)));	
	}
	}
	//method for title validation
	public static void titlevalidation(WebDriver driver, String exp_title)
	{
	String act_title = driver.getCurrentUrl();
	Assert.assertEquals(act_title,  exp_title);
	System.out.println(act_title);
	}
	
	//select from dropdown list
	
	public static void dropdownaction(WebDriver driver,String locatortype ,String locatorvalue,String data )
	{
		Actions action =new Actions(driver);
		if(locatortype.equalsIgnoreCase("id"))
		{
			WebElement element=driver.findElement(By.id(locatorvalue));
			action.moveToElement(element).perform();
			action.click().sendKeys(data).sendKeys(Keys.ENTER).build().perform();
		}
		
		else if(locatortype.equalsIgnoreCase("xpath"))
		{
			WebElement element=driver.findElement(By.xpath(locatorvalue));
			action.moveToElement(element).perform();
			action.click().sendKeys(data).sendKeys(Keys.ENTER).build().perform();
		}
	}
	public static void Scrolldownaction(WebDriver driver) throws Throwable
	{
		
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("window.scrollBy(0,2200)", "");
		Thread.sleep(6000);
	}
	
	// create method for date generation
	public static String generateDate()
	{
	Date date = new Date();// import Date from java.util
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy_mm_dd_ss");
	return sdf.format(date);
	}
}
