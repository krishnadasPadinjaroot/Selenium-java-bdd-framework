@smoke
Feature: Locked out User

  Scenario: verify Login of Locked out_user"

    Given user opens HomePage
    When user login with username "locked_out_user" and password "secret_sauce"
    Then  User Verify User Locked out error message


