package Scripts;

import java.io.IOException;
import java.util.Random;
import static Config.TakScreenshot.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriverException;
import org.testng.annotations.Test;
import static ObjectRepository.Admin.*;
import static UIWrappers.UIObjects.*;
import static ObjectRepository.Locations.*;
import static ObjectRepository.TaskGroups.*;
import static ObjectRepository.Threats.*;

import UIWrappers.Page;
import jxl.read.biff.BiffException;

/*********************************
 *      @author:Geetanjali
 *      
 * Test1: Navigation to Admin Page
 * Test2: Navigation to "Email/SMS Reports Tab
 * Test3: Search for SMS
 * Test4: SMS Pagination
 * Test5: Show Entries Drop Down
 * 
 */

public class Adm_EmailOrSMSReport extends Page {
	LoginLogout ll = new LoginLogout();
	
/*	@Test(priority=0)
	void login() throws BiffException, IOException, InterruptedException
	{
		ll.loginToSIB();
		thread();
	}  */

	@Test(priority=1)
	void navigateToAdminPage() throws InterruptedException
	{
		click(adminBtnInTopMnu);
		thread();
		click(adminInTopDD);
		thread();
	}
	
	// click on Email/SMS Reports tab
	
	@Test(priority=2)
	void emailORSMSReports() throws InterruptedException
	{
		click(reportsTb);
		thread();
					
	}
	
	// Search By date
	
	@Test(priority=3)
	void searchSMS() throws InterruptedException, IOException
	{
		try
		{
		click(repCalendarIcon);
		thread();
		click(repSelectDateIndd);
		thread();
		getTotalValuesIndd(repSmsTableVal);
		thread();
		
		Random rand=new Random();
		int rs=rand.nextInt(totalDDValCount-1)+1;
		String smsRandomVal=driver.findElement(By.xpath("//table[@id='sms_table']/tbody/tr["+rs+"]/td[1]")).getText();
		thread();
		
		//System.out.println("Random value "+smsRandomVal);
		enterText(repSmsSearchBox,smsRandomVal);
		thread();
		Thread.sleep(3000);
		getObjectText(repSmsTableSearchFirstVal);
		thread();
		Thread.sleep(3000);
		verifyAssertEquals(smsRandomVal,getActualObjectTxt);
		takeScreenshot();
		clear(repSmsSearchBox);
		enterKeyInKyBord(repSmsSearchBox);
		thread();
		
		enterText(repSmsSearchBox,"Invalid name");
		Thread.sleep(4000);
		enterKeyInKyBord(repSmsSearchBox);
		thread();
		getObjectText(repSmsNoRecords);
		thread();
		verifyAssertEquals(noMatchingRecordsFound,getActualObjectTxt);
		takeScreenshot();
		clear(repSmsSearchBox);
		enterKeyInKyBord(repSmsSearchBox);
		thread();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
			
	}

	
	//     Pagination
	
	@Test(priority=4)
	void sMSPagination() throws IOException, InterruptedException
	{
		try
		{		
		click(repCalendarIcon);
		thread();
		click(repSelectDateIndd);
		Thread.sleep(10000);
		getTotalValuesIndd(repSmsListViewPagination);
		thread();
		
		if(totalDDValCount > 5)
		{
			pagination();
		}
		
      //	Click Pagination Number
			
					Random randomPagi = new Random();
					int randomPge = randomPagi.nextInt((totalDDValCount)-4)+4;
					thread();
					//System.out.println("random number "+randomPge);
					driver.findElement(By.xpath("//div[@id='sms_table_wrapper']/div[2]/div[2]/div/ul/li["+randomPge+"]/a")).click();
					thread();
					Thread.sleep(5000);
					takeScreenshot();
					click(pagiStartArw);
					thread();
					Thread.sleep(5000);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	
	}
	
	
	//   Select value for Show Entries Drop Down
	
	@Test(priority=5)
	void verifySmsShowEntriesDropdown() throws InterruptedException, IOException
	{
		try
		{
			getObjectText(repSmsListViewRecsInfo);
			thread();
			String actVal;
			if(getActualObjectTxt.contains(","))
			{
				//System.out.println("inside if");
				String entries[]=getActualObjectTxt.split("of ");
				String val[]=entries[1].split(",");
				String val1=val[1].split(" ")[0];
				actVal=val[0].concat(val1);
				
			/*	String entries1[]=getActualObjectTxt.split(" ");
				actVal=entries1[5].replaceAll(",", "");*/
				
	        }
			else
			{
				//System.out.println("inside else");
				actVal=getActualObjectTxt.split("of ")[1].split(" ")[0].trim();
			}
			
		//	System.out.println("Actual val "+actVal);
			
			//if(Integer.parseInt(getActualObjectTxt.split("of ")[1].split(" ")[0].trim()) > 10)
				if(Integer.parseInt(actVal)>10)
			{
				selectTextFromDropdown(repSmsListViewLength, "25");
				thread();
				webElement(repSmsListViewRecsInfo);
				scrollInnerScrollBar(webelementFrame);
				thread();
				Thread.sleep(5000);
				takeScreenshot();
				
				selectTextFromDropdown(repSmsListViewLength, "10");
				thread();
				Thread.sleep(5000);
				takeScreenshot();
				
				selectTextFromDropdown(repSmsListViewLength, "50");
				thread();
				Thread.sleep(5000);
				takeScreenshot();
				selectTextFromDropdown(repSmsListViewLength, "100");
				thread();
				Thread.sleep(5000);
				takeScreenshot();
			}
		}
		catch(WebDriverException e)
		{
			System.out.println("Pagination is not available in List View. It contains 10 or less than 10 records");
		}
		   
	}
	
	//   Search Email By Date 
	
	@Test(priority=6)
	
	public void searchEmail() throws InterruptedException, IOException
	{
		try
		{
		click(repCalendarIcon);
		thread();
		click(repSelectDateIndd);
		thread();
		webElement(repEmailListViewRecsInfo);
		scrollInnerScrollBar(webelementFrame);
		
		getTotalValuesIndd(repSmsTableVal);
		thread();
		
		Random rand=new Random();
		int re=rand.nextInt(totalDDValCount-1)+1;
		String emailRandomVal=driver.findElement(By.xpath("//table[@id='email_table']/tbody/tr["+re+"]/td[1]")).getText();
		thread();
		
		System.out.println("Random value "+emailRandomVal);
		
		enterText(repEmailSearchBox,emailRandomVal);
		thread();
		enterKeyInKyBord(repEmailSearchBox);
		thread();
		getObjectText(repEmailTableSearchFirstVal);
		thread();
		verifyAssertEquals(emailRandomVal,getActualObjectTxt);
		takeScreenshot();
		clear(repEmailSearchBox);
		thread();
		enterKeyInKyBord(repEmailSearchBox);
		thread();
		//System.out.println("Valid Email Search Completed");
		
		enterText(repEmailSearchBox,"Invalid name");
		thread();
		enterKeyInKyBord(repEmailSearchBox);
		thread();
		getObjectText(repEmailNoRecords);
		verifyAssertEquals(noMatchingRecordsFound,getActualObjectTxt);
		takeScreenshot();
		clear(repEmailSearchBox);
		thread();
		enterKeyInKyBord(repEmailSearchBox);
		thread();
		//System.out.println("Invalid Search Completed");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
	//Email Pagination
	
	@Test(priority=7)
	void emailPagination() throws IOException, InterruptedException
	{
		try
		{
		click(repCalendarIcon);
		thread();
		click(repSelectDateIndd);
		Thread.sleep(10000);
		webElement(repEmailListViewRecsInfo);
		scrollInnerScrollBar(webelementFrame);
		thread();
		click(chatHide);
		thread();
		
		getTotalValuesIndd(repEmailListViewPagination);
		//System.out.println("total DD val "+totalDDValCount);
		int n=totalDDValCount;
		int m=n-1;
		 
		if(totalDDValCount > 5)
		{
			webElement(repEmailListViewRecsInfo);
			scrollInnerScrollBar(webelementFrame);
			thread();
			
			driver.findElement(By.xpath("//div[@id='email_table_wrapper']/div[2]/div[2]/div/ul/li["+n+"]/a")).click(); //click page end arrow
			takeScreenshot();
			thread();
			Thread.sleep(5000);
			webElement(repEmailListViewRecsInfo);
			scrollInnerScrollBar(webelementFrame);
			thread();
	
			click(repEmailPageStartArrow);
			takeScreenshot();
			thread();
			Thread.sleep(5000);
			
			if(totalDDValCount == 6)
			{
				webElement(repEmailListViewRecsInfo);
				scrollInnerScrollBar(webelementFrame);
				thread();
				driver.findElement(By.xpath("//div[@id='email_table_wrapper']/div[2]/div[2]/div/ul/li["+m+"]/a")).click();     //click next arrow
				takeScreenshot();
				thread();
				Thread.sleep(5000);
				webElement(repEmailListViewRecsInfo);
				scrollInnerScrollBar(webelementFrame);
				thread();
				
				click(repEmailPrevArrow);
				takeScreenshot();
				thread();
				Thread.sleep(5000);
			}
			
		}
			
      //	Click Pagination Number
			
					Random randomPagi = new Random();
					int randomPge = randomPagi.nextInt((totalDDValCount)-4)+4;
					thread();
					//System.out.println("random number for pagination "+randomPge);
					driver.findElement(By.xpath("//div[@id='email_table_wrapper']/div[2]/div[2]/div/ul/li["+randomPge+"]/a")).click();
					thread();
					Thread.sleep(5000);
					webElement(repEmailListViewRecsInfo);
					scrollInnerScrollBar(webelementFrame);
					thread();
					takeScreenshot();
					
					click(repEmailPageStartArrow);
					thread();
					Thread.sleep(5000);
					
					//System.out.println("Email Pagination");		
					
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	
	
	}
	
	
	//   Select value for Show Entries Drop Down
	
	@Test(priority=8)
	void verifyEmailShowEntriesDropdown() throws InterruptedException, IOException
	{
	
		try
		{
			getObjectText(repEmailListViewRecsInfo);
			thread();
			
			String actVal="";
			if(getActualObjectTxt.contains(","))
            {
			//	System.out.println("inside if");
			String entries[]=getActualObjectTxt.split("of ");
			String val[]=entries[1].split(",");
			String val1=val[1].split(" ")[0];
			actVal=val[0].concat(val1);
            }
			else
			{
			//	System.out.println("inside else");
			actVal=getActualObjectTxt.split("of ")[1].split(" ")[0].trim();
			}
			
			//System.out.println("Actual val "+actVal);
						
			//if(Integer.parseInt(getActualObjectTxt.split("of ")[1].split(" ")[0].trim()) > 10)
			if(Integer.parseInt(actVal)>10)
			{
				selectTextFromDropdown(repEmailListViewLength, "25");
				thread();
				webElement(repEmailListViewRecsInfo);
				scrollInnerScrollBar(webelementFrame);
				thread();
				Thread.sleep(5000);
				takeScreenshot();
				//System.out.println("25 records displayed");
				
				selectTextFromDropdown(repEmailListViewLength, "10");
				thread();
				Thread.sleep(5000);
				takeScreenshot();
				//System.out.println("10 records displayed");
				selectTextFromDropdown(repEmailListViewLength, "50");
				thread();
				Thread.sleep(5000);
				takeScreenshot();
				
				selectTextFromDropdown(repEmailListViewLength, "100");
				thread();
				Thread.sleep(5000);
				takeScreenshot();
			}
		}
		catch(WebDriverException e)
		{
			System.out.println("Pagination is not available in Task Group List View. It contains 10 or less than 10 records");
		}
		   
	}
	
	//Logout
	
	/*@Test(priority=9)
	void logout() throws InterruptedException
	{
		ll.logoutSession();
		thread();
		//System.out.println("Logout Successful");
		closeWindow();
		//System.out.println("Window Closed");
	}*/
	
	
	
	
		
}
