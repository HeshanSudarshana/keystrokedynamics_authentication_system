import oracle.jrockit.jfr.JFR;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class signup_form extends JFrame {
    private JButton signUpButton;
    private JButton cancelSignupButton;
    private JButton resetSignupButton;
    private JTextField username_signup_text;
    private JTextField input_signup_text;
    private JPanel signup_panel;
    private JLabel status_signup_label;
    private JTextField reenter1_signup_text;
    private JTextField reenter2_signup_text;
    private JLabel label1;
    private JLabel label2;

    private KeyPressListener signupListener;
    private KeyPressListener signupListener1;
    private KeyPressListener signupListener2;

    public signup_form(JFrame parent, HashMap userList, ArrayList pressingDifferences, ArrayList pressingDurations, ArrayList passwords) {

        signupListener = new KeyPressListener();
        input_signup_text.addKeyListener(signupListener);
        reenter1_signup_text.addKeyListener(signupListener1);
        reenter2_signup_text.addKeyListener(signupListener2);
        status_signup_label.setText("");

        setMinimumSize(new Dimension(600, 300));
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setContentPane(signup_panel);
        setLocation(250,250);
        cancelSignupButton.addActionListener(new ButtonClickListener(this) {
            @Override
            public void actionPerformed(ActionEvent e) {
                getPreviousFrame().setVisible(false);
                parent.setEnabled(true);
            }
        });
        signUpButton.addActionListener(new ButtonClickListener(this) {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (username_signup_text.getText().isEmpty() || input_signup_text.getText().isEmpty()) {
                    signupListener.clearArrays();
                    input_signup_text.setText("");
                    status_signup_label.setText("Please Enter the username and the text!");
                } else if (signupListener.getStr_pressed_keys().contains("Backspace")) {
                    signupListener.clearArrays();
                    input_signup_text.setText("");
                    status_signup_label.setText("Do not enter backspaces while typing the text!");
                } else if (userList.get(username_signup_text.getText()) != null) {
                    signupListener.clearArrays();
                    input_signup_text.setText("");
                    status_signup_label.setText("Username already exists!");
                } else if(input_signup_text.getText()!=reenter1_signup_text.getText() || reenter1_signup_text.getText()!=reenter2_signup_text.getText() || input_signup_text.getText()!=reenter2_signup_text.getText()) {

                } else {
                    userList.put(username_signup_text.getText(), userList.size());
                    pressingDifferences.add(signupListener.pressedDifferences());
                    pressingDurations.add(signupListener.pressedDurations());
                    passwords.add(input_signup_text.getText());

                    System.out.println(signupListener.pressed_keys);

                    status_signup_label.setText("User Successfully Registered to the system!");
                    signupListener.printUserInfo();
                    username_signup_text.setText("");
                    input_signup_text.setText("");
                    signupListener.clearArrays();

                    System.out.println("Users list --> " + userList);
                    System.out.println("Passwords --> " + passwords);

                }
            }
        });
        resetSignupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                username_signup_text.setText("");
                input_signup_text.setText("");
                signupListener.clearArrays();
            }
        });
    }
}
