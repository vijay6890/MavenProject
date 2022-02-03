package Scripts;

/***************************************************************************************************
 * 
 * @author Tamizhselvi
 * 
 * 
 * 
****************************************************************************************************/

import static Config.TakScreenshot.takeScreenshot;
import static ObjectRepository.Alerts.*;
import static ObjectRepository.Incidents.*;
import static ObjectRepository.Locations.*;
import static ObjectRepository.ManageDisaster.*;
import static ObjectRepository.Threats.*;
import static UIWrappers.UIObjects.*;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import UIWrappers.Page;
import jxl.read.biff.BiffException;
//import model.IncidentsDetails;

public class Inc_AddEdit extends Page {

	LoginLogout ll = new LoginLogout();
	MD_DeclareDisaster md=new MD_DeclareDisaster();
	
	//ArrayList <IncidentsDetails> Inclist = new ArrayList<IncidentsDetails>();
	
	String year ,incidentLocation,incidentType,incidentSpot,date,comments,time,incComment,incAffect,incSeverity;
	String currentTab;
	String modEdtincidentlocation,modEdtAreyouAff,modEdtSeverityLvl,modEdtTime, modEdtIncidentSpot, modEdtIncidentType,modifiedComments ;
	String incViewDate,incViewStatus,incViewComments,incSpot,incType;
	
    String selectlocation = "Select Location";
    String phincIncidentSpot = "Incident Spot";
    String phincIncidentType = "Incident Type";
    String phdate  = "Date";
    String phincTime= "Time(HH:MM)";
    String selectSeverityLevel = "Select Severity Level";
    String selectAreyouaffected = "Select Are you affected?";
    
	
  /* @Test(priority=0)
	void login() throws BiffException, IOException, InterruptedException
	{
	
		ll.loginToSIB();
		waitForPageLoad();
	} */

	@Test(priority=1)
	void verifyAddNewIncPage() throws InterruptedException
	{
		click(alertBtnInTopMenu);
		
		click(incidentsMenu);
	    waitForPageLoad();
	    
	    click(incAddBtn);	   
	    thread();
	}
	
	@Test(priority=2)
	void addNewIncident() throws BiffException, IOException, InterruptedException
	{
		getIncidentsSheetFromExcel();
				
		Random rrow = new Random();
		int rexRow = rrow.nextInt(inc.getRows()-1)+1;
		
		// Incident Location 
		click(incLocationArrow);
		getTotalValuesIndd(incLocationCnt);
		
		if(totalDDValCount > 1)
		{
			Random ran = new Random ();
			int rIncLoc = ran.nextInt(totalDDValCount-1)+1;
			incidentLocation = driver.findElement(By.id("incident_location_chzn_o_"+rIncLoc)).getText();
			enterText(incLocationSearchBox, incidentLocation);
			enterKeyInKyBord(incLocationSearchBox);
			thread();
			
			}
		
		    // Incident Spot
		   incSpot=inc.getCell(0,rexRow).getContents();
		   enterText(incIncidentSpot,inc.getCell(0,rexRow).getContents());
		   thread();
		
		    // Incident Type 
		   incType=inc.getCell(0,rexRow).getContents();
		    enterText(incIncidentType,inc.getCell(0,rexRow).getContents());
		    thread();
		
		   // Date 		
		   click(incIconCalendar);
		   click(incDatePickrDaysCalndr);
		   thread();
		
		   click(incDatePickrMonthsCalndr);
		   thread();
		
		   click(incCalndrLeftSideArrow);
		
		   // Select year 
		   getTotalValuesIndd(incGetTotalYearsCnt);			
			Random ranYear = new Random();
			int ryear = ranYear.nextInt(totalDDValCount-1)+1;		
			year = driver.findElement(By.xpath("//div[@class='datepicker-years']//tbody/tr/td/span["+ryear+"]")).getText();
			driver.findElement(By.xpath("//span[contains(text(),"+year+")]")).click();
			thread();
			
			//		Select Month
			getTotalValuesIndd(incGetTotalMonths);
			Random ranMonth = new Random();			
			int rmonth = ranMonth.nextInt(totalDDValCount-1)+1;		
			
			WebElement ranMonthNm = driver.findElement(By.xpath("//div[@class='datepicker-months']//tbody/tr/td/span["+rmonth+"]")); 
			ranMonthNm.click();
			thread();
			
			//		Select Date
			getTotalValuesIndd(incGetRandomDateRowCnt);
			Random rDateRow = new Random();
			int rDateR = rDateRow.nextInt(totalDDValCount-1)+1;		
						
			Random rDateCol = new Random();
			int rDateC = rDateCol.nextInt(7-1)+1;
						
			WebElement rDate = driver.findElement(By.xpath("//div[@class='datepicker-days']//tbody/tr["+rDateR+"]/td["+rDateC+"]"));
			String Date = rDate.getText();
			rDate.click();
			thread();
			
			 //Time 			
			time = inc.getCell(2,rexRow).getContents();
			enterText(incTime,time);
		    thread();
		    
		    // Severity Level 		    
		    click(incSeverityLvlArrow);
			getTotalValuesIndd(incSeverityLvlCnt);
			
			if(totalDDValCount > 1)
			{
				Random ran = new Random ();
				int rsl = ran.nextInt(totalDDValCount-1)+1;	
				incSeverity=driver.findElement(By.id("incident_severity_chzn_o_"+rsl)).getText();
				enterText(incSeverityLvlSearchBox,driver.findElement(By.id("incident_severity_chzn_o_"+rsl)).getText());
				enterKeyInKyBord(incSeverityLvlSearchBox);
				thread();
			}
		
			//Are you Affected			
			click(incAreyouAffArrow);
			getTotalValuesIndd(incAreyouAffCnt);
			
			if(totalDDValCount > 1)
			{
				Random ran = new Random ();
				int rayaff= ran.nextInt(totalDDValCount-1)+1;
				incAffect=driver.findElement(By.id("incident_affected_chzn_o_"+rayaff)).getText();
				enterText(incAreyouAffSearchBox,driver.findElement(By.id("incident_affected_chzn_o_"+rayaff)).getText());
				enterKeyInKyBord(incAreyouAffSearchBox);
				thread();
				
			}
			
				
			// What Happened Comments			
			WebElement ifram = driver.findElement(scEdtrFrame);
			driver.switchTo().frame(ifram);	
			incComment=inc.getCell(3,rexRow).getContents();
			enterText(scEdtrCommentsField, inc.getCell(3,rexRow).getContents());
			switchBackFromFrame();
			
			//Teams to notify
			getTotalValuesIndd(incTeamsTotalCount);
			try
			{
			if(totalDDValCount==0)
			{
				//System.out.println("No teams to notify");
			}
			else if(totalDDValCount==1)
			{
				driver.findElement(By.xpath("//table[@id='team_table']/tbody/tr[1]/td[1]")).click();
				thread();
			}
			else
			{
				Random rand2=new Random();
				int rt=rand2.nextInt(totalDDValCount-1)+1;
				driver.findElement(By.xpath("//table[@id='team_table']/tbody/tr["+rt+"]/td[1]")).click();
				thread();
				
			}
			}
			catch(Exception e)
			{
				//System.out.println("No teams to notify");
			}
			
			 //Scroll down the page
			webElement(incSubmitBtn);
			scrollInnerScrollBar(webelementFrame);
			thread();
			
		    //click submit button 
			click(incSubmitBtn);
			thread();
			takeScreenshot();
			
			//click confirmation button
		//	getObjectText(deleteConfPpMessage); 
		//	verifyAssertEquals("Confirm to report this incident?",getActualObjectTxt);
			thread();
			waitForElement(delConfOkBtn);
			click(delConfOkBtn);
			thread();
			
			//Verify  IncidentLocation Added Successfully message
			
			getObjectText(msgNotificationBar);
			try
			 {
			 verifyAssertEquals(incidentLocation+" Added Successfully!", getActualObjectTxt);
			 thread();
			 }
			 catch(Error e)
			 {
				 e.printStackTrace();
				// System.out.println(incidentLocation);
			 }
			 
		}
	
	    @Test(priority=3)
	    void verifyAddedIncidentlocationdetailsinlistview() throws InterruptedException
	    {
	    	getTotalValuesIndd(incIncidentLocListviewTbCnt);
	    	for(int i=1;i<3;i++)
	    	{
	    		String getAddedIncidentLocation = driver.findElement(By.xpath("//table[@id='incident_table']/tbody/tr["+i+"]/td")).getText();
	    		
	    		if(getAddedIncidentLocation.contains(incidentLocation))
	    				{
	    			       for(int j=2;j<8;j++)
	    			       {
	    			    	   
	    			    	   String ChkAddeddtls = driver.findElement(By.xpath("//table[@id='incident_table']/tbody/tr["+i+"]/td["+j+"] ")).getText();
	    			      
	    			       if(j==2)
	    			    	   verifyAssertEquals(incidentLocation,ChkAddeddtls);
	    			       if(j==3)
	    			    	   verifyAssertEquals(incidentSpot,ChkAddeddtls);
	    			       if(j==4)
	    			    	   verifyAssertEquals(incidentType,ChkAddeddtls);
	    			       if(j==5)
	    			    	   verifyAssertEquals(date,ChkAddeddtls);
	    			       if(j==6)
	    			    	   verifyAssertEquals(comments,ChkAddeddtls);
	    			    	break;   
	    			       
	    			      }
	    	            }
	     
	    	    thread();
	    	}
	    
		}
	    
	    @Test(priority=4)
	    void updateStatus() throws InterruptedException, AWTException
	    {
			
			String incLoc=driver.findElement(By.xpath("//table[@id='incident_table']/tbody/tr[1]/td[2]")).getText();
			
			try
			{
			verifyAssert(By.xpath("//table[@id='incident_table']/tbody/tr[1]/td[8]/div/a"));
			thread();
			click(By.xpath("//table[@id='incident_table']/tbody/tr[1]/td[8]/div/a"));
			thread();
			getTotalValuesIndd(By.xpath("//table[@id='incident_table']/tbody/tr[1]/td[8]/div/ul/li"));
			thread();
			Random rand1=new Random();
			int rd=rand1.nextInt(totalDDValCount-1)+1;
			
			String value=driver.findElement(By.xpath("//table[@id='incident_table']/tbody/tr[1]/td[8]/div/ul/li["+rd+"]")).getText();
			
		//	String value="";         //Add Comment,Resolved,Closed,Raise an Alert,Raise a Threat Alert
		//	int rd=6;                           //1,2,3,4,5
			
			if(value.equals("Add Comment"))
			{
				click(By.xpath("//table[@id='incident_table']/tbody/tr[1]/td[8]/div/ul/li["+rd+"]"));
				thread();
				getObjectText(incAddCmtTitle);
				verifyAssertEquals("Add Comment to "+incidentLocation+" Incident",getActualObjectTxt);
				thread();
				
				//WebElement ifram = driver.findElement(scEdtrFrame);
				WebElement ifram=driver.findElement(By.xpath("//div[@id='addMessagePopUp']/div[2]/div/div/div/iframe"));
				driver.switchTo().frame(ifram);			
				enterText(scEdtrCommentsField,inc.getCell(4,1).getContents());//"Test Message added"
				switchBackFromFrame();
				thread();
				
				click(incAddCmtBtn);
				thread();
				
				getObjectText(msgNotificationBar); 
				thread();
				verifyAssertEquals("Comment added to Incident successfully",getActualObjectTxt);
			}
			else if(value.equals("Resolved"))
			{
				
				click(By.xpath("//table[@id='incident_table']/tbody/tr[1]/td[8]/div/ul/li["+rd+"]"));
				thread();
				
				getObjectText(incResTitle);			
				verifyAssertEquals("Update "+incidentLocation+" status to Resolved",getActualObjectTxt);
				thread();
				
			    WebElement ifram = driver.findElement(By.xpath("//div[@id='statusChangePopUp']/div[2]/div/div/div/iframe"));
				driver.switchTo().frame(ifram);	
				enterText(scEdtrCommentsField,inc.getCell(4,2).getContents());//inc.getCell(4,2).getContents()
				switchBackFromFrame();
				thread();
								
				click(updatBtn);
				thread();
				
				getObjectText(msgNotificationBar); 
				thread();
				verifyAssertEquals("Incident status updated successfully",getActualObjectTxt);
			}
			
			else if(value.equals("Closed"))
			{
				click(By.xpath("//table[@id='incident_table']/tbody/tr[1]/td[8]/div/ul/li["+rd+"]"));
				thread();
				
				getObjectText(incResTitle);
				verifyAssertEquals("Update "+incidentLocation+" status to Closed",getActualObjectTxt);
				thread();
			
			    WebElement ifram = driver.findElement(By.xpath("//div[@id='statusChangePopUp']/div[2]/div/div/div/iframe"));
				driver.switchTo().frame(ifram);			
				enterText(scEdtrCommentsField, inc.getCell(4,3).getContents());
				switchBackFromFrame();
				thread();
				
				click(updatBtn);
				thread();
				
				//getObjectText(deleteConfPpMessage); 
				getObjectText(msgNotificationBar); 
				verifyAssertEquals("Incident status updated successfully",getActualObjectTxt);
				thread();
			}
			
			else if (value.equals("Raise an Alert"))
			{
				click(By.xpath("//table[@id='incident_table']/tbody/tr[1]/td[8]/div/ul/li["+rd+"]"));
				thread();
				getObjectText(deleteConfPpMessage); 
				verifyAssertEquals("You will be redirected to Alert page, confirm to proceed?",getActualObjectTxt);
				thread();
				click(delConfOkBtn);
				thread();
			//	verifyAssert(alSearchAlerts);
			//	thread();
	            click(alertBtnInTopMenu);
				
				click(incidentsMenu);
			    waitForPageLoad();
				
			}
			
			else if(value.equals("Raise a Threat Alert"))
			{
				click(By.xpath("//table[@id='incident_table']/tbody/tr[1]/td[8]/div/ul/li["+rd+"]"));
				thread();
				getObjectText(deleteConfPpMessage); 
				verifyAssertEquals("You will be redirected to Threat Alert page, confirm to proceed?",getActualObjectTxt);
				thread();
				click(delConfOkBtn);
				thread();
				verifyAssert(mdLocationsSearchBox);
				thread();
				
				webElement(mdLocationsSearchBox);        	
				scrollInnerScrollBar(webelementFrame);
				thread();
				
				getTotalValuesIndd(mdAffLocationLstViewCnt);
				thread();
				
				for(int i=1;i<=totalDDValCount;i++)
				{
				   try
			     	{
						if(driver.findElement(By.xpath("//table[@id='disaster_location_table']/tbody/tr["+i+"]/td[2]")).getText().equals(incLoc))
					       {
							
							String radio=driver.findElement(By.xpath("//table[@id='disaster_location_table']/tbody/tr["+i+"]/td[1]/input")).getAttribute("checked");
						   	verifyAssertEquals("true",radio);
						    thread();
						    
						   	webElement( mdThreatSearchBox);        	
							scrollInnerScrollBar(webelementFrame);
							thread();
						   // md.verifySelectedThreatforLocation();
						    getTotalValuesIndd(mdThreatNmeInLstViewCnt);
					        
					        if(totalDDValCount==0)
					        {
					        	System.out.println("No threats to declare disaster");
					        }
					        else
					        {
					        //	driver.findElement(By.xpath("//table[@id='DataTables_Table_2']/tbody/tr[1]/td[1]")).click();
					        //	thread();
					    
					        	Random rand3=new Random();
						        	int rth=rand3.nextInt(totalDDValCount-1)+1;
						        	driver.findElement(By.xpath("//table[@id='DataTables_Table_2']/tbody/tr["+rth+"]/td[1]")).click();
						        	thread();
						        	
						        
						        
					        }
					       }
						thread();
					}
					catch(Exception e)
					{
						e.printStackTrace();
					}
					
				}
				
				click(alertBtnInTopMenu);
				
				click(incidentsMenu);
			    waitForPageLoad();
				
			}
			
			else
			{
				rd=6;
				click(By.xpath("//table[@id='incident_table']/tbody/tr[1]/td[8]/div/ul/li["+rd+"]"));
				thread();
				getObjectText(deleteConfPpMessage); 
				verifyAssertEquals("You will be redirected to Disaster page, confirm to proceed?",getActualObjectTxt);
				thread();
				click(delConfOkBtn);
				thread();
				
				verifyAssert(mdLocationsSearchBox);
				thread();
				
				webElement(mdLocationsSearchBox);        	
				scrollInnerScrollBar(webelementFrame);
				thread();
				
				getTotalValuesIndd(mdAffLocationLstViewCnt);
				thread();
				
				for(int i=1;i<=totalDDValCount;i++)
				{
				   try
			     	{
						if(driver.findElement(By.xpath("//table[@id='disaster_location_table']/tbody/tr["+i+"]/td[2]")).getText().equals(incLoc))
					       {
							
							String radio=driver.findElement(By.xpath("//table[@id='disaster_location_table']/tbody/tr["+i+"]/td[1]/input")).getAttribute("checked");
						  	verifyAssertEquals("true",radio);
						    thread();
						    
						    webElement( mdThreatSearchBox);        	
							scrollInnerScrollBar(webelementFrame);
							thread();
						   // md.verifySelectedThreatforLocation();
						    getTotalValuesIndd(mdThreatNmeInLstViewCnt);
					        
					        if(totalDDValCount==0)
					        {
					        	System.out.println("No threats to declare disaster");
					        }
			
					        else
					        {
					        	
					        	Random rath=new Random();
					        	int rth=rath.nextInt(totalDDValCount-1)+1;
					        	driver.findElement(By.xpath("//table[@id='DataTables_Table_2']/tbody/tr["+rth+"]/td[1]")).click();
					        	thread();
					        	md.verifyListofDRPlan();
					        }
					       }
						thread();
					}
					catch(Exception e)
					{
						e.printStackTrace();
					}
					
				}
				click(alertBtnInTopMenu);
				
				click(incidentsMenu);
			    waitForPageLoad();
			}
			}
			
			catch(Exception e)
			{
			
				e.printStackTrace();
				//System.out.println("Update Status is in disabled mode for this record");
			}
			
	    }
	   
	    
	     
	/*  @Test(priority=4)
	  void EditIncidentLocationName() throws BiffException, IOException, InterruptedException
	  {
		  getIncidentsSheetFromExcel();
		  
		  click(incEditBtn);
		  thread();
		  
		  //Scroll down the page
  		  webElement(incClearBtn);
  		  scrollInnerScrollBar(webelementFrame);
  		
  		  // click clear button 
  		  click( incClearBtn);
  		  thread();
  		  
  		Random rrow = new Random();
		int rexRow = rrow.nextInt(inc.getRows()+1);
		
		// Incident Location 
		click(incLocationArrow);
		getTotalValuesIndd(incLocationCnt);
		
		if(totalDDValCount > 1)
		{
			Random ran = new Random ();
			int rIncLoc = ran.nextInt(totalDDValCount-1)+1;
			modEdtincidentlocation = driver.findElement(By.id("incident_location_chzn_o_"+rIncLoc)).getText().split(",")[0].trim();
			enterText(incLocationSearchBox, modEdtincidentlocation);
			enterKeyInKyBord(incLocationSearchBox);
			
		}
		
		    // Incident Spot
		
		    modEdtIncidentSpot = inc.getCell(0,rexRow).getContents().trim();
		    enterText(incIncidentSpot,modEdtIncidentSpot);
		    thread();
		
		    // Incident Type 
		
		    modEdtIncidentType = inc.getCell(1,rexRow).getContents().trim();
		    enterText(incIncidentType,modEdtIncidentType);
		    thread();
		
		   // Date 
		
		   click(incIconCalendar);
		   click(incDatePickrDaysCalndr);
		   thread();
		
		   click(incDatePickrMonthsCalndr);
		   thread();
		
		   click(incCalndrLeftSideArrow);
		
		   // Select year 
		

			getTotalValuesIndd(incGetTotalYearsCnt);			
			Random ranYear = new Random();
			int ryear = ranYear.nextInt(totalDDValCount-1)+1;		
			year = driver.findElement(By.xpath("//div[@class='datepicker-years']//tbody/tr/td/span["+ryear+"]")).getText();
			driver.findElement(By.xpath("//span[contains(text(),"+year+")]")).click();
			thread();
			
			//		Select Month
			getTotalValuesIndd(incGetTotalMonths);
			Random ranMonth = new Random();			
			int rmonth = ranMonth.nextInt(totalDDValCount-1)+1;		
			
			WebElement ranMonthNm = driver.findElement(By.xpath("//div[@class='datepicker-months']//tbody/tr/td/span["+rmonth+"]")); 
			ranMonthNm.click();
			thread();
			
			//		Select Date
			getTotalValuesIndd(incGetRandomDateRowCnt);
			Random rDateRow = new Random();
			int rDateR = rDateRow.nextInt(totalDDValCount-1)+1;		
						
			Random rDateCol = new Random();
			int rDateC = rDateCol.nextInt(7-1)+1;
						
			WebElement rDate = driver.findElement(By.xpath("//div[@class='datepicker-days']//tbody/tr["+rDateR+"]/td["+rDateC+"]"));
			String Date = rDate.getText();
			System.out.println("Date: "+Date); 
			rDate.click();
			thread();
			
			 //Time 
			
			modEdtTime = inc.getCell(2,rexRow).getContents();
			enterText(incTime,modEdtTime);
		    thread();
		    
		    // Severity Level 
		    
		    click(incSeverityLvlArrow);
			getTotalValuesIndd(incSeverityLvlCnt);
			
			if(totalDDValCount > 1)
			{
				Random ran = new Random ();
				int rsl = ran.nextInt(totalDDValCount-1)+1;
				modEdtSeverityLvl = driver.findElement(By.id("incident_severity_chzn_o_"+rsl)).getText().split(",")[0].trim();
				enterText(incSeverityLvlSearchBox,modEdtSeverityLvl);
				enterKeyInKyBord(incSeverityLvlSearchBox);
			}
		
			//Are you Affected
			click(incAreyouAffArrow);
			getTotalValuesIndd(incAreyouAffCnt);
			
			if(totalDDValCount > 1)
			{
				Random ran = new Random ();
				int rayaff= ran.nextInt(totalDDValCount-1)+1;
				modEdtAreyouAff = driver.findElement(By.id("incident_affected_chzn_o_"+rayaff)).getText().split(",")[0].trim();
				enterText(incAreyouAffSearchBox,modEdtAreyouAff);
				enterKeyInKyBord(incAreyouAffSearchBox);
			}
			
			// What Happened Comments
			
			WebElement ifram = driver.findElement(scEdtrFrame);
			driver.switchTo().frame(ifram);
			modifiedComments = inc.getCell(3,rexRow).getContents();
			enterText(scEdtrCommentsField, modifiedComments);
			switchBackFromFrame();
			
			 //Scroll down the page
			webElement(incSubmitBtn);
			scrollInnerScrollBar(webelementFrame);
			
		    //click submit button 
			click(incSubmitBtn);
			thread();
			takeScreenshot();
			
			//Verify  IncidentLocation Added Successfully message		 
			 getObjectText(msgNotificationBar);
			 thread();
			 verifyAssertEquals(modEdtincidentlocation+" Updated Successfully!", getActualObjectTxt);
			 thread();
			 
		  
		  }  */
	  
	/*       @Test(priority=5)
  	       void verifyEditedIncLocNameInListView() throws InterruptedException
  	       {
  		     for(int i=1; i<3; i++)
  		   {
  			String getmodIncLocNameInListView = driver.findElement(By.xpath("//table[@id='incident_table']/tbody/tr["+i+"]/td[2]")).getText();
  			
  			if(getmodIncLocNameInListView.contains(modEdtincidentlocation))
  			{
  				
  				for(int j=2; j<8; j++)
  				{
  					String getmodDetails = driver.findElement(By.xpath("//table[@id='incident_table']/tbody/tr["+i+"]/td["+j+"]")).getText();
  					
  					if(j==2)
  					{
							verifyAssertEquals(modEdtincidentlocation, getmodDetails);
  					}
  					else if(j==3)
  					{
							verifyAssertEquals(modEdtIncidentSpot, getmodDetails);
  					}
  					else if(j==4)
  					{
  						   verifyAssertEquals(modEdtIncidentType,getmodDetails);
  					}
  					//else if(j==5)
  				//	{
  				//		verifyAssertEquals(modEdtTime, getmodDetails);
  				//	}
  					else if(j==6)
  					{
  						verifyAssertEquals(modifiedComments.trim(), getmodDetails);
  					}
  				}
  			}
  		  }
  		}	*/
	       
	    @Test(priority=6)
        void verifyViewPage() throws IOException, InterruptedException
        {
	    	
	    	currentTab=driver.getWindowHandle();
        	//click(incViewBtn);
	    	thread();
	    	incViewDate=driver.findElement(By.xpath("//table[@id='incident_table']/tbody/tr[1]/td[6]")).getText();//list view data
	    	incViewStatus=driver.findElement(By.xpath("//table[@id='incident_table']/tbody/tr[1]/td[5]")).getText().trim();//list view Data
	    		    	
	    	driver.findElement(By.xpath("//table[@id='incident_table']/tbody/tr[1]/td[8]/a")).click();
	    	thread();
	    	 ArrayList<String> newTab = new ArrayList<String>(driver.getWindowHandles());
			 newTab.remove(currentTab);
			 
			 // change focus to new tab
			 driver.switchTo().window(newTab.get(0));
        	takeScreenshot();
        	//getObjectText(viewScreen);
        	getObjectText(viewPageHeader);
        	thread();
        	verifyAssertEquals("Incident Details",getActualObjectTxt);
        	thread();
        	incViewComments=driver.findElement(By.xpath("//div[@id='v_incident_comments']/p")).getText().trim();//view screen comments
        	
        	getTotalValuesIndd(incViewTtlRowCnt);
        	
        	for(int j=1;j<=totalDDValCount;j++)
        	{
        		for(int i=1;i<=4;i++)
        		{
        		String incViewDetails=driver.findElement(By.xpath("//div[@id='showdisplay_view']/fieldset/div["+j+"]/div/div["+i+"]/div/div")).getText();
        		
        		if(i==1 && j==1)
        		    verifyAssertEquals(incidentLocation.trim(),incViewDetails.trim());     //location name      		
        		if(i==2 && j==1)
        			verifyAssertEquals(incSpot.trim(),incViewDetails.trim());       //Spot
        		if(i==3 && j==1)
        			verifyAssertEquals(incType.trim(),incViewDetails.trim());        //type		
        		//if(i==4 && j==1)
        		//	verifyAssertEquals(incViewDate,incViewDetails);         //Date
        		
        		if(i==1 && j==2)
        			verifyAssertEquals(time,incViewDetails);                 //time
        		if(i==2 && j==2)
        			verifyAssertEquals(incSeverity,incViewDetails);           //severity
        		if(i==3 && j==2)
        			verifyAssertEquals(incAffect.toLowerCase(),incViewDetails.toLowerCase());             //Affect
        		
        		if(i==4 && j==3)
        			verifyAssertEquals(incViewStatus,incViewDetails.trim());         //status
        		}
        	}
        	
        	verifyAssertEquals(incComment.trim(),incViewComments);     //comments
        	//click(incViewCloseBtn);
        	thread();
        	 driver.close();
			 thread();
			
			 //switch to old window
			 driver.switchTo().window(currentTab);
        	 	
        }
	       @Test(priority=7)
	        void verifySerachFilterinListView() throws InterruptedException, IOException
	        {
	        	getTotalValuesIndd(incIncidentLocListviewTbCnt);
	        	
	        	Random random = new Random();
	        	int rrow = random.nextInt(totalDDValCount-1)+1;
	        	
	        	String incLocNameSearch = driver.findElement(By.xpath("//table[@id='incident_table']//tr["+rrow+"]/td[2]")).getText();
	        	
	        	//Search valid Incident location
	        	enterText(incSearchBox,incLocNameSearch);
	        	thread();
	        	getObjectText(incLstViewSearchRslt);
	        	verifyAssertEquals(incLocNameSearch.trim(), getActualObjectTxt);
	        	takeScreenshot();
	       		clear(incSearchBox);
	       		enterKeyInKyBord(incSearchBox);
	       		thread();
	       		
	       		
	       		//	Search Invalid Incident location
	       		enterText(incSearchBox, "Invalid data");
	       		thread();
	       		enterKeyInKyBord(incSearchBox);
	       		thread();
	       		getObjectText(noRecordsFoundMsg);
	       		thread();
	       		verifyAssertEquals(noMatchingRecordsFound, getActualObjectTxt);
	       		takeScreenshot();
	       		clear(incSearchBox);
	       		enterKeyInKyBord(incSearchBox);
	       		thread();
	       		
	        }
	        
           @Test(priority=8)
           void incInfopage() throws IOException, InterruptedException
           {
        	   click(incInfoBtn);
        	   takeScreenshot();
        	   getObjectText(incInfoPopup);
        	   click(incInfoCloseBtn);
        	   thread();
        	   
          }

         /* @Test(priority=9)
       	   void verifyClearndCancelBtnFunc() throws InterruptedException, IOException
       	   {
       		getTotalValuesIndd(incIncidentLocListviewTbCnt);
       		Random random = new Random();
       		int rrow = random.nextInt(totalDDValCount-1)+1;
       		
       		driver.findElement(By.xpath("//table[@id='incident_table']/tbody/tr["+rrow+"]/td[7]/a[1]")).click();
            thread();
       		
       		//Scroll down the page
   			webElement(incClearBtn);
   			scrollInnerScrollBar(webelementFrame);
   			
   		    //click clear button 
   			click(incClearBtn);
   			takeScreenshot();
       		
   			
       	   //Incident Location 
   			getObjectText(incidentLocationDefTxt);
       		verifyAssertEquals(selectlocation.trim(),getActualObjectTxt);
       		
       	   //Incident Spot 
       		getAttributePh(incIncidentSpot);
       		verifyAssertEquals(phincIncidentSpot , getAttribtePh);
       		
       		//Incident Type 
       		getAttributePh(incIncidentType);
       		verifyAssertEquals(phincIncidentType , getAttribtePh);
       		
       		//Date
       		getAttributePh(incDate);
       		verifyAssertEquals(phdate , getAttribtePh);
       		
       		//Time 
       		getAttributePh(incTime);
       		verifyAssertEquals(phincTime , getAttribtePh);
       		
       		//Severity Level 
       		getObjectText(severityLevelDefTxt);
       		verifyAssertEquals(selectSeverityLevel,getActualObjectTxt);
       		
       		//Are you affected 
       		getObjectText(areYouAffectedDefTxt);
       		verifyAssertEquals(selectAreyouaffected.trim(),getActualObjectTxt);
      		
       		//Comments
    		WebElement ifram = driver.findElement(scEdtrFrame);
    		driver.switchTo().frame(ifram);		
    		getObjectText(scEdtrCommentsField);
    		verifyAssertEquals("", getActualObjectTxt);
    		switchBackFromFrame();
    		thread();
    		
		    //click cancel button 
    		webElement(incCancelBtn);
   			scrollInnerScrollBar(webelementFrame);
   			
    		click(incCancelBtn);
    		thread();
    		verifyAssert(incSearchBox);
    		Thread.sleep(1000);
       		
       	   }*/
           
           @Test(priority=10)
		    void verifySelectAllCheckbox() throws IOException, InterruptedException 
		    {
		    	click(incSelectAllCheckbox);
		    	takeScreenshot();
		    	
		    	getTotalValuesIndd(incIncidentLocListviewTbCnt);
		    	for(int i=1; i<=totalDDValCount;i++)
		    	{
		    		boolean chkd = driver.findElement(By.xpath("//table[@id='incident_table']/tbody/tr["+i+"]/td/div/span/input")).isEnabled();
		    		Assert.assertEquals(true, chkd);
		    	}
		    	
		    	 click(incSelectAllCheckbox);
		    	 thread();
		     }  
           
        	@Test(priority=11)
           	void verifyShowEntriesDropDown() throws InterruptedException, IOException
           	{
           		try
           		{
           			getObjectText(incNameListViewRecsInfo);
           			
           			if(Integer.parseInt(getActualObjectTxt.split("of ")[1].split(" ")[0].trim()) > 10)

           			{
           				selectTextFromDropdown(incShowEntLength , "25");
           				thread();
           				takeScreenshot();
           				
           				selectTextFromDropdown(incShowEntLength , "10");
           				thread();
           			}
           		}
           		catch(WebDriverException e)
           		{
           			System.out.println("Pagination is not available in Incident List View. It contains 10 or less than 10 records");
           		}
           	}
        	
        	//To view the pagination 
            @Test(priority=12)
          	void incLocNameListViewPagination() throws IOException, InterruptedException
        	     {
        		   getTotalValuesIndd(incListViewPagination);
        		
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
        			int randomPge = randomPagi.nextInt(totalDDValCount-2)+2;
        			driver.findElement(By.xpath("//div[@id='incident_table_wrapper']/div[2]/div[2]/div/ul/li["+randomPge+"]/a")).click();
        			takeScreenshot();
        			click(pagiStartArw);
        			thread();
        		}		
        	}
            
          
            
            /*@Test(priority=13)
       	    void logout() throws InterruptedException
       	     {
       		  ll.logoutSession();
       		  thread();
       		  closeWindow();
       	     }*/
    }



