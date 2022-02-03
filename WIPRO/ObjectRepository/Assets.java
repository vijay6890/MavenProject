package ObjectRepository;

import org.openqa.selenium.By;

public class Assets {

	public static By assetsInMainMenu = By.xpath("//a[contains(@href,'/assets')]");
	
	public static By astOvrInfoBtn = By.xpath("//a[@class='btn btn-help btn-small Assets-help']");
	public static By astOvrAddBtn = By.xpath("//a[@class='btn btn-help btn-small act']");
	public static By astOvrMassEditBtn = By.xpath("//a[@id='mass_update'][@class='btn btn-importcsv btn-small']");
	public static By astOvrDeleteBtn = By.xpath("//a[@class='btn btn-documents btn-small']");
	public static By astOvrImportCSVBtn = By.xpath("//a[@class='btn btn-importcsv btn-small act']");
	public static By astOvrExportAsCSVBtn = By.id("export");
	
	public static By astHelpDocPopup = By.id("Assets-help");
	public static By astHelpDocPopupCrosCloseBtn = By.xpath("//div[@id='Assets-help']/div/button");
	
	//	ASSET LIST VIEW
	public static By astAssetsSearchBox = By.cssSelector("input[aria-controls='assets_table']");
	
	public static By astAssetListViewTtl = By.xpath("//table[@id='assets_table']/tbody/tr");
	
	public static By astLstviewFstEdtBtn = By.xpath("//table[@id='assets_table']/tbody/tr[1]/td[12]/a[1]");
	public static By astLstviewFstDelBtn = By.xpath("//table[@id='assets_table']//tr/td[12]/a[2]");
	
	public static By astFstNameInLstView = By.xpath("//table[@id='assets_table']/tbody/tr[1]/td[2]");
	public static By astListViewLength = By.name("assets_table_length");
	public static By astListViewRecsInfo = By.id("assets_table_info");
	
	public static By astSelectAllchkBox = By.id("chk_source_table");
	public static By astListViewPagination = By.xpath("//div[3]/div[1]/div[2]/div/div[1]/div[2]/div[2]/div/div/div/div[2]/div[2]/div/ul/li");
	
	public static By astLstFirstName = By.xpath("//table[@id='assets_table']/tbody/tr[1]/td//input");
	
	//public static By astViewBtn = By.xpath("//table[@id='assets_table']/tbody/tr/td[11]/a[3]");
	public static By astViewBtn = By.xpath("//table[@id='assets_table']/tbody/tr[1]/td[12]/a[3]");
	public static By astViewCloseBtn = By.xpath("//div[@id='showdisplay_view']/fieldset/div[6]/input");
	public static By astViewPopup = By.xpath("//div[@id='content']/div[2]/div[2]");
		
	//	ADD ASSET
	public static By astAssetName = By.id("asset_name");
	public static By astStatusArrow = By.xpath("//div[@id='asset_status_chzn']/a/div");
	public static By astStatusSearchBox = By.xpath("//div[@id='asset_status_chzn']/div/div/input");
	public static By astStatusCnt = By.xpath("//div[@id='asset_status_chzn']//ul/li");
	
	public static By astAssetTypeArrow = By.xpath("//div[@id='asset_type_chzn']/a/div");
	public static By astAssetTypeSearchBox = By.xpath("//div[@id='asset_type_chzn']/div/div/input");
	public static By astAssetTypeCnt = By.xpath("//div[@id='asset_type_chzn']//ul/li");
	
	public static By astModelNumber = By.id("asset_model_number");
	public static By astAssetId = By.id("asset_reference");
	public static By astManufacturerName = By.id("asset_manufacture");
	public static By astSerialNumber = By.id("asset_serial_no");
	public static By astQuantity = By.id("asset_quantity");
	public static By astReplacementValue = By.id("asset_replacement_value");
	public static By astCommentsField = By.xpath("//html/body/p");
	
	public static By astSubmitBtn = By.id("asset_submit");
	public static By astClearBtn = By.cssSelector("input[value='Clear']");
	public static By astCancelBtn = By.cssSelector("input[value='Cancel']");
		
	//	EDIT
	public static By astStatusEdt = By.xpath("//div[@id='asset_status_chzn']/a/span");
	public static By astAssetTypeEdt = By.xpath("//div[@id='asset_type_chzn']/a/span");
	
	//	MASS EDIT
	public static By astmEdtAssetTypeArw = By.xpath("//div[@id='assets_type_chzn']/a/div");
	public static By astmEdtAssetTypeSearchBox = By.xpath("//div[@id='assets_type_chzn']/div/div/input");
	public static By astmEdtAssetTypeCnt = By.xpath("//div[@id='assets_type_chzn']//ul/li");
	
	public static By astmEdtManufacturerName = By.id("assets_manufacture");
	
	public static By astmEdtAssetStatusArw = By.xpath("//div[@id='assets_status_chzn']/a/div");
	public static By astmEdtAssetStatusSearchBox = By.xpath("//div[@id='assets_status_chzn']/div/div/input");
	public static By astmEdtAssetStatusCnt = By.xpath("//div[@id='assets_status_chzn']//ul/li");
	
	public static By astmEdtSubmitBtn = By.id("assets_submit");
	public static By astmEdtCancelBtn = By.xpath("//button[@class='btn validate-cancel {assets_mass_update_form}']");
	public static By astmEdtClearBtn = By.xpath("//button[@class='btn act-clr validate-cancel {assets_mass_update_form}']");
	
	public static By astmEdtStatusAftrM = By.xpath("//table[@id='assets_table']/tbody/tr/td[3]");
	public static By astmEdtAssetTypeAftrM = By.xpath("//table[@id='assets_table']/tbody/tr/td[4]");
	
	//	Relationship Mapping
	public static By astRltnLocationsSearchBox = By.xpath("//div[@id='rel_facilities_filter']//input");	
	public static By astRltnLocationsCnt = By.xpath("//table[@id='rel_facilities']//tr");
	public static By astRltnLocationsSrchRslt = By.xpath("//table[@id='rel_facilities']//tr/td");
	
	public static By astRltnAssetGrpCnt = By.xpath("//table[@id='rel_asset_grp']/tbody/tr ");
	public static By astRltnAssetGrpSearchBox = By.xpath("//div[@id='rel_asset_grp_filter']//input");
	public static By astRltnAssetGrpSrchRslt = By.xpath("//table[@id='rel_asset_grp']//tr/td");	
	
	public static By astRltnContactsCnt = By.xpath("//table[@id='rel_contacts']/tbody/tr");
	public static By astRltnContactsSearchBox = By.xpath("//div[@id='rel_contacts_filter']//input");
	public static By astRltnContactsSrchRslt = By.xpath("//table[@id='rel_contacts']//tr/td");
	
	public static By astRltnInsCnt = By.xpath("//table[@id='rel_insurances']/tbody/tr ");
	public static By astRltnInsSearchBox = By.xpath("//div[@id='rel_insurances_filter']//input ");
	public static By astRltnInsSrchRslt = By.xpath(" //table[@id='rel_insurances']//tr/td "); 
	 
	public static By astRltnBusinesFuncCnt = By.xpath("//table[@id='rel_processes']/tbody/tr");
	public static By astRltnBusinesFuncSearchBox = By.xpath("//div[@id='rel_processes_filter']//input");
    public static By astRltnBusinessFuncSearchRslt = By.xpath("//table[@id='rel_processes']//tr/td");
	
    public static By astRltnTasksSearchBox = By.xpath("//input[@aria-controls='rel_task']");
	public static By astRltnTasksCnt = By.xpath("//table[@id='rel_task']//tr");
	public static By astRltnTasksSrchRslt = By.xpath("//table[@id='rel_task']//tr/td");
	
	//	NO MATCHING RECS FOUND MESSAGE
	public static By astContactsTbNoMatchngRecFound = By.xpath("//table[@id='rel_contacts']//tbody/tr/td");
	public static By astInsuranceTbNoMatchngRecFound = By.xpath("//table[@id='rel_insurances']//tbody/tr/td");
	public static By astBusinessFuncTbNoMatchngRecFound = By.xpath("//table[@id='rel_processes']//tbody/tr/td"); 
	public static By astTasksTbNoMatchngRecFound = By.xpath("//table[@id='rel_task']//tbody/tr/td");
	
	/////////                  Import And Export CSV          ///////////// 
	
	public static By astImportBtn=By.xpath("//div[@id='content']/div[2]/div[1]/p/a[4]");
	public static By astUploadFile=By.name("up_assets_csv");
	public static By astImpSubmitBtn=By.xpath(".//*[@id='csv_asset_submit']");
	public static By astSearchBox=By.xpath(".//*[@id='assets_table_filter']/label/input");
	public static By astImportCancelBtn=By.xpath(".//*[@id='csv_asstes_form']/div[1]/div/div/div/div/input[1]"); 
	public static By astHelpDocBtn=By.xpath(".//*[@id='csv_asstes_form']/div[1]/div/div/div/div/a/button");

	public static By astExportBtn=By.xpath("html/body/div[4]/div[1]/div[1]/div[2]/div[2]/div[1]/p/a[6]");
	
	//// View Screen
	public static By viewAstName=By.xpath("//div[@id='v_assets_name']");
	public static By viewAstStatus=By.xpath("//div[@id='v_assets_status']");
	public static By viewAstType=By.xpath("//div[@id='v_assets_type']");
	public static By viewAstModlNo=By.xpath("//div[@id='v_assets_model']");
	public static By viewAstId=By.xpath("//div[@id='v_assets_reference']");
	public static By viewAstMfdName=By.xpath("//div[@id='v_assets_manufacture']");
	public static By viewAstSerialNo=By.xpath("//div[@id='v_assets_serial']");
	public static By viewAstQuant=By.xpath("//div[@id='v_assets_quantity']");
	public static By viewAstReplaceVal=By.xpath("//div[@id='v_assets_replacementvalue']");
	public static By viewAstCmnts=By.xpath("//div[@id='v_assets_description']");
	
	
	
	public static By viewAstCloseBtn=By.xpath("//div[@id='showdisplay_view']/fieldset/div[6]/input");
	
	
}
















