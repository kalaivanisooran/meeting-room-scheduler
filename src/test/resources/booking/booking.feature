  Feature: Booking glass door meeting room
      Everybody want to book the glass door for their meetings

  Scenario Outline: Book the meeting room with valid input
       Given User logged the admin panel
       When User provide content-type application/json
       And User provide <valid> input <request>
       And response message should be <response_message>
       And User will get respective <response_status_code>

       Examples:
        | case | request | response_status_code | response_message |
        | Valid Input |{"roomId":1,"usrEmpId":"1234", "userName":"Team","bookingStartDate":"2120-10-01","bookingStartTime":"11:00:00","bookingEndTime":"11:30:00","mode":"today","purpose":"Retro"} | 200 | {"status":200,"message":"Meeting room booked successfully"} |
        | Valid Input with no user name |{"roomId":1,"usrEmpId":"1234","bookingStartDate":"2120-10-02","bookingStartTime":"11:00:00","bookingEndTime":"11:30:00","mode":"tomorrow","purpose":"Retro"} | 200 | {"status":200,"message":"Meeting room booked successfully"} |
        | Valid Input with custom date |{"roomId":1,"usrEmpId":"1234","bookingStartDate":"2120-11-02","bookingStartTime":"11:00:00","bookingEndTime":"11:30:00","mode":"custom","purpose":"Retro","customBookingDate":["2120-04-10","2120-04-13","2120-04-22","2120-05-30"]} | 200 | {"status":200,"message":"Meeting room booked successfully"} |


 Scenario Outline: Book the meeting room with invalid input
       Given User logged the admin panel
       When User provide content-type application/json
       And User provide <invalid> input <request>
       And response message should be <response_message>
       And User will get respective <response_status_code>

       Examples:
         | case | request | response_status_code | response_message |
         | Invalid input with no roomId |{"usrEmpId":"1234", "userName":"Team","bookingStartDate":"2020-10-01","bookingStartTime":"11:00:00","bookingEndTime":"11:30:00","purpose":"Retro","mode":"today","purpose":"Retro"} | 400 | {"status":400,"message":"Room Id should not be empty"} |
         | Invalid input with no purpose |{"roomId":2,"usrEmpId":"1234", "userName":"Team","bookingStartDate":"2020-10-01","bookingStartTime":"11:00:00","bookingEndTime":"11:30:00","mode":"today"} | 400 | {"status":400,"message":"Purpose should not be empty"} |
         | Invalid input with no mode |{"roomId":1,"bookingStartDate":"2020-10-01","bookingStartTime":"10:00:00","bookingEndTime":"10:30:00","purpose":"Retro"} | 400 | {"status":400,"message":"Mode should not be empty"}|
         | Invalid input with invalid mode |{"roomId":1,"bookingStartDate":"2020-10-01","bookingStartTime":"10:00:00","bookingEndTime":"10:30:00","purpose":"Retro","mode":"invalidmode"} | 400 | {"status":400,"message":"Invalid mode type"}|
         | Invalid Input with no start Time |{"roomId":1,"usrEmpId":"1234", "userName":"Team","bookingStartDate":"2020-10-01","bookingEndTime":"11:30:00","mode":"today","purpose":"Retro"} | 400 | {"status":400,"message":"Start Time should not be empty"} |
         | Invalid Input with no End Time |{"roomId":1,"usrEmpId":"1234", "userName":"Team","bookingStartDate":"2020-10-01","bookingStartTime":"11:00:00","mode":"today","purpose":"Retro"} | 400 | {"status":400,"message":"End Time should not be empty"} |
         | Invalid Input with same Start Time and End Time |{"roomId":1,"usrEmpId":"1234","bookingStartDate":"2020-10-01","bookingStartTime":"11:00:00","bookingEndTime":"11:00:00","mode":"today","purpose":"Retro"} | 400 | {"status":400,"message":"End Time should be greater than Start Time"} |
         | Invalid Input with End Time > Start Time |{"roomId":1,"usrEmpId":"1234","bookingStartDate":"2020-10-01","bookingStartTime":"11:30:00","bookingEndTime":"11:00:00","mode":"today","purpose":"Retro"} | 400 | {"status":400,"message":"End Time should be greater than Start Time"} |