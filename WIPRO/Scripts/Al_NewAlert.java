package Scripts;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import UIWrappers.Page;
import jxl.read.biff.BiffException;


import static ObjectRepository.Alerts.*;
import static UIWrappers.UIObjects.*;
import static ObjectRepository.Threats.*;
import static ObjectRepository.Home.*;
import static ObjectRepository.Incidents.alertBtnInTopMenu;
import static ObjectRepository.Locations.*;
import static ObjectRepository.ManageDisaster.*;
import static ObjectRepository.Tasks.*;
import static ObjectRepository.Teams.*;
import static Config.TakScreenshot.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


/************************************************************************************************************
 * 
 * @author Sellamuthu
 * 
 * Test 1: Verify Alerts List View Title Text
 * Test 2: Add New Alert Details
 * Test 3: Verify Alert Added Successfully message
 * Test 4: Verify Added Alert details in list view
 * Test 5: Verify Alert Message Mouse Hover
 * Test 6: Compare Location & Team details with View Page details
 * Test 7: Search with Valid/Invalid alert names
 * Test 8: Check the Pagination
 * Test 9: Verify Show Entries
 * Test 10: Delete the Alert Name and verify deleted successfully message
 * Test 11: Delete multiple Alert names and verify the message
 * Test 12: Verify alert help pop up
 * 
*************************************************************************************************************/

public class Al_NewAlert extends Page {
	
	LoginLogout ll = new LoginLogout();
	Loc_AddnDeleteMultiple loc_AddnDeleteMultiple = new Loc_AddnDeleteMultiple();
	
	private String rAlertName, rAlertMessage, alertdLocation, alertdTeam,alertDate,alertLastChangedDateAndTime,alertStatusAfterUpdate,alertSeverityLevel,alertResolvedMessage,alertClosedMessage,resolvedModeOfCoomunicationValue,ClosedModeOfCommunicationValue,profileName,selectedBusinessFunctionName,alertAdditionalMessage,alertStatusBeforeUpdate;
	private String additionalMessage,selectedCommunicationMode;
	private String alertType = "Drill";
	private String selectedTask = "No Records Found";
	private String alertTypeViewScreen = "Drill Alert";
	private String locName, locStatus, locType, locManagerName, locManagerContact, locAddress, locCity, locState, locCountry;
	private String parentWindow,selectedLocationName, selectedTeamName;
	private String teamName, teamLocation, teamTeamLeader, teamType, teamEmail, teamMobile, teamComments;
	private List<WebElement> employeeList ;
	private String[] employeeListArray = new String[100];
	private int employeeListArraySize;
		
   /*@Test(priority=0)
	void login() throws BiffException, IOException, InterruptedException
	{
		ll.loginToSIB();
		waitForPageLoad();
		
	}*/
	
	@Test(priority=1)
	void addNewAlert() throws InterruptedException, BiffException, IOException
	{
		
		getAlertsSheetFromExcel();
		webElement(alertBtnInTopMenu);
		scrollInnerScrollBar(webelementFrame);
		click(alertsBtnInTopMnu);
		click(alertsOptnInTopDDLst);
		click(alOvrAddBtn);
		click(alDrillAlertChkBox);	//	Drill Alert
		click(alModeOfCommEmailndSMS);	//	Email & SMS communication mode
		selectedCommunicationMode = driver.findElement(By.xpath("//label[@for='comm_mode_1']")).getText();
		
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
		Thread.sleep(1000);
		
		try
		{
		verifyObjDisplay(By.id("cometchat_hide"));
		if(chkObjDisplay=true)
		{
		driver.findElement(By.id("cometchat_hide")).click();
		}
		}
		catch(Exception e)
		{
			//e.printStackTrace();
		}
		
		// closing chat window
		
		profileName = driver.findElement(By.xpath("//span[@class='hidden-phone hidden-tablet']")).getText();
		//getting the profile name from the header for validation in the view screen 
		List<WebElement> locationsList = driver.findElements(alertsLocations);
		int locationsListSize = locationsList.size();
		Random random = new Random();
		int randomLocationValue = random.nextInt(locationsListSize);
		if(randomLocationValue==0){
			randomLocationValue = randomLocationValue+1;
		}
		driver.findElement(By.xpath("//table[@id='location_table']//tbody//tr["+randomLocationValue+"]//input")).click();
		selectedLocationName = driver.findElement(By.xpath("//table[@id='location_table']//tbody//tr["+randomLocationValue+"]//td[2]")).getText();
		Thread.sleep(1000);
		
		try{
			WebElement selectTeamsLabel = driver.findElement(By.xpath("//form[@id='alert_form']//label[@class='control-label']//h4[contains(text(),'Select Teams')]"));
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", selectTeamsLabel);
			if(driver.findElement(teamsNoRecordsFound).isDisplayed()){
			selectedTeamName = "No Records Found";	
			WebElement selectBusinessFunctionsLabel = driver.findElement(By.xpath("//form[@id='alert_form']//label[@class='control-label']//h4[contains(text(),'Select Business Functions')]"));
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", selectBusinessFunctionsLabel);
			
		}}catch(Exception E){
			List<WebElement> alertsTeamsList = driver.findElements(alertsTeams);
			int alertsTeamsListSize = alertsTeamsList.size();
			Random random1 = new Random();
			int randomTeamValue = random1.nextInt(alertsTeamsListSize);
			if(randomTeamValue==0){
				randomTeamValue = randomTeamValue+1;
			}
			driver.findElement(By.xpath("//table[@id='team_table']//tbody//tr["+randomTeamValue+"]//input")).click();
			selectedTeamName = driver.findElement(By.xpath("//table[@id='team_table']//tbody//tr["+randomTeamValue+"]//td[2]")).getText();
		}
		
		try{
			WebElement selectBusinessFunctionsLabel = driver.findElement(By.xpath("//form[@id='alert_form']//label[@class='control-label']//h4[contains(text(),'Select Business Functions')]"));
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", selectBusinessFunctionsLabel);
			if(driver.findElement(businessFunctionsNoRecordsFound).isDisplayed()){
				selectedBusinessFunctionName = "No Records Found";
				WebElement alertListLabel = driver.findElement(By.xpath("//form[@id='alert_form']//label[@class='control-label']//h4[contains(text(),'Alert List')]"));
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", alertListLabel);	
			}
		}catch(Exception E){
			List<WebElement> businessFunctionsList = driver.findElements(alertsBusinessFunctions);
			int businessFunctionsListSize = businessFunctionsList.size();
			Random random2 = new Random();
			int randomBusinessFunctionValue = random2.nextInt(businessFunctionsListSize);
			if(randomBusinessFunctionValue==0){
				randomBusinessFunctionValue = randomBusinessFunctionValue+1;
			}
			driver.findElement(By.xpath("//table[@id='business_function_table']//tbody//tr["+randomBusinessFunctionValue+"]//input")).click();
			selectedBusinessFunctionName = driver.findElement(By.xpath("//table[@id='business_function_table']//tbody//tr["+randomBusinessFunctionValue+"]//td[2]")).getText();
		}
		
		try{
			if(driver.findElement(selectTasksLabel).isDisplayed()){
				List<WebElement> tasksList = driver.findElements(alertsTasksList);
				int taskSize = tasksList.size();
				Random random4 = new Random();
				int randomTaskValue = random4.nextInt(taskSize);
				if(randomTaskValue==0){
					randomTaskValue = randomTaskValue+1;
				}
				driver.findElement(By.xpath("//table[@id='task_table']//tbody//tr["+randomTaskValue+"]//td[1]")).click();
				selectedTask = driver.findElement(By.xpath("//table[@id='task_table']//tbody//tr["+randomTaskValue+"]//td[2]")).getText();
				thread();
			}			
		}catch(Exception E){
			
			
			System.out.println("No tasks found for the location");
		}
		
		try{
			WebElement alertListLabel = driver.findElement(By.xpath("//form[@id='alert_form']//label[@class='control-label']//h4[contains(text(),'Alert List')]"));
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", alertListLabel);	
			if(driver.findElement(employeesNoRecordsFound).isDisplayed()){
				click(addEmployeeBtn);
				thread();
				driver.findElement(By.xpath("//table[@id='additional_employee_location_table']//input[@title='Select All']")).click();
				thread();
				
				WebDriverWait wait = new WebDriverWait(driver, 10);
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//table[@id='additional_employee_table']//tbody//tr[1]//td[1]")));
				
				webElement(By.xpath("//table[@id='additional_employee_table']//tbody//tr[1]//td[1]"));        	
				scrollInnerScrollBar(webelementFrame);
				thread();
				
				WebElement selectEmployeesLabel = driver.findElement(By.xpath("//label[@class='control-label']//h4[contains(text(),'Select Employees')]"));
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", selectEmployeesLabel);
				List<WebElement> additionalEmployeeList = driver.findElements(alertsEmployeesAdditionalEmployeeTable);
				int additionalEmployeeTableSize = additionalEmployeeList.size();
				Random random3 = new Random();
				int randomEmpValue = random3.nextInt(additionalEmployeeTableSize);
				if(randomEmpValue==0){
					randomEmpValue= randomEmpValue+1;
				}
				
		/*		WebDriverWait wait = new WebDriverWait(driver, 5);
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//table[@id='additional_employee_table']//tbody//tr["+randomEmpValue+"]//td[1]")));
				*/
				webElement(By.xpath("//table[@id='additional_employee_table']//tbody//tr["+randomEmpValue+"]//td[1]"));        	
				scrollInnerScrollBar(webelementFrame);
				thread();   
				
				driver.findElement(By.xpath("//table[@id='additional_employee_table']//tbody//tr["+randomEmpValue+"]//td[1]")).click();
				thread();
				click(addBtnAddEmployeeWindow);
				thread();
				List<WebElement> employeeList = driver.findElements(By.xpath("//table[@id='employee_table']//tbody//tr//td[1]"));
				int employeeListSize = employeeList.size();
				for(int i=0;i<employeeListSize;i++){
					employeeListArray[i] = employeeList.get(i).getText();
				}
				click(alSendBtn);
				Thread.sleep(1000);
				click(alconfirmationpopupokbtn);
				Thread.sleep(82000);
	
				
			}
		}catch(Exception E){
			WebElement alertListLabel = driver.findElement(By.xpath("//form[@id='alert_form']//label[@class='control-label']//h4[contains(text(),'Select Business Functions')]"));
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", alertListLabel);	
			List<WebElement> employeeList = driver.findElements(By.xpath("//table[@id='employee_table']//tbody//tr//td[1]"));
			int employeeListSize = employeeList.size();
			for(int i=0;i<employeeListSize;i++){
				employeeListArray[i] = employeeList.get(i).getText();
			}
			WebElement alertSendButton = driver.findElement(By.name("alert_submit"));
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", alertSendButton);	
			webElement(alSendBtn);
			scrollInnerScrollBar(webelementFrame);
			click(alSendBtn);
			Thread.sleep(82000);
		}
		click(alconfirmationpopupokbtn);
		Thread.sleep(82000);
	}
	
	@Test(priority=3)
	void verifySuccessMessageForAddedAlert() throws InterruptedException, IOException{
		waitForElement(msgNotificationBar); 
		getObjectText(msgNotificationBar);
		takeScreenshot();
		verifyAssertEquals("Alert has been sent successfully.", getActualObjectTxt);
		thread();
		alertSeverityLevel=driver.findElement(By.xpath("//tbody[@role='alert']//tr//td[contains(text(),'"+rAlertName+"')]/../td[3]")).getText();
		// getting alert severity level
		alertStatusBeforeUpdate = driver.findElement(By.xpath("//tbody[@role='alert']//tr//td[contains(text(),'"+rAlertName+"')]/../td[6]")).getText();
		// getting alert status before updating 
		alertDate = driver.findElement(By.xpath("//span[contains(@title,'Created on:')]")).getText();
		Thread.sleep(1000);
		// getting the alert date 
	}
	
	@Test(priority=4)
	void verifyAddedAlertPresentInListView() throws InterruptedException{
		List<WebElement> alertsList = driver.findElements(By.xpath("//table[@id='alert_table']//tr"));
		String[] alertNameInListViewArray = new String[100];
		String flag="";
		int alertsListSize = alertsList.size();
		for(int i=0;i<=alertsListSize;i++){
			alertNameInListViewArray[i] = driver.findElement(alertNameInListView).getText();
			if(alertNameInListViewArray[i].equals(rAlertName)){
				flag = "true";
				break;
			}else
			{
				flag="false";
			}
			
		}
		
		verifyAssertEquals(flag,"true");
		Thread.sleep(1000);
		
	}
	
	@Test(priority=5)
	void verifyHideClosedAlertsButton() throws InterruptedException{
		click(alHideClosedAlertsbtn);
		String flag="";
		String[] alertsStatusInListViewArray = new String[100]; 
		List<WebElement> alertsList = driver.findElements(By.xpath("//table[@id='alert_table']//tr"));
		int alertsListSize = alertsList.size();
		for(int i=0;i<=alertsListSize;i++){
			alertsStatusInListViewArray[i] = driver.findElement(alertsStatusListView).getText();
			if(alertsStatusInListViewArray[i].equals("Active")){
				flag="true";
			}
			else
			{
				flag="false";
				break;
			}
			
		}
		verifyAssertEquals(flag,"true");
		Thread.sleep(1000);
	}
	
	@Test(priority=6)
	void verifyAddCommentFunctionality() throws BiffException, IOException, InterruptedException{
		getAlertsSheetFromExcel();	
		driver.findElement(By.xpath("//tr[@data-for='"+rAlertName+"']//td//a[@class='closeAlertButton label btn-primary dropdown-toggle']")).click();
		driver.findElement(By.xpath("//tr[@data-for='"+rAlertName+"']//td//a[@class='closeAlertButton label btn-primary dropdown-toggle']/../ul//li//a[contains(text(),'Add Comment')]")).click();
		thread();
		List<WebElement> addMessageScreenTextList = driver.findElements(By.xpath("//div[@class='control-group']//b/../div"));
		int addMessageScreenTextListSize = addMessageScreenTextList.size();
		for(int i=0;i<addMessageScreenTextListSize;i++){
			String addScreenMessages = addMessageScreenTextList.get(i).getText();
			if(i==0){
				verifyAssertEquals(rAlertName,addScreenMessages);
			}
			if(i==1){
				verifyAssertEquals(profileName,addScreenMessages);
			}
			if(i==2){
				verifyAssertEquals(alertDate,addScreenMessages);
			}
			if(i==3){
				verifyAssertEquals(selectedCommunicationMode,addScreenMessages);
			}
			if(i==4){
				verifyAssertEquals(alertType,addScreenMessages);
			}
			if(i==5){
				verifyAssertEquals(alertSeverityLevel,addScreenMessages);
			}
			if(i==6){
				verifyAssertEquals(rAlertMessage,addScreenMessages);
			}
			if(i==8){
				verifyAssertEquals(alertStatusBeforeUpdate,addScreenMessages);
			}
		}
		List<WebElement> communicationOptionList=driver.findElements(By.xpath("//label[contains(@for,'comm_mode_')]"));
		int listSize = communicationOptionList.size();
		Random communicationOption = new Random();
		int communicationOptionValue = communicationOption.nextInt(listSize);
		if(communicationOptionValue==0)
		{
			communicationOptionValue=communicationOptionValue+1;
		}
		driver.findElement(By.xpath("//label[@for='comm_mode_"+communicationOptionValue+"']")).click();
		webElement(scEdtrFrame);
		switchToWEFrame(webelementFrame);
		alertAdditionalMessage = alrt.getCell(3, 1).getContents();
		enterText(scEdtrCommentsField, alertAdditionalMessage);
		switchBackFromFrame();
		
		webElement(alertsAddCommentButton);
		scrollInnerScrollBar(webelementFrame);
		
		click(alertsAddCommentButton);
		Thread.sleep(1000);
		waitForElement(msgNotificationBar); 
		getObjectText(msgNotificationBar);
		takeScreenshot();
		verifyAssertEquals("Comment successfully added to Alert!", getActualObjectTxt);
		thread();
	}
	
	@Test(priority=7)
	void changeAlertStatusToResolved() throws BiffException, IOException, InterruptedException{
		getAlertsSheetFromExcel();	
		driver.findElement(By.xpath("//tr[@data-for='"+rAlertName+"']//td//a[@class='closeAlertButton label btn-primary dropdown-toggle']")).click();
		//clicking on the update button for the alert that was raised
		driver.findElement(By.xpath("//tr[@data-for='"+rAlertName+"']//td//a[@class='closeAlertButton label btn-primary dropdown-toggle']/../ul//li//a[contains(text(),'Update Status')]")).click();
		// clicking on the update status button
		thread();
		click(alertResolvedRadiobtn);
		webElement(scEdtrFrame);
		switchToWEFrame(webelementFrame);
		alertResolvedMessage=alrt.getCell(2, 1).getContents();
		enterText(scEdtrCommentsField, alertResolvedMessage);
		switchBackFromFrame();
		//select a random value for mode of communication option 
		List<WebElement> communicationOptionList=driver.findElements(By.xpath("//label[contains(@for,'comm_mode_')]"));
		int listSize = communicationOptionList.size();
		Random communicationOption = new Random();
		int communicationOptionValue = communicationOption.nextInt(listSize);
		if(communicationOptionValue==0)
		{
			communicationOptionValue=communicationOptionValue+1;
		}
		driver.findElement(By.xpath("//label[@for='comm_mode_"+communicationOptionValue+"']")).click();
		resolvedModeOfCoomunicationValue=driver.findElement(By.xpath("//label[@for='comm_mode_"+communicationOptionValue+"']")).getText();
		WebElement updateStatusButton = driver.findElement(By.xpath("//button[@type='submit']"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", updateStatusButton);	
		click(alertUpdateStatusbtn);
		waitForElement(msgNotificationBar); 
		getObjectText(msgNotificationBar);
		takeScreenshot();
		verifyAssertEquals("Alert status has been updated successfully.", getActualObjectTxt);
		Thread.sleep(1000);
	}
	
	@Test(priority=8)
	void changeAlertStatusToClosed() throws BiffException, IOException, InterruptedException{
		getAlertsSheetFromExcel();	
		driver.findElement(By.xpath("//tr[@data-for='"+rAlertName+"']//td//a[@class='closeAlertButton label btn-primary dropdown-toggle']")).click();
		//clicking on the update button for the alert that was raised
		driver.findElement(By.xpath("//tr[@data-for='"+rAlertName+"']//td//a[@class='closeAlertButton label btn-primary dropdown-toggle']/../ul//li//a[contains(text(),'Update Status')]")).click();
		thread();
		click(alertClosedRadiobtn);
		webElement(scEdtrFrame);
		switchToWEFrame(webelementFrame);
		alertClosedMessage=alrt.getCell(3, 1).getContents();
		enterText(scEdtrCommentsField, alertClosedMessage);
		switchBackFromFrame();
		//select a random value for mode of communication option 
		List<WebElement> communicationOptionList=driver.findElements(By.xpath("//label[contains(@for,'comm_mode_')]"));
		int listSize = communicationOptionList.size();
		Random communicationOption = new Random();
		int communicationOptionValue = communicationOption.nextInt(listSize);
		if(communicationOptionValue==0)
		{
			communicationOptionValue=communicationOptionValue+1;
		}
		driver.findElement(By.xpath("//label[@for='comm_mode_"+communicationOptionValue+"']")).click();
		ClosedModeOfCommunicationValue = driver.findElement(By.xpath("//label[@for='comm_mode_"+communicationOptionValue+"']")).getText();
		scrollToBottom();
		WebElement updateStatusButton = driver.findElement(By.xpath("//button[@type='submit']"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", updateStatusButton);	
		click(alertUpdateStatusbtn);
		waitForElement(msgNotificationBar); 
		getObjectText(msgNotificationBar);
		takeScreenshot();
		verifyAssertEquals("Alert status has been updated successfully.", getActualObjectTxt);
		
		
		
		alertStatusAfterUpdate=driver.findElement(By.xpath("//tbody[@role='alert']//tr//td[contains(text(),'"+rAlertName+"')]/../td[6]")).getText();
	}
	
	@Test(priority=9)
	void verifyAlertViewScreen() throws InterruptedException{
		String tab1 = driver.getWindowHandle();
		driver.findElement(By.xpath("//td[contains(text(),'"+rAlertName+"')]/../td//a[@target='_blank']")).click();
		//clicking on the view button for the raised alert
		ArrayList<String> tab2 = new ArrayList<String>(driver.getWindowHandles());
		tab2.remove(tab1);
		driver.switchTo().window(tab2.get(0));
		thread();
		
		List<WebElement> alertViewScreenTextsList = driver.findElements(By.xpath("//div[@class='control-group']//b/../div"));
		int alertViewScreenTextsListSize=alertViewScreenTextsList.size();
		for(int i=0;i<alertViewScreenTextsListSize;i++){
			String alertViewScreenMessages = alertViewScreenTextsList.get(i).getText();
			if(i==0){
				verifyAssertEquals(rAlertName,alertViewScreenMessages);
			}
			if(i==1){
				verifyAssertEquals(profileName,alertViewScreenMessages);
			}
			if(i==2){
				verifyAssertEquals(alertDate,alertViewScreenMessages);
			}
			if(i==3){
				verifyAssertEquals(selectedCommunicationMode,alertViewScreenMessages);
				
			}
			if(i==4){
				verifyAssertEquals(alertTypeViewScreen,alertViewScreenMessages);
				
			}
			if(i==5){
				verifyAssertEquals(alertSeverityLevel,alertViewScreenMessages);
				
			}
			if(i==6){
				verifyAssertEquals(rAlertMessage,alertViewScreenMessages);				
			}
			if(i==7){
				click(additionalMessageLinkViewScreen);
				thread();
				additionalMessage = driver.findElement(additionalMessageText).getText();
				verifyAssertEquals(alertAdditionalMessage.trim(),additionalMessage);
				click(closeBtnAdditionalMessageScreen);
			}
			if(i==8){
				verifyAssertEquals(alertStatusAfterUpdate,alertViewScreenMessages);
			}
			if(i==9){
				continue;
				
			}
			if(i==10){
				verifyAssertEquals(profileName,alertViewScreenMessages);
				
			}
			if(i==11){
				verifyAssertEquals(alertResolvedMessage.trim(),alertViewScreenMessages);
				
			}
			if(i==12){
				continue;
			}
			if(i==13){
				verifyAssertEquals(resolvedModeOfCoomunicationValue,alertViewScreenMessages);
				
			}
			if(i==14){
				continue;
				
			}
			if(i==15){
				verifyAssertEquals(profileName,alertViewScreenMessages);
				
			}
			if(i==16){
				verifyAssertEquals(alertClosedMessage.trim(),alertViewScreenMessages);
				
			}
			if(i==17){
				continue;
				
			}
			if(i==18){
				verifyAssertEquals(ClosedModeOfCommunicationValue,alertViewScreenMessages);
				
			}
			
		}
		String locationNameInViewScreen = driver.findElement(By.xpath("//table[@id='location_table']//tbody//tr//td[1]")).getText();
		verifyAssertEquals(selectedLocationName,locationNameInViewScreen);
		// verify the alert location in view screen
		String teamNameInViewScreen = driver.findElement(By.xpath("//table[@id='resourcegroup_table']//tbody//tr//td[1]")).getText();
		verifyAssertEquals(selectedTeamName,teamNameInViewScreen);
		String businessFunctionNameInViewScreen = driver.findElement(By.xpath("//table[@id='business_function_table']//tbody//tr//td[1]")).getText();
		verifyAssertEquals(selectedBusinessFunctionName,businessFunctionNameInViewScreen);
		String taskNameInViewScreen = driver.findElement(By.xpath("//table[@id='task_table']//tbody//tr//td[1]")).getText();
		verifyAssertEquals(selectedTask,taskNameInViewScreen);
		List<WebElement> employeeListViewScreen = driver.findElements(By.xpath("//table[@id='employee_table']//tbody//tr//td[1]"));
		int employeeListViewScreenSize = employeeListViewScreen.size();
		System.out.println("Employee view screen size"+employeeListViewScreenSize);
		Thread.sleep(1000);
		String[] employeeListViewScreenArray = new String[100];
		String flag="";
		for(int i=0;i<employeeListViewScreenSize;i++){
			employeeListViewScreenArray[i] = employeeListViewScreen.get(i).getText();
		}
		for(int i=0;i<employeeListViewScreenSize;i++){
			for(int j=0;j<employeeListViewScreenSize;j++){
				if(employeeListArray[i].equals(employeeListViewScreenArray[j])){
					flag="true";
					Thread.sleep(1000);
					break;
				}else
				{
					flag="false";
				}
			}
		}
		try
		{
		verifyAssertEquals(flag,"true");
		}
		catch(Error e)
		{
			e.printStackTrace();
		}
		driver.close();
		driver.switchTo().window(tab1);
	}
	
	
	@Test(priority=10)
	void verifyDeleteAlertFunctionality() throws IOException, InterruptedException{
		
		driver.findElement(By.xpath("//td[contains(text(),'"+rAlertName+"')]/../td//a[contains(@onclick,'remove_row')]")).click();
		thread();
		// deleting the alert that was raised
		click(alconfirmationpopupokbtn);
		waitForElement(msgNotificationBar); 
		getObjectText(msgNotificationBar);
		takeScreenshot();
		verifyAssertEquals(rAlertName+" Is Deleted", getActualObjectTxt);
		thread();
	}
	
	@Test(priority=11)
	void verifyAddEmployeeBtnAddAlertsPage() throws BiffException, IOException, InterruptedException, AWTException{
		
        getAlertsSheetFromExcel();
		click(alOvrAddBtn);
        click(alDrillAlertChkBox);	
		click(alModeOfCommEmailndSMS);	
		selectedCommunicationMode = driver.findElement(By.xpath("//label[@for='comm_mode_1']")).getText();
		Random randomAlert = new Random();
		int ralert = randomAlert.nextInt(alrt.getRows()-1)+1;
		rAlertName = alrt.getCell(0, ralert).getContents();
		enterText(alAlertTitle, rAlertName);
		webElement(scEdtrFrame);
		switchToWEFrame(webelementFrame);
		rAlertMessage = alrt.getCell(1, ralert).getContents();
		enterText(scEdtrCommentsField, rAlertMessage);
		switchBackFromFrame();
		WebElement alertListLabel = driver.findElement(By.xpath("//form[@id='alert_form']//label[@class='control-label']//h4[contains(text(),'Alert List')]"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", alertListLabel);	
		click(addEmployeeBtn);
		thread();
		waitForPageLoad();
		driver.findElement(By.xpath("//table[@id='additional_employee_location_table']//input[@title='Select All']")).click();
		thread();
		waitForPageLoad();
		
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//table[@id='additional_employee_table']//tbody//tr[1]//td[1]")));
		thread();
		
		webElement(By.xpath("//table[@id='additional_employee_table']//tbody//tr[1]//td[1]"));        	
		scrollInnerScrollBar(webelementFrame);
		thread();
		
		WebElement selectEmployeesLabel = driver.findElement(By.xpath("//label[@class='control-label']//h4[contains(text(),'Select Employees')]"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", selectEmployeesLabel);
		waitForPageLoad();
		List<WebElement> additionalEmployeeList = driver.findElements(alertsEmployeesAdditionalEmployeeTable);
		int additionalEmployeeTableSize = additionalEmployeeList.size();
		Random random3 = new Random();
		int randomEmpValue = random3.nextInt(additionalEmployeeTableSize);
		if(randomEmpValue==0){
			randomEmpValue= randomEmpValue+1;
		}
		thread();
/*		waitForPageLoad();
		
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//table[@id='additional_employee_table']//tbody//tr["+randomEmpValue+"]//td[1]")));
		*/
		
		webElement(By.xpath("//table[@id='additional_employee_table']//tbody//tr["+randomEmpValue+"]//td[1]"));        	
		scrollInnerScrollBar(webelementFrame);
		thread();  
		
		driver.findElement(By.xpath("//table[@id='additional_employee_table']//tbody//tr["+randomEmpValue+"]//td[1]")).click();
		thread();
		String empFirstNameBeforeMapping = driver.findElement(By.xpath("//table[@id='additional_employee_table']//tbody//tr["+randomEmpValue+"]//td[2]")).getText();
		String empSecondNameBeforeMapping = driver.findElement(By.xpath("//table[@id='additional_employee_table']//tbody//tr["+randomEmpValue+"]//td[3]")).getText();
		String empTitleBeforeMapping = driver.findElement(By.xpath("//table[@id='additional_employee_table']//tbody//tr["+randomEmpValue+"]//td[4]")).getText();
		String empTypeBeforMapping = driver.findElement(By.xpath("//table[@id='additional_employee_table']//tbody//tr["+randomEmpValue+"]//td[5]")).getText();
		String empMobileNumberBeforeMapping = driver.findElement(By.xpath("//table[@id='additional_employee_table']//tbody//tr["+randomEmpValue+"]//td[6]")).getText();
		String empEmailBeforeMapping = driver.findElement(By.xpath("//table[@id='additional_employee_table']//tbody//tr["+randomEmpValue+"]//td[7]")).getText();
		String empLocBeforeMapping = driver.findElement(By.xpath("//table[@id='additional_employee_table']//tbody//tr["+randomEmpValue+"]//td[8]")).getText();
		String empStatusBeforeMapping = driver.findElement(By.xpath("//table[@id='additional_employee_table']//tbody//tr["+randomEmpValue+"]//td[9]")).getText();
		
		thread();
		click(addBtnAddEmployeeWindow);
		thread();
		waitForElement(msgNotificationBar); 
		getObjectText(msgNotificationBar);
		takeScreenshot();
		verifyAssertEquals(empFirstNameBeforeMapping+" "+empSecondNameBeforeMapping+" "+"is added to the list", getActualObjectTxt);
		
		String empFirstNameAfterMapping = driver.findElement(By.xpath("//table[@id='employee_table']//tbody//tr//td[1]")).getText();
		String empSecondNameAfterMapping = driver.findElement(By.xpath("//table[@id='employee_table']//tbody//tr//td[2]")).getText();
		String empTitleAfterMapping = driver.findElement(By.xpath("//table[@id='employee_table']//tbody//tr//td[3]")).getText();
		String empTypeAfterMapping = driver.findElement(By.xpath("//table[@id='employee_table']//tbody//tr//td[4]")).getText();
		String empMobileNumberAfterMapping = driver.findElement(By.xpath("//table[@id='employee_table']//tbody//tr//td[5]")).getText();
		String empEmailAfterMapping = driver.findElement(By.xpath("//table[@id='employee_table']//tbody//tr//td[6]")).getText();
		String empLocAfterMapping = driver.findElement(By.xpath("//table[@id='employee_table']//tbody//tr//td[7]")).getText();
		String empStatusAfterMapping = driver.findElement(By.xpath("//table[@id='employee_table']//tbody//tr//td[8]")).getText();
		
		verifyAssertEquals(empFirstNameBeforeMapping,empFirstNameAfterMapping);
		verifyAssertEquals(empSecondNameBeforeMapping,empSecondNameAfterMapping);
		verifyAssertEquals(empTitleBeforeMapping,empTitleAfterMapping);
		verifyAssertEquals(empTypeBeforMapping,empTypeAfterMapping);
		verifyAssertEquals(empMobileNumberBeforeMapping,empMobileNumberAfterMapping);
		//verifyAssertEquals(empEmailBeforeMapping,empEmailAfterMapping);
		verifyAssertEquals(empLocBeforeMapping,empLocAfterMapping);
		verifyAssertEquals(empStatusBeforeMapping,empStatusAfterMapping);
		webElement(alSendBtn);
		scrollInnerScrollBar(webelementFrame);
		click(alSendBtn);
		click(alconfirmationpopupokbtn);
		Thread.sleep(1000);
		getObjectText(msgNotificationBar);
		takeScreenshot();
		verifyAssertEquals("Alert has been sent successfully.", getActualObjectTxt);
		Thread.sleep(1000);
		
	}
	
	@Test(priority=12)
	void verifyRemoveAllFunctionality() throws InterruptedException, IOException{
		String flag="";
		click(alOvrAddBtn);
		thread();
		WebElement alertListLabel = driver.findElement(By.xpath("//form[@id='alert_form']//label[@class='control-label']//h4[contains(text(),'Alert List')]"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", alertListLabel);	
		click(addEmployeeBtn);
		thread();
		driver.findElement(By.xpath("//table[@id='additional_employee_location_table']//input[@title='Select All']")).click();
		thread();
		
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//table[@id='additional_employee_table']//tbody//tr[1]//td[1]")));
		
		webElement(By.xpath("//table[@id='additional_employee_table']//tbody//tr[1]//td[1]"));        	
		scrollInnerScrollBar(webelementFrame);
		thread();
		
		WebElement selectEmployeesLabel = driver.findElement(By.xpath("//label[@class='control-label']//h4[contains(text(),'Select Employees')]"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", selectEmployeesLabel);
		List<WebElement> additionalEmployeeList = driver.findElements(alertsEmployeesAdditionalEmployeeTable);
		int additionalEmployeeTableSize = additionalEmployeeList.size();
		Random random3 = new Random();
		int randomEmpValue = random3.nextInt(additionalEmployeeTableSize);
		if(randomEmpValue==0){
			randomEmpValue= randomEmpValue+1;
		}
		
		thread();
/*		waitForPageLoad();
		
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//table[@id='additional_employee_table']//tbody//tr["+randomEmpValue+"]//td[1]")));
		 */
		webElement(By.xpath("//table[@id='additional_employee_table']//tbody//tr["+randomEmpValue+"]//td[1]"));        	
		scrollInnerScrollBar(webelementFrame);
		thread();
		
		driver.findElement(By.xpath("//table[@id='additional_employee_table']//tbody//tr["+randomEmpValue+"]//td[1]")).click();
		thread();
		click(addBtnAddEmployeeWindow);
		thread();
		driver.findElement(alertsRemoveAllButton).click();
		thread();
		driver.findElement(By.xpath("//button[@data-bb-handler='confirm']")).click();
		waitForElement(msgNotificationBar); 
		getObjectText(msgNotificationBar);
		takeScreenshot();
		verifyAssertEquals("All employees are removed from the list", getActualObjectTxt);
		try{
			if(driver.findElement(By.xpath("//table[@id='employee_table']//tbody//tr//td[1]")).getText().equals("No Records Found")){
				flag="true";
			}
		}catch(Exception E){
			flag="false";
		}
		
		verifyAssertEquals(flag,"true");
	}
	
	
	@Test(priority=13)
	void verifyRemoveEmployeeButton() throws BiffException, IOException, InterruptedException
	{
		WebElement alertListLabel = driver.findElement(By.xpath("//form[@id='alert_form']//label[@class='control-label']//h4[contains(text(),'Alert List')]"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", alertListLabel);	
		click(addEmployeeBtn);
		thread();
		driver.findElement(By.xpath("//table[@id='additional_employee_location_table']//input[@title='Select All']")).click();
		thread();
		
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//table[@id='additional_employee_table']//tbody//tr[1]//td[1]")));
		
		webElement(By.xpath("//table[@id='additional_employee_table']//tbody//tr[1]//td[1]"));        	
		scrollInnerScrollBar(webelementFrame);
		thread();
		
		WebElement selectEmployeesLabel = driver.findElement(By.xpath("//label[@class='control-label']//h4[contains(text(),'Select Employees')]"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", selectEmployeesLabel);
		List<WebElement> additionalEmployeeList = driver.findElements(alertsEmployeesAdditionalEmployeeTable);
		int additionalEmployeeTableSize = additionalEmployeeList.size();
		Random random3 = new Random();
		int randomEmpValue = random3.nextInt(additionalEmployeeTableSize);
		if(randomEmpValue==0){
			randomEmpValue= randomEmpValue+1;
		}
		thread();
		waitForPageLoad();
		
	/*	WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//table[@id='additional_employee_table']//tbody//tr["+randomEmpValue+"]//td[1]")));
		 */
		webElement(By.xpath("//table[@id='additional_employee_table']//tbody//tr["+randomEmpValue+"]//td[1]"));        	
		scrollInnerScrollBar(webelementFrame);
		thread(); 
		
		driver.findElement(By.xpath("//table[@id='additional_employee_table']//tbody//tr["+randomEmpValue+"]//td[1]")).click();
		thread();
		
		String empFirstNameBeforeMapping = driver.findElement(By.xpath("//table[@id='additional_employee_table']//tbody//tr["+randomEmpValue+"]//td[2]")).getText();
		String empSecondNameBeforeMapping = driver.findElement(By.xpath("//table[@id='additional_employee_table']//tbody//tr["+randomEmpValue+"]//td[3]")).getText();
		click(addBtnAddEmployeeWindow);
		thread();
		driver.findElement(By.xpath("//table[@id='employee_table']//tbody//tr//td[9]")).click();
		thread();
		driver.findElement(By.xpath("//button[@data-bb-handler='confirm']")).click();
		waitForElement(msgNotificationBar); 
		getObjectText(msgNotificationBar);
		takeScreenshot();
		try
		{
			verifyAssertEquals(empFirstNameBeforeMapping+" "+empSecondNameBeforeMapping+" "+"is removed from the list", getActualObjectTxt);
		}
		catch(Error e)
		{
			e.printStackTrace();
		}
		
	}
	
	@Test(priority=14)
	void searchAlertNames() throws IOException, InterruptedException
	{
		click(alertsBtnInTopMnu);
		click(alertsOptnInTopDDLst);
		thread();
		getTotalValuesIndd(alAlertsListViewCnt);
		Random random = new Random();
		int ralrtNm = random.nextInt(totalDDValCount-1)+1;
		String getrAlertNm = driver.findElement(By.xpath("//table[@id='alert_table']//tbody/tr["+ralrtNm+"]/td[2]")).getText();
				
		//		Valid Alert Name
		enterText(alSearchAlerts, getrAlertNm);
		getObjectText(alSrchRsltText);
		verifyAssertEquals(getrAlertNm, getActualObjectTxt);
		takeScreenshot();
		clear(alSearchAlerts);
		enterKeyInKyBord(alSearchAlerts);
		
		//		Invalid Alert Name
		enterText(alSearchAlerts, "Inv Alert Name");
		getObjectText(noRecordsFoundMsg);
		verifyAssertEquals(noMatchingRecordsFound, getActualObjectTxt); 
		clear(alSearchAlerts);
		enterKeyInKyBord(alSearchAlerts);
	}
	
	@Test(priority=15)
	void verifySelectAllCheckBox() throws InterruptedException
	{
		click(alSelectAllChkBox);
		
		getTotalValuesIndd(alAlertsListViewCnt);
		for(int i=1; i<totalDDValCount; i++)
		{
			boolean selectAllChk = driver.findElement(By.xpath("//table[@id='alert_table']//tbody/tr["+i+"]//span/input")).isSelected();
			Assert.assertEquals(true, selectAllChk);
		}
		
		click(alSelectAllChkBox);
		thread();
	}
	
	
	
	@Test(priority=16)
	void showEntriesDropdown() throws InterruptedException, IOException
	{
		try
		{
			getObjectText(alAlertsListViewRecsInfo);
			
			if(Integer.parseInt(getActualObjectTxt.split("of ")[1].split(" ")[0].trim()) > 10)
			{
				selectTextFromDropdown(alAlertsListViewLength , "25");
				thread();
				takeScreenshot();
				
				selectTextFromDropdown(alAlertsListViewLength , "10");
				thread();
				
				selectTextFromDropdown(alAlertsListViewLength , "50");
				thread();
				
				selectTextFromDropdown(alAlertsListViewLength , "100");
				thread();
				
			}
		}
		catch(WebDriverException e)
		{
			System.out.println("Alerts list view has 10 or less than 10 records. So values are not selected from show entries drop down");
		}
	}
	
	@Test(priority=17)
	void alertListViewPagination() throws InterruptedException
	{
		getTotalValuesIndd(alAlertsListViewPagination);
		
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
				int randomPge = randomPagi.nextInt((totalDDValCount)-4)+4;
				driver.findElement(By.xpath("//div[@id='alert_table_wrapper']/div/div//ul/li["+randomPge+"]/a")).click();
				thread();
				takeScreenshot();
				click(pagiStartArw);
				thread();
			}	
			else
			{
				System.out.println("*****Pagination is not available in Alerts List View. List View has 10 or less than 10 records*****");
			}
		}
		catch(Exception e)
		{
			//e.printStackTrace();
		}
		thread();
	}
	

	@Test(priority=18)
	void infoPopup() throws InterruptedException
	{
		click(alOvrInfoBtn);
		try{
		verifyAssert(alAlertHelpPp);
		}
		catch(Exception e)
		{
			//
		}
		
		webElement(alAlertHelpPpCloseBtn);
		scrollInnerScrollBar(webelementFrame);
		click(alAlertHelpPpCloseBtn);
		thread();
	}
	
	
	@Test(priority=19)
	void verifyMultipleDeleteMessage() throws InterruptedException
	{
		click(alOvrDeleteBtn);
		thread();
		
		//		Verify Select Records To Perform Delete Message
		getObjectText(msgNotificationBar);
		verifyAssertEquals(loc_AddnDeleteMultiple.selectRecordsToPerformDelete, getActualObjectTxt);
		thread();
		
	}
		
	@Test(priority=20, invocationCount=2)
	void addAlertsForMassDelete() throws BiffException, InterruptedException, IOException
	{
			addNewAlert();
			changeAlertStatusToClosed();
			Thread.sleep(10000);
	}
		
		
	@Test(priority=21)
	void verifyMassDeleteFunctionaltiy() throws InterruptedException, IOException
	{
		for(int i=1;i<=2;i++)
		{
			driver.findElement(By.xpath("//table[@id='alert_table']/tbody/tr["+i+"]/td[1]/div/span/input")).click();
			thread();
		}
			
		click(alOvrDeleteBtn);
		Thread.sleep(10000);
	    verifyAssert(deleteConfPopup);
		getObjectText(deleteConfPpMessage);
		verifyAssertEquals("Confirm to remove 2 record(s) from Alerts?", getActualObjectTxt);
		click(delConfOkBtn);
		thread();
		
		
		getObjectText(msgNotificationBar);
		verifyAssertEquals("2 records deleted successfully", getActualObjectTxt);
		Thread.sleep(10000);
	}
	

	/*@Test(priority=12)
	void logout() throws InterruptedException
	{
		ll.logoutSession();
		thread();
		quitWindow();
	}*/
}
