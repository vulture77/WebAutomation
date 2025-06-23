package Pages;
import static org.testng.Assert.assertTrue;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import utils.TestUtil;

public class Changepassword_myprofile extends BasePage{
	    //Locators
	      By Your_EmailId = By.xpath("(//input[@placeholder='Your Email Id'])[2]");
	      By Your_Password=By.xpath("(//input[@name='password'])[2]");
	      By Sign_In =By.xpath("(//button[@type='submit'])[2]");
	      By Myprofile_Icon=By.xpath("(//img[@alt='Venkatesulu LSP1'][1])");
	      By Change_Password=By.xpath("//li[contains(text(),'Change Password')]");
	      By Old_Password=By.xpath("(//input[@placeholder='Old Password'])[1]");
          By New_Password=By.xpath("//input[@placeholder='New Password']");
          By Confirm_NewPassword=By.xpath("//input[@placeholder='Confirm New Password']");
          By Submit_Btn=By.xpath("//button[@type='submit']");
          By toastMessageLocator = By.xpath("//div[@class='toast-message']");
         
          
	    //Constructor
	    
          public Changepassword_myprofile(WebDriver driver) {
        	  super(driver);
           
	    
          }
	
	    // (action method) Change Password_Method
	      public void Change_password () throws InterruptedException {
	    	  TestUtil.loginAsUserByRow(driver,4); 
	    	  Thread.sleep(2000);
	    	  driver.findElement(Myprofile_Icon).click();
	    	  driver.findElement(Change_Password).click();
	    	  driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	    	  driver.findElement(Old_Password).sendKeys("Challa@1234");
	    	  driver.findElement(New_Password).sendKeys("Challa@123");
	    	  driver.findElement(Confirm_NewPassword).sendKeys("Challa@123");
	    	  driver.findElement(Submit_Btn).click();
	   
           //Toast message validation
	    	
	    	  String toastText = getToastMessageText(toastMessageLocator);
	    	    System.out.println("Toast message shown: " + toastText);
	    	    String ExpectedPartialMessage = "Password changed succesfully";
	    	    //assertTrueCheck(toastText, ExpectedPartialMessage);
	    	    assertTrue(toastText.contains(ExpectedPartialMessage),"Toast message did not contain expected text. Actual toast: " + toastText);
	    	    System.out.println("✅ Toast message appeared correctly: " + toastText);
	    	  
	    	  
	    	   }

		
	    
	    
}
	    
	   
	         
	
	