Feature: Randrr I'm Interested
        As a user I should able to request to stay in touch.
 
 Scenario: I enter invalid email 
        Given I navigate to "http://www.randrr.com"
        Then I should see page title having partial text as "Revolutionizing recruiting for the common"
        And I enter "A-Garbage-Email-Address" into input field having xpath "(//input[@name='email'])[1]"
        And I click on element having value "I'm Interested"
        Then element having xpath "//label[@data-reactid='.0.0:$0.$email.3.$0.0']" should have text as "Email must be formatted correctly."
        