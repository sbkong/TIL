package kr.sbk;

import java.util.Timer;
import java.util.TimerTask;

public class Elevator extends Thread {
    public Elevator(String string) {
        super(string);
    }

    int FLOOR_EV = 1;
    int CNT = 0;

    public void run() {
        String evName = this.getName();

        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                System.out.println(evName + " : " + FLOOR_EV);

                CNT++;
                System.out.println(CNT);

                if(CNT>5) this.cancel();
            }
        };
        timer.schedule(timerTask, 0, 1000);

    }
}
