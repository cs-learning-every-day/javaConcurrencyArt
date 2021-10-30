package com.xmchx.art;

import java.util.concurrent.CountDownLatch;

/**
 * @author xmchx (sunhuayangak47@gmail.com)
 */
public class CountDownLatchTest {
	public static void main(String[] args) throws InterruptedException {
		CountDownLatch cd = new CountDownLatch(2);
		new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println(1);
				cd.countDown();
				System.out.println(2);
				cd.countDown();
			}
		}).start();
		cd.await();
		System.out.println(3);
	}
}
