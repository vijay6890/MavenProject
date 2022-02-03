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

public class DR_CreateNewSection_AstToCG extends Page 
{

	LoginLogout ll = new LoginLogout();
	DR_CreateEditDRPlan dR_CreateEditDRPlan = new DR_CreateEditDRPlan();
	
	private String filterValueExistsMsg = "This filter exists please try with other options";
	private String enterFilterVal="Please enter filter value";
	
/*	@Test(priority=0)
	void login() throws BiffException, IOException, InterruptedException
	{
		ll.loginToSIB();
	} */
	
	@Test(priority=1)
	void edit() throws InterruptedException
	{
		click(drThreatsnDRPlanInMainMenu);
		waitForPageLoad();
		
		driver.findElement(By.xpath("//table[@id='plan_table']//tbody/tr/td[8]/a")).click();
		thread();
		
	}
	
	
	@Test(priority=2)
	void addAssetsSection() throws InterruptedException, BiffException, IOException
	{
		getDRPlanCreateNewSectionSheetFromExcel();
		 
		click(drEdtSecCreateNewSectionBtn);
		verifyAssert(createNewSectionPopup);
		
		//	Section Title
		click(secPpSectionTitleDDArrow);
		String asecSectionTitle = drplnNewsc.getCell(1, 5).getContents();		//	Add New Section Name (or) Choose Existing Section Name
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
				verifyAssertEquals("Do you want to add "+drplnNewsc.getCell(1, 5).getContents()+" into Section Title ?", getActualObjectTxt);
				click(delConfOkBtn);
				thread();
				
				getObjectText(msgNotificationBar);
				verifyAssertEquals(drplnNewsc.getCell(1, 5).getContents()+" added into Section Title successfully", getActualObjectTxt);		//	Check New Section Added Successfully
				thread();
				
				getObjectText(secPpSectionTitleDefTxt);
				verifyAssertEquals(drplnNewsc.getCell(1, 5).getContents(), getActualObjectTxt);		//	Verify Added Title In Drop Down
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
		enterText(drscEdtrCommentsField, drplnNewsc.getCell(2, 5).getContents());
		switchBackFromFrame();
		takeScreenshot();
		
		click(secPpSaveBtn);
		thread();
			
		getObjectText(msgNotificationBar);
		verifyAssertEquals(drplnNewsc.getCell(8, 7).getContents(), getActualObjectTxt);		//	Section Created Successfully	
		
		getObjectText(asecDescription);
		verifyAssertEquals(drplnNewsc.getCell(2, 5).getContents(), getActualObjectTxt);		//	Check Added Section Description
		
		click(asecDescription);
		thread();
		
		//		Edit Description		//
		verifyAssert(createNewSectionPopup);
		getObjectText(secPpCreateNewSectionTtl);
		verifyAssertEquals(drplnNewsc.getCell(9, 3).getContents(), getActualObjectTxt);		//	Check Update Description Title
		
		webElement(crSectionDescription);
		switchToWEFrame(webelementFrame);
		useKeyFuncToRmvVal(drscEdtrCommentsField);
		enterText(drscEdtrCommentsField, drplnNewsc.getCell(3, 5).getContents());
		switchBackFromFrame();
		
		click(secPpSaveBtn);
		thread();
		
		getObjectText(msgNotificationBar);
		verifyAssertEquals(drplnNewsc.getCell(8, 1).getContents(), getActualObjectTxt);		//	Section Updated Successfully
		thread();	
		
		getObjectText(asecDescription);
		verifyAssertEquals(drplnNewsc.getCell(3, 5).getContents(), getActualObjectTxt);		//	Verify Edited Description Details
		
		try
		{
		//		Authorized & Verified By Person Names		//
		getObjectText1(lsecLblAuthorizedBy);
		getObjectText(asecAuthorizedByNm);
		verifyAssertEquals("Authorized By : "+asecAuthorizedBy, getActualObjectTxt1+" "+getActualObjectTxt);	//	Check Authorized Person Name
		
		getObjectText1(lsecLblVerifiedBy);
		getObjectText(asecVerifiedByNm);
		verifyAssertEquals("Verified By : "+asecVerifiedBy, getActualObjectTxt1+" "+getActualObjectTxt);	//	Check Verified Person Name
		thread();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		
		//		Add Text/Paragraph		//
		click(asecAddTextBtn);
		
		verifyAssert(adTxtAddTextParagraphPopup);
		getObjectText(adTxtAddTextParagraphPpTtl);
		verifyAssertEquals(drplnNewsc.getCell(9, 4).getContents(), getActualObjectTxt);		//	Add Text/Paragraph Pop Up Title
			
		getObjectText(adTxtPpEnterSomeTxtLbl);
		verifyAssertEquals(drplnNewsc.getCell(10, 1).getContents(), getActualObjectTxt);	//	Enter Some Text Label Name
			
		webElement(adTxtEnterSomeTextFld);
		switchToWEFrame(webelementFrame);
		enterText(scEdtrCommentsField, drplnNewsc.getCell(4, 5).getContents());		//	Enter Some Texts
		switchBackFromFrame();
		
		click(adTxtParagrphPpSaveBtn);
		thread();
		
		getObjectText(msgNotificationBar);
		verifyAssertEquals(drplnNewsc.getCell(8, 2).getContents(), getActualObjectTxt);		//	Text/Paragraph added successfully 
		
		getObjectText(asecfirstText);
		verifyAssertEquals(drplnNewsc.getCell(4, 5).getContents(), getActualObjectTxt);		//	Verify Entered Text In Section Page
		
		click(asecfirstText);
		thread();
		
		//		Edit Text		//
		getObjectText(adTxtAddTextParagraphPpTtl);
		verifyAssertEquals(drplnNewsc.getCell(9, 5).getContents(), getActualObjectTxt);		//	Update Text/Paragraph Title
			
		webElement(adTxtEnterSomeTextFld);
		switchToWEFrame(webelementFrame);
		useKeyFuncToRmvVal(scEdtrCommentsField);	//	Edit Some Texts
		enterText(scEdtrCommentsField, drplnNewsc.getCell(5, 5).getContents());
		switchBackFromFrame();
			
		click(adTxtParagrphPpSaveBtn);
		thread();
			
		getObjectText(msgNotificationBar);
		verifyAssertEquals(drplnNewsc.getCell(8, 3).getContents(), getActualObjectTxt);		//	Text/Paragraph Updated Successfully
			
		getObjectText(asecfirstText);
		verifyAssertEquals(drplnNewsc.getCell(5, 5).getContents(), getActualObjectTxt);		//	Verify Modified Text
		
		
		//		Add Dynamic Data		//
		click(asecAddDataBtn);
		thread();
		try
		{
		
		verifyAssert(addDynamicDataPopup);
			
		click(dynDtaPpModuleDDArrow);
		enterText(dynDtaPpModuleDDSearchBox, drplnNewsc.getCell(11, 5).getContents());		//	Choose Assets module
		enterKeyInKyBord(dynDtaPpModuleDDSearchBox);
		thread();
		                                       
		//try
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
					
				click(asecdfAssetName);
				click(asecdfManufacturerName);
				click(asecdfQuantity);
				click(asecdfReplacementValue);
									
			//	click(dynDtaAddNewFilterBtn);	//	Add New Filter button
					
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
					click(dynDtaFieldValueDD);
					driver.findElement(By.id("dyn_sel_data_fld_val_chzn_o_"+alist.get(i))).click();
					thread();                                       

				/*	String randomValue = driver.findElement(By.id("ddlDDataNewFilterValues_chzn_o_"+alist.get(i))).getText();
					enterText(dynDtaFieldValueDD, randomValue);
					enterKeyInKyBord(dynDtaFieldValueDD);*/
	
					click(dynDtaFilterAddBtn);
					
					//		Check the same filter value exists
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
					String getColumnName = driver.findElement(By.xpath("//div[@id='divDynamicDRPlanSections']/div[5]//table[@class='table table-responsive table-striped table-bordered bootstrap-datatable dynamic-data-table']//tr/td["+i+"]")).getText();
					if(i == 1)
						verifyAssertEquals(drplnNewsc.getCell(12, 15).getContents(), getColumnName);	//	Asset Name
					if(i == 2)
						verifyAssertEquals(drplnNewsc.getCell(12, 16).getContents(), getColumnName);	//	Manufacturer Name
					if(i == 3)
						verifyAssertEquals(drplnNewsc.getCell(12, 17).getContents(), getColumnName);	//	Quantity
					if(i == 4)
					{
						String val[]=getColumnName.split("\\(",2);
						verifyAssertEquals(drplnNewsc.getCell(12, 18).getContents(), val[0]);	//	Replacement Value
					}
				}
				
				thread();
				click(asecTeamsList);
				thread();
				
				verifyAssert(addDynamicDataPopup);
				
				for(int i=2; i<4; i++)
				{
					boolean checked = driver.findElement(By.xpath("//div[@id='pri-mod-flds-holder']/span["+i+"]/input")).isSelected();
					Assert.assertEquals(true, checked);
				}
				thread();
						
				//	Drag & Drop the objects
				
				sourceElement(dynDtaEmpWeFirstName);
				destinationElement(dynDtaEmpWeLastName);
				dragAndDrop(sourceObj, destinationObj);					//	Change Asset & Manufacturer Name position				
							
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
					String getColumnName = driver.findElement(By.xpath("//div[@id='divDynamicDRPlanSections']/div[5]//table[@class='table table-responsive table-striped table-bordered bootstrap-datatable dynamic-data-table']//tr/td["+i+"]")).getText();
					if(i == 1)
						//verifyAssertEquals(drplnNewsc.getCell(12, 16).getContents(), getColumnName);	//	Manufacturer Name
					if(i == 2)
					//	verifyAssertEquals(drplnNewsc.getCell(12, 15).getContents(), getColumnName);	//	Asset Name
					if(i == 3)
					{
						String val[]=getColumnName.split("\\(",2);
						verifyAssertEquals(drplnNewsc.getCell(12, 18).getContents(), val[0]);	//	Replacement Value
					}
					if(i == 4)
						verifyAssertEquals(drplnNewsc.getCell(12, 17).getContents(), getColumnName);	//	Quantity
				}				
							
				//		Edit Section		//
				click(asecEditSectionBtn);
				thread();
			
				verifyAssert(createNewSectionPopup);
				getObjectText(secPpCreateNewSectionTtl);
				verifyAssertEquals(drplnNewsc.getCell(9, 2).getContents(), getActualObjectTxt);		//	Update Section Pop up Title
			
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
				
				
			try
			{
				//		Verify Edited Authorized & Verified By Name
				getObjectText1(drEdtSecAuthorizedByLblNm);
				getObjectText(asecAuthorizedByNm);
				verifyAssertEquals("Authorized By : "+edtAuthorizedBy, getActualObjectTxt1+" "+getActualObjectTxt);	//	Check Authorized Person Name
			
				getObjectText1(drEdtSecVerifiedByLblNm);
				getObjectText(asecVerifiedByNm);
				verifyAssertEquals("Verified By : "+edtVerifiedBy, getActualObjectTxt1+" "+getActualObjectTxt);	//	Check Verified Person Name
				thread();
			}
			catch(Exception e)
			{
			//	System.out.println(e);
			}
			
				//		Add Text - 2nd Text		//
				click(asecAddTextBtn);
			
				verifyAssert(adTxtAddTextParagraphPopup);
				getObjectText(adTxtAddTextParagraphPpTtl);
				verifyAssertEquals(drplnNewsc.getCell(9, 4).getContents(), getActualObjectTxt);		//	Add Text/Paragraph Pop Up Title
				
				getObjectText(adTxtPpEnterSomeTxtLbl);
				verifyAssertEquals(drplnNewsc.getCell(10, 1).getContents(), getActualObjectTxt);		//	Enter Some Text Label Name
				
				webElement(adTxtEnterSomeTextFld);
				switchToWEFrame(webelementFrame);
				enterText(scEdtrCommentsField, drplnNewsc.getCell(6, 5).getContents());		//	Enter Some Texts
				switchBackFromFrame();
			
				click(adTxtParagrphPpSaveBtn);
				thread();
			
				getObjectText(msgNotificationBar);
				verifyAssertEquals(drplnNewsc.getCell(8, 2).getContents(), getActualObjectTxt);		//	Text/Paragraph added successfully 
			
				webElement(asec2ndTxt); 
				scrollInnerScrollBar(webelementFrame);		//	Scrolling Section Grid Inner Scroll Bar	
			
				getObjectText(asec2ndTxt);
				verifyAssertEquals(drplnNewsc.getCell(6, 5).getContents(), getActualObjectTxt);		//	Verify Entered Text In Section Page
				thread();
			
				//		Mouse Hover - Move Last
				webElement(asecmHovrOnFirstOptn);
				mouseHoverOn(webelementFrame);
				click(asecmoveLastIcon);				
				getObjectText(asec2ndTxt); 
				verifyAssertEquals(drplnNewsc.getCell(5, 5).getContents(), getActualObjectTxt);
				thread();
			
				//	Mouse Hover - Move Top
				webElement(asecmHovrOnSecOptn);
				mouseHoverOn(webelementFrame);
				thread();
				webElement(asecmoveTopIcon);
				mouseHoverOn(webelementFrame);
				click(asecmoveTopIcon);				
				getObjectText(asecfirstText);
				verifyAssertEquals(drplnNewsc.getCell(6, 5).getContents(), getActualObjectTxt);	
				thread();
			
				//	Mouse Hover - Move Down
				webElement(asecmHovrOnFirstOptn);
				mouseHoverOn(webelementFrame);
				click(asecmoveDownIcon);				
				getObjectText(asec2ndTxt1);
				verifyAssertEquals(drplnNewsc.getCell(6, 5).getContents(), getActualObjectTxt);
				thread();
			
				//	Mouse Hover - Move Up
				webElement(asecmHovrOnSecOptn);
				mouseHoverOn(webelementFrame);
				click(asecmoveUpIcon);
				getObjectText(asecfirstText);
				verifyAssertEquals(drplnNewsc.getCell(6, 5).getContents(), getActualObjectTxt);
				thread();
			
				//	Mouse Hover - Delete
				webElement(asecmHovrOnThirdOptn);
				mouseHoverOn(webelementFrame);
				click(asecremoveIcon);
			
				verifyAssert(txtDeleteConfPopup);
				getObjectText(deleteConfPpMessage);
				verifyAssertEquals(drplnNewsc.getCell(8, 6).getContents(), getActualObjectTxt);
				click(delConfOkBtn);
				thread();	
			} 
	                                      

	
	@Test(priority=3)
	void addAssetGroupsSection() throws InterruptedException, BiffException, IOException
	{
		getDRPlanCreateNewSectionSheetFromExcel();
		 
		click(drEdtSecCreateNewSectionBtn);
		verifyAssert(createNewSectionPopup);
		
		//	Section Title
		click(secPpSectionTitleDDArrow);
		String agsecSectionTitle = drplnNewsc.getCell(1, 6).getContents();		//	Add New Section Name (or) Choose Existing Section Name
		enterText(secPpSectionTitleDDSearchBox, agsecSectionTitle);
		thread();
		enterKeyInKyBord(secPpSectionTitleDDSearchBox);
		thread();
		
		try
		{
			verifyObjDisplay(clickToAddTxtInDD);
			if(chkObjDisplay == true)
			{
				click(clickToAddTxtInDD);
				thread();
				enterKeyInKyBord(secPpSectionTitleDDSearchBox);
				thread();
				verifyAssert(sectionAddConfPopup);
				getObjectText(deleteConfPpMessage); 
				thread();
				verifyAssertEquals("Do you want to add "+drplnNewsc.getCell(1, 6).getContents()+" into Section Title ?", getActualObjectTxt);
				thread();
				click(delConfOkBtn);
				thread();
				
				getObjectText(msgNotificationBar);
				verifyAssertEquals(drplnNewsc.getCell(1, 6).getContents()+" added into Section Title successfully", getActualObjectTxt);		//	Check New Section Added Successfully
				thread();
				
				getObjectText(secPpSectionTitleDefTxt);
				verifyAssertEquals(drplnNewsc.getCell(1, 6).getContents(), getActualObjectTxt);		//	Verify Added Title In Drop Down
			}
		}
		catch(Exception e)
		{
			thread();
		//	arrowDown(secPpSectionTitleDDSearchBox);
		//	enterKeyInKyBord(secPpSectionTitleDDSearchBox);
		//	System.out.println("Title Already Exists");
			
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
		enterText(drscEdtrCommentsField, drplnNewsc.getCell(2, 6).getContents());
		switchBackFromFrame();
		takeScreenshot();
		
		click(secPpSaveBtn);
		thread();
			
		getObjectText(msgNotificationBar);
		verifyAssertEquals(drplnNewsc.getCell(8, 7).getContents(), getActualObjectTxt);		//	Section Created Successfully	
		
		getObjectText(agsecDescription);
		verifyAssertEquals(drplnNewsc.getCell(2, 6).getContents(), getActualObjectTxt);		//	Check Added Section Description
		
		click(agsecDescription);
		thread();
		
		
		//		Edit Description		//
		verifyAssert(createNewSectionPopup);
		getObjectText(secPpCreateNewSectionTtl);
		verifyAssertEquals(drplnNewsc.getCell(9, 3).getContents(), getActualObjectTxt);		//	Check Update Description Title
		
		webElement(crSectionDescription);
		switchToWEFrame(webelementFrame);
		useKeyFuncToRmvVal(drscEdtrCommentsField);
		enterText(drscEdtrCommentsField, drplnNewsc.getCell(3, 6).getContents());
		switchBackFromFrame();
		try
		{
		click(secPpSaveBtn);
		thread();
		
		getObjectText(msgNotificationBar);
		verifyAssertEquals(drplnNewsc.getCell(8, 1).getContents(), getActualObjectTxt);		//	Section Updated Successfully
		thread();	
		}
		catch(Exception e)
		{
			click(secEditCloseBtn);
			thread();
		}
		
		getObjectText(agsecDescription);
		verifyAssertEquals(drplnNewsc.getCell(3, 6).getContents(), getActualObjectTxt);		//	Verify Edited Description Details
		
		
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
		verifyAssertEquals(drplnNewsc.getCell(9, 4).getContents(), getActualObjectTxt);		//	Add Text/Paragraph Pop Up Title
			
		getObjectText(adTxtPpEnterSomeTxtLbl);
		verifyAssertEquals(drplnNewsc.getCell(10, 1).getContents(), getActualObjectTxt);	//	Enter Some Text Label Name
			
		webElement(adTxtEnterSomeTextFld);
		switchToWEFrame(webelementFrame);
		enterText(scEdtrCommentsField, drplnNewsc.getCell(4, 6).getContents());		//	Enter Some Texts
		switchBackFromFrame();
		
		click(adTxtParagrphPpSaveBtn);
		thread();
		
		getObjectText(msgNotificationBar);
		verifyAssertEquals(drplnNewsc.getCell(8, 2).getContents(), getActualObjectTxt);		//	Text/Paragraph added successfully 
		
		getObjectText(agsecfirstText);
		verifyAssertEquals(drplnNewsc.getCell(4, 6).getContents(), getActualObjectTxt);		//	Verify Entered Text In Section Page
		
		click(agsecfirstText);
		thread();
		
		//		Edit Text		//
		getObjectText(adTxtAddTextParagraphPpTtl);
		verifyAssertEquals(drplnNewsc.getCell(9, 5).getContents(), getActualObjectTxt);		//	Update Text/Paragraph Title
			
		webElement(adTxtEnterSomeTextFld);
		switchToWEFrame(webelementFrame);
		useKeyFuncToRmvVal(scEdtrCommentsField);	//	Edit Some Texts
		enterText(scEdtrCommentsField, drplnNewsc.getCell(5, 6).getContents());
		switchBackFromFrame();
			
		click(adTxtParagrphPpSaveBtn);
		thread();
			
		getObjectText(msgNotificationBar);
		verifyAssertEquals(drplnNewsc.getCell(8, 3).getContents(), getActualObjectTxt);		//	Text/Paragraph Updated Successfully
			
		getObjectText(agsecfirstText);
		verifyAssertEquals(drplnNewsc.getCell(5, 6).getContents(), getActualObjectTxt);		//	Verify Modified Text
		
		
		//		Add Dynamic Data		//
		click(agsecAddDataBtn);
		thread();
		try
		{
		
		verifyAssert(addDynamicDataPopup);
			
		click(dynDtaPpModuleDDArrow);
		enterText(dynDtaPpModuleDDSearchBox, drplnNewsc.getCell(11, 6).getContents());		//	Choose Assets module
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
					
				click(agsecdfGroupName);
				click(agsecdfAssetGroupId);
				click(agsecdfComments);
													
			//	click(dynDtaAddNewFilterBtn);	//	Add New Filter button
					
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
					click(dynDtaFieldValueDD);
					driver.findElement(By.id("dyn_sel_data_fld_val_chzn_o_"+alist.get(i))).click();
					Thread.sleep(1000);
					thread(); 
				
        			/*	String randomValue = driver.findElement(By.id("ddlDDataNewFilterValues_chzn_o_"+alist.get(i))).getText();
					enterText(dynDtaFieldValueDD, randomValue);
					enterKeyInKyBord(dynDtaFieldValueDD);*/
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
						//acceptAlertMessage();
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
				for(int i=1; i<4; i++)
				{
					String getColumnName = driver.findElement(By.xpath("//div[@id='divDynamicDRPlanSections']/div[6]//table[@class='table table-responsive table-striped table-bordered bootstrap-datatable dynamic-data-table']//tr/td["+i+"]")).getText();
					if(i == 1)
						verifyAssertEquals(drplnNewsc.getCell(12, 19).getContents(), getColumnName);	//	Group Name
					if(i == 2)
						verifyAssertEquals(drplnNewsc.getCell(12, 20).getContents(), getColumnName);	//	Asset Group ID
					if(i == 3)
						verifyAssertEquals(drplnNewsc.getCell(12, 10).getContents(), getColumnName);	//	Comments
				}
				
				thread();
				driver.findElement(By.xpath("//div[@id='divDynamicDRPlanSections']/div[6]/div[2]/div[2]/div[1]/div/div[2]/div[2]/div[2]/table/thead/tr/td[1]")).click();
				//driver.findElement(By.xpath("//div[@id='divDynamicDRPlanSections']/div[6]/div[2]/div[2]/div[1]/div/div[2]/div[2]/div[2]")).click();
				//click(agsecTeamsList);
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
				for(int i=1; i<4; i++)
				{
					String getColumnName = driver.findElement(By.xpath("//div[@id='divDynamicDRPlanSections']/div[6]//table[@class='table table-responsive table-striped table-bordered bootstrap-datatable dynamic-data-table']//tr/td["+i+"]")).getText();
					if(i == 1)
						verifyAssertEquals(drplnNewsc.getCell(12, 20).getContents(), getColumnName);	//	Asset Group ID
					if(i == 2)
						verifyAssertEquals(drplnNewsc.getCell(12, 10).getContents(), getColumnName);	//	Comments
					if(i == 3)
						verifyAssertEquals(drplnNewsc.getCell(12, 19).getContents(), getColumnName);	//	Group Name
				}				
							
				//		Edit Section		//
				click(agsecEditSectionBtn);
				thread();
			
				verifyAssert(createNewSectionPopup);
				getObjectText(secPpCreateNewSectionTtl);
				verifyAssertEquals(drplnNewsc.getCell(9, 2).getContents(), getActualObjectTxt);		//	Update Section Pop up Title
			
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
				verifyAssertEquals(drplnNewsc.getCell(9, 4).getContents(), getActualObjectTxt);		//	Add Text/Paragraph Pop Up Title
				
				getObjectText(adTxtPpEnterSomeTxtLbl);
				verifyAssertEquals(drplnNewsc.getCell(10, 1).getContents(), getActualObjectTxt);		//	Enter Some Text Label Name
				
				webElement(adTxtEnterSomeTextFld);
				switchToWEFrame(webelementFrame);
				enterText(scEdtrCommentsField, drplnNewsc.getCell(6, 6).getContents());		//	Enter Some Texts
				switchBackFromFrame();
			
				click(adTxtParagrphPpSaveBtn);
				thread();
			
				getObjectText(msgNotificationBar);
				verifyAssertEquals(drplnNewsc.getCell(8, 2).getContents(), getActualObjectTxt);		//	Text/Paragraph added successfully 
			
				webElement(agsec2ndTxt); 
				scrollInnerScrollBar(webelementFrame);		//	Scrolling Section Grid Inner Scroll Bar	
			
				getObjectText(agsec2ndTxt);
				verifyAssertEquals(drplnNewsc.getCell(6, 6).getContents(), getActualObjectTxt);		//	Verify Entered Text In Section Page
				thread();
			
				//		Mouse Hover - Move Last
				webElement(agsecmHovrOnFirstOptn);
				mouseHoverOn(webelementFrame);
				click(agsecmoveLastIcon);				
				getObjectText(agsec2ndTxt); 
				verifyAssertEquals(drplnNewsc.getCell(5, 6).getContents(), getActualObjectTxt);
				thread();
			
				//	Mouse Hover - Move Top
				webElement(agsecmHovrOnSecOptn);
				mouseHoverOn(webelementFrame);
				thread();
				webElement(agsecmoveTopIcon);
				mouseHoverOn(webelementFrame);
				click(agsecmoveTopIcon);				
				getObjectText(agsecfirstText);
				verifyAssertEquals(drplnNewsc.getCell(6, 6).getContents(), getActualObjectTxt);	
				thread();
			
				//	Mouse Hover - Move Down
				webElement(agsecmHovrOnFirstOptn);
				mouseHoverOn(webelementFrame);
				click(agsecmoveDownIcon);				
				getObjectText(agsec2ndTxt1);
				verifyAssertEquals(drplnNewsc.getCell(6, 6).getContents(), getActualObjectTxt);
				thread();
			
				//	Mouse Hover - Move Up
				webElement(agsecmHovrOnSecOptn);
				mouseHoverOn(webelementFrame);
				click(agsecmoveUpIcon);
				getObjectText(agsecfirstText);
				verifyAssertEquals(drplnNewsc.getCell(6, 6).getContents(), getActualObjectTxt);
				thread();
			
				//	Mouse Hover - Delete
				webElement(agsecmHovrOnThirdOptn);
				mouseHoverOn(webelementFrame);
				click(agsecremoveIcon);
				Thread.sleep(1000);
				thread();
			
				verifyAssert(txtDeleteConfPopup);
				getObjectText(deleteConfPpMessage);
				verifyAssertEquals(drplnNewsc.getCell(8, 6).getContents(), getActualObjectTxt);
				click(delConfOkBtn);
				thread();	
			}
	
	                              


	
	@Test(priority=5)
	void addContactsSection() throws InterruptedException, BiffException, IOException
	{
		getDRPlanCreateNewSectionSheetFromExcel();
		 
		click(drEdtSecCreateNewSectionBtn);
		verifyAssert(createNewSectionPopup);
		
		//	Section Title
		click(secPpSectionTitleDDArrow);
		String csecSectionTitle = drplnNewsc.getCell(1, 7).getContents();		//	Add New Section Name (or) Choose Existing Section Name
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
				verifyAssertEquals("Do you want to add "+drplnNewsc.getCell(1, 7).getContents()+" into Section Title ?", getActualObjectTxt);
				click(delConfOkBtn);
				thread();
				
				getObjectText(msgNotificationBar);
				verifyAssertEquals(drplnNewsc.getCell(1, 7).getContents()+" added into Section Title successfully", getActualObjectTxt);		//	Check New Section Added Successfully
				thread();
				
				getObjectText(secPpSectionTitleDefTxt);
				verifyAssertEquals(drplnNewsc.getCell(1, 7).getContents(), getActualObjectTxt);		//	Verify Added Title In Drop Down
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
		enterText(drscEdtrCommentsField, drplnNewsc.getCell(2, 7).getContents());
		switchBackFromFrame();
		takeScreenshot();
		
		click(secPpSaveBtn);
		thread();
			
		getObjectText(msgNotificationBar);
		verifyAssertEquals(drplnNewsc.getCell(8, 7).getContents(), getActualObjectTxt);		//	Section Created Successfully	
		
		getObjectText(csecDescription);
		verifyAssertEquals(drplnNewsc.getCell(2, 7).getContents(), getActualObjectTxt);		//	Check Added Section Description
		
		click(csecDescription);
		thread();
		
		//		Edit Description		//
		verifyAssert(createNewSectionPopup);
		getObjectText(secPpCreateNewSectionTtl);
		verifyAssertEquals(drplnNewsc.getCell(9, 3).getContents(), getActualObjectTxt);		//	Check Update Description Title
		
		webElement(crSectionDescription);
		switchToWEFrame(webelementFrame);
		useKeyFuncToRmvVal(drscEdtrCommentsField);
		enterText(drscEdtrCommentsField, drplnNewsc.getCell(3, 7).getContents());
		switchBackFromFrame();
		
		click(secPpSaveBtn);
		thread();
		
		getObjectText(msgNotificationBar);
		verifyAssertEquals(drplnNewsc.getCell(8, 1).getContents(), getActualObjectTxt);		//	Section Updated Successfully
		thread();	
		
		getObjectText(csecDescription);
		verifyAssertEquals(drplnNewsc.getCell(3, 7).getContents(), getActualObjectTxt);		//	Verify Edited Description Details
		
		
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
		verifyAssertEquals(drplnNewsc.getCell(9, 4).getContents(), getActualObjectTxt);		//	Add Text/Paragraph Pop Up Title
			
		getObjectText(adTxtPpEnterSomeTxtLbl);
		verifyAssertEquals(drplnNewsc.getCell(10, 1).getContents(), getActualObjectTxt);	//	Enter Some Text Label Name
			
		webElement(adTxtEnterSomeTextFld);
		switchToWEFrame(webelementFrame);
		enterText(scEdtrCommentsField, drplnNewsc.getCell(4, 7).getContents());		//	Enter Some Texts
		switchBackFromFrame();
		
		click(adTxtParagrphPpSaveBtn);
		thread();
		
		getObjectText(msgNotificationBar);
		verifyAssertEquals(drplnNewsc.getCell(8, 2).getContents(), getActualObjectTxt);		//	Text/Paragraph added successfully 
		
		getObjectText(csecfirstText);
		verifyAssertEquals(drplnNewsc.getCell(4, 7).getContents(), getActualObjectTxt);		//	Verify Entered Text In Section Page
		
		click(csecfirstText);
		thread();
		
		//		Edit Text		//
		getObjectText(adTxtAddTextParagraphPpTtl);
		verifyAssertEquals(drplnNewsc.getCell(9, 5).getContents(), getActualObjectTxt);		//	Update Text/Paragraph Title
			
		webElement(adTxtEnterSomeTextFld);
		switchToWEFrame(webelementFrame);
		useKeyFuncToRmvVal(scEdtrCommentsField);	//	Edit Some Texts
		enterText(scEdtrCommentsField, drplnNewsc.getCell(5, 7).getContents());
		switchBackFromFrame();
			
		click(adTxtParagrphPpSaveBtn);
		thread();
			
		getObjectText(msgNotificationBar);
		verifyAssertEquals(drplnNewsc.getCell(8, 3).getContents(), getActualObjectTxt);		//	Text/Paragraph Updated Successfully
			
		getObjectText(csecfirstText);
		verifyAssertEquals(drplnNewsc.getCell(5, 7).getContents(), getActualObjectTxt);		//	Verify Modified Text
		
		
		//		Add Dynamic Data		//
		click(csecAddDataBtn);
		thread();
		try
		{
		verifyAssert(addDynamicDataPopup);
			
		click(dynDtaPpModuleDDArrow);
		enterText(dynDtaPpModuleDDSearchBox, drplnNewsc.getCell(11, 7).getContents());		//	Choose Assets module
		enterKeyInKyBord(dynDtaPpModuleDDSearchBox);
		thread();
		
		//try
	//	{
		//	verifyObjDisplay(dynDtaEmpSelectAllChkBox);
		//	if(chkObjDisplay == true)
		//	{
				//	Enable Select All Check Box
				click(dynDtaEmpSelectAllChkBox);
				Thread.sleep(1000);
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
					
				click(csecdfCompanyName);
				click(csecdfCountry);
				click(csecdfCity);
				click(csecdfZipcode);
				click(csecdfpFirstName);
				click(csecdfpLastName);
				click(csecdfpBusinessEmail);
				click(csecdfpMobileNo);
									
			//	click(dynDtaAddNewFilterBtn);	//	Add New Filter button
					
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
				thread();
				for(int i=0; i<2; i++)
				{
					click(dynDtaFieldValueDD);
					thread();
					driver.findElement(By.id("dyn_sel_data_fld_val_chzn_o_"+alist.get(i))).click();
					Thread.sleep(10000);
					click(dynDtaFilterAddBtn);
					thread();
					/*String randomValue = driver.findElement(By.id("ddlDDataNewFilterValues_chzn_o_"+alist.get(i))).getText();
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
				for(int i=1; i<9; i++)
				{
					String getColumnName = driver.findElement(By.xpath("//div[@id='divDynamicDRPlanSections']/div[7]//table[@class='table table-responsive table-striped table-bordered bootstrap-datatable dynamic-data-table']//tr/td["+i+"]")).getText();
					if(i == 1)
						verifyAssertEquals(drplnNewsc.getCell(12, 21).getContents(), getColumnName);	//	Company Name
					if(i == 2)
						verifyAssertEquals(drplnNewsc.getCell(12, 22).getContents(), getColumnName);	//	Country
					if(i == 3)
						verifyAssertEquals(drplnNewsc.getCell(13, 1).getContents(), getColumnName);	//	City
					if(i == 4)
						verifyAssertEquals(drplnNewsc.getCell(12, 8).getContents(), getColumnName);	//	Zip Code
					if(i == 5)
						verifyAssertEquals(drplnNewsc.getCell(12, 23).getContents(), getColumnName);	//	Primary First Name
					if(i == 6)
						verifyAssertEquals(drplnNewsc.getCell(12, 24).getContents(), getColumnName);	//	Primary Last Name
					if(i == 7)
						verifyAssertEquals(drplnNewsc.getCell(12, 25).getContents(), getColumnName);	//	Primary Business Email
					if(i == 8)
						verifyAssertEquals(drplnNewsc.getCell(12, 26).getContents(), getColumnName);	//	Primary Mobile No
				}
				thread();
				click(csecTeamsList);
				thread();
				
				verifyAssert(addDynamicDataPopup);
				
				for(int i=2; i<4; i++)
				{
					boolean checked = driver.findElement(By.xpath("//div[@id='pri-mod-flds-holder']/span["+i+"]/input")).isSelected();
					Assert.assertEquals(true, checked);
				}
				thread();
						
				//	Drag & Drop the objects
			/*	sourceElement(dynDtaEmpWeFirstName);
				destinationElement(dynDtaEmpWeLastName);
				dragAndDrop(sourceObj, destinationObj);		//	Change Company Name & Country position	
				thread();*/
							
				sourceElement(dynDtaEmpWeMobile);
				destinationElement(dynDtaEmpWeAlternateEmployee);
				dragAndDrop(sourceObj, destinationObj);		//	Change City & Zip Code position		
				thread();
				
			/*	sourceElement(dynDtaDisplayFldFifthWe);
				destinationElement(dynDtaDisplayFldSixthWe);
				dragAndDrop(sourceObj, destinationObj);		//	Change Primary First & Last Name position
				thread();
						
				sourceElement(dynDtaDisplayFldSeventhWe);
				destinationElement(dynDtaDisplayFldEigthWe);
				dragAndDrop(sourceObj, destinationObj);		//	Change Primary Business Email & Mobile No position
				thread();*/
	
				
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
				for(int i=1; i<9; i++)
				{
					String getColumnName = driver.findElement(By.xpath("//div[@id='divDynamicDRPlanSections']/div[7]//table[@class='table table-responsive table-striped table-bordered bootstrap-datatable dynamic-data-table']//tr/td["+i+"]")).getText();
				/*	if(i == 1)
						verifyAssertEquals(drplnNewsc.getCell(12, 22).getContents(), getColumnName);	//	Country
					if(i == 2)
						verifyAssertEquals(drplnNewsc.getCell(12, 21).getContents(), getColumnName);	//	Company Name*/
					if(i == 3)
						verifyAssertEquals(drplnNewsc.getCell(12, 8).getContents(), getColumnName);	//	Zip Code
					if(i == 4)
						verifyAssertEquals(drplnNewsc.getCell(13, 1).getContents(), getColumnName);	//	City
				/*	if(i == 5)
						verifyAssertEquals(drplnNewsc.getCell(12, 24).getContents(), getColumnName);	//	Primary Last Name
					if(i == 6)
						verifyAssertEquals(drplnNewsc.getCell(12, 23).getContents(), getColumnName);	//	Primary First Name
					if(i == 7)
						verifyAssertEquals(drplnNewsc.getCell(12, 26).getContents(), getColumnName);	//	Primary Mobile No
					if(i == 8)
						verifyAssertEquals(drplnNewsc.getCell(12, 25).getContents(), getColumnName);	//	Primary Business Email
						*/
				}				
							
				//		Edit Section		//
				click(csecEditSectionBtn);
				thread();
			
				verifyAssert(createNewSectionPopup);
				getObjectText(secPpCreateNewSectionTtl);
				verifyAssertEquals(drplnNewsc.getCell(9, 2).getContents(), getActualObjectTxt);		//	Update Section Pop up Title
			
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
				
				try
				{
				//		Verify Edited Authorized & Verified By Name
				getObjectText1(drEdtSecAuthorizedByLblNm);
				getObjectText(csecAuthorizedByNm);
				verifyAssertEquals("Authorized By : "+edtAuthorizedBy, getActualObjectTxt1+" "+getActualObjectTxt);	//	Check Authorized Person Name
			
				getObjectText1(drEdtSecVerifiedByLblNm);
				getObjectText(csecVerifiedByNm);
				verifyAssertEquals("Verified By : "+edtVerifiedBy, getActualObjectTxt1+" "+getActualObjectTxt);	//	Check Verified Person Name
				thread();
				}
				catch(Exception e)
				{
				//	System.out.println(e);
				}
			
				//		Add Text - 2nd Text		//
				click(csecAddTextBtn);
			
				verifyAssert(adTxtAddTextParagraphPopup);
				getObjectText(adTxtAddTextParagraphPpTtl);
				verifyAssertEquals(drplnNewsc.getCell(9, 4).getContents(), getActualObjectTxt);		//	Add Text/Paragraph Pop Up Title
				
				getObjectText(adTxtPpEnterSomeTxtLbl);
				verifyAssertEquals(drplnNewsc.getCell(10, 1).getContents(), getActualObjectTxt);		//	Enter Some Text Label Name
				
				webElement(adTxtEnterSomeTextFld);
				switchToWEFrame(webelementFrame);
				enterText(scEdtrCommentsField, drplnNewsc.getCell(6, 7).getContents());		//	Enter Some Texts
				switchBackFromFrame();
			
				click(adTxtParagrphPpSaveBtn);
				thread();
			
				getObjectText(msgNotificationBar);
				verifyAssertEquals(drplnNewsc.getCell(8, 2).getContents(), getActualObjectTxt);		//	Text/Paragraph added successfully 
			
				webElement(csec2ndTxt); 
				scrollInnerScrollBar(webelementFrame);		//	Scrolling Section Grid Inner Scroll Bar	
			
				getObjectText(csec2ndTxt);
				verifyAssertEquals(drplnNewsc.getCell(6, 7).getContents(), getActualObjectTxt);		//	Verify Entered Text In Section Page
				thread();
			
				//		Mouse Hover - Move Last
				webElement(csecmHovrOnFirstOptn);
				mouseHoverOn(webelementFrame);
				click(csecmoveLastIcon);				
				getObjectText(csec2ndTxt); 
				verifyAssertEquals(drplnNewsc.getCell(5, 7).getContents(), getActualObjectTxt);
				thread();
			
				//	Mouse Hover - Move Top
				webElement(csecmHovrOnSecOptn);
				mouseHoverOn(webelementFrame);
				thread();
				webElement(csecmoveTopIcon);
				mouseHoverOn(webelementFrame);
				click(csecmoveTopIcon);				
				getObjectText(csecfirstText);
				verifyAssertEquals(drplnNewsc.getCell(6, 7).getContents(), getActualObjectTxt);	
				thread();
			
				//	Mouse Hover - Move Down
				webElement(csecmHovrOnFirstOptn);
				mouseHoverOn(webelementFrame);
				click(csecmoveDownIcon);				
				getObjectText(csec2ndTxt1);
				verifyAssertEquals(drplnNewsc.getCell(6, 7).getContents(), getActualObjectTxt);
				thread();
			
				//	Mouse Hover - Move Up
				webElement(csecmHovrOnSecOptn);
				mouseHoverOn(webelementFrame);
				click(csecmoveUpIcon);
				getObjectText(csecfirstText);
				verifyAssertEquals(drplnNewsc.getCell(6, 7).getContents(), getActualObjectTxt);
				thread();
			
				//	Mouse Hover - Delete
				webElement(csecmHovrOnThirdOptn);
				mouseHoverOn(webelementFrame);
				click(csecremoveIcon);
			
				verifyAssert(txtDeleteConfPopup);
				getObjectText(deleteConfPpMessage);
				verifyAssertEquals(drplnNewsc.getCell(8, 6).getContents(), getActualObjectTxt);
				click(delConfOkBtn);
				thread();	
			}  
	
	                                        

	@Test(priority=6)
	void addContactGroupsSection() throws InterruptedException, BiffException, IOException
	{
		getDRPlanCreateNewSectionSheetFromExcel();
		 
		click(drEdtSecCreateNewSectionBtn);
		verifyAssert(createNewSectionPopup);
		
		//	Section Title
		click(secPpSectionTitleDDArrow);
		String cgsecSectionTitle = drplnNewsc.getCell(1, 8).getContents();		//	Add New Section Name (or) Choose Existing Section Name
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
				verifyAssertEquals("Do you want to add "+drplnNewsc.getCell(1, 8).getContents()+" into Section Title ?", getActualObjectTxt);
				click(delConfOkBtn);
				thread();
				
				getObjectText(msgNotificationBar);
				verifyAssertEquals(drplnNewsc.getCell(1, 8).getContents()+" added into Section Title successfully", getActualObjectTxt);		//	Check New Section Added Successfully
				thread();
				
				getObjectText(secPpSectionTitleDefTxt);
				verifyAssertEquals(drplnNewsc.getCell(1, 8).getContents(), getActualObjectTxt);		//	Verify Added Title In Drop Down
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
		enterText(drscEdtrCommentsField, drplnNewsc.getCell(2, 8).getContents());
		switchBackFromFrame();
		takeScreenshot();
		
		click(secPpSaveBtn);
		thread();
			
		getObjectText(msgNotificationBar);
		verifyAssertEquals(drplnNewsc.getCell(8, 7).getContents(), getActualObjectTxt);		//	Section Created Successfully	
		
		getObjectText(cgsecDescription);
		verifyAssertEquals(drplnNewsc.getCell(2, 8).getContents(), getActualObjectTxt);		//	Check Added Section Description
		
		click(cgsecDescription);
		thread();
		
		//		Edit Description		//
		verifyAssert(createNewSectionPopup);
		getObjectText(secPpCreateNewSectionTtl);
		verifyAssertEquals(drplnNewsc.getCell(9, 3).getContents(), getActualObjectTxt);		//	Check Update Description Title
		
		webElement(crSectionDescription);
		switchToWEFrame(webelementFrame);
		useKeyFuncToRmvVal(drscEdtrCommentsField);
		enterText(drscEdtrCommentsField, drplnNewsc.getCell(3, 8).getContents());
		switchBackFromFrame();
		
		click(secPpSaveBtn);
		thread();
		
		getObjectText(msgNotificationBar);
		verifyAssertEquals(drplnNewsc.getCell(8, 1).getContents(), getActualObjectTxt);		//	Section Updated Successfully
		thread();	
		
		getObjectText(cgsecDescription);
		verifyAssertEquals(drplnNewsc.getCell(3, 8).getContents(), getActualObjectTxt);		//	Verify Edited Description Details
		
		
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
		verifyAssertEquals(drplnNewsc.getCell(9, 4).getContents(), getActualObjectTxt);		//	Add Text/Paragraph Pop Up Title
			
		getObjectText(adTxtPpEnterSomeTxtLbl);
		verifyAssertEquals(drplnNewsc.getCell(10, 1).getContents(), getActualObjectTxt);	//	Enter Some Text Label Name
			
		webElement(adTxtEnterSomeTextFld);
		switchToWEFrame(webelementFrame);
		enterText(scEdtrCommentsField, drplnNewsc.getCell(4, 8).getContents());		//	Enter Some Texts
		switchBackFromFrame();
		
		click(adTxtParagrphPpSaveBtn);
		thread();
		
		getObjectText(msgNotificationBar);
		verifyAssertEquals(drplnNewsc.getCell(8, 2).getContents(), getActualObjectTxt);		//	Text/Paragraph added successfully 
		
		getObjectText(cgsecfirstText);
		verifyAssertEquals(drplnNewsc.getCell(4, 8).getContents(), getActualObjectTxt);		//	Verify Entered Text In Section Page
		
		click(cgsecfirstText);
		thread();
		
		//		Edit Text		//
		getObjectText(adTxtAddTextParagraphPpTtl);
		verifyAssertEquals(drplnNewsc.getCell(9, 5).getContents(), getActualObjectTxt);		//	Update Text/Paragraph Title
			
		webElement(adTxtEnterSomeTextFld);
		switchToWEFrame(webelementFrame);
		useKeyFuncToRmvVal(scEdtrCommentsField);	//	Edit Some Texts
		enterText(scEdtrCommentsField, drplnNewsc.getCell(5, 8).getContents());
		switchBackFromFrame();
			
		click(adTxtParagrphPpSaveBtn);
		thread();
			
		getObjectText(msgNotificationBar);
		verifyAssertEquals(drplnNewsc.getCell(8, 3).getContents(), getActualObjectTxt);		//	Text/Paragraph Updated Successfully
			
		getObjectText(cgsecfirstText);
		verifyAssertEquals(drplnNewsc.getCell(5, 8).getContents(), getActualObjectTxt);		//	Verify Modified Text
		
		
		//		Add Dynamic Data		//
		click(cgsecAddDataBtn);
		thread();
		try
		{
		verifyAssert(addDynamicDataPopup);
			
		click(dynDtaPpModuleDDArrow);
		enterText(dynDtaPpModuleDDSearchBox, drplnNewsc.getCell(11, 8).getContents());		//	Choose Contact Groups module
		enterKeyInKyBord(dynDtaPpModuleDDSearchBox);
		thread();
		
		//try
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
					
				click(cgsecdfContactGroupName);
				click(cgsecdfGroupPurpose);
				click(cgsecdfcomments);
													
			//	click(dynDtaAddNewFilterBtn);	//	Add New Filter button
					
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
					click(dynDtaFieldValueDD);
					driver.findElement(By.id("dyn_sel_data_fld_val_chzn_o_"+alist.get(i))).click();
					thread(); 
					/*String randomValue = driver.findElement(By.id("ddlDDataNewFilterValues_chzn_o_"+alist.get(i))).getText();
					enterText(dynDtaFieldValueDD, randomValue);
					enterKeyInKyBord(dynDtaFieldValueDD);*/
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
				for(int i=1; i<4; i++)
				{
					String getColumnName = driver.findElement(By.xpath("//div[@id='divDynamicDRPlanSections']/div[8]//table[@class='table table-responsive table-striped table-bordered bootstrap-datatable dynamic-data-table']//tr/td["+i+"]")).getText();
					if(i == 1)
						verifyAssertEquals(drplnNewsc.getCell(12, 27).getContents(), getColumnName);	//	Contact Group Name
					if(i == 2)
						verifyAssertEquals(drplnNewsc.getCell(12, 28).getContents(), getColumnName);	//	Group Purpose
					if(i == 3)
						verifyAssertEquals(drplnNewsc.getCell(12, 10).getContents(), getColumnName);	//	Comments		
				}
				
				click(cgsecTeamsList);
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
				for(int i=1; i<4; i++)
				{
					String getColumnName = driver.findElement(By.xpath("//div[@id='divDynamicDRPlanSections']/div[8]//table[@class='table table-responsive table-striped table-bordered bootstrap-datatable dynamic-data-table']//tr/td["+i+"]")).getText();
					if(i == 1)
						verifyAssertEquals(drplnNewsc.getCell(12, 28).getContents(), getColumnName);	//	Group Purpose
					if(i == 2)
						verifyAssertEquals(drplnNewsc.getCell(12, 10).getContents(), getColumnName);	//	Comments
					if(i == 3)
						verifyAssertEquals(drplnNewsc.getCell(12, 27).getContents(), getColumnName);	//	Contact Group Name
				}				
							
				//		Edit Section		//
				click(cgsecEditSectionBtn);
				thread();
			
				verifyAssert(createNewSectionPopup);
				getObjectText(secPpCreateNewSectionTtl);
				verifyAssertEquals(drplnNewsc.getCell(9, 2).getContents(), getActualObjectTxt);		//	Update Section Pop up Title
			
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
				thread();
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
				verifyAssertEquals(drplnNewsc.getCell(9, 4).getContents(), getActualObjectTxt);		//	Add Text/Paragraph Pop Up Title
				
				getObjectText(adTxtPpEnterSomeTxtLbl);
				verifyAssertEquals(drplnNewsc.getCell(10, 1).getContents(), getActualObjectTxt);		//	Enter Some Text Label Name
				
				webElement(adTxtEnterSomeTextFld);
				switchToWEFrame(webelementFrame);
				enterText(scEdtrCommentsField, drplnNewsc.getCell(6, 8).getContents());		//	Enter Some Texts
				switchBackFromFrame();
			
				click(adTxtParagrphPpSaveBtn);
				thread();
			
				getObjectText(msgNotificationBar);
				verifyAssertEquals(drplnNewsc.getCell(8, 2).getContents(), getActualObjectTxt);		//	Text/Paragraph added successfully 
			
				webElement(cgsec2ndTxt); 
				scrollInnerScrollBar(webelementFrame);		//	Scrolling Section Grid Inner Scroll Bar	
			
				getObjectText(cgsec2ndTxt);
				verifyAssertEquals(drplnNewsc.getCell(6, 8).getContents(), getActualObjectTxt);		//	Verify Entered Text In Section Page
				thread();
			
				//		Mouse Hover - Move Last
				webElement(cgsecmHovrOnFirstOptn);
				mouseHoverOn(webelementFrame);
				click(cgsecmoveLastIcon);				
				getObjectText(cgsec2ndTxt); 
				verifyAssertEquals(drplnNewsc.getCell(5, 8).getContents(), getActualObjectTxt);
				thread();
			
				//	Mouse Hover - Move Top
				webElement(cgsecmHovrOnSecOptn);
				mouseHoverOn(webelementFrame);
				thread();
				webElement(cgsecmoveTopIcon);
				mouseHoverOn(webelementFrame);
				click(cgsecmoveTopIcon);				
				getObjectText(cgsecfirstText);
				verifyAssertEquals(drplnNewsc.getCell(6, 8).getContents(), getActualObjectTxt);	
				thread();
			
				//	Mouse Hover - Move Down
				webElement(cgsecmHovrOnFirstOptn);
				mouseHoverOn(webelementFrame);
				click(cgsecmoveDownIcon);				
				getObjectText(cgsec2ndTxt1);
				verifyAssertEquals(drplnNewsc.getCell(6, 8).getContents(), getActualObjectTxt);
				thread();
			
				//	Mouse Hover - Move Up
				webElement(cgsecmHovrOnSecOptn);
				mouseHoverOn(webelementFrame);
				click(cgsecmoveUpIcon);
				getObjectText(cgsecfirstText);
				verifyAssertEquals(drplnNewsc.getCell(6, 8).getContents(), getActualObjectTxt);
				thread();
			
				//	Mouse Hover - Delete
				webElement(cgsecmHovrOnThirdOptn);
				mouseHoverOn(webelementFrame);
				click(cgsecremoveIcon);
			
				verifyAssert(txtDeleteConfPopup);
				getObjectText(deleteConfPpMessage);
				verifyAssertEquals(drplnNewsc.getCell(8, 6).getContents(), getActualObjectTxt);
				click(delConfOkBtn);
				thread();	
			}                                     
	
}
