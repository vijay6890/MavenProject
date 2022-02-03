package Scripts;
/***************************************************************************************************
 * 
 * @author Tamizhselvi
 * 
 * 
 * 
****************************************************************************************************/
import static Config.TakScreenshot.takeScreenshot;
import static ObjectRepository.Incidents.*;

import static UIWrappers.UIObjects.*;
import static ObjectRepository.Locations.*;
import static ObjectRepository.Tasks.tskSubmitBtn;
import static ObjectRepository.Threats.*;

import java.io.IOException;
import java.util.Random;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import UIWrappers.Page;
import jxl.read.biff.BiffException;

public class Inc_DeleteMassEdit extends Page{

	LoginLogout ll = new LoginLogout();
	Loc_AddnDeleteMultiple loc_AddnDeleteMultiple = new Loc_AddnDeleteMultiple();
	String mEdtIncidentType;
	
	Inc_AddEdit inc_AddEdit = new Inc_AddEdit();
	
 /* @Test(priority=0)
	void login() throws BiffException, IOException, InterruptedException
	{
		ll.loginToSIB();
		waitForPageLoad();
	}*/
	 
	@Test(priority=1)
	void checkSelectRecordsToPerformEditMsg() throws IOException, InterruptedException
	{
		webElement(alertBtnInTopMenu);
		scrollInnerScrollBar(webelementFrame);
		click(alertBtnInTopMenu);
		thread();
		click(incidentsMenu);
		thread();
	}
	
/*	@Test
	void massEdit() throws InterruptedException, IOException
	{
	    click(incMassEditBtn);
	    thread();
	    getObjectText(msgNotificationBar);
		verifyAssertEquals(loc_AddnDeleteMultiple.selectRecordstoPerformEditOperation, getActualObjectTxt);
		takeScreenshot();
		thread();
	    
	 }
	
	@Test(priority=2)
	void massEditIncident() throws BiffException, IOException, InterruptedException
	{

		click(alertBtnInTopMenu);
		click(incidentsMenu);
	    waitForPageLoad();
		
	    getIncidentsSheetFromExcel();
		
		click(incFirstRow );
		click(incSecondRow);
		thread();
		click(incMassEditBtn);
		thread();
		
		//	Verify Please Fill at least anyone field to update message
		click(incmEdtSubmitBtn);
		thread();
		getObjectText(msgNotificationBar);
		verifyAssertEquals(loc_AddnDeleteMultiple.fillAtleastOneFieldToUpdate, getActualObjectTxt);
		takeScreenshot();
		thread(); 
		
	    // Incident Type
		Random rrow = new Random();
		int inctype = rrow.nextInt(inc.getRows()-1+1);
		mEdtIncidentType = inc.getCell(1,inctype).getContents();
	    enterText(incmEdtIncidentType,mEdtIncidentType);
	    thread();
	    
	    // Severity Level 		    
	    click(incmEdtSeverityLvlArrow);
		getTotalValuesIndd(incmEdtSeverityLvlCnt);
		
		if(totalDDValCount > 1)
		{
			Random ran = new Random ();
			int rsl = ran.nextInt(totalDDValCount-1)+1;
			enterText(incmEdtSeverityLvlSearchBox,driver.findElement(By.id("incident_bulk_severity_chzn_o_"+rsl)).getText());
			enterKeyInKyBord(incmEdtSeverityLvlSearchBox);
		}
	
		//Are you Affected			
		click(incmEdtAreyouAffArrow);
		getTotalValuesIndd(incmEdtAreyouAffCnt);
		
		if(totalDDValCount > 1)
		{
			Random ran = new Random ();
			int rayaff= ran.nextInt(totalDDValCount-1)+1;
			enterText(incmEdtAreyouAffSearchBox,driver.findElement(By.id("incident_bulk_affected_chzn_o_"+rayaff)).getText());
			enterKeyInKyBord(incmEdtAreyouAffSearchBox);
		}
		
		
		click(incmEdtSubmitBtn);
		thread();
		getObjectText(msgNotificationBar);
		verifyAssertEquals("Successfully Updated 2 Record(s)", getActualObjectTxt);		
		thread();
		
	}
	
	@Test(priority=2)
	void verifyMassEditDtlsInListView() throws InterruptedException
	{
		for(int i=1; i<3; i++)
		{
			String incidenTypeInListView = driver.findElement(By.xpath("//table[@id='incident_table']/tbody/tr["+i+"]/td[4]")).getText();
		
		if(incidenTypeInListView.contains(mEdtIncidentType))
		{
		for(int j=4; j<8; j++)
		{
			String mEdtdDtlsInLst = driver.findElement(By.xpath("//table[@id='incident_table']/tbody/tr["+i+"]/td["+j+"]")).getText();
			
			if(j==4)
			{
				//Check Incident Type
				verifyAssertEquals(mEdtIncidentType, mEdtdDtlsInLst);
		    }
		}
	    }thread();
	    }
	
	}*/
	

	@Test(priority=3)
	void deletebFunc() throws InterruptedException, IOException, BiffException
	{

	    inc_AddEdit.verifyAddNewIncPage();
		inc_AddEdit.addNewIncident();
		thread();
	    //update status to closed
	    
	    verifyAssert(By.xpath("//table[@id='incident_table']/tbody/tr[1]/td[8]/div/a"));
		thread();
		click(By.xpath("//table[@id='incident_table']/tbody/tr[1]/td[8]/div/a"));
		thread();
	    click(By.xpath("//table[@id='incident_table']/tbody/tr[1]/td[8]/div/ul/li[3]"));
		thread();
		
		getObjectText(incResTitle);
		verifyAssertEquals("Update "+inc_AddEdit.incidentLocation+" status to Closed",getActualObjectTxt);
		thread();
	
	    WebElement ifram = driver.findElement(By.xpath("//div[@id='statusChangePopUp']/div[2]/div/div/div/iframe"));
		driver.switchTo().frame(ifram);			
		enterText(scEdtrCommentsField, inc.getCell(4,3).getContents());
		switchBackFromFrame();
		thread();
		
		click(updatBtn);
		thread();
		
		//getObjectText(deleteConfPpMessage); 
		getObjectText(msgNotificationBar); 
		verifyAssertEquals("Incident status updated successfully",getActualObjectTxt);
		thread();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//table[@id='incident_table']/tbody/tr[1]/td[8]/span/a")).click();
		thread();		
        verifyAssert(deleteConfPopup);		
		getObjectText(deleteConfPpMessage);
		verifyAssertEquals("Confirm to remove Incident   "+inc_AddEdit.incidentLocation+" ?",getActualObjectTxt);
		Thread.sleep(1000);
		click(delConfOkBtn);
		Thread.sleep(1000);
		getObjectText(msgNotificationBar);
		verifyAssertEquals("  "+inc_AddEdit.incidentLocation+" Successfully Deleted!", getActualObjectTxt);
		takeScreenshot();
		
/*		getTotalValuesIndd(incIncidentLocListviewTbCnt);
		
		Random random = new Random();
		int del = random.nextInt(totalDDValCount-1)+1;
		WebElement delBtn = driver.findElement(By.xpath("//table[@id='incident_table']/tbody/tr["+del+"]/td[7]/a[2]"));
		
		delBtn.click();
		verifyAssert(deleteConfPopup);
		
		getObjectText(deleteConfPpMessage);
		inc_AddEdit.incidentLocation= driver.findElement(By.xpath("//table[@id='incident_table']//tr["+del+"]/td[2]")).getText();
		
		
       //Check Delete Confirmation pop up message
		getObjectText(deleteConfPpMessage);
		verifyAssertEquals("Confirm to remove Incident "+inc_AddEdit.incidentLocation+"?", getActualObjectTxt);
		
		click(delConfOkBtn);
		thread();
		
		getObjectText(msgNotificationBar);
		
		verifyAssertEquals(inc_AddEdit.incidentLocation+" Successfully Deleted!", getActualObjectTxt);
		takeScreenshot();*/
	}
		
	@Test(priority=4, invocationCount=2)
	void addNewIncidentName() throws BiffException, IOException, InterruptedException
	{
		
		
		inc_AddEdit.verifyAddNewIncPage();
		inc_AddEdit.addNewIncident();
		Thread.sleep(1000);
	}
	
	@Test(priority=5)
	void deleteMultipleIncident() throws InterruptedException, IOException, BiffException
	{
		
		
		for(int i=1; i<3; i++)
			
		{
			String incLoc=driver.findElement(By.xpath("//table[@id='incident_table']/tbody/tr["+i+"]/td[2]")).getText();
			
			 verifyAssert(By.xpath("//table[@id='incident_table']/tbody/tr["+i+"]/td[8]/div/a"));
				thread();
				click(By.xpath("//table[@id='incident_table']/tbody/tr["+i+"]/td[8]/div/a"));
				thread();
			    click(By.xpath("//table[@id='incident_table']/tbody/tr["+i+"]/td[8]/div/ul/li[3]"));
				thread();
				
				getObjectText(incResTitle);
				verifyAssertEquals("Update "+incLoc+" status to Closed",getActualObjectTxt);
				thread();
			
			    WebElement ifram = driver.findElement(By.xpath("//div[@id='statusChangePopUp']/div[2]/div/div/div/iframe"));
				driver.switchTo().frame(ifram);			
				enterText(scEdtrCommentsField, inc.getCell(4,3).getContents());
				switchBackFromFrame();
				thread();
				
				click(updatBtn);
				thread();
				
				//getObjectText(deleteConfPpMessage); 
				getObjectText(msgNotificationBar); 
				verifyAssertEquals("Incident status updated successfully",getActualObjectTxt);
				thread();
				
		/*	Random random = new Random();
			getTotalValuesIndd(incIncidentLocListviewTbCnt);
			int rincidentLoc = random.nextInt(totalDDValCount-1)+1;
			
			driver.findElement(By.xpath("//table[@id='incident_table']//tr["+rincidentLoc+"]/td/div/span/input")).click();
			thread();*/
		}
		
		for(int i=1;i<=2;i++)
		{
			driver.findElement(By.xpath("//table[@id='incident_table']//tr["+i+"]/td/div/span/input")).click();
			thread();
		}
			
		click(incDeleteBtn);
		takeScreenshot();
		
		waitForElement(deleteConfPopup);
		getObjectText(deleteConfPpMessage);
		
		verifyAssertEquals("Confirm to remove 2 record(s) from Incident?", getActualObjectTxt);
		Thread.sleep(1000);
		click(delConfOkBtn);
		Thread.sleep(1000);
		
		getObjectText(msgNotificationBar);
		verifyAssertEquals("2 records deleted successfully", getActualObjectTxt);		
		takeScreenshot();
		thread();
	}
	
	@Test(priority=6)
	void verifySelectRecordsToPerformDeleteMsg() throws InterruptedException, IOException
	{
		click(incDeleteBtn);
		thread();
		getObjectText(msgNotificationBar);
		verifyAssertEquals(loc_AddnDeleteMultiple.selectRecordsToPerformDelete, getActualObjectTxt);
		thread();
		takeScreenshot();
		Thread.sleep(1000);
	}
	
	/*@Test(priority=7)
	void logout() throws InterruptedException 
	{
		ll.logoutSession();
		thread();
		closeWindow();
	}*/
		
}


