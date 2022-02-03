Feature: Testing of New Incident Creation.

Scenario: Testing of Positive Flow of Incident Creation.


#Given Open the Chrome Browser
#And Open the Application Url 'https://dev87778.service-now.com/navpage.do'
#When Enter the username as 'admin'
#And Enter the password as 'Sra@1234'
#And Click on Login Button
#And Enter 'Incident' in the Search field and Press Enter key
And Click on 'Create New' Link
And Click on Caller id Lookup
And Select the value as 'Abel Tuter' in the New Window
And Click on Short Description lookup
And Select the value as 'Issue with a web page' in the New Window
And Get the Incident number value
And Click on Submit Button
And Verify the incident is successfully created.
