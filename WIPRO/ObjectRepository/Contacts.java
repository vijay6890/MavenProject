package ObjectRepository;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

public class Contacts {
	
	
	public static By contactsInMainMenu = By.xpath("//a[contains(@href,'/contacts')]");

	public static By cntOvrInfoBtn = By.cssSelector("a.btn.Vendors-help");
	public static By cntOvrAddBtn = By.xpath("//a[@class='btn btn-help btn-small act']");
	public static By cntOvrMassEditBtn = By.xpath("//a[@id='mass_update'][@class='btn btn-importcsv btn-small']");
	public static By cntOvrDeleteBtn = By.xpath("//a[@class='btn btn-documents btn-small']");
	public static By cntOvrImportDataBtn = By.cssSelector("a[title='Import CSV']");
	public static By cntOvrExportDataBtn = By.id("export");
		
	public static By cntHelpDocumentPp = By.id("Vendors-help");
	public static By cntHelpDocumentPpCloseBtn = By.xpath("//div[@id='Vendors-help']/div[3]/button");
	
	
	//	CONTACTS LIST VIEW
	public static By cntContactsSearchBox = By.cssSelector("input[aria-controls='contacts_table']");	
	
	public static By cntContactsListViewTtl = By.xpath("//table[@id='contacts_table']//tbody/tr");
	
	public static By cntLstviewFstEdtBtn = By.xpath("//table[@id='contacts_table']//tr/td[11]/a");
	public static By cntLstviewFstDelBtn = By.xpath("//table[@id='contacts_table']//tr/td[11]/a[2]");
	
	public static By cntContactListViewFstName = By.xpath("//table[@id='contacts_table']/tbody/tr[1]/td[2]");
	public static By cntContactListViewLstName=By.xpath("//table[@id='contacts_table']/tbody/tr[1]/td[3]");
	
	public static By cntSelectAllChkBox = By.id("chk_source_table");
	
	public static By cntContactsLstViewPagination = By.xpath("//div[@id='contacts_table_wrapper']//div[@class='dataTables_paginate paging_bootstrap pagination pagination-right']/ul/li");
	
	public static By cntListViewFstNameChk = By.xpath("//table[@id='contacts_table']/tbody/tr//input");
	public static By cntListViewSecNameChk = By.xpath("//table[@id='contacts_table']/tbody/tr[2]//input");
	
	public static By cntListViewLength = By.name("contacts_table_length");
	public static By cntListViewRecsInfo = By.id("contacts_table_info");
	
	public static By cntViewBtn = By.xpath("//table[@id='contacts_table']/tbody/tr/td[11]/a[3]");
	public static By cntViewCloseBtn = By.xpath("//form[@id='con_form']/feildset/div[13]/button");
	public static By cntViewPopup = By.xpath("//div[@id='showdisplay_view']");
	
	//	ADD NEW CONTACT
	public static By cntCompanyName = By.id("company_name");
	public static By cntContactId = By.id("contact_ref_id");
	
	public static By cntContactTypeDefTxt = By.xpath("//div[@id='cnt_type_chzn']/a/span");
	public static By cntContactTypeDDArrow = By.xpath("//div[@id='cnt_type_chzn']/a/div");
	public static By cntContactTypeSearchBox = By.xpath("//div[@id='cnt_type_chzn']/div/div/input");
	public static By cntContactTypeDDTtl = By.xpath("//div[@id='cnt_type_chzn']//ul/li");
	
	public static By cntContactStatusDefTxt = By.xpath("//div[@id='contact_status_chzn']/a/span");
	public static By cntContactStatusDDArrow = By.xpath("//div[@id='contact_status_chzn']/a/div");
	public static By cntContactStatusSearchBox = By.xpath("//div[@id='contact_status_chzn']/div/div/input");
	public static By cntContactStatusDDTtl = By.xpath("//div[@id='contact_status_chzn']//ul/li");
	
	public static By cntAddress = By.id("address");
	public static By cntCountryDefTxt = By.xpath("//div[@id='country_chzn']/a/span");
	public static By cntCountryDDArrow = By.xpath("//div[@id='country_chzn']/a/div");
	public static By cntCountrySearchBox = By.xpath("//div[@id='country_chzn']/div/div/input");
		
	public static By cntStateDefTxt = By.xpath("//div[@id='state_chzn']/a/span");
	public static By cntStateDDArrow = By.xpath("//div[@id='state_chzn']/a/div");
	public static By cntStateSearchBox = By.xpath("//div[@id='state_chzn']/div/div/input");
	public static By cntStateDDTtl = By.xpath("//div[@id='state_chzn']//ul/li");
	
	public static By cntCity = By.id("city"); 
	public static By cntZipcode = By.id("zipcode");
	
	public static By cntServiceAreaDefTxt = By.xpath("//div[@id='service_area_chzn']/a/span");
	public static By cntServiceAreaDDArrow = By.xpath("//div[@id='service_area_chzn']/a/div");
	public static By cntServiceAreaSearchBox = By.xpath("//div[@id='service_area_chzn']/div/div/input");
	public static By cntServiceAreaDDTtl = By.xpath("//div[@id='service_area_chzn']//ul/li");
	
	public static By cntPhone = By.id("business_phone");
	public static By cntComments = By.xpath("//html/body/p");
	
	public static By cntLocationDDArrow=By.xpath("//div[@id='facility_id_chzn']/a/span");
	public static By cntLocationSearchBox=By.xpath("//div[@id='facility_id_chzn']/div/div/input");
	public static By cntLocationDDTtl=By.xpath("//div[@id='facility_id_chzn']//ul/li");
	
	//	PRIMARY CONTACT
	public static By cntPmyFirstName = By.id("first_name"); 
	public static By cntPmyLastName = By.id("last_name");
	public static By cntPmyTitle = By.id("title");
	public static By cntPmyBusinessEmail = By.id("business_email");
	public static By cntPmyAlternateEmail = By.id("alternate_email");
	public static By cntPmyPhone = By.id("p_business_phone");
	public static By cntPmyPhoneExtn = By.id("extn");
	public static By cntPmyMobileNo = By.id("mobile_phone");
	
	
	//	SECONDARY CONTACT
	public static By cntSecFirstName = By.id("sec_first_name");
	public static By cntSecLastName = By.id("sec_last_name");
	public static By cntSecTitle = By.id("sec_title");
	public static By cntSecBusinessEmail = By.id("sec_business_email");
	public static By cntSecAlternateEmail = By.id("sec_alternate_email");
	public static By cntSecPhone = By.id("sec_business_phone");
	public static By cntSecPhoneExtn = By.id("sec_extension");
	public static By cntSecMobileNo = By.id("sec_mobile_phone");
	
	public static By cntSubmitBtn = By.id("con_submit");
	public static By cntClearBtn = By.xpath("//button[@class='btn act-clr validate-cancel {con_form}']");
	public static By cntCancelBtn = By.xpath("//button[@class='btn validate-cancel {con_form}']");
	
	//	RELATIONSHIP - TASKS
	//public static By cntRltnRelationshipTitle = By.id("showdisplay1");	
	
	public static By cntRltnRelationshipTitle=By.id("show-lbl-rel");
	public static By cntRltnTaskLstViewFstName = By.xpath("//table[@id='rel_task']/tbody/tr/td");
	public static By cntRltnTasksListViewTtl = By.xpath("//table[@id='rel_task']//tbody/tr");
	public static By cntRltnTasksSearchBox = By.xpath("//input[@aria-controls='rel_task']");
	
	public static By cntRltnLocCnt=By.xpath("//table[@id='rel_facilities']/tbody/tr");
	public static By cntRltnLocSearchBox=By.xpath("//div[@id='rel_facilities_filter']/label/input");
	public static By cntRltnLocSrchResult=By.xpath("//table[@id='rel_facilities']/tbody/tr[1]/td[1]");
	
	public static By cntRltnTasksCnt = By.xpath("//table[@id='rel_task']//tr");
	public static By cntRltnTasksSrchRslt = By.xpath("//table[@id='rel_task']//tr/td");
	
	public static By cntTskNoMatchngRecFound = By.xpath("//table[@id='rel_task']//tbody/tr/td");	
	
	//	RELATIONSHIP - CONTACT GROUPS
	public static By cntRltnContactGrpSearchBox = By.cssSelector("input[aria-controls=rel_contact_grp]");
	public static By cntRltnContactGrpLstViewFstName = By.xpath("//table[@id='rel_contact_grp']/tbody/tr/td");
	public static By cntRltnContactGrpListViewTtl = By.xpath("//table[@id='rel_contact_grp']//tbody/tr");
	
	public static By cntRltnContactGroupscnt = By.xpath("//table[@id='rel_contact_grp']/tbody/tr");
	public static By cntRltnContactGroupsSearchBox = By.xpath("//div[@id='rel_contact_grp_filter']//input");
	public static By cntRltnContactGroupsSrchRslt = By.xpath("//table[@id='rel_contact_grp']//tr/td ");
	
	public static By cntCntGrpNoMatchngRecFound = By.xpath("//table[@id='rel_contact_grp']//tbody/tr/td");
	
	//	MASS EDIT
	public static By cntmEdtContactTypeDDArw = By.xpath("//div[@id='contacts_type_chzn']/a/div");
	public static By cntmEdtContactTypeSearchBox = By.xpath("//div[@id='contacts_type_chzn']/div/div/input");
	public static By cntmEdtContactTypeCnt = By.xpath("//div[@id='contacts_type_chzn']//ul/li");
	
	public static By cntmEdtContactStatusDDArw = By.xpath("//div[@id='contacts_status_chzn']/a/div");
	public static By cntmEdtContactStatusSearchBox = By.xpath("//div[@id='contacts_status_chzn']/div/div/input");
	public static By cntmEdtContactStatusCnt = By.xpath("//div[@id='contacts_status_chzn']//ul/li");
	
	public static By cntmEdtCountryDDArw = By.xpath("//div[@id='contacts_country_chzn']/a/div");
	public static By cntmEdtCountrySearchBox = By.xpath("//div[@id='contacts_country_chzn']/div/div/input");
	
	public static By cntmEdtStateDDArw = By.xpath("//div[@id='contacts_state_chzn']/a/div");
	public static By cntmEdtStateSearchBox = By.xpath("//div[@id='contacts_state_chzn']/div/div/input");
			
	public static By cntmEdtServiceAreaDDArw = By.xpath("//div[@id='contacts_service_chzn']/a/div");
	public static By cntmEdtServiceAreaSearchBox = By.xpath("//div[@id='contacts_service_chzn']/div/div/input");
	public static By cntmEdtServiceAreaCnt = By.xpath("//div[@id='contacts_service_chzn']//ul/li");
	
	public static By cntmEdtAddress = By.id("contacts_address");
	public static By cntmEdtCity = By.id("contacts_city");
	public static By cntmEdtZipcode = By.id("contacts_zip");
	
	public static By cntmEdtSubmitBtn = By.id("contacts_submit");
	public static By cntmEdtClearBtn = By.cssSelector("button[class='btn act-clr validate-cancel {contacts_mass_update_form}']");
	//public static By cntmEdtClearBtn=By.xpath("//div[@id='showdisplay2']/div/div[3]/button[2]");
	public static By cntmEdtCancelBtn = By.cssSelector("button[class='btn validate-cancel {contacts_mass_update_form}']");
	
	
	/*   Contacts Internal Mapping */
	                          //Tasks tab //
	public static By contLeftBox=By.xpath("//select[@id='map_list']/..//select[contains(@name,'map_list[]_helper1')]/option");
	public static By contsRightBox=By.xpath("//select[@id='map_list']/..//select[contains(@name,'map_list[]_helper2')]/option");
	public static By submitBtn=By.id("post_relationship");
	public static By closeBtn=By.xpath("//button[@id='post_relationship']/../a");
	public static By contLeftSearchBox=By.xpath("//select[@id='map_list']/..//select[contains(@name,'map_list[]_helper1')]/..//input");
	public static By contRightSearchBox=By.xpath("//select[@id='map_list']/..//select[contains(@name,'map_list[]_helper2')]/..//input");
	public static By contRightBoxRemoveAll=By.xpath("//select[@id='map_list']/..//select[contains(@name,'map_list[]_helper2')]/..//button[2]");
	public static By contRightBoxRemoveOne=By.xpath("//select[@id='map_list']/..//select[contains(@name,'map_list[]_helper2')]/..//button[1]");
	public static By contleftBoxPushAll=By.xpath("//select[@id='map_list']/..//select[contains(@name,'map_list[]_helper1')]/..//button[1]");
	public static By contleftBoxPushOne=By.xpath("//select[@id='map_list']/..//select[contains(@name,'map_list[]_helper1')]/..//button[2]");
	public static By taskRelation=By.xpath("//table[@id='rel_task']//tbody[@role='alert']//td[1]/div/span");
	public static By contactGridValues=By.xpath("//table[@id='contacts_table']//tbody//tr");
	public static By contactViewButton = By.xpath("//table[@id='rel_task']/tbody/tr[1]/td[1]/a");
	public static By addremoveRelationsButton = By.xpath("//table[@id='rel_task']//div[@class='pull-right']");
	public static By searchBox=By.xpath("//div[@id='resource_nestable_5c128a3e-4eec-3eb7-25f9-583ffb9f52ca_filter']//input");
	public static By closeButton=By.xpath("//a[@class='btn btn-small btn-cancel-nrelation']");
	public static By submitButton=By.xpath("//a[@class='btn btn-small btn-primary btn-save-nrelation']");
	public static By moveAll=By.xpath("//button[@class='btn moveall']");
	public static By moveRight=By.xpath("//i[@class='icon-arrow-right']");
	public static By removeAll=By.xpath("//button[@class='btn removeall']");
	public static By moveLeft=By.xpath("//button[@class='btn remove']");
	public static By leftBox=By.xpath("//select[@id='bootstrap-duallistbox-nonselected-list_map_list[]']");
	public static By rightBox=By.xpath("//select[@id='bootstrap-duallistbox-selected-list_map_list[]']");
    public static By leftSearchBox=By.xpath("//input[@class='filter']");
    public static By gridValue=By.xpath("//table[@id='rel_task']//tbody[@role='alert']//tr[@class='tableRow odd']//td[1]");
	@FindBys(value= { @FindBy(xpath ="div[@class='span12 module-tabitem']")})
	public static List<WebElement> leftMenu;	
	public static By addRemoveButton=By.xpath("//a[@class='btn btn-small']");
	public static By empListValues =By.xpath("//select[contains(@id,'ddl_resource_mdata')]/..//select[@name='map_list[]_helper1']/option");
	public static By teamListValues=By.xpath("//select[contains(@id,'ddl_resourcegrp_mdata')]/..//select[@name='map_list[]_helper1']/option");
	public static By assetListValues=By.xpath("//select[contains(@id,'ddl_assets_mdata')]/..//select[@name='map_list[]_helper1']/option");
	public static By assetGroupListValues=By.xpath("//select[contains(@id,'ddl_asset_group_mdata')]/..//select[@name='map_list[]_helper1']/option");
	public static By contactListValues=By.xpath("//select[contains(@id,'ddl_contacts_mdata')]/..//select[@name='map_list[]_helper1']/option");
	public static By contactGroupListValues=By.xpath("//select[contains(@id,'ddl_contact_group_mdata')]/..//select[@name='map_list[]_helper1']/option");
	public static By taskGroupListValues=By.xpath("//select[contains(@id,'ddl_task_group_mdata')]/..//select[@name='map_list[]_helper1']/option");
	public static By businessFunctionListValues=By.xpath("//select[contains(@id,'ddl_processes_mdata')]/..//select[@name='map_list[]_helper1']/option");
	public static By tasksListValues=By.xpath("//select[contains(@id,'ddl_tasks_mdata')]/..//select[@name='map_list[]_helper1']/option");
                  
	// Contact Groups tab //
	
	public static By contactGroupsTab=By.id("contactgrp");
	public static By contactGroupViewButton=By.xpath("//table[@id='rel_contact_grp']//a");
	public static By contactGroupSubMenus=By.xpath("//div[@class='span2 module-tab-list']");
	public static By contactGroupGridValue=By.xpath("//table[@id='rel_contact_grp']//tbody[@role='alert']//tr[@class='tableRow odd']//td[1]");
	public static By contactGrpAddRemoveBtns=By.xpath("//table[@id='rel_contact_grp']//a[@class='label btn-primary btn-addremove-nrelation']");

	           //----x------x----Contacts Internal Mapping-----x------x-------//
	
	////       Import And Export CSV Files       /////////
	public static By cntImportBtn=By.xpath(".//*[@id='content']/div[2]/div[1]/div[1]/p/a[4]");
	public static By cntUploadFile=By.xpath(".//*[@id='up_cont_csv']");
	public static By cntImportSubmitBtn=By.xpath(".//*[@id='btnCsvUpload']");
	public static By cntSearchBox=By.xpath(".//*[@id='contacts_table_filter']/label/input");
	public static By cntImportCancelBtn=By.xpath(".//*[@id='csv_contact_form']/div[1]/div/div/div/div/button");
	public static By cntImpHelpDocBtn=By.xpath(".//*[@id='csv_contact_form']/div[1]/div/div/div/div/a/button");
	
	public static By cntExportBtn=By.xpath("html/body/div[4]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/p/a[6]");
	
	///   View Screen
	
	public static By viewCmpyNme=By.xpath("//div[@id='v_cont_companyname']");
	public static By viewCntId=By.xpath("//div[@id='v_cont_id']");
	public static By viewCnttype=By.xpath("//div[@id='v_cont_type']");
	public static By viewCntStatus=By.xpath("//div[@id='v_cont_status']");
	public static By viewCntLoc=By.xpath("//div[@id='v_cont_location']");
	public static By viewCntCmnts=By.xpath("//div[@id='v_cont_comments']");
	
	public static By viewCntCloseBtn=By.xpath("//form[@id='con_form']/feildset/div[13]/button");
	
	
	

	

	

}

	