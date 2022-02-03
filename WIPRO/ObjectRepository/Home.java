package ObjectRepository;

import org.openqa.selenium.By;

public class Home {
	
	/*public static String activTaskAssigneeName1 = "//div[@id='divActiveTasks']/div[2]/div/div[";
	public static String activTaskAssigneeName2 = "]/div[3]/span[2]";
	public static String activTaskDuration1 = "//div[@id='divActiveTasks']/div[2]/div/div[";
	public static String activTaskDuration2 = "]/div[3]/span";*/
	
	public static By homeInMainMenu = By.xpath("//ul[@class='nav nav-tabs nav-stacked main-menu']/li/a");
	
	public static By alertsHIcon = By.cssSelector("a[data-original-title='Alerts']");
	public static By disasterAlertHIcon = By.cssSelector("a[data-original-title='Disaster Alert']");
	public static By employeesHIcon = By.cssSelector("a[data-original-title='Employees']");
	public static By businessFunctionsHIcon = By.cssSelector("a[data-original-title='Business Functions']");
	public static By teamsHIcon = By.cssSelector("a[data-original-title='Teams']");
	public static By tasksHIcon = By.cssSelector("a[data-original-title='Tasks']");	
	
	public static By affectedLocationDD = By.id("ddlAffectedLocation");
	public static By affectedLocationTtlDDCount=By.xpath("//select[@id='ddlAffectedLocation']/option");
		
	public static By tab = By.xpath("//div[3]/div/div[2]/div/div/div[1]/div[2]/div[2]/div[1]/ul/li[3]/a");
	public static By totalActiveDisastersCount=By.xpath("//div[@id='content']/div[2]/div[1]/div[2]/div[2]/div[1]/ul/li");
	
	public static By activeDisasterNmeOnHmePge=By.xpath("//div[@id='content']/div[2]/div[1]/div[1]/div/a[2]/span");
	public static By disasterAlertNmeInSumryPge=By.xpath("//div[@id='content']//div[2]/div/div[1]//div[2]/div[1]/div[1]");
	
	//Count
	public static By alertsCount=By.xpath("//div[@id='content']/div[2]/div[1]/div[1]/div/a[1]/span");
	public static By empCount=By.xpath("//div[@id='content']/div[2]/div[1]/div[1]/div/a[3]/span");
	public static By busFnsCount=By.xpath("//div[@id='content']/div[2]/div[1]/div[1]/div/a[4]/span");
	public static By teamsCount=By.xpath("//div[@id='content']/div[2]/div[1]/div[1]/div/a[5]/span");
	public static By taskCount=By.xpath("//div[@id='content']/div[2]/div[1]/div[1]/div/a[6]/span");
		
	// TITLE BAR
	public static By appDownloadBtnInTopMnu = By.xpath("//a[contains(@href,'/app')]");
	public static By appMobileNumber =By.id("phone");
	public static By appCountryDD = By.xpath("//select[@id='country_code']/option");
	public static By appCountrySelectStatusVal= By.xpath("//select[@id='country_code']");
	public static By googleAppBtn= By.id("Gapp_type");
	public static By appStoreBtn = By.id("Aapp_type");
	public static By helpManualBtnInTopMnu = By.xpath("//a[contains(@href,'/help')]");
	public static By alertsBtnInTopMnu = By.xpath("//span[contains(text(),'Alerts')]");
	public static By userNameBtninTopMnu = By.xpath("//div[@class='navbar-inner']//div[2]/a");
	
	public static By incidentsOptnInTopMenu = By.xpath("//a[contains(@href,'/incident')]");
	
	
	//	CHAT
	public static By chtBarInBottom = By.id("cometchat_base");
	public static By chtRoomsBtnInBtmBr = By.id("cometchat_trayicon_chatrooms");
	public static By chtNowBtnInBtmBr = By.id("cometchat_userstab");
	public static By chtAnnouncementsIconInBtmBr = By.id("cometchat_trayicon_announcements");
	public static By chtChangeThemeIconInBtmBr = By.id("cometchat_trayicon_themechanger");
	public static By chtBroadCastMsgIconInBtmBr = By.id("cometchat_trayicon_broadcastmessage");
	public static By chtChatOptionsIconInBtmBr = By.id("cometchat_optionsbutton");
	
	public static By chatRoomsWin = By.id("cometchat_trayicon_chatrooms_popup");
	public static By whosOnlineWin = By.id("cometchat_userstab_popup");
	public static By announcementsWin = By.id("cometchat_trayicon_announcements_popup");
	public static By changeThemeWin = By.id("cometchat_trayicon_themechanger_popup");
	public static By broadcastMessageWin = By.id("cometchat_trayicon_broadcastmessage_popup");
	public static By chatOptionsWin = By.id("cometchat_optionsbutton_popup");
	
	public static By broadCastWininimizBtn = By.xpath("//div[@id='cometchat_trayicon_broadcastmessage_popup']/div/div[2]");
	
	//	ACTIVITIES
	public static By activitiesSection=By.xpath("//div[@id='divTaskActivities']/div[1]/h2");
	public static By yetToStartTab = By.xpath("//a[contains(@href,'#tabitemOpenTask')]"); 
	public static By completedTab = By.xpath("//a[contains(@href,'#tabitemCompletedTask')]");
	public static By yetToStartTasks=By.xpath("//div[@id='tabitemOpenTask']/div[@class='dr-task-list ng-scope']");
	public static By completedTaskCount=By.xpath("//div[@id='tabitemCompletedTask']/div[@class='dr-task-list ng-scope']");
	
	//public static By yetToStartTskName=By.xpath("//div[@id='tabitemOpenTask']/div[2]/div[2]/b");
	public static By tskStartBtn = By.linkText("Start");
	public static By progressBarStartBtn = By.xpath("//div[3]/div/div[2]/div/div/div[1]/div[2]/div[2]/div[4]/div[2]/div[2]/div/div[1]/div[1]/div[4]/div/a/span[2]/span[2]/span/span[2]/span[2]");			
	public static By progressBar = By.cssSelector("input[ng-model='aut.progress_copy'][max='99']");
	public static By strtProgressBar=By.cssSelector("input.ng-valid.ng-dirty");
	//public static By strtBtnInProgressBar=By.cssSelector("span.dashboard-btn-green.pull-right");
	public static By strtBtnInProgressBar=By.xpath("//span[@ng-click='UpdateOpenTaskProgress($index,$event);'][text()='Start']");
	
	public static By activitisTskAssigneeName = By.xpath("//div[@id='myTabContent']/div/div/div/span/b");
	public static By activitisTaskDuration = By.xpath("//div[@id='myTabContent']/div/div/div[2]/span");
	public static By activitisTaskGrpName1 = By.xpath("//div[@id='myTabContent']/div/div/div[2]/b");
	public static By activitisTaskGrpName2 = By.xpath("//div[@id='myTabContent']/div/div/div[2]/i");
	public static By hmeEditTaskDur=By.xpath("//input[@id='txtTaskDuration_EAT']");
	public static By hmeSaveEditTask=By.cssSelector("button[ng-click='SaveEditTask();']");
	public static By hmeCloseEditTask=By.xpath("//div[@id='divEditActiveTask']/div[3]/button[2]");
	public static By hmeEditComment=By.xpath("//form[@id='frmEditActiveTask']/div[2]/div/div/div/div");
	
	
	
	// unplanned Task
	
	public static By unplanTskBtn=By.xpath("//a[contains(text(),'Add Unplanned Task')]");
	public static By unplanTskPageTitleTxt=By.xpath("//h3[contains(text(),'Add Unplanned Task')]");
	public static By unplanTskNme=By.xpath("//input[@id='txtTaskName_AEAT']");
	public static By unplanTskId=By.xpath("//input[@id='txtTaskID_AEAT']");
	public static By unplanTskDur=By.xpath("//input[@id='txtTaskDuration_AEAT']");
	public static By unplanTskCmnt=By.xpath("//form[@id='frmaddActiveTask']/div[3]/div/div/div/div");
	public static By unplanTskType=By.xpath("//div[@id='txtTaskType_AEAT_chzn']/a/span");
	public static By unplanTskTypeTxtBox=By.xpath("//div[@id='txtTaskType_AEAT_chzn']/div/div/input");
	public static By unplanTskTypeTtlVal=By.xpath("//div[@id='txtTaskType_AEAT_chzn']/div/ul/li");
	public static By unplanTskStrtDt=By.xpath("//input[@id='txtStartDate_AEAT']");
	//th[class='next']
	public static By unplanTskDtRgtArow=By.xpath("html/body/div[26]/div[1]/table/thead/tr[1]/th[3]");
	public static By unplanTskStrtTime=By.xpath("//input[@id='txtStartTime_AEAT']");
	public static By unplanTskTimeHrs=By.xpath("html/body/div[24]/ul/li[1]/span");
	public static By unplanTskTimeMin=By.xpath("html/body/div[24]/ul/li[3]/span");
	public static By umplanTskEndDt=By.xpath("//input[@id='txtEndDate_AEAT']");
	public static By asignEmp=By.xpath("//div[@id='ddlEmployeeAssigned_AEAT_chzn']/a/span");
	public static By asignEmpTxtBox=By.xpath("//div[@id='ddlEmployeeAssigned_AEAT_chzn']/div/div/input");
	public static By asignEmpTtlVal=By.xpath("//div[@id='ddlEmployeeAssigned_AEAT_chzn']/div/ul/li");
	public static By unplanTskSavebtn=By.xpath("//button[contains(text(),'Save')][@ng-click='SaveAddTask();']");
	
	
    //	ACTIVE TASKS
	
	public static By tskDeleteConfPopup = By.xpath("//div[@class='bootbox modal fade bootbox-confirm in']/div/div");
	
	public static By viewTskName=By.xpath("//div[@id='divViewActiveTask']/div[2]/div[1]/div/div[1]/div/div");
	public static By reassignBtn = By.linkText("Reassign");
	public static By editBtnFstTsk = By.xpath("//div[@id='divActiveTasks']/div[2]/div/div/div[3]/div/a[2]");
	public static By deleteBtnFstTsk = By.xpath("//div[@id='divActiveTasks']/div[2]/div/div/div[3]/div/a[3]");
	public static By updateProgressBtn = By.linkText("Update Progress");
	public static By totalActiveTasks = By.xpath("//div[@id='divActiveTasks']/div[2]/div/div[@class='dr-task-list ng-scope']");
	public static By activTaskAssigneeName = By.xpath("//div[@id='divActiveTasks']/div[2]/div/div/div[3]/span[2]");
	public static By activTaskDurationEdt = By.xpath("//div[@id='divActiveTasks']/div[2]/div/div/div[3]/span");
	public static By activeTaskProgressBar=By.cssSelector("input[ng-model='at.progress_copy'][min='1']");
	public static By activeTskProgressBarUpdtBtn=By.xpath("//span[@ng-click='UpdateActiveTaskProgress($index,$event);'][text()='Update']");
	
	//	REASSIGN POPUP
	public static By taskReassignPopup = By.id("divAssignResourceTask");
	
	public static By asignReasignEmpDropDown = By.xpath("//div[@id='ddlActiveTaskEmployee_chzn']/a/span");
	public static By assignReassignDDArrow = By.xpath("//div[@id='ddlActiveTaskEmployee_chzn']/a/div");
	public static By assignReassignSearchBox = By.xpath("//div[@id='ddlActiveTaskEmployee_chzn']/div/div/input");
	public static By assignReassignNewEmpDDTtlVal=By.xpath("//div[@id='ddlActiveTaskEmployee_chzn']/div/ul/li");
	public static By reassignSaveBtn = By.xpath("//div[@id='divAssignResourceTask']/div[3]/button[1]");
	public static By reassignCloseBtn = By.xpath("//div[@id='divAssignResourceTask']/div[3]/button[2]");
	public static By reassignCrossCloseBtn = By.xpath("//div[@id='divAssignResourceTask']/div/button");
	
	//	EDIT POPUP
	public static By editTaskPopup = By.id("divEditActiveTask");
	
	public static By taskDurationField = By.id("xtTaskDuration_EAT");
	public static By editTaskSaveBtn = By.xpath("//div[@id='divEditActiveTask']/div[3]/button");			
	public static By editTaskCloseBtn = By.xpath("//div[@id='divEditActiveTask']/div[3]/button[2]");
	public static By editTaskCrossCloseBtn = By.xpath("//div[@id='divEditActiveTask']/div/button");
	
	public static By reasignEmployeeDDVal = By.xpath("//div[3]/div/div[2]/div/div/div[4]/div[2]/form/div/div/div/div/div/div/ul/li");
	
	//	DELETE CONFIRMATION
	public static By deleteConfOkBtn = By.xpath("//div[@class='bootbox modal fade bootbox-confirm in']/div/div/div[2]/button[2]");
	public static By deleteConfCancelBtn = By.xpath("//div[@class='bootbox modal fade bootbox-confirm in']/div/div/div[2]/button");
	public static By deleteConfCrossCloseBtn = By.xpath("//div[@class='bootbox modal fade bootbox-confirm in']/div/div/div/button");
	
	//	NORMAL DASHBOARD
	public static By drTeamTitleTxt = By.xpath("//div[@id='divDRTeam']/div/h2"); 
	// Help menu 
	public static By helpManualBtn = By.xpath("//div[@id='helptextId']");
	public static By helpDashboardTxt  = By.xpath("//div[@id='content']/div[2]/div/div[1]/h2");
	public static By stepsformakingyourDRBCPlan = By.xpath("//div[@id='content']/div[2]/div/div[2]/div/div/div[1]/div[1]/div/h4/a");
	public static By homeBtnInHlpDocPge = By.xpath("//div[@id='content']/div[2]/div/div[2]/div[2]/div/div/a[1]");
	public static By componentsOfSIB  = By.xpath("//div[@id='content']/div[2]/div/div[2]/div/div/div[1]/div[2]/div/h4");
	public static By understandingtheWorkspace = By.xpath("//div[@id='content']/div[2]/div/div[2]/div/div/div[1]/div[3]/div/h4/a");
	public static By orderofDataEntry = By.xpath("//div[@id='content']/div[2]/div/div[2]/div/div/div[1]/div[4]/div/h4/a");
	public static By methodsofDataEntry = By.xpath("//div[@id='content']/div[2]/div/div[2]/div/div/div[2]/div[1]/div/h4/a");
	public static By massEdit = By.xpath("//div[@id='content']/div[2]/div/div[2]/div/div/div[2]/div[2]/div/h4/a");
	public static By massDelete = By.xpath("//div[@id='content']/div[2]/div/div[2]/div/div/div[2]/div[3]/div/h4/a");
	public static By exportingDataFromthePlatform = By.xpath("//div[@id='content']/div[2]/div/div[2]/div/div/div[2]/div[4]/div/h4/a");
	public static By understandingPreDefinedFieldsnValues = By.xpath("//div[@id='content']/div[2]/div/div[2]/div/div/div[3]/div[1]/div/h4/a");
	public static By relationshipRecordsBetweenModules = By.xpath("//div[@id='content']/div[2]/div/div[2]/div/div/div[3]/div[2]/div/h4/a");
	public static By logintoStayinBusinessPlatform = By.xpath("//div[@id='content']/div[2]/div/div[2]/div/div/div[3]/div[3]/div/h4/a");
	public static By forgotandRecoverPassword = By.xpath("//div[@id='content']/div[2]/div/div[2]/div/div/div[3]/div[4]/div/h4/a");
	public static By locationsh = By.xpath("//div[@id='content']/div[2]/div/div[2]/div/div/div[4]/div[1]/div/h4/a");
	public static By Threatsh= By.xpath("//div[@id='content']/div[2]/div/div[2]/div/div/div[4]/div[2]/div/h4/a");
	public static By employeesh = By.xpath("//div[@id='content']/div[2]/div/div[2]/div/div/div[4]/div[3]/div/h4/a");
	public static By teamsh = By.xpath("//div[@id='content']/div[2]/div/div[2]/div/div/div[4]/div[4]/div/h4/a");
	public static By assetsh =By.xpath("//div[@id='content']/div[2]/div/div[2]/div/div/div[5]/div[1]/div/h4/a");
	public static By assetGrph = By.xpath("//div[@id='content']/div[2]/div/div[2]/div/div/div[5]/div[2]/div/h4/a");
	public static By contactsh = By.xpath("//div[@id='content']/div[2]/div/div[2]/div/div/div[5]/div[3]/div/h4/a");
	public static By contactGroupsh = By.xpath("//div[@id='content']/div[2]/div/div[2]/div/div/div[5]/div[4]/div/h4/a");
	public static By insurancesh = By.xpath("//div[@id='content']/div[2]/div/div[2]/div/div/div[6]/div[1]/div/h4/a");
	public static By tasksh = By.xpath("//div[@id='content']/div[2]/div/div[2]/div/div/div[6]/div[2]/div/h4/a");
	public static By taskgroupsh =By.xpath("//div[@id='content']/div[2]/div/div[2]/div/div/div[6]/div[3]/div/h4/a");
	public static By businessFunctionsh =By.xpath("//div[@id='content']/div[2]/div/div[2]/div/div/div[6]/div[4]/div/h4/a");
	public static By threatsDRPlanh = By.xpath("//div[@id='content']/div[2]/div/div[2]/div/div/div[7]/div[1]/div/h4/a");
	public static By stepstoCreataBCandDRPlanh = By.xpath("//div[@id='content']/div[2]/div/div[2]/div/div/div[7]/div[2]/div/h4/a");
	public static By dRTemplatesh = By.xpath("//div[@id='content']/div[2]/div/div[2]/div/div/div[7]/div[3]/div/h4/a");
	public static By managedisasterh  = By.xpath("//div[@id='content']/div[2]/div/div[2]/div/div/div[7]/div[4]/div/h4/a");
	public static By activatingaThreat = By.xpath("//div[@id='content']/div[2]/div/div[2]/div/div/div[8]/div[1]/div/h4/a");
	public static By declareaDisasterandActivateaBCDRPlan = By.xpath("//div[@id='content']/div[2]/div/div[2]/div/div/div[8]/div[2]/div/h4/a");
	public static By managingRecoveryOperations = By.xpath("//div[@id='content']/div[2]/div/div[2]/div/div/div[8]/div[3]/div/h4/a");
	
	public static By endingDeclaredDisasterandRestoration=By.xpath("//div[@id='content']/div[2]/div/div[2]/div/div/div[8]/div[4]/div/h4/a");
	public static By alertsh = By.xpath("//div[@id='content']/div[2]/div/div[2]/div/div/div[9]/div[1]/div/h4/a");
	public static By incidentsh = By.xpath("//div[@id='content']/div[2]/div/div[2]/div/div/div[9]/div[2]/div/h4/a");
	public static By adminModuleh = By.xpath("//div[@id='content']/div[2]/div/div[2]/div/div/div[9]/div[3]/div/h4/a");
	public static By managingRolesh = By.xpath("//div[@id='content']/div[2]/div/div[2]/div/div/div[9]/div[4]/div/h4/a");
	public static By managingPermissionsh = By.xpath("//div[@id='content']/div[2]/div/div[2]/div/div/div[10]/div[1]/div/h4/a");
	public static By mapTeamsandRolesh = By.xpath("//div[@id='content']/div[2]/div/div[2]/div/div/div[10]/div[2]/div/h4/a");
	public static By addAndModifyUserRolesh = By.xpath("//div[@id='content']/div[2]/div/div[2]/div/div/div[10]/div[3]/div/h4/a");
	
	public static By settingsh = By.xpath("//div[@id='content']/div[2]/div/div[2]/div/div/div[10]/div[4]/div/h4/a");
	public static By emailsandSMSReportsh = By.xpath("//div[@id='content']/div[2]/div/div[2]/div/div/div[11]/div[1]/div/h4/a");
	public static By chatMonitorh = By.xpath("//div[@id='content']/div[2]/div/div[2]/div/div/div[11]/div[2]/div/h4/a");
	public static By chatReportsh = By.xpath("//div[@id='content']/div[2]/div/div[2]/div/div/div[11]/div[3]/div/h4/a");
	public static By managingUserProfileh = By.xpath("//div[@id='content']/div[2]/div/div[2]/div/div/div[11]/div[4]/div/h4");
	public static By chatsModuleh = By.xpath("//div[@id='content']/div[2]/div/div[2]/div/div/div[12]/div[1]/div/h4/a");
	public static By Documentsh = By.xpath("//div[@id='content']/div[2]/div/div[2]/div/div/div[12]/div[2]/div/h4/a");
	public static By logouth = By.xpath("//div[@id='content']/div[2]/div/div[2]/div/div/div[12]/div[3]/div/h4/a");
	public static By glossaryh = By.xpath("//div[@id='content']/div[2]/div/div[2]/div/div/div[12]/div[4]/div/h4/a");



	//Recovery status of affected business functions
	
	public static By hmeBsfSectionHeader=By.xpath("//div[@id='content']/div[2]/div[1]/div[2]/div[2]/div[5]/div/div[1]/h2");
	public static By hmeBsfTableTtlVal=By.xpath("//tr[@class='tr-afct-bsns-fns ng-scope']");
	public static By hmeBsfNTasksCount=By.xpath("//table[@id='ProcessRecovery']/tbody/tr");
	
	//Recently Recovered Disasters
	public static By recDisasterHeader=By.xpath("//div[@id='divRecoveredDisasterList']/div/div[1]/h2");
	public static By recDisasterSearchBox=By.xpath("//div[@id='tblRecoveredDisaster_filter']/label/input");
	public static By recDisasterSearchFstVal=By.xpath("//table[@id='tblRecoveredDisaster']/tbody/tr[1]/td[1]/div[1]");
	public static By recDisasterTableTltVal=By.xpath("//table[@id='tblRecoveredDisaster']/tbody/tr");
	public static By recDisasterViewReport=By.xpath("//table[@id='tblRecoveredDisaster']/tbody/tr[1]/td[8]/a");
	public static By recDisasterSaveAsPdfBtn=By.xpath("//div[@id='content']/div[2]/div/div[1]/p/a");
	public static By recDisasterPagicount=By.xpath("//div[@id='tblRecoveredDisaster_wrapper']/div[2]/div[2]/div/ul/li");
	public static By recDisasterShowEntries=By.xpath("//select[@name='tblRecoveredDisaster_length']");
	public static By recDisasterListViewRecsInfo=By.xpath("//div[@id='tblRecoveredDisaster_info']");
	
	//End Disaster
	public static By hmeEndDisasterBtn=By.xpath("//a[@id='btnEndDisaster']");
	public static By hmeEndDisasterConfirmOkBtn=By.cssSelector("button[data-bb-handler='confirm']");
	public static By hmeEndDisasterRecTimeDtNextBtn=By.cssSelector("button[ng-click='SaveAndContinue1(this)']");
	

	public static By hmeEndDisasterRecEmailChckAll=By.cssSelector("#chk_selectall_ed");
	public static By recEmpTotalTableVal=By.xpath("//table[@id='tblEmailContent_ED']/tbody/tr");
	public static By hmeEndDisasterRecEmailNextBtn=By.cssSelector("button[ng-click='SaveAndContinue2(this)']");
	public static By recEmpPgTtlCnt=By.xpath("//div[@id='tblEmailContent_ED_wrapper']/div[2]/div[2]/div/ul/li");
		
	public static By hmeEndDisasterLocEmpChckAll=By.cssSelector("#chk_selectall_ep");
	public static By locEmpTotalTableVal=By.xpath("//table[@id='tblEmailContent_EP']/tbody/tr");
	public static By locEmpPgTtlCnt=By.xpath("//div[@id='tblEmailContent_EP_wrapper']/div[2]/div[2]/div/ul/li");
	public static By hmeEndDisasterLocEmpNextBtn=By.cssSelector("button[ng-click='SaveAndContinue3(this)']");
	
	public static By hmeEndDisasterCustChckAll=By.cssSelector("#chk_selectall_ec");
	public static By custEmpTotalTableVal=By.xpath("//table[@id='tblEmailContent_EC']/tbody/tr");
	public static By custEmpPgTtlCnt=By.xpath("//div[@id='tblEmailContent_EC_wrapper']/div[2]/div[2]/div/ul/li");
	public static By hmeEndDisasterCustNextBtn=By.cssSelector("button[ng-click='SaveAndContinue4(this)']");
	
	public static By hmeEndDisasterVendrChckAll=By.cssSelector("#chk_selectall_ev");
	public static By vendrEmpTotalTableVal=By.xpath("//table[@id='tblEmailContent_EV']/tbody/tr");
	public static By vendrEmpPgTtlCnt=By.xpath("//div[@id='tblEmailContent_EV_wrapper']/div[2]/div[2]/div/ul/li");
	public static By hmeEndDisasterVendrNextBtn=By.cssSelector("button[ng-click='SaveAndContinue5(this)']");
	
	public static By restoreBtn=By.cssSelector("button[ng-click='EndDisaster()']");






}
