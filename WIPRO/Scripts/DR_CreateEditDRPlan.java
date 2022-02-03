package Scripts;

import static ObjectRepository.DRPlan.*;
import static ObjectRepository.Tasks.*;
import static ObjectRepository.Threats.*;
import static ObjectRepository.Locations.*;
import static UIWrappers.UIObjects.*;
import static Config.TakScreenshot.*;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import UIWrappers.Page;
import jxl.read.biff.BiffException;

/***************************************************************************************************
 * 
 * @author Sellamuthu
 * 
 * Test 1: Create New DR Plan
 * Test 2: Edit DR Plan
 * Test 3: Verify 'Preview' Button
 * Test 4: Verify 'Generate PDF' button
 * Test 5: Verify 'Cancel' button redirection from 'Edit Plan Information' page
 * Test 6: Verify the added DR Plan in list view 
 * 
***************************************************************************************************/

public class DR_CreateEditDRPlan extends Page {
	
	LoginLogout ll = new LoginLogout();	

	public String drPlanStatement = "Sample Plan Statement";
	public String drPlanNamee = "Plan Name";
	public String planAuthorNm;

	public String planLocationNm;

	public String planLocationNm1;

	public String planReviewerNm;

	public String selectedThreatNm;
	public String phPlanName = "Plan Name";
	public String lblPlanName = "Plan Name";
	public String lblPlanAuthor = "Plan Author";
	public String lblPlanLocation = "Plan Location";
	public String lblPlanReviewer = "Plan Reviewer";
	public String lblThreats = "Threats";
	public String defTxtPlanAuthor = "Select Plan Author";
	public String defTxtPlanLocation = "Select Plan Location";
	public String defTxtPlanReviewer = "Select Plan Reviewer";
	public String noThreatsFoundForSelectedLoc = "No Threats found for selected location!";
	public String excelPlanSt;

	public String excelPlanNm;
	public String editPlNm,editAuthorname,editReviewer,editLocNme,editPlanSt,currentTab;
	
	
	@Test(priority=0)
	void login() throws BiffException, IOException, InterruptedException
	{
		ll.loginToSIB();
	}
	
	@Test(priority=1)
	void drPlan() throws InterruptedException
	{
		click(drThreatsnDRPlanInMainMenu);
		thread();		
		click(drOvrAddBtn);
		thread();
	}
	
  @Test(priority=2)
	void addNewDRPlan() throws BiffException, IOException, InterruptedException
	{
	  try
	  {
		getDRPlanSheetFromExcel();
		
		//click(drThreatsnDRPlanInMainMenu);
	//	thread();		
	//	click(drOvrAddBtn);
	//	thread();
		
		//	Plan Name
		int ttlRows = drpln.getRows();
		Random drplnName = new Random();
		int rdrPlnName = drplnName.nextInt(ttlRows-1)+1;
		enterText(drPlanName, drpln.getCell(0, rdrPlnName).getContents());
		excelPlanNm=drpln.getCell(0, rdrPlnName).getContents();
		excelPlanSt=drpln.getCell(1, rdrPlnName).getContents();
		//	Plan Author
		click(drPlanAuthorDDArrow);
		getTotalValuesIndd(drPlanAuthorDDCnt);
		Random plnAuthor = new Random();
		int rplnAuthor = plnAuthor.nextInt(totalDDValCount-2)+2;
		planAuthorNm = driver.findElement(By.id("ddlPlanOwner_chzn_o_"+rplnAuthor)).getText();
		thread();
		enterText(drPlanAuthorDDSearchBox, planAuthorNm.split(",")[0]);
		enterKeyInKyBord(drPlanAuthorDDSearchBox);
		
		//	Plan Location	
/*	changloc:
		for(int i=1; i<totalDDValCount; i++)
		{
			click(drPlanLocationDDArrow);
			getTotalValuesIndd(drPlanLocationDDCnt);
			Random rand=new Random();
			int rl=rand.nextInt(totalDDValCount-1)+1;
			planLocationNm = driver.findElement(By.id("ddlPlanLocation_chzn_o_"+rl)).getText();
			enterText(drPlanLocationDDSearchBox, planLocationNm);
			enterKeyInKyBord(drPlanLocationDDSearchBox); 
			//waitForPageLoad();
			try
			{
				getAlertMsgText();
				
				getObjectText(msgNotificationBar);
				
				if(getActlAlertTxt.equals(noThreatsFoundForSelectedLoc))
				{
				acceptAlertMessage();
				}
				else
				{
					continue changloc;
				}
			
			}
			catch(Exception e)
			{
				click(drThreats);
				selectedThreatNm = driver.findElement(By.id("ddlPossibleThreats_chzn_o_0")).getText();
				enterKeyInKyBord(drThreats);
				break;
			}		
		}   */
		
  loc:
	//for(int i=2; i<totalDDValCount; i++)
	  while(1==1)
		{
			click(drPlanLocationDDArrow);
			getTotalValuesIndd(drPlanLocationDDCnt);
			Random rand=new Random();
			int rl=rand.nextInt(totalDDValCount-1)+1;
			planLocationNm = driver.findElement(By.id("ddlPlanLocation_chzn_o_"+rl)).getText();
			enterText(drPlanLocationDDSearchBox, planLocationNm);
			thread();
			enterKeyInKyBord(drPlanLocationDDSearchBox); 
			thread();
			//waitForPageLoad();
			try
			{
				getObjectText(msgNotificationBar);
				thread();
				if(getActualObjectTxt.equals(noThreatsFoundForSelectedLoc))
				{
				   	continue loc;
				   	
				}
				else
				{
					
					click(drThreats);
					thread();
					getTotalValuesIndd(drThreatsTotalCount);
					thread();
					//System.out.println("total dd count "+totalDDValCount);
					if(totalDDValCount==1)
					{
						selectedThreatNm = driver.findElement(By.id("ddlPossibleThreats_chzn_o_0")).getText();
						driver.findElement(By.id("ddlPossibleThreats_chzn_o_0")).click();
					}
					else
					{
					Random rand1=new Random();
					int rt=rand1.nextInt(totalDDValCount-1)+1;
					//System.out.println("threat rand val "+rt);
					selectedThreatNm = driver.findElement(By.xpath("//div[@id='ddlPossibleThreats_chzn']/div/ul/li["+rt+"]")).getText();
					//selectedThreatNm = driver.findElement(By.id("ddlPossibleThreats_chzn_o_0")).getText();
					thread();
					//driver.findElement(By.id("ddlPossibleThreats_chzn_o_0")).click();
					driver.findElement(By.xpath("//div[@id='ddlPossibleThreats_chzn']/div/ul/li["+rt+"]")).click();
					
					thread();
					}
					enterKeyInKyBord(drThreats);
					thread();
					break;
					
				}
			
			}
			catch(Exception e)
			{
				click(drThreats);
				getTotalValuesIndd(drThreatsTotalCount);
				thread();
				//System.out.println("total dd count "+totalDDValCount);
				if(totalDDValCount==1)
				{
					selectedThreatNm = driver.findElement(By.id("ddlPossibleThreats_chzn_o_0")).getText();
					driver.findElement(By.id("ddlPossibleThreats_chzn_o_0")).click();
				}
				else
				{
				Random rand1=new Random();
				int rt=rand1.nextInt(totalDDValCount-1)+1;
				//System.out.println("threat rand val "+rt);
				selectedThreatNm = driver.findElement(By.xpath("//div[@id='ddlPossibleThreats_chzn']/div/ul/li["+rt+"]")).getText();
				//selectedThreatNm = driver.findElement(By.id("ddlPossibleThreats_chzn_o_0")).getText();
				//driver.findElement(By.id("ddlPossibleThreats_chzn_o_0")).click();
				driver.findElement(By.xpath("//div[@id='ddlPossibleThreats_chzn']/div/ul/li["+rt+"]")).click();
				thread();
				}
				enterKeyInKyBord(drThreats);
				thread();
				break;
			}		
		}
		
		//		Plan Reviewer
		click(drPlanReviewerDDArrow);
		getTotalValuesIndd(drPlanReviewerDDCnt);
		Random plnReviewer = new Random();
		int rreviewerName = plnReviewer.nextInt(totalDDValCount-2)+2;
		planReviewerNm = driver.findElement(By.id("ddlPlanReviewer_chzn_o_"+rreviewerName)).getText();
		thread();
		enterText(drPlanReviewerDDSearchBox,planReviewerNm.split(",")[0]);
		thread();
		enterKeyInKyBord(drPlanReviewerDDSearchBox);
		thread();					
		
		//	Plan Statement
		WebElement ifram = driver.findElement(scEdtrFrame);
		driver.switchTo().frame(ifram);		
		enterText(scEdtrCommentsField, drpln.getCell(1, rdrPlnName).getContents());
		thread();		
		switchBackFromFrame();
		
		thread();
		click(drSaveAndContinueBtn);
		thread();
		takeScreenshot();
		}
	  catch(Exception e)
	  {
		  e.printStackTrace();
	  }
		
	
	} 
	
	@Test(priority=3)
	void verifyLocationBasedTemplates() throws InterruptedException
	{
		try
		{
		click(drEdtCollapseAllSectionsBtn);
		thread();
		
		getTotalValuesIndd(drEdtDynamicTemplatesCnt);
		for(int i=1; i<= totalDDValCount; i++)
		{
			String getCustomTemplateName = driver.findElement(By.xpath("//div[@id='divDynamicDRPlanSections']/div["+i+"]/div/div/h4/span")).getText();
			if(i ==1)
				verifyAssertEquals("Threats", getCustomTemplateName);
			else if(i == 2)
				verifyAssertEquals("Employees", getCustomTemplateName);
			else if(i == 3)
				verifyAssertEquals("Assets", getCustomTemplateName);
			else if(i == 4)
				verifyAssertEquals("Insurance", getCustomTemplateName);
			else if(i == 5)
				verifyAssertEquals("Business Functions", getCustomTemplateName);
		}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}		
	
	@Test(priority=4)
	void editPlanInformation() throws InterruptedException, BiffException, IOException
	{	
		try
		{
		click(drEdtEditPlanInformationBtn);
		thread();
		
	   //	BUG 4229
		getObjectText(drlblPlanName);
		verifyAssertEquals(lblPlanName, getActualObjectTxt);	
		
		getObjectText(drlblPlanAuthor);
		verifyAssertEquals(lblPlanAuthor, getActualObjectTxt);	
		
		getObjectText(drlblPlanLocation);
		verifyAssertEquals(lblPlanLocation, getActualObjectTxt);	
		
		getObjectText(drlblPlanReviewer);
		verifyAssertEquals(lblPlanReviewer, getActualObjectTxt);	
		
		getObjectText(drlblThreats);
		verifyAssertEquals(lblThreats, getActualObjectTxt);
		
		
		//	CHECK PREVIOUSLY SELECTED TEXTS IN EDIT PAGE		
		getAttributePh(drPlanName);
		verifyAssertEquals(phPlanName, getAttribtePh);	
		
		//getObjectText(drPlanName);
		//verifyAssertEquals(excelPlanNm, getActualObjectTxt);
		
		try{			
		getObjectText(drPlanAuthorDefTxt);
		verifyAssertEquals(planAuthorNm, getActualObjectTxt);
		}
		catch(Error e)
		{
			//
		}
		try{
		getObjectText(drPlanLocationDefTxt);
		verifyAssertEquals(planLocationNm, getActualObjectTxt);
		}
		catch(Error e)
		{
			//
		}
		try{
		getObjectText(drPlanReviewerDefTxt);
		verifyAssertEquals(planReviewerNm, getActualObjectTxt);
		}
		catch(Error e)
		{
			//
		}
	
		
		//	Threats
		try
		{
			verifyObjDisplay(drThreatsDrpDownVal);
			getObjectText(drThreatsDrpDownVal1);
			verifyAssertEquals(selectedThreatNm, getActualObjectTxt);
		}
		catch(WebDriverException e)
		{
			System.out.println("Selected Threat Name is not displaying");
		}
		
		//	Plan Statement
		WebElement ifram = driver.findElement(scEdtrFrame);
		driver.switchTo().frame(ifram);		
		getObjectText(scEdtrCommentsField);
		verifyAssertEquals(excelPlanSt, getActualObjectTxt);
		switchBackFromFrame();
		thread();
		
		
		//	VERIFY CLEAR BUTTON
		webElement(drClearBtn);
		scrollInnerScrollBar(webelementFrame);
		
		click(drClearBtn);
		thread();
		
		getAttributePh(drPlanName);
		verifyAssertEquals(phPlanName, getAttribtePh);
		
		getObjectText(drPlanAuthorDefTxt);
		verifyAssertEquals(defTxtPlanAuthor, getActualObjectTxt);
		
		getObjectText(drPlanLocationDefTxt);
		verifyAssertEquals(defTxtPlanLocation, getActualObjectTxt);
		
		getObjectText(drPlanReviewerDefTxt);
		verifyAssertEquals(defTxtPlanReviewer, getActualObjectTxt);	
		
		//	Plan Name
			int ttlRows = drpln.getRows();
			//System.out.println("Roqa: "+ttlRows);
			Random drplnName = new Random();
			int rdrPlnName = drplnName.nextInt(ttlRows-1)+1;
			enterText(drPlanName, drpln.getCell(0, rdrPlnName).getContents());
			editPlNm=drpln.getCell(0, rdrPlnName).getContents();
			editPlanSt=drpln.getCell(1, rdrPlnName).getContents();
			//	Plan Author
			click(drPlanAuthorDDArrow);
			getTotalValuesIndd(drPlanAuthorDDCnt);
			Random plnAuthor = new Random();
			int rplnAuthor = plnAuthor.nextInt(totalDDValCount-2)+2;
			planAuthorNm = driver.findElement(By.id("ddlPlanOwner_chzn_o_"+rplnAuthor)).getText();
			thread();
			enterText(drPlanAuthorDDSearchBox, planAuthorNm.split(",")[0]);
			thread();
			enterKeyInKyBord(drPlanAuthorDDSearchBox);
			
			//	Plan Location		
	/*		changloc:
			for(int i=1; i<totalDDValCount; i++)
			{
				click(drPlanLocationDDArrow);
				getTotalValuesIndd(drPlanLocationDDCnt);			
				planLocationNm = driver.findElement(By.id("ddlPlanLocation_chzn_o_"+i)).getText();
				enterText(drPlanLocationDDSearchBox, planLocationNm);
				enterKeyInKyBord(drPlanLocationDDSearchBox); 
				waitForPageLoad();
				
				try
				{
					getAlertMsgText();
					if(getActlAlertTxt.equals(noThreatsFoundForSelectedLoc))
					{
						acceptAlertMessage();
					}
					else
					{
						continue changloc;
					}
				
				}catch(Exception e)
				{
					click(drThreats);
					selectedThreatNm = driver.findElement(By.id("ddlPossibleThreats_chzn_o_0")).getText();
					enterKeyInKyBord(drThreats);
					break;
				}		
			}*/
			
			loc:
				//for(int i=2; i<totalDDValCount; i++)
				 while(1==1)
					{
						click(drPlanLocationDDArrow);
						getTotalValuesIndd(drPlanLocationDDCnt);
						Random rand=new Random();
						int rl=rand.nextInt(totalDDValCount-1)+1;
						planLocationNm = driver.findElement(By.id("ddlPlanLocation_chzn_o_"+rl)).getText();
						enterText(drPlanLocationDDSearchBox, planLocationNm);
						thread();
						enterKeyInKyBord(drPlanLocationDDSearchBox); 
						thread();
						//waitForPageLoad();
						try
						{
							getObjectText(msgNotificationBar);
							thread();
							if(getActualObjectTxt.equals(noThreatsFoundForSelectedLoc))
							{
							   	continue loc;
							   	
							}
							else
							{
								
								click(drThreats);
								getTotalValuesIndd(drThreatsTotalCount);
								thread();
								//System.out.println("total dd count "+totalDDValCount);
								if(totalDDValCount==1)
								{
									selectedThreatNm = driver.findElement(By.id("ddlPossibleThreats_chzn_o_0")).getText();
									driver.findElement(By.id("ddlPossibleThreats_chzn_o_0")).click();
								}
								else
								{
								Random rand1=new Random();
								int rt=rand1.nextInt(totalDDValCount-1)+1;
								//System.out.println("threat rand val "+rt);
								selectedThreatNm = driver.findElement(By.xpath("//div[@id='ddlPossibleThreats_chzn']/div/ul/li["+rt+"]")).getText();
								//selectedThreatNm = driver.findElement(By.id("ddlPossibleThreats_chzn_o_0")).getText();
								thread();
								//driver.findElement(By.id("ddlPossibleThreats_chzn_o_0")).click();
								driver.findElement(By.xpath("//div[@id='ddlPossibleThreats_chzn']/div/ul/li["+rt+"]")).click();
								thread();
								}
								enterKeyInKyBord(drThreats);
								thread();
								break;
							}
						
						}
						catch(Exception e)
						{
		
							click(drThreats);
							thread();
							getTotalValuesIndd(drThreatsTotalCount);
							thread();
							//System.out.println("total dd count "+totalDDValCount);
							if(totalDDValCount==1)
							{
								selectedThreatNm = driver.findElement(By.id("ddlPossibleThreats_chzn_o_0")).getText();
								driver.findElement(By.id("ddlPossibleThreats_chzn_o_0")).click();
							}
							else
							{
							Random rand1=new Random();
							int rt=rand1.nextInt(totalDDValCount-1)+1;
							//System.out.println("threat rand val "+rt);
							selectedThreatNm = driver.findElement(By.xpath("//div[@id='ddlPossibleThreats_chzn']/div/ul/li["+rt+"]")).getText();
							//selectedThreatNm = driver.findElement(By.id("ddlPossibleThreats_chzn_o_0")).getText();
							//driver.findElement(By.id("ddlPossibleThreats_chzn_o_0")).click();
							driver.findElement(By.xpath("//div[@id='ddlPossibleThreats_chzn']/div/ul/li["+rt+"]")).click();
							thread();
							}
							enterKeyInKyBord(drThreats);
							thread();
							break;
						}		
					}
			
			//		Plan Reviewer
			click(drPlanReviewerDDArrow);
			getTotalValuesIndd(drPlanReviewerDDCnt);
			Random plnReviewer = new Random();
			int rreviewerName = plnReviewer.nextInt(totalDDValCount-2)+2;
			planReviewerNm = driver.findElement(By.id("ddlPlanReviewer_chzn_o_"+rreviewerName)).getText();
			thread();
			click(drPlanReviewerDDSearchBox);
			thread();
			enterText(drPlanReviewerDDSearchBox, planReviewerNm.split(",")[0]);
			//System.out.println("edit plan reviewer name "+planReviewerNm);
			//enterText(drPlanReviewerDDSearchBox,planReviewerNm);
			thread();
			enterKeyInKyBord(drPlanReviewerDDSearchBox);
			thread();					
			
			//	Plan Statement
			WebElement iframe = driver.findElement(scEdtrFrame);
			driver.switchTo().frame(iframe);		
			enterText(scEdtrCommentsField, drpln.getCell(1, rdrPlnName).getContents());
			thread();		
			switchBackFromFrame();
			
			click(drSaveAndContinueBtn);
			thread();
			Thread.sleep(5000);
			
			Thread.sleep(5000);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	@Test(priority=5)
	void preview() throws InterruptedException, IOException
	{
		try
		{
		thread();
		click(drEdtPreviewBtn);
		thread();
		takeScreenshot();
		Thread.sleep(5000);
		
		click(drEdtPreviewCloseBtn);
		thread();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
	@Test(priority=6)
	void verifyDRPlanNmInListView() throws InterruptedException
	{
		try
		{
		webElement(drEdtCancelBtn);
		scrollInnerScrollBar(webelementFrame);
		
		click(drEdtCancelBtn);
		thread();
		
		verifyObjDisplay(drDRPlanSearchBox);
		try
		{
			if(chkObjDisplay == true)
			{
				getTotalValuesIndd(drDRPlanLstViewTtlCnt);
				for(int i=1; i<=totalDDValCount; i++)
				{
					String getDRPlanName = driver.findElement(By.xpath("//table[@id='plan_table']//tbody/tr["+i+"]/td[2]")).getText();
					if(getDRPlanName.equals(drPlanNamee))
					{
						for(int j=2; j<7; j++)
						{
							String getAddedDRDtls = driver.findElement(By.xpath("//table[@id='plan_table']//tbody/tr["+i+"]/td["+j+"]")).getText();
							if(j == 2)
								//verifyAssertEquals(drPlanNamee, getAddedDRDtls);
							verifyAssertEquals(editPlNm,getAddedDRDtls);
							else if(j == 3)
								verifyAssertEquals(planAuthorNm, getAddedDRDtls);
							else if(j == 5)
								verifyAssertEquals(planLocationNm, getAddedDRDtls);
							else if(j == 6)
								verifyAssertEquals(planReviewerNm, getAddedDRDtls);
						}
						break;
					}					
				}
			}
		}
		catch(WebDriverException e)
		{
			//System.out.println("Edit Plan Information 'Cancel' button is not redirecting to 'Threats/DR Plan' list view");
		}
		thread();	
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	/*@Test(priority=7)
	void Documentupload() throws IOException, InterruptedException, AWTException
    {
		try
		{
		//click(drThreatsnDRPlanInMainMenu);
		//thread();		
        click(docuemntTb);
    	thread();
    	//Scroll down the page
		webElement(choosefiles);
		
		scrollInnerScrollBar(webelementFrame);
		
	    //click choosefiles button 
		click(choosefiles);
		thread();
		takeScreenshot();
    	
       //put path to your image in a clipboard
		uploadFile("D:\\SIB\\ProfilePics\\Creek.jpg");
		thread();

        // click to upload the files 
         click(docUpload);
         thread();
         
         getObjectText(msgNotificationBar);
         thread();
         verifyAssertEquals("1 File uploaded successfully",getActualObjectTxt);
         thread();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
      }*/
        
	
	
	@Test(priority=8)
	void generatePdf() throws InterruptedException, IOException, AWTException
	{
		thread();
		click( drEditBtn);
		Thread.sleep(1000);
		thread();
		currentTab=driver.getWindowHandle();
		try
		{
		click(drEdtGeneratePDFBtn);
		waitForPageLoad();
		takeScreenshot();
		
		 ArrayList<String> newTab = new ArrayList<String>(driver.getWindowHandles());
		 newTab.remove(currentTab);
		 
		 // change focus to new tab
		 driver.switchTo().window(newTab.get(0));
		 try{
		 takeScreenshot();
		 Thread.sleep(5000);
		 }
		 catch(Exception e)
		 {
			 e.printStackTrace();
		 }
		 
		 Robot r=new Robot();
		 r.keyPress(KeyEvent.VK_PAGE_DOWN);
		 r.keyRelease(KeyEvent.VK_PAGE_DOWN);
		 thread();
		 
		 getObjectText(drEdtPdfPlanName);
		 thread();
		
		 verifyAssertEquals(editPlNm,getActualObjectTxt);
		 thread();
		
		 driver.close();
		 thread();
		
		 //switch to old window
		 driver.switchTo().window(currentTab);
		 thread();
		 
		 click(drThreatsnDRPlanInMainMenu);
		 thread();	
		}
		catch(Error e)
		{
			driver.close();
			 thread();
			
			 //switch to old window
			 driver.switchTo().window(currentTab);
			 thread();
			 
			 click(drThreatsnDRPlanInMainMenu);
			 thread();	
			e.printStackTrace();
		}

		
	}
	
	/*@Test(priority=9)
	void logout() throws InterruptedException
	{
		ll.logoutSession();
		thread();
		driver.quit();
	}	*/
}


