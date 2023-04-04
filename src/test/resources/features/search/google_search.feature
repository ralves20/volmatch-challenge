Feature: Google Search

  @google
  Scenario: Searching for a term
    Given Sergey is researching things on the internet
    And he searches for "Weather London"
    When he verifies the temperature in the location that he searched
    Then he should see that the temperature is the same as the existant in the OpenWeatherMap for "London"


