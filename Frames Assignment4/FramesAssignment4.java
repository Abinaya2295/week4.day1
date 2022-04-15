package week4.day1;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class FramesAssignment4 {

	public static void main(String[] args) throws IOException, InterruptedException {
		////Open the browser
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.get("http://leafground.com/pages/frame.html");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		//switch to frame
		driver.switchTo().frame(0);
		driver.findElement(By.xpath("//button[@id='Click']")).click();
		//Take a screenshot
		File source = driver.getScreenshotAs(OutputType.FILE);
		//creating the physical file
		File destination = new File("./snaps/screenshot1.png");
		// - ./ refers to project folder
		//copy source to destination
		FileUtils.copyFile(source, destination);
		Thread.sleep(2000);
		//come out of frame
		driver.switchTo().defaultContent();			
		//Find total number of Frames
		List<WebElement> findFrame = driver.findElements(By.tagName("iframe"));
		System.out.println("Total Number of Frames : "+findFrame.size());
		
	}

}
