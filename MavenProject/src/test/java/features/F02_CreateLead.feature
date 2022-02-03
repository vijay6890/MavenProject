Feature: Leaftaps Login Functionality

#Background: 
#Given Open the Chrome Browser
#And Load the Application Url 'http://leaftaps.com/opentaps/control/main'

Scenario: Create Lead with mandatory information
Given Enter 'Demosalesmanager' as Username
Given Enter 'crmsfa' as Password
When Click on Login Button
Then Title should be matching with 'Leaftaps - TestLeaf Automation Platform'
When click on CRMSFA link