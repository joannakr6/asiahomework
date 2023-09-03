Feature: Add a new user's address after login


  Scenario Outline: Add a new address in the into field
    Given I'm on the shop authentication page
    When I login using "emilyjohnson@gmail.com" and "ejohnson1"
    And I go to my addresses page
    And I attempt to add a new address
    And I enter new address <alias>, "<address>", "<city>", <postcode>, <phone>
    Then I can see a new address
    And I verify created address has <alias>, "<address>", "<city>", <postcode>, <phone>
    And I remove the address
    And I can see there is no addresses
    And I close the browser
    Examples:
      | alias | address     | city       | postcode |  phone      |
      | Work  | Groove str. | Los Santos | 23-987   |  +123456789 |