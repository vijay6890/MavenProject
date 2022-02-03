package ObjectRepository;

import org.openqa.selenium.By;


public class Threats {
	
	
	public static By threatsInMainMenu = By.xpath("//a[contains(@href,'/threats')]");
	
	public static By thrOvrInfoBtn = By.cssSelector("a.btn-help.Threats-help");
	public static By thrOvrAddBtn = By.xpath("//a[@class='btn btn-help btn-small act']");
	public static By thrOvrDeleteBtn = By.xpath("//a[contains(text(),'DELETE')][@class='btn btn-documents btn-small']");
	
	//	THREATS LIST VIEW
	public static By thrThreatsListViewCnt = By.xpath("//table[@id='threat_table']//tbody/tr");
	
	public static By thrLstviewFstEdtBtn = By.xpath("//table[@id='threat_table']//tbody/tr/td[5]/a");
	public static By thrLstviewFstDelBtn = By.xpath("//table[@id='threat_table']//tbody/tr/td[5]/a[2]");
		
	public static By threatsSearchBox = By.xpath("//div[@id='threat_table_filter']/label/input");
	public static By thrSrchRslt = By.xpath("//table[@id='threat_table']/tbody/tr/td[2]");
	
	public static By thrNoMatchngRecsFoundMsg = By.xpath("//table[@id='threat_table']/tbody/tr/td");
	
	public static By threatsListView = By.id("threat_table");
	public static By thrSelectAllChkBox = By.id("chk_source_table");
	
	public static By thrThreatsSearchBox = By.cssSelector("input[aria-controls='threat_table']");
	
	public static By listViewPagination = By.xpath("//div[3]/div[1]/div[2]/div/div/div[1]/div[2]/div[1]/div/div/div/div[2]/div[2]/div/ul/li");
	public static By pagiEndArw = By.linkText(">>");
	public static By pagiStartArw = By.linkText("<<");
	public static By pagiPreviousArw = By.linkText("<");
	public static By pagiNextArw = By.linkText(">");
	
	public static By thrListViewRecsInfo = By.id("threat_table_info");
	public static By thrListViwLength = By.name("threat_table_length");
	
	public static By thrViewBtn = By.xpath("//table[@id='threat_table']/tbody/tr/td[5]/a[3]");
	public static By thrViewPopup = By.xpath("//div[@id='content']/div[2]/div[1]/div[2]");
	public static By thrViewCloseBtn = By.xpath("//div[@id='showdisplay_view']/fieldset/div[4]/button");
	
	public static By thrEditBtn = By.xpath("//table[@id='threat_table']/tbody/tr/td[5]/a[1]");
	
	//	ADD NEW THREATS
	public static By thrThreatName = By.name("threat_name");
	
	public static By thrSelectAddNewThreatdrpDown = By.xpath("//div[@id='threat_name_chzn']/a");
	
	public static By thrClickToAddTxtInDD = By.cssSelector("li[class='no-results opt-results']");
	public static By thrNewThrAdConfPp = By.xpath("//div[@class='bootbox modal fade bootbox-confirm in']/div/div");
	
	public static By thrSelectdThrNameInEdt = By.xpath("//a[contains(@href,'javascript:void(0)')]/span");
	public static By thrSelectdThrTypeInEdt = By.xpath("//div[@id='threat_type_chzn']/a/span");
	public static By thrComments = By.id("threat_comments");
	
	public static By thrAddNewThreatConfPp = By.xpath("//div[@class='bootbox modal fade bootbox-confirm in']/div/div");
	public static By thrThreatAddConfPpMsg = By.xpath("//div[@class='bootbox modal fade bootbox-confirm in']/div/div/div/div");
	public static By thrThreatAddConfCancelBtn = By.xpath("//div[7]/div/div/div[2]/button[1]");
	public static By thrThreatAddConfOkBtn = By.xpath("//div[7]/div/div/div[2]/button[2]");
	
	public static By thrThreatTypeDDArw = By.xpath("//div[@id='threat_type_chzn']/a/div");
	public static By thrThreatTypeSearchBox = By.xpath("//div[@id='threat_type_chzn']/div/div/input");
	public static By thrThreatTypeDDTtlVal = By.xpath("//div[@id='threat_type_chzn']/div/ul/li");
		
	public static By thrSubmitBtn = By.id("threat_submit");
	public static By thrClearBtn = By.xpath("//form[@id='threats_form']/fieldset/div/div[3]/div/input[1]");
	public static By thrCancelBtn = By.xpath("//form[@id='threats_form']/fieldset/div/div[3]/div/input[2]");
		
	public static By thrHelpPp = By.id("Threats-help");
	
	public static By thrHideBtn = By.cssSelector("a.btn-minimize");
	
	public static By thrmsgNotificationBar =By.cssSelector("div.notify-message-bar");
	
	//	DELETE
	public static By deleteConfPopup = By.xpath("//div[@class='bootbox modal fade bootbox-confirm in']/div/div");
	
	public static By deleteConfPpMessage = By.cssSelector("div.bootbox-body");
	
	//	SC EDITOR
	public static By scEdtrFrame = By.xpath("//div[@class='sceditor-container ltr']/iframe");
	public static By scEdtrCommentsField = By.cssSelector("body[dir='ltr']");
	
	//	RELATIONSHIP
	public static By selectdNmeInRltnBar = By.id("show-lbl-rel");
	
	public static By rltnLocatnTbEmpty = By.xpath("//table[@id='rel_facilities']/tbody/tr/td");
	public static By rltnTskGrpTbEmpty = By.xpath("//table[@id='rel_task_grp']/tbody/tr/td");
	public static By rltnMapLocationsPp = By.id("MapModal");
	
	public static By rltnThreatNmInMapPp = By.xpath("//div[@id='MapModal']/div/h3");
	
	public static By thrRltnLocatnSearchBox = By.cssSelector("input[aria-controls='rel_facilities']");
	public static By thrTtlLocatnsInRltnTbl = By.xpath("//table[@id='rel_facilities']//tbody/tr");
	public static By thrLocatnRltnSrchRslt = By.xpath("//table[@id='rel_facilities']//tbody/tr/td");
	
	public static By thrRltnTskGrpsSearchBox = By.cssSelector("input[aria-controls='rel_task_grp']");
	public static By thrTtlTskGrpsInRltnTbl = By.xpath("//table[@id='rel_task_grp']//tbody/tr");
	public static By thrTskGrpsRltnSrchRslt = By.xpath("//table[@id='rel_task_grp']//tbody/tr/td");
	
	public static By thrRelatnHideBtn = By.xpath("//div[@id='content']/div/div/div[2]/div/div/a");
	
	
	//Export 
	
	public static By thrExportBtn = By.xpath("html/body/div[4]/div[1]/div/div[2]/div[2]/div/div[1]/p/a[4]");
	
	//view
	
	public static By viewThrNme=By.xpath("//span[@id='v_threat_name']");
	public static By viewThrType=By.xpath("//span[@id='v_threat_type']");
	public static By viewThrCmts=By.xpath("//div[@id='v_threat_comments']");
	
			
	
	

	
}
