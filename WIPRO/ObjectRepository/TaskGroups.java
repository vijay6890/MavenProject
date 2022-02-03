package ObjectRepository;

import org.openqa.selenium.By;

public class TaskGroups {

	public static By taskGroupsInMainMenu = By.xpath("//a[contains(@href,'/task-groups')]");
	
	public static By tskGrpOvrInfoBtn = By.xpath("//a[@class='btn btn-help btn-small Task-Groups-help']");
	public static By tskGrpOvrAddBtn = By.xpath("//a[@class='btn btn-help btn-small act']");
	public static By tskGrpOvrDeleteBtn = By.xpath("//a[@class='btn btn-documents btn-small']");
	public static By tskGrpOvrExportDataBtn = By.id("export");
	
	public static By tskGrpHelpPopup = By.id("Task-Groups-help");
	public static By tskGrpHelpPopupCloseBtn = By.xpath("//div[@id='Task-Groups-help']/div[3]/button");
	
	//	TASK GROUPS LIST VIEW
	public static By tskGrpTaskGroupsSearchBox = By.cssSelector("input[aria-controls='task_group_table']");
		
	public static By tskGrpTaskGroupNameTtl = By.xpath("//table[@id='task_group_table']//tbody/tr");
	
	public static By tskGrpLstviewFstEdtBtn = By.xpath("//table[@id='task_group_table']//tr/td[4]/a");
	public static By tskGrpLstviewFstDelBtn = By.xpath("//table[@id='task_group_table']//tr/td[4]/a[2]");
	
	public static By tskGrpTaskGroupName = By.id("task_group_name");	
	
	public static By tskGrpSubmitBtn = By.id("task_group_submit");
	public static By tskGrpClearBtn = By.xpath("//input[@class='btn validate-cancel {taskgroups_form}']");
	public static By tskGrpCancelBtn = By.cssSelector("input[value='Cancel']");
	
	public static By tskGrpLstViewSearchRslt = By.xpath("//table[@id='task_group_table']//tr/td[2]");
	
	public static By tskGrpSelectAllChkBox = By.id("chk_source_table");
	
	public static By tskGrpListViewPagination = By.xpath("//div[@id='task_group_table_wrapper']//div/div[2]/div/ul/li");
	
	public static By tskGrpListViewLength = By.name("task_group_table_length");
	public static By tskGrpListViewRecsInfo = By.id("task_group_table_info");
	
	public static By tskGrpViewBtn = By.xpath("//table[@id='task_group_table']/tbody/tr/td[4]/a[3]");
	public static By tskGrpViewPopup = By.xpath("//div[@id='content']/div[2]/div[1]/div[2]");
	public static By tskGrpViewCloseBtn = By.xpath("//div[@id='showdisplay_view']/fieldset/div[2]/div/input");
	
	//	RELATIONSHIP
	public static By tskGrpRelationshipTitleNm = By.id("show-lbl-rel");
	
	public static By tskGrpRltnTasksSearchBox = By.xpath("//input[@aria-controls='rel_task']");
	public static By tskGrpRltnTasksCnt = By.xpath("//table[@id='rel_task']//tr");
	public static By tskGrpRltnTasksSrchRslt = By.xpath("//table[@id='rel_task']//tr/td");
	
	//export 
	
	public static By tskGrpExportBtn = By.xpath("html/body/div[4]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/p/a[4]");
	
    // task group inner relationship table 		
     public static By tasksTabRltSection = By.xpath("//span[@class='hidden-phone hidden-tablet' and contains(text(),'Tasks')]");
     public static By taskRightMoveAllArrowRltWindow = By.xpath("//label[contains(text(),'Mapped Tasks')]/..//button[@class='btn removeall']");
     public static By tasksLeftRltWindowValues = By.xpath("//label[contains(text(),'List of Tasks')]/..//button[@class='btn moveall']/../../select//option");
	 public static By tasksLeftSearchTxtBoxRltWindow = By.xpath("//label[contains(text(),'List of Tasks')]/../input[@class='filter']");
	 public static By tasksRightSearchTxtBoxRltWindow = By.xpath("//label[contains(text(),'Mapped Tasks')]/../input[@class='filter']");
	 public static By tasksLeftMoveBtnRltWindow = By.xpath("//label[contains(text(),'List of Tasks')]/..//button[@class='btn move']");
	 public static By tasksViewBtnRltWindow = By.xpath("//table[@id='rel_task']//a[@class='btn btn-small btn-primary btn-nestable-action']");
	 public static By tasksAddRemoveBtnInnerRltTable = By.xpath("//table[@id='rel_task']//a[@class='label btn-primary btn-addremove-nrelation']");
	 public static By tasksRightMoveAllArrowInnerRltTable = By.xpath("//table[@id='rel_task']//button[@class='btn removeall']");
	 public static By tasksLeftInnerRltValues = By.xpath("//table[@id='rel_task']//select[@name='map_list[]_helper1']/option");
	 public static By tasksSearchTxtBoxInnerRltTable = By.xpath("//table[@id='rel_task']//input[@class='filter']");
	 public static By tasksRltTabValues = By.xpath("//table[@id='rel_task']//div[contains(@class,'span12 module-tabitem')]");
	 public static By tasksRightRltValues = By.xpath("//table[@id='rel_task']//select[@name='map_list[]_helper2']/option"); 
	 public static By tasksValueAfterMapping = By.xpath("//table[@id='rel_task']//tbody[@role='alert']//tr[@class='tableRow odd']//td[1]");
	 public static By tasksSubmitBtnInnerRltTable = By.xpath("//table[@id='rel_task']//a[@class='btn btn-small btn-primary btn-save-nrelation']");
	 public static By tasksLeftMoveArrowInnerRltTable = By.xpath("//table[@id='rel_task']//button[@class='btn move']");
	 
	 
	 // View
	 
	 public static By viewTskGrpName=By.xpath("//div[@id='v_taskg_name']");
	 public static By viewTskGrpCmnts=By.xpath("//div[@id='v_taskg_comments']");
	 public static By viewTskGrpCloseBtn=By.xpath("//div[@id='showdisplay_view']/fieldset/div[2]/div/input");
	 
	 	
}
