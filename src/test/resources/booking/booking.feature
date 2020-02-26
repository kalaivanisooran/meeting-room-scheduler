  Feature: Booking glass door meeting room
      Everybody want to book the glass door for their meetings

 @positiveCase
  Scenario Outline: Book the meeting room with valid input
       Given User logged the admin panel
       When User provide <valid> input <request>
       Then Trying to book the meeting room
       And User will get respective <response_status_code>
       And response message should be <response_message>

       Examples:
        | case | request | response_status_code | response_message |
        | Valid Input |{"roomId":1,"usrEmpId":"1234", "userName":"Team","bookingDate":"2020-03-01","bookingStartTime":"11:00:00","bookingEndTime":"11:30:00"} | 200 | {"status":200,"message":"Meeting room booked successfully"} |
        | Valid Input with no user name |{"roomId":1,"usrEmpId":"1234","bookingDate":"2020-03-01","bookingStartTime":"11:00:00","bookingEndTime":"11:30:00"} | 200 | {"status":200,"message":"Meeting room booked successfully"} |


     Scenario Outline: Book the meeting room with invalid input
           Given User logged the admin panel
           When User provide <invalid> input <request>
           Then Trying to book the meeting room
           And User will get respective <response_status_code>
           And response message should be <response_message>

           Examples:
             | case | request | response_status_code | response_message |
             | Invalid input with no roomId |{"usrEmpId":"1234", "userName":"Team","bookingDate":"2020-03-01","bookingStartTime":"11:00:00","bookingEndTime":"11:30:00"} | 400 | {"status":400,"message":"Invalid. Requested room information is not available"} |
             | Invalid input with no usrEmpId |{"roomId":1,"userName":"Team","bookingDate":"2020-03-01","bookingStartTime":"11:00:00","bookingEndTime":"11:30:00"} | 400 | {"status":400,"message":"Invalid. Requested user information is not available"}|
             | Invalid Input with invalid usrEmpId |{"roomId":1,"usrEmpId":"4567", "userName":"Team","bookingStartTime":"11:00:00","bookingEndTime":"11:30:00"} | 400 | {"status":400,"message":"Invalid. Requested user information is not available"} |
             | Invalid Input with no start Time |{"roomId":1,"usrEmpId":"1234", "userName":"Team","bookingDate":"2020-03-01","bookingEndTime":"11:30:00"} | 400 | {"status":400,"message":"Start Time should not be empty"} |
             | Invalid Input with no End Time |{"roomId":1,"usrEmpId":"1234", "userName":"Team","bookingDate":"2020-03-01","bookingStartTime":"11:00:00"} | 400 | {"status":400,"message":"End Time should not be empty"} |
             | Invalid Input with same Start Time and End Time |{"roomId":1,"usrEmpId":"1234","bookingDate":"2020-03-01","bookingStartTime":"11:00:00","bookingEndTime":"11:00:00"} | 400 | {"status":400,"message":"End Time should be greater than Start Time"} |
             | Invalid Input with End Time > Start Time |{"roomId":1,"usrEmpId":"1234","bookingDate":"2020-03-01","bookingStartTime":"11:30:00","bookingEndTime":"11:00:00"} | 400 | {"status":400,"message":"End Time should be greater than Start Time"} |

