package Week3.day2;

import java.awt.RenderingHints.Key;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Ajio {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.get("https://www.ajio.com");
		driver.manage().window().maximize();
		driver.findElement(By.xpath("//input[@name='searchVal']")).sendKeys("bags", Keys.ENTER);
		driver.findElement(By.xpath("//label[@for='Men']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//label[@for='Men - Fashion Bags']")).click();
		Thread.sleep(2000);
		String text = driver.findElement(By.xpath("//div[@class='length']")).getText();
		System.out.println(text);
		List<WebElement> findElements = driver.findElements(By.xpath("//div[@class='brand']"));
		List<WebElement> findElements2 = driver.findElements(By.xpath("//div[@class='name']"));

		for (int i = 0; i < findElements.size(); i++) {
			WebElement webElement = findElements.get(i);
			String text2 = webElement.getText();
			System.out.println("List Of Brands:" + text2);

		}
		for (int i = 0; i < findElements2.size(); i++) {

			WebElement webElement = findElements2.get(i);
			String text2 = webElement.getText();
			System.out.println("List Of Names:" + text2);

		}

	}

}
