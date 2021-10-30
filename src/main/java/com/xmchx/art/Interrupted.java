package com.xmchx.art;

import com.xmchx.util.SleepUtils;

/**
 * @author xmchx (sunhuayangak47@gmail.com)
 */
public class Interrupted {
	public static void main(String[] args) {
		Thread sleepThread = new Thread(new SleepRunner(), "SleepThread");
		sleepThread.setDaemon(true);
		Thread busyThread = new Thread(new BusyRunner(), "BusyThread");
		busyThread.setDaemon(true);

		sleepThread.start();
		busyThread.start();

		SleepUtils.second(5);

		sleepThread.interrupt();
		busyThread.interrupt();

		System.out.println("SleepThread interrupted is " + sleepThread.isInterrupted());
		System.out.println("BusyThread interrupted is " + busyThread.isInterrupted());
		// 防止sleepThread和busyThead立刻退出
		SleepUtils.second(2);
	}

	static class SleepRunner implements Runnable {

		@Override
		public void run() {
			while (true) {
				// 抛出InterruptedException 会使中断标识位清除
				SleepUtils.second(10);
			}
		}
	}

	static class BusyRunner implements Runnable {

		@Override
		public void run() {
			while (true) {
			}
		}
	}
}
