package Scripts;

import java.awt.AWTException;
import java.io.IOException;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import static ObjectRepository.Incidents.*;
import static ObjectRepository.Locations.*;
import static ObjectRepository.Threats.*;
import static ObjectRepository.Alerts.*;
import static ObjectRepository.ManageDisaster.*;

import UIWrappers.Page;
import static UIWrappers.UIObjects.*;
import jxl.read.biff.BiffException;

public class Inc_ExportNUpdateStatus extends Page
{
	LoginLogout ll = new LoginLogout();
	MD_DeclareDisaster md=new MD_DeclareDisaster();
	Al_NewAlert al=new Al_NewAlert();
	String selectedLocationName,rAlertName,rAlertMessage,incLoc;
	String[] employeeListArray = new String[100];
	List<WebElement> employeeList;
	/*@Test(priority=0)
    void login() throws BiffException, IOException, InterruptedException
    {
	ll.loginToSIB();
    }*/
	
	@Test(priority=1)
	void incidentPageNavigation() throws InterruptedException
	{
		click(alertBtnInTopMenu);
		thread();
		click(incidentsMenu);
		thread();
	}
	
/*	@Test(priority=2)
	void incidentExport() throws InterruptedException, AWTException
	{
		
		verifyAssert(incExportDataBtn);
		thread();
		click(incExportDataBtn);
		thread();
		download();
		click(incSearchBox);
		thread();
		
	}*/
	
	@Test(priority=3)
	void updateStatus() throws InterruptedException, BiffException, IOException
	{
		getTotalValuesIndd(incIncidentLocListviewTbCnt);
		thread();
		//int rn=5;
		Random rand=new Random();
		int rn=rand.nextInt(totalDDValCount-1)+1;
		System.out.println("random number "+rn);
		//WebElement update=driver.findElement(By.xpath("//table[@id='incident_table']/tbody/tr["+rn+"]/td[8]/div/a"));
		thread();
		
		String incLoc=driver.findElement(By.xpath("//table[@id='incident_table']/tbody/tr["+rn+"]/td[2]")).getText();
		
		
	//	try
	//	{
		verifyAssert(By.xpath("//table[@id='incident_table']/tbody/tr["+rn+"]/td[8]/div/a"));
		thread();
		click(By.xpath("//table[@id='incident_table']/tbody/tr["+rn+"]/td[8]/div/a"));
		thread();
		getTotalValuesIndd(By.xpath("//table[@id='incident_table']/tbody/tr["+rn+"]/td[8]/div/ul/li"));
		thread();
		
		Random rand1=new Random();
		int rd=rand1.nextInt(totalDDValCount-1)+1;		
		String value=driver.findElement(By.xpath("//table[@id='incident_table']/tbody/tr["+rn+"]/td[8]/div/ul/li["+rd+"]")).getText();
		//String value="Raise an Alert";
		//String value="Raise a Threat Alert";
		//String value="";
		//int rd=5;
		
		if(value.equals("Resolved"))
		{
			click(By.xpath("//table[@id='incident_table']/tbody/tr["+rn+"]/td[8]/div/ul/li["+rd+"]"));
			thread();
			getObjectText(deleteConfPpMessage); 
			verifyAssertEquals("Confirm to Resolve incident?",getActualObjectTxt);
			thread();
			click(delConfOkBtn);
			thread();
		}
		
		else if(value.equals("Closed"))
		{
			click(By.xpath("//table[@id='incident_table']/tbody/tr["+rn+"]/td[8]/div/ul/li["+rd+"]"));
			thread();
			getObjectText(deleteConfPpMessage); 
			verifyAssertEquals("Confirm to Close incident?",getActualObjectTxt);
			thread();
			click(delConfOkBtn);
			thread();
			getObjectText(deleteConfPpMessage); 
			verifyAssertEquals("Incident status updated successfully",getActualObjectTxt);
			thread();
		}
		
		else if (value.equals("Raise an Alert"))
		{
			incLoc=driver.findElement(By.xpath("//table[@id='incident_table']/tbody/tr["+rn+"]/td[2]")).getText();
			click(By.xpath("//table[@id='incident_table']/tbody/tr["+rn+"]/td[8]/div/ul/li["+rd+"]"));
			thread();
			getObjectText(deleteConfPpMessage); 
			verifyAssertEquals("You will be redirected to Alert page, confirm to proceed?",getActualObjectTxt);
			thread();
			click(delConfOkBtn);
			thread();
			//verifyAssert(alSearchAlerts);
			//thread();
			//al.addNewAlert();
			
			getAlertsSheetFromExcel();
			
			click(alDrillAlertChkBox);	//	Drill Alert
			
			click(alModeOfCommEmailndSMS);	//	Email & SMS communication mode
			
			//	Alert Title
			Random randomAlert = new Random();
			int ralert = randomAlert.nextInt(alrt.getRows()-1)+1;
			rAlertName = alrt.getCell(0, ralert).getContents();
			enterText(alAlertTitle, rAlertName);
			
			//	Alert Message
			webElement(scEdtrFrame);
			switchToWEFrame(webelementFrame);
			rAlertMessage = alrt.getCell(1, ralert).getContents();
			enterText(scEdtrCommentsField, rAlertMessage);
			switchBackFromFrame();
			
			driver.findElement(By.id("cometchat_hide")).click();
			// closing chat window
			
			getTotalValuesIndd(alLocationsListViewCnt);
			scrollToBottom();
			
			for(int i=1;i<=totalDDValCount;i++)
			{
			selectedLocationName = driver.findElement(By.xpath("//table[@id='location_table']//tbody/tr["+i+"]/td[2]")).getText();
			if(selectedLocationName.equals(incLoc))
			//{
				break;
		//	}
			
			thread();
			}
			
			//Teams
			
			try
			{
			getTotalValuesIndd(alTmsTotalCnt);
			
			//int rTeam=1;
		
	//		if(totalDDValCount==0)
		//	{
		//		System.out.println("No teams to select");
		//	}
			if(totalDDValCount==1)
			{
				driver.findElement(By.xpath("//table[@id='team_table']/tbody/tr[1]/td[1]")).click();
				thread();
			}
			if(totalDDValCount>1)
			{
				Random randt=new Random();
				int rTeam=randt.nextInt(totalDDValCount-1)+1;
				driver.findElement(By.xpath("//table[@id='team_table']/tbody/tr["+rTeam+"]/td[1]")).click();
			}
			
			//Business Functions
			
			try
			{
			getTotalValuesIndd(alBusFnsTotalCnt);	
			if(totalDDValCount==1)
			{
			driver.findElement(By.xpath("//table[@id='business_function_table']//tbody/tr[1]/td[1]")).click();
			}
			
			if(totalDDValCount>1)
			{
				Random randb=new Random();
				int rBsFn=randb.nextInt(totalDDValCount-1)+1;
				driver.findElement(By.xpath("//table[@id='business_function_table']//tbody/tr["+rBsFn+"]/td[1]")).click();
			}
			
			}
			catch(Exception e)
			{
				System.out.println("No Business Functions");
				e.printStackTrace();
			}
			
			}
			catch(Exception e)
			{
				System.out.println("No teams");
				e.printStackTrace();
			}
			
			// Add employees
			try
			{
			employeeList = driver.findElements(By.xpath("//table[@id='employee_table']//tr//td[2]"));
			int employeeListArraySize=employeeList.size();
			for(int j=0;j<employeeListArraySize;j++)
			{
				employeeListArray[j] = employeeList.get(j).getText();
			}
			//if(employeeListArraySize==0)
			//{
				click(addEmployeeBtn);
				thread();
				click(selectAllCheckBoxAddEmployeeWindow);
				thread();
				click(addBtnAddEmployeeWindow);
				thread();
		//	}
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			scrollToBottom();
			thread();
			// getting employee list
			click(alSendBtn);
			click(alconfirmationpopupokbtn);
			thread();
			
	}

		
		else if(value.equals("Raise a Threat Alert"))
		{
			click(By.xpath("//table[@id='incident_table']/tbody/tr["+rn+"]/td[8]/div/ul/li["+rd+"]"));
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
			  
					if(driver.findElement(By.xpath("//table[@id='DataTables_Table_0']/tbody/tr["+i+"]/td[2]")).getText().equals(incLoc))
				       {
						String radio=driver.findElement(By.xpath("//table[@id='DataTables_Table_0']/tbody/tr["+i+"]/td[1]/input")).getAttribute("checked");
					    System.out.println("radio button "+radio);
						verifyAssertEquals("true",radio);
					    thread();
						break;
				       }
					
			}
						
				 try
					{					    
					    webElement( mdThreatSearchBox);        	
						scrollInnerScrollBar(webelementFrame);
						thread();
					   // md.verifySelectedThreatforLocation();
					    getTotalValuesIndd(mdThreatNmeInLstViewCnt);
				        
				       if(totalDDValCount==0)
				        {
				        	System.out.println("No threats to declare disaster");
				        }
				        else if(totalDDValCount==1)
				        {
				        	driver.findElement(By.xpath("//table[@id='DataTables_Table_2']/tbody/tr[1]/td[1]")).click();
				        	thread();
				        	md.addDisasterInfo();
					        md.selectModeofCommunication();
					        md.selectContactGrpToNotify();
					        md.verifynotifyAllEmployeesInThisLoc();

				        }
				        else
				        {
				        	Random rand3=new Random();
				        	int rth=rand3.nextInt(totalDDValCount-1)+1;
				        	driver.findElement(By.xpath("//table[@id='DataTables_Table_2']/tbody/tr["+rth+"]/td[1]")).click();
				        	thread();
				        	md.addDisasterInfo();
					        md.selectModeofCommunication();
					        md.selectContactGrpToNotify();
					        md.verifynotifyAllEmployeesInThisLoc();

				        
				        }
				        
				       thread();
				       }
					
			//	}
				catch(Exception e)
				{
					System.out.println("No teams to select");
					e.printStackTrace();
				}
				
			//}
			
			
			
		}
		
		else
		{
			click(By.xpath("//table[@id='incident_table']/tbody/tr["+rn+"]/td[8]/div/ul/li["+rd+"]"));
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
			  
					if(driver.findElement(By.xpath("//table[@id='DataTables_Table_0']/tbody/tr["+i+"]/td[2]")).getText().equals(incLoc))
				       {
						
						String radio=driver.findElement(By.xpath("//table[@id='DataTables_Table_0']/tbody/tr["+i+"]/td[1]/input")).getAttribute("checked");
					    System.out.println("radio button "+radio);
						verifyAssertEquals("true",radio);
					    thread();
					    break;
				       }
		     	}
			
			try
		     	{
					    webElement( mdThreatSearchBox);        	
						scrollInnerScrollBar(webelementFrame);
						thread();
					   // md.verifySelectedThreatforLocation();
					    
                        getTotalValuesIndd(mdThreatNmeInLstViewCnt);
				        
				        if(totalDDValCount==0)
				        {
				        	System.out.println("No threats to declare disaster");
				        }
				        
				        else if(totalDDValCount==1)
				        {
				        	driver.findElement(By.xpath("//table[@id='DataTables_Table_2']/tbody/tr[1]/td[1]")).click();
				        	thread();
				        	   md.verifyListofDRPlan();
						        md.addDisasterInfo();
						        md.selectModeofCommunication();
						        md.selectContactGrpToNotify();
						        md.verifynotifyAllEmployeesInThisLoc();
				        }
				        
				        else
				        	{
				        	Random rand3=new Random();
				        	int rth=rand3.nextInt(totalDDValCount-1)+1;
				        	driver.findElement(By.xpath("//table[@id='DataTables_Table_2']/tbody/tr["+rth+"]/td[1]")).click();
				        	thread();
				        	md.verifyListofDRPlan();
						    md.addDisasterInfo();
						    md.selectModeofCommunication();
						    md.selectContactGrpToNotify();
						    md.verifynotifyAllEmployeesInThisLoc();
				        	}
				          
				        thread();
				        }
				      
					
				
				catch(Exception e)
				{
					e.printStackTrace();
				}
				
			}
		}
		
		
	//	catch(Exception e)
	//	{
	//	
		//	System.out.println("Update Status is in disabled mode for this record");
	//	}
//	}
	
/*	@Test(priority=2)
	void logout() throws InterruptedException
	{
		ll.logoutSession();
		thread();
		closeWindow();
	} */

}
