Login story

Narrative:
In order to login successfully into Demo application
As a developer
I want to use Behaviour-Driven Development
					 
Scenario:  Login page for Demo (Success)
Given the user is at login page
When the username is passed as test1
And the password is passed as pwd
And login button is clicked
Then the page title is Login Success

Scenario:  Login page for Demo (Failure)
Given the user is at login page
When the username is passed as test
And the password is passed as pwd
And login button is clicked
Then the page title is Login Failure
