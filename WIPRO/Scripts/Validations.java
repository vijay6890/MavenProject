package Scripts;

import java.io.IOException;
import java.util.Random;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import UIWrappers.Page;
import jxl.read.biff.BiffException;

import static Config.TakScreenshot.takeScreenshot;
import static ObjectRepository.Locations.*;
import static ObjectRepository.Threats.*;
import static ObjectRepository.Teams.*;
import static UIWrappers.UIObjects.*;

public class Validations extends Page
{
LoginLogout ll = new LoginLogout();
Tms_AddNewTeam ta=new Tms_AddNewTeam();

	@Test(priority=0)
	void login() throws BiffException, IOException, InterruptedException
	{
		ll.loginToSIB();
	}
	
	@Test(priority=1)
	void verifyThreatsHighlightedInMainMenu() throws InterruptedException
	{
		click(threatsInMainMenu);
		thread();
		
		getHighlightOptn(mainThreatsOptn);
		verifyAssertEquals("active", getHighlightdTxt);	
	}
	
    @Test(priority=2)
    void validateThreatsMandatoryFields() throws InterruptedException, IOException
    {
    	click(thrOvrAddBtn);
    	thread();
    	
    	 click(thrSubmitBtn);
    	 thread();
    	 
    	getObjectText(stepValidatnMsg);
 		verifyAssertEquals("Threat Name is required.", getActualObjectTxt);
 		takeScreenshot();
    }
    
    @Test(priority=3)
    void verifyTeamsHighlightedInMainMenu() throws InterruptedException
    {
    	click(teamsInMainMenu);
    	thread();
    	getHighlightOptn(mainTeamsOptn);
		verifyAssertEquals("active", getHighlightdTxt);	
    }
    
    @Test(priority=4)
    void validateTeamsMandatoryFields() throws InterruptedException, IOException, BiffException
    {
    	getTeamsSheetFromExcel();
    	click(tmsOvrAddBtn);
    	thread();
    	
    	click(tmsSubmitBtn);
        	
    	getObjectText(stepValidatnMsg);
 		verifyAssertEquals("Team Name is required.", getActualObjectTxt);
 		takeScreenshot();
 		
 		Random random = new Random();
		int rt = random.nextInt(tms.getRows()-1)+1;
		
		//	Team Name
		ta.teamName = tms.getCell(0, rt).getContents();
		enterText(tmsTeamName, ta.teamName);
		
		click(tmsSubmitBtn);
    	
    	getObjectText(stepValidatnMsg);
 		verifyAssertEquals("Team Leader is required.", getActualObjectTxt);
 		takeScreenshot();
 		
 		
          // 		Choose Team Leader
 			click(tmsTeamLeaderArrow);
 			getTotalValuesIndd(tmsTeamLeaderCnt);
 			if(totalDDValCount > 1)
 			{
 				Random rTmLdrrandom = new Random();
 				int rteamLdrName = rTmLdrrandom.nextInt(totalDDValCount-1)+1;			
 				String teamLeaderName = driver.findElement(By.id("group_leader_chzn_o_"+rteamLdrName)).getText();
 				enterText(tmsTeamLeaderSearchBox, teamLeaderName.substring(0, 10));
 				enterKeyInKyBord(tmsTeamLeaderSearchBox);
 			} 			
 			
 			click(tmsSubmitBtn); 	    
 	    	
 	    	getObjectText(stepValidatnMsg);
 	 		verifyAssertEquals("Location is required.", getActualObjectTxt);
 	 		takeScreenshot();
    	
    }
    
    
    
    /*@Test(priority=3)
	void logout() throws InterruptedException
	{
		ll.logoutSession();
		thread();
		closeWindow();
	}*/
	
}
