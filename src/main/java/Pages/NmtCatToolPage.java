package Pages;

import java.io.IOException;
import java.time.Duration;
import java.util.Random;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import com.mailosaur.MailosaurException;

import utils.ExcelURLManager;
import utils.TestUtil;

public class NmtCatToolPage extends BasePage {

    // LSP Locators
    By Email = By.xpath("(//input[@name='email'])[2]");
    By Password = By.xpath("(//input[@name='password'])[2]");
    By Login = By.xpath("(//span[text()='Sign In'])[2]");
    By Create_Project_Button = By.xpath("//span[text()='Create Project']");
    By Project_Name = By.name("projectName");
    By Target_Language_Dropedown = By.xpath("//div[text()='Select Target Language *']");
    By Choose_Language = By.xpath("//div[text()='Hindi']");
    By Service_Dropdown = By.xpath("//div[@id='select-pipeline']");
    By Choose_Service = By.xpath("//li[normalize-space()='TranslationProofreadingEditing']");
    By Input_Poject_File = By.cssSelector("input[type='file']");
    String path = System.getProperty("user.dir") + "\\ProjectCreationFiles\\Tags_Testing.docx";
    By Advanced_Settings = By.xpath("//span[normalize-space()='Advanced settings']");
    By Cleint_Dropdown = By.xpath("//div[@id='select-clientName']");
    By Choose_Client = By.xpath("//li[normalize-space()='Dexter']");
    By TM_Type = By.xpath("//div[@id='select-tmType']");
    By Select_NO_TM = By.xpath("//li[normalize-space()='No TM']");
    By Create = By.xpath("//span[text()='Create']");
    By Publish = By.xpath("//span[normalize-space()='Publish']");
    By Publish_YES = By.xpath("//span[normalize-space()='Yes']");
    By Add_Team_Member = By.xpath("//a[normalize-space()='Add Team Members']");
    By Email_Area = By.xpath("//input[@type='text']");
    By User_Email_Address = By.xpath("//span[normalize-space()='associate_april3@reverieinc.com']");
    By Wordcount = By.xpath("//label[normalize-space()='Word Count']");
    By Assign_Button = By.xpath("//span[normalize-space()='Assign Users']");
    By Assignment_Successfull_Toaster = By.xpath("//div[contains(text(),'Successfully invited selected users')]");
    By Close_Assignment = By.xpath("//div[@class='project-settings-close']");
    By User_Profile_Icon = By.xpath("//div[text()='c']");
    By Logout = By.xpath("//li[normalize-space()='Log Out']");
    By Logout_YES = By.xpath("//span[normalize-space()='Logout']");

    // Associate Locators
    By My_Projects = By.xpath("//*[text()='My Projects']");
    By All_Projects = By.xpath("//*[text()='All Project']");
    By Search_Assigned_Project = By.cssSelector("input[placeholder='Search Project']");
    By Task_List = By.xpath("//*[text()='Published']");
    By View_Task = By.xpath("//*[text()='View']");
    By Navigate_CatTool = By.xpath("//span[normalize-space()='Start Work']");
    By Agree_Terms = By.xpath("//*[text()='Agree']");
    By Resume = By.xpath("//span[normalize-space()='Resume Work']");
    By Confirm_ALL_Button = By.xpath("//span[normalize-space()='Confirm All']");
    By NMT_Dropdown = By.xpath("//div[@role='button'][normalize-space()='Apply NMT on Full Page']");
    By Apply_ReverieNMT = By.xpath("//li[normalize-space()='Reverie NMT']");
    By NMT_Dropdown_Second_Page = By.xpath("//div[@role='button'][normalize-space()='Reverie NMT']");
    By Toast_Message_NMT_Applied = By.xpath("//div[@class='toast-message'][normalize-space()='Reverie NMT has been applied successfully on Full Page.']");
    By Toast_Message_Credits_Deducted = By.xpath("//div[contains(text(),'credits has been deducted')]");
    By Next_Page = By.xpath("//button[@aria-label='Next Page']");

    // Active Segment specific locators
    By Toast_Message_Active_Segment = By.xpath("//div[contains(text(),'Reverie NMT has been applied successfully.')]");
    By Target_Area = By.xpath("(//div[contains(@class, 'target-textarea')])[1]");
    By Reverie_NMT = By.xpath("//img[@alt='Reverie NMT']");
    By Reverie_NMT_1 = By.xpath("(//img[@alt='Reverie NMT'])[2]");

    // Constructor
    public NmtCatToolPage(WebDriver driver) {
        super(driver);
    }

    public void waitAndClick(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

    public void waitAndSendKeys(By locator, String text) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).sendKeys(text);
    }

    private String generateRandomProjectName() {
        Random random = new Random();
        return "Automation_Project_" + random.nextInt(10000);
    }

    // Common project setup method
    private String setupProjectAndLogin() throws InterruptedException, IOException, MailosaurException {
        String projectName = generateRandomProjectName();

        // ✅ Store last 4 digits in Excel
        ExcelURLManager.writeProjectCode(projectName);

        // ✅ Store URL after project creation later
        TestUtil.loginAsUserByRow(driver, 3);
        waitAndClick(Create_Project_Button);
        waitAndSendKeys(Project_Name, projectName);
        Assert.assertEquals(driver.findElement(Project_Name).getAttribute("value"), projectName);

        waitAndClick(Target_Language_Dropedown);
        waitAndClick(Choose_Language);

        waitAndClick(Service_Dropdown);
        waitAndClick(Choose_Service);

        WebElement fileInput = driver.findElement(Input_Poject_File);
        fileInput.sendKeys(path);

        waitAndClick(Advanced_Settings);
        waitAndClick(Cleint_Dropdown);
        waitAndClick(Choose_Client);
        waitAndClick(TM_Type);
        waitAndClick(Select_NO_TM);

        waitAndClick(Create);
        waitAndClick(Publish);
        waitAndClick(Publish_YES);

        String currentUrl = driver.getCurrentUrl();
        ExcelURLManager.writeURL(currentUrl); // ✅ Write URL to Excel

        waitAndClick(Add_Team_Member);
        waitAndSendKeys(Email_Area, "associate_april3@reverieinc.com");
        waitAndClick(User_Email_Address);

        WebElement wordCountInput = driver.findElement(By.xpath("(//input[@id='wordCount'])[1]"));
        wordCountInput.sendKeys("2000");

        waitAndClick(Assign_Button);
        wait.until(ExpectedConditions.visibilityOfElementLocated(Assignment_Successfull_Toaster));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(Assignment_Successfull_Toaster));

        driver.findElement(By.xpath("(//*[text()='C'])[1]")).click();
        waitAndClick(Logout);
        waitAndClick(Logout_YES);

        return projectName;
    }

    private void loginAsAssociateAndNavigateToProject(String projectName) throws InterruptedException {
        waitAndSendKeys(Email, "associate_april3@reverieinc.com");
        waitAndSendKeys(Password, "Rev@12345");
        waitAndClick(Login);

        waitAndClick(My_Projects);
        waitAndClick(All_Projects);
        waitAndSendKeys(Search_Assigned_Project, projectName);

        waitAndClick(Task_List);
        waitAndClick(View_Task);

        waitAndClick(Navigate_CatTool);
        waitAndClick(Agree_Terms);
        Thread.sleep(1000);
        waitAndClick(Resume);
    }

    public void executeFullPageNMTTest() throws InterruptedException, IOException, MailosaurException {
        String projectName = setupProjectAndLogin();
        loginAsAssociateAndNavigateToProject(projectName);

        waitAndClick(NMT_Dropdown);
        waitAndClick(Apply_ReverieNMT);

        WebDriverWait waitToast = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement toastMessage = waitToast.until(ExpectedConditions.visibilityOfElementLocated(Toast_Message_NMT_Applied));
        Assert.assertTrue(toastMessage.isDisplayed());

        try {
            WebElement toast = waitToast.until(ExpectedConditions.visibilityOfElementLocated(Toast_Message_Credits_Deducted));
            Assert.assertTrue(toast.getText().contains("credits has been deducted"));
        } catch (Exception e) {
            System.out.println("Credit toaster not found: " + e.getMessage());
        }

        wait.until(ExpectedConditions.invisibilityOfElementLocated(Toast_Message_NMT_Applied));
        waitAndClick(Confirm_ALL_Button);
        waitAndClick(Next_Page);
        Thread.sleep(1000);
        waitAndClick(NMT_Dropdown_Second_Page);
        waitAndClick(Apply_ReverieNMT);

        WebElement toastMessage1 = waitToast.until(ExpectedConditions.visibilityOfElementLocated(Toast_Message_NMT_Applied));
        Assert.assertTrue(toastMessage1.isDisplayed());

        try {
            WebElement toast = waitToast.until(ExpectedConditions.visibilityOfElementLocated(Toast_Message_Credits_Deducted));
            Assert.assertTrue(toast.getText().contains("credits has been deducted"));
        } catch (Exception e) {
            System.out.println("Credit toaster not found: " + e.getMessage());
        }
    }

    public void executeActiveSegmentNMTTest() throws InterruptedException, IOException, MailosaurException {
        String projectName = setupProjectAndLogin();
        loginAsAssociateAndNavigateToProject(projectName);

        waitAndClick(Target_Area);
        waitAndClick(Reverie_NMT);

        WebDriverWait waitToast = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement toastMessage = waitToast.until(ExpectedConditions.visibilityOfElementLocated(Toast_Message_Active_Segment));
        Assert.assertTrue(toastMessage.isDisplayed());
        wait.until(ExpectedConditions.invisibilityOfElementLocated(Toast_Message_Active_Segment));

        waitAndClick(Target_Area);
        String targetText = driver.findElement(Target_Area).getText();
        Assert.assertFalse(targetText.trim().isEmpty());

        WebElement currentSegment = wait.until(ExpectedConditions.elementToBeClickable(Target_Area));
        currentSegment.click();
        currentSegment.sendKeys("");
        Thread.sleep(500);

        Actions actions = new Actions(driver);
        actions.click(currentSegment)
                .keyDown(Keys.ALT)
                .sendKeys(Keys.ARROW_DOWN)
                .keyUp(Keys.ALT)
                .build()
                .perform();

        waitAndClick(Reverie_NMT_1);
        WebElement toastMessage_1 = waitToast.until(ExpectedConditions.visibilityOfElementLocated(Toast_Message_Active_Segment));
        Assert.assertTrue(toastMessage_1.isDisplayed());
    }

    @Deprecated
    public void NMTCatTool() throws InterruptedException, IOException, MailosaurException {
        executeFullPageNMTTest();
    }
}
