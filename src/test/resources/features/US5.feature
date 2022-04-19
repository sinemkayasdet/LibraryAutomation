Feature: As a data consumer, I want UI and DB book information are match.

  Scenario: Verify book information with DB
    Given  Establish the database connection
    And I navigate to "Books" page
    When I open book "Chordeiles minor"
    Then book information must match the Database
      | isbn     | 387448631259     |
      | name     | Chordeiles minor |
      | author   | Waylon Connold   |
      | category | Classic          |
      | year     | 2005             |