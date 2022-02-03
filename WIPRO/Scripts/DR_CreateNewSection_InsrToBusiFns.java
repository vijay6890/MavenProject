package Scripts;

import static Config.TakScreenshot.takeScreenshot;
import static ObjectRepository.DRPlan.*;
import static ObjectRepository.Locations.*;
import static ObjectRepository.Threats.*;
import static UIWrappers.UIObjects.*;

import java.io.IOException;
import java.util.*;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import UIWrappers.Page;
import jxl.read.biff.BiffException;

public class DR_CreateNewSection_InsrToBusiFns extends Page
{
	
	LoginLogout ll = new LoginLogout();
	DR_CreateEditDRPlan dR_CreateEditDRPlan = new DR_CreateEditDRPlan();
	
	private String filterValueExistsMsg = "This filter exists please try with other options";
	private String enterFilterVal="Please enter filter value";
	
/*	@Test(priority=0)
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
	

	
	@Test(priority=2)
	void addInsuranceSection() throws InterruptedException, BiffException, IOException
	{
		getDRPlanCreateNewSectionSheetFromExcel();
		 
		click(drEdtSecCreateNewSectionBtn);
		verifyAssert(createNewSectionPopup);
		
		//	Section Title
		click(secPpSectionTitleDDArrow);
		String isecSectionTitle = drplnNewsc.getCell(1, 9).getContents();		//	Add New Section Name (or) Choose Existing Section Name
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
				verifyAssertEquals("Do you want to add "+drplnNewsc.getCell(1, 9).getContents()+" into Section Title ?", getActualObjectTxt);
				click(delConfOkBtn);
				thread();
				
				getObjectText(msgNotificationBar);
				verifyAssertEquals(drplnNewsc.getCell(1, 9).getContents()+" added into Section Title successfully", getActualObjectTxt);		//	Check New Section Added Successfully
				thread();
				
				getObjectText(secPpSectionTitleDefTxt);
				verifyAssertEquals(drplnNewsc.getCell(1, 9).getContents(), getActualObjectTxt);		//	Verify Added Title In Drop Down
			}
		}
		catch(Exception e)
		{
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
		enterText(drscEdtrCommentsField, drplnNewsc.getCell(2, 9).getContents());
		switchBackFromFrame();
		takeScreenshot();
		
		click(secPpSaveBtn);
		thread();
			
		getObjectText(msgNotificationBar);
		verifyAssertEquals(drplnNewsc.getCell(8, 7).getContents(), getActualObjectTxt);		//	Section Created Successfully	
		
		getObjectText(isecDescription);
		verifyAssertEquals(drplnNewsc.getCell(2, 9).getContents(), getActualObjectTxt);		//	Check Added Section Description
		
		click(isecDescription);
		thread();
		
		//		Edit Description		//
		verifyAssert(createNewSectionPopup);
		getObjectText(secPpCreateNewSectionTtl);
		verifyAssertEquals(drplnNewsc.getCell(9, 3).getContents(), getActualObjectTxt);		//	Check Update Description Title
		
		webElement(crSectionDescription);
		switchToWEFrame(webelementFrame);
		useKeyFuncToRmvVal(drscEdtrCommentsField);
		enterText(drscEdtrCommentsField, drplnNewsc.getCell(3, 9).getContents());
		switchBackFromFrame();
		
		click(secPpSaveBtn);
		thread();
		
		getObjectText(msgNotificationBar);
		verifyAssertEquals(drplnNewsc.getCell(8, 1).getContents(), getActualObjectTxt);		//	Section Updated Successfully
		thread();	
		
		getObjectText(isecDescription);
		verifyAssertEquals(drplnNewsc.getCell(3, 9).getContents(), getActualObjectTxt);		//	Verify Edited Description Details
		
		
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
		verifyAssertEquals(drplnNewsc.getCell(9, 4).getContents(), getActualObjectTxt);		//	Add Text/Paragraph Pop Up Title
			
		getObjectText(adTxtPpEnterSomeTxtLbl);
		verifyAssertEquals(drplnNewsc.getCell(10, 1).getContents(), getActualObjectTxt);	//	Enter Some Text Label Name
			
		webElement(adTxtEnterSomeTextFld);
		switchToWEFrame(webelementFrame);
		enterText(scEdtrCommentsField, drplnNewsc.getCell(4, 9).getContents());		//	Enter Some Texts
		switchBackFromFrame();
		
		click(adTxtParagrphPpSaveBtn);
		thread();
		
		getObjectText(msgNotificationBar);
		verifyAssertEquals(drplnNewsc.getCell(8, 2).getContents(), getActualObjectTxt);		//	Text/Paragraph added successfully 
		
		getObjectText(isecfirstText);
		verifyAssertEquals(drplnNewsc.getCell(4, 9).getContents(), getActualObjectTxt);		//	Verify Entered Text In Section Page
		
		click(isecfirstText);
		thread();
		
		//		Edit Text		//
		getObjectText(adTxtAddTextParagraphPpTtl);
		verifyAssertEquals(drplnNewsc.getCell(9, 5).getContents(), getActualObjectTxt);		//	Update Text/Paragraph Title
			
		webElement(adTxtEnterSomeTextFld);
		switchToWEFrame(webelementFrame);
		useKeyFuncToRmvVal(scEdtrCommentsField);	//	Edit Some Texts
		enterText(scEdtrCommentsField, drplnNewsc.getCell(5, 9).getContents());
		switchBackFromFrame();
			
		click(adTxtParagrphPpSaveBtn);
		thread();
			
		getObjectText(msgNotificationBar);
		verifyAssertEquals(drplnNewsc.getCell(8, 3).getContents(), getActualObjectTxt);		//	Text/Paragraph Updated Successfully
			
		getObjectText(isecfirstText);
		verifyAssertEquals(drplnNewsc.getCell(5, 9).getContents(), getActualObjectTxt);		//	Verify Modified Text
		
		
		//		Add Dynamic Data		//
		click(isecAddDataBtn);
		thread();
		try
	    {
		verifyAssert(addDynamicDataPopup);
			
		click(dynDtaPpModuleDDArrow);
		enterText(dynDtaPpModuleDDSearchBox, drplnNewsc.getCell(11, 9).getContents());		//	Choose Insurance module
		enterKeyInKyBord(dynDtaPpModuleDDSearchBox);
		thread();
		
	//	try
	//{
		//	verifyObjDisplay(dynDtaEmpSelectAllChkBox);
		//	if(chkObjDisplay == true)
		//	{
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
				}
				catch(Exception e3) 
				{
						e3.printStackTrace();
				}
					
				click(isecdfPolicyName);
				click(isecdfInsurer);
				click(isecdfExpiryDate);
				click(isecdfInsurerContact);
									
			//	click(dynDtaAddNewFilterBtn);	//	Add New Filter button
					
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
				thread();
				for(int i=0; i<2; i++)
				{
					click(dynDtaFieldValueDD);
					thread();
					driver.findElement(By.id("dyn_sel_data_fld_val_chzn_o_"+alist.get(i))).click();
					thread(); 
					click(dynDtaFilterAddBtn);
					thread();
				/*	String randomValue = driver.findElement(By.id("ddlDDataNewFilterValues_chzn_o_"+alist.get(i))).getText();
					enterText(dynDtaFieldValueDD, randomValue);
					enterKeyInKyBord(dynDtaFieldValueDD);  */
					
					try
					{							
						isAlertPresent();
						getAlertMsgText();
						try
						{
						verifyAssertEquals(enterFilterVal, getActlAlertTxt);
						acceptAlertMessage();
						}
						catch(Exception e)
						{
							acceptAlertMessage();
						}
						break;
					}
					catch(Exception noalrt)
					{
					//	System.out.println("No Alert Message");
						thread();
					}
				}		
				thread();
				verifyObjDisplay(By.xpath("//div[@id='cometchat_hide']"));
				if(chkObjDisplay == true)
				{
				click(By.xpath("//div[@id='cometchat_hide']"));
				}
				click(dynDtaPpSaveBtn);
				thread();
		}
		catch(Exception e)
		{
			click(dynDtaPpCloseBtn);
			thread();
			e.printStackTrace();
		}
				
				getObjectText(msgNotificationBar);
				verifyAssertEquals(drplnNewsc.getCell(8, 4).getContents(), getActualObjectTxt);		//	Data Created Successfully
				thread();
				
				//		Check Selected Display Order in Table
				for(int i=1; i<5; i++)
				{
					String getColumnName = driver.findElement(By.xpath("//div[@id='divDynamicDRPlanSections']/div[9]//table[@class='table table-responsive table-striped table-bordered bootstrap-datatable dynamic-data-table']//tr/td["+i+"]")).getText();
					if(i == 1)
						verifyAssertEquals(drplnNewsc.getCell(12, 29).getContents(), getColumnName);	//	Policy Name
					if(i == 2)
						verifyAssertEquals(drplnNewsc.getCell(12, 30).getContents(), getColumnName);	//	Insurer
					if(i == 3)
						verifyAssertEquals(drplnNewsc.getCell(12, 31).getContents(), getColumnName);	//	Expiry Date
					if(i == 4)
						verifyAssertEquals(drplnNewsc.getCell(12, 32).getContents(), getColumnName);	//	Insurer Contact
				}
				
				click(isecTeamsList);
				thread();
				
				verifyAssert(addDynamicDataPopup);
				
				for(int i=2; i<4; i++)
				{
					boolean checked = driver.findElement(By.xpath("//div[@id='pri-mod-flds-holder']/span["+i+"]/input")).isSelected();
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
				}
				catch(Exception e) 
				{
				//	System.out.println("Selected Filter values are not displaying in Edit screen");
					e.printStackTrace();
				}
				thread();
				verifyObjDisplay(By.xpath("//div[@id='cometchat_hide']"));
				if(chkObjDisplay == true)
				{
				click(By.xpath("//div[@id='cometchat_hide']"));
				}
				click(dynDtaPpSaveBtn);
				thread();
							
				getObjectText(msgNotificationBar);
				verifyAssertEquals(drplnNewsc.getCell(8, 5).getContents(), getActualObjectTxt);		//	Data Updated Successfully
				thread();
												
				//		Verify Column Name Order Changes In Table
				for(int i=1; i<5; i++)
				{
					String getColumnName = driver.findElement(By.xpath("//div[@id='divDynamicDRPlanSections']/div[9]//table[@class='table table-responsive table-striped table-bordered bootstrap-datatable dynamic-data-table']//tr/td["+i+"]")).getText();
					thread();
					if(i == 1)
						//verifyAssertEquals(drplnNewsc.getCell(12, 30).getContents(), getColumnName);	//	Insurer
					if(i == 2)
					//	verifyAssertEquals(drplnNewsc.getCell(12, 29).getContents(), getColumnName);	//	Policy Name
					if(i == 3)
						verifyAssertEquals(drplnNewsc.getCell(12, 32).getContents(), getColumnName);	//	Insurer Contact
					if(i == 4)
						verifyAssertEquals(drplnNewsc.getCell(12, 31).getContents(), getColumnName);	//	Expiry Date
				}				
							
				//		Edit Section		//
				click(isecEditSectionBtn);
				thread();
			
				verifyAssert(createNewSectionPopup);
				getObjectText(secPpCreateNewSectionTtl);
				verifyAssertEquals(drplnNewsc.getCell(9, 2).getContents(), getActualObjectTxt);		//	Update Section Pop up Title
			
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
				verifyAssertEquals(drplnNewsc.getCell(9, 4).getContents(), getActualObjectTxt);		//	Add Text/Paragraph Pop Up Title
				
				getObjectText(adTxtPpEnterSomeTxtLbl);
				verifyAssertEquals(drplnNewsc.getCell(10, 1).getContents(), getActualObjectTxt);		//	Enter Some Text Label Name
				
				webElement(adTxtEnterSomeTextFld);
				switchToWEFrame(webelementFrame);
				enterText(scEdtrCommentsField, drplnNewsc.getCell(6, 9).getContents());		//	Enter Some Texts
				switchBackFromFrame();
			
				click(adTxtParagrphPpSaveBtn);
				thread();
			
				getObjectText(msgNotificationBar);
				verifyAssertEquals(drplnNewsc.getCell(8, 2).getContents(), getActualObjectTxt);		//	Text/Paragraph added successfully 
			
				webElement(isec2ndTxt); 
				scrollInnerScrollBar(webelementFrame);		//	Scrolling Section Grid Inner Scroll Bar	
			
				getObjectText(isec2ndTxt);
				verifyAssertEquals(drplnNewsc.getCell(6, 9).getContents(), getActualObjectTxt);		//	Verify Entered Text In Section Page
				thread();
			
				//		Mouse Hover - Move Last
				webElement(isecmHovrOnFirstOptn);
				mouseHoverOn(webelementFrame);
				click(isecmoveLastIcon);				
				getObjectText(isec2ndTxt); 
				verifyAssertEquals(drplnNewsc.getCell(5, 9).getContents(), getActualObjectTxt);
				thread();
			
				//	Mouse Hover - Move Top
				webElement(isecmHovrOnSecOptn);
				mouseHoverOn(webelementFrame);
				thread();
				webElement(isecmoveTopIcon);
				mouseHoverOn(webelementFrame);
				click(isecmoveTopIcon);
				Thread.sleep(1000);
				getObjectText(isecfirstText);
				verifyAssertEquals(drplnNewsc.getCell(6, 9).getContents(), getActualObjectTxt);	
				thread();
			
				//	Mouse Hover - Move Down
				webElement(isecmHovrOnFirstOptn);
				mouseHoverOn(webelementFrame);
				click(isecmoveDownIcon);				
				getObjectText(isec2ndTxt1);
				verifyAssertEquals(drplnNewsc.getCell(6, 9).getContents(), getActualObjectTxt);
				thread();
			
				//	Mouse Hover - Move Up
				webElement(isecmHovrOnSecOptn);
				mouseHoverOn(webelementFrame);
				click(isecmoveUpIcon);
				getObjectText(isecfirstText);
				verifyAssertEquals(drplnNewsc.getCell(6, 9).getContents(), getActualObjectTxt);
				thread();
			
				//	Mouse Hover - Delete
				webElement(isecmHovrOnThirdOptn);
				mouseHoverOn(webelementFrame);
				click(isecremoveIcon);
			
				verifyAssert(txtDeleteConfPopup);
				getObjectText(deleteConfPpMessage);
				verifyAssertEquals(drplnNewsc.getCell(8, 6).getContents(), getActualObjectTxt);
				click(delConfOkBtn);
				thread();	
			}   
			                    
	

	@Test(priority=3)
	void addTasksSection() throws InterruptedException, BiffException, IOException
	{
		getDRPlanCreateNewSectionSheetFromExcel();
		 
		click(drEdtSecCreateNewSectionBtn);
		verifyAssert(createNewSectionPopup);
		
		//	Section Title
		click(secPpSectionTitleDDArrow);
		String tsecSectionTitle = drplnNewsc.getCell(1, 10).getContents();		//	Add New Section Name (or) Choose Existing Section Name
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
				verifyAssertEquals("Do you want to add "+drplnNewsc.getCell(1, 10).getContents()+" into Section Title ?", getActualObjectTxt);
				click(delConfOkBtn);
				thread();
				
				getObjectText(msgNotificationBar);
				verifyAssertEquals(drplnNewsc.getCell(1, 10).getContents()+" added into Section Title successfully", getActualObjectTxt);		//	Check New Section Added Successfully
				thread();
				
				getObjectText(secPpSectionTitleDefTxt);
				verifyAssertEquals(drplnNewsc.getCell(1, 10).getContents(), getActualObjectTxt);		//	Verify Added Title In Drop Down
			}
		}
		catch(Exception e)
		{
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
		enterText(drscEdtrCommentsField, drplnNewsc.getCell(2, 10).getContents());
		switchBackFromFrame();
		takeScreenshot();
		thread();
		
		click(secPpSaveBtn);
		thread();
			
		getObjectText(msgNotificationBar);
		verifyAssertEquals(drplnNewsc.getCell(8, 7).getContents(), getActualObjectTxt);		//	Section Created Successfully	
		
		getObjectText(tsecDescription);
		verifyAssertEquals(drplnNewsc.getCell(2, 10).getContents(), getActualObjectTxt);		//	Check Added Section Description
		
		click(tsecDescription);
		thread();
		
		//		Edit Description		//
		verifyAssert(createNewSectionPopup);
		getObjectText(secPpCreateNewSectionTtl);
		verifyAssertEquals(drplnNewsc.getCell(9, 3).getContents(), getActualObjectTxt);		//	Check Update Description Title
		
		webElement(crSectionDescription);
		switchToWEFrame(webelementFrame);
		useKeyFuncToRmvVal(drscEdtrCommentsField);
		enterText(drscEdtrCommentsField, drplnNewsc.getCell(3, 10).getContents());
		switchBackFromFrame();
		
		click(secPpSaveBtn);
		thread();
		
		getObjectText(msgNotificationBar);
		verifyAssertEquals(drplnNewsc.getCell(8, 1).getContents(), getActualObjectTxt);		//	Section Updated Successfully
		thread();	
		
		getObjectText(tsecDescription);
		verifyAssertEquals(drplnNewsc.getCell(3, 10).getContents(), getActualObjectTxt);		//	Verify Edited Description Details
		
		
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
		verifyAssertEquals(drplnNewsc.getCell(9, 4).getContents(), getActualObjectTxt);		//	Add Text/Paragraph Pop Up Title
			
		getObjectText(adTxtPpEnterSomeTxtLbl);
		verifyAssertEquals(drplnNewsc.getCell(10, 1).getContents(), getActualObjectTxt);	//	Enter Some Text Label Name
			
		webElement(adTxtEnterSomeTextFld);
		switchToWEFrame(webelementFrame);
		enterText(scEdtrCommentsField, drplnNewsc.getCell(4, 10).getContents());		//	Enter Some Texts
		switchBackFromFrame();
		
		click(adTxtParagrphPpSaveBtn);
		thread();
		
		getObjectText(msgNotificationBar);
		verifyAssertEquals(drplnNewsc.getCell(8, 2).getContents(), getActualObjectTxt);		//	Text/Paragraph added successfully 
		
		getObjectText(tsecfirstText);
		verifyAssertEquals(drplnNewsc.getCell(4, 10).getContents(), getActualObjectTxt);		//	Verify Entered Text In Section Page
		
		click(tsecfirstText);
		thread();
		
		//		Edit Text		//
		getObjectText(adTxtAddTextParagraphPpTtl);
		verifyAssertEquals(drplnNewsc.getCell(9, 5).getContents(), getActualObjectTxt);		//	Update Text/Paragraph Title
			
		webElement(adTxtEnterSomeTextFld);
		switchToWEFrame(webelementFrame);
		useKeyFuncToRmvVal(scEdtrCommentsField);	//	Edit Some Texts
		enterText(scEdtrCommentsField, drplnNewsc.getCell(5, 10).getContents());
		switchBackFromFrame();
			
		click(adTxtParagrphPpSaveBtn);
		thread();
			
		getObjectText(msgNotificationBar);
		verifyAssertEquals(drplnNewsc.getCell(8, 3).getContents(), getActualObjectTxt);		//	Text/Paragraph Updated Successfully
			
		getObjectText(tsecfirstText);
		verifyAssertEquals(drplnNewsc.getCell(5, 10).getContents(), getActualObjectTxt);		//	Verify Modified Text
		
		
		//		Add Dynamic Data		//
		click(tsecAddDataBtn);
		thread();
		try
		{
		verifyAssert(addDynamicDataPopup);
			
		click(dynDtaPpModuleDDArrow);
		enterText(dynDtaPpModuleDDSearchBox, drplnNewsc.getCell(11, 10).getContents());		//	Choose Assets module
		enterKeyInKyBord(dynDtaPpModuleDDSearchBox);
		thread();
		
	//	try
	//	{
		//	verifyObjDisplay(dynDtaEmpSelectAllChkBox);
		//	if(chkObjDisplay == true)
		//	{
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
				}
				catch(Exception e3) 
				{
						e3.printStackTrace();
				}
					
				click(tsecdfTaskName);
				click(tsecdfTaskType);
				click(tsecdfTaskID);
				click(tsecdfEmployeeAssigned);
									
			//	click(dynDtaAddNewFilterBtn);	//	Add New Filter button
					
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
					click(dynDtaFieldValueDD);
					driver.findElement(By.id("dyn_sel_data_fld_val_chzn_o_"+alist.get(i))).click();
					thread();
		/*			String randomValue = driver.findElement(By.id("ddlDDataNewFilterValues_chzn_o_"+alist.get(i))).getText();
					enterText(dynDtaFieldValueDD, randomValue);
					enterKeyInKyBord(dynDtaFieldValueDD);  */
				thread();
					click(dynDtaFilterAddBtn);
					thread();
					try
					{							
						isAlertPresent();
						getAlertMsgText();
						try
						{
						verifyAssertEquals(enterFilterVal, getActlAlertTxt);
						acceptAlertMessage();
						}
						catch(Exception e)
						{
							acceptAlertMessage();
						}
						break;
					}
					catch(Exception noalrt)
					{
					//	System.out.println("No Alert Message");
						thread();
					}
				}	
				
				thread();
				verifyObjDisplay(By.xpath("//div[@id='cometchat_hide']"));
				if(chkObjDisplay == true)
				{
				click(By.xpath("//div[@id='cometchat_hide']"));
				}
					
				click(dynDtaPpSaveBtn);
				thread();
		}
		catch(Exception e)
		{
			click(dynDtaPpCloseBtn);
			thread();
		}
				
				getObjectText(msgNotificationBar);
				verifyAssertEquals(drplnNewsc.getCell(8, 4).getContents(), getActualObjectTxt);		//	Data Created Successfully
				thread();
				
				//		Check Selected Display Order in Table
				for(int i=1; i<5; i++)
				{
					String getColumnName = driver.findElement(By.xpath("//div[@id='divDynamicDRPlanSections']/div[10]//table[@class='table table-responsive table-striped table-bordered bootstrap-datatable dynamic-data-table']//tr/td["+i+"]")).getText();
					if(i == 1)
						verifyAssertEquals(drplnNewsc.getCell(12, 33).getContents(), getColumnName);	//	Task Name
					if(i == 2)
						verifyAssertEquals(drplnNewsc.getCell(12, 34).getContents(), getColumnName);	//	Task Type
					if(i == 3)
						verifyAssertEquals(drplnNewsc.getCell(12, 35).getContents(), getColumnName);	//	Task ID
					if(i == 4)
						verifyAssertEquals(drplnNewsc.getCell(12, 36).getContents(), getColumnName);	//	Employee Assigned
				}
				
				click(tsecTeamsList);
				thread();
				
				verifyAssert(addDynamicDataPopup);
				
				for(int i=2; i<4; i++)
				{
					boolean checked = driver.findElement(By.xpath("//div[@id='pri-mod-flds-holder']/span["+i+"]/input")).isSelected();
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
				}
				catch(Exception e) 
				{
				//	System.out.println("Selected Filter values are not displaying in Edit screen");
					e.printStackTrace();
				}
				
				thread();
				verifyObjDisplay(By.xpath("//div[@id='cometchat_hide']"));
				if(chkObjDisplay == true)
				{
				click(By.xpath("//div[@id='cometchat_hide']"));
				}
				click(dynDtaPpSaveBtn);
				thread();
							
				getObjectText(msgNotificationBar);
				verifyAssertEquals(drplnNewsc.getCell(8, 5).getContents(), getActualObjectTxt);		//	Data Updated Successfully
				thread();
												
				//		Verify Column Name Order Changes In Table
				for(int i=1; i<5; i++)
				{
					String getColumnName = driver.findElement(By.xpath("//div[@id='divDynamicDRPlanSections']/div[10]//table[@class='table table-responsive table-striped table-bordered bootstrap-datatable dynamic-data-table']//tr/td["+i+"]")).getText();
					if(i == 1)
						verifyAssertEquals(drplnNewsc.getCell(12, 34).getContents(), getColumnName);	//	Task Type
					if(i == 2)
						verifyAssertEquals(drplnNewsc.getCell(12, 33).getContents(), getColumnName);	//	Task Name
					if(i == 3)
						verifyAssertEquals(drplnNewsc.getCell(12, 36).getContents(), getColumnName);	//	Employee Assigned
					if(i == 4)
						verifyAssertEquals(drplnNewsc.getCell(12, 35).getContents(), getColumnName);	//	Task ID
				}				
							
				//		Edit Section		//
				click(tsecEditSectionBtn);
				thread();
			
				verifyAssert(createNewSectionPopup);
				getObjectText(secPpCreateNewSectionTtl);
				verifyAssertEquals(drplnNewsc.getCell(9, 2).getContents(), getActualObjectTxt);		//	Update Section Pop up Title
			
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
				verifyAssertEquals(drplnNewsc.getCell(9, 4).getContents(), getActualObjectTxt);		//	Add Text/Paragraph Pop Up Title
				
				getObjectText(adTxtPpEnterSomeTxtLbl);
				verifyAssertEquals(drplnNewsc.getCell(10, 1).getContents(), getActualObjectTxt);		//	Enter Some Text Label Name
				
				webElement(adTxtEnterSomeTextFld);
				switchToWEFrame(webelementFrame);
				enterText(scEdtrCommentsField, drplnNewsc.getCell(6, 10).getContents());		//	Enter Some Texts
				switchBackFromFrame();
			
				click(adTxtParagrphPpSaveBtn);
				thread();
			
				getObjectText(msgNotificationBar);
				verifyAssertEquals(drplnNewsc.getCell(8, 2).getContents(), getActualObjectTxt);		//	Text/Paragraph added successfully 
			
				webElement(tsec2ndTxt); 
				scrollInnerScrollBar(webelementFrame);		//	Scrolling Section Grid Inner Scroll Bar	
			
				getObjectText(tsec2ndTxt);
				verifyAssertEquals(drplnNewsc.getCell(6, 10).getContents(), getActualObjectTxt);		//	Verify Entered Text In Section Page
				thread();
			
				//		Mouse Hover - Move Last
				webElement(tsecmHovrOnFirstOptn);
				mouseHoverOn(webelementFrame);
				click(tsecmoveLastIcon);
				thread();
				getObjectText(tsec2ndTxt); 
				verifyAssertEquals(drplnNewsc.getCell(5, 10).getContents(), getActualObjectTxt);
				thread();
			
				//	Mouse Hover - Move Top
				webElement(tsecmHovrOnSecOptn);
				mouseHoverOn(webelementFrame);
				thread();
				webElement(tsecmoveTopIcon);
				mouseHoverOn(webelementFrame);
				click(tsecmoveTopIcon);				
				getObjectText(tsecfirstText);
				verifyAssertEquals(drplnNewsc.getCell(6, 10).getContents(), getActualObjectTxt);	
				thread();
			
				//	Mouse Hover - Move Down
				webElement(tsecmHovrOnFirstOptn);
				mouseHoverOn(webelementFrame);
				click(tsecmoveDownIcon);				
				getObjectText(tsec2ndTxt1);
				verifyAssertEquals(drplnNewsc.getCell(6, 10).getContents(), getActualObjectTxt);
				thread();
			
				//	Mouse Hover - Move Up
				webElement(tsecmHovrOnSecOptn);
				mouseHoverOn(webelementFrame);
				click(tsecmoveUpIcon);
				getObjectText(tsecfirstText);
				verifyAssertEquals(drplnNewsc.getCell(6, 10).getContents(), getActualObjectTxt);
				thread();
			
				//	Mouse Hover - Delete
				webElement(tsecmHovrOnThirdOptn);
				mouseHoverOn(webelementFrame);
				click(tsecremoveIcon);
			
				verifyAssert(txtDeleteConfPopup);
				getObjectText(deleteConfPpMessage);
				verifyAssertEquals(drplnNewsc.getCell(8, 6).getContents(), getActualObjectTxt);
				click(delConfOkBtn);
				thread();	
			}                                    
	                                     
	

	@Test(priority=4)
	void addTaskGroupsSection() throws InterruptedException, BiffException, IOException
	{
		getDRPlanCreateNewSectionSheetFromExcel();
		 
		click(drEdtSecCreateNewSectionBtn);
		verifyAssert(createNewSectionPopup);
		
		//	Section Title
		click(secPpSectionTitleDDArrow);
		String tgsecSectionTitle = drplnNewsc.getCell(1, 11).getContents();		//	Add New Section Name (or) Choose Existing Section Name
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
				verifyAssertEquals("Do you want to add "+drplnNewsc.getCell(1, 11).getContents()+" into Section Title ?", getActualObjectTxt);
				click(delConfOkBtn);
				thread();
				
				getObjectText(msgNotificationBar);
				verifyAssertEquals(drplnNewsc.getCell(1, 11).getContents()+" added into Section Title successfully", getActualObjectTxt);		//	Check New Section Added Successfully
				thread();
				
				getObjectText(secPpSectionTitleDefTxt);
				verifyAssertEquals(drplnNewsc.getCell(1, 11).getContents(), getActualObjectTxt);		//	Verify Added Title In Drop Down
			}
		}
		catch(Exception e)
		{
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
		enterText(drscEdtrCommentsField, drplnNewsc.getCell(2, 11).getContents());
		switchBackFromFrame();
		takeScreenshot();
		
		click(secPpSaveBtn);
		thread();
			
		getObjectText(msgNotificationBar);
		verifyAssertEquals(drplnNewsc.getCell(8, 7).getContents(), getActualObjectTxt);		//	Section Created Successfully	
		
		getObjectText(tgsecDescription);
		verifyAssertEquals(drplnNewsc.getCell(2, 11).getContents(), getActualObjectTxt);		//	Check Added Section Description
		
		click(tgsecDescription);
		thread();
		
		//		Edit Description		//
		verifyAssert(createNewSectionPopup);
		getObjectText(secPpCreateNewSectionTtl);
		verifyAssertEquals(drplnNewsc.getCell(9, 3).getContents(), getActualObjectTxt);		//	Check Update Description Title
		
		webElement(crSectionDescription);
		switchToWEFrame(webelementFrame);
		useKeyFuncToRmvVal(drscEdtrCommentsField);
		enterText(drscEdtrCommentsField, drplnNewsc.getCell(3, 11).getContents());
		switchBackFromFrame();
		
		click(secPpSaveBtn);
		thread();
		
		getObjectText(msgNotificationBar);
		verifyAssertEquals(drplnNewsc.getCell(8, 1).getContents(), getActualObjectTxt);		//	Section Updated Successfully
		thread();	
		
		getObjectText(tgsecDescription);
		verifyAssertEquals(drplnNewsc.getCell(3, 11).getContents(), getActualObjectTxt);		//	Verify Edited Description Details
		
		
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
		verifyAssertEquals(drplnNewsc.getCell(9, 4).getContents(), getActualObjectTxt);		//	Add Text/Paragraph Pop Up Title
			
		getObjectText(adTxtPpEnterSomeTxtLbl);
		verifyAssertEquals(drplnNewsc.getCell(10, 1).getContents(), getActualObjectTxt);	//	Enter Some Text Label Name
			
		webElement(adTxtEnterSomeTextFld);
		switchToWEFrame(webelementFrame);
		enterText(scEdtrCommentsField, drplnNewsc.getCell(4, 11).getContents());		//	Enter Some Texts
		switchBackFromFrame();
		
		click(adTxtParagrphPpSaveBtn);
		thread();
		
		getObjectText(msgNotificationBar);
		verifyAssertEquals(drplnNewsc.getCell(8, 2).getContents(), getActualObjectTxt);		//	Text/Paragraph added successfully 
		
		getObjectText(tgsecfirstText);
		verifyAssertEquals(drplnNewsc.getCell(4, 11).getContents(), getActualObjectTxt);		//	Verify Entered Text In Section Page
		
		click(tgsecfirstText);
		thread();
		
		//		Edit Text		//
		getObjectText(adTxtAddTextParagraphPpTtl);
		verifyAssertEquals(drplnNewsc.getCell(9, 5).getContents(), getActualObjectTxt);		//	Update Text/Paragraph Title
			
		webElement(adTxtEnterSomeTextFld);
		switchToWEFrame(webelementFrame);
		useKeyFuncToRmvVal(scEdtrCommentsField);	//	Edit Some Texts
		enterText(scEdtrCommentsField, drplnNewsc.getCell(5, 11).getContents());
		switchBackFromFrame();
			
		click(adTxtParagrphPpSaveBtn);
		thread();
			
		getObjectText(msgNotificationBar);
		verifyAssertEquals(drplnNewsc.getCell(8, 3).getContents(), getActualObjectTxt);		//	Text/Paragraph Updated Successfully
			
		getObjectText(tgsecfirstText);
		verifyAssertEquals(drplnNewsc.getCell(5, 11).getContents(), getActualObjectTxt);		//	Verify Modified Text
		
		
		//		Add Dynamic Data		//
		click(tgsecAddDataBtn);
		thread();
		
		try
		{
		verifyAssert(addDynamicDataPopup);
			
		click(dynDtaPpModuleDDArrow);
		enterText(dynDtaPpModuleDDSearchBox, drplnNewsc.getCell(11, 11).getContents());		//	Choose Assets module
		enterKeyInKyBord(dynDtaPpModuleDDSearchBox);
		thread();
		
	//	try
	//	{
		//	verifyObjDisplay(dynDtaEmpSelectAllChkBox);
		//	if(chkObjDisplay == true)
		//	{
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
				}
				catch(Exception e3)
				{
						e3.printStackTrace();
				}
					
				click(tgsecdfTaskGroupName);
				click(tgsecdfSummary);
													
			//	click(dynDtaAddNewFilterBtn);	//	Add New Filter button
					
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
					click(dynDtaFieldValueDD);
					thread();
					driver.findElement(By.id("dyn_sel_data_fld_val_chzn_o_"+alist.get(i))).click();
					Thread.sleep(70);
					Thread.sleep(1000);
					click(dynDtaFilterAddBtn);
					thread();
				/*	String randomValue = driver.findElement(By.id("ddlDDataNewFilterValues_chzn_o_"+alist.get(i))).getText();
					enterText(dynDtaFieldValueDD, randomValue);
					thread();
					enterKeyInKyBord(dynDtaFieldValueDD);  */
					
					try
					{							
						isAlertPresent();
						getAlertMsgText();
						try
						{
						verifyAssertEquals(enterFilterVal, getActlAlertTxt);
						acceptAlertMessage();
						}
						catch(Exception e)
						{
							acceptAlertMessage();
						}
						break;
					}
					catch(Exception noalrt)
					{
					//	System.out.println("No Alert Message");
						thread();
					}
				}		
				thread();
				verifyObjDisplay(By.xpath("//div[@id='cometchat_hide']"));
				if(chkObjDisplay == true)
				{
				click(By.xpath("//div[@id='cometchat_hide']"));
				}
				click(dynDtaPpSaveBtn);
				thread();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			click(dynDtaPpCloseBtn);
			thread();
		}
				
				getObjectText(msgNotificationBar);
				verifyAssertEquals(drplnNewsc.getCell(8, 4).getContents(), getActualObjectTxt);		//	Data Created Successfully
				thread();
				
				//		Check Selected Display Order in Table
				for(int i=1; i<3; i++)
				{
					String getColumnName = driver.findElement(By.xpath("//div[@id='divDynamicDRPlanSections']/div[11]//table[@class='table table-responsive table-striped table-bordered bootstrap-datatable dynamic-data-table']//tr/td["+i+"]")).getText();
					if(i == 1)
						verifyAssertEquals(drplnNewsc.getCell(12, 37).getContents(), getColumnName);	//	Task Group Name
					if(i == 2)
						verifyAssertEquals(drplnNewsc.getCell(12, 38).getContents(), getColumnName);	//	Summary
				}
				thread();
				click(tgsecTeamsList);
				Thread.sleep(1000);
				thread();
				
				verifyAssert(addDynamicDataPopup);
				
				for(int i=2; i<4; i++)
				{
					boolean checked = driver.findElement(By.xpath("//div[@id='pri-mod-flds-holder']/span["+i+"]/input")).isSelected();
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
				}
				catch(Exception e) 
				{
				//	System.out.println("Selected Filter values are not displaying in Edit screen");
					e.printStackTrace();
				}
				thread();
				verifyObjDisplay(By.xpath("//div[@id='cometchat_hide']"));
				if(chkObjDisplay == true)
				{
				click(By.xpath("//div[@id='cometchat_hide']"));
				}
				click(dynDtaPpSaveBtn);
				thread();
							
				getObjectText(msgNotificationBar);
				verifyAssertEquals(drplnNewsc.getCell(8, 5).getContents(), getActualObjectTxt);		//	Data Updated Successfully
				thread();
												
				//		Verify Column Name Order Changes In Table
				for(int i=1; i<3; i++)
				{
					String getColumnName = driver.findElement(By.xpath("//div[@id='divDynamicDRPlanSections']/div[11]//table[@class='table table-responsive table-striped table-bordered bootstrap-datatable dynamic-data-table']//tr/td["+i+"]")).getText();
					if(i == 1)
						verifyAssertEquals(drplnNewsc.getCell(12, 38).getContents(), getColumnName);	//	Summary
					if(i == 2)
						verifyAssertEquals(drplnNewsc.getCell(12, 37).getContents(), getColumnName);	//	Task Group Name					
				}				
							
				//		Edit Section		//
				click(tgsecEditSectionBtn);
				thread();
			
				verifyAssert(createNewSectionPopup);
				getObjectText(secPpCreateNewSectionTtl);
				verifyAssertEquals(drplnNewsc.getCell(9, 2).getContents(), getActualObjectTxt);		//	Update Section Pop up Title
			
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
				verifyAssertEquals(drplnNewsc.getCell(9, 4).getContents(), getActualObjectTxt);		//	Add Text/Paragraph Pop Up Title
				
				getObjectText(adTxtPpEnterSomeTxtLbl);
				verifyAssertEquals(drplnNewsc.getCell(10, 1).getContents(), getActualObjectTxt);		//	Enter Some Text Label Name
				
				webElement(adTxtEnterSomeTextFld);
				switchToWEFrame(webelementFrame);
				enterText(scEdtrCommentsField, drplnNewsc.getCell(6, 11).getContents());		//	Enter Some Texts
				switchBackFromFrame();
				thread();
			
				click(adTxtParagrphPpSaveBtn);
				thread();
			
				getObjectText(msgNotificationBar);
				verifyAssertEquals(drplnNewsc.getCell(8, 2).getContents(), getActualObjectTxt);		//	Text/Paragraph added successfully 
			
				webElement(tgsec2ndTxt); 
				scrollInnerScrollBar(webelementFrame);		//	Scrolling Section Grid Inner Scroll Bar	
			
				getObjectText(tgsec2ndTxt);
				verifyAssertEquals(drplnNewsc.getCell(6, 11).getContents(), getActualObjectTxt);		//	Verify Entered Text In Section Page
				thread();
			
				//		Mouse Hover - Move Last
				webElement(tgsecmHovrOnFirstOptn);
				mouseHoverOn(webelementFrame);
				click(tgsecmoveLastIcon);				
				getObjectText(tgsec2ndTxt); 
				verifyAssertEquals(drplnNewsc.getCell(5, 11).getContents(), getActualObjectTxt);
				thread();
			
				//	Mouse Hover - Move Top
				webElement(tgsecmHovrOnSecOptn);
				mouseHoverOn(webelementFrame);
				thread();
				webElement(tgsecmoveTopIcon);
				mouseHoverOn(webelementFrame);
				click(tgsecmoveTopIcon);				
				getObjectText(tgsecfirstText);
				verifyAssertEquals(drplnNewsc.getCell(6, 11).getContents(), getActualObjectTxt);	
				thread();
			
				//	Mouse Hover - Move Down
				webElement(tgsecmHovrOnFirstOptn);
				mouseHoverOn(webelementFrame);
				click(tgsecmoveDownIcon);				
				getObjectText(tgsec2ndTxt1);
				verifyAssertEquals(drplnNewsc.getCell(6, 11).getContents(), getActualObjectTxt);
				thread();
			
				//	Mouse Hover - Move Up
				webElement(tgsecmHovrOnSecOptn);
				mouseHoverOn(webelementFrame);
				click(tgsecmoveUpIcon);
				getObjectText(tgsecfirstText);
				verifyAssertEquals(drplnNewsc.getCell(6, 11).getContents(), getActualObjectTxt);
				thread();
			
				//	Mouse Hover - Delete
				webElement(tgsecmHovrOnThirdOptn);
				mouseHoverOn(webelementFrame);
				click(tgsecremoveIcon);
				Thread.sleep(1000);
				thread();
			
				verifyAssert(txtDeleteConfPopup);
				getObjectText(deleteConfPpMessage);
				verifyAssertEquals(drplnNewsc.getCell(8, 6).getContents(), getActualObjectTxt);
				click(delConfOkBtn);
				thread();	
			}                        
                                                    
	
	
	@Test(priority=5)
	void addBusinessFunctionsSection() throws InterruptedException, BiffException, IOException
	{
		getDRPlanCreateNewSectionSheetFromExcel();
		 
		click(drEdtSecCreateNewSectionBtn);
		verifyAssert(createNewSectionPopup);
		
		//	Section Title
		click(secPpSectionTitleDDArrow);
		String bfsecSectionTitle = drplnNewsc.getCell(1, 12).getContents();		//	Add New Section Name (or) Choose Existing Section Name
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
				verifyAssertEquals("Do you want to add "+drplnNewsc.getCell(1, 12).getContents()+" into Section Title ?", getActualObjectTxt);
				click(delConfOkBtn);
				thread();
				
				getObjectText(msgNotificationBar);
				verifyAssertEquals(drplnNewsc.getCell(1, 12).getContents()+" added into Section Title successfully", getActualObjectTxt);		//	Check New Section Added Successfully
				thread();
				
				getObjectText(secPpSectionTitleDefTxt);
				verifyAssertEquals(drplnNewsc.getCell(1, 12).getContents(), getActualObjectTxt);		//	Verify Added Title In Drop Down
			}
		}
		catch(Exception e)
		{
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
		enterText(drscEdtrCommentsField, drplnNewsc.getCell(2, 12).getContents());
		switchBackFromFrame();
		takeScreenshot();
		
		click(secPpSaveBtn);
		thread();
			
		getObjectText(msgNotificationBar);
		verifyAssertEquals(drplnNewsc.getCell(8, 7).getContents(), getActualObjectTxt);		//	Section Created Successfully	
		
		getObjectText(bfsecDescription);
		verifyAssertEquals(drplnNewsc.getCell(2, 12).getContents(), getActualObjectTxt);		//	Check Added Section Description
		
		click(bfsecDescription);
		thread();
		
		//		Edit Description		//
		verifyAssert(createNewSectionPopup);
		getObjectText(secPpCreateNewSectionTtl);
		verifyAssertEquals(drplnNewsc.getCell(9, 3).getContents(), getActualObjectTxt);		//	Check Update Description Title
		
		webElement(crSectionDescription);
		switchToWEFrame(webelementFrame);
		useKeyFuncToRmvVal(drscEdtrCommentsField);
		thread();
		enterText(drscEdtrCommentsField, drplnNewsc.getCell(3, 12).getContents());
		switchBackFromFrame();
		thread();
		Thread.sleep(3000);
		click(secPpSaveBtn);
		thread();
		
		getObjectText(msgNotificationBar);
		verifyAssertEquals(drplnNewsc.getCell(8, 1).getContents(), getActualObjectTxt);		//	Section Updated Successfully
		thread();	
		
		getObjectText(bfsecDescription);
		verifyAssertEquals(drplnNewsc.getCell(3, 12).getContents(), getActualObjectTxt);		//	Verify Edited Description Details
		
		
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
		verifyAssertEquals(drplnNewsc.getCell(9, 4).getContents(), getActualObjectTxt);		//	Add Text/Paragraph Pop Up Title
			
		getObjectText(adTxtPpEnterSomeTxtLbl);
		verifyAssertEquals(drplnNewsc.getCell(10, 1).getContents(), getActualObjectTxt);	//	Enter Some Text Label Name
			
		webElement(adTxtEnterSomeTextFld);
		switchToWEFrame(webelementFrame);
		enterText(scEdtrCommentsField, drplnNewsc.getCell(4, 12).getContents());		//	Enter Some Texts
		switchBackFromFrame();
		
		click(adTxtParagrphPpSaveBtn);
		thread();
		
		getObjectText(msgNotificationBar);
		verifyAssertEquals(drplnNewsc.getCell(8, 2).getContents(), getActualObjectTxt);		//	Text/Paragraph added successfully 
		
		getObjectText(bfsecfirstText);
		verifyAssertEquals(drplnNewsc.getCell(4, 12).getContents(), getActualObjectTxt);		//	Verify Entered Text In Section Page
		
		click(bfsecfirstText);
		thread();
		
		//		Edit Text		//
		getObjectText(adTxtAddTextParagraphPpTtl);
		verifyAssertEquals(drplnNewsc.getCell(9, 5).getContents(), getActualObjectTxt);		//	Update Text/Paragraph Title
			
		webElement(adTxtEnterSomeTextFld);
		switchToWEFrame(webelementFrame);
		useKeyFuncToRmvVal(scEdtrCommentsField);	//	Edit Some Texts
		enterText(scEdtrCommentsField, drplnNewsc.getCell(5, 12).getContents());
		switchBackFromFrame();
			
		click(adTxtParagrphPpSaveBtn);
		thread();
			
		getObjectText(msgNotificationBar);
		verifyAssertEquals(drplnNewsc.getCell(8, 3).getContents(), getActualObjectTxt);		//	Text/Paragraph Updated Successfully
			
		getObjectText(bfsecfirstText);
		verifyAssertEquals(drplnNewsc.getCell(5, 12).getContents(), getActualObjectTxt);		//	Verify Modified Text
		
		
		//		Add Dynamic Data		//
		click(bfsecAddDataBtn);
		thread();
		try
		{
		verifyAssert(addDynamicDataPopup);
			
		click(dynDtaPpModuleDDArrow);
		enterText(dynDtaPpModuleDDSearchBox, drplnNewsc.getCell(11, 12).getContents());		//	Choose Assets module
		enterKeyInKyBord(dynDtaPpModuleDDSearchBox);
		thread();
		
	//	try
	//	{
		//	verifyObjDisplay(dynDtaEmpSelectAllChkBox);
		//	if(chkObjDisplay == true)
		//	{
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
				}
				catch(Exception e3)
				{
						e3.printStackTrace();
				}
					
				click(bfsecdfFunctionName);
				click(bfsecdfPriority);
				click(bfsecdfEmployeeAssigned);
				click(bfsecdfLossperDay);
									
			//	click(dynDtaAddNewFilterBtn);	//	Add New Filter button
					
				click(dynDtaFieldDDArrow);
				enterText(dynDtaFieldDDSearchBox, "Function Name");		//	Choose Filter Field
				enterKeyInKyBord(dynDtaFieldDDSearchBox);
				thread();
				
				//		Choose Filter Value
				click(dynDtaFieldValueDD);
				thread();
				getTotalValuesIndd(dynDtaFieldValueCnt);
				
				ArrayList<Integer> alist = new ArrayList<Integer>();
				for(int i=1; i<totalDDValCount; i++)
				{
					alist.add(i);
				}
				Collections.shuffle(alist);
				
				for(int i=0; i<2; i++)
				{
					click(dynDtaFieldValueDD);
					Thread.sleep(3000);
					driver.findElement(By.id("dyn_sel_data_fld_val_chzn_o_"+alist.get(i))).click();
					Thread.sleep(1000);
					thread(); 
					click(dynDtaFilterAddBtn);
					thread();
		
				/*	String randomValue = driver.findElement(By.id("ddlDDataNewFilterValues_chzn_o_"+alist.get(i))).getText();
					enterText(dynDtaFieldValueDD, randomValue);
					thread();
					enterKeyInKyBord(dynDtaFieldValueDD);   */
					
					try
					{							
						isAlertPresent();
						getAlertMsgText();
						try
						{
						verifyAssertEquals(enterFilterVal, getActlAlertTxt);
						acceptAlertMessage();
						}
						catch(Exception e)
						{
							acceptAlertMessage();
						}
						break;
					}
					catch(Exception noalrt)
					{
					//	System.out.println("No Alert Message");
						thread();
					}
				}		
					
				thread();
				verifyObjDisplay(By.xpath("//div[@id='cometchat_hide']"));
				if(chkObjDisplay == true)
				{
				click(By.xpath("//div[@id='cometchat_hide']"));
				}
				click(dynDtaPpSaveBtn);
				thread();
		}
		catch(Exception e)
		{
			System.out.println(e);
			click(dynDtaPpCloseBtn);
			thread();
		}
				
				getObjectText(msgNotificationBar);
				verifyAssertEquals(drplnNewsc.getCell(8, 4).getContents(), getActualObjectTxt);		//	Data Created Successfully
				thread();
				
				//		Check Selected Display Order in Table
				for(int i=1; i<5; i++)
				{
					String getColumnName = driver.findElement(By.xpath("//div[@id='divDynamicDRPlanSections']/div[12]//table[@class='table table-responsive table-striped table-bordered bootstrap-datatable dynamic-data-table']//tr/td["+i+"]")).getText();
					if(i == 1)
						verifyAssertEquals(drplnNewsc.getCell(12, 39).getContents(), getColumnName);	//	Function Name
					if(i == 2)
						verifyAssertEquals(drplnNewsc.getCell(12, 40).getContents(), getColumnName);	//	Priority
					if(i == 3)
						verifyAssertEquals(drplnNewsc.getCell(12, 36).getContents(), getColumnName);	//	Employee Assigned
					if(i == 4)
						verifyAssertEquals(drplnNewsc.getCell(12, 41).getContents(), getColumnName.split("\\(")[0]);	//	Loss Per Day
				}
				
				click(bfsecTeamsList);
				thread();
				
				verifyAssert(addDynamicDataPopup);
				
				for(int i=2; i<4; i++)
				{
					boolean checked = driver.findElement(By.xpath("//div[@id='pri-mod-flds-holder']/span["+i+"]/input")).isSelected();
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
				}
				catch(Exception e) 
				{
				//	System.out.println("Selected Filter values are not displaying in Edit screen");
					e.printStackTrace();
				}
				
				try
				{
					verifyObjDisplay(By.xpath("//div[@id='cometchat_hide']"));
					if(chkObjDisplay == true)
					{
					click(By.xpath("//div[@id='cometchat_hide']"));
					}
				click(dynDtaPpSaveBtn);
				thread();
							
				getObjectText(msgNotificationBar);
				verifyAssertEquals(drplnNewsc.getCell(8, 5).getContents(), getActualObjectTxt);		//	Data Updated Successfully
				thread();
				}
				
				catch(Exception e)
				{
					click(dynDtaPpCloseBtn);
					thread();
				}
												
				//		Verify Column Name Order Changes In Table
				for(int i=1; i<5; i++)
				{
					String getColumnName = driver.findElement(By.xpath("//div[@id='divDynamicDRPlanSections']/div[12]//table[@class='table table-responsive table-striped table-bordered bootstrap-datatable dynamic-data-table']//tr/td["+i+"]")).getText();
					if(i == 1)
						verifyAssertEquals(drplnNewsc.getCell(12, 40).getContents(), getColumnName);	//	Priority
					if(i == 2)
						verifyAssertEquals(drplnNewsc.getCell(12, 39).getContents(), getColumnName);	//	Function Name
					if(i == 3)
						verifyAssertEquals(drplnNewsc.getCell(12, 41).getContents(), getColumnName.split("\\(")[0]);	//	Loss Per Day
					if(i == 4)
						verifyAssertEquals(drplnNewsc.getCell(12, 36).getContents(), getColumnName);	//	Employee Assigned
				}				
							
				//		Edit Section		//
				click(bfsecEditSectionBtn);
				thread();
			
				verifyAssert(createNewSectionPopup);
				getObjectText(secPpCreateNewSectionTtl);
				verifyAssertEquals(drplnNewsc.getCell(9, 2).getContents(), getActualObjectTxt);		//	Update Section Pop up Title
			
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
				
			try
			{
				//		Verify Edited Authorized & Verified By Name
				getObjectText1(drEdtSecAuthorizedByLblNm);
				getObjectText(bfsecAuthorizedByNm);
				verifyAssertEquals("Authorized By : "+edtAuthorizedBy, getActualObjectTxt1+" "+getActualObjectTxt);	//	Check Authorized Person Name
			
				getObjectText1(drEdtSecVerifiedByLblNm);
				getObjectText(bfsecVerifiedByNm);
				verifyAssertEquals("Verified By : "+edtVerifiedBy, getActualObjectTxt1+" "+getActualObjectTxt);	//	Check Verified Person Name
				thread();
			}
			catch(Exception e)
			{
			//	System.out.println(e);
			}
				//		Add Text - 2nd Text		//
				click(bfsecAddTextBtn);
			
				verifyAssert(adTxtAddTextParagraphPopup);
				getObjectText(adTxtAddTextParagraphPpTtl);
				verifyAssertEquals(drplnNewsc.getCell(9, 4).getContents(), getActualObjectTxt);		//	Add Text/Paragraph Pop Up Title
				
				getObjectText(adTxtPpEnterSomeTxtLbl);
				verifyAssertEquals(drplnNewsc.getCell(10, 1).getContents(), getActualObjectTxt);		//	Enter Some Text Label Name
				
				webElement(adTxtEnterSomeTextFld);
				switchToWEFrame(webelementFrame);
				enterText(scEdtrCommentsField, drplnNewsc.getCell(6, 12).getContents());		//	Enter Some Texts
				switchBackFromFrame();
			
				click(adTxtParagrphPpSaveBtn);
				thread();
			
				getObjectText(msgNotificationBar);
				verifyAssertEquals(drplnNewsc.getCell(8, 2).getContents(), getActualObjectTxt);		//	Text/Paragraph added successfully 
			
				webElement(bfsec2ndTxt); 
				scrollInnerScrollBar(webelementFrame);		//	Scrolling Section Grid Inner Scroll Bar	
			
				getObjectText(bfsec2ndTxt);
				verifyAssertEquals(drplnNewsc.getCell(6, 12).getContents(), getActualObjectTxt);		//	Verify Entered Text In Section Page
				thread();
			
				//		Mouse Hover - Move Last
				webElement(bfsecmHovrOnFirstOptn);
				mouseHoverOn(webelementFrame);
				click(bfsecmoveLastIcon);				
				getObjectText(bfsec2ndTxt); 
				verifyAssertEquals(drplnNewsc.getCell(5, 12).getContents(), getActualObjectTxt);
				thread();
			
				//	Mouse Hover - Move Top
				webElement(bfsecmHovrOnSecOptn);
				mouseHoverOn(webelementFrame);
				thread();
				webElement(bfsecmoveTopIcon);
				mouseHoverOn(webelementFrame);
				click(bfsecmoveTopIcon);				
				getObjectText(bfsecfirstText);
				verifyAssertEquals(drplnNewsc.getCell(6, 12).getContents(), getActualObjectTxt);	
				thread();
			
				//	Mouse Hover - Move Down
				webElement(bfsecmHovrOnFirstOptn);
				mouseHoverOn(webelementFrame);
				click(bfsecmoveDownIcon);				
				getObjectText(bfsec2ndTxt1);
				verifyAssertEquals(drplnNewsc.getCell(6, 12).getContents(), getActualObjectTxt);
				thread();
			
				//	Mouse Hover - Move Up
				webElement(bfsecmHovrOnSecOptn);
				mouseHoverOn(webelementFrame);
				click(bfsecmoveUpIcon);
				getObjectText(bfsecfirstText);
				verifyAssertEquals(drplnNewsc.getCell(6, 12).getContents(), getActualObjectTxt);
				thread();
			
				//	Mouse Hover - Delete
				webElement(bfsecmHovrOnThirdOptn);
				mouseHoverOn(webelementFrame);
				click(bfsecremoveIcon);
			
				verifyAssert(txtDeleteConfPopup);
				getObjectText(deleteConfPpMessage);
				verifyAssertEquals(drplnNewsc.getCell(8, 6).getContents(), getActualObjectTxt);
				click(delConfOkBtn);
				thread();	
			}
	

}
