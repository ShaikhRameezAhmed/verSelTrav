package objectRepository;

import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

public class POM {
	public static WebElement element;
	static Logger log = Logger.getLogger("POM.class");
	public static WebDriverWait wait = null;

	// emailtextbox
	public static WebElement emailtextbox(WebDriver driver) {
		element = driver.findElement(By.xpath("//input[@id='user']"));
		element.click();
		Reporter.log("clicking on emailtextbox tab||",true);
		return element;
	}

	// passwordbox
	public static WebElement passwordbox(WebDriver driver) {
		element = driver.findElement(By.xpath("//input[@id='pass']"));
		element.click();
		Reporter.log("clicking on passwordboxtextbox tab||",true);
		return element;
	}
	// submit button
	public static void signin(WebDriver driver) {
		element = driver.findElement(By.xpath("//*[contains(text(), 'Sign in')]"));
		Reporter.log("clicking on signin tab||",true);
		element.click();
	}
	
	//logout
	public static void logOut(WebDriver driver) {
		element = driver.findElement(By.xpath("//*[contains(text(), 'Log out')]"));
		element.click();
		Reporter.log("clicking on signin tab||",true);
		log.info("Log Out successful");
		
	}
	
	// Switching to Alert
	public static void handleAlert(WebDriver driver) throws InterruptedException {
		Actions action = new Actions(driver);
		action.sendKeys(Keys.ESCAPE);
		//action.sendKeys(Keys.ESCAPE).build().perform();
		/*Alert alert = driver.switchTo().alert();		
	   	
	    Thread.sleep(5000);
	    alert.dismiss();*/
	}
}
