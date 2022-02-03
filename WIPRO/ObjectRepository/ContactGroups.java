
package ObjectRepository;

import org.openqa.selenium.By;

public class ContactGroups {
	
	public static By contactGroupsInMainMenu = By.xpath("//a[contains(@href,'/contactgroup')]");
	
	public static By cntGrpOvrInfoBtn = By.cssSelector("a.btn.Vendor-Groups-help");
	public static By cntGrpOvrAddBtn = By.xpath("//a[@class='btn btn-help btn-small act']");
	public static By cntGrpOvrDeleteBtn = By.cssSelector("a[class='btn btn-documents btn-small']");
	public static By cntGrpOvrExportBtn = By.id("export");
	
	public static By cntGrpHelpPopup = By.id("Vendor-Groups-help");
	public static By cntGrpHelpPpCloseBtn = By.xpath("//div[@id='Vendor-Groups-help']//div[3]/button");
	
	//	CONTACT GROUPS LIST VIEW
	public static By cntGrpContactGroupsSearchBox = By.cssSelector("input[aria-controls='contactgroup_table']");
	
	public static By cntGrpContactGrpNameTtl = By.xpath("//table[@id='contactgroup_table']//tbody/tr");
	
	public static By cntGrpLstviewFstEdtBtn = By.xpath("//table[@id='contactgroup_table']//tr/td[5]/a");
	public static By cntGrpLstviewFstDelBtn = By.xpath("//table[@id='contactgroup_table']//tr/td[5]/a[2]");	
			
	public static By cntGrpNoRecordsFoundMsg = By.xpath("//table[@id='contactgroup_table']//td[@class='dataTables_empty']");
	
	public static By cntGrpContactGroupName = By.id("cgroup_name");
	public static By cntGrpGroupPurpose = By.id("cgroup_purpose");
	public static By cntGrpComments = By.xpath("//html/body/p");
	
	public static By cntGrpSelectAllChkBox = By.id("chk_source_table");
	
	public static By cntGrpListViewCheckBox = By.xpath("//table[@id='contactgroup_table']/tbody/tr/td/div/span/input");
	
	public static By cntGrpListViewPagination = By.xpath("//form[@id='deleteallform']/div/div//div/div[2]/div/ul/li");
					
	public static By cntGrpListViewLength = By.name("contactgroup_table_length");
	public static By cntGrpListViewRecsInfo = By.id("contactgroup_table_info");
	
	public static By cntGrpSubmitBtn = By.id("cgp_submit");
	public static By cntGrpClearBtn = By.xpath("//button[@class='btn act-clr validate-cancel {cgp_form}']");
	public static By cntGrpCancelBtn = By.xpath("//button[@class='btn validate-cancel {cgp_form}']");
	
	public static By cntGrpViewBtn = By.xpath("//table[@id='contactgroup_table']/tbody/tr/td[5]/a[3]");
	public static By cntGrpViewCloseBtn = By.xpath("//div[@id='showdisplay_view']/feildset/div[4]/button");
	public static By cntGrpViewPopup = By.xpath("//div[@id='showdisplay_view']");
	
	//	RELATIONSHIP
	public static By cntGrpRltnContactsSearchBox = By.xpath("//input[@aria-controls='rel_contacts']");
	public static By cntGrpRltnContactsTtl = By.xpath("//table[@id='rel_contacts']//tr");
	public static By cntGrpRltnContactsSrchRslt = By.xpath("//table[@id='rel_contacts']//tr/td[2]");
	
	public static By cntGrpRltnTasksSearchBox = By.xpath("//input[@aria-controls='rel_task']");
	public static By cntGrpRltnTasksTtl = By.xpath("//table[@id='rel_task']//tr");
	public static By cntGrpRltnTasksSrchRslt = By.xpath("//table[@id='rel_task']//tr/td[2]");
	
	public static By cntGrpRltnTitleName = By.id("show-lbl-rel");
	
	////////////         Export CSV Files       ////////////
	public static By cntGrpExportBtn=By.xpath("html/body/div[4]/div[1]/div/div[2]/div[2]/div/div[1]/p/a[4]");
	
	//// View
	public static By viewCntGrpName=By.xpath("//div[@id='v_contg_name']");
	public static By viewCntGrpPurpose=By.xpath("//div[@id='v_contg_purpose']");
	public static By viewCntGrpCmnts=By.xpath("//div[@id='v_contg_comments']");
	public static By viewCntGrpCloseBtn=By.xpath("//div[@id='showdisplay_view']/feildset/div[4]/button");

}
