package Scripts;
/***************************************************************************************************
 * 
 * @author Tamizhselvi
 * 
 * 
 * 
****************************************************************************************************/
import static Config.TakScreenshot.*;

import static ObjectRepository.Locations.*;
import static ObjectRepository.TaskGroups.tskGrpViewPopup;

import org.openqa.selenium.interactions.Actions;
import static ObjectRepository.Tasks.*;

import static ObjectRepository.Threats.*;
import static UIWrappers.UIObjects.*;

import java.awt.AWTException;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

import java.io.File;
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
import model.TaskDetails;

public  class Tsk_AddEdit extends Page
{



	LoginLogout ll = new LoginLogout();
	
	String taskName, taskType, duration, taskId, employeeAssigned, Status, Comments , TskTypetskMedit, verifyAssertEquals,TaskMassEdit, mEdttaskType, mEdtemployeeAssigned,getAttributePh,getObjectText;
	String PhtskTaskName = "Task Name";
	String PhtskDuration = "Duration(Days:Hours:Minutes)";
	String PhtskTaskId = "Task ID";
	String selectEmployeeAssigned = "Select Employee Assigned";
	String selectAddNewTaskType = "Select/Add New Task Type ";
	String modifiedEmployeeAssigned,modifiedTaskName,modifiedtskDuration,modifiedtskTaskId,modifiedTsktype,modifiedComments;
	
	ArrayList<TaskDetails> tskList = new ArrayList<TaskDetails>();



  /*@Test(priority=0)
	void login() throws BiffException, IOException, InterruptedException
	{
		ll.loginToSIB();
	}  */  
	
	@Test(priority=1)
	 void verifyAddNewTaskPge() throws InterruptedException
	{
		click(tasksInMainMenu);
		thread();
	   click(tskOvrAddBtn);
		thread();
		verifyAssert(tskTaskName);
	}
	
	@Test(priority=2)
	void addNewTaskName() throws BiffException, IOException, InterruptedException
	{
		
		getTasksSheetFromExcel();
		
		//		DON'T UN COMMENT BELOW CODE		//
		/*if(tskList.size() == 0)
	    {
			getTasksSheetFromExcel();
			
			for(int row=1; row<tsk.getRows(); row++)
			{
				TaskDetails taskDetails = new TaskDetails();	
				
				for(int col=0; col<tsk.getColumns(); col++)
				{					
					if(col == 0)						
						taskDetails.setTaskName(tsk.getCell(col, row).getContents());
					if(col == 1)
						taskDetails.setDuration(tsk.getCell(col, row).getContents());
					if(col == 2)
						taskDetails.setTaskId(tsk.getCell(col, row).getContents());					
					if(col == 3)
						taskDetails.setTaskDescription(tsk.getCell(col, row).getContents());
				}				
				tskList.add(taskDetails);
			}			
	    }
		
	    Random random = new Random();
		int ranRow = random.nextInt(tsk.getRows()-1)+1; 
		
		if(tskList.size() > 0)
	   {
			TaskDetails taskDetails  = tskList.get(ranRow);
			
			//	Task Name 
			taskName = taskDetails.getTaskName().trim();
			System.out.println("Task Name: "+taskDetails.getTaskName());
			enterText(tskTaskName, taskName);
			thread();
			
			//	Task Type	
			click(tskTaskTypeArrow);
			getTotalValuesIndd(tskTaskTypeCnt);
			if(totalDDValCount > 1)
			{
				Random tskType = new Random();
				int rtskType = tskType.nextInt(totalDDValCount-1)+1;
				taskType = driver.findElement(By.id("task_type_chzn_o_"+rtskType)).getText();
				System.out.println("Task Type: "+taskType);
				enterText(tskTaskTypeSearchBox, taskType);
				enterKeyInKyBord(tskTaskTypeSearchBox);
			}			
			
			//	Duration
			duration = taskDetails.getDuration().trim();
			System.out.println("Duration: "+duration);
			enterText(tskDuration, duration);
			Thread.sleep(2000);
			
			//	Task ID
			taskId = taskDetails.getTaskId().trim();
			enterText(tskTaskId, taskDetails.getTaskId());
						
			//	Employee Assigned
			click(tskEmployeeAssignedArrow);
			getTotalValuesIndd(tskEmployeeAssignedCnt);
			if(totalDDValCount > 1)
			{
				Random empAsigned = new Random();
				int rEmpAsigned = empAsigned.nextInt(totalDDValCount-1)+1;
				employeeAssigned = driver.findElement(By.id("principal_resource_chzn_o_"+rEmpAsigned)).getText().split(",")[0];
				enterText(tskEmployeeAssignedSearchBox, employeeAssigned);
				enterKeyInKyBord(tskEmployeeAssignedSearchBox);
			}			
			
			//	Task Description
			WebElement ifram = driver.findElement(scEdtrFrame);
			driver.switchTo().frame(ifram);			
			System.out.println("Desc: "+taskDetails.getTaskDescription().trim());
			enterText(scEdtrCommentsField, taskDetails.getTaskDescription().trim());
			Thread.sleep(2000);
					
			switchBackFromFrame();*/
		
		Random rrow = new Random();
		int rexRow = rrow.nextInt(tsk.getRows()-1)+1;

		System.out.println("TTR: "+tsk.getRows());
		
		//		Task Name
		taskName = tsk.getCell(0, rexRow).getContents();
		enterText(tskTaskName, taskName);
		thread();
		
		//		Task Type
		click(tskTaskTypeArrow);
		getTotalValuesIndd(tskTaskTypeCnt);
		if(totalDDValCount > 1)
		{
			Random ran = new Random();
			int rtype = ran.nextInt(totalDDValCount-1)+1;
			taskType=driver.findElement(By.id("task_type_chzn_o_"+rtype)).getText().split(",")[0];
			enterText(tskTaskTypeSearchBox,taskType);
			enterKeyInKyBord(tskTaskTypeSearchBox);
		}
		
		//		Duration
		duration=tsk.getCell(1, rexRow).getContents();
		enterText(tskDuration,duration);
		thread();
		
		//		Task Id
		int n=100;
		Random rnd=new Random();
		int rInt=rnd.nextInt(n-1)+1;
		taskId=tsk.getCell(2, rexRow).getContents()+rInt;
		enterText(tskTaskId, taskId);
		thread();
		
		//		Employee Assigned
		click(tskEmployeeAssignedArrow);
		getTotalValuesIndd(tskEmployeeAssignedCnt);
		if(totalDDValCount > 1)
		{
			Random remp = new Random();
			int rempAsign = remp.nextInt(totalDDValCount-1)+1;
			employeeAssigned=driver.findElement(By.id("principal_resource_chzn_o_"+rempAsign)).getText().split(",")[0];
			enterText(tskEmployeeAssignedSearchBox,employeeAssigned);
			enterKeyInKyBord(tskEmployeeAssignedSearchBox);
			thread();
		}
		
		//Alternate Employee
		click(tskAltrEmployeeDDArrow);
		getTotalValuesIndd(tskAltrEmployeeCnt);
		if(totalDDValCount>=1)
		{
			Random rAemp=new Random();
			int rAltrEmp=rAemp.nextInt(totalDDValCount-1)+1;
			String rAlterEmp=driver.findElement(By.id("alternate_resource_chzn_o_"+rAltrEmp)).getText().split(",")[0];
			thread();
			enterText(tskAltrEmployeeSearchBox,rAlterEmp);
			enterKeyInKyBord(tskAltrEmployeeSearchBox);
			thread();
		}
		
		//Comments
		WebElement ifram = driver.findElement(scEdtrFrame);
		driver.switchTo().frame(ifram);	
		Comments=tsk.getCell(3, rexRow).getContents();
		enterText(scEdtrCommentsField,Comments);
		switchBackFromFrame();
		
	    //Scroll down the page
			webElement(tskSubmitBtn);
			scrollInnerScrollBar(webelementFrame);
			
		//click submit button 
			click(tskSubmitBtn);
			thread();
			takeScreenshot();    
			
			try
		    {
				
				
				   File newFile = new File(xlFilesLocation);
					if (newFile.exists())
					            {
						            getObjectText(msgNotificationBar);
						            click(tskClearBtn);
						            thread();
									addNewTaskName();
					            }
				 }
			catch(Exception e)
			{
				e.printStackTrace();
				
			}
			 thread();
			 
		//Verify Task Name Added Successfully message		 
		 getObjectText(msgNotificationBar);
		 thread();
		 verifyAssertEquals(taskName+" Successfully Added", getActualObjectTxt);
		 thread();
		
	}
	

	@Test(priority=3)
	void verifyAddedtasknamedetailsinlistview() throws InterruptedException
	{
				getTotalValuesIndd(tsktaskNameLstViewTtl);
				for(int i=1; i<3; i++)
				{
					String getAddedTskName = driver.findElement(By.xpath("//table[@id='tasks_table']/tbody/tr["+i+"]/td[2]")).getText();
					if(getAddedTskName.contains(taskName))
					{
						for(int j=2; j<8; j++)
						{
						
							String ChkAddedDtls = driver.findElement(By.xpath("//table[@id='tasks_table']/tbody/tr["+i+"]/td["+j+"]")).getText();
							//driver.manage().timeouts().implicitlyWait(78 ,TimeUnit.SECONDS);
							if(j ==2)
								verifyAssertEquals(taskName, ChkAddedDtls);
							if(j ==3)
								verifyAssertEquals(taskId, ChkAddedDtls);
							if(j==4)
								verifyAssertEquals(taskType, ChkAddedDtls);
							if(j==5)
								verifyAssertEquals(duration, ChkAddedDtls);
							if(j==6)
								verifyAssertEquals(employeeAssigned, ChkAddedDtls);
							//if(j==7)
								//verifyAssertEquals(Status, ChkAddedDtls);
							if(j==8)
								verifyAssertEquals(Comments, ChkAddedDtls);							
							break;
						}
					}			
				}
				
				thread();
			}
		

		//Relationship Mapping for Task to employees 
			
		  @Test(priority=4)
			void maptasktoEmployees() throws IOException, InterruptedException
			{
				click(employeesTb);
				click(rltnAddRemoveRltnsBtn);
				verifyAssert(mapMappingPopup);
				thread();
				
				//click to mapping the employees Names in Relationship task 
				
				doMappingt();
				takeScreenshot();
				click(mapSubmitBtn);
				thread();
			
				//Verify task name successfully added message 
				
				getObjectText(msgNotificationBar);
				verifyAssertEquals(taskName+" successfully mapped to Employees" , getActualObjectTxt);
				takeScreenshot();
				thread();
				
				}
			 
          
		// Mapping employees search filter 
			
			@Test(priority=5)
			void searchMappedEmployees() throws IOException, InterruptedException
			{
				getTotalValuesIndd(tskRltnEmployeesCnt);
				
				Random empName = new Random();
				int rempName = empName.nextInt(totalDDValCount-1)+1;
				
				//	Search Valid Employee Name
				String employeName = driver.findElement(By.xpath("//table[@id='rel_resources']/tbody/tr["+rempName+"]/td")).getText();
			
				enterText(tskRltnEmployeesSearchBox, employeName);
				getObjectText(tskRltnEmployeesSrchRslt);
				verifyAssertEquals(employeName, getActualObjectTxt);
				takeScreenshot();
				clear(tskRltnEmployeesSearchBox);
				enterKeyInKyBord(tskRltnEmployeesSearchBox);
				thread();
				
				//	Search Invalid Employee Name
				enterText(tskRltnEmployeesSearchBox, "Inv Emp Name");
				getObjectText(noRecordsFoundMsg);
				verifyAssertEquals(noMatchingRecordsFound, getActualObjectTxt);
				takeScreenshot();
				clear(tskRltnEmployeesSearchBox);
				enterKeyInKyBord(tskRltnEmployeesSearchBox);
				thread();
			}
		
			//Relationship Mapping for Task to teams
			
			@Test(priority=6)
			void maptaskToTeams() throws InterruptedException, IOException
			{
				click(tskTeamsTb);
				thread();
				click(rltnAddRemoveRltnsBtn);
				verifyAssert(mapMappingPopup);
				thread();
				getObjectText(mapMappingPpTitleNm);
				thread();
				verifyAssertEquals("Map Teams To "+taskName, getActualObjectTxt);
				thread();
				
				doMappingt();
				takeScreenshot();
				click(mapSubmitBtn);
				thread();
				
				getObjectText(msgNotificationBar);
				verifyAssertEquals(taskName+" successfully mapped to Teams", getActualObjectTxt);
				takeScreenshot();
				thread();
			}

			@Test(priority=7)
			void searchMappedTeams() throws IOException, InterruptedException
			{
				//		Search with Valid Details
				getTotalValuesIndd(tskRltnTeamsCnt);
				Random random = new Random();
				int rTms = random.nextInt(totalDDValCount-1)+1;
				String getTeamsName = driver.findElement(By.xpath("//table[@id='rel_resource_grp']/tbody/tr["+rTms+"]/td")).getText();
				enterText(tskRltnTeamsSearchBox, getTeamsName);
			    getObjectText(tskRltnTeamsSearchRslt);
				verifyAssertEquals(getTeamsName, getActualObjectTxt);
				takeScreenshot();
				clear(tskRltnTeamsSearchBox);
				enterKeyInKyBord(tskRltnTeamsSearchBox);
				thread();
				
             // Search Invalid Team Name
				enterText(tskRltnTeamsSearchBox, "Inv Team Name");
				getObjectText(noRecordsFoundMsg);
				verifyAssertEquals(noMatchingRecordsFound, getActualObjectTxt);
				takeScreenshot();
				clear(tskRltnTeamsSearchBox);
				enterKeyInKyBord(tskRltnTeamsSearchBox);
				thread();
			}		

			//Relationship Mapping for Task to Contacts
			
			@Test(priority=8)
			void maptasktoContacts() throws InterruptedException, IOException
			{
				click(contactTb);
				click(rltnAddRemoveRltnsBtn);
				thread();
				verifyAssert(mapMappingPopup);
				thread();
				getObjectText(mapMappingPpTitleNm);
				verifyAssertEquals("Map Contacts To "+taskName, getActualObjectTxt);
				
				doMappingt();
				click(mapSubmitBtn);
				thread();
				
				getObjectText(msgNotificationBar);
				verifyAssertEquals(taskName+" successfully mapped to Contacts", getActualObjectTxt);
				takeScreenshot();
				thread();
			}
			
			@Test(priority=9)
			void searchMappedContacts() throws IOException, InterruptedException
			{
				getTotalValuesIndd(tskRltnContactsCnt);
				
				//	Search Valid Contact Name
				Random cnt = new Random();
				int rcnt = cnt.nextInt(totalDDValCount-1)+1;		
				String Contacts = driver.findElement(By.xpath("//table[@id='rel_contacts']/tbody/tr["+rcnt+"]/td")).getText();
				enterText(tskRltnContactsSearchBox, Contacts);
			
				getObjectText(tskRltnContactsSrchRslt);
				verifyAssertEquals(Contacts, getActualObjectTxt);
				takeScreenshot();
				clear(tskRltnContactsSearchBox);
				thread();
				enterKeyInKyBord(tskRltnContactsSearchBox);
				thread();
				
				//	Search Invalid Contact Name
				enterText(tskRltnContactsSearchBox, "Inv Contacts");
				getObjectText(noRecordsFoundMsg);
				verifyAssertEquals(noMatchingRecordsFound, getActualObjectTxt);
				takeScreenshot();
				clear(tskRltnContactsSearchBox);
				enterKeyInKyBord(tskRltnContactsSearchBox);
				thread();
			}
			
			//Relationship Mapping for Task to Contact Group 
			@Test(priority=10)	
			void maptasktoContactGroups() throws InterruptedException, IOException
			{
				click(contactGroupsTb);
				click(rltnAddRemoveRltnsBtn);
				thread();
				verifyAssert(mapMappingPopup);
				thread();
				getObjectText(mapMappingPpTitleNm);
				thread();
				verifyAssertEquals("Map Contact Groups To "+taskName, getActualObjectTxt);
				 
				doMappingt();
				click(mapSubmitBtn);
				thread();
				
				getObjectText(msgNotificationBar);
				verifyAssertEquals(taskName+" successfully mapped to Contact Groups", getActualObjectTxt);
				takeScreenshot();
				thread();
			}
			
			@Test(priority=11)
			void searchMappedContactGroups() throws IOException, InterruptedException
			{
				
				getTotalValuesIndd(tskRltnContactGroupscnt);
				
				//	Search Valid Contact Name
				Random cntgrp = new Random();
				int rCntgrp = cntgrp.nextInt(totalDDValCount-1)+1;		
				String ContactGroups = driver.findElement(By.xpath("//table[@id='rel_contact_grp']/tbody/tr["+rCntgrp+"]/td")).getText();
				enterText(tskRltnContactGroupsSearchBox, ContactGroups);
				getObjectText(tskRltnContactGroupsSrchRslt);
				verifyAssertEquals(ContactGroups, getActualObjectTxt);
				takeScreenshot();
				clear(tskRltnContactGroupsSearchBox);
				enterKeyInKyBord(tskRltnContactGroupsSearchBox);
				thread();
				
				//	Search Invalid Contact Name
				enterText(tskRltnContactGroupsSearchBox, "Inv contactGroups");
				enterKeyInKyBord(tskRltnContactGroupsSearchBox);
				thread();
				getObjectText(noRecordsFoundMsg);
				verifyAssertEquals(noMatchingRecordsFound, getActualObjectTxt);
				takeScreenshot();
				clear(tskRltnContactGroupsSearchBox);
				enterKeyInKyBord(tskRltnContactGroupsSearchBox);
				thread();
			
		}
		    
			//Relationship Mapping for Task to Asset 
			@Test(priority=12)	
			void maptasktoAssets() throws InterruptedException, IOException
			{
				click(assetsTb);
				click(rltnAddRemoveRltnsBtn);
				thread();
				verifyAssert(mapMappingPopup);
				thread();
				
				getObjectText(mapMappingPpTitleNm);
				verifyAssertEquals("Map Assets To "+taskName, getActualObjectTxt);
				
				doMappingt();
				click(mapSubmitBtn);
				thread();
				
				getObjectText(msgNotificationBar);
				verifyAssertEquals(taskName+" successfully mapped to Assets", getActualObjectTxt);
				takeScreenshot();
				thread();
			}
			
			@Test(priority=13)
			void searchMappedAssets() throws IOException, InterruptedException
			{
				getTotalValuesIndd(tskRltnAssetsCnt);
				
				//	Search Valid Assert Name
				Random astName = new Random();
				int rastName = astName.nextInt(totalDDValCount-1)+1;
				thread();
				//	Search Valid Asset Name
				String assetName = driver.findElement(By.xpath("//table[@id='rel_assets']/tbody/tr["+rastName+"]/td")).getText();
				enterText(tskRltnAssetsSearchBox, assetName);
				getObjectText(tskRltnAssetsSrchRslt);
				verifyAssertEquals(assetName, getActualObjectTxt);
				takeScreenshot();
				clear(tskRltnAssetsSearchBox);
				enterKeyInKyBord(tskRltnAssetsSearchBox);
				
				//	Search Invalid Asset Name
				enterText(tskRltnAssetsSearchBox, "Inv Asset Name");
				enterKeyInKyBord(tskRltnAssetsSearchBox);
				thread();
				getObjectText(noRecordsFoundMsg);
				verifyAssertEquals(noMatchingRecordsFound, getActualObjectTxt);
				takeScreenshot();
				clear(tskRltnAssetsSearchBox);
				enterKeyInKyBord(tskRltnAssetsSearchBox);
				thread();
			}
			
			
			//Relationship Mapping for Task to Assert Group  
			@Test(priority=14)	
			void maptasktoAssertGroup() throws InterruptedException, IOException
			
			{
				click(assetGroupsTb);
				click(rltnAddRemoveRltnsBtn);
				thread();
				verifyAssert(mapMappingPopup);
				
				getObjectText(mapMappingPpTitleNm);
				verifyAssertEquals("Map Asset Groups To "+taskName, getActualObjectTxt);
				
				doMappingt();
				click(mapSubmitBtn);
				thread();
				
				getObjectText(msgNotificationBar);
				verifyAssertEquals(taskName+" successfully mapped to Asset Groups", getActualObjectTxt);
				takeScreenshot();
				thread();
			}  
			
			@Test(priority=15)
			void searchMappedAssertGroups() throws IOException, InterruptedException
			{
				
				getTotalValuesIndd(tskRltnassetGroupsCnt);
				
				//	Search Valid Assert Group 
				Random Assgrp = new Random();
				int rAssgrp = Assgrp.nextInt(totalDDValCount-1)+1;		
				String assetGroups = driver.findElement(By.xpath("//table[@id='rel_asset_grp']/tbody/tr["+rAssgrp+"]/td")).getText();
				enterText(tskRltnassetGroupsSearchBox, assetGroups);
				getObjectText(tskRltnassetGroupsSrchRslt);
				thread();
				verifyAssertEquals(assetGroups, getActualObjectTxt);
				takeScreenshot();
				clear(tskRltnassetGroupsSearchBox);
				enterKeyInKyBord(tskRltnassetGroupsSearchBox);
				thread();
				
				//	Search Invalid Assert Group 
				enterText(tskRltnassetGroupsSearchBox, "Inv AssertGroups");
				enterKeyInKyBord(tskRltnassetGroupsSearchBox);
				thread();
				getObjectText(noRecordsFoundMsg);
				verifyAssertEquals(noMatchingRecordsFound, getActualObjectTxt);
				takeScreenshot();
				clear(tskRltnassetGroupsSearchBox);
				enterKeyInKyBord(tskRltnassetGroupsSearchBox);
				thread();
			}
		
			//Relationship Mapping for Task to Business Function 
			@Test(priority=16)
			void mapTaskToBusinessFunctions() throws InterruptedException, IOException
			{
				click(businessFunctionsTb);
				click(rltnAddRemoveRltnsBtn);
				verifyAssert(mapMappingPopup);
				
				getObjectText(mapMappingPpTitleNm);
				verifyAssertEquals("Map Business Functions To "+taskName ,  getActualObjectTxt);
				
				doMappingt();
				takeScreenshot();
				click(mapSubmitBtn);
				thread();
				
				getObjectText(msgNotificationBar);
				verifyAssertEquals(taskName+" successfully mapped to Business Functions", getActualObjectTxt);
				takeScreenshot();
				thread();
			}
			
			@Test(priority=17)
			void searchMappedBusinessFunctions() throws IOException, InterruptedException
			{
				//		Search with Valid Details
				getTotalValuesIndd(tskRltnBusinesFuncCnt);
				Random random = new Random();
				int rBusiFunc = random.nextInt(totalDDValCount-1)+1;
				String getBusinessFunctionName = driver.findElement(By.xpath("//table[@id='rel_processes']/tbody/tr["+rBusiFunc+"]/td")).getText();
				enterText(tskRltnBusinesFuncSearchBox, getBusinessFunctionName);
				
				getObjectText(tskRltnBusinessFuncSearchRslt);
				verifyAssertEquals(getBusinessFunctionName, getActualObjectTxt);
				takeScreenshot();
				clear(tskRltnBusinesFuncSearchBox);
				enterKeyInKyBord(tskRltnBusinesFuncSearchBox);
				thread();
				
				//	Search with Invalid Details
				enterText(tskRltnBusinesFuncSearchBox, "Inv Bus Func Name");
				enterKeyInKyBord(tskRltnBusinesFuncSearchBox);
				thread();
				getObjectText(noRecordsFoundMsg);
				verifyAssertEquals(noMatchingRecordsFound, getActualObjectTxt);
				takeScreenshot();
				clear(tskRltnBusinesFuncSearchBox);
				enterKeyInKyBord(tskRltnBusinesFuncSearchBox);
				thread();
			}

			//Relationship Mapping for Task to Task Group 
			@Test(priority=18)
			void mapTasktoTaskGroups() throws InterruptedException, IOException
			{
				click(taskGroupsTb);
				click(rltnAddRemoveRltnsBtn);
				verifyAssert(mapMappingPopup);
				
				getObjectText(mapMappingPpTitleNm);
				verifyAssertEquals("Map Task Groups To "+taskName ,  getActualObjectTxt);
				
				doMappingt();
				takeScreenshot();
				click(mapSubmitBtn);
				thread();
				
				getObjectText(msgNotificationBar);
				verifyAssertEquals(taskName+" successfully mapped to Task Groups" ,  getActualObjectTxt);             
				takeScreenshot();
				thread();
			}
			
			@Test(priority=19)
			void searchMappedTaskGroups()throws InterruptedException, IOException
			{
				//		Search with Valid Details
				getTotalValuesIndd(tskRltntskgrpCnt);
				Random random = new Random();
				int rtskgrp=random.nextInt(totalDDValCount-1)+1;
				String gettskgrp = driver.findElement(By.xpath("//table[@id='rel_task_grp']/tbody/tr["+rtskgrp+"]/td")).getText();
				enterText(tskRltntskgrpSearchBox, gettskgrp);
				
				getObjectText(tskRltntskgrpSearchRslt);
				verifyAssertEquals(gettskgrp, getActualObjectTxt);
				takeScreenshot();
				clear(tskRltntskgrpSearchBox);
				enterKeyInKyBord(tskRltntskgrpSearchBox);
				thread();
				
				//	Search with Invalid Details
				enterText(tskRltntskgrpSearchBox, "Inv Task Group");
				enterKeyInKyBord(tskRltntskgrpSearchBox);
				thread();
				getObjectText(noRecordsFoundMsg);
				verifyAssertEquals(noMatchingRecordsFound, getActualObjectTxt);
				takeScreenshot();
				clear(tskRltntskgrpSearchBox);
				enterKeyInKyBord(tskRltntskgrpSearchBox);
				thread();
			}
			
			

          /*@Test(priority=20)
            void Documentupload() throws IOException, InterruptedException, AWTException
            {
            	
    	        click(docuemntTb);
            	thread();
            	//Scroll down the page
    			webElement(choosefiles);
    			scrollInnerScrollBar(webelementFrame);
    			
    		    //click choosefiles button 
    			click(choosefiles);
    			thread();
    			takeScreenshot();
            	
               //put path to your image in a clipboard
                StringSelection ss = new StringSelection("D:\\SIB\\Logo\\FatPipe_logo.jpg");    
                Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
        
               // thread();
                //imitate mouse events like ENTER, CTRL+C, CTRL+V
                Robot robot = new Robot();
                robot.keyPress(KeyEvent.VK_CONTROL);
                robot.keyPress(KeyEvent.VK_V);
                robot.keyRelease(KeyEvent.VK_V);
                robot.keyRelease(KeyEvent.VK_CONTROL);
                robot.keyPress(KeyEvent.VK_ENTER);
                robot.keyRelease(KeyEvent.VK_ENTER);
                // click to upload the files 
                 click(docUpload);
                 thread();
              }*/
                
			
			 @Test(priority=21)
	        	void verifyClearndCancelBtnFunc() throws InterruptedException, IOException
	        	{
	        		getTotalValuesIndd(tsktaskNameLstViewTtl);
	        		Random random = new Random();
	        		int rrow = random.nextInt(totalDDValCount-1)+1;
	        		
	        		WebElement taskName = driver.findElement(By.xpath("//table[@id='tasks_table']/tbody/tr["+rrow+"]/td[2]"));
	        		taskName.click();
	        		//Edit Button 
	        		driver.findElement(By.xpath("//table[@id='tasks_table']/tbody/tr["+rrow+"]/td[9]/a[1]")).click();
	        		thread();
	        		
	        		//Scroll down the page
	    			webElement(tskClearBtn);
	    			scrollInnerScrollBar(webelementFrame);
	    			
	    		    //click clear button 
	    			click(tskClearBtn);
	    			thread();
	    			takeScreenshot();
	        		
	    			
	        	    //TaskName
	        		getAttributePh(tskTaskName);
	        		verifyAssertEquals(PhtskTaskName , getAttribtePh);
	        		
	        		// TaskType
	        		getObjectText(taskTypeDefTxt);
	        		verifyAssertEquals(selectAddNewTaskType.trim(), getActualObjectTxt);
	        		
	        		//Duration 
	        		getAttributePh(tskDuration);
	        		verifyAssertEquals(PhtskDuration, getAttribtePh);
	        		
	        		// TaskId
	        		getAttributePh(tskTaskId);
	        		verifyAssertEquals(PhtskTaskId,  getAttribtePh);
	        		
	        		//	Employee Assigned
	        		getObjectText(tskEmployeeAssignedDefTxt);
	        		verifyAssertEquals(selectEmployeeAssigned, getActualObjectTxt);
	        		
	        		
	        		//	Comments
	        		WebElement ifram = driver.findElement(scEdtrFrame);
	        		driver.switchTo().frame(ifram);		
	        		getObjectText(scEdtrCommentsField);
	        		verifyAssertEquals("", getActualObjectTxt);
	        		switchBackFromFrame();
	        		thread();
	        		
	    			
	    		    //click cancel button 
	        		click(tskCancelBtn);
	        		thread();
	        		verifyAssert(tskTasksSearchBox);
	        	}
	        
			
            @Test(priority=22)
        	void verifySelectAllCheckBox() throws IOException, InterruptedException
        	{
        		click(tskSelectAllChkBox);
        		takeScreenshot();
        		
        		getTotalValuesIndd(tsktaskNameLstViewTtl);
        		for(int i=1; i<=totalDDValCount-1; i++)
        		{
        			boolean chkd = driver.findElement(By.xpath("//table[@id='tasks_table']//tr["+i+"]/td/div/span/input")).isEnabled();
        			Assert.assertEquals(true, chkd);	
        		}		
        		click(tskSelectAllChkBox);
        		thread();
        
        	}

                  // Verify task Name in relationshipTitle 
            
            @Test(priority=23)
        	void verifytaskNameInRelationshipTitle() throws InterruptedException
        	{
        		getTotalValuesIndd(tsktaskNameLstViewTtl);
        		Random random = new Random();
        		int rrow = random.nextInt(totalDDValCount-1)+1;
        		
        		WebElement tsktaskName = driver.findElement(By.xpath("//table[@id='tasks_table']/tbody/tr["+rrow+"]/td[2]"));
        		String tasknameforchkTtl = tsktaskName.getText();
        		tsktaskName.click();
        		
        		//	Verify Task Name In List View Relationship Title
        		getObjectText(tskNameRelationshipTitleNm);
        		verifyAssertEquals(tasknameforchkTtl, getActualObjectTxt.substring(4));
        		
        		driver.findElement(By.xpath("//table[@id='tasks_table']/tbody/tr["+rrow+"]/td[9]/a[1]")).click();
        		thread();
        		
        		//	Verify Task Name In Edit Screen Relationship Title
        		getObjectText(tskNameRelationshipTitleNm);
        		verifyAssertEquals(tasknameforchkTtl, getActualObjectTxt.substring(4));
        		thread();
        		
        		click(tskCancelBtn);
        		thread();
        	}
            
            // Mass Edit 
            
            @Test(priority=24)
	       void TaskMassEdit() throws InterruptedException, IOException, BiffException
	       {
             getTasksSheetFromExcel();
             thread();
             click(tskFirstrow);
             thread();
	         click(tskSecrow);
	       	 thread();
    		 click(tskOvrMassEditBtn);
		     thread();
		
		   //	Task Type 
		   click(tskEditTskTypeArrow);
		   getTotalValuesIndd(tskEditTskTypeCnt);
		   Random mEdttskType = new Random();
		   int rmEdttskType = mEdttskType.nextInt(totalDDValCount-2)+2;
		   mEdttaskType = driver.findElement(By.id("tasks_type_chzn_o_"+rmEdttskType)).getText().split(",")[0];
		   enterText(tskmEdtTskTypeSearchBox, mEdttaskType);
		   enterKeyInKyBord(tskmEdtTskTypeSearchBox);
		   thread();
		
		  //	Employee Assigned 
		   click(tskEditEmpAssignArrow);
		   thread();
		   getTotalValuesIndd(tskEditEmpAssignCnt);
		   if(totalDDValCount > 1)
			{
				Random remp = new Random();
				int rmEdtEmpAsigned = remp.nextInt(totalDDValCount-1)+1;
				 mEdtemployeeAssigned  = driver.findElement(By.id("tasks_resource_chzn_o_"+ rmEdtEmpAsigned)).getText().split(",")[0];
				 enterText(tskmEdtEmpAssignSearchBox, mEdtemployeeAssigned);
				 enterKeyInKyBord(tskmEdtEmpAssignSearchBox);
				 takeScreenshot();
				 thread();
				 
			}
	        
		  
		    click(tskEditSubmitBtn);
		    thread();
		   try
		   {
			  getObjectText(tskEmpAltPopup);
		      thread();
		      click(tskEmpAltOkBtn);
		      thread();
		     }
		   catch(WebDriverException e)
		   {
		    
		    getObjectText(msgNotificationBar);
		    takeScreenshot();
			verifyAssertEquals("2 Tasks have been updated with "+ mEdtemployeeAssigned.trim(), getActualObjectTxt);  
			Thread.sleep(100);
	       }
		   
	       }
              
              // Verify updated Edit section 
              @Test(priority=25)
          	void verifyTaskMassUpdatedTextInListView() throws InterruptedException
          	{
          		for(int i=1; i<3; i++)
          		{
          			for(int j=3; j<9; j++)
          			{
          				String getupdatdTxt = driver.findElement(By.xpath("//table[@id='tasks_table']/tbody/tr["+i+"]/td["+j+"]")).getText();
          				thread();
          			//driver.manage().timeouts().implicitlyWait(78 ,TimeUnit.SECONDS);
						
          				if(i == 1 && j == 4)
          				{
          					//	TaskType
          					verifyAssertEquals(mEdttaskType, getupdatdTxt);
          				}
          				else if(i == 1 && j == 6)
          				{
          					//	EmployeeAssigned 
          					verifyAssertEquals(mEdtemployeeAssigned.trim(), getupdatdTxt);
          				}
          			
          			}
          	    }
          		
          		      thread();
          	 }	
              
             
              // To view the task order 
           
            @Test(priority=26)  
             void TskTaskOrder() throws InterruptedException, IOException
            {
            	click(tskTaskOrderbtn);
            	takeScreenshot();
            	getObjectText(tskTaskOrderpopup);
            	
            	
            	// Drag and Drop options 
            	
            	 Actions act = new Actions(driver);
            	 Thread.sleep(1000);
            	 WebElement From = driver.findElement(By.xpath("//table[@id='tasks_list_table']/tbody/tr[5]/td[1]"));
            	 Thread.sleep(1000);
            	 WebElement To = driver.findElement(By.xpath("//table[@id='tasks_list_table']/tbody/tr[2]/td[1]"));
            	
            	 act.clickAndHold(From).build().perform();             
            	 act.moveToElement(To).build().perform();
            	 Thread.sleep(1000);
            	 act.release(To).build().perform();
                
                 thread();
                     
      		    // verify task order message
      		    getObjectText(tskodrMsgNotificationBar);
      		    takeScreenshot();
      		    verifyAssertEquals("Task order is done successfully" , getActualObjectTxt);	
      		    thread();
      	       
            	click(tskTaskOrderclosebtn);
            	thread();
            	
            }
            
            @Test(priority=27)
        	void EditTaskName() throws InterruptedException, BiffException, IOException
        	{
            	click(tasksInMainMenu);
        		
        		click(tskEditBtn);
       		    thread();
       		    
        		//Scroll down the page
        		webElement(tskClearBtn);
        		scrollInnerScrollBar(webelementFrame);
        		
        		// click clear button 
        		click( tskClearBtn);
        		thread();
        		getTasksSheetFromExcel();
        		Random rerow = new Random();
        		int rexRow = rerow.nextInt(tsk.getRows()-1)+1;
        		//System.out.println("Task Name +++");
        		
        				
        		//		Task Name
        		modifiedTaskName = tsk.getCell(0, rexRow).getContents();
        		enterText(tskTaskName, modifiedTaskName);
        		thread();
        		
        		//		Task Type
        		click(tskTaskTypeArrow);
        		getTotalValuesIndd(tskTaskTypeCnt);
        		if(totalDDValCount > 1)
        		{
        			Random ran = new Random();
        			int rtype = ran.nextInt(totalDDValCount-1)+1;
        			modifiedTsktype =driver.findElement(By.id("task_type_chzn_o_"+rtype)).getText();
        			enterText(tskTaskTypeSearchBox, modifiedTsktype);
        			enterKeyInKyBord(tskTaskTypeSearchBox);
        		}
        		
        		//		Duration
        		modifiedtskDuration= tsk.getCell(1, rexRow).getContents();
        		enterText(tskDuration, modifiedtskDuration);
        		thread();
        		
        		//		Task Id
        		int n=100;
        		Random rnd=new Random();
        		int rInt=rnd.nextInt(n-1)+1;
        		modifiedtskTaskId = tsk.getCell(2, rexRow).getContents()+rInt;
        		enterText(tskTaskId, modifiedtskTaskId);
        		thread();
        		
        		//		Employee Assigned
        		click(tskEmployeeAssignedArrow);
        		getTotalValuesIndd(tskEmployeeAssignedCnt);
        		if(totalDDValCount > 1)
        		{
        			Random remp = new Random();
        			int rempAsign = remp.nextInt(totalDDValCount-1)+1;
        			modifiedEmployeeAssigned= driver.findElement(By.id("principal_resource_chzn_o_"+rempAsign)).getText().split(",")[0];
        			enterText(tskEmployeeAssignedSearchBox, modifiedEmployeeAssigned);
        			enterKeyInKyBord(tskEmployeeAssignedSearchBox);
        		}
        		
        		//Alternate Employee
        		click(tskAltrEmployeeDDArrow);
        		getTotalValuesIndd(tskAltrEmployeeCnt);
        		if(totalDDValCount>=1)
        		{
        			Random rAemp=new Random();
        			int rAltrEmp=rAemp.nextInt(totalDDValCount-1)+1;
        			String rAlterEmp=driver.findElement(By.id("alternate_resource_chzn_o_"+rAltrEmp)).getText().split(",")[0];
        			enterText(tskAltrEmployeeSearchBox,rAlterEmp);
        			enterKeyInKyBord(tskAltrEmployeeSearchBox);
        		}
        		
        		//Comments
        		WebElement ifram = driver.findElement(scEdtrFrame);
        		driver.switchTo().frame(ifram);			
        		enterText(scEdtrCommentsField, tsk.getCell(3, rexRow).getContents());
        		switchBackFromFrame();
        		
        	    //Scroll down the page
        			webElement(tskSubmitBtn);
        			scrollInnerScrollBar(webelementFrame);
        			
        		//click submit button 
        			click(tskSubmitBtn);
        			thread();
        			takeScreenshot();
        			
        			try
        		    {
        				
        				
        				   File editFile = new File(xlFilesLocation);
        					if (editFile.exists())
        					            {
        						            getObjectText(msgNotificationBar);
        						            click(tskClearBtn);
        						            EditTaskName();
        						            thread();
        					            }
        				 }
        			catch(Exception e)
        			{
        				e.printStackTrace();
        				
        			}
        		  
        			thread();
        		
    		//		Verify task Name Updated Successfully messages
    		    getObjectText(msgNotificationBar);
    		    thread();
    		    verifyAssertEquals(modifiedTaskName+" Successfully Updated", getActualObjectTxt);	
    		    thread();
    	    }
        	
        	
            @Test(priority=28)
        	void verifyEditedtskNameInListView() throws InterruptedException
        	{
        		for(int i=1; i<3; i++)
        		{
        			WebElement getmodtskNameInListView = driver.findElement(By.xpath("//table[@id='tasks_table']/tbody/tr["+i+"]/td[2]"));
      
        			if(getmodtskNameInListView .getText().contains(modifiedTaskName))
        			{
        		        				
        				for(int j=2; j<9; j++)
        				{
        					String getmodDetails = driver.findElement(By.xpath("//table[@id='tasks_table']/tbody/tr["+i+"]/td["+j+"]")).getText();
        					
        					if(j==2)
        					{
								verifyAssertEquals(modifiedTaskName, getmodDetails);
        					}
        					else if(j==3)
        					{
								verifyAssertEquals(modifiedtskTaskId, getmodDetails);
        					}
        					else if(j==4)
        					{
								verifyAssertEquals(modifiedTsktype, getmodDetails);
        					}
        					else if(j==5)
        					{
								verifyAssertEquals(modifiedtskDuration, getmodDetails);
        					}
        					else if(j==6)
        					{
								verifyAssertEquals(modifiedEmployeeAssigned, getmodDetails);
        					}
        					
        					/*else if(j==8)
        					{
								verifyAssertEquals(modifiedComments.substring(100), getmodDetails);
        					}*/
							
        				}
        				break;
        			}			
        		}	
        		
        		
        	}
             
           //To view the pagination 
            @Test(priority=29)
        	void tskNameListViewPagination() throws IOException, InterruptedException
        	{
        		getTotalValuesIndd(tskNameListViewPagination);
        		
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
        			int randomPge = randomPagi.nextInt((totalDDValCount)-4)+4;
        			driver.findElement(By.xpath("//div[@id='tasks_table_wrapper']/div[2]/div[2]/div/ul/li["+randomPge+"]/a")).click();
        			thread();
        			takeScreenshot();
        			click(pagiStartArw);
        			thread();
        		}		
        	}
        	
        	@Test(priority=30)
        	void verifyShowEntriesDropDown() throws InterruptedException, IOException
        	{
        		try
        		{
        			getObjectText(tskNameListViewRecsInfo);
        			
        			if(Integer.parseInt(getActualObjectTxt.split("of ")[1].split(" ")[0].trim()) > 10)
        			{
        				selectTextFromDropdown(tskNameListViewLength , "25");
        				thread();
        				takeScreenshot();
        				
        				selectTextFromDropdown(tskNameListViewLength , "10");
        				thread();
        			}
        		}
        		catch(WebDriverException e)
        		{
        			System.out.println("Pagination is not available in TaskName List View. It contains 10 or less than 10 records");
        		}
        	}
        	
        	 @Test(priority=31)
           	void searchTaskNameInListView() throws IOException, InterruptedException
           	{
           		getTotalValuesIndd(tsktaskNameLstViewTtl);
           		
           		Random random = new Random();
           		int rrow = random.nextInt(totalDDValCount-1)+1;
           		
           		String tsknameSearch = driver.findElement(By.xpath("//table[@id='tasks_table']/tbody/tr["+rrow+"]/td[2]")).getText();
           		
           		//	Search Valid  Task Name
           		enterText(tsknameSearchBox,tsknameSearch);
           		getObjectText(tskLstViewSrchRslt);
           		Thread.sleep(1000);
           		verifyAssertEquals(tsknameSearch, getActualObjectTxt);
           		takeScreenshot();
           		clear(tsknameSearchBox);
           		enterKeyInKyBord(tsknameSearchBox);
           		Thread.sleep(1000);
           		
           		//	Search Invalid Task Name
           		enterText(tsknameSearchBox, "Inv Task Name");
           		getObjectText(noRecordsFoundMsg);
           		verifyAssertEquals(noMatchingRecordsFound, getActualObjectTxt);
           		takeScreenshot();
           		clear(tsknameSearchBox);
           		enterKeyInKyBord(tsknameSearchBox);
           		thread();

   	
           	}
        	 
        	 @Test(priority=32)
        		void verifyViewPage()throws IOException, InterruptedException
        		{
        			click(tskViewBtn);
        			takeScreenshot();
        	     	getObjectText(tskViewPopup);
        	     	thread();
        	     	click(tskViewCloseBtn);
        	     	thread();
        		}
        		
        	/*@Test(priority=33)
        	void logout() throws InterruptedException
        	{
        		ll.logoutSession();
        		thread();
        		closeWindow();
        	}*/


     }





			
	
	

