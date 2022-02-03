package Samples;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Hotels {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
  String district=null;
  String phone=null;
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://downtowndallas.com/experience/stay/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.findElement(By.xpath("//div[@id='popup']//img")).click();
		driver.navigate().back();
		List<WebElement> hotels = driver.findElements(By.xpath("//div[@class='place-square__info place-square__info-bg ']/a"));
		List<String> lst=new ArrayList<String>();
		int count = hotels.size();
		      for (int i = 1; i <=count; i++) {
		    	  driver.findElement(By.xpath("(//a[@class='place-square__btn'])["+i+"]")).click();
		    	  String imagepath = driver.findElement(By.xpath("//div[@class='place-info-image']/img")).getAttribute("src");
		    	  String hotelname = driver.findElement(By.xpath("//*[@class='place-name']")).getText();
		    	  String add1 = driver.findElement(By.xpath("(//a[@target='_blank'])[1]")).getText();
		    		if(driver.findElements(By.xpath("//div[@class='place-info-address']//a")).size()>1);
		    	 {
		    		  phone = driver.findElement(By.xpath("(//div[@class='place-info-address'])[2]//a")).getText();
		    	 
		    	if(driver.findElements(By.xpath("//div[@class='place-info-address']//a")).size()>2)
		    	 {
		    		  
		 district = driver.findElement(By.xpath("(//div[@class='place-info-address'])[3]/a")).getText();
	               
	                 }
		      
	               lst.add(imagepath);
		    	  lst.add(hotelname);
		    	  lst.add(phone);
		    	  lst.add(district);
		    	  lst.add(add1);
		    	  Thread.sleep(3000);
		    	  driver.navigate().back();
		    }
		      System.out.println(lst);
		
	}

	}
	}

