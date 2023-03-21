package ui;

// Represents main class, where entire program is run.

import model.Booking;
import model.Pilot;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Main extends JFrame {
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

    public Main() {
        pilot = new Pilot();
        bookedLabels = new DefaultListModel();
        frame = new JFrame();
        frame.setSize(WIDTH, HEIGHT);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setTitle("Skybod Flight Planner");
        frame.setVisible(true);
        frame.setLayout(new BorderLayout());
        frame.addWindowListener(new AreYouSure());

        setUpDisplay();
    }

    private void setUpDisplay() {
        JPanel splashPage = setUpSplash();
        cards = new JPanel(new CardLayout());
        cards.add(splashPage, "splashPage");

        frame.add(cards);
        frame.pack();
    }

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

    private Booking makeNewBooking() {
        Booking b = new Booking();
        b.getPlane().setType(acTypeText.getText());
        b.setDayBooked(dayText.getText());
        b.setTimeBooked(timeText.getText());
        b.getInstructor().setName(instrText.getText());
        return b;
    }

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

    private JPanel setUpSplash() {
        JPanel headerPanel = new JPanel();
        JLabel title = new JLabel("Welcome to Skybod Flight Planner");
        title.setForeground(Color.BLACK);
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
                    Main.this,
                    "Unable to load from: " + JSON_STORE,
                    "Error", JOptionPane.OK_OPTION,
                    JOptionPane.ERROR_MESSAGE, null, null,
                    null);
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

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

    private void setPilotFieldsExceptMednum() {
        pilot.setName(nameText.getText());
        pilot.addRating(ratingsText.getText());
        pilot.setStudent(studentStat.isSelected());
    }

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

    private JPanel createTitle2Panel() {
        JPanel title2 = new JPanel(new GridLayout(1, 2));
        JLabel ratings = new JLabel("Enter your ratings");
        title2.add(new JPanel());
        title2.add(ratings);
        title2.add(new JPanel());
        title2.add(new JPanel());
        return title2;
    }

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

    private JTabbedPane createTabbedPane() {
        JTabbedPane tp = new JTabbedPane();
        tp.setBounds(WIDTH / 2, HEIGHT / 2, WIDTH, HEIGHT);
        JPanel pilotInfo = makePilotInfo();
        JPanel bookingInfo = makeBookingPage();
        JPanel weatherInfo = makeWeatherPage();
        // JPanel exitInfo = makeExitPage();

        tp.add("Weather", weatherInfo);
        tp.add("Bookings", bookingInfo);
        tp.add("Pilot Info", pilotInfo);
        // tp.add("Exit", exitInfo);

        return tp;
    }

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

    private JButton updateMetarButton() {
        JButton updateMetar = new JButton("Update Metar");
        ActionListener a = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pilot.getWx().metarUpdate(tx.getText().toUpperCase());
                JTabbedPane menu = createTabbedPane();
                menu.getComponentAt(2);
                cards.remove(3);
                cards.add(menu, "menu");
                CardLayout cl = (CardLayout) (cards.getLayout());
                cl.show(cards, "menu");
            }
        };

        updateMetar.addActionListener(a);

        return updateMetar;
    }

    private JButton updateTafButton() {
        JButton checkTaf = new JButton("Update Taf");
        ActionListener a = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pilot.getWx().tafUpdate(tx.getText().toUpperCase());
                JTabbedPane menu = createTabbedPane();
                menu.getComponentAt(2);
                cards.remove(3);
                cards.add(menu, "menu");
                CardLayout cl = (CardLayout) (cards.getLayout());
                cl.show(cards, "menu");
            }
        };

        checkTaf.addActionListener(a);
        return checkTaf;
    }

    private JPanel createTafPanel() {
        taf = new JLabel("TAF: " + "\n" + pilot.getWx().getCurrentTaf());
        taf.setFont(new Font("Serif", Font.BOLD, 15));
        JPanel tafPanel = new JPanel();
        tafPanel.add(taf);

        return tafPanel;
    }

    private JPanel createMetarPanel() {
        metar = new JLabel("METAR: " + "\n" + pilot.getWx().getCurrentMetar());
        metar.setFont(new Font("Serif", Font.BOLD, 15));
        JPanel metarPanel = new JPanel();
        metarPanel.add(metar);

        return metarPanel;
    }

    private JPanel createWxTitle() {
        JLabel title = new JLabel("Last Checked Weather Report");
        JPanel titlePanel = new JPanel();
        titlePanel.add(title);
        title.setFont(new Font("Serif", Font.BOLD, 40));
        return titlePanel;
    }

    private JPanel makeExitPage() {
        JPanel exit = new JPanel(new GridLayout(4, 1));
        JLabel title = new JLabel("Thanks for using Skybod Flight Planner!");
        JPanel titlePanel = new JPanel();
        titlePanel.add(title);

        title.setFont(new Font("Serif", Font.BOLD, 40));
        JLabel footer = new JLabel("We hope to see you again!");
        footer.setFont(new Font("Serif", Font.BOLD, 30));
        JLabel isSave = new JLabel("Would you like to save your progress?");
        isSave.setFont(new Font("Serif", Font.BOLD, 30));

        JPanel isSavePanel = new JPanel();
        isSavePanel.add(isSave);
        JPanel footerPanel = new JPanel();
        footerPanel.add(footer);

        JButton yes = new JButton("Yes");
        JButton no = new JButton("No");

        JPanel buttons = new JPanel();
        buttons.add(yes);
        buttons.add(no);

        exit.add(titlePanel);
        exit.add(isSavePanel);
        exit.add(buttons);
        exit.add(footerPanel);

        return exit;
    }

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

    private void updateBookingScroll(Booking b) {
        bookedLabels.addElement(b.getDayBooked() + " at " + b.getTimeBooked()
                + " for " + b.getPlane().getType() + " with instructor " + b.getInstructor().getName()
                + " for " + b.getTypeOfLesson() + " lesson ");
    }

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

    private class AreYouSure extends WindowAdapter {
        public void windowClosing(WindowEvent e) {
            int option = JOptionPane.showOptionDialog(
                    Main.this,
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
                            Main.this,
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

    public static void main(String[] args) {
        new Main();
    }

}
