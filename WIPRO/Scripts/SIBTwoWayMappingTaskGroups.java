package Scripts;

import static Config.TakScreenshot.takeScreenshot;
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
import static ObjectRepository.TaskGroups.*;
import static ObjectRepository.Employees.*;
import static ObjectRepository.Tasks.*;
import static ObjectRepository.Threats.*;
import static ObjectRepository.Assets.*;
import static ObjectRepository.Insurance.*;
import static ObjectRepository.BusinessFunctions.*;
import static ObjectRepository.ContactGroups.contactGroupsInMainMenu;
import static ObjectRepository.Tasks.*;
import static ObjectRepository.Teams.*;
import static ObjectRepository.AssetGroups.*;
import static ObjectRepository.Contacts.*;
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

public class SIBTwoWayMappingTaskGroups extends Page{
	String flag;
	String taskGroupName,taskGroupSummary;
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
	void taskGroupsDataMappingValidations() throws InterruptedException, IOException{
		
		click(taskGroupsInMainMenu);
		thread();
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
		System.out.println("Task group name in list view+++"+taskGroupName);
		taskGroupSummary = driver.findElement(By.xpath("//table[@id='task_group_table']//tbody[@role='alert']//tr"+"["+taskGroupValue+"]"+"//td[3]")).getText();
		System.out.println("Task group summary in list view+++"+taskGroupSummary);
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
		String taskNameInRltWindow = driver.findElement(By.xpath("//label[contains(text(),'List of Tasks')]/..//button[@class='btn moveall']/../../select//option"+"["+randomTaskValue+"]")).getText();
		driver.findElement(By.xpath("//label[contains(text(),'List of Tasks')]/..//button[@class='btn moveall']/../../select//option"+"["+randomTaskValue+"]")).click();
		enterText(tasksLeftSearchTxtBoxRltWindow,taskNameInRltWindow);
		thread();
		click(tasksLeftMoveBtnRltWindow);
		enterText(tasksRightSearchTxtBoxRltWindow,taskNameInRltWindow);
		thread();
		click(submitButtonRelationShipWindow);
		waitForElement(msgNotificationBar);
		getObjectText(msgNotificationBar);
		takeScreenshot();
		verifyAssertEquals(taskGroupName+ " "+"successfully mapped to Tasks", getActualObjectTxt);
		thread();
		scrollToBottom();
		taskName = driver.findElement(By.xpath("//table[@id='rel_task']//tbody[@role='alert']//span")).getText();
		click(tasksInMainMenu);
		thread();
		Select tasksCountDD = new Select(driver.findElement(By.name("tasks_table_length")));
		tasksCountDD.selectByVisibleText("100");
		thread();
		WebElement mappedTask = driver.findElement(By.xpath("//table[@id='tasks_table']//tbody//tr[@data-for='"+taskName+"']//td[2]"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", mappedTask);
		mappedTask.click();
		scrollToBottom();
		click(taskGroupsTabRltSection);
		thread();
		Select taskGroupsInnerCountDD = new Select(driver.findElement(By.name("rel_task_grp_length")));
		taskGroupsInnerCountDD.selectByVisibleText("100");
		thread();
		List<WebElement> taskGroupList = driver.findElements(By.xpath("//table[@id='rel_task_grp']//tbody//tr"));
		int taskGroupListSize = taskGroupList.size();
		for(int i=1;i<=taskGroupListSize;i++){
			if(driver.findElement(By.xpath("//table[@id='rel_task_grp']//tbody//tr["+i+"]//td[1]")).getText().equals(taskGroupName)){
				System.out.println("Task group name++"+driver.findElement(By.xpath("//table[@id='rel_task_grp']//tbody//tr["+i+"]//td[1]")).getText());
				verifyAssertEquals(driver.findElement(By.xpath("//table[@id='rel_task_grp']//tbody//tr["+i+"]//td[2]")).getText(),taskGroupSummary);
				System.out.println("Task group summary++"+driver.findElement(By.xpath("//table[@id='rel_task_grp']//tbody//tr["+i+"]//td[2]")).getText());
				flag="true";
			}else
			{
				flag="false";
			}
		}
		
		verifyAssertEquals(flag,"true");
	}

}
