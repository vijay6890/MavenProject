package Week4.Day1;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LearnAlert {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stubb
		
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver=new ChromeDriver();
		driver.get("http://leafground.com/pages/Alert.html");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		driver.findElement(By.xpath("//button[text()='Alert Box']")).click();
		Alert alert = driver.switchTo().alert();
		String text = alert.getText();
		System.out.println("Text Present in Simple"+text);
		Thread.sleep(2000);
		alert.accept();
		driver.findElement(By.xpath("//button[text()='Confirm Box']")).click();
		String text2 = alert.getText();
		System.out.println("Text Present inConfirmation"+text2);
		Thread.sleep(2000);
		alert.accept();
		String text4 = driver.findElement(By.xpath("//p[contains (text(),'OK')]")).getText();
		if(text4.contains("OK"))
		{
			System.out.println("Ok button clicked");
		}
		driver.findElement(By.xpath("//button[text()='Prompt Box']")).click();
		   String text3 = alert.getText();
		   System.out.println(text3);
		   alert.sendKeys("veeravel");
			Thread.sleep(2000);
		   alert.dismiss();
		
		        
		

	}

}
