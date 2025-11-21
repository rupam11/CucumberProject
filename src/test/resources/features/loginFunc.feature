Feature: Login functionality

  Background:
    Given user launches the application

  Scenario Outline: Login with different credentials
    When user enters username "<username>"
    And user enters password "<password>"
    And user clicks on login button
    Then login "<result>" message should be displayed

    Examples:
      | username  | password              | result   |
      | tomsmith  | SuperSecretPassword!  | success  |
      | tomsmith  | wrongPassword         | failure  |
      | wronguser | SuperSecretPassword!  | failure  |
