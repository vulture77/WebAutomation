package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class LoginPage extends BasePage {
	
    // Locators
    By Email = By.xpath("(//input[@name='email'])[2]");
    By Password = By.xpath("(//input[@name='password'])[2]");
    By SignIn = By.xpath("(//button[@type='submit'])[2]");
    
    //Constructor
    public LoginPage(WebDriver driver) 
    {
       super(driver);
    }
    
    public void existingUserLogin(String emailID,String pass) 
    {	
    	
    	wait.until(ExpectedConditions.visibilityOf(driver.findElement(Email))).sendKeys(emailID);
    	wait.until(ExpectedConditions.visibilityOf(driver.findElement(Password))).sendKeys(pass);
    	driver.findElement(SignIn).click(); 	

    }  
    
  
}
