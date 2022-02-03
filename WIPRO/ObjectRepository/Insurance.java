package ObjectRepository;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class Insurance {
	
	public static By insuranceInMainMenu = By.xpath("//a[contains(@href,'/insurance')]");
	
	public static By insOvrInfoBtn = By.cssSelector("a.btn.Insurance-help");
	public static By insOvrAddBtn = By.xpath("//a[@class='btn btn-help btn-small act']");
	public static By insOvrMassEditBtn = By.xpath("//a[@id='mass_update'][@class='btn btn-importcsv btn-small']");
	public static By insOvrDeleteBtn = By.xpath("//a[@class='btn btn-documents btn-small']");
	public static By insOvrImportDataBtn = By.xpath("//a[@class='btn btn-importcsv btn-small act']");
	public static By insOvrExportDataBtn = By.id("export");
	
	//	INSURANCE LIST VIEW
	public static By insInsuranceSearchBox = By.cssSelector("input[aria-controls='insurance_table']");	
	
	public static By insSelectAllChkBox = By.id("chk_source_table");
	
	public static By insLstviewFstEdtBtn = By.xpath("//table[@id='insurance_table']//tr/td[10]/a");
	public static By insLstviewFstDelBtn = By.xpath("//table[@id='insurance_table']//tr/td[10]/a[2]");
	
	public static By insInsuranceListViewTtl = By.xpath("//table[@id='insurance_table']//tbody/tr");
	public static By insInsuranceSearchRslt = By.xpath("//table[@id='insurance_table']/tbody/tr[1]/td[2]");
	
	public static By insFirstNameInLstView = By.xpath("//table[@id='insurance_table']/tbody/tr[1]/td/div/span/input");
	public static By insSecNameInLstView = By.xpath("//table[@id='insurance_table']/tbody/tr[2]/td/div/span/input");
	
	public static By insInsuranceHelpPp = By.id("Insurance-help");
	public static By insInsuranceHelpPpCloseBtn = By.xpath("//div[@id='Insurance-help']/div[3]/button"); 
	
	public static By insViewBtn = By.xpath("//table[@id='insurance_table']/tbody/tr[1]/td[10]/a[3]");
	public static By insViewPopup = By.xpath("//div[@id='showdisplay_view']");
	public static By insViewCloseBtn = By.xpath("//div[@id='showdisplay_view']/fieldset/div[6]/div/input");
	
	//	ADD NEW INSURANCE
	public static By insPolicyName = By.id("insur_name");
	public static By insPolicyNumber = By.id("insur_number");
	
	public static By insPolicyTypeDefTxt = By.xpath("//div[@id='insur_type_chzn']/a/span");
	public static By insPolicyTypeArrow = By.xpath("//div[@id='insur_type_chzn']/a/div");
	public static By insPolicyTypeSearchBox = By.xpath("//div[@id='insur_type_chzn']/div/div/input");
	public static By insPolicyTypeCnt = By.xpath("//div[@id='insur_type_chzn']//ul/li");
	
	public static By insInsurer = By.id("insur_insurer");
	public static By insPremium = By.id("insur_Premium");
	public static By insExpiryDate = By.id("insur_expiry");
	
	public static By insInsurerContactDefTxt = By.xpath("//div[@id='insur_contact_chzn']/a/span");
	public static By insInsurerContactArrow = By.xpath("//div[@id='insur_contact_chzn']/a/div");
	public static By insInsurerContactSearchBox = By.xpath("//div[@id='insur_contact_chzn']/div/div/input");
	public static By insInsurerContactCnt = By.xpath("//div[@id='insur_contact_chzn']//ul/li");
	
	public static By insInsurerAdminDefTxt = By.xpath("//div[@id='insur_admin_chzn']/a/span");
	public static By insInsurerAdminArrow = By.xpath("//div[@id='insur_admin_chzn']/a/div");
	public static By insInsurerAdminSearchBox = By.xpath("//div[@id='insur_admin_chzn']/div/div/input");
	public static By insInsurerAdminCnt = By.xpath("//div[@id='insur_admin_chzn']//ul/li");
	
	public static By insInsuranceId = By.id("insur_reference_id");
	
	public static By insCalndrMnth = By.xpath("//div[7]/div[1]/table/thead/tr[1]/th[2]");
	public static By insCalndrNextMnthArw = By.xpath("//div[7]/div[2]/table/thead/tr/th[3]"); 
			
	public static By insCalndrJunMnth = By.xpath("//span[contains(text(),'Jun')]");
	public static By insCalndrDateThrty = By.xpath("//div[7]/div[1]/table/tbody/tr[5]/td[6]"); 
		
	public static By insCalndrOctMnth = By.xpath("//span[contains(text(),'Oct')]");
	public static By insCalndrDateTwenyFiv = By.xpath("//div[7]/div[1]/table/tbody/tr[5]/td[3]"); 
	
	public static By insCalndrDecMnth = By.xpath("//span[contains(text(),'Dec')]");
	public static By insCalndrDateTwelv = By.xpath("//div[7]/div[1]/table/tbody/tr[2]/td[6]");
		
	public static By insCalndrAugMnth = By.xpath("//span[contains(text(),'Aug')]");
	public static By insCalndrDateTwenyOne = By.xpath("//div[7]/div[1]/table/tbody/tr[4]/td[3]");
		
	public static By insCalndrJanMnth = By.xpath("//span[contains(text(),'Jan')]");
	public static By insCalndrDateTwenyFour = By.xpath("//div[7]/div[1]/table/tbody/tr[4]/td[6]");
		
	public static By insSubmitBtn = By.id("insur_submit");
	public static By insClearBtn = By.xpath("//input[@class='btn act-clr validate-cancel {insurance_form}']");
	public static By insCancelBtn = By.xpath("//input[@class='btn validate-cancel {insurance_form}']");
	
	public static By insInsuranceListViewPagination = By.xpath("//table[@id='insurance_table']//div/div[2]/div/ul/li");
	
	public static By insListViewLength = By.name("insurance_table_length");
	public static By insListViewRecsInfo = By.id("insurance_table_info");
	
	//	CALENDAR
	public static By insDatePickrDaysCalndr = By.xpath("//div[@class='datepicker-days']//th[@class='datepicker-switch']");
	public static By insDatePickrMonthsCalndr = By.xpath("//div[@class='datepicker-months']//th[@class='datepicker-switch']");
	public static By insDatePickrYearsCalndr = By.xpath("//div[@class='datepicker-years']//th[@class='datepicker-switch']");
	
	public static By insYrInCalTop = By.xpath("//table[@class='table-condensed']//tr/th[2]");
	
	public static By insCalndrRightSideArrow = By.xpath("//div[@class='datepicker-years']/table/thead/tr/th[3]");
	
	public static By insGetTotalYearsCnt = By.xpath("//div[@class='datepicker-years']//tbody/tr/td/span");
	public static By insGetTotalMonths = By.xpath("//div[@class='datepicker-months']//tbody/tr/td/span");
	public static By insGetRandomDateRowCnt = By.xpath("//div[@class='datepicker-days']//tbody/tr");	
	public static By insGetRandomDateColCnt = By.xpath("//div[@class='datepicker-days']//tbody/tr/td");
	
	
	//	RELATIONSHIP
	public static By insRltnLocationsTtlCnt = By.xpath("//table[@id='rel_facilities']//tbody/tr");
	public static By insRltnEmployeesTtlCnt = By.xpath("//table[@id='rel_resources']//tbody/tr");
	public static By insRltnAssetsTtlCnt = By.xpath("//table[@id='rel_assets']//tbody/tr");
	public static By insRltnBusinessFuncsTtlCnt = By.xpath("//table[@id='rel_processes']//tbody/tr");	
	
	public static By insRltnLocationSrchBox = By.xpath("//input[@aria-controls='rel_facilities']");
	public static By insRltnLocationsSrchRslt = By.xpath("//table[@id='rel_facilities']//tbody/tr/td");
	
	public static By insRltnEmployeesSrchBox = By.xpath("//input[@aria-controls='rel_resources']");
	public static By insRltnEmployeesSrchRslt = By.xpath("//table[@id='rel_resources']//tbody/tr/td");
	
	public static By insRltnAssetsrchBox = By.xpath("//input[@aria-controls='rel_assets']");
	public static By insRltnAssetsSrchRslt = By.xpath("//table[@id='rel_assets']//tbody/tr/td");
	
	public static By insRltnBusinessFuncsSrchBox = By.xpath("//input[@aria-controls='rel_processes']");
	public static By insRltnBusinessFuncsSrchRslt = By.xpath("//table[@id='rel_processes']//tbody/tr/td");
	
	
	//	MASS EDIT
	public static By insmEdtPolicyTypeDefTxt = By.xpath("//div[@id='insurance_type_chzn']/a/span");
	public static By insmEdtPolicyTypeCnt = By.xpath("//div[@id='insurance_type_chzn']//ul/li");
	public static By insmEdtPolicyTypeDDArw = By.xpath("//div[@id='insurance_type_chzn']/a/div");
	public static By insmEdtPolicyTypeSearchBox = By.xpath("//div[@id='insurance_type_chzn']//div/input");
	
	public static By insmEdtInsurer = By.id("insurance_insurer");
	
	public static By insmEdtInsurerAdminDefTxt = By.xpath("//div[@id='insurance_admin_chzn']/a/span");
	public static By insmEdtInsurerAdminCnt = By.xpath("//div[@id='insurance_admin_chzn']//ul/li");
	public static By insmEdtInsurerAdminDDArw = By.xpath("//div[@id='insurance_admin_chzn']/a/div");
	public static By insmEdtInsurerAdminSearchBox = By.xpath("//div[@id='insurance_admin_chzn']//div/input");
	
	public static By insmEdtInsurerContactDefTxt = By.xpath("//div[@id='insurance_contact_chzn']/a/span");
	public static By insmEdtInsurerContactCnt = By.xpath("//div[@id='insurance_contact_chzn']//ul/li");
	public static By insmEdtInsurerContactDDArw = By.xpath("//div[@id='insurance_contact_chzn']/a/div");
	public static By insmEdtInsurerContactSearchBox = By.xpath("//div[@id='insurance_contact_chzn']//div/input");
	
	public static By insmEdtSubmitBtn = By.id("insurance_submit");
	public static By insmEdtClearBtn = By.xpath("//button[@class='btn act-clr validate-cancel {insurance_mass_update_form}']");
	public static By insmEdtCancelBtn = By.xpath("//button[@class='btn validate-cancel {insurance_mass_update_form}']");
	
	//import 
	
	public static By insImportBtn = By.cssSelector("a[class = 'btn btn-importcsv btn-small act']");
	public static By insImportUploadBtn = By.id("up_ins_csv");
	public static By insImportSubmitBtn = By.id("csv_insur_submit");
	public static By insImportCancelBtn = By.xpath("//form[@id='csv_insurance_form']/div[1]/div/div/div/div/input");
	public static By insImportHelpDocBtn = By.xpath("//form[@id='csv_insurance_form']/div[1]/div/div/div/div/a/button");
	
	public static By insExportBtn = By.xpath("html/body/div[4]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/p/a[6]");
	public static By locationsTabRltTable = By.xpath("//li[@id='facilities']");
	public static By addRemoveRelationsBtn = By.xpath("//span[contains(text(),'Add / Remove Relations')]");
	public static By leftSearchTextBoxLocationsRelationWindow = By.xpath("//label[contains(text(),'List of Locations')]/../input[@class='filter']");
    public static By leftMoveAllBtnInnerRelationWindow = By.xpath("//table[@id='rel_facilities']//button[@class='btn moveall']");
    public static By leftMoveBtnInnerRltTable = By.xpath("//table[@id='rel_facilities']//button[@class='btn move']");
    public static By rightSearchTextBoxLocationsRelationWindow = By.xpath("//label[contains(text(),'Mapped Locations')]/../input[@class='filter']");
    public static By submitButtonRelationShipWindow = By.id("post_relationship");
    public static By addRemoveButtonInnerRelationTable = By.xpath("//table[@id='rel_facilities']//a[@class='label btn-primary btn-addremove-nrelation']");
    public static By leftInnerRelationshipTable = By.xpath("//table[@id='rel_facilities']//div[@class='span12 panel-addremove-nrelation']//select[@name='map_list[]_helper1']");
    public static By leftInnerRelationshipTableValues = By.xpath("//table[@id='rel_facilities']//div[@class='span12 panel-addremove-nrelation']//select[@name='map_list[]_helper1']//option");
    public static By locationsInnerRelationshipTableSearchTextBox = By.xpath("//table[@id='rel_facilities']//input[@class='filter']");
    public static By rightMoveAllBtnRelationWindow = By.xpath("//label[contains(text(),'Mapped Locations')]/..//button[@class='btn removeall']");
    public static By tabValuesInsideLocations = By.xpath("//table[@id='rel_facilities']//div[contains(@class,'span12 module-tabitem')]");
    public static By locationsSubmitButtonInnerTable = By.xpath("//table[@id='rel_facilities']//a[@class='btn btn-small btn-primary btn-save-nrelation']");
    public static By recordAfterSubmitInnerRelationTable = By.xpath("//table[@id='rel_facilities']//div[@class='span10 module-tab-container']//tbody[@role='alert']//td[1]");
    public static By leftMoveAllBtnRelationWindow = By.xpath("//label[contains(text(),'List of Locations')]/..//button[@class='btn moveall']");
    public static By locLeftMoveBtnRltWindow = By.xpath("//label[contains(text(),'List of Locations')]/..//button[@class='btn move']");
    public static By rightMoveAllBtnInnerRelationWindow = By.xpath("//table[@id='rel_facilities']//button[@class='btn removeall']");
    public static By otherLocationsEmployeesOkButton = By.xpath("//button[@class='btn btn btn-primary']");
    public static By locAddRemoveBtnInnerRltTable = By.xpath("//table[@id='rel_facilities']//a[@class='label btn-primary btn-addremove-nrelation']");
    public static By locLeftRelationShipTableValue = By.xpath("//table[@id='rel_facilities']//select[@name='map_list[]_helper1']/option");
    public static By locRightRelationShipTableValue = By.xpath("//table[@id='rel_facilities']//select[@name='map_list[]_helper2']/option");
    public static By locValueAfterInnerMapping = By.xpath("//table[@id='rel_facilities']//tbody[@role='alert']//tr[@class='tableRow odd']//td[1]");
    public static By rightLocValueRltWindow = By.xpath("//label[contains(text(),'Mapped Locations')]/..//button[@class='btn removeall']/../../select//option");
    
   
    
    public static By employeesTabInsuranceRelationSection = By.xpath("//span[@class='hidden-phone hidden-tablet' and contains(text(),'Employees')]");
    public static By leftEmployeeRelationWindowValues = By.xpath("//label[contains(text(),'List of Employees')]/..//button[@class='btn moveall']/../../select//option");
    public static By rightMoveAllBtnEmpRltWindow = By.xpath("//label[contains(text(),'Mapped Employees')]/..//button[@class='btn removeall']");
    public static By empLeftSearchTxtBoxRltWindow = By.xpath("//label[contains(text(),'List of Employees')]/../input[@class='filter']");
    public static By leftMoveAllBtnEmpRltWindow = By.xpath("//label[contains(text(),'List of Employees')]/..//button[@class='btn moveall']");
    public static By leftMoveBtnEmpRltWindow = By.xpath("//label[contains(text(),'List of Employees')]/..//button[@class='btn move']");
    public static By empRightSearchTxtBoxRltWindow = By.xpath("//label[contains(text(),'Mapped Employees')]/../input[@class='filter']");
    public static By empViewBtnRltWindow = By.xpath("//table[@id='rel_resources']//a[@class='btn btn-small btn-primary btn-nestable-action']");
    public static By empAddRemoveRltBtnInnerRltTable = By.xpath("//table[@id='rel_resources']//a[@class='label btn-primary btn-addremove-nrelation']");
    public static By empLeftInnerRltTable = By.xpath("//table[@id='rel_resources']//select[@name='map_list[]_helper1']");
    public static By empLeftInnerRltTableValues = By.xpath("//table[@id='rel_resources']//select[@name='map_list[]_helper1']/option");
    public static By empSearchTxtBoxInnerRltTable = By.xpath("//table[@id='rel_resources']//input[@class='filter']");
    public static By empLeftTabsRelationshipTable = By.xpath("//table[@id='rel_resources']//div[contains(@class,'span12 module-tabitem')]");
    public static By empLeftRltTableMoveAllArrow = By.xpath("//table[@id='rel_resources']//button[@class='btn moveall']");
    public static By empRightRltTableMoveAllArrow = By.xpath("//table[@id='rel_resources']//button[@class='btn removeall']");
    public static By empRightInnerTableValues = By.xpath("//table[@id='rel_resources']//select[@name='map_list[]_helper2']/option");
    public static By empSubmitBtnInnerRltTable = By.xpath("//table[@id='rel_resources']//a[@class='btn btn-small btn-primary btn-save-nrelation']");
    public static By tableValueAfterMapping = By.xpath("//table[@id='rel_resources']//tbody[@role='alert']//tr[@class='tableRow odd']//td[1]");
    public static By empLeftMoveBtnInnerRltTable = By.xpath("//table[@id='rel_resources']//button[@class='btn move']");



    public static By assetsTabInsuranceRelationSection = By.xpath("//span[@class='hidden-phone hidden-tablet' and contains(text(),'Assets')]");
    public static By rightMoveAllBtnAssetsRltWindow = By.xpath("//label[contains(text(),'Mapped Assets')]/..//button[@class='btn removeall']");
    public static By leftAssetRelationWindowValues = By.xpath("//label[contains(text(),'List of Assets')]/..//button[@class='btn moveall']/../../select//option");
    public static By rightMoveAllBtnAssetRltWindow = By.xpath("//label[contains(text(),'Mapped Assets')]/..//button[@class='btn removeall']");
    public static By assetLeftSearchTxtBoxRltWindow = By.xpath("//label[contains(text(),'List of Assets')]/../input[@class='filter']");
    public static By leftMoveAllBtnAssetRltWindow = By.xpath("//label[contains(text(),'List of Assets')]/..//button[@class='btn moveall']");
    public static By leftMoveBtnAssetRltWindow = By.xpath("//label[contains(text(),'List of Assets')]/..//button[@class='btn move']");
    public static By assetRightSearchTxtBoxRltWindow = By.xpath("//label[contains(text(),'Mapped Assets')]/../input[@class='filter']");
    public static By assetViewBtnRltWindow = By.xpath("//table[@id='rel_assets']//a[@class='btn btn-small btn-primary btn-nestable-action']");
    public static By assetAddRemoveRltBtnInnerRltTable = By.xpath("//table[@id='rel_assets']//a[@class='label btn-primary btn-addremove-nrelation']");
    public static By assetLefttabsRltTable = By.xpath("//table[@id='rel_assets']//div[contains(@class,'span12 module-tabitem')]");
    public static By assetSearchTxtBoxInnerRltTable = By.xpath("//table[@id='rel_assets']//input[@class='filter']");
    public static By assetLeftRltTableMoveAllArrow = By.xpath("//table[@id='rel_assets']//button[@class='btn moveall']");
    public static By assetLeftRltTableMoveArrow = By.xpath("//table[@id='rel_assets']//button[@class='btn move']");
    public static By assetRightTableMoveAllArrow = By.xpath("//table[@id='rel_assets']//button[@class='btn removeall']");
    public static By assetLeftRltTableValues = By.xpath("//table[@id='rel_assets']//select[@name='map_list[]_helper1']/option");
    public static By assetRightRltTableValues = By.xpath("//table[@id='rel_assets']//select[@name='map_list[]_helper2']/option");
    public static By assetSubmitBtnInnerRltTable = By.xpath("//table[@id='rel_assets']//a[@class='btn btn-small btn-primary btn-save-nrelation']");
    public static By assetTableValueAfterMapping = By.xpath("//table[@id='rel_assets']//tbody[@role='alert']//tr[@class='tableRow odd']//td[1]");
    
    public static By locationSubTab = By.xpath("//select[contains(@id,'ddl_facilities')]/../div//select/option");
    public static By employeesSubTab = By.xpath("//select[contains(@id,'ddl_resource')]/../div//select/option");
    public static By contactsSubTab = By.xpath("//select[contains(@id,'ddl_contacts')]/../div//select/option");
    public static By insuranceSubTab = By.xpath("//select[contains(@id,'ddl_insurances')]/../div//select/option");
    public static By businessFunctionsSubTab = By.xpath("//select[contains(@id,'ddl_processes')]/../div//select/option");
    public static By teamsSubTab = By.xpath("//select[contains(@id,'ddl_resourcegrp')]/../div//select/option");
    public static By assetsSubTab = By.xpath("//select[contains(@id,'ddl_assets')]/../div//select/option");
    public static By assetGroupsSubTab = By.xpath("//select[contains(@id,'ddl_asset_group')]/../div//select/option");
    public static By contactGroupsSubTab = By.xpath("//select[contains(@id,'ddl_contact_group')]/../div//select/option");
    public static By taskGroupsSubTab = By.xpath("//select[contains(@id,'ddl_task_group')]/../div//select/option");
    public static By threatsSubTab = By.xpath("//select[contains(@id,'ddl_threats')]/../div//select/option");
    public static By taskSubTab = By.xpath("//select[contains(@id,'ddl_tasks')]/../div//select/option");


    public static By businessFntTabInsuranceRltSection = By.xpath("//span[@class='hidden-phone hidden-tablet' and contains(text(),'Business Functions')]");
    public static By businessFntLeftSearchTextBoxRltWindow = By.xpath("//label[contains(text(),'List of Business Functions')]/../input[@class='filter']");
    public static By leftBusinessFntRltWindowValues = By.xpath("//label[contains(text(),'List of Business Functions')]/..//button[@class='btn moveall']/../../select//option");
    public static By leftMoveAllBtnBusinessFntRltWindow = By.xpath("//label[contains(text(),'List of Business Functions')]/..//button[@class='btn moveall']");
    public static By leftMoveBtnBusinessFntRltWindow = By.xpath("//label[contains(text(),'List of Business Functions')]/..//button[@class='btn move']");
    public static By rightMoveAllBtnBusinessFtnRltWindow = By.xpath("//label[contains(text(),'Mapped Business Functions')]/..//button[@class='btn removeall']");
    public static By businessFntRightSearchTextBoxRltWindow = By.xpath("//label[contains(text(),'Mapped Business Functions')]/../input[@class='filter']");
    public static By businessFntViewBtnRltWindow = By.xpath("//table[@id='rel_processes']//a[@class='btn btn-small btn-primary btn-nestable-action']");
    public static By businessFntAddRemoveBtnInnerRltWindow = By.xpath("//table[@id='rel_processes']//a[@class='label btn-primary btn-addremove-nrelation']");
    public static By businessFntLeftMoveAllArrowInnerRltTable = By.xpath("//table[@id='rel_processes']//button[@class='btn moveall']");
    public static By businessFntLeftMoveArrowInnerRltTable = By.xpath("//table[@id='rel_processes']//button[@class='btn move']");
    public static By businessFntRightMoveAllArrowInnerRltTable = By.xpath("//table[@id='rel_processes']//button[@class='btn removeall']");
    public static By businessFntSearchTxtBoxInnerRltTable = By.xpath("//table[@id='rel_processes']//input[@class='filter']");
    public static By businessFntLeftTabsInnerRltTable = By.xpath("//table[@id='rel_processes']//div[contains(@class,'span12 module-tabitem')]");
    public static By businessFntSubmitBtnInnerRltTable = By.xpath("//table[@id='rel_processes']//a[@class='btn btn-small btn-primary btn-save-nrelation']");
    public static By businessFntLeftRltTableValues = By.xpath("//table[@id='rel_processes']//select[@name='map_list[]_helper1']/option");
    public static By businessFntRightRltTableValues = By.xpath("//table[@id='rel_processes']//select[@name='map_list[]_helper2']/option");
    public static By businessFntTableValueAfterMapping = By.xpath("//table[@id='rel_processes']//tbody[@role='alert']//tr[@class='tableRow odd']//td[1]");

    
    /// View
    
    public static By viewInsName=By.xpath("//div[@id='v_insure_name']");
    public static By viewInsNo=By.xpath("//div[@id='v_insure_number']");
    public static By viewInsType=By.xpath("//div[@id='v_insure_type']");
    public static By viewInsInsurer=By.xpath("//div[@id='v_insure_insure']");
    public static By viewInsPre=By.xpath("//div[@id='v_insure_premium']");
    public static By viewInsExpDt=By.xpath("//div[@id='v_insure_expiry']");
    public static By viewIncCnt=By.xpath("//div[@id='v_insure_contact']");
    public static By viewIncAdmin=By.xpath("//div[@id='v_insure_admin']");    
    public static By viewInsId=By.xpath("//div[@id='v_insure_ref']");
    public static By viewInsCmnts=By.xpath("//div[@id='v_insure_description']");
    
    public static By viewInsCloseBtn=By.xpath("//div[@id='showdisplay_view']/fieldset/div[6]/div/input");
}



