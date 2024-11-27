package Utility;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JLabel;

public class LiveClock implements Runnable {
    private JLabel clockLabel;

    public LiveClock(JLabel clockLabel) {
        this.clockLabel = clockLabel;
    }

    @Override
    public void run() {
        SimpleDateFormat formatter = new SimpleDateFormat("hh:mm:ss a");
        while (true) {
            try {
                String currentTime = formatter.format(new Date());
                clockLabel.setText(currentTime);
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
                break;
            }
        }
    }
}
