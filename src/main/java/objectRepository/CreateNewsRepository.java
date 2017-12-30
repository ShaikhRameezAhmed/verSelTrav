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

public class CreateNewsRepository {
	public static WebDriverWait wait = null;
	public static WebElement element;
	static ExtentTest test;
	static Logger logger = Logger.getLogger("Service Alerts");
	static Configuration Config = new Configuration();
	static Actions action;
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
	//clicking on News link
	public static void newsLink(WebDriver driver){
		wait=new WebDriverWait(driver,30);
		element=driver.findElement(By.xpath(".//*[@id='sub_2']/ul"));
		wait.until(ExpectedConditions.visibilityOf(element));
		ArrayList<WebElement> alllinks=(ArrayList<WebElement>) element.findElements(By.tagName("li"));
		alllinks.get(0).click();
		String actualTitle ="MyTravelport - News";
		String expectedTitle= driver.getTitle();
		Assert.assertEquals(actualTitle, expectedTitle);
	}
	//Create Article
	public static void createArticle(WebDriver driver){
		wait=new WebDriverWait(driver,30);
		element=driver.findElement(By.xpath("//*[contains(text(), 'Create Article')]"));
		wait.until(ExpectedConditions.visibilityOf(element));
		element.click();
	}
	
	//Scroll down the webpage
	public static void scrolldown(WebDriver driver,int pagedown, int pageup){
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		/*// Scroll vertically downward by 250 pixels
		jse.executeScript("window.scrollBy(" + beforeArg + "," + arg +  ")", "");
		//jse.executeScript("document.getElementById(\"" + divid + ").scrollTop(250); ", "");
		element =driver.findElement(By.xpath("//*[@id='title_en_US']"));
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", element);*/
		
		action = new Actions(driver);
		for(int i=0;i<pagedown;i++){
		 action.sendKeys(Keys.PAGE_DOWN).build().perform();}
		for(int j=0;j<pageup;j++){
		 action.sendKeys(Keys.PAGE_UP).build().perform();}
		//jse.executeScript("document.getElementsByClassName(\'" + divid + "\')[0]", "");
	}
	
	// Fill Form
		public static WebElement title(WebDriver driver) {
			element = driver.findElement(By.xpath("//*[@id='title_en_US']"));
			element.click();
			return element;
		}

		public static WebElement summary(WebDriver driver) {
			element = driver.findElement(By.xpath("//*[@id='summary_en_US']"));
			element.click();
			return element;
		}
		
		//Not needed as of now
		/*public static WebElement content(WebDriver driver) {
			 driver.switchTo().frame(1);
			System.out.println("********We are switched to the iframe*******");
			for(int i=0; i<=size; i++){
				driver.switchTo().frame(i);
				int total=driver.findElements(By.xpath("//html/body[@class='html-editor portlet tvp-article-container cke_editable cke_editable_themed cke_contents_ltr cke_show_borders']")).size();
				System.out.println(total);
				    driver.switchTo().defaultContent();}
			element = driver.findElement(By.xpath("//html/body[@class='html-editor portlet tvp-article-container cke_editable cke_editable_themed cke_contents_ltr cke_show_borders']"));
			System.out.println("********We are switched to the element*******");
			element.click();
			return element;
		}*/

		//Upload File
	/*public static void uploadFile(WebDriver driver) throws InterruptedException {
		wait = new WebDriverWait(driver, 30);
		WebElement uploadElement = driver.findElement(By
				.xpath("//*[@id='_travelportarticleportlet_WAR_travelportarticleportlet_en_US_Image_INSTANCE_rpie']"));
		wait.until(ExpectedConditions.visibilityOf(uploadElement));
		uploadElement.sendKeys("D:\\Test Repository\\CustomerPortal.jpg");
		Thread.sleep(2000);
		POM.loadPageBeforeNaviating(driver);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("$('input[type='file']').val('D:\\Test Repository\\CustomerPortal.jpg') ");

	}*/
		
		public static void publish(WebDriver driver) {
			element = driver.findElement(By.xpath("//*[@id='articleSubmit']"));
			element.click();
		}
		
		//Click on home tab to validate
		public static void home(WebDriver driver) {
			wait=new WebDriverWait(driver,30);
			element=driver.findElement(By.linkText("Home"));
			wait.until(ExpectedConditions.visibilityOf(element));
			Actions action=new Actions(driver);
			action.moveToElement(element).perform();
			action.click();
		}
		//validating News
		public static void validate(WebDriver driver) {
			String actual ="Automation Test Service News #12346";
			element=driver.findElement(By.xpath(".//*[contains(text(), 'Selenium Test Service News #12342')]"));
			String expected=element.getText();
			Assert.assertTrue(actual.contains(expected));
		}

}
