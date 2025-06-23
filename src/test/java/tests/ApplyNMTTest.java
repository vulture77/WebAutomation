package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import Base.BaseTest;
import Pages.ApplyNMTProjectOverview;
import Pages.ReportPage;
import utils.ExcelReader;
import utils.TestUtil;

public class ApplyNMTTest extends BaseTest {

	@Test
	public void verifyApplyNmt() throws InterruptedException {
		
		ApplyNMTProjectOverview po = new ApplyNMTProjectOverview(driver);
		TestUtil.loginAsUserByRow(driver,1);
		po.applyNMT();
		//po.publish();
		po.report();
		ReportPage rp = new ReportPage(driver, wait);
		rp.reportpage();
		//Assert.assertEquals(rp.isNmtApplied(), "nmt-reverie");
		
	}

}
