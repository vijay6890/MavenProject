package ObjectRepository;

import org.openqa.selenium.By;

public class Tasks {
	
	public static By tasksInMainMenu = By.xpath("//a[contains(@href,'/tasks')]");
	
	public static By tskOvrInfoBtn = By.xpath(".//*[@id='content']/div/div[1]/div[1]/div[1]/p/a[1]");
	public static By tskOvrAddBtn = By.xpath("//a[@id='addactive'][@class='btn btn-help btn-small act']");
	
	public static By tskOvrDeleteBtn = By.xpath("//a[@class='btn btn-documents btn-small']");
	public static By tskOvrTaskOrderBtn = By.xpath("//a[@class='btn btn-help btn-small act Task-List']");
	public static By tskOvrImportDataBtn = By.xpath("//a[@class='btn btn-importcsv btn-small act']");
	public static By tskOvrExportDataBtn = By.id("export");	
	public static By tskHelpPopup = By.id("Tasks-help");
	public static By tskHelpPopupCloseBtn = By.xpath(".//*[@id='Tasks-help']/div[3]/button");
	
	
	//Mass Edit 
	public static By tskOvrMassEditBtn = By.xpath("//a[@id='mass_update'][@class='btn btn-importcsv btn-small']");
	public static By tskEditTskTypeCnt = By.xpath("//div[@id='tasks_type_chzn']//ul/li");
	public static By tskEditTskTypeArrow = By.xpath("//div[@id='tasks_type_chzn']/a/div");
	public static By tskmEdtTskTypeSearchBox= By.xpath("//div[@id='tasks_type_chzn']//input");
	
	public static By tskEditEmpAssignCnt = By.xpath("//div[@id='tasks_resource_chzn']//ul/li");
	public static By tskEditEmpAssignArrow = By.xpath("//div[@id='tasks_resource_chzn']/a/div");
	public static By tskmEdtEmpAssignSearchBox = By.xpath("//div[@id='tasks_resource_chzn']//input");
	
	public static By tskEditSubmitBtn = By.id("tasks_submit");
	public static By tskmEdtClearBtn = By.xpath("//form[@id='tasks_mass_update_form']/fieldset/div[2]/button[2]");
	public static By tskmEdtCancelBtn = By.xpath("//form[@id='tasks_mass_update_form']/fieldset/div[2]/button[3]");
	
	public static By tskFirstrow = By.xpath("//table[@id='tasks_table']/tbody/tr[1]/td/div/span/input");
	public static By tskSecrow = By.xpath("//table[@id='tasks_table']/tbody/tr[2]/td/div/span/input");
	
	public static By tskEmpAltPopup = By.xpath("html/body/div[8]/div/div/div[1]");
	
	public static By tskEmpAltOkBtn =  By.xpath("html/body/div[8]/div/div/div[2]/button[2]");
	public static By tskEmpAltCancelBtn = By.xpath("html/body/div[8]/div/div/div[2]/button[1]");
	
	
	//	Task Name  Added View
	public static By tskTasksSearchBox = By.cssSelector("input[aria-controls='tasks_table'],[type='checkbox']");		
	
	public static By tskTaskName = By.xpath("//input[@class='form-control validate {req, Task Name}']");
	public static By tskDuration = By.id("task_duration");
	public static By tskTaskId = By.xpath("//input[@id='task_ref_id']");
	
	public static By tskTaskTypeArrow = By.xpath("//div[@id='task_type_chzn']/a/div");
	public static By tskTaskTypeSearchBox = By.xpath("//div[@id='task_type_chzn']//input");
	public static By tskTaskTypeCnt = By.xpath("//div[@id='task_type_chzn']//ul/li");			
	
	
	public static By tskEmployeeAssignedArrow = By.xpath("//div[@id='principal_resource_chzn']/a/div");
	public static By tskEmployeeAssignedSearchBox = By.xpath("//div[@id='principal_resource_chzn']//input");
	public static By tskEmployeeAssignedCnt = By.xpath("//div[@id='principal_resource_chzn']//ul/li");
	
	public static By tskAltrEmployeeDDArrow=By.xpath("//div[@id='alternate_resource_chzn']/a/span");
	public static By tskAltrEmployeeSearchBox=By.xpath("//div[@id='alternate_resource_chzn']/div/div/input");
	public static By tskAltrEmployeeCnt=By.xpath("//div[@id='alternate_resource_chzn']/div/ul/li");
	
	public static By tskdescription= By.xpath("html/body/div[3]/div[1]/div[2]/div/div[1]/div[1]/div[2]/div[3]/form/fieldset/div/div[3]/div/div/div/div/label");
	
	public static By tskSubmitBtn = By.id("task_submit"); 
	public static By tskClearBtn = By.xpath("//form[@id='tasks_form']/fieldset/div/div[4]/div/div/div/div/div[1]/input[1]");
	public static By tskCancelBtn = By.xpath("//form[@id='tasks_form']/fieldset/div/div[4]/div/div/div/div/div[1]/input[2]");
	
	
	public static By tskEmployeeAssignedDefTxt = By.xpath("//div[@id='principal_resource_chzn']/a/span");
	public static By taskTypeDefTxt = By.xpath("//div[@id='task_type_chzn']/a/span");
	
	//Task name list view 
	
	public static By tskNameSearchbox = By.xpath("//div[@id='tasks_table_filter']//input");
    
	public static By tskTaskNameTtl = By.xpath("//table[@id='tasks_table']/tbody/tr"); 
	
	public static By tskLstviewFstEdtBtn = By.xpath("//table[@id='tasks_table']//tr/td[9]/a");
	public static By tskLstviewFstDelBtn = By.xpath("//table[@id='tasks_table']//tr/td[9]/a[2]");

	public static By tskNameLstViewSearchRslt = By.xpath("//table[@id='tasks_table']//tr/td[2]");
	
	public static By tskNameSelectAllChkBox = By.id("chk_source_table");
	
	public static By tskNameListViewPagination = By.xpath("//div[@id='tasks_table_wrapper']/div[2]/div[2]/div/ul/li/a");
	
	public static By tskNameListViewLength = By.name("tasks_table_length");
	
	public static By tskNameListViewRecsInfo = By.xpath(".//*[@id='tasks_table_info']");
	
	public static By tskSelectAllChkBox = By.id("chk_source_table");
	public static By tsktaskNameLstViewTtl = By.xpath("//table[@id='tasks_table']//tbody/tr");
	
	public static By tsknameSearchBox = By.xpath("//div[@id='tasks_table_filter']/label/input");
	public static By tskLstViewSrchRslt = By.xpath("//table[@id='tasks_table']/tbody/tr/td[2]");
	
	public static By tskSelectChkBox  =By.xpath("//div[@id='uniform-undefined']//input ");
	
	public static By tskDelBtn= By.xpath("//table[@id='tasks_table']/tbody/tr/td[9]/a[2]");
	public static By tskEditBtn = By.xpath("//table[@id='tasks_table']/tbody/tr/td[9]/a[1]");
	
	public static By tskViewBtn = By.xpath("//table[@id='tasks_table']/tbody/tr/td[9]/a[3]");
	public static By tskViewCloseBtn = By.xpath("//div[@id='showdisplay_view']/fieldset/div/div[5]/div/div/div/div/div[1]/input");
	public static By  tskViewPopup = By.xpath("//div[@id='showdisplay_view']");

	
	
	//Relationship tab and search 
	
	public static By tskNameRelationshipTitleNm = By.id("show-lbl-rel");
	

	public static By tskRltnEmployeesCnt = By.xpath("//table[@id='rel_resources']/tbody/tr");
	public static By tskRltnEmployeesSearchBox = By.xpath("//div[@id='rel_resources_filter']//input");
	public static By tskRltnEmployeesSrchRslt = By.xpath("//table[@id='rel_resources']//tr/td");
	
	public static By tskRltnTeamsCnt = By.xpath("//table[@id='rel_resource_grp']/tbody/tr");
	public static By tskRltnTeamsSearchBox = By.xpath("//div[@id='rel_resource_grp_filter']//input");
	public static By tskRltnTeamsSearchRslt = By.xpath("//table[@id='rel_resource_grp']//tr/td");
	
	
	public static By tskRltnContactsCnt = By.xpath("//table[@id='rel_contacts']/tbody/tr");
	public static By tskRltnContactsSearchBox = By.xpath("//div[@id='rel_contacts_filter']//input");
	public static By tskRltnContactsSrchRslt = By.xpath("//table[@id='rel_contacts']//tr/td");

	public static By tskRltnContactGroupscnt = By.xpath("//table[@id='rel_contact_grp']/tbody/tr");
	public static By tskRltnContactGroupsSearchBox = By.xpath("//div[@id='rel_contact_grp_filter']//input");
	public static By tskRltnContactGroupsSrchRslt = By.xpath("//table[@id='rel_contact_grp']//tr/td ");
	
	public static By tskRltnAssetsCnt = By.xpath("//table[@id='rel_assets']/tbody/tr");
    public static By tskRltnAssetsSearchBox = By.xpath("//div[@id='rel_assets_filter']//input");
    public static By tskRltnAssetsSrchRslt = By.xpath("//table[@id='rel_assets']//tr/td");
    
    public static By tskRltnassetGroupsCnt = By.xpath("//table[@id='rel_asset_grp']/tbody/tr");
    public static By tskRltnassetGroupsSearchBox = By.xpath("//div[@id='rel_asset_grp_filter']//input");
    public static By tskRltnassetGroupsSrchRslt = By.xpath("//table[@id='rel_asset_grp']//tr/td");
    
    public static By tskRltnBusinesFuncCnt = By.xpath("//table[@id='rel_processes']/tbody/tr");
    public static By tskRltnBusinesFuncSearchBox = By.xpath("//div[@id='rel_processes_filter']//input");
    public static By tskRltnBusinessFuncSearchRslt = By.xpath("//table[@id='rel_processes']//tr/td");
    
    public static By tskRltntskgrpCnt = By.xpath("//table[@id='rel_task_grp']/tbody/tr");
    public static By tskRltntskgrpSearchBox = By.xpath("//div[@id='rel_task_grp_filter']//input ");
    public static By tskRltntskgrpSearchRslt = By.xpath("//table[@id='rel_task_grp']//tr/td");
    
   //Documents
    public static By docuemntTb = By.id("documents");
    public static By choosefiles = By.id("doc_files");
    public static By docUpload = By.id("doc_upload");
    public static By browse = By.name("upload");
    public static By open = By.id("1");
    public static By docComments = By.id("doc_description");
    public static By docCancelBtn = By.xpath("//form[@id='document_form']/div[3]/div/div/div/input");
	public static By docSearchBox = By.xpath("//div[@id='rel_documents_wrapper']/div/div/div/label/input");
    
    
    public static By rltnEmployess = By.id("resources");
	
	public static By rltnAssets = By.id("assets");
	public static By rltnteams = By.id("resource_group");
	public static By rltnBusinessFunctions = By.id("process");
	public static By rltnDocuments = By.id("documents");
	
	public static By rltnPopupCloseBtn = By.cssSelector("a[data-dismiss='modal']");
	public static By rltnPopupSubmitBtn = By.id("post_relationship");
	

	//Task order 
	
	public static By tskTaskOrderbtn = By.cssSelector("a[class='btn btn-help btn-small act Task-List']");
	public static By tskTaskOrderclosebtn = By.xpath("//div[@id='Task-List']/div[3]/button");
	public static By tskTaskOrderpopup  = By.xpath("//div[@id='Task-List']//div");
	public static By tskodrMsgNotificationBar = By.cssSelector("div.notify-message-bar");
	
	// To view info menu
	
	public static By tskInfo = By.cssSelector("a[class='btn btn-help btn-small Tasks-help']");
	public static By tskInfopopup= By.xpath("//div[@id='Tasks-help']/div");
	public static By tskInfoclosebtn = By.xpath("//div[@id='Tasks-help']/div[3]/button");
	
	// View
	
	public static By viewTskName=By.xpath("//div[@id='v_task_name']");
	public static By viewTskType=By.xpath("//div[@id='v_task_type']");
	public static By viewTskId=By.xpath("//div[@id='v_task_ref']");
	public static By viewTskCmnts=By.xpath("//div[@id='v_task_comments']");
	
	public static By viewTskCloseBtn=By.xpath("//div[@id='showdisplay_view']/fieldset//div[5]//div[1]/input");
	
	
	// Import and Export menu 
	
	public static By tskImportBtn = By.cssSelector("a[class = 'btn btn-importcsv btn-small act']");
	public static By tskImportUploadBtn = By.id("up_tasks_csv");
	public static By tskImportSubmitBtn = By.id("csv_task_submit");
	public static By tskImportCancelBtn = By.xpath("//form[@id='csv_tasks_form']/div[1]/div/div/div/div/button");
	public static By tskImportHelpDocBtn = By.xpath("//*[@id='csv_tasks_form']/div[1]/div/div/div/div/a");
	
	public static By tskExportBtn = By.xpath("html/body/div[4]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/p/a[7]");
	
	public static By tasksInGrid = By.xpath("//table[@id='tasks_table']//tbody[@role='alert']//tr");
	//public static By addRemoveRelationsBtn = By.xpath("//span[contains(text(),'Add / Remove Relations')]");
	public static By rightMoveAllBtnRltWindow = By.xpath("//label[contains(text(),'Mapped Employees')]/..//button[@class='btn removeall']");
	public static By employeeValuesRltWindow = By.xpath("//label[contains(text(),'List of Employees')]/..//button[@class='btn moveall']/../../select//option");
	//public static By empLeftSearchTxtBoxRltWindow = By.xpath("//label[contains(text(),'List of Employees')]/../input[@class='filter']");
	public static By empLeftMoveAllBtnRltWindpw = By.xpath("//label[contains(text(),'List of Employees')]/..//button[@class='btn moveall']");
	//public static By empRightSearchTxtBoxRltWindow = By.xpath("//label[contains(text(),'Mapped Employees')]/../input[@class='filter']");
	public static By empValuesRltWindow = By.xpath("//label[contains(text(),'Mapped Employees')]/..//button[@class='btn removeall']/../../select//option");
	public static By submitBtnRltWindow = By.id("post_relationship");
	public static By empMoveLeftButtonRltTable = By.xpath("//label[contains(text(),'List of Employees')]/..//button[@class='btn move']");
	
	
	public static By teamsTabTasksRltSection = By.xpath("//span[@class='hidden-phone hidden-tablet' and contains(text(),'Teams')]");
	public static By rightMoveAllArrowTeamsRltWindow = By.xpath("//label[contains(text(),'Mapped Teams')]/..//button[@class='btn removeall']");
	public static By teamsLeftRltWindowValues = By.xpath("//label[contains(text(),'List of Teams')]/..//button[@class='btn moveall']/../../select//option");
	public static By teamsLeftSearchTxtBoxRltWindow = By.xpath("//label[contains(text(),'List of Teams')]/../input[@class='filter']");
	public static By teamsRightSearchTxtBoxRltWindow = By.xpath("//label[contains(text(),'Mapped Teams')]/../input[@class='filter']");
	public static By teamsLeftMoveAllBtnRltWindow = By.xpath("//label[contains(text(),'List of Teams')]/..//button[@class='btn moveall']");
	public static By teamsLeftMoveBtnRltWindow = By.xpath("//label[contains(text(),'List of Teams')]/..//button[@class='btn move']");
	public static By teamsViewBtnRltWindow = By.xpath("//table[@id='rel_resource_grp']//a[@class='btn btn-small btn-primary btn-nestable-action']");
    public static By teamsAddRemoveBtnInnerRltWindow = By.xpath("//table[@id='rel_resource_grp']//a[@class='label btn-primary btn-addremove-nrelation']");
    public static By teamsTabValuesInnerRltWindow = By.xpath("//table[@id='rel_resource_grp']//div[contains(@class,'span12 module-tabitem')]");
    public static By teamsSearchTxtBoxinnerRltTable = By.xpath("//table[@id='rel_resource_grp']//input[@class='filter']");
    public static By teamsSubmitBtnInnerRltTable = By.xpath("//table[@id='rel_resource_grp']//a[@class='btn btn-small btn-primary btn-save-nrelation']");
    public static By teamsLeftMoveAllArrowInnerRltTable = By.xpath("//table[@id='rel_resource_grp']//button[@class='btn moveall']");
    public static By teamsLeftMoveBtnInnerRltTable = By.xpath("//table[@id='rel_resource_grp']//button[@class='btn move']");
    public static By teamsRightMoveAllArrowInnerRltTable = By.xpath("//table[@id='rel_resource_grp']//button[@class='btn removeall']");
    public static By teamsLeftInnerRltTableValues = By.xpath("//table[@id='rel_resource_grp']//select[@name='map_list[]_helper1']/option");
    public static By teamsRightInnerRltTableValues = By.xpath("//table[@id='rel_resource_grp']//select[@name='map_list[]_helper2']/option");
    public static By teamsValueInnerRltTableAfterMapping = By.xpath("//table[@id='rel_resource_grp']//tbody[@role='alert']//tr[@class='tableRow odd']//td[1]");
    
    
    public static By contactsTabTasksRltSection = By.xpath("//span[@class='hidden-phone hidden-tablet' and contains(text(),'Contacts')]");
    public static By rightMoveAllArrowContactsRltWindow = By.xpath("//label[contains(text(),'Mapped Contacts')]/..//button[@class='btn removeall']");
    public static By contactsLeftRltWindowValues = By.xpath("//label[contains(text(),'List of Contacts')]/..//button[@class='btn moveall']/../../select//option");
    public static By contactsLeftSearchTxtBoxRltWindow = By.xpath("//label[contains(text(),'List of Contacts')]/../input[@class='filter']");
    public static By contactsLeftMoveAllBtnRltWindow = By.xpath("//label[contains(text(),'List of Contacts')]/..//button[@class='btn moveall']");
    public static By contactsLeftMoveBtnRltWindow = By.xpath("//label[contains(text(),'List of Contacts')]/..//button[@class='btn move']");
    public static By contactsRightSearchTxtBoxRltWindow = By.xpath("//label[contains(text(),'Mapped Contacts')]/../input[@class='filter']");
    public static By contactsViewBtnRltWindow = By.xpath("//table[@id='rel_contacts']//a[@class='btn btn-small btn-primary btn-nestable-action']");
    public static By contactsAddRemoveBtnInnerRltWindow = By.xpath("//table[@id='rel_contacts']//a[@class='label btn-primary btn-addremove-nrelation']");
    public static By contactsTabValueInnerRltTable = By.xpath("//table[@id='rel_contacts']//div[contains(@class,'span12 module-tabitem')]");
    public static By contactsSearchTxtBoxInnerRltTable = By.xpath("//table[@id='rel_contacts']//input[@class='filter']");
    public static By contactsSubmitButtonInnerRltTable = By.xpath("//table[@id='rel_contacts']//a[@class='btn btn-small btn-primary btn-save-nrelation']");
    public static By contactsLeftMoveAllArrowInnerRltTable = By.xpath("//table[@id='rel_contacts']//button[@class='btn moveall']");
    public static By contactsLeftMoveBtnInnerRltTable = By.xpath("//table[@id='rel_contacts']//button[@class='btn move']");
    public static By contactsRightMoveAllArrowInnerRltTable = By.xpath("//table[@id='rel_contacts']//button[@class='btn removeall']");
    public static By contactsLeftInnerTableValues = By.xpath("//table[@id='rel_contacts']//select[@name='map_list[]_helper1']/option");
    public static By contactsRightInnerTableValues = By.xpath("//table[@id='rel_contacts']//select[@name='map_list[]_helper2']/option");
    public static By contactsValueInnerRltTableAfterMapping = By.xpath("//table[@id='rel_contacts']//tbody[@role='alert']//tr[@class='tableRow odd']//td[1]");


    public static By contactsGroupTabRltSection = By.xpath("//span[@class='hidden-phone hidden-tablet' and contains(text(),'Contact Groups')]");
    public static By contactGroupRightMoveAllArrowRltWindow = By.xpath("//label[contains(text(),'Mapped Contact Groups')]/..//button[@class='btn removeall']");
    public static By contactGroupLeftRltWindowValues = By.xpath("//label[contains(text(),'List of Contact Groups')]/..//button[@class='btn moveall']/../../select//option");
    public static By contactGroupLeftSearchTxtBoxRltWindow = By.xpath("//label[contains(text(),'List of Contact Groups')]/../input[@class='filter']");
    public static By contactGroupRightSearchTxtBoxRltWindow = By.xpath("//label[contains(text(),'Mapped Contact Groups')]/../input[@class='filter']");
    public static By contactGroupLeftMoveBtnRltWindow = By.xpath("//label[contains(text(),'List of Contact Groups')]/..//button[@class='btn move']");
    public static By contactGroupViewButtonRltWindow = By.xpath("//table[@id='rel_contact_grp']//a[@class='btn btn-small btn-primary btn-nestable-action']");
    public static By contactGroupAddRemoveButtonInnerRltTable = By.xpath("//table[@id='rel_contact_grp']//a[@class='label btn-primary btn-addremove-nrelation']");
    public static By contactGroupRightMoveAllArrowInnerRltTable = By.xpath("//table[@id='rel_contact_grp']//button[@class='btn removeall']");
    public static By contactGroupLeftInnerTabValues = By.xpath("//table[@id='rel_contact_grp']//div[contains(@class,'span12 module-tabitem')]");
    public static By contactGroupInnerRltTableSearchTxtBox = By.xpath("//table[@id='rel_contact_grp']//input[@class='filter']");
    public static By contactGroupLeftMoveArrowInnerRltTable = By.xpath("//table[@id='rel_contact_grp']//button[@class='btn move']");
    public static By contactGroupLeftInnerRltValues = By.xpath("//table[@id='rel_contact_grp']//select[@name='map_list[]_helper1']/option");
    public static By contactGroupInnerRltSubmitBtn = By.xpath("//table[@id='rel_contact_grp']//a[@class='btn btn-small btn-primary btn-save-nrelation']");
    public static By contactGroupInnerRltValueAfterMapping = By.xpath("//table[@id='rel_contact_grp']//tbody[@role='alert']//tr[@class='tableRow odd']//td[1]");
    public static By contactGroupRightInnerRltValues = By.xpath("//table[@id='rel_contact_grp']//select[@name='map_list[]_helper2']/option");


    public static By assetGroupsTabRltSection = By.xpath("//span[@class='hidden-phone hidden-tablet' and contains(text(),'Asset Groups')]");
    public static By assetGroupRightMoveAllArrowRltWindow = By.xpath("//label[contains(text(),'Mapped Asset Groups')]/..//button[@class='btn removeall']");
    public static By assetGroupLeftRltWindowValues = By.xpath("//label[contains(text(),'List of Asset Groups')]/..//button[@class='btn moveall']/../../select//option");
    public static By assetGroupLeftSearchTxtBoxRltWindow = By.xpath("//label[contains(text(),'List of Asset Groups')]/../input[@class='filter']");
    public static By assetGroupRightSearchTxtBoxRltWindow = By.xpath("//label[contains(text(),'Mapped Asset Groups')]/../input[@class='filter']");
    public static By assetGroupLeftMoveBtnRltWindow = By.xpath("//label[contains(text(),'List of Asset Groups')]/..//button[@class='btn move']");
    public static By assetGroupViewBtnRltWindow = By.xpath("//table[@id='rel_asset_grp']//a[@class='btn btn-small btn-primary btn-nestable-action']");
    public static By assetGroupAddRemoveBtnInnerRltTable = By.xpath("//table[@id='rel_asset_grp']//a[@class='label btn-primary btn-addremove-nrelation']");
    public static By assetGroupRightMoveAllArrowInnerRltTable = By.xpath("//table[@id='rel_asset_grp']//button[@class='btn removeall']");
    public static By assetGroupLeftInnerTabValues = By.xpath("//table[@id='rel_asset_grp']//div[contains(@class,'span12 module-tabitem')]");
    public static By assetGroupInnerRltTableSearchTxtBox = By.xpath("//table[@id='rel_asset_grp']//input[@class='filter']");
    public static By assetGroupLeftMoveArrowInnerRltTable = By.xpath("//table[@id='rel_asset_grp']//button[@class='btn move']");
    public static By assetGroupInnerRltValues = By.xpath("//table[@id='rel_asset_grp']//select[@name='map_list[]_helper1']/option");
    public static By assetGroupInnerRltSubmitBtn = By.xpath("//table[@id='rel_asset_grp']//a[@class='btn btn-small btn-primary btn-save-nrelation']");
    public static By assetGroupRltValueAfterMapping = By.xpath("//table[@id='rel_asset_grp']//tbody[@role='alert']//tr[@class='tableRow odd']//td[1]");
    public static By assetGroupRightInnerRltValues = By.xpath("//table[@id='rel_asset_grp']//select[@name='map_list[]_helper2']/option");


   public static By taskGroupsTabRltSection = By.xpath("//span[@class='hidden-phone hidden-tablet' and contains(text(),'Task Groups')]");
   public static By taskGroupsRightMoveAllArrowRltWindow = By.xpath("//label[contains(text(),'Mapped Task Groups')]/..//button[@class='btn removeall']");
   public static By taskGroupLeftRltWindowValues = By.xpath("//label[contains(text(),'List of Task Groups')]/..//button[@class='btn moveall']/../../select//option");
   public static By taskGroupLeftSearchTxtBoxRltWindow = By.xpath("//label[contains(text(),'List of Task Groups')]/../input[@class='filter']");
   public static By taskGroupsRightSearchTxtBoxRltWindow = By.xpath("//label[contains(text(),'Mapped Task Groups')]/../input[@class='filter']");
   public static By taskGroupsLeftMoveBtnRltWindow = By.xpath("//label[contains(text(),'List of Task Groups')]/..//button[@class='btn move']");
   public static By taskGroupsViewBtnRltWindow = By.xpath("//table[@id='rel_task_grp']//a[@class='btn btn-small btn-primary btn-nestable-action']");
   public static By taskGroupsAddRemoveBtnInnerRltTable = By.xpath("//table[@id='rel_task_grp']//a[@class='label btn-primary btn-addremove-nrelation']");
   public static By taskGroupRightMoveAllArrowInnerRltTable = By.xpath("//table[@id='rel_task_grp']//button[@class='btn removeall']");
   public static By taskGroupLeftInnerRltValues = By.xpath("//table[@id='rel_task_grp']//select[@name='map_list[]_helper1']/option");
   public static By taskGroupInnerRltTableLeftSearchTxtBox = By.xpath("//table[@id='rel_task_grp']//label[contains(text(),'List of Items')]/../input[@class='filter']");
   public static By taskGroupInnerRltTableRightSearchTxtBox = By.xpath("//table[@id='rel_task_grp']//label[contains(text(),'Mapped Items')]/../input[@class='filter']");
   public static By taskGroupsInnerRltSubmitBtn = By.xpath("//table[@id='rel_task_grp']//a[@class='btn btn-small btn-primary btn-save-nrelation']");
   public static By taskGroupsRltValueAfterMapping = By.xpath("//table[@id='rel_task_grp']//tbody[@role='alert']//tr[@class='tableRow odd']//td[1]");
   public static By taskGroupsRightInnerRltValues = By.xpath("//table[@id='rel_task_grp']//select[@name='map_list[]_helper2']/option");
   public static By taskGroupsLeftInnerRltMoveBtn = By.xpath("//table[@id='rel_task_grp']//button[@class='btn move']");
} 
		
