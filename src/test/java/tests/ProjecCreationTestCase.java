
package tests;

import java.awt.AWTException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import Base.BaseTest;
import Pages.ProjectCreation;
import Pages.ProjectOverviewPage;
import Pages.ReportPage;
import utils.ExcelUtils;
import utils.TestUtil;

public class ProjecCreationTestCase extends BaseTest {
	
	// Locators
    By Email = By.xpath("(//input[@name='email'])[2]");
    By Password = By.xpath("(//input[@name='password'])[2]");
    By SignIn = By.xpath("(//button[@type='submit'])[2]");
    
	public static String projectId;

	SoftAssert softAssert;
	
	@Test
	void testProjectCreation() throws InterruptedException, AWTException 
	{
//		wait.until(ExpectedConditions.visibilityOf(driver.findElement(Email))).sendKeys("sainath+lsp3@reverieinc.com");
//    	wait.until(ExpectedConditions.visibilityOf(driver.findElement(Password))).sendKeys("MischiefManaged!");
//    	driver.findElement(SignIn).click();
        
		TestUtil.loginAsUserByRow(driver,1);  // or 2, 3 etc., based on which row of Excel you want
		ProjectCreation PC=new ProjectCreation(driver, new WebDriverWait(driver, Duration.ofSeconds(10)));
		softAssert = new SoftAssert();
		PC.clickCreateProject();
		PC.setProjectName();
		PC.selectTargetLang(5);
		PC.selectService(4);
		String path1 = System.getProperty("user.dir")+"\\ProjectCreationFiles\\avk_sansad_rs_Bulletin Part-I_14.12.2023_edited.docx";
		String path2 = System.getProperty("user.dir")+"\\ProjectCreationFiles\\Different format.pdf";
		String path3 = System.getProperty("user.dir")+"\\ProjectCreationFiles\\InstructionFile.docx";
		PC.uploadFile(path1, path2);	
		PC.clickAdvancedSetting();
		PC.selectClient("Testing clien");
		PC.selectClientPOC("Sai");
		PC.selectDM("Sai");
		String Deliverydate = PC.getDeliveryDate();
		PC.selectIndustry(3);
		PC.selectPM("Sai");	
		PC.selectTM("Global TM");
		PC.setProjectDescription("Testing");
		PC.uploadInstruction(path3);
		PC.clickCreateBtn();
		String currentDateTime = getCurrentDateTime();
		Thread.sleep(20000);
		ProjectOverviewPage overview=new ProjectOverviewPage(driver, new WebDriverWait(driver, Duration.ofSeconds(2)));	
		projectId=driver.getCurrentUrl();
		ExcelUtils.appendEmailIdToExcel("GeneratedEmails.xlsx","ProjectID",projectId);
		softAssert.assertEquals(overview.getSourceWC(), 6514, "Incorrect Source WordCount!");
        softAssert.assertEquals(overview.getUniqueWC(), 5205, "Incorrect Unique WordCount!");
        softAssert.assertEquals(overview.getEffectiveWC(), 25999, "Incorrect Effective WordCount!");
        overview.clickViewMore();
        softAssert.assertEquals(overview.getTargetLangaugeList(), new String[] {"Hindi", "Gujarati", "Marathi", "Odia", "Assamese"}, "Mismatch in the Target Langauge List");
        softAssert.assertEquals(overview.getSourceWClist(), new int[] {6514, 6514, 6514, 6514, 6514}, "Mismatch in the SourceWC List");
        softAssert.assertEquals(overview.getUniqueWClist(), new int[] {5205, 5205, 5205, 5205, 5205}, "Mismatch in the UniqueWClist List");
        softAssert.assertEquals(overview.getEffectiveWClist(), new int[] {5179, 5205, 5205, 5205, 5205}, "Mismatch in the EffectiveWClist List");
		overview.clickClose();	
		//String[] ActualDetails = overview.getProjectDetails();
		//String[] expectedDetails= projectDetailsExpectedVales(currentDateTime, Deliverydate);
		//compareProjectDetails(ActualDetails,expectedDetails);
		
		softAssert.assertTrue(overview.isButtonsareDisplayed(),"Few Buttons are not displayed" );
		overview.clickPublish();
		softAssert.assertTrue(overview.isMetrics_SignOff_AttachedTMDisplayed(),"Few Buttons are not displayed");
		overview.clickReport();
		
		ReportPage rp = new ReportPage(driver, new WebDriverWait(driver, Duration.ofSeconds(2)));
		rp.selectingSerivce("Tm");
		rp.reportpage();
		System.out.println(rp.isTMApplied());
		
		
		softAssert.assertAll();	
		
		try 
		{
			softAssert.assertAll();
		} 
		catch (AssertionError e) 
		{
        System.out.println("❌ Soft Assertion(s) failed:\n" + e.getMessage());
        throw e;
		}
	}

	
	
	private void compareProjectDetails(String[] lis, String[] expected) {
	    if (lis.length == expected.length) {
	        for (int i = 0; i < lis.length; i++) {
	            if (i == 1) {
	                String dateStr1 = lis[1];
	                String dateStr2 = expected[1];

	                try {
	                    LocalDateTime dateTime1 = parseFlexibleDate(dateStr1);
	                    LocalDateTime dateTime2 = parseFlexibleDate(dateStr2);

	                    if (dateTime1.isEqual(dateTime2)) {
	                        softAssert.assertEquals(dateTime1, dateTime2);
	                    } else {
	                        softAssert.fail("Delivery Date is not matching with the date selected while creating a project");
	                    }
	                } catch (IllegalArgumentException e) {
	                    softAssert.fail("Date parsing failed: " + e.getMessage());
	                }

	            } else {
	                softAssert.assertEquals(lis[i], expected[i]);
	            }
	        }
	    } else {
	        softAssert.fail("Few of the project details are not displayed");
	    }
	}

	// Util method to handle dynamic date formats
	private LocalDateTime parseFlexibleDate(String dateStr) {
	    DateTimeFormatter[] formatters = new DateTimeFormatter[]{
	        DateTimeFormatter.ofPattern("MMM d, yyyy h:mm a", Locale.ENGLISH),
	        DateTimeFormatter.ofPattern("MMM dd, yyyy h:mm a", Locale.ENGLISH),
	        DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm a", Locale.ENGLISH),
	        DateTimeFormatter.ofPattern("dd-MM-yyyy h:mm a", Locale.ENGLISH)
	    };

	    for (DateTimeFormatter formatter : formatters) {
	        try {
	            return LocalDateTime.parse(dateStr, formatter);
	        } catch (DateTimeParseException ignored) {
	            // Try next format
	        }
	    }

	    throw new IllegalArgumentException("Unrecognized date format: " + dateStr);
	}



		private String[] projectDetailsExpectedVales(String Created, String Delivery) {
		String[] val = 
			{
			"Test Mnop", Delivery ,  Created,             
		    "Translation Proofreading Editing",
		    "Translation",                      
		    "sainath+lsp3@reverieinc.com",  
		    "testing clien",                  
		    "sainath.clinet@gmail.com",        
		    "sainath+lsp3@reverieinc.com",      
		    "Testing",                          
		    "Enabled"                        
		};
		
		return val;
		
		
	}
	

	private String getCurrentDateTime() 
	{
		LocalDateTime now = LocalDateTime.now();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd, yyyy h:mm a", Locale.ENGLISH);

        return now.format(formatter);
        }
	
	
}

	

