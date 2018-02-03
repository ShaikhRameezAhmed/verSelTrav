package lib;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;



public class Takescreenshot 
{
public void Takesnap(WebDriver driver,String excelPath)
    {
    try 
    {
    	File src=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(src,new File(excelPath));
	 } 
    catch (Exception e) 
    {
	System.out.println(e.getMessage());
    }
  }
}