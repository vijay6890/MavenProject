package Scripts;

/******************************************************************************************************************************
 * 
 * @author Rahul Nair
 * 
 * Test 1: Login to SIB application
 * Test 2: Navigate to Teams module
 * Test 3: Verify sub module mapping functionality in Employees sub tab
 * Test 4: Verify sub module mapping functionality in Tasks sub tab
 * Test 5: Verify sub module mapping functionality in Locations sub tab
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
import static ObjectRepository.Locations.*;
import static ObjectRepository.TaskGroups.*;
import static ObjectRepository.Teams.*;

public class Teams_SubModuleMapping extends Page{
	
	String teamName;
	LoginLogout ll = new LoginLogout();
	
	/*@Test(priority = 0)
	void login() throws BiffException, IOException, InterruptedException {
		ll.loginToSIB();
		driver.findElement(By.id("cometchat_hide")).click();
	}*/

	@Test(priority = 1)
	void navigateToTeamsModule() throws InterruptedException {
		click(teamsInMainMenu);
		thread();
				
	}
	
	@Test(priority=2)
	void verifySubModuleMappingEmployees() throws InterruptedException, IOException{
		String employeesDefaultRowCountAfterMapping = "4";
		String flag;
		String[] rightRelationshipTableValue = new String[100];
		String[] leftRelationshipTableValue = new String[100];
		String[] tableValueAfterMappingArray = new String[100];
		String[] flagArray = new String[100];
		List<WebElement> teamsList = driver.findElements(By.xpath("//table[@id='resourcegroup_table']//tbody[@role='alert']//tr"));
		int teamsListSize = teamsList.size();
		Random random = new Random();
		int teamValue = random.nextInt(teamsListSize);
		if(teamValue==0){
			teamValue = teamValue+1;
		}
		System.out.println("Team value inside list view++++"+teamValue);
		driver.findElement(By.xpath("//table[@id='resourcegroup_table']//tbody[@role='alert']//tr"+"["+teamValue+"]")).click();
		teamName = driver.findElement(By.xpath("//table[@id='resourcegroup_table']//tbody[@role='alert']//tr["+teamValue+"]")).getAttribute("data-for");
		thread();
		thread();
		click(addRemoveRelationsBtn);
		thread();
		click(rightMoveAllBtnEmpRltWindow);
		List<WebElement> leftEmployeeRelationWindowValueList = driver.findElements(leftEmployeeRelationWindowValues);
		int leftEmployeeRelationWindowValueListSize = leftEmployeeRelationWindowValueList.size();
		Random random1 = new Random();
		int employeeValue = random1.nextInt(leftEmployeeRelationWindowValueListSize);
		if(employeeValue==0){
			employeeValue = employeeValue+ 1;
		}
		System.out.println("Employee value inside relationship section++++"+employeeValue);
		String employeeName = driver.findElement(By.xpath("//label[contains(text(),'List of Employees')]/..//button[@class='btn moveall']/../../select//option"+"["+employeeValue+"]")).getText();
		driver.findElement(By.xpath("//label[contains(text(),'List of Employees')]/..//button[@class='btn moveall']/../../select//option"+"["+employeeValue+"]")).click();
	    enterText(empLeftSearchTxtBoxRltWindow,employeeName);
	    thread();
	    click(leftMoveBtnEmpRltWindow);
	    enterText(empRightSearchTxtBoxRltWindow,employeeName);
	    thread();
	    click(submitButtonRelationShipWindow);
	    waitForElement(msgNotificationBar);
		getObjectText(msgNotificationBar);
		takeScreenshot();
		verifyAssertEquals(teamName+" "+"successfully mapped to Employees", getActualObjectTxt);
		thread();
		scrollToBottom();
		String employeeValueAfterMapping = driver.findElement(By.xpath("//table[@id='rel_resources']//tbody[@role='alert']//span")).getText();
		if(employeeName.contains(employeeValueAfterMapping)){
			 flag = "true";
		}
		else{
			 flag = "false";
		}
		verifyAssertEquals(flag,"true");
		click(empViewBtnRltWindow);
		thread();
		scrollToBottom();
		click(empAddRemoveRltBtnInnerRltTable);
		thread();
		List<WebElement> addRemoveButtonList = driver.findElements(empAddRemoveRltBtnInnerRltTable);
		List<WebElement> searchTextBoxInnerRelationTableList = driver.findElements(empSearchTxtBoxInnerRltTable);
		List<WebElement> leftTabsRelationshipTableList = driver.findElements(empLeftTabsRelationshipTable);
		int leftTabsRelationshipTableListSize = leftTabsRelationshipTableList.size();
		List<WebElement> leftRelationshipTableMoveAllArrowList = driver.findElements(empLeftRltTableMoveAllArrow);
		List<WebElement> rightRelationshipTableMoveAllArrowList = driver.findElements(empRightRltTableMoveAllArrow);
		List<WebElement> submitButtonList = driver.findElements(empSubmitBtnInnerRltTable);
		List<WebElement> valueAfterMappingList = driver.findElements(tableValueAfterMapping);
		List<WebElement> empLeftMoveBtnInnerRltTableList = driver.findElements(empLeftMoveBtnInnerRltTable);
		click(empRightRltTableMoveAllArrow);
		thread();
		By employeesXpathList[] = {teamsSubTab,insuranceSubTab,taskSubTab,businessFunctionsSubTab};
		for(int i=0;i<leftTabsRelationshipTableListSize;i++){
			
			if(i>0){
				leftTabsRelationshipTableList.get(i).click();
				addRemoveButtonList.get(i).click();		
				rightRelationshipTableMoveAllArrowList.get(i).click();
				thread();
			}
			int leftValueListSize = driver.findElements(empLeftInnerRltTableValues).size();
			System.out.println("i value inside employee loop++++"+i);
			System.out.println("Employee value inside relationship section++++"+leftValueListSize);
			Random random2 = new Random();
			int randomValue = random2.nextInt(leftValueListSize);
			System.out.println("Random employee value inside loop++++"+randomValue);
			leftRelationshipTableValue[i] = driver.findElement(employeesXpathList[i]).getText();
			driver.findElement(employeesXpathList[i]).click();
			searchTextBoxInnerRelationTableList.get(i+i).sendKeys(leftRelationshipTableValue[i]);
			thread();
			empLeftMoveBtnInnerRltTableList.get(i).click();
			searchTextBoxInnerRelationTableList.get(i+i+1).sendKeys(leftRelationshipTableValue[i]);
			thread();
			submitButtonList.get(i).click();
			thread();
			tableValueAfterMappingArray[i] = driver.findElements(tableValueAfterMapping).get(i).getText();
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
		
		click(empViewBtnRltWindow);
		List <WebElement> rowsAfterMapping = driver.findElements(By.xpath("//table[@id='rel_resources']//tbody[@role='alert']//tr[contains(@class,'tableRow')]"));
		int rowsAfterMappingSize = rowsAfterMapping.size();
		System.out.println("Employee count"+rowsAfterMappingSize);
		String employeesRowCountAfterMapping = Integer.toString(rowsAfterMappingSize);
		verifyAssertEquals(employeesDefaultRowCountAfterMapping,employeesRowCountAfterMapping);
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
		thread();
		click(addRemoveRelationsBtn);
		click(taskRightMoveAllArrowRltWindow);
		List<WebElement> tasksLeftRltWindowValuesList = driver.findElements(tasksLeftRltWindowValues);
		int tasksLeftRltWindowValuesListSize = tasksLeftRltWindowValuesList.size();
		Random random1 = new Random();
		int randomTaskValue = random1.nextInt(tasksLeftRltWindowValuesListSize);
		if(randomTaskValue==0){
			randomTaskValue = randomTaskValue+1;
		}
		System.out.println("Tasks value inside relationship section++++"+randomTaskValue);
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
		verifyAssertEquals(teamName+ " "+"successfully mapped to Tasks", getActualObjectTxt);
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
		By tasksXpathList[] = {employeesSubTab,teamsSubTab,assetsSubTab,assetGroupsSubTab,contactsSubTab,contactGroupsSubTab,taskGroupsSubTab,businessFunctionsSubTab};
		for(int i=0;i<leftTabsRelationshipTableListSize;i++){
			
			if(i>0){
				leftTabsRelationshipTableList.get(i).click();
				addRemoveButtonList.get(i).click();		
				rightRelationshipTableMoveAllArrowList.get(i).click();
				thread();
			}
			int leftValueListSize = driver.findElements(tasksLeftInnerRltValues).size();
			System.out.println("i value inside tasks loop++++"+i);
			System.out.println("Tasks size in loop++++"+leftValueListSize);
			Random random3 = new Random();
			int randomValue = random3.nextInt(leftValueListSize);
			System.out.println("Random task value inside loop++++"+randomValue);
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
	
	
	@Test(priority = 4)
	void verifySubModuleMappingLocations() throws IOException, InterruptedException {
		String locationsDefaultRowCountAfterMapping = "5";
		String flag;
		String[] leftRelationShipTableValue = new String[100];
		String[] rightRelationShipTableValue = new String[100];
		String[] tableValueAfterMapping = new String[100];
		String[] flagArray = new String[100];
		click(locationsTabRltSection);
		thread();
		click(addRemoveRelationsBtn);
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
		enterText(rightSearchTextBoxLocationsRelationWindow, leftLocationValue);
		thread();
		click(submitButtonRelationShipWindow);
		waitForElement(msgNotificationBar);
		getObjectText(msgNotificationBar);
		takeScreenshot();
		verifyAssertEquals(teamName + " " + "successfully mapped to Locations", getActualObjectTxt);
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
			int j=1;
			if(i>0){
				tabValuesInsideLocationsList.get(i).click();
				addRemoveButtonInnerRelationTableList.get(i).click();
				rightMoveAllArrowList.get(i).click();
				thread();
			}
			int leftValueListSize = driver.findElements(locLeftRelationShipTableValue).size();
			System.out.println("i value inside location loop++++"+i);
			System.out.println("Location size inside loop++++"+leftValueListSize);
			Random random1 = new Random();
			int randomValue = random1.nextInt(leftValueListSize);
			System.out.println("Random value inside locations loop++++"+randomValue);
			leftRelationShipTableValue[i] = driver.findElement(locationsXpathList[i]).getText();
			driver.findElement(locationsXpathList[i]).click();
			locationsInnerRelationshipTableSearchTextBoxList.get(i+i).sendKeys(leftRelationShipTableValue[i]);
			thread();
			leftMoveBtnInnerRltTableList.get(i).click();
			locationsInnerRelationshipTableSearchTextBoxList.get(i+i+1).sendKeys(leftRelationShipTableValue[i]);
			thread();
			rightRelationShipTableValue[i]=driver.findElements(locRightRelationShipTableValue).get(i).getText();
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
	

}
