package Scripts;


import java.io.IOException;
import java.util.Random;

import javax.swing.Action;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import static Config.TakScreenshot.takeScreenshot;
import static ObjectRepository.Admin.*;
import static ObjectRepository.DRPlan.drscEdtrCommentsField;
import static ObjectRepository.Home.*;
import static ObjectRepository.Locations.*;
import static ObjectRepository.Threats.*;

import UIWrappers.Page;
import static UIWrappers.UIObjects.*;
import jxl.read.biff.BiffException;

public class Hme_ActiveTasks extends Page
{
	LoginLogout ll = new LoginLogout();
	String edtTskDur,edtTskCmnt,comTaskNme;
	static String disName;
	Hme_AddUnplannedTask unplnTsk=new Hme_AddUnplannedTask();
		
/*	@Test(priority=0)
		void login() throws BiffException, IOException, InterruptedException 
		{
			ll.loginToSIB();
		} */
		
		@Test(priority=1)
		static
		void selectLocation() throws InterruptedException
		{
			Thread.sleep(1000);
			click(affectedLocationDD);
			getTotalValuesIndd(affectedLocationTtlDDCount);
		// for(int i=2;i<totalDDValCount;i++)
		//	{
			Random rand=new Random();
			int rn=rand.nextInt(totalDDValCount-1)+1;
			String rloc=driver.findElement(By.xpath("//select[@id='ddlAffectedLocation']/option["+rn+"]")).getText();
			//driver.findElement(By.xpath("//select[@id='ddlAffectedLocation']/option["+rn+"]")).click();
			enterText(affectedLocationDD,rloc);
			enterKeyInKyBord(affectedLocationDD);
			Thread.sleep(1000);
			//}
		}
		
		@Test(priority=2)
		static void selectDisaster() throws InterruptedException
		{
			getTotalValuesIndd(totalActiveDisastersCount);
			Random rand=new Random();
			int rn=rand.nextInt(totalDDValCount-1)+1;
			driver.findElement(By.xpath("//div[@id='content']/div[2]/div[1]/div[2]/div[2]/div[1]/ul/li["+rn+"]")).click();
			thread();
			disName=driver.findElement(By.xpath("//div[@id='content']/div[2]/div[1]/div[2]/div[2]/div[1]/ul/li["+rn+"]")).getText();
		    Thread.sleep(1000);
		}
		
		@Test(priority=3)
		void startTask() throws InterruptedException, BiffException, IOException
		{
			try
			{
			getTotalValuesIndd(yetToStartTasks);
			Thread.sleep(1000);
			if(totalDDValCount==0)
			{
				unplnTsk.unplanTaskPageTitle();
				unplnTsk.addUnplannedTask();
				unplnTsk.setStartDateNTime();
				unplnTsk.startTime();
				unplnTsk.selectEmployee();
				unplnTsk.saveTask();
			}
			getTotalValuesIndd(yetToStartTasks);
			
			Random rand=new Random();
			int rn=rand.nextInt(totalDDValCount);
			if(rn==0)
			{
				rn=rn+1;
			}
			System.out.println("Start task"+rn);
			webElement(activitiesSection);
			scrollInnerScrollBar(webelementFrame);
			thread();
			driver.findElement(By.xpath("//div[@id='tabitemOpenTask']/div["+rn+"]/div[4]/div/span/a")).click();
			thread();			
			//click(progressBar);
			WebElement slideBar=driver.findElement(By.xpath(".//*[@id='tabitemOpenTask']/div["+rn+"]/div[4]/div/span/span/span[2]/span/span[1]/input"));
			
			//String val=driver.findElement(By.xpath(".//*[@id='tabitemOpenTask']/div["+rn+"]/div[4]/div/a/span[2]/span[2]/span/label/span/span")).getText();
			//int sp=Integer.parseInt(val);	
			Thread.sleep(1000);
			Random r1=new Random();
			int rs=r1.nextInt(45);
			for(int i=1;i<=rs;i++)
			{					
				slideBar.sendKeys(Keys.ARROW_RIGHT);				
			}
			
			
			driver.findElement(By.xpath(".//*[@id='tabitemOpenTask']/div["+rn+"]/div[4]/div/span/span/span[2]/span/span[2]/span[2]")).click();
			thread();
			
			
			/* 
			  enterText(progressBar,Integer.toString(rn1));			 
			click(progressBarStartBtn);
			 click(strtBtnInProgressBar);
		      thread();  */
			
			getObjectText(msgNotificationBar);
			verifyAssertEquals("The task has been added to your active tasks.",getActualObjectTxt);
			thread();
			}
			catch(Exception e)
			{
				System.out.println(e);			
				//reassignTask();
			}
		}
		
		
		@Test(priority=4)
		void viewTask()
		{
			try
			{
				getTotalValuesIndd(totalActiveTasks);
				thread();
				Random rand=new Random();
				
				int rn=rand.nextInt(totalDDValCount);
				if(rn==0)
				{
					rn=rn+1;
				}
				
				WebElement wTName=driver.findElement(By.xpath(".//*[@id='divActiveTasks']/div[2]/div[1]/div["+rn+"]/div[1]/div[1]/b"));
				String tName=wTName.getText();
				
				
				driver.findElement(By.xpath("//div[@id='divActiveTasks']/div[2]/div[1]/div["+rn+"]/div[3]/div/a[1]")).click();
				thread();
				
				getObjectText(viewTskName);
				thread();
				
				verifyAssertEquals(tName,getActualObjectTxt);
				
				driver.findElement(By.xpath("//div[@id='divViewActiveTask']/div[3]/button")).click();
				Thread.sleep(1000);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			
			
		}
		
		
		@Test(priority=5)
		void reassignTask() throws InterruptedException
		{
			try
			{
				webElement(activitiesSection);
				scrollInnerScrollBar(webelementFrame);
				thread();
			getTotalValuesIndd(totalActiveTasks);
			thread();
			Random rand=new Random();
			
			int rn=rand.nextInt(totalDDValCount);
			if(rn==0)
			{
				rn=rn+1;
			}
			
			driver.findElement(By.xpath("//div[@id='divActiveTasks']/div[2]/div[1]/div["+rn+"]/div[3]/div/a[2]")).click();
			thread();
			click(asignReasignEmpDropDown);
			getTotalValuesIndd(assignReassignNewEmpDDTtlVal);
			Random rand1=new Random();
			int ren=rand1.nextInt(totalDDValCount-2)+2;
						
			//String remp=driver.findElement(By.xpath("//div[@id='ddlActiveTaskEmployee_chzn']/div/ul/li["+ren+"]")).getText();
			String remp=driver.findElement(By.xpath("//li[@id='ddlActiveTaskEmployee_chzn_o_"+ren+"']")).getText();
			
			enterText(assignReassignSearchBox,remp);
			thread();
			enterKeyInKyBord(assignReassignSearchBox);
			thread();
			
			click(reassignSaveBtn);
			thread();
						
			try
			{
			verifyAssert(deleteConfPopup);
			int i;
			getObjectText(deleteConfPpMessage);
			verifyAssertEquals("Are you sure want to remove alternate employee of this task ?", getActualObjectTxt);
			click(delConfOkBtn);
			thread();
			}
					
			catch(Exception e)
			{
				e.printStackTrace();
			}
			
			
			getObjectText(msgNotificationBar);
			thread();
			verifyAssertEquals("Task updated successfully",getActualObjectTxt);
			Thread.sleep(1000);
			}
		catch(Exception e)
		    {
			//reassignTask();
				System.out.println(e);				
			}
		}
		
		@Test(priority=6)
		void editActiveTask() throws InterruptedException, BiffException, IOException
		{
			try
			{
			//selectLocation();
			//selectDisaster();
			//click(homeInMainMenu);
				webElement(activitiesSection);
				scrollInnerScrollBar(webelementFrame);
				thread();
			getTotalValuesIndd(totalActiveTasks);
			thread();
			getUnplannedTaskSheetFromExcel();
			Random rand=new Random();
		
			int rn=rand.nextInt(totalDDValCount);
			if(rn==0)
			{
				rn=rn+1;
			}
			
	
			
			driver.findElement(By.xpath("//div[@id='divActiveTasks']/div[2]/div[1]/div["+rn+"]/div[3]/div/a[3]")).click();
			thread();
						
			Random rand1=new Random();
			int rrow=rand1.nextInt(unplanTsk.getRows()-1)+1;
			
			edtTskDur=unplanTsk.getCell(2, rrow).getContents().trim();		
			clear(hmeEditTaskDur);
			thread();
			enterText(hmeEditTaskDur,edtTskDur);
			thread();
			
			WebElement editFrame=driver.findElement(By.xpath("//form[@id='frmEditActiveTask']/div[2]/div/div/div/div/iframe"));
			edtTskCmnt = unplanTsk.getCell(3, rrow).getContents().trim();
			
			switchToWEFrame(editFrame);
			useKeyFuncToRmvVal(drscEdtrCommentsField);
			enterText(drscEdtrCommentsField, edtTskCmnt);
			switchBackFromFrame();			
			takeScreenshot();
			thread();
			
			click(hmeSaveEditTask);
			thread();
			getObjectText(msgNotificationBar);
			thread();
			verifyAssertEquals("Updated Successfully",getActualObjectTxt);
			takeScreenshot();
			thread();
			}
			catch(Exception e)
			{
				e.printStackTrace();
				//click(hmeCloseEditTask);
				thread();
			}
			
		}
			
	/*	@Test(priority=7)
		void deleteActiveTask() throws InterruptedException
		{
			try
			{
			//selectLocation();
		   // selectDisaster();
			//click(homeInMainMenu);
			webElement(activitiesSection);
			scrollInnerScrollBar(webelementFrame);
			thread();
			
			getTotalValuesIndd(totalActiveTasks);
			thread();
			Random rand=new Random();
			//int rn=rand.nextInt(totalDDValCount-1)+1;
			int rn=rand.nextInt(totalDDValCount);
			if(rn==0)
			{
				rn=rn+1;
			}
			driver.findElement(By.xpath("//div[@id='divActiveTasks']/div[2]/div[1]/div["+rn+"]/div[3]/div/a[3]")).click();
			thread();
			
			click(delConfOkBtn);
			thread();
			
			getObjectText(msgNotificationBar);
			thread();
			verifyAssertEquals("Active task deleted successfully.",getActualObjectTxt);
			thread();
			}
			catch(Exception e)
			{
				e.printStackTrace();
				System.out.println("No active tasks to delete");
			}
			
		}*/
		
		@Test(priority=8)
		void updateTaskProgress() throws InterruptedException, IOException
		{
		//	try
		//	{
		//	selectLocation();
		//	selectDisaster();
			//click(homeInMainMenu);
				webElement(activitiesSection);
				scrollInnerScrollBar(webelementFrame);
				thread();
			getTotalValuesIndd(totalActiveTasks);
			thread();
			webElement(activitiesSection);
			scrollInnerScrollBar(webelementFrame);
			Random rand=new Random();
			
			int rn=rand.nextInt(totalDDValCount);
			if(rn==0)
			{
				rn=rn+1;
			}
			
			
			driver.findElement(By.xpath("//div[@id='divActiveTasks']/div[2]/div[1]/div["+rn+"]/div[3]/div/a[5]/span[1]")).click();
			thread();
			Thread.sleep(1000);
			
			WebElement completeBar=driver.findElement(By.xpath(".//*[@id='divActiveTasks']/div[2]/div[1]/div["+rn+"]/div[3]/div/a[5]/span[2]/span[2]/span/span[1]/input")); 
			completeBar.click();
			thread();
			
			String progress=driver.findElement(By.xpath("//div[@id='divActiveTasks']/div[2]/div[1]/div["+rn+"]/div[3]/div/a[5]/span[2]/span[2]/span/label/span/span")).getText();
			int percent=Integer.parseInt(progress);
			
			Random r1=new Random();
			int rv=r1.nextInt(99-45)+45;
			
			for(int i=percent;i<=rv;i++)
			{
				completeBar.sendKeys(Keys.ARROW_RIGHT);
			}
			thread();
			
			
			driver.findElement(By.xpath(".//*[@id='divActiveTasks']/div[2]/div[1]/div["+rn+"]/div[3]/div/a[5]/span[2]/span[2]/span/span[2]/span")).click();
			thread();
			
			String progress1=driver.findElement(By.xpath(".//*[@id='divActiveTasks']/div[2]/div[1]/div["+rn+"]/div[3]/div/a[5]/span[2]/span[2]/span/label/span/span")).getText();
			
			getObjectText(msgNotificationBar);
			thread();
			
			if(getActualObjectTxt.contains("Task updated"))
			{
			verifyAssertEquals("Task updated "+(rv+1)+"% successfully",getActualObjectTxt);
			takeScreenshot();
			}
		//	}
		//	catch(Exception e)
		//	{
		//		e.printStackTrace();				
		//	}
		}
		
		@Test(priority=9)
		void affectedBusinessFunctions() throws InterruptedException
		{
			try
			{
			webElement(hmeBsfSectionHeader);
			scrollInnerScrollBar(webelementFrame);
			
			/*
			 // To click random business function
			  
			getTotalValuesIndd(hmeBsfTableTtlVal);
			System.out.println("total val count"+totalDDValCount);
			Random rand=new Random();
			int rn=rand.nextInt(totalDDValCount-1)+1;
			System.out.println("rand num"+rn);
			if(rn%2==0)
			{
				driver.findElement(By.xpath("//table[@id='ProcessRecovery']/tbody/tr["+(rn-1)+"]/td[1]/b/strong/b")).click();
				thread();
				driver.findElement(By.xpath("//table[@id='ProcessRecovery']/tbody/tr["+(rn-1)+"]/td[1]/b/strong/b")).click();
			}
			else
			{
				driver.findElement(By.xpath("//table[@id='ProcessRecovery']/tbody/tr["+rn+"]/td[1]/b/strong/b")).click();
				thread();
				driver.findElement(By.xpath("//table[@id='ProcessRecovery']/tbody/tr["+rn+"]/td[1]/b/strong/b")).click();
			}*/
			
			// To click all the business functions
			
			getTotalValuesIndd(hmeBsfNTasksCount);
			thread();
		//	System.out.println("total val count"+totalDDValCount);
			for(int i=1,j=1;i<=totalDDValCount;i++,j=j+2)
			{
			//	System.out.println(i+" ------- "+driver.findElement(By.xpath("//table[@id='ProcessRecovery']/tbody/tr["+j+"]/td[1]/b/strong/b")).getText());
				driver.findElement(By.xpath("//table[@id='ProcessRecovery']/tbody/tr["+j+"]/td[1]/b/strong/b")).click();
				Thread.sleep(4000);
				driver.findElement(By.xpath("//table[@id='ProcessRecovery']/tbody/tr["+j+"]/td[1]/b/strong/b")).click();
			}
			}
			catch(Exception e)
			{
				e.printStackTrace();				
			}
		}
		
		@Test(priority=10)
		void completeTask() throws InterruptedException
		{
			try
			{
		//	selectLocation();
		//	selectDisaster();
			//click(homeInMainMenu);
				webElement(activitiesSection);
				scrollInnerScrollBar(webelementFrame);
				thread();
			getTotalValuesIndd(totalActiveTasks);
			thread();
			webElement(activitiesSection);
			scrollInnerScrollBar(webelementFrame);
			Random rand=new Random();
			
			int rn=rand.nextInt(totalDDValCount);
			if(rn==0)
			{
				rn=rn+1;
			}
			
			comTaskNme=driver.findElement(By.xpath("//div[@id='divActiveTasks']/div[2]/div[1]/div["+rn+"]/div[1]/div[1]/b")).getText();
			thread();
			
			driver.findElement(By.xpath("//div[@id='divActiveTasks']/div[2]/div[1]/div["+rn+"]/div[3]/div/a[5]/span[1]")).click();
			thread();
			Thread.sleep(1000);
			
			//click(activeTaskProgressBar);
			//System.out.println("progress val "+driver.findElement(By.xpath("//div[@id='divActiveTasks']/div[2]/div[1]/div["+rn+"]/div[3]/div/a[4]/span[2]/span[2]/span/label/span/span")).getText());
			//click(activeTskProgressBarUpdtBtn);
		
			WebElement completeBar=driver.findElement(By.xpath("//div[@id='divActiveTasks']/div[2]/div[1]/div["+rn+"]/div[3]/div/a[5]/span[2]/span[2]/span/span[1]/input"));
			completeBar.click();                                   
			thread();
			String progress=driver.findElement(By.xpath(".//*[@id='divActiveTasks']/div[2]/div[1]/div["+rn+"]/div[3]/div/a[5]/span[2]/span[2]/span/label/span/span")).getText();
						
			int percent=Integer.parseInt(progress);
						
			for(int i=percent;i<=100;i++ )
			{
				completeBar.sendKeys(Keys.ARROW_RIGHT);
			}
			thread();
			
			driver.findElement(By.xpath(".//*[@id='divActiveTasks']/div[2]/div[1]/div["+rn+"]/div[3]/div/a[5]/span[2]/span[2]/span/span[2]/span")).click();
			thread();
			
						
			getObjectText(msgNotificationBar);
			thread();
			verifyAssertEquals("The task has been completed successfully",getActualObjectTxt);
			
		/*	if(getActualObjectTxt.contains("The task has been completed successfully"))
			{
			//verifyAssertEquals("Task updated % successfully",getActualObjectTxt);
				System.out.println("task is completed");
				takeScreenshot();
			}*/
			}
			catch(Exception e)
			{
				e.printStackTrace();				
			}
		}
		
		@Test(priority=11)
		void verifyCompletedTaskName() throws InterruptedException
		{
			webElement(activitiesSection);
			scrollInnerScrollBar(webelementFrame);
			thread();
			click(completedTab);
			thread();
			getTotalValuesIndd(completedTaskCount);
			String lastComTask=driver.findElement(By.xpath("//div[@id='tabitemCompletedTask']/div["+totalDDValCount+"]/div[2]/span[1]/b")).getText();
			webElement(By.xpath("//div[@id='tabitemCompletedTask']/div["+totalDDValCount+"]/div[2]/span[1]/b"));
			scrollInnerScrollBar(webelementFrame);
			thread();
			verifyAssertEquals(comTaskNme,lastComTask);
			
		}
		
		

}
