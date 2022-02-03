package ObjectRepository;

import org.openqa.selenium.By;

public class Loc_SubModule_Mapping {
	
	public static By emp_View=By.xpath("//table[@id='rel_resources']/tbody/tr/td/a");
	public static By Rel_Threats=By.xpath("//a[@href='#Threats']");
	
	public static By subAddRlnRemoveBtn = By.xpath("//a[@class='label btn-primary btn-addremove-nrelation']");
	
	public static By subTasks=By.xpath("//div[contains(text(),'Tasks')]");
	public static By subBusFns=By.xpath("//div[contains(text(),'Business Functions')]");
	
	public static By subSubmit=By.xpath("//table[@id='rel_resources']/tbody/tr[2]/td/div/div/div[2]/div[1]/div[2]/div[1]/div[2]/a[2]");
	public static By subClose =  By.cssSelector("a['btn btn-small btn-cancel-nrelation']");
	
	//Pagination Entries information
	public static By teamsRecInfo=By.xpath("//div[@id='resourcegroup_nestable_273dfa04-6d17-9f67-c655-5809bd3aa4bb_info']");
	public static By teamsShowEntLength=By.name("resourcegroup_nestable_273dfa04-6d17-9f67-c655-5809bd3aa4bb_length");
	public static By sub_TeamsListViewPagination=By.xpath(".//*[@id='resourcegroup_nestable_273dfa04-6d17-9f67-c655-5809bd3aa4bb_wrapper']/div[2]/div[2]/div/ul/li/a");
	public static By pagiEndArw = By.linkText(">>");
	public static By pagiStartArw = By.linkText("<<");
	public static By pagiPreviousArw = By.linkText("<");
	public static By pagiNextArw = By.linkText(">");
	
	//submodule mapping
	public static By locEmpTeamsTtlCnt=By.xpath("//table[@id='rel_resources']/tbody/tr[2]/td/div/div/div[2]/div[1]/div[2]/div[1]/div[1]/div/div[1]/select/option");
	public static By locEmpInsTtlCnt=By.xpath("//table[@id='rel_resources']/tbody/tr[2]/td/div/div/div[2]/div[2]/div[2]/div[1]/div[1]/div/div[1]/select/option");
	public static By locEmpTskTtlCNt=By.xpath("//table[@id='rel_resources']/tbody/tr[2]/td/div/div/div[2]/div[3]/div[2]/div[1]/div[1]/div/div[1]/select/option");
	public static By locEmpBsFnTtlCnt=By.xpath("//table[@id='rel_resources']/tbody/tr[2]/td/div/div/div[2]/div[4]/div[2]/div[1]/div[1]/div/div[1]/select/option");
	
	public static By locEmpTmsSubmitBtn=By.xpath("//table[@id='rel_resources']/tbody/tr[2]/td/div/div/div[2]/div[1]/div[2]/div[1]/div[2]/a[2]");
	public static By locEmpInsSubmitBtn=By.xpath("//table[@id='rel_resources']/tbody/tr[2]/td/div/div/div[2]/div[2]/div[2]/div[1]/div[2]/a[2]");
	public static By locEmpTskSubmitBtn=By.xpath("//table[@id='rel_resources']/tbody/tr[2]/td/div/div/div[2]/div[3]/div[2]/div[1]/div[2]/a[2]");
	public static By locEmpBsFnSubmitBtn=By.xpath("//table[@id='rel_resources']/tbody/tr[2]/td/div/div/div[2]/div[4]/div[2]/div[1]/div[2]/a[2]");
	
	//Mapping
	public static By subMapPopupLftBox = By.name("map_list[]_helper1");
	public static By subMapLftTtlVal=By.xpath("//select[@id='bootstrap-duallistbox-nonselected-list_map_list[]']/option");
	public static By subMapRgtTtlVal=By.xpath("//select[@id='bootstrap-duallistbox-selected-list_map_list[]']/option");	
	public static By subMapPoupRgtBox = By.name("map_list[]_helper2");
	
	public static By subLftSearchBox = By.xpath("//table[@id='rel_resources']/tbody/tr[2]/td/div/div/div[2]/div[1]/div[2]/div[1]/div[1]/div/div[1]/input");
	public static By subRgtSearchBox = By.xpath("//table[@id='rel_resources']/tbody/tr[2]/td/div/div/div[2]/div[1]/div[2]/div[1]/div[1]/div/div[2]/input");
	
	public static By subMapMoveAllArrow = By.cssSelector("button.moveall");
	public static By subMapMoveArrow = By.cssSelector("button.move");
	
	public static By subMapRemoveAllArrow = By.cssSelector("button.removeall");
	public static By subMapRemoveArrow = By.cssSelector("button.remove");
	
	public static By subMapPopupFirstNameInLftBox = By.xpath("//select[@name='map_list[]_helper1']/option");
	public static By submapPopupFirstNameInRgtBox = By.xpath("//select[@name='map_list[]_helper2']/option");
	
	
	//Relationship tab 
	
	public static By subTeams=By.xpath("html/body/div[4]/div[1]/div[1]/div[2]/div[2]/div[2]/div[2]/div/div[1]/div/div/table/tbody/tr[2]/td/div/div/div[1]/div[1]");
	public static By subTeamsTblTtl = By.xpath("//table[@id='resourcegroup_nestable_d17afa6a-4cb4-9e7a-1f9d-57ebdcad66fe']/tbody/tr/td");
	public static By subTeamSearchBox = By.xpath("html/body/div[4]/div[1]/div[1]/div[2]/div[2]/div[2]/div[2]/div/div[1]/div/div/table/tbody/tr[2]/td/div/div/div[2]/div[1]/div[2]/div[2]/div/div[1]/div[1]/div/label/input");
	public static By subTeamSearchRslt = By.xpath("//table[@id='resourcegroup_nestable_33b176b1-4213-1bb5-b927-57ebb00d6fcc']/tbody/tr/td");
	
	
	public static By subInsurance=By.xpath("html/body/div[4]/div[1]/div[1]/div[2]/div[2]/div[2]/div[2]/div/div[1]/div/div/table/tbody/tr[2]/td/div/div/div[1]/div[2]");
	public static By subMapInsuLftTtlVal = By.xpath("//select[@id='bootstrap-duallistbox-nonselected-list_map_list[]']/option");
	
	public static By subLftInsuSearchBox = By.xpath("//table[@id='rel_resources']/tbody/tr[2]/td/div/div/div[2]/div[2]/div[2]/div[1]/div[1]/div/div[1]/input");
	public static By subLftTskSearchBox = By.xpath("//table[@id='rel_resources']/tbody/tr[2]/td/div/div/div[2]/div[3]/div[2]/div[1]/div[1]/div/div[1]/input");
	public static By subLftBsFnSearchBox = By.xpath("//table[@id='rel_resources']/tbody/tr[2]/td/div/div/div[2]/div[4]/div[2]/div[1]/div[1]/div/div[1]/input");
	
	public static By subRgtInsuSearchBox=By.xpath("//table[@id='rel_resources']/tbody/tr[2]/td/div/div/div[2]/div[2]/div[2]/div[1]/div[1]/div/div[2]/input");
	public static By subRgtTskSearchBox=By.xpath("//table[@id='rel_resources']/tbody/tr[2]/td/div/div/div[2]/div[3]/div[2]/div[1]/div[1]/div/div[2]/input");
	public static By subRgtBsFnSearchBox=By.xpath("//table[@id='rel_resources']/tbody/tr[2]/td/div/div/div[2]/div[4]/div[2]/div[1]/div[1]/div/div[2]/input");
	
	public static By locEmpInsLftFstName=By.xpath("//table[@id='rel_resources']/tbody/tr[2]/td/div/div/div[2]/div[2]/div[2]/div[1]/div[1]/div/div[1]/select/option[1]");
	public static By locEmpTskLftFstName=By.xpath("//table[@id='rel_resources']/tbody/tr[2]/td/div/div/div[2]/div[3]/div[2]/div[1]/div[1]/div/div[1]/select/option[1]");
	public static By locEmpBsFnLftFstName=By.xpath("//table[@id='rel_resources']/tbody/tr[2]/td/div/div/div[2]/div[4]/div[2]/div[1]/div[1]/div/div[1]/select/option[1]");
	
	public static By locEmpInsRgtFstName=By.xpath("//table[@id='rel_resources']/tbody/tr[2]/td/div/div/div[2]/div[2]/div[2]/div[1]/div[1]/div/div[2]/select/option[1]");
	public static By locEmpTskRgtFstName=By.xpath("//table[@id='rel_resources']/tbody/tr[2]/td/div/div/div[2]/div[3]/div[2]/div[1]/div[1]/div/div[2]/select/option[1]");
	public static By locEmpBsFnRgtFstName=By.xpath("//table[@id='rel_resources']/tbody/tr[2]/td/div/div/div[2]/div[4]/div[2]/div[1]/div[1]/div/div[2]/select/option[1]");
	
	public static By locEmpInsLftMoveAll=By.xpath("//table[@id='rel_resources']/tbody/tr[2]/td/div/div/div[2]/div[2]/div[2]/div[1]/div[1]/div/div[1]/div[1]/button[1]");
	public static By locEmpTskLftMoveAll=By.xpath("//table[@id='rel_resources']/tbody/tr[2]/td/div/div/div[2]/div[3]/div[2]/div[1]/div[1]/div/div[1]/div[1]/button[1]");
	public static By locEmpBsFnLftMoveAll=By.xpath("//table[@id='rel_resources']/tbody/tr[2]/td/div/div/div[2]/div[4]/div[2]/div[1]/div[1]/div/div[1]/div[1]/button[1]");
	
	public static By locEmpInsRgtMoveAll=By.xpath("//table[@id='rel_resources']/tbody/tr[2]/td/div/div/div[2]/div[2]/div[2]/div[1]/div[1]/div/div[2]/div[1]/button[2]");
	public static By locEmpTskRgtMoveAll=By.xpath("//table[@id='rel_resources']/tbody/tr[2]/td/div/div/div[2]/div[3]/div[2]/div[1]/div[1]/div/div[2]/div[1]/button[2]");
	public static By locEmpBsFnRgtMoveAll=By.xpath("//table[@id='rel_resources']/tbody/tr[2]/td/div/div/div[2]/div[4]/div[2]/div[1]/div[1]/div/div[2]/div[1]/button[2]");
   //employees
	
	public static By locEmpTotalCount=By.xpath("//table[@id='rel_resources']/tbody/tr");
	public static By locEmpExist=By.xpath("//table[@id='rel_resources']/tbody/tr[1]/td[7]");
	
	public static By addRemoveRltnBtn=By.xpath("//table[@id='rel_resources']//div[@class='pull-right']");
	public static By empsubFstVal=By.xpath("//table[@id='rel_resources']//tbody[@role='alert']//tr[@class='tableRow odd']//td[1]");
	public static By insuranceListValues=By.xpath("//select[contains(@id,'ddl_tasks_mdata')]/..//select[@name='map_list[]_helper1']/option");
	//ddl_insurances_mdata
	
	//Teams
	public static By loctTmsViewbtn=By.xpath("//table[@id='rel_resourcegroup']/tbody/tr[1]/td[1]/a");
	
	public static By tmsLocTab=By.xpath("//table[@id='rel_resourcegroup']/tbody/tr[2]/td/div/div/div[1]/div[1]");
	public static By tmsEmpTab=By.xpath("//table[@id='rel_resourcegroup']/tbody/tr[2]/td/div/div/div[1]/div[2]");
	public static By tmsTskTab=By.xpath("//table[@id='rel_resourcegroup']/tbody/tr[2]/td/div/div/div[1]/div[3]");
	
	public static By tmsLocAddRemoveRltnBtn=By.xpath("//table[@id='rel_resourcegroup']/tbody/tr[2]/td/div/div/div[2]/div[1]/div[1]/div[2]/a");
	public static By tmsEmpAddRemoveRltnBtn=By.xpath("//table[@id='rel_resourcegroup']/tbody/tr[2]/td/div/div/div[2]/div[2]/div[1]/div[2]/a");
	public static By tmsTskAddRemoveRltnBtn=By.xpath("//table[@id='rel_resourcegroup']/tbody/tr[2]/td/div/div/div[2]/div[3]/div[1]/div[2]/a");
	
	public static By tmsLocMoveAllLft=By.xpath("//table[@id='rel_resourcegroup']/tbody/tr[2]/td/div/div/div[2]/div[1]/div[2]/div[1]/div[1]/div/div[2]/div[1]/button[2]");
	public static By tmsEmpMoveAllLft=By.xpath("//table[@id='rel_resourcegroup']/tbody/tr[2]/td/div/div/div[2]/div[2]/div[2]/div[1]/div[1]/div/div[2]/div[1]/button[2]");
	public static By tmsTskMoveAllLft=By.xpath("//table[@id='rel_resourcegroup']/tbody/tr[2]/td/div/div/div[2]/div[3]/div[2]/div[1]/div[1]/div/div[2]/div[1]/button[2]");
	
	public static By tmsLocTtlCnt=By.xpath("//table[@id='rel_resourcegroup']/tbody/tr[2]/td/div/div/div[2]/div[1]/div[2]/div[1]/div[1]/div/div[1]/select/option");
	public static By tmsEmpTtlCnt=By.xpath("//table[@id='rel_resourcegroup']/tbody/tr[2]/td/div/div/div[2]/div[2]/div[2]/div[1]/div[1]/div/div[1]/select/option");
	public static By tmsTskTtlCnt=By.xpath("//table[@id='rel_resourcegroup']/tbody/tr[2]/td/div/div/div[2]/div[3]/div[2]/div[1]/div[1]/div/div[1]/select/option");
	
	public static By tmsLocLftSearchBox=By.xpath("//table[@id='rel_resourcegroup']/tbody/tr[2]/td/div/div/div[2]/div[1]/div[2]/div[1]/div[1]/div/div[1]/input");
	public static By tmsEmpLftSearchBox=By.xpath("//table[@id='rel_resourcegroup']/tbody/tr[2]/td/div/div/div[2]/div[2]/div[2]/div[1]/div[1]/div/div[1]/input");
	public static By tmsTskLftSearchBox=By.xpath("//table[@id='rel_resourcegroup']/tbody/tr[2]/td/div/div/div[2]/div[3]/div[2]/div[1]/div[1]/div/div[1]/input");
	
	public static By tmsLocMoveRgt=By.xpath("//table[@id='rel_resourcegroup']/tbody/tr[2]/td/div/div/div[2]/div[1]/div[2]/div[1]/div[1]/div/div[1]/div[1]/button[2]");
	public static By tmsEmpMoveRgt=By.xpath("//table[@id='rel_resourcegroup']/tbody/tr[2]/td/div/div/div[2]/div[2]/div[2]/div[1]/div[1]/div/div[1]/div[1]/button[2]");
	public static By tmsTskMoveRgt=By.xpath("//table[@id='rel_resourcegroup']/tbody/tr[2]/td/div/div/div[2]/div[3]/div[2]/div[1]/div[1]/div/div[1]/div[1]/button[2]");
	
	public static By tmsLocRgtSearchBox=By.xpath("//table[@id='rel_resourcegroup']/tbody/tr[2]/td/div/div/div[2]/div[1]/div[2]/div[1]/div[1]/div/div[2]/input");
	public static By tmsEmpRgtSearchBox=By.xpath("//table[@id='rel_resourcegroup']/tbody/tr[2]/td/div/div/div[2]/div[2]/div[2]/div[1]/div[1]/div/div[2]/input");
	public static By tmsTskRgtSearchBox=By.xpath("//table[@id='rel_resourcegroup']/tbody/tr[2]/td/div/div/div[2]/div[3]/div[2]/div[1]/div[1]/div/div[2]/input");
	
	public static By tmsLocLftFstVal=By.xpath("//table[@id='rel_resourcegroup']/tbody/tr[2]/td/div/div/div[2]/div[1]/div[2]/div[1]/div[1]/div/div[1]/select/option[1]");
	public static By tmsEmpLftFstVal=By.xpath("//table[@id='rel_resourcegroup']/tbody/tr[2]/td/div/div/div[2]/div[2]/div[2]/div[1]/div[1]/div/div[1]/select/option[1]");
	public static By tmsTskLftFstVal=By.xpath("//table[@id='rel_resourcegroup']/tbody/tr[2]/td/div/div/div[2]/div[3]/div[2]/div[1]/div[1]/div/div[1]/select/option[1]");
	
	public static By tmsLocRgtFstVal=By.xpath("//table[@id='rel_resourcegroup']/tbody/tr[2]/td/div/div/div[2]/div[1]/div[2]/div[1]/div[1]/div/div[2]/select/option[1]");
	public static By tmsEmpRgtFstVal=By.xpath("//table[@id='rel_resourcegroup']/tbody/tr[2]/td/div/div/div[2]/div[2]/div[2]/div[1]/div[1]/div/div[2]/select/option[1]");
	public static By tmsTskRgtFstVal=By.xpath("//table[@id='rel_resourcegroup']/tbody/tr[2]/td/div/div/div[2]/div[3]/div[2]/div[1]/div[1]/div/div[2]/select/option[1]");
	
	public static By tmsLocSubmitBtn=By.xpath("//table[@id='rel_resourcegroup']/tbody/tr[2]/td/div/div/div[2]/div[1]/div[2]/div[1]/div[2]/a[2]");
	public static By tmsEmpSubmitBtn=By.xpath("//table[@id='rel_resourcegroup']/tbody/tr[2]/td/div/div/div[2]/div[2]/div[2]/div[1]/div[2]/a[2]");
	public static By tmsTskSubmitBtn=By.xpath("//table[@id='rel_resourcegroup']/tbody/tr[2]/td/div/div/div[2]/div[3]/div[2]/div[1]/div[2]/a[2]");
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
