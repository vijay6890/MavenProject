Feature: Testing the DeleteLead

@Smoke @Regression
Scenario: Test with Positive Credential

When Click on 'Find Leads' link
Then 'Find Leads' Page should be displayed
Then Click on Phone tab
Given Enter the Phone number as '99'
Then Click on 'Find Leads' Button
And Click on First row 
And Click on 'Delete' link
When Click on 'Find Leads' link
Then 'Find Leads' Page should be displayed
When Enter the Lead ID in the Lead ID field
Then Click on 'Find Leads' Button
Then No Records should be available