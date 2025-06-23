package utils;

import Pages.LoginPage;
import org.openqa.selenium.WebDriver;

public class TestUtil {


    public static void loginAsUserByRow(WebDriver driver, int rowNum) {
        String[] credentials = ExcelReader.getUserCredentialsByRow(
                "test-data/GeneratedEmails.xlsx", "UserCredentials", rowNum
        );
        LoginPage loginPage = new LoginPage(driver);
        loginPage.existingUserLogin(credentials[0], credentials[1]);
    }

}