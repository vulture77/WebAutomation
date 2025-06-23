package Pages;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.ExcelReader;

public class NewUserLoginPage extends BasePage
{
    
	OTPMailosaur mail = new OTPMailosaur();
	String EmailId;

//Locators
	By email= By.xpath("(//input[@name='email'])[2]");
	By password = By.xpath("(//input[@name='password'])[2]");
	By SignInbutton = By.xpath("(//span[contains(text(),'Sign In')])[2]");
	By toastMessageLocator = By.xpath("//div[@class='toast-message']");
	By privacyPolicyCheckBox = By.xpath("//input[@name='aggreedPrivacy']");
	By readAndAcceptButton=By.xpath("//span[contains(text(),'Read and Accept')]");
    By privacyPolicyLabel=By.xpath("//strong[contains(text(),'Privacy Policy Terms and Conditions')]");
    By basicInfoPage=By.xpath("//div[contains(text(),'Complete your basic info.')]");
    By companyNameField=By.xpath("//input[@name='organisationName']");
    By companyWebsiteField=By.xpath("//input[@name='organisationUrl']");
    By fullNameField=By.xpath("//input[@name='displayName']");
    By billingAdressField=By.xpath("//input[@name='billingAddress']");
    By aboutCompanyField=By.xpath("//textarea[@name='about']");
    By teamSizeField=By.xpath("//select[@name='teamsize']");
    By basicInfoSaveButton=By.xpath("//button[@type='submit']");
    By eulaPage=By.xpath("//h6[text()='END-USER LICENSE AGREEMENT']");
    By eulaCheckBox=By.xpath("//input[@value='eulaChecked']");
    By eulaAgreeButton=By.xpath("//span[text()='Agree']");
    By planPage=By.xpath("//h6[contains(text(),'✨✨✨ Congratulations ✨✨✨')]");
    By planSkip=By.xpath("//span[contains(text(),'Skip')]");
    By planUpgrade=By.xpath("//span[contains(text(),'Upgrade Plan')]");

//Constructor
 public NewUserLoginPage(WebDriver driver) 
{
   super(driver); // This sets driver & wait from BasePage
}

public void LoginPageValidation() throws Exception
{
	EmailId = ExcelReader.getLatestEmailID("test-data/GeneratedEmails.xlsx","EmailID",0);
	System.out.println(EmailId);
	driver.findElement(email).sendKeys(EmailId);
	incorrectPasswordValidation();
	userLoginValidation();
	privacyPolicyValidation();
	basicInfoPageValidation();
	eulaPageValidation();
}
 
public void incorrectPasswordValidation()
{
	//Incorrect Password Validation
	driver.findElement(password).sendKeys("ABC");
	driver.findElement(SignInbutton).click();
	String toastText = getToastMessageText(toastMessageLocator);
	System.out.println("Toast message shown: " + toastText);
	String ExpectedPartialMessage = "incorrect username or password";
	//assertTrueCheck(toastText, ExpectedPartialMessage);
	assertTrue(toastText.toLowerCase().trim().contains(ExpectedPartialMessage),"Toast message did not contain expected text. Actual toast: " + toastText);
    System.out.println("✅ Toast message appeared correctly: " + toastText);
}

public void userLoginValidation() throws Exception
{
	driver.findElement(password).clear();
	driver.findElement(password).sendKeys("Test@123");
	driver.findElement(SignInbutton).click();
}

public void privacyPolicyValidation() throws Exception
{
	wait.until(ExpectedConditions.presenceOfElementLocated(privacyPolicyLabel));
	assertEquals((driver.findElement(privacyPolicyLabel).getText()), "Privacy Policy Terms and Conditions");
    System.out.println("✅ Assertion passed: Privacy Policy agreement is displayed");
	wait.until(ExpectedConditions.presenceOfElementLocated(privacyPolicyCheckBox));
	try 
	{
	WebElement checkbox = wait.until(ExpectedConditions.presenceOfElementLocated(privacyPolicyCheckBox));
	scrollwithJS(checkbox);
	clickWithJS(checkbox);
	}
	catch (Exception e)
	{
		    System.out.println("❌ Exception occurred: " + e.getMessage());
		    System.out.println("Current URL: " + driver.getCurrentUrl());
		    throw e; 
	}
	driver.findElement(readAndAcceptButton).click();
	//Policy Email validation
	String Policyemail=mail.getPrivacyPolicyEmail(EmailId);
    assertEquals(Policyemail, "Prabandhak Privacy Policy");
    System.out.println("✅ Assertion passed: Privacy Policy agreement mail received");
    
}
public void basicInfoPageValidation() throws InterruptedException
{
    //BAsic info page Navigation validation
	WebElement message = wait.until(ExpectedConditions.visibilityOfElementLocated(basicInfoPage));
	assertEquals(message.getText().trim(), "Complete your basic info.");
    System.out.println("✅ Assertion passed: User navigated to Basic Info Page ");
	assertTrueCheck(driver.findElement(companyNameField),"companyNameField ");
	assertTrueCheck(driver.findElement(companyWebsiteField),"companyWebsiteField ");
	assertTrueCheck(driver.findElement(fullNameField),"fullNameField ");
	assertTrueCheck(driver.findElement(billingAdressField),"billingAdressField ");
	assertTrueCheck(driver.findElement(aboutCompanyField),"aboutCompanyField ");
	assertTrueCheck(driver.findElement(teamSizeField),"teamSizeField ");
	assertTrueCheck(driver.findElement(basicInfoSaveButton),"basicInfoSaveButton");
    driver.findElement(companyNameField).sendKeys("Reverie Language Technologies");
    driver.findElement(fullNameField).sendKeys("XYZ");
    driver.findElement(billingAdressField).sendKeys("Sarjapur");
    wait.until(ExpectedConditions.invisibilityOfElementLocated(toastMessageLocator));
    driver.findElement(basicInfoSaveButton).click();
    String sucessMessage=getToastMessageText(toastMessageLocator);
    System.out.println("Toast message shown: " + sucessMessage);
	String expectedSuccessMessage = "Profile Update Successfully";
	assertTrue(sucessMessage.trim().contains(expectedSuccessMessage.trim()),"Toast message did not contain expected text. Actual toast: " + sucessMessage);
    System.out.println("✅ Toast message appeared correctly: " + sucessMessage);
       
}

public void eulaPageValidation() throws Exception
{
	WebElement eulamessage = wait.until(ExpectedConditions.visibilityOfElementLocated(eulaPage));
	assertEquals(eulamessage.getText().trim(), "END-USER LICENSE AGREEMENT");
    System.out.println("✅ Assertion passed: User navigated to EULA Page");
    wait.until(ExpectedConditions.presenceOfElementLocated(eulaCheckBox));
	try 
	{
	WebElement eulacheckbox = wait.until(ExpectedConditions.presenceOfElementLocated(eulaCheckBox));
	scrollwithJS(eulacheckbox);
	clickWithJS(eulacheckbox);
	}
	catch (Exception e)
	{
		    System.out.println("❌ Exception occurred: " + e.getMessage());
		    throw e; 
	}
    wait.until(ExpectedConditions.invisibilityOfElementLocated(toastMessageLocator));
	driver.findElement(eulaAgreeButton).click();
	//Toast Message Validation
	String sucessMessage=getToastMessageText(toastMessageLocator);
	System.out.println("Toast message shown: " + sucessMessage);
	String expectedSuccessMessage = "Terms and conditions has been sent to your registered email";
	assertTrue(sucessMessage.trim().contains(expectedSuccessMessage.trim()),"Toast message did not contain expected text. Actual toast: " + sucessMessage);
	System.out.println("✅ Toast message appeared correctly: " + sucessMessage);
	//Policy Email validation
	String eulaMail=mail.getEULAEmail(EmailId);
    assertEquals(eulaMail, "End user license agreement");
    System.out.println("✅ Assertion passed: Privacy Policy agreement mail received");
    WebElement planMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(planPage));
	assertEquals(planMessage.getText().trim(), "✨✨✨ Congratulations ✨✨✨");
    System.out.println("✅ Assertion passed: User navigated to Plan Upgrade Page");
    assertTrueCheck(driver.findElement(planSkip), "Skip Button ");
    assertTrueCheck(driver.findElement(planUpgrade), "Upgrade button ");
    driver.findElement(planSkip).click();
}

}
