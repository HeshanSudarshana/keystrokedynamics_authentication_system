import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
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

    private Integer[] avgPressingDurations;
    private Integer[] avgPressingDifferences;

    public signup_form(JFrame parent, HashMap userList, ArrayList finalPressingDifferences, ArrayList finalPressingDurations, ArrayList passwords, ArrayList pressedKeys, ArrayList releasedKeys) {

        signupListener = new KeyPressListener();
        signupListener1 = new KeyPressListener();
        signupListener2 = new KeyPressListener();
        input_signup_text.addKeyListener(signupListener);
        reenter1_signup_text.addKeyListener(signupListener1);
        reenter2_signup_text.addKeyListener(signupListener2);
        status_signup_label.setText("");

        setMinimumSize(new Dimension(500, 500));
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setContentPane(signup_panel);
        setLocation(100,100);
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
                if (username_signup_text.getText().isEmpty()) {
                    status_signup_label.setText("Please Enter the username");
                } else if(input_signup_text.getText().isEmpty()) {
                    signupListener.clearArrays();
                    input_signup_text.setText("");
                    status_signup_label.setText("Please Enter the password!");
                } else if(reenter1_signup_text.getText().isEmpty()) {
                    signupListener1.clearArrays();
                    reenter1_signup_text.setText("");
                    status_signup_label.setText("Please Enter the Re-Enter password 1!");
                } else if(reenter2_signup_text.getText().isEmpty()) {
                    signupListener2.clearArrays();
                    reenter2_signup_text.setText("");
                    status_signup_label.setText("Please Enter the Re-Enter password 2!");
                } else if (signupListener.getStr_pressed_keys().contains("Backspace")) {
                    signupListener.clearArrays();
                    input_signup_text.setText("");
                    status_signup_label.setText("Do not enter backspaces while typing the text in the password!");
                } else if(input_signup_text.getText().length()<8) {
                    signupListener.clearArrays();
                    signupListener1.clearArrays();
                    signupListener2.clearArrays();
                    input_signup_text.setText("");
                    reenter1_signup_text.setText("");
                    reenter2_signup_text.setText("");
                    status_signup_label.setText("Password must contain at least 8 characters!");
                } else if (signupListener1.getStr_pressed_keys().contains("Backspace")) {
                    signupListener1.clearArrays();
                    reenter1_signup_text.setText("");
                    status_signup_label.setText("Do not enter backspaces while typing the text in the reenter password 1!");
                } else if (signupListener2.getStr_pressed_keys().contains("Backspace")) {
                    signupListener2.clearArrays();
                    reenter2_signup_text.setText("");
                    status_signup_label.setText("Do not enter backspaces while typing the text in the reenter password 2!");
                } else if (userList.get(username_signup_text.getText()) != null) {
                    signupListener.clearArrays();
                    signupListener1.clearArrays();
                    signupListener2.clearArrays();
                    input_signup_text.setText("");
                    reenter1_signup_text.setText("");
                    reenter2_signup_text.setText("");
                    status_signup_label.setText("Username already exists!");
                } else if(!input_signup_text.getText().equals(reenter1_signup_text.getText()) || !reenter1_signup_text.getText().equals(reenter2_signup_text.getText())) {
                    signupListener1.clearArrays();
                    signupListener2.clearArrays();
                    reenter1_signup_text.setText("");
                    reenter2_signup_text.setText("");
                    status_signup_label.setText("Password mismatch!");
                } else if(!signupListener.getStr_pressed_keys().equals(signupListener1.getStr_pressed_keys()) || !signupListener.getStr_pressed_keys().equals(signupListener2.getStr_pressed_keys())) {
                    System.out.println(signupListener.getStr_pressed_keys());
                    System.out.println(signupListener1.getStr_pressed_keys());
                    System.out.println(signupListener2.getStr_pressed_keys());
                    signupListener.clearArrays();
                    signupListener1.clearArrays();
                    signupListener2.clearArrays();
                    input_signup_text.setText("");
                    reenter1_signup_text.setText("");
                    reenter2_signup_text.setText("");
                    status_signup_label.setText("Pressed key Pattern mismatch!");
                } else if(!signupListener.getStr_released_keys().equals(signupListener1.getStr_released_keys()) || !signupListener.getStr_released_keys().equals(signupListener2.getStr_released_keys())) {
                    signupListener.clearArrays();
                    signupListener1.clearArrays();
                    signupListener2.clearArrays();
                    input_signup_text.setText("");
                    reenter1_signup_text.setText("");
                    reenter2_signup_text.setText("");
                    status_signup_label.setText("Released key Pattern mismatch!");
                } else {
                    try{
                        avgPressingDifferences = avgTime(signupListener.pressedDifferences(), signupListener1.pressedDifferences(), signupListener2.pressedDifferences());
                        avgPressingDurations = avgTime(signupListener.pressedDurations(), signupListener1.pressedDurations(), signupListener2.pressedDurations());

                        userList.put(username_signup_text.getText(), userList.size());
                        finalPressingDifferences.add(avgPressingDifferences);
                        finalPressingDurations.add(avgPressingDurations);
                        passwords.add(input_signup_text.getText());
                        pressedKeys.add(signupListener.getStr_pressed_keys());
                        releasedKeys.add(signupListener.getStr_released_keys());

                        System.out.println(signupListener.pressed_keys);

                        showUserInfo(avgPressingDurations, avgPressingDifferences);

                        status_signup_label.setText("User Successfully Registered to the system!");
                        username_signup_text.setText("");
                        input_signup_text.setText("");
                        reenter1_signup_text.setText("");
                        reenter2_signup_text.setText("");
                        signupListener.clearArrays();
                        signupListener1.clearArrays();
                        signupListener2.clearArrays();

                        System.out.println("Users list --> " + userList);
                        System.out.println("Passwords --> " + passwords);
                        JOptionPane.showMessageDialog(parent, "User successfully registered!");
                        dispose();
                        parent.setEnabled(true);

                    } catch (IndexOutOfBoundsException err) {
                        username_signup_text.setText("");
                        input_signup_text.setText("");
                        reenter1_signup_text.setText("");
                        reenter2_signup_text.setText("");
                        signupListener.clearArrays();
                        signupListener1.clearArrays();
                        signupListener2.clearArrays();
                        status_signup_label.setText("Please Enter the details again!");
                    }

                }
            }
        });
        resetSignupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                username_signup_text.setText("");
                input_signup_text.setText("");
                reenter1_signup_text.setText("");
                reenter2_signup_text.setText("");
                signupListener.clearArrays();
                signupListener1.clearArrays();
                signupListener2.clearArrays();
            }
        });
    }
    public Integer[] avgTime(Integer[] list1, Integer[] list2, Integer[] list3) {
        Integer [] temp = new Integer[list1.length];
        for (int i=0; i<list1.length; i++) {
            temp[i] = (list1[i]+list2[i]+list3[i])/3;
        }
        return temp;
    }

    public void showUserInfo(Integer[] durations, Integer[] differences) {
        System.out.print("Key Pressed durations --> ");
        for(int i=0; i<durations.length; i++) {
            if(i!=durations.length-1) {
                System.out.print(durations[i]+", ");
            } else {
                System.out.print(durations[i]+"\n");
            }
        }
        System.out.print("Key Pressed differences --> ");
        for(int i=0; i<differences.length; i++) {
            if(i!=differences.length-1) {
                System.out.print(differences[i]+", ");
            } else {
                System.out.print(differences[i]+"\n");
            }
        }
    }

    public Integer[] getAvgPressingDurations() {
        return avgPressingDurations;
    }

    public void setAvgPressingDurations(Integer[] avgPressingDurations) {
        this.avgPressingDurations = avgPressingDurations;
    }

    public Integer[] getAvgPressingDifferences() {
        return avgPressingDifferences;
    }

    public void setAvgPressingDifferences(Integer[] avgPressingDifferences) {
        this.avgPressingDifferences = avgPressingDifferences;
    }
}


