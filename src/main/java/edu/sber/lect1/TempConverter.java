package edu.sber.lect1;

public class TempConverter {

    public static void main(String[] args) {

        double t = 36.6d;

        System.out.println(t + "C = " + convert(t, Scales.R) + "R");
        System.out.println(t + "C = " + convert(t, Scales.F) + "F");
        System.out.println(t + "C = " + convert(t, Scales.K) + "K");
    }

    public static double convert(double tempC, Scales scale) {

        switch (scale) {
            case R:
                return ((5d / 4) * tempC);
            case F:
                return ((5d / 4) * (tempC - 32));
            case K:
                return tempC - 273.15;
            default:
                throw new IllegalStateException("Unexpected value: " + scale);
        }
    }

}

enum Scales {

    F,
    R,
    K

}
