package Scripts;

import java.io.IOException;
import java.util.Random;

import static Config.TakScreenshot.takeScreenshot;
import static ObjectRepository.Home.*;
import static ObjectRepository.Threats.*;
import static ObjectRepository.Incidents.*;
import static ObjectRepository.Locations.*;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import UIWrappers.Page;
import static UIWrappers.UIObjects.*;
import jxl.read.biff.BiffException;

public class Hme_AddUnplannedTask extends Page
{
LoginLogout ll = new LoginLogout();
String tskNme,tskId,tskDur,tskCmnt,year,edttskDur,edttskCmnt;
	
/*	@Test(priority=0)
	void login() throws BiffException, IOException, InterruptedException 
	{
		ll.loginToSIB();
	}  */
	
	@Test(priority=1)
	void unplanTaskPageTitle() throws InterruptedException, BiffException, IOException
	{
		Thread.sleep(1000);
		Hme_ActiveTasks.selectLocation();
		Hme_ActiveTasks.selectDisaster();
		click(unplanTskBtn);        
		Thread.sleep(1000);
		getObjectText(unplanTskPageTitleTxt);
		verifyAssertEquals("Add Unplanned Task",getActualObjectTxt);
		Thread.sleep(1000);
	}
		
	@Test(priority=2)
	void addUnplannedTask() throws InterruptedException, BiffException, IOException
	{
		getUnplannedTaskSheetFromExcel();
		Thread.sleep(1000);
		Random rand=new Random();
		int rrow=rand.nextInt(unplanTsk.getRows()-1)+1;
		Thread.sleep(1000);
		tskNme=unplanTsk.getCell(0, rrow).getContents();
		tskId=unplanTsk.getCell(1, rrow).getContents();
		tskDur=unplanTsk.getCell(2, rrow).getContents();
		tskCmnt=unplanTsk.getCell(3, rrow).getContents();		
		
		enterText(unplanTskNme,tskNme);
		thread();
		
		Random rand1=new Random();
		int rId=rand1.nextInt(1000-99)+99;
		enterText(unplanTskId,tskId+rId);
		thread();
		enterText(unplanTskDur,tskDur);
		thread();
		//switchToWEFrame(driver.findElement(unplanTskIFrame));
		WebElement ifram = driver.findElement(scEdtrFrame);
		driver.switchTo().frame(ifram);
		thread();  
		//enterText(unplanTskCmnt,tskCmnt);
		enterText(scEdtrCommentsField,tskCmnt);
		thread();
		switchBackFromFrame();
		click(unplanTskType);
		thread();
		
		getTotalValuesIndd(unplanTskTypeTtlVal);
		thread();
		thread();
		Random rt=new Random();
		int rtn=rt.nextInt(totalDDValCount-1)+1;
		String rtv=driver.findElement(By.xpath("//li[@id='txtTaskType_AEAT_chzn_o_"+rtn+"']")).getText();
		
		enterText(unplanTskTypeTxtBox,rtv);
		enterKeyInKyBord(unplanTskTypeTxtBox);
		Thread.sleep(1000);
		
	}
	
	@Test(priority=3)
	void setStartDateNTime() throws InterruptedException
	{
		
		click(unplanTskStrtDt);
		thread();
		//click(incCalndrRightSideArrow);
		//click(unplanTskDtRgtArow);
		driver.findElement(By.xpath("html/body/div[26]/div[1]/table/thead/tr[1]/th[3]")).click();
		thread();
		Thread.sleep(4000);
		/* click(incDatePickrDaysCalndr);
		   thread();
		
		   click(incDatePickrMonthsCalndr);
		   thread();
		
		//click(incDatePickrDaysCalndr);
		thread();
		
	//select random year
		getTotalValuesIndd(incGetTotalYearsCnt);			
		Random ranYear = new Random();
		int ryear = ranYear.nextInt(totalDDValCount-1)+1;		
		year = driver.findElement(By.xpath("//div[@class='datepicker-years']//tbody/tr/td/span["+ryear+"]")).getText();
		System.out.println(year);
		driver.findElement(By.xpath("//span[contains(text(),"+year+")]")).click();
		thread();
		
		//		Select Month
		getTotalValuesIndd(incGetTotalMonths);
		Random ranMonth = new Random();			
		int rmonth = ranMonth.nextInt(totalDDValCount-1)+1;		
		System.out.println(rmonth);
		WebElement ranMonthNm = driver.findElement(By.xpath("//div[@class='datepicker-months']//tbody/tr/td/span["+rmonth+"]"));
		System.out.println(driver.findElement(By.xpath("//div[@class='datepicker-months']//tbody/tr/td/span["+rmonth+"]")).getText());
		ranMonthNm.click();
		thread();
		*/
		
	
	
		//selecting random row n col in a month
	    getTotalValuesIndd(incGetRandomDateRowCnt);
		Random rDateRow = new Random();
		int rDateR = rDateRow.nextInt(totalDDValCount-1)+1;		
					
		Random rDateCol = new Random();
		int rDateC = rDateCol.nextInt(7-1)+1;
					
		WebElement rDate = driver.findElement(By.xpath("//div[@class='datepicker-days']//tbody/tr["+rDateR+"]/td["+rDateC+"]"));
		String Date = rDate.getText();		 
		rDate.click();
		Thread.sleep(1000);
		
	}
	
	@Test(priority=4)
	void startTime() throws InterruptedException
	{	
		//select Time
		Thread.sleep(1000);
		click(unplanTskStrtTime);
		thread();
		
		getTotalValuesIndd(unplanTskTimeHrs);
		Random rand=new Random();
		int rhn=rand.nextInt(totalDDValCount-1)+1;
		driver.findElement(By.xpath("html/body/div[24]/ul/li[1]/span["+rhn+"]")).click();
		
		getTotalValuesIndd(unplanTskTimeMin);
		Random rand1=new Random();
		int rmn=rand1.nextInt(totalDDValCount-1)+1;
		driver.findElement(By.xpath("html/body/div[24]/ul/li[3]/span["+rmn+"]")).click();
		
		click(umplanTskEndDt);
		thread();
	
	}
	
	@Test(priority=5)
	void selectEmployee() throws InterruptedException
	{
		try
		{
		click(asignEmp);
		thread();
		
		getTotalValuesIndd(asignEmpTtlVal);
		thread();
		Random re=new Random();
		int ren=re.nextInt(totalDDValCount-1)+1;		
		String rtv=driver.findElement(By.xpath("//li[@id='ddlEmployeeAssigned_AEAT_chzn_o_"+ren+"']")).getText();
		
		enterText(asignEmpTxtBox,rtv);
		enterKeyInKyBord(asignEmpTxtBox);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}thread();
	}
	
	@Test(priority=6)
	void saveTask() throws InterruptedException
	{
		try
		{
		click(unplanTskSavebtn);
		thread();
		getObjectText(msgNotificationBar);
		verifyAssertEquals("Task added successfully",getActualObjectTxt);
		thread();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}thread();
	}
	
	@Test(priority=7)
	void verifyAddedTask() throws InterruptedException
	{
		getTotalValuesIndd(yetToStartTasks);
        Thread.sleep(1000);
		String uptskname=driver.findElement(By.xpath("//div[@id='tabitemOpenTask']/div["+totalDDValCount+"]/div[2]/b")).getText();
		verifyAssertEquals(tskNme,uptskname);
	}
	
	
	
	
	
	
	

}
