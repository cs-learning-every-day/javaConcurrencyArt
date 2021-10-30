package com.xmchx.art;

/**
 * @author xmchx (sunhuayangak47@gmail.com)
 */
public class JoinCountDownLatchTest {
	public static void main(String[] args) throws InterruptedException {
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("parse1 finish");
			}
		});

		Thread t2 = new Thread(() -> System.out.println("parse2 finish"));

		t1.start();
		t2.start();
		t1.join();
		t2.join();
		System.out.println("all parse finish");
	}
}
