package Scripts;

/******************************************************************************************************************************
 * 
 * @author Rahul Nair
 * 
 * Test 1: Login to SIB application
 * Test 2: Navigate to Tasks module
 * Test 3: Verify sub module mapping functionality in Employees sub tab
 * Test 4: Verify sub module mapping functionality in Teams sub tab
 * Test 5: Verify sub module mapping functionality in Contacts sub tab
 * Test 6: Verify sub module mapping functionality in Contact Groups sub tab
 * Test 7: Verify sub module mapping functionality in Assets sub tab
 * Test 8: Verify sub module mapping functionality in Asset Groups sub tab
 * Test 9: Verify sub module mapping functionality in Business Functions sub tab
 * Test 10: Verify sub module mapping functionality in Task Groups sub tab
 
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
import static ObjectRepository.Locations.*;
import static ObjectRepository.Tasks.*;
import static ObjectRepository.Insurance.*;

public class Tasks_SubModuleMapping extends Page{
	
	String taskName;
	
	LoginLogout ll = new LoginLogout();
	
	/*@Test(priority = 0)
	void login() throws BiffException, IOException, InterruptedException {
		ll.loginToSIB();
		driver.findElement(By.id("cometchat_hide")).click();
	}*/
	
	@Test(priority = 1)
	void navigateToTasksModule() throws InterruptedException{
		click(tasksInMainMenu);
		thread();
	}
	
	@Test(priority = 2)
	void verifySubModuleMappingEmployees() throws IOException, InterruptedException{
		String employeesDefaultRowCountAfterMapping = "4";
		String[] rightRelationshipTableValue = new String[100];
		String[] leftRelationshipTableValue = new String[100];
		String[] tableValueAfterMappingArray = new String[100];
		String[] flagArray = new String[100];
		String flag;
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
		String empNameAfterMapping = driver.findElement(By.xpath("//table[@id='rel_resources']//tbody[@role='alert']//span")).getText();
		if(empName.contains(empNameAfterMapping)){
			flag="true";
		}else
		{
			flag="false";
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
			int j=1;
			if(i>0){
				leftTabsRelationshipTableList.get(i).click();
				addRemoveButtonList.get(i).click();		
				rightRelationshipTableMoveAllArrowList.get(i).click();
				thread();
			}
			int leftValueListSize = driver.findElements(empLeftInnerRltTableValues).size();
			System.out.println("i value inside employees loop+++"+i);
			System.out.println("Tasks size inside loop+++"+leftValueListSize);
			Random random2 = new Random();
			int randomValue = random2.nextInt(leftValueListSize);
			System.out.println("Random task value inside loop+++"+randomTaskValue);
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


    @Test(priority = 2)
    void verifySubModuleMappingTeams() throws IOException, InterruptedException{
    	String teamsDefaultRowCountAfterMapping = "3";
    	String flag;
    	String[] flagArray = new String[100];
    	String[] leftRltTableValueList = new String[100];
		String[] rightRltTableValueList =new String[100];
		String[] tableValueAfterMappingArray = new String[100];
    	click(teamsTabTasksRltSection);
    	thread();
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
		System.out.println("Team value inside relationship section++++"+randomTeamValue);
		String teamName = driver.findElement(By.xpath("//label[contains(text(),'List of Teams')]/..//button[@class='btn moveall']/../../select//option"+"["+randomTeamValue+"]")).getText();
		driver.findElement(By.xpath("//label[contains(text(),'List of Teams')]/..//button[@class='btn moveall']/../../select//option"+"["+randomTeamValue+"]")).click();
		enterText(teamsLeftSearchTxtBoxRltWindow,teamName);
		click(teamsLeftMoveBtnRltWindow);
		enterText(teamsRightSearchTxtBoxRltWindow,teamName);
		click(submitButtonRelationShipWindow);
		waitForElement(msgNotificationBar);
		getObjectText(msgNotificationBar);
		takeScreenshot();
		verifyAssertEquals(taskName+" "+"successfully mapped to Teams", getActualObjectTxt);
		thread();
		scrollToBottom();
		click(teamsViewBtnRltWindow);
		thread();
		scrollToBottom();
		String teamValueAfterMapping = driver.findElement(By.xpath("//table[@id='rel_resource_grp']//tbody[@role='alert']//span")).getText();
		if(teamName.contains(teamValueAfterMapping)){
			flag="true";
		}else
		{
			flag="false";
		}
		verifyAssertEquals(flag,"true");
		click(teamsAddRemoveBtnInnerRltWindow);
		thread();
		scrollToBottom();
		List<WebElement> addRemoveBtnList = driver.findElements(teamsAddRemoveBtnInnerRltWindow);
		List<WebElement> teamsTabValuesInnerRltWindowList = driver.findElements(teamsTabValuesInnerRltWindow);
		int teamsTabValuesInnerRltWindowListSize = teamsTabValuesInnerRltWindowList.size();
		List<WebElement> teamsSearchTxtBoxinnerRltTableList = driver.findElements(teamsSearchTxtBoxinnerRltTable);
		List<WebElement> teamsSubmitBtnInnerRltTableList = driver.findElements(teamsSubmitBtnInnerRltTable);
		List<WebElement> teamsLeftMoveAllArrowInnerRltTableList = driver.findElements(teamsLeftMoveAllArrowInnerRltTable);
		List<WebElement> teamsRightMoveAllArrowInnerRltTableList = driver.findElements(teamsRightMoveAllArrowInnerRltTable);
		List<WebElement> teamsLeftMoveBtnInnerRltTableLst = driver.findElements(teamsLeftMoveBtnInnerRltTable);
		click(teamsRightMoveAllArrowInnerRltTable);
		thread();
		By teamsXpathList[] = {locationSubTab,employeesSubTab,taskSubTab};
		for(int i=0;i<teamsTabValuesInnerRltWindowListSize;i++){
			if(i>0){
				teamsTabValuesInnerRltWindowList.get(i).click();
				addRemoveBtnList.get(i).click();
				teamsRightMoveAllArrowInnerRltTableList.get(i).click();
				thread();
				
			}
			int leftListValueSize = driver.findElements(teamsLeftInnerRltTableValues).size();
			System.out.println("i value inside teams loop++++"+i);
			System.out.println("Teams size inside loop++++"+leftListValueSize);
			Random random1 = new Random();
			int randomValue = random1.nextInt(leftListValueSize);
			leftRltTableValueList[i] = driver.findElement(teamsXpathList[i]).getText();
			driver.findElement(teamsXpathList[i]).click();
			teamsSearchTxtBoxinnerRltTableList.get(i+i).sendKeys(leftRltTableValueList[i]);
			thread();
			teamsLeftMoveBtnInnerRltTableLst.get(i).click();
			teamsSearchTxtBoxinnerRltTableList.get(i+i+1).sendKeys(leftRltTableValueList[i]);
			thread();
			
			teamsSubmitBtnInnerRltTableList.get(i).click();
			thread();
			tableValueAfterMappingArray[i] = driver.findElements(teamsValueInnerRltTableAfterMapping).get(i).getText();
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
		click(teamsViewBtnRltWindow);
		List<WebElement> rowsAfterMapping = driver.findElements(By.xpath("//table[@id='rel_resource_grp']//tbody[@role='alert']//tr[contains(@class,'tableRow')]"));
		int rowsAfterMappingSize = rowsAfterMapping.size();
		String teamsRowCountAfterMapping = Integer.toString(rowsAfterMappingSize);
		verifyAssertEquals(teamsRowCountAfterMapping,teamsDefaultRowCountAfterMapping);
    }
    
    @Test(priority=3)
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
    	System.out.println("Contacts value inside relationship section++++"+randomContactValue);
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
		verifyAssertEquals(taskName+" "+"successfully mapped to Contacts", getActualObjectTxt);
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
			System.out.println("i value inside contacts loop++++"+i);
			System.out.println("Contacts size inside loop+++"+leftListValueSize);
			Random random1 = new Random();
			int randomValue = random1.nextInt(leftListValueSize);
			System.out.println("Random contact value inside contacts loop++++"+randomValue);
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
    
    
   @Test(priority=4)
   void verifySubModuleMappingContactGroups() throws IOException, InterruptedException{
	   String contactGroupsDefaultRowCountAfterMapping = "2";
	   String flag;
	   String[] rightRelationshipTableValue = new String[100];
	   String[] leftRelationshipTableValue = new String[100];
	   String[] tableValueAfterMappingArray = new String[100];
	   String[] flagArray = new String[100];
	   click(contactsGroupTabRltSection);
	   thread();
	   click(addRemoveRelationsBtn);
	   click(contactGroupRightMoveAllArrowRltWindow);
	   List<WebElement> contactGroupLeftRltWindowValuesList = driver.findElements(contactGroupLeftRltWindowValues);
	   int contactGroupLeftRltWindowValuesListSize = contactGroupLeftRltWindowValuesList.size();
	   Random random = new Random();
	   int randomContactGroupValue = random.nextInt(contactGroupLeftRltWindowValuesListSize);
	   if(randomContactGroupValue==0){
		   randomContactGroupValue = randomContactGroupValue+1;
	   }
	   System.out.println("Contact group value inside relationship section++++"+randomContactGroupValue);
	   String contactGroupName = driver.findElement(By.xpath("//label[contains(text(),'List of Contact Groups')]/..//button[@class='btn moveall']/../../select//option"+"["+randomContactGroupValue+"]")).getText();
	   driver.findElement(By.xpath("//label[contains(text(),'List of Contact Groups')]/..//button[@class='btn moveall']/../../select//option"+"["+randomContactGroupValue+"]")).click();
	   enterText(contactGroupLeftSearchTxtBoxRltWindow,contactGroupName);
	   thread();
	   click(contactGroupLeftMoveBtnRltWindow);
	   enterText(contactGroupRightSearchTxtBoxRltWindow,contactGroupName);
	   click(submitButtonRelationShipWindow);
		waitForElement(msgNotificationBar);
		getObjectText(msgNotificationBar);
		takeScreenshot();
		verifyAssertEquals(taskName+" "+"successfully mapped to Contact Groups", getActualObjectTxt);
		thread();
		scrollToBottom();
		String contactGroupValueAfterMapping = driver.findElement(By.xpath("//table[@id='rel_contact_grp']//tbody[@role='alert']//tr//td[1]")).getText();
		System.out.println(contactGroupValueAfterMapping);
		if(contactGroupName.contains(contactGroupValueAfterMapping)){
			flag="true";
		}else
		{
			flag="false";
		}
		verifyAssertEquals(flag,"true");
		click(contactGroupViewButtonRltWindow);
		thread();
		scrollToBottom();
		click(contactGroupAddRemoveButtonInnerRltTable);
		thread();
		List<WebElement> contactsAddRemoveButtonInnerRltTableList = driver.findElements(contactGroupAddRemoveButtonInnerRltTable);
		List<WebElement> contactGroupLeftInnerTabValuesList = driver.findElements(contactGroupLeftInnerTabValues);
		int contactGroupLeftInnerTabValuesListSize = contactGroupLeftInnerTabValuesList.size();
		List<WebElement> contactGroupInnerRltTableSearchTxtBoxList = driver.findElements(contactGroupInnerRltTableSearchTxtBox);
		List<WebElement> contactGroupRightMoveAllArrowInnerRltTableList = driver.findElements(contactGroupRightMoveAllArrowInnerRltTable);
		List<WebElement> contactGroupLeftMoveArrowInnerRltTableList = driver.findElements(contactGroupLeftMoveArrowInnerRltTable);
		List<WebElement> contactGroupInnerRltSubmitBtnList = driver.findElements(contactGroupInnerRltSubmitBtn);
		click(contactGroupRightMoveAllArrowInnerRltTable);
        scrollToBottom();
		thread();
		By contactGroupsXpathList[] = {contactsSubTab,taskSubTab};
		for(int i=0;i<contactGroupLeftInnerTabValuesListSize;i++){
			if(i>0){
				contactGroupLeftInnerTabValuesList.get(i).click();
				contactsAddRemoveButtonInnerRltTableList.get(i).click();		
				contactGroupRightMoveAllArrowInnerRltTableList.get(i).click();
				thread();
			}
			int leftValueListSize = driver.findElements(contactGroupLeftInnerRltValues).size();
			System.out.println("i value inside contacts group loop++++"+i);
			System.out.println("Contact group size inside loop++++"+leftValueListSize);
			Random random1 = new Random();
			int randomValue = random1.nextInt(leftValueListSize);
			System.out.println("Contact group random value inside loop++++"+randomValue);
			leftRelationshipTableValue[i] = driver.findElement(contactGroupsXpathList[i]).getText();
			driver.findElement(contactGroupsXpathList[i]).click();
			contactGroupInnerRltTableSearchTxtBoxList.get(i+i).sendKeys(leftRelationshipTableValue[i]);
			thread();
			contactGroupLeftMoveArrowInnerRltTableList.get(i).click();
			contactGroupInnerRltTableSearchTxtBoxList.get(i+i+1).sendKeys(leftRelationshipTableValue[i]);
			thread();
			contactGroupInnerRltSubmitBtnList.get(i).click();
			thread();
			tableValueAfterMappingArray[i] = driver.findElements(contactGroupInnerRltValueAfterMapping).get(i).getText();
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
		click(contactGroupViewButtonRltWindow);
		List<WebElement> rowsAfterMapping = driver.findElements(By.xpath("//table[@id='rel_contact_grp']//tbody[@role='alert']//tr[contains(@class,'tableRow')]"));
		int rowsAfterMappingSize = rowsAfterMapping.size();
		String contactGroupsRowCountAfterMapping = Integer.toString(rowsAfterMappingSize);
		verifyAssertEquals(contactGroupsRowCountAfterMapping,contactGroupsDefaultRowCountAfterMapping);
   }
   
   
   @Test(priority = 5)
	void verifySubModuleMappingAssets() throws IOException, InterruptedException{
	    String assetDefaultRowCountAfterMapping = "6";
		String flag;
		String[] flagArray = new String[100];
		String[] leftRltTableValueList = new String[100];
		String[] rightRltTableValueList =new String[100];
		String[] tableValueAfterMappingArray = new String[100];
		click(assetsTabInsuranceRelationSection);
		thread();
		click(addRemoveRelationsBtn);
		click(rightMoveAllBtnAssetRltWindow);
		List<WebElement> leftAssetRelationWindowValueList = driver.findElements(leftAssetRelationWindowValues);
		int leftAssetRelationWindowValueListSize = leftAssetRelationWindowValueList.size();
		Random random = new Random();
		int assetValue = random.nextInt(leftAssetRelationWindowValueListSize);
		if(assetValue == 0){
			assetValue = assetValue+1;
		}
		System.out.println("Asset value inside relationship section++++"+assetValue);
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
		verifyAssertEquals(taskName+" "+"successfully mapped to Assets", getActualObjectTxt);
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
		By assetsXpathsList[] = {locationSubTab,assetGroupsSubTab,contactsSubTab,insuranceSubTab,taskSubTab,businessFunctionsSubTab};
		for(int i=0;i<assetLefttabsRltTableListSize;i++){
			if(i>0){
				assetLefttabsRltTableList.get(i).click();
				assetAddRemoveBtnInnerRltTableList.get(i).click();
				rightRltWindowMoveAllArrowList.get(i).click();
				thread();
			}
			int leftListValueSize = driver.findElements(assetLeftRltTableValues).size();
			System.out.println("i value inside asset loop++++"+i);
			System.out.println("Asset size inside loop++++"+leftListValueSize);
			Random random1 = new Random();
			int randomValue = random1.nextInt(leftListValueSize);
			System.out.println("Random value inside asset loop++++"+randomValue);
			leftRltTableValueList[i] = driver.findElement(assetsXpathsList[i]).getText();
			driver.findElement(assetsXpathsList[i]).click();
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
   
   @Test(priority = 6)
  	void verifySubModuleMappingAssetGroups() throws IOException, InterruptedException{
	   String assetGroupsDefaultRowCountAfterMapping = "2";
	   String flag;
	   String[] rightRelationshipTableValue = new String[100];
	   String[] leftRelationshipTableValue = new String[100];
	   String[] tableValueAfterMappingArray = new String[100];
	   String[] flagArray = new String[100];
	   click(assetGroupsTabRltSection);
	   thread();
	   click(addRemoveRelationsBtn);
	   click(assetGroupRightMoveAllArrowRltWindow);
	   List<WebElement> assetGroupLeftRltWindowValuesList = driver.findElements(assetGroupLeftRltWindowValues);
	   int assetGroupLeftRltWindowValuesSize = assetGroupLeftRltWindowValuesList.size();
	   Random random = new Random();
	   int assetGroupValue = random.nextInt(assetGroupLeftRltWindowValuesSize);
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
	   thread();
	   List<WebElement> addRemoveButtonList = driver.findElements(assetGroupAddRemoveBtnInnerRltTable);
	   List<WebElement> searchTextBoxInnerRelationTableList = driver.findElements(assetGroupInnerRltTableSearchTxtBox);
	   List<WebElement> leftTabsRelationshipTableList = driver.findElements(assetGroupLeftInnerTabValues);
	   int leftTabsRelationshipTableListSize = leftTabsRelationshipTableList.size();
	   List<WebElement> rightRelationshipTableMoveAllArrowList = driver.findElements(assetGroupRightMoveAllArrowInnerRltTable);
	   List<WebElement> submitButtonList = driver.findElements(assetGroupInnerRltSubmitBtn);
	   List<WebElement> valueAfterMappingList = driver.findElements(assetGroupRltValueAfterMapping);
	   List<WebElement> assetGroupLeftMoveBtnInnerRltTableList = driver.findElements(assetGroupLeftMoveArrowInnerRltTable);
	   click(assetGroupRightMoveAllArrowInnerRltTable);
	   scrollToBottom();
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
			Random random1 = new Random();
			int randomValue = random1.nextInt(leftValueListSize);
			leftRelationshipTableValue[i] = driver.findElement(assetGroupsXpathList[i]).getText();
			driver.findElement(assetGroupsXpathList[i]).click();
			searchTextBoxInnerRelationTableList.get(i+i).sendKeys(leftRelationshipTableValue[i]);
			thread();
			assetGroupLeftMoveBtnInnerRltTableList.get(i).click();
			searchTextBoxInnerRelationTableList.get(i+i+1).sendKeys(leftRelationshipTableValue[i]);
			thread();
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
   
   @Test(priority=7)
	void verifySubModuleMappingBusinessFunctions() throws IOException, InterruptedException{
	    String businessFunctionsDefaultCountAfterMapping = "5";
		String flag;
		String[] leftInnerTableValue = new String[100];
		String[] rightInnerTableValue = new String[100];
		String[] tableValueAfterMapping = new String[100];
		String[] flagArray = new String[100];
		click(businessFntTabInsuranceRltSection);
		thread();
		click(addRemoveRelationsBtn);
		click(rightMoveAllBtnBusinessFtnRltWindow);
		List <WebElement> leftBusinessFntRltWindowValuesList = driver.findElements(leftBusinessFntRltWindowValues);
		int leftBusinessFntRltWindowValuesListSize = leftBusinessFntRltWindowValuesList.size();
		Random random = new Random();
		int leftBusinessFntRltWindowValue = random.nextInt(leftBusinessFntRltWindowValuesListSize);
		if(leftBusinessFntRltWindowValue==0){
			leftBusinessFntRltWindowValue = leftBusinessFntRltWindowValue + 1;
		}
		System.out.println("Business function value inside relationship section++++"+leftBusinessFntRltWindowValue);
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
		verifyAssertEquals(taskName+" "+"successfully mapped to Business Functions", getActualObjectTxt);
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
			System.out.println("i value inside business functions loop++++"+i);
			System.out.println("Business functions size inside loop+++"+leftListValueSize);
			Random random1 = new Random();
			int randomValue = random1.nextInt(leftListValueSize);
			System.out.println("Random value inside business functions loop++++"+randomValue);
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
   
   @Test(priority=8)
   void verifySubModuleMappingTaskGroups() throws IOException, InterruptedException{
	   String taskGroupsDefaultCountAfterMapping = "1";
	   String flag, flag1;
	   click(taskGroupsTabRltSection);
	   click(addRemoveRelationsBtn);
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
	   verifyAssertEquals(taskName+" "+"successfully mapped to Task Groups", getActualObjectTxt);
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
	   System.out.println("Task groups size++++"+leftListValueSize);
	   System.out.println(leftListValueSize);
	   Random random1 = new Random();
	   int randomValue = random1.nextInt(leftListValueSize);
	   String leftInnerTableValue = driver.findElement(taskGroupLeftInnerRltValues).getText();
	   driver.findElement(taskSubTab).click();
	   driver.findElement(taskSubTab).sendKeys(leftInnerTableValue);
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
