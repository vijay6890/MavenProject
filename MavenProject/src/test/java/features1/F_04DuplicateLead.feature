Feature: Testing the DuplicateLEad


@Regression
Scenario: Test with Positive Credential

When Click on 'Find Leads' link
Then 'Find Leads' Page should be displayed
Then Click on Phone tab
Given Enter the Phone number as '99'
Then Click on 'Find Leads' Button
And Click on First row 
And Click on 'Duplicate Lead' link
And Click on Submit Button