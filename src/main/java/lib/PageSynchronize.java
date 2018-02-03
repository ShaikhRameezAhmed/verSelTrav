package lib;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PageSynchronize {

	public static void loadPageBeforeNaviating(final WebDriver wb) {
	      WebDriverWait _wait = new WebDriverWait(wb, 40);
		_wait.until(new ExpectedCondition<Boolean>() {
		        public Boolean apply(WebDriver wdriver) {
		            return ((JavascriptExecutor) wb).executeScript(
		                "return document.readyState"
		            ).equals("complete");
		        }
		    }); 
	      
	  }

}
