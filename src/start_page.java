import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.HashMap;

public class start_page extends JFrame{
    private JButton signUpButton;
    private JButton loginButton;
    private JPanel main_panel;

    private ArrayList <Integer[]> finalPressingDurations;
    private ArrayList <Integer[]> finalPressingDifferences;

    private ArrayList <String> pressedKeys;
    private ArrayList <String> releasedKeys;
    private ArrayList <String> passwords;
    private HashMap <String, Integer> users;

    public start_page() {

        setFinalPressingDifferences(new ArrayList<>());
        setFinalPressingDurations(new ArrayList<>());
        setPasswords(new ArrayList<>());
        setUsers(new HashMap<>());
        setPressedKeys(new ArrayList<>());
        setReleasedKeys(new ArrayList<>());

        setMinimumSize(new Dimension(600, 500));
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setContentPane(main_panel);
        setLocation(100,100);
        signUpButton.addActionListener(new ButtonClickListener(this) {
            @Override
            public void actionPerformed(ActionEvent e) {
                signup_form signup_page = new signup_form(getPreviousFrame(), getUsers(), getFinalPressingDifferences(), getFinalPressingDurations(), getPasswords(), getPressedKeys(), getReleasedKeys());
                signup_page.setVisible(true);
                signup_page.setAlwaysOnTop(true);
                getPreviousFrame().setEnabled(false);
            }
        });
        loginButton.addActionListener(new ButtonClickListener(this) {
            @Override
            public void actionPerformed(ActionEvent e) {
                login_form login_page = new login_form(getPreviousFrame(), getUsers(), getFinalPressingDifferences(), getFinalPressingDurations(), getPasswords(), getPressedKeys(), getReleasedKeys());
                login_page.setVisible(true);
                login_page.setAlwaysOnTop(true);
                getPreviousFrame().setEnabled(false);
            }
        });
    }

    //main method

    public static void main(String[] args) {
        start_page start_page_form = new start_page();
        start_page_form.setVisible(true);
    }


    public ArrayList<String> getPasswords() {
        return passwords;
    }

    public void setPasswords(ArrayList<String> passwords) {
        this.passwords = passwords;
    }

    public HashMap<String, Integer> getUsers() {
        return users;
    }

    public void setUsers(HashMap<String, Integer> users) {
        this.users = users;
    }

    public ArrayList<Integer[]> getFinalPressingDurations() {
        return finalPressingDurations;
    }

    public void setFinalPressingDurations(ArrayList<Integer[]> finalPressingDurations) {
        this.finalPressingDurations = finalPressingDurations;
    }

    public ArrayList<Integer[]> getFinalPressingDifferences() {
        return finalPressingDifferences;
    }

    public void setFinalPressingDifferences(ArrayList<Integer[]> finalPressingDifferences) {
        this.finalPressingDifferences = finalPressingDifferences;
    }

    public ArrayList<String> getPressedKeys() {
        return pressedKeys;
    }

    public void setPressedKeys(ArrayList<String> pressedKeys) {
        this.pressedKeys = pressedKeys;
    }

    public ArrayList<String> getReleasedKeys() {
        return releasedKeys;
    }

    public void setReleasedKeys(ArrayList<String> releasedKeys) {
        this.releasedKeys = releasedKeys;
    }
}


