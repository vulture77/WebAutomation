package Pages;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProjectOverviewPage {
	
	
	WebDriver driver;
    WebDriverWait wait;

    // Locators
    By ViewTask = By.xpath("(//button[@type='button'])[8]");
    By Publish = By.xpath("//span[text()='Publish']");
    By AttachGlossary = By.xpath("//span[contains(text(), 'Attach Glossary')]");
    By ApplyNMT = By.xpath("//span[contains(text(), 'Apply NMT')]");
    By AttachTM = By.xpath("//span[contains(text(), 'Attach TM')]");
    By Activitylog = By.xpath("//span[contains(text(), 'Activity Log')]");
    By Report = By.xpath("//a[contains(text(),'Report')]");
    By SourceWC = By.xpath("(//div[@class='wc-count'])[1]");
    By UniqueWC = By.xpath("(//div[@class='wc-count'])[2]");
    By EffectiveWC = By.xpath("(//div[@class='wc-count'])[3]");   
    By Yes = By.xpath("//span[text()='Yes']");
    By ViewMore = By.xpath("//div[@class='wc-image']/button");
    By Tar_lang= By.xpath("//tr/td[1]");
    By SourceWc_li= By.xpath("//tr/td[2]");
    By UniqueWC_li = By.xpath("//tr/td[3]");
    By EffectiveWC_li= By.xpath("//tr/td[4]");
    By CloseBtn = By.xpath("//span[contains(text(),'Close')]");    
    By ProjectDetails = By.xpath("//div/li//p");
    By Metrics = By.xpath("//span[text()='Metrics']");
    By SignOff = By.xpath("//span[text()='Sign Off']");
    By ViewAttachedTM = By.xpath("//span[text()='View Attached TM']");
    
    
    
  
    	//Constructor
    	public ProjectOverviewPage(WebDriver driver, WebDriverWait wait) 
    	{
    		this.driver = driver;
    		this.wait = wait;
    	}
    	
    	public int getSourceWC()
    	{
    		String WC = driver.findElement(SourceWC).getText();
    		return Integer.parseInt(WC);
    	}
    	    
    	public int getUniqueWC() 
    	{	
    		String WC = driver.findElement(UniqueWC).getText();
    		return Integer.parseInt(WC);  	
    	}
    	
    	public int getEffectiveWC() 
        {
        	String WC=driver.findElement(EffectiveWC).getText();
        	return Integer.parseInt(WC);  	
        }
    	
    	public void clickViewMore() throws InterruptedException 
    	{	
    		Thread.sleep(2000);
    		driver.findElement(ViewMore).click();
    	}
    	public int[] getSourceWClist() throws InterruptedException 
    	{
    		List<WebElement> wclist = driver.findElements(SourceWc_li);
    		return provideWCList(wclist);
    	}
    	
    	
    	public int[] getUniqueWClist() throws InterruptedException 
    	{
    		List<WebElement> wclist = driver.findElements(UniqueWC_li);
    		return provideWCList(wclist);
    	}
    	
    	
    	public int[] getEffectiveWClist() throws InterruptedException
    	{
    		List<WebElement> wclist = driver.findElements(EffectiveWC_li);
    		return provideWCList(wclist);   	
    	}

    
    	private int[] provideWCList(List<WebElement> l) 
    	{	
    		int[] arr=new int[l.size()];
    		for(int i=0; i<l.size(); i++) 
    		{
    		arr[i]=Integer.parseInt(l.get(i).getText());
    		}
    		return arr;  	
    	}
    	
    	public String[] getProjectDetails() throws InterruptedException 
    	{
    		Thread.sleep(2000);
    		List<WebElement> project = driver.findElements(ProjectDetails);
    		System.out.println(project.size());
    		return returnStringValues(project);	
    	}
    	
    	public void clickPublish() 
    	{
    		driver.findElement(Publish).click();
    		driver.findElement(Yes).click();
    	}
    	
    	public boolean isButtonsareDisplayed()
        {
        	if(driver.findElement(Publish).isDisplayed()== true && driver.findElement(AttachGlossary).isDisplayed()== true &&
        		driver.findElement(ApplyNMT).isDisplayed()== true && driver.findElement(AttachTM).isDisplayed() ==true
        		&& driver.findElement(Activitylog).isDisplayed()== true && driver.findElement(Report).isDisplayed()==true) 
        	{	
        		return true;
        	}
        	else 
        	{
        		return false;		
        	}	
        }
    	
    	public boolean isMetrics_SignOff_AttachedTMDisplayed() throws InterruptedException 
    	{
    		Thread.sleep(2000);
    		if(driver.findElement(Metrics).isDisplayed()==true && driver.findElement(SignOff).isDisplayed()==true &&
    				driver.findElement(ViewAttachedTM).isDisplayed()==true)
    		{
    			return true;
    		}
    		else 
    		{
    			return false;
    		} 		
    	}
    	
    	
    
    	
    
    	public String[] getTargetLangaugeList() throws InterruptedException 
    	{
    		Thread.sleep(2000);
    		List<WebElement> l2 = driver.findElements(Tar_lang);
    	 	return returnStringValues(l2); 	
    	
    	}
    	
    	private String[] returnStringValues(List<WebElement> l2) 
    	{
    		String[] arr=new String[l2.size()];
        	for(int i=0; i<l2.size(); i++) 
        	{
        		arr[i]=l2.get(i).getText();
        	}
        	return arr;
    		
    	}
    	
    
    	public void clickClose()
    	{
    		driver.findElement(CloseBtn).click();
    	}
    	
    
    
    public void clickActivity() throws InterruptedException {
    	Thread.sleep(2000);
    	driver.findElement(Activitylog).click();
    }
    
    public boolean waitingForPublishBtn() throws InterruptedException {
    	 try {
    	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    	        wait.until(ExpectedConditions.visibilityOfElementLocated(Publish));
    	        return true;
    	    } catch (TimeoutException e) {
    	        return false;
    	    }
    	
    }
    public void clickReport() {
    	
    	driver.findElement(Report).click();
    	
    }
    
    
	
	

}
