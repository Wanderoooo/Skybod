package ui;

import model.*;

import java.awt.print.Book;
import java.util.ArrayList;
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
    private String choice;
    private Scanner sc;
    private String quit;

    // EFFECT: create flight planner based on user input
    public FlightPlanner() {
        setUp();

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

            System.out.println("To manage booking - 'BOOKING' "
                    + "To check weather - 'WX' "
                    + "To preflight - 'PREFLIGHT' "
                    + "To postflight - 'POSTFLIGHT' "
                    + "To quit - 'QUIT' ");

            quit = sc.next().toUpperCase();

        }

        System.out.println("Thanks for using Skybod Flight Planner! See you next time!");
    }

    public void flightBook() {
        System.out.println("To book - 'BOOK'"
                + "To cancel existing booking - 'CANCEL'"
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

            System.out.println("To book - 'BOOK'"
                    + "To cancel existing booking - 'CANCEL'"
                    + "To return to menu - 'MENU'");
            choice = sc.next();
        }
    }

    public void setUp() {
        sc = new Scanner(System.in);
        Pilot pilot = new Pilot();

        System.out.println("Welcome to Skybod Aviation! Let's get you set up:");
        System.out.println("Enter your full name");
        String name = sc.next();
        pilot.setName(name);

        System.out.println("What are your ratings? - VFR, IFR, Multi, Float, None");
        choice = "add";
        int n = 2;

        while (choice.equals("add")) {
            // add check user doesn't type random shit
            choice = sc.next();
            pilot.addRating(choice);
            System.out.println("type 'add' to add more ratings, any other key to continue");
            choice = sc.next();

            if (choice.equals("add")) {
                System.out.println("Enter your " + n + "th rating");
                n++;
            }
        }

        System.out.println("Enter your TC medical number");
        int mednum = sc.nextInt();
        pilot.setMedNum(mednum);

        System.out.println("Are you a student? "
                + "Student - enter true\n"
                + "Otherwise - enter false");

        boolean isStud = sc.nextBoolean();
        pilot.setStudent(isStud);
        System.out.println("Great! You are all set up!"
                + "To manage booking - 'BOOKING' "
                + "To check weather - 'WX' "
                + "To preflight - 'PREFLIGHT' "
                + "To postflight - 'POSTFLIGHT' "
                + "To quit - 'QUIT' ");

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
        System.out.println("For ground session - 'GROUND"
                + "For flight lesson - 'FLIGHT'"
                + "To return to previous options - 'PREV' ");

        choice = sc.next();

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

            System.out.println("For ground session - 'GROUND"
                    + "For flight lesson - 'FLIGHT'"
                    + "To return to previous options - 'PREV' ");

            choice = sc.next();
        }
    }

    public void bookFlight() {
        // stub
    }

    public void bookGround() {
        // stub
    }

}
