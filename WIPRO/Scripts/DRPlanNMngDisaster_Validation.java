package Scripts;

import java.io.IOException;
import java.util.Random;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import UIWrappers.Page;
import jxl.read.biff.BiffException;
import static ObjectRepository.DRPlan.*;
import static ObjectRepository.Locations.*;
import static ObjectRepository.ManageDisaster.*;
import static UIWrappers.UIObjects.*;
import static Config.TakScreenshot.*;

public class DRPlanNMngDisaster_Validation extends Page
{
	LoginLogout ll = new LoginLogout();
	DR_CreateEditDRPlan da=new DR_CreateEditDRPlan();
	MD_DeclareDisaster ma=new MD_DeclareDisaster();
	
	@Test(priority=0)
	void login() throws BiffException, IOException, InterruptedException
	{
		ll.loginToSIB();
	}
	
	@Test(priority=1)
	void checkDRPlanHighlightedInmainMenu() throws InterruptedException
	{
		click(drThreatsnDRPlanInMainMenu);
		thread();
		getHighlightOptn(mainDRPlanOptn);
		verifyAssertEquals("active", getHighlightdTxt);			
	}
	
	@Test(priority=2)
	void checkDRPlanMandatoryFieldValidation() throws InterruptedException, BiffException, IOException
	{
		getDRPlanSheetFromExcel();
		
		click(drOvrAddBtn);
		thread();
		
		click(drSaveAndContinueBtn);
		getObjectText(stepValidatnMsg);
		verifyAssertEquals("Plan Name is required.",getActualObjectTxt);
		
		int ttlRows = drpln.getRows();
		Random drplnName = new Random();
		int rdrPlnName = drplnName.nextInt(ttlRows-1)+1;
		enterText(drPlanName, drpln.getCell(0, rdrPlnName).getContents());
		click(drSaveAndContinueBtn);
		getObjectText(stepValidatnMsg);
		verifyAssertEquals("Plan Author is required.",getActualObjectTxt);
		
		click(drPlanAuthorDDArrow);
		getTotalValuesIndd(drPlanAuthorDDCnt);
		Random plnAuthor = new Random();
		int rplnAuthor = plnAuthor.nextInt(totalDDValCount-2)+2;
		da.planAuthorNm = driver.findElement(By.id("ddlPlanOwner_chzn_o_"+rplnAuthor)).getText();
		thread();
		enterText(drPlanAuthorDDSearchBox, da.planAuthorNm.split(",")[0]);
		enterKeyInKyBord(drPlanAuthorDDSearchBox);
		click(drSaveAndContinueBtn);
		getObjectText(stepValidatnMsg);
		verifyAssertEquals("Plan Location is required.",getActualObjectTxt);
		
		click(drPlanLocationDDArrow);
		enterText(drPlanLocationDDSearchBox, "Agimba");
		enterKeyInKyBord(drPlanLocationDDSearchBox);
		click(drSaveAndContinueBtn);
		getObjectText(stepValidatnMsg);
		verifyAssertEquals("Plan Reviewer is required.",getActualObjectTxt);
		
		click(drPlanReviewerDDArrow);
		getTotalValuesIndd(drPlanReviewerDDCnt);
		Random plnReviewer = new Random();
		int rreviewerName = plnReviewer.nextInt(totalDDValCount-2)+2;
		da.planReviewerNm = driver.findElement(By.id("ddlPlanReviewer_chzn_o_"+rreviewerName)).getText();
		thread();
		enterText(drPlanReviewerDDSearchBox,da.planReviewerNm.split(",")[0]);		
		enterKeyInKyBord(drPlanReviewerDDSearchBox);
		click(drSaveAndContinueBtn);
		getObjectText(stepValidatnMsg);
		verifyAssertEquals("Please select at least one threat",getActualObjectTxt);
		
	}
	
	@Test(priority=3)
	void checkManageDisasterHighlightedInMainMenu() throws InterruptedException
	{
		click(manageDisasterInMainMenu);
		waitForPageLoad();
		getHighlightOptn(mainManageDisasterOptn);
		verifyAssertEquals("active", getHighlightdTxt);	
	}
	
	@Test(priority=4)
	void checkManageDisasterMandatoryFieldValidation() throws InterruptedException, BiffException, IOException
	{
		webElement(mdAffLocationRadiobBtn);
		scrollInnerScrollBar(webelementFrame);
		enterText(mdLocationsSearchBox,"Agimba");
		enterKeyInKyBord(mdLocationsSearchBox);
		click(mdAffLocationRadiobBtn);
		thread();
		
		click(mdDeclareDisasterBtn);
		getObjectText(msgNotificationBar);
		verifyAssertEquals("Please Select Threats",getActualObjectTxt);
		thread();
		
		driver.findElement(By.xpath("//table[@id='DataTables_Table_2']/tbody/tr[1]/td[1]/div/span")).click();
		click(mdDeclareDisasterBtn);
		getObjectText(msgNotificationBar);
		verifyAssertEquals("Please enter disaster name",getActualObjectTxt);
		thread();
		
		getManageDisasterSheetFromExcel();
		thread();
		Random rrow = new Random();
		int rexRow = rrow.nextInt(mgDisastr.getRows()-1)+1;
					
		ma.disasterName = mgDisastr.getCell(0, rexRow).getContents();
		enterText(mdDisasterName,ma.disasterName);
		thread();
		click(mdDeclareDisasterBtn);
		getObjectText(msgNotificationBar);
		verifyAssertEquals("Please enter general message",getActualObjectTxt);
		thread();
		
	}
	
	
	
	
	
	
	
	
	
	
	
	

}
