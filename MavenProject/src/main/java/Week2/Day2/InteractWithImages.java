package Week2.Day2;

import java.awt.Desktop.Action;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class InteractWithImages {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.get("http://leafground.com/pages/Image.html");
		driver.manage().window().maximize();
		driver.findElement(By.xpath("//label[contains(text(),'Click on this')]/following-sibling::img")).click();
		driver.navigate().to("http://leafground.com/pages/Image.html");
	//	Thread.sleep(6000);
		driver.findElement(By.xpath("//img[@src='../images/abcd.jpg']")).click();
	   // String attribute = BrokenImg.getAttribute("src");
		//driver.navigate().to(attribute);
		driver.navigate().back();
		Thread.sleep(3000);
		//Dimension size = BrokenImg.getSize();
		//System.out.println(size);
	String title = driver.getTitle();
	if(title.contains("404"))
	{
		System.out.println("Image is Broken");
	}
	else
	{
		System.out.println("Image is not broken");
	}
	//WebElement Ele = driver.findElement(By.xpath("//label[contains(text(),'Keyboard')]/following-sibling::img"));
		//Actions act = new Actions(driver);
		//act.click(Ele);

	}

}
