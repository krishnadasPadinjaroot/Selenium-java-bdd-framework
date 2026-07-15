@Regression
Feature: Login

#  Scenario: verify Order placement-"standard_user"
#
#    Given user opens HomePage
#    When user login with username "standard_user" and password "secret_sauce"
#    And user add products to cart
#    And user navigate to cart page
#    And user navigate to check out page
#    And user enter Checkout Info And Finish
#    Then user verify success message
#    And user logOut

  Scenario: Verify LastName Error Msg-"problem_user"

    Given user opens HomePage
    When user login with username "problem_user" and password "secret_sauce"
    And user add products to cart
    And user navigate to cart page
    And user navigate to check out page
    Then user enter Checkout Info And Verify LastName Error Msg
    And user logOut
