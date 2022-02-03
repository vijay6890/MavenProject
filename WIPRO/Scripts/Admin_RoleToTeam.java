package Scripts;

import static Config.TakScreenshot.takeScreenshot;
import static ObjectRepository.Admin.*;
import static UIWrappers.UIObjects.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import UIWrappers.Page;
import jxl.read.biff.BiffException;
import static ObjectRepository.Locations.*;

public class Admin_RoleToTeam extends Page{
	LoginLogout ll = new LoginLogout();
	ArrayList<String> list=new ArrayList<String>();
	public String selTxt="";
	
/*	@Test(priority=0)
	void login() throws BiffException, IOException, InterruptedException 
	{
		ll.loginToSIB();
	}        */
	
	@Test(priority=1)
	void navigateToAdminPage() throws InterruptedException
	{
		click(adminBtnInTopMnu);
		thread();
		click(adminInTopDD);
		thread();
	}
	
	@Test(priority=2)
	void navigateToTeamsNRolesTab() throws InterruptedException
	{
		click(teamsndRolesTb);
		thread();
	}
	
	/*@Test(priority=3)
	void listPrint() throws InterruptedException
	{
		getTotalValuesIndd(teamTableTotalVal);
		thread(); 
		
		
		for(int i=1;i<=totalDDValCount;i++)
		{
			
			//System.out.println(list.add(driver.findElement(By.xpath("//table[@id='team_list_table']/tbody/tr["+i+"]/td[1]")).getText()));
			list.add(driver.findElement(By.xpath("//table[@id='team_list_table']/tbody/tr["+i+"]/td[1]")).getText());
	   
		}	
		
		for(String team:list)
		{
			System.out.println(team);
		}
	}*/
	
	@Test(priority=3)
	void assignRoleToteam() throws InterruptedException, IOException
	{
		//try
	//	{
		getTotalValuesIndd(teamNameTotalDDVal);
		thread();
		click(teamNameDDArow);
				
		Random randt=new Random();
		int rn=randt.nextInt(totalDDValCount-1)+1;		
		//String randTeamName=driver.findElement(By.xpath("//div[@id='team_name_chzn']/div/ul/li["+rn+"]")).getText();
        String randTeamName=driver.findElement(By.xpath("//li[@id='team_name_chzn_o_"+rn+"']")).getText();
		
		enterText(teamNameSearchBox, randTeamName);
		enterKeyInKyBord(teamNameSearchBox);
		takeScreenshot();
		
		/*	if(rn==0)
		{
			rn=2;
		}
		else if(rn==1)
		{
			rn=2;
		}
		String randTeamName=driver.findElement(By.xpath("//select[@id='team_name']/option["+rn+"]")).getText();
		selectTextFromDropdown(teamNameSearchBox, randTeamName);
		takeScreenshot();*/
		
		
		getTotalValuesIndd(roleTotalDDVal);
		thread();
		
		Random randr=new Random();
		int rr=randr.nextInt(totalDDValCount-1)+1;
		
		if(rr==0)
		{
			rr=2;
		}
		else if(rr==1)
		{
			rr=2;
		}
		String randRole=driver.findElement(By.xpath("//select[@id='team_role']/option["+rr+"]")).getText();
		
		selectTextFromDropdown(roleSelectTable,randRole);
		//enterKeyInKyBord();
		
		getTotalValuesIndd(teamTableTotalVal);
		thread(); 
		
		/*click(teamRoleSubmitBtn);
		takeScreenshot();
		thread();
		getObjectText(msgNotificationBar);
		thread();*/
		
		for(int i=1;i<=totalDDValCount;i++)
		{
			
			//System.out.println(list.add(driver.findElement(By.xpath("//table[@id='team_list_table']/tbody/tr["+i+"]/td[1]")).getText()));
			list.add(driver.findElement(By.xpath("//table[@id='team_list_table']/tbody/tr["+i+"]/td[1]")).getText());
	   
		}	
		
	/*	for(String team:list)
		{
			System.out.println(team);
		}*/
		
		click(teamRoleSubmitBtn);
		takeScreenshot();
		thread();
		getObjectText(msgNotificationBar);
		thread();
		
		//if(list.contains(randTeamName))
		try
			{
	    	verifyAssertEquals(randTeamName+" updated successfully", getActualObjectTxt);	    	
	    	}
	  
		//else
		catch(Error e)
	    {
				verifyAssertEquals(randTeamName+" added successfully", getActualObjectTxt);				
		}
		
		takeScreenshot();
		thread();
		
		
		
		//search recently added team
		
		webElement(teamRecsInfo);
		scrollInnerScrollBar(webelementFrame);
		
		enterText(teamSearch,randTeamName);
		enterKeyInKyBord(teamSearch);
		thread();
		getObjectText(teamSearchFirstVal);
		thread();
		verifyAssertEquals(randTeamName,getActualObjectTxt);
		takeScreenshot();
		thread();
		clear(teamSearch);
		enterKeyInKyBord(teamSearch);
		thread();
		
	//	}
	//	catch(Exception e)
	//	{
	//		System.out.println(e);
	//	}
		
		
		
		
	/*	
	 getTotalValuesIndd(teamTableTotalVal);
		thread(); 
	 for(int i=1;i<=totalDDValCount;i++)
		{
			if((driver.findElement(By.xpath("//table[@id='team_list_table']/tbody/tr["+i+"]/td[1]")).getText().equals(randTeamName))&&
					((driver.findElement(By.xpath("//table[@id='team_list_table']/tbody/tr["+i+"]/td[3]")).getText().equals(randRole))))
			{
				
			}
		}*/
	
	}
	
	@Test(priority=4)
	void searchTeamNRole() throws InterruptedException, IOException
	{
		getTotalValuesIndd(teamTableTotalVal);
		thread(); 
		
		Random rand=new Random();
		int rn1=rand.nextInt(totalDDValCount)+1;
		String randTeamNme=driver.findElement(By.xpath("//table[@id='team_list_table']/tbody/tr["+rn1+"]/td[1]")).getText();
		 
		webElement(teamRecsInfo);
		scrollInnerScrollBar(webelementFrame);
		 
		enterText(teamSearch,randTeamNme);
		enterKeyInKyBord(teamSearch);
		thread();
		getObjectText(teamSearchFirstVal);
		thread();
		verifyAssertEquals(randTeamNme,getActualObjectTxt);
		takeScreenshot();
		thread();
		clear(teamSearch);
		enterKeyInKyBord(teamSearch);
		thread();
		
		enterText(teamSearch,"Invalid Team");
		thread();
		getObjectText(teamSearchNoRecs);
		thread();
		verifyAssertEquals(noMatchingRecordsFound,getActualObjectTxt);
		takeScreenshot();
		thread();
		clear(teamSearch);
		enterKeyInKyBord(teamSearch);
		thread();
		
		
	}
	
	@Test(priority=5)
	void verifyTeamAndRole() throws InterruptedException, IOException
	{
		getTotalValuesIndd(teamTableTotalVal);
		thread(); 
		
		Random rand=new Random();
		int rn1=rand.nextInt(totalDDValCount)+1;
		String randTeamNme=driver.findElement(By.xpath("//table[@id='team_list_table']/tbody/tr["+rn1+"]/td[1]")).getText();
		webElement(teamRecsInfo);
		scrollInnerScrollBar(webelementFrame);
		 
		enterText(teamSearch,randTeamNme);
		enterKeyInKyBord(teamSearch);
		thread();
		
		String getTeamRole=driver.findElement(By.xpath("//table[@id='team_list_table']/tbody/tr[1]/td[3]")).getText();
		
		webElement(teamNameSearchBox);
		scrollInnerScrollBar(webelementFrame);
		
		click(teamNameDDArow);
		enterText(teamNameSearchBox, randTeamNme);
		enterKeyInKyBord(teamNameSearchBox);
		
		//selectTextFromDropdown(teamNameSearchBox, randTeamNme);
		takeScreenshot();
		
		
		if(getTeamRole.contains(","))
		{
			getAllSelectedTxtFromDropdown(roleSelectTable);
			for(WebElement sel:getAllSelectedText)
			{
				selTxt=selTxt.concat(",").concat(sel.getText());
			}
			//System.out.println("All roles "+selTxt);
			//verifyAssertEquals(getTeamRole,selTxt.substring(1));
		}
		else
		{
		getSelectedTxtFromDropdown(roleSelectTable);
		verifyAssertEquals(getTeamRole,getVisibleTxt);
		}
		 
	}
	
/*	@Test(priority=5)
	void deleteteam() throws IOException, InterruptedException
	{
		try
		{
		getTotalValuesIndd(teamTableTotalVal);
		thread(); 
		
		Random rand=new Random();
		int rn1=rand.nextInt(totalDDValCount-1)+1;
		String randTeamNme=driver.findElement(By.xpath("//table[@id='team_list_table']/tbody/tr["+rn1+"]/td[1]")).getText();
		 
		webElement(teamRecsInfo);
		scrollInnerScrollBar(webelementFrame);
		 
		enterText(teamSearch,randTeamNme);
		enterKeyInKyBord(teamSearch);
		thread();
		getObjectText(teamSearchFirstVal);
		thread();
		verifyAssertEquals(randTeamNme,getActualObjectTxt);
		takeScreenshot();
		thread();
		
		click(teamDeleteBtn);
		thread();
		click(delConfOkBtn);
		thread();
		takeScreenshot();
		getObjectText(msgNotificationBar);
		thread();
		verifyAssertEquals(randTeamNme+" team is deleted successfully", getActualObjectTxt);
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		
	}*/
	
	/*@Test(priority=6)
	void logout() throws InterruptedException
	{
		ll.logoutSession();
		thread();
		closeWindow();
		
	}*/
	
}
