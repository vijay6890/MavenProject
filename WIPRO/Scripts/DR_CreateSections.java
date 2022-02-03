package Scripts;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import UIWrappers.Page;
import jxl.read.biff.BiffException;

import static UIWrappers.UIObjects.*;
import static ObjectRepository.DRPlan.*;
import static ObjectRepository.Locations.*;
import static ObjectRepository.Threats.*;
import static Config.TakScreenshot.*;

public class DR_CreateSections extends Page {
		
	LoginLogout ll = new LoginLogout();
	DR_CreateEditDRPlan dR_CreateEditDRPlan = new DR_CreateEditDRPlan();
	
	private String filterValueExistsMsg = "This filter exists please try with other options";
	
	/*@Test(priority=0)
	void login() throws BiffException, IOException, InterruptedException
	{
		ll.loginToSIB();
	}*/
	
	@Test(priority=1)
	void edit() throws InterruptedException
	{
		click(drThreatsnDRPlanInMainMenu);
		waitForPageLoad();
		
		driver.findElement(By.xpath("//table[@id='plan_table']//tbody/tr/td[8]/a")).click();
		thread();
		
	}
	
/*	@Test(priority=1)
	void createDRPlan() throws BiffException, IOException, InterruptedException
	{
		dR_CreateEditDRPlan.addNewDRPlan();
		thread();
	}*/
	
	@Test(priority=2)
	void deleteExistingSections() throws InterruptedException
	{
		click(drEdtCollapseAllSectionsBtn);
		thread();
	
		for(int i=0; i<5; i++)
		{
			click(drEdtSectionDeleteBtn);
			thread();
			
			verifyAssert(deleteConfPopup);
			thread();
			click(delConfOkBtn);
			thread();
		}
	}
	
	@Test(priority=3)
	void createEmployeesSection() throws InterruptedException, IOException, BiffException
	{
		getDRPlanCreateNewSectionSheetFromExcel();
		
		click(drEdtFstCreateNewSectionBtn);
		thread();
		
		//		Create Section		//
	//	try
	//	{
			//verifyAssert(createNewSectionPopup);
			
			getObjectText(secPpCreateNewSectionTtl);
			
			verifyAssertEquals("Create New Section", getActualObjectTxt);		//	Verify Create New Section Title In Pop Up
			
			//	Section Title
			click(secPpSectionTitleDDArrow);
			thread();
			enterText(secPpSectionTitleDDSearchBox, drplnNewsc.getCell(0, 1).getContents());
			thread();
			//	Verify Click to Add Text in drop down
	/*		try
			{
				verifyObjDisplay(clickToAddTxtInDD);
				if(chkObjDisplay == true)
				{
					click(clickToAddTxtInDD);
					verifyAssert(sectionAddConfPopup);
					getObjectText(deleteConfPpMessage); 
					verifyAssertEquals("Do you want to add "+drpln.getCell(1, 1).getContents()+" into Section Title ?", getActualObjectTxt);
					click(delConfOkBtn);
					thread();
					
					getObjectText(msgNotificationBar);
					verifyAssertEquals(drpln.getCell(1, 1).getContents()+" added into Section Title successfully", getActualObjectTxt);		//	Check Section Added Successfully
					thread();
					
					getObjectText(secPpSectionTitleDefTxt);
					verifyAssertEquals(drpln.getCell(1, 1).getContents(), getActualObjectTxt);		//	Verify Added Title In Drop Down
				}
			}catch(WebDriverException e){
				arrowDown(secPpSectionTitleDDSearchBox);
				enterKeyInKyBord(secPpSectionTitleDDSearchBox);
			}         */
			
			//	Authorized By
			click(secPpAuthorizedByDDArrow);
			getTotalValuesIndd(secPpAuthorizedByDDCnt);
			Random authBy = new Random();
			int rauthBy = authBy.nextInt(totalDDValCount-2)+2;
			String authorizedBy = driver.findElement(By.id("ddlDSAuthorizedBy_chzn_o_"+rauthBy)).getText();
			enterText(secPpAuthorizedByDDSearchBox, authorizedBy.split(",")[0]);
			thread();
			enterKeyInKyBord(secPpAuthorizedByDDSearchBox);
			
			//	Verified By
			click(secPpVerifiedByDDArrow);
			getTotalValuesIndd(secPpVerifiedByDDCnt);
			Random veriBy = new Random();
			int rveriBy = veriBy.nextInt(totalDDValCount-2)+2;
			String verifiedBy = driver.findElement(By.id("ddlDSVerifiedBy_chzn_o_"+rveriBy)).getText();
			enterText(secPpVerifiedByDDSearchBox, verifiedBy.split(",")[0]);
			thread();
			enterKeyInKyBord(secPpVerifiedByDDSearchBox);
			thread();
			driver.findElement(secPpVerifiedByDDSearchBox).sendKeys(Keys.TAB);
			driver.findElement(secPpVerifiedByDDSearchBox).sendKeys(Keys.TAB);
			
			//	Description
			//webElement(crSectionDescription);
		//	switchToWEFrame(webelementFrame);
			
			WebElement ifram = driver.findElement(scEdtrFrame);
			driver.switchTo().frame(ifram);	
			System.out.println("inside frame"+drplnNewsc.getCell(1, 1).getContents());
			enterText(scEdtrCommentsField, drplnNewsc.getCell(1, 1).getContents());
			thread();		
			switchBackFromFrame();
			takeScreenshot();
			
			click(secPpSaveBtn);
			thread();
			
			getObjectText(msgNotificationBar);
			verifyAssertEquals(drpln.getCell(4, 1).getContents(), getActualObjectTxt);	 //	Verify New Section Created Successfully
			
		/*	//		Edit Description		//
			getObjectText(secAddedSectionDescription);
			verifyAssertEquals(drpln.getCell(2, 1).getContents(), getActualObjectTxt);		//	Check Added Section Description
			
			click(secAddedSectionDescription);
			thread();
			verifyAssert(createNewSectionPopup);
			getObjectText(secPpCreateNewSectionTtl);
			verifyAssertEquals(drpln.getCell(9, 3).getContents(), getActualObjectTxt);		//	Check Update Description Title
			
			webElement(crSectionDescription);
			switchToWEFrame(webelementFrame);
			useKeyFuncToRmvVal(drscEdtrCommentsField);
			enterText(drscEdtrCommentsField, drpln.getCell(3, 1).getContents());
			switchBackFromFrame();
			
			click(secPpSaveBtn);
			thread();
			
			getObjectText(msgNotificationBar);
			verifyAssertEquals(drpln.getCell(8, 1).getContents(), getActualObjectTxt);		//	Section Updated Successfully
			thread();	
			
			getObjectText(secAddedSectionDescription);
			verifyAssertEquals(drpln.getCell(3, 1).getContents(), getActualObjectTxt);			
			
			
			//		Authorized & Verified By Person Names		//
			getObjectText1(drEdtSecAuthorizedByLblNm);
			getObjectText(drEdtSecAuthorizedByNm);
			verifyAssertEquals("Authorized By : "+authorizedBy, getActualObjectTxt1+" "+getActualObjectTxt);	//	Check Authorized Person Name
			
			getObjectText1(drEdtSecVerifiedByLblNm);
			getObjectText(drEdtSecVerifiedByNm);
			verifyAssertEquals("Verified By : "+verifiedBy, getActualObjectTxt1+" "+getActualObjectTxt);		//	Check Verified Person Name
			thread();
			
			//		Add Text/Paragraph		//
			click(edtSecAddTextBtn);
			
			verifyAssert(adTxtAddTextParagraphPopup);
			getObjectText(adTxtAddTextParagraphPpTtl);
			verifyAssertEquals(drpln.getCell(9, 4).getContents(), getActualObjectTxt);		//	Add Text/Paragraph Pop Up Title
				
			getObjectText(adTxtPpEnterSomeTxtLbl);
			verifyAssertEquals(drpln.getCell(10, 1).getContents(), getActualObjectTxt);		//	Enter Some Text Label Name
				
			webElement(adTxtEnterSomeTextFld);
			switchToWEFrame(webelementFrame);
			enterText(scEdtrCommentsField, drpln.getCell(4, 1).getContents());		//	Enter Some Texts
			switchBackFromFrame();
			
			click(adTxtParagrphPpSaveBtn);
			thread();
			
			getObjectText(msgNotificationBar);
			verifyAssertEquals(drpln.getCell(8, 2).getContents(), getActualObjectTxt);		//	Text/Paragraph added successfully 
			
			getObjectText(adTxtEditAddedTxt);
			verifyAssertEquals(drpln.getCell(4, 1).getContents(), getActualObjectTxt);		//	Verify Entered Text In Section Page
			
			click(adTxtEditAddedTxt);
			thread();
			
			//		Edit Text/Paragraph		//
			getObjectText(adTxtAddTextParagraphPpTtl);
			verifyAssertEquals(drpln.getCell(9, 5).getContents(), getActualObjectTxt);		//	Update Text/Paragraph Title
				
			webElement(adTxtEnterSomeTextFld);
			switchToWEFrame(webelementFrame);
			useKeyFuncToRmvVal(scEdtrCommentsField);	//	Edit Some Texts
			enterText(scEdtrCommentsField, drpln.getCell(5, 1).getContents());		//	Edit Texts
			switchBackFromFrame();
				
			click(adTxtParagrphPpSaveBtn);
			thread();
				
			getObjectText(msgNotificationBar);
			verifyAssertEquals(drpln.getCell(8, 3).getContents(), getActualObjectTxt);		//	Text/Paragraph Updated Successfully
				
			getObjectText(adTxtEditAddedTxt);
			verifyAssertEquals(drpln.getCell(5, 1).getContents(), getActualObjectTxt);		//	Verify Modified Text
			
			
			//		Add Dynamic Data		//
			click(edtSecAddDataBtn);	
			thread();
			
			verifyAssert(addDynamicDataPopup);
				
			click(dynDtaPpModuleDDArrow);
			enterText(dynDtaPpModuleDDSearchBox, drpln.getCell(11, 1).getContents());		//	Choose Employee In Add Dynamic Data Pop up
			enterKeyInKyBord(dynDtaPpModuleDDSearchBox);
			thread();
				
			try
			{
				verifyObjDisplay(dynDtaEmpSelectAllChkBox);
				if(chkObjDisplay == true)
				{
					//	Enable Select All Check Box
					click(dynDtaEmpSelectAllChkBox);						
					getTotalValuesIndd(dynDtaEmpDisplayFldCnt);
					try
					{
						for(int i=1; i<=totalDDValCount; i++)
						{
							boolean checked = driver.findElement(By.xpath("//div[@id='divDDataFields']/span["+i+"]/input")).isSelected();
							Assert.assertEquals(true, checked);
						}
							
						//		Disable Select All Check Box
						click(dynDtaEmpSelectAllChkBox);
						getTotalValuesIndd(dynDtaEmpDisplayFldCnt);
						for(int j=1; j<=totalDDValCount; j++)
						{
							boolean unchecked = driver.findElement(By.xpath("//div[@id='divDDataFields']/span["+j+"]/input")).isSelected();
							Assert.assertEquals(false, unchecked);
						}
					}catch(WebDriverException e3) {
							e3.printStackTrace();
					}
						
					click(dynDtaEmpFirstName);
					click(dynDtaEmpLastName);
					click(dynDtaEmpMobile);
					click(dynDtaEmpAlternateEmployee);
						
					click(dynDtaAddNewFilterBtn);
						
					click(dynDtaFieldDDArrow);
					enterText(dynDtaFieldDDSearchBox, drpln.getCell(12, 1).getContents());		//	Display Field Name
					enterKeyInKyBord(dynDtaFieldDDSearchBox);
					thread();
					
					//		Choose Filter Value
					//click(dynDtaFieldValueDD);
					getTotalValuesIndd(dynDtaFieldValueCnt);
										
					ArrayList<Integer> alist = new ArrayList<Integer>();
					for(int i=1; i<totalDDValCount; i++)
					{
						alist.add(i);
					}
					Collections.shuffle(alist);
					
					for(int i=0; i<2; i++)
					{	
						String randomValue = driver.findElement(By.id("ddlDDataNewFilterValues_chzn_o_"+alist.get(i))).getText();
						enterText(dynDtaFieldValueDD, randomValue);
						enterKeyInKyBord(dynDtaFieldValueDD);
						click(dynDtaFilterAddBtn);
						
						//	Check the same filter value exists
						try
						{							
							isAlertPresent();
							getAlertMsgText();
							verifyAssertEquals(filterValueExistsMsg, getActlAlertTxt);
							acceptAlertMessage();
							break;
						}catch(NoAlertPresentException noalrt) {
							
						}
					}		
						
					click(dynDtaPpSaveBtn);
					thread();
					
					getObjectText(msgNotificationBar);
					verifyAssertEquals(drpln.getCell(8, 4).getContents(), getActualObjectTxt);		//	Data Created Successfully
					thread();
					
					//		Check Selected Display Order in Table
					for(int i=1; i<5; i++)
					{
						String getColumnName = driver.findElement(By.xpath("//table[@class='table table-responsive table-striped table-bordered bootstrap-datatable dynamic-data-table']/thead/tr/td["+i+"]")).getText();
						if(i == 1)
							verifyAssertEquals(drpln.getCell(12, 1).getContents(), getColumnName);
						if(i == 2)
							verifyAssertEquals(drpln.getCell(12, 2).getContents(), getColumnName);
						if(i == 3)
							verifyAssertEquals(drpln.getCell(12, 3).getContents(), getColumnName);
						if(i == 4)
							verifyAssertEquals(drpln.getCell(12, 4).getContents(), getColumnName);
					}
					
					click(dynDtaEmpClikToEdit);
					thread();
					
					verifyAssert(addDynamicDataPopup);
					
					for(int i=2; i<6; i++)
					{
						boolean checked = driver.findElement(By.xpath("//div[@id='divDDataFields']/span["+i+"]/input")).isSelected();
						Assert.assertEquals(true, checked);
					}
							
					//	Drag & Drop the objects
					sourceElement(dynDtaEmpWeFirstName);
					destinationElement(dynDtaEmpWeLastName);
					dragAndDrop(sourceObj, destinationObj);		//	Change First & Last Name position
													
					sourceElement(dynDtaEmpWeMobile);
					destinationElement(dynDtaEmpWeAlternateEmployee);
					dragAndDrop(sourceObj, destinationObj);		//	Change Mobile No & Alternate Employee position
					
					//	Verify Added Filter Values
					try
					{
						verifyObjDisplay(dynDtaEmpSelectedFilterValue);
						if(chkObjDisplay == true)
						{
							verifyAssert(dynDtaEmpFilterValue1);
							verifyAssert(dynDtaEmpFilterValue2);
						}
					}catch(WebDriverException e) {
						System.out.println("Selected Filter values are not displaying in Edit screen");
						e.printStackTrace();
					}
					
					click(dynDtaPpSaveBtn);
					thread();
								
					getObjectText(msgNotificationBar);
					verifyAssertEquals(drpln.getCell(8, 5).getContents(), getActualObjectTxt);		//	Data Updated Successfully
					thread();
													
					//		Verify Column Name Order Changes In List View
					for(int i=1; i<5; i++)
					{
						String getColumnName = driver.findElement(By.xpath("//table[@class='table table-responsive table-striped table-bordered bootstrap-datatable dynamic-data-table']/thead/tr/td["+i+"]")).getText();
						if(i == 1)
							verifyAssertEquals(drpln.getCell(12, 2).getContents(), getColumnName);	//	Last Name
						if(i == 2)
							verifyAssertEquals(drpln.getCell(12, 1).getContents(), getColumnName);		//	First Name
						if(i == 3)
							verifyAssertEquals(drpln.getCell(12, 4).getContents(), getColumnName);	//	Alternate Employee
						if(i == 4)
							verifyAssertEquals(drpln.getCell(12, 3).getContents(), getColumnName);		//	Mobile
					}					
				}
				
				//		Edit Section		//
				click(edtSecEditSectionBtn);
				thread();
				
				verifyAssert(createNewSectionPopup);
				getObjectText(secPpCreateNewSectionTtl);
				verifyAssertEquals(drpln.getCell(9, 2).getContents(), getActualObjectTxt);		//	Update Section Pop up Title
				
				getObjectText(secPpSectionTitleDefTxt);
				verifyAssertEquals(drpln.getCell(1, 1).getContents(), getActualObjectTxt);		//	Verify Selected Section Title Name 	
				
				getObjectText(secPpAuthorizedByDefTxt);
				verifyAssertEquals(authorizedBy, getActualObjectTxt);	//	Check Selected Authorized By Name
				
				getObjectText(secPpVerifiedByDefTxt);
				verifyAssertEquals(verifiedBy, getActualObjectTxt);	//	Check Select Verified By Name
				
				//		Section Title
				//click(secPpSectionTitleDDArrow);
				//getTotalValuesIndd(secPpSectionTitleDDCnt);
				//Random edtSecTitle = new Random();
				//int edtrSecTitle = edtSecTitle.nextInt(totalDDValCount-2)+2;
				//String edtSectionTitle = driver.findElement(By.id("ddlNewDynamicSectionTitle_chzn_o_"+edtrSecTitle)).getText();
				//enterText(secPpSectionTitleDDSearchBox, edtSectionTitle);
				//enterKeyInKyBord(secPpSectionTitleDDSearchBox);
				
				//	Authorized By
				click(secPpAuthorizedByDDArrow);
				getTotalValuesIndd(secPpAuthorizedByDDCnt);
				Random edtAuthBy = new Random();
				int edtrAuthBy = edtAuthBy.nextInt(totalDDValCount-2)+2;
				String edtAuthorizedBy = driver.findElement(By.id("ddlDSAuthorizedBy_chzn_o_"+edtrAuthBy)).getText();
				enterText(secPpAuthorizedByDDSearchBox, edtAuthorizedBy.split(",")[0]);
				enterKeyInKyBord(secPpAuthorizedByDDSearchBox);
				
				//	Verified By
				click(secPpVerifiedByDDArrow);
				getTotalValuesIndd(secPpVerifiedByDDCnt);
				Random edtVeriBy = new Random();
				int edtrVeriBy = edtVeriBy.nextInt(totalDDValCount-2)+2;
				String edtVerifiedBy = driver.findElement(By.id("ddlDSVerifiedBy_chzn_o_"+edtrVeriBy)).getText();
				enterText(secPpVerifiedByDDSearchBox, edtVerifiedBy.split(",")[0]);
				enterKeyInKyBord(secPpVerifiedByDDSearchBox);
				
				click(secPpSaveBtn);
				thread();
				
				getObjectText(msgNotificationBar);
				verifyAssertEquals(drpln.getCell(8, 1).getContents(), getActualObjectTxt);		//	Verify Section Updated Successfully
				
				//try
				//{
					//click(secPpSaveBtn);
					//thread();
					
					//getObjectText(msgNotificationBar);
					//verifyAssertEquals(drpln.getCell(8, 1).getContents(), getActualObjectTxt);		//	Verify Section Updated Successfully
				//}catch(WebDriverException e) {
					//e.printStackTrace();
					
					//click(secPpSaveBtn);
					//thread();
					
					//getObjectText(msgNotificationBar);
					//verifyAssertEquals(sectionTitle+sectionTitileNmAlreadyExistsMsg, getActualObjectTxt);		//	Section Title Already Exists
					
					//click(secPpSectionTitleDDArrow);
					//getTotalValuesIndd(secPpSectionTitleDDCnt);
					//Random edtSecTitle1 = new Random();
					//int edtrSecTitle1 = edtSecTitle1.nextInt(totalDDValCount-0)+0;
					//String edtSectionTitle1 = driver.findElement(By.id("ddlNewDynamicSectionTitle_chzn_o_"+edtrSecTitle1)).getText();
					//enterText(secPpSectionTitleDDSearchBox, edtSectionTitle1);
					//enterKeyInKyBord(secPpSectionTitleDDSearchBox);
					
					//click(secPpSaveBtn);
					//thread();
				//}
				
				//		Verify Edited Authorized & Verified By Name
				getObjectText1(drEdtSecAuthorizedByLblNm);
				getObjectText(drEdtSecAuthorizedByNm);
				verifyAssertEquals("Authorized By : "+edtAuthorizedBy, getActualObjectTxt1+" "+getActualObjectTxt);	//	Check Authorized Person Name
				
				getObjectText1(drEdtSecVerifiedByLblNm);
				getObjectText(drEdtSecVerifiedByNm);
				verifyAssertEquals("Verified By : "+edtVerifiedBy, getActualObjectTxt1+" "+getActualObjectTxt);	//	Check Verified Person Name
				thread();
				
				//		Add Text/Paragraph - 2nd Text		//
				click(edtSecAddTextBtn);
				
				verifyAssert(adTxtAddTextParagraphPopup);
				getObjectText(adTxtAddTextParagraphPpTtl);
				verifyAssertEquals(drpln.getCell(9, 4).getContents(), getActualObjectTxt);		//	Add Text/Paragraph Pop Up Title
					
				getObjectText(adTxtPpEnterSomeTxtLbl);
				verifyAssertEquals(drpln.getCell(10, 1).getContents(), getActualObjectTxt);		//	Enter Some Text Label Name
					
				webElement(adTxtEnterSomeTextFld);
				switchToWEFrame(webelementFrame);
				enterText(scEdtrCommentsField, drpln.getCell(6, 1).getContents());		//	Enter Some Texts
				switchBackFromFrame();
				
				click(adTxtParagrphPpSaveBtn);
				thread();
				
				getObjectText(msgNotificationBar);
				verifyAssertEquals(drpln.getCell(8, 2).getContents(), getActualObjectTxt);		//	Text/Paragraph added successfully 
				
				webElement(adTxtEditAddedTxt2nd); 
				scrollInnerScrollBar(webelementFrame);		//	Scrolling Section Grid Inner Scroll Bar	
				
				getObjectText(adTxtEditAddedTxt2nd);
				verifyAssertEquals(drpln.getCell(6, 1).getContents(), getActualObjectTxt);		//	Verify Entered Text In Section Page
				thread();
				
				//		Mouse Hover - Move Last
				webElement(mHovrOnFirstOptn);
				mouseHoverOn(webelementFrame);
				click(moveLastIcon);				
				getObjectText(adTxtEditAddedTxt2nd); 
				verifyAssertEquals(drpln.getCell(5, 1).getContents(), getActualObjectTxt);
				thread();
				
				//	Mouse Hover - Move Top
				webElement(mHovrOnSecOptn);
				mouseHoverOn(webelementFrame);
				thread();
				webElement(moveTopIcon);
				mouseHoverOn(webelementFrame);
				click(moveTopIcon);				
				getObjectText(adTxtEditAddedTxt);
				verifyAssertEquals(drpln.getCell(6, 1).getContents(), getActualObjectTxt);	
				thread();
				
				//	Mouse Hover - Move Down
				webElement(mHovrOnFirstOptn);
				mouseHoverOn(webelementFrame);
				click(moveDownIcon);				
				getObjectText(adTxtEditAddedTxt);
				verifyAssertEquals(drpln.getCell(6, 1).getContents(), getActualObjectTxt);
				thread();
				
				//	Mouse Hover - Move Up
				webElement(mHovrOnSecOptn);
				mouseHoverOn(webelementFrame);
				click(moveUpIcon);
				getObjectText(adTxtEditAddedTxt);
				verifyAssertEquals(drpln.getCell(6, 1).getContents(), getActualObjectTxt);
				thread();
				
				//	Mouse Hover - Delete
				webElement(mHovrOnThirdOptn);
				mouseHoverOn(webelementFrame);
				click(removeIcon);
				
				verifyAssert(txtDeleteConfPopup);
				getObjectText(deleteConfPpMessage);
				verifyAssertEquals(drpln.getCell(8, 6).getContents(), getActualObjectTxt);
				click(delConfOkBtn);
				thread();				
			}catch(WebDriverException e1) {
				System.out.println("Display fields are not displaying");
				e1.printStackTrace();
			}	*/		
	//	}catch(WebDriverException e) {
	//		System.out.println("Create New Section Popup is not displaying");
	//		e.printStackTrace();
	//	}
		thread();
	}
	
	
	
	/*@Test(priority=4)
	void createLocationsSection() throws IOException, InterruptedException, BiffException
	{
		getDRPlanSheetFromExcel();
		 
		click(drEdtSecCreateNewSectionBtn);
		verifyAssert(createNewSectionPopup);
		
		//	Section Title
		click(secPpSectionTitleDDArrow);
		String lsecSectionTitle = drpln.getCell(1, 2).getContents();
		enterText(secPpSectionTitleDDSearchBox, lsecSectionTitle);
		
		try
		{
			verifyObjDisplay(clickToAddTxtInDD);
			if(chkObjDisplay == true)
			{
				click(clickToAddTxtInDD);
				enterKeyInKyBord(secPpSectionTitleDDSearchBox);
				verifyAssert(sectionAddConfPopup);
				getObjectText(deleteConfPpMessage); 
				verifyAssertEquals("Do you want to add "+drpln.getCell(1, 2).getContents()+" into Section Title ?", getActualObjectTxt);
				click(delConfOkBtn);
				thread();
				
				getObjectText(msgNotificationBar);
				verifyAssertEquals(drpln.getCell(1, 2).getContents()+" added into Section Title successfully", getActualObjectTxt);		//	Check Section Added Successfully
				thread();
				
				getObjectText(secPpSectionTitleDefTxt);
				verifyAssertEquals(drpln.getCell(1, 2).getContents(), getActualObjectTxt);		//	Verify Added Title In Drop Down
			}
		}catch(WebDriverException e){
			arrowDown(secPpSectionTitleDDSearchBox);
			enterKeyInKyBord(secPpSectionTitleDDSearchBox);
		}
		
		//	Authorized By
		click(secPpAuthorizedByDDArrow);
		getTotalValuesIndd(secPpAuthorizedByDDCnt);
		Random authBy = new Random();
		int rauthBy = authBy.nextInt(totalDDValCount-2)+2;
		String lsecAuthorizedBy = driver.findElement(By.id("ddlDSAuthorizedBy_chzn_o_"+rauthBy)).getText();
		enterText(secPpAuthorizedByDDSearchBox, lsecAuthorizedBy.split(",")[0]);
		enterKeyInKyBord(secPpAuthorizedByDDSearchBox);
		
		//	Verified By
		click(secPpVerifiedByDDArrow);
		getTotalValuesIndd(secPpVerifiedByDDCnt);
		Random veriBy = new Random();
		int rveriBy = veriBy.nextInt(totalDDValCount-2)+2;
		String lsecVerifiedBy = driver.findElement(By.id("ddlDSVerifiedBy_chzn_o_"+rveriBy)).getText();
		enterText(secPpVerifiedByDDSearchBox, lsecVerifiedBy.split(",")[0]);
		enterKeyInKyBord(secPpVerifiedByDDSearchBox);
		
		//	Description
		webElement(crSectionDescription);
		switchToWEFrame(webelementFrame);
		enterText(drscEdtrCommentsField, drpln.getCell(2, 2).getContents());
		switchBackFromFrame();
		takeScreenshot();
		
		click(secPpSaveBtn);
		thread();
			
		getObjectText(msgNotificationBar);
		verifyAssertEquals(drpln.getCell(8, 7).getContents(), getActualObjectTxt);		//	Section Created Successfully	
		
		getObjectText(lsecDescription);
		verifyAssertEquals(drpln.getCell(2, 2).getContents(), getActualObjectTxt);		//	Check Added Section Description
		
		click(lsecDescription);
		thread();
		
		//		Edit Description		//
		verifyAssert(createNewSectionPopup);
		getObjectText(secPpCreateNewSectionTtl);
		verifyAssertEquals(drpln.getCell(9, 3).getContents(), getActualObjectTxt);		//	Check Update Description Title
		
		webElement(crSectionDescription);
		switchToWEFrame(webelementFrame);
		useKeyFuncToRmvVal(drscEdtrCommentsField);
		enterText(drscEdtrCommentsField, drpln.getCell(3, 2).getContents());
		switchBackFromFrame();
		click(secPpSaveBtn);
		thread();
		
		getObjectText(msgNotificationBar);
		verifyAssertEquals(drpln.getCell(8, 1).getContents(), getActualObjectTxt);		//	Section Updated Successfully
		thread();	
		
		getObjectText(lsecDescription);
		verifyAssertEquals(drpln.getCell(3, 2).getContents(), getActualObjectTxt);
		
		
		//		Authorized & Verified By Person Names		//
		getObjectText1(lsecLblAuthorizedBy);
		getObjectText(lsecAuthorizedByNm);
		verifyAssertEquals("Authorized By : "+lsecAuthorizedBy, getActualObjectTxt1+" "+getActualObjectTxt);	//	Check Authorized Person Name
		
		getObjectText1(lsecLblVerifiedBy);
		getObjectText(lsecVerifiedByNm);
		verifyAssertEquals("Verified By : "+lsecVerifiedBy, getActualObjectTxt1+" "+getActualObjectTxt);	//	Check Verified Person Name
		thread();
		
		
		//		Add Text		//
		click(lsecAddTextBtn);
		
		verifyAssert(adTxtAddTextParagraphPopup);
		getObjectText(adTxtAddTextParagraphPpTtl);
		verifyAssertEquals(drpln.getCell(9, 4).getContents(), getActualObjectTxt);		//	Add Text/Paragraph Pop Up Title
			
		getObjectText(adTxtPpEnterSomeTxtLbl);
		verifyAssertEquals(drpln.getCell(10, 1).getContents(), getActualObjectTxt);	//	Enter Some Text Label Name
			
		webElement(adTxtEnterSomeTextFld);
		switchToWEFrame(webelementFrame);
		enterText(scEdtrCommentsField, drpln.getCell(4, 2).getContents());		//	Enter Some Texts
		switchBackFromFrame();
		
		click(adTxtParagrphPpSaveBtn);
		thread();
		
		getObjectText(msgNotificationBar);
		verifyAssertEquals(drpln.getCell(8, 2).getContents(), getActualObjectTxt);		//	Text/Paragraph added successfully 
		
		getObjectText(lsecfirstText);
		verifyAssertEquals(drpln.getCell(4, 2).getContents(), getActualObjectTxt);		//	Verify Entered Text In Section Page
		
		click(lsecfirstText);
		thread();
		
		//		Edit Text		//
		getObjectText(adTxtAddTextParagraphPpTtl);
		verifyAssertEquals(drpln.getCell(9, 5).getContents(), getActualObjectTxt);		//	Update Text/Paragraph Title
			
		webElement(adTxtEnterSomeTextFld);
		switchToWEFrame(webelementFrame);
		useKeyFuncToRmvVal(scEdtrCommentsField);	//	Edit Some Texts
		enterText(scEdtrCommentsField, drpln.getCell(5, 2).getContents());
		switchBackFromFrame();
			
		click(adTxtParagrphPpSaveBtn);
		thread();
			
		getObjectText(msgNotificationBar);
		verifyAssertEquals(drpln.getCell(8, 3).getContents(), getActualObjectTxt);		//	Text/Paragraph Updated Successfully
			
		getObjectText(lsecfirstText);
		verifyAssertEquals(drpln.getCell(5, 2).getContents(), getActualObjectTxt);		//	Verify Modified Text
		
		
		//		Add Dynamic Data		//
		click(lsecAddDataBtn);	
		thread();
		
		verifyAssert(addDynamicDataPopup);
			
		click(dynDtaPpModuleDDArrow);
		enterText(dynDtaPpModuleDDSearchBox, drpln.getCell(11, 2).getContents());		//	Choose Locations module
		enterKeyInKyBord(dynDtaPpModuleDDSearchBox);
		thread();
		
		try
		{
			verifyObjDisplay(dynDtaEmpSelectAllChkBox);
			if(chkObjDisplay == true)
			{
				//	Enable Select All Check Box
				click(dynDtaEmpSelectAllChkBox);						
				getTotalValuesIndd(dynDtaEmpDisplayFldCnt);
				try
				{
					for(int i=1; i<=totalDDValCount; i++)
					{
						boolean checked = driver.findElement(By.xpath("//div[@id='divDDataFields']/span["+i+"]/input")).isSelected();
						Assert.assertEquals(true, checked);
					}
						
					//	Disable Select All Check Box
					click(dynDtaEmpSelectAllChkBox);
					getTotalValuesIndd(dynDtaEmpDisplayFldCnt);
					for(int j=1; j<=totalDDValCount; j++)
					{
						boolean unchecked = driver.findElement(By.xpath("//div[@id='divDDataFields']/span["+j+"]/input")).isSelected();
						Assert.assertEquals(false, unchecked);
					}
				}catch(WebDriverException e3) {
						e3.printStackTrace();
				}
					
				click(lsecdfLocationName);
				click(lsecdfManager);
				click(lsecdfCity);
				click(lsecdfZipcode);
					
				click(dynDtaAddNewFilterBtn);	//	Add New Filter button
					
				click(dynDtaFieldDDArrow);
				enterText(dynDtaFieldDDSearchBox, "Location Name");
				enterKeyInKyBord(dynDtaFieldDDSearchBox);
				thread();
				
				//		Choose Filter Value
				click(dynDtaFieldValueDD);
				getTotalValuesIndd(dynDtaFieldValueCnt);
				
				ArrayList<Integer> alist = new ArrayList<Integer>();
				for(int i=1; i<totalDDValCount; i++)
				{
					alist.add(i);
				}
				Collections.shuffle(alist);
				
				for(int i=0; i<2; i++)
				{
					String randomValue = driver.findElement(By.id("ddlDDataNewFilterValues_chzn_o_"+alist.get(i))).getText();
					enterText(dynDtaFieldValueDD, randomValue);
					enterKeyInKyBord(dynDtaFieldValueDD);
					click(dynDtaFilterAddBtn);
					
					//		Check the same filter value exists
					try
					{							
						isAlertPresent();
						getAlertMsgText();
						verifyAssertEquals(filterValueExistsMsg, getActlAlertTxt);
						acceptAlertMessage();
						break;
					}catch(NoAlertPresentException noalrt) {
						
					}
				}		
					
				click(dynDtaPpSaveBtn);
				thread();
				
				getObjectText(msgNotificationBar);
				verifyAssertEquals(drpln.getCell(8, 4).getContents(), getActualObjectTxt);		//	Data Created Successfully
				thread();
				
				//		Check Selected Display Order in Table
				for(int i=1; i<5; i++)
				{
					String getColumnName = driver.findElement(By.xpath("//div[@id='divDynamicDRPlanSections']/div[2]//table[@class='table table-responsive table-striped table-bordered bootstrap-datatable dynamic-data-table']//tr/td["+i+"]")).getText();
					if(i == 1)
						verifyAssertEquals(drpln.getCell(12, 5).getContents(), getColumnName);	//	Location Name
					if(i == 2)
						verifyAssertEquals(drpln.getCell(12, 6).getContents(), getColumnName);	//	Manager
					if(i == 3)
						verifyAssertEquals(drpln.getCell(12, 7).getContents(), getColumnName);	//	City
					if(i == 4)
						verifyAssertEquals(drpln.getCell(12, 8).getContents(), getColumnName);	//	Alternate Employee
				}
				
				click(lsecLocationsList);
				thread();
				
				verifyAssert(addDynamicDataPopup);
				
				for(int i=2; i<6; i++)
				{
					boolean checked = driver.findElement(By.xpath("//div[@id='divDDataFields']/span["+i+"]/input")).isSelected();
					Assert.assertEquals(true, checked);
				}
						
				//	Drag & Drop the objects
				sourceElement(dynDtaEmpWeFirstName);
				destinationElement(dynDtaEmpWeLastName);
				dragAndDrop(sourceObj, destinationObj);		//	Change First & Last Name position
												
				sourceElement(dynDtaEmpWeMobile);
				destinationElement(dynDtaEmpWeAlternateEmployee);
				dragAndDrop(sourceObj, destinationObj);		//	Change Mobile No & Alternate Employee position
				
				//	Verify Added Filter Values
				try
				{
					verifyObjDisplay(dynDtaEmpSelectedFilterValue);
					if(chkObjDisplay == true)
					{
						verifyAssert(dynDtaEmpFilterValue1);
						verifyAssert(dynDtaEmpFilterValue2);
					}
				}catch(WebDriverException e) {
					System.out.println("Selected Filter values are not displaying in Edit screen");
					e.printStackTrace();
				}
				
				click(dynDtaPpSaveBtn);
				thread();
							
				getObjectText(msgNotificationBar);
				verifyAssertEquals(drpln.getCell(8, 5).getContents(), getActualObjectTxt);		//	Data Updated Successfully
				thread();
												
				//		Verify Column Name Order Changes In Table
				for(int i=1; i<5; i++)
				{
					String getColumnName = driver.findElement(By.xpath("//div[@id='divDynamicDRPlanSections']/div[2]//table[@class='table table-responsive table-striped table-bordered bootstrap-datatable dynamic-data-table']//tr/td["+i+"]")).getText();
					if(i == 1)
						verifyAssertEquals(drpln.getCell(12, 6).getContents(), getColumnName);	//	Manager
					if(i == 2)
						verifyAssertEquals(drpln.getCell(12, 5).getContents(), getColumnName);	//	Location Name
					if(i == 3)
						verifyAssertEquals(drpln.getCell(12, 8).getContents(), getColumnName);	//	Zip Code
					if(i == 4)
						verifyAssertEquals(drpln.getCell(12, 7).getContents(), getColumnName);	//	City
				}
				
							
				//		Edit Section		//
				click(lsecEditSectionBtn);
				thread();
			
				verifyAssert(createNewSectionPopup);
				getObjectText(secPpCreateNewSectionTtl);
				verifyAssertEquals(drpln.getCell(9, 2).getContents(), getActualObjectTxt);		//	Update Section Pop up Title
			
				getObjectText(secPpSectionTitleDefTxt);
				verifyAssertEquals(lsecSectionTitle, getActualObjectTxt);		//	Verify Selected Section Title Name 	
			
				getObjectText(secPpAuthorizedByDefTxt);
				verifyAssertEquals(lsecAuthorizedBy, getActualObjectTxt);	//	Check Selected Authorized By Name
			
				getObjectText(secPpVerifiedByDefTxt);
				verifyAssertEquals(lsecVerifiedBy, getActualObjectTxt);	//	Check Select Verified By Name
			
				//		Section Title
				//click(secPpSectionTitleDDArrow);
				//getTotalValuesIndd(secPpSectionTitleDDCnt);
				//Random edtSecTitle = new Random();
				//int edtrSecTitle = edtSecTitle.nextInt(totalDDValCount-2)+2;
				//String edtSectionTitle = driver.findElement(By.id("ddlNewDynamicSectionTitle_chzn_o_"+edtrSecTitle)).getText();
				//enterText(secPpSectionTitleDDSearchBox, edtSectionTitle);
				//enterKeyInKyBord(secPpSectionTitleDDSearchBox);
			
				//	Authorized By
				click(secPpAuthorizedByDDArrow);
				getTotalValuesIndd(secPpAuthorizedByDDCnt);
				Random edtAuthBy = new Random();
				int edtrAuthBy = edtAuthBy.nextInt(totalDDValCount-2)+2;
				String edtAuthorizedBy = driver.findElement(By.id("ddlDSAuthorizedBy_chzn_o_"+edtrAuthBy)).getText();
				enterText(secPpAuthorizedByDDSearchBox, edtAuthorizedBy.split(",")[0]);
				enterKeyInKyBord(secPpAuthorizedByDDSearchBox);
			
				//	Verified By
				click(secPpVerifiedByDDArrow);
				getTotalValuesIndd(secPpVerifiedByDDCnt);
				Random edtVeriBy = new Random();
				int edtrVeriBy = edtVeriBy.nextInt(totalDDValCount-2)+2;
				String edtVerifiedBy = driver.findElement(By.id("ddlDSVerifiedBy_chzn_o_"+edtrVeriBy)).getText();
				enterText(secPpVerifiedByDDSearchBox, edtVerifiedBy.split(",")[0]);
				enterKeyInKyBord(secPpVerifiedByDDSearchBox);
				
				click(secPpSaveBtn);
				thread();
				
				//try
				//{
				//	click(secPpSaveBtn);
				//	thread();
				
				//	getObjectText(msgNotificationBar);
				//	verifyAssertEquals(sectionUpdatedSuccessfullyMsg, getActualObjectTxt);		//	Section Updated Successfully
				//}catch(WebDriverException e) {
				//	e.printStackTrace();
				
				//	click(secPpSaveBtn);
					//thread();
				
					//getObjectText(msgNotificationBar);
					//verifyAssertEquals(lsecSectionTitle+sectionTitileNmAlreadyExistsMsg, getActualObjectTxt);		//	Section Title Already Exists
				
					//click(secPpSectionTitleDDArrow);
					//getTotalValuesIndd(secPpSectionTitleDDCnt);
					//Random edtSecTitle1 = new Random();
					//int edtrSecTitle1 = edtSecTitle1.nextInt(totalDDValCount-0)+0;
					//String edtSectionTitle1 = driver.findElement(By.id("ddlNewDynamicSectionTitle_chzn_o_"+edtrSecTitle1)).getText();
					//enterText(secPpSectionTitleDDSearchBox, edtSectionTitle1);
					//enterKeyInKyBord(secPpSectionTitleDDSearchBox);
				
					//click(secPpSaveBtn);
					//thread();
				//}
			
				//		Verify Edited Authorized & Verified By Name
				getObjectText1(drEdtSecAuthorizedByLblNm);
				getObjectText(lsecAuthorizedByNm);
				verifyAssertEquals("Authorized By : "+edtAuthorizedBy, getActualObjectTxt1+" "+getActualObjectTxt);	//	Check Authorized Person Name
			
				getObjectText1(drEdtSecVerifiedByLblNm);
				getObjectText(lsecVerifiedByNm);
				verifyAssertEquals("Verified By : "+edtVerifiedBy, getActualObjectTxt1+" "+getActualObjectTxt);	//	Check Verified Person Name
				thread();
			
				//		Add Text - 2nd Text		//
				click(lsecAddTextBtn);
			
				verifyAssert(adTxtAddTextParagraphPopup);
				getObjectText(adTxtAddTextParagraphPpTtl);
				verifyAssertEquals(drpln.getCell(9, 4).getContents(), getActualObjectTxt);		//	Add Text/Paragraph Pop Up Title
				
				getObjectText(adTxtPpEnterSomeTxtLbl);
				verifyAssertEquals(drpln.getCell(10, 1).getContents(), getActualObjectTxt);		//	Enter Some Text Label Name
				
				webElement(adTxtEnterSomeTextFld);
				switchToWEFrame(webelementFrame);
				enterText(scEdtrCommentsField, drpln.getCell(6, 2).getContents());		//	Enter Some Texts
				switchBackFromFrame();
			
				click(adTxtParagrphPpSaveBtn);
				thread();
			
				getObjectText(msgNotificationBar);
				verifyAssertEquals(drpln.getCell(8, 2).getContents(), getActualObjectTxt);		//	Text/Paragraph added successfully 
			
				webElement(lsec2ndTxt); 
				scrollInnerScrollBar(webelementFrame);		//	Scrolling Section Grid Inner Scroll Bar	
			
				getObjectText(lsec2ndTxt);
				verifyAssertEquals(drpln.getCell(6, 2).getContents(), getActualObjectTxt);		//	Verify Entered Text In Section Page
				thread();
			
				//		Mouse Hover - Move Last
				webElement(lsecmHovrOnFirstOptn);
				mouseHoverOn(webelementFrame);
				click(lsecmoveLastIcon);				
				getObjectText(lsec2ndTxt); 
				verifyAssertEquals(drpln.getCell(5, 2).getContents(), getActualObjectTxt);
				thread();
			
				//	Mouse Hover - Move Top
				webElement(lsecmHovrOnSecOptn);
				mouseHoverOn(webelementFrame);
				thread();
				webElement(lsecmoveTopIcon);
				mouseHoverOn(webelementFrame);
				click(lsecmoveTopIcon);				
				getObjectText(lsecfirstText);
				verifyAssertEquals(drpln.getCell(6, 2).getContents(), getActualObjectTxt);	
				thread();
			
				//	Mouse Hover - Move Down
				webElement(lsecmHovrOnFirstOptn);
				mouseHoverOn(webelementFrame);
				click(lsecmoveDownIcon);				
				getObjectText(lsec2ndTxt1);
				verifyAssertEquals(drpln.getCell(6, 2).getContents(), getActualObjectTxt);
				thread();
			
				//	Mouse Hover - Move Up
				webElement(lsecmHovrOnSecOptn);
				mouseHoverOn(webelementFrame);
				click(lsecmoveUpIcon);
				getObjectText(lsecfirstText);
				verifyAssertEquals(drpln.getCell(6, 2).getContents(), getActualObjectTxt);
				thread();
			
				//	Mouse Hover - Delete
				webElement(lsecmHovrOnThirdOptn);
				mouseHoverOn(webelementFrame);
				click(lsecremoveIcon);
			
				verifyAssert(txtDeleteConfPopup);
				getObjectText(deleteConfPpMessage);
				verifyAssertEquals(drpln.getCell(8, 6).getContents(), getActualObjectTxt);
				click(delConfOkBtn);
				thread();	
			}
			}catch(WebDriverException e1) {
				System.out.println("Display fields are not displaying");
				e1.printStackTrace();
			}
			thread();
		}
	
	@Test(priority=5)
	void createThreatsSection() throws InterruptedException, BiffException, IOException
	{
		getDRPlanSheetFromExcel();
		 
		click(drEdtSecCreateNewSectionBtn);
		verifyAssert(createNewSectionPopup);
		
		//	Section Title
		click(secPpSectionTitleDDArrow);
		String trsecSectionTitle = drpln.getCell(1, 3).getContents();	//	Add New Section Name (or) Choose Existing Section Name
		enterText(secPpSectionTitleDDSearchBox, trsecSectionTitle);
		
		try
		{
			verifyObjDisplay(clickToAddTxtInDD);
			if(chkObjDisplay == true)
			{
				click(clickToAddTxtInDD);
				enterKeyInKyBord(secPpSectionTitleDDSearchBox);
				verifyAssert(sectionAddConfPopup);
				getObjectText(deleteConfPpMessage); 
				verifyAssertEquals("Do you want to add "+drpln.getCell(1, 3).getContents()+" into Section Title ?", getActualObjectTxt);
				click(delConfOkBtn);
				thread();
				
				getObjectText(msgNotificationBar);
				verifyAssertEquals(drpln.getCell(1, 3).getContents()+" added into Section Title successfully", getActualObjectTxt);		//	Check New Section Added Successfully
				thread();
				
				getObjectText(secPpSectionTitleDefTxt);
				verifyAssertEquals(drpln.getCell(1, 3).getContents(), getActualObjectTxt);		//	Verify Added Title In Drop Down
			}
		}catch(WebDriverException e){
			arrowDown(secPpSectionTitleDDSearchBox);
			enterKeyInKyBord(secPpSectionTitleDDSearchBox);
		}
		
		//	Authorized By
		click(secPpAuthorizedByDDArrow);
		getTotalValuesIndd(secPpAuthorizedByDDCnt);
		Random authBy = new Random();
		int rauthBy = authBy.nextInt(totalDDValCount-2)+2;
		String trsecAuthorizedBy = driver.findElement(By.id("ddlDSAuthorizedBy_chzn_o_"+rauthBy)).getText();
		enterText(secPpAuthorizedByDDSearchBox, trsecAuthorizedBy.split(",")[0]);
		enterKeyInKyBord(secPpAuthorizedByDDSearchBox);
		
		//	Verified By
		click(secPpVerifiedByDDArrow);
		getTotalValuesIndd(secPpVerifiedByDDCnt);
		Random veriBy = new Random();
		int rveriBy = veriBy.nextInt(totalDDValCount-2)+2;
		String trsecVerifiedBy = driver.findElement(By.id("ddlDSVerifiedBy_chzn_o_"+rveriBy)).getText();
		enterText(secPpVerifiedByDDSearchBox, trsecVerifiedBy.split(",")[0]);
		enterKeyInKyBord(secPpVerifiedByDDSearchBox);
		
		//	Description
		webElement(crSectionDescription);
		switchToWEFrame(webelementFrame);
		enterText(drscEdtrCommentsField, drpln.getCell(2, 3).getContents());
		switchBackFromFrame();
		takeScreenshot();
		
		click(secPpSaveBtn);
		thread();
			
		getObjectText(msgNotificationBar);
		verifyAssertEquals(drpln.getCell(8, 7).getContents(), getActualObjectTxt);		//	Section Created Successfully	
		
		getObjectText(trsecDescription);
		verifyAssertEquals(drpln.getCell(2, 3).getContents(), getActualObjectTxt);		//	Check Added Section Description
		
		click(trsecDescription);
		thread();
		
		//		Edit Description		//
		verifyAssert(createNewSectionPopup);
		getObjectText(secPpCreateNewSectionTtl);
		verifyAssertEquals(drpln.getCell(9, 3).getContents(), getActualObjectTxt);		//	Check Update Description Title
		
		webElement(crSectionDescription);
		switchToWEFrame(webelementFrame);
		useKeyFuncToRmvVal(drscEdtrCommentsField);
		enterText(drscEdtrCommentsField, drpln.getCell(3, 3).getContents());
		switchBackFromFrame();
		click(secPpSaveBtn);
		thread();
		
		getObjectText(msgNotificationBar);
		verifyAssertEquals(drpln.getCell(8, 1).getContents(), getActualObjectTxt);		//	Section Updated Successfully
		thread();	
		
		getObjectText(trsecDescription);
		verifyAssertEquals(drpln.getCell(3, 3).getContents(), getActualObjectTxt);		//	Verify Edited Description Details
		
		
		//		Authorized & Verified By Person Names		//
		getObjectText1(lsecLblAuthorizedBy);
		getObjectText(trsecAuthorizedByNm);
		verifyAssertEquals("Authorized By : "+trsecAuthorizedBy, getActualObjectTxt1+" "+getActualObjectTxt);	//	Check Authorized Person Name
		
		getObjectText1(lsecLblVerifiedBy);
		getObjectText(trsecVerifiedByNm);
		verifyAssertEquals("Verified By : "+trsecVerifiedBy, getActualObjectTxt1+" "+getActualObjectTxt);	//	Check Verified Person Name
		thread();
		
		
		//		Add Text/Paragraph		//
		click(trsecAddTextBtn);
		
		verifyAssert(adTxtAddTextParagraphPopup);
		getObjectText(adTxtAddTextParagraphPpTtl);
		verifyAssertEquals(drpln.getCell(9, 4).getContents(), getActualObjectTxt);		//	Add Text/Paragraph Pop Up Title
			
		getObjectText(adTxtPpEnterSomeTxtLbl);
		verifyAssertEquals(drpln.getCell(10, 1).getContents(), getActualObjectTxt);	//	Enter Some Text Label Name
			
		webElement(adTxtEnterSomeTextFld);
		switchToWEFrame(webelementFrame);
		enterText(scEdtrCommentsField, drpln.getCell(4, 3).getContents());		//	Enter Some Texts
		switchBackFromFrame();
		
		click(adTxtParagrphPpSaveBtn);
		thread();
		
		getObjectText(msgNotificationBar);
		verifyAssertEquals(drpln.getCell(8, 2).getContents(), getActualObjectTxt);		//	Text/Paragraph added successfully 
		
		getObjectText(trsecfirstText);
		verifyAssertEquals(drpln.getCell(4, 3).getContents(), getActualObjectTxt);		//	Verify Entered Text In Section Page
		
		click(trsecfirstText);
		thread();
		
		//		Edit Text		//
		getObjectText(adTxtAddTextParagraphPpTtl);
		verifyAssertEquals(drpln.getCell(9, 5).getContents(), getActualObjectTxt);		//	Update Text/Paragraph Title
			
		webElement(adTxtEnterSomeTextFld);
		switchToWEFrame(webelementFrame);
		useKeyFuncToRmvVal(scEdtrCommentsField);	//	Edit Some Texts
		enterText(scEdtrCommentsField, drpln.getCell(5, 3).getContents());
		switchBackFromFrame();
			
		click(adTxtParagrphPpSaveBtn);
		thread();
			
		getObjectText(msgNotificationBar);
		verifyAssertEquals(drpln.getCell(8, 3).getContents(), getActualObjectTxt);		//	Text/Paragraph Updated Successfully
			
		getObjectText(trsecfirstText);
		verifyAssertEquals(drpln.getCell(5, 3).getContents(), getActualObjectTxt);		//	Verify Modified Text
		
		
		//		Add Dynamic Data		//
		click(trsecAddDataBtn);
		thread();
		
		verifyAssert(addDynamicDataPopup);
			
		click(dynDtaPpModuleDDArrow);
		enterText(dynDtaPpModuleDDSearchBox, drpln.getCell(11, 3).getContents());		//	Choose Threats module
		enterKeyInKyBord(dynDtaPpModuleDDSearchBox);
		thread();
		
		try
		{
			verifyObjDisplay(dynDtaEmpSelectAllChkBox);
			if(chkObjDisplay == true)
			{
				//	Enable Select All Check Box
				click(dynDtaEmpSelectAllChkBox);						
				getTotalValuesIndd(dynDtaEmpDisplayFldCnt);
				try
				{
					for(int i=1; i<=totalDDValCount; i++)
					{
						boolean checked = driver.findElement(By.xpath("//div[@id='divDDataFields']/span["+i+"]/input")).isSelected();
						Assert.assertEquals(true, checked);
					}
						
					//	Disable Select All Check Box
					click(dynDtaEmpSelectAllChkBox);
					getTotalValuesIndd(dynDtaEmpDisplayFldCnt);
					for(int j=1; j<=totalDDValCount; j++)
					{
						boolean unchecked = driver.findElement(By.xpath("//div[@id='divDDataFields']/span["+j+"]/input")).isSelected();
						Assert.assertEquals(false, unchecked);
					}
				}catch(WebDriverException e3) {
						e3.printStackTrace();
				}
					
				click(trsecdfThreatName);
				click(trsecdfComments);
									
				click(dynDtaAddNewFilterBtn);	//	Add New Filter button
					
				click(dynDtaFieldDDArrow);
				enterText(dynDtaFieldDDSearchBox, "Threat Name");		//	Choose Filter Field
				enterKeyInKyBord(dynDtaFieldDDSearchBox);
				thread();
				
				//		Choose Filter Value
				click(dynDtaFieldValueDD);
				getTotalValuesIndd(dynDtaFieldValueCnt);
				
				ArrayList<Integer> alist = new ArrayList<Integer>();
				for(int i=1; i<totalDDValCount; i++)
				{
					alist.add(i);
				}
				Collections.shuffle(alist);
				
				for(int i=0; i<2; i++)
				{
					String randomValue = driver.findElement(By.id("ddlDDataNewFilterValues_chzn_o_"+alist.get(i))).getText();
					enterText(dynDtaFieldValueDD, randomValue);
					enterKeyInKyBord(dynDtaFieldValueDD);
					click(dynDtaFilterAddBtn);
					
					//		Check the same filter value exists
					try
					{							
						isAlertPresent();
						getAlertMsgText();
						verifyAssertEquals(filterValueExistsMsg, getActlAlertTxt);
						acceptAlertMessage();
						break;
					}catch(NoAlertPresentException noalrt) {
						
					}
				}		
					
				click(dynDtaPpSaveBtn);
				thread();
				
				getObjectText(msgNotificationBar);
				verifyAssertEquals(drpln.getCell(8, 4).getContents(), getActualObjectTxt);		//	Data Created Successfully
				thread();
				
				//		Check Selected Display Order in Table
				for(int i=1; i<3; i++)
				{
					String getColumnName = driver.findElement(By.xpath("//div[@id='divDynamicDRPlanSections']/div[3]//table[@class='table table-responsive table-striped table-bordered bootstrap-datatable dynamic-data-table']//tr/td["+i+"]")).getText();
					if(i == 1)
						verifyAssertEquals(drpln.getCell(12, 9).getContents(), getColumnName);	//	Threat Name
					if(i == 2)
						verifyAssertEquals(drpln.getCell(12, 10).getContents(), getColumnName);	//	Comments
				}
				
				click(trsecThreatsList);
				thread();
				
				verifyAssert(addDynamicDataPopup);
				
				for(int i=2; i<4; i++)
				{
					boolean checked = driver.findElement(By.xpath("//div[@id='divDDataFields']/span["+i+"]/input")).isSelected();
					Assert.assertEquals(true, checked);
				}
						
				//	Drag & Drop the objects
				sourceElement(dynDtaEmpWeFirstName);
				destinationElement(dynDtaEmpWeLastName);
				dragAndDrop(sourceObj, destinationObj);		//	Change Threat Name & Comments position				
				
				//	Verify Added Filter Values
				try
				{
					verifyObjDisplay(dynDtaEmpSelectedFilterValue);
					if(chkObjDisplay == true)
					{
						verifyAssert(dynDtaEmpFilterValue1);
						verifyAssert(dynDtaEmpFilterValue2);
					}
				}catch(WebDriverException e) {
					System.out.println("Selected Filter values are not displaying in Edit screen");
					e.printStackTrace();
				}
				
				click(dynDtaPpSaveBtn);
				thread();
							
				getObjectText(msgNotificationBar);
				verifyAssertEquals(drpln.getCell(8, 5).getContents(), getActualObjectTxt);		//	Data Updated Successfully
				thread();
												
				//		Verify Column Name Order Changes In Table
				for(int i=1; i<3; i++)
				{
					String getColumnName = driver.findElement(By.xpath("//div[@id='divDynamicDRPlanSections']/div[3]//table[@class='table table-responsive table-striped table-bordered bootstrap-datatable dynamic-data-table']//tr/td["+i+"]")).getText();
					if(i == 1)
						verifyAssertEquals(drpln.getCell(12, 10).getContents(), getColumnName);	//	Comments
					if(i == 2)
						verifyAssertEquals(drpln.getCell(12, 9).getContents(), getColumnName);	//	Threat Name
				}				
							
				//		Edit Section		//
				click(trsecEditSectionBtn);
				thread();
			
				verifyAssert(createNewSectionPopup);
				getObjectText(secPpCreateNewSectionTtl);
				verifyAssertEquals(drpln.getCell(9, 2).getContents(), getActualObjectTxt);		//	Update Section Pop up Title
			
				getObjectText(secPpSectionTitleDefTxt);
				verifyAssertEquals(trsecSectionTitle, getActualObjectTxt);		//	Verify Selected Section Title Name 	
			
				getObjectText(secPpAuthorizedByDefTxt);
				verifyAssertEquals(trsecAuthorizedBy, getActualObjectTxt);		//	Check Selected Authorized By Name
			
				getObjectText(secPpVerifiedByDefTxt);
				verifyAssertEquals(trsecVerifiedBy, getActualObjectTxt);		//	Check Select Verified By Name
			
				//		Section Title
				//click(secPpSectionTitleDDArrow);
				//getTotalValuesIndd(secPpSectionTitleDDCnt);
				//Random edtSecTitle = new Random();
				//int edtrSecTitle = edtSecTitle.nextInt(totalDDValCount-2)+2;
				//String edtSectionTitle = driver.findElement(By.id("ddlNewDynamicSectionTitle_chzn_o_"+edtrSecTitle)).getText();
				//enterText(secPpSectionTitleDDSearchBox, edtSectionTitle);
				//enterKeyInKyBord(secPpSectionTitleDDSearchBox);
			
				//	Authorized By
				click(secPpAuthorizedByDDArrow);
				getTotalValuesIndd(secPpAuthorizedByDDCnt);
				Random edtAuthBy = new Random();
				int edtrAuthBy = edtAuthBy.nextInt(totalDDValCount-2)+2;
				String edtAuthorizedBy = driver.findElement(By.id("ddlDSAuthorizedBy_chzn_o_"+edtrAuthBy)).getText();
				enterText(secPpAuthorizedByDDSearchBox, edtAuthorizedBy.split(",")[0]);
				enterKeyInKyBord(secPpAuthorizedByDDSearchBox);
			
				//	Verified By
				click(secPpVerifiedByDDArrow);
				getTotalValuesIndd(secPpVerifiedByDDCnt);
				Random edtVeriBy = new Random();
				int edtrVeriBy = edtVeriBy.nextInt(totalDDValCount-2)+2;
				String edtVerifiedBy = driver.findElement(By.id("ddlDSVerifiedBy_chzn_o_"+edtrVeriBy)).getText();
				enterText(secPpVerifiedByDDSearchBox, edtVerifiedBy.split(",")[0]);
				enterKeyInKyBord(secPpVerifiedByDDSearchBox);
				
				click(secPpSaveBtn);
				thread();
				
				//try
				//{
				//	click(secPpSaveBtn);
				//	thread();
				
				//	getObjectText(msgNotificationBar);
				//	verifyAssertEquals(sectionUpdatedSuccessfullyMsg, getActualObjectTxt);		//	Section Updated Successfully
				//}catch(WebDriverException e) {
				//	e.printStackTrace();
				
				//	click(secPpSaveBtn);
					//thread();
				
					//getObjectText(msgNotificationBar);
					//verifyAssertEquals(lsecSectionTitle+sectionTitileNmAlreadyExistsMsg, getActualObjectTxt);		//	Section Title Already Exists
				
					//click(secPpSectionTitleDDArrow);
					//getTotalValuesIndd(secPpSectionTitleDDCnt);
					//Random edtSecTitle1 = new Random();
					//int edtrSecTitle1 = edtSecTitle1.nextInt(totalDDValCount-0)+0;
					//String edtSectionTitle1 = driver.findElement(By.id("ddlNewDynamicSectionTitle_chzn_o_"+edtrSecTitle1)).getText();
					//enterText(secPpSectionTitleDDSearchBox, edtSectionTitle1);
					//enterKeyInKyBord(secPpSectionTitleDDSearchBox);
				
					//click(secPpSaveBtn);
					//thread();
				//}
			
				//		Verify Edited Authorized & Verified By Name
				getObjectText1(drEdtSecAuthorizedByLblNm);
				getObjectText(trsecAuthorizedByNm);
				verifyAssertEquals("Authorized By : "+edtAuthorizedBy, getActualObjectTxt1+" "+getActualObjectTxt);	//	Check Authorized Person Name
			
				getObjectText1(drEdtSecVerifiedByLblNm);
				getObjectText(trsecVerifiedByNm);
				verifyAssertEquals("Verified By : "+edtVerifiedBy, getActualObjectTxt1+" "+getActualObjectTxt);	//	Check Verified Person Name
				thread();
			
				//		Add Text - 2nd Text		//
				click(trsecAddTextBtn);
			
				verifyAssert(adTxtAddTextParagraphPopup);
				getObjectText(adTxtAddTextParagraphPpTtl);
				verifyAssertEquals(drpln.getCell(9, 4).getContents(), getActualObjectTxt);		//	Add Text/Paragraph Pop Up Title
				
				getObjectText(adTxtPpEnterSomeTxtLbl);
				verifyAssertEquals(drpln.getCell(10, 1).getContents(), getActualObjectTxt);		//	Enter Some Text Label Name
				
				webElement(adTxtEnterSomeTextFld);
				switchToWEFrame(webelementFrame);
				enterText(scEdtrCommentsField, drpln.getCell(6, 3).getContents());		//	Enter Some Texts
				switchBackFromFrame();
			
				click(adTxtParagrphPpSaveBtn);
				thread();
			
				getObjectText(msgNotificationBar);
				verifyAssertEquals(drpln.getCell(8, 2).getContents(), getActualObjectTxt);		//	Text/Paragraph added successfully 
			
				webElement(trsec2ndTxt); 
				scrollInnerScrollBar(webelementFrame);		//	Scrolling Section Grid Inner Scroll Bar	
			
				getObjectText(trsec2ndTxt);
				verifyAssertEquals(drpln.getCell(6, 3).getContents(), getActualObjectTxt);		//	Verify Entered Text In Section Page
				thread();
			
				//		Mouse Hover - Move Last
				webElement(trsecmHovrOnFirstOptn);
				mouseHoverOn(webelementFrame);
				click(trsecmoveLastIcon);				
				getObjectText(trsec2ndTxt); 
				verifyAssertEquals(drpln.getCell(5, 3).getContents(), getActualObjectTxt);
				thread();
			
				//	Mouse Hover - Move Top
				webElement(trsecmHovrOnSecOptn);
				mouseHoverOn(webelementFrame);
				thread();
				webElement(trsecmoveTopIcon);
				mouseHoverOn(webelementFrame);
				click(trsecmoveTopIcon);				
				getObjectText(trsecfirstText);
				verifyAssertEquals(drpln.getCell(6, 3).getContents(), getActualObjectTxt);	
				thread();
			
				//	Mouse Hover - Move Down
				webElement(trsecmHovrOnFirstOptn);
				mouseHoverOn(webelementFrame);
				click(trsecmoveDownIcon);				
				getObjectText(trsec2ndTxt1);
				verifyAssertEquals(drpln.getCell(6, 3).getContents(), getActualObjectTxt);
				thread();
			
				//	Mouse Hover - Move Up
				webElement(trsecmHovrOnSecOptn);
				mouseHoverOn(webelementFrame);
				click(trsecmoveUpIcon);
				getObjectText(trsecfirstText);
				verifyAssertEquals(drpln.getCell(6, 3).getContents(), getActualObjectTxt);
				thread();
			
				//	Mouse Hover - Delete
				webElement(trsecmHovrOnThirdOptn);
				mouseHoverOn(webelementFrame);
				click(trsecremoveIcon);
			
				verifyAssert(txtDeleteConfPopup);
				getObjectText(deleteConfPpMessage);
				verifyAssertEquals(drpln.getCell(8, 6).getContents(), getActualObjectTxt);
				click(delConfOkBtn);
				thread();	
			}
			}catch(WebDriverException e1) {
				System.out.println("Display fields are not displaying");
				e1.printStackTrace();
			}
			thread();
		}
	
	@Test(priority=6)
	void addTeamsSection() throws InterruptedException, BiffException, IOException
	{
		getDRPlanSheetFromExcel();
		 
		click(drEdtSecCreateNewSectionBtn);
		verifyAssert(createNewSectionPopup);
		
		//	Section Title
		click(secPpSectionTitleDDArrow);
		String tmsecSectionTitle = drpln.getCell(1, 4).getContents();	//	Add New Section Name (or) Choose Existing Section Name
		enterText(secPpSectionTitleDDSearchBox, tmsecSectionTitle);
		
		try
		{
			verifyObjDisplay(clickToAddTxtInDD);
			if(chkObjDisplay == true)
			{
				click(clickToAddTxtInDD); 
				enterKeyInKyBord(secPpSectionTitleDDSearchBox);
				verifyAssert(sectionAddConfPopup);
				getObjectText(deleteConfPpMessage); 
				verifyAssertEquals("Do you want to add "+drpln.getCell(1, 4).getContents()+" into Section Title ?", getActualObjectTxt);
				click(delConfOkBtn);
				thread();
				
				getObjectText(msgNotificationBar);
				verifyAssertEquals(drpln.getCell(1, 4).getContents()+" added into Section Title successfully", getActualObjectTxt);		//	Check New Section Added Successfully
				thread();
				
				getObjectText(secPpSectionTitleDefTxt);
				verifyAssertEquals(drpln.getCell(1, 4).getContents(), getActualObjectTxt);		//	Verify Added Title In Drop Down
			}
		}catch(WebDriverException e){
			arrowDown(secPpSectionTitleDDSearchBox); 
			enterKeyInKyBord(secPpSectionTitleDDSearchBox);
		}
		
		//	Authorized By
		click(secPpAuthorizedByDDArrow);
		getTotalValuesIndd(secPpAuthorizedByDDCnt);
		Random authBy = new Random();
		int rauthBy = authBy.nextInt(totalDDValCount-2)+2;
		String tmsecAuthorizedBy = driver.findElement(By.id("ddlDSAuthorizedBy_chzn_o_"+rauthBy)).getText();
		enterText(secPpAuthorizedByDDSearchBox, tmsecAuthorizedBy.split(",")[0]);
		enterKeyInKyBord(secPpAuthorizedByDDSearchBox);
		
		//	Verified By
		click(secPpVerifiedByDDArrow);
		getTotalValuesIndd(secPpVerifiedByDDCnt);
		Random veriBy = new Random();
		int rveriBy = veriBy.nextInt(totalDDValCount-2)+2;
		String tmsecVerifiedBy = driver.findElement(By.id("ddlDSVerifiedBy_chzn_o_"+rveriBy)).getText();
		enterText(secPpVerifiedByDDSearchBox, tmsecVerifiedBy.split(",")[0]);
		enterKeyInKyBord(secPpVerifiedByDDSearchBox);
		
		//	Description
		webElement(crSectionDescription);
		switchToWEFrame(webelementFrame);
		enterText(drscEdtrCommentsField, drpln.getCell(2, 4).getContents());
		switchBackFromFrame();
		takeScreenshot();
		
		click(secPpSaveBtn);
		thread();
			
		getObjectText(msgNotificationBar);
		verifyAssertEquals(drpln.getCell(8, 7).getContents(), getActualObjectTxt);		//	Section Created Successfully	
		
		getObjectText(tmsecDescription);
		verifyAssertEquals(drpln.getCell(2, 4).getContents(), getActualObjectTxt);		//	Check Added Section Description
		
		click(tmsecDescription);
		thread();
		
		//		Edit Description		//
		verifyAssert(createNewSectionPopup);
		getObjectText(secPpCreateNewSectionTtl);
		verifyAssertEquals(drpln.getCell(9, 3).getContents(), getActualObjectTxt);		//	Check Update Description Title
		
		webElement(crSectionDescription);
		switchToWEFrame(webelementFrame);
		useKeyFuncToRmvVal(drscEdtrCommentsField);
		enterText(drscEdtrCommentsField, drpln.getCell(3, 4).getContents());
		switchBackFromFrame();
		click(secPpSaveBtn);
		thread();
		
		getObjectText(msgNotificationBar);
		verifyAssertEquals(drpln.getCell(8, 1).getContents(), getActualObjectTxt);		//	Section Updated Successfully
		thread();	
		
		getObjectText(tmsecDescription);
		verifyAssertEquals(drpln.getCell(3, 4).getContents(), getActualObjectTxt);		//	Verify Edited Description Details
		
		
		//		Authorized & Verified By Person Names		//
		getObjectText1(lsecLblAuthorizedBy);
		getObjectText(tmsecAuthorizedByNm);
		verifyAssertEquals("Authorized By : "+tmsecAuthorizedBy, getActualObjectTxt1+" "+getActualObjectTxt);	//	Check Authorized Person Name
		
		getObjectText1(lsecLblVerifiedBy);
		getObjectText(tmsecVerifiedByNm);
		verifyAssertEquals("Verified By : "+tmsecVerifiedBy, getActualObjectTxt1+" "+getActualObjectTxt);	//	Check Verified Person Name
		thread();
		
		
		//		Add Text/Paragraph		//
		click(tmsecAddTextBtn);
		
		verifyAssert(adTxtAddTextParagraphPopup);
		getObjectText(adTxtAddTextParagraphPpTtl);
		verifyAssertEquals(drpln.getCell(9, 4).getContents(), getActualObjectTxt);		//	Add Text/Paragraph Pop Up Title
			
		getObjectText(adTxtPpEnterSomeTxtLbl);
		verifyAssertEquals(drpln.getCell(10, 1).getContents(), getActualObjectTxt);	//	Enter Some Text Label Name
			
		webElement(adTxtEnterSomeTextFld);
		switchToWEFrame(webelementFrame);
		enterText(scEdtrCommentsField, drpln.getCell(4, 4).getContents());		//	Enter Some Texts
		switchBackFromFrame();
		
		click(adTxtParagrphPpSaveBtn);
		thread();
		
		getObjectText(msgNotificationBar);
		verifyAssertEquals(drpln.getCell(8, 2).getContents(), getActualObjectTxt);		//	Text/Paragraph added successfully 
		
		getObjectText(tmsecfirstText);
		verifyAssertEquals(drpln.getCell(4, 4).getContents(), getActualObjectTxt);		//	Verify Entered Text In Section Page
		
		click(tmsecfirstText);
		thread();
		
		//		Edit Text		//
		getObjectText(adTxtAddTextParagraphPpTtl);
		verifyAssertEquals(drpln.getCell(9, 5).getContents(), getActualObjectTxt);		//	Update Text/Paragraph Title
			
		webElement(adTxtEnterSomeTextFld);
		switchToWEFrame(webelementFrame);
		useKeyFuncToRmvVal(scEdtrCommentsField);	//	Edit Some Texts
		enterText(scEdtrCommentsField, drpln.getCell(5, 4).getContents());
		switchBackFromFrame();
			
		click(adTxtParagrphPpSaveBtn);
		thread();
			
		getObjectText(msgNotificationBar);
		verifyAssertEquals(drpln.getCell(8, 3).getContents(), getActualObjectTxt);		//	Text/Paragraph Updated Successfully
			
		getObjectText(tmsecfirstText);
		verifyAssertEquals(drpln.getCell(5, 4).getContents(), getActualObjectTxt);		//	Verify Modified Text
		
		
		//		Add Dynamic Data		//
		click(tmsecAddDataBtn);
		thread();
		
		verifyAssert(addDynamicDataPopup);
			
		click(dynDtaPpModuleDDArrow);
		enterText(dynDtaPpModuleDDSearchBox, drpln.getCell(11, 4).getContents());		//	Choose Threats module
		enterKeyInKyBord(dynDtaPpModuleDDSearchBox);
		thread();
		
		try
		{
			verifyObjDisplay(dynDtaEmpSelectAllChkBox);
			if(chkObjDisplay == true)
			{
				//	Enable Select All Check Box
				click(dynDtaEmpSelectAllChkBox);						
				getTotalValuesIndd(dynDtaEmpDisplayFldCnt);
				try
				{
					for(int i=1; i<=totalDDValCount; i++)
					{
						boolean checked = driver.findElement(By.xpath("//div[@id='divDDataFields']/span["+i+"]/input")).isSelected();
						Assert.assertEquals(true, checked);
					}
						
					//	Disable Select All Check Box
					click(dynDtaEmpSelectAllChkBox);
					getTotalValuesIndd(dynDtaEmpDisplayFldCnt);
					for(int j=1; j<=totalDDValCount; j++)
					{
						boolean unchecked = driver.findElement(By.xpath("//div[@id='divDDataFields']/span["+j+"]/input")).isSelected();
						Assert.assertEquals(false, unchecked);
					}
				}catch(WebDriverException e3) {
						e3.printStackTrace();
				}
					
				click(tmsecdfTeamName);
				click(tmsecdfTeamLeader);
				click(tmsecdfTeamType);
				click(tmsecdfLocation);
									
				click(dynDtaAddNewFilterBtn);	//	Add New Filter button
					
				click(dynDtaFieldDDArrow);
				enterText(dynDtaFieldDDSearchBox, "Team Name");		//	Choose Filter Field
				enterKeyInKyBord(dynDtaFieldDDSearchBox);
				thread();
				
				//		Choose Filter Value
				click(dynDtaFieldValueDD);
				getTotalValuesIndd(dynDtaFieldValueCnt);
				
				ArrayList<Integer> alist = new ArrayList<Integer>();
				for(int i=1; i<totalDDValCount; i++)
				{
					alist.add(i);
				}
				Collections.shuffle(alist);
				
				for(int i=0; i<2; i++)
				{
					String randomValue = driver.findElement(By.id("ddlDDataNewFilterValues_chzn_o_"+alist.get(i))).getText();
					enterText(dynDtaFieldValueDD, randomValue);
					enterKeyInKyBord(dynDtaFieldValueDD);
					click(dynDtaFilterAddBtn);
					
					//		Check the same filter value exists
					try
					{							
						isAlertPresent();
						getAlertMsgText();
						verifyAssertEquals(filterValueExistsMsg, getActlAlertTxt);
						acceptAlertMessage();
						break;
					}catch(NoAlertPresentException noalrt) {
						
					}
				}		
					
				click(dynDtaPpSaveBtn);
				thread();
				
				getObjectText(msgNotificationBar);
				verifyAssertEquals(drpln.getCell(8, 4).getContents(), getActualObjectTxt);		//	Data Created Successfully
				thread();
				
				//		Check Selected Display Order in Table
				for(int i=1; i<5; i++)
				{
					String getColumnName = driver.findElement(By.xpath("//div[@id='divDynamicDRPlanSections']/div[4]//table[@class='table table-responsive table-striped table-bordered bootstrap-datatable dynamic-data-table']//tr/td["+i+"]")).getText();
					if(i == 1)
						verifyAssertEquals(drpln.getCell(12, 11).getContents(), getColumnName);	//	Team Name
					if(i == 2)
						verifyAssertEquals(drpln.getCell(12, 12).getContents(), getColumnName);	//	Team Leader
					if(i == 3)
						verifyAssertEquals(drpln.getCell(12, 13).getContents(), getColumnName);	//	Team Type
					if(i == 4)
						verifyAssertEquals(drpln.getCell(12, 14).getContents(), getColumnName);	//	Location
				}
				
				click(tmsecTeamsList);
				thread();
				
				verifyAssert(addDynamicDataPopup);
				
				for(int i=2; i<4; i++)
				{
					boolean checked = driver.findElement(By.xpath("//div[@id='divDDataFields']/span["+i+"]/input")).isSelected();
					Assert.assertEquals(true, checked);
				}
						
				//	Drag & Drop the objects
				sourceElement(dynDtaEmpWeFirstName);
				destinationElement(dynDtaEmpWeLastName);
				dragAndDrop(sourceObj, destinationObj);		//	Change Team Name & Leader position				
				
				sourceElement(dynDtaEmpWeMobile);
				destinationElement(dynDtaEmpWeAlternateEmployee);
				dragAndDrop(sourceObj, destinationObj);		//	Change Team Type & Location position
				
				//	Verify Added Filter Values
				try
				{
					verifyObjDisplay(dynDtaEmpSelectedFilterValue);
					if(chkObjDisplay == true)
					{
						verifyAssert(dynDtaEmpFilterValue1);
						verifyAssert(dynDtaEmpFilterValue2);
					}
				}catch(WebDriverException e) {
					System.out.println("Selected Filter values are not displaying in Edit screen");
					e.printStackTrace();
				}
				
				click(dynDtaPpSaveBtn);
				thread();
							
				getObjectText(msgNotificationBar);
				verifyAssertEquals(drpln.getCell(8, 5).getContents(), getActualObjectTxt);		//	Data Updated Successfully
				thread();
												
				//		Verify Column Name Order Changes In Table
				for(int i=1; i<5; i++)
				{
					String getColumnName = driver.findElement(By.xpath("//div[@id='divDynamicDRPlanSections']/div[4]//table[@class='table table-responsive table-striped table-bordered bootstrap-datatable dynamic-data-table']//tr/td["+i+"]")).getText();
					if(i == 1)
						verifyAssertEquals(drpln.getCell(12, 12).getContents(), getColumnName);	//	Team Leader
					if(i == 2)
						verifyAssertEquals(drpln.getCell(12, 11).getContents(), getColumnName);	//	Team Name
					if(i == 3)
						verifyAssertEquals(drpln.getCell(12, 14).getContents(), getColumnName);	//	Location
					if(i == 4)
						verifyAssertEquals(drpln.getCell(12, 13).getContents(), getColumnName);	//	Team Type
				}				
							
				//		Edit Section		//
				click(tmsecEditSectionBtn);
				thread();
			
				verifyAssert(createNewSectionPopup);
				getObjectText(secPpCreateNewSectionTtl);
				verifyAssertEquals(drpln.getCell(9, 2).getContents(), getActualObjectTxt);		//	Update Section Pop up Title
			
				getObjectText(secPpSectionTitleDefTxt);
				verifyAssertEquals(tmsecSectionTitle, getActualObjectTxt);		//	Verify Selected Section Title Name 	
			
				getObjectText(secPpAuthorizedByDefTxt);
				verifyAssertEquals(tmsecAuthorizedBy, getActualObjectTxt);		//	Check Selected Authorized By Name
			
				getObjectText(secPpVerifiedByDefTxt);
				verifyAssertEquals(tmsecVerifiedBy, getActualObjectTxt);		//	Check Select Verified By Name
			
				//		Section Title
				//click(secPpSectionTitleDDArrow);
				//getTotalValuesIndd(secPpSectionTitleDDCnt);
				//Random edtSecTitle = new Random();
				//int edtrSecTitle = edtSecTitle.nextInt(totalDDValCount-2)+2;
				//String edtSectionTitle = driver.findElement(By.id("ddlNewDynamicSectionTitle_chzn_o_"+edtrSecTitle)).getText();
				//enterText(secPpSectionTitleDDSearchBox, edtSectionTitle);
				//enterKeyInKyBord(secPpSectionTitleDDSearchBox);
			
				//	Authorized By
				click(secPpAuthorizedByDDArrow);
				getTotalValuesIndd(secPpAuthorizedByDDCnt);
				Random edtAuthBy = new Random();
				int edtrAuthBy = edtAuthBy.nextInt(totalDDValCount-2)+2;
				String edtAuthorizedBy = driver.findElement(By.id("ddlDSAuthorizedBy_chzn_o_"+edtrAuthBy)).getText();
				enterText(secPpAuthorizedByDDSearchBox, edtAuthorizedBy.split(",")[0]);
				enterKeyInKyBord(secPpAuthorizedByDDSearchBox);
			
				//	Verified By
				click(secPpVerifiedByDDArrow);
				getTotalValuesIndd(secPpVerifiedByDDCnt);
				Random edtVeriBy = new Random();
				int edtrVeriBy = edtVeriBy.nextInt(totalDDValCount-2)+2;
				String edtVerifiedBy = driver.findElement(By.id("ddlDSVerifiedBy_chzn_o_"+edtrVeriBy)).getText();
				enterText(secPpVerifiedByDDSearchBox, edtVerifiedBy.split(",")[0]);
				enterKeyInKyBord(secPpVerifiedByDDSearchBox);
				
				click(secPpSaveBtn);
				thread();
				
				//try
				//{
				//	click(secPpSaveBtn);
				//	thread();
				
				//	getObjectText(msgNotificationBar);
				//	verifyAssertEquals(sectionUpdatedSuccessfullyMsg, getActualObjectTxt);		//	Section Updated Successfully
				//}catch(WebDriverException e) {
				//	e.printStackTrace();
				
				//	click(secPpSaveBtn);
					//thread();
				
					//getObjectText(msgNotificationBar);
					//verifyAssertEquals(lsecSectionTitle+sectionTitileNmAlreadyExistsMsg, getActualObjectTxt);		//	Section Title Already Exists
				
					//click(secPpSectionTitleDDArrow);
					//getTotalValuesIndd(secPpSectionTitleDDCnt);
					//Random edtSecTitle1 = new Random();
					//int edtrSecTitle1 = edtSecTitle1.nextInt(totalDDValCount-0)+0;
					//String edtSectionTitle1 = driver.findElement(By.id("ddlNewDynamicSectionTitle_chzn_o_"+edtrSecTitle1)).getText();
					//enterText(secPpSectionTitleDDSearchBox, edtSectionTitle1);
					//enterKeyInKyBord(secPpSectionTitleDDSearchBox);
				
					//click(secPpSaveBtn);
					//thread();
				//}
			
				//		Verify Edited Authorized & Verified By Name
				getObjectText1(drEdtSecAuthorizedByLblNm);
				getObjectText(tmsecAuthorizedByNm);
				verifyAssertEquals("Authorized By : "+edtAuthorizedBy, getActualObjectTxt1+" "+getActualObjectTxt);	//	Check Authorized Person Name
			
				getObjectText1(drEdtSecVerifiedByLblNm);
				getObjectText(tmsecVerifiedByNm);
				verifyAssertEquals("Verified By : "+edtVerifiedBy, getActualObjectTxt1+" "+getActualObjectTxt);	//	Check Verified Person Name
				thread();
			
				//		Add Text - 2nd Text		//
				click(tmsecAddTextBtn);
			
				verifyAssert(adTxtAddTextParagraphPopup);
				getObjectText(adTxtAddTextParagraphPpTtl);
				verifyAssertEquals(drpln.getCell(9, 4).getContents(), getActualObjectTxt);		//	Add Text/Paragraph Pop Up Title
				
				getObjectText(adTxtPpEnterSomeTxtLbl);
				verifyAssertEquals(drpln.getCell(10, 1).getContents(), getActualObjectTxt);		//	Enter Some Text Label Name
				
				webElement(adTxtEnterSomeTextFld);
				switchToWEFrame(webelementFrame);
				enterText(scEdtrCommentsField, drpln.getCell(6, 4).getContents());		//	Enter Some Texts
				switchBackFromFrame();
			
				click(adTxtParagrphPpSaveBtn);
				thread();
			
				getObjectText(msgNotificationBar);
				verifyAssertEquals(drpln.getCell(8, 2).getContents(), getActualObjectTxt);		//	Text/Paragraph added successfully 
			
				webElement(tmsec2ndTxt); 
				scrollInnerScrollBar(webelementFrame);		//	Scrolling Section Grid Inner Scroll Bar	
			
				getObjectText(tmsec2ndTxt);
				verifyAssertEquals(drpln.getCell(6, 4).getContents(), getActualObjectTxt);		//	Verify Entered Text In Section Page
				thread();
			
				//		Mouse Hover - Move Last
				webElement(tmsecmHovrOnFirstOptn);
				mouseHoverOn(webelementFrame);
				click(tmsecmoveLastIcon);				
				getObjectText(tmsec2ndTxt); 
				verifyAssertEquals(drpln.getCell(5, 4).getContents(), getActualObjectTxt);
				thread();
			
				//	Mouse Hover - Move Top
				webElement(tmsecmHovrOnSecOptn);
				mouseHoverOn(webelementFrame);
				thread();
				webElement(tmsecmoveTopIcon);
				mouseHoverOn(webelementFrame);
				click(tmsecmoveTopIcon);				
				getObjectText(tmsecfirstText);
				verifyAssertEquals(drpln.getCell(6, 4).getContents(), getActualObjectTxt);	
				thread();
			
				//	Mouse Hover - Move Down
				webElement(tmsecmHovrOnFirstOptn);
				mouseHoverOn(webelementFrame);
				click(tmsecmoveDownIcon);				
				getObjectText(tmsec2ndTxt1);
				verifyAssertEquals(drpln.getCell(6, 4).getContents(), getActualObjectTxt);
				thread();
			
				//	Mouse Hover - Move Up
				webElement(tmsecmHovrOnSecOptn);
				mouseHoverOn(webelementFrame);
				click(tmsecmoveUpIcon);
				getObjectText(tmsecfirstText);
				verifyAssertEquals(drpln.getCell(6, 4).getContents(), getActualObjectTxt);
				thread();
			
				//	Mouse Hover - Delete
				webElement(tmsecmHovrOnThirdOptn);
				mouseHoverOn(webelementFrame);
				click(tmsecremoveIcon);
			
				verifyAssert(txtDeleteConfPopup);
				getObjectText(deleteConfPpMessage);
				verifyAssertEquals(drpln.getCell(8, 6).getContents(), getActualObjectTxt);
				click(delConfOkBtn);
				thread();	
			}
			}catch(WebDriverException e1) {
				System.out.println("Display fields are not displaying");
				e1.printStackTrace();
			}
			thread();
		}
	
	@Test(priority=7)
	void addAssetsSection() throws InterruptedException, BiffException, IOException
	{
		getDRPlanSheetFromExcel();
		 
		click(drEdtSecCreateNewSectionBtn);
		verifyAssert(createNewSectionPopup);
		
		//	Section Title
		click(secPpSectionTitleDDArrow);
		String asecSectionTitle = drpln.getCell(1, 5).getContents();		//	Add New Section Name (or) Choose Existing Section Name
		enterText(secPpSectionTitleDDSearchBox, asecSectionTitle);
		
		try
		{
			verifyObjDisplay(clickToAddTxtInDD);
			if(chkObjDisplay == true)
			{
				click(clickToAddTxtInDD);
				enterKeyInKyBord(secPpSectionTitleDDSearchBox);
				verifyAssert(sectionAddConfPopup);
				getObjectText(deleteConfPpMessage); 
				verifyAssertEquals("Do you want to add "+drpln.getCell(1, 5).getContents()+" into Section Title ?", getActualObjectTxt);
				click(delConfOkBtn);
				thread();
				
				getObjectText(msgNotificationBar);
				verifyAssertEquals(drpln.getCell(1, 5).getContents()+" added into Section Title successfully", getActualObjectTxt);		//	Check New Section Added Successfully
				thread();
				
				getObjectText(secPpSectionTitleDefTxt);
				verifyAssertEquals(drpln.getCell(1, 5).getContents(), getActualObjectTxt);		//	Verify Added Title In Drop Down
			}
		}catch(WebDriverException e){
			arrowDown(secPpSectionTitleDDSearchBox); 
			enterKeyInKyBord(secPpSectionTitleDDSearchBox);
		}
		
		//	Authorized By
		click(secPpAuthorizedByDDArrow);
		getTotalValuesIndd(secPpAuthorizedByDDCnt);
		Random authBy = new Random();
		int rauthBy = authBy.nextInt(totalDDValCount-2)+2;
		String asecAuthorizedBy = driver.findElement(By.id("ddlDSAuthorizedBy_chzn_o_"+rauthBy)).getText();
		enterText(secPpAuthorizedByDDSearchBox, asecAuthorizedBy.split(",")[0]);
		enterKeyInKyBord(secPpAuthorizedByDDSearchBox);
		
		//	Verified By
		click(secPpVerifiedByDDArrow);
		getTotalValuesIndd(secPpVerifiedByDDCnt);
		Random veriBy = new Random();
		int rveriBy = veriBy.nextInt(totalDDValCount-2)+2;
		String asecVerifiedBy = driver.findElement(By.id("ddlDSVerifiedBy_chzn_o_"+rveriBy)).getText();
		enterText(secPpVerifiedByDDSearchBox, asecVerifiedBy.split(",")[0]);
		enterKeyInKyBord(secPpVerifiedByDDSearchBox);
		
		//	Description
		webElement(crSectionDescription);
		switchToWEFrame(webelementFrame);
		enterText(drscEdtrCommentsField, drpln.getCell(2, 5).getContents());
		switchBackFromFrame();
		takeScreenshot();
		
		click(secPpSaveBtn);
		thread();
			
		getObjectText(msgNotificationBar);
		verifyAssertEquals(drpln.getCell(8, 7).getContents(), getActualObjectTxt);		//	Section Created Successfully	
		
		getObjectText(asecDescription);
		verifyAssertEquals(drpln.getCell(2, 5).getContents(), getActualObjectTxt);		//	Check Added Section Description
		
		click(asecDescription);
		thread();
		
		//		Edit Description		//
		verifyAssert(createNewSectionPopup);
		getObjectText(secPpCreateNewSectionTtl);
		verifyAssertEquals(drpln.getCell(9, 3).getContents(), getActualObjectTxt);		//	Check Update Description Title
		
		webElement(crSectionDescription);
		switchToWEFrame(webelementFrame);
		useKeyFuncToRmvVal(drscEdtrCommentsField);
		enterText(drscEdtrCommentsField, drpln.getCell(3, 5).getContents());
		switchBackFromFrame();
		
		click(secPpSaveBtn);
		thread();
		
		getObjectText(msgNotificationBar);
		verifyAssertEquals(drpln.getCell(8, 1).getContents(), getActualObjectTxt);		//	Section Updated Successfully
		thread();	
		
		getObjectText(asecDescription);
		verifyAssertEquals(drpln.getCell(3, 5).getContents(), getActualObjectTxt);		//	Verify Edited Description Details
		
		
		//		Authorized & Verified By Person Names		//
		getObjectText1(lsecLblAuthorizedBy);
		getObjectText(asecAuthorizedByNm);
		verifyAssertEquals("Authorized By : "+asecAuthorizedBy, getActualObjectTxt1+" "+getActualObjectTxt);	//	Check Authorized Person Name
		
		getObjectText1(lsecLblVerifiedBy);
		getObjectText(asecVerifiedByNm);
		verifyAssertEquals("Verified By : "+asecVerifiedBy, getActualObjectTxt1+" "+getActualObjectTxt);	//	Check Verified Person Name
		thread();
		
		
		//		Add Text/Paragraph		//
		click(asecAddTextBtn);
		
		verifyAssert(adTxtAddTextParagraphPopup);
		getObjectText(adTxtAddTextParagraphPpTtl);
		verifyAssertEquals(drpln.getCell(9, 4).getContents(), getActualObjectTxt);		//	Add Text/Paragraph Pop Up Title
			
		getObjectText(adTxtPpEnterSomeTxtLbl);
		verifyAssertEquals(drpln.getCell(10, 1).getContents(), getActualObjectTxt);	//	Enter Some Text Label Name
			
		webElement(adTxtEnterSomeTextFld);
		switchToWEFrame(webelementFrame);
		enterText(scEdtrCommentsField, drpln.getCell(4, 5).getContents());		//	Enter Some Texts
		switchBackFromFrame();
		
		click(adTxtParagrphPpSaveBtn);
		thread();
		
		getObjectText(msgNotificationBar);
		verifyAssertEquals(drpln.getCell(8, 2).getContents(), getActualObjectTxt);		//	Text/Paragraph added successfully 
		
		getObjectText(asecfirstText);
		verifyAssertEquals(drpln.getCell(4, 5).getContents(), getActualObjectTxt);		//	Verify Entered Text In Section Page
		
		click(asecfirstText);
		thread();
		
		//		Edit Text		//
		getObjectText(adTxtAddTextParagraphPpTtl);
		verifyAssertEquals(drpln.getCell(9, 5).getContents(), getActualObjectTxt);		//	Update Text/Paragraph Title
			
		webElement(adTxtEnterSomeTextFld);
		switchToWEFrame(webelementFrame);
		useKeyFuncToRmvVal(scEdtrCommentsField);	//	Edit Some Texts
		enterText(scEdtrCommentsField, drpln.getCell(5, 5).getContents());
		switchBackFromFrame();
			
		click(adTxtParagrphPpSaveBtn);
		thread();
			
		getObjectText(msgNotificationBar);
		verifyAssertEquals(drpln.getCell(8, 3).getContents(), getActualObjectTxt);		//	Text/Paragraph Updated Successfully
			
		getObjectText(asecfirstText);
		verifyAssertEquals(drpln.getCell(5, 5).getContents(), getActualObjectTxt);		//	Verify Modified Text
		
		
		//		Add Dynamic Data		//
		click(asecAddDataBtn);
		thread();
		
		verifyAssert(addDynamicDataPopup);
			
		click(dynDtaPpModuleDDArrow);
		enterText(dynDtaPpModuleDDSearchBox, drpln.getCell(11, 5).getContents());		//	Choose Assets module
		enterKeyInKyBord(dynDtaPpModuleDDSearchBox);
		thread();
		
		try
		{
			verifyObjDisplay(dynDtaEmpSelectAllChkBox);
			if(chkObjDisplay == true)
			{
				//	Enable Select All Check Box
				click(dynDtaEmpSelectAllChkBox);						
				getTotalValuesIndd(dynDtaEmpDisplayFldCnt);
				try
				{
					for(int i=1; i<=totalDDValCount; i++)
					{
						boolean checked = driver.findElement(By.xpath("//div[@id='divDDataFields']/span["+i+"]/input")).isSelected();
						Assert.assertEquals(true, checked);
					}
						
					//	Disable Select All Check Box
					click(dynDtaEmpSelectAllChkBox);
					getTotalValuesIndd(dynDtaEmpDisplayFldCnt);
					for(int j=1; j<=totalDDValCount; j++)
					{
						boolean unchecked = driver.findElement(By.xpath("//div[@id='divDDataFields']/span["+j+"]/input")).isSelected();
						Assert.assertEquals(false, unchecked);
					}
				}catch(WebDriverException e3) {
						e3.printStackTrace();
				}
					
				click(asecdfAssetName);
				click(asecdfManufacturerName);
				click(asecdfQuantity);
				click(asecdfReplacementValue);
									
				click(dynDtaAddNewFilterBtn);	//	Add New Filter button
					
				click(dynDtaFieldDDArrow);
				enterText(dynDtaFieldDDSearchBox, "Asset Name");		//	Choose Filter Field
				enterKeyInKyBord(dynDtaFieldDDSearchBox);
				thread();
				
				//		Choose Filter Value
				click(dynDtaFieldValueDD);
				getTotalValuesIndd(dynDtaFieldValueCnt);
				
				ArrayList<Integer> alist = new ArrayList<Integer>();
				for(int i=1; i<totalDDValCount; i++)
				{
					alist.add(i);
				}
				Collections.shuffle(alist);
				
				for(int i=0; i<2; i++)
				{
					String randomValue = driver.findElement(By.id("ddlDDataNewFilterValues_chzn_o_"+alist.get(i))).getText();
					enterText(dynDtaFieldValueDD, randomValue);
					enterKeyInKyBord(dynDtaFieldValueDD);
					click(dynDtaFilterAddBtn);
					
					//		Check the same filter value exists
					try
					{							
						isAlertPresent();
						getAlertMsgText();
						verifyAssertEquals(filterValueExistsMsg, getActlAlertTxt);
						acceptAlertMessage();
						break;
					}catch(NoAlertPresentException noalrt) {
						
					}
				}		
					
				click(dynDtaPpSaveBtn);
				thread();
				
				getObjectText(msgNotificationBar);
				verifyAssertEquals(drpln.getCell(8, 4).getContents(), getActualObjectTxt);		//	Data Created Successfully
				thread();
				
				//		Check Selected Display Order in Table
				for(int i=1; i<5; i++)
				{
					String getColumnName = driver.findElement(By.xpath("//div[@id='divDynamicDRPlanSections']/div[5]//table[@class='table table-responsive table-striped table-bordered bootstrap-datatable dynamic-data-table']//tr/td["+i+"]")).getText();
					if(i == 1)
						verifyAssertEquals(drpln.getCell(12, 15).getContents(), getColumnName);	//	Asset Name
					if(i == 2)
						verifyAssertEquals(drpln.getCell(12, 16).getContents(), getColumnName);	//	Manufacturer Name
					if(i == 3)
						verifyAssertEquals(drpln.getCell(12, 17).getContents(), getColumnName);	//	Quantity
					if(i == 4)
						verifyAssertEquals(drpln.getCell(12, 18).getContents(), getColumnName);	//	Replacement Value
				}
				
				click(asecTeamsList);
				thread();
				
				verifyAssert(addDynamicDataPopup);
				
				for(int i=2; i<4; i++)
				{
					boolean checked = driver.findElement(By.xpath("//div[@id='divDDataFields']/span["+i+"]/input")).isSelected();
					Assert.assertEquals(true, checked);
				}
						
				//	Drag & Drop the objects
				sourceElement(dynDtaEmpWeFirstName);
				destinationElement(dynDtaEmpWeLastName);
				dragAndDrop(sourceObj, destinationObj);		//	Change Asset & Manufacturer Name position				
				
				sourceElement(dynDtaEmpWeMobile);
				destinationElement(dynDtaEmpWeAlternateEmployee);
				dragAndDrop(sourceObj, destinationObj);		//	Change Quantity & Replacement Value position
				
				//	Verify Added Filter Values
				try
				{
					verifyObjDisplay(dynDtaEmpSelectedFilterValue);
					if(chkObjDisplay == true)
					{
						verifyAssert(dynDtaEmpFilterValue1);
						verifyAssert(dynDtaEmpFilterValue2);
					}
				}catch(WebDriverException e) {
					System.out.println("Selected Filter values are not displaying in Edit screen");
					e.printStackTrace();
				}
				
				click(dynDtaPpSaveBtn);
				thread();
							
				getObjectText(msgNotificationBar);
				verifyAssertEquals(drpln.getCell(8, 5).getContents(), getActualObjectTxt);		//	Data Updated Successfully
				thread();
												
				//		Verify Column Name Order Changes In Table
				for(int i=1; i<5; i++)
				{
					String getColumnName = driver.findElement(By.xpath("//div[@id='divDynamicDRPlanSections']/div[5]//table[@class='table table-responsive table-striped table-bordered bootstrap-datatable dynamic-data-table']//tr/td["+i+"]")).getText();
					if(i == 1)
						verifyAssertEquals(drpln.getCell(12, 16).getContents(), getColumnName);	//	Manufacturer Name
					if(i == 2)
						verifyAssertEquals(drpln.getCell(12, 15).getContents(), getColumnName);	//	Asset Name
					if(i == 3)
						verifyAssertEquals(drpln.getCell(12, 18).getContents(), getColumnName);	//	Replacement Value
					if(i == 4)
						verifyAssertEquals(drpln.getCell(12, 17).getContents(), getColumnName);	//	Quantity
				}				
							
				//		Edit Section		//
				click(asecEditSectionBtn);
				thread();
			
				verifyAssert(createNewSectionPopup);
				getObjectText(secPpCreateNewSectionTtl);
				verifyAssertEquals(drpln.getCell(9, 2).getContents(), getActualObjectTxt);		//	Update Section Pop up Title
			
				getObjectText(secPpSectionTitleDefTxt);
				verifyAssertEquals(asecSectionTitle, getActualObjectTxt);		//	Verify Selected Section Title Name 	
			
				getObjectText(secPpAuthorizedByDefTxt);
				verifyAssertEquals(asecAuthorizedBy, getActualObjectTxt);		//	Check Selected Authorized By Name
			
				getObjectText(secPpVerifiedByDefTxt);
				verifyAssertEquals(asecVerifiedBy, getActualObjectTxt);		//	Check Select Verified By Name
			
				//		Section Title
				//click(secPpSectionTitleDDArrow);
				//getTotalValuesIndd(secPpSectionTitleDDCnt);
				//Random edtSecTitle = new Random();
				//int edtrSecTitle = edtSecTitle.nextInt(totalDDValCount-2)+2;
				//String edtSectionTitle = driver.findElement(By.id("ddlNewDynamicSectionTitle_chzn_o_"+edtrSecTitle)).getText();
				//enterText(secPpSectionTitleDDSearchBox, edtSectionTitle);
				//enterKeyInKyBord(secPpSectionTitleDDSearchBox);
			
				//	Authorized By
				click(secPpAuthorizedByDDArrow);
				getTotalValuesIndd(secPpAuthorizedByDDCnt);
				Random edtAuthBy = new Random();
				int edtrAuthBy = edtAuthBy.nextInt(totalDDValCount-2)+2;
				String edtAuthorizedBy = driver.findElement(By.id("ddlDSAuthorizedBy_chzn_o_"+edtrAuthBy)).getText();
				enterText(secPpAuthorizedByDDSearchBox, edtAuthorizedBy.split(",")[0]);
				enterKeyInKyBord(secPpAuthorizedByDDSearchBox);
			
				//	Verified By
				click(secPpVerifiedByDDArrow);
				getTotalValuesIndd(secPpVerifiedByDDCnt);
				Random edtVeriBy = new Random();
				int edtrVeriBy = edtVeriBy.nextInt(totalDDValCount-2)+2;
				String edtVerifiedBy = driver.findElement(By.id("ddlDSVerifiedBy_chzn_o_"+edtrVeriBy)).getText();
				enterText(secPpVerifiedByDDSearchBox, edtVerifiedBy.split(",")[0]);
				enterKeyInKyBord(secPpVerifiedByDDSearchBox);
				
				click(secPpSaveBtn);
				thread();
				
				//try
				//{
				//	click(secPpSaveBtn);
				//	thread();
				
				//	getObjectText(msgNotificationBar);
				//	verifyAssertEquals(sectionUpdatedSuccessfullyMsg, getActualObjectTxt);		//	Section Updated Successfully
				//}catch(WebDriverException e) {
				//	e.printStackTrace();
				
				//	click(secPpSaveBtn);
					//thread();
				
					//getObjectText(msgNotificationBar);
					//verifyAssertEquals(lsecSectionTitle+sectionTitileNmAlreadyExistsMsg, getActualObjectTxt);		//	Section Title Already Exists
				
					//click(secPpSectionTitleDDArrow);
					//getTotalValuesIndd(secPpSectionTitleDDCnt);
					//Random edtSecTitle1 = new Random();
					//int edtrSecTitle1 = edtSecTitle1.nextInt(totalDDValCount-0)+0;
					//String edtSectionTitle1 = driver.findElement(By.id("ddlNewDynamicSectionTitle_chzn_o_"+edtrSecTitle1)).getText();
					//enterText(secPpSectionTitleDDSearchBox, edtSectionTitle1);
					//enterKeyInKyBord(secPpSectionTitleDDSearchBox);
				
					//click(secPpSaveBtn);
					//thread();
				//}
			
				//		Verify Edited Authorized & Verified By Name
				getObjectText1(drEdtSecAuthorizedByLblNm);
				getObjectText(asecAuthorizedByNm);
				verifyAssertEquals("Authorized By : "+edtAuthorizedBy, getActualObjectTxt1+" "+getActualObjectTxt);	//	Check Authorized Person Name
			
				getObjectText1(drEdtSecVerifiedByLblNm);
				getObjectText(asecVerifiedByNm);
				verifyAssertEquals("Verified By : "+edtVerifiedBy, getActualObjectTxt1+" "+getActualObjectTxt);	//	Check Verified Person Name
				thread();
			
				//		Add Text - 2nd Text		//
				click(asecAddTextBtn);
			
				verifyAssert(adTxtAddTextParagraphPopup);
				getObjectText(adTxtAddTextParagraphPpTtl);
				verifyAssertEquals(drpln.getCell(9, 4).getContents(), getActualObjectTxt);		//	Add Text/Paragraph Pop Up Title
				
				getObjectText(adTxtPpEnterSomeTxtLbl);
				verifyAssertEquals(drpln.getCell(10, 1).getContents(), getActualObjectTxt);		//	Enter Some Text Label Name
				
				webElement(adTxtEnterSomeTextFld);
				switchToWEFrame(webelementFrame);
				enterText(scEdtrCommentsField, drpln.getCell(6, 5).getContents());		//	Enter Some Texts
				switchBackFromFrame();
			
				click(adTxtParagrphPpSaveBtn);
				thread();
			
				getObjectText(msgNotificationBar);
				verifyAssertEquals(drpln.getCell(8, 2).getContents(), getActualObjectTxt);		//	Text/Paragraph added successfully 
			
				webElement(asec2ndTxt); 
				scrollInnerScrollBar(webelementFrame);		//	Scrolling Section Grid Inner Scroll Bar	
			
				getObjectText(asec2ndTxt);
				verifyAssertEquals(drpln.getCell(6, 5).getContents(), getActualObjectTxt);		//	Verify Entered Text In Section Page
				thread();
			
				//		Mouse Hover - Move Last
				webElement(asecmHovrOnFirstOptn);
				mouseHoverOn(webelementFrame);
				click(asecmoveLastIcon);				
				getObjectText(asec2ndTxt); 
				verifyAssertEquals(drpln.getCell(5, 5).getContents(), getActualObjectTxt);
				thread();
			
				//	Mouse Hover - Move Top
				webElement(asecmHovrOnSecOptn);
				mouseHoverOn(webelementFrame);
				thread();
				webElement(asecmoveTopIcon);
				mouseHoverOn(webelementFrame);
				click(asecmoveTopIcon);				
				getObjectText(asecfirstText);
				verifyAssertEquals(drpln.getCell(6, 5).getContents(), getActualObjectTxt);	
				thread();
			
				//	Mouse Hover - Move Down
				webElement(asecmHovrOnFirstOptn);
				mouseHoverOn(webelementFrame);
				click(asecmoveDownIcon);				
				getObjectText(asec2ndTxt1);
				verifyAssertEquals(drpln.getCell(6, 5).getContents(), getActualObjectTxt);
				thread();
			
				//	Mouse Hover - Move Up
				webElement(asecmHovrOnSecOptn);
				mouseHoverOn(webelementFrame);
				click(asecmoveUpIcon);
				getObjectText(asecfirstText);
				verifyAssertEquals(drpln.getCell(6, 5).getContents(), getActualObjectTxt);
				thread();
			
				//	Mouse Hover - Delete
				webElement(asecmHovrOnThirdOptn);
				mouseHoverOn(webelementFrame);
				click(asecremoveIcon);
			
				verifyAssert(txtDeleteConfPopup);
				getObjectText(deleteConfPpMessage);
				verifyAssertEquals(drpln.getCell(8, 6).getContents(), getActualObjectTxt);
				click(delConfOkBtn);
				thread();	
			}
			}catch(WebDriverException e1) {
				System.out.println("Display fields are not displaying");
				e1.printStackTrace();
			}
			thread();
		}*/
	
	/*@Test(priority=8)
	void addAssetGroupsSection() throws InterruptedException, BiffException, IOException
	{
		getDRPlanSheetFromExcel();
		 
		click(drEdtSecCreateNewSectionBtn);
		verifyAssert(createNewSectionPopup);
		
		//	Section Title
		click(secPpSectionTitleDDArrow);
		String agsecSectionTitle = drpln.getCell(1, 6).getContents();		//	Add New Section Name (or) Choose Existing Section Name
		enterText(secPpSectionTitleDDSearchBox, agsecSectionTitle);
		
		try
		{
			verifyObjDisplay(clickToAddTxtInDD);
			if(chkObjDisplay == true)
			{
				click(clickToAddTxtInDD);
				enterKeyInKyBord(secPpSectionTitleDDSearchBox);
				verifyAssert(sectionAddConfPopup);
				getObjectText(deleteConfPpMessage); 
				verifyAssertEquals("Do you want to add "+drpln.getCell(1, 6).getContents()+" into Section Title ?", getActualObjectTxt);
				click(delConfOkBtn);
				thread();
				
				getObjectText(msgNotificationBar);
				verifyAssertEquals(drpln.getCell(1, 6).getContents()+" added into Section Title successfully", getActualObjectTxt);		//	Check New Section Added Successfully
				thread();
				
				getObjectText(secPpSectionTitleDefTxt);
				verifyAssertEquals(drpln.getCell(1, 6).getContents(), getActualObjectTxt);		//	Verify Added Title In Drop Down
			}
		}catch(WebDriverException e){
			arrowDown(secPpSectionTitleDDSearchBox);
			enterKeyInKyBord(secPpSectionTitleDDSearchBox);
		}
		
		//	Authorized By
		click(secPpAuthorizedByDDArrow);
		getTotalValuesIndd(secPpAuthorizedByDDCnt);
		Random authBy = new Random();
		int rauthBy = authBy.nextInt(totalDDValCount-2)+2;
		String agsecAuthorizedBy = driver.findElement(By.id("ddlDSAuthorizedBy_chzn_o_"+rauthBy)).getText();
		enterText(secPpAuthorizedByDDSearchBox, agsecAuthorizedBy.split(",")[0]);
		enterKeyInKyBord(secPpAuthorizedByDDSearchBox);
		
		//	Verified By
		click(secPpVerifiedByDDArrow);
		getTotalValuesIndd(secPpVerifiedByDDCnt);
		Random veriBy = new Random();
		int rveriBy = veriBy.nextInt(totalDDValCount-2)+2;
		String agsecVerifiedBy = driver.findElement(By.id("ddlDSVerifiedBy_chzn_o_"+rveriBy)).getText();
		enterText(secPpVerifiedByDDSearchBox, agsecVerifiedBy.split(",")[0]);
		enterKeyInKyBord(secPpVerifiedByDDSearchBox);
		
		//	Description
		webElement(crSectionDescription);
		switchToWEFrame(webelementFrame);
		enterText(drscEdtrCommentsField, drpln.getCell(2, 6).getContents());
		switchBackFromFrame();
		takeScreenshot();
		
		click(secPpSaveBtn);
		thread();
			
		getObjectText(msgNotificationBar);
		verifyAssertEquals(drpln.getCell(8, 7).getContents(), getActualObjectTxt);		//	Section Created Successfully	
		
		getObjectText(agsecDescription);
		verifyAssertEquals(drpln.getCell(2, 6).getContents(), getActualObjectTxt);		//	Check Added Section Description
		
		click(agsecDescription);
		thread();
		
		//		Edit Description		//
		verifyAssert(createNewSectionPopup);
		getObjectText(secPpCreateNewSectionTtl);
		verifyAssertEquals(drpln.getCell(9, 3).getContents(), getActualObjectTxt);		//	Check Update Description Title
		
		webElement(crSectionDescription);
		switchToWEFrame(webelementFrame);
		useKeyFuncToRmvVal(drscEdtrCommentsField);
		enterText(drscEdtrCommentsField, drpln.getCell(3, 6).getContents());
		switchBackFromFrame();
		
		click(secPpSaveBtn);
		thread();
		
		getObjectText(msgNotificationBar);
		verifyAssertEquals(drpln.getCell(8, 1).getContents(), getActualObjectTxt);		//	Section Updated Successfully
		thread();	
		
		getObjectText(agsecDescription);
		verifyAssertEquals(drpln.getCell(3, 6).getContents(), getActualObjectTxt);		//	Verify Edited Description Details
		
		
		//		Authorized & Verified By Person Names		//
		getObjectText1(lsecLblAuthorizedBy);
		getObjectText(agsecAuthorizedByNm);
		verifyAssertEquals("Authorized By : "+agsecAuthorizedBy, getActualObjectTxt1+" "+getActualObjectTxt);	//	Check Authorized Person Name
		
		getObjectText1(lsecLblVerifiedBy);
		getObjectText(agsecVerifiedByNm);
		verifyAssertEquals("Verified By : "+agsecVerifiedBy, getActualObjectTxt1+" "+getActualObjectTxt);		//	Check Verified Person Name
		thread();
		
		
		//		Add Text/Paragraph		//
		click(agsecAddTextBtn);
		
		verifyAssert(adTxtAddTextParagraphPopup);
		getObjectText(adTxtAddTextParagraphPpTtl);
		verifyAssertEquals(drpln.getCell(9, 4).getContents(), getActualObjectTxt);		//	Add Text/Paragraph Pop Up Title
			
		getObjectText(adTxtPpEnterSomeTxtLbl);
		verifyAssertEquals(drpln.getCell(10, 1).getContents(), getActualObjectTxt);	//	Enter Some Text Label Name
			
		webElement(adTxtEnterSomeTextFld);
		switchToWEFrame(webelementFrame);
		enterText(scEdtrCommentsField, drpln.getCell(4, 6).getContents());		//	Enter Some Texts
		switchBackFromFrame();
		
		click(adTxtParagrphPpSaveBtn);
		thread();
		
		getObjectText(msgNotificationBar);
		verifyAssertEquals(drpln.getCell(8, 2).getContents(), getActualObjectTxt);		//	Text/Paragraph added successfully 
		
		getObjectText(agsecfirstText);
		verifyAssertEquals(drpln.getCell(4, 6).getContents(), getActualObjectTxt);		//	Verify Entered Text In Section Page
		
		click(agsecfirstText);
		thread();
		
		//		Edit Text		//
		getObjectText(adTxtAddTextParagraphPpTtl);
		verifyAssertEquals(drpln.getCell(9, 5).getContents(), getActualObjectTxt);		//	Update Text/Paragraph Title
			
		webElement(adTxtEnterSomeTextFld);
		switchToWEFrame(webelementFrame);
		useKeyFuncToRmvVal(scEdtrCommentsField);	//	Edit Some Texts
		enterText(scEdtrCommentsField, drpln.getCell(5, 6).getContents());
		switchBackFromFrame();
			
		click(adTxtParagrphPpSaveBtn);
		thread();
			
		getObjectText(msgNotificationBar);
		verifyAssertEquals(drpln.getCell(8, 3).getContents(), getActualObjectTxt);		//	Text/Paragraph Updated Successfully
			
		getObjectText(agsecfirstText);
		verifyAssertEquals(drpln.getCell(5, 6).getContents(), getActualObjectTxt);		//	Verify Modified Text
		
		
		//		Add Dynamic Data		//
		click(agsecAddDataBtn);
		thread();
		
		verifyAssert(addDynamicDataPopup);
			
		click(dynDtaPpModuleDDArrow);
		enterText(dynDtaPpModuleDDSearchBox, drpln.getCell(11, 6).getContents());		//	Choose Assets module
		enterKeyInKyBord(dynDtaPpModuleDDSearchBox);
		thread();
		
		try
		{
			verifyObjDisplay(dynDtaEmpSelectAllChkBox);
			if(chkObjDisplay == true)
			{
				//	Enable Select All Check Box
				click(dynDtaEmpSelectAllChkBox);						
				getTotalValuesIndd(dynDtaEmpDisplayFldCnt);
				try
				{
					for(int i=1; i<=totalDDValCount; i++)
					{
						boolean checked = driver.findElement(By.xpath("//div[@id='divDDataFields']/span["+i+"]/input")).isSelected();
						Assert.assertEquals(true, checked);
					}
						
					//	Disable Select All Check Box
					click(dynDtaEmpSelectAllChkBox);
					getTotalValuesIndd(dynDtaEmpDisplayFldCnt);
					for(int j=1; j<=totalDDValCount; j++)
					{
						boolean unchecked = driver.findElement(By.xpath("//div[@id='divDDataFields']/span["+j+"]/input")).isSelected();
						Assert.assertEquals(false, unchecked);
					}
				}catch(WebDriverException e3) {
						e3.printStackTrace();
				}
					
				click(agsecdfGroupName);
				click(agsecdfAssetGroupId);
				click(agsecdfComments);
													
				click(dynDtaAddNewFilterBtn);	//	Add New Filter button
					
				click(dynDtaFieldDDArrow);
				enterText(dynDtaFieldDDSearchBox, "Group Name");		//	Choose Filter Field
				enterKeyInKyBord(dynDtaFieldDDSearchBox);
				thread();
				
				//		Choose Filter Value
				click(dynDtaFieldValueDD);
				getTotalValuesIndd(dynDtaFieldValueCnt);
				
				ArrayList<Integer> alist = new ArrayList<Integer>();
				for(int i=1; i<totalDDValCount; i++)
				{
					alist.add(i);
				}
				Collections.shuffle(alist);
				
				for(int i=0; i<2; i++)
				{
					String randomValue = driver.findElement(By.id("ddlDDataNewFilterValues_chzn_o_"+alist.get(i))).getText();
					enterText(dynDtaFieldValueDD, randomValue);
					enterKeyInKyBord(dynDtaFieldValueDD);
					click(dynDtaFilterAddBtn);
				}		
					
				click(dynDtaPpSaveBtn);
				thread();
				
				getObjectText(msgNotificationBar);
				verifyAssertEquals(drpln.getCell(8, 4).getContents(), getActualObjectTxt);		//	Data Created Successfully
				thread();
				
				//		Check Selected Display Order in Table
				for(int i=1; i<4; i++)
				{
					String getColumnName = driver.findElement(By.xpath("//div[@id='divDynamicDRPlanSections']/div[6]//table[@class='table table-responsive table-striped table-bordered bootstrap-datatable dynamic-data-table']//tr/td["+i+"]")).getText();
					if(i == 1)
						verifyAssertEquals(drpln.getCell(12, 19).getContents(), getColumnName);	//	Group Name
					if(i == 2)
						verifyAssertEquals(drpln.getCell(12, 20).getContents(), getColumnName);	//	Asset Group ID
					if(i == 3)
						verifyAssertEquals(drpln.getCell(12, 10).getContents(), getColumnName);	//	Comments
				}
				
				click(agsecTeamsList);
				thread();
				
				verifyAssert(addDynamicDataPopup);
				
				for(int i=2; i<4; i++)
				{
					boolean checked = driver.findElement(By.xpath("//div[@id='divDDataFields']/span["+i+"]/input")).isSelected();
					Assert.assertEquals(true, checked);
				}
						
				//	Drag & Drop the objects
				sourceElement(dynDtaEmpWeFirstName);
				destinationElement(dynDtaEmpWeLastName);
				dragAndDrop(sourceObj, destinationObj);		//	Change Group Name & Asset Group ID position				
				
				sourceElement(dynDtaEmpWeLastName);
				destinationElement(dynDtaEmpWeMobile);
				dragAndDrop(sourceObj, destinationObj);		//	Change Group Name & Comments position
				
				//	Verify Added Filter Values
				try
				{
					verifyObjDisplay(dynDtaEmpSelectedFilterValue);
					if(chkObjDisplay == true)
					{
						verifyAssert(dynDtaEmpFilterValue1);
						verifyAssert(dynDtaEmpFilterValue2);
					}
				}catch(WebDriverException e) {
					System.out.println("Selected Filter values are not displaying in Edit screen");
					e.printStackTrace();
				}
				
				click(dynDtaPpSaveBtn);
				thread();
							
				getObjectText(msgNotificationBar);
				verifyAssertEquals(drpln.getCell(8, 5).getContents(), getActualObjectTxt);		//	Data Updated Successfully
				thread();
												
				//		Verify Column Name Order Changes In Table
				for(int i=1; i<4; i++)
				{
					String getColumnName = driver.findElement(By.xpath("//div[@id='divDynamicDRPlanSections']/div[6]//table[@class='table table-responsive table-striped table-bordered bootstrap-datatable dynamic-data-table']//tr/td["+i+"]")).getText();
					if(i == 1)
						verifyAssertEquals(drpln.getCell(12, 20).getContents(), getColumnName);	//	Asset Group ID
					if(i == 2)
						verifyAssertEquals(drpln.getCell(12, 10).getContents(), getColumnName);	//	Comments
					if(i == 3)
						verifyAssertEquals(drpln.getCell(12, 19).getContents(), getColumnName);	//	Group Name
				}				
							
				//		Edit Section		//
				click(agsecEditSectionBtn);
				thread();
			
				verifyAssert(createNewSectionPopup);
				getObjectText(secPpCreateNewSectionTtl);
				verifyAssertEquals(drpln.getCell(9, 2).getContents(), getActualObjectTxt);		//	Update Section Pop up Title
			
				getObjectText(secPpSectionTitleDefTxt);
				verifyAssertEquals(agsecSectionTitle, getActualObjectTxt);		//	Verify Selected Section Title Name 	
			
				getObjectText(secPpAuthorizedByDefTxt);
				verifyAssertEquals(agsecAuthorizedBy, getActualObjectTxt);		//	Check Selected Authorized By Name
			
				getObjectText(secPpVerifiedByDefTxt);
				verifyAssertEquals(agsecVerifiedBy, getActualObjectTxt);		//	Check Select Verified By Name
			
				//		Section Title
				//click(secPpSectionTitleDDArrow);
				//getTotalValuesIndd(secPpSectionTitleDDCnt);
				//Random edtSecTitle = new Random();
				//int edtrSecTitle = edtSecTitle.nextInt(totalDDValCount-2)+2;
				//String edtSectionTitle = driver.findElement(By.id("ddlNewDynamicSectionTitle_chzn_o_"+edtrSecTitle)).getText();
				//enterText(secPpSectionTitleDDSearchBox, edtSectionTitle);
				//enterKeyInKyBord(secPpSectionTitleDDSearchBox);
			
				//	Authorized By
				click(secPpAuthorizedByDDArrow);
				getTotalValuesIndd(secPpAuthorizedByDDCnt);
				Random edtAuthBy = new Random();
				int edtrAuthBy = edtAuthBy.nextInt(totalDDValCount-2)+2;
				String edtAuthorizedBy = driver.findElement(By.id("ddlDSAuthorizedBy_chzn_o_"+edtrAuthBy)).getText();
				enterText(secPpAuthorizedByDDSearchBox, edtAuthorizedBy.split(",")[0]);
				enterKeyInKyBord(secPpAuthorizedByDDSearchBox);
			
				//	Verified By
				click(secPpVerifiedByDDArrow);
				getTotalValuesIndd(secPpVerifiedByDDCnt);
				Random edtVeriBy = new Random();
				int edtrVeriBy = edtVeriBy.nextInt(totalDDValCount-2)+2;
				String edtVerifiedBy = driver.findElement(By.id("ddlDSVerifiedBy_chzn_o_"+edtrVeriBy)).getText();
				enterText(secPpVerifiedByDDSearchBox, edtVerifiedBy.split(",")[0]);
				enterKeyInKyBord(secPpVerifiedByDDSearchBox);
				
				click(secPpSaveBtn);
				thread();
				
				//try
				//{
				//	click(secPpSaveBtn);
				//	thread();
				
				//	getObjectText(msgNotificationBar);
				//	verifyAssertEquals(sectionUpdatedSuccessfullyMsg, getActualObjectTxt);		//	Section Updated Successfully
				//}catch(WebDriverException e) {
				//	e.printStackTrace();
				
				//	click(secPpSaveBtn);
					//thread();
				
					//getObjectText(msgNotificationBar);
					//verifyAssertEquals(lsecSectionTitle+sectionTitileNmAlreadyExistsMsg, getActualObjectTxt);		//	Section Title Already Exists
				
					//click(secPpSectionTitleDDArrow);
					//getTotalValuesIndd(secPpSectionTitleDDCnt);
					//Random edtSecTitle1 = new Random();
					//int edtrSecTitle1 = edtSecTitle1.nextInt(totalDDValCount-0)+0;
					//String edtSectionTitle1 = driver.findElement(By.id("ddlNewDynamicSectionTitle_chzn_o_"+edtrSecTitle1)).getText();
					//enterText(secPpSectionTitleDDSearchBox, edtSectionTitle1);
					//enterKeyInKyBord(secPpSectionTitleDDSearchBox);
				
					//click(secPpSaveBtn);
					//thread();
				//}
			
				//		Verify Edited Authorized & Verified By Name
				getObjectText1(drEdtSecAuthorizedByLblNm);
				getObjectText(agsecAuthorizedByNm);
				verifyAssertEquals("Authorized By : "+edtAuthorizedBy, getActualObjectTxt1+" "+getActualObjectTxt);	//	Check Authorized Person Name
			
				getObjectText1(drEdtSecVerifiedByLblNm);
				getObjectText(agsecVerifiedByNm);
				verifyAssertEquals("Verified By : "+edtVerifiedBy, getActualObjectTxt1+" "+getActualObjectTxt);	//	Check Verified Person Name
				thread();
			
				//		Add Text - 2nd Text		//
				click(agsecAddTextBtn);
			
				verifyAssert(adTxtAddTextParagraphPopup);
				getObjectText(adTxtAddTextParagraphPpTtl);
				verifyAssertEquals(drpln.getCell(9, 4).getContents(), getActualObjectTxt);		//	Add Text/Paragraph Pop Up Title
				
				getObjectText(adTxtPpEnterSomeTxtLbl);
				verifyAssertEquals(drpln.getCell(10, 1).getContents(), getActualObjectTxt);		//	Enter Some Text Label Name
				
				webElement(adTxtEnterSomeTextFld);
				switchToWEFrame(webelementFrame);
				enterText(scEdtrCommentsField, drpln.getCell(6, 6).getContents());		//	Enter Some Texts
				switchBackFromFrame();
			
				click(adTxtParagrphPpSaveBtn);
				thread();
			
				getObjectText(msgNotificationBar);
				verifyAssertEquals(drpln.getCell(8, 2).getContents(), getActualObjectTxt);		//	Text/Paragraph added successfully 
			
				webElement(agsec2ndTxt); 
				scrollInnerScrollBar(webelementFrame);		//	Scrolling Section Grid Inner Scroll Bar	
			
				getObjectText(agsec2ndTxt);
				verifyAssertEquals(drpln.getCell(6, 6).getContents(), getActualObjectTxt);		//	Verify Entered Text In Section Page
				thread();
			
				//		Mouse Hover - Move Last
				webElement(agsecmHovrOnFirstOptn);
				mouseHoverOn(webelementFrame);
				click(agsecmoveLastIcon);				
				getObjectText(agsec2ndTxt); 
				verifyAssertEquals(drpln.getCell(5, 6).getContents(), getActualObjectTxt);
				thread();
			
				//	Mouse Hover - Move Top
				webElement(agsecmHovrOnSecOptn);
				mouseHoverOn(webelementFrame);
				thread();
				webElement(agsecmoveTopIcon);
				mouseHoverOn(webelementFrame);
				click(agsecmoveTopIcon);				
				getObjectText(agsecfirstText);
				verifyAssertEquals(drpln.getCell(6, 6).getContents(), getActualObjectTxt);	
				thread();
			
				//	Mouse Hover - Move Down
				webElement(agsecmHovrOnFirstOptn);
				mouseHoverOn(webelementFrame);
				click(agsecmoveDownIcon);				
				getObjectText(agsec2ndTxt1);
				verifyAssertEquals(drpln.getCell(6, 6).getContents(), getActualObjectTxt);
				thread();
			
				//	Mouse Hover - Move Up
				webElement(agsecmHovrOnSecOptn);
				mouseHoverOn(webelementFrame);
				click(agsecmoveUpIcon);
				getObjectText(agsecfirstText);
				verifyAssertEquals(drpln.getCell(6, 6).getContents(), getActualObjectTxt);
				thread();
			
				//	Mouse Hover - Delete
				webElement(agsecmHovrOnThirdOptn);
				mouseHoverOn(webelementFrame);
				click(agsecremoveIcon);
			
				verifyAssert(txtDeleteConfPopup);
				getObjectText(deleteConfPpMessage);
				verifyAssertEquals(drpln.getCell(8, 6).getContents(), getActualObjectTxt);
				click(delConfOkBtn);
				thread();	
			}
			}catch(WebDriverException e1) {
				System.out.println("Display fields are not displaying");
				e1.printStackTrace();
			}
			thread();
		}
	
	@Test(priority=9)
	void addContactsSection() throws InterruptedException, BiffException, IOException
	{
		getDRPlanSheetFromExcel();
		 
		click(drEdtSecCreateNewSectionBtn);
		verifyAssert(createNewSectionPopup);
		
		//	Section Title
		click(secPpSectionTitleDDArrow);
		String csecSectionTitle = drpln.getCell(1, 7).getContents();		//	Add New Section Name (or) Choose Existing Section Name
		enterText(secPpSectionTitleDDSearchBox, csecSectionTitle);
		
		try
		{
			verifyObjDisplay(clickToAddTxtInDD);
			if(chkObjDisplay == true)
			{
				click(clickToAddTxtInDD); 
				enterKeyInKyBord(secPpSectionTitleDDSearchBox);
				verifyAssert(sectionAddConfPopup);
				getObjectText(deleteConfPpMessage); 
				verifyAssertEquals("Do you want to add "+drpln.getCell(1, 7).getContents()+" into Section Title ?", getActualObjectTxt);
				click(delConfOkBtn);
				thread();
				
				getObjectText(msgNotificationBar);
				verifyAssertEquals(drpln.getCell(1, 7).getContents()+" added into Section Title successfully", getActualObjectTxt);		//	Check New Section Added Successfully
				thread();
				
				getObjectText(secPpSectionTitleDefTxt);
				verifyAssertEquals(drpln.getCell(1, 7).getContents(), getActualObjectTxt);		//	Verify Added Title In Drop Down
			}
		}catch(WebDriverException e){
			arrowDown(secPpSectionTitleDDSearchBox);
			enterKeyInKyBord(secPpSectionTitleDDSearchBox);
		}
		
		//	Authorized By
		click(secPpAuthorizedByDDArrow);
		getTotalValuesIndd(secPpAuthorizedByDDCnt);
		Random authBy = new Random();
		int rauthBy = authBy.nextInt(totalDDValCount-2)+2;
		String csecAuthorizedBy = driver.findElement(By.id("ddlDSAuthorizedBy_chzn_o_"+rauthBy)).getText();
		enterText(secPpAuthorizedByDDSearchBox, csecAuthorizedBy.split(",")[0]);
		enterKeyInKyBord(secPpAuthorizedByDDSearchBox);
		
		//	Verified By
		click(secPpVerifiedByDDArrow);
		getTotalValuesIndd(secPpVerifiedByDDCnt);
		Random veriBy = new Random();
		int rveriBy = veriBy.nextInt(totalDDValCount-2)+2;
		String csecVerifiedBy = driver.findElement(By.id("ddlDSVerifiedBy_chzn_o_"+rveriBy)).getText();
		enterText(secPpVerifiedByDDSearchBox, csecVerifiedBy.split(",")[0]);
		enterKeyInKyBord(secPpVerifiedByDDSearchBox);
		
		//	Description
		webElement(crSectionDescription);
		switchToWEFrame(webelementFrame);
		enterText(drscEdtrCommentsField, drpln.getCell(2, 7).getContents());
		switchBackFromFrame();
		takeScreenshot();
		
		click(secPpSaveBtn);
		thread();
			
		getObjectText(msgNotificationBar);
		verifyAssertEquals(drpln.getCell(8, 7).getContents(), getActualObjectTxt);		//	Section Created Successfully	
		
		getObjectText(csecDescription);
		verifyAssertEquals(drpln.getCell(2, 7).getContents(), getActualObjectTxt);		//	Check Added Section Description
		
		click(csecDescription);
		thread();
		
		//		Edit Description		//
		verifyAssert(createNewSectionPopup);
		getObjectText(secPpCreateNewSectionTtl);
		verifyAssertEquals(drpln.getCell(9, 3).getContents(), getActualObjectTxt);		//	Check Update Description Title
		
		webElement(crSectionDescription);
		switchToWEFrame(webelementFrame);
		useKeyFuncToRmvVal(drscEdtrCommentsField);
		enterText(drscEdtrCommentsField, drpln.getCell(3, 7).getContents());
		switchBackFromFrame();
		
		click(secPpSaveBtn);
		thread();
		
		getObjectText(msgNotificationBar);
		verifyAssertEquals(drpln.getCell(8, 1).getContents(), getActualObjectTxt);		//	Section Updated Successfully
		thread();	
		
		getObjectText(csecDescription);
		verifyAssertEquals(drpln.getCell(3, 7).getContents(), getActualObjectTxt);		//	Verify Edited Description Details
		
		
		//		Authorized & Verified By Person Names		//
		getObjectText1(lsecLblAuthorizedBy);
		getObjectText(csecAuthorizedByNm);
		verifyAssertEquals("Authorized By : "+csecAuthorizedBy, getActualObjectTxt1+" "+getActualObjectTxt);	//	Check Authorized Person Name
		
		getObjectText1(lsecLblVerifiedBy);
		getObjectText(csecVerifiedByNm);
		verifyAssertEquals("Verified By : "+csecVerifiedBy, getActualObjectTxt1+" "+getActualObjectTxt);	//	Check Verified Person Name
		thread();
		
		
		//		Add Text/Paragraph		//
		click(csecAddTextBtn);
		
		verifyAssert(adTxtAddTextParagraphPopup);
		getObjectText(adTxtAddTextParagraphPpTtl);
		verifyAssertEquals(drpln.getCell(9, 4).getContents(), getActualObjectTxt);		//	Add Text/Paragraph Pop Up Title
			
		getObjectText(adTxtPpEnterSomeTxtLbl);
		verifyAssertEquals(drpln.getCell(10, 1).getContents(), getActualObjectTxt);	//	Enter Some Text Label Name
			
		webElement(adTxtEnterSomeTextFld);
		switchToWEFrame(webelementFrame);
		enterText(scEdtrCommentsField, drpln.getCell(4, 7).getContents());		//	Enter Some Texts
		switchBackFromFrame();
		
		click(adTxtParagrphPpSaveBtn);
		thread();
		
		getObjectText(msgNotificationBar);
		verifyAssertEquals(drpln.getCell(8, 2).getContents(), getActualObjectTxt);		//	Text/Paragraph added successfully 
		
		getObjectText(csecfirstText);
		verifyAssertEquals(drpln.getCell(4, 7).getContents(), getActualObjectTxt);		//	Verify Entered Text In Section Page
		
		click(csecfirstText);
		thread();
		
		//		Edit Text		//
		getObjectText(adTxtAddTextParagraphPpTtl);
		verifyAssertEquals(drpln.getCell(9, 5).getContents(), getActualObjectTxt);		//	Update Text/Paragraph Title
			
		webElement(adTxtEnterSomeTextFld);
		switchToWEFrame(webelementFrame);
		useKeyFuncToRmvVal(scEdtrCommentsField);	//	Edit Some Texts
		enterText(scEdtrCommentsField, drpln.getCell(5, 7).getContents());
		switchBackFromFrame();
			
		click(adTxtParagrphPpSaveBtn);
		thread();
			
		getObjectText(msgNotificationBar);
		verifyAssertEquals(drpln.getCell(8, 3).getContents(), getActualObjectTxt);		//	Text/Paragraph Updated Successfully
			
		getObjectText(csecfirstText);
		verifyAssertEquals(drpln.getCell(5, 7).getContents(), getActualObjectTxt);		//	Verify Modified Text
		
		
		//		Add Dynamic Data		//
		click(csecAddDataBtn);
		thread();
		
		verifyAssert(addDynamicDataPopup);
			
		click(dynDtaPpModuleDDArrow);
		enterText(dynDtaPpModuleDDSearchBox, drpln.getCell(11, 7).getContents());		//	Choose Assets module
		enterKeyInKyBord(dynDtaPpModuleDDSearchBox);
		thread();
		
		try
		{
			verifyObjDisplay(dynDtaEmpSelectAllChkBox);
			if(chkObjDisplay == true)
			{
				//	Enable Select All Check Box
				click(dynDtaEmpSelectAllChkBox);						
				getTotalValuesIndd(dynDtaEmpDisplayFldCnt);
				try
				{
					for(int i=1; i<=totalDDValCount; i++)
					{
						boolean checked = driver.findElement(By.xpath("//div[@id='divDDataFields']/span["+i+"]/input")).isSelected();
						Assert.assertEquals(true, checked);
					}
						
					//	Disable Select All Check Box
					click(dynDtaEmpSelectAllChkBox);
					getTotalValuesIndd(dynDtaEmpDisplayFldCnt);
					for(int j=1; j<=totalDDValCount; j++)
					{
						boolean unchecked = driver.findElement(By.xpath("//div[@id='divDDataFields']/span["+j+"]/input")).isSelected();
						Assert.assertEquals(false, unchecked);
					}
				}catch(WebDriverException e3) {
						e3.printStackTrace();
				}
					
				click(csecdfCompanyName);
				click(csecdfCountry);
				click(csecdfCity);
				click(csecdfZipcode);
				click(csecdfpFirstName);
				click(csecdfpLastName);
				click(csecdfpBusinessEmail);
				click(csecdfpMobileNo);
									
				click(dynDtaAddNewFilterBtn);	//	Add New Filter button
					
				click(dynDtaFieldDDArrow);
				enterText(dynDtaFieldDDSearchBox, "Company Name");		//	Choose Filter Field
				enterKeyInKyBord(dynDtaFieldDDSearchBox);
				thread();
				
				//		Choose Filter Value
				click(dynDtaFieldValueDD);
				getTotalValuesIndd(dynDtaFieldValueCnt);
				
				ArrayList<Integer> alist = new ArrayList<Integer>();
				for(int i=1; i<totalDDValCount; i++)
				{
					alist.add(i);
				}
				Collections.shuffle(alist);
				
				for(int i=0; i<2; i++)
				{
					String randomValue = driver.findElement(By.id("ddlDDataNewFilterValues_chzn_o_"+alist.get(i))).getText();
					enterText(dynDtaFieldValueDD, randomValue);
					enterKeyInKyBord(dynDtaFieldValueDD);
					click(dynDtaFilterAddBtn);
				}		
					
				click(dynDtaPpSaveBtn);
				thread();
				
				getObjectText(msgNotificationBar);
				verifyAssertEquals(drpln.getCell(8, 4).getContents(), getActualObjectTxt);		//	Data Created Successfully
				thread();
				
				//		Check Selected Display Order in Table
				for(int i=1; i<9; i++)
				{
					String getColumnName = driver.findElement(By.xpath("//div[@id='divDynamicDRPlanSections']/div[7]//table[@class='table table-responsive table-striped table-bordered bootstrap-datatable dynamic-data-table']//tr/td["+i+"]")).getText();
					if(i == 1)
						verifyAssertEquals(drpln.getCell(12, 21).getContents(), getColumnName);	//	Company Name
					if(i == 2)
						verifyAssertEquals(drpln.getCell(12, 22).getContents(), getColumnName);	//	Country
					if(i == 3)
						verifyAssertEquals(drpln.getCell(12, 7).getContents(), getColumnName);	//	City
					if(i == 4)
						verifyAssertEquals(drpln.getCell(12, 8).getContents(), getColumnName);	//	Zip Code
					if(i == 5)
						verifyAssertEquals(drpln.getCell(12, 23).getContents(), getColumnName);	//	Primary First Name
					if(i == 6)
						verifyAssertEquals(drpln.getCell(12, 24).getContents(), getColumnName);	//	Primary Last Name
					if(i == 7)
						verifyAssertEquals(drpln.getCell(12, 25).getContents(), getColumnName);	//	Primary Business Email
					if(i == 8)
						verifyAssertEquals(drpln.getCell(12, 26).getContents(), getColumnName);	//	Primary Mobile No
				}
				
				click(csecTeamsList);
				thread();
				
				verifyAssert(addDynamicDataPopup);
				
				for(int i=2; i<4; i++)
				{
					boolean checked = driver.findElement(By.xpath("//div[@id='divDDataFields']/span["+i+"]/input")).isSelected();
					Assert.assertEquals(true, checked);
				}
						
				//	Drag & Drop the objects
				sourceElement(dynDtaEmpWeFirstName);
				destinationElement(dynDtaEmpWeLastName);
				dragAndDrop(sourceObj, destinationObj);		//	Change Company Name & Country position				
				
				sourceElement(dynDtaEmpWeMobile);
				destinationElement(dynDtaEmpWeAlternateEmployee);
				dragAndDrop(sourceObj, destinationObj);		//	Change City & Zip Code position
				
				sourceElement(dynDtaDisplayFldFifthWe);
				destinationElement(dynDtaDisplayFldSixthWe);
				dragAndDrop(sourceObj, destinationObj);		//	Change Primary First & Last Name position
				
				sourceElement(dynDtaDisplayFldSeventhWe);
				destinationElement(dynDtaDisplayFldEigthWe);
				dragAndDrop(sourceObj, destinationObj);		//	Change Primary Business Email & Mobile No position
				
				//	Verify Added Filter Values
				try
				{
					verifyObjDisplay(dynDtaEmpSelectedFilterValue);
					if(chkObjDisplay == true)
					{
						verifyAssert(dynDtaEmpFilterValue1);
						verifyAssert(dynDtaEmpFilterValue2);
					}
				}catch(WebDriverException e) {
					System.out.println("Selected Filter values are not displaying in Edit screen");
					e.printStackTrace();
				}
				
				click(dynDtaPpSaveBtn);
				thread();
							
				getObjectText(msgNotificationBar);
				verifyAssertEquals(drpln.getCell(8, 5).getContents(), getActualObjectTxt);		//	Data Updated Successfully
				thread();
												
				//		Verify Column Name Order Changes In Table
				for(int i=1; i<9; i++)
				{
					String getColumnName = driver.findElement(By.xpath("//div[@id='divDynamicDRPlanSections']/div[7]//table[@class='table table-responsive table-striped table-bordered bootstrap-datatable dynamic-data-table']//tr/td["+i+"]")).getText();
					if(i == 1)
						verifyAssertEquals(drpln.getCell(12, 22).getContents(), getColumnName);	//	Country
					if(i == 2)
						verifyAssertEquals(drpln.getCell(12, 21).getContents(), getColumnName);	//	Company Name
					if(i == 3)
						verifyAssertEquals(drpln.getCell(12, 8).getContents(), getColumnName);	//	Zip Code
					if(i == 4)
						verifyAssertEquals(drpln.getCell(12, 7).getContents(), getColumnName);	//	City
					if(i == 5)
						verifyAssertEquals(drpln.getCell(12, 24).getContents(), getColumnName);	//	Primary Last Name
					if(i == 6)
						verifyAssertEquals(drpln.getCell(12, 23).getContents(), getColumnName);	//	Primary First Name
					if(i == 7)
						verifyAssertEquals(drpln.getCell(12, 26).getContents(), getColumnName);	//	Primary Mobile No
					if(i == 8)
						verifyAssertEquals(drpln.getCell(12, 25).getContents(), getColumnName);	//	Primary Business Email
				}				
							
				//		Edit Section		//
				click(csecEditSectionBtn);
				thread();
			
				verifyAssert(createNewSectionPopup);
				getObjectText(secPpCreateNewSectionTtl);
				verifyAssertEquals(drpln.getCell(9, 2).getContents(), getActualObjectTxt);		//	Update Section Pop up Title
			
				getObjectText(secPpSectionTitleDefTxt);
				verifyAssertEquals(csecSectionTitle, getActualObjectTxt);		//	Verify Selected Section Title Name 	
			
				getObjectText(secPpAuthorizedByDefTxt);
				verifyAssertEquals(csecAuthorizedBy, getActualObjectTxt);		//	Check Selected Authorized By Name
			
				getObjectText(secPpVerifiedByDefTxt);
				verifyAssertEquals(csecVerifiedBy, getActualObjectTxt);		//	Check Select Verified By Name
			
				//		Section Title
				//click(secPpSectionTitleDDArrow);
				//getTotalValuesIndd(secPpSectionTitleDDCnt);
				//Random edtSecTitle = new Random();
				//int edtrSecTitle = edtSecTitle.nextInt(totalDDValCount-2)+2;
				//String edtSectionTitle = driver.findElement(By.id("ddlNewDynamicSectionTitle_chzn_o_"+edtrSecTitle)).getText();
				//enterText(secPpSectionTitleDDSearchBox, edtSectionTitle);
				//enterKeyInKyBord(secPpSectionTitleDDSearchBox);
			
				//	Authorized By
				click(secPpAuthorizedByDDArrow);
				getTotalValuesIndd(secPpAuthorizedByDDCnt);
				Random edtAuthBy = new Random();
				int edtrAuthBy = edtAuthBy.nextInt(totalDDValCount-2)+2;
				String edtAuthorizedBy = driver.findElement(By.id("ddlDSAuthorizedBy_chzn_o_"+edtrAuthBy)).getText();
				enterText(secPpAuthorizedByDDSearchBox, edtAuthorizedBy.split(",")[0]);
				enterKeyInKyBord(secPpAuthorizedByDDSearchBox);
			
				//	Verified By
				click(secPpVerifiedByDDArrow);
				getTotalValuesIndd(secPpVerifiedByDDCnt);
				Random edtVeriBy = new Random();
				int edtrVeriBy = edtVeriBy.nextInt(totalDDValCount-2)+2;
				String edtVerifiedBy = driver.findElement(By.id("ddlDSVerifiedBy_chzn_o_"+edtrVeriBy)).getText();
				enterText(secPpVerifiedByDDSearchBox, edtVerifiedBy.split(",")[0]);
				enterKeyInKyBord(secPpVerifiedByDDSearchBox);
				
				click(secPpSaveBtn);
				thread();
				
				//try
				//{
				//	click(secPpSaveBtn);
				//	thread();
				
				//	getObjectText(msgNotificationBar);
				//	verifyAssertEquals(sectionUpdatedSuccessfullyMsg, getActualObjectTxt);		//	Section Updated Successfully
				//}catch(WebDriverException e) {
				//	e.printStackTrace();
				
				//	click(secPpSaveBtn);
					//thread();
				
					//getObjectText(msgNotificationBar);
					//verifyAssertEquals(lsecSectionTitle+sectionTitileNmAlreadyExistsMsg, getActualObjectTxt);		//	Section Title Already Exists
				
					//click(secPpSectionTitleDDArrow);
					//getTotalValuesIndd(secPpSectionTitleDDCnt);
					//Random edtSecTitle1 = new Random();
					//int edtrSecTitle1 = edtSecTitle1.nextInt(totalDDValCount-0)+0;
					//String edtSectionTitle1 = driver.findElement(By.id("ddlNewDynamicSectionTitle_chzn_o_"+edtrSecTitle1)).getText();
					//enterText(secPpSectionTitleDDSearchBox, edtSectionTitle1);
					//enterKeyInKyBord(secPpSectionTitleDDSearchBox);
				
					//click(secPpSaveBtn);
					//thread();
				//}
			
				//		Verify Edited Authorized & Verified By Name
				getObjectText1(drEdtSecAuthorizedByLblNm);
				getObjectText(csecAuthorizedByNm);
				verifyAssertEquals("Authorized By : "+edtAuthorizedBy, getActualObjectTxt1+" "+getActualObjectTxt);	//	Check Authorized Person Name
			
				getObjectText1(drEdtSecVerifiedByLblNm);
				getObjectText(csecVerifiedByNm);
				verifyAssertEquals("Verified By : "+edtVerifiedBy, getActualObjectTxt1+" "+getActualObjectTxt);	//	Check Verified Person Name
				thread();
			
				//		Add Text - 2nd Text		//
				click(csecAddTextBtn);
			
				verifyAssert(adTxtAddTextParagraphPopup);
				getObjectText(adTxtAddTextParagraphPpTtl);
				verifyAssertEquals(drpln.getCell(9, 4).getContents(), getActualObjectTxt);		//	Add Text/Paragraph Pop Up Title
				
				getObjectText(adTxtPpEnterSomeTxtLbl);
				verifyAssertEquals(drpln.getCell(10, 1).getContents(), getActualObjectTxt);		//	Enter Some Text Label Name
				
				webElement(adTxtEnterSomeTextFld);
				switchToWEFrame(webelementFrame);
				enterText(scEdtrCommentsField, drpln.getCell(6, 7).getContents());		//	Enter Some Texts
				switchBackFromFrame();
			
				click(adTxtParagrphPpSaveBtn);
				thread();
			
				getObjectText(msgNotificationBar);
				verifyAssertEquals(drpln.getCell(8, 2).getContents(), getActualObjectTxt);		//	Text/Paragraph added successfully 
			
				webElement(csec2ndTxt); 
				scrollInnerScrollBar(webelementFrame);		//	Scrolling Section Grid Inner Scroll Bar	
			
				getObjectText(csec2ndTxt);
				verifyAssertEquals(drpln.getCell(6, 7).getContents(), getActualObjectTxt);		//	Verify Entered Text In Section Page
				thread();
			
				//		Mouse Hover - Move Last
				webElement(csecmHovrOnFirstOptn);
				mouseHoverOn(webelementFrame);
				click(csecmoveLastIcon);				
				getObjectText(csec2ndTxt); 
				verifyAssertEquals(drpln.getCell(5, 7).getContents(), getActualObjectTxt);
				thread();
			
				//	Mouse Hover - Move Top
				webElement(csecmHovrOnSecOptn);
				mouseHoverOn(webelementFrame);
				thread();
				webElement(csecmoveTopIcon);
				mouseHoverOn(webelementFrame);
				click(csecmoveTopIcon);				
				getObjectText(csecfirstText);
				verifyAssertEquals(drpln.getCell(6, 7).getContents(), getActualObjectTxt);	
				thread();
			
				//	Mouse Hover - Move Down
				webElement(csecmHovrOnFirstOptn);
				mouseHoverOn(webelementFrame);
				click(csecmoveDownIcon);				
				getObjectText(csec2ndTxt1);
				verifyAssertEquals(drpln.getCell(6, 7).getContents(), getActualObjectTxt);
				thread();
			
				//	Mouse Hover - Move Up
				webElement(csecmHovrOnSecOptn);
				mouseHoverOn(webelementFrame);
				click(csecmoveUpIcon);
				getObjectText(csecfirstText);
				verifyAssertEquals(drpln.getCell(6, 7).getContents(), getActualObjectTxt);
				thread();
			
				//	Mouse Hover - Delete
				webElement(csecmHovrOnThirdOptn);
				mouseHoverOn(webelementFrame);
				click(csecremoveIcon);
			
				verifyAssert(txtDeleteConfPopup);
				getObjectText(deleteConfPpMessage);
				verifyAssertEquals(drpln.getCell(8, 6).getContents(), getActualObjectTxt);
				click(delConfOkBtn);
				thread();	
			}
			}catch(WebDriverException e1) {
				System.out.println("Display fields are not displaying");
				e1.printStackTrace();
			}
			thread();
		}
	
	@Test(priority=10)
	void addContactGroupsSection() throws InterruptedException, BiffException, IOException
	{
		getDRPlanSheetFromExcel();
		 
		click(drEdtSecCreateNewSectionBtn);
		verifyAssert(createNewSectionPopup);
		
		//	Section Title
		click(secPpSectionTitleDDArrow);
		String cgsecSectionTitle = drpln.getCell(1, 8).getContents();		//	Add New Section Name (or) Choose Existing Section Name
		enterText(secPpSectionTitleDDSearchBox, cgsecSectionTitle);
		
		try
		{
			verifyObjDisplay(clickToAddTxtInDD);
			if(chkObjDisplay == true)
			{
				click(clickToAddTxtInDD);
				enterKeyInKyBord(secPpSectionTitleDDSearchBox);
				verifyAssert(sectionAddConfPopup);
				getObjectText(deleteConfPpMessage); 
				verifyAssertEquals("Do you want to add "+drpln.getCell(1, 8).getContents()+" into Section Title ?", getActualObjectTxt);
				click(delConfOkBtn);
				thread();
				
				getObjectText(msgNotificationBar);
				verifyAssertEquals(drpln.getCell(1, 8).getContents()+" added into Section Title successfully", getActualObjectTxt);		//	Check New Section Added Successfully
				thread();
				
				getObjectText(secPpSectionTitleDefTxt);
				verifyAssertEquals(drpln.getCell(1, 8).getContents(), getActualObjectTxt);		//	Verify Added Title In Drop Down
			}
		}catch(WebDriverException e){
			arrowDown(secPpSectionTitleDDSearchBox);
			enterKeyInKyBord(secPpSectionTitleDDSearchBox);
		}
		
		//	Authorized By
		click(secPpAuthorizedByDDArrow);
		getTotalValuesIndd(secPpAuthorizedByDDCnt);
		Random authBy = new Random();
		int rauthBy = authBy.nextInt(totalDDValCount-2)+2;
		String cgsecAuthorizedBy = driver.findElement(By.id("ddlDSAuthorizedBy_chzn_o_"+rauthBy)).getText();
		enterText(secPpAuthorizedByDDSearchBox, cgsecAuthorizedBy.split(",")[0]);
		enterKeyInKyBord(secPpAuthorizedByDDSearchBox);
		
		//	Verified By
		click(secPpVerifiedByDDArrow);
		getTotalValuesIndd(secPpVerifiedByDDCnt);
		Random veriBy = new Random();
		int rveriBy = veriBy.nextInt(totalDDValCount-2)+2;
		String cgsecVerifiedBy = driver.findElement(By.id("ddlDSVerifiedBy_chzn_o_"+rveriBy)).getText();
		enterText(secPpVerifiedByDDSearchBox, cgsecVerifiedBy.split(",")[0]);
		enterKeyInKyBord(secPpVerifiedByDDSearchBox);
		
		//	Description
		webElement(crSectionDescription);
		switchToWEFrame(webelementFrame);
		enterText(drscEdtrCommentsField, drpln.getCell(2, 8).getContents());
		switchBackFromFrame();
		takeScreenshot();
		
		click(secPpSaveBtn);
		thread();
			
		getObjectText(msgNotificationBar);
		verifyAssertEquals(drpln.getCell(8, 7).getContents(), getActualObjectTxt);		//	Section Created Successfully	
		
		getObjectText(cgsecDescription);
		verifyAssertEquals(drpln.getCell(2, 8).getContents(), getActualObjectTxt);		//	Check Added Section Description
		
		click(cgsecDescription);
		thread();
		
		//		Edit Description		//
		verifyAssert(createNewSectionPopup);
		getObjectText(secPpCreateNewSectionTtl);
		verifyAssertEquals(drpln.getCell(9, 3).getContents(), getActualObjectTxt);		//	Check Update Description Title
		
		webElement(crSectionDescription);
		switchToWEFrame(webelementFrame);
		useKeyFuncToRmvVal(drscEdtrCommentsField);
		enterText(drscEdtrCommentsField, drpln.getCell(3, 8).getContents());
		switchBackFromFrame();
		
		click(secPpSaveBtn);
		thread();
		
		getObjectText(msgNotificationBar);
		verifyAssertEquals(drpln.getCell(8, 1).getContents(), getActualObjectTxt);		//	Section Updated Successfully
		thread();	
		
		getObjectText(cgsecDescription);
		verifyAssertEquals(drpln.getCell(3, 8).getContents(), getActualObjectTxt);		//	Verify Edited Description Details
		
		
		//		Authorized & Verified By Person Names		//
		getObjectText1(lsecLblAuthorizedBy);
		getObjectText(cgsecAuthorizedByNm);
		verifyAssertEquals("Authorized By : "+cgsecAuthorizedBy, getActualObjectTxt1+" "+getActualObjectTxt);	//	Check Authorized Person Name
		
		getObjectText1(lsecLblVerifiedBy);
		getObjectText(cgsecVerifiedByNm);
		verifyAssertEquals("Verified By : "+cgsecVerifiedBy, getActualObjectTxt1+" "+getActualObjectTxt);	//	Check Verified Person Name
		thread();
		
		
		//		Add Text/Paragraph		//
		click(cgsecAddTextBtn);
		
		verifyAssert(adTxtAddTextParagraphPopup);
		getObjectText(adTxtAddTextParagraphPpTtl);
		verifyAssertEquals(drpln.getCell(9, 4).getContents(), getActualObjectTxt);		//	Add Text/Paragraph Pop Up Title
			
		getObjectText(adTxtPpEnterSomeTxtLbl);
		verifyAssertEquals(drpln.getCell(10, 1).getContents(), getActualObjectTxt);	//	Enter Some Text Label Name
			
		webElement(adTxtEnterSomeTextFld);
		switchToWEFrame(webelementFrame);
		enterText(scEdtrCommentsField, drpln.getCell(4, 8).getContents());		//	Enter Some Texts
		switchBackFromFrame();
		
		click(adTxtParagrphPpSaveBtn);
		thread();
		
		getObjectText(msgNotificationBar);
		verifyAssertEquals(drpln.getCell(8, 2).getContents(), getActualObjectTxt);		//	Text/Paragraph added successfully 
		
		getObjectText(cgsecfirstText);
		verifyAssertEquals(drpln.getCell(4, 8).getContents(), getActualObjectTxt);		//	Verify Entered Text In Section Page
		
		click(cgsecfirstText);
		thread();
		
		//		Edit Text		//
		getObjectText(adTxtAddTextParagraphPpTtl);
		verifyAssertEquals(drpln.getCell(9, 5).getContents(), getActualObjectTxt);		//	Update Text/Paragraph Title
			
		webElement(adTxtEnterSomeTextFld);
		switchToWEFrame(webelementFrame);
		useKeyFuncToRmvVal(scEdtrCommentsField);	//	Edit Some Texts
		enterText(scEdtrCommentsField, drpln.getCell(5, 8).getContents());
		switchBackFromFrame();
			
		click(adTxtParagrphPpSaveBtn);
		thread();
			
		getObjectText(msgNotificationBar);
		verifyAssertEquals(drpln.getCell(8, 3).getContents(), getActualObjectTxt);		//	Text/Paragraph Updated Successfully
			
		getObjectText(cgsecfirstText);
		verifyAssertEquals(drpln.getCell(5, 8).getContents(), getActualObjectTxt);		//	Verify Modified Text
		
		
		//		Add Dynamic Data		//
		click(cgsecAddDataBtn);
		thread();
		
		verifyAssert(addDynamicDataPopup);
			
		click(dynDtaPpModuleDDArrow);
		enterText(dynDtaPpModuleDDSearchBox, drpln.getCell(11, 8).getContents());		//	Choose Contact Groups module
		enterKeyInKyBord(dynDtaPpModuleDDSearchBox);
		thread();
		
		try
		{
			verifyObjDisplay(dynDtaEmpSelectAllChkBox);
			if(chkObjDisplay == true)
			{
				//	Enable Select All Check Box
				click(dynDtaEmpSelectAllChkBox);						
				getTotalValuesIndd(dynDtaEmpDisplayFldCnt);
				try
				{
					for(int i=1; i<=totalDDValCount; i++)
					{
						boolean checked = driver.findElement(By.xpath("//div[@id='divDDataFields']/span["+i+"]/input")).isSelected();
						Assert.assertEquals(true, checked);
					}
						
					//	Disable Select All Check Box
					click(dynDtaEmpSelectAllChkBox);
					getTotalValuesIndd(dynDtaEmpDisplayFldCnt);
					for(int j=1; j<=totalDDValCount; j++)
					{
						boolean unchecked = driver.findElement(By.xpath("//div[@id='divDDataFields']/span["+j+"]/input")).isSelected();
						Assert.assertEquals(false, unchecked);
					}
				}catch(WebDriverException e3) {
						e3.printStackTrace();
				}
					
				click(cgsecdfContactGroupName);
				click(cgsecdfGroupPurpose);
				click(cgsecdfcomments);
													
				click(dynDtaAddNewFilterBtn);	//	Add New Filter button
					
				click(dynDtaFieldDDArrow);
				enterText(dynDtaFieldDDSearchBox, "Contact Group Name");		//	Choose Filter Field
				enterKeyInKyBord(dynDtaFieldDDSearchBox);
				thread();
				
				//		Choose Filter Value
				click(dynDtaFieldValueDD);
				getTotalValuesIndd(dynDtaFieldValueCnt);
				
				ArrayList<Integer> alist = new ArrayList<Integer>();
				for(int i=1; i<totalDDValCount; i++)
				{
					alist.add(i);
				}
				Collections.shuffle(alist);
				
				for(int i=0; i<2; i++)
				{
					String randomValue = driver.findElement(By.id("ddlDDataNewFilterValues_chzn_o_"+alist.get(i))).getText();
					enterText(dynDtaFieldValueDD, randomValue);
					enterKeyInKyBord(dynDtaFieldValueDD);
					click(dynDtaFilterAddBtn);
				}		
					
				click(dynDtaPpSaveBtn);
				thread();
				
				getObjectText(msgNotificationBar);
				verifyAssertEquals(drpln.getCell(8, 4).getContents(), getActualObjectTxt);		//	Data Created Successfully
				thread();
				
				//		Check Selected Display Order in Table
				for(int i=1; i<4; i++)
				{
					String getColumnName = driver.findElement(By.xpath("//div[@id='divDynamicDRPlanSections']/div[8]//table[@class='table table-responsive table-striped table-bordered bootstrap-datatable dynamic-data-table']//tr/td["+i+"]")).getText();
					if(i == 1)
						verifyAssertEquals(drpln.getCell(12, 27).getContents(), getColumnName);	//	Contact Group Name
					if(i == 2)
						verifyAssertEquals(drpln.getCell(12, 28).getContents(), getColumnName);	//	Group Purpose
					if(i == 3)
						verifyAssertEquals(drpln.getCell(12, 10).getContents(), getColumnName);	//	Comments		
				}
				
				click(cgsecTeamsList);
				thread();
				
				verifyAssert(addDynamicDataPopup);
				
				for(int i=2; i<4; i++)
				{
					boolean checked = driver.findElement(By.xpath("//div[@id='divDDataFields']/span["+i+"]/input")).isSelected();
					Assert.assertEquals(true, checked);
				}
						
				//	Drag & Drop the objects
				sourceElement(dynDtaEmpWeFirstName);
				destinationElement(dynDtaEmpWeLastName);
				dragAndDrop(sourceObj, destinationObj);		//	Change Contact Group Name & Group Purpose position				
				
				sourceElement(dynDtaEmpWeLastName);
				destinationElement(dynDtaEmpWeMobile);
				dragAndDrop(sourceObj, destinationObj);		//	Change Contact Group Name & Comments position
				
				//	Verify Added Filter Values
				try
				{
					verifyObjDisplay(dynDtaEmpSelectedFilterValue);
					if(chkObjDisplay == true)
					{
						verifyAssert(dynDtaEmpFilterValue1);
						verifyAssert(dynDtaEmpFilterValue2);
					}
				}catch(WebDriverException e) {
					System.out.println("Selected Filter values are not displaying in Edit screen");
					e.printStackTrace();
				}
				
				click(dynDtaPpSaveBtn);
				thread();
							
				getObjectText(msgNotificationBar);
				verifyAssertEquals(drpln.getCell(8, 5).getContents(), getActualObjectTxt);		//	Data Updated Successfully
				thread();
												
				//		Verify Column Name Order Changes In Table
				for(int i=1; i<4; i++)
				{
					String getColumnName = driver.findElement(By.xpath("//div[@id='divDynamicDRPlanSections']/div[8]//table[@class='table table-responsive table-striped table-bordered bootstrap-datatable dynamic-data-table']//tr/td["+i+"]")).getText();
					if(i == 1)
						verifyAssertEquals(drpln.getCell(12, 28).getContents(), getColumnName);	//	Group Purpose
					if(i == 2)
						verifyAssertEquals(drpln.getCell(12, 10).getContents(), getColumnName);	//	Comments
					if(i == 3)
						verifyAssertEquals(drpln.getCell(12, 27).getContents(), getColumnName);	//	Contact Group Name
				}				
							
				//		Edit Section		//
				click(cgsecEditSectionBtn);
				thread();
			
				verifyAssert(createNewSectionPopup);
				getObjectText(secPpCreateNewSectionTtl);
				verifyAssertEquals(drpln.getCell(9, 2).getContents(), getActualObjectTxt);		//	Update Section Pop up Title
			
				getObjectText(secPpSectionTitleDefTxt);
				verifyAssertEquals(cgsecSectionTitle, getActualObjectTxt);		//	Verify Selected Section Title Name 	
			
				getObjectText(secPpAuthorizedByDefTxt);
				verifyAssertEquals(cgsecAuthorizedBy, getActualObjectTxt);		//	Check Selected Authorized By Name
			
				getObjectText(secPpVerifiedByDefTxt);
				verifyAssertEquals(cgsecVerifiedBy, getActualObjectTxt);		//	Check Select Verified By Name
			
				//		Section Title
				//click(secPpSectionTitleDDArrow);
				//getTotalValuesIndd(secPpSectionTitleDDCnt);
				//Random edtSecTitle = new Random();
				//int edtrSecTitle = edtSecTitle.nextInt(totalDDValCount-2)+2;
				//String edtSectionTitle = driver.findElement(By.id("ddlNewDynamicSectionTitle_chzn_o_"+edtrSecTitle)).getText();
				//enterText(secPpSectionTitleDDSearchBox, edtSectionTitle);
				//enterKeyInKyBord(secPpSectionTitleDDSearchBox);
			
				//	Authorized By
				click(secPpAuthorizedByDDArrow);
				getTotalValuesIndd(secPpAuthorizedByDDCnt);
				Random edtAuthBy = new Random();
				int edtrAuthBy = edtAuthBy.nextInt(totalDDValCount-2)+2;
				String edtAuthorizedBy = driver.findElement(By.id("ddlDSAuthorizedBy_chzn_o_"+edtrAuthBy)).getText();
				enterText(secPpAuthorizedByDDSearchBox, edtAuthorizedBy.split(",")[0]);
				enterKeyInKyBord(secPpAuthorizedByDDSearchBox);
			
				//	Verified By
				click(secPpVerifiedByDDArrow);
				getTotalValuesIndd(secPpVerifiedByDDCnt);
				Random edtVeriBy = new Random();
				int edtrVeriBy = edtVeriBy.nextInt(totalDDValCount-2)+2;
				String edtVerifiedBy = driver.findElement(By.id("ddlDSVerifiedBy_chzn_o_"+edtrVeriBy)).getText();
				enterText(secPpVerifiedByDDSearchBox, edtVerifiedBy.split(",")[0]);
				enterKeyInKyBord(secPpVerifiedByDDSearchBox);
				
				click(secPpSaveBtn);
				thread();
				
				//try
				//{
				//	click(secPpSaveBtn);
				//	thread();
				
				//	getObjectText(msgNotificationBar);
				//	verifyAssertEquals(sectionUpdatedSuccessfullyMsg, getActualObjectTxt);		//	Section Updated Successfully
				//}catch(WebDriverException e) {
				//	e.printStackTrace();
				
				//	click(secPpSaveBtn);
					//thread();
				
					//getObjectText(msgNotificationBar);
					//verifyAssertEquals(lsecSectionTitle+sectionTitileNmAlreadyExistsMsg, getActualObjectTxt);		//	Section Title Already Exists
				
					//click(secPpSectionTitleDDArrow);
					//getTotalValuesIndd(secPpSectionTitleDDCnt);
					//Random edtSecTitle1 = new Random();
					//int edtrSecTitle1 = edtSecTitle1.nextInt(totalDDValCount-0)+0;
					//String edtSectionTitle1 = driver.findElement(By.id("ddlNewDynamicSectionTitle_chzn_o_"+edtrSecTitle1)).getText();
					//enterText(secPpSectionTitleDDSearchBox, edtSectionTitle1);
					//enterKeyInKyBord(secPpSectionTitleDDSearchBox);
				
					//click(secPpSaveBtn);
					//thread();
				//}
			
				//		Verify Edited Authorized & Verified By Name
				getObjectText1(drEdtSecAuthorizedByLblNm);
				getObjectText(cgsecAuthorizedByNm);
				verifyAssertEquals("Authorized By : "+edtAuthorizedBy, getActualObjectTxt1+" "+getActualObjectTxt);	//	Check Authorized Person Name
			
				getObjectText1(drEdtSecVerifiedByLblNm);
				getObjectText(cgsecVerifiedByNm);
				verifyAssertEquals("Verified By : "+edtVerifiedBy, getActualObjectTxt1+" "+getActualObjectTxt);	//	Check Verified Person Name
				thread();
			
				//		Add Text - 2nd Text		//
				click(cgsecAddTextBtn);
			
				verifyAssert(adTxtAddTextParagraphPopup);
				getObjectText(adTxtAddTextParagraphPpTtl);
				verifyAssertEquals(drpln.getCell(9, 4).getContents(), getActualObjectTxt);		//	Add Text/Paragraph Pop Up Title
				
				getObjectText(adTxtPpEnterSomeTxtLbl);
				verifyAssertEquals(drpln.getCell(10, 1).getContents(), getActualObjectTxt);		//	Enter Some Text Label Name
				
				webElement(adTxtEnterSomeTextFld);
				switchToWEFrame(webelementFrame);
				enterText(scEdtrCommentsField, drpln.getCell(6, 8).getContents());		//	Enter Some Texts
				switchBackFromFrame();
			
				click(adTxtParagrphPpSaveBtn);
				thread();
			
				getObjectText(msgNotificationBar);
				verifyAssertEquals(drpln.getCell(8, 2).getContents(), getActualObjectTxt);		//	Text/Paragraph added successfully 
			
				webElement(cgsec2ndTxt); 
				scrollInnerScrollBar(webelementFrame);		//	Scrolling Section Grid Inner Scroll Bar	
			
				getObjectText(cgsec2ndTxt);
				verifyAssertEquals(drpln.getCell(6, 8).getContents(), getActualObjectTxt);		//	Verify Entered Text In Section Page
				thread();
			
				//		Mouse Hover - Move Last
				webElement(cgsecmHovrOnFirstOptn);
				mouseHoverOn(webelementFrame);
				click(cgsecmoveLastIcon);				
				getObjectText(cgsec2ndTxt); 
				verifyAssertEquals(drpln.getCell(5, 8).getContents(), getActualObjectTxt);
				thread();
			
				//	Mouse Hover - Move Top
				webElement(cgsecmHovrOnSecOptn);
				mouseHoverOn(webelementFrame);
				thread();
				webElement(cgsecmoveTopIcon);
				mouseHoverOn(webelementFrame);
				click(cgsecmoveTopIcon);				
				getObjectText(cgsecfirstText);
				verifyAssertEquals(drpln.getCell(6, 8).getContents(), getActualObjectTxt);	
				thread();
			
				//	Mouse Hover - Move Down
				webElement(cgsecmHovrOnFirstOptn);
				mouseHoverOn(webelementFrame);
				click(cgsecmoveDownIcon);				
				getObjectText(cgsec2ndTxt1);
				verifyAssertEquals(drpln.getCell(6, 8).getContents(), getActualObjectTxt);
				thread();
			
				//	Mouse Hover - Move Up
				webElement(cgsecmHovrOnSecOptn);
				mouseHoverOn(webelementFrame);
				click(cgsecmoveUpIcon);
				getObjectText(cgsecfirstText);
				verifyAssertEquals(drpln.getCell(6, 8).getContents(), getActualObjectTxt);
				thread();
			
				//	Mouse Hover - Delete
				webElement(cgsecmHovrOnThirdOptn);
				mouseHoverOn(webelementFrame);
				click(cgsecremoveIcon);
			
				verifyAssert(txtDeleteConfPopup);
				getObjectText(deleteConfPpMessage);
				verifyAssertEquals(drpln.getCell(8, 6).getContents(), getActualObjectTxt);
				click(delConfOkBtn);
				thread();	
			}
			}catch(WebDriverException e1) {
				System.out.println("Display fields are not displaying");
				e1.printStackTrace();
			}
			thread();
		}
	
	@Test(priority=11)
	void addInsuranceSection() throws InterruptedException, BiffException, IOException
	{
		getDRPlanSheetFromExcel();
		 
		click(drEdtSecCreateNewSectionBtn);
		verifyAssert(createNewSectionPopup);
		
		//	Section Title
		click(secPpSectionTitleDDArrow);
		String isecSectionTitle = drpln.getCell(1, 9).getContents();		//	Add New Section Name (or) Choose Existing Section Name
		enterText(secPpSectionTitleDDSearchBox, isecSectionTitle);
		
		try
		{
			verifyObjDisplay(clickToAddTxtInDD);
			if(chkObjDisplay == true)
			{
				click(clickToAddTxtInDD);
				enterKeyInKyBord(secPpSectionTitleDDSearchBox);
				verifyAssert(sectionAddConfPopup);
				getObjectText(deleteConfPpMessage); 
				verifyAssertEquals("Do you want to add "+drpln.getCell(1, 9).getContents()+" into Section Title ?", getActualObjectTxt);
				click(delConfOkBtn);
				thread();
				
				getObjectText(msgNotificationBar);
				verifyAssertEquals(drpln.getCell(1, 9).getContents()+" added into Section Title successfully", getActualObjectTxt);		//	Check New Section Added Successfully
				thread();
				
				getObjectText(secPpSectionTitleDefTxt);
				verifyAssertEquals(drpln.getCell(1, 9).getContents(), getActualObjectTxt);		//	Verify Added Title In Drop Down
			}
		}catch(WebDriverException e){
			arrowDown(secPpSectionTitleDDSearchBox);
			enterKeyInKyBord(secPpSectionTitleDDSearchBox);
		}
		
		//	Authorized By
		click(secPpAuthorizedByDDArrow);
		getTotalValuesIndd(secPpAuthorizedByDDCnt);
		Random authBy = new Random();
		int rauthBy = authBy.nextInt(totalDDValCount-2)+2;
		String isecAuthorizedBy = driver.findElement(By.id("ddlDSAuthorizedBy_chzn_o_"+rauthBy)).getText();
		enterText(secPpAuthorizedByDDSearchBox, isecAuthorizedBy.split(",")[0]);
		enterKeyInKyBord(secPpAuthorizedByDDSearchBox);
		
		//	Verified By
		click(secPpVerifiedByDDArrow);
		getTotalValuesIndd(secPpVerifiedByDDCnt);
		Random veriBy = new Random();
		int rveriBy = veriBy.nextInt(totalDDValCount-2)+2;
		String isecVerifiedBy = driver.findElement(By.id("ddlDSVerifiedBy_chzn_o_"+rveriBy)).getText();
		enterText(secPpVerifiedByDDSearchBox, isecVerifiedBy.split(",")[0]);
		enterKeyInKyBord(secPpVerifiedByDDSearchBox);
		
		//	Description
		webElement(crSectionDescription);
		switchToWEFrame(webelementFrame);
		enterText(drscEdtrCommentsField, drpln.getCell(2, 9).getContents());
		switchBackFromFrame();
		takeScreenshot();
		
		click(secPpSaveBtn);
		thread();
			
		getObjectText(msgNotificationBar);
		verifyAssertEquals(drpln.getCell(8, 7).getContents(), getActualObjectTxt);		//	Section Created Successfully	
		
		getObjectText(isecDescription);
		verifyAssertEquals(drpln.getCell(2, 9).getContents(), getActualObjectTxt);		//	Check Added Section Description
		
		click(isecDescription);
		thread();
		
		//		Edit Description		//
		verifyAssert(createNewSectionPopup);
		getObjectText(secPpCreateNewSectionTtl);
		verifyAssertEquals(drpln.getCell(9, 3).getContents(), getActualObjectTxt);		//	Check Update Description Title
		
		webElement(crSectionDescription);
		switchToWEFrame(webelementFrame);
		useKeyFuncToRmvVal(drscEdtrCommentsField);
		enterText(drscEdtrCommentsField, drpln.getCell(3, 9).getContents());
		switchBackFromFrame();
		
		click(secPpSaveBtn);
		thread();
		
		getObjectText(msgNotificationBar);
		verifyAssertEquals(drpln.getCell(8, 1).getContents(), getActualObjectTxt);		//	Section Updated Successfully
		thread();	
		
		getObjectText(isecDescription);
		verifyAssertEquals(drpln.getCell(3, 9).getContents(), getActualObjectTxt);		//	Verify Edited Description Details
		
		
		//		Authorized & Verified By Person Names		//
		getObjectText1(lsecLblAuthorizedBy);
		getObjectText(isecAuthorizedByNm);
		verifyAssertEquals("Authorized By : "+isecAuthorizedBy, getActualObjectTxt1+" "+getActualObjectTxt);	//	Check Authorized Person Name
		
		getObjectText1(lsecLblVerifiedBy);
		getObjectText(isecVerifiedByNm);
		verifyAssertEquals("Verified By : "+isecVerifiedBy, getActualObjectTxt1+" "+getActualObjectTxt);	//	Check Verified Person Name
		thread();
		
		
		//		Add Text/Paragraph		//
		click(isecAddTextBtn);
		
		verifyAssert(adTxtAddTextParagraphPopup);
		getObjectText(adTxtAddTextParagraphPpTtl);
		verifyAssertEquals(drpln.getCell(9, 4).getContents(), getActualObjectTxt);		//	Add Text/Paragraph Pop Up Title
			
		getObjectText(adTxtPpEnterSomeTxtLbl);
		verifyAssertEquals(drpln.getCell(10, 1).getContents(), getActualObjectTxt);	//	Enter Some Text Label Name
			
		webElement(adTxtEnterSomeTextFld);
		switchToWEFrame(webelementFrame);
		enterText(scEdtrCommentsField, drpln.getCell(4, 9).getContents());		//	Enter Some Texts
		switchBackFromFrame();
		
		click(adTxtParagrphPpSaveBtn);
		thread();
		
		getObjectText(msgNotificationBar);
		verifyAssertEquals(drpln.getCell(8, 2).getContents(), getActualObjectTxt);		//	Text/Paragraph added successfully 
		
		getObjectText(isecfirstText);
		verifyAssertEquals(drpln.getCell(4, 9).getContents(), getActualObjectTxt);		//	Verify Entered Text In Section Page
		
		click(isecfirstText);
		thread();
		
		//		Edit Text		//
		getObjectText(adTxtAddTextParagraphPpTtl);
		verifyAssertEquals(drpln.getCell(9, 5).getContents(), getActualObjectTxt);		//	Update Text/Paragraph Title
			
		webElement(adTxtEnterSomeTextFld);
		switchToWEFrame(webelementFrame);
		useKeyFuncToRmvVal(scEdtrCommentsField);	//	Edit Some Texts
		enterText(scEdtrCommentsField, drpln.getCell(5, 9).getContents());
		switchBackFromFrame();
			
		click(adTxtParagrphPpSaveBtn);
		thread();
			
		getObjectText(msgNotificationBar);
		verifyAssertEquals(drpln.getCell(8, 3).getContents(), getActualObjectTxt);		//	Text/Paragraph Updated Successfully
			
		getObjectText(isecfirstText);
		verifyAssertEquals(drpln.getCell(5, 9).getContents(), getActualObjectTxt);		//	Verify Modified Text
		
		
		//		Add Dynamic Data		//
		click(isecAddDataBtn);
		thread();
		
		verifyAssert(addDynamicDataPopup);
			
		click(dynDtaPpModuleDDArrow);
		enterText(dynDtaPpModuleDDSearchBox, drpln.getCell(11, 9).getContents());		//	Choose Insurance module
		enterKeyInKyBord(dynDtaPpModuleDDSearchBox);
		thread();
		
		try
		{
			verifyObjDisplay(dynDtaEmpSelectAllChkBox);
			if(chkObjDisplay == true)
			{
				//	Enable Select All Check Box
				click(dynDtaEmpSelectAllChkBox);						
				getTotalValuesIndd(dynDtaEmpDisplayFldCnt);
				try
				{
					for(int i=1; i<=totalDDValCount; i++)
					{
						boolean checked = driver.findElement(By.xpath("//div[@id='divDDataFields']/span["+i+"]/input")).isSelected();
						Assert.assertEquals(true, checked);
					}
						
					//	Disable Select All Check Box
					click(dynDtaEmpSelectAllChkBox);
					getTotalValuesIndd(dynDtaEmpDisplayFldCnt);
					for(int j=1; j<=totalDDValCount; j++)
					{
						boolean unchecked = driver.findElement(By.xpath("//div[@id='divDDataFields']/span["+j+"]/input")).isSelected();
						Assert.assertEquals(false, unchecked);
					}
				}catch(WebDriverException e3) {
						e3.printStackTrace();
				}
					
				click(isecdfPolicyName);
				click(isecdfInsurer);
				click(isecdfExpiryDate);
				click(isecdfInsurerContact);
									
				click(dynDtaAddNewFilterBtn);	//	Add New Filter button
					
				click(dynDtaFieldDDArrow);
				enterText(dynDtaFieldDDSearchBox, "Policy Name");		//	Choose Filter Field
				enterKeyInKyBord(dynDtaFieldDDSearchBox);
				thread();
				
				//		Choose Filter Value
				click(dynDtaFieldValueDD);
				getTotalValuesIndd(dynDtaFieldValueCnt);
				
				ArrayList<Integer> alist = new ArrayList<Integer>();
				for(int i=1; i<totalDDValCount; i++)
				{
					alist.add(i);
				}
				Collections.shuffle(alist);
				
				for(int i=0; i<2; i++)
				{
					String randomValue = driver.findElement(By.id("ddlDDataNewFilterValues_chzn_o_"+alist.get(i))).getText();
					enterText(dynDtaFieldValueDD, randomValue);
					enterKeyInKyBord(dynDtaFieldValueDD);
					click(dynDtaFilterAddBtn);
				}		
					
				click(dynDtaPpSaveBtn);
				thread();
				
				getObjectText(msgNotificationBar);
				verifyAssertEquals(drpln.getCell(8, 4).getContents(), getActualObjectTxt);		//	Data Created Successfully
				thread();
				
				//		Check Selected Display Order in Table
				for(int i=1; i<5; i++)
				{
					String getColumnName = driver.findElement(By.xpath("//div[@id='divDynamicDRPlanSections']/div[9]//table[@class='table table-responsive table-striped table-bordered bootstrap-datatable dynamic-data-table']//tr/td["+i+"]")).getText();
					if(i == 1)
						verifyAssertEquals(drpln.getCell(12, 29).getContents(), getColumnName);	//	Policy Name
					if(i == 2)
						verifyAssertEquals(drpln.getCell(12, 30).getContents(), getColumnName);	//	Insurer
					if(i == 3)
						verifyAssertEquals(drpln.getCell(12, 31).getContents(), getColumnName);	//	Expiry Date
					if(i == 4)
						verifyAssertEquals(drpln.getCell(12, 32).getContents(), getColumnName);	//	Insurer Contact
				}
				
				click(isecTeamsList);
				thread();
				
				verifyAssert(addDynamicDataPopup);
				
				for(int i=2; i<4; i++)
				{
					boolean checked = driver.findElement(By.xpath("//div[@id='divDDataFields']/span["+i+"]/input")).isSelected();
					Assert.assertEquals(true, checked);
				}
						
				//	Drag & Drop the objects
				sourceElement(dynDtaEmpWeFirstName);
				destinationElement(dynDtaEmpWeLastName);
				dragAndDrop(sourceObj, destinationObj);		//	Change Policy Name & Insurer position				
				
				sourceElement(dynDtaEmpWeMobile);
				destinationElement(dynDtaEmpWeAlternateEmployee);
				dragAndDrop(sourceObj, destinationObj);		//	Change Expiry Date & Insurer Contact position
				
				//	Verify Added Filter Values
				try
				{
					verifyObjDisplay(dynDtaEmpSelectedFilterValue);
					if(chkObjDisplay == true)
					{
						verifyAssert(dynDtaEmpFilterValue1);
						verifyAssert(dynDtaEmpFilterValue2);
					}
				}catch(WebDriverException e) {
					System.out.println("Selected Filter values are not displaying in Edit screen");
					e.printStackTrace();
				}
				
				click(dynDtaPpSaveBtn);
				thread();
							
				getObjectText(msgNotificationBar);
				verifyAssertEquals(drpln.getCell(8, 5).getContents(), getActualObjectTxt);		//	Data Updated Successfully
				thread();
												
				//		Verify Column Name Order Changes In Table
				for(int i=1; i<5; i++)
				{
					String getColumnName = driver.findElement(By.xpath("//div[@id='divDynamicDRPlanSections']/div[9]//table[@class='table table-responsive table-striped table-bordered bootstrap-datatable dynamic-data-table']//tr/td["+i+"]")).getText();
					if(i == 1)
						verifyAssertEquals(drpln.getCell(12, 30).getContents(), getColumnName);	//	Insurer
					if(i == 2)
						verifyAssertEquals(drpln.getCell(12, 29).getContents(), getColumnName);	//	Policy Name
					if(i == 3)
						verifyAssertEquals(drpln.getCell(12, 32).getContents(), getColumnName);	//	Insurer Contact
					if(i == 4)
						verifyAssertEquals(drpln.getCell(12, 31).getContents(), getColumnName);	//	Expiry Date
				}				
							
				//		Edit Section		//
				click(isecEditSectionBtn);
				thread();
			
				verifyAssert(createNewSectionPopup);
				getObjectText(secPpCreateNewSectionTtl);
				verifyAssertEquals(drpln.getCell(9, 2).getContents(), getActualObjectTxt);		//	Update Section Pop up Title
			
				getObjectText(secPpSectionTitleDefTxt);
				verifyAssertEquals(isecSectionTitle, getActualObjectTxt);		//	Verify Selected Section Title Name 	
			
				getObjectText(secPpAuthorizedByDefTxt);
				verifyAssertEquals(isecAuthorizedBy, getActualObjectTxt);		//	Check Selected Authorized By Name
			
				getObjectText(secPpVerifiedByDefTxt);
				verifyAssertEquals(isecVerifiedBy, getActualObjectTxt);		//	Check Select Verified By Name
			
				//		Section Title
				//click(secPpSectionTitleDDArrow);
				//getTotalValuesIndd(secPpSectionTitleDDCnt);
				//Random edtSecTitle = new Random();
				//int edtrSecTitle = edtSecTitle.nextInt(totalDDValCount-2)+2;
				//String edtSectionTitle = driver.findElement(By.id("ddlNewDynamicSectionTitle_chzn_o_"+edtrSecTitle)).getText();
				//enterText(secPpSectionTitleDDSearchBox, edtSectionTitle);
				//enterKeyInKyBord(secPpSectionTitleDDSearchBox);
			
				//	Authorized By
				click(secPpAuthorizedByDDArrow);
				getTotalValuesIndd(secPpAuthorizedByDDCnt);
				Random edtAuthBy = new Random();
				int edtrAuthBy = edtAuthBy.nextInt(totalDDValCount-2)+2;
				String edtAuthorizedBy = driver.findElement(By.id("ddlDSAuthorizedBy_chzn_o_"+edtrAuthBy)).getText();
				enterText(secPpAuthorizedByDDSearchBox, edtAuthorizedBy.split(",")[0]);
				enterKeyInKyBord(secPpAuthorizedByDDSearchBox);
			
				//	Verified By
				click(secPpVerifiedByDDArrow);
				getTotalValuesIndd(secPpVerifiedByDDCnt);
				Random edtVeriBy = new Random();
				int edtrVeriBy = edtVeriBy.nextInt(totalDDValCount-2)+2;
				String edtVerifiedBy = driver.findElement(By.id("ddlDSVerifiedBy_chzn_o_"+edtrVeriBy)).getText();
				enterText(secPpVerifiedByDDSearchBox, edtVerifiedBy.split(",")[0]);
				enterKeyInKyBord(secPpVerifiedByDDSearchBox);
				
				click(secPpSaveBtn);
				thread();
				
				//try
				//{
				//	click(secPpSaveBtn);
				//	thread();
				
				//	getObjectText(msgNotificationBar);
				//	verifyAssertEquals(sectionUpdatedSuccessfullyMsg, getActualObjectTxt);		//	Section Updated Successfully
				//}catch(WebDriverException e) {
				//	e.printStackTrace();
				
				//	click(secPpSaveBtn);
					//thread();
				
					//getObjectText(msgNotificationBar);
					//verifyAssertEquals(lsecSectionTitle+sectionTitileNmAlreadyExistsMsg, getActualObjectTxt);		//	Section Title Already Exists
				
					//click(secPpSectionTitleDDArrow);
					//getTotalValuesIndd(secPpSectionTitleDDCnt);
					//Random edtSecTitle1 = new Random();
					//int edtrSecTitle1 = edtSecTitle1.nextInt(totalDDValCount-0)+0;
					//String edtSectionTitle1 = driver.findElement(By.id("ddlNewDynamicSectionTitle_chzn_o_"+edtrSecTitle1)).getText();
					//enterText(secPpSectionTitleDDSearchBox, edtSectionTitle1);
					//enterKeyInKyBord(secPpSectionTitleDDSearchBox);
				
					//click(secPpSaveBtn);
					//thread();
				//}
			
				//		Verify Edited Authorized & Verified By Name
				getObjectText1(drEdtSecAuthorizedByLblNm);
				getObjectText(isecAuthorizedByNm);
				verifyAssertEquals("Authorized By : "+edtAuthorizedBy, getActualObjectTxt1+" "+getActualObjectTxt);	//	Check Authorized Person Name
			
				getObjectText1(drEdtSecVerifiedByLblNm);
				getObjectText(isecVerifiedByNm);
				verifyAssertEquals("Verified By : "+edtVerifiedBy, getActualObjectTxt1+" "+getActualObjectTxt);	//	Check Verified Person Name
				thread();
			
				//		Add Text - 2nd Text		//
				click(isecAddTextBtn);
			
				verifyAssert(adTxtAddTextParagraphPopup);
				getObjectText(adTxtAddTextParagraphPpTtl);
				verifyAssertEquals(drpln.getCell(9, 4).getContents(), getActualObjectTxt);		//	Add Text/Paragraph Pop Up Title
				
				getObjectText(adTxtPpEnterSomeTxtLbl);
				verifyAssertEquals(drpln.getCell(10, 1).getContents(), getActualObjectTxt);		//	Enter Some Text Label Name
				
				webElement(adTxtEnterSomeTextFld);
				switchToWEFrame(webelementFrame);
				enterText(scEdtrCommentsField, drpln.getCell(6, 9).getContents());		//	Enter Some Texts
				switchBackFromFrame();
			
				click(adTxtParagrphPpSaveBtn);
				thread();
			
				getObjectText(msgNotificationBar);
				verifyAssertEquals(drpln.getCell(8, 2).getContents(), getActualObjectTxt);		//	Text/Paragraph added successfully 
			
				webElement(isec2ndTxt); 
				scrollInnerScrollBar(webelementFrame);		//	Scrolling Section Grid Inner Scroll Bar	
			
				getObjectText(isec2ndTxt);
				verifyAssertEquals(drpln.getCell(6, 9).getContents(), getActualObjectTxt);		//	Verify Entered Text In Section Page
				thread();
			
				//		Mouse Hover - Move Last
				webElement(isecmHovrOnFirstOptn);
				mouseHoverOn(webelementFrame);
				click(isecmoveLastIcon);				
				getObjectText(isec2ndTxt); 
				verifyAssertEquals(drpln.getCell(5, 9).getContents(), getActualObjectTxt);
				thread();
			
				//	Mouse Hover - Move Top
				webElement(isecmHovrOnSecOptn);
				mouseHoverOn(webelementFrame);
				thread();
				webElement(isecmoveTopIcon);
				mouseHoverOn(webelementFrame);
				click(isecmoveTopIcon);				
				getObjectText(isecfirstText);
				verifyAssertEquals(drpln.getCell(6, 9).getContents(), getActualObjectTxt);	
				thread();
			
				//	Mouse Hover - Move Down
				webElement(isecmHovrOnFirstOptn);
				mouseHoverOn(webelementFrame);
				click(isecmoveDownIcon);				
				getObjectText(isec2ndTxt1);
				verifyAssertEquals(drpln.getCell(6, 9).getContents(), getActualObjectTxt);
				thread();
			
				//	Mouse Hover - Move Up
				webElement(isecmHovrOnSecOptn);
				mouseHoverOn(webelementFrame);
				click(isecmoveUpIcon);
				getObjectText(isecfirstText);
				verifyAssertEquals(drpln.getCell(6, 9).getContents(), getActualObjectTxt);
				thread();
			
				//	Mouse Hover - Delete
				webElement(isecmHovrOnThirdOptn);
				mouseHoverOn(webelementFrame);
				click(isecremoveIcon);
			
				verifyAssert(txtDeleteConfPopup);
				getObjectText(deleteConfPpMessage);
				verifyAssertEquals(drpln.getCell(8, 6).getContents(), getActualObjectTxt);
				click(delConfOkBtn);
				thread();	
			}
			}catch(WebDriverException e1) {
				System.out.println("Display fields are not displaying");
				e1.printStackTrace();
			}
			thread();
		}
	
	@Test(priority=12)
	void addTasksSection() throws InterruptedException, BiffException, IOException
	{
		getDRPlanSheetFromExcel();
		 
		click(drEdtSecCreateNewSectionBtn);
		verifyAssert(createNewSectionPopup);
		
		//	Section Title
		click(secPpSectionTitleDDArrow);
		String tsecSectionTitle = drpln.getCell(1, 10).getContents();		//	Add New Section Name (or) Choose Existing Section Name
		enterText(secPpSectionTitleDDSearchBox, tsecSectionTitle);
		
		try
		{
			verifyObjDisplay(clickToAddTxtInDD);
			if(chkObjDisplay == true)
			{
				click(clickToAddTxtInDD);
				enterKeyInKyBord(secPpSectionTitleDDSearchBox);
				verifyAssert(sectionAddConfPopup);
				getObjectText(deleteConfPpMessage); 
				verifyAssertEquals("Do you want to add "+drpln.getCell(1, 10).getContents()+" into Section Title ?", getActualObjectTxt);
				click(delConfOkBtn);
				thread();
				
				getObjectText(msgNotificationBar);
				verifyAssertEquals(drpln.getCell(1, 10).getContents()+" added into Section Title successfully", getActualObjectTxt);		//	Check New Section Added Successfully
				thread();
				
				getObjectText(secPpSectionTitleDefTxt);
				verifyAssertEquals(drpln.getCell(1, 10).getContents(), getActualObjectTxt);		//	Verify Added Title In Drop Down
			}
		}catch(WebDriverException e){
			arrowDown(secPpSectionTitleDDSearchBox);
			enterKeyInKyBord(secPpSectionTitleDDSearchBox);
		}
		
		//	Authorized By
		click(secPpAuthorizedByDDArrow);
		getTotalValuesIndd(secPpAuthorizedByDDCnt);
		Random authBy = new Random();
		int rauthBy = authBy.nextInt(totalDDValCount-2)+2;
		String tsecAuthorizedBy = driver.findElement(By.id("ddlDSAuthorizedBy_chzn_o_"+rauthBy)).getText();
		enterText(secPpAuthorizedByDDSearchBox, tsecAuthorizedBy.split(",")[0]);
		enterKeyInKyBord(secPpAuthorizedByDDSearchBox);
		
		//	Verified By
		click(secPpVerifiedByDDArrow);
		getTotalValuesIndd(secPpVerifiedByDDCnt);
		Random veriBy = new Random();
		int rveriBy = veriBy.nextInt(totalDDValCount-2)+2;
		String tsecVerifiedBy = driver.findElement(By.id("ddlDSVerifiedBy_chzn_o_"+rveriBy)).getText();
		enterText(secPpVerifiedByDDSearchBox, tsecVerifiedBy.split(",")[0]);
		enterKeyInKyBord(secPpVerifiedByDDSearchBox);
		
		//	Description
		webElement(crSectionDescription);
		switchToWEFrame(webelementFrame);
		enterText(drscEdtrCommentsField, drpln.getCell(2, 10).getContents());
		switchBackFromFrame();
		takeScreenshot();
		
		click(secPpSaveBtn);
		thread();
			
		getObjectText(msgNotificationBar);
		verifyAssertEquals(drpln.getCell(8, 7).getContents(), getActualObjectTxt);		//	Section Created Successfully	
		
		getObjectText(tsecDescription);
		verifyAssertEquals(drpln.getCell(2, 10).getContents(), getActualObjectTxt);		//	Check Added Section Description
		
		click(tsecDescription);
		thread();
		
		//		Edit Description		//
		verifyAssert(createNewSectionPopup);
		getObjectText(secPpCreateNewSectionTtl);
		verifyAssertEquals(drpln.getCell(9, 3).getContents(), getActualObjectTxt);		//	Check Update Description Title
		
		webElement(crSectionDescription);
		switchToWEFrame(webelementFrame);
		useKeyFuncToRmvVal(drscEdtrCommentsField);
		enterText(drscEdtrCommentsField, drpln.getCell(3, 10).getContents());
		switchBackFromFrame();
		
		click(secPpSaveBtn);
		thread();
		
		getObjectText(msgNotificationBar);
		verifyAssertEquals(drpln.getCell(8, 1).getContents(), getActualObjectTxt);		//	Section Updated Successfully
		thread();	
		
		getObjectText(tsecDescription);
		verifyAssertEquals(drpln.getCell(3, 10).getContents(), getActualObjectTxt);		//	Verify Edited Description Details
		
		
		//		Authorized & Verified By Person Names		//
		getObjectText1(lsecLblAuthorizedBy);
		getObjectText(tsecAuthorizedByNm);
		verifyAssertEquals("Authorized By : "+tsecAuthorizedBy, getActualObjectTxt1+" "+getActualObjectTxt);	//	Check Authorized Person Name
		
		getObjectText1(lsecLblVerifiedBy);
		getObjectText(tsecVerifiedByNm);
		verifyAssertEquals("Verified By : "+tsecVerifiedBy, getActualObjectTxt1+" "+getActualObjectTxt);	//	Check Verified Person Name
		thread();
		
		
		//		Add Text/Paragraph		//
		click(tsecAddTextBtn);
		
		verifyAssert(adTxtAddTextParagraphPopup);
		getObjectText(adTxtAddTextParagraphPpTtl);
		verifyAssertEquals(drpln.getCell(9, 4).getContents(), getActualObjectTxt);		//	Add Text/Paragraph Pop Up Title
			
		getObjectText(adTxtPpEnterSomeTxtLbl);
		verifyAssertEquals(drpln.getCell(10, 1).getContents(), getActualObjectTxt);	//	Enter Some Text Label Name
			
		webElement(adTxtEnterSomeTextFld);
		switchToWEFrame(webelementFrame);
		enterText(scEdtrCommentsField, drpln.getCell(4, 10).getContents());		//	Enter Some Texts
		switchBackFromFrame();
		
		click(adTxtParagrphPpSaveBtn);
		thread();
		
		getObjectText(msgNotificationBar);
		verifyAssertEquals(drpln.getCell(8, 2).getContents(), getActualObjectTxt);		//	Text/Paragraph added successfully 
		
		getObjectText(tsecfirstText);
		verifyAssertEquals(drpln.getCell(4, 10).getContents(), getActualObjectTxt);		//	Verify Entered Text In Section Page
		
		click(tsecfirstText);
		thread();
		
		//		Edit Text		//
		getObjectText(adTxtAddTextParagraphPpTtl);
		verifyAssertEquals(drpln.getCell(9, 5).getContents(), getActualObjectTxt);		//	Update Text/Paragraph Title
			
		webElement(adTxtEnterSomeTextFld);
		switchToWEFrame(webelementFrame);
		useKeyFuncToRmvVal(scEdtrCommentsField);	//	Edit Some Texts
		enterText(scEdtrCommentsField, drpln.getCell(5, 10).getContents());
		switchBackFromFrame();
			
		click(adTxtParagrphPpSaveBtn);
		thread();
			
		getObjectText(msgNotificationBar);
		verifyAssertEquals(drpln.getCell(8, 3).getContents(), getActualObjectTxt);		//	Text/Paragraph Updated Successfully
			
		getObjectText(tsecfirstText);
		verifyAssertEquals(drpln.getCell(5, 10).getContents(), getActualObjectTxt);		//	Verify Modified Text
		
		
		//		Add Dynamic Data		//
		click(tsecAddDataBtn);
		thread();
		
		verifyAssert(addDynamicDataPopup);
			
		click(dynDtaPpModuleDDArrow);
		enterText(dynDtaPpModuleDDSearchBox, drpln.getCell(11, 10).getContents());		//	Choose Assets module
		enterKeyInKyBord(dynDtaPpModuleDDSearchBox);
		thread();
		
		try
		{
			verifyObjDisplay(dynDtaEmpSelectAllChkBox);
			if(chkObjDisplay == true)
			{
				//	Enable Select All Check Box
				click(dynDtaEmpSelectAllChkBox);						
				getTotalValuesIndd(dynDtaEmpDisplayFldCnt);
				try
				{
					for(int i=1; i<=totalDDValCount; i++)
					{
						boolean checked = driver.findElement(By.xpath("//div[@id='divDDataFields']/span["+i+"]/input")).isSelected();
						Assert.assertEquals(true, checked);
					}
						
					//	Disable Select All Check Box
					click(dynDtaEmpSelectAllChkBox);
					getTotalValuesIndd(dynDtaEmpDisplayFldCnt);
					for(int j=1; j<=totalDDValCount; j++)
					{
						boolean unchecked = driver.findElement(By.xpath("//div[@id='divDDataFields']/span["+j+"]/input")).isSelected();
						Assert.assertEquals(false, unchecked);
					}
				}catch(WebDriverException e3) {
						e3.printStackTrace();
				}
					
				click(tsecdfTaskName);
				click(tsecdfTaskType);
				click(tsecdfTaskID);
				click(tsecdfEmployeeAssigned);
									
				click(dynDtaAddNewFilterBtn);	//	Add New Filter button
					
				click(dynDtaFieldDDArrow);
				enterText(dynDtaFieldDDSearchBox, "Task Name");		//	Choose Filter Field
				enterKeyInKyBord(dynDtaFieldDDSearchBox);
				thread();
				
				//		Choose Filter Value
				click(dynDtaFieldValueDD);
				getTotalValuesIndd(dynDtaFieldValueCnt);
				
				ArrayList<Integer> alist = new ArrayList<Integer>();
				for(int i=1; i<totalDDValCount; i++)
				{
					alist.add(i);
				}
				Collections.shuffle(alist);
				
				for(int i=0; i<2; i++)
				{
					String randomValue = driver.findElement(By.id("ddlDDataNewFilterValues_chzn_o_"+alist.get(i))).getText();
					enterText(dynDtaFieldValueDD, randomValue);
					enterKeyInKyBord(dynDtaFieldValueDD);
					click(dynDtaFilterAddBtn);
				}		
					
				click(dynDtaPpSaveBtn);
				thread();
				
				getObjectText(msgNotificationBar);
				verifyAssertEquals(drpln.getCell(8, 4).getContents(), getActualObjectTxt);		//	Data Created Successfully
				thread();
				
				//		Check Selected Display Order in Table
				for(int i=1; i<5; i++)
				{
					String getColumnName = driver.findElement(By.xpath("//div[@id='divDynamicDRPlanSections']/div[10]//table[@class='table table-responsive table-striped table-bordered bootstrap-datatable dynamic-data-table']//tr/td["+i+"]")).getText();
					if(i == 1)
						verifyAssertEquals(drpln.getCell(12, 33).getContents(), getColumnName);	//	Task Name
					if(i == 2)
						verifyAssertEquals(drpln.getCell(12, 34).getContents(), getColumnName);	//	Task Type
					if(i == 3)
						verifyAssertEquals(drpln.getCell(12, 35).getContents(), getColumnName);	//	Task ID
					if(i == 4)
						verifyAssertEquals(drpln.getCell(12, 36).getContents(), getColumnName);	//	Employee Assigned
				}
				
				click(tsecTeamsList);
				thread();
				
				verifyAssert(addDynamicDataPopup);
				
				for(int i=2; i<4; i++)
				{
					boolean checked = driver.findElement(By.xpath("//div[@id='divDDataFields']/span["+i+"]/input")).isSelected();
					Assert.assertEquals(true, checked);
				}
						
				//	Drag & Drop the objects
				sourceElement(dynDtaEmpWeFirstName);
				destinationElement(dynDtaEmpWeLastName);
				dragAndDrop(sourceObj, destinationObj);		//	Change Task Name & Task Type position				
				
				sourceElement(dynDtaEmpWeMobile);
				destinationElement(dynDtaEmpWeAlternateEmployee);
				dragAndDrop(sourceObj, destinationObj);		//	Change Task ID & Employee Assigned position
				
				//	Verify Added Filter Values
				try
				{
					verifyObjDisplay(dynDtaEmpSelectedFilterValue);
					if(chkObjDisplay == true)
					{
						verifyAssert(dynDtaEmpFilterValue1);
						verifyAssert(dynDtaEmpFilterValue2);
					}
				}catch(WebDriverException e) {
					System.out.println("Selected Filter values are not displaying in Edit screen");
					e.printStackTrace();
				}
				
				click(dynDtaPpSaveBtn);
				thread();
							
				getObjectText(msgNotificationBar);
				verifyAssertEquals(drpln.getCell(8, 5).getContents(), getActualObjectTxt);		//	Data Updated Successfully
				thread();
												
				//		Verify Column Name Order Changes In Table
				for(int i=1; i<5; i++)
				{
					String getColumnName = driver.findElement(By.xpath("//div[@id='divDynamicDRPlanSections']/div[10]//table[@class='table table-responsive table-striped table-bordered bootstrap-datatable dynamic-data-table']//tr/td["+i+"]")).getText();
					if(i == 1)
						verifyAssertEquals(drpln.getCell(12, 34).getContents(), getColumnName);	//	Task Type
					if(i == 2)
						verifyAssertEquals(drpln.getCell(12, 33).getContents(), getColumnName);	//	Task Name
					if(i == 3)
						verifyAssertEquals(drpln.getCell(12, 36).getContents(), getColumnName);	//	Employee Assigned
					if(i == 4)
						verifyAssertEquals(drpln.getCell(12, 35).getContents(), getColumnName);	//	Task ID
				}				
							
				//		Edit Section		//
				click(tsecEditSectionBtn);
				thread();
			
				verifyAssert(createNewSectionPopup);
				getObjectText(secPpCreateNewSectionTtl);
				verifyAssertEquals(drpln.getCell(9, 2).getContents(), getActualObjectTxt);		//	Update Section Pop up Title
			
				getObjectText(secPpSectionTitleDefTxt);
				verifyAssertEquals(tsecSectionTitle, getActualObjectTxt);		//	Verify Selected Section Title Name 	
			
				getObjectText(secPpAuthorizedByDefTxt);
				verifyAssertEquals(tsecAuthorizedBy, getActualObjectTxt);		//	Check Selected Authorized By Name
			
				getObjectText(secPpVerifiedByDefTxt);
				verifyAssertEquals(tsecVerifiedBy, getActualObjectTxt);		//	Check Select Verified By Name
			
				//		Section Title
				//click(secPpSectionTitleDDArrow);
				//getTotalValuesIndd(secPpSectionTitleDDCnt);
				//Random edtSecTitle = new Random();
				//int edtrSecTitle = edtSecTitle.nextInt(totalDDValCount-2)+2;
				//String edtSectionTitle = driver.findElement(By.id("ddlNewDynamicSectionTitle_chzn_o_"+edtrSecTitle)).getText();
				//enterText(secPpSectionTitleDDSearchBox, edtSectionTitle);
				//enterKeyInKyBord(secPpSectionTitleDDSearchBox);
			
				//	Authorized By
				click(secPpAuthorizedByDDArrow);
				getTotalValuesIndd(secPpAuthorizedByDDCnt);
				Random edtAuthBy = new Random();
				int edtrAuthBy = edtAuthBy.nextInt(totalDDValCount-2)+2;
				String edtAuthorizedBy = driver.findElement(By.id("ddlDSAuthorizedBy_chzn_o_"+edtrAuthBy)).getText();
				enterText(secPpAuthorizedByDDSearchBox, edtAuthorizedBy.split(",")[0]);
				enterKeyInKyBord(secPpAuthorizedByDDSearchBox);
			
				//	Verified By
				click(secPpVerifiedByDDArrow);
				getTotalValuesIndd(secPpVerifiedByDDCnt);
				Random edtVeriBy = new Random();
				int edtrVeriBy = edtVeriBy.nextInt(totalDDValCount-2)+2;
				String edtVerifiedBy = driver.findElement(By.id("ddlDSVerifiedBy_chzn_o_"+edtrVeriBy)).getText();
				enterText(secPpVerifiedByDDSearchBox, edtVerifiedBy.split(",")[0]);
				enterKeyInKyBord(secPpVerifiedByDDSearchBox);
				
				click(secPpSaveBtn);
				thread();
				
				//try
				//{
				//	click(secPpSaveBtn);
				//	thread();
				
				//	getObjectText(msgNotificationBar);
				//	verifyAssertEquals(sectionUpdatedSuccessfullyMsg, getActualObjectTxt);		//	Section Updated Successfully
				//}catch(WebDriverException e) {
				//	e.printStackTrace();
				
				//	click(secPpSaveBtn);
					//thread();
				
					//getObjectText(msgNotificationBar);
					//verifyAssertEquals(lsecSectionTitle+sectionTitileNmAlreadyExistsMsg, getActualObjectTxt);		//	Section Title Already Exists
				
					//click(secPpSectionTitleDDArrow);
					//getTotalValuesIndd(secPpSectionTitleDDCnt);
					//Random edtSecTitle1 = new Random();
					//int edtrSecTitle1 = edtSecTitle1.nextInt(totalDDValCount-0)+0;
					//String edtSectionTitle1 = driver.findElement(By.id("ddlNewDynamicSectionTitle_chzn_o_"+edtrSecTitle1)).getText();
					//enterText(secPpSectionTitleDDSearchBox, edtSectionTitle1);
					//enterKeyInKyBord(secPpSectionTitleDDSearchBox);
				
					//click(secPpSaveBtn);
					//thread();
				//}
			
				//		Verify Edited Authorized & Verified By Name
				getObjectText1(drEdtSecAuthorizedByLblNm);
				getObjectText(tsecAuthorizedByNm);
				verifyAssertEquals("Authorized By : "+edtAuthorizedBy, getActualObjectTxt1+" "+getActualObjectTxt);	//	Check Authorized Person Name
			
				getObjectText1(drEdtSecVerifiedByLblNm);
				getObjectText(tsecVerifiedByNm);
				verifyAssertEquals("Verified By : "+edtVerifiedBy, getActualObjectTxt1+" "+getActualObjectTxt);	//	Check Verified Person Name
				thread();
			
				//		Add Text - 2nd Text		//
				click(tsecAddTextBtn);
			
				verifyAssert(adTxtAddTextParagraphPopup);
				getObjectText(adTxtAddTextParagraphPpTtl);
				verifyAssertEquals(drpln.getCell(9, 4).getContents(), getActualObjectTxt);		//	Add Text/Paragraph Pop Up Title
				
				getObjectText(adTxtPpEnterSomeTxtLbl);
				verifyAssertEquals(drpln.getCell(10, 1).getContents(), getActualObjectTxt);		//	Enter Some Text Label Name
				
				webElement(adTxtEnterSomeTextFld);
				switchToWEFrame(webelementFrame);
				enterText(scEdtrCommentsField, drpln.getCell(6, 10).getContents());		//	Enter Some Texts
				switchBackFromFrame();
			
				click(adTxtParagrphPpSaveBtn);
				thread();
			
				getObjectText(msgNotificationBar);
				verifyAssertEquals(drpln.getCell(8, 2).getContents(), getActualObjectTxt);		//	Text/Paragraph added successfully 
			
				webElement(tsec2ndTxt); 
				scrollInnerScrollBar(webelementFrame);		//	Scrolling Section Grid Inner Scroll Bar	
			
				getObjectText(tsec2ndTxt);
				verifyAssertEquals(drpln.getCell(6, 10).getContents(), getActualObjectTxt);		//	Verify Entered Text In Section Page
				thread();
			
				//		Mouse Hover - Move Last
				webElement(tsecmHovrOnFirstOptn);
				mouseHoverOn(webelementFrame);
				click(tsecmoveLastIcon);				
				getObjectText(tsec2ndTxt); 
				verifyAssertEquals(drpln.getCell(5, 10).getContents(), getActualObjectTxt);
				thread();
			
				//	Mouse Hover - Move Top
				webElement(tsecmHovrOnSecOptn);
				mouseHoverOn(webelementFrame);
				thread();
				webElement(tsecmoveTopIcon);
				mouseHoverOn(webelementFrame);
				click(tsecmoveTopIcon);				
				getObjectText(tsecfirstText);
				verifyAssertEquals(drpln.getCell(6, 10).getContents(), getActualObjectTxt);	
				thread();
			
				//	Mouse Hover - Move Down
				webElement(tsecmHovrOnFirstOptn);
				mouseHoverOn(webelementFrame);
				click(tsecmoveDownIcon);				
				getObjectText(tsec2ndTxt1);
				verifyAssertEquals(drpln.getCell(6, 10).getContents(), getActualObjectTxt);
				thread();
			
				//	Mouse Hover - Move Up
				webElement(tsecmHovrOnSecOptn);
				mouseHoverOn(webelementFrame);
				click(tsecmoveUpIcon);
				getObjectText(tsecfirstText);
				verifyAssertEquals(drpln.getCell(6, 10).getContents(), getActualObjectTxt);
				thread();
			
				//	Mouse Hover - Delete
				webElement(tsecmHovrOnThirdOptn);
				mouseHoverOn(webelementFrame);
				click(tsecremoveIcon);
			
				verifyAssert(txtDeleteConfPopup);
				getObjectText(deleteConfPpMessage);
				verifyAssertEquals(drpln.getCell(8, 6).getContents(), getActualObjectTxt);
				click(delConfOkBtn);
				thread();	
			}
			}catch(WebDriverException e1) {
				System.out.println("Display fields are not displaying");
				e1.printStackTrace();
			}
			thread();
		}
	
	@Test(priority=13)
	void addTaskGroupsSection() throws InterruptedException, BiffException, IOException
	{
		getDRPlanSheetFromExcel();
		 
		click(drEdtSecCreateNewSectionBtn);
		verifyAssert(createNewSectionPopup);
		
		//	Section Title
		click(secPpSectionTitleDDArrow);
		String tgsecSectionTitle = drpln.getCell(1, 11).getContents();		//	Add New Section Name (or) Choose Existing Section Name
		enterText(secPpSectionTitleDDSearchBox, tgsecSectionTitle);
		
		try
		{
			verifyObjDisplay(clickToAddTxtInDD);
			if(chkObjDisplay == true)
			{
				click(clickToAddTxtInDD);
				enterKeyInKyBord(secPpSectionTitleDDSearchBox);
				verifyAssert(sectionAddConfPopup);
				getObjectText(deleteConfPpMessage); 
				verifyAssertEquals("Do you want to add "+drpln.getCell(1, 11).getContents()+" into Section Title ?", getActualObjectTxt);
				click(delConfOkBtn);
				thread();
				
				getObjectText(msgNotificationBar);
				verifyAssertEquals(drpln.getCell(1, 11).getContents()+" added into Section Title successfully", getActualObjectTxt);		//	Check New Section Added Successfully
				thread();
				
				getObjectText(secPpSectionTitleDefTxt);
				verifyAssertEquals(drpln.getCell(1, 11).getContents(), getActualObjectTxt);		//	Verify Added Title In Drop Down
			}
		}catch(WebDriverException e){
			arrowDown(secPpSectionTitleDDSearchBox);
			enterKeyInKyBord(secPpSectionTitleDDSearchBox);
		}
		
		//	Authorized By
		click(secPpAuthorizedByDDArrow);
		getTotalValuesIndd(secPpAuthorizedByDDCnt);
		Random authBy = new Random();
		int rauthBy = authBy.nextInt(totalDDValCount-2)+2;
		String tgsecAuthorizedBy = driver.findElement(By.id("ddlDSAuthorizedBy_chzn_o_"+rauthBy)).getText();
		enterText(secPpAuthorizedByDDSearchBox, tgsecAuthorizedBy.split(",")[0]);
		enterKeyInKyBord(secPpAuthorizedByDDSearchBox);
		
		//	Verified By
		click(secPpVerifiedByDDArrow);
		getTotalValuesIndd(secPpVerifiedByDDCnt);
		Random veriBy = new Random();
		int rveriBy = veriBy.nextInt(totalDDValCount-2)+2;
		String tgsecVerifiedBy = driver.findElement(By.id("ddlDSVerifiedBy_chzn_o_"+rveriBy)).getText();
		enterText(secPpVerifiedByDDSearchBox, tgsecVerifiedBy.split(",")[0]);
		enterKeyInKyBord(secPpVerifiedByDDSearchBox);
		
		//	Description
		webElement(crSectionDescription);
		switchToWEFrame(webelementFrame);
		enterText(drscEdtrCommentsField, drpln.getCell(2, 11).getContents());
		switchBackFromFrame();
		takeScreenshot();
		
		click(secPpSaveBtn);
		thread();
			
		getObjectText(msgNotificationBar);
		verifyAssertEquals(drpln.getCell(8, 7).getContents(), getActualObjectTxt);		//	Section Created Successfully	
		
		getObjectText(tgsecDescription);
		verifyAssertEquals(drpln.getCell(2, 11).getContents(), getActualObjectTxt);		//	Check Added Section Description
		
		click(tgsecDescription);
		thread();
		
		//		Edit Description		//
		verifyAssert(createNewSectionPopup);
		getObjectText(secPpCreateNewSectionTtl);
		verifyAssertEquals(drpln.getCell(9, 3).getContents(), getActualObjectTxt);		//	Check Update Description Title
		
		webElement(crSectionDescription);
		switchToWEFrame(webelementFrame);
		useKeyFuncToRmvVal(drscEdtrCommentsField);
		enterText(drscEdtrCommentsField, drpln.getCell(3, 11).getContents());
		switchBackFromFrame();
		
		click(secPpSaveBtn);
		thread();
		
		getObjectText(msgNotificationBar);
		verifyAssertEquals(drpln.getCell(8, 1).getContents(), getActualObjectTxt);		//	Section Updated Successfully
		thread();	
		
		getObjectText(tgsecDescription);
		verifyAssertEquals(drpln.getCell(3, 11).getContents(), getActualObjectTxt);		//	Verify Edited Description Details
		
		
		//		Authorized & Verified By Person Names		//
		getObjectText1(lsecLblAuthorizedBy);
		getObjectText(tgsecAuthorizedByNm);
		verifyAssertEquals("Authorized By : "+tgsecAuthorizedBy, getActualObjectTxt1+" "+getActualObjectTxt);	//	Check Authorized Person Name
		
		getObjectText1(lsecLblVerifiedBy);
		getObjectText(tgsecVerifiedByNm);
		verifyAssertEquals("Verified By : "+tgsecVerifiedBy, getActualObjectTxt1+" "+getActualObjectTxt);	//	Check Verified Person Name
		thread();
		
		
		//		Add Text/Paragraph		//
		click(tgsecAddTextBtn);
		
		verifyAssert(adTxtAddTextParagraphPopup);
		getObjectText(adTxtAddTextParagraphPpTtl);
		verifyAssertEquals(drpln.getCell(9, 4).getContents(), getActualObjectTxt);		//	Add Text/Paragraph Pop Up Title
			
		getObjectText(adTxtPpEnterSomeTxtLbl);
		verifyAssertEquals(drpln.getCell(10, 1).getContents(), getActualObjectTxt);	//	Enter Some Text Label Name
			
		webElement(adTxtEnterSomeTextFld);
		switchToWEFrame(webelementFrame);
		enterText(scEdtrCommentsField, drpln.getCell(4, 11).getContents());		//	Enter Some Texts
		switchBackFromFrame();
		
		click(adTxtParagrphPpSaveBtn);
		thread();
		
		getObjectText(msgNotificationBar);
		verifyAssertEquals(drpln.getCell(8, 2).getContents(), getActualObjectTxt);		//	Text/Paragraph added successfully 
		
		getObjectText(tgsecfirstText);
		verifyAssertEquals(drpln.getCell(4, 11).getContents(), getActualObjectTxt);		//	Verify Entered Text In Section Page
		
		click(tgsecfirstText);
		thread();
		
		//		Edit Text		//
		getObjectText(adTxtAddTextParagraphPpTtl);
		verifyAssertEquals(drpln.getCell(9, 5).getContents(), getActualObjectTxt);		//	Update Text/Paragraph Title
			
		webElement(adTxtEnterSomeTextFld);
		switchToWEFrame(webelementFrame);
		useKeyFuncToRmvVal(scEdtrCommentsField);	//	Edit Some Texts
		enterText(scEdtrCommentsField, drpln.getCell(5, 5).getContents());
		switchBackFromFrame();
			
		click(adTxtParagrphPpSaveBtn);
		thread();
			
		getObjectText(msgNotificationBar);
		verifyAssertEquals(drpln.getCell(8, 3).getContents(), getActualObjectTxt);		//	Text/Paragraph Updated Successfully
			
		getObjectText(tgsecfirstText);
		verifyAssertEquals(drpln.getCell(5, 11).getContents(), getActualObjectTxt);		//	Verify Modified Text
		
		
		//		Add Dynamic Data		//
		click(tgsecAddDataBtn);
		thread();
		
		verifyAssert(addDynamicDataPopup);
			
		click(dynDtaPpModuleDDArrow);
		enterText(dynDtaPpModuleDDSearchBox, drpln.getCell(11, 11).getContents());		//	Choose Assets module
		enterKeyInKyBord(dynDtaPpModuleDDSearchBox);
		thread();
		
		try
		{
			verifyObjDisplay(dynDtaEmpSelectAllChkBox);
			if(chkObjDisplay == true)
			{
				//	Enable Select All Check Box
				click(dynDtaEmpSelectAllChkBox);						
				getTotalValuesIndd(dynDtaEmpDisplayFldCnt);
				try
				{
					for(int i=1; i<=totalDDValCount; i++)
					{
						boolean checked = driver.findElement(By.xpath("//div[@id='divDDataFields']/span["+i+"]/input")).isSelected();
						Assert.assertEquals(true, checked);
					}
						
					//	Disable Select All Check Box
					click(dynDtaEmpSelectAllChkBox);
					getTotalValuesIndd(dynDtaEmpDisplayFldCnt);
					for(int j=1; j<=totalDDValCount; j++)
					{
						boolean unchecked = driver.findElement(By.xpath("//div[@id='divDDataFields']/span["+j+"]/input")).isSelected();
						Assert.assertEquals(false, unchecked);
					}
				}catch(WebDriverException e3) {
						e3.printStackTrace();
				}
					
				click(tgsecdfTaskGroupName);
				click(tgsecdfSummary);
													
				click(dynDtaAddNewFilterBtn);	//	Add New Filter button
					
				click(dynDtaFieldDDArrow);
				enterText(dynDtaFieldDDSearchBox, "Task Group Name");		//	Choose Filter Field
				enterKeyInKyBord(dynDtaFieldDDSearchBox);
				thread();
				
				//		Choose Filter Value
				click(dynDtaFieldValueDD);
				getTotalValuesIndd(dynDtaFieldValueCnt);
				
				ArrayList<Integer> alist = new ArrayList<Integer>();
				for(int i=1; i<totalDDValCount; i++)
				{
					alist.add(i);
				}
				Collections.shuffle(alist);
				
				for(int i=0; i<2; i++)
				{
					String randomValue = driver.findElement(By.id("ddlDDataNewFilterValues_chzn_o_"+alist.get(i))).getText();
					enterText(dynDtaFieldValueDD, randomValue);
					enterKeyInKyBord(dynDtaFieldValueDD);
					click(dynDtaFilterAddBtn);
				}		
					
				click(dynDtaPpSaveBtn);
				thread();
				
				getObjectText(msgNotificationBar);
				verifyAssertEquals(drpln.getCell(8, 4).getContents(), getActualObjectTxt);		//	Data Created Successfully
				thread();
				
				//		Check Selected Display Order in Table
				for(int i=1; i<3; i++)
				{
					String getColumnName = driver.findElement(By.xpath("//div[@id='divDynamicDRPlanSections']/div[11]//table[@class='table table-responsive table-striped table-bordered bootstrap-datatable dynamic-data-table']//tr/td["+i+"]")).getText();
					if(i == 1)
						verifyAssertEquals(drpln.getCell(12, 37).getContents(), getColumnName);	//	Task Group Name
					if(i == 2)
						verifyAssertEquals(drpln.getCell(12, 38).getContents(), getColumnName);	//	Summary
				}
				
				click(tgsecTeamsList);
				thread();
				
				verifyAssert(addDynamicDataPopup);
				
				for(int i=2; i<4; i++)
				{
					boolean checked = driver.findElement(By.xpath("//div[@id='divDDataFields']/span["+i+"]/input")).isSelected();
					Assert.assertEquals(true, checked);
				}
						
				//	Drag & Drop the objects
				sourceElement(dynDtaEmpWeFirstName);
				destinationElement(dynDtaEmpWeLastName);
				dragAndDrop(sourceObj, destinationObj);		//	Change Task Group Name & Summary position				
				
				//	Verify Added Filter Values
				try
				{
					verifyObjDisplay(dynDtaEmpSelectedFilterValue);
					if(chkObjDisplay == true)
					{
						verifyAssert(dynDtaEmpFilterValue1);
						verifyAssert(dynDtaEmpFilterValue2);
					}
				}catch(WebDriverException e) {
					System.out.println("Selected Filter values are not displaying in Edit screen");
					e.printStackTrace();
				}
				
				click(dynDtaPpSaveBtn);
				thread();
							
				getObjectText(msgNotificationBar);
				verifyAssertEquals(drpln.getCell(8, 5).getContents(), getActualObjectTxt);		//	Data Updated Successfully
				thread();
												
				//		Verify Column Name Order Changes In Table
				for(int i=1; i<3; i++)
				{
					String getColumnName = driver.findElement(By.xpath("//div[@id='divDynamicDRPlanSections']/div[11]//table[@class='table table-responsive table-striped table-bordered bootstrap-datatable dynamic-data-table']//tr/td["+i+"]")).getText();
					if(i == 1)
						verifyAssertEquals(drpln.getCell(12, 38).getContents(), getColumnName);	//	Summary
					if(i == 2)
						verifyAssertEquals(drpln.getCell(12, 37).getContents(), getColumnName);	//	Task Group Name					
				}				
							
				//		Edit Section		//
				click(tgsecEditSectionBtn);
				thread();
			
				verifyAssert(createNewSectionPopup);
				getObjectText(secPpCreateNewSectionTtl);
				verifyAssertEquals(drpln.getCell(9, 2).getContents(), getActualObjectTxt);		//	Update Section Pop up Title
			
				getObjectText(secPpSectionTitleDefTxt);
				verifyAssertEquals(tgsecSectionTitle, getActualObjectTxt);		//	Verify Selected Section Title Name 	
			
				getObjectText(secPpAuthorizedByDefTxt);
				verifyAssertEquals(tgsecAuthorizedBy, getActualObjectTxt);		//	Check Selected Authorized By Name
			
				getObjectText(secPpVerifiedByDefTxt);
				verifyAssertEquals(tgsecVerifiedBy, getActualObjectTxt);		//	Check Select Verified By Name
			
				//		Section Title
				//click(secPpSectionTitleDDArrow);
				//getTotalValuesIndd(secPpSectionTitleDDCnt);
				//Random edtSecTitle = new Random();
				//int edtrSecTitle = edtSecTitle.nextInt(totalDDValCount-2)+2;
				//String edtSectionTitle = driver.findElement(By.id("ddlNewDynamicSectionTitle_chzn_o_"+edtrSecTitle)).getText();
				//enterText(secPpSectionTitleDDSearchBox, edtSectionTitle);
				//enterKeyInKyBord(secPpSectionTitleDDSearchBox);
			
				//	Authorized By
				click(secPpAuthorizedByDDArrow);
				getTotalValuesIndd(secPpAuthorizedByDDCnt);
				Random edtAuthBy = new Random();
				int edtrAuthBy = edtAuthBy.nextInt(totalDDValCount-2)+2;
				String edtAuthorizedBy = driver.findElement(By.id("ddlDSAuthorizedBy_chzn_o_"+edtrAuthBy)).getText();
				enterText(secPpAuthorizedByDDSearchBox, edtAuthorizedBy.split(",")[0]);
				enterKeyInKyBord(secPpAuthorizedByDDSearchBox);
			
				//	Verified By
				click(secPpVerifiedByDDArrow);
				getTotalValuesIndd(secPpVerifiedByDDCnt);
				Random edtVeriBy = new Random();
				int edtrVeriBy = edtVeriBy.nextInt(totalDDValCount-2)+2;
				String edtVerifiedBy = driver.findElement(By.id("ddlDSVerifiedBy_chzn_o_"+edtrVeriBy)).getText();
				enterText(secPpVerifiedByDDSearchBox, edtVerifiedBy.split(",")[0]);
				enterKeyInKyBord(secPpVerifiedByDDSearchBox);
				
				click(secPpSaveBtn);
				thread();
				
				//try
				//{
				//	click(secPpSaveBtn);
				//	thread();
				
				//	getObjectText(msgNotificationBar);
				//	verifyAssertEquals(sectionUpdatedSuccessfullyMsg, getActualObjectTxt);		//	Section Updated Successfully
				//}catch(WebDriverException e) {
				//	e.printStackTrace();
				
				//	click(secPpSaveBtn);
					//thread();
				
					//getObjectText(msgNotificationBar);
					//verifyAssertEquals(lsecSectionTitle+sectionTitileNmAlreadyExistsMsg, getActualObjectTxt);		//	Section Title Already Exists
				
					//click(secPpSectionTitleDDArrow);
					//getTotalValuesIndd(secPpSectionTitleDDCnt);
					//Random edtSecTitle1 = new Random();
					//int edtrSecTitle1 = edtSecTitle1.nextInt(totalDDValCount-0)+0;
					//String edtSectionTitle1 = driver.findElement(By.id("ddlNewDynamicSectionTitle_chzn_o_"+edtrSecTitle1)).getText();
					//enterText(secPpSectionTitleDDSearchBox, edtSectionTitle1);
					//enterKeyInKyBord(secPpSectionTitleDDSearchBox);
				
					//click(secPpSaveBtn);
					//thread();
				//}
			
				//		Verify Edited Authorized & Verified By Name
				getObjectText1(drEdtSecAuthorizedByLblNm);
				getObjectText(tgsecAuthorizedByNm);
				verifyAssertEquals("Authorized By : "+edtAuthorizedBy, getActualObjectTxt1+" "+getActualObjectTxt);	//	Check Authorized Person Name
			
				getObjectText1(drEdtSecVerifiedByLblNm);
				getObjectText(tgsecVerifiedByNm);
				verifyAssertEquals("Verified By : "+edtVerifiedBy, getActualObjectTxt1+" "+getActualObjectTxt);	//	Check Verified Person Name
				thread();
			
				//		Add Text - 2nd Text		//
				click(tgsecAddTextBtn);
			
				verifyAssert(adTxtAddTextParagraphPopup);
				getObjectText(adTxtAddTextParagraphPpTtl);
				verifyAssertEquals(drpln.getCell(9, 4).getContents(), getActualObjectTxt);		//	Add Text/Paragraph Pop Up Title
				
				getObjectText(adTxtPpEnterSomeTxtLbl);
				verifyAssertEquals(drpln.getCell(10, 1).getContents(), getActualObjectTxt);		//	Enter Some Text Label Name
				
				webElement(adTxtEnterSomeTextFld);
				switchToWEFrame(webelementFrame);
				enterText(scEdtrCommentsField, drpln.getCell(6, 11).getContents());		//	Enter Some Texts
				switchBackFromFrame();
			
				click(adTxtParagrphPpSaveBtn);
				thread();
			
				getObjectText(msgNotificationBar);
				verifyAssertEquals(drpln.getCell(8, 2).getContents(), getActualObjectTxt);		//	Text/Paragraph added successfully 
			
				webElement(tgsec2ndTxt); 
				scrollInnerScrollBar(webelementFrame);		//	Scrolling Section Grid Inner Scroll Bar	
			
				getObjectText(tgsec2ndTxt);
				verifyAssertEquals(drpln.getCell(6, 11).getContents(), getActualObjectTxt);		//	Verify Entered Text In Section Page
				thread();
			
				//		Mouse Hover - Move Last
				webElement(tgsecmHovrOnFirstOptn);
				mouseHoverOn(webelementFrame);
				click(tgsecmoveLastIcon);				
				getObjectText(tgsec2ndTxt); 
				verifyAssertEquals(drpln.getCell(5, 11).getContents(), getActualObjectTxt);
				thread();
			
				//	Mouse Hover - Move Top
				webElement(tgsecmHovrOnSecOptn);
				mouseHoverOn(webelementFrame);
				thread();
				webElement(tgsecmoveTopIcon);
				mouseHoverOn(webelementFrame);
				click(tgsecmoveTopIcon);				
				getObjectText(tgsecfirstText);
				verifyAssertEquals(drpln.getCell(6, 11).getContents(), getActualObjectTxt);	
				thread();
			
				//	Mouse Hover - Move Down
				webElement(tgsecmHovrOnFirstOptn);
				mouseHoverOn(webelementFrame);
				click(tgsecmoveDownIcon);				
				getObjectText(tgsec2ndTxt1);
				verifyAssertEquals(drpln.getCell(6, 11).getContents(), getActualObjectTxt);
				thread();
			
				//	Mouse Hover - Move Up
				webElement(tgsecmHovrOnSecOptn);
				mouseHoverOn(webelementFrame);
				click(tgsecmoveUpIcon);
				getObjectText(tgsecfirstText);
				verifyAssertEquals(drpln.getCell(6, 11).getContents(), getActualObjectTxt);
				thread();
			
				//	Mouse Hover - Delete
				webElement(tgsecmHovrOnThirdOptn);
				mouseHoverOn(webelementFrame);
				click(tgsecremoveIcon);
			
				verifyAssert(txtDeleteConfPopup);
				getObjectText(deleteConfPpMessage);
				verifyAssertEquals(drpln.getCell(8, 6).getContents(), getActualObjectTxt);
				click(delConfOkBtn);
				thread();	
			}
			}catch(WebDriverException e1) {
				System.out.println("Display fields are not displaying");
				e1.printStackTrace();
			}
			thread();
		}
	
	@Test(priority=14)
	void addBusinessFunctionsSection() throws InterruptedException, BiffException, IOException
	{
		getDRPlanSheetFromExcel();
		 
		click(drEdtSecCreateNewSectionBtn);
		verifyAssert(createNewSectionPopup);
		
		//	Section Title
		click(secPpSectionTitleDDArrow);
		String bfsecSectionTitle = drpln.getCell(1, 12).getContents();		//	Add New Section Name (or) Choose Existing Section Name
		enterText(secPpSectionTitleDDSearchBox, bfsecSectionTitle);
		
		try
		{
			verifyObjDisplay(clickToAddTxtInDD);
			if(chkObjDisplay == true)
			{
				click(clickToAddTxtInDD);
				enterKeyInKyBord(secPpSectionTitleDDSearchBox);
				verifyAssert(sectionAddConfPopup);
				getObjectText(deleteConfPpMessage); 
				verifyAssertEquals("Do you want to add "+drpln.getCell(1, 12).getContents()+" into Section Title ?", getActualObjectTxt);
				click(delConfOkBtn);
				thread();
				
				getObjectText(msgNotificationBar);
				verifyAssertEquals(drpln.getCell(1, 12).getContents()+" added into Section Title successfully", getActualObjectTxt);		//	Check New Section Added Successfully
				thread();
				
				getObjectText(secPpSectionTitleDefTxt);
				verifyAssertEquals(drpln.getCell(1, 12).getContents(), getActualObjectTxt);		//	Verify Added Title In Drop Down
			}
		}catch(WebDriverException e){
			arrowDown(secPpSectionTitleDDSearchBox);
			enterKeyInKyBord(secPpSectionTitleDDSearchBox);
		}
		
		//	Authorized By
		click(secPpAuthorizedByDDArrow);
		getTotalValuesIndd(secPpAuthorizedByDDCnt);
		Random authBy = new Random();
		int rauthBy = authBy.nextInt(totalDDValCount-2)+2;
		String bfsecAuthorizedBy = driver.findElement(By.id("ddlDSAuthorizedBy_chzn_o_"+rauthBy)).getText();
		enterText(secPpAuthorizedByDDSearchBox, bfsecAuthorizedBy.split(",")[0]);
		enterKeyInKyBord(secPpAuthorizedByDDSearchBox);
		
		//	Verified By
		click(secPpVerifiedByDDArrow);
		getTotalValuesIndd(secPpVerifiedByDDCnt);
		Random veriBy = new Random();
		int rveriBy = veriBy.nextInt(totalDDValCount-2)+2;
		String bfsecVerifiedBy = driver.findElement(By.id("ddlDSVerifiedBy_chzn_o_"+rveriBy)).getText();
		enterText(secPpVerifiedByDDSearchBox, bfsecVerifiedBy.split(",")[0]);
		enterKeyInKyBord(secPpVerifiedByDDSearchBox);
		
		//	Description
		webElement(crSectionDescription);
		switchToWEFrame(webelementFrame);
		enterText(drscEdtrCommentsField, drpln.getCell(2, 12).getContents());
		switchBackFromFrame();
		takeScreenshot();
		
		click(secPpSaveBtn);
		thread();
			
		getObjectText(msgNotificationBar);
		verifyAssertEquals(drpln.getCell(8, 7).getContents(), getActualObjectTxt);		//	Section Created Successfully	
		
		getObjectText(bfsecDescription);
		verifyAssertEquals(drpln.getCell(2, 12).getContents(), getActualObjectTxt);		//	Check Added Section Description
		
		click(asecDescription);
		thread();
		
		//		Edit Description		//
		verifyAssert(createNewSectionPopup);
		getObjectText(secPpCreateNewSectionTtl);
		verifyAssertEquals(drpln.getCell(9, 3).getContents(), getActualObjectTxt);		//	Check Update Description Title
		
		webElement(crSectionDescription);
		switchToWEFrame(webelementFrame);
		useKeyFuncToRmvVal(drscEdtrCommentsField);
		enterText(drscEdtrCommentsField, drpln.getCell(3, 12).getContents());
		switchBackFromFrame();
		
		click(secPpSaveBtn);
		thread();
		
		getObjectText(msgNotificationBar);
		verifyAssertEquals(drpln.getCell(8, 1).getContents(), getActualObjectTxt);		//	Section Updated Successfully
		thread();	
		
		getObjectText(bfsecDescription);
		verifyAssertEquals(drpln.getCell(3, 12).getContents(), getActualObjectTxt);		//	Verify Edited Description Details
		
		
		//		Authorized & Verified By Person Names		//
		getObjectText1(lsecLblAuthorizedBy);
		getObjectText(bfsecAuthorizedByNm);
		verifyAssertEquals("Authorized By : "+bfsecAuthorizedBy, getActualObjectTxt1+" "+getActualObjectTxt);	//	Check Authorized Person Name
		
		getObjectText1(lsecLblVerifiedBy);
		getObjectText(bfsecVerifiedByNm);
		verifyAssertEquals("Verified By : "+bfsecVerifiedBy, getActualObjectTxt1+" "+getActualObjectTxt);	//	Check Verified Person Name
		thread();
		
		
		//		Add Text/Paragraph		//
		click(bfsecAddTextBtn);
		
		verifyAssert(adTxtAddTextParagraphPopup);
		getObjectText(adTxtAddTextParagraphPpTtl);
		verifyAssertEquals(drpln.getCell(9, 4).getContents(), getActualObjectTxt);		//	Add Text/Paragraph Pop Up Title
			
		getObjectText(adTxtPpEnterSomeTxtLbl);
		verifyAssertEquals(drpln.getCell(10, 1).getContents(), getActualObjectTxt);	//	Enter Some Text Label Name
			
		webElement(adTxtEnterSomeTextFld);
		switchToWEFrame(webelementFrame);
		enterText(scEdtrCommentsField, drpln.getCell(4, 12).getContents());		//	Enter Some Texts
		switchBackFromFrame();
		
		click(adTxtParagrphPpSaveBtn);
		thread();
		
		getObjectText(msgNotificationBar);
		verifyAssertEquals(drpln.getCell(8, 2).getContents(), getActualObjectTxt);		//	Text/Paragraph added successfully 
		
		getObjectText(bfsecfirstText);
		verifyAssertEquals(drpln.getCell(4, 12).getContents(), getActualObjectTxt);		//	Verify Entered Text In Section Page
		
		click(bfsecfirstText);
		thread();
		
		//		Edit Text		//
		getObjectText(adTxtAddTextParagraphPpTtl);
		verifyAssertEquals(drpln.getCell(9, 5).getContents(), getActualObjectTxt);		//	Update Text/Paragraph Title
			
		webElement(adTxtEnterSomeTextFld);
		switchToWEFrame(webelementFrame);
		useKeyFuncToRmvVal(scEdtrCommentsField);	//	Edit Some Texts
		enterText(scEdtrCommentsField, drpln.getCell(5, 12).getContents());
		switchBackFromFrame();
			
		click(adTxtParagrphPpSaveBtn);
		thread();
			
		getObjectText(msgNotificationBar);
		verifyAssertEquals(drpln.getCell(8, 3).getContents(), getActualObjectTxt);		//	Text/Paragraph Updated Successfully
			
		getObjectText(bfsecfirstText);
		verifyAssertEquals(drpln.getCell(5, 12).getContents(), getActualObjectTxt);		//	Verify Modified Text
		
		
		//		Add Dynamic Data		//
		click(bfsecAddDataBtn);
		thread();
		
		verifyAssert(addDynamicDataPopup);
			
		click(dynDtaPpModuleDDArrow);
		enterText(dynDtaPpModuleDDSearchBox, drpln.getCell(11, 12).getContents());		//	Choose Assets module
		enterKeyInKyBord(dynDtaPpModuleDDSearchBox);
		thread();
		
		try
		{
			verifyObjDisplay(dynDtaEmpSelectAllChkBox);
			if(chkObjDisplay == true)
			{
				//	Enable Select All Check Box
				click(dynDtaEmpSelectAllChkBox);						
				getTotalValuesIndd(dynDtaEmpDisplayFldCnt);
				try
				{
					for(int i=1; i<=totalDDValCount; i++)
					{
						boolean checked = driver.findElement(By.xpath("//div[@id='divDDataFields']/span["+i+"]/input")).isSelected();
						Assert.assertEquals(true, checked);
					}
						
					//	Disable Select All Check Box
					click(dynDtaEmpSelectAllChkBox);
					getTotalValuesIndd(dynDtaEmpDisplayFldCnt);
					for(int j=1; j<=totalDDValCount; j++)
					{
						boolean unchecked = driver.findElement(By.xpath("//div[@id='divDDataFields']/span["+j+"]/input")).isSelected();
						Assert.assertEquals(false, unchecked);
					}
				}catch(WebDriverException e3) {
						e3.printStackTrace();
				}
					
				click(bfsecdfFunctionName);
				click(bfsecdfPriority);
				click(bfsecdfEmployeeAssigned);
				click(bfsecdfLossperDay);
									
				click(dynDtaAddNewFilterBtn);	//	Add New Filter button
					
				click(dynDtaFieldDDArrow);
				enterText(dynDtaFieldDDSearchBox, "Function Name");		//	Choose Filter Field
				enterKeyInKyBord(dynDtaFieldDDSearchBox);
				thread();
				
				//		Choose Filter Value
				click(dynDtaFieldValueDD);
				getTotalValuesIndd(dynDtaFieldValueCnt);
				
				ArrayList<Integer> alist = new ArrayList<Integer>();
				for(int i=1; i<totalDDValCount; i++)
				{
					alist.add(i);
				}
				Collections.shuffle(alist);
				
				for(int i=0; i<2; i++)
				{
					String randomValue = driver.findElement(By.id("ddlDDataNewFilterValues_chzn_o_"+alist.get(i))).getText();
					enterText(dynDtaFieldValueDD, randomValue);
					enterKeyInKyBord(dynDtaFieldValueDD);
					click(dynDtaFilterAddBtn);
				}		
					
				click(dynDtaPpSaveBtn);
				thread();
				
				getObjectText(msgNotificationBar);
				verifyAssertEquals(drpln.getCell(8, 4).getContents(), getActualObjectTxt);		//	Data Created Successfully
				thread();
				
				//		Check Selected Display Order in Table
				for(int i=1; i<5; i++)
				{
					String getColumnName = driver.findElement(By.xpath("//div[@id='divDynamicDRPlanSections']/div[12]//table[@class='table table-responsive table-striped table-bordered bootstrap-datatable dynamic-data-table']//tr/td["+i+"]")).getText();
					if(i == 1)
						verifyAssertEquals(drpln.getCell(12, 39).getContents(), getColumnName);	//	Function Name
					if(i == 2)
						verifyAssertEquals(drpln.getCell(12, 40).getContents(), getColumnName);	//	Priority
					if(i == 3)
						verifyAssertEquals(drpln.getCell(12, 36).getContents(), getColumnName);	//	Employee Assigned
					if(i == 4)
						verifyAssertEquals(drpln.getCell(12, 41).getContents(), getColumnName);	//	Loss Per Day
				}
				
				click(bfsecTeamsList);
				thread();
				
				verifyAssert(addDynamicDataPopup);
				
				for(int i=2; i<4; i++)
				{
					boolean checked = driver.findElement(By.xpath("//div[@id='divDDataFields']/span["+i+"]/input")).isSelected();
					Assert.assertEquals(true, checked);
				}
						
				//	Drag & Drop the objects
				sourceElement(dynDtaEmpWeFirstName);
				destinationElement(dynDtaEmpWeLastName);
				dragAndDrop(sourceObj, destinationObj);		//	Change Priority & Function Name position				
				
				sourceElement(dynDtaEmpWeMobile);
				destinationElement(dynDtaEmpWeAlternateEmployee);
				dragAndDrop(sourceObj, destinationObj);		//	Change Employee Assigned & Loss Per Day position
				
				//	Verify Added Filter Values
				try
				{
					verifyObjDisplay(dynDtaEmpSelectedFilterValue);
					if(chkObjDisplay == true)
					{
						verifyAssert(dynDtaEmpFilterValue1);
						verifyAssert(dynDtaEmpFilterValue2);
					}
				}catch(WebDriverException e) {
					System.out.println("Selected Filter values are not displaying in Edit screen");
					e.printStackTrace();
				}
				
				click(dynDtaPpSaveBtn);
				thread();
							
				getObjectText(msgNotificationBar);
				verifyAssertEquals(drpln.getCell(8, 5).getContents(), getActualObjectTxt);		//	Data Updated Successfully
				thread();
												
				//		Verify Column Name Order Changes In Table
				for(int i=1; i<5; i++)
				{
					String getColumnName = driver.findElement(By.xpath("//div[@id='divDynamicDRPlanSections']/div[12]//table[@class='table table-responsive table-striped table-bordered bootstrap-datatable dynamic-data-table']//tr/td["+i+"]")).getText();
					if(i == 1)
						verifyAssertEquals(drpln.getCell(12, 40).getContents(), getColumnName);	//	Priority
					if(i == 2)
						verifyAssertEquals(drpln.getCell(12, 39).getContents(), getColumnName);	//	Function Name
					if(i == 3)
						verifyAssertEquals(drpln.getCell(12, 41).getContents(), getColumnName);	//	Loss Per Day
					if(i == 4)
						verifyAssertEquals(drpln.getCell(12, 36).getContents(), getColumnName);	//	Employee Assigned
				}				
							
				//		Edit Section		//
				click(bfsecEditSectionBtn);
				thread();
			
				verifyAssert(createNewSectionPopup);
				getObjectText(secPpCreateNewSectionTtl);
				verifyAssertEquals(drpln.getCell(9, 2).getContents(), getActualObjectTxt);		//	Update Section Pop up Title
			
				getObjectText(secPpSectionTitleDefTxt);
				verifyAssertEquals(bfsecSectionTitle, getActualObjectTxt);		//	Verify Selected Section Title Name 	
			
				getObjectText(secPpAuthorizedByDefTxt);
				verifyAssertEquals(bfsecAuthorizedBy, getActualObjectTxt);		//	Check Selected Authorized By Name
			
				getObjectText(secPpVerifiedByDefTxt);
				verifyAssertEquals(bfsecVerifiedBy, getActualObjectTxt);		//	Check Select Verified By Name
			
				//		Section Title
				//click(secPpSectionTitleDDArrow);
				//getTotalValuesIndd(secPpSectionTitleDDCnt);
				//Random edtSecTitle = new Random();
				//int edtrSecTitle = edtSecTitle.nextInt(totalDDValCount-2)+2;
				//String edtSectionTitle = driver.findElement(By.id("ddlNewDynamicSectionTitle_chzn_o_"+edtrSecTitle)).getText();
				//enterText(secPpSectionTitleDDSearchBox, edtSectionTitle);
				//enterKeyInKyBord(secPpSectionTitleDDSearchBox);
			
				//	Authorized By
				click(secPpAuthorizedByDDArrow);
				getTotalValuesIndd(secPpAuthorizedByDDCnt);
				Random edtAuthBy = new Random();
				int edtrAuthBy = edtAuthBy.nextInt(totalDDValCount-2)+2;
				String edtAuthorizedBy = driver.findElement(By.id("ddlDSAuthorizedBy_chzn_o_"+edtrAuthBy)).getText();
				enterText(secPpAuthorizedByDDSearchBox, edtAuthorizedBy.split(",")[0]);
				enterKeyInKyBord(secPpAuthorizedByDDSearchBox);
			
				//	Verified By
				click(secPpVerifiedByDDArrow);
				getTotalValuesIndd(secPpVerifiedByDDCnt);
				Random edtVeriBy = new Random();
				int edtrVeriBy = edtVeriBy.nextInt(totalDDValCount-2)+2;
				String edtVerifiedBy = driver.findElement(By.id("ddlDSVerifiedBy_chzn_o_"+edtrVeriBy)).getText();
				enterText(secPpVerifiedByDDSearchBox, edtVerifiedBy.split(",")[0]);
				enterKeyInKyBord(secPpVerifiedByDDSearchBox);
				
				click(secPpSaveBtn);
				thread();
				
				//try
				//{
				//	click(secPpSaveBtn);
				//	thread();
				
				//	getObjectText(msgNotificationBar);
				//	verifyAssertEquals(sectionUpdatedSuccessfullyMsg, getActualObjectTxt);		//	Section Updated Successfully
				//}catch(WebDriverException e) {
				//	e.printStackTrace();
				
				//	click(secPpSaveBtn);
					//thread();
				
					//getObjectText(msgNotificationBar);
					//verifyAssertEquals(lsecSectionTitle+sectionTitileNmAlreadyExistsMsg, getActualObjectTxt);		//	Section Title Already Exists
				
					//click(secPpSectionTitleDDArrow);
					//getTotalValuesIndd(secPpSectionTitleDDCnt);
					//Random edtSecTitle1 = new Random();
					//int edtrSecTitle1 = edtSecTitle1.nextInt(totalDDValCount-0)+0;
					//String edtSectionTitle1 = driver.findElement(By.id("ddlNewDynamicSectionTitle_chzn_o_"+edtrSecTitle1)).getText();
					//enterText(secPpSectionTitleDDSearchBox, edtSectionTitle1);
					//enterKeyInKyBord(secPpSectionTitleDDSearchBox);
				
					//click(secPpSaveBtn);
					//thread();
				//}
			
				//		Verify Edited Authorized & Verified By Name
				getObjectText1(drEdtSecAuthorizedByLblNm);
				getObjectText(bfsecAuthorizedByNm);
				verifyAssertEquals("Authorized By : "+edtAuthorizedBy, getActualObjectTxt1+" "+getActualObjectTxt);	//	Check Authorized Person Name
			
				getObjectText1(drEdtSecVerifiedByLblNm);
				getObjectText(bfsecVerifiedByNm);
				verifyAssertEquals("Verified By : "+edtVerifiedBy, getActualObjectTxt1+" "+getActualObjectTxt);	//	Check Verified Person Name
				thread();
			
				//		Add Text - 2nd Text		//
				click(bfsecAddTextBtn);
			
				verifyAssert(adTxtAddTextParagraphPopup);
				getObjectText(adTxtAddTextParagraphPpTtl);
				verifyAssertEquals(drpln.getCell(9, 4).getContents(), getActualObjectTxt);		//	Add Text/Paragraph Pop Up Title
				
				getObjectText(adTxtPpEnterSomeTxtLbl);
				verifyAssertEquals(drpln.getCell(10, 1).getContents(), getActualObjectTxt);		//	Enter Some Text Label Name
				
				webElement(adTxtEnterSomeTextFld);
				switchToWEFrame(webelementFrame);
				enterText(scEdtrCommentsField, drpln.getCell(6, 12).getContents());		//	Enter Some Texts
				switchBackFromFrame();
			
				click(adTxtParagrphPpSaveBtn);
				thread();
			
				getObjectText(msgNotificationBar);
				verifyAssertEquals(drpln.getCell(8, 2).getContents(), getActualObjectTxt);		//	Text/Paragraph added successfully 
			
				webElement(bfsec2ndTxt); 
				scrollInnerScrollBar(webelementFrame);		//	Scrolling Section Grid Inner Scroll Bar	
			
				getObjectText(bfsec2ndTxt);
				verifyAssertEquals(drpln.getCell(6, 12).getContents(), getActualObjectTxt);		//	Verify Entered Text In Section Page
				thread();
			
				//		Mouse Hover - Move Last
				webElement(bfsecmHovrOnFirstOptn);
				mouseHoverOn(webelementFrame);
				click(bfsecmoveLastIcon);				
				getObjectText(bfsec2ndTxt); 
				verifyAssertEquals(drpln.getCell(5, 12).getContents(), getActualObjectTxt);
				thread();
			
				//	Mouse Hover - Move Top
				webElement(bfsecmHovrOnSecOptn);
				mouseHoverOn(webelementFrame);
				thread();
				webElement(bfsecmoveTopIcon);
				mouseHoverOn(webelementFrame);
				click(bfsecmoveTopIcon);				
				getObjectText(bfsecfirstText);
				verifyAssertEquals(drpln.getCell(6, 12).getContents(), getActualObjectTxt);	
				thread();
			
				//	Mouse Hover - Move Down
				webElement(bfsecmHovrOnFirstOptn);
				mouseHoverOn(webelementFrame);
				click(bfsecmoveDownIcon);				
				getObjectText(bfsec2ndTxt1);
				verifyAssertEquals(drpln.getCell(6, 12).getContents(), getActualObjectTxt);
				thread();
			
				//	Mouse Hover - Move Up
				webElement(bfsecmHovrOnSecOptn);
				mouseHoverOn(webelementFrame);
				click(bfsecmoveUpIcon);
				getObjectText(bfsecfirstText);
				verifyAssertEquals(drpln.getCell(6, 12).getContents(), getActualObjectTxt);
				thread();
			
				//	Mouse Hover - Delete
				webElement(bfsecmHovrOnThirdOptn);
				mouseHoverOn(webelementFrame);
				click(bfsecremoveIcon);
			
				verifyAssert(txtDeleteConfPopup);
				getObjectText(deleteConfPpMessage);
				verifyAssertEquals(drpln.getCell(8, 6).getContents(), getActualObjectTxt);
				click(delConfOkBtn);
				thread();	
			}
			}catch(WebDriverException e1) {
				System.out.println("Display fields are not displaying");
				e1.printStackTrace();
			}
			thread();
		}*/
	
	@Test(priority=12)
	void previewOfDRPlan() throws InterruptedException, IOException
	{
		click(collapseAllSectionsBtn);
		click(drPreviewBtn);
		waitForPageLoad();
		Thread.sleep(10000);
		takeScreenshot();
		
		//webElement(drPreviewScrOutrBdy);
		//scrollInnerScrollBar(webelementFrame);		
		//scrollDownToBottom(webelementFrame);
		
		click(drPreviewCloseBtn);
		thread();
		
		click(drViewPDFRevisionsBtn);
		click(drGeneratePdfOptn);
		thread();
		
		String parentwindow = driver.getWindowHandle();
		
		for (String ttlWindowsCnt : driver.getWindowHandles())  
	     {  
			System.out.println("TTL Windows: "+ttlWindowsCnt);
			
			switchToWindow(ttlWindowsCnt);
			waitForPageLoad();
			takeScreenshot();	
			
			scrollToBottom();		
	     }
		
		driver.close();
		driver.switchTo().window(parentwindow);
		thread();
		
		
	}
}