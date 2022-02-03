package ObjectRepository;


import org.openqa.selenium.By;

public class Locations {
	
	public static String noMatchingRecordsFound = "No matching records found";
	
	public static By locationsInMainMenu = By.xpath("//a[contains(@href,'/facilities')]");
	
	public static By locOvrInfoBtn = By.cssSelector("a.btn.btn-help.btn-small.Locations-help");
			//By.cssSelector("a.Locations-help");


	public static By locOvrAddBtn = By.xpath("html/body/div[4]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/p/a[2]");	
	//public static By locOvrMassEditBtn = By.id("mass_update");
	public static By locOvrMassEditBtn=By.xpath("//a[@id='mass_update'][@class='btn btn-importcsv btn-small']");
	public static By locOvrDeleteBtn = By.xpath("//a[@class='btn btn-documents btn-small']");
	public static By locOvrImportCSVBtn = By.xpath("//a[@class='btn btn-importcsv btn-small act']");
	public static By locOvrExportListAsCSVBtn = By.id("export");
	public static By locOvrShowHideBtn = By.cssSelector("a.btn-minimize");
	
	public static By locLocationHelpPp = By.id("Locations-help");
	
	public static By locLocationHelpPpCloseBtn = By.xpath("//div[@id='Locations-help']//button");
	
	
	//	LOCATIONS LIST VIEW
	public static By locLocationsLstViewTtl = By.xpath("//table[@id='facilities_table']//tbody/tr");
	
	public static By locListviewFstNameEdtBtn = By.xpath("//table[@id='facilities_table']//tr/td[12]/a");
	public static By locListviewFstNameDelBtn = By.xpath("//table[@id='facilities_table']//tr/td[12]/a[2]");
	
	public static By csvChooseFileBtn = By.id("up_fac_csv");
	public static By csvField = By.cssSelector("span.filename");
	public static By csvCancelBtn = By.xpath("//input[@class='btn act']");
	public static By csvHelpCSVFormatBtn = By.xpath("//a[contains(@href,'/data/csv/Locations.csv')]");

	public static By locSelectAllChkBox = By.id("chk_source_table");
	
	public static By locLocationsListViewFirRow = By.xpath("//table[@id='facilities_table']/tbody/tr");
	public static By locLocationLstViewFstName = By.xpath("//table[@id='facilities_table']/tbody/tr/td[2]");
	
	//public static By delConfOkBtn1 = By.xpath("html/body/div[8]/div/div/div[2]/button[2]");
	public static By delConfOkBtn=By.cssSelector("button[data-bb-handler='confirm']");
	public static By delConfCancelBtn = By.cssSelector("button[data-bb-handler='cancel']");
	public static By delConfCrossClose = By.cssSelector("button.bootbox-close-button");
	
	public static By thrHelpPpCloseBtn = By.xpath("//div[@id='Threats-help']/div[3]/button");
	
	public static By locConfirmToRemovNoOfRecordsMsgPth = By.cssSelector("div.bootbox-body");
	
	public static By paginationGrid = By.xpath("//div[@class='dataTables_paginate paging_bootstrap pagination pagination-right']/ul/li");
	public static By lastPagination = By.linkText(">>");
	public static By firstPagination = By.linkText("<<");
	public static By nextPagination = By.linkText(">");
	public static By previousPagination = By.linkText("<");
	
	public static By locListViewFstLoc = By.xpath("//table[@id='facilities_table']/tbody/tr[1]//input");
	public static By locListViewSecLoc = By.xpath("//table[@id='facilities_table']/tbody/tr[2]//input");
	
	public static By locListViewPagination = By.xpath("//div[@id='facilities_table_wrapper']//div[@class='dataTables_paginate paging_bootstrap pagination pagination-right']/ul/li");
	
	public static By locListViewLength = By.name("facilities_table_length");
	public static By locListViewRecsInfo = By.id("facilities_table_info");
	
	public static By locViewBtn = By.xpath("//table[@id='facilities_table']//tbody/tr[1]/td[12]/a[3]");
	public static By locLocationsDetailsTxtInViewPge = By.xpath("//div[@id='content']//div[2]/div//div[2]/div[4]/h5");
	
	public static By locViewDRPlanBtn = By.xpath("//table[@id='facilities_table']//tbody/tr/td[12]/a[4]");
	public static By locCreateDRPlanBtn = By.xpath("//table[@id='facilities_table']//tbody/tr/td[12]/a[5]");
	public static By locRaiseAnAlertBtn = By.xpath("//table[@id='facilities_table']//tbody/tr/td[12]/a[6]");
	public static By locDesclareDisasterBtn = By.xpath("//table[@id='facilities_table']//tbody/tr/td[12]/a[7]");
	
	
	//	ADD NEW LOCATION
	//public static By locLocationName = By.id("fac_name");
	public static By locLocationName=By.xpath("//input[@id='fac_name']");
	
	public static By locStatusDDDefTxt = By.xpath("//div[@id='fac_status_chzn']/a/span");
	public static By locStatusDDArw = By.xpath("//div[@id='fac_status_chzn']/a/div");
	public static By locStatusSearchBox = By.xpath("//div[@id='fac_status_chzn']//input");
	public static By locStatusDDCnt = By.xpath("//div[@id='fac_status_chzn']//ul/li");
	
	public static By locTypeDDDefTxt = By.xpath("//div[@id='fac_type_chzn']/a/span");
	public static By locTypeDDArw = By.xpath("//div[@id='fac_type_chzn']/a/div"); 
	public static By locTypeSearchBox = By.xpath("//div[@id='fac_type_chzn']//input");
	public static By locTypeDDCnt = By.xpath("//div[@id='fac_type_chzn']//ul/li");
	
	public static By locManagerDDDefTxt = By.xpath("//div[@id='fac_manager_chzn']/a/span");
	public static By locManagerDDArw = By.xpath("//div[@id='fac_manager_chzn']/a/div");
	public static By locManagerSearchBox = By.xpath("//div[@id='fac_manager_chzn']//input");
	public static By locManagerDDCnt = By.xpath("//div[@id='fac_manager_chzn']//ul/li");
	
	public static By locBuiltOnYear = By.id("fac_construct_date");
	public static By locBuilOnYrFldCalendarIcon = By.xpath("//div[2]/div[1]/div[2]/div/div[1]/div[2]/div[2]/form/fieldset/div[2]/div/div/div[1]/div/div/div/span");
	public static By locLocationId = By.id("fac_reference_id");
	public static By locAddress = By.id("fac_address");
	public static By locPhone=By.xpath("//input[@id='fac_phone']");
	public static By locFax=By.xpath("//input[@id='fac_fax']");
	
	public static By locCountryDDDefTxt = By.xpath("//div[@id='fac_country_chzn']/a/span");
	public static By locCountryDDArw = By.xpath("//div[@id='fac_country_chzn']/a/div");
	public static By locCountrySearchBox = By.xpath("//div[@id='fac_country_chzn']//input");
	public static By locCountryTotalCnt=By.xpath("//div[@id='fac_country_chzn']/div/ul/li");
	
	public static By locStateDDDefTxt = By.xpath("//div[@id='fac_state_chzn']/a/span");	
	public static By locStateDDArw = By.xpath("//div[@id='fac_state_chzn']/a/div");
	public static By locStateSearchBox = By.xpath("//div[@id='fac_state_chzn']//input");
	public static By locStateTotalCnt=By.xpath("//div[@id='fac_state_chzn']/div/ul/li");
	
	public static By locCity = By.id("fac_city");
	public static By locZipCode = By.id("fac_zip");
	
	public static By locAltrManagerDD=By.xpath("//div[@id='fac_alt_manager_chzn']/a");
	public static By locAltrManagerSearchBox=By.xpath("//div[@id='fac_alt_manager_chzn']/div/div/input");
	public static By locAltrManagerDDCnt=By.xpath("//div[@id='fac_alt_manager_chzn']/div/ul/li");
		
	public static By locSubmitBtn = By.id("fac_submit");
	public static By locClearBtn = By.xpath("//form[@id='facilities_form']//button[2]");
	public static By locCancelBtn = By.xpath("//form[@id='facilities_form']//button[3]");
	
	public static By msgNotificationBar = By.cssSelector("div.notify-message-bar");
	public static By stepValidatnMsg = By.cssSelector("div.step-validation-msg");			
	
	public static By locLocationsSearchBox = By.cssSelector("input[aria-controls='facilities_table']");
	
	
	//	EDIT LOCATION
	public static By locEdtStatus = By.xpath("//div[@id='fac_status_chzn']/a/span");
	public static By locEdtType = By.xpath("//div[@id='fac_type_chzn']/a/span");
	public static By locEdtManager = By.xpath("//div[@id='fac_manager_chzn']/a/span");
	public static By locEdtCountry = By.xpath("//div[@id='fac_country_chzn']/a/span");
	public static By locEdtState = By.xpath("//div[@id='fac_state_chzn']/a/span");
	
	public static By locEdtStatusArrow = By.xpath("//div[@id='fac_status_chzn']/a/div");
	public static By locEdtTypeArrow = By.xpath("//div[@id='fac_type_chzn']/a/div");
	public static By locEdtManagerArrow = By.xpath("//div[@id='fac_manager_chzn']/a/div");
	public static By locEdtCountryArrow = By.xpath("//div[@id='fac_country_chzn']/a/div");
	public static By locEdtStateArrow = By.xpath("//div[@id='fac_state_chzn']/a/div");
	
	public static By locEdtStatusSrchBox = By.xpath("//div[@id='fac_status_chzn']/div/div/input");
	public static By locEdtTypeSrchBox = By.xpath("//div[@id='fac_type_chzn']/div/div/input");
	public static By locEdtManagerSrchBox = By.xpath("//div[@id='fac_manager_chzn']/div/div/input");
	public static By locEdtCountrySrchBox = By.xpath("//div[@id='fac_country_chzn']/div/div/input");
	public static By locEdtStateSrchBox = By.xpath("//div[@id='fac_state_chzn']/div/div/input");
	
	public static By locEdtSubmitBtn = By.id("fac_submit");	
	
	
	//	RELATIONSHIP
	public static By rltnHeader=By.cssSelector("#show-lbl-rel");
	public static By rltnEmployess = By.id("resources");
	public static By rltnThreats = By.id("threats");
	public static By rltnAssets = By.id("assets");
	public static By rltnInsurance = By.id("insurance");
	public static By rltnBusinessFunctions = By.id("process");
	public static By rltnDocuments = By.id("documents");
	
	public static By rltnPopupCloseBtn = By.cssSelector("a[data-dismiss='modal']");
	public static By rltnPopupSubmitBtn = By.id("post_relationship");
	
	public static By rltnAddRemoveRltnDisabled=By.cssSelector("a[class='btn btn-small'][disabled='disabled']");
	public static By rltnAddRemoveRltnsBtn = By.cssSelector("p.relation_link");		
	public static By rltnEmployeeNameClmn = By.xpath("//div[2]/div[1]/div[2]/div/div[2]/div[2]/div/div[1]/div/div/table/tbody/tr[2]/td[1]/span");
	
	public static By noRecordsFoundMsg = By.cssSelector("td.dataTables_empty");
	
		
	//	MAPPING POP UP
	public static By mapMappingPopup = By.id("MapModal");
	
	public static By mapListOfEmployeesSearchBox = By.cssSelector("input.filter");
	public static By mapMoveAllArrow = By.cssSelector("button.moveall");
	public static By mapMoveArrow = By.cssSelector("button.move");
	
	public static By mapRemoveAllArrow = By.cssSelector("button.removeall");
	public static By mapRemoveArrow = By.cssSelector("button.remove");
	
	public static By mapSubmitBtn = By.id("post_relationship");
	public static By mapCloseBtn = By.cssSelector("a[data-dismiss='modal']");
	public static By mapOkBtn=By.cssSelector("button[data-bb-handler='success']");
	
	public static By mapPopupLftSearchBox = By.xpath("//div[@id='map_box']/div/div/input");
	public static By mapPopupRgtSearchBox = By.xpath("//div[@id='map_box']/div/div[2]/input");
	
	public static By mapPopupLftBox = By.name("map_list[]_helper1");
	public static By mapPopupLftTtlVal = By.xpath("//select[@id='bootstrap-duallistbox-nonselected-list_map_list[]']/option");
	public static By mapPopupRgtTtlVal = By.xpath("//select[@id='bootstrap-duallistbox-selected-list_map_list[]']/option");
	public static By mapPopupRgtBox = By.name("map_list[]_helper2");
	
	public static By mapListOfTaskFld = By.xpath("//div[3]/div[1]/div[3]/div[2]/form/div/div[1]/div/div[1]/input");
	public static By mapMappedTaskFld = By.xpath("//div[3]/div[1]/div[3]/div[2]/form/div/div[1]/div/div[2]/input");
	
	public static By mapPopupFirstNameInLftBox = By.xpath("//select[@name='map_list[]_helper1']/option");
	public static By mapPopupFirstNameInRgtBox = By.xpath("//select[@name='map_list[]_helper2']/option");
	
	public static By mapMappingPpTitleNm = By.xpath("//div[@id='MapModal']/div/h3");
	
	public static By locRltnEmployeesSearchBox  = By.xpath("//div[@id='rel_resources_filter']//input");
	public static By locRltnEmployeesSrchRslt  = By.xpath("//table[@id='rel_resources']//tr/td");
	public static By locRltnEmployeesCnt = By.xpath("//table[@id='rel_resources']/tbody/tr");
	
	public static By locRltnThrNmCnt = By.xpath("//table[@id='rel_threats']/tbody/tr");
	public static By locRltnThrNmSearchBox = By.xpath("//div[@id='rel_threats_filter']//input");
	public static By locRltnThrNmSrchRslt = By.xpath("//table[@id='rel_threats']//tr/td");
	
	public static By locRltnAssetsCnt = By.xpath("//table[@id='rel_assets']/tbody/tr");
	public static By locRltnAssetsSearchBox = By.xpath("//div[@id='rel_assets_filter']//input");
	public static By locRltnAssetsSrchRslt  = By.xpath("//table[@id='rel_assets']//tr/td");
	
	public static By locRltnBusinesFuncCnt = By.xpath("//table[@id='rel_processes']/tbody/tr");
    public static By locRltnBusinesFuncSearchBox = By.xpath("//div[@id='rel_processes_filter']//input");
    public static By locRltnBusinessFuncSearchRslt = By.xpath("//table[@id='rel_processes']//tr/td");
    
    public static By locRltnInsCnt = By.xpath("//table[@id='rel_insurances']/tbody/tr");
    public static By locRltnInsSearchBox = By.xpath("//div[@id='rel_insurances_filter']//input ");
    public static By locRltnInsSrchRslt = By.xpath(" //table[@id='rel_insurances']//tr/td ");
    
    public static By locRltnEmergCont = By.xpath("//table[@id='rel_contacts']/tbody/tr");
    public static By locRltnEmergContSearchBox = By.xpath("//div[@id='rel_contacts_filter']//input");
    public static By locRltnEmergContSrchRslt = By.xpath("//table[@id='rel_contacts']//tr/td");
    
    public static By locRltnTeamsCnt=By.xpath("//table[@id='rel_resourcegroup']/tbody/tr");
    public static By locRltnTeamsSearchBox=By.xpath("//div[@id='rel_resourcegroup_filter']/label/input");
    public static By locRltnTeamsSearchRslt=By.xpath("//table[@id='rel_resourcegroup']//tr/td");
    
    
    //		DIFFERENT LOCATION POP UP
    public static By locEmpDiffLocatnPp = By.xpath("//div[@class='bootbox modal fade in']/div");
    public static By locEmpDiffLocatnMsg = By.xpath("//div[@class='bootbox modal fade in']//h4");
    //public static By locEmpDiffLocatnPpOkBtn = By.xpath("//div[@class='bootbox modal fade in']//button");
    //public static By locEmpDiffLocatnPpOkBtn =By.cssSelector("button[data-bb-handler='success']");
    public static By locEmpDiffLocatnPpOkBtn = By.xpath("//div[@class='bootbox modal fade in']/div/div/div[3]/button");
    public static By locEmpDiffLocatnPpCnt = By.xpath("//div[@class='bootbox modal fade in']//ul/li");
	
	//	RELATIONSHIP TABS
	public static By tasksTb = By.id("task");
	public static By employeesTb = By.id("resources");
	public static By threatsTb = By.id("threats");
	
	public static By assetsTb = By.id("assets");
	public static By insuranceTb = By.id("insurance");	
	public static By businessFunctionsTb = By.id("process");
	public static By teamsTb = By.id("resourcegroups");	
		
	public static By documentsTb = By.id("documents");
	public static By locationsTb = By.id("facilities");
	public static By taskGroupsTb = By.id("task_group");
	public static By assetGroupsTb = By.id("asset_group");
	public static By contactTb = By.id("contacts");	
	public static By contactGroupsTb = By.id("contact_group");
	public static By empInsuranceTb = By.id("insurances");
	
	public static By astContactsTb = By.id("contact");
	public static By cntContactGroupsTb = By.id("contactgrp");
	public static By tskTeamsTb = By.id("resource_group");
	public static By tskContactsTb = By.id("contacts");
	public static By tskcontactGroupsTb = By.id("contact_group");
	public static By emergencycontactsTb = By.xpath(".//*[@id='contacts']/a/span[2]");
	public static By otherRelationTb=By.xpath("//li[@id='other_relation']");
	
		
	//	GOOGLE MAP
	public static By goglMapZoomInBtn = By.cssSelector("div[title='Zoom in']");
	public static By goglMapZoomOutBtn = By.cssSelector("div[title='Zoom out']");
	
	public static By gogleSatelliteViewBtn = By.cssSelector("div[title='Show satellite imagery']");
	public static By goglStreetViewBtn = By.cssSelector("div[title='Show street map']");
	
	public static By goglMapCloseBtn = By.cssSelector("i.icon-remove"); 
	
	public static By goglLabelsChkBox = By.cssSelector("div[title='Show imagery with street names']");
	
	public static By goglDragging = By.xpath("//div[@id='g-map-show']/div/div/div[2]/div");
			
	
	//	MASS EDIT
	public static By locmEdtStatusDDArw = By.xpath("//div[@id='facility_status_chzn']/a/div");
	public static By locmEdtStatusSearchBox = By.xpath("//div[@id='facility_status_chzn']//input");
	public static By locmEdtStatusCnt = By.xpath("//div[@id='facility_status_chzn']//ul/li");
	
	public static By locmEdtTypeDDArw = By.xpath("//div[@id='facility_type_chzn']/a/div");
	public static By locmEdtTypeSearchBox = By.xpath("//div[@id='facility_type_chzn']//input");
	public static By locmEdtTypeCnt = By.xpath("//div[@id='facility_type_chzn']//ul/li");
	
	public static By locmEdtManagerDDArw = By.xpath("//div[@id='facility_manager_chzn']/a/div");
	public static By locmEdtManagerSearchBox = By.xpath("//div[@id='facility_manager_chzn']//input");
	public static By locmEdtManagerCnt = By.xpath("//div[@id='facility_manager_chzn']//ul/li");	
	
	public static By locmEdtCountryDDArw = By.xpath("//div[@id='facility_country_chzn']/a/div");
	public static By locmEdtCountrySearchBox = By.xpath("//div[@id='facility_country_chzn']//input");
	public static By locmEdtCountryCnt = By.xpath("//div[@id='facility_country_chzn']//ul/li");
	
	public static By locmEdtStateDDArw = By.xpath("//div[@id='facility_state_chzn']/a/div");
	public static By locmEdtStateSearchBox = By.xpath("//div[@id='facility_state_chzn']//input");
	public static By locmEdtStateCnt = By.xpath("//div[@id='facility_state_chzn']//ul/li");
	
	public static By locmEdtZipCode = By.id("facility_zip");
	public static By locmEdtCity = By.id("facility_city");
	
	public static By locmEdtSubmitBtn = By.id("facility_submit");
	public static By locmEdtClearBtn = By.xpath("//form[@id='facilities_mass_update_form']//button[2]");
	public static By locmEdtCancelBtn = By.xpath("//form[@id='facilities_mass_update_form']//button[3]");
	
	//	IMPORT DATA
	public static By locImptCancelBtn = By.xpath("//div[3]/div[1]/div[2]/div/div/div[1]/div[2]/div[4]/form/div[1]/div/div/div/div/input");
	//public static By locImptHelpCSVBtn = By.xpath("//a[contains(@href,'/data/csv/csvtemplate.xls')]");
	public static By locImptHelpCSVBtn=By.xpath("//form[@id='csv_facilities_form']/div[1]/div/div/div/div/a/button");
	public static By locSelectCSVFileFld = By.id("up_fac_csv");
	
	
	
	//	MAIN MENU
	public static By mainAssetsOptn = By.xpath("html/body/div[4]/div[1]/div[1]/div[1]/div/ul/li[7]");
	public static By mainLocationsOptn=By.xpath("html/body/div[4]/div[1]/div[1]/div[1]/div/ul/li[3]");
	public static By mainThreatsOptn=By.xpath("html/body/div[4]/div[1]/div/div[1]/div/ul/li[4]");
	public static By mainEmployeesOptn=By.xpath("html/body/div[4]/div[1]/div[1]/div[1]/div/ul/li[5]");
	public static By mainTeamsOptn=By.xpath("html/body/div[4]/div[1]/div[1]/div[1]/div/ul/li[6]");
	public static By mainAssetGroupsOptn=By.xpath("html/body/div[4]/div[1]/div[1]/div[1]/div/ul/li[8]");
	public static By mainContactsOptn=By.xpath("html/body/div[4]/div[1]/div[1]/div[1]/div/ul/li[9]");
	public static By mainContactGroupsOptn=By.xpath("html/body/div[4]/div[1]/div/div[1]/div/ul/li[10]");
	public static By mainTasksOptn=By.xpath("html/body/div[4]/div[1]/div[1]/div[1]/div/ul/li[12]");
    public static By mainTaskGroupsOptn=By.xpath("html/body/div[4]/div[1]/div[1]/div[1]/div/ul/li[13]");
    public static By mainInsuranceOptn=By.xpath("html/body/div[4]/div[1]/div[1]/div[1]/div/ul/li[11]");
    public static By mainBusinessFunctionsOptn=By.xpath("html/body/div[4]/div[1]/div[1]/div[1]/div/ul/li[14]");
    public static By mainDRPlanOptn=By.xpath("html/body/div[4]/div[1]/div/div[1]/div/ul/li[15]");
    public static By mainManageDisasterOptn=By.xpath("html/body/div[4]/div[1]/div/div[1]/div/ul/li[16]");
	
	// Import
	
	public static By locImportBtn = By.cssSelector("a[class = 'btn btn-importcsv btn-small act']");
	public static By locImportUploadBtn = By.id("up_fac_csv");
	public static By locImportSubmitBtn = By.id("csv_fac_submit");
	public static By locImportCancelBtn = By.xpath("//form[@id='csv_facilities_form']/div[1]/div/div/div/div/input[1]");
	public static By locImportHelpDocBtn = By.xpath("//form[@id='csv_facilities_form']/div[1]/div/div/div/div/a/button");
	
	//Export
	
	public static By locExportBtn = By.xpath("html/body/div[4]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/p/a[5]");
	
	//View
	public static By viewLocName=By.xpath("//div[@id='v_fac_name']");
	public static By viewLocStatus=By.xpath("//div[@id='v_fac_status']");
	public static By viewLocType=By.xpath("//div[@id='v_fac_type']");
	public static By viewLocManager=By.xpath("//div[@id='v_fac_manager']");
	public static By viewLocBuilt=By.xpath("//div[@id='v_fac_date']");
	public static By viewLocId=By.xpath("//div[@id='v_fac_location']");
	public static By viewLocAddr=By.xpath("//div[@id='v_fac_address']");
	public static By viewLocPh=By.xpath("//div[@id='v_fac_phone']");
	public static By viewLocFax=By.xpath("//div[@id='v_fac_fax']");
	public static By viewLocCntry=By.xpath("//div[@id='v_fac_country']");
	public static By viewLocState=By.xpath("//div[@id='v_fac_state']");
	public static By viewLocCity=By.xpath("//div[@id='v_fac_city']");
	public static By viewLocZipCode=By.xpath("//div[@id='v_fac_zip']");
	public static By viewLocAltMngr=By.xpath("//div[@id='v_fac_alt_manager']");
	
	
	// relationship table 
	
    public static By threatsTabRelationSection = By.xpath("//span[@class='hidden-phone hidden-tablet' and contains(text(),'Threats')]");
    public static By rightMoveAllBtnThreatRltWindow = By.xpath("//label[contains(text(),'Mapped Threats')]/..//button[@class='btn removeall']");
    public static By threatsLeftRltWindowValues = By.xpath("//label[contains(text(),'List of Threats')]/..//button[@class='btn moveall']/../../select//option");
    public static By threatsRightMoveAllBtnRltWindow = By.xpath("//label[contains(text(),'Mapped Threats')]/..//button[@class='btn removeall']");
    public static By threatsLeftSearchTxtBoxRltWindow = By.xpath("//label[contains(text(),'List of Threats')]/../input[@class='filter']");
    public static By threatsLeftMoveBtnRltWindow = By.xpath("//label[contains(text(),'List of Threats')]/..//button[@class='btn move']");
    public static By threatsRightSearchTxtBoxRltWindow = By.xpath("//label[contains(text(),'Mapped Threats')]/../input[@class='filter']");
    public static By threatsViewBtnRltWindow = By.xpath("//table[@id='rel_threats']//a[@class='btn btn-small btn-primary btn-nestable-action']");
    public static By threatsAddRemoveBtnInnerRltTable = By.xpath("//table[@id='rel_threats']//a[@class='label btn-primary btn-addremove-nrelation']");
    public static By threatsLeftTabsInnerRltTable = By.xpath("//table[@id='rel_threats']//div[contains(@class,'span12 module-tabitem')]");
    public static By threatsSearchTxtBoxInnerRltTable = By.xpath("//table[@id='rel_threats']//input[@class='filter']");
    public static By threatsLeftMoveBtnInnerRltTable = By.xpath("//table[@id='rel_threats']//button[@class='btn move']");
    public static By threatsRightMoveAllArrowInnerRltTable = By.xpath("//table[@id='rel_threats']//button[@class='btn removeall']");
    public static By threatsLeftRltTableValues = By.xpath("//table[@id='rel_threats']//select[@name='map_list[]_helper1']/option");
    public static By threatsRightRltTableValues = By.xpath("//table[@id='rel_threats']//select[@name='map_list[]_helper2']/option");
    public static By threatsSubmitBtnInnerRltTable = By.xpath("//table[@id='rel_threats']//a[@class='btn btn-small btn-primary btn-save-nrelation']");
    public static By threatsTableValueAfterMapping = By.xpath("//table[@id='rel_threats']//tbody[@role='alert']//tr[@class='tableRow odd']//td[1]");
    
    // Location view screen
    
    public static By viewScreenValues=By.xpath("//div[@id='showdisplay_view']//div[@class='control-group']//div[@class='controls view_content']");
    public static By closeButton=By.xpath("//button[@class='btn validate-cancel {facilities_form}'][contains(text(),'Close')]");
    public static By locationViewButton=By.xpath("//a[@title='View']");
    public static By viewScreenValuesWithColumnNames=By.xpath("//div[@id='showdisplay_view']//div[@class='control-group']");
    public static By loggedInUserName=By.xpath("//a[@class='btn dropdown-toggle']/span[2][@class='hidden-phone hidden-tablet']");
}


