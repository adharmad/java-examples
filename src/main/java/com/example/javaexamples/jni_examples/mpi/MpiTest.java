package com.example.javaexamples.jni_examples.mpi;

class MpiTest {
    private native void mpitest(int argc, String[] argv);
    
    public static void main(String []args){
        MpiTest m = new MpiTest();
        m.mpitest(args.length, args);
    }
    
    static {
        System.loadLibrary("MpiTest");
    }
}
