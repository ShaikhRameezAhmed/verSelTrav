package objectRepository;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.relevantcodes.extentreports.ExtentTest;

import Utility.Configuration;
import lib.ExcelDataConfig;


public class IncidentCreate 
{
	
	public static WebDriverWait wait = null;
	public static WebElement element;
	static ExtentTest test;
	static Logger logger = Logger.getLogger("Service Alerts");
	static Configuration Config = new Configuration();
	static ExcelDataConfig excel;
	
	//.Wait till visibility of Create Button and Click it
	public static void ButtonCreate(WebDriver driver){
		 wait=new WebDriverWait(driver,40);
		element=driver.findElement(By.xpath("//*[@id='quickbar-incidents']/a"));
		wait.until(ExpectedConditions.visibilityOf(element));
		element.click();
	}
	
	//.Wait till visibility of QATEST Button and Click it
		public static void ButtonQATEST(WebDriver driver)
		{
			wait=new WebDriverWait(driver,30);
			element=driver.findElement(By.xpath("//*[@id='portlet_travelportincidentportlet_WAR_travelportincidentportlet']/div/div/div/div[3]/div[1]/div[2]/div[2]/a"));
			wait.until(ExpectedConditions.visibilityOf(element));
			element.click();
		}
		
	//.Enter data in Subject
		public static WebElement Subject(WebDriver driver) throws IOException
		{
					wait=new WebDriverWait(driver,30);
					element=driver.findElement(By.xpath(".//*[@id='_travelportincidentportlet_WAR_travelportincidentportlet_subject']"));
					wait.until(ExpectedConditions.visibilityOf(element));
					element.click();
					return element;
				}
		//.Enter data in Question
				public static WebElement Question(WebDriver driver)
				{
							wait=new WebDriverWait(driver,30);
							element=driver.findElement(By.xpath(".//*[@id='question']"));
							wait.until(ExpectedConditions.visibilityOf(element));
							element.click();
							return element;
				}
				
				//.Wait till visibility of Submit Button and Click it
				public static void SubmitBTN(WebDriver driver)
				{
					wait=new WebDriverWait(driver,30);
					element=driver.findElement(By.xpath(".//*[@id='incidentSubmit']"));
					wait.until(ExpectedConditions.visibilityOf(element));
					element.click();
				}
				
				//.Alert Message post Incident Creation
				public static WebElement CREATE_Alert(WebDriver driver)
				{
					wait=new WebDriverWait(driver,30);
					element=driver.findElement(By.xpath(".//*[@id='portlet_travelportincidentportlet_WAR_travelportincidentportlet']/div/div/div/div[1]"));
					wait.until(ExpectedConditions.visibilityOf(element));
					element.click();
					return element;
				}
	
	public static void Validate(WebDriver driver) throws IOException
	{
		//ExcelDataConfig excel = new ExcelDataConfig(Config.getExcelPath());
		String expectedMessage =excel.getData(0, 1, 0);
		WebElement actualMessageelement = IncidentCreate.CREATE_Alert(driver);
		String	actualMessage= actualMessageelement.getText();
	
	}
	
	//Clicking on Notification
		public static void notification(WebDriver driver){
			element=driver.findElement(By.xpath("//*[@id='_2_WAR_notificationsportlet_userNotifications']/a"));
			wait.until(ExpectedConditions.visibilityOf(element));
			element.click();
		}
		
		//clicking on notification link
		public static void notificationlink(WebDriver driver) throws InterruptedException{
			element=driver.findElement(By.xpath("//*[contains(text(), 'Notifications (')]"));
			wait.until(ExpectedConditions.visibilityOf(element));
			element.click();
			Thread.sleep(30);
			
		}
	
	}
