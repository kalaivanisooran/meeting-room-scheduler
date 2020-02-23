Feature: Booking glassdoor meeting room
    Everybody want to book the glassdoor for their meetings

  Scenario: User provided the valid input
    Given User logged the adminpanel
    When User provide valid input
    Then Booking the meeting room for that schedule should be successful