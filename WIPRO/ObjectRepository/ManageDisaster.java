package ObjectRepository;

import org.openqa.selenium.By;

public class ManageDisaster {

	public static By manageDisasterInMainMenu = By.xpath("//a[contains(@href,'/disaster')]");
	
	public static By listOfDisasterText = By.xpath("//div[@id='content']//div[2]//div/h2");
	public static By mdOvrInfoBtn = By.xpath("//div[@id='content']/div[2]/div[1]/p/a[1] ");
	public static By mdHelpPopup = By.xpath("//div[@id='Declare-Disaster-help']/div[2]/div/div/div[2]/div/div");
	public static By mdHelpPopupCloseBtn = By.xpath("//div[@id='Declare-Disaster-help']/div[3]/button");
	public static By mdExportBtn = By.xpath("html/body/div[4]/div[1]/div/div[2]/div[2]/div[1]/p/a[2]");
	
	//	LIST OF DISASTER
	public static By mdDisasterNameSearchBox = By.cssSelector("input[aria-controls='disaster_table']");
	public static By mdDeclareRestoredReport = By.xpath("//table[@id='disaster_table']/tbody/tr/td[8]/a");
	public static By mdListDeclareSrchRslt = By.xpath("//table[@id='disaster_table']/tbody/tr/td[1]");
	public static By mdListSearchBox = By.xpath("//div[@id='disaster_table_filter']//input");
	public static By mdLsViewCnt = By.xpath("//table[@id='disaster_table']/tbody/tr/td[1]");
	
	public static By mdListViewRecsInfo = By.xpath("//div[@id='disaster_table_info']");
	public static By mdListViewLength = By.name("disaster_table_length");
	public static By mdListViewPagination = By.xpath("//div[@id='disaster_table_wrapper']/div[2]/div[2]/div/ul/li/a");
	
	public static By mdReportSaveAsPdf = By.cssSelector("a[class='btn btn-small']");
	
	//	AFFECTED LOCATION
	public static By mdLocationsSearchBox = By.xpath("//div[@id='disaster_location_table_filter']//input");
	public static By mdLocationSrchRslt = By.xpath("//table[@id='disaster_location_table']/tbody/tr/td[6]");
	
	public static By mdAffLocationRadiobBtn = By.xpath("//table[@id='disaster_location_table']/tbody/tr/td[1]/input");
	public static By mdAffLocationLstViewCnt = By.xpath("//table[@id='disaster_location_table']//tbody/tr");
	
	public static By mdAfftLocViewRecsInfo = By.xpath("//div[@id='disaster_location_table_info']");
	public static By mdAffLocViewLength = By.name("disaster_location_table_length");
	public static By mdAffLocViewPagination = By.xpath("//div[@id='disaster_location_table_wrapper']/div[2]/div[2]/div/ul/li/a");
	
	
	
	//	LIST OF DR PLANS
	public static By mdDRPlanSearchBox = By.cssSelector("input[aria-controls='DataTables_Table_0']");
	
	public static By mdFstDRPlanRadioBtn = By.xpath("//table[@id='DataTables_Table_0']/tbody/tr/td[1]");
	
    public static By mdDRPlanSrchRslt = By.xpath("//table[@id='DataTables_Table_0']/tbody/tr/td[2] ");
   	
	public static By mdDRPlanLstViewCnt = By.xpath("//table[@id='DataTables_Table_0']/tbody/tr/td[2]");
	
	public static By mdDRPlanViewRecsInfo = By.xpath("//div[@id='DataTables_Table_0_info']");
	public static By mdDRPlanViewLength = By.name("DataTables_Table_0_length");
	public static By mdDRPLanViewPagination = By.xpath("//div[@id='DataTables_Table_0_wrapper']/div[2]/div[2]/div/ul/li/a");
	
	 public static By mdDRPlanFstVal=By.xpath("//table[@id='DataTables_Table_0']/tbody/tr[1]/td[2]");
	public static By mdDRPlanCheckBox=By.xpath("//table[@id='DataTables_Table_0']/tbody/tr/td[1]/div/span/input");	
	
	//	THREATS
	public static By mdFstThreatNmeInThreats = By.xpath("//table[@id='DataTables_Table_2']/tbody/tr/td[1]/div/span/input");
	
     public static By mdThreatNmeSrchRslt = By.xpath("//table[@id='DataTables_Table_2']/tbody/tr/td");
     public static By mdThreatCheckSourceTable = By.id("chk_source_table");
	 
     public static By mdThreatNmeInLstViewCnt = By.xpath("//table[@id='DataTables_Table_2']/tbody/tr/td[2]");
     public static By mdThreatSearchBox = By.xpath("//div[@id='DataTables_Table_2_filter']//input");
 	 public static By mdThreatSrchRslt = By.xpath("//table[@id='DataTables_Table_2']/tbody/tr/td[2]");
	
	 public static By mdThreatNmeViewRecsInfo = By.xpath("//div[@id='DataTables_Table_2_info']");
	 public static By mdThreatNmeViewLength = By.name("DataTables_Table_2_length");
	 public static By mdThreatNmeViewPagination = By.xpath("//div[@id='DataTables_Table_2_wrapper']/div[2]/div[2]/div/ul/li/a");
	 
	// public static By mdThreatSearchBox=By.xpath("//div[@id='DataTables_Table_2_filter']/label/input");
	 public static By mdThreatFstVal=By.xpath("//table[@id='DataTables_Table_2']/tbody/tr[1]/td[2]");
		
	
	
	//	ADD DISASTER INFO	
	public static By mdDisasterName = By.id("disaster_name_i");
	public static By mdDisasterDate = By.id("disater_time_i");
	public static By dDateIconCalendar = By.xpath("//div[@id='disater-time-block']/div[1]/div/div/div/span/i");
	public static By disDatePickrDaysCalndr = By.xpath("//div[@class='datepicker-days']//th[@class='datepicker-switch']");
	public static By disDatePickrMonthsCalndr = By.xpath("//div[@class='datepicker-months']//th[@class='datepicker-switch']");
	public static By disDateCalndrLeftSideArrow = By.xpath("//div[@class='datepicker-years']/table/thead/tr/th[1]");
	public static By disGetTotalYearsCnt = By.xpath("//div[@class='datepicker-years']//tbody/tr/td/span");
	public static By disGetTotalMonths = By.xpath("//div[@class='datepicker-months']//tbody/tr/td/span");
	public static By disGetRandomDateRowCnt = By.xpath("//div[@class='datepicker-days']//tbody/tr");	
	public static By disGetRandomDateColCnt = By.xpath("//div[@class='datepicker-days']//tbody/tr/td");
      public static By disYrInCalTop = By.xpath("//table[@class='table-condensed']//tr/th[2]");
	
	public static By disCalndrRightSideArrow = By.xpath("//div[@class='datepicker-years']/table/thead/tr/th[3]");
	public static By disTimePickerControlDown = By.xpath("html/body/div[8]/ul/li/span[3]") ;
	public static By disTimePickerControlUp = By.xpath("html/body/div[8]/ul/li/span[1]");
	public static By timecloseBtn = By.xpath("html/body/div[8]/p/span");
	public static By mdDisasterTime = By.id("disater_time_h");
	public static By mdNotificationDate = By.id("notification_time_i");
	public static By mdNotificationIconCalendar = By.xpath("//div[@id='notification-time-block']/div[1]/div/div/div/span/i");
	public static By mdNotificationDateCalendrIcon = By.xpath("//div[@id='notification-time-block']/div/div/div/div/span/i");
	public static By mdNotificationTime = By.id("notification_time_h");
	public static By mdEstimatedRecoveryDateCalendrIcon = By.xpath("//div[@id='estimate_recovery-time-block']/div/div/div/div/span/i");
	public static By mdEstimatedRecoveryDate = By.id("estimate_recovery_time_i");
	public static By mdEstimatedRecoveryTime = By.id("estimate_recovery_time_h");
	
	public static By mdNotifiedByText = By.xpath("//div[@id='dd_notified_by_i_chzn']/a/span");
	public static By mdNotifiedByDDArrow = By.xpath("//div[@id='dd_notified_by_i_chzn']/a/div");
	public static By mdNotifiedBycnt = By.xpath("//div[@id='dd_notified_by_i_chzn']//ul/li");
	public static By mdNotifiedByDDSearchBox = By.xpath("//div[@id='dd_notified_by_i_chzn']/div/div/input");
	public static By mdAuthorizedByDDArrow = By.xpath("//div[@id='dd_authorized_by_i_chzn']/a/div");
	public static By mdAuthorizedByDDSearchBox = By.xpath("//div[@id='dd_authorized_by_i_chzn']/div/div/input");
	public static By mdAuthorizedByCnt = By.xpath("//div[@id='dd_authorized_by_i_chzn']//ul/li");
	
	public static By mdNotifyAllEmployeesChkBox = By.name("ddr_loc_to_alert");
	public static By mdAlertPrimaryEmpRadioBtn = By.id("PrimaryId");
	public static By mdAlertPrimaryndSecondaryEmpRadioBtn = By.id("AlternateId");
	public static By mdPrimaryPrimSecondRadioCnt = By.xpath("//form[@id='ddr-dd-form']/fieldset/div[7]/div/div/div/label/input");
	
	public static By mdContactGrpNotifyCnt = By.xpath(".//*[@id='dd_contact_group']");
	
	public static By mdDeclareDisasterBtn = By.name("dd_submit");
	public static By mdClearBtn = By.xpath("//div[3]/div[1]/div[2]/div/div[2]/div[2]/div[3]/form/feildset/div[8]/input[4]");
	
	public static By declareDisasterConfPopup = By.xpath("//div[@class='bootbox modal fade bootbox-confirm in']/div");
	public static By declareDisasterConfPpCancelBtn = By.xpath("//div[@class='bootbox modal fade bootbox-confirm in']/div/div/div[2]/button");
	public static By declareDisasterConfPpOkBtn = By.xpath("//div[@class='bootbox modal fade bootbox-confirm in']/div/div/div[2]/button[2]");
	
	public static By mdDrillAlert = By.xpath(".//*[@id='uniform-undefined']/span/input");
	public static By mdModeofCommunicationCnt = By.xpath("//form[@id='ddr-dd-form']/fieldset/div[6]/div/div/div/label/input");
	
	public static By contscEdtrCommentsField = By.xpath("//form[@id='ddr-dd-form']/fieldset/div[9]/div/div/div/div/iframe");
	public static By notifyAllEmpLoc = By.xpath("//form[@id='ddr-dd-form']/fieldset/div[11]/div/div/div/div/iframe");
	
	public static By disasterSummaryPage = By.xpath("//div[@id='content']/div[2]/div/div[1]/h2");
	
 
	
	
	
}
