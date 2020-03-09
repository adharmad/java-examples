package com.example.javaexamples.jni_examples.constr;

class CallConstr {
    private native String myNewString(char[] buf, int len);
    
    public static void main(String []args){
        CallConstr c = new CallConstr();
		char[] buf = new char[10];
		buf[0] = 'a'; 
		buf[1] = 'b';
		buf[2] = 'c';
		buf[3] = 'd';
		buf[4] = 'e';
		buf[5] = 'f';
		buf[6] = 'g';
		buf[7] = 'h';
		buf[8] = 'i';
		buf[9] = 'j';
	
		String s = c.myNewString(buf, 10);
        System.out.println("String contains: " + s);
    }
    
    static {
        System.loadLibrary("CallConstr");
    }
}
