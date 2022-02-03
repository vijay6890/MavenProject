package Scripts;

import static Config.TakScreenshot.takeScreenshot;
import static ObjectRepository.Locations.*;
import static ObjectRepository.Threats.thrListViewRecsInfo;
import static ObjectRepository.Threats.viewThrCmts;
import static ObjectRepository.Threats.viewThrNme;
import static ObjectRepository.Threats.viewThrType;
import static ObjectRepository.Employees.*;
import static UIWrappers.UIObjects.*;

import java.awt.AWTException;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import UIWrappers.Page;
import jxl.read.biff.BiffException;

public class Emp_ImportExport extends Page {
	
	 String totalEntries;
     public String downloadPath="D:\\SIB\\Downloads";
     int rVal,appRows;
     String[] val;
	LoginLogout ll = new LoginLogout();
	
	@Test(priority=0)
	void login() throws BiffException, IOException, InterruptedException
	{
		ll.loginToSIB();
	}
	
	@Test(priority=1)
	void clickonEmployeeMainMenu() throws InterruptedException
	{
		click(employeesInMainMenu);
		  thread();
	}
	

	@Test(priority=2)
	void verifyEmployeesImportBtn() throws InterruptedException, AWTException, IOException
	{
		click(empImportBtn);
		thread();
		click (empImportUploadBtn);
		thread();
	   // uploadFile("C:\\Users\\Tamizhselvi\\Desktop\\Import file\\Employees.csv");
		uploadFile("D:\\SIB\\ImportFiles\\Employees.csv");
		click(empImportSubmitBtn);
		verifyAssert(empEmployeesSearchBox);
		
		getObjectText(msgNotificationBar);
		takeScreenshot();
		verifyAssertEquals("1618 employees imported successfully" , getActualObjectTxt);	
		thread();
		
		
	}
	
	@Test(priority=3)
	void verifyEmployeesCancelBtn() throws InterruptedException, IOException
	{
		click(empImportBtn);
		thread();
		click(empImportCancelBtn);
		verifyAssert(empEmployeesSearchBox);
		takeScreenshot();	
		thread();
		
	}
	
    void verifyEmployeesImportHelpDoc() throws InterruptedException, AWTException
	
	{
	
		click(empImportBtn);
		click(empImportHelpDocBtn);
		thread();
		download();
		thread();
	}
	
	
	@Test(priority=5)
	void verifyEmployeesExportBtn() throws AWTException, InterruptedException
	{
		click(employeesInMainMenu);
		  thread();
		  
		  webElement(empPaginationInfo);
			scrollInnerScrollBar(webelementFrame);
			
			getObjectText(empPaginationInfo);
			thread();
			totalEntries = getActualObjectTxt.split(" ")[5];
					
			if(totalEntries.contains(","))
			{
				totalEntries=totalEntries.replaceAll(",", "");
			}
			appRows=Integer.parseInt(totalEntries);
						
			System.out.println("Total Entries in App "+totalEntries);
			
			if(appRows==0)
			{
				int c=1/0;
			}
			
		  click(empExportBtn);
		  thread();
		   download();
           thread();
		  
	}
	
	@Test(priority=6,dependsOnMethods={"verifyEmployeesExportBtn"})
	void validateEmployeesExportedCSVFile() throws InterruptedException, IOException
	{
		try
		{
        File file = getLatestFilefromDir(downloadPath);
   		String csvFileName = file.getName();
   		System.out.println("CSV File Downloaded is :- "+csvFileName);
   		
        //Read csv file
   		
        LinkedList<String> ll=readCSVFileLines(downloadPath+"\\"+csvFileName);
   		
        int csvRows=ll.size()-1;
   		System.out.println("Total rows in CSV "+csvRows);
    	   		
   		try
   		{
   	    Assert.assertEquals(appRows,csvRows);	
   	    
   		thread();
   		System.out.println("validation successfully completed");
   		}
   		catch(Error e)
   		{
   			System.out.println("validation unsuccessful "+e);
   		}	
   		
   		
   		WebElement selectDD=driver.findElement(By.xpath("//div[@id='resource_table_length']/label/select"));
   		Select sel=new Select(selectDD);
   		sel.selectByValue("100");
   		thread();
   		
   		getTotalValuesIndd(By.xpath("//table[@id='resource_table']/tbody/tr"));	   		
   	    int pgCnt=Integer.parseInt(totalEntries);
   	  
   	 outerforloop:  
   	    for(rVal=1;rVal<=pgCnt;rVal++)
		{
		thread();
		while(rVal==1 || rVal==pgCnt)  //comparing 1st n last record values
		{
			if(rVal==pgCnt)
			{
				rVal=pgCnt%100;
				//val=ll.get(rVal).split(",");
				val=ll.get(pgCnt).split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
			}
			else
			{
				//val=ll.get(rVal).split(",");
				val=ll.get(rVal).split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
			}
			thread();
			
		webElement(By.xpath("//table[@id='resource_table']/tbody/tr["+rVal+"]/td[11]/a[3]"));	
		scrollInnerScrollBar(webelementFrame);
		thread();
		
		click(By.xpath("//table[@id='resource_table']/tbody/tr["+rVal+"]/td[11]/a[3]"));
		thread();
		System.out.println("Row Number "+rVal);
			   			   		
		//String[] val=ll.get(rVal).split(",");
		for(int i=0;i<val.length;i++)
		{
			
			//System.out.println(" "+val[i]);	   			
			if(i==1)
			{
				try{
				getObjectText(viewEmpFstNme);
				if(getActualObjectTxt.trim().equals("No Data"))
				{
					getActualObjectTxt="";
				}
		   		verifyAssertEquals(val[i].replaceAll("\"", "").trim(),getActualObjectTxt.trim());
				}catch(Error e)
				{
					System.out.println("No data "+e);
				}
			}
			else if(i==2)
			{
				try{
				getObjectText(viewEmpLstNme);
				if(getActualObjectTxt.trim().equals("No Data"))
				{
					getActualObjectTxt="";
				}
		   		verifyAssertEquals(val[i].replaceAll("\"", "").trim(),getActualObjectTxt.trim());
				}catch(Error e)
				{
					System.out.println("No data "+e);
				}
			}
			else if(i==3)
			{
				try{	   					
	   			getObjectText(viewEmpTitle);
	   			if(getActualObjectTxt.trim().equals("No Data"))
				{
					getActualObjectTxt="";
				}
		   		verifyAssertEquals(val[i].replaceAll("\"", "").trim(),getActualObjectTxt.trim());
				}catch(Error e)
				{
					System.out.println("No data "+e);
				}
			}
			else if(i==4)
			{
				try{	   					
	   			getObjectText(viewEmpType);
	   			if(getActualObjectTxt.trim().equals("No Data"))
				{
					getActualObjectTxt="";
				}
		   		verifyAssertEquals(val[i].replaceAll("\"", "").trim(),getActualObjectTxt.trim());
				}catch(Error e)
				{
					System.out.println("No data "+e);
				}
			}
			else if(i==5)
			{
				try{	   					
	   			getObjectText(viewEmpStatus);
	   			if(getActualObjectTxt.trim().equals("No Data"))
				{
					getActualObjectTxt="";
				}
		   		verifyAssertEquals(val[i].replaceAll("\"", "").trim(),getActualObjectTxt.trim());
				}catch(Error e)
				{
					System.out.println("No data "+e);
				}
			}
			else if(i==6)
			{
				try{	   					
	   			getObjectText(viewEmpDept);
	   			if(getActualObjectTxt.trim().equals("No Data"))
				{
					getActualObjectTxt="";
				}
		   		verifyAssertEquals(val[i].replaceAll("\"", "").trim(),getActualObjectTxt.trim());
				}catch(Error e)
				{
					System.out.println("No data "+e);
				}
			}
			else if(i==7)
			{
				try{	   					
	   			getObjectText(viewEmpBsPh);
	   			if(getActualObjectTxt.trim().equals("No Data"))
				{
					getActualObjectTxt="";
				}
		   		verifyAssertEquals(val[i].replaceAll("\"", "").trim(),getActualObjectTxt.trim());
				}catch(Error e)
				{
					System.out.println("No data "+e);
				}
			}
			else if(i==8)
			{
				try{	   					
	   			getObjectText(viewEmpResNo);
	   			if(getActualObjectTxt.trim().equals("No Data"))
				{
					getActualObjectTxt="";
				}
		   		verifyAssertEquals(val[i].replaceAll("\"", "").trim(),getActualObjectTxt.trim());
				}catch(Error e)
				{
					System.out.println("No data "+e);
				}
			}
			else if(i==9)
			{
				try{	   					
	   			getObjectText(viewEmpMobile);
	   			if(getActualObjectTxt.trim().equals("No Data"))
				{
					getActualObjectTxt="";
				}
		   		verifyAssertEquals(val[i].replaceAll("\"", "").trim(),getActualObjectTxt.trim());
				}catch(Error e)
				{
					System.out.println("No data "+e);
				}
			}
			else if(i==10)
			{
				try{	   					
	   			getObjectText(viewEmpOfcNo);
	   			thread();	   			
	   			getActualObjectTxt=getActualObjectTxt.split("-")[0];	   		
	   			if(getActualObjectTxt.trim().equals("No Data"))
				{
					getActualObjectTxt="";
				}	   			
		   		verifyAssertEquals(val[i].replaceAll("\"", "").trim(),getActualObjectTxt.trim());
				}catch(Error e)
				{
					System.out.println("No data "+e);
				}
			}
			else if(i==11)
			{
				try{	   					
	   			getObjectText(viewEmpOfcNo);
	   			getActualObjectTxt=getActualObjectTxt.split("-")[1];	   			
	   			if(getActualObjectTxt.trim().equals("No Data"))
				{
					getActualObjectTxt="";
				}
	   			verifyAssertEquals(val[i].replaceAll("\"", "").trim(),getActualObjectTxt.trim());
				}catch(Error e)
				{
					System.out.println("No data "+e);
				}
			}
			else if(i==13)
			{
				try{	   					
	   			getObjectText(viewEmpEmail);
	   			if(getActualObjectTxt.trim().equals("No Data"))
				{
					getActualObjectTxt="";
				}
		   		verifyAssertEquals(val[i].replaceAll("\"", "").trim(),getActualObjectTxt.trim());
				}catch(Error e)
				{
					System.out.println("No data "+e);
				}
			}
			else if(i==14)
			{
				try{	   					
	   			getObjectText(viewEmpSecEmail);
	   			if(getActualObjectTxt.trim().equals("No Data"))
				{
					getActualObjectTxt="";
				}
		   		verifyAssertEquals(val[i].replaceAll("\"", "").trim(),getActualObjectTxt.trim());
				}catch(Error e)
				{
					System.out.println("No data "+e);
				}
			}
			else if(i==15)
			{
				try{	   					
	   			getObjectText(viewEmpSecMobile);
	   			if(getActualObjectTxt.trim().equals("No Data"))
				{
					getActualObjectTxt="";
				}
		   		verifyAssertEquals(val[i].replaceAll("\"", "").trim(),getActualObjectTxt.trim());
				}catch(Error e)
				{
					System.out.println("No data "+e);
				}
			}
			else if(i==16)
			{
				try{	   					
	   			getObjectText(viewEmpAddr);
	   			if(getActualObjectTxt.trim().equals("No Data"))
				{
					getActualObjectTxt="";
				}
		   		verifyAssertEquals(val[i].replaceAll("\"", "").trim(),getActualObjectTxt.trim());
				}catch(Error e)
				{
					System.out.println("No data "+e);
				}
			}
			else if(i==17)
			{
				try{	   					
	   			getObjectText(viewEmpState);
	   			if(getActualObjectTxt.trim().equals("No Data"))
				{
					getActualObjectTxt="";
				}
		   		verifyAssertEquals(val[i].replaceAll("\"", "").trim(),getActualObjectTxt.trim());
				}catch(Error e)
				{
					System.out.println("No data "+e);
				}
			}
			else if(i==18)
			{
				try{	   					
	   			getObjectText(viewEmpCity);
	   			if(getActualObjectTxt.trim().equals("No Data"))
				{
					getActualObjectTxt="";
				}
		   		verifyAssertEquals(val[i].replaceAll("\"", "").trim(),getActualObjectTxt.trim());
				}catch(Error e)
				{
					System.out.println("No data "+e);
				}
			}
			else if(i==19)
			{
				try{	   					
	   			getObjectText(viewEmpCntry);
	   			if(getActualObjectTxt.trim().equals("No Data"))
				{
					getActualObjectTxt="";
				}
		   		verifyAssertEquals(val[i].replaceAll("\"", "").trim(),getActualObjectTxt.trim());
				}catch(Error e)
				{
					System.out.println("No data "+e);
				}
			}
			else if(i==20)
			{
				try{	   					
	   			getObjectText(viewEmpZipCode);
	   			if(getActualObjectTxt.trim().equals("No Data"))
				{
					getActualObjectTxt="";
				}
		   		verifyAssertEquals(val[i].replaceAll("\"", "").trim(),getActualObjectTxt.trim());
				}catch(Error e)
				{
					System.out.println("No data "+e);
				}
			}
			else if(i==21)
			{
				try{	   					
	   			getObjectText(viewEmpId);
	   			if(getActualObjectTxt.trim().equals("No Data"))
				{
					getActualObjectTxt="";
				}
		   		verifyAssertEquals(val[i].replaceAll("\"", "").trim(),getActualObjectTxt.trim());
				}catch(Error e)
				{
					System.out.println("No data "+e);
				}
			}
			else if(i==22)
			{
				try{	   					
	   			getObjectText(viewEmpLoc);
	   			if(getActualObjectTxt.trim().equals("No Data"))
				{
					getActualObjectTxt="";
				}
		   		verifyAssertEquals(val[i].replaceAll("\"", "").trim(),getActualObjectTxt.trim());
				}catch(Error e)
				{
					System.out.println("No data "+e);
				}
			}
			else if(i==23)
			{
				try{	   					
	   			getObjectText(viewEmpAltrEmp);
	   			if(getActualObjectTxt.trim().equals("No Data"))
				{
					getActualObjectTxt="";
				}
		   		verifyAssertEquals(val[i].replaceAll("\"", "").trim(),getActualObjectTxt.trim());
				}catch(Error e)
				{
					System.out.println("No data "+e);
				}
			}
			else{
				//
			}
			   			
		}
		thread();
		webElement(viewEmpCloseBtn);
		scrollInnerScrollBar(webelementFrame);
		
		click(viewEmpCloseBtn);  //close button in view page
		thread();
		
		webElement(By.xpath("//div[@id='resource_table_wrapper']/div[2]/div[2]/div/ul/li[9]/a"));
		scrollInnerScrollBar(webelementFrame);
		
		click(By.xpath("//div[@id='resource_table_wrapper']/div[2]/div[2]/div/ul/li[9]/a"));
		thread();
		
		if(rVal==pgCnt%100)
		{
			break outerforloop;
		}
		if(rVal==1)
		{		
		rVal=pgCnt;
		}
		thread();
		}
	    }
   	    
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	
	
	/* @Test(priority=6)
	  void logout() throws InterruptedException
	  {
		ll.logoutSession();
		thread();
		closeWindow();
	  }*/
	


}
