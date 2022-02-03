Feature: Testing the EditLead

@Smoke
Scenario Outline: Test with Positive Credential

When Click on 'Find Leads' link
Then 'Find Leads' Page should be displayed
Then Click on Phone tab
Given Enter the Phone number as '99'
Then Click on 'Find Leads' Button
And Click on First row 
And Click on Edit Button
And Modify the CompanyName as 'Infosys'
And Click on Submit Button
	

#Then New Lead should be created

Examples: 
|username|password|
|'DemoSalesManager'|'crmsfa'|

