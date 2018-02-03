package objectRepository;

import java.util.NoSuchElementException;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.relevantcodes.extentreports.LogStatus;

public class BootstrapAlerts {
	static Logger logger = Logger.getLogger("BootstrapAlerts");
	
	public static void AlertsDismiss(WebDriver driver){
		/*WebElement modalDisplay = driver.findElement(By.xpath("//div[@class='modal-content yui3-widget-stdmod']"));
		WebDriverWait block = new WebDriverWait(driver, 30);
		closePopUpDisplayWithFocussed(block, driver);*/
		try {
			WebElement modalDisplay = driver.findElement(By.xpath("//div[@class='modal-content yui3-widget-stdmod']"));
			WebDriverWait block = new WebDriverWait(driver, 30);
			closePopUpDisplayWithFocussed(block, driver);
			block.until(ExpectedConditions.visibilityOf(modalDisplay));
			while ((modalDisplay.isDisplayed())) {
				modalDisplay.click();
				closePopUpDisplayWithFocussed(block, driver);
				}
		} 
		catch (org.openqa.selenium.NoSuchElementException e) {
			e.printStackTrace();
			//Assert.assertTrue(true);
			//test.log(LogStatus.PASS, e.getMessage());
		}
		catch (org.openqa.selenium.StaleElementReferenceException e) {
			logger.info("All pop up/alerts handled.");
			//logger.error(e.getMessage());
			//Assert.assertTrue(true);
		}
		finally{
			return;
		}
	
		 
	}

	private static void closePopUpDisplayWithFocussed(WebDriverWait block, WebDriver driver) {
		
		try{	
		WebElement Cross = driver.findElement(By
					.xpath("//*[@class='yui3-widget modal yui3-widget-positioned yui3-widget-stacked yui3-widget-modal modal-focused']"
							+ "/descendant::button[@type='button'][text()='×']"));

			block.until(ExpectedConditions.visibilityOf(Cross));
			if (Cross.isDisplayed()) {
				Cross.click();
			}
		}
		catch(org.openqa.selenium.NoSuchElementException e){
			logger.info("Alerts not present.");
		}
		finally{
			return;
		}
		

		
	}

}
