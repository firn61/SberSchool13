package edu.sber.lect4;

import java.util.concurrent.ThreadLocalRandom;

public class PinValidator {

    private final String pin;

    PinValidator(){
        pin = String.valueOf(ThreadLocalRandom.current().nextInt(9999));
    }
}
