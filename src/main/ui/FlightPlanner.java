package ui;

import model.*;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

// Represents a flight planner console application, allows user to book, save, cancel booking, check weather
// and conduct preflight and postflight documentations. Intakes a user input

// CREDIT: CREDIT: json implementation code template from WorkRoomApp
// from https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git

public class FlightPlanner {
    private static final String JSON_STORE = "./data/pilot.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private Pilot pilot;
    private String choice;
    private Scanner sc;
    private String quit;
    private Booking booking;

    // EFFECT: create flight planner based on user input
    public FlightPlanner() {
        sc = new Scanner(System.in);
        pilot = new Pilot();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

        runFlightPlanner();

    }

    private void runFlightPlanner() {
        boolean isLoad = loadPilot();
        if (!isLoad) {
            initializePlaneInstrUser();
        }

        printMenu();
        while (!quit.equals("QUIT")) {
            menuOptions();
            System.out.println("To manage booking - 'BOOKING'\n"
                    + "To check weather - 'WX'\n"
                    + "To preflight - 'PREFLIGHT'\n"
                    + "To postflight - 'POSTFLIGHT'\n"
                    + "To quit - 'QUIT'");

            quit = sc.next().toUpperCase();

        }

        System.out.println("Would you like to save your progress to file?\n"
                + "1 - yes\n"
                + "2 - no");

        String isSave = sc.next();

        if (isSave.equals("1")) {
            savePilot();
        }
        System.out.println("Thanks for using Skybod Flight Planner, see you next time!");
    }


    // EFFECT: initialize plane, user, and instructor
    private void initializePlaneInstrUser() {
        initializePlaneInstr();
        registerUser();
    }

    // MODIFIES: this
    // EFFECT: loads pilot info from file
    private boolean loadPilot() {
        boolean isLoad = false;

        System.out.println("Would you like to load your last saved progress from file?\n"
                + "1 - yes\n"
                + "2 - no");

        String load = sc.next();

        if (load.equals("1")) {
            try {
                pilot = jsonReader.read();
                System.out.println("Loaded " + pilot.getName() + "'s info & progress from " + JSON_STORE + "\n");
                isLoad = true;
            } catch (IOException e) {
                System.out.println("Unable to read from file: " + JSON_STORE);
            }


        }

        return isLoad;
    }

    // EFFECT: saves pilot info to file
    private void savePilot() {
        try {
            jsonWriter.open();
            jsonWriter.write(pilot);
            jsonWriter.close();
            System.out.println("Saved " + pilot.getName() + "'s pilot info & progress to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // EFFECT: flight planner menu, choose & display next action based on user input
    private void menuOptions() {
        switch (quit) {
            case "BOOKING":
                flightBook();
                break;
            case "WX":
                flightWX();
                break;
            case "PREFLIGHT":
                flightPre();
                break;
            case "POSTFLIGHT":
                flightPost();
                break;
            default:
                System.out.println("That was not a valid option, please enter an option below:");
        }
    }

    // MODIFIES: this
    // EFFECT: create a flying booking for pilot, based on user input
    public void flightBook() {
        System.out.println("To book - 'BOOK'\n"
                + "To cancel existing booking - 'CANCEL'\n"
                + "To check all your bookings - 'CHECK'\n"
                + "To return to menu - 'MENU'");
        String choice1 = sc.next().toUpperCase();

        while (!choice1.equals("MENU")) {
            bookingOptions(choice1);
            System.out.println("To book - 'BOOK'\n"
                    + "To cancel existing booking - 'CANCEL'\n"
                    + "To check all your bookings - 'CHECK'\n"
                    + "To return to menu - 'MENU'");

            choice1 = sc.next().toUpperCase();
        }
    }

    // EFFECT: booking menu, choose & display next action based on user input
    private void bookingOptions(String choice1) {
        switch (choice1) {
            case "BOOK":
                bookGroundFlight();
                break;
            case "CANCEL":
                cancelBooking();
                break;
            case "CHECK":
                checkBookings();
            default:
        }
    }

    // MODIFIES: this
    // EFFECT: registers user, based on user input
    public void registerUser() {
        System.out.println("Welcome to Skybod Aviation! Let's get you set up:" + "\nEnter your full name");
        sc.nextLine();
        String name = sc.nextLine();
        pilot.setName(name);

        System.out.println("Hi " + pilot.getName() + ", what are your ratings? - VFR, IFR, Multi, Float, None");
        String choice = "add";
        ratingInputs(choice);

        System.out.println("Enter your TC medical number");
        int mednum = sc.nextInt();
        pilot.setMedNum(mednum);

        System.out.println("Are you a student? "
                + "Student - enter true\n"
                + "Otherwise - enter false");

        boolean isStud = sc.nextBoolean();
        pilot.setStudent(isStud);
    }

    private void printMenu() {
        System.out.println("Great! You are all set up!\n"
                + "To manage booking - 'BOOKING'\n"
                + "To check weather - 'WX'\n"
                + "To preflight - 'PREFLIGHT'\n"
                + "To postflight - 'POSTFLIGHT'\n"
                + "To quit - 'QUIT'");

        quit = sc.next().toUpperCase();
    }

    // MODIFIES: this
    // EFFECT: allows user to add ratings to their pilot profile
    private void ratingInputs(String choice) {
        int n = 2;

        while (choice.equalsIgnoreCase("add")) {
            // add check user does not type something twice
            choice = sc.next().toUpperCase();

            if (choice.equals("VFR") || choice.equals("IFR") || choice.equals("FLOAT")
                    || choice.equals("MULTI")) {

                if (!pilot.getRatings().contains(choice)) {
                    pilot.addRating(choice);
                    System.out.println("Type 'add' to add more ratings, any other key to continue");
                } else {
                    System.out.println("You've already added this rating,\n"
                            + "To add another type 'add', any other key to continue");
                    n--;
                }

                choice = sc.next();

                if (choice.equalsIgnoreCase("add")) {
                    System.out.println("Enter your " + n + "th rating");
                    n++;
                }
            } else if (!choice.equals("NONE")) {

                choice = "add";
                System.out.println("That's not a valid option, please enter - VFR, IFR, Multi, Float, None (to quit)");
            }
        }
    }

    // MODIFIES: this
    // EFFECT: allow user to check current, and last checked weather reports & forecasts
    public void flightWX() {
        Weather wxObject = pilot.getWx();

        System.out.println("To check most recent weather:\n\n"
                + "Terminal Area Forecast - TAF\n"
                + "Meteorological Terminal Air Report - METAR\n"
                + "Check last checked weather - LAST\n"
                + "To return to menu - MENU");

        String wxChoice = sc.next();

        while (!wxChoice.equalsIgnoreCase("MENU")) {
            wxOptions(wxChoice, wxObject);

            System.out.println("To check most recent weather:\n\n"
                    + "Terminal Area Forecast - TAF\n"
                    + "Meteorological Terminal Air Report - METAR\n"
                    + "Check last checked weather - LAST\n"
                    + "To return to menu - 'MENU");

            wxChoice = sc.next();
        }

        pilot.setWx(wxObject);
    }

    // EFFECT: weather checking menu, displays & executes next actions based on user input, returns wxObject
    private Weather wxOptions(String wxChoice, Weather wxObject) {
        switch (wxChoice.toUpperCase()) {
            case "TAF":
                checkTaf(wxObject);
                break;
            case "METAR":
                checkMetar(wxObject);
                break;
            case "LAST":
                lastChecked(wxObject);
                break;
            default:
                System.out.println("That's not a valid option, please try again\n");
        }

        return wxObject;

    }

    // MODIFIES: this
    // EFFECT: allow user to check most recent TAF (weather forecast)
    private void checkTaf(Weather wxObject) {
        System.out.println("Enter 4-character ICAO code for TAF at desired airport");

        String airportIdent = sc.next().toUpperCase();
        wxObject.tafUpdate(airportIdent);
        System.out.println("The latest TAF at " + airportIdent + " is:\n"
                + wxObject.getCurrentTaf() + "\n");

    }

    // MODIFIES: this
    // EFFECT: allow user to check last checked weather report & forecast
    private void lastChecked(Weather wxObject) {
        if (wxObject.getCurrentTaf().equals("") && wxObject.getCurrentMetar().equals("")) {
            System.out.println("No last checked TAF and METAR");
        } else if (wxObject.getCurrentTaf().equals("")) {
            System.out.println("The last checked METAR is:\n\n"
                    + wxObject.getCurrentMetar() + "\n\n"
                    + "No last checked TAF");
        } else if (wxObject.getCurrentMetar().equals("")) {
            System.out.println("The last checked TAF is:\n\n"
                    + wxObject.getCurrentTaf() + "\n\n"
                    + "No last checked METAR");
        } else {
            System.out.println("The last checked TAF & METAR (respectively):\n\n"
                    + wxObject.getCurrentTaf() + "\n\n"
                    + wxObject.getCurrentMetar() + "\n");
        }
    }

    // MODIFIES: this
    // EFFECT: allow user to check most recent METAR (weather report)
    private void checkMetar(Weather wxObject) {
        System.out.println("Enter 4-character ICAO code for METAR at desired airport");

        String airportIdent = sc.next().toUpperCase();
        wxObject.metarUpdate(airportIdent);
        System.out.println("The latest METAR at " + airportIdent + " is:\n"
                + wxObject.getCurrentMetar() + "\n");
    }

    // MODIFIES: this
    // EFFECT: displays preflight checklist based on user input
    public void flightPre() {
        List<Booking> allMyBookings = pilot.getBookings();
        Preflight preflight = new Preflight();
        System.out.println("All your current aeroplane bookings:");
        int n = 1;
        LinkedList<Booking> allFlightBookings = new LinkedList<>();
        for (Booking b : allMyBookings) {
            if (b.getTypeOfLesson().equals("FLIGHT")) {
                allFlightBookings.add(b);
                System.out.print(n + " - ");
                printBooking(b);
                System.out.println("");
                n++;
            }
        }

        if (n == 1) {
            System.out.println("You don't have any flight bookings");
        } else {
            prefChecklist(allFlightBookings, preflight);
        }
    }

    // MODIFIES: this, allMyBookings, preflight
    // EFFECT: updates bookings and preflight status
    private void prefChecklist(List<Booking> allFlightBookings, Preflight preflight) {
        Booking toPreflight = prefSelection(allFlightBookings);
        docCheck();
        insuranceCheck(preflight, toPreflight);
        walkaroundCheck(preflight);
        electricalCheck();
        engineOilCheck();
        WeightBalance wb = fuelCheck(preflight, toPreflight);
        fireExtingCheck(preflight);
        System.out.println("Your aircraft walk-around is now complete");
        preflight.setWalkAroundDone(true);
        wbCheck(preflight, toPreflight, wb);
        preTakeOffCheck(preflight, toPreflight);
        completePref(toPreflight);
    }

    // MODIFIES: toPreflight, this
    // EFFECT: adds preflighted booking to pilot record, informs user of preflight completion
    private void completePref(Booking toPreflight) {
        pilot.getToPostFlight().add(toPreflight);
        pilot.getBookings().remove(toPreflight);
        System.out.println("You've completed preflighting for " + toPreflight.getPlane().getCallSign() + "\n");
    }

    // MODIFIES: this, preflight, toPreflight
    // EFFECT: allow user to complete pre-takoff checks
    private void preTakeOffCheck(Preflight preflight, Booking toPreflight) {
        System.out.println("Enter current hobbs meter value");
        double hobbsStart = sc.nextDouble();
        preflight.setHobbsTimeStart(hobbsStart);
        toPreflight.setPref(preflight);

        System.out.println("Enter departing Airport 4-digit ICAO code");
        String depAP = sc.next().toUpperCase();
        preflight.setDepartAP(depAP);
    }

    // MODIFIES: this, preflight, toPreflight, wb
    // EFFECT: allow users to input & calculate weight and balance
    private void wbCheck(Preflight preflight, Booking toPreflight, WeightBalance wb) {

        boolean done = false;
        while (!done) {
            System.out.println("Let's complete your weight and balance, enter pilot weight in lb");
            double pilotWeight = sc.nextDouble();

            System.out.println("Enter passenger weight");
            double passengerWeight = sc.nextDouble();

            System.out.println("Enter cargo weight");
            double cargoWeight = sc.nextDouble();

            double takeoffWeight = toPreflight.getPlane().getPd().getWeightInfo()
                    + toPreflight.getPlane().getFuelAmount() * 6.0 + pilotWeight + passengerWeight + cargoWeight;
            printWeightCalc(toPreflight, pilotWeight, passengerWeight, cargoWeight, takeoffWeight);
            boolean weightWithinLimit = sc.nextBoolean();

            if (weightWithinLimit) {
                wbValid(preflight, toPreflight, wb, pilotWeight, passengerWeight, takeoffWeight);
                done = true;
            } else {
                System.out.println("Reduce cargo weight, redo weight & balance chart, or do not fly aircraft");
            }
        }
    }

    // MODIFIES: this, preflight, toPreflight, wb
    // EFFECT: save weight and balance calculations if valid
    private void wbValid(Preflight preflight, Booking toPreflight, WeightBalance wb, double pilotWeight,
                         double passengerWeight, double takeoffWeight) {
        wb.setAircraftWeight(toPreflight.getPlane().getPd().getWeightInfo());
        wb.setFuelWeight(toPreflight.getPlane().getFuelAmount() * 6.0);
        wb.setPassengerWeight(passengerWeight);
        wb.setPilotWeight(pilotWeight);
        wb.setTakeoffWeight(takeoffWeight);
        wb.setWithinLimit(true);
        preflight.setWb(wb);
        preflight.setWBDone(true);
        System.out.println("Your weight & balance calculations is now complete");
    }

    // MODIFIES: this, toPreflight
    // EFFECT: prints out & let user confirm weight & balance calculations
    private void printWeightCalc(Booking toPreflight, double pilotWeight, double passengerWeight,
                                 double cargoWeight, double takeoffWeight) {
        System.out.println("Aircraft Empty Weight - " + toPreflight.getPlane().getPd().getWeightInfo() + "lb\n"
                + "Fuel Weight (6.0lb/US Gallon) - " + toPreflight.getPlane().getFuelAmount() * 6.0 + "lb\n"
                + "Pilot Weight - " + pilotWeight + "lb\n"
                + "Passenger Weight - " + passengerWeight + "lb\n"
                + "Cargo Weight - " + cargoWeight + "lb\n"
                + "Total takeoff weight is " + takeoffWeight + "lb\n"
                + "Is this within takeoff limit according to " + toPreflight.getPlane().getCallSign() + "'s POH?\n"
                + "Yes - true\n"
                + "No - false");
    }

    // MODIFIES: this, preflight
    // EFFECT: allow user to confirm fire extinguisher check
    private void fireExtingCheck(Preflight preflight) {
        boolean completed;

        System.out.println("Is the aircraft's fire extinguisher charged & locked?\n"
                + "Yes - true\n"
                + "No - false");
        completed = sc.nextBoolean();

        while (!completed) {
            System.out.println("Replace fire extinguisher, or ensure it is locked.\n"
                    + "Is the fire extinguisher charged & locked now?\n"
                    + "Yes - true\n"
                    + "No - false");

            boolean functional = sc.nextBoolean();
            if (functional) {
                completed = true;
            }
        }

        preflight.setCheckedFireExt(true);
    }

    // MODIFIES: this, preflight, toPreflight
    // EFFECT: allow user to add fuel to aircraft
    private WeightBalance fuelCheck(Preflight preflight, Booking toPreflight) {
        boolean completed;

        printFuelOptions(toPreflight);

        completed = sc.nextBoolean();
        WeightBalance wb = new WeightBalance();

        if (completed) {
            printFuelingOptions(toPreflight);

            boolean isTopUp = sc.nextBoolean();

            if (isTopUp) {
                toPreflight.getPlane().setFuelToMaxFuel();
                System.out.println("Your aircraft now has " + toPreflight.getPlane().getFuelAmount()
                        + " Gallons of fuel");
                preflight.setFuelEnough(true);
                wb.setFuelGallons(toPreflight.getPlane().getFuelAmount());

            } else {
                notAddFuelToMax(preflight, toPreflight, wb);
            }

        } else {
            notAddFuel(preflight, toPreflight, wb);
        }
        return wb;
    }

    // MODIFIES: this, preflight, toPreflight, wb
    // EFFECT: option when user does not fill up aircraft fuel to max capacity
    private void notAddFuelToMax(Preflight preflight, Booking toPreflight, WeightBalance wb) {
        System.out.println("Enter amount of fuel you'd like to add");
        double fuelAmountAdd = sc.nextDouble();
        if (fuelAmountAdd + toPreflight.getPlane().getFuelAmount() < toPreflight.getPlane().getMaxFuel()
                && !(fuelAmountAdd == 0)) {
            addFuel(preflight, toPreflight, wb, fuelAmountAdd);

        } else if (fuelAmountAdd == 0) {
            System.out.println("You are not adding any fuel");

        } else {
            System.out.println("Amount of fuel you are trying to add exceeds the plane's fuel capacity");
        }
    }

    // MODIFIES: this, preflight toPreflight, wb
    // EFFECT: prints out option when user does not add fuel
    private void notAddFuel(Preflight preflight, Booking toPreflight, WeightBalance wb) {
        System.out.println("You are responsible for ensuring you have enough fuel for the duration of your "
                + "flight");
        preflight.setFuelEnough(true);
        wb.setFuelGallons(toPreflight.getPlane().getFuelAmount());
        System.out.println("Total possible flight time is " + (wb.getFuelGallons() / 9));
    }

    // MODIFIES: this, preflight toPreflight, wb
    // EFFECT: informs amount of fuel user added, based on user input
    private void addFuel(Preflight preflight, Booking toPreflight, WeightBalance wb, double fuelAmountAdd) {
        toPreflight.getPlane().addFuel(fuelAmountAdd);
        System.out.println(fuelAmountAdd + " Gallons of fuel has been added to "
                + toPreflight.getPlane().getCallSign() + ", current fuel amount is "
                + toPreflight.getPlane().getFuelAmount());
        preflight.setFuelEnough(true);
        wb.setFuelGallons(toPreflight.getPlane().getFuelAmount());
        System.out.println("Total possible flight time is " + (wb.getFuelGallons() / 9));
    }

    // MODIFIES: this, toPreflight
    // EFFECT: prints out add fuel options
    private void printFuelingOptions(Booking toPreflight) {
        System.out.println("Your aircraft " + toPreflight.getPlane().getCallSign()
                + "'s fuel capacity is " + toPreflight.getPlane().getMaxFuel()
                + " Gallons of fuel, would you like to top up, or add specific amount of fuel?\n"
                + "To top up to max - true\n"
                + "To add specific amount of fuel - false");
    }

    // MODIFIES: this, toPreflight
    // EFFECT: prints out add fuel options
    private void printFuelOptions(Booking toPreflight) {
        System.out.println("Your aircraft currently has " + toPreflight.getPlane().getFuelAmount()
                + "US Gallons of fuel, would you like to refuel " + toPreflight.getPlane().getType() + "?\n"
                + "To refuel - true\n"
                + "To continue with current fuel amount - false");
    }

    // MODIFIES: this
    // EFFECT: prints out engine oil checklist
    private void engineOilCheck() {
        boolean completed;
        System.out.println("Does the aircraft have safe amount of engine oil?\n"
                + "Yes - true\n"
                + "No - false");
        completed = sc.nextBoolean();
        while (!completed) {
            System.out.println("Add engine oil.\n"
                    + "Is the engine oil enough now?\n"
                    + "Yes, - true\n"
                    + "No, - false");

            boolean functional = sc.nextBoolean();
            if (functional) {
                completed = true;
            }
        }
    }

    // MODIFIES: this
    // EFFECT: prints out electrical check, intake user confirmation
    private void electricalCheck() {
        boolean completed;

        System.out.println("Is the aircraft electrical system functional?\n"
                + "Yes - true\n"
                + "No - false");
        completed = sc.nextBoolean();
        while (!completed) {
            System.out.println("Report to maintenance.\n"
                    + "Is the electrical system functional now?\n"
                    + "Yes, functional - true\n"
                    + "No, not functional still - false");

            boolean functional = sc.nextBoolean();
            if (functional) {
                completed = true;
            }
        }
    }

    // MODIFIES: this, preflight
    // EFFECT: prints out walkaround checks & intake user confirmation
    private void walkaroundCheck(Preflight preflight) {
        boolean completed;

        preflight.setInsuranceValid(true);

        System.out.println("Complete your aircraft walk-around checks, enter true or false for the following");
        System.out.println("Are all airfoils functional (fully extendable)?\n"
                + "Yes - true\n"
                + "No - false");

        completed = sc.nextBoolean();
        while (!completed) {
            System.out.println("Report to maintenance.\n"
                    + "Are all the airfoils functional now?\n"
                    + "Yes, functional - true\n"
                    + "No, not functional still - false");

            boolean functional = sc.nextBoolean();
            if (functional) {
                completed = true;
            }
        }
    }

    // MODIFIES: this, preflight, toPreflight
    // EFFECT: prints out insurance check, updates once complete
    private void insuranceCheck(Preflight preflight, Booking toPreflight) {
        boolean completed;
        preflight.setDocOnBoard(true);
        System.out.println("Aircraft insurance is valid from "
                + toPreflight.getPlane().getPd().getInsurance().getDateValid()
                + " to "
                + toPreflight.getPlane().getPd().getInsurance().getDateValidUntil()
                + ", is it CURRENTLY valid?\n"
                + "Yes, valid - true\n"
                + "No, invalid - false");

        completed = sc.nextBoolean();
        while (!completed) {
            System.out.println("Purchase insurance to continue.\n"
                    + "Has insurance been repurchased?"
                    + "Yes, repurchased - true\n"
                    + "No, still invalid - false");

            boolean repurchaseIns = sc.nextBoolean();
            if (repurchaseIns) {
                completed = true;
            }
        }
    }

    // MODIFIES: this
    // EFFECT: prints out document checks & intake user confirmation
    private void docCheck() {
        boolean completed = false;
        while (!completed) {
            System.out.println("Are all the following documents on board?\n"
                    + "- Aircraft registration\n"
                    + "- Aircraft insurance\n"
                    + "- Weight & balance of aircraft\n"
                    + "- Aircraft journey log\n"
                    + "- Interception procedure\n"
                    + "Yes - true\n"
                    + "No - false");

            completed = sc.nextBoolean();
            if (!completed) {
                System.out.println("Complete document check\n");
            }
        }
    }

    // MODIFIES: this, allMyBookings
    // EFFECT: allow user to select booking to preflight, prints out confirmation once completed
    private Booking prefSelection(List<Booking> allFlightBookings) {
        System.out.println("Enter the corresponding number for the booking you'd like to preflight");
        int bookingNum = sc.nextInt();

        Booking toPreflight = allFlightBookings.get(bookingNum - 1);
        System.out.print("You are preflighting for ");
        printBooking(toPreflight);

        System.out.println("Complete your aircraft document checks, enter true or false for following actions");
        return toPreflight;
    }

    // MODIFIES: this
    // EFFECT: post flight calculations
    public void flightPost() {
        List<Booking> allToPostflight = pilot.getToPostFlight();
        if (allToPostflight.size() == 0) {
            noPostflight();
        } else {
            getAllPref(allToPostflight);
            Booking toPostflight = postInitialize(allToPostflight);
            planeTiedDownCheck(toPostflight);

            boolean fuelMakesSense = false;
            while (!fuelMakesSense) {
                double endHobbsTime = enterEndHobbsTime();
                double flightTime = endHobbsTime - toPostflight.getPref().getHobbsTimeStart();
                double fuelUse = 9 * flightTime;
                double beforeFlightFuel = toPostflight.getPlane().getFuelAmount();
                double fuelLeft = beforeFlightFuel - fuelUse;

                fuelMakesSense = checkEndHobbsTimeValid(toPostflight, fuelMakesSense,
                        endHobbsTime, flightTime, fuelUse, fuelLeft);
            }
        }
    }

    // EFFECT: check if total ending hobbs time is not negative, does not exceed flight time possible, then save info
    // into booking
    private boolean checkEndHobbsTimeValid(Booking toPostflight, boolean fuelMakesSense, double endHobbsTime,
                                           double flightTime, double fuelUse, double fuelLeft) {
        if (flightTime < 0) {
            negHobbsTime();
        } else if (fuelLeft < 0) {
            negFlightTime(toPostflight);
        } else {
            fuelMakesSense = correctHobbsTime(toPostflight, flightTime, fuelUse, fuelLeft);
            lastPostProcedures(toPostflight, endHobbsTime, flightTime);
        }
        return fuelMakesSense;
    }

    // MODIFIES: this
    // EFFECT: allow user to input ending hobbs time
    private double enterEndHobbsTime() {
        System.out.println("Enter your ending hobbs time");
        double endHobbsTime = sc.nextDouble();
        return endHobbsTime;
    }

    // EFFECT: prints warning if no flight to postflight
    private void noPostflight() {
        System.out.println("You have no bookings to postflight, please complete preflight first for flying bookings");
    }

    // MODIFIES: this, toPostflight
    // EFFECT:
    private void lastPostProcedures(Booking toPostflight,
                                    double endHobbsTime, double flightTime) {
        PlaneFlightLog fl = untilMaintenance(toPostflight, flightTime);
        completePost(toPostflight, endHobbsTime, flightTime, fl);
        System.out.println("You've completed postflighting for " + toPostflight.getPlane().getCallSign());
    }

    // MODIFIES: this, allPostflight
    // EFFECT: displays all preflighted bookings
    private void getAllPref(List<Booking> allToPostflight) {
        System.out.println("All your preflighted bookings:");
        int n = 1;
        for (Booking b : allToPostflight) {
            System.out.print(n + " - ");
            printBooking(b);
            System.out.println("");
            n++;
        }
    }

    // MODIFIES: this, toPostflight, fl
    // EFFECT: receive input from user, updates fl, postflight, and toPosflight
    private void completePost(Booking toPostflight,
                              double endHobbsTime, double flightTime, PlaneFlightLog fl) {
        System.out.println("Enter arrival airport 4-digit ICAO code");
        String arrAP = sc.next().toUpperCase();

        fl.setHobbsTimeStart(toPostflight.getPref().getHobbsTimeStart());
        fl.setHobbsTimeEnd(endHobbsTime);
        fl.setDepartingAP(toPostflight.getPref().getDepartAP());
        fl.setArrivingAP(arrAP);

        System.out.println("Enter type of piloting completed- dual, solo");
        String typeOfPiloting = sc.next();

        PilotLog pilotLog = new PilotLog();
        pilotLog.setFlightTime(flightTime);
        pilotLog.setTime(toPostflight.getTimeBooked());
        pilotLog.setTypeOfPiloting(typeOfPiloting);
        pilotLog.setDay(toPostflight.getDayBooked());
        pilotLog.setPlaneType(toPostflight.getPlane().getType());
        pilotLog.setPlaneCallSign(toPostflight.getPlane().getCallSign());

        toPostflight.getPlane().getPd().getFl().add(fl);
        pilot.getCompletedBookings().add(toPostflight);
        pilot.getPl().add(pilotLog);
        pilot.getToPostFlight().remove(toPostflight);
    }

    // MODIFIES: toPostflight, this
    // EFFECT: calculate maintenance time
    private PlaneFlightLog untilMaintenance(Booking toPostflight, double flightTime) {
        double newHourTillMaint = toPostflight.getPlane().getPd().getFl().getFirst().getHoursTillMaint()
                - flightTime;
        PlaneFlightLog fl = new PlaneFlightLog();

        if (newHourTillMaint > 0) {
            System.out.println("Hour until aircraft maintenance: " + newHourTillMaint);
            fl.setHoursTillMaint(newHourTillMaint);
        } else {
            System.out.println("Aircraft is due for maintenance!");
            fl.setHoursTillMaint(100);
        }
        return fl;
    }

    // MODIFIES: this, toPostflight
    // EFFECT: confirms correct user input of flight time
    private boolean correctHobbsTime(Booking toPostflight, double flightTime, double fuelUse, double fuelLeft) {
        boolean fuelMakesSense;
        System.out.println("“Your total flight time is " + flightTime + "\n"
                + "your total fuel use is " + fuelUse + " gallons");
        toPostflight.getPlane().setFuelAmount(fuelLeft);
        fuelMakesSense = true;
        return fuelMakesSense;
    }

    // EFFECT: error message if user input ending hobbs time exceeding flight time
    private void negFlightTime(Booking toPostflight) {
        double flightTimeMax = toPostflight.getPref().getWb().getFuelGallons() / 9;
        System.out.println("Your maximum flight time is " + flightTimeMax
                + ", as noted, please enter the correct ending hobbs time");
    }

    // EFFECT: error message if user input ending hobbs time < starting hobbs time
    private void negHobbsTime() {
        System.out.println("Your total hobbs time is negative, please enter the correct ending hobbs time");
    }

    // MODIFIES: this, toPostflight
    // EFFECT: confirm with user that aircraft is tied down
    private void planeTiedDownCheck(Booking toPostflight) {
        System.out.println("Is " + toPostflight.getPlane().getCallSign() + " secured (tied down) after flight?\n"
                + "yes - true\n"
                + "no - false");
        boolean planeTiedDown = sc.nextBoolean();

        while (!planeTiedDown) {
            System.out.println("Tie down your aircraft!\n" + "is the aircraft tied down now?\n"
                    + "yes - true\n"
                    + "no - false");

            boolean confirm = sc.nextBoolean();

            if (confirm) {
                planeTiedDown = true;
            }
        }
    }

    // MODIFIES: this, allToFlight
    // EFFECT: initialize postflight checklist, based on user input
    private Booking postInitialize(List<Booking> allToPostflight) {
        System.out.println("Enter the corresponding number for booking to postflight");
        int bookingNum = sc.nextInt();

        Booking toPostflight = allToPostflight.get(bookingNum - 1);
        System.out.print("You are postflighting for ");
        printBooking(toPostflight);
        return toPostflight;
    }

    // EFFECT: prints out menu for booking flights, proceed based on user input
    public void bookGroundFlight() {
        System.out.println("To book ground session - 'GROUND\n"
                + "To book flight lesson - 'FLIGHT'\n"
                + "To return to previous options - 'PREV' ");

        choice = sc.next().toUpperCase();

        while (!choice.equals("PREV")) {
            switch (choice) {
                case "FLIGHT":
                    bookFlight();
                    break;
                case "GROUND":
                    bookGround();
                    break;
                default:
                    System.out.print("That's not a valid option, try again:");
            }

            System.out.println("\nTo book ground session - 'GROUND'\n"
                    + "To book flight lesson - 'FLIGHT'\n"
                    + "return to previous options - 'PREV'");

            choice = sc.next().toUpperCase();
        }
    }


    // MODIFIES: this
    // EFFECT: allow user to book aircraft, and instructor if applicable
    public void bookFlight() {
        booking = new Booking();
        selectPlaneType();
        System.out.println(booking.getPlane().getType() + " " + booking.getPlane().getCallSign()
                + "'s availability on chosen day:");
        printDayAvail(choice, booking.getPlane().getAvails());
        ArrayList<String> dayAvail = booking.getPlane().getAvails().findDay(choice);

        System.out.println("\nTo book a time - enter the hour");

        String c = sc.next();
        if (pilot.getStudentStatus()) {
            String insName = selectInstructor();
            boolean isInsBooked = false;
            boolean isInstrFound = false;
            isInstrFound = toBookInstr(c, insName, isInsBooked, isInstrFound);
            instrNotFound(isInstrFound);
        }

        boolean successBookTime = false;
        for (String time : dayAvail) {
            if (time.equals(c)) {
                successBookTime = isSuccessBookTime(dayAvail, c);
            }
        }

        bookingAtDayTime(c, successBookTime);
        addTheBooking(booking);
    }

    // MODIFIES: pilot, this
    // EFFECT: adds booking to pilot documentation
    private void addTheBooking(Booking booking) {
        booking.setTypeOfLesson("FLIGHT");
        pilot.addBooking(booking);
    }

    // EFFECT: instructor not found error printout
    private void instrNotFound(boolean isInstrFound) {
        if (!isInstrFound) {
            System.out.println("Instructor not found");
        }
    }

    // MODIFIES: this
    // EFFECT: book selected instructor
    private boolean toBookInstr(String c, String insName, boolean isInsBooked, boolean isInstrFound) {
        for (Instructor i : pilot.getLoi()) {
            if (i.getName().equalsIgnoreCase(insName)) {
                ArrayList<String> insAvailOnDay = i.getAvails().findDay(choice);
                isInstrFound = true;

                for (String time : insAvailOnDay) {
                    if (time.equals(c)) {
                        isInsBooked = isInsBooked(c, insName, i, insAvailOnDay);
                    }
                }

                if (!isInsBooked) {
                    System.out.println(insName + " is not available at " + c + ", please select a different"
                            + " instructor or rebook");
                }
            }
        }
        return isInstrFound;
    }

    // EFFECT: check if instructor has been booked
    private boolean isInsBooked(String c, String insName, Instructor i, ArrayList<String> insAvailOnDay) {
        System.out.println(insName + " has been booked at " + c + " for flying lesson, ");
        booking.setInstructor(i);
        ArrayList<String> notInfForloop = (ArrayList<String>) insAvailOnDay.clone();
        notInfForloop.remove(c);
        booking.getInstructor().getAvails().setDay(choice, notInfForloop);
        return true;
    }

    // EFFECT: menu for instructor selection
    private String selectInstructor() {
        System.out.println("Enter your instructor's name:\n"
                + "James Gordon\n"
                + "Nelly Chou\n"
                + "Toren Molly\n"
                + "Ash Salem\n"
                + "Zor Lee");

        sc.nextLine();
        String insName = sc.nextLine();
        return insName;
    }

    // EFFECT: prints plane booking if successful, otherwise notifies user of failure
    private void bookingAtDayTime(String c, boolean successBookTime) {
        if (successBookTime) {
            System.out.println("you've booked " + booking.getPlane().getCallSign() + " on " + choice + " at " + c);
        } else {
            System.out.println("The aircraft is unavailable at the time you've selected, please try again");
        }
    }

    // MODIFIES: this, dayAvail
    // EFFECT: saves booking time & updates days plane is available
    private boolean isSuccessBookTime(ArrayList<String> dayAvail, String c) {
        ArrayList<String> notInfForloop = (ArrayList<String>) dayAvail.clone();
        notInfForloop.remove(c);
        booking.getPlane().getAvails().setDay(choice, notInfForloop);
        booking.setDayBooked(choice.toUpperCase());
        booking.setTimeBooked(c);
        return true;
    }

    // MODIFIES: this
    // EFFECT: allow user to book aircraft
    private void selectPlaneType() {
        boolean isPlaneAvail = false;
        while (!isPlaneAvail) {
            System.out.println("Select your airplane type:\n"
                    + "Piper-Seneca\n"
                    + "Cessna-152\n"
                    + "Diamond-DA40\n"
                    + "Cirrus-SR22T\n"
                    + "Cessna-172");

            String type = sc.next();
            for (Plane p : pilot.getLop()) {
                if (p.getType().equalsIgnoreCase(type)) {
                    booking.setPlane(p);
                }
            }

            if (!booking.getPlane().equals(null)) {
                System.out.println("Enter day which you'd like to make your booking on:\n"
                        + "Monday - Friday");
                isPlaneAvail = true;

            } else {
                System.out.println("The plane type you requested is not available, enter another plane type");
            }
        }

        choice = sc.next();
    }

    // MODIFIES: this
    // EFFECT: allow user to instructors
    public void bookGround() {
        booking = new Booking();
        String instrName = selectInstructor();
        for (Instructor i : pilot.getLoi()) {
            if (i.getName().equalsIgnoreCase(instrName)) {
                booking.setInstructor(i);
            }
        }

        printNextStep();

        choice = sc.next();
        System.out.println(booking.getInstructor().getName() + "'s availability on " + choice + ":");
        printDayAvail(choice, booking.getInstructor().getAvails());
        ArrayList<String> dayAvail = booking.getInstructor().getAvails().findDay(choice);

        System.out.println("\nTo book a time - enter the hour");

        String c = sc.next();
        boolean successBookTime = false;

        for (String time : dayAvail) {
            if (time.equals(c)) {
                successBookTime = isBookTime(dayAvail, c);
            }
        }
        bookedOrNot(c, successBookTime);
    }

    // MODIFIES: this
    // EFFECT: checks if instructor is booked
    private void bookedOrNot(String c, boolean successBookTime) {
        if (successBookTime) {
            System.out.println("You've booked " + booking.getInstructor().getName()
                    + " on " + choice + " at " + c);
            booking.setTypeOfLesson("GROUND");
            pilot.addBooking(booking);
        } else {
            System.out.println("The time you've selected is unavailable, please try again");
        }
    }

    // MODIFIES: this, dayAvail
    // EFFECT: updates booking and available days of instructor
    private boolean isBookTime(ArrayList<String> dayAvail, String c) {
        ArrayList<String> notInfForloop = (ArrayList<String>) dayAvail.clone();
        notInfForloop.remove(c);
        booking.getInstructor().getAvails().setDay(choice, notInfForloop);
        booking.setDayBooked(choice.toUpperCase());
        booking.setTimeBooked(c);
        return true;
    }

    // MODIFIES: this
    // EFFECT: print booking days selection
    private void printNextStep() {
        if (!booking.getInstructor().equals(null)) {
            System.out.println("Enter day which you'd like to make your booking on:\n"
                    + "Monday - Friday");
        } else {
            System.out.println("The instructor you requested is not a member of this school");
        }
    }

    // MODIFIES: this
    // EFFECT: allows user to cancel bookings
    public void cancelBooking() {
        LinkedList<Booking> myBooking = pilot.getBookings();

        if (myBooking.size() == 0) {
            System.out.println("You have no bookings");
        } else {
            System.out.println("Enter the day of the booking for cancellation:");
            String day = sc.next();
            ArrayList<Booking> bookingsOnDay = new ArrayList<>();

            for (Booking b : myBooking) {
                if (b.getDayBooked().equalsIgnoreCase(day)) {
                    bookingsOnDay.add(b);
                }
            }

            if (bookingsOnDay.size() == 0) {
                System.out.println("You have no bookings on " + day);
            } else {
                cancelBookingAtTime(day, bookingsOnDay);
            }

        }
    }

    // MODIFIES: this, bookingsOnDay
    // EFFECT: allow user to cancel book at user inputted time
    private void cancelBookingAtTime(String day, ArrayList<Booking> bookingsOnDay) {
        printBookingsOnDay(day, bookingsOnDay);

        System.out.println("Enter the time of the booking you'd like to cancel");
        String time = sc.next();

        Booking cancelBooking = getBooking(bookingsOnDay, time);

        if (!(cancelBooking.getDayBooked().equals(""))) {
            printCancelReason();
            String reasonCancel = sc.next();
            String cancelDay = cancelBooking.getDayBooked();
            String cancelTime = cancelBooking.getTimeBooked();
            if (!(cancelBooking.getPlane().getType().equals(""))) {
                cancelBooking.getPlane().getAvails().addBackTimeGivenDay(cancelDay, cancelTime);
            } else if (!(cancelBooking.getInstructor().getName().equals(""))) {
                cancelBooking.getInstructor().getAvails().addBackTimeGivenDay(cancelDay, cancelTime);
            }
            cancelThisBooking(cancelBooking, reasonCancel);

        } else {
            System.out.println("There's no booking at " + time + " on " + day);
        }
    }

    // MODIFIES: this, cancelBooking
    // EFFECT: cancels booking at user specified time
    private void cancelThisBooking(Booking cancelBooking, String reasonCancel) {
        cancelBooking.setReasonCancelled(reasonCancel);
        pilot.getBookings().remove(cancelBooking);
        System.out.println("Your booking");
        printBooking(cancelBooking);
        System.out.println("has been cancelled\n");
        pilot.getCancelled().add(cancelBooking);
    }

    // MODIFIES: this
    // EFFECT: prints cancellation reasons
    private void printCancelReason() {
        System.out.println("Enter reason for cancellation:\n"
                + "Due to weather - wx\n"
                + "Due to sickness - sick\n"
                + "Due to unexpected event - event");
    }

    // MODIFIES: this, bookingsOnDay
    // EFFECT: get booking to cancel
    private Booking getBooking(ArrayList<Booking> bookingsOnDay, String time) {
        Booking cancelBooking = null;
        for (Booking b : bookingsOnDay) {
            if (b.getTimeBooked().equalsIgnoreCase(time)) {
                cancelBooking = b;
            }
        }
        return cancelBooking;
    }

    // MODIFIES: this, bookingsOnDay
    // EFFECT: get booking to cancel on day
    private void printBookingsOnDay(String day, ArrayList<Booking> bookingsOnDay) {
        System.out.println("Your bookings on " + day + " are:");
        for (Booking b : bookingsOnDay) {
            printBooking(b);
            System.out.println("");
        }
    }

    // MODIFIES: this
    // EFFECT: checks if user has bookings
    public void checkBookings() {
        List<Booking> myBookings = pilot.getBookings();

        if (myBookings.size() == 0) {
            System.out.println("You have no bookings");
        } else {
            System.out.println("All your current bookings are:");

            for (Booking b : myBookings) {
                printBooking(b);
                System.out.println(" on " + b.getDayBooked());
            }

            System.out.println(" ");
        }
    }

    // EFFECT: print all user's bookings
    public void printBooking(Booking b) {
        if (b.getPlane().getType().equals("")) {
            System.out.print(b.getTypeOfLesson() + " lesson at " + b.getTimeBooked() + " with "
                    + b.getInstructor().getName());
        } else if (b.getInstructor().getName().equals("")) {
            System.out.print("AIRCRAFT at " + b.getTimeBooked() + " for " + b.getPlane().getType() + " "
                    + b.getPlane().getCallSign());
        } else {
            System.out.print(b.getTypeOfLesson() + " lesson at " + b.getTimeBooked() + " on " + b.getPlane().getType()
                    + " "
                    + b.getPlane().getCallSign() + " with " + b.getInstructor().getName());
        }
    }

    // REQUIRES: d must be a day name: monday - sunday.
    // EFFECT: prints out availability on given day
    public void printDayAvail(String d, DayTime avail) {
        System.out.print(d.substring(0, 1).toUpperCase() + d.substring(1).toLowerCase() + ": ");
        ArrayList<String> givenDay = avail.findDay(d);
        for (String t : givenDay) {
            System.out.print(t + "   ");
        }
    }

    // MODIFIES: this
    // EFFECT: initialize 5 available planes
    public void initializePlaneInstr() {
        Plane cessna172 = new Plane();
        Plane cessna152 = new Plane();
        Plane piper = new Plane();
        Plane cirrus = new Plane();
        Plane diamond = new Plane();

        DayTime dt = availabilityAll();
        initialize172(cessna172, dt);
        initialize152(cessna152, dt);
        initializePiper(piper, dt);
        initializeCirrus(cirrus, dt);
        initializeDiamond(diamond, dt);

        ArrayList<Plane> lop = new ArrayList<>();
        lop.add(cessna152);
        lop.add(cessna172);
        lop.add(piper);
        lop.add(cirrus);
        lop.add(diamond);

        pilot.setLop(lop);

        initializeInstructor(dt);
    }

    // MODIFIES: this
    // EFFECT: initialize diamond-DA40 aircraft
    private void initializeDiamond(Plane diamond, DayTime allDayTime) {
        diamond.setType("Diamond-DA40");
        diamond.setCallSign("C-POYL");
        diamond.setAvails(allDayTime);
        diamond.setHourlyRentalRate(200);
        diamond.setHourlyFuelRate(45);

        Insurance diamondins = initializeInsDiamond();

        PlaneFlightLog diamondfl = new PlaneFlightLog();
        diamondfl.setArrivingAP("CYUL");
        diamondfl.setDepartingAP("CYVR");
        diamondfl.setHobbsTimeStart(0.3);
        diamondfl.setHobbsTimeEnd(11.1);
        diamondfl.setHoursTillMaint(203.6);

        LinkedList<PlaneFlightLog> logdiamond = new LinkedList<>();
        logdiamond.add(diamondfl);

        PlaneDocuments docdiamond = initializePDDiamond(diamondins, logdiamond);

        diamond.setPd(docdiamond);
        diamond.setFuelAmount(10.4);
        diamond.setMaxFuel(24.0);
    }

    // MODIFIES: this, diamondins, logdiamond
    // EFFECT: initialize diamond-DA40 plane docs
    private PlaneDocuments initializePDDiamond(Insurance diamondins, LinkedList<PlaneFlightLog> logdiamond) {
        PlaneDocuments docdiamond = new PlaneDocuments();
        docdiamond.setFl(logdiamond);
        docdiamond.setWeightInfo(1058.25);
        docdiamond.setInsurance(diamondins);
        return docdiamond;
    }

    // MODIFIES: this
    // EFFECT: initialize diamond-DA40's insurance'
    private Insurance initializeInsDiamond() {
        Insurance diamondins = new Insurance();
        diamondins.setDateValid("03/10/2022");
        diamondins.setDateValidUntil("03/10/2023");
        diamondins.setAmountInsured(7000000);
        diamondins.setTypeOfInsurance("Liability, Hull");
        return diamondins;
    }


    // MODIFIES: this
    // EFFECT: initialize cirrus aircraft
    private void initializeCirrus(Plane cirrus, DayTime allDayTime) {
        cirrus.setType("Cirrus-SR22T");
        cirrus.setCallSign("C-CIRR");
        cirrus.setAvails(allDayTime);
        cirrus.setHourlyFuelRate(51);
        cirrus.setHourlyRentalRate(212);
        Insurance cirrusins = new Insurance();
        cirrusins.setDateValid("08/03/2022");
        cirrusins.setDateValidUntil("08/03/2023");
        cirrusins.setAmountInsured(7000000);
        cirrusins.setTypeOfInsurance("Liability, Hull");

        PlaneFlightLog cirrusfl = initializePFLcirrus();

        LinkedList<PlaneFlightLog> logcirrus = new LinkedList<>();
        logcirrus.add(cirrusfl);

        PlaneDocuments doccirrus = new PlaneDocuments();
        doccirrus.setFl(logcirrus);
        doccirrus.setWeightInfo(1441.92);
        doccirrus.setInsurance(cirrusins);

        cirrus.setPd(doccirrus);
        cirrus.setFuelAmount(40.1);
        cirrus.setMaxFuel(92.0);
    }

    // MODIFIES: this
    // EFFECT: initialize cirrus flight logs
    private PlaneFlightLog initializePFLcirrus() {
        PlaneFlightLog cirrusfl = new PlaneFlightLog();
        cirrusfl.setArrivingAP("CYZZ");
        cirrusfl.setDepartingAP("CYVR");
        cirrusfl.setHobbsTimeStart(77.1);
        cirrusfl.setHobbsTimeEnd(78.2);
        cirrusfl.setHoursTillMaint(29.4);
        return cirrusfl;
    }

    // MODIFIES: this, allDayTime
    // EFFECT: initialize everyone's availability, returns it
    private DayTime availabilityAll() {
        DayTime allDayTime = new DayTime();
        allDayTime.addGivenDayTime("Monday", "0000", "2400");
        allDayTime.addGivenDayTime("Tuesday", "0000", "2400");
        allDayTime.addGivenDayTime("Wednesday", "0000", "2400");
        allDayTime.addGivenDayTime("Thursday", "0000", "2400");
        allDayTime.addGivenDayTime("Friday", "0000", "2400");
        allDayTime.addGivenDayTime("Saturday", "0000", "2400");
        allDayTime.addGivenDayTime("Sunday", "0000", "2400");

        return allDayTime;
    }

    // MODIFIES: this
    // EFFECT: initialize piper aircraft
    private void initializePiper(Plane piper, DayTime allDayTime) {
        piper.setType("Piper-Seneca");
        piper.setCallSign("C-FOTX");
        piper.setAvails(allDayTime);
        piper.setHourlyRentalRate(215);
        piper.setHourlyFuelRate(50);

        Insurance piperins = initializeInsPiper();

        PlaneFlightLog piperfl = initializeFLPiper();

        LinkedList<PlaneFlightLog> logspiper = new LinkedList<>();
        logspiper.add(piperfl);

        PlaneDocuments docpiper = new PlaneDocuments();
        docpiper.setFl(logspiper);
        docpiper.setWeightInfo(1343.93);
        docpiper.setInsurance(piperins);

        piper.setPd(docpiper);
        piper.setMaxFuel(98.0);
        piper.setFuelAmount(39.3);
    }

    // MODIFIES: this
    // EFFECT: initialize piper flight logs
    private PlaneFlightLog initializeFLPiper() {
        PlaneFlightLog piperfl = new PlaneFlightLog();
        piperfl.setArrivingAP("CYVR");
        piperfl.setDepartingAP("CYZY");
        piperfl.setHobbsTimeStart(45.4);
        piperfl.setHobbsTimeEnd(46.0);
        piperfl.setHoursTillMaint(104.8);
        return piperfl;
    }

    // MODIFIES: this
    // EFFECT: initialize piper insurance
    private Insurance initializeInsPiper() {
        Insurance piperins = new Insurance();
        piperins.setDateValid("07/27/2022");
        piperins.setDateValidUntil("07/27/2023");
        piperins.setAmountInsured(4000000);
        piperins.setTypeOfInsurance("Liability, Hull");
        return piperins;
    }


    // MODIFIES: this
    // EFFECT: initialize cessna152 aircraft
    private void initialize152(Plane cessna152, DayTime allDayTime) {
        cessna152.setType("Cessna-152");
        cessna152.setCallSign("C-GUUY");
        cessna152.setAvails(allDayTime);
        cessna152.setHourlyRentalRate(175);
        cessna152.setHourlyFuelRate(36);

        Insurance c152ins = initializeIns152();

        PlaneFlightLog c152fl = initializePFL();

        LinkedList<PlaneFlightLog> logs152 = new LinkedList<>();
        logs152.add(c152fl);

        PlaneDocuments doc152 = new PlaneDocuments();
        doc152.setFl(logs152);
        doc152.setWeightInfo(1138.26);
        doc152.setInsurance(c152ins);

        cessna152.setPd(doc152);
        cessna152.setFuelAmount(13.5);
        cessna152.setMaxFuel(26.0);
    }

    // MODIFIES: this
    // EFFECT: initialize cessna152 flight log
    private PlaneFlightLog initializePFL() {
        PlaneFlightLog c152fl = new PlaneFlightLog();
        c152fl.setArrivingAP("CYXX");
        c152fl.setDepartingAP("CYVR");
        c152fl.setHobbsTimeStart(95.6);
        c152fl.setHobbsTimeEnd(97.0);
        c152fl.setHoursTillMaint(3.0);
        return c152fl;
    }

    // MODIFIES: this
    // EFFECT: initialize cessna152's insurance
    private Insurance initializeIns152() {
        Insurance c152ins = new Insurance();
        c152ins.setDateValid("11/30/2022");
        c152ins.setDateValidUntil("11/30/2023");
        c152ins.setAmountInsured(5000000);
        c152ins.setTypeOfInsurance("Liability, Hull");
        return c152ins;
    }


    // MODIFIES: this
    // EFFECT: initialize cessna172 aircraft
    private void initialize172(Plane cessna172, DayTime allDayTime) {
        cessna172.setType("Cessna-172");
        cessna172.setCallSign("C-GOOV");
        cessna172.setAvails(allDayTime);
        cessna172.setHourlyRentalRate(190);
        cessna172.setHourlyFuelRate(40);
        Insurance c172ins = initializeInsurance172();
        PlaneFlightLog c172fl = initializePlaneLog172();

        LinkedList<PlaneFlightLog> logs172 = new LinkedList<>();
        logs172.add(c172fl);

        PlaneDocuments doc172 = initializePd172(c172ins, logs172);

        cessna172.setPd(doc172);
        cessna172.setFuelAmount(25.5);
        cessna172.setMaxFuel(48.0);
    }

    // MODIFIES: this, c172ins, logs172
    // EFFECT: initialize cessna172 plane documents
    private PlaneDocuments initializePd172(Insurance c172ins, LinkedList<PlaneFlightLog> logs172) {
        PlaneDocuments doc172 = new PlaneDocuments();
        doc172.setFl(logs172);
        doc172.setWeightInfo(1209.13);
        doc172.setInsurance(c172ins);
        return doc172;
    }

    // MODIFIES: this
    // EFFECT: initialize cessna172 flight logs
    private PlaneFlightLog initializePlaneLog172() {
        PlaneFlightLog c172fl = new PlaneFlightLog();
        c172fl.setArrivingAP("CZBB");
        c172fl.setDepartingAP("CYVR");
        c172fl.setHobbsTimeStart(29.8);
        c172fl.setHobbsTimeEnd(30.1);
        c172fl.setHoursTillMaint(69.9);
        return c172fl;
    }

    // MODIFIES: this
    // EFFECT: initialize cessna172 insurance
    private Insurance initializeInsurance172() {
        Insurance c172ins = new Insurance();
        c172ins.setDateValid("05/22/2022");
        c172ins.setDateValidUntil("05/22/2023");
        c172ins.setAmountInsured(3000000);
        c172ins.setTypeOfInsurance("Liability, Hull");
        return c172ins;
    }


    // MODIFIES: this
    // EFFECT: flight club's available instructors
    public void initializeInstructor(DayTime allDayTime) {
        Instructor james = initializeJames(allDayTime);
        Instructor nelly = initializeNelly(allDayTime);
        Instructor toren = initializeToren(allDayTime);
        Instructor ash = initializeAsh(allDayTime);
        Instructor zor = initializeZor(allDayTime);

        ArrayList<Instructor> loi = new ArrayList<>();
        loi.add(james);
        loi.add(toren);
        loi.add(nelly);
        loi.add(ash);
        loi.add(zor);

        pilot.setLoi(loi);


    }

    // MODIFIES: this
    // EFFECT: initialize instructor Zor
    private Instructor initializeZor(DayTime allDayTime) {
        Instructor zor = new Instructor();
        zor.setName("Zor Lee");
        zor.setAvails(allDayTime);
        zor.setExpYears(10);
        zor.setInstrClass("CFII - 1");
        zor.setHourlyRate(85);
        HashSet<String> zr = new HashSet<>();
        zr.add("VFR");
        zr.add("Float");
        zr.add("IFR");
        zr.add("Multi");
        zor.setRatings(zr);
        return zor;
    }

    // MODIFIES: this
    // EFFECT: initialize instructor Ash
    private Instructor initializeAsh(DayTime allDayTime) {
        Instructor ash = new Instructor();
        ash.setAvails(allDayTime);
        ash.setExpYears(5);
        ash.setHourlyRate(80);
        ash.setName("Ash Salem");
        ash.setInstrClass("CFII - 2");
        HashSet<String> ar = new HashSet<>();
        ar.add("VFR");
        ar.add("IFR");
        ar.add("Multi");
        ash.setRatings(ar);
        return ash;
    }

    // MODIFIES: this
    // EFFECT: initialize instructor Toren
    private Instructor initializeToren(DayTime allDayTime) {
        Instructor toren = new Instructor();
        toren.setName("Toren Molly");
        toren.setInstrClass("CFI - 4");
        toren.setAvails(allDayTime);
        toren.setHourlyRate(70);
        toren.setExpYears(1);
        HashSet<String> tr = new HashSet<>();
        tr.add("VFR");
        tr.add("Multi");
        toren.setRatings(tr);
        return toren;
    }

    // MODIFIES: this
    // EFFECT: initialize instructor Nelly
    private Instructor initializeNelly(DayTime allDayTime) {
        Instructor nelly = new Instructor();
        nelly.setName("Nelly Chou");
        nelly.setHourlyRate(72);
        nelly.setAvails(allDayTime);
        nelly.setInstrClass("CFII - 3");
        nelly.setExpYears(4);
        HashSet<String> nr = new HashSet<>();
        nr.add("IFR");
        nr.add("VFR");
        nr.add("Multi");
        nelly.setRatings(nr);
        return nelly;
    }

    // MODIFIES: this
    // EFFECT: initialize instructor James
    private Instructor initializeJames(DayTime allDayTime) {
        Instructor james = new Instructor();
        james.setName("James Gordon");
        james.setInstrClass("CFII - 4");
        james.setAvails(allDayTime);
        james.setHourlyRate(70);
        james.setExpYears(2);
        HashSet<String> jr = new HashSet<>();
        jr.add("IFR");
        jr.add("VFR");
        jr.add("Multi");
        james.setRatings(jr);
        return james;
    }
}
