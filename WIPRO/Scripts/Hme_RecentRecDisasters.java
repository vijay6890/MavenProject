package Scripts;

import java.awt.AWTException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriverException;
import org.testng.annotations.Test;

import static Config.TakScreenshot.takeScreenshot;
import static ObjectRepository.Admin.repSmsListViewLength;
import static ObjectRepository.Admin.repSmsListViewRecsInfo;
import static ObjectRepository.Home.*;
import static ObjectRepository.Threats.pagiStartArw;
import static UIWrappers.UIObjects.*;

import UIWrappers.Page;
import jxl.read.biff.BiffException;

public class Hme_RecentRecDisasters extends Page 
{
	
	LoginLogout ll = new LoginLogout();
	String currentTab,rDisasterName,srchFstDisName;
		
/*		@Test(priority=0)
		void login() throws BiffException, IOException, InterruptedException 
		{
			ll.loginToSIB();
		}*/
		
		@Test(priority=1)
		void searchRecoveredDisasters() throws InterruptedException
		{
			Thread.sleep(1000);
			webElement(recDisasterHeader);
			scrollInnerScrollBar(webelementFrame);
			getTotalValuesIndd(recDisasterTableTltVal);
			Random rand=new Random();
			int rn=rand.nextInt(totalDDValCount-1)+1;
			rDisasterName=driver.findElement(By.xpath("//table[@id='tblRecoveredDisaster']/tbody/tr["+rn+"]/td[1]/div[1]")).getText();
			enterText(recDisasterSearchBox,rDisasterName);
			thread();
			getObjectText(recDisasterSearchFstVal);
			thread();
			srchFstDisName=getActualObjectTxt;
			verifyAssertEquals(rDisasterName,getActualObjectTxt);
			Thread.sleep(1000);
			currentTab=driver.getWindowHandle();
			click(recDisasterViewReport);
			thread();
			
			
		}
		
		@Test(priority=2)
		void verifyDisasterNameInNewTab() throws InterruptedException, AWTException
		{
			 ArrayList<String> newTab = new ArrayList<String>(driver.getWindowHandles());
			 newTab.remove(currentTab);
			 
			 // change focus to new tab
			 driver.switchTo().window(newTab.get(0));
			 thread();
			 //System.out.println("Disaster Name "+driver.findElement(disasterAlertNmeInSumryPge).getText());
			 getObjectText(disasterAlertNmeInSumryPge);
			 thread();
			 verifyAssertEquals(rDisasterName,getActualObjectTxt.split(":")[1].trim());			 
			 click(recDisasterSaveAsPdfBtn);
			 thread();
			// System.out.println("pdf click");
			 download();
			 driver.close();
			 thread();
			
			 //switch to old window
			 driver.switchTo().window(currentTab);
			 clear(recDisasterSearchBox);
			 thread();
			 enterKeyInKyBord(recDisasterSearchBox);
			 Thread.sleep(1000);
		}

	@Test(priority=3)
		void recDisPagination() throws InterruptedException, IOException
		{
		try
		{
			webElement(recDisasterPagicount);
			scrollInnerScrollBar(webelementFrame);
			
		getTotalValuesIndd(recDisasterPagicount);
		Thread.sleep(1000);
		//System.out.println("count "+totalDDValCount);	
			if(totalDDValCount>5)
				
			{
				pagination();
			}
			
			webElement(recDisasterPagicount);
			scrollInnerScrollBar(webelementFrame);
			Random rand = new Random();
			int rPge = rand.nextInt((totalDDValCount)-4)+4;
			thread();
			//System.out.println("random number "+rPge);
			driver.findElement(By.xpath("//div[@id='tblRecoveredDisaster_wrapper']/div[2]/div[2]/div/ul/li["+rPge+"]/a")).click();
			thread();
			Thread.sleep(5000);
			takeScreenshot();
			click(pagiStartArw);
			thread();
			Thread.sleep(5000);
		}
		catch(Exception e)
		{
			//System.out.println("Total pagination count is equal to 5");
		}
		}
	
	@Test(priority=4)
	void showEntries() throws InterruptedException, IOException
	{
		try
		{
			getObjectText(recDisasterListViewRecsInfo);
			thread();
			if(Integer.parseInt(getActualObjectTxt.split("of ")[1].split(" ")[0].trim()) > 10)
			{
				
				selectTextFromDropdown(recDisasterShowEntries, "25");
				thread();
				webElement(recDisasterListViewRecsInfo);
				scrollInnerScrollBar(webelementFrame);
				thread();
				Thread.sleep(5000);
				takeScreenshot();
				
				selectTextFromDropdown(recDisasterShowEntries, "10");
				thread();
				Thread.sleep(5000);
				takeScreenshot();
				
				selectTextFromDropdown(recDisasterShowEntries, "50");
				thread();
				Thread.sleep(5000);
				takeScreenshot();
				
				selectTextFromDropdown(recDisasterShowEntries, "100");
				thread();
				Thread.sleep(5000);
				takeScreenshot();
			}
			
		}
		catch(WebDriverException e)
		{
			//System.out.println("Pagination is not available in List View. It contains 10 or less than 10 records");
		}
		   
	}
}

























