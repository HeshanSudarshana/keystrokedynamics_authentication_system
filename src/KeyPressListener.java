import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class KeyPressListener implements KeyListener {

    ArrayList <Integer> pressed_time;
    ArrayList <Integer> released_time;
    ArrayList <String> pressed_keys;
    ArrayList <String> released_keys;

    public KeyPressListener() {
        pressed_time = new ArrayList();
        released_time = new ArrayList();
        pressed_keys = new ArrayList();
        released_keys = new ArrayList();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        pressed_time.add((int)(long)System.currentTimeMillis());
        pressed_keys.add(KeyEvent.getKeyText(e.getKeyCode()));
    }

    @Override
    public void keyReleased(KeyEvent e) {
        released_time.add((int)(long)System.currentTimeMillis());
        released_keys.add(KeyEvent.getKeyText(e.getKeyCode()));
    }

    //to calculate the key pressing durations and the differences between key press events

    public Integer[] pressedDifferences() {
        Integer [] differences = new Integer[pressed_time.size()-1];
        for (int i = 1; i < pressed_time.size(); i++) {
            differences[i-1] = pressed_time.get(i) - pressed_time.get(i - 1);
        }
        return differences;
    }

    public Integer[] pressedDurations() {
        Integer [] pressed_durations = new Integer[pressed_time.size()];
        for (int i=0;i<pressed_time.size();i++) {
            pressed_durations[i] = (released_time.get(i)-pressed_time.get(i));
        }
        return pressed_durations;
    }

    //print user info

    public void printUserInfo() {
        System.out.println("User Info:");
        System.out.print("Key pressed durations --> ");
        int temp;
        for (int i = 1; i< pressed_time.size(); i++) {
            temp = pressed_time.get(i)-pressed_time.get(i-1);
            if(i!= pressed_time.size()-1) {
                System.out.print(temp + ", ");
            }
            else {
                System.out.print(temp + "\n");
            }
        }
        System.out.print("Key pressed differences --> ");
        for (int i = 0; i< pressed_time.size(); i++) {
            temp = released_time.get(i) - pressed_time.get(i);
            if(i!= pressed_time.size()-1) {
                System.out.print(temp + ", ");
            }
            else {
                System.out.print(temp + "\n");
            }
        }
    }

    //String of user pressed keys

    public String getStr_pressed_keys() {
        String temp = "";
        for(int i = 0; i<pressed_keys.size(); i++) {
            if (i != pressed_keys.size() - 1) {
                temp += pressed_keys.get(i) + " ";
            } else {
                temp += pressed_keys.get(i);
            }
        }
        return temp;
    }

    //String of user released keys

    public String getStr_released_keys() {
        String temp = "";
        for(int i = 0; i<released_keys.size(); i++) {
            if (i != released_keys.size() - 1) {
                temp += released_keys.get(i) + " ";
            } else {
                temp += released_keys.get(i);
            }
        }
        return temp;
    }

    public void clearArrays() {
        pressed_time.clear();
        released_time.clear();
        pressed_keys.clear();
        released_keys.clear();
    }

}
