package Pages;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProjectCreation {
	
	WebDriver driver;
    WebDriverWait wait;

    // Locators
    By CreateProject = By.xpath("(//a[@role='button'])[2]");
    By ProjectName = By.xpath("//input[@name='projectName']");
    By SourceLangauge = By.xpath("//div[@id='select-sourceLanguage']");
    By TargetLang = By.xpath("//div[contains(@class, 'select__placeholder')]");
    By TargetLangList = By.xpath("//div[contains(@id, 'react-select')]");
    By ServiceType= By.xpath("//div[@id='select-pipeline']");
    By ServiceTypelist=By.xpath("//ul/li[@role='option']");
    By UploadSorce = By.xpath("(//input[@type='file'])[1]");
    By AdvancedSett = By.xpath("//span[contains(text(), 'Advanced settings')]");
    By ClientName = By.xpath("//div[@id='select-clientName']");
    By ClientList = By.xpath("//ul/li[@role='option']");
    By ClientPoc= By.xpath("//div[@id='select-clientPoc']");
    By ClientPoclist = By.xpath("//ul/li[@role='option']");
    By DM = By.xpath("//div[@id='select-clientRm']");
    By DMList= By.xpath("//ul/li[@role='option']");
    By TMType = By.id("select-tmType");
    By Tmlist = By.xpath("//ul/li[@role='option']");
    By DeliveryDate = By.xpath("//input[@type='text' and @readonly][1]");
    By SelectAllIndustryTypeCheck = By.xpath("(//input[@type='checkbox'])[3]");
    By Industrydropdwn= By.xpath("(//div[@class='select__control css-3zodhl-control'])[2]");
    By IndustryList = By.xpath("//div[contains(@class, 'select__option')]");
    By PM = By.id("select-allocatedTo");
    By PMlist = By.xpath("//ul/li[@role='option']");
    By ProjectDes=By.name("projectDescription");
    By Instruction = By.xpath("(//input[@type='file'])[2]");
    By CreateBtn = By.xpath("//button[@type='submit']");
    
    
    
    public ProjectCreation(WebDriver driver, WebDriverWait wait) 
    {
        this.driver = driver;
        this.wait = wait;
    }
    
    public void clickCreateProject() throws InterruptedException, AWTException 
    {
    	Thread.sleep(2000);
    	wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(CreateProject))).click();
    	Robot robot = new Robot();

        // Optional delay to observe or prepare browser
        robot.delay(1000);

        // Hold Ctrl + Shift
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_SHIFT);

        // Press Minus (-)
        robot.keyPress(KeyEvent.VK_MINUS);
        robot.keyRelease(KeyEvent.VK_MINUS);
        robot.keyPress(KeyEvent.VK_MINUS);
        robot.keyRelease(KeyEvent.VK_MINUS);

        // Release Shift + Ctrl
        robot.keyRelease(KeyEvent.VK_SHIFT);
        robot.keyRelease(KeyEvent.VK_CONTROL);
    	
    }
    
    public void setProjectName() throws InterruptedException 
    {	
    	Thread.sleep(2000);
    	wait.until(ExpectedConditions.visibilityOfElementLocated(ProjectName)).sendKeys("Test Mnop");	
    }
    
    public void selectTargetLang(int NoOfLang) 
    {
    	driver.findElement(TargetLang).click();
    	List<WebElement> langlist = driver.findElements(TargetLangList);
    	for(int i=0; i<NoOfLang; i++) 
    	{
    		 langlist.get(i).click();
    	}
    	
    }
    public void selectService(int indexNumber) 
    {
    	driver.findElement(ServiceType).click();
    	List<WebElement> servicelist = driver.findElements(ServiceTypelist);
    	for(int i=0; i<servicelist.size(); i++) {
    		if(i==indexNumber)
    		{
    			servicelist.get(i).click();
    			break;
    		}
    	}
    	
    }
    
    public void uploadFile(String Path1, String Path2) throws InterruptedException 
    {
    	driver.findElement(UploadSorce).sendKeys(Path1);
    	Thread.sleep(1000);
    	driver.findElement(UploadSorce).sendKeys(Path2);
    }
    
    public void clickAdvancedSetting() throws InterruptedException 
    {
    	Thread.sleep(2000);
    	JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(AdvancedSett));
        driver.findElement(AdvancedSett).click();
	}
    
    public void selectClient(String Name) throws InterruptedException 
    { 	Thread.sleep(2000);
    		//Removed this code to Handle the different screen size
    	//JavascriptExecutor js = (JavascriptExecutor) driver;
        //js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(ClientName));
    	driver.findElement(ClientName).click();
    	Thread.sleep(2000);
    	List<WebElement> listofClient = driver.findElements(ClientList);
    	for(WebElement x:listofClient )
    	{
    		if(x.getText().equalsIgnoreCase(Name)) 
    		{
    			x.click();
    			break;		
    		}
    	}
    	
    }
    
    public void selectClientPOC(String POC) throws InterruptedException 
    {
    	driver.findElement(ClientPoc).click();
    	Thread.sleep(2000);
    	List<WebElement> listofClient = driver.findElements(ClientPoclist);
    	for(WebElement x:listofClient ) 
    	{
    		if(x.getText().equalsIgnoreCase(POC))
    		{
    			x.click();
    			break;
    			
    		}
    	}
    }
    
    public void selectDM(String DMName) throws InterruptedException 
    {
    	driver.findElement(DM).click();
    	Thread.sleep(2000);
    	List<WebElement> listofDM = driver.findElements(DMList);
 
    	for(WebElement x:listofDM ) {
    		if(x.getText().equalsIgnoreCase(DMName)) 
    		{
    			x.click();
    			break;
    			
    		}
    	
    }
    }
    
    public void selectIndustry(int count) throws InterruptedException 
    {
    	//UnSelect the Industry Type
    	driver.findElement(SelectAllIndustryTypeCheck).click(); 	
        driver.findElement(Industrydropdwn).click();
    	Thread.sleep(2000);
    	List<WebElement> listOfIndustry = driver.findElements(IndustryList);
    	for(int i=0; i<count; i++) 
    	{
    		listOfIndustry.get(i).click();
    	}
    	
    	
    }
    
    public void selectPM(String NamePM) throws InterruptedException 
    {	
    	Thread.sleep(2000);
    	JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(SelectAllIndustryTypeCheck));
        driver.findElement(PM).click(); 	 	
    	Thread.sleep(2000);
    	List<WebElement> pmli = driver.findElements(PMlist);
    	for(WebElement pm: pmli ) 
    	{
    		if(pm.getText().equalsIgnoreCase(NamePM)) 
    		{
    			pm.click();
    			break;
    		}
    	}
    	
    }
    
    public void selectTM(String TypeofTM) throws InterruptedException 
    {
    	driver.findElement(TMType).click();
    	Thread.sleep(2000);
    	List<WebElement> listofTM = driver.findElements(Tmlist);
    	for(WebElement x:listofTM )
    	{
    		if(x.getText().equals(TypeofTM)) 
    		{
    			x.click();
    			break;
    			
    		}	
    }	
    }
    
    public void setProjectDescription(String Desc) 
    {
    	driver.findElement(ProjectDes).sendKeys(Desc);
    }
    
    public void uploadInstruction(String path) 
    {
    	driver.findElement(Instruction).sendKeys(path);  	
    }
    	
    	
    
    
    
    public void clickCreateBtn() throws AWTException 
    {
    	
    	JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(CreateBtn));
        driver.findElement(CreateBtn).click();	
        
    }
    
    
    public String getDeliveryDate() {
    	
    	return driver.findElement(DeliveryDate).getAttribute("value");
    	
    }
    
    
    
    
    
   
    
    }
    
    
    
   
    
   
    
    
    
    
    

    

    
    
        
    
    
   
    
    
    
    
    


