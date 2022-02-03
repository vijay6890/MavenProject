package Scripts;

/******************************************************************************************************************************
 * 
 * @author Rahul Nair
 * 
 * Test 1: Login to SIB application
 * Test 2: Navigate to Asset Groups module
 * Test 3: Verify sub module mapping functionality in Assets sub tab
 * Test 3: Verify sub module mapping functionality in Tasks sub tab
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
import static ObjectRepository.AssetGroups.*;

public class AssetGroups_SubModuleMapping extends Page{
	
	String assetGroupName;
	LoginLogout ll = new LoginLogout();
	
	/*@Test(priority = 0)
	void login() throws BiffException, IOException, InterruptedException {
		ll.loginToSIB();
		driver.findElement(By.id("cometchat_hide")).click();
	}*/

	@Test(priority = 1)
	void navigateToAssetGroupsModule() throws InterruptedException {
		click(assetGroupsInMainMenu);
		thread();				
	}
	
	@Test(priority = 2)
	void verifySubModuleMappingAssets() throws IOException, InterruptedException{
	    String assetDefaultRowCountAfterMapping = "6";
		String flag;
		String[] flagArray = new String[100];
		String[] leftRltTableValueList = new String[100];
		String[] rightRltTableValueList =new String[100];
		String[] tableValueAfterMappingArray = new String[100];
		List<WebElement> assetGroupsList = driver.findElements(By.xpath("//table[@id='asset_group_table']//tbody[@role='alert']//tr"));
		int assetGroupsListSize = assetGroupsList.size();
		Random random = new Random();
		int assetGroupValue = random.nextInt(assetGroupsListSize);
		if(assetGroupValue==0){
			assetGroupValue = assetGroupValue+1;
		}
		System.out.println("Random value in asset groups++++"+assetGroupValue);
		driver.findElement(By.xpath("//table[@id='asset_group_table']//tbody[@role='alert']//tr"+"["+assetGroupValue+"]")).click();
		assetGroupName = driver.findElement(By.xpath("//table[@id='asset_group_table']//tbody[@role='alert']//tr["+assetGroupValue+"]")).getAttribute("data-for");
		thread();
		click(assetsTabInsuranceRelationSection);
		click(addRemoveRelationsBtn);
		thread();
		click(rightMoveAllBtnAssetRltWindow);
		List<WebElement> leftAssetRelationWindowValueList = driver.findElements(leftAssetRelationWindowValues);
		int leftAssetRelationWindowValueListSize = leftAssetRelationWindowValueList.size();
		Random random1 = new Random();
		int assetValue = random1.nextInt(leftAssetRelationWindowValueListSize);
		if(assetValue == 0){
			assetValue = assetValue+1;
		}
		System.out.println("Random value in assets relationship section ++++"+assetValue);
		String assetName = driver.findElement(By.xpath("//label[contains(text(),'List of Assets')]/..//button[@class='btn moveall']/../../select//option"+"["+assetValue+"]")).getText();
		driver.findElement(By.xpath("//label[contains(text(),'List of Assets')]/..//button[@class='btn moveall']/../../select//option"+"["+assetValue+"]")).click();
		enterText(assetLeftSearchTxtBoxRltWindow,assetName);
		thread();
		click(leftMoveBtnAssetRltWindow);
		enterText(assetRightSearchTxtBoxRltWindow,assetName);
		click(submitButtonRelationShipWindow);
		waitForElement(msgNotificationBar);
		getObjectText(msgNotificationBar);
		takeScreenshot();
		verifyAssertEquals(assetGroupName+" "+"successfully mapped to Assets", getActualObjectTxt);
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
		thread();
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
		scrollToBottom();
		thread();
		By assetXpathArray[] = {locationSubTab,assetGroupsSubTab,contactsSubTab,insuranceSubTab,taskSubTab,businessFunctionsSubTab};
		for(int i=0;i<assetLefttabsRltTableListSize;i++){
			if(i>0){
				assetLefttabsRltTableList.get(i).click();
				assetAddRemoveBtnInnerRltTableList.get(i).click();
				rightRltWindowMoveAllArrowList.get(i).click();
				thread();
			}
			int leftListValueSize = driver.findElements(assetLeftRltTableValues).size();
			System.out.println("i value +++++"+i);
			System.out.println("Size value inside assets loop +++++"+leftListValueSize);
			Random random2 = new Random();
			int randomValue = random2.nextInt(leftListValueSize);
			System.out.println("Random value inside asset loop +++++"+randomValue);
			leftRltTableValueList[i] = driver.findElement(assetXpathArray[i]).getText();
			driver.findElement(assetXpathArray[i]).click();
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
		System.out.println("Random task value+++++"+randomTaskValue);
		String taskName = driver.findElement(By.xpath("//label[contains(text(),'List of Tasks')]/..//button[@class='btn moveall']/../../select//option"+"["+randomTaskValue+"]")).getText();
		driver.findElement(By.xpath("//label[contains(text(),'List of Tasks')]/..//button[@class='btn moveall']/../../select//option"+"["+randomTaskValue+"]")).click();
		enterText(tasksLeftSearchTxtBoxRltWindow,taskName);
		thread();
		click(tasksLeftMoveBtnRltWindow);
		enterText(tasksRightSearchTxtBoxRltWindow,taskName);
		click(submitButtonRelationShipWindow);
		waitForElement(msgNotificationBar);
		getObjectText(msgNotificationBar);
		takeScreenshot();
		verifyAssertEquals(assetGroupName+ " "+"successfully mapped to Tasks", getActualObjectTxt);
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
		By tasksXpathArray[] = {employeesSubTab,teamsSubTab,assetsSubTab,assetGroupsSubTab,contactsSubTab,contactGroupsSubTab,taskGroupsSubTab,businessFunctionsSubTab};
		for(int i=0;i<leftTabsRelationshipTableListSize;i++){
			
			if(i>0){
				leftTabsRelationshipTableList.get(i).click();
				addRemoveButtonList.get(i).click();		
				rightRelationshipTableMoveAllArrowList.get(i).click();
				thread();
			}
			int leftValueListSize = driver.findElements(tasksLeftInnerRltValues).size();
			System.out.println("i value inside loop+++++"+i);
			System.out.println("Tasks size inside loop+++++"+leftValueListSize);
			Random random3 = new Random();
			int randomValue = random3.nextInt(leftValueListSize);
			System.out.println("Random value inside tasks loop+++++"+randomValue);			
			leftRelationshipTableValue[i] = driver.findElement(tasksXpathArray[i]).getText();
			driver.findElement(tasksXpathArray[i]).click();
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
