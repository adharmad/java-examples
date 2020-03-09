package com.example.javaexamples.cron;

import com.cronutils.descriptor.CronDescriptor;
import com.cronutils.model.Cron;
import com.cronutils.model.definition.CronDefinition;
import com.cronutils.model.definition.CronDefinitionBuilder;
import com.cronutils.parser.CronParser;

import java.security.SecureRandom;
import java.util.Locale;

import static com.cronutils.model.CronType.QUARTZ;

public class CronExpressionGenerator {
    public static void main(String[] args) {
        int[] testIntervals = {
                15, 30, 60, 135, 165, 183, 211, 1440, 1500, 2700, 10080
        };

        for (int testInterval : testIntervals) {
            String cronExpression = generateCronExpression(testInterval);

            CronDefinition cronDefinition =
                    CronDefinitionBuilder.instanceDefinitionFor(QUARTZ);

            //create a parser based on provided definition
            CronParser parser = new CronParser(cronDefinition);
            Cron quartzCron = parser.parse("0 * * 1-3 * ? *");

            //create a descriptor for a specific Locale
            CronDescriptor descriptor = CronDescriptor.instance(Locale.UK);

            //parse some expression and ask descriptor for description
            String description = descriptor.describe(parser.parse(cronExpression));
            //description will be: "every 45 seconds"
            System.out.println(cronExpression + " -- " + description);

            //validate expression
            quartzCron.validate();
        }

    }

    public static String generateCronExpression(int intervalInMins) {
        // reduce interval in mins by 1 to align with cron expression mins which are from 0-59
        //if (intervalInMins > 0) intervalInMins--;

        int mins = 0;
        int hours = 0;
        int days = 0;
        String cronExpression = "";
        SecureRandom rand = new SecureRandom();

        if (intervalInMins < 60) {
            mins = intervalInMins;
            int randomMin = rand.nextInt(intervalInMins);
            cronExpression = "0 " + randomMin + "/" + mins + " *   ?   *   *   *";

        } else if (intervalInMins >= 60 && intervalInMins < 1440) {
            // There does not seem to be any easy way of scheduling for fractional hours
            // eg - every 2 hours 30 mins
            // So we round to the closest "hour"

            hours = intervalInMins / 60;
            mins = intervalInMins % 60;

            if (mins >= 30) {
                hours++;
            }

            int randomMin = rand.nextInt(60);

//            if (mins == 0) {
//                cronExpression = "0 " + randomMin + " */" + hours + " ?   *   *   *";
//            } else {
//                cronExpression = "0 0/" + mins + " */" + hours + " ?   *   *   *";
//                //cronExpression = "0 " + mins + " " + randomHour + "/" + hours + " ?   *   *   *";
//            }

            cronExpression = "0 " + randomMin + " */" + hours + " ?   *   *   *";

        } else if (intervalInMins >= 1440) {
            // There does not seem to be any easy way of scheduling for fractional days
            // eg - every 1 day 1 hour
            // So we round to the closest "day"
            days = intervalInMins / 1440;
            int minsInDay = intervalInMins % 1440;

            /*
            if (minsInDay < 60) {
                mins = minsInDay;
            } else if (minsInDay >= 60 && minsInDay < 1440) {
                hours = minsInDay / 60;
                mins = minsInDay % 60;
            }



            int randomMin = rand.nextInt(60);
            int randomHour = rand.nextInt(24);

            if (mins == 0) {
                cronExpression = "0 " + randomMin + " 0/" + hours + " 0/" + days + " * *";
            } else {
                //cronExpression = "0 " + randomMin + "/" + mins + " " + randomHour + "/" + hours + " ?   * " + days +  " *";
                cronExpression = "0 0/" + mins + " 0/" + hours + " 0/" + days + " * *";
            }
             */

            if (minsInDay >= 720) {
                days++;
            }

            int randomMin = rand.nextInt(60);
            int randomHour = rand.nextInt(24);

            cronExpression = "0 " + randomMin + " " + randomHour + " 1/" + days + " * ? *";
        }

        return cronExpression;
    }
}
