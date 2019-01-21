Navigating to Employee details page

Narrative:
In order to verify User Acceptance Criteria in Demo application
As a developer
I want to use Behaviour-Driven Development
					 
Scenario:  Verify Car Inventory Addition

Given the user is at Employee Details Page
When the user click Add car details
And enters Manufacturer as BMW
And enters Car Name as 320d
And enters Color as Black
And enters price as 6000
Then on clicking Add button, entered details : BMW , 320d , Black , 6000 should be present at Inventory details Page
And under price<10000 USD range
