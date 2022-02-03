package Scripts;

/******************************************************************************************************************************
 * 
 * @author Rahul Nair
 * 
 * Test 1: Login to SIB application
 * Test 2: Navigate to Business Functions module
 * Test 3: Verify sub module mapping functionality in Locations sub tab
 * Test 4: Verify sub module mapping functionality in Employees sub tab
 * Test 5: Verify sub module mapping functionality in Assets sub tab
 * Test 6: Verify sub module mapping functionality in Insurance sub tab
 * Test 7: Verify sub module mapping functionality in Tasks sub tab
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
import static ObjectRepository.BusinessFunctions.*;
import static ObjectRepository.TaskGroups.*;

public class BusinessFunctions_SubModuleMapping extends Page {
	
	String businessFunctionName;
	LoginLogout ll = new LoginLogout();

	/*@Test(priority = 0)
	void login() throws BiffException, IOException, InterruptedException {
		ll.loginToSIB();
		driver.findElement(By.id("cometchat_hide")).click();
	}*/

	@Test(priority = 1)
	void navigateToBusinessFunctionsModule() throws InterruptedException {
		click(businessFunctionsInMainMenu);
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
		List<WebElement> businessFunctionsList = driver
				.findElements(By.xpath("//table[@id='process_table']//tbody[@role='alert']//tr"));
		int businessFunctionsListSize = businessFunctionsList.size();
		Random businessFunction = new Random();
		int businessFunctionValue = businessFunction.nextInt(businessFunctionsListSize);
		if (businessFunctionValue == 0) {
			businessFunctionValue = businessFunctionValue + 1;
		}
		System.out.println("Business function random value from list view++"+businessFunctionValue);
		driver.findElement(
				By.xpath("//table[@id='process_table']//tbody[@role='alert']//tr" + "[" + businessFunctionValue + "]"))
		.click();
		// Clicking on a random insurance value
		businessFunctionName = driver
				.findElement(By.xpath(
						"//table[@id='process_table']//tbody[@role='alert']//tr[" + businessFunctionValue + "]//td[2]"))
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
		verifyAssertEquals(businessFunctionName + " " + "successfully mapped to Locations", getActualObjectTxt);
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
			System.out.println("i value inside location loop+++"+locationNameValue);
			System.out.println("location size inside loop+++"+leftValueListSize);
			Random random1 = new Random();
			int randomValue = random1.nextInt(leftValueListSize);
			System.out.println("Random value inside loop+++"+randomValue);
			leftRelationShipTableValue[i] = driver.findElement(locationsXpathList[i]).getText();
			driver.findElement(locationsXpathList[i]).click();
			locationsInnerRelationshipTableSearchTextBoxList.get(i+i).sendKeys(leftRelationShipTableValue[i]);
			thread();
			leftMoveBtnInnerRltTableList.get(i).click();
			//leftMoveAllArrowList.get(i).click();
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
		verifyAssertEquals(businessFunctionName+" "+"successfully mapped to Employees", getActualObjectTxt);
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
		By employeesXpathList[] = {teamsSubTab,insuranceSubTab,taskSubTab,businessFunctionsSubTab};
		for(int i=0;i<leftTabsRelationshipTableListSize;i++){
			
			if(i>0){
				leftTabsRelationshipTableList.get(i).click();
				addRemoveButtonList.get(i).click();		
				rightRelationshipTableMoveAllArrowList.get(i).click();
				thread();
			}
			int leftValueListSize = driver.findElements(empLeftInnerRltTableValues).size();
			System.out.println("i value inside loop+++"+leftValueListSize);
			System.out.println("Employee size inside loop+++"+leftValueListSize);
			Random random1 = new Random();
			int randomValue = random1.nextInt(leftValueListSize);
			System.out.println("Random value inside employees loop+++"+randomValue);
			leftRelationshipTableValue[i] = driver.findElement(empLeftInnerRltTableValues).getText();
			driver.findElement(empLeftInnerRltTableValues).click();
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
		String assetName = driver.findElement(By.xpath("//label[contains(text(),'List of Assets')]/..//button[@class='btn moveall']/../../select//option"+"["+assetValue+"]")).getText();
		driver.findElement(By.xpath("//label[contains(text(),'List of Assets')]/..//button[@class='btn moveall']/../../select//option"+"["+assetValue+"]")).click();
		enterText(assetLeftSearchTxtBoxRltWindow,assetName);
		thread();
		click(leftMoveBtnAssetRltWindow);
		//click(leftMoveAllBtnAssetRltWindow);
		enterText(assetRightSearchTxtBoxRltWindow,assetName);
		click(submitButtonRelationShipWindow);
		waitForElement(msgNotificationBar);
		getObjectText(msgNotificationBar);
		takeScreenshot();
		verifyAssertEquals(businessFunctionName+" "+"successfully mapped to Assets", getActualObjectTxt);
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
		thread();
		By assetsXpathList[] = {locationSubTab,assetGroupsSubTab,contactsSubTab,insuranceSubTab,taskSubTab,businessFunctionsSubTab};
		for(int i=0;i<assetLefttabsRltTableListSize;i++){
			
			if(i>0){
				assetLefttabsRltTableList.get(i).click();
				assetAddRemoveBtnInnerRltTableList.get(i).click();
				rightRltWindowMoveAllArrowList.get(i).click();
				thread();
			}
			int leftListValueSize = driver.findElements(assetLeftRltTableValues).size();
			System.out.println("i value inside asset loop+++"+i);
			System.out.println("Asset size inside loop+++"+leftListValueSize);
			Random random1 = new Random();
			int randomValue = random1.nextInt(leftListValueSize);
			leftRltTableValueList[i] = driver.findElement(assetsXpathList[i]).getText();
			driver.findElement(assetsXpathList[i]).click();
			searchTxtBoxInnerRltTableList.get(i+i).sendKeys(leftRltTableValueList[i]);
			thread();
			assetLeftRltTableMoveArrowList.get(i).click();
			//leftRltWindowMoveAllArrowList.get(i).click();
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
		click(submitButtonRelationShipWindow);
		waitForElement(msgNotificationBar);
		getObjectText(msgNotificationBar);
		takeScreenshot();
		verifyAssertEquals(businessFunctionName+" "+"successfully mapped to Insurance", getActualObjectTxt);
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
			System.out.println("i value inside insurance loop+++"+leftValueListSize);
			Random random1 = new Random();
			int randomValue = random1.nextInt(leftValueListSize);
			System.out.println("Insurance size inside loop+++"+randomValue);
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
		System.out.println("Tasks value inside relationship section+++"+randomTaskValue);
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
		verifyAssertEquals(businessFunctionName+ " "+"successfully mapped to Tasks", getActualObjectTxt);
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
			System.out.println("i value inside tasks loop+++"+i);
			System.out.println("Tasks size inside loop+++"+leftValueListSize);
			Random random3 = new Random();
			int randomValue = random3.nextInt(leftValueListSize);
			System.out.println("Random value inside task loop+++"+randomValue);
			leftRelationshipTableValue[i] = driver.findElement(tasksXpathList[i]).getText();
			driver.findElement(tasksXpathList[i]).click();
			searchTextBoxInnerRelationTableList.get(i+i).sendKeys(leftRelationshipTableValue[i]);
			LeftMoveBtnInnerRltTableList.get(i).click();
			//leftRelationshipTableMoveAllArrowList.get(i).click();
			searchTextBoxInnerRelationTableList.get(i+i+1).sendKeys(leftRelationshipTableValue[i]);
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
