package Week2.Day2;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class PlayWithHyperLinks {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver=new ChromeDriver();
		driver.get("http://leafground.com/pages/Link.html");
		driver.manage().window().maximize();
		driver.findElement(By.xpath("(//a[text()='Go to Home Page'])[1]")).click();
		driver.navigate().to("http://leafground.com/pages/Link.html");
		String attribute = driver.findElement(By.linkText("Find where am supposed to go without clicking me?")).getAttribute("href");
		System.out.println(attribute);
		driver.findElement(By.linkText("Verify am I broken?")).click();
	    System.out.println(driver.getTitle());
		Thread.sleep(3000);
		driver.navigate().to("http://leafground.com/pages/Link.html");
		Thread.sleep(3000);
		driver.findElement(By.xpath("(//a[text()='Go to Home Page'])[2]")).click();
		List<WebElement> count = driver.findElements(By.tagName("a"));	
		int size = count.size();
		System.out.println(size);
			
		
	}

}
