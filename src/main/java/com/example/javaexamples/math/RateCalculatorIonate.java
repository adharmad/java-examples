package com.example.javaexamples.math;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class RateCalculatorIonate {
    public static final double PRECISION = 0.000001;

    public static void main(String[] args) {
        double rateValue = 1;
        double rateValueNew = 0;
        double errorMargin = Math.abs(rateValue);

        System.out.println("  RATE-VALUE      RATE-VALUE-NEW       ERROR-MARGIN");
        System.out.println("======================================================");

        while (errorMargin > PRECISION) {

            double rateFunction = Math.sqrt(5 - Math.pow(rateValue, 3)) - Math.exp(rateValue * rateValue);
            double derivativeRateFunction = (3 * rateValue * rateValue) / (2 * Math.sqrt(5 - Math.pow(rateValue, 3)))
                    - 2 * rateValue * Math.exp(rateValue * rateValue);

            rateValueNew = rateValue - (rateFunction / derivativeRateFunction);

            BigDecimal bigDecimalRateValue = new BigDecimal(Double.toString(rateValueNew));
            bigDecimalRateValue = bigDecimalRateValue.setScale(6, RoundingMode.HALF_UP);
            rateValueNew = bigDecimalRateValue.doubleValue();

            errorMargin = Math.abs(rateValueNew - rateValue);

            System.out.printf("    %1.6f        %1.6f        %1.16f\n", rateValue, rateValueNew, errorMargin);
            rateValue = rateValueNew;
        }
    }
}
