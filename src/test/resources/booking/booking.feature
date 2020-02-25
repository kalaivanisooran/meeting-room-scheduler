  Feature: Booking glass door meeting room
      Everybody want to book the glass door for their meetings

    Scenario Outline: Book the meeting room with valid input
      Given User logged the admin panel
      When User provide valid input <request>
      Then Booking the meeting room
      And User will get respective <response>

      Examples:
        | request | response |
        |{"roomId":1,"usrEmpId":"4567", "userName":"Team","bookingDate":"2020-03-01","bookingStartTime":"11:00:00","bookingEndTime":"11:30:00"} |   200 |