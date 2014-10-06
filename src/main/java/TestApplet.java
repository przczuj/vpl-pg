import javax.swing.*;
import java.applet.*;

public class TestApplet extends Applet {

    public void init() {
        try {
            JOptionPane.showMessageDialog(this, "Hello World!");
        }
        catch(Exception error) {
            System.out.println(error.getMessage());
        }
    }
}