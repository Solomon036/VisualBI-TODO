package utils;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import configs.Environment_Configuration;
import object_repos.Activity_objectrepo;

public class Webinterface extends Environment_Configuration
{
	
	WebDriver driver;
	Activity_objectrepo element = new Activity_objectrepo();
	
	public void initiate_browser()
	{
		
		
		System.setProperty("webdriver.chrome.driver",".\\bin\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get(URL);	
	}
	
	public void click(String element) throws InterruptedException
	{
		try
		{
			driver.findElement(By.xpath(element)).click();
		}
		catch(ElementClickInterceptedException e)
		{
			scroll_to_element(element);
			driver.findElement(By.xpath(element)).click();
		}
	}
	
		
	public boolean isElementPresent(String xpath)
	{
		try
		{
			return driver.findElement(By.xpath(xpath)).isDisplayed();
		}
		catch(NoSuchElementException e)
		{
			System.out.println("No element is displayed");
			return false;
		}
	}
	
	
	public void enter(String xpath, String input) 
	{
		WebElement element=driver.findElement(By.xpath(xpath));
		try
		{			
			element.click();
			element.clear();
			element.sendKeys(input);
		}
		catch(ElementClickInterceptedException e)
		{
			element.click();
			element.clear();
			element.sendKeys(input);
		}
	}
	
	
	public void page_refresh() throws Exception
	{
		driver.navigate().refresh();   
	}
	
	
	public void scroll_to_element(String element_path)
	{
		JavascriptExecutor js = (JavascriptExecutor) driver;

        //Find element by link text and store in variable "Element"        		
        WebElement Element = driver.findElement(By.xpath(element_path));

        //This will scroll the page till the element is found		
        js.executeScript("arguments[0].scrollIntoView();", Element);
	}

	public String get_value(String xpath)
	{
		System.out.println(driver.findElement(By.xpath(xpath)).getText());
		return driver.findElement(By.xpath(xpath)).getText();
	}
	
	
	public ArrayList<String> get_list_of_text(String Weblement_xpath)
	{
		ArrayList<String> list_of_items = new ArrayList<String>();
		List<WebElement> elements = driver.findElements(By.xpath(Weblement_xpath));
		for (WebElement webElement : elements) {
			list_of_items.add(webElement.getText());
        }
		return list_of_items;
	}
	
	public void hover_and_click_element(String element_xpath,String cross_xpath)
	{
		Actions actions = new Actions(driver);
		WebElement element = driver.findElement(By.xpath(element_xpath));
		actions.moveToElement(element).build().perform();
		
		WebElement cross_button = driver.findElement(By.xpath(cross_xpath));
		actions.moveToElement(cross_button).click().build().perform();
	}
	
	
	public File takeScreenshot()
	{
		TakesScreenshot scrShot =((TakesScreenshot)driver);
        File sourcePath = scrShot.getScreenshotAs(OutputType.FILE);
        return sourcePath;
	}
	
	
	public void closeBrowser()
	{
		driver.close();
	}
	

}
