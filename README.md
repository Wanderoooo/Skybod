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

## Code Credit
JsonWriter JsonReader classes, along with corresponding tests, Writable interface, implementation
of toJson in each class in model package all involve code with inspiration/direct usage of
code template from WorkRoomApp at https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git

Some java SWING library code were taken from https://docs.oracle.com/javase/tutorial/uiswing/, and Stack
Overflow user contributions.

## Instructions for Grader

- You can generate the first required action related to adding Xs to a Y by:
1. Complete registration
2. Navigate to the "Bookings" tab
3. Click the "Create New Booking" button
4. Enter the day, time, aircraft type, instructor name, and toggle the type of lesson button
5. Click "Confirm & Book" button
6. Now you can see your added booking in the scroll pane in the "Bookings" tab


- You can generate the second required action related to adding Xs to a Y (removing X from Y) by:
1. Single click on the booking you'd like to cancel in the scroll pane in the "Bookings" tab
2. Click on "Cancel Booking" below


- You can locate my visual component by:
1. My visual component is the image on the splash screen when the gui is first launched
   (created with Canva.com)


- You can save the state of my application by:
1. Clicking on the X button top right of the window
2. Click "Yes" on the popup dialogue box


- You can reload the state of my application by:
1. After launching of the gui, single click to exit the splash screen
2. Click "Continue from last saved" button

## Phase 4: Task 2
* Mon Apr 03 15:36:45 PDT 2023
* Registers the pilot
* Mon Apr 03 15:36:46 PDT 2023
* Updates last METAR
* Mon Apr 03 15:36:47 PDT 2023
* Updates last TAF
* Mon Apr 03 15:36:47 PDT 2023
* Updates last TAF
* Mon Apr 03 15:36:48 PDT 2023
* Updates last METAR
* Mon Apr 03 15:36:50 PDT 2023
* Created a new booking
* Mon Apr 03 15:36:52 PDT 2023
  Removed a booking from scheduled bookingsRemoved a booking from scheduled bookings

## Phase 4: Task 3

If I had more time, there are a lot of possible improvements (refactoring) I'd make. 
For one, my entire project consists of 30 classes, with my model having 14 classes. As
can be seen in my UML diagram, this resulted in high coupling and low cohesion, with many
classes involving lots of fields of each other. If I had more time, I'd refactor many of
the smaller classes, such as PlaneFlightLog and PilotLog, into inner classes of Plane and Pilot
since no other class requires of them. I'd also split my classes into three different folders:
piloting, plane, and weather, to better organize them into their respective functionalities,
and thus increase cohesion within these folders; allowing high cohesion within and low coupling
between.

The amount of classes I have hindered the scalability and modifiability of my project, which
led to a difficult phase 2 period as I needed to manually implement the toJson method in every
class and edit every class to implement the Writable interface. This led to a lot of redundancy
in code, such as for every list in the model, I have to code an almost identical JSONArray method.
If I had more time, I would create a new abstract class and subtype classes involving list to it,
so that I can reuse the same JSONArray list in the appropriate classes and reduce redundancy.
I would also split the GUI code into more classes, as to assign different responsibility to classes
and expedite debugging.