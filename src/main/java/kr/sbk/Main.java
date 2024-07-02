package kr.sbk;

import java.io.*;

public class Main {

    public static void main(String[] args) {
        Elevator ev1 = new Elevator("EV1");
        Elevator ev2 = new Elevator("EV2");
        Elevator ev3 = new Elevator("EV3");

        ev1.start();
        ev2.start();
        ev3.start();

        
    }
}
