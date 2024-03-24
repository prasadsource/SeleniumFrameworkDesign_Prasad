
@tag
Feature: Purchase order from Ecommerce website
  I want to use this template for my feature file


	Background:
	Given I landed on the Ecommerce site

  @tag2
  Scenario Outline: Positive Test Case of Submitting the Order
    Given Logged in with username with <name> and password <password>
    When I add the product <productName> from the cart
    AND Checkout <productName> and submit the order
    Then "THANKYOU FOR THE ORDER." message is displayed on the Confirmation Page.
    Examples: 
      | name  | password | productName | productName
			| prasadtest@gmail.com | Test@123 | ZARA COAT 3