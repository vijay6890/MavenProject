package Scripts;

import java.io.IOException;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import UIWrappers.Page;
import static UIWrappers.UIObjects.*;
import jxl.read.biff.BiffException;

import static Config.TakScreenshot.takeScreenshot;
import static ObjectRepository.Locations.*;
import static ObjectRepository.LoginPage.*;
import static ObjectRepository.Profile.*;

public class ProfilePage extends Page {
	
	LoginLogout ll = new LoginLogout();
	String oldPswd="test@123";
	String newPswd="test123@";
	String str[]={"Facebook1@gmail.com","Twitter1@gmail.com","9962848906","LinkedIn1@gmail.com"};
	long phNo=9962848906L;
	
/*	@Test()
	void print()
	{
		System.out.println(str.length);
	for(int j=0;j<str.length;j++)
	  {
		System.out.println(str[j]);
	  }
	}   */       
	
	/*@Test(priority=0)
	void login() throws BiffException, IOException, InterruptedException 
	{
		ll.loginToSIB();
	} */

	@Test(priority=1)
	void navigateToProfilepage() throws InterruptedException
	{
		click(clkUserNameBtn);
		thread();
		click(profileInDropDown);
		thread();
	}
	
	@Test(priority=2)
	void verifyMyStatusPage() throws InterruptedException
	{
		thread();
		getObjectText(myStatusText);
		thread();
		verifyAssertEquals("My Status",getActualObjectTxt);
		thread();
		getObjectText(editOption);
		thread();
		verifyAssertEquals("edit",getActualObjectTxt);
		thread();
		getObjectText(removeOption);
		thread();
		verifyAssertEquals("remove",getActualObjectTxt);
		thread();
		
	}
	@Test(priority=6)
	void editProfilePicture() throws InterruptedException, IOException
	{
		
		click(editOption);
		Thread.sleep(1000);
		uploadFile("/var/lib/jenkins/jobs/SIBSELENIUM/workspace/image1.jpg");
		Thread.sleep(1000);
		try
		{
		getObjectText(msgNotificationBar);
		Thread.sleep(5000);
		takeScreenshot();
		waitForPageLoad();
		verifyAssertEquals("Profile picture uploaded successfully" , getActualObjectTxt);	
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		Thread.sleep(1000);
		
	}
	
	@Test(priority=7)
	void removeProfilePicture() throws InterruptedException, IOException
	{
	Thread.sleep(9000);
	click(removeOption);
    thread();
	click(delConfOkBtn);
	thread();
	getObjectText(msgNotificationBar);
	thread();
	takeScreenshot();
	verifyAssertEquals("Profile picture removed successfully",getActualObjectTxt);
	thread();
	}
	
	@Test(priority=3)
	void changeStatus() throws InterruptedException, IOException
	{
		//click(stsSelectStatus);
		//thread();
		getTotalValuesIndd(stsSelectStatusDDVal);
		thread();
		
		Random rand=new Random();
		int rv=rand.nextInt(totalDDValCount-2)+2;
		String rval=driver.findElement(By.xpath("//select[@name='status_id']/option["+rv+"]")).getText();
		
		selectTextFromDropdown(stsSelectStatus,rval);
		thread();
		//enterKeyInKyBord(stsSelectStatus);
		//thread();
		click(stsUpdateBtn);
		thread();
		getObjectText(msgNotificationBar);
		thread();
		takeScreenshot();
		verifyAssertEquals("Status has been updated successfully",getActualObjectTxt);
		thread();
		click(clkUserNameBtn);
		thread();
		getObjectText(profileStatus);
		String status=getActualObjectTxt;
		System.out.println("status under user name"+status);
		getSelectedTxtFromDropdown(stsSelectStatus);
		thread();
		System.out.println("text in dropdown " +getVisibleTxt);
		verifyAssertEquals(status,getVisibleTxt);
		thread();
	}
	
	@Test(priority=4)
	void addSocialInfo() throws InterruptedException, IOException, BiffException
	{
		Thread.sleep(10000);
		click(socialInfoTab);
		click(socInfoSelect);
		thread();
		
		getSocialInformationFromExcel();    //get Social Information from excel sheet 
		
		Random rs=new Random();
		int rInf=rs.nextInt(socInfo.getRows()-1)+1;
		
		getTotalValuesIndd(socInfoTotalddVal);
		
		Random rand=new Random();
		int rn=rand.nextInt(totalDDValCount-2)+2;
		String rSocInfo=driver.findElement(By.xpath("//select[@id='social']/option["+rn+"]")).getText();
		
		selectTextFromDropdown(socInfoSelect,rSocInfo);
		thread();
		enterKeyInKyBord(socInfoSelect);
		
		if(rn==2)
		{
		String fb=socInfo.getCell(0, rInf).getContents();		
		enterText(socInfoTxtBox,fb);
		thread();
		}
		else if(rn==3)
		{
			String twtr=socInfo.getCell(1, rInf).getContents();	
			enterText(socInfoTxtBox,twtr);
			thread();
		}
		else if(rn==4)
		{
			String ph=socInfo.getCell(2, rInf).getContents();	
			enterText(socInfoTxtBoxFrWtsApp,ph);
			thread();
		}
		else
		{
			String link=socInfo.getCell(3, rInf).getContents();	
			enterText(socInfoTxtBox,link);
			thread();
		}
		
		
	/*	int rn1=rand.nextInt(100);
		String rstr=Integer.toString(rn1);
				
		if(rn==2)
		{
		String[] fb=str[0].split("@");		
		enterText(socInfoTxtBox,fb[0].concat(rstr).concat("@").concat(fb[1]));
		thread();
		}
		else if(rn==3)
		{
			String[] twtr=str[1].split("@");
			enterText(socInfoTxtBox,twtr[0].concat(rstr).concat("@").concat(twtr[1]));
			thread();
		}
		else if(rn==4)
		{
			enterText(socInfoTxtBoxFrWtsApp,str[2]);
			thread();
		}
		else
		{
			String[] link=str[3].split("@");
			enterText(socInfoTxtBox,link[0].concat(rstr).concat("@").concat(link[1]));
			thread();
		}        */
		
		try
		{
		click(socInfoSaveBtn);
		thread();
		
		getObjectText(msgNotificationBar);
		thread();
		takeScreenshot();
		verifyAssertEquals("Social information has been added successfully",getActualObjectTxt);
		thread();
		}
		catch(Exception e)
	   {
			System.out.println("Same informatin already exists.Try with another number ");
			System.out.println(e);
		}
	
	}
	
	
	@Test(priority=5)
	void deleteSocialInfo() throws InterruptedException, IOException
	{
		getTotalValuesIndd(socInfoTableValues);
		Thread.sleep(10000);
		Random rand=new Random();
		int rv=rand.nextInt(totalDDValCount-1)+1;
		System.out.println(rv);
		WebElement delRec=driver.findElement(By.xpath("//table[@id='social_table']/tbody/tr["+rv+"]/td[3]"));
		
		delRec.click();
		thread();
		
		click(delConfOkBtn);
		thread();
		
		getObjectText(msgNotificationBar);
		thread();
		takeScreenshot();
		verifyAssertEquals("Social information has been removed successfully",getActualObjectTxt);
		thread();
	
	}
	
/*	@Test(priority=8)
	void changePasswordPage() throws InterruptedException, IOException
	{
		click(chngPwdTab);
		thread();
		enterText(oldPwdTxtBox,oldPswd);
		thread();
		enterText(newPwdTxtBox,newPswd);
		thread();
		enterText(cnfmPwdTxtBox,newPswd);
		thread();
		
		click(chngPwdSaveBtn);
		thread();
		
		getObjectText(msgNotificationBar);
		thread();
		takeScreenshot();
		verifyAssertEquals("Password updated successfully",getActualObjectTxt);
		thread();
	}*/
	
	@Test(priority=9)
	void genConfigPage() throws InterruptedException, IOException
	{
		click(genConfigTab);
		thread();
		
		getTotalValuesIndd(genConfTotalddval);
		thread();
		click(genConfSelect);
		thread();
		enterKeyInKyBord(genConfSelect);
		
		Random rand=new Random();
		int rv=rand.nextInt(totalDDValCount-1)+1;
		System.out.println(rv);
		String rval=driver.findElement(By.xpath("//select[@name='ppr_id']/option["+rv+"]")).getText();
		System.out.println(rval);
		selectTextFromDropdown(genConfSelect,rval);
		thread();
		
		click(stsUpdateBtn);
		thread();
		
		getObjectText(msgNotificationBar);
		thread();
		takeScreenshot();
		verifyAssertEquals("No of Records per page is updated successfully!",getActualObjectTxt);
		thread();
		
		click(locationsInMainMenu);
		thread();
		getSelectedTxtFromDropdown(locListViewLength);
		thread();
		System.out.println(getVisibleTxt);
		verifyAssertEquals(rval,getVisibleTxt);
		thread();
		
	}
	
	@Test(priority=10)
	void resetDefault() throws InterruptedException, IOException
	{
		
		click(clkUserNameBtn);
		thread();
		click(profileInDropDown);
		thread();
		click(genConfigTab);
		thread();
		
		try{
			System.out.println("reset operation");
		click(resetDefaultBtn);
		thread();
		getObjectText(msgNotificationBar);
		thread();
		takeScreenshot();
		verifyAssertEquals("General configuration reset successfully!",getActualObjectTxt);
		thread();
		
		click(locationsInMainMenu);
		thread();
		getSelectedTxtFromDropdown(locListViewLength);
		thread();
		verifyAssertEquals("25",getVisibleTxt);
		thread();
		}
		catch(Exception e)
		{
			System.out.println("Reset button is in disabled mode as No.of records per page is already set to 25");
			System.out.println(e);
		
		}thread();
	}
		
	
}
