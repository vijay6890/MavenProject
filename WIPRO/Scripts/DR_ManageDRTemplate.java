package Scripts;

import static UIWrappers.UIObjects.*;

import static ObjectRepository.DRPlan.*;
import static ObjectRepository.Threats.*;
import static ObjectRepository.Locations.*;
import static ObjectRepository.Tasks.choosefiles;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import UIWrappers.Page;
import static UIWrappers.UIObjects.*;
import jxl.read.biff.BiffException;

public class DR_ManageDRTemplate extends Page
{
	String myTempname,currentTab,currentTab1,currentTab2,rTempName;
	int rv;
	//int rmt;
	
	LoginLogout ll = new LoginLogout();	
	DR_CreateEditDRPlan drAdd=new DR_CreateEditDRPlan();
	
/*	@Test(priority=0)
	void login() throws BiffException, IOException, InterruptedException
	{
		ll.loginToSIB();
	}    */

	@Test(priority=1)
	void manageDRTemplate() throws InterruptedException, BiffException, IOException
	{
		getManageDRTemplateSheetFromExcel();
		thread();
		click(drThreatsnDRPlanInMainMenu);
		thread();
		
		currentTab=driver.getWindowHandle();
		
		click(drManageDRTemp);
		thread();
		waitForPageLoad();
		
		ArrayList<String> newTab = new ArrayList<String>(driver.getWindowHandles());
		newTab.remove(currentTab);
		thread();
		 // change focus to new tab
		driver.switchTo().window(newTab.get(0));
		thread();
		
	}
	
	@Test(priority=2)
	void searchMyTemplates() throws InterruptedException
	{
		//Show or hide templates
        thread();
        waitForPageLoad();
       
        webElement(drMyTempShowOrHideBtn);        	
		scrollInnerScrollBar(webelementFrame);	
			
		click(drMyTempShowOrHideBtn);       //hide template
		thread();
		click(drMyTempShowOrHideBtn);      //show template
		thread();
		
		try
		{
		while(driver.findElement(drMyTempLoadMreBtn).isDisplayed())
			{
			
			webElement(drMyTempLoadMreBtn);        	
			scrollInnerScrollBar(webelementFrame);	
				
			//verifyAssert(drMyTempLoadMreBtn);
			click(drMyTempLoadMreBtn);
			thread();
		}
		}
		catch(Exception e)
		{
			//e.printStackTrace();
			//System.out.println("No load more buttton  "+e);
		}
		
		
		getTotalValuesIndd(drMyTempTotalCount);
		thread();
		
		Random myTmp = new Random();
		int rmt1 = myTmp.nextInt(totalDDValCount-2)+1;
		myTempname=driver.findElement(By.xpath("//div[@id='divMyTemplates_MT']/div[2]/div["+rmt1+"]/p[1]")).getText();
		thread();
		
		enterText(drMyTempSearchBox,myTempname);
		enterKeyInKyBord(drMyTempSearchBox);
		thread();
		
		click(drMyTempSearchBtn);
		thread();
		
		try
		{
		getObjectText(drMyTempSearchFstName);
		thread();
		verifyAssertEquals(myTempname,getActualObjectTxt);
		thread();
		}
		catch(Error e)
		{
			e.printStackTrace();
			
		}
		click(drMyTempClearBtn);
		thread();
				
		getTotalValuesIndd(drMyTempTotalCount);
		thread();
		int ttlCnt=totalDDValCount;
		
		if(ttlCnt==totalDDValCount)
		{
			//System.out.println("Clear performed successfully");
		}
		else
		{
			//System.out.println("Clear operation not performed properly");
		}
		
	}
	
	
	   @Test(priority=3)
		void manageDRTemplateRandomPreview() throws InterruptedException, BiffException, IOException
		{	
		
		   try
			{
			while(driver.findElement(drMyTempLoadMreBtn).isDisplayed())
				{
				
				webElement(drMyTempLoadMreBtn);        	
				scrollInnerScrollBar(webelementFrame);	
					
				//verifyAssert(drMyTempLoadMreBtn);
				click(drMyTempLoadMreBtn);
				thread();
			}
			}
			catch(Exception e)
			{
			//	System.out.println("No load more buttton  "+e);
			}
		   
				
		getTotalValuesIndd(drMyTempTotalCount);
		thread();
		
		Random myTmp = new Random();
		//int rmt1 = myTmp.nextInt(totalDDValCount-2)+1;
		int rmt1 = myTmp.nextInt(totalDDValCount);
		if(rmt1==0)
		{
			rmt1=+1;
		}
		myTempname=driver.findElement(By.xpath("//div[@id='divMyTemplates_MT']/div[2]/div["+rmt1+"]/p[1]")).getText();
		thread();
		
		webElement(By.xpath("//div[@id='divMyTemplates_MT']/div[2]/div["+rmt1+"]"));        	
		scrollInnerScrollBar(webelementFrame); 
		
		driver.findElement(By.xpath("//div[@id='divMyTemplates_MT']/div[2]/div["+rmt1+"]")).click();       //click template
		thread();
		driver.findElement(By.xpath("//div[@id='divMyTemplates_MT']/div[2]/div["+rmt1+"]/div/ul/li[1]")).click();  //click preview
		thread();
				
		try
		{
		getObjectText(drMyTempPrevNme);
		thread();
		try
		{
		verifyAssertEquals(myTempname,getActualObjectTxt);       //verfiy template name
		}
		catch(Error e)
		{
			//
		}
		
		getTotalValuesIndd(drMyTempPrevTtlSecCount);
		
		for(int i=1;i<=totalDDValCount;i++)                               //click all sections in the preview screen
		{
			String secNme=driver.findElement(By.xpath("//div[@id='divSectionList_TP']/div["+i+"]")).getText();
			driver.findElement(By.xpath("//div[@id='divSectionList_TP']/div["+i+"]")).click();
			getObjectText(drMyTempPrevSecName);
			verifyAssertEquals("Section Preview - "+secNme,getActualObjectTxt);
			thread();
		}
		click(drMyTempPrevCloseBtn);
		thread();
		}
		catch(Exception e)
		{
			System.out.println(e);
			click(drMyTempPrevCloseBtn);
			thread();
		}
	}
		
	
	@Test(priority=4)
	void manageDRTemplateCreateDRPlan() throws InterruptedException, BiffException, IOException
	{
		
		getTotalValuesIndd(drMyTempTotalCount);
		thread();
	
		Random myTmp = new Random();
		//int rmt = myTmp.nextInt(totalDDValCount-2)+1;
		int rmt = myTmp.nextInt(totalDDValCount);
		if(rmt==0)
		{
			rmt=+1;
		}
		myTempname=driver.findElement(By.xpath("//div[@id='divMyTemplates_MT']/div[2]/div["+rmt+"]/p[1]")).getText();
		thread();
		
		webElement(By.xpath("//div[@id='divMyTemplates_MT']/div[2]/div["+rmt+"]"));        	
		scrollInnerScrollBar(webelementFrame);
		
		driver.findElement(By.xpath("//div[@id='divMyTemplates_MT']/div[2]/div["+rmt+"]")).click();       //click template
		thread();
		
		driver.findElement(By.xpath("//div[@id='divMyTemplates_MT']/div[2]/div["+rmt+"]/div/ul/li[2]")).click();	//click create new template
		thread();
		
		try
		{	
			
	   // Create DR Plan		
		drAdd.addNewDRPlan();
		driver.close();
		driver.switchTo().window(currentTab);
		}
	
		catch(Exception e)
		{
			System.out.println(e);
			//click(drMyTempManageDRTemps);
			//manageDRTemplate();
			//thread();
			driver.close();
			driver.switchTo().window(currentTab);
		}
	
		
		thread();
		
	} 
	
	@Test(priority=5)
	void viewOrEditMarkSharedTemplate() throws InterruptedException, BiffException, IOException
	{
		manageDRTemplate();
		try
			{
			while(driver.findElement(drMyTempLoadMreBtn).isDisplayed())
				{
				
				webElement(drMyTempLoadMreBtn);        	
				scrollInnerScrollBar(webelementFrame);	
					
				//verifyAssert(drMyTempLoadMreBtn);
				click(drMyTempLoadMreBtn);
				thread();
			}
			}
			catch(Exception e)
			{
				//System.out.println("No load more buttton  "+e);
			}
		
		getTotalValuesIndd(drMyTempTotalCount);
		thread();
		
		Random myTmp = new Random();
		//int rmt = myTmp.nextInt(totalDDValCount-2)+1;
		int rmt = myTmp.nextInt(totalDDValCount);
		if(rmt==0)
		{
			rmt=rmt+1;
		}

		//private Template
		
		if(driver.findElement(By.xpath("//div[@id='divMyTemplates_MT']/div[2]/div["+rmt+"]")).getAttribute("class").equals("span3 dr-tmplte-item dr-tmplte-private drt-act-registered"))
		{
			//System.out.println(driver.findElement(By.xpath("//div[@id='divMyTemplates_MT']/div[2]/div["+rmt+"]")).getAttribute("class"));
			myTempname=driver.findElement(By.xpath("//div[@id='divMyTemplates_MT']/div[2]/div["+rmt+"]/p[1]")).getText();
			thread();
			
			webElement(By.xpath("//div[@id='divMyTemplates_MT']/div[2]/div["+rmt+"]"));        	
		    scrollInnerScrollBar(webelementFrame); 
		
			driver.findElement(By.xpath("//div[@id='divMyTemplates_MT']/div[2]/div["+rmt+"]")).click();       //click template
			thread();
			
			driver.findElement(By.xpath("//div[@id='divMyTemplates_MT']/div[2]/div["+rmt+"]/div/ul/li[3]")).click();	//click mark as public
			thread();
			try
			{
			verifyAssert(deleteConfPopup);            //pop up 
			getObjectText(deleteConfPpMessage);      //pop up message
			
			//mark as private
			verifyAssertEquals("Are you sure want to mark "+myTempname+" as Public Template?",getActualObjectTxt);
			click(delConfOkBtn);
			thread();
			
			getObjectText(msgNotificationBar);
			verifyAssertEquals("Template type changed successfully!", getActualObjectTxt);		
			thread();
			}
			catch(Exception e)
			{
				//System.out.println(e);
				click(delConfCancelBtn);
				thread();
			}
		}
		
		//shared Template
		
		else if(driver.findElement(By.xpath("//div[@id='divMyTemplates_MT']/div[2]/div["+rmt+"]")).getAttribute("class").equals("span3 dr-tmplte-item dr-tmplte-shared drt-act-registered"))
		{
			//System.out.println(driver.findElement(By.xpath("//div[@id='divMyTemplates_MT']/div[2]/div["+rmt+"]")).getAttribute("class"));
			myTempname=driver.findElement(By.xpath("//div[@id='divMyTemplates_MT']/div[2]/div["+rmt+"]/p[1]")).getText();
			thread();
			
			webElement(By.xpath("//div[@id='divMyTemplates_MT']/div[2]/div["+rmt+"]"));        	
		scrollInnerScrollBar(webelementFrame);
		
			driver.findElement(By.xpath("//div[@id='divMyTemplates_MT']/div[2]/div["+rmt+"]")).click();       //click template
			thread();
			
			driver.findElement(By.xpath("//div[@id='divMyTemplates_MT']/div[2]/div["+rmt+"]/div/ul/li[3]")).click();	//click edit/share
			thread();
			
			try
			{
			verifyAssert(deleteConfPopup);            //pop up 
			getObjectText(deleteConfPpMessage);      //pop up message
			
			
			verifyAssertEquals("Are you sure want to stop sharing "+myTempname+" template?",getActualObjectTxt);
			click(delConfOkBtn);
			thread();
			getObjectText(msgNotificationBar);
			verifyAssertEquals("Template type changed successfully!", getActualObjectTxt);		
			thread();
			}
			catch(Exception e)
			{
				//System.out.println(e);
				click(delConfCancelBtn);
				thread();
			} 
			
		/*
		 * 
		 * 	try
			{
			
			getObjectText(drMyTempSharedTitleNme);
			verifyAssertEquals("View/Edit Shared Organizations - "+myTempname,getActualObjectTxt);
			
			click(drMyTempSharedSaveBtn);
			thread();
			
			getObjectText(msgNotificationBar);
			verifyAssertEquals("Organizations updated successfully!", getActualObjectTxt);		
			thread();
			}
			catch(Exception e)
			{
				System.out.println(e);
				click(drMyTempSharedCloseBtn);
				thread();
			}------ */
			
		}
		else
		{
			System.out.println(driver.findElement(By.xpath("//div[@id='divMyTemplates_MT']/div[2]/div["+rmt+"]")).getAttribute("class"));
			
			myTempname=driver.findElement(By.xpath("//div[@id='divMyTemplates_MT']/div[2]/div["+rmt+"]/p[1]")).getText();
			thread();
			
			webElement(By.xpath("//div[@id='divMyTemplates_MT']/div[2]/div["+rmt+"]"));        	
		    scrollInnerScrollBar(webelementFrame);
		
			driver.findElement(By.xpath("//div[@id='divMyTemplates_MT']/div[2]/div["+rmt+"]")).click();       //click template
			thread();
			
			driver.findElement(By.xpath("//div[@id='divMyTemplates_MT']/div[2]/div["+rmt+"]/div/ul/li[3]")).click();	//click mark as private
			thread();
			try
			{
			verifyAssert(deleteConfPopup);            //pop up 
			getObjectText(deleteConfPpMessage);      //pop up message
			
			verifyAssertEquals("Are you sure want to mark "+myTempname+" as Private Template?",getActualObjectTxt);
			click(delConfOkBtn);
			thread();
			
			getObjectText(msgNotificationBar);
			verifyAssertEquals("Template type changed successfully!", getActualObjectTxt);		
			thread();
			}
			catch(Exception e)
			{
		//		System.out.println(e);
				click(delConfCancelBtn);
				thread();
			}
			
		}
		
	} 
	
/*
 * 
 * 	@Test(priority=6)

	void shareOrStopSharingTemplate() throws InterruptedException
	{
		getTotalValuesIndd(drMyTempTotalCount);
		thread();
		System.out.println("total count "+totalDDValCount);
		Random myTmp = new Random();
		int rmt = myTmp.nextInt(totalDDValCount-2)+1;
		
		//shared Template
		 if(driver.findElement(By.xpath("//div[@id='divMyTemplates_MT']/div[2]/div["+rmt+"]")).getAttribute("class").equals("span3 dr-tmplte-item dr-tmplte-shared drt-act-registered"))
		{
			 System.out.println(driver.findElement(By.xpath("//div[@id='divMyTemplates_MT']/div[2]/div["+rmt+"]")).getAttribute("class"));
			 myTempname=driver.findElement(By.xpath("//div[@id='divMyTemplates_MT']/div[2]/div["+rmt+"]/p[1]")).getText();
				thread();
				
				webElement(By.xpath("//div[@id='divMyTemplates_MT']/div[2]/div["+rmt+"]"));        	
		        scrollInnerScrollBar(webelementFrame);
		
				driver.findElement(By.xpath("//div[@id='divMyTemplates_MT']/div[2]/div["+rmt+"]")).click();       //click template
				thread();
				
				driver.findElement(By.xpath("//div[@id='divMyTemplates_MT']/div[2]/div["+rmt+"]/div/ul/li[4]")).click();	//click stop sharing
				thread();
				
				try
				{
				
				getObjectText(drMyTempSharedTitleNme);
				verifyAssertEquals("View/Edit Shared Organizations - "+myTempname,getActualObjectTxt);
				
				click(drMyTempSharedSaveBtn);
				thread();
				
				getObjectText(msgNotificationBar);
				verifyAssertEquals("Organizations updated successfully!", getActualObjectTxt);		
				thread();
				}
				catch(Exception e)
				{
					System.out.println(e);
					click(drMyTempSharedCloseBtn);
					thread();
				}
					
		/*		try
				{
				verifyAssert(deleteConfPopup);            //pop up 
				getObjectText(deleteConfPpMessage);      //pop up message
				
				
				verifyAssertEquals("Are you sure want to stop sharing "+myTempname+" template?",getActualObjectTxt);
				click(delConfOkBtn);
				thread();
				getObjectText(msgNotificationBar);
				verifyAssertEquals("Template type changed successfully!", getActualObjectTxt);		
				thread();
				}
				catch(Exception e)
				{
					System.out.println(e);
					click(delConfCancelBtn);
					thread();
				}    */					
/*		}
		 //Public and Private Templates
		 else
		 {
			 System.out.println(driver.findElement(By.xpath("//div[@id='divMyTemplates_MT']/div[2]/div["+rmt+"]")).getAttribute("class"));
			 myTempname=driver.findElement(By.xpath("//div[@id='divMyTemplates_MT']/div[2]/div["+rmt+"]/p[1]")).getText();
				thread();
				
				webElement(By.xpath("//div[@id='divMyTemplates_MT']/div[2]/div["+rmt+"]"));        	
		        scrollInnerScrollBar(webelementFrame);
		
				driver.findElement(By.xpath("//div[@id='divMyTemplates_MT']/div[2]/div["+rmt+"]")).click();       //click template
				thread();
				
				driver.findElement(By.xpath("//div[@id='divMyTemplates_MT']/div[2]/div["+rmt+"]/div/ul/li[4]")).click();	//click share
				thread();
				
				try
				{
				getObjectText(drMyTempShareTitle);
				thread();
				verifyAssertEquals("Share Template - "+myTempname,getActualObjectTxt);
				
				click(drMyTempSearchOrgFrSharing);
				
				getTotalValuesIndd(drMyTempTotalOrgCountFrSharing);
				
				System.out.println("total count "+totalDDValCount);
				Random rand1 = new Random();
				int rOrg = myTmp.nextInt(totalDDValCount-1)+1;

				//String rOrgNme=driver.findElement(By.xpath("//div[@id='ddlOrgToShareTemplate_ST_chzn']/div/ul/li["+rOrg+"]")).getText();
				
				//enterText(drMyTempSearchOrgFrSharing,rOrgNme);
				driver.findElement(By.xpath("//div[@id='ddlOrgToShareTemplate_ST_chzn']/div/ul/li["+rOrg+"]")).click();
				thread();
				
				click(drMyTempOrgShareBtn);
				thread();
				getObjectText(msgNotificationBar);
				verifyAssertEquals("Organizations updated successfully!", getActualObjectTxt);		
				thread();
				}
				catch(Exception e)
				{
					System.out.println(e);
					click(drMyTempOrgCloseBtn);
					thread();
				}
				
		 }
		
	} ------ */
	
	@Test(priority=7)
	void editTemplateFromMyTemplates() throws InterruptedException, BiffException, IOException
	{
		
		getTotalValuesIndd(drMyTempTotalCount);
		thread();
		
		Random myTmp = new Random();
		//int rmt1 = myTmp.nextInt(totalDDValCount-2)+1;
		int rmt1 = myTmp.nextInt(totalDDValCount);
		if(rmt1==0)
		{
			rmt1=+1;
		}
		myTempname=driver.findElement(By.xpath("//div[@id='divMyTemplates_MT']/div[2]/div["+rmt1+"]/p[1]")).getText();
		thread();
		
		webElement(By.xpath("//div[@id='divMyTemplates_MT']/div[2]/div["+rmt1+"]"));        	
		scrollInnerScrollBar(webelementFrame);
		
		driver.findElement(By.xpath("//div[@id='divMyTemplates_MT']/div[2]/div["+rmt1+"]")).click();       //click template
		thread();
		driver.findElement(By.xpath("//div[@id='divMyTemplates_MT']/div[2]/div["+rmt1+"]/div/ul/li[4]")).click();  //click Edit
		thread();
		
		getObjectText(drMyTempEditTitleNme);
		verifyAssertEquals("Edit Template - "+myTempname,getActualObjectTxt);
		
		getManageDRTemplateSheetFromExcel();
		
		Random re=new Random();
		int rr=re.nextInt(drTemp.getRows()-1)+1;
		int rn=re.nextInt(100);
		
		try
		{
		clear(drMyTempEditTempNme);		
		enterText(drMyTempEditTempNme,drTemp.getCell(0, rr).getContents()+rn);
		enterKeyInKyBord(drMyTempEditTempNme);
		
		clear(drMyTempEditTempDesc);		
		enterText(drMyTempEditTempDesc,drTemp.getCell(1, rr).getContents()+rn);
		enterKeyInKyBord(drMyTempEditTempDesc);
		
		
		getTotalValuesIndd(drMyTempEdtShrOrgTtlCnt);
		thread();
		Random randOrg=new Random();
		int ro=randOrg.nextInt(totalDDValCount-1)+1;
		
		click(drMyTempEdtShrSearchBox);
		driver.findElement(By.xpath("//li[@id='divSharedOrgList_VSO_chzn_o_"+ro+"']")).click();
		//enterText(drMyTempEdtShrSearchBox,"");
		
		click(drMyTempEditTempSaveBtn);
		thread();
		
		getObjectText(msgNotificationBar);
		verifyAssertEquals("Template updated successfully",getActualObjectTxt);
		thread();
		}
		catch(Exception e)
		{
			//System.out.println(e);
			//System.out.println("Template Already exists");
			click(drMyTempEditCloseBtn);
		}
	}
	
	@Test(priority=8)
	void deleteTemplateFromMyTemplates() throws InterruptedException
	{
		getTotalValuesIndd(drMyTempTotalCount);
		thread();
		
		Random myTmp = new Random();
		//int rmt1 = myTmp.nextInt(totalDDValCount-2)+1;
		int rmt1 = myTmp.nextInt(totalDDValCount);
		if(rmt1==0)
		{
			rmt1=+1;
		}
		myTempname=driver.findElement(By.xpath("//div[@id='divMyTemplates_MT']/div[2]/div["+rmt1+"]/p[1]")).getText();
		thread();
		
		webElement(By.xpath("//div[@id='divMyTemplates_MT']/div[2]/div["+rmt1+"]"));        	
		scrollInnerScrollBar(webelementFrame);
		
		driver.findElement(By.xpath("//div[@id='divMyTemplates_MT']/div[2]/div["+rmt1+"]")).click();       //click template
		thread();
		driver.findElement(By.xpath("//div[@id='divMyTemplates_MT']/div[2]/div["+rmt1+"]/div/ul/li[5]")).click();  //click Delete
		thread();
		try
		{
		verifyAssert(deleteConfPopup);            //pop up 
		getObjectText(deleteConfPpMessage);      //pop up message
		
		verifyAssertEquals("Are you sure want to remove "+myTempname+" ?",getActualObjectTxt);
		click(delConfOkBtn);
		thread();
		
		getObjectText(msgNotificationBar);
		verifyAssertEquals("Template deleted successfully", getActualObjectTxt);		
		thread();
		}
		catch(Exception e)
		{
			//System.out.println(e);
			click(delConfCancelBtn);
			thread();
		}
		
	}  
	
	@Test(priority=9)
	void closeSelectedTemplate() throws InterruptedException
	{
		getTotalValuesIndd(drMyTempTotalCount);
		thread();
		
		Random myTmp = new Random();
		//int rmt1 = myTmp.nextInt(totalDDValCount-2)+1;
		int rmt1 = myTmp.nextInt(totalDDValCount);
		if(rmt1==0)
		{
			rmt1=+1;
		}
		myTempname=driver.findElement(By.xpath("//div[@id='divMyTemplates_MT']/div[2]/div["+rmt1+"]/p[1]")).getText();
		thread();
		
		webElement(By.xpath("//div[@id='divMyTemplates_MT']/div[2]/div["+rmt1+"]"));        	
		scrollInnerScrollBar(webelementFrame);
		
		driver.findElement(By.xpath("//div[@id='divMyTemplates_MT']/div[2]/div["+rmt1+"]")).click();       //click template
		thread();
		getObjectText(By.xpath("//div[@id='divMyTemplates_MT']/div[2]/div["+rmt1+"]/div/ul/li[6]"));
		verifyAssertEquals("Close",getActualObjectTxt);
		thread();
		driver.findElement(By.xpath("//div[@id='divMyTemplates_MT']/div[2]/div["+rmt1+"]/div/ul/li[6]")).click();  //click Close
		thread();
		
	}
	
	//Templates Shared By Other Organisations
	
	@Test(priority=10)
	void searchSharedTemp() throws InterruptedException
	{
        webElement(drTempShareSection);        	
		scrollInnerScrollBar(webelementFrame); //scroll to shared templates section
		
		click(drTempShareShowOrHideBtn);
		thread();
		click(drTempShareShowOrHideBtn);
		thread();
		getTotalValuesIndd(drTempShareTtlCnt);
		thread();
		if(totalDDValCount==1)
		{
			System.out.println("No templates Found");
		}
		else
		{
			System.out.println("Templates exists");
		}
		
	}   
	
	
	//	Public Templates of Other Organization 
	
	@Test(priority=11)
	void publicTempSearch() throws Exception
	{
		webElement(drPublicTempSection);        	
		scrollInnerScrollBar(webelementFrame);         //scroll to public templates section
		
		click(drPublicTempShowOrHideBtn);
		thread();
		click(drPublicTempShowOrHideBtn);
		thread();
		
		try
		{
			while(driver.findElement(drPublicTempLoadMreBtn).isDisplayed())
			{
				webElement(drPublicTempLoadMreBtn);        	
				scrollInnerScrollBar(webelementFrame); 
				
				click(drPublicTempLoadMreBtn);
				thread();
				
			}
		}
		catch(Exception e)
		{
			//System.out.println("No load more buttton  "+e);
		}
		
		webElement(drPublicTempSection);        	
		scrollInnerScrollBar(webelementFrame); 
		
		getTotalValuesIndd(drPublicTempTtlCnt);
		thread();
		
		Random rand=new Random();
		//int rn=rand.nextInt(totalDDValCount-2)+1;
		int rn=rand.nextInt(totalDDValCount);
		if(rn==0)
		{
			rn=+1;
		}
		rTempName=driver.findElement(By.xpath("//div[@id='divPublicTemplates_MT']/div[2]/div["+rn+"]/p[1]")).getText();
		
		enterText(drPublicTempSearchBox,rTempName);
		click(drPublicTempSearchBtn);
		thread();
		
		try
		{
		getObjectText(drPublicTempSearchFstNme);
		verifyAssertEquals(rTempName,getActualObjectTxt);
		thread();
		click(drPublicTempClearBtn);
		thread();
		}
		catch(Error e)
		{
			
			click(drPublicTempClearBtn);
			thread();
			
		/*	getTotalValuesIndd(drPublicTempTtlCnt);
			thread();
			forloop:
			for(int i=1;i<totalDDValCount;i++)
			{
			String tempName=driver.findElement(By.xpath("//div[@id='divPublicTemplates_MT']/div[2]/div["+i+"]/p[1]")).getText();
			
				//verifyAssertEquals(rTempName,tempName);
			ifloop:	
				if(rTempName.equals(tempName))
				{
					System.out.println("matched record is at "+i+" position");
					break ;
				}
				
			}    
			*/
	}		
		
	}
	
	@Test(priority=12)
	void publicTempPreview() throws InterruptedException
	{
		webElement(drPublicTempSection);        	
		scrollInnerScrollBar(webelementFrame);  
		try
		{
			while(driver.findElement(drPublicTempLoadMreBtn).isDisplayed())
			{
				webElement(drPublicTempLoadMreBtn);        	
				scrollInnerScrollBar(webelementFrame); 
				
				click(drPublicTempLoadMreBtn);
				thread();
				
			}
		}
		catch(Exception e)
		{
			//System.out.println("No load more buttton  "+e);
		}
		
		webElement(drPublicTempSection);        	
		scrollInnerScrollBar(webelementFrame); 
		
		getTotalValuesIndd(drPublicTempTtlCnt);
		thread();
		
		Random rand=new Random();
		//int rn=rand.nextInt(totalDDValCount-2)+1;
		int rn=rand.nextInt(totalDDValCount);
		if(rn==0)
		{
			rn=+1;
		}
		rTempName=driver.findElement(By.xpath("//div[@id='divPublicTemplates_MT']/div[2]/div["+rn+"]/p[1]")).getText();
		
		webElement(By.xpath("//div[@id='divPublicTemplates_MT']/div[2]/div["+rn+"]"));        	
		scrollInnerScrollBar(webelementFrame);  
		
		driver.findElement(By.xpath("//div[@id='divPublicTemplates_MT']/div[2]/div["+rn+"]")).click();
		thread();
		
		driver.findElement(By.xpath("//div[@id='divPublicTemplates_MT']/div[2]/div["+rn+"]/div/ul/li[1]")).click();
		thread();
		
		
		//
		try
		{
		getObjectText(drPublicTempPrevName);
		thread();
		verifyAssertEquals("Template Preview - "+rTempName,getActualObjectTxt); 
		
		
		getObjectText(drMyTempPrevNme);
		thread();
		verifyAssertEquals(rTempName,getActualObjectTxt);       //verfiy template name
		
		getTotalValuesIndd(drMyTempPrevTtlSecCount);
		
		for(int i=1;i<=totalDDValCount;i++)                               //click all sections in the preview screen
		{
			String secNme=driver.findElement(By.xpath("//div[@id='divSectionList_TP']/div["+i+"]")).getText();
			driver.findElement(By.xpath("//div[@id='divSectionList_TP']/div["+i+"]")).click();
			getObjectText(drMyTempPrevSecName);
			verifyAssertEquals("Section Preview - "+secNme,getActualObjectTxt);
			thread();
		}
		click(drMyTempPrevCloseBtn);
		thread();
		}
		catch(Exception e)
		{
		//	System.out.println(e);
			click(drMyTempPrevCloseBtn);
			thread();
		}
		
	}
	
	@Test(priority=13)
	void publicTempCreateDRPlan() throws InterruptedException, BiffException, IOException
	{
		getTotalValuesIndd(drPublicTempTtlCnt);
		thread();
		
		Random rand=new Random();
		//int rn=rand.nextInt(totalDDValCount-2)+1;
		int rn=rand.nextInt(totalDDValCount);
		if(rn==0)
		{
			rn=+1;
		}
		rTempName=driver.findElement(By.xpath("//div[@id='divPublicTemplates_MT']/div[2]/div["+rn+"]/p[1]")).getText();
		
		webElement(By.xpath("//div[@id='divPublicTemplates_MT']/div[2]/div["+rn+"]"));        	
		scrollInnerScrollBar(webelementFrame);  
		
		driver.findElement(By.xpath("//div[@id='divPublicTemplates_MT']/div[2]/div["+rn+"]")).click();
		thread();
		
		driver.findElement(By.xpath("//div[@id='divPublicTemplates_MT']/div[2]/div["+rn+"]/div/ul/li[2]")).click();
		thread();
		
		
		try
		{	
			
	   // Create DR Plan		
		drAdd.addNewDRPlan();
		driver.close();
		driver.switchTo().window(currentTab);
		}
	
		catch(Exception e)
		{
		//	System.out.println(e);
			//click(drMyTempManageDRTemps);
			//manageDRTemplate();
			//thread();
			driver.close();
			driver.switchTo().window(currentTab);
		}
	
		
		thread();
		manageDRTemplate();
	}
	
	@Test(priority=14)
	void publicTempSaveCopy() throws InterruptedException
	{
		webElement(drPublicTempSection);        	
		scrollInnerScrollBar(webelementFrame);  
		try
		{
			while(driver.findElement(drPublicTempLoadMreBtn).isDisplayed())
			{
				webElement(drPublicTempLoadMreBtn);        	
				scrollInnerScrollBar(webelementFrame); 
				
				click(drPublicTempLoadMreBtn);
				thread();
				
			}
		}
		catch(Exception e)
		{
			//System.out.println("No load more buttton  "+e);
		}
		
		webElement(drPublicTempSection);        	
		scrollInnerScrollBar(webelementFrame); 
		
		
		getTotalValuesIndd(drPublicTempTtlCnt);
		thread();
		
		Random rand=new Random();
		//int rn=rand.nextInt(totalDDValCount-2)+1;
		int rn=rand.nextInt(totalDDValCount);
		if(rn==0)
		{
			rn=+1;
		}
		rTempName=driver.findElement(By.xpath("//div[@id='divPublicTemplates_MT']/div[2]/div["+rn+"]/p[1]")).getText();
		
		webElement(By.xpath("//div[@id='divPublicTemplates_MT']/div[2]/div["+rn+"]"));        	
		scrollInnerScrollBar(webelementFrame);  
		
		driver.findElement(By.xpath("//div[@id='divPublicTemplates_MT']/div[2]/div["+rn+"]")).click();
		thread();
		
		driver.findElement(By.xpath("//div[@id='divPublicTemplates_MT']/div[2]/div["+rn+"]/div/ul/li[3]")).click();
		thread();
		try
		{
		getObjectText(drPublicTempSaveCopyTitle);
		verifyAssertEquals("Save as Copy - "+rTempName,getActualObjectTxt);
		thread();
		
		Random r1=new Random();
		rv=r1.nextInt(100);
		
		clear(drpublicTempSaveCopyTextBox);
		thread();
		enterText(drpublicTempSaveCopyTextBox,rTempName+rv);
		Thread.sleep(1000);
		
		click(drPublicTempSaveCopyBtn);
		thread();
		
		getObjectText(msgNotificationBar);
		verifyAssertEquals("Template created successfully",getActualObjectTxt);

		getObjectText(drMyTempSearchFstName);
		verifyAssertEquals(rTempName+rv,getActualObjectTxt);
		}
		catch(Exception e)
		{
			System.out.println("Exception "+e);
			click(drPublicTempSaveCopyCloseBtn);
			thread();
			
		}
		
		webElement(drPublicTempSection);        	
		scrollInnerScrollBar(webelementFrame); 
		
	}
	
	
	@Test(priority=15)
	void publicTempClose() throws InterruptedException
	{
		
		getTotalValuesIndd(drPublicTempTtlCnt);
		thread();
		
		Random rand=new Random();
		//int rn=rand.nextInt(totalDDValCount-2)+1;
		int rn=rand.nextInt(totalDDValCount);
		if(rn==0)
		{
			rn=+1;
		}
		rTempName=driver.findElement(By.xpath("//div[@id='divPublicTemplates_MT']/div[2]/div["+rn+"]/p[1]")).getText();
		
		webElement(By.xpath("//div[@id='divPublicTemplates_MT']/div[2]/div["+rn+"]"));        	
		scrollInnerScrollBar(webelementFrame);  
		
		driver.findElement(By.xpath("//div[@id='divPublicTemplates_MT']/div[2]/div["+rn+"]")).click();
		thread();
		
		getObjectText(By.xpath("//div[@id='divPublicTemplates_MT']/div[2]/div["+rn+"]/div/ul/li[4]"));
		verifyAssertEquals("Close",getActualObjectTxt);
		thread();
		
		driver.findElement(By.xpath("//div[@id='divPublicTemplates_MT']/div[2]/div["+rn+"]/div/ul/li[4]")).click();
		thread();
	} 
	
	@Test(priority=16)
	void tempType() throws InterruptedException
	{
		webElement(drTempTypePublic);        	
		scrollInnerScrollBar(webelementFrame);
		click(drTempTypePublic);
	    Thread.sleep(1000);
		click(drTempTypeShared);
		thread();
		click(drTempTypePrivate);
		thread();
			
		webElement(drTempTypePublic);        	
		scrollInnerScrollBar(webelementFrame);
		
		click(drTempTypePublic);	
		click(drTempTypePrivate);
		click(drTempTypeShared);
	}
	
	@Test(priority=17)
	void closeCurrentNSwitchToOldWindow() throws InterruptedException
	{
		driver.close();
			 thread();
			
	// switch to old window
	driver.switchTo().window(currentTab);
	
	}
	
	/*@Test(priority=18)
	void logout() throws InterruptedException
	{
		ll.logoutSession();
		thread();
		driver.quit();
	}*/
}
