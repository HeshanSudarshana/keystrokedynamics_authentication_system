import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.HashMap;

public class start_page extends JFrame{
    private JButton signUpButton;
    private JButton loginButton;
    private JPanel main_panel;

    private ArrayList <Integer[]> pressingDurations;
    private ArrayList <Integer[]> pressingDifferences;
    private ArrayList <Integer[]> pressingDurations1;
    private ArrayList <Integer[]> pressingDifferences1;
    private ArrayList <Integer[]> PressingDurations2;
    private ArrayList <Integer[]> pressingDifferences2;

    private ArrayList <String> passwords;
    private HashMap <String, Integer> users;

    public start_page() {

        setPressingDifferences(new ArrayList<>());
        setPressingDurations(new ArrayList<>());
        setPasswords(new ArrayList<>());
        setUsers(new HashMap<>());

        setMinimumSize(new Dimension(600, 200));
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setContentPane(main_panel);
        setLocation(250,250);
        signUpButton.addActionListener(new ButtonClickListener(this) {
            @Override
            public void actionPerformed(ActionEvent e) {
                signup_form signup_page = new signup_form(getPreviousFrame(), getUsers(), getPressingDifferences(), getPressingDurations(), getPasswords());
                signup_page.setVisible(true);
                signup_page.setAlwaysOnTop(true);
                getPreviousFrame().setEnabled(false);
            }
        });
        loginButton.addActionListener(new ButtonClickListener(this) {
            @Override
            public void actionPerformed(ActionEvent e) {
                login_form login_page = new login_form(getPreviousFrame(), getUsers(), getPressingDifferences(), getPressingDurations(), getPasswords());
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


    public ArrayList<Integer[]> getPressingDurations() {
        return pressingDurations;
    }

    public void setPressingDurations(ArrayList<Integer[]> pressingDurations) {
        this.pressingDurations = pressingDurations;
    }

    public ArrayList<Integer[]> getPressingDifferences() {
        return pressingDifferences;
    }

    public void setPressingDifferences(ArrayList<Integer[]> pressingDifferences) {
        this.pressingDifferences = pressingDifferences;
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

}


