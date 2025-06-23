package Pages;

import java.io.File;
import java.io.IOException;
import java.util.Random;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.openqa.selenium.interactions.Actions;
import com.mailosaur.MailosaurException;

import utils.TestUtil;

public class ExportTaskOverviewPage extends BasePage{

    // Locators
    By LSP_Email = By.xpath("(//input[@name='email'])[2]");
    By LSP_Password = By.xpath("(//input[@name='password'])[2]");
    By LSP_Login = By.xpath("(//span[text()='Sign In'])[2]");
    By LSP_My_Projects = By.xpath("//span[normalize-space()='My Projects']");
    By LSP_All_Projects = By.xpath("//span[normalize-space()='All Project']");
    By LSP_Project_Status = By.xpath("//div[contains(text(),'All')]");
    By LSP_Published = By.xpath("//li[normalize-space()='Published']");
    By LSP_Signedoff = By.xpath("//li[normalize-space()='Signed Off']");
    By LSP_Published_First_Project = By.xpath("(//span[contains(text(),'translation')])[1]");
    By LSP_Published_Task = By.xpath("(//span[contains(text(),'translation')])[1]");
    By Task_Export_Button = By.cssSelector("button[aria-label='toggle']");
    By Task_Export_Header = By.xpath("//h6[normalize-space()='Task Export']");
    By Folder_Name = By.xpath("(//input[@type='text'])[1]");
    By Pdf_as_Docx = By.xpath("//span[normalize-space()='Export pdf files as docx']");
    By Select_All_Languages_Dropdown = By.xpath("//span[normalize-space()='Select All Languages']");
    By Header_Name = By.xpath("(//input[@type='text'])[3]");
    By Footer_Name = By.xpath("(//input[@type='text'])[4]");
    By Download_Button = By.xpath("//span[normalize-space()='Downloads']");
    By Export_Downloadeded_Folder_Successfull_Toaster = By.xpath("//div[@class='toast-message'][normalize-space()='Your translated files are ready for download. You can download them from the export sidebar.']");
    By Export_Downloaded_Folder = By.xpath("(//button[@type='submit'])[3]");
    By Task_Overview_Header = By.xpath("//strong[normalize-space()='Task Overview']");
    By Language_Dropdown_Placeholder = By.xpath("//div[@class='select__placeholder css-1wa3eu0-placeholder']");

    public ExportTaskOverviewPage(WebDriver driver) {
        super(driver);
    }

    private void waitAndClick(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
        System.out.println("Clicked element: " + locator.toString());
    }

    private void waitAndSendKeys(By locator, String text) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        element.clear();
        element.sendKeys(text);
        System.out.println("Entered text '" + text + "' in: " + locator.toString());
    }

    private WebElement waitForVisibility(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    private boolean isFileDownloaded(String downloadPath, String fileName, int timeoutSeconds) throws InterruptedException {
        File folder = new File(downloadPath);
        File[] files;

        for (int i = 0; i < timeoutSeconds; i++) {
            files = folder.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.getName().equals(fileName) && !file.getName().endsWith(".crdownload")) {
                        return true;
                    }
                }
            }
            Thread.sleep(1000);
        }
        return false;
    }

    private void assertToasterMessage() {
        WebElement toaster = waitForVisibility(Export_Downloadeded_Folder_Successfull_Toaster);
        String actual = toaster.getText();
        String expected = "Your translated files are ready for download. You can download them from the export sidebar.";
        Assert.assertEquals(actual, expected, "Toaster message mismatch!");
        System.out.println("Toaster message verified: " + actual);
        toaster.click();
    }

    public void TaskExport(String downloadPath) throws InterruptedException, IOException, MailosaurException {

        // Published Section
        String folderName = "Dexter_" + new Random().nextInt(10000);
        String fileName = folderName + ".zip";
        
        //user login
        
        TestUtil.loginAsUserByRow(driver,3);

        //Project selection
        waitAndClick(LSP_My_Projects);
        wait.until(ExpectedConditions.visibilityOfElementLocated(LSP_All_Projects));
        waitAndClick(LSP_All_Projects);
        waitAndClick(LSP_Project_Status);
        waitAndClick(LSP_Published);
        Thread.sleep(1000);

        waitAndClick(LSP_Published_First_Project);
        Thread.sleep(1000);
        waitAndClick(LSP_Published_Task);

        WebElement overview = waitForVisibility(Task_Overview_Header);
        Assert.assertTrue(overview.isDisplayed(), "Task Overview not visible");
        Assert.assertEquals(overview.getText(), "Task Overview");
        System.out.println("Verified page header: Task Overview");

        waitAndClick(Task_Export_Button);
        Assert.assertEquals(waitForVisibility(Task_Export_Header).getText(), "Task Export");
        System.out.println("Verified section header: Task Export");

        waitAndSendKeys(Folder_Name, folderName);
        waitAndClick(Pdf_as_Docx);
        waitAndClick(Select_All_Languages_Dropdown);
        new Actions(driver).moveToElement(driver.findElement(Language_Dropdown_Placeholder))
                .click().sendKeys(Keys.ENTER).build().perform();
        System.out.println("Languages selected");

        waitAndSendKeys(Header_Name, "header_name");
        waitAndSendKeys(Footer_Name, "footer_name");
        waitAndClick(Download_Button);
        Thread.sleep(5000);

        assertToasterMessage();
        waitAndClick(Export_Downloaded_Folder);

        boolean downloaded = isFileDownloaded(downloadPath, fileName, 20);
        Assert.assertTrue(downloaded, "Download failed for: " + fileName);
        System.out.println("Download verified: " + fileName);

        // Signed Off Section
        String folderName2 = "Dexter_" + new Random().nextInt(10000);
        String fileName2 = folderName2 + ".zip";

        waitAndClick(LSP_All_Projects);
        waitAndClick(LSP_Project_Status);
        waitAndClick(LSP_Signedoff);
        Thread.sleep(1000);
        waitAndClick(LSP_Published_First_Project);
        Thread.sleep(1000);
        waitAndClick(LSP_Published_Task);

        WebElement overview2 = waitForVisibility(Task_Overview_Header);
        Assert.assertTrue(overview2.isDisplayed(), "Task Overview not visible");
        Assert.assertEquals(overview2.getText(), "Task Overview");
        System.out.println("Verified page header: Task Overview (Signed Off)");

        waitAndClick(Task_Export_Button);
        Assert.assertEquals(waitForVisibility(Task_Export_Header).getText(), "Task Export");
        System.out.println("Verified section header: Task Export (Signed Off)");

        waitAndSendKeys(Folder_Name, folderName2);
        waitAndClick(Pdf_as_Docx);
        waitAndClick(Select_All_Languages_Dropdown);
        new Actions(driver).moveToElement(driver.findElement(Language_Dropdown_Placeholder))
                .click().sendKeys(Keys.ENTER).build().perform();
        System.out.println("Languages selected (Signed Off)");

        waitAndSendKeys(Header_Name, "header_name");
        waitAndSendKeys(Footer_Name, "footer_name");
        waitAndClick(Download_Button);
        Thread.sleep(5000);

        assertToasterMessage();
        waitAndClick(Export_Downloaded_Folder);

        boolean downloaded2 = isFileDownloaded(downloadPath, fileName2, 20);
        Assert.assertTrue(downloaded2, "Download failed for: " + fileName2);
        System.out.println("Download verified: " + fileName2);
    }
}
