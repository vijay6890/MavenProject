package Scripts;

import static Config.TakScreenshot.takeScreenshot;
import static ObjectRepository.Insurance.addRemoveRelationsBtn;
import static ObjectRepository.Insurance.assetLeftSearchTxtBoxRltWindow;
import static ObjectRepository.Insurance.assetRightSearchTxtBoxRltWindow;
import static ObjectRepository.Insurance.assetsTabInsuranceRelationSection;
import static ObjectRepository.Insurance.businessFntLeftSearchTextBoxRltWindow;
import static ObjectRepository.Insurance.businessFntRightSearchTextBoxRltWindow;
import static ObjectRepository.Insurance.businessFntTabInsuranceRltSection;
import static ObjectRepository.Insurance.empLeftSearchTxtBoxRltWindow;
import static ObjectRepository.Insurance.empRightSearchTxtBoxRltWindow;
import static ObjectRepository.Insurance.employeesTabInsuranceRelationSection;
import static ObjectRepository.Insurance.leftAssetRelationWindowValues;
import static ObjectRepository.Insurance.leftBusinessFntRltWindowValues;
import static ObjectRepository.Insurance.leftEmployeeRelationWindowValues;
import static ObjectRepository.Insurance.leftMoveBtnAssetRltWindow;
import static ObjectRepository.Insurance.leftMoveBtnBusinessFntRltWindow;
import static ObjectRepository.Insurance.leftMoveBtnEmpRltWindow;
import static ObjectRepository.Insurance.otherLocationsEmployeesOkButton;
import static ObjectRepository.Insurance.rightMoveAllBtnAssetRltWindow;
import static ObjectRepository.Insurance.rightMoveAllBtnBusinessFtnRltWindow;
import static ObjectRepository.Insurance.rightMoveAllBtnEmpRltWindow;
import static ObjectRepository.Insurance.submitButtonRelationShipWindow;
import static ObjectRepository.Locations.locationsInMainMenu;
import static ObjectRepository.Locations.msgNotificationBar;
import static ObjectRepository.Locations.rightMoveAllBtnThreatRltWindow;
import static ObjectRepository.Locations.threatsLeftMoveBtnRltWindow;
import static ObjectRepository.Locations.threatsLeftRltWindowValues;
import static ObjectRepository.Locations.threatsLeftSearchTxtBoxRltWindow;
import static ObjectRepository.Locations.threatsRightSearchTxtBoxRltWindow;
import static ObjectRepository.Locations.threatsTabRelationSection;
import static ObjectRepository.TaskGroups.taskRightMoveAllArrowRltWindow;
import static ObjectRepository.TaskGroups.tasksLeftMoveBtnRltWindow;
import static ObjectRepository.TaskGroups.tasksLeftRltWindowValues;
import static ObjectRepository.TaskGroups.tasksLeftSearchTxtBoxRltWindow;
import static ObjectRepository.TaskGroups.tasksRightSearchTxtBoxRltWindow;
import static ObjectRepository.Tasks.rightMoveAllArrowTeamsRltWindow;
import static ObjectRepository.Tasks.teamsLeftMoveBtnRltWindow;
import static ObjectRepository.Tasks.teamsLeftRltWindowValues;
import static ObjectRepository.Tasks.teamsLeftSearchTxtBoxRltWindow;
import static ObjectRepository.Tasks.teamsRightSearchTxtBoxRltWindow;
import static ObjectRepository.Tasks.teamsTabTasksRltSection;
import static ObjectRepository.Employees.*;
import static ObjectRepository.Threats.*;
import static ObjectRepository.Assets.*;
import static ObjectRepository.Insurance.*;
import static ObjectRepository.BusinessFunctions.*;
import static ObjectRepository.Tasks.*;
import static ObjectRepository.Teams.*;
import static UIWrappers.UIObjects.click;
import static UIWrappers.UIObjects.enterText;
import static UIWrappers.UIObjects.getActualObjectTxt;
import static UIWrappers.UIObjects.getObjectText;
import static UIWrappers.UIObjects.scrollToBottom;
import static UIWrappers.UIObjects.thread;
import static UIWrappers.UIObjects.verifyAssertEquals;
import static UIWrappers.UIObjects.waitForElement;
import static UIWrappers.UIObjects.waitForPageLoad;
import static UIWrappers.UIObjects.verifyAssertEquals;
import static UIWrappers.UIObjects.scrollToTop;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import UIWrappers.Page;
import jxl.read.biff.BiffException;

public class EmployeesNestedRelationshipTableMapping extends Page{
	String flag,flag1,flag2,flag3,flag4,flag5;
	String taskName,taskID,taskType,taskDuration,taskStatus,taskComments,taskPrimaryEmpName,taskAlternateEmpName;
	String businessFunctionName,businessFunctionEmpAssigned;
	String locationName,locationMgrName;
	String insuranceName,insuranceEmpAssigned;
	String teamName,teamLeaderName;
	
	
    LoginLogout ll = new LoginLogout();
	
	@Test(priority=0)
	void login() throws BiffException, IOException, InterruptedException
	{
		ll.loginToSIB();
		waitForPageLoad();
		driver.findElement(By.id("cometchat_hide")).click();
	}
	
	@Test(priority=1)
	void employeeNestedTableMappingValidations() throws InterruptedException, IOException{
		click(tasksInMainMenu);
		thread();
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
		driver.findElement(By.xpath("//table[@id='tasks_table']//tbody[@role='alert']//tr["+randomTaskValue+"]//td//a[@title='Edit']")).click();
		thread();
		List<WebElement> taskPrimaryEmpList = driver.findElements(By.xpath("//div[@id='principal_resource_chzn']//ul//li"));
		int taskPrimaryEmpListSize = taskPrimaryEmpList.size();
		Random random1 = new Random();
		int taskPrimaryEmpNbr = random1.nextInt(taskPrimaryEmpListSize);
		if(taskPrimaryEmpNbr==0){
			taskPrimaryEmpNbr = taskPrimaryEmpNbr+1;
		}
		driver.findElement(By.xpath("//div[@id='principal_resource_chzn']")).click();
		thread();
		driver.findElement(By.xpath("//div[@id='principal_resource_chzn']//ul//li[@id='principal_resource_chzn_o_"+taskPrimaryEmpNbr+"']")).click();
		taskPrimaryEmpName = driver.findElement(By.xpath("//div[@id='principal_resource_chzn']//a//span")).getText().split(",")[0];;
		List<WebElement> taskAlternateEmpList = driver.findElements(By.xpath("//div[@id='alternate_resource_chzn']//ul//li"));
		int taskAlternateEmpListSize = taskAlternateEmpList.size();
		Random random2 = new Random();
		int taskAlternateEmpNbr = random2.nextInt(taskAlternateEmpListSize);
		if(taskAlternateEmpNbr==0){
			taskAlternateEmpNbr = taskAlternateEmpNbr+1;
		}
		driver.findElement(By.xpath("//div[@id='alternate_resource_chzn']")).click();
		thread();
		driver.findElement(By.xpath("//div[@id='alternate_resource_chzn']//ul//li[@id='alternate_resource_chzn_o_"+taskAlternateEmpNbr+"']")).click();
		taskAlternateEmpName = driver.findElement(By.xpath("//div[@id='alternate_resource_chzn']//a//span")).getText().split(",")[0];;
		thread();
		WebElement tasksSubmitButton = driver.findElement(By.id("task_submit"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", tasksSubmitButton);
		tasksSubmitButton.click();
		thread();
		System.out.println("Primary Employee assigned to task++"+taskPrimaryEmpName);
		System.out.println("Alternate Employee assigned to task++"+taskAlternateEmpName);
		
		click(businessFunctionsInMainMenu);
		thread();
		List<WebElement> businessFunctionsList = driver
				.findElements(By.xpath("//table[@id='process_table']//tbody[@role='alert']//tr"));
		int businessFunctionsListSize = businessFunctionsList.size();
		Random businessFunction = new Random();
		int businessFunctionValue = businessFunction.nextInt(businessFunctionsListSize);
		if (businessFunctionValue == 0) {
			businessFunctionValue = businessFunctionValue + 1;
		}
		System.out.println("Business function random value from list view++"+businessFunctionValue);
		driver.findElement(By.xpath("//table[@id='process_table']//tbody[@role='alert']//tr" + "[" +businessFunctionValue +"]")).click();
		businessFunctionName = driver.findElement(By.xpath("//table[@id='process_table']//tbody[@role='alert']//tr[" + businessFunctionValue + "]//td[2]")).getText();
		driver.findElement(By.xpath("//table[@id='process_table']//tbody[@role='alert']//tr["+businessFunctionValue+"]//td[9]//a[@title='Edit']")).click();
		thread();
		List<WebElement> businessFntEmpList = driver.findElements(By.xpath("//div[@id='process_owner_chzn']//ul//li"));
		int businessFntEmpListSize = businessFntEmpList.size();
		Random random3 = new Random();
		int businessFntEmpNbr = random3.nextInt(businessFntEmpListSize);
		if(businessFntEmpNbr==0){
			businessFntEmpNbr = businessFntEmpNbr+1;
		}
		driver.findElement(By.xpath("//div[@id='process_owner_chzn']")).click();
		thread();
		driver.findElement(By.xpath("//div[@id='process_owner_chzn']//ul//li[@id='process_owner_chzn_o_"+businessFntEmpNbr+"']")).click();
		businessFunctionEmpAssigned = driver.findElement(By.xpath("//div[@id='process_owner_chzn']//a//span")).getText().split(",")[0];
		WebElement businessFntSubmitButton = driver.findElement(By.id("process_submit"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", businessFntSubmitButton);
		businessFntSubmitButton.click();
		thread();
		System.out.println("Employee assigned to business function++"+businessFunctionEmpAssigned);
		
		click(locationsInMainMenu);
		thread();
		List<WebElement> locationsList = driver.findElements(By.xpath("//table[@id='facilities_table']//tbody[@role='alert']//tr"));
		int locationsListSize = locationsList.size();
		Random random6 = new Random();
		int locationValue = random6.nextInt(locationsListSize);
		if(locationValue==0){
			locationValue = locationValue+1;
		}
		System.out.println("Location value inside list view+++"+locationValue);
		driver.findElement(By.xpath("//table[@id='facilities_table']//tbody[@role='alert']//tr"+"["+locationValue+"]")).click();
		locationName = driver.findElement(By.xpath("//table[@id='facilities_table']//tbody[@role='alert']//tr["+locationValue+"]"+"//td[2]")).getAttribute("title");
		driver.findElement(By.xpath("//table[@id='facilities_table']//tbody[@role='alert']//tr["+locationValue+"]//td[12]//a[@title='Edit']")).click();
		thread();
		List<WebElement> locationMgrList = driver.findElements(By.xpath("//div[@id='fac_manager_chzn']//ul//li"));
		int locationMgrListSize = locationMgrList.size();
		Random random7 = new Random();
		int locationMgrNbr = random7.nextInt(locationMgrListSize);
		if(locationMgrNbr==0){
			locationMgrNbr = locationMgrNbr+1;
		}
		driver.findElement(By.id("fac_manager_chzn")).click();
		thread();
		driver.findElement(By.xpath("//div[@id='fac_manager_chzn']//ul//li[@id='fac_manager_chzn_o_"+locationMgrNbr+"']")).click();
		locationMgrName = driver.findElement(By.xpath("//div[@id='fac_manager_chzn']//a//span")).getText().split(",")[0];
		WebElement locationsSubmitButton = driver.findElement(By.id("fac_submit"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", locationsSubmitButton);
		locationsSubmitButton.click();
		thread();
		System.out.println("Employee assigned as manager to location++"+locationMgrName);
		
		click(insuranceInMainMenu);
		thread();
		List<WebElement> insuranceList = driver.findElements(By.xpath("//table[@id='insurance_table']//tbody[@role='alert']//tr"));
		int insuranceListSize = insuranceList.size();
		Random insurance = new Random();
		int insuranceValue = insurance.nextInt(insuranceListSize);
		if (insuranceValue == 0) {
			insuranceValue = insuranceValue + 1;
		}
		System.out.println("Insurance value inside list view+++"+insuranceValue);
		driver.findElement(By.xpath("//table[@id='insurance_table']//tbody[@role='alert']//tr" + "[" + insuranceValue + "]")).click();
		insuranceName = driver.findElement(By.xpath("//table[@id='insurance_table']//tbody[@role='alert']//tr[" + insuranceValue + "]//td[2]")).getText();
		driver.findElement(By.xpath("//table[@id='insurance_table']//tbody[@role='alert']//tr["+insuranceValue+"]//td//a[@title='Edit']")).click();
		thread();
		List<WebElement> insuranceEmpList = driver.findElements(By.xpath("//div[@id='insur_admin_chzn']//ul//li"));
		int insuranceEmpListSize = insuranceEmpList.size();
		Random random8 = new Random();
		int insuranceEmpNbr = random8.nextInt(insuranceEmpListSize);
		if(insuranceEmpNbr==0){
			insuranceEmpNbr = insuranceEmpNbr+1;
		}
		driver.findElement(By.id("insur_admin_chzn")).click();
		thread();
		driver.findElement(By.xpath("//div[@id='insur_admin_chzn']//ul//li[@id='insur_admin_chzn_o_"+insuranceEmpNbr+"']")).click();
		insuranceEmpAssigned = driver.findElement(By.xpath("//div[@id='insur_admin_chzn']//a//span")).getText().split(",")[0];
		WebElement insuranceSubmitButton = driver.findElement(By.id("insur_submit"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", insuranceSubmitButton);
		insuranceSubmitButton.click();
		thread();
		System.out.println("Employee assigned as insurance admin++"+insuranceEmpAssigned);
		
		click(teamsInMainMenu);
		thread();
		List<WebElement> teamsList = driver.findElements(By.xpath("//table[@id='resourcegroup_table']//tbody[@role='alert']//tr"));
		int teamsListSize = teamsList.size();
		Random random9 = new Random();
		int teamValue = random9.nextInt(teamsListSize);
		if(teamValue==0){
			teamValue = teamValue+1;
		}
		System.out.println("Team value inside list view++++"+teamValue);
		driver.findElement(By.xpath("//table[@id='resourcegroup_table']//tbody[@role='alert']//tr"+"["+teamValue+"]")).click();
		teamName = driver.findElement(By.xpath("//table[@id='resourcegroup_table']//tbody[@role='alert']//tr["+teamValue+"]")).getAttribute("data-for");
		driver.findElement(By.xpath("//table[@id='resourcegroup_table']//tbody[@role='alert']//tr["+teamValue+"]//td//a[@title='Edit']")).click();
		thread();
		List<WebElement> teamsEmpList = driver.findElements(By.xpath("//div[@id='group_leader_chzn']//ul/li"));
		int teamsEmpListSize = teamsEmpList.size();
		Random random10 = new Random();
		int teamEmpNbr = random10.nextInt(teamsEmpListSize);
		if(teamEmpNbr==0){
			teamEmpNbr = teamEmpNbr+1;
		}
		driver.findElement(By.id("group_leader_chzn")).click();
		thread();
		driver.findElement(By.xpath("//div[@id='group_leader_chzn']//li[@id='group_leader_chzn_o_"+teamEmpNbr+"']")).click();
		teamLeaderName = driver.findElement(By.xpath("//div[@id='group_leader_chzn']//a//span")).getText().split(",")[0];
		WebElement teamsSubmitButton = driver.findElement(By.id("rgp_submit"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", teamsSubmitButton);
		teamsSubmitButton.click();
		thread();
		System.out.println("Employee assigned as team leader++"+teamLeaderName);
		
		
		click(employeesInMainMenu);
		thread();
		Select employeeCountDD = new Select(driver.findElement(By.name("resource_table_length")));
		employeeCountDD.selectByVisibleText("100");
		driver.findElement(By.xpath("//table[@id='resource_table']//tbody//tr[@data-for='"+taskPrimaryEmpName+"']")).click();
		thread();
		scrollToBottom();
		List<WebElement> tasksListEmp = driver.findElements(By.xpath("//table[@id='rel_task_primary']//tbody//tr"));
		int tasksListEmpSize = tasksListEmp.size();
		for(int i=1;i<=tasksListEmpSize;i++){
			if(driver.findElement(By.xpath("//table[@id='rel_task_primary']//tbody//tr["+i+"]//span")).getText().equals(taskName)){
				flag="true";
				break;
			}else
			{
				flag="false";
			}
		}
		verifyAssertEquals(flag,"true");
		
		scrollToTop();
		thread();
		driver.findElement(By.xpath("//table[@id='resource_table']//tbody//tr[@data-for='"+taskAlternateEmpName+"']")).click();
		thread();
		scrollToBottom();
		for(int i=1;i<=tasksListEmpSize;i++){
			if(driver.findElement(By.xpath("//table[@id='rel_task_primary']//tbody//tr["+i+"]//span")).getText().equals(taskName)){
				flag1="true";
				break;
			}else
			{
				flag1="false";
			}
		}
		verifyAssertEquals(flag1,"true");
		
		scrollToTop();
		driver.findElement(By.xpath("//table[@id='resource_table']//tbody//tr[@data-for='"+businessFunctionEmpAssigned+"']")).click();
		thread();
		scrollToBottom();
		click(businessFntTabInsuranceRltSection);
		thread();
		List<WebElement> businessFntList = driver.findElements(By.xpath("//table[@id='rel_process_primary']//tbody//tr"));
		int businessFntListSize = businessFntList.size();
		for(int i=1;i<=businessFntListSize;i++){
			if(driver.findElement(By.xpath("//table[@id='rel_process_primary']//tbody//tr["+i+"]//span")).getText().equals(businessFunctionName)){
				flag2="true";
				break;
			}else
			{
				flag2="false";
			}
		}
		verifyAssertEquals(flag2,"true");
		
		scrollToTop();
		driver.findElement(By.xpath("//table[@id='resource_table']//tbody//tr[@data-for='"+locationMgrName+"']")).click();
		thread();
		scrollToBottom();
		click(locationsTabRltTable);
		thread();
		List<WebElement> empLocationsList = driver.findElements(By.xpath("//table[@id='rel_facilities_manager']//tbody//tr"));
		int empLocationsListSize = empLocationsList.size();
		for(int i=1;i<=empLocationsListSize;i++){
			if(driver.findElement(By.xpath("//table[@id='rel_facilities_manager']//tbody//tr["+i+"]//td[1]")).getText().equals(locationName)){
				flag3="true";
				break;
			}else
			{
				flag3="false";
			}
		}
		verifyAssertEquals(flag3,"true");
		
		scrollToTop();
		driver.findElement(By.xpath("//table[@id='resource_table']//tbody//tr[@data-for='"+insuranceEmpAssigned+"']")).click();
		thread();
		scrollToBottom();
		click(insuranceTabRltSection);
		thread();
		List<WebElement> empInsuranceList = driver.findElements(By.xpath("//table[@id='rel_insurances_admin']//tbody//tr"));
		int empInsuranceListSize = empInsuranceList.size();
		for(int i=1;i<=empInsuranceListSize;i++){
			if(driver.findElement(By.xpath("//table[@id='rel_insurances_admin']//tbody//tr["+i+"]//span")).getText().equals(insuranceName)){
				flag4="true";
				break;
			}else{
				flag4="false";
			}
		}
		verifyAssertEquals(flag4,"true");
		
		scrollToTop();
		driver.findElement(By.xpath("//table[@id='resource_table']//tbody//tr[@data-for='"+teamLeaderName+"']")).click();
		thread();
		scrollToBottom();
		click(teamsTabTasksRltSection);
		thread();
		List<WebElement> empTeamsList = driver.findElements(By.xpath("//table[@id='rel_resourcegroup_leader']//tbody//tr"));
		int empTeamsListSize = empTeamsList.size();
		for(int i=1;i<=empTeamsListSize;i++){
			if(driver.findElement(By.xpath("//table[@id='rel_resourcegroup_leader']//tbody//tr["+i+"]//span")).getText().equals(teamName)){
				flag5="true";
		        break;
			}else{
				flag5="false";
			}
		}
		verifyAssertEquals(flag5,"true");
	}

}
