package Scripts;

/******************************************************************************************************************************
 * 
 * @author Rahul Nair
 * 
 * Test 1: Login to SIB application
 * Test 2: Navigate to Assets module
 * Test 3: Verify sub module mapping functionality in Locations sub tab
 * Test 4: Verify sub module mapping functionality in Asset Groups sub tab
 * Test 5: Verify sub module mapping functionality in Contacts sub tab
 * Test 6: Verify sub module mapping functionality in Insurance sub tab
 * Test 7: Verify sub module mapping functionality in Business Functions sub tab
 * Test 8: Verify sub module mapping functionality in Tasks sub tab
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
import static ObjectRepository.Tasks.assetGroupAddRemoveBtnInnerRltTable;
import static ObjectRepository.Tasks.assetGroupInnerRltSubmitBtn;
import static ObjectRepository.Tasks.assetGroupInnerRltTableSearchTxtBox;
import static ObjectRepository.Tasks.assetGroupInnerRltValues;
import static ObjectRepository.Tasks.assetGroupLeftInnerTabValues;
import static ObjectRepository.Tasks.assetGroupLeftMoveArrowInnerRltTable;
import static ObjectRepository.Tasks.assetGroupLeftMoveBtnRltWindow;
import static ObjectRepository.Tasks.assetGroupLeftRltWindowValues;
import static ObjectRepository.Tasks.assetGroupLeftSearchTxtBoxRltWindow;
import static ObjectRepository.Tasks.assetGroupRightInnerRltValues;
import static ObjectRepository.Tasks.assetGroupRightMoveAllArrowInnerRltTable;
import static ObjectRepository.Tasks.assetGroupRightMoveAllArrowRltWindow;
import static ObjectRepository.Tasks.assetGroupRightSearchTxtBoxRltWindow;
import static ObjectRepository.Tasks.assetGroupRltValueAfterMapping;
import static ObjectRepository.Tasks.assetGroupViewBtnRltWindow;
import static ObjectRepository.Tasks.assetGroupsTabRltSection;
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
import static ObjectRepository.Teams.teamsInMainMenu;
import static ObjectRepository.Assets.*;
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

public class Assets_SubModuleMapping extends Page{
	
	String assetName;
	LoginLogout ll = new LoginLogout();
	
	/*@Test(priority = 0)
	void login() throws BiffException, IOException, InterruptedException {
		ll.loginToSIB();
		driver.findElement(By.id("cometchat_hide")).click();
	}*/

	@Test(priority = 1)
	void navigateToAssetsModule() throws InterruptedException {
		click(assetsInMainMenu);
		thread();				
	}
	
	@Test(priority=2)
	void verifySubModuleMappingLocations() throws IOException, InterruptedException {
			String locationsDefaultRowCountAfterMapping = "6";
			String flag;
			String[] leftRelationShipTableValue = new String[100];
			String[] rightRelationShipTableValue = new String[100];
			String[] tableValueAfterMapping = new String[100];
			String[] flagArray = new String[100];
			List<WebElement> assetsList = driver
					.findElements(By.xpath("//table[@id='assets_table']//tbody[@role='alert']//tr"));
			int assetsListSize = assetsList.size();
			Random random = new Random();
			int assetValue = random.nextInt(assetsListSize);
			if (assetValue == 0) {
				assetValue = assetValue + 1;
			}
			System.out.println("Asset list view random value+++"+assetValue);
			driver.findElement(
					By.xpath("//table[@id='assets_table']//tbody[@role='alert']//tr" + "[" + assetValue + "]"))
			.click();
			// Clicking on a random insurance value
			assetName = driver
					.findElement(By.xpath(
							"//table[@id='assets_table']//tbody[@role='alert']//tr[" + assetValue + "]//td[2]"))
					.getText();
			thread();
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
			System.out.println("Location random value inside relationship section random value+++"+locationNameValue);
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
			verifyAssertEquals(assetName + " " + "successfully mapped to Locations", getActualObjectTxt);
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
			scrollToBottom();
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
				System.out.println("i value inside locations+++"+i);
				System.out.println("Locations size inside loop"+leftValueListSize);
				Random random1 = new Random();
				int randomValue = random1.nextInt(leftValueListSize);
				System.out.println("Random value inside locations loop+++"+randomValue);
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
	
	@Test(priority = 3)
  	void verifySubModuleMappingAssetGroups() throws IOException, InterruptedException{
	   String assetGroupsDefaultRowCountAfterMapping = "2";
	   String flag;
	   String[] rightRelationshipTableValue = new String[100];
	   String[] leftRelationshipTableValue = new String[100];
	   String[] tableValueAfterMappingArray = new String[100];
	   String[] flagArray = new String[100];
	   click(assetGroupsTabRltSection);
	   click(addRemoveRelationsBtn);
	   thread();
	   click(assetGroupRightMoveAllArrowRltWindow);
	   List<WebElement> assetGroupLeftRltWindowValuesList = driver.findElements(assetGroupLeftRltWindowValues);
	   int assetGroupLeftRltWindowValuesSize = assetGroupLeftRltWindowValuesList.size();
	   Random random = new Random();
	   int assetGroupValue = random.nextInt(assetGroupLeftRltWindowValuesSize);
	   if(assetGroupValue==0){
		   assetGroupValue = assetGroupValue+1;
	   }
	   System.out.println("Random asset group value inside relatioship section+++"+assetGroupValue);
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
	   verifyAssertEquals(assetName+" "+"successfully mapped to Asset Groups", getActualObjectTxt);
	   thread();
	   scrollToBottom();
	   String assetGroupNameAfterMapping = driver.findElement(By.xpath("//table[@id='rel_asset_grp']//tbody[@role='alert']//tr//td[1]")).getText();
	   if(randomAssetGroupName.contains(assetGroupNameAfterMapping)){
		   flag="true";
	   }else
	   {
		   flag="false";
	   }
	   verifyAssertEquals(flag,"true");
	   click(assetGroupViewBtnRltWindow);
	   thread();
	   scrollToBottom();
	   click(assetGroupAddRemoveBtnInnerRltTable);
	   scrollToBottom();
	   List<WebElement> addRemoveButtonList = driver.findElements(assetGroupAddRemoveBtnInnerRltTable);
	   List<WebElement> searchTextBoxInnerRelationTableList = driver.findElements(assetGroupInnerRltTableSearchTxtBox);
	   List<WebElement> leftTabsRelationshipTableList = driver.findElements(assetGroupLeftInnerTabValues);
	   int leftTabsRelationshipTableListSize = leftTabsRelationshipTableList.size();
	   //List<WebElement> leftRelationshipTableMoveAllArrowList = driver.findElements(empLeftRltTableMoveAllArrow);
	   List<WebElement> rightRelationshipTableMoveAllArrowList = driver.findElements(assetGroupRightMoveAllArrowInnerRltTable);
	   List<WebElement> submitButtonList = driver.findElements(assetGroupInnerRltSubmitBtn);
	   List<WebElement> valueAfterMappingList = driver.findElements(assetGroupRltValueAfterMapping);
	   List<WebElement> assetGroupLeftMoveBtnInnerRltTableList = driver.findElements(assetGroupLeftMoveArrowInnerRltTable);
	   click(assetGroupRightMoveAllArrowInnerRltTable);
	   thread();
	   By assetGroupsXpathList[] = {assetsSubTab,taskSubTab};
	   for(int i=0;i<leftTabsRelationshipTableListSize;i++){
			
			if(i>0){
				leftTabsRelationshipTableList.get(i).click();
				addRemoveButtonList.get(i).click();		
				rightRelationshipTableMoveAllArrowList.get(i).click();
				thread();
			}
			int leftValueListSize = driver.findElements(assetGroupInnerRltValues).size();
			System.out.println("i value inside asset group+++"+i);
			System.out.println("Asset group size inside loop"+leftValueListSize);
			Random random1 = new Random();
			int randomValue = random1.nextInt(leftValueListSize);
			System.out.println("Random value inside asset group loop+++"+randomValue);
			leftRelationshipTableValue[i] = driver.findElement(assetGroupInnerRltValues).getText();
			driver.findElement(assetGroupInnerRltValues).click();
			searchTextBoxInnerRelationTableList.get(i+i).sendKeys(leftRelationshipTableValue[i]);
			thread();
			assetGroupLeftMoveBtnInnerRltTableList.get(i).click();
			//leftRelationshipTableMoveAllArrowList.get(i).click();
			searchTextBoxInnerRelationTableList.get(i+i+1).sendKeys(leftRelationshipTableValue[i]);
			thread();
			//rightRelationshipTableValue[i] = driver.findElements(assetGroupRightInnerRltValues).get(i).getText();
			submitButtonList.get(i).click();
			thread();
			tableValueAfterMappingArray[i] = driver.findElements(assetGroupRltValueAfterMapping).get(i).getText();
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
	   click(assetGroupViewBtnRltWindow);
	   List<WebElement> rowsAfterMapping = driver.findElements(By.xpath("//table[@id='rel_asset_grp']//tbody[@role='alert']//tr[contains(@class,'tableRow')]"));
	   int rowsAfterMappingSize = rowsAfterMapping.size();
	   String assetGroupsRowCountAfterMapping = Integer.toString(rowsAfterMappingSize);
	   verifyAssertEquals(assetGroupsRowCountAfterMapping,assetGroupsDefaultRowCountAfterMapping);
   }
   
	@Test(priority=4)
    void verifySubModuleMappingContacts() throws IOException, InterruptedException{
    	String contactsDefaultRowCountAfterMapping = "3";
    	String flag;
    	String[] leftInnerTableValue = new String[100];
		String[] rightInnerTableValue = new String[100];
		String[] tableValueAfterMapping = new String[100];
		String[] flagArray = new String[100];
    	click(contactsTabTasksRltSection);
    	click(addRemoveRelationsBtn);
    	thread();
    	click(rightMoveAllArrowContactsRltWindow);
    	List<WebElement> contactsLeftRltWindowValuesList = driver.findElements(contactsLeftRltWindowValues);
    	int contactsLeftRltWindowValuesSize = contactsLeftRltWindowValuesList.size();
    	Random random = new Random();
    	int randomContactValue = random.nextInt(contactsLeftRltWindowValuesSize);
    	if(randomContactValue==0){
    		randomContactValue = randomContactValue+1;
    	}
    	System.out.println("Random value inside contacts relationship section+++"+randomContactValue);
    	String contactName = driver.findElement(By.xpath("//label[contains(text(),'List of Contacts')]/..//button[@class='btn moveall']/../../select//option"+"["+randomContactValue+"]")).getText();
    	driver.findElement(By.xpath("//label[contains(text(),'List of Contacts')]/..//button[@class='btn moveall']/../../select//option"+"["+randomContactValue+"]")).click();
    	enterText(contactsLeftSearchTxtBoxRltWindow,contactName);
    	thread();
    	click(contactsLeftMoveBtnRltWindow);
    	//click(contactsLeftMoveAllBtnRltWindow);
    	enterText(contactsRightSearchTxtBoxRltWindow,contactName);
    	thread();
    	click(submitButtonRelationShipWindow);
    	waitForElement(msgNotificationBar);
		getObjectText(msgNotificationBar);
		takeScreenshot();
		verifyAssertEquals(assetName+" "+"successfully mapped to Contacts", getActualObjectTxt);
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
		thread();
		By contactsXpathList[] = {contactGroupsSubTab,taskSubTab};
		for(int i=0;i<contactsTabValueInnerRltTableListSize;i++){
			if(i>0){
				contactsTabValueInnerRltTableList.get(i).click();
				contactsAddRemoveBtnInnerRltWindowList.get(i).click();
				contactsRightMoveAllArrowInnerRltTableList.get(i).click();
				thread();
			}
			int leftListValueSize=driver.findElements(contactsLeftInnerTableValues).size();
			System.out.println("i value inside contacts loop+++"+i);
			System.out.println("Contacts size inside loop"+leftListValueSize);
			Random random1 = new Random();
			int randomValue = random1.nextInt(leftListValueSize);
			System.out.println("Random value insde contacts loop+++"+randomValue);
			leftInnerTableValue[i] = driver.findElement(contactsLeftInnerTableValues).getText();
			driver.findElement(contactsLeftInnerTableValues).click();
			contactsSearchTxtBoxInnerRltTableList.get(i+i).sendKeys(leftInnerTableValue[i]);
			thread();
			contactsLeftMoveBtnInnerRltTableList.get(i).click();
			//contactsLeftMoveAllArrowInnerRltTableList.get(i).click();
			contactsSearchTxtBoxInnerRltTableList.get(i+i+1).sendKeys(leftInnerTableValue[i]);
			thread();
			//rightInnerTableValue[i] = driver.findElements(contactsRightInnerTableValues).get(i).getText();
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
		System.out.println("Random value inside insurance relationship section+++"+randomInsuranceValue);
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
		verifyAssertEquals(assetName+" "+"successfully mapped to Insurance", getActualObjectTxt);
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
			System.out.println("i value inside loop+++"+i);
			System.out.println("Insurance size inside loop+++"+leftValueListSize);
			Random random1 = new Random();
			int randomValue = random1.nextInt(leftValueListSize);
			System.out.println("Random value inside insurance loop+++"+randomValue);
			leftRelationshipTableValue[i] = driver.findElement(insuranceXpathList[i]).getText();
			driver.findElement(insuranceXpathList[i]).click();
			searchTextBoxInnerRelationTableList.get(i+i).sendKeys(leftRelationshipTableValue[i]);
			thread();
			empLeftMoveBtnInnerRltTableList.get(i).click();
			//leftRelationshipTableMoveAllArrowList.get(i).click();
			searchTextBoxInnerRelationTableList.get(i+i+1).sendKeys(leftRelationshipTableValue[i]);
			thread();
			//rightRelationshipTableValue[i] = driver.findElements(insuranceRightInnerRltTableValues).get(i).getText();
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
	
	@Test(priority=6)
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
		String businessFunctionName = driver.findElement(By.xpath("//label[contains(text(),'List of Business Functions')]/..//button[@class='btn moveall']/../../select//option"+"["+leftBusinessFntRltWindowValue+"]")).getText();
		driver.findElement(By.xpath("//label[contains(text(),'List of Business Functions')]/..//button[@class='btn moveall']/../../select//option"+"["+leftBusinessFntRltWindowValue+"]")).click();
		enterText(businessFntLeftSearchTextBoxRltWindow,businessFunctionName);
		thread();
		click(leftMoveBtnBusinessFntRltWindow);
		//click(leftMoveAllBtnBusinessFntRltWindow);
		enterText(businessFntRightSearchTextBoxRltWindow,businessFunctionName);
		thread();
		click(submitButtonRelationShipWindow);
		waitForElement(msgNotificationBar);
		getObjectText(msgNotificationBar);
		takeScreenshot();
		verifyAssertEquals(assetName+" "+"successfully mapped to Business Functions", getActualObjectTxt);
		thread();
		scrollToBottom();
		String businessFunctionValueAfterMapping = driver.findElement(By.xpath("//table[@id='rel_processes']//tbody[@role='alert']//span")).getText();
		if(businessFunctionName.contains(businessFunctionValueAfterMapping)){
			flag = "true";
		}else{
			flag="false";
		}
		verifyAssertEquals(flag,"true");
		click(businessFntViewBtnRltWindow);
		thread();
		scrollToBottom();
		click(businessFntAddRemoveBtnInnerRltWindow);
		thread();
		List<WebElement> businessFntAddRemoveBtnInnerRltWindowList = driver.findElements(businessFntAddRemoveBtnInnerRltWindow);
		List<WebElement> businessFntLeftMoveAllArrowInnerRltTableList = driver.findElements(businessFntLeftMoveAllArrowInnerRltTable);
		List<WebElement> businessFntRightMoveAllArrowInnerRltTableList = driver.findElements(businessFntRightMoveAllArrowInnerRltTable);
		List<WebElement> businessFntSearchTxtBoxInnerRltTableList = driver.findElements(businessFntSearchTxtBoxInnerRltTable);
		List<WebElement> businessFntLeftTabsInnerRltTableList = driver.findElements(businessFntLeftTabsInnerRltTable);
		int businessFntLeftTabsInnerRltTableListSize = businessFntLeftTabsInnerRltTableList.size();
		List<WebElement> businessFntSubmitBtnInnerRltTableList = driver.findElements(businessFntSubmitBtnInnerRltTable);
		List<WebElement> businessFntLeftMoveArrowInnerRltTableList = driver.findElements(businessFntLeftMoveArrowInnerRltTable);
		click(businessFntRightMoveAllArrowInnerRltTable);
		thread();
		By businessFunctionsXpathList[] = {locationSubTab,employeesSubTab,assetsSubTab,insuranceSubTab,taskSubTab};
		for(int i=0;i<businessFntLeftTabsInnerRltTableListSize;i++){			
			if(i>0){
				businessFntLeftTabsInnerRltTableList.get(i).click();
				businessFntAddRemoveBtnInnerRltWindowList.get(i).click();
				businessFntRightMoveAllArrowInnerRltTableList.get(i).click();
				thread();
			}
			int leftListValueSize=driver.findElements(businessFntLeftRltTableValues).size();
			System.out.println("i value inside business functions loop+++"+i);
			System.out.println("Business function size inside loop"+leftListValueSize);
			System.out.println(leftListValueSize);
			Random random1 = new Random();
			int randomValue = random1.nextInt(leftListValueSize);
			System.out.println("Random value inside business function loop+++"+randomValue);
			leftInnerTableValue[i] = driver.findElement(businessFunctionsXpathList[i]).getText();
			driver.findElement(businessFunctionsXpathList[i]).click();
			businessFntSearchTxtBoxInnerRltTableList.get(i+i).sendKeys(leftInnerTableValue[i]);
			thread();
			businessFntLeftMoveArrowInnerRltTableList.get(i).click();			
			//businessFntLeftMoveAllArrowInnerRltTableList.get(i).click();
			businessFntSearchTxtBoxInnerRltTableList.get(i+i+1).sendKeys(leftInnerTableValue[i]);
			thread();
			//rightInnerTableValue[i] = driver.findElements(businessFntRightRltTableValues).get(i).getText();
			businessFntSubmitBtnInnerRltTableList.get(i).click();
			thread();	
			tableValueAfterMapping[i] = driver.findElements(businessFntTableValueAfterMapping).get(i).getText();
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
		click(businessFntViewBtnRltWindow);
		List<WebElement> rowsAfterMapping = driver.findElements(By.xpath("//table[@id='rel_processes']//tbody[@role='alert']//tr[contains(@class,'tableRow')]"));
		int rowsAfterMappingSize = rowsAfterMapping.size();
		String businessFunctionsCountAfterMapping = Integer.toString(rowsAfterMappingSize);
		verifyAssertEquals(businessFunctionsDefaultCountAfterMapping,businessFunctionsCountAfterMapping);
	}
	
	@Test(priority = 7)
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
		verifyAssertEquals(assetName+ " "+"successfully mapped to Tasks", getActualObjectTxt);
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
			System.out.println("i value inside task loop++++"+i);
			System.out.println("Tasks size inside loop"+leftValueListSize);
			Random random3 = new Random();
			int randomValue = random3.nextInt(leftValueListSize);
			System.out.println("Random value inside task loop+++"+randomValue);
			leftRelationshipTableValue[i] = driver.findElement(tasksXpathList[i]).getText();
			driver.findElement(tasksXpathList[i]).click();
			searchTextBoxInnerRelationTableList.get(i+i).sendKeys(leftRelationshipTableValue[i]);
			thread();
			LeftMoveBtnInnerRltTableList.get(i).click();
			searchTextBoxInnerRelationTableList.get(i+i+1).sendKeys(leftRelationshipTableValue[i]);
			thread();
			//rightRelationshipTableValue[i] = driver.findElements(tasksRightRltValues).get(i).getText();
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
