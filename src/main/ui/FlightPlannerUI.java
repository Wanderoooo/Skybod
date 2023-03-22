package ui;

import model.Booking;
import model.Pilot;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.io.*;

// Represents a gui flight planner application, allows user to book, cancel booking, save, and check weather.

// CREDIT: CREDIT: json implementation code template from WorkRoomApp
// from https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git

public class FlightPlannerUI extends JFrame {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private JFrame frame;
    private JPanel cards;
    private Pilot pilot;
    private JTextField nameText;
    private JTextField mednumText;
    private JTextField ratingsText;
    private JToggleButton studentStat;
    private JLabel taf;
    private JLabel metar;
    private JTextField tx;
    private JScrollPane bookingsScroll;
    private JTextField dayText;
    private JToggleButton toggle;
    private JTextField timeText;
    private JTextField acTypeText;
    private JTextField instrText;
    private JList<JLabel> labelList;
    private DefaultListModel bookedLabels;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private static final String JSON_STORE = "./data/pilot.json";

    // EFFECT: sets up a gui Flight Planner application
    public FlightPlannerUI() {
        pilot = new Pilot();
        bookedLabels = new DefaultListModel();
        frame = new JFrame();
        frame.setSize(WIDTH, HEIGHT);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setTitle("Skybod Flight Planner");
        frame.setVisible(true);
        frame.addWindowListener(new SaveProgress());

        setUpDisplay();
    }

    // MODIFIES: this, cards, frame
    // EFFECT: adds splash page to frame
    private void setUpDisplay() {
        JPanel splashPage = setUpSplash();
        JPanel firstPage = getFirstPage();
        setupCards(splashPage, firstPage);

        frame.add(cards);
        frame.pack();
    }

    // MODIFIES: this, cards
    // EFFECT: adds splash page to cards & mouse click listener, returns it
    private void setupCards(JPanel splashPage, JPanel firstPage) {
        cards = new JPanel(new CardLayout());
        MouseListener m = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                cards.remove(0);
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        };

        cards.addMouseListener(m);
        cards.add(firstPage, "firstPage");
        cards.add(splashPage, "splashPage");
    }

    // MODIFIES: this, cards
    // EFFECT: adds splash page to cards & mouse click listener, returns it
    private JPanel getFirstPage() {
        JPanel firstPage = new JPanel();
        try {
            BufferedImage image = ImageIO.read(new File("./data/Skybod.png"));
            JLabel label = new JLabel(new ImageIcon(image));
            firstPage.add(label);
        } catch (Exception e) {
            System.exit(0);
        }

        return firstPage;
    }

    // MODIFIES: this
    // EFFECT: creates and returns new booking page
    private JPanel createBook() {
        JPanel book = new JPanel(new GridLayout(6, 2));
        JLabel title = new JLabel("New Booking");
        title.setFont(new Font("Serif", Font.BOLD, 40));
        JPanel titlePanel = new JPanel();
        titlePanel.add(title);

        JPanel dayTimePanel = createDayTimePanel();
        JPanel instrFlightGround = createInstrPanel();
        JPanel bookButtonPanel = createBookButton();

        book.add(titlePanel);
        book.add(new JPanel());
        book.add(dayTimePanel);
        book.add(instrFlightGround);
        book.add(bookButtonPanel);

        return book;
    }

    // MODIFIES: this
    // EFFECT: creates buttons for booking confirmation, returns them on a panel
    private JPanel createBookButton() {
        JButton confirm = new JButton("Confirm & Book");
        ActionListener a = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Booking b = makeNewBooking();

                String typeOfLesson;
                if (toggle.isSelected()) {
                    typeOfLesson = "FLIGHT";
                } else {
                    typeOfLesson = "GROUND";
                }

                b.setTypeOfLesson(typeOfLesson);
                pilot.addBooking(b);
                updateBookingScroll(b);
                CardLayout cl = (CardLayout) (cards.getLayout());
                cl.show(cards, "menu");
            }
        };

        confirm.addActionListener(a);
        JPanel bookButtonPanel = new JPanel();
        bookButtonPanel.add(confirm);
        return bookButtonPanel;
    }

    // MODIFIES: booking
    // EFFECT: create and returns new booking object with JTextField input info
    private Booking makeNewBooking() {
        Booking b = new Booking();
        b.getPlane().setType(acTypeText.getText());
        b.setDayBooked(dayText.getText());
        b.setTimeBooked(timeText.getText());
        b.getInstructor().setName(instrText.getText());
        return b;
    }

    // EFFECT: creates and returns instructor & plane booking page
    private JPanel createInstrPanel() {
        JPanel instrFlightGround = new JPanel();
        toggle = lessonTypeButton();

        acTypeText = new JTextField(15);
        instrText = new JTextField(15);
        JLabel instrName = new JLabel("Instructor name:");
        JLabel acTypeTx = new JLabel("Aircraft type:");

        instrFlightGround.add(toggle);
        instrFlightGround.add(new JPanel());
        instrFlightGround.add(acTypeTx);
        instrFlightGround.add(acTypeText);
        instrFlightGround.add(new JPanel());
        instrFlightGround.add(instrName);
        instrFlightGround.add(instrText);
        return instrFlightGround;
    }

    // EFFECT: creates and returns day time input panel
    private JPanel createDayTimePanel() {
        JPanel dayTimePanel = new JPanel();
        JLabel day = new JLabel("Day:");
        JLabel time = new JLabel("Time:");
        dayText = new JTextField(15);
        timeText = new JTextField(15);
        dayTimePanel.add(day);
        dayTimePanel.add(dayText);
        dayTimePanel.add(new JPanel());
        dayTimePanel.add(time);
        dayTimePanel.add(timeText);
        return dayTimePanel;
    }

    // EFFECT: creates and returns flight or ground lesson toggle button
    private JToggleButton lessonTypeButton() {
        JToggleButton toggle = new JToggleButton("FLIGHT LESSON", true);
        toggle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (toggle.isSelected()) {
                    toggle.setText("FLIGHT LESSON");
                } else {
                    toggle.setText("GROUND LESSON");
                }
            }
        });
        return toggle;
    }

    // EFFECT: creates and returns day time input panel
    private JPanel setUpSplash() {
        JPanel headerPanel = new JPanel();
        JLabel title = new JLabel("Welcome to Skybod Flight Planner");
        title.setFont(new Font("Serif", Font.BOLD, 40));
        headerPanel.add(title);

        JPanel buttonPanel = new JPanel();
        GridLayout gl = new GridLayout(1, 4);
        gl.setHgap(20);
        buttonPanel.setLayout(gl);
        JButton reg = makeRegButton();
        JButton cont = makeContButton();
        buttonPanel.add(new JPanel());
        buttonPanel.add(reg);
        buttonPanel.add(cont);
        buttonPanel.add(new JPanel());

        JPanel regContPanel = addToSplashPanel(headerPanel, buttonPanel);

        return regContPanel;

    }

    // EFFECT: creates and returns continue button
    private JButton makeContButton() {
        JButton cont = new JButton("Continue from last saved");
        ActionListener contAction = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                loadFromLastSaved();
                JTabbedPane menu = createTabbedPane();
                JPanel book = createBook();
                cards.add(menu, "menu");
                cards.add(book, "book");
                CardLayout cl = (CardLayout) (cards.getLayout());
                cl.show(cards, "menu");
            }
        };

        cont.addActionListener(contAction);
        return cont;
    }

    // MODIFIES: this
    // EFFECT: load data from last saved progress
    private void loadFromLastSaved() {
        try {
            jsonReader = new JsonReader(JSON_STORE);
            pilot = jsonReader.read();

            for (Booking b : pilot.getBookings()) {
                bookedLabels.addElement(b.getDayBooked() + " at " + b.getTimeBooked()
                        + " for " + b.getPlane().getType() + " with instructor " + b.getInstructor().getName()
                        + " for " + b.getTypeOfLesson() + " lesson ");
            }

        } catch (IOException ex) {
            int option2 = JOptionPane.showOptionDialog(
                    FlightPlannerUI.this,
                    "Unable to load from: " + JSON_STORE,
                    "Error", JOptionPane.OK_OPTION,
                    JOptionPane.ERROR_MESSAGE, null, null,
                    null);
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    // EFFECT: creates and returns registration buttone
    private JButton makeRegButton() {
        JButton reg = new JButton("Register");
        ActionListener regAction = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JPanel regPage = regUserWindow();
                JTabbedPane menu = createTabbedPane();
                JPanel book = createBook();
                cards.add(regPage, "regPage");
                cards.add(menu, "menu");
                cards.add(book, "book");
                CardLayout cl = (CardLayout) (cards.getLayout());
                cl.show(cards, "regPage");
            }
        };

        reg.addActionListener(regAction);

        return reg;
    }

    // EFFECT: creates start page and returns it
    private JPanel addToSplashPanel(JPanel headerPanel, JPanel buttonPanel) {
        JPanel regContPanel = new JPanel();
        regContPanel.setLayout(new GridLayout(10, 1));
        regContPanel.add(new JPanel());
        regContPanel.add(new JPanel());
        regContPanel.add(new JPanel());
        regContPanel.add(headerPanel);
        regContPanel.add(new JPanel());
        regContPanel.add(buttonPanel);
        return regContPanel;
    }

    // EFFECT: creates registration page and returns it
    private JPanel regUserWindow() {
        JPanel regUser = new JPanel();
        regUser.setLayout(new GridLayout(4, 1));

        JPanel titlePanel = new JPanel();
        JLabel title = new JLabel("Complete your info");
        title.setFont(new Font("Serif", Font.BOLD, 30));
        titlePanel.add(title);
        JPanel formPanel = makeFormPanel();
        JPanel nextPanel = makeNextPanel();

        regUser.add(titlePanel);
        regUser.add(formPanel);
        regUser.add(new JPanel());
        regUser.add(nextPanel);

        return regUser;

    }

    // EFFECT: initiates menu and saves user input on a panel, and returns it
    private JPanel makeNextPanel() {
        JPanel nextPanel = new JPanel();
        JButton submit = new JButton("Submit");
        ActionListener goToMenu = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setPilotFieldsExceptMednum();
                try {
                    pilot.setMedNum(Integer.parseInt(mednumText.getText()));
                    JTabbedPane menu = createTabbedPane();
                    JPanel book = createBook();
                    cards.add(menu, "menu");
                    cards.add(book, "book");
                    CardLayout cl = (CardLayout) (cards.getLayout());
                    cl.show(cards, "menu");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frame, "Please enter a valid medical number");
                }
            }
        };

        submit.addActionListener(goToMenu);
        nextPanel.add(submit);

        return nextPanel;
    }

    // EFFECT: sets pilot's name, ratings, and student status based on user input
    private void setPilotFieldsExceptMednum() {
        pilot.setName(nameText.getText());
        pilot.addRating(ratingsText.getText());
        pilot.setStudent(studentStat.isSelected());
    }

    // EFFECT: creates registration form panel and returns it
    private JPanel makeFormPanel() {
        JPanel formPanel = new JPanel(new GridLayout(4, 2));
        JPanel title1 = createTitle1Panel();
        formPanel.add(title1);

        JPanel textbox1 = createTextbox1Panel();
        formPanel.add(textbox1);

        JPanel title2 = createTitle2Panel();
        formPanel.add(title2);

        JPanel textbox2 = createTextbox2Panel();
        formPanel.add(textbox2);

        return formPanel;
    }

    // EFFECT: creates registration textbox 2 panel and returns it
    private JPanel createTextbox2Panel() {
        JPanel textbox2 = new JPanel(new GridLayout(1, 2));
        studentStat = new JToggleButton("Student");
        ratingsText = new JTextField(10);
        textbox2.add(new JPanel());
        textbox2.add(ratingsText);
        textbox2.add(new JPanel());
        textbox2.add(studentStat);
        textbox2.add(new JPanel());
        return textbox2;
    }

    // EFFECT: creates registration title 2 panel and returns it
    private JPanel createTitle2Panel() {
        JPanel title2 = new JPanel(new GridLayout(1, 2));
        JLabel ratings = new JLabel("Enter your ratings");
        title2.add(new JPanel());
        title2.add(ratings);
        title2.add(new JPanel());
        title2.add(new JPanel());
        return title2;
    }

    // EFFECT: creates registration textbox 1 panel and returns it
    private JPanel createTextbox1Panel() {
        JPanel textbox1 = new JPanel(new GridLayout(1, 2));
        nameText = new JTextField(10);
        mednumText = new JTextField(10);
        textbox1.add(new JPanel());
        textbox1.add(nameText);
        textbox1.add(new JPanel());
        textbox1.add(mednumText);
        textbox1.add(new JPanel());
        return textbox1;
    }

    // EFFECT: creates registration title 1 panel and returns it
    private JPanel createTitle1Panel() {
        JPanel title1 = new JPanel(new GridLayout(1, 2));
        JLabel name = new JLabel("Full name");
        JLabel mednum = new JLabel("Medical number");
        title1.add(new JPanel());
        title1.add(name);
        title1.add(new JPanel());
        title1.add(mednum);
        title1.add(new JPanel());
        return title1;
    }

    // EFFECT: creates menu tabbed pane and returns it
    private JTabbedPane createTabbedPane() {
        JTabbedPane tp = new JTabbedPane();
        tp.setBounds(WIDTH / 2, HEIGHT / 2, WIDTH, HEIGHT);
        JPanel pilotInfo = makePilotInfo();
        JPanel bookingInfo = makeBookingPage();
        JPanel weatherInfo = makeWeatherPage();

        tp.add("Weather", weatherInfo);
        tp.add("Bookings", bookingInfo);
        tp.add("Pilot Info", pilotInfo);

        return tp;
    }

    // EFFECT: creates pilot info panel and returns it
    private JPanel makePilotInfo() {
        JPanel pilotInfoPage = new JPanel(new GridLayout(5, 1));
        JLabel title = new JLabel("Pilot Information");
        JPanel titlePanel = new JPanel();
        titlePanel.add(title);
        title.setFont(new Font("Serif", Font.BOLD, 40));
        JPanel makeNameMednumPanel = makeNameMednumPanel();
        JPanel makeRatingStudent = makeRatingStudent();

        pilotInfoPage.add(titlePanel);
        pilotInfoPage.add(makeNameMednumPanel);
        pilotInfoPage.add(makeRatingStudent);
        pilotInfoPage.add(new JPanel());
        pilotInfoPage.add(new JPanel());

        return pilotInfoPage;
    }

    // EFFECT: creates pilot info student status & ratings panel and returns it
    private JPanel makeRatingStudent() {
        JPanel returnPage = new JPanel();
        JLabel rating = new JLabel("Your Rating:");
        rating.setFont(new Font("Serif", Font.BOLD, 20));

        String rate = "";

        for (String r : pilot.getRatings()) {
            rate = r;
        }

        JLabel getRating = new JLabel(rate);
        getRating.setFont(new Font("Serif", Font.PLAIN, 20));
        JLabel student = new JLabel("Student Status:");
        student.setFont(new Font("Serif", Font.BOLD, 20));
        JLabel getStudentStatus = new JLabel(String.valueOf(pilot.getStudentStatus()));
        getStudentStatus.setFont(new Font("Serif", Font.PLAIN, 20));

        returnPage.add(rating);
        returnPage.add(getRating);
        returnPage.add(new JPanel());
        returnPage.add(student);
        returnPage.add(getStudentStatus);

        return returnPage;
    }

    // EFFECT: creates pilot info name and medical number panel and returns it
    private JPanel makeNameMednumPanel() {
        JPanel returnPage = new JPanel();
        JLabel name = new JLabel("Pilot Name:");
        name.setFont(new Font("Serif", Font.BOLD, 20));
        JLabel getName = new JLabel(pilot.getName());
        getName.setFont(new Font("Serif", Font.PLAIN, 20));

        JLabel med = new JLabel("Medical Number:");
        med.setFont(new Font("Serif", Font.BOLD, 20));
        JLabel getMednum = new JLabel(String.valueOf(pilot.getMedNum()));
        getMednum.setFont(new Font("Serif", Font.PLAIN, 20));

        returnPage.add(name);
        returnPage.add(getName);
        returnPage.add(new JPanel());
        returnPage.add(med);
        returnPage.add(getMednum);

        return returnPage;
    }

    // EFFECT: creates weather panel and returns it
    private JPanel makeWeatherPage() {
        JPanel wx = new JPanel(new GridLayout(5, 1));
        JPanel titlePanel = createWxTitle();
        JPanel tafPanel = createTafPanel();
        JPanel metarPanel = createMetarPanel();

        JPanel enter = new JPanel();
        tx = new JTextField(10);
        JLabel label = new JLabel("Enter Airport Code:");
        enter.add(label);
        enter.add(tx);

        JPanel buttons = new JPanel();
        JButton checkTaf = updateTafButton();
        JButton checkMetar = updateMetarButton();
        buttons.add(checkTaf);
        buttons.add(checkMetar);

        wx.add(titlePanel);
        wx.add(tafPanel);
        wx.add(metarPanel);
        wx.add(enter);
        wx.add(buttons);

        return wx;
    }

    // EFFECT: creates metar update button and returns it
    private JButton updateMetarButton() {
        JButton updateMetar = new JButton("Update Metar");
        ActionListener a = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pilot.getWx().metarUpdate(tx.getText().toUpperCase());
                metar.setText("METAR: " + "\n" + pilot.getWx().getCurrentMetar());
            }
        };

        updateMetar.addActionListener(a);

        return updateMetar;
    }

    // EFFECT: creates taf update button and returns it
    private JButton updateTafButton() {
        JButton checkTaf = new JButton("Update Taf");
        ActionListener a = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pilot.getWx().tafUpdate(tx.getText().toUpperCase());
                taf.setText("TAF: " + "\n" + pilot.getWx().getCurrentTaf());
            }
        };

        checkTaf.addActionListener(a);
        return checkTaf;
    }

    // EFFECT: creates taf text panel and returns it
    private JPanel createTafPanel() {
        taf = new JLabel("TAF: " + "\n" + pilot.getWx().getCurrentTaf());
        taf.setFont(new Font("Serif", Font.BOLD, 15));
        JPanel tafPanel = new JPanel();
        tafPanel.add(taf);

        return tafPanel;
    }

    // EFFECT: creates metar text panel and returns it
    private JPanel createMetarPanel() {
        metar = new JLabel("METAR: " + "\n" + pilot.getWx().getCurrentMetar());
        metar.setFont(new Font("Serif", Font.BOLD, 15));
        JPanel metarPanel = new JPanel();
        metarPanel.add(metar);

        return metarPanel;
    }

    // EFFECT: creates weather panel title panel and returns it
    private JPanel createWxTitle() {
        JLabel title = new JLabel("Last Checked Weather Report");
        JPanel titlePanel = new JPanel();
        titlePanel.add(title);
        title.setFont(new Font("Serif", Font.BOLD, 40));
        return titlePanel;
    }

    // EFFECT: creates booking page panel and returns it
    private JPanel makeBookingPage() {
        JPanel booking = new JPanel(new GridLayout(3, 1));
        JLabel title = new JLabel("Your Bookings");
        JPanel titlePanel = new JPanel();
        titlePanel.add(title);
        title.setFont(new Font("Serif", Font.BOLD, 40));

        labelList = new JList(bookedLabels);
        labelList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        labelList.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        labelList.setVisibleRowCount(100);
        labelList.setFont(new Font("Serif", Font.BOLD, 15));
        bookingsScroll = new JScrollPane(labelList);

        JButton bookNew = getBookNewButton();
        JButton cancel = getCancelButton();


        JPanel buttons = new JPanel();
        buttons.add(bookNew);
        buttons.add(cancel);

        booking.add(titlePanel);
        booking.add(bookingsScroll);
        booking.add(buttons);

        return booking;
    }

    // EFFECT: creates cancel booking button and returns it
    private JButton getCancelButton() {
        JButton cancel = new JButton("Cancel Booking");
        ActionListener a = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = labelList.getSelectedIndex();
                bookedLabels.remove(index);
                pilot.getBookings().remove(index);
            }
        };

        cancel.addActionListener(a);
        return cancel;
    }

    // EFFECT: update booking scroll pane; add given booking
    private void updateBookingScroll(Booking b) {
        bookedLabels.addElement(b.getDayBooked() + " at " + b.getTimeBooked()
                + " for " + b.getPlane().getType() + " with instructor " + b.getInstructor().getName()
                + " for " + b.getTypeOfLesson() + " lesson ");
    }

    // EFFECT: creates "create new booking" button and returns it
    private JButton getBookNewButton() {
        JButton bookNew = new JButton("Create New Booking");
        ActionListener next = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout) (cards.getLayout());
                cl.show(cards, "book");
            }
        };

        bookNew.addActionListener(next);

        return bookNew;
    }

    // Progress saver, which initialize pop up window of save progress option
    private class SaveProgress extends WindowAdapter {

        // EFFECT: window listener, initialize save progress dialogue box when event detected
        public void windowClosing(WindowEvent e) {
            int option = JOptionPane.showOptionDialog(
                    FlightPlannerUI.this,
                    "Would you like to save your progress?", "Exit Dialog", JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE, null, null,
                    null);

            if (option == JOptionPane.YES_OPTION) {
                try {
                    jsonWriter = new JsonWriter(JSON_STORE);
                    jsonWriter.open();
                    jsonWriter.write(pilot);
                    jsonWriter.close();
                } catch (FileNotFoundException ex) {
                    int option2 = JOptionPane.showOptionDialog(
                            FlightPlannerUI.this,
                            "Unable to write to file:" + JSON_STORE,
                            "Error", JOptionPane.OK_OPTION,
                            JOptionPane.ERROR_MESSAGE, null, null,
                            null);
                }

                System.exit(0);
            } else {
                System.exit(0);
            }
        }
    }

}
