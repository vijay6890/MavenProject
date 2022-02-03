package Scripts;

/******************************************************************************************************************************
 * 
 * @author Rahul Nair
 * 
 * Test 1: Login to SIB application
 * Test 2: Navigate to insurance modules
 * Test 3: Verify sub module mapping functionality in Locations sub tab
 * Test 4: Verify sub module mapping functionality in Employees sub tab
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
import static ObjectRepository.Locations.msgNotificationBar;

public class Ins_SubModuleMapping extends Page {
	
	String insuranceName;
	LoginLogout ll = new LoginLogout();

/*	@Test(priority = 0)
	void login() throws BiffException, IOException, InterruptedException {
		ll.loginToSIB();
		driver.findElement(By.id("cometchat_hide")).click();
	}   */

	@Test(priority = 1)
	void navigateToInsuranceModule() throws InterruptedException {
		click(insuranceInMainMenu);
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
		List<WebElement> insuranceList = driver
				.findElements(By.xpath("//table[@id='insurance_table']//tbody[@role='alert']//tr"));
		int insuranceListSize = insuranceList.size();
		Random insurance = new Random();
		int insuranceValue = insurance.nextInt(insuranceListSize);
		if (insuranceValue == 0) {
			insuranceValue = insuranceValue + 1;
		}
		System.out.println("Insurance value inside list view+++"+insuranceValue);
		driver.findElement(
				By.xpath("//table[@id='insurance_table']//tbody[@role='alert']//tr" + "[" + insuranceValue + "]"))
		.click();
		// Clicking on a random insurance value
		insuranceName = driver
				.findElement(By.xpath(
						"//table[@id='insurance_table']//tbody[@role='alert']//tr[" + insuranceValue + "]//td[2]"))
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
		System.out.println("Location value inside relationship section+++"+locationNameValue);
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
		verifyAssertEquals(insuranceName + " " + "successfully mapped to Locations", getActualObjectTxt);
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
		thread();
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
			System.out.println("i value inside locations loop+++"+i);
			System.out.println("Locations size inside loop+++"+leftValueListSize);
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
	void verifySubModuleMappingEmployees() throws IOException, InterruptedException{
		String employeesDefaultRowCountAfterMapping = "4";
		String flag;
		String[] rightRelationshipTableValue = new String[100];
		String[] leftRelationshipTableValue = new String[100];
		String[] tableValueAfterMappingArray = new String[100];
		String[] flagArray = new String[100];
		click(employeesTabInsuranceRelationSection);
		click(addRemoveRelationsBtn);
		thread();
		click(rightMoveAllBtnEmpRltWindow);
		
		List<WebElement> leftEmployeeRelationWindowValueList = driver.findElements(leftEmployeeRelationWindowValues);
		int leftEmployeeRelationWindowValueListSize = leftEmployeeRelationWindowValueList.size();
		Random random = new Random();
		int employeeValue = random.nextInt(leftEmployeeRelationWindowValueListSize);
		if(employeeValue==0){
			employeeValue = employeeValue+ 1;
		}
		System.out.println("Employee value inside relationship section+++"+employeeValue);
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
		verifyAssertEquals(insuranceName+" "+"successfully mapped to Employees", getActualObjectTxt);
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
			System.out.println("i value inside employees loop+++"+i);
			System.out.println("Employee size inside loop+++"+leftValueListSize);
			Random random1 = new Random();
			int randomValue = random1.nextInt(leftValueListSize);
			System.out.println("Random value inside employee loop+++"+randomValue);
			leftRelationshipTableValue[i] = driver.findElement(employeesXpathList[i]).getText();
			driver.findElement(employeesXpathList[i]).click();
			searchTextBoxInnerRelationTableList.get(i+i).sendKeys(leftRelationshipTableValue[i]);
			thread();
			empLeftMoveBtnInnerRltTableList.get(i).click();
			//leftRelationshipTableMoveAllArrowList.get(i).click();
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
		System.out.println("Asset value inside relationship section+++"+assetValue);
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
		verifyAssertEquals(insuranceName+" "+"successfully mapped to Assets", getActualObjectTxt);
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
		System.out.println(assetLefttabsRltTableListSize);
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
			System.out.println("i value inside assets loop+++"+i);
			System.out.println("Asset size inside loop+++"+leftListValueSize);
			Random random1 = new Random();
			int randomValue = random1.nextInt(leftListValueSize);
			System.out.println("Random value inside assets loop+++"+randomValue);
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
			System.out.println(rightRltTableValueList[i]);
			System.out.println(tableValueAfterMappingArray[i]);
			System.out.println(flagArray[i]);
			verifyAssertEquals(flagArray[i],"true");
		}
		click(assetViewBtnRltWindow);
		List<WebElement> rowsAfterMapping = driver.findElements(By.xpath("//table[@id='rel_assets']//tbody[@role='alert']//tr[contains(@class,'tableRow')]"));
		int rowsAfterMappingSize = rowsAfterMapping.size();
		System.out.println("Asset count"+rowsAfterMappingSize);
		String assetRowCountAfterMapping = Integer.toString(rowsAfterMappingSize);
		verifyAssertEquals(assetRowCountAfterMapping,assetDefaultRowCountAfterMapping);
	}
	
	@Test(priority=5)
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
		System.out.println("Business function value inside relationship section+++"+leftBusinessFntRltWindowValue);
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
		verifyAssertEquals(insuranceName+" "+"successfully mapped to Business Functions", getActualObjectTxt);
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
			System.out.println("Business functions size inside loop+++"+leftListValueSize);
			Random random1 = new Random();
			int randomValue = random1.nextInt(leftListValueSize);
			System.out.println("Random value inside business functions+++"+randomValue);
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

}


