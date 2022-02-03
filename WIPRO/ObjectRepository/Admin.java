package ObjectRepository;

import org.openqa.selenium.By;

public class Admin {
	
	public static By admRolesSearchBox = By.xpath("//input[@aria-controls='roles_list_table']");
	
	public static By adminBtnInTopMnu = By.xpath("//div[@class='navbar-inner']//div[3]/a");
	public static By adminInTopDD = By.xpath("//a[contains(@href,'/manage/rba')]");
	
	//	Tab
	public static By rolesndPermissionsTb = By.xpath("//a[contains(@href,'/manage/rba')]");
	
	public static By manageRolesTxt = By.xpath("//div[@id='tab1default']/div/div//h3");
	
	public static By teamsndRolesTb = By.xpath("//a[contains(@href,'/manage/troles')]");
	public static By usersTb = By.xpath("//a[contains(@href,'/manage/users')]");
	public static By settingsTb = By.xpath("//a[contains(@href,'/manage/settings')]");
	public static By reportsTb = By.xpath("//a[contains(@href,'/manage/reports')]");
	public static By chatMonitor = By.xpath("//a[contains(@href,'/chat/monitor')]");
	public static By chatReports =  By.xpath("//a[contains(@href,'/chat/reports')]");
	
	//	Roles & Permissions
	public static By roleName = By.id("role_title");
	public static By roleDescription = By.id("role_comments");
	
	public static By roleSubmitBtn = By.xpath("//form[@class='ng-valid ng-dirty']/div[3]//button");
	public static By roleClearBtn = By.xpath("//form[@class='ng-valid ng-dirty']/div[3]//input");
	public static By roleEditBtn=By.xpath("//a[contains(text(),'Edit')]");
	public static By roleDeleteBtn=By.xpath("//a[contains(text(),'Delete')]");
	public static By roleDeleteOkBtn=By.xpath("//div[8]/div/div/div[2]/button[2]");

	public static By roleManageRolesTtlCnt = By.xpath("//table[@id='roles_list_table']//tbody/tr");
	public static By roleSearchBox=By.xpath("//div[@id='roles_list_table_filter']/label/input");
	public static By roleSearchRoleNameVal=By.xpath("//table[@id='roles_list_table']/tbody/tr[1]/td[1]");
	public static By roleSearchDescriptionVal=By.xpath("//table[@id='roles_list_table']/tbody/tr[1]/td[2]");
	
	public static By roleTableTotalDDval=By.xpath("//ul[@id='role_active']/li");
	public static By roleAllowAllPermission=By.id("permission-access-all");
	public static By roleAllowText=By.xpath("//table[@id='permission_tab']/tbody/tr[2]/td[2]");
	public static By roleEditPermission=By.xpath("//table[@id='permission_tab']/thead/tr/th[3]/select/option");
	public static By roleDeletePermission=By.xpath("//table[@id='permission_tab']/thead/tr/th[4]/select/option");
	public static By roleListPermission=By.xpath("//table[@id='permission_tab']/thead/tr/th[5]/select/option");
	public static By roleDocPermission=By.xpath("//table[@id='permission_tab']/thead/tr/th[6]/select/option");
	public static By roleScroll=By.xpath("//strong[contains(text(),'Declare Disaster')]");
	
	
	//	Teams & Roles
	public static By teamNameDDArow = By.xpath("//div[@id='team_name_chzn']/a/div");
	public static By teamNameSearchBox = By.xpath("//div[@id='team_name_chzn']/div/div/input");
	public static By teamNameTotalDDVal=By.xpath("//div[@id='team_name_chzn']/div/ul/li");
	//public static By teamNameSearchBox = By.xpath("//select[@id='team_name']");
	//public static By teamNameTotalDDVal=By.xpath("//select[@id='team_name']/option");
	
	public static By roleSelectTable=By.xpath("//select[@id='team_role']");
	public static By roleTotalDDVal=By.xpath("//select[@id='team_role']/option");
	public static By teamRoleSubmitBtn=By.xpath("//button[@id='team_add_btn']");
	public static By teamTableTotalVal=By.xpath("//table[@id='team_list_table']/tbody/tr");
	public static By teamSearch=By.xpath("//div[@id='team_list_table_filter']/label/input");
	public static By teamSearchFirstVal=By.xpath("//table[@id='team_list_table']/tbody/tr[1]/td[1]");
	public static By teamSearchNoRecs=By.xpath("//table[@id='team_list_table']/tbody/tr/td");
	public static By teamRecsInfo=By.xpath("//div[@id='team_list_table_info']");
	public static By teamDeleteBtn=By.xpath("//table[@id='team_list_table']/tbody/tr/td[6]/a");
	public static By teamDeleteOkBtn=By.xpath("html/body/div[8]/div/div/div[2]/button[2]");
	
	public static By teamSubmitBtn = By.xpath("//form[@id='manage_team']/div[2]//button");
	public static By teamClearBtn = By.xpath("//form[@id='manage_team']/div[2]//input"); 

	//	Users
	public static By usrEmpNameDDArow = By.xpath("//div[@id='user_name_chzn']/a/div");
	//public static By usrEmpNameDDArow = By.xpath("//select[@id='user_name']");
	public static By usrEmpNameSearchBox = By.xpath("//div[@id='user_name_chzn']//input");
	public static By usrEmpnameTotalVal=By.xpath("//div[@id='user_name_chzn']/div/ul/li");
	//public static By usrEmpnameTotalVal=By.xpath("//select[@id='user_name']/option");
	
	public static By usrRoleSelectTable=By.xpath("//select[@id='rrole_name']");
	public static By usrRoleTotalVal=By.xpath("//select[@id='rrole_name']/option");
	
	public static By usrTeamSelectTable=By.xpath("//select[@id='rteam_name']");
	public static By usrTeamTotalval=By.xpath("//select[@id='rteam_name']/option");
	
	public static By usrSubmitBtn = By.xpath("//button[@id='user_add_btn']");
	public static By usrClearBtn = By.xpath("//form[@id='manage_users']/div[2]//input");
	public static By usrTableTotalval=By.xpath("//table[@id='user_list_table']/tbody/tr");
	public static By usrSearchBox=By.xpath("//div[@id='user_list_table_filter']/label/input");
	public static By usrTableFirstVal=By.xpath("//table[@id='user_list_table']/tbody/tr[1]/td[1]");
	public static By usrTableSecColVal=By.xpath("//table[@id='user_list_table']/tbody/tr[1]/td[2]");
	public static By usrRecsInfo=By.xpath("//div[@id='user_list_table_info']");
	public static By usrDeleteBtn=By.xpath("//table[@id='user_list_table']/tbody/tr/td[8]/a");
	public static By usrStatus=By.xpath("//table[@id='user_list_table']/tbody/tr/td[7]/a");
	public static By usrStatusSelectTable=By.xpath("//table[@id='user_list_table']/tbody/tr/td[7]/select");
	//public static By usrStatusTotalVal=By.xpath("//table[@id='user_list_table']/tbody/tr/td[7]/select/option");
	public static By usrStatusValue=By.xpath("//table[@id='user_list_table']/tbody/tr/td[7]");
	
	//public static By usrDeleteOkBtn=By.xpath("html/body/div[8]/div/div/div[2]/button[2]");
	
	//	Settings
	public static By stOrganizationName = By.name("org_name");
	public static By stTagline = By.name("tag_line");
	public static By stNotificationFromEmailAddress = By.id("from_email_id");
	public static By stReplyToEmailAddress = By.id("reply_email_id");
	public static By stCountryDDArow = By.xpath("//div[@id='country_chzn']/a/span");
	public static By stCountrySearchBox = By.xpath("//div[@id='country_chzn']/div/div/input");
	public static By stCountryTotalVal=By.xpath("//div[@id='country_chzn']/div/ul/li");
	public static By stCurrencyCode = By.id("currency_code");
	public static By stTimeZoneDDArow = By.xpath("//div[@id='time_zone_chzn']/a/span");
	public static By stTimeZoneSearchBox = By.xpath("//div[@id='time_zone_chzn']/div/div/input"); 
	public static By stTimeZoneTotalVal=By.xpath("//div[@id='time_zone_chzn']/div/ul/li");
	public static By stEmailChkBox = By.id("email_log");
	public static By stSMSChkBox = By.id("sms_log");
	public static By stSubmitBtn=By.xpath("//button[@id='settings_submit']");
	public static By stChoseUploadLogo=By.xpath("//input[@id='logo_img']");
	public static By stUpldLogoSubmitBtn=By.xpath("//button[@id='_submit']");
	
	//         Email/SMS Reports
	public static By repSmsTableVal=By.xpath("//table[@id='sms_table']/tbody/tr");
	public static By repSmsSearchBox=By.xpath("//div[@id='sms_table_filter']/label/input");
	public static By repSmsTableSearchFirstVal=By.xpath("//table[@id='sms_table']/tbody/tr[1]/td[1]");
	public static By repSmsNoRecords=By.xpath("//table[@id='sms_table']/tbody/tr/td");
	public static By repCalendarIcon=By.cssSelector("i.icon-calendar");
	public static By repSelectDateIndd=By.xpath("//div[8]/div[1]/ul/li[4]");
	
	public static By repSmsListViewPagination=By.xpath("//div[@id='sms_table_wrapper']/div[2]/div[2]/div/ul/li");
	public static By repSmsListViewRecsInfo=By.xpath("//div[@id='sms_table_info']");
	public static By repSmsListViewLength=By.name("sms_table_length");
	
	
	public static By repEmailListViewRecsInfo=By.xpath("//div[@id='email_table_info']");
	public static By repEmailListViewPagination=By.xpath("//div[@id='email_table_wrapper']/div[2]/div[2]/div/ul/li");
	public static By repEmailListViewLength=By.name("email_table_length");
	
	public static By repEmailTableVal=By.xpath("//table[@id='email_table']/tbody/tr");
	public static By repEmailTableSearchFirstVal=By.xpath("//table[@id='email_table']/tbody/tr[1]/td[1]");
	public static By repEmailSearchBox=By.xpath("//div[@id='email_table_filter']/label/input");
	public static By repEmailNoRecords=By.xpath("//table[@id='email_table']/tbody/tr/td");
	
	public static By repEmailPageStartArrow=By.xpath("//div[@id='email_table_wrapper']/div[2]/div[2]/div/ul/li[1]/a");
	public static By repEmailPrevArrow=By.xpath("//div[@id='email_table_wrapper']/div[2]/div[2]/div/ul/li[2]/a");
	
	public static By chatHide=By.xpath("//div[@id='cometchat_hide']");
	
	
	
	// Chat Monitor
	public static By chtMonSearchBox=By.xpath("//div[@id='chat_history_filter']/label/input");
	public static By chtMonTableVal=By.xpath("//table[@id='chat_history']/tbody/tr");	
	public static By chtMonTableSearchFirstVal=By.xpath("//table[@id='chat_history']/tbody/tr[1]/td[4]");
	public static By chtMonInvalidSearchVal=By.xpath("//table[@id='chat_history']/tbody/tr/td");
	
	public static By chtMonListViewRecsInfo=By.xpath("//div[@id='chat_history_info']");
	public static By chtMonListViewPagination=By.xpath("//div[@id='chat_history_wrapper']/div[4]/div[2]/div/ul/li");
	public static By chtMonListViewLength=By.name("chat_history_length");
	
	//Chat Reports
	
	//public static By chtRepSelectDateIndd=By.xpath("//div[8]/div[1]/ul/li[4]");
	public static By chtRepTableVal=By.xpath("//table[@id='chat_reports']/tbody/tr");
	public static By chtRepSearchbox=By.xpath("//div[@id='chat_reports_filter']/label/input");
	public static By chtRepTableSearchFirstVal=By.xpath("//table[@id='chat_reports']/tbody/tr[1]/td[1]");
	public static By chtRepViewBtn=By.cssSelector("a.viewable");
	
	public static By chtRepChtRmListViewPagination=By.xpath("//div[@id='chat_reports_wrapper']/div[4]/div[2]/div/ul/li");
	public static By chtRepChtRmListViewRecsInfo=By.xpath("//div[@id='chat_reports_info']");
	public static By chtRepChtRmListViewLength=By.name("chat_reports_length");

	public static By chtRepNestedSearchBox=By.xpath("//div[@id='nested_table_filter']/label/input");
	public static By chtRepNestedTableVal=By.xpath("//table[@id='nested_table']/tbody/tr");
	public static By chtRepNestedTableFirstVal=By.xpath("//table[@id='nested_table']/tbody/tr/td[1]");
	public static By chtRepNestedNoRecords=By.xpath("//table[@id='nested_table']/tbody/tr/td");
	public static By chtRepCloseBtn=By.xpath("//a[contains(text(),'Close')]");
	
	public static By chtRepChtRmNestedListViewPagination=By.xpath("//div[@id='nested_table_wrapper']/div[2]/div[2]/div/ul/li");
	public static By chtRepChtRmNestedListViewRecsInfo=By.xpath("//div[@id='nested_table_info']");
	public static By chtRepChtRmNestedListViewLength=By.name("nested_table_length");
	
	// Individual user
	public static By chtRepIndiUser=By.xpath(".//*[@id='c_reports']/label[2]/input");
	public static By chtRepIndiUserSelectDateIndd=By.xpath("//div[9]/div[1]/ul/li[4]");
	

}
