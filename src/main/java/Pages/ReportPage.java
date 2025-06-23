package Pages;


import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import Base.BaseTest;



public class ReportPage extends BaseTest{
	
	
	//Locators
	By sltFile = By.xpath("(//div[@role='button' and @aria-haspopup='true'])[1]");
    By slt = By.xpath("//span[text()='Select All']");
    By submit = By.xpath("//button[span[text()='Submit']]");
    By nmt_reverie = By.xpath("//td[text()='nmt-reverie']");
    By Service = By.xpath("(//div[contains(@class,' css-1hwfws3')])[2]");
    By ServiceList = By.xpath("//div[contains(@class, ' css-11unzgr')]/div");
    By TM=By.xpath("//tbody//td[3]//td[3]");
    
    
    
    //Constructor
     public ReportPage(WebDriver driver, WebDriverWait wait) 
     {
    	 this.driver = driver;
    	 this.wait = wait;
    }
    
     public void reportpage() throws InterruptedException 
     {
     Thread.sleep(3000);
     driver.findElement(sltFile).click();
     driver.findElement(slt).click(); 
     Thread.sleep(5000);
     Actions act = new Actions(driver);
     act.sendKeys(Keys.ESCAPE).perform();
     driver.findElement(submit).click();
     }
     
     public String isNmtApplied() throws InterruptedException
     {
     Thread.sleep(2000);
     String text = driver.findElement(nmt_reverie).getText();
     return text;
     }
     
     public void selectingSerivce(String ser) throws InterruptedException {
    	 Thread.sleep(2000);
    	 driver.findElement(Service).click();
    	 List<WebElement> li = driver.findElements(ServiceList);
    	 for(int i=0; i<li.size(); i++) {
    		 if(li.get(i).getText().equals(ser)){
    			 li.get(i).click();
    			 break;
    			 
    		 }
    	 }
    	 
     }
     
     public String isTMApplied() throws InterruptedException
     {
    	 Thread.sleep(1000);
    	 return driver.findElement(TM).getText();
     }
     

}


