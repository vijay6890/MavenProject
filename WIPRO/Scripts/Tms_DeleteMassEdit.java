package Scripts;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import UIWrappers.Page;
import jxl.read.biff.BiffException;

import static UIWrappers.UIObjects.*;
import static ObjectRepository.Teams.*;
import static ObjectRepository.Threats.*;
import static ObjectRepository.Assets.*;
import static Config.TakScreenshot.*;
import static ObjectRepository.Locations.*;

/********************************************************************************************************************
 * 
 * Test 1: Delete Team Name
 * Test 2: Verify Team Name Deleted Successfully message
 * Test 3: Verify 'Select Records to Delete' message
 * Test 4: Team Mass Edit
 * Test 5: Verify Mass Edit updated successfully message
 * Test 6: Verify Select Records to Edit message
 * Test 7: Mass Edit page validation
 * Test 8: Verify 'Cancel' button redirection from Mass Edit page
 * Test 9: Verify Teams Help document
 *
*********************************************************************************************************************/

public class Tms_DeleteMassEdit extends Page{
	
	LoginLogout ll = new LoginLogout();
	
	String teamNameFrDel;
	String teamTypeBfrMedt, locationNameBfrMed;
	String[] teamLeaderBfrMedt;
	String[] locationNameBfrMedt;
	public String teamLeaderName,altTeamLeaderName;
	
	Tms_AddNewTeam tms_AddNewTeam = new Tms_AddNewTeam();
	
  /* @Test(priority=0)
	void login() throws BiffException, IOException, InterruptedException
	{
		ll.loginToSIB();
	} */ 
	
	@Test(priority=1)
	void deleteSingleTeam() throws IOException, InterruptedException, BiffException
	{
		tms_AddNewTeam.addNewTeamDtls();
		thread();
		
		getTotalValuesIndd(tmsTeamsLstViewTtl);
        thread();
        
		Random random = new Random();
		int del = random.nextInt(totalDDValCount-1)+1;
		WebElement delBtn = driver.findElement(By.xpath("//table[@id='resourcegroup_table']/tbody/tr["+del+"]/td[9]/a[2]"));
		
		delBtn.click();
		verifyAssert(deleteConfPopup);
		
		getObjectText(deleteConfPpMessage);
		teamNameFrDel = driver.findElement(By.xpath("//table[@id='resourcegroup_table']/tbody/tr["+del+"]/td[2]")).getText();
		verifyAssertEquals("Confirm to remove Team "+teamNameFrDel+"?", getActualObjectTxt);
		takeScreenshot();
		click(delConfOkBtn);
		thread();
	}
	
	@Test(priority=2)
	void verifyTeamNameDeletedSuccMessage() throws IOException, InterruptedException
	{
		getObjectText(msgNotificationBar);
		verifyAssertEquals(teamNameFrDel+" Is Deleted", getActualObjectTxt);
		takeScreenshot();
		thread();
	}
	
	@Test(priority=3)
	void verifySelectRecordsToDeleteMsg() throws InterruptedException, IOException
	{
		click(tmsOvrDeleteBtn);
		thread();
		getObjectText(msgNotificationBar);
		verifyAssertEquals("Select records to perform delete!", getActualObjectTxt);
		takeScreenshot();
		thread();
	}
	
	@Test(priority=4, invocationCount=2)
	void addNewTeamName() throws BiffException, IOException, InterruptedException
	{
		
		tms_AddNewTeam.addNewTeamDtls();
		thread();
	}
	
	
	@Test(priority=5)
	void deleteMultipleTeamName() throws IOException, InterruptedException
	{
		getTotalValuesIndd(tmsTeamsLstViewTtl);
		
	/*	ArrayList<Integer> list = new ArrayList<Integer>();
	    for (int i=1; i<totalDDValCount; i++) 
	    {
	    	list.add(new Integer(i));
	    }
	    Collections.shuffle(list);
	    thread();
	    for (int i=1; i<3; i++) 
	    {
	    	driver.findElement(By.xpath("//table[@id='resourcegroup_table']/tbody/tr["+list.get(i)+"]/td/div/span/input")).click();
	    	thread();
	    }*/
		
		 for (int i=1; i<3; i++) 
		    {
		    	driver.findElement(By.xpath("//table[@id='resourcegroup_table']/tbody/tr["+i+"]/td/div/span/input")).click();
		    	thread();
		    }
		
		takeScreenshot();
		click(tmsOvrDeleteBtn);
		
		waitForElement(deleteConfPopup);
		getObjectText(deleteConfPpMessage);
		verifyAssertEquals("Confirm to remove 2 record(s) from Teams?", getActualObjectTxt);
		takeScreenshot();
		
		click(delConfOkBtn);
		thread();
		
		getObjectText(msgNotificationBar);
		verifyAssertEquals("2 records deleted successfully", getActualObjectTxt);
		takeScreenshot();
		thread();
	}
	
	@Test(priority=6)
	void massEditPageValidation() throws InterruptedException, IOException
	{
		click(teamsInMainMenu);
		thread();
		
		click(tmsLstViewFstNmFrMEdt);
		thread();
		click(astOvrMassEditBtn);
		thread();
		click(tmsmEdtSubmitBtn);
		thread();
		getObjectText(msgNotificationBar);
		verifyAssertEquals("Fill atleast any one field to update!", getActualObjectTxt);
		takeScreenshot();
		thread();
	}
	
	@Test(priority=7)
	void teamsMassEdit() throws InterruptedException, IOException, BiffException
	{
		click(teamsInMainMenu);
		thread();
		
		click(tmsLstViewFstNmFrMEdt);
		thread();
		click(tmsLstViewSecNmFrMEdt);
		thread();
		
		getTeamsSheetFromExcel();
		
		click(astOvrMassEditBtn);
		thread();
		
		//	Team Type
		click(tmsmEdtTeamTypeDDArw);
		getTotalValuesIndd(tmsmEdtTeamTypeCnt);
		Random random = new Random();
		int raNmMasEdtTm = random.nextInt(totalDDValCount-1)+1;
		teamTypeBfrMedt = driver.findElement(By.id("rgroup_team_type_chzn_o_"+raNmMasEdtTm)).getText();
		enterText(tmsmEdtTeamTypeSearchBox, teamTypeBfrMedt);
		enterKeyInKyBord(tmsmEdtTeamTypeSearchBox);
		thread();
		
		//	Team Leader
		click(tmsmEdtTeamLeaderDDArw);
		getTotalValuesIndd(tmsmEdtTeamLeaderCnt);
		Random random1 = new Random();
		int raNmMasEdtTL = random1.nextInt(totalDDValCount-1)+1;
		String teamLeaderBfrMed = driver.findElement(By.id("rgroup_group_leader_chzn_o_"+raNmMasEdtTL)).getText();
		teamLeaderName=teamLeaderBfrMed.substring(0,teamLeaderBfrMed.indexOf(","));
		teamLeaderBfrMedt = teamLeaderBfrMed.split(",");
		enterText(tmsmEdtTeamLeaderSearchBox, teamLeaderBfrMedt[0]);
		enterKeyInKyBord(tmsmEdtTeamLeaderSearchBox);
		takeScreenshot();
		
		//Alternate Team Leader
		click(tmsmEdtAltTeamLeadDDArw);
		getTotalValuesIndd(tmsEdtAltTeamLeadCnt);
		Random randAlt=new Random();
		int raAlt=randAlt.nextInt(totalDDValCount-1)+1;
		String alterLead=driver.findElement(By.xpath("//li[@id='rgroup_alternate_leader_chzn_o_"+raAlt+"']")).getText();
		altTeamLeaderName=alterLead.substring(0, alterLead.indexOf(","));
		enterText(tmsEdtAltTeamleadSearchBox,altTeamLeaderName);
		enterKeyInKyBord(tmsEdtAltTeamleadSearchBox);
		takeScreenshot();
		
		//	Location
		click(tmsmEdtLocationDDArw);
		getTotalValuesIndd(tmsmEdtLocationCnt);
		Random random3 = new Random();
		int raNmMasEdtLoc = random3.nextInt(totalDDValCount-2)+2;
		String locationNameBfrMed = driver.findElement(By.id("rgroup_location_chzn_o_"+ raNmMasEdtLoc)).getText();
		locationNameBfrMedt = locationNameBfrMed.split(",");
		enterText(tmsmEdtLocationSearchBox, locationNameBfrMedt[0]);
		enterKeyInKyBord(tmsmEdtLocationSearchBox);
		takeScreenshot();
		
		click(tmsmEdtSubmitBtn);
		thread();
	}
	
	@Test(priority=8)
	void verifyUpdatedSuccMsgForMassEdit() throws IOException, InterruptedException
	{
		getObjectText(msgNotificationBar);
		//verifyAssertEquals("2 Teams have been Updated with "+teamLeaderName+" and "+altTeamLeaderName, getActualObjectTxt);
		verifyAssertEquals("2 Teams have been updated with "+altTeamLeaderName+" and "+teamLeaderName, getActualObjectTxt);
		takeScreenshot();
		thread();
		refreshThePage();
		waitForPageLoad();
	}
	
	@Test(priority=9)
	void verifyMassEditDetailsInLstView() throws InterruptedException
	{
		for(int i=1; i<2; i++)
		{
			for(int j=3; j<9; j++)
			{
				String masEdit = driver.findElement(By.xpath("//table[@id='resourcegroup_table']/tbody/tr["+i+"]/td["+j+"]")).getText();
				if(j == 3 && i == 1)
				{
					verifyAssertEquals(teamLeaderBfrMedt[0], masEdit);
				}
				else if(j == 4 && i == 1)
				{
					verifyAssertEquals(teamTypeBfrMedt, masEdit);
				}
				else if(j == 5 && i == 1)
				{
					verifyAssertEquals(locationNameBfrMedt[0], masEdit);
				}	
			}
		} thread();		
	}
	
	@Test(priority=10)
	void verifySelectRecordToEditMsg() throws InterruptedException, IOException
	{
		click(astOvrMassEditBtn);
		thread();
		getObjectText(msgNotificationBar);
		verifyAssertEquals("Select records to perform Edit Operation...", getActualObjectTxt);
		takeScreenshot();
		thread();
	}
	
	@Test(priority=11)
	void cancelBtnRedirectionFrmMasEdtPage() throws InterruptedException
	{
		
		click(tmsLstViewFstNmFrMEdt);
		thread();
		
		click(astOvrMassEditBtn);
		thread();
		
		click(tmsmEdtCancelBtn);
		thread();
		verifyAssert(tmsTeamsSearchBox);
		thread();
	}
	
	@Test(priority=12)
	void verifyTeamsHelpPopup() throws InterruptedException, IOException
	{
		click(tmsOvrInfoBtn);
		verifyAssert(tmsTeamHelpDoc);
		takeScreenshot();
		thread();
		click(tmsTeamHelpPpCloseBtn);
	}
	
	/*@Test(priority=13)
	void logout() throws InterruptedException
	{
		ll.logoutSession();
		thread();
		closeWindow();
	}*/

}
