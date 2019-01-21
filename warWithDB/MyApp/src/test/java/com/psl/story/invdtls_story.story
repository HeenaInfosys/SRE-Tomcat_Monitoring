Navigating to Inventory details page

Narrative:
In order to login successfully into Demo application
As a developer
I want to use Behaviour-Driven Development
					 
Scenario:  Verify Inventory Details Page
Given the user login successfully
When the user is at Employee Detail screen 
And he clicks on submit button
Then page title is Inventory Details
And on clicking button for cars having price < 10000, corresponding car details are displayed
And on clicking button for cars having price > 10000, corresponding car details are displayed
And on clicking back button user is navigated to login page again having title as Login Form
