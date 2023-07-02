Feature: Testing the application-CRM system.

  @Activity1
  Scenario: Verify the website title
    Given user is on CRM login page
    Then user reads the title and verify text

  @Activity2
  Scenario: Get the url of the header image
    Given user is on CRM login page
    Then user gets the url of header image

  @Activity3
  Scenario: Get the copyright text
    Given user is on CRM login page
    Then user gets the first copyright text in the footer

  @Activity4
  Scenario: Logging into the site
    Given user is on CRM login page
    When user enters credentials to login
    Then verify the homepage

  @Activity5
  Scenario: Get the color of the navigation menu
    Given user is on CRM login page
    When user enters credentials to login
    Then user gets the color of navigation menu

  @Activity6
  Scenario: Make sure that the “Activities” menu item exists and is clickable
    Given user is on CRM login page
    When user enters credentials to login
    Then user finds and selects Activities menu item

  @Activity7
  Scenario: Reading a popup that contains additional information about the account/lead
    Given user is on CRM login page
    When user enters credentials to login
    Then user reads additional info about lead

  @Activity8
  Scenario: Open the accounts page and print the contents of the table
    Given user is on CRM login page
    When user enters credentials to login
    Then user navigates to accounts and print the table contents

  @Activity9
  Scenario: Open the leads page and print the usernames and full names from the table
    Given user is on CRM login page
    When user enters credentials to login
    Then user navigates to leads page and print their fullnames and usernames