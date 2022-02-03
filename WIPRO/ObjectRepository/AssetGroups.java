package ObjectRepository;

import org.openqa.selenium.By;

public class AssetGroups {
	
	public static By assetGroupsInMainMenu = By.xpath("//a[contains(@href,'/asset-groups')]");
	
	public static By astGrpOvrInfoBtn = By.xpath("//a[@class='btn btn-help btn-small Asset-Groups-help']");
	public static By astGrpOvrAddBtn = By.xpath("//a[@class='btn btn-help btn-small act']");
	public static By astGrpOvrDeleteBtn = By.xpath("//div[@class='box-header well']//p/a[3]");
	public static By astGrpOvrExportBtn = By.id("export");
	
	public static By astGrpHelpPopup = By.id("Asset-Groups-help");
	public static By astGrpHelpPopupCloseBtn = By.xpath("//div[@id='Asset-Groups-help']/div[3]/button");
	
	
	//	ASSET GROUPS LIST VIEW
	public static By astGrpAssetGroupsSearchBox = By.cssSelector("input[aria-controls='asset_group_table']");
	
	public static By astGrpAssetGrpListViewTtl = By.xpath("//table[@id='asset_group_table']//tbody/tr");
	
	public static By astGrpLstviewFstEdtBtn = By.xpath("//table[@id='asset_group_table']//tr/td[5]/a");
	public static By astGrpLstviewFstDelBtn = By.xpath("//table[@id='asset_group_table']//tr/td[5]/a[2]");
	
	public static By astGrpLstViewFstName = By.xpath("//table[@id='asset_group_table']/tbody/tr/td[2]");
	
	public static By astGrpLstViewFstNameChk = By.xpath("//table[@id='asset_group_table']/tbody/tr/td/div/span/input");
	public static By astGrpLstViewSecNameChk = By.xpath("//table[@id='asset_group_table']/tbody/tr[2]/td/div/span/input");
	
	public static By astGrpSelectAllChkBox = By.id("chk_source_table");
	
	public static By astGrpListViewPagination = By.xpath("//div[@id='asset_group_table_wrapper']//div[@class='dataTables_paginate paging_bootstrap pagination pagination-right']/ul/li");
	
	public static By astGrpListViewLength = By.name("asset_group_table_length");
	public static By astGrpListViewRecsInfo = By.id("asset_group_table_info");
	
	public static By astGrpViewBtn = By.xpath("//table[@id='asset_group_table']/tbody/tr[1]/td[5]/a[3]");
	public static By astGrpViewPopup = By.xpath("//div[@id='content']/div[2]/div[1]/div[2]");
	public static By astGrpViewCloseBtn = By.xpath("//div[@id='showdisplay_view']/fieldset/div[4]/div/div/div/div/div[2]/input");

	
	//	ADD ASSET GROUP
	public static By astGrpGroupName = By.id("asset_group_name");
	public static By astGrpAssetGroupId = By.id("asset_group_ref");
		
	public static By astGrpSubmitBtn = By.id("asset_group_submit");
	public static By astGrpClearBtn = By.cssSelector("input[value='Clear']");
	public static By astGrpCancelBtn = By.cssSelector("input[value='Cancel']");
	
	
	//	RELATIONSHIP
	public static By astGrpRelationshipBarTitle = By.id("show-lbl-rel");
	
	public static By AstGrpRltnAssetsCnt = By.xpath("//table[@id='rel_assets']/tbody/tr");
    public static By AstGrpRltnAssetsSearchBox = By.xpath("//div[@id='rel_assets_filter']//input");
    public static By AstGrpRltnAssetsSrchRslt = By.xpath("//table[@id='rel_assets']//tr/td");
	
    public static By AstGrpRltnTasksSearchBox = By.xpath("//input[@aria-controls='rel_task']");
	public static By AstGrpRltnTasksCnt = By.xpath("//table[@id='rel_task']//tr");
	public static By AstGrpRltnTasksSrchRslt = By.xpath("//table[@id='rel_task']//tr/td");
	
	//Export 
	
	public static By astGrpExportBtn = By.xpath("html/body/div[4]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/p/a[4]");
	
	/// View
	public static By viewAstGrpName=By.xpath("//div[@id='v_asg_name']");
	public static By viewAstGrpId=By.xpath("//div[@id='v_asg_ref']");
	public static By viewAstGrpCmnts=By.xpath("//div[@id='v_asg_comments']");
	public static By viewAstGrpCloseBtn=By.xpath("//div[@id='showdisplay_view']/fieldset/div[4]/div/div/div/div/div[2]/input");
	
	
}
