import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

public class login_form extends JFrame {
    private JButton loginButton;
    private JButton clearLoginButton;
    private JButton cancelLoginButton;
    private JTextField username_login_text;
    private JTextField input_login_text;
    private JPanel login_panel;
    private JLabel status_login_label;

    KeyPressListener loginListener;
    Authenticator authenticator;
    private Integer[] loginPressingDifferences;
    private Integer[] loginPressingDurations;

    public login_form(JFrame parent, HashMap userList, ArrayList pressingDifferences, ArrayList pressingDurations, ArrayList passwords) {

        loginListener = new KeyPressListener();
        authenticator = new Authenticator();
        input_login_text.addKeyListener(loginListener);
        status_login_label.setText("");

        setMinimumSize(new Dimension(600, 300));
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setContentPane(login_panel);
        setLocation(250,250);
        cancelLoginButton.addActionListener(new ButtonClickListener(this) {
            @Override
            public void actionPerformed(ActionEvent e) {
                getPreviousFrame().setVisible(false);
                parent.setEnabled(true);
            }
        });
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (username_login_text.getText().isEmpty() || input_login_text.getText().isEmpty()) {
                    loginListener.clearArrays();
                    input_login_text.setText("");
                    status_login_label.setText("Please Enter the username and the password!");
                } else if (loginListener.getStr_pressed_keys().contains("Backspace")) {
                    loginListener.clearArrays();
                    input_login_text.setText("");
                    status_login_label.setText("Do not enter backspaces while typing the password!");
                } else if (userList.get(username_login_text.getText()) == null) {
                    loginListener.clearArrays();
                    username_login_text.setText("");
                    input_login_text.setText("");
                    status_login_label.setText("Username does not exist!");
                } else if(!(passwords.get((Integer) userList.get(username_login_text.getText()))).equals(input_login_text.getText())) {
                    loginListener.clearArrays();
                    input_login_text.setText("");
                    status_login_label.setText("Password mismatch!");
                } else {
                    try {
                        loginPressingDifferences = loginListener.pressedDifferences();
                        loginPressingDurations = loginListener.pressedDurations();
                        Integer[] signupDiff = (Integer[]) pressingDifferences.get((Integer) userList.get(username_login_text.getText()));
                        Integer[] signupDurations = (Integer[]) pressingDurations.get((Integer) userList.get(username_login_text.getText()));
                        boolean auth = authenticator.authenticate(signupDiff, signupDurations, loginPressingDifferences, loginPressingDurations);
                        showUserInfo(loginPressingDurations, loginPressingDifferences);
                        if(auth) {
                            username_login_text.setText("");
                            input_login_text.setText("");
                            loginListener.clearArrays();
                            status_login_label.setText("User successfully authenticated!");
                        } else {
                            username_login_text.setText("");
                            input_login_text.setText("");
                            loginListener.clearArrays();
                            status_login_label.setText("Access Denied!");
                        }
                    } catch (IndexOutOfBoundsException err) {
                        username_login_text.setText("");
                        input_login_text.setText("");
                        loginListener.clearArrays();
                        status_login_label.setText("Please Enter the details again!");
                    }

                }
            }
        });
        clearLoginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                username_login_text.setText("");
                input_login_text.setText("");
                loginListener.clearArrays();
            }
        });
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
}
