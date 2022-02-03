package Week4.Day1;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WindowHandling {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver=new  ChromeDriver();
		driver.get("https://www.irctc.co.in/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.findElement(By.xpath("//button[text()='OK']")).click();
		driver.findElement(By.xpath("//a[contains(text(),'FLIGHTS')]")).click();
		 Set<String> windowHandles = driver.getWindowHandles();
		List<String> listhand=new ArrayList<String>(windowHandles);
		
		driver.switchTo().window(listhand.get(1));
		String title = driver.getTitle();
		System.out.println("Window 2 :"+title);
		String text = driver.findElement(By.xpath("//a[contains(text(),' flights@irctc.co.in')]")).getText();
		System.out.println("Email id :"+text);
		driver.switchTo().window(listhand.get(0));
		driver.close();

	}

}
