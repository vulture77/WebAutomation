package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.ExcelReader;

public class ApplyNMTProjectOverview extends BasePage {
	
    // Locators

    By Publish = By.xpath("//button[span[text()='Publish']]");
    By Yes = By.xpath("//span[text()='Yes']");
    By ApplyNMT = By.xpath("//button[span[text()='Apply NMT']]");
    By Apply = By.xpath("//button[span[text()='Apply']]");
    By Close = By.xpath("//button[span[text()='Close']]");
    By Report = By.xpath("//a[@class='nav-link' and text()='Report']");
    
    
    //Constructor
      public ApplyNMTProjectOverview(WebDriver driver) {
      
    	  super(driver);
    }
      
      public void applyNMT() throws InterruptedException {
      String projectId = ExcelReader.getLatestEmailID("test-data/GeneratedEmails.xlsx","ProjectID" ,0);
	  driver.navigate().to(projectId);
	  Thread.sleep(9000);
	  driver.findElement(ApplyNMT).click();
	  driver.findElement(Apply).click();
	  Thread.sleep(20000);
	  driver.findElement(Close).click();
      }
      
      public void publish() 
      {
      driver.findElement(Publish).click();
      driver.findElement(Yes).click();
      }
	  
      public void report() throws InterruptedException
      {
      Thread.sleep(9000);
      driver.findElement(Report).click();
      }

	  

}
