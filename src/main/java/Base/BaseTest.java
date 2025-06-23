package Base;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import listeners.ExtentTestListener; 

@Listeners(ExtentTestListener.class)  // ✅ This is key
public class BaseTest {


    protected WebDriver driver;
    protected WebDriverWait wait;

    @BeforeMethod
    public void SetUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        options.addArguments("--ignore-certificate-errors");  // <-- Ignore SSL certificate errors
        options.addArguments("--allow-insecure-localhost");   // <-- Allow insecure localhost

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get("https://staging.prabandhak.in/#/login");
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    @AfterMethod
    public void TearDown() {
        if (driver != null) 
        {
            driver.quit();
        }
            
        }
   
    WebElement waitforElement(By locator, int timeout) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public String getToastMessageText(By toastLocator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(toastLocator));
        return driver.findElement(toastLocator).getText();
    }
	

}
