package Scripts;

import static Config.TakScreenshot.takeScreenshot;

import static ObjectRepository.DRPlan.*;
import static ObjectRepository.Locations.*;
import static ObjectRepository.Threats.*;
import static UIWrappers.UIObjects.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import UIWrappers.Page;
import jxl.read.biff.BiffException;

/*********************************
 *      @author:Geetanjali
 *            
 */


public class DR_CreateNewSection extends Page
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
	void deleteExistingSections() throws InterruptedException
	{
		click(drThreatsnDRPlanInMainMenu);
		waitForPageLoad();
		
		driver.findElement(By.xpath("//table[@id='plan_table']//tbody/tr/td[8]/a")).click();
		thread();
		
		//Remove predefined sections
		verifyObjDisplay(drEdtCollapseAllSectionsBtn);
		if(chkObjDisplay==true)
		{
			click(drEdtCollapseAllSectionsBtn);
			thread();
			getTotalValuesIndd(drEdtTtlSectionCnt);
			
			for(int i=1;i<=totalDDValCount;i++)
			{
				driver.findElement(By.xpath("//div[@id='divDynamicDRPlanSections']/div[1]/div[1]/div/h4/a[3]")).click();
				thread();
				verifyAssert(deleteConfPopup);
				thread();
				click(delConfOkBtn);
				thread();
			}
		}
		
	}
	
/*	@Test(priority=1)
	void createDRPlan() throws BiffException, IOException, InterruptedException
	{
		dR_CreateEditDRPlan.addNewDRPlan();
		thread();
	}*/
	
/*	@Test(priority=2)
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
	}     */
	

 	@Test(priority=3)
	void createEmployeesSection() throws InterruptedException, IOException, BiffException
	{
		getDRPlanCreateNewSectionSheetFromExcel();
		
		click(drEdtFstCreateNewSectionBtn);
		thread();   
		
		//		Create Section		
	//	try
	//	{
			//verifyAssert(createNewSectionPopup);
		getObjectText(secPpCreateNewSectionTtl);
		thread();
		verifyAssertEquals(drplnNewsc.getCell(9, 1).getContents(),getActualObjectTxt);
		thread();
			
			getObjectText(secPpCreateNewSectionTtl);
			
			verifyAssertEquals("Create New Section", getActualObjectTxt);		//	Verify Create New Section Title In Pop Up
			
			//	Section Title
			System.out.println("Employees Section name "+drplnNewsc.getCell(1, 1).getContents());
			click(secPpSectionTitleDDArrow);
			thread();
			enterText(secPpSectionTitleDDSearchBox, drplnNewsc.getCell(1, 1).getContents());
			thread();			
			enterKeyInKyBord(secPpSectionTitleDDSearchBox);
			thread();
			//	Verify Click to Add Text in drop down
			try
			{
				verifyObjDisplay(clickToAddTxtInDD);
				//verifyObjDisplay(clickAddNewTitle);

              //  driver.findElement(By.xpath("//*[contains(text(),'Click to add')]")).click();;
		         thread();
				if(chkObjDisplay == true)
				{
					click(clickToAddTxtInDD);
					//click(clickAddNewTitle);
					thread();
					verifyAssert(sectionAddConfPopup);
					getObjectText(deleteConfPpMessage); 
					verifyAssertEquals("Do you want to add "+drplnNewsc.getCell(1, 1).getContents()+" into Section Title ?", getActualObjectTxt);
					thread();
					click(delConfOkBtn);
					thread();
					
					getObjectText(msgNotificationBar);
					verifyAssertEquals(drplnNewsc.getCell(1, 1).getContents()+" added into Section Title successfully", getActualObjectTxt);		//	Check Section Added Successfully
					thread();
					
					getObjectText(secPpSectionTitleDefTxt);
					verifyAssertEquals(drplnNewsc.getCell(1, 1).getContents(), getActualObjectTxt);		//	Verify Added Title In Drop Down
				}
			}catch(Exception e){
				//arrowDown(secPpSectionTitleDDSearchBox);
				//enterKeyInKyBord(secPpSectionTitleDDSearchBox);
			//System.out.println("title already exists");
		}        
			
			//	Authorized By
			click(secPpAuthorizedByDDArrow);
			getTotalValuesIndd(secPpAuthorizedByDDCnt);
			Random authBy = new Random();
			int rauthBy = authBy.nextInt(totalDDValCount-2)+2;
			String authorizedBy = driver.findElement(By.id("ddlDSAuthorizedBy_chzn_o_"+rauthBy)).getText();
			enterText(secPpAuthorizedByDDSearchBox, authorizedBy.split(",")[0]);
			thread();
			enterKeyInKyBord(secPpAuthorizedByDDSearchBox);
			thread();
			
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
		
			//webElement(crSectionDescription);
		//	switchToWEFrame(webelementFrame);
			
			webElement(crSectionDescription);
			switchToWEFrame(webelementFrame);
			thread();
			Thread.sleep(1000);
			webElement(drscEdtrCommentsField);
			scrollInnerScrollBar(webelementFrame);
			useKeyFuncToRmvVal(drscEdtrCommentsField);
			Thread.sleep(1000);
			thread();
			enterText(drscEdtrCommentsField, drplnNewsc.getCell(2, 1).getContents());
			switchBackFromFrame();
			thread();
			takeScreenshot();
			
			click(secPpSaveBtn);
			thread();
			
			try
			{
			getObjectText(secAddedSectionDescription);
			verifyAssertEquals(drplnNewsc.getCell(2, 1).getContents(), getActualObjectTxt);		//	Check Added Section Description
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			click(secAddedSectionDescription);
			thread();
			getObjectText(secPpCreateNewSectionTtl);
			thread();
			verifyAssertEquals(drplnNewsc.getCell(9, 3).getContents(),getActualObjectTxt);
			thread();		                                                                 //	Check Update Description Title
			
			webElement(crSectionDescription);
			switchToWEFrame(webelementFrame);
			thread();
			Thread.sleep(1000);
			webElement(drscEdtrCommentsField);
			scrollInnerScrollBar(webelementFrame);
			thread();
			Thread.sleep(5000);
			useKeyFuncToRmvVal(drscEdtrCommentsField);
			Thread.sleep(1000);
			thread();
			enterText(drscEdtrCommentsField, drplnNewsc.getCell(3, 1).getContents());
			thread();
			switchBackFromFrame();
			thread();
			
			click(secPpSaveBtn);
			thread();
			
			try
			{
			getObjectText(msgNotificationBar);
			verifyAssertEquals(drplnNewsc.getCell(8, 1).getContents(), getActualObjectTxt);		//	Section Updated Successfully
			thread();	
			
			getObjectText(secAddedSectionDescription);
			verifyAssertEquals(drplnNewsc.getCell(3, 1).getContents(), getActualObjectTxt);			
			}
			catch(Error e)
			{
				e.printStackTrace();
			}
			
//			Authorized & Verified By Person Names		//
			try
			{
				getObjectText1(drEdtSecAuthorizedByLblNm);
				getObjectText(drEdtSecAuthorizedByNm);
				verifyAssertEquals("Authorized By : "+authorizedBy, getActualObjectTxt1+" "+getActualObjectTxt);	//	Check Authorized Person Name
				
				getObjectText1(drEdtSecVerifiedByLblNm);
				getObjectText(drEdtSecVerifiedByNm);
				verifyAssertEquals("Verified By : "+verifiedBy, getActualObjectTxt1+" "+getActualObjectTxt);		//	Check Verified Person Name
				thread();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
				
				//		Add Text/Paragraph		//
				click(edtSecAddTextBtn);
				
				verifyAssert(adTxtAddTextParagraphPopup);
				getObjectText(adTxtAddTextParagraphPpTtl);
				verifyAssertEquals(drplnNewsc.getCell(9, 4).getContents(), getActualObjectTxt);		//	Add Text/Paragraph Pop Up Title
					
				getObjectText(adTxtPpEnterSomeTxtLbl);
				verifyAssertEquals(drplnNewsc.getCell(10, 1).getContents(), getActualObjectTxt);		//	Enter Some Text Label Name
					
				webElement(adTxtEnterSomeTextFld);
				switchToWEFrame(webelementFrame);
				enterText(scEdtrCommentsField, drplnNewsc.getCell(4, 1).getContents());		//	Enter Some Texts
				switchBackFromFrame();
				thread();
				
				click(adTxtParagrphPpSaveBtn);
				thread();
				
				try
				{
				getObjectText(msgNotificationBar);
				verifyAssertEquals(drplnNewsc.getCell(8, 2).getContents(), getActualObjectTxt);		//	Text/Paragraph added successfully 
				
				getObjectText(adTxtEditAddedTxt);
				verifyAssertEquals(drplnNewsc.getCell(4, 1).getContents(), getActualObjectTxt);		//	Verify Entered Text In Section Page
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
				
				
//				Edit Text/Paragraph		//
				click(adTxteditClick);
				thread();
				getObjectText(adTxtAddTextParagraphPpTtl);
				verifyAssertEquals(drplnNewsc.getCell(9, 5).getContents(), getActualObjectTxt);		//	Update Text/Paragraph Title
					
				webElement(adTxtEnterSomeTextFld);
				switchToWEFrame(webelementFrame);
				thread();
				Thread.sleep(1000);
				webElement(drscEdtrCommentsField);
				scrollInnerScrollBar(webelementFrame);
				webElement(drscEdtrCommentsField);
				scrollInnerScrollBar(webelementFrame);
				useKeyFuncToRmvVal(scEdtrCommentsField);	//	Edit Some Texts
				Thread.sleep(1000);
				thread();
				enterText(scEdtrCommentsField, drplnNewsc.getCell(5, 1).getContents());		//	Edit Texts
				switchBackFromFrame();
					
				click(adTxtParagrphPpSaveBtn);
				thread();
					
				try
				{
				getObjectText(msgNotificationBar);
				verifyAssertEquals(drplnNewsc.getCell(8, 3).getContents(), getActualObjectTxt);		//	Text/Paragraph Updated Successfully
					
				getObjectText(adTxtEditAddedTxt);
				verifyAssertEquals(drplnNewsc.getCell(5, 1).getContents(), getActualObjectTxt);		//	Verify Modified Text
				}
				catch(Error e)
				{
					e.printStackTrace();
				}
				
				
								
				//		Add Dynamic Data		//
				
				click(edtSecAddDataBtn);	
				thread();
		//		try
		//		{
				
				verifyAssert(addDynamicDataPopup);
					
				click(dynDtaPpModuleDDArrow);
				enterText(dynDtaPpModuleDDSearchBox, drplnNewsc.getCell(11, 1).getContents());	
				thread();                                                                          //	Choose Employee In Add Dynamic Data Pop up;
				enterKeyInKyBord(dynDtaPpModuleDDSearchBox);
				thread();
					
				try
				{
					//verifyObjDisplay(dynDtaEmpSelectAllChkBox);
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
								
							//		Disable Select All Check Box
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
							
						click(dynDtaEmpFirstName);
						click(dynDtaEmpLastName);
						click(dynDtaEmpMobile);
						click(dynDtaEmpAlternateEmployee);
							
					//	click(dynDtaAddNewFilterBtn);
							
						click(dynDtaFieldDDArrow);
						enterText(dynDtaFieldDDSearchBox, drplnNewsc.getCell(12, 1).getContents());		//	Display Field Name
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
							click(dynDtaFieldValueDD);
							try
							{
							driver.findElement(By.id("dyn_sel_data_fld_val_chzn_o_"+alist.get(i))).click();   
						/*	String randomValue = driver.findElement(By.id("dyn_sel_data_fld_val_chzn_o_"+alist.get(i))).getText();
							enterText(dynDtaFieldValueDD, randomValue);
							enterKeyInKyBord(dynDtaFieldValueDD);*/
							click(dynDtaFilterAddBtn);   
							thread();
							}
							catch(Exception e)
							{
								e.printStackTrace();
								click(dynDtaFilterAddBtn);   
								thread();
							}
							
							//	Check the same filter value exists
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
								
							//	System.out.println(noalrt);
								
							}
						}
						
						verifyObjDisplay(By.xpath("//div[@id='cometchat_hide']"));
						if(chkObjDisplay == true)
						{
						click(By.xpath("//div[@id='cometchat_hide']"));
						}
						thread();
						click(dynDtaPpSaveBtn);
						thread();
								
				}
				catch(Exception e)
				{
					click(dynDtaPpCloseBtn);
					thread();
				}     
					try
						{
						getObjectText(msgNotificationBar);
						verifyAssertEquals(drplnNewsc.getCell(8, 4).getContents(), getActualObjectTxt);		//	Data Created Successfully
						thread();
						}
						catch(Exception e)
						{
							e.printStackTrace();
						}
				
						
						//		Check Selected Display Order in Table
						for(int i=1; i<5; i++)
						{
							//String getColumnName = driver.findElement(By.xpath("//table[@class='table table-responsive table-striped table-bordered bootstrap-datatable dynamic-data-table']/thead/tr/td["+i+"]")).getText();
						    thread();
							String getColumnName=driver.findElement(By.xpath("//div[@id='divDynamicDRPlanSections']/div[1]/div[2]/div[2]/div[1]/div/div[2]/div[2]/div[2]/table/thead/tr/td["+i+"]")).getText();
							thread();
							if(i == 1)
								verifyAssertEquals(drplnNewsc.getCell(12, 1).getContents(), getColumnName);
							if(i == 2)
								verifyAssertEquals(drplnNewsc.getCell(12, 2).getContents(), getColumnName);
							if(i == 3)
								verifyAssertEquals(drplnNewsc.getCell(12, 3).getContents(), getColumnName);
							if(i == 4)
								verifyAssertEquals(drplnNewsc.getCell(12, 4).getContents(), getColumnName);
						}
						thread();
						click(dynDtaEmpClikToEdit);
						Thread.sleep(6000);
						Thread.sleep(7000);
						thread();
						
						verifyAssert(addDynamicDataPopup);
						
						for(int i=2; i<6; i++)
						{
							boolean checked = driver.findElement(By.xpath("//div[@id='pri-mod-flds-holder']/span[2]/input")).isSelected();  //div[@id='divDDataFields']/span["+i+"]/input
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
						}
						catch(Exception e) 
						{
							//System.out.println("Selected Filter values are not displaying in Edit screen");
							e.printStackTrace();
						}
						
						click(dynDtaPpSaveBtn);
						thread();
									
						getObjectText(msgNotificationBar);
						verifyAssertEquals(drplnNewsc.getCell(8, 5).getContents(), getActualObjectTxt);		//	Data Updated Successfully
						thread();
														
						//		Verify Column Name Order Changes In List View
						for(int i=1; i<5; i++)
						{
							String getColumnName = driver.findElement(By.xpath("//table[@class='table table-responsive table-striped table-bordered bootstrap-datatable dynamic-data-table']/thead/tr/td["+i+"]")).getText();
							if(i == 1)
								verifyAssertEquals(drplnNewsc.getCell(12, 2).getContents(), getColumnName);	//	Last Name
							if(i == 2)
								verifyAssertEquals(drplnNewsc.getCell(12, 1).getContents(), getColumnName);		//	First Name
							if(i == 3)
								verifyAssertEquals(drplnNewsc.getCell(12, 4).getContents(), getColumnName);	//	Alternate Employee
							if(i == 4)
								verifyAssertEquals(drplnNewsc.getCell(12, 3).getContents(), getColumnName);		//	Mobile
						}
						
						
						
						
					
                   //	Edit Section		//
						
					click(edtSecEditSectionBtn);
					thread();
					
					
					verifyAssert(createNewSectionPopup);
					getObjectText(secPpCreateNewSectionTtl);
					verifyAssertEquals(drplnNewsc.getCell(9, 2).getContents(), getActualObjectTxt);		//	Update Section Pop up Title
					
					getObjectText(secPpSectionTitleDefTxt);
					verifyAssertEquals(drplnNewsc.getCell(1, 1).getContents(), getActualObjectTxt);		//	Verify Selected Section Title Name 	
					
					getObjectText(secPpAuthorizedByDefTxt);
					verifyAssertEquals(authorizedBy, getActualObjectTxt);	//	Check Selected Authorized By Name
					
					getObjectText(secPpVerifiedByDefTxt);
					verifyAssertEquals(verifiedBy, getActualObjectTxt);	//	Check Select Verified By Name
					
//					Section Title
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
					verifyAssertEquals(drplnNewsc.getCell(8, 1).getContents(), getActualObjectTxt);		//	Verify Section Updated Successfully
					
					
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
					
					try
					{
					//		Verify Edited Authorized & Verified By Name
					getObjectText1(drEdtSecAuthorizedByLblNm);
					getObjectText(drEdtSecAuthorizedByNm);
					verifyAssertEquals("Authorized By : "+edtAuthorizedBy, getActualObjectTxt1+" "+getActualObjectTxt);	//	Check Authorized Person Name
					
					getObjectText1(drEdtSecVerifiedByLblNm);
					getObjectText(drEdtSecVerifiedByNm);
					verifyAssertEquals("Verified By : "+edtVerifiedBy, getActualObjectTxt1+" "+getActualObjectTxt);	//	Check Verified Person Name
					thread();
					
					}
					catch(Exception e)
					{
						e.printStackTrace();
					}
					
					//		Add Text/Paragraph - 2nd Text		//
					click(edtSecAddTextBtn);
					
					try
					{
					verifyAssert(adTxtAddTextParagraphPopup);
					getObjectText(adTxtAddTextParagraphPpTtl);
					verifyAssertEquals(drplnNewsc.getCell(9, 4).getContents(), getActualObjectTxt);		//	Add Text/Paragraph Pop Up Title
						
					getObjectText(adTxtPpEnterSomeTxtLbl);
					verifyAssertEquals(drplnNewsc.getCell(10, 1).getContents(), getActualObjectTxt);		//	Enter Some Text Label Name
						
					webElement(adTxtEnterSomeTextFld);
					switchToWEFrame(webelementFrame);
					enterText(scEdtrCommentsField, drplnNewsc.getCell(6, 1).getContents());		//	Enter Some Texts
					switchBackFromFrame();
					
					click(adTxtParagrphPpSaveBtn);
					thread();
					}
					catch(Exception e)
					{
						e.printStackTrace();
						click(adTxtParagrphPpCloseBtn);
						thread();
					}
					
					try
					{
					getObjectText(msgNotificationBar);					
					verifyAssertEquals(drplnNewsc.getCell(8, 2).getContents(), getActualObjectTxt);		//	Text/Paragraph added successfully 
					}
					catch(Exception e)
					{
						e.printStackTrace();
					}
					
					webElement(adTxtEditAddedTxt2nd); 
					scrollInnerScrollBar(webelementFrame);		//	Scrolling Section Grid Inner Scroll Bar	
					
					getObjectText(adTxtEditAddedTxt2nd);
					verifyAssertEquals(drplnNewsc.getCell(6, 1).getContents(), getActualObjectTxt);		//	Verify Entered Text In Section Page
					thread();
					
//					Mouse Hover - Move Last
					webElement(mHovrOnFirstOptn);
					mouseHoverOn(webelementFrame);
					thread();
					click(moveLastIcon);	
					thread();
					getObjectText(adTxtEditAddedTxt2nd); 
					verifyAssertEquals(drplnNewsc.getCell(5, 1).getContents(), getActualObjectTxt);
					thread();
					
					//	Mouse Hover - Move Top
					webElement(mHovrOnSecOptn);
					mouseHoverOn(webelementFrame);
					thread();
					webElement(moveTopIcon);
					mouseHoverOn(webelementFrame);
					click(moveTopIcon);	
					thread();
					getObjectText(adTxtEditAddedTxt);
					verifyAssertEquals(drplnNewsc.getCell(6, 1).getContents(), getActualObjectTxt);	
					thread();
					
					//	Mouse Hover - Move Down
					webElement(mHovrOnFirstOptn);
					mouseHoverOn(webelementFrame);
					thread();
					click(moveDownIcon);
					thread();
					getObjectText(adTxtEditAddedTxt);
					verifyAssertEquals(drplnNewsc.getCell(6, 1).getContents(), getActualObjectTxt);
					thread();
					
					//	Mouse Hover - Move Up
					webElement(mHovrOnSecOptn);
					mouseHoverOn(webelementFrame);
					thread();
					click(moveUpIcon);
					thread();
					getObjectText(adTxtEditAddedTxt);
					verifyAssertEquals(drplnNewsc.getCell(6, 1).getContents(), getActualObjectTxt);
					thread();
					
					//	Mouse Hover - Delete
					webElement(mHovrOnThirdOptn);
					mouseHoverOn(webelementFrame);
					thread();
					click(removeIcon);
					Thread.sleep(1000);
					verifyAssert(txtDeleteConfPopup);
					getObjectText(deleteConfPpMessage);
					verifyAssertEquals(drplnNewsc.getCell(8, 6).getContents(), getActualObjectTxt);
					click(delConfOkBtn);
					thread();				
				
	        //    catch(Exception e1)
	        //    {
			//		System.out.println("Display fields are not displaying");
			//		e1.printStackTrace();
			//	}	
					
					
	}		                     


	

  
  	@Test(priority=4)
 
	void createLocationsSection() throws IOException, InterruptedException, BiffException
	{
		getDRPlanCreateNewSectionSheetFromExcel();
		 
		click(drEdtSecCreateNewSectionBtn);
		verifyAssert(createNewSectionPopup);
		
		//	Section Title
		click(secPpSectionTitleDDArrow);
		String lsecSectionTitle = drplnNewsc.getCell(1, 2).getContents();
		enterText(secPpSectionTitleDDSearchBox, lsecSectionTitle);
		thread();
		enterKeyInKyBord(secPpSectionTitleDDSearchBox);
		
		try
		{
			verifyObjDisplay(clickToAddTxtInDD);
			if(chkObjDisplay == true)
			{
				click(clickToAddTxtInDD);
				enterKeyInKyBord(secPpSectionTitleDDSearchBox);
				verifyAssert(sectionAddConfPopup);
				getObjectText(deleteConfPpMessage); 
				verifyAssertEquals("Do you want to add "+drplnNewsc.getCell(1, 2).getContents()+" into Section Title ?", getActualObjectTxt);
				click(delConfOkBtn);
				thread();
				
				getObjectText(msgNotificationBar);
				verifyAssertEquals(drplnNewsc.getCell(1, 2).getContents()+" added into Section Title successfully", getActualObjectTxt);		//	Check Section Added Successfully
				thread();
				
				getObjectText(secPpSectionTitleDefTxt);
				verifyAssertEquals(drplnNewsc.getCell(1, 2).getContents(), getActualObjectTxt);		//	Verify Added Title In Drop Down
			}
		}
		catch(Exception e){
			thread();
			//arrowDown(secPpSectionTitleDDSearchBox);
			//enterKeyInKyBord(secPpSectionTitleDDSearchBox);
		//System.out.println("title exists");
		}
		
		//	Authorized By
		click(secPpAuthorizedByDDArrow);
		getTotalValuesIndd(secPpAuthorizedByDDCnt);
		Random authBy = new Random();
		int rauthBy = authBy.nextInt(totalDDValCount-2)+2;
		String lsecAuthorizedBy = driver.findElement(By.id("ddlDSAuthorizedBy_chzn_o_"+rauthBy)).getText();
		thread();
		enterText(secPpAuthorizedByDDSearchBox, lsecAuthorizedBy.split(",")[0]);
		enterKeyInKyBord(secPpAuthorizedByDDSearchBox);
		
		//	Verified By
		click(secPpVerifiedByDDArrow);
		getTotalValuesIndd(secPpVerifiedByDDCnt);
		Random veriBy = new Random();
		int rveriBy = veriBy.nextInt(totalDDValCount-2)+2;
		String lsecVerifiedBy = driver.findElement(By.id("ddlDSVerifiedBy_chzn_o_"+rveriBy)).getText();
		thread();
		enterText(secPpVerifiedByDDSearchBox, lsecVerifiedBy.split(",")[0]);
		enterKeyInKyBord(secPpVerifiedByDDSearchBox);
		
		//	Description
		webElement(crSectionDescription);
		switchToWEFrame(webelementFrame);
		enterText(drscEdtrCommentsField, drplnNewsc.getCell(2, 2).getContents());
		switchBackFromFrame();
		takeScreenshot();
		
		click(secPpSaveBtn);
		thread();
			
		getObjectText(msgNotificationBar);
		verifyAssertEquals(drplnNewsc.getCell(8, 7).getContents(), getActualObjectTxt);		//	Section Created Successfully	
		
		getObjectText(lsecDescription);
		verifyAssertEquals(drplnNewsc.getCell(2, 2).getContents(), getActualObjectTxt);		//	Check Added Section Description
		
		click(lsecDescription);
		thread();
		
		//		Edit Description		//
		verifyAssert(createNewSectionPopup);
		getObjectText(secPpCreateNewSectionTtl);
		verifyAssertEquals(drplnNewsc.getCell(9, 3).getContents(), getActualObjectTxt);		//	Check Update Description Title
		
		webElement(crSectionDescription);
		switchToWEFrame(webelementFrame);
		useKeyFuncToRmvVal(drscEdtrCommentsField);
		enterText(drscEdtrCommentsField, drplnNewsc.getCell(3, 2).getContents());
		switchBackFromFrame();
		thread();
		click(secPpSaveBtn);
		thread();
		
		getObjectText(msgNotificationBar);
		verifyAssertEquals(drplnNewsc.getCell(8, 1).getContents(), getActualObjectTxt);		//	Section Updated Successfully
		thread();	
		
		getObjectText(lsecDescription);
		verifyAssertEquals(drplnNewsc.getCell(3, 2).getContents(), getActualObjectTxt);
		
		
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
		verifyAssertEquals(drplnNewsc.getCell(9, 4).getContents(), getActualObjectTxt);		//	Add Text/Paragraph Pop Up Title
			
		getObjectText(adTxtPpEnterSomeTxtLbl);
		verifyAssertEquals(drplnNewsc.getCell(10, 1).getContents(), getActualObjectTxt);	//	Enter Some Text Label Name
			
		webElement(adTxtEnterSomeTextFld);
		switchToWEFrame(webelementFrame);
		enterText(scEdtrCommentsField, drplnNewsc.getCell(4, 2).getContents());		//	Enter Some Texts
		switchBackFromFrame();
		
		click(adTxtParagrphPpSaveBtn);
		thread();
		
		getObjectText(msgNotificationBar);
		verifyAssertEquals(drplnNewsc.getCell(8, 2).getContents(), getActualObjectTxt);		//	Text/Paragraph added successfully 
		
		getObjectText(lsecfirstText);
		verifyAssertEquals(drplnNewsc.getCell(4, 2).getContents(), getActualObjectTxt);		//	Verify Entered Text In Section Page
		
		click(lsecfirstText);
		thread();
		
		//		Edit Text		//
		getObjectText(adTxtAddTextParagraphPpTtl);
		verifyAssertEquals(drplnNewsc.getCell(9, 5).getContents(), getActualObjectTxt);		//	Update Text/Paragraph Title
			
		webElement(adTxtEnterSomeTextFld);
		switchToWEFrame(webelementFrame);
		useKeyFuncToRmvVal(scEdtrCommentsField);	//	Edit Some Texts
		enterText(scEdtrCommentsField, drplnNewsc.getCell(5, 2).getContents());
		switchBackFromFrame();
			
		click(adTxtParagrphPpSaveBtn);
		thread();
			
		getObjectText(msgNotificationBar);
		verifyAssertEquals(drplnNewsc.getCell(8, 3).getContents(), getActualObjectTxt);		//	Text/Paragraph Updated Successfully
			
		getObjectText(lsecfirstText);
		verifyAssertEquals(drplnNewsc.getCell(5, 2).getContents(), getActualObjectTxt);		//	Verify Modified Text
		
		
		//		Add Dynamic Data		//
		click(lsecAddDataBtn);	
		thread();
	//	try
	//	{
		
		verifyAssert(addDynamicDataPopup);
			
		click(dynDtaPpModuleDDArrow);
		enterText(dynDtaPpModuleDDSearchBox, drplnNewsc.getCell(11, 2).getContents());		//	Choose Locations module
		enterKeyInKyBord(dynDtaPpModuleDDSearchBox);
		thread();
		
		try
		{
			verifyObjDisplay(dynDtaEmpSelectAllChkBox);
		//	if(chkObjDisplay == true)
			//{
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
					
				click(lsecdfLocationName);
				click(lsecdfManager);
				click(lsecdfState);
				click(lsecdfZipcode);
					
			//	click(dynDtaAddNewFilterBtn);	//	Add New Filter button
					
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
					thread();
					click(dynDtaFieldValueDD);
					thread();
					try
					{
					driver.findElement(By.id("dyn_sel_data_fld_val_chzn_o_"+alist.get(i))).click();
					thread();
				/*	String randomValue = driver.findElement(By.id("ddlDDataNewFilterValues_chzn_o_"+alist.get(i))).getText();
					enterText(dynDtaFieldValueDD, randomValue);
					enterKeyInKyBord(dynDtaFieldValueDD);*/
					click(dynDtaFilterAddBtn);
					Thread.sleep(1000);
					}
					catch(Exception e)
					{
						e.printStackTrace();
					}
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
						//System.out.println("No alert message");
						
					}
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
				for(int i=1; i<5; i++)
				{
					thread();
					String getColumnName = driver.findElement(By.xpath("//div[@id='divDynamicDRPlanSections']/div[2]//table[@class='table table-responsive table-striped table-bordered bootstrap-datatable dynamic-data-table']//tr/td["+i+"]")).getText();
					thread();
					if(i == 1)
						verifyAssertEquals(drplnNewsc.getCell(12, 5).getContents(), getColumnName);	//	Location Name
					if(i == 2)
						verifyAssertEquals(drplnNewsc.getCell(12, 6).getContents(), getColumnName);	//	Manager
					if(i == 3)
						verifyAssertEquals(drplnNewsc.getCell(12, 7).getContents(), getColumnName);	//	State
					if(i == 4)
						verifyAssertEquals(drplnNewsc.getCell(12, 8).getContents(), getColumnName);	//	Zip code
				}
				
				click(lsecLocationsList);
				thread();
				
				verifyAssert(addDynamicDataPopup);
				
				for(int i=2; i<6; i++)
				{
					boolean checked = driver.findElement(By.xpath("//div[@id='pri-mod-flds-holder']/span["+i+"]/input")).isSelected(); ////div[@id='divDDataFields']/span["+i+"]/input"
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
				}
				catch(Exception e)
				{
					//System.out.println("Selected Filter values are not displaying in Edit screen");
					e.printStackTrace();
				}
				
				click(dynDtaPpSaveBtn);
				thread();
							
				getObjectText(msgNotificationBar);
				verifyAssertEquals(drplnNewsc.getCell(8, 5).getContents(), getActualObjectTxt);		//	Data Updated Successfully
				thread();
												
				//		Verify Column Name Order Changes In Table
				for(int i=1; i<5; i++)
				{
					String getColumnName = driver.findElement(By.xpath("//div[@id='divDynamicDRPlanSections']/div[2]//table[@class='table table-responsive table-striped table-bordered bootstrap-datatable dynamic-data-table']//tr/td["+i+"]")).getText();
					if(i == 1)
						verifyAssertEquals(drplnNewsc.getCell(12, 6).getContents(), getColumnName);	//	Manager
					if(i == 2)
						verifyAssertEquals(drplnNewsc.getCell(12, 5).getContents(), getColumnName);	//	Location Name
					if(i == 3)
						verifyAssertEquals(drplnNewsc.getCell(12, 8).getContents(), getColumnName);	//	Zip Code
					if(i == 4)
						verifyAssertEquals(drplnNewsc.getCell(12, 7).getContents(), getColumnName);	//	City
				}
				
							
				//		Edit Section		//
				click(lsecEditSectionBtn);
				thread();
			
				verifyAssert(createNewSectionPopup);
				getObjectText(secPpCreateNewSectionTtl);
				verifyAssertEquals(drplnNewsc.getCell(9, 2).getContents(), getActualObjectTxt);		//	Update Section Pop up Title
			
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
				verifyAssertEquals(drplnNewsc.getCell(9, 4).getContents(), getActualObjectTxt);		//	Add Text/Paragraph Pop Up Title
				
				getObjectText(adTxtPpEnterSomeTxtLbl);
				verifyAssertEquals(drplnNewsc.getCell(10, 1).getContents(), getActualObjectTxt);		//	Enter Some Text Label Name
				
				webElement(adTxtEnterSomeTextFld);
				switchToWEFrame(webelementFrame);
				enterText(scEdtrCommentsField, drplnNewsc.getCell(6, 2).getContents());		//	Enter Some Texts
				switchBackFromFrame();
			
				click(adTxtParagrphPpSaveBtn);
				thread();
			
				getObjectText(msgNotificationBar);
				verifyAssertEquals(drplnNewsc.getCell(8, 2).getContents(), getActualObjectTxt);		//	Text/Paragraph added successfully 
			
				webElement(lsec2ndTxt); 
				scrollInnerScrollBar(webelementFrame);		//	Scrolling Section Grid Inner Scroll Bar	
			
				getObjectText(lsec2ndTxt);
				verifyAssertEquals(drplnNewsc.getCell(6, 2).getContents(), getActualObjectTxt);		//	Verify Entered Text In Section Page
				thread();
			
				//		Mouse Hover - Move Last
				webElement(lsecmHovrOnFirstOptn);
				mouseHoverOn(webelementFrame);
				click(lsecmoveLastIcon);				
				getObjectText(lsec2ndTxt); 
				verifyAssertEquals(drplnNewsc.getCell(5, 2).getContents(), getActualObjectTxt);
				thread();
			
				//	Mouse Hover - Move Top
				webElement(lsecmHovrOnSecOptn);
				mouseHoverOn(webelementFrame);
				thread();
				webElement(lsecmoveTopIcon);
				mouseHoverOn(webelementFrame);
				click(lsecmoveTopIcon);				
				getObjectText(lsecfirstText);
				verifyAssertEquals(drplnNewsc.getCell(6, 2).getContents(), getActualObjectTxt);	
				thread();
			
				//	Mouse Hover - Move Down
				webElement(lsecmHovrOnFirstOptn);
				mouseHoverOn(webelementFrame);
				click(lsecmoveDownIcon);				
				getObjectText(lsec2ndTxt1);
				verifyAssertEquals(drplnNewsc.getCell(6, 2).getContents(), getActualObjectTxt);
				thread();
			
				//	Mouse Hover - Move Up
				webElement(lsecmHovrOnSecOptn);
				mouseHoverOn(webelementFrame);
				click(lsecmoveUpIcon);
				getObjectText(lsecfirstText);
				verifyAssertEquals(drplnNewsc.getCell(6, 2).getContents(), getActualObjectTxt);
				thread();
			
				//	Mouse Hover - Delete
				webElement(lsecmHovrOnThirdOptn);
				mouseHoverOn(webelementFrame);
				click(lsecremoveIcon);
			
				verifyAssert(txtDeleteConfPopup);
				getObjectText(deleteConfPpMessage);
				verifyAssertEquals(drplnNewsc.getCell(8, 6).getContents(), getActualObjectTxt);
				click(delConfOkBtn);
				thread();	
			}                                  
	
	

	@Test(priority=5)
	void createThreatsSection() throws InterruptedException, BiffException, IOException
	{
		getDRPlanCreateNewSectionSheetFromExcel();
		 
		click(drEdtSecCreateNewSectionBtn);
		verifyAssert(createNewSectionPopup);   
		
		//	Section Title
		click(secPpSectionTitleDDArrow);
		String trsecSectionTitle = drplnNewsc.getCell(1, 3).getContents();	//	Add New Section Name (or) Choose Existing Section Name
		enterText(secPpSectionTitleDDSearchBox, trsecSectionTitle);
		thread();
		enterKeyInKyBord(secPpSectionTitleDDSearchBox);
		thread();   
		
		try
		{
			verifyObjDisplay(clickToAddTxtInDD);
			if(chkObjDisplay == true)
			{
				click(clickToAddTxtInDD);
				enterKeyInKyBord(secPpSectionTitleDDSearchBox);
				verifyAssert(sectionAddConfPopup);
				getObjectText(deleteConfPpMessage); 
				verifyAssertEquals("Do you want to add "+drplnNewsc.getCell(1, 3).getContents()+" into Section Title ?", getActualObjectTxt);
				click(delConfOkBtn);
				thread();
				
				getObjectText(msgNotificationBar);
				verifyAssertEquals(drplnNewsc.getCell(1, 3).getContents()+" added into Section Title successfully", getActualObjectTxt);		//	Check New Section Added Successfully
				thread();
				
				getObjectText(secPpSectionTitleDefTxt);
				verifyAssertEquals(drplnNewsc.getCell(1, 3).getContents(), getActualObjectTxt);		//	Verify Added Title In Drop Down
			}
		}               

 		catch(Exception e)
 
		{                                                
	//		arrowDown(secPpSectionTitleDDSearchBox);
	//		enterKeyInKyBord(secPpSectionTitleDDSearchBox);
	//	System.out.println("Title Aleary exists");
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
		enterText(drscEdtrCommentsField, drplnNewsc.getCell(2, 3).getContents());
		switchBackFromFrame();
		takeScreenshot();
		
		click(secPpSaveBtn);
		thread();
			
		getObjectText(msgNotificationBar);
		verifyAssertEquals(drplnNewsc.getCell(8, 7).getContents(), getActualObjectTxt);		//	Section Created Successfully	
		
		getObjectText(trsecDescription);
		verifyAssertEquals(drplnNewsc.getCell(2, 3).getContents(), getActualObjectTxt);		//	Check Added Section Description
		
		click(trsecDescription);
		thread();
		
		//		Edit Description		//
		verifyAssert(createNewSectionPopup);
		getObjectText(secPpCreateNewSectionTtl);
		verifyAssertEquals(drplnNewsc.getCell(9, 3).getContents(), getActualObjectTxt);		//	Check Update Description Title
		
		webElement(crSectionDescription);
		switchToWEFrame(webelementFrame);
		useKeyFuncToRmvVal(drscEdtrCommentsField);
		enterText(drscEdtrCommentsField, drplnNewsc.getCell(3, 3).getContents());
		switchBackFromFrame();
		click(secPpSaveBtn);
		thread();
		
		getObjectText(msgNotificationBar);
		verifyAssertEquals(drplnNewsc.getCell(8, 1).getContents(), getActualObjectTxt);		//	Section Updated Successfully
		thread();	
		
		getObjectText(trsecDescription);
		verifyAssertEquals(drplnNewsc.getCell(3, 3).getContents(), getActualObjectTxt);		//	Verify Edited Description Details
		
		
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
		verifyAssertEquals(drplnNewsc.getCell(9, 4).getContents(), getActualObjectTxt);		//	Add Text/Paragraph Pop Up Title
			
		getObjectText(adTxtPpEnterSomeTxtLbl);
		verifyAssertEquals(drplnNewsc.getCell(10, 1).getContents(), getActualObjectTxt);	//	Enter Some Text Label Name
			
		webElement(adTxtEnterSomeTextFld);
		switchToWEFrame(webelementFrame);
		enterText(scEdtrCommentsField, drplnNewsc.getCell(4, 3).getContents());		//	Enter Some Texts
		switchBackFromFrame();
		
		click(adTxtParagrphPpSaveBtn);
		thread();
		
		getObjectText(msgNotificationBar);
		verifyAssertEquals(drplnNewsc.getCell(8, 2).getContents(), getActualObjectTxt);		//	Text/Paragraph added successfully 
		
		getObjectText(trsecfirstText);
		verifyAssertEquals(drplnNewsc.getCell(4, 3).getContents(), getActualObjectTxt);		//	Verify Entered Text In Section Page
		
		click(trsecfirstText);
		thread();
		
		//		Edit Text		//
		getObjectText(adTxtAddTextParagraphPpTtl);
		verifyAssertEquals(drplnNewsc.getCell(9, 5).getContents(), getActualObjectTxt);		//	Update Text/Paragraph Title
			
		webElement(adTxtEnterSomeTextFld);
		switchToWEFrame(webelementFrame);
		useKeyFuncToRmvVal(scEdtrCommentsField);	//	Edit Some Texts
		enterText(scEdtrCommentsField, drplnNewsc.getCell(5, 3).getContents());
		switchBackFromFrame();
			
		click(adTxtParagrphPpSaveBtn);
		thread();
			
		getObjectText(msgNotificationBar);
		verifyAssertEquals(drplnNewsc.getCell(8, 3).getContents(), getActualObjectTxt);		//	Text/Paragraph Updated Successfully
			
		getObjectText(trsecfirstText);
		verifyAssertEquals(drplnNewsc.getCell(5, 3).getContents(), getActualObjectTxt);		//	Verify Modified Text
		
		
		//		Add Dynamic Data		//
		click(trsecAddDataBtn);
		thread();
		try
		{
		
		verifyAssert(addDynamicDataPopup);
			
		click(dynDtaPpModuleDDArrow);
		enterText(dynDtaPpModuleDDSearchBox, drplnNewsc.getCell(11, 3).getContents());		//	Choose Threats module
		enterKeyInKyBord(dynDtaPpModuleDDSearchBox);
		thread();                                                  
		
		//try
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
					
				click(trsecdfThreatName);
				click(trsecdfComments);
									
			//	click(dynDtaAddNewFilterBtn);	//	Add New Filter button
					
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
					thread();
					click(dynDtaFieldValueDD);
					thread();
					try
					{
					driver.findElement(By.id("dyn_sel_data_fld_val_chzn_o_"+alist.get(i))).click();
					Thread.sleep(1000);
					click(dynDtaFilterAddBtn);
					thread();
					}
										
				/*	String randomValue = driver.findElement(By.id("ddlDDataNewFilterValues_chzn_o_"+alist.get(i))).getText();
					enterText(dynDtaFieldValueDD, randomValue);
					enterKeyInKyBord(dynDtaFieldValueDD);
					thread();*/
					catch(Exception e)
					{
					e.printStackTrace();
				    click(dynDtaFilterAddBtn);
					thread();
					}
					
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
						//System.out.println("No alerts");
					}
				}		
				
				verifyObjDisplay(By.xpath("//div[@id='cometchat_hide']"));
				if(chkObjDisplay == true)
				{
				click(By.xpath("//div[@id='cometchat_hide']"));
				}
				thread();
				click(dynDtaPpSaveBtn);
				thread();
				
				getObjectText(msgNotificationBar);
				verifyAssertEquals(drplnNewsc.getCell(8, 4).getContents(), getActualObjectTxt);		//	Data Created Successfully
				thread();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			click(dynDtaPpCloseBtn);
			thread();
		}
				
				//		Check Selected Display Order in Table
				for(int i=1; i<3; i++)
				{
					thread();
					String getColumnName = driver.findElement(By.xpath("//div[@id='divDynamicDRPlanSections']/div[3]//table[@class='table table-responsive table-striped table-bordered bootstrap-datatable dynamic-data-table']//tr/td["+i+"]")).getText();
					thread();
					if(i == 1)
						verifyAssertEquals(drplnNewsc.getCell(12, 9).getContents(), getColumnName);	//	Threat Name
					if(i == 2)
						verifyAssertEquals(drplnNewsc.getCell(12, 10).getContents(), getColumnName);	//	Comments
				}
				
				click(trsecThreatsList);
				thread();
				
				verifyAssert(addDynamicDataPopup);
				
				for(int i=2; i<4; i++)
				{
					boolean checked = driver.findElement(By.xpath("//div[@id='pri-mod-flds-holder']/span["+i+"]/input")).isSelected();  //div[@id='divDDataFields']/span["+i+"]/input"
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
				}
				catch(Exception e) 
				{
					//System.out.println("Selected Filter values are not displaying in Edit screen");
					e.printStackTrace();
				}
				
				click(dynDtaPpSaveBtn);
				thread();
							
				getObjectText(msgNotificationBar);
				verifyAssertEquals(drplnNewsc.getCell(8, 5).getContents(), getActualObjectTxt);		//	Data Updated Successfully
				thread();
												
				//		Verify Column Name Order Changes In Table
				for(int i=1; i<3; i++)
				{
					String getColumnName = driver.findElement(By.xpath("//div[@id='divDynamicDRPlanSections']/div[3]//table[@class='table table-responsive table-striped table-bordered bootstrap-datatable dynamic-data-table']//tr/td["+i+"]")).getText();
					if(i == 1)
						verifyAssertEquals(drplnNewsc.getCell(12, 10).getContents(), getColumnName);	//	Comments
					if(i == 2)
						verifyAssertEquals(drplnNewsc.getCell(12, 9).getContents(), getColumnName);	//	Threat Name
				}				
							
				//		Edit Section		//
				click(trsecEditSectionBtn);
				thread();
			
				verifyAssert(createNewSectionPopup);
				getObjectText(secPpCreateNewSectionTtl);
				verifyAssertEquals(drplnNewsc.getCell(9, 2).getContents(), getActualObjectTxt);		//	Update Section Pop up Title
			
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
				verifyAssertEquals(drplnNewsc.getCell(9, 4).getContents(), getActualObjectTxt);		//	Add Text/Paragraph Pop Up Title
				
				getObjectText(adTxtPpEnterSomeTxtLbl);
				verifyAssertEquals(drplnNewsc.getCell(10, 1).getContents(), getActualObjectTxt);		//	Enter Some Text Label Name
				
				webElement(adTxtEnterSomeTextFld);
				switchToWEFrame(webelementFrame);
				enterText(scEdtrCommentsField, drplnNewsc.getCell(6, 3).getContents());		//	Enter Some Texts
				switchBackFromFrame();
			
				click(adTxtParagrphPpSaveBtn);
				thread();
			
				getObjectText(msgNotificationBar);
				verifyAssertEquals(drplnNewsc.getCell(8, 2).getContents(), getActualObjectTxt);		//	Text/Paragraph added successfully 
			
				webElement(trsec2ndTxt); 
				scrollInnerScrollBar(webelementFrame);		//	Scrolling Section Grid Inner Scroll Bar	
			
				getObjectText(trsec2ndTxt);
				verifyAssertEquals(drplnNewsc.getCell(6, 3).getContents(), getActualObjectTxt);		//	Verify Entered Text In Section Page
				thread();
			
				//		Mouse Hover - Move Last
				webElement(trsecmHovrOnFirstOptn);
				mouseHoverOn(webelementFrame);
				click(trsecmoveLastIcon);
				thread();
				getObjectText(trsec2ndTxt); 
				verifyAssertEquals(drplnNewsc.getCell(5, 3).getContents(), getActualObjectTxt);
				thread();
			
				//	Mouse Hover - Move Top
				webElement(trsecmHovrOnSecOptn);
				mouseHoverOn(webelementFrame);
				thread();
				webElement(trsecmoveTopIcon);
				mouseHoverOn(webelementFrame);
				click(trsecmoveTopIcon);				
				getObjectText(trsecfirstText);
				verifyAssertEquals(drplnNewsc.getCell(6, 3).getContents(), getActualObjectTxt);	
				thread();
			
				//	Mouse Hover - Move Down
				webElement(trsecmHovrOnFirstOptn);
				mouseHoverOn(webelementFrame);
				click(trsecmoveDownIcon);				
				getObjectText(trsec2ndTxt1);
				verifyAssertEquals(drplnNewsc.getCell(6, 3).getContents(), getActualObjectTxt);
				thread();
			
				//	Mouse Hover - Move Up
				webElement(trsecmHovrOnSecOptn);
				mouseHoverOn(webelementFrame);
				click(trsecmoveUpIcon);
				getObjectText(trsecfirstText);
				verifyAssertEquals(drplnNewsc.getCell(6, 3).getContents(), getActualObjectTxt);
				thread();
			
				//	Mouse Hover - Delete
				webElement(trsecmHovrOnThirdOptn);
				mouseHoverOn(webelementFrame);
				click(trsecremoveIcon);
			
				verifyAssert(txtDeleteConfPopup);
				getObjectText(deleteConfPpMessage);
				verifyAssertEquals(drplnNewsc.getCell(8, 6).getContents(), getActualObjectTxt);
				click(delConfOkBtn);
				thread();	
			}
	
	
	

 	@Test(priority=6)

	void createTeamsSection() throws InterruptedException, BiffException, IOException
	{
		getDRPlanCreateNewSectionSheetFromExcel();
		 
		click(drEdtSecCreateNewSectionBtn);
		verifyAssert(createNewSectionPopup);
		
		//	Section Title
		click(secPpSectionTitleDDArrow);
		String tmsecSectionTitle = drplnNewsc.getCell(1, 4).getContents();	//	Add New Section Name (or) Choose Existing Section Name
		enterText(secPpSectionTitleDDSearchBox, tmsecSectionTitle);
		thread();
		enterKeyInKyBord(secPpSectionTitleDDSearchBox);
		
		try
		{
			verifyObjDisplay(clickToAddTxtInDD);
			if(chkObjDisplay == true)
			{
				click(clickToAddTxtInDD); 
				enterKeyInKyBord(secPpSectionTitleDDSearchBox);
				verifyAssert(sectionAddConfPopup);
				getObjectText(deleteConfPpMessage); 
				verifyAssertEquals("Do you want to add "+drplnNewsc.getCell(1, 4).getContents()+" into Section Title ?", getActualObjectTxt);
				click(delConfOkBtn);
				thread();
				
				getObjectText(msgNotificationBar);
				verifyAssertEquals(drplnNewsc.getCell(1, 4).getContents()+" added into Section Title successfully", getActualObjectTxt);		//	Check New Section Added Successfully
				thread();
				
				getObjectText(secPpSectionTitleDefTxt);
				verifyAssertEquals(drplnNewsc.getCell(1, 4).getContents(), getActualObjectTxt);		//	Verify Added Title In Drop Down
			}
		}
		catch(Exception e)
		{
			//arrowDown(secPpSectionTitleDDSearchBox); 
			//enterKeyInKyBord(secPpSectionTitleDDSearchBox);
			//System.out.println("Title name already exists");
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
		enterText(drscEdtrCommentsField, drplnNewsc.getCell(2, 4).getContents());
		switchBackFromFrame();
		takeScreenshot();
		
		click(secPpSaveBtn);
		thread();
			
		getObjectText(msgNotificationBar);
		verifyAssertEquals(drplnNewsc.getCell(8, 7).getContents(), getActualObjectTxt);		//	Section Created Successfully	
		
		getObjectText(tmsecDescription);
		verifyAssertEquals(drplnNewsc.getCell(2, 4).getContents(), getActualObjectTxt);		//	Check Added Section Description
		
		click(tmsecDescription);
		thread();
		
		//		Edit Description		//
		verifyAssert(createNewSectionPopup);
		getObjectText(secPpCreateNewSectionTtl);
		verifyAssertEquals(drplnNewsc.getCell(9, 3).getContents(), getActualObjectTxt);		//	Check Update Description Title
		
		webElement(crSectionDescription);
		switchToWEFrame(webelementFrame);
		useKeyFuncToRmvVal(drscEdtrCommentsField);
		enterText(drscEdtrCommentsField, drplnNewsc.getCell(3, 4).getContents());
		switchBackFromFrame();
		click(secPpSaveBtn);
		thread();
		
		getObjectText(msgNotificationBar);
		verifyAssertEquals(drplnNewsc.getCell(8, 1).getContents(), getActualObjectTxt);		//	Section Updated Successfully
		thread();	
		
		getObjectText(tmsecDescription);
		verifyAssertEquals(drplnNewsc.getCell(3, 4).getContents(), getActualObjectTxt);		//	Verify Edited Description Details
		
		
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
		verifyAssertEquals(drplnNewsc.getCell(9, 4).getContents(), getActualObjectTxt);		//	Add Text/Paragraph Pop Up Title
			
		getObjectText(adTxtPpEnterSomeTxtLbl);
		verifyAssertEquals(drplnNewsc.getCell(10, 1).getContents(), getActualObjectTxt);	//	Enter Some Text Label Name
			
		webElement(adTxtEnterSomeTextFld);
		switchToWEFrame(webelementFrame);
		enterText(scEdtrCommentsField, drplnNewsc.getCell(4, 4).getContents());		//	Enter Some Texts
		switchBackFromFrame();
		
		click(adTxtParagrphPpSaveBtn);
		thread();
		
		getObjectText(msgNotificationBar);
		verifyAssertEquals(drplnNewsc.getCell(8, 2).getContents(), getActualObjectTxt);		//	Text/Paragraph added successfully 
		
		getObjectText(tmsecfirstText);
		verifyAssertEquals(drplnNewsc.getCell(4, 4).getContents(), getActualObjectTxt);		//	Verify Entered Text In Section Page
		
		click(tmsecfirstText);
		thread();
		
		//		Edit Text		//
		getObjectText(adTxtAddTextParagraphPpTtl);
		verifyAssertEquals(drplnNewsc.getCell(9, 5).getContents(), getActualObjectTxt);		//	Update Text/Paragraph Title
			
		webElement(adTxtEnterSomeTextFld);
		switchToWEFrame(webelementFrame);
		useKeyFuncToRmvVal(scEdtrCommentsField);	//	Edit Some Texts
		enterText(scEdtrCommentsField, drplnNewsc.getCell(5, 4).getContents());
		switchBackFromFrame();
			
		click(adTxtParagrphPpSaveBtn);
		thread();
			
		getObjectText(msgNotificationBar);
		verifyAssertEquals(drplnNewsc.getCell(8, 3).getContents(), getActualObjectTxt);		//	Text/Paragraph Updated Successfully
			
		getObjectText(tmsecfirstText);
		verifyAssertEquals(drplnNewsc.getCell(5, 4).getContents(), getActualObjectTxt);		//	Verify Modified Text
		
		
		//		Add Dynamic Data		//
		click(tmsecAddDataBtn);
		thread();
		
		try
		{
		
		verifyAssert(addDynamicDataPopup);
			
		click(dynDtaPpModuleDDArrow);
		enterText(dynDtaPpModuleDDSearchBox, drplnNewsc.getCell(11, 4).getContents());		//	Choose Threats module
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
					
				click(tmsecdfTeamName);
				click(tmsecdfTeamLeader);
				click(tmsecdfTeamType);
				click(tmsecdfLocation);
									
			//	click(dynDtaAddNewFilterBtn);	//	Add New Filter button
					
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
					click(dynDtaFieldValueDD);
					thread();
					try
					{
					driver.findElement(By.id("dyn_sel_data_fld_val_chzn_o_"+alist.get(i))).click();
					thread();
				/*	String randomValue = driver.findElement(By.id("ddlDDataNewFilterValues_chzn_o_"+alist.get(i))).getText();
					enterText(dynDtaFieldValueDD, randomValue);
					enterKeyInKyBord(dynDtaFieldValueDD);*/
					click(dynDtaFilterAddBtn);
					thread();
					}
					catch(Exception e)
					{
						click(dynDtaFilterAddBtn);
						thread();
					}
					
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
						//System.out.println(noalrt);
						
					}
				}		
				
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
				for(int i=1; i<5; i++)
				{
					thread();
					String getColumnName = driver.findElement(By.xpath("//div[@id='divDynamicDRPlanSections']/div[4]//table[@class='table table-responsive table-striped table-bordered bootstrap-datatable dynamic-data-table']//tr/td["+i+"]")).getText();
					thread();
					if(i == 1)
						verifyAssertEquals(drplnNewsc.getCell(12, 11).getContents(), getColumnName);	//	Team Name
					if(i == 2)
						verifyAssertEquals(drplnNewsc.getCell(12, 12).getContents(), getColumnName);	//	Team Leader
					if(i == 3)
						verifyAssertEquals(drplnNewsc.getCell(12, 13).getContents(), getColumnName);	//	Team Type
					if(i == 4)
						verifyAssertEquals(drplnNewsc.getCell(12, 14).getContents(), getColumnName);	//	Location
				}
				thread();
				//webElement(tmsecTeamsList);
				//scrollInnerScrollBar(webelementFrame);
				click(tmsecTeamsList);
				Thread.sleep(10000);
			
				verifyAssert(addDynamicDataPopup);
				
				for(int i=2; i<4; i++)
				{
					boolean checked = driver.findElement(By.xpath("//div[@id='pri-mod-flds-holder']/span["+i+"]/input")).isSelected();
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
				}
				catch(Exception e) 
				{
				//	System.out.println("Selected Filter values are not displaying in Edit screen");
					e.printStackTrace();
				}
				
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
					String getColumnName = driver.findElement(By.xpath("//div[@id='divDynamicDRPlanSections']/div[4]//table[@class='table table-responsive table-striped table-bordered bootstrap-datatable dynamic-data-table']//tr/td["+i+"]")).getText();
					if(i == 1)
						verifyAssertEquals(drplnNewsc.getCell(12, 12).getContents(), getColumnName);	//	Team Leader
					if(i == 2)
						verifyAssertEquals(drplnNewsc.getCell(12, 11).getContents(), getColumnName);	//	Team Name
					if(i == 3)
						verifyAssertEquals(drplnNewsc.getCell(12, 14).getContents(), getColumnName);	//	Location
					if(i == 4)
						verifyAssertEquals(drplnNewsc.getCell(12, 13).getContents(), getColumnName);	//	Team Type
				}				
							
				//		Edit Section		//
				click(tmsecEditSectionBtn);
				thread();
			
				verifyAssert(createNewSectionPopup);
				getObjectText(secPpCreateNewSectionTtl);
				verifyAssertEquals(drplnNewsc.getCell(9, 2).getContents(), getActualObjectTxt);		//	Update Section Pop up Title
			
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
				verifyAssertEquals(drplnNewsc.getCell(9, 4).getContents(), getActualObjectTxt);		//	Add Text/Paragraph Pop Up Title
				
				getObjectText(adTxtPpEnterSomeTxtLbl);
				verifyAssertEquals(drplnNewsc.getCell(10, 1).getContents(), getActualObjectTxt);		//	Enter Some Text Label Name
				
				webElement(adTxtEnterSomeTextFld);
				switchToWEFrame(webelementFrame);
				enterText(scEdtrCommentsField, drplnNewsc.getCell(6, 4).getContents());		//	Enter Some Texts
				switchBackFromFrame();
			
				click(adTxtParagrphPpSaveBtn);
				thread();
			
				getObjectText(msgNotificationBar);
				verifyAssertEquals(drplnNewsc.getCell(8, 2).getContents(), getActualObjectTxt);		//	Text/Paragraph added successfully 
			
				webElement(tmsec2ndTxt); 
				scrollInnerScrollBar(webelementFrame);		//	Scrolling Section Grid Inner Scroll Bar	
			
				getObjectText(tmsec2ndTxt);
				verifyAssertEquals(drplnNewsc.getCell(6, 4).getContents(), getActualObjectTxt);		//	Verify Entered Text In Section Page
				thread();
			
				//		Mouse Hover - Move Last
				webElement(tmsecmHovrOnFirstOptn);
				mouseHoverOn(webelementFrame);
				click(tmsecmoveLastIcon);				
				getObjectText(tmsec2ndTxt); 
				verifyAssertEquals(drplnNewsc.getCell(5, 4).getContents(), getActualObjectTxt);
				thread();
			
				//	Mouse Hover - Move Top
				webElement(tmsecmHovrOnSecOptn);
				mouseHoverOn(webelementFrame);
				thread();
				webElement(tmsecmoveTopIcon);
				mouseHoverOn(webelementFrame);
				click(tmsecmoveTopIcon);
				Thread.sleep(1000);
				getObjectText(tmsecfirstText);
				verifyAssertEquals(drplnNewsc.getCell(6, 4).getContents(), getActualObjectTxt);	
				thread();
			
				//	Mouse Hover - Move Down
				webElement(tmsecmHovrOnFirstOptn);
				mouseHoverOn(webelementFrame);
				click(tmsecmoveDownIcon);				
				getObjectText(tmsec2ndTxt1);
				verifyAssertEquals(drplnNewsc.getCell(6, 4).getContents(), getActualObjectTxt);
				thread();
			
				//	Mouse Hover - Move Up
				webElement(tmsecmHovrOnSecOptn);
				mouseHoverOn(webelementFrame);
				click(tmsecmoveUpIcon);
				getObjectText(tmsecfirstText);
				verifyAssertEquals(drplnNewsc.getCell(6, 4).getContents(), getActualObjectTxt);
				thread();
			
				//	Mouse Hover - Delete
				webElement(tmsecmHovrOnThirdOptn);
				mouseHoverOn(webelementFrame);
				click(tmsecremoveIcon);
				Thread.sleep(1000);
			
				verifyAssert(txtDeleteConfPopup);
				getObjectText(deleteConfPpMessage);
				verifyAssertEquals(drplnNewsc.getCell(8, 6).getContents(), getActualObjectTxt);
				click(delConfOkBtn);
				thread();	
			}                               
	
	
/*	
	@Test(priority=7)
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
	
/*					click(dynDtaFilterAddBtn);
					
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
				thread();
				destinationElement(dynDtaEmpWeLastName);
				thread();
				dragAndDrop(sourceObj, destinationObj);					//	Change Asset & Manufacturer Name position				
				thread();
				
				sourceElement(dynDtaEmpWeMobile);
				thread();
				destinationElement(dynDtaEmpWeAlternateEmployee);
				thread();
				dragAndDrop(sourceObj, destinationObj);		//	Change Quantity & Replacement Value position
				thread();
				
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
						verifyAssertEquals(drplnNewsc.getCell(12, 16).getContents(), getColumnName);	//	Manufacturer Name
					if(i == 2)
						verifyAssertEquals(drplnNewsc.getCell(12, 15).getContents(), getColumnName);	//	Asset Name
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
			}                                               */
 	
	                                      
/*--	
	@Test(priority=8)
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
					thread();            
        			/*	String randomValue = driver.findElement(By.id("ddlDDataNewFilterValues_chzn_o_"+alist.get(i))).getText();
					enterText(dynDtaFieldValueDD, randomValue);
					enterKeyInKyBord(dynDtaFieldValueDD);
					click(dynDtaFilterAddBtn);
					thread();   */
					
/*---				try
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
				thread();
			
				verifyAssert(txtDeleteConfPopup);
				getObjectText(deleteConfPpMessage);
				verifyAssertEquals(drplnNewsc.getCell(8, 6).getContents(), getActualObjectTxt);
				click(delConfOkBtn);
				thread();	
			}                                         -----*/
	
	                              


/*---	
	@Test(priority=9)
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
				
				for(int i=0; i<2; i++)
				{
					thread();
					click(dynDtaFieldValueDD);
					driver.findElement(By.id("dyn_sel_data_fld_val_chzn_o_"+alist.get(i))).click();
					thread();
					/*String randomValue = driver.findElement(By.id("ddlDDataNewFilterValues_chzn_o_"+alist.get(i))).getText();
					enterText(dynDtaFieldValueDD, randomValue);
					enterKeyInKyBord(dynDtaFieldValueDD);  */
/*---	    			thread();  
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
				
				click(csecTeamsList);
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
				thread();
				dragAndDrop(sourceObj, destinationObj);		//	Change Company Name & Country position	
							
				sourceElement(dynDtaEmpWeMobile);
				destinationElement(dynDtaEmpWeAlternateEmployee);
				thread();
				dragAndDrop(sourceObj, destinationObj);		//	Change City & Zip Code position		
				
				sourceElement(dynDtaDisplayFldFifthWe);
				destinationElement(dynDtaDisplayFldSixthWe);
				thread();
				dragAndDrop(sourceObj, destinationObj);		//	Change Primary First & Last Name position
						
				sourceElement(dynDtaDisplayFldSeventhWe);
				destinationElement(dynDtaDisplayFldEigthWe);
				thread();
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
					if(i == 1)
						verifyAssertEquals(drplnNewsc.getCell(12, 22).getContents(), getColumnName);	//	Country
					if(i == 2)
						verifyAssertEquals(drplnNewsc.getCell(12, 21).getContents(), getColumnName);	//	Company Name
					if(i == 3)
						verifyAssertEquals(drplnNewsc.getCell(12, 8).getContents(), getColumnName);	//	Zip Code
					if(i == 4)
						verifyAssertEquals(drplnNewsc.getCell(13, 1).getContents(), getColumnName);	//	City
					if(i == 5)
						verifyAssertEquals(drplnNewsc.getCell(12, 24).getContents(), getColumnName);	//	Primary Last Name
					if(i == 6)
						verifyAssertEquals(drplnNewsc.getCell(12, 23).getContents(), getColumnName);	//	Primary First Name
					if(i == 7)
						verifyAssertEquals(drplnNewsc.getCell(12, 26).getContents(), getColumnName);	//	Primary Mobile No
					if(i == 8)
						verifyAssertEquals(drplnNewsc.getCell(12, 25).getContents(), getColumnName);	//	Primary Business Email
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
			}                           ---*/
	
	                                        
/*---
	@Test(priority=10)
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
/*---					thread();
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
			}                                    ---*/ 
	

/*---
	@Test(priority=11)
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
		
		//try
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
				
				for(int i=0; i<2; i++)
				{
					click(dynDtaFieldValueDD);
					driver.findElement(By.id("dyn_sel_data_fld_val_chzn_o_"+alist.get(i))).click();
					thread(); 
				/*	String randomValue = driver.findElement(By.id("ddlDDataNewFilterValues_chzn_o_"+alist.get(i))).getText();
					enterText(dynDtaFieldValueDD, randomValue);
					enterKeyInKyBord(dynDtaFieldValueDD);  */
/*					click(dynDtaFilterAddBtn);
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
				thread();
				dragAndDrop(sourceObj, destinationObj);		//	Change Policy Name & Insurer position	
							
				sourceElement(dynDtaEmpWeMobile);
				destinationElement(dynDtaEmpWeAlternateEmployee);
				thread();
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
						verifyAssertEquals(drplnNewsc.getCell(12, 30).getContents(), getColumnName);	//	Insurer
					if(i == 2)
						verifyAssertEquals(drplnNewsc.getCell(12, 29).getContents(), getColumnName);	//	Policy Name
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
			}                               ---*/		              
	

	
/*---	
	@Test(priority=12)
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
/*					thread();
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
			}                                 ---*/
	                                     
	
/*---
	@Test(priority=13)
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
		
		//try
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
					driver.findElement(By.id("dyn_sel_data_fld_val_chzn_o_"+alist.get(i))).click();
					thread();
					
				/*	String randomValue = driver.findElement(By.id("ddlDDataNewFilterValues_chzn_o_"+alist.get(i))).getText();
					enterText(dynDtaFieldValueDD, randomValue);
					thread();
					enterKeyInKyBord(dynDtaFieldValueDD);  */
/*					click(dynDtaFilterAddBtn);
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
				for(int i=1; i<3; i++)
				{
					String getColumnName = driver.findElement(By.xpath("//div[@id='divDynamicDRPlanSections']/div[11]//table[@class='table table-responsive table-striped table-bordered bootstrap-datatable dynamic-data-table']//tr/td["+i+"]")).getText();
					if(i == 1)
						verifyAssertEquals(drplnNewsc.getCell(12, 37).getContents(), getColumnName);	//	Task Group Name
					if(i == 2)
						verifyAssertEquals(drplnNewsc.getCell(12, 38).getContents(), getColumnName);	//	Summary
				}
				
				click(tgsecTeamsList);
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
				thread();
			
				verifyAssert(txtDeleteConfPopup);
				getObjectText(deleteConfPpMessage);
				verifyAssertEquals(drplnNewsc.getCell(8, 6).getContents(), getActualObjectTxt);
				click(delConfOkBtn);
				thread();	
			}                           ---*/  
                                                    
	
/*---	
	@Test(priority=14)
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
					thread();
					enterKeyInKyBord(dynDtaFieldValueDD);   */
/*					click(dynDtaFilterAddBtn);
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
			}                                  */
	
	
	
}
				
	
	
	
