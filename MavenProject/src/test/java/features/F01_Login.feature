Feature: Leaftaps Login Functionality

#Background: 
#Given Open the Chrome Browser
#And Load the Application Url 'http://leaftaps.com/opentaps/control/main'

Scenario Outline: Test with Positive Credential

And Enter <username> as Username
And Enter <password> as Password
When Click on Login Button
Then Title should be matching with 'Leaftaps - TestLeaf Automation Platform'



Examples: 
|username|password|
|'Demosalesmanager'|'crmsfa'|
|'Democsr'|'crmsfa'|


Scenario: Test with Negative Credential
And Enter 'demo' as Username
And Enter 'crmsfa' as Password
When  Click on Login Button
But Error page should be displayed
