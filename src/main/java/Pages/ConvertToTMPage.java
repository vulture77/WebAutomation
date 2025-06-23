package Pages;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.mailosaur.MailosaurException;

import utils.ExcelURLManager;
import utils.TestUtil;

public class ConvertToTMPage extends BasePage {

    // Locators
    By Sign_Off = By.xpath("//span[normalize-space()='Sign Off']");
    By Confirmation = By.xpath("//span[normalize-space()='Yes']");
    By Convert_To_TM = By.xpath("//span[normalize-space()='Convert to TM']");
    By ConvertTM_Xlsx = By.xpath("(//button[@type='button'])[22]");
    By ConvertTM_Csv = By.xpath("(//button[@type='button'])[23]");
    By ConvertTM_Tmx = By.xpath("(//button[@type='button'])[24]");
    By ConvertTM_Json = By.xpath("(//button[@type='button'])[25]");
    By Toast_Message_TM_Conversion = By.xpath("//div[contains(text(),'project has been converted to TM successfully')]");
    By Translation_Memory = By.xpath("//span[normalize-space()='Translation Memory']");
    By Translation_Memory_1 = By.xpath("(//a[@role='button'])[3]");
    By Search_TM = By.xpath("//input[@placeholder='Search Translation memory.']");
    By Source_Lang = By.xpath("//td[normalize-space()='english']");
    By Target_Lang = By.xpath("//td[normalize-space()='hindi']");
    By Client_Name = By.xpath("//td[normalize-space()='Dexter']");
    By Domain_Name = By.xpath("//span[@title='Accounting & auditing, Advertising & public relations, Aerospace engineering, Anatomy & physiology, Arts and Humanities, Astronomy, Banking & financial law, Banking, Biography, Business, Chemical engineering, Chemistry, Civil & hydraulic engineering, Computer hardware, Computer systems analysis, Computers, Contracts, Corporate law, Criminology & penology, Dentistry, Economics & finance, Electrical engineering, Engineering, Genetics, Health care, Hotel management, Immigration, Immunology, Industrial engineering, Information & library sciences, Instruments, Insurance, International development, Journalism, Labor relations, Law, Linguistics, Marketing, Materials Science, Mathematics & statistics, Mechanical engineering, medical, Medicine, Metallurgy, Nontraditional medicine, Nuclear engineering, Nutrition, Patents & trademarks & copyrights, Personal injury law, Petroleum engineering, Pharmaceuticals, Physical sciences, Physics, Political science, Printing & publishing, Psychiatry, Psychology, Pure Sciences, Radiology, Real estate, Shipping & maritime, Social Sciences, Sociology, Software localization, Stock market, Tax law, Toxicology, Travel & tourism, Veterinary medicine, Legal, Judiciary, E-learning, Agriculture, Education, Fashion, Entertainment, Retail, Places, BOT, Names, App localization, Finance, Other']");
    By View_TM = By.xpath("(//*[name()='svg'][@role='presentation'])[12]");
    By Verify_Confirmed_Target = By.xpath("//td[contains(text(),'लेकिन यह मेरे लिए मुश्किल लग रहा है।')]");
    By Verify_Content_Unavailable = By.xpath("//td[normalize-space()='We have even ensured it.']");

    // Constructor
    public ConvertToTMPage(WebDriver driver) {
        super(driver);
    }

    // Utility: click
    public void waitAndClick(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

    // Utility: type with optional Keys
    public void waitAndSendKeys(By locator, CharSequence... keysToSend) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).sendKeys(keysToSend);
    }

    // Main Test Flow
    public void AccessprojectandConverttoTM() throws InterruptedException, IOException, MailosaurException {
        // Step 1: Read URL from Excel and navigate
        String url = ExcelURLManager.readURL();

        if (url != null && !url.isEmpty()) {
            driver.get(url);
            System.out.println("Navigated to URL from Excel: " + url);
        } else {
            throw new RuntimeException("URL not found or empty in Excel.");
        }

        // Step 2: Login
        TestUtil.loginAsUserByRow(driver, 3);
        waitAndClick(Sign_Off);
	    waitAndClick(Confirmation);
	    waitAndClick(Convert_To_TM);
	    waitAndClick(ConvertTM_Xlsx);
        WebDriverWait waitToast = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement toastMessage = waitToast.until(ExpectedConditions.visibilityOfElementLocated(Toast_Message_TM_Conversion));
        Assert.assertTrue(toastMessage.isDisplayed());
        
        
	    // Step 3: Read project code from Excel Row 2
        String projectCode = ExcelURLManager.readProjectCode();
        System.out.println("Project code read from Excel: " + projectCode);
        
        // Step 4: Navigate to Translation Memory
        waitAndClick(Translation_Memory);
        waitAndClick(Translation_Memory_1);

        // Step 5: Search for the project using TM search box
        waitAndSendKeys(Search_TM, projectCode, Keys.ENTER);

        // Step 6: Locate the project name using partial code
        By projectNameLocator = By.xpath("//td[contains(., '" + projectCode + "')]");
        WebElement projectLink = wait.until(ExpectedConditions.visibilityOfElementLocated(projectNameLocator));
        String fullProjectName = projectLink.getText().trim();
        System.out.println("Full Project Name: " + fullProjectName);
        waitAndClick(Source_Lang);
        waitAndClick(Target_Lang);
        waitAndClick(Client_Name);
        waitAndClick(Domain_Name);        
        // Step 7: View TM and verify text
        waitAndClick(View_TM);
        Thread.sleep(1000);
        waitAndClick(Verify_Confirmed_Target);

        boolean isContentPresent = !driver.findElements(Verify_Content_Unavailable).isEmpty();
        Assert.assertFalse(isContentPresent, "❌ Unexpected content found: 'Today I went to the market7.'");
        
        //Verify this String Doesnot Exist
        
    }
}
