package objectRepository;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.relevantcodes.extentreports.ExtentTest;

import Utility.Configuration;
import lib.Takescreenshot;

public class CreateEventRepository {
	public static WebDriverWait wait = null;
	public static WebElement element;
	static ExtentTest test;
	static Logger logger = Logger.getLogger("Create Event");
	static Configuration Config = new Configuration();
	static Takescreenshot obj = new Takescreenshot();
	
	//Hovering over News tab
	public static void newsTab(WebDriver driver){
		wait=new WebDriverWait(driver,30);
		//element=driver.findElement(By.xpath("//*[@id='home']/body/div[4]/div[1]/header/nav/ul/li[2]/a"));
		element=driver.findElement(By.linkText("News"));
		wait.until(ExpectedConditions.visibilityOf(element));
		Actions action=new Actions(driver);
		action.moveToElement(element).perform();
		action.click();
	}
	//clicking on Event Link
	public static void EventLink(WebDriver driver){
		wait=new WebDriverWait(driver,30);
		element=driver.findElement(By.xpath(".//*[@id='sub_2']/ul"));
		wait.until(ExpectedConditions.visibilityOf(element));
		ArrayList<WebElement> alllinks=(ArrayList<WebElement>) element.findElements(By.tagName("li"));
		alllinks.get(1).click();
		String actualTitle ="MyTravelport - Events";
		String expectedTitle= driver.getTitle();
		Assert.assertEquals(actualTitle, expectedTitle);
	}
	//Create Event
	public static void createEvent(WebDriver driver){
		wait=new WebDriverWait(driver,30);
		element=driver.findElement(By.xpath("//*[contains(text(), 'Create Event')]"));
		wait.until(ExpectedConditions.visibilityOf(element));
		element.click();
	}
	
	//Scroll down the webpage
	public static void scrolldown(WebDriver driver,int pagedown, int pageup){
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		Actions action;
		action = new Actions(driver);
		for(int i=0;i<pagedown;i++){
		 action.sendKeys(Keys.PAGE_DOWN).build().perform();}
		for(int j=0;j<pageup;j++){
		 action.sendKeys(Keys.PAGE_UP).build().perform();}
		
	}
	
	public static WebElement PublishDate(WebDriver driver) {
		element = driver.findElement(By.xpath(".//*[@id='publishDate']"));
		element.click();
		return element;
	}
	
	public static WebElement PublishTime(WebDriver driver) {
		element = driver.findElement(By.xpath(".//*[@id='publishTime']"));
		element.click();
		return element;
	}
	
	// Fill Form
		public static WebElement title(WebDriver driver) {
			element = driver.findElement(By.xpath(".//*[@id='title_en_US']"));
			element.click();
			return element;
		}

		public static WebElement summary(WebDriver driver) {
			element = driver.findElement(By.xpath(".//*[@id='summary_en_US']"));
			element.click();
			return element;
		}
		
		public static WebElement content(WebDriver driver) {
			element = driver.findElement(By.className("html-editor portlet tvp-article-container cke_editable cke_editable_themed cke_contents_ltr cke_show_borders"));
			element.click();
			return element;
		}
		
		public static WebElement StartDate(WebDriver driver) {
			wait=new WebDriverWait(driver,40);
			element = driver.findElement(By.xpath("//*[@class='lfr-input-date datepicker-input']"));
			wait.until(ExpectedConditions.visibilityOf(element));
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("arguments[0].click();", element);
			ArrayList<WebElement> allspans=(ArrayList<WebElement>) element.findElements(By.tagName("input"));
			allspans.get(0).click();
			allspans.get(0).sendKeys("02/11/2018");
			element.click();
			System.out.println("element clicked");
			return element;
			/*POM.loadPageBeforeNaviating(driver);
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("$('input[id=\"ixfm_column1_0_displayDate\"]').click();");
			//jse.executeScript("$('input[name=\"_travelportarticleportlet_WAR_travelportarticleportlet_en_US_StartDate_INSTANCE_lmcq\"]').filter('[placeholder='mm/dd/yyyy']').val(02/03/2019);");
*/		}
		public static WebElement StartTime(WebDriver driver) {
			wait=new WebDriverWait(driver,40);
			element = driver.findElement(By.xpath("//*[@class='lfr-input-time timepicker-input']"));
			wait.until(ExpectedConditions.visibilityOf(element));
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("arguments[0].click();", element);
			ArrayList<WebElement> allspans=(ArrayList<WebElement>) element.findElements(By.tagName("input"));
			allspans.get(0).click();
			allspans.get(0).sendKeys("7:30AM");
			System.out.println("element clicked");
			Actions action=new Actions(driver);
			action.sendKeys(Keys.TAB);
			System.out.println("End Date  clicked");
			return element;
		
	}
		
		public static WebElement EndDate(WebDriver driver) {
			wait=new WebDriverWait(driver,40);
			element = driver.findElement(By.xpath("//*[@class='lfr-input-date datepicker-input']"));
			//element = driver.findElement(By.xpath("//*[(@id='_travelportarticleportlet_WAR_travelportarticleportlet_en__US__StartDate__INSTANCE__[a-z]')]"));
			wait.until(ExpectedConditions.visibilityOf(element));
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("arguments[0].click();", element);
			/*ArrayList<WebElement> allspans=(ArrayList<WebElement>) element.findElements(By.tagName("input"));
			allspans.get(0).click();*/
			//Actions action = new Actions(driver);
			ArrayList<WebElement> allspans=(ArrayList<WebElement>) element.findElements(By.tagName("input"));
			//action.moveToElement(allspans.get(1)).build().perform();
			allspans.get(1).click();
			allspans.get(1).sendKeys("02/14/2018");
			//element.click();
			System.out.println("element clicked");
			
			return element;
			/*POM.loadPageBeforeNaviating(driver);
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("$('input[id=\"ixfm_column1_0_displayDate\"]').click();");
			//jse.executeScript("$('input[name=\"_travelportarticleportlet_WAR_travelportarticleportlet_en_US_StartDate_INSTANCE_lmcq\"]').filter('[placeholder='mm/dd/yyyy']').val(02/03/2019);");
*/		}
		public static WebElement EndTime(WebDriver driver) {
			wait=new WebDriverWait(driver,40);
			element = driver.findElement(By.xpath("//*[@class='lfr-input-time timepicker-input']"));
			wait.until(ExpectedConditions.visibilityOf(element));
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("arguments[0].click();", element);
			ArrayList<WebElement> allspans=(ArrayList<WebElement>) element.findElements(By.tagName("input"));
			allspans.get(1).click();
			allspans.get(1).sendKeys("7:30AM");
			System.out.println("element clicked");
			return element;
	}

		
		public static WebElement Location(WebDriver driver) {
			element = driver.findElement(By.xpath(".//*[@id='_travelportarticleportlet_WAR_travelportarticleportlet_en_US_Location_INSTANCE_ybwg']"));
			element.click();
			return element;
		}
		
		public static WebElement LocationLink(WebDriver driver) {
			element = driver.findElement(By.xpath(".//*[@id='_travelportarticleportlet_WAR_travelportarticleportlet_en_US_LocationLink_INSTANCE_uyja']"));
			element.click();
			element.clear();
			return element;
		}
		
		public static WebElement RegistrationLink(WebDriver driver) {
			element = driver.findElement(By.xpath(".//*[@id='_travelportarticleportlet_WAR_travelportarticleportlet_en_US_RegistrationLink_INSTANCE_rjvf']"));
			element.click();
			element.clear();
			return element;
		}
		
		//Upload File
		public static void uploadFile(WebDriver driver) throws InterruptedException{
			wait=new WebDriverWait(driver,30);
			WebElement uploadElement = driver.findElement(By.xpath(".//*[@id='_travelportarticleportlet_WAR_travelportarticleportlet_en_US_Image_INSTANCE_qhzc']"));
			wait.until(ExpectedConditions.visibilityOf(uploadElement));
			uploadElement.sendKeys("D:\\Test Repository\\CustomerPortal.jpg");
			Thread.sleep(2000);
			
		}
		
		public static void publish(WebDriver driver) {
			element = driver.findElement(By.xpath("//*[@id='articleSubmit']"));
			element.click();
		}

}
