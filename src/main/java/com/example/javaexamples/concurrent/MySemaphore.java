package com.example.javaexamples.concurrent;

public class MySemaphore {
	private int permits;

	public MySemaphore(int permits) {
		this.permits = permits;
	}

	public synchronized void P() throws InterruptedException {
		permits--;
		if (permits < 0)
			wait();
	}

	public synchronized void V() {
		permits++;
		if (permits <= 0)
			notify();
	}

}
