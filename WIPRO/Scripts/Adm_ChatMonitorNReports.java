package Scripts;

import static ObjectRepository.Admin.*;
import static ObjectRepository.Incidents.alertBtnInTopMenu;
import static UIWrappers.UIObjects.*;
import static ObjectRepository.Locations.*;
import static ObjectRepository.Threats.*;

import java.io.IOException;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriverException;
import org.testng.annotations.Test;
import static Config.TakScreenshot.*;

import UIWrappers.Page;
import jxl.read.biff.BiffException;

public class Adm_ChatMonitorNReports extends Page {
	
LoginLogout ll = new LoginLogout();
	
  /*@Test(priority=0)
	void login() throws BiffException, IOException, InterruptedException
	{
		ll.loginToSIB();
		thread();
	} */      
	
	@Test(priority=1)
	void navigateToAdminPage() throws InterruptedException
	{
	    
		click(adminBtnInTopMnu);
		thread();
		click(adminInTopDD);
		thread();
	}
	
	@Test(priority=2)
	void chatMonitor() throws InterruptedException
	{
		click(chatMonitor);
		thread();
	}
	
/*	@Test(priority=3)
	void searchMessages() throws InterruptedException, IOException
	{
		try
		{
		//click(chtMonSearchBox);
		thread();
		getTotalValuesIndd(chtMonTableVal);
		thread();
		
		Random rand=new Random();
		int rn=rand.nextInt(totalDDValCount-1)+1;
		String chtMonRandVal=driver.findElement(By.xpath("//table[@id='chat_history']/tbody/tr["+rn+"]/td[4]")).getText();
		thread();
		System.out.println("random Val "+chtMonRandVal);
		
		enterText(chtMonSearchBox,chtMonRandVal);
		thread();
		getObjectText(chtMonTableSearchFirstVal);
		thread();
		verifyAssertEquals(chtMonRandVal,getActualObjectTxt);
		takeScreenshot();
		thread();
		clear(chtMonSearchBox);
		thread();
		enterKeyInKyBord(chtMonSearchBox);
		Thread.sleep(5000);
		
		enterText(chtMonSearchBox,"Invalid Name");
		thread();
		getObjectText(chtMonInvalidSearchVal);
		thread();
		verifyAssertEquals(noMatchingRecordsFound,getActualObjectTxt);
		takeScreenshot();
		clear(chtMonSearchBox);
		thread();
		enterKeyInKyBord(chtMonSearchBox);
		Thread.sleep(5000);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
			
	}
	
	@Test(priority=4)
	void chtMonitorPagination() throws InterruptedException, IOException
	{
		try
		{
		webElement(chtMonListViewRecsInfo);
		scrollInnerScrollBar(webelementFrame);
		thread();
		
		getTotalValuesIndd(chtMonListViewPagination);
		
		Random randomPagi = new Random();
		int randomPge = randomPagi.nextInt(totalDDValCount);
		thread();
		//System.out.println("random number for pagination "+randomPge);
		driver.findElement(By.xpath("//div[@id='chat_history_wrapper']/div[4]/div[2]/div/ul/li["+randomPge+"]/a")).click();
		thread();
		Thread.sleep(5000);
		takeScreenshot();
		click(pagiStartArw);
		thread();
		Thread.sleep(5000);
		}
		catch(WebDriverException e)
		{
			System.out.println("Pagination is not available in  List View. It contains 10 or less than 10 records");
		}
	}
	
	@Test(priority=5)
	void chtMonitorShowEntries() throws InterruptedException, IOException
	{
	try
	{
		getObjectText(chtMonListViewRecsInfo);
		thread();
		if(Integer.parseInt(getActualObjectTxt.split("of ")[1].split(" ")[0].trim()) > 10)
		{
			selectTextFromDropdown(chtMonListViewLength, "25");
			thread();
			webElement(chtMonListViewRecsInfo);
			scrollInnerScrollBar(webelementFrame);
			thread();
			Thread.sleep(5000);
			takeScreenshot();
			//System.out.println("25 records displayed");
			
			selectTextFromDropdown(chtMonListViewLength, "10");
			thread();
			Thread.sleep(5000);
			takeScreenshot();
			//System.out.println("10 records displayed");
		}
	}
	catch(WebDriverException e)
	{
		System.out.println("Pagination is not available in Task Group List View. It contains 10 or less than 10 records");
	}
	   
}   */
	
	@Test(priority=6)
	void chatReports() throws InterruptedException
	{
		click(chatReports);
		thread();
	}
	
	
	
	@Test(priority=7)
	void searchChatRooms() throws InterruptedException, IOException
	{
		try
		{
		click(repCalendarIcon);
		thread();
		click(repSelectDateIndd);
		thread();
		getTotalValuesIndd(chtRepTableVal);
		thread();
		
		Random random=new Random();
		int rv=random.nextInt(totalDDValCount-1)+1;
		String chtRepSearchVal=driver.findElement(By.xpath("//table[@id='chat_reports']/tbody/tr["+rv+"]/td[1]")).getText();
		thread();
		
		enterText(chtRepSearchbox,chtRepSearchVal.substring(7));
		thread();
		getObjectText(chtRepTableSearchFirstVal);
		thread();
		//System.out.println(chtRepSearchVal.substring(7)+"-------"+getActualObjectTxt.substring(7));
		//System.out.println("actual object text "+getActualObjectTxt);
		
		verifyAssertEquals(chtRepSearchVal.substring(7),getActualObjectTxt.substring(7));
		takeScreenshot();
		
		click(chtRepViewBtn);
		thread();
		}
		catch(Exception e)
		{
			//System.out.println(e);
		}
	}
	
	@Test(priority=8)
	void nestedSearch() throws InterruptedException, IOException
	{
		try
		{
		getTotalValuesIndd(chtRepNestedTableVal);
		thread();
		//System.out.println("total val "+totalDDValCount);
		try{
		if(totalDDValCount>1)
		{
        Random random=new Random();
		int rnv=random.nextInt(totalDDValCount-1)+1;
		String chtRepNestedSearchVal=driver.findElement(By.xpath("//table[@id='nested_table']/tbody/tr["+rnv+"]/td[1]")).getText();
		thread();
		
		enterText(chtRepNestedSearchBox,chtRepNestedSearchVal);
		thread();
		getObjectText(chtRepNestedTableFirstVal);
		thread();
		//System.out.println(chtRepNestedSearchVal+"---"+getActualObjectTxt);
		verifyAssertEquals(chtRepNestedSearchVal,getActualObjectTxt);
		takeScreenshot();
		clear(chtRepNestedSearchBox);
		thread();
		enterKeyInKyBord(chtRepNestedSearchBox);
		
		getTotalValuesIndd(chtRepNestedTableVal);
		//System.out.println("total val after clear operation "+totalDDValCount);
		
		enterText(chtRepNestedSearchBox,"Invalid name");
		thread();
		getObjectText(chtRepNestedNoRecords);
		thread();
		verifyAssertEquals(noMatchingRecordsFound,getActualObjectTxt);
		takeScreenshot();
		clear(chtRepNestedSearchBox);
		thread();
		enterKeyInKyBord(chtRepNestedSearchBox);
		
		getTotalValuesIndd(chtRepNestedTableVal);
		//System.out.println("total val after clear operation-invalid search "+totalDDValCount);
		}
		}
		catch(Exception e)
		{
			//System.out.println(e+" No records in the nested table");
		}
		thread();
		}
		catch(Exception e)
		{
			//System.out.println(e);
		}
				
		
	}
	
	// pagination for nested records
	
	@Test(priority=9)
	void nestedChtRepPagination() throws InterruptedException, IOException
	{
		try
		{
		getTotalValuesIndd(chtRepChtRmNestedListViewPagination);
		thread();
		
		if(totalDDValCount > 5)
		{
		pagination();	
		}
		
		Random randomPagi = new Random();
		int randomPge = randomPagi.nextInt(totalDDValCount-1)+1;
		thread();
		//System.out.println("random number for pagination "+randomPge);
		driver.findElement(By.xpath("//div[@id='nested_table_wrapper']/div[2]/div[2]/div/ul/li["+randomPge+"]/a")).click();
		thread();
		Thread.sleep(5000);
		takeScreenshot();
		click(pagiStartArw);
		thread();
		Thread.sleep(5000);
		}
		catch(WebDriverException e)
		{
			//System.out.println("Pagination is not available in  List View. It contains 10 or less than 10 records");
		}
	}
	
	// show nested entries
	@Test(priority=10)
	void nestedShowEntries() throws InterruptedException, IOException
	{
		try
		{
			getObjectText(chtRepChtRmNestedListViewRecsInfo);
			thread();
			if(Integer.parseInt(getActualObjectTxt.split("of ")[1].split(" ")[0].trim()) > 10)
			{
				selectTextFromDropdown(chtRepChtRmNestedListViewLength, "25");
				thread();
				webElement(chtRepChtRmNestedListViewRecsInfo);
				scrollInnerScrollBar(webelementFrame);
				thread();
				Thread.sleep(5000);
				takeScreenshot();
				//System.out.println("25 records displayed");
				
				selectTextFromDropdown(chtRepChtRmNestedListViewLength, "10");
				thread();
				Thread.sleep(5000);
				takeScreenshot();
				//System.out.println("10 records displayed");
			}
		}
		catch(WebDriverException e)
		{
			//System.out.println("Pagination is not available in Task Group List View. It contains 10 or less than 10 records");
		}
	}
	
	// click close button
	
	@Test(priority=11)
	void nestedClose() throws InterruptedException
	{
		try
		{
			thread();
			click(chtRepCloseBtn);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
	//  ChatRooms Pagination
	
	@Test(priority=12)
	void chatRoomPagination() throws InterruptedException, IOException
	{
		try
		{
		clear(chtRepSearchbox);
		enterKeyInKyBord(chtRepSearchbox);
		thread();
		try
		{
		getTotalValuesIndd(chtRepChtRmListViewPagination);
		
		if(totalDDValCount > 5)
		{
		pagination();	
		}
		
		Random randomPagi = new Random();
		int randomPge = randomPagi.nextInt(totalDDValCount);
		thread();
		//System.out.println("random number for pagination "+randomPge);
		driver.findElement(By.xpath("//div[@id='chat_reports_wrapper']/div[4]/div[2]/div/ul/li["+randomPge+"]/a")).click();
		thread();
		Thread.sleep(5000);
		takeScreenshot();
		click(pagiStartArw);
		thread();
		Thread.sleep(5000);
		}
		catch(WebDriverException e)
		{
			//System.out.println("Pagination is not available in  List View. It contains 10 or less than 10 records");
		}
		}
		catch(Exception e)
		{
			//System.out.println(e);
		}
	}
	
	
	// show entries for chat rooms
	@Test(priority=13)
	void chtRoomShowEntries() throws InterruptedException, IOException
	{
		try
		{
			getObjectText(chtRepChtRmListViewRecsInfo);
			thread();
			if(Integer.parseInt(getActualObjectTxt.split("of ")[1].split(" ")[0].trim()) > 10)
			{
				selectTextFromDropdown(chtRepChtRmListViewLength, "25");
				thread();
				webElement(chtRepChtRmListViewRecsInfo);
				scrollInnerScrollBar(webelementFrame);
				thread();
				Thread.sleep(5000);
				takeScreenshot();
				//System.out.println("25 records displayed");
				
				selectTextFromDropdown(chtRepChtRmListViewLength, "10");
				thread();
				Thread.sleep(5000);
				takeScreenshot();
				//System.out.println("10 records displayed");
			}
		}
		catch(WebDriverException e)
		{
			//System.out.println("Pagination is not available in Task Group List View. It contains 10 or less than 10 records");
		}
	}

	//Search Individual User
	
	@Test(priority=14)
	void searchIndivUser() throws InterruptedException, IOException
	{
		try
		{
		click(chtRepIndiUser);
		thread();
		click(repCalendarIcon);
		thread();
		click(chtRepIndiUserSelectDateIndd);
		thread();
		getTotalValuesIndd(chtRepTableVal);
		thread();
		
		Random random=new Random();
		int rv=random.nextInt(totalDDValCount-1)+1;
		String chtRepSearchVal=driver.findElement(By.xpath("//table[@id='chat_reports']/tbody/tr["+rv+"]/td[1]")).getText();
		thread();
		
		enterText(chtRepSearchbox,chtRepSearchVal.substring(7));
		thread();
		getObjectText(chtRepTableSearchFirstVal);
		thread();
		//System.out.println(chtRepSearchVal.substring(7)+"-------"+getActualObjectTxt.substring(7));
		//System.out.println("actual object text "+getActualObjectTxt);
		try
		{
		verifyAssertEquals(chtRepSearchVal.substring(7),getActualObjectTxt.substring(7));
		takeScreenshot();
				
		click(chtRepViewBtn);
		thread();
		}
		catch(Exception e)
		{
		//	System.out.println(e);
		}
		}
		catch(Exception e)
		{
			// 
		}
	}
	
	//nested individual user search
	@Test(priority=15)
	void nestedIndiUserSearch() throws InterruptedException
	{
		try
		{
		getTotalValuesIndd(chtRepNestedTableVal);
		thread();
		//System.out.println("total val "+totalDDValCount);
		try{
		if(totalDDValCount>1)
		{
        Random random=new Random();
		int rnv=random.nextInt(totalDDValCount-1)+1;
		String chtRepNestedSearchVal=driver.findElement(By.xpath("//table[@id='nested_table']/tbody/tr["+rnv+"]/td[1]")).getText();
		thread();
		
		enterText(chtRepNestedSearchBox,chtRepNestedSearchVal);
		thread();
		getObjectText(chtRepNestedTableFirstVal);
		thread();
		//System.out.println(chtRepNestedSearchVal+"---"+getActualObjectTxt);
		verifyAssertEquals(chtRepNestedSearchVal,getActualObjectTxt);
		takeScreenshot();
		clear(chtRepNestedSearchBox);
		thread();
		enterKeyInKyBord(chtRepNestedSearchBox);
		
		getTotalValuesIndd(chtRepNestedTableVal);
		//System.out.println("total val after clear operation "+totalDDValCount);
		
		enterText(chtRepNestedSearchBox,"Invalid name");
		thread();
		getObjectText(chtRepNestedNoRecords);
		thread();
		verifyAssertEquals(noMatchingRecordsFound,getActualObjectTxt);
		takeScreenshot();
		clear(chtRepNestedSearchBox);
		thread();
		enterKeyInKyBord(chtRepNestedSearchBox);
		
		getTotalValuesIndd(chtRepNestedTableVal);
		//System.out.println("total val after clear operation-invalid search "+totalDDValCount);
		}
		}
		catch(Exception e)
		{
			//System.out.println(e+" No records in the nested table");
		}
		thread();
		}
		catch(Exception e)
		{
			//System.out.println(e);
		}
	}
	
	//nested individual user pagination
	@Test(priority=16)
	void nestedIndiUserPagination() throws InterruptedException, IOException
	{
		try
		{
		getTotalValuesIndd(chtRepChtRmNestedListViewPagination);
		
		if(totalDDValCount > 5)
		{
		pagination();	
		}
		
		Random randomPagi = new Random();
		int randomPge = randomPagi.nextInt(totalDDValCount);
		thread();
		//System.out.println("random number for pagination "+randomPge);
		driver.findElement(By.xpath("//div[@id='nested_table_wrapper']/div[2]/div[2]/div/ul/li["+randomPge+"]/a")).click();
		thread();
		Thread.sleep(5000);
		takeScreenshot();
		click(pagiStartArw);
		thread();
		Thread.sleep(5000);
		}
		catch(Exception e)
		{
			//System.out.println("Pagination is not available in  List View. It contains 10 or less than 10 records");
		}
	}
	
	// show Individual user nested entries
	
		@Test(priority=17)
		void nestedIndivUserShowEntries() throws InterruptedException, IOException
		{
			try
			{
				getObjectText(chtRepChtRmNestedListViewRecsInfo);
				thread();
				if(Integer.parseInt(getActualObjectTxt.split("of ")[1].split(" ")[0].trim()) > 10)
				{
					selectTextFromDropdown(chtRepChtRmNestedListViewLength, "25");
					thread();
					webElement(chtRepChtRmNestedListViewRecsInfo);
					scrollInnerScrollBar(webelementFrame);
					thread();
					Thread.sleep(5000);
					takeScreenshot();
					//System.out.println("25 records displayed");
					
					selectTextFromDropdown(chtRepChtRmNestedListViewLength, "10");
					thread();
					Thread.sleep(5000);
					takeScreenshot();
					//System.out.println("10 records displayed");
				}
			}
			catch(WebDriverException e)
			{
				//System.out.println("Pagination is not available in Task Group List View. It contains 10 or less than 10 records");
			}
		}
		
		// click close button
		
		@Test(priority=18)
		void nestedIndivClose() throws InterruptedException
		{
			try
			{
				thread();
			click(chtRepCloseBtn);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			
		}
		
	//  Individual User Pagination
		
		@Test(priority=19)
		void individualUserPagination() throws InterruptedException, IOException
		{
			clear(chtRepSearchbox);
			enterKeyInKyBord(chtRepSearchbox);
			thread();
			try
			{
			getTotalValuesIndd(chtRepChtRmListViewPagination);
			
			if(totalDDValCount > 5)
			{
			pagination();	
			}
			
			Random randomPagi = new Random();
			int randomPge = randomPagi.nextInt(totalDDValCount);
			thread();
			//System.out.println("random number for pagination "+randomPge);
			driver.findElement(By.xpath("//div[@id='chat_reports_wrapper']/div[4]/div[2]/div/ul/li["+randomPge+"]/a")).click();
			thread();
			Thread.sleep(5000);
			takeScreenshot();
			click(pagiStartArw);
			thread();
			Thread.sleep(5000);
			}
			catch(WebDriverException e)
			{
				//System.out.println("Pagination is not available in  List View. It contains 10 or less than 10 records");
			}
		}
		
		
		// show entries for Individual user
		
		@Test(priority=20)
		void individualUserShowEntries() throws InterruptedException, IOException
		{
			try
			{
				getObjectText(chtRepChtRmListViewRecsInfo);
				thread();
				String actVal;
				if(getActualObjectTxt.contains(","))
				{
					//System.out.println("inside if");
					String entries[]=getActualObjectTxt.split("of ");
					String val[]=entries[1].split(",");
					String val1=val[1].split(" ")[0];
					actVal=val[0].concat(val1);
		        }
				else
				{
					//System.out.println("inside else");
					actVal=getActualObjectTxt.split("of ")[1].split(" ")[0].trim();
				}
				
			//	if(Integer.parseInt(getActualObjectTxt.split("of ")[1].split(" ")[0].trim()) > 10)
				if(Integer.parseInt(actVal)>10)
				{
					selectTextFromDropdown(chtRepChtRmListViewLength, "25");
					thread();
					webElement(chtRepChtRmListViewRecsInfo);
					scrollInnerScrollBar(webelementFrame);
					thread();
					Thread.sleep(5000);
					takeScreenshot();
					//System.out.println("25 records displayed");
					
					selectTextFromDropdown(chtRepChtRmListViewLength, "10");
					thread();
					Thread.sleep(5000);
					takeScreenshot();
					//System.out.println("10 records displayed");
					selectTextFromDropdown(chtRepChtRmListViewLength, "50");
					thread();
					Thread.sleep(5000);
					takeScreenshot();
					
					selectTextFromDropdown(chtRepChtRmListViewLength, "100");
					thread();
					Thread.sleep(5000);
					takeScreenshot();
				}
			}
			catch(WebDriverException e)
			{
				//System.out.println("Pagination is not available in Task Group List View. It contains 10 or less than 10 records");
			}
		}

		
		
//Logout

		/*@Test(priority=21)
void logout() throws InterruptedException
{
	ll.logoutSession();
	thread();
	//System.out.println("Logout Successful");
	closeWindow();
	//System.out.println("Window Closed");
}*/

	
	
	

}
