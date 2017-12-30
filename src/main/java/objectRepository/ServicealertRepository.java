package objectRepository;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import Utility.Configuration;
import lib.ExcelDataConfig;
import lib.Takescreenshot;

public class ServicealertRepository {
	public static WebDriverWait wait = null;
	public static WebElement element, titleElement, refElement, descriptionElement;
	static ExtentTest test;
	static Logger logger = Logger.getLogger("Service Alerts");
	static Configuration Config = new Configuration();
	static Takescreenshot obj = new Takescreenshot();

	public static void servicealerts(WebDriver driver) {
		wait = new WebDriverWait(driver, 30);
		element = driver.findElement(By.xpath("//*[@id='quickbar-alerts']/h4/a"));
		wait.until(ExpectedConditions.visibilityOf(element));
		element.click();
	}

	// CreateAlertButton
	public static void createAlert(WebDriver driver) {
		wait = new WebDriverWait(driver, 40);
		element = driver.findElement(
				By.xpath("//*[@id='portlet_travelportalert_WAR_travelportalertportlet']/div/div/div/div[1]/a"));
		wait.until(ExpectedConditions.visibilityOf(element));
		element.click();
	}

	// Fill Form
	public static WebElement title(WebDriver driver) {
		element = driver.findElement(By.xpath("//input[@id='title']"));
		element.click();
		return element;
	}

	public static WebElement reference(WebDriver driver) {
		element = driver.findElement(By.xpath("//*[@id='reference']"));
		element.click();
		return element;
	}

	public static WebElement description(WebDriver driver) {
		element = driver.findElement(By.xpath("//*[@id='description']"));
		element.click();
		return element;
	}

	public static void submitAlert(WebDriver driver) {
		element = driver.findElement(By.xpath("//*[@id='alertNewSubmit']"));
		element.click();
	}

	// Check for the message displayed after creating alert
	public static void messageDisplayed(WebDriver driver) throws InterruptedException {
		String expected = "Your request completed successfully";
		element = driver.findElement(By.xpath("//*[contains(text(), 'Your request completed successfully.')]"));
		wait.until(ExpectedConditions.visibilityOf(element));
		String actualresult = element.getText();
		Thread.sleep(2000);
		obj.Takesnap(driver, Config.SnapShotPath() + "/Alerts Created/Message_Dispalyed.jpg");
		Assert.assertTrue(actualresult.contains(expected));
	}

	// Clearing input fields
	public static void clear(WebDriver driver, WebElement titleElement, WebElement refElement,
			WebElement descriptionElement) {
		titleElement = driver.findElement(By.xpath("//input[@id='title']"));
		titleElement.clear();
		refElement = driver.findElement(By.xpath("//*[@id='reference']"));
		refElement.clear();
		descriptionElement = driver.findElement(By.xpath("//*[@id='description']"));
		descriptionElement.clear();
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

	/*//Assert alert
	public static void newlycreatedAlert(WebDriver driver) throws IOException{
		ExcelDataConfig excel = new ExcelDataConfig(Config.getExcelPath());
		element=driver.findElement(By.xpath("//*[@id='yui_patched_v3_11_0_1_1512640477139_195']/div[2]/li[1]/div/div[1]/div[2]/div[1]/div[1]"));
		wait.until(ExpectedConditions.visibilityOf(element));
		String nameFrom = element.getText();
		String toCheck = nameFrom.substring(nameFrom.indexOf("\'"), nameFrom.length());
		System.out.println(toCheck);
		System.out.println(excel.getData(1, 1, 0));
		assertEquals(toCheck, excel.getData(1, 1, 0));
		
	}*/
	

	// Perform the mandatory missing field validation.
	public static void negativetest(WebDriver driver) throws IOException {
		ExcelDataConfig excel = new ExcelDataConfig(Config.getExcelPath());
		ServicealertRepository.title(driver).sendKeys(excel.getData(1, 1, 0));
		ServicealertRepository.reference(driver);
		ServicealertRepository.description(driver).sendKeys(excel.getData(1, 10, 0));
		ServicealertRepository.submitAlert(driver);
		String expectedMessageReference = "Please fill out this field.";
		String actualMessageReference = driver.findElement(By.xpath("//*[@id='alertForm']/div[2]/div")).getText();
		Assert.assertEquals(actualMessageReference, expectedMessageReference);
		test.log(LogStatus.PASS, "PASSED negativeTest_Title");
		logger.info("negativeTest_Reference Successful");

		ServicealertRepository.clear(driver, titleElement, refElement, descriptionElement);
		ServicealertRepository.title(driver);
		if (!ServicealertRepository.reference(driver).getText().equals("")) {
			ServicealertRepository.reference(driver).sendKeys(excel.getData(1, 4, 0));
		}
		if (!ServicealertRepository.description(driver).getText().equals("")) {
			ServicealertRepository.description(driver).sendKeys(excel.getData(1, 10, 0));
		}
		ServicealertRepository.submitAlert(driver);
		String expectedMessageTitle = "Please fill out this field.";
		String actualMessageTitle = driver.findElement(By.xpath("//*[@id='alertForm']/div[2]/div")).getText();
		Assert.assertEquals(actualMessageTitle, expectedMessageTitle);
		test.log(LogStatus.PASS, "PASSED negativeTest_Title");
		logger.info("negativeTest_Title Successful");

		if (!ServicealertRepository.title(driver).getText().equals("")) {
			ServicealertRepository.title(driver).sendKeys(excel.getData(1, 1, 0));
		}
		if (!ServicealertRepository.reference(driver).getText().equals("")) {
			ServicealertRepository.reference(driver).sendKeys(excel.getData(1, 4, 0));
		}
		ServicealertRepository.description(driver);
		ServicealertRepository.submitAlert(driver);
		String expectedMessageDescription = "Please fill out this field.";
		String actualMessageDescription = driver.findElement(By.xpath("//*[@id='alertForm']/div[2]/div")).getText();
		Assert.assertEquals(actualMessageDescription, expectedMessageDescription);
		test.log(LogStatus.PASS, "PASSED negativeTest_Title");
		logger.info("negativeTest_Description Successful");

	}

}
