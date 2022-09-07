package com.example.javaexamples.functional;

@FunctionalInterface
public interface Runner {
    void run();

    default Runner andThen(Runner after){
        return () -> {
            this.run();
            after.run();
        };
    }

    default Runner compose(Runner before){
        return () -> {
            before.run();
            this.run();
        };
    }
}
