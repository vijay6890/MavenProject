package Scripts;

/******************************************************************************************************************************
 * 
 * @author Rahul Nair
 * 
 * Test 1: Login to SIB application
 * Test 2: Navigate to threats module
 * Test 3: Verify sub module mapping functionality in Locations sub tab
 * Test 4: Verify sub module mapping functionality in Task Groups sub tab
 
 
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
import static ObjectRepository.Tasks.taskGroupInnerRltTableLeftSearchTxtBox;
import static ObjectRepository.Tasks.taskGroupInnerRltTableRightSearchTxtBox;
import static ObjectRepository.Tasks.taskGroupLeftInnerRltValues;
import static ObjectRepository.Tasks.taskGroupLeftRltWindowValues;
import static ObjectRepository.Tasks.taskGroupLeftSearchTxtBoxRltWindow;
import static ObjectRepository.Tasks.taskGroupRightMoveAllArrowInnerRltTable;
import static ObjectRepository.Tasks.taskGroupsAddRemoveBtnInnerRltTable;
import static ObjectRepository.Tasks.taskGroupsInnerRltSubmitBtn;
import static ObjectRepository.Tasks.taskGroupsLeftInnerRltMoveBtn;
import static ObjectRepository.Tasks.taskGroupsLeftMoveBtnRltWindow;
import static ObjectRepository.Tasks.taskGroupsRightInnerRltValues;
import static ObjectRepository.Tasks.taskGroupsRightMoveAllArrowRltWindow;
import static ObjectRepository.Tasks.taskGroupsRightSearchTxtBoxRltWindow;
import static ObjectRepository.Tasks.taskGroupsRltValueAfterMapping;
import static ObjectRepository.Tasks.taskGroupsTabRltSection;
import static ObjectRepository.Tasks.taskGroupsViewBtnRltWindow;
import static ObjectRepository.Threats.*;

public class Threats_SubModuleMapping extends Page{
	
	String threatName;
	
	LoginLogout ll = new LoginLogout();

	/*@Test(priority = 0)
	void login() throws BiffException, IOException, InterruptedException {
		ll.loginToSIB();
		driver.findElement(By.id("cometchat_hide")).click();
	}*/

	@Test(priority = 1)
	void navigateToThreatsModule() throws InterruptedException {
		click(threatsInMainMenu);
		thread();				
	}
	
	@Test(priority = 2)
	void verifySubModuleMappingLocations() throws IOException, InterruptedException {
		String locationsDefaultRowCountAfterMapping = "6";
		String flag;
		String[] leftRelationShipTableValue = new String[100];
		String[] rightRelationShipTableValue = new String[100];
		String[] tableValueAfterMapping = new String[100];
		String[] flagArray = new String[100];
		List<WebElement> threatList = driver
				.findElements(By.xpath("//table[@id='threat_table']//tbody[@role='alert']//tr"));
		int insuranceListSize = threatList.size();
		Random threat = new Random();
		int threatValue = threat.nextInt(insuranceListSize);
		if (threatValue == 0) {
			threatValue = threatValue + 1;
		}
		System.out.println("Threat value inside list view++++"+threatValue);
		driver.findElement(
				By.xpath("//table[@id='threat_table']//tbody[@role='alert']//tr" + "[" + threatValue + "]"))
		.click();
		// Clicking on a random insurance value
		threatName = driver
				.findElement(By.xpath(
						"//table[@id='threat_table']//tbody[@role='alert']//tr[" + threatValue + "]//td[2]"))
				.getText();
		thread();
		
		// Getting the name of the insurance
		// if(driver.findElement(By.xpath("//table[@id='rel_facilities']//td[@class='dataTables_empty']")).isDisplayed()){
		// When no records found in the relationship window
		click(addRemoveRelationsBtn);
		thread();
		click(rightMoveAllBtnRelationWindow);
		List<WebElement> leftValueListInsideRelationShipWindow = driver
				.findElements(By.xpath("//label[contains(text(),'List of Locations')]/..//button[@class='btn moveall']/../../select//option"));
		int leftValueListInsideRelationShipWindowSize = leftValueListInsideRelationShipWindow.size();
		Random locationName = new Random();
		int locationNameValue = locationName.nextInt(leftValueListInsideRelationShipWindowSize);
		if (locationNameValue == 0) {
			locationNameValue = locationNameValue + 1;
		}
		System.out.println("Location value inside relationship section++++"+locationNameValue);
		String leftLocationValue = driver.findElement(By.xpath("//label[contains(text(),'List of Locations')]/..//button[@class='btn moveall']/../../select//option"+"["+locationNameValue+"]")).getText();	
		driver.findElement(By.xpath("//label[contains(text(),'List of Locations')]/..//button[@class='btn moveall']/../../select//option"+"["+locationNameValue+"]")).click();
		enterText(leftSearchTextBoxLocationsRelationWindow, leftLocationValue);
		thread();
		click(locLeftMoveBtnRltWindow);
		// Getting a random location name from the list of locations available
		
		//click(leftMoveAllBtnRelationWindow);
		enterText(rightSearchTextBoxLocationsRelationWindow, leftLocationValue);
		thread();
		// verifying if the location mapped from the left window is present in
		// the right window
		click(submitButtonRelationShipWindow);
		waitForElement(msgNotificationBar);
		getObjectText(msgNotificationBar);
		takeScreenshot();
		verifyAssertEquals(threatName + " " + "successfully mapped to Locations", getActualObjectTxt);
		thread();
		scrollToBottom();
		String locationValueAfterMapping = driver.findElement(By.xpath("//table[@id='rel_facilities']//tbody[@role='alert']//span")).getText();
		if(leftLocationValue.contains(locationValueAfterMapping)){
			flag="true";
		}else
		{
			flag="false";
		}
		verifyAssertEquals(flag,"true");
		WebElement viewButtonLocations = driver.findElement(
				By.xpath("//table[@id='rel_facilities']//a[@class='btn btn-small btn-primary btn-nestable-action']"));
		viewButtonLocations.click();
		thread();
		scrollToBottom();
		List<WebElement> addRemoveButtonInnerRelationTableList = driver.findElements(locAddRemoveBtnInnerRltTable);
		click(locAddRemoveBtnInnerRltTable);
		scrollToBottom();
		thread();
		List<WebElement> locationsInnerRelationshipTableSearchTextBoxList = driver
				.findElements(locationsInnerRelationshipTableSearchTextBox);
		List<WebElement> locationsSubmitButtonInnerTableList = driver.findElements(locationsSubmitButtonInnerTable);
		List<WebElement> tabValuesInsideLocationsList = driver.findElements(tabValuesInsideLocations);
		int tabValuesInsideLocationsListSize = tabValuesInsideLocationsList.size();
		List<WebElement> leftInnerRelationshipTabs = driver.findElements(leftInnerRelationshipTable);
		int leftInnerRelationshipTableValuesSize = leftInnerRelationshipTabs.size();
		List<WebElement> leftMoveAllArrowList = driver.findElements(leftMoveAllBtnInnerRelationWindow);
		List<WebElement> rightMoveAllArrowList = driver.findElements(rightMoveAllBtnInnerRelationWindow);
		List<WebElement> leftMoveBtnInnerRltTableList = driver.findElements(leftMoveBtnInnerRltTable);
		
		click(rightMoveAllBtnInnerRelationWindow);
		thread();
		By locationsXpathList[] = {threatsSubTab,employeesSubTab,assetsSubTab,contactsSubTab,insuranceSubTab,businessFunctionsSubTab};
		for (int i = 0; i < tabValuesInsideLocationsListSize; i++) {
			if(i>0){
				tabValuesInsideLocationsList.get(i).click();
				addRemoveButtonInnerRelationTableList.get(i).click();
				rightMoveAllArrowList.get(i).click();
				thread();
			}
			int leftValueListSize = driver.findElements(locLeftRelationShipTableValue).size();
			System.out.println("i value inside locations loop++++"+i);
			System.out.println("Locations size inside loop++++"+leftValueListSize);
			Random random1 = new Random();
			int randomValue = random1.nextInt(leftValueListSize);
			System.out.println("Random value inside locations++++"+randomValue);
			leftRelationShipTableValue[i] = driver.findElement(locationsXpathList[i]).getText();
			driver.findElement(locationsXpathList[i]).click();
			locationsInnerRelationshipTableSearchTextBoxList.get(i+i).sendKeys(leftRelationShipTableValue[i]);
			thread();
			leftMoveBtnInnerRltTableList.get(i).click();
			locationsInnerRelationshipTableSearchTextBoxList.get(i+i+1).sendKeys(leftRelationShipTableValue[i]);
			thread();
			locationsSubmitButtonInnerTableList.get(i).click();
			thread();
			try{
				if(driver.findElement(By.xpath("//div[@class='modal-content']//div//h4[contains(text(),'Employees')]")).isDisplayed()){
					driver.findElement(otherLocationsEmployeesOkButton).click();
					thread();
					tableValueAfterMapping[i]=driver.findElement(locValueAfterInnerMapping).getText();
					if(leftRelationShipTableValue[i].contains(tableValueAfterMapping[i])){
						flagArray[i] = "true";
					}else
					{
						flagArray[i] = "false";
					}
					System.out.println(leftRelationShipTableValue[i]);
					System.out.println(tableValueAfterMapping[i]);
					System.out.println(flagArray[i]);
					verifyAssertEquals(flagArray[i],"true");
				}
			}catch(Exception E)
			{
				tableValueAfterMapping[i]=driver.findElement(locValueAfterInnerMapping).getText();
				if(leftRelationShipTableValue[i].contains(tableValueAfterMapping[i])){
					flagArray[i] = "true";
				}else
				{
					flagArray[i] = "false";
				}
				System.out.println(leftRelationShipTableValue[i]);
				System.out.println(tableValueAfterMapping[i]);
				System.out.println(flagArray[i]);
				verifyAssertEquals(flagArray[i],"true");
			}
			}
		viewButtonLocations.click();
		List<WebElement> rowsAfterMapping = driver.findElements(By.xpath("//table[@id='rel_facilities']//tbody[@role='alert']//tr[contains(@class,'tableRow')]"));
		int rowsAfterMappingSize = rowsAfterMapping.size();
		System.out.println("Locations count"+rowsAfterMappingSize);
		String locationsRowCountAfterMapping = Integer.toString(rowsAfterMappingSize);
		verifyAssertEquals(locationsDefaultRowCountAfterMapping,locationsRowCountAfterMapping);
	}
	
	  @Test(priority=3)
	   void verifySubModuleMappingTaskGroups() throws IOException, InterruptedException{
		   String taskGroupsDefaultCountAfterMapping = "1";
		   String flag, flag1;
		   click(taskGroupsTabRltSection);
		   click(addRemoveRelationsBtn);
		   thread();
		   click(taskGroupsRightMoveAllArrowRltWindow);
		   List <WebElement> leftTaskGroupsRltWindowValuesList = driver.findElements(taskGroupLeftRltWindowValues);
		   int leftTaskGroupsRltWindowValuesListSize = leftTaskGroupsRltWindowValuesList.size();
		   Random random = new Random();
		   int leftTaskGroupsRltWindowValue = random.nextInt(leftTaskGroupsRltWindowValuesListSize);
		   if(leftTaskGroupsRltWindowValue==0){
			   leftTaskGroupsRltWindowValue = leftTaskGroupsRltWindowValue + 1;
			}
		   System.out.println("Task group value inside relationship section++++"+leftTaskGroupsRltWindowValue);
		   String taskGroupName = driver.findElement(By.xpath("//label[contains(text(),'List of Task Groups')]/..//button[@class='btn moveall']/../../select//option"+"["+leftTaskGroupsRltWindowValue+"]")).getText();
		   driver.findElement(By.xpath("//label[contains(text(),'List of Task Groups')]/..//button[@class='btn moveall']/../../select//option"+"["+leftTaskGroupsRltWindowValue+"]")).click();
		   enterText(taskGroupLeftSearchTxtBoxRltWindow,taskGroupName);
		   thread();
		   click(taskGroupsLeftMoveBtnRltWindow);
		   enterText(taskGroupsRightSearchTxtBoxRltWindow,taskGroupName);
		   thread();
		   click(submitButtonRelationShipWindow);
		   waitForElement(msgNotificationBar);
		   getObjectText(msgNotificationBar);
		   takeScreenshot();
		   verifyAssertEquals(threatName+" "+"successfully mapped to Task Groups", getActualObjectTxt);
		   thread();
		   scrollToBottom();
		   String assetGroupValueAfterMapping = driver.findElement(By.xpath("//table[@id='rel_task_grp']//tbody[@role='alert']//tr//td[1]")).getText();
		   if(taskGroupName.contains(assetGroupValueAfterMapping)){
			   flag="true";
		   }else
		   {
			   flag="false";
		   }
		   verifyAssertEquals(flag,"true");
		   click(taskGroupsViewBtnRltWindow);
		   thread();
		   scrollToBottom();
		   click(taskGroupsAddRemoveBtnInnerRltTable);
		   thread();
		   click(taskGroupRightMoveAllArrowInnerRltTable);
		   thread();
		   int leftListValueSize=driver.findElements(taskGroupLeftInnerRltValues).size();
		   System.out.println("Task groups size"+leftListValueSize);
		   Random random1 = new Random();
		   int randomValue = random1.nextInt(leftListValueSize);
		   System.out.println("Random value inside task group++++"+randomValue);
		   String leftInnerTableValue = driver.findElement(taskSubTab).getText();
		   driver.findElement(taskSubTab).click();
		   driver.findElement(taskGroupInnerRltTableLeftSearchTxtBox).sendKeys(leftInnerTableValue);
		   thread();
		   click(taskGroupsLeftInnerRltMoveBtn);
		   driver.findElement(taskGroupInnerRltTableRightSearchTxtBox).sendKeys(leftInnerTableValue);
		   thread();
		   click(taskGroupsInnerRltSubmitBtn);
		   thread();
		   String tableValueAfterMapping = driver.findElement(taskGroupsRltValueAfterMapping).getText();
		   if(leftInnerTableValue.contains(tableValueAfterMapping)){
			   flag1="true";
		   }else
		   {
			   flag1="false";
		   }
		   System.out.println(leftInnerTableValue);
		   System.out.println(tableValueAfterMapping);
		   System.out.println(flag1);
		   verifyAssertEquals(flag1,"true");
		   click(taskGroupsViewBtnRltWindow);
		   List<WebElement> rowsAfterMapping = driver.findElements(By.xpath("//table[@id='rel_task_grp']//tbody[@role='alert']//tr[contains(@class,'tableRow')]"));
		   int rowsAfterMappingSize = rowsAfterMapping.size();
		   String taskGroupsCountAfterMapping = Integer.toString(rowsAfterMappingSize);
		   verifyAssertEquals(taskGroupsCountAfterMapping,taskGroupsDefaultCountAfterMapping);
	   }

}
