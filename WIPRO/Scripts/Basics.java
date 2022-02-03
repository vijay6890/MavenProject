package Scripts;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import jxl.read.biff.BiffException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;



import UIWrappers.Page;
import static Config.TakScreenshot.*;
import static UIWrappers.UIObjects.*;
import static ObjectRepository.LoginPage.*;
import static ObjectRepository.Profile.*;
import static ObjectRepository.Admin.*;
import static ObjectRepository.DRPlan.drManageDRTemp;
import static ObjectRepository.Home.*;
import static ObjectRepository.Locations.msgNotificationBar;

import org.openqa.selenium.support.ui.Select;




/**************************************************************************************************************
 * 
 * @author Sellamuthu
 * 
 * Test 1: Verify the login process with valid/invalid credentials
 * Test 2: Verify the Display of Chat bar in bottom of the page
 * Test 3: Check all chat bar options
 * Test 4: Verify top menu options loaded or not  
 * 
***************************************************************************************************************/

public class Basics extends Page {
	
	LoginLogout ll = new LoginLogout();
	
	String invalidUsrNmValidPwdErr = "A record with the supplied identity could not be found.";
	String validUsrNmInvalidPwdErr = "Supplied credential is invalid.";
	String invalidMailFormatValidPwdErr = "Invalid Access";
	String invalidMailFormatValidPwdErr1 = "Email address standard is incorrect";
    String appPhone;
    String currentTab;
	
	@Test(priority=0)
	void verifyDisplayOfLoginPage() throws Exception 
	{
		
		verifyAssert(username);
         Thread.sleep(25000);
		verifyAssert(forgotPasswordLink);
		 Thread.sleep(25000);
		
	}
	
	@Test(priority=1)
	void loginPageValidation() throws BiffException, IOException, InterruptedException
	{
		getLoginSeetNameFromExcel();
		
		for(int col=1; col<loginVal.getColumns(); col++)
		{
			for(int row=1; row<loginVal.getRows(); )
			{
				enterText(username, loginVal.getCell(col, row).getContents());
				enterText(password, loginVal.getCell(col, row+1).getContents());
				break;
			}
			thread();
			click(loginBtn);
			thread();
			
			if(col == 1)
			{
				//	Invalid User Name & Valid Password
				getObjectText(invCredentialsMsg);
				Assert.assertEquals(getActualObjectTxt, invalidUsrNmValidPwdErr);
			}
			else if(col == 2)
			{
				//	Valid User Name & Invalid Password
				getObjectText(invCredentialsMsg);
				Assert.assertEquals(getActualObjectTxt, validUsrNmInvalidPwdErr);
			}
			else if(col == 3)
			{
				//	Invalid Mail Format & Valid Password
				getObjectText(invMailFormatMsg1);
				Assert.assertEquals(getActualObjectTxt, invalidMailFormatValidPwdErr);
				getObjectText(invMailFormatMsg2);
				Assert.assertEquals(getActualObjectTxt, invalidMailFormatValidPwdErr1);
				
				refreshThePage();
				waitForPageLoad();
			}
			else if(col ==4)
			{
				//	For Valid Credentials
				verifyAssert(homeInMainMenu);
			}	
		}	
	}
	
	@Test(priority=2)
	void displayOfHomePge() throws IOException, BiffException, InterruptedException
	{
		verifyAssert(homeInMainMenu);
		click(homeInMainMenu);
		waitForPageLoad();
		takeScreenshot();
		thread();
		
		try
		{
			verifyAssert(affectedLocationDD);			
		}
		catch(WebDriverException h)
		{
			getObjectText(drTeamTitleTxt);
			verifyAssertEquals("DR Team", getActualObjectTxt);
		}
		
		thread();
	}
	
	
	
	//		DON'T UN COMMENT CHAT FUNC
	@Test(priority=3)
	void displayOfChatBarInBotom() throws IOException, InterruptedException
	{
		verifyAssert(chtBarInBottom);
		takeScreenshot();
		thread();
	}
	
	@Test(priority=4)
	void displayOfChatRoomsWin() throws IOException, InterruptedException
	{
		click(chtRoomsBtnInBtmBr);
		waitForPageLoad();
		
		verifyAssert(chatRoomsWin);
		takeScreenshot();
		thread();
	}
	
	@Test(priority=5)
	void displayOfWhosOnlineWin() throws IOException, InterruptedException
	{
		click(chtNowBtnInBtmBr);
		waitForPageLoad();
		
		verifyAssert(whosOnlineWin);
		takeScreenshot();
		thread();
	}
	
/*	@Test(priority=6)
	void displayOfAnnouncementsWin() throws IOException, InterruptedException
	{
		click(chtAnnouncementsIconInBtmBr);
		waitForPageLoad();
		
		verifyAssert(announcementsWin);
		takeScreenshot();
		thread();
	}*/
	
	@Test(priority=7)
	void displayOfChatOptionsWin() throws IOException, InterruptedException
	{
		click(chtChatOptionsIconInBtmBr);
		waitForPageLoad();
		
		verifyAssert(chatOptionsWin);
		takeScreenshot();
		thread();
	}
	
/*	@Test(priority=8)
	void displayOfChangeThemeWin() throws IOException, InterruptedException
	{
		thread();
		click(chtChangeThemeIconInBtmBr);
		waitForPageLoad();
		
		verifyAssert(changeThemeWin);
		takeScreenshot();
		thread();
	}
	
	@Test(priority=9)
	void displayOfBroadCastWin() throws IOException, InterruptedException
	{
		thread();
		click(chtBroadCastMsgIconInBtmBr);
		waitForPageLoad();
		
		verifyAssert(broadcastMessageWin);
		takeScreenshot();
		click(broadCastWininimizBtn);
		thread();
	}    */
	
	@Test(priority=10)
	void displayOfHelpnManualPge() throws IOException, InterruptedException
	{
		click(helpManualBtn);
		waitForPageLoad();
		
		//	Check Help & Manual Page Title
		getObjectText(helpDashboardTxt);
		verifyAssertEquals("Help Dashboard", getActualObjectTxt);
		
		//DRBCplan Help
		click(stepsformakingyourDRBCPlan);
		thread();
		takeScreenshot();
		getObjectText(helpDashboardTxt);
		verifyAssertEquals("Steps for making your DRBC Plan", getActualObjectTxt);
		webElement(homeBtnInHlpDocPge);
		scrollInnerScrollBar(webelementFrame);
		click(homeBtnInHlpDocPge);
		thread();
				
		//	Components of SIB Help
		click(componentsOfSIB);
		thread();
		takeScreenshot();
		getObjectText(helpDashboardTxt);
		verifyAssertEquals("Components of SIB", getActualObjectTxt);
		webElement(homeBtnInHlpDocPge);
		scrollInnerScrollBar(webelementFrame);
		click(homeBtnInHlpDocPge);
		thread();
		
		//Understanding the Workspace
		
		click(understandingtheWorkspace);
		thread();
		takeScreenshot();
		getObjectText(helpDashboardTxt);
		verifyAssertEquals("Understanding the Workspace", getActualObjectTxt);
		webElement(homeBtnInHlpDocPge);
		scrollInnerScrollBar(webelementFrame);
		click(homeBtnInHlpDocPge);
		thread();
			
		//Order of Data Entry 
		click(orderofDataEntry);
		thread();
		takeScreenshot();
		getObjectText(helpDashboardTxt);
		verifyAssertEquals("Order of Data Entry", getActualObjectTxt);
		webElement(homeBtnInHlpDocPge);
		scrollInnerScrollBar(webelementFrame);
		click(homeBtnInHlpDocPge);
		thread();
			
		//Methods of Data Entry
		click(methodsofDataEntry);
		thread();
		takeScreenshot();
		getObjectText(helpDashboardTxt);
		verifyAssertEquals("Methods of Data Entry", getActualObjectTxt);
		webElement(homeBtnInHlpDocPge);
		scrollInnerScrollBar(webelementFrame);
		click(homeBtnInHlpDocPge);
		thread();
			
		// Mass Edit 
		click(massEdit);
		thread();
		takeScreenshot();
		getObjectText(helpDashboardTxt);
		thread();
		verifyAssertEquals("Mass Edit", getActualObjectTxt);
		webElement(homeBtnInHlpDocPge);
		scrollInnerScrollBar(webelementFrame);
		click(homeBtnInHlpDocPge);
		thread();
			
		// Mass Delete 
		click(massDelete);
		thread();
		takeScreenshot();
		getObjectText(helpDashboardTxt);
		verifyAssertEquals("Mass Delete", getActualObjectTxt);
		webElement(homeBtnInHlpDocPge);
		scrollInnerScrollBar(webelementFrame);
		click(homeBtnInHlpDocPge);
		thread();
			
		//Exporting Data from the Platform
		click(exportingDataFromthePlatform);
		thread();
		takeScreenshot();
		getObjectText(helpDashboardTxt);
		thread();
		verifyAssertEquals("Exporting Data from the Platform", getActualObjectTxt);
		webElement(homeBtnInHlpDocPge);
		scrollInnerScrollBar(webelementFrame);
		click(homeBtnInHlpDocPge);
		thread();
		
		//Understanding Pre Defined Fields & Values
		click(understandingPreDefinedFieldsnValues);
		thread();
		takeScreenshot();
		getObjectText(helpDashboardTxt);
		thread();
		verifyAssertEquals("Understanding Pre Defined Fields & Values", getActualObjectTxt);
		webElement(homeBtnInHlpDocPge);
		scrollInnerScrollBar(webelementFrame);
		click(homeBtnInHlpDocPge);
		thread();
			
		//Relationship Records between Modules
		click(relationshipRecordsBetweenModules);
		thread();
		takeScreenshot();
		getObjectText(helpDashboardTxt);
		thread();
		verifyAssertEquals("Building Relationship for Records between Modules", getActualObjectTxt);
		webElement(homeBtnInHlpDocPge);
		scrollInnerScrollBar(webelementFrame);
		click(homeBtnInHlpDocPge);
		thread();
			
		//Login to Stay in Business Platform
		click(logintoStayinBusinessPlatform);
		thread();
		takeScreenshot();
		getObjectText(helpDashboardTxt);
		verifyAssertEquals("Login to Stay in Business Platform", getActualObjectTxt);
		webElement(homeBtnInHlpDocPge);
		scrollInnerScrollBar(webelementFrame);
		click(homeBtnInHlpDocPge);
		thread();
			
		//Forgot Password / Recover Password 
		click(forgotandRecoverPassword );
		thread();
		takeScreenshot();
		getObjectText(helpDashboardTxt);
		verifyAssertEquals("Forgot Password / Recover Password", getActualObjectTxt);
		webElement(homeBtnInHlpDocPge);
		scrollInnerScrollBar(webelementFrame);
		click(homeBtnInHlpDocPge);
		thread();
			
		//Locations 
		click(locationsh);
		thread();
		takeScreenshot();
		getObjectText(helpDashboardTxt);
		verifyAssertEquals("Locations Help Document", getActualObjectTxt);
		webElement(homeBtnInHlpDocPge);
		scrollInnerScrollBar(webelementFrame);
		click(homeBtnInHlpDocPge);
		thread();
			
		//Threats
		click(Threatsh);
		thread();
		takeScreenshot();
		getObjectText(helpDashboardTxt);
		verifyAssertEquals("Threats Help Document", getActualObjectTxt);
		webElement(homeBtnInHlpDocPge);
		scrollInnerScrollBar(webelementFrame);
		click(homeBtnInHlpDocPge);
		thread();
			
		//Employees
		click(employeesh);
		thread();
		takeScreenshot();
		getObjectText(helpDashboardTxt);
		verifyAssertEquals("Employees Help Document", getActualObjectTxt);
		webElement(homeBtnInHlpDocPge);
		scrollInnerScrollBar(webelementFrame);
		click(homeBtnInHlpDocPge);
		thread();
		
		//Teams
		click(teamsh);
		thread();
		takeScreenshot();
		getObjectText(helpDashboardTxt);
		verifyAssertEquals("Teams Help Document", getActualObjectTxt);
		webElement(homeBtnInHlpDocPge);
		scrollInnerScrollBar(webelementFrame);
		click(homeBtnInHlpDocPge);
		thread();
		
		// Assets
		webElement(assetsh);
		scrollInnerScrollBar(webelementFrame);
		click(assetsh);
		thread();
		takeScreenshot();
		getObjectText(helpDashboardTxt);
		verifyAssertEquals("Assets Help Document", getActualObjectTxt);
		webElement(homeBtnInHlpDocPge);
		scrollInnerScrollBar(webelementFrame);
		click(homeBtnInHlpDocPge);
		
		// Asset Groups
		webElement(assetsh);
		scrollInnerScrollBar(webelementFrame);
		click(assetGrph);
		thread();
		takeScreenshot();
		getObjectText(helpDashboardTxt);
		verifyAssertEquals("Asset Groups Help Document", getActualObjectTxt);
		webElement(homeBtnInHlpDocPge);
		scrollInnerScrollBar(webelementFrame);
		click(homeBtnInHlpDocPge);
		
		// Contacts
		webElement(assetsh);
		scrollInnerScrollBar(webelementFrame);
		webElement(contactsh);
		scrollInnerScrollBar(webelementFrame);
		click(contactsh);
		thread();
		takeScreenshot();
		getObjectText(helpDashboardTxt);
		verifyAssertEquals("Contacts Help Document", getActualObjectTxt);
		webElement(homeBtnInHlpDocPge);
		scrollInnerScrollBar(webelementFrame);
		click(homeBtnInHlpDocPge);
		
		//Contacts Group 
		webElement(assetsh);
		scrollInnerScrollBar(webelementFrame);
		click(contactGroupsh);
		thread();
		takeScreenshot();
		getObjectText(helpDashboardTxt);
		verifyAssertEquals("Contact Groups Help Document", getActualObjectTxt);
		webElement(homeBtnInHlpDocPge);
		scrollInnerScrollBar(webelementFrame);
		click(homeBtnInHlpDocPge);
		
		//Insurance 
		webElement(assetsh);
		scrollInnerScrollBar(webelementFrame);
		webElement(insurancesh);
		scrollInnerScrollBar(webelementFrame);
		click(insurancesh);
		thread();
		takeScreenshot();
		getObjectText(helpDashboardTxt);
		verifyAssertEquals("Insurance Help Document", getActualObjectTxt);
		webElement(homeBtnInHlpDocPge);
		scrollInnerScrollBar(webelementFrame);
		click(homeBtnInHlpDocPge);
		
		//Tasks 
		webElement(assetsh);
		scrollInnerScrollBar(webelementFrame);
		webElement(tasksh);
		scrollInnerScrollBar(webelementFrame);
		click(tasksh);
		thread();
		takeScreenshot();
		getObjectText(helpDashboardTxt);
		verifyAssertEquals("Tasks Help Document", getActualObjectTxt);
		webElement(homeBtnInHlpDocPge);
		scrollInnerScrollBar(webelementFrame);
		click(homeBtnInHlpDocPge);
		
		//Task Groups
		webElement(assetsh);
		scrollInnerScrollBar(webelementFrame);
		click(businessFunctionsh);
		thread();
		takeScreenshot();
		getObjectText(helpDashboardTxt);
		verifyAssertEquals("Business Functions Help Document", getActualObjectTxt);
		webElement(homeBtnInHlpDocPge);
		scrollInnerScrollBar(webelementFrame);
		click(homeBtnInHlpDocPge);
		
		//Threats / DR Plan 
		webElement(assetsh);
		scrollInnerScrollBar(webelementFrame);
		click(threatsDRPlanh);
		thread();
		takeScreenshot();
		getObjectText(helpDashboardTxt);
		verifyAssertEquals("Threats / DR Plan Help Document", getActualObjectTxt);
		webElement(homeBtnInHlpDocPge);
		scrollInnerScrollBar(webelementFrame);
		click(homeBtnInHlpDocPge);
		
		//Steps to Create a BC / DR Plan
		webElement(assetsh);
		scrollInnerScrollBar(webelementFrame);
		click(stepstoCreataBCandDRPlanh);
		thread();
		takeScreenshot();
		getObjectText(helpDashboardTxt);
		verifyAssertEquals("24. Steps to Create a BC / DR Plan", getActualObjectTxt);
		webElement(homeBtnInHlpDocPge);
		scrollInnerScrollBar(webelementFrame);
		click(homeBtnInHlpDocPge);
		thread();
		
		// DR Templates 
		webElement(assetsh);
		scrollInnerScrollBar(webelementFrame);
		webElement(dRTemplatesh);
		scrollInnerScrollBar(webelementFrame);
		click(dRTemplatesh);
		thread();
		takeScreenshot();
		getObjectText(helpDashboardTxt);
		verifyAssertEquals("DR Templates", getActualObjectTxt);
		webElement(homeBtnInHlpDocPge);
		scrollInnerScrollBar(webelementFrame);
		click(homeBtnInHlpDocPge);
		thread();
		
		//Manage Disaster 
		webElement(assetsh);
		scrollInnerScrollBar(webelementFrame);
		click(managedisasterh);
		thread();
		takeScreenshot();
		getObjectText(helpDashboardTxt);
		verifyAssertEquals("Manage Disaster Help Document",getActualObjectTxt);
		webElement(homeBtnInHlpDocPge);
		scrollInnerScrollBar(webelementFrame);
		click(homeBtnInHlpDocPge);
		thread();
		
		//Activating a Threat 
		webElement(assetsh);
		scrollInnerScrollBar(webelementFrame);
		click(activatingaThreat);
		thread();
		takeScreenshot();
		getObjectText(helpDashboardTxt);
		verifyAssertEquals("Activating a Threat",getActualObjectTxt);
		webElement(homeBtnInHlpDocPge);
		scrollInnerScrollBar(webelementFrame);
		click(homeBtnInHlpDocPge);
		thread();
		
		//Declare a Disaster / Activate a BC DR Plan
		webElement(assetsh);
		scrollInnerScrollBar(webelementFrame);
		click(declareaDisasterandActivateaBCDRPlan);
		thread();
		takeScreenshot();
		getObjectText(helpDashboardTxt);
		verifyAssertEquals("Declaring a Disaster / Activating a BC DR Plan",getActualObjectTxt);
		webElement(homeBtnInHlpDocPge);
		scrollInnerScrollBar(webelementFrame);
		click(homeBtnInHlpDocPge);
		thread();
		
	   //Managing Recovery Operations 
		webElement(assetsh);
		scrollInnerScrollBar(webelementFrame);
		click(managingRecoveryOperations);
		thread();
		takeScreenshot();
		getObjectText(helpDashboardTxt);
		verifyAssertEquals("Managing Recovery Operations",getActualObjectTxt);
		webElement(homeBtnInHlpDocPge);
		scrollInnerScrollBar(webelementFrame);
		click(homeBtnInHlpDocPge);
		thread();
		
		//Ending Declared Disaster / Restoration
		webElement(assetsh);
		scrollInnerScrollBar(webelementFrame);
		click(endingDeclaredDisasterandRestoration);
		thread();
		takeScreenshot();
		getObjectText(helpDashboardTxt);
		verifyAssertEquals("Ending Declared Disaster / Restoration",getActualObjectTxt);
		webElement(homeBtnInHlpDocPge);
		scrollInnerScrollBar(webelementFrame);
		click(homeBtnInHlpDocPge);
		thread();
		
		//Alerts
		webElement(assetsh);
		scrollInnerScrollBar(webelementFrame);
		click(alertsh);
		thread();
		takeScreenshot();
		getObjectText(helpDashboardTxt);
		verifyAssertEquals("Alerts",getActualObjectTxt);
		webElement(homeBtnInHlpDocPge);
		scrollInnerScrollBar(webelementFrame);
		click(homeBtnInHlpDocPge);
		thread();
		
		//Incidents  
		webElement(assetsh);
		scrollInnerScrollBar(webelementFrame);
		click(incidentsh);
		thread();
		takeScreenshot();
		getObjectText(helpDashboardTxt);
		verifyAssertEquals("Incidents",getActualObjectTxt);
		webElement(homeBtnInHlpDocPge);
		scrollInnerScrollBar(webelementFrame);
		click(homeBtnInHlpDocPge);
		thread();
		
		//Admin Module
		webElement(assetsh);
		scrollInnerScrollBar(webelementFrame);
		click(adminModuleh);
		thread();
		takeScreenshot();
		getObjectText(helpDashboardTxt);
		verifyAssertEquals("Admin Module",getActualObjectTxt);
		webElement(homeBtnInHlpDocPge);
		scrollInnerScrollBar(webelementFrame);
		click(homeBtnInHlpDocPge);
		thread();
		
		//Managing Roles
		webElement(assetsh);
		scrollInnerScrollBar(webelementFrame);
		click(managingRolesh);
		thread();
		takeScreenshot();
		getObjectText(helpDashboardTxt);
		verifyAssertEquals("Managing Roles",getActualObjectTxt);
		webElement(homeBtnInHlpDocPge);
		scrollInnerScrollBar(webelementFrame);
		click(homeBtnInHlpDocPge);
		thread();
		
		//Managing Permissions
		webElement(managingPermissionsh);
		scrollInnerScrollBar(webelementFrame);
		click(managingPermissionsh);
		thread();
		takeScreenshot();
		getObjectText(helpDashboardTxt);
		verifyAssertEquals("Managing Permissions",getActualObjectTxt);
		webElement(homeBtnInHlpDocPge);
		scrollInnerScrollBar(webelementFrame);
		click(homeBtnInHlpDocPge);
		thread();
		
		// Map Teams & Roles 
		webElement(managingPermissionsh);
		scrollInnerScrollBar(webelementFrame);
		click(mapTeamsandRolesh);
		thread();
		takeScreenshot();
		getObjectText(helpDashboardTxt);
		verifyAssertEquals("Map Teams & Roles",getActualObjectTxt);
		webElement(homeBtnInHlpDocPge);
		scrollInnerScrollBar(webelementFrame);
		click(homeBtnInHlpDocPge);
		thread();
		
		// Add and Modify User Roles  
		webElement(managingPermissionsh);
		scrollInnerScrollBar(webelementFrame);
		click(addAndModifyUserRolesh);
		thread();
		takeScreenshot();
		getObjectText(helpDashboardTxt);
		verifyAssertEquals("Add and Modify User Roles",getActualObjectTxt);
		webElement(homeBtnInHlpDocPge);
		scrollInnerScrollBar(webelementFrame);
		click(homeBtnInHlpDocPge);
		thread();
		
		//Settings
		webElement(managingPermissionsh);
		scrollInnerScrollBar(webelementFrame);
		click(settingsh);
		thread();
		takeScreenshot();
		getObjectText(helpDashboardTxt);
		verifyAssertEquals("Settings",getActualObjectTxt);
		webElement(homeBtnInHlpDocPge);
		scrollInnerScrollBar(webelementFrame);
		click(homeBtnInHlpDocPge);
		thread();
		
		//Emails / SMS Reports 
		webElement(managingPermissionsh);
		scrollInnerScrollBar(webelementFrame);
		click(emailsandSMSReportsh);
		thread();
		takeScreenshot();
		getObjectText(helpDashboardTxt);
		verifyAssertEquals("Emails / SMS Reports",getActualObjectTxt);
		webElement(homeBtnInHlpDocPge);
		scrollInnerScrollBar(webelementFrame);
		click(homeBtnInHlpDocPge);
		thread();
		
		//Chat Monitor 
		webElement(managingPermissionsh);
		scrollInnerScrollBar(webelementFrame);
		click(chatMonitorh);
		thread();
		takeScreenshot();
		getObjectText(helpDashboardTxt);
		verifyAssertEquals("Chat Monitor",getActualObjectTxt);
		webElement(homeBtnInHlpDocPge);
		scrollInnerScrollBar(webelementFrame);
		click(homeBtnInHlpDocPge);
		thread();
		
		//Chat Reports 
		webElement(managingPermissionsh);
		scrollInnerScrollBar(webelementFrame);
		click(chatReportsh);
		thread();
		takeScreenshot();
		getObjectText(helpDashboardTxt);
		verifyAssertEquals("Chat Reports",getActualObjectTxt);
		webElement(homeBtnInHlpDocPge);
		scrollInnerScrollBar(webelementFrame);
		click(homeBtnInHlpDocPge);
		thread();
		
		// Managing User Profile 
		webElement(managingPermissionsh);
		scrollInnerScrollBar(webelementFrame);
		click(managingUserProfileh);
		thread();
		takeScreenshot();
		getObjectText(helpDashboardTxt);
		verifyAssertEquals("Managing User Profile",getActualObjectTxt);
		webElement(homeBtnInHlpDocPge);
		scrollInnerScrollBar(webelementFrame);
		click(homeBtnInHlpDocPge);
		thread();
		
		// Chats Module 
		webElement(managingPermissionsh);
		scrollInnerScrollBar(webelementFrame);
		click(chatsModuleh);
		thread();
		takeScreenshot();
		getObjectText(helpDashboardTxt);
		verifyAssertEquals("Chats Module",getActualObjectTxt);
		webElement(homeBtnInHlpDocPge);
		scrollInnerScrollBar(webelementFrame);
		click(homeBtnInHlpDocPge);
		thread();
		
		// Documents 
		webElement(managingPermissionsh);
		scrollInnerScrollBar(webelementFrame);
		click(Documentsh);
		thread();
		takeScreenshot();
		getObjectText(helpDashboardTxt);
		verifyAssertEquals("Documents",getActualObjectTxt);
		webElement(homeBtnInHlpDocPge);
		scrollInnerScrollBar(webelementFrame);
		click(homeBtnInHlpDocPge);
		thread();
		
		// Logout
		webElement(managingPermissionsh);
		scrollInnerScrollBar(webelementFrame);
		click(logouth);
		thread();
		takeScreenshot();
		getObjectText(helpDashboardTxt);
		verifyAssertEquals("Logout",getActualObjectTxt);
		webElement(homeBtnInHlpDocPge);
		scrollInnerScrollBar(webelementFrame);
		click(homeBtnInHlpDocPge);
		thread();
		
		//Glossary
		webElement(managingPermissionsh);
		scrollInnerScrollBar(webelementFrame);
		click(glossaryh);
		thread();
		takeScreenshot();
		getObjectText(helpDashboardTxt);
		verifyAssertEquals("Glossary",getActualObjectTxt);
		webElement(homeBtnInHlpDocPge);
		scrollInnerScrollBar(webelementFrame);
		click(homeBtnInHlpDocPge);
		thread();
		
		}    
	
	@Test(priority=11)
	void displayOfAdminPge() throws IOException, InterruptedException
	{
		click(adminBtnInTopMnu);
		thread();
		click(adminInTopDD);
		thread();
		verifyAssert(admRolesSearchBox);
		takeScreenshot();
	}
	
	@Test(priority=12)
	void displayOfProfilePge() throws IOException, InterruptedException
	{
		click(clkUserNameBtn);
		thread();
		click(profileInDropDown);
			
		verifyAssert(updateProfilestatus);
		takeScreenshot();
		thread();
	}
	
	@Test(priority=13)
	void displayOfAppDownloadPge() throws IOException, InterruptedException, BiffException
	{
       currentTab=driver.getWindowHandle();
		
       click(appDownloadBtnInTopMnu);
		thread();
		waitForPageLoad();
		
		 ArrayList<String> newTab = new ArrayList<String>(driver.getWindowHandles());
		 newTab.remove(currentTab);
		 // change focus to new tab
		driver.switchTo().window(newTab.get(0));
		thread();
		
		click(By.xpath("//select[@id='country_code']"));
		thread();
		getTotalValuesIndd(appCountryDD);
		Random rc=new Random();
		int rCnt=rc.nextInt(totalDDValCount-2)+2;
		if(rCnt==0 ||rCnt==1)
		{
			rCnt=2;
		}
		//String countryVal=driver.findElement(By.xpath("//select[@id='country_code']/option[103]")).getText();
		String countryVal=driver.findElement(By.xpath("//select[@id='country_code']/option["+rCnt+"]")).getText(); //Random Country
		selectTextFromDropdown(appCountrySelectStatusVal,countryVal);
		enterKeyInKyBord(appCountrySelectStatusVal);
		thread();
		
		//enterText(appMobileNumber,"9962848906");
		//thread();
		
		//Random Mobile Number
		getAppPhoneNoSheetFromExcel();
		Random rrow = new Random();
		int rexRow = rrow.nextInt(app.getRows()-1)+1;
		verifyAssert(appMobileNumber);
		appPhone = app.getCell(0, rexRow).getContents();
		enterText(appMobileNumber, appPhone);
		thread();			
			
		click(googleAppBtn);   //Android
		thread();
		
	//	click(appStoreBtn);    //IOS
	//	thread();
		
		getObjectText(msgNotificationBar);
		thread();
		try
		{
		verifyAssertEquals("App Link has been sent successfully",getActualObjectTxt);
		}
		catch(AssertionError e)
		{
			verifyAssertEquals("App Link has not been sent successfully",getActualObjectTxt);
		}

		driver.close();
		driver.switchTo().window(currentTab);
		thread();
		
		
 /*       String parentwindow = driver.getWindowHandle();
       
		for (String ttlWindowsCnt : driver.getWindowHandles())  
	     {  
			System.out.println("TTL Windows: "+ttlWindowsCnt);
			
			switchToWindow(ttlWindowsCnt);
			takeScreenshot();
			click(By.xpath("//select[@id='country_code']"));
			thread();
			 Select dd = new Select(driver.findElement(By.xpath("//select[@id='country_code']")));
	        // DD.selectByVisibleText("India");
			 dd.selectByValue("+91");
	         enterKey();
			 thread();
	         getAppPhoneNoSheetFromExcel();
			 Random rrow = new Random();
			int rexRow = rrow.nextInt(app.getRows()-1)+1;
//			Phone number 
				verifyAssert(appMobileNumber);
				appPhone = app.getCell(0, rexRow).getContents();
				enterText(appMobileNumber, appPhone);
				thread();
			    click(googleAppBtn);
			    thread();
			    click(appStoreBtn);
				takeScreenshot();
				switchBackFromFrame();
		        thread();
	//     }
		driver.close();
		//driver.switchTo().window(parentwindow);
		driver.switchTo().window(currentTab);
		thread(); */
					
			// getTotalValuesIndd(appCountryDD);
		/*Random rand=new Random();
		int r=rand.nextInt(totalDDValCount-1)+1;
		String countryval=driver.findElement(By.xpath("//select[@id='country_code']/option["+r+"]")).getText();
		selectTextFromDropdown(appCountrySelectStatusVal,countryval);*/
		
		
        
	}
	

	/*@Test(priority=29)
	void logout() throws InterruptedException
	{
		ll.logoutSession();
		thread();
        driver.quit();
	}*/

}
