package Week4.Day2;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Amazon {

	public static void main(String[] args) throws InterruptedException, IOException {
		// TODO Auto-generated method stub
//1.Load the uRL https://www.amazon.in/
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.get("https://www.amazon.in/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		// 2.search as oneplus 9 pro
		driver.findElement(By.id("twotabsearchtextbox")).sendKeys("oneplus 9 pro", Keys.ENTER);

		// 3.Get the price of the first product
		List<WebElement> cost = driver.findElements(By.className("a-price-whole"));
		List<String> price = new ArrayList<String>();
		// for (int i = 0; i < cost.size(); i++) {
		String text = cost.get(0).getText();
		price.add(text);
		System.out.println(price);
		// 4. Print the number of customer ratings for the first displayed product
		List<WebElement> reviews = driver.findElements(By.xpath("//span[@class='a-size-base']"));
		// List<String> lstreviews = new ArrayList<String>();
		String text2 = reviews.get(0).getText();
		System.out.println("Review Count of First Product is:" + text2);
		Thread.sleep(3000);

		// 5. Click on the stars
		driver.findElement(By.xpath("(//i[@class='a-icon a-icon-star-small a-star-small-4 aok-align-bottom'])[1]"))
				.click();
		// 6. Get the percentage of ratings for the 5 star.
		WebElement table = driver.findElement(By.xpath("//table[@id='histogramTable']/tbody"));
		List<WebElement> rows = table.findElements(By.tagName("tr"));
		System.out.println("Number of Rows:" + rows.size());
		List<WebElement> column = rows.get(0).findElements(By.tagName("td"));
		String text3 = column.get(2).getText();
		System.out.println("Five Star Rating % is :" + text3);

		// 7. Click the first text link of the first image
		List<WebElement> link = driver
				.findElements(By.xpath("//span[@class='a-size-medium a-color-base a-text-normal']"));

		link.get(0).click();
		Set<String> windowHandles = driver.getWindowHandles();
		List<String> lst = new ArrayList<String>(windowHandles);
		driver.switchTo().window(lst.get(1));

		// 8. Take a screen shot of the product displayed
		WebElement src = driver.findElement(By.xpath("(//img[@class='a-dynamic-image a-stretch-horizontal'])[1]"));
		File screenshotAs = src.getScreenshotAs(OutputType.FILE);
		File dest = new File("./snaps/pic3.png");
		FileUtils.copyFile(screenshotAs, dest);
		Thread.sleep(3000);

		// 9. Click 'Add to Cart' button
		WebElement ele1 = driver.findElement(By.xpath("//input[@id='add-to-cart-button']"));
		Actions act1 = new Actions(driver);
		act1.moveToElement(ele1).perform();
		driver.findElement(By.xpath("//input[@id='add-to-cart-button']")).click();

		// 10. Get the cart subtotal and verify if it is correct.
		String text4 = driver.findElement(By.id("attach-accessory-cart-subtotal")).getText();
		if (text.contains(text4)) {
			System.out.println("Price and Subtotal are matching");
		} else {
			System.out.println("Price and Subtotal are mismatching");
		}

	}

}
