# Skybod Flight Planner

## *Your personal sky buddy*

**GOAL:** <ins>Assist pilots in pre-flight & post-flight documentations and procedures</ins>

## **KEY Functionalities:**
- Allow users to book aeroplanes & instructors:
  - Displays aeroplane call-signs, types, and availability
  - Displays instructor names, ratings, pass rate, specialization, hourly rate
    <br></br>
- Provide booked aeroplane’s documentations: flying logs, Pilot Operating Handbook, plane Insurance & maintenance history
  - **blocks** booking if certification not met (ex. Night bookings require pilot to be instrument-rated, or need to be accompanied by an instructor)
    <br></br>
- Check weather reports:
  - METARS, TAFS, NOTAMS, SIGMARS, file pilot PIREP (NOTE:  these are preflight weather reporting systems)
  - **Generate** simple graphics for associated weather, **translates** weather code into simple language, cautions users when weather falls below flying minimums.
    <br></br>
- **Create & submit/save** flight plan to appropriate authorities & post-flight documentation:
  - File flight plan to Air Traffic Control
  - Automatically records flight data during flight, allow post-flight documentation in PILOT LOGS (gives user data, user needs to calculate flight & ground time, etc)

## *Built for pilots, built for flight*
Skybod Flight Planner is built for young and experienced pilots alike, including:
- Recreational pilots
- General aviation pilots
- Student pilots
- Aviation enthusiast
- Flight schools & flying clubs

## Project personal interest
<p>I am a student pilot. During my training, I’ve noticed the significant amount of documentation required pre-
and post-flight. Since aviation is quite a risky hobby, these procedures are essential as to risk management. However,
without a comprehensive checklist in place, it is extremely easy to unintentionally neglect a crucial component. 
Furthermore, student pilots are initially unfamiliar with how to submit certain documents, so an app with a user-friendly
interface with optional tutorial can enhance learning and pre-flight and post-flight documentation processes.</p>

## User Stories

- As a user, I want to be able to book aeroplanes of my desired type & instructor
- As a user, I want to be able to add and remove (cancel) bookings to/from my list of bookings
- As a user, I want to be able to check current weather report & weather forecast
- As a user, I want to be able to add my license and medical # in my profile
- As a user, I want to be able to add & complete preflight and postflight documentations
- As a user, I want to be able to record & save flight data in my pilot log
- As a user, when I select the quit option from the application menu, I want to be reminded to save my flight planner to 
file and have the option to do so or not.
- As a user, when I start the application, I want to be given the option to load my flight planner from file.