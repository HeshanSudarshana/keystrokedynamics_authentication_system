import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonClickListener implements ActionListener {

    private JFrame previous_frame;

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public ButtonClickListener(JFrame frame) {
        this.previous_frame = frame;
    }

    public JFrame getPreviousFrame() {
        return previous_frame;
    }

}