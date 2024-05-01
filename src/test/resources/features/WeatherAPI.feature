Feature: Check air quality index for cities

  Scenario Outline: Check if the air quality index for given cities is 2 or above
    Given I have the city "<city>"
    When I fetch the weather data
    And I fetch the air pollution data using the coordinates
    Then I check if the air quality index is 2 or above

    Examples:
      | city       |
      | Melbourne  |
      | Manchester |
#      | Wellington |