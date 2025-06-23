package tests;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.mailosaur.MailosaurException;

import Base.BaseTest;
import Pages.ExportProjectOverviewPage;

public class ExportProject extends BaseTest {
    
    @Test
    public void ExportProjectOverviewPageValidation() throws InterruptedException, IOException, MailosaurException {
        
        ExportProjectOverviewPage page = new ExportProjectOverviewPage(driver);
        
        // Use system-independent download path
        String downloadPath = System.getProperty("user.home") + "\\Downloads";
        
        // Call the method with the required parameter
        page.ProjectExport(downloadPath);
    }
}