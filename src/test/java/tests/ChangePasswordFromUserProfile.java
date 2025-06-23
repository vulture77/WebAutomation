package tests;

import java.time.Duration;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import Base.BaseTest;
import Pages.Changepassword_myprofile;


public class ChangePasswordFromUserProfile extends BaseTest{
	
	@Test
	public void ChangePasswordValidation() throws InterruptedException {
	
	Changepassword_myprofile page = new Changepassword_myprofile(driver);
	page.Change_password();
		
	}


}