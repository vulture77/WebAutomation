package tests;

import java.io.IOException;
import org.testng.annotations.Test;

import com.mailosaur.MailosaurException;

import Base.BaseTest;
import Pages.RegistrationPage;

public class UserCreation extends BaseTest{
	
	
	@Test
	public void newUserRegistrationTest() throws InterruptedException, IOException, MailosaurException
	{
	
	RegistrationPage page = new RegistrationPage(driver);
	page.userRegistration();
		
	}

}