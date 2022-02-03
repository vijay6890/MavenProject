package ObjectRepository;

import org.openqa.selenium.By;

public class Alerts {
	
public static By alertsOptnInTopDDLst = By.xpath("//a[contains(@href,'/alerts')]");
	
	public static By alOvrInfoBtn = By.xpath("//a[@class='btn btn-help btn-small act Alert-help']");
	public static By alOvrAddBtn = By.xpath("//a[@class='btn btn-help btn-small act']");
	public static By alOvrDeleteBtn = By.xpath("//a[@class='btn btn-documents btn-small']");
	
	public static By alAlertHelpPp = By.xpath("//div[@id='Alert-help']/div[2]/div/div");
	public static  By alAlertHelpPpCloseBtn = By.xpath("html/body/div[8]/div[3]/button");
	
	//		ALERTS LIST VIEW
	//public static By alAlertPageTitle=By.xpath(".//*[@id='content']/div[2]/div[1]/div[1]/h2");
	public static By alAlertsListTtlTxt = By.xpath("//i[@class='icon-threat']");
	public static By alSearchAlerts = By.xpath("//input[@aria-controls='alert_table']");
	
	public static By alAlertsListViewCnt = By.xpath("//table[@id='alert_table']//tbody/tr");
	public static By alSrchRsltText = By.xpath("//table[@id='alert_table']//tbody/tr/td[2]");
	
	public static By alSelectAllChkBox = By.id("chk_source_table");
	
	public static By alLstViewFstNm = By.xpath("//table[@id='alert_table']//tbody/tr//input");
	public static By alLstViewSecNm = By.xpath("//table[@id='alert_table']//tbody/tr[2]//input");
	
	public static By alAlertsListViewRecsInfo = By.id("alert_table_info");
	public static By alAlertsListViewLength = By.name("alert_table_length");
	
	public static By alAlertsListViewPagination = By.xpath("//div[@id='alert_table_wrapper']/div/div//ul/li");
		
	//		ADD NEW ALERT
	public static By alAlertsTxtInPanel = By.xpath("//div[@id='content']//div[2]//div/h2");
	
	public static By alDrillAlertChkBox = By.id("is_mock");
	public static By alAlertTitle = By.id("alert_title");
	
	public static By alModeOfCommEmailndSMS = By.id("comm_mode_1");
	
	
	//		LOCATIONS
	public static By alSearchLocations = By.xpath("//input[@aria-controls='location_table']");	
	
	public static By alLocNoRecordsFoundMsg = By.xpath("//table[@id='location_table']//tbody/tr/td");
	
	
	public static By alLocationsListViewCnt = By.xpath("//table[@id='location_table']//tbody/tr");
	public static By alLocShowEntries = By.xpath("location_table_length");
	
	//		TEAMS
	public static By alTmsTotalCnt=By.xpath("//table[@id='team_table']/tbody/tr/td[2]");
	
	public static By alTeamsSearchBox = By.xpath("//input[@aria-controls='affected_bus_area']");
	
	public static By alTmsNoRecordsFoundMsg = By.xpath("//table[@id='team_table']//tbody/tr/td");
	
	public static By alTeamsLstViewCnt = By.xpath("//table[@id='resourcegroup_table']//tbody/tr/td[2]");
	public static By alTeamsShowEntries = By.xpath("affected_bus_area_length");
	public static By alTeamsNoRecordsFoundMsg = By.xpath("//table[@id='affected_bus_area']//td[@class='dataTables_empty']");
	public static By alTeamListViewFstChkBox = By.xpath("//table[@id='team_table']//tbody/tr/td/div/span/input");
	public static By alTeamListViewFstChkBoxClk = By.xpath("//table[@id='team_table']//tbody/tr/td/div/span/input");
	
	public static By alTeamsListFstNm = By.xpath("//table[@id='affected_bus_area']//tbody//td[2]");
	
	public static By alBusFnsTotalCnt=By.xpath("//table[@id='business_function_table']/tbody/tr/td[2]");
	
	public static By alSendBtn = By.name("alert_submit");
	public static By alClearBtn = By.xpath("//button[contains(text(),'Clear')]");
	public static By alCancelBtn = By.xpath("//button[contains(text(),'Cancel')]");
	public static By alconfirmationpopupcancelbtn=By.xpath("//button[@data-bb-handler='cancel");
	public static By alconfirmationpopupokbtn=By.xpath("html/body/div[24]/div/div/div[2]/button[2]");
	public static By alHideClosedAlertsbtn=By.id("show_closed");
	public static By alertResolvedRadiobtn=By.id("status_resolved");
	public static By alertNotifyEmployeeCheckbox=By.id("notify_employee");
	public static By alertUpdateStatusbtn=By.xpath("//button[@type='submit']");
	public static By alertClosedRadiobtn=By.id("status_closed");
	public static By addEmployeeBtn=By.id("add_employee");
    public static By selectAllCheckBoxAddEmployeeWindow = By.xpath("//table[@id='additional_employee_table']//input[@title='Select All']");
    public static By addBtnAddEmployeeWindow = By.id("add_list_user");
    public static By alertsDeleteAllButton = By.id("delete_all_btn");
    
    
    public static By alertsLocations = By.xpath("//table[@id='location_table']//tbody//tr");
    public static By teamsNoRecordsFound = By.xpath("//table[@id='team_table']//tr//td[contains(text(),'No Records Found')]");
    public static By alertsTeams = By.xpath("//table[@id='team_table']//tbody//tr");
    public static By businessFunctionsNoRecordsFound = By.xpath("//table[@id='business_function_table']//tbody//tr//td[contains(text(),'No Records Found')]");
    public static By alertsBusinessFunctions = By.xpath("//table[@id='business_function_table']//tbody//tr");
    public static By employeesNoRecordsFound = By.xpath("//table[@id='employee_table']//tbody//tr//td[contains(text(),'No Records Found')]");
    public static By alertsEmployeesAdditionalEmployeeTable = By.xpath("//table[@id='additional_employee_table']//tbody//tr");
    public static By empFirstNameBeforeMapping = By.xpath("//table[@id='additional_employee_table']//tbody//tr//td[2]");
    public static By empSecondNameBeforeMapping = By.xpath("//table[@id='additional_employee_table']//tbody//tr//td[3]");
    public static By empFirstNameAfterMapping = By.xpath("//table[@id='employee_table']//tbody//tr//td[1]");
    public static By empSecondNameAfterMapping = By.xpath("//table[@id='employee_table']//tbody//tr//td[2]");
    public static By alertNameInListView = By.xpath("//table[@id='alert_table']//tbody//tr//td[2]");
    public static By alertsStatusListView = By.xpath("//table[@id='alert_table']//tr[1]//td[6]");
    public static By alertsAdditionalEmpTableNoRecordsFound = By.xpath("//table[@id='additional_employee_table']//tbody//tr//td[contains(text(),'No Records Found')]");
    public static By additionalEmpTableSelectAllCheckBoxLocations = By.xpath("//table[@id='additional_employee_location_table']//input[@title='Select All']");
    //public static By alertsAddCommentButton = By.xpath("//button[contains(text(),'Add Comment')]");
    public static By alertsAddCommentButton=By.xpath("//form[@id='commentForm']/button[1]");    
    public static By additionalMessageLinkViewScreen = By.xpath("//div[@class='control-group']//b/../div//a");
    public static By additionalMessageText = By.xpath("//div[@id='Active_comments']//p");
    public static By closeBtnAdditionalMessageScreen = By.xpath("//div[@id='Active_comments']//button[contains(text(),'Close')]");
    public static By alertsRemoveAllButton = By.xpath("//span[@id='clear_list']");
    public static By selectTeamsLabel = By.xpath("//label[@class='control-label']//h4[contains(text(),'Select Teams')]");
    public static By selectLocationsLabel = By.xpath("//form[@id='alert_form']//label[@class='control-label']//h4[contains(text(),'Select Locations')]");
    public static By alertsTasksList = By.xpath("//table[@id='task_table']//tbody//tr");
    public static By selectTasksLabel = By.xpath("//label[@class='control-label']//h4[contains(text(),'Select Tasks')]");
    //test
}
