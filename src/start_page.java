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
        setPressingDifferences1(new ArrayList<>());
        setPressingDifferences2(new ArrayList<>());
        setPressingDurations(new ArrayList<>());
        setPressingDurations1(new ArrayList<>());
        setPressingDurations2(new ArrayList<>());
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

    public ArrayList<Integer[]> getPressingDurations1() {
        return pressingDurations1;
    }

    public void setPressingDurations1(ArrayList<Integer[]> pressingDurations1) {
        this.pressingDurations1 = pressingDurations1;
    }

    public ArrayList<Integer[]> getPressingDifferences1() {
        return pressingDifferences1;
    }

    public void setPressingDifferences1(ArrayList<Integer[]> pressingDifferences1) {
        this.pressingDifferences1 = pressingDifferences1;
    }

    public ArrayList<Integer[]> getPressingDurations2() {
        return PressingDurations2;
    }

    public void setPressingDurations2(ArrayList<Integer[]> pressingDurations2) {
        PressingDurations2 = pressingDurations2;
    }

    public ArrayList<Integer[]> getPressingDifferences2() {
        return pressingDifferences2;
    }

    public void setPressingDifferences2(ArrayList<Integer[]> pressingDifferences2) {
        this.pressingDifferences2 = pressingDifferences2;
    }
}


