package com.xmchx;

/**
 * @author xmchx (sunhuayangak47@gmail.com)
 */
public class WaitAndNotify {

	static final Object lock = new Object();

	static class A implements Runnable {
		@Override
		public void run() {
			synchronized(lock) {
				for (int i = 0; i < 10; i++) {
					System.out.println("A: " + i);
					lock.notify();
					try {
						lock.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				lock.notify();
			}
		}
	}

	static class B extends Thread {
		@Override
		public void run() {
			synchronized (lock){
				for (int i = 0; i < 10; i++) {
					System.out.println("B: " + i);
					lock.notify();
					try {
						lock.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}

				lock.notify();
			}

		}
	}

	public static void main(String[] args) throws InterruptedException {
		new Thread(new A()).start();
		Thread.sleep(1000);
		new Thread(new B()).start();
	}
}
