package ObjectRepository;

import org.openqa.selenium.By;

public class Teams {
	
	public static By teamsInMainMenu = By.xpath("//a[contains(@href,'/resourcegroup')]");
	
	public static By tmsOvrInfoBtn = By.cssSelector("a.btn.btn-help.Teams-help");
	public static By tmsOvrAddBtn = By.xpath("//a[@class='btn btn-help btn-small act']");
	public static By tmsOvrDeleteBtn = By.xpath("//div[@id='content']/div[2]/div[1]/div[1]/p/a[5]");
	public static By tmsOvrExportBtn = By.id("export");
	public static By tmsOvrImportBtn=By.xpath("//div[@id='content']/div[2]/div[1]/div[1]/p/a[4]");
	
	public static By tmsTeamHelpDoc = By.id("Teams-help");
	public static By tmsTeamHelpPpCloseBtn = By.xpath("//div[@id='Teams-help']/div[3]/button");
	
	//	TEAM LIST VIEW
	public static By tmsTeamsLstViewTtl = By.xpath("//table[@id='resourcegroup_table']//tr");
	
	public static By tmsLstviewFstEdtBtn = By.xpath("//table[@id='resourcegroup_table']//tr/td[9]/a");
	public static By tmsLstviewFstDelBtn = By.xpath("//table[@id='resourcegroup_table']//tr/td[9]/a[2]");
	
	public static By tmsSelectAllChkBox = By.id("chk_source_table");
	
	public static By tmsTeamsSearchBox = By.xpath("//div[@id='resourcegroup_table_filter']//input");
	public static By tmsLstViewFstName = By.xpath("//table[@id='resourcegroup_table']/tbody/tr/td[2]");
	public static By tmsLstViewSecName = By.xpath("//table[@id='resourcegroup_table']/tbody/tr[2]/td[2]");
	
	public static By tmsLstViewFstNmFrMEdt = By.xpath("//table[@id='resourcegroup_table']/tbody/tr[1]/td[1]/div/span");
	public static By tmsLstViewSecNmFrMEdt = By.xpath("//table[@id='resourcegroup_table']/tbody/tr[2]/td[1]/div/span");
	
	public static By tmsLstViewFstNameEdtBtn = By.xpath("//table[@id='resourcegroup_table']/tbody/tr/td[9]/a[1]");
	
	public static By tmsListViewPagination = By.xpath("//div[3]/div[1]/div[2]/div/div/div[1]/div[2]/div[2]/div/form/div/div/div[2]/div[2]/div/ul/li");
	
	public static By tmsListViewLength = By.name("resourcegroup_table_length");
	public static By tmsListViewRecsInfo = By.id("resourcegroup_table_info");
	
	public static By tmsLstViewSrchRslt = By.xpath("//table[@id='resourcegroup_table']//tr/td");
	
	public static By tmsViewBtn = By.xpath("//table[@id='resourcegroup_table']/tbody/tr/td[9]/a[3]");
	public static By tmsViewPopup = By.xpath("//div[@id='showdisplay_view']");
	public static By tmsViewCloseBtn = By.xpath("//div[@id='showdisplay_view']/feildset/div[3]/div/div/div/div/div/div[2]/button");
	
	
	
	
	//	ADD NEW TEAM
	public static By tmsTeamName = By.id("name");
	
	public static By tmsTeamLeaderArrow = By.xpath("//div[@id='group_leader_chzn']/a/div");
	public static By tmsTeamLeaderdd = By.xpath("//div[@id='group_leader_chzn']/a");
	public static By tmsTeamLeaderDefTxt = By.xpath("//div[@id='group_leader_chzn']/a/span");
	public static By tmsTeamLeaderSearchBox = By.xpath("//div[@id='group_leader_chzn']//input");
	public static By tmsTeamLeaderCnt = By.xpath("//div[@id='group_leader_chzn']//ul/li");
	
	public static By tmsResponsibility = By.id("responsibility");
	
	public static By tmsTeamTypeArrow = By.xpath("//div[@id='team_type_chzn']/a/div");
	public static By tmsTeamTypedd = By.xpath("//div[@id='team_type_chzn']/a");
	public static By tmsTeamTypeDefTxt = By.xpath("//div[@id='team_type_chzn']/a/span");

	public static By tmsTeamTypeSearchBox = By.xpath("//div[@id='team_type_chzn']//input");
	public static By tmsTeamTypeCnt = By.xpath("//div[@id='team_type_chzn']//ul/li");
		
	public static By tmsLocationArrow = By.xpath("//div[@id='location_chzn']/a/div");
	public static By tmsLocationdd = By.xpath("//div[@id='location_chzn']/a");
	public static By tmsLocationDefTxt = By.xpath("//div[@id='location_chzn']/a/span");
	public static By tmsLocationSearchBox = By.xpath("//div[@id='location_chzn']//input");
	public static By tmsLocationCnt = By.xpath("//div[@id='location_chzn']//ul/li");
	
	public static By tmsAltrEmpArrow=By.xpath("//div[@id='alternate_group_leader_chzn']/a");
	public static By tmsAltrEmpCnt=By.xpath("//div[@id='alternate_group_leader_chzn']/div/ul/li");
	public static By tmsAltrEmpSearchBox=By.xpath("//div[@id='alternate_group_leader_chzn']/div/div/input");	
	
	
	public static By tmsCommentsField = By.xpath("//html/body/p");
	
	public static By tmsSubmitBtn = By.xpath("//button[@id='rgp_submit']");
	public static By tmsClearBtn = By.xpath("//form[@id='rgp_form']//button[2]");
	public static By tmsCancelBtn = By.xpath("//form[@id='rgp_form']//button[3]");
	
	public static By tmsEditBtn = By.xpath("//table[@id='resourcegroup_table']/tbody/tr/td[9]/a[1]");
	public static By tmsDeleteBtn = By.xpath("//table[@id='resourcegroup_table']/tbody/tr/td[9]/a[2]");
	
	
	//	Relationship
	public static By rltnAddRemoveRelationsBtn = By.cssSelector("a[onclick='load_map_data();']");
	
	public static By rltnEmployeesListView = By.id("rel_resources");
	public static By rltnEmpTblTotalRows = By.xpath("//table[@id='rel_resources']/tbody/tr");
	
	public static By rltnEmpListviewCnt = By.xpath("//table[@id='rel_resources']//tbody/tr");
	
	public static By TmsRltnEmployeesCnt = By.xpath("//table[@id='rel_resources']/tbody/tr");
	public static By TmsRltnEmployeesSearchBox = By.xpath("//div[@id='rel_resources_filter']//input");
	public static By TmsRltnEmployeesSrchRslt = By.xpath("//table[@id='rel_resources']//tr/td");
	
	public static By TmsRltnTasksCnt = By.xpath("//table[@id='rel_task']//tr");
	public static By TmsRltnTaskSearchbox =  By.xpath("//input[@aria-controls='rel_task']");
	public static By TmsRltnTaskSearchRslt = By.xpath("//table[@id='rel_task']//tr/td");
	
	public static By TmsRltnLocationsSearchBox = By.xpath("//div[@id='rel_facilities_filter']//input");	
	public static By TmsRltnLocationsCnt = By.xpath("//table[@id='rel_facilities']//tr");
	public static By TmsRltnLocationsSrchRslt = By.xpath("//table[@id='rel_facilities']//tr/td");
				
	//	MASS EDIT
	public static By tmsmEdtTeamTypeDDArw = By.xpath("//div[@id='rgroup_team_type_chzn']/a/div");
	public static By tmsmEdtTeamTypeSearchBox = By.xpath("//div[@id='rgroup_team_type_chzn']//input");
	public static By tmsmEdtTeamTypeCnt = By.xpath("//div[@id='rgroup_team_type_chzn']//ul/li");
	
	public static By tmsmEdtTeamLeaderDDArw = By.xpath("//div[@id='rgroup_group_leader_chzn']/a/div");
	public static By tmsmEdtTeamLeaderSearchBox = By.xpath("//div[@id='rgroup_group_leader_chzn']//input");
	public static By tmsmEdtTeamLeaderCnt = By.xpath("//div[@id='rgroup_group_leader_chzn']//ul/li");
	
	public static By tmsmEdtAltTeamLeadDDArw=By.xpath("//div[@id='rgroup_alternate_leader_chzn']/a");
	public static By tmsEdtAltTeamleadSearchBox=By.xpath("//div[@id='rgroup_alternate_leader_chzn']/div/div/input");
	public static By tmsEdtAltTeamLeadCnt=By.xpath("//div[@id='rgroup_alternate_leader_chzn']/div/ul/li");
	
	public static By tmsmEdtLocationDDArw = By.xpath("//div[@id='rgroup_location_chzn']/a/div");
	public static By tmsmEdtLocationSearchBox = By.xpath("//div[@id='rgroup_location_chzn']/div/div/input");
	public static By tmsmEdtLocationCnt = By.xpath("//div[@id='rgroup_location_chzn']//ul/li");
	
	public static By tmsmEdtSubmitBtn = By.id("rgroup_submit");
	public static By tmsmEdtClearBtn = By.xpath("//form[@id='rgroup_mass_form']//button[2]");
	public static By tmsmEdtCancelBtn = By.xpath("//form[@id='rgroup_mass_form']//button[3]");
	
	
	//Import 
	public static By tmsUploadFile=By.xpath("//input[@id='up_rgp_csv']");
	public static By tmsImpSubmitBtn=By.xpath("//input[@id='btnCsvUpload']");
	public static By tmsImpCancelBtn=By.xpath("//form[@id='csv_resourcegroup_form']/button");
	public static By tmsHelpDocBtn=By.xpath("//form[@id='csv_resourcegroup_form']/a/button");
	
	
	//Export
	
	//public static By tmsExportBtn = By.xpath("html/body/div[4]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/p/a[5]");
	public static By tmsExportBtn=By.xpath("//a[@id='export'][@class='btn btn-importcsv btn-small']");
	public static By viewTmName=By.xpath("//div[@id='v_team_name']");
	public static By viewTmLedr=By.xpath("//div[@id='v_team_leader']");
	public static By viewTmResp=By.xpath("//div[@id='v_team_respons']");
	public static By viewTmType=By.xpath("//div[@id='v_team_type']");
	public static By viewTmLoc=By.xpath("//div[@id='v_team_location']");
	public static By viewTmCmnt=By.xpath("//div[@id='v_team_comments']");
	public static By viewTmAltrLd=By.xpath("//div[@id='v_alternate_team_leader']");
	public static By viewTmCloseBtn=By.xpath("//div[@id='showdisplay_view']/feildset/div[3]//div[2]/button");
	
	// relationship section teams
	
	public static By locationsTabRltSection = By.xpath("//span[@class='hidden-phone hidden-tablet' and contains(text(),'Locations')]");
	
	
	
	
}
