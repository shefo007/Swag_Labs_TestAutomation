Feature: Login

  Background:
    Given user is on login page

  Scenario: Successful login with valid credentials
    When user enters valid credentials
    And clicks login button with valid credentials
    Then user redirects to products page
    And sees products

  Scenario: Unsuccessful login with invalid credentials
    When user enters invalid credentials
    And clicks login button with invalid credentials
    Then user sees an error message appeared

  Scenario: Successful login-out
    When user enters valid credentials
    And clicks login button with valid credentials
    Then user redirects to products page
    And sees products
    When user click burger menu
    And click logout button
    Then user redirects to login page