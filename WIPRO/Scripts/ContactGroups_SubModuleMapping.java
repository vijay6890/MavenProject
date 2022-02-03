package Scripts;

/******************************************************************************************************************************
 * 
 * @author Rahul Nair
 * 
 * Test 1: Login to SIB application
 * Test 2: Navigate to Contact Groups module
 * Test 3: Verify sub module mapping functionality in Contacts sub tab
 * Test 4: Verify sub module mapping functionality in Tasks sub tab
 
*******************************************************************************************************************************/

import java.io.IOException;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import static UIWrappers.UIObjects.*;

import UIWrappers.Page;
import jxl.read.biff.BiffException;

import static Config.TakScreenshot.takeScreenshot;
import static ObjectRepository.AssetGroups.assetGroupsInMainMenu;
import static ObjectRepository.Assets.assetsInMainMenu;
import static ObjectRepository.Insurance.*;
import static ObjectRepository.Locations.msgNotificationBar;
import static ObjectRepository.TaskGroups.taskRightMoveAllArrowRltWindow;
import static ObjectRepository.TaskGroups.tasksAddRemoveBtnInnerRltTable;
import static ObjectRepository.TaskGroups.tasksLeftInnerRltValues;
import static ObjectRepository.TaskGroups.tasksLeftMoveArrowInnerRltTable;
import static ObjectRepository.TaskGroups.tasksLeftMoveBtnRltWindow;
import static ObjectRepository.TaskGroups.tasksLeftRltWindowValues;
import static ObjectRepository.TaskGroups.tasksLeftSearchTxtBoxRltWindow;
import static ObjectRepository.TaskGroups.tasksRightMoveAllArrowInnerRltTable;
import static ObjectRepository.TaskGroups.tasksRightRltValues;
import static ObjectRepository.TaskGroups.tasksRightSearchTxtBoxRltWindow;
import static ObjectRepository.TaskGroups.tasksRltTabValues;
import static ObjectRepository.TaskGroups.tasksSearchTxtBoxInnerRltTable;
import static ObjectRepository.TaskGroups.tasksSubmitBtnInnerRltTable;
import static ObjectRepository.TaskGroups.tasksTabRltSection;
import static ObjectRepository.TaskGroups.tasksValueAfterMapping;
import static ObjectRepository.TaskGroups.tasksViewBtnRltWindow;
import static ObjectRepository.Tasks.contactsAddRemoveBtnInnerRltWindow;
import static ObjectRepository.Tasks.contactsLeftInnerTableValues;
import static ObjectRepository.Tasks.contactsLeftMoveAllArrowInnerRltTable;
import static ObjectRepository.Tasks.contactsLeftMoveBtnInnerRltTable;
import static ObjectRepository.Tasks.contactsLeftMoveBtnRltWindow;
import static ObjectRepository.Tasks.contactsLeftRltWindowValues;
import static ObjectRepository.Tasks.contactsLeftSearchTxtBoxRltWindow;
import static ObjectRepository.Tasks.contactsRightInnerTableValues;
import static ObjectRepository.Tasks.contactsRightMoveAllArrowInnerRltTable;
import static ObjectRepository.Tasks.contactsRightSearchTxtBoxRltWindow;
import static ObjectRepository.Tasks.contactsSearchTxtBoxInnerRltTable;
import static ObjectRepository.Tasks.contactsSubmitButtonInnerRltTable;
import static ObjectRepository.Tasks.contactsTabTasksRltSection;
import static ObjectRepository.Tasks.contactsTabValueInnerRltTable;
import static ObjectRepository.Tasks.contactsValueInnerRltTableAfterMapping;
import static ObjectRepository.Tasks.contactsViewBtnRltWindow;
import static ObjectRepository.Tasks.rightMoveAllArrowContactsRltWindow;
import static ObjectRepository.ContactGroups.*;

public class ContactGroups_SubModuleMapping extends Page{
	String contactGroupName;
	LoginLogout ll = new LoginLogout();
	
	/*@Test(priority = 0)
	void login() throws BiffException, IOException, InterruptedException {
		ll.loginToSIB();
		driver.findElement(By.id("cometchat_hide")).click();
	}*/

	@Test(priority = 1)
	void navigateToContactGroupsModule() throws InterruptedException {
		click(contactGroupsInMainMenu);
		thread();				
	}
	
	@Test(priority=2)
    void verifySubModuleMappingContacts() throws IOException, InterruptedException{
    	String contactsDefaultRowCountAfterMapping = "3";
    	String flag;
    	String[] leftInnerTableValue = new String[100];
		String[] rightInnerTableValue = new String[100];
		String[] tableValueAfterMapping = new String[100];
		String[] flagArray = new String[100];
		List<WebElement> contactGroupsList = driver.findElements(By.xpath("//table[@id='contactgroup_table']//tbody[@role='alert']//tr"));
		int contactGroupsListSize = contactGroupsList.size();
		Random random = new Random();
		int contactGroupValue = random.nextInt(contactGroupsListSize);
		if(contactGroupValue==0){
			contactGroupValue = contactGroupValue+1;
		}
		System.out.println("Random value in contact groups list view+++"+contactGroupValue);
		driver.findElement(By.xpath("//table[@id='contactgroup_table']//tbody[@role='alert']//tr"+"["+contactGroupValue+"]")).click();
		contactGroupName = driver.findElement(By.xpath("//table[@id='contactgroup_table']//tbody[@role='alert']//tr["+contactGroupValue+"]")).getAttribute("data-for");
		thread();
    	click(addRemoveRelationsBtn);
    	thread();
    	click(rightMoveAllArrowContactsRltWindow);
    	List<WebElement> contactsLeftRltWindowValuesList = driver.findElements(contactsLeftRltWindowValues);
    	int contactsLeftRltWindowValuesSize = contactsLeftRltWindowValuesList.size();
    	Random random1 = new Random();
    	int randomContactValue = random1.nextInt(contactsLeftRltWindowValuesSize);
    	if(randomContactValue==0){
    		randomContactValue = randomContactValue+1;
    	}
    	System.out.println("Contact value inside relationship section+++"+randomContactValue);
    	String contactName = driver.findElement(By.xpath("//label[contains(text(),'List of Contacts')]/..//button[@class='btn moveall']/../../select//option"+"["+randomContactValue+"]")).getText();
    	driver.findElement(By.xpath("//label[contains(text(),'List of Contacts')]/..//button[@class='btn moveall']/../../select//option"+"["+randomContactValue+"]")).click();
    	enterText(contactsLeftSearchTxtBoxRltWindow,contactName);
    	thread();
    	click(contactsLeftMoveBtnRltWindow);
    	enterText(contactsRightSearchTxtBoxRltWindow,contactName);
    	thread();
    	click(submitButtonRelationShipWindow);
    	waitForElement(msgNotificationBar);
		getObjectText(msgNotificationBar);
		takeScreenshot();
		//verifyAssertEquals(contactGroupName+"successfully mapped to Contacts", getActualObjectTxt);
		thread();
		scrollToBottom();
		String contactValueAfterMapping = driver.findElement(By.xpath("//table[@id='rel_contacts']//tbody[@role='alert']//span")).getText();
		if(contactName.contains(contactValueAfterMapping)){
			flag="true";
		}else
		{
			flag="false";
		}
		verifyAssertEquals(flag,"true");		
		click(contactsViewBtnRltWindow);
		thread();
		scrollToBottom();
		click(contactsAddRemoveBtnInnerRltWindow);
		thread();
		scrollToBottom();
		List<WebElement> contactsAddRemoveBtnInnerRltWindowList = driver.findElements(contactsAddRemoveBtnInnerRltWindow);
		List<WebElement> contactsTabValueInnerRltTableList = driver.findElements(contactsTabValueInnerRltTable);
		int contactsTabValueInnerRltTableListSize = contactsTabValueInnerRltTableList.size();
		List<WebElement> contactsSearchTxtBoxInnerRltTableList = driver.findElements(contactsSearchTxtBoxInnerRltTable);
		List<WebElement> contactsSubmitButtonInnerRltTableList = driver.findElements(contactsSubmitButtonInnerRltTable);
		List<WebElement> contactsLeftMoveAllArrowInnerRltTableList = driver.findElements(contactsLeftMoveAllArrowInnerRltTable);
		List<WebElement> contactsLeftMoveBtnInnerRltTableList = driver.findElements(contactsLeftMoveBtnInnerRltTable);
		List<WebElement> contactsRightMoveAllArrowInnerRltTableList = driver.findElements(contactsRightMoveAllArrowInnerRltTable);
		click(contactsRightMoveAllArrowInnerRltTable);
		By contactsXpathList[] = {locationSubTab,contactGroupsSubTab,taskSubTab};
		for(int i=0;i<contactsTabValueInnerRltTableListSize;i++){
			if(i>0){
				contactsTabValueInnerRltTableList.get(i).click();
				contactsAddRemoveBtnInnerRltWindowList.get(i).click();
				contactsRightMoveAllArrowInnerRltTableList.get(i).click();
				thread();
			}
			int leftListValueSize=driver.findElements(contactsLeftInnerTableValues).size();
			System.out.println("i value inside contacts loop+++"+i);
			System.out.println("contacts size inside loop"+leftListValueSize);
			Random random2 = new Random();
			int randomValue = random2.nextInt(leftListValueSize);
			System.out.println("Random value inside contacts loop+++"+randomValue);
			leftInnerTableValue[i] = driver.findElement(contactsXpathList[i]).getText();
			driver.findElement(contactsXpathList[i]).click();
			contactsSearchTxtBoxInnerRltTableList.get(i+i).sendKeys(leftInnerTableValue[i]);
			thread();
			contactsLeftMoveBtnInnerRltTableList.get(i).click();
			//contactsLeftMoveAllArrowInnerRltTableList.get(i).click();
			contactsSearchTxtBoxInnerRltTableList.get(i+i+1).sendKeys(leftInnerTableValue[i]);
			thread();
			contactsSubmitButtonInnerRltTableList.get(i).click();
			thread();	
			tableValueAfterMapping[i] = driver.findElements(contactsValueInnerRltTableAfterMapping).get(i).getText();
			if(leftInnerTableValue[i].contains(tableValueAfterMapping[i])){
				flagArray[i] = "true";
			}else
			{
				flagArray[i] = "false";
			}
			System.out.println(rightInnerTableValue[i]);
			System.out.println(tableValueAfterMapping[i]);
			System.out.println(flagArray[i]);
			verifyAssertEquals(flagArray[i],"true");
		}
		click(contactsViewBtnRltWindow);
		List<WebElement> rowsAfterMapping = driver.findElements(By.xpath("//table[@id='rel_contacts']//tbody[@role='alert']//tr[contains(@class,'tableRow')]"));
		int rowsAfterMappingSize = rowsAfterMapping.size();
		String contactsRowCountAfterMapping = Integer.toString(rowsAfterMappingSize);
		verifyAssertEquals(contactsRowCountAfterMapping,contactsDefaultRowCountAfterMapping);
    }
	
	@Test(priority = 3)
	void verifySubModuleMappingTasks() throws IOException, InterruptedException{
		String tasksDefaultCountAfterMapping = "8";
		String flag;
		String[] rightRelationshipTableValue = new String[100];
		String[] leftRelationshipTableValue = new String[100];
		String[] tableValueAfterMappingArray = new String[100];
		String[] flagArray = new String[100];
		click(tasksTabRltSection);
		click(addRemoveRelationsBtn);
		thread();
		click(taskRightMoveAllArrowRltWindow);
		List<WebElement> tasksLeftRltWindowValuesList = driver.findElements(tasksLeftRltWindowValues);
		int tasksLeftRltWindowValuesListSize = tasksLeftRltWindowValuesList.size();
		Random random1 = new Random();
		int randomTaskValue = random1.nextInt(tasksLeftRltWindowValuesListSize);
		if(randomTaskValue==0){
			randomTaskValue = randomTaskValue+1;
		}
		System.out.println("Task value inside relationship section+++"+randomTaskValue);
		String taskName = driver.findElement(By.xpath("//label[contains(text(),'List of Tasks')]/..//button[@class='btn moveall']/../../select//option"+"["+randomTaskValue+"]")).getText();
		driver.findElement(By.xpath("//label[contains(text(),'List of Tasks')]/..//button[@class='btn moveall']/../../select//option"+"["+randomTaskValue+"]")).click();
		enterText(tasksLeftSearchTxtBoxRltWindow,taskName);
		thread();
		click(tasksLeftMoveBtnRltWindow);
		enterText(tasksRightSearchTxtBoxRltWindow,taskName);
		thread();
		click(submitButtonRelationShipWindow);
		waitForElement(msgNotificationBar);
		getObjectText(msgNotificationBar);
		takeScreenshot();
		//verifyAssertEquals(contactGroupName+"successfully mapped to Tasks", getActualObjectTxt);
		thread();
		scrollToBottom();
		String taskValueAfterMapping = driver.findElement(By.xpath("//table[@id='rel_task']//tbody[@role='alert']//span")).getText();
		if(taskName.contains(taskValueAfterMapping)){
			flag="true";
		}
		else
		{
			flag="false";
		}
		verifyAssertEquals(flag,"true");
		click(tasksViewBtnRltWindow);
		thread();
		scrollToBottom();
		click(tasksAddRemoveBtnInnerRltTable);
		thread();
		List<WebElement> addRemoveButtonList = driver.findElements(tasksAddRemoveBtnInnerRltTable);
		List<WebElement> searchTextBoxInnerRelationTableList = driver.findElements(tasksSearchTxtBoxInnerRltTable);
		List<WebElement> leftTabsRelationshipTableList = driver.findElements(tasksRltTabValues);
		int leftTabsRelationshipTableListSize = leftTabsRelationshipTableList.size();
		List<WebElement> rightRelationshipTableMoveAllArrowList = driver.findElements(tasksRightMoveAllArrowInnerRltTable);
		List<WebElement> submitButtonList = driver.findElements(tasksSubmitBtnInnerRltTable);
		List<WebElement> valueAfterMappingList = driver.findElements(tasksValueAfterMapping);
		List<WebElement> LeftMoveBtnInnerRltTableList = driver.findElements(tasksLeftMoveArrowInnerRltTable);
		click(tasksRightMoveAllArrowInnerRltTable);
		scrollToBottom();
		thread();
		By tasksXpathList[] ={employeesSubTab,teamsSubTab,assetsSubTab,assetGroupsSubTab,contactsSubTab,contactGroupsSubTab,taskGroupsSubTab,businessFunctionsSubTab};
		for(int i=0;i<leftTabsRelationshipTableListSize;i++){
			
			if(i>0){
				leftTabsRelationshipTableList.get(i).click();
				addRemoveButtonList.get(i).click();		
				rightRelationshipTableMoveAllArrowList.get(i).click();
				thread();
			}
			int leftValueListSize = driver.findElements(tasksLeftInnerRltValues).size();
			System.out.println("i value inside tasks loop+++"+i);
			System.out.println("tasks size inside loop"+leftValueListSize);
			Random random3 = new Random();
			int randomValue = random3.nextInt(leftValueListSize);
			System.out.println("Random value nside tasks loop+++"+randomValue);
			leftRelationshipTableValue[i] = driver.findElement(tasksXpathList[i]).getText();
			driver.findElement(tasksXpathList[i]).click();
			searchTextBoxInnerRelationTableList.get(i+i).sendKeys(leftRelationshipTableValue[i]);
			thread();
			LeftMoveBtnInnerRltTableList.get(i).click();
			searchTextBoxInnerRelationTableList.get(i+i+1).sendKeys(leftRelationshipTableValue[i]);
			thread();
			
			submitButtonList.get(i).click();
			thread();
			tableValueAfterMappingArray[i] = driver.findElements(tasksValueAfterMapping).get(i).getText();
			if(leftRelationshipTableValue[i].contains(tableValueAfterMappingArray[i])){
				flagArray[i] = "true";
			}
			else{
				flagArray[i] = "false";
			}
			System.out.println(rightRelationshipTableValue[i]);
			System.out.println(tableValueAfterMappingArray[i]);
			System.out.println(flagArray[i]);
			verifyAssertEquals(flagArray[i],"true");
		}
		click(tasksViewBtnRltWindow);
		List<WebElement> rowsAfterMapping = driver.findElements(By.xpath("//table[@id='rel_task']//tbody[@role='alert']//tr[contains(@class,'tableRow')]"));
		int rowsAfterMappingSize = rowsAfterMapping.size();
		String tasksCountAfterMapping = Integer.toString(rowsAfterMappingSize);
		verifyAssertEquals(tasksCountAfterMapping,tasksDefaultCountAfterMapping);
	}

}
