package week4.day1;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class FramesAssignment3 {

	public static void main(String[] args) throws InterruptedException {
		//Open the browser
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.get("https://chercher.tech/practice/frames-example-selenium-webdriver");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		String header = driver.findElement(By.tagName("h1")).getText();
		System.out.println(header);
		String gettitle = driver.findElement(By.xpath("//label/span")).getText();
		System.out.println(gettitle);
		//Switch to frames
		driver.switchTo().frame("frame1");
		driver.findElement(By.xpath("//b[@id='topic']/following-sibling::input")).sendKeys("Friendly Topic");
		//switch to inner frame
		driver.switchTo().frame("frame3");
		boolean selected = driver.findElement(By.xpath("//b[text()='Inner Frame Check box :']/following-sibling::input")).isSelected();
		System.out.println(selected);
		if(!selected)
		{
			driver.findElement(By.xpath("//b[text()='Inner Frame Check box :']/following-sibling::input")).click();
		}
		else
		{
			System.out.println("checkbox is already selected");
		}
		//come out of frame and enter into new frame
		driver.switchTo().defaultContent();
		driver.switchTo().frame("frame2");
		WebElement dropdown = driver.findElement(By.id("animals"));
		Select options = new Select(dropdown);
		options.selectByVisibleText("Big Baby Cat");
		Thread.sleep(2000);
		driver.close();
			
	}

}
