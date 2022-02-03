package Scripts;


import org.openqa.selenium.By;
import org.testng.annotations.Test;


import UIWrappers.Page;
import jxl.read.biff.BiffException;

import static UIWrappers.UIObjects.*;

import java.io.IOException;
import java.util.NoSuchElementException;

import static ObjectRepository.Locations.*;
import static ObjectRepository.Threats.*;
import static ObjectRepository.Employees.*;
import static ObjectRepository.Teams.*;
import static ObjectRepository.Assets.*;
import static ObjectRepository.AssetGroups.*;
import static ObjectRepository.Contacts.*;
import static ObjectRepository.ContactGroups.*;
import static ObjectRepository.Insurance.*;
import static ObjectRepository.Tasks.*;
import static ObjectRepository.TaskGroups.*;
import static ObjectRepository.BusinessFunctions.*;

/******************************************************************************************
 * 
 * @author Sellamuthu
 * 
 *  Test 1: Check the list view count and add data's in all modules
 * 
*******************************************************************************************/

public class AddingData extends Page {
	
	LoginLogout ll = new LoginLogout();
	Loc_AddDeleteLocation loc_AddDeleteLocation = new Loc_AddDeleteLocation();
	Thr_AddndDelete thr_AddndDelete = new Thr_AddndDelete();
	Emp_AddEdit emp_AddEdit = new Emp_AddEdit();
	Tms_AddNewTeam tms_AddNewTeam = new Tms_AddNewTeam();
	Ast_AddNewAssets ast_AddNewAssets = new Ast_AddNewAssets();
	AstGrp_AddEdit astGrp_AddEdit = new AstGrp_AddEdit();
	Cnt_AddNewContact cnt_AddNewContact = new Cnt_AddNewContact();
	CntGrp_AddEdit cntGrp_AddEdit = new CntGrp_AddEdit();
	Ins_AddEdit ins_AddEdit = new Ins_AddEdit();
	Tsk_AddEdit tsk_AddEdit = new Tsk_AddEdit();
	TskGrp_AddEdit tskGrp_AddEdit = new TskGrp_AddEdit();
	BFunc_AddEdit bFunc_AddEdit = new BFunc_AddEdit();
	Loc_AddnDeleteMultiple loc_AddnDeleteMultiple = new Loc_AddnDeleteMultiple();
	
	/*@Test(priority=0)
	void login() throws BiffException, IOException, InterruptedException
	{
		ll.loginToSIB();
		waitForPageLoad();
	}*/
	
	@Test(priority=1)
	void locations() throws BiffException, IOException, InterruptedException
	{
		click(locationsInMainMenu);
		waitForPageLoad();
		driver.findElement(By.id("cometchat_hide")).click();
		thread();
		
		try
		{			
			getTotalValuesIndd(locLocationsLstViewTtl);
			getObjectText(locLocationsLstViewTtl);			
			if((totalDDValCount == 1) && (getActualObjectTxt.equals("No Records Found")))
			{
				for(int i=0; i<3; i++) 
				{
					click(locOvrAddBtn);
					loc_AddDeleteLocation.addNewLocation();
				}
			}
			else if(totalDDValCount == 1)
			{
				for(int i=0; i<2; i++)
				{
					click(locOvrAddBtn);
					loc_AddDeleteLocation.addNewLocation();
				}					
			}
			else if(totalDDValCount == 2)
			{
				for(int i=0; i<1; i++)
				{
					click(locOvrAddBtn);
					loc_AddDeleteLocation.addNewLocation();
				}					
			}
			else if(totalDDValCount >= 3)
			{
			//	Log File	
			}
			else 
			{
				verifyObjDisplay(locLocationsLstViewTtl);
				if(chkObjDisplay == false)
				{
					System.out.println("***** 'No Records Found' message is not displaying in Locations list view *****"); 
				}
			}
		}catch(NoSuchElementException nse) 
		{
			nse.printStackTrace();			
		}	
		thread();
	}
	
	@Test(priority=2)
	void threats() throws BiffException, InterruptedException, IOException
	{
		click(threatsInMainMenu);
		waitForPageLoad();
		
		try
		{			
			getTotalValuesIndd(thrThreatsListViewCnt);
			getObjectText(thrThreatsListViewCnt);
			if((totalDDValCount == 1) && (getActualObjectTxt.equals("No Records Found")))
			{
				for(int i=0; i<3; i++) 
				{
					click(thrOvrAddBtn);
					thr_AddndDelete.addNewThreatName();
				}
			}
			else if(totalDDValCount == 1)
			{
				for(int i=0; i<2; i++)
				{
					click(thrOvrAddBtn);
					thr_AddndDelete.addNewThreatName();
				}					
			}
			else if(totalDDValCount == 2)
			{
				for(int i=0; i<1; i++)
				{
					click(thrOvrAddBtn);
					thr_AddndDelete.addNewThreatName();
				}					
			}
			else if(totalDDValCount >= 3)
			{
			//	Log File	
			}
			else 
			{
				verifyObjDisplay(thrThreatsListViewCnt);
				if(chkObjDisplay == false)
				{
					System.out.println("***** 'No Records Found' message is not displaying in Threats list view *****"); 
				}
			}
		}catch(NoSuchElementException nse) 
		{
			nse.printStackTrace();			
		}	
		thread();
	}
	
	@Test(priority=3)
	void employees() throws BiffException, InterruptedException, IOException
	{
		click(employeesInMainMenu);
		waitForPageLoad();
		
		try
		{			
			getTotalValuesIndd(empEmployeesListViewTtl);
			getObjectText(empEmployeesListViewTtl);			
			if((totalDDValCount == 1) && (getActualObjectTxt.equals("No Records Found")))
			{
				for(int i=0; i<3; i++) 
				{
					click(empOvrAddBtn);
					emp_AddEdit.addEmployeeDetails();
				}
			}
			else if(totalDDValCount == 1)
			{
				for(int i=0; i<2; i++)
				{
					click(empOvrAddBtn);
					emp_AddEdit.addEmployeeDetails();
				}					
			}
			else if(totalDDValCount == 2)
			{
				for(int i=0; i<1; i++)
				{
					click(empOvrAddBtn);
					emp_AddEdit.addEmployeeDetails();
				}					
			}
			else if(totalDDValCount >= 3)
			{
			//	Log File	
			}
			else 
			{
				verifyObjDisplay(empEmployeesListViewTtl);
				if(chkObjDisplay == false)
				{
					System.out.println("***** 'No Records Found' message is not displaying in Employees list view *****"); 
				}
			}
		}catch(NoSuchElementException nse) 
		{
			nse.printStackTrace();			
		}	
		thread();
	}
	
	@Test(priority=4)
	void teams() throws BiffException, InterruptedException, IOException
	{
		click(teamsInMainMenu);
		waitForPageLoad();
		
		try
		{			
			getTotalValuesIndd(tmsTeamsLstViewTtl);
			getObjectText(tmsTeamsLstViewTtl);			
			if((totalDDValCount == 1) && (getActualObjectTxt.equals("No Records Found")))
			{
				for(int i=0; i<3; i++) 
				{
					click(tmsOvrAddBtn);
					tms_AddNewTeam.addNewTeamDtls();
				}
			}
			else if(totalDDValCount == 1)
			{
				for(int i=0; i<2; i++)
				{
					click(tmsOvrAddBtn);
					tms_AddNewTeam.addNewTeamDtls();
				}					
			}
			else if(totalDDValCount == 2)
			{
				for(int i=0; i<1; i++)
				{
					click(tmsOvrAddBtn);
					tms_AddNewTeam.addNewTeamDtls();
				}					
			}
			else if(totalDDValCount >= 3)
			{
			//	Log File	
			}
			else 
			{
				verifyObjDisplay(tmsTeamsLstViewTtl);
				if(chkObjDisplay == false)
				{
					System.out.println("***** 'No Records Found' message is not displaying in Teams list view *****"); 
				}
			}
		}catch(NoSuchElementException nse) 
		{
			nse.printStackTrace();			
		}		
		thread();
	}
	
	@Test(priority=5)
	void assets() throws BiffException, InterruptedException, IOException
	{
		click(assetsInMainMenu);
		waitForPageLoad();
		
		try
		{			
			getTotalValuesIndd(astAssetListViewTtl);
			getObjectText(astAssetListViewTtl);			
			if((totalDDValCount == 1) && (getActualObjectTxt.equals("No Records Found")))
			{
				for(int i=0; i<3; i++) 
				{
					click(astOvrAddBtn);
					ast_AddNewAssets.addNewAssetName();
				}
			}
			else if(totalDDValCount == 1)
			{
				for(int i=0; i<2; i++)
				{
					click(astOvrAddBtn);
					ast_AddNewAssets.addNewAssetName();
				}					
			}
			else if(totalDDValCount == 2)
			{
				for(int i=0; i<1; i++)
				{
					click(astOvrAddBtn);
					ast_AddNewAssets.addNewAssetName();
				}					
			}
			else if(totalDDValCount >= 3)
			{
			//	Log File	
			}
			else 
			{
				verifyObjDisplay(astAssetListViewTtl);
				if(chkObjDisplay == false)
				{
					System.out.println("***** 'No Records Found' message is not displaying in Assets list view *****"); 
				}
			}
		}catch(NoSuchElementException nse) 
		{
			nse.printStackTrace();			
		}		
		thread();
	}
	
	@Test(priority=6)
	void assetGroups() throws BiffException, InterruptedException, IOException
	{
		click(assetGroupsInMainMenu);
		waitForPageLoad();
		
		try
		{			
			getTotalValuesIndd(astGrpAssetGrpListViewTtl);
			getObjectText(astGrpAssetGrpListViewTtl);			
			if((totalDDValCount == 1) && (getActualObjectTxt.equals("No Records Found")))
			{
				for(int i=0; i<3; i++) 
				{
					click(astGrpOvrAddBtn);
					astGrp_AddEdit.addAssetGroup();
				}
			}
			else if(totalDDValCount == 1)
			{
				for(int i=0; i<2; i++)
				{
					click(astGrpOvrAddBtn);
					astGrp_AddEdit.addAssetGroup();
				}					
			}
			else if(totalDDValCount == 2)
			{
				for(int i=0; i<1; i++)
				{
					click(astGrpOvrAddBtn);
					astGrp_AddEdit.addAssetGroup();
				}					
			}
			else if(totalDDValCount >= 3)
			{
			//	Log File	
			}
			else 
			{
				verifyObjDisplay(astGrpAssetGrpListViewTtl);
				if(chkObjDisplay == false)
				{
					System.out.println("***** 'No Records Found' message is not displaying in Asset Groups list view *****"); 
				}
			}
		}catch(NoSuchElementException nse) 
		{
			nse.printStackTrace();			
		}
		thread();
	}
	
	@Test(priority=7)
	void contacts() throws BiffException, InterruptedException, IOException
	{
		click(contactsInMainMenu);
		waitForPageLoad();
		
		try
		{			
			getTotalValuesIndd(cntContactsListViewTtl);
			getObjectText(cntContactsListViewTtl);			
			if((totalDDValCount == 1) && (getActualObjectTxt.equals("No Records Found")))
			{
				for(int i=0; i<3; i++) 
				{
					click(cntOvrAddBtn);
					cnt_AddNewContact.addContactDetails();
				}
			}
			else if(totalDDValCount == 1)
			{
				for(int i=0; i<2; i++)
				{
					click(cntOvrAddBtn);
					cnt_AddNewContact.addContactDetails();
				}					
			}
			else if(totalDDValCount == 2)
			{
				for(int i=0; i<1; i++)
				{
					click(cntOvrAddBtn);
					cnt_AddNewContact.addContactDetails();
				}					
			}
			else if(totalDDValCount >= 3)
			{
			//	Log File	
			}
			else 
			{
				verifyObjDisplay(cntContactsListViewTtl);
				if(chkObjDisplay == false)
				{
					System.out.println("***** 'No Records Found' message is not displaying in Contacts list view *****"); 
				}
			}
		}catch(NoSuchElementException nse) 
		{
			nse.printStackTrace();			
		}		
		thread();
	}
	
	@Test(priority=8)
	void contactGroups() throws BiffException, InterruptedException, IOException
	{
		click(contactGroupsInMainMenu);
		waitForPageLoad();
		
		try
		{			
			getTotalValuesIndd(cntGrpContactGrpNameTtl);
			getObjectText(cntGrpContactGrpNameTtl);			
			if((totalDDValCount == 1) && (getActualObjectTxt.equals("No Records Found")))
			{
				for(int i=0; i<3; i++) 
				{
					click(cntGrpOvrAddBtn);
					cntGrp_AddEdit.addNewContactGroup();
				}
			}
			else if(totalDDValCount == 1)
			{
				for(int i=0; i<2; i++)
				{
					click(cntGrpOvrAddBtn);
					cntGrp_AddEdit.addNewContactGroup();
				}					
			}
			else if(totalDDValCount == 2)
			{
				for(int i=0; i<1; i++)
				{
					click(cntGrpOvrAddBtn);
					cntGrp_AddEdit.addNewContactGroup();
				}					
			}
			else if(totalDDValCount >= 3)
			{
			//	Log File	
			}
			else 
			{
				verifyObjDisplay(cntGrpContactGrpNameTtl);
				if(chkObjDisplay == false)
				{
					System.out.println("***** 'No Records Found' message is not displaying in Contact Groups list view *****"); 
				}
			}
		}catch(NoSuchElementException nse) 
		{
			nse.printStackTrace();			
		}	
		thread();
	}
	
	@Test(priority=9)
	void insurance() throws BiffException, InterruptedException, IOException
	{
		click(insuranceInMainMenu);
		waitForPageLoad();
		
		try
		{			
			getTotalValuesIndd(insInsuranceListViewTtl);
			getObjectText(insInsuranceListViewTtl);			
			if((totalDDValCount == 1) && (getActualObjectTxt.equals("No Records Found")))
			{
				for(int i=0; i<3; i++) 
				{
					click(insOvrAddBtn);
					ins_AddEdit.addInsuranceName();
				}
			}
			else if(totalDDValCount == 1)
			{
				for(int i=0; i<2; i++)
				{
					click(insOvrAddBtn);
					ins_AddEdit.addInsuranceName();
				}					
			}
			else if(totalDDValCount == 2)
			{
				for(int i=0; i<1; i++)
				{
					click(insOvrAddBtn);
					ins_AddEdit.addInsuranceName();
				}					
			}
			else if(totalDDValCount >= 3)
			{
			//	Log File	
			}
			else 
			{
				verifyObjDisplay(insInsuranceListViewTtl);
				if(chkObjDisplay == false)
				{
					System.out.println("***** 'No Records Found' message is not displaying in Insurance list view *****"); 
				}
			}
		}catch(NoSuchElementException nse) 
		{
			nse.printStackTrace();			
		}	
		thread();
	}
	
	@Test(priority=10)
	void tasks() throws BiffException, InterruptedException, IOException
	{
		click(tasksInMainMenu);
		waitForPageLoad();
		
		try
		{			
			getTotalValuesIndd(tsktaskNameLstViewTtl);
			getObjectText(tsktaskNameLstViewTtl);			
			if((totalDDValCount == 1) && (getActualObjectTxt.equals("No Records Found")))
			{
				for(int i=0; i<3; i++) 
				{
					click(tskOvrAddBtn);
					tsk_AddEdit.addNewTaskName();
				}
			}
			else if(totalDDValCount == 1)
			{
				for(int i=0; i<2; i++)
				{
					click(tskOvrAddBtn);
					tsk_AddEdit.addNewTaskName();
				}					
			}
			else if(totalDDValCount == 2)
			{
				for(int i=0; i<1; i++)
				{
					click(tskOvrAddBtn);
					tsk_AddEdit.addNewTaskName();
				}					
			}
			else if(totalDDValCount >= 3)
			{
			//	Log File	
			}
			else 
			{
				verifyObjDisplay(tsktaskNameLstViewTtl);
				if(chkObjDisplay == false)
				{
					System.out.println("***** 'No Records Found' message is not displaying in Tasks list view *****"); 
				}
			}
		}catch(NoSuchElementException nse) 
		{
			nse.printStackTrace();			
		}	
		thread();
	}
	
	@Test(priority=11)
	void taskGroups() throws BiffException, InterruptedException, IOException
	{
		click(taskGroupsInMainMenu);
		waitForPageLoad();
		
		try
		{			
			getTotalValuesIndd(tskGrpTaskGroupNameTtl);
			getObjectText(tskGrpTaskGroupNameTtl);			
			if((totalDDValCount == 1) && (getActualObjectTxt.equals("No Records Found")))
			{
				for(int i=0; i<3; i++) 
				{
					click(tskGrpOvrAddBtn);
					tskGrp_AddEdit.addNewTaskGroup();
				}
			}
			else if(totalDDValCount == 1)
			{
				for(int i=0; i<2; i++)
				{
					click(tskGrpOvrAddBtn);
					tskGrp_AddEdit.addNewTaskGroup();
				}					
			}
			else if(totalDDValCount == 2)
			{
				for(int i=0; i<1; i++)
				{
					click(tskGrpOvrAddBtn);
					tskGrp_AddEdit.addNewTaskGroup();
				}					
			}
			else if(totalDDValCount >= 3)
			{
			//	Log File	
			}
			else 
			{
				verifyObjDisplay(tskGrpTaskGroupNameTtl);
				if(chkObjDisplay == false)
				{
					System.out.println("***** 'No Records Found' message is not displaying in Task Groups list view *****"); 
				}
			}
		}catch(NoSuchElementException nse) 
		{
			nse.printStackTrace();			
		}		
		thread();
	}
	
	@Test(priority=12)
	void businessFunctions() throws BiffException, InterruptedException, IOException
	{
		click(businessFunctionsInMainMenu);
		waitForPageLoad();
		
		try
		{			
			getTotalValuesIndd(bsfBusinesFunctionsLstViewTtl);
			getObjectText(bsfBusinesFunctionsLstViewTtl);			
			if((totalDDValCount == 1) && (getActualObjectTxt.equals("No Records Found")))
			{
				for(int i=0; i<3; i++) 
				{
					click(bsfOvrAddBtn);
					bFunc_AddEdit.addNewBusinessFunctionName();
					
				}
			}
			else if(totalDDValCount == 1)
			{
				for(int i=0; i<2; i++)
				{
					click(bsfOvrAddBtn);
					bFunc_AddEdit.addNewBusinessFunctionName();
				}					
			}
			else if(totalDDValCount == 2)
			{
				for(int i=0; i<1; i++)
				{
					click(bsfOvrAddBtn);
					bFunc_AddEdit.addNewBusinessFunctionName();
				}					
			}
			else if(totalDDValCount >= 3)
			{
			//	Log File	
			}
			else 
			{
				verifyObjDisplay(bsfBusinesFunctionsLstViewTtl);
				if(chkObjDisplay == false)
				{
					System.out.println("***** 'No Records Found' message is not displaying in Business Functions list view *****"); 
				}
			}
		}catch(NoSuchElementException nse) 
		{
			nse.printStackTrace();			
		}	
		thread();
	}
	
	/*@Test(priority=13)
	void logout() throws InterruptedException
	{
		ll.logoutSession();
		thread();
		closeWindow();
	}*/
}
