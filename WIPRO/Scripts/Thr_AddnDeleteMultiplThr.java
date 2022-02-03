package Scripts;

import static UIWrappers.UIObjects.*;

import java.io.IOException;

import jxl.read.biff.BiffException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import UIWrappers.Page;
import static ObjectRepository.Threats.*;
import static Config.TakScreenshot.*;
import static ObjectRepository.Locations.*;



/*****************************************************************************************************
 * 
 * @author Sellamuthu
 * 
 * Test 1: Add a New Threat Name
 * Test 2: Verify Threat Name on Delete Confirmation pop up
 * Test 3: Verify Deleted Successfully message
 * Test 4: Choose multiple Threat Name and delete it
 * Test 5: Verify the Threat count in delete confirmation pop up
 * Test 6: Check the appropriate message while deleting more than one records
 * Test 7: Verify display of Info Pop up
 *  
******************************************************************************************************/

public class Thr_AddnDeleteMultiplThr extends Page{
	
	LoginLogout ll = new LoginLogout();
	Thr_AddndDelete thr_AddndDelete = new Thr_AddndDelete();
	Loc_AddnDeleteMultiple loc_AddnDeleteMultiple = new Loc_AddnDeleteMultiple();
	
   /*@Test(priority=0)
	void login() throws BiffException, IOException, InterruptedException
	{
		ll.loginToSIB();
	}*/
	
	@Test(priority=1)
	void deleteTheAddedThreatName() throws BiffException, IOException, InterruptedException
	{
		thr_AddndDelete.addNewThreatName();	
		Thread.sleep(100);
		
		click(threatsInMainMenu);
		waitForPageLoad();
		
		getTotalValuesIndd(thrThreatsListViewCnt);		
		for(int i=1; i<totalDDValCount; i++)
		{
			String getAddedThretNm = driver.findElement(By.xpath("//table[@id='threat_table']/tbody/tr["+i+"]/td[2]")).getText();
			WebElement delBtn = driver.findElement(By.xpath("//table[@id='threat_table']/tbody/tr["+i+"]/td[5]/a[2]"));
			
			if(getAddedThretNm.contains(thr_AddndDelete.threatNme))
			{
				delBtn.click();
				waitForElement(deleteConfPopup);
				verifyAssert(deleteConfPopup);
				Thread.sleep(1000);
				getObjectText(deleteConfPpMessage);
				verifyAssertEquals("Confirm to remove Threat   "+getAddedThretNm+" ?", getActualObjectTxt);
				takeScreenshot();
				Thread.sleep(1000);
				click(delConfOkBtn);
				Thread.sleep(1000);
				//		Verify Deleted Successfully Message
				getObjectText(msgNotificationBar);
				verifyAssertEquals("  "+getAddedThretNm+" Is Deleted", getActualObjectTxt);
				takeScreenshot();
				thread();				
			}
		}				
	}
	
	
	@Test(priority=2, invocationCount=3)
	void addMultipleThreatName() throws BiffException, InterruptedException, IOException
	{
		thr_AddndDelete.addNewThreatName();
		thread();
		
	}
	
	@Test(priority=3)
	void deleteMultiple() throws IOException, InterruptedException
	{
		getTotalValuesIndd(thrThreatsListViewCnt);
		for(int i=1; i<4; i++)
		{
			driver.findElement(By.xpath("//table[@id='threat_table']/tbody/tr["+i+"]/td/div/span/input")).click();			
		}
		
		thread();
		takeScreenshot();
		click(thrOvrDeleteBtn);
		thread();
		
		getObjectText(deleteConfPpMessage);
		verifyAssertEquals("Confirm to remove 3 record(s) from Threats?", getActualObjectTxt);
		takeScreenshot();
	    click(delConfOkBtn);
	    Thread.sleep(5000);
	    getObjectText(msgNotificationBar);
		verifyAssertEquals("Successfully deleted 3 records", getActualObjectTxt);
		takeScreenshot();
		thread();		
	}
	
	/*@Test(priority=4)
	void vfyThrCountInConfPpWhilChosingThrtsFrmDiffPaginatn()
	{
		for(int i=0; i<2; i++)
		{
			Random random = new Random();
			getTotalValuesIndd(thrThreatsListViewCnt);
			int ranmThrName = random.nextInt(totalDDValCount-1)+1;
			
			driver.findElement(By.xpath("//table[@id='threat_table']/tbody/tr["+ranmThrName+"]/td/div/span/input")).click();			
		}
		
		try
		{
			getTotalValuesIndd(listViewPagination);
			if(totalDDValCount > 5)
			{
				click(pagiNextArw);
				
				for(int i=0; i<2; i++)
				{
					Random random = new Random();
					getTotalValuesIndd(thrThreatsListViewCnt);
					int ranmThrName = random.nextInt(totalDDValCount-1)+1;
					
					driver.findElement(By.xpath("//table[@id='threat_table']/tbody/tr["+ranmThrName+"]/td/div/span/input")).click();			
				}	
			}
			
			click(thrOvrDeleteBtn);
			waitForElement(deleteConfPopup);
			getObjectText(deleteConfPpMessage);
			verifyAssertEquals("Confirm to remove 4 records from Threats?", getActualObjectTxt);
			takeScreenshot();
			
			click(delConfCancelBtn);
			thread();			
		}
		catch(Exception e)
		{
			System.out.println("Pagination links are not available");
		}		
	}*/
	
	@Test(priority=4)
	void verifyInfoPopup() throws InterruptedException, IOException
	{
		click(thrOvrInfoBtn);
		verifyAssert(thrHelpPp);
		thread();
		
		takeScreenshot();
		click(thrHelpPpCloseBtn);
		thread();
	}
	
	@Test(priority=5)
	void verifySelectRecordsToPerformDeleteMsg() throws InterruptedException, IOException
	{
		click(thrOvrDeleteBtn);
		thread();
		getObjectText(msgNotificationBar);
		verifyAssertEquals(loc_AddnDeleteMultiple.selectRecordsToPerformDelete, getActualObjectTxt);
		thread();
		takeScreenshot();
	}
	/*@Test(priority=6)
	void logout() throws InterruptedException
	{
		ll.logoutSession();
		thread();
		closeWindow();
	}*/
}

