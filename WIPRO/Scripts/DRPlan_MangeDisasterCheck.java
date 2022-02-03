package Scripts;

import java.io.IOException;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import static ObjectRepository.DRPlan.*;
import static ObjectRepository.ManageDisaster.*;
import static ObjectRepository.Tasks.*;
import static UIWrappers.UIObjects.*;

import UIWrappers.Page;
import jxl.read.biff.BiffException;

public class DRPlan_MangeDisasterCheck extends Page
{
	String drLoc,drPlanName,drThreat;
	LoginLogout ll = new LoginLogout();	
	DR_CreateEditDRPlan drEdit=new DR_CreateEditDRPlan(); 
	
	/*@Test(priority=1)
	void login() throws BiffException, IOException, InterruptedException
	{
		ll.loginToSIB();
	}*/
	
	@Test(priority=2)
	void checkAddedDRPlanInMDPage() throws BiffException, IOException, InterruptedException
	{
		drEdit.addNewDRPlan();
		drEdit.verifyLocationBasedTemplates();
		//drEdit.editPlanInformation();
		//drEdit.preview();
		//drEdit.verifyDRPlanNmInListView();
		click(drThreatsnDRPlanInMainMenu);
		thread();
		
		drLoc=driver.findElement(By.xpath("//table[@id='plan_table']/tbody/tr[1]/td[6]")).getText(); //Added Dr Plan Location
		drPlanName=driver.findElement(By.xpath("//table[@id='plan_table']/tbody/tr[1]/td[2]")).getText();  //Dr Plan Name
		drThreat=driver.findElement(By.xpath("//table[@id='plan_table']/tbody/tr[1]/td[7]")).getText();   // Threat
		
	
		click(manageDisasterInMainMenu);
		waitForPageLoad();
		
        webElement(mdLocationsSearchBox);
		scrollInnerScrollBar(webelementFrame);
		
		//enterText(mdLocationsSearchBox,drLoc);
		//enterKeyInKyBord(mdLocationsSearchBox);
		
		getTotalValuesIndd(mdAffLocationLstViewCnt);
		thread();
		
		for(int i=1;i<=totalDDValCount;i++)
		{
		   try
	     	{
				if(driver.findElement(By.xpath("//table[@id='DataTables_Table_0']/tbody/tr["+i+"]/td[2]")).getText().equals(drLoc))
			       {
					
				     driver.findElement(By.xpath("//table[@id='DataTables_Table_0']/tbody/tr["+i+"]/td[1]/input")).click();
				     thread();
			       }
				thread();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			
		}
		
		
		//Threats Search
		
		 webElement(mdThreatSearchBox);
		 scrollInnerScrollBar(webelementFrame);
		 
		 enterText(mdThreatSearchBox,drThreat);
		 enterKeyInKyBord(mdThreatSearchBox);
		 
		 getObjectText(mdThreatFstVal);
		 thread();
		 verifyAssertEquals(drThreat,getActualObjectTxt);
		 click(mdFstThreatNmeInThreats);
		 thread();
		 
		 //DR Plan Search
		
		 webElement(mdDRPlanSearchBox);
		 scrollInnerScrollBar(webelementFrame);
		 
		 
		enterText(mdDRPlanSearchBox,drPlanName);
		enterKeyInKyBord(mdDRPlanSearchBox);
		
		getObjectText(mdDRPlanFstVal);
		thread();
		verifyAssertEquals(drPlanName,getActualObjectTxt);
		click(mdDRPlanCheckBox);
		thread();
		
		
	}
	

}
