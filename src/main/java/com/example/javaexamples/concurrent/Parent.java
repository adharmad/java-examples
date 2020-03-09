package com.example.javaexamples.concurrent;

import java.util.Vector;

public class Parent {
	public static Vector v = new Vector();

	public static final int NUM_THREADS = 5;

	public static MySemaphore s = new MySemaphore(NUM_THREADS);

	public static void main(String[] args) {
		Parent p = new Parent();
		p.runParent();

	}

	public Parent() {
		for (int i = 0; i < NUM_THREADS; i++) {
			Worker w = new Worker("Thread" + i);
			v.add(w);
		}

	}

	public void runParent() {

		Worker w = null;

		for (int i = 0; i < 10; i++) {

			try {
				synchronized (v) {
					System.out.println("Waiting for workers");
					while (v.size() == 0) {
						v.wait();
					}
					System.out.println("Got workers");

					w = (Worker) v.remove(0);

					s.P();

				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}

			w.setWork(i);
			Thread t = new Thread(w);
			t.start();

		}

		System.out.println("Work is done");

		try {
			for (int i = 0; i < NUM_THREADS; i++) {
				System.out.println("Acquiring " + i);
				s.P();
				synchronized (v) {
					w = (Worker) v.remove(0);
					w.cleanup();
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
