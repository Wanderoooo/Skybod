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
    private String quit;

    // EFFECT: create flight planner based on user input
    public FlightPlanner() {
        Scanner name = new Scanner(System.in);
        Scanner rating = new Scanner(System.in);
        Scanner add = new Scanner(System.in);
        Scanner mednum = new Scanner(System.in);
        Scanner isStud = new Scanner(System.in);
        Scanner options = new Scanner(System.in);

        Pilot pilot = new Pilot();
        System.out.println("Welcome to Skybod Aviation! Let's get you set up:");
        System.out.println("Enter your full name");
        pilot.setName(name.next());

        System.out.println("What are your ratings? - VFR, IFR, Multi, Float");
        String choice = "add";

        while (choice.equals("add")) {
            pilot.getRatings().add(rating.next()); // how to add ratings without designing arb. # scanners
            System.out.println("type 'add' to add more ratings, any other key to continue");
            choice = add.next();
        }

        System.out.println("Enter your TC medical number");
        pilot.setMedNum(mednum.nextInt());

        System.out.println("Are you a student? "
                + "Student - enter true"
                + "Otherwise - enter false");

        pilot.setStudent(isStud.nextBoolean());

        System.out.println("Great! You are all set up!"
                + "To manage booking - 'BOOKING' "
                + "To check weather - 'WX' "
                + "To preflight - 'PREFLIGHT' "
                + "To postflight - 'POSTFLIGHT' ");

        quit = options.next().toUpperCase();

        while (!quit.equals("QUIT")) {
            switch (quit) {
                case BOOKING:
                    // stub
                    break;
                case WX:
                    // stub
                    break;
                case PREFLIGHT:
                    // stub
                    break;
                case POSTFLIGHT:
                    // stub
                    break;
                default:
                    System.out.println("That was not a valid option, please enter an option below:"
                            + "To manage booking - 'BOOKING' "
                            + "To check weather - 'WX' "
                            + "To preflight - 'PREFLIGHT' "
                            + "To postflight - 'POSTFLIGHT' ");

                    quit = options.next().toUpperCase();
            }
        }
    }
}
