package Scripts;

/******************************************************************************************************************************
 * 
 * @author Rahul Nair
 * 
 * Test 1: Login to SIB application
 * Test 2: Navigate to Employee module
 * Test 3: Verify sub module mapping functionality in Tasks sub tab
 * Test 4: Verify sub module mapping functionality in Business functions sub tab
 * Test 5: Verify sub module mapping functionality in Locations sub tab
 * Test 6: Verify sub module mapping functionality in Insurance Functions sub tab
 * Test 7: Verify sub module mapping functionality in Teams Functions sub tab
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
import static ObjectRepository.BusinessFunctions.insuranceAddRemoveBtnRltWindow;
import static ObjectRepository.BusinessFunctions.insuranceInnerTableValueAfterMapping;
import static ObjectRepository.BusinessFunctions.insuranceLeftInnerRltTableValues;
import static ObjectRepository.BusinessFunctions.insuranceLeftMoveArrowRltWindow;
import static ObjectRepository.BusinessFunctions.insuranceLeftRltValues;
import static ObjectRepository.BusinessFunctions.insuranceLeftSearchTxtBoxRltWindow;
import static ObjectRepository.BusinessFunctions.insuranceLeftTabsInnerRltTable;
import static ObjectRepository.BusinessFunctions.insuranceMoveBtnInnerRltWindow;
import static ObjectRepository.BusinessFunctions.insuranceRightInnerRltTableValues;
import static ObjectRepository.BusinessFunctions.insuranceRightMoveAllBtnInnerRltWindow;
import static ObjectRepository.BusinessFunctions.insuranceRightMoveAllBtnRltWindow;
import static ObjectRepository.BusinessFunctions.insuranceRightSearchTxtBoxRltWindow;
import static ObjectRepository.BusinessFunctions.insuranceSearchTxtBoxInnerRltTable;
import static ObjectRepository.BusinessFunctions.insuranceSubmitBtninnerRltTable;
import static ObjectRepository.BusinessFunctions.insuranceTabRltSection;
import static ObjectRepository.BusinessFunctions.insuranceViewBtnRltWindow;
import static ObjectRepository.Employees.*;
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
import static ObjectRepository.Tasks.rightMoveAllArrowTeamsRltWindow;
import static ObjectRepository.Tasks.teamsAddRemoveBtnInnerRltWindow;
import static ObjectRepository.Tasks.teamsLeftInnerRltTableValues;
import static ObjectRepository.Tasks.teamsLeftMoveAllArrowInnerRltTable;
import static ObjectRepository.Tasks.teamsLeftMoveBtnInnerRltTable;
import static ObjectRepository.Tasks.teamsLeftMoveBtnRltWindow;
import static ObjectRepository.Tasks.teamsLeftRltWindowValues;
import static ObjectRepository.Tasks.teamsLeftSearchTxtBoxRltWindow;
import static ObjectRepository.Tasks.teamsRightInnerRltTableValues;
import static ObjectRepository.Tasks.teamsRightMoveAllArrowInnerRltTable;
import static ObjectRepository.Tasks.teamsRightSearchTxtBoxRltWindow;
import static ObjectRepository.Tasks.teamsSearchTxtBoxinnerRltTable;
import static ObjectRepository.Tasks.teamsSubmitBtnInnerRltTable;
import static ObjectRepository.Tasks.teamsTabTasksRltSection;
import static ObjectRepository.Tasks.teamsTabValuesInnerRltWindow;
import static ObjectRepository.Tasks.teamsValueInnerRltTableAfterMapping;
import static ObjectRepository.Tasks.teamsViewBtnRltWindow;

public class Employees_SubModuleMapping extends Page {
	
	String employeeName;
	
	LoginLogout ll = new LoginLogout();

	@Test(priority = 0)
	void login() throws BiffException, IOException, InterruptedException {
		ll.loginToSIB();
		driver.findElement(By.id("cometchat_hide")).click();
	}

	@Test(priority = 1)
	void navigateToEmployeeModule() throws InterruptedException {
		click(employeesInMainMenu);
		thread();				
	}
	
	@Test(priority = 2)
	void verifySubModuleMappingTasks() throws IOException, InterruptedException{
		List<WebElement> employeeList = driver.findElements(By.xpath("//table[@id='resource_table']//tbody[@role='alert']//tr"));
		int employeeListSize = employeeList.size();
		Random random = new Random();
		int randomEmployeeValue = random.nextInt(employeeListSize);
		if(randomEmployeeValue==0){
			randomEmployeeValue = randomEmployeeValue + 1;
		}
		System.out.println("Emloyee value inside list view+++"+randomEmployeeValue);
		driver.findElement(By.xpath("//table[@id='resource_table']//tbody[@role='alert']//tr"+"["+randomEmployeeValue+"]")).click();
		String employeeFirstName = driver.findElement(By.xpath("//table[@id='resource_table']//tbody[@role='alert']//tr["+randomEmployeeValue+"]//td[2]")).getText();
		String employeeSecondName = driver.findElement(By.xpath("//table[@id='resource_table']//tbody[@role='alert']//tr["+randomEmployeeValue+"]//td[3]")).getText();
		employeeName = employeeFirstName + " "+ employeeSecondName;
		String tasksDefaultCountAfterMapping = "8";
		String flag;
		String[] rightRelationshipTableValue = new String[100];
		String[] leftRelationshipTableValue = new String[100];
		String[] tableValueAfterMappingArray = new String[100];
		String[] flagArray = new String[100];
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
		verifyAssertEquals(employeeName+" "+"successfully mapped to Tasks", getActualObjectTxt);
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
		By employeesXpathList[] = {employeesSubTab,teamsSubTab,assetsSubTab,assetGroupsSubTab,contactsSubTab,contactGroupsSubTab,taskGroupsSubTab,businessFunctionsSubTab};
		for(int i=0;i<leftTabsRelationshipTableListSize;i++){			
			if(i>0){
				leftTabsRelationshipTableList.get(i).click();
				addRemoveButtonList.get(i).click();		
				rightRelationshipTableMoveAllArrowList.get(i).click();
				thread();
			}
			int leftValueListSize = driver.findElements(tasksLeftInnerRltValues).size();
			System.out.println("i value inside tasks loop+++"+i);
			System.out.println("Tasks size inside loop+++"+leftValueListSize);
			Random random3 = new Random();
			int randomValue = random3.nextInt(leftValueListSize);
			System.out.println("Random value inside loop+++"+randomValue);
			leftRelationshipTableValue[i] = driver.findElement(employeesXpathList[i]).getText();
			driver.findElement(employeesXpathList[i]).click();
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
	
	@Test(priority=3)
	void verifySubModuleMappingBusinessFunctions() throws IOException, InterruptedException{
	    String businessFunctionsDefaultCountAfterMapping = "5";
		String flag;
		String[] leftInnerTableValue = new String[100];
		String[] rightInnerTableValue = new String[100];
		String[] tableValueAfterMapping = new String[100];
		String[] flagArray = new String[100];
		click(businessFntTabInsuranceRltSection);
		click(addRemoveRelationsBtn);
		thread();
		click(rightMoveAllBtnBusinessFtnRltWindow);
		List <WebElement> leftBusinessFntRltWindowValuesList = driver.findElements(leftBusinessFntRltWindowValues);
		int leftBusinessFntRltWindowValuesListSize = leftBusinessFntRltWindowValuesList.size();
		Random random = new Random();
		int leftBusinessFntRltWindowValue = random.nextInt(leftBusinessFntRltWindowValuesListSize);
		if(leftBusinessFntRltWindowValue==0){
			leftBusinessFntRltWindowValue = leftBusinessFntRltWindowValue + 1;
		}
		System.out.println("Business functions value inside relationship section+++"+leftBusinessFntRltWindowValue);
		String businessFunctionName = driver.findElement(By.xpath("//label[contains(text(),'List of Business Functions')]/..//button[@class='btn moveall']/../../select//option"+"["+leftBusinessFntRltWindowValue+"]")).getText();
		driver.findElement(By.xpath("//label[contains(text(),'List of Business Functions')]/..//button[@class='btn moveall']/../../select//option"+"["+leftBusinessFntRltWindowValue+"]")).click();
		enterText(businessFntLeftSearchTextBoxRltWindow,businessFunctionName);
		thread();
		click(leftMoveBtnBusinessFntRltWindow);
		enterText(businessFntRightSearchTextBoxRltWindow,businessFunctionName);
		thread();
		click(submitButtonRelationShipWindow);
		waitForElement(msgNotificationBar);
		getObjectText(msgNotificationBar);
		takeScreenshot();
		verifyAssertEquals(employeeName+" "+"successfully mapped to Business Functions", getActualObjectTxt);
		thread();
		scrollToBottom();
		String businessFunctionValueAfterMapping = driver.findElement(By.xpath("//table[@id='rel_process']//tbody[@role='alert']//span")).getText();
		if(businessFunctionName.contains(businessFunctionValueAfterMapping)){
			flag = "true";
		}else{
			flag="false";
		}
		verifyAssertEquals(flag,"true");
		click(empBusinessFntViewBtnEmpRltSection);
		thread();
		scrollToBottom();
		click(empBusinessFntAddRemoveBtnInnerRltWindow);
		thread();
		List<WebElement> businessFntAddRemoveBtnInnerRltWindowList = driver.findElements(empBusinessFntAddRemoveBtnInnerRltWindow);
		
		List<WebElement> businessFntRightMoveAllArrowInnerRltTableList = driver.findElements(empBusinessFntRightMoveAllArrowInnerRltTable);
		List<WebElement> businessFntSearchTxtBoxInnerRltTableList = driver.findElements(empBusinessFntSearchTxtBoxInnerRltTable);
		List<WebElement> businessFntLeftTabsInnerRltTableList = driver.findElements(empBusinessFntLeftTabsInnerRltTable);
		int businessFntLeftTabsInnerRltTableListSize = businessFntLeftTabsInnerRltTableList.size();
		List<WebElement> businessFntSubmitBtnInnerRltTableList = driver.findElements(empBusinessFntSubmitBtnInnerRltTable);
		List<WebElement> businessFntLeftMoveArrowInnerRltTableList = driver.findElements(empBusinessFntLeftMoveArrowInnerRltTable);
		click(empBusinessFntRightMoveAllArrowInnerRltTable);
		thread();
		By businessFunctionsXpathList[] = {locationSubTab,employeesSubTab,assetsSubTab,insuranceSubTab,taskSubTab};
		for(int i=0;i<businessFntLeftTabsInnerRltTableListSize;i++){			
			if(i>0){
				businessFntLeftTabsInnerRltTableList.get(i).click();
				businessFntAddRemoveBtnInnerRltWindowList.get(i).click();
				businessFntRightMoveAllArrowInnerRltTableList.get(i).click();
				thread();
			}
			int leftListValueSize=driver.findElements(empBusinessFntLeftRltTableValues).size();
			System.out.println("i value inside business function loop+++"+leftListValueSize);
			System.out.println("Business function size inside loop+++"+leftListValueSize);
			Random random1 = new Random();
			int randomValue = random1.nextInt(leftListValueSize);
			System.out.println("Random value inside business fuctions loop+++"+randomValue);
			leftInnerTableValue[i] = driver.findElement(businessFunctionsXpathList[i]).getText();
			driver.findElement(businessFunctionsXpathList[i]).click();
			businessFntSearchTxtBoxInnerRltTableList.get(i+i).sendKeys(leftInnerTableValue[i]);
			thread();
			businessFntLeftMoveArrowInnerRltTableList.get(i).click();
			businessFntSearchTxtBoxInnerRltTableList.get(i+i+1).sendKeys(leftInnerTableValue[i]);
			thread();
			
			businessFntSubmitBtnInnerRltTableList.get(i).click();
			thread();	
			tableValueAfterMapping[i] = driver.findElements(empBusinessFntTableValueAfterMapping).get(i).getText();
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
		click(empBusinessFntViewBtnEmpRltSection);
		List<WebElement> rowsAfterMapping = driver.findElements(By.xpath("//table[@id='rel_process']//tbody[@role='alert']//tr[contains(@class,'tableRow')]"));
		int rowsAfterMappingSize = rowsAfterMapping.size();
		String businessFunctionsCountAfterMapping = Integer.toString(rowsAfterMappingSize);
		verifyAssertEquals(businessFunctionsDefaultCountAfterMapping,businessFunctionsCountAfterMapping);
	}
	
	@Test(priority = 4)
	void verifySubModuleMappingLocations() throws IOException, InterruptedException {
		String locationsDefaultRowCountAfterMapping = "6";
		String flag;
		String[] leftRelationShipTableValue = new String[100];
		String[] rightRelationShipTableValue = new String[100];
		String[] tableValueAfterMapping = new String[100];
		String[] flagArray = new String[100];
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
		String leftLocationValue = driver.findElement(By.xpath("//label[contains(text(),'List of Locations')]/..//button[@class='btn moveall']/../../select//option"+"["+locationNameValue+"]")).getText();	
		driver.findElement(By.xpath("//label[contains(text(),'List of Locations')]/..//button[@class='btn moveall']/../../select//option"+"["+locationNameValue+"]")).click();
		enterText(leftSearchTextBoxLocationsRelationWindow, leftLocationValue);
		click(locLeftMoveBtnRltWindow);
		// Getting a random location name from the list of locations available
		
		//click(leftMoveAllBtnRelationWindow);
		enterText(rightSearchTextBoxLocationsRelationWindow, leftLocationValue);
		String rightLocationValue=driver.findElement(rightLocValueRltWindow).getText();
		verifyAssertEquals(leftLocationValue, rightLocationValue);
		// verifying if the location mapped from the left window is present in
		// the right window
		click(submitButtonRelationShipWindow);
		waitForElement(msgNotificationBar);
		getObjectText(msgNotificationBar);
		takeScreenshot();
		verifyAssertEquals(employeeName+ " " + "successfully mapped to Locations", getActualObjectTxt);
		thread();
		scrollToBottom();
		String locationValueAfterMapping = driver.findElement(By.xpath("//table[@id='rel_facilities']//tbody[@role='alert']//span")).getText();
		if(rightLocationValue.contains(locationValueAfterMapping)){
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
		scrollToBottom();
		
		for (int i = 0; i < tabValuesInsideLocationsListSize; i++) {
			int j=1;
			if(i>0){
				tabValuesInsideLocationsList.get(i).click();
				addRemoveButtonInnerRelationTableList.get(i).click();
				rightMoveAllArrowList.get(i).click();
				thread();
			}
			int leftValueListSize = driver.findElements(locLeftRelationShipTableValue).size();
			Random random1 = new Random();
			int randomValue = random1.nextInt(leftValueListSize);
			leftRelationShipTableValue[i] = driver.findElements(locLeftRelationShipTableValue).get(randomValue).getText();
			driver.findElements(locLeftRelationShipTableValue).get(randomValue).click();
			locationsInnerRelationshipTableSearchTextBoxList.get(i+i).sendKeys(leftRelationShipTableValue[i]);
			leftMoveBtnInnerRltTableList.get(i).click();
			//leftMoveAllArrowList.get(i).click();
			locationsInnerRelationshipTableSearchTextBoxList.get(i+i+1).sendKeys(leftRelationShipTableValue[i]);
			rightRelationShipTableValue[i]=driver.findElements(locRightRelationShipTableValue).get(i).getText();
			locationsSubmitButtonInnerTableList.get(i).click();
			thread();
			try{
				if(driver.findElement(By.xpath("//div[@class='modal-content']//div//h4[contains(text(),'Employees')]")).isDisplayed()){
					driver.findElement(otherLocationsEmployeesOkButton).click();
					thread();
					tableValueAfterMapping[i]=driver.findElement(locValueAfterInnerMapping).getText();
					if(rightRelationShipTableValue[i].contains(tableValueAfterMapping[i])){
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
				if(rightRelationShipTableValue[i].contains(tableValueAfterMapping[i])){
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
	
	@Test(priority = 5)
	void verifySubModuleMappingInsurance() throws IOException, InterruptedException{
		String insuranceDefaultRowCountAfterMapping = "4";
		String flag;
		String[] rightRelationshipTableValue = new String[100];
		String[] leftRelationshipTableValue = new String[100];
		String[] tableValueAfterMappingArray = new String[100];
		String[] flagArray = new String[100];
		click(insuranceTabRltSection);
		click(addRemoveRelationsBtn);
		thread();
		click(insuranceRightMoveAllBtnRltWindow);
		List<WebElement> insuranceLeftRltWindowValueList = driver.findElements(insuranceLeftRltValues);
		int insuranceLeftRltWindowValueListSize = insuranceLeftRltWindowValueList.size();
		Random random = new Random();
		int randomInsuranceValue = random.nextInt(insuranceLeftRltWindowValueListSize);
		if(randomInsuranceValue==0){
			randomInsuranceValue=randomInsuranceValue+1;
		}
		System.out.println("Insurance value inside relationship section+++"+randomInsuranceValue);
		String insuranceName = driver.findElement(By.xpath("//label[contains(text(),'List of Insurance')]/..//button[@class='btn moveall']/../../select//option"+"["+randomInsuranceValue+"]")).getText();
		driver.findElement(By.xpath("//label[contains(text(),'List of Insurance')]/..//button[@class='btn moveall']/../../select//option"+"["+randomInsuranceValue+"]")).click();
		enterText(insuranceLeftSearchTxtBoxRltWindow,insuranceName);
		thread();
		click(insuranceLeftMoveArrowRltWindow);
		enterText(insuranceRightSearchTxtBoxRltWindow,insuranceName);
		thread();
		click(submitButtonRelationShipWindow);
		waitForElement(msgNotificationBar);
		getObjectText(msgNotificationBar);
		takeScreenshot();
		verifyAssertEquals(employeeName+" "+"successfully mapped to Insurance", getActualObjectTxt);
		thread();
		scrollToBottom();
		String insuranceNameAfterMapping = driver.findElement(By.xpath("//table[@id='rel_insurances']//tbody[@role='alert']//span")).getText();
		if(insuranceName.contains(insuranceNameAfterMapping)){
			flag="true";
		}else
		{
			flag="false";
		}
		verifyAssertEquals(flag,"true");
		click(insuranceViewBtnRltWindow);
		thread();
		scrollToBottom();
		click(insuranceAddRemoveBtnRltWindow);
		thread();
		List<WebElement> addRemoveButtonList = driver.findElements(insuranceAddRemoveBtnRltWindow);
		List<WebElement> searchTextBoxInnerRelationTableList = driver.findElements(insuranceSearchTxtBoxInnerRltTable);
		List<WebElement> leftTabsRelationshipTableList = driver.findElements(insuranceLeftTabsInnerRltTable);
		int leftTabsRelationshipTableListSize = leftTabsRelationshipTableList.size();
		List<WebElement> rightRelationshipTableMoveAllArrowList = driver.findElements(insuranceRightMoveAllBtnInnerRltWindow);
		List<WebElement> submitButtonList = driver.findElements(insuranceSubmitBtninnerRltTable);
		List<WebElement> valueAfterMappingList = driver.findElements(insuranceInnerTableValueAfterMapping);
		List<WebElement> empLeftMoveBtnInnerRltTableList = driver.findElements(insuranceMoveBtnInnerRltWindow);
		click(insuranceRightMoveAllBtnInnerRltWindow);
		scrollToBottom();
		thread();
		By insuranceXpathList[] = {locationSubTab,employeesSubTab,assetsSubTab,businessFunctionsSubTab};
        for(int i=0;i<leftTabsRelationshipTableListSize;i++){
			
			if(i>0){
				leftTabsRelationshipTableList.get(i).click();
				addRemoveButtonList.get(i).click();		
				rightRelationshipTableMoveAllArrowList.get(i).click();
				thread();
			}
			int leftValueListSize = driver.findElements(insuranceLeftInnerRltTableValues).size();
			System.out.println("i value inside insurance loop+++"+i);
			System.out.println("Insurance size inside loop+++"+leftValueListSize);
			Random random1 = new Random();
			int randomValue = random1.nextInt(leftValueListSize);
			System.out.println("Random value inside insurance loop+++"+randomValue);
			leftRelationshipTableValue[i] = driver.findElement(insuranceXpathList[i]).getText();
			driver.findElement(insuranceXpathList[i]).click();
			searchTextBoxInnerRelationTableList.get(i+i).sendKeys(leftRelationshipTableValue[i]);
			thread();
			empLeftMoveBtnInnerRltTableList.get(i).click();
			searchTextBoxInnerRelationTableList.get(i+i+1).sendKeys(leftRelationshipTableValue[i]);
			thread();
			
			submitButtonList.get(i).click();
			thread();
			tableValueAfterMappingArray[i] = driver.findElements(insuranceInnerTableValueAfterMapping).get(i).getText();
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
        click(insuranceViewBtnRltWindow);
        List<WebElement> rowsAfterMapping = driver.findElements(By.xpath("//table[@id='rel_insurances']//tbody[@role='alert']//tr[contains(@class,'tableRow')]"));
        int rowsAfterMappingSize = rowsAfterMapping.size();
        String insuranceRowCountAfterMapping = Integer.toString(rowsAfterMappingSize);
        verifyAssertEquals(insuranceRowCountAfterMapping,insuranceDefaultRowCountAfterMapping);
	}
	
	@Test(priority = 6)
    void verifySubModuleMappingTeams() throws IOException, InterruptedException{
    	String teamsDefaultRowCountAfterMapping = "3";
    	String flag;
    	String[] flagArray = new String[100];
    	String[] leftRltTableValueList = new String[100];
		String[] rightRltTableValueList =new String[100];
		String[] tableValueAfterMappingArray = new String[100];
    	click(teamsTabTasksRltSection);
    	click(addRemoveRelationsBtn);
    	thread();
		click(rightMoveAllArrowTeamsRltWindow);
		List<WebElement> teamsLeftRltWindowValuesList = driver.findElements(teamsLeftRltWindowValues);
		int teamsLeftRltWindowValuesListSize = teamsLeftRltWindowValuesList.size();
		Random random = new Random();
		int randomTeamValue = random.nextInt(teamsLeftRltWindowValuesListSize);
		if(randomTeamValue==0){
			randomTeamValue = randomTeamValue+1;
		}
		System.out.println("Teams value inside relationship section+++"+randomTeamValue);
		String teamName = driver.findElement(By.xpath("//label[contains(text(),'List of Teams')]/..//button[@class='btn moveall']/../../select//option"+"["+randomTeamValue+"]")).getText();
		driver.findElement(By.xpath("//label[contains(text(),'List of Teams')]/..//button[@class='btn moveall']/../../select//option"+"["+randomTeamValue+"]")).click();
		enterText(teamsLeftSearchTxtBoxRltWindow,teamName);
		thread();
		click(teamsLeftMoveBtnRltWindow);
		enterText(teamsRightSearchTxtBoxRltWindow,teamName);
		thread();
		click(submitButtonRelationShipWindow);
		waitForElement(msgNotificationBar);
		getObjectText(msgNotificationBar);
		takeScreenshot();
		verifyAssertEquals(employeeName+" "+"successfully mapped to Teams", getActualObjectTxt);
		thread();
		scrollToBottom();
		click(empTeamsViewBtnRltWindow);
		thread();
		scrollToBottom();
		String teamValueAfterMapping = driver.findElement(By.xpath("//table[@id='rel_resourcegroup']//tbody[@role='alert']//span")).getText();
		System.out.println("Team value after mapping +++++"+teamValueAfterMapping);
		if(teamName.contains(teamValueAfterMapping)){
			flag="true";
		}else
		{
			flag="false";
		}
		verifyAssertEquals(flag,"true");
		click(empTeamsAddRemoveBtnInnerRltWindow);
		thread();
		scrollToBottom();
		List<WebElement> addRemoveBtnList = driver.findElements(empTeamsAddRemoveBtnInnerRltWindow);
		List<WebElement> teamsTabValuesInnerRltWindowList = driver.findElements(empTeamsTabValuesInnerRltWindow);
		int teamsTabValuesInnerRltWindowListSize = teamsTabValuesInnerRltWindowList.size();
		List<WebElement> teamsSearchTxtBoxinnerRltTableList = driver.findElements(empTeamsSearchTxtBoxinnerRltTable);
		List<WebElement> teamsSubmitBtnInnerRltTableList = driver.findElements(empTeamsSubmitBtnInnerRltTable);
		List<WebElement> teamsRightMoveAllArrowInnerRltTableList = driver.findElements(empTeamsRightMoveAllArrowInnerRltTable);
		List<WebElement> teamsLeftMoveBtnInnerRltTableLst = driver.findElements(empTeamsLeftMoveBtnInnerRltTable);
		click(empTeamsRightMoveAllArrowInnerRltTable);
		thread();
		By teamsXpathList[] = {locationSubTab,employeesSubTab,taskSubTab};
		for(int i=0;i<teamsTabValuesInnerRltWindowListSize;i++){
			if(i>0){
				teamsTabValuesInnerRltWindowList.get(i).click();
				addRemoveBtnList.get(i).click();
				teamsRightMoveAllArrowInnerRltTableList.get(i).click();
				thread();
				
			}
			int leftListValueSize = driver.findElements(empTeamsLeftInnerRltTableValues).size();
			System.out.println("i value inside teams loop+++"+i);
			System.out.println("Teams size inside loop+++"+leftListValueSize);
			Random random1 = new Random();
			int randomValue = random1.nextInt(leftListValueSize);
			System.out.println("Random value inside teams loop+++"+randomValue);
			leftRltTableValueList[i] = driver.findElement(empTeamsLeftInnerRltTableValues).getText();
			driver.findElement(empTeamsLeftInnerRltTableValues).click();
			teamsSearchTxtBoxinnerRltTableList.get(i+i).sendKeys(leftRltTableValueList[i]);
			thread();
			teamsLeftMoveBtnInnerRltTableLst.get(i).click();
			teamsSearchTxtBoxinnerRltTableList.get(i+i+1).sendKeys(leftRltTableValueList[i]);
			thread();
			
			teamsSubmitBtnInnerRltTableList.get(i).click();
			thread();
			
			tableValueAfterMappingArray[i] = driver.findElements(empTeamsValueInnerRltTableAfterMapping).get(i).getText();
			if(leftRltTableValueList[i].contains(tableValueAfterMappingArray[i])){
				flagArray[i] = "true";
			}else
			{
				flagArray[i] = "false";
			}
			System.out.println(rightRltTableValueList[i]);
			System.out.println(tableValueAfterMappingArray[i]);
			System.out.println(flagArray[i]);
			verifyAssertEquals(flagArray[i],"true");
		}
		click(empTeamsViewBtnRltWindow);
		List<WebElement> rowsAfterMapping = driver.findElements(By.xpath("//table[@id='rel_resourcegroup']//tbody[@role='alert']//tr[contains(@class,'tableRow')]"));
		int rowsAfterMappingSize = rowsAfterMapping.size();
		String teamsRowCountAfterMapping = Integer.toString(rowsAfterMappingSize);
		verifyAssertEquals(teamsRowCountAfterMapping,teamsDefaultRowCountAfterMapping);
    }

}
