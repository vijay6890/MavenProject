package Samples;


	import java.util.ArrayList;
	import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
	import org.openqa.selenium.chrome.ChromeDriver;

	import io.github.bonigarcia.wdm.WebDriverManager;

	public class Hotels1 {

			// TODO Auto-generated method stub
	public static void main(String[] args) throws InterruptedException {

				WebDriverManager.chromedriver().setup();

				
				ChromeDriver driver = new ChromeDriver();
				driver.manage().window().maximize();
				
				driver.get("https://downtowndallas.com/experience/stay/");
				driver.findElement(By.xpath("//div[@id='popup']//img")).click();
				 driver.navigate().back();
				 int size = driver.findElements(By.xpath("//a[@class='place-square__btn']")).size();
				 List<String> list = new ArrayList<String>();
				 for (int i = 1; i <= size; i++) {
					 String area = null;
					 String phone = null;
					 String imgurl = driver.findElement(By.xpath("//div[@class='place-square__img '] //img")).getAttribute("src");
					 driver.findElement(By.xpath("(//a[@class='place-square__btn'])["+i+"]")).click();
					 String place = driver.findElement(By.className("place-name")).getText();
					 String address = driver.findElement(By.xpath("//div[@class='place-info-address']//a")).getText();
					 if(driver.findElements(By.xpath("(//div[@class='place-info-address']//a)")).size()>1) {
					  phone = driver.findElement(By.xpath("(//div[@class='place-info-address']//a)[2]")).getText();
						 if(driver.findElements(By.xpath("//div[@class='place-info-address']//a")).size()>2)
						 {
							 area  = driver.findElement(By.xpath("(//div[@class='place-info-address']//a)[3]")).getText();
				     
					 }   
				   
					 }
					 list.add(imgurl);
					 list.add(place);
					 list.add(address);
					 list.add(phone);
					 list.add(area);
					 Thread.sleep(3000);
					 driver.navigate().back();
					 
				}
				 System.out.println(list);
				 
			}
		}

