Feature: Booking glassdoor meeting room
    Everybody want to book the glassdoor for their meetings

  Scenario Outline: eating
    Given User logged the adminpanel
    When User provide valid <request>
    Then Booking the meeting room
    And User will get the <response>

    Examples:
      | request | response |
      |{"roomId":1,"usrEmpId":"4567", "userName":"Team","bookingDate":"2020-03-01","bookingStartTime":"11:00:00","bookingEndTime":"11:30:00"} |   200 |