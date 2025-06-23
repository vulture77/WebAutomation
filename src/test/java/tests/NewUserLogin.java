package tests;

import org.testng.annotations.Test;
import Base.BaseTest;
import Pages.NewUserLoginPage;

public class NewUserLogin extends BaseTest {
	
@Test

public void newUserLoginTest() throws Exception
{
  NewUserLoginPage login = new NewUserLoginPage(driver);
  login.LoginPageValidation();
}

}