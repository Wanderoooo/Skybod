package ui;

import model.*;

import java.util.*;

public class FlightPlanner {
    private static final String BOOKING = "BOOKING";
    private static final String WX = "WX";
    private static final String PREFLIGHT = "PREFLIGHT";
    private static final String POSTFLIGHT = "POSTFLIGHT";
    private static final String MENU = "MENU";
    private static final String QUIT = "QUIT";
    private static final String PREV = "PREV";
    private static final String FLIGHT = "FLIGHT";
    private static final String GROUND = "GROUND";
    private static final String VFR = "VFR";
    private static final String IFR = "IFR";
    private static final String FLOAT = "FLOAT";
    private static final String MULTI = "MULTI";
    private static final String NONE = "NONE";
    private Pilot pilot;
    private String choice;
    private Scanner sc;
    private String quit;
    private ArrayList<Plane> lop;
    private ArrayList<Instructor> loi;
    private Booking booking;
    private Weather wxObject;

    // here declared as field due to difference in method (& duplication of DateTime if
    // declared as local var)
    private DayTime piperDateTime;
    private DayTime cessna172DateTime;
    private DayTime diamondDateTime;
    private DayTime cessna152DateTime;
    private DayTime cirrusDateTime;

    // EFFECT: create flight planner based on user input
    public FlightPlanner() {

        initializePlane();
        initializeInstructor();
        registerUser();

        while (!quit.equals(QUIT)) {
            menuOptions();
            System.out.println("To manage booking - 'BOOKING'\n"
                    + "To check weather - 'WX'\n"
                    + "To preflight - 'PREFLIGHT'\n"
                    + "To postflight - 'POSTFLIGHT'\n"
                    + "To quit - 'QUIT'");

            quit = sc.next().toUpperCase();

        }

        System.out.println("Thanks for using Skybod Flight Planner! See you next time!");
    }

    private void menuOptions() {
        switch (quit) {
            case BOOKING:
                flightBook();
                break;
            case WX:
                flightWX();
                break;
            case PREFLIGHT:
                flightPre();
                break;
            case POSTFLIGHT:
                flightPost();
                break;
            default:
                System.out.println("That was not a valid option, please enter an option below:");
        }
    }

    public void flightBook() {
        System.out.println("To book - 'BOOK'\n"
                + "To cancel existing booking - 'CANCEL'\n"
                + "To check all your bookings - 'CHECK'\n"
                + "To return to menu - 'MENU'");
        String choice1 = sc.next().toUpperCase();

        while (!choice1.equals(MENU)) {
            bookingOptions(choice1);
            System.out.println("To book - 'BOOK'\n"
                    + "To cancel existing booking - 'CANCEL'\n"
                    + "To check all your bookings - 'CHECK'\n"
                    + "To return to menu - 'MENU'");

            choice1 = sc.next().toUpperCase();
        }
    }

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
                System.out.println("That's not a valid option, try again:");
        }
    }

    public void registerUser() {
        sc = new Scanner(System.in);
        pilot = new Pilot();

        System.out.println("Welcome to Skybod Aviation! Let's get you set up:" + "\nEnter your full name");
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

        // guard against non-boolean input?
        boolean isStud = sc.nextBoolean();
        pilot.setStudent(isStud);
        System.out.println("Great! You are all set up!\n"
                + "To manage booking - 'BOOKING'\n"
                + "To check weather - 'WX'\n"
                + "To preflight - 'PREFLIGHT'\n"
                + "To postflight - 'POSTFLIGHT'\n"
                + "To quit - 'QUIT'");

        quit = sc.next().toUpperCase();
    }

    private void ratingInputs(String choice) {
        int n = 2;

        while (choice.equalsIgnoreCase("add")) {
            // add check user does not type something twice
            choice = sc.next().toUpperCase();

            if (choice.equals(VFR) || choice.equals(IFR) || choice.equals(FLOAT)
                    || choice.equals(MULTI)) {

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
            } else if (!choice.equals(NONE)) {

                choice = "add";
                System.out.println("That's not a valid option, please enter - VFR, IFR, Multi, Float, None (to quit)");
            }
        }
    }

    public void flightWX() {
        wxObject = new Weather();

        System.out.println("To check most recent weather:\n\n"
                + "Terminal Area Forecast - TAF\n"
                + "Meteorological Terminal Air Report - METAR\n"
                + "Check last checked weather - LAST\n"
                + "To return to menu - 'MENU");

        String wxChoice = sc.next();

        while (!wxChoice.equalsIgnoreCase(MENU)) {
            wxOptions(wxChoice);

            System.out.println("To check most recent weather:\n\n"
                    + "Terminal Area Forecast - TAF\n"
                    + "Meteorological Terminal Air Report - METAR\n"
                    + "Check last checked weather - LAST\n"
                    + "To return to menu - 'MENU");

            wxChoice = sc.next();
        }
    }

    private void wxOptions(String wxChoice) {
        switch (wxChoice.toUpperCase()) {
            case "TAF":
                checkTaf();
                break;
            case "METAR":
                checkMetar();
                break;
            case "LAST":
                lastChecked();
                break;
            default:
                System.out.println("That's not a valid option, please try again\n");
        }
    }

    private void checkTaf() {
        System.out.println("Enter 4-character ICAO code for TAF at desired airport");

        String airportIdent = sc.next().toUpperCase();
        wxObject.tafUpdate(airportIdent);
        System.out.println("The latest TAF at " + airportIdent + " is:\n"
                + wxObject.getCurrentTaf() + "\n");

    }

    private void lastChecked() {
        if (wxObject.getCurrentTaf() == null && wxObject.getCurrentMetar() == null) {
            System.out.println("No last checked TAF and METAR");
        } else if (wxObject.getCurrentTaf() == null) {
            System.out.println("The last checked METAR is:\n\n"
                    + wxObject.getCurrentMetar() + "\n\n"
                    + "No last checked TAF");
        } else if (wxObject.getCurrentMetar() == null) {
            System.out.println("The last checked TAF is:\n\n"
                    + wxObject.getCurrentTaf() + "\n\n"
                    + "No last checked METAR");
        } else {
            System.out.println("The last checked TAF & METAR (respectively):\n\n"
                    + wxObject.getCurrentTaf() + "\n\n"
                    + wxObject.getCurrentMetar() + "\n");
        }
    }

    private void checkMetar() {
        System.out.println("Enter 4-character ICAO code for METAR at desired airport");

        String airportIdent = sc.next().toUpperCase();
        wxObject.metarUpdate(airportIdent);
        System.out.println("The latest METAR at " + airportIdent + " is:\n"
                + wxObject.getCurrentMetar() + "\n");
    }

    public void flightPre() {
        List<Booking> allMyBookings = pilot.getBookings();
        Preflight preflight = new Preflight();
        System.out.println("All your current aeroplane bookings:");
        int n = 1;
        for (Booking b : allMyBookings) {
            if (b.getTypeOfLesson().equals("FLIGHT")) {
                System.out.print(n + " - ");
                printBooking(b);
                n++;
            }
        }

        if (n == 1) {
            System.out.println("You don't have any flight bookings");
        } else {
            prefChecklist(allMyBookings, preflight);
        }
    }

    private void prefChecklist(List<Booking> allMyBookings, Preflight preflight) {
        Booking toPreflight = prefSelection(allMyBookings);
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

    private void completePref(Booking toPreflight) {
        pilot.getToPostFlight().add(toPreflight);
        pilot.getBookings().remove(toPreflight);
        System.out.println("You've completed preflighting for " + toPreflight.getPlane().getCallSign() + "\n");
    }

    private void preTakeOffCheck(Preflight preflight, Booking toPreflight) {
        System.out.println("Enter current hobbs meter value");
        double hobbsStart = sc.nextDouble();
        preflight.setHobbsTimeStart(hobbsStart);
        toPreflight.setPref(preflight);

        System.out.println("Enter departing Airport 4-digit ICAO code");
        String depAP = sc.next().toUpperCase();
        preflight.setDepartAP(depAP);
    }

    private void wbCheck(Preflight preflight, Booking toPreflight, WeightBalance wb) {
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
            wb.setAircraftWeight(toPreflight.getPlane().getPd().getWeightInfo());
            wb.setFuelWeight(toPreflight.getPlane().getFuelAmount() * 6.0);
            wb.setPassengerWeight(passengerWeight);
            wb.setPilotWeight(pilotWeight);
            wb.setTakeoffWeight(takeoffWeight);
            wb.setWithinLimit(true);
            preflight.setWb(wb);
            preflight.setWBDone(true);
            System.out.println("Your weight & balance calculations is now complete");
        } else {
            System.out.println("Reduce cargo weight, redo weight & balance chart, or do not fly aircraft");
        }
    }

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

    private void notAddFuel(Preflight preflight, Booking toPreflight, WeightBalance wb) {
        System.out.println("You are responsible for ensuring you have enough fuel for the duration of your "
                + "flight");
        preflight.setFuelEnough(true);
        wb.setFuelGallons(toPreflight.getPlane().getFuelAmount());
        System.out.println("Total possible flight time is " + (wb.getFuelGallons() / 9));
    }

    private void addFuel(Preflight preflight, Booking toPreflight, WeightBalance wb, double fuelAmountAdd) {
        toPreflight.getPlane().addFuel(fuelAmountAdd);
        System.out.println(fuelAmountAdd + " Gallons of fuel has been added to "
                + toPreflight.getPlane().getCallSign() + ", current fuel amount is "
                + toPreflight.getPlane().getFuelAmount());
        preflight.setFuelEnough(true);
        wb.setFuelGallons(toPreflight.getPlane().getFuelAmount());
        System.out.println("Total possible flight time is " + (wb.getFuelGallons() / 9));
    }

    private void printFuelingOptions(Booking toPreflight) {
        System.out.println("Your aircraft " + toPreflight.getPlane().getCallSign()
                + "'s fuel capacity is " + toPreflight.getPlane().getMaxFuel()
                + " Gallons of fuel, would you like to top up, or add specific amount of fuel?\n"
                + "To top up to max - true\n"
                + "To add specific amount of fuel - false");
    }

    private void printFuelOptions(Booking toPreflight) {
        System.out.println("Your aircraft currently has " + toPreflight.getPlane().getFuelAmount()
                + "US Gallons of fuel, would you like to refuel " + toPreflight.getPlane().getType() + "?\n"
                + "To refuel - true\n"
                + "To continue with current fuel amount - false");
    }

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

    private Booking prefSelection(List<Booking> allMyBookings) {
        System.out.println("Enter the corresponding number for the booking you'd like to preflight");
        int bookingNum = sc.nextInt();

        Booking toPreflight = allMyBookings.get(bookingNum - 1);
        System.out.print("You are preflighting for ");
        printBooking(toPreflight);

        System.out.println("Complete your aircraft document checks, enter true or false for following actions");
        return toPreflight;
    }

    public void flightPost() {
        List<Booking> allToPostflight = pilot.getToPostFlight();
        if (allToPostflight.size() == 0) {
            noPostflight();
        } else {
            Postflight postflight = new Postflight();
            getAllPref(allToPostflight);
            Booking toPostflight = postInitialize(allToPostflight);
            planeTiedDownCheck(postflight, toPostflight);

            double endHobbsTime = enterEndHobbsTime();
            double flightTime = endHobbsTime - toPostflight.getPref().getHobbsTimeStart();
            double fuelUse = 9 * flightTime;
            double beforeFlightFuel = toPostflight.getPlane().getFuelAmount();
            double fuelLeft = beforeFlightFuel - fuelUse;
            boolean fuelMakesSense = false;

            while (!fuelMakesSense) {
                if (fuelLeft < 0) {
                    endHobbsTime = negHobbsTime(toPostflight);
                } else {
                    fuelMakesSense = correctHobbsTime(toPostflight, flightTime, fuelUse, fuelLeft);
                }
            }

            lastPostProcedures(postflight, toPostflight, endHobbsTime, flightTime);
        }
    }

    private double enterEndHobbsTime() {
        System.out.println("Enter your ending hobbs time");
        double endHobbsTime = sc.nextDouble();
        return endHobbsTime;
    }

    private void noPostflight() {
        System.out.println("You have no bookings to postflight, please complete preflight first for flying bookings");
    }

    private void lastPostProcedures(Postflight postflight, Booking toPostflight,
                                    double endHobbsTime, double flightTime) {
        PlaneFlightLog fl = untilMaintenance(toPostflight, flightTime);
        completePost(postflight, toPostflight, endHobbsTime, flightTime, fl);
        System.out.println("You've completed postflighting for " + toPostflight.getPlane().getCallSign());
    }

    private void getAllPref(List<Booking> allToPostflight) {
        System.out.println("All your preflighted bookings:");
        int n = 1;
        for (Booking b : allToPostflight) {
            System.out.print(n + " - ");
            printBooking(b);
            n++;
        }
    }

    private void completePost(Postflight postflight, Booking toPostflight,
                              double endHobbsTime, double flightTime, PlaneFlightLog fl) {
        System.out.println("Enter arrival airport 4-digit ICAO code");
        String arrAP = sc.next().toUpperCase();

        fl.setHobbsTimeStart(toPostflight.getPref().getHobbsTimeStart());
        fl.setHobbsTimeEnd(endHobbsTime);
        fl.setDepartingAP(toPostflight.getPref().getDepartAP());
        fl.setArrivingAP(arrAP);

        System.out.println("Enter type of piloting completed- dual, solo");
        String typeOfPiloting = sc.next();

        postflight.setDocComplete(true);
        PilotLog pilotLog = new PilotLog();
        pilotLog.setFlightTime(flightTime);
        pilotLog.setTime(toPostflight.getTimeBooked());
        pilotLog.setTypeOfPiloting(typeOfPiloting);
        pilotLog.setDay(toPostflight.getDayBooked());
        pilotLog.setPlaneType(toPostflight.getPlane().getType());
        pilotLog.setPlaneCallSign(toPostflight.getPlane().getCallSign());

        toPostflight.getPlane().getPd().getFl().add(fl);
        toPostflight.setPostf(postflight);
        pilot.getCompletedBookings().add(toPostflight);
        pilot.getPl().add(pilotLog);
        pilot.getToPostFlight().remove(toPostflight);
    }

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

    private boolean correctHobbsTime(Booking toPostflight, double flightTime, double fuelUse, double fuelLeft) {
        boolean fuelMakesSense;
        System.out.println("“Your total flight time is " + flightTime + "\n"
                + "your total fuel use is " + fuelUse + " gallons");
        toPostflight.getPlane().setFuelAmount(fuelLeft);
        fuelMakesSense = true;
        return fuelMakesSense;
    }

    private double negHobbsTime(Booking toPostflight) {
        double endHobbsTime;
        double flightTimeMax = toPostflight.getPref().getWb().getFuelGallons() / 9;
        System.out.println("Your maximum flight time is " + flightTimeMax
                + ", as noted, please enter the correct ending hobbs time");
        endHobbsTime = sc.nextDouble();
        return endHobbsTime;
    }

    private void planeTiedDownCheck(Postflight postflight, Booking toPostflight) {
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
        postflight.setPlaneTiedDown(true);
    }

    private Booking postInitialize(List<Booking> allToPostflight) {
        System.out.println("Enter the corresponding number for booking to postflight");
        int bookingNum = sc.nextInt();

        Booking toPostflight = allToPostflight.get(bookingNum - 1);
        System.out.print("You are postflighting for ");
        printBooking(toPostflight);
        return toPostflight;
    }

    public void bookGroundFlight() {
        System.out.println("To book ground session - 'GROUND\n"
                + "To book flight lesson - 'FLIGHT'\n"
                + "To return to previous options - 'PREV' ");

        choice = sc.next().toUpperCase();

        while (!choice.equals(PREV)) {
            switch (choice) {
                case FLIGHT:
                    bookFlight();
                    break;
                case GROUND:
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


    public void bookFlight() {
        booking = new Booking();
        selectPlaneType();
        System.out.println(booking.getPlane().getType() + " " + booking.getPlane().getCallSign()
                + "'s availability on chosen day:");
        printDayAvail(choice, booking.getPlane().getAvails());
        ArrayList<String> dayAvail = booking.getPlane().getAvails().findDay(choice);

        System.out.println("\nTo book a time - enter the hour" + "\nTo return to previous option - 'PREV'");

        String c = sc.next();
        boolean successBookTime = false;

        for (String time : dayAvail) {
            if (time.equals(c)) {
                successBookTime = isSuccessBookTime(dayAvail, c);
            }
        }

        bookingAtDayTime(c, successBookTime);

        if (pilot.getStudentStatus()) {
            String insName = selectInstructor();
            boolean isInsBooked = false;
            boolean isInstrFound = false;
            isInstrFound = toBookInstr(c, insName, isInsBooked, isInstrFound);
            instrNotFound(isInstrFound);
        }

        addTheBooking();
    }

    private void addTheBooking() {
        booking.setTypeOfLesson(FLIGHT);
        pilot.addBooking(booking);
    }

    private void instrNotFound(boolean isInstrFound) {
        if (!isInstrFound) {
            System.out.println("Instructor not found");
        }
    }

    private boolean toBookInstr(String c, String insName, boolean isInsBooked, boolean isInstrFound) {
        for (Instructor i : loi) {
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

    private boolean isInsBooked(String c, String insName, Instructor i, ArrayList<String> insAvailOnDay) {
        boolean isInsBooked;
        System.out.println(insName + " has been book at " + c + " for flying lesson ");
        booking.setInstructor(i);
        ArrayList<String> notInfForloop = (ArrayList<String>) insAvailOnDay.clone();
        notInfForloop.remove(c);
        booking.getInstructor().getAvails().setDay(choice, notInfForloop);
        isInsBooked = true;
        return isInsBooked;
    }

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

    private void bookingAtDayTime(String c, boolean successBookTime) {
        if (successBookTime) {
            System.out.println("You've booked " + booking.getPlane().getCallSign() + " on " + choice + " at " + c);
        } else {
            System.out.println("The time you've selected is unavailable, please try again");
        }
    }

    private boolean isSuccessBookTime(ArrayList<String> dayAvail, String c) {
        boolean successBookTime;
        ArrayList<String> notInfForloop = (ArrayList<String>) dayAvail.clone();
        notInfForloop.remove(c);
        booking.getPlane().getAvails().setDay(choice, notInfForloop);
        successBookTime = true;
        booking.setDayBooked(choice.toUpperCase());
        booking.setTimeBooked(c);
        return successBookTime;
    }

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
            for (Plane p : lop) {
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

    public void bookGround() {
        booking = new Booking();
        String instrName = selectInstructor();
        for (Instructor i : loi) {
            if (i.getName().equalsIgnoreCase(instrName)) {
                booking.setInstructor(i);
            }
        }

        printNextStep();

        choice = sc.next();
        System.out.println(booking.getInstructor().getName() + "'s availability on " + choice + ":");
        printDayAvail(choice, booking.getInstructor().getAvails());
        ArrayList<String> dayAvail = booking.getInstructor().getAvails().findDay(choice);

        System.out.println("\nTo book a time - enter the hour"
                + "\nTo return to previous option - 'PREV'");

        String c = sc.next();
        boolean successBookTime = false;

        for (String time : dayAvail) {
            if (time.equals(c)) {
                successBookTime = isBookTime(dayAvail, c);
            }
        }
        bookedOrNot(c, successBookTime);
    }

    private void bookedOrNot(String c, boolean successBookTime) {
        if (successBookTime) {
            System.out.println("You've booked " + booking.getInstructor().getName()
                    + " on " + choice + " at " + c);
        } else {
            System.out.println("The time you've selected is unavailable, please try again");
        }

        booking.setTypeOfLesson(GROUND);
        pilot.addBooking(booking);
    }

    private boolean isBookTime(ArrayList<String> dayAvail, String c) {
        boolean successBookTime;
        ArrayList<String> notInfForloop = (ArrayList<String>) dayAvail.clone();
        notInfForloop.remove(c);
        booking.getInstructor().getAvails().setDay(choice, notInfForloop);
        successBookTime = true;
        booking.setDayBooked(choice.toUpperCase());
        booking.setTimeBooked(c);
        return successBookTime;
    }

    private void printNextStep() {
        if (!booking.getInstructor().equals(null)) {
            System.out.println("Enter day which you'd like to make your booking on:\n"
                    + "Monday - Friday");
        } else {
            System.out.println("The instructor you requested is not a member of this school");
        }
    }

    // EFFECT:
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

    private void cancelBookingAtTime(String day, ArrayList<Booking> bookingsOnDay) {
        printBookingsOnDay(day, bookingsOnDay);

        System.out.println("Enter the time of the booking you'd like to cancel");
        String time = sc.next();

        Booking cancelBooking = getBooking(bookingsOnDay, time);

        if (!(cancelBooking == null)) {
            printCancelReason();
            String reasonCancel = sc.next();
            String cancelDay = cancelBooking.getDayBooked();
            String cancelTime = cancelBooking.getTimeBooked();
            if (!(cancelBooking.getPlane() == null)) {
                cancelBooking.getPlane().getAvails().addBackTimeGivenDay(cancelDay, cancelTime);
            }

            if (!(cancelBooking.getInstructor() == null)) {
                cancelBooking.getInstructor().getAvails().addBackTimeGivenDay(cancelDay, cancelTime);
            }
            cancelThisBooking(cancelBooking, reasonCancel);
        } else {
            System.out.println("There's no booking at " + time + " on " + day);
        }
    }

    private void cancelThisBooking(Booking cancelBooking, String reasonCancel) {
        cancelBooking.setReasonCancelled(reasonCancel);
        pilot.getBookings().remove(cancelBooking);
        System.out.println("Your booking");
        printBooking(cancelBooking);
        System.out.println("has been cancelled\n");
        pilot.getCancelled().add(cancelBooking);
    }

    private void printCancelReason() {
        System.out.println("Enter reason for cancellation:\n"
                + "Due to weather - wx\n"
                + "Due to sickness - sick\n"
                + "Due to unexpected event - event");
    }

    private Booking getBooking(ArrayList<Booking> bookingsOnDay, String time) {
        Booking cancelBooking = null;
        for (Booking b : bookingsOnDay) {
            if (b.getTimeBooked().equalsIgnoreCase(time)) {
                cancelBooking = b;
            }
        }
        return cancelBooking;
    }

    private void printBookingsOnDay(String day, ArrayList<Booking> bookingsOnDay) {
        System.out.println("Your bookings on " + day + " are:");
        for (Booking b : bookingsOnDay) {
            printBooking(b);
        }
    }

    public void checkBookings() {
        List<Booking> myBookings = pilot.getBookings();

        if (myBookings.size() == 0) {
            System.out.println("You have no bookings");
        } else {
            System.out.println("All your current bookings are:");

            for (Booking b : myBookings) {
                printBooking(b);
            }
        }

        System.out.print("\n");
    }

    // EFFECT:
    public void printBooking(Booking b) {
        if (b.getPlane() == null) {
            System.out.println(b.getTypeOfLesson() + " lesson at " + b.getTimeBooked() + " with "
                    + b.getInstructor().getName());
        } else if (b.getInstructor() == null) {
            System.out.println("AIRCRAFT at " + b.getTimeBooked() + " for " + b.getPlane().getType() + " "
                    + b.getPlane().getCallSign());
        } else {
            System.out.println(b.getTypeOfLesson() + " lesson at " + b.getTimeBooked() + " on " + b.getPlane().getType()
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

    public void initializePlane() {
        Plane cessna172 = new Plane();
        Plane cessna152 = new Plane();
        Plane piper = new Plane();
        Plane cirrus = new Plane();
        Plane diamond = new Plane();

        initialize172(cessna172);
        initialize152(cessna152);
        initializePiper(piper);
        initializeCirrus(cirrus);
        initializeDiamond(diamond);

        lop = new ArrayList<>();
        lop.add(cessna152);
        lop.add(cessna172);
        lop.add(piper);
        lop.add(cirrus);
        lop.add(diamond);
    }

    private void initializeDiamond(Plane diamond) {
        diamond.setType("Diamond-DA40");
        diamond.setCallSign("C-POYL");
        initializeDayTimeDiamond();
        diamond.setAvails(diamondDateTime);
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

    private PlaneDocuments initializePDDiamond(Insurance diamondins, LinkedList<PlaneFlightLog> logdiamond) {
        PlaneDocuments docdiamond = new PlaneDocuments();
        docdiamond.setFl(logdiamond);
        docdiamond.setWeightInfo(1058.25);
        docdiamond.setInsurance(diamondins);
        return docdiamond;
    }

    private Insurance initializeInsDiamond() {
        Insurance diamondins = new Insurance();
        diamondins.setDateValid("03/10/2022");
        diamondins.setDateValidUntil("03/10/2023");
        diamondins.setAmountInsured(7000000);
        diamondins.setTypeOfInsurance("Liability, Hull");
        return diamondins;
    }

    private void initializeDayTimeDiamond() {
        diamondDateTime = new DayTime();
        diamondDateTime.addGivenDayTime("Monday", "0100", "2200");
        diamondDateTime.addGivenDayTime("Tuesday", "1100", "2300");
        diamondDateTime.addGivenDayTime("Wednesday", "0900", "1600");
        diamondDateTime.addGivenDayTime("Thursday", "0300", "1800");
        diamondDateTime.addGivenDayTime("Friday", "0400", "2000");
        diamondDateTime.addGivenDayTime("Saturday", "1800", "2400");
        diamondDateTime.addGivenDayTime("Sunday", "1300", "2200");
    }

    private void initializeCirrus(Plane cirrus) {
        cirrus.setType("Cirrus-SR22T");
        cirrus.setCallSign("C-CIRR");
        initializeDayTimeCirrus();
        cirrus.setAvails(cirrusDateTime);
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

    private PlaneFlightLog initializePFLcirrus() {
        PlaneFlightLog cirrusfl = new PlaneFlightLog();
        cirrusfl.setArrivingAP("CYZZ");
        cirrusfl.setDepartingAP("CYVR");
        cirrusfl.setHobbsTimeStart(77.1);
        cirrusfl.setHobbsTimeEnd(78.2);
        cirrusfl.setHoursTillMaint(29.4);
        return cirrusfl;
    }

    private void initializeDayTimeCirrus() {
        cirrusDateTime = new DayTime();
        cirrusDateTime.addGivenDayTime("Monday", "0000", "2400");
        cirrusDateTime.addGivenDayTime("Tuesday", "0000", "2400");
        cirrusDateTime.addGivenDayTime("Wednesday", "0000", "2400");
        cirrusDateTime.addGivenDayTime("Thursday", "0000", "2400");
        cirrusDateTime.addGivenDayTime("Friday", "0000", "2400");
        cirrusDateTime.addGivenDayTime("Saturday", "0000", "2400");
        cirrusDateTime.addGivenDayTime("Sunday", "0000", "2400");
    }

    private void initializePiper(Plane piper) {
        piper.setType("Piper-Seneca");
        piper.setCallSign("C-FOTX");
        initializeDayTimePiper();
        piper.setAvails(piperDateTime);
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

    private PlaneFlightLog initializeFLPiper() {
        PlaneFlightLog piperfl = new PlaneFlightLog();
        piperfl.setArrivingAP("CYVR");
        piperfl.setDepartingAP("CYZY");
        piperfl.setHobbsTimeStart(45.4);
        piperfl.setHobbsTimeEnd(46.0);
        piperfl.setHoursTillMaint(104.8);
        return piperfl;
    }

    private Insurance initializeInsPiper() {
        Insurance piperins = new Insurance();
        piperins.setDateValid("07/27/2022");
        piperins.setDateValidUntil("07/27/2023");
        piperins.setAmountInsured(4000000);
        piperins.setTypeOfInsurance("Liability, Hull");
        return piperins;
    }

    private void initializeDayTimePiper() {
        piperDateTime = new DayTime();
        piperDateTime.addGivenDayTime("Monday", "1000", "1700");
        piperDateTime.addGivenDayTime("Tuesday", "0300", "2200");
        piperDateTime.addGivenDayTime("Wednesday", "0600", "1200");
        piperDateTime.addGivenDayTime("Thursday", "0300", "1900");
        piperDateTime.addGivenDayTime("Friday", "0500", "1500");
        piperDateTime.addGivenDayTime("Saturday", "1300", "2400");
        piperDateTime.addGivenDayTime("Sunday", "1100", "2000");
    }

    private void initialize152(Plane cessna152) {
        cessna152.setType("Cessna-152");
        cessna152.setCallSign("C-GUUY");
        initializeDayTime152();
        cessna152.setAvails(cessna152DateTime);
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

    private PlaneFlightLog initializePFL() {
        PlaneFlightLog c152fl = new PlaneFlightLog();
        c152fl.setArrivingAP("CYXX");
        c152fl.setDepartingAP("CYVR");
        c152fl.setHobbsTimeStart(95.6);
        c152fl.setHobbsTimeEnd(97.0);
        c152fl.setHoursTillMaint(3.0);
        return c152fl;
    }

    private Insurance initializeIns152() {
        Insurance c152ins = new Insurance();
        c152ins.setDateValid("11/30/2022");
        c152ins.setDateValidUntil("11/30/2023");
        c152ins.setAmountInsured(5000000);
        c152ins.setTypeOfInsurance("Liability, Hull");
        return c152ins;
    }

    private void initializeDayTime152() {
        cessna152DateTime = new DayTime();
        cessna152DateTime.addGivenDayTime("Monday", "0900", "2100");
        cessna152DateTime.addGivenDayTime("Tuesday", "0700", "2200");
        cessna152DateTime.addGivenDayTime("Wednesday", "0700", "1800");
        cessna152DateTime.addGivenDayTime("Thursday", "0500", "1700");
        cessna152DateTime.addGivenDayTime("Friday", "0300", "0900");
        cessna152DateTime.addGivenDayTime("Saturday", "1400", "2300");
        cessna152DateTime.addGivenDayTime("Sunday", "1200", "2300");
    }

    private void initialize172(Plane cessna172) {
        cessna172.setType("Cessna-172");
        cessna172.setCallSign("C-GOOV");
        initializeDayTime172();
        cessna172.setAvails(cessna172DateTime);
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

    private PlaneDocuments initializePd172(Insurance c172ins, LinkedList<PlaneFlightLog> logs172) {
        PlaneDocuments doc172 = new PlaneDocuments();
        doc172.setFl(logs172);
        doc172.setWeightInfo(1209.13);
        doc172.setInsurance(c172ins);
        return doc172;
    }

    private PlaneFlightLog initializePlaneLog172() {
        PlaneFlightLog c172fl = new PlaneFlightLog();
        c172fl.setArrivingAP("CZBB");
        c172fl.setDepartingAP("CYVR");
        c172fl.setHobbsTimeStart(29.8);
        c172fl.setHobbsTimeEnd(30.1);
        c172fl.setHoursTillMaint(69.9);
        return c172fl;
    }

    private Insurance initializeInsurance172() {
        Insurance c172ins = new Insurance();
        c172ins.setDateValid("05/22/2022");
        c172ins.setDateValidUntil("05/22/2023");
        c172ins.setAmountInsured(3000000);
        c172ins.setTypeOfInsurance("Liability, Hull");
        return c172ins;
    }

    private void initializeDayTime172() {
        cessna172DateTime = new DayTime();
        cessna172DateTime.addGivenDayTime("Monday", "0700", "2200");
        cessna172DateTime.addGivenDayTime("Tuesday", "0900", "2000");
        cessna172DateTime.addGivenDayTime("Wednesday", "0600", "1000");
        cessna172DateTime.addGivenDayTime("Thursday", "0800", "2100");
        cessna172DateTime.addGivenDayTime("Friday", "0400", "2400");
        cessna172DateTime.addGivenDayTime("Saturday", "0500", "2300");
        cessna172DateTime.addGivenDayTime("Sunday", "0200", "1900");
    }

    public void initializeInstructor() {
        Instructor james = initializeJames();
        Instructor nelly = initializeNelly();
        Instructor toren = initializeToren();
        Instructor ash = initializeAsh();
        Instructor zor = initializeZor();

        loi = new ArrayList<>();
        loi.add(james);
        loi.add(toren);
        loi.add(nelly);
        loi.add(ash);
        loi.add(zor);

    }

    private Instructor initializeZor() {
        Instructor zor = new Instructor();
        zor.setName("Zor Lee");
        zor.setAvails(cirrusDateTime);
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

    private Instructor initializeAsh() {
        Instructor ash = new Instructor();
        ash.setAvails(cirrusDateTime);
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

    private Instructor initializeToren() {
        Instructor toren = new Instructor();
        toren.setName("Toren Molly");
        toren.setInstrClass("CFI - 4");
        toren.setAvails(cirrusDateTime);
        toren.setHourlyRate(70);
        toren.setExpYears(1);
        HashSet<String> tr = new HashSet<>();
        tr.add("VFR");
        tr.add("Multi");
        toren.setRatings(tr);
        return toren;
    }

    private Instructor initializeNelly() {
        Instructor nelly = new Instructor();
        nelly.setName("Nelly Chou");
        nelly.setHourlyRate(72);
        nelly.setAvails(cirrusDateTime);
        nelly.setInstrClass("CFII - 3");
        nelly.setExpYears(4);
        HashSet<String> nr = new HashSet<>();
        nr.add("IFR");
        nr.add("VFR");
        nr.add("Multi");
        nelly.setRatings(nr);
        return nelly;
    }

    private Instructor initializeJames() {
        Instructor james = new Instructor();
        james.setName("James Gordon");
        james.setInstrClass("CFII - 4");
        james.setAvails(cirrusDateTime);
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
