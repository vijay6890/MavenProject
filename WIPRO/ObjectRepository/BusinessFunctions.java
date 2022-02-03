package ObjectRepository;

import org.openqa.selenium.By;

public class BusinessFunctions {
	
	//public static By businessFunctionsInMainMenu=By.xpath("//i[@class='icon24-process']");
	public static By businessFunctionsInMainMenu = By.xpath("//a[contains(@href,'/process')]");
	
	public static By bsfOvrInfoBtn = By.xpath("//a[@class='btn btn-help btn-small Business-Functions-help']");
	public static By bsfOvrAddBtn = By.cssSelector("a[id='addactive'][class='btn btn-help btn-small act']");
	public static By bsfOvrMassEditBtn = By.xpath("//a[@id='mass_update'][@class='btn btn-importcsv btn-small']");
	public static By bsfOvrDeleteBtn = By.xpath("//div[@id='content']/div[2]/div[1]/div[1]/p/a[5]");
	
	public static By bsfOvrImportDataBtn = By.xpath("//a[@class='btn btn-importcsv btn-small act']");
	public static By bsfOvrExportDataBtn = By.id("export");
	
	public static By bsfHelpPopup = By.id("Business-Functions-help");
	public static By bsfHelpPopupCloseBtn = By.xpath("//div[@id='Business-Functions-help']/div[3]/button");
	
	
	//	BUSINESS FUNCTIONS LIST VIEW
	public static By bsfBusinessFunctionsSearchBox = By.cssSelector("input[aria-controls='process_table']");
	
	public static By bsfBusinesFunctionsLstViewTtl = By.xpath("//table[@id='process_table']//tbody/tr");
	
	public static By bsfLstviewFstEdtBtn = By.xpath("//table[@id='process_table']//tr/td[9]/a");
	public static By bsfLstviewFstDelBtn = By.xpath("//table[@id='process_table']//tr/td[9]/a[2]");
	
	public static By bsfLstViewSrchRslt = By.xpath("//table[@id='process_table']//tr/td[2]");
	
	public static By bsfSelectAllChkBox = By.id("chk_source_table");
	
	public static By bsfListViewFstName = By.xpath("//table[@id='process_table']//tr/td//span/input");
	public static By bsfListViewSecName = By.xpath("//table[@id='process_table']//tr[2]/td//span/input");
	
	public static By bsfFunctionName = By.id("process_name");
	public static By bsfFunctionId = By.id("process_ref_id");
	public static By bsfDepartment = By.id("process_department");
	
	public static By bsfViewBtn = By.xpath("//table[@id='process_table']/tbody/tr[1]/td[9]/a[3]");
	public static By bsfViewCloseBtn = By.xpath("//div[@id='showdisplay_view']/fieldset/div[6]/div/input");
	public static By bsfViewPopup = By.xpath("//div[@id='showdisplay_view']");
	
	/*@FindBy(xpath="//input[@id='process_department']")
	public static WebElement dept;*/
	
	
	public static By bsfPriorityDefTxt = By.xpath("//div[@id='process_priority_chzn']/a/span");
	public static By bsfPriorityArrow = By.xpath("//div[@id='process_priority_chzn']/a/div");
	public static By bsfPrioritySearchBox = By.xpath("//div[@id='process_priority_chzn']//input");
	public static By bsfPriorityCnt = By.xpath("//div[@id='process_priority_chzn']//ul/li");
	
	public static By bsfEmployeeAssignedDefTxt = By.xpath("//div[@id='process_owner_chzn']/a/span");
	public static By bsfEmployeeAssignedArrow = By.xpath("//div[@id='process_owner_chzn']/a/div");
	public static By bsfEmployeeAssignedSearchBox = By.xpath("//div[@id='process_owner_chzn']//input");
	public static By bsfEmployeeAssigneedCnt = By.xpath("//div[@id='process_owner_chzn']//ul/li");
		
	public static By bsfLossPerDay = By.id("process_lpd");
	public static By bsfMinimumEmpRequired = By.id("process_min_rsc");
	public static By bsfRecoveryTime = By.id("process_rto");
	public static By bsfObjective = By.id("process_objective");
	public static By bsfConsequences = By.id("process_consequences");
	
	public static By bsfSubmitBtn = By.id("process_submit");
	public static By bsfClearBtn = By.xpath("//input[@class='btn act-clr validate-cancel {process_form}']");
	public static By bsfCancelBtn = By.xpath("//input[@class='btn validate-cancel {process_form}'][@value='Cancel']");
	
	
	public static By bsfListViewRecsInfo = By.id("process_table_info");
	public static By bsfListViwLength = By.name("process_table_length");
	
	public static By bsfListViewPagination = By.xpath("//div[@id='process_table_wrapper']//div/div[2]/div/ul/li");
	
	public static By bsfRelationshipTitleNm = By.id("show-lbl-rel");
	
	//	MASS EDIT
	public static By bsfmEdtPriorityDefTxt = By.xpath("//div[@id='prs_priority_chzn']/a/span");
	public static By mEdtbsfPriorityArrow = By.xpath("//div[@id='prs_priority_chzn']/a/div");
	public static By mEdtbsfPriorityCnt = By.xpath("//div[@id='prs_priority_chzn']//ul/li");
	public static By mEdtbsfPrioritySearchBox = By.xpath("//div[@id='prs_priority_chzn']//input");
	
	public static By bsfmEdtDepartment = By.id("prs_department");
	
	public static By bsfmEdtEmployeeAssignedDefTxt = By.xpath("//div[@id='prs_owner_chzn']/a/span");
	public static By mEdtbsfEmployeeAssignedArrow = By.xpath("//div[@id='prs_owner_chzn']/a/div");
	public static By mEdtbsfEmployeeAssigneedCnt = By.xpath("//div[@id='prs_owner_chzn']//ul/li");
	public static By mEdtbsfEmployeeAssignedSearchBox = By.xpath("//div[@id='prs_owner_chzn']//input");
	
	public static By bsfmEdtSubmitBtn = By.id("prs_submit");
	public static By bsfmEdtClearBtn = By.xpath("//button[@class='btn act-clr validate-cancel {process_mass_update_form}']");
	public static By bsfmEdtCancelBtn = By.xpath("//button[@class='btn validate-cancel {process_mass_update_form}']");
	
	//	RELATIONSHIP
	public static By bsfRltnLocationsSearchBox = By.xpath("//div[@id='rel_facilities_filter']//input");	
	public static By bsfRltnLocationsCnt = By.xpath("//table[@id='rel_facilities']//tr");
	public static By bsfRltnLocationsSrchRslt = By.xpath("//table[@id='rel_facilities']//tr/td");
	
	public static By bsfRltnEmployeesSearchBox = By.xpath("//div[@id='rel_resources_filter']//input");	
	public static By bsfRltnEmployeesCnt = By.xpath("//table[@id='rel_resources']//tr");
	public static By bsfRltnEmployeesSrchRslt = By.xpath("//table[@id='rel_resources']//tr/td");

	public static By bsfRltnAssetsSearchBox = By.xpath("//div[@id='rel_assets_filter']//input");	
	public static By bsfRltnAssetsCnt = By.xpath("//table[@id='rel_assets']//tr");
	public static By bsfRltnAssetsSrchRslt = By.xpath("//table[@id='rel_assets']//tr/td");
	
	public static By bsfRltnInsuranceSearchBox = By.xpath("//div[@id='rel_insurances_filter']//input");	
	public static By bsfRltnInsuranceCnt = By.xpath("//table[@id='rel_insurances']//tr");
	public static By bsfRltnInsuranceSrchRslt = By.xpath("//table[@id='rel_insurances']//tr/td");
	
	public static By bsfRltnTasksSearchBox = By.xpath("//div[@id='rel_task_filter']//input");	
	public static By bsfRltnTasksCnt = By.xpath("//table[@id='rel_task']//tr");
	public static By bsfRltnTasksSrchRslt = By.xpath("//table[@id='rel_task']//tr/td");
	
	//Import 
	
	public static By bsfImportBtn = By.cssSelector("a[class = 'btn btn-importcsv btn-small act']");
	public static By bsfImportUploadBtn = By.xpath("//input[@id='up_pro_csv']");
	public static By bsfImportSubmitBtn = By.id("csv_process_submit");
	public static By bsfImportCancelBtn = By.xpath("//form[@id='csv_process_form']/div[1]/div/div/div/div/button");
	public static By bsfImportHelpDocBtn =By.xpath("//form[@id='csv_process_form']/div[1]/div/div/div/div/a/button");
	
	//  View
	public static By viewBsfName=By.xpath("//div[@id='v_pro_name']");
	public static By viewBsfId=By.xpath("//div[@id='v_pro_ref']");
	public static By viewBsfDept=By.xpath("//div[@id='v_pro_department']");
	public static By viewBsfprior=By.xpath("//div[@id='v_pro_priority']");
	public static By viewBsfOwnr=By.xpath("//div[@id='v_pro_owner']");
	public static By viewBsfLossPerDay=By.xpath("//div[@id='v_pro_lpd']");
	public static By viewBsfMinEmpReq=By.xpath("//div[@id='v_pro_minrec']");
	public static By viewBsfRecTime=By.xpath("//div[@id='v_pro_rto']");
	public static By viewBsfObj=By.xpath("//div[@id='v_pro_objective']");
	public static By viewBsfConseq=By.xpath("//div[@id='v_pro_consequences']");
	public static By viewBsfCmnts=By.xpath("//div[@id='v_pro_comments']");
	public static By viewBsfCloseBtn=By.xpath("//div[@id='showdisplay_view']/fieldset/div[6]/div/input");
	
	
	//Export 
	public static By bsfExportBtn = By.xpath("html/body/div[4]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/p/a[6]");
	
	// inner relationship table 
	public static By insuranceTabRltSection = By.xpath("//span[@class='hidden-phone hidden-tablet' and contains(text(),'Insurance')]");
	public static By insuranceLeftSearchTxtBoxRltWindow = By.xpath("//label[contains(text(),'List of Insurance')]/../input[@class='filter']");
	public static By insuranceLeftRltValues = By.xpath("//label[contains(text(),'List of Insurance')]/..//button[@class='btn moveall']/../../select//option");
	public static By insuranceLeftMoveArrowRltWindow = By.xpath("//label[contains(text(),'List of Insurance')]/..//button[@class='btn move']");
	public static By insuranceRightMoveAllBtnRltWindow = By.xpath("//label[contains(text(),'Mapped Insurance')]/..//button[@class='btn removeall']");
	public static By insuranceRightSearchTxtBoxRltWindow = By.xpath("//label[contains(text(),'Mapped Insurance')]/../input[@class='filter']");
	public static By insuranceViewBtnRltWindow = By.xpath("//table[@id='rel_insurances']//a[@class='btn btn-small btn-primary btn-nestable-action']");
	public static By insuranceAddRemoveBtnRltWindow = By.xpath("//table[@id='rel_insurances']//a[@class='label btn-primary btn-addremove-nrelation']");
	public static By insuranceMoveBtnInnerRltWindow = By.xpath("//table[@id='rel_insurances']//button[@class='btn move']");
	public static By insuranceRightMoveAllBtnInnerRltWindow = By.xpath("//table[@id='rel_insurances']//button[@class='btn removeall']");
	public static By insuranceSearchTxtBoxInnerRltTable = By.xpath("//table[@id='rel_insurances']//input[@class='filter']");
	public static By insuranceLeftTabsInnerRltTable = By.xpath("//table[@id='rel_insurances']//div[contains(@class,'span12 module-tabitem')]");
	public static By insuranceSubmitBtninnerRltTable = By.xpath("//table[@id='rel_insurances']//a[@class='btn btn-small btn-primary btn-save-nrelation']");
	public static By insuranceLeftInnerRltTableValues = By.xpath("//table[@id='rel_insurances']//select[@name='map_list[]_helper1']/option");
	public static By insuranceRightInnerRltTableValues = By.xpath("//table[@id='rel_insurances']//select[@name='map_list[]_helper2']/option");
	public static By insuranceInnerTableValueAfterMapping = By.xpath("//table[@id='rel_insurances']//tbody[@role='alert']//tr[@class='tableRow odd']//td[1]");
}

