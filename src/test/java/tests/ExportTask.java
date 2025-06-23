package tests;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.mailosaur.MailosaurException;

import Base.BaseTest;
import Pages.ExportTaskOverviewPage;

public class ExportTask extends BaseTest {

    @Test
    public void ExportTaskOverviewPageValidation() throws InterruptedException, IOException, MailosaurException {

        // Instantiate page object
        ExportTaskOverviewPage page = new ExportTaskOverviewPage(driver);

        // Define download path (Windows user Downloads directory)
        String downloadPath = System.getProperty("user.home") + "\\Downloads";

        // Call the method with downloadPath
        page.TaskExport(downloadPath);
    }
}
