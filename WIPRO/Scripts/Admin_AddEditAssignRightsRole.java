package Scripts;

import static Config.TakScreenshot.takeScreenshot;
import static ObjectRepository.Admin.*;
import static ObjectRepository.Assets.*;
import static ObjectRepository.Locations.*;
import static UIWrappers.UIObjects.*;


import java.io.IOException;
import java.util.Random;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import UIWrappers.Page;
import jxl.read.biff.BiffException;

public class Admin_AddEditAssignRightsRole extends Page {
	
	LoginLogout ll = new LoginLogout();
	public String roleName1;
	public String roleDesc;
	
/*  @Test(priority=0)
	void login() throws BiffException, IOException, InterruptedException 
	{
		ll.loginToSIB();
	}   */
	
	@Test(priority=1)
	void navigateToAdminPage() throws InterruptedException
	{
		click(adminBtnInTopMnu);
		thread();
		click(adminInTopDD);
		thread();
	}
	
	@Test(priority=2)
	void addNewRole() throws BiffException, IOException, InterruptedException
	{
		getAdminSheetFromExcel();
		Random random = new Random();
		int rrow = random.nextInt(admn.getRows()-1)+1;
		
		//Role Name
		roleName1=admn.getCell(0, rrow).getContents();
		roleDesc=admn.getCell(1, rrow).getContents();
		enterText(roleName,roleName1);
		enterText(roleDescription,roleDesc);		
		takeScreenshot();
		click(roleSubmitBtn);
		thread();		
	}
	
	@Test(priority=3)
	void verifyAssetAddedSuccMsg() throws IOException, InterruptedException 
	{
		getObjectText(msgNotificationBar);
		verifyAssertEquals(roleName1+" role is added successfully", getActualObjectTxt);
		takeScreenshot();
		thread();
	}
	
	@Test(priority=4)
	void searchForAddedRole() throws InterruptedException, IOException
	{
		enterText(roleSearchBox,roleName1);
		thread();
		getObjectText(roleSearchRoleNameVal);
		thread();
		verifyAssertEquals(roleName1,getActualObjectTxt);
		thread();
		getObjectText(roleSearchDescriptionVal);
		thread();
		verifyAssertEquals(roleDesc,getActualObjectTxt);
		thread();
		takeScreenshot();
		thread();
		
		clear(roleSearchBox);
		
	}
	
	@Test(priority=5)
	void editAndDeleteAddedRole() throws BiffException, IOException, InterruptedException
	{
		enterText(roleSearchBox,roleName1);
		
		//  edit
		click(roleEditBtn);
		thread();
		clear(roleName);
		thread();
		clear(roleDescription);
		thread();
		
		getAdminSheetFromExcel();
		//System.out.println("rows "+admn.getRows());
		Random random = new Random();
		int rrow = random.nextInt(admn.getRows()-1)+1;
		
		//Role Name
		roleName1=admn.getCell(0, rrow).getContents();
		roleDesc=admn.getCell(1, rrow).getContents();
		enterText(roleName,roleName1);
		enterText(roleDescription,roleDesc);
		
		takeScreenshot();

		click(roleSubmitBtn);
		thread();
		
		getObjectText(msgNotificationBar);
		verifyAssertEquals(roleName1+" role is updated successfully", getActualObjectTxt);
		takeScreenshot();
		thread();
		
		//   Delete
	/*	enterText(roleSearchBox,roleName1);
		thread();
		click(roleDeleteBtn);
		thread();
		click(roleDeleteOkBtn);
		thread();
		
		getObjectText(msgNotificationBar);
		verifyAssertEquals(roleName1+" role is deleted successfully", getActualObjectTxt);
		takeScreenshot();
		thread();*/
		
	}
	
	//Set permissions to added role
	
	@Test(priority=6)
	void setPermissionsToRole() throws InterruptedException
	{
		getTotalValuesIndd(roleTableTotalDDval);
		thread();
		webElement(roleScroll);
		scrollInnerScrollBar(webelementFrame);
		thread();
		for(int i=1;i<=totalDDValCount;i++)
		{
		
			String rName=driver.findElement(By.xpath("//ul[@id='role_active']/li["+i+"]/a")).getText();
		
			if(rName.trim().equals(roleName1.trim()))
			{
				thread();
				driver.findElement(By.xpath("//ul[@id='role_active']/li["+i+"]/a")).click();
			
				thread();
				Thread.sleep(3000);
				click(roleAllowAllPermission);
				thread();
				getTotalValuesIndd(roleEditPermission);
				Random rand=new Random();
				int re=rand.nextInt(totalDDValCount-2)+2;
				driver.findElement(By.xpath("//table[@id='permission_tab']/thead/tr/th[3]/select/option["+re+"]")).click();
				thread();
				
				getTotalValuesIndd(roleDeletePermission);
				Random rand1=new Random();
				int rd=rand1.nextInt(totalDDValCount-2)+2;
				driver.findElement(By.xpath("//table[@id='permission_tab']/thead/tr/th[4]/select/option["+rd+"]")).click();
				thread();
				
				getTotalValuesIndd(roleListPermission);
				Random rand3=new Random();
				int rl=rand3.nextInt(totalDDValCount-2)+2;
				driver.findElement(By.xpath("//table[@id='permission_tab']/thead/tr/th[5]/select/option["+rl+"]")).click();
				thread();
				
				getTotalValuesIndd(roleDocPermission);
				Random rand4=new Random();
				int rdoc=rand4.nextInt(totalDDValCount-2)+2;
				driver.findElement(By.xpath("//table[@id='permission_tab']/thead/tr/th[6]/select/option["+rdoc+"]")).click();
				thread();
			}
	}
	}
		
	/*	@Test(priority=7)
		void logout() throws InterruptedException
		{
			ll.logoutSession();
			thread();
			closeWindow();
		}*/
	
	
	
	
	
	
	
	
	
	
}
