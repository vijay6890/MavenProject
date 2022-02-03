package Scripts;

import static Config.TakScreenshot.takeScreenshot;
import static ObjectRepository.Locations.locationsInMainMenu;
import static ObjectRepository.Locations.msgNotificationBar;
import static ObjectRepository.Locations.rightMoveAllBtnThreatRltWindow;
import static ObjectRepository.Locations.threatsLeftMoveBtnRltWindow;
import static ObjectRepository.Locations.threatsLeftRltWindowValues;
import static ObjectRepository.Locations.threatsLeftSearchTxtBoxRltWindow;
import static ObjectRepository.Locations.threatsRightSearchTxtBoxRltWindow;
import static ObjectRepository.Locations.threatsTabRelationSection;
import static ObjectRepository.TaskGroups.taskRightMoveAllArrowRltWindow;
import static ObjectRepository.TaskGroups.tasksLeftMoveBtnRltWindow;
import static ObjectRepository.TaskGroups.tasksLeftRltWindowValues;
import static ObjectRepository.TaskGroups.tasksLeftSearchTxtBoxRltWindow;
import static ObjectRepository.TaskGroups.tasksRightSearchTxtBoxRltWindow;
import static ObjectRepository.TaskGroups.tasksTabRltSection;
import static ObjectRepository.TaskGroups.*;
import static ObjectRepository.Employees.*;
import static ObjectRepository.Tasks.*;
import static ObjectRepository.Threats.*;
import static ObjectRepository.Assets.*;
import static ObjectRepository.Insurance.*;
import static ObjectRepository.BusinessFunctions.*;
import static ObjectRepository.ContactGroups.contactGroupsInMainMenu;
import static ObjectRepository.Tasks.*;
import static ObjectRepository.Teams.*;
import static ObjectRepository.AssetGroups.*;
import static ObjectRepository.Contacts.*;
import static UIWrappers.UIObjects.click;
import static UIWrappers.UIObjects.enterText;
import static UIWrappers.UIObjects.getActualObjectTxt;
import static UIWrappers.UIObjects.getObjectText;
import static UIWrappers.UIObjects.scrollToBottom;
import static UIWrappers.UIObjects.thread;
import static UIWrappers.UIObjects.verifyAssertEquals;
import static UIWrappers.UIObjects.waitForElement;
import static UIWrappers.UIObjects.waitForPageLoad;
import static UIWrappers.UIObjects.verifyAssertEquals;
import static UIWrappers.UIObjects.scrollToTop;

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
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import UIWrappers.Page;
import jxl.read.biff.BiffException;

public class SIBTwoWayMappingTasks extends Page{
	String flag;
	String taskName,taskID,taskType,taskDuration,taskEmployeeAssigned,taskStatus,taskComments;
	String employeeFirstName,employeeSecondName,employeeName,teamName,contactFirstName,contactLastName,contactName;
	String contactGroupName,assetName,assetGroupName,businessFunctionName,taskGroupName;
	
LoginLogout ll = new LoginLogout();
	
	@Test(priority=0)
	void login() throws BiffException, IOException, InterruptedException
	{
		ll.loginToSIB();
		waitForPageLoad();
		driver.findElement(By.id("cometchat_hide")).click();
	}
	
	@Test(priority=1)
	void tasksDataMappingValidations() throws InterruptedException, IOException{
		click(tasksInMainMenu);
		thread();
		List<WebElement> tasksList = driver.findElements(By.xpath("//table[@id='tasks_table']//tbody[@role='alert']//tr"));
		int taskListSize = tasksList.size();
		Random random = new Random();
		int randomTaskValue = random.nextInt(taskListSize);
		if(randomTaskValue==0){
			randomTaskValue=randomTaskValue+1;
		}
		System.out.println("Task value inside List view+++"+randomTaskValue);
		driver.findElement(By.xpath("//table[@id='tasks_table']//tbody[@role='alert']//tr"+"["+randomTaskValue+"]")).click();
		taskName = driver.findElement(By.xpath("//table[@id='tasks_table']//tbody[@role='alert']//tr"+"["+randomTaskValue+"]//td[2]")).getText();
		System.out.println("Task name inside list view+++"+taskName);
		taskID = driver.findElement(By.xpath("//table[@id='tasks_table']//tbody[@role='alert']//tr"+"["+randomTaskValue+"]//td[3]")).getText();
		System.out.println("Task ID inside list view+++"+taskID);
		taskType = driver.findElement(By.xpath("//table[@id='tasks_table']//tbody[@role='alert']//tr"+"["+randomTaskValue+"]//td[4]")).getText();
		System.out.println("Task type inside list view+++"+taskType);
		taskDuration = driver.findElement(By.xpath("//table[@id='tasks_table']//tbody[@role='alert']//tr"+"["+randomTaskValue+"]//td[5]")).getText();
		System.out.println("Task duration inside list view+++"+taskDuration);
		taskEmployeeAssigned = driver.findElement(By.xpath("//table[@id='tasks_table']//tbody[@role='alert']//tr"+"["+randomTaskValue+"]//td[6]")).getText();
		System.out.println("Employee assigned inside list view+++"+taskEmployeeAssigned);
		taskStatus = driver.findElement(By.xpath("//table[@id='tasks_table']//tbody[@role='alert']//tr"+"["+randomTaskValue+"]//td[7]")).getText();
		System.out.println("Task status inside list view+++"+taskStatus);
		taskComments = driver.findElement(By.xpath("//table[@id='tasks_table']//tbody[@role='alert']//tr"+"["+randomTaskValue+"]//td[8]")).getText();
		System.out.println("Task comments inside list view+++"+taskComments);
		click(addRemoveRelationsBtn);
		thread();
		click(rightMoveAllBtnRltWindow);
		List <WebElement> employeeValuesRltWindowList = driver.findElements(employeeValuesRltWindow);
		int employeeValuesRltWindowSize = employeeValuesRltWindowList.size();
		Random random1 = new Random();
		int randomEmpValue = random1.nextInt(employeeValuesRltWindowSize);
		if(randomEmpValue==0){
			randomEmpValue= randomEmpValue+1;
		}
		System.out.println("Employee value inside relationship section++++"+randomEmpValue);
		String empName =driver.findElement(By.xpath("//label[contains(text(),'List of Employees')]/..//button[@class='btn moveall']/../../select//option"+"["+randomEmpValue+"]")).getText();
		driver.findElement(By.xpath("//label[contains(text(),'List of Employees')]/..//button[@class='btn moveall']/../../select//option"+"["+randomEmpValue+"]")).click();
	    enterText(empLeftSearchTxtBoxRltWindow,empName);
	    thread();
	    click(empMoveLeftButtonRltTable);
	    enterText(empRightSearchTxtBoxRltWindow,empName);
	    thread();
	    click(submitBtnRltWindow);
	    waitForElement(msgNotificationBar);
		getObjectText(msgNotificationBar);
		takeScreenshot();
		verifyAssertEquals(taskName + " " + "successfully mapped to Employees", getActualObjectTxt);
		thread();
		scrollToBottom();
		employeeFirstName = driver.findElement(By.xpath("//table[@id='rel_resources']//tbody[@role='alert']//span")).getText();
		employeeSecondName = driver.findElement(By.xpath("//table[@id='rel_resources']//tbody[@role='alert']//td[2]")).getText();
		employeeName = employeeFirstName+" "+employeeSecondName;
		
		click(teamsTabTasksRltSection);
    	thread();
    	click(addRemoveRelationsBtn);
    	thread();
		click(rightMoveAllArrowTeamsRltWindow);
		List<WebElement> teamsLeftRltWindowValuesList = driver.findElements(teamsLeftRltWindowValues);
		int teamsLeftRltWindowValuesListSize = teamsLeftRltWindowValuesList.size();
		Random random2 = new Random();
		int randomTeamValue = random2.nextInt(teamsLeftRltWindowValuesListSize);
		if(randomTeamValue==0){
			randomTeamValue = randomTeamValue+1;
		}
		System.out.println("Team value inside relationship section++++"+randomTeamValue);
		String teamNameInRltWindow = driver.findElement(By.xpath("//label[contains(text(),'List of Teams')]/..//button[@class='btn moveall']/../../select//option"+"["+randomTeamValue+"]")).getText();
		driver.findElement(By.xpath("//label[contains(text(),'List of Teams')]/..//button[@class='btn moveall']/../../select//option"+"["+randomTeamValue+"]")).click();
		enterText(teamsLeftSearchTxtBoxRltWindow,teamNameInRltWindow);
		click(teamsLeftMoveBtnRltWindow);
		enterText(teamsRightSearchTxtBoxRltWindow,teamNameInRltWindow);
		click(submitButtonRelationShipWindow);
		waitForElement(msgNotificationBar);
		getObjectText(msgNotificationBar);
		takeScreenshot();
		verifyAssertEquals(taskName+" "+"successfully mapped to Teams", getActualObjectTxt);
		thread();
		scrollToBottom();
		thread();
		scrollToBottom();
		teamName = driver.findElement(By.xpath("//table[@id='rel_resource_grp']//tbody[@role='alert']//span")).getText();
		
		click(contactsTabTasksRltSection);
    	thread();
    	click(addRemoveRelationsBtn);
    	thread();
    	click(rightMoveAllArrowContactsRltWindow);
    	List<WebElement> contactsLeftRltWindowValuesList = driver.findElements(contactsLeftRltWindowValues);
    	int contactsLeftRltWindowValuesSize = contactsLeftRltWindowValuesList.size();
    	Random random3 = new Random();
    	int randomContactValue = random3.nextInt(contactsLeftRltWindowValuesSize);
    	if(randomContactValue==0){
    		randomContactValue = randomContactValue+1;
    	}
    	System.out.println("Contacts value inside relationship section++++"+randomContactValue);
    	String contactNameInRltWindow = driver.findElement(By.xpath("//label[contains(text(),'List of Contacts')]/..//button[@class='btn moveall']/../../select//option"+"["+randomContactValue+"]")).getText();
    	driver.findElement(By.xpath("//label[contains(text(),'List of Contacts')]/..//button[@class='btn moveall']/../../select//option"+"["+randomContactValue+"]")).click();
    	enterText(contactsLeftSearchTxtBoxRltWindow,contactNameInRltWindow);
    	thread();
    	click(contactsLeftMoveBtnRltWindow);
    	enterText(contactsRightSearchTxtBoxRltWindow,contactNameInRltWindow);
    	click(submitButtonRelationShipWindow);
    	waitForElement(msgNotificationBar);
		getObjectText(msgNotificationBar);
		takeScreenshot();
		verifyAssertEquals(taskName+" "+"successfully mapped to Contacts", getActualObjectTxt);
		thread();
		scrollToBottom();
		contactFirstName = driver.findElement(By.xpath("//table[@id='rel_contacts']//tbody[@role='alert']//span")).getText();
		contactLastName = driver.findElement(By.xpath("//table[@id='rel_contacts']//tbody[@role='alert']//td[2]")).getText();
		contactName = contactFirstName+" "+contactLastName;
		
		click(contactsGroupTabRltSection);
		thread();
		click(addRemoveRelationsBtn);
		click(contactGroupRightMoveAllArrowRltWindow);
		List<WebElement> contactGroupLeftRltWindowValuesList = driver.findElements(contactGroupLeftRltWindowValues);
		int contactGroupLeftRltWindowValuesListSize = contactGroupLeftRltWindowValuesList.size();
		Random random4 = new Random();
		int randomContactGroupValue = random4.nextInt(contactGroupLeftRltWindowValuesListSize);
		if(randomContactGroupValue==0){
			   randomContactGroupValue = randomContactGroupValue+1;
		   }
		System.out.println("Contact group value inside relationship section++++"+randomContactGroupValue);
		String contactGroupNameInRltWindow = driver.findElement(By.xpath("//label[contains(text(),'List of Contact Groups')]/..//button[@class='btn moveall']/../../select//option"+"["+randomContactGroupValue+"]")).getText();
		driver.findElement(By.xpath("//label[contains(text(),'List of Contact Groups')]/..//button[@class='btn moveall']/../../select//option"+"["+randomContactGroupValue+"]")).click();
		enterText(contactGroupLeftSearchTxtBoxRltWindow,contactGroupNameInRltWindow);
		thread();
		click(contactGroupLeftMoveBtnRltWindow);
		enterText(contactGroupRightSearchTxtBoxRltWindow,contactGroupNameInRltWindow);
		click(submitButtonRelationShipWindow);
	    waitForElement(msgNotificationBar);
		getObjectText(msgNotificationBar);
		takeScreenshot();
		verifyAssertEquals(taskName+" "+"successfully mapped to Contact Groups", getActualObjectTxt);
		thread();
		scrollToBottom();
		contactGroupName = driver.findElement(By.xpath("//table[@id='rel_contact_grp']//tbody[@role='alert']//tr//td[1]")).getText();
		
		click(assetsTabInsuranceRelationSection);
		thread();
		click(addRemoveRelationsBtn);
		click(rightMoveAllBtnAssetRltWindow);
		List<WebElement> leftAssetRelationWindowValueList = driver.findElements(leftAssetRelationWindowValues);
		int leftAssetRelationWindowValueListSize = leftAssetRelationWindowValueList.size();
		Random random5 = new Random();
		int assetValue = random5.nextInt(leftAssetRelationWindowValueListSize);
		if(assetValue == 0){
			assetValue = assetValue+1;
		}
		System.out.println("Asset value inside relationship section++++"+assetValue);
		String assetNameInRltWindow = driver.findElement(By.xpath("//label[contains(text(),'List of Assets')]/..//button[@class='btn moveall']/../../select//option"+"["+assetValue+"]")).getText();
		driver.findElement(By.xpath("//label[contains(text(),'List of Assets')]/..//button[@class='btn moveall']/../../select//option"+"["+assetValue+"]")).click();
		enterText(assetLeftSearchTxtBoxRltWindow,assetNameInRltWindow);
		thread();
		click(leftMoveBtnAssetRltWindow);
		enterText(assetRightSearchTxtBoxRltWindow,assetNameInRltWindow);
		thread();
		click(submitButtonRelationShipWindow);
		waitForElement(msgNotificationBar);
		getObjectText(msgNotificationBar);
		takeScreenshot();
		verifyAssertEquals(taskName+" "+"successfully mapped to Assets", getActualObjectTxt);
		thread();
		scrollToBottom();
		assetName = driver.findElement(By.xpath("//table[@id='rel_assets']//tbody[@role='alert']//span")).getText();
		
		click(assetGroupsTabRltSection);
		thread();
		click(addRemoveRelationsBtn);
		click(assetGroupRightMoveAllArrowRltWindow);
		List<WebElement> assetGroupLeftRltWindowValuesList = driver.findElements(assetGroupLeftRltWindowValues);
		int assetGroupLeftRltWindowValuesSize = assetGroupLeftRltWindowValuesList.size();
		Random random6 = new Random();
		int assetGroupValue = random6.nextInt(assetGroupLeftRltWindowValuesSize);
		if(assetGroupValue==0){
			   assetGroupValue = assetGroupValue+1;
		   }
		System.out.println("Asset group value inside relationship section++++"+assetGroupValue);
		String randomAssetGroupName = driver.findElement(By.xpath("//label[contains(text(),'List of Asset Groups')]/..//button[@class='btn moveall']/../../select//option"+"["+assetGroupValue+"]")).getText();
		driver.findElement(By.xpath("//label[contains(text(),'List of Asset Groups')]/..//button[@class='btn moveall']/../../select//option"+"["+assetGroupValue+"]")).click();
		enterText(assetGroupLeftSearchTxtBoxRltWindow,randomAssetGroupName);
		thread();
		click(assetGroupLeftMoveBtnRltWindow);
	    enterText(assetGroupRightSearchTxtBoxRltWindow,randomAssetGroupName);
		thread();
		click(submitButtonRelationShipWindow);
		waitForElement(msgNotificationBar);
		getObjectText(msgNotificationBar);
		takeScreenshot();
		verifyAssertEquals(taskName+" "+"successfully mapped to Asset Groups", getActualObjectTxt);
		thread();
		scrollToBottom();
		assetGroupName = driver.findElement(By.xpath("//table[@id='rel_asset_grp']//tbody[@role='alert']//tr//td[1]")).getText();
		
		click(businessFntTabInsuranceRltSection);
		thread();
		click(addRemoveRelationsBtn);
		click(rightMoveAllBtnBusinessFtnRltWindow);
		List <WebElement> leftBusinessFntRltWindowValuesList = driver.findElements(leftBusinessFntRltWindowValues);
		int leftBusinessFntRltWindowValuesListSize = leftBusinessFntRltWindowValuesList.size();
		Random random7 = new Random();
		int leftBusinessFntRltWindowValue = random7.nextInt(leftBusinessFntRltWindowValuesListSize);
		if(leftBusinessFntRltWindowValue==0){
			leftBusinessFntRltWindowValue = leftBusinessFntRltWindowValue + 1;
		}
		System.out.println("Business function value inside relationship section++++"+leftBusinessFntRltWindowValue);
		String businessFunctionNameInRltWindow = driver.findElement(By.xpath("//label[contains(text(),'List of Business Functions')]/..//button[@class='btn moveall']/../../select//option"+"["+leftBusinessFntRltWindowValue+"]")).getText();
		driver.findElement(By.xpath("//label[contains(text(),'List of Business Functions')]/..//button[@class='btn moveall']/../../select//option"+"["+leftBusinessFntRltWindowValue+"]")).click();
		enterText(businessFntLeftSearchTextBoxRltWindow,businessFunctionNameInRltWindow);
		thread();
		click(leftMoveBtnBusinessFntRltWindow);
		enterText(businessFntRightSearchTextBoxRltWindow,businessFunctionNameInRltWindow);
		thread();
		click(submitButtonRelationShipWindow);
		waitForElement(msgNotificationBar);
		getObjectText(msgNotificationBar);
		takeScreenshot();
		verifyAssertEquals(taskName+" "+"successfully mapped to Business Functions", getActualObjectTxt);
		thread();
		scrollToBottom();
		businessFunctionName = driver.findElement(By.xpath("//table[@id='rel_processes']//tbody[@role='alert']//span")).getText();
		
		click(taskGroupsTabRltSection);
		click(addRemoveRelationsBtn);
		click(taskGroupsRightMoveAllArrowRltWindow);
		List <WebElement> leftTaskGroupsRltWindowValuesList = driver.findElements(taskGroupLeftRltWindowValues);
		int leftTaskGroupsRltWindowValuesListSize = leftTaskGroupsRltWindowValuesList.size();
		Random random8 = new Random();
		int leftTaskGroupsRltWindowValue = random8.nextInt(leftTaskGroupsRltWindowValuesListSize);
		if(leftTaskGroupsRltWindowValue==0){
			   leftTaskGroupsRltWindowValue = leftTaskGroupsRltWindowValue + 1;
			}
		System.out.println("Task group value inside relationship section++++"+leftTaskGroupsRltWindowValue);
		String taskGroupNameInRltWindow = driver.findElement(By.xpath("//label[contains(text(),'List of Task Groups')]/..//button[@class='btn moveall']/../../select//option"+"["+leftTaskGroupsRltWindowValue+"]")).getText();
		driver.findElement(By.xpath("//label[contains(text(),'List of Task Groups')]/..//button[@class='btn moveall']/../../select//option"+"["+leftTaskGroupsRltWindowValue+"]")).click();
		enterText(taskGroupLeftSearchTxtBoxRltWindow,taskGroupNameInRltWindow);
		thread();
		click(taskGroupsLeftMoveBtnRltWindow);
		enterText(taskGroupsRightSearchTxtBoxRltWindow,taskGroupNameInRltWindow);
		thread();
		click(submitButtonRelationShipWindow);
		waitForElement(msgNotificationBar);
		getObjectText(msgNotificationBar);
		takeScreenshot();
		verifyAssertEquals(taskName+" "+"successfully mapped to Task Groups", getActualObjectTxt);
		thread();
		scrollToBottom();
		taskGroupName = driver.findElement(By.xpath("//table[@id='rel_task_grp']//tbody[@role='alert']//tr//td[1]")).getText();
		
		scrollToTop();
		click(employeesInMainMenu);
		thread();
		Select employeesCountDD = new Select(driver.findElement(By.name("resource_table_length")));
		employeesCountDD.selectByVisibleText("100");
		thread();
		WebElement mappedEmployee = driver.findElement(By.xpath("//table[@id='resource_table']//tbody//tr[@data-for='"+employeeName+"']//td[2]"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", mappedEmployee);
		mappedEmployee.click();
		scrollToBottom();
		thread();
		Select tasksInnerCountDD = new Select(driver.findElement(By.name("rel_task_length")));
		tasksInnerCountDD.selectByVisibleText("100");
		thread();
		validateTaskValues();
		verifyAssertEquals(flag,"true");
		
		scrollToTop();
		click(teamsInMainMenu);
		thread();
		Select teamsCountDD = new Select(driver.findElement(By.name("resourcegroup_table_length")));
		teamsCountDD.selectByVisibleText("100");
		thread();
		WebElement mappedTeam = driver.findElement(By.xpath("//table[@id='resourcegroup_table']//tbody//tr[@data-for='"+teamName+"']//td[2]"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", mappedTeam);
		mappedTeam.click();
		scrollToBottom();
		click(tasksTabRltSection);
		thread();
		Select tasksInnerCountDD1 = new Select(driver.findElement(By.name("rel_task_length")));
		tasksInnerCountDD1.selectByVisibleText("100");
		thread();
		validateTaskValues();
		verifyAssertEquals(flag,"true");
		
		scrollToTop();
		click(contactsInMainMenu);
		thread();
		Select contactsCountDD = new Select(driver.findElement(By.name("contacts_table_length")));
		contactsCountDD.selectByVisibleText("100");
		thread();
		WebElement mappedContact = driver.findElement(By.xpath("//table[@id='contacts_table']//tbody//td[contains(text(),'"+contactFirstName+"')]/../td//div[contains(text(),'"+contactLastName+"')]"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", mappedContact);
		mappedContact.click();
		scrollToBottom();
		thread();
		Select tasksInnerCountDD2 = new Select(driver.findElement(By.name("rel_task_length")));
		tasksInnerCountDD2.selectByVisibleText("100");
		thread();
		validateTaskValues();
		verifyAssertEquals(flag,"true");
		
		scrollToTop();
		click(contactGroupsInMainMenu);
		thread();
		Select contactGroupsCountDD = new Select(driver.findElement(By.name("contactgroup_table_length")));
		contactGroupsCountDD.selectByVisibleText("100");
		thread();
		WebElement mappedContactGroup = driver.findElement(By.xpath("//table[@id='contactgroup_table']//tbody//tr[@data-for='"+contactGroupName+"']//td[2]"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", mappedContactGroup);
		mappedContactGroup.click();
		scrollToBottom();
		click(tasksTabRltSection);
		thread();
		Select tasksInnerCountDD3 = new Select(driver.findElement(By.name("rel_task_length")));
		tasksInnerCountDD3.selectByVisibleText("100");
		thread();
		validateTaskValues();
		verifyAssertEquals(flag,"true");
		
		scrollToTop();
		click(assetsInMainMenu);
		thread();
		Select assetsCountDD = new Select(driver.findElement(By.name("assets_table_length")));
		assetsCountDD.selectByVisibleText("100");
		thread();
		WebElement mappedAsset = driver.findElement(By.xpath("//table[@id='assets_table']//tbody//tr[@data-for='"+assetName+"']//td[2]"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", mappedAsset);
		mappedAsset.click();
		scrollToBottom();
		click(tasksTabRltSection);
		thread();
		Select tasksInnerCountDD4 = new Select(driver.findElement(By.name("rel_task_length")));
		tasksInnerCountDD4.selectByVisibleText("100");
		thread();
		validateTaskValues();
		verifyAssertEquals(flag,"true");
		
		scrollToTop();
		click(assetGroupsInMainMenu);
		thread();
		Select assetGroupCountDD = new Select(driver.findElement(By.name("asset_group_table_length")));
		assetGroupCountDD.selectByVisibleText("100");
		thread();
		WebElement mappedassetGroup = driver.findElement(By.xpath("//table[@id='asset_group_table']//tbody//tr[@data-for='"+assetGroupName+"']//td[2]"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", mappedassetGroup);
		mappedassetGroup.click();
		scrollToBottom();
		click(tasksTabRltSection);
		thread();
		Select tasksInnerCountDD5 = new Select(driver.findElement(By.name("rel_task_length")));
		tasksInnerCountDD5.selectByVisibleText("100");
		thread();
		validateTaskValues();
		verifyAssertEquals(flag,"true");
		
		scrollToTop();
		click(businessFunctionsInMainMenu);
		thread();
		Select businessFunctionsCountDD = new Select(driver.findElement(By.name("process_table_length")));
		businessFunctionsCountDD.selectByVisibleText("100");
		thread();
		WebElement mappedbusinessFunction = driver.findElement(By.xpath("//table[@id='process_table']//tbody//tr[@data-for='"+businessFunctionName+"']//td[2]"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", mappedbusinessFunction);
		mappedbusinessFunction.click();
		scrollToBottom();
		click(tasksTabRltSection);
		thread();
		Select tasksInnerCountDD6 = new Select(driver.findElement(By.name("rel_task_length")));
		tasksInnerCountDD6.selectByVisibleText("100");
		thread();
		validateTaskValues();
		verifyAssertEquals(flag,"true");
		
		scrollToTop();
		click(taskGroupsInMainMenu);
		thread();
		Select taskGroupsCountDD = new Select(driver.findElement(By.name("task_group_table_length")));
		taskGroupsCountDD.selectByVisibleText("100");
		thread();
		WebElement mappedTaskGroup = driver.findElement(By.xpath("//table[@id='task_group_table']//tbody//tr[@data-for='"+taskGroupName+"']//td[2]"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", mappedTaskGroup);
		mappedTaskGroup.click();
		scrollToBottom();
		click(tasksTabRltSection);
		thread();
		Select tasksInnerCountDD7 = new Select(driver.findElement(By.name("rel_task_length")));
		tasksInnerCountDD7.selectByVisibleText("100");
		thread();
		validateTaskValues();
		verifyAssertEquals(flag,"true");
		
		
		
		
	}
	
	public String validateTaskValues(){
		List<WebElement> tasksList = driver.findElements(By.xpath("//table[@id='rel_task']//tbody//tr"));
		int tasksListSize = tasksList.size();
		for(int i=1;i<=tasksListSize;i++){
			if(driver.findElement(By.xpath("//table[@id='rel_task']//tbody//tr["+i+"]//td[1]")).getText().equals(taskName)){
				System.out.println("Task name is+++"+taskName);
				verifyAssertEquals(driver.findElement(By.xpath("//table[@id='rel_task']//tbody//tr["+i+"]//td[2]")).getText(),taskID);
				System.out.println("Task ID is+++"+driver.findElement(By.xpath("//table[@id='rel_task']//tbody//tr["+i+"]//td[2]")).getText());
				verifyAssertEquals(driver.findElement(By.xpath("//table[@id='rel_task']//tbody//tr["+i+"]//td[3]")).getText(),taskType);
				System.out.println("Task Type is+++"+driver.findElement(By.xpath("//table[@id='rel_task']//tbody//tr["+i+"]//td[3]")).getText());
				verifyAssertEquals(driver.findElement(By.xpath("//table[@id='rel_task']//tbody//tr["+i+"]//td[4]")).getText(),taskDuration);
				System.out.println("Task duration is+++"+driver.findElement(By.xpath("//table[@id='rel_task']//tbody//tr["+i+"]//td[4]")).getText());
				verifyAssertEquals(driver.findElement(By.xpath("//table[@id='rel_task']//tbody//tr["+i+"]//td[5]")).getText(),taskEmployeeAssigned);
				System.out.println("Task employee assigned is+++"+driver.findElement(By.xpath("//table[@id='rel_task']//tbody//tr["+i+"]//td[5]")).getText());
				verifyAssertEquals(driver.findElement(By.xpath("//table[@id='rel_task']//tbody//tr["+i+"]//td[6]")).getText(),taskStatus);
				System.out.println("Task status is+++"+driver.findElement(By.xpath("//table[@id='rel_task']//tbody//tr["+i+"]//td[6]")).getText());
				verifyAssertEquals(driver.findElement(By.xpath("//table[@id='rel_task']//tbody//tr["+i+"]//td[7]")).getText(),taskComments);
				System.out.println("Task comments are+++"+driver.findElement(By.xpath("//table[@id='rel_task']//tbody//tr["+i+"]//td[7]")).getText());
				flag="true";
			}else
			{
				flag="false";
			}
		}
		return flag;
	}

}
