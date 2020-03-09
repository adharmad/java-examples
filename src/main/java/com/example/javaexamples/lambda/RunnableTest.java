package com.example.javaexamples.lambda;

/**
 * Example copied from:
 * http://www.oracle.com/webfolder/technetwork/tutorials/obe/java/Lambda-QuickStart/index.html
 */
public class RunnableTest {
    public static void main(String[] args) {
        Runnable anonClassRunnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("Running anonClassRunnable!");
            }
        };

        Runnable lambdaRunnable = () -> System.out.println("Running lambdaRunnable!");

        // Run the anonClassRunnable
        //new Thread(anonClassRunnable).start();
        anonClassRunnable.run();

        // Run the lambdaRunnable
        //new Thread(lambdaRunnable).start();
        lambdaRunnable.run();

    }
}
