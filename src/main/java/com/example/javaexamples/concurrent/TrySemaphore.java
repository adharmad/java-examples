package com.example.javaexamples.concurrent;

import java.util.UUID;
import java.util.concurrent.Semaphore;

public class TrySemaphore {
	private Semaphore semaphore = new Semaphore(5);
	public TrySemaphore() {
	}
	
	public void doIt() throws Exception{
		String[] work = new String[100];
		
		Thread[] t = new Thread[10];
		
		//W[] w = new W[10];
		for (int i=0 ; i<t.length ; i++) {
			W w = new W(UUID.randomUUID().toString());
			t[i] = new Thread(w);
		}
		
		for (int i=0 ; i<t.length ; i++) {
			t[i].start();
		}	

		for (int i=0 ; i<t.length ; i++) {
			t[i].join();
		}	
	}
	
	public static void main(String[] args) throws Exception {
		TrySemaphore sem = new TrySemaphore();
		sem.doIt();
		
	}
	
	class W implements Runnable {

		private String id;
		
		public W(String wid) {
			id = wid;
		}
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			
			System.out.println("Inside worker id=" + id);
		}
		
	}
}
