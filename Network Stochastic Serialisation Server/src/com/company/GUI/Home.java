package com.company.GUI;

import com.company.Classes.Main;
import com.company.Controllers.CurrentValueController;
import com.company.Data.Settings;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class handles the GUI form.
 */

public class Home {
    private JTextField txtSteps;
    private JPanel HomePanel;
    private JLabel lblSteps;
    private JTextField txtStrike;
    private JTextField txtStart;
    private JLabel lblStrike;
    private JLabel lblStart;
    private JButton btnStart;
    private JLabel lblError;
    private JLabel lblInfo;
    private JButton Default;

    /**
     * @return Returns the home panel.
     */
    public JPanel getHomePanel() {
        return HomePanel;
    }

    /**
     * This constructor holds the listeners for the two buttons on the form.
     */
    public Home() {
        btnStart.addActionListener(new ActionListener() {

            /**
             * When btnStart is clicked this method is run. The Settings class is updated with values from the GUI.
             * @param e The action event.
             */
            @Override
            public void actionPerformed(ActionEvent e) {

                try{

                    if (txtSteps.getText().equals("")) {
                        Settings.maxSteps = 10000;
                    } else {

                        Settings.maxSteps = Integer.parseInt(txtSteps.getText());

                    }

                    Settings.startValue = Double.parseDouble(txtStart.getText());
                    Settings.strikeValue = Double.parseDouble(txtStrike.getText());

                } catch (Exception ex){

                    System.err.println(ex.getMessage());
                    lblError.setText("All values must be numbers");
                    return;

                }

                start();
            }
        });
        Default.addActionListener(new ActionListener() {

            /**
             * When the default button is pressed this method is run.
             * @param e The action event.
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                Settings.maxSteps = 100;
                Settings.startValue = 10;
                Settings.strikeValue = 11;

                start();
            }
        });
    }

    /**
     * After either button is pressed, this method is called. Text fields are updated and the application execution started.
     */
    public void start(){

        txtSteps.setText(String.valueOf(Settings.maxSteps));
        txtStart.setText(String.valueOf(Settings.startValue));
        txtStrike.setText(String.valueOf(Settings.strikeValue));

        lblError.setText("");
        Main.startTimer();

    }

    /**
     * This method is run after every tick and updates the info label.
     */
    public void update(){

        lblInfo.setText("Current step: " + CurrentValueController.iteration + ", value: " + (CurrentValueController.cv.value));

    }

    /**
     * If the strike value is reached this method is called to update the info label.
     */
    public void reachedStrike(){

        lblInfo.setText("<html><font color='green'>Strike point reached. Final value: " + CurrentValueController.cv.value + " after " + CurrentValueController.iteration + " iterations.</font></html>");

    }

    /**
     * If the max iteration value is reached this method is called to update the info label.
     */
    public void reachedMaxIters(){

        lblInfo.setText("<html><font color='red'>Max iterations reached. Final value: " + CurrentValueController.cv.value + " after " + CurrentValueController.iteration + " iterations.</font></html>");

    }

}
