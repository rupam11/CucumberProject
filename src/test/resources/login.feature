Feature: Login functionality

  Background:
    Given user launches the application

  Scenario: Successful login with valid credentials
    When user enters username "tomsmith"
    And user enters password "SuperSecretPassword!"
    And user clicks on login button
    Then user should see the successful login message
    
  Scenario: Successful login with invalid username
    When user enters username "usernm"
    And user enters password "SuperSecretPassword!"
    And user clicks on login button
    Then user should see the failure login message
    
        
  Scenario: Successful login with invalid password
    When user enters username "tomsmith"
    And user enters password "PwdPwdPwd!"
    And user clicks on login button
    Then user should see the failure login message
    
    
 