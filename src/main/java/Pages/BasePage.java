package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.LoggerUtil;
import static org.testng.Assert.assertTrue;
import java.time.Duration;


public class BasePage {
	protected WebDriver driver;
    protected WebDriverWait wait;

	public BasePage(WebDriver driver) {
		this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20)); //
	}
	
	public String getToastMessageText(By toastLocator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(toastLocator));
        return driver.findElement(toastLocator).getText();
    }
	
	public void clickWithJS(WebElement element) 
	{
	    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
	}
	
	public void scrollwithJS(WebElement element)
	
	{
	    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center', behavior: 'instant'});",element);

	}
	
	public void assertTrueCheck(WebElement element , String label)
	{
		assertTrue(element.isDisplayed());
		String message= "✅"+ label + " is displayed";
		LoggerUtil.logPass(message);
	  
	}
}
