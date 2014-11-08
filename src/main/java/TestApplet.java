import javax.swing.*;
import java.applet.*;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TestApplet extends Applet {

    private final static int TICK_RATE_MILLISEC = 50;

    public void init() {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        executor.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                // placeholder
            }
        }, /*first execution delay*/0, TICK_RATE_MILLISEC, TimeUnit.MILLISECONDS);

        try {
            JOptionPane.showMessageDialog(this, "Hello World!", "vpl-pg ;>", JOptionPane.INFORMATION_MESSAGE);
        }
        catch(Exception error) {
            System.out.println(error.getMessage());
        }
    }
}