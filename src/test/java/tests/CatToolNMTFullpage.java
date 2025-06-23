package tests;

import java.io.IOException;
import org.testng.annotations.Test;
import com.mailosaur.MailosaurException;
import Base.BaseTest;
import Pages.NmtCatToolPage;

public class CatToolNMTFullpage extends BaseTest {
    
    @Test
    public void CaTToolNMTFullpageTest() throws InterruptedException, IOException, MailosaurException {
        NmtCatToolPage page = new NmtCatToolPage(driver);
        page.executeFullPageNMTTest();
    }
}