package ui;

// Represents main class, where entire program is run.

import model.Pilot;
import model.Weather;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static final String FILE_DESCRIPTOR = "...file";
    private static final String SCREEN_DESCRIPTOR = "...screen";
    private JFrame frame;
    private JPanel cards;
    private Pilot pilot;

    public Main() {
        frame = new JFrame();
        frame.setSize(WIDTH, HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Skybod Flight Planner");
        frame.setVisible(true);
        frame.setLayout(new BorderLayout());

        setUpDisplay();
    }

    private void setUpDisplay() {
        JPanel splashPage = setUpSplash();
        JPanel regPage = regUserWindow();
        JTabbedPane menu = createTabbedPane();
        JPanel book = createBook();

        cards = new JPanel(new CardLayout());

        cards.add(splashPage, "splashPage");
        cards.add(regPage, "regPage");
        cards.add(menu, "menu");
        cards.add(book, "book");

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
                CardLayout cl = (CardLayout) (cards.getLayout());
                cl.show(cards, "menu");
            }
        };

        confirm.addActionListener(a);
        JPanel bookButtonPanel = new JPanel();
        bookButtonPanel.add(confirm);
        return bookButtonPanel;
    }

    private JPanel createInstrPanel() {
        JPanel instrFlightGround = new JPanel();
        JToggleButton toggle = lessonTypeButton();

        JTextField acType = new JTextField(15);
        JTextField instr = new JTextField(15);
        JLabel instrName = new JLabel("Instructor name:");
        JLabel acTypeTx = new JLabel("Aircraft type:");

        instrFlightGround.add(toggle);
        instrFlightGround.add(new JPanel());
        instrFlightGround.add(acTypeTx);
        instrFlightGround.add(acType);
        instrFlightGround.add(new JPanel());
        instrFlightGround.add(instrName);
        instrFlightGround.add(instr);
        return instrFlightGround;
    }

    private JPanel createDayTimePanel() {
        JPanel dayTimePanel = new JPanel();
        JLabel day = new JLabel("Day:");
        JLabel time = new JLabel("Time:");
        JTextField dayt = new JTextField(15);
        JTextField timet = new JTextField(15);
        dayTimePanel.add(day);
        dayTimePanel.add(dayt);
        dayTimePanel.add(new JPanel());
        dayTimePanel.add(time);
        dayTimePanel.add(timet);
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
                CardLayout cl = (CardLayout) (cards.getLayout());
                cl.show(cards, "menu");
            }
        };

        cont.addActionListener(contAction);
        return cont;
    }

    private JButton makeRegButton() {
        JButton reg = new JButton("Register");
        ActionListener regAction = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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
                CardLayout cl = (CardLayout) (cards.getLayout());
                cl.show(cards, "menu");
            }
        };

        submit.addActionListener(goToMenu);
        nextPanel.add(submit);

        return nextPanel;
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
        JToggleButton studentStat = new JToggleButton("Student");
        JTextField ratingsText = new JTextField(10);
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
        JTextField nameText = new JTextField(10);
        JTextField mednumText = new JTextField(10);
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
        JPanel exitInfo = makeExitPage();

        tp.add("Pilot Info", pilotInfo);
        tp.add("Bookings", bookingInfo);
        tp.add("Weather", weatherInfo);
        tp.add("Exit", exitInfo);

        return tp;
    }

    private JPanel makePilotInfo() {
        JPanel pi = new JPanel();

        return pi;
    }

    private JPanel makeWeatherPage() {
        JPanel wx = new JPanel(new GridLayout(5, 1));
        JPanel titlePanel = createWxTitle();
        JPanel tafPanel = createTafPanel();
        JPanel metarPanel = createMetarPanel();

        JPanel enter = new JPanel();
        JTextField tx = new JTextField(10);
        JLabel label = new JLabel("Enter Airport Code:");
        enter.add(label);
        enter.add(tx);

        JPanel buttons = new JPanel();
        JButton checkTaf = new JButton("Update Taf");
        JButton checkMetar = new JButton("Update Metar");
        buttons.add(checkTaf);
        buttons.add(checkMetar);

        wx.add(titlePanel);
        wx.add(tafPanel);
        wx.add(metarPanel);
        wx.add(enter);
        wx.add(buttons);

        return wx;
    }

    private JPanel createTafPanel() {
        JLabel taf = new JLabel("TAF: ");
        taf.setFont(new Font("Serif", Font.BOLD, 20));
        JPanel tafPanel = new JPanel();
        tafPanel.add(taf);

        return tafPanel;
    }

    private JPanel createMetarPanel() {
        JLabel metar = new JLabel("METAR: ");
        metar.setFont(new Font("Serif", Font.BOLD, 20));
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

        JScrollPane bookings = new JScrollPane();
        ; // todo

        JButton bookNew = getBookNewButton();
        JButton cancel = new JButton("Cancel Booking");

        JPanel buttons = new JPanel();
        buttons.add(bookNew);
        buttons.add(cancel);

        booking.add(titlePanel);
        booking.add(bookings);
        booking.add(buttons);

        return booking;
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

    public static void main(String[] args) {
        new Main();
    }

}
