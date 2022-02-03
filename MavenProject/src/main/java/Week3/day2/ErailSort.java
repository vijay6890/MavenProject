package Week3.day2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ErailSort {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.get("https://erail.in/");
		driver.findElement(By.id("chkSelectDateOnly")).click();
		driver.findElement(By.id("txtStationFrom")).clear();
		Thread.sleep(3000);
		driver.findElement(By.id("txtStationFrom")).sendKeys("MAS", Keys.ENTER);
		driver.findElement(By.id("txtStationTo")).clear();
		Thread.sleep(3000);
		driver.findElement(By.id("txtStationTo")).sendKeys("MDU", Keys.ENTER);
		// List<WebElement> findElements =
		// driver.findElements(By.xpath("//*[@id=\"divTrainsList\"]/table[1]/tbody/tr/td[1]"));
		driver.findElement(By.id("buttonFromTo")).click();
		List<WebElement> findElements = driver
				.findElements(By.xpath("//table[@class='DataTable TrainList TrainListHeader']//td[2]"));
		int size = findElements.size();
		System.out.println(size);
		List<String> trainnames = new ArrayList<String>();

		for (int i = 0; i < size; i++) {

			WebElement findtrain = findElements.get(i);
			trainnames.add(findtrain.getText());

		}

		Collections.sort(trainnames);
		System.out.println(trainnames);

	}

}
