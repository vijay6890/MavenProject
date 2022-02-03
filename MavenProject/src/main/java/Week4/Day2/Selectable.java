package Week4.Day2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Selectable {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://jqueryui.com/selectable/");
		driver.switchTo().frame(0);
		WebElement item1 = driver.findElement(By.xpath("//li[text()='Item 1']"));
		// WebElement item2= driver.findElement(By.xpath("//li[text()='Item 2']"));
		WebElement item5 = driver.findElement(By.xpath("//li[text()='Item 5']"));

		Actions act = new Actions(driver);
		act.clickAndHold(item1).moveToElement(item5).release().perform();

	}

}
