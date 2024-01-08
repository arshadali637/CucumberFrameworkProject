Feature: Login 

#@Sanity @regression execute for both if we qwrite like this then

@Sanity
Scenario: Successful Login with Valid Credentials 
	Given User Launch Chrome browser 
	When User opens URL "https://admin-demo.nopcommerce.com/login" 
	And User enters Email as "admin@yourstore.com" and Password as "admin" 
	And Click on Login 
	Then Page Title should be "Dashboard / nopCommerce administration" 
	When User click on Log out link 
	Then Page Title should be "Your store. Login" 
	And close browser 
	
@regression
Scenario Outline:Successful Login with Valid Credentials DDT
	Given User Launch Chrome browser 
	When User opens URL "https://admin-demo.nopcommerce.com/login" 
	And User enters Email as "<email>" and Password as "<password>" 
	And Click on Login 
	Then Page Title should be "Dashboard / nopCommerce administration" 
	When User click on Log out link 
	Then Page Title should be "Your store. Login" 
	And close browser 

Examples:
|email|password|
|admin@yourstore.com|admin|
#|test@yourstore.com|admin|