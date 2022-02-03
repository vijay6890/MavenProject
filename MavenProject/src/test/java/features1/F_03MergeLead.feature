Feature: Testing the MergeLead


@Functional
Scenario: Test with Positive Credential

When Click on 'Merge Leads' link
Then 'Merge Leads' Page should be displayed
When Click on From Contact Lookup
Then New Window should be displayed
And Enter the First name as 'Mani'
When Click on 'Find Leads' Button
Then 'Lead List' Grid should be displayed
Then Get the text of First row link id data
And Click on First row data
And Click on To Contact Lookup
Then New Window should be displayed
And Enter the First name as 'babu'
When Click on 'Find Leads' Button
Then 'Lead List' Grid should be displayed
And Click on First row data
And Click on the 'Merge' Button
And Accept the Popup Alert
When Click on 'Find Leads' link
Then 'Find Leads' Page should be displayed
When Enter the Lead ID in the Lead ID field
When Click on 'Find Leads' Button
Then No Records should be available
