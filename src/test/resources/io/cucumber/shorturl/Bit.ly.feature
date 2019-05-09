Feature: Using bit.ly for creating shorturl

@Positive
  Scenario: Create a shorturl in bit.ly
    Given open bit.ly url
    Then  Display the main page
    Given User logs in as user "vinaybshetty@gmail.com" and password "Welcome123"
    Then User Home Page is displayed
    Given User click on Create button
    And creates short url for the link "https://www.youtube.com/watch?v=K6RPMhcRICE"
    Then short url should be created
    Given open shorturl
    Then It should redirect to the long URL "https://www.youtube.com/watch?v=K6RPMhcRICE"
    
   @Negative 
    Scenario: Invalid shorturl in bit.ly
    Given open bit.ly url
    Then  Display the main page
    Given User logs in as user "vinaybshetty@gmail.com" and password "Welcome123"
    Then User Home Page is displayed
    Given User click on Create button
    And creates short url for the link "test"
    Then Error message should be displayed
    
     @Negative 
    Scenario: Invalid login in bit.ly
    Given open bit.ly url
    Then  Display the main page
    Given User logs in as user "test" and password "test123"
    Then Invalid Login message is displayed
    