package com.xmchx.art;

import com.xmchx.util.SleepUtils;

/**
 * @author xmchx (sunhuayangak47@gmail.com)
 */
public class Shutdown {
	public static void main(String[] args) {
		Runner one = new Runner();
		Thread countThread = new Thread(one, "CountThread");
		countThread.start();

		SleepUtils.second(1);
		countThread.interrupt();

		Runner two = new Runner();
		countThread = new Thread(two, "CountThread");
		countThread.start();

		SleepUtils.second(1);
		two.cancel();
	}

	static class Runner implements Runnable {
		private long i;
		private volatile boolean on = true;

		@Override
		public void run() {
			while (on && !Thread.currentThread().isInterrupted()) {
				i++;
			}
			System.out.println("Count i = " + i);
		}

		public void cancel() {
			on = false;
		}
	}
}
