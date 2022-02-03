package Scripts;

import static ObjectRepository.Admin.*;
import static ObjectRepository.Locations.*;

import static UIWrappers.UIObjects.*;
import static Config.TakScreenshot.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import UIWrappers.Page;
import jxl.read.biff.BiffException;

public class Admin_AddDeleteUsers extends Page{
LoginLogout ll = new LoginLogout();
ArrayList<String> list=new ArrayList<String>();
public String roleTxt="";
public String teamTxt="";
	
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
	void navigateToUsers() throws InterruptedException
	{
		click(usersTb);
		thread();
	}
	
	@Test(priority=3)
	void addUser() throws InterruptedException, IOException
	{
		try
		{
		getTotalValuesIndd(usrEmpnameTotalVal);
		thread();
		click(usrEmpNameDDArow);
		thread();
		
		Random rand=new Random();
		int rn=rand.nextInt(totalDDValCount-1)+1;
	
	/*	if(rn==0)
		{
			rn=2;
		}
		else if(rn==1)
		{
			rn=2;
		}
		String randEmpName=driver.findElement(By.xpath("//select[@id='user_name']/option["+rn+"]")).getText();
		
		
		String randEmpName1=randEmpName.substring(0, randEmpName.indexOf(","));
		selectTextFromDropdown(usrEmpNameDDArow,randEmpName);
		thread();  */
		

		if(rn==1)
		{
			rn=+1;
		}
        String randEmpName1=driver.findElement(By.xpath("//div[@id='user_name_chzn']/div/ul/li["+rn+"]")).getText();
		String randEmpName=randEmpName1.substring(0, randEmpName1.indexOf(","));
		
		//String randEmpName=driver.findElement(By.xpath("//li[@id='user_name_chzn_o_"+rn+"']")).getText();
		
	 //   String randEmpName1=randEmpName.substring(0, randEmpName.indexOf(","));
		
		enterText(usrEmpNameSearchBox,randEmpName);
        thread();			
		enterKeyInKyBord(usrEmpNameSearchBox);
				
		
		getTotalValuesIndd(usrRoleTotalVal);
		thread();
		
		Random rand1=new Random();
		int rn1=rand1.nextInt(totalDDValCount-1)+1;
		if(rn1==0)
		{
			rn1=2;
		}
		else if(rn1==1)
		{
			rn1=2;
		}
		String randRole=driver.findElement(By.xpath("//select[@id='rrole_name']/option["+rn1+"]")).getText();
		
		selectTextFromDropdown(usrRoleSelectTable,randRole);
		thread();
		
		getTotalValuesIndd(usrTeamTotalval);
		thread();
		
		Random rand2=new Random();
		int rn2=rand2.nextInt(totalDDValCount-1)+1;
		if(rn2==0)
		{
			rn2=2;
		}
		else if(rn2==1)
		{
			rn2=2;
		}
		String randTeam=driver.findElement(By.xpath("//select[@id='rteam_name']/option["+rn2+"]")).getText();
		
		selectTextFromDropdown(usrTeamSelectTable,randTeam);
		thread();
		
		getTotalValuesIndd(usrTableTotalval);
		thread();
		
		/*click(usrSubmitBtn);
		takeScreenshot();
		thread();
		
		getObjectText(msgNotificationBar);
		thread();*/
		
		for(int i=1;i<=totalDDValCount;i++)
		{
			list.add(driver.findElement(By.xpath("//table[@id='user_list_table']/tbody/tr["+i+"]/td[1]")).getText().concat(" ").
	    		concat(driver.findElement(By.xpath("//table[@id='user_list_table']/tbody/tr["+i+"]/td[2]")).getText()));
		}
		
	/*	for(String name:list)
		{
			System.out.println(name);
		}   */
			
	    click(usrSubmitBtn);
		takeScreenshot();
		thread();
		
		getObjectText(msgNotificationBar);
		thread();
		
	//	if(list.contains(randEmpName.substring(0, randEmpName.indexOf(","))))
		try    
    	{
			try
		    {
    	verifyAssertEquals(randEmpName+" updated successfully.", getActualObjectTxt);
    	//System.out.println("user updated");
    	}
    	
   // else
		catch(Error e)
		{
    	verifyAssertEquals(randEmpName+" added successfully.", getActualObjectTxt);
    	//System.out.println("New User added");
        }
    	
		
		takeScreenshot();
		thread();
		
		webElement(usrRecsInfo);
		scrollInnerScrollBar(webelementFrame);
		
		enterText(usrSearchBox,randEmpName);
		enterKeyInKyBord(usrSearchBox);
		thread();
		getObjectText(usrTableFirstVal);
		thread();
	    verifyAssertEquals(randEmpName.substring(0, randEmpName.indexOf(" ")),getActualObjectTxt);
		takeScreenshot();
		thread();
		clear(usrSearchBox);
		enterKeyInKyBord(usrSearchBox);
		thread();
		
		}
		catch (Error e)
		{
			verifyAssertEquals("Email Id is required to add an employee as a user",getActualObjectTxt);
		}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
				//account is deleted successfully
		
		
	}
	
	@Test(priority=4)
	void searchUserName() throws InterruptedException, IOException
	{
		try
		{
		getTotalValuesIndd(usrTableTotalval);
		thread(); 
		
		Random rand=new Random();
		int rn1=rand.nextInt(totalDDValCount)+1;
		String randUserNme=driver.findElement(By.xpath("//table[@id='user_list_table']/tbody/tr["+rn1+"]/td[1]")).getText();
		 
		webElement(usrRecsInfo);
		scrollInnerScrollBar(webelementFrame);
		
		enterText(usrSearchBox,randUserNme);
		thread();
		getObjectText(usrTableFirstVal);
		thread();
		verifyAssertEquals(randUserNme,getActualObjectTxt);
		takeScreenshot();
		thread();
		clear(usrSearchBox);
		enterKeyInKyBord(usrSearchBox);
		thread();
		
		enterText(usrSearchBox,"invalid name");
		enterKeyInKyBord(usrSearchBox);
		thread();
		getObjectText(usrTableFirstVal);
		thread();
		verifyAssertEquals(noMatchingRecordsFound,getActualObjectTxt);
		takeScreenshot();
		thread();
		clear(usrSearchBox);
		enterKeyInKyBord(usrSearchBox);
		thread();	
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
	@Test(priority=5)
	void verifySelectedRoleNTeam() throws InterruptedException, IOException
	{
		try
		{
		getTotalValuesIndd(usrTableTotalval);
		thread(); 
		
		Random rand=new Random();
		int rn1=rand.nextInt(totalDDValCount)+1;
		String randUserNme=driver.findElement(By.xpath("//table[@id='user_list_table']/tbody/tr["+rn1+"]/td[1]")).getText();
		 
		webElement(usrRecsInfo);
		scrollInnerScrollBar(webelementFrame);
		
		enterText(usrSearchBox,randUserNme);
		thread();	
		
		String roleName=driver.findElement(By.xpath("//table[@id='user_list_table']/tbody/tr[1]/td[3]")).getText();
		String teamName=driver.findElement(By.xpath("//table[@id='user_list_table']/tbody/tr[1]/td[4]")).getText();
		
		
		webElement(usrEmpNameDDArow);
		scrollInnerScrollBar(webelementFrame);
		
		click(usrEmpNameDDArow);
		enterText(usrEmpNameSearchBox,randUserNme);
		//selectTextFromDropdown(usrEmpNameDDArow,randUserNme);
		enterKeyInKyBord(usrEmpNameSearchBox);
		thread();
		takeScreenshot();
		
		// verify selected roles  
		
		if(roleName.contains(","))
		{
			getAllSelectedTxtFromDropdown(usrRoleSelectTable);
			for(WebElement sel:getAllSelectedText)
			{
				roleTxt=roleTxt.concat(",").concat(sel.getText());
			}
			//System.out.println("All roles "+roleTxt);
			//verifyAssertEquals(getTeamRole,selTxt.substring(1));
		}
		else
		{
		getSelectedTxtFromDropdown(usrRoleSelectTable);
		verifyAssertEquals(roleName,getVisibleTxt);
		}
		
		// verify selected Teams
		
		if(teamName.contains(","))
		{
			getAllSelectedTxtFromDropdown(usrTeamSelectTable);
			for(WebElement sel:getAllSelectedText)
			{
				teamTxt=teamTxt.concat(",").concat(sel.getText());
			}
			//System.out.println("All Teams "+teamTxt);
			//verifyAssertEquals(getTeamRole,selTxt.substring(1));
		}
		else
		{
		getSelectedTxtFromDropdown(usrTeamSelectTable);
		verifyAssertEquals(teamName,getVisibleTxt);
		}
		thread();
		clear(usrSearchBox);
		}
		catch(Exception e)
		{
			thread();
			clear(usrSearchBox);
			//Exception occurred
		}
		
	}
	
	/*@Test(priority=5)
	void deleteUser() throws IOException, InterruptedException
	{
		getTotalValuesIndd(usrTableTotalval);
		thread(); 
		
		Random rand=new Random();
		int rn1=rand.nextInt(totalDDValCount)+1;
		String randUserNme=driver.findElement(By.xpath("//table[@id='user_list_table']/tbody/tr["+rn1+"]/td[1]")).getText();
		String randUserCol2=driver.findElement(By.xpath("//table[@id='user_list_table']/tbody/tr["+rn1+"]/td[2]")).getText();
		 
		webElement(usrRecsInfo);
		scrollInnerScrollBar(webelementFrame);
		
		enterText(usrSearchBox,randUserNme);
		thread();
		getObjectText(usrTableFirstVal);
		thread();
		verifyAssertEquals(randUserNme,getActualObjectTxt);
		takeScreenshot();
		thread();
		
		click(usrDeleteBtn);
		thread();
		click(delConfOkBtn);
		thread();
		takeScreenshot();
		getObjectText(msgNotificationBar);
		thread();
		verifyAssertEquals(randUserNme.concat(" ").concat(randUserCol2)+" account is deleted successfully", getActualObjectTxt);
	}*/
	
	
/*	@Test(priority=6)
	void inActiveUser() throws InterruptedException, IOException
	{
		try
		{
		getTotalValuesIndd(usrTableTotalval);
		thread(); 
		
		Random rand=new Random();
		int rn1=rand.nextInt(totalDDValCount)+1;
		String randUserNme=driver.findElement(By.xpath("//table[@id='user_list_table']/tbody/tr["+rn1+"]/td[1]")).getText();
		 
		webElement(usrRecsInfo);
		scrollInnerScrollBar(webelementFrame);
		
		enterText(usrSearchBox,randUserNme);
		thread();
		getObjectText(usrTableFirstVal);
		thread();
		verifyAssertEquals(randUserNme,getActualObjectTxt);
		takeScreenshot();
		thread();
		
		
		thread();
		getObjectText(usrStatusValue);
		thread();
	//	System.out.println(getActualObjectTxt);
		
		if(getActualObjectTxt.equals("active"))
		{
			click(usrStatus);
			selectTextFromDropdown(usrStatusSelectTable,"inactive");
			thread();
		}
		else
		{
			click(usrStatus);
			selectTextFromDropdown(usrStatusSelectTable,"active");
			thread();
		}
		thread();
		clear(usrSearchBox);
		enterKeyInKyBord(usrSearchBox);
		thread();	
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	/*@Test(priority=7)
	void logout() throws InterruptedException
	{
		ll.logoutSession();
		thread();
		closeWindow();
		
	}*/
	
	
}
