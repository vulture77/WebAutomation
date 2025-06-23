package Pages;
import static org.testng.Assert.assertEquals;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

import com.mailosaur.MailosaurException;

import utils.ExcelUtils;

public class RegistrationPage extends BasePage {

    // Locators
    By RegisterPage = By.xpath("//a[@class='jss473 jss485 jss487 jss490 jss508 jss846'][2]");
    By LSPradiobutton = By.xpath("(//input[@value='lsp'])[2]");
    By Email = By.xpath("(//input[@class='jss1379 jss1364'])[2]");
    By MobileNumber =By.xpath("(//input[@placeholder='Enter Phone Number'])[2]");
    By Password = By.xpath("(//input[@name='password'])[2]");
    By Passwordconfirm= By.xpath("(//input[@type='password'])[4]");
    By PrivacyPolicyCheckbox=By.xpath("(//input[@name='checkbox'])[2]");
    By RegisterButtton=By.xpath("(//span[contains(text(),'Register')])[2]");
    By Otpverification=By.xpath("//input[@name='otp']");
    By VerifyButton=By.xpath("//span[contains(text(),'Verify')]");
    By IncorrectPassword = By.xpath("(//p[contains(text(),'Passwords do not match')])[2]");
    By resendOtp = By.xpath("//span[contains(text(),'Resend OTP')]");

    // Constructor
    public RegistrationPage(WebDriver driver) {
        super(driver);
    }

    // Main registration method
        public void userRegistration() throws InterruptedException, IOException, MailosaurException {
        
        OTPMailosaur mail= new OTPMailosaur();
        driver.findElement(RegisterPage).click();
        Thread.sleep(2000);
        driver.findElement(LSPradiobutton).click();
        String generatedEmail = mail.getcorrectEmailID();
        ExcelUtils.appendEmailIdToExcel("GeneratedEmails.xlsx","EmailID",generatedEmail); // ✅ Log the email to Excel
        driver.findElement(Email).sendKeys(generatedEmail);
        System.out.println(generatedEmail);
        driver.findElement(MobileNumber).sendKeys(mail.MobileNumberGenerator());
        driver.findElement(Password).sendKeys("Test@123");
        driver.findElement(Passwordconfirm).sendKeys("ABC"); 
        
        //tabbing out to generate the error
        Actions actions = new Actions(driver);
        actions.sendKeys(Keys.TAB).perform();
       
        String ErrorMessage = (driver.findElement(IncorrectPassword)).getText();
        assertEquals(ErrorMessage,"Passwords do not match");
        System.out.println("✅ Assertion passed: Error message is correct.");
        driver.findElement(Passwordconfirm).clear();
        driver.findElement(Passwordconfirm).sendKeys("Test@123");
        driver.findElement(PrivacyPolicyCheckbox).click();
        driver.findElement(RegisterButtton).click();
       // System.out.println("⏳ Waiting 2 minutes for OTP to expire...");
       // Thread.sleep((2*60*1000)+2000);// Wait 2 minutes + 2 seconds (in milliseconds)
      //  driver.findElement(resendOtp);
        String OTP=mail.testEmailExample(generatedEmail);
        driver.findElement(Otpverification).sendKeys(OTP);
        driver.findElement(VerifyButton).click();
        String WelcomeMessage = mail.getWelcomeEmail(generatedEmail);
        assertEquals(WelcomeMessage, "Welcome to Prabandhak");
        System.out.println("✅ Assertion passed: Welcome message is received.");
       
}
       
}