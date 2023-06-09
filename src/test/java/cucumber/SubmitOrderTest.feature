
@tag
Feature: Submitting the order for Ecommerce website
  I want to use this template for my feature file

Background:
Given Logging into the application


  @Regression
  Scenario Outline: Positive test of submitting the order
    Given Logged in with Username <Username> and <Password>
    When Adding the product <ProductName> to the cart
    And Checkout <ProductName> and Submit the order
    Then I verify "THANKYOU FOR THE ORDER." message is displayed on confirmation page

    Examples: 
      |        UserName          | Password      | ProductName |
      | chandanhngowda@gmail.com | Password@2022 | ZARA COAT 3 |
