package objectRepository;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.relevantcodes.extentreports.ExtentTest;

import Utility.Configuration;
import lib.ExcelDataConfig;

public class Profile {
	
	public static WebDriverWait wait = null;
	public static WebElement element;
	static ExtentTest test;
	static Logger logger = Logger.getLogger("Service Alerts");
	Configuration Config = new Configuration();
	static ExcelDataConfig excel;
	
	public static void Settings(WebDriver driver){
		 wait=new WebDriverWait(driver,30);
		element=driver.findElement(By.xpath("//i[@class='fa fa-cog']"));
			wait.until(ExpectedConditions.visibilityOf(element));
			element.click();
	}
	
	public static WebElement FirstName(WebDriver driver){
		 wait=new WebDriverWait(driver,30);
		element=driver.findElement(By.xpath(".//*[@id='firstName']"));
			wait.until(ExpectedConditions.visibilityOf(element));
			element.click();
			element.clear();
			return element;
	}
	
	public static WebElement LastName(WebDriver driver){
		 wait=new WebDriverWait(driver,30);
		element=driver.findElement(By.xpath(".//*[@id='lastName']"));
			wait.until(ExpectedConditions.visibilityOf(element));
			element.click();
			element.clear();
			return element;
	}
	
	public static void SAVEBtn(WebDriver driver){
		 wait=new WebDriverWait(driver,30);
		element=driver.findElement(By.xpath(".//*[@id='updateProfile']"));
			wait.until(ExpectedConditions.visibilityOf(element));
			element.click();
	}
	
	public static void Confirm(WebDriver driver){
		 wait=new WebDriverWait(driver,30);
		element=driver.findElement(By.xpath("//*[@class='user-full-name']"));
			wait.until(ExpectedConditions.visibilityOf(element));
			String Actual= element.getText();
			System.out.println(Actual);
			String Expected="Profile Test";
			Assert.assertTrue(Actual.startsWith(Expected));
			//test.log(LogStatus.PASS, "PASSED Profile Update");
			logger.info("Profile Updated");
			
			
	}
	
	public static void ProfileName(WebDriver driver){
		 wait=new WebDriverWait(driver,30);
		element=driver.findElement(By.xpath("//*[@id='nav_content_profile']/div[2]"));
			wait.until(ExpectedConditions.visibilityOf(element));
			String ActualName= element.getText();
			System.out.println(ActualName);
			String ExpectedName="Profile information saved successfully";
			Assert.assertTrue(ActualName.equalsIgnoreCase(ExpectedName));
			//test.log(LogStatus.PASS, "PASSED Name Matches");
			logger.info("Name Matches");
			
			
	}
  
}
