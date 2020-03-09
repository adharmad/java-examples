package com.example.javaexamples.anoncls;

public class CreateSimpleAnonClass {
    interface Talker {
        public void talk();
    }

    public void concreteTalk() {
        Talker talker = new Talker() {

            @Override
            public void talk() {
                System.out.println("I am talking!");
            }
        };

        talker.talk();
    }

    public static void main(String... args) {
        CreateSimpleAnonClass anonClass = new CreateSimpleAnonClass();
        anonClass.concreteTalk();
    }

}
