package tests;

import java.io.IOException;
import org.testng.annotations.Test;
import com.mailosaur.MailosaurException;
import Base.BaseTest;
import Pages.ConvertToTMPage;

public class ConvertTM extends BaseTest {
    
    @Test
    public void convertToTMTest() throws InterruptedException, IOException, MailosaurException {
        ConvertToTMPage convertPage = new ConvertToTMPage(driver);
        convertPage.AccessprojectandConverttoTM();
    }
}