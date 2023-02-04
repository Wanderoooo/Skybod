package ui;

import model.*;

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
    private Plane cessna172;
    private Plane cessna152;
    private Plane piper;
    private Plane cirrus;
    private Plane diamond;
    private Instructor i1;
    private Instructor i2;
    private Instructor i3;
    private Instructor i4;
    private Instructor i5;

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
                    System.out.println("That's not a valid option, try again:");
            }

            System.out.println("For ground session - 'GROUND'\n"
                    + "For flight lesson - 'FLIGHT'\n"
                    + "To return to previous options - 'PREV'");

            choice = sc.next().toUpperCase();
        }
    }

    public void bookFlight() {
        // stub
    }

    public void bookGround() {
        // stub
    }

    public void initializePlane() {
        cessna172 = new Plane();
        cessna152 = new Plane();
        piper = new Plane();
        cirrus = new Plane();
        diamond = new Plane();

        cessna172.setType("Cessna-172");
        cessna172.setCallSign("C-GOOV");
        // avail
        cessna172.setHourlyRentalRate(190);
        cessna172.setHourlyFuelRate(40);
        // plane doc
        cessna172.setFuelAmount(25.5);
        cessna172.setMaxFuel(48.0);


        cessna152.setType("Cessna-152");
        cessna152.setCallSign("C-GUUY");
        // avail
        cessna152.setHourlyRentalRate(175);
        cessna152.setHourlyFuelRate(36);
        // plane doc
        cessna152.setFuelAmount(13.5);
        cessna152.setMaxFuel(26.0);



        piper.setType("Piper-Seneca");
        piper.setCallSign("C-FOTX");
        // avail
        piper.setHourlyRentalRate(215);
        piper.setHourlyFuelRate(50);
        // plane doc
        piper.setMaxFuel(98.0);
        piper.setFuelAmount(39.3);



        cirrus.setType("Cirrus-SR22T");
        cirrus.setCallSign("C-CIRR");
        // avail
        cirrus.setHourlyFuelRate(51);
        cirrus.setHourlyRentalRate(212);
        // plane doc
        cirrus.setFuelAmount(40.1);
        cirrus.setMaxFuel(92.0);


        diamond.setType("Diamond-DA40");
        diamond.setCallSign("C-POYL");
        // avail
        diamond.setHourlyRentalRate(200);
        diamond.setHourlyFuelRate(45);
        // plane doc
        diamond.setFuelAmount(10.4);
        diamond.setMaxFuel(24.0);
        
        

        //    private String type;
        //    private String callSign;
        //    private ArrayList<DateTime> avails;
        //    private int hourlyRentalRate;
        //    private int hourlyFuelRate;
        //    private PlaneDocuments pd;
        //    private double fuelAmount;
        //    private double maxFuel;

    }

    public void initializeInstructor() {
        // stub
    }

}
