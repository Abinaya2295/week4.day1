package week4.day1;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class MergeContact {

	public static void main(String[] args) throws InterruptedException {
		//Launch URL "http://leaftaps.com/opentaps/control/login"
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.get("http://leaftaps.com/opentaps/control/login");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		
		//Enter UserName and Password Using Id Locator
		driver.findElement(By.id("username")).sendKeys("democsr");
		driver.findElement(By.id("password")).sendKeys("crmsfa");
		
		//Click on Login Button using Class Locator
		driver.findElement(By.className("decorativeSubmit")).click();
		
		//Click on CRM/SFA Link
		driver.findElement(By.linkText("CRM/SFA")).click();
		
		//Click on contacts Button
		driver.findElement(By.linkText("Contacts")).click();
		
		//Click on Merge Contacts using Xpath Locator
		driver.findElement(By.xpath("//a[text()='Merge Contacts']")).click();
		
		//Click on Widget of From Contact
		driver.findElement(By.xpath("(//input[@id='partyIdFrom']/following::img)[1]")).click();
		
		//Click on First Resulting Contact
		Set<String> windowHandles = driver.getWindowHandles();
		List<String> getHandles = new ArrayList<String>(windowHandles);
		System.out.println(getHandles);
		driver.switchTo().window(getHandles.get(1));
		driver.manage().window().maximize();
		Thread.sleep(2000);
		driver.findElement(By.xpath("(//td[@class='x-grid3-col x-grid3-cell x-grid3-td-partyId x-grid3-cell-first ']//a)[1]")).click();
		
		//Click on Widget of To Contact
		driver.switchTo().window(getHandles.get(0));
		driver.findElement(By.xpath("(//input[@id='partyIdTo']/following::img)[1]")).click();
		
		//Click on Second Resulting Contact
		Set<String> windowHandles2 = driver.getWindowHandles();
		List<String> winHandles2 = new ArrayList<String>(windowHandles2);
		System.out.println(winHandles2);
		
		driver.switchTo().window(winHandles2.get(1));
		driver.manage().window().maximize();
		Thread.sleep(2000);
		driver.findElement(By.xpath("(//td[@class='x-grid3-col x-grid3-cell x-grid3-td-partyId x-grid3-cell-first ']//a)[2]")).click();
		
		//Click on Merge button using Xpath Locator
		driver.switchTo().window(winHandles2.get(0));
		driver.findElement(By.xpath("//td/a[text()='Merge']")).click();
		
		
		//Accept the Alert
		Alert alert = driver.switchTo().alert();
		Thread.sleep(2000);
		alert.accept();
		
		//Verify the title of the page
		String title = driver.getTitle();
		System.out.println(title);
		Thread.sleep(2000);
		driver.close();
	}

}
