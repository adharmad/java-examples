package com.example.javaexamples.reflection;

public class MultiThreadedClassLoaderDriver extends Thread {
	private TryMultiThreadClassLoader clsLoader;
	public static final String[] classes = {
		"testpack.Test1",
		"testpack.Test2",
		"testpack.Test3",
		"testpack.Test4",
		"testpack.Test5"
	};

	public MultiThreadedClassLoaderDriver(String tid) {
		super(tid);
		clsLoader = TryMultiThreadClassLoader.getInstance();
	}

	public void run() {
		Class c = null;
		long t1, t2;
		try {
			for (int i = 0; i < classes.length; i++) {
				t1 = System.currentTimeMillis();
				c = clsLoader.loadAdapterFromFile(classes[i]);
				t2 = System.currentTimeMillis();
				System.out.println("Thread " + getName() + " loaded class " + c + " in time " + (t2-t1));
				//System.out.println(t2-t1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		int num = 100;
		MultiThreadedClassLoaderDriver[] t = new MultiThreadedClassLoaderDriver[num];
		String prefix = "foo";
		
		for (int i = 0; i < num; i++) {
			t[i] = new MultiThreadedClassLoaderDriver(prefix + i);
		}

		for (int i = 0; i < num; i++) {
			t[i].start();
		}

		for (int i = 0; i < num; i++) {
			try {
				t[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
