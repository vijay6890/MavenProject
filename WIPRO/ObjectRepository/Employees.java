package ObjectRepository;

import org.openqa.selenium.By;

public class Employees {
	
	public static By employeesInMainMenu = By.xpath("//a[contains(@href,'/resources')]");
	
	public static By empOvrInfoBtn = By.xpath("//a[@class='btn btn-help btn-small Employee-help']");
	public static By empOvrAddBtn = By.xpath("//a[@class='btn btn-help btn-small act']");
	public static By empOvrMassUpdateBtn =By.xpath("//a[@id='mass_update'][@class='btn btn-importcsv btn-small']");
	public static By empOvrDeleteBtn = By.xpath("//a[@class='btn btn-documents btn-small']");
	public static By empOvrImportDataBtn = By.xpath("//a[@class='btn btn-importcsv btn-small act']");
	public static By empOvrExportDataBtn = By.id("export");
	
	public static By empHelpPopup = By.id("Employee-help");
	public static By empHelpPopupCloseBtn = By.xpath("//div[@id='Employee-help']/div[3]/button");
		
	//	EMPLOYEES LIST VIEW
	public static By empEmployeesListViewTtl = By.xpath("//table[@id='resource_table']/tbody/tr");
	
	public static By empLstviewFstEdtBtn = By.xpath("//table[@id='resource_table']//tr/td[11]/a");
	public static By empLstviewFstDelBtn = By.xpath("//table[@id='resource_table']//tr/td[11]/a[2]");
	
	public static By empSelectAllChkBox = By.id("chk_source_table");
	
	//public static By delConfirmationPopup = By.cssSelector("div.modal-content");
	public static By delConfirmationPopup = By.xpath("//div[@class='bootbox modal fade bootbox-confirm in']/div");
	
	public static By empListViewFstName = By.xpath("//table[@id='resource_table']//tr/td/div/span/input");
	public static By empListViewSecName = By.xpath("//table[@id='resource_table']//tr[2]/td/div/span/input");
		
	public static By empSearchBox = By.xpath("//div[@id='resource_table_filter']/label/input");
	
	
	public static By empSrchRslt = By.xpath("//table[@id='resource_table']/tbody/tr/td[2]");
	public static By empActiveEmpName = By.xpath("//table[@id='resource_table']/tbody/tr/td[2]");
	
	public static By empSendEmailBtnOnMouseHvr = By.xpath("//a[contains(text(),'Send Email')]");
	
	public static By empEmployeesListView = By.id("resource_table");
	public static By empEmpMailIdPath = By.xpath("//table[@id='resource_table']/tbody/tr/td[7]");
	
	public static By empEmployeesListHideShowBtn = By.xpath("//div[@id='content']/div/div/div/div/div/a");
	public static By empEmployeesRelationShipHideShowBtn = By.xpath("//div[@id='content']/div/div/div[2]/div/div/a");
	
	public static By empPaginationInfo = By.id("resource_table_info");
	public static By empEmployeesListViewLength = By.name("resource_table_length");
	
	public static By empEmployeesSearchBox = By.cssSelector("input[aria-controls='resource_table']");
	
	public static By empEmployeesListViewPagination = By.xpath("//div[@id='resource_table_wrapper']/div[2]/div[2]/div/ul/li/a");
	
	public static By empListViewLength = By.name("resource_table_length");
	public static By empListViewRecsInfo = By.id("resource_table_info");
	
	public static By empViewBtn = By.xpath("//table[@id='resource_table']/tbody/tr/td[11]/a[3]");
	public static By empViewPopup = By.xpath("//div[@id='showdisplay_view']");
	public static By empViewCloseBtn = By.xpath("//div[@id='showdisplay_view']/fieldset/div[8]/button");
	public static By empFstViewBtn = By.xpath("//table[@id='resource_table']/tbody/tr[1]/td[11]/a[3]");
	
	
	//	ADD NEW EMPLOYEE
	public static By empFirstName = By.id("firstname");
	public static By empLastname = By.id("lastname");
	public static By empTitle = By.id("title");
	public static By empEmployeeId = By.id("referencenumber");
	
	public static By empEmployeeStatusDefTxt = By.xpath("//div[@id='rsc_status_chzn']/a/span");
	public static By empEmployeeStatusArrow = By.xpath("//div[@id='rsc_status_chzn']/a/div");
	public static By empEmployeeStatusCnt = By.xpath("//div[@id='rsc_status_chzn']//ul/li");
	public static By empEmployeeStatusSearchBox = By.xpath("//div[@id='rsc_status_chzn']//input");
	
	public static By empEmployeeTypeDefTxt = By.xpath("//div[@id='resourcetype_chzn']/a/span");
	public static By empEmployeeTypeArrow = By.xpath("//div[@id='resourcetype_chzn']/a/div");
	public static By empEmployeeTypeCnt = By.xpath("//div[@id='resourcetype_chzn']//ul/li");
	public static By empEmployeeTypeSearchBox = By.xpath("//div[@id='resourcetype_chzn']//input");
	
	public static By empDepartment = By.id("deptcomp");
	public static By empEmail = By.id("email");
	public static By empBusinessPhone = By.id("businessphone");
	public static By empMobile = By.id("mobile");
	public static By empResidenceNumber = By.id("residencenumber");
	public static By empOfficeNumber = By.id("othernumber");
	public static By empOfficeExtnNo = By.id("extension");
	public static By empAddress = By.id("address"); 
	
	public static By empCountryDDArrow = By.xpath("//div[@id='country_chzn']/a/span");
	public static By empCountryDDSrchBox = By.xpath("//div[@id='country_chzn']//input");
	public static By empCountry = By.xpath("//div[@id='country_chzn']/a/span");
	
	
	public static By empStateDDArrow = By.xpath("//div[@id='state_chzn']/a/span");
	public static By empStateDDSrchBox = By.xpath("//div[@id='state_chzn']//input");
	//public static By empState = By.id("state");
	public static By empState=By.xpath("//div[@id='state_chzn']/a/span");
	
	public static By empCity = By.id("city");
	public static By empZipcode = By.id("zipcode");
	public static By empSecEmail = By.id("secondary_email");
	public static By empSecMobileNo = By.id("secondary_mobile");
	
	public static By empLocationDefTxt = By.xpath("//div[@id='location_chzn']/a/span");
	public static By empLocationArrow = By.xpath("//div[@id='location_chzn']/a/div");
	public static By empLocationCnt = By.xpath("//div[@id='location_chzn']//ul/li");
	public static By empLocationSearchBox = By.xpath("//div[@id='location_chzn']//input");
	
	public static By empAlternateEmployeeDefTxt = By.xpath("//div[@id='alternate_resource_chzn']/a/span");
	public static By empAlternateEmployeeArrow = By.xpath("//div[@id='alternate_resource_chzn']/a/div");
	public static By empAlternateEmployeeCnt = By.xpath("//div[@id='alternate_resource_chzn']//ul/li");
	public static By empAlternateEmployeeSearchBox = By.xpath("//div[@id='alternate_resource_chzn']//input");
	
	public static By empSubmitBtn = By.id("res_submit");
	public static By empClearBtn = By.xpath("//button[@class='btn act-clr validate-cancel {res_form}']");
	public static By empCancelBtn = By.xpath("//button[@class='btn validate-cancel {res_form}']");
	
	public static By empEditBtn = By.xpath("//table[@id='resource_table']/tbody/tr/td[11]/a[1]");
	
	
	//		TERMINATED POP UP
	public static By empTerminatedPopup = By.xpath("//div[@class='popup-inner']");
	
	public static By empTerminatdPpMsg = By.id("edit_popup_head");
	public static By empTerminatedPpOkBtn = By.id("edit_popup_ok");
	public static By empTerminatedPpCancelBtn = By.xpath("//div[@class='popup-inner']//button[5]");
	public static By empTerminatedPpCrossCloseBtn = By.xpath("//a[@class='popup-close']");
	
		
	//	RELATIONSHIP
	public static By empRelationshipTitleName = By.id("show-lbl-rel");
	
	public static By empRltnTasksTblTtl = By.xpath("//table[@id='rel_task']/tbody/tr");
	public static By empRltnTaskSearchbox = By.xpath("//div[@id='rel_task_filter']//input");
	public static By empRltnTaskSearchRslt = By.xpath("//table[@id='rel_task']//tr/td");
	
	public static By empRltnBusinesFuncTblTtl = By.xpath("//table[@id='rel_process']//tr");
	public static By empRltnBusinesFuncSearchBox = By.xpath("//input[@aria-controls='rel_process']");
	public static By empRltnBusinessFuncSearchRslt = By.xpath("//table[@id='rel_process']//tr/td");
	
	public static By empRltnLocationTblTtl=By.xpath("//table[@id='rel_facilities']/tbody/tr");
	public static By empRltnLocationSearchBox=By.xpath("//div[@id='rel_facilities_filter']/label/input");
	public static By empRltnLocationSearchRslt=By.xpath("//table[@id='rel_facilities']/tbody/tr[1]/td[1]");
	
	public static By empRltnInsuranceTblTtl = By.xpath("//table[@id='rel_insurances']/tbody/tr");
	public static By empRltnInsuranceSearchBox = By.xpath("//div[@id='rel_insurances_filter']//input");
	public static By empRltnInsuranceSearchRslt = By.xpath("//table[@id='rel_insurances']//tr/td");
	
	public static By empRltnTeamsTblTtl = By.xpath("//table[@id='rel_resourcegroup']/tbody/tr");
	public static By empRltnTeamsSearchBox = By.xpath("//div[@id='rel_resourcegroup_filter']//input");
	public static By empRltnTeamsSearchRslt = By.xpath("//table[@id='rel_resourcegroup']//tr/td");
	
	//other relationship
	public static By otherRltnTblTtl=By.xpath("//table[@id='rel_other']/tbody/tr");
	public static By empOtherRltTab = By.id("other_relation");
	public static By otherRltnSearchBox=By.xpath("//div[@id='rel_other_filter']/label/input");
	public static By otherRltnSearchRslt=By.xpath("//table[@id='rel_other']/tbody/tr[1]/td[1]");
	public static By otherRltnPopUpText=By.cssSelector("div.bootbox-body");
	public static By otherCancelBtn=By.cssSelector("button.btn-default");
	public static By unRelateAllBtn=By.cssSelector("a.btn.removeOtherRelation");
	
	public static By empOtrRltnAssignArrow=By.xpath("//div[@id='new_employee_list_chzn']/a");
	public static By empOtrTotalCnt=By.xpath("//div[@id='new_employee_list_chzn']/div/ul/li");
	public static By empOtrSearchBox=By.xpath("//div[@id='new_employee_list_chzn']/div/div/input");
	
	public static By skipnContBtn=By.xpath("//button[@id='assign_employee']");
	public static By cancelBtn=By.cssSelector("button[class='btn'][data-dismiss='modal']");
	
	//individual unrelate button
	
	
	
	//	MASS EDIT
	public static By empmEdtEmployeeStatusDefTxt = By.xpath("//div[@id='resource_status_chzn']/a/span");
	public static By empmEdtEmployeeStatusArrow = By.xpath("//div[@id='resource_status_chzn']/a/div");
	public static By empmEdtEmployeeStatusSearchBox = By.xpath("//div[@id='resource_status_chzn']//input");
	public static By empmEdtEmployeeStatusCnt = By.xpath("//div[@id='resource_status_chzn']//ul/li");
	
	public static By empmEdtEmployeeTypeDefTxt = By.xpath("//div[@id='resource_type_chzn']/a/span");
	public static By empmEdtEmployeeTypeArrow = By.xpath("//div[@id='resource_type_chzn']/a/div");
	public static By empmEdtEmployeeTypeSearchBox = By.xpath("//div[@id='resource_type_chzn']//input");
	public static By empmEdtEmployeeTypeCnt = By.xpath("//div[@id='resource_type_chzn']//ul/li");
	
	public static By empmEdtLocationDefTxt = By.xpath("//div[@id='resource_location_chzn']/a/span");
	public static By empmEdtLocationArrow = By.xpath("//div[@id='resource_location_chzn']/a/div");
	public static By empmEdtLocationSearchBox = By.xpath("//div[@id='resource_location_chzn']//input");
	public static By empmEdtLocationCnt = By.xpath("//div[@id='resource_location_chzn']//ul/li");
	
	public static By empmEdtAddress = By.id("resource_address");
	
	public static By empmEdtCountryDefTxt = By.xpath("//div[@id='resource_country_chzn']/a/span");
	public static By empmEdtCountryArrow = By.xpath("//div[@id='resource_country_chzn']/a/div");
	public static By empmEdtCountrySearchBox = By.xpath("//div[@id='resource_country_chzn']//input");
	public static By empmEdtCountryCnt = By.xpath("//div[@id='resource_country_chzn']//ul/li");
	
	public static By empmEdtStateDefTxt = By.xpath("//div[@id='resource_state_chzn']/a/span");
	public static By empmEdtStateArrow = By.xpath("//div[@id='resource_state_chzn']/a/div");
	public static By empmEdtStateSearchBox = By.xpath("//div[@id='resource_state_chzn']/div/div/input");
	public static By empmEdtStateCnt = By.xpath("//div[@id='resource_state_chzn']//ul/li");
	
	public static By empmEdtCity = By.id("resource_city");
	public static By empmEdtZipcode = By.id("resource_zipcode");
	
	public static By empmEdtAlternateEmployeeDefTxt = By.xpath("//div[@id='resource_alternate_resource_chzn']/a/span");
	public static By empmEdtAlternateEmployeeArrow = By.xpath("//div[@id='resource_alternate_resource_chzn']/a/div");
	public static By empmEdtAlternateEmployeeSearchBox = By.xpath("//div[@id='resource_alternate_resource_chzn']//input");
	public static By empmEdtAlternateEmployeeCnt = By.xpath("//div[@id='resource_alternate_resource_chzn']//ul/li");
	
	public static By empmEdtSubmitBtn = By.id("resource_submit");
	public static By empmEdtClearBtn = By.xpath("//button[@class='btn act-clr validate-cancel {res_mass_update_form}']");
	public static By empmEdtCancelBtn = By.xpath("//button[@class='btn validate-cancel {res_mass_update_form}']");
	
	  // Import and Export menu 
	
		public static By empImportBtn = By.cssSelector("a[class = 'btn btn-importcsv btn-small act']");
		public static By empImportUploadBtn = By.id("up_res_csv");
		public static By empImportSubmitBtn = By.id("btnCsvUpload");
		public static By empImportCancelBtn = By.xpath("//form[@id='csv_resources_form']/div[1]/div/div/div/div/button");
		public static By empImportHelpDocBtn = By.xpath("//form[@id='csv_resources_form']/div[1]/div/div/div/div/a/button");
		
		public static By empExportBtn = By.xpath("html/body/div[4]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/p/a[6]");
		
		//View
		public static By viewEmpFstNme=By.xpath("//div[@id='v_res_fname']");
		public static By viewEmpLstNme=By.xpath("//div[@id='v_res_lname']");
		public static By viewEmpTitle=By.xpath("//div[@id='v_res_title']");
		public static By viewEmpType=By.xpath("//div[@id='v_res_type']");
		public static By viewEmpStatus=By.xpath("//div[@id='v_res_status']");
		public static By viewEmpDept=By.xpath("//div[@id='v_res_dept']");
		public static By viewEmpMobile=By.xpath("//div[@id='v_res_mobile']");
		public static By viewEmpEmail=By.xpath("//div[@id='v_res_email']");
		public static By viewEmpLoc=By.xpath("//div[@id='v_res_location']");
		public static By viewEmpCloseBtn=By.xpath("//div[@id='showdisplay_view']/fieldset/div[8]/button");
		
		public static By viewEmpId=By.xpath("//div[@id='v_res_refnumber']");
		public static By viewEmpBsPh=By.xpath("//div[@id='v_res_bphone']");
		public static By viewEmpResNo=By.xpath("//div[@id='v_res_resnumber']");
		public static By viewEmpOfcNo=By.xpath("//div[@id='v_res_othernumber']");
		public static By viewEmpAddr=By.xpath("//div[@id='v_res_address']");
		public static By viewEmpCntry=By.xpath("//div[@id='v_res_country']");
		public static By viewEmpState=By.xpath("//div[@id='v_res_state']");
		public static By viewEmpCity=By.xpath("//div[@id='v_res_city']");
		public static By viewEmpZipCode=By.xpath("//div[@id='v_res_zipcode']");
		public static By viewEmpSecEmail=By.xpath("//div[@id='v_res_semail']");
		public static By viewEmpSecMobile=By.xpath("//div[@id='v_res_smobile']");
		public static By viewEmpAltrEmp=By.xpath("//div[@id='v_res_alterresource']");
		
		
		// relationship table 
		public static By locationsTabEmpRelationshipSection = By.xpath("//li[@id='facilities']");
		public static By alternateEmployeeValue = By.xpath("//div[@id='alternate_resource_chzn']//span");
		
		 public static By empBusinessFntViewBtnEmpRltSection = By.xpath("//table[@id='rel_process']//a[@class='btn btn-small btn-primary btn-nestable-action']");
		 public static By empBusinessFntAddRemoveBtnInnerRltWindow = By.xpath("//table[@id='rel_process']//a[@class='label btn-primary btn-addremove-nrelation']");
		 public static By empBusinessFntLeftMoveAllArrowInnerRltTable = By.xpath("//table[@id='rel_process']//button[@class='btn moveall']");
		 public static By empBusinessFntLeftMoveArrowInnerRltTable = By.xpath("//table[@id='rel_process']//button[@class='btn move']");
		 public static By empBusinessFntRightMoveAllArrowInnerRltTable = By.xpath("//table[@id='rel_process']//button[@class='btn removeall']");
		 public static By empBusinessFntSearchTxtBoxInnerRltTable = By.xpath("//table[@id='rel_process']//input[@class='filter']");
		 public static By empBusinessFntLeftTabsInnerRltTable = By.xpath("//table[@id='rel_process']//div[contains(@class,'span12 module-tabitem')]");
		 public static By empBusinessFntSubmitBtnInnerRltTable = By.xpath("//table[@id='rel_process']//a[@class='btn btn-small btn-primary btn-save-nrelation']");
		 public static By empBusinessFntLeftRltTableValues = By.xpath("//table[@id='rel_process']//select[@name='map_list[]_helper1']/option");
		 public static By empBusinessFntRightRltTableValues = By.xpath("//table[@id='rel_process']//select[@name='map_list[]_helper2']/option");
		 public static By empBusinessFntTableValueAfterMapping = By.xpath("//table[@id='rel_process']//tbody[@role='alert']//tr[@class='tableRow odd']//td[1]");
		 
		 
		 
		 public static By empTeamsViewBtnRltWindow = By.xpath("//table[@id='rel_resourcegroup']//a[@class='btn btn-small btn-primary btn-nestable-action']");
		 public static By empTeamsAddRemoveBtnInnerRltWindow = By.xpath("//table[@id='rel_resourcegroup']//a[@class='label btn-primary btn-addremove-nrelation']");
		 public static By empTeamsTabValuesInnerRltWindow = By.xpath("//table[@id='rel_resourcegroup']//div[contains(@class,'span12 module-tabitem')]");
		 public static By empTeamsSearchTxtBoxinnerRltTable = By.xpath("//table[@id='rel_resourcegroup']//input[@class='filter']");
		 public static By empTeamsSubmitBtnInnerRltTable = By.xpath("//table[@id='rel_resourcegroup']//a[@class='btn btn-small btn-primary btn-save-nrelation']");
		 public static By empTeamsLeftMoveAllArrowInnerRltTable = By.xpath("//table[@id='rel_resourcegroup']//button[@class='btn moveall']");
		 public static By empTeamsLeftMoveBtnInnerRltTable = By.xpath("//table[@id='rel_resourcegroup']//button[@class='btn move']");
		 public static By empTeamsRightMoveAllArrowInnerRltTable = By.xpath("//table[@id='rel_resourcegroup']//button[@class='btn removeall']");
		 public static By empTeamsLeftInnerRltTableValues = By.xpath("//table[@id='rel_resourcegroup']//select[@name='map_list[]_helper1']/option");
		 public static By empTeamsRightInnerRltTableValues = By.xpath("//table[@id='rel_resourcegroup']//select[@name='map_list[]_helper2']/option");
		 public static By empTeamsValueInnerRltTableAfterMapping = By.xpath("//table[@id='rel_resourcegroup']//tbody[@role='alert']//tr[@class='tableRow odd']//td[1]");
}
