Feature: Testing the CreateLead

@Functional @Regression
Scenario Outline: CreateLead with Mandatory information

#Given Open the Chrome Browser
#And Open the Application Url 'http://leaftaps.com/opentaps/'
#Given Enter <username> as Username
#And Enter <password> as Password
#When Click on Login Button
#Then Title should be matching with 'Leaftaps - TestLeaf Automation Platform'
#When Click on 'CRM/SFA' link
#Then 'My Home' Page should be displayed
#When Click on 'Leads' link
#Then 'My Leads' Page should be displayed

When Click on 'Create Lead' link
Then 'Create Lead' Page should be displayed
Given Enter the Company name as 'Testleaf'
And Enter the FirstName as 'Hari'
And Enter the LastName as 'R'
When Click on Create Lead button
#Then New Lead should be created

Examples: 
|username|password|
|'DemoSalesManager'|'crmsfa'|

