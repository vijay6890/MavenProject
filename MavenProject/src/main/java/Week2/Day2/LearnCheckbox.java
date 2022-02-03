package Week2.Day2;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LearnCheckbox {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.get("http://leafground.com/pages/checkbox.html");
		driver.manage().window().maximize();
		driver.findElement(By.xpath("//div[text()='C']/input")).click();
		//String text = driver.findElement(By.xpath("//div[text()='Selenium']/input")).getText();
		//boolean selected = driver.findElement(By.xpath("//div[text()='Selenium']/input")).isSelected();
		String text = driver.findElement(By.xpath("//label[text()='Confirm Selenium is checked']/following::input")).getText();
		boolean selected=driver.findElement(By.xpath("//label[text()='Confirm Selenium is checked']/following::input")).isSelected();
		if (selected == true) {
			System.out.println(text + "Is selected");
		} else {
			System.out.println(text + "Is not Selected");
		}
		boolean selected2 = driver.findElement(By.xpath("//div[text()='I am Selected']/input")).isSelected();
		if (selected2 == true) {

			driver.findElement(By.xpath("//div[text()='I am Selected']/input")).click();
		}
		boolean selected3 = driver.findElement(By.xpath("//div[text()='Not Selected']/input")).isSelected();
		if (selected3 == true) {
			driver.findElement(By.xpath("//div[text()='Not Selected']/input")).click();
		}
		driver.findElement(By.xpath("//div[text()='Option 1']/input")).click();
		driver.findElement(By.xpath("//div[text()='Option 2']/input")).click();
		driver.findElement(By.xpath("//div[text()='Option 3']/input")).click();
		driver.findElement(By.xpath("//div[text()='Option 4']/input")).click();
		driver.findElement(By.xpath("//div[text()='Option 5']/input")).click();
	}

}
