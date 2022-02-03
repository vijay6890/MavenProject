package Scripts;

import static ObjectRepository.Alerts.alAlertTitle;
import static ObjectRepository.BusinessFunctions.businessFunctionsInMainMenu;
import static ObjectRepository.Employees.employeesInMainMenu;
import static ObjectRepository.Insurance.insuranceInMainMenu;
import static ObjectRepository.Locations.locationsInMainMenu;
import static ObjectRepository.Tasks.tasksInMainMenu;
import static ObjectRepository.Teams.teamsInMainMenu;
import static ObjectRepository.Threats.scEdtrCommentsField;
import static ObjectRepository.Threats.scEdtrFrame;
import static ObjectRepository.Locations.*;
import static ObjectRepository.Employees.*;
import static ObjectRepository.Tasks.*;
import static UIWrappers.UIObjects.*;
import static ObjectRepository.BusinessFunctions.*;

import java.io.IOException;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import UIWrappers.Page;
import jxl.read.biff.BiffException;

public class Employee_OtherRelationship extends Page{
	
	String mgrName,altMgrName,teamLeaderName,altTeamLeaderName,insEmpAssigned,businessFntEmpAssigned;
	String taskEmpAssigned,taskAltEmpAssigned,locationStatus,locationName,teamName,teamComments;
	String insuranceName,insuranceComments,taskName,taskComments;
	String businessFntName,businessFntComments;
	
	LoginLogout ll = new LoginLogout();

	@Test(priority = 0)
	void login() throws BiffException, IOException, InterruptedException {
		ll.loginToSIB();
		driver.findElement(By.id("cometchat_hide")).click();
	}
	
	/*@Test(priority=1)
	void navigateToLocations() throws InterruptedException{
		click(locationsInMainMenu);
		thread();
	}
	
	@Test(priority=2)
	void selectManagerAndAlternateManager() throws InterruptedException, BiffException, IOException{
		getEmpOtherRltSheetFromExcel();
		String address = otherRltTab.getCell(0,1).getContents();
		String city = otherRltTab.getCell(1,1).getContents();
		String zipCode = otherRltTab.getCell(2,1).getContents();
		List<WebElement> locationsList = driver.findElements(By.xpath("//table[@id='facilities_table']//tbody[@role='alert']//tr"));
		int locationsListSize = locationsList.size();
		Random random = new Random();
		int locationValue = random.nextInt(locationsListSize);
		if(locationValue==0){
			locationValue = locationValue+1;
		}
		locationName = driver.findElement(By.xpath("//table[@id='facilities_table']//tbody[@role='alert']//tr["+locationValue+"]")).getAttribute("data-for");
		driver.findElement(By.xpath("//table[@id='facilities_table']//tbody[@role='alert']//tr["+locationValue+"]//td[12]//a[@title='Edit']")).click();
		//clicking on the edit button for a random location
		thread();
		
		System.out.println("Location name ++"+locationName);
		driver.findElement(By.id("fac_manager_chzn")).click();
		thread();
		List<WebElement> mgrList = driver.findElements(By.xpath("//li[contains(@id,'fac_manager_chzn_o')]"));
		int mgrListSize = mgrList.size();
		Random random1 = new Random();
		int randomMgrValue = random1.nextInt(mgrListSize);
		if(randomMgrValue==0){
			randomMgrValue = randomMgrValue + 1;
		}
		mgrName = driver.findElement(By.xpath("//li[@id='fac_manager_chzn_o_"+randomMgrValue+"']")).getText();
		driver.findElement(By.xpath("//li[@id='fac_manager_chzn_o_"+randomMgrValue+"']")).click();
		driver.findElement(By.id("fac_alt_manager_chzn")).click();
		thread();
		List<WebElement> altMgrList = driver.findElements(By.xpath("//li[contains(@id,'fac_alt_manager_chzn_o')]"));
		int altMgrListSize = altMgrList.size();
		Random random2 = new Random();
		int randomAltMgrValue = random2.nextInt(altMgrListSize);
		if(randomAltMgrValue==0){
			randomAltMgrValue = randomAltMgrValue + 1;
		}
		altMgrName = driver.findElement(By.xpath("//li[@id='fac_alt_manager_chzn_o_"+randomAltMgrValue+"']")).getText();
		driver.findElement(By.xpath("//li[@id='fac_alt_manager_chzn_o_"+randomAltMgrValue+"']")).click();
		driver.findElement(locAddress).clear();
		enterText(locAddress,address);
		if(driver.findElement(By.xpath("//div[@id='fac_country_chzn']//span")).getText().equals("Select Country")){
			driver.findElement(By.id("fac_country_chzn")).click();
			thread();
			List<WebElement> countryList = driver.findElements(By.xpath("//li[contains(@id,'fac_country_chzn_o_')]"));
			int countryListSize = countryList.size();
			Random random3 = new Random();
			int randomCountryValue = random3.nextInt(countryListSize);
			driver.findElement(By.xpath("//li[contains(@id,'fac_country_chzn_o_"+randomCountryValue+"')]")).click();
			driver.findElement(By.id("fac_state_chzn")).click();
			thread();
			List<WebElement> stateList = driver.findElements(By.xpath("//li[contains(@id,'fac_state_chzn_o_')]"));
			int stateListSize = stateList.size();
			Random random4 = new Random();
			int randomStateValue = random4.nextInt(stateListSize);
			driver.findElement(By.xpath("//li[contains(@id,'fac_state_chzn_o_"+randomStateValue+"')]")).click();
			
		}
		if(driver.findElement(By.xpath("//div[@id='fac_status_chzn']//a//span")).getText().equals("Select Location Status")){
			driver.findElement(By.id("fac_status_chzn")).click();
			thread();
			List<WebElement> locationStatusList = driver.findElements(By.xpath("//li[contains(@id,'fac_status_chzn_o_')]"));
			int locationStatusListSize = locationStatusList.size();
			Random random4 = new Random();
			int statusValue = random4.nextInt(locationStatusListSize);
			driver.findElement(By.xpath("//li[contains(@id,'fac_status_chzn_o_"+statusValue+"')]")).click();
			thread();
			locationStatus = driver.findElement(By.xpath("//div[@id='fac_status_chzn']//a//span")).getText();
		}else{
			
			locationStatus = driver.findElement(By.xpath("//div[@id='fac_status_chzn']//a//span")).getText();
		}
		System.out.println("Location status is"+locationStatus);
		driver.findElement(locCity).clear();
		enterText(locCity,city);
		driver.findElement(locZipCode).clear();
		enterText(locZipCode,zipCode);
		driver.findElement(By.id("fac_submit")).click();
		thread();
	}
	
	@Test(priority=3)
	void navigateToTeams() throws InterruptedException{
		
		click(teamsInMainMenu);
		thread();
	}
	
	@Test(priority=4)
	void selectTeamLeaderAndAlternateTeamLeader() throws InterruptedException, BiffException, IOException{
		getEmpOtherRltSheetFromExcel();
		String teamCommentsText = otherRltTab.getCell(3,1).getContents();
		List<WebElement> teamList = driver.findElements(By.xpath("//table[@id='resourcegroup_table']//tbody[@role='alert']//tr"));
		int teamListSize = teamList.size();
		Random random = new Random();
		int teamValue = random.nextInt(teamListSize);
		if(teamValue==0){
			teamValue = teamValue + 1;
		}
		teamName = driver.findElement(By.xpath("//table[@id='resourcegroup_table']//tbody[@role='alert']//tr["+teamValue+"]")).getAttribute("data-for");
		driver.findElement(By.xpath("//table[@id='resourcegroup_table']//tbody[@role='alert']//tr["+teamValue+"]//td[9]//a[@title='Edit']")).click();
		// clicking edit button for a random team value from the list
		thread();
		driver.findElement(By.id("group_leader_chzn")).click();
		thread();
		List<WebElement> teamLeaderList = driver.findElements(By.xpath("//div[@id='group_leader_chzn']//li[contains(@id,'group_leader_chzn_o')]"));
		int teamLeaderListSize = teamLeaderList.size();
		Random random1 = new Random();
		int randomTeamLeaderValue = random1.nextInt(teamLeaderListSize);
		if(randomTeamLeaderValue==0){
			randomTeamLeaderValue = randomTeamLeaderValue + 1;
		}
		teamLeaderName = driver.findElement(By.xpath("//div[@id='group_leader_chzn']//li[contains(@id,'group_leader_chzn_o_"+randomTeamLeaderValue+"')]")).getText();
		driver.findElement(By.xpath("//div[@id='group_leader_chzn']//li[contains(@id,'group_leader_chzn_o_"+randomTeamLeaderValue+"')]")).click();
		driver.findElement(By.id("alternate_group_leader_chzn")).click();
		thread();
		List<WebElement> altTeamLeaderList = driver.findElements(By.xpath("//div[@id='alternate_group_leader_chzn']//li[contains(@id,'alternate_group_leader_chzn_o')]"));
	    int altTeamLeaderListSize = altTeamLeaderList.size();
	    Random random2 = new Random();
	    int randomAltTeamLeaderValue = random2.nextInt(altTeamLeaderListSize);
	    if(randomAltTeamLeaderValue==0){
	    	randomAltTeamLeaderValue = randomAltTeamLeaderValue +1;
	    }
	    altTeamLeaderName = driver.findElement(By.xpath("//div[@id='alternate_group_leader_chzn']//li[contains(@id,'alternate_group_leader_chzn_o_"+randomAltTeamLeaderValue+"')]")).getText();
	    driver.findElement(By.xpath("//div[@id='alternate_group_leader_chzn']//li[contains(@id,'alternate_group_leader_chzn_o_"+randomAltTeamLeaderValue+"')]")).click();
	    if(driver.findElement(By.xpath("//div[@id='location_chzn']//a//span")).getText().equals("Select Location")){
	       driver.findElement(By.id("location_chzn")).click();
	       thread();
	       List<WebElement> locationList = driver.findElements(By.xpath("//div[@id='location_chzn']//li[contains(@id,'location_chzn_o_')]"));
	       int locationListSize = locationList.size();
	       Random random3 = new Random();
	       int randomLocationValue = random3.nextInt(locationListSize);
	       if(randomLocationValue==0){
	    	   randomLocationValue = randomLocationValue+1;
	       }
	       driver.findElement(By.xpath("//div[@id='location_chzn']//li[contains(@id,'location_chzn_o_"+randomLocationValue+"')]")).click();
	    	
	    }
	    webElement(scEdtrFrame);
		switchToWEFrame(webelementFrame);
		driver.findElement(scEdtrCommentsField).clear();
		enterText(scEdtrCommentsField, teamCommentsText);
		teamComments = driver.findElement(By.xpath("//body[@dir='ltr']")).getText();
		System.out.println("Comments inside text editor"+teamComments);
		switchBackFromFrame();
	    driver.findElement(By.id("rgp_submit")).click();
	    thread();
	}
	
	@Test(priority=5)
	void navigateToInsurance() throws InterruptedException{
		click(insuranceInMainMenu);
		thread();
	}
	
	@Test(priority=6)
	void selectInsuranceEmpAssigned() throws InterruptedException, BiffException, IOException{
		getEmpOtherRltSheetFromExcel();
		String insuranceCommentsText = otherRltTab.getCell(4,1).getContents();
		List<WebElement> insuranceList = driver.findElements(By.xpath("//table[@id='insurance_table']//tbody[@role='alert']//tr"));
		int insuranceListSize = insuranceList.size();
		Random random = new Random();
		int insuranceValue = random.nextInt(insuranceListSize);
		if (insuranceValue == 0) {
			insuranceValue = insuranceValue + 1;
		}
		insuranceName = driver.findElement(By.xpath("//table[@id='insurance_table']//tbody[@role='alert']//tr["+insuranceValue+"]")).getAttribute("data-for");
		driver.findElement(By.xpath("//table[@id='insurance_table']//tbody[@role='alert']//tr["+insuranceValue+"]//td[10]//a[@title='Edit']")).click();
		//clicking on the edit button for a random insurance from the list
		thread();
		driver.findElement(By.id("insur_admin_chzn")).click();
		thread();
		List<WebElement> insEmpList = driver.findElements(By.xpath("//li[contains(@id,'insur_admin_chzn_o')]"));
		int insEmpListSize = insEmpList.size();
		Random random1 = new Random();
		int insEmpValue = random1.nextInt(insEmpListSize);
		if(insEmpValue==0){
			insEmpValue = insEmpValue + 1;
		}
		insEmpAssigned = driver.findElement(By.xpath("//li[contains(@id,'insur_admin_chzn_o_"+insEmpValue+"')]")).getText();
		driver.findElement(By.xpath("//li[contains(@id,'insur_admin_chzn_o_"+insEmpValue+"')]")).click();
		webElement(scEdtrFrame);
		switchToWEFrame(webelementFrame);
		driver.findElement(scEdtrCommentsField).clear();
		enterText(scEdtrCommentsField, insuranceCommentsText);
		insuranceComments = driver.findElement(By.xpath("//body[@dir='ltr']")).getText();
		switchBackFromFrame();
		driver.findElement(By.id("insur_submit")).click();
		thread();
	}
	
	@Test(priority=7)
	void navigateToTasks() throws InterruptedException{
		click(tasksInMainMenu);
		thread();
	}
	
	@Test(priority=8)
	void selectTasksEmpAndAltEmpAssigned() throws InterruptedException, BiffException, IOException{
		getEmpOtherRltSheetFromExcel();
		String taskCmnts = otherRltTab.getCell(5,1).getContents();
		String taskDuration = otherRltTab.getCell(6,1).getContents();
		List<WebElement> tasksList = driver.findElements(By.xpath("//table[@id='tasks_table']//tbody[@role='alert']//tr"));
		int taskListSize = tasksList.size();
		System.out.println("Task list size++"+taskListSize);
		Random random = new Random();
		int randomTaskValue = random.nextInt(taskListSize);
		if(randomTaskValue==0){
			randomTaskValue=randomTaskValue+1;
		}
		taskName = driver.findElement(By.xpath("//table[@id='tasks_table']//tbody[@role='alert']//tr["+randomTaskValue+"]")).getAttribute("data-for");
		driver.findElement(By.xpath("//table[@id='tasks_table']//tbody[@role='alert']//tr["+randomTaskValue+"]//td[9]//a[@title='Edit']")).click();
		// clicking on the the edit button for a random task from the list
		thread();
		driver.findElement(By.id("principal_resource_chzn")).click();
		thread();
		List<WebElement> empList = driver.findElements(By.xpath("//div[@id='principal_resource_chzn']//li[contains(@id,'principal_resource_chzn_o_')]"));
		int empListSize = empList.size();
		Random random1 = new Random();
		int randomEmpValue = random1.nextInt(empListSize);
		if(randomEmpValue==0){
			
			randomEmpValue = randomEmpValue + 1;
		}
		
		taskEmpAssigned = driver.findElement(By.xpath("//div[@id='principal_resource_chzn']//li[contains(@id,'principal_resource_chzn_o_"+randomEmpValue+"')]")).getText();
		driver.findElement(By.xpath("//li[contains(@id,'principal_resource_chzn_o_"+randomEmpValue+"')]")).click();
		driver.findElement(By.id("alternate_resource_chzn")).click();
		thread();
		List<WebElement> altEmpList = driver.findElements(By.xpath("//div[@id='alternate_resource_chzn']//li[contains(@id,'alternate_resource_chzn_o_')]"));
		int altEmpListSize = altEmpList.size();
		Random random2 = new Random();
		int randomAltEmpValue = random2.nextInt(altEmpListSize);
		if(randomAltEmpValue==0){
			randomAltEmpValue = randomAltEmpValue + 1;
		}
		taskAltEmpAssigned = driver.findElement(By.xpath("//div[@id='alternate_resource_chzn']//li[contains(@id,'alternate_resource_chzn_o_"+randomAltEmpValue+"')]")).getText();
		driver.findElement(By.xpath("//div[@id='alternate_resource_chzn']//li[contains(@id,'alternate_resource_chzn_o_"+randomAltEmpValue+"')]")).click();
		driver.findElement(tskDuration).clear();
		enterText(tskDuration,taskDuration);
		webElement(scEdtrFrame);
		switchToWEFrame(webelementFrame);
		driver.findElement(scEdtrCommentsField).clear();
		enterText(scEdtrCommentsField, taskCmnts);
		taskComments = driver.findElement(By.xpath("//body[@dir='ltr']")).getText();
		switchBackFromFrame();
		driver.findElement(By.id("task_submit")).click();
		thread();
	}*/
	
	@Test(priority=9)
	void navigateToBusinessFunctions() throws InterruptedException{
		
		click(businessFunctionsInMainMenu);
		thread();	
	}
	
	@Test(priority=10)
	void selectBusinessFntEmpAssigned() throws InterruptedException, BiffException, IOException{
		getEmpOtherRltSheetFromExcel();
		String lossPerDay = otherRltTab.getCell(7,1).getContents();
		String recoveryTime = otherRltTab.getCell(8,1).getContents();
		String bsnFntCmt =  otherRltTab.getCell(9,1).getContents();
		List<WebElement> businessFunctionsList = driver.findElements(By.xpath("//table[@id='process_table']//tbody[@role='alert']//tr"));
		int businessFunctionsListSize = businessFunctionsList.size();
		Random random = new Random();
		int businessFunctionValue = random.nextInt(businessFunctionsListSize);
		if (businessFunctionValue == 0) {
			
			businessFunctionValue = businessFunctionValue + 1;
		}
		businessFntName = driver.findElement(By.xpath("//table[@id='process_table']//tbody[@role='alert']//tr["+businessFunctionValue+"]")).getAttribute("data-for"); 
		driver.findElement(By.xpath("//table[@id='process_table']//tbody[@role='alert']//tr["+businessFunctionValue+"]//td[9]//a[@title='Edit']")).click();
		//clicking on the edit button for a random business function from the list
		thread();
		driver.findElement(By.id("process_owner_chzn")).click();
		List<WebElement> empList = driver.findElements(By.xpath("//li[contains(@id,'process_owner_chzn_o')]"));
		int empListSize = empList.size();
		Random random1 = new Random();
		int empValue = random1.nextInt(empListSize);
		if(empValue == 0){
			empValue = empValue + 1;
		}
		businessFntEmpAssigned = driver.findElement(By.xpath("//li[contains(@id,'process_owner_chzn_o_"+empValue+"')]")).getText();
		driver.findElement(By.xpath("//li[contains(@id,'process_owner_chzn_o_"+empValue+"')]")).click();
		driver.findElement(bsfLossPerDay).clear();
		enterText(bsfLossPerDay,lossPerDay);
		driver.findElement(bsfRecoveryTime).clear();
		enterText(bsfRecoveryTime,recoveryTime);
		webElement(scEdtrFrame);
		switchToWEFrame(webelementFrame);
		driver.findElement(scEdtrCommentsField).clear();
		enterText(scEdtrCommentsField, bsnFntCmt);
		businessFntComments = driver.findElement(By.xpath("//body[@dir='ltr']")).getText();
		switchBackFromFrame();
		driver.findElement(By.id("process_submit")).click();
		thread();
		
	}
	
	@Test(priority=11)
	void navigateToEmployees() throws InterruptedException{
		click(employeesInMainMenu);
		thread();
		thread();
	}
	
	/*@Test(priority=12)
	void verifyLocManagerAndAltManager() throws InterruptedException{
		String flag = "";
		String flag1="";
		mgrName = mgrName.substring(0, mgrName.indexOf(','));
		String managerName[]= mgrName.split("\\s");
		String firstName = managerName[0];
		String lastName = managerName[1];
		altMgrName = altMgrName.substring(0, altMgrName.indexOf(','));
		String altManager[] = altMgrName.split("\\s");
		String altFirstName = altManager[0];
		String altLastName =  altManager[1];
		enterText(empSearchBox,mgrName);
		thread();
		List<WebElement> empList = driver.findElements(By.xpath("//table[@id='resource_table']//tbody[@role='alert']//tr"));
		int empListSize = empList.size();
		for(int i=1;i<=empListSize;i++){
			if(driver.findElement(By.xpath("//table[@id='resource_table']//tbody[@role='alert']//tr["+i+"]//td[2]")).getText().equals(firstName)){
				if(driver.findElement(By.xpath("//table[@id='resource_table']//tbody[@role='alert']//tr["+i+"]//td[3]")).getText().equals(lastName)){
					driver.findElement(By.xpath("//table[@id='resource_table']//tbody[@role='alert']//tr["+i+"]//td[2]")).click();
					thread();
					// clicking on the employee record
				}
			}
		}
		click(empOtherRltTab);
		thread();
		Select select = new Select(driver.findElement(By.name("rel_other_length")));
		select.selectByVisibleText("100");
		List<WebElement> rltList = driver.findElements(By.xpath("//table[@id='rel_other']//tbody[@role='alert']//tr"));
		int rltListSize = rltList.size();
		for(int i=1;i<=rltListSize;i++){
			if(driver.findElement(By.xpath("//table[@id='rel_other']//tbody[@role='alert']//tr["+i+"]//td[2]")).getText().equals(locationName)){
				WebElement locName = driver.findElement(By.xpath("//table[@id='rel_other']//tbody[@role='alert']//tr["+i+"]//td[2]"));
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", locName);	
				verifyAssertEquals(driver.findElement(By.xpath("//table[@id='rel_other']//tbody[@role='alert']//tr["+i+"]//td[1]")).getText(),"Location");
				verifyAssertEquals(driver.findElement(By.xpath("//table[@id='rel_other']//tbody[@role='alert']//tr["+i+"]//td[4]")).getText(),"Manager");
				verifyAssertEquals(driver.findElement(By.xpath("//table[@id='rel_other']//tbody[@role='alert']//tr["+i+"]//td[3]")).getText(),locationStatus);
				flag = "true";
			}else
			{
				flag = "false";
			}
		}
		
		verifyAssertEquals(flag,"true");
		driver.findElement(empSearchBox).clear();
		enterText(empSearchBox,altMgrName);
		thread();
		List<WebElement> altEmpList = driver.findElements(By.xpath("//table[@id='resource_table']//tbody[@role='alert']//tr"));
		int altEmpListSize = altEmpList.size();
		for(int i=1;i<=altEmpListSize;i++){
			if(driver.findElement(By.xpath("//table[@id='resource_table']//tbody[@role='alert']//tr["+i+"]//td[2]")).getText().equals(altFirstName)){
				if(driver.findElement(By.xpath("//table[@id='resource_table']//tbody[@role='alert']//tr["+i+"]//td[3]")).getText().equals(altLastName)){
					driver.findElement(By.xpath("//table[@id='resource_table']//tbody[@role='alert']//tr["+i+"]//td[2]")).click();
					thread();
					// clicking on the employee record
				}
			}
		}
		click(empOtherRltTab);
		thread();
		Select select1 = new Select(driver.findElement(By.name("rel_other_length")));
		select1.selectByVisibleText("100");
		List<WebElement> altRltList = driver.findElements(By.xpath("//table[@id='rel_other']//tbody[@role='alert']//tr"));
		int altRltListSize = altRltList.size();
		for(int i=1;i<=altRltListSize;i++){
			if(driver.findElement(By.xpath("//table[@id='rel_other']//tbody[@role='alert']//tr["+i+"]//td[2]")).getText().equals(locationName)){
				WebElement altLocName = driver.findElement(By.xpath("//table[@id='rel_other']//tbody[@role='alert']//tr["+i+"]//td[2]"));
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", altLocName);
				verifyAssertEquals(driver.findElement(By.xpath("//table[@id='rel_other']//tbody[@role='alert']//tr["+i+"]//td[1]")).getText(),"Location");
				verifyAssertEquals(driver.findElement(By.xpath("//table[@id='rel_other']//tbody[@role='alert']//tr["+i+"]//td[4]")).getText(),"Alternate Manager");
				verifyAssertEquals(driver.findElement(By.xpath("//table[@id='rel_other']//tbody[@role='alert']//tr["+i+"]//td[3]")).getText(),locationStatus);
				flag1 = "true";
			}else
			{
				flag1 = "false";
			}
		}
		
		verifyAssertEquals(flag1,"true");
		
	}
	
	@Test(priority=13)
	void verifyTeamLeaderAndAltTeamLeader() throws InterruptedException{
		String flag = "";
		String flag1="";
		teamLeaderName = teamLeaderName.substring(0, teamLeaderName.indexOf(','));
		String teamLeader[]= teamLeaderName.split("\\s");
		String firstName = teamLeader[0];
		String lastName = teamLeader[1];
		altTeamLeaderName = altTeamLeaderName.substring(0, altTeamLeaderName.indexOf(','));
		String altTeamLeader[] = altTeamLeaderName.split("\\s");
		String altFirstName = altTeamLeader[0];
		String altLastName =  altTeamLeader[1];
		driver.findElement(empSearchBox).clear();
		enterText(empSearchBox,teamLeaderName);
		List<WebElement> empList = driver.findElements(By.xpath("//table[@id='resource_table']//tbody[@role='alert']//tr"));
		int empListSize = empList.size();
		for(int i=1;i<=empListSize;i++){
			if(driver.findElement(By.xpath("//table[@id='resource_table']//tbody[@role='alert']//tr["+i+"]//td[2]")).getText().equals(firstName)){
				if(driver.findElement(By.xpath("//table[@id='resource_table']//tbody[@role='alert']//tr["+i+"]//td[3]")).getText().equals(lastName)){
					driver.findElement(By.xpath("//table[@id='resource_table']//tbody[@role='alert']//tr["+i+"]//td[2]")).click();
					thread();
					// clicking on the employee record
				}
			}
		}
		click(empOtherRltTab);
		thread();
		Select select = new Select(driver.findElement(By.name("rel_other_length")));
		select.selectByVisibleText("100");
		List<WebElement> rltList = driver.findElements(By.xpath("//table[@id='rel_other']//tbody[@role='alert']//tr"));
		int rltListSize = rltList.size();
		for(int i=1;i<=rltListSize;i++){
			if(driver.findElement(By.xpath("//table[@id='rel_other']//tbody[@role='alert']//tr["+i+"]//td[2]")).getText().equals(teamName)){
				WebElement tmName = driver.findElement(By.xpath("//table[@id='rel_other']//tbody[@role='alert']//tr["+i+"]//td[2]"));
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", tmName);	
				verifyAssertEquals(driver.findElement(By.xpath("//table[@id='rel_other']//tbody[@role='alert']//tr["+i+"]//td[1]")).getText(),"Team");
				verifyAssertEquals(driver.findElement(By.xpath("//table[@id='rel_other']//tbody[@role='alert']//tr["+i+"]//td[4]")).getText(),"Team Leader");
				verifyAssertEquals(driver.findElement(By.xpath("//table[@id='rel_other']//tbody[@role='alert']//tr["+i+"]//td[3]")).getText(),teamComments);
				flag = "true";
			}else
			{
				flag = "false";
			}
		}
		
		verifyAssertEquals(flag,"true");
		driver.findElement(empSearchBox).clear();
		enterText(empSearchBox,altTeamLeaderName);
		List<WebElement> altEmpList = driver.findElements(By.xpath("//table[@id='resource_table']//tbody[@role='alert']//tr"));
		int altEmpListSize = altEmpList.size();
		for(int i=1;i<=altEmpListSize;i++){
			if(driver.findElement(By.xpath("//table[@id='resource_table']//tbody[@role='alert']//tr["+i+"]//td[2]")).getText().equals(altFirstName)){
				if(driver.findElement(By.xpath("//table[@id='resource_table']//tbody[@role='alert']//tr["+i+"]//td[3]")).getText().equals(altLastName)){
					driver.findElement(By.xpath("//table[@id='resource_table']//tbody[@role='alert']//tr["+i+"]//td[2]")).click();
					thread();
					// clicking on the employee record
				}
			}
		}
		click(empOtherRltTab);
		thread();
		Select select1 = new Select(driver.findElement(By.name("rel_other_length")));
		select1.selectByVisibleText("100");
		List<WebElement> altRltList = driver.findElements(By.xpath("//table[@id='rel_other']//tbody[@role='alert']//tr"));
		int altRltListSize = altRltList.size();
		for(int i=1;i<=altRltListSize;i++){
			if(driver.findElement(By.xpath("//table[@id='rel_other']//tbody[@role='alert']//tr["+i+"]//td[2]")).getText().equals(teamName)){
				WebElement team = driver.findElement(By.xpath("//table[@id='rel_other']//tbody[@role='alert']//tr["+i+"]//td[2]"));
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", team);
				verifyAssertEquals(driver.findElement(By.xpath("//table[@id='rel_other']//tbody[@role='alert']//tr["+i+"]//td[1]")).getText(),"Team");
				verifyAssertEquals(driver.findElement(By.xpath("//table[@id='rel_other']//tbody[@role='alert']//tr["+i+"]//td[4]")).getText(),"Alternate Team Leader");
				verifyAssertEquals(driver.findElement(By.xpath("//table[@id='rel_other']//tbody[@role='alert']//tr["+i+"]//td[3]")).getText(),teamComments);
				flag1 = "true";
			}else
			{
				flag1 = "false";
			}
		}
	}
	
	@Test(priority=14)
	void verifyInsEmpAssigned() throws InterruptedException{
		String flag = "";
		insEmpAssigned = insEmpAssigned.substring(0, insEmpAssigned.indexOf(','));
		String insEmployee[]= insEmpAssigned.split("\\s");
		String firstName = insEmployee[0];
		String lastName = insEmployee[1];
		driver.findElement(empSearchBox).clear();
		enterText(empSearchBox,insEmpAssigned);
		List<WebElement> empList = driver.findElements(By.xpath("//table[@id='resource_table']//tbody[@role='alert']//tr"));
		int empListSize = empList.size();
		for(int i=1;i<=empListSize;i++){
			if(driver.findElement(By.xpath("//table[@id='resource_table']//tbody[@role='alert']//tr["+i+"]//td[2]")).getText().equals(firstName)){
				if(driver.findElement(By.xpath("//table[@id='resource_table']//tbody[@role='alert']//tr["+i+"]//td[3]")).getText().equals(lastName)){
					driver.findElement(By.xpath("//table[@id='resource_table']//tbody[@role='alert']//tr["+i+"]//td[2]")).click();
					thread();
					// clicking on the employee record
				}
			}
		}
		click(empOtherRltTab);
		thread();
		Select select = new Select(driver.findElement(By.name("rel_other_length")));
		select.selectByVisibleText("100");
		List<WebElement> rltList = driver.findElements(By.xpath("//table[@id='rel_other']//tbody[@role='alert']//tr"));
		int rltListSize = rltList.size();
		for(int i=1;i<=rltListSize;i++){
			if(driver.findElement(By.xpath("//table[@id='rel_other']//tbody[@role='alert']//tr["+i+"]//td[2]")).getText().equals(insuranceName)){
				WebElement insName = driver.findElement(By.xpath("//table[@id='rel_other']//tbody[@role='alert']//tr["+i+"]//td[2]"));
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", insName);
				verifyAssertEquals(driver.findElement(By.xpath("//table[@id='rel_other']//tbody[@role='alert']//tr["+i+"]//td[1]")).getText(),"Insurance");
				verifyAssertEquals(driver.findElement(By.xpath("//table[@id='rel_other']//tbody[@role='alert']//tr["+i+"]//td[4]")).getText(),"Admin");
				verifyAssertEquals(driver.findElement(By.xpath("//table[@id='rel_other']//tbody[@role='alert']//tr["+i+"]//td[3]")).getText(),insuranceComments);
				flag = "true";
			}else
			{
				flag = "false";
			}
		}
		verifyAssertEquals(flag,"true");
	}
	
	@Test(priority=15)
	void verifyTaskEmpAndAltEmp() throws InterruptedException{
		String flag = "";
		String flag1="";
		taskEmpAssigned = taskEmpAssigned.substring(0, taskEmpAssigned.indexOf(','));
		String taskEmployee[]= taskEmpAssigned.split("\\s");
		String firstName = taskEmployee[0];
		String lastName = taskEmployee[1];
		taskAltEmpAssigned = taskAltEmpAssigned.substring(0,taskAltEmpAssigned.indexOf(','));
		String taskAltEmployee[]= taskAltEmpAssigned.split("\\s");
		String altFirstName = taskAltEmployee[0];
		String altLastName = taskAltEmployee[1];
		driver.findElement(empSearchBox).clear();
		enterText(empSearchBox,taskEmpAssigned);
		List<WebElement> empList = driver.findElements(By.xpath("//table[@id='resource_table']//tbody[@role='alert']//tr"));
		int empListSize = empList.size();
		for(int i=1;i<=empListSize;i++){
			if(driver.findElement(By.xpath("//table[@id='resource_table']//tbody[@role='alert']//tr["+i+"]//td[2]")).getText().equals(firstName)){
				if(driver.findElement(By.xpath("//table[@id='resource_table']//tbody[@role='alert']//tr["+i+"]//td[3]")).getText().equals(lastName)){
					driver.findElement(By.xpath("//table[@id='resource_table']//tbody[@role='alert']//tr["+i+"]//td[2]")).click();
					thread();
					// clicking on the employee record
				}
			}
		}
		click(empOtherRltTab);
		thread();
		Select select = new Select(driver.findElement(By.name("rel_other_length")));
		select.selectByVisibleText("100");
		List<WebElement> rltList = driver.findElements(By.xpath("//table[@id='rel_other']//tbody[@role='alert']//tr"));
		int rltListSize = rltList.size();
		for(int i=1;i<=rltListSize;i++){
			if(driver.findElement(By.xpath("//table[@id='rel_other']//tbody[@role='alert']//tr["+i+"]//td[2]")).getText().equals(taskName)){
				WebElement tskName = driver.findElement(By.xpath("//table[@id='rel_other']//tbody[@role='alert']//tr["+i+"]//td[2]"));
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", tskName);
				verifyAssertEquals(driver.findElement(By.xpath("//table[@id='rel_other']//tbody[@role='alert']//tr["+i+"]//td[1]")).getText(),"Task");
				verifyAssertEquals(driver.findElement(By.xpath("//table[@id='rel_other']//tbody[@role='alert']//tr["+i+"]//td[4]")).getText(),"Primary Employee");
				verifyAssertEquals(driver.findElement(By.xpath("//table[@id='rel_other']//tbody[@role='alert']//tr["+i+"]//td[3]")).getText(),taskComments);
				flag = "true";
			}else
			{
				flag = "false";
			}
		}
		verifyAssertEquals(flag,"true");
		driver.findElement(empSearchBox).clear();
		enterText(empSearchBox,taskAltEmpAssigned);
		List<WebElement> altEmpList = driver.findElements(By.xpath("//table[@id='resource_table']//tbody[@role='alert']//tr"));
		int altEmpListSize = altEmpList.size();
		for(int i=1;i<=altEmpListSize;i++){
			if(driver.findElement(By.xpath("//table[@id='resource_table']//tbody[@role='alert']//tr["+i+"]//td[2]")).getText().equals(altFirstName)){
				if(driver.findElement(By.xpath("//table[@id='resource_table']//tbody[@role='alert']//tr["+i+"]//td[3]")).getText().equals(altLastName)){
					driver.findElement(By.xpath("//table[@id='resource_table']//tbody[@role='alert']//tr["+i+"]//td[2]")).click();
					thread();
					// clicking on the employee record
				}
			}
		}
		click(empOtherRltTab);
		thread();
		Select select1 = new Select(driver.findElement(By.name("rel_other_length")));
		select1.selectByVisibleText("100");
		List<WebElement> altRltList = driver.findElements(By.xpath("//table[@id='rel_other']//tbody[@role='alert']//tr"));
		int altRltListSize = altRltList.size();
		for(int i=1;i<=altRltListSize;i++){
			if(driver.findElement(By.xpath("//table[@id='rel_other']//tbody[@role='alert']//tr["+i+"]//td[2]")).getText().equals(taskName)){
				WebElement tskName = driver.findElement(By.xpath("//table[@id='rel_other']//tbody[@role='alert']//tr["+i+"]//td[2]"));
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", tskName);
				verifyAssertEquals(driver.findElement(By.xpath("//table[@id='rel_other']//tbody[@role='alert']//tr["+i+"]//td[1]")).getText(),"Task");
				verifyAssertEquals(driver.findElement(By.xpath("//table[@id='rel_other']//tbody[@role='alert']//tr["+i+"]//td[4]")).getText(),"Alternate Employee");
				verifyAssertEquals(driver.findElement(By.xpath("//table[@id='rel_other']//tbody[@role='alert']//tr["+i+"]//td[3]")).getText(),taskComments);
				flag1 = "true";
			}else
			{
				flag1 = "false";
			}
		}
		verifyAssertEquals(flag1,"true");
		
	}*/
	
	@Test(priority=16)
	void verifyBusinessFntEmpAssigned() throws InterruptedException{
		String flag = "";
		businessFntEmpAssigned = businessFntEmpAssigned.substring(0, businessFntEmpAssigned.indexOf(','));
		String bsnFntEmployee[]= businessFntEmpAssigned.split("\\s");
		String firstName = bsnFntEmployee[0];
		String lastName = bsnFntEmployee[1];
		driver.findElement(empSearchBox).clear();
		enterText(empSearchBox,businessFntEmpAssigned);
		List<WebElement> empList = driver.findElements(By.xpath("//table[@id='resource_table']//tbody[@role='alert']//tr"));
		int empListSize = empList.size();
		for(int i=1;i<=empListSize;i++){
			if(driver.findElement(By.xpath("//table[@id='resource_table']//tbody[@role='alert']//tr["+i+"]//td[2]")).getText().equals(firstName)){
				if(driver.findElement(By.xpath("//table[@id='resource_table']//tbody[@role='alert']//tr["+i+"]//td[3]")).getText().equals(lastName)){
					driver.findElement(By.xpath("//table[@id='resource_table']//tbody[@role='alert']//tr["+i+"]//td[2]")).click();
					thread();
					// clicking on the employee record
				}
			}
		}
		click(empOtherRltTab);
		thread();
		Select select = new Select(driver.findElement(By.name("rel_other_length")));
		select.selectByVisibleText("100");
		List<WebElement> rltList = driver.findElements(By.xpath("//table[@id='rel_other']//tbody[@role='alert']//tr"));
		int rltListSize = rltList.size();
		for(int i=1;i<=rltListSize;i++){
			if(driver.findElement(By.xpath("//table[@id='rel_other']//tbody[@role='alert']//tr["+i+"]//td[2]")).getText().equals(businessFntName)){
				WebElement businessFntName = driver.findElement(By.xpath("//table[@id='rel_other']//tbody[@role='alert']//tr["+i+"]//td[2]"));
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", businessFntName);
				verifyAssertEquals(driver.findElement(By.xpath("//table[@id='rel_other']//tbody[@role='alert']//tr["+i+"]//td[1]")).getText(),"Business Function");
				verifyAssertEquals(driver.findElement(By.xpath("//table[@id='rel_other']//tbody[@role='alert']//tr["+i+"]//td[4]")).getText(),"Primary Employee");
				verifyAssertEquals(driver.findElement(By.xpath("//table[@id='rel_other']//tbody[@role='alert']//tr["+i+"]//td[3]")).getText(),businessFntComments);
				flag = "true";
			}else
			{
				flag = "false";
			}
		}
		verifyAssertEquals(flag,"true");
	}
	
	
	
	

}
