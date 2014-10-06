import javax.swing.*;
import java.applet.*;

public class TestApplet extends Applet {

    public void init() {
        try {
            JOptionPane.showMessageDialog(this, "Hello World!", "vpl-pg ;>", JOptionPane.INFORMATION_MESSAGE);
        }
        catch(Exception error) {
            System.out.println(error.getMessage());
        }
    }
}