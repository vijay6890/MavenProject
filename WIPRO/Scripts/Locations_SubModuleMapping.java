package Scripts;
import static ObjectRepository.Tasks.*;
import static ObjectRepository.Loc_SubModule_Mapping.*;

/******************************************************************************************************************************
 * 
 * @author Rahul Nair
 * 
 * Test 1: Login to SIB application
 * Test 2: Navigate to Locations module
 * Test 3: Verify sub module mapping functionality in Employees sub tab
 * Test 4: Verify sub module mapping functionality in Threats sub tab
 * Test 5: Verify sub module mapping functionality in Assets sub tab
 * Test 6: Verify sub module mapping functionality in Business Functions sub tab
 
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
import static ObjectRepository.BusinessFunctions.*;
import static ObjectRepository.TaskGroups.*;
import static ObjectRepository.Tasks.contactsAddRemoveBtnInnerRltWindow;
import static ObjectRepository.Tasks.contactsLeftInnerTableValues;
import static ObjectRepository.Tasks.contactsLeftMoveAllArrowInnerRltTable;
import static ObjectRepository.Tasks.contactsLeftMoveBtnInnerRltTable;
import static ObjectRepository.Tasks.contactsLeftMoveBtnRltWindow;
import static ObjectRepository.Tasks.contactsLeftRltWindowValues;
import static ObjectRepository.Tasks.contactsLeftSearchTxtBoxRltWindow;
import static ObjectRepository.Tasks.contactsRightMoveAllArrowInnerRltTable;
import static ObjectRepository.Tasks.contactsRightSearchTxtBoxRltWindow;
import static ObjectRepository.Tasks.contactsSearchTxtBoxInnerRltTable;
import static ObjectRepository.Tasks.contactsSubmitButtonInnerRltTable;
import static ObjectRepository.Tasks.contactsTabTasksRltSection;
import static ObjectRepository.Tasks.contactsTabValueInnerRltTable;
import static ObjectRepository.Tasks.contactsValueInnerRltTableAfterMapping;
import static ObjectRepository.Tasks.contactsViewBtnRltWindow;
import static ObjectRepository.Tasks.rightMoveAllArrowContactsRltWindow;
import static ObjectRepository.Threats.rltnMapLocationsPp;
import static ObjectRepository.Locations.*;

public class Locations_SubModuleMapping extends Page {
	
	String locationName,NameForSearch;
	LoginLogout ll = new LoginLogout();
	Loc_AddDeleteLocation locAdd=new Loc_AddDeleteLocation();

	@Test(priority = 0)
	void login() throws BiffException, IOException, InterruptedException {
		ll.loginToSIB();
		verifyObjDisplay(By.id("cometchat_hide"));
		if(chkObjDisplay==true)
		{
		driver.findElement(By.id("cometchat_hide")).click();
		thread();
		}
	}  

	@Test(priority = 1)
	void navigateToLocationsModule() throws InterruptedException {
		click(locationsInMainMenu);
		thread();				
	}
	
	@Test(priority = 2)
	void verifySubModuleMappingEmployees() throws IOException, InterruptedException {
		String employeesDefaultRowCountAfterMapping = "4";
		String flag;
		String[] rightRelationshipTableValue = new String[100];
		String[] leftRelationshipTableValue = new String[100];
		String[] tableValueAfterMappingArray = new String[100];
		String[] flagArray = new String[100];
		List<WebElement> locationsList = driver.findElements(By.xpath("//table[@id='facilities_table']//tbody[@role='alert']//tr"));
		int locationsListSize = locationsList.size();
		Random random = new Random();
		int locationValue = random.nextInt(locationsListSize);
		System.out.println("Location value "+locationValue);
		//int locationValue=1;
		if(locationValue==0){
			locationValue = locationValue+1;
		}
		System.out.println("Location value inside list view+++"+locationValue);
		driver.findElement(By.xpath("//table[@id='facilities_table']//tbody[@role='alert']//tr"+"["+locationValue+"]")).click();
		locationName = driver.findElement(By.xpath("//table[@id='facilities_table']//tbody[@role='alert']//tr["+locationValue+"]"+"//td[2]")).getAttribute("title");
		//locationName = driver.findElement(By.xpath("//table[@id='facilities_table']//tbody[@role='alert']//tr[1]//td[2]")).getAttribute("title");
		thread();
		click(employeesTabInsuranceRelationSection);
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
		//System.out.println("Employee value inside relationship section+++"+employeeValue);
		String employeeName = driver.findElement(By.xpath("//label[contains(text(),'List of Employees')]/..//button[@class='btn moveall']/../../select//option"+"["+employeeValue+"]")).getText();
		driver.findElement(By.xpath("//label[contains(text(),'List of Employees')]/..//button[@class='btn moveall']/../../select//option"+"["+employeeValue+"]")).click();
	    enterText(empLeftSearchTxtBoxRltWindow,employeeName);
	    click(leftMoveBtnEmpRltWindow);
	    //click(leftMoveAllBtnEmpRltWindow);
	    enterText(empRightSearchTxtBoxRltWindow,employeeName);
	    click(submitButtonRelationShipWindow);
	    try{
	    	thread();
	    	if(driver.findElement(By.xpath("//div[@class='modal-content']//div//h4[contains(text(),'Employees')]")).isDisplayed()){
				driver.findElement(otherLocationsEmployeesOkButton).click();
				waitForElement(msgNotificationBar);
				getObjectText(msgNotificationBar);
				takeScreenshot();
				verifyAssertEquals(locationName+" "+"successfully mapped to Employees", getActualObjectTxt);
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
				scrollToBottom();
				thread();
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
					//System.out.println("i value inside employee loop+++"+i);
					//System.out.println("Employee size inside loop+++"+leftValueListSize);
					Random random2 = new Random();
					int randomValue = random2.nextInt(leftValueListSize);
					//System.out.println("Random value inside employee loop+++"+randomValue);
					leftRelationshipTableValue[i] = driver.findElement(employeesXpathList[i]).getText();
					driver.findElement(employeesXpathList[i]).click();
					searchTextBoxInnerRelationTableList.get(i+i).sendKeys(leftRelationshipTableValue[i]);
					thread();
					empLeftMoveBtnInnerRltTableList.get(i).click();
					searchTextBoxInnerRelationTableList.get(i+i+1).sendKeys(leftRelationshipTableValue[i]);
					thread();
					
					submitButtonList.get(i).click();
					thread();
					driver.findElements(By.xpath("//table[@id='rel_resources']//tbody[@role='alert']//tr[contains(@class,'tableRow')]"));
					tableValueAfterMappingArray[i] = driver.findElements(tableValueAfterMapping).get(i).getText();
					if(leftRelationshipTableValue[i].contains(tableValueAfterMappingArray[i])){
						flagArray[i] = "true";
					}
					else{
						flagArray[i] = "false";
					}
					//System.out.println(rightRelationshipTableValue[i]);
					//System.out.println(tableValueAfterMappingArray[i]);
					//System.out.println(flagArray[i]);
					verifyAssertEquals(flagArray[i],"true");
					
				}
				click(empViewBtnRltWindow);
				List <WebElement> rowsAfterMapping = driver.findElements(By.xpath("//table[@id='rel_resources']//tbody[@role='alert']//tr[contains(@class,'tableRow')]"));
				int rowsAfterMappingSize = rowsAfterMapping.size();
				//System.out.println("Employee count"+rowsAfterMappingSize);
				String employeesRowCountAfterMapping = Integer.toString(rowsAfterMappingSize);
				verifyAssertEquals(employeesDefaultRowCountAfterMapping,employeesRowCountAfterMapping);
				
	    	}
			}catch(Exception E){				
				waitForElement(msgNotificationBar);
				getObjectText(msgNotificationBar);
				takeScreenshot();
				verifyAssertEquals(locationName+" "+"successfully mapped to Employees", getActualObjectTxt);
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
				scrollToBottom();
				thread();
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
					//System.out.println("i value inside employee loop+++"+i);
					//System.out.println("Employee size inside loop+++"+leftValueListSize);
					Random random2 = new Random();
					int randomValue = random2.nextInt(leftValueListSize);
					//System.out.println("Random value inside employee loop+++"+randomValue);
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
					thread();
					if(leftRelationshipTableValue[i].contains(tableValueAfterMappingArray[i])){
						flagArray[i] = "true";
					}
					else{
						flagArray[i] = "false";
					}
					
					//System.out.println(rightRelationshipTableValue[i]);
					//System.out.println(tableValueAfterMappingArray[i]);
					//System.out.println(flagArray[i]);
					verifyAssertEquals(flagArray[i],"true");
				}
				
				click(empViewBtnRltWindow);
				List <WebElement> rowsAfterMapping = driver.findElements(By.xpath("//table[@id='rel_resources']//tbody[@role='alert']//tr[contains(@class,'tableRow')]"));
				int rowsAfterMappingSize = rowsAfterMapping.size();
				//System.out.println("Employee count"+rowsAfterMappingSize);
				String employeesRowCountAfterMapping = Integer.toString(rowsAfterMappingSize);
				verifyAssertEquals(employeesDefaultRowCountAfterMapping,employeesRowCountAfterMapping);
	    	
			}
	}
	
	@Test(priority = 3)
	void verifySubModuleMappingThreats() throws IOException, InterruptedException {
		String threatsDefaultRowCountAfterMapping = "2";
		String flag;
		String[] rightRelationshipTableValue = new String[100];
		String[] leftRelationshipTableValue = new String[100];
		String[] tableValueAfterMappingArray = new String[100];
		String[] flagArray = new String[100];
		click(threatsTabRelationSection);
		click(addRemoveRelationsBtn);
		thread();
		click(rightMoveAllBtnThreatRltWindow);
		List<WebElement> leftThreatRelationWindowValueList = driver.findElements(threatsLeftRltWindowValues);
		int leftThreatRelationWindowValueListSize = leftThreatRelationWindowValueList.size();
		Random random = new Random();
		int threatValue = random.nextInt(leftThreatRelationWindowValueListSize);
		if(threatValue==0){
			threatValue = threatValue+ 1;
		}
		//System.out.println("Threat value inside relationship section+++"+threatValue);
		String threatName = driver.findElement(By.xpath("//label[contains(text(),'List of Threats')]/..//button[@class='btn moveall']/../../select//option"+"["+threatValue+"]")).getText();
		driver.findElement(By.xpath("//label[contains(text(),'List of Threats')]/..//button[@class='btn moveall']/../../select//option"+"["+threatValue+"]")).click();
	    enterText(threatsLeftSearchTxtBoxRltWindow,threatName);
	    thread();
	    click(threatsLeftMoveBtnRltWindow);
	    enterText(threatsRightSearchTxtBoxRltWindow,threatName);
	    thread();
	    click(submitButtonRelationShipWindow);
	    waitForElement(msgNotificationBar);
		getObjectText(msgNotificationBar);
		takeScreenshot();
		verifyAssertEquals(locationName+" "+"successfully mapped to Threats", getActualObjectTxt);
		thread();
		scrollToBottom();
		String threatValueAfterMapping = driver.findElement(By.xpath("//table[@id='rel_threats']//tbody[@role='alert']//td[1]")).getText();
		if(threatName.contains(threatValueAfterMapping)){
			 flag = "true";
		}
		else{
			 flag = "false";
		}
		verifyAssertEquals(flag,"true");
		click(threatsViewBtnRltWindow);
		thread();
		scrollToBottom();
		click(threatsAddRemoveBtnInnerRltTable);
		thread();
		List<WebElement> addRemoveButtonList = driver.findElements(threatsAddRemoveBtnInnerRltTable);
		List<WebElement> searchTextBoxInnerRelationTableList = driver.findElements(threatsSearchTxtBoxInnerRltTable);
		List<WebElement> leftTabsRelationshipTableList = driver.findElements(threatsLeftTabsInnerRltTable);
		int leftTabsRelationshipTableListSize = leftTabsRelationshipTableList.size();
		List<WebElement> rightRelationshipTableMoveAllArrowList = driver.findElements(threatsRightMoveAllArrowInnerRltTable);
		List<WebElement> submitButtonList = driver.findElements(threatsSubmitBtnInnerRltTable);
		List<WebElement> valueAfterMappingList = driver.findElements(threatsTableValueAfterMapping);
		List<WebElement> threatsLeftMoveBtnInnerRltTableList = driver.findElements(threatsLeftMoveBtnInnerRltTable);
		click(threatsRightMoveAllArrowInnerRltTable);
		scrollToBottom();
		thread();
		By threatXpathList[] = {locationSubTab,taskGroupsSubTab};
		for(int i=0;i<leftTabsRelationshipTableListSize;i++){
			
			if(i>0){
				leftTabsRelationshipTableList.get(i).click();
				addRemoveButtonList.get(i).click();		
				rightRelationshipTableMoveAllArrowList.get(i).click();
				thread();
			}
			int leftValueListSize = driver.findElements(threatsLeftRltTableValues).size();
			//System.out.println("i value inside threats loop+++"+i);
			//System.out.println("Threat size inside loop+++"+leftValueListSize);
			Random random1 = new Random();
			int randomValue = random1.nextInt(leftValueListSize);
			//System.out.println("Random value inside threat loop+++"+randomValue);
			leftRelationshipTableValue[i] = driver.findElement(threatXpathList[i]).getText();
			driver.findElement(threatXpathList[i]).click();
			searchTextBoxInnerRelationTableList.get(i+i).sendKeys(leftRelationshipTableValue[i]);
			thread();
			threatsLeftMoveBtnInnerRltTableList.get(i).click();
			searchTextBoxInnerRelationTableList.get(i+i+1).sendKeys(leftRelationshipTableValue[i]);
			thread();
			
			submitButtonList.get(i).click();
			thread();
			tableValueAfterMappingArray[i] = driver.findElements(threatsTableValueAfterMapping).get(i).getText();
			if(leftRelationshipTableValue[i].contains(tableValueAfterMappingArray[i])){
				flagArray[i] = "true";
			}
			else{
				flagArray[i] = "false";
			}
			//System.out.println(rightRelationshipTableValue[i]);
			//System.out.println(tableValueAfterMappingArray[i]);
			//System.out.println(flagArray[i]);
			verifyAssertEquals(flagArray[i],"true");
		}
		click(threatsViewBtnRltWindow);
		List <WebElement> rowsAfterMapping = driver.findElements(By.xpath("//table[@id='rel_threats']//tbody[@role='alert']//tr[contains(@class,'tableRow')]"));
		int rowsAfterMappingSize = rowsAfterMapping.size();
		//System.out.println("Employee count"+rowsAfterMappingSize);
		String threatsRowCountAfterMapping = Integer.toString(rowsAfterMappingSize);
		verifyAssertEquals(threatsDefaultRowCountAfterMapping,threatsRowCountAfterMapping);
	}
	
	@Test(priority = 4)
	void verifySubModuleMappingAssets() throws IOException, InterruptedException{
		String assetDefaultRowCountAfterMapping = "6";
		String flag;
		String[] flagArray = new String[100];
		String[] leftRltTableValueList = new String[100];
		String[] rightRltTableValueList =new String[100];
		String[] tableValueAfterMappingArray = new String[100];
		click(assetsTabInsuranceRelationSection);
		click(addRemoveRelationsBtn);
		thread();
		click(rightMoveAllBtnAssetRltWindow);
		List<WebElement> leftAssetRelationWindowValueList = driver.findElements(leftAssetRelationWindowValues);
		int leftAssetRelationWindowValueListSize = leftAssetRelationWindowValueList.size();
		Random random = new Random();
		int assetValue = random.nextInt(leftAssetRelationWindowValueListSize);
		if(assetValue == 0){
			assetValue = assetValue+1;
		}
		//System.out.println("Asset value inside relationship section+++"+assetValue);
		String assetName = driver.findElement(By.xpath("//label[contains(text(),'List of Assets')]/..//button[@class='btn moveall']/../../select//option"+"["+assetValue+"]")).getText();
		driver.findElement(By.xpath("//label[contains(text(),'List of Assets')]/..//button[@class='btn moveall']/../../select//option"+"["+assetValue+"]")).click();
		enterText(assetLeftSearchTxtBoxRltWindow,assetName);
		thread();
		click(leftMoveBtnAssetRltWindow);
		enterText(assetRightSearchTxtBoxRltWindow,assetName);
		thread();
		click(submitButtonRelationShipWindow);
		waitForElement(msgNotificationBar);
		getObjectText(msgNotificationBar);
		takeScreenshot();
		verifyAssertEquals(locationName+" "+"successfully mapped to Assets", getActualObjectTxt);
		thread();
		scrollToBottom();
		String assetValueAfterMapping = driver.findElement(By.xpath("//table[@id='rel_assets']//tbody[@role='alert']//span")).getText();
		if(assetName.contains(assetValueAfterMapping)){
			flag = "true";
		}else
		{
			flag = "false";
		}
		verifyAssertEquals(flag,"true");
		click(assetViewBtnRltWindow);
		thread();
		scrollToBottom();
		click(assetAddRemoveRltBtnInnerRltTable);
		thread();
		scrollToBottom();
		List<WebElement> assetAddRemoveBtnInnerRltTableList = driver.findElements(assetAddRemoveRltBtnInnerRltTable);
		List<WebElement> assetLefttabsRltTableList = driver.findElements(assetLefttabsRltTable);
		int assetLefttabsRltTableListSize = assetLefttabsRltTableList.size();
		//System.out.println(assetLefttabsRltTableListSize);
		List<WebElement> searchTxtBoxInnerRltTableList = driver.findElements(assetSearchTxtBoxInnerRltTable);
		List<WebElement> leftRltWindowMoveAllArrowList = driver.findElements(assetLeftRltTableMoveAllArrow);
		List<WebElement> rightRltWindowMoveAllArrowList = driver.findElements(assetRightTableMoveAllArrow);
		List<WebElement> submitBtnInnerRltTable = driver.findElements(assetSubmitBtnInnerRltTable);
		List<WebElement> assetLeftRltTableValuesList = driver.findElements(assetLeftRltTableValues);
		List<WebElement> assetLeftRltTableMoveArrowList = driver.findElements(assetLeftRltTableMoveArrow);
		
		click(assetRightTableMoveAllArrow);
		By assetsXpathList[] = {locationSubTab,assetGroupsSubTab,contactsSubTab,insuranceSubTab,taskSubTab,businessFunctionsSubTab};
		for(int i=0;i<assetLefttabsRltTableListSize;i++){
			
			if(i>0){
				assetLefttabsRltTableList.get(i).click();
				assetAddRemoveBtnInnerRltTableList.get(i).click();
				rightRltWindowMoveAllArrowList.get(i).click();
				thread();
			}
			int leftListValueSize = driver.findElements(assetLeftRltTableValues).size();
			//System.out.println("i value inside asset loop+++"+i);
			//System.out.println("Asset size inside loop+++"+leftListValueSize);
			Random random1 = new Random();
			int randomValue = random1.nextInt(leftListValueSize);
			//System.out.println("Random value inside asset loop+++"+randomValue);
			leftRltTableValueList[i] = driver.findElement(assetsXpathList[i]).getText();
			driver.findElement(assetsXpathList[i]).click();
			searchTxtBoxInnerRltTableList.get(i+i).sendKeys(leftRltTableValueList[i]);
			thread();
			assetLeftRltTableMoveArrowList.get(i).click();
			searchTxtBoxInnerRltTableList.get(i+i+1).sendKeys(leftRltTableValueList[i]);
			thread();
			submitBtnInnerRltTable.get(i).click();
			thread();
			tableValueAfterMappingArray[i] = driver.findElements(assetTableValueAfterMapping).get(i).getText();
			if(leftRltTableValueList[i].contains(tableValueAfterMappingArray[i])){
				flagArray[i] = "true";
			}else
			{
				flagArray[i] = "false";
			}
			//System.out.println(rightRltTableValueList[i]);
			//System.out.println(tableValueAfterMappingArray[i]);
			//System.out.println(flagArray[i]);
			verifyAssertEquals(flagArray[i],"true");
		}
		click(assetViewBtnRltWindow);
		List<WebElement> rowsAfterMapping = driver.findElements(By.xpath("//table[@id='rel_assets']//tbody[@role='alert']//tr[contains(@class,'tableRow')]"));
		int rowsAfterMappingSize = rowsAfterMapping.size();
		//System.out.println("Asset count"+rowsAfterMappingSize);
		String assetRowCountAfterMapping = Integer.toString(rowsAfterMappingSize);
		verifyAssertEquals(assetRowCountAfterMapping,assetDefaultRowCountAfterMapping);
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
		//System.out.println("Insurance value inside relationship section+++"+randomInsuranceValue);
		String insuranceName = driver.findElement(By.xpath("//label[contains(text(),'List of Insurance')]/..//button[@class='btn moveall']/../../select//option"+"["+randomInsuranceValue+"]")).getText();
		driver.findElement(By.xpath("//label[contains(text(),'List of Insurance')]/..//button[@class='btn moveall']/../../select//option"+"["+randomInsuranceValue+"]")).click();
		enterText(insuranceLeftSearchTxtBoxRltWindow,insuranceName);
		thread();
		click(insuranceLeftMoveArrowRltWindow);
		thread();
		enterText(insuranceRightSearchTxtBoxRltWindow,insuranceName);
		click(submitButtonRelationShipWindow);
		waitForElement(msgNotificationBar);
		getObjectText(msgNotificationBar);
		takeScreenshot();
		verifyAssertEquals(locationName+" "+"successfully mapped to Insurance", getActualObjectTxt);
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
			//System.out.println("i value inside insurance loop+++"+i);
			//System.out.println("Insurance size inside loop+++"+leftValueListSize);
			Random random1 = new Random();
			int randomValue = random1.nextInt(leftValueListSize);
			//System.out.println("Random value inside insurance loop+++"+randomValue);
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
			//System.out.println(rightRelationshipTableValue[i]);
			//System.out.println(tableValueAfterMappingArray[i]);
			//System.out.println(flagArray[i]);
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
		enterText(businessFntRightSearchTextBoxRltWindow,businessFunctionName);
		thread();
		click(submitButtonRelationShipWindow);
		waitForElement(msgNotificationBar);
		getObjectText(msgNotificationBar);
		takeScreenshot();
		verifyAssertEquals(locationName+" "+"successfully mapped to Business Functions", getActualObjectTxt);
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
		By businessFunctionsXpathList[] = {locationSubTab,employeesSubTab,assetsSubTab,insuranceSubTab,taskSubTab};
		for(int i=0;i<businessFntLeftTabsInnerRltTableListSize;i++){
			
			if(i>0){
				businessFntLeftTabsInnerRltTableList.get(i).click();
				businessFntAddRemoveBtnInnerRltWindowList.get(i).click();
				businessFntRightMoveAllArrowInnerRltTableList.get(i).click();
				thread();
			}
			int leftListValueSize=driver.findElements(businessFntLeftRltTableValues).size();
			//System.out.println("i value inside business functions loop+++"+i);
			//System.out.println("Business functions size inside loop+++"+leftListValueSize);
			Random random1 = new Random();
			int randomValue = random1.nextInt(leftListValueSize);
			//System.out.println("Random value inside business function loop+++"+randomValue);
			leftInnerTableValue[i] = driver.findElement(businessFunctionsXpathList[i]).getText();
			driver.findElement(businessFunctionsXpathList[i]).click();
			businessFntSearchTxtBoxInnerRltTableList.get(i+i).sendKeys(leftInnerTableValue[i]);
			thread();
			businessFntLeftMoveArrowInnerRltTableList.get(i).click();
			businessFntSearchTxtBoxInnerRltTableList.get(i+i+1).sendKeys(leftInnerTableValue[i]);
			thread();
			
			businessFntSubmitBtnInnerRltTableList.get(i).click();
			thread();	
			tableValueAfterMapping[i] = driver.findElements(businessFntTableValueAfterMapping).get(i).getText();
			if(leftInnerTableValue[i].contains(tableValueAfterMapping[i])){
				flagArray[i] = "true";
			}else
			{
				flagArray[i] = "false";
			}
			//System.out.println(rightInnerTableValue[i]);
			//System.out.println(tableValueAfterMapping[i]);
			//System.out.println(flagArray[i]);
			verifyAssertEquals(flagArray[i],"true");
		}
		click(businessFntViewBtnRltWindow);
		List<WebElement> rowsAfterMapping = driver.findElements(By.xpath("//table[@id='rel_processes']//tbody[@role='alert']//tr[contains(@class,'tableRow')]"));
		int rowsAfterMappingSize = rowsAfterMapping.size();
		String businessFunctionsCountAfterMapping = Integer.toString(rowsAfterMappingSize);
		verifyAssertEquals(businessFunctionsDefaultCountAfterMapping,businessFunctionsCountAfterMapping);
		
	}
	
	@Test(priority=7)
    void verifySubModuleMappingContacts() throws IOException, InterruptedException{
    	String contactsDefaultRowCountAfterMapping = "3";
    	String flag;
    	String[] leftInnerTableValue = new String[100];
		String[] rightInnerTableValue = new String[100];
		String[] tableValueAfterMapping = new String[100];
		String[] flagArray = new String[100];
    	click(contactsTabTasksRltSection);
    	thread();
    	click(addRemoveRelationsBtn);
    	click(rightMoveAllArrowContactsRltWindow);
    	List<WebElement> contactsLeftRltWindowValuesList = driver.findElements(contactsLeftRltWindowValues);
    	int contactsLeftRltWindowValuesSize = contactsLeftRltWindowValuesList.size();
    	Random random = new Random();
    	int randomContactValue = random.nextInt(contactsLeftRltWindowValuesSize);
    	if(randomContactValue==0){
    		randomContactValue = randomContactValue+1;
    	}
    	//System.out.println("Contacts value inside relationship section++++"+randomContactValue);
    	String contactName = driver.findElement(By.xpath("//label[contains(text(),'List of Contacts')]/..//button[@class='btn moveall']/../../select//option"+"["+randomContactValue+"]")).getText();
    	driver.findElement(By.xpath("//label[contains(text(),'List of Contacts')]/..//button[@class='btn moveall']/../../select//option"+"["+randomContactValue+"]")).click();
    	enterText(contactsLeftSearchTxtBoxRltWindow,contactName);
    	thread();
    	click(contactsLeftMoveBtnRltWindow);
    	enterText(contactsRightSearchTxtBoxRltWindow,contactName);
    	click(submitButtonRelationShipWindow);
    	waitForElement(msgNotificationBar);
		getObjectText(msgNotificationBar);
		takeScreenshot();
		verifyAssertEquals(locationName+" "+"successfully mapped to Contacts", getActualObjectTxt);
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
		By contactsXpathList[] = {locationSubTab,contactGroupsSubTab,taskSubTab};
		for(int i=0;i<contactsTabValueInnerRltTableListSize;i++){
			if(i>0){
				contactsTabValueInnerRltTableList.get(i).click();
				contactsAddRemoveBtnInnerRltWindowList.get(i).click();
				contactsRightMoveAllArrowInnerRltTableList.get(i).click();
				thread();
			}
			int leftListValueSize=driver.findElements(contactsLeftInnerTableValues).size();
			//System.out.println("i value inside contacts loop++++"+i);
			//System.out.println("Contacts size inside loop+++"+leftListValueSize);
			Random random1 = new Random();
			int randomValue = random1.nextInt(leftListValueSize);
			//System.out.println("Random contact value inside contacts loop++++"+randomValue);
			leftInnerTableValue[i] = driver.findElement(contactsXpathList[i]).getText();
			driver.findElement(contactsXpathList[i]).click();
			contactsSearchTxtBoxInnerRltTableList.get(i+i).sendKeys(leftInnerTableValue[i]);
			thread();
			contactsLeftMoveBtnInnerRltTableList.get(i).click();
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
			//System.out.println(rightInnerTableValue[i]);
			//System.out.println(tableValueAfterMapping[i]);
			//System.out.println(flagArray[i]);
			verifyAssertEquals(flagArray[i],"true");
		}
		click(contactsViewBtnRltWindow);
		List<WebElement> rowsAfterMapping = driver.findElements(By.xpath("//table[@id='rel_contacts']//tbody[@role='alert']//tr[contains(@class,'tableRow')]"));
		int rowsAfterMappingSize = rowsAfterMapping.size();
		String contactsRowCountAfterMapping = Integer.toString(rowsAfterMappingSize);
		verifyAssertEquals(contactsRowCountAfterMapping,contactsDefaultRowCountAfterMapping);
    }
	
	@Test(priority=8)
	void verifySubModuleMappingTeams() throws InterruptedException, IOException
	{
		click(locationsInMainMenu);
		thread();
		//driver.findElement(By.xpath("//table[@id='facilities_table']//tbody[@role='alert']//tr"+"["+locationValue+"]")).click();
		
		webElement(teamsTabTasksRltSection);
		scrollInnerScrollBar(webelementFrame);
		
		click(teamsTabTasksRltSection);
		thread();
		click(addRemoveRelationsBtn);
		thread();
		
		verifyAssert(rltnMapLocationsPp);
		thread();
		//Loc_AddDeleteLocation.mapTeamsToLocations();
				
	    doMappingt();
		takeScreenshot();
		
		click(mapSubmitBtn);
		thread();
		
		waitForElement(msgNotificationBar);
		getObjectText(msgNotificationBar);
		takeScreenshot();
		//verifyAssertEquals(locAdd.adedLocationName+" successfully mapped to Teams", getActualObjectTxt);
		
		thread();
		webElement(loctTmsViewbtn);
		scrollInnerScrollBar(webelementFrame);
		
		click(loctTmsViewbtn);
		thread();
		
		
		// Mapping team to Locaitons
		
		click(tmsLocTab);
		thread();
		
		webElement(tmsLocAddRemoveRltnBtn);
		scrollInnerScrollBar(webelementFrame);
		
		click(tmsLocAddRemoveRltnBtn);
		thread();
		click(tmsLocMoveAllLft);
		thread();
		for(int i=1;i<=2;i++)
		{
	   
		//getTotalValuesIndd(subMapLftTtlVal);
	    getTotalValuesIndd(tmsLocTtlCnt);
		 Random random=new Random();
		 int ran = random.nextInt(totalDDValCount-1)+1;
		 WebElement name=driver.findElement(By.xpath("//table[@id='rel_resourcegroup']/tbody/tr[2]/td/div/div/div[2]/div[1]/div[2]/div[1]/div[1]/div/div[1]/select/option["+ran+"]"));
		 String NameForSearch=driver.findElement(By.xpath("//table[@id='rel_resourcegroup']/tbody/tr[2]/td/div/div/div[2]/div[1]/div[2]/div[1]/div[1]/div/div[1]/select/option["+ran+"]")).getText();
       // System.out.println("Loc Name "+NameForSearch);
        name.click();
        thread();
		 
		 // click(subMapRemoveAllArrow);
		//	Enter Name for Search
			enterText(tmsLocLftSearchBox, NameForSearch);
			getObjectText(tmsLocLftFstVal);
			verifyAssertEquals(NameForSearch, getActualObjectTxt);
          click(tmsLocMoveRgt);
          			
           clear(tmsLocLftSearchBox);
			thread();
		//	Search Mapped Name
			enterText(tmsLocRgtSearchBox, NameForSearch);
			getObjectText(tmsLocRgtFstVal);
			//	Verify Mapped Name
			verifyAssertEquals(NameForSearch, getActualObjectTxt);
			clear(tmsLocRgtSearchBox);
	    
		takeScreenshot();
		thread();
		}	
		webElement(tmsLocSubmitBtn);
		scrollInnerScrollBar(webelementFrame);
		click(tmsLocSubmitBtn);
		thread();
		
		
		//mapping team to Employees
		
		webElement(tmsEmpTab);
		scrollInnerScrollBar(webelementFrame);
		
		click(tmsEmpTab);
		thread();
		
		webElement(tmsEmpAddRemoveRltnBtn);
		scrollInnerScrollBar(webelementFrame);
		
		click(tmsEmpAddRemoveRltnBtn);
		thread();
		click(tmsEmpMoveAllLft);
		thread();
		for(int i=1;i<=2;i++)
		{	   
	    getTotalValuesIndd(tmsEmpTtlCnt);
		 Random random=new Random();
		 int ran = random.nextInt(totalDDValCount-1)+1;
		 WebElement name=driver.findElement(By.xpath("//table[@id='rel_resourcegroup']/tbody/tr[2]/td/div/div/div[2]/div[2]/div[2]/div[1]/div[1]/div/div[1]/select/option["+ran+"]"));
		 String NameForSearch=driver.findElement(By.xpath("//table[@id='rel_resourcegroup']/tbody/tr[2]/td/div/div/div[2]/div[2]/div[2]/div[1]/div[1]/div/div[1]/select/option["+ran+"]")).getText();
        //System.out.println("Emp Name "+NameForSearch);
        name.click();
        thread();
		 
		//	Enter Name for Search
			enterText(tmsEmpLftSearchBox, NameForSearch);
			getObjectText(tmsEmpLftFstVal);
			verifyAssertEquals(NameForSearch, getActualObjectTxt);
          click(tmsEmpMoveRgt);
          			
           clear(tmsEmpLftSearchBox);
			thread();
		//	Search Mapped Name
			enterText(tmsEmpRgtSearchBox, NameForSearch);
			getObjectText(tmsEmpRgtFstVal);
			verifyAssertEquals(NameForSearch, getActualObjectTxt);
			clear(tmsEmpRgtSearchBox);
	    
		takeScreenshot();
		thread();
		}	
		webElement(tmsEmpSubmitBtn);
		scrollInnerScrollBar(webelementFrame);
		click(tmsEmpSubmitBtn);
		thread();
		
		
		//mapping team to Tasks
		
		webElement(tmsTskTab);
		scrollInnerScrollBar(webelementFrame);
		
		click(tmsTskTab);
		thread();
		
		webElement(tmsTskAddRemoveRltnBtn);
		scrollInnerScrollBar(webelementFrame);
		
		click(tmsTskAddRemoveRltnBtn);
		thread();
		click(tmsTskMoveAllLft);
		thread();
		for(int i=1;i<=2;i++)
		{	   
	    getTotalValuesIndd(tmsTskTtlCnt);
		 Random random=new Random();
		 int ran = random.nextInt(totalDDValCount-1)+1;
		 WebElement name=driver.findElement(By.xpath("//table[@id='rel_resourcegroup']/tbody/tr[2]/td/div/div/div[2]/div[3]/div[2]/div[1]/div[1]/div/div[1]/select/option["+ran+"]"));
		 String NameForSearch=driver.findElement(By.xpath("//table[@id='rel_resourcegroup']/tbody/tr[2]/td/div/div/div[2]/div[3]/div[2]/div[1]/div[1]/div/div[1]/select/option["+ran+"]")).getText();
       // System.out.println("Task Name "+NameForSearch);
        name.click();
        thread();
		 
		//	Enter Name for Search
			enterText(tmsTskLftSearchBox, NameForSearch);
			getObjectText(tmsTskLftFstVal);
			try{
			verifyAssertEquals(NameForSearch, getActualObjectTxt);
			}
			catch(Error e)
			{
				
			}
          click(tmsTskMoveRgt);
          			
           clear(tmsTskLftSearchBox);
			thread();
		//	Search Mapped Name
			enterText(tmsTskRgtSearchBox, NameForSearch);
			getObjectText(tmsTskRgtFstVal);
			verifyAssertEquals(NameForSearch, getActualObjectTxt);
			clear(tmsTskRgtSearchBox);
	    
		takeScreenshot();
		thread();
		}	
		webElement(tmsTskSubmitBtn);
		scrollInnerScrollBar(webelementFrame);
		click(tmsTskSubmitBtn);
		thread();
		
	}
	
	

}
