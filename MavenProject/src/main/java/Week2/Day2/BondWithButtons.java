package Week2.Day2;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BondWithButtons {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.get("http://leafground.com/pages/Button.html");
		driver.manage().window().maximize();

		Point location = driver.findElement(By.xpath("//button[text()='Get Position']")).getLocation();
		System.out.println("Position :" + location);
		String cssValue = driver.findElement(By.id("color")).getCssValue("background-color");
		System.out.println("Backcolor :" + cssValue);
		Dimension size = driver.findElement(By.id("size")).getSize();
		System.out.println("Size is: " + size);
		driver.findElement(By.xpath("//button[text()='Go to Home Page']")).click();

	}

}
