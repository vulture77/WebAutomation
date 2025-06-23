package tests;

import java.io.IOException;
import org.testng.annotations.Test;
import com.mailosaur.MailosaurException;
import Base.BaseTest;
import Pages.NmtCatToolPage;

public class CatToolNMTActiveSegment extends BaseTest {
    
    @Test
    public void CaTToolNMTActiveSegmentTest() throws InterruptedException, IOException, MailosaurException {
        NmtCatToolPage page = new NmtCatToolPage(driver);
        page.executeActiveSegmentNMTTest();
    }
}