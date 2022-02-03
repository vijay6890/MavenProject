package Scripts;

import java.io.IOException;
import static ObjectRepository.Home.*;
import static ObjectRepository.Alerts.*;
import static ObjectRepository.Employees.*;
import static ObjectRepository.BusinessFunctions.*;
import static ObjectRepository.Teams.*;
import static ObjectRepository.Tasks.*;
import static UIWrappers.UIObjects.*;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import UIWrappers.Page;
import jxl.read.biff.BiffException;

public class Home_Icons extends Page
{
	LoginLogout ll = new LoginLogout();
	
 /*@Test(priority=0)
	void login() throws BiffException, IOException, InterruptedException 
	{
		ll.loginToSIB();
	}   */
	
	@Test(priority=1)
	void homeAlertIcon() throws InterruptedException
	{
		Thread.sleep(1000);
		String hmeAlrtCnt=driver.findElement(alertsCount).getText();
		click(alertsHIcon);
		thread();		
		verifyAssert(alSearchAlerts);
		getObjectText(alAlertsListViewRecsInfo);
		String[] sStr=getActualObjectTxt.split(" ");
		try
		{
		verifyAssertEquals(hmeAlrtCnt,sStr[5]);
		}
		catch(Error e)
		{
			verifyAssertEquals(hmeAlrtCnt,sStr[5].replaceAll(",", ""));
		}
		thread();
		click(homeInMainMenu);
		thread();
		
	}
	
	@Test(priority=2)
	void homeDisasterIcon() throws InterruptedException
	{
		getObjectText(activeDisasterNmeOnHmePge);
		thread();
		String disNameHmePge=getActualObjectTxt;		
		click(disasterAlertHIcon);
		thread();		
		getObjectText(disasterAlertNmeInSumryPge);		
		verifyAssertEquals(disNameHmePge,getActualObjectTxt.split(":")[1].trim());		
		click(homeInMainMenu);
		thread();
		
	}
	
	@Test(priority=3)
	void homeEmployeesIcon() throws InterruptedException
	{
		String hmeEmpCount=driver.findElement(empCount).getText();
		click(employeesHIcon);
		thread();
		verifyAssert(empSearchBox);
		thread();
		getObjectText(empListViewRecsInfo);
		String[] sStr=getActualObjectTxt.split(" ");
		try
		{
		verifyAssertEquals(hmeEmpCount,sStr[5]);
		}
		catch(Error e)
		{
			verifyAssertEquals(hmeEmpCount,sStr[5].replaceAll(",", ""));
		}
		click(homeInMainMenu);
		Thread.sleep(1000);

		
	}
	
	@Test(priority=4)
	void homeBusiFunctionsIcon() throws InterruptedException
	{
		String hmeBsFnCount=driver.findElement(busFnsCount).getText();
		click(businessFunctionsHIcon);
		thread();
		verifyAssert(bsfBusinessFunctionsSearchBox);
		thread();		
		getObjectText(bsfListViewRecsInfo);
		String[] sStr=getActualObjectTxt.split(" ");
		try
		{
			verifyAssertEquals(hmeBsFnCount,sStr[5]);	
		}
		catch(Error e)
		{
		verifyAssertEquals(hmeBsFnCount,sStr[5].replaceAll(",", ""));
		}
		click(homeInMainMenu);
		thread();
		
	}
	
	@Test(priority=5)
	void homeTeamsIcon() throws InterruptedException
	{
		String hmeTeamsCount=driver.findElement(teamsCount).getText();
		click(teamsHIcon);
		Thread.sleep(1000);
		verifyAssert(tmsTeamsSearchBox);
		thread();
		getObjectText(tmsListViewRecsInfo);
		String[] sStr=getActualObjectTxt.split(" ");
		try
		{
		verifyAssertEquals(hmeTeamsCount,sStr[5]);
		}
		catch(Error e)
		{
			verifyAssertEquals(hmeTeamsCount,sStr[5].replaceAll(",", ""));
		}		
		click(homeInMainMenu);
		thread();
		
	}
	
	@Test(priority=6)
	void homeTasksIcon() throws InterruptedException
	{
		String hmeTaskCount=driver.findElement(taskCount).getText();
		Thread.sleep(1000);
		click(tasksHIcon);
		thread();
		verifyAssert(tskTasksSearchBox);
		thread();
		getObjectText(tskNameListViewRecsInfo);
		String[] sStr=getActualObjectTxt.split(" ");
		try
		{
		verifyAssertEquals(hmeTaskCount,sStr[5]);
		}
		catch(Error e)
		{
			verifyAssertEquals(hmeTaskCount,sStr[5].replaceAll(",", ""));
		}
		click(homeInMainMenu);
		thread();
		
	}

}
