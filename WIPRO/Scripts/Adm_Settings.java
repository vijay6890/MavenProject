package Scripts;

import static ObjectRepository.Admin.*;
import static ObjectRepository.Locations.*;
import static UIWrappers.UIObjects.*;
import static Config.TakScreenshot.*;

import java.io.IOException;
import java.util.Random;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import UIWrappers.Page;
import jxl.read.biff.BiffException;

public class Adm_Settings extends Page {
	LoginLogout ll = new LoginLogout();
	public String orgName,tagLine,rplyToEmail;
	

/*	@Test(priority=0)
	void login() throws BiffException, IOException, InterruptedException 
	{
		ll.loginToSIB();
	}  */
	
	@Test(priority=1)
	void navigateToAdminPage() throws InterruptedException
	{
		click(adminBtnInTopMnu);
		thread();
		click(adminInTopDD);
		thread();
	}

	@Test(priority=2)
    void settingspage() throws BiffException, IOException, InterruptedException
    {
		click(settingsTb);
		thread();
		
		getSettingsSheetFromExcel();
		
		Random rand=new Random();
		System.out.println("rows "+setting.getRows());
		
		int rr=rand.nextInt(setting.getRows()-1)+1;
		System.out.println("row "+rr);
		orgName=setting.getCell(0, rr).getContents();
		tagLine=setting.getCell(1, rr).getContents();
		rplyToEmail=setting.getCell(2,rr).getContents();
		//System.out.println(orgName+"  "+ tagLine+"  "+ rplyToEmail);
		
		clear(stOrganizationName);
		thread();
		enterText(stOrganizationName,orgName);
		thread();
		clear(stTagline);
		thread();
		enterText(stTagline,tagLine);
		thread();
		clear(stReplyToEmailAddress);
		thread();
		enterText(stReplyToEmailAddress,rplyToEmail);
		thread();
		
		getTotalValuesIndd(stCountryTotalVal);
		thread();
		//System.out.println("country total"+totalDDValCount);
		click(stCountryDDArow);
		thread();
		Random rand1=new Random();
		int rc=rand1.nextInt(totalDDValCount-2)+2;
		//System.out.println("Random country val"+rc);
		String randCntryNme=driver.findElement(By.xpath("//div[@id='country_chzn']/div/ul/li["+rc+"]")).getText();
		
		enterText(stCountrySearchBox,randCntryNme);
		thread();
		enterKeyInKyBord(stCountrySearchBox);
		thread();
		
		getTotalValuesIndd(stTimeZoneTotalVal);
		thread();
		//System.out.println("timezone total"+totalDDValCount);
		click(stTimeZoneDDArow);
		thread();
		Random rand2=new Random();
		int rtz=rand2.nextInt(totalDDValCount-2)+2;
	//	System.out.println("Random timezone val"+rtz);
		String randTimeZone=driver.findElement(By.xpath("//div[@id='time_zone_chzn']/div/ul/li["+rtz+"]")).getText();
	//	System.out.println("time zone val"+randTimeZone);
	
		//System.out.println(driver.findElement(By.xpath("//select[@id='time_zone']/option[3]")).getText());
		
		enterText(stTimeZoneSearchBox,randTimeZone);
		thread();
		enterKeyInKyBord(stTimeZoneSearchBox);
		thread();
		
		//System.out.println("Email checkbox");
		try
		{
			if(driver.findElement(stEmailChkBox).getAttribute("checked").equals("true"))
	
		{
			//System.out.println("In enabled mode");
			click(stEmailChkBox);
			thread();
			//System.out.println("Now Disabled");
		}
		}
		catch(Exception e)
		{
			//System.out.println("In disenabled mode");
			click(stEmailChkBox);
			thread();
			//System.out.println("now checked");
		}
		
		//System.out.println("SMS checkbox");
		try
		{
		if(driver.findElement(stSMSChkBox).getAttribute("checked").equals("true"))
		{
		//	System.out.println("In enabled mode");
			click(stSMSChkBox);
			thread();
		//	System.out.println("Now Disabled");
		}
		}
		
	catch(Exception e)
		{
		//System.out.println("In disenabled mode");
		click(stSMSChkBox);
		thread();
		//System.out.println("now checked");
		}
		
		click(stSubmitBtn);
		thread();
		
    }
	
	@Test(priority=3)
	void settingSuccessMsg() throws InterruptedException, IOException
	{
		getObjectText(msgNotificationBar);
		thread();
		verifyAssertEquals("Site settings updated successfully",getActualObjectTxt);
		takeScreenshot();
		thread();
		
	}
	
	@Test(priority=4)
	void updateLogo() throws InterruptedException, IOException
	{
		try
		{
		webElement(stUpldLogoSubmitBtn);
		scrollInnerScrollBar(webelementFrame);
		Thread.sleep(10000);
		click(stChoseUploadLogo);
		uploadFile("/var/lib/jenkins/jobs/SIBSELENIUM/workspace/index1.png");
		Thread.sleep(1000);
		click(stUpldLogoSubmitBtn);
		Thread.sleep(1000);
		getObjectText(msgNotificationBar);
		takeScreenshot();
		verifyAssertEquals("Logo uploaded successfully" , getActualObjectTxt);	
		thread();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	/*@Test(priority=5)
	void logout() throws InterruptedException
	{
		ll.logoutSession();
		thread();
		closeWindow();
	}*/
	
	
}
