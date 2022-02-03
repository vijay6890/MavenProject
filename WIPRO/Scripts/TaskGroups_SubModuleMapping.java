package Scripts;

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
/******************************************************************************************************************************
 * 
 * @author Rahul Nair
 * 
 * Test 1: Login to SIB application
 * Test 2: Navigate to Task Groups module
 * Test 3: Verify sub module mapping functionality in Tasks sub tab

 
*******************************************************************************************************************************/

public class TaskGroups_SubModuleMapping extends Page {
	
	String taskGroupName; 
	
    LoginLogout ll = new LoginLogout();
	
	/*@Test(priority = 0)
	void login() throws BiffException, IOException, InterruptedException {
		
		ll.loginToSIB();
		driver.findElement(By.id("cometchat_hide")).click();
	}*/
	
	@Test(priority = 1)
	void navigateToTaskGroupsModule() throws InterruptedException{
		click(taskGroupsInMainMenu);
		thread();
	}
	
	@Test(priority=2)
	void verifySubModuleMappingTasks() throws InterruptedException, IOException{
		String tasksDefaultCountAfterMapping = "8";
		String flag;
		String[] rightRelationshipTableValue = new String[100];
		String[] leftRelationshipTableValue = new String[100];
		String[] tableValueAfterMappingArray = new String[100];
		String[] flagArray = new String[100];
		List<WebElement> taskGroupsList = driver.findElements(By.xpath("//table[@id='task_group_table']//tbody[@role='alert']//tr"));
		int taskGroupsListSize = taskGroupsList.size();
		Random random = new Random();
		int taskGroupValue = random.nextInt(taskGroupsListSize);
		if(taskGroupValue==0){
			taskGroupValue = taskGroupValue+1;
		}
		System.out.println("Task group value inside list view+++"+taskGroupValue);
		driver.findElement(By.xpath("//table[@id='task_group_table']//tbody[@role='alert']//tr"+"["+taskGroupValue+"]")).click();
		taskGroupName = driver.findElement(By.xpath("//table[@id='task_group_table']//tbody[@role='alert']//tr"+"["+taskGroupValue+"]"+"//td[2]")).getText();
		thread();
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
		verifyAssertEquals(taskGroupName+ " "+"successfully mapped to Tasks", getActualObjectTxt);
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
			System.out.println("Task size inside loop+++"+leftValueListSize);
			Random random3 = new Random();
			int randomValue = random3.nextInt(leftValueListSize);
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
