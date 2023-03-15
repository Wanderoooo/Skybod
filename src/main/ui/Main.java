package ui;

// Represents main class, where entire program is run.

import model.Pilot;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Main extends JFrame {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static final String FILE_DESCRIPTOR = "...file";
    private static final String SCREEN_DESCRIPTOR = "...screen";
    private Pilot pilot;
    private JComboBox<String> printCombo;
    private JDesktopPane desktop;
    private JInternalFrame controlPanel;

    public Main() {
        pilot = new Pilot();

        desktop = new JDesktopPane();
        desktop.addMouseListener(new DesktopFocusAction());

        setContentPane(desktop);
        setTitle("Skybod Flight Planner");
        setSize(WIDTH, HEIGHT);

        addButtonPanel();
        addMenu();
        addKeyPad();
        addAlarmDisplayPanel();

        Remote r = new Remote(ac);
        addRemote(r);

        controlPanel.pack();
        controlPanel.setVisible(true);
        desktop.add(controlPanel);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        centreOnScreen();
        setVisible(true);
    }

    /**
     * Adds menu bar.
     */
    private void addMenu() {
        JMenuBar menuBar = new JMenuBar();
        JMenu sensorMenu = new JMenu("Pilot Info");
        sensorMenu.setMnemonic('P');
        addMenuItem(sensorMenu, new PilotInfo(),
                KeyStroke.getKeyStroke("control S"));
        menuBar.add(sensorMenu);

        JMenu codeMenu = new JMenu("Code");
        codeMenu.setMnemonic('C');
        addMenuItem(codeMenu, new AddCodeAction(), null);
        addMenuItem(codeMenu, new RemoveCodeAction(), null);
        menuBar.add(codeMenu);

        JMenu systemMenu = new JMenu("System");
        systemMenu.setMnemonic('y');
        addMenuItem(systemMenu, new ArmAction(),
                KeyStroke.getKeyStroke("control A"));
        addMenuItem(systemMenu, new DisarmAction(),
                KeyStroke.getKeyStroke("control D"));
        menuBar.add(systemMenu);

        setJMenuBar(menuBar);
    }

    /**
     * Adds an item with given handler to the given menu
     * @param theMenu  menu to which new item is added
     * @param action   handler for new menu item
     * @param accelerator    keystroke accelerator for this menu item
     */
    private void addMenuItem(JMenu theMenu, AbstractAction action, KeyStroke accelerator) {
        JMenuItem menuItem = new JMenuItem(action);
        menuItem.setMnemonic(menuItem.getText().charAt(0));
        menuItem.setAccelerator(accelerator);
        theMenu.add(menuItem);
    }

    private void addButtonPanel() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4,1));
        buttonPanel.add(new JButton(new PilotInfo()));
        buttonPanel.add(new JButton(new MyBookings()));
        buttonPanel.add(new JButton(new MyWeather()));
        buttonPanel.add(new JButton(new Exit()));

        controlPanel.add(buttonPanel, BorderLayout.WEST);
    }

    private class PilotInfo extends AbstractAction {

        PilotInfo() {
            super("Pilot Info");
        }

        @Override
        public void actionPerformed(ActionEvent evt) {
            // TODO
        }
    }

    private class MyBookings extends AbstractAction {

        MyBookings() {
            super("My Bookings");
        }

        @Override
        public void actionPerformed(ActionEvent evt) {
            // TODO
        }
    }

    private class Exit extends AbstractAction {

        Exit() {
            super("Exit");
        }

        @Override
        public void actionPerformed(ActionEvent evt) {
            // TODO
        }
    }

    private class MyWeather extends AbstractAction {

        MyWeather() {
            super("Weather");
        }

        @Override
        public void actionPerformed(ActionEvent evt) {
            // TODO
        }
    }

    /**
     * Represents action to be taken when user clicks desktop
     * to switch focus. (Needed for key handling.)
     */
    private class DesktopFocusAction extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            Main.this.requestFocusInWindow();
        }
    }

    // EFFECT: main method, where a flight planner is initiated
    public static void main(String[] args) {
        new FlightPlanner();

    }
}
