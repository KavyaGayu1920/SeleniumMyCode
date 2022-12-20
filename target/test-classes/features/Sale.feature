Feature: VT successful transaction should be performed in merchant login

@Login
Scenario Outline: Login with valid credential and do sale transaction
    Given Valid merchant credential like "<mailID>" and "<password>" to login portal
    When I Click on login button 
    Then Merchant account page will be displayed
    And I Clicked on VT module
    And I Entered all valid transaction details like 
    |amount        |100.00											|
    |mobilenum     |9941453510									|
    |mail			     |gayathrikavya1920@gmail.com |
    |PAN			     |4111111111111111						|
    |expiry        |1226												|
    When I clicked on process button
    When I clicked on process button in confirmation screen
    Then Transaction should be approved or declined "<expectedResult>"
Examples:
|mailID															 |password  |expectedResult|
|gayathrikavya1920+merchant@gmail.com|1234Qwerty|Success			 |
