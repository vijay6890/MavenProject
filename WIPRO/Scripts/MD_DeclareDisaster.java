package Scripts;

import static Config.TakScreenshot.takeScreenshot;
import static ObjectRepository.ManageDisaster.*;

import static ObjectRepository.Threats.*;
import static UIWrappers.UIObjects.*;
import static ObjectRepository.Locations.*;


import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Random;



import org.openqa.selenium.By;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import UIWrappers.Page;
import jxl.read.biff.BiffException;

/***************************************************************************************************
 * 
 * @author Tamizhselvi
 * 
 * 
 * 
****************************************************************************************************/

public class MD_DeclareDisaster extends Page {
	
	LoginLogout ll = new LoginLogout();
	
	String disasterName,contactMessageLabelName ;
	
	String valueToSelect;
		
	
	/*@Test(priority=0)
	void login() throws BiffException, IOException, InterruptedException
	{
		ll.loginToSIB();
		thread();
	}*/
	
	@Test(priority=1)
	void addManageDisaster() throws InterruptedException
	{
		Thread.sleep(1000);
		webElement(manageDisasterInMainMenu);
	    scrollInnerScrollBar(webelementFrame);
		click(manageDisasterInMainMenu);
		waitForPageLoad();
		Thread.sleep(1000);
	}

    @Test(priority=2)
	void verifyDDAffectedLocation() throws IOException, InterruptedException
	{ 
			   getTotalValuesIndd(mdAffLocationLstViewCnt);
		       webElement(mdAffLocationRadiobBtn);
		       scrollInnerScrollBar(webelementFrame);
		       if(totalDDValCount>=1)
		        {
				Random rrow = new Random();
				int chckrow = rrow.nextInt(totalDDValCount-1)+1;				
				WebElement location = driver.findElement(By.xpath("//table[@id='disaster_location_table']/tbody/tr["+chckrow+"]/td[1]/input"));
				location.click();
		        }
		        thread();
	}	    
    			
	@Test(priority=3)
	void verifySelectedThreatforLocation() throws InterruptedException, IOException
	{
		        getTotalValuesIndd(mdThreatNmeInLstViewCnt);
		        thread();
		        
		        if(totalDDValCount==0)
		        {
		        	verifyDDAffectedLocation();
		        }
		       /* for(int i=1; i<=totalDDValCount-1; i++)
        		{
        			boolean chkd = driver.findElement(By.xpath("//table[@id='DataTables_Table_2']//tr["+i+"]/td[1]/div/span/input")).isEnabled();
        			Assert.assertEquals(true, chkd);
        		    thread();
        		}	*/
		        click(mdThreatCheckSourceTable);
		        thread();
		        if(totalDDValCount==0)
		        {
		        	System.out.println("No records found");
		        }
		        
		        else if(totalDDValCount==0)
			        {
			        	verifyDDAffectedLocation();
			        }
		        
		        click(mdThreatCheckSourceTable);
		        Thread.sleep(1000);
		        if(totalDDValCount>=1)
		        {
				Random rrow = new Random();
				int threatNm = rrow.nextInt(totalDDValCount-1)+1;
		        WebElement threatLoc = driver.findElement(By.xpath("//table[@id='DataTables_Table_2']/tbody/tr["+threatNm+"]/td[1]/div/span"));
			    threatLoc.click();
		        }
		        Thread.sleep(1000);
		   
		        }
	
	@Test(priority=4)
  void serachThreatNameinListView() throws IOException, InterruptedException
  {
	   
	    getTotalValuesIndd(mdThreatNmeInLstViewCnt);
	    if(totalDDValCount>1)
	    {
	  
        //Search Valid Contact Name
		Random thrtName = new Random();
		int rlistvw = thrtName.nextInt(totalDDValCount-1)+1;		
		String ThreatName = driver.findElement(By.xpath("//table[@id='DataTables_Table_2']/tbody/tr["+rlistvw+"]/td[2]")).getText().trim();
		enterText(mdThreatSearchBox, ThreatName);
		getObjectText(mdThreatSrchRslt);
		Thread.sleep(1000);
		verifyAssertEquals(ThreatName.trim(), getActualObjectTxt);
		Thread.sleep(1000);
		takeScreenshot();
		clear(mdThreatSearchBox);
		enterKeyInKyBord(mdThreatSearchBox);
		thread();
		
		//	Search Invalid Contact Name
		enterText(mdThreatSearchBox, "Inv threatName");
		getObjectText(noRecordsFoundMsg);
		verifyAssertEquals(noMatchingRecordsFound, getActualObjectTxt);
		takeScreenshot();
		clear(mdThreatSearchBox);
		enterKeyInKyBord(mdThreatSearchBox);
		thread();
	    }
	
  }
  
    	
	
	@Test(priority=5)
	void verifyListofDRPlan() throws InterruptedException
	{
		    getTotalValuesIndd(mdDRPlanLstViewCnt);	
		    webElement(mdDRPlanSearchBox);
	        scrollInnerScrollBar(webelementFrame);
	        thread();
	        if(totalDDValCount==0)
	        {
	        	System.out.println("No plans for the threat ");
	        }
	       else if(totalDDValCount==1){
	        WebElement threatLoc = driver.findElement(By.xpath("//table[@id='DataTables_Table_0']//tr[1]/td[1]/div/span/input"));
			  threatLoc.click();
	        }
	        if(totalDDValCount>=1)
			{
				Random rrow = new Random();
				int drplan = rrow.nextInt(totalDDValCount-1)+1;
		        WebElement threatLoc = driver.findElement(By.xpath("//table[@id='DataTables_Table_0']//tr["+drplan+"]/td[1]/div/span/input"));
			    threatLoc.click();
			   
			} thread();
		
	}
	 @Test(priority=6)
	   void searchDRPlanNameInListView() throws InterruptedException, IOException
	   {
		   webElement(mdFstDRPlanRadioBtn);
	       scrollInnerScrollBar(webelementFrame);
	       thread();
		   getTotalValuesIndd(mdDRPlanLstViewCnt);	
		   thread();
		   if(totalDDValCount==0)
	        {
	        	System.out.println("No records found");
	        }
		   else if(totalDDValCount>=1)  
		   {
	        Random random = new Random();
			int rrow = random.nextInt(totalDDValCount-1)+1;
			//Search Valid Declare Disaster 
			String drplanNme = driver.findElement(By.xpath("//table[@id='DataTables_Table_0']/tbody/tr["+rrow+"]/td[2]")).getText();
			enterText(mdDRPlanSearchBox,drplanNme);
			getObjectText(mdDRPlanSrchRslt);
			thread();
			verifyAssertEquals(drplanNme, getActualObjectTxt);
			thread();
			clear(mdDRPlanSearchBox);
			enterKeyInKyBord(mdDRPlanSearchBox);
			thread();
	        
	      //Search Invalid Declare Disaster 
	  		enterText(mdDRPlanSearchBox, "Inv Dr plan");
	  		getObjectText(noRecordsFoundMsg);
	  		verifyAssertEquals(noMatchingRecordsFound, getActualObjectTxt);
	  		takeScreenshot();
	  		clear(mdDRPlanSearchBox);
	  		enterKeyInKyBord(mdDRPlanSearchBox);
	  		thread();
		   }
	   }

	
	@Test(priority=7)
	void addDisasterInfo() throws BiffException, IOException, InterruptedException
	{
		webElement(mdDisasterName);
	    scrollInnerScrollBar(webelementFrame);
		getManageDisasterSheetFromExcel();
		thread();
		Random rrow = new Random();
		int rexRow = rrow.nextInt(mgDisastr.getRows()-1)+1;
		System.out.println("TTR: "+mgDisastr.getRows());
		
		// Disaster Name //
		disasterName = mgDisastr.getCell(0, rexRow).getContents();
		enterText(mdDisasterName,disasterName);
		thread();
		
		  // Disaster Date //

		   click(dDateIconCalendar);
		   click(disDatePickrDaysCalndr);
		   thread();
		
		   click(disDatePickrMonthsCalndr);
		   thread();
		
		   click(disDateCalndrLeftSideArrow);
		
		   // Select year 
		   getTotalValuesIndd(disGetTotalYearsCnt);			
			Random ranYear = new Random();
			int ryear = ranYear.nextInt(totalDDValCount-1)+1;		
			String year = driver.findElement(By.xpath("//div[@class='datepicker-years']//tbody/tr/td/span["+ryear+"]")).getText();
			driver.findElement(By.xpath("//span[contains(text(),"+year+")]")).click();
			thread();
			
			//		Select Month
			getTotalValuesIndd(disGetTotalMonths);
			Random ranMonth = new Random();			
			int rmonth = ranMonth.nextInt(totalDDValCount-1)+1;		
			
			WebElement ranMonthNm = driver.findElement(By.xpath("//div[@class='datepicker-months']//tbody/tr/td/span["+rmonth+"]")); 
			ranMonthNm.click();
			thread();
			
			//		Select Date
			getTotalValuesIndd(disGetRandomDateRowCnt);
			Random rDateRow = new Random();
			int rDateR = rDateRow.nextInt(totalDDValCount-1)+1;		
						
			Random rDateCol = new Random();
			int rDateC = rDateCol.nextInt(7-1)+1;
						
			WebElement rDate = driver.findElement(By.xpath("//div[@class='datepicker-days']//tbody/tr["+rDateR+"]/td["+rDateC+"]"));
			String Date = rDate.getText();
			System.out.println("Date: "+Date); 
			rDate.click();
			thread();
			
			//Disaster Time //
			
			click(mdDisasterTime);
			click(disTimePickerControlDown);
			click(timecloseBtn);
			thread();
			
			// Notification Date //
			 click(mdNotificationIconCalendar );
			 thread();
			 DateFormat notificationdate = new SimpleDateFormat("dd/MM/yy");
			 Calendar c = Calendar.getInstance();
			 c.setTime(new Date(rDateC));
			 Date currentDate = new Date(rDateC);
			 String today = notificationdate.format(currentDate);
			 // find the calendar 
			 WebElement dateWidget = driver.findElement(By.xpath("//div[@class='datepicker-days']//tbody/tr/td"));
			 List<WebElement> columns = dateWidget.findElements(By.tagName("td"));
			 
			 //Comparing the text of cell with today's date and clicking it 
			 for(WebElement cell : columns)
			 {
				 if(cell.getText().equals(today))
						 {
					       cell.click();
					       thread();
						 }
			 }thread();
			 
			 // Notification Time //

				click(mdNotificationTime);
				click(disTimePickerControlDown);
				click(timecloseBtn);
				thread();
			 
			// Estimated Date //
				
				  click(mdEstimatedRecoveryDateCalendrIcon);
				  click(disDatePickrDaysCalndr);
				   thread();
				
				   click(disDatePickrMonthsCalndr);
				   thread();
				   
				   click(disCalndrRightSideArrow);
				   
				   // Select year 
				    getTotalValuesIndd(disGetTotalYearsCnt);			
					Random ranYear1 = new Random();
					int ryear1 = ranYear1.nextInt(totalDDValCount-1)+1;		
					String estyear = driver.findElement(By.xpath("//div[@class='datepicker-years']//tbody/tr/td/span["+ryear1+"]")).getText();
					driver.findElement(By.xpath("//span[contains(text(),"+estyear+")]")).click();
					thread();
					
					//		Select Month
					getTotalValuesIndd(disGetTotalMonths);
					Random ranMonth1 = new Random();			
					int rmonth1 = ranMonth1.nextInt(totalDDValCount-1)+1;		
					
					WebElement ranEstMonthNm = driver.findElement(By.xpath("//div[@class='datepicker-months']//tbody/tr/td/span["+rmonth1+"]")); 
					ranEstMonthNm.click();
					thread();
					
					//		Select Date
					getTotalValuesIndd(disGetRandomDateRowCnt);
					Random rDateRow1 = new Random();
					int rDateR1 = rDateRow1.nextInt(totalDDValCount-1)+1;		
								
					Random rDateCol1 = new Random();
					int rDateC1 = rDateCol1.nextInt(7-1)+1;
								
					WebElement rDate1 = driver.findElement(By.xpath("//div[@class='datepicker-days']//tbody/tr["+rDateR1+"]/td["+rDateC1+"]"));
					String FutureDate = rDate1.getText();
					System.out.println("Date: "+FutureDate); 
					rDate1.click();
					thread();
				 
					//Estimated Recovery Time//
					click(mdEstimatedRecoveryTime);
					click(disTimePickerControlUp);
					click(timecloseBtn);
					thread();
				
				
		
		//	NotifiedBy//
		click(mdNotifiedByDDArrow);
		getTotalValuesIndd(mdNotifiedBycnt);
		if(totalDDValCount >1)
		{
			Random ran = new Random();
			int rtype = ran.nextInt(totalDDValCount-1)+1;
			enterText(mdNotifiedByDDSearchBox, driver.findElement(By.id("dd_notified_by_i_chzn_o_"+rtype)).getText().split(",")[0]);
			enterKeyInKyBord(mdNotifiedByDDSearchBox);
			thread();
		}
				
		// AuthorizedBy//
		click(mdAuthorizedByDDArrow);
		getTotalValuesIndd(mdAuthorizedByCnt);
		if(totalDDValCount>1)
		{
			Random ran = new Random();
			int rtype = ran.nextInt(totalDDValCount-1)+1;
			enterText(mdAuthorizedByDDSearchBox, driver.findElement(By.id("dd_authorized_by_i_chzn_o_"+rtype)).getText().split(",")[0]);
			enterKeyInKyBord(mdAuthorizedByDDSearchBox);
			thread();
		}
		
		//Comments//
		WebElement ifram = driver.findElement(scEdtrFrame);
		driver.switchTo().frame(ifram);
		enterText(scEdtrCommentsField, mgDisastr.getCell(1,rexRow).getContents());
		switchBackFromFrame();
		thread();
		
	}
	
	@Test(priority=8)
	void chckDrillAlert() throws InterruptedException
	{
		webElement(mdDrillAlert);
	    scrollInnerScrollBar(webelementFrame);
		click(mdDrillAlert);
		thread();
	}
	
	@Test(priority=9)
	void selectModeofCommunication() throws InterruptedException
	{
		 getTotalValuesIndd(mdModeofCommunicationCnt);
		 for(int i=0; i<=3; i++)
			{
				Random rrow = new Random();
				int mdComm = rrow.nextInt(totalDDValCount-1)+1;
			    WebElement mdModeofCommunication = driver.findElement(By.xpath("//input[@name='comm_mode']["+mdComm+"]"));
			    mdModeofCommunication.click();
			    thread();
		    }
		  
	}
		  
  @Test(priority=10)
   void selectPrimaryAlternate() throws InterruptedException
   {
	       getTotalValuesIndd(mdPrimaryPrimSecondRadioCnt);
	        List<WebElement> radios = driver.findElements(By.xpath("//form[@id='ddr-dd-form']/fieldset/div[7]/div/div/div/label/input"));
	        if (totalDDValCount>14 && totalDDValCount<=16) 
	        {
	            radios.get(totalDDValCount-1).click();
	        } 
	        
	     else
	        {
	        	click(mdAlertPrimaryndSecondaryEmpRadioBtn);
	        }
	        thread();
   }
   
   @Test(priority=11)
   void selectContactGrpToNotify() throws InterruptedException, BiffException, IOException
   {
	   getTotalValuesIndd(mdContactGrpNotifyCnt);
	   String mdContactGrp = ".//*[@id='dd_contact_group']";
	   List<WebElement> elementToClick = driver.findElements(By.xpath(mdContactGrp));
	   for (WebElement AllCheck : elementToClick) 
	   {
	       AllCheck.click();
	   }
	    thread();
	    webElement(mdContactGrpNotifyCnt);
        scrollInnerScrollBar(webelementFrame);
        thread();
	     getManageDisasterSheetFromExcel();
	     WebElement ifram = driver.findElement(contscEdtrCommentsField);
		 driver.switchTo().frame(ifram);
		 enterText(scEdtrCommentsField, mgDisastr.getCell(2,6).getContents().trim());
		 switchBackFromFrame();
		 Thread.sleep(1000);
	}
   
   @Test(priority=12)
   void verifynotifyAllEmployeesInThisLoc() throws BiffException, IOException, InterruptedException
   {
	    click(mdNotifyAllEmployeesChkBox);
	  // driver.findElement(mdNotifyAllEmployeesChkBox).sendKeys(Keys.TAB);
	     getManageDisasterSheetFromExcel();
	     WebElement ifram = driver.findElement(notifyAllEmpLoc);
	     driver.switchTo().frame(ifram);
		 enterText(scEdtrCommentsField , mgDisastr.getCell(3,4).getContents().trim());
		 switchBackFromFrame();
		 thread();
		 webElement(mdDeclareDisasterBtn);
		 scrollInnerScrollBar(webelementFrame);
		 thread();
		 click(mdDeclareDisasterBtn);
		 getObjectText(declareDisasterConfPopup);
		 takeScreenshot();
		  Thread.sleep(1000);
		 click(declareDisasterConfPpOkBtn);
		  Thread.sleep(1000);
		 verifyAssert(disasterSummaryPage); 
		  Thread.sleep(1000);
	}
   
     //Declare the disaster with threat only this code written Without Dr plan//
   @Test(priority=13)
   void verifyThreatLocation() throws IOException, InterruptedException, BiffException
   {
	   webElement(manageDisasterInMainMenu);
		scrollInnerScrollBar(webelementFrame);
	    click(manageDisasterInMainMenu);
		 waitForPageLoad();	
		 verifyDDAffectedLocation();
		 Thread.sleep(1000);
		 
		 verifySelectedThreatforLocation();
		 thread();
		 
		 addDisasterInfo();
		 thread();
		 
		 chckDrillAlert();
		 thread();
		 
		 selectModeofCommunication();
		 thread();
		 
		 selectPrimaryAlternate();
		 thread();
		 
		 selectContactGrpToNotify();
		 thread();
		 
		 verifynotifyAllEmployeesInThisLoc();
		 thread();
		 
	}
   
    @Test(priority=14)
	void searchAffLocationsNameInListView() throws IOException, InterruptedException
	{
    	click(manageDisasterInMainMenu);
    	thread();
    	webElement(mdAffLocationRadiobBtn);
        scrollInnerScrollBar(webelementFrame);
	    thread();
        getTotalValuesIndd(mdAffLocationLstViewCnt);
       
		Random random = new Random();
		int rrow = random.nextInt(totalDDValCount-1)+1;
		//Search Valid Declare Disaster 
		String LocationAddress = driver.findElement(By.xpath("//table[@id='disaster_location_table']/tbody/tr["+rrow+"]/td[6]")).getText();
		enterText(mdLocationsSearchBox,LocationAddress);
		getObjectText(mdLocationSrchRslt);
		Thread.sleep(1000);
		verifyAssertEquals(LocationAddress.trim(), getActualObjectTxt);
		Thread.sleep(1000);
		clear(mdLocationsSearchBox);
		enterKeyInKyBord(mdLocationsSearchBox);
		thread();
         
       //Search Invalid Declare Disaster 
   		enterText(mdLocationsSearchBox, "Inv AffectedLoc");
   		getObjectText(noRecordsFoundMsg);
   		verifyAssertEquals(noMatchingRecordsFound, getActualObjectTxt);
   		takeScreenshot();
   		clear(mdLocationsSearchBox);
   		enterKeyInKyBord(mdLocationsSearchBox);
   		thread();
   		
   		
	}
    
    @Test(priority=15)
    void affLocationLstViewPagination()
    {

		getTotalValuesIndd(mdAffLocViewPagination);		
		try
		{
			if(totalDDValCount > 5)
			{
				click(pagiEndArw);
				takeScreenshot();
				thread();
				
				click(pagiStartArw);
				takeScreenshot();
				thread();
				
				if(totalDDValCount == 6)
				{
					click(pagiNextArw);
					takeScreenshot();
					thread();
					
					click(pagiPreviousArw);
					takeScreenshot();
					thread();
				}
				
				
				
				
				//	Click Pagination Number
				Random randomPagi = new Random();
				int randomPge = randomPagi.nextInt((totalDDValCount-2)-4)+4;
				driver.findElement(By.xpath("//div[@id='DataTables_Table_0_wrapper']//div[@class='dataTables_paginate paging_bootstrap pagination pagination-right']/ul/li["+randomPge+"]/a")).click();
				thread();
				takeScreenshot();
				click(pagiStartArw);
				thread();
			}	
			else
			{
				System.out.println("***** Pagination is not available. Locations list view has 10 or less than 10 records *****");
			}
		} 
		catch(Exception e) 
		{
			//e.printStackTrace();
		}		
	}
    
    @Test(priority=16)
    void afflocationLstViewShowEntries() throws InterruptedException, IOException
    {
    
    	try
		{
			getObjectText(mdAfftLocViewRecsInfo);
			
			if(Integer.parseInt(getActualObjectTxt.split("of ")[1].substring(0, 2).trim()) >=11)
			{
				selectTextFromDropdown(mdAffLocViewLength, "25");
				thread();
				takeScreenshot();
				
				selectTextFromDropdown(mdAffLocViewLength, "10");
				thread();
			}
			else
			{
				System.out.println("***** Pagination is not available in Aff Locations List View. List View has 10 or less than 10 records. So we can't choose the list view length *****");
			}
		}
		catch(WebDriverException e)
		{
			e.printStackTrace();
		}
    	thread();
	}
    
 
   @Test(priority=17)
   void threatListViewPagination() throws InterruptedException
   {
	   getTotalValuesIndd(mdThreatNmeViewPagination);
	   webElement(mdThreatNmeViewPagination);
       scrollInnerScrollBar(webelementFrame);
	    thread();
	   try
	   {
		   if(totalDDValCount>5)
		   {
			   click(pagiEndArw);
			   takeScreenshot();
			   thread();
			   
			   click(pagiStartArw);
			   takeScreenshot();
			   thread();
			   if(totalDDValCount==6)
			   {
				 click(pagiNextArw);
				 takeScreenshot();
				 thread();
				 
				 click(pagiPreviousArw);
				 takeScreenshot();
				 thread();
				 
			   }
		   
		   
		   //Click Pagination Number 
		   Random randomPagi = new Random();
		   int randompge = randomPagi.nextInt((totalDDValCount-2)-4)+4;
	   	   driver.findElement(By.xpath("//div[@id='DataTables_Table_2_wrapper']//div[@class='dataTables_paginate paging_bootstrap pagination pagination-right']/ul/li["+randompge+"]/a")).click();
		   takeScreenshot();
		   click(pagiStartArw);
		   thread();
		   }
			else
			{
				System.out.println("***** Pagination is not available. Threat list view has 10 or less than 10 records *****");
			}
		} 
		catch(Exception e) 
		{
			e.printStackTrace();
		}		
	
   }
   @Test(priority=18)
   void threatListViewShowEntries() throws InterruptedException, IOException
   {
   
	   getTotalValuesIndd(mdThreatNmeViewRecsInfo);
	   webElement(mdThreatNmeViewPagination);
   	try
		{
			getObjectText(mdThreatNmeViewRecsInfo);
			
			if(Integer.parseInt(getActualObjectTxt.split("of ")[1].substring(0, 2).trim()) >=11)
			{
				selectTextFromDropdown(mdThreatNmeViewLength, "25");
				thread();
				takeScreenshot();
				
				selectTextFromDropdown(mdThreatNmeViewLength, "10");
				thread();
			}
			else
			{
				System.out.println("***** Pagination is not available in Threat List View. List View has 10 or less than 10 records. So we can't choose the list view length *****");
			}
		}
		catch(WebDriverException e)
		{
			e.printStackTrace();
		}
   	thread();
	}
   
   
   @Test(priority=19)
   void drPlanListViewPagination()
   {
	   getTotalValuesIndd(mdDRPLanViewPagination);
	   try
	   {
		   if(totalDDValCount>5)
		   {
			   click(pagiEndArw);
			   takeScreenshot();
			   thread();
			   
			   click(pagiStartArw);
			   takeScreenshot();
			   
			   thread();
			   if(totalDDValCount==6)
			   {
				 click(pagiNextArw);
				 takeScreenshot();
				 thread();
				 
				 click(pagiPreviousArw);
				 takeScreenshot();
				 thread();
				 
			   }
		   
		   
		   //Click Pagination Number 
		   Random randomPagi = new Random();
		   int randompge = randomPagi.nextInt((totalDDValCount-2)-4)+4;
	   	   driver.findElement(By.xpath("//div[@id='DataTables_Table_1_wrapper']//div[@class='dataTables_paginate paging_bootstrap pagination pagination-right']/ul/li["+randompge+"]/a")).click();
		   takeScreenshot();
		   click(pagiStartArw);
		   thread();
		   }
			else
			{
				System.out.println("***** Pagination is not available. DRPlan list view has 10 or less than 10 records *****");
			}
		} 
		catch(Exception e) 
		{
			e.printStackTrace();
		}		
	
   }
   @Test(priority=20)
   void drPlanListViewShowEntries() throws InterruptedException, IOException
   {
   
   	try
		{
			getObjectText(mdDRPlanViewRecsInfo);
			
			if(Integer.parseInt(getActualObjectTxt.split("of ")[1].substring(0, 2).trim()) >=11)
			{
				selectTextFromDropdown( mdDRPlanViewLength, "25");
				thread();
				takeScreenshot();
				
				selectTextFromDropdown( mdDRPlanViewLength, "10");
				thread();
			}
			else
			{
				System.out.println("***** Pagination is not available in DR plan List View. List View has 10 or less than 10 records. So we can't choose the list view length *****");
			}
		}
		catch(WebDriverException e)
		{
			e.printStackTrace();
		}
   	thread();
	}
   
  

}

  
    	
    

		     	


