package week4.day1;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ServiceNowFrame {

	public static void main(String[] args) throws InterruptedException {
		//Load ServiceNow application URL
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.get("https://dev121343.service-now.com");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		//switch into frame
		driver.switchTo().frame(0);
		//Enter username (Check for frame before entering the username)
		driver.findElement(By.id("user_name")).sendKeys("admin");
		//Enter password 
		driver.findElement(By.id("user_password")).sendKeys("India@123");
		//Click Login
		driver.findElement(By.xpath("//button[text()='Log in']")).click();
		//Search “incident “ Filter Navigator
		driver.findElement(By.id("filter")).sendKeys("incident");		
		//Click “All”
		driver.findElement(By.xpath("(//div[text()='All'][@class='sn-widget-list-title'])[2]")).click();
		//Click New button
		driver.switchTo().frame(0);
		driver.findElement(By.xpath("//button[text()='New'][@id='sysverb_new']")).click();
		//Select a value for Caller
		driver.findElement(By.xpath("//span[@class='icon icon-search']")).click();
		//moving to next window, select item in second window
		Set<String> windowHandles = driver.getWindowHandles();
		List<String> switchWindow = new ArrayList<String>(windowHandles);
		driver.switchTo().window(switchWindow.get(1));
		driver.manage().window().maximize();
		//select first recurring result
		driver.findElement(By.xpath("//table/tbody/tr[1]/td[3]/a")).click();
		//Again switch to main window
		driver.switchTo().window(switchWindow.get(0));
		driver.switchTo().frame(0);
		//Enter value for short_description
		driver.findElement(By.xpath("//input[@id='incident.short_description'][@class='form-control']")).sendKeys("Short Description");
		//Read the incident number and save it a variable
		WebElement readIncident = driver.findElement(By.id("incident.number"));
		String attribute = readIncident.getAttribute("Value");
		System.out.println(attribute);		
		//Click on Submit button
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[@type='submit']")).click();		
		//Search the same incident number in the next search screen as below
		driver.findElement(By.xpath("//input[@class='form-control']")).sendKeys(attribute,Keys.ENTER);	
		//Verify the incident is created successful.
		String text = driver.findElement(By.xpath("//a[@class='linked formlink']")).getText();
		if(text.contains(attribute))
		{
			System.out.println("Incident is created successfully");
		}
		else
		{
			System.out.println("Incident is not created successfully"); 
		}
	}

}
