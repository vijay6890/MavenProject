package ObjectRepository;

import org.openqa.selenium.By;

public class DRPlan {
	
	public static By drThreatsnDRPlanInMainMenu = By.xpath("//a[contains(@href,'bcplan')]");
	
	public static By drOvrInfoBtn = By.cssSelector("a.DR-Plan-help");
	public static By drOvrAddBtn  = By.cssSelector("a[class='btn btn-help btn-small act']");
	public static By drOvrDeleteBtn = By.cssSelector(".btn.btn-documents.btn-small");
	public static By drOvrExportDataBtn = By.id("export");
	public static By drManageDRTemp=By.xpath("//div[@id='content']/div[2]/div[1]/p/a[5]");
	
	public static By drThreatsDRPlanTxt = By.xpath("//div[@id='content']//div[2]/div/h2");
	
	
	//	THREATS/DR PLAN LIST VIEW
	public static By drDRPlanSearchBox = By.cssSelector("input[aria-controls='plan_table']");
	
	public static By drDRPlanLstViewTtlCnt = By.xpath("//table[@id='plan_table']//tbody/tr");
	
	public static By drPlnLstViewFstDelBtn = By.xpath("//table[@id='plan_table']//tr/td[7]/a[4]"); 
	
	public static By drEditBtn = By.xpath("//table[@id='plan_table']/tbody/tr[1]/td[8]/a[1]");
	public static By drPreviewBtn = By.xpath("//button[contains(text(),'Preview')]");
	public static By drPDFBtn = By.xpath("//table[@id='plan_table']//tr[1]/td[7]/a[3]");
	public static By drDeleteBtn = By.xpath("//table[@id='plan_table']//tr[1]/td[7]/a[4]");
	
	public static By drListViewPagination = By.xpath("//*[@id='plan_table_wrapper']/div[2]/div[2]/div/ul/li");
	
	public static By drListViewLength = By.name("plan_table_length");
	public static By drListViewRecsInfo = By.id("plan_table_info");	
	
	public static By drPreviewCloseBtn = By.cssSelector("a.dynamcic-plan-op-close-btn");
	
	public static By drViewPDFRevisionsBtn = By.xpath("//div[@id='download-drp']/a");
	public static By drGeneratePdfOptn = By.xpath("//div[@id='download-drp']//ul//a");
	public static By drFstRecPlanNm=By.xpath("//table[@id='plan_table']//tbody/tr[1]/td[2]");
	
	//	CREATE NEW DR PLAN
	public static By drlblPlanName = By.xpath("//form[@id='frmSavePlanInformation']/div[1]/div[1]/div[1]/div/label/b");
	public static By drlblPlanAuthor = By.xpath("//form[@id='frmSavePlanInformation']/div[1]/div[1]/div[2]/div/label/b");
	public static By drlblPlanLocation = By.xpath("//form[@id='frmSavePlanInformation']/div[1]/div[1]/div[3]/div/label/b");
	public static By drlblPlanReviewer = By.xpath("//form[@id='frmSavePlanInformation']/div[1]/div[1]/div[4]/div/label/b");
	public static By drlblThreats = By.xpath("//form[@id='frmSavePlanInformation']/div[1]/div[2]/div[1]/div/label/b");
	
	public static By drPlanName = By.id("txtDRPlanName");
	public static By drPlanNamee = By.id("//input[@id='txtDRPlanName']");
	
	
	public static By drPlanAuthor = By.id("ddlPlanOwner_chzn");
	public static By drPlanAuthorDefTxt = By.xpath("//div[@id='ddlPlanOwner_chzn']/a/span");
	public static By drPlanAuthorDDArrow = By.xpath("//div[@id='ddlPlanOwner_chzn']/a/div");
	public static By drPlanAuthorDDCnt = By.xpath("//div[@id='ddlPlanOwner_chzn']/div/ul/li");
	public static By drPlanAuthorDDSearchBox = By.xpath("//div[@id='ddlPlanOwner_chzn']/div/div/input");
	
	public static By drPlanLocationDefTxt = By.xpath("//div[@id='ddlPlanLocation_chzn']/a/span");
	public static By drPlanLocationDDArrow = By.xpath("//div[@id='ddlPlanLocation_chzn']/a/div");			
	public static By drPlanLocationDDCnt = By.xpath("//div[@id='ddlPlanLocation_chzn']//ul/li");
	public static By drPlanLocationDDSearchBox = By.xpath("//div[@id='ddlPlanLocation_chzn']/div/div/input");
	
	public static By drPlanReviewerDefTxt = By.xpath("//div[@id='ddlPlanReviewer_chzn']/a/span");
	public static By drPlanReviewerDDArrow = By.xpath("//div[@id='ddlPlanReviewer_chzn']/a/div");
	public static By drPlanReviewerDDCnt = By.xpath("//div[@id='ddlPlanReviewer_chzn']//ul/li");
	public static By drPlanReviewerDDSearchBox = By.xpath("//div[@id='ddlPlanReviewer_chzn']/div/div/input");
	
	public static By drThreats = By.xpath("//div[@id='ddlPossibleThreats_chzn']/ul/li/input");
	public static By drThreatsDrpDownVal = By.id("ddlPossibleThreats_chzn_o_0");
	public static By drThreatsDrpDownVal1 = By.xpath("//li[@id='ddlPossibleThreats_chzn_c_0']/span");
	public static By drThreatsTotalCount=By.xpath("//div[@id='ddlPossibleThreats_chzn']/div/ul/li");
	
	public static By drSaveAndContinueBtn = By.xpath("//a[contains(text(),'Save and Continue')]"); 
	public static By drClearBtn = By.cssSelector("input[class='btn act-clr btn-primary btn-small']");
	public static By drCancelBtn = By.id("btnCancelEditPlanInformation");
	
	
	
	//	EDIT PLAN INFORMATION
	public static By drEdtCollapseAllSectionsBtn = By.id("btnCollapseAllSections");
	public static By drEdtTtlSectionCnt=By.xpath("//div/div[@class='span12 mrgleft0 dynamcic-plan-section-viewmode dynamcic-plan-section-sorthand'][@style='']");
	
	public static By drEdtDynamicTemplatesCnt = By.xpath("//div[@id='divDynamicDRPlanSections']/div");
	
	public static By drEdtfromCancelBtn = By.id("btnCancelEditPlanInformation");
	
		
	public static By drEdtFstCreateNewSectionBtn = By.xpath("//div[@id='divDRPlanActions']/button[2]");
	public static By drEdtEditPlanInformationBtn = By.xpath("//div[@id='divDRPlanActions']/button[1]");
	
	public static By drEdtPreviewBtn = By.xpath("//div[@id='divDRPlanActions']/button[3]");
	public static By drEdtGeneratePDFBtn = By.xpath("//div[@id='divDRPlanActions']/button[4]");
	public static By drEdtDownloadDRPlanBtn = By.id("download-drp");
	
	
	public static By drEdtPdfPlanName=By.xpath("//div[@id='pageContainer1']/xhtml:div[2]/xhtml:div[2]");
	
	public static By drEdtPreviewCloseBtn = By.xpath("//a[@class='btn btn-round dynamcic-plan-op-close-btn']");
		
	public static By drEdtSecCreateNewSectionBtn = By.xpath("//div[@id='divDynamicDRPlanParent']/div/a");
	public static By drEdtCancelBtn = By.xpath("//div[@id='divDynamicDRPlanParent']/div[3]/a[2]");
	
	public static By drEdtSectionDeleteBtn = By.xpath("//a[@class='btn btn-small pull-right btn-delete-section']"); 
	
	public static By drEdtSecAuthorizedByLblNm = By.xpath("//div[@id='divDynamicDRPlanSections']/div/div[2]/div[2]/div[2]/div[1]/span/b");
	public static By drEdtSecAuthorizedByNm = By.xpath("//span[@class='dynamcic-section-authorizedby']");
	
	public static By drEdtSecVerifiedByLblNm = By.xpath("//div[@id='divDynamicDRPlanSections']//div[@class='pull-left row-fluid'][2]/span/b");
	public static By drEdtSecVerifiedByNm = By.xpath("//span[@class='dynamcic-section-verifiedby']");
	
	
	
	//	CREATE NEW SECTION POP UP
	public static By edtSecEditSectionBtn = By.xpath("//div[@id='divDynamicDRPlanSections']//button[4]");
	public static By createNewSectionPopup = By.id("divCreateNewDynamicSection");
	public static By clickToAddTxtInDD = By.cssSelector("li[class='no-results opt-results']");
	public static By clickAddNewTitle=By.xpath("//li[@class='no-results opt-results'][contains(text(),'Click to add')]");
	
	public static By sectionAddConfPopup = By.xpath("//div[@class='bootbox modal fade bootbox-confirm in']/div");
	
	public static By secPpCreateNewSectionTtl = By.id("myModalLabel_CNDS");
	
	public static By secPpSectionTitleDefTxt = By.xpath("//div[@id='ddlNewDynamicSectionTitle_chzn']/a/span");
	public static By secPpSectionTitleDDArrow = By.xpath("//div[@id='ddlNewDynamicSectionTitle_chzn']/a/div");
	public static By secPpSectionTitleDDCnt = By.xpath("//div[@id='ddlNewDynamicSectionTitle_chzn']//ul/li");
	public static By secPpSectionTitleDDSearchBox = By.xpath("//div[@id='ddlNewDynamicSectionTitle_chzn']/div/div/input");

	public static By secPpAuthorizedByDefTxt = By.xpath("//div[@id='ddlDSAuthorizedBy_chzn']/a/span");
	public static By secPpAuthorizedByDDArrow = By.xpath("//div[@id='ddlDSAuthorizedBy_chzn']/a/div");
	public static By secPpAuthorizedByDDCnt = By.xpath("//div[@id='ddlDSAuthorizedBy_chzn']//ul/li");
	public static By secPpAuthorizedByDDSearchBox = By.xpath("//div[@id='ddlDSAuthorizedBy_chzn']/div/div/input");
	
	public static By secPpVerifiedByDefTxt = By.xpath("//div[@id='ddlDSVerifiedBy_chzn']/a/span");
	public static By secPpVerifiedByDDArrow = By.xpath("//div[@id='ddlDSVerifiedBy_chzn']/a/div");
	public static By secPpVerifiedByDDCnt = By.xpath("//div[@id='ddlDSVerifiedBy_chzn']//ul/li");
	public static By secPpVerifiedByDDSearchBox = By.xpath("//div[@id='ddlDSVerifiedBy_chzn']/div/div/input");
	
	public static By secPpSectionTitleDDVal = By.xpath("//div[@id='ddlNewDynamicSectionTitle_chzn']/div/ul/li[");
	public static By secPpSectionTitleDDVal1 = By.xpath("]");
	public static By secPpAuthorizedByDDVal = By.xpath("//div[@id='ddlDSAuthorizedBy_chzn']/div/ul/li");
	public static By secPpVerifiedByDDVal = By.xpath("//div[@id='ddlDSVerifiedBy_chzn']/div/ul/li");
	
	public static By crSectionDescription = By.xpath("//div[@class='controls span12']//iframe");
	//form[@id='frmCreateNewDynamicSection']/div/div[4]/div/div/div/iframe");
	public static By secAddedSectionDescription = By.xpath("//div[@class='span12 dynamcic-section-workarea-parent']/div/div/p[1]");
			
	
	public static By secPpSaveBtn = By.xpath("//div[@id='divCreateNewDynamicSection']/div[3]/button");
	public static By secPpCloseBtn = By.xpath("//div[@id='divCreateNewDynamicSection']/div[3]/button[2]");
	public static By secEditCloseBtn=By.xpath("//div[@id='divCreateNewDynamicSection']/div[3]/button[3]");
	public static By secPpCrossCloseBtn = By.xpath("//button[@class='close act']");
	
	public static By collapseAllSectionsBtn = By.id("btnCollapseAllSections");
	public static By expandAllSectionsBtn = By.id("btnExpandAllSections");
	
	
	//public static By newSecPopupTitle=By.xpath("//h3[@id='myModalLabel_CNDS']");
	
	//	SC EDITOR
	public static By scEdtrBoldBtn = By.xpath("//div[@id='divCreateNewDynamicSection']/div[2]/form/div/div[4]/div/div/div/div/div/a/div");
	public static By scEdtrItalicBtn = By.xpath("//div[@id='divCreateNewDynamicSection']/div[2]/form/div/div[4]/div/div/div/div/div/a[2]");
	public static By scEdtrUnderlineBtn = By.xpath("//div[@id='divCreateNewDynamicSection']/div[2]/form/div/div[4]/div/div/div/div/div/a[3]");
	
	public static By scEdtrLeftAlignBtn = By.xpath("//div[@id='divCreateNewDynamicSection']/div[2]/form/div/div[4]/div/div/div/div/div[2]/a");
	public static By scEdtrCenterAlignBtn = By.xpath("//div[@id='divCreateNewDynamicSection']/div[2]/form/div/div[4]/div/div/div/div/div[2]/a[2]"); 
	public static By scEdtrRightAlignBtn = By.xpath("//div[@id='divCreateNewDynamicSection']/div[2]/form/div/div[4]/div/div/div/div/div[2]/a[3]"); 
	public static By scEdtrJustifyBtn = By.xpath("//div[@id='divCreateNewDynamicSection']/div[2]/form/div/div[4]/div/div/div/div/div[2]/a[4]");
	
	public static By scEdtrFontNameBtn = By.xpath("//div[@id='divCreateNewDynamicSection']/div[2]/form/div/div[4]/div/div/div/div/div[3]/a");
	public static By scEdtrFormatSizeBtn = By.xpath("//div[@id='divCreateNewDynamicSection']/div[2]/form/div/div[4]/div/div/div/div/div[3]/a[2]"); 
	public static By scEdtrFontColorBtn = By.xpath("//div[@id='divCreateNewDynamicSection']/div[2]/form/div/div[4]/div/div/div/div/div[3]/a[3]"); 
	public static By scEdtrRemoveFormattingBtn = By.xpath("//div[@id='divCreateNewDynamicSection']/div[2]/form/div/div[4]/div/div/div/div/div[3]/a[4]");
	
	public static By scEdtrBulletListBtn = By.xpath("//div[@id='divCreateNewDynamicSection']/div[2]/form/div/div[4]/div/div/div/div/div[5]/a");
	public static By scEdtrNumberedListBtn = By.xpath("//div[@id='divCreateNewDynamicSection']/div[2]/form/div/div[4]/div/div/div/div/div[5]/a[2]");
	
	public static By scEdtrInsertTableBtn = By.xpath("//div[@id='divCreateNewDynamicSection']/div[2]/form/div/div[4]/div/div/div/div/div[6]/a");
	public static By scEdtrMaximizeBtn = By.xpath("//div[@id='divCreateNewDynamicSection']/div[2]/form/div/div[4]/div/div/div/div/div[8]/a");
	
	public static By scEdtrFntVerdana = By.xpath("//div[@class='sceditor-dropdown sceditor-font-picker']/div/a[11]/font");
	public static By scEdtrFormatSizeFiv = By.xpath("//div[@class='sceditor-dropdown sceditor-fontsize-custom']/div/a[5]");
	public static By scEdtrChooseColorRose = By.xpath("//div[@class='sceditor-dropdown sceditor-color-picker']/div/div/a[18]");
	public static By scEdtrChooseColorBlue = By.xpath("//div[@class='sceditor-dropdown sceditor-color-picker']/div/div[17]/a[17]"); 
	
	
	//	SECTIONS LIST
	public static By totalSectionsList = By.xpath("//div[@id='divDynamicDRPlanSections']/div");
	
	public static By addTextParagraphCommentsSwitch = By.xpath("//div[@id='divAddEditDynamicCtrl_Text']/div[2]/form/div/iframe");
	
	public static By drscEdtrFrame = By.xpath("//div[@class='sceditor-container ltr']/iframe");
	public static By drscEdtrCommentsField = By.cssSelector("body[dir='ltr']");
	
	
	//	SECTION GRID
	public static By drCntlAddTextParagraphOptn = By.xpath("//span[contains(text(),'Add Text/Paragraph')]");
	public static By drCntlAddDataOptn = By.xpath("//span[contains(text(),'Add Data')]");
	
	public static By drCntlSaveChangesBtn = By.xpath("//button[contains(text(),'Save Changes')]");
	
	public static By sectionInnrScrollBar = By.xpath("//div[@class='box span12 mrgleft0 dynamcic-plan-section-editmode']");
	
	public static By moveLastIcon = By.xpath("//i[@class='icon-circle-arrow-down']");
	public static By moveTopIcon = By.xpath("//div[@class='span12 mrgleft0 dynamcic-section-workarea']/div[2]//a[2]/i");
	public static By moveDownIcon = By.xpath("//i[@class='icon-arrow-down']");
	public static By moveUpIcon = By.xpath("//div[@class='span12 mrgleft0 dynamcic-section-workarea']/div[2]//a[3]/i");
	public static By removeIcon = By.xpath("//div[@class='span12 mrgleft0 dynamcic-section-workarea']/div[3]//a/i");	
	
	
	//	ADD DYNAMIC DATA POP UP
	
	public static By dynDtaPpTitleText = By.id("txtTitleText_DData");
	
	public static By addDynamicDataPopup=By.id("add-edit-dynamic-crtl-data-block");
	public static By dynDtaPpModuleDDArrow=By.xpath("//div[@id='dyn_pri_mod_src_chzn']/a/div");
	public static By dynDtaPpModuleDDCnt=By.xpath("//div[@id='dyn_pri_mod_src_chzn']//ul//li");
	public static By dynDtaPpModuleDDSearchBox=By.xpath("//div[@id='dyn_pri_mod_src_chzn']/div/div/input");
	
	public static By dynDtaAddNewFilterBtn = By.id("btnShowAddNewFilter");
	
//	public static By dynDtaFieldDDCnt = By.xpath("//div[@id='ddlDDataNewFilterFields_chzn']//ul/li");
//	public static By dynDtaFieldDDArrow = By.xpath("//div[@id='ddlDDataNewFilterFields_chzn']/a/div");
//	public static By dynDtaFieldDDSearchBox = By.xpath("//div[@id='ddlDDataNewFilterFields_chzn']/div/div/input");
	
	public static By dynDtaFieldDDCnt = By.xpath("//div[@id='dyn_pri_flt_fld_chzn']/div/ul/li");
	public static By dynDtaFieldDDArrow = By.xpath("//div[@id='dyn_pri_flt_fld_chzn']/a/div");
	public static By dynDtaFieldDDSearchBox = By.xpath("//div[@id='dyn_pri_flt_fld_chzn']/div/div/input");
	
	public static By dynDtaFieldValueDD = By.xpath("//div[@id='dyn_sel_data_fld_val_chzn']/ul/li/input");
	public static By dynDtaFieldValueCnt = By.xpath("//div[@id='dyn_sel_data_fld_val_chzn']/div/ul/li");
	public static By dynDtaFilterAddBtn = By.xpath("//div[@id='dyn-sec-tab-of-PrimaryModule']/div[5]/div[2]/div[1]/div[4]/div/a");
	
	
	public static By drAdDynDtaPpFieldValDD = By.xpath("//div[@id='divDDataNewFilter']/div[2]/div/div/ul/li/input");
	
//	public static By dynDtaFieldValueDD = By.xpath("//div[@id='ddlDDataNewFilterValues_chzn']/ul/li/input");
//	public static By dynDtaFieldValueCnt = By.xpath("//div[@id='ddlDDataNewFilterValues_chzn']/div/ul/li");
//	public static By dynDtaFilterAddBtn = By.xpath("//div[@id='divFilterType_Mdl_Only']/div[3]/div/a");
	
	//public static By drAdDynDtaPpFltrAddBtn = By.xpath("//div[@id='divDDataNewFilter']/div[3]/div/a");
	public static By dynDtaPpSaveBtn = By.id("btnAddEditDynamicCtrl_DData");
	//public static By dynDtaPpCloseBtn = By.xpath("//div[@id='divAddEditDynamicCtrl_DData']/div[2]/div/button[2]");
	public static By dynDtaPpCloseBtn=By.xpath("//div[@id='add-edit-dynamic-crtl-data-block']/div/button[2]");
	public static By dynDtaPpCrossCloseBtn = By.xpath("//div[@id='divAddEditDynamicCtrl_DData']/div/button");
	
	public static By drAdDynDtaModuleVal = By.id("ddlModule_DData_chzn_o_");

	
	
	//	EMPLOYEES - TEXT
	public static By edtSecAddTextBtn = By.xpath("//div[@id='divDynamicDRPlanSections']//button[7]");
	
	public static By adTxtAddTextParagraphPopup = By.id("divAddEditDynamicCtrl_Text");
	
	public static By adTxtAddTextParagraphPpTtl = By.id("myModalLabel_Text");
	public static By adTxtPpEnterSomeTxtLbl = By.xpath("//form[@id='frmAddEditTextDynamicCtrl']/label");
	public static By adTxtEnterSomeTextFld = By.xpath("//form[@id='frmAddEditTextDynamicCtrl']//div/iframe"); 
	
	public static By adTxtEditAddedTxt = By.xpath("//div[@class='span12 dynamcic-section-workarea-parent']/div[2]/div/div/p");
	public static By adTxtEditAddedTxt2nd = By.xpath("//div[@class='span12 mrgleft0 dynamcic-section-workarea']/div[3]/div/p");
	
	public static By adTxteditClick=By.xpath("//div[@id='divDynamicDRPlanSections']/div/div[2]/div[2]/div[1]/div/div[2]/div/div[2]");
	public static By adTxtParagrphPpSaveBtn = By.id("btnAddEditDynamicCtrl_Text"); 
	public static By adTxtParagrphPpCloseBtn = By.xpath("//div[@id='divAddEditDynamicCtrl_Text']/div[3]/button[2]");
	public static By adTxtParagrphPpCrossCloseBtn = By.xpath("//div[@id='divAddEditDynamicCtrl_Text']/div/button");
	
	public static By mHovrOnFirstOptn = By.xpath("//div[@class='span12 dynamcic-section-workarea-parent']/div[2]/div/div");
	public static By mHovrOnSecOptn = By.xpath("//div[@class='span12 dynamcic-section-workarea-parent']/div[2]/div[2]/div");
	public static By mHovrOnThirdOptn = By.xpath("//div[@class='span12 dynamcic-section-workarea-parent']/div[2]/div[3]");
	
	public static By txtDeleteConfPopup = By.xpath("//div[@class='bootbox modal fade bootbox-confirm in']/div");
	
	
	//	DYNAMIC DATA - EMPLOYEES - ADD DATA
	public static By edtSecAddDataBtn = By.xpath("//div[@id='divDynamicDRPlanSections']//button[5]");
	
	public static By dynDtaEmpSelectAllChkBox = By.xpath("//input[@class='ddatafldselect-all-chkbox']");
	
	public static By dynDtaEmpDisplayFldCnt = By.xpath("//div[@id='divDDataFields']/span");
	
	public static By dynDtaDisplayFldFifthWe = By.xpath("//div[@id='pri-mod-flds-holder']/span[6]");
	public static By dynDtaDisplayFldSixthWe = By.xpath("//div[@id='pri-mod-flds-holder']/span[7]");
	public static By dynDtaDisplayFldSeventhWe = By.xpath("//div[@id='pri-mod-flds-holder']/span[8]");
	public static By dynDtaDisplayFldEigthWe = By.xpath("//div[@id='pri-mod-flds-holder']/span[9]");
	
	public static By dynDtaEmpWeFirstName = By.xpath("//div[@id='pri-mod-flds-holder']/span[2]");
	public static By dynDtaEmpWeLastName = By.xpath("//div[@id='pri-mod-flds-holder']/span[3]");
	public static By dynDtaEmpWeMobile = By.xpath("//div[@id='pri-mod-flds-holder']/span[4]");
	public static By dynDtaEmpWeAlternateEmployee = By.xpath("//div[@id='pri-mod-flds-holder']/span[5]");
	
	public static By dynDtaEmpFirstName = By.xpath("//div[@id='pri-mod-flds-holder']/span[2]/input");
	public static By dynDtaEmpLastName = By.xpath("//div[@id='pri-mod-flds-holder']/span[3]/input");
	public static By dynDtaEmpMobile = By.xpath("//div[@id='pri-mod-flds-holder']/span[11]/input");
	public static By dynDtaEmpAlternateEmployee = By.xpath("//div[@id='pri-mod-flds-holder']/span[23]/input");
	
	public static By dynDtaEmpFilterValue1 = By.xpath("//div[@id='divDDataFilters']/div");
	public static By dynDtaEmpFilterValLblNm1 = By.xpath("//div[@id='divDDataFilters']/div/span");
	public static By dynDtaEmpFilterVal1 = By.xpath("//div[@id='divDDataFilters']/div/span[2]");
	public static By dynDtaEmpFilterValCloseBtn1 = By.xpath("//div[@id='divDDataFilters']/div/a");
	
	public static By dynDtaEmpFilterValue2 = By.xpath("//div[@id='divDDataFilters']/div[2]");
	public static By dynDtaEmpFilterValLblNm2 = By.xpath("//div[@id='divDDataFilters']/div[2]/span");
	public static By dynDtaEmpFilterVal2 = By.xpath("//div[@id='divDDataFilters']/div[2]/span[2]");
	public static By dynDtaEmpFilterValCloseBtn2 = By.xpath("//div[@id='divDDataFilters']/div[2]/a");
	
	
	public static By dynDtaEmpSelectedFilterValue = By.id("divDDataFilters");
	
	//public static By dynDtaEmpClikToEdit = By.xpath("//div[@class='span12 mrgleft0 dynamcic-section-workarea']/div[2]/div[2]");
	public static By dynDtaEmpClikToEdit=By.xpath("//div[@id='divDynamicDRPlanSections']/div[1]/div[2]/div[2]/div[1]/div/div[2]/div[2]/div[2]");
	
	//	DR PLAN PREVIEW
	public static By drPreviewScrnBody = By.cssSelector("div.dynamcic-plan-output-body");
	
	
	//	Locations Section
	public static By lsecDescription = By.xpath("//div[@id='divDynamicDRPlanSections']/div[2]//div[@class='dynamcic-section-desc']");
	//public static By lsecDescription=By.xpath("");
	
	public static By lsecLblAuthorizedBy = By.xpath("//div[@id='divDynamicDRPlanSections']/div[2]//b[contains(text(),'Authorized By : ')]");
	public static By lsecAuthorizedByNm = By.xpath("//div[@id='divDynamicDRPlanSections']/div[2]//span[@class='dynamcic-section-authorizedby']");
	public static By lsecLblVerifiedBy = By.xpath("//div[@id='divDynamicDRPlanSections']/div[2]/div[2]/div[2]/div[2]/div[2]/span/b");
	public static By lsecVerifiedByNm = By.xpath("//div[@id='divDynamicDRPlanSections']/div[2]//span[@class='dynamcic-section-verifiedby']");
	
	public static By lsecAddTextBtn = By.xpath("//div[@id='divDynamicDRPlanSections']/div[2]//button[contains(text(),'Add Text')]");
	public static By lsecAddImageBtn = By.xpath("//div[@id='divDynamicDRPlanSections']/div[2]//button[contains(text(),'Add Image')]");
	public static By lsecAddDataBtn = By.xpath("//div[@id='divDynamicDRPlanSections']/div[2]//button[contains(text(),'Add Data')]");
	public static By lsecEditSectionBtn = By.xpath("//div[@id='divDynamicDRPlanSections']/div[2]//button[contains(text(),'Edit Section')]");
	public static By lsecDeleteSectionBtn = By.xpath("//div[@id='divDynamicDRPlanSections']/div[2]//button[contains(text(),'Delete Section')]");
	
	public static By lsecfirstText = By.xpath("//div[@id='divDynamicDRPlanSections']/div[2]//div[@class='dynamic-control-preview']/div[2]");
	public static By lsecLocationsList = By.xpath("//div[@id='divDynamicDRPlanSections']/div[2]//div[@class='span12 mrgleft0 dynamcic-section-workarea']/div[2]/div[2]");
	
	public static By lsecdfLocationName = By.xpath("//div[@id='pri-mod-flds-holder']/span[2]/input");
	public static By lsecdfManager = By.xpath("//div[@id='pri-mod-flds-holder']/span[5]/input");
	public static By lsecdfState = By.xpath("//div[@id='pri-mod-flds-holder']/span[12]/input");
	public static By lsecdfZipcode = By.xpath("//div[@id='pri-mod-flds-holder']/span[14]/input");
	
	
	public static By lsec2ndTxt1 = By.xpath("//div[@id='divDynamicDRPlanSections']/div[2]//div[@class='span12 mrgleft0 dynamcic-section-workarea']/div[2]/div[2]");
	public static By lsec2ndTxt = By.xpath("//div[@id='divDynamicDRPlanSections']/div[2]//div[@class='span12 mrgleft0 dynamcic-section-workarea']/div[3]/div[2]");
	
	public static By lsecmHovrOnFirstOptn = By.xpath("//div[@id='divDynamicDRPlanSections']/div[2]//div[@class='span12 mrgleft0 dynamcic-section-workarea']/div/div[2]");
	public static By lsecmHovrOnSecOptn = By.xpath("//div[@id='divDynamicDRPlanSections']/div[2]//div[@class='span12 mrgleft0 dynamcic-section-workarea']/div[2]/div[2]");
	public static By lsecmHovrOnThirdOptn = By.xpath("//div[@id='divDynamicDRPlanSections']/div[2]//div[@class='span12 mrgleft0 dynamcic-section-workarea']/div[3]/div[2]");
	
	public static By lsecmoveLastIcon = By.xpath("//div[@id='divDynamicDRPlanSections']/div[2]//i[@class='icon-circle-arrow-down']");
	public static By lsecmoveTopIcon = By.xpath("//div[@id='divDynamicDRPlanSections']/div[2]//div[@class='span12 mrgleft0 dynamcic-section-workarea']/div[2]//a[2]/i");
	public static By lsecmoveDownIcon = By.xpath("//div[@id='divDynamicDRPlanSections']/div[2]//i[@class='icon-arrow-down']");
	public static By lsecmoveUpIcon = By.xpath("//div[@id='divDynamicDRPlanSections']/div[2]//div[@class='span12 mrgleft0 dynamcic-section-workarea']/div[2]//a[3]/i");
	public static By lsecremoveIcon = By.xpath("//div[@id='divDynamicDRPlanSections']/div[2]//div[@class='span12 mrgleft0 dynamcic-section-workarea']/div[3]//a/i");
	
	
	//		Threats Section
	public static By trsecDescription = By.xpath("//div[@id='divDynamicDRPlanSections']/div[3]//div[@class='dynamcic-section-desc']");
	
	public static By trsecAddTextBtn = By.xpath("//div[@id='divDynamicDRPlanSections']/div[3]//button[contains(text(),'Add Text')]");
	public static By trsecAddImageBtn = By.xpath("//div[@id='divDynamicDRPlanSections']/div[3]//button[contains(text(),'Add Image')]");
	public static By trsecAddDataBtn = By.xpath("//div[@id='divDynamicDRPlanSections']/div[3]//button[contains(text(),'Add Data')]");
	public static By trsecEditSectionBtn = By.xpath("//div[@id='divDynamicDRPlanSections']/div[3]//button[contains(text(),'Edit Section')]");
	public static By trsecDeleteSectionBtn = By.xpath("//div[@id='divDynamicDRPlanSections']/div[3]//button[contains(text(),'Delete Section')]");
	
	public static By trsecfirstText = By.xpath("//div[@id='divDynamicDRPlanSections']/div[3]//div[@class='dynamic-control-preview']/div[2]");
	public static By trsecThreatsList = By.xpath("//div[@id='divDynamicDRPlanSections']/div[3]//div[@class='span12 mrgleft0 dynamcic-section-workarea']/div[2]/div[2]");
	
	public static By trsecAuthorizedByNm = By.xpath("//div[@id='divDynamicDRPlanSections']/div[3]//span[@class='dynamcic-section-authorizedby']");
	public static By trsecVerifiedByNm = By.xpath("//div[@id='divDynamicDRPlanSections']/div[3]//span[@class='dynamcic-section-verifiedby']");
	
	public static By trsecdfThreatName = By.xpath("//div[@id='pri-mod-flds-holder']/span[2]/input");
	public static By trsecdfComments = By.xpath("//div[@id='pri-mod-flds-holder']/span[4]/input");
		
	public static By trsec2ndTxt1 = By.xpath("//div[@id='divDynamicDRPlanSections']/div[3]//div[@class='span12 mrgleft0 dynamcic-section-workarea']/div[2]/div[2]");
	public static By trsec2ndTxt = By.xpath("//div[@id='divDynamicDRPlanSections']/div[3]//div[@class='span12 mrgleft0 dynamcic-section-workarea']/div[3]/div[2]");
	
	public static By trsecmHovrOnFirstOptn = By.xpath("//div[@id='divDynamicDRPlanSections']/div[3]//div[@class='span12 mrgleft0 dynamcic-section-workarea']/div/div[2]");
	public static By trsecmHovrOnSecOptn = By.xpath("//div[@id='divDynamicDRPlanSections']/div[3]//div[@class='span12 mrgleft0 dynamcic-section-workarea']/div[2]/div[2]");
	public static By trsecmHovrOnThirdOptn = By.xpath("//div[@id='divDynamicDRPlanSections']/div[3]//div[@class='span12 mrgleft0 dynamcic-section-workarea']/div[3]/div[2]");
	
	public static By trsecmoveLastIcon = By.xpath("//div[@id='divDynamicDRPlanSections']/div[3]//i[@class='icon-circle-arrow-down']");
	public static By trsecmoveTopIcon = By.xpath("//div[@id='divDynamicDRPlanSections']/div[3]//div[@class='span12 mrgleft0 dynamcic-section-workarea']/div[2]//a[2]/i");
	public static By trsecmoveDownIcon = By.xpath("//div[@id='divDynamicDRPlanSections']/div[3]//i[@class='icon-arrow-down']");
	public static By trsecmoveUpIcon = By.xpath("//div[@id='divDynamicDRPlanSections']/div[3]//div[@class='span12 mrgleft0 dynamcic-section-workarea']/div[2]//a[3]/i");
	public static By trsecremoveIcon = By.xpath("//div[@id='divDynamicDRPlanSections']/div[3]//div[@class='span12 mrgleft0 dynamcic-section-workarea']/div[3]//a/i");
	
	
	//		Teams Section
	public static By tmsecDescription = By.xpath("//div[@id='divDynamicDRPlanSections']/div[4]//div[@class='dynamcic-section-desc']");
	
	public static By tmsecAddTextBtn = By.xpath("//div[@id='divDynamicDRPlanSections']/div[4]//button[contains(text(),'Add Text')]");
	public static By tmsecAddImageBtn = By.xpath("//div[@id='divDynamicDRPlanSections']/div[4]//button[contains(text(),'Add Image')]");
	public static By tmsecAddDataBtn = By.xpath("//div[@id='divDynamicDRPlanSections']/div[4]//button[contains(text(),'Add Data')]");
	public static By tmsecEditSectionBtn = By.xpath("//div[@id='divDynamicDRPlanSections']/div[4]//button[contains(text(),'Edit Section')]");
	public static By tmsecDeleteSectionBtn = By.xpath("//div[@id='divDynamicDRPlanSections']/div[4]//button[contains(text(),'Delete Section')]");
	
	public static By tmsecAuthorizedByNm = By.xpath("//div[@id='divDynamicDRPlanSections']/div[4]//span[@class='dynamcic-section-authorizedby']");
	public static By tmsecVerifiedByNm = By.xpath("//div[@id='divDynamicDRPlanSections']/div[4]//span[@class='dynamcic-section-verifiedby']");
	
	public static By tmsecfirstText = By.xpath("//div[@id='divDynamicDRPlanSections']/div[4]//div[@class='dynamic-control-preview']/div[2]");
	public static By tmsecTeamsList = By.xpath("//div[@id='divDynamicDRPlanSections']/div[4]//div[@class='span12 mrgleft0 dynamcic-section-workarea']/div[2]/div[2]");
	
	public static By tmsec2ndTxt1 = By.xpath("//div[@id='divDynamicDRPlanSections']/div[4]//div[@class='span12 mrgleft0 dynamcic-section-workarea']/div[2]/div[2]");
	public static By tmsec2ndTxt = By.xpath("//div[@id='divDynamicDRPlanSections']/div[4]//div[@class='span12 mrgleft0 dynamcic-section-workarea']/div[3]/div[2]");
	
	public static By tmsecdfTeamName = By.xpath("//div[@id='pri-mod-flds-holder']/span[2]/input");
	public static By tmsecdfTeamLeader = By.xpath("//div[@id='pri-mod-flds-holder']/span[3]/input");
	public static By tmsecdfTeamType = By.xpath("//div[@id='pri-mod-flds-holder']/span[5]/input");
	public static By tmsecdfLocation = By.xpath("//div[@id='pri-mod-flds-holder']/span[6]/input");
	
	public static By tmsecmHovrOnFirstOptn = By.xpath("//div[@id='divDynamicDRPlanSections']/div[4]//div[@class='span12 mrgleft0 dynamcic-section-workarea']/div/div[2]");
	public static By tmsecmHovrOnSecOptn = By.xpath("//div[@id='divDynamicDRPlanSections']/div[4]//div[@class='span12 mrgleft0 dynamcic-section-workarea']/div[2]/div[2]");
	public static By tmsecmHovrOnThirdOptn = By.xpath("//div[@id='divDynamicDRPlanSections']/div[4]//div[@class='span12 mrgleft0 dynamcic-section-workarea']/div[3]/div[2]");
	
	public static By tmsecmoveLastIcon = By.xpath("//div[@id='divDynamicDRPlanSections']/div[4]//i[@class='icon-circle-arrow-down']");
	public static By tmsecmoveTopIcon = By.xpath("//div[@id='divDynamicDRPlanSections']/div[4]//div[@class='span12 mrgleft0 dynamcic-section-workarea']/div[2]//a[2]/i");
	public static By tmsecmoveDownIcon = By.xpath("//div[@id='divDynamicDRPlanSections']/div[4]//i[@class='icon-arrow-down']");
	public static By tmsecmoveUpIcon = By.xpath("//div[@id='divDynamicDRPlanSections']/div[4]//div[@class='span12 mrgleft0 dynamcic-section-workarea']/div[2]//a[3]/i");
	public static By tmsecremoveIcon = By.xpath("//div[@id='divDynamicDRPlanSections']/div[4]//div[@class='span12 mrgleft0 dynamcic-section-workarea']/div[3]//a/i");
	
	
	//		Assets Section
	public static By asecDescription = By.xpath("//div[@id='divDynamicDRPlanSections']/div[5]//div[@class='dynamcic-section-desc']");

	public static By asecAddTextBtn = By.xpath("//div[@id='divDynamicDRPlanSections']/div[5]//button[contains(text(),'Add Text')]");
	public static By asecAddImageBtn = By.xpath("//div[@id='divDynamicDRPlanSections']/div[5]//button[contains(text(),'Add Image')]");
	public static By asecAddDataBtn = By.xpath("//div[@id='divDynamicDRPlanSections']/div[5]//button[contains(text(),'Add Data')]");
	public static By asecEditSectionBtn = By.xpath("//div[@id='divDynamicDRPlanSections']/div[5]//button[contains(text(),'Edit Section')]");
	public static By asecDeleteSectionBtn = By.xpath("//div[@id='divDynamicDRPlanSections']/div[5]//button[contains(text(),'Delete Section')]");

	public static By asecAuthorizedByNm = By.xpath("//div[@id='divDynamicDRPlanSections']/div[5]//span[@class='dynamcic-section-authorizedby']");
	public static By asecVerifiedByNm = By.xpath("//div[@id='divDynamicDRPlanSections']/div[5]//span[@class='dynamcic-section-verifiedby']");

	public static By asecfirstText = By.xpath("//div[@id='divDynamicDRPlanSections']/div[5]//div[@class='dynamic-control-preview']/div[2]");
	public static By asecTeamsList = By.xpath("//div[@id='divDynamicDRPlanSections']/div[5]//div[@class='span12 mrgleft0 dynamcic-section-workarea']/div[2]/div[2]");

	public static By asec2ndTxt1 = By.xpath("//div[@id='divDynamicDRPlanSections']/div[5]//div[@class='span12 mrgleft0 dynamcic-section-workarea']/div[2]/div[2]");
	public static By asec2ndTxt = By.xpath("//div[@id='divDynamicDRPlanSections']/div[5]//div[@class='span12 mrgleft0 dynamcic-section-workarea']/div[3]/div[2]");
	
	public static By asecdfAssetName = By.xpath("//div[@id='pri-mod-flds-holder']/span[2]/input");
	public static By asecdfManufacturerName = By.xpath("//div[@id='pri-mod-flds-holder']/span[7]/input");
	public static By asecdfQuantity = By.xpath("//div[@id='pri-mod-flds-holder']/span[9]/input");
	public static By asecdfReplacementValue = By.xpath("//div[@id='pri-mod-flds-holder']/span[10]/input");

	public static By asecmHovrOnFirstOptn = By.xpath("//div[@id='divDynamicDRPlanSections']/div[5]//div[@class='span12 mrgleft0 dynamcic-section-workarea']/div/div[2]");
	public static By asecmHovrOnSecOptn = By.xpath("//div[@id='divDynamicDRPlanSections']/div[5]//div[@class='span12 mrgleft0 dynamcic-section-workarea']/div[2]/div[2]");
	public static By asecmHovrOnThirdOptn = By.xpath("//div[@id='divDynamicDRPlanSections']/div[5]//div[@class='span12 mrgleft0 dynamcic-section-workarea']/div[3]/div[2]");

	public static By asecmoveLastIcon = By.xpath("//div[@id='divDynamicDRPlanSections']/div[5]//i[@class='icon-circle-arrow-down']");
	public static By asecmoveTopIcon = By.xpath("//div[@id='divDynamicDRPlanSections']/div[5]//div[@class='span12 mrgleft0 dynamcic-section-workarea']/div[2]//a[2]/i");
	public static By asecmoveDownIcon = By.xpath("//div[@id='divDynamicDRPlanSections']/div[5]//i[@class='icon-arrow-down']");
	public static By asecmoveUpIcon = By.xpath("//div[@id='divDynamicDRPlanSections']/div[5]//div[@class='span12 mrgleft0 dynamcic-section-workarea']/div[2]//a[3]/i");
	public static By asecremoveIcon = By.xpath("//div[@id='divDynamicDRPlanSections']/div[5]//div[@class='span12 mrgleft0 dynamcic-section-workarea']/div[3]//a/i");

	
	//		Asset Groups Section
	public static By agsecDescription = By.xpath("//div[@id='divDynamicDRPlanSections']/div[6]//div[@class='dynamcic-section-desc']");

	public static By agsecAddTextBtn = By.xpath("//div[@id='divDynamicDRPlanSections']/div[6]//button[contains(text(),'Add Text')]");
	public static By agsecAddImageBtn = By.xpath("//div[@id='divDynamicDRPlanSections']/div[6]//button[contains(text(),'Add Image')]");
	public static By agsecAddDataBtn = By.xpath("//div[@id='divDynamicDRPlanSections']/div[6]//button[contains(text(),'Add Data')]");
	public static By agsecEditSectionBtn = By.xpath("//div[@id='divDynamicDRPlanSections']/div[6]//button[contains(text(),'Edit Section')]");
	public static By agsecDeleteSectionBtn = By.xpath("//div[@id='divDynamicDRPlanSections']/div[6]//button[contains(text(),'Delete Section')]");

	public static By agsecAuthorizedByNm = By.xpath("//div[@id='divDynamicDRPlanSections']/div[6]//span[@class='dynamcic-section-authorizedby']");
	public static By agsecVerifiedByNm = By.xpath("//div[@id='divDynamicDRPlanSections']/div[6]//span[@class='dynamcic-section-verifiedby']");

	public static By agsecfirstText = By.xpath("//div[@id='divDynamicDRPlanSections']/div[6]//div[@class='dynamic-control-preview']/div[2]");
	public static By agsecTeamsList = By.xpath("//div[@id='divDynamicDRPlanSections']/div[6]//div[@class='span12 mrgleft0 dynamcic-section-workarea']/div[2]/div[2]");

	public static By agsec2ndTxt1 = By.xpath("//div[@id='divDynamicDRPlanSections']/div[6]//div[@class='span12 mrgleft0 dynamcic-section-workarea']/div[2]/div[2]");
	public static By agsec2ndTxt = By.xpath("//div[@id='divDynamicDRPlanSections']/div[6]//div[@class='span12 mrgleft0 dynamcic-section-workarea']/div[3]/div[2]");

	public static By agsecdfGroupName = By.xpath("//div[@id='pri-mod-flds-holder']/span[2]/input");
	public static By agsecdfAssetGroupId = By.xpath("//div[@id='pri-mod-flds-holder']/span[3]/input");
	public static By agsecdfComments = By.xpath("//div[@id='pri-mod-flds-holder']/span[4]/input");
	
	public static By agsecmHovrOnFirstOptn = By.xpath("//div[@id='divDynamicDRPlanSections']/div[6]//div[@class='span12 mrgleft0 dynamcic-section-workarea']/div/div[2]");
	public static By agsecmHovrOnSecOptn = By.xpath("//div[@id='divDynamicDRPlanSections']/div[6]//div[@class='span12 mrgleft0 dynamcic-section-workarea']/div[2]/div[2]");
	public static By agsecmHovrOnThirdOptn = By.xpath("//div[@id='divDynamicDRPlanSections']/div[6]//div[@class='span12 mrgleft0 dynamcic-section-workarea']/div[3]/div[2]");

	public static By agsecmoveLastIcon = By.xpath("//div[@id='divDynamicDRPlanSections']/div[6]//i[@class='icon-circle-arrow-down']");
	public static By agsecmoveTopIcon = By.xpath("//div[@id='divDynamicDRPlanSections']/div[6]//div[@class='span12 mrgleft0 dynamcic-section-workarea']/div[2]//a[2]/i");
	public static By agsecmoveDownIcon = By.xpath("//div[@id='divDynamicDRPlanSections']/div[6]//i[@class='icon-arrow-down']");
	public static By agsecmoveUpIcon = By.xpath("//div[@id='divDynamicDRPlanSections']/div[6]//div[@class='span12 mrgleft0 dynamcic-section-workarea']/div[2]//a[3]/i");
	public static By agsecremoveIcon = By.xpath("//div[@id='divDynamicDRPlanSections']/div[6]//div[@class='span12 mrgleft0 dynamcic-section-workarea']/div[3]//a/i");
	
	
	//		Contacts Section
	public static By csecDescription = By.xpath("//div[@id='divDynamicDRPlanSections']/div[7]//div[@class='dynamcic-section-desc']");

	public static By csecAddTextBtn = By.xpath("//div[@id='divDynamicDRPlanSections']/div[7]//button[contains(text(),'Add Text')]");
	public static By csecAddImageBtn = By.xpath("//div[@id='divDynamicDRPlanSections']/div[7]//button[contains(text(),'Add Image')]");
	public static By csecAddDataBtn = By.xpath("//div[@id='divDynamicDRPlanSections']/div[7]//button[contains(text(),'Add Data')]");
	public static By csecEditSectionBtn = By.xpath("//div[@id='divDynamicDRPlanSections']/div[7]//button[contains(text(),'Edit Section')]");
	public static By csecDeleteSectionBtn = By.xpath("//div[@id='divDynamicDRPlanSections']/div[7]//button[contains(text(),'Delete Section')]");

	public static By csecAuthorizedByNm = By.xpath("//div[@id='divDynamicDRPlanSections']/div[7]//span[@class='dynamcic-section-authorizedby']");
	public static By csecVerifiedByNm = By.xpath("//div[@id='divDynamicDRPlanSections']/div[7]//span[@class='dynamcic-section-verifiedby']");

	public static By csecfirstText = By.xpath("//div[@id='divDynamicDRPlanSections']/div[7]//div[@class='dynamic-control-preview']/div[2]");
	public static By csecTeamsList = By.xpath("//div[@id='divDynamicDRPlanSections']/div[7]//div[@class='span12 mrgleft0 dynamcic-section-workarea']/div[2]/div[2]");

	public static By csec2ndTxt1 = By.xpath("//div[@id='divDynamicDRPlanSections']/div[7]//div[@class='span12 mrgleft0 dynamcic-section-workarea']/div[2]/div[2]");
	public static By csec2ndTxt = By.xpath("//div[@id='divDynamicDRPlanSections']/div[7]//div[@class='span12 mrgleft0 dynamcic-section-workarea']/div[3]/div[2]");

	public static By csecdfCompanyName = By.xpath("//div[@id='pri-mod-flds-holder']/span[2]/input");
	public static By csecdfCountry = By.xpath("//div[@id='pri-mod-flds-holder']/span[7]/input");
	public static By csecdfCity = By.xpath("//div[@id='pri-mod-flds-holder']/span[9]/input");
	public static By csecdfZipcode = By.xpath("//div[@id='pri-mod-flds-holder']/span[10]/input");
	public static By csecdfpFirstName = By.xpath("//div[@id='pri-mod-flds-holder']/span[15]/input");
	public static By csecdfpLastName = By.xpath("//div[@id='pri-mod-flds-holder']/span[16]/input");
	public static By csecdfpBusinessEmail = By.xpath("//div[@id='pri-mod-flds-holder']/span[18]/input");
	public static By csecdfpMobileNo = By.xpath("//div[@id='pri-mod-flds-holder']/span[20]/input");

	public static By csecmHovrOnFirstOptn = By.xpath("//div[@id='divDynamicDRPlanSections']/div[7]//div[@class='span12 mrgleft0 dynamcic-section-workarea']/div/div[2]");
	public static By csecmHovrOnSecOptn = By.xpath("//div[@id='divDynamicDRPlanSections']/div[7]//div[@class='span12 mrgleft0 dynamcic-section-workarea']/div[2]/div[2]");
	public static By csecmHovrOnThirdOptn = By.xpath("//div[@id='divDynamicDRPlanSections']/div[7]//div[@class='span12 mrgleft0 dynamcic-section-workarea']/div[3]/div[2]");

	public static By csecmoveLastIcon = By.xpath("//div[@id='divDynamicDRPlanSections']/div[7]//i[@class='icon-circle-arrow-down']");
	public static By csecmoveTopIcon = By.xpath("//div[@id='divDynamicDRPlanSections']/div[7]//div[@class='span12 mrgleft0 dynamcic-section-workarea']/div[2]//a[2]/i");
	public static By csecmoveDownIcon = By.xpath("//div[@id='divDynamicDRPlanSections']/div[7]//i[@class='icon-arrow-down']");
	public static By csecmoveUpIcon = By.xpath("//div[@id='divDynamicDRPlanSections']/div[7]//div[@class='span12 mrgleft0 dynamcic-section-workarea']/div[2]//a[3]/i");
	public static By csecremoveIcon = By.xpath("//div[@id='divDynamicDRPlanSections']/div[7]//div[@class='span12 mrgleft0 dynamcic-section-workarea']/div[3]//a/i");

	
	//		Contact Groups Section
	public static By cgsecDescription = By.xpath("//div[@id='divDynamicDRPlanSections']/div[8]//div[@class='dynamcic-section-desc']");

	public static By cgsecAddTextBtn = By.xpath("//div[@id='divDynamicDRPlanSections']/div[8]//button[contains(text(),'Add Text')]");
	public static By cgsecAddImageBtn = By.xpath("//div[@id='divDynamicDRPlanSections']/div[8]//button[contains(text(),'Add Image')]");
	public static By cgsecAddDataBtn = By.xpath("//div[@id='divDynamicDRPlanSections']/div[8]//button[contains(text(),'Add Data')]");
	public static By cgsecEditSectionBtn = By.xpath("//div[@id='divDynamicDRPlanSections']/div[8]//button[contains(text(),'Edit Section')]");
	public static By cgsecDeleteSectionBtn = By.xpath("//div[@id='divDynamicDRPlanSections']/div[8]//button[contains(text(),'Delete Section')]");

	public static By cgsecAuthorizedByNm = By.xpath("//div[@id='divDynamicDRPlanSections']/div[8]//span[@class='dynamcic-section-authorizedby']");
	public static By cgsecVerifiedByNm = By.xpath("//div[@id='divDynamicDRPlanSections']/div[8]//span[@class='dynamcic-section-verifiedby']");

	public static By cgsecfirstText = By.xpath("//div[@id='divDynamicDRPlanSections']/div[8]//div[@class='dynamic-control-preview']/div[2]");
	public static By cgsecTeamsList = By.xpath("//div[@id='divDynamicDRPlanSections']/div[8]//div[@class='span12 mrgleft0 dynamcic-section-workarea']/div[2]/div[2]");

	public static By cgsec2ndTxt1 = By.xpath("//div[@id='divDynamicDRPlanSections']/div[8]//div[@class='span12 mrgleft0 dynamcic-section-workarea']/div[2]/div[2]");
	public static By cgsec2ndTxt = By.xpath("//div[@id='divDynamicDRPlanSections']/div[8]//div[@class='span12 mrgleft0 dynamcic-section-workarea']/div[3]/div[2]");

	public static By cgsecdfContactGroupName = By.xpath("//div[@id='pri-mod-flds-holder']/span[2]/input");
	public static By cgsecdfGroupPurpose = By.xpath("//div[@id='pri-mod-flds-holder']/span[3]/input");
	public static By cgsecdfcomments = By.xpath("//div[@id='pri-mod-flds-holder']/span[4]/input");
	
	public static By cgsecmHovrOnFirstOptn = By.xpath("//div[@id='divDynamicDRPlanSections']/div[8]//div[@class='span12 mrgleft0 dynamcic-section-workarea']/div/div[2]");
	public static By cgsecmHovrOnSecOptn = By.xpath("//div[@id='divDynamicDRPlanSections']/div[8]//div[@class='span12 mrgleft0 dynamcic-section-workarea']/div[2]/div[2]");
	public static By cgsecmHovrOnThirdOptn = By.xpath("//div[@id='divDynamicDRPlanSections']/div[8]//div[@class='span12 mrgleft0 dynamcic-section-workarea']/div[3]/div[2]");

	public static By cgsecmoveLastIcon = By.xpath("//div[@id='divDynamicDRPlanSections']/div[8]//i[@class='icon-circle-arrow-down']");
	public static By cgsecmoveTopIcon = By.xpath("//div[@id='divDynamicDRPlanSections']/div[8]//div[@class='span12 mrgleft0 dynamcic-section-workarea']/div[2]//a[2]/i");
	public static By cgsecmoveDownIcon = By.xpath("//div[@id='divDynamicDRPlanSections']/div[8]//i[@class='icon-arrow-down']");
	public static By cgsecmoveUpIcon = By.xpath("//div[@id='divDynamicDRPlanSections']/div[8]//div[@class='span12 mrgleft0 dynamcic-section-workarea']/div[2]//a[3]/i");
	public static By cgsecremoveIcon = By.xpath("//div[@id='divDynamicDRPlanSections']/div[8]//div[@class='span12 mrgleft0 dynamcic-section-workarea']/div[3]//a/i");
	
	
	//		Insurance Section
	public static By isecDescription = By.xpath("//div[@id='divDynamicDRPlanSections']/div[9]//div[@class='dynamcic-section-desc']");

	public static By isecAddTextBtn = By.xpath("//div[@id='divDynamicDRPlanSections']/div[9]//button[contains(text(),'Add Text')]");
	public static By isecAddImageBtn = By.xpath("//div[@id='divDynamicDRPlanSections']/div[9]//button[contains(text(),'Add Image')]");
	public static By isecAddDataBtn = By.xpath("//div[@id='divDynamicDRPlanSections']/div[9]//button[contains(text(),'Add Data')]");
	public static By isecEditSectionBtn = By.xpath("//div[@id='divDynamicDRPlanSections']/div[9]//button[contains(text(),'Edit Section')]");
	public static By isecDeleteSectionBtn = By.xpath("//div[@id='divDynamicDRPlanSections']/div[9]//button[contains(text(),'Delete Section')]");

	public static By isecAuthorizedByNm = By.xpath("//div[@id='divDynamicDRPlanSections']/div[9]//span[@class='dynamcic-section-authorizedby']");
	public static By isecVerifiedByNm = By.xpath("//div[@id='divDynamicDRPlanSections']/div[9]//span[@class='dynamcic-section-verifiedby']");

	public static By isecfirstText = By.xpath("//div[@id='divDynamicDRPlanSections']/div[9]//div[@class='dynamic-control-preview']/div[2]");
	public static By isecTeamsList = By.xpath("//div[@id='divDynamicDRPlanSections']/div[9]//div[@class='span12 mrgleft0 dynamcic-section-workarea']/div[2]/div[2]");

	public static By isec2ndTxt1 = By.xpath("//div[@id='divDynamicDRPlanSections']/div[9]//div[@class='span12 mrgleft0 dynamcic-section-workarea']/div[2]/div[2]");
	public static By isec2ndTxt = By.xpath("//div[@id='divDynamicDRPlanSections']/div[9]//div[@class='span12 mrgleft0 dynamcic-section-workarea']/div[3]/div[2]");

	public static By isecdfPolicyName = By.xpath("//div[@id='pri-mod-flds-holder']/span[2]/input");
	public static By isecdfInsurer = By.xpath("//div[@id='pri-mod-flds-holder']/span[5]/input");
	public static By isecdfExpiryDate = By.xpath("//div[@id='pri-mod-flds-holder']/span[7]/input");
	public static By isecdfInsurerContact = By.xpath("//div[@id='pri-mod-flds-holder']/span[8]/input");

	public static By isecmHovrOnFirstOptn = By.xpath("//div[@id='divDynamicDRPlanSections']/div[9]//div[@class='span12 mrgleft0 dynamcic-section-workarea']/div/div[2]");
	public static By isecmHovrOnSecOptn = By.xpath("//div[@id='divDynamicDRPlanSections']/div[9]//div[@class='span12 mrgleft0 dynamcic-section-workarea']/div[2]/div[2]");
	public static By isecmHovrOnThirdOptn = By.xpath("//div[@id='divDynamicDRPlanSections']/div[9]//div[@class='span12 mrgleft0 dynamcic-section-workarea']/div[3]/div[2]");

	public static By isecmoveLastIcon = By.xpath("//div[@id='divDynamicDRPlanSections']/div[9]//i[@class='icon-circle-arrow-down']");
	public static By isecmoveTopIcon = By.xpath("//div[@id='divDynamicDRPlanSections']/div[9]//div[@class='span12 mrgleft0 dynamcic-section-workarea']/div[2]//a[2]/i");
	public static By isecmoveDownIcon = By.xpath("//div[@id='divDynamicDRPlanSections']/div[9]//i[@class='icon-arrow-down']");
	public static By isecmoveUpIcon = By.xpath("//div[@id='divDynamicDRPlanSections']/div[9]//div[@class='span12 mrgleft0 dynamcic-section-workarea']/div[2]//a[3]/i");
	public static By isecremoveIcon = By.xpath("//div[@id='divDynamicDRPlanSections']/div[9]//div[@class='span12 mrgleft0 dynamcic-section-workarea']/div[3]//a/i");


	//		Tasks Section
	public static By tsecDescription = By.xpath("//div[@id='divDynamicDRPlanSections']/div[10]//div[@class='dynamcic-section-desc']");

	public static By tsecAddTextBtn = By.xpath("//div[@id='divDynamicDRPlanSections']/div[10]//button[contains(text(),'Add Text')]");
	public static By tsecAddImageBtn = By.xpath("//div[@id='divDynamicDRPlanSections']/div[10]//button[contains(text(),'Add Image')]");
	public static By tsecAddDataBtn = By.xpath("//div[@id='divDynamicDRPlanSections']/div[10]//button[contains(text(),'Add Data')]");
	public static By tsecEditSectionBtn = By.xpath("//div[@id='divDynamicDRPlanSections']/div[10]//button[contains(text(),'Edit Section')]");
	public static By tsecDeleteSectionBtn = By.xpath("//div[@id='divDynamicDRPlanSections']/div[10]//button[contains(text(),'Delete Section')]");

	public static By tsecAuthorizedByNm = By.xpath("//div[@id='divDynamicDRPlanSections']/div[10]//span[@class='dynamcic-section-authorizedby']");
	public static By tsecVerifiedByNm = By.xpath("//div[@id='divDynamicDRPlanSections']/div[10]//span[@class='dynamcic-section-verifiedby']");

	public static By tsecfirstText = By.xpath("//div[@id='divDynamicDRPlanSections']/div[10]//div[@class='dynamic-control-preview']/div[2]");
	public static By tsecTeamsList = By.xpath("//div[@id='divDynamicDRPlanSections']/div[10]//div[@class='span12 mrgleft0 dynamcic-section-workarea']/div[2]/div[2]");
	
	public static By tsec2ndTxt1 = By.xpath("//div[@id='divDynamicDRPlanSections']/div[10]//div[@class='span12 mrgleft0 dynamcic-section-workarea']/div[2]/div[2]");
	public static By tsec2ndTxt = By.xpath("//div[@id='divDynamicDRPlanSections']/div[10]//div[@class='span12 mrgleft0 dynamcic-section-workarea']/div[3]/div[2]");

	public static By tsecdfTaskName = By.xpath("//div[@id='pri-mod-flds-holder']/span[2]/input");
	public static By tsecdfTaskType = By.xpath("//div[@id='pri-mod-flds-holder']/span[3]/input");
	public static By tsecdfTaskID = By.xpath("//div[@id='pri-mod-flds-holder']/span[5]/input");
	public static By tsecdfEmployeeAssigned = By.xpath("//div[@id='pri-mod-flds-holder']/span[6]/input");

	public static By tsecmHovrOnFirstOptn = By.xpath("//div[@id='divDynamicDRPlanSections']/div[10]//div[@class='span12 mrgleft0 dynamcic-section-workarea']/div/div[2]");
	public static By tsecmHovrOnSecOptn = By.xpath("//div[@id='divDynamicDRPlanSections']/div[10]//div[@class='span12 mrgleft0 dynamcic-section-workarea']/div[2]/div[2]");
	public static By tsecmHovrOnThirdOptn = By.xpath("//div[@id='divDynamicDRPlanSections']/div[10]//div[@class='span12 mrgleft0 dynamcic-section-workarea']/div[3]/div[2]");

	public static By tsecmoveLastIcon = By.xpath("//div[@id='divDynamicDRPlanSections']/div[10]//i[@class='icon-circle-arrow-down']");
	public static By tsecmoveTopIcon = By.xpath("//div[@id='divDynamicDRPlanSections']/div[10]//div[@class='span12 mrgleft0 dynamcic-section-workarea']/div[2]//a[2]/i");
	public static By tsecmoveDownIcon = By.xpath("//div[@id='divDynamicDRPlanSections']/div[10]//i[@class='icon-arrow-down']");
	public static By tsecmoveUpIcon = By.xpath("//div[@id='divDynamicDRPlanSections']/div[10]//div[@class='span12 mrgleft0 dynamcic-section-workarea']/div[2]//a[3]/i");
	public static By tsecremoveIcon = By.xpath("//div[@id='divDynamicDRPlanSections']/div[10]//div[@class='span12 mrgleft0 dynamcic-section-workarea']/div[3]//a/i");

	
	//		Task Groups Section
	public static By tgsecDescription = By.xpath("//div[@id='divDynamicDRPlanSections']/div[11]//div[@class='dynamcic-section-desc']");

	public static By tgsecAddTextBtn = By.xpath("//div[@id='divDynamicDRPlanSections']/div[11]//button[contains(text(),'Add Text')]");
	public static By tgsecAddImageBtn = By.xpath("//div[@id='divDynamicDRPlanSections']/div[11]//button[contains(text(),'Add Image')]");
	public static By tgsecAddDataBtn = By.xpath("//div[@id='divDynamicDRPlanSections']/div[11]//button[contains(text(),'Add Data')]");
	public static By tgsecEditSectionBtn = By.xpath("//div[@id='divDynamicDRPlanSections']/div[11]//button[contains(text(),'Edit Section')]");
	public static By tgsecDeleteSectionBtn = By.xpath("//div[@id='divDynamicDRPlanSections']/div[11]//button[contains(text(),'Delete Section')]");

	public static By tgsecAuthorizedByNm = By.xpath("//div[@id='divDynamicDRPlanSections']/div[11]//span[@class='dynamcic-section-authorizedby']");
	public static By tgsecVerifiedByNm = By.xpath("//div[@id='divDynamicDRPlanSections']/div[11]//span[@class='dynamcic-section-verifiedby']");

	public static By tgsecfirstText = By.xpath("//div[@id='divDynamicDRPlanSections']/div[11]//div[@class='dynamic-control-preview']/div[2]");
	public static By tgsecTeamsList = By.xpath("//div[@id='divDynamicDRPlanSections']/div[11]//div[@class='span12 mrgleft0 dynamcic-section-workarea']/div[2]/div[2]");
	
	public static By tgsec2ndTxt1 = By.xpath("//div[@id='divDynamicDRPlanSections']/div[11]//div[@class='span12 mrgleft0 dynamcic-section-workarea']/div[2]/div[2]");
	public static By tgsec2ndTxt = By.xpath("//div[@id='divDynamicDRPlanSections']/div[11]//div[@class='span12 mrgleft0 dynamcic-section-workarea']/div[3]/div[2]");

	public static By tgsecdfTaskGroupName = By.xpath("//div[@id='pri-mod-flds-holder']/span[2]/input");
	public static By tgsecdfSummary = By.xpath("//div[@id='pri-mod-flds-holder']/span[3]/input");
	
	public static By tgsecmHovrOnFirstOptn = By.xpath("//div[@id='divDynamicDRPlanSections']/div[11]//div[@class='span12 mrgleft0 dynamcic-section-workarea']/div/div[2]");
	public static By tgsecmHovrOnSecOptn = By.xpath("//div[@id='divDynamicDRPlanSections']/div[11]//div[@class='span12 mrgleft0 dynamcic-section-workarea']/div[2]/div[2]");
	public static By tgsecmHovrOnThirdOptn = By.xpath("//div[@id='divDynamicDRPlanSections']/div[11]//div[@class='span12 mrgleft0 dynamcic-section-workarea']/div[3]/div[2]");

	public static By tgsecmoveLastIcon = By.xpath("//div[@id='divDynamicDRPlanSections']/div[11]//i[@class='icon-circle-arrow-down']");
	public static By tgsecmoveTopIcon = By.xpath("//div[@id='divDynamicDRPlanSections']/div[11]//div[@class='span12 mrgleft0 dynamcic-section-workarea']/div[2]//a[2]/i");
	public static By tgsecmoveDownIcon = By.xpath("//div[@id='divDynamicDRPlanSections']/div[11]//i[@class='icon-arrow-down']");
	public static By tgsecmoveUpIcon = By.xpath("//div[@id='divDynamicDRPlanSections']/div[11]//div[@class='span12 mrgleft0 dynamcic-section-workarea']/div[2]//a[3]/i");
	public static By tgsecremoveIcon = By.xpath("//div[@id='divDynamicDRPlanSections']/div[11]//div[@class='span12 mrgleft0 dynamcic-section-workarea']/div[3]//a/i");
	
	
	//		Business Functions Section
	public static By bfsecDescription = By.xpath("//div[@id='divDynamicDRPlanSections']/div[12]//div[@class='dynamcic-section-desc']");

	public static By bfsecAddTextBtn = By.xpath("//div[@id='divDynamicDRPlanSections']/div[12]//button[contains(text(),'Add Text')]");
	public static By bfsecAddImageBtn = By.xpath("//div[@id='divDynamicDRPlanSections']/div[12]//button[contains(text(),'Add Image')]");
	public static By bfsecAddDataBtn = By.xpath("//div[@id='divDynamicDRPlanSections']/div[12]//button[contains(text(),'Add Data')]");
	public static By bfsecEditSectionBtn = By.xpath("//div[@id='divDynamicDRPlanSections']/div[12]//button[contains(text(),'Edit Section')]");
	public static By bfsecDeleteSectionBtn = By.xpath("//div[@id='divDynamicDRPlanSections']/div[12]//button[contains(text(),'Delete Section')]");

	public static By bfsecAuthorizedByNm = By.xpath("//div[@id='divDynamicDRPlanSections']/div[12]//span[@class='dynamcic-section-authorizedby']");
	public static By bfsecVerifiedByNm = By.xpath("//div[@id='divDynamicDRPlanSections']/div[12]//span[@class='dynamcic-section-verifiedby']");

	public static By bfsecfirstText = By.xpath("//div[@id='divDynamicDRPlanSections']/div[12]//div[@class='dynamic-control-preview']/div[2]");
	public static By bfsecTeamsList = By.xpath("//div[@id='divDynamicDRPlanSections']/div[12]//div[@class='span12 mrgleft0 dynamcic-section-workarea']/div[2]/div[2]");
	
	public static By bfsec2ndTxt1 = By.xpath("//div[@id='divDynamicDRPlanSections']/div[12]//div[@class='span12 mrgleft0 dynamcic-section-workarea']/div[2]/div[2]");
	public static By bfsec2ndTxt = By.xpath("//div[@id='divDynamicDRPlanSections']/div[12]//div[@class='span12 mrgleft0 dynamcic-section-workarea']/div[3]/div[2]");

	public static By bfsecdfFunctionName = By.xpath("//div[@id='pri-mod-flds-holder']/span[2]/input");
	public static By bfsecdfPriority = By.xpath("//div[@id='pri-mod-flds-holder']/span[5]/input");
	public static By bfsecdfEmployeeAssigned = By.xpath("//div[@id='pri-mod-flds-holder']/span[6]/input");
	public static By bfsecdfLossperDay = By.xpath("//div[@id='pri-mod-flds-holder']/span[7]/input");

	public static By bfsecmHovrOnFirstOptn = By.xpath("//div[@id='divDynamicDRPlanSections']/div[12]//div[@class='span12 mrgleft0 dynamcic-section-workarea']/div/div[2]");
	public static By bfsecmHovrOnSecOptn = By.xpath("//div[@id='divDynamicDRPlanSections']/div[12]//div[@class='span12 mrgleft0 dynamcic-section-workarea']/div[2]/div[2]");
	public static By bfsecmHovrOnThirdOptn = By.xpath("//div[@id='divDynamicDRPlanSections']/div[12]//div[@class='span12 mrgleft0 dynamcic-section-workarea']/div[3]/div[2]");

	public static By bfsecmoveLastIcon = By.xpath("//div[@id='divDynamicDRPlanSections']/div[12]//i[@class='icon-circle-arrow-down']");
	public static By bfsecmoveTopIcon = By.xpath("//div[@id='divDynamicDRPlanSections']/div[12]//div[@class='span12 mrgleft0 dynamcic-section-workarea']/div[2]//a[2]/i");
	public static By bfsecmoveDownIcon = By.xpath("//div[@id='divDynamicDRPlanSections']/div[12]//i[@class='icon-arrow-down']");
	public static By bfsecmoveUpIcon = By.xpath("//div[@id='divDynamicDRPlanSections']/div[12]//div[@class='span12 mrgleft0 dynamcic-section-workarea']/div[2]//a[3]/i");
	public static By bfsecremoveIcon = By.xpath("//div[@id='divDynamicDRPlanSections']/div[12]//div[@class='span12 mrgleft0 dynamcic-section-workarea']/div[3]//a/i");
	
	//		DR Preview
	public static By drPreviewScrOutrBdy = By.xpath("//div[@id='divDRPlanOutput']/div[2]");
	
	// Create New Section
	public static By drCreateNewSectBtn=By.xpath("//a[contains(text(),'Create New Section')]");
	public static By drTitleTotalVal=By.xpath("//div[@id='ddlNewDynamicSectionTitle_chzn']/div/ul/li");
	public static By drTotalSectionsCount=By.xpath("//div[@id='divDynamicDRPlanSections']/div");
	
	// Manage DR Template
	
	//My Templates
	public static By drMyTempSearchBox=By.xpath("//div[@id='divMyTemplates_MT']/div[1]/div[3]/input");
	public static By drMyTempSearchBtn=By.xpath("//div[@id='divMyTemplates_MT']/div[1]/div[3]/button[1]");
	public static By drMyTempClearBtn=By.xpath("//div[@id='divMyTemplates_MT']/div[1]/div[3]/button[2]");
	public static By drMyTempSearchFstName=By.xpath("//div[@id='divMyTemplates_MT']/div[2]/div[1]/p[1]");
	public static By drMyTempShowOrHideBtn=By.xpath("//div[@id='divMyTemplates_MT']/div[1]/div[1]/a");
	
	public static By drMyTempManageDRTemps=By.xpath("//div[@id='content']/div[2]/div[1]/p/a[2]");
	public static By drMyTempTotalCount=By.xpath("//div[@id='divMyTemplates_MT']/div[2]/div");
	public static By drMyTempLoadMreBtn=By.xpath("//div[@id='divMyTemplates_MT_More']/button");
	public static By drMyTempPreview=By.cssSelector("li[onclick='Preview_DRTemplate(this);']");
	public static By drMyTempPrevNme=By.cssSelector("#lblTemplateName_TP");
	public static By drMyTempPrevTtlSecCount=By.xpath("//div[@id='divSectionList_TP']/div");
	public static By drMyTempPrevSecName=By.xpath("//div[@id='divTemplatePreview']/div[2]/div[2]/div[2]/span");
	public static By drMyTempPrevCloseBtn=By.xpath("//div[@id='divTemplatePreview']/div[3]/button");
	
	public static By drMyTempPrivateTemp=By.xpath("//div[@class='span3 dr-tmplte-item dr-tmplte-private drt-act-registered']");
	public static By drMyTempSharedTemp=By.xpath("//div[@class='span3 dr-tmplte-item dr-tmplte-shared drt-act-registered']");
	public static By drMyTempPublicTemp=By.xpath("//div[@class='span3 dr-tmplte-item dr-tmplte-public drt-act-registered'][@data-desc='']");
	
	public static By drMyTempSharedTitleNme=By.xpath("//div[@id='divViewSharedOrg']/div[1]/h3");
	public static By drMyTempSharedSaveBtn=By.cssSelector("button[class='btn'][onclick='EditShare_DRTemplate();return false;']");
	public static By drMyTempSharedCloseBtn=By.xpath("//div[@id='divViewSharedOrg']/div[3]/button[2]");
	public static By drMyTempShareTitle=By.xpath("//div[@id='divShareTemplate']/div[1]/h3");
	public static By drMyTempSearchOrgFrSharing=By.xpath("//div[@id='ddlOrgToShareTemplate_ST_chzn']/ul");
	public static By drMyTempTotalOrgCountFrSharing=By.xpath("//div[@id='ddlOrgToShareTemplate_ST_chzn']/div/ul/li");
	public static By drMyTempOrgShareBtn=By.xpath("//div[@id='divShareTemplate']/div[3]/button[1]");
	public static By drMyTempOrgCloseBtn=By.xpath("//div[@id='divShareTemplate']/div[3]/button[2]");
	
	public static By drMyTempEditTitleNme=By.xpath("//div[@id='divEditTemplate']/div[1]/h3");
	public static By drMyTempEditTempNme=By.xpath("//input[@id='txtDrTemplateName_ET']");
	public static By drMyTempEditTempDesc=By.xpath("//input[@id='txtDrTemplateDesc_ET']");
	public static By drMyTempEditTempSaveBtn=By.xpath("//div[@id='divEditTemplate']/div[3]/button[1]");
	public static By drMyTempEditCloseBtn=By.xpath("//div[@id='divEditTemplate']/div[3]/button[2]");
	
	public static By drMyTempEdtShrOrgTtlCnt=By.xpath("//div[@id='divSharedOrgList_VSO_chzn']/div/ul/li");
	public static By drMyTempEdtShrSearchBox=By.xpath("//div[@id='divSharedOrgList_VSO_chzn']/ul");
	
	//Templates shared by others
	public static By drTempShareSection=By.xpath("//div[@id='divSharedTemplates_MT']/div[1]/h2");
	public static By drTempShareShowOrHideBtn=By.xpath("//div[@id='divSharedTemplates_MT']/div[1]/div[1]/a");
	public static By drTempShareSearchBox=By.xpath("//div[@id='divSharedTemplates_MT']/div[1]/div[2]/input");
	public static By drTempShareSearchBtn=By.xpath("//div[@id='divSharedTemplates_MT']/div[1]/div[2]/button[1]");
	public static By drTempShareClearBtn=By.xpath("//div[@id='divSharedTemplates_MT']/div[1]/div[2]/button[2]");
	
	public static By drTempShareTtlCnt=By.xpath("//div[@id='divSharedTemplates_MT']/div[2]/div");
	
	//	Public Templates of Other Organization 
	public static By drPublicTempSection=By.xpath("//div[@id='divPublicTemplates_MT']/div[1]/h2");
	public static By drPublicTempShowOrHideBtn=By.xpath("//div[@id='divPublicTemplates_MT']/div[1]/div[1]/a");
	public static By drPublicTempSearchBox=By.xpath("//div[@id='divPublicTemplates_MT']/div[1]/div[2]/input");
	public static By drPublicTempSearchBtn=By.xpath("//div[@id='divPublicTemplates_MT']/div[1]/div[2]/button[1]");
	public static By drPublicTempClearBtn=By.xpath("//div[@id='divPublicTemplates_MT']/div[1]/div[2]/button[2]");
	public static By drPublicTempSearchFstNme=By.xpath("//div[@id='divPublicTemplates_MT']/div[2]/div[1]/p[1]");
	
	public static By drPublicTempLoadMreBtn=By.xpath("//div[@id='divPublicTemplates_MT_More']/button");
	public static By drPublicTempTtlCnt=By.xpath("//*[@id='divPublicTemplates_MT']/div[2]/div");
	
	public static By drPublicTempPrevName=By.xpath("//div[@id='divTemplatePreview']/div[1]/h3");
	public static By drPublicTempSaveCopyTitle=By.xpath("//div[@id='divSaveAsCopy']/div[1]/h3");
	public static By drPublicTempSaveCopyBtn=By.xpath("//div[@id='divSaveAsCopy']/div[3]/button[1]");
	public static By drPublicTempSaveCopyCloseBtn=By.xpath("//div[@id='divSaveAsCopy']/div[3]/button[2]");
	public static By drpublicTempSaveCopyTextBox=By.xpath("//input[@id='txtDrTemplateName_SAC']");
	
	//Template Type
	public static By drTempTypePublic=By.xpath("//div[@id='divManageTemplates']/div[1]/div/div/div[1]");
	public static By drPublicNoTempMsg=By.cssSelector("#Not_divPublicTemplates_MT");
	public static By drTempTypePrivate=By.xpath("//div[@id='divManageTemplates']/div[1]/div/div/div[2]");
	public static By drPrivateMyTempNoTempMsg=By.cssSelector("#Not_divMyTemplates_MT");
	public static By drTempTypeShared=By.xpath("//div[@id='divManageTemplates']/div[1]/div/div/div[3]");
	public static By drSharedNoTempMsg=By.cssSelector("#Not_divSharedTemplates_MT");
	
	
	//Change PDF Layout
	
	public static By drChangPdfLayOutBtn=By.xpath("//div[@id='content']/div[2]/div[1]/p/a[6]");
	public static By pdfLayoutPageTitle=By.xpath("//div[@id='content']/div[2]/div[1]/h2");
	public static By logoSizeSlider=By.xpath("//div[@id='range_logo_size']");
	public static By borderWidthSlider=By.xpath("//div[@id='range_border_size']");
	public static By borderSpaceSlider=By.xpath("//div[@id='range_border_gap']");
	public static By borderColour=By.cssSelector("#color_picker");
	
	public static By pdfLayoutSaveBtn=By.xpath("//button[@id='save_layout']");
	public static By pdfLayoutClearBtn=By.xpath("//button[@id='reset_editor']");
	public static By pdfLayoutRestoreBtn=By.xpath("//button[@id='restore_layout']");
	public static By pdfLayoutCancelBtn=By.xpath("//div[@id='content']/div[2]/div[2]/div[3]/a");
	
	public static By pdfLayoutEditor=By.xpath("//div[@id='editor_content']");
	public static By pdfLayoutEditorTextBox=By.xpath("//html/body/div[1]/div/div[5]");
	public static By pdfLayoutEditorSaveBtn=By.xpath("//button[@id='editor_save']");
	public static By pdfLayoutEditorCancelBtn=By.xpath("//div[@id='editior_popup']/div[3]/button[2]");
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}

