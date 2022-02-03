package Scripts;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import static Config.TakScreenshot.*;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import static ObjectRepository.Admin.repSmsListViewPagination;
import static ObjectRepository.Home.*;
import static ObjectRepository.Threats.*;
import static ObjectRepository.Locations.*;
import static UIWrappers.UIObjects.*;

import UIWrappers.Page;
import jxl.read.biff.BiffException;

public class Hme_EndDisaster extends Page 
{
	LoginLogout ll = new LoginLogout();
	String edRecMsg,edLocMsg,edCustMsg,edVendrMsg,edReview,currentTab;
	

		
		@Test(priority=0)
		void login() throws BiffException, IOException, InterruptedException 
		{
			ll.loginToSIB();
		}
		
		@Test(priority=1)
		void getExcelSheetData() throws BiffException, IOException
		{
			getEndDisasterSheetFromExcel();
			Random rand=new Random();
			int rn=rand.nextInt(endDisMsgs.getRows()-1)+1;
			
			edRecMsg=endDisMsgs.getCell(0, rn).getContents();
			edLocMsg=endDisMsgs.getCell(1, rn).getContents();
			edCustMsg=endDisMsgs.getCell(2, rn).getContents();
			edVendrMsg=endDisMsgs.getCell(3, rn).getContents();
			edReview=endDisMsgs.getCell(4, rn).getContents();
			//System.out.println(edRecMsg +" "+edLocMsg+" "+ edCustMsg+" "+ edVendrMsg+" "+ edReview);
		}
				
		@Test(priority=2)
		void endDisasterBtn() throws InterruptedException
		{
			Hme_ActiveTasks.selectLocation();
			Hme_ActiveTasks.selectDisaster();
			click(hmeEndDisasterBtn);
			thread();
			try
			{
			click(hmeEndDisasterConfirmOkBtn);
			thread();
			}
			catch(Exception e)
			{
				//no pop up
				e.printStackTrace();
			}
					
		}
		
				
		@Test(priority=3)
		void recEmail() throws InterruptedException, BiffException, IOException, AWTException
		{						
			click(hmeEndDisasterRecTimeDtNextBtn);             
			thread();
			try
			{
			//click(hmeEndDisasterRecEmailChckAll);             //check all
			thread();
			
			
			getTotalValuesIndd(recEmpTotalTableVal);
			thread();
			//System.out.println("Total Recovery emp count "+totalDDValCount);
			String className=driver.findElement(By.xpath("//table[@id='tblEmailContent_ED']/tbody/tr/td")).getAttribute("class");
			
			//System.out.println("Class Name rec"+className);
			if(!className.equals("dataTables_empty"))
			{
			  if(totalDDValCount>=4)
			    {
			      for(int i=1;i<=2;i++)
			       {
				     Random rand=new Random(System.nanoTime());
				     int rv=rand.nextInt(totalDDValCount-1)+1;
				   //  System.out.println("random val "+rv);
				      WebElement locEmp=driver.findElement(By.xpath("//table[@id='tblEmailContent_ED']/tbody/tr["+rv+"]/td[1]"));
				     locEmp.click();
				     thread();
				     takeScreenshot();
			        }
			    }
			  else if(totalDDValCount>=1 &&totalDDValCount<=3)
			    {
				  for(int i=1;i<=totalDDValCount;i++)
				  {
					WebElement locEmp=driver.findElement(By.xpath("//table[@id='tblEmailContent_ED']/tbody/tr["+i+"]/td[1]"));
					locEmp.click();
					thread();
					takeScreenshot();
				  }
			    }
			  else
			  {
			  }
			
			  
			  webElement(hmeEndDisasterRecEmailNextBtn);
				scrollInnerScrollBar(webelementFrame);
				
				getTotalValuesIndd(recEmpPgTtlCnt);
				thread();
				
				//click end arrow
				driver.findElement(By.xpath("//div[@id='tblEmailContent_ED_wrapper']/div[2]/div[2]/div/ul/li["+totalDDValCount+"]/a")).click();
				thread();
				
				//using tab key to move to testarea
				tabKey();
				
				WebElement ifram = driver.findElement(scEdtrFrame);
				driver.switchTo().frame(ifram);
				thread();
				enterText(scEdtrCommentsField,edRecMsg);
				thread();
				takeScreenshot();
				switchBackFromFrame();
			}
				click(hmeEndDisasterRecEmailNextBtn);
				thread();
			}
			
			catch(Exception e)
			{
				System.out.println(e);
				click(hmeEndDisasterRecEmailNextBtn);
				thread();
				//System.out.println("no records");	
			}
			
		}
		
		
		@Test(priority=4)
		void locEmp() throws InterruptedException, IOException, AWTException
		{
		  try
			{
			getTotalValuesIndd(locEmpTotalTableVal);
			thread();
			//System.out.println("Total loc emp count "+totalDDValCount);
            String className=driver.findElement(By.xpath("//table[@id='tblEmailContent_EP']/tbody/tr/td")).getAttribute("class");
			
          //  System.out.println("Class Name loc"+className);
			if(!className.equals("dataTables_empty"))
			{
			  if(totalDDValCount>=4)
			    {
			      for(int i=1;i<=2;i++)
			       {
				     Random rand=new Random(System.nanoTime());
				     int rv=rand.nextInt(totalDDValCount-1)+1;
				     System.out.println("random val "+rv);
				      WebElement locEmp=driver.findElement(By.xpath("//table[@id='tblEmailContent_EP']/tbody/tr["+rv+"]/td[1]"));
				     locEmp.click();
				     thread();
				     takeScreenshot();
			        }
			    }
			  else if(totalDDValCount>=1 &&totalDDValCount<=3)
			    {
				   for(int i=1;i<=totalDDValCount;i++)
				   {
					WebElement locEmp=driver.findElement(By.xpath("//table[@id='tblEmailContent_EP']/tbody/tr["+i+"]/td[1]"));
					locEmp.click();
					thread();
					takeScreenshot();
				   }
			    }
			  else
			  {
			  }
			
			
		  
			//  click(hmeEndDisasterLocEmpChckAll);         //check all
			//	thread();
			  
		      //enter data in text area
			  webElement(hmeEndDisasterLocEmpNextBtn);
				scrollInnerScrollBar(webelementFrame);
				
				getTotalValuesIndd(locEmpPgTtlCnt);
				thread();
				
				//click end arrow
				driver.findElement(By.xpath("//div[@id='tblEmailContent_EP_wrapper']/div[2]/div[2]/div/ul/li["+totalDDValCount+"]/a")).click();
				thread();				
							
				//using tab key to move to testarea
				tabKey();
				
				WebElement ifram = driver.findElement(scEdtrFrame);
				driver.switchTo().frame(ifram);
				thread();
				enterText(scEdtrCommentsField,edLocMsg);
				thread();
				takeScreenshot();
				switchBackFromFrame();
			}
				click(hmeEndDisasterLocEmpNextBtn);
				thread();
			  
			}
		  
			catch(Exception e)
			{
			System.out.println(e);
			click(hmeEndDisasterLocEmpNextBtn);
			thread();
			//System.out.println("No records");
			}
			
		}
	
		
		@Test(priority=5)
		void custEmp() throws InterruptedException, IOException, AWTException
		{

			try
		    {
			//click(hmeEndDisasterCustChckAll);         //check all
				
			getTotalValuesIndd(custEmpTotalTableVal);
			thread();
            String className=driver.findElement(By.xpath("//table[@id='tblEmailContent_EC']/tbody/tr/td")).getAttribute("class");
			
          //  System.out.println("Class Name cust"+className);
			if(!className.equals("dataTables_empty"))
			{
			  if(totalDDValCount>=4)
			    {
			      for(int i=1;i<=2;i++)
			       {
				     Random rand=new Random(System.nanoTime());
				     int rv=rand.nextInt(totalDDValCount-1)+1;
				   //  System.out.println("random val "+rv);
				      WebElement locEmp=driver.findElement(By.xpath("//table[@id='tblEmailContent_EC']/tbody/tr["+rv+"]/td[1]"));
				      thread();
				     locEmp.click();
				     thread();
				     takeScreenshot();
			        }
			    }
			  else if(totalDDValCount>=1 &&totalDDValCount<=3)
			    {
				   for(int i=1;i<=totalDDValCount;i++)
				   {
					WebElement locEmp=driver.findElement(By.xpath("//table[@id='tblEmailContent_EC']/tbody/tr["+i+"]/td[1]"));
					locEmp.click();
					thread();
					takeScreenshot();
				   }
			    }
			  else
			  {
			  }
			
			  
	        //enter data in text area
		    webElement(hmeEndDisasterCustNextBtn);
			scrollInnerScrollBar(webelementFrame);
			
			getTotalValuesIndd(custEmpPgTtlCnt);
			thread();
			
			//click end arrow
			driver.findElement(By.xpath("//div[@id='tblEmailContent_EC_wrapper']/div[2]/div[2]/div/ul/li["+totalDDValCount+"]/a")).click();
			thread();
	
			//using tab key to move to testarea
			tabKey();
			
			WebElement ifram = driver.findElement(scEdtrFrame);
			driver.switchTo().frame(ifram);
			thread();
			enterText(scEdtrCommentsField,edCustMsg);
			thread();
			takeScreenshot();
			switchBackFromFrame();
			thread();
			}
			click(hmeEndDisasterCustNextBtn);
			thread();
			}
			
			catch(Exception e)
			{
				System.out.println(e);
				click(hmeEndDisasterCustNextBtn);
				thread();				
			//	System.out.println("No records");
			}
			
		}
		
		@Test(priority=6)
		void vendrEmp() throws InterruptedException, IOException, AWTException
		{
			try
			{
			//	click(hmeEndDisasterVendrChckAll);         //check all
				
				getTotalValuesIndd(vendrEmpTotalTableVal);
				thread();
	            String className=driver.findElement(By.xpath("//table[@id='tblEmailContent_EV']/tbody/tr/td")).getAttribute("class");
				
	         //   System.out.println("Class Name vendor"+className);
				if(!className.equals("dataTables_empty"))
				{
				  if(totalDDValCount>=4)
				    {
				      for(int i=1;i<=2;i++)
				       {
					     Random rand=new Random(System.nanoTime());
					     int rv=rand.nextInt(totalDDValCount-1)+1;
					 //    System.out.println("random val "+rv);
					      WebElement locEmp=driver.findElement(By.xpath("//table[@id='tblEmailContent_EV']/tbody/tr["+rv+"]/td[1]"));
					     locEmp.click();
					     thread();
					     takeScreenshot();
				        }
				    }
				  else if(totalDDValCount>=1 &&totalDDValCount<=3)
				    {
					   for(int i=1;i<=totalDDValCount;i++)
					   {
						WebElement locEmp=driver.findElement(By.xpath("//table[@id='tblEmailContent_EV']/tbody/tr["+i+"]/td[1]"));
						locEmp.click();
						thread();
						takeScreenshot();
					   }
				    }
				  else
				  {
				  }
				
				thread();
				
	        //enter data in text area
				
		    webElement(hmeEndDisasterVendrNextBtn);
			scrollInnerScrollBar(webelementFrame);
			
			getTotalValuesIndd(vendrEmpPgTtlCnt);
			thread();
			
			//click end arrow
			driver.findElement(By.xpath("//div[@id='tblEmailContent_EV_wrapper']/div[2]/div[2]/div/ul/li["+totalDDValCount+"]/a")).click();
			thread();	
		
			//using tab key to move to testarea
			tabKey();
			
			WebElement ifram = driver.findElement(scEdtrFrame);
			driver.switchTo().frame(ifram);
			thread();
			enterText(scEdtrCommentsField,edVendrMsg);
			thread();
			takeScreenshot();
			switchBackFromFrame();
			thread();
		    }
			click(hmeEndDisasterVendrNextBtn);
			thread();
			}
			
			catch(Exception e)
			{
				System.out.println(e);
				click(hmeEndDisasterVendrNextBtn);
				thread();
				//System.out.println("No records");
			}        
			
			
		}

		@Test(priority=7)
		void reviewMessage() throws InterruptedException, IOException, AWTException
		{
			try
			{			
			for(int i=1;i<2;i++)
			{
			    Robot r = new Robot();
			    r.keyPress(KeyEvent.VK_TAB);
			    r.keyRelease(KeyEvent.VK_TAB);
			   // System.out.println("Tabbed "+i+" time.");
			}
		//	tabKey();
		
			WebElement ifram = driver.findElement(scEdtrFrame);
			driver.switchTo().frame(ifram);
			thread();
			//click(scEdtrCommentsField);
			thread();
			enterText(scEdtrCommentsField,edReview);
			thread();
			switchBackFromFrame();
			takeScreenshot();
			thread();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			
		}
		
		@Test(priority=8)
		void restoreDisaster() throws InterruptedException, IOException
		{
			try
			{
			currentTab=driver.getWindowHandle();
			
			click(restoreBtn);
			thread();
			getObjectText(msgNotificationBar);
			thread();
			takeScreenshot();
		//	System.out.println("success message "+getActualObjectTxt);
			verifyAssertEquals("Disaster Declaration ended successfully",getActualObjectTxt);
			thread();
			
			 ArrayList<String> newTab = new ArrayList<String>(driver.getWindowHandles());
			 newTab.remove(currentTab);
			 
			 // change focus to new tab
			 driver.switchTo().window(newTab.get(0));
			 takeScreenshot();
			
			getObjectText(disasterAlertNmeInSumryPge);
			thread();
			//System.out.println(Hme_ActiveTasks.disName.trim()+"--------"+getActualObjectTxt.split(":")[1].trim());
			verifyAssertEquals(Hme_ActiveTasks.disName.trim(),getActualObjectTxt.split(":")[1].trim());
			thread();
			driver.close();
			 thread();
			 driver.switchTo().window(currentTab);
			 thread();
			 takeScreenshot();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			
		}
		
		@Test(priority=9)
		void verifyEndedDisasterInRecentlyRecDis() throws InterruptedException
		{
			try
			{
			webElement(recDisasterHeader);
			scrollInnerScrollBar(webelementFrame);
			thread();
			getObjectText(recDisasterSearchFstVal);
			thread();
			verifyAssertEquals(Hme_ActiveTasks.disName.trim(),getActualObjectTxt);
			thread();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		
	}
