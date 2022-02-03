package Scripts;

import java.io.IOException;
import java.util.Random;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import UIWrappers.Page;
import jxl.read.biff.BiffException;
import static ObjectRepository.Employees.*;
import static ObjectRepository.Locations.*;
import static UIWrappers.UIObjects.*;

public class Emp_Validations extends Page 
{
	
   LoginLogout ll = new LoginLogout();
   Emp_AddEdit ea=new Emp_AddEdit();
   
   
	@Test(priority=0)
	void login() throws BiffException, IOException, InterruptedException
	{
		ll.loginToSIB();
	}  
	
	@Test(priority=1)
	void empHighlightedInMainMenu() throws InterruptedException
	{
		click(employeesInMainMenu);
		thread();
		getHighlightOptn(mainEmployeesOptn);
		verifyAssertEquals("active", getHighlightdTxt);	
	}
	
	@Test(priority=2)
	void empMandatoryFieldsValidation() throws InterruptedException, BiffException, IOException
	{
		
		getEmpSheetNameFromExcel();
		Random rand=new Random();
		int re=rand.nextInt(emp.getRows()-1)+1;
		
		click(empOvrAddBtn);
		thread();
		
		click(empSubmitBtn);
		
		getObjectText(stepValidatnMsg);
		verifyAssertEquals("First or Last Name is required.",getActualObjectTxt);
		thread();
		
		ea.firstName = emp.getCell(0, re).getContents();
		enterText(empFirstName, ea.firstName);	
        click(empSubmitBtn);		
		getObjectText(stepValidatnMsg);
		verifyAssertEquals("E-mail is required.",getActualObjectTxt);
		thread();
		
		ea.emailId = emp.getCell(7, re).getContents();
		enterText(empEmail, ea.emailId);		
		click(empSubmitBtn);			
		getObjectText(stepValidatnMsg);
		verifyAssertEquals("Mobile Number is required.",getActualObjectTxt);
		thread();
		
		ea.mobileN0 = emp.getCell(9, re).getContents();
		enterText(empMobile, ea.mobileN0);		
		click(empSubmitBtn);			
		getObjectText(stepValidatnMsg);
		verifyAssertEquals("Country is required.",getActualObjectTxt);
		thread();
		
		click(empCountryDDArrow);
		enterText(empCountryDDSrchBox, emp.getCell(14, re).getContents());
		enterKeyInKyBord(empCountryDDSrchBox);
		click(empSubmitBtn);			
		getObjectText(stepValidatnMsg);
		verifyAssertEquals("State is required.",getActualObjectTxt);
		thread();
		
		click(empStateDDArrow);
		enterText(empStateDDSrchBox, emp.getCell(15, re).getContents());			
		enterKeyInKyBord(empStateDDSrchBox);
		click(empSubmitBtn);			
		getObjectText(stepValidatnMsg);
		verifyAssertEquals("Location is required.",getActualObjectTxt);
		thread();
		
		click(empLocationArrow);
		getTotalValuesIndd(empLocationCnt);
		if(totalDDValCount > 1)
		{
			Random selectLocation = new Random();
			int empLoc = selectLocation.nextInt(totalDDValCount-1)+1;
			ea.location = driver.findElement(By.id("location_chzn_o_"+empLoc)).getText();
			enterText(empLocationSearchBox, ea.location);
			enterKeyInKyBord(empLocationSearchBox);
			thread();
		}	
	}
	
	
}
