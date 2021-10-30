package com.xmchx;

/**
 * @author xmchx (sunhuayangak47@gmail.com)
 */
public class Signal {
	private static volatile int signal = 0;

	static class ThreadA implements Runnable {

		@Override
		public void run() {
			while (signal < 5) {
				if (signal % 2 == 0) {
					System.out.println("threadA: " + signal);
					signal++;
				}
			}
		}
	}

	static class ThreadB implements Runnable {

		@Override
		public void run() {
			while (signal < 5) {
				if (signal % 2 == 1) {
					System.out.println("threadB: " + signal);
					signal = signal + 1;
				}
			}
		}
	}

	public static void main(String[] args) throws InterruptedException {
		new Thread(new ThreadA()).start();
		new Thread(new ThreadA()).start();
		new Thread(new ThreadA()).start();
		new Thread(new ThreadB()).start();
		new Thread(new ThreadB()).start();
		new Thread(new ThreadB()).start();
	}
}
