package ui;

import model.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class FlightPlanner {
    private static final String BOOKING = "BOOKING";
    private static final String WX = "WX";
    private static final String PREFLIGHT = "PREFLIGHT";
    private static final String POSTFLIGHT = "POSTFLIGHT";
    private static final String BOOK = "BOOK";
    private static final String CANCEL = "CANCEL";
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

            System.out.println("To manage booking - 'BOOKING'\n"
                    + "To check weather - 'WX'\n"
                    + "To preflight - 'PREFLIGHT'\n"
                    + "To postflight - 'POSTFLIGHT'\n"
                    + "To quit - 'QUIT'");

            quit = sc.next().toUpperCase();

        }

        System.out.println("Thanks for using Skybod Flight Planner! See you next time!");
    }

    public void flightBook() {
        System.out.println("To book - 'BOOK'\n"
                + "To cancel existing booking - 'CANCEL'\n"
                + "To return to menu - 'MENU'");
        choice = sc.next().toUpperCase();

        while (!choice.equals(MENU)) {

            switch (choice) {
                case BOOK:
                    bookGroundFlight();
                    break;
                case CANCEL:
                    // stub
                    break;
                case MENU:
                default:
                    System.out.println("That's not a valid option, try again:");
            }

            System.out.println("To book - 'BOOK'\n"
                    + "To cancel existing booking - 'CANCEL'\n"
                    + "To return to menu - 'MENU'");

            choice = sc.next().toUpperCase();
        }
    }

    public void registerUser() {
        sc = new Scanner(System.in);
        pilot = new Pilot();

        System.out.println("Welcome to Skybod Aviation! Let's get you set up:" + "\nEnter your full name");
        String name = sc.nextLine();
        pilot.setName(name);

        // should this go into another method? Or can I override 25 line req
        System.out.println("Hi " + pilot.getName() + ", what are your ratings? - VFR, IFR, Multi, Float, None");
        choice = "add";
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

        // guard against non-int input?
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

    public void flightWX() {
        // stub
    }

    public void flightPre() {
        // stub
    }

    public void flightPost() {
        // stub
    }

    public void bookGroundFlight() {
        System.out.println("For ground session - 'GROUND\n"
                + "For flight lesson - 'FLIGHT'\n"
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

            System.out.println("\nFor ground session - 'GROUND'\n"
                    + "For flight lesson - 'FLIGHT'\n"
                    + "To return to previous options - 'PREV'");

            choice = sc.next().toUpperCase();
        }
    }

    public void bookFlight() {
        booking = new Booking();
        System.out.println("Select your airplane type:\n"
                + "Piper-Seneca\n"
                + "Cessna-152\n"
                + "Diamond-DA40\n"
                + "Cirrus-SR22T");

        String type = sc.next();
        for (Plane p : lop) {
            if (p.getType().equalsIgnoreCase(type)) {
                booking.setPlane(p);
            }
        }

        // integrate a while-loop
        if (!booking.getPlane().equals(null)) {
            System.out.println("Enter day which you'd like to make your booking on:\n"
                    + "Monday - Friday");
        } else {
            System.out.println("The plane type you requested is not available");
        }

        choice = sc.next();
        System.out.println(type + booking.getPlane().getCallSign() + "'s availability on " + choice + ":");
        booking.getPlane().getAvails().printDayAvail(choice);

        System.out.println("To book a time - enter the hour"
                + "To return to previous option - 'PREV'");

        // Airplane type
        //Date & time (if no booking @ same day & time, prints booking?, “enter another time/date”)
        //(Instructor name) -> if student
        //“These are available…” -> prints time table w/ plane call sign & instructor name (OR if none avail, loop to Date & Time)
        //Airplane call sign
        //“Plane … & instructor booked @ … for flight lesson”
    }

    public void bookGround() {
        // stub
    }

    public void initializePlane() {
        Plane cessna172 = new Plane();
        Plane cessna152 = new Plane();
        Plane piper = new Plane();
        Plane cirrus = new Plane();
        Plane diamond = new Plane();

        cessna172.setType("Cessna-172");
        cessna172.setCallSign("C-GOOV");
        cessna172DateTime = new DayTime();
        cessna172DateTime.addGivenDayTime("Monday", "0700", "2200");
        cessna172DateTime.addGivenDayTime("Tuesday", "0900", "2000");
        cessna172DateTime.addGivenDayTime("Wednesday", "0600", "1000");
        cessna172DateTime.addGivenDayTime("Thursday", "0800", "2100");
        cessna172DateTime.addGivenDayTime("Friday", "0400", "2400");
        cessna172DateTime.addGivenDayTime("Saturday", "0500", "2300");
        cessna172DateTime.addGivenDayTime("Sunday", "0200", "1900");
        cessna172.setAvails(cessna172DateTime);
        cessna172.setHourlyRentalRate(190);
        cessna172.setHourlyFuelRate(40);

        Insurance c172ins = new Insurance();
        c172ins.setDateValid("05/22/2022");
        c172ins.setDateValidUntil("05/22/2023");
        c172ins.setAmountInsured(3000000);
        c172ins.setTypeOfInsurance("Liability, Hull");

        PlaneFlightLog c172fl = new PlaneFlightLog();
        c172fl.setArrivingAP("CZBB");
        c172fl.setDepartingAP("CYVR");
        c172fl.setHobbsTimeStart(29.8);
        c172fl.setHobbsTimeEnd(30.1);
        c172fl.setHoursTillMaint(69.9);

        ArrayList<PlaneFlightLog> logs172 = new ArrayList<>();
        logs172.add(c172fl);

        PlaneDocuments doc172 = new PlaneDocuments();
        doc172.setFl(logs172);
        doc172.setWeightInfo("Empty Weight - 1209.13lb");
        doc172.setInsurance(c172ins);

        cessna172.setPd(doc172);
        cessna172.setFuelAmount(25.5);
        cessna172.setMaxFuel(48.0);


        cessna152.setType("Cessna-152");
        cessna152.setCallSign("C-GUUY");
        cessna152DateTime = new DayTime();
        cessna152DateTime.addGivenDayTime("Monday", "0900", "2100");
        cessna152DateTime.addGivenDayTime("Tuesday", "0700", "2200");
        cessna152DateTime.addGivenDayTime("Wednesday", "0700", "1800");
        cessna152DateTime.addGivenDayTime("Thursday", "0500", "1700");
        cessna152DateTime.addGivenDayTime("Friday", "0300", "0900");
        cessna152DateTime.addGivenDayTime("Saturday", "1400", "2300");
        cessna152DateTime.addGivenDayTime("Sunday", "1200", "2300");
        cessna152.setAvails(cessna152DateTime);
        cessna152.setHourlyRentalRate(175);
        cessna152.setHourlyFuelRate(36);

        Insurance c152ins = new Insurance();
        c152ins.setDateValid("11/30/2022");
        c152ins.setDateValidUntil("11/30/2023");
        c152ins.setAmountInsured(5000000);
        c152ins.setTypeOfInsurance("Liability, Hull");

        PlaneFlightLog c152fl = new PlaneFlightLog();
        c152fl.setArrivingAP("CYXX");
        c152fl.setDepartingAP("CYVR");
        c152fl.setHobbsTimeStart(95.6);
        c152fl.setHobbsTimeEnd(97.0);
        c152fl.setHoursTillMaint(3.0);

        ArrayList<PlaneFlightLog> logs152 = new ArrayList<>();
        logs152.add(c152fl);

        PlaneDocuments doc152 = new PlaneDocuments();
        doc152.setFl(logs152);
        doc152.setWeightInfo("Empty Weight - 1138.26lb");
        doc152.setInsurance(c152ins);

        cessna152.setPd(doc152);
        cessna152.setFuelAmount(13.5);
        cessna152.setMaxFuel(26.0);


        piper.setType("Piper-Seneca");
        piper.setCallSign("C-FOTX");
        piperDateTime = new DayTime();
        piperDateTime.addGivenDayTime("Monday", "1000", "1700");
        piperDateTime.addGivenDayTime("Tuesday", "0300", "2200");
        piperDateTime.addGivenDayTime("Wednesday", "0600", "1200");
        piperDateTime.addGivenDayTime("Thursday", "0300", "1900");
        piperDateTime.addGivenDayTime("Friday", "0500", "1500");
        piperDateTime.addGivenDayTime("Saturday", "1300", "2400");
        piperDateTime.addGivenDayTime("Sunday", "1100", "2000");
        piper.setAvails(piperDateTime);
        piper.setHourlyRentalRate(215);
        piper.setHourlyFuelRate(50);

        Insurance piperins = new Insurance();
        piperins.setDateValid("07/27/2022");
        piperins.setDateValidUntil("07/27/2023");
        piperins.setAmountInsured(4000000);
        piperins.setTypeOfInsurance("Liability, Hull");

        PlaneFlightLog piperfl = new PlaneFlightLog();
        piperfl.setArrivingAP("CYVR");
        piperfl.setDepartingAP("CYZY");
        piperfl.setHobbsTimeStart(45.4);
        piperfl.setHobbsTimeEnd(45.2);
        piperfl.setHoursTillMaint(104.8);

        ArrayList<PlaneFlightLog> logspiper = new ArrayList<>();
        logspiper.add(piperfl);

        PlaneDocuments docpiper = new PlaneDocuments();
        docpiper.setFl(logspiper);
        docpiper.setWeightInfo("Empty Weight - 1343.93lb");
        docpiper.setInsurance(piperins);

        piper.setPd(docpiper);
        piper.setMaxFuel(98.0);
        piper.setFuelAmount(39.3);


        cirrus.setType("Cirrus-SR22T");
        cirrus.setCallSign("C-CIRR");
        cirrusDateTime = new DayTime();
        cirrusDateTime.addGivenDayTime("Monday", "0000", "2400");
        cirrusDateTime.addGivenDayTime("Tuesday", "0000", "2400");
        cirrusDateTime.addGivenDayTime("Wednesday", "0000", "2400");
        cirrusDateTime.addGivenDayTime("Thursday", "0000", "2400");
        cirrusDateTime.addGivenDayTime("Friday", "0000", "2400");
        cirrusDateTime.addGivenDayTime("Saturday", "0000", "2400");
        cirrusDateTime.addGivenDayTime("Sunday", "0000", "2400");
        cirrus.setAvails(cirrusDateTime);
        cirrus.setHourlyFuelRate(51);
        cirrus.setHourlyRentalRate(212);
        Insurance cirrusins = new Insurance();
        cirrusins.setDateValid("08/03/2022");
        cirrusins.setDateValidUntil("08/03/2023");
        cirrusins.setAmountInsured(7000000);
        cirrusins.setTypeOfInsurance("Liability, Hull");

        PlaneFlightLog cirrusfl = new PlaneFlightLog();
        cirrusfl.setArrivingAP("CYZZ");
        cirrusfl.setDepartingAP("CYVR");
        cirrusfl.setHobbsTimeStart(77.1);
        cirrusfl.setHobbsTimeEnd(78.2);
        cirrusfl.setHoursTillMaint(29.4);

        ArrayList<PlaneFlightLog> logcirrus = new ArrayList<>();
        logcirrus.add(cirrusfl);

        PlaneDocuments doccirrus = new PlaneDocuments();
        doccirrus.setFl(logcirrus);
        doccirrus.setWeightInfo("Empty Weight - 1441.92lb");
        doccirrus.setInsurance(cirrusins);

        cirrus.setPd(doccirrus);
        cirrus.setFuelAmount(40.1);
        cirrus.setMaxFuel(92.0);


        diamond.setType("Diamond-DA40");
        diamond.setCallSign("C-POYL");
        diamondDateTime = new DayTime();
        diamondDateTime.addGivenDayTime("Monday", "0100", "2200");
        diamondDateTime.addGivenDayTime("Tuesday", "1100", "2300");
        diamondDateTime.addGivenDayTime("Wednesday", "0900", "1600");
        diamondDateTime.addGivenDayTime("Thursday", "0300", "1800");
        diamondDateTime.addGivenDayTime("Friday", "0400", "2000");
        diamondDateTime.addGivenDayTime("Saturday", "1800", "2400");
        diamondDateTime.addGivenDayTime("Sunday", "1300", "2200");
        diamond.setAvails(diamondDateTime);
        diamond.setHourlyRentalRate(200);
        diamond.setHourlyFuelRate(45);

        Insurance diamondins = new Insurance();
        diamondins.setDateValid("03/10/2022");
        diamondins.setDateValidUntil("03/10/2023");
        diamondins.setAmountInsured(7000000);
        diamondins.setTypeOfInsurance("Liability, Hull");

        PlaneFlightLog diamondfl = new PlaneFlightLog();
        diamondfl.setArrivingAP("CYUL");
        diamondfl.setDepartingAP("CYVR");
        diamondfl.setHobbsTimeStart(0.3);
        diamondfl.setHobbsTimeEnd(11.1);
        diamondfl.setHoursTillMaint(203.6);

        ArrayList<PlaneFlightLog> logdiamond = new ArrayList<>();
        logdiamond.add(diamondfl);

        PlaneDocuments docdiamond = new PlaneDocuments();
        docdiamond.setFl(logdiamond);
        docdiamond.setWeightInfo("Empty Weight - 1058.25lb");
        docdiamond.setInsurance(diamondins);

        diamond.setPd(docdiamond);
        diamond.setFuelAmount(10.4);
        diamond.setMaxFuel(24.0);

        lop = new ArrayList<>();
        lop.add(cessna152);
        lop.add(cessna172);
        lop.add(piper);
        lop.add(cirrus);
        lop.add(diamond);
    }

    public void initializeInstructor() {
        Instructor james = new Instructor();
        james.setName("James Gordon");
        james.setInstrClass("CFII - 4");
        james.setAvails(piperDateTime);
        james.setHourlyRate(70);
        james.setExpYears(2);
        HashSet<String> jr = new HashSet<>();
        jr.add("IFR");
        jr.add("VFR");
        jr.add("Multi");
        james.setRatings(jr);

        Instructor nelly = new Instructor();
        nelly.setName("Nelly Chou");
        nelly.setHourlyRate(72);
        nelly.setAvails(cessna152DateTime);
        nelly.setInstrClass("CFII - 3");
        nelly.setExpYears(4);
        HashSet<String> nr = new HashSet<>();
        nr.add("IFR");
        nr.add("VFR");
        nr.add("Multi");
        nelly.setRatings(nr);

        Instructor toren = new Instructor();
        toren.setName("Toren Molly");
        toren.setInstrClass("CFI - 4");
        toren.setAvails(cessna172DateTime);
        toren.setHourlyRate(70);
        toren.setExpYears(1);
        HashSet<String> tr = new HashSet<>();
        tr.add("VFR");
        tr.add("Multi");
        toren.setRatings(tr);

        Instructor ash = new Instructor();
        ash.setAvails(diamondDateTime);
        ash.setExpYears(5);
        ash.setHourlyRate(80);
        ash.setName("Ash Salem");
        ash.setInstrClass("CFII - 2");
        HashSet<String> ar = new HashSet<>();
        ar.add("VFR");
        ar.add("IFR");
        ar.add("Multi");
        ash.setRatings(ar);

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

        loi = new ArrayList<>();
        loi.add(james);
        loi.add(toren);
        loi.add(nelly);
        loi.add(ash);
        loi.add(zor);

    }

}
