package com.example.javaexamples.math;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class NewtonRaphson {
    public static void main(String[] args) {
        double xn = 1;
        double xn1 = 0;
        double delta = Math.abs(xn);
        while (delta > 0.000001) {
//            double fx = Math.pow(8, xn) - 8 + xn*xn;
//            double fdashx = Math.log(8) * Math.pow(8, xn) + 2*xn;

//            double fx = Math.atan(xn) - xn*xn + 5;
//            double fdashx = 1/(1 + xn*xn) - 2*xn;

            double fx = Math.sqrt(5-Math.pow(xn, 3)) - Math.exp(xn*xn);
            double fdashx = (3*xn*xn) / (2 * Math.sqrt(5-Math.pow(xn, 3))) - 2*xn*Math.exp(xn*xn);

//            double fx = 8 * Math.cos(xn) - 8*xn*Math.sin(xn);
//            double fdashx = -16 * Math.sin(xn) - 8*xn*Math.cos(xn);

//            double fx = Math.pow(xn, 3) - xn-5;
//            double fdashx = 3*xn*xn - 1;

//            double fx = 5 * Math.sin(xn);
//            double fdashx = 5 * Math.cos(xn);

            xn1 = xn - (fx / fdashx);
            // comment out below for ionate fix
//            BigDecimal bd = new BigDecimal(Double.toString(xn1));
//            bd = bd.setScale(6, RoundingMode.HALF_UP);
//            xn1 = bd.doubleValue();
            // comment ends
            delta = Math.abs(xn1 - xn);

            System.out.println(xn + " " + xn1 + " " + delta);
            xn = xn1;
        }
    }
}
