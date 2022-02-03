package ObjectRepository;

import org.openqa.selenium.By;

public class Incidents {

	public static By alertBtnInTopMenu = By.xpath("//div[3]/div/div/div[4]/a/span[1]");
	public static By incidentsMenu = By.xpath("//div[3]/div/div/div[4]/ul/li[2]/a/span");
	
	//Main Menu 
	public static By incAddBtn = By.xpath("//div[@id='content']/div[2]/div/div[1]/p/a[2]");
	public static By incSearchBox = By.xpath("//div[@id='incident_table_filter']//input");
	public static By incInfoBtn = By.xpath("//div[@id='content']/div[2]/div/div[1]/p/a[1]");
	public static By incInfoPopup = By.xpath("//div[@id='Incident-help']/div");
	public static By incInfoCloseBtn = By.xpath("//div[@id='Incident-help']/div[3]/button");
	public static By incMassEditBtn = By.id("mass_update");
	public static By incDeleteBtn = By.cssSelector("[class='btn btn-documents btn-small']");
	public static By incExportDataBtn = By.id("export");
	public static By incIconDownUpArrow = By.xpath("//div[@id='content']/div[2]/div/div[1]/div/a/i");
	public static By incUpdateStatus=By.cssSelector("a[class='label stat_btn btn-primary dropdown-toggle']");

	
	//Incident Main List Table 
	public static By incEditBtn = By.xpath("//table[@id='incident_table']/tbody/tr/td[7]/a[1]");
	public static By incListtblDeleteBtn = By.xpath("//table[@id='incident_table']/tbody/tr/td[7]/a[2]");
	public static By incViewBtn = By.xpath("//table[@id='incident_table']/tbody/tr/td[7]/a[3]");
	public static By incSelectAllCheckbox = By.id("chk_source_table");
	public static By incLstViewSearchRslt = By.xpath("//table[@id='incident_table']//tr/td[2]");
	
	public static By incDeleteOkBtn = By.xpath("//button[@class='btn btn-primary']");
	public static By incDeleteCancelBtn = By.xpath("//button[@class='btn btn-default']");
	
	public static By incViewCloseBtn = By.xpath("//div[@id='showdisplay_view']/fieldset/div[5]/input");
	public static By viewScreen  = By.xpath("//div[@id='showdisplay_view']");
	public static By viewPageHeader=By.xpath("//div[@id='showdisplay_view']/h5");
	
	public static By incIncidentLocListviewTbCnt = By.xpath("//table[@id='incident_table']/tbody/tr");
	
	
	
	
	//Add New Incident 
	
	public static By incLocationArrow = By.xpath("//div[@id='incident_location_chzn']/a/div");
	public static By incLocationSearchBox = By.xpath("//div[@id='incident_location_chzn']//input");
	public static By incLocationCnt = By.xpath("//div[@id='incident_location_chzn']//ul/li");
	public static By incSelectLocation = By.xpath("//div[@id='incident_location_chzn']/a/span");
	
	
	
	public static By incIncidentSpot = By.id("incident_spot");
	public static By incIncidentType = By.id("incident_type");
	
	public static By incidentLocationDefTxt = By.xpath("//div[@id='incident_location_chzn']/a/span");
	public static By severityLevelDefTxt = By.xpath("//div[@id='incident_severity_chzn']/a/span");
	public static By areYouAffectedDefTxt= By.xpath("//div[@id='incident_affected_chzn']/a/span");
	
	//Date picker 
	
	public static By incIconCalendar = By.xpath("//form[@id='incident_form']/fieldset/div[1]/div/div/div/div[4]/div/div/div/span");
	public static By incDatePickrDaysCalndr = By.xpath("//div[@class='datepicker-days']//th[@class='datepicker-switch']");
	public static By incDatePickrMonthsCalndr = By.xpath("//div[@class='datepicker-months']//th[@class='datepicker-switch']");
	public static By incDatePickrYearsCalndr = By.xpath("//div[@class='datepicker-years']//th[@class='datepicker-switch']");
	
	public static By incYrInCalTop = By.xpath("//table[@class='table-condensed']//tr/th[2]");
	
	public static By incCalndrRightSideArrow = By.xpath("//div[@class='datepicker-years']/table/thead/tr/th[3]");
	public static By incCalndrLeftSideArrow = By.xpath("//div[@class='datepicker-years']/table/thead/tr/th[1]");
	
	public static By incGetTotalYearsCnt = By.xpath("//div[@class='datepicker-years']//tbody/tr/td/span");
	public static By incGetTotalMonths = By.xpath("//div[@class='datepicker-months']//tbody/tr/td/span");
	public static By incGetRandomDateRowCnt = By.xpath("//div[@class='datepicker-days']//tbody/tr");	
	public static By incGetRandomDateColCnt = By.xpath("//div[@class='datepicker-days']//tbody/tr/td");
	
	public static By  incCalndrMnth = By.xpath("//div[24]/div[1]/table/thead/tr[1]/th[2]");
	
	public static By  incCalndrJunMnth = By.xpath("//span[contains(text(),'Jun')]");
	public static By  incCalndrDateThrty = By.xpath("//div[24]/div[1]/table/tbody/tr[5]/td[6]"); 
	
	public static By incDate = By.id("incident_date");
			
	
	public static By incTime = By.id("incident_time");
	
	public static By incSeverityLvlArrow = By.xpath("//div[@id='incident_severity_chzn']/a/div");
	public static By incSeverityLvlSearchBox = By.xpath("//div[@id='incident_severity_chzn']//input");
	public static By incSeverityLvlCnt = By.xpath("//div[@id='incident_severity_chzn']//ul/li");
	public static By incSelectSeverityLvl = By.xpath("//div[@id='incident_severity_chzn']/a/span");
	
	
	public static By incAreyouAffArrow = By.xpath("//div[@id='incident_affected_chzn']/a/div");
	public static By incAreyouAffSearchBox = By.xpath("//div[@id='incident_affected_chzn']//input");
	public static By incAreyouAffCnt = By.xpath("//div[@id='incident_affected_chzn']//ul/li");
	public static By incSelectAreyouAff = By.xpath("//div[@id='incident_affected_chzn']/a/span");
	
	public static By incSubmitBtn = By.id("incident_submit");
	public static By incClearBtn = By.xpath("//form[@id='incident_form']/fieldset/div[4]/div/input[1]");
	public static By incCancelBtn = By.cssSelector("input[class='btn validate-cancel {incidents_form}']");
	
	
	// Mass Edit 
	
	public static By incmEdtIncidentType = By.id("incident_bulk_type");
	
	public static By incmEdtSeverityLvlArrow = By.xpath("//div[@id='incident_bulk_severity_chzn']/a/div");
	public static By incmEdtSeverityLvlSearchBox = By.xpath("//div[@id='incident_bulk_severity_chzn']//input");
	public static By incmEdtSeverityLvlCnt = By.xpath("//div[@id='incident_bulk_severity_chzn']//ul/li");
	public static By incmEdtSelectSeverityLvl = By.xpath("//div[@id='incident_bulk_severity_chzn']/a/span");
	
	public static By incmEdtAreyouAffArrow = By.xpath("//div[@id='incident_bulk_affected_chzn']/a/div");
	public static By incmEdtAreyouAffSearchBox = By.xpath("//div[@id='incident_bulk_affected_chzn']//input");
	public static By incmEdtAreyouAffCnt = By.xpath("//div[@id='incident_bulk_affected_chzn']//ul/li");
	public static By incmEdtSelectAreyouAff = By.xpath("//div[@id='incident_bulk_affected_chzn']/a/span");
	
	public static By incmEdtSubmitBtn = By.id("incident_bulk_submit");
	public static By incmEdtClearBtn = By.xpath("//button[@class='btn act-clr validate-cancel {incident_mass_update_form}']");
	public static By incmEdtCancelBtn = By.xpath("//button[@class='btn validate-cancel {incident_mass_update_form}']");
	
	public static By incFirstRow = By.xpath("//table[@id='incident_table']/tbody/tr[1]/td/div/span/input");
	public static By incSecondRow = By.xpath("//table[@id='incident_table']/tbody/tr[2]/td/div/span/input");
	
	// Pagination and Show Entries 
    public static By incListViewPagination = By.xpath("//div[@id='incident_table_wrapper']/div[2]/div[2]/div/ul/li/a");
	
	public static By incShowEntLength = By.name("incident_table_length");
	
	public static By incNameListViewRecsInfo = By.xpath("//div[@id='incident_table_info']");
	
	//Teams to notify
	
	public static By incTeamsTotalCount=By.xpath("//table[@id='team_table']/tbody/tr/td[2]");
	
	//View Page
	public static By incViewTtlRowCnt=By.xpath("//div[@id='showdisplay_view']/fieldset/div");
	
	//update status
	public static By incAddCmtTitle=By.xpath("//div[@id='addMessagePopUp']/div[1]/h3");
	public static By incAddCmtBtn=By.xpath("//button[@id='add_message_confirm']");
	public static By incResTitle=By.xpath("//div[@id='statusChangePopUp']/div[1]/h3");
	public static By updatBtn=By.xpath("//button[@id='update_status_confirm']");
	
}

