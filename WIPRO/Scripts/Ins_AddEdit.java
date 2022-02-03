package Scripts;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import UIWrappers.Page;
import jxl.read.biff.BiffException;
import model.InsuranceDetails;

import static UIWrappers.UIObjects.*;
import static ObjectRepository.Insurance.*;
import static ObjectRepository.Locations.*;
import static ObjectRepository.Tasks.*;
import static ObjectRepository.Threats.*;
import static Config.TakScreenshot.*;

/***************************************************************************************************************************
 * 
 * @author Sellamuthu
 * 
 * Test 1: Check 'Add New Insurance' page
 * Test 2: Add the New Insurance Policy details 
 * Test 3: Verify Insurance Policy added successfully message
 * Test 4: Check added Insurance policy details in List View
 * Test 5: Check the Policy Name in Locations, Employees, Assets & Business functions mapping pop up
 * Test 6: Map policy name to Locations, Employees, Assets & Business Functions
 * Test 7: Verify successfully mapped message for Locations, Employees, Assets & Business Functions
 * Test 8: Search Valid/Invalid mapped details in Relationship table
 * Test 9: Check policy name in List View/Edit page Relationship title
 * Test 10: Search with Valid/Invalid policy name
 * Test 11: Check the 'Clear' button functionality
 * Test 12: Edit the policy details 
 * Test 13: Verify policy details successfully updated message
 * Test 14: Check the updated details in list view
 * Test 15: Verify the 'Select All' check box 
 * Test 16: Verify the pagination links
 * Test 17: Check the 'Show Entries' drop down
 *  
****************************************************************************************************************************/

public class Ins_AddEdit extends Page {
	
	private static final String COMMA_DELIMITER = null;

	LoginLogout ll = new LoginLogout();
		
	private String policyNumber, insurer, premiumAmt, insurerContactName, year, month, insExpDate, insExpDatee, policyNameFrTtl, expDate,insuranceId;
	public String policyName;
	private int rrow;

	private String phPolicyName = "Policy Name";
	private String phPolicyNumber = "Policy Number";
	private String defPolicyType = "Select Policy Type";
	private String phInsurer = "Insurer";
	private String phPremiumAmt = "Premium";
	private String phExpiryDate = "mm/dd/yy";
	private String defInsurerContact = "Select Insurer Contact";
	private String defInsurerAdmin = "Select Insurer Admin";
	private String phInsuranceId = "Insurer Id";
	
	ArrayList<InsuranceDetails> insList = new ArrayList<InsuranceDetails>();
	
	void rchoosingValueFromTable()
	{
		getTotalValuesIndd(insInsuranceListViewTtl);
		Random randomRow = new Random();
		rrow = randomRow.nextInt(totalDDValCount-1)+1;
		
		WebElement pcyNmFrChkTtl = driver.findElement(By.xpath("//table[@id='insurance_table']//tbody/tr["+rrow+"]/td[2]"));
		policyNameFrTtl = pcyNmFrChkTtl.getText();
		pcyNmFrChkTtl.click();		
	}  
	
   /* @Test(priority=0)
	void login() throws BiffException, IOException, InterruptedException
	{
		ll.loginToSIB();
	} */   
	
	@Test(priority=1)
	void verifyInsurancePolicyAddPge() throws InterruptedException
	{
		click(insuranceInMainMenu);
		waitForPageLoad();
		Thread.sleep(1000);
		click(insOvrAddBtn);
		Thread.sleep(1000);
		verifyAssert(insPolicyName);
		Thread.sleep(1000);
		//driver.findElement(By.id("cometchat_hide")).click();
	}
	
	@Test(priority=2)
	void addInsuranceName() throws BiffException, IOException, InterruptedException
	{
		if(insList.size() == 0)
		{
			getInsuranceSheetFromExcel();
			
			for(int row=1; row<ins.getRows(); row++)
			{
				InsuranceDetails insuranceDetails = new InsuranceDetails();
				
				for(int col=0; col<ins.getColumns(); col++)
				{					
					if(col == 0)						
						insuranceDetails.setPolicyName(ins.getCell(col, row).getContents());
					if(col == 1)
						insuranceDetails.setPolicyNumber(ins.getCell(col, row).getContents());
					if(col == 2)
						insuranceDetails.setInsurer(ins.getCell(col, row).getContents());
					if(col == 3)
						insuranceDetails.setPremium(ins.getCell(col, row).getContents());
					if(col == 4)
						insuranceDetails.setInsuranceId(ins.getCell(col, row).getContents());
					if(col == 5)
						insuranceDetails.setComments(ins.getCell(col, row).getContents());					
					if(col == 6)
						insuranceDetails.setExpiryDate(ins.getCell(col, row).getContents());
				}
				insList.add(insuranceDetails);
			}			
		}
		
		Random random = new Random();
		int ranRow = random.nextInt(ins.getRows()-1)+1; 
		
		if(insList.size() > 0)
		{
			InsuranceDetails insuranceDetails  = insList.get(ranRow);
			
			//	Policy Name			
			policyName = insuranceDetails.getPolicyName().trim();
			enterText(insPolicyName, policyName);
			
			//	Policy Number	
			policyNumber = insuranceDetails.getPolicyNumber();
			enterText(insPolicyNumber, policyNumber);
			
			//	Policy Type
			click(insPolicyTypeArrow);
			getTotalValuesIndd(insPolicyTypeCnt);
			Random ranType =  new Random();
			int rTyp = ranType.nextInt(totalDDValCount-1)+1;
			String rInsType = driver.findElement(By.id("insur_type_chzn_o_"+rTyp)).getText();
			enterText(insPolicyTypeSearchBox, rInsType);
			enterKeyInKyBord(insPolicyTypeSearchBox);
			
			//	Insurer
			insurer = insuranceDetails.getInsurer();
			enterText(insInsurer, insurer);
			
			//	Premium
			premiumAmt = insuranceDetails.getPremium();
			enterText(insPremium, premiumAmt);
						
			//	Expiry Date
			click(insExpiryDate);
			click(insDatePickrDaysCalndr);
			thread();

			//click(insYrInCalTop);
			//thread();			
			//click(insMonthnYrInCalTop);
			
			click(insDatePickrMonthsCalndr);
			thread();			
			
			click(insCalndrRightSideArrow);
			
			//		Select year
			getTotalValuesIndd(insGetTotalYearsCnt);			
			Random ranYear = new Random();
			int ryear = ranYear.nextInt(totalDDValCount-1)+1;		
			year = driver.findElement(By.xpath("//div[@class='datepicker-years']//tbody/tr/td/span["+ryear+"]")).getText();
			driver.findElement(By.xpath("//span[contains(text(),"+year+")]")).click();
			thread();
			
			//		Select Month
			getTotalValuesIndd(insGetTotalMonths);
			Random ranMonth = new Random();			
			int rmonth = ranMonth.nextInt(totalDDValCount-1)+1;		
			//month = driver.findElement(By.xpath("//div[@class='datepicker-months']//tbody/tr/td/span["+rmonth+"]")).getText();
			WebElement ranMonthNm = driver.findElement(By.xpath("//div[@class='datepicker-months']//tbody/tr/td/span["+rmonth+"]")); 
			ranMonthNm.click();
			thread();
			
			//		Select Date
			getTotalValuesIndd(insGetRandomDateRowCnt);
			Random rDateRow = new Random();
			int rDateR = rDateRow.nextInt(totalDDValCount-1)+1;		
						
			Random rDateCol = new Random();
			int rDateC = rDateCol.nextInt(7-1)+1;
						
			WebElement rExpDate = driver.findElement(By.xpath("//div[@class='datepicker-days']//tbody/tr["+rDateR+"]/td["+rDateC+"]"));
			String expDate = rExpDate.getText();
			System.out.println("exp Date: "+expDate); 
			rExpDate.click();
			thread();
			
			}
			
			//	Insurer Contact
			click(insInsurerContactArrow);
			getTotalValuesIndd(insInsurerContactCnt);
			Random insContact = new Random();
			int rInsContact = insContact.nextInt(totalDDValCount-1)+1;
			insurerContactName = driver.findElement(By.id("insur_contact_chzn_o_"+rInsContact)).getText().split(",")[0];
			enterText(insInsurerContactSearchBox, insurerContactName);
			enterKeyInKyBord(insInsurerContactSearchBox);
			
			//	Insurer Admin
			click(insInsurerAdminArrow);
			getTotalValuesIndd(insInsurerAdminCnt);
			Random ranInsAdmin = new Random();
			int insAdmin = ranInsAdmin.nextInt(totalDDValCount-1)+1;
			String ranInsurAdmin = driver.findElement(By.id("insur_admin_chzn_o_"+insAdmin)).getText();
			enterText(insInsurerAdminSearchBox, ranInsurAdmin.split(",")[0]);
			enterKeyInKyBord(insInsurerAdminSearchBox);
						
			//	Insurance Id
			
			insuranceId = InsuranceDetails.getInsuranceId();
			enterText(insInsuranceId, insuranceId);
			
			//	Comments
			WebElement ifram = driver.findElement(scEdtrFrame);
			driver.switchTo().frame(ifram);
			enterText(scEdtrCommentsField, InsuranceDetails.getComments().trim());
			thread();		
			switchBackFromFrame();
			takeScreenshot();
			
			webElement(insSubmitBtn);
			scrollInnerScrollBar(webelementFrame);
			
			click(insSubmitBtn);
			thread();
			takeScreenshot();  
			
			getObjectText(msgNotificationBar);
			thread();
			verifyAssertEquals(policyName.trim()+" Successfully Added", getActualObjectTxt);
			thread();	
	}	
	
	@Test(priority=3)
	void verifyAddedPolicyNameInListView() throws InterruptedException
	{
		String[] monthArray = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
		
		getTotalValuesIndd(insInsuranceListViewTtl);
		for(int row=1; row<totalDDValCount; row++)
		{
			WebElement getAddedPcyNmInListView = driver.findElement(By.xpath("//table[@id='insurance_table']/tbody/tr["+row+"]/td[2]"));
			if(getAddedPcyNmInListView.getText().contains(policyName.trim()))
			{
				getAddedPcyNmInListView.click();
				
				for(int col=2; col<8; col++)
				{
					String getRegisteredDetails = driver.findElement(By.xpath("//table[@id='insurance_table']/tbody/tr["+row+"]/td["+col+"]")).getText();
					if(col == 2)
						verifyAssertEquals(policyName, getRegisteredDetails);		//	Policy Name
					if(col == 3)						
						verifyAssertEquals(policyNumber, getRegisteredDetails);	//	Policy Number
					if(col == 4)
						verifyAssertEquals(insurer, getRegisteredDetails);	//	Insurer
					if(col == 5)
						
						verifyAssertEquals(premiumAmt, getRegisteredDetails.replace(",", ""));	//	Premium Amount
					if(col == 6) 
					{
						for(int ma=0; ma<monthArray.length; ma++)
						{
							if(monthArray[ma].equals(month) && Integer.parseInt(monthArray[ma]) < 9)
							{
								String monthIntVal = "0"+ma+1;
								insExpDate = Integer.parseInt(monthIntVal)+"/"+expDate+"/"+year;
								verifyAssertEquals(insExpDate, getRegisteredDetails);
								thread();					
							}
							else if(monthArray[ma].equals(month) && Integer.parseInt(monthArray[ma]) >= 9)
							{
								int monthIntVal1 = ma+1;
								insExpDatee = monthIntVal1+"/"+expDate+"/"+year;
								verifyAssertEquals(insExpDate, getRegisteredDetails);
								thread();
							}
						}
					}											
					if(col == 7)
						verifyAssertEquals(insurerContactName.trim(), getRegisteredDetails);		//	Insurer Contact Name		
				}				
			}
			break;
		}		
	}
	
	@Test(priority=4)
	void verifyInsurancePolicyNmInLocationMapingPp()
	{
		click(rltnAddRemoveRltnsBtn);
		verifyAssert(mapMappingPopup);
		
		getObjectText(mapMappingPpTitleNm);
		verifyAssertEquals("Map Locations To "+policyName, getActualObjectTxt);
	}
	
	@Test(priority=5)
	void mapLocationsToInsurance() throws InterruptedException, IOException
	{
		doMappingt();
		takeScreenshot();
		click(mapSubmitBtn);
		thread();
	}
	
	@Test(priority=6)
	void verifyLocationSuccMapedToInsuranceMsg() throws InterruptedException
	{
		getObjectText(msgNotificationBar);
		verifyAssertEquals(policyName.trim()+" successfully mapped to Locations", getActualObjectTxt);
		thread();
	}
	
	@Test(priority=7)
	void searchValidMapedLocationNmInRltnList() throws InterruptedException, IOException
	{
		getTotalValuesIndd(insRltnLocationsTtlCnt);
		
		Random getrLoc = new Random();
		int rloc = getrLoc.nextInt(totalDDValCount-1)+1;
		String getAddedLocNm = driver.findElement(By.xpath("//table[@id='rel_facilities']//tbody/tr["+rloc+"]/td")).getText();
		enterText(insRltnLocationSrchBox, getAddedLocNm);
		
		getObjectText(insRltnLocationsSrchRslt);
		verifyAssertEquals(getAddedLocNm, getActualObjectTxt);
		takeScreenshot();
		clear(insRltnLocationSrchBox);
		enterKeyInKyBord(insRltnLocationSrchBox);
		thread();
	}
	
	@Test(priority=8)
	void searchInvalidMapedLocationNmInRltnList() throws InterruptedException, IOException
	{
		enterText(insRltnLocationSrchBox, "Inv Maped Loc Name");
		
		getObjectText(noRecordsFoundMsg);
		verifyAssertEquals(noMatchingRecordsFound, getActualObjectTxt);
		takeScreenshot();
		clear(insRltnLocationSrchBox);
		enterKeyInKyBord(insRltnLocationSrchBox);
		thread();
	}
	
	@Test(priority=9)
	void verifyPolicyNmInMapEmployeesPp()
	{
		click(employeesTb);
		click(rltnAddRemoveRltnsBtn);
		verifyAssert(mapMappingPopup);
		
		getObjectText(mapMappingPpTitleNm);
		verifyAssertEquals("Map Employees To "+policyName, getActualObjectTxt);
	}
	
	@Test(priority=10)
	void mapEmployeesToInsurance() throws InterruptedException, IOException
	{
		doMappingt();
		takeScreenshot();
		click(mapSubmitBtn);
		thread();
	}
	
	@Test(priority=11)
	void verifyEmployeesSuccMapedToInsuranceMsg() throws IOException, InterruptedException
	{
		getObjectText(msgNotificationBar);
		verifyAssertEquals(policyName.trim()+" successfully mapped to Employees", getActualObjectTxt);
		takeScreenshot();
		thread();
	}
	
	@Test(priority=12)
	void searchValidMapedEmployeeNmInRltnList() throws InterruptedException, IOException
	{
		getTotalValuesIndd(insRltnEmployeesTtlCnt);
		
		Random getrEmp = new Random();
		int remp = getrEmp.nextInt(totalDDValCount-1)+1;
		String getAddedEmpNm = driver.findElement(By.xpath("//table[@id='rel_resources']//tbody/tr["+remp+"]/td")).getText();
		enterText(insRltnEmployeesSrchBox, getAddedEmpNm);
		
		getObjectText(insRltnEmployeesSrchRslt);
		verifyAssertEquals(getAddedEmpNm, getActualObjectTxt);
		takeScreenshot();
		clear(insRltnEmployeesSrchBox);
		enterKeyInKyBord(insRltnEmployeesSrchBox);
		thread();
	}
	
	@Test(priority=13)
	void searchInvalidMapedEmployeeNmInRltnList() throws InterruptedException, IOException
	{
		enterText(insRltnEmployeesSrchBox, "Inv Maped Emp Name");
		
		getObjectText(noRecordsFoundMsg);
		verifyAssertEquals(noMatchingRecordsFound, getActualObjectTxt);
		takeScreenshot();
		clear(insRltnEmployeesSrchBox);
		enterKeyInKyBord(insRltnEmployeesSrchBox);
		thread();
	}
	
	@Test(priority=14)
	void verifyPolicyNmInMapAssetsPp()
	{
		click(assetsTb);
		click(rltnAddRemoveRltnsBtn);
		verifyAssert(mapMappingPopup);
		
		getObjectText(mapMappingPpTitleNm);
		verifyAssertEquals("Map Assets To "+policyName, getActualObjectTxt);
	}
	
	@Test(priority=15)
	void mapAssetsToInsurance() throws InterruptedException, IOException
	{
		doMappingt();
		takeScreenshot();
		click(mapSubmitBtn);
		thread();
	}
	
	@Test(priority=16)
	void verifyAssetsSuccMapedToInsuranceMsg() throws IOException, InterruptedException
	{
		getObjectText(msgNotificationBar);
		verifyAssertEquals(policyName.trim()+" successfully mapped to Assets", getActualObjectTxt);
		takeScreenshot();
		thread();
	}
	
	@Test(priority=17)
	void searchValidMapedAssetNmInRltnList() throws InterruptedException, IOException
	{
		getTotalValuesIndd(insRltnAssetsTtlCnt);
		
		Random getrAst = new Random();
		int rast = getrAst.nextInt(totalDDValCount-1)+1;
		String getAddedAstNm = driver.findElement(By.xpath("//table[@id='rel_assets']//tbody/tr["+rast+"]/td")).getText();
		enterText(insRltnAssetsrchBox, getAddedAstNm.substring(4));
		
		getObjectText(insRltnAssetsSrchRslt);
		verifyAssertEquals(getAddedAstNm, getActualObjectTxt);
		takeScreenshot();
		clear(insRltnAssetsrchBox);
		enterKeyInKyBord(insRltnAssetsrchBox);
		thread();
	}
	
	@Test(priority=18)
	void searchInvalidMapedAssetNmInRltnList() throws InterruptedException, IOException
	{
		enterText(insRltnAssetsrchBox, "Inv Maped Ast Name");
		
		getObjectText(noRecordsFoundMsg);
		verifyAssertEquals(noMatchingRecordsFound, getActualObjectTxt);
		takeScreenshot();
		clear(insRltnAssetsrchBox);
		enterKeyInKyBord(insRltnAssetsrchBox);
		thread();
	}

	@Test(priority=19)
	void verifyPolicyNmInMapBusinessFuncsPp()
	{
		click(businessFunctionsTb);
		click(rltnAddRemoveRltnsBtn);
		verifyAssert(mapMappingPopup);
		
		getObjectText(mapMappingPpTitleNm);
		verifyAssertEquals("Map Business Functions To "+policyName, getActualObjectTxt);
	}
	
	@Test(priority=20)
	void mapBusinessFuncsToInsurance() throws InterruptedException, IOException
	{
		doMappingt();
		takeScreenshot();
		click(mapSubmitBtn);
		thread();
	}
	
	@Test(priority=21)
	void verifyBusinessFuncsSuccMapedToInsuranceMsg() throws IOException, InterruptedException
	{
		getObjectText(msgNotificationBar);
		verifyAssertEquals(policyName+" successfully mapped to Business Functions", getActualObjectTxt);
		takeScreenshot();
		thread();
	}
	
	@Test(priority=22)
	void searchValidMapedBusinessFuncsNmInRltnList() throws InterruptedException, IOException
	{
		getTotalValuesIndd(insRltnBusinessFuncsTtlCnt);
		
		Random getrBusFunc = new Random();
		int rbusfunc = getrBusFunc.nextInt(totalDDValCount-1)+1;
		String getAddedBusinesFuncNm = driver.findElement(By.xpath("//table[@id='rel_processes']//tbody/tr["+rbusfunc+"]/td")).getText();
		enterText(insRltnBusinessFuncsSrchBox, getAddedBusinesFuncNm);
		
		getObjectText(insRltnBusinessFuncsSrchRslt);
		verifyAssertEquals(getAddedBusinesFuncNm, getActualObjectTxt);
		takeScreenshot();
		clear(insRltnBusinessFuncsSrchBox);
		enterKeyInKyBord(insRltnBusinessFuncsSrchBox);
		thread();
	}
	
	@Test(priority=23)
	void searchInvalidMapedBusinesFuncsNmInRltnList() throws InterruptedException, IOException
	{
		enterText(insRltnBusinessFuncsSrchBox, "Inv Maped Busines Funcs Name");
		
		getObjectText(noRecordsFoundMsg);
		verifyAssertEquals(noMatchingRecordsFound, getActualObjectTxt);
		takeScreenshot();
		clear(insRltnBusinessFuncsSrchBox);
		enterKeyInKyBord(insRltnBusinessFuncsSrchBox);
		thread();
	}
	
	@Test(priority=24)
	void verifyAddedPolicyNmInRelationshipTitle() throws IOException
	{
		rchoosingValueFromTable();
		
		getObjectText(selectdNmeInRltnBar);
		verifyAssertEquals(policyNameFrTtl, getActualObjectTxt.substring(4).trim());
		takeScreenshot();
		
	}
	
	@Test(priority=25)
	void verifyPolicyNameInEditPgeRelationshipTitle() throws IOException, InterruptedException
	{
		rchoosingValueFromTable();
		
		driver.findElement(By.xpath("//table[@id='insurance_table']/tbody/tr["+rrow+"]/td[10]/a[1]")).click();	
		thread();
		
		getObjectText(selectdNmeInRltnBar);
		verifyAssertEquals(policyNameFrTtl, getActualObjectTxt.substring(4).trim());
		takeScreenshot();
			
		click(insCancelBtn);
	}
	
	@Test(priority=26)
	void verifyClearBtnFunc() throws InterruptedException
	{
		//rchoosingValueFromTable();
		
		//driver.findElement(By.xpath("//table[@id='insurance_table']/tbody/tr["+rrow+"]/td[10]/a[1]")).click();
		driver.findElement(By.xpath("//table[@id='insurance_table']/tbody/tr[1]/td[10]/a[1]")).click();
		thread();
		
		click(insClearBtn);
		
		//	Policy Name
		getAttributePh(insPolicyName);
		verifyAssertEquals(phPolicyName, getAttribtePh);
		
		//	Policy Number
		getAttributePh(insPolicyNumber);
		verifyAssertEquals(phPolicyNumber, getAttribtePh);
		
		//	Policy Type
		getObjectText(insPolicyTypeDefTxt);
		verifyAssertEquals(defPolicyType, getActualObjectTxt);
		
		//	Insurer
		getAttributePh(insInsurer);
		verifyAssertEquals(phInsurer, getAttribtePh);
		
		//	Premium Amount
		getAttributePh(insPremium);
		verifyAssertEquals(phPremiumAmt, getAttribtePh);
		
		//	Expiry Date
		getAttributePh(insExpiryDate);
		verifyAssertEquals(phExpiryDate, getAttribtePh);
		
		//	Insurer Contact
		getObjectText(insInsurerContactDefTxt);
		verifyAssertEquals(defInsurerContact, getActualObjectTxt);
		
		//	Insurer Admin
		getObjectText(insInsurerAdminDefTxt);
		verifyAssertEquals(defInsurerAdmin, getActualObjectTxt);
		
		//	Insurance Id
		getAttributePh(insInsuranceId);
		verifyAssertEquals(phInsuranceId, getAttribtePh);
		
		//	Comments
		WebElement ifram = driver.findElement(scEdtrFrame);
		driver.switchTo().frame(ifram);
		
		getObjectText(scEdtrCommentsField);
		verifyAssertEquals("", getActualObjectTxt);
		switchBackFromFrame();	
		
		click(insCancelBtn);
		thread();
	}
	
	@Test(priority=27)
	void editInsurancePolicyDetails() throws BiffException, IOException, InterruptedException
	{
		
		//driver.findElement(By.xpath("//table[@id='insurance_table']/tbody/tr["+rrow+"]/td[10]/a[1]")).click();
		driver.findElement(By.xpath("//table[@id='insurance_table']/tbody/tr[1]/td[10]/a[1]")).click();
		thread();
		
		click(insClearBtn);
		thread();
		
		if(insList.size() == 0)
		{
			getInsuranceSheetFromExcel();
			
			for(int row=1; row<ins.getRows(); row++)
			{
				InsuranceDetails insuranceDetails = new InsuranceDetails();
				
				for(int col=0; col<ins.getColumns(); col++)
				{					
					if(col == 0)						
						insuranceDetails.setPolicyName(ins.getCell(col, row).getContents());
					if(col == 1)
						insuranceDetails.setPolicyNumber(ins.getCell(col, row).getContents());
					if(col == 2)
						insuranceDetails.setInsurer(ins.getCell(col, row).getContents());
					if(col == 3)
						insuranceDetails.setPremium(ins.getCell(col, row).getContents());
					if(col == 4)
						insuranceDetails.setInsuranceId(ins.getCell(col, row).getContents());
					if(col == 5)
						insuranceDetails.setComments(ins.getCell(col, row).getContents());					
					if(col == 6)
						insuranceDetails.setExpiryDate(ins.getCell(col, row).getContents());
				}
				insList.add(insuranceDetails);
			}			
		}
		
		Random random = new Random();
		int ranRow = random.nextInt(ins.getRows()-1)+1; 
		
		if(insList.size() > 0)
		{
			InsuranceDetails insuranceDetails  = insList.get(ranRow);
			
			//	Policy Name			
			policyName = insuranceDetails.getPolicyName().trim();
			enterText(insPolicyName, policyName);
			
			//	Policy Number	
			policyNumber = insuranceDetails.getPolicyNumber();
			enterText(insPolicyNumber, policyNumber);
			
			//	Policy Type
			click(insPolicyTypeArrow);
			getTotalValuesIndd(insPolicyTypeCnt);
			Random ranType =  new Random();
			int rTyp = ranType.nextInt(totalDDValCount-1)+1;
			String rInsType = driver.findElement(By.id("insur_type_chzn_o_"+rTyp)).getText();
			enterText(insPolicyTypeSearchBox, rInsType);
			enterKeyInKyBord(insPolicyTypeSearchBox);
			
			//	Insurer
			insurer = insuranceDetails.getInsurer();
			enterText(insInsurer, insurer);
			
			//	Premium
			premiumAmt = insuranceDetails.getPremium();
			enterText(insPremium, premiumAmt);
						
			//	Expiry Date
			click(insExpiryDate);
			click(insDatePickrDaysCalndr);
			thread();

			//click(insYrInCalTop);
			//thread();			
			//click(insMonthnYrInCalTop);
			
			click(insDatePickrMonthsCalndr);
			thread();			
			
			click(insCalndrRightSideArrow);
			
			//		Select year
			getTotalValuesIndd(insGetTotalYearsCnt);			
			Random ranYear = new Random();
			int ryear = ranYear.nextInt(totalDDValCount-1)+1;		
			year = driver.findElement(By.xpath("//div[@class='datepicker-years']//tbody/tr/td/span["+ryear+"]")).getText();
			driver.findElement(By.xpath("//span[contains(text(),"+year+")]")).click();
			thread();
			
			//		Select Month
			getTotalValuesIndd(insGetTotalMonths);
			Random ranMonth = new Random();			
			int rmonth = ranMonth.nextInt(totalDDValCount-1)+1;		
			//month = driver.findElement(By.xpath("//div[@class='datepicker-months']//tbody/tr/td/span["+rmonth+"]")).getText();
			WebElement ranMonthNm = driver.findElement(By.xpath("//div[@class='datepicker-months']//tbody/tr/td/span["+rmonth+"]")); 
			ranMonthNm.click();
			thread();
			
			//		Select Date
			getTotalValuesIndd(insGetRandomDateRowCnt);
			Random rDateRow = new Random();
			int rDateR = rDateRow.nextInt(totalDDValCount-1)+1;		
						
			Random rDateCol = new Random();
			int rDateC = rDateCol.nextInt(7-1)+1;
						
			WebElement rExpDate = driver.findElement(By.xpath("//div[@class='datepicker-days']//tbody/tr["+rDateR+"]/td["+rDateC+"]"));
			String expDate = rExpDate.getText();
			System.out.println("exp Date: "+expDate); 
			rExpDate.click();
			thread();
			
			}
			
			//	Insurer Contact
			click(insInsurerContactArrow);
			getTotalValuesIndd(insInsurerContactCnt);
			Random insContact = new Random();
			int rInsContact = insContact.nextInt(totalDDValCount-1)+1;
			insurerContactName = driver.findElement(By.id("insur_contact_chzn_o_"+rInsContact)).getText().split(",")[0];
			enterText(insInsurerContactSearchBox, insurerContactName);
			enterKeyInKyBord(insInsurerContactSearchBox);
			
			//	Insurer Admin
			click(insInsurerAdminArrow);
			getTotalValuesIndd(insInsurerAdminCnt);
			Random ranInsAdmin = new Random();
			int insAdmin = ranInsAdmin.nextInt(totalDDValCount-1)+1;
			String ranInsurAdmin = driver.findElement(By.id("insur_admin_chzn_o_"+insAdmin)).getText();
			enterText(insInsurerAdminSearchBox, ranInsurAdmin.split(",")[0]);
			enterKeyInKyBord(insInsurerAdminSearchBox);
						
			//	Insurance Id
			
			insuranceId = InsuranceDetails.getInsuranceId();
			enterText(insInsuranceId, insuranceId);
			
			//	Comments
			WebElement ifram = driver.findElement(scEdtrFrame);
			driver.switchTo().frame(ifram);
			enterText(scEdtrCommentsField, InsuranceDetails.getComments().trim());
			thread();		
			switchBackFromFrame();
			takeScreenshot();
			
			webElement(insSubmitBtn);
			scrollInnerScrollBar(webelementFrame);
			
			click(insSubmitBtn);
			thread();
			takeScreenshot();  
		
		   getObjectText(msgNotificationBar);
		   verifyAssertEquals(policyName.trim()+" Successfully Updated", getActualObjectTxt);
		   takeScreenshot();
		   thread();
	}
	
	@Test(priority=28)
	void verifyModifiedDetailsInListView() throws InterruptedException
	{
		String[] monthArray = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
		
		getTotalValuesIndd(insInsuranceListViewTtl);
		for(int row=1; row<totalDDValCount; row++)
		{
			if(driver.findElement(By.xpath("//table[@id='insurance_table']/tbody/tr["+row+"]/td[2]")).getText().contains(policyName))
			{
				for(int col=2; col<8; col++)
				{
					String getRegisteredDetails = driver.findElement(By.xpath("//table[@id='insurance_table']/tbody/tr["+row+"]/td["+col+"]")).getText();
					if(col == 2)
						verifyAssertEquals(policyName, getRegisteredDetails);
					if(col == 3)						
						verifyAssertEquals(policyNumber, getRegisteredDetails);
					if(col == 4)
						verifyAssertEquals(insurer.trim(), getRegisteredDetails);
					if(col == 5)
						verifyAssertEquals(premiumAmt, getRegisteredDetails.replace(",", ""));
					if(col == 6) 
						
					{
						for(int ma=0; ma<monthArray.length; ma++)
						{
							if(monthArray[ma].equals(month) && Integer.parseInt(monthArray[ma]) < 9)
							{
								String monthIntVal = "0"+ma+1;
								insExpDate = Integer.parseInt(monthIntVal)+"/"+expDate+"/"+year;
								verifyAssertEquals(insExpDate, getRegisteredDetails);
								thread();					
							}
							else if(monthArray[ma].equals(month) && Integer.parseInt(monthArray[ma]) >= 9)
							{
								int monthIntVal1 = ma+1;
								insExpDatee = monthIntVal1+"/"+expDate+"/"+year;
								verifyAssertEquals(insExpDate, getRegisteredDetails);
								thread();
							}
						}
					}					
					if(col == 7)
						verifyAssertEquals(insurerContactName.trim(), getRegisteredDetails);					
				}				
			}	
			break;
		}	
	}
	
	@Test(priority=29)
	void searchValidInsurancePolicyName() throws IOException, InterruptedException
	{
		rchoosingValueFromTable();
		
		enterText(insInsuranceSearchBox, policyNameFrTtl);
		enterKeyInKyBord(insInsuranceSearchBox);
		thread();
		getObjectText(insInsuranceSearchRslt);
		verifyAssertEquals(policyNameFrTtl, getActualObjectTxt.trim());
		takeScreenshot();
		clear(insInsuranceSearchBox);
		enterKeyInKyBord(insInsuranceSearchBox);
		thread();
	}
	
	@Test(priority=30)
	void searchInvalidInsurancePolicyName() throws IOException, InterruptedException
	{
		enterText(insInsuranceSearchBox, "Invalid Ins Policy Name");
		getObjectText(noRecordsFoundMsg);
		verifyAssertEquals(noMatchingRecordsFound, getActualObjectTxt);
		takeScreenshot();
		clear(insInsuranceSearchBox);
		enterKeyInKyBord(insInsuranceSearchBox);
		thread();
	}
	
	@Test(priority=31)
	void verifySelectAllchkBoxFunc() throws IOException, InterruptedException
	{
		click(insSelectAllChkBox);
		takeScreenshot();
		
		getTotalValuesIndd(insInsuranceListViewTtl);
		for(int i=1; i<=totalDDValCount; i++)
		{
			boolean chkd = driver.findElement(By.xpath("//table[@id='insurance_table']/tbody/tr["+i+"]/td/div/span/input")).isEnabled();
			Assert.assertEquals(true, chkd);					
		}		
		click(insSelectAllChkBox);
		thread();
	}
	
	@Test(priority=32)
	void insuranceListViewPagination() throws IOException, InterruptedException
	{
		getTotalValuesIndd(insInsuranceListViewPagination);
		
		if(totalDDValCount > 5)
		{
			click(pagiEndArw);
			takeScreenshot();
			thread();
			
			click(pagiStartArw);
			takeScreenshot();
			thread();
			
			if(totalDDValCount == 6)
			{
				click(pagiNextArw);
				takeScreenshot();
				thread();
				
				click(pagiPreviousArw);
				takeScreenshot();
				thread();
			}
			
			//	Click Pagination Number
			Random randomPagi = new Random();
			int randomPge = randomPagi.nextInt((totalDDValCount-2)-4)+4;
			driver.findElement(By.xpath("//table[@id='insurance_table']//div/div[2]/div/ul/li["+randomPge+"]/a")).click();
			thread();
			takeScreenshot();
			click(pagiStartArw);
			thread();
		}
	}
	
	@Test(priority=33)
	void verifyShowEntriesDropDown() throws InterruptedException, IOException
	{
		try
		
		{
			getObjectText(insListViewRecsInfo);
			
			if(Integer.parseInt(getActualObjectTxt.split("of ")[1].split(" ")[0].trim()) > 10)
			{
				selectTextFromDropdown(insListViewLength , "25");
				thread();
				takeScreenshot();
				
				selectTextFromDropdown(insListViewLength , "10");
				thread();
			}
		}
		catch(WebDriverException e)
		{
			System.out.println("Insurance list view has 10 or less than 10 records. So values are not selected from show entries drop down");
		}
	}
	
	/*@Test(priority=34)
    void Documentupload() throws IOException, InterruptedException, AWTException
    {
    	
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
        StringSelection ss = new StringSelection("D:\\SIB\\Logo\\FatPipe_logo.jpg");
	        thread();
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);

        thread();
        //imitate mouse events like ENTER, CTRL+C, CTRL+V
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        // click to upload the files D:\SIB\Logo\FatPipe_logo.jpg
        
         click(docUpload);
         thread();
         thread();
      }*/    
        
	@Test(priority=35)
	void verifyViewPage()throws IOException, InterruptedException
	{
		click(insViewBtn);
		takeScreenshot();
     	getObjectText(insViewPopup);
     	thread();
     	click(insViewCloseBtn);
     	thread();
	} 
	
	
	
	/*@Test(priority=36)
	void logout() throws InterruptedException
	{
		ll.logoutSession();
		thread();
		closeWindow();
	}*/
}
