package com.example.javaexamples.concurrent;

public class Worker implements Runnable {

	private String tid;
	private int work;
	
	public Worker(String tid) {
		this.tid = tid;
	}
	
	public void run() {
		for (int i=0 ; i<10 ; i++) {
			System.out.println("TID = " + tid + " work = " + work + " i = " + i);
			try {
				Thread.sleep(1000);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		
		synchronized (Parent.v) {
			System.out.println("TID = " + tid + " done. adding to vector");
			Parent.v.add(this);
			Parent.v.notifyAll();
		}
		System.out.println("TID = " + tid + " done. releasing");
		Parent.s.V();

	}
	
	public void cleanup() {
		System.out.println("TID = " + tid + " cleanup");
	}

	public void setWork(int work) {
		this.work = work;
	}

}
