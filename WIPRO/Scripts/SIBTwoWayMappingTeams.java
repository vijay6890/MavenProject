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
import static ObjectRepository.TaskGroups.tasksTabRltSection;
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

public class SIBTwoWayMappingTeams extends Page{
	String flag;
	String teamName,teamLeader,teamType,teamLeaderEmail,teamLeaderMobileNumber,teamComments;
	String employeeName,employeeFirstName,employeeSecondName;
	String taskName;
	
LoginLogout ll = new LoginLogout();
	
	@Test(priority=0)
	void login() throws BiffException, IOException, InterruptedException
	{
		ll.loginToSIB();
		waitForPageLoad();
		driver.findElement(By.id("cometchat_hide")).click();
	}
	
	@Test(priority=1)
	void teamsDataMappingValidations() throws InterruptedException, IOException{
		click(teamsInMainMenu);
		thread();
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
		System.out.println("Team name from list+++"+teamName);
		teamLeader = driver.findElement(By.xpath("//table[@id='resourcegroup_table']//tbody[@role='alert']//tr"+"["+teamValue+"]//td[3]")).getText();
		System.out.println("Team Leader from list+++"+teamLeader);
		teamType = driver.findElement(By.xpath("//table[@id='resourcegroup_table']//tbody[@role='alert']//tr"+"["+teamValue+"]//td[4]")).getText();
		System.out.println("Team Type from list+++"+teamType);
		teamLeaderEmail = driver.findElement(By.xpath("//table[@id='resourcegroup_table']//tbody[@role='alert']//tr"+"["+teamValue+"]//td[6]")).getText();
		System.out.println("Team Leader email from list+++"+teamLeaderEmail);
		teamLeaderMobileNumber = driver.findElement(By.xpath("//table[@id='resourcegroup_table']//tbody[@role='alert']//tr"+"["+teamValue+"]//td[7]")).getText();
		System.out.println("Team Leader Mobile from list+++"+teamLeaderMobileNumber);
		teamComments = driver.findElement(By.xpath("//table[@id='resourcegroup_table']//tbody[@role='alert']//tr"+"["+teamValue+"]//td[8]")).getText();
		System.out.println("Team Comments from list+++"+teamComments);
		click(addRemoveRelationsBtn);
		click(rightMoveAllBtnEmpRltWindow);
		List<WebElement> leftEmployeeRelationWindowValueList = driver.findElements(leftEmployeeRelationWindowValues);
		int leftEmployeeRelationWindowValueListSize = leftEmployeeRelationWindowValueList.size();
		Random random1 = new Random();
		int employeeValue = random1.nextInt(leftEmployeeRelationWindowValueListSize);
		if(employeeValue==0){
			employeeValue = employeeValue+ 1;
		}
		System.out.println("Employee value inside relationship section++++"+employeeValue);
		String employeeNameInsideRelationShipWindow = driver.findElement(By.xpath("//label[contains(text(),'List of Employees')]/..//button[@class='btn moveall']/../../select//option"+"["+employeeValue+"]")).getText();
		driver.findElement(By.xpath("//label[contains(text(),'List of Employees')]/..//button[@class='btn moveall']/../../select//option"+"["+employeeValue+"]")).click();
	    enterText(empLeftSearchTxtBoxRltWindow,employeeNameInsideRelationShipWindow);
	    thread();
	    click(leftMoveBtnEmpRltWindow);
	    enterText(empRightSearchTxtBoxRltWindow,employeeNameInsideRelationShipWindow);
	    thread();
	    click(submitButtonRelationShipWindow);
	    waitForElement(msgNotificationBar);
		getObjectText(msgNotificationBar);
		takeScreenshot();
		verifyAssertEquals(teamName+" "+"successfully mapped to Employees", getActualObjectTxt);
		thread();
		scrollToBottom();
		employeeFirstName = driver.findElement(By.xpath("//table[@id='rel_resources']//tbody[@role='alert']//span")).getText();
		employeeSecondName = driver.findElement(By.xpath("//table[@id='rel_resources']//tbody[@role='alert']//tr//td[2]")).getText();
		employeeName = employeeFirstName+" "+employeeSecondName;
		
		click(tasksTabRltSection);
		thread();
		click(addRemoveRelationsBtn);
		click(taskRightMoveAllArrowRltWindow);
		List<WebElement> tasksLeftRltWindowValuesList = driver.findElements(tasksLeftRltWindowValues);
		int tasksLeftRltWindowValuesListSize = tasksLeftRltWindowValuesList.size();
		Random random2 = new Random();
		int randomTaskValue = random2.nextInt(tasksLeftRltWindowValuesListSize);
		if(randomTaskValue==0){
			randomTaskValue = randomTaskValue+1;
		}
		System.out.println("Tasks value inside relationship section++++"+randomTaskValue);
		String taskNameInsideRelationShipWindow = driver.findElement(By.xpath("//label[contains(text(),'List of Tasks')]/..//button[@class='btn moveall']/../../select//option"+"["+randomTaskValue+"]")).getText();
		driver.findElement(By.xpath("//label[contains(text(),'List of Tasks')]/..//button[@class='btn moveall']/../../select//option"+"["+randomTaskValue+"]")).click();
		enterText(tasksLeftSearchTxtBoxRltWindow,taskNameInsideRelationShipWindow);
		thread();
		click(tasksLeftMoveBtnRltWindow);
		enterText(tasksRightSearchTxtBoxRltWindow,taskNameInsideRelationShipWindow);
		thread();
		click(submitButtonRelationShipWindow);
		waitForElement(msgNotificationBar);
		getObjectText(msgNotificationBar);
		takeScreenshot();
		verifyAssertEquals(teamName+ " "+"successfully mapped to Tasks", getActualObjectTxt);
		thread();
		scrollToBottom();
		taskName = driver.findElement(By.xpath("//table[@id='rel_task']//tbody[@role='alert']//span")).getText();
		
		scrollToTop();
		click(employeesInMainMenu);
		thread();
		Select employeesCountDD = new Select(driver.findElement(By.name("resource_table_length")));
		employeesCountDD.selectByVisibleText("100");
		WebElement mappedemployee = driver.findElement(By.xpath("//table[@id='resource_table']//tbody//tr[@data-for='"+employeeName+"']//td[2]"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", mappedemployee);
		mappedemployee.click();
		scrollToBottom();
		click(teamsTabTasksRltSection);
		thread();
		Select teamsInnerCountDD = new Select(driver.findElement(By.name("rel_resourcegroup_length")));
		teamsInnerCountDD.selectByVisibleText("100");
		scrollToBottom();
		validateTeamValues();
		verifyAssertEquals(flag,"true");
		
		scrollToTop();
		click(tasksInMainMenu);
		thread();
		Select tasksCountDD = new Select(driver.findElement(By.name("tasks_table_length")));
		tasksCountDD.selectByVisibleText("100");
		WebElement mappedTask = driver.findElement(By.xpath("//table[@id='tasks_table']//tbody//tr[@data-for='"+taskName+"']//td[2]"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", mappedTask);
		mappedTask.click();
		scrollToBottom();
		click(teamsTabTasksRltSection);
		thread();
		Select teamsInnerCountDD1 = new Select(driver.findElement(By.name("rel_resource_grp_length")));
		teamsInnerCountDD1.selectByVisibleText("100");
		validateTeamValues1();
		verifyAssertEquals(flag,"true");		
	}
		
		
		public String validateTeamValues(){
			String teamLeaderWhenNull;
			List<WebElement> teamsList = driver.findElements(By.xpath("//table[@id='rel_resourcegroup']//tbody//tr"));
			int teamsListSize = teamsList.size();
			for(int i=1;i<=teamsListSize;i++){
				if(driver.findElement(By.xpath("//table[@id='rel_resourcegroup']//tbody//tr["+i+"]//td[1]")).getText().equals(teamName)){
					System.out.println("Team name+++"+driver.findElement(By.xpath("//table[@id='rel_resourcegroup']//tbody//tr["+i+"]//td[1]")).getText());
					if(teamLeader.equals("")){
						teamLeader="Nil";
					}
					verifyAssertEquals(driver.findElement(By.xpath("//table[@id='rel_resourcegroup']//tbody//tr["+i+"]//td[2]")).getText(),teamLeader);
					System.out.println("Team leader+++"+driver.findElement(By.xpath("//table[@id='rel_resourcegroup']//tbody//tr["+i+"]//td[2]")).getText());
					verifyAssertEquals(driver.findElement(By.xpath("//table[@id='rel_resourcegroup']//tbody//tr["+i+"]//td[3]")).getText(),teamType);
					System.out.println("Team Type+++"+driver.findElement(By.xpath("//table[@id='rel_resourcegroup']//tbody//tr["+i+"]//td[3]")).getText());
					verifyAssertEquals(driver.findElement(By.xpath("//table[@id='rel_resourcegroup']//tbody//tr["+i+"]//td[4]")).getText(),teamLeaderEmail);
					verifyAssertEquals(driver.findElement(By.xpath("//table[@id='rel_resourcegroup']//tbody//tr["+i+"]//td[5]")).getText(),teamLeaderMobileNumber);
					System.out.println("Team leader mobile number+++"+driver.findElement(By.xpath("//table[@id='rel_resourcegroup']//tbody//tr["+i+"]//td[5]")).getText());
					verifyAssertEquals(driver.findElement(By.xpath("//table[@id='rel_resourcegroup']//tbody//tr["+i+"]//td[6]")).getText(),teamComments);
					System.out.println("Team comments+++"+driver.findElement(By.xpath("//table[@id='rel_resourcegroup']//tbody//tr["+i+"]//td[6]")).getText());
					System.out.println("Team leader email+++"+driver.findElement(By.xpath("//table[@id='rel_resourcegroup']//tbody//tr["+i+"]//td[4]")).getText());
					flag="true";
				}else
				{
					flag="false";
				}
			}
			return flag;
			
		}
		
		
		public String validateTeamValues1(){
			String teamLeaderWhenNull;
			List<WebElement> teamsList = driver.findElements(By.xpath("//table[@id='rel_resource_grp']//tbody//tr"));
			int teamsListSize = teamsList.size();
			for(int i=1;i<=teamsListSize;i++){
				if(driver.findElement(By.xpath("//table[@id='rel_resource_grp']//tbody//tr["+i+"]//td[1]")).getText().equals(teamName)){
					System.out.println("Team name+++"+driver.findElement(By.xpath("//table[@id='rel_resource_grp']//tbody//tr["+i+"]//td[1]")).getText());
					if(teamLeader.equals("")){
						teamLeader="Nil";
					}
					verifyAssertEquals(driver.findElement(By.xpath("//table[@id='rel_resource_grp']//tbody//tr["+i+"]//td[2]")).getText(),teamLeader);
					System.out.println("Team leader+++"+driver.findElement(By.xpath("//table[@id='rel_resource_grp']//tbody//tr["+i+"]//td[2]")).getText());
					verifyAssertEquals(driver.findElement(By.xpath("//table[@id='rel_resource_grp']//tbody//tr["+i+"]//td[3]")).getText(),teamType);
					System.out.println("Team Type+++"+driver.findElement(By.xpath("//table[@id='rel_resource_grp']//tbody//tr["+i+"]//td[3]")).getText());
					verifyAssertEquals(driver.findElement(By.xpath("//table[@id='rel_resource_grp']//tbody//tr["+i+"]//td[4]")).getText(),teamLeaderEmail);
					verifyAssertEquals(driver.findElement(By.xpath("//table[@id='rel_resource_grp']//tbody//tr["+i+"]//td[5]")).getText(),teamLeaderMobileNumber);
					System.out.println("Team leader mobile number+++"+driver.findElement(By.xpath("//table[@id='rel_resource_grp']//tbody//tr["+i+"]//td[5]")).getText());
					verifyAssertEquals(driver.findElement(By.xpath("//table[@id='rel_resource_grp']//tbody//tr["+i+"]//td[6]")).getText(),teamComments);
					System.out.println("Team comments+++"+driver.findElement(By.xpath("//table[@id='rel_resource_grp']//tbody//tr["+i+"]//td[6]")).getText());
					System.out.println("Team leader email+++"+driver.findElement(By.xpath("//table[@id='rel_resource_grp']//tbody//tr["+i+"]//td[4]")).getText());
					flag="true";
				}else
				{
					flag="false";
				}
			}
			return flag;
			
		}
	
	
	
	

}
