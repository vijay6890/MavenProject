package Week4.Day2;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SnapDeal {

	public static void main(String[] args) throws InterruptedException, IOException {
		// TODO Auto-generated method stub
		// 1. Launch https://www.snapdeal.com/
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.get("https://www.snapdeal.com/");
		driver.manage().window().maximize();
		// 2. Go to Mens Fashion
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

		driver.findElement(By.xpath("(//span[contains(text(),'Fashion')])[5]")).click();

		driver.findElement(By.xpath("(//span[text()='Sports Shoes'])[1]")).click();
		// 4. Get the count of the sports shoes
		String text = driver.findElement(By.xpath("//span[@class='category-count']")).getText();
		System.out.println("Count of the Shoes" + text);
		// 5. Click Training shoes
		driver.findElement(By.xpath("//div[text()='Training Shoes']")).click();
		// 6. Sort by Low to High
		driver.findElement(By.xpath("//div[@class='sort-drop clearfix']")).click();
		driver.findElement(By.xpath("//ul[@class='sort-value']/li[2]")).click();
		Thread.sleep(3000);
		// 7. Check if the items displayed are sorted correctly
		List<WebElement> list = driver.findElements(By.xpath("//span[@class='lfloat product-price']"));
		Thread.sleep(5000);
		System.out.println(list.size());
		List<String> pricelist = new ArrayList<String>();
		for (int i = 0; i < list.size(); i++) {
			String attribute = list.get(i).getAttribute("data-price");
			pricelist.add(attribute);
		}

		System.out.println("Low to High Price List:" + pricelist);

		// 8.Select the price range (900-1200)
		driver.findElement(By.name("fromVal")).clear();
		driver.findElement(By.name("fromVal")).sendKeys("900");
		driver.findElement(By.name("toVal")).clear();
		driver.findElement(By.name("toVal")).sendKeys("1200");
		driver.findElement(By.xpath("//div[contains(text(),'GO')]")).click();
		Thread.sleep(3000);

		// 9.Filter with Black Navy
		Actions act = new Actions(driver);
		WebElement ele = driver.findElement(By.xpath("(//a[contains(text(),'Black')])[1]/parent::label"));
		act.moveToElement(ele).perform();

		driver.findElement(By.xpath("(//a[contains(text(),'Black')])[1]/parent::label")).click();

		// 10. verify the all applied filters
		String text2 = driver.findElement(By.xpath("//a[@class='clear-filter-pill']")).getText();
		String text3 = driver.findElement(By.xpath("(//a[text()='Black'])[1]")).getText();
		if (text2.contains("900") && text3.contains("Black")) {
			System.out.println("Price Range and Color Filter applied Successfully");
		} else {
			System.out.println("Filter was not applying");
		}

		// 11. Mouse Hover on first resulting Training shoes
		WebElement ele1 = driver.findElement(By.xpath("//img[@title='Sparx SM438 Black Training Shoes']"));
		Actions act1 = new Actions(driver);
		act1.moveToElement(ele1).perform();
		// 12. click QuickView button
		driver.findElement(By.xpath("//div[contains(text(),'Quick View')]")).click();
		// 13. Print the cost and the discount percentage
		String text4 = driver.findElement(By.className("payBlkBig")).getText();
		System.out.println("Price of the Shoe is :" + text4);
		String text5 = driver.findElement(By.xpath("//span[@class='percent-desc ']")).getText();
		System.out.println("Discount Rate is:" + text5);

		// 14. Take the snapshot of the shoes.
		WebElement img = driver.findElement(By.xpath(
				"(//img[@src='https://n1.sdlcdn.com/imgs/j/p/2/Sparx-SM438-Black-Training-Shoes-SDL076724906-1-ca8a8.jpg'])[1]"));
		File screenshotAs = img.getScreenshotAs(OutputType.FILE);
		File dst = new File("./snaps/pic.png");
		FileUtils.copyFile(screenshotAs, dst);

		// 15.Close the Window
		// driver.close();
		// driver.switchTo().window(list.get(1));
		// driver.close();

	}

}
