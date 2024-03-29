package com.xmchx.art;

import com.xmchx.util.SleepUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author xmchx (sunhuayangak47@gmail.com)
 */
public class WaitNotify {
	static boolean flag = true;
	static final Object lock = new Object();

	public static void main(String[] args) throws InterruptedException {
		Thread waitThread = new Thread(new Wait(),"WaitThread");
		waitThread.start();
		TimeUnit.SECONDS.sleep(1);
		Thread notifyThread = new Thread(new Notify(), "NotifyThread");
		notifyThread.start();
	}

	static class Wait implements Runnable {
		@Override
		public void run() {
			synchronized (lock) {
				while (flag) {
					try {
						System.out.println(Thread.currentThread() +
								" flag is true. wait @ " + new SimpleDateFormat("HH:mm:ss").format(new Date()));
						lock.wait();
					} catch (InterruptedException ignored) {
					}
				}
				System.out.println(Thread.currentThread() +
						" flag is false. running @ " + new SimpleDateFormat("HH:mm:ss").format(new Date()));
			}
		}
	}

	static class Notify implements Runnable {

		@Override
		public void run() {
			synchronized (lock) {
				System.out.println(Thread.currentThread() +
						" hold lock. notify @ " + new SimpleDateFormat("HH:mm:ss").format(new Date()));
				lock.notifyAll();
				flag = false;
				SleepUtils.second(5);
			}

			synchronized (lock) {
				System.out.println(Thread.currentThread() +
						" hold lock again. sleep @ " + new SimpleDateFormat("HH:mm:ss").format(new Date()));
				SleepUtils.second(5);
			}
		}
	}
}
