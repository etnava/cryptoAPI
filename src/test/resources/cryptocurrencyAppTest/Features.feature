Feature: Does the API Work?

  Scenario Outline: Check if user is able to submit GET API Request
    Given I execute <service> api
    When I submit the GET REQUEST
    Then I verify that <statuscode>

    Examples: 
      | service | statuscode |
      | hello   |        404 |
